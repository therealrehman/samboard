package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
class MonthAdapter extends BaseAdapter {
    private static final int NO_DAY_NUMBER = -1;
    final CalendarConstraints calendarConstraints;
    CalendarStyle calendarStyle;
    final DateSelector<?> dateSelector;
    final DayViewDecorator dayViewDecorator;
    final Month month;
    private Collection<Long> previouslySelectedDates;
    static final int MAXIMUM_WEEKS = UtcDates.getUtcCalendar().getMaximum(4);
    private static final int MAXIMUM_GRID_CELLS = (UtcDates.getUtcCalendar().getMaximum(7) + UtcDates.getUtcCalendar().getMaximum(5)) - 1;

    public MonthAdapter(Month month, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints, DayViewDecorator dayViewDecorator) {
        this.month = month;
        this.dateSelector = dateSelector;
        this.calendarConstraints = calendarConstraints;
        this.dayViewDecorator = dayViewDecorator;
        this.previouslySelectedDates = dateSelector.getSelectedDays();
    }

    private String getDayContentDescription(Context context, long j5) {
        return DateStrings.getDayContentDescription(context, j5, isToday(j5), isStartOfRange(j5), isEndOfRange(j5));
    }

    private void initializeStyles(Context context) {
        if (this.calendarStyle == null) {
            this.calendarStyle = new CalendarStyle(context);
        }
    }

    private boolean isSelected(long j5) {
        Iterator<Long> it = this.dateSelector.getSelectedDays().iterator();
        while (it.hasNext()) {
            if (UtcDates.canonicalYearMonthDay(j5) == UtcDates.canonicalYearMonthDay(it.next().longValue())) {
                return true;
            }
        }
        return false;
    }

    private boolean isToday(long j5) {
        return UtcDates.getTodayCalendar().getTimeInMillis() == j5;
    }

    private void updateSelectedState(TextView textView, long j5, int i5) {
        boolean z9;
        CalendarItemStyle calendarItemStyle;
        if (textView == null) {
            return;
        }
        Context context = textView.getContext();
        String dayContentDescription = getDayContentDescription(context, j5);
        textView.setContentDescription(dayContentDescription);
        boolean zIsValid = this.calendarConstraints.getDateValidator().isValid(j5);
        if (zIsValid) {
            textView.setEnabled(true);
            boolean zIsSelected = isSelected(j5);
            textView.setSelected(zIsSelected);
            calendarItemStyle = zIsSelected ? this.calendarStyle.selectedDay : isToday(j5) ? this.calendarStyle.todayDay : this.calendarStyle.day;
            z9 = zIsSelected;
        } else {
            textView.setEnabled(false);
            z9 = false;
            calendarItemStyle = this.calendarStyle.invalidDay;
        }
        DayViewDecorator dayViewDecorator = this.dayViewDecorator;
        if (dayViewDecorator == null || i5 == -1) {
            calendarItemStyle.styleItem(textView);
            return;
        }
        Month month = this.month;
        int i7 = month.year;
        int i9 = month.month;
        ColorStateList backgroundColor = dayViewDecorator.getBackgroundColor(context, i7, i9, i5, zIsValid, z9);
        boolean z10 = z9;
        calendarItemStyle.styleItem(textView, backgroundColor, this.dayViewDecorator.getTextColor(context, i7, i9, i5, zIsValid, z10));
        Drawable compoundDrawableLeft = this.dayViewDecorator.getCompoundDrawableLeft(context, i7, i9, i5, zIsValid, z10);
        Drawable compoundDrawableTop = this.dayViewDecorator.getCompoundDrawableTop(context, i7, i9, i5, zIsValid, z10);
        Drawable compoundDrawableRight = this.dayViewDecorator.getCompoundDrawableRight(context, i7, i9, i5, zIsValid, z10);
        boolean z11 = z9;
        textView.setCompoundDrawables(compoundDrawableLeft, compoundDrawableTop, compoundDrawableRight, this.dayViewDecorator.getCompoundDrawableBottom(context, i7, i9, i5, zIsValid, z11));
        textView.setContentDescription(this.dayViewDecorator.getContentDescription(context, i7, i9, i5, zIsValid, z11, dayContentDescription));
    }

    private void updateSelectedStateForDate(MaterialCalendarGridView materialCalendarGridView, long j5) {
        if (Month.create(j5).equals(this.month)) {
            int dayOfMonth = this.month.getDayOfMonth(j5);
            updateSelectedState((TextView) materialCalendarGridView.getChildAt(materialCalendarGridView.getAdapter().dayToPosition(dayOfMonth) - materialCalendarGridView.getFirstVisiblePosition()), j5, dayOfMonth);
        }
    }

    public int dayToPosition(int i5) {
        return firstPositionInMonth() + (i5 - 1);
    }

    public int firstPositionInMonth() {
        return this.month.daysFromStartOfWeekToFirstOfMonth(this.calendarConstraints.getFirstDayOfWeek());
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return MAXIMUM_GRID_CELLS;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i5) {
        return i5 / this.month.daysInWeek;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    public boolean isEndOfRange(long j5) {
        Iterator<K.c> it = this.dateSelector.getSelectedRanges().iterator();
        while (it.hasNext()) {
            Object obj = it.next().f1640b;
            if (obj != null && ((Long) obj).longValue() == j5) {
                return true;
            }
        }
        return false;
    }

    public boolean isFirstInRow(int i5) {
        return i5 % this.month.daysInWeek == 0;
    }

    public boolean isLastInRow(int i5) {
        return (i5 + 1) % this.month.daysInWeek == 0;
    }

    public boolean isStartOfRange(long j5) {
        Iterator<K.c> it = this.dateSelector.getSelectedRanges().iterator();
        while (it.hasNext()) {
            Object obj = it.next().f1639a;
            if (obj != null && ((Long) obj).longValue() == j5) {
                return true;
            }
        }
        return false;
    }

    public int lastPositionInMonth() {
        return (firstPositionInMonth() + this.month.daysInMonth) - 1;
    }

    public int positionToDay(int i5) {
        return (i5 - firstPositionInMonth()) + 1;
    }

    public void updateSelectedStates(MaterialCalendarGridView materialCalendarGridView) {
        Iterator<Long> it = this.previouslySelectedDates.iterator();
        while (it.hasNext()) {
            updateSelectedStateForDate(materialCalendarGridView, it.next().longValue());
        }
        DateSelector<?> dateSelector = this.dateSelector;
        if (dateSelector != null) {
            Iterator<Long> it2 = dateSelector.getSelectedDays().iterator();
            while (it2.hasNext()) {
                updateSelectedStateForDate(materialCalendarGridView, it2.next().longValue());
            }
            this.previouslySelectedDates = this.dateSelector.getSelectedDays();
        }
    }

    public boolean withinMonth(int i5) {
        return i5 >= firstPositionInMonth() && i5 <= lastPositionInMonth();
    }

    @Override // android.widget.Adapter
    public Long getItem(int i5) {
        if (i5 < firstPositionInMonth() || i5 > lastPositionInMonth()) {
            return null;
        }
        return Long.valueOf(this.month.getDay(positionToDay(i5)));
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0054  */
    @Override // android.widget.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.widget.TextView getView(int r6, android.view.View r7, android.view.ViewGroup r8) {
        /*
            r5 = this;
            android.content.Context r0 = r8.getContext()
            r5.initializeStyles(r0)
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
            r1 = 0
            if (r7 != 0) goto L1e
            android.content.Context r7 = r8.getContext()
            android.view.LayoutInflater r7 = android.view.LayoutInflater.from(r7)
            int r0 = com.google.android.material.R.layout.mtrl_calendar_day
            android.view.View r7 = r7.inflate(r0, r8, r1)
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
        L1e:
            int r7 = r5.firstPositionInMonth()
            int r7 = r6 - r7
            if (r7 < 0) goto L54
            com.google.android.material.datepicker.Month r8 = r5.month
            int r2 = r8.daysInMonth
            if (r7 < r2) goto L2d
            goto L54
        L2d:
            r2 = 1
            int r7 = r7 + r2
            r0.setTag(r8)
            android.content.res.Resources r8 = r0.getResources()
            android.content.res.Configuration r8 = r8.getConfiguration()
            java.util.Locale r8 = r8.locale
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            java.lang.String r4 = "%d"
            java.lang.String r8 = java.lang.String.format(r8, r4, r3)
            r0.setText(r8)
            r0.setVisibility(r1)
            r0.setEnabled(r2)
            goto L5d
        L54:
            r7 = 8
            r0.setVisibility(r7)
            r0.setEnabled(r1)
            r7 = -1
        L5d:
            java.lang.Long r6 = r5.getItem(r6)
            if (r6 != 0) goto L64
            return r0
        L64:
            long r1 = r6.longValue()
            r5.updateSelectedState(r0, r1, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.MonthAdapter.getView(int, android.view.View, android.view.ViewGroup):android.widget.TextView");
    }
}
