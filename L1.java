package androidx.recyclerview.widget;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final class l1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k1 f9186a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final P2.a f9187b;

    public l1(k1 k1Var) {
        this.f9186a = k1Var;
        P2.a aVar = new P2.a();
        aVar.f2403b = 0;
        this.f9187b = aVar;
    }

    public final View a(int i5, int i7, int i9, int i10) {
        k1 k1Var = this.f9186a;
        int iD = k1Var.d();
        int iA = k1Var.a();
        int i11 = i7 > i5 ? 1 : -1;
        View view = null;
        while (i5 != i7) {
            View viewC = k1Var.c(i5);
            int iB = k1Var.b(viewC);
            int iE = k1Var.e(viewC);
            P2.a aVar = this.f9187b;
            aVar.f2404c = iD;
            aVar.f2405d = iA;
            aVar.f2406e = iB;
            aVar.f2407f = iE;
            if (i9 != 0) {
                aVar.f2403b = i9;
                if (aVar.a()) {
                    return viewC;
                }
            }
            if (i10 != 0) {
                aVar.f2403b = i10;
                if (aVar.a()) {
                    view = viewC;
                }
            }
            i5 += i11;
        }
        return view;
    }

    public final boolean b(View view) {
        k1 k1Var = this.f9186a;
        int iD = k1Var.d();
        int iA = k1Var.a();
        int iB = k1Var.b(view);
        int iE = k1Var.e(view);
        P2.a aVar = this.f9187b;
        aVar.f2404c = iD;
        aVar.f2405d = iA;
        aVar.f2406e = iB;
        aVar.f2407f = iE;
        aVar.f2403b = 24579;
        return aVar.a();
    }
}
