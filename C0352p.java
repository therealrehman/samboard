package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* JADX INFO: renamed from: androidx.recyclerview.widget.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0352p extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f9207e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ C0354q f9208f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ ViewPropertyAnimator f9209g;
    public final /* synthetic */ View h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ C0357s f9210i;

    public /* synthetic */ C0352p(C0357s c0357s, C0354q c0354q, ViewPropertyAnimator viewPropertyAnimator, View view, int i5) {
        this.f9207e = i5;
        this.f9210i = c0357s;
        this.f9208f = c0354q;
        this.f9209g = viewPropertyAnimator;
        this.h = view;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.f9207e) {
            case 0:
                this.f9209g.setListener(null);
                View view = this.h;
                view.setAlpha(1.0f);
                view.setTranslationX(0.0f);
                view.setTranslationY(0.0f);
                C0354q c0354q = this.f9208f;
                V0 v02 = c0354q.f9241a;
                C0357s c0357s = this.f9210i;
                c0357s.c(v02);
                c0357s.o.remove(c0354q.f9241a);
                c0357s.i();
                int i5 = c0357s.f9265p;
                if ((i5 & 4) != 0) {
                    c0357s.f9265p = i5 & (-5);
                }
                break;
            default:
                this.f9209g.setListener(null);
                View view2 = this.h;
                view2.setAlpha(1.0f);
                view2.setTranslationX(0.0f);
                view2.setTranslationY(0.0f);
                C0354q c0354q2 = this.f9208f;
                V0 v03 = c0354q2.f9242b;
                C0357s c0357s2 = this.f9210i;
                c0357s2.c(v03);
                c0357s2.o.remove(c0354q2.f9242b);
                c0357s2.i();
                break;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        switch (this.f9207e) {
            case 0:
                V0 v02 = this.f9208f.f9241a;
                this.f9210i.getClass();
                break;
            default:
                V0 v03 = this.f9208f.f9242b;
                this.f9210i.getClass();
                break;
        }
    }
}
