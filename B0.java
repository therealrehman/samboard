package androidx.transition;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;
import d6.AbstractC0476d;

/* JADX INFO: loaded from: classes.dex */
public final class b0 implements TypeEvaluator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float[] f9403a = new float[9];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float[] f9404b = new float[9];

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Matrix f9405c = new Matrix();

    @Override // android.animation.TypeEvaluator
    public final Object evaluate(float f2, Object obj, Object obj2) {
        float[] fArr = this.f9403a;
        ((Matrix) obj).getValues(fArr);
        float[] fArr2 = this.f9404b;
        ((Matrix) obj2).getValues(fArr2);
        for (int i5 = 0; i5 < 9; i5++) {
            float f7 = fArr2[i5];
            float f9 = fArr[i5];
            fArr2[i5] = AbstractC0476d.u(f7, f9, f2, f9);
        }
        Matrix matrix = this.f9405c;
        matrix.setValues(fArr2);
        return matrix;
    }
}
