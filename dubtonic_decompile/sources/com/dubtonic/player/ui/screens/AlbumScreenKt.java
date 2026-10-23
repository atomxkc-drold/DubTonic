package com.dubtonic.player.ui.screens;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.InitializerViewModelFactoryBuilder;
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner;
import androidx.lifecycle.viewmodel.compose.ViewModelKt;
import androidx.media3.common.C;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.dubtonic.player.data.LibraryRepository;
import com.dubtonic.player.data.LoadState;
import com.dubtonic.player.data.api.ServerConfig;
import com.dubtonic.player.data.model.Album;
import com.dubtonic.player.data.model.Song;
import com.dubtonic.player.di.AppContainer;
import com.dubtonic.player.player.PlayerBus;
import com.dubtonic.player.player.PlayerUiState;
import com.dubtonic.player.player.QueueItem;
import com.dubtonic.player.ui.components.ComponentsKt;
import com.dubtonic.player.ui.theme.DubColors;
import com.dubtonic.player.ui.theme.DubTheme;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: AlbumScreen.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u001a?\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u0010\n\u001a}\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\u000f2\u0014\u0010\u0010\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00072\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0003¢\u0006\u0002\u0010\u0019¨\u0006\u001a²\u0006\u0010\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u008a\u0084\u0002²\u0006\u0010\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012X\u008a\u0084\u0002²\u0006\n\u0010\u001e\u001a\u00020\u001fX\u008a\u0084\u0002"}, d2 = {"AlbumScreen", "", TtmlNode.RUBY_CONTAINER, "Lcom/dubtonic/player/di/AppContainer;", "albumId", "", "onOpenArtist", "Lkotlin/Function1;", "onOpenPlayer", "Lkotlin/Function0;", "(Lcom/dubtonic/player/di/AppContainer;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "SongRow", "song", "Lcom/dubtonic/player/data/model/Song;", "fullList", "", "artUrl", "starred", "", "isCurrent", "", "isPlaying", "demo", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Lcom/dubtonic/player/data/model/Song;Ljava/util/List;Lkotlin/jvm/functions/Function1;Ljava/util/Set;ZZZLkotlinx/coroutines/CoroutineScope;Lcom/dubtonic/player/di/AppContainer;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app_release", "state", "Lcom/dubtonic/player/data/LoadState;", "Lcom/dubtonic/player/data/model/Album;", "playerState", "Lcom/dubtonic/player/player/PlayerUiState;"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class AlbumScreenKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AlbumScreen$lambda$17(AppContainer appContainer, String str, Function1 function1, Function0 function0, int i, Composer composer, int i2) {
        AlbumScreen(appContainer, str, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SongRow$lambda$23(Song song, List list, Function1 function1, Set set, boolean z, boolean z2, boolean z3, CoroutineScope coroutineScope, AppContainer appContainer, Function0 function0, int i, Composer composer, int i2) {
        SongRow(song, list, function1, set, z, z2, z3, coroutineScope, appContainer, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:93:0x02fb  */
    public static final void AlbumScreen(final AppContainer container, final String albumId, final Function1<? super String, Unit> onOpenArtist, final Function0<Unit> onOpenPlayer, Composer composer, final int i) {
        Composer composer2;
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(albumId, "albumId");
        Intrinsics.checkNotNullParameter(onOpenArtist, "onOpenArtist");
        Intrinsics.checkNotNullParameter(onOpenPlayer, "onOpenPlayer");
        Composer composerStartRestartGroup = composer.startRestartGroup(-737105071);
        int i2 = (i & 6) == 0 ? (composerStartRestartGroup.changedInstance(container) ? 4 : 2) | i : i;
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(albumId) ? 32 : 16;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onOpenArtist) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onOpenPlayer) ? 2048 : 1024;
        }
        int i3 = i2;
        if ((i3 & 1171) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-737105071, i3, -1, "com.dubtonic.player.ui.screens.AlbumScreen (AlbumScreen.kt:58)");
            }
            final DubColors colors = DubTheme.INSTANCE.getColors(composerStartRestartGroup, 6);
            String str = "album-" + albumId;
            composerStartRestartGroup.startReplaceGroup(-2096149272);
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(container) | ((i3 & 112) == 32);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.dubtonic.player.ui.screens.AlbumScreenKt$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AlbumScreenKt.AlbumScreen$lambda$1$lambda$0(container, albumId, (CreationExtras) obj);
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
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(AlbumViewModel.class);
            InitializerViewModelFactoryBuilder initializerViewModelFactoryBuilder = new InitializerViewModelFactoryBuilder();
            initializerViewModelFactoryBuilder.addInitializer(Reflection.getOrCreateKotlinClass(AlbumViewModel.class), function1);
            ViewModel viewModel = ViewModelKt.viewModel((KClass<ViewModel>) orCreateKotlinClass, current, str, initializerViewModelFactoryBuilder.build(), current instanceof HasDefaultViewModelProviderFactory ? ((HasDefaultViewModelProviderFactory) current).getDefaultViewModelCreationExtras() : CreationExtras.Empty.INSTANCE, composerStartRestartGroup, 0, 0);
            composerStartRestartGroup.endReplaceableGroup();
            State stateCollectAsState = SnapshotStateKt.collectAsState(((AlbumViewModel) viewModel).getState(), null, composerStartRestartGroup, 0, 1);
            final State stateCollectAsState2 = SnapshotStateKt.collectAsState(container.getSettings().getStarredFlow(), SetsKt.emptySet(), null, composerStartRestartGroup, 48, 2);
            final State stateCollectAsState3 = SnapshotStateKt.collectAsState(PlayerBus.INSTANCE.getState(), null, composerStartRestartGroup, 0, 1);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -954367824, "CC(remember):Effects.kt#9igjgp");
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                CompositionScopedCoroutineScopeCanceller compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup));
                composerStartRestartGroup.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                objRememberedValue2 = compositionScopedCoroutineScopeCanceller;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue2).getCoroutineScope();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final ServerConfig serverNow = container.getSettings().getServerNow();
            final boolean zIsDemo = container.getLibrary().isDemo();
            LoadState<Album> loadStateAlbumScreen$lambda$2 = AlbumScreen$lambda$2(stateCollectAsState);
            if (loadStateAlbumScreen$lambda$2 instanceof LoadState.Loading) {
                composerStartRestartGroup.startReplaceGroup(-2096133384);
                ComponentsKt.LoadingState(null, 6, composerStartRestartGroup, 48, 1);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                if (loadStateAlbumScreen$lambda$2 instanceof LoadState.Failed) {
                    composerStartRestartGroup.startReplaceGroup(-2096131656);
                    ComponentsKt.ErrorBanner(((LoadState.Failed) loadStateAlbumScreen$lambda$2).getMessage(), null, null, composerStartRestartGroup, 0, 6);
                    composerStartRestartGroup.endReplaceGroup();
                } else if (loadStateAlbumScreen$lambda$2 instanceof LoadState.Ready) {
                    composerStartRestartGroup.startReplaceGroup(-555378609);
                    final Album album = (Album) ((LoadState.Ready) loadStateAlbumScreen$lambda$2).getValue();
                    final List<Song> song = album.getSong();
                    final LinkedHashMap linkedHashMap = new LinkedHashMap();
                    for (Object obj : song) {
                        Long lValueOf = Long.valueOf(((Song) obj).getDiscNumber());
                        Object obj2 = linkedHashMap.get(lValueOf);
                        if (obj2 == null) {
                            ArrayList arrayList = new ArrayList();
                            linkedHashMap.put(lValueOf, arrayList);
                            obj2 = arrayList;
                        }
                        ((List) obj2).add(obj);
                    }
                    Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                    PaddingValues paddingValuesM678PaddingValuesa9UjIt4$default = PaddingKt.m678PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, Dp.m6299constructorimpl(24), 7, null);
                    composerStartRestartGroup.startReplaceGroup(-2096116844);
                    boolean zChanged = composerStartRestartGroup.changed(serverNow) | composerStartRestartGroup.changedInstance(container) | composerStartRestartGroup.changedInstance(album) | composerStartRestartGroup.changed(colors) | ((i3 & 896) == 256) | composerStartRestartGroup.changedInstance(song) | composerStartRestartGroup.changed(zIsDemo) | ((i3 & 7168) == 2048) | composerStartRestartGroup.changedInstance(linkedHashMap) | composerStartRestartGroup.changed(stateCollectAsState2) | composerStartRestartGroup.changed(stateCollectAsState3) | composerStartRestartGroup.changedInstance(coroutineScope);
                    Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        composer2 = composerStartRestartGroup;
                        Function1 function2 = new Function1() { // from class: com.dubtonic.player.ui.screens.AlbumScreenKt$$ExternalSyntheticLambda6
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj3) {
                                return AlbumScreenKt.AlbumScreen$lambda$16$lambda$15(linkedHashMap, song, album, colors, onOpenArtist, serverNow, container, zIsDemo, onOpenPlayer, coroutineScope, stateCollectAsState2, stateCollectAsState3, (LazyListScope) obj3);
                            }
                        };
                        composer2.updateRememberedValue(function2);
                        objRememberedValue3 = function2;
                    } else {
                        composer2 = composerStartRestartGroup;
                    }
                    composer2.endReplaceGroup();
                    LazyDslKt.LazyColumn(modifierFillMaxSize$default, null, paddingValuesM678PaddingValuesa9UjIt4$default, false, null, null, null, false, (Function1) objRememberedValue3, composer2, 390, 250);
                    composer2.endReplaceGroup();
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.startReplaceGroup(-2095983482);
                    composer2.endReplaceGroup();
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
            composer2 = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.dubtonic.player.ui.screens.AlbumScreenKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return AlbumScreenKt.AlbumScreen$lambda$17(container, albumId, onOpenArtist, onOpenPlayer, i, (Composer) obj3, ((Integer) obj4).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AlbumViewModel AlbumScreen$lambda$1$lambda$0(AppContainer appContainer, String str, CreationExtras viewModel) {
        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
        return new AlbumViewModel(appContainer, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String AlbumScreen$artUrl(ServerConfig serverConfig, AppContainer appContainer, String str) {
        if (serverConfig != null) {
            return LibraryRepository.coverArtUrl$default(appContainer.getLibrary(), serverConfig, str, 0, 4, null);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit AlbumScreen$lambda$16$lambda$15(Map map, final List list, Album album, final DubColors dubColors, Function1 function1, final ServerConfig serverConfig, final AppContainer appContainer, final boolean z, final Function0 function0, final CoroutineScope coroutineScope, final State state, final State state2, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-896088667, true, new AlbumScreenKt$AlbumScreen$1$1$1(album, dubColors, function1, serverConfig, appContainer, list, z, function0)), 3, null);
        if (map.size() <= 1) {
            final Function1 function2 = new Function1() { // from class: com.dubtonic.player.ui.screens.AlbumScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return AlbumScreenKt.AlbumScreen$lambda$16$lambda$15$lambda$12((Song) obj);
                }
            };
            final AlbumScreenKt$AlbumScreen$lambda$16$lambda$15$$inlined$items$default$1 albumScreenKt$AlbumScreen$lambda$16$lambda$15$$inlined$items$default$1 = new Function1() { // from class: com.dubtonic.player.ui.screens.AlbumScreenKt$AlbumScreen$lambda$16$lambda$15$$inlined$items$default$1
                @Override // kotlin.jvm.functions.Function1
                public final Void invoke(Song song) {
                    return null;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return invoke((Song) obj);
                }
            };
            LazyColumn.items(list.size(), new Function1<Integer, Object>() { // from class: com.dubtonic.player.ui.screens.AlbumScreenKt$AlbumScreen$lambda$16$lambda$15$$inlined$items$default$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Object invoke(int i) {
                    return function2.invoke(list.get(i));
                }
            }, new Function1<Integer, Object>() { // from class: com.dubtonic.player.ui.screens.AlbumScreenKt$AlbumScreen$lambda$16$lambda$15$$inlined$items$default$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Object invoke(int i) {
                    return albumScreenKt$AlbumScreen$lambda$16$lambda$15$$inlined$items$default$1.invoke(list.get(i));
                }
            }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.screens.AlbumScreenKt$AlbumScreen$lambda$16$lambda$15$$inlined$items$default$4
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
                    Song song2 = (Song) list.get(i);
                    composer.startReplaceGroup(158851420);
                    List list2 = list;
                    composer.startReplaceGroup(-271969913);
                    boolean zChanged = composer.changed(serverConfig) | composer.changedInstance(appContainer);
                    AlbumScreenKt$AlbumScreen$1$1$4$1$1 albumScreenKt$AlbumScreen$1$1$4$1$1RememberedValue = composer.rememberedValue();
                    if (zChanged || albumScreenKt$AlbumScreen$1$1$4$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                        albumScreenKt$AlbumScreen$1$1$4$1$1RememberedValue = new AlbumScreenKt$AlbumScreen$1$1$4$1$1(serverConfig, appContainer);
                        composer.updateRememberedValue(albumScreenKt$AlbumScreen$1$1$4$1$1RememberedValue);
                    }
                    composer.endReplaceGroup();
                    Function1 function3 = (Function1) ((KFunction) albumScreenKt$AlbumScreen$1$1$4$1$1RememberedValue);
                    Set setAlbumScreen$lambda$3 = AlbumScreenKt.AlbumScreen$lambda$3(state);
                    QueueItem current = AlbumScreenKt.AlbumScreen$lambda$4(state2).getCurrent();
                    AlbumScreenKt.SongRow(song2, list2, function3, setAlbumScreen$lambda$3, Intrinsics.areEqual((current == null || (song = current.getSong()) == null) ? null : song.getId(), song2.getId()), AlbumScreenKt.AlbumScreen$lambda$4(state2).getPlaying(), z, coroutineScope, appContainer, function0, composer, 0);
                    composer.endReplaceGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }));
        } else {
            for (Iterator it = CollectionsKt.sortedWith(map.entrySet(), new Comparator() { // from class: com.dubtonic.player.ui.screens.AlbumScreenKt$AlbumScreen$lambda$16$lambda$15$$inlined$sortedBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues((Long) ((Map.Entry) t).getKey(), (Long) ((Map.Entry) t2).getKey());
                }
            }).iterator(); it.hasNext(); it = it) {
                Map.Entry entry = (Map.Entry) it.next();
                final long jLongValue = ((Number) entry.getKey()).longValue();
                final List list2 = (List) entry.getValue();
                LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(848991707, true, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.screens.AlbumScreenKt$AlbumScreen$1$1$2$1
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
                        invoke(lazyItemScope, composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(LazyItemScope item, Composer composer, int i) {
                        Intrinsics.checkNotNullParameter(item, "$this$item");
                        if ((i & 17) != 16 || !composer.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(848991707, i, -1, "com.dubtonic.player.ui.screens.AlbumScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (AlbumScreen.kt:147)");
                            }
                            TextKt.m2373Text4IGK_g("Disc " + jLongValue, PaddingKt.m682paddingVpY3zN4(Modifier.INSTANCE, Dp.m6299constructorimpl(20), Dp.m6299constructorimpl(8)), dubColors.m7165getTextFaint0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getLabelLarge(), composer, 48, 0, 65528);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        composer.skipToGroupEnd();
                    }
                }), 3, null);
                final Function1 function3 = new Function1() { // from class: com.dubtonic.player.ui.screens.AlbumScreenKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return AlbumScreenKt.AlbumScreen$lambda$16$lambda$15$lambda$11$lambda$8((Song) obj);
                    }
                };
                final AlbumScreenKt$AlbumScreen$lambda$16$lambda$15$lambda$11$$inlined$items$default$1 albumScreenKt$AlbumScreen$lambda$16$lambda$15$lambda$11$$inlined$items$default$1 = new Function1() { // from class: com.dubtonic.player.ui.screens.AlbumScreenKt$AlbumScreen$lambda$16$lambda$15$lambda$11$$inlined$items$default$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Void invoke(Song song) {
                        return null;
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        return invoke((Song) obj);
                    }
                };
                LazyColumn.items(list2.size(), new Function1<Integer, Object>() { // from class: com.dubtonic.player.ui.screens.AlbumScreenKt$AlbumScreen$lambda$16$lambda$15$lambda$11$$inlined$items$default$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                        return invoke(num.intValue());
                    }

                    public final Object invoke(int i) {
                        return function3.invoke(list2.get(i));
                    }
                }, new Function1<Integer, Object>() { // from class: com.dubtonic.player.ui.screens.AlbumScreenKt$AlbumScreen$lambda$16$lambda$15$lambda$11$$inlined$items$default$3
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                        return invoke(num.intValue());
                    }

                    public final Object invoke(int i) {
                        return albumScreenKt$AlbumScreen$lambda$16$lambda$15$lambda$11$$inlined$items$default$1.invoke(list2.get(i));
                    }
                }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.screens.AlbumScreenKt$AlbumScreen$lambda$16$lambda$15$lambda$11$$inlined$items$default$4
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
                        Song song2 = (Song) list2.get(i);
                        composer.startReplaceGroup(-1665099911);
                        List list3 = list;
                        composer.startReplaceGroup(-1716280378);
                        boolean zChanged = composer.changed(serverConfig) | composer.changedInstance(appContainer);
                        AlbumScreenKt$AlbumScreen$1$1$2$3$1$1 albumScreenKt$AlbumScreen$1$1$2$3$1$1RememberedValue = composer.rememberedValue();
                        if (zChanged || albumScreenKt$AlbumScreen$1$1$2$3$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                            albumScreenKt$AlbumScreen$1$1$2$3$1$1RememberedValue = new AlbumScreenKt$AlbumScreen$1$1$2$3$1$1(serverConfig, appContainer);
                            composer.updateRememberedValue(albumScreenKt$AlbumScreen$1$1$2$3$1$1RememberedValue);
                        }
                        composer.endReplaceGroup();
                        Function1 function4 = (Function1) ((KFunction) albumScreenKt$AlbumScreen$1$1$2$3$1$1RememberedValue);
                        Set setAlbumScreen$lambda$3 = AlbumScreenKt.AlbumScreen$lambda$3(state);
                        QueueItem current = AlbumScreenKt.AlbumScreen$lambda$4(state2).getCurrent();
                        AlbumScreenKt.SongRow(song2, list3, function4, setAlbumScreen$lambda$3, Intrinsics.areEqual((current == null || (song = current.getSong()) == null) ? null : song.getId(), song2.getId()), AlbumScreenKt.AlbumScreen$lambda$4(state2).getPlaying(), z, coroutineScope, appContainer, function0, composer, 0);
                        composer.endReplaceGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }));
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object AlbumScreen$lambda$16$lambda$15$lambda$11$lambda$8(Song it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object AlbumScreen$lambda$16$lambda$15$lambda$12(Song it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SongRow(final Song song, final List<Song> list, final Function1<? super String, String> function1, final Set<String> set, final boolean z, final boolean z2, final boolean z3, final CoroutineScope coroutineScope, final AppContainer appContainer, final Function0<Unit> function0, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1278833665);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(song) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(list) ? 32 : 16;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(set) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(z2) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(z3) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(coroutineScope) ? 8388608 : 4194304;
        }
        if ((100663296 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(appContainer) ? 67108864 : 33554432;
        }
        if ((805306368 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? C.BUFFER_FLAG_LAST_SAMPLE : 268435456;
        }
        if ((306783379 & i2) != 306783378 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1278833665, i2, -1, "com.dubtonic.player.ui.screens.SongRow (AlbumScreen.kt:183)");
            }
            String title = song.getTitle();
            String artist = song.getArtist();
            String strInvoke = function1.invoke(song.getArtworkKey());
            long duration = song.getDuration();
            Long lValueOf = Long.valueOf(song.getTrack());
            if (lValueOf.longValue() <= 0) {
                lValueOf = null;
            }
            String string = lValueOf != null ? lValueOf.toString() : null;
            boolean zContains = set.contains(song.getId());
            composerStartRestartGroup.startReplaceGroup(-1842031270);
            int i3 = i2 & 14;
            boolean zChangedInstance = ((3670016 & i2) == 1048576) | composerStartRestartGroup.changedInstance(list) | (i3 == 4) | ((1879048192 & i2) == 536870912);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function0() { // from class: com.dubtonic.player.ui.screens.AlbumScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AlbumScreenKt.SongRow$lambda$20$lambda$19(list, song, z3, function0);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Function0 function2 = (Function0) objRememberedValue;
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.startReplaceGroup(-1842026960);
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(coroutineScope) | composerStartRestartGroup.changedInstance(appContainer) | (i3 == 4) | composerStartRestartGroup.changedInstance(set);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: com.dubtonic.player.ui.screens.AlbumScreenKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return AlbumScreenKt.SongRow$lambda$22$lambda$21(coroutineScope, appContainer, song, set);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composerStartRestartGroup.endReplaceGroup();
            ComponentsKt.TrackRow(title, artist, strInvoke, duration, string, z, z2, zContains, function2, (Function0) objRememberedValue2, null, null, composerStartRestartGroup, (i2 << 3) & 4128768, 0, 3072);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.dubtonic.player.ui.screens.AlbumScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AlbumScreenKt.SongRow$lambda$23(song, list, function1, set, z, z2, z3, coroutineScope, appContainer, function0, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SongRow$lambda$20$lambda$19(List list, Song song, boolean z, Function0 function0) {
        PlayerBus.INSTANCE.playQueue(list, list.indexOf(song), z);
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit SongRow$lambda$22$lambda$21(CoroutineScope coroutineScope, AppContainer appContainer, Song song, Set set) {
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AlbumScreenKt$SongRow$3$1$1(appContainer, song, set, null), 3, null);
        return Unit.INSTANCE;
    }

    private static final LoadState<Album> AlbumScreen$lambda$2(State<? extends LoadState<Album>> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Set<String> AlbumScreen$lambda$3(State<? extends Set<String>> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PlayerUiState AlbumScreen$lambda$4(State<PlayerUiState> state) {
        return state.getValue();
    }
}
