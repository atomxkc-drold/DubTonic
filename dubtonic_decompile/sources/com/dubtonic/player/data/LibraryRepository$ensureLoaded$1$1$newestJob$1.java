package com.dubtonic.player.data;

import com.dubtonic.player.data.api.ServerConfig;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
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
@DebugMetadata(c = "com.dubtonic.player.data.LibraryRepository$ensureLoaded$1$1$newestJob$1", f = "LibraryRepository.kt", i = {}, l = {118}, m = "invokeSuspend", n = {}, s = {})
final class LibraryRepository$ensureLoaded$1$1$newestJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ServerConfig $server;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ LibraryRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LibraryRepository$ensureLoaded$1$1$newestJob$1(LibraryRepository libraryRepository, ServerConfig serverConfig, Continuation<? super LibraryRepository$ensureLoaded$1$1$newestJob$1> continuation) {
        super(2, continuation);
        this.this$0 = libraryRepository;
        this.$server = serverConfig;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LibraryRepository$ensureLoaded$1$1$newestJob$1(this.this$0, this.$server, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LibraryRepository$ensureLoaded$1$1$newestJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0058  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MutableStateFlow mutableStateFlow;
        Exception e;
        MutableStateFlow mutableStateFlow2;
        String message;
        LoadState failed;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MutableStateFlow mutableStateFlow3 = this.this$0._newest;
            try {
                this.L$0 = mutableStateFlow3;
                this.L$1 = mutableStateFlow3;
                this.label = 1;
                Object objAlbumListOf = this.this$0.albumListOf(this.$server, "newest", 24, this);
                if (objAlbumListOf == coroutine_suspended) {
                    return coroutine_suspended;
                }
                mutableStateFlow2 = mutableStateFlow3;
                obj = objAlbumListOf;
                mutableStateFlow = mutableStateFlow2;
            } catch (Exception e2) {
                mutableStateFlow = mutableStateFlow3;
                e = e2;
                message = e.getMessage();
                if (message == null) {
                    message = "Could not load new albums";
                }
                failed = new LoadState.Failed(message);
                mutableStateFlow2 = mutableStateFlow;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            mutableStateFlow2 = (MutableStateFlow) this.L$1;
            mutableStateFlow = (MutableStateFlow) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Exception e3) {
                e = e3;
                message = e.getMessage();
                if (message == null) {
                    message = "Could not load new albums";
                }
                failed = new LoadState.Failed(message);
                mutableStateFlow2 = mutableStateFlow;
            }
        }
        failed = new LoadState.Ready(obj);
        mutableStateFlow2.setValue(failed);
        return Unit.INSTANCE;
    }
}
