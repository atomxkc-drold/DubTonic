package com.dubtonic.player.ui.components;

import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.rounded.HomeKt;
import androidx.compose.material.icons.rounded.LibraryBooksKt;
import androidx.compose.material.icons.rounded.PauseKt;
import androidx.compose.material.icons.rounded.PlayArrowKt;
import androidx.compose.material.icons.rounded.QueueMusicKt;
import androidx.compose.material.icons.rounded.SearchKt;
import androidx.compose.material.icons.rounded.SettingsKt;
import androidx.compose.material.icons.rounded.SkipNextKt;
import androidx.compose.material.icons.rounded.TuneKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.MenuKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.draw.ShadowKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.profileinstaller.ProfileVerifier;
import com.dubtonic.player.player.PlayerBus;
import com.dubtonic.player.player.PlayerUiState;
import com.dubtonic.player.player.QueueItem;
import com.dubtonic.player.ui.theme.DubColors;
import com.dubtonic.player.ui.theme.DubDimens;
import com.dubtonic.player.ui.theme.DubTheme;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: Bars.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\u001aG\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007H\u0007¢\u0006\u0002\u0010\u000b\u001a3\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\u00032\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00142\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\u0015\u001a;\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\u001b\u001a%\u0010\u001c\u001a\u00020\u00012\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\u001e\"\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001f²\u0006\n\u0010 \u001a\u00020!X\u008a\u008e\u0002"}, d2 = {"TopBar", "", "statusLabel", "", "statusTone", "Lcom/dubtonic/player/ui/components/StatusTone;", "onLogoClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "onSettings", "(Ljava/lang/String;Lcom/dubtonic/player/ui/components/StatusTone;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "navItems", "", "Lcom/dubtonic/player/ui/components/NavItem;", "getNavItems", "()Ljava/util/List;", "BottomNav", "activeRoute", "onNavigate", "Lkotlin/Function1;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "MiniPlayer", "state", "Lcom/dubtonic/player/player/PlayerUiState;", "onOpen", "onDismiss", "(Lcom/dubtonic/player/player/PlayerUiState;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "LibHint", "onGotIt", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "app_release", "dragOffset", ""}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class BarsKt {
    private static final List<NavItem> navItems = CollectionsKt.listOf((Object[]) new NavItem[]{new NavItem("Home", HomeKt.getHome(Icons.Rounded.INSTANCE), "home"), new NavItem("Library", LibraryBooksKt.getLibraryBooks(Icons.Rounded.INSTANCE), "library/albums"), new NavItem("Search", SearchKt.getSearch(Icons.Rounded.INSTANCE), "search"), new NavItem("Playlists", QueueMusicKt.getQueueMusic(Icons.Rounded.INSTANCE), "playlists"), new NavItem("Settings", TuneKt.getTune(Icons.Rounded.INSTANCE), "settings")});

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BottomNav$lambda$11(String str, Function1 function1, Modifier modifier, int i, int i2, Composer composer, int i3) {
        BottomNav(str, function1, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit LibHint$lambda$22(Function0 function0, Modifier modifier, int i, int i2, Composer composer, int i3) {
        LibHint(function0, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int MiniPlayer$lambda$17$lambda$16(int i) {
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int MiniPlayer$lambda$19$lambda$18(int i) {
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit MiniPlayer$lambda$20(PlayerUiState playerUiState, Function0 function0, Function0 function1, Modifier modifier, int i, int i2, Composer composer, int i3) {
        MiniPlayer(playerUiState, function0, function1, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit TopBar$lambda$3(String str, StatusTone statusTone, Function0 function0, Modifier modifier, Function0 function1, int i, int i2, Composer composer, int i3) {
        TopBar(str, statusTone, function0, modifier, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:100:0x041c  */
    /* JADX WARN: Code duplicated, block: B:101:0x0420  */
    /* JADX WARN: Code duplicated, block: B:104:0x0445  */
    /* JADX WARN: Code duplicated, block: B:106:0x0453  */
    /* JADX WARN: Code duplicated, block: B:109:0x0528  */
    /* JADX WARN: Code duplicated, block: B:112:0x05a5  */
    /* JADX WARN: Code duplicated, block: B:116:0x05b2  */
    /* JADX WARN: Code duplicated, block: B:118:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:46:0x0085  */
    /* JADX WARN: Code duplicated, block: B:47:0x0088  */
    /* JADX WARN: Code duplicated, block: B:49:0x008c  */
    /* JADX WARN: Code duplicated, block: B:51:0x0094  */
    /* JADX WARN: Code duplicated, block: B:52:0x0097  */
    /* JADX WARN: Code duplicated, block: B:57:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:61:0x00b3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:62:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:63:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:66:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:67:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:70:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:73:0x0130  */
    /* JADX WARN: Code duplicated, block: B:76:0x013c  */
    /* JADX WARN: Code duplicated, block: B:77:0x0140  */
    /* JADX WARN: Code duplicated, block: B:80:0x0165  */
    /* JADX WARN: Code duplicated, block: B:82:0x0173  */
    /* JADX WARN: Code duplicated, block: B:85:0x0281  */
    /* JADX WARN: Code duplicated, block: B:88:0x028d  */
    /* JADX WARN: Code duplicated, block: B:89:0x0291  */
    /* JADX WARN: Code duplicated, block: B:92:0x02b6  */
    /* JADX WARN: Code duplicated, block: B:94:0x02c4  */
    /* JADX WARN: Code duplicated, block: B:97:0x0410  */
    public static final void TopBar(final String statusLabel, final StatusTone statusTone, final Function0<Unit> onLogoClick, Modifier modifier, Function0<Unit> function0, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        Function0<Unit> function1;
        int i5;
        int i6;
        Modifier modifier3;
        Function0<Unit> function2;
        DubColors colors;
        int currentCompositeKeyHash;
        Function0<ComposeUiNode> constructor;
        Composer composerM3333constructorimpl;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
        int currentCompositeKeyHash2;
        Function0<ComposeUiNode> constructor2;
        Composer composerM3333constructorimpl2;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2;
        float f;
        int currentCompositeKeyHash3;
        Function0<ComposeUiNode> constructor3;
        Composer composerM3333constructorimpl3;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3;
        Composer composer2;
        final Function0<Unit> function3;
        final Modifier modifier4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(statusLabel, "statusLabel");
        Intrinsics.checkNotNullParameter(statusTone, "statusTone");
        Intrinsics.checkNotNullParameter(onLogoClick, "onLogoClick");
        Composer composerStartRestartGroup = composer.startRestartGroup(160445168);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(statusLabel) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(statusTone) ? 32 : 16;
        }
        if ((i2 & 4) != 0) {
            i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onLogoClick) ? 256 : 128;
        }
        int i7 = i2 & 8;
        if (i7 == 0) {
            if ((i & 3072) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 2048 : 1024;
            }
            i4 = i2 & 16;
            if (i4 != 0) {
                if ((i & 24576) == 0) {
                    function1 = function0;
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i5 = 16384;
                    } else {
                        i5 = 8192;
                    }
                    i3 |= i5;
                }
                i6 = i3;
                if ((i6 & 9363) == 9362 || !composerStartRestartGroup.getSkipping()) {
                    if (i7 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier2;
                    }
                    if (i4 != 0) {
                        function2 = null;
                    } else {
                        function2 = function1;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(160445168, i6, -1, "com.dubtonic.player.ui.components.TopBar (Bars.kt:69)");
                    }
                    colors = DubTheme.INSTANCE.getColors(composerStartRestartGroup, 6);
                    float f2 = 12;
                    Modifier modifierM682paddingVpY3zN4 = PaddingKt.m682paddingVpY3zN4(WindowInsetsPadding_androidKt.statusBarsPadding(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null)), Dp.m6299constructorimpl(f2), Dp.m6299constructorimpl(8));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN4);
                    constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                    composerM3333constructorimpl = Updater.m3333constructorimpl(composerStartRestartGroup);
                    Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3333constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    float f3 = 1;
                    Modifier modifierM683paddingVpY3zN4$default = PaddingKt.m683paddingVpY3zN4$default(ShadowKt.m3504shadows4CzXII$default(BorderKt.m247borderxT4_qwU(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.m712height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), DubDimens.INSTANCE.m7175getTopbarD9Ej5fM()), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM())), colors.m7143getGlassStrong0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(f3), colors.m7141getGlassBorder0d7_KjU(), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM())), Dp.m6299constructorimpl(18), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM()), false, Color.m3839copywmQWz5c$default(Color.INSTANCE.m3866getBlack0d7_KjU(), 0.25f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3866getBlack0d7_KjU(), 0.25f, 0.0f, 0.0f, 0.0f, 14, null), 4, null), Dp.m6299constructorimpl(16), 0.0f, 2, null);
                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composerStartRestartGroup, 48);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM683paddingVpY3zN4$default);
                    constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor2);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3333constructorimpl2 = Updater.m3333constructorimpl(composerStartRestartGroup);
                    Updater.m3340setimpl(composerM3333constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3340setimpl(composerM3333constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3333constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                        composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                        composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                    }
                    Updater.m3340setimpl(composerM3333constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    float f4 = 4;
                    Modifier modifier5 = modifier3;
                    TextKt.m2373Text4IGK_g("DubTonic", PaddingKt.m682paddingVpY3zN4(ClickableKt.m269clickableXHw0xAI$default(ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(Dp.m6299constructorimpl(f2))), false, null, null, onLogoClick, 7, null), Dp.m6299constructorimpl(f4), Dp.m6299constructorimpl(f4)), colors.m7164getText0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getExtraBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getTitleLarge(), composerStartRestartGroup, 196614, 0, 65496);
                    SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null), composerStartRestartGroup, 0);
                    Modifier modifierM247borderxT4_qwU = BorderKt.m247borderxT4_qwU(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM())), colors.m7160getSurface0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(f3), colors.m7146getLine0d7_KjU(), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM()));
                    float fM6299constructorimpl = Dp.m6299constructorimpl(f2);
                    f = 6;
                    Modifier modifierM682paddingVpY3zN5 = PaddingKt.m682paddingVpY3zN4(modifierM247borderxT4_qwU, fM6299constructorimpl, Dp.m6299constructorimpl(f));
                    Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                    MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, composerStartRestartGroup, 48);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                    currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN5);
                    constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor3);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3333constructorimpl3 = Updater.m3333constructorimpl(composerStartRestartGroup);
                    Updater.m3340setimpl(composerM3333constructorimpl3, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3340setimpl(composerM3333constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3333constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                        composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                        composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                    }
                    Updater.m3340setimpl(composerM3333constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                    RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                    float f5 = 7;
                    BoxKt.Box(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f5)), RoundedCornerShapeKt.getCircleShape()), statusTone.m7046colorvNxB06k(colors), null, 2, null), composerStartRestartGroup, 0);
                    SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f5)), composerStartRestartGroup, 6);
                    TextKt.m2373Text4IGK_g(statusLabel, SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(MenuKt.InTransitionDuration)), colors.m7166getTextSoft0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6238getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composerStartRestartGroup, (i6 & 14) | 196656, 3120, 55256);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composer2 = composerStartRestartGroup;
                    composer2.startReplaceGroup(1391377507);
                    if (function2 != null) {
                        SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f)), composer2, 6);
                        IconKt.m1830Iconww6aTOc(SettingsKt.getSettings(Icons.Rounded.INSTANCE), "Settings", PaddingKt.m681padding3ABfNKs(ClickableKt.m269clickableXHw0xAI$default(ClipKt.clip(SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(26)), RoundedCornerShapeKt.getCircleShape()), false, null, null, function2, 7, null), Dp.m6299constructorimpl(3)), colors.m7166getTextSoft0d7_KjU(), composer2, 48, 0);
                    }
                    composer2.endReplaceGroup();
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
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function3 = function2;
                    modifier4 = modifier5;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier4 = modifier2;
                    function3 = function1;
                    composer2 = composerStartRestartGroup;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda1
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return BarsKt.TopBar$lambda$3(statusLabel, statusTone, onLogoClick, modifier4, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            function1 = function0;
            i6 = i3;
            if ((i6 & 9363) == 9362) {
                if (i7 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    function2 = null;
                } else {
                    function2 = function1;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(160445168, i6, -1, "com.dubtonic.player.ui.components.TopBar (Bars.kt:69)");
                }
                colors = DubTheme.INSTANCE.getColors(composerStartRestartGroup, 6);
                float f6 = 12;
                Modifier modifierM682paddingVpY3zN6 = PaddingKt.m682paddingVpY3zN4(WindowInsetsPadding_androidKt.statusBarsPadding(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null)), Dp.m6299constructorimpl(f6), Dp.m6299constructorimpl(8));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN6);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                composerM3333constructorimpl = Updater.m3333constructorimpl(composerStartRestartGroup);
                Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3333constructorimpl.getInserting()) {
                    composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                float f7 = 1;
                Modifier modifierM683paddingVpY3zN4$default2 = PaddingKt.m683paddingVpY3zN4$default(ShadowKt.m3504shadows4CzXII$default(BorderKt.m247borderxT4_qwU(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.m712height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), DubDimens.INSTANCE.m7175getTopbarD9Ej5fM()), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM())), colors.m7143getGlassStrong0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(f7), colors.m7141getGlassBorder0d7_KjU(), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM())), Dp.m6299constructorimpl(18), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM()), false, Color.m3839copywmQWz5c$default(Color.INSTANCE.m3866getBlack0d7_KjU(), 0.25f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3866getBlack0d7_KjU(), 0.25f, 0.0f, 0.0f, 0.0f, 14, null), 4, null), Dp.m6299constructorimpl(16), 0.0f, 2, null);
                Alignment.Vertical centerVertically3 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically3, composerStartRestartGroup, 48);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM683paddingVpY3zN4$default2);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3333constructorimpl2 = Updater.m3333constructorimpl(composerStartRestartGroup);
                Updater.m3340setimpl(composerM3333constructorimpl2, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl2, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3333constructorimpl2.getInserting()) {
                    composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                } else {
                    composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m3340setimpl(composerM3333constructorimpl2, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                float f8 = 4;
                Modifier modifier6 = modifier3;
                TextKt.m2373Text4IGK_g("DubTonic", PaddingKt.m682paddingVpY3zN4(ClickableKt.m269clickableXHw0xAI$default(ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(Dp.m6299constructorimpl(f6))), false, null, null, onLogoClick, 7, null), Dp.m6299constructorimpl(f8), Dp.m6299constructorimpl(f8)), colors.m7164getText0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getExtraBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getTitleLarge(), composerStartRestartGroup, 196614, 0, 65496);
                SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance3, Modifier.INSTANCE, 1.0f, false, 2, null), composerStartRestartGroup, 0);
                Modifier modifierM247borderxT4_qwU2 = BorderKt.m247borderxT4_qwU(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM())), colors.m7160getSurface0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(f7), colors.m7146getLine0d7_KjU(), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM()));
                float fM6299constructorimpl2 = Dp.m6299constructorimpl(f6);
                f = 6;
                Modifier modifierM682paddingVpY3zN7 = PaddingKt.m682paddingVpY3zN4(modifierM247borderxT4_qwU2, fM6299constructorimpl2, Dp.m6299constructorimpl(f));
                Alignment.Vertical centerVertically4 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically4, composerStartRestartGroup, 48);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN7);
                constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor3);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3333constructorimpl3 = Updater.m3333constructorimpl(composerStartRestartGroup);
                Updater.m3340setimpl(composerM3333constructorimpl3, measurePolicyRowMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl3, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3333constructorimpl3.getInserting()) {
                    composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                } else {
                    composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.m3340setimpl(composerM3333constructorimpl3, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
                float f9 = 7;
                BoxKt.Box(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f9)), RoundedCornerShapeKt.getCircleShape()), statusTone.m7046colorvNxB06k(colors), null, 2, null), composerStartRestartGroup, 0);
                SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f9)), composerStartRestartGroup, 6);
                TextKt.m2373Text4IGK_g(statusLabel, SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(MenuKt.InTransitionDuration)), colors.m7166getTextSoft0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6238getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composerStartRestartGroup, (i6 & 14) | 196656, 3120, 55256);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                composer2.startReplaceGroup(1391377507);
                if (function2 != null) {
                    SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f)), composer2, 6);
                    IconKt.m1830Iconww6aTOc(SettingsKt.getSettings(Icons.Rounded.INSTANCE), "Settings", PaddingKt.m681padding3ABfNKs(ClickableKt.m269clickableXHw0xAI$default(ClipKt.clip(SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(26)), RoundedCornerShapeKt.getCircleShape()), false, null, null, function2, 7, null), Dp.m6299constructorimpl(3)), colors.m7166getTextSoft0d7_KjU(), composer2, 48, 0);
                }
                composer2.endReplaceGroup();
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
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function3 = function2;
                modifier4 = modifier6;
            } else {
                if (i7 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    function2 = null;
                } else {
                    function2 = function1;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(160445168, i6, -1, "com.dubtonic.player.ui.components.TopBar (Bars.kt:69)");
                }
                colors = DubTheme.INSTANCE.getColors(composerStartRestartGroup, 6);
                float f10 = 12;
                Modifier modifierM682paddingVpY3zN8 = PaddingKt.m682paddingVpY3zN4(WindowInsetsPadding_androidKt.statusBarsPadding(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null)), Dp.m6299constructorimpl(f10), Dp.m6299constructorimpl(8));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN8);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                composerM3333constructorimpl = Updater.m3333constructorimpl(composerStartRestartGroup);
                Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3333constructorimpl.getInserting()) {
                    composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                float f11 = 1;
                Modifier modifierM683paddingVpY3zN4$default3 = PaddingKt.m683paddingVpY3zN4$default(ShadowKt.m3504shadows4CzXII$default(BorderKt.m247borderxT4_qwU(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.m712height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), DubDimens.INSTANCE.m7175getTopbarD9Ej5fM()), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM())), colors.m7143getGlassStrong0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(f11), colors.m7141getGlassBorder0d7_KjU(), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM())), Dp.m6299constructorimpl(18), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM()), false, Color.m3839copywmQWz5c$default(Color.INSTANCE.m3866getBlack0d7_KjU(), 0.25f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3866getBlack0d7_KjU(), 0.25f, 0.0f, 0.0f, 0.0f, 14, null), 4, null), Dp.m6299constructorimpl(16), 0.0f, 2, null);
                Alignment.Vertical centerVertically5 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy5 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically5, composerStartRestartGroup, 48);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM683paddingVpY3zN4$default3);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3333constructorimpl2 = Updater.m3333constructorimpl(composerStartRestartGroup);
                Updater.m3340setimpl(composerM3333constructorimpl2, measurePolicyRowMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl2, currentCompositionLocalMap8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3333constructorimpl2.getInserting()) {
                    composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                } else {
                    composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m3340setimpl(composerM3333constructorimpl2, modifierMaterializeModifier8, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance5 = RowScopeInstance.INSTANCE;
                float f12 = 4;
                Modifier modifier7 = modifier3;
                TextKt.m2373Text4IGK_g("DubTonic", PaddingKt.m682paddingVpY3zN4(ClickableKt.m269clickableXHw0xAI$default(ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(Dp.m6299constructorimpl(f10))), false, null, null, onLogoClick, 7, null), Dp.m6299constructorimpl(f12), Dp.m6299constructorimpl(f12)), colors.m7164getText0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getExtraBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getTitleLarge(), composerStartRestartGroup, 196614, 0, 65496);
                SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance5, Modifier.INSTANCE, 1.0f, false, 2, null), composerStartRestartGroup, 0);
                Modifier modifierM247borderxT4_qwU3 = BorderKt.m247borderxT4_qwU(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM())), colors.m7160getSurface0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(f11), colors.m7146getLine0d7_KjU(), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM()));
                float fM6299constructorimpl3 = Dp.m6299constructorimpl(f10);
                f = 6;
                Modifier modifierM682paddingVpY3zN9 = PaddingKt.m682paddingVpY3zN4(modifierM247borderxT4_qwU3, fM6299constructorimpl3, Dp.m6299constructorimpl(f));
                Alignment.Vertical centerVertically6 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy6 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically6, composerStartRestartGroup, 48);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap9 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier9 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN9);
                constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor3);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3333constructorimpl3 = Updater.m3333constructorimpl(composerStartRestartGroup);
                Updater.m3340setimpl(composerM3333constructorimpl3, measurePolicyRowMeasurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl3, currentCompositionLocalMap9, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3333constructorimpl3.getInserting()) {
                    composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                } else {
                    composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.m3340setimpl(composerM3333constructorimpl3, modifierMaterializeModifier9, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance6 = RowScopeInstance.INSTANCE;
                float f13 = 7;
                BoxKt.Box(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f13)), RoundedCornerShapeKt.getCircleShape()), statusTone.m7046colorvNxB06k(colors), null, 2, null), composerStartRestartGroup, 0);
                SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f13)), composerStartRestartGroup, 6);
                TextKt.m2373Text4IGK_g(statusLabel, SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(MenuKt.InTransitionDuration)), colors.m7166getTextSoft0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6238getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composerStartRestartGroup, (i6 & 14) | 196656, 3120, 55256);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                composer2.startReplaceGroup(1391377507);
                if (function2 != null) {
                    SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f)), composer2, 6);
                    IconKt.m1830Iconww6aTOc(SettingsKt.getSettings(Icons.Rounded.INSTANCE), "Settings", PaddingKt.m681padding3ABfNKs(ClickableKt.m269clickableXHw0xAI$default(ClipKt.clip(SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(26)), RoundedCornerShapeKt.getCircleShape()), false, null, null, function2, 7, null), Dp.m6299constructorimpl(3)), colors.m7166getTextSoft0d7_KjU(), composer2, 48, 0);
                }
                composer2.endReplaceGroup();
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
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function3 = function2;
                modifier4 = modifier7;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return BarsKt.TopBar$lambda$3(statusLabel, statusTone, onLogoClick, modifier4, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        modifier2 = modifier;
        i4 = i2 & 16;
        if (i4 != 0) {
            if ((i & 24576) == 0) {
                function1 = function0;
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i5 = 16384;
                } else {
                    i5 = 8192;
                }
                i3 |= i5;
            }
            i6 = i3;
            if ((i6 & 9363) == 9362) {
                if (i7 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    function2 = null;
                } else {
                    function2 = function1;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(160445168, i6, -1, "com.dubtonic.player.ui.components.TopBar (Bars.kt:69)");
                }
                colors = DubTheme.INSTANCE.getColors(composerStartRestartGroup, 6);
                float f14 = 12;
                Modifier modifierM682paddingVpY3zN10 = PaddingKt.m682paddingVpY3zN4(WindowInsetsPadding_androidKt.statusBarsPadding(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null)), Dp.m6299constructorimpl(f14), Dp.m6299constructorimpl(8));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap10 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier10 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN10);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                composerM3333constructorimpl = Updater.m3333constructorimpl(composerStartRestartGroup);
                Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap10, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3333constructorimpl.getInserting()) {
                    composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier10, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                float f15 = 1;
                Modifier modifierM683paddingVpY3zN4$default4 = PaddingKt.m683paddingVpY3zN4$default(ShadowKt.m3504shadows4CzXII$default(BorderKt.m247borderxT4_qwU(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.m712height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), DubDimens.INSTANCE.m7175getTopbarD9Ej5fM()), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM())), colors.m7143getGlassStrong0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(f15), colors.m7141getGlassBorder0d7_KjU(), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM())), Dp.m6299constructorimpl(18), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM()), false, Color.m3839copywmQWz5c$default(Color.INSTANCE.m3866getBlack0d7_KjU(), 0.25f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3866getBlack0d7_KjU(), 0.25f, 0.0f, 0.0f, 0.0f, 14, null), 4, null), Dp.m6299constructorimpl(16), 0.0f, 2, null);
                Alignment.Vertical centerVertically7 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy7 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically7, composerStartRestartGroup, 48);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap11 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier11 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM683paddingVpY3zN4$default4);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3333constructorimpl2 = Updater.m3333constructorimpl(composerStartRestartGroup);
                Updater.m3340setimpl(composerM3333constructorimpl2, measurePolicyRowMeasurePolicy7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl2, currentCompositionLocalMap11, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3333constructorimpl2.getInserting()) {
                    composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                } else {
                    composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m3340setimpl(composerM3333constructorimpl2, modifierMaterializeModifier11, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance7 = RowScopeInstance.INSTANCE;
                float f16 = 4;
                Modifier modifier8 = modifier3;
                TextKt.m2373Text4IGK_g("DubTonic", PaddingKt.m682paddingVpY3zN4(ClickableKt.m269clickableXHw0xAI$default(ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(Dp.m6299constructorimpl(f14))), false, null, null, onLogoClick, 7, null), Dp.m6299constructorimpl(f16), Dp.m6299constructorimpl(f16)), colors.m7164getText0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getExtraBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getTitleLarge(), composerStartRestartGroup, 196614, 0, 65496);
                SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance7, Modifier.INSTANCE, 1.0f, false, 2, null), composerStartRestartGroup, 0);
                Modifier modifierM247borderxT4_qwU4 = BorderKt.m247borderxT4_qwU(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM())), colors.m7160getSurface0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(f15), colors.m7146getLine0d7_KjU(), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM()));
                float fM6299constructorimpl4 = Dp.m6299constructorimpl(f14);
                f = 6;
                Modifier modifierM682paddingVpY3zN11 = PaddingKt.m682paddingVpY3zN4(modifierM247borderxT4_qwU4, fM6299constructorimpl4, Dp.m6299constructorimpl(f));
                Alignment.Vertical centerVertically8 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy8 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically8, composerStartRestartGroup, 48);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap12 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier12 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN11);
                constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor3);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3333constructorimpl3 = Updater.m3333constructorimpl(composerStartRestartGroup);
                Updater.m3340setimpl(composerM3333constructorimpl3, measurePolicyRowMeasurePolicy8, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl3, currentCompositionLocalMap12, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3333constructorimpl3.getInserting()) {
                    composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                } else {
                    composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.m3340setimpl(composerM3333constructorimpl3, modifierMaterializeModifier12, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance8 = RowScopeInstance.INSTANCE;
                float f17 = 7;
                BoxKt.Box(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f17)), RoundedCornerShapeKt.getCircleShape()), statusTone.m7046colorvNxB06k(colors), null, 2, null), composerStartRestartGroup, 0);
                SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f17)), composerStartRestartGroup, 6);
                TextKt.m2373Text4IGK_g(statusLabel, SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(MenuKt.InTransitionDuration)), colors.m7166getTextSoft0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6238getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composerStartRestartGroup, (i6 & 14) | 196656, 3120, 55256);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                composer2.startReplaceGroup(1391377507);
                if (function2 != null) {
                    SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f)), composer2, 6);
                    IconKt.m1830Iconww6aTOc(SettingsKt.getSettings(Icons.Rounded.INSTANCE), "Settings", PaddingKt.m681padding3ABfNKs(ClickableKt.m269clickableXHw0xAI$default(ClipKt.clip(SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(26)), RoundedCornerShapeKt.getCircleShape()), false, null, null, function2, 7, null), Dp.m6299constructorimpl(3)), colors.m7166getTextSoft0d7_KjU(), composer2, 48, 0);
                }
                composer2.endReplaceGroup();
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
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function3 = function2;
                modifier4 = modifier8;
            } else {
                if (i7 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (i4 != 0) {
                    function2 = null;
                } else {
                    function2 = function1;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(160445168, i6, -1, "com.dubtonic.player.ui.components.TopBar (Bars.kt:69)");
                }
                colors = DubTheme.INSTANCE.getColors(composerStartRestartGroup, 6);
                float f18 = 12;
                Modifier modifierM682paddingVpY3zN12 = PaddingKt.m682paddingVpY3zN4(WindowInsetsPadding_androidKt.statusBarsPadding(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null)), Dp.m6299constructorimpl(f18), Dp.m6299constructorimpl(8));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy5 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap13 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier13 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN12);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                composerM3333constructorimpl = Updater.m3333constructorimpl(composerStartRestartGroup);
                Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap13, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3333constructorimpl.getInserting()) {
                    composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier13, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                float f19 = 1;
                Modifier modifierM683paddingVpY3zN4$default5 = PaddingKt.m683paddingVpY3zN4$default(ShadowKt.m3504shadows4CzXII$default(BorderKt.m247borderxT4_qwU(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.m712height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), DubDimens.INSTANCE.m7175getTopbarD9Ej5fM()), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM())), colors.m7143getGlassStrong0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(f19), colors.m7141getGlassBorder0d7_KjU(), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM())), Dp.m6299constructorimpl(18), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM()), false, Color.m3839copywmQWz5c$default(Color.INSTANCE.m3866getBlack0d7_KjU(), 0.25f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3866getBlack0d7_KjU(), 0.25f, 0.0f, 0.0f, 0.0f, 14, null), 4, null), Dp.m6299constructorimpl(16), 0.0f, 2, null);
                Alignment.Vertical centerVertically9 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy9 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically9, composerStartRestartGroup, 48);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap14 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier14 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM683paddingVpY3zN4$default5);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3333constructorimpl2 = Updater.m3333constructorimpl(composerStartRestartGroup);
                Updater.m3340setimpl(composerM3333constructorimpl2, measurePolicyRowMeasurePolicy9, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl2, currentCompositionLocalMap14, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3333constructorimpl2.getInserting()) {
                    composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                } else {
                    composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m3340setimpl(composerM3333constructorimpl2, modifierMaterializeModifier14, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance9 = RowScopeInstance.INSTANCE;
                float f110 = 4;
                Modifier modifier9 = modifier3;
                TextKt.m2373Text4IGK_g("DubTonic", PaddingKt.m682paddingVpY3zN4(ClickableKt.m269clickableXHw0xAI$default(ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(Dp.m6299constructorimpl(f18))), false, null, null, onLogoClick, 7, null), Dp.m6299constructorimpl(f110), Dp.m6299constructorimpl(f110)), colors.m7164getText0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getExtraBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getTitleLarge(), composerStartRestartGroup, 196614, 0, 65496);
                SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance9, Modifier.INSTANCE, 1.0f, false, 2, null), composerStartRestartGroup, 0);
                Modifier modifierM247borderxT4_qwU5 = BorderKt.m247borderxT4_qwU(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM())), colors.m7160getSurface0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(f19), colors.m7146getLine0d7_KjU(), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM()));
                float fM6299constructorimpl5 = Dp.m6299constructorimpl(f18);
                f = 6;
                Modifier modifierM682paddingVpY3zN13 = PaddingKt.m682paddingVpY3zN4(modifierM247borderxT4_qwU5, fM6299constructorimpl5, Dp.m6299constructorimpl(f));
                Alignment.Vertical centerVertically10 = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy10 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically10, composerStartRestartGroup, 48);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap15 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier15 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN13);
                constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor3);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3333constructorimpl3 = Updater.m3333constructorimpl(composerStartRestartGroup);
                Updater.m3340setimpl(composerM3333constructorimpl3, measurePolicyRowMeasurePolicy10, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl3, currentCompositionLocalMap15, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3333constructorimpl3.getInserting()) {
                    composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                } else {
                    composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.m3340setimpl(composerM3333constructorimpl3, modifierMaterializeModifier15, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance10 = RowScopeInstance.INSTANCE;
                float f111 = 7;
                BoxKt.Box(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f111)), RoundedCornerShapeKt.getCircleShape()), statusTone.m7046colorvNxB06k(colors), null, 2, null), composerStartRestartGroup, 0);
                SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f111)), composerStartRestartGroup, 6);
                TextKt.m2373Text4IGK_g(statusLabel, SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(MenuKt.InTransitionDuration)), colors.m7166getTextSoft0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6238getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composerStartRestartGroup, (i6 & 14) | 196656, 3120, 55256);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composer2 = composerStartRestartGroup;
                composer2.startReplaceGroup(1391377507);
                if (function2 != null) {
                    SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f)), composer2, 6);
                    IconKt.m1830Iconww6aTOc(SettingsKt.getSettings(Icons.Rounded.INSTANCE), "Settings", PaddingKt.m681padding3ABfNKs(ClickableKt.m269clickableXHw0xAI$default(ClipKt.clip(SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(26)), RoundedCornerShapeKt.getCircleShape()), false, null, null, function2, 7, null), Dp.m6299constructorimpl(3)), colors.m7166getTextSoft0d7_KjU(), composer2, 48, 0);
                }
                composer2.endReplaceGroup();
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
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function3 = function2;
                modifier4 = modifier9;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return BarsKt.TopBar$lambda$3(statusLabel, statusTone, onLogoClick, modifier4, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        function1 = function0;
        i6 = i3;
        if ((i6 & 9363) == 9362) {
            if (i7 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i4 != 0) {
                function2 = null;
            } else {
                function2 = function1;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(160445168, i6, -1, "com.dubtonic.player.ui.components.TopBar (Bars.kt:69)");
            }
            colors = DubTheme.INSTANCE.getColors(composerStartRestartGroup, 6);
            float f112 = 12;
            Modifier modifierM682paddingVpY3zN14 = PaddingKt.m682paddingVpY3zN4(WindowInsetsPadding_androidKt.statusBarsPadding(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null)), Dp.m6299constructorimpl(f112), Dp.m6299constructorimpl(8));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy6 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap16 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier16 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN14);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
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
            composerM3333constructorimpl = Updater.m3333constructorimpl(composerStartRestartGroup);
            Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap16, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3333constructorimpl.getInserting()) {
                composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier16, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
            float f113 = 1;
            Modifier modifierM683paddingVpY3zN4$default6 = PaddingKt.m683paddingVpY3zN4$default(ShadowKt.m3504shadows4CzXII$default(BorderKt.m247borderxT4_qwU(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.m712height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), DubDimens.INSTANCE.m7175getTopbarD9Ej5fM()), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM())), colors.m7143getGlassStrong0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(f113), colors.m7141getGlassBorder0d7_KjU(), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM())), Dp.m6299constructorimpl(18), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM()), false, Color.m3839copywmQWz5c$default(Color.INSTANCE.m3866getBlack0d7_KjU(), 0.25f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3866getBlack0d7_KjU(), 0.25f, 0.0f, 0.0f, 0.0f, 14, null), 4, null), Dp.m6299constructorimpl(16), 0.0f, 2, null);
            Alignment.Vertical centerVertically11 = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy11 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically11, composerStartRestartGroup, 48);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap17 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier17 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM683paddingVpY3zN4$default6);
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerM3333constructorimpl2 = Updater.m3333constructorimpl(composerStartRestartGroup);
            Updater.m3340setimpl(composerM3333constructorimpl2, measurePolicyRowMeasurePolicy11, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl2, currentCompositionLocalMap17, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3333constructorimpl2.getInserting()) {
                composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            } else {
                composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m3340setimpl(composerM3333constructorimpl2, modifierMaterializeModifier17, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance11 = RowScopeInstance.INSTANCE;
            float f114 = 4;
            Modifier modifier10 = modifier3;
            TextKt.m2373Text4IGK_g("DubTonic", PaddingKt.m682paddingVpY3zN4(ClickableKt.m269clickableXHw0xAI$default(ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(Dp.m6299constructorimpl(f112))), false, null, null, onLogoClick, 7, null), Dp.m6299constructorimpl(f114), Dp.m6299constructorimpl(f114)), colors.m7164getText0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getExtraBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getTitleLarge(), composerStartRestartGroup, 196614, 0, 65496);
            SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance11, Modifier.INSTANCE, 1.0f, false, 2, null), composerStartRestartGroup, 0);
            Modifier modifierM247borderxT4_qwU6 = BorderKt.m247borderxT4_qwU(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM())), colors.m7160getSurface0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(f113), colors.m7146getLine0d7_KjU(), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM()));
            float fM6299constructorimpl6 = Dp.m6299constructorimpl(f112);
            f = 6;
            Modifier modifierM682paddingVpY3zN15 = PaddingKt.m682paddingVpY3zN4(modifierM247borderxT4_qwU6, fM6299constructorimpl6, Dp.m6299constructorimpl(f));
            Alignment.Vertical centerVertically12 = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy12 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically12, composerStartRestartGroup, 48);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap18 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier18 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN15);
            constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor3);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerM3333constructorimpl3 = Updater.m3333constructorimpl(composerStartRestartGroup);
            Updater.m3340setimpl(composerM3333constructorimpl3, measurePolicyRowMeasurePolicy12, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl3, currentCompositionLocalMap18, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3333constructorimpl3.getInserting()) {
                composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            } else {
                composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m3340setimpl(composerM3333constructorimpl3, modifierMaterializeModifier18, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance12 = RowScopeInstance.INSTANCE;
            float f115 = 7;
            BoxKt.Box(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f115)), RoundedCornerShapeKt.getCircleShape()), statusTone.m7046colorvNxB06k(colors), null, 2, null), composerStartRestartGroup, 0);
            SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f115)), composerStartRestartGroup, 6);
            TextKt.m2373Text4IGK_g(statusLabel, SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(MenuKt.InTransitionDuration)), colors.m7166getTextSoft0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6238getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composerStartRestartGroup, (i6 & 14) | 196656, 3120, 55256);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composer2 = composerStartRestartGroup;
            composer2.startReplaceGroup(1391377507);
            if (function2 != null) {
                SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f)), composer2, 6);
                IconKt.m1830Iconww6aTOc(SettingsKt.getSettings(Icons.Rounded.INSTANCE), "Settings", PaddingKt.m681padding3ABfNKs(ClickableKt.m269clickableXHw0xAI$default(ClipKt.clip(SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(26)), RoundedCornerShapeKt.getCircleShape()), false, null, null, function2, 7, null), Dp.m6299constructorimpl(3)), colors.m7166getTextSoft0d7_KjU(), composer2, 48, 0);
            }
            composer2.endReplaceGroup();
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
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function3 = function2;
            modifier4 = modifier10;
        } else {
            if (i7 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (i4 != 0) {
                function2 = null;
            } else {
                function2 = function1;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(160445168, i6, -1, "com.dubtonic.player.ui.components.TopBar (Bars.kt:69)");
            }
            colors = DubTheme.INSTANCE.getColors(composerStartRestartGroup, 6);
            float f116 = 12;
            Modifier modifierM682paddingVpY3zN16 = PaddingKt.m682paddingVpY3zN4(WindowInsetsPadding_androidKt.statusBarsPadding(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null)), Dp.m6299constructorimpl(f116), Dp.m6299constructorimpl(8));
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy7 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap19 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier19 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN16);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
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
            composerM3333constructorimpl = Updater.m3333constructorimpl(composerStartRestartGroup);
            Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap19, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3333constructorimpl.getInserting()) {
                composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier19, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
            float f117 = 1;
            Modifier modifierM683paddingVpY3zN4$default7 = PaddingKt.m683paddingVpY3zN4$default(ShadowKt.m3504shadows4CzXII$default(BorderKt.m247borderxT4_qwU(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.m712height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), DubDimens.INSTANCE.m7175getTopbarD9Ej5fM()), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM())), colors.m7143getGlassStrong0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(f117), colors.m7141getGlassBorder0d7_KjU(), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM())), Dp.m6299constructorimpl(18), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM()), false, Color.m3839copywmQWz5c$default(Color.INSTANCE.m3866getBlack0d7_KjU(), 0.25f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3866getBlack0d7_KjU(), 0.25f, 0.0f, 0.0f, 0.0f, 14, null), 4, null), Dp.m6299constructorimpl(16), 0.0f, 2, null);
            Alignment.Vertical centerVertically13 = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy13 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically13, composerStartRestartGroup, 48);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap110 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier110 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM683paddingVpY3zN4$default7);
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerM3333constructorimpl2 = Updater.m3333constructorimpl(composerStartRestartGroup);
            Updater.m3340setimpl(composerM3333constructorimpl2, measurePolicyRowMeasurePolicy13, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl2, currentCompositionLocalMap110, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3333constructorimpl2.getInserting()) {
                composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            } else {
                composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m3340setimpl(composerM3333constructorimpl2, modifierMaterializeModifier110, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance13 = RowScopeInstance.INSTANCE;
            float f118 = 4;
            Modifier modifier11 = modifier3;
            TextKt.m2373Text4IGK_g("DubTonic", PaddingKt.m682paddingVpY3zN4(ClickableKt.m269clickableXHw0xAI$default(ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(Dp.m6299constructorimpl(f116))), false, null, null, onLogoClick, 7, null), Dp.m6299constructorimpl(f118), Dp.m6299constructorimpl(f118)), colors.m7164getText0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getExtraBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getTitleLarge(), composerStartRestartGroup, 196614, 0, 65496);
            SpacerKt.Spacer(RowScope.weight$default(rowScopeInstance13, Modifier.INSTANCE, 1.0f, false, 2, null), composerStartRestartGroup, 0);
            Modifier modifierM247borderxT4_qwU7 = BorderKt.m247borderxT4_qwU(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM())), colors.m7160getSurface0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(f117), colors.m7146getLine0d7_KjU(), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM()));
            float fM6299constructorimpl7 = Dp.m6299constructorimpl(f116);
            f = 6;
            Modifier modifierM682paddingVpY3zN17 = PaddingKt.m682paddingVpY3zN4(modifierM247borderxT4_qwU7, fM6299constructorimpl7, Dp.m6299constructorimpl(f));
            Alignment.Vertical centerVertically14 = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy14 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically14, composerStartRestartGroup, 48);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap111 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier111 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN17);
            constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor3);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerM3333constructorimpl3 = Updater.m3333constructorimpl(composerStartRestartGroup);
            Updater.m3340setimpl(composerM3333constructorimpl3, measurePolicyRowMeasurePolicy14, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl3, currentCompositionLocalMap111, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3333constructorimpl3.getInserting()) {
                composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            } else {
                composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m3340setimpl(composerM3333constructorimpl3, modifierMaterializeModifier111, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance14 = RowScopeInstance.INSTANCE;
            float f119 = 7;
            BoxKt.Box(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f119)), RoundedCornerShapeKt.getCircleShape()), statusTone.m7046colorvNxB06k(colors), null, 2, null), composerStartRestartGroup, 0);
            SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f119)), composerStartRestartGroup, 6);
            TextKt.m2373Text4IGK_g(statusLabel, SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(MenuKt.InTransitionDuration)), colors.m7166getTextSoft0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6238getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composerStartRestartGroup, (i6 & 14) | 196656, 3120, 55256);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composer2 = composerStartRestartGroup;
            composer2.startReplaceGroup(1391377507);
            if (function2 != null) {
                SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f)), composer2, 6);
                IconKt.m1830Iconww6aTOc(SettingsKt.getSettings(Icons.Rounded.INSTANCE), "Settings", PaddingKt.m681padding3ABfNKs(ClickableKt.m269clickableXHw0xAI$default(ClipKt.clip(SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(26)), RoundedCornerShapeKt.getCircleShape()), false, null, null, function2, 7, null), Dp.m6299constructorimpl(3)), colors.m7166getTextSoft0d7_KjU(), composer2, 48, 0);
            }
            composer2.endReplaceGroup();
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
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function3 = function2;
            modifier4 = modifier11;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BarsKt.TopBar$lambda$3(statusLabel, statusTone, onLogoClick, modifier4, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final List<NavItem> getNavItems() {
        return navItems;
    }

    /* JADX WARN: Code duplicated, block: B:102:0x0386  */
    /* JADX WARN: Code duplicated, block: B:104:0x038e  */
    /* JADX WARN: Code duplicated, block: B:107:0x03fe  */
    /* JADX WARN: Code duplicated, block: B:110:0x040a  */
    /* JADX WARN: Code duplicated, block: B:111:0x040e  */
    /* JADX WARN: Code duplicated, block: B:114:0x0433  */
    /* JADX WARN: Code duplicated, block: B:116:0x0441  */
    /* JADX WARN: Code duplicated, block: B:119:0x047a  */
    /* JADX WARN: Code duplicated, block: B:120:0x047f  */
    /* JADX WARN: Code duplicated, block: B:123:0x04d6  */
    /* JADX WARN: Code duplicated, block: B:126:0x04e2  */
    /* JADX WARN: Code duplicated, block: B:127:0x04e6  */
    /* JADX WARN: Code duplicated, block: B:130:0x050b  */
    /* JADX WARN: Code duplicated, block: B:132:0x0519  */
    /* JADX WARN: Code duplicated, block: B:135:0x0546  */
    /* JADX WARN: Code duplicated, block: B:136:0x054b  */
    /* JADX WARN: Code duplicated, block: B:139:0x05ae  */
    /* JADX WARN: Code duplicated, block: B:140:0x05b3  */
    /* JADX WARN: Code duplicated, block: B:143:0x05bc  */
    /* JADX WARN: Code duplicated, block: B:144:0x05c1  */
    /* JADX WARN: Code duplicated, block: B:148:0x0647  */
    /* JADX WARN: Code duplicated, block: B:151:0x0650  */
    /* JADX WARN: Code duplicated, block: B:156:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:40:0x0078 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:41:0x007a  */
    /* JADX WARN: Code duplicated, block: B:42:0x0080  */
    /* JADX WARN: Code duplicated, block: B:45:0x0087  */
    /* JADX WARN: Code duplicated, block: B:48:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:51:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:52:0x0100  */
    /* JADX WARN: Code duplicated, block: B:55:0x0125  */
    /* JADX WARN: Code duplicated, block: B:57:0x0133  */
    /* JADX WARN: Code duplicated, block: B:60:0x0240  */
    /* JADX WARN: Code duplicated, block: B:63:0x024c  */
    /* JADX WARN: Code duplicated, block: B:64:0x0250  */
    /* JADX WARN: Code duplicated, block: B:67:0x0275  */
    /* JADX WARN: Code duplicated, block: B:69:0x0283  */
    /* JADX WARN: Code duplicated, block: B:73:0x02bc  */
    /* JADX WARN: Code duplicated, block: B:75:0x02cf  */
    /* JADX WARN: Code duplicated, block: B:77:0x02d8  */
    /* JADX WARN: Code duplicated, block: B:79:0x02e4  */
    /* JADX WARN: Code duplicated, block: B:80:0x02e9  */
    /* JADX WARN: Code duplicated, block: B:82:0x02f5  */
    /* JADX WARN: Code duplicated, block: B:83:0x0309  */
    /* JADX WARN: Code duplicated, block: B:85:0x0315  */
    /* JADX WARN: Code duplicated, block: B:86:0x031c  */
    /* JADX WARN: Code duplicated, block: B:88:0x032b  */
    /* JADX WARN: Code duplicated, block: B:95:0x0346  */
    /* JADX WARN: Code duplicated, block: B:98:0x0378  */
    /* JADX WARN: Code duplicated, block: B:99:0x037a  */
    public static final void BottomNav(final String activeRoute, final Function1<? super String, Unit> onNavigate, Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        Modifier modifier3;
        DubColors colors;
        float f;
        Modifier modifier4;
        String str;
        String str2;
        int currentCompositeKeyHash;
        Function0<ComposeUiNode> constructor;
        String str3;
        Composer composerM3333constructorimpl;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
        String str4;
        int currentCompositeKeyHash2;
        Function0<ComposeUiNode> constructor2;
        Composer composerM3333constructorimpl2;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2;
        RowScopeInstance rowScopeInstance;
        Modifier modifier5;
        boolean z;
        boolean zAreEqual;
        boolean z2;
        boolean zChanged;
        Object objRememberedValue;
        int currentCompositeKeyHash3;
        Function0<ComposeUiNode> constructor3;
        Composer composerM3333constructorimpl3;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3;
        long jM3875getTransparent0d7_KjU;
        int currentCompositeKeyHash4;
        Function0<ComposeUiNode> constructor4;
        Composer composerM3333constructorimpl4;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4;
        long jM7166getTextSoft0d7_KjU;
        long jM7165getTextFaint0d7_KjU;
        FontWeight.Companion companion;
        FontWeight normal;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(activeRoute, "activeRoute");
        Intrinsics.checkNotNullParameter(onNavigate, "onNavigate");
        Composer composerStartRestartGroup = composer.startRestartGroup(-318631305);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(activeRoute) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onNavigate) ? 32 : 16;
        }
        int i5 = i2 & 4;
        if (i5 == 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
            }
            i4 = i3;
            if ((i4 & 147) == 146 || !composerStartRestartGroup.getSkipping()) {
                if (i5 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-318631305, i4, -1, "com.dubtonic.player.ui.components.BottomNav (Bars.kt:176)");
                }
                colors = DubTheme.INSTANCE.getColors(composerStartRestartGroup, 6);
                f = 14;
                Modifier modifierM682paddingVpY3zN4 = PaddingKt.m682paddingVpY3zN4(WindowInsetsPadding_androidKt.navigationBarsPadding(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null)), Dp.m6299constructorimpl(f), Dp.m6299constructorimpl(10));
                Alignment center = Alignment.INSTANCE.getCenter();
                modifier4 = modifier3;
                str = "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo";
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
                str2 = "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh";
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN4);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
                str3 = "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp";
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
                composerM3333constructorimpl = Updater.m3333constructorimpl(composerStartRestartGroup);
                Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3333constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                str4 = "C73@3429L9:Box.kt#2w3rfo";
                Modifier modifierM683paddingVpY3zN4$default = PaddingKt.m683paddingVpY3zN4$default(ShadowKt.m3504shadows4CzXII$default(BorderKt.m247borderxT4_qwU(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.m712height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), DubDimens.INSTANCE.m7168getNavD9Ej5fM()), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7169getRadiusLgD9Ej5fM())), colors.m7143getGlassStrong0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(1), colors.m7141getGlassBorder0d7_KjU(), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7169getRadiusLgD9Ej5fM())), Dp.m6299constructorimpl(24), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7169getRadiusLgD9Ej5fM()), false, Color.m3839copywmQWz5c$default(Color.INSTANCE.m3866getBlack0d7_KjU(), 0.3f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3866getBlack0d7_KjU(), 0.3f, 0.0f, 0.0f, 0.0f, 14, null), 4, null), Dp.m6299constructorimpl(4), 0.0f, 2, null);
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                Arrangement.HorizontalOrVertical spaceEvenly = Arrangement.INSTANCE.getSpaceEvenly();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(spaceEvenly, centerVertically, composerStartRestartGroup, 54);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM683paddingVpY3zN4$default);
                constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3333constructorimpl2 = Updater.m3333constructorimpl(composerStartRestartGroup);
                Updater.m3340setimpl(composerM3333constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3333constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m3340setimpl(composerM3333constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                rowScopeInstance = RowScopeInstance.INSTANCE;
                composerStartRestartGroup.startReplaceGroup(1490191288);
                for (final NavItem navItem : navItems) {
                    if (Intrinsics.areEqual(navItem.getRoute(), "home")) {
                        zAreEqual = Intrinsics.areEqual(activeRoute, "home");
                    } else if (Intrinsics.areEqual(navItem.getRoute(), "search")) {
                        zAreEqual = Intrinsics.areEqual(activeRoute, "search");
                    } else if (Intrinsics.areEqual(navItem.getRoute(), "playlists")) {
                        zAreEqual = SetsKt.setOf((Object[]) new String[]{"playlists", "playlist"}).contains(activeRoute);
                    } else {
                        if (Intrinsics.areEqual(navItem.getRoute(), "settings")) {
                            zAreEqual = Intrinsics.areEqual(activeRoute, "settings");
                        } else if (StringsKt.startsWith$default(navItem.getRoute(), "library", false, 2, (Object) null) || !(StringsKt.startsWith$default(activeRoute, "library", false, 2, (Object) null) || StringsKt.startsWith$default(activeRoute, "album", false, 2, (Object) null) || StringsKt.startsWith$default(activeRoute, "artist", false, 2, (Object) null))) {
                            z = false;
                        } else {
                            z = true;
                        }
                        Modifier modifierClip = ClipKt.clip(RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM()));
                        composerStartRestartGroup.startReplaceGroup(1229323337);
                        if ((i4 & 112) == 32) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        zChanged = z2 | composerStartRestartGroup.changed(navItem);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (!zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function0() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda5
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return BarsKt.BottomNav$lambda$10$lambda$9$lambda$8$lambda$5$lambda$4(onNavigate, navItem);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        Modifier modifierM683paddingVpY3zN4$default2 = PaddingKt.m683paddingVpY3zN4$default(ClickableKt.m269clickableXHw0xAI$default(modifierClip, false, null, null, (Function0) objRememberedValue, 7, null), 0.0f, Dp.m6299constructorimpl(8), 1, null);
                        Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                        Arrangement.HorizontalOrVertical center2 = Arrangement.INSTANCE.getCenter();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center2, centerHorizontally, composerStartRestartGroup, 54);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, str2);
                        currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM683paddingVpY3zN4$default2);
                        constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, str3);
                        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor3);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM3333constructorimpl3 = Updater.m3333constructorimpl(composerStartRestartGroup);
                        Updater.m3340setimpl(composerM3333constructorimpl3, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3340setimpl(composerM3333constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!composerM3333constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                            composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                            composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                        }
                        Updater.m3340setimpl(composerM3333constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                        Modifier modifierClip2 = ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM()));
                        if (z) {
                            jM3875getTransparent0d7_KjU = colors.m7155getSecondary0d7_KjU();
                        } else {
                            jM3875getTransparent0d7_KjU = Color.INSTANCE.m3875getTransparent0d7_KjU();
                        }
                        Modifier modifierM682paddingVpY3zN5 = PaddingKt.m682paddingVpY3zN4(BackgroundKt.m236backgroundbw27NRU$default(modifierClip2, jM3875getTransparent0d7_KjU, null, 2, null), Dp.m6299constructorimpl(f), Dp.m6299constructorimpl(3));
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, str);
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, str2);
                        currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN5);
                        constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, str3);
                        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor4);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM3333constructorimpl4 = Updater.m3333constructorimpl(composerStartRestartGroup);
                        Updater.m3340setimpl(composerM3333constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3340setimpl(composerM3333constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (!composerM3333constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                            composerM3333constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                            composerM3333constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                        }
                        Updater.m3340setimpl(composerM3333constructorimpl4, modifierMaterializeModifier4, ComposeUiNode.INSTANCE.getSetModifier());
                        String str5 = str4;
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, str5);
                        BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                        ImageVector icon = navItem.getIcon();
                        String label = navItem.getLabel();
                        if (z) {
                            jM7166getTextSoft0d7_KjU = colors.m7149getOnSecondary0d7_KjU();
                        } else {
                            jM7166getTextSoft0d7_KjU = colors.m7166getTextSoft0d7_KjU();
                        }
                        String str6 = str2;
                        float f2 = f;
                        String str7 = str3;
                        String str8 = str;
                        Modifier modifier6 = modifier4;
                        IconKt.m1830Iconww6aTOc(icon, label, SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(22)), jM7166getTextSoft0d7_KjU, composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composerStartRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        String label2 = navItem.getLabel();
                        TextStyle bodySmall = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall();
                        if (z) {
                            jM7165getTextFaint0d7_KjU = colors.m7164getText0d7_KjU();
                        } else {
                            jM7165getTextFaint0d7_KjU = colors.m7165getTextFaint0d7_KjU();
                        }
                        long j = jM7165getTextFaint0d7_KjU;
                        companion = FontWeight.INSTANCE;
                        if (z) {
                            normal = companion.getSemiBold();
                        } else {
                            normal = companion.getNormal();
                        }
                        TextKt.m2373Text4IGK_g(label2, (Modifier) null, j, 0L, (FontStyle) null, normal, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, bodySmall, composerStartRestartGroup, 0, 0, 65498);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        composerStartRestartGroup.endNode();
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        str2 = str6;
                        f = f2;
                        str3 = str7;
                        modifier4 = modifier6;
                        str = str8;
                        i4 = i4;
                        str4 = str5;
                    }
                    z = zAreEqual;
                    Modifier modifierClip3 = ClipKt.clip(RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM()));
                    composerStartRestartGroup.startReplaceGroup(1229323337);
                    if ((i4 & 112) == 32) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    zChanged = z2 | composerStartRestartGroup.changed(navItem);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue = new Function0() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return BarsKt.BottomNav$lambda$10$lambda$9$lambda$8$lambda$5$lambda$4(onNavigate, navItem);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new Function0() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return BarsKt.BottomNav$lambda$10$lambda$9$lambda$8$lambda$5$lambda$4(onNavigate, navItem);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    Modifier modifierM683paddingVpY3zN4$default3 = PaddingKt.m683paddingVpY3zN4$default(ClickableKt.m269clickableXHw0xAI$default(modifierClip3, false, null, null, (Function0) objRememberedValue, 7, null), 0.0f, Dp.m6299constructorimpl(8), 1, null);
                    Alignment.Horizontal centerHorizontally2 = Alignment.INSTANCE.getCenterHorizontally();
                    Arrangement.HorizontalOrVertical center3 = Arrangement.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(center3, centerHorizontally2, composerStartRestartGroup, 54);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, str2);
                    currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM683paddingVpY3zN4$default3);
                    constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, str3);
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor3);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3333constructorimpl3 = Updater.m3333constructorimpl(composerStartRestartGroup);
                    Updater.m3340setimpl(composerM3333constructorimpl3, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3340setimpl(composerM3333constructorimpl3, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3333constructorimpl3.getInserting()) {
                        composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                        composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                    } else {
                        composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                        composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                    }
                    Updater.m3340setimpl(composerM3333constructorimpl3, modifierMaterializeModifier5, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                    Modifier modifierClip4 = ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM()));
                    if (z) {
                        jM3875getTransparent0d7_KjU = colors.m7155getSecondary0d7_KjU();
                    } else {
                        jM3875getTransparent0d7_KjU = Color.INSTANCE.m3875getTransparent0d7_KjU();
                    }
                    Modifier modifierM682paddingVpY3zN6 = PaddingKt.m682paddingVpY3zN4(BackgroundKt.m236backgroundbw27NRU$default(modifierClip4, jM3875getTransparent0d7_KjU, null, 2, null), Dp.m6299constructorimpl(f), Dp.m6299constructorimpl(3));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, str);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, str2);
                    currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN6);
                    constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, str3);
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor4);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3333constructorimpl4 = Updater.m3333constructorimpl(composerStartRestartGroup);
                    Updater.m3340setimpl(composerM3333constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3340setimpl(composerM3333constructorimpl4, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3333constructorimpl4.getInserting()) {
                        composerM3333constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                        composerM3333constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                    } else {
                        composerM3333constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                        composerM3333constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                    }
                    Updater.m3340setimpl(composerM3333constructorimpl4, modifierMaterializeModifier6, ComposeUiNode.INSTANCE.getSetModifier());
                    String str9 = str4;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, str9);
                    BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                    ImageVector icon2 = navItem.getIcon();
                    String label3 = navItem.getLabel();
                    if (z) {
                        jM7166getTextSoft0d7_KjU = colors.m7149getOnSecondary0d7_KjU();
                    } else {
                        jM7166getTextSoft0d7_KjU = colors.m7166getTextSoft0d7_KjU();
                    }
                    String str10 = str2;
                    float f3 = f;
                    String str11 = str3;
                    String str12 = str;
                    Modifier modifier7 = modifier4;
                    IconKt.m1830Iconww6aTOc(icon2, label3, SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(22)), jM7166getTextSoft0d7_KjU, composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    String label4 = navItem.getLabel();
                    TextStyle bodySmall2 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall();
                    if (z) {
                        jM7165getTextFaint0d7_KjU = colors.m7164getText0d7_KjU();
                    } else {
                        jM7165getTextFaint0d7_KjU = colors.m7165getTextFaint0d7_KjU();
                    }
                    long j2 = jM7165getTextFaint0d7_KjU;
                    companion = FontWeight.INSTANCE;
                    if (z) {
                        normal = companion.getSemiBold();
                    } else {
                        normal = companion.getNormal();
                    }
                    TextKt.m2373Text4IGK_g(label4, (Modifier) null, j2, 0L, (FontStyle) null, normal, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, bodySmall2, composerStartRestartGroup, 0, 0, 65498);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    str2 = str10;
                    f = f3;
                    str3 = str11;
                    modifier4 = modifier7;
                    str = str12;
                    i4 = i4;
                    str4 = str9;
                }
                modifier5 = modifier4;
                composerStartRestartGroup.endReplaceGroup();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
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
                modifier5 = modifier2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier8 = modifier5;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return BarsKt.BottomNav$lambda$11(activeRoute, onNavigate, modifier8, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        modifier2 = modifier;
        i4 = i3;
        if ((i4 & 147) == 146) {
            if (i5 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-318631305, i4, -1, "com.dubtonic.player.ui.components.BottomNav (Bars.kt:176)");
            }
            colors = DubTheme.INSTANCE.getColors(composerStartRestartGroup, 6);
            f = 14;
            Modifier modifierM682paddingVpY3zN7 = PaddingKt.m682paddingVpY3zN4(WindowInsetsPadding_androidKt.navigationBarsPadding(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null)), Dp.m6299constructorimpl(f), Dp.m6299constructorimpl(10));
            Alignment center4 = Alignment.INSTANCE.getCenter();
            modifier4 = modifier3;
            str = "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo";
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(center4, false);
            str2 = "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh";
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN7);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            str3 = "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp";
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
            composerM3333constructorimpl = Updater.m3333constructorimpl(composerStartRestartGroup);
            Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3333constructorimpl.getInserting()) {
                composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier7, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
            str4 = "C73@3429L9:Box.kt#2w3rfo";
            Modifier modifierM683paddingVpY3zN4$default4 = PaddingKt.m683paddingVpY3zN4$default(ShadowKt.m3504shadows4CzXII$default(BorderKt.m247borderxT4_qwU(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.m712height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), DubDimens.INSTANCE.m7168getNavD9Ej5fM()), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7169getRadiusLgD9Ej5fM())), colors.m7143getGlassStrong0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(1), colors.m7141getGlassBorder0d7_KjU(), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7169getRadiusLgD9Ej5fM())), Dp.m6299constructorimpl(24), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7169getRadiusLgD9Ej5fM()), false, Color.m3839copywmQWz5c$default(Color.INSTANCE.m3866getBlack0d7_KjU(), 0.3f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3866getBlack0d7_KjU(), 0.3f, 0.0f, 0.0f, 0.0f, 14, null), 4, null), Dp.m6299constructorimpl(4), 0.0f, 2, null);
            Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
            Arrangement.HorizontalOrVertical spaceEvenly2 = Arrangement.INSTANCE.getSpaceEvenly();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(spaceEvenly2, centerVertically2, composerStartRestartGroup, 54);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM683paddingVpY3zN4$default4);
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerM3333constructorimpl2 = Updater.m3333constructorimpl(composerStartRestartGroup);
            Updater.m3340setimpl(composerM3333constructorimpl2, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl2, currentCompositionLocalMap8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3333constructorimpl2.getInserting()) {
                composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            } else {
                composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m3340setimpl(composerM3333constructorimpl2, modifierMaterializeModifier8, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            rowScopeInstance = RowScopeInstance.INSTANCE;
            composerStartRestartGroup.startReplaceGroup(1490191288);
            while (r34.hasNext()) {
                if (Intrinsics.areEqual(navItem.getRoute(), "home")) {
                    zAreEqual = Intrinsics.areEqual(activeRoute, "home");
                } else if (Intrinsics.areEqual(navItem.getRoute(), "search")) {
                    zAreEqual = Intrinsics.areEqual(activeRoute, "search");
                } else if (Intrinsics.areEqual(navItem.getRoute(), "playlists")) {
                    zAreEqual = SetsKt.setOf((Object[]) new String[]{"playlists", "playlist"}).contains(activeRoute);
                } else {
                    if (Intrinsics.areEqual(navItem.getRoute(), "settings")) {
                        zAreEqual = Intrinsics.areEqual(activeRoute, "settings");
                    } else if (StringsKt.startsWith$default(navItem.getRoute(), "library", false, 2, (Object) null)) {
                        z = false;
                    } else {
                        z = false;
                    }
                    Modifier modifierClip5 = ClipKt.clip(RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM()));
                    composerStartRestartGroup.startReplaceGroup(1229323337);
                    if ((i4 & 112) == 32) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    zChanged = z2 | composerStartRestartGroup.changed(navItem);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue = new Function0() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return BarsKt.BottomNav$lambda$10$lambda$9$lambda$8$lambda$5$lambda$4(onNavigate, navItem);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new Function0() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return BarsKt.BottomNav$lambda$10$lambda$9$lambda$8$lambda$5$lambda$4(onNavigate, navItem);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    Modifier modifierM683paddingVpY3zN4$default5 = PaddingKt.m683paddingVpY3zN4$default(ClickableKt.m269clickableXHw0xAI$default(modifierClip5, false, null, null, (Function0) objRememberedValue, 7, null), 0.0f, Dp.m6299constructorimpl(8), 1, null);
                    Alignment.Horizontal centerHorizontally3 = Alignment.INSTANCE.getCenterHorizontally();
                    Arrangement.HorizontalOrVertical center5 = Arrangement.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    MeasurePolicy measurePolicyColumnMeasurePolicy3 = ColumnKt.columnMeasurePolicy(center5, centerHorizontally3, composerStartRestartGroup, 54);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, str2);
                    currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap9 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier9 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM683paddingVpY3zN4$default5);
                    constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, str3);
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor3);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3333constructorimpl3 = Updater.m3333constructorimpl(composerStartRestartGroup);
                    Updater.m3340setimpl(composerM3333constructorimpl3, measurePolicyColumnMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3340setimpl(composerM3333constructorimpl3, currentCompositionLocalMap9, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3333constructorimpl3.getInserting()) {
                        composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                        composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                    } else {
                        composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                        composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                    }
                    Updater.m3340setimpl(composerM3333constructorimpl3, modifierMaterializeModifier9, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                    Modifier modifierClip6 = ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM()));
                    if (z) {
                        jM3875getTransparent0d7_KjU = colors.m7155getSecondary0d7_KjU();
                    } else {
                        jM3875getTransparent0d7_KjU = Color.INSTANCE.m3875getTransparent0d7_KjU();
                    }
                    Modifier modifierM682paddingVpY3zN8 = PaddingKt.m682paddingVpY3zN4(BackgroundKt.m236backgroundbw27NRU$default(modifierClip6, jM3875getTransparent0d7_KjU, null, 2, null), Dp.m6299constructorimpl(f), Dp.m6299constructorimpl(3));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, str);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy5 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, str2);
                    currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap10 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier10 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN8);
                    constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, str3);
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor4);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3333constructorimpl4 = Updater.m3333constructorimpl(composerStartRestartGroup);
                    Updater.m3340setimpl(composerM3333constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3340setimpl(composerM3333constructorimpl4, currentCompositionLocalMap10, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3333constructorimpl4.getInserting()) {
                        composerM3333constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                        composerM3333constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                    } else {
                        composerM3333constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                        composerM3333constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                    }
                    Updater.m3340setimpl(composerM3333constructorimpl4, modifierMaterializeModifier10, ComposeUiNode.INSTANCE.getSetModifier());
                    String str13 = str4;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, str13);
                    BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                    ImageVector icon3 = navItem.getIcon();
                    String label5 = navItem.getLabel();
                    if (z) {
                        jM7166getTextSoft0d7_KjU = colors.m7149getOnSecondary0d7_KjU();
                    } else {
                        jM7166getTextSoft0d7_KjU = colors.m7166getTextSoft0d7_KjU();
                    }
                    String str14 = str2;
                    float f4 = f;
                    String str15 = str3;
                    String str16 = str;
                    Modifier modifier9 = modifier4;
                    IconKt.m1830Iconww6aTOc(icon3, label5, SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(22)), jM7166getTextSoft0d7_KjU, composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    String label6 = navItem.getLabel();
                    TextStyle bodySmall3 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall();
                    if (z) {
                        jM7165getTextFaint0d7_KjU = colors.m7164getText0d7_KjU();
                    } else {
                        jM7165getTextFaint0d7_KjU = colors.m7165getTextFaint0d7_KjU();
                    }
                    long j3 = jM7165getTextFaint0d7_KjU;
                    companion = FontWeight.INSTANCE;
                    if (z) {
                        normal = companion.getSemiBold();
                    } else {
                        normal = companion.getNormal();
                    }
                    TextKt.m2373Text4IGK_g(label6, (Modifier) null, j3, 0L, (FontStyle) null, normal, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, bodySmall3, composerStartRestartGroup, 0, 0, 65498);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    str2 = str14;
                    f = f4;
                    str3 = str15;
                    modifier4 = modifier9;
                    str = str16;
                    i4 = i4;
                    str4 = str13;
                }
                z = zAreEqual;
                Modifier modifierClip7 = ClipKt.clip(RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM()));
                composerStartRestartGroup.startReplaceGroup(1229323337);
                if ((i4 & 112) == 32) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                zChanged = z2 | composerStartRestartGroup.changed(navItem);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue = new Function0() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return BarsKt.BottomNav$lambda$10$lambda$9$lambda$8$lambda$5$lambda$4(onNavigate, navItem);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function0() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return BarsKt.BottomNav$lambda$10$lambda$9$lambda$8$lambda$5$lambda$4(onNavigate, navItem);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composerStartRestartGroup.endReplaceGroup();
                Modifier modifierM683paddingVpY3zN4$default6 = PaddingKt.m683paddingVpY3zN4$default(ClickableKt.m269clickableXHw0xAI$default(modifierClip7, false, null, null, (Function0) objRememberedValue, 7, null), 0.0f, Dp.m6299constructorimpl(8), 1, null);
                Alignment.Horizontal centerHorizontally4 = Alignment.INSTANCE.getCenterHorizontally();
                Arrangement.HorizontalOrVertical center6 = Arrangement.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy4 = ColumnKt.columnMeasurePolicy(center6, centerHorizontally4, composerStartRestartGroup, 54);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, str2);
                currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap11 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier11 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM683paddingVpY3zN4$default6);
                constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, str3);
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor3);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3333constructorimpl3 = Updater.m3333constructorimpl(composerStartRestartGroup);
                Updater.m3340setimpl(composerM3333constructorimpl3, measurePolicyColumnMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl3, currentCompositionLocalMap11, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3333constructorimpl3.getInserting()) {
                    composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                } else {
                    composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.m3340setimpl(composerM3333constructorimpl3, modifierMaterializeModifier11, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
                Modifier modifierClip8 = ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM()));
                if (z) {
                    jM3875getTransparent0d7_KjU = colors.m7155getSecondary0d7_KjU();
                } else {
                    jM3875getTransparent0d7_KjU = Color.INSTANCE.m3875getTransparent0d7_KjU();
                }
                Modifier modifierM682paddingVpY3zN9 = PaddingKt.m682paddingVpY3zN4(BackgroundKt.m236backgroundbw27NRU$default(modifierClip8, jM3875getTransparent0d7_KjU, null, 2, null), Dp.m6299constructorimpl(f), Dp.m6299constructorimpl(3));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, str);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy6 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, str2);
                currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap12 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier12 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN9);
                constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, str3);
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor4);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3333constructorimpl4 = Updater.m3333constructorimpl(composerStartRestartGroup);
                Updater.m3340setimpl(composerM3333constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl4, currentCompositionLocalMap12, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3333constructorimpl4.getInserting()) {
                    composerM3333constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                    composerM3333constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                } else {
                    composerM3333constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                    composerM3333constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                }
                Updater.m3340setimpl(composerM3333constructorimpl4, modifierMaterializeModifier12, ComposeUiNode.INSTANCE.getSetModifier());
                String str17 = str4;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, str17);
                BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                ImageVector icon4 = navItem.getIcon();
                String label7 = navItem.getLabel();
                if (z) {
                    jM7166getTextSoft0d7_KjU = colors.m7149getOnSecondary0d7_KjU();
                } else {
                    jM7166getTextSoft0d7_KjU = colors.m7166getTextSoft0d7_KjU();
                }
                String str18 = str2;
                float f5 = f;
                String str19 = str3;
                String str110 = str;
                Modifier modifier10 = modifier4;
                IconKt.m1830Iconww6aTOc(icon4, label7, SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(22)), jM7166getTextSoft0d7_KjU, composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                String label8 = navItem.getLabel();
                TextStyle bodySmall4 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall();
                if (z) {
                    jM7165getTextFaint0d7_KjU = colors.m7164getText0d7_KjU();
                } else {
                    jM7165getTextFaint0d7_KjU = colors.m7165getTextFaint0d7_KjU();
                }
                long j4 = jM7165getTextFaint0d7_KjU;
                companion = FontWeight.INSTANCE;
                if (z) {
                    normal = companion.getSemiBold();
                } else {
                    normal = companion.getNormal();
                }
                TextKt.m2373Text4IGK_g(label8, (Modifier) null, j4, 0L, (FontStyle) null, normal, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, bodySmall4, composerStartRestartGroup, 0, 0, 65498);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                str2 = str18;
                f = f5;
                str3 = str19;
                modifier4 = modifier10;
                str = str110;
                i4 = i4;
                str4 = str17;
            }
            modifier5 = modifier4;
            composerStartRestartGroup.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            if (i5 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-318631305, i4, -1, "com.dubtonic.player.ui.components.BottomNav (Bars.kt:176)");
            }
            colors = DubTheme.INSTANCE.getColors(composerStartRestartGroup, 6);
            f = 14;
            Modifier modifierM682paddingVpY3zN10 = PaddingKt.m682paddingVpY3zN4(WindowInsetsPadding_androidKt.navigationBarsPadding(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, null)), Dp.m6299constructorimpl(f), Dp.m6299constructorimpl(10));
            Alignment center7 = Alignment.INSTANCE.getCenter();
            modifier4 = modifier3;
            str = "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo";
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy7 = BoxKt.maybeCachedBoxMeasurePolicy(center7, false);
            str2 = "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh";
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap13 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier13 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN10);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
            str3 = "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp";
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
            composerM3333constructorimpl = Updater.m3333constructorimpl(composerStartRestartGroup);
            Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy7, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap13, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3333constructorimpl.getInserting()) {
                composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier13, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
            str4 = "C73@3429L9:Box.kt#2w3rfo";
            Modifier modifierM683paddingVpY3zN4$default7 = PaddingKt.m683paddingVpY3zN4$default(ShadowKt.m3504shadows4CzXII$default(BorderKt.m247borderxT4_qwU(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.m712height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), DubDimens.INSTANCE.m7168getNavD9Ej5fM()), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7169getRadiusLgD9Ej5fM())), colors.m7143getGlassStrong0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(1), colors.m7141getGlassBorder0d7_KjU(), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7169getRadiusLgD9Ej5fM())), Dp.m6299constructorimpl(24), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7169getRadiusLgD9Ej5fM()), false, Color.m3839copywmQWz5c$default(Color.INSTANCE.m3866getBlack0d7_KjU(), 0.3f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3866getBlack0d7_KjU(), 0.3f, 0.0f, 0.0f, 0.0f, 14, null), 4, null), Dp.m6299constructorimpl(4), 0.0f, 2, null);
            Alignment.Vertical centerVertically3 = Alignment.INSTANCE.getCenterVertically();
            Arrangement.HorizontalOrVertical spaceEvenly3 = Arrangement.INSTANCE.getSpaceEvenly();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(spaceEvenly3, centerVertically3, composerStartRestartGroup, 54);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap14 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier14 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM683paddingVpY3zN4$default7);
            constructor2 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerM3333constructorimpl2 = Updater.m3333constructorimpl(composerStartRestartGroup);
            Updater.m3340setimpl(composerM3333constructorimpl2, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl2, currentCompositionLocalMap14, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3333constructorimpl2.getInserting()) {
                composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            } else {
                composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m3340setimpl(composerM3333constructorimpl2, modifierMaterializeModifier14, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            rowScopeInstance = RowScopeInstance.INSTANCE;
            composerStartRestartGroup.startReplaceGroup(1490191288);
            while (r34.hasNext()) {
                if (Intrinsics.areEqual(navItem.getRoute(), "home")) {
                    zAreEqual = Intrinsics.areEqual(activeRoute, "home");
                } else if (Intrinsics.areEqual(navItem.getRoute(), "search")) {
                    zAreEqual = Intrinsics.areEqual(activeRoute, "search");
                } else if (Intrinsics.areEqual(navItem.getRoute(), "playlists")) {
                    zAreEqual = SetsKt.setOf((Object[]) new String[]{"playlists", "playlist"}).contains(activeRoute);
                } else {
                    if (Intrinsics.areEqual(navItem.getRoute(), "settings")) {
                        zAreEqual = Intrinsics.areEqual(activeRoute, "settings");
                    } else if (StringsKt.startsWith$default(navItem.getRoute(), "library", false, 2, (Object) null)) {
                        z = false;
                    } else {
                        z = false;
                    }
                    Modifier modifierClip9 = ClipKt.clip(RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM()));
                    composerStartRestartGroup.startReplaceGroup(1229323337);
                    if ((i4 & 112) == 32) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    zChanged = z2 | composerStartRestartGroup.changed(navItem);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (!zChanged) {
                        objRememberedValue = new Function0() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return BarsKt.BottomNav$lambda$10$lambda$9$lambda$8$lambda$5$lambda$4(onNavigate, navItem);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new Function0() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda5
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return BarsKt.BottomNav$lambda$10$lambda$9$lambda$8$lambda$5$lambda$4(onNavigate, navItem);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    Modifier modifierM683paddingVpY3zN4$default8 = PaddingKt.m683paddingVpY3zN4$default(ClickableKt.m269clickableXHw0xAI$default(modifierClip9, false, null, null, (Function0) objRememberedValue, 7, null), 0.0f, Dp.m6299constructorimpl(8), 1, null);
                    Alignment.Horizontal centerHorizontally5 = Alignment.INSTANCE.getCenterHorizontally();
                    Arrangement.HorizontalOrVertical center8 = Arrangement.INSTANCE.getCenter();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                    MeasurePolicy measurePolicyColumnMeasurePolicy5 = ColumnKt.columnMeasurePolicy(center8, centerHorizontally5, composerStartRestartGroup, 54);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, str2);
                    currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap15 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier15 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM683paddingVpY3zN4$default8);
                    constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, str3);
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor3);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3333constructorimpl3 = Updater.m3333constructorimpl(composerStartRestartGroup);
                    Updater.m3340setimpl(composerM3333constructorimpl3, measurePolicyColumnMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3340setimpl(composerM3333constructorimpl3, currentCompositionLocalMap15, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3333constructorimpl3.getInserting()) {
                        composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                        composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                    } else {
                        composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                        composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                    }
                    Updater.m3340setimpl(composerM3333constructorimpl3, modifierMaterializeModifier15, ComposeUiNode.INSTANCE.getSetModifier());
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                    ColumnScopeInstance columnScopeInstance5 = ColumnScopeInstance.INSTANCE;
                    Modifier modifierClip10 = ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM()));
                    if (z) {
                        jM3875getTransparent0d7_KjU = colors.m7155getSecondary0d7_KjU();
                    } else {
                        jM3875getTransparent0d7_KjU = Color.INSTANCE.m3875getTransparent0d7_KjU();
                    }
                    Modifier modifierM682paddingVpY3zN11 = PaddingKt.m682paddingVpY3zN4(BackgroundKt.m236backgroundbw27NRU$default(modifierClip10, jM3875getTransparent0d7_KjU, null, 2, null), Dp.m6299constructorimpl(f), Dp.m6299constructorimpl(3));
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, str);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy8 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, str2);
                    currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap16 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier16 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN11);
                    constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, str3);
                    if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor4);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM3333constructorimpl4 = Updater.m3333constructorimpl(composerStartRestartGroup);
                    Updater.m3340setimpl(composerM3333constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy8, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                    Updater.m3340setimpl(composerM3333constructorimpl4, currentCompositionLocalMap16, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                    setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                    if (!composerM3333constructorimpl4.getInserting()) {
                        composerM3333constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                        composerM3333constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                    } else {
                        composerM3333constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                        composerM3333constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                    }
                    Updater.m3340setimpl(composerM3333constructorimpl4, modifierMaterializeModifier16, ComposeUiNode.INSTANCE.getSetModifier());
                    String str111 = str4;
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, str111);
                    BoxScopeInstance boxScopeInstance8 = BoxScopeInstance.INSTANCE;
                    ImageVector icon5 = navItem.getIcon();
                    String label9 = navItem.getLabel();
                    if (z) {
                        jM7166getTextSoft0d7_KjU = colors.m7149getOnSecondary0d7_KjU();
                    } else {
                        jM7166getTextSoft0d7_KjU = colors.m7166getTextSoft0d7_KjU();
                    }
                    String str112 = str2;
                    float f6 = f;
                    String str113 = str3;
                    String str114 = str;
                    Modifier modifier11 = modifier4;
                    IconKt.m1830Iconww6aTOc(icon5, label9, SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(22)), jM7166getTextSoft0d7_KjU, composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    String label10 = navItem.getLabel();
                    TextStyle bodySmall5 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall();
                    if (z) {
                        jM7165getTextFaint0d7_KjU = colors.m7164getText0d7_KjU();
                    } else {
                        jM7165getTextFaint0d7_KjU = colors.m7165getTextFaint0d7_KjU();
                    }
                    long j5 = jM7165getTextFaint0d7_KjU;
                    companion = FontWeight.INSTANCE;
                    if (z) {
                        normal = companion.getSemiBold();
                    } else {
                        normal = companion.getNormal();
                    }
                    TextKt.m2373Text4IGK_g(label10, (Modifier) null, j5, 0L, (FontStyle) null, normal, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, bodySmall5, composerStartRestartGroup, 0, 0, 65498);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    composerStartRestartGroup.endNode();
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    str2 = str112;
                    f = f6;
                    str3 = str113;
                    modifier4 = modifier11;
                    str = str114;
                    i4 = i4;
                    str4 = str111;
                }
                z = zAreEqual;
                Modifier modifierClip11 = ClipKt.clip(RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM()));
                composerStartRestartGroup.startReplaceGroup(1229323337);
                if ((i4 & 112) == 32) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                zChanged = z2 | composerStartRestartGroup.changed(navItem);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (!zChanged) {
                    objRememberedValue = new Function0() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return BarsKt.BottomNav$lambda$10$lambda$9$lambda$8$lambda$5$lambda$4(onNavigate, navItem);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function0() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda5
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return BarsKt.BottomNav$lambda$10$lambda$9$lambda$8$lambda$5$lambda$4(onNavigate, navItem);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composerStartRestartGroup.endReplaceGroup();
                Modifier modifierM683paddingVpY3zN4$default9 = PaddingKt.m683paddingVpY3zN4$default(ClickableKt.m269clickableXHw0xAI$default(modifierClip11, false, null, null, (Function0) objRememberedValue, 7, null), 0.0f, Dp.m6299constructorimpl(8), 1, null);
                Alignment.Horizontal centerHorizontally6 = Alignment.INSTANCE.getCenterHorizontally();
                Arrangement.HorizontalOrVertical center9 = Arrangement.INSTANCE.getCenter();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
                MeasurePolicy measurePolicyColumnMeasurePolicy6 = ColumnKt.columnMeasurePolicy(center9, centerHorizontally6, composerStartRestartGroup, 54);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, str2);
                currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap17 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier17 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM683paddingVpY3zN4$default9);
                constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, str3);
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor3);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3333constructorimpl3 = Updater.m3333constructorimpl(composerStartRestartGroup);
                Updater.m3340setimpl(composerM3333constructorimpl3, measurePolicyColumnMeasurePolicy6, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl3, currentCompositionLocalMap17, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3333constructorimpl3.getInserting()) {
                    composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                } else {
                    composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.m3340setimpl(composerM3333constructorimpl3, modifierMaterializeModifier17, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -384784025, "C88@4444L9:Column.kt#2w3rfo");
                ColumnScopeInstance columnScopeInstance6 = ColumnScopeInstance.INSTANCE;
                Modifier modifierClip12 = ClipKt.clip(Modifier.INSTANCE, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7171getRadiusPillD9Ej5fM()));
                if (z) {
                    jM3875getTransparent0d7_KjU = colors.m7155getSecondary0d7_KjU();
                } else {
                    jM3875getTransparent0d7_KjU = Color.INSTANCE.m3875getTransparent0d7_KjU();
                }
                Modifier modifierM682paddingVpY3zN12 = PaddingKt.m682paddingVpY3zN4(BackgroundKt.m236backgroundbw27NRU$default(modifierClip12, jM3875getTransparent0d7_KjU, null, 2, null), Dp.m6299constructorimpl(f), Dp.m6299constructorimpl(3));
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, str);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy9 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, str2);
                currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap18 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier18 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN12);
                constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -692256719, str3);
                if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor4);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM3333constructorimpl4 = Updater.m3333constructorimpl(composerStartRestartGroup);
                Updater.m3340setimpl(composerM3333constructorimpl4, measurePolicyMaybeCachedBoxMeasurePolicy9, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl4, currentCompositionLocalMap18, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3333constructorimpl4.getInserting()) {
                    composerM3333constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                    composerM3333constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                } else {
                    composerM3333constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                    composerM3333constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                }
                Updater.m3340setimpl(composerM3333constructorimpl4, modifierMaterializeModifier18, ComposeUiNode.INSTANCE.getSetModifier());
                String str115 = str4;
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, str115);
                BoxScopeInstance boxScopeInstance9 = BoxScopeInstance.INSTANCE;
                ImageVector icon6 = navItem.getIcon();
                String label11 = navItem.getLabel();
                if (z) {
                    jM7166getTextSoft0d7_KjU = colors.m7149getOnSecondary0d7_KjU();
                } else {
                    jM7166getTextSoft0d7_KjU = colors.m7166getTextSoft0d7_KjU();
                }
                String str116 = str2;
                float f7 = f;
                String str117 = str3;
                String str118 = str;
                Modifier modifier12 = modifier4;
                IconKt.m1830Iconww6aTOc(icon6, label11, SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(22)), jM7166getTextSoft0d7_KjU, composerStartRestartGroup, RendererCapabilities.DECODER_SUPPORT_MASK, 0);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                String label12 = navItem.getLabel();
                TextStyle bodySmall6 = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall();
                if (z) {
                    jM7165getTextFaint0d7_KjU = colors.m7164getText0d7_KjU();
                } else {
                    jM7165getTextFaint0d7_KjU = colors.m7165getTextFaint0d7_KjU();
                }
                long j6 = jM7165getTextFaint0d7_KjU;
                companion = FontWeight.INSTANCE;
                if (z) {
                    normal = companion.getSemiBold();
                } else {
                    normal = companion.getNormal();
                }
                TextKt.m2373Text4IGK_g(label12, (Modifier) null, j6, 0L, (FontStyle) null, normal, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, bodySmall6, composerStartRestartGroup, 0, 0, 65498);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                composerStartRestartGroup.endNode();
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                str2 = str116;
                f = f7;
                str3 = str117;
                modifier4 = modifier12;
                str = str118;
                i4 = i4;
                str4 = str115;
            }
            modifier5 = modifier4;
            composerStartRestartGroup.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endNode();
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier13 = modifier5;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BarsKt.BottomNav$lambda$11(activeRoute, onNavigate, modifier13, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit BottomNav$lambda$10$lambda$9$lambda$8$lambda$5$lambda$4(Function1 function1, NavItem navItem) {
        function1.invoke(navItem.getRoute());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:50:0x0095 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:51:0x0097  */
    /* JADX WARN: Code duplicated, block: B:52:0x009d  */
    /* JADX WARN: Code duplicated, block: B:55:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:58:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:61:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:62:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:65:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:67:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:70:0x0125  */
    /* JADX WARN: Code duplicated, block: B:73:0x0151  */
    /* JADX WARN: Code duplicated, block: B:76:0x0196  */
    /* JADX WARN: Code duplicated, block: B:80:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:82:? A[RETURN, SYNTHETIC] */
    public static final void MiniPlayer(final PlayerUiState state, final Function0<Unit> onOpen, final Function0<Unit> onDismiss, Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        Modifier modifier3;
        Object objRememberedValue;
        MutableState mutableState;
        boolean z;
        BarsKt$MiniPlayer$1$1 barsKt$MiniPlayer$1$1RememberedValue;
        Object objRememberedValue2;
        Object objRememberedValue3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(state, "state");
        Intrinsics.checkNotNullParameter(onOpen, "onOpen");
        Intrinsics.checkNotNullParameter(onDismiss, "onDismiss");
        Composer composerStartRestartGroup = composer.startRestartGroup(-1953362111);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(state) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onOpen) ? 32 : 16;
        }
        if ((i2 & 4) != 0) {
            i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onDismiss) ? 256 : 128;
        }
        int i4 = i2 & 8;
        if (i4 == 0) {
            if ((i & 3072) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 2048 : 1024;
            }
            if ((i3 & 1171) == 1170 || !composerStartRestartGroup.getSkipping()) {
                if (i4 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1953362111, i3, -1, "com.dubtonic.player.ui.components.MiniPlayer (Bars.kt:253)");
                }
                DubColors colors = DubTheme.INSTANCE.getColors(composerStartRestartGroup, 6);
                composerStartRestartGroup.startReplaceGroup(1396145917);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(0.0f), null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                composerStartRestartGroup.endReplaceGroup();
                Float fValueOf = Float.valueOf(MiniPlayer$lambda$13(mutableState));
                composerStartRestartGroup.startReplaceGroup(1396149040);
                if ((i3 & 896) == 256) {
                    z = true;
                } else {
                    z = false;
                }
                barsKt$MiniPlayer$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
                if (!z || barsKt$MiniPlayer$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
                    barsKt$MiniPlayer$1$1RememberedValue = new BarsKt$MiniPlayer$1$1(90.0f, onDismiss, mutableState, null);
                    composerStartRestartGroup.updateRememberedValue(barsKt$MiniPlayer$1$1RememberedValue);
                }
                composerStartRestartGroup.endReplaceGroup();
                EffectsKt.LaunchedEffect(fValueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) barsKt$MiniPlayer$1$1RememberedValue, composerStartRestartGroup, 0);
                boolean hasQueue = state.getHasQueue();
                composerStartRestartGroup.startReplaceGroup(1396155588);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue2 = new Function1() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Integer.valueOf(BarsKt.MiniPlayer$lambda$17$lambda$16(((Integer) obj).intValue()));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                composerStartRestartGroup.endReplaceGroup();
                EnterTransition enterTransitionPlus = EnterExitTransitionKt.slideInVertically$default(null, (Function1) objRememberedValue2, 1, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null));
                composerStartRestartGroup.startReplaceGroup(1396157284);
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function1() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda3
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return Integer.valueOf(BarsKt.MiniPlayer$lambda$19$lambda$18(((Integer) obj).intValue()));
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                composerStartRestartGroup.endReplaceGroup();
                AnimatedVisibilityKt.AnimatedVisibility(hasQueue, modifier3, enterTransitionPlus, EnterExitTransitionKt.slideOutVertically$default(null, (Function1) objRememberedValue3, 1, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(1591993369, true, new AnonymousClass4(state, colors, onOpen, mutableState), composerStartRestartGroup, 54), composerStartRestartGroup, ((i3 >> 6) & 112) | 200064, 16);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier3;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final Modifier modifier4 = modifier2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return BarsKt.MiniPlayer$lambda$20(state, onOpen, onDismiss, modifier4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        modifier2 = modifier;
        if ((i3 & 1171) == 1170) {
            if (i4 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1953362111, i3, -1, "com.dubtonic.player.ui.components.MiniPlayer (Bars.kt:253)");
            }
            DubColors colors2 = DubTheme.INSTANCE.getColors(composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceGroup(1396145917);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(0.0f), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            mutableState = (MutableState) objRememberedValue;
            composerStartRestartGroup.endReplaceGroup();
            Float fValueOf2 = Float.valueOf(MiniPlayer$lambda$13(mutableState));
            composerStartRestartGroup.startReplaceGroup(1396149040);
            if ((i3 & 896) == 256) {
                z = true;
            } else {
                z = false;
            }
            barsKt$MiniPlayer$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z) {
                barsKt$MiniPlayer$1$1RememberedValue = new BarsKt$MiniPlayer$1$1(90.0f, onDismiss, mutableState, null);
                composerStartRestartGroup.updateRememberedValue(barsKt$MiniPlayer$1$1RememberedValue);
            } else {
                barsKt$MiniPlayer$1$1RememberedValue = new BarsKt$MiniPlayer$1$1(90.0f, onDismiss, mutableState, null);
                composerStartRestartGroup.updateRememberedValue(barsKt$MiniPlayer$1$1RememberedValue);
            }
            composerStartRestartGroup.endReplaceGroup();
            EffectsKt.LaunchedEffect(fValueOf2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) barsKt$MiniPlayer$1$1RememberedValue, composerStartRestartGroup, 0);
            boolean hasQueue2 = state.getHasQueue();
            composerStartRestartGroup.startReplaceGroup(1396155588);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Integer.valueOf(BarsKt.MiniPlayer$lambda$17$lambda$16(((Integer) obj).intValue()));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composerStartRestartGroup.endReplaceGroup();
            EnterTransition enterTransitionPlus2 = EnterExitTransitionKt.slideInVertically$default(null, (Function1) objRememberedValue2, 1, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null));
            composerStartRestartGroup.startReplaceGroup(1396157284);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function1() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Integer.valueOf(BarsKt.MiniPlayer$lambda$19$lambda$18(((Integer) obj).intValue()));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            composerStartRestartGroup.endReplaceGroup();
            AnimatedVisibilityKt.AnimatedVisibility(hasQueue2, modifier3, enterTransitionPlus2, EnterExitTransitionKt.slideOutVertically$default(null, (Function1) objRememberedValue3, 1, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(1591993369, true, new AnonymousClass4(state, colors2, onOpen, mutableState), composerStartRestartGroup, 54), composerStartRestartGroup, ((i3 >> 6) & 112) | 200064, 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
        } else {
            if (i4 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1953362111, i3, -1, "com.dubtonic.player.ui.components.MiniPlayer (Bars.kt:253)");
            }
            DubColors colors3 = DubTheme.INSTANCE.getColors(composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceGroup(1396145917);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(0.0f), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            mutableState = (MutableState) objRememberedValue;
            composerStartRestartGroup.endReplaceGroup();
            Float fValueOf3 = Float.valueOf(MiniPlayer$lambda$13(mutableState));
            composerStartRestartGroup.startReplaceGroup(1396149040);
            if ((i3 & 896) == 256) {
                z = true;
            } else {
                z = false;
            }
            barsKt$MiniPlayer$1$1RememberedValue = composerStartRestartGroup.rememberedValue();
            if (!z) {
                barsKt$MiniPlayer$1$1RememberedValue = new BarsKt$MiniPlayer$1$1(90.0f, onDismiss, mutableState, null);
                composerStartRestartGroup.updateRememberedValue(barsKt$MiniPlayer$1$1RememberedValue);
            } else {
                barsKt$MiniPlayer$1$1RememberedValue = new BarsKt$MiniPlayer$1$1(90.0f, onDismiss, mutableState, null);
                composerStartRestartGroup.updateRememberedValue(barsKt$MiniPlayer$1$1RememberedValue);
            }
            composerStartRestartGroup.endReplaceGroup();
            EffectsKt.LaunchedEffect(fValueOf3, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) barsKt$MiniPlayer$1$1RememberedValue, composerStartRestartGroup, 0);
            boolean hasQueue3 = state.getHasQueue();
            composerStartRestartGroup.startReplaceGroup(1396155588);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Integer.valueOf(BarsKt.MiniPlayer$lambda$17$lambda$16(((Integer) obj).intValue()));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composerStartRestartGroup.endReplaceGroup();
            EnterTransition enterTransitionPlus3 = EnterExitTransitionKt.slideInVertically$default(null, (Function1) objRememberedValue2, 1, null).plus(EnterExitTransitionKt.fadeIn$default(null, 0.0f, 3, null));
            composerStartRestartGroup.startReplaceGroup(1396157284);
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function1() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Integer.valueOf(BarsKt.MiniPlayer$lambda$19$lambda$18(((Integer) obj).intValue()));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            composerStartRestartGroup.endReplaceGroup();
            AnimatedVisibilityKt.AnimatedVisibility(hasQueue3, modifier3, enterTransitionPlus3, EnterExitTransitionKt.slideOutVertically$default(null, (Function1) objRememberedValue3, 1, null).plus(EnterExitTransitionKt.fadeOut$default(null, 0.0f, 3, null)), (String) null, ComposableLambdaKt.rememberComposableLambda(1591993369, true, new AnonymousClass4(state, colors3, onOpen, mutableState), composerStartRestartGroup, 54), composerStartRestartGroup, ((i3 >> 6) & 112) | 200064, 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Modifier modifier5 = modifier2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BarsKt.MiniPlayer$lambda$20(state, onOpen, onDismiss, modifier5, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float MiniPlayer$lambda$13(MutableState<Float> mutableState) {
        return mutableState.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void MiniPlayer$lambda$14(MutableState<Float> mutableState, float f) {
        mutableState.setValue(Float.valueOf(f));
    }

    /* JADX INFO: renamed from: com.dubtonic.player.ui.components.BarsKt$MiniPlayer$4, reason: invalid class name */
    /* JADX INFO: compiled from: Bars.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    static final class AnonymousClass4 implements Function3<AnimatedVisibilityScope, Composer, Integer, Unit> {
        final /* synthetic */ DubColors $colors;
        final /* synthetic */ MutableState<Float> $dragOffset$delegate;
        final /* synthetic */ Function0<Unit> $onOpen;
        final /* synthetic */ PlayerUiState $state;

        AnonymousClass4(PlayerUiState playerUiState, DubColors dubColors, Function0<Unit> function0, MutableState<Float> mutableState) {
            this.$state = playerUiState;
            this.$colors = dubColors;
            this.$onOpen = function0;
            this.$dragOffset$delegate = mutableState;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, Integer num) {
            invoke(animatedVisibilityScope, composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(AnimatedVisibilityScope AnimatedVisibility, Composer composer, int i) {
            Intrinsics.checkNotNullParameter(AnimatedVisibility, "$this$AnimatedVisibility");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1591993369, i, -1, "com.dubtonic.player.ui.components.MiniPlayer.<anonymous> (Bars.kt:271)");
            }
            QueueItem current = this.$state.getCurrent();
            if (current == null) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            Modifier modifierM683paddingVpY3zN4$default = PaddingKt.m683paddingVpY3zN4$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6299constructorimpl(14), 0.0f, 2, null);
            DubColors dubColors = this.$colors;
            final Function0<Unit> function0 = this.$onOpen;
            PlayerUiState playerUiState = this.$state;
            MutableState<Float> mutableState = this.$dragOffset$delegate;
            ComposerKt.sourceInformationMarkerStart(composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierM683paddingVpY3zN4$default);
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
            Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3333constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            Modifier modifierM247borderxT4_qwU = BorderKt.m247borderxT4_qwU(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.m712height3ABfNKs(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), DubDimens.INSTANCE.m7167getMiniD9Ej5fM()), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM())), dubColors.m7143getGlassStrong0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(1), dubColors.m7141getGlassBorder0d7_KjU(), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM()));
            Unit unit = Unit.INSTANCE;
            composer.startReplaceGroup(-1864778487);
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = (Function2) new BarsKt$MiniPlayer$4$1$1$1(mutableState, null);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceGroup();
            Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(modifierM247borderxT4_qwU, unit, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue);
            composer.startReplaceGroup(-1864765661);
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = InteractionSourceKt.MutableInteractionSource();
                composer.updateRememberedValue(objRememberedValue2);
            }
            MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) objRememberedValue2;
            composer.endReplaceGroup();
            composer.startReplaceGroup(-1864762328);
            boolean zChanged = composer.changed(function0);
            Object objRememberedValue3 = composer.rememberedValue();
            if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function0() { // from class: com.dubtonic.player.ui.components.BarsKt$MiniPlayer$4$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return BarsKt.AnonymousClass4.invoke$lambda$10$lambda$3$lambda$2(function0);
                    }
                };
                composer.updateRememberedValue(objRememberedValue3);
            }
            composer.endReplaceGroup();
            float f = 10;
            Modifier modifierM682paddingVpY3zN4 = PaddingKt.m682paddingVpY3zN4(ClickableKt.m267clickableO2vRcR0$default(modifierPointerInput, mutableInteractionSource, null, false, null, null, (Function0) objRememberedValue3, 28, null), Dp.m6299constructorimpl(f), Dp.m6299constructorimpl(7));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(composer, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composer, 48);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer, modifierM682paddingVpY3zN4);
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
            Updater.m3340setimpl(composerM3333constructorimpl2, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3333constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m3340setimpl(composerM3333constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComponentsKt.m7042ArtworkTN_CM5M(current.getArtworkUrl(), current.getSong().getTitle(), Dp.m6299constructorimpl(52), null, null, composer, RendererCapabilities.DECODER_SUPPORT_MASK, 24);
            Modifier modifierM683paddingVpY3zN4$default2 = PaddingKt.m683paddingVpY3zN4$default(RowScope.weight$default(rowScopeInstance, Modifier.INSTANCE, 1.0f, false, 2, null), Dp.m6299constructorimpl(f), 0.0f, 2, null);
            ComposerKt.sourceInformationMarkerStart(composer, -483455358, "CC(Column)P(2,3,1)86@4330L61,87@4396L133:Column.kt#2w3rfo");
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer, 0);
            ComposerKt.sourceInformationMarkerStart(composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer, modifierM683paddingVpY3zN4$default2);
            Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
            ComposerKt.sourceInformationMarkerStart(composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!(composer.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor3);
            } else {
                composer.useNode();
            }
            Composer composerM3333constructorimpl3 = Updater.m3333constructorimpl(composer);
            Updater.m3340setimpl(composerM3333constructorimpl3, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3333constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                composerM3333constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM3333constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m3340setimpl(composerM3333constructorimpl3, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composer, -384784025, "C88@4444L9:Column.kt#2w3rfo");
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            TextKt.m2373Text4IGK_g(current.getSong().getTitle(), (Modifier) null, dubColors.m7164getText0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6238getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getBodyLarge(), composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 3120, 55258);
            TextKt.m2373Text4IGK_g(current.getSong().getArtist(), (Modifier) null, dubColors.m7166getTextSoft0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, TextOverflow.INSTANCE.m6238getEllipsisgIe3tQ8(), false, 1, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composer, MaterialTheme.$stable).getBodySmall(), composer, 0, 3120, 55290);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ImageVector pause = playerUiState.getPlaying() ? PauseKt.getPause(Icons.Rounded.INSTANCE) : PlayArrowKt.getPlayArrow(Icons.Rounded.INSTANCE);
            String str = playerUiState.getPlaying() ? "Pause" : "Play";
            long jM7149getOnSecondary0d7_KjU = dubColors.m7149getOnSecondary0d7_KjU();
            Modifier modifierM236backgroundbw27NRU$default = BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(44)), RoundedCornerShapeKt.getCircleShape()), dubColors.m7155getSecondary0d7_KjU(), null, 2, null);
            composer.startReplaceGroup(1130729685);
            Object objRememberedValue4 = composer.rememberedValue();
            if (objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new Function0() { // from class: com.dubtonic.player.ui.components.BarsKt$MiniPlayer$4$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return BarsKt.AnonymousClass4.invoke$lambda$10$lambda$9$lambda$6$lambda$5();
                    }
                };
                composer.updateRememberedValue(objRememberedValue4);
            }
            composer.endReplaceGroup();
            IconKt.m1830Iconww6aTOc(pause, str, PaddingKt.m681padding3ABfNKs(ClickableKt.m269clickableXHw0xAI$default(modifierM236backgroundbw27NRU$default, false, null, null, (Function0) objRememberedValue4, 7, null), Dp.m6299constructorimpl(f)), jM7149getOnSecondary0d7_KjU, composer, 0, 0);
            ImageVector skipNext = SkipNextKt.getSkipNext(Icons.Rounded.INSTANCE);
            long jM7166getTextSoft0d7_KjU = dubColors.m7166getTextSoft0d7_KjU();
            Modifier modifierClip = ClipKt.clip(SizeKt.m726size3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(40)), RoundedCornerShapeKt.getCircleShape());
            composer.startReplaceGroup(1130742447);
            Object objRememberedValue5 = composer.rememberedValue();
            if (objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = new Function0() { // from class: com.dubtonic.player.ui.components.BarsKt$MiniPlayer$4$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return BarsKt.AnonymousClass4.invoke$lambda$10$lambda$9$lambda$8$lambda$7();
                    }
                };
                composer.updateRememberedValue(objRememberedValue5);
            }
            composer.endReplaceGroup();
            IconKt.m1830Iconww6aTOc(skipNext, "Next", PaddingKt.m681padding3ABfNKs(ClickableKt.m269clickableXHw0xAI$default(modifierClip, false, null, null, (Function0) objRememberedValue5, 7, null), Dp.m6299constructorimpl(9)), jM7166getTextSoft0d7_KjU, composer, 48, 0);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            BoxKt.Box(BackgroundKt.m236backgroundbw27NRU$default(boxScopeInstance.align(SizeKt.m712height3ABfNKs(SizeKt.fillMaxWidth(Modifier.INSTANCE, RangesKt.coerceIn(playerUiState.getDurationMs() > 0 ? playerUiState.getPositionMs() / playerUiState.getDurationMs() : 0.0f, 0.0f, 1.0f)), Dp.m6299constructorimpl(2)), Alignment.INSTANCE.getBottomCenter()), dubColors.m7155getSecondary0d7_KjU(), null, 2, null), composer, 0);
            ComposerKt.sourceInformationMarkerEnd(composer);
            composer.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            ComposerKt.sourceInformationMarkerEnd(composer);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$10$lambda$3$lambda$2(Function0 function0) {
            function0.invoke();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$10$lambda$9$lambda$6$lambda$5() {
            PlayerBus.INSTANCE.togglePlay();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit invoke$lambda$10$lambda$9$lambda$8$lambda$7() {
            PlayerBus.INSTANCE.next();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x005b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:31:0x005d  */
    /* JADX WARN: Code duplicated, block: B:32:0x0063  */
    /* JADX WARN: Code duplicated, block: B:35:0x006a  */
    /* JADX WARN: Code duplicated, block: B:38:0x0103  */
    /* JADX WARN: Code duplicated, block: B:41:0x010f  */
    /* JADX WARN: Code duplicated, block: B:42:0x0113  */
    /* JADX WARN: Code duplicated, block: B:45:0x0138  */
    /* JADX WARN: Code duplicated, block: B:47:0x0146  */
    /* JADX WARN: Code duplicated, block: B:50:0x020f  */
    /* JADX WARN: Code duplicated, block: B:54:0x021a  */
    /* JADX WARN: Code duplicated, block: B:56:? A[RETURN, SYNTHETIC] */
    public static final void LibHint(final Function0<Unit> onGotIt, Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        final Modifier modifier2;
        Modifier modifier3;
        int currentCompositeKeyHash;
        Function0<ComposeUiNode> constructor;
        Composer composerM3333constructorimpl;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
        Composer composer2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Intrinsics.checkNotNullParameter(onGotIt, "onGotIt");
        Composer composerStartRestartGroup = composer.startRestartGroup(806829765);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(onGotIt) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i4 = i2 & 2;
        if (i4 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i3 & 19) == 18 || !composerStartRestartGroup.getSkipping()) {
                if (i4 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(806829765, i3, -1, "com.dubtonic.player.ui.components.LibHint (Bars.kt:358)");
                }
                DubColors colors = DubTheme.INSTANCE.getColors(composerStartRestartGroup, 6);
                float f = 10;
                Modifier modifierM682paddingVpY3zN4 = PaddingKt.m682paddingVpY3zN4(BorderKt.m247borderxT4_qwU(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(modifier3, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7172getRadiusSmD9Ej5fM())), colors.m7143getGlassStrong0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(1), colors.m7141getGlassBorder0d7_KjU(), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7172getRadiusSmD9Ej5fM())), Dp.m6299constructorimpl(14), Dp.m6299constructorimpl(f));
                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composerStartRestartGroup, 48);
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN4);
                constructor = ComposeUiNode.INSTANCE.getConstructor();
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
                composerM3333constructorimpl = Updater.m3333constructorimpl(composerStartRestartGroup);
                Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if (!composerM3333constructorimpl.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier, ComposeUiNode.INSTANCE.getSetModifier());
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
                TextKt.m2373Text4IGK_g("Tap the DubTonic logo in the top-left corner to open settings.", RowScope.weight$default(RowScopeInstance.INSTANCE, Modifier.INSTANCE, 1.0f, false, 2, null), colors.m7164getText0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composerStartRestartGroup, 6, 0, 65528);
                SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f)), composerStartRestartGroup, 6);
                Modifier modifier4 = modifier3;
                composer2 = composerStartRestartGroup;
                TextKt.m2373Text4IGK_g("Got it", ClickableKt.m269clickableXHw0xAI$default(Modifier.INSTANCE, false, null, null, onGotIt, 7, null), colors.m7151getPrimaryInk0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composer2, 196614, 0, 65496);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                ComposerKt.sourceInformationMarkerEnd(composer2);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                composer2 = composerStartRestartGroup;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return BarsKt.LibHint$lambda$22(onGotIt, modifier2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        if ((i3 & 19) == 18) {
            if (i4 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(806829765, i3, -1, "com.dubtonic.player.ui.components.LibHint (Bars.kt:358)");
            }
            DubColors colors2 = DubTheme.INSTANCE.getColors(composerStartRestartGroup, 6);
            float f2 = 10;
            Modifier modifierM682paddingVpY3zN5 = PaddingKt.m682paddingVpY3zN4(BorderKt.m247borderxT4_qwU(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(modifier3, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7172getRadiusSmD9Ej5fM())), colors2.m7143getGlassStrong0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(1), colors2.m7141getGlassBorder0d7_KjU(), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7172getRadiusSmD9Ej5fM())), Dp.m6299constructorimpl(14), Dp.m6299constructorimpl(f2));
            Alignment.Vertical centerVertically2 = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically2, composerStartRestartGroup, 48);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN5);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
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
            composerM3333constructorimpl = Updater.m3333constructorimpl(composerStartRestartGroup);
            Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3333constructorimpl.getInserting()) {
                composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            TextKt.m2373Text4IGK_g("Tap the DubTonic logo in the top-left corner to open settings.", RowScope.weight$default(RowScopeInstance.INSTANCE, Modifier.INSTANCE, 1.0f, false, 2, null), colors2.m7164getText0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composerStartRestartGroup, 6, 0, 65528);
            SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f2)), composerStartRestartGroup, 6);
            Modifier modifier5 = modifier3;
            composer2 = composerStartRestartGroup;
            TextKt.m2373Text4IGK_g("Got it", ClickableKt.m269clickableXHw0xAI$default(Modifier.INSTANCE, false, null, null, onGotIt, 7, null), colors2.m7151getPrimaryInk0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composer2, 196614, 0, 65496);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier5;
        } else {
            if (i4 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(806829765, i3, -1, "com.dubtonic.player.ui.components.LibHint (Bars.kt:358)");
            }
            DubColors colors3 = DubTheme.INSTANCE.getColors(composerStartRestartGroup, 6);
            float f3 = 10;
            Modifier modifierM682paddingVpY3zN6 = PaddingKt.m682paddingVpY3zN4(BorderKt.m247borderxT4_qwU(BackgroundKt.m236backgroundbw27NRU$default(ClipKt.clip(modifier3, RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7172getRadiusSmD9Ej5fM())), colors3.m7143getGlassStrong0d7_KjU(), null, 2, null), Dp.m6299constructorimpl(1), colors3.m7141getGlassBorder0d7_KjU(), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7172getRadiusSmD9Ej5fM())), Dp.m6299constructorimpl(14), Dp.m6299constructorimpl(f3));
            Alignment.Vertical centerVertically3 = Alignment.INSTANCE.getCenterVertically();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 693286680, "CC(Row)P(2,1,3)99@5018L58,100@5081L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically3, composerStartRestartGroup, 48);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM682paddingVpY3zN6);
            constructor = ComposeUiNode.INSTANCE.getConstructor();
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
            composerM3333constructorimpl = Updater.m3333constructorimpl(composerStartRestartGroup);
            Updater.m3340setimpl(composerM3333constructorimpl, measurePolicyRowMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (!composerM3333constructorimpl.getInserting()) {
                composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM3333constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM3333constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m3340setimpl(composerM3333constructorimpl, modifierMaterializeModifier3, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -407840262, "C101@5126L9:Row.kt#2w3rfo");
            TextKt.m2373Text4IGK_g("Tap the DubTonic logo in the top-left corner to open settings.", RowScope.weight$default(RowScopeInstance.INSTANCE, Modifier.INSTANCE, 1.0f, false, 2, null), colors3.m7164getText0d7_KjU(), 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composerStartRestartGroup, 6, 0, 65528);
            SpacerKt.Spacer(SizeKt.m731width3ABfNKs(Modifier.INSTANCE, Dp.m6299constructorimpl(f3)), composerStartRestartGroup, 6);
            Modifier modifier6 = modifier3;
            composer2 = composerStartRestartGroup;
            TextKt.m2373Text4IGK_g("Got it", ClickableKt.m269clickableXHw0xAI$default(Modifier.INSTANCE, false, null, null, onGotIt, 7, null), colors3.m7151getPrimaryInk0d7_KjU(), 0L, (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, MaterialTheme.$stable).getBodySmall(), composer2, 196614, 0, 65496);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier6;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.dubtonic.player.ui.components.BarsKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BarsKt.LibHint$lambda$22(onGotIt, modifier2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
