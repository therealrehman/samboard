package androidx.transition;

import android.animation.Animator;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import com.samsung.android.keyscafe.R;

/* JADX INFO: renamed from: androidx.transition.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0391t extends l0 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final DecelerateInterpolator f9498f = new DecelerateInterpolator();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final AccelerateInterpolator f9499g = new AccelerateInterpolator();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f9500e;

    @Override // androidx.transition.l0, androidx.transition.U
    public final void captureEndValues(c0 c0Var) {
        super.captureEndValues(c0Var);
        h(c0Var);
    }

    @Override // androidx.transition.l0, androidx.transition.U
    public final void captureStartValues(c0 c0Var) {
        super.captureStartValues(c0Var);
        h(c0Var);
    }

    public final void g(View view, Rect rect, int[] iArr) {
        int iCenterX;
        int iCenterY;
        int[] iArr2 = this.f9500e;
        view.getLocationOnScreen(iArr2);
        int i5 = iArr2[0];
        int i7 = iArr2[1];
        Rect epicenter = getEpicenter();
        if (epicenter == null) {
            iCenterX = Math.round(view.getTranslationX()) + (view.getWidth() / 2) + i5;
            iCenterY = Math.round(view.getTranslationY()) + (view.getHeight() / 2) + i7;
        } else {
            iCenterX = epicenter.centerX();
            iCenterY = epicenter.centerY();
        }
        float fCenterX = rect.centerX() - iCenterX;
        float fCenterY = rect.centerY() - iCenterY;
        if (fCenterX == 0.0f && fCenterY == 0.0f) {
            fCenterX = ((float) (Math.random() * 2.0d)) - 1.0f;
            fCenterY = ((float) (Math.random() * 2.0d)) - 1.0f;
        }
        float fSqrt = (float) Math.sqrt((fCenterY * fCenterY) + (fCenterX * fCenterX));
        int i9 = iCenterX - i5;
        int i10 = iCenterY - i7;
        float fMax = Math.max(i9, view.getWidth() - i9);
        float fMax2 = Math.max(i10, view.getHeight() - i10);
        float fSqrt2 = (float) Math.sqrt((fMax2 * fMax2) + (fMax * fMax));
        iArr[0] = Math.round((fCenterX / fSqrt) * fSqrt2);
        iArr[1] = Math.round(fSqrt2 * (fCenterY / fSqrt));
    }

    public final void h(c0 c0Var) {
        View view = c0Var.f9408b;
        int[] iArr = this.f9500e;
        view.getLocationOnScreen(iArr);
        int i5 = iArr[0];
        int i7 = iArr[1];
        c0Var.f9407a.put("android:explode:screenBounds", new Rect(i5, i7, view.getWidth() + i5, view.getHeight() + i7));
    }

    @Override // androidx.transition.l0
    public final Animator onAppear(ViewGroup viewGroup, View view, c0 c0Var, c0 c0Var2) {
        if (c0Var2 == null) {
            return null;
        }
        Rect rect = (Rect) c0Var2.f9407a.get("android:explode:screenBounds");
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        g(viewGroup, rect, this.f9500e);
        return N.b(view, c0Var2, rect.left, rect.top, translationX + r0[0], translationY + r0[1], translationX, translationY, f9498f, this);
    }

    @Override // androidx.transition.l0
    public final Animator onDisappear(ViewGroup viewGroup, View view, c0 c0Var, c0 c0Var2) {
        float f2;
        float f7;
        if (c0Var == null) {
            return null;
        }
        Rect rect = (Rect) c0Var.f9407a.get("android:explode:screenBounds");
        int i5 = rect.left;
        int i7 = rect.top;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        int[] iArr = (int[]) c0Var.f9408b.getTag(R.id.transition_position);
        if (iArr != null) {
            f2 = (r7 - rect.left) + translationX;
            f7 = (r0 - rect.top) + translationY;
            rect.offsetTo(iArr[0], iArr[1]);
        } else {
            f2 = translationX;
            f7 = translationY;
        }
        g(viewGroup, rect, this.f9500e);
        return N.b(view, c0Var, i5, i7, translationX, translationY, f2 + r0[0], f7 + r0[1], f9499g, this);
    }
}
