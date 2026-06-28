package com.google.android.material.datepicker;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import com.google.android.material.R;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
abstract class DateFormatTextWatcher extends TextWatcherAdapter {
    private final CalendarConstraints constraints;
    private final DateFormat dateFormat;
    private final String formatHint;
    private int lastLength = 0;
    private final String outOfRange;
    private final Runnable setErrorCallback;
    private Runnable setRangeErrorCallback;
    private final TextInputLayout textInputLayout;

    public DateFormatTextWatcher(final String str, DateFormat dateFormat, TextInputLayout textInputLayout, CalendarConstraints calendarConstraints) {
        this.formatHint = str;
        this.dateFormat = dateFormat;
        this.textInputLayout = textInputLayout;
        this.constraints = calendarConstraints;
        this.outOfRange = textInputLayout.getContext().getString(R.string.mtrl_picker_out_of_range);
        this.setErrorCallback = new Runnable() { // from class: com.google.android.material.datepicker.a
            @Override // java.lang.Runnable
            public final void run() {
                this.f10081e.lambda$new$0(str);
            }
        };
    }

    private Runnable createRangeErrorCallback(final long j5) {
        return new Runnable() { // from class: com.google.android.material.datepicker.b
            @Override // java.lang.Runnable
            public final void run() {
                this.f10083e.lambda$createRangeErrorCallback$1(j5);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createRangeErrorCallback$1(long j5) {
        this.textInputLayout.setError(String.format(this.outOfRange, sanitizeDateString(DateStrings.getDateString(j5))));
        onInvalidDate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(String str) {
        TextInputLayout textInputLayout = this.textInputLayout;
        DateFormat dateFormat = this.dateFormat;
        Context context = textInputLayout.getContext();
        textInputLayout.setError(context.getString(R.string.mtrl_picker_invalid_format) + "\n" + String.format(context.getString(R.string.mtrl_picker_invalid_format_use), sanitizeDateString(str)) + "\n" + String.format(context.getString(R.string.mtrl_picker_invalid_format_example), sanitizeDateString(dateFormat.format(new Date(UtcDates.getTodayCalendar().getTimeInMillis())))));
        onInvalidDate();
    }

    private String sanitizeDateString(String str) {
        return str.replace(' ', (char) 160);
    }

    @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
        if (!Locale.getDefault().getLanguage().equals(Locale.KOREAN.getLanguage()) && editable.length() != 0 && editable.length() < this.formatHint.length() && editable.length() >= this.lastLength) {
            char cCharAt = this.formatHint.charAt(editable.length());
            if (Character.isDigit(cCharAt)) {
                return;
            }
            editable.append(cCharAt);
        }
    }

    @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i5, int i7, int i9) {
        this.lastLength = charSequence.length();
    }

    public void onInvalidDate() {
    }

    @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i5, int i7, int i9) {
        this.textInputLayout.removeCallbacks(this.setErrorCallback);
        this.textInputLayout.removeCallbacks(this.setRangeErrorCallback);
        this.textInputLayout.setError(null);
        onValidDate(null);
        if (TextUtils.isEmpty(charSequence) || charSequence.length() < this.formatHint.length()) {
            return;
        }
        try {
            Date date = this.dateFormat.parse(charSequence.toString());
            this.textInputLayout.setError(null);
            long time = date.getTime();
            if (this.constraints.getDateValidator().isValid(time) && this.constraints.isWithinBounds(time)) {
                onValidDate(Long.valueOf(date.getTime()));
                return;
            }
            Runnable runnableCreateRangeErrorCallback = createRangeErrorCallback(time);
            this.setRangeErrorCallback = runnableCreateRangeErrorCallback;
            runValidation(this.textInputLayout, runnableCreateRangeErrorCallback);
        } catch (ParseException unused) {
            runValidation(this.textInputLayout, this.setErrorCallback);
        }
    }

    public abstract void onValidDate(Long l2);

    public void runValidation(View view, Runnable runnable) {
        view.post(runnable);
    }
}
