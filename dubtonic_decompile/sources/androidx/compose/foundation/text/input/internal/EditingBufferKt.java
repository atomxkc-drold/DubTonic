package androidx.compose.foundation.text.input.internal;

import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import kotlin.Metadata;

/* JADX INFO: compiled from: EditingBuffer.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\"\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0006"}, d2 = {"updateRangeAfterDelete", "Landroidx/compose/ui/text/TextRange;", "target", "deleted", "updateRangeAfterDelete-pWDy79M", "(JJ)J", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EditingBufferKt {
    /* JADX INFO: renamed from: updateRangeAfterDelete-pWDy79M, reason: not valid java name */
    public static final long m1144updateRangeAfterDeletepWDy79M(long j, long j2) {
        int iM5780getLengthimpl;
        int iM5782getMinimpl = TextRange.m5782getMinimpl(j);
        int iM5781getMaximpl = TextRange.m5781getMaximpl(j);
        if (TextRange.m5786intersects5zctL8(j2, j)) {
            if (TextRange.m5774contains5zctL8(j2, j)) {
                iM5782getMinimpl = TextRange.m5782getMinimpl(j2);
                iM5781getMaximpl = iM5782getMinimpl;
            } else {
                if (TextRange.m5774contains5zctL8(j, j2)) {
                    iM5780getLengthimpl = TextRange.m5780getLengthimpl(j2);
                } else if (TextRange.m5775containsimpl(j2, iM5782getMinimpl)) {
                    iM5782getMinimpl = TextRange.m5782getMinimpl(j2);
                    iM5780getLengthimpl = TextRange.m5780getLengthimpl(j2);
                } else {
                    iM5781getMaximpl = TextRange.m5782getMinimpl(j2);
                }
                iM5781getMaximpl -= iM5780getLengthimpl;
            }
        } else if (iM5781getMaximpl > TextRange.m5782getMinimpl(j2)) {
            iM5782getMinimpl -= TextRange.m5780getLengthimpl(j2);
            iM5780getLengthimpl = TextRange.m5780getLengthimpl(j2);
            iM5781getMaximpl -= iM5780getLengthimpl;
        }
        return TextRangeKt.TextRange(iM5782getMinimpl, iM5781getMaximpl);
    }
}
