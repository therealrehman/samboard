package androidx.recyclerview.widget;

import android.view.View;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class j1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f9172a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f9173b = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f9174c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f9175d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f9176e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ StaggeredGridLayoutManager f9177f;

    public j1(StaggeredGridLayoutManager staggeredGridLayoutManager, int i5) {
        this.f9177f = staggeredGridLayoutManager;
        this.f9176e = i5;
    }

    public final void a() {
        View view = (View) this.f9172a.get(r0.size() - 1);
        g1 g1Var = (g1) view.getLayoutParams();
        this.f9174c = this.f9177f.f9004g.b(view);
        g1Var.getClass();
    }

    public final void b() {
        this.f9172a.clear();
        this.f9173b = Integer.MIN_VALUE;
        this.f9174c = Integer.MIN_VALUE;
        this.f9175d = 0;
    }

    public final int c() {
        boolean z9 = this.f9177f.f9008l;
        ArrayList arrayList = this.f9172a;
        return z9 ? e(arrayList.size() - 1, -1, false, true) : e(0, arrayList.size(), false, true);
    }

    public final int d() {
        boolean z9 = this.f9177f.f9008l;
        ArrayList arrayList = this.f9172a;
        return z9 ? e(0, arrayList.size(), false, true) : e(arrayList.size() - 1, -1, false, true);
    }

    public final int e(int i5, int i7, boolean z9, boolean z10) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = this.f9177f;
        int iK = staggeredGridLayoutManager.f9004g.k();
        int iG = staggeredGridLayoutManager.f9004g.g();
        int i9 = i7 > i5 ? 1 : -1;
        while (i5 != i7) {
            View view = (View) this.f9172a.get(i5);
            int iE = staggeredGridLayoutManager.f9004g.e(view);
            int iB = staggeredGridLayoutManager.f9004g.b(view);
            boolean z11 = false;
            boolean z12 = !z10 ? iE >= iG : iE > iG;
            if (!z10 ? iB > iK : iB >= iK) {
                z11 = true;
            }
            if (z12 && z11) {
                if (z9) {
                    return staggeredGridLayoutManager.getPosition(view);
                }
                if (iE < iK || iB > iG) {
                    return staggeredGridLayoutManager.getPosition(view);
                }
            }
            i5 += i9;
        }
        return -1;
    }

    public final int f(int i5) {
        int i7 = this.f9174c;
        if (i7 != Integer.MIN_VALUE) {
            return i7;
        }
        if (this.f9172a.size() == 0) {
            return i5;
        }
        a();
        return this.f9174c;
    }

    public final View g(int i5, int i7) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = this.f9177f;
        ArrayList arrayList = this.f9172a;
        View view = null;
        if (i7 != -1) {
            int size = arrayList.size() - 1;
            while (size >= 0) {
                View view2 = (View) arrayList.get(size);
                if ((staggeredGridLayoutManager.f9008l && staggeredGridLayoutManager.getPosition(view2) >= i5) || ((!staggeredGridLayoutManager.f9008l && staggeredGridLayoutManager.getPosition(view2) <= i5) || !view2.hasFocusable())) {
                    break;
                }
                size--;
                view = view2;
            }
        } else {
            int size2 = arrayList.size();
            int i9 = 0;
            while (i9 < size2) {
                View view3 = (View) arrayList.get(i9);
                if ((staggeredGridLayoutManager.f9008l && staggeredGridLayoutManager.getPosition(view3) <= i5) || ((!staggeredGridLayoutManager.f9008l && staggeredGridLayoutManager.getPosition(view3) >= i5) || !view3.hasFocusable())) {
                    break;
                }
                i9++;
                view = view3;
            }
        }
        return view;
    }

    public final int h(int i5) {
        int i7 = this.f9173b;
        if (i7 != Integer.MIN_VALUE) {
            return i7;
        }
        if (this.f9172a.size() == 0) {
            return i5;
        }
        View view = (View) this.f9172a.get(0);
        g1 g1Var = (g1) view.getLayoutParams();
        this.f9173b = this.f9177f.f9004g.e(view);
        g1Var.getClass();
        return this.f9173b;
    }
}
