package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.core.view.I;
import androidx.core.view.W;
import androidx.recyclerview.widget.AbstractC0341j0;
import androidx.recyclerview.widget.C0372z0;
import androidx.recyclerview.widget.V0;
import com.google.android.material.R;
import com.google.android.material.datepicker.MaterialCalendar;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
class MonthsPagerAdapter extends AbstractC0341j0 {
    private final CalendarConstraints calendarConstraints;
    private final DateSelector<?> dateSelector;
    private final DayViewDecorator dayViewDecorator;
    private final int itemHeight;
    private final MaterialCalendar.OnDayClickListener onDayClickListener;

    public static class ViewHolder extends V0 {
        final MaterialCalendarGridView monthGrid;
        final TextView monthTitle;

        public ViewHolder(LinearLayout linearLayout, boolean z9) {
            super(linearLayout);
            TextView textView = (TextView) linearLayout.findViewById(R.id.month_title);
            this.monthTitle = textView;
            WeakHashMap weakHashMap = W.f7199a;
            new I(com.samsung.android.keyscafe.R.id.tag_accessibility_heading, Boolean.class, 0, 28, 2).i(textView, Boolean.TRUE);
            this.monthGrid = (MaterialCalendarGridView) linearLayout.findViewById(R.id.month_grid);
            if (z9) {
                return;
            }
            textView.setVisibility(8);
        }
    }

    public MonthsPagerAdapter(Context context, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints, DayViewDecorator dayViewDecorator, MaterialCalendar.OnDayClickListener onDayClickListener) {
        Month start = calendarConstraints.getStart();
        Month end = calendarConstraints.getEnd();
        Month openAt = calendarConstraints.getOpenAt();
        if (start.compareTo(openAt) > 0) {
            throw new IllegalArgumentException("firstPage cannot be after currentPage");
        }
        if (openAt.compareTo(end) > 0) {
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        this.itemHeight = (MaterialCalendar.getDayHeight(context) * MonthAdapter.MAXIMUM_WEEKS) + (MaterialDatePicker.isFullscreen(context) ? MaterialCalendar.getDayHeight(context) : 0);
        this.calendarConstraints = calendarConstraints;
        this.dateSelector = dateSelector;
        this.dayViewDecorator = dayViewDecorator;
        this.onDayClickListener = onDayClickListener;
        setHasStableIds(true);
    }

    @Override // androidx.recyclerview.widget.AbstractC0341j0
    public int getItemCount() {
        return this.calendarConstraints.getMonthSpan();
    }

    @Override // androidx.recyclerview.widget.AbstractC0341j0
    public long getItemId(int i5) {
        return this.calendarConstraints.getStart().monthsLater(i5).getStableId();
    }

    public Month getPageMonth(int i5) {
        return this.calendarConstraints.getStart().monthsLater(i5);
    }

    public CharSequence getPageTitle(int i5) {
        return getPageMonth(i5).getLongName();
    }

    public int getPosition(Month month) {
        return this.calendarConstraints.getStart().monthsUntil(month);
    }

    @Override // androidx.recyclerview.widget.AbstractC0341j0
    public void onBindViewHolder(ViewHolder viewHolder, int i5) {
        Month monthMonthsLater = this.calendarConstraints.getStart().monthsLater(i5);
        viewHolder.monthTitle.setText(monthMonthsLater.getLongName());
        final MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) viewHolder.monthGrid.findViewById(R.id.month_grid);
        if (materialCalendarGridView.getAdapter() == null || !monthMonthsLater.equals(materialCalendarGridView.getAdapter().month)) {
            MonthAdapter monthAdapter = new MonthAdapter(monthMonthsLater, this.dateSelector, this.calendarConstraints, this.dayViewDecorator);
            materialCalendarGridView.setNumColumns(monthMonthsLater.daysInWeek);
            materialCalendarGridView.setAdapter((ListAdapter) monthAdapter);
        } else {
            materialCalendarGridView.invalidate();
            materialCalendarGridView.getAdapter().updateSelectedStates(materialCalendarGridView);
        }
        materialCalendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.google.android.material.datepicker.MonthsPagerAdapter.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i7, long j5) {
                if (materialCalendarGridView.getAdapter().withinMonth(i7)) {
                    MonthsPagerAdapter.this.onDayClickListener.onDayClick(materialCalendarGridView.getAdapter().getItem(i7).longValue());
                }
            }
        });
    }

    @Override // androidx.recyclerview.widget.AbstractC0341j0
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i5) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mtrl_calendar_month_labeled, viewGroup, false);
        if (!MaterialDatePicker.isFullscreen(viewGroup.getContext())) {
            return new ViewHolder(linearLayout, false);
        }
        linearLayout.setLayoutParams(new C0372z0(-1, this.itemHeight));
        return new ViewHolder(linearLayout, true);
    }
}
