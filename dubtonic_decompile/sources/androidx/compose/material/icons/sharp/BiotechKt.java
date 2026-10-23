package androidx.compose.material.icons.sharp;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Biotech.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_biotech", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Biotech", "Landroidx/compose/material/icons/Icons$Sharp;", "getBiotech", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class BiotechKt {
    private static ImageVector _biotech;

    public static final ImageVector getBiotech(Icons.Sharp sharp) {
        ImageVector imageVector = _biotech;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.Biotech", Dp.m6299constructorimpl(24.0f), Dp.m6299constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3866getBlack0d7_KjU(), null);
        int iM4193getButtKaPHkGw = StrokeCap.INSTANCE.m4193getButtKaPHkGw();
        int iM4203getBevelLxFBmk8 = StrokeJoin.INSTANCE.m4203getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(13.0f, 19.0f);
        pathBuilder.verticalLineToRelative(-2.0f);
        pathBuilder.horizontalLineToRelative(5.0f);
        pathBuilder.verticalLineToRelative(-2.0f);
        pathBuilder.horizontalLineToRelative(-8.0f);
        pathBuilder.curveToRelative(-1.66f, 0.0f, -3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilder.curveToRelative(0.0f, -1.09f, 0.59f, -2.04f, 1.46f, -2.56f);
        pathBuilder.curveTo(8.17f, 9.03f, 8.0f, 8.54f, 8.0f, 8.0f);
        pathBuilder.curveToRelative(0.0f, -0.21f, 0.04f, -0.42f, 0.09f, -0.62f);
        pathBuilder.curveTo(6.28f, 8.13f, 5.0f, 9.92f, 5.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, 2.76f, 2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.horizontalLineTo(5.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.horizontalLineToRelative(14.0f);
        pathBuilder.verticalLineToRelative(-2.0f);
        pathBuilder.horizontalLineTo(13.0f);
        pathBuilder.close();
        ImageVector.Builder.m4530addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM4193getButtKaPHkGw, iM4203getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(Color.INSTANCE.m3866getBlack0d7_KjU(), null);
        int iM4193getButtKaPHkGw2 = StrokeCap.INSTANCE.m4193getButtKaPHkGw();
        int iM4203getBevelLxFBmk9 = StrokeJoin.INSTANCE.m4203getBevelLxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(10.56f, 5.51f);
        pathBuilder2.curveTo(11.91f, 5.54f, 13.0f, 6.64f, 13.0f, 8.0f);
        pathBuilder2.curveToRelative(0.0f, 0.75f, -0.33f, 1.41f, -0.85f, 1.87f);
        pathBuilder2.lineToRelative(0.59f, 1.62f);
        pathBuilder2.lineToRelative(0.94f, -0.34f);
        pathBuilder2.lineToRelative(0.34f, 0.94f);
        pathBuilder2.lineToRelative(1.88f, -0.68f);
        pathBuilder2.lineToRelative(-0.34f, -0.94f);
        pathBuilder2.lineToRelative(0.94f, -0.34f);
        pathBuilder2.lineTo(13.76f, 2.6f);
        pathBuilder2.lineToRelative(-0.94f, 0.34f);
        pathBuilder2.lineTo(12.48f, 2.0f);
        pathBuilder2.lineTo(10.6f, 2.68f);
        pathBuilder2.lineToRelative(0.34f, 0.94f);
        pathBuilder2.lineTo(10.0f, 3.97f);
        pathBuilder2.lineTo(10.56f, 5.51f);
        pathBuilder2.close();
        ImageVector.Builder.m4530addPathoIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM4193getButtKaPHkGw2, iM4203getBevelLxFBmk9, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(Color.INSTANCE.m3866getBlack0d7_KjU(), null);
        int iM4193getButtKaPHkGw3 = StrokeCap.INSTANCE.m4193getButtKaPHkGw();
        int iM4203getBevelLxFBmk10 = StrokeJoin.INSTANCE.m4203getBevelLxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(10.5f, 8.0f);
        pathBuilder3.moveToRelative(-1.5f, 0.0f);
        pathBuilder3.arcToRelative(1.5f, 1.5f, 0.0f, true, true, 3.0f, 0.0f);
        pathBuilder3.arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.m4530addPathoIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM4193getButtKaPHkGw3, iM4203getBevelLxFBmk10, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _biotech = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
