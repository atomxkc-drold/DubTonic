package com.dubtonic.player.ui.screens;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.rounded.PlayArrowKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import com.dubtonic.player.ui.components.ComponentsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ArtistScreen.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class ComposableSingletons$ArtistScreenKt {
    public static final ComposableSingletons$ArtistScreenKt INSTANCE = new ComposableSingletons$ArtistScreenKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f70lambda1 = ComposableLambdaKt.composableLambdaInstance(967821418, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.screens.ComposableSingletons$ArtistScreenKt$lambda-1$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            if ((i & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(967821418, i, -1, "com.dubtonic.player.ui.screens.ComposableSingletons$ArtistScreenKt.lambda-1.<anonymous> (ArtistScreen.kt:130)");
            }
            ComponentsKt.SectionHeader("Albums", null, null, composer, 6, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-2, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f71lambda2 = ComposableLambdaKt.composableLambdaInstance(-185274381, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.screens.ComposableSingletons$ArtistScreenKt$lambda-2$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            if ((i & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-185274381, i, -1, "com.dubtonic.player.ui.screens.ComposableSingletons$ArtistScreenKt.lambda-2.<anonymous> (ArtistScreen.kt:148)");
            }
            ComponentsKt.EmptyState(PlayArrowKt.getPlayArrow(Icons.Rounded.INSTANCE), "Nothing to show yet", "This artist has no albums.", null, null, null, composer, 432, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-3, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f72lambda3 = ComposableLambdaKt.composableLambdaInstance(-55851706, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.screens.ComposableSingletons$ArtistScreenKt$lambda-3$1
        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
            invoke(lazyItemScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(LazyItemScope item, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(item, "$this$item");
            if ((i & 17) == 16 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-55851706, i, -1, "com.dubtonic.player.ui.screens.ComposableSingletons$ArtistScreenKt.lambda-3.<anonymous> (ArtistScreen.kt:152)");
            }
            ComponentsKt.SectionHeader("Top songs", null, null, composer, 6, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: getLambda-1$app_release, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m7053getLambda1$app_release() {
        return f70lambda1;
    }

    /* JADX INFO: renamed from: getLambda-2$app_release, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m7054getLambda2$app_release() {
        return f71lambda2;
    }

    /* JADX INFO: renamed from: getLambda-3$app_release, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m7055getLambda3$app_release() {
        return f72lambda3;
    }
}
