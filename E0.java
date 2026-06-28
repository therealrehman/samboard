package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import com.samsung.android.keyscafe.R;

/* JADX INFO: loaded from: classes.dex */
public final class e0 extends AnimatorListenerAdapter implements T {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final View f9415e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final View f9416f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f9417g;
    public final int h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int[] f9418i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f9419j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f9420k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final float f9421l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final float f9422m;

    public e0(View view, View view2, int i5, int i7, float f2, float f7) {
        this.f9416f = view;
        this.f9415e = view2;
        this.f9417g = i5 - Math.round(view.getTranslationX());
        this.h = i7 - Math.round(view.getTranslationY());
        this.f9421l = f2;
        this.f9422m = f7;
        int[] iArr = (int[]) view2.getTag(R.id.transition_position);
        this.f9418i = iArr;
        if (iArr != null) {
            view2.setTag(R.id.transition_position, null);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        if (this.f9418i == null) {
            this.f9418i = new int[2];
        }
        int[] iArr = this.f9418i;
        float f2 = this.f9417g;
        View view = this.f9416f;
        iArr[0] = Math.round(view.getTranslationX() + f2);
        this.f9418i[1] = Math.round(view.getTranslationY() + this.h);
        this.f9415e.setTag(R.id.transition_position, this.f9418i);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationPause(Animator animator) {
        View view = this.f9416f;
        this.f9419j = view.getTranslationX();
        this.f9420k = view.getTranslationY();
        view.setTranslationX(this.f9421l);
        view.setTranslationY(this.f9422m);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationResume(Animator animator) {
        float f2 = this.f9419j;
        View view = this.f9416f;
        view.setTranslationX(f2);
        view.setTranslationY(this.f9420k);
    }

    @Override // androidx.transition.T
    public final void onTransitionCancel(U u5) {
    }

    @Override // androidx.transition.T
    public final void onTransitionEnd(U u5) {
        View view = this.f9416f;
        view.setTranslationX(this.f9421l);
        view.setTranslationY(this.f9422m);
        u5.removeListener(this);
    }

    @Override // androidx.transition.T
    public final void onTransitionPause(U u5) {
    }

    @Override // androidx.transition.T
    public final void onTransitionResume(U u5) {
    }

    @Override // androidx.transition.T
    public final void onTransitionStart(U u5) {
    }
}
