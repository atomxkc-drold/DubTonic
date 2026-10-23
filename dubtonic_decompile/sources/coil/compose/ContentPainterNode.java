package coil.compose;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.ScaleFactor;
import androidx.compose.ui.layout.ScaleFactorKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: ContentPainterModifier.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B/\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u001a\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020(H\u0002ø\u0001\u0000¢\u0006\u0004\b*\u0010+J\u001a\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-H\u0002ø\u0001\u0000¢\u0006\u0004\b/\u0010+J\f\u00100\u001a\u000201*\u000202H\u0016J\u001c\u00103\u001a\u000204*\u0002052\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000204H\u0016J\u001c\u00109\u001a\u000204*\u0002052\u0006\u00106\u001a\u0002072\u0006\u0010:\u001a\u000204H\u0016J&\u0010;\u001a\u00020<*\u00020=2\u0006\u00106\u001a\u00020>2\u0006\u0010.\u001a\u00020-H\u0016ø\u0001\u0000¢\u0006\u0004\b?\u0010@J\u001c\u0010A\u001a\u000204*\u0002052\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u000204H\u0016J\u001c\u0010B\u001a\u000204*\u0002052\u0006\u00106\u001a\u0002072\u0006\u0010:\u001a\u000204H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020$8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006C"}, d2 = {"Lcoil/compose/ContentPainterNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "alignment", "Landroidx/compose/ui/Alignment;", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "alpha", "", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "(Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;)V", "getAlignment", "()Landroidx/compose/ui/Alignment;", "setAlignment", "(Landroidx/compose/ui/Alignment;)V", "getAlpha", "()F", "setAlpha", "(F)V", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "getContentScale", "()Landroidx/compose/ui/layout/ContentScale;", "setContentScale", "(Landroidx/compose/ui/layout/ContentScale;)V", "getPainter", "()Landroidx/compose/ui/graphics/painter/Painter;", "setPainter", "(Landroidx/compose/ui/graphics/painter/Painter;)V", "shouldAutoInvalidate", "", "getShouldAutoInvalidate", "()Z", "calculateScaledSize", "Landroidx/compose/ui/geometry/Size;", "dstSize", "calculateScaledSize-E7KxVPU", "(J)J", "modifyConstraints", "Landroidx/compose/ui/unit/Constraints;", "constraints", "modifyConstraints-ZezNO4M", "draw", "", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "maxIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurable", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "maxIntrinsicWidth", "height", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "coil-compose-base_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class ContentPainterNode extends Modifier.Node implements DrawModifierNode, LayoutModifierNode {
    public static final int $stable = 8;
    private Alignment alignment;
    private float alpha;
    private ColorFilter colorFilter;
    private ContentScale contentScale;
    private Painter painter;

    @Override // androidx.compose.ui.Modifier.Node
    public boolean getShouldAutoInvalidate() {
        return false;
    }

    public final Painter getPainter() {
        return this.painter;
    }

    public final void setPainter(Painter painter) {
        this.painter = painter;
    }

    public final Alignment getAlignment() {
        return this.alignment;
    }

    public final void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public final ContentScale getContentScale() {
        return this.contentScale;
    }

    public final void setContentScale(ContentScale contentScale) {
        this.contentScale = contentScale;
    }

    public final float getAlpha() {
        return this.alpha;
    }

    public final void setAlpha(float f) {
        this.alpha = f;
    }

    public final ColorFilter getColorFilter() {
        return this.colorFilter;
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.colorFilter = colorFilter;
    }

    public ContentPainterNode(Painter painter, Alignment alignment, ContentScale contentScale, float f, ColorFilter colorFilter) {
        this.painter = painter;
        this.alignment = alignment;
        this.contentScale = contentScale;
        this.alpha = f;
        this.colorFilter = colorFilter;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo91measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        final Placeable placeableMo5175measureBRTryo0 = measurable.mo5175measureBRTryo0(m6969modifyConstraintsZezNO4M(j));
        return MeasureScope.layout$default(measureScope, placeableMo5175measureBRTryo0.getWidth(), placeableMo5175measureBRTryo0.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: coil.compose.ContentPainterNode$measure$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope placementScope) {
                Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo5175measureBRTryo0, 0, 0, 0.0f, 4, null);
            }
        }, 4, null);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (this.painter.getIntrinsicSize() != Size.INSTANCE.m3676getUnspecifiedNHjbRc()) {
            int iMinIntrinsicWidth = intrinsicMeasurable.minIntrinsicWidth(Constraints.m6251getMaxHeightimpl(m6969modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, 0, 0, i, 7, null))));
            return Math.max(MathKt.roundToInt(Size.m3668getWidthimpl(m6968calculateScaledSizeE7KxVPU(SizeKt.Size(iMinIntrinsicWidth, i)))), iMinIntrinsicWidth);
        }
        return intrinsicMeasurable.minIntrinsicWidth(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (this.painter.getIntrinsicSize() != Size.INSTANCE.m3676getUnspecifiedNHjbRc()) {
            int iMaxIntrinsicWidth = intrinsicMeasurable.maxIntrinsicWidth(Constraints.m6251getMaxHeightimpl(m6969modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, 0, 0, i, 7, null))));
            return Math.max(MathKt.roundToInt(Size.m3668getWidthimpl(m6968calculateScaledSizeE7KxVPU(SizeKt.Size(iMaxIntrinsicWidth, i)))), iMaxIntrinsicWidth);
        }
        return intrinsicMeasurable.maxIntrinsicWidth(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (this.painter.getIntrinsicSize() != Size.INSTANCE.m3676getUnspecifiedNHjbRc()) {
            int iMinIntrinsicHeight = intrinsicMeasurable.minIntrinsicHeight(Constraints.m6252getMaxWidthimpl(m6969modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, i, 0, 0, 13, null))));
            return Math.max(MathKt.roundToInt(Size.m3665getHeightimpl(m6968calculateScaledSizeE7KxVPU(SizeKt.Size(i, iMinIntrinsicHeight)))), iMinIntrinsicHeight);
        }
        return intrinsicMeasurable.minIntrinsicHeight(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (this.painter.getIntrinsicSize() != Size.INSTANCE.m3676getUnspecifiedNHjbRc()) {
            int iMaxIntrinsicHeight = intrinsicMeasurable.maxIntrinsicHeight(Constraints.m6252getMaxWidthimpl(m6969modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, i, 0, 0, 13, null))));
            return Math.max(MathKt.roundToInt(Size.m3665getHeightimpl(m6968calculateScaledSizeE7KxVPU(SizeKt.Size(i, iMaxIntrinsicHeight)))), iMaxIntrinsicHeight);
        }
        return intrinsicMeasurable.maxIntrinsicHeight(i);
    }

    /* JADX INFO: renamed from: calculateScaledSize-E7KxVPU, reason: not valid java name */
    private final long m6968calculateScaledSizeE7KxVPU(long dstSize) {
        if (Size.m3670isEmptyimpl(dstSize)) {
            return Size.INSTANCE.m3677getZeroNHjbRc();
        }
        long intrinsicSize = this.painter.getIntrinsicSize();
        if (intrinsicSize == Size.INSTANCE.m3676getUnspecifiedNHjbRc()) {
            return dstSize;
        }
        float fM3668getWidthimpl = Size.m3668getWidthimpl(intrinsicSize);
        if (Float.isInfinite(fM3668getWidthimpl) || Float.isNaN(fM3668getWidthimpl)) {
            fM3668getWidthimpl = Size.m3668getWidthimpl(dstSize);
        }
        float fM3665getHeightimpl = Size.m3665getHeightimpl(intrinsicSize);
        if (Float.isInfinite(fM3665getHeightimpl) || Float.isNaN(fM3665getHeightimpl)) {
            fM3665getHeightimpl = Size.m3665getHeightimpl(dstSize);
        }
        long jSize = SizeKt.Size(fM3668getWidthimpl, fM3665getHeightimpl);
        long jMo5166computeScaleFactorH7hwNQA = this.contentScale.mo5166computeScaleFactorH7hwNQA(jSize, dstSize);
        float fM5264getScaleXimpl = ScaleFactor.m5264getScaleXimpl(jMo5166computeScaleFactorH7hwNQA);
        if (Float.isInfinite(fM5264getScaleXimpl) || Float.isNaN(fM5264getScaleXimpl)) {
            return dstSize;
        }
        float fM5265getScaleYimpl = ScaleFactor.m5265getScaleYimpl(jMo5166computeScaleFactorH7hwNQA);
        return (Float.isInfinite(fM5265getScaleYimpl) || Float.isNaN(fM5265getScaleYimpl)) ? dstSize : ScaleFactorKt.m5280timesmw2e94(jMo5166computeScaleFactorH7hwNQA, jSize);
    }

    /* JADX INFO: renamed from: modifyConstraints-ZezNO4M, reason: not valid java name */
    private final long m6969modifyConstraintsZezNO4M(long constraints) {
        float fM6254getMinWidthimpl;
        int iM6253getMinHeightimpl;
        float fM6999constrainHeightK40F9xA;
        boolean zM6250getHasFixedWidthimpl = Constraints.m6250getHasFixedWidthimpl(constraints);
        boolean zM6249getHasFixedHeightimpl = Constraints.m6249getHasFixedHeightimpl(constraints);
        if (zM6250getHasFixedWidthimpl && zM6249getHasFixedHeightimpl) {
            return constraints;
        }
        boolean z = Constraints.m6248getHasBoundedWidthimpl(constraints) && Constraints.m6247getHasBoundedHeightimpl(constraints);
        long intrinsicSize = this.painter.getIntrinsicSize();
        if (intrinsicSize == Size.INSTANCE.m3676getUnspecifiedNHjbRc()) {
            return z ? Constraints.m6243copyZbe2FdA$default(constraints, Constraints.m6252getMaxWidthimpl(constraints), 0, Constraints.m6251getMaxHeightimpl(constraints), 0, 10, null) : constraints;
        }
        if (z && (zM6250getHasFixedWidthimpl || zM6249getHasFixedHeightimpl)) {
            fM6254getMinWidthimpl = Constraints.m6252getMaxWidthimpl(constraints);
            iM6253getMinHeightimpl = Constraints.m6251getMaxHeightimpl(constraints);
        } else {
            float fM3668getWidthimpl = Size.m3668getWidthimpl(intrinsicSize);
            float fM3665getHeightimpl = Size.m3665getHeightimpl(intrinsicSize);
            fM6254getMinWidthimpl = (Float.isInfinite(fM3668getWidthimpl) || Float.isNaN(fM3668getWidthimpl)) ? Constraints.m6254getMinWidthimpl(constraints) : UtilsKt.m7000constrainWidthK40F9xA(constraints, fM3668getWidthimpl);
            if (Float.isInfinite(fM3665getHeightimpl) || Float.isNaN(fM3665getHeightimpl)) {
                iM6253getMinHeightimpl = Constraints.m6253getMinHeightimpl(constraints);
            } else {
                fM6999constrainHeightK40F9xA = UtilsKt.m6999constrainHeightK40F9xA(constraints, fM3665getHeightimpl);
            }
            long jM6968calculateScaledSizeE7KxVPU = m6968calculateScaledSizeE7KxVPU(SizeKt.Size(fM6254getMinWidthimpl, fM6999constrainHeightK40F9xA));
            return Constraints.m6243copyZbe2FdA$default(constraints, ConstraintsKt.m6269constrainWidthK40F9xA(constraints, MathKt.roundToInt(Size.m3668getWidthimpl(jM6968calculateScaledSizeE7KxVPU))), 0, ConstraintsKt.m6268constrainHeightK40F9xA(constraints, MathKt.roundToInt(Size.m3665getHeightimpl(jM6968calculateScaledSizeE7KxVPU))), 0, 10, null);
        }
        fM6999constrainHeightK40F9xA = iM6253getMinHeightimpl;
        long jM6968calculateScaledSizeE7KxVPU2 = m6968calculateScaledSizeE7KxVPU(SizeKt.Size(fM6254getMinWidthimpl, fM6999constrainHeightK40F9xA));
        return Constraints.m6243copyZbe2FdA$default(constraints, ConstraintsKt.m6269constrainWidthK40F9xA(constraints, MathKt.roundToInt(Size.m3668getWidthimpl(jM6968calculateScaledSizeE7KxVPU2))), 0, ConstraintsKt.m6268constrainHeightK40F9xA(constraints, MathKt.roundToInt(Size.m3665getHeightimpl(jM6968calculateScaledSizeE7KxVPU2))), 0, 10, null);
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope contentDrawScope) {
        long jM6968calculateScaledSizeE7KxVPU = m6968calculateScaledSizeE7KxVPU(contentDrawScope.mo4396getSizeNHjbRc());
        long jMo3445alignKFBX0sM = this.alignment.mo3445alignKFBX0sM(UtilsKt.m7002toIntSizeuvyYCjk(jM6968calculateScaledSizeE7KxVPU), UtilsKt.m7002toIntSizeuvyYCjk(contentDrawScope.mo4396getSizeNHjbRc()), contentDrawScope.getLayoutDirection());
        ContentDrawScope contentDrawScope2 = contentDrawScope;
        float fM6419component1impl = IntOffset.m6419component1impl(jMo3445alignKFBX0sM);
        float fM6420component2impl = IntOffset.m6420component2impl(jMo3445alignKFBX0sM);
        contentDrawScope2.getDrawContext().getTransform().translate(fM6419component1impl, fM6420component2impl);
        this.painter.m4522drawx_KDEd0(contentDrawScope2, jM6968calculateScaledSizeE7KxVPU, this.alpha, this.colorFilter);
        contentDrawScope2.getDrawContext().getTransform().translate(-fM6419component1impl, -fM6420component2impl);
        contentDrawScope.drawContent();
    }
}
