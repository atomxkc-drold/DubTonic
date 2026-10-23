package com.dubtonic.player.ui.screens;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.rounded.LibraryMusicKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import com.dubtonic.player.ui.components.ComponentsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LibraryScreen.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
public final class ComposableSingletons$LibraryScreenKt {
    public static final ComposableSingletons$LibraryScreenKt INSTANCE = new ComposableSingletons$LibraryScreenKt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f76lambda1 = ComposableLambdaKt.composableLambdaInstance(-1527139345, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.screens.ComposableSingletons$LibraryScreenKt$lambda-1$1
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
                ComposerKt.traceEventStart(-1527139345, i, -1, "com.dubtonic.player.ui.screens.ComposableSingletons$LibraryScreenKt.lambda-1.<anonymous> (LibraryScreen.kt:96)");
            }
            ComponentsKt.LoadingState(null, 6, composer, 48, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-2, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f77lambda2 = ComposableLambdaKt.composableLambdaInstance(-182576302, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.screens.ComposableSingletons$LibraryScreenKt$lambda-2$1
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
                ComposerKt.traceEventStart(-182576302, i, -1, "com.dubtonic.player.ui.screens.ComposableSingletons$LibraryScreenKt.lambda-2.<anonymous> (LibraryScreen.kt:100)");
            }
            ComponentsKt.EmptyState(LibraryMusicKt.getLibraryMusic(Icons.Rounded.INSTANCE), "No albums yet", "Your server has no albums to show.", null, null, null, composer, 432, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-3, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f78lambda3 = ComposableLambdaKt.composableLambdaInstance(2141026968, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.screens.ComposableSingletons$LibraryScreenKt$lambda-3$1
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
                ComposerKt.traceEventStart(2141026968, i, -1, "com.dubtonic.player.ui.screens.ComposableSingletons$LibraryScreenKt.lambda-3.<anonymous> (LibraryScreen.kt:113)");
            }
            ComponentsKt.LoadingState(null, 6, composer, 48, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-4, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f79lambda4 = ComposableLambdaKt.composableLambdaInstance(1480955195, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.screens.ComposableSingletons$LibraryScreenKt$lambda-4$1
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
                ComposerKt.traceEventStart(1480955195, i, -1, "com.dubtonic.player.ui.screens.ComposableSingletons$LibraryScreenKt.lambda-4.<anonymous> (LibraryScreen.kt:117)");
            }
            ComponentsKt.EmptyState(LibraryMusicKt.getLibraryMusic(Icons.Rounded.INSTANCE), "No artists", "Your server has no artists to show.", null, null, null, composer, 432, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-5, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f80lambda5 = ComposableLambdaKt.composableLambdaInstance(1707063287, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.screens.ComposableSingletons$LibraryScreenKt$lambda-5$1
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
                ComposerKt.traceEventStart(1707063287, i, -1, "com.dubtonic.player.ui.screens.ComposableSingletons$LibraryScreenKt.lambda-5.<anonymous> (LibraryScreen.kt:153)");
            }
            ComponentsKt.LoadingState(null, 8, composer, 48, 1);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: lambda-6, reason: not valid java name */
    public static Function3<LazyItemScope, Composer, Integer, Unit> f81lambda6 = ComposableLambdaKt.composableLambdaInstance(1046991514, false, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.screens.ComposableSingletons$LibraryScreenKt$lambda-6$1
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
                ComposerKt.traceEventStart(1046991514, i, -1, "com.dubtonic.player.ui.screens.ComposableSingletons$LibraryScreenKt.lambda-6.<anonymous> (LibraryScreen.kt:158)");
            }
            ComponentsKt.EmptyState(LibraryMusicKt.getLibraryMusic(Icons.Rounded.INSTANCE), "No songs", "Your server has no songs to show.", null, null, null, composer, 432, 56);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    });

    /* JADX INFO: renamed from: getLambda-1$app_release, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m7059getLambda1$app_release() {
        return f76lambda1;
    }

    /* JADX INFO: renamed from: getLambda-2$app_release, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m7060getLambda2$app_release() {
        return f77lambda2;
    }

    /* JADX INFO: renamed from: getLambda-3$app_release, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m7061getLambda3$app_release() {
        return f78lambda3;
    }

    /* JADX INFO: renamed from: getLambda-4$app_release, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m7062getLambda4$app_release() {
        return f79lambda4;
    }

    /* JADX INFO: renamed from: getLambda-5$app_release, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m7063getLambda5$app_release() {
        return f80lambda5;
    }

    /* JADX INFO: renamed from: getLambda-6$app_release, reason: not valid java name */
    public final Function3<LazyItemScope, Composer, Integer, Unit> m7064getLambda6$app_release() {
        return f81lambda6;
    }
}
