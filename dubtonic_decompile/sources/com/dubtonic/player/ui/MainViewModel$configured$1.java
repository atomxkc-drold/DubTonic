package com.dubtonic.player.ui;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.dubtonic.player.data.api.ServerConfig;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: MainViewModel.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", "", CmcdData.Factory.STREAMING_FORMAT_SS, "Lcom/dubtonic/player/data/api/ServerConfig;", "d"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "com.dubtonic.player.ui.MainViewModel$configured$1", f = "MainViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class MainViewModel$configured$1 extends SuspendLambda implements Function3<ServerConfig, Boolean, Continuation<? super Boolean>, Object> {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;

    MainViewModel$configured$1(Continuation<? super MainViewModel$configured$1> continuation) {
        super(3, continuation);
    }

    public final Object invoke(ServerConfig serverConfig, boolean z, Continuation<? super Boolean> continuation) {
        MainViewModel$configured$1 mainViewModel$configured$1 = new MainViewModel$configured$1(continuation);
        mainViewModel$configured$1.L$0 = serverConfig;
        mainViewModel$configured$1.Z$0 = z;
        return mainViewModel$configured$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Object invoke(ServerConfig serverConfig, Boolean bool, Continuation<? super Boolean> continuation) {
        return invoke(serverConfig, bool.booleanValue(), continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ServerConfig serverConfig = (ServerConfig) this.L$0;
        boolean z = this.Z$0;
        boolean z2 = true;
        if ((serverConfig == null || !serverConfig.getConfigured()) && !z) {
            z2 = false;
        }
        return Boxing.boxBoolean(z2);
    }
}
