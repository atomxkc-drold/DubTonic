package com.dubtonic.player.ui;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AppNav.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "com.dubtonic.player.ui.AppNavKt$MainScreen$1$1", f = "AppNav.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class AppNavKt$MainScreen$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MutableState<Boolean> $playerOpen$delegate;
    final /* synthetic */ State<Boolean> $statusHidden$delegate;
    final /* synthetic */ View $view;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AppNavKt$MainScreen$1$1(View view, MutableState<Boolean> mutableState, State<Boolean> state, Continuation<? super AppNavKt$MainScreen$1$1> continuation) {
        super(2, continuation);
        this.$view = view;
        this.$playerOpen$delegate = mutableState;
        this.$statusHidden$delegate = state;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AppNavKt$MainScreen$1$1(this.$view, this.$playerOpen$delegate, this.$statusHidden$delegate, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AppNavKt$MainScreen$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Context context = this.$view.getContext();
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
        WindowInsetsControllerCompat windowInsetsControllerCompat = new WindowInsetsControllerCompat(((Activity) context).getWindow(), this.$view);
        if (AppNavKt.MainScreen$lambda$6(this.$playerOpen$delegate) || AppNavKt.MainScreen$lambda$12(this.$statusHidden$delegate)) {
            windowInsetsControllerCompat.hide(WindowInsetsCompat.Type.statusBars());
        } else {
            windowInsetsControllerCompat.show(WindowInsetsCompat.Type.statusBars());
        }
        return Unit.INSTANCE;
    }
}
