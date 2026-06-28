package androidx.picker.widget;

import android.view.inputmethod.InputMethodManager;
import androidx.recyclerview.widget.D0;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: renamed from: androidx.picker.widget.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0286f extends D0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8373a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AbstractC0288h f8374b;

    public /* synthetic */ C0286f(SeslAppPickerGridView seslAppPickerGridView, int i5) {
        this.f8373a = i5;
        this.f8374b = seslAppPickerGridView;
    }

    @Override // androidx.recyclerview.widget.D0
    public final void onScrollStateChanged(RecyclerView recyclerView, int i5) {
        switch (this.f8373a) {
            case 0:
                if (i5 == 1) {
                    AbstractC0288h abstractC0288h = this.f8374b;
                    ((InputMethodManager) abstractC0288h.f8379f.getSystemService("input_method")).hideSoftInputFromWindow(abstractC0288h.getWindowToken(), 0);
                }
                break;
            default:
                AbstractC0288h abstractC0288h2 = this.f8374b;
                if (i5 == 0) {
                    abstractC0288h2.getClass();
                    break;
                } else if (i5 == 1) {
                    abstractC0288h2.getClass();
                    break;
                }
                break;
        }
    }
}
