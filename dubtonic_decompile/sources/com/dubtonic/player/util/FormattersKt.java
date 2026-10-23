package com.dubtonic.player.util;

import androidx.autofill.HintConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: Formatters.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0003\u001a\u0010\u0010\u0007\u001a\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u000e\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u0001\u001a\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0001¨\u0006\r"}, d2 = {"formatTime", "", "totalSeconds", "", "formatDuration", "trackCountLabel", "n", "greeting", "hour", "", "initials", HintConstants.AUTOFILL_HINT_NAME, "hueOf", "app_release"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class FormattersKt {
    public static final String formatTime(long j) {
        long jCoerceAtLeast = RangesKt.coerceAtLeast(j, 0L);
        long j2 = 3600;
        long j3 = jCoerceAtLeast / j2;
        long j4 = 60;
        long j5 = (jCoerceAtLeast % j2) / j4;
        long j6 = jCoerceAtLeast % j4;
        if (j3 > 0) {
            String str = String.format(Locale.ROOT, "%d:%02d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(j3), Long.valueOf(j5), Long.valueOf(j6)}, 3));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            return str;
        }
        String str2 = String.format(Locale.ROOT, "%d:%02d", Arrays.copyOf(new Object[]{Long.valueOf(j5), Long.valueOf(j6)}, 2));
        Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
        return str2;
    }

    public static final String formatDuration(long j) {
        long jCoerceAtLeast = RangesKt.coerceAtLeast(j, 0L);
        long j2 = 3600;
        long j3 = jCoerceAtLeast / j2;
        long j4 = (jCoerceAtLeast % j2) / ((long) 60);
        if (j3 > 0 && j4 > 0) {
            return j3 + " hr " + j4 + " min";
        }
        if (j3 > 0) {
            return j3 + " hr";
        }
        if (j4 > 0) {
            return j4 + " min";
        }
        return jCoerceAtLeast + " sec";
    }

    public static final String trackCountLabel(long j) {
        return j == 1 ? "1 track" : j + " tracks";
    }

    public static /* synthetic */ String greeting$default(int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = Calendar.getInstance().get(11);
        }
        return greeting(i);
    }

    public static final String greeting(int i) {
        if (5 <= i && i < 12) {
            return "Good morning";
        }
        if (12 <= i && i < 18) {
            return "Good afternoon";
        }
        return "Good evening";
    }

    public static final String initials(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        List<String> listSplit = new Regex("\\s+").split(StringsKt.trim((CharSequence) name).toString(), 0);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listSplit) {
            if (!StringsKt.isBlank((String) obj)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            return "♪";
        }
        if (arrayList2.size() == 1) {
            String strTake = StringsKt.take((String) arrayList2.get(0), 2);
            Locale ROOT = Locale.ROOT;
            Intrinsics.checkNotNullExpressionValue(ROOT, "ROOT");
            String upperCase = strTake.toUpperCase(ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            return upperCase;
        }
        String str = StringsKt.first((CharSequence) arrayList2.get(0)) + String.valueOf(StringsKt.first((CharSequence) arrayList2.get(1)));
        Locale ROOT2 = Locale.ROOT;
        Intrinsics.checkNotNullExpressionValue(ROOT2, "ROOT");
        String upperCase2 = str.toUpperCase(ROOT2);
        Intrinsics.checkNotNullExpressionValue(upperCase2, "toUpperCase(...)");
        return upperCase2;
    }

    public static final int hueOf(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        int length = name.length();
        int iCharAt = 0;
        for (int i = 0; i < length; i++) {
            iCharAt = ((iCharAt * 31) + name.charAt(i)) % 360;
        }
        return iCharAt;
    }
}
