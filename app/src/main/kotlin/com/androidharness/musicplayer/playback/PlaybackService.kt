package com.androidharness.musicplayer.playback

import android.app.PendingIntent
import android.content.Intent
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.DefaultMediaNotificationProvider
import androidx.media3.session.LibraryResult
import androidx.media3.session.MediaLibraryService
import androidx.media3.session.MediaLibraryService.LibraryParams
import androidx.media3.session.MediaLibraryService.MediaLibrarySession
import androidx.media3.session.MediaSession
import com.androidharness.musicplayer.MainActivity
import com.androidharness.musicplayer.R
import com.androidharness.musicplayer.domain.repository.MusicRepository
import com.androidharness.musicplayer.playback.audio.CrossfadeController
import com.androidharness.musicplayer.ui.image.AppImageLoader
import com.google.common.collect.ImmutableList
import com.google.common.util.concurrent.Futures
import com.google.common.util.concurrent.ListenableFuture
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.guava.future
import kotlinx.coroutines.launch

@OptIn(UnstableApi::class)
@AndroidEntryPoint
class PlaybackService : MediaLibraryService() {

    @Inject lateinit var engine: PlaybackEngine
    @Inject lateinit var resolver: MediaItemResolver
    @Inject lateinit var libraryTree: LibraryTree
    @Inject lateinit var resumptionStore: PlaybackResumptionStore
    @Inject lateinit var musicRepository: MusicRepository
    @Inject lateinit var crossfade: CrossfadeController
    @Inject lateinit var imageLoader: AppImageLoader

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val playerScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private lateinit var librarySession: MediaLibrarySession

    private val playerListener = object : Player.Listener {

        override fun onPlayerError(error: PlaybackException) {
            engine.onPlaybackError(error)
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            if (playbackState == Player.STATE_READY) engine.onPlaybackSuccess()
        }

        override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
            persistQueue()
            crossfade.onTransition(engine.player)
            preloadNextArtwork()
            if (mediaItem != null) {
                scope.launch {
                    engine.onTrackChanged(runCatching { musicRepository.track(mediaItem.mediaId) }.getOrNull())
                }
            }
        }

        override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
            if (!playWhenReady) persistQueue()
        }
    }

    override fun onCreate() {
        super.onCreate()
        engine.ensureCreated()
        engine.player.addListener(playerListener)
        crossfade.attach(engine.player, playerScope)

        librarySession = MediaLibrarySession.Builder(this, engine.player, libraryCallback)
            .setSessionActivity(openAppIntent())
            .build()

        setMediaNotificationProvider(
            DefaultMediaNotificationProvider.Builder(this)
                .setChannelId("playback")
                .setChannelName(R.string.playback_channel_name)
                .build()
        )
    }

    override fun onGetSession(
        controllerInfo: MediaSession.ControllerInfo,
    ): MediaLibrarySession = librarySession

    override fun onTaskRemoved(rootIntent: Intent?) {
        val player = engine.player
        val idle = !player.playWhenReady ||
            player.mediaItemCount == 0 ||
            player.playbackState == Player.STATE_ENDED
        if (idle) stopSelf()
    }

    override fun onDestroy() {
        runCatching { persistQueue() }
        engine.player.removeListener(playerListener)
        if (::librarySession.isInitialized) librarySession.release()
        crossfade.release()
        playerScope.cancel()
        scope.cancel()
        engine.release()
        super.onDestroy()
    }

    private fun preloadNextArtwork() {
        val player = runCatching { engine.player }.getOrNull() ?: return
        val nextIndex = player.currentMediaItemIndex + 1
        if (nextIndex in 0 until player.mediaItemCount) {
            imageLoader.preload(player.getMediaItemAt(nextIndex).mediaMetadata.artworkUri)
        }
        imageLoader.preload(player.currentMediaItem?.mediaMetadata?.artworkUri)
    }

    private fun persistQueue() {
        val player = runCatching { engine.player }.getOrNull() ?: return
        if (player.mediaItemCount == 0) return
        val count = minOf(player.mediaItemCount, MAX_STORED_ITEMS)
        val items = (0 until count).map { index -> player.getMediaItemAt(index) }
        resumptionStore.save(items, player.currentMediaItemIndex, player.currentPosition)
    }

    private fun openAppIntent(): PendingIntent = PendingIntent.getActivity(
        this,
        0,
        Intent(this, MainActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE,
    )

    private val libraryCallback = object : MediaLibrarySession.Callback {

        override fun onGetLibraryRoot(
            session: MediaLibrarySession,
            browser: MediaSession.ControllerInfo,
            params: LibraryParams?,
        ): ListenableFuture<LibraryResult<MediaItem>> =
            Futures.immediateFuture(LibraryResult.ofItem(libraryTree.root(), params))

        override fun onGetChildren(
            session: MediaLibrarySession,
            browser: MediaSession.ControllerInfo,
            parentId: String,
            page: Int,
            pageSize: Int,
            params: LibraryParams?,
        ): ListenableFuture<LibraryResult<ImmutableList<MediaItem>>> =
            Futures.immediateFuture(
                LibraryResult.ofItemList(
                    ImmutableList.copyOf(libraryTree.children(parentId, page, pageSize)),
                    params,
                )
            )

        override fun onAddMediaItems(
            mediaSession: MediaSession,
            controller: MediaSession.ControllerInfo,
            mediaItems: List<MediaItem>,
        ): ListenableFuture<List<MediaItem>> = scope.future {
            resolver.toPlayable(mediaItems)
        }

        override fun onPlaybackResumption(
            mediaSession: MediaSession,
            controller: MediaSession.ControllerInfo,
        ): ListenableFuture<MediaSession.MediaItemsWithStartPosition> = scope.future {
            resolver.lastQueue()
        }
    }

    private companion object {
        const val MAX_STORED_ITEMS = 1_000
    }
}
