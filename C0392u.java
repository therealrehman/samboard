package androidx.transition;

import android.view.View;

/* JADX INFO: renamed from: androidx.transition.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0392u extends W {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f9501e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f9502f;

    public /* synthetic */ C0392u() {
        this.f9501e = 2;
    }

    @Override // androidx.transition.T
    public final void onTransitionEnd(U u5) {
        switch (this.f9501e) {
            case 0:
                C0376d c0376d = f0.f9429a;
                ((View) this.f9502f).setTransitionAlpha(1.0f);
                u5.removeListener(this);
                break;
            case 1:
                ((U) this.f9502f).runAnimators();
                u5.removeListener(this);
                break;
            default:
                a0 a0Var = (a0) this.f9502f;
                int i5 = a0Var.f9401g - 1;
                a0Var.f9401g = i5;
                if (i5 == 0) {
                    a0Var.h = false;
                    a0Var.end();
                }
                u5.removeListener(this);
                break;
        }
    }

    @Override // androidx.transition.W, androidx.transition.T
    public void onTransitionStart(U u5) {
        switch (this.f9501e) {
            case 2:
                a0 a0Var = (a0) this.f9502f;
                if (!a0Var.h) {
                    a0Var.start();
                    a0Var.h = true;
                }
                break;
        }
    }

    public /* synthetic */ C0392u(int i5, Object obj) {
        this.f9501e = i5;
        this.f9502f = obj;
    }
}
