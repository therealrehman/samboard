package androidx.picker.widget;

import androidx.recyclerview.widget.GridLayoutManager;
import f0.C0517g;

/* JADX INFO: renamed from: androidx.picker.widget.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0283c extends androidx.recyclerview.widget.I {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8285a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ GridLayoutManager f8286b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ SeslAppPickerGridView f8287c;

    public /* synthetic */ C0283c(SeslAppPickerGridView seslAppPickerGridView, GridLayoutManager gridLayoutManager, int i5) {
        this.f8285a = i5;
        this.f8287c = seslAppPickerGridView;
        this.f8286b = gridLayoutManager;
    }

    @Override // androidx.recyclerview.widget.I
    public final int getSpanSize(int i5) {
        int i7;
        int i9;
        switch (this.f8285a) {
            case 0:
                SeslAppPickerGridView seslAppPickerGridView = this.f8287c;
                C0517g c0517g = seslAppPickerGridView.f8378e;
                if (c0517g == null || i5 < 0 || i5 >= c0517g.getItemCount()) {
                    return 1;
                }
                B0.h hVarE = seslAppPickerGridView.f8378e.e(i5);
                boolean z9 = hVarE instanceof B0.c;
                GridLayoutManager gridLayoutManager = this.f8286b;
                return (!z9 || (i7 = ((B0.c) hVarE).f163d) == -1) ? gridLayoutManager.f8899f : i7;
            default:
                SeslAppPickerGridView seslAppPickerGridView2 = this.f8287c;
                C0517g c0517g2 = seslAppPickerGridView2.f8378e;
                if (c0517g2 == null || i5 < 0 || i5 >= c0517g2.getItemCount()) {
                    return 1;
                }
                B0.h hVarE2 = seslAppPickerGridView2.f8378e.e(i5);
                boolean z10 = hVarE2 instanceof B0.c;
                GridLayoutManager gridLayoutManager2 = this.f8286b;
                return (!z10 || (i9 = ((B0.c) hVarE2).f163d) == -1) ? gridLayoutManager2.f8899f : i9;
        }
    }
}
