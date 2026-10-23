package com.dubtonic.player.ui.components;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Bars.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "com.dubtonic.player.ui.components.BarsKt$MiniPlayer$4$1$1$1", f = "Bars.kt", i = {}, l = {286}, m = "invokeSuspend", n = {}, s = {})
final class BarsKt$MiniPlayer$4$1$1$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<Float> $dragOffset$delegate;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BarsKt$MiniPlayer$4$1$1$1(MutableState<Float> mutableState, Continuation<? super BarsKt$MiniPlayer$4$1$1$1> continuation) {
        super(2, continuation);
        this.$dragOffset$delegate = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        BarsKt$MiniPlayer$4$1$1$1 barsKt$MiniPlayer$4$1$1$1 = new BarsKt$MiniPlayer$4$1$1$1(this.$dragOffset$delegate, continuation);
        barsKt$MiniPlayer$4$1$1$1.L$0 = obj;
        return barsKt$MiniPlayer$4$1$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((BarsKt$MiniPlayer$4$1$1$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
            final MutableState<Float> mutableState = this.$dragOffset$delegate;
            Function0 function0 = new Function0() { // from class: com.dubtonic.player.ui.components.BarsKt$MiniPlayer$4$1$1$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return BarsKt$MiniPlayer$4$1$1$1.invokeSuspend$lambda$0(mutableState);
                }
            };
            final MutableState<Float> mutableState2 = this.$dragOffset$delegate;
            this.label = 1;
            if (DragGestureDetectorKt.detectVerticalDragGestures$default(pointerInputScope, null, function0, null, new Function2() { // from class: com.dubtonic.player.ui.components.BarsKt$MiniPlayer$4$1$1$1$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return BarsKt$MiniPlayer$4$1$1$1.invokeSuspend$lambda$1(mutableState2, (PointerInputChange) obj2, ((Float) obj3).floatValue());
                }
            }, this, 5, null) == coroutine_suspended) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$0(MutableState mutableState) {
        BarsKt.MiniPlayer$lambda$14(mutableState, 0.0f);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit invokeSuspend$lambda$1(MutableState mutableState, PointerInputChange pointerInputChange, float f) {
        if (f > 0.0f) {
            BarsKt.MiniPlayer$lambda$14(mutableState, BarsKt.MiniPlayer$lambda$13(mutableState) + f);
        }
        return Unit.INSTANCE;
    }
}
