package com.google.android.material.datepicker;

import android.content.Context;
import com.google.android.material.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
class DateStrings {
    private DateStrings() {
    }

    public static K.c getDateRangeString(Long l2, Long l9, SimpleDateFormat simpleDateFormat) {
        if (l2 == null && l9 == null) {
            return new K.c(null, null);
        }
        if (l2 == null) {
            return new K.c(null, getDateString(l9.longValue(), simpleDateFormat));
        }
        if (l9 == null) {
            return new K.c(getDateString(l2.longValue(), simpleDateFormat), null);
        }
        Calendar todayCalendar = UtcDates.getTodayCalendar();
        Calendar utcCalendar = UtcDates.getUtcCalendar();
        utcCalendar.setTimeInMillis(l2.longValue());
        Calendar utcCalendar2 = UtcDates.getUtcCalendar();
        utcCalendar2.setTimeInMillis(l9.longValue());
        if (simpleDateFormat != null) {
            return new K.c(simpleDateFormat.format(new Date(l2.longValue())), simpleDateFormat.format(new Date(l9.longValue())));
        }
        return utcCalendar.get(1) == utcCalendar2.get(1) ? utcCalendar.get(1) == todayCalendar.get(1) ? new K.c(getMonthDay(l2.longValue(), Locale.getDefault()), getMonthDay(l9.longValue(), Locale.getDefault())) : new K.c(getMonthDay(l2.longValue(), Locale.getDefault()), getYearMonthDay(l9.longValue(), Locale.getDefault())) : new K.c(getYearMonthDay(l2.longValue(), Locale.getDefault()), getYearMonthDay(l9.longValue(), Locale.getDefault()));
    }

    public static String getDateString(long j5) {
        return getDateString(j5, null);
    }

    public static String getDayContentDescription(Context context, long j5, boolean z9, boolean z10, boolean z11) {
        String optionalYearMonthDayOfWeekDay = getOptionalYearMonthDayOfWeekDay(j5);
        if (z9) {
            optionalYearMonthDayOfWeekDay = String.format(context.getString(R.string.mtrl_picker_today_description), optionalYearMonthDayOfWeekDay);
        }
        return z10 ? String.format(context.getString(R.string.mtrl_picker_start_date_description), optionalYearMonthDayOfWeekDay) : z11 ? String.format(context.getString(R.string.mtrl_picker_end_date_description), optionalYearMonthDayOfWeekDay) : optionalYearMonthDayOfWeekDay;
    }

    public static String getMonthDay(long j5) {
        return getMonthDay(j5, Locale.getDefault());
    }

    public static String getMonthDayOfWeekDay(long j5) {
        return getMonthDayOfWeekDay(j5, Locale.getDefault());
    }

    public static String getOptionalYearMonthDayOfWeekDay(long j5) {
        return isDateWithinCurrentYear(j5) ? getMonthDayOfWeekDay(j5) : getYearMonthDayOfWeekDay(j5);
    }

    public static String getYearContentDescription(Context context, int i5) {
        return UtcDates.getTodayCalendar().get(1) == i5 ? String.format(context.getString(R.string.mtrl_picker_navigate_to_current_year_description), Integer.valueOf(i5)) : String.format(context.getString(R.string.mtrl_picker_navigate_to_year_description), Integer.valueOf(i5));
    }

    public static String getYearMonth(long j5) {
        return UtcDates.getYearMonthFormat(Locale.getDefault()).format(new Date(j5));
    }

    public static String getYearMonthDay(long j5) {
        return getYearMonthDay(j5, Locale.getDefault());
    }

    public static String getYearMonthDayOfWeekDay(long j5) {
        return getYearMonthDayOfWeekDay(j5, Locale.getDefault());
    }

    private static boolean isDateWithinCurrentYear(long j5) {
        Calendar todayCalendar = UtcDates.getTodayCalendar();
        Calendar utcCalendar = UtcDates.getUtcCalendar();
        utcCalendar.setTimeInMillis(j5);
        return todayCalendar.get(1) == utcCalendar.get(1);
    }

    public static String getDateString(long j5, SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat != null ? simpleDateFormat.format(new Date(j5)) : isDateWithinCurrentYear(j5) ? getMonthDay(j5) : getYearMonthDay(j5);
    }

    public static String getMonthDay(long j5, Locale locale) {
        return UtcDates.getAbbrMonthDayFormat(locale).format(new Date(j5));
    }

    public static String getMonthDayOfWeekDay(long j5, Locale locale) {
        return UtcDates.getMonthWeekdayDayFormat(locale).format(new Date(j5));
    }

    public static String getYearMonthDay(long j5, Locale locale) {
        return UtcDates.getYearAbbrMonthDayFormat(locale).format(new Date(j5));
    }

    public static String getYearMonthDayOfWeekDay(long j5, Locale locale) {
        return UtcDates.getYearMonthWeekdayDayFormat(locale).format(new Date(j5));
    }

    public static K.c getDateRangeString(Long l2, Long l9) {
        return getDateRangeString(l2, l9, null);
    }
}
