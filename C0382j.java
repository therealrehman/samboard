package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.C0138c;
import java.util.HashMap;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: androidx.transition.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0382j extends U {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String[] f9450f = {"android:clipBounds:clip"};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String[] f9451g = {"android:changeScroll:x", "android:changeScroll:y"};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f9452e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0382j(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet);
        this.f9452e = i5;
    }

    public static void e(c0 c0Var) {
        View view = c0Var.f9408b;
        if (view.getVisibility() == 8) {
            return;
        }
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        Rect clipBounds = view.getClipBounds();
        HashMap map = c0Var.f9407a;
        map.put("android:clipBounds:clip", clipBounds);
        if (clipBounds == null) {
            map.put("android:clipBounds:bounds", new Rect(0, 0, view.getWidth(), view.getHeight()));
        }
    }

    public static void f(c0 c0Var) {
        HashMap map = c0Var.f9407a;
        map.put("android:changeScroll:x", Integer.valueOf(c0Var.f9408b.getScrollX()));
        map.put("android:changeScroll:y", Integer.valueOf(c0Var.f9408b.getScrollY()));
    }

    @Override // androidx.transition.U
    public final void captureEndValues(c0 c0Var) {
        switch (this.f9452e) {
            case 0:
                e(c0Var);
                break;
            default:
                f(c0Var);
                break;
        }
    }

    @Override // androidx.transition.U
    public final void captureStartValues(c0 c0Var) {
        switch (this.f9452e) {
            case 0:
                e(c0Var);
                break;
            default:
                f(c0Var);
                break;
        }
    }

    @Override // androidx.transition.U
    public final Animator createAnimator(ViewGroup viewGroup, c0 c0Var, c0 c0Var2) {
        ObjectAnimator objectAnimatorOfInt;
        ObjectAnimator objectAnimatorOfObject = null;
        switch (this.f9452e) {
            case 0:
                if (c0Var != null && c0Var2 != null) {
                    HashMap map = c0Var.f9407a;
                    if (map.containsKey("android:clipBounds:clip")) {
                        HashMap map2 = c0Var2.f9407a;
                        if (map2.containsKey("android:clipBounds:clip")) {
                            Rect rect = (Rect) map.get("android:clipBounds:clip");
                            Rect rect2 = (Rect) map2.get("android:clipBounds:clip");
                            boolean z9 = rect2 == null;
                            if (rect != null || rect2 != null) {
                                if (rect == null) {
                                    rect = (Rect) map.get("android:clipBounds:bounds");
                                } else if (rect2 == null) {
                                    rect2 = (Rect) map2.get("android:clipBounds:bounds");
                                }
                                if (!rect.equals(rect2)) {
                                    View view = c0Var2.f9408b;
                                    WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
                                    view.setClipBounds(rect);
                                    Rect rect3 = new Rect();
                                    C0394w c0394w = new C0394w(1);
                                    c0394w.f9504b = rect3;
                                    objectAnimatorOfObject = ObjectAnimator.ofObject(c0Var2.f9408b, f0.f9430b, c0394w, rect, rect2);
                                    if (z9) {
                                        objectAnimatorOfObject.addListener(new C0138c(3, c0Var2.f9408b));
                                    }
                                }
                            }
                        }
                    }
                }
                return objectAnimatorOfObject;
            default:
                if (c0Var == null || c0Var2 == null) {
                    return null;
                }
                View view2 = c0Var2.f9408b;
                HashMap map3 = c0Var.f9407a;
                int iIntValue = ((Integer) map3.get("android:changeScroll:x")).intValue();
                HashMap map4 = c0Var2.f9407a;
                int iIntValue2 = ((Integer) map4.get("android:changeScroll:x")).intValue();
                int iIntValue3 = ((Integer) map3.get("android:changeScroll:y")).intValue();
                int iIntValue4 = ((Integer) map4.get("android:changeScroll:y")).intValue();
                if (iIntValue != iIntValue2) {
                    view2.setScrollX(iIntValue);
                    objectAnimatorOfInt = ObjectAnimator.ofInt(view2, "scrollX", iIntValue, iIntValue2);
                } else {
                    objectAnimatorOfInt = null;
                }
                if (iIntValue3 != iIntValue4) {
                    view2.setScrollY(iIntValue3);
                    objectAnimatorOfObject = ObjectAnimator.ofInt(view2, "scrollY", iIntValue3, iIntValue4);
                }
                if (objectAnimatorOfInt == null) {
                    return objectAnimatorOfObject;
                }
                if (objectAnimatorOfObject == null) {
                    return objectAnimatorOfInt;
                }
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(objectAnimatorOfInt, objectAnimatorOfObject);
                return animatorSet;
        }
    }

    @Override // androidx.transition.U
    public final String[] getTransitionProperties() {
        switch (this.f9452e) {
            case 0:
                return f9450f;
            default:
                return f9451g;
        }
    }
}
