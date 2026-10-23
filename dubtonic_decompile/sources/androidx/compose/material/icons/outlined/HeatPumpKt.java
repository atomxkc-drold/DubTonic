package androidx.compose.material.icons.outlined;

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

/* JADX INFO: compiled from: HeatPump.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_heatPump", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HeatPump", "Landroidx/compose/material/icons/Icons$Outlined;", "getHeatPump", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HeatPumpKt {
    private static ImageVector _heatPump;

    public static final ImageVector getHeatPump(Icons.Outlined outlined) {
        ImageVector imageVector = _heatPump;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.HeatPump", Dp.m6299constructorimpl(24.0f), Dp.m6299constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3866getBlack0d7_KjU(), null);
        int iM4193getButtKaPHkGw = StrokeCap.INSTANCE.m4193getButtKaPHkGw();
        int iM4203getBevelLxFBmk8 = StrokeJoin.INSTANCE.m4203getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(19.0f, 3.0f);
        pathBuilder.horizontalLineTo(5.0f);
        pathBuilder.curveTo(3.9f, 3.0f, 3.0f, 3.9f, 3.0f, 5.0f);
        pathBuilder.verticalLineToRelative(14.0f);
        pathBuilder.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder.horizontalLineToRelative(14.0f);
        pathBuilder.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder.verticalLineTo(5.0f);
        pathBuilder.curveTo(21.0f, 3.9f, 20.1f, 3.0f, 19.0f, 3.0f);
        pathBuilder.close();
        pathBuilder.moveTo(19.0f, 19.0f);
        pathBuilder.horizontalLineTo(5.0f);
        pathBuilder.verticalLineTo(5.0f);
        pathBuilder.horizontalLineToRelative(14.0f);
        pathBuilder.verticalLineTo(19.0f);
        pathBuilder.close();
        ImageVector.Builder.m4530addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, null, 1.0f, 1.0f, iM4193getButtKaPHkGw, iM4203getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(Color.INSTANCE.m3866getBlack0d7_KjU(), null);
        int iM4193getButtKaPHkGw2 = StrokeCap.INSTANCE.m4193getButtKaPHkGw();
        int iM4203getBevelLxFBmk9 = StrokeJoin.INSTANCE.m4203getBevelLxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(12.0f, 18.0f);
        pathBuilder2.curveToRelative(3.31f, 0.0f, 6.0f, -2.69f, 6.0f, -6.0f);
        pathBuilder2.reflectiveCurveToRelative(-2.69f, -6.0f, -6.0f, -6.0f);
        pathBuilder2.reflectiveCurveToRelative(-6.0f, 2.69f, -6.0f, 6.0f);
        pathBuilder2.reflectiveCurveTo(8.69f, 18.0f, 12.0f, 18.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(11.25f, 15.92f);
        pathBuilder2.curveToRelative(-0.55f, -0.1f, -1.05f, -0.32f, -1.5f, -0.62f);
        pathBuilder2.lineToRelative(1.5f, -1.5f);
        pathBuilder2.verticalLineTo(15.92f);
        pathBuilder2.close();
        pathBuilder2.moveTo(12.75f, 15.92f);
        pathBuilder2.verticalLineToRelative(-2.11f);
        pathBuilder2.lineToRelative(1.5f, 1.5f);
        pathBuilder2.curveTo(13.8f, 15.61f, 13.3f, 15.82f, 12.75f, 15.92f);
        pathBuilder2.close();
        pathBuilder2.moveTo(15.31f, 14.25f);
        pathBuilder2.lineToRelative(-1.5f, -1.5f);
        pathBuilder2.horizontalLineToRelative(2.11f);
        pathBuilder2.curveTo(15.82f, 13.3f, 15.61f, 13.8f, 15.31f, 14.25f);
        pathBuilder2.close();
        pathBuilder2.moveTo(15.92f, 11.25f);
        pathBuilder2.horizontalLineToRelative(-2.11f);
        pathBuilder2.lineToRelative(1.5f, -1.5f);
        pathBuilder2.curveTo(15.61f, 10.2f, 15.82f, 10.7f, 15.92f, 11.25f);
        pathBuilder2.close();
        pathBuilder2.moveTo(12.75f, 8.08f);
        pathBuilder2.curveToRelative(0.55f, 0.1f, 1.05f, 0.32f, 1.5f, 0.62f);
        pathBuilder2.lineToRelative(-1.5f, 1.5f);
        pathBuilder2.verticalLineTo(8.08f);
        pathBuilder2.close();
        pathBuilder2.moveTo(12.0f, 11.0f);
        pathBuilder2.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilder2.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilder2.reflectiveCurveToRelative(-1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilder2.curveTo(11.0f, 11.45f, 11.45f, 11.0f, 12.0f, 11.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(11.25f, 8.08f);
        pathBuilder2.verticalLineToRelative(2.11f);
        pathBuilder2.lineToRelative(-1.5f, -1.5f);
        pathBuilder2.curveTo(10.2f, 8.39f, 10.7f, 8.18f, 11.25f, 8.08f);
        pathBuilder2.close();
        pathBuilder2.moveTo(8.69f, 9.75f);
        pathBuilder2.lineToRelative(1.5f, 1.5f);
        pathBuilder2.horizontalLineTo(8.08f);
        pathBuilder2.curveTo(8.18f, 10.7f, 8.39f, 10.2f, 8.69f, 9.75f);
        pathBuilder2.close();
        pathBuilder2.moveTo(10.19f, 12.75f);
        pathBuilder2.lineToRelative(-1.5f, 1.5f);
        pathBuilder2.curveToRelative(-0.3f, -0.44f, -0.51f, -0.95f, -0.62f, -1.5f);
        pathBuilder2.horizontalLineTo(10.19f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4530addPathoIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM4193getButtKaPHkGw2, iM4203getBevelLxFBmk9, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _heatPump = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
