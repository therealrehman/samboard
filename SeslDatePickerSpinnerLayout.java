package androidx.picker.widget;

import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.timepicker.TimeModel;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
class SeslDatePickerSpinnerLayout extends LinearLayout {

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public static final /* synthetic */ int f8185C = 0;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final EditText[] f8186A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public final C0305z f8187B;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8188e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Context f8189f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Calendar f8190g;
    public Calendar h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Calendar f8191i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Calendar f8192j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f8193k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Locale f8194l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public SeslDatePicker f8195m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final SeslNumberPicker f8196n;
    public final SeslNumberPicker o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final SeslNumberPicker f8197p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final EditText f8198q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final EditText f8199r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final EditText f8200s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final View f8201t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final View f8202u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String[] f8203v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public String[] f8204w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final String f8205x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Toast f8206y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public C0287g f8207z;

    public SeslDatePickerSpinnerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.datePickerStyle, 0);
        C0304y c0304y = new C0304y(this);
        this.f8186A = new EditText[3];
        this.f8187B = new C0305z(0, this);
        this.f8189f = context;
        LayoutInflater.from(context).inflate(com.samsung.android.keyscafe.R.layout.sesl_date_picker_spinner, (ViewGroup) this, true);
        Locale locale = Locale.getDefault();
        this.f8194l = locale;
        b(locale);
        C0304y c0304y2 = new C0304y(this);
        LinearLayout linearLayout = (LinearLayout) findViewById(com.samsung.android.keyscafe.R.id.sesl_date_picker_pickers);
        this.f8201t = findViewById(com.samsung.android.keyscafe.R.id.sesl_date_picker_spinner_day_padding);
        this.f8202u = findViewById(com.samsung.android.keyscafe.R.id.sesl_date_picker_spinner_year_padding);
        SeslNumberPicker seslNumberPicker = (SeslNumberPicker) findViewById(com.samsung.android.keyscafe.R.id.sesl_date_picker_spinner_day);
        this.f8196n = seslNumberPicker;
        this.f8198q = (EditText) seslNumberPicker.findViewById(com.samsung.android.keyscafe.R.id.numberpicker_input);
        seslNumberPicker.setFormatter(SeslNumberPicker.getTwoDigitFormatter());
        seslNumberPicker.setOnValueChangedListener(c0304y2);
        seslNumberPicker.setOnEditTextModeChangedListener(c0304y);
        seslNumberPicker.setMaxInputLength(2);
        seslNumberPicker.a();
        SeslNumberPicker seslNumberPicker2 = (SeslNumberPicker) findViewById(com.samsung.android.keyscafe.R.id.sesl_date_picker_spinner_month);
        this.o = seslNumberPicker2;
        EditText editText = (EditText) seslNumberPicker2.findViewById(com.samsung.android.keyscafe.R.id.numberpicker_input);
        this.f8199r = editText;
        if (k()) {
            seslNumberPicker2.setMinValue(1);
            seslNumberPicker2.setMaxValue(12);
            seslNumberPicker2.a();
            seslNumberPicker2.setMaxInputLength(2);
        } else {
            seslNumberPicker2.setMinValue(0);
            seslNumberPicker2.setMaxValue(this.f8193k - 1);
            seslNumberPicker2.setFormatter(null);
            seslNumberPicker2.setDisplayedValues(this.f8203v);
            editText.setInputType(1);
            EditText editText2 = seslNumberPicker2.f8209e.f7980e;
            editText2.setImeOptions(33554432);
            editText2.setPrivateImeOptions("inputType=month_edittext");
            editText2.setText("");
            seslNumberPicker2.setCustomTalkbackFormatter(new C0304y(this));
        }
        seslNumberPicker2.setOnValueChangedListener(c0304y2);
        seslNumberPicker2.setOnEditTextModeChangedListener(c0304y);
        SeslNumberPicker seslNumberPicker3 = (SeslNumberPicker) findViewById(com.samsung.android.keyscafe.R.id.sesl_date_picker_spinner_year);
        this.f8197p = seslNumberPicker3;
        this.f8200s = (EditText) seslNumberPicker3.findViewById(com.samsung.android.keyscafe.R.id.numberpicker_input);
        seslNumberPicker3.setOnValueChangedListener(c0304y2);
        seslNumberPicker3.setOnEditTextModeChangedListener(c0304y);
        seslNumberPicker3.setMaxInputLength(4);
        seslNumberPicker3.a();
        Typeface typefaceCreate = Build.VERSION.SDK_INT >= 34 ? Typeface.create(Typeface.create("sec", 0), 600, false) : Typeface.create("sec-roboto-light", 1);
        seslNumberPicker.setTextTypeface(typefaceCreate);
        seslNumberPicker2.setTextTypeface(typefaceCreate);
        seslNumberPicker3.setTextTypeface(typefaceCreate);
        this.f8205x = context.getResources().getString(com.samsung.android.keyscafe.R.string.sesl_number_picker_invalid_value_entered);
        f();
        seslNumberPicker.setPickerContentDescription(context.getResources().getString(com.samsung.android.keyscafe.R.string.sesl_date_picker_day));
        seslNumberPicker2.setPickerContentDescription(context.getResources().getString(com.samsung.android.keyscafe.R.string.sesl_date_picker_month));
        seslNumberPicker3.setPickerContentDescription(context.getResources().getString(com.samsung.android.keyscafe.R.string.sesl_date_picker_year));
        this.f8192j.setTimeInMillis(System.currentTimeMillis());
        c(this.f8192j.get(1), this.f8192j.get(2), this.f8192j.get(5));
        j(true, true, true, true);
        linearLayout.removeAllViews();
        char[] dateFormatOrder = DateFormat.getDateFormatOrder(context);
        int length = dateFormatOrder.length;
        for (int i5 = 0; i5 < length; i5++) {
            char c5 = dateFormatOrder[i5];
            if (c5 == 'M') {
                SeslNumberPicker seslNumberPicker4 = this.o;
                linearLayout.addView(seslNumberPicker4);
                e(seslNumberPicker4, length, i5);
            } else if (c5 == 'd') {
                SeslNumberPicker seslNumberPicker5 = this.f8196n;
                linearLayout.addView(seslNumberPicker5);
                e(seslNumberPicker5, length, i5);
            } else {
                if (c5 != 'y') {
                    throw new IllegalArgumentException(Arrays.toString(dateFormatOrder));
                }
                SeslNumberPicker seslNumberPicker6 = this.f8197p;
                linearLayout.addView(seslNumberPicker6);
                e(seslNumberPicker6, length, i5);
            }
        }
        char c9 = dateFormatOrder[0];
        View view = this.f8201t;
        View view2 = this.f8202u;
        if (c9 == 'y') {
            linearLayout.addView(view2, 0);
            linearLayout.addView(view);
        } else {
            linearLayout.addView(view, 0);
            linearLayout.addView(view2);
        }
        char c10 = dateFormatOrder[0];
        char c11 = dateFormatOrder[1];
        if (c10 == 'M') {
            g(0);
            return;
        }
        if (c10 == 'd') {
            g(1);
        } else {
            if (c10 != 'y') {
                return;
            }
            if (c11 == 'd') {
                g(3);
            } else {
                g(2);
            }
        }
    }

    public static Calendar a(Calendar calendar, Locale locale) {
        if (calendar == null) {
            return Calendar.getInstance(locale);
        }
        long timeInMillis = calendar.getTimeInMillis();
        Calendar calendar2 = Calendar.getInstance(locale);
        calendar2.setTimeInMillis(timeInMillis);
        return calendar2;
    }

    public static void e(SeslNumberPicker seslNumberPicker, int i5, int i7) {
        ((TextView) seslNumberPicker.findViewById(com.samsung.android.keyscafe.R.id.numberpicker_input)).setImeOptions(i7 < i5 + (-1) ? 33554437 : 33554438);
    }

    public final void b(Locale locale) {
        this.f8190g = a(this.f8190g, locale);
        this.h = a(this.h, locale);
        this.f8191i = a(this.f8191i, locale);
        this.f8192j = a(this.f8192j, locale);
        this.f8193k = this.f8190g.getActualMaximum(2) + 1;
        this.f8203v = new DateFormatSymbols().getShortMonths();
        this.f8204w = new DateFormatSymbols().getMonths();
        int i5 = 0;
        int i7 = 0;
        while (true) {
            String[] strArr = this.f8203v;
            if (i7 >= strArr.length) {
                break;
            }
            strArr[i7] = strArr[i7].toUpperCase();
            i7++;
        }
        if (k()) {
            this.f8203v = new String[this.f8193k];
            while (i5 < this.f8193k) {
                int i9 = i5 + 1;
                this.f8203v[i5] = String.format(TimeModel.NUMBER_FORMAT, Integer.valueOf(i9));
                i5 = i9;
            }
        }
    }

    public final void c(int i5, int i7, int i9) {
        this.f8192j.set(i5, i7, i9);
        if (this.f8192j.before(this.h)) {
            this.f8192j.setTimeInMillis(this.h.getTimeInMillis());
        } else if (this.f8192j.after(this.f8191i)) {
            this.f8192j.setTimeInMillis(this.f8191i.getTimeInMillis());
        }
    }

    public final void d(boolean z9) {
        if (this.f8188e == z9) {
            return;
        }
        this.f8188e = z9;
        InputMethodManager inputMethodManager = (InputMethodManager) this.f8189f.getSystemService("input_method");
        SeslNumberPicker seslNumberPicker = this.f8196n;
        seslNumberPicker.setEditTextMode(z9);
        this.o.setEditTextMode(z9);
        this.f8197p.setEditTextMode(z9);
        if (inputMethodManager != null) {
            if (this.f8188e) {
                inputMethodManager.showSoftInput(seslNumberPicker, 0);
            } else {
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
            }
        }
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    public final void f() {
        Resources resources = this.f8189f.getResources();
        int integer = resources.getInteger(com.samsung.android.keyscafe.R.integer.sesl_date_picker_spinner_number_text_size);
        int integer2 = resources.getInteger(com.samsung.android.keyscafe.R.integer.sesl_date_picker_spinner_number_text_size_with_unit);
        float f2 = integer;
        SeslNumberPicker seslNumberPicker = this.f8196n;
        seslNumberPicker.setTextSize(f2);
        SeslNumberPicker seslNumberPicker2 = this.f8197p;
        seslNumberPicker2.setTextSize(f2);
        String language = this.f8194l.getLanguage();
        if ("my".equals(language) || "ml".equals(language) || "ar".equals(language) || "fa".equals(language)) {
            integer = resources.getInteger(com.samsung.android.keyscafe.R.integer.sesl_date_picker_spinner_long_month_text_size);
        } else if ("ga".equals(language)) {
            integer = resources.getInteger(com.samsung.android.keyscafe.R.integer.sesl_date_picker_spinner_long_month_text_size) - 1;
        } else if ("hu".equals(language)) {
            integer -= 4;
        }
        boolean zK = k();
        SeslNumberPicker seslNumberPicker3 = this.o;
        if (zK) {
            seslNumberPicker3.setTextSize(f2);
        } else {
            seslNumberPicker3.setTextSize(integer);
        }
        if ("ko".equals(language) || "zh".equals(language) || "ja".equals(language)) {
            float f7 = integer2;
            seslNumberPicker.setTextSize(f7);
            seslNumberPicker3.setTextSize(f7);
            seslNumberPicker2.setTextSize(f7);
            seslNumberPicker.setDateUnit(997);
            seslNumberPicker3.setDateUnit(998);
            seslNumberPicker2.setDateUnit(androidx.room.k.MAX_BIND_PARAMETER_CNT);
        }
    }

    public final void g(int i5) {
        int i7;
        int i9;
        int i10;
        k();
        if (i5 == 0) {
            i7 = 1;
            i9 = 2;
            i10 = 0;
        } else if (i5 != 1) {
            if (i5 == 2) {
                i10 = 1;
                i7 = 2;
            } else if (i5 != 3) {
                i9 = -1;
                i10 = -1;
                i7 = -1;
            } else {
                i7 = 1;
                i10 = 2;
            }
            i9 = 0;
        } else {
            i10 = 1;
            i9 = 2;
            i7 = 0;
        }
        EditText editText = this.f8197p.getEditText();
        EditText[] editTextArr = this.f8186A;
        editTextArr[i9] = editText;
        editTextArr[i10] = this.o.getEditText();
        editTextArr[i7] = this.f8196n.getEditText();
        editTextArr[i9].addTextChangedListener(new B(this, 4, i9, false));
        if (k()) {
            editTextArr[i10].addTextChangedListener(new B(this, 2, i10, true));
        } else {
            editTextArr[i10].addTextChangedListener(new B(this, 3, i10, true));
        }
        editTextArr[i7].addTextChangedListener(new B(this, 2, i7, false));
        if (i5 != 3 || k()) {
            editTextArr[editTextArr.length - 1].setOnEditorActionListener(this.f8187B);
        }
        editTextArr[i9].setOnKeyListener(new A(0, this));
        editTextArr[i10].setOnKeyListener(new A(0, this));
        editTextArr[i7].setOnKeyListener(new A(0, this));
    }

    public final void h(int i5, int i7, int i9) {
        if (this.f8192j.get(1) == i5 && this.f8192j.get(2) == i7 && this.f8192j.get(5) == i9) {
            return;
        }
        c(i5, i7, i9);
        j(true, true, true, true);
    }

    public final void i() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.f8189f.getSystemService("input_method");
        if (inputMethodManager != null) {
            EditText editText = this.f8200s;
            if (inputMethodManager.isActive(editText)) {
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
                editText.clearFocus();
                return;
            }
            EditText editText2 = this.f8199r;
            if (inputMethodManager.isActive(editText2)) {
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
                editText2.clearFocus();
                return;
            }
            EditText editText3 = this.f8198q;
            if (inputMethodManager.isActive(editText3)) {
                inputMethodManager.hideSoftInputFromWindow(getWindowToken(), 0);
                editText3.clearFocus();
            }
        }
    }

    public final void j(boolean z9, boolean z10, boolean z11, boolean z12) {
        EditText[] editTextArr;
        int actualMaximum;
        int i5;
        int i7;
        int i9;
        SeslNumberPicker seslNumberPicker = this.f8197p;
        if (z10) {
            seslNumberPicker.setMinValue(this.h.get(1));
            seslNumberPicker.setMaxValue(this.f8191i.get(1));
            seslNumberPicker.setWrapSelectorWheel(false);
        }
        SeslNumberPicker seslNumberPicker2 = this.o;
        if (z11) {
            if (this.f8191i.get(1) - this.h.get(1) == 0) {
                i7 = this.h.get(2);
                i9 = this.f8191i.get(2);
            } else {
                int i10 = this.f8192j.get(1);
                if (i10 == this.h.get(1)) {
                    i7 = this.h.get(2);
                } else if (i10 == this.f8191i.get(1)) {
                    i9 = this.f8191i.get(2);
                    i7 = 0;
                } else {
                    i7 = 0;
                }
                i9 = 11;
            }
            if (k()) {
                i7++;
                i9++;
            }
            seslNumberPicker2.setDisplayedValues(null);
            seslNumberPicker2.setMinValue(i7);
            seslNumberPicker2.setMaxValue(i9);
            if (!k()) {
                seslNumberPicker2.setDisplayedValues((String[]) Arrays.copyOfRange(this.f8203v, seslNumberPicker2.getMinValue(), seslNumberPicker2.getMaxValue() + 1));
            }
        }
        SeslNumberPicker seslNumberPicker3 = this.f8196n;
        if (z12) {
            int i11 = this.f8191i.get(1) - this.h.get(1);
            int i12 = this.f8191i.get(2) - this.h.get(2);
            if (i11 == 0 && i12 == 0) {
                i5 = this.h.get(5);
                actualMaximum = this.f8191i.get(5);
            } else {
                int i13 = this.f8192j.get(1);
                int i14 = this.f8192j.get(2);
                if (i13 == this.h.get(1) && i14 == this.h.get(2)) {
                    i5 = this.h.get(5);
                    actualMaximum = this.f8192j.getActualMaximum(5);
                } else {
                    actualMaximum = (i13 == this.f8191i.get(1) && i14 == this.f8191i.get(2)) ? this.f8191i.get(5) : this.f8192j.getActualMaximum(5);
                    i5 = 1;
                }
            }
            seslNumberPicker3.setMinValue(i5);
            seslNumberPicker3.setMaxValue(actualMaximum);
        }
        if (z9) {
            seslNumberPicker.setValue(this.f8192j.get(1));
            int i15 = this.f8192j.get(2);
            if (k()) {
                seslNumberPicker2.setValue(i15 + 1);
            } else {
                seslNumberPicker2.setValue(i15);
            }
            seslNumberPicker3.setValue(this.f8192j.get(5));
            if (k()) {
                this.f8199r.setRawInputType(2);
            }
            if (!this.f8188e || (editTextArr = this.f8186A) == null) {
                return;
            }
            for (EditText editText : editTextArr) {
                if (editText.hasFocus()) {
                    editText.setSelection(0, 0);
                    editText.selectAll();
                    return;
                }
            }
        }
    }

    public final boolean k() {
        return Character.isDigit(this.f8203v[0].charAt(0));
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        b(configuration.locale);
        f();
    }

    @Override // android.view.View
    public final void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.getText().add(DateUtils.formatDateTime(this.f8189f, this.f8192j.getTimeInMillis(), 20));
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        super.requestLayout();
        SeslNumberPicker seslNumberPicker = this.f8196n;
        if (seslNumberPicker != null) {
            seslNumberPicker.requestLayout();
        }
        SeslNumberPicker seslNumberPicker2 = this.f8197p;
        if (seslNumberPicker2 != null) {
            seslNumberPicker2.requestLayout();
        }
        SeslNumberPicker seslNumberPicker3 = this.o;
        if (seslNumberPicker3 != null) {
            seslNumberPicker3.requestLayout();
        }
    }

    @Override // android.view.View
    public final void setEnabled(boolean z9) {
        this.f8196n.setEnabled(z9);
        this.o.setEnabled(z9);
        this.f8197p.setEnabled(z9);
    }
}
