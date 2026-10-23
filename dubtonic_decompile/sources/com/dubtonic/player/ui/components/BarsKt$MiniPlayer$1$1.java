package com.dubtonic.player.ui.components;

import androidx.compose.runtime.MutableState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Bars.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "com.dubtonic.player.ui.components.BarsKt$MiniPlayer$1$1", f = "Bars.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class BarsKt$MiniPlayer$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ float $dismissThreshold;
    final /* synthetic */ MutableState<Float> $dragOffset$delegate;
    final /* synthetic */ Function0<Unit> $onDismiss;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BarsKt$MiniPlayer$1$1(float f, Function0<Unit> function0, MutableState<Float> mutableState, Continuation<? super BarsKt$MiniPlayer$1$1> continuation) {
        super(2, continuation);
        this.$dismissThreshold = f;
        this.$onDismiss = function0;
        this.$dragOffset$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BarsKt$MiniPlayer$1$1(this.$dismissThreshold, this.$onDismiss, this.$dragOffset$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BarsKt$MiniPlayer$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        if (BarsKt.MiniPlayer$lambda$13(this.$dragOffset$delegate) > this.$dismissThreshold) {
            BarsKt.MiniPlayer$lambda$14(this.$dragOffset$delegate, 0.0f);
            this.$onDismiss.invoke();
        }
        return Unit.INSTANCE;
    }
}
