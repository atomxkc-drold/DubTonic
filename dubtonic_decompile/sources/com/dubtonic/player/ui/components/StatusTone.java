package com.dubtonic.player.ui.components;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.dubtonic.player.ui.theme.DubColors;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Bars.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rj\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\u000e"}, d2 = {"Lcom/dubtonic/player/ui/components/StatusTone;", "", "<init>", "(Ljava/lang/String;I)V", "ONLINE", "OFFLINE", "DEMO", "CONNECTING", TtmlNode.ATTR_TTS_COLOR, "Landroidx/compose/ui/graphics/Color;", "colors", "Lcom/dubtonic/player/ui/theme/DubColors;", "color-vNxB06k", "(Lcom/dubtonic/player/ui/theme/DubColors;)J", "app_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public enum StatusTone {
    ONLINE,
    OFFLINE,
    DEMO,
    CONNECTING;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: compiled from: Bars.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StatusTone.values().length];
            try {
                iArr[StatusTone.ONLINE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[StatusTone.OFFLINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[StatusTone.DEMO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[StatusTone.CONNECTING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static EnumEntries<StatusTone> getEntries() {
        return $ENTRIES;
    }

    /* JADX INFO: renamed from: color-vNxB06k, reason: not valid java name */
    public final long m7046colorvNxB06k(DubColors colors) {
        Intrinsics.checkNotNullParameter(colors, "colors");
        int i = WhenMappings.$EnumSwitchMapping$0[ordinal()];
        if (i == 1) {
            return colors.m7155getSecondary0d7_KjU();
        }
        if (i == 2) {
            return colors.m7138getDanger0d7_KjU();
        }
        if (i == 3) {
            return colors.m7151getPrimaryInk0d7_KjU();
        }
        if (i != 4) {
            throw new NoWhenBranchMatchedException();
        }
        return colors.m7155getSecondary0d7_KjU();
    }
}
