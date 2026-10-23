package com.dubtonic.player.ui.theme;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import kotlin.Metadata;

/* JADX INFO: compiled from: Theme.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0003¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u00058G¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/dubtonic/player/ui/theme/DubTheme;", "", "<init>", "()V", "colors", "Lcom/dubtonic/player/ui/theme/DubColors;", "getColors", "(Landroidx/compose/runtime/Composer;I)Lcom/dubtonic/player/ui/theme/DubColors;", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DubTheme {
    public static final int $stable = 0;
    public static final DubTheme INSTANCE = new DubTheme();

    private DubTheme() {
    }

    public final DubColors getColors(Composer composer, int i) {
        composer.startReplaceGroup(-871079738);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-871079738, i, -1, "com.dubtonic.player.ui.theme.DubTheme.<get-colors> (Theme.kt:266)");
        }
        ProvidableCompositionLocal<DubColors> localDubColors = ThemeKt.getLocalDubColors();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = composer.consume(localDubColors);
        ComposerKt.sourceInformationMarkerEnd(composer);
        DubColors dubColors = (DubColors) objConsume;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return dubColors;
    }
}
