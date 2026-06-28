package androidx.transition;

import android.graphics.Matrix;
import android.view.View;

/* JADX INFO: renamed from: androidx.transition.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0388p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Matrix f9480a = new Matrix();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final View f9481b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float[] f9482c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f9483d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f9484e;

    public C0388p(View view, float[] fArr) {
        this.f9481b = view;
        float[] fArr2 = (float[]) fArr.clone();
        this.f9482c = fArr2;
        this.f9483d = fArr2[2];
        this.f9484e = fArr2[5];
        a();
    }

    public final void a() {
        float f2 = this.f9483d;
        float[] fArr = this.f9482c;
        fArr[2] = f2;
        fArr[5] = this.f9484e;
        Matrix matrix = this.f9480a;
        matrix.setValues(fArr);
        C0376d c0376d = f0.f9429a;
        this.f9481b.setAnimationMatrix(matrix);
    }
}
