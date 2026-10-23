package com.dubtonic.player.data;

import androidx.core.location.LocationRequestCompat;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.dubtonic.player.data.api.ServerConfig;
import com.dubtonic.player.data.api.SubsonicClient;
import com.dubtonic.player.data.model.Album;
import com.dubtonic.player.data.model.AlbumList;
import com.dubtonic.player.data.model.Artist;
import com.dubtonic.player.data.model.ArtistDetail;
import com.dubtonic.player.data.model.Playlist;
import com.dubtonic.player.data.model.PlaylistDetail;
import com.dubtonic.player.data.model.SearchResult3;
import com.dubtonic.player.data.model.Song;
import com.dubtonic.player.data.model.SongList;
import com.dubtonic.player.data.model.SubsonicBody;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* JADX INFO: compiled from: LibraryRepository.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u00100\u001a\u000201J\u0006\u00102\u001a\u000201J\u000e\u00103\u001a\u0002012\u0006\u00104\u001a\u00020/J,\u00105\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u00104\u001a\u00020/2\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000209H\u0082@¢\u0006\u0002\u0010:J \u0010;\u001a\u0004\u0018\u00010\u000e2\u0006\u00104\u001a\u00020/2\u0006\u0010<\u001a\u000207H\u0086@¢\u0006\u0002\u0010=J \u0010>\u001a\u0004\u0018\u00010?2\u0006\u00104\u001a\u00020/2\u0006\u0010<\u001a\u000207H\u0086@¢\u0006\u0002\u0010=J.\u0010@\u001a\b\u0012\u0004\u0012\u00020\u001e0\r2\u0006\u00104\u001a\u00020/2\u0006\u0010A\u001a\u0002072\b\b\u0002\u0010B\u001a\u000209H\u0086@¢\u0006\u0002\u0010:J \u0010C\u001a\u0004\u0018\u00010D2\u0006\u00104\u001a\u00020/2\u0006\u0010<\u001a\u000207H\u0086@¢\u0006\u0002\u0010=J \u0010E\u001a\u0004\u0018\u00010F2\u0006\u00104\u001a\u00020/2\u0006\u0010G\u001a\u000207H\u0086@¢\u0006\u0002\u0010=J\"\u0010H\u001a\u0004\u0018\u0001072\u0006\u00104\u001a\u00020/2\u0006\u0010<\u001a\u0002072\b\b\u0002\u00108\u001a\u000209J\u0018\u0010I\u001a\u0004\u0018\u0001072\u0006\u00104\u001a\u00020/2\u0006\u0010J\u001a\u000207J\u0016\u0010K\u001a\u00020L2\u0006\u00104\u001a\u00020/H\u0086@¢\u0006\u0002\u0010MR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\n\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f0\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R \u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f0\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R \u0010\u0016\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u0017\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f0\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R \u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\r0\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\r0\f0\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0012R \u0010\u001d\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\r0\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\u001f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\r0\f0\u0010¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0012R \u0010!\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\r0\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010#\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\r0\f0\u0010¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0012R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010'\u001a\b\u0012\u0004\u0012\u00020&0\u0010¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0012R\u0014\u0010)\u001a\b\u0012\u0004\u0012\u00020&0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020&0\u0010¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u0012R\u0011\u0010,\u001a\u00020&8F¢\u0006\u0006\u001a\u0004\b,\u0010-R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006N"}, d2 = {"Lcom/dubtonic/player/data/LibraryRepository;", "", "client", "Lcom/dubtonic/player/data/api/SubsonicClient;", "settings", "Lcom/dubtonic/player/data/SettingsStore;", "<init>", "(Lcom/dubtonic/player/data/api/SubsonicClient;Lcom/dubtonic/player/data/SettingsStore;)V", "scope", "Lkotlinx/coroutines/CoroutineScope;", "_albums", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/dubtonic/player/data/LoadState;", "", "Lcom/dubtonic/player/data/model/Album;", "albums", "Lkotlinx/coroutines/flow/StateFlow;", "getAlbums", "()Lkotlinx/coroutines/flow/StateFlow;", "_newest", "newest", "getNewest", "_randomAlbums", "randomAlbums", "getRandomAlbums", "_artists", "Lcom/dubtonic/player/data/model/Artist;", "artists", "getArtists", "_songs", "Lcom/dubtonic/player/data/model/Song;", "songs", "getSongs", "_playlists", "Lcom/dubtonic/player/data/model/Playlist;", "playlists", "getPlaylists", "_connected", "", "connected", "getConnected", "_demoMode", "demoMode", "getDemoMode", "isDemo", "()Z", "loadedFor", "Lcom/dubtonic/player/data/api/ServerConfig;", "enterDemo", "", "reset", "ensureLoaded", "server", "albumListOf", "type", "", "size", "", "(Lcom/dubtonic/player/data/api/ServerConfig;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "album", TtmlNode.ATTR_ID, "(Lcom/dubtonic/player/data/api/ServerConfig;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "artist", "Lcom/dubtonic/player/data/model/ArtistDetail;", "topSongs", "artistName", "count", "playlist", "Lcom/dubtonic/player/data/model/PlaylistDetail;", "search", "Lcom/dubtonic/player/data/model/SearchResult3;", "query", "coverArtUrl", "streamUrl", "songId", "ping", "Lcom/dubtonic/player/data/model/SubsonicBody;", "(Lcom/dubtonic/player/data/api/ServerConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LibraryRepository {
    public static final int $stable = 8;
    private final MutableStateFlow<LoadState<List<Album>>> _albums;
    private final MutableStateFlow<LoadState<List<Artist>>> _artists;
    private final MutableStateFlow<Boolean> _connected;
    private final MutableStateFlow<Boolean> _demoMode;
    private final MutableStateFlow<LoadState<List<Album>>> _newest;
    private final MutableStateFlow<LoadState<List<Playlist>>> _playlists;
    private final MutableStateFlow<LoadState<List<Album>>> _randomAlbums;
    private final MutableStateFlow<LoadState<List<Song>>> _songs;
    private final StateFlow<LoadState<List<Album>>> albums;
    private final StateFlow<LoadState<List<Artist>>> artists;
    private final SubsonicClient client;
    private final StateFlow<Boolean> connected;
    private final StateFlow<Boolean> demoMode;
    private ServerConfig loadedFor;
    private final StateFlow<LoadState<List<Album>>> newest;
    private final StateFlow<LoadState<List<Playlist>>> playlists;
    private final StateFlow<LoadState<List<Album>>> randomAlbums;
    private final CoroutineScope scope;
    private final SettingsStore settings;
    private final StateFlow<LoadState<List<Song>>> songs;

    /* JADX INFO: renamed from: com.dubtonic.player.data.LibraryRepository$album$1, reason: invalid class name */
    /* JADX INFO: compiled from: LibraryRepository.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.data.LibraryRepository", f = "LibraryRepository.kt", i = {}, l = {170}, m = "album", n = {}, s = {})
    static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LibraryRepository.this.album(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.dubtonic.player.data.LibraryRepository$albumListOf$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LibraryRepository.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.data.LibraryRepository", f = "LibraryRepository.kt", i = {}, l = {164}, m = "albumListOf", n = {}, s = {})
    static final class C06281 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C06281(Continuation<? super C06281> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LibraryRepository.this.albumListOf(null, null, 0, this);
        }
    }

    /* JADX INFO: renamed from: com.dubtonic.player.data.LibraryRepository$artist$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LibraryRepository.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.data.LibraryRepository", f = "LibraryRepository.kt", i = {}, l = {183}, m = "artist", n = {}, s = {})
    static final class C06291 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C06291(Continuation<? super C06291> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LibraryRepository.this.artist(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.dubtonic.player.data.LibraryRepository$playlist$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LibraryRepository.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.data.LibraryRepository", f = "LibraryRepository.kt", i = {}, l = {205}, m = "playlist", n = {}, s = {})
    static final class C06311 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C06311(Continuation<? super C06311> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LibraryRepository.this.playlist(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.dubtonic.player.data.LibraryRepository$search$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LibraryRepository.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.data.LibraryRepository", f = "LibraryRepository.kt", i = {}, l = {216}, m = "search", n = {}, s = {})
    static final class C06321 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C06321(Continuation<? super C06321> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LibraryRepository.this.search(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.dubtonic.player.data.LibraryRepository$topSongs$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LibraryRepository.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.data.LibraryRepository", f = "LibraryRepository.kt", i = {}, l = {190}, m = "topSongs", n = {}, s = {})
    static final class C06331 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        C06331(Continuation<? super C06331> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LibraryRepository.this.topSongs(null, null, 0, this);
        }
    }

    public LibraryRepository(SubsonicClient client, SettingsStore settings) {
        Intrinsics.checkNotNullParameter(client, "client");
        Intrinsics.checkNotNullParameter(settings, "settings");
        this.client = client;
        this.settings = settings;
        this.scope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getIO()));
        MutableStateFlow<LoadState<List<Album>>> MutableStateFlow = StateFlowKt.MutableStateFlow(LoadState.Idle.INSTANCE);
        this._albums = MutableStateFlow;
        this.albums = FlowKt.asStateFlow(MutableStateFlow);
        MutableStateFlow<LoadState<List<Album>>> MutableStateFlow2 = StateFlowKt.MutableStateFlow(LoadState.Idle.INSTANCE);
        this._newest = MutableStateFlow2;
        this.newest = FlowKt.asStateFlow(MutableStateFlow2);
        MutableStateFlow<LoadState<List<Album>>> MutableStateFlow3 = StateFlowKt.MutableStateFlow(LoadState.Idle.INSTANCE);
        this._randomAlbums = MutableStateFlow3;
        this.randomAlbums = FlowKt.asStateFlow(MutableStateFlow3);
        MutableStateFlow<LoadState<List<Artist>>> MutableStateFlow4 = StateFlowKt.MutableStateFlow(LoadState.Idle.INSTANCE);
        this._artists = MutableStateFlow4;
        this.artists = FlowKt.asStateFlow(MutableStateFlow4);
        MutableStateFlow<LoadState<List<Song>>> MutableStateFlow5 = StateFlowKt.MutableStateFlow(LoadState.Idle.INSTANCE);
        this._songs = MutableStateFlow5;
        this.songs = FlowKt.asStateFlow(MutableStateFlow5);
        MutableStateFlow<LoadState<List<Playlist>>> MutableStateFlow6 = StateFlowKt.MutableStateFlow(LoadState.Idle.INSTANCE);
        this._playlists = MutableStateFlow6;
        this.playlists = FlowKt.asStateFlow(MutableStateFlow6);
        MutableStateFlow<Boolean> MutableStateFlow7 = StateFlowKt.MutableStateFlow(false);
        this._connected = MutableStateFlow7;
        this.connected = FlowKt.asStateFlow(MutableStateFlow7);
        MutableStateFlow<Boolean> MutableStateFlow8 = StateFlowKt.MutableStateFlow(false);
        this._demoMode = MutableStateFlow8;
        this.demoMode = FlowKt.asStateFlow(MutableStateFlow8);
    }

    public final StateFlow<LoadState<List<Album>>> getAlbums() {
        return this.albums;
    }

    public final StateFlow<LoadState<List<Album>>> getNewest() {
        return this.newest;
    }

    public final StateFlow<LoadState<List<Album>>> getRandomAlbums() {
        return this.randomAlbums;
    }

    public final StateFlow<LoadState<List<Artist>>> getArtists() {
        return this.artists;
    }

    public final StateFlow<LoadState<List<Song>>> getSongs() {
        return this.songs;
    }

    public final StateFlow<LoadState<List<Playlist>>> getPlaylists() {
        return this.playlists;
    }

    public final StateFlow<Boolean> getConnected() {
        return this.connected;
    }

    public final StateFlow<Boolean> getDemoMode() {
        return this.demoMode;
    }

    public final boolean isDemo() {
        return this._demoMode.getValue().booleanValue();
    }

    public final void enterDemo() {
        this._demoMode.setValue(true);
        this.loadedFor = null;
        this._albums.setValue(new LoadState.Ready(DemoLibrary.INSTANCE.getAlbums()));
        this._newest.setValue(new LoadState.Ready(CollectionsKt.reversed(CollectionsKt.takeLast(DemoLibrary.INSTANCE.getAlbums(), 4))));
        this._randomAlbums.setValue(new LoadState.Ready(CollectionsKt.take(CollectionsKt.shuffled(DemoLibrary.INSTANCE.getAlbums()), 6)));
        this._artists.setValue(new LoadState.Ready(DemoLibrary.INSTANCE.getArtists()));
        this._songs.setValue(new LoadState.Ready(DemoLibrary.INSTANCE.getSongs()));
        this._playlists.setValue(new LoadState.Ready(DemoLibrary.INSTANCE.getPlaylists()));
        this._connected.setValue(true);
    }

    public final void reset() {
        this._demoMode.setValue(false);
        this.loadedFor = null;
        this._albums.setValue(LoadState.Idle.INSTANCE);
        this._newest.setValue(LoadState.Idle.INSTANCE);
        this._randomAlbums.setValue(LoadState.Idle.INSTANCE);
        this._artists.setValue(LoadState.Idle.INSTANCE);
        this._songs.setValue(LoadState.Idle.INSTANCE);
        this._playlists.setValue(LoadState.Idle.INSTANCE);
        this._connected.setValue(false);
    }

    public final void ensureLoaded(ServerConfig server) {
        Intrinsics.checkNotNullParameter(server, "server");
        if (this._demoMode.getValue().booleanValue() || Intrinsics.areEqual(this.loadedFor, server)) {
            return;
        }
        this.loadedFor = server;
        this._albums.setValue(LoadState.Loading.INSTANCE);
        this._newest.setValue(LoadState.Loading.INSTANCE);
        this._randomAlbums.setValue(LoadState.Loading.INSTANCE);
        this._artists.setValue(LoadState.Loading.INSTANCE);
        this._songs.setValue(LoadState.Loading.INSTANCE);
        this._playlists.setValue(LoadState.Loading.INSTANCE);
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new C06301(server, null), 3, null);
    }

    /* JADX INFO: renamed from: com.dubtonic.player.data.LibraryRepository$ensureLoaded$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LibraryRepository.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.data.LibraryRepository$ensureLoaded$1", f = "LibraryRepository.kt", i = {}, l = {LocationRequestCompat.QUALITY_LOW_POWER}, m = "invokeSuspend", n = {}, s = {})
    static final class C06301 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ServerConfig $server;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06301(ServerConfig serverConfig, Continuation<? super C06301> continuation) {
            super(2, continuation);
            this.$server = serverConfig;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LibraryRepository.this.new C06301(this.$server, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06301) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: com.dubtonic.player.data.LibraryRepository$ensureLoaded$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: LibraryRepository.kt */
        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
        @DebugMetadata(c = "com.dubtonic.player.data.LibraryRepository$ensureLoaded$1$1", f = "LibraryRepository.kt", i = {}, l = {157}, m = "invokeSuspend", n = {}, s = {})
        static final class C01271 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Unit>>, Object> {
            final /* synthetic */ ServerConfig $server;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ LibraryRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01271(LibraryRepository libraryRepository, ServerConfig serverConfig, Continuation<? super C01271> continuation) {
                super(2, continuation);
                this.this$0 = libraryRepository;
                this.$server = serverConfig;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C01271 c01271 = new C01271(this.this$0, this.$server, continuation);
                c01271.L$0 = obj;
                return c01271;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends Unit>> continuation) {
                return invoke2(coroutineScope, (Continuation<? super List<Unit>>) continuation);
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<Unit>> continuation) {
                return ((C01271) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                    Deferred[] deferredArr = {BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new LibraryRepository$ensureLoaded$1$1$albumsJob$1(this.this$0, this.$server, null), 3, null), BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new LibraryRepository$ensureLoaded$1$1$newestJob$1(this.this$0, this.$server, null), 3, null), BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new LibraryRepository$ensureLoaded$1$1$randomJob$1(this.this$0, this.$server, null), 3, null), BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new LibraryRepository$ensureLoaded$1$1$artistsJob$1(this.this$0, this.$server, null), 3, null), BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new LibraryRepository$ensureLoaded$1$1$songsJob$1(this.this$0, this.$server, null), 3, null), BuildersKt__Builders_commonKt.async$default(coroutineScope, null, null, new LibraryRepository$ensureLoaded$1$1$playlistsJob$1(this.this$0, this.$server, null), 3, null)};
                    this.label = 1;
                    obj = AwaitKt.awaitAll(CollectionsKt.listOf((Object[]) deferredArr), this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return obj;
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(new C01271(LibraryRepository.this, this.$server, null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            LibraryRepository.this._connected.setValue(Boxing.boxBoolean(true));
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object albumListOf(ServerConfig serverConfig, String str, int i, Continuation<? super List<Album>> continuation) {
        C06281 c06281;
        List<Album> album;
        if (continuation instanceof C06281) {
            c06281 = (C06281) continuation;
            if ((c06281.label & Integer.MIN_VALUE) != 0) {
                c06281.label -= Integer.MIN_VALUE;
            } else {
                c06281 = new C06281(continuation);
            }
        } else {
            c06281 = new C06281(continuation);
        }
        C06281 c06282 = c06281;
        Object albumList$default = c06282.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c06282.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(albumList$default);
            SubsonicClient subsonicClient = this.client;
            c06282.label = 1;
            albumList$default = SubsonicClient.getAlbumList$default(subsonicClient, serverConfig, str, i, 0, c06282, 8, null);
            if (albumList$default == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(albumList$default);
        }
        SubsonicBody subsonicBody = (SubsonicBody) albumList$default;
        AlbumList albumList2 = subsonicBody.getAlbumList2();
        if (albumList2 != null && (album = albumList2.getAlbum()) != null) {
            return album;
        }
        AlbumList albumList = subsonicBody.getAlbumList();
        return albumList != null ? albumList.getAlbum() : CollectionsKt.emptyList();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object album(ServerConfig serverConfig, String str, Continuation<? super Album> continuation) {
        AnonymousClass1 anonymousClass1;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            if ((anonymousClass1.label & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label -= Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object album = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = anonymousClass1.label;
        if (i == 0) {
            ResultKt.throwOnFailure(album);
            if (!this._demoMode.getValue().booleanValue()) {
                SubsonicClient subsonicClient = this.client;
                anonymousClass1.label = 1;
                album = subsonicClient.getAlbum(serverConfig, str, anonymousClass1);
                if (album == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                for (Object obj : DemoLibrary.INSTANCE.getAlbums()) {
                    if (Intrinsics.areEqual(((Album) obj).getId(), str)) {
                        return obj;
                    }
                }
                return null;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(album);
        }
        return ((SubsonicBody) album).getAlbum();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object artist(ServerConfig serverConfig, String str, Continuation<? super ArtistDetail> continuation) {
        C06291 c06291;
        Object next;
        Object next2;
        if (continuation instanceof C06291) {
            c06291 = (C06291) continuation;
            if ((c06291.label & Integer.MIN_VALUE) != 0) {
                c06291.label -= Integer.MIN_VALUE;
            } else {
                c06291 = new C06291(continuation);
            }
        } else {
            c06291 = new C06291(continuation);
        }
        Object artist = c06291.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c06291.label;
        if (i == 0) {
            ResultKt.throwOnFailure(artist);
            if (!this._demoMode.getValue().booleanValue()) {
                SubsonicClient subsonicClient = this.client;
                c06291.label = 1;
                artist = subsonicClient.getArtist(serverConfig, str, c06291);
                if (artist == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                Iterator<T> it = DemoLibrary.INSTANCE.getArtists().iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!Intrinsics.areEqual(((Artist) next).getId(), str));
                Artist artist2 = (Artist) next;
                if (artist2 == null) {
                    return null;
                }
                String id = artist2.getId();
                String name = artist2.getName();
                List<Album> albums = DemoLibrary.INSTANCE.getAlbums();
                ArrayList arrayList = new ArrayList();
                for (Object obj : albums) {
                    Album album = (Album) obj;
                    Iterator<T> it2 = DemoLibrary.INSTANCE.getArtists().iterator();
                    do {
                        if (!it2.hasNext()) {
                            next2 = null;
                            break;
                        }
                        next2 = it2.next();
                    } while (!Intrinsics.areEqual(((Artist) next2).getId(), str));
                    Artist artist3 = (Artist) next2;
                    if (Intrinsics.areEqual(artist3 != null ? artist3.getName() : null, album.getArtist())) {
                        arrayList.add(obj);
                    }
                }
                return new ArtistDetail(id, name, (String) null, 0L, arrayList, 12, (DefaultConstructorMarker) null);
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(artist);
        }
        return ((SubsonicBody) artist).getArtist();
    }

    public static /* synthetic */ Object topSongs$default(LibraryRepository libraryRepository, ServerConfig serverConfig, String str, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 50;
        }
        return libraryRepository.topSongs(serverConfig, str, i, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object topSongs(ServerConfig serverConfig, String str, int i, Continuation<? super List<Song>> continuation) {
        C06331 c06331;
        List<Song> song;
        if (continuation instanceof C06331) {
            c06331 = (C06331) continuation;
            if ((c06331.label & Integer.MIN_VALUE) != 0) {
                c06331.label -= Integer.MIN_VALUE;
            } else {
                c06331 = new C06331(continuation);
            }
        } else {
            c06331 = new C06331(continuation);
        }
        Object topSongs = c06331.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c06331.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(topSongs);
            if (this._demoMode.getValue().booleanValue()) {
                List<Album> albums = DemoLibrary.INSTANCE.getAlbums();
                ArrayList arrayList = new ArrayList();
                for (Object obj : albums) {
                    if (Intrinsics.areEqual(((Album) obj).getArtist(), str)) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = new ArrayList();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    CollectionsKt.addAll(arrayList2, ((Album) it.next()).getSong());
                }
                return CollectionsKt.take(arrayList2, i);
            }
            SubsonicClient subsonicClient = this.client;
            c06331.label = 1;
            topSongs = subsonicClient.getTopSongs(serverConfig, str, i, c06331);
            if (topSongs == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(topSongs);
        }
        SongList topSongs2 = ((SubsonicBody) topSongs).getTopSongs();
        return (topSongs2 == null || (song = topSongs2.getSong()) == null) ? CollectionsKt.emptyList() : song;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001a  */
    public final Object playlist(ServerConfig serverConfig, String str, Continuation<? super PlaylistDetail> continuation) {
        C06311 c06311;
        Object next;
        if (continuation instanceof C06311) {
            c06311 = (C06311) continuation;
            if ((c06311.label & Integer.MIN_VALUE) != 0) {
                c06311.label -= Integer.MIN_VALUE;
            } else {
                c06311 = new C06311(continuation);
            }
        } else {
            c06311 = new C06311(continuation);
        }
        Object playlist = c06311.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c06311.label;
        if (i == 0) {
            ResultKt.throwOnFailure(playlist);
            if (!this._demoMode.getValue().booleanValue()) {
                SubsonicClient subsonicClient = this.client;
                c06311.label = 1;
                playlist = subsonicClient.getPlaylist(serverConfig, str, c06311);
                if (playlist == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                Iterator<T> it = DemoLibrary.INSTANCE.getPlaylists().iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (!Intrinsics.areEqual(((Playlist) next).getId(), str));
                Playlist playlist2 = (Playlist) next;
                if (playlist2 == null) {
                    return null;
                }
                return new PlaylistDetail(playlist2.getId(), playlist2.getName(), playlist2.getOwner(), playlist2.getComment(), (String) null, false, playlist2.getSongCount(), playlist2.getDuration(), (String) null, (String) null, (List) DemoLibrary.INSTANCE.playlistSongs(str), 816, (DefaultConstructorMarker) null);
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(playlist);
        }
        return ((SubsonicBody) playlist).getPlaylist();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object search(ServerConfig serverConfig, String str, Continuation<? super SearchResult3> continuation) {
        C06321 c06321;
        if (continuation instanceof C06321) {
            c06321 = (C06321) continuation;
            if ((c06321.label & Integer.MIN_VALUE) != 0) {
                c06321.label -= Integer.MIN_VALUE;
            } else {
                c06321 = new C06321(continuation);
            }
        } else {
            c06321 = new C06321(continuation);
        }
        C06321 c06322 = c06321;
        Object objSearch3$default = c06322.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = c06322.label;
        if (i == 0) {
            ResultKt.throwOnFailure(objSearch3$default);
            if (this._demoMode.getValue().booleanValue()) {
                String lowerCase = str.toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                List<Artist> artists = DemoLibrary.INSTANCE.getArtists();
                ArrayList arrayList = new ArrayList();
                for (Object obj : artists) {
                    String lowerCase2 = ((Artist) obj).getName().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
                    if (StringsKt.contains$default((CharSequence) lowerCase2, (CharSequence) lowerCase, false, 2, (Object) null)) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = arrayList;
                List<Album> albums = DemoLibrary.INSTANCE.getAlbums();
                ArrayList arrayList3 = new ArrayList();
                for (Object obj2 : albums) {
                    Album album = (Album) obj2;
                    String lowerCase3 = album.getName().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase3, "toLowerCase(...)");
                    String str2 = lowerCase;
                    if (!StringsKt.contains$default((CharSequence) lowerCase3, (CharSequence) str2, false, 2, (Object) null)) {
                        String lowerCase4 = album.getArtist().toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase4, "toLowerCase(...)");
                        if (StringsKt.contains$default((CharSequence) lowerCase4, (CharSequence) str2, false, 2, (Object) null)) {
                        }
                    }
                    arrayList3.add(obj2);
                }
                ArrayList arrayList4 = arrayList3;
                List<Song> songs = DemoLibrary.INSTANCE.getSongs();
                ArrayList arrayList5 = new ArrayList();
                for (Object obj3 : songs) {
                    Song song = (Song) obj3;
                    String lowerCase5 = song.getTitle().toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase5, "toLowerCase(...)");
                    String str3 = lowerCase;
                    if (!StringsKt.contains$default((CharSequence) lowerCase5, (CharSequence) str3, false, 2, (Object) null)) {
                        String lowerCase6 = song.getAlbum().toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(lowerCase6, "toLowerCase(...)");
                        if (!StringsKt.contains$default((CharSequence) lowerCase6, (CharSequence) str3, false, 2, (Object) null)) {
                            String lowerCase7 = song.getArtist().toLowerCase(Locale.ROOT);
                            Intrinsics.checkNotNullExpressionValue(lowerCase7, "toLowerCase(...)");
                            if (StringsKt.contains$default((CharSequence) lowerCase7, (CharSequence) str3, false, 2, (Object) null)) {
                            }
                        }
                    }
                    arrayList5.add(obj3);
                }
                return new SearchResult3(arrayList2, arrayList4, arrayList5);
            }
            SubsonicClient subsonicClient = this.client;
            c06322.label = 1;
            objSearch3$default = SubsonicClient.search3$default(subsonicClient, serverConfig, str, 0, 0, 0, c06322, 28, null);
            if (objSearch3$default == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(objSearch3$default);
        }
        return ((SubsonicBody) objSearch3$default).getSearchResult3();
    }

    public static /* synthetic */ String coverArtUrl$default(LibraryRepository libraryRepository, ServerConfig serverConfig, String str, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 512;
        }
        return libraryRepository.coverArtUrl(serverConfig, str, i);
    }

    public final String coverArtUrl(ServerConfig server, String id, int size) {
        Intrinsics.checkNotNullParameter(server, "server");
        Intrinsics.checkNotNullParameter(id, "id");
        if (this._demoMode.getValue().booleanValue()) {
            return null;
        }
        return this.client.coverArtUrl(server, id, size);
    }

    public final String streamUrl(ServerConfig server, String songId) {
        Intrinsics.checkNotNullParameter(server, "server");
        Intrinsics.checkNotNullParameter(songId, "songId");
        if (this._demoMode.getValue().booleanValue()) {
            return null;
        }
        return this.client.streamUrl(server, songId);
    }

    public final Object ping(ServerConfig serverConfig, Continuation<? super SubsonicBody> continuation) {
        return this.client.ping(serverConfig, continuation);
    }
}
