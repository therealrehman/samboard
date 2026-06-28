package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public final class j0 extends AnimatorListenerAdapter implements T {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final View f9453e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f9454f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ViewGroup f9455g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f9456i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f9457j = false;
    public final boolean h = true;

    public j0(View view, int i5) {
        this.f9453e = view;
        this.f9454f = i5;
        this.f9455g = (ViewGroup) view.getParent();
        a(true);
    }

    public final void a(boolean z9) {
        ViewGroup viewGroup;
        if (!this.h || this.f9456i == z9 || (viewGroup = this.f9455g) == null) {
            return;
        }
        this.f9456i = z9;
        viewGroup.suppressLayout(z9);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.f9457j = true;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        if (!this.f9457j) {
            C0376d c0376d = f0.f9429a;
            this.f9453e.setTransitionVisibility(this.f9454f);
            ViewGroup viewGroup = this.f9455g;
            if (viewGroup != null) {
                viewGroup.invalidate();
            }
        }
        a(false);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationPause(Animator animator) {
        if (this.f9457j) {
            return;
        }
        C0376d c0376d = f0.f9429a;
        this.f9453e.setTransitionVisibility(this.f9454f);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationResume(Animator animator) {
        if (this.f9457j) {
            return;
        }
        C0376d c0376d = f0.f9429a;
        this.f9453e.setTransitionVisibility(0);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
    }

    @Override // androidx.transition.T
    public final void onTransitionCancel(U u5) {
    }

    @Override // androidx.transition.T
    public final void onTransitionEnd(U u5) {
        if (!this.f9457j) {
            C0376d c0376d = f0.f9429a;
            this.f9453e.setTransitionVisibility(this.f9454f);
            ViewGroup viewGroup = this.f9455g;
            if (viewGroup != null) {
                viewGroup.invalidate();
            }
        }
        a(false);
        u5.removeListener(this);
    }

    @Override // androidx.transition.T
    public final void onTransitionPause(U u5) {
        a(false);
    }

    @Override // androidx.transition.T
    public final void onTransitionResume(U u5) {
        a(true);
    }

    @Override // androidx.transition.T
    public final void onTransitionStart(U u5) {
    }
}
