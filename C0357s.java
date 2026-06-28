package androidx.recyclerview.widget;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.PathInterpolator;
import java.util.ArrayList;

/* JADX INFO: renamed from: androidx.recyclerview.widget.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0357s extends c1 {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static TimeInterpolator f9254r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final PathInterpolator f9255s = new PathInterpolator(0.4f, 0.6f, 0.0f, 1.0f);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList f9256e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ArrayList f9257f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ArrayList f9258g;
    public ArrayList h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ArrayList f9259i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ArrayList f9260j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ArrayList f9261k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ArrayList f9262l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ArrayList f9263m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public ArrayList f9264n;
    public ArrayList o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f9265p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f9266q;

    public static void h(ArrayList arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            ((V0) arrayList.get(size)).itemView.animate().cancel();
        }
    }

    @Override // androidx.recyclerview.widget.AbstractC0358s0
    public final void d(V0 v02) {
        View view = v02.itemView;
        view.animate().cancel();
        ArrayList arrayList = this.f9258g;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            if (((r) arrayList.get(size)).f9247a == v02) {
                view.setTranslationY(0.0f);
                view.setTranslationX(0.0f);
                c(v02);
                arrayList.remove(size);
            }
        }
        j(this.h, v02);
        if (this.f9256e.remove(v02)) {
            view.setAlpha(1.0f);
            c(v02);
        }
        if (this.f9257f.remove(v02)) {
            view.setAlpha(1.0f);
            c(v02);
        }
        ArrayList arrayList2 = this.f9261k;
        for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
            ArrayList arrayList3 = (ArrayList) arrayList2.get(size2);
            j(arrayList3, v02);
            if (arrayList3.isEmpty()) {
                arrayList2.remove(size2);
            }
        }
        ArrayList arrayList4 = this.f9260j;
        for (int size3 = arrayList4.size() - 1; size3 >= 0; size3--) {
            ArrayList arrayList5 = (ArrayList) arrayList4.get(size3);
            int size4 = arrayList5.size() - 1;
            while (true) {
                if (size4 < 0) {
                    break;
                }
                if (((r) arrayList5.get(size4)).f9247a == v02) {
                    view.setTranslationY(0.0f);
                    view.setTranslationX(0.0f);
                    c(v02);
                    arrayList5.remove(size4);
                    if (arrayList5.isEmpty()) {
                        arrayList4.remove(size3);
                    }
                } else {
                    size4--;
                }
            }
        }
        ArrayList arrayList6 = this.f9259i;
        for (int size5 = arrayList6.size() - 1; size5 >= 0; size5--) {
            ArrayList arrayList7 = (ArrayList) arrayList6.get(size5);
            if (arrayList7.remove(v02)) {
                view.setAlpha(1.0f);
                c(v02);
                if (arrayList7.isEmpty()) {
                    arrayList6.remove(size5);
                }
            }
        }
        this.f9264n.remove(v02);
        this.f9262l.remove(v02);
        this.o.remove(v02);
        this.f9263m.remove(v02);
        i();
    }

    @Override // androidx.recyclerview.widget.AbstractC0358s0
    public final void e() {
        ArrayList arrayList = this.f9258g;
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                break;
            }
            r rVar = (r) arrayList.get(size);
            View view = rVar.f9247a.itemView;
            view.setTranslationY(0.0f);
            view.setTranslationX(0.0f);
            c(rVar.f9247a);
            arrayList.remove(size);
        }
        ArrayList arrayList2 = this.f9256e;
        for (int size2 = arrayList2.size() - 1; size2 >= 0; size2--) {
            c((V0) arrayList2.get(size2));
            arrayList2.remove(size2);
        }
        ArrayList arrayList3 = this.f9257f;
        int size3 = arrayList3.size();
        while (true) {
            size3--;
            if (size3 < 0) {
                break;
            }
            V0 v02 = (V0) arrayList3.get(size3);
            v02.itemView.setAlpha(1.0f);
            c(v02);
            arrayList3.remove(size3);
        }
        ArrayList arrayList4 = this.h;
        for (int size4 = arrayList4.size() - 1; size4 >= 0; size4--) {
            C0354q c0354q = (C0354q) arrayList4.get(size4);
            V0 v03 = c0354q.f9241a;
            if (v03 != null) {
                k(c0354q, v03);
            }
            V0 v04 = c0354q.f9242b;
            if (v04 != null) {
                k(c0354q, v04);
            }
        }
        arrayList4.clear();
        if (f()) {
            ArrayList arrayList5 = this.f9260j;
            for (int size5 = arrayList5.size() - 1; size5 >= 0; size5--) {
                ArrayList arrayList6 = (ArrayList) arrayList5.get(size5);
                for (int size6 = arrayList6.size() - 1; size6 >= 0; size6--) {
                    r rVar2 = (r) arrayList6.get(size6);
                    View view2 = rVar2.f9247a.itemView;
                    view2.setTranslationY(0.0f);
                    view2.setTranslationX(0.0f);
                    c(rVar2.f9247a);
                    arrayList6.remove(size6);
                    if (arrayList6.isEmpty()) {
                        arrayList5.remove(arrayList6);
                    }
                }
            }
            ArrayList arrayList7 = this.f9259i;
            for (int size7 = arrayList7.size() - 1; size7 >= 0; size7--) {
                ArrayList arrayList8 = (ArrayList) arrayList7.get(size7);
                for (int size8 = arrayList8.size() - 1; size8 >= 0; size8--) {
                    V0 v05 = (V0) arrayList8.get(size8);
                    v05.itemView.setAlpha(1.0f);
                    c(v05);
                    arrayList8.remove(size8);
                    if (arrayList8.isEmpty()) {
                        arrayList7.remove(arrayList8);
                    }
                }
            }
            ArrayList arrayList9 = this.f9261k;
            for (int size9 = arrayList9.size() - 1; size9 >= 0; size9--) {
                ArrayList arrayList10 = (ArrayList) arrayList9.get(size9);
                for (int size10 = arrayList10.size() - 1; size10 >= 0; size10--) {
                    C0354q c0354q2 = (C0354q) arrayList10.get(size10);
                    V0 v06 = c0354q2.f9241a;
                    if (v06 != null) {
                        k(c0354q2, v06);
                    }
                    V0 v07 = c0354q2.f9242b;
                    if (v07 != null) {
                        k(c0354q2, v07);
                    }
                    if (arrayList10.isEmpty()) {
                        arrayList9.remove(arrayList10);
                    }
                }
            }
            h(this.f9264n);
            h(this.f9263m);
            h(this.f9262l);
            h(this.o);
            ArrayList arrayList11 = this.f9268b;
            if (arrayList11.size() > 0) {
                A8.l.z(arrayList11.get(0));
                throw null;
            }
            arrayList11.clear();
        }
    }

    @Override // androidx.recyclerview.widget.AbstractC0358s0
    public final boolean f() {
        return (this.f9257f.isEmpty() && this.h.isEmpty() && this.f9258g.isEmpty() && this.f9256e.isEmpty() && this.f9263m.isEmpty() && this.f9264n.isEmpty() && this.f9262l.isEmpty() && this.o.isEmpty() && this.f9260j.isEmpty() && this.f9259i.isEmpty() && this.f9261k.isEmpty()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.c1
    public final boolean g(V0 v02, int i5, int i7, int i9, int i10) {
        View view = v02.itemView;
        int translationX = i5 + ((int) view.getTranslationX());
        int translationY = i7 + ((int) v02.itemView.getTranslationY());
        l(v02);
        int i11 = i9 - translationX;
        int i12 = i10 - translationY;
        if (i11 == 0 && i12 == 0) {
            c(v02);
            return false;
        }
        if (i11 != 0) {
            view.setTranslationX(-i11);
        }
        if (i12 != 0) {
            view.setTranslationY(-i12);
        }
        ArrayList arrayList = this.f9258g;
        r rVar = new r();
        rVar.f9247a = v02;
        rVar.f9248b = translationX;
        rVar.f9249c = translationY;
        rVar.f9250d = i9;
        rVar.f9251e = i10;
        arrayList.add(rVar);
        int i13 = this.f9265p;
        if ((i13 & 2) != 0) {
            return true;
        }
        this.f9265p = i13 | 2;
        return true;
    }

    public final void i() {
        if (f()) {
            return;
        }
        ArrayList arrayList = this.f9268b;
        if (arrayList.size() <= 0) {
            arrayList.clear();
        } else {
            A8.l.z(arrayList.get(0));
            throw null;
        }
    }

    public final void j(ArrayList arrayList, V0 v02) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            C0354q c0354q = (C0354q) arrayList.get(size);
            if (k(c0354q, v02) && c0354q.f9241a == null && c0354q.f9242b == null) {
                arrayList.remove(c0354q);
            }
        }
    }

    public final boolean k(C0354q c0354q, V0 v02) {
        if (c0354q.f9242b == v02) {
            c0354q.f9242b = null;
        } else {
            if (c0354q.f9241a != v02) {
                return false;
            }
            c0354q.f9241a = null;
        }
        v02.itemView.setAlpha(1.0f);
        v02.itemView.setTranslationX(0.0f);
        v02.itemView.setTranslationY(0.0f);
        c(v02);
        return true;
    }

    public final void l(V0 v02) {
        if (f9254r == null) {
            f9254r = new ValueAnimator().getInterpolator();
        }
        v02.itemView.animate().setInterpolator(f9254r);
        d(v02);
    }
}
