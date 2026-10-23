package com.dubtonic.player.data.api;

import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.media3.extractor.ts.TsExtractor;
import com.dubtonic.player.data.model.ApiException;
import com.dubtonic.player.data.model.NetworkException;
import com.dubtonic.player.data.model.SubsonicBody;
import com.dubtonic.player.data.model.SubsonicError;
import com.dubtonic.player.data.model.SubsonicResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonBuilder;
import kotlinx.serialization.json.JsonKt;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: compiled from: SubsonicClient.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0007\u0018\u0000 72\u00020\u0001:\u00017B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0086@¢\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0086@¢\u0006\u0002\u0010\fJ\u0016\u0010\u000e\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0086@¢\u0006\u0002\u0010\fJ\u001e\u0010\u000f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u0012J\u001e\u0010\u0013\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u0012J(\u0010\u0014\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00112\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0086@¢\u0006\u0002\u0010\u0018J \u0010\u0019\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\u001a\u001a\u00020\u0017H\u0086@¢\u0006\u0002\u0010\u001bJ2\u0010\u001c\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u00172\b\b\u0002\u0010\u001e\u001a\u00020\u0017H\u0086@¢\u0006\u0002\u0010\u001fJ\u0016\u0010 \u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0086@¢\u0006\u0002\u0010\fJ\u001e\u0010!\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@¢\u0006\u0002\u0010\u0012J<\u0010\"\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020\u00112\b\b\u0002\u0010$\u001a\u00020\u00172\b\b\u0002\u0010%\u001a\u00020\u00172\b\b\u0002\u0010&\u001a\u00020\u0017H\u0086@¢\u0006\u0002\u0010'J\u0018\u0010(\u001a\u0004\u0018\u00010\u00112\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020\u0011J\"\u0010*\u001a\u0004\u0018\u00010\u00112\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u0017J2\u0010+\u001a\u00020\t2\u0006\u0010,\u001a\u00020\u00112\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110.2\u0006\u0010\n\u001a\u00020\u000bH\u0082@¢\u0006\u0002\u0010/J\u0010\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u0017H\u0002J>\u00103\u001a\u00020\u00112\u0006\u00104\u001a\u00020\u00112\u0006\u0010,\u001a\u00020\u00112\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00110.2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u00105\u001a\u000206H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lcom/dubtonic/player/data/api/SubsonicClient;", "", "okHttp", "Lokhttp3/OkHttpClient;", "<init>", "(Lokhttp3/OkHttpClient;)V", "json", "Lkotlinx/serialization/json/Json;", "ping", "Lcom/dubtonic/player/data/model/SubsonicBody;", "server", "Lcom/dubtonic/player/data/api/ServerConfig;", "(Lcom/dubtonic/player/data/api/ServerConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getArtists", "getIndexes", "getAlbum", TtmlNode.ATTR_ID, "", "(Lcom/dubtonic/player/data/api/ServerConfig;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getArtist", "getTopSongs", "artist", "count", "", "(Lcom/dubtonic/player/data/api/ServerConfig;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRandomSongs", "size", "(Lcom/dubtonic/player/data/api/ServerConfig;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAlbumList", "type", "offset", "(Lcom/dubtonic/player/data/api/ServerConfig;Ljava/lang/String;IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPlaylists", "getPlaylist", "search3", "query", "artistCount", "albumCount", "songCount", "(Lcom/dubtonic/player/data/api/ServerConfig;Ljava/lang/String;IIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "streamUrl", "songId", "coverArtUrl", "get", "method", "params", "", "(Ljava/lang/String;Ljava/util/Map;Lcom/dubtonic/player/data/api/ServerConfig;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "backoff", "", "attempt", "buildUrl", TtmlNode.RUBY_BASE, "withFormat", "", "Companion", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SubsonicClient {
    private final Json json;
    private final OkHttpClient okHttp;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    /* JADX INFO: renamed from: com.dubtonic.player.data.api.SubsonicClient$getAlbumList$1, reason: invalid class name */
    /* JADX INFO: compiled from: SubsonicClient.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.data.api.SubsonicClient", f = "SubsonicClient.kt", i = {0, 0, 0}, l = {TsExtractor.TS_STREAM_TYPE_DVBSUBS, 92}, m = "getAlbumList", n = {"this", "server", "params"}, s = {"L$0", "L$1", "L$2"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SubsonicClient.this.getAlbumList(null, null, 0, 0, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long backoff(int attempt) {
        if (attempt == 1) {
            return 1000L;
        }
        if (attempt != 2) {
            return 4000L;
        }
        return ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS;
    }

    public SubsonicClient(OkHttpClient okHttp) {
        Intrinsics.checkNotNullParameter(okHttp, "okHttp");
        this.okHttp = okHttp;
        this.json = JsonKt.Json$default(null, new Function1() { // from class: com.dubtonic.player.data.api.SubsonicClient$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SubsonicClient.json$lambda$0((JsonBuilder) obj);
            }
        }, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit json$lambda$0(JsonBuilder Json) {
        Intrinsics.checkNotNullParameter(Json, "$this$Json");
        Json.setIgnoreUnknownKeys(true);
        Json.setLenient(true);
        Json.setCoerceInputValues(true);
        return Unit.INSTANCE;
    }

    public final Object ping(ServerConfig serverConfig, Continuation<? super SubsonicBody> continuation) {
        return get("ping.view", MapsKt.emptyMap(), serverConfig, continuation);
    }

    public final Object getArtists(ServerConfig serverConfig, Continuation<? super SubsonicBody> continuation) {
        return get("getArtists.view", MapsKt.emptyMap(), serverConfig, continuation);
    }

    public final Object getIndexes(ServerConfig serverConfig, Continuation<? super SubsonicBody> continuation) {
        return get("getIndexes.view", MapsKt.emptyMap(), serverConfig, continuation);
    }

    public final Object getAlbum(ServerConfig serverConfig, String str, Continuation<? super SubsonicBody> continuation) {
        return get("getAlbum.view", MapsKt.mapOf(TuplesKt.to(TtmlNode.ATTR_ID, str)), serverConfig, continuation);
    }

    public final Object getArtist(ServerConfig serverConfig, String str, Continuation<? super SubsonicBody> continuation) {
        return get("getArtist.view", MapsKt.mapOf(TuplesKt.to(TtmlNode.ATTR_ID, str)), serverConfig, continuation);
    }

    public static /* synthetic */ Object getTopSongs$default(SubsonicClient subsonicClient, ServerConfig serverConfig, String str, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 50;
        }
        return subsonicClient.getTopSongs(serverConfig, str, i, continuation);
    }

    public final Object getTopSongs(ServerConfig serverConfig, String str, int i, Continuation<? super SubsonicBody> continuation) {
        return get("getTopSongs.view", MapsKt.mapOf(TuplesKt.to("artist", str), TuplesKt.to("count", String.valueOf(i))), serverConfig, continuation);
    }

    public static /* synthetic */ Object getRandomSongs$default(SubsonicClient subsonicClient, ServerConfig serverConfig, int i, Continuation continuation, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 500;
        }
        return subsonicClient.getRandomSongs(serverConfig, i, continuation);
    }

    public final Object getRandomSongs(ServerConfig serverConfig, int i, Continuation<? super SubsonicBody> continuation) {
        return get("getRandomSongs.view", MapsKt.mapOf(TuplesKt.to("size", String.valueOf(i))), serverConfig, continuation);
    }

    public static /* synthetic */ Object getAlbumList$default(SubsonicClient subsonicClient, ServerConfig serverConfig, String str, int i, int i2, Continuation continuation, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i = 100;
        }
        int i4 = i;
        if ((i3 & 8) != 0) {
            i2 = 0;
        }
        return subsonicClient.getAlbumList(serverConfig, str, i4, i2, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:29:0x009e A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0014  */
    public final Object getAlbumList(ServerConfig serverConfig, String str, int i, int i2, Continuation<? super SubsonicBody> continuation) {
        AnonymousClass1 anonymousClass1;
        Map<String, String> mapMapOf;
        SubsonicClient subsonicClient;
        ServerConfig serverConfig2;
        Map<String, String> map;
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
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = anonymousClass1.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(obj);
            mapMapOf = MapsKt.mapOf(TuplesKt.to("type", str), TuplesKt.to("size", String.valueOf(i)), TuplesKt.to("offset", String.valueOf(i2)));
            try {
                anonymousClass1.L$0 = this;
                anonymousClass1.L$1 = serverConfig;
                anonymousClass1.L$2 = mapMapOf;
                anonymousClass1.label = 1;
                obj = get("getAlbumList2.view", mapMapOf, serverConfig, anonymousClass1);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                subsonicClient = this;
            } catch (ApiException unused) {
                subsonicClient = this;
                Map<String, String> map2 = mapMapOf;
                serverConfig2 = serverConfig;
                map = map2;
                anonymousClass1.L$0 = null;
                anonymousClass1.L$1 = null;
                anonymousClass1.L$2 = null;
                anonymousClass1.label = 2;
                obj = subsonicClient.get("getAlbumList.view", map, serverConfig2, anonymousClass1);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return (SubsonicBody) obj;
            }
        } else {
            if (i3 != 1) {
                if (i3 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return (SubsonicBody) obj;
            }
            map = (Map) anonymousClass1.L$2;
            serverConfig2 = (ServerConfig) anonymousClass1.L$1;
            subsonicClient = (SubsonicClient) anonymousClass1.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                mapMapOf = map;
                serverConfig = serverConfig2;
            } catch (ApiException unused2) {
                anonymousClass1.L$0 = null;
                anonymousClass1.L$1 = null;
                anonymousClass1.L$2 = null;
                anonymousClass1.label = 2;
                obj = subsonicClient.get("getAlbumList.view", map, serverConfig2, anonymousClass1);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return (SubsonicBody) obj;
            }
        }
        try {
            return (SubsonicBody) obj;
        } catch (ApiException unused3) {
            Map<String, String> map3 = mapMapOf;
            serverConfig2 = serverConfig;
            map = map3;
            anonymousClass1.L$0 = null;
            anonymousClass1.L$1 = null;
            anonymousClass1.L$2 = null;
            anonymousClass1.label = 2;
            obj = subsonicClient.get("getAlbumList.view", map, serverConfig2, anonymousClass1);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            return (SubsonicBody) obj;
        }
    }

    public final Object getPlaylists(ServerConfig serverConfig, Continuation<? super SubsonicBody> continuation) {
        return get("getPlaylists.view", MapsKt.emptyMap(), serverConfig, continuation);
    }

    public final Object getPlaylist(ServerConfig serverConfig, String str, Continuation<? super SubsonicBody> continuation) {
        return get("getPlaylist.view", MapsKt.mapOf(TuplesKt.to(TtmlNode.ATTR_ID, str)), serverConfig, continuation);
    }

    public static /* synthetic */ Object search3$default(SubsonicClient subsonicClient, ServerConfig serverConfig, String str, int i, int i2, int i3, Continuation continuation, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i = 20;
        }
        int i5 = i;
        if ((i4 & 8) != 0) {
            i2 = 30;
        }
        int i6 = i2;
        if ((i4 & 16) != 0) {
            i3 = 50;
        }
        return subsonicClient.search3(serverConfig, str, i5, i6, i3, continuation);
    }

    public final Object search3(ServerConfig serverConfig, String str, int i, int i2, int i3, Continuation<? super SubsonicBody> continuation) {
        return get("search3.view", MapsKt.mapOf(TuplesKt.to("query", str), TuplesKt.to("artistCount", String.valueOf(i)), TuplesKt.to("albumCount", String.valueOf(i2)), TuplesKt.to("songCount", String.valueOf(i3))), serverConfig, continuation);
    }

    public final String streamUrl(ServerConfig server, String songId) {
        Intrinsics.checkNotNullParameter(server, "server");
        Intrinsics.checkNotNullParameter(songId, "songId");
        String strNormalizedBase = server.normalizedBase();
        if (strNormalizedBase == null) {
            return null;
        }
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        mapCreateMapBuilder.put(TtmlNode.ATTR_ID, songId);
        if (server.getBitrate() > 0) {
            mapCreateMapBuilder.put("maxBitRate", String.valueOf(server.getBitrate()));
        }
        Unit unit = Unit.INSTANCE;
        return buildUrl(strNormalizedBase, "stream.view", MapsKt.build(mapCreateMapBuilder), server, false);
    }

    public static /* synthetic */ String coverArtUrl$default(SubsonicClient subsonicClient, ServerConfig serverConfig, String str, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 512;
        }
        return subsonicClient.coverArtUrl(serverConfig, str, i);
    }

    public final String coverArtUrl(ServerConfig server, String id, int size) {
        Intrinsics.checkNotNullParameter(server, "server");
        Intrinsics.checkNotNullParameter(id, "id");
        String strNormalizedBase = server.normalizedBase();
        if (strNormalizedBase == null) {
            return null;
        }
        return buildUrl(strNormalizedBase, "getCoverArt.view", MapsKt.mapOf(TuplesKt.to(TtmlNode.ATTR_ID, id), TuplesKt.to("size", String.valueOf(size))), server, false);
    }

    /* JADX INFO: renamed from: com.dubtonic.player.data.api.SubsonicClient$get$2, reason: invalid class name */
    /* JADX INFO: compiled from: SubsonicClient.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/dubtonic/player/data/model/SubsonicBody;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "com.dubtonic.player.data.api.SubsonicClient$get$2", f = "SubsonicClient.kt", i = {0, 0, 0, 1, 1, 1}, l = {171, 174}, m = "invokeSuspend", n = {"url", "lastError", "attempt", "url", "lastError", "attempt"}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "I$0"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SubsonicBody>, Object> {
        final /* synthetic */ String $method;
        final /* synthetic */ Map<String, String> $params;
        final /* synthetic */ ServerConfig $server;
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ SubsonicClient this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(ServerConfig serverConfig, SubsonicClient subsonicClient, String str, Map<String, String> map, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$server = serverConfig;
            this.this$0 = subsonicClient;
            this.$method = str;
            this.$params = map;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.$server, this.this$0, this.$method, this.$params, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super SubsonicBody> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Exception {
            NetworkException networkException;
            int i;
            String str;
            String strString;
            String message;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                String strNormalizedBase = this.$server.normalizedBase();
                if (strNormalizedBase == null) {
                    throw new NetworkException("Server address is invalid", null, 2, null);
                }
                String strBuildUrl$default = SubsonicClient.buildUrl$default(this.this$0, strNormalizedBase, this.$method, this.$params, this.$server, false, 16, null);
                networkException = new NetworkException("Could not reach the server", null, 2, null);
                i = 0;
                str = strBuildUrl$default;
            } else {
                if (i2 != 1 && i2 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                int i3 = this.I$0;
                Exception exc = (Exception) this.L$1;
                str = (String) this.L$0;
                ResultKt.throwOnFailure(obj);
                i = i3;
                networkException = exc;
            }
            while (i < 4) {
                i++;
                try {
                    Response responseExecute = this.this$0.okHttp.newCall(new Request.Builder().url(str).get().build()).execute();
                    SubsonicClient subsonicClient = this.this$0;
                    try {
                        Response response = responseExecute;
                        ResponseBody responseBodyBody = response.body();
                        if (responseBodyBody == null || (strString = responseBodyBody.string()) == null) {
                            strString = "";
                        }
                        if (response.isSuccessful()) {
                            try {
                                Json json = subsonicClient.json;
                                json.getSerializersModule();
                                SubsonicBody subsonicResponse = ((SubsonicResponse) json.decodeFromString(SubsonicResponse.INSTANCE.serializer(), strString)).getSubsonicResponse();
                                if (!Intrinsics.areEqual(subsonicResponse.getStatus(), "ok")) {
                                    SubsonicError error = subsonicResponse.getError();
                                    String str2 = "The server rejected the request";
                                    if (error != null && (message = error.getMessage()) != null) {
                                        String str3 = message;
                                        if (StringsKt.isBlank(str3)) {
                                            str3 = "The server rejected the request";
                                        }
                                        String str4 = str3;
                                        if (str4 != null) {
                                            str2 = str4;
                                        }
                                    }
                                    SubsonicError error2 = subsonicResponse.getError();
                                    throw new ApiException(str2, error2 != null ? error2.getCode() : 0L);
                                }
                                CloseableKt.closeFinally(responseExecute, null);
                                return subsonicResponse;
                            } catch (Exception e) {
                                throw new NetworkException("The server returned an invalid response", e);
                            }
                        }
                        throw new NetworkException("Server error " + response.code(), null, 2, null);
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(responseExecute, th);
                            throw th2;
                        }
                    }
                } catch (ApiException e2) {
                    throw e2;
                } catch (NetworkException e3) {
                    networkException = e3;
                    if (i < 4) {
                        this.L$0 = str;
                        this.L$1 = networkException;
                        this.I$0 = i;
                        this.label = 1;
                        if (DelayKt.delay(this.this$0.backoff(i), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        continue;
                    }
                } catch (Exception e4) {
                    String message2 = e4.getMessage();
                    if (message2 == null) {
                        message2 = "network error";
                    }
                    networkException = new NetworkException("Could not reach the server: " + message2, e4);
                    if (i < 4) {
                        this.L$0 = str;
                        this.L$1 = networkException;
                        this.I$0 = i;
                        this.label = 2;
                        if (DelayKt.delay(this.this$0.backoff(i), this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        continue;
                    }
                }
            }
            throw networkException;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object get(String str, Map<String, String> map, ServerConfig serverConfig, Continuation<? super SubsonicBody> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(serverConfig, this, str, map, null), continuation);
    }

    static /* synthetic */ String buildUrl$default(SubsonicClient subsonicClient, String str, String str2, Map map, ServerConfig serverConfig, boolean z, int i, Object obj) {
        if ((i & 16) != 0) {
            z = true;
        }
        return subsonicClient.buildUrl(str, str2, map, serverConfig, z);
    }

    private final String buildUrl(String base, String method, Map<String, String> params, ServerConfig server, boolean withFormat) throws NoSuchAlgorithmException {
        Companion companion = INSTANCE;
        String strRandomBase36 = companion.randomBase36(10);
        String strMd5Hex = companion.md5Hex(server.getPassword() + strRandomBase36);
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.add(TuplesKt.to("u", server.getUsername()));
        listCreateListBuilder.add(TuplesKt.to(CmcdData.Factory.STREAMING_FORMAT_SS, strRandomBase36));
        listCreateListBuilder.add(TuplesKt.to("t", strMd5Hex));
        listCreateListBuilder.add(TuplesKt.to("v", "1.16.1"));
        listCreateListBuilder.add(TuplesKt.to("c", "dubtonic"));
        if (withFormat) {
            listCreateListBuilder.add(TuplesKt.to("f", "json"));
        }
        List listBuild = CollectionsKt.build(listCreateListBuilder);
        HttpUrl httpUrl = HttpUrl.INSTANCE.parse(base + "/rest/" + method);
        Intrinsics.checkNotNull(httpUrl);
        HttpUrl.Builder builderNewBuilder = httpUrl.newBuilder();
        for (Pair pair : CollectionsKt.plus((Collection) listBuild, (Iterable) MapsKt.toList(params))) {
            builderNewBuilder.addQueryParameter((String) pair.component1(), (String) pair.component2());
        }
        return builderNewBuilder.build().getUrl();
    }

    /* JADX INFO: compiled from: SubsonicClient.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005J\u000e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/dubtonic/player/data/api/SubsonicClient$Companion;", "", "<init>", "()V", "md5Hex", "", "input", "randomBase36", "length", "", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String md5Hex(String input) throws NoSuchAlgorithmException {
            Intrinsics.checkNotNullParameter(input, "input");
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = input.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            byte[] bArrDigest = messageDigest.digest(bytes);
            Intrinsics.checkNotNull(bArrDigest);
            return ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: com.dubtonic.player.data.api.SubsonicClient$Companion$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SubsonicClient.Companion.md5Hex$lambda$0(((Byte) obj).byteValue());
                }
            }, 30, (Object) null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CharSequence md5Hex$lambda$0(byte b) {
            String str = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            return str;
        }

        public final String randomBase36(int length) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                sb.append(StringsKt.random("0123456789abcdefghijklmnopqrstuvwxyz", Random.INSTANCE));
            }
            String string = sb.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            return string;
        }
    }
}
