package com.dubtonic.player.data;

import androidx.media3.extractor.ts.PsExtractor;
import com.dubtonic.player.data.api.ServerConfig;
import com.dubtonic.player.data.api.SubsonicClient;
import com.dubtonic.player.data.model.Album;
import com.dubtonic.player.data.model.AlbumList;
import com.dubtonic.player.data.model.SubsonicBody;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.MutableStateFlow;

/* JADX INFO: compiled from: LibraryRepository.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "com.dubtonic.player.data.LibraryRepository$ensureLoaded$1$1$albumsJob$1", f = "LibraryRepository.kt", i = {}, l = {108, 109}, m = "invokeSuspend", n = {}, s = {})
final class LibraryRepository$ensureLoaded$1$1$albumsJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ServerConfig $server;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ LibraryRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LibraryRepository$ensureLoaded$1$1$albumsJob$1(LibraryRepository libraryRepository, ServerConfig serverConfig, Continuation<? super LibraryRepository$ensureLoaded$1$1$albumsJob$1> continuation) {
        super(2, continuation);
        this.this$0 = libraryRepository;
        this.$server = serverConfig;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LibraryRepository$ensureLoaded$1$1$albumsJob$1(this.this$0, this.$server, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LibraryRepository$ensureLoaded$1$1$albumsJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0095 A[Catch: Exception -> 0x00a8, TryCatch #1 {Exception -> 0x00a8, blocks: (B:7:0x0016, B:29:0x008d, B:31:0x0095, B:32:0x009a, B:17:0x003c), top: B:45:0x0008 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x009a A[Catch: Exception -> 0x00a8, TRY_LEAVE, TryCatch #1 {Exception -> 0x00a8, blocks: (B:7:0x0016, B:29:0x008d, B:31:0x0095, B:32:0x009a, B:17:0x003c), top: B:45:0x0008 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v2, types: [kotlinx.coroutines.flow.MutableStateFlow] */
    /* JADX WARN: Type inference failed for: r1v6 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        LoadState failed;
        ?? r1;
        MutableStateFlow mutableStateFlow;
        MutableStateFlow mutableStateFlow2;
        MutableStateFlow mutableStateFlow3;
        MutableStateFlow mutableStateFlow4;
        List<Album> album;
        MutableStateFlow mutableStateFlow5;
        MutableStateFlow mutableStateFlow6;
        AlbumList albumList;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r2 = this.label;
        try {
            if (r2 == 0) {
                ResultKt.throwOnFailure(obj);
                MutableStateFlow mutableStateFlow7 = this.this$0._albums;
                this.L$0 = mutableStateFlow7;
                this.L$1 = mutableStateFlow7;
                this.label = 1;
                obj = SubsonicClient.getAlbumList$default(this.this$0.client, this.$server, "alphabeticalByName", PsExtractor.VIDEO_STREAM_MASK, 0, this, 8, null);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mutableStateFlow = mutableStateFlow7;
                mutableStateFlow2 = mutableStateFlow7;
            } else {
                if (r2 != 1) {
                    if (r2 != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    MutableStateFlow mutableStateFlow8 = (MutableStateFlow) this.L$1;
                    MutableStateFlow mutableStateFlow9 = (MutableStateFlow) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    mutableStateFlow3 = mutableStateFlow8;
                    mutableStateFlow4 = mutableStateFlow9;
                    albumList = ((SubsonicBody) obj).getAlbumList();
                    if (albumList != null) {
                        album = albumList.getAlbum();
                    } else {
                        album = CollectionsKt.emptyList();
                    }
                    mutableStateFlow5 = mutableStateFlow4;
                    mutableStateFlow6 = mutableStateFlow3;
                    mutableStateFlow6 = mutableStateFlow2;
                    mutableStateFlow5 = mutableStateFlow;
                    failed = new LoadState.Ready(album);
                    r1 = mutableStateFlow6;
                    r1.setValue(failed);
                    return Unit.INSTANCE;
                }
                MutableStateFlow mutableStateFlow10 = (MutableStateFlow) this.L$1;
                mutableStateFlow5 = (MutableStateFlow) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    mutableStateFlow2 = mutableStateFlow10;
                    mutableStateFlow = mutableStateFlow5;
                } catch (Exception e) {
                    e = e;
                    r2 = mutableStateFlow5;
                    String message = e.getMessage();
                    if (message == null) {
                        message = "Could not load albums";
                    }
                    failed = new LoadState.Failed(message);
                    r1 = r2;
                }
            }
            AlbumList albumList2 = ((SubsonicBody) obj).getAlbumList2();
            if (albumList2 == null || (album = albumList2.getAlbum()) == null) {
                mutableStateFlow6 = mutableStateFlow2;
                mutableStateFlow5 = mutableStateFlow;
                this.L$0 = mutableStateFlow;
                this.L$1 = mutableStateFlow2;
                this.label = 2;
                obj = SubsonicClient.getAlbumList$default(this.this$0.client, this.$server, "alphabeticalByName", PsExtractor.VIDEO_STREAM_MASK, 0, this, 8, null);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mutableStateFlow3 = mutableStateFlow2;
                mutableStateFlow4 = mutableStateFlow;
                albumList = ((SubsonicBody) obj).getAlbumList();
                if (albumList != null) {
                    album = albumList.getAlbum();
                } else {
                    album = CollectionsKt.emptyList();
                }
                mutableStateFlow5 = mutableStateFlow4;
                mutableStateFlow6 = mutableStateFlow3;
            }
            mutableStateFlow6 = mutableStateFlow2;
            mutableStateFlow5 = mutableStateFlow;
            failed = new LoadState.Ready(album);
            r1 = mutableStateFlow6;
        } catch (Exception e2) {
            e = e2;
        }
        r1.setValue(failed);
        return Unit.INSTANCE;
    }
}
