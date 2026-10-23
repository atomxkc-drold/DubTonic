package androidx.compose.material3.internal;

import androidx.compose.ui.R;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Strings.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0081@\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u000e\u0010\u0005J\u0010\u0010\u000f\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002¨\u0006\u0014"}, d2 = {"Landroidx/compose/material3/internal/Strings;", "", "value", "", "constructor-impl", "(I)I", "getValue", "()I", "equals", "", "other", "equals-impl", "(ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "Companion", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@JvmInline
public final class Strings {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final int value;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Strings m2584boximpl(int i) {
        return new Strings(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static int m2585constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2586equalsimpl(int i, Object obj) {
        return (obj instanceof Strings) && i == ((Strings) obj).m2590unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2587equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2588hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2589toStringimpl(int i) {
        return "Strings(value=" + i + ')';
    }

    public boolean equals(Object obj) {
        return m2586equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m2588hashCodeimpl(this.value);
    }

    public String toString() {
        return m2589toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ int m2590unboximpl() {
        return this.value;
    }

    /* JADX INFO: compiled from: Strings.android.kt */
    @Metadata(d1 = {"\u0000\u0015\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0003\b\u0081\u0001\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0018\u0010\u0003\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0018\u0010\u0007\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0018\u0010\t\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006R\u0018\u0010\u000b\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\f\u0010\u0006R\u0018\u0010\r\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0006R\u0018\u0010\u000f\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0006R\u0018\u0010\u0011\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0006R\u0018\u0010\u0013\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0006R\u0018\u0010\u0015\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0006R\u0018\u0010\u0017\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0006R\u0018\u0010\u0019\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0006R\u0018\u0010\u001b\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0006R\u0018\u0010\u001d\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0006R\u0018\u0010\u001f\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b \u0010\u0006R\u0018\u0010!\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\"\u0010\u0006R\u0018\u0010#\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b$\u0010\u0006R\u0018\u0010%\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b&\u0010\u0006R\u0018\u0010'\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b(\u0010\u0006R\u0018\u0010)\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b*\u0010\u0006R\u0018\u0010+\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b,\u0010\u0006R\u0018\u0010-\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b.\u0010\u0006R\u0018\u0010/\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b0\u0010\u0006R\u0018\u00101\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b2\u0010\u0006R\u0018\u00103\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b4\u0010\u0006R\u0018\u00105\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b6\u0010\u0006R\u0018\u00107\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b8\u0010\u0006R\u0018\u00109\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b:\u0010\u0006R\u0018\u0010;\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b<\u0010\u0006R\u0018\u0010=\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b>\u0010\u0006R\u0018\u0010?\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b@\u0010\u0006R\u0018\u0010A\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bB\u0010\u0006R\u0018\u0010C\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bD\u0010\u0006R\u0018\u0010E\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bF\u0010\u0006R\u0018\u0010G\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bH\u0010\u0006R\u0018\u0010I\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bJ\u0010\u0006R\u0018\u0010K\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bL\u0010\u0006R\u0018\u0010M\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bN\u0010\u0006R\u0018\u0010O\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bP\u0010\u0006R\u0018\u0010Q\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bR\u0010\u0006R\u0018\u0010S\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bT\u0010\u0006R\u0018\u0010U\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bV\u0010\u0006R\u0018\u0010W\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bX\u0010\u0006R\u0018\u0010Y\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bZ\u0010\u0006R\u0018\u0010[\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\\\u0010\u0006R\u0018\u0010]\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b^\u0010\u0006R\u0018\u0010_\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b`\u0010\u0006R\u0018\u0010a\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bb\u0010\u0006R\u0018\u0010c\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bd\u0010\u0006R\u0018\u0010e\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bf\u0010\u0006R\u0018\u0010g\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bh\u0010\u0006R\u0018\u0010i\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bj\u0010\u0006R\u0018\u0010k\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bl\u0010\u0006R\u0018\u0010m\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bn\u0010\u0006R\u0018\u0010o\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bp\u0010\u0006R\u0018\u0010q\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\br\u0010\u0006R\u0018\u0010s\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bt\u0010\u0006R\u0018\u0010u\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bv\u0010\u0006R\u0018\u0010w\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bx\u0010\u0006R\u0018\u0010y\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\bz\u0010\u0006R\u0018\u0010{\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b|\u0010\u0006R\u0018\u0010}\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b~\u0010\u0006R\u0019\u0010\u007f\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0007\u001a\u0005\b\u0080\u0001\u0010\u0006R\u001a\u0010\u0081\u0001\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0007\u001a\u0005\b\u0082\u0001\u0010\u0006R\u001a\u0010\u0083\u0001\u001a\u00020\u00048Æ\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0007\u001a\u0005\b\u0084\u0001\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0085\u0001"}, d2 = {"Landroidx/compose/material3/internal/Strings$Companion;", "", "()V", "BottomSheetDismissDescription", "Landroidx/compose/material3/internal/Strings;", "getBottomSheetDismissDescription-8iCLdWM", "()I", "BottomSheetDragHandleDescription", "getBottomSheetDragHandleDescription-8iCLdWM", "BottomSheetExpandDescription", "getBottomSheetExpandDescription-8iCLdWM", "BottomSheetPaneTitle", "getBottomSheetPaneTitle-8iCLdWM", "BottomSheetPartialExpandDescription", "getBottomSheetPartialExpandDescription-8iCLdWM", "CloseDrawer", "getCloseDrawer-8iCLdWM", "CloseSheet", "getCloseSheet-8iCLdWM", "DateInputHeadline", "getDateInputHeadline-8iCLdWM", "DateInputHeadlineDescription", "getDateInputHeadlineDescription-8iCLdWM", "DateInputInvalidForPattern", "getDateInputInvalidForPattern-8iCLdWM", "DateInputInvalidNotAllowed", "getDateInputInvalidNotAllowed-8iCLdWM", "DateInputInvalidYearRange", "getDateInputInvalidYearRange-8iCLdWM", "DateInputLabel", "getDateInputLabel-8iCLdWM", "DateInputNoInputDescription", "getDateInputNoInputDescription-8iCLdWM", "DateInputTitle", "getDateInputTitle-8iCLdWM", "DatePickerHeadline", "getDatePickerHeadline-8iCLdWM", "DatePickerHeadlineDescription", "getDatePickerHeadlineDescription-8iCLdWM", "DatePickerNavigateToYearDescription", "getDatePickerNavigateToYearDescription-8iCLdWM", "DatePickerNoSelectionDescription", "getDatePickerNoSelectionDescription-8iCLdWM", "DatePickerScrollToShowEarlierYears", "getDatePickerScrollToShowEarlierYears-8iCLdWM", "DatePickerScrollToShowLaterYears", "getDatePickerScrollToShowLaterYears-8iCLdWM", "DatePickerSwitchToCalendarMode", "getDatePickerSwitchToCalendarMode-8iCLdWM", "DatePickerSwitchToDaySelection", "getDatePickerSwitchToDaySelection-8iCLdWM", "DatePickerSwitchToInputMode", "getDatePickerSwitchToInputMode-8iCLdWM", "DatePickerSwitchToNextMonth", "getDatePickerSwitchToNextMonth-8iCLdWM", "DatePickerSwitchToPreviousMonth", "getDatePickerSwitchToPreviousMonth-8iCLdWM", "DatePickerSwitchToYearSelection", "getDatePickerSwitchToYearSelection-8iCLdWM", "DatePickerTitle", "getDatePickerTitle-8iCLdWM", "DatePickerTodayDescription", "getDatePickerTodayDescription-8iCLdWM", "DatePickerYearPickerPaneTitle", "getDatePickerYearPickerPaneTitle-8iCLdWM", "DateRangeInputInvalidRangeInput", "getDateRangeInputInvalidRangeInput-8iCLdWM", "DateRangeInputTitle", "getDateRangeInputTitle-8iCLdWM", "DateRangePickerDayInRange", "getDateRangePickerDayInRange-8iCLdWM", "DateRangePickerEndHeadline", "getDateRangePickerEndHeadline-8iCLdWM", "DateRangePickerScrollToShowNextMonth", "getDateRangePickerScrollToShowNextMonth-8iCLdWM", "DateRangePickerScrollToShowPreviousMonth", "getDateRangePickerScrollToShowPreviousMonth-8iCLdWM", "DateRangePickerStartHeadline", "getDateRangePickerStartHeadline-8iCLdWM", "DateRangePickerTitle", "getDateRangePickerTitle-8iCLdWM", "DefaultErrorMessage", "getDefaultErrorMessage-8iCLdWM", "Dialog", "getDialog-8iCLdWM", "ExposedDropdownMenu", "getExposedDropdownMenu-8iCLdWM", "MenuCollapsed", "getMenuCollapsed-8iCLdWM", "MenuExpanded", "getMenuExpanded-8iCLdWM", "NavigationMenu", "getNavigationMenu-8iCLdWM", "SearchBarSearch", "getSearchBarSearch-8iCLdWM", "SliderRangeEnd", "getSliderRangeEnd-8iCLdWM", "SliderRangeStart", "getSliderRangeStart-8iCLdWM", "SnackbarDismiss", "getSnackbarDismiss-8iCLdWM", "SuggestionsAvailable", "getSuggestionsAvailable-8iCLdWM", "TimePicker24HourSuffix", "getTimePicker24HourSuffix-8iCLdWM", "TimePickerAM", "getTimePickerAM-8iCLdWM", "TimePickerHour", "getTimePickerHour-8iCLdWM", "TimePickerHourSelection", "getTimePickerHourSelection-8iCLdWM", "TimePickerHourSuffix", "getTimePickerHourSuffix-8iCLdWM", "TimePickerHourTextField", "getTimePickerHourTextField-8iCLdWM", "TimePickerMinute", "getTimePickerMinute-8iCLdWM", "TimePickerMinuteSelection", "getTimePickerMinuteSelection-8iCLdWM", "TimePickerMinuteSuffix", "getTimePickerMinuteSuffix-8iCLdWM", "TimePickerMinuteTextField", "getTimePickerMinuteTextField-8iCLdWM", "TimePickerPM", "getTimePickerPM-8iCLdWM", "TimePickerPeriodToggle", "getTimePickerPeriodToggle-8iCLdWM", "ToggleDropdownMenu", "getToggleDropdownMenu-8iCLdWM", "TooltipLongPressLabel", "getTooltipLongPressLabel-8iCLdWM", "TooltipPaneDescription", "getTooltipPaneDescription-8iCLdWM", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: getDefaultErrorMessage-8iCLdWM, reason: not valid java name */
        public final int m2629getDefaultErrorMessage8iCLdWM() {
            return Strings.m2585constructorimpl(R.string.default_error_message);
        }

        /* JADX INFO: renamed from: getExposedDropdownMenu-8iCLdWM, reason: not valid java name */
        public final int m2631getExposedDropdownMenu8iCLdWM() {
            return Strings.m2585constructorimpl(R.string.dropdown_menu);
        }

        /* JADX INFO: renamed from: getSliderRangeStart-8iCLdWM, reason: not valid java name */
        public final int m2637getSliderRangeStart8iCLdWM() {
            return Strings.m2585constructorimpl(R.string.range_start);
        }

        /* JADX INFO: renamed from: getSliderRangeEnd-8iCLdWM, reason: not valid java name */
        public final int m2636getSliderRangeEnd8iCLdWM() {
            return Strings.m2585constructorimpl(R.string.range_end);
        }

        /* JADX INFO: renamed from: getDialog-8iCLdWM, reason: not valid java name */
        public final int m2630getDialog8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_dialog);
        }

        /* JADX INFO: renamed from: getMenuExpanded-8iCLdWM, reason: not valid java name */
        public final int m2633getMenuExpanded8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_dropdown_menu_expanded);
        }

        /* JADX INFO: renamed from: getMenuCollapsed-8iCLdWM, reason: not valid java name */
        public final int m2632getMenuCollapsed8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_dropdown_menu_collapsed);
        }

        /* JADX INFO: renamed from: getToggleDropdownMenu-8iCLdWM, reason: not valid java name */
        public final int m2652getToggleDropdownMenu8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_dropdown_menu_toggle);
        }

        /* JADX INFO: renamed from: getSnackbarDismiss-8iCLdWM, reason: not valid java name */
        public final int m2638getSnackbarDismiss8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_snackbar_dismiss);
        }

        /* JADX INFO: renamed from: getSearchBarSearch-8iCLdWM, reason: not valid java name */
        public final int m2635getSearchBarSearch8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_search_bar_search);
        }

        /* JADX INFO: renamed from: getSuggestionsAvailable-8iCLdWM, reason: not valid java name */
        public final int m2639getSuggestionsAvailable8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_suggestions_available);
        }

        /* JADX INFO: renamed from: getDatePickerTitle-8iCLdWM, reason: not valid java name */
        public final int m2618getDatePickerTitle8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_title);
        }

        /* JADX INFO: renamed from: getDatePickerHeadline-8iCLdWM, reason: not valid java name */
        public final int m2606getDatePickerHeadline8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_headline);
        }

        /* JADX INFO: renamed from: getDatePickerYearPickerPaneTitle-8iCLdWM, reason: not valid java name */
        public final int m2620getDatePickerYearPickerPaneTitle8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_year_picker_pane_title);
        }

        /* JADX INFO: renamed from: getDatePickerSwitchToYearSelection-8iCLdWM, reason: not valid java name */
        public final int m2617getDatePickerSwitchToYearSelection8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_switch_to_year_selection);
        }

        /* JADX INFO: renamed from: getDatePickerSwitchToDaySelection-8iCLdWM, reason: not valid java name */
        public final int m2613getDatePickerSwitchToDaySelection8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_switch_to_day_selection);
        }

        /* JADX INFO: renamed from: getDatePickerSwitchToNextMonth-8iCLdWM, reason: not valid java name */
        public final int m2615getDatePickerSwitchToNextMonth8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_switch_to_next_month);
        }

        /* JADX INFO: renamed from: getDatePickerSwitchToPreviousMonth-8iCLdWM, reason: not valid java name */
        public final int m2616getDatePickerSwitchToPreviousMonth8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_switch_to_previous_month);
        }

        /* JADX INFO: renamed from: getDatePickerNavigateToYearDescription-8iCLdWM, reason: not valid java name */
        public final int m2608getDatePickerNavigateToYearDescription8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_navigate_to_year_description);
        }

        /* JADX INFO: renamed from: getDatePickerHeadlineDescription-8iCLdWM, reason: not valid java name */
        public final int m2607getDatePickerHeadlineDescription8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_headline_description);
        }

        /* JADX INFO: renamed from: getDatePickerNoSelectionDescription-8iCLdWM, reason: not valid java name */
        public final int m2609getDatePickerNoSelectionDescription8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_no_selection_description);
        }

        /* JADX INFO: renamed from: getDatePickerTodayDescription-8iCLdWM, reason: not valid java name */
        public final int m2619getDatePickerTodayDescription8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_today_description);
        }

        /* JADX INFO: renamed from: getDatePickerScrollToShowLaterYears-8iCLdWM, reason: not valid java name */
        public final int m2611getDatePickerScrollToShowLaterYears8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_scroll_to_later_years);
        }

        /* JADX INFO: renamed from: getDatePickerScrollToShowEarlierYears-8iCLdWM, reason: not valid java name */
        public final int m2610getDatePickerScrollToShowEarlierYears8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_scroll_to_earlier_years);
        }

        /* JADX INFO: renamed from: getDateInputTitle-8iCLdWM, reason: not valid java name */
        public final int m2605getDateInputTitle8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_input_title);
        }

        /* JADX INFO: renamed from: getDateInputHeadline-8iCLdWM, reason: not valid java name */
        public final int m2598getDateInputHeadline8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_input_headline);
        }

        /* JADX INFO: renamed from: getDateInputLabel-8iCLdWM, reason: not valid java name */
        public final int m2603getDateInputLabel8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_input_label);
        }

        /* JADX INFO: renamed from: getDateInputHeadlineDescription-8iCLdWM, reason: not valid java name */
        public final int m2599getDateInputHeadlineDescription8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_input_headline_description);
        }

        /* JADX INFO: renamed from: getDateInputNoInputDescription-8iCLdWM, reason: not valid java name */
        public final int m2604getDateInputNoInputDescription8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_input_no_input_description);
        }

        /* JADX INFO: renamed from: getDateInputInvalidNotAllowed-8iCLdWM, reason: not valid java name */
        public final int m2601getDateInputInvalidNotAllowed8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_input_invalid_not_allowed);
        }

        /* JADX INFO: renamed from: getDateInputInvalidForPattern-8iCLdWM, reason: not valid java name */
        public final int m2600getDateInputInvalidForPattern8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_input_invalid_for_pattern);
        }

        /* JADX INFO: renamed from: getDateInputInvalidYearRange-8iCLdWM, reason: not valid java name */
        public final int m2602getDateInputInvalidYearRange8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_input_invalid_year_range);
        }

        /* JADX INFO: renamed from: getDatePickerSwitchToCalendarMode-8iCLdWM, reason: not valid java name */
        public final int m2612getDatePickerSwitchToCalendarMode8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_switch_to_calendar_mode);
        }

        /* JADX INFO: renamed from: getDatePickerSwitchToInputMode-8iCLdWM, reason: not valid java name */
        public final int m2614getDatePickerSwitchToInputMode8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_picker_switch_to_input_mode);
        }

        /* JADX INFO: renamed from: getDateRangePickerTitle-8iCLdWM, reason: not valid java name */
        public final int m2628getDateRangePickerTitle8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_range_picker_title);
        }

        /* JADX INFO: renamed from: getDateRangePickerStartHeadline-8iCLdWM, reason: not valid java name */
        public final int m2627getDateRangePickerStartHeadline8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_range_picker_start_headline);
        }

        /* JADX INFO: renamed from: getDateRangePickerEndHeadline-8iCLdWM, reason: not valid java name */
        public final int m2624getDateRangePickerEndHeadline8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_range_picker_end_headline);
        }

        /* JADX INFO: renamed from: getDateRangePickerScrollToShowNextMonth-8iCLdWM, reason: not valid java name */
        public final int m2625getDateRangePickerScrollToShowNextMonth8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_range_picker_scroll_to_next_month);
        }

        /* JADX INFO: renamed from: getDateRangePickerScrollToShowPreviousMonth-8iCLdWM, reason: not valid java name */
        public final int m2626getDateRangePickerScrollToShowPreviousMonth8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_range_picker_scroll_to_previous_month);
        }

        /* JADX INFO: renamed from: getDateRangePickerDayInRange-8iCLdWM, reason: not valid java name */
        public final int m2623getDateRangePickerDayInRange8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_range_picker_day_in_range);
        }

        /* JADX INFO: renamed from: getDateRangeInputTitle-8iCLdWM, reason: not valid java name */
        public final int m2622getDateRangeInputTitle8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_range_input_title);
        }

        /* JADX INFO: renamed from: getDateRangeInputInvalidRangeInput-8iCLdWM, reason: not valid java name */
        public final int m2621getDateRangeInputInvalidRangeInput8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_date_range_input_invalid_range_input);
        }

        /* JADX INFO: renamed from: getBottomSheetPaneTitle-8iCLdWM, reason: not valid java name */
        public final int m2594getBottomSheetPaneTitle8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_bottom_sheet_pane_title);
        }

        /* JADX INFO: renamed from: getBottomSheetDragHandleDescription-8iCLdWM, reason: not valid java name */
        public final int m2592getBottomSheetDragHandleDescription8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_bottom_sheet_drag_handle_description);
        }

        /* JADX INFO: renamed from: getBottomSheetPartialExpandDescription-8iCLdWM, reason: not valid java name */
        public final int m2595getBottomSheetPartialExpandDescription8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_bottom_sheet_collapse_description);
        }

        /* JADX INFO: renamed from: getBottomSheetDismissDescription-8iCLdWM, reason: not valid java name */
        public final int m2591getBottomSheetDismissDescription8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_bottom_sheet_dismiss_description);
        }

        /* JADX INFO: renamed from: getBottomSheetExpandDescription-8iCLdWM, reason: not valid java name */
        public final int m2593getBottomSheetExpandDescription8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_bottom_sheet_expand_description);
        }

        /* JADX INFO: renamed from: getTooltipLongPressLabel-8iCLdWM, reason: not valid java name */
        public final int m2653getTooltipLongPressLabel8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_tooltip_long_press_label);
        }

        /* JADX INFO: renamed from: getTimePickerAM-8iCLdWM, reason: not valid java name */
        public final int m2641getTimePickerAM8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_am);
        }

        /* JADX INFO: renamed from: getTimePickerPM-8iCLdWM, reason: not valid java name */
        public final int m2650getTimePickerPM8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_pm);
        }

        /* JADX INFO: renamed from: getTimePickerPeriodToggle-8iCLdWM, reason: not valid java name */
        public final int m2651getTimePickerPeriodToggle8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_period_toggle_description);
        }

        /* JADX INFO: renamed from: getTimePickerMinuteSelection-8iCLdWM, reason: not valid java name */
        public final int m2647getTimePickerMinuteSelection8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_minute_selection);
        }

        /* JADX INFO: renamed from: getTimePickerHourSelection-8iCLdWM, reason: not valid java name */
        public final int m2643getTimePickerHourSelection8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_hour_selection);
        }

        /* JADX INFO: renamed from: getTimePickerHourSuffix-8iCLdWM, reason: not valid java name */
        public final int m2644getTimePickerHourSuffix8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_hour_suffix);
        }

        /* JADX INFO: renamed from: getTimePickerMinuteSuffix-8iCLdWM, reason: not valid java name */
        public final int m2648getTimePickerMinuteSuffix8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_minute_suffix);
        }

        /* JADX INFO: renamed from: getTimePicker24HourSuffix-8iCLdWM, reason: not valid java name */
        public final int m2640getTimePicker24HourSuffix8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_hour_24h_suffix);
        }

        /* JADX INFO: renamed from: getTimePickerHour-8iCLdWM, reason: not valid java name */
        public final int m2642getTimePickerHour8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_hour);
        }

        /* JADX INFO: renamed from: getTimePickerMinute-8iCLdWM, reason: not valid java name */
        public final int m2646getTimePickerMinute8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_minute);
        }

        /* JADX INFO: renamed from: getTimePickerHourTextField-8iCLdWM, reason: not valid java name */
        public final int m2645getTimePickerHourTextField8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_hour_text_field);
        }

        /* JADX INFO: renamed from: getTimePickerMinuteTextField-8iCLdWM, reason: not valid java name */
        public final int m2649getTimePickerMinuteTextField8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_time_picker_minute_text_field);
        }

        /* JADX INFO: renamed from: getTooltipPaneDescription-8iCLdWM, reason: not valid java name */
        public final int m2654getTooltipPaneDescription8iCLdWM() {
            return Strings.m2585constructorimpl(androidx.compose.material3.R.string.m3c_tooltip_pane_description);
        }

        /* JADX INFO: renamed from: getNavigationMenu-8iCLdWM, reason: not valid java name */
        public final int m2634getNavigationMenu8iCLdWM() {
            return Strings.m2585constructorimpl(R.string.navigation_menu);
        }

        /* JADX INFO: renamed from: getCloseDrawer-8iCLdWM, reason: not valid java name */
        public final int m2596getCloseDrawer8iCLdWM() {
            return Strings.m2585constructorimpl(R.string.close_drawer);
        }

        /* JADX INFO: renamed from: getCloseSheet-8iCLdWM, reason: not valid java name */
        public final int m2597getCloseSheet8iCLdWM() {
            return Strings.m2585constructorimpl(R.string.close_sheet);
        }
    }

    private /* synthetic */ Strings(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }
}
