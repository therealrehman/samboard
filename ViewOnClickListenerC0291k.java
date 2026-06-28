package androidx.picker.widget;

import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.LinearLayout;

/* JADX INFO: renamed from: androidx.picker.widget.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ViewOnClickListenerC0291k implements View.OnClickListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f8424e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ LinearLayout f8425f;

    public /* synthetic */ ViewOnClickListenerC0291k(LinearLayout linearLayout, int i5) {
        this.f8424e = i5;
        this.f8425f = linearLayout;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.f8424e) {
            case 0:
                SeslColorPicker seslColorPicker = (SeslColorPicker) this.f8425f;
                int size = seslColorPicker.f8110y.size();
                for (int i5 = 0; i5 < size && i5 < 6; i5++) {
                    if (seslColorPicker.f8106u.getChildAt(i5).equals(view)) {
                        seslColorPicker.h = true;
                        int iIntValue = ((Integer) seslColorPicker.f8110y.get(i5)).intValue();
                        seslColorPicker.f8094g.O(iIntValue);
                        seslColorPicker.a(iIntValue);
                        seslColorPicker.getClass();
                    }
                }
                break;
            default:
                SeslDatePicker seslDatePicker = (SeslDatePicker) this.f8425f;
                seslDatePicker.setCurrentViewType((seslDatePicker.f8180v + 1) % 2);
                int i7 = seslDatePicker.f8180v;
                ObjectAnimator objectAnimator = seslDatePicker.f8163l0;
                ObjectAnimator objectAnimator2 = seslDatePicker.f8165m0;
                if (i7 == 0) {
                    if (objectAnimator2.isRunning()) {
                        objectAnimator2.cancel();
                    }
                    objectAnimator.start();
                } else {
                    if (objectAnimator.isRunning()) {
                        objectAnimator.cancel();
                    }
                    objectAnimator2.start();
                }
                break;
        }
    }
}
