package com.google.android.material.color.utilities;

/* JADX INFO: loaded from: classes.dex */
public final class ViewingConditions {
    public static final ViewingConditions DEFAULT = defaultWithBackgroundLstar(50.0d);
    private final double aw;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final double f10067c;
    private final double fl;
    private final double flRoot;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private final double f10068n;
    private final double nbb;
    private final double nc;
    private final double ncb;
    private final double[] rgbD;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    private final double f10069z;

    private ViewingConditions(double d8, double d10, double d11, double d12, double d13, double d14, double[] dArr, double d15, double d16, double d17) {
        this.f10068n = d8;
        this.aw = d10;
        this.nbb = d11;
        this.ncb = d12;
        this.f10067c = d13;
        this.nc = d14;
        this.rgbD = dArr;
        this.fl = d15;
        this.flRoot = d16;
        this.f10069z = d17;
    }

    public static ViewingConditions defaultWithBackgroundLstar(double d8) {
        return make(ColorUtils.whitePointD65(), (ColorUtils.yFromLstar(50.0d) * 63.66197723675813d) / 100.0d, d8, 2.0d, false);
    }

    public static ViewingConditions make(double[] dArr, double d8, double d10, double d11, boolean z9) {
        double dMax = Math.max(0.1d, d10);
        double[][] dArr2 = Cam16.XYZ_TO_CAM16RGB;
        double d12 = dArr[0];
        double[] dArr3 = dArr2[0];
        double d13 = dArr3[0] * d12;
        double d14 = dArr[1];
        double d15 = (dArr3[1] * d14) + d13;
        double d16 = dArr[2];
        double d17 = (dArr3[2] * d16) + d15;
        double[] dArr4 = dArr2[1];
        double d18 = (dArr4[2] * d16) + (dArr4[1] * d14) + (dArr4[0] * d12);
        double[] dArr5 = dArr2[2];
        double d19 = (d16 * dArr5[2]) + (d14 * dArr5[1]) + (d12 * dArr5[0]);
        double d20 = (d11 / 10.0d) + 0.8d;
        double dLerp = d20 >= 0.9d ? MathUtils.lerp(0.59d, 0.69d, (d20 - 0.9d) * 10.0d) : MathUtils.lerp(0.525d, 0.59d, (d20 - 0.8d) * 10.0d);
        double dClampDouble = MathUtils.clampDouble(0.0d, 1.0d, z9 ? 1.0d : (1.0d - (Math.exp(((-d8) - 42.0d) / 92.0d) * 0.2777777777777778d)) * d20);
        double[] dArr6 = {(((100.0d / d17) * dClampDouble) + 1.0d) - dClampDouble, (((100.0d / d18) * dClampDouble) + 1.0d) - dClampDouble, (((100.0d / d19) * dClampDouble) + 1.0d) - dClampDouble};
        double d21 = 5.0d * d8;
        double d22 = 1.0d / (d21 + 1.0d);
        double d23 = d22 * d22 * d22 * d22;
        double d24 = 1.0d - d23;
        double dCbrt = (Math.cbrt(d21) * 0.1d * d24 * d24) + (d23 * d8);
        double dYFromLstar = ColorUtils.yFromLstar(dMax) / dArr[1];
        double dSqrt = Math.sqrt(dYFromLstar) + 1.48d;
        double dPow = 0.725d / Math.pow(dYFromLstar, 0.2d);
        double[] dArr7 = {Math.pow(((dArr6[0] * dCbrt) * d17) / 100.0d, 0.42d), Math.pow(((dArr6[1] * dCbrt) * d18) / 100.0d, 0.42d), Math.pow(((dArr6[2] * dCbrt) * d19) / 100.0d, 0.42d)};
        double d25 = dArr7[0];
        double d26 = (d25 * 400.0d) / (d25 + 27.13d);
        double d27 = dArr7[1];
        double d28 = (d27 * 400.0d) / (d27 + 27.13d);
        double d29 = dArr7[2];
        double[] dArr8 = {d26, d28, (400.0d * d29) / (d29 + 27.13d)};
        return new ViewingConditions(dYFromLstar, ((dArr8[2] * 0.05d) + (dArr8[0] * 2.0d) + dArr8[1]) * dPow, dPow, dPow, dLerp, d20, dArr6, dCbrt, Math.pow(dCbrt, 0.25d), dSqrt);
    }

    public double getAw() {
        return this.aw;
    }

    public double getC() {
        return this.f10067c;
    }

    public double getFl() {
        return this.fl;
    }

    public double getFlRoot() {
        return this.flRoot;
    }

    public double getN() {
        return this.f10068n;
    }

    public double getNbb() {
        return this.nbb;
    }

    public double getNc() {
        return this.nc;
    }

    public double getNcb() {
        return this.ncb;
    }

    public double[] getRgbD() {
        return this.rgbD;
    }

    public double getZ() {
        return this.f10069z;
    }
}
