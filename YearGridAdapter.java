package com.google.android.material.datepicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.AbstractC0341j0;
import androidx.recyclerview.widget.V0;
import com.google.android.material.R;
import com.google.android.material.datepicker.MaterialCalendar;
import com.google.android.material.timepicker.TimeModel;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
class YearGridAdapter extends AbstractC0341j0 {
    private final MaterialCalendar<?> materialCalendar;

    public static class ViewHolder extends V0 {
        final TextView textView;

        public ViewHolder(TextView textView) {
            super(textView);
            this.textView = textView;
        }
    }

    public YearGridAdapter(MaterialCalendar<?> materialCalendar) {
        this.materialCalendar = materialCalendar;
    }

    private View.OnClickListener createYearClickListener(final int i5) {
        return new View.OnClickListener() { // from class: com.google.android.material.datepicker.YearGridAdapter.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                YearGridAdapter.this.materialCalendar.setCurrentMonth(YearGridAdapter.this.materialCalendar.getCalendarConstraints().clamp(Month.create(i5, YearGridAdapter.this.materialCalendar.getCurrentMonth().month)));
                YearGridAdapter.this.materialCalendar.setSelector(MaterialCalendar.CalendarSelector.DAY);
            }
        };
    }

    @Override // androidx.recyclerview.widget.AbstractC0341j0
    public int getItemCount() {
        return this.materialCalendar.getCalendarConstraints().getYearSpan();
    }

    public int getPositionForYear(int i5) {
        return i5 - this.materialCalendar.getCalendarConstraints().getStart().year;
    }

    public int getYearForPosition(int i5) {
        return this.materialCalendar.getCalendarConstraints().getStart().year + i5;
    }

    @Override // androidx.recyclerview.widget.AbstractC0341j0
    public void onBindViewHolder(ViewHolder viewHolder, int i5) {
        int yearForPosition = getYearForPosition(i5);
        viewHolder.textView.setText(String.format(Locale.getDefault(), TimeModel.NUMBER_FORMAT, Integer.valueOf(yearForPosition)));
        TextView textView = viewHolder.textView;
        textView.setContentDescription(DateStrings.getYearContentDescription(textView.getContext(), yearForPosition));
        CalendarStyle calendarStyle = this.materialCalendar.getCalendarStyle();
        Calendar todayCalendar = UtcDates.getTodayCalendar();
        CalendarItemStyle calendarItemStyle = todayCalendar.get(1) == yearForPosition ? calendarStyle.todayYear : calendarStyle.year;
        Iterator<Long> it = this.materialCalendar.getDateSelector().getSelectedDays().iterator();
        while (it.hasNext()) {
            todayCalendar.setTimeInMillis(it.next().longValue());
            if (todayCalendar.get(1) == yearForPosition) {
                calendarItemStyle = calendarStyle.selectedYear;
            }
        }
        calendarItemStyle.styleItem(viewHolder.textView);
        viewHolder.textView.setOnClickListener(createYearClickListener(yearForPosition));
    }

    @Override // androidx.recyclerview.widget.AbstractC0341j0
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i5) {
        return new ViewHolder((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mtrl_calendar_year, viewGroup, false));
    }
}
