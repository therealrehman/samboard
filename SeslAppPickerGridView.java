package androidx.picker.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.picker.adapter.layoutmanager.AutoFitGridLayoutManager;
import androidx.recyclerview.widget.AbstractC0370y0;
import androidx.recyclerview.widget.GridLayoutManager;
import com.samsung.android.keyscafe.R;
import f0.C0515e;
import f0.C0517g;
import v7.AbstractC1115c;

/* JADX INFO: loaded from: classes.dex */
public class SeslAppPickerGridView extends AbstractC0288h {
    public SeslAppPickerGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int dimensionPixelOffset = getContext().getResources().getDimensionPixelOffset(R.dimen.picker_app_grid_item_interval_spacing) / 2;
        setPadding(0, dimensionPixelOffset, 0, dimensionPixelOffset);
        setClipToPadding(false);
        this.f8382j = 1;
        Context context2 = this.f8379f;
        int i5 = this.f8383k;
        C0515e c0515e = new C0515e(context2, i5);
        c0515e.setHasStableIds(true);
        C0517g c0517g = new C0517g(c0515e);
        this.f8378e = c0517g;
        while (getItemDecorationCount() > 0) {
            removeItemDecorationAt(0);
        }
        addItemDecoration(new n0.b(context2, this.f8380g));
        addItemDecoration(new n0.a(getContext().getResources().getDimensionPixelOffset(R.dimen.picker_app_grid_item_interval_spacing)));
        addItemDecoration(new n0.c(context2, c0517g, B.b.a(context2, AbstractC1115c.a(i5))));
        AutoFitGridLayoutManager autoFitGridLayoutManager = new AutoFitGridLayoutManager(context2);
        autoFitGridLayoutManager.f8903k = new C0283c(this, autoFitGridLayoutManager, 0);
        setLayoutManager(autoFitGridLayoutManager);
        setAdapter(this.f8378e);
        C0517g c0517g2 = this.f8378e;
        c0517g2.getClass();
        c0517g2.f10778e.getClass();
        seslSetGoToTopEnabled(true);
        seslSetFastScrollerEnabled(true);
        seslSetFillBottomEnabled(true);
    }

    @Override // androidx.picker.widget.AbstractC0288h, i0.a
    /* JADX INFO: renamed from: getLogTag */
    public String getF7850m() {
        return "SeslAppPickerGridView";
    }

    public void setGridSpanCount(int i5) {
        AbstractC0370y0 layoutManager = getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            if (gridLayoutManager.f8899f == i5) {
                return;
            }
            if (layoutManager instanceof AutoFitGridLayoutManager) {
                AutoFitGridLayoutManager autoFitGridLayoutManager = (AutoFitGridLayoutManager) layoutManager;
                i0.b.a(autoFitGridLayoutManager, "setSpanCount " + autoFitGridLayoutManager.f8899f + " -> " + i5);
                autoFitGridLayoutManager.f7853q = true;
                autoFitGridLayoutManager.p(i5);
            } else {
                gridLayoutManager.p(i5);
            }
            gridLayoutManager.f8903k = new C0283c(this, gridLayoutManager, 1);
            C0517g c0517g = this.f8378e;
            if (c0517g != null) {
                c0517g.notifyDataSetChanged();
            }
        }
    }
}
