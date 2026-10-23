package com.dubtonic.player.ui.screens;

import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.profileinstaller.ProfileVerifier;
import com.dubtonic.player.data.LibraryRepository;
import com.dubtonic.player.data.LoadState;
import com.dubtonic.player.data.api.ServerConfig;
import com.dubtonic.player.data.model.Album;
import com.dubtonic.player.data.model.Artist;
import com.dubtonic.player.data.model.Song;
import com.dubtonic.player.di.AppContainer;
import com.dubtonic.player.player.PlayerBus;
import com.dubtonic.player.player.PlayerUiState;
import com.dubtonic.player.player.QueueItem;
import com.dubtonic.player.ui.components.ComponentsKt;
import com.dubtonic.player.ui.theme.DubColors;
import com.dubtonic.player.ui.theme.DubTheme;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LibraryScreen.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\u001ag\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0007¢\u0006\u0002\u0010\f\u001aE\u0010\r\u001a\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0007H\u0003¢\u0006\u0002\u0010\u0012¨\u0006\u0013²\u0006\u0016\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\u0014X\u008a\u0084\u0002²\u0006\u0016\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u000f0\u0014X\u008a\u0084\u0002²\u0006\u0016\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u000f0\u0014X\u008a\u0084\u0002²\u0006\u0010\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u001aX\u008a\u0084\u0002²\u0006\u0010\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u001aX\u008a\u0084\u0002²\u0006\n\u0010\u001c\u001a\u00020\u001dX\u008a\u0084\u0002"}, d2 = {"LibraryScreen", "", TtmlNode.RUBY_CONTAINER, "Lcom/dubtonic/player/di/AppContainer;", "tab", "", "onTabChange", "Lkotlin/Function1;", "onOpenAlbum", "onOpenArtist", "onOpenPlayer", "Lkotlin/Function0;", "(Lcom/dubtonic/player/di/AppContainer;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "AlbumGrid", "albums", "", "Lcom/dubtonic/player/data/model/Album;", "artUrl", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "app_release", "Lcom/dubtonic/player/data/LoadState;", "artists", "Lcom/dubtonic/player/data/model/Artist;", "songs", "Lcom/dubtonic/player/data/model/Song;", "hidden", "", "starred", "playerState", "Lcom/dubtonic/player/player/PlayerUiState;"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LibraryScreenKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AlbumGrid$lambda$28(List list, Function1 function1, Function1 function2, int i, Composer composer, int i2) {
        AlbumGrid(list, function1, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LibraryScreen$lambda$21(AppContainer appContainer, String str, Function1 function1, Function1 function2, Function1 function3, Function0 function0, int i, Composer composer, int i2) {
        LibraryScreen(appContainer, str, function1, function2, function3, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void LibraryScreen(final AppContainer container, final String tab, final Function1<? super String, Unit> onTabChange, final Function1<? super String, Unit> onOpenAlbum, final Function1<? super String, Unit> onOpenArtist, final Function0<Unit> onOpenPlayer, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(tab, "tab");
        Intrinsics.checkNotNullParameter(onTabChange, "onTabChange");
        Intrinsics.checkNotNullParameter(onOpenAlbum, "onOpenAlbum");
        Intrinsics.checkNotNullParameter(onOpenArtist, "onOpenArtist");
        Intrinsics.checkNotNullParameter(onOpenPlayer, "onOpenPlayer");
        Composer composerStartRestartGroup = composer.startRestartGroup(1914609771);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(container) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(tab) ? 32 : 16;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onTabChange) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onOpenAlbum) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onOpenArtist) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onOpenPlayer) ? 131072 : 65536;
        }
        int i3 = i2;
        if ((74899 & i3) != 74898 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1914609771, i3, -1, "com.dubtonic.player.ui.screens.LibraryScreen (LibraryScreen.kt:53)");
            }
            final LibraryRepository library = container.getLibrary();
            final DubColors colors = DubTheme.INSTANCE.getColors(composerStartRestartGroup, 6);
            final State stateCollectAsState = SnapshotStateKt.collectAsState(library.getAlbums(), null, composerStartRestartGroup, 0, 1);
            final State stateCollectAsState2 = SnapshotStateKt.collectAsState(library.getArtists(), null, composerStartRestartGroup, 0, 1);
            final State stateCollectAsState3 = SnapshotStateKt.collectAsState(library.getSongs(), null, composerStartRestartGroup, 0, 1);
            final State stateCollectAsState4 = SnapshotStateKt.collectAsState(container.getSettings().getHiddenFlow(), SetsKt.emptySet(), null, composerStartRestartGroup, 48, 2);
            final State stateCollectAsState5 = SnapshotStateKt.collectAsState(container.getSettings().getStarredFlow(), SetsKt.emptySet(), null, composerStartRestartGroup, 48, 2);
            final State stateCollectAsState6 = SnapshotStateKt.collectAsState(PlayerBus.INSTANCE.getState(), null, composerStartRestartGroup, 0, 1);
            final ServerConfig serverNow = container.getSettings().getServerNow();
            final boolean zIsDemo = library.isDemo();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                objRememberedValue = compositionScopedCoroutineScopeCanceller;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
            PaddingValues paddingValuesM678PaddingValuesa9UjIt4$default = PaddingKt.m678PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, Dp.m6299constructorimpl(24), 7, null);
            composerStartRestartGroup.startReplaceGroup(-2024854331);
            boolean zChanged = ((i3 & 7168) == 2048) | composerStartRestartGroup.changed(colors) | ((i3 & 112) == 32) | ((i3 & 896) == 256) | composerStartRestartGroup.changed(stateCollectAsState) | composerStartRestartGroup.changed(serverNow) | composerStartRestartGroup.changedInstance(library) | composerStartRestartGroup.changed(stateCollectAsState2) | ((57344 & i3) == 16384) | composerStartRestartGroup.changed(stateCollectAsState3) | composerStartRestartGroup.changed(stateCollectAsState4) | composerStartRestartGroup.changed(stateCollectAsState6) | composerStartRestartGroup.changed(stateCollectAsState5) | composerStartRestartGroup.changed(zIsDemo) | ((i3 & 458752) == 131072) | composerStartRestartGroup.changedInstance(coroutineScope) | composerStartRestartGroup.changedInstance(container);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: com.dubtonic.player.ui.screens.LibraryScreenKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return LibraryScreenKt.LibraryScreen$lambda$20$lambda$19(tab, colors, onTabChange, stateCollectAsState, serverNow, library, onOpenAlbum, stateCollectAsState2, onOpenArtist, stateCollectAsState3, stateCollectAsState4, zIsDemo, onOpenPlayer, coroutineScope, container, stateCollectAsState5, stateCollectAsState6, (LazyListScope) obj);
                    }
                };
                composer2 = composerStartRestartGroup;
                composer2.updateRememberedValue(objRememberedValue2);
            } else {
                composer2 = composerStartRestartGroup;
            }
            composer2.endReplaceGroup();
            LazyDslKt.LazyColumn(modifierFillMaxSize$default, null, paddingValuesM678PaddingValuesa9UjIt4$default, false, null, null, null, false, (Function1) objRememberedValue2, composer2, 390, 250);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.dubtonic.player.ui.screens.LibraryScreenKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LibraryScreenKt.LibraryScreen$lambda$21(container, tab, onTabChange, onOpenAlbum, onOpenArtist, onOpenPlayer, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String LibraryScreen$artUrl(ServerConfig serverConfig, LibraryRepository libraryRepository, String str) {
        if (serverConfig != null) {
            return LibraryRepository.coverArtUrl$default(libraryRepository, serverConfig, str, 0, 4, null);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LibraryScreen$lambda$20$lambda$19(final String str, final DubColors dubColors, final Function1 function1, State state, final ServerConfig serverConfig, final LibraryRepository libraryRepository, final Function1 function2, State state2, final Function1 function3, State state3, State state4, final boolean z, final Function0 function0, final CoroutineScope coroutineScope, final AppContainer appContainer, final State state5, final State state6, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(1643863039, true, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.screens.LibraryScreenKt$LibraryScreen$1$1$1
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
                invoke(lazyItemScope, composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(LazyItemScope item, Composer composer, int i) {
                Intrinsics.checkNotNullParameter(item, "$this$item");
                if ((i & 17) != 16 || !composer.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1643863039, i, -1, "com.dubtonic.player.ui.screens.LibraryScreen.<anonymous>.<anonymous>.<anonymous> (LibraryScreen.kt:73)");
                    }
                    Modifier modifierM682paddingVpY3zN4 = PaddingKt.m682paddingVpY3zN4(Modifier.INSTANCE, Dp.m6299constructorimpl(18), Dp.m6299constructorimpl(8));
                    DubColors dubColors2 = dubColors;
                    String str2 = str;
                    Function1<String, Unit> function4 = function1;
                    ComposerKt.sourceInformationMarkerStart(composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, 0);
                    ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierM682paddingVpY3zN4);
                    Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composer.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composer.startReusableNode();
                    if (composer.getInserting()) {
                        composer.createNode(constructor);
                    } else {
                        composer.useNode();
                    }
                    Composer composerM3333constructorimpl = Updater.m3333constructorimpl(composer);
                    Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (composerM3333constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                    TextKt.m2373Text4IGK_g("Library", (Modifier) null, dubColors2.m7164getText0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getDisplaySmall(), composer, 6, 0, 65530);
                    TextKt.m2373Text4IGK_g("Everything on your server, one scroll away.", (Modifier) null, dubColors2.m7166getTextSoft0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getBodyLarge(), composer, 6, 0, 65530);
                    SpacerKt.Spacer(PaddingKt.m685paddingqDBjuR0$default(Modifier.INSTANCE, 0.0f, Dp.m6299constructorimpl(12), 0.0f, 0.0f, 13, null), composer, 6);
                    ComponentsKt.SegmentedControl(CollectionsKt.listOf((Object[]) new String[]{"Albums", "Artists", "Songs"}), str2, function4, null, composer, 6, 8);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    composer.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    ComposerKt.sourceInformationMarkerEnd(composer);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer.skipToGroupEnd();
            }
        }), 3, null);
        int iHashCode = str.hashCode();
        if (iHashCode != -1415163932) {
            if (iHashCode != -732362228) {
                if (iHashCode == 109620734 && str.equals("songs")) {
                    final LoadState<List<Song>> loadStateLibraryScreen$lambda$2 = LibraryScreen$lambda$2(state3);
                    if (loadStateLibraryScreen$lambda$2 instanceof LoadState.Loading) {
                        LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$LibraryScreenKt.INSTANCE.m7063getLambda5$app_release(), 3, null);
                    } else if (loadStateLibraryScreen$lambda$2 instanceof LoadState.Failed) {
                        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(1528447200, true, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.screens.LibraryScreenKt$LibraryScreen$1$1$7
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
                                    ComposerKt.traceEventStart(1528447200, i, -1, "com.dubtonic.player.ui.screens.LibraryScreen.<anonymous>.<anonymous>.<anonymous> (LibraryScreen.kt:154)");
                                }
                                ComponentsKt.ErrorBanner(((LoadState.Failed) loadStateLibraryScreen$lambda$2).getMessage(), null, null, composer, 0, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }), 3, null);
                    } else if (loadStateLibraryScreen$lambda$2 instanceof LoadState.Ready) {
                        Iterable iterable = (Iterable) ((LoadState.Ready) loadStateLibraryScreen$lambda$2).getValue();
                        ArrayList arrayList = new ArrayList();
                        for (Object obj : iterable) {
                            if (!LibraryScreen$lambda$3(state4).contains(((Song) obj).getId())) {
                                arrayList.add(obj);
                            }
                        }
                        final ArrayList arrayList2 = arrayList;
                        if (arrayList2.isEmpty()) {
                            LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$LibraryScreenKt.INSTANCE.m7064getLambda6$app_release(), 3, null);
                        } else {
                            final Function1 function4 = new Function1() { // from class: com.dubtonic.player.ui.screens.LibraryScreenKt$$ExternalSyntheticLambda5
                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    return LibraryScreenKt.LibraryScreen$lambda$20$lambda$19$lambda$14((Song) obj2);
                                }
                            };
                            final LibraryScreenKt$LibraryScreen$lambda$20$lambda$19$$inlined$items$default$5 libraryScreenKt$LibraryScreen$lambda$20$lambda$19$$inlined$items$default$5 = new Function1() { // from class: com.dubtonic.player.ui.screens.LibraryScreenKt$LibraryScreen$lambda$20$lambda$19$$inlined$items$default$5
                                @Override // kotlin.jvm.functions.Function1
                                public final Void invoke(Song song) {
                                    return null;
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                                    return invoke((Song) obj2);
                                }
                            };
                            LazyColumn.items(arrayList2.size(), new Function1<Integer, Object>() { // from class: com.dubtonic.player.ui.screens.LibraryScreenKt$LibraryScreen$lambda$20$lambda$19$$inlined$items$default$6
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                                    return invoke(num.intValue());
                                }

                                public final Object invoke(int i) {
                                    return function4.invoke(arrayList2.get(i));
                                }
                            }, new Function1<Integer, Object>() { // from class: com.dubtonic.player.ui.screens.LibraryScreenKt$LibraryScreen$lambda$20$lambda$19$$inlined$items$default$7
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                                    return invoke(num.intValue());
                                }

                                public final Object invoke(int i) {
                                    return libraryScreenKt$LibraryScreen$lambda$20$lambda$19$$inlined$items$default$5.invoke(arrayList2.get(i));
                                }
                            }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.screens.LibraryScreenKt$LibraryScreen$lambda$20$lambda$19$$inlined$items$default$8
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(4);
                                }

                                @Override // kotlin.jvm.functions.Function4
                                public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                                    invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(LazyItemScope lazyItemScope, int i, Composer composer, int i2) {
                                    int i3;
                                    Song song;
                                    ComposerKt.sourceInformation(composer, "C152@7074L22:LazyDsl.kt#428nma");
                                    if ((i2 & 6) == 0) {
                                        i3 = i2 | (composer.changed(lazyItemScope) ? 4 : 2);
                                    } else {
                                        i3 = i2;
                                    }
                                    if ((i2 & 48) == 0) {
                                        i3 |= composer.changed(i) ? 32 : 16;
                                    }
                                    if ((i3 & 147) == 146 && composer.getSkipping()) {
                                        composer.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-632812321, i3, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:152)");
                                    }
                                    final Song song2 = (Song) arrayList2.get(i);
                                    composer.startReplaceGroup(206655858);
                                    String title = song2.getTitle();
                                    String artist = song2.getArtist();
                                    String strLibraryScreen$artUrl = LibraryScreenKt.LibraryScreen$artUrl(serverConfig, libraryRepository, song2.getArtworkKey());
                                    long duration = song2.getDuration();
                                    Long lValueOf = Long.valueOf(song2.getTrack());
                                    String id = null;
                                    if (lValueOf.longValue() <= 0) {
                                        lValueOf = null;
                                    }
                                    String string = lValueOf != null ? lValueOf.toString() : null;
                                    QueueItem current = LibraryScreenKt.LibraryScreen$lambda$5(state6).getCurrent();
                                    if (current != null && (song = current.getSong()) != null) {
                                        id = song.getId();
                                    }
                                    boolean zAreEqual = Intrinsics.areEqual(id, song2.getId());
                                    boolean playing = LibraryScreenKt.LibraryScreen$lambda$5(state6).getPlaying();
                                    boolean zContains = LibraryScreenKt.LibraryScreen$lambda$4(state5).contains(song2.getId());
                                    composer.startReplaceGroup(-963145813);
                                    boolean zChangedInstance = composer.changedInstance(arrayList2) | composer.changed(song2) | composer.changed(z) | composer.changed(function0);
                                    Object objRememberedValue = composer.rememberedValue();
                                    if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                        final List list = arrayList2;
                                        final boolean z2 = z;
                                        final Function0 function5 = function0;
                                        objRememberedValue = (Function0) new Function0<Unit>() { // from class: com.dubtonic.player.ui.screens.LibraryScreenKt$LibraryScreen$1$1$9$2$1
                                            @Override // kotlin.jvm.functions.Function0
                                            public /* bridge */ /* synthetic */ Unit invoke() {
                                                invoke2();
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2() {
                                                PlayerBus playerBus = PlayerBus.INSTANCE;
                                                List<Song> list2 = list;
                                                playerBus.playQueue(list2, list2.indexOf(song2), z2);
                                                function5.invoke();
                                            }
                                        };
                                        composer.updateRememberedValue(objRememberedValue);
                                    }
                                    Function0 function6 = (Function0) objRememberedValue;
                                    composer.endReplaceGroup();
                                    composer.startReplaceGroup(-963138009);
                                    boolean zChangedInstance2 = composer.changedInstance(coroutineScope) | composer.changedInstance(appContainer) | composer.changed(song2) | composer.changed(state5);
                                    Object objRememberedValue2 = composer.rememberedValue();
                                    if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                        final CoroutineScope coroutineScope2 = coroutineScope;
                                        final AppContainer appContainer2 = appContainer;
                                        final State state7 = state5;
                                        objRememberedValue2 = (Function0) new Function0<Unit>() { // from class: com.dubtonic.player.ui.screens.LibraryScreenKt$LibraryScreen$1$1$9$3$1

                                            /* JADX INFO: renamed from: com.dubtonic.player.ui.screens.LibraryScreenKt$LibraryScreen$1$1$9$3$1$1, reason: invalid class name */
                                            /* JADX INFO: compiled from: LibraryScreen.kt */
                                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                                            @DebugMetadata(c = "com.dubtonic.player.ui.screens.LibraryScreenKt$LibraryScreen$1$1$9$3$1$1", f = "LibraryScreen.kt", i = {}, l = {176}, m = "invokeSuspend", n = {}, s = {})
                                            static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                                                final /* synthetic */ AppContainer $container;
                                                final /* synthetic */ Song $song;
                                                final /* synthetic */ State<Set<String>> $starred$delegate;
                                                int label;

                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                /* JADX WARN: Multi-variable type inference failed */
                                                AnonymousClass1(AppContainer appContainer, Song song, State<? extends Set<String>> state, Continuation<? super AnonymousClass1> continuation) {
                                                    super(2, continuation);
                                                    this.$container = appContainer;
                                                    this.$song = song;
                                                    this.$starred$delegate = state;
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                                                    return new AnonymousClass1(this.$container, this.$song, this.$starred$delegate, continuation);
                                                }

                                                @Override // kotlin.jvm.functions.Function2
                                                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                                                    return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                                                }

                                                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                public final Object invokeSuspend(Object obj) {
                                                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                    int i = this.label;
                                                    if (i == 0) {
                                                        ResultKt.throwOnFailure(obj);
                                                        this.label = 1;
                                                        if (this.$container.getSettings().setStarred(this.$song.getId(), !LibraryScreenKt.LibraryScreen$lambda$4(this.$starred$delegate).contains(this.$song.getId()), this) == coroutine_suspended) {
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
                                            }

                                            @Override // kotlin.jvm.functions.Function0
                                            public /* bridge */ /* synthetic */ Unit invoke() {
                                                invoke2();
                                                return Unit.INSTANCE;
                                            }

                                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                            public final void invoke2() {
                                                BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(appContainer2, song2, state7, null), 3, null);
                                            }
                                        };
                                        composer.updateRememberedValue(objRememberedValue2);
                                    }
                                    composer.endReplaceGroup();
                                    ComponentsKt.TrackRow(title, artist, strLibraryScreen$artUrl, duration, string, zAreEqual, playing, zContains, function6, (Function0) objRememberedValue2, null, null, composer, 0, 0, 3072);
                                    composer.endReplaceGroup();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }
                            }));
                        }
                    }
                }
            } else if (str.equals("artists")) {
                final LoadState<List<Artist>> loadStateLibraryScreen$lambda$1 = LibraryScreen$lambda$1(state2);
                if (loadStateLibraryScreen$lambda$1 instanceof LoadState.Loading) {
                    LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$LibraryScreenKt.INSTANCE.m7061getLambda3$app_release(), 3, null);
                } else if (loadStateLibraryScreen$lambda$1 instanceof LoadState.Failed) {
                    LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(1962410881, true, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.screens.LibraryScreenKt$LibraryScreen$1$1$4
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
                                ComposerKt.traceEventStart(1962410881, i, -1, "com.dubtonic.player.ui.screens.LibraryScreen.<anonymous>.<anonymous>.<anonymous> (LibraryScreen.kt:114)");
                            }
                            ComponentsKt.ErrorBanner(((LoadState.Failed) loadStateLibraryScreen$lambda$1).getMessage(), null, null, composer, 0, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }), 3, null);
                } else if (loadStateLibraryScreen$lambda$1 instanceof LoadState.Ready) {
                    LoadState.Ready ready = (LoadState.Ready) loadStateLibraryScreen$lambda$1;
                    if (((List) ready.getValue()).isEmpty()) {
                        LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$LibraryScreenKt.INSTANCE.m7062getLambda4$app_release(), 3, null);
                    } else {
                        final List list = (List) ready.getValue();
                        final Function1 function5 = new Function1() { // from class: com.dubtonic.player.ui.screens.LibraryScreenKt$$ExternalSyntheticLambda4
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                return LibraryScreenKt.LibraryScreen$lambda$20$lambda$19$lambda$7((Artist) obj2);
                            }
                        };
                        final LibraryScreenKt$LibraryScreen$lambda$20$lambda$19$$inlined$items$default$1 libraryScreenKt$LibraryScreen$lambda$20$lambda$19$$inlined$items$default$1 = new Function1() { // from class: com.dubtonic.player.ui.screens.LibraryScreenKt$LibraryScreen$lambda$20$lambda$19$$inlined$items$default$1
                            @Override // kotlin.jvm.functions.Function1
                            public final Void invoke(Artist artist) {
                                return null;
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                                return invoke((Artist) obj2);
                            }
                        };
                        LazyColumn.items(list.size(), new Function1<Integer, Object>() { // from class: com.dubtonic.player.ui.screens.LibraryScreenKt$LibraryScreen$lambda$20$lambda$19$$inlined$items$default$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                                return invoke(num.intValue());
                            }

                            public final Object invoke(int i) {
                                return function5.invoke(list.get(i));
                            }
                        }, new Function1<Integer, Object>() { // from class: com.dubtonic.player.ui.screens.LibraryScreenKt$LibraryScreen$lambda$20$lambda$19$$inlined$items$default$3
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                                return invoke(num.intValue());
                            }

                            public final Object invoke(int i) {
                                return libraryScreenKt$LibraryScreen$lambda$20$lambda$19$$inlined$items$default$1.invoke(list.get(i));
                            }
                        }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.screens.LibraryScreenKt$LibraryScreen$lambda$20$lambda$19$$inlined$items$default$4
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(4);
                            }

                            @Override // kotlin.jvm.functions.Function4
                            public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                                invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(LazyItemScope lazyItemScope, int i, Composer composer, int i2) {
                                int i3;
                                ComposerKt.sourceInformation(composer, "C152@7074L22:LazyDsl.kt#428nma");
                                if ((i2 & 6) == 0) {
                                    i3 = i2 | (composer.changed(lazyItemScope) ? 4 : 2);
                                } else {
                                    i3 = i2;
                                }
                                if ((i2 & 48) == 0) {
                                    i3 |= composer.changed(i) ? 32 : 16;
                                }
                                if ((i3 & 147) == 146 && composer.getSkipping()) {
                                    composer.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-632812321, i3, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:152)");
                                }
                                final Artist artist = (Artist) list.get(i);
                                composer.startReplaceGroup(204407087);
                                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                                composer.startReplaceGroup(-963233978);
                                boolean zChanged = composer.changed(function3) | composer.changed(artist);
                                Object objRememberedValue = composer.rememberedValue();
                                if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    final Function1 function6 = function3;
                                    objRememberedValue = (Function0) new Function0<Unit>() { // from class: com.dubtonic.player.ui.screens.LibraryScreenKt$LibraryScreen$1$1$6$1$1
                                        @Override // kotlin.jvm.functions.Function0
                                        public /* bridge */ /* synthetic */ Unit invoke() {
                                            invoke2();
                                            return Unit.INSTANCE;
                                        }

                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            function6.invoke(artist.getId());
                                        }
                                    };
                                    composer.updateRememberedValue(objRememberedValue);
                                }
                                composer.endReplaceGroup();
                                Modifier modifierM682paddingVpY3zN4 = PaddingKt.m682paddingVpY3zN4(ClickableKt.m269clickableXHw0xAI$default(modifierFillMaxWidth$default, false, null, null, (Function0) objRememberedValue, 7, null), Dp.m6299constructorimpl(18), Dp.m6299constructorimpl(8));
                                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                ComposerKt.sourceInformationMarkerStart(composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composer, 48);
                                ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierM682paddingVpY3zN4);
                                Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                ComposerKt.sourceInformationMarkerStart(composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!(composer.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer.startReusableNode();
                                if (composer.getInserting()) {
                                    composer.createNode(constructor);
                                } else {
                                    composer.useNode();
                                }
                                Composer composerM3333constructorimpl = Updater.m3333constructorimpl(composer);
                                Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3333constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                ServerConfig serverConfig2 = serverConfig;
                                LibraryRepository libraryRepository2 = libraryRepository;
                                String coverArt = artist.getCoverArt();
                                if (StringsKt.isBlank(coverArt)) {
                                    coverArt = artist.getId();
                                }
                                ComponentsKt.m7042ArtworkTN_CM5M(LibraryScreenKt.LibraryScreen$artUrl(serverConfig2, libraryRepository2, coverArt), artist.getName(), Dp.m6299constructorimpl(52), null, null, composer, RendererCapabilities.DECODER_SUPPORT_MASK, 24);
                                Modifier modifierM685paddingqDBjuR0$default = PaddingKt.m685paddingqDBjuR0$default(Modifier.INSTANCE, Dp.m6299constructorimpl(12), 0.0f, 0.0f, 0.0f, 14, null);
                                ComposerKt.sourceInformationMarkerStart(composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, 0);
                                ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                                CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer, modifierM685paddingqDBjuR0$default);
                                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                ComposerKt.sourceInformationMarkerStart(composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                                if (!(composer.getApplier() instanceof Applier)) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer.startReusableNode();
                                if (composer.getInserting()) {
                                    composer.createNode(constructor2);
                                } else {
                                    composer.useNode();
                                }
                                Composer composerM3333constructorimpl2 = Updater.m3333constructorimpl(composer);
                                Updater.m3340setimpl(composerM3333constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                Updater.m3340setimpl(composerM3333constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                if (composerM3333constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                    composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                    composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                }
                                Updater.m3340setimpl(composerM3333constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                                ComposerKt.sourceInformationMarkerStart(composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                                TextKt.m2373Text4IGK_g(artist.getName(), (Modifier) null, dubColors.m7164getText0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6238getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getBodyLarge(), composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 3120, 55258);
                                TextKt.m2373Text4IGK_g(artist.getAlbumCount() + " albums", (Modifier) null, dubColors.m7166getTextSoft0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getBodySmall(), composer, 0, 0, 65530);
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                composer.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                composer.endNode();
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                ComposerKt.sourceInformationMarkerEnd(composer);
                                composer.endReplaceGroup();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }));
                    }
                }
            }
        } else if (str.equals("albums")) {
            final LoadState<List<Album>> loadStateLibraryScreen$lambda$0 = LibraryScreen$lambda$0(state);
            if (loadStateLibraryScreen$lambda$0 instanceof LoadState.Loading) {
                LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$LibraryScreenKt.INSTANCE.m7059getLambda1$app_release(), 3, null);
            } else if (loadStateLibraryScreen$lambda$0 instanceof LoadState.Failed) {
                LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1241292008, true, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.screens.LibraryScreenKt$LibraryScreen$1$1$2
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
                            ComposerKt.traceEventStart(-1241292008, i, -1, "com.dubtonic.player.ui.screens.LibraryScreen.<anonymous>.<anonymous>.<anonymous> (LibraryScreen.kt:97)");
                        }
                        ComponentsKt.ErrorBanner(((LoadState.Failed) loadStateLibraryScreen$lambda$0).getMessage(), null, null, composer, 0, 6);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }), 3, null);
            } else if (loadStateLibraryScreen$lambda$0 instanceof LoadState.Ready) {
                if (((List) ((LoadState.Ready) loadStateLibraryScreen$lambda$0).getValue()).isEmpty()) {
                    LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$LibraryScreenKt.INSTANCE.m7060getLambda2$app_release(), 3, null);
                } else {
                    LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1369956197, true, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.screens.LibraryScreenKt$LibraryScreen$1$1$3
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
                                ComposerKt.traceEventStart(-1369956197, i, -1, "com.dubtonic.player.ui.screens.LibraryScreen.<anonymous>.<anonymous>.<anonymous> (LibraryScreen.kt:103)");
                            }
                            List list2 = (List) ((LoadState.Ready) loadStateLibraryScreen$lambda$0).getValue();
                            composer.startReplaceGroup(-963263373);
                            boolean zChanged = composer.changed(serverConfig) | composer.changedInstance(libraryRepository);
                            ServerConfig serverConfig2 = serverConfig;
                            LibraryRepository libraryRepository2 = libraryRepository;
                            LibraryScreenKt$LibraryScreen$1$1$3$1$1 libraryScreenKt$LibraryScreen$1$1$3$1$1RememberedValue = composer.rememberedValue();
                            if (zChanged || libraryScreenKt$LibraryScreen$1$1$3$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                                libraryScreenKt$LibraryScreen$1$1$3$1$1RememberedValue = new LibraryScreenKt$LibraryScreen$1$1$3$1$1(serverConfig2, libraryRepository2);
                                composer.updateRememberedValue(libraryScreenKt$LibraryScreen$1$1$3$1$1RememberedValue);
                            }
                            composer.endReplaceGroup();
                            LibraryScreenKt.AlbumGrid(list2, (Function1) ((KFunction) libraryScreenKt$LibraryScreen$1$1$3$1$1RememberedValue), function2, composer, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }), 3, null);
                }
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object LibraryScreen$lambda$20$lambda$19$lambda$7(Artist it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object LibraryScreen$lambda$20$lambda$19$lambda$14(Song it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void AlbumGrid(final List<Album> list, final Function1<? super String, String> function1, final Function1<? super String, Unit> function2, Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(520283043);
        int i2 = (i & 6) == 0 ? (composerStartRestartGroup.changedInstance(list) ? 4 : 2) | i : i;
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        int i3 = i2;
        if ((i3 & 147) != 146 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(520283043, i3, -1, "com.dubtonic.player.ui.screens.AlbumGrid (LibraryScreen.kt:193)");
            }
            float f = 0.0f;
            Modifier modifierM683paddingVpY3zN4$default = PaddingKt.m683paddingVpY3zN4$default(Modifier.INSTANCE, Dp.m6299constructorimpl(8), 0.0f, 2, null);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composerStartRestartGroup, 0);
            String str = "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh";
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM683paddingVpY3zN4$default);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            String str2 = "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp";
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
            Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3333constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            composerStartRestartGroup.startReplaceGroup(943393018);
            Iterator it = CollectionsKt.chunked(list, 2).iterator();
            while (it.hasNext()) {
                List<Album> list2 = (List) it.next();
                int i4 = 1;
                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, f, 1, null);
                Arrangement.HorizontalOrVertical spaceEvenly = Arrangement.INSTANCE.getSpaceEvenly();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceEvenly, Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, str);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierFillMaxWidth$default);
                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, str2);
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM3333constructorimpl2 = Updater.m3333constructorimpl(composerStartRestartGroup);
                Updater.m3340setimpl(composerM3333constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (composerM3333constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m3340setimpl(composerM3333constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                composerStartRestartGroup.startReplaceGroup(-967705664);
                for (final Album album : list2) {
                    String name = album.getName();
                    String artist = album.getArtist();
                    String strInvoke = function1.invoke(album.getArtworkKey());
                    float fM6299constructorimpl = Dp.m6299constructorimpl(132);
                    float fM6299constructorimpl2 = Dp.m6299constructorimpl(165);
                    composerStartRestartGroup.startReplaceGroup(1715038097);
                    int i5 = i3;
                    boolean zChangedInstance = ((i3 & 896) == 256) | composerStartRestartGroup.changedInstance(album);
                    Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function0() { // from class: com.dubtonic.player.ui.screens.LibraryScreenKt$$ExternalSyntheticLambda0
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return LibraryScreenKt.AlbumGrid$lambda$27$lambda$26$lambda$25$lambda$24$lambda$23$lambda$22(function2, album);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    ComponentsKt.m7044MediaCardwNmHvu0(name, artist, strInvoke, fM6299constructorimpl, null, fM6299constructorimpl2, (Function0) objRememberedValue, composerStartRestartGroup, 199680, 16);
                    i4 = 1;
                    str2 = str2;
                    str = str;
                    f = 0.0f;
                    i3 = i5;
                    it = it;
                }
                Iterator it2 = it;
                String str3 = str;
                String str4 = str2;
                float f2 = f;
                int i6 = i3;
                int i7 = i4;
                composerStartRestartGroup.endReplaceGroup();
                composerStartRestartGroup.startReplaceGroup(-967692947);
                if (list2.size() == i7) {
                    SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null), composerStartRestartGroup, 0);
                }
                composerStartRestartGroup.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                it = it2;
                str2 = str4;
                str = str3;
                f = f2;
                i3 = i6;
            }
            composerStartRestartGroup.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.dubtonic.player.ui.screens.LibraryScreenKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LibraryScreenKt.AlbumGrid$lambda$28(list, function1, function2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AlbumGrid$lambda$27$lambda$26$lambda$25$lambda$24$lambda$23$lambda$22(Function1 function1, Album album) {
        function1.invoke(album.getId());
        return Unit.INSTANCE;
    }

    private static final LoadState<List<Album>> LibraryScreen$lambda$0(State<? extends LoadState<? extends List<Album>>> state) {
        return (LoadState) state.getValue();
    }

    private static final LoadState<List<Artist>> LibraryScreen$lambda$1(State<? extends LoadState<? extends List<Artist>>> state) {
        return (LoadState) state.getValue();
    }

    private static final LoadState<List<Song>> LibraryScreen$lambda$2(State<? extends LoadState<? extends List<Song>>> state) {
        return (LoadState) state.getValue();
    }

    private static final Set<String> LibraryScreen$lambda$3(State<? extends Set<String>> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Set<String> LibraryScreen$lambda$4(State<? extends Set<String>> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PlayerUiState LibraryScreen$lambda$5(State<PlayerUiState> state) {
        return state.getValue();
    }
}
