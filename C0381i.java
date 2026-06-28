package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeConverter;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashMap;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: androidx.transition.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0381i extends U {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String[] f9440f = {"android:changeBounds:bounds", "android:changeBounds:clip", "android:changeBounds:parent", "android:changeBounds:windowX", "android:changeBounds:windowY"};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C0376d f9441g;
    public static final C0376d h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final C0376d f9442i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final C0376d f9443j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final C0376d f9444k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final C0394w f9445l;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f9446e = false;

    static {
        new C0375c(PointF.class, "boundsOrigin").f9406a = new Rect();
        f9441g = new C0376d(0, PointF.class, "topLeft");
        h = new C0376d(1, PointF.class, "bottomRight");
        f9442i = new C0376d(2, PointF.class, "bottomRight");
        f9443j = new C0376d(3, PointF.class, "topLeft");
        f9444k = new C0376d(4, PointF.class, "position");
        f9445l = new C0394w(1);
    }

    @Override // androidx.transition.U
    public final void captureEndValues(c0 c0Var) {
        captureValues(c0Var);
    }

    @Override // androidx.transition.U
    public final void captureStartValues(c0 c0Var) {
        captureValues(c0Var);
    }

    public final void captureValues(c0 c0Var) {
        View view = c0Var.f9408b;
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        if (!view.isLaidOut() && view.getWidth() == 0 && view.getHeight() == 0) {
            return;
        }
        HashMap map = c0Var.f9407a;
        map.put("android:changeBounds:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
        map.put("android:changeBounds:parent", c0Var.f9408b.getParent());
        if (this.f9446e) {
            map.put("android:changeBounds:clip", view.getClipBounds());
        }
    }

    @Override // androidx.transition.U
    public final Animator createAnimator(ViewGroup viewGroup, c0 c0Var, c0 c0Var2) {
        int i5;
        View view;
        ObjectAnimator objectAnimatorOfObject;
        int i7;
        ObjectAnimator objectAnimatorOfObject2;
        boolean z9;
        Animator animator;
        Animator animator2;
        if (c0Var == null || c0Var2 == null) {
            return null;
        }
        HashMap map = c0Var.f9407a;
        HashMap map2 = c0Var2.f9407a;
        ViewGroup viewGroup2 = (ViewGroup) map.get("android:changeBounds:parent");
        ViewGroup viewGroup3 = (ViewGroup) map2.get("android:changeBounds:parent");
        if (viewGroup2 == null || viewGroup3 == null) {
            return null;
        }
        View view2 = c0Var2.f9408b;
        Rect rect = (Rect) map.get("android:changeBounds:bounds");
        Rect rect2 = (Rect) map2.get("android:changeBounds:bounds");
        int i9 = rect.left;
        int i10 = rect2.left;
        int i11 = rect.top;
        int i12 = rect2.top;
        int i13 = rect.right;
        int i14 = rect2.right;
        int i15 = rect.bottom;
        int i16 = rect2.bottom;
        int i17 = i13 - i9;
        int i18 = i15 - i11;
        int i19 = i14 - i10;
        int i20 = i16 - i12;
        Rect rect3 = (Rect) map.get("android:changeBounds:clip");
        Rect rect4 = (Rect) map2.get("android:changeBounds:clip");
        if ((i17 == 0 || i18 == 0) && (i19 == 0 || i20 == 0)) {
            i5 = 0;
        } else {
            i5 = (i9 == i10 && i11 == i12) ? 0 : 1;
            if (i13 != i14 || i15 != i16) {
                i5++;
            }
        }
        if ((rect3 != null && !rect3.equals(rect4)) || (rect3 == null && rect4 != null)) {
            i5++;
        }
        if (i5 <= 0) {
            return null;
        }
        boolean z10 = this.f9446e;
        C0376d c0376d = f9444k;
        if (z10) {
            view = view2;
            int iMax = Math.max(i17, i19);
            int iMax2 = Math.max(i18, i20) + i11;
            C0376d c0376d2 = f0.f9429a;
            view.setLeftTopRightBottom(i9, i11, iMax + i9, iMax2);
            objectAnimatorOfObject = (i9 == i10 && i11 == i12) ? null : ObjectAnimator.ofObject(view, c0376d, (TypeConverter) null, getPathMotion().getPath(i9, i11, i10, i12));
            if (rect3 == null) {
                i7 = 0;
                rect3 = new Rect(0, 0, i17, i18);
            } else {
                i7 = 0;
            }
            Rect rect5 = rect4 == null ? new Rect(i7, i7, i19, i20) : rect4;
            if (rect3.equals(rect5)) {
                objectAnimatorOfObject2 = null;
            } else {
                WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
                view.setClipBounds(rect3);
                objectAnimatorOfObject2 = ObjectAnimator.ofObject(view, "clipBounds", f9445l, rect3, rect5);
                objectAnimatorOfObject2.addListener(new C0378f(view, rect4, i10, i12, i14, i16));
            }
            if (objectAnimatorOfObject == null) {
                animator = objectAnimatorOfObject2;
                z9 = true;
                animator2 = animator;
            } else {
                if (objectAnimatorOfObject2 != null) {
                    AnimatorSet animatorSet = new AnimatorSet();
                    z9 = true;
                    animatorSet.playTogether(objectAnimatorOfObject, objectAnimatorOfObject2);
                    animator2 = animatorSet;
                }
                animator = objectAnimatorOfObject;
                z9 = true;
                animator2 = animator;
            }
        } else {
            C0376d c0376d3 = f0.f9429a;
            view = view2;
            view.setLeftTopRightBottom(i9, i11, i13, i15);
            if (i5 != 2) {
                objectAnimatorOfObject = (i9 == i10 && i11 == i12) ? ObjectAnimator.ofObject(view, f9442i, (TypeConverter) null, getPathMotion().getPath(i13, i15, i14, i16)) : ObjectAnimator.ofObject(view, f9443j, (TypeConverter) null, getPathMotion().getPath(i9, i11, i10, i12));
            } else if (i17 == i19 && i18 == i20) {
                objectAnimatorOfObject = ObjectAnimator.ofObject(view, c0376d, (TypeConverter) null, getPathMotion().getPath(i9, i11, i10, i12));
            } else {
                C0380h c0380h = new C0380h();
                c0380h.f9437e = view;
                ObjectAnimator objectAnimatorOfObject3 = ObjectAnimator.ofObject(c0380h, f9441g, (TypeConverter) null, getPathMotion().getPath(i9, i11, i10, i12));
                ObjectAnimator objectAnimatorOfObject4 = ObjectAnimator.ofObject(c0380h, h, (TypeConverter) null, getPathMotion().getPath(i13, i15, i14, i16));
                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.playTogether(objectAnimatorOfObject3, objectAnimatorOfObject4);
                animatorSet2.addListener(new C0377e(c0380h));
                animator = animatorSet2;
                z9 = true;
                animator2 = animator;
            }
            animator = objectAnimatorOfObject;
            z9 = true;
            animator2 = animator;
        }
        if (view.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup4 = (ViewGroup) view.getParent();
            viewGroup4.suppressLayout(z9);
            addListener(new C0379g(viewGroup4));
        }
        return animator2;
    }

    @Override // androidx.transition.U
    public final String[] getTransitionProperties() {
        return f9440f;
    }
}
