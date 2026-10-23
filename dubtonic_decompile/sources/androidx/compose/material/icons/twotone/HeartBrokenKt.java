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

/* JADX INFO: compiled from: HeartBroken.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_heartBroken", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HeartBroken", "Landroidx/compose/material/icons/Icons$TwoTone;", "getHeartBroken", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HeartBrokenKt {
    private static ImageVector _heartBroken;

    public static final ImageVector getHeartBroken(Icons.TwoTone twoTone) {
        ImageVector imageVector = _heartBroken;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.HeartBroken", Dp.m6299constructorimpl(24.0f), Dp.m6299constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3866getBlack0d7_KjU(), null);
        int iM4193getButtKaPHkGw = StrokeCap.INSTANCE.m4193getButtKaPHkGw();
        int iM4203getBevelLxFBmk8 = StrokeJoin.INSTANCE.m4203getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(9.23f, 5.42f);
        pathBuilder.curveTo(8.69f, 5.15f, 8.09f, 5.0f, 7.5f, 5.0f);
        pathBuilder.curveTo(5.54f, 5.0f, 4.0f, 6.54f, 4.0f, 8.5f);
        pathBuilder.curveToRelative(0.0f, 2.5f, 2.45f, 4.84f, 6.24f, 8.23f);
        pathBuilder.lineTo(10.77f, 12.0f);
        pathBuilder.horizontalLineTo(7.35f);
        pathBuilder.lineTo(9.23f, 5.42f);
        pathBuilder.close();
        ImageVector.Builder.m4530addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM4193getButtKaPHkGw, iM4203getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(Color.INSTANCE.m3866getBlack0d7_KjU(), null);
        int iM4193getButtKaPHkGw2 = StrokeCap.INSTANCE.m4193getButtKaPHkGw();
        int iM4203getBevelLxFBmk9 = StrokeJoin.INSTANCE.m4203getBevelLxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(16.5f, 5.0f);
        pathBuilder2.curveToRelative(-0.37f, 0.0f, -0.75f, 0.06f, -1.12f, 0.18f);
        pathBuilder2.lineTo(14.77f, 7.0f);
        pathBuilder2.horizontalLineToRelative(2.91f);
        pathBuilder2.lineToRelative(-2.56f, 8.53f);
        pathBuilder2.curveTo(17.98f, 12.93f, 20.0f, 10.71f, 20.0f, 8.5f);
        pathBuilder2.curveTo(20.0f, 6.54f, 18.46f, 5.0f, 16.5f, 5.0f);
        pathBuilder2.close();
        ImageVector.Builder.m4530addPathoIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 0.3f, null, 0.3f, 1.0f, iM4193getButtKaPHkGw2, iM4203getBevelLxFBmk9, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(Color.INSTANCE.m3866getBlack0d7_KjU(), null);
        int iM4193getButtKaPHkGw3 = StrokeCap.INSTANCE.m4193getButtKaPHkGw();
        int iM4203getBevelLxFBmk10 = StrokeJoin.INSTANCE.m4203getBevelLxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(16.5f, 3.0f);
        pathBuilder3.curveToRelative(-0.96f, 0.0f, -1.9f, 0.25f, -2.73f, 0.69f);
        pathBuilder3.lineTo(12.0f, 9.0f);
        pathBuilder3.horizontalLineToRelative(3.0f);
        pathBuilder3.lineToRelative(-3.0f, 10.0f);
        pathBuilder3.lineToRelative(1.0f, -9.0f);
        pathBuilder3.horizontalLineToRelative(-3.0f);
        pathBuilder3.lineToRelative(1.54f, -5.39f);
        pathBuilder3.curveTo(10.47f, 3.61f, 9.01f, 3.0f, 7.5f, 3.0f);
        pathBuilder3.curveTo(4.42f, 3.0f, 2.0f, 5.42f, 2.0f, 8.5f);
        pathBuilder3.curveToRelative(0.0f, 4.13f, 4.16f, 7.18f, 10.0f, 12.5f);
        pathBuilder3.curveToRelative(5.47f, -4.94f, 10.0f, -8.26f, 10.0f, -12.5f);
        pathBuilder3.curveTo(22.0f, 5.42f, 19.58f, 3.0f, 16.5f, 3.0f);
        pathBuilder3.close();
        pathBuilder3.moveTo(10.24f, 16.73f);
        pathBuilder3.curveTo(6.45f, 13.34f, 4.0f, 11.0f, 4.0f, 8.5f);
        pathBuilder3.curveTo(4.0f, 6.54f, 5.54f, 5.0f, 7.5f, 5.0f);
        pathBuilder3.curveToRelative(0.59f, 0.0f, 1.19f, 0.15f, 1.73f, 0.42f);
        pathBuilder3.lineTo(7.35f, 12.0f);
        pathBuilder3.horizontalLineToRelative(3.42f);
        pathBuilder3.lineTo(10.24f, 16.73f);
        pathBuilder3.close();
        pathBuilder3.moveTo(15.13f, 15.53f);
        pathBuilder3.lineTo(17.69f, 7.0f);
        pathBuilder3.horizontalLineToRelative(-2.91f);
        pathBuilder3.lineToRelative(0.61f, -1.82f);
        pathBuilder3.curveTo(15.75f, 5.06f, 16.13f, 5.0f, 16.5f, 5.0f);
        pathBuilder3.curveTo(18.46f, 5.0f, 20.0f, 6.54f, 20.0f, 8.5f);
        pathBuilder3.curveTo(20.0f, 10.71f, 17.98f, 12.93f, 15.13f, 15.53f);
        pathBuilder3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4530addPathoIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, null, 1.0f, 1.0f, iM4193getButtKaPHkGw3, iM4203getBevelLxFBmk10, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _heartBroken = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
