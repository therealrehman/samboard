package androidx.picker.widget;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

/* JADX INFO: renamed from: androidx.picker.widget.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ViewOnFocusChangeListenerC0294n implements View.OnFocusChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8432a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f8433b;

    public /* synthetic */ ViewOnFocusChangeListenerC0294n(int i5, Object obj) {
        this.f8432a = i5;
        this.f8433b = obj;
    }

    @Override // android.view.View.OnFocusChangeListener
    public final void onFocusChange(View view, boolean z9) {
        switch (this.f8432a) {
            case 0:
                if (!z9) {
                    ((SeslDatePicker) this.f8433b).l();
                }
                break;
            case 1:
                if (z9) {
                    SeslDatePicker seslDatePicker = (SeslDatePicker) this.f8433b;
                    if (seslDatePicker.f8180v == 1) {
                        seslDatePicker.setEditTextMode(false);
                    }
                }
                break;
            default:
                P p4 = (P) this.f8433b;
                if (!z9) {
                    p4.f7980e.setSelection(0, 0);
                    p4.getClass();
                    String strValueOf = String.valueOf(((TextView) view).getText());
                    int iH = p4.h(strValueOf);
                    if (TextUtils.isEmpty(strValueOf) || p4.o == iH) {
                        int i5 = p4.f7999p;
                        if (i5 != 1 && p4.f8001q && p4.f8003r) {
                            p4.b(iH % i5 == 0);
                        } else {
                            p4.B();
                        }
                    } else {
                        int i7 = p4.f7999p;
                        if (i7 != 1 && p4.f8001q) {
                            p4.b(iH % i7 == 0);
                        }
                        p4.w(iH, true);
                    }
                } else {
                    p4.u(true);
                    p4.f7980e.selectAll();
                }
                break;
        }
    }
}
