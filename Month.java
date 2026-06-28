package com.google.android.material.datepicker;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;

/* JADX INFO: loaded from: classes.dex */
final class Month implements Comparable<Month>, Parcelable {
    public static final Parcelable.Creator<Month> CREATOR = new Parcelable.Creator<Month>() { // from class: com.google.android.material.datepicker.Month.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Month createFromParcel(Parcel parcel) {
            return Month.create(parcel.readInt(), parcel.readInt());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Month[] newArray(int i5) {
            return new Month[i5];
        }
    };
    final int daysInMonth;
    final int daysInWeek;
    private final Calendar firstOfMonth;
    private String longName;
    final int month;
    final long timeInMillis;
    final int year;

    private Month(Calendar calendar) {
        calendar.set(5, 1);
        Calendar dayCopy = UtcDates.getDayCopy(calendar);
        this.firstOfMonth = dayCopy;
        this.month = dayCopy.get(2);
        this.year = dayCopy.get(1);
        this.daysInWeek = dayCopy.getMaximum(7);
        this.daysInMonth = dayCopy.getActualMaximum(5);
        this.timeInMillis = dayCopy.getTimeInMillis();
    }

    public static Month create(long j5) {
        Calendar utcCalendar = UtcDates.getUtcCalendar();
        utcCalendar.setTimeInMillis(j5);
        return new Month(utcCalendar);
    }

    public static Month current() {
        return new Month(UtcDates.getTodayCalendar());
    }

    public int daysFromStartOfWeekToFirstOfMonth(int i5) {
        int i7 = this.firstOfMonth.get(7);
        if (i5 <= 0) {
            i5 = this.firstOfMonth.getFirstDayOfWeek();
        }
        int i9 = i7 - i5;
        return i9 < 0 ? i9 + this.daysInWeek : i9;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Month)) {
            return false;
        }
        Month month = (Month) obj;
        return this.month == month.month && this.year == month.year;
    }

    public long getDay(int i5) {
        Calendar dayCopy = UtcDates.getDayCopy(this.firstOfMonth);
        dayCopy.set(5, i5);
        return dayCopy.getTimeInMillis();
    }

    public int getDayOfMonth(long j5) {
        Calendar dayCopy = UtcDates.getDayCopy(this.firstOfMonth);
        dayCopy.setTimeInMillis(j5);
        return dayCopy.get(5);
    }

    public String getLongName() {
        if (this.longName == null) {
            this.longName = DateStrings.getYearMonth(this.firstOfMonth.getTimeInMillis());
        }
        return this.longName;
    }

    public long getStableId() {
        return this.firstOfMonth.getTimeInMillis();
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.month), Integer.valueOf(this.year)});
    }

    public Month monthsLater(int i5) {
        Calendar dayCopy = UtcDates.getDayCopy(this.firstOfMonth);
        dayCopy.add(2, i5);
        return new Month(dayCopy);
    }

    public int monthsUntil(Month month) {
        if (!(this.firstOfMonth instanceof GregorianCalendar)) {
            throw new IllegalArgumentException("Only Gregorian calendars are supported.");
        }
        return (month.month - this.month) + ((month.year - this.year) * 12);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i5) {
        parcel.writeInt(this.year);
        parcel.writeInt(this.month);
    }

    @Override // java.lang.Comparable
    public int compareTo(Month month) {
        return this.firstOfMonth.compareTo(month.firstOfMonth);
    }

    public static Month create(int i5, int i7) {
        Calendar utcCalendar = UtcDates.getUtcCalendar();
        utcCalendar.set(1, i5);
        utcCalendar.set(2, i7);
        return new Month(utcCalendar);
    }
}
