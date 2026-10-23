package com.dubtonic.player.ui.screens;

import com.dubtonic.player.ui.MainViewModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: compiled from: SettingsScreen.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* synthetic */ class SettingsScreenKt$SettingsScreen$1$1$2$1$1$2$1 extends FunctionReferenceImpl implements Function1<Boolean, Unit> {
    SettingsScreenKt$SettingsScreen$1$1$2$1$1$2$1(Object obj) {
        super(1, obj, MainViewModel.class, "setStatusHidden", "setStatusHidden(Z)V", 0);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
        invoke(bool.booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(boolean z) {
        ((MainViewModel) this.receiver).setStatusHidden(z);
    }
}
