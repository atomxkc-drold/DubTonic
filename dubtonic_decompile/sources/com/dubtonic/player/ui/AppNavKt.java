package com.dubtonic.player.ui;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import androidx.activity.compose.BackHandlerKt;
import androidx.compose.animation.AnimatedContentScope;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.DarkThemeKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.compose.FlowExtKt;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.InitializerViewModelFactoryBuilder;
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner;
import androidx.lifecycle.viewmodel.compose.ViewModelKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.navigation.NamedNavArgumentKt;
import androidx.navigation.NavArgumentBuilder;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraphBuilder;
import androidx.navigation.NavHostController;
import androidx.navigation.NavOptions;
import androidx.navigation.NavOptionsBuilder;
import androidx.navigation.NavType;
import androidx.navigation.Navigator;
import androidx.navigation.PopUpToBuilder;
import androidx.navigation.compose.NavGraphBuilderKt;
import androidx.navigation.compose.NavHostControllerKt;
import androidx.navigation.compose.NavHostKt;
import com.dubtonic.player.data.api.ServerConfig;
import com.dubtonic.player.di.AppContainer;
import com.dubtonic.player.player.PlayerBus;
import com.dubtonic.player.player.PlayerUiState;
import com.dubtonic.player.ui.components.BarsKt;
import com.dubtonic.player.ui.components.ComponentsKt;
import com.dubtonic.player.ui.components.StatusTone;
import com.dubtonic.player.ui.screens.ConnectScreenKt;
import com.dubtonic.player.ui.screens.PlayerScreenKt;
import com.dubtonic.player.ui.screens.QueueSheetKt;
import com.dubtonic.player.ui.screens.SettingsScreenKt;
import com.dubtonic.player.ui.theme.DubTheme;
import com.dubtonic.player.ui.theme.ThemeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AppNav.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001H\u0007¢\u0006\u0002\u0010\u0002\u001a\u0015\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0003¢\u0006\u0002\u0010\u0006¨\u0006\u0007²\u0006\n\u0010\b\u001a\u00020\tX\u008a\u0084\u0002²\u0006\n\u0010\n\u001a\u00020\u000bX\u008a\u0084\u0002²\u0006\n\u0010\f\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\r\u001a\u00020\u000bX\u008a\u008e\u0002²\u0006\n\u0010\u000e\u001a\u00020\u000fX\u008a\u0084\u0002²\u0006\n\u0010\u0010\u001a\u00020\u000bX\u008a\u0084\u0002²\u0006\n\u0010\u0011\u001a\u00020\u000bX\u008a\u0084\u0002²\u0006\f\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u008a\u0084\u0002²\u0006\n\u0010\u0014\u001a\u00020\u000bX\u008a\u0084\u0002²\u0006\f\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u008a\u0084\u0002"}, d2 = {"DubTonicRoot", "", "(Landroidx/compose/runtime/Composer;I)V", "MainScreen", "vm", "Lcom/dubtonic/player/ui/MainViewModel;", "(Lcom/dubtonic/player/ui/MainViewModel;Landroidx/compose/runtime/Composer;I)V", "app_release", "themeMode", "", "configured", "", "playerOpen", "queueOpen", "playerState", "Lcom/dubtonic/player/player/PlayerUiState;", "statusHidden", "demo", "server", "Lcom/dubtonic/player/data/api/ServerConfig;", "connecting", "backStackEntry", "Landroidx/navigation/NavBackStackEntry;"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AppNavKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DubTonicRoot$lambda$4(int i, Composer composer, int i2) {
        DubTonicRoot(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$49(MainViewModel mainViewModel, int i, Composer composer, int i2) {
        MainScreen(mainViewModel, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void DubTonicRoot(Composer composer, final int i) {
        CreationExtras.Empty defaultViewModelCreationExtras;
        boolean z;
        Composer composerStartRestartGroup = composer.startRestartGroup(-782130728);
        if (i != 0 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-782130728, i, -1, "com.dubtonic.player.ui.DubTonicRoot (AppNav.kt:56)");
            }
            ProvidableCompositionLocal<AppContainer> localAppContainer = LocalsKt.getLocalAppContainer();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localAppContainer);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final AppContainer appContainer = (AppContainer) objConsume;
            composerStartRestartGroup.startReplaceGroup(965414348);
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(appContainer);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.dubtonic.player.ui.AppNavKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AppNavKt.DubTonicRoot$lambda$1$lambda$0(appContainer, (CreationExtras) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Function1 function1 = (Function1) objRememberedValue;
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.startReplaceableGroup(419377738);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(viewModel)P(2,1)*124@5789L7,129@5965L288:ViewModel.kt#3tja67");
            ViewModelStoreOwner current = LocalViewModelStoreOwner.INSTANCE.getCurrent(composerStartRestartGroup, 6);
            if (current == null) {
                throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
            }
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(MainViewModel.class);
            InitializerViewModelFactoryBuilder initializerViewModelFactoryBuilder = new InitializerViewModelFactoryBuilder();
            initializerViewModelFactoryBuilder.addInitializer(Reflection.getOrCreateKotlinClass(MainViewModel.class), function1);
            ViewModelProvider.Factory factoryBuild = initializerViewModelFactoryBuilder.build();
            if (current instanceof HasDefaultViewModelProviderFactory) {
                defaultViewModelCreationExtras = ((HasDefaultViewModelProviderFactory) current).getDefaultViewModelCreationExtras();
            } else {
                defaultViewModelCreationExtras = CreationExtras.Empty.INSTANCE;
            }
            ViewModel viewModel = ViewModelKt.viewModel((KClass<ViewModel>) orCreateKotlinClass, current, (String) null, factoryBuild, defaultViewModelCreationExtras, composerStartRestartGroup, 0, 0);
            composerStartRestartGroup.endReplaceableGroup();
            MainViewModel mainViewModel = (MainViewModel) viewModel;
            State stateCollectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(mainViewModel.getThemeMode(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            boolean zIsSystemInDarkTheme = DarkThemeKt.isSystemInDarkTheme(composerStartRestartGroup, 0);
            String strDubTonicRoot$lambda$2 = DubTonicRoot$lambda$2(stateCollectAsStateWithLifecycle);
            if (Intrinsics.areEqual(strDubTonicRoot$lambda$2, "light")) {
                z = false;
            } else {
                z = Intrinsics.areEqual(strDubTonicRoot$lambda$2, "dark") ? true : zIsSystemInDarkTheme;
            }
            ThemeKt.DubTonicTheme(z, ComposableLambdaKt.rememberComposableLambda(-982734633, true, new AnonymousClass1(z, mainViewModel, FlowExtKt.collectAsStateWithLifecycle(mainViewModel.getConfigured(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7)), composerStartRestartGroup, 54), composerStartRestartGroup, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.dubtonic.player.ui.AppNavKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppNavKt.DubTonicRoot$lambda$4(i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MainViewModel DubTonicRoot$lambda$1$lambda$0(AppContainer appContainer, CreationExtras viewModel) {
        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
        return new MainViewModel(appContainer);
    }

    /* JADX INFO: renamed from: com.dubtonic.player.ui.AppNavKt$DubTonicRoot$1, reason: invalid class name */
    /* JADX INFO: compiled from: AppNav.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass1 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ State<Boolean> $configured$delegate;
        final /* synthetic */ boolean $dark;
        final /* synthetic */ MainViewModel $vm;

        AnonymousClass1(boolean z, MainViewModel mainViewModel, State<Boolean> state) {
            this.$dark = z;
            this.$vm = mainViewModel;
            this.$configured$delegate = state;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 3) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-982734633, i, -1, "com.dubtonic.player.ui.DubTonicRoot.<anonymous> (AppNav.kt:69)");
                }
                ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
                ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = composer.consume(localView);
                ComposerKt.sourceInformationMarkerEnd(composer);
                final View view = (View) objConsume;
                composer.startReplaceGroup(-1251751291);
                if (!view.isInEditMode()) {
                    composer.startReplaceGroup(-1251749782);
                    boolean zChangedInstance = composer.changedInstance(view) | composer.changed(this.$dark);
                    final boolean z = this.$dark;
                    Object objRememberedValue = composer.rememberedValue();
                    if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function0() { // from class: com.dubtonic.player.ui.AppNavKt$DubTonicRoot$1$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return AppNavKt.AnonymousClass1.invoke$lambda$2$lambda$1(view, z);
                            }
                        };
                        composer.updateRememberedValue(objRememberedValue);
                    }
                    composer.endReplaceGroup();
                    EffectsKt.SideEffect((Function0) objRememberedValue, composer, 0);
                }
                composer.endReplaceGroup();
                if (AppNavKt.DubTonicRoot$lambda$3(this.$configured$delegate)) {
                    composer.startReplaceGroup(-1251739875);
                    AppNavKt.MainScreen(this.$vm, composer, 0);
                } else {
                    composer.startReplaceGroup(-1251739232);
                    ConnectScreenKt.ConnectScreen(this.$vm, composer, 0);
                }
                composer.endReplaceGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$2$lambda$1(View view, boolean z) {
            Context context = view.getContext();
            Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
            WindowInsetsControllerCompat insetsController = WindowCompat.getInsetsController(((Activity) context).getWindow(), view);
            insetsController.setAppearanceLightStatusBars(!z);
            insetsController.setAppearanceLightNavigationBars(!z);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void MainScreen(final MainViewModel mainViewModel, Composer composer, final int i) {
        int i2;
        final MutableState mutableState;
        final MutableState mutableState2;
        Composer composer2;
        String str;
        StatusTone statusTone;
        final MutableState mutableState3;
        final MutableState mutableState4;
        final MutableState mutableState5;
        NavDestination destination;
        Composer composerStartRestartGroup = composer.startRestartGroup(-215558306);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(mainViewModel) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i2 & 3) != 2 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-215558306, i2, -1, "com.dubtonic.player.ui.MainScreen (AppNav.kt:84)");
            }
            ProvidableCompositionLocal<AppContainer> localAppContainer = LocalsKt.getLocalAppContainer();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localAppContainer);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final AppContainer appContainer = (AppContainer) objConsume;
            final NavHostController navHostControllerRememberNavController = NavHostControllerKt.rememberNavController(new Navigator[0], composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceGroup(-1457021543);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableState mutableState6 = (MutableState) objRememberedValue;
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.startReplaceGroup(-1457019751);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            MutableState mutableState7 = (MutableState) objRememberedValue2;
            composerStartRestartGroup.endReplaceGroup();
            State stateCollectAsState = SnapshotStateKt.collectAsState(PlayerBus.INSTANCE.getState(), null, composerStartRestartGroup, 0, 1);
            State stateCollectAsStateWithLifecycle = FlowExtKt.collectAsStateWithLifecycle(mainViewModel.getStatusHidden(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            State stateCollectAsStateWithLifecycle2 = FlowExtKt.collectAsStateWithLifecycle(mainViewModel.getDemo(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            State stateCollectAsStateWithLifecycle3 = FlowExtKt.collectAsStateWithLifecycle(mainViewModel.getServer(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            State stateCollectAsStateWithLifecycle4 = FlowExtKt.collectAsStateWithLifecycle(mainViewModel.getConnecting(), (LifecycleOwner) null, (Lifecycle.State) null, (CoroutineContext) null, composerStartRestartGroup, 0, 7);
            NavBackStackEntry navBackStackEntryMainScreen$lambda$16 = MainScreen$lambda$16(NavHostControllerKt.currentBackStackEntryAsState(navHostControllerRememberNavController, composerStartRestartGroup, 0));
            String route = (navBackStackEntryMainScreen$lambda$16 == null || (destination = navBackStackEntryMainScreen$lambda$16.getDestination()) == null) ? null : destination.getRoute();
            ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume2 = composerStartRestartGroup.consume(localView);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            View view = (View) objConsume2;
            Boolean boolValueOf = Boolean.valueOf(MainScreen$lambda$6(mutableState6));
            Boolean boolValueOf2 = Boolean.valueOf(MainScreen$lambda$12(stateCollectAsStateWithLifecycle));
            composerStartRestartGroup.startReplaceGroup(-1457002174);
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(view) | composerStartRestartGroup.changed(stateCollectAsStateWithLifecycle);
            AppNavKt$MainScreen$1$1 appNavKt$MainScreen$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || appNavKt$MainScreen$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                mutableState = mutableState6;
                appNavKt$MainScreen$1$1RememberedValue = new AppNavKt$MainScreen$1$1(view, mutableState, stateCollectAsStateWithLifecycle, null);
                composerStartRestartGroup.updateRememberedValue(appNavKt$MainScreen$1$1RememberedValue);
            } else {
                mutableState = mutableState6;
            }
            composerStartRestartGroup.endReplaceGroup();
            EffectsKt.LaunchedEffect(boolValueOf, boolValueOf2, (Function2) appNavKt$MainScreen$1$1RememberedValue, composerStartRestartGroup, 0);
            boolean z = MainScreen$lambda$6(mutableState) || MainScreen$lambda$9(mutableState7);
            composerStartRestartGroup.startReplaceGroup(-1456990144);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                mutableState2 = mutableState7;
                objRememberedValue3 = new Function0() { // from class: com.dubtonic.player.ui.AppNavKt$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AppNavKt.MainScreen$lambda$19$lambda$18(mutableState, mutableState2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                mutableState2 = mutableState7;
            }
            composerStartRestartGroup.endReplaceGroup();
            BackHandlerKt.BackHandler(z, (Function0) objRememberedValue3, composerStartRestartGroup, 48, 0);
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            MutableState mutableState8 = mutableState2;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxSize$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM3333constructorimpl = Updater.m3333constructorimpl(composerStartRestartGroup);
            Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3333constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComponentsKt.AmbientGlow(null, composerStartRestartGroup, 0, 1);
            Modifier modifierM685paddingqDBjuR0$default = PaddingKt.m685paddingqDBjuR0$default(WindowInsetsPadding_androidKt.statusBarsPadding(Modifier.INSTANCE), 0.0f, Dp.m6299constructorimpl(76), 0.0f, Dp.m6299constructorimpl(MainScreen$lambda$11(stateCollectAsState).getHasQueue() ? 166 : 86), 5, null);
            composerStartRestartGroup.startReplaceGroup(-534867469);
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(appContainer) | composerStartRestartGroup.changedInstance(navHostControllerRememberNavController) | composerStartRestartGroup.changedInstance(mainViewModel);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new Function1() { // from class: com.dubtonic.player.ui.AppNavKt$$ExternalSyntheticLambda15
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AppNavKt.MainScreen$lambda$48$lambda$25$lambda$24(appContainer, navHostControllerRememberNavController, mutableState, mainViewModel, (NavGraphBuilder) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            composerStartRestartGroup.endReplaceGroup();
            MutableState mutableState9 = mutableState;
            composer2 = composerStartRestartGroup;
            NavHostKt.NavHost(navHostControllerRememberNavController, "home", modifierM685paddingqDBjuR0$default, null, null, null, null, null, null, null, (Function1) objRememberedValue4, composerStartRestartGroup, 48, 0, 1016);
            if (MainScreen$lambda$15(stateCollectAsStateWithLifecycle4)) {
                str = "Connecting…";
            } else if (MainScreen$lambda$13(stateCollectAsStateWithLifecycle2)) {
                str = "Demo library";
            } else if (MainScreen$lambda$14(stateCollectAsStateWithLifecycle3) != null) {
                ServerConfig serverConfigMainScreen$lambda$14 = MainScreen$lambda$14(stateCollectAsStateWithLifecycle3);
                Intrinsics.checkNotNull(serverConfigMainScreen$lambda$14);
                String name = serverConfigMainScreen$lambda$14.getName();
                if (StringsKt.isBlank(name)) {
                    ServerConfig serverConfigMainScreen$lambda$15 = MainScreen$lambda$14(stateCollectAsStateWithLifecycle3);
                    Intrinsics.checkNotNull(serverConfigMainScreen$lambda$15);
                    name = serverConfigMainScreen$lambda$15.getUrl();
                }
                str = name;
            } else {
                str = "Offline";
            }
            if (MainScreen$lambda$15(stateCollectAsStateWithLifecycle4)) {
                statusTone = StatusTone.CONNECTING;
            } else if (MainScreen$lambda$13(stateCollectAsStateWithLifecycle2)) {
                statusTone = StatusTone.DEMO;
            } else {
                statusTone = MainScreen$lambda$14(stateCollectAsStateWithLifecycle3) != null ? StatusTone.ONLINE : StatusTone.OFFLINE;
            }
            composer2.startReplaceGroup(-534756772);
            boolean zChangedInstance3 = composer2.changedInstance(navHostControllerRememberNavController);
            Object objRememberedValue5 = composer2.rememberedValue();
            if (zChangedInstance3 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = new Function0() { // from class: com.dubtonic.player.ui.AppNavKt$$ExternalSyntheticLambda16
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AppNavKt.MainScreen$lambda$48$lambda$28$lambda$27(navHostControllerRememberNavController);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue5);
            }
            composer2.endReplaceGroup();
            float f = 0;
            BarsKt.TopBar(str, statusTone, (Function0) objRememberedValue5, PaddingKt.m685paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m6299constructorimpl(f), 0.0f, 0.0f, 13, null), null, composer2, 3072, 16);
            Modifier modifierM685paddingqDBjuR0$default2 = PaddingKt.m685paddingqDBjuR0$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), 0.0f, 0.0f, 0.0f, Dp.m6299constructorimpl(f), 7, null);
            ComposerKt.sourceInformationMarkerStart(composer2, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer2, 0);
            ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer2, modifierM685paddingqDBjuR0$default2);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer2.startReusableNode();
            if (composer2.getInserting()) {
                composer2.createNode(constructor2);
            } else {
                composer2.useNode();
            }
            Composer composerM3333constructorimpl2 = Updater.m3333constructorimpl(composer2);
            Updater.m3340setimpl(composerM3333constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3333constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m3340setimpl(composerM3333constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer2, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            BoxKt.Box(ColumnScope.weight$default(ColumnScopeInstance.INSTANCE, Modifier.INSTANCE, 1.0f, false, 2, null), composer2, 0);
            PlayerUiState playerUiStateMainScreen$lambda$11 = MainScreen$lambda$11(stateCollectAsState);
            composer2.startReplaceGroup(-813934710);
            Object objRememberedValue6 = composer2.rememberedValue();
            if (objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                mutableState3 = mutableState9;
                objRememberedValue6 = new Function0() { // from class: com.dubtonic.player.ui.AppNavKt$$ExternalSyntheticLambda17
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AppNavKt.MainScreen$lambda$48$lambda$37$lambda$30$lambda$29(mutableState3);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue6);
            } else {
                mutableState3 = mutableState9;
            }
            Function0 function0 = (Function0) objRememberedValue6;
            composer2.endReplaceGroup();
            composer2.startReplaceGroup(-813933073);
            Object objRememberedValue7 = composer2.rememberedValue();
            if (objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue7 = new Function0() { // from class: com.dubtonic.player.ui.AppNavKt$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AppNavKt.MainScreen$lambda$48$lambda$37$lambda$32$lambda$31();
                    }
                };
                composer2.updateRememberedValue(objRememberedValue7);
            }
            composer2.endReplaceGroup();
            MutableState mutableState10 = mutableState3;
            BarsKt.MiniPlayer(playerUiStateMainScreen$lambda$11, function0, (Function0) objRememberedValue7, null, composer2, 432, 8);
            String str2 = route == null ? "home" : route;
            composer2.startReplaceGroup(-813928331);
            boolean zChangedInstance4 = composer2.changedInstance(navHostControllerRememberNavController);
            Object objRememberedValue8 = composer2.rememberedValue();
            if (zChangedInstance4 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue8 = new Function1() { // from class: com.dubtonic.player.ui.AppNavKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AppNavKt.MainScreen$lambda$48$lambda$37$lambda$36$lambda$35(navHostControllerRememberNavController, (String) obj);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue8);
            }
            composer2.endReplaceGroup();
            BarsKt.BottomNav(str2, (Function1) objRememberedValue8, null, composer2, 0, 4);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.startReplaceGroup(-534730871);
            if (MainScreen$lambda$6(mutableState10)) {
                composer2.startReplaceGroup(-534728852);
                Object objRememberedValue9 = composer2.rememberedValue();
                if (objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                    mutableState5 = mutableState10;
                    objRememberedValue9 = new Function0() { // from class: com.dubtonic.player.ui.AppNavKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AppNavKt.MainScreen$lambda$48$lambda$39$lambda$38(mutableState5);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue9);
                } else {
                    mutableState5 = mutableState10;
                }
                Function0 function1 = (Function0) objRememberedValue9;
                composer2.endReplaceGroup();
                composer2.startReplaceGroup(-534727051);
                Object objRememberedValue10 = composer2.rememberedValue();
                if (objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                    mutableState4 = mutableState8;
                    objRememberedValue10 = new Function0() { // from class: com.dubtonic.player.ui.AppNavKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AppNavKt.MainScreen$lambda$48$lambda$41$lambda$40(mutableState5, mutableState4);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue10);
                } else {
                    mutableState4 = mutableState8;
                }
                composer2.endReplaceGroup();
                PlayerScreenKt.PlayerScreen(function1, (Function0) objRememberedValue10, composer2, 54);
            } else {
                mutableState4 = mutableState8;
            }
            composer2.endReplaceGroup();
            composer2.startReplaceGroup(-534722467);
            if (MainScreen$lambda$9(mutableState4)) {
                Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                ComposerKt.sourceInformationMarkerStart(composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer2, modifierFillMaxSize$default2);
                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    composer2.createNode(constructor3);
                } else {
                    composer2.useNode();
                }
                Composer composerM3333constructorimpl3 = Updater.m3333constructorimpl(composer2);
                Updater.m3340setimpl(composerM3333constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM3333constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.m3340setimpl(composerM3333constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                Modifier modifierM236backgroundbw27NRU$default = BackgroundKt.m236backgroundbw27NRU$default(SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null), DubTheme.INSTANCE.getColors(composer2, 6).m7154getScrim0d7_KjU(), null, 2, null);
                composer2.startReplaceGroup(-813902486);
                Object objRememberedValue11 = composer2.rememberedValue();
                if (objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue11 = new Function0() { // from class: com.dubtonic.player.ui.AppNavKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AppNavKt.MainScreen$lambda$48$lambda$47$lambda$43$lambda$42(mutableState4);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue11);
                }
                composer2.endReplaceGroup();
                BoxKt.Box(ClickableKt.m269clickableXHw0xAI$default(modifierM236backgroundbw27NRU$default, false, null, null, (Function0) objRememberedValue11, 7, null), composer2, 0);
                Modifier modifierM682paddingVpY3zN4 = PaddingKt.m682paddingVpY3zN4(boxScopeInstance2.align(Modifier.INSTANCE, Alignment.INSTANCE.getBottomCenter()), Dp.m6299constructorimpl(10), Dp.m6299constructorimpl(12));
                ComposerKt.sourceInformationMarkerStart(composer2, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(composer2, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                CompositionLocalMap currentCompositionLocalMap4 = composer2.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer2, modifierM682paddingVpY3zN4);
                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composer2.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer2.startReusableNode();
                if (composer2.getInserting()) {
                    composer2.createNode(constructor4);
                } else {
                    composer2.useNode();
                }
                Composer composerM3333constructorimpl4 = Updater.m3333constructorimpl(composer2);
                Updater.m3340setimpl(composerM3333constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM3333constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                    composerM3333constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                    composerM3333constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                }
                Updater.m3340setimpl(composerM3333constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composer2, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                composer2.startReplaceGroup(-118201719);
                Object objRememberedValue12 = composer2.rememberedValue();
                if (objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue12 = new Function0() { // from class: com.dubtonic.player.ui.AppNavKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return AppNavKt.MainScreen$lambda$48$lambda$47$lambda$46$lambda$45$lambda$44(mutableState4);
                        }
                    };
                    composer2.updateRememberedValue(objRememberedValue12);
                }
                composer2.endReplaceGroup();
                QueueSheetKt.QueueSheet((Function0) objRememberedValue12, composer2, 6);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
            }
            composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.dubtonic.player.ui.AppNavKt$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AppNavKt.MainScreen$lambda$49(mainViewModel, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean MainScreen$lambda$6(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void MainScreen$lambda$7(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final void MainScreen$lambda$10(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final boolean MainScreen$lambda$9(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$19$lambda$18(MutableState mutableState, MutableState mutableState2) {
        if (MainScreen$lambda$6(mutableState)) {
            MainScreen$lambda$7(mutableState, false);
        } else {
            MainScreen$lambda$10(mutableState2, false);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$48$lambda$25$lambda$24(AppContainer appContainer, NavHostController navHostController, MutableState mutableState, final MainViewModel mainViewModel, NavGraphBuilder NavHost) {
        Intrinsics.checkNotNullParameter(NavHost, "$this$NavHost");
        NavGraphBuilderKt.composable$default(NavHost, "home", null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(237895303, true, new AppNavKt$MainScreen$3$1$1$1(appContainer, navHostController, mutableState)), 254, null);
        NavGraphBuilderKt.composable$default(NavHost, "library/{tab}", CollectionsKt.listOf(NamedNavArgumentKt.navArgument("tab", new Function1() { // from class: com.dubtonic.player.ui.AppNavKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppNavKt.MainScreen$lambda$48$lambda$25$lambda$24$lambda$20((NavArgumentBuilder) obj);
            }
        })), null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(-480243074, true, new AppNavKt$MainScreen$3$1$1$3(appContainer, navHostController, mutableState)), 252, null);
        NavGraphBuilderKt.composable$default(NavHost, "album/{id}", CollectionsKt.listOf(NamedNavArgumentKt.navArgument(TtmlNode.ATTR_ID, new Function1() { // from class: com.dubtonic.player.ui.AppNavKt$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppNavKt.MainScreen$lambda$48$lambda$25$lambda$24$lambda$21((NavArgumentBuilder) obj);
            }
        })), null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(-1748913153, true, new AppNavKt$MainScreen$3$1$1$5(appContainer, navHostController, mutableState)), 252, null);
        NavGraphBuilderKt.composable$default(NavHost, "artist/{id}", CollectionsKt.listOf(NamedNavArgumentKt.navArgument(TtmlNode.ATTR_ID, new Function1() { // from class: com.dubtonic.player.ui.AppNavKt$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppNavKt.MainScreen$lambda$48$lambda$25$lambda$24$lambda$22((NavArgumentBuilder) obj);
            }
        })), null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(1277384064, true, new AppNavKt$MainScreen$3$1$1$7(appContainer, navHostController, mutableState)), 252, null);
        NavGraphBuilderKt.composable$default(NavHost, "playlist/{id}", CollectionsKt.listOf(NamedNavArgumentKt.navArgument(TtmlNode.ATTR_ID, new Function1() { // from class: com.dubtonic.player.ui.AppNavKt$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppNavKt.MainScreen$lambda$48$lambda$25$lambda$24$lambda$23((NavArgumentBuilder) obj);
            }
        })), null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(8713985, true, new AppNavKt$MainScreen$3$1$1$9(appContainer, mutableState)), 252, null);
        NavGraphBuilderKt.composable$default(NavHost, "search", null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(-1259956094, true, new AppNavKt$MainScreen$3$1$1$10(appContainer, navHostController, mutableState)), 254, null);
        NavGraphBuilderKt.composable$default(NavHost, "playlists", null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(1766341123, true, new AppNavKt$MainScreen$3$1$1$11(appContainer, navHostController, mutableState)), 254, null);
        NavGraphBuilderKt.composable$default(NavHost, "settings", null, null, null, null, null, null, null, ComposableLambdaKt.composableLambdaInstance(497671044, true, new Function4<AnimatedContentScope, NavBackStackEntry, Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.AppNavKt$MainScreen$3$1$1$12
            @Override // kotlin.jvm.functions.Function4
            public /* bridge */ /* synthetic */ Unit invoke(AnimatedContentScope animatedContentScope, NavBackStackEntry navBackStackEntry, Composer composer, Integer num) {
                invoke(animatedContentScope, navBackStackEntry, composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(AnimatedContentScope composable, NavBackStackEntry it, Composer composer, int i) {
                Intrinsics.checkNotNullParameter(composable, "$this$composable");
                Intrinsics.checkNotNullParameter(it, "it");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(497671044, i, -1, "com.dubtonic.player.ui.MainScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AppNav.kt:191)");
                }
                SettingsScreenKt.SettingsScreen(mainViewModel, composer, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }), 254, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$48$lambda$25$lambda$24$lambda$20(NavArgumentBuilder navArgument) {
        Intrinsics.checkNotNullParameter(navArgument, "$this$navArgument");
        navArgument.setType(NavType.StringType);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$48$lambda$25$lambda$24$lambda$21(NavArgumentBuilder navArgument) {
        Intrinsics.checkNotNullParameter(navArgument, "$this$navArgument");
        navArgument.setType(NavType.StringType);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$48$lambda$25$lambda$24$lambda$22(NavArgumentBuilder navArgument) {
        Intrinsics.checkNotNullParameter(navArgument, "$this$navArgument");
        navArgument.setType(NavType.StringType);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$48$lambda$25$lambda$24$lambda$23(NavArgumentBuilder navArgument) {
        Intrinsics.checkNotNullParameter(navArgument, "$this$navArgument");
        navArgument.setType(NavType.StringType);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$48$lambda$28$lambda$27(NavHostController navHostController) {
        NavController.navigate$default((NavController) navHostController, "settings", (NavOptions) null, (Navigator.Extras) null, 6, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$48$lambda$37$lambda$30$lambda$29(MutableState mutableState) {
        MainScreen$lambda$7(mutableState, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$48$lambda$37$lambda$32$lambda$31() {
        PlayerBus.INSTANCE.clearQueue();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$48$lambda$37$lambda$36$lambda$35(final NavHostController navHostController, String target) {
        Intrinsics.checkNotNullParameter(target, "target");
        navHostController.navigate(target, new Function1() { // from class: com.dubtonic.player.ui.AppNavKt$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppNavKt.MainScreen$lambda$48$lambda$37$lambda$36$lambda$35$lambda$34(navHostController, (NavOptionsBuilder) obj);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$48$lambda$37$lambda$36$lambda$35$lambda$34(NavHostController navHostController, NavOptionsBuilder navigate) {
        Intrinsics.checkNotNullParameter(navigate, "$this$navigate");
        navigate.popUpTo(navHostController.getGraph().getStartDestId(), new Function1() { // from class: com.dubtonic.player.ui.AppNavKt$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AppNavKt.MainScreen$lambda$48$lambda$37$lambda$36$lambda$35$lambda$34$lambda$33((PopUpToBuilder) obj);
            }
        });
        navigate.setLaunchSingleTop(true);
        navigate.setRestoreState(true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$48$lambda$37$lambda$36$lambda$35$lambda$34$lambda$33(PopUpToBuilder popUpTo) {
        Intrinsics.checkNotNullParameter(popUpTo, "$this$popUpTo");
        popUpTo.setSaveState(true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$48$lambda$39$lambda$38(MutableState mutableState) {
        MainScreen$lambda$7(mutableState, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$48$lambda$41$lambda$40(MutableState mutableState, MutableState mutableState2) {
        MainScreen$lambda$7(mutableState, false);
        MainScreen$lambda$10(mutableState2, true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$48$lambda$47$lambda$43$lambda$42(MutableState mutableState) {
        MainScreen$lambda$10(mutableState, false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MainScreen$lambda$48$lambda$47$lambda$46$lambda$45$lambda$44(MutableState mutableState) {
        MainScreen$lambda$10(mutableState, false);
        return Unit.INSTANCE;
    }

    private static final String DubTonicRoot$lambda$2(State<String> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean DubTonicRoot$lambda$3(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final PlayerUiState MainScreen$lambda$11(State<PlayerUiState> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean MainScreen$lambda$12(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean MainScreen$lambda$13(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final ServerConfig MainScreen$lambda$14(State<ServerConfig> state) {
        return state.getValue();
    }

    private static final boolean MainScreen$lambda$15(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final NavBackStackEntry MainScreen$lambda$16(State<NavBackStackEntry> state) {
        return state.getValue();
    }
}
