package androidx.picker.widget;

import android.view.KeyEvent;
import android.widget.TextView;

/* JADX INFO: renamed from: androidx.picker.widget.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0305z implements TextView.OnEditorActionListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f8454e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f8455f;

    public /* synthetic */ C0305z(int i5, Object obj) {
        this.f8454e = i5;
        this.f8455f = obj;
    }

    @Override // android.widget.TextView.OnEditorActionListener
    public final boolean onEditorAction(TextView textView, int i5, KeyEvent keyEvent) {
        switch (this.f8454e) {
            case 0:
                if (i5 == 6) {
                    SeslDatePickerSpinnerLayout seslDatePickerSpinnerLayout = (SeslDatePickerSpinnerLayout) this.f8455f;
                    seslDatePickerSpinnerLayout.i();
                    seslDatePickerSpinnerLayout.d(false);
                }
                break;
            default:
                if (i5 == 6) {
                    j0 j0Var = (j0) this.f8455f;
                    if (!j0Var.f8406g) {
                        SeslNumberPicker seslNumberPicker = j0Var.f8407i;
                        P p4 = seslNumberPicker.f8209e;
                        if ((p4.f7999p == 1 || p4.f8001q) && seslNumberPicker.getValue() % 5 != 0) {
                            seslNumberPicker.f8209e.b(false);
                        }
                    }
                    j0.a(j0Var);
                    j0Var.f(false);
                }
                break;
        }
        return false;
    }
}
