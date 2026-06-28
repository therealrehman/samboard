package androidx.recyclerview.widget;

import android.content.Context;
import android.util.DisplayMetrics;

/* JADX INFO: renamed from: androidx.recyclerview.widget.f0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0333f0 extends U {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ float f9133a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f9134b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0333f0(RecyclerView recyclerView, Context context, float f2) {
        super(context);
        this.f9134b = recyclerView;
        this.f9133a = f2;
    }

    @Override // androidx.recyclerview.widget.U
    public final float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        return this.f9133a / this.f9134b.computeHorizontalScrollRange();
    }

    @Override // androidx.recyclerview.widget.U
    public final int getHorizontalSnapPreference() {
        return 1;
    }

    @Override // androidx.recyclerview.widget.U
    public final int getVerticalSnapPreference() {
        return 1;
    }
}
