package androidx.picker.widget;

import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class SeslTimePicker extends FrameLayout {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final j0 f8217e;

    public SeslTimePicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.timePickerStyle, 0);
        this.f8217e = new j0(this, context, attributeSet);
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        this.f8217e.d(accessibilityEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.View
    public int getBaseline() {
        return this.f8217e.h.getBaseline();
    }

    public int getHour() {
        return this.f8217e.b();
    }

    public int getMinute() {
        return this.f8217e.f8407i.getValue();
    }

    @Override // android.view.View
    public final boolean isEnabled() {
        return this.f8217e.f8417t;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        j0 j0Var = this.f8217e;
        j0Var.getClass();
        Locale locale = configuration.locale;
        if (!locale.equals(j0Var.f8402c)) {
            j0Var.f8402c = locale;
        }
        j0Var.f8418u = Calendar.getInstance(locale);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        this.f8217e.getClass();
        accessibilityEvent.setClassName(TimePicker.class.getName());
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        this.f8217e.getClass();
        accessibilityNodeInfo.setClassName(TimePicker.class.getName());
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i5, int i7) {
        int mode = View.MeasureSpec.getMode(i5);
        int mode2 = View.MeasureSpec.getMode(i7);
        j0 j0Var = this.f8217e;
        if (mode == Integer.MIN_VALUE) {
            i5 = View.MeasureSpec.makeMeasureSpec(j0Var.f8394A, 1073741824);
        }
        if (mode2 == Integer.MIN_VALUE) {
            i7 = View.MeasureSpec.makeMeasureSpec(j0Var.f8395B, 1073741824);
        }
        super.onMeasure(i5, i7);
    }

    @Override // android.view.View
    public final void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        this.f8217e.d(accessibilityEvent);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        View.BaseSavedState baseSavedState = (View.BaseSavedState) parcelable;
        super.onRestoreInstanceState(baseSavedState.getSuperState());
        j0 j0Var = this.f8217e;
        j0Var.getClass();
        h0 h0Var = (h0) baseSavedState;
        j0Var.e(h0Var.f8387e);
        j0Var.g(h0Var.f8388f);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        Parcelable parcelableOnSaveInstanceState = super.onSaveInstanceState();
        j0 j0Var = this.f8217e;
        j0Var.getClass();
        return new h0(parcelableOnSaveInstanceState, j0Var.b(), j0Var.f8407i.getValue());
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        super.requestLayout();
        j0 j0Var = this.f8217e;
        if (j0Var != null) {
            SeslNumberPicker seslNumberPicker = j0Var.f8408j;
            if (seslNumberPicker != null) {
                seslNumberPicker.requestLayout();
            }
            SeslNumberPicker seslNumberPicker2 = j0Var.h;
            if (seslNumberPicker2 != null) {
                seslNumberPicker2.requestLayout();
            }
            SeslNumberPicker seslNumberPicker3 = j0Var.f8407i;
            if (seslNumberPicker3 != null) {
                seslNumberPicker3.requestLayout();
            }
        }
    }

    public void set5MinuteInterval(boolean z9) {
        j0 j0Var = this.f8217e;
        SeslNumberPicker seslNumberPicker = j0Var.f8407i;
        if (!z9) {
            seslNumberPicker.setCustomIntervalValue(5);
            return;
        }
        if (seslNumberPicker.getValue() >= 58) {
            int iB = j0Var.b();
            j0Var.e(iB == 23 ? 0 : iB + 1);
        }
        seslNumberPicker.setCustomIntervalValue(5);
        seslNumberPicker.f8209e.b(true);
        j0Var.f8396C = 5;
    }

    public void setCustomTimePickerIdleColor(int i5) {
        j0 j0Var = this.f8217e;
        j0Var.h.setCustomNumberPickerIdleColor(i5);
        j0Var.f8407i.setCustomNumberPickerIdleColor(i5);
        j0Var.f8408j.setCustomNumberPickerIdleColor(i5);
        j0Var.f8411m.setTextColor(i5);
        j0Var.f8400a.invalidate();
    }

    public void setCustomTimePickerScrollColor(int i5) {
        j0 j0Var = this.f8217e;
        j0Var.h.setCustomNumberPickerScrollColor(i5);
        j0Var.f8407i.setCustomNumberPickerScrollColor(i5);
        j0Var.f8408j.setCustomNumberPickerScrollColor(i5);
        j0Var.f8411m.setTextColor(j0Var.f8401b.getResources().getColor(com.samsung.android.keyscafe.R.color.sesl_number_picker_text_color_appwidget));
        j0Var.f8400a.invalidate();
    }

    public void setEditTextMode(boolean z9) {
        this.f8217e.f(z9);
    }

    @Override // android.view.View
    public void setEnabled(boolean z9) {
        super.setEnabled(z9);
        j0 j0Var = this.f8217e;
        j0Var.f8407i.setEnabled(z9);
        TextView textView = j0Var.f8411m;
        if (textView != null) {
            textView.setEnabled(z9);
        }
        j0Var.h.setEnabled(z9);
        j0Var.f8408j.setEnabled(z9);
        j0Var.f8417t = z9;
    }

    public void setHour(int i5) {
        this.f8217e.e(com.bumptech.glide.c.d(i5, 0, 23));
    }

    public void setIs24HourView(Boolean bool) {
        if (bool == null) {
            return;
        }
        j0 j0Var = this.f8217e;
        boolean zBooleanValue = bool.booleanValue();
        if (j0Var.f8403d == zBooleanValue) {
            return;
        }
        int iB = j0Var.b();
        j0Var.f8403d = zBooleanValue;
        j0Var.c();
        j0Var.j();
        j0Var.e(iB);
        j0Var.i();
    }

    public void setLocale(Locale locale) {
        j0 j0Var = this.f8217e;
        if (!locale.equals(j0Var.f8402c)) {
            j0Var.f8402c = locale;
        }
        j0Var.f8418u = Calendar.getInstance(locale);
    }

    public void setMinute(int i5) {
        this.f8217e.g(com.bumptech.glide.c.d(i5, 0, 59));
    }

    public void setOnEditTextModeChangedListener(e0 e0Var) {
        this.f8217e.getClass();
    }

    public void setOnTimeChangedListener(f0 f0Var) {
        this.f8217e.getClass();
    }
}
