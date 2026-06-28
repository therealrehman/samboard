package androidx.picker.widget;

import android.os.Message;
import android.util.SparseArray;
import java.util.Calendar;

/* JADX INFO: renamed from: androidx.picker.widget.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0297q implements androidx.viewpager.widget.g {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ SeslDatePicker f8438e;

    public C0297q(SeslDatePicker seslDatePicker) {
        this.f8438e = seslDatePicker;
    }

    @Override // androidx.viewpager.widget.g
    public final void onPageScrollStateChanged(int i5) {
    }

    @Override // androidx.viewpager.widget.g
    public final void onPageScrolled(int i5, float f2, int i7) {
    }

    @Override // androidx.viewpager.widget.g
    public final void onPageSelected(int i5) {
        SeslDatePicker seslDatePicker = this.f8438e;
        if (seslDatePicker.f8162l) {
            seslDatePicker.f8157i = false;
        }
        seslDatePicker.f8136M = i5;
        int minMonth = seslDatePicker.getMinMonth() + i5;
        int minYear = seslDatePicker.getMinYear() + (minMonth / 12);
        int i7 = minMonth % 12;
        int actualMaximum = seslDatePicker.o.get(5);
        Calendar calendar = seslDatePicker.f8173r;
        boolean z9 = minYear != calendar.get(1);
        calendar.set(1, minYear);
        calendar.set(2, i7);
        calendar.set(5, 1);
        if (actualMaximum > calendar.getActualMaximum(5)) {
            actualMaximum = calendar.getActualMaximum(5);
        }
        calendar.set(5, actualMaximum);
        HandlerC0295o handlerC0295o = seslDatePicker.f8176s0;
        Message messageObtainMessage = handlerC0295o.obtainMessage();
        messageObtainMessage.what = 1000;
        messageObtainMessage.obj = Boolean.valueOf(z9);
        handlerC0295o.sendMessage(messageObtainMessage);
        Message messageObtainMessage2 = handlerC0295o.obtainMessage();
        messageObtainMessage2.what = 1001;
        handlerC0295o.sendMessage(messageObtainMessage2);
        SparseArray sparseArray = (SparseArray) seslDatePicker.f8141R.f8440b;
        if (sparseArray.get(i5) != null) {
            ((V) sparseArray.get(i5)).a();
            ((V) sparseArray.get(i5)).setImportantForAccessibility(1);
        }
        if (i5 != 0) {
            int i9 = i5 - 1;
            if (sparseArray.get(i9) != null) {
                ((V) sparseArray.get(i9)).a();
                ((V) sparseArray.get(i9)).setImportantForAccessibility(2);
            }
        }
        if (i5 != seslDatePicker.f8137N - 1) {
            int i10 = i5 + 1;
            if (sparseArray.get(i10) != null) {
                ((V) sparseArray.get(i10)).a();
                ((V) sparseArray.get(i10)).setImportantForAccessibility(2);
            }
        }
    }
}
