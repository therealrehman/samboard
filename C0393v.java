package androidx.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashMap;

/* JADX INFO: renamed from: androidx.transition.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0393v extends l0 {
    public C0393v(int i5) {
        setMode(i5);
    }

    @Override // androidx.transition.l0, androidx.transition.U
    public final void captureStartValues(c0 c0Var) {
        super.captureStartValues(c0Var);
        HashMap map = c0Var.f9407a;
        View view = c0Var.f9408b;
        C0376d c0376d = f0.f9429a;
        map.put("android:fade:transitionAlpha", Float.valueOf(view.getTransitionAlpha()));
    }

    public final ObjectAnimator g(View view, float f2, float f7) {
        if (f2 == f7) {
            return null;
        }
        C0376d c0376d = f0.f9429a;
        view.setTransitionAlpha(f2);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, f0.f9429a, f7);
        objectAnimatorOfFloat.addListener(new androidx.recyclerview.widget.A(view));
        addListener(new C0392u(0, view));
        return objectAnimatorOfFloat;
    }

    @Override // androidx.transition.l0
    public final Animator onAppear(ViewGroup viewGroup, View view, c0 c0Var, c0 c0Var2) {
        Float f2;
        float fFloatValue = (c0Var == null || (f2 = (Float) c0Var.f9407a.get("android:fade:transitionAlpha")) == null) ? 0.0f : f2.floatValue();
        return g(view, fFloatValue != 1.0f ? fFloatValue : 0.0f, 1.0f);
    }

    @Override // androidx.transition.l0
    public final Animator onDisappear(ViewGroup viewGroup, View view, c0 c0Var, c0 c0Var2) {
        Float f2;
        C0376d c0376d = f0.f9429a;
        return g(view, (c0Var == null || (f2 = (Float) c0Var.f9407a.get("android:fade:transitionAlpha")) == null) ? 1.0f : f2.floatValue(), 0.0f);
    }
}
