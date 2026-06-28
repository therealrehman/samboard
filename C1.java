package androidx.recyclerview.widget;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class c1 extends AbstractC0358s0 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f9120d;

    @Override // androidx.recyclerview.widget.AbstractC0358s0
    public final boolean a(V0 v02, V0 v03, C0356r0 c0356r0, C0356r0 c0356r02) {
        int i5;
        int i7;
        int i9 = c0356r0.f9252a;
        int i10 = c0356r0.f9253b;
        if (v03.shouldIgnore()) {
            int i11 = c0356r0.f9252a;
            i7 = c0356r0.f9253b;
            i5 = i11;
        } else {
            i5 = c0356r02.f9252a;
            i7 = c0356r02.f9253b;
        }
        C0357s c0357s = (C0357s) this;
        if (v02 == v03) {
            return c0357s.g(v02, i9, i10, i5, i7);
        }
        float translationX = v02.itemView.getTranslationX();
        float translationY = v02.itemView.getTranslationY();
        float alpha = v02.itemView.getAlpha();
        c0357s.l(v02);
        v02.itemView.setTranslationX(translationX);
        v02.itemView.setTranslationY(translationY);
        v02.itemView.setAlpha(alpha);
        c0357s.l(v03);
        v03.itemView.setTranslationX(-((int) ((i5 - i9) - translationX)));
        v03.itemView.setTranslationY(-((int) ((i7 - i10) - translationY)));
        v03.itemView.setAlpha(0.0f);
        ArrayList arrayList = c0357s.h;
        C0354q c0354q = new C0354q();
        c0354q.f9241a = v02;
        c0354q.f9242b = v03;
        c0354q.f9243c = i9;
        c0354q.f9244d = i10;
        c0354q.f9245e = i5;
        c0354q.f9246f = i7;
        arrayList.add(c0354q);
        int i12 = c0357s.f9265p;
        if ((i12 & 4) == 0) {
            c0357s.f9265p = i12 | 4;
        }
        return true;
    }

    public abstract boolean g(V0 v02, int i5, int i7, int i9, int i10);
}
