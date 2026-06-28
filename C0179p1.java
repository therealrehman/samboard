package androidx.appcompat.widget;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.samsung.android.keyscafe.R;

/* JADX INFO: renamed from: androidx.appcompat.widget.p1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0179p1 extends ArrayAdapter {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f6834e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f6835f;

    @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public final View getDropDownView(int i5, View view, ViewGroup parent) {
        kotlin.jvm.internal.j.f(parent, "parent");
        View dropDownView = super.getDropDownView(i5, view, parent);
        if (view == null) {
            this.f6834e = dropDownView.getPaddingTop();
            this.f6835f = dropDownView.getPaddingBottom();
        }
        int dimensionPixelSize = dropDownView.getResources().getDimensionPixelSize(R.dimen.sesl_popup_menu_first_last_item_vertical_edge_padding);
        int i7 = this.f6834e + dimensionPixelSize;
        int i9 = this.f6835f + dimensionPixelSize;
        int paddingLeft = dropDownView.getPaddingLeft();
        if (i5 != 0) {
            i7 = this.f6834e;
        }
        int paddingRight = dropDownView.getPaddingRight();
        if (i5 != getCount() - 1) {
            i9 = this.f6835f;
        }
        dropDownView.setPadding(paddingLeft, i7, paddingRight, i9);
        return dropDownView;
    }
}
