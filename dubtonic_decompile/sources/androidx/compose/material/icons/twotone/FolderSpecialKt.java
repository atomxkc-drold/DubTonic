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

/* JADX INFO: compiled from: FolderSpecial.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_folderSpecial", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FolderSpecial", "Landroidx/compose/material/icons/Icons$TwoTone;", "getFolderSpecial", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FolderSpecialKt {
    private static ImageVector _folderSpecial;

    public static final ImageVector getFolderSpecial(Icons.TwoTone twoTone) {
        ImageVector imageVector = _folderSpecial;
        if (imageVector != null) {
            Intrinsics.checkNotNull(imageVector);
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.FolderSpecial", Dp.m6299constructorimpl(24.0f), Dp.m6299constructorimpl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.INSTANCE.m3866getBlack0d7_KjU(), null);
        int iM4193getButtKaPHkGw = StrokeCap.INSTANCE.m4193getButtKaPHkGw();
        int iM4203getBevelLxFBmk8 = StrokeJoin.INSTANCE.m4203getBevelLxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(11.17f, 8.0f);
        pathBuilder.lineToRelative(-2.0f, -2.0f);
        pathBuilder.horizontalLineTo(4.0f);
        pathBuilder.verticalLineToRelative(12.0f);
        pathBuilder.horizontalLineToRelative(16.0f);
        pathBuilder.verticalLineTo(8.0f);
        pathBuilder.horizontalLineToRelative(-8.83f);
        pathBuilder.close();
        pathBuilder.moveTo(15.0f, 9.0f);
        pathBuilder.lineToRelative(1.19f, 2.79f);
        pathBuilder.lineToRelative(3.03f, 0.26f);
        pathBuilder.lineToRelative(-2.3f, 1.99f);
        pathBuilder.lineToRelative(0.69f, 2.96f);
        pathBuilder.lineTo(15.0f, 15.47f);
        pathBuilder.lineTo(12.39f, 17.0f);
        pathBuilder.lineToRelative(0.69f, -2.96f);
        pathBuilder.lineToRelative(-2.3f, -1.99f);
        pathBuilder.lineToRelative(3.03f, -0.26f);
        pathBuilder.lineTo(15.0f, 9.0f);
        pathBuilder.close();
        ImageVector.Builder.m4530addPathoIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, null, 0.3f, 1.0f, iM4193getButtKaPHkGw, iM4203getBevelLxFBmk8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(Color.INSTANCE.m3866getBlack0d7_KjU(), null);
        int iM4193getButtKaPHkGw2 = StrokeCap.INSTANCE.m4193getButtKaPHkGw();
        int iM4203getBevelLxFBmk9 = StrokeJoin.INSTANCE.m4203getBevelLxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(20.0f, 6.0f);
        pathBuilder2.horizontalLineToRelative(-8.0f);
        pathBuilder2.lineToRelative(-2.0f, -2.0f);
        pathBuilder2.lineTo(4.0f, 4.0f);
        pathBuilder2.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilder2.verticalLineToRelative(12.0f);
        pathBuilder2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder2.horizontalLineToRelative(16.0f);
        pathBuilder2.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder2.lineTo(22.0f, 8.0f);
        pathBuilder2.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(20.0f, 18.0f);
        pathBuilder2.lineTo(4.0f, 18.0f);
        pathBuilder2.lineTo(4.0f, 6.0f);
        pathBuilder2.horizontalLineToRelative(5.17f);
        pathBuilder2.lineToRelative(2.0f, 2.0f);
        pathBuilder2.lineTo(20.0f, 8.0f);
        pathBuilder2.verticalLineToRelative(10.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(13.08f, 14.04f);
        pathBuilder2.lineTo(12.39f, 17.0f);
        pathBuilder2.lineTo(15.0f, 15.47f);
        pathBuilder2.lineTo(17.61f, 17.0f);
        pathBuilder2.lineToRelative(-0.69f, -2.96f);
        pathBuilder2.lineToRelative(2.3f, -1.99f);
        pathBuilder2.lineToRelative(-3.03f, -0.26f);
        pathBuilder2.lineTo(15.0f, 9.0f);
        pathBuilder2.lineToRelative(-1.19f, 2.79f);
        pathBuilder2.lineToRelative(-3.03f, 0.26f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.m4530addPathoIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, null, 1.0f, 1.0f, iM4193getButtKaPHkGw2, iM4203getBevelLxFBmk9, 1.0f, 0.0f, 0.0f, 0.0f, 14336, null).build();
        _folderSpecial = imageVectorBuild;
        Intrinsics.checkNotNull(imageVectorBuild);
        return imageVectorBuild;
    }
}
