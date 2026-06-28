package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* JADX INFO: renamed from: androidx.recyclerview.widget.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0346m extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f9188e = 1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ V0 f9189f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ View f9190g;
    public final /* synthetic */ ViewPropertyAnimator h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ C0357s f9191i;

    public C0346m(C0357s c0357s, V0 v02, ViewPropertyAnimator viewPropertyAnimator, View view) {
        this.f9191i = c0357s;
        this.f9189f = v02;
        this.h = viewPropertyAnimator;
        this.f9190g = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public void onAnimationCancel(Animator animator) {
        switch (this.f9188e) {
            case 1:
                this.f9190g.setAlpha(1.0f);
                break;
            default:
                super.onAnimationCancel(animator);
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.f9188e) {
            case 0:
                this.h.setListener(null);
                this.f9190g.setAlpha(1.0f);
                C0357s c0357s = this.f9191i;
                V0 v02 = this.f9189f;
                c0357s.c(v02);
                c0357s.f9264n.remove(v02);
                c0357s.i();
                int i5 = c0357s.f9265p;
                if ((i5 & 1) != 0) {
                    c0357s.f9265p = i5 & (-2);
                }
                break;
            default:
                this.h.setListener(null);
                C0357s c0357s2 = this.f9191i;
                V0 v03 = this.f9189f;
                c0357s2.c(v03);
                c0357s2.f9262l.remove(v03);
                c0357s2.i();
                int i7 = c0357s2.f9265p;
                if ((i7 & 8) != 0) {
                    c0357s2.f9265p = i7 & (-9);
                }
                int i9 = c0357s2.f9265p;
                if ((i9 & 16) != 0) {
                    c0357s2.f9265p = i9 & (-17);
                }
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        switch (this.f9188e) {
            case 0:
                this.f9191i.getClass();
                break;
            default:
                this.f9191i.getClass();
                break;
        }
    }

    public C0346m(C0357s c0357s, V0 v02, View view, ViewPropertyAnimator viewPropertyAnimator) {
        this.f9191i = c0357s;
        this.f9189f = v02;
        this.f9190g = view;
        this.h = viewPropertyAnimator;
    }
}
