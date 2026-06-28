package androidx.picker.adapter.layoutmanager;

import android.content.Context;
import androidx.recyclerview.widget.G0;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.R0;
import com.samsung.android.keyscafe.R;
import i0.a;
import i0.b;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002¨\u0006\u0003"}, d2 = {"Landroidx/picker/adapter/layoutmanager/AutoFitGridLayoutManager;", "Landroidx/recyclerview/widget/GridLayoutManager;", "Li0/a;", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public final class AutoFitGridLayoutManager extends GridLayoutManager implements a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f7850m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f7851n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f7852p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f7853q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f7854r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutoFitGridLayoutManager(Context context) {
        super(1);
        j.f(context, "context");
        this.f7850m = "AutoFitGridLayoutManager";
        this.o = context.getResources().getDimensionPixelOffset(R.dimen.picker_app_grid_item_view_item_width_land);
        this.f7852p = context.getResources().getDimensionPixelOffset(R.dimen.picker_app_selected_layout_horizontal_interval);
        this.f7854r = true;
    }

    @Override // i0.a
    /* JADX INFO: renamed from: getLogTag, reason: from getter */
    public final String getF7850m() {
        return this.f7850m;
    }

    @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.AbstractC0370y0
    public final void onLayoutChildren(G0 g02, R0 r02) {
        if (!this.f7853q) {
            int i5 = this.f7851n;
            int width = getWidth();
            int i7 = this.o;
            if (i5 != width || (this.f7854r && i7 > 0)) {
                int width2 = (getWidth() - getPaddingStart()) - getPaddingEnd();
                int i9 = this.f7852p;
                int i10 = (width2 + i9) / (i7 + i9);
                if (1 >= i10) {
                    i10 = 1;
                }
                b.a(this, "onLayoutChildren " + this.f8899f + " -> " + i10 + ", availableWidth=" + width2);
                p(i10);
                this.f7854r = false;
                this.f7851n = getWidth();
            }
        }
        super.onLayoutChildren(g02, r02);
    }
}
