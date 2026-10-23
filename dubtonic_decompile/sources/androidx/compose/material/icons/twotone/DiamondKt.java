package androidx.compose.material.icons.twotone;

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

/* JADX INFO: compiled from: Diamond.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_diamond", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Diamond", "Landroidx/compose/material/icons/Icons$TwoTone;", "getDiamond", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DiamondKt {
    private static ImageVector _diamond;

    public static final ImageVector getDiamond(Icons.TwoTone twoTone) {
        ImageVector imageVector = _diamond;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Diamond", Dp.m6299constructorimpl(24.0f), Dp.m6299constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3866getBlack0d7_KjU(), null);
        int iM4193getButtKaPHkGw = StrokeCap.INSTANCE.m4193getButtKaPHkGw();
        int iM4203getBevelLxFBmk8 = StrokeJoin.INSTANCE.m4203getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(8.88f, 5.0f);
        pathBuilder.lineToRelative(-2.64f, 0.0f);
        pathBuilder.lineToRelative(-1.5f, 3.0f);
        pathBuilder.lineToRelative(2.64f, 0.0f);
        pathBuilder.close();
        ImageVector.Builder.m4530addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM4193getButtKaPHkGw, iM4203getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(Color.INSTANCE.m3866getBlack0d7_KjU(), null);
        int iM4193getButtKaPHkGw2 = StrokeCap.INSTANCE.m4193getButtKaPHkGw();
        int iM4203getBevelLxFBmk9 = StrokeJoin.INSTANCE.m4203getBevelLxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(19.26f, 8.0f);
        pathBuilder2.lineToRelative(-1.5f, -3.0f);
        pathBuilder2.lineToRelative(-2.64f, 0.0f);
        pathBuilder2.lineToRelative(1.5f, 3.0f);
        pathBuilder2.close();
        ImageVector.Builder.m4530addPathoIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 0.3f, null, 0.3f, 1.0f, iM4193getButtKaPHkGw2, iM4203getBevelLxFBmk9, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(Color.INSTANCE.m3866getBlack0d7_KjU(), null);
        int iM4193getButtKaPHkGw3 = StrokeCap.INSTANCE.m4193getButtKaPHkGw();
        int iM4203getBevelLxFBmk10 = StrokeJoin.INSTANCE.m4203getBevelLxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(11.0f, 16.68f);
        pathBuilder3.lineToRelative(0.0f, -6.68f);
        pathBuilder3.lineToRelative(-5.56f, 0.0f);
        pathBuilder3.close();
        ImageVector.Builder.m4530addPathoIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 0.3f, null, 0.3f, 1.0f, iM4193getButtKaPHkGw3, iM4203getBevelLxFBmk10, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(Color.INSTANCE.m3866getBlack0d7_KjU(), null);
        int iM4193getButtKaPHkGw4 = StrokeCap.INSTANCE.m4193getButtKaPHkGw();
        int iM4203getBevelLxFBmk11 = StrokeJoin.INSTANCE.m4203getBevelLxFBmk8();
        PathBuilder pathBuilder4 = new PathBuilder();
        pathBuilder4.moveTo(13.0f, 16.68f);
        pathBuilder4.lineToRelative(5.56f, -6.68f);
        pathBuilder4.lineToRelative(-5.56f, 0.0f);
        pathBuilder4.close();
        ImageVector.Builder.m4530addPathoIyEayM$default(builder, pathBuilder4.getNodes(), defaultFillType4, "", solidColor4, 0.3f, null, 0.3f, 1.0f, iM4193getButtKaPHkGw4, iM4203getBevelLxFBmk11, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType5 = VectorKt.getDefaultFillType();
        SolidColor solidColor5 = new SolidColor(Color.INSTANCE.m3866getBlack0d7_KjU(), null);
        int iM4193getButtKaPHkGw5 = StrokeCap.INSTANCE.m4193getButtKaPHkGw();
        int iM4203getBevelLxFBmk12 = StrokeJoin.INSTANCE.m4203getBevelLxFBmk8();
        PathBuilder pathBuilder5 = new PathBuilder();
        pathBuilder5.moveTo(12.88f, 5.0f);
        pathBuilder5.lineToRelative(-1.76f, 0.0f);
        pathBuilder5.lineToRelative(-1.5f, 3.0f);
        pathBuilder5.lineToRelative(4.76f, 0.0f);
        pathBuilder5.close();
        ImageVector.Builder.m4530addPathoIyEayM$default(builder, pathBuilder5.getNodes(), defaultFillType5, "", solidColor5, 0.3f, null, 0.3f, 1.0f, iM4193getButtKaPHkGw5, iM4203getBevelLxFBmk12, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType6 = VectorKt.getDefaultFillType();
        SolidColor solidColor6 = new SolidColor(Color.INSTANCE.m3866getBlack0d7_KjU(), null);
        int iM4193getButtKaPHkGw6 = StrokeCap.INSTANCE.m4193getButtKaPHkGw();
        int iM4203getBevelLxFBmk13 = StrokeJoin.INSTANCE.m4203getBevelLxFBmk8();
        PathBuilder pathBuilder6 = new PathBuilder();
        pathBuilder6.moveTo(19.0f, 3.0f);
        pathBuilder6.horizontalLineTo(5.0f);
        pathBuilder6.lineTo(2.0f, 9.0f);
        pathBuilder6.lineToRelative(10.0f, 12.0f);
        pathBuilder6.lineTo(22.0f, 9.0f);
        pathBuilder6.lineTo(19.0f, 3.0f);
        pathBuilder6.close();
        pathBuilder6.moveTo(17.76f, 5.0f);
        pathBuilder6.lineToRelative(1.5f, 3.0f);
        pathBuilder6.horizontalLineToRelative(-2.65f);
        pathBuilder6.lineToRelative(-1.5f, -3.0f);
        pathBuilder6.horizontalLineTo(17.76f);
        pathBuilder6.close();
        pathBuilder6.moveTo(6.24f, 5.0f);
        pathBuilder6.horizontalLineToRelative(2.65f);
        pathBuilder6.lineToRelative(-1.5f, 3.0f);
        pathBuilder6.horizontalLineTo(4.74f);
        pathBuilder6.lineTo(6.24f, 5.0f);
        pathBuilder6.close();
        pathBuilder6.moveTo(11.0f, 16.68f);
        pathBuilder6.lineTo(5.44f, 10.0f);
        pathBuilder6.horizontalLineTo(11.0f);
        pathBuilder6.verticalLineTo(16.68f);
        pathBuilder6.close();
        pathBuilder6.moveTo(9.62f, 8.0f);
        pathBuilder6.lineToRelative(1.5f, -3.0f);
        pathBuilder6.horizontalLineToRelative(1.76f);
        pathBuilder6.lineToRelative(1.5f, 3.0f);
        pathBuilder6.horizontalLineTo(9.62f);
        pathBuilder6.close();
        pathBuilder6.moveTo(13.0f, 16.68f);
        pathBuilder6.verticalLineTo(10.0f);
        pathBuilder6.horizontalLineToRelative(5.56f);
        pathBuilder6.lineTo(13.0f, 16.68f);
        pathBuilder6.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4530addPathoIyEayM$default(builder, pathBuilder6.getNodes(), defaultFillType6, "", solidColor6, 1.0f, null, 1.0f, 1.0f, iM4193getButtKaPHkGw6, iM4203getBevelLxFBmk13, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _diamond = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
