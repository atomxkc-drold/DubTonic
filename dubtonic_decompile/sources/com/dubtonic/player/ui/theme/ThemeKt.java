package com.dubtonic.player.ui.theme;

import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.material3.MaterialThemeKt;
import androidx.compose.material3.Shapes;
import androidx.compose.material3.Typography;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.font.SystemFontFamily;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.TextUnitKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Theme.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a(\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0011\u0010\u0015\u001a\r\u0012\u0004\u0012\u00020\u00120\u0016¢\u0006\u0002\b\u0017H\u0007¢\u0006\u0002\u0010\u0018\"\u0011\u0010\u0000\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0011\u0010\u0004\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0003\"\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\"\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"DarkDubColors", "Lcom/dubtonic/player/ui/theme/DubColors;", "getDarkDubColors", "()Lcom/dubtonic/player/ui/theme/DubColors;", "LightDubColors", "getLightDubColors", "LocalDubColors", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "getLocalDubColors", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "DarkScheme", "Landroidx/compose/material3/ColorScheme;", "LightScheme", "DubTypography", "Landroidx/compose/material3/Typography;", "DubShapes", "Landroidx/compose/material3/Shapes;", "DubTonicTheme", "", "dark", "", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "app_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ThemeKt {
    private static final DubColors DarkDubColors;
    private static final ColorScheme DarkScheme;
    private static final Shapes DubShapes;
    private static final Typography DubTypography;
    private static final DubColors LightDubColors;
    private static final ColorScheme LightScheme;
    private static final ProvidableCompositionLocal<DubColors> LocalDubColors;

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit DubTonicTheme$lambda$1(boolean z, Function2 function2, int i, Composer composer, int i2) {
        DubTonicTheme(z, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static {
        DubColors dubColors = new DubColors(ColorKt.Color(4279177753L), ColorKt.Color(4279507235L), ColorKt.Color(4278782994L), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3877getWhite0d7_KjU(), 0.055f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(ColorKt.Color(4279902251L), 0.78f, 0.0f, 0.0f, 0.0f, 14, null), ColorKt.Color(4279704871L), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3877getWhite0d7_KjU(), 0.09f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3877getWhite0d7_KjU(), 0.08f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3877getWhite0d7_KjU(), 0.16f, 0.0f, 0.0f, 0.0f, 14, null), ColorKt.Color(4294178552L), Color.m3839copywmQWz5c$default(ColorKt.Color(4294178552L), 0.64f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(ColorKt.Color(4294178552L), 0.4f, 0.0f, 0.0f, 0.0f, 14, null), ColorKt.Color(4278396356L), Color.m3839copywmQWz5c$default(ColorKt.Color(4283198463L), 0.15f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(ColorKt.Color(4283198463L), 0.28f, 0.0f, 0.0f, 0.0f, 14, null), ColorKt.Color(4288524543L), Color.INSTANCE.m3877getWhite0d7_KjU(), ColorKt.Color(4291224934L), Color.m3839copywmQWz5c$default(ColorKt.Color(4291224934L), 0.15f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(ColorKt.Color(4291224934L), 0.32f, 0.0f, 0.0f, 0.0f, 14, null), ColorKt.Color(4279968773L), ColorKt.Color(4294934904L), Color.m3839copywmQWz5c$default(ColorKt.Color(4294934904L), 0.12f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(ColorKt.Color(4279375648L), 0.58f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(ColorKt.Color(4279770664L), 0.8f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3877getWhite0d7_KjU(), 0.1f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3877getWhite0d7_KjU(), 0.16f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(ColorKt.Color(4278396356L), 0.55f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(ColorKt.Color(4291224934L), 0.14f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3866getBlack0d7_KjU(), 0.6f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3877getWhite0d7_KjU(), 0.06f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3877getWhite0d7_KjU(), 0.13f, 0.0f, 0.0f, 0.0f, 14, null), null);
        DarkDubColors = dubColors;
        DubColors dubColors2 = new DubColors(ColorKt.Color(4292928739L), ColorKt.Color(4293586669L), ColorKt.Color(4292008151L), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3877getWhite0d7_KjU(), 0.56f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3877getWhite0d7_KjU(), 0.82f, 0.0f, 0.0f, 0.0f, 14, null), ColorKt.Color(4294375671L), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3877getWhite0d7_KjU(), 0.74f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(ColorKt.Color(4279177753L), 0.08f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(ColorKt.Color(4279177753L), 0.16f, 0.0f, 0.0f, 0.0f, 14, null), ColorKt.Color(4279177753L), Color.m3839copywmQWz5c$default(ColorKt.Color(4279177753L), 0.64f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(ColorKt.Color(4279177753L), 0.42f, 0.0f, 0.0f, 0.0f, 14, null), ColorKt.Color(4278396356L), Color.m3839copywmQWz5c$default(ColorKt.Color(4278396356L), 0.1f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(ColorKt.Color(4278396356L), 0.18f, 0.0f, 0.0f, 0.0f, 14, null), ColorKt.Color(4278396356L), Color.INSTANCE.m3877getWhite0d7_KjU(), ColorKt.Color(4291224934L), Color.m3839copywmQWz5c$default(ColorKt.Color(4291224934L), 0.26f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(ColorKt.Color(4291224934L), 0.46f, 0.0f, 0.0f, 0.0f, 14, null), ColorKt.Color(4279968773L), ColorKt.Color(4289934636L), Color.m3839copywmQWz5c$default(ColorKt.Color(4289934636L), 0.1f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3877getWhite0d7_KjU(), 0.52f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3877getWhite0d7_KjU(), 0.74f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3877getWhite0d7_KjU(), 0.7f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3877getWhite0d7_KjU(), 0.92f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(ColorKt.Color(4278396356L), 0.16f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(ColorKt.Color(4291224934L), 0.34f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(ColorKt.Color(4279177753L), 0.4f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(ColorKt.Color(4279177753L), 0.07f, 0.0f, 0.0f, 0.0f, 14, null), Color.m3839copywmQWz5c$default(Color.INSTANCE.m3877getWhite0d7_KjU(), 0.55f, 0.0f, 0.0f, 0.0f, 14, null), null);
        LightDubColors = dubColors2;
        LocalDubColors = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: com.dubtonic.player.ui.theme.ThemeKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ThemeKt.DarkDubColors;
            }
        });
        DarkScheme = ColorSchemeKt.m1611darkColorSchemeCXl9yA$default(dubColors.m7150getPrimary0d7_KjU(), dubColors.m7148getOnPrimary0d7_KjU(), dubColors.m7152getPrimarySoft0d7_KjU(), dubColors.m7151getPrimaryInk0d7_KjU(), 0L, dubColors.m7155getSecondary0d7_KjU(), dubColors.m7149getOnSecondary0d7_KjU(), dubColors.m7156getSecondarySoft0d7_KjU(), dubColors.m7155getSecondary0d7_KjU(), 0L, 0L, 0L, 0L, dubColors.m7135getBg0d7_KjU(), dubColors.m7164getText0d7_KjU(), dubColors.m7162getSurfaceSolid0d7_KjU(), dubColors.m7164getText0d7_KjU(), dubColors.m7162getSurfaceSolid0d7_KjU(), dubColors.m7166getTextSoft0d7_KjU(), 0L, 0L, 0L, dubColors.m7138getDanger0d7_KjU(), 0L, dubColors.m7139getDangerSoft0d7_KjU(), 0L, dubColors.m7147getLineStrong0d7_KjU(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, -88596976, 15, null);
        LightScheme = ColorSchemeKt.m1615lightColorSchemeCXl9yA$default(dubColors2.m7150getPrimary0d7_KjU(), dubColors2.m7148getOnPrimary0d7_KjU(), dubColors2.m7152getPrimarySoft0d7_KjU(), dubColors2.m7151getPrimaryInk0d7_KjU(), 0L, dubColors2.m7155getSecondary0d7_KjU(), dubColors2.m7149getOnSecondary0d7_KjU(), dubColors2.m7156getSecondarySoft0d7_KjU(), dubColors2.m7149getOnSecondary0d7_KjU(), 0L, 0L, 0L, 0L, dubColors2.m7135getBg0d7_KjU(), dubColors2.m7164getText0d7_KjU(), dubColors2.m7162getSurfaceSolid0d7_KjU(), dubColors2.m7164getText0d7_KjU(), dubColors2.m7162getSurfaceSolid0d7_KjU(), dubColors2.m7166getTextSoft0d7_KjU(), 0L, 0L, 0L, dubColors2.m7138getDanger0d7_KjU(), 0L, dubColors2.m7139getDangerSoft0d7_KjU(), 0L, dubColors2.m7147getLineStrong0d7_KjU(), 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, -88596976, 15, null);
        SystemFontFamily systemFontFamily = FontFamily.INSTANCE.getDefault();
        FontWeight bold = FontWeight.INSTANCE.getBold();
        SystemFontFamily systemFontFamily2 = systemFontFamily;
        TextStyle textStyle = new TextStyle(0L, TextUnitKt.getSp(32), bold, (FontStyle) null, (FontSynthesis) null, systemFontFamily2, (String) null, TextUnitKt.getSp(-1), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(36), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645977, (DefaultConstructorMarker) null);
        SystemFontFamily systemFontFamily3 = FontFamily.INSTANCE.getDefault();
        FontWeight bold2 = FontWeight.INSTANCE.getBold();
        SystemFontFamily systemFontFamily4 = systemFontFamily3;
        TextStyle textStyle2 = new TextStyle(0L, TextUnitKt.getSp(26), bold2, (FontStyle) null, (FontSynthesis) null, systemFontFamily4, (String) null, TextUnitKt.getSp(-0.5d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(30), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645977, (DefaultConstructorMarker) null);
        SystemFontFamily systemFontFamily5 = FontFamily.INSTANCE.getDefault();
        FontWeight bold3 = FontWeight.INSTANCE.getBold();
        SystemFontFamily systemFontFamily6 = systemFontFamily5;
        TextStyle textStyle3 = new TextStyle(0L, TextUnitKt.getSp(22), bold3, (FontStyle) null, (FontSynthesis) null, systemFontFamily6, (String) null, TextUnitKt.getSp(-0.4d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(26), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645977, (DefaultConstructorMarker) null);
        SystemFontFamily systemFontFamily7 = FontFamily.INSTANCE.getDefault();
        TextStyle textStyle4 = new TextStyle(0L, TextUnitKt.getSp(19), FontWeight.INSTANCE.getBold(), (FontStyle) null, (FontSynthesis) null, systemFontFamily7, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(24), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646105, (DefaultConstructorMarker) null);
        SystemFontFamily systemFontFamily8 = FontFamily.INSTANCE.getDefault();
        TextStyle textStyle5 = new TextStyle(0L, TextUnitKt.getSp(16), FontWeight.INSTANCE.getSemiBold(), (FontStyle) null, (FontSynthesis) null, systemFontFamily8, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(21), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646105, (DefaultConstructorMarker) null);
        SystemFontFamily systemFontFamily9 = FontFamily.INSTANCE.getDefault();
        TextStyle textStyle6 = new TextStyle(0L, TextUnitKt.getSp(15), FontWeight.INSTANCE.getNormal(), (FontStyle) null, (FontSynthesis) null, systemFontFamily9, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(22), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646105, (DefaultConstructorMarker) null);
        SystemFontFamily systemFontFamily10 = FontFamily.INSTANCE.getDefault();
        TextStyle textStyle7 = new TextStyle(0L, TextUnitKt.getSp(13), FontWeight.INSTANCE.getNormal(), (FontStyle) null, (FontSynthesis) null, systemFontFamily10, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(19), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646105, (DefaultConstructorMarker) null);
        SystemFontFamily systemFontFamily11 = FontFamily.INSTANCE.getDefault();
        TextStyle textStyle8 = new TextStyle(0L, TextUnitKt.getSp(11.5d), FontWeight.INSTANCE.getNormal(), (FontStyle) null, (FontSynthesis) null, systemFontFamily11, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(16), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646105, (DefaultConstructorMarker) null);
        SystemFontFamily systemFontFamily12 = FontFamily.INSTANCE.getDefault();
        FontWeight semiBold = FontWeight.INSTANCE.getSemiBold();
        SystemFontFamily systemFontFamily13 = systemFontFamily12;
        DubTypography = new Typography(null, null, textStyle, null, textStyle2, textStyle3, textStyle4, textStyle5, null, textStyle6, textStyle7, textStyle8, new TextStyle(0L, TextUnitKt.getSp(10.5d), semiBold, (FontStyle) null, (FontSynthesis) null, systemFontFamily13, (String) null, TextUnitKt.getSp(1.6d), (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(14), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16645977, (DefaultConstructorMarker) null), null, null, 24843, null);
        DubShapes = new Shapes(RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7174getRadiusXsD9Ej5fM()), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7172getRadiusSmD9Ej5fM()), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7170getRadiusMdD9Ej5fM()), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7169getRadiusLgD9Ej5fM()), RoundedCornerShapeKt.m964RoundedCornerShape0680j_4(DubDimens.INSTANCE.m7173getRadiusXlD9Ej5fM()));
    }

    public static final DubColors getDarkDubColors() {
        return DarkDubColors;
    }

    public static final DubColors getLightDubColors() {
        return LightDubColors;
    }

    public static final ProvidableCompositionLocal<DubColors> getLocalDubColors() {
        return LocalDubColors;
    }

    public static final void DubTonicTheme(final boolean z, final Function2<? super Composer, ? super Integer, Unit> content, Composer composer, final int i) {
        int i2;
        Intrinsics.checkNotNullParameter(content, "content");
        Composer composerStartRestartGroup = composer.startRestartGroup(251614344);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(content) ? 32 : 16;
        }
        if ((i2 & 19) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(251614344, i2, -1, "com.dubtonic.player.ui.theme.DubTonicTheme (Theme.kt:252)");
            }
            CompositionLocalKt.CompositionLocalProvider(LocalDubColors.provides(z ? DarkDubColors : LightDubColors), ComposableLambdaKt.rememberComposableLambda(1095928136, true, new Function2<Composer, Integer, Unit>() { // from class: com.dubtonic.player.ui.theme.ThemeKt.DubTonicTheme.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i3) {
                    if ((i3 & 3) != 2 || !composer2.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1095928136, i3, -1, "com.dubtonic.player.ui.theme.DubTonicTheme.<anonymous> (Theme.kt:255)");
                        }
                        MaterialThemeKt.MaterialTheme(z ? ThemeKt.DarkScheme : ThemeKt.LightScheme, ThemeKt.DubShapes, ThemeKt.DubTypography, content, composer2, 432, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer2.skipToGroupEnd();
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: com.dubtonic.player.ui.theme.ThemeKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ThemeKt.DubTonicTheme$lambda$1(z, content, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
