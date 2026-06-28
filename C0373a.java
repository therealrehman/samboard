package androidx.transition;

import android.graphics.Path;
import d6.AbstractC0476d;

/* JADX INFO: renamed from: androidx.transition.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0373a extends H {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final float f9395d = (float) Math.tan(Math.toRadians(35.0d));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f9396a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f9397b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f9398c;

    public static float a(float f2) {
        if (f2 < 0.0f || f2 > 90.0f) {
            throw new IllegalArgumentException("Arc must be between 0 and 90 degrees");
        }
        return (float) Math.tan(Math.toRadians(f2 / 2.0f));
    }

    @Override // androidx.transition.H
    public final Path getPath(float f2, float f7, float f9, float f10) {
        float fU;
        float fU2;
        float f11;
        Path path = new Path();
        path.moveTo(f2, f7);
        float f12 = f9 - f2;
        float f13 = f10 - f7;
        float f14 = (f13 * f13) + (f12 * f12);
        float f15 = (f2 + f9) / 2.0f;
        float f16 = (f7 + f10) / 2.0f;
        float f17 = 0.25f * f14;
        boolean z9 = f7 > f10;
        if (Math.abs(f12) < Math.abs(f13)) {
            float fAbs = Math.abs(f14 / (f13 * 2.0f));
            if (z9) {
                fU2 = fAbs + f10;
                fU = f9;
            } else {
                fU2 = fAbs + f7;
                fU = f2;
            }
            f11 = this.f9397b;
        } else {
            float f18 = f14 / (f12 * 2.0f);
            if (z9) {
                fU2 = f7;
                fU = f18 + f2;
            } else {
                fU = f9 - f18;
                fU2 = f10;
            }
            f11 = this.f9396a;
        }
        float f19 = f17 * f11 * f11;
        float f20 = f15 - fU;
        float f21 = f16 - fU2;
        float f22 = (f21 * f21) + (f20 * f20);
        float f23 = this.f9398c;
        float f24 = f17 * f23 * f23;
        if (f22 >= f19) {
            f19 = f22 > f24 ? f24 : 0.0f;
        }
        if (f19 != 0.0f) {
            float fSqrt = (float) Math.sqrt(f19 / f22);
            fU = AbstractC0476d.u(fU, f15, fSqrt, f15);
            fU2 = AbstractC0476d.u(fU2, f16, fSqrt, f16);
        }
        path.cubicTo((f2 + fU) / 2.0f, (f7 + fU2) / 2.0f, (fU + f9) / 2.0f, (fU2 + f10) / 2.0f, f9, f10);
        return path;
    }
}
