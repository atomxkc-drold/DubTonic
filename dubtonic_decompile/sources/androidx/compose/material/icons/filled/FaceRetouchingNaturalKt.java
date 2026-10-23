package androidx.compose.material.icons.filled;

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

/* JADX INFO: compiled from: FaceRetouchingNatural.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_faceRetouchingNatural", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FaceRetouchingNatural", "Landroidx/compose/material/icons/Icons$Filled;", "getFaceRetouchingNatural", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FaceRetouchingNaturalKt {
    private static ImageVector _faceRetouchingNatural;

    public static final ImageVector getFaceRetouchingNatural(Icons.Filled filled) {
        ImageVector imageVector = _faceRetouchingNatural;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.FaceRetouchingNatural", Dp.m6299constructorimpl(24.0f), Dp.m6299constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3866getBlack0d7_KjU(), null);
        int iM4193getButtKaPHkGw = StrokeCap.INSTANCE.m4193getButtKaPHkGw();
        int iM4203getBevelLxFBmk8 = StrokeJoin.INSTANCE.m4203getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(9.0f, 13.0f);
        pathBuilder.moveToRelative(-1.25f, 0.0f);
        pathBuilder.arcToRelative(1.25f, 1.25f, 0.0f, true, true, 2.5f, 0.0f);
        pathBuilder.arcToRelative(1.25f, 1.25f, 0.0f, true, true, -2.5f, 0.0f);
        ImageVector.Builder.m4530addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM4193getButtKaPHkGw, iM4203getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(Color.INSTANCE.m3866getBlack0d7_KjU(), null);
        int iM4193getButtKaPHkGw2 = StrokeCap.INSTANCE.m4193getButtKaPHkGw();
        int iM4203getBevelLxFBmk9 = StrokeJoin.INSTANCE.m4203getBevelLxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(20.77f, 8.58f);
        pathBuilder2.lineToRelative(-0.92f, 2.01f);
        pathBuilder2.curveToRelative(0.09f, 0.46f, 0.15f, 0.93f, 0.15f, 1.41f);
        pathBuilder2.curveToRelative(0.0f, 4.41f, -3.59f, 8.0f, -8.0f, 8.0f);
        pathBuilder2.reflectiveCurveToRelative(-8.0f, -3.59f, -8.0f, -8.0f);
        pathBuilder2.curveToRelative(0.0f, -0.05f, 0.01f, -0.1f, 0.0f, -0.14f);
        pathBuilder2.curveToRelative(2.6f, -0.98f, 4.69f, -2.99f, 5.74f, -5.55f);
        pathBuilder2.curveTo(11.58f, 8.56f, 14.37f, 10.0f, 17.5f, 10.0f);
        pathBuilder2.curveToRelative(0.45f, 0.0f, 0.89f, -0.04f, 1.33f, -0.1f);
        pathBuilder2.lineToRelative(-0.6f, -1.32f);
        pathBuilder2.lineToRelative(-0.88f, -1.93f);
        pathBuilder2.lineToRelative(-1.93f, -0.88f);
        pathBuilder2.lineToRelative(-2.79f, -1.27f);
        pathBuilder2.lineToRelative(2.79f, -1.27f);
        pathBuilder2.lineToRelative(0.71f, -0.32f);
        pathBuilder2.curveTo(14.87f, 2.33f, 13.47f, 2.0f, 12.0f, 2.0f);
        pathBuilder2.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilder2.reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilder2.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilder2.curveToRelative(0.0f, -1.47f, -0.33f, -2.87f, -0.9f, -4.13f);
        pathBuilder2.lineToRelative(-0.33f, 0.71f);
        pathBuilder2.close();
        ImageVector.Builder.m4530addPathoIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM4193getButtKaPHkGw2, iM4203getBevelLxFBmk9, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(Color.INSTANCE.m3866getBlack0d7_KjU(), null);
        int iM4193getButtKaPHkGw3 = StrokeCap.INSTANCE.m4193getButtKaPHkGw();
        int iM4203getBevelLxFBmk10 = StrokeJoin.INSTANCE.m4203getBevelLxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(15.0f, 13.0f);
        pathBuilder3.moveToRelative(-1.25f, 0.0f);
        pathBuilder3.arcToRelative(1.25f, 1.25f, 0.0f, true, true, 2.5f, 0.0f);
        pathBuilder3.arcToRelative(1.25f, 1.25f, 0.0f, true, true, -2.5f, 0.0f);
        ImageVector.Builder.m4530addPathoIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM4193getButtKaPHkGw3, iM4203getBevelLxFBmk10, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(Color.INSTANCE.m3866getBlack0d7_KjU(), null);
        int iM4193getButtKaPHkGw4 = StrokeCap.INSTANCE.m4193getButtKaPHkGw();
        int iM4203getBevelLxFBmk11 = StrokeJoin.INSTANCE.m4203getBevelLxFBmk8();
        PathBuilder pathBuilder4 = new PathBuilder();
        pathBuilder4.moveTo(20.6f, 5.6f);
        pathBuilder4.lineTo(19.5f, 8.0f);
        pathBuilder4.lineToRelative(-1.1f, -2.4f);
        pathBuilder4.lineTo(16.0f, 4.5f);
        pathBuilder4.lineToRelative(2.4f, -1.1f);
        pathBuilder4.lineTo(19.5f, 1.0f);
        pathBuilder4.lineToRelative(1.1f, 2.4f);
        pathBuilder4.lineTo(23.0f, 4.5f);
        pathBuilder4.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4530addPathoIyEayM$default(builder, pathBuilder4.getNodes(), defaultFillType4, "", solidColor4, 1.0f, null, 1.0f, 1.0f, iM4193getButtKaPHkGw4, iM4203getBevelLxFBmk11, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _faceRetouchingNatural = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
