package androidx.recyclerview.widget;

import android.util.Log;
import android.view.animation.BaseInterpolator;
import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes.dex */
public final class O0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f8962a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f8963b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f8964c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f8965d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Interpolator f8966e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f8967f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f8968g;

    public final void a(RecyclerView recyclerView) {
        int i5 = this.f8965d;
        if (i5 >= 0) {
            this.f8965d = -1;
            recyclerView.jumpToPositionForSmoothScroller(i5);
            this.f8967f = false;
            return;
        }
        if (!this.f8967f) {
            this.f8968g = 0;
            return;
        }
        Interpolator interpolator = this.f8966e;
        if (interpolator != null && this.f8964c < 1) {
            throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
        }
        int i7 = this.f8964c;
        if (i7 < 1) {
            throw new IllegalStateException("Scroll duration must be a positive number");
        }
        recyclerView.mViewFlinger.c(this.f8962a, this.f8963b, interpolator, i7);
        int i9 = this.f8968g + 1;
        this.f8968g = i9;
        if (i9 > 10) {
            Log.e("SeslRecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
        }
        this.f8967f = false;
    }

    public final void b(int i5, int i7, int i9, BaseInterpolator baseInterpolator) {
        this.f8962a = i5;
        this.f8963b = i7;
        this.f8964c = i9;
        this.f8966e = baseInterpolator;
        this.f8967f = true;
    }
}
