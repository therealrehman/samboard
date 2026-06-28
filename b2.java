package androidx.appcompat.widget;

import l.C0669k;
import m7.AbstractC0752G;

/* JADX INFO: loaded from: classes.dex */
public final class b2 extends AbstractC0752G {
    public final /* synthetic */ int h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f6674i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f6675j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ Object f6676k;

    public b2(c2 c2Var, int i5) {
        this.h = 0;
        this.f6676k = c2Var;
        this.f6675j = i5;
        this.f6674i = false;
    }

    @Override // m7.AbstractC0752G, androidx.core.view.f0
    public final void b() {
        switch (this.h) {
            case 0:
                ((c2) this.f6676k).f6679a.setVisibility(0);
                break;
            default:
                if (!this.f6674i) {
                    this.f6674i = true;
                    androidx.core.view.f0 f0Var = ((C0669k) this.f6676k).f11990d;
                    if (f0Var != null) {
                        f0Var.b();
                    }
                    break;
                }
                break;
        }
    }

    @Override // m7.AbstractC0752G, androidx.core.view.f0
    public void onAnimationCancel() {
        switch (this.h) {
            case 0:
                this.f6674i = true;
                break;
        }
    }

    @Override // androidx.core.view.f0
    public final void onAnimationEnd() {
        switch (this.h) {
            case 0:
                if (!this.f6674i) {
                    ((c2) this.f6676k).f6679a.setVisibility(this.f6675j);
                }
                break;
            default:
                int i5 = this.f6675j + 1;
                this.f6675j = i5;
                C0669k c0669k = (C0669k) this.f6676k;
                if (i5 == c0669k.f11987a.size()) {
                    androidx.core.view.f0 f0Var = c0669k.f11990d;
                    if (f0Var != null) {
                        f0Var.onAnimationEnd();
                    }
                    this.f6675j = 0;
                    this.f6674i = false;
                    c0669k.f11991e = false;
                }
                break;
        }
    }

    public b2(C0669k c0669k) {
        this.h = 1;
        this.f6676k = c0669k;
        this.f6674i = false;
        this.f6675j = 0;
    }
}
