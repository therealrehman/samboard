package androidx.picker.widget;

/* JADX INFO: renamed from: androidx.picker.widget.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0304y implements E, G, C {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ SeslDatePickerSpinnerLayout f8453a;

    public /* synthetic */ C0304y(SeslDatePickerSpinnerLayout seslDatePickerSpinnerLayout) {
        this.f8453a = seslDatePickerSpinnerLayout;
    }

    @Override // androidx.picker.widget.E
    public void a(boolean z9) {
        SeslDatePickerSpinnerLayout seslDatePickerSpinnerLayout = this.f8453a;
        seslDatePickerSpinnerLayout.d(z9);
        if (seslDatePickerSpinnerLayout.f8188e == z9 || z9) {
            return;
        }
        SeslNumberPicker seslNumberPicker = seslDatePickerSpinnerLayout.f8196n;
        if (seslNumberPicker.f8209e.f7986h0) {
            seslNumberPicker.setEditTextMode(false);
        }
        SeslNumberPicker seslNumberPicker2 = seslDatePickerSpinnerLayout.o;
        if (seslNumberPicker2.f8209e.f7986h0) {
            seslNumberPicker2.setEditTextMode(false);
        }
        SeslNumberPicker seslNumberPicker3 = seslDatePickerSpinnerLayout.f8197p;
        if (seslNumberPicker3.f8209e.f7986h0) {
            seslNumberPicker3.setEditTextMode(false);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    @Override // androidx.picker.widget.G
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b(androidx.picker.widget.SeslNumberPicker r8, int r9, int r10) {
        /*
            Method dump skipped, instruction units count: 260
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.picker.widget.C0304y.b(androidx.picker.widget.SeslNumberPicker, int, int):void");
    }
}
