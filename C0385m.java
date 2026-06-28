package androidx.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.HashMap;

/* JADX INFO: renamed from: androidx.transition.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0385m extends U {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final String[] f9465e = {"android:changeImageTransform:matrix", "android:changeImageTransform:bounds"};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C0383k f9466f = new C0383k();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C0376d f9467g = new C0376d(5, Matrix.class, "animatedTransform");

    public static void e(c0 c0Var) {
        Matrix matrix;
        View view = c0Var.f9408b;
        if ((view instanceof ImageView) && view.getVisibility() == 0) {
            ImageView imageView = (ImageView) view;
            if (imageView.getDrawable() == null) {
                return;
            }
            HashMap map = c0Var.f9407a;
            map.put("android:changeImageTransform:bounds", new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()));
            Drawable drawable = imageView.getDrawable();
            if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
                matrix = new Matrix(imageView.getImageMatrix());
            } else {
                int i5 = AbstractC0384l.f9464a[imageView.getScaleType().ordinal()];
                if (i5 == 1) {
                    Drawable drawable2 = imageView.getDrawable();
                    Matrix matrix2 = new Matrix();
                    matrix2.postScale(imageView.getWidth() / drawable2.getIntrinsicWidth(), imageView.getHeight() / drawable2.getIntrinsicHeight());
                    matrix = matrix2;
                } else if (i5 != 2) {
                    matrix = new Matrix(imageView.getImageMatrix());
                } else {
                    Drawable drawable3 = imageView.getDrawable();
                    int intrinsicWidth = drawable3.getIntrinsicWidth();
                    float width = imageView.getWidth();
                    float f2 = intrinsicWidth;
                    int intrinsicHeight = drawable3.getIntrinsicHeight();
                    float height = imageView.getHeight();
                    float f7 = intrinsicHeight;
                    float fMax = Math.max(width / f2, height / f7);
                    int iRound = Math.round((width - (f2 * fMax)) / 2.0f);
                    int iRound2 = Math.round((height - (f7 * fMax)) / 2.0f);
                    Matrix matrix3 = new Matrix();
                    matrix3.postScale(fMax, fMax);
                    matrix3.postTranslate(iRound, iRound2);
                    matrix = matrix3;
                }
            }
            map.put("android:changeImageTransform:matrix", matrix);
        }
    }

    @Override // androidx.transition.U
    public final void captureEndValues(c0 c0Var) {
        e(c0Var);
    }

    @Override // androidx.transition.U
    public final void captureStartValues(c0 c0Var) {
        e(c0Var);
    }

    @Override // androidx.transition.U
    public final Animator createAnimator(ViewGroup viewGroup, c0 c0Var, c0 c0Var2) {
        if (c0Var != null && c0Var2 != null) {
            HashMap map = c0Var.f9407a;
            Rect rect = (Rect) map.get("android:changeImageTransform:bounds");
            HashMap map2 = c0Var2.f9407a;
            Rect rect2 = (Rect) map2.get("android:changeImageTransform:bounds");
            if (rect != null && rect2 != null) {
                Matrix matrix = (Matrix) map.get("android:changeImageTransform:matrix");
                Matrix matrix2 = (Matrix) map2.get("android:changeImageTransform:matrix");
                boolean z9 = (matrix == null && matrix2 == null) || (matrix != null && matrix.equals(matrix2));
                if (rect.equals(rect2) && z9) {
                    return null;
                }
                ImageView imageView = (ImageView) c0Var2.f9408b;
                Drawable drawable = imageView.getDrawable();
                int intrinsicWidth = drawable.getIntrinsicWidth();
                int intrinsicHeight = drawable.getIntrinsicHeight();
                C0376d c0376d = f9467g;
                if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
                    C0383k c0383k = f9466f;
                    F f2 = G.f9357a;
                    return ObjectAnimator.ofObject(imageView, c0376d, c0383k, f2, f2);
                }
                if (matrix == null) {
                    matrix = G.f9357a;
                }
                if (matrix2 == null) {
                    matrix2 = G.f9357a;
                }
                c0376d.getClass();
                imageView.animateTransform(matrix);
                return ObjectAnimator.ofObject(imageView, c0376d, new b0(), matrix, matrix2);
            }
        }
        return null;
    }

    @Override // androidx.transition.U
    public final String[] getTransitionProperties() {
        return f9465e;
    }
}
