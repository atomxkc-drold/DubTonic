package com.dubtonic.player.player;

import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.MediaItem;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.dubtonic.player.data.LibraryRepository;
import com.dubtonic.player.data.api.ServerConfig;
import com.dubtonic.player.data.model.Song;
import com.dubtonic.player.di.AppContainer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: PlayerController.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0016\u001a\u00020\u0017J$\u0010\u0018\u001a\u00020\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0014\u0010 \u001a\u00020\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aJ\u000e\u0010!\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\"\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001dJ\u0016\u0010#\u001a\u00020\u00172\u0006\u0010$\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020\u001dJ\u0006\u0010&\u001a\u00020\u0017J\u0006\u0010'\u001a\u00020\u0017J\u0006\u0010(\u001a\u00020\u0017J\u0006\u0010)\u001a\u00020\u0017J\u000e\u0010*\u001a\u00020\u00172\u0006\u0010+\u001a\u00020,J\u0006\u0010-\u001a\u00020\u0017J\u0006\u0010.\u001a\u00020\u0017J\u0006\u0010/\u001a\u00020\u0017J\u001a\u00100\u001a\u00020\u00172\u0006\u00101\u001a\u00020\u001f2\b\b\u0002\u00102\u001a\u00020\u001fH\u0002J\u0010\u00103\u001a\u00020\u001d2\u0006\u00104\u001a\u00020\u001dH\u0002J\b\u00105\u001a\u00020\u0017H\u0002J\u0006\u00106\u001a\u00020\u0017J\u0006\u00107\u001a\u00020\u0017J\u0006\u00108\u001a\u00020\u0017J\u000e\u00109\u001a\u00020\u00172\u0006\u0010:\u001a\u00020;J\u0015\u0010<\u001a\u00020\u00172\b\u0010=\u001a\u0004\u0018\u00010\u001d¢\u0006\u0002\u0010>J\u000e\u0010?\u001a\u00020\u00172\u0006\u0010@\u001a\u00020;J\u0006\u0010A\u001a\u00020\u0017J\u0016\u0010B\u001a\u00020\u00172\u0006\u0010C\u001a\u00020\u001d2\u0006\u0010D\u001a\u00020\u001dJ\u000e\u0010E\u001a\u00020\u00172\u0006\u0010F\u001a\u00020\u001fJ\n\u0010G\u001a\u0004\u0018\u00010HH\u0002J\u0006\u0010I\u001a\u00020\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006J"}, d2 = {"Lcom/dubtonic/player/player/PlayerController;", "", TtmlNode.RUBY_CONTAINER, "Lcom/dubtonic/player/di/AppContainer;", "<init>", "(Lcom/dubtonic/player/di/AppContainer;)V", "scope", "Lkotlinx/coroutines/CoroutineScope;", "tickerJob", "Lkotlinx/coroutines/Job;", "audioFx", "Lcom/dubtonic/player/player/AudioFx;", "getAudioFx", "()Lcom/dubtonic/player/player/AudioFx;", "player", "Landroidx/media3/exoplayer/ExoPlayer;", "getPlayer", "()Landroidx/media3/exoplayer/ExoPlayer;", "state", "Lcom/dubtonic/player/player/PlayerUiState;", "getState", "()Lcom/dubtonic/player/player/PlayerUiState;", "restore", "", "playQueue", "songs", "", "Lcom/dubtonic/player/data/model/Song;", "index", "", "demo", "", "addToQueue", "removeAt", "jumpTo", "moveMediaItem", "from", "to", "clearQueue", "togglePlay", "play", "pause", "seekTo", "positionMs", "", "next", "prev", "stop", "advanceDemo", "auto", "backward", "randomOtherIndex", "current", "restartTicker", "toggleShuffle", "cycleRepeat", "cycleSpeed", "setSpeed", "speed", "", "setSleep", "minutes", "(Ljava/lang/Integer;)V", "setVolume", "volume", "toggleMute", "setEqBand", "band", "levelMillibels", "setBassBoost", "enabled", "serverConfig", "Lcom/dubtonic/player/data/api/ServerConfig;", "release", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class PlayerController {
    public static final int $stable = 8;
    private final AudioFx audioFx;
    private final AppContainer container;
    private final ExoPlayer player;
    private final CoroutineScope scope;
    private Job tickerJob;

    /* JADX INFO: compiled from: PlayerController.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[RepeatMode.values().length];
            try {
                iArr[RepeatMode.OFF.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[RepeatMode.ALL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[RepeatMode.ONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public PlayerController(AppContainer container) {
        Intrinsics.checkNotNullParameter(container, "container");
        this.container = container;
        CoroutineScope CoroutineScope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain()));
        this.scope = CoroutineScope;
        this.audioFx = new AudioFx();
        ExoPlayer exoPlayerBuild = new ExoPlayer.Builder(container.getContext()).build();
        Intrinsics.checkNotNullExpressionValue(exoPlayerBuild, "build(...)");
        exoPlayerBuild.setAudioAttributes(new AudioAttributes.Builder().setUsage(1).setContentType(2).build(), true);
        exoPlayerBuild.setWakeMode(1);
        this.player = exoPlayerBuild;
        BuildersKt__Builders_commonKt.launch$default(CoroutineScope, null, null, new AnonymousClass1(null), 3, null);
        exoPlayerBuild.addListener(new Player.Listener() { // from class: com.dubtonic.player.player.PlayerController.2
            @Override // androidx.media3.common.Player.Listener
            public void onIsPlayingChanged(boolean isPlaying) {
                try {
                    if (isPlaying) {
                        PlayerBus playerBus = PlayerBus.INSTANCE;
                        PlayerUiState state = PlayerController.this.getState();
                        playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : true, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
                    } else {
                        PlayerBus playerBus2 = PlayerBus.INSTANCE;
                        PlayerUiState state2 = PlayerController.this.getState();
                        playerBus2.publish$app_release(state2.copy((1048319 & 1) != 0 ? state2.queue : null, (1048319 & 2) != 0 ? state2.index : 0, (1048319 & 4) != 0 ? state2.playing : false, (1048319 & 8) != 0 ? state2.loading : false, (1048319 & 16) != 0 ? state2.positionMs : 0L, (1048319 & 32) != 0 ? state2.durationMs : 0L, (1048319 & 64) != 0 ? state2.volume : 0.0f, (1048319 & 128) != 0 ? state2.muted : false, (1048319 & 256) != 0 ? state2.speed : 0.0f, (1048319 & 512) != 0 ? state2.shuffle : false, (1048319 & 1024) != 0 ? state2.repeat : null, (1048319 & 2048) != 0 ? state2.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state2.demo : false, (1048319 & 8192) != 0 ? state2.error : null, (1048319 & 16384) != 0 ? state2.audioSessionId : 0, (1048319 & 32768) != 0 ? state2.sampleRate : 0, (1048319 & 65536) != 0 ? state2.bitDepth : 0, (1048319 & 131072) != 0 ? state2.codec : null, (1048319 & 262144) != 0 ? state2.bitrateBps : 0, (1048319 & 524288) != 0 ? state2.eq : null));
                    }
                    PlayerController.this.restartTicker();
                } catch (Exception unused) {
                }
            }

            @Override // androidx.media3.common.Player.Listener
            public void onPlaybackStateChanged(int playbackState) {
                try {
                    if (playbackState == 2) {
                        PlayerBus playerBus = PlayerBus.INSTANCE;
                        PlayerUiState state = PlayerController.this.getState();
                        playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : true, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
                    } else {
                        if (playbackState != 3) {
                            if (playbackState != 4) {
                                return;
                            }
                            PlayerBus playerBus2 = PlayerBus.INSTANCE;
                            PlayerUiState state2 = PlayerController.this.getState();
                            playerBus2.publish$app_release(state2.copy((1048319 & 1) != 0 ? state2.queue : null, (1048319 & 2) != 0 ? state2.index : 0, (1048319 & 4) != 0 ? state2.playing : false, (1048319 & 8) != 0 ? state2.loading : false, (1048319 & 16) != 0 ? state2.positionMs : 0L, (1048319 & 32) != 0 ? state2.durationMs : 0L, (1048319 & 64) != 0 ? state2.volume : 0.0f, (1048319 & 128) != 0 ? state2.muted : false, (1048319 & 256) != 0 ? state2.speed : 0.0f, (1048319 & 512) != 0 ? state2.shuffle : false, (1048319 & 1024) != 0 ? state2.repeat : null, (1048319 & 2048) != 0 ? state2.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state2.demo : false, (1048319 & 8192) != 0 ? state2.error : null, (1048319 & 16384) != 0 ? state2.audioSessionId : 0, (1048319 & 32768) != 0 ? state2.sampleRate : 0, (1048319 & 65536) != 0 ? state2.bitDepth : 0, (1048319 & 131072) != 0 ? state2.codec : null, (1048319 & 262144) != 0 ? state2.bitrateBps : 0, (1048319 & 524288) != 0 ? state2.eq : null));
                            return;
                        }
                        PlayerBus playerBus3 = PlayerBus.INSTANCE;
                        PlayerUiState state3 = PlayerController.this.getState();
                        Long lValueOf = Long.valueOf(PlayerController.this.getPlayer().getDuration());
                        if (lValueOf.longValue() == C.TIME_UNSET) {
                            lValueOf = null;
                        }
                        playerBus3.publish$app_release(state3.copy((1048319 & 1) != 0 ? state3.queue : null, (1048319 & 2) != 0 ? state3.index : 0, (1048319 & 4) != 0 ? state3.playing : false, (1048319 & 8) != 0 ? state3.loading : false, (1048319 & 16) != 0 ? state3.positionMs : RangesKt.coerceAtLeast(PlayerController.this.getPlayer().getCurrentPosition(), 0L), (1048319 & 32) != 0 ? state3.durationMs : lValueOf != null ? lValueOf.longValue() : PlayerController.this.getState().getDurationMs(), (1048319 & 64) != 0 ? state3.volume : 0.0f, (1048319 & 128) != 0 ? state3.muted : false, (1048319 & 256) != 0 ? state3.speed : 0.0f, (1048319 & 512) != 0 ? state3.shuffle : false, (1048319 & 1024) != 0 ? state3.repeat : null, (1048319 & 2048) != 0 ? state3.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state3.demo : false, (1048319 & 8192) != 0 ? state3.error : null, (1048319 & 16384) != 0 ? state3.audioSessionId : 0, (1048319 & 32768) != 0 ? state3.sampleRate : 0, (1048319 & 65536) != 0 ? state3.bitDepth : 0, (1048319 & 131072) != 0 ? state3.codec : null, (1048319 & 262144) != 0 ? state3.bitrateBps : 0, (1048319 & 524288) != 0 ? state3.eq : null));
                    }
                } catch (Exception unused) {
                }
            }

            @Override // androidx.media3.common.Player.Listener
            public void onMediaItemTransition(MediaItem mediaItem, int reason) {
                Song song;
                long jLongValue;
                try {
                    int currentMediaItemIndex = PlayerController.this.getPlayer().getCurrentMediaItemIndex();
                    if (currentMediaItemIndex >= 0 && mediaItem != null) {
                        PlayerBus playerBus = PlayerBus.INSTANCE;
                        PlayerUiState state = PlayerController.this.getState();
                        Long lValueOf = Long.valueOf(PlayerController.this.getPlayer().getDuration());
                        if (lValueOf.longValue() == C.TIME_UNSET) {
                            lValueOf = null;
                        }
                        if (lValueOf == null) {
                            QueueItem queueItem = (QueueItem) CollectionsKt.getOrNull(PlayerController.this.getState().getQueue(), currentMediaItemIndex);
                            lValueOf = (queueItem == null || (song = queueItem.getSong()) == null) ? null : Long.valueOf(song.getDuration() * ((long) 1000));
                            jLongValue = lValueOf != null ? lValueOf.longValue() : 0L;
                        }
                        playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : currentMediaItemIndex, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : jLongValue, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
                        BuildersKt__Builders_commonKt.launch$default(PlayerController.this.scope, null, null, new PlayerController$2$onMediaItemTransition$2(PlayerController.this, mediaItem, null), 3, null);
                    }
                } catch (Exception unused) {
                }
            }

            @Override // androidx.media3.common.Player.Listener
            public void onPlayerError(PlaybackException error) {
                Intrinsics.checkNotNullParameter(error, "error");
                try {
                    PlayerBus playerBus = PlayerBus.INSTANCE;
                    PlayerUiState state = PlayerController.this.getState();
                    playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : "Playback failed: " + error.getErrorCodeName(), (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
                    if (PlayerController.this.getPlayer().getMediaItemCount() > 1) {
                        PlayerController.this.getPlayer().seekToNextMediaItem();
                    }
                } catch (Exception unused) {
                }
            }
        });
    }

    public final AudioFx getAudioFx() {
        return this.audioFx;
    }

    public final ExoPlayer getPlayer() {
        return this.player;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PlayerUiState getState() {
        return PlayerBus.INSTANCE.getState().getValue();
    }

    public final void restore() {
        try {
            PlayerUiState value = PlayerBus.INSTANCE.getState().getValue();
            if (value.getQueue().isEmpty()) {
                return;
            }
            if (value.getDemo()) {
                if (value.getPlaying()) {
                    restartTicker();
                    return;
                }
                return;
            }
            if (serverConfig() == null) {
                return;
            }
            List<QueueItem> queue = value.getQueue();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = queue.iterator();
            while (it.hasNext()) {
                MediaItem mediaItem = PlayerControllerKt.toMediaItem((QueueItem) it.next());
                if (mediaItem != null) {
                    arrayList.add(mediaItem);
                }
            }
            ArrayList arrayList2 = arrayList;
            if (arrayList2.isEmpty()) {
                return;
            }
            this.player.setShuffleModeEnabled(value.getShuffle());
            this.player.setRepeatMode(PlayerControllerKt.toPlayerRepeat(value.getRepeat()));
            this.player.setMediaItems(arrayList2, RangesKt.coerceIn(value.getIndex(), 0, CollectionsKt.getLastIndex(arrayList2)), RangesKt.coerceAtLeast(value.getPositionMs(), 0L));
            this.player.prepare();
            if (value.getPlaying()) {
                this.player.play();
            }
            restartTicker();
        } catch (Exception e) {
            PlayerBus playerBus = PlayerBus.INSTANCE;
            PlayerUiState state = getState();
            String message = e.getMessage();
            if (message == null) {
                message = e.getClass().getSimpleName();
            }
            playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : "Playback failed: " + message, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
        }
    }

    /* JADX INFO: renamed from: com.dubtonic.player.player.PlayerController$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlayerController.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.player.PlayerController$1", f = "PlayerController.kt", i = {}, l = {77}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PlayerController.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object objFirst;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                objFirst = FlowKt.first(PlayerController.this.container.getSettings().getPlaybackSpeedFlow(), this);
                if (objFirst == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                objFirst = obj;
            }
            float fFloatValue = ((Number) objFirst).floatValue();
            PlayerController.this.getPlayer().setPlaybackParameters(new PlaybackParameters(fFloatValue));
            PlayerBus playerBus = PlayerBus.INSTANCE;
            PlayerUiState state = PlayerController.this.getState();
            playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : fFloatValue, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
            return Unit.INSTANCE;
        }
    }

    public final void playQueue(List<Song> songs, int index, boolean demo) {
        Intrinsics.checkNotNullParameter(songs, "songs");
        if (songs.isEmpty()) {
            return;
        }
        try {
            if (demo) {
                List<Song> list = songs;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(new QueueItem((Song) it.next(), null, null, 6, null));
                }
                ArrayList arrayList2 = arrayList;
                PlayerBus playerBus = PlayerBus.INSTANCE;
                PlayerUiState state = getState();
                playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : arrayList2, (1048319 & 2) != 0 ? state.index : RangesKt.coerceIn(index, 0, CollectionsKt.getLastIndex(arrayList2)), (1048319 & 4) != 0 ? state.playing : true, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : ((QueueItem) arrayList2.get(index)).getSong().getDuration() * ((long) 1000), (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : true, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
                BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C06441(arrayList2, index, null), 3, null);
                restartTicker();
                return;
            }
            ServerConfig serverConfig = serverConfig();
            if (serverConfig == null) {
                PlayerController playerController = this;
                PlayerBus playerBus2 = PlayerBus.INSTANCE;
                PlayerUiState state2 = getState();
                playerBus2.publish$app_release(state2.copy((1048319 & 1) != 0 ? state2.queue : null, (1048319 & 2) != 0 ? state2.index : 0, (1048319 & 4) != 0 ? state2.playing : false, (1048319 & 8) != 0 ? state2.loading : false, (1048319 & 16) != 0 ? state2.positionMs : 0L, (1048319 & 32) != 0 ? state2.durationMs : 0L, (1048319 & 64) != 0 ? state2.volume : 0.0f, (1048319 & 128) != 0 ? state2.muted : false, (1048319 & 256) != 0 ? state2.speed : 0.0f, (1048319 & 512) != 0 ? state2.shuffle : false, (1048319 & 1024) != 0 ? state2.repeat : null, (1048319 & 2048) != 0 ? state2.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state2.demo : false, (1048319 & 8192) != 0 ? state2.error : "No server connected", (1048319 & 16384) != 0 ? state2.audioSessionId : 0, (1048319 & 32768) != 0 ? state2.sampleRate : 0, (1048319 & 65536) != 0 ? state2.bitDepth : 0, (1048319 & 131072) != 0 ? state2.codec : null, (1048319 & 262144) != 0 ? state2.bitrateBps : 0, (1048319 & 524288) != 0 ? state2.eq : null));
                return;
            }
            List<Song> list2 = songs;
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (Song song : list2) {
                arrayList3.add(new QueueItem(song, this.container.getLibrary().streamUrl(serverConfig, song.getId()), LibraryRepository.coverArtUrl$default(this.container.getLibrary(), serverConfig, song.getArtworkKey(), 0, 4, null)));
            }
            ArrayList arrayList4 = arrayList3;
            ArrayList arrayList5 = new ArrayList();
            Iterator it2 = arrayList4.iterator();
            while (it2.hasNext()) {
                MediaItem mediaItem = PlayerControllerKt.toMediaItem((QueueItem) it2.next());
                if (mediaItem != null) {
                    arrayList5.add(mediaItem);
                }
            }
            ArrayList arrayList6 = arrayList5;
            if (arrayList6.isEmpty()) {
                PlayerBus playerBus3 = PlayerBus.INSTANCE;
                PlayerUiState state3 = getState();
                playerBus3.publish$app_release(state3.copy((1048319 & 1) != 0 ? state3.queue : null, (1048319 & 2) != 0 ? state3.index : 0, (1048319 & 4) != 0 ? state3.playing : false, (1048319 & 8) != 0 ? state3.loading : false, (1048319 & 16) != 0 ? state3.positionMs : 0L, (1048319 & 32) != 0 ? state3.durationMs : 0L, (1048319 & 64) != 0 ? state3.volume : 0.0f, (1048319 & 128) != 0 ? state3.muted : false, (1048319 & 256) != 0 ? state3.speed : 0.0f, (1048319 & 512) != 0 ? state3.shuffle : false, (1048319 & 1024) != 0 ? state3.repeat : null, (1048319 & 2048) != 0 ? state3.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state3.demo : false, (1048319 & 8192) != 0 ? state3.error : "No playable tracks", (1048319 & 16384) != 0 ? state3.audioSessionId : 0, (1048319 & 32768) != 0 ? state3.sampleRate : 0, (1048319 & 65536) != 0 ? state3.bitDepth : 0, (1048319 & 131072) != 0 ? state3.codec : null, (1048319 & 262144) != 0 ? state3.bitrateBps : 0, (1048319 & 524288) != 0 ? state3.eq : null));
                return;
            }
            int iCoerceIn = RangesKt.coerceIn(index, 0, CollectionsKt.getLastIndex(arrayList4));
            PlayerBus playerBus4 = PlayerBus.INSTANCE;
            PlayerUiState state4 = getState();
            playerBus4.publish$app_release(state4.copy((1048319 & 1) != 0 ? state4.queue : arrayList4, (1048319 & 2) != 0 ? state4.index : iCoerceIn, (1048319 & 4) != 0 ? state4.playing : true, (1048319 & 8) != 0 ? state4.loading : false, (1048319 & 16) != 0 ? state4.positionMs : 0L, (1048319 & 32) != 0 ? state4.durationMs : ((QueueItem) arrayList4.get(iCoerceIn)).getSong().getDuration() * ((long) 1000), (1048319 & 64) != 0 ? state4.volume : 0.0f, (1048319 & 128) != 0 ? state4.muted : false, (1048319 & 256) != 0 ? state4.speed : 0.0f, (1048319 & 512) != 0 ? state4.shuffle : false, (1048319 & 1024) != 0 ? state4.repeat : null, (1048319 & 2048) != 0 ? state4.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state4.demo : false, (1048319 & 8192) != 0 ? state4.error : null, (1048319 & 16384) != 0 ? state4.audioSessionId : 0, (1048319 & 32768) != 0 ? state4.sampleRate : 0, (1048319 & 65536) != 0 ? state4.bitDepth : 0, (1048319 & 131072) != 0 ? state4.codec : null, (1048319 & 262144) != 0 ? state4.bitrateBps : 0, (1048319 & 524288) != 0 ? state4.eq : null));
            this.player.setShuffleModeEnabled(getState().getShuffle());
            this.player.setRepeatMode(PlayerControllerKt.toPlayerRepeat(getState().getRepeat()));
            this.player.setMediaItems(arrayList6, iCoerceIn, 0L);
            this.player.prepare();
            this.player.play();
            restartTicker();
        } catch (Exception e) {
            PlayerBus playerBus5 = PlayerBus.INSTANCE;
            PlayerUiState state5 = getState();
            String message = e.getMessage();
            if (message == null) {
                message = e.getClass().getSimpleName();
            }
            playerBus5.publish$app_release(state5.copy((1048319 & 1) != 0 ? state5.queue : null, (1048319 & 2) != 0 ? state5.index : 0, (1048319 & 4) != 0 ? state5.playing : false, (1048319 & 8) != 0 ? state5.loading : false, (1048319 & 16) != 0 ? state5.positionMs : 0L, (1048319 & 32) != 0 ? state5.durationMs : 0L, (1048319 & 64) != 0 ? state5.volume : 0.0f, (1048319 & 128) != 0 ? state5.muted : false, (1048319 & 256) != 0 ? state5.speed : 0.0f, (1048319 & 512) != 0 ? state5.shuffle : false, (1048319 & 1024) != 0 ? state5.repeat : null, (1048319 & 2048) != 0 ? state5.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state5.demo : false, (1048319 & 8192) != 0 ? state5.error : "Playback failed: " + message, (1048319 & 16384) != 0 ? state5.audioSessionId : 0, (1048319 & 32768) != 0 ? state5.sampleRate : 0, (1048319 & 65536) != 0 ? state5.bitDepth : 0, (1048319 & 131072) != 0 ? state5.codec : null, (1048319 & 262144) != 0 ? state5.bitrateBps : 0, (1048319 & 524288) != 0 ? state5.eq : null));
        }
    }

    /* JADX INFO: renamed from: com.dubtonic.player.player.PlayerController$playQueue$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlayerController.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.player.PlayerController$playQueue$1", f = "PlayerController.kt", i = {}, l = {160}, m = "invokeSuspend", n = {}, s = {})
    static final class C06441 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ int $index;
        final /* synthetic */ List<QueueItem> $items;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06441(List<QueueItem> list, int i, Continuation<? super C06441> continuation) {
            super(2, continuation);
            this.$items = list;
            this.$index = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PlayerController.this.new C06441(this.$items, this.$index, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06441) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (PlayerController.this.container.getSettings().addRecent(this.$items.get(this.$index).getSong().getId(), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final void addToQueue(List<Song> songs) {
        Intrinsics.checkNotNullParameter(songs, "songs");
        if (songs.isEmpty()) {
            return;
        }
        try {
            if (getState().getDemo()) {
                List<QueueItem> queue = getState().getQueue();
                List<Song> list = songs;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(new QueueItem((Song) it.next(), null, null, 6, null));
                }
                List listPlus = CollectionsKt.plus((Collection) queue, (Iterable) arrayList);
                PlayerBus playerBus = PlayerBus.INSTANCE;
                PlayerUiState state = getState();
                playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : listPlus, (1048319 & 2) != 0 ? state.index : (getState().getIndex() >= 0 || listPlus.isEmpty()) ? getState().getIndex() : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
                return;
            }
            ServerConfig serverConfig = serverConfig();
            if (serverConfig == null) {
                return;
            }
            List<Song> list2 = songs;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (Song song : list2) {
                arrayList2.add(new QueueItem(song, this.container.getLibrary().streamUrl(serverConfig, song.getId()), LibraryRepository.coverArtUrl$default(this.container.getLibrary(), serverConfig, song.getArtworkKey(), 0, 4, null)));
            }
            ArrayList arrayList3 = arrayList2;
            ExoPlayer exoPlayer = this.player;
            ArrayList arrayList4 = new ArrayList();
            Iterator it2 = arrayList3.iterator();
            while (it2.hasNext()) {
                MediaItem mediaItem = PlayerControllerKt.toMediaItem((QueueItem) it2.next());
                if (mediaItem != null) {
                    arrayList4.add(mediaItem);
                }
            }
            exoPlayer.addMediaItems(arrayList4);
            PlayerBus playerBus2 = PlayerBus.INSTANCE;
            PlayerUiState state2 = getState();
            playerBus2.publish$app_release(state2.copy((1048319 & 1) != 0 ? state2.queue : CollectionsKt.plus((Collection) getState().getQueue(), (Iterable) arrayList3), (1048319 & 2) != 0 ? state2.index : 0, (1048319 & 4) != 0 ? state2.playing : false, (1048319 & 8) != 0 ? state2.loading : false, (1048319 & 16) != 0 ? state2.positionMs : 0L, (1048319 & 32) != 0 ? state2.durationMs : 0L, (1048319 & 64) != 0 ? state2.volume : 0.0f, (1048319 & 128) != 0 ? state2.muted : false, (1048319 & 256) != 0 ? state2.speed : 0.0f, (1048319 & 512) != 0 ? state2.shuffle : false, (1048319 & 1024) != 0 ? state2.repeat : null, (1048319 & 2048) != 0 ? state2.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state2.demo : false, (1048319 & 8192) != 0 ? state2.error : null, (1048319 & 16384) != 0 ? state2.audioSessionId : 0, (1048319 & 32768) != 0 ? state2.sampleRate : 0, (1048319 & 65536) != 0 ? state2.bitDepth : 0, (1048319 & 131072) != 0 ? state2.codec : null, (1048319 & 262144) != 0 ? state2.bitrateBps : 0, (1048319 & 524288) != 0 ? state2.eq : null));
        } catch (Exception e) {
            PlayerBus playerBus3 = PlayerBus.INSTANCE;
            PlayerUiState state3 = getState();
            String message = e.getMessage();
            if (message == null) {
                message = e.getClass().getSimpleName();
            }
            playerBus3.publish$app_release(state3.copy((1048319 & 1) != 0 ? state3.queue : null, (1048319 & 2) != 0 ? state3.index : 0, (1048319 & 4) != 0 ? state3.playing : false, (1048319 & 8) != 0 ? state3.loading : false, (1048319 & 16) != 0 ? state3.positionMs : 0L, (1048319 & 32) != 0 ? state3.durationMs : 0L, (1048319 & 64) != 0 ? state3.volume : 0.0f, (1048319 & 128) != 0 ? state3.muted : false, (1048319 & 256) != 0 ? state3.speed : 0.0f, (1048319 & 512) != 0 ? state3.shuffle : false, (1048319 & 1024) != 0 ? state3.repeat : null, (1048319 & 2048) != 0 ? state3.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state3.demo : false, (1048319 & 8192) != 0 ? state3.error : "Playback failed: " + message, (1048319 & 16384) != 0 ? state3.audioSessionId : 0, (1048319 & 32768) != 0 ? state3.sampleRate : 0, (1048319 & 65536) != 0 ? state3.bitDepth : 0, (1048319 & 131072) != 0 ? state3.codec : null, (1048319 & 262144) != 0 ? state3.bitrateBps : 0, (1048319 & 524288) != 0 ? state3.eq : null));
        }
    }

    public final void removeAt(int index) {
        try {
            if (getState().getDemo()) {
                List mutableList = CollectionsKt.toMutableList((Collection) getState().getQueue());
                if (index < 0 || index >= mutableList.size()) {
                    return;
                }
                mutableList.remove(index);
                if (getState().getIndex() == index) {
                    if (mutableList.isEmpty()) {
                        PlayerBus playerBus = PlayerBus.INSTANCE;
                        PlayerUiState state = getState();
                        playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : CollectionsKt.emptyList(), (1048319 & 2) != 0 ? state.index : -1, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
                        return;
                    } else {
                        int iCoerceAtMost = RangesKt.coerceAtMost(index, CollectionsKt.getLastIndex(mutableList));
                        PlayerBus playerBus2 = PlayerBus.INSTANCE;
                        PlayerUiState state2 = getState();
                        playerBus2.publish$app_release(state2.copy((1048319 & 1) != 0 ? state2.queue : mutableList, (1048319 & 2) != 0 ? state2.index : iCoerceAtMost, (1048319 & 4) != 0 ? state2.playing : false, (1048319 & 8) != 0 ? state2.loading : false, (1048319 & 16) != 0 ? state2.positionMs : 0L, (1048319 & 32) != 0 ? state2.durationMs : 0L, (1048319 & 64) != 0 ? state2.volume : 0.0f, (1048319 & 128) != 0 ? state2.muted : false, (1048319 & 256) != 0 ? state2.speed : 0.0f, (1048319 & 512) != 0 ? state2.shuffle : false, (1048319 & 1024) != 0 ? state2.repeat : null, (1048319 & 2048) != 0 ? state2.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state2.demo : false, (1048319 & 8192) != 0 ? state2.error : null, (1048319 & 16384) != 0 ? state2.audioSessionId : 0, (1048319 & 32768) != 0 ? state2.sampleRate : 0, (1048319 & 65536) != 0 ? state2.bitDepth : 0, (1048319 & 131072) != 0 ? state2.codec : null, (1048319 & 262144) != 0 ? state2.bitrateBps : 0, (1048319 & 524288) != 0 ? state2.eq : null));
                        return;
                    }
                }
                int index2 = getState().getIndex() > index ? getState().getIndex() - 1 : getState().getIndex();
                PlayerBus playerBus3 = PlayerBus.INSTANCE;
                PlayerUiState state3 = getState();
                playerBus3.publish$app_release(state3.copy((1048319 & 1) != 0 ? state3.queue : mutableList, (1048319 & 2) != 0 ? state3.index : index2, (1048319 & 4) != 0 ? state3.playing : false, (1048319 & 8) != 0 ? state3.loading : false, (1048319 & 16) != 0 ? state3.positionMs : 0L, (1048319 & 32) != 0 ? state3.durationMs : 0L, (1048319 & 64) != 0 ? state3.volume : 0.0f, (1048319 & 128) != 0 ? state3.muted : false, (1048319 & 256) != 0 ? state3.speed : 0.0f, (1048319 & 512) != 0 ? state3.shuffle : false, (1048319 & 1024) != 0 ? state3.repeat : null, (1048319 & 2048) != 0 ? state3.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state3.demo : false, (1048319 & 8192) != 0 ? state3.error : null, (1048319 & 16384) != 0 ? state3.audioSessionId : 0, (1048319 & 32768) != 0 ? state3.sampleRate : 0, (1048319 & 65536) != 0 ? state3.bitDepth : 0, (1048319 & 131072) != 0 ? state3.codec : null, (1048319 & 262144) != 0 ? state3.bitrateBps : 0, (1048319 & 524288) != 0 ? state3.eq : null));
                return;
            }
            this.player.removeMediaItem(index);
            PlayerBus playerBus4 = PlayerBus.INSTANCE;
            PlayerUiState state4 = getState();
            List<QueueItem> queue = getState().getQueue();
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (Object obj : queue) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                if (i != index) {
                    arrayList.add(obj);
                }
                i = i2;
            }
            playerBus4.publish$app_release(state4.copy((1048319 & 1) != 0 ? state4.queue : arrayList, (1048319 & 2) != 0 ? state4.index : RangesKt.coerceAtLeast(this.player.getCurrentMediaItemIndex(), 0), (1048319 & 4) != 0 ? state4.playing : false, (1048319 & 8) != 0 ? state4.loading : false, (1048319 & 16) != 0 ? state4.positionMs : 0L, (1048319 & 32) != 0 ? state4.durationMs : 0L, (1048319 & 64) != 0 ? state4.volume : 0.0f, (1048319 & 128) != 0 ? state4.muted : false, (1048319 & 256) != 0 ? state4.speed : 0.0f, (1048319 & 512) != 0 ? state4.shuffle : false, (1048319 & 1024) != 0 ? state4.repeat : null, (1048319 & 2048) != 0 ? state4.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state4.demo : false, (1048319 & 8192) != 0 ? state4.error : null, (1048319 & 16384) != 0 ? state4.audioSessionId : 0, (1048319 & 32768) != 0 ? state4.sampleRate : 0, (1048319 & 65536) != 0 ? state4.bitDepth : 0, (1048319 & 131072) != 0 ? state4.codec : null, (1048319 & 262144) != 0 ? state4.bitrateBps : 0, (1048319 & 524288) != 0 ? state4.eq : null));
        } catch (Exception e) {
            PlayerBus playerBus5 = PlayerBus.INSTANCE;
            PlayerUiState state5 = getState();
            String message = e.getMessage();
            if (message == null) {
                message = e.getClass().getSimpleName();
            }
            playerBus5.publish$app_release(state5.copy((1048319 & 1) != 0 ? state5.queue : null, (1048319 & 2) != 0 ? state5.index : 0, (1048319 & 4) != 0 ? state5.playing : false, (1048319 & 8) != 0 ? state5.loading : false, (1048319 & 16) != 0 ? state5.positionMs : 0L, (1048319 & 32) != 0 ? state5.durationMs : 0L, (1048319 & 64) != 0 ? state5.volume : 0.0f, (1048319 & 128) != 0 ? state5.muted : false, (1048319 & 256) != 0 ? state5.speed : 0.0f, (1048319 & 512) != 0 ? state5.shuffle : false, (1048319 & 1024) != 0 ? state5.repeat : null, (1048319 & 2048) != 0 ? state5.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state5.demo : false, (1048319 & 8192) != 0 ? state5.error : "Playback failed: " + message, (1048319 & 16384) != 0 ? state5.audioSessionId : 0, (1048319 & 32768) != 0 ? state5.sampleRate : 0, (1048319 & 65536) != 0 ? state5.bitDepth : 0, (1048319 & 131072) != 0 ? state5.codec : null, (1048319 & 262144) != 0 ? state5.bitrateBps : 0, (1048319 & 524288) != 0 ? state5.eq : null));
        }
    }

    public final void jumpTo(int index) {
        try {
            if (getState().getDemo()) {
                if (index < 0 || index >= getState().getQueue().size()) {
                    return;
                }
                PlayerBus playerBus = PlayerBus.INSTANCE;
                PlayerUiState state = getState();
                playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : index, (1048319 & 4) != 0 ? state.playing : true, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
                return;
            }
            this.player.seekTo(index, 0L);
            this.player.play();
        } catch (Exception e) {
            PlayerBus playerBus2 = PlayerBus.INSTANCE;
            PlayerUiState state2 = getState();
            String message = e.getMessage();
            if (message == null) {
                message = e.getClass().getSimpleName();
            }
            playerBus2.publish$app_release(state2.copy((1048319 & 1) != 0 ? state2.queue : null, (1048319 & 2) != 0 ? state2.index : 0, (1048319 & 4) != 0 ? state2.playing : false, (1048319 & 8) != 0 ? state2.loading : false, (1048319 & 16) != 0 ? state2.positionMs : 0L, (1048319 & 32) != 0 ? state2.durationMs : 0L, (1048319 & 64) != 0 ? state2.volume : 0.0f, (1048319 & 128) != 0 ? state2.muted : false, (1048319 & 256) != 0 ? state2.speed : 0.0f, (1048319 & 512) != 0 ? state2.shuffle : false, (1048319 & 1024) != 0 ? state2.repeat : null, (1048319 & 2048) != 0 ? state2.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state2.demo : false, (1048319 & 8192) != 0 ? state2.error : "Playback failed: " + message, (1048319 & 16384) != 0 ? state2.audioSessionId : 0, (1048319 & 32768) != 0 ? state2.sampleRate : 0, (1048319 & 65536) != 0 ? state2.bitDepth : 0, (1048319 & 131072) != 0 ? state2.codec : null, (1048319 & 262144) != 0 ? state2.bitrateBps : 0, (1048319 & 524288) != 0 ? state2.eq : null));
        }
    }

    public final void moveMediaItem(int from, int to) {
        int i;
        int i2;
        if (from == to) {
            return;
        }
        try {
            if (getState().getDemo()) {
                List mutableList = CollectionsKt.toMutableList((Collection) getState().getQueue());
                if (from < 0 || from >= mutableList.size() || to < 0 || to >= mutableList.size()) {
                    return;
                }
                mutableList.add(to, (QueueItem) mutableList.remove(from));
                int index = getState().getIndex();
                if (index == from) {
                    i = to;
                } else {
                    if (from < index && to >= index) {
                        i2 = index - 1;
                    } else if (from <= index || to > index) {
                        i = index;
                    } else {
                        i2 = index + 1;
                    }
                    i = i2;
                }
                PlayerBus playerBus = PlayerBus.INSTANCE;
                PlayerUiState state = getState();
                playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : mutableList, (1048319 & 2) != 0 ? state.index : i, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
                return;
            }
            this.player.moveMediaItem(from, to);
            List mutableList2 = CollectionsKt.toMutableList((Collection) getState().getQueue());
            if (from >= 0 && from < mutableList2.size() && to >= 0 && to < mutableList2.size()) {
                mutableList2.add(to, (QueueItem) mutableList2.remove(from));
            }
            PlayerBus playerBus2 = PlayerBus.INSTANCE;
            PlayerUiState state2 = getState();
            playerBus2.publish$app_release(state2.copy((1048319 & 1) != 0 ? state2.queue : mutableList2, (1048319 & 2) != 0 ? state2.index : RangesKt.coerceAtLeast(this.player.getCurrentMediaItemIndex(), 0), (1048319 & 4) != 0 ? state2.playing : false, (1048319 & 8) != 0 ? state2.loading : false, (1048319 & 16) != 0 ? state2.positionMs : 0L, (1048319 & 32) != 0 ? state2.durationMs : 0L, (1048319 & 64) != 0 ? state2.volume : 0.0f, (1048319 & 128) != 0 ? state2.muted : false, (1048319 & 256) != 0 ? state2.speed : 0.0f, (1048319 & 512) != 0 ? state2.shuffle : false, (1048319 & 1024) != 0 ? state2.repeat : null, (1048319 & 2048) != 0 ? state2.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state2.demo : false, (1048319 & 8192) != 0 ? state2.error : null, (1048319 & 16384) != 0 ? state2.audioSessionId : 0, (1048319 & 32768) != 0 ? state2.sampleRate : 0, (1048319 & 65536) != 0 ? state2.bitDepth : 0, (1048319 & 131072) != 0 ? state2.codec : null, (1048319 & 262144) != 0 ? state2.bitrateBps : 0, (1048319 & 524288) != 0 ? state2.eq : null));
        } catch (Exception e) {
            PlayerBus playerBus3 = PlayerBus.INSTANCE;
            PlayerUiState state3 = getState();
            String message = e.getMessage();
            if (message == null) {
                message = e.getClass().getSimpleName();
            }
            playerBus3.publish$app_release(state3.copy((1048319 & 1) != 0 ? state3.queue : null, (1048319 & 2) != 0 ? state3.index : 0, (1048319 & 4) != 0 ? state3.playing : false, (1048319 & 8) != 0 ? state3.loading : false, (1048319 & 16) != 0 ? state3.positionMs : 0L, (1048319 & 32) != 0 ? state3.durationMs : 0L, (1048319 & 64) != 0 ? state3.volume : 0.0f, (1048319 & 128) != 0 ? state3.muted : false, (1048319 & 256) != 0 ? state3.speed : 0.0f, (1048319 & 512) != 0 ? state3.shuffle : false, (1048319 & 1024) != 0 ? state3.repeat : null, (1048319 & 2048) != 0 ? state3.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state3.demo : false, (1048319 & 8192) != 0 ? state3.error : "Playback failed: " + message, (1048319 & 16384) != 0 ? state3.audioSessionId : 0, (1048319 & 32768) != 0 ? state3.sampleRate : 0, (1048319 & 65536) != 0 ? state3.bitDepth : 0, (1048319 & 131072) != 0 ? state3.codec : null, (1048319 & 262144) != 0 ? state3.bitrateBps : 0, (1048319 & 524288) != 0 ? state3.eq : null));
        }
    }

    public final void clearQueue() {
        try {
            if (getState().getDemo()) {
                PlayerBus.INSTANCE.publish$app_release(new PlayerUiState(null, 0, false, false, 0L, 0L, getState().getVolume(), getState().getMuted(), getState().getSpeed(), getState().getShuffle(), getState().getRepeat(), 0L, false, null, 0, 0, 0, null, 0, null, 1046591, null));
                return;
            }
            this.player.stop();
            this.player.clearMediaItems();
            PlayerBus.INSTANCE.publish$app_release(new PlayerUiState(null, 0, false, false, 0L, 0L, getState().getVolume(), getState().getMuted(), getState().getSpeed(), getState().getShuffle(), getState().getRepeat(), 0L, false, null, 0, 0, 0, null, 0, null, 1046591, null));
            restartTicker();
        } catch (Exception e) {
            PlayerBus playerBus = PlayerBus.INSTANCE;
            PlayerUiState state = getState();
            String message = e.getMessage();
            if (message == null) {
                message = e.getClass().getSimpleName();
            }
            playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : "Playback failed: " + message, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
        }
    }

    public final void togglePlay() {
        try {
            if (getState().getPlaying()) {
                pause();
            } else {
                play();
            }
        } catch (Exception e) {
            PlayerBus playerBus = PlayerBus.INSTANCE;
            PlayerUiState state = getState();
            String message = e.getMessage();
            if (message == null) {
                message = e.getClass().getSimpleName();
            }
            playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : "Playback failed: " + message, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
        }
    }

    public final void play() {
        try {
            if (getState().getQueue().isEmpty()) {
                return;
            }
            if (getState().getDemo()) {
                PlayerBus playerBus = PlayerBus.INSTANCE;
                PlayerUiState state = getState();
                playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : true, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
                restartTicker();
                return;
            }
            try {
                this.player.play();
                return;
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
        }
        PlayerBus playerBus2 = PlayerBus.INSTANCE;
        PlayerUiState state2 = getState();
        String message = e.getMessage();
        if (message == null) {
            message = e.getClass().getSimpleName();
        }
        playerBus2.publish$app_release(state2.copy((1048319 & 1) != 0 ? state2.queue : null, (1048319 & 2) != 0 ? state2.index : 0, (1048319 & 4) != 0 ? state2.playing : false, (1048319 & 8) != 0 ? state2.loading : false, (1048319 & 16) != 0 ? state2.positionMs : 0L, (1048319 & 32) != 0 ? state2.durationMs : 0L, (1048319 & 64) != 0 ? state2.volume : 0.0f, (1048319 & 128) != 0 ? state2.muted : false, (1048319 & 256) != 0 ? state2.speed : 0.0f, (1048319 & 512) != 0 ? state2.shuffle : false, (1048319 & 1024) != 0 ? state2.repeat : null, (1048319 & 2048) != 0 ? state2.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state2.demo : false, (1048319 & 8192) != 0 ? state2.error : "Playback failed: " + message, (1048319 & 16384) != 0 ? state2.audioSessionId : 0, (1048319 & 32768) != 0 ? state2.sampleRate : 0, (1048319 & 65536) != 0 ? state2.bitDepth : 0, (1048319 & 131072) != 0 ? state2.codec : null, (1048319 & 262144) != 0 ? state2.bitrateBps : 0, (1048319 & 524288) != 0 ? state2.eq : null));
    }

    public final void pause() {
        try {
            if (getState().getDemo()) {
                PlayerBus playerBus = PlayerBus.INSTANCE;
                PlayerUiState state = getState();
                playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
                restartTicker();
                return;
            }
            try {
                this.player.pause();
                return;
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
        }
        PlayerBus playerBus2 = PlayerBus.INSTANCE;
        PlayerUiState state2 = getState();
        String message = e.getMessage();
        if (message == null) {
            message = e.getClass().getSimpleName();
        }
        playerBus2.publish$app_release(state2.copy((1048319 & 1) != 0 ? state2.queue : null, (1048319 & 2) != 0 ? state2.index : 0, (1048319 & 4) != 0 ? state2.playing : false, (1048319 & 8) != 0 ? state2.loading : false, (1048319 & 16) != 0 ? state2.positionMs : 0L, (1048319 & 32) != 0 ? state2.durationMs : 0L, (1048319 & 64) != 0 ? state2.volume : 0.0f, (1048319 & 128) != 0 ? state2.muted : false, (1048319 & 256) != 0 ? state2.speed : 0.0f, (1048319 & 512) != 0 ? state2.shuffle : false, (1048319 & 1024) != 0 ? state2.repeat : null, (1048319 & 2048) != 0 ? state2.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state2.demo : false, (1048319 & 8192) != 0 ? state2.error : "Playback failed: " + message, (1048319 & 16384) != 0 ? state2.audioSessionId : 0, (1048319 & 32768) != 0 ? state2.sampleRate : 0, (1048319 & 65536) != 0 ? state2.bitDepth : 0, (1048319 & 131072) != 0 ? state2.codec : null, (1048319 & 262144) != 0 ? state2.bitrateBps : 0, (1048319 & 524288) != 0 ? state2.eq : null));
    }

    public final void seekTo(long positionMs) {
        try {
            if (getState().getDemo()) {
                PlayerBus playerBus = PlayerBus.INSTANCE;
                PlayerUiState state = getState();
                playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : positionMs, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
                return;
            } else {
                try {
                    this.player.seekTo(positionMs);
                    return;
                } catch (Exception e) {
                    e = e;
                }
            }
        } catch (Exception e2) {
            e = e2;
        }
        PlayerBus playerBus2 = PlayerBus.INSTANCE;
        PlayerUiState state2 = getState();
        String message = e.getMessage();
        if (message == null) {
            message = e.getClass().getSimpleName();
        }
        playerBus2.publish$app_release(state2.copy((1048319 & 1) != 0 ? state2.queue : null, (1048319 & 2) != 0 ? state2.index : 0, (1048319 & 4) != 0 ? state2.playing : false, (1048319 & 8) != 0 ? state2.loading : false, (1048319 & 16) != 0 ? state2.positionMs : 0L, (1048319 & 32) != 0 ? state2.durationMs : 0L, (1048319 & 64) != 0 ? state2.volume : 0.0f, (1048319 & 128) != 0 ? state2.muted : false, (1048319 & 256) != 0 ? state2.speed : 0.0f, (1048319 & 512) != 0 ? state2.shuffle : false, (1048319 & 1024) != 0 ? state2.repeat : null, (1048319 & 2048) != 0 ? state2.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state2.demo : false, (1048319 & 8192) != 0 ? state2.error : "Playback failed: " + message, (1048319 & 16384) != 0 ? state2.audioSessionId : 0, (1048319 & 32768) != 0 ? state2.sampleRate : 0, (1048319 & 65536) != 0 ? state2.bitDepth : 0, (1048319 & 131072) != 0 ? state2.codec : null, (1048319 & 262144) != 0 ? state2.bitrateBps : 0, (1048319 & 524288) != 0 ? state2.eq : null));
    }

    public final void next() {
        try {
            if (getState().getDemo()) {
                advanceDemo$default(this, false, false, 2, null);
            } else {
                this.player.seekToNextMediaItem();
            }
        } catch (Exception e) {
            PlayerBus playerBus = PlayerBus.INSTANCE;
            PlayerUiState state = getState();
            String message = e.getMessage();
            if (message == null) {
                message = e.getClass().getSimpleName();
            }
            playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : "Playback failed: " + message, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
        }
    }

    public final void prev() {
        try {
            if (getState().getDemo()) {
                advanceDemo(false, true);
            } else if (this.player.getCurrentPosition() > C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS) {
                this.player.seekTo(0L);
            } else {
                this.player.seekToPreviousMediaItem();
            }
        } catch (Exception e) {
            PlayerBus playerBus = PlayerBus.INSTANCE;
            PlayerUiState state = getState();
            String message = e.getMessage();
            if (message == null) {
                message = e.getClass().getSimpleName();
            }
            playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : "Playback failed: " + message, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
        }
    }

    public final void stop() {
        try {
            if (getState().getDemo()) {
                PlayerBus playerBus = PlayerBus.INSTANCE;
                PlayerUiState state = getState();
                playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
            } else {
                try {
                    this.player.stop();
                } catch (Exception e) {
                    e = e;
                    PlayerBus playerBus2 = PlayerBus.INSTANCE;
                    PlayerUiState state2 = getState();
                    String message = e.getMessage();
                    if (message == null) {
                        message = e.getClass().getSimpleName();
                    }
                    playerBus2.publish$app_release(state2.copy((1048319 & 1) != 0 ? state2.queue : null, (1048319 & 2) != 0 ? state2.index : 0, (1048319 & 4) != 0 ? state2.playing : false, (1048319 & 8) != 0 ? state2.loading : false, (1048319 & 16) != 0 ? state2.positionMs : 0L, (1048319 & 32) != 0 ? state2.durationMs : 0L, (1048319 & 64) != 0 ? state2.volume : 0.0f, (1048319 & 128) != 0 ? state2.muted : false, (1048319 & 256) != 0 ? state2.speed : 0.0f, (1048319 & 512) != 0 ? state2.shuffle : false, (1048319 & 1024) != 0 ? state2.repeat : null, (1048319 & 2048) != 0 ? state2.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state2.demo : false, (1048319 & 8192) != 0 ? state2.error : "Playback failed: " + message, (1048319 & 16384) != 0 ? state2.audioSessionId : 0, (1048319 & 32768) != 0 ? state2.sampleRate : 0, (1048319 & 65536) != 0 ? state2.bitDepth : 0, (1048319 & 131072) != 0 ? state2.codec : null, (1048319 & 262144) != 0 ? state2.bitrateBps : 0, (1048319 & 524288) != 0 ? state2.eq : null));
                    return;
                }
            }
            restartTicker();
        } catch (Exception e2) {
            e = e2;
        }
    }

    static /* synthetic */ void advanceDemo$default(PlayerController playerController, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = false;
        }
        playerController.advanceDemo(z, z2);
    }

    /* JADX WARN: Code duplicated, block: B:30:0x00ad A[PHI: r4
      0x00ad: PHI (r4v6 int) = (r4v2 int), (r4v12 int) binds: [B:29:0x00ab, B:18:0x0071] A[DONT_GENERATE, DONT_INLINE]] */
    private final void advanceDemo(boolean auto, boolean backward) {
        int index;
        PlayerUiState state = getState();
        if (state.getQueue().isEmpty()) {
            return;
        }
        if (state.getRepeat() == RepeatMode.ONE && auto) {
            PlayerBus.INSTANCE.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
            return;
        }
        int iRandomOtherIndex = 0;
        if (backward) {
            if (state.getShuffle() && state.getQueue().size() > 1) {
                iRandomOtherIndex = randomOtherIndex(state.getIndex());
            } else {
                index = state.getIndex() - 1;
                if (index >= 0) {
                    iRandomOtherIndex = index;
                } else if (state.getRepeat() == RepeatMode.ALL) {
                    iRandomOtherIndex = CollectionsKt.getLastIndex(state.getQueue());
                }
            }
        } else if (state.getShuffle() && state.getQueue().size() > 1) {
            iRandomOtherIndex = randomOtherIndex(state.getIndex());
        } else {
            index = state.getIndex() + 1;
            if (index < state.getQueue().size()) {
                iRandomOtherIndex = index;
            } else if (state.getRepeat() != RepeatMode.ALL) {
                stop();
                return;
            }
        }
        int i = iRandomOtherIndex;
        if (i == state.getIndex()) {
            PlayerBus.INSTANCE.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
        } else {
            PlayerBus.INSTANCE.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : i, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : state.getQueue().get(i).getSong().getDuration() * ((long) 1000), (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
        }
    }

    private final int randomOtherIndex(int current) {
        int size = getState().getQueue().size();
        int iRandom = RangesKt.random(RangesKt.until(0, size), Random.INSTANCE);
        return (size <= 1 || iRandom != current) ? iRandom : (iRandom + 1) % size;
    }

    /* JADX INFO: renamed from: com.dubtonic.player.player.PlayerController$restartTicker$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlayerController.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.player.PlayerController$restartTicker$1", f = "PlayerController.kt", i = {}, l = {479}, m = "invokeSuspend", n = {}, s = {})
    static final class C06451 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06451(Continuation<? super C06451> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PlayerController.this.new C06451(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06451) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Code duplicated, block: B:11:0x0029 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:15:0x003a A[Catch: Exception -> 0x0115, TryCatch #2 {Exception -> 0x0115, blocks: (B:13:0x0030, B:15:0x003a, B:17:0x0040, B:21:0x006f, B:23:0x0078, B:25:0x0085, B:28:0x008c, B:31:0x0097, B:33:0x009b, B:38:0x00a6, B:40:0x00aa, B:42:0x00b6, B:44:0x00bc, B:45:0x00c5, B:47:0x00cb, B:49:0x00d1, B:51:0x00db, B:53:0x00e1, B:56:0x00e8, B:58:0x0119, B:22:0x0074), top: B:87:0x0030 }] */
        /* JADX WARN: Code duplicated, block: B:63:0x0159 A[Catch: Exception -> 0x01b9, TryCatch #1 {Exception -> 0x01b9, blocks: (B:60:0x014b, B:61:0x0153, B:63:0x0159, B:65:0x015f, B:67:0x0173, B:69:0x017b), top: B:85:0x014b }] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0027 -> B:12:0x002a). Please report as a decompilation issue!!! */
        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final java.lang.Object invokeSuspend(java.lang.Object r34) {
            /*
                Method dump skipped, instruction units count: 447
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.dubtonic.player.player.PlayerController.C06451.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void restartTicker() {
        Job job = this.tickerJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.tickerJob = BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C06451(null), 3, null);
    }

    public final void toggleShuffle() {
        try {
            boolean z = !getState().getShuffle();
            this.player.setShuffleModeEnabled(z);
            PlayerBus playerBus = PlayerBus.INSTANCE;
            PlayerUiState state = getState();
            playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : z, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
        } catch (Exception e) {
            PlayerBus playerBus2 = PlayerBus.INSTANCE;
            PlayerUiState state2 = getState();
            String message = e.getMessage();
            if (message == null) {
                message = e.getClass().getSimpleName();
            }
            playerBus2.publish$app_release(state2.copy((1048319 & 1) != 0 ? state2.queue : null, (1048319 & 2) != 0 ? state2.index : 0, (1048319 & 4) != 0 ? state2.playing : false, (1048319 & 8) != 0 ? state2.loading : false, (1048319 & 16) != 0 ? state2.positionMs : 0L, (1048319 & 32) != 0 ? state2.durationMs : 0L, (1048319 & 64) != 0 ? state2.volume : 0.0f, (1048319 & 128) != 0 ? state2.muted : false, (1048319 & 256) != 0 ? state2.speed : 0.0f, (1048319 & 512) != 0 ? state2.shuffle : false, (1048319 & 1024) != 0 ? state2.repeat : null, (1048319 & 2048) != 0 ? state2.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state2.demo : false, (1048319 & 8192) != 0 ? state2.error : "Playback failed: " + message, (1048319 & 16384) != 0 ? state2.audioSessionId : 0, (1048319 & 32768) != 0 ? state2.sampleRate : 0, (1048319 & 65536) != 0 ? state2.bitDepth : 0, (1048319 & 131072) != 0 ? state2.codec : null, (1048319 & 262144) != 0 ? state2.bitrateBps : 0, (1048319 & 524288) != 0 ? state2.eq : null));
        }
    }

    public final void cycleRepeat() {
        RepeatMode repeatMode;
        try {
            int i = WhenMappings.$EnumSwitchMapping$0[getState().getRepeat().ordinal()];
            if (i == 1) {
                repeatMode = RepeatMode.ALL;
            } else if (i == 2) {
                repeatMode = RepeatMode.ONE;
            } else {
                if (i != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                repeatMode = RepeatMode.OFF;
            }
            RepeatMode repeatMode2 = repeatMode;
            this.player.setRepeatMode(PlayerControllerKt.toPlayerRepeat(repeatMode2));
            PlayerBus playerBus = PlayerBus.INSTANCE;
            PlayerUiState state = getState();
            playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : repeatMode2, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
        } catch (Exception e) {
            PlayerBus playerBus2 = PlayerBus.INSTANCE;
            PlayerUiState state2 = getState();
            String message = e.getMessage();
            if (message == null) {
                message = e.getClass().getSimpleName();
            }
            playerBus2.publish$app_release(state2.copy((1048319 & 1) != 0 ? state2.queue : null, (1048319 & 2) != 0 ? state2.index : 0, (1048319 & 4) != 0 ? state2.playing : false, (1048319 & 8) != 0 ? state2.loading : false, (1048319 & 16) != 0 ? state2.positionMs : 0L, (1048319 & 32) != 0 ? state2.durationMs : 0L, (1048319 & 64) != 0 ? state2.volume : 0.0f, (1048319 & 128) != 0 ? state2.muted : false, (1048319 & 256) != 0 ? state2.speed : 0.0f, (1048319 & 512) != 0 ? state2.shuffle : false, (1048319 & 1024) != 0 ? state2.repeat : null, (1048319 & 2048) != 0 ? state2.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state2.demo : false, (1048319 & 8192) != 0 ? state2.error : "Playback failed: " + message, (1048319 & 16384) != 0 ? state2.audioSessionId : 0, (1048319 & 32768) != 0 ? state2.sampleRate : 0, (1048319 & 65536) != 0 ? state2.bitDepth : 0, (1048319 & 131072) != 0 ? state2.codec : null, (1048319 & 262144) != 0 ? state2.bitrateBps : 0, (1048319 & 524288) != 0 ? state2.eq : null));
        }
    }

    public final void cycleSpeed() {
        Object next;
        try {
            List listListOf = CollectionsKt.listOf((Object[]) new Float[]{Float.valueOf(1.0f), Float.valueOf(1.25f), Float.valueOf(1.5f), Float.valueOf(1.75f), Float.valueOf(2.0f), Float.valueOf(0.75f), Float.valueOf(0.5f)});
            float speed = getState().getSpeed();
            Iterator it = listListOf.iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (((Number) next).floatValue() <= 0.01f + speed);
            Float f = (Float) next;
            try {
                setSpeed(f != null ? f.floatValue() : ((Number) CollectionsKt.first(listListOf)).floatValue());
            } catch (Exception e) {
                e = e;
                PlayerBus playerBus = PlayerBus.INSTANCE;
                PlayerUiState state = getState();
                String message = e.getMessage();
                if (message == null) {
                    message = e.getClass().getSimpleName();
                }
                playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : "Playback failed: " + message, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public final void setSpeed(float speed) {
        try {
            this.player.setPlaybackParameters(new PlaybackParameters(speed));
            PlayerBus playerBus = PlayerBus.INSTANCE;
            PlayerUiState state = getState();
            playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : speed, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
            BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C06471(speed, null), 3, null);
            restartTicker();
        } catch (Exception e) {
            PlayerBus playerBus2 = PlayerBus.INSTANCE;
            PlayerUiState state2 = getState();
            String message = e.getMessage();
            if (message == null) {
                message = e.getClass().getSimpleName();
            }
            playerBus2.publish$app_release(state2.copy((1048319 & 1) != 0 ? state2.queue : null, (1048319 & 2) != 0 ? state2.index : 0, (1048319 & 4) != 0 ? state2.playing : false, (1048319 & 8) != 0 ? state2.loading : false, (1048319 & 16) != 0 ? state2.positionMs : 0L, (1048319 & 32) != 0 ? state2.durationMs : 0L, (1048319 & 64) != 0 ? state2.volume : 0.0f, (1048319 & 128) != 0 ? state2.muted : false, (1048319 & 256) != 0 ? state2.speed : 0.0f, (1048319 & 512) != 0 ? state2.shuffle : false, (1048319 & 1024) != 0 ? state2.repeat : null, (1048319 & 2048) != 0 ? state2.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state2.demo : false, (1048319 & 8192) != 0 ? state2.error : "Playback failed: " + message, (1048319 & 16384) != 0 ? state2.audioSessionId : 0, (1048319 & 32768) != 0 ? state2.sampleRate : 0, (1048319 & 65536) != 0 ? state2.bitDepth : 0, (1048319 & 131072) != 0 ? state2.codec : null, (1048319 & 262144) != 0 ? state2.bitrateBps : 0, (1048319 & 524288) != 0 ? state2.eq : null));
        }
    }

    /* JADX INFO: renamed from: com.dubtonic.player.player.PlayerController$setSpeed$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlayerController.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.player.PlayerController$setSpeed$1", f = "PlayerController.kt", i = {}, l = {554}, m = "invokeSuspend", n = {}, s = {})
    static final class C06471 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ float $speed;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06471(float f, Continuation<? super C06471> continuation) {
            super(2, continuation);
            this.$speed = f;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return PlayerController.this.new C06471(this.$speed, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06471) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (PlayerController.this.container.getSettings().setPlaybackSpeed(this.$speed, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final void setSleep(Integer minutes) {
        if (minutes != null) {
            try {
                long jCurrentTimeMillis = minutes.intValue() <= 0 ? 0L : System.currentTimeMillis() + (((long) minutes.intValue()) * 60000);
                long j = jCurrentTimeMillis;
                PlayerBus playerBus = PlayerBus.INSTANCE;
                PlayerUiState state = getState();
                playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : j, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
                if (minutes != null || minutes.intValue() <= 0) {
                    return;
                }
                try {
                    BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C06461(minutes, j, this, null), 3, null);
                    return;
                } catch (Exception e) {
                    e = e;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } else {
            long j2 = jCurrentTimeMillis;
            PlayerBus playerBus2 = PlayerBus.INSTANCE;
            PlayerUiState state2 = getState();
            playerBus2.publish$app_release(state2.copy((1048319 & 1) != 0 ? state2.queue : null, (1048319 & 2) != 0 ? state2.index : 0, (1048319 & 4) != 0 ? state2.playing : false, (1048319 & 8) != 0 ? state2.loading : false, (1048319 & 16) != 0 ? state2.positionMs : 0L, (1048319 & 32) != 0 ? state2.durationMs : 0L, (1048319 & 64) != 0 ? state2.volume : 0.0f, (1048319 & 128) != 0 ? state2.muted : false, (1048319 & 256) != 0 ? state2.speed : 0.0f, (1048319 & 512) != 0 ? state2.shuffle : false, (1048319 & 1024) != 0 ? state2.repeat : null, (1048319 & 2048) != 0 ? state2.sleepDeadlineMs : j2, (1048319 & 4096) != 0 ? state2.demo : false, (1048319 & 8192) != 0 ? state2.error : null, (1048319 & 16384) != 0 ? state2.audioSessionId : 0, (1048319 & 32768) != 0 ? state2.sampleRate : 0, (1048319 & 65536) != 0 ? state2.bitDepth : 0, (1048319 & 131072) != 0 ? state2.codec : null, (1048319 & 262144) != 0 ? state2.bitrateBps : 0, (1048319 & 524288) != 0 ? state2.eq : null));
            if (minutes != null) {
            }
            return;
        }
        PlayerBus playerBus3 = PlayerBus.INSTANCE;
        PlayerUiState state3 = getState();
        String message = e.getMessage();
        if (message == null) {
            message = e.getClass().getSimpleName();
        }
        playerBus3.publish$app_release(state3.copy((1048319 & 1) != 0 ? state3.queue : null, (1048319 & 2) != 0 ? state3.index : 0, (1048319 & 4) != 0 ? state3.playing : false, (1048319 & 8) != 0 ? state3.loading : false, (1048319 & 16) != 0 ? state3.positionMs : 0L, (1048319 & 32) != 0 ? state3.durationMs : 0L, (1048319 & 64) != 0 ? state3.volume : 0.0f, (1048319 & 128) != 0 ? state3.muted : false, (1048319 & 256) != 0 ? state3.speed : 0.0f, (1048319 & 512) != 0 ? state3.shuffle : false, (1048319 & 1024) != 0 ? state3.repeat : null, (1048319 & 2048) != 0 ? state3.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state3.demo : false, (1048319 & 8192) != 0 ? state3.error : "Playback failed: " + message, (1048319 & 16384) != 0 ? state3.audioSessionId : 0, (1048319 & 32768) != 0 ? state3.sampleRate : 0, (1048319 & 65536) != 0 ? state3.bitDepth : 0, (1048319 & 131072) != 0 ? state3.codec : null, (1048319 & 262144) != 0 ? state3.bitrateBps : 0, (1048319 & 524288) != 0 ? state3.eq : null));
    }

    /* JADX INFO: renamed from: com.dubtonic.player.player.PlayerController$setSleep$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlayerController.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.player.PlayerController$setSleep$1", f = "PlayerController.kt", i = {}, l = {568}, m = "invokeSuspend", n = {}, s = {})
    static final class C06461 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $deadline;
        final /* synthetic */ Integer $minutes;
        int label;
        final /* synthetic */ PlayerController this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06461(Integer num, long j, PlayerController playerController, Continuation<? super C06461> continuation) {
            super(2, continuation);
            this.$minutes = num;
            this.$deadline = j;
            this.this$0 = playerController;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C06461(this.$minutes, this.$deadline, this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06461) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (DelayKt.delay(((long) this.$minutes.intValue()) * 60000, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            if (PlayerBus.INSTANCE.getState().getValue().getSleepDeadlineMs() == this.$deadline && PlayerBus.INSTANCE.getState().getValue().getPlaying()) {
                this.this$0.pause();
            }
            return Unit.INSTANCE;
        }
    }

    public final void setVolume(float volume) {
        try {
            this.player.setVolume(volume);
            PlayerBus playerBus = PlayerBus.INSTANCE;
            PlayerUiState state = getState();
            playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : volume, (1048319 & 128) != 0 ? state.muted : volume == 0.0f, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
        } catch (Exception e) {
            PlayerBus playerBus2 = PlayerBus.INSTANCE;
            PlayerUiState state2 = getState();
            String message = e.getMessage();
            if (message == null) {
                message = e.getClass().getSimpleName();
            }
            playerBus2.publish$app_release(state2.copy((1048319 & 1) != 0 ? state2.queue : null, (1048319 & 2) != 0 ? state2.index : 0, (1048319 & 4) != 0 ? state2.playing : false, (1048319 & 8) != 0 ? state2.loading : false, (1048319 & 16) != 0 ? state2.positionMs : 0L, (1048319 & 32) != 0 ? state2.durationMs : 0L, (1048319 & 64) != 0 ? state2.volume : 0.0f, (1048319 & 128) != 0 ? state2.muted : false, (1048319 & 256) != 0 ? state2.speed : 0.0f, (1048319 & 512) != 0 ? state2.shuffle : false, (1048319 & 1024) != 0 ? state2.repeat : null, (1048319 & 2048) != 0 ? state2.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state2.demo : false, (1048319 & 8192) != 0 ? state2.error : "Playback failed: " + message, (1048319 & 16384) != 0 ? state2.audioSessionId : 0, (1048319 & 32768) != 0 ? state2.sampleRate : 0, (1048319 & 65536) != 0 ? state2.bitDepth : 0, (1048319 & 131072) != 0 ? state2.codec : null, (1048319 & 262144) != 0 ? state2.bitrateBps : 0, (1048319 & 524288) != 0 ? state2.eq : null));
        }
    }

    public final void toggleMute() {
        try {
            if (getState().getMuted()) {
                this.player.setVolume(getState().getVolume());
                PlayerBus playerBus = PlayerBus.INSTANCE;
                PlayerUiState state = getState();
                playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : null));
            } else {
                this.player.setVolume(0.0f);
                PlayerBus playerBus2 = PlayerBus.INSTANCE;
                PlayerUiState state2 = getState();
                playerBus2.publish$app_release(state2.copy((1048319 & 1) != 0 ? state2.queue : null, (1048319 & 2) != 0 ? state2.index : 0, (1048319 & 4) != 0 ? state2.playing : false, (1048319 & 8) != 0 ? state2.loading : false, (1048319 & 16) != 0 ? state2.positionMs : 0L, (1048319 & 32) != 0 ? state2.durationMs : 0L, (1048319 & 64) != 0 ? state2.volume : 0.0f, (1048319 & 128) != 0 ? state2.muted : true, (1048319 & 256) != 0 ? state2.speed : 0.0f, (1048319 & 512) != 0 ? state2.shuffle : false, (1048319 & 1024) != 0 ? state2.repeat : null, (1048319 & 2048) != 0 ? state2.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state2.demo : false, (1048319 & 8192) != 0 ? state2.error : null, (1048319 & 16384) != 0 ? state2.audioSessionId : 0, (1048319 & 32768) != 0 ? state2.sampleRate : 0, (1048319 & 65536) != 0 ? state2.bitDepth : 0, (1048319 & 131072) != 0 ? state2.codec : null, (1048319 & 262144) != 0 ? state2.bitrateBps : 0, (1048319 & 524288) != 0 ? state2.eq : null));
            }
        } catch (Exception e) {
            PlayerBus playerBus3 = PlayerBus.INSTANCE;
            PlayerUiState state3 = getState();
            String message = e.getMessage();
            if (message == null) {
                message = e.getClass().getSimpleName();
            }
            playerBus3.publish$app_release(state3.copy((1048319 & 1) != 0 ? state3.queue : null, (1048319 & 2) != 0 ? state3.index : 0, (1048319 & 4) != 0 ? state3.playing : false, (1048319 & 8) != 0 ? state3.loading : false, (1048319 & 16) != 0 ? state3.positionMs : 0L, (1048319 & 32) != 0 ? state3.durationMs : 0L, (1048319 & 64) != 0 ? state3.volume : 0.0f, (1048319 & 128) != 0 ? state3.muted : false, (1048319 & 256) != 0 ? state3.speed : 0.0f, (1048319 & 512) != 0 ? state3.shuffle : false, (1048319 & 1024) != 0 ? state3.repeat : null, (1048319 & 2048) != 0 ? state3.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state3.demo : false, (1048319 & 8192) != 0 ? state3.error : "Playback failed: " + message, (1048319 & 16384) != 0 ? state3.audioSessionId : 0, (1048319 & 32768) != 0 ? state3.sampleRate : 0, (1048319 & 65536) != 0 ? state3.bitDepth : 0, (1048319 & 131072) != 0 ? state3.codec : null, (1048319 & 262144) != 0 ? state3.bitrateBps : 0, (1048319 & 524288) != 0 ? state3.eq : null));
        }
    }

    public final void setEqBand(int band, int levelMillibels) {
        this.audioFx.setBand(band, levelMillibels);
        PlayerBus playerBus = PlayerBus.INSTANCE;
        PlayerUiState state = getState();
        playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : this.audioFx.getState().getValue()));
    }

    public final void setBassBoost(boolean enabled) {
        this.audioFx.setBassBoost(enabled);
        PlayerBus playerBus = PlayerBus.INSTANCE;
        PlayerUiState state = getState();
        playerBus.publish$app_release(state.copy((1048319 & 1) != 0 ? state.queue : null, (1048319 & 2) != 0 ? state.index : 0, (1048319 & 4) != 0 ? state.playing : false, (1048319 & 8) != 0 ? state.loading : false, (1048319 & 16) != 0 ? state.positionMs : 0L, (1048319 & 32) != 0 ? state.durationMs : 0L, (1048319 & 64) != 0 ? state.volume : 0.0f, (1048319 & 128) != 0 ? state.muted : false, (1048319 & 256) != 0 ? state.speed : 0.0f, (1048319 & 512) != 0 ? state.shuffle : false, (1048319 & 1024) != 0 ? state.repeat : null, (1048319 & 2048) != 0 ? state.sleepDeadlineMs : 0L, (1048319 & 4096) != 0 ? state.demo : false, (1048319 & 8192) != 0 ? state.error : null, (1048319 & 16384) != 0 ? state.audioSessionId : 0, (1048319 & 32768) != 0 ? state.sampleRate : 0, (1048319 & 65536) != 0 ? state.bitDepth : 0, (1048319 & 131072) != 0 ? state.codec : null, (1048319 & 262144) != 0 ? state.bitrateBps : 0, (1048319 & 524288) != 0 ? state.eq : this.audioFx.getState().getValue()));
    }

    private final ServerConfig serverConfig() {
        return this.container.getSettings().getServerNow();
    }

    public final void release() {
        Job job = this.tickerJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.audioFx.release();
        this.player.release();
    }
}
