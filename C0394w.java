package androidx.transition;

import android.animation.TypeEvaluator;
import android.graphics.Rect;
import d6.AbstractC0476d;

/* JADX INFO: renamed from: androidx.transition.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0394w implements TypeEvaluator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9503a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f9504b;

    @Override // android.animation.TypeEvaluator
    public final Object evaluate(float f2, Object obj, Object obj2) {
        switch (this.f9503a) {
            case 0:
                float[] fArr = (float[]) obj;
                float[] fArr2 = (float[]) obj2;
                float[] fArr3 = (float[]) this.f9504b;
                if (fArr3 == null) {
                    fArr3 = new float[fArr.length];
                }
                for (int i5 = 0; i5 < fArr3.length; i5++) {
                    float f7 = fArr[i5];
                    fArr3[i5] = AbstractC0476d.u(fArr2[i5], f7, f2, f7);
                }
                return fArr3;
            default:
                Rect rect = (Rect) obj;
                Rect rect2 = (Rect) obj2;
                int i7 = rect.left + ((int) ((rect2.left - r0) * f2));
                int i9 = rect.top + ((int) ((rect2.top - r1) * f2));
                int i10 = rect.right + ((int) ((rect2.right - r2) * f2));
                int i11 = rect.bottom + ((int) ((rect2.bottom - r6) * f2));
                Rect rect3 = (Rect) this.f9504b;
                if (rect3 == null) {
                    return new Rect(i7, i9, i10, i11);
                }
                rect3.set(i7, i9, i10, i11);
                return rect3;
        }
    }
}
