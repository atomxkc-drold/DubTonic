package com.dubtonic.player.ui;

import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import com.dubtonic.player.di.AppContainer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: Locals.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"LocalAppContainer", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Lcom/dubtonic/player/di/AppContainer;", "getLocalAppContainer", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "app_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LocalsKt {
    private static final ProvidableCompositionLocal<AppContainer> LocalAppContainer = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: com.dubtonic.player.ui.LocalsKt$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return LocalsKt.LocalAppContainer$lambda$0();
        }
    });

    public static final ProvidableCompositionLocal<AppContainer> getLocalAppContainer() {
        return LocalAppContainer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AppContainer LocalAppContainer$lambda$0() {
        throw new IllegalStateException("AppContainer not provided".toString());
    }
}
