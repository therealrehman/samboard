package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Matrix;
import android.view.View;
import com.samsung.android.keyscafe.R;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: androidx.transition.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0386n extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f9469e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Matrix f9470f = new Matrix();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ boolean f9471g;
    public final /* synthetic */ Matrix h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ View f9472i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ C0389q f9473j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ C0388p f9474k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r f9475l;

    public C0386n(r rVar, boolean z9, Matrix matrix, View view, C0389q c0389q, C0388p c0388p) {
        this.f9475l = rVar;
        this.f9471g = z9;
        this.h = matrix;
        this.f9472i = view;
        this.f9473j = c0389q;
        this.f9474k = c0388p;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.f9469e = true;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        boolean z9 = this.f9469e;
        C0389q c0389q = this.f9473j;
        View view = this.f9472i;
        if (!z9) {
            if (this.f9471g && this.f9475l.f9495e) {
                Matrix matrix = this.f9470f;
                matrix.set(this.h);
                view.setTag(R.id.transition_transform, matrix);
                view.setTranslationX(c0389q.f9485a);
                view.setTranslationY(c0389q.f9486b);
                WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
                androidx.core.view.M.w(view, c0389q.f9487c);
                view.setScaleX(c0389q.f9488d);
                view.setScaleY(c0389q.f9489e);
                view.setRotationX(c0389q.f9490f);
                view.setRotationY(c0389q.f9491g);
                view.setRotation(c0389q.h);
            } else {
                view.setTag(R.id.transition_transform, null);
                view.setTag(R.id.parent_matrix, null);
            }
        }
        C0376d c0376d = f0.f9429a;
        view.setAnimationMatrix(null);
        view.setTranslationX(c0389q.f9485a);
        view.setTranslationY(c0389q.f9486b);
        WeakHashMap weakHashMap2 = androidx.core.view.W.f7199a;
        androidx.core.view.M.w(view, c0389q.f9487c);
        view.setScaleX(c0389q.f9488d);
        view.setScaleY(c0389q.f9489e);
        view.setRotationX(c0389q.f9490f);
        view.setRotationY(c0389q.f9491g);
        view.setRotation(c0389q.h);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationPause(Animator animator) {
        Matrix matrix = this.f9474k.f9480a;
        Matrix matrix2 = this.f9470f;
        matrix2.set(matrix);
        View view = this.f9472i;
        view.setTag(R.id.transition_transform, matrix2);
        C0389q c0389q = this.f9473j;
        view.setTranslationX(c0389q.f9485a);
        view.setTranslationY(c0389q.f9486b);
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        androidx.core.view.M.w(view, c0389q.f9487c);
        view.setScaleX(c0389q.f9488d);
        view.setScaleY(c0389q.f9489e);
        view.setRotationX(c0389q.f9490f);
        view.setRotationY(c0389q.f9491g);
        view.setRotation(c0389q.h);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationResume(Animator animator) {
        View view = this.f9472i;
        view.setTranslationX(0.0f);
        view.setTranslationY(0.0f);
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        androidx.core.view.M.w(view, 0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setRotation(0.0f);
    }
}
