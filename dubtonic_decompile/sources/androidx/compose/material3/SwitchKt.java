package androidx.compose.material3;

import androidx.compose.animation.core.SnapSpec;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.ToggleableKt;
import androidx.compose.material3.tokens.SwitchTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Switch.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001al\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0018¢\u0006\u0002\b\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00122\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0007¢\u0006\u0002\u0010\u001f\u001aR\u0010 \u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001c2\u0013\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0018¢\u0006\u0002\b\u00192\u0006\u0010\u001d\u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0003¢\u0006\u0002\u0010$\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007\"\u0010\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007\"\u0016\u0010\t\u001a\u00020\u0006X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\n\u0010\u000b\"\u0010\u0010\f\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007\"\u0016\u0010\r\u001a\u00020\u0006X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0007\u001a\u0004\b\u000e\u0010\u000b¨\u0006%"}, d2 = {"AnimationSpec", "Landroidx/compose/animation/core/TweenSpec;", "", "SnapSpec", "Landroidx/compose/animation/core/SnapSpec;", "SwitchHeight", "Landroidx/compose/ui/unit/Dp;", "F", "SwitchWidth", "ThumbDiameter", "getThumbDiameter", "()F", "ThumbPadding", "UncheckedThumbDiameter", "getUncheckedThumbDiameter", "Switch", "", "checked", "", "onCheckedChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "thumbContent", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "enabled", "colors", "Landroidx/compose/material3/SwitchColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/SwitchColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "SwitchImpl", "Landroidx/compose/foundation/interaction/InteractionSource;", "thumbShape", "Landroidx/compose/ui/graphics/Shape;", "(Landroidx/compose/ui/Modifier;ZZLandroidx/compose/material3/SwitchColors;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;I)V", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwitchKt {
    private static final TweenSpec<Float> AnimationSpec;
    private static final SnapSpec<Float> SnapSpec;
    private static final float SwitchHeight;
    private static final float SwitchWidth;
    private static final float ThumbDiameter;
    private static final float ThumbPadding;
    private static final float UncheckedThumbDiameter;

    /* JADX WARN: Code duplicated, block: B:102:0x0125  */
    /* JADX WARN: Code duplicated, block: B:104:0x012f  */
    /* JADX WARN: Code duplicated, block: B:105:0x013a  */
    /* JADX WARN: Code duplicated, block: B:108:0x014d  */
    /* JADX WARN: Code duplicated, block: B:111:0x0164  */
    /* JADX WARN: Code duplicated, block: B:113:0x0178  */
    /* JADX WARN: Code duplicated, block: B:115:0x0186  */
    /* JADX WARN: Code duplicated, block: B:118:0x018d  */
    /* JADX WARN: Code duplicated, block: B:119:0x01af  */
    /* JADX WARN: Code duplicated, block: B:122:0x0204  */
    /* JADX WARN: Code duplicated, block: B:126:0x0216  */
    /* JADX WARN: Code duplicated, block: B:128:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x0064  */
    /* JADX WARN: Code duplicated, block: B:37:0x0067  */
    /* JADX WARN: Code duplicated, block: B:39:0x006b  */
    /* JADX WARN: Code duplicated, block: B:41:0x0073  */
    /* JADX WARN: Code duplicated, block: B:42:0x0076  */
    /* JADX WARN: Code duplicated, block: B:47:0x0080  */
    /* JADX WARN: Code duplicated, block: B:48:0x0083  */
    /* JADX WARN: Code duplicated, block: B:50:0x0087  */
    /* JADX WARN: Code duplicated, block: B:52:0x008f  */
    /* JADX WARN: Code duplicated, block: B:53:0x0092  */
    /* JADX WARN: Code duplicated, block: B:58:0x009d  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:63:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:66:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:69:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:70:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:72:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:75:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:80:0x00db  */
    /* JADX WARN: Code duplicated, block: B:84:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:86:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:93:0x0111 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:94:0x0113  */
    /* JADX WARN: Code duplicated, block: B:95:0x0118  */
    /* JADX WARN: Code duplicated, block: B:97:0x011c  */
    /* JADX WARN: Code duplicated, block: B:99:0x011f  */
    public static final void Switch(final boolean z, final Function1<? super Boolean, Unit> function1, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, boolean z2, SwitchColors switchColors, MutableInteractionSource mutableInteractionSource, Composer composer, final int i, final int i2) {
        int i3;
        final Modifier modifier2;
        int i4;
        Function2<? super Composer, ? super Integer, Unit> function3;
        int i5;
        int i6;
        boolean z3;
        int i7;
        SwitchColors switchColorsColors;
        int i8;
        MutableInteractionSource mutableInteractionSource2;
        int i9;
        Modifier.Companion companion;
        int i10;
        Function2<? super Composer, ? super Integer, Unit> function4;
        boolean z4;
        SwitchColors switchColors2;
        MutableInteractionSource mutableInteractionSource3;
        Modifier modifier3;
        MutableInteractionSource mutableInteractionSource4;
        Object obj;
        Modifier.Companion companionM937toggleableO2vRcR0;
        Composer composer2;
        final Function2<? super Composer, ? super Integer, Unit> function5;
        final boolean z5;
        final SwitchColors switchColors3;
        Object objRememberedValue;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Composer composerStartRestartGroup = composer.startRestartGroup(1580463220);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Switch)P(!1,5,4,6,2)97@4514L8,129@5619L5,119@5244L424:Switch.kt#uh7d8r");
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        int i11 = i2 & 4;
        if (i11 == 0) {
            if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    function3 = function2;
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 16;
                if (i6 != 0) {
                    if ((i & 24576) == 0) {
                        z3 = z2;
                        if (composerStartRestartGroup.changed(z3)) {
                            i7 = 16384;
                        } else {
                            i7 = 8192;
                        }
                        i3 |= i7;
                    }
                    if ((196608 & i) == 0) {
                        if ((i2 & 32) == 0) {
                            switchColorsColors = switchColors;
                            int i12 = composerStartRestartGroup.changed(switchColorsColors) ? 131072 : 65536;
                            i3 |= i12;
                        } else {
                            switchColorsColors = switchColors;
                        }
                        i3 |= i12;
                    } else {
                        switchColorsColors = switchColors;
                    }
                    i8 = i2 & 64;
                    if (i8 != 0) {
                        if ((1572864 & i) == 0) {
                            mutableInteractionSource2 = mutableInteractionSource;
                            if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                                i9 = 1048576;
                            } else {
                                i9 = 524288;
                            }
                            i3 |= i9;
                        }
                        if ((i3 & 599187) == 599186 || !composerStartRestartGroup.getSkipping()) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                if (i11 != 0) {
                                    companion = Modifier.INSTANCE;
                                } else {
                                    companion = modifier;
                                }
                                if (i4 != 0) {
                                    function3 = null;
                                }
                                if (i6 != 0) {
                                    z3 = true;
                                }
                                if ((i2 & 32) != 0) {
                                    i3 &= -458753;
                                    switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                }
                                if (i8 != 0) {
                                    modifier3 = companion;
                                    i10 = i3;
                                    function4 = function3;
                                    z4 = z3;
                                    switchColors2 = switchColorsColors;
                                    mutableInteractionSource3 = null;
                                } else {
                                    i10 = i3;
                                    function4 = function3;
                                    z4 = z3;
                                    switchColors2 = switchColorsColors;
                                    mutableInteractionSource3 = mutableInteractionSource2;
                                    modifier3 = companion;
                                }
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i2 & 32) != 0) {
                                    i3 &= -458753;
                                }
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = mutableInteractionSource2;
                                modifier3 = modifier;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                            }
                            composerStartRestartGroup.startReplaceGroup(783532531);
                            ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                            if (mutableInteractionSource3 == null) {
                                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                                mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                            } else {
                                mutableInteractionSource4 = mutableInteractionSource3;
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            if (function1 != null) {
                                obj = null;
                                companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                            } else {
                                obj = null;
                                companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                            }
                            int i13 = i10 << 3;
                            int i14 = i10 >> 6;
                            Modifier modifier4 = modifier3;
                            composer2 = composerStartRestartGroup;
                            SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i13 & 112) | (i14 & 896) | (i14 & 7168) | (i13 & 57344));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier2 = modifier4;
                            function5 = function4;
                            z5 = z4;
                            switchColors3 = switchColors2;
                            mutableInteractionSource2 = mutableInteractionSource3;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            function5 = function3;
                            z5 = z3;
                            switchColors3 = switchColorsColors;
                            composer2 = composerStartRestartGroup;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            final MutableInteractionSource mutableInteractionSource5 = mutableInteractionSource2;
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i15) {
                                    SwitchKt.Switch(z, function1, modifier2, function5, z5, switchColors3, mutableInteractionSource5, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                                }
                            });
                        }
                    }
                    i3 |= 1572864;
                    mutableInteractionSource2 = mutableInteractionSource;
                    if ((i3 & 599187) == 599186) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i11 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z3 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                modifier3 = companion;
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = null;
                            } else {
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = mutableInteractionSource2;
                                modifier3 = companion;
                            }
                        } else {
                            if (i11 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z3 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                modifier3 = companion;
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = null;
                            } else {
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = mutableInteractionSource2;
                                modifier3 = companion;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                        }
                        composerStartRestartGroup.startReplaceGroup(783532531);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                        if (mutableInteractionSource3 == null) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        if (function1 != null) {
                            obj = null;
                            companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                        } else {
                            obj = null;
                            companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                        }
                        int i15 = i10 << 3;
                        int i16 = i10 >> 6;
                        Modifier modifier5 = modifier3;
                        composer2 = composerStartRestartGroup;
                        SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i15 & 112) | (i16 & 896) | (i16 & 7168) | (i15 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier5;
                        function5 = function4;
                        z5 = z4;
                        switchColors3 = switchColors2;
                        mutableInteractionSource2 = mutableInteractionSource3;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i11 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z3 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                modifier3 = companion;
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = null;
                            } else {
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = mutableInteractionSource2;
                                modifier3 = companion;
                            }
                        } else {
                            if (i11 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z3 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                modifier3 = companion;
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = null;
                            } else {
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = mutableInteractionSource2;
                                modifier3 = companion;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                        }
                        composerStartRestartGroup.startReplaceGroup(783532531);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                        if (mutableInteractionSource3 == null) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        if (function1 != null) {
                            obj = null;
                            companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                        } else {
                            obj = null;
                            companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                        }
                        int i17 = i10 << 3;
                        int i18 = i10 >> 6;
                        Modifier modifier6 = modifier3;
                        composer2 = composerStartRestartGroup;
                        SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i17 & 112) | (i18 & 896) | (i18 & 7168) | (i17 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier6;
                        function5 = function4;
                        z5 = z4;
                        switchColors3 = switchColors2;
                        mutableInteractionSource2 = mutableInteractionSource3;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final MutableInteractionSource mutableInteractionSource6 = mutableInteractionSource2;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i19) {
                                SwitchKt.Switch(z, function1, modifier2, function5, z5, switchColors3, mutableInteractionSource6, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 24576;
                z3 = z2;
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        switchColorsColors = switchColors;
                        if (composerStartRestartGroup.changed(switchColorsColors)) {
                        }
                        i3 |= i12;
                    } else {
                        switchColorsColors = switchColors;
                    }
                    i3 |= i12;
                } else {
                    switchColorsColors = switchColors;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    if ((1572864 & i) == 0) {
                        mutableInteractionSource2 = mutableInteractionSource;
                        if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                    if ((i3 & 599187) == 599186) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i11 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z3 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                modifier3 = companion;
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = null;
                            } else {
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = mutableInteractionSource2;
                                modifier3 = companion;
                            }
                        } else {
                            if (i11 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z3 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                modifier3 = companion;
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = null;
                            } else {
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = mutableInteractionSource2;
                                modifier3 = companion;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                        }
                        composerStartRestartGroup.startReplaceGroup(783532531);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                        if (mutableInteractionSource3 == null) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        if (function1 != null) {
                            obj = null;
                            companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                        } else {
                            obj = null;
                            companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                        }
                        int i19 = i10 << 3;
                        int i110 = i10 >> 6;
                        Modifier modifier7 = modifier3;
                        composer2 = composerStartRestartGroup;
                        SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i19 & 112) | (i110 & 896) | (i110 & 7168) | (i19 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier7;
                        function5 = function4;
                        z5 = z4;
                        switchColors3 = switchColors2;
                        mutableInteractionSource2 = mutableInteractionSource3;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i11 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z3 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                modifier3 = companion;
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = null;
                            } else {
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = mutableInteractionSource2;
                                modifier3 = companion;
                            }
                        } else {
                            if (i11 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z3 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                modifier3 = companion;
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = null;
                            } else {
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = mutableInteractionSource2;
                                modifier3 = companion;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                        }
                        composerStartRestartGroup.startReplaceGroup(783532531);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                        if (mutableInteractionSource3 == null) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        if (function1 != null) {
                            obj = null;
                            companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                        } else {
                            obj = null;
                            companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                        }
                        int i111 = i10 << 3;
                        int i112 = i10 >> 6;
                        Modifier modifier8 = modifier3;
                        composer2 = composerStartRestartGroup;
                        SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i111 & 112) | (i112 & 896) | (i112 & 7168) | (i111 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier8;
                        function5 = function4;
                        z5 = z4;
                        switchColors3 = switchColors2;
                        mutableInteractionSource2 = mutableInteractionSource3;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final MutableInteractionSource mutableInteractionSource7 = mutableInteractionSource2;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i113) {
                                SwitchKt.Switch(z, function1, modifier2, function5, z5, switchColors3, mutableInteractionSource7, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 1572864;
                mutableInteractionSource2 = mutableInteractionSource;
                if ((i3 & 599187) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    } else {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function1 != null) {
                        obj = null;
                        companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                    } else {
                        obj = null;
                        companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i113 = i10 << 3;
                    int i114 = i10 >> 6;
                    Modifier modifier9 = modifier3;
                    composer2 = composerStartRestartGroup;
                    SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i113 & 112) | (i114 & 896) | (i114 & 7168) | (i113 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier9;
                    function5 = function4;
                    z5 = z4;
                    switchColors3 = switchColors2;
                    mutableInteractionSource2 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    } else {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function1 != null) {
                        obj = null;
                        companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                    } else {
                        obj = null;
                        companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i115 = i10 << 3;
                    int i116 = i10 >> 6;
                    Modifier modifier10 = modifier3;
                    composer2 = composerStartRestartGroup;
                    SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i115 & 112) | (i116 & 896) | (i116 & 7168) | (i115 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier10;
                    function5 = function4;
                    z5 = z4;
                    switchColors3 = switchColors2;
                    mutableInteractionSource2 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final MutableInteractionSource mutableInteractionSource8 = mutableInteractionSource2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i117) {
                            SwitchKt.Switch(z, function1, modifier2, function5, z5, switchColors3, mutableInteractionSource8, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 3072;
            function3 = function2;
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    z3 = z2;
                    if (composerStartRestartGroup.changed(z3)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        switchColorsColors = switchColors;
                        if (composerStartRestartGroup.changed(switchColorsColors)) {
                        }
                        i3 |= i12;
                    } else {
                        switchColorsColors = switchColors;
                    }
                    i3 |= i12;
                } else {
                    switchColorsColors = switchColors;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    if ((1572864 & i) == 0) {
                        mutableInteractionSource2 = mutableInteractionSource;
                        if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                    if ((i3 & 599187) == 599186) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i11 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z3 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                modifier3 = companion;
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = null;
                            } else {
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = mutableInteractionSource2;
                                modifier3 = companion;
                            }
                        } else {
                            if (i11 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z3 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                modifier3 = companion;
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = null;
                            } else {
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = mutableInteractionSource2;
                                modifier3 = companion;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                        }
                        composerStartRestartGroup.startReplaceGroup(783532531);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                        if (mutableInteractionSource3 == null) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        if (function1 != null) {
                            obj = null;
                            companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                        } else {
                            obj = null;
                            companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                        }
                        int i117 = i10 << 3;
                        int i118 = i10 >> 6;
                        Modifier modifier11 = modifier3;
                        composer2 = composerStartRestartGroup;
                        SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i117 & 112) | (i118 & 896) | (i118 & 7168) | (i117 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier11;
                        function5 = function4;
                        z5 = z4;
                        switchColors3 = switchColors2;
                        mutableInteractionSource2 = mutableInteractionSource3;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i11 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z3 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                modifier3 = companion;
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = null;
                            } else {
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = mutableInteractionSource2;
                                modifier3 = companion;
                            }
                        } else {
                            if (i11 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z3 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                modifier3 = companion;
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = null;
                            } else {
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = mutableInteractionSource2;
                                modifier3 = companion;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                        }
                        composerStartRestartGroup.startReplaceGroup(783532531);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                        if (mutableInteractionSource3 == null) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        if (function1 != null) {
                            obj = null;
                            companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                        } else {
                            obj = null;
                            companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                        }
                        int i119 = i10 << 3;
                        int i1110 = i10 >> 6;
                        Modifier modifier12 = modifier3;
                        composer2 = composerStartRestartGroup;
                        SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i119 & 112) | (i1110 & 896) | (i1110 & 7168) | (i119 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier12;
                        function5 = function4;
                        z5 = z4;
                        switchColors3 = switchColors2;
                        mutableInteractionSource2 = mutableInteractionSource3;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final MutableInteractionSource mutableInteractionSource9 = mutableInteractionSource2;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i1111) {
                                SwitchKt.Switch(z, function1, modifier2, function5, z5, switchColors3, mutableInteractionSource9, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 1572864;
                mutableInteractionSource2 = mutableInteractionSource;
                if ((i3 & 599187) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    } else {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function1 != null) {
                        obj = null;
                        companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                    } else {
                        obj = null;
                        companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i1111 = i10 << 3;
                    int i1112 = i10 >> 6;
                    Modifier modifier13 = modifier3;
                    composer2 = composerStartRestartGroup;
                    SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1111 & 112) | (i1112 & 896) | (i1112 & 7168) | (i1111 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier13;
                    function5 = function4;
                    z5 = z4;
                    switchColors3 = switchColors2;
                    mutableInteractionSource2 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    } else {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function1 != null) {
                        obj = null;
                        companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                    } else {
                        obj = null;
                        companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i1113 = i10 << 3;
                    int i1114 = i10 >> 6;
                    Modifier modifier14 = modifier3;
                    composer2 = composerStartRestartGroup;
                    SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1113 & 112) | (i1114 & 896) | (i1114 & 7168) | (i1113 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier14;
                    function5 = function4;
                    z5 = z4;
                    switchColors3 = switchColors2;
                    mutableInteractionSource2 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final MutableInteractionSource mutableInteractionSource10 = mutableInteractionSource2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i1115) {
                            SwitchKt.Switch(z, function1, modifier2, function5, z5, switchColors3, mutableInteractionSource10, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 24576;
            z3 = z2;
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    switchColorsColors = switchColors;
                    if (composerStartRestartGroup.changed(switchColorsColors)) {
                    }
                    i3 |= i12;
                } else {
                    switchColorsColors = switchColors;
                }
                i3 |= i12;
            } else {
                switchColorsColors = switchColors;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                if ((1572864 & i) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                if ((i3 & 599187) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    } else {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function1 != null) {
                        obj = null;
                        companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                    } else {
                        obj = null;
                        companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i1115 = i10 << 3;
                    int i1116 = i10 >> 6;
                    Modifier modifier15 = modifier3;
                    composer2 = composerStartRestartGroup;
                    SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1115 & 112) | (i1116 & 896) | (i1116 & 7168) | (i1115 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier15;
                    function5 = function4;
                    z5 = z4;
                    switchColors3 = switchColors2;
                    mutableInteractionSource2 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    } else {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function1 != null) {
                        obj = null;
                        companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                    } else {
                        obj = null;
                        companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i1117 = i10 << 3;
                    int i1118 = i10 >> 6;
                    Modifier modifier16 = modifier3;
                    composer2 = composerStartRestartGroup;
                    SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1117 & 112) | (i1118 & 896) | (i1118 & 7168) | (i1117 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier16;
                    function5 = function4;
                    z5 = z4;
                    switchColors3 = switchColors2;
                    mutableInteractionSource2 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final MutableInteractionSource mutableInteractionSource11 = mutableInteractionSource2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i1119) {
                            SwitchKt.Switch(z, function1, modifier2, function5, z5, switchColors3, mutableInteractionSource11, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 1572864;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((i3 & 599187) == 599186) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        modifier3 = companion;
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = null;
                    } else {
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = mutableInteractionSource2;
                        modifier3 = companion;
                    }
                } else {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        modifier3 = companion;
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = null;
                    } else {
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = mutableInteractionSource2;
                        modifier3 = companion;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                }
                composerStartRestartGroup.startReplaceGroup(783532531);
                ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                if (mutableInteractionSource3 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                } else {
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                composerStartRestartGroup.endReplaceGroup();
                if (function1 != null) {
                    obj = null;
                    companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                } else {
                    obj = null;
                    companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                }
                int i1119 = i10 << 3;
                int i11110 = i10 >> 6;
                Modifier modifier17 = modifier3;
                composer2 = composerStartRestartGroup;
                SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1119 & 112) | (i11110 & 896) | (i11110 & 7168) | (i1119 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier17;
                function5 = function4;
                z5 = z4;
                switchColors3 = switchColors2;
                mutableInteractionSource2 = mutableInteractionSource3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        modifier3 = companion;
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = null;
                    } else {
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = mutableInteractionSource2;
                        modifier3 = companion;
                    }
                } else {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        modifier3 = companion;
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = null;
                    } else {
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = mutableInteractionSource2;
                        modifier3 = companion;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                }
                composerStartRestartGroup.startReplaceGroup(783532531);
                ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                if (mutableInteractionSource3 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                } else {
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                composerStartRestartGroup.endReplaceGroup();
                if (function1 != null) {
                    obj = null;
                    companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                } else {
                    obj = null;
                    companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                }
                int i11111 = i10 << 3;
                int i11112 = i10 >> 6;
                Modifier modifier18 = modifier3;
                composer2 = composerStartRestartGroup;
                SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i11111 & 112) | (i11112 & 896) | (i11112 & 7168) | (i11111 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier18;
                function5 = function4;
                z5 = z4;
                switchColors3 = switchColors2;
                mutableInteractionSource2 = mutableInteractionSource3;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final MutableInteractionSource mutableInteractionSource12 = mutableInteractionSource2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i11113) {
                        SwitchKt.Switch(z, function1, modifier2, function5, z5, switchColors3, mutableInteractionSource12, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        modifier2 = modifier;
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                function3 = function2;
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    z3 = z2;
                    if (composerStartRestartGroup.changed(z3)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        switchColorsColors = switchColors;
                        if (composerStartRestartGroup.changed(switchColorsColors)) {
                        }
                        i3 |= i12;
                    } else {
                        switchColorsColors = switchColors;
                    }
                    i3 |= i12;
                } else {
                    switchColorsColors = switchColors;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    if ((1572864 & i) == 0) {
                        mutableInteractionSource2 = mutableInteractionSource;
                        if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                    if ((i3 & 599187) == 599186) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i11 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z3 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                modifier3 = companion;
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = null;
                            } else {
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = mutableInteractionSource2;
                                modifier3 = companion;
                            }
                        } else {
                            if (i11 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z3 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                modifier3 = companion;
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = null;
                            } else {
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = mutableInteractionSource2;
                                modifier3 = companion;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                        }
                        composerStartRestartGroup.startReplaceGroup(783532531);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                        if (mutableInteractionSource3 == null) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        if (function1 != null) {
                            obj = null;
                            companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                        } else {
                            obj = null;
                            companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                        }
                        int i11113 = i10 << 3;
                        int i11114 = i10 >> 6;
                        Modifier modifier19 = modifier3;
                        composer2 = composerStartRestartGroup;
                        SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i11113 & 112) | (i11114 & 896) | (i11114 & 7168) | (i11113 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier19;
                        function5 = function4;
                        z5 = z4;
                        switchColors3 = switchColors2;
                        mutableInteractionSource2 = mutableInteractionSource3;
                    } else {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i11 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z3 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                modifier3 = companion;
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = null;
                            } else {
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = mutableInteractionSource2;
                                modifier3 = companion;
                            }
                        } else {
                            if (i11 != 0) {
                                companion = Modifier.INSTANCE;
                            } else {
                                companion = modifier;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z3 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                modifier3 = companion;
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = null;
                            } else {
                                i10 = i3;
                                function4 = function3;
                                z4 = z3;
                                switchColors2 = switchColorsColors;
                                mutableInteractionSource3 = mutableInteractionSource2;
                                modifier3 = companion;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                        }
                        composerStartRestartGroup.startReplaceGroup(783532531);
                        ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                        if (mutableInteractionSource3 == null) {
                            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        if (function1 != null) {
                            obj = null;
                            companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                        } else {
                            obj = null;
                            companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                        }
                        int i11115 = i10 << 3;
                        int i11116 = i10 >> 6;
                        Modifier modifier110 = modifier3;
                        composer2 = composerStartRestartGroup;
                        SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i11115 & 112) | (i11116 & 896) | (i11116 & 7168) | (i11115 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier110;
                        function5 = function4;
                        z5 = z4;
                        switchColors3 = switchColors2;
                        mutableInteractionSource2 = mutableInteractionSource3;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        final MutableInteractionSource mutableInteractionSource13 = mutableInteractionSource2;
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i11117) {
                                SwitchKt.Switch(z, function1, modifier2, function5, z5, switchColors3, mutableInteractionSource13, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                            }
                        });
                    }
                }
                i3 |= 1572864;
                mutableInteractionSource2 = mutableInteractionSource;
                if ((i3 & 599187) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    } else {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function1 != null) {
                        obj = null;
                        companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                    } else {
                        obj = null;
                        companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i11117 = i10 << 3;
                    int i11118 = i10 >> 6;
                    Modifier modifier111 = modifier3;
                    composer2 = composerStartRestartGroup;
                    SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i11117 & 112) | (i11118 & 896) | (i11118 & 7168) | (i11117 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier111;
                    function5 = function4;
                    z5 = z4;
                    switchColors3 = switchColors2;
                    mutableInteractionSource2 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    } else {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function1 != null) {
                        obj = null;
                        companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                    } else {
                        obj = null;
                        companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i11119 = i10 << 3;
                    int i111110 = i10 >> 6;
                    Modifier modifier112 = modifier3;
                    composer2 = composerStartRestartGroup;
                    SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i11119 & 112) | (i111110 & 896) | (i111110 & 7168) | (i11119 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier112;
                    function5 = function4;
                    z5 = z4;
                    switchColors3 = switchColors2;
                    mutableInteractionSource2 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final MutableInteractionSource mutableInteractionSource14 = mutableInteractionSource2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i111111) {
                            SwitchKt.Switch(z, function1, modifier2, function5, z5, switchColors3, mutableInteractionSource14, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 24576;
            z3 = z2;
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    switchColorsColors = switchColors;
                    if (composerStartRestartGroup.changed(switchColorsColors)) {
                    }
                    i3 |= i12;
                } else {
                    switchColorsColors = switchColors;
                }
                i3 |= i12;
            } else {
                switchColorsColors = switchColors;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                if ((1572864 & i) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                if ((i3 & 599187) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    } else {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function1 != null) {
                        obj = null;
                        companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                    } else {
                        obj = null;
                        companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i111111 = i10 << 3;
                    int i111112 = i10 >> 6;
                    Modifier modifier113 = modifier3;
                    composer2 = composerStartRestartGroup;
                    SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i111111 & 112) | (i111112 & 896) | (i111112 & 7168) | (i111111 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier113;
                    function5 = function4;
                    z5 = z4;
                    switchColors3 = switchColors2;
                    mutableInteractionSource2 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    } else {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function1 != null) {
                        obj = null;
                        companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                    } else {
                        obj = null;
                        companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i111113 = i10 << 3;
                    int i111114 = i10 >> 6;
                    Modifier modifier114 = modifier3;
                    composer2 = composerStartRestartGroup;
                    SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i111113 & 112) | (i111114 & 896) | (i111114 & 7168) | (i111113 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier114;
                    function5 = function4;
                    z5 = z4;
                    switchColors3 = switchColors2;
                    mutableInteractionSource2 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final MutableInteractionSource mutableInteractionSource15 = mutableInteractionSource2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i111115) {
                            SwitchKt.Switch(z, function1, modifier2, function5, z5, switchColors3, mutableInteractionSource15, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 1572864;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((i3 & 599187) == 599186) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        modifier3 = companion;
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = null;
                    } else {
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = mutableInteractionSource2;
                        modifier3 = companion;
                    }
                } else {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        modifier3 = companion;
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = null;
                    } else {
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = mutableInteractionSource2;
                        modifier3 = companion;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                }
                composerStartRestartGroup.startReplaceGroup(783532531);
                ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                if (mutableInteractionSource3 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                } else {
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                composerStartRestartGroup.endReplaceGroup();
                if (function1 != null) {
                    obj = null;
                    companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                } else {
                    obj = null;
                    companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                }
                int i111115 = i10 << 3;
                int i111116 = i10 >> 6;
                Modifier modifier115 = modifier3;
                composer2 = composerStartRestartGroup;
                SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i111115 & 112) | (i111116 & 896) | (i111116 & 7168) | (i111115 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier115;
                function5 = function4;
                z5 = z4;
                switchColors3 = switchColors2;
                mutableInteractionSource2 = mutableInteractionSource3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        modifier3 = companion;
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = null;
                    } else {
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = mutableInteractionSource2;
                        modifier3 = companion;
                    }
                } else {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        modifier3 = companion;
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = null;
                    } else {
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = mutableInteractionSource2;
                        modifier3 = companion;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                }
                composerStartRestartGroup.startReplaceGroup(783532531);
                ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                if (mutableInteractionSource3 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                } else {
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                composerStartRestartGroup.endReplaceGroup();
                if (function1 != null) {
                    obj = null;
                    companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                } else {
                    obj = null;
                    companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                }
                int i111117 = i10 << 3;
                int i111118 = i10 >> 6;
                Modifier modifier116 = modifier3;
                composer2 = composerStartRestartGroup;
                SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i111117 & 112) | (i111118 & 896) | (i111118 & 7168) | (i111117 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier116;
                function5 = function4;
                z5 = z4;
                switchColors3 = switchColors2;
                mutableInteractionSource2 = mutableInteractionSource3;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final MutableInteractionSource mutableInteractionSource16 = mutableInteractionSource2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i111119) {
                        SwitchKt.Switch(z, function1, modifier2, function5, z5, switchColors3, mutableInteractionSource16, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 3072;
        function3 = function2;
        i6 = i2 & 16;
        if (i6 != 0) {
            if ((i & 24576) == 0) {
                z3 = z2;
                if (composerStartRestartGroup.changed(z3)) {
                    i7 = 16384;
                } else {
                    i7 = 8192;
                }
                i3 |= i7;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    switchColorsColors = switchColors;
                    if (composerStartRestartGroup.changed(switchColorsColors)) {
                    }
                    i3 |= i12;
                } else {
                    switchColorsColors = switchColors;
                }
                i3 |= i12;
            } else {
                switchColorsColors = switchColors;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                if ((1572864 & i) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                if ((i3 & 599187) == 599186) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    } else {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function1 != null) {
                        obj = null;
                        companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                    } else {
                        obj = null;
                        companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i111119 = i10 << 3;
                    int i1111110 = i10 >> 6;
                    Modifier modifier117 = modifier3;
                    composer2 = composerStartRestartGroup;
                    SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i111119 & 112) | (i1111110 & 896) | (i1111110 & 7168) | (i111119 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier117;
                    function5 = function4;
                    z5 = z4;
                    switchColors3 = switchColors2;
                    mutableInteractionSource2 = mutableInteractionSource3;
                } else {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    } else {
                        if (i11 != 0) {
                            companion = Modifier.INSTANCE;
                        } else {
                            companion = modifier;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z3 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            modifier3 = companion;
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = null;
                        } else {
                            i10 = i3;
                            function4 = function3;
                            z4 = z3;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                            modifier3 = companion;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                    }
                    composerStartRestartGroup.startReplaceGroup(783532531);
                    ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                    if (mutableInteractionSource3 == null) {
                        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    if (function1 != null) {
                        obj = null;
                        companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                    } else {
                        obj = null;
                        companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                    }
                    int i1111111 = i10 << 3;
                    int i1111112 = i10 >> 6;
                    Modifier modifier118 = modifier3;
                    composer2 = composerStartRestartGroup;
                    SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1111111 & 112) | (i1111112 & 896) | (i1111112 & 7168) | (i1111111 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier118;
                    function5 = function4;
                    z5 = z4;
                    switchColors3 = switchColors2;
                    mutableInteractionSource2 = mutableInteractionSource3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    final MutableInteractionSource mutableInteractionSource17 = mutableInteractionSource2;
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                            invoke(composer3, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer3, int i1111113) {
                            SwitchKt.Switch(z, function1, modifier2, function5, z5, switchColors3, mutableInteractionSource17, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                        }
                    });
                }
            }
            i3 |= 1572864;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((i3 & 599187) == 599186) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        modifier3 = companion;
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = null;
                    } else {
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = mutableInteractionSource2;
                        modifier3 = companion;
                    }
                } else {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        modifier3 = companion;
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = null;
                    } else {
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = mutableInteractionSource2;
                        modifier3 = companion;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                }
                composerStartRestartGroup.startReplaceGroup(783532531);
                ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                if (mutableInteractionSource3 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                } else {
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                composerStartRestartGroup.endReplaceGroup();
                if (function1 != null) {
                    obj = null;
                    companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                } else {
                    obj = null;
                    companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                }
                int i1111113 = i10 << 3;
                int i1111114 = i10 >> 6;
                Modifier modifier119 = modifier3;
                composer2 = composerStartRestartGroup;
                SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1111113 & 112) | (i1111114 & 896) | (i1111114 & 7168) | (i1111113 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier119;
                function5 = function4;
                z5 = z4;
                switchColors3 = switchColors2;
                mutableInteractionSource2 = mutableInteractionSource3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        modifier3 = companion;
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = null;
                    } else {
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = mutableInteractionSource2;
                        modifier3 = companion;
                    }
                } else {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        modifier3 = companion;
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = null;
                    } else {
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = mutableInteractionSource2;
                        modifier3 = companion;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                }
                composerStartRestartGroup.startReplaceGroup(783532531);
                ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                if (mutableInteractionSource3 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                } else {
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                composerStartRestartGroup.endReplaceGroup();
                if (function1 != null) {
                    obj = null;
                    companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                } else {
                    obj = null;
                    companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                }
                int i1111115 = i10 << 3;
                int i1111116 = i10 >> 6;
                Modifier modifier1110 = modifier3;
                composer2 = composerStartRestartGroup;
                SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1111115 & 112) | (i1111116 & 896) | (i1111116 & 7168) | (i1111115 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier1110;
                function5 = function4;
                z5 = z4;
                switchColors3 = switchColors2;
                mutableInteractionSource2 = mutableInteractionSource3;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final MutableInteractionSource mutableInteractionSource18 = mutableInteractionSource2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i1111117) {
                        SwitchKt.Switch(z, function1, modifier2, function5, z5, switchColors3, mutableInteractionSource18, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 24576;
        z3 = z2;
        if ((196608 & i) == 0) {
            if ((i2 & 32) == 0) {
                switchColorsColors = switchColors;
                if (composerStartRestartGroup.changed(switchColorsColors)) {
                }
                i3 |= i12;
            } else {
                switchColorsColors = switchColors;
            }
            i3 |= i12;
        } else {
            switchColorsColors = switchColors;
        }
        i8 = i2 & 64;
        if (i8 != 0) {
            if ((1572864 & i) == 0) {
                mutableInteractionSource2 = mutableInteractionSource;
                if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                    i9 = 1048576;
                } else {
                    i9 = 524288;
                }
                i3 |= i9;
            }
            if ((i3 & 599187) == 599186) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        modifier3 = companion;
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = null;
                    } else {
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = mutableInteractionSource2;
                        modifier3 = companion;
                    }
                } else {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        modifier3 = companion;
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = null;
                    } else {
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = mutableInteractionSource2;
                        modifier3 = companion;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                }
                composerStartRestartGroup.startReplaceGroup(783532531);
                ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                if (mutableInteractionSource3 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                } else {
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                composerStartRestartGroup.endReplaceGroup();
                if (function1 != null) {
                    obj = null;
                    companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                } else {
                    obj = null;
                    companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                }
                int i1111117 = i10 << 3;
                int i1111118 = i10 >> 6;
                Modifier modifier1111 = modifier3;
                composer2 = composerStartRestartGroup;
                SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1111117 & 112) | (i1111118 & 896) | (i1111118 & 7168) | (i1111117 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier1111;
                function5 = function4;
                z5 = z4;
                switchColors3 = switchColors2;
                mutableInteractionSource2 = mutableInteractionSource3;
            } else {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        modifier3 = companion;
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = null;
                    } else {
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = mutableInteractionSource2;
                        modifier3 = companion;
                    }
                } else {
                    if (i11 != 0) {
                        companion = Modifier.INSTANCE;
                    } else {
                        companion = modifier;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z3 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        modifier3 = companion;
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = null;
                    } else {
                        i10 = i3;
                        function4 = function3;
                        z4 = z3;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = mutableInteractionSource2;
                        modifier3 = companion;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
                }
                composerStartRestartGroup.startReplaceGroup(783532531);
                ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
                if (mutableInteractionSource3 == null) {
                    ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                } else {
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                composerStartRestartGroup.endReplaceGroup();
                if (function1 != null) {
                    obj = null;
                    companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
                } else {
                    obj = null;
                    companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
                }
                int i1111119 = i10 << 3;
                int i11111110 = i10 >> 6;
                Modifier modifier1112 = modifier3;
                composer2 = composerStartRestartGroup;
                SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1111119 & 112) | (i11111110 & 896) | (i11111110 & 7168) | (i1111119 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier1112;
                function5 = function4;
                z5 = z4;
                switchColors3 = switchColors2;
                mutableInteractionSource2 = mutableInteractionSource3;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                final MutableInteractionSource mutableInteractionSource19 = mutableInteractionSource2;
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                        invoke(composer3, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer composer3, int i11111111) {
                        SwitchKt.Switch(z, function1, modifier2, function5, z5, switchColors3, mutableInteractionSource19, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                    }
                });
            }
        }
        i3 |= 1572864;
        mutableInteractionSource2 = mutableInteractionSource;
        if ((i3 & 599187) == 599186) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i11 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier;
                }
                if (i4 != 0) {
                    function3 = null;
                }
                if (i6 != 0) {
                    z3 = true;
                }
                if ((i2 & 32) != 0) {
                    i3 &= -458753;
                    switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                if (i8 != 0) {
                    modifier3 = companion;
                    i10 = i3;
                    function4 = function3;
                    z4 = z3;
                    switchColors2 = switchColorsColors;
                    mutableInteractionSource3 = null;
                } else {
                    i10 = i3;
                    function4 = function3;
                    z4 = z3;
                    switchColors2 = switchColorsColors;
                    mutableInteractionSource3 = mutableInteractionSource2;
                    modifier3 = companion;
                }
            } else {
                if (i11 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier;
                }
                if (i4 != 0) {
                    function3 = null;
                }
                if (i6 != 0) {
                    z3 = true;
                }
                if ((i2 & 32) != 0) {
                    i3 &= -458753;
                    switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                if (i8 != 0) {
                    modifier3 = companion;
                    i10 = i3;
                    function4 = function3;
                    z4 = z3;
                    switchColors2 = switchColorsColors;
                    mutableInteractionSource3 = null;
                } else {
                    i10 = i3;
                    function4 = function3;
                    z4 = z3;
                    switchColors2 = switchColorsColors;
                    mutableInteractionSource3 = mutableInteractionSource2;
                    modifier3 = companion;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
            }
            composerStartRestartGroup.startReplaceGroup(783532531);
            ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
            if (mutableInteractionSource3 == null) {
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
            } else {
                mutableInteractionSource4 = mutableInteractionSource3;
            }
            composerStartRestartGroup.endReplaceGroup();
            if (function1 != null) {
                obj = null;
                companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
            } else {
                obj = null;
                companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
            }
            int i11111111 = i10 << 3;
            int i11111112 = i10 >> 6;
            Modifier modifier1113 = modifier3;
            composer2 = composerStartRestartGroup;
            SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i11111111 & 112) | (i11111112 & 896) | (i11111112 & 7168) | (i11111111 & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier1113;
            function5 = function4;
            z5 = z4;
            switchColors3 = switchColors2;
            mutableInteractionSource2 = mutableInteractionSource3;
        } else {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i11 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier;
                }
                if (i4 != 0) {
                    function3 = null;
                }
                if (i6 != 0) {
                    z3 = true;
                }
                if ((i2 & 32) != 0) {
                    i3 &= -458753;
                    switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                if (i8 != 0) {
                    modifier3 = companion;
                    i10 = i3;
                    function4 = function3;
                    z4 = z3;
                    switchColors2 = switchColorsColors;
                    mutableInteractionSource3 = null;
                } else {
                    i10 = i3;
                    function4 = function3;
                    z4 = z3;
                    switchColors2 = switchColorsColors;
                    mutableInteractionSource3 = mutableInteractionSource2;
                    modifier3 = companion;
                }
            } else {
                if (i11 != 0) {
                    companion = Modifier.INSTANCE;
                } else {
                    companion = modifier;
                }
                if (i4 != 0) {
                    function3 = null;
                }
                if (i6 != 0) {
                    z3 = true;
                }
                if ((i2 & 32) != 0) {
                    i3 &= -458753;
                    switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                if (i8 != 0) {
                    modifier3 = companion;
                    i10 = i3;
                    function4 = function3;
                    z4 = z3;
                    switchColors2 = switchColorsColors;
                    mutableInteractionSource3 = null;
                } else {
                    i10 = i3;
                    function4 = function3;
                    z4 = z3;
                    switchColors2 = switchColorsColors;
                    mutableInteractionSource3 = mutableInteractionSource2;
                    modifier3 = companion;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1580463220, i10, -1, "androidx.compose.material3.Switch (Switch.kt:99)");
            }
            composerStartRestartGroup.startReplaceGroup(783532531);
            ComposerKt.sourceInformation(composerStartRestartGroup, "101@4666L39");
            if (mutableInteractionSource3 == null) {
                ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 783533182, "CC(remember):Switch.kt#9igjgp");
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
                mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
            } else {
                mutableInteractionSource4 = mutableInteractionSource3;
            }
            composerStartRestartGroup.endReplaceGroup();
            if (function1 != null) {
                obj = null;
                companionM937toggleableO2vRcR0 = ToggleableKt.m937toggleableO2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z, mutableInteractionSource4, null, z4, Role.m5587boximpl(Role.INSTANCE.m5599getSwitcho7Vup1c()), function1);
            } else {
                obj = null;
                companionM937toggleableO2vRcR0 = Modifier.INSTANCE;
            }
            int i11111113 = i10 << 3;
            int i11111114 = i10 >> 6;
            Modifier modifier1114 = modifier3;
            composer2 = composerStartRestartGroup;
            SwitchImpl(SizeKt.m720requiredSizeVpY3zN4(SizeKt.wrapContentSize$default(modifier3.then(companionM937toggleableO2vRcR0), Alignment.INSTANCE.getCenter(), false, 2, obj), SwitchWidth, SwitchHeight), z, z4, switchColors2, function4, mutableInteractionSource4, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i11111113 & 112) | (i11111114 & 896) | (i11111114 & 7168) | (i11111113 & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier1114;
            function5 = function4;
            z5 = z4;
            switchColors3 = switchColors2;
            mutableInteractionSource2 = mutableInteractionSource3;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final MutableInteractionSource mutableInteractionSource110 = mutableInteractionSource2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.Switch.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i11111115) {
                    SwitchKt.Switch(z, function1, modifier2, function5, z5, switchColors3, mutableInteractionSource110, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SwitchImpl(final Modifier modifier, final boolean z, final boolean z2, final SwitchColors switchColors, final Function2<? super Composer, ? super Integer, Unit> function2, final InteractionSource interactionSource, final Shape shape, Composer composer, final int i) {
        int i2;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1594099146);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SwitchImpl)P(4!1,2!1,5)147@6165L5,149@6176L1114:Switch.kt#uh7d8r");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i2 |= composerStartRestartGroup.changed(z2) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changed(switchColors) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(interactionSource) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(shape) ? 1048576 : 524288;
        }
        int i3 = i2;
        if ((599187 & i3) != 599186 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1594099146, i3, -1, "androidx.compose.material3.SwitchImpl (Switch.kt:144)");
            }
            long jM2254trackColorWaAFU9c$material3_release = switchColors.m2254trackColorWaAFU9c$material3_release(z2, z);
            long jM2253thumbColorWaAFU9c$material3_release = switchColors.m2253thumbColorWaAFU9c$material3_release(z2, z);
            Shape value = ShapesKt.getValue(SwitchTokens.INSTANCE.getTrackShape(), composerStartRestartGroup, 6);
            Modifier modifierM235backgroundbw27NRU = BackgroundKt.m235backgroundbw27NRU(BorderKt.m247borderxT4_qwU(modifier, SwitchTokens.INSTANCE.m3205getTrackOutlineWidthD9Ej5fM(), switchColors.m2234borderColorWaAFU9c$material3_release(z2, z), value), jM2254trackColorWaAFU9c$material3_release, value);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM235backgroundbw27NRU);
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
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -249502072, "C161@6662L183,154@6351L933:Switch.kt#uh7d8r");
            Modifier modifierM235backgroundbw27NRU2 = BackgroundKt.m235backgroundbw27NRU(IndicationKt.indication(boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterStart()).then(new ThumbElement(interactionSource, z)), interactionSource, RippleKt.m2087rippleOrFallbackImplementation9IZ8Weo(false, Dp.m6299constructorimpl(SwitchTokens.INSTANCE.m3203getStateLayerSizeD9Ej5fM() / 2), 0L, composerStartRestartGroup, 54, 4)), jM2253thumbColorWaAFU9c$material3_release, shape);
            Alignment center = Alignment.INSTANCE.getCenter();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(center, false);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierM235backgroundbw27NRU2);
            Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
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
            Composer composerM3333constructorimpl2 = Updater.m3333constructorimpl(composerStartRestartGroup);
            Updater.m3340setimpl(composerM3333constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3340setimpl(composerM3333constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM3333constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM3333constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM3333constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM3333constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m3340setimpl(composerM3333constructorimpl2, modifierMaterializeModifier2, ComposeUiNode.INSTANCE.getSetModifier());
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1707453249, "C:Switch.kt#uh7d8r");
            composerStartRestartGroup.startReplaceGroup(1163457794);
            ComposerKt.sourceInformation(composerStartRestartGroup, "171@7116L144");
            composer2 = composerStartRestartGroup;
            if (function2 != null) {
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3830boximpl(switchColors.m2252iconColorWaAFU9c$material3_release(z2, z))), function2, composer2, ProvidedValue.$stable | ((i3 >> 9) & 112));
            }
            composer2.endReplaceGroup();
            ComposerKt.sourceInformationMarkerEnd(composer2);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd(composer2);
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
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SwitchKt.SwitchImpl.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                    invoke(composer3, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer3, int i4) {
                    SwitchKt.SwitchImpl(modifier, z, z2, switchColors, function2, interactionSource, shape, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    static {
        float fM3201getSelectedHandleWidthD9Ej5fM = SwitchTokens.INSTANCE.m3201getSelectedHandleWidthD9Ej5fM();
        ThumbDiameter = fM3201getSelectedHandleWidthD9Ej5fM;
        UncheckedThumbDiameter = SwitchTokens.INSTANCE.m3208getUnselectedHandleWidthD9Ej5fM();
        SwitchWidth = SwitchTokens.INSTANCE.m3206getTrackWidthD9Ej5fM();
        float fM3204getTrackHeightD9Ej5fM = SwitchTokens.INSTANCE.m3204getTrackHeightD9Ej5fM();
        SwitchHeight = fM3204getTrackHeightD9Ej5fM;
        ThumbPadding = Dp.m6299constructorimpl(Dp.m6299constructorimpl(fM3204getTrackHeightD9Ej5fM - fM3201getSelectedHandleWidthD9Ej5fM) / 2);
        SnapSpec = new SnapSpec<>(0, 1, null);
        AnimationSpec = new TweenSpec<>(100, 0, null, 6, null);
    }

    public static final float getThumbDiameter() {
        return ThumbDiameter;
    }

    public static final float getUncheckedThumbDiameter() {
        return UncheckedThumbDiameter;
    }
}
