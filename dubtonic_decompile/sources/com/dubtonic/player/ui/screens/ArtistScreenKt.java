package com.dubtonic.player.ui.screens;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.Dp;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.InitializerViewModelFactoryBuilder;
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner;
import androidx.lifecycle.viewmodel.compose.ViewModelKt;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.dubtonic.player.data.LibraryRepository;
import com.dubtonic.player.data.LoadState;
import com.dubtonic.player.data.api.ServerConfig;
import com.dubtonic.player.data.model.Album;
import com.dubtonic.player.data.model.ArtistDetail;
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
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: ArtistScreen.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u001a?\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u0010\n\u001ai\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00072\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\tH\u0003¢\u0006\u0002\u0010\u0014¨\u0006\u0015²\u0006\u0010\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u008a\u0084\u0002²\u0006\u0010\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fX\u008a\u0084\u0002²\u0006\n\u0010\u0019\u001a\u00020\u001aX\u008a\u0084\u0002²\u0006\u0010\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u008a\u008e\u0002"}, d2 = {"ArtistScreen", "", TtmlNode.RUBY_CONTAINER, "Lcom/dubtonic/player/di/AppContainer;", "artistId", "", "onOpenAlbum", "Lkotlin/Function1;", "onOpenPlayer", "Lkotlin/Function0;", "(Lcom/dubtonic/player/di/AppContainer;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "TopSongsLoader", "artistName", "artUrl", "starred", "", "currentSongId", "playing", "", "demo", "(Lcom/dubtonic/player/di/AppContainer;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Ljava/util/Set;Ljava/lang/String;ZZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app_release", "state", "Lcom/dubtonic/player/data/LoadState;", "Lcom/dubtonic/player/data/model/ArtistDetail;", "playerState", "Lcom/dubtonic/player/player/PlayerUiState;", "songs", "", "Lcom/dubtonic/player/data/model/Song;"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ArtistScreenKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ArtistScreen$lambda$14(AppContainer appContainer, String str, Function1 function1, Function0 function0, int i, Composer composer, int i2) {
        ArtistScreen(appContainer, str, function1, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TopSongsLoader$lambda$25(AppContainer appContainer, String str, Function1 function1, Set set, String str2, boolean z, boolean z2, Function0 function0, int i, Composer composer, int i2) {
        TopSongsLoader(appContainer, str, function1, set, str2, z, z2, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:86:0x02a9  */
    public static final void ArtistScreen(final AppContainer container, final String artistId, final Function1<? super String, Unit> onOpenAlbum, final Function0<Unit> onOpenPlayer, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Intrinsics.checkNotNullParameter(container, "container");
        Intrinsics.checkNotNullParameter(artistId, "artistId");
        Intrinsics.checkNotNullParameter(onOpenAlbum, "onOpenAlbum");
        Intrinsics.checkNotNullParameter(onOpenPlayer, "onOpenPlayer");
        Composer composerStartRestartGroup = composer.startRestartGroup(79731451);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(container) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(artistId) ? 32 : 16;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(onOpenAlbum) ? 256 : 128;
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
                ComposerKt.traceEventStart(79731451, i3, -1, "com.dubtonic.player.ui.screens.ArtistScreen (ArtistScreen.kt:50)");
            }
            final DubColors colors = DubTheme.INSTANCE.getColors(composerStartRestartGroup, 6);
            String str = "artist-" + artistId;
            composerStartRestartGroup.startReplaceGroup(1521670900);
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(container) | ((i3 & 112) == 32);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: com.dubtonic.player.ui.screens.ArtistScreenKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return ArtistScreenKt.ArtistScreen$lambda$1$lambda$0(container, artistId, (CreationExtras) obj);
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
            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(ArtistViewModel.class);
            InitializerViewModelFactoryBuilder initializerViewModelFactoryBuilder = new InitializerViewModelFactoryBuilder();
            initializerViewModelFactoryBuilder.addInitializer(Reflection.getOrCreateKotlinClass(ArtistViewModel.class), function1);
            ViewModel viewModel = ViewModelKt.viewModel((KClass<ViewModel>) orCreateKotlinClass, current, str, initializerViewModelFactoryBuilder.build(), current instanceof HasDefaultViewModelProviderFactory ? ((HasDefaultViewModelProviderFactory) current).getDefaultViewModelCreationExtras() : CreationExtras.Empty.INSTANCE, composerStartRestartGroup, 0, 0);
            composerStartRestartGroup.endReplaceableGroup();
            State stateCollectAsState = SnapshotStateKt.collectAsState(((ArtistViewModel) viewModel).getState(), null, composerStartRestartGroup, 0, 1);
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
            LoadState<ArtistDetail> loadStateArtistScreen$lambda$2 = ArtistScreen$lambda$2(stateCollectAsState);
            if (loadStateArtistScreen$lambda$2 instanceof LoadState.Loading) {
                composerStartRestartGroup.startReplaceGroup(1521686850);
                ComponentsKt.LoadingState(null, 6, composerStartRestartGroup, 48, 1);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                if (loadStateArtistScreen$lambda$2 instanceof LoadState.Failed) {
                    composerStartRestartGroup.startReplaceGroup(1521688578);
                    ComponentsKt.ErrorBanner(((LoadState.Failed) loadStateArtistScreen$lambda$2).getMessage(), null, null, composerStartRestartGroup, 0, 6);
                    composerStartRestartGroup.endReplaceGroup();
                } else if (loadStateArtistScreen$lambda$2 instanceof LoadState.Ready) {
                    composerStartRestartGroup.startReplaceGroup(-72052660);
                    final ArtistDetail artistDetail = (ArtistDetail) ((LoadState.Ready) loadStateArtistScreen$lambda$2).getValue();
                    final List<Album> album = artistDetail.getAlbum();
                    Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                    PaddingValues paddingValuesM678PaddingValuesa9UjIt4$default = PaddingKt.m678PaddingValuesa9UjIt4$default(0.0f, 0.0f, 0.0f, Dp.m6299constructorimpl(24), 7, null);
                    composerStartRestartGroup.startReplaceGroup(1521703339);
                    boolean zChanged = composerStartRestartGroup.changed(serverNow) | composerStartRestartGroup.changedInstance(container) | composerStartRestartGroup.changedInstance(artistDetail) | composerStartRestartGroup.changed(colors) | composerStartRestartGroup.changedInstance(coroutineScope) | composerStartRestartGroup.changed(zIsDemo) | ((i3 & 7168) == 2048) | composerStartRestartGroup.changedInstance(album) | ((i3 & 896) == 256) | composerStartRestartGroup.changed(stateCollectAsState2) | composerStartRestartGroup.changed(stateCollectAsState3);
                    Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        composer2 = composerStartRestartGroup;
                        Function1 function2 = new Function1() { // from class: com.dubtonic.player.ui.screens.ArtistScreenKt$$ExternalSyntheticLambda1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return ArtistScreenKt.ArtistScreen$lambda$13$lambda$12(album, serverNow, artistDetail, colors, container, coroutineScope, zIsDemo, onOpenPlayer, onOpenAlbum, stateCollectAsState2, stateCollectAsState3, (LazyListScope) obj);
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
                    composer2.startReplaceGroup(1521886704);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.dubtonic.player.ui.screens.ArtistScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ArtistScreenKt.ArtistScreen$lambda$14(container, artistId, onOpenAlbum, onOpenPlayer, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ArtistViewModel ArtistScreen$lambda$1$lambda$0(AppContainer appContainer, String str, CreationExtras viewModel) {
        Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
        return new ArtistViewModel(appContainer, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String ArtistScreen$artUrl(ServerConfig serverConfig, AppContainer appContainer, String str) {
        if (serverConfig != null) {
            return LibraryRepository.coverArtUrl$default(appContainer.getLibrary(), serverConfig, str, 0, 4, null);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ArtistScreen$lambda$13$lambda$12(List list, final ServerConfig serverConfig, final ArtistDetail artistDetail, DubColors dubColors, final AppContainer appContainer, final CoroutineScope coroutineScope, final boolean z, final Function0 function0, Function1 function1, final State state, final State state2, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(1915318351, true, new ArtistScreenKt$ArtistScreen$1$1$1(artistDetail, dubColors, serverConfig, appContainer, coroutineScope, z, function0)), 3, null);
        if (!list.isEmpty()) {
            LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$ArtistScreenKt.INSTANCE.m7053getLambda1$app_release(), 3, null);
            LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1123969887, true, new ArtistScreenKt$ArtistScreen$1$1$2(list, serverConfig, appContainer, function1)), 3, null);
        } else {
            LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$ArtistScreenKt.INSTANCE.m7054getLambda2$app_release(), 3, null);
        }
        LazyListScope.item$default(LazyColumn, null, null, ComposableSingletons$ArtistScreenKt.INSTANCE.m7055getLambda3$app_release(), 3, null);
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, ((Album) it.next()).getSong());
        }
        final ArrayList arrayList2 = arrayList;
        if (arrayList2.isEmpty() && serverConfig != null) {
            LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-67337567, true, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.screens.ArtistScreenKt$ArtistScreen$1$1$3
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer, Integer num) {
                    invoke(lazyItemScope, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(LazyItemScope item, Composer composer, int i) {
                    Song song;
                    Intrinsics.checkNotNullParameter(item, "$this$item");
                    if ((i & 17) != 16 || !composer.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-67337567, i, -1, "com.dubtonic.player.ui.screens.ArtistScreen.<anonymous>.<anonymous>.<anonymous> (ArtistScreen.kt:156)");
                        }
                        AppContainer appContainer2 = appContainer;
                        String name = artistDetail.getName();
                        composer.startReplaceGroup(-1620820975);
                        boolean zChanged = composer.changed(serverConfig) | composer.changedInstance(appContainer);
                        ServerConfig serverConfig2 = serverConfig;
                        AppContainer appContainer3 = appContainer;
                        ArtistScreenKt$ArtistScreen$1$1$3$1$1 artistScreenKt$ArtistScreen$1$1$3$1$1RememberedValue = composer.rememberedValue();
                        if (zChanged || artistScreenKt$ArtistScreen$1$1$3$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                            artistScreenKt$ArtistScreen$1$1$3$1$1RememberedValue = new ArtistScreenKt$ArtistScreen$1$1$3$1$1(serverConfig2, appContainer3);
                            composer.updateRememberedValue(artistScreenKt$ArtistScreen$1$1$3$1$1RememberedValue);
                        }
                        composer.endReplaceGroup();
                        Function1 function2 = (Function1) ((KFunction) artistScreenKt$ArtistScreen$1$1$3$1$1RememberedValue);
                        Set setArtistScreen$lambda$3 = ArtistScreenKt.ArtistScreen$lambda$3(state);
                        QueueItem current = ArtistScreenKt.ArtistScreen$lambda$4(state2).getCurrent();
                        ArtistScreenKt.TopSongsLoader(appContainer2, name, function2, setArtistScreen$lambda$3, (current == null || (song = current.getSong()) == null) ? null : song.getId(), ArtistScreenKt.ArtistScreen$lambda$4(state2).getPlaying(), z, function0, composer, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer.skipToGroupEnd();
                }
            }), 3, null);
        } else {
            final Function1 function2 = new Function1() { // from class: com.dubtonic.player.ui.screens.ArtistScreenKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ArtistScreenKt.ArtistScreen$lambda$13$lambda$12$lambda$7((Song) obj);
                }
            };
            final ArtistScreenKt$ArtistScreen$lambda$13$lambda$12$$inlined$items$default$1 artistScreenKt$ArtistScreen$lambda$13$lambda$12$$inlined$items$default$1 = new Function1() { // from class: com.dubtonic.player.ui.screens.ArtistScreenKt$ArtistScreen$lambda$13$lambda$12$$inlined$items$default$1
                @Override // kotlin.jvm.functions.Function1
                public final Void invoke(Song song) {
                    return null;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return invoke((Song) obj);
                }
            };
            LazyColumn.items(arrayList2.size(), new Function1<Integer, Object>() { // from class: com.dubtonic.player.ui.screens.ArtistScreenKt$ArtistScreen$lambda$13$lambda$12$$inlined$items$default$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Object invoke(int i) {
                    return function2.invoke(arrayList2.get(i));
                }
            }, new Function1<Integer, Object>() { // from class: com.dubtonic.player.ui.screens.ArtistScreenKt$ArtistScreen$lambda$13$lambda$12$$inlined$items$default$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Object invoke(int i) {
                    return artistScreenKt$ArtistScreen$lambda$13$lambda$12$$inlined$items$default$1.invoke(arrayList2.get(i));
                }
            }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.screens.ArtistScreenKt$ArtistScreen$lambda$13$lambda$12$$inlined$items$default$4
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
                    composer.startReplaceGroup(1294628406);
                    String title = song2.getTitle();
                    String artist = song2.getArtist();
                    String strArtistScreen$artUrl = ArtistScreenKt.ArtistScreen$artUrl(serverConfig, appContainer, song2.getArtworkKey());
                    long duration = song2.getDuration();
                    Long lValueOf = Long.valueOf(song2.getTrack());
                    String id = null;
                    if (lValueOf.longValue() <= 0) {
                        lValueOf = null;
                    }
                    String string = lValueOf != null ? lValueOf.toString() : null;
                    QueueItem current = ArtistScreenKt.ArtistScreen$lambda$4(state2).getCurrent();
                    if (current != null && (song = current.getSong()) != null) {
                        id = song.getId();
                    }
                    boolean zAreEqual = Intrinsics.areEqual(id, song2.getId());
                    boolean playing = ArtistScreenKt.ArtistScreen$lambda$4(state2).getPlaying();
                    boolean zContains = ArtistScreenKt.ArtistScreen$lambda$3(state).contains(song2.getId());
                    composer.startReplaceGroup(-1620788785);
                    boolean zChangedInstance = composer.changedInstance(arrayList2) | composer.changed(song2) | composer.changed(z) | composer.changed(function0);
                    Object objRememberedValue = composer.rememberedValue();
                    if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        final List list2 = arrayList2;
                        final boolean z2 = z;
                        final Function0 function3 = function0;
                        objRememberedValue = (Function0) new Function0<Unit>() { // from class: com.dubtonic.player.ui.screens.ArtistScreenKt$ArtistScreen$1$1$5$2$1
                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                PlayerBus playerBus = PlayerBus.INSTANCE;
                                List<Song> list3 = list2;
                                playerBus.playQueue(list3, list3.indexOf(song2), z2);
                                function3.invoke();
                            }
                        };
                        composer.updateRememberedValue(objRememberedValue);
                    }
                    Function0 function4 = (Function0) objRememberedValue;
                    composer.endReplaceGroup();
                    composer.startReplaceGroup(-1620782059);
                    boolean zChangedInstance2 = composer.changedInstance(coroutineScope) | composer.changedInstance(appContainer) | composer.changed(song2) | composer.changed(state);
                    Object objRememberedValue2 = composer.rememberedValue();
                    if (zChangedInstance2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                        final CoroutineScope coroutineScope2 = coroutineScope;
                        final AppContainer appContainer2 = appContainer;
                        final State state3 = state;
                        objRememberedValue2 = (Function0) new Function0<Unit>() { // from class: com.dubtonic.player.ui.screens.ArtistScreenKt$ArtistScreen$1$1$5$3$1

                            /* JADX INFO: renamed from: com.dubtonic.player.ui.screens.ArtistScreenKt$ArtistScreen$1$1$5$3$1$1, reason: invalid class name */
                            /* JADX INFO: compiled from: ArtistScreen.kt */
                            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
                            @DebugMetadata(c = "com.dubtonic.player.ui.screens.ArtistScreenKt$ArtistScreen$1$1$5$3$1$1", f = "ArtistScreen.kt", i = {}, l = {184}, m = "invokeSuspend", n = {}, s = {})
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
                                        if (this.$container.getSettings().setStarred(this.$song.getId(), !ArtistScreenKt.ArtistScreen$lambda$3(this.$starred$delegate).contains(this.$song.getId()), this) == coroutine_suspended) {
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
                                BuildersKt__Builders_commonKt.launch$default(coroutineScope2, null, null, new AnonymousClass1(appContainer2, song2, state3, null), 3, null);
                            }
                        };
                        composer.updateRememberedValue(objRememberedValue2);
                    }
                    composer.endReplaceGroup();
                    ComponentsKt.TrackRow(title, artist, strArtistScreen$artUrl, duration, string, zAreEqual, playing, zContains, function4, (Function0) objRememberedValue2, null, null, composer, 0, 0, 3072);
                    composer.endReplaceGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object ArtistScreen$lambda$13$lambda$12$lambda$7(Song it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.getId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TopSongsLoader(final AppContainer appContainer, final String str, final Function1<? super String, String> function1, final Set<String> set, final String str2, final boolean z, final boolean z2, final Function0<Unit> function0, Composer composer, final int i) {
        int i2;
        char c;
        Long l;
        Composer composer2;
        final MutableState mutableState;
        Composer composerStartRestartGroup = composer.startRestartGroup(-210167285);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(appContainer) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(str) ? 32 : 16;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(set) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changed(str2) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(z2) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 8388608 : 4194304;
        }
        int i3 = i2;
        if ((4793491 & i3) != 4793490 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-210167285, i3, -1, "com.dubtonic.player.ui.screens.TopSongsLoader (ArtistScreen.kt:204)");
            }
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
            ServerConfig serverNow = appContainer.getSettings().getServerNow();
            composerStartRestartGroup.startReplaceGroup(2065166337);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(CollectionsKt.emptyList(), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            MutableState mutableState2 = (MutableState) objRememberedValue2;
            composerStartRestartGroup.endReplaceGroup();
            composerStartRestartGroup.startReplaceGroup(2065171601);
            boolean zChanged = composerStartRestartGroup.changed(serverNow) | composerStartRestartGroup.changedInstance(appContainer) | ((i3 & 112) == 32);
            ArtistScreenKt$TopSongsLoader$1$1 artistScreenKt$TopSongsLoader$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || artistScreenKt$TopSongsLoader$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                c = 0;
                l = null;
                artistScreenKt$TopSongsLoader$1$1RememberedValue = new ArtistScreenKt$TopSongsLoader$1$1(serverNow, appContainer, str, mutableState2, null);
                composerStartRestartGroup.updateRememberedValue(artistScreenKt$TopSongsLoader$1$1RememberedValue);
            } else {
                c = 0;
                l = null;
            }
            composerStartRestartGroup.endReplaceGroup();
            EffectsKt.LaunchedEffect(str, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) artistScreenKt$TopSongsLoader$1$1RememberedValue, composerStartRestartGroup, (i3 >> 3) & 14);
            Iterator it = TopSongsLoader$lambda$16(mutableState2).iterator();
            while (it.hasNext()) {
                final Song song = (Song) it.next();
                String title = song.getTitle();
                String artist = song.getArtist();
                String strInvoke = function1.invoke(song.getArtworkKey());
                long duration = song.getDuration();
                Long lValueOf = Long.valueOf(song.getTrack());
                if (lValueOf.longValue() <= 0) {
                    lValueOf = l;
                }
                String string = lValueOf != null ? lValueOf.toString() : l;
                boolean zAreEqual = Intrinsics.areEqual(str2, song.getId());
                boolean zContains = set.contains(song.getId());
                composerStartRestartGroup.startReplaceGroup(1307238184);
                Iterator it2 = it;
                boolean zChanged2 = ((i3 & 3670016) == c) | composerStartRestartGroup.changed(song) | ((i3 & 29360128) == 8388608);
                Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    mutableState = mutableState2;
                    objRememberedValue3 = new Function0() { // from class: com.dubtonic.player.ui.screens.ArtistScreenKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ArtistScreenKt.TopSongsLoader$lambda$24$lambda$21$lambda$20(song, z2, function0, mutableState);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    mutableState = mutableState2;
                }
                Function0 function2 = (Function0) objRememberedValue3;
                composerStartRestartGroup.endReplaceGroup();
                composerStartRestartGroup.startReplaceGroup(1307242816);
                boolean zChangedInstance = composerStartRestartGroup.changedInstance(r17) | composerStartRestartGroup.changedInstance(appContainer) | composerStartRestartGroup.changed(song) | composerStartRestartGroup.changedInstance(set);
                mutableState2 = mutableState;
                Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue4 = new Function0() { // from class: com.dubtonic.player.ui.screens.ArtistScreenKt$$ExternalSyntheticLambda4
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return ArtistScreenKt.TopSongsLoader$lambda$24$lambda$23$lambda$22(coroutineScope, appContainer, song, set);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                composerStartRestartGroup.endReplaceGroup();
                Composer composer3 = composerStartRestartGroup;
                ComponentsKt.TrackRow(title, artist, strInvoke, duration, string, zAreEqual, z, zContains, function2, (Function0) objRememberedValue4, null, null, composer3, (i3 << 3) & 3670016, 0, 3072);
                it = it2;
                c = 0;
                composerStartRestartGroup = composer3;
            }
            composer2 = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.dubtonic.player.ui.screens.ArtistScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ArtistScreenKt.TopSongsLoader$lambda$25(appContainer, str, function1, set, str2, z, z2, function0, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final List<Song> TopSongsLoader$lambda$16(MutableState<List<Song>> mutableState) {
        return mutableState.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TopSongsLoader$lambda$24$lambda$21$lambda$20(Song song, boolean z, Function0 function0, MutableState mutableState) {
        PlayerBus.INSTANCE.playQueue(TopSongsLoader$lambda$16(mutableState), TopSongsLoader$lambda$16(mutableState).indexOf(song), z);
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TopSongsLoader$lambda$24$lambda$23$lambda$22(CoroutineScope coroutineScope, AppContainer appContainer, Song song, Set set) {
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new ArtistScreenKt$TopSongsLoader$2$3$1$1(appContainer, song, set, null), 3, null);
        return Unit.INSTANCE;
    }

    private static final LoadState<ArtistDetail> ArtistScreen$lambda$2(State<? extends LoadState<ArtistDetail>> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Set<String> ArtistScreen$lambda$3(State<? extends Set<String>> state) {
        return state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PlayerUiState ArtistScreen$lambda$4(State<PlayerUiState> state) {
        return state.getValue();
    }
}
