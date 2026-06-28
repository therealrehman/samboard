package com.google.android.material.color.utilities;

/* JADX INFO: loaded from: classes.dex */
public final class Cam16 {
    private final double astar;
    private final double bstar;
    private final double chroma;
    private final double hue;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private final double f10053j;
    private final double jstar;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private final double f10054m;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private final double f10055q;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private final double f10056s;
    private final double[] tempArray = {0.0d, 0.0d, 0.0d};
    static final double[][] XYZ_TO_CAM16RGB = {new double[]{0.401288d, 0.650173d, -0.051461d}, new double[]{-0.250268d, 1.204414d, 0.045854d}, new double[]{-0.002079d, 0.048952d, 0.953127d}};
    static final double[][] CAM16RGB_TO_XYZ = {new double[]{1.8620678d, -1.0112547d, 0.14918678d}, new double[]{0.38752654d, 0.62144744d, -0.00897398d}, new double[]{-0.0158415d, -0.03412294d, 1.0499644d}};

    private Cam16(double d8, double d10, double d11, double d12, double d13, double d14, double d15, double d16, double d17) {
        this.hue = d8;
        this.chroma = d10;
        this.f10053j = d11;
        this.f10055q = d12;
        this.f10054m = d13;
        this.f10056s = d14;
        this.jstar = d15;
        this.astar = d16;
        this.bstar = d17;
    }

    public static Cam16 fromInt(int i5) {
        return fromIntInViewingConditions(i5, ViewingConditions.DEFAULT);
    }

    public static Cam16 fromIntInViewingConditions(int i5, ViewingConditions viewingConditions) {
        double dLinearized = ColorUtils.linearized((16711680 & i5) >> 16);
        double dLinearized2 = ColorUtils.linearized((65280 & i5) >> 8);
        double dLinearized3 = ColorUtils.linearized(i5 & 255);
        return fromXyzInViewingConditions((0.18051042d * dLinearized3) + (0.35762064d * dLinearized2) + (0.41233895d * dLinearized), (0.0722d * dLinearized3) + (0.7152d * dLinearized2) + (0.2126d * dLinearized), (dLinearized3 * 0.95034478d) + (dLinearized2 * 0.11916382d) + (dLinearized * 0.01932141d), viewingConditions);
    }

    public static Cam16 fromJch(double d8, double d10, double d11) {
        return fromJchInViewingConditions(d8, d10, d11, ViewingConditions.DEFAULT);
    }

    private static Cam16 fromJchInViewingConditions(double d8, double d10, double d11, ViewingConditions viewingConditions) {
        double d12 = d8 / 100.0d;
        double aw = (viewingConditions.getAw() + 4.0d) * Math.sqrt(d12) * (4.0d / viewingConditions.getC()) * viewingConditions.getFlRoot();
        double flRoot = viewingConditions.getFlRoot() * d10;
        double dSqrt = Math.sqrt((viewingConditions.getC() * (d10 / Math.sqrt(d12))) / (viewingConditions.getAw() + 4.0d)) * 50.0d;
        double radians = Math.toRadians(d11);
        double d13 = (1.7000000000000002d * d8) / ((0.007d * d8) + 1.0d);
        double dLog1p = 43.859649122807014d * Math.log1p(flRoot * 0.0228d);
        return new Cam16(d11, d10, d8, aw, flRoot, dSqrt, d13, Math.cos(radians) * dLog1p, Math.sin(radians) * dLog1p);
    }

    public static Cam16 fromUcs(double d8, double d10, double d11) {
        return fromUcsInViewingConditions(d8, d10, d11, ViewingConditions.DEFAULT);
    }

    public static Cam16 fromUcsInViewingConditions(double d8, double d10, double d11, ViewingConditions viewingConditions) {
        double dExpm1 = (Math.expm1(Math.hypot(d10, d11) * 0.0228d) / 0.0228d) / viewingConditions.getFlRoot();
        double dAtan2 = Math.atan2(d11, d10) * 57.29577951308232d;
        if (dAtan2 < 0.0d) {
            dAtan2 += 360.0d;
        }
        return fromJchInViewingConditions(d8 / (1.0d - ((d8 - 100.0d) * 0.007d)), dExpm1, dAtan2, viewingConditions);
    }

    public static Cam16 fromXyzInViewingConditions(double d8, double d10, double d11, ViewingConditions viewingConditions) {
        double[][] dArr = XYZ_TO_CAM16RGB;
        double[] dArr2 = dArr[0];
        double d12 = (dArr2[2] * d11) + (dArr2[1] * d10) + (dArr2[0] * d8);
        double[] dArr3 = dArr[1];
        double d13 = (dArr3[2] * d11) + (dArr3[1] * d10) + (dArr3[0] * d8);
        double[] dArr4 = dArr[2];
        double d14 = (dArr4[2] * d11) + (dArr4[1] * d10) + (dArr4[0] * d8);
        double d15 = viewingConditions.getRgbD()[0] * d12;
        double d16 = viewingConditions.getRgbD()[1] * d13;
        double d17 = viewingConditions.getRgbD()[2] * d14;
        double dPow = Math.pow((Math.abs(d15) * viewingConditions.getFl()) / 100.0d, 0.42d);
        double dPow2 = Math.pow((Math.abs(d16) * viewingConditions.getFl()) / 100.0d, 0.42d);
        double dPow3 = Math.pow((Math.abs(d17) * viewingConditions.getFl()) / 100.0d, 0.42d);
        double dSignum = ((Math.signum(d15) * 400.0d) * dPow) / (dPow + 27.13d);
        double dSignum2 = ((Math.signum(d16) * 400.0d) * dPow2) / (dPow2 + 27.13d);
        double dSignum3 = ((Math.signum(d17) * 400.0d) * dPow3) / (dPow3 + 27.13d);
        double d18 = ((((-12.0d) * dSignum2) + (dSignum * 11.0d)) + dSignum3) / 11.0d;
        double d19 = ((dSignum + dSignum2) - (dSignum3 * 2.0d)) / 9.0d;
        double d20 = dSignum2 * 20.0d;
        double d21 = ((21.0d * dSignum3) + ((dSignum * 20.0d) + d20)) / 20.0d;
        double d22 = (((dSignum * 40.0d) + d20) + dSignum3) / 20.0d;
        double degrees = Math.toDegrees(Math.atan2(d19, d18));
        if (degrees < 0.0d) {
            degrees += 360.0d;
        } else if (degrees >= 360.0d) {
            degrees -= 360.0d;
        }
        double d23 = degrees;
        double radians = Math.toRadians(d23);
        double dPow4 = Math.pow((viewingConditions.getNbb() * d22) / viewingConditions.getAw(), viewingConditions.getC() * viewingConditions.getZ()) * 100.0d;
        double d24 = dPow4 / 100.0d;
        double flRoot = viewingConditions.getFlRoot() * (viewingConditions.getAw() + 4.0d) * Math.sqrt(d24) * (4.0d / viewingConditions.getC());
        double dPow5 = Math.pow((Math.hypot(d18, d19) * (viewingConditions.getNcb() * (viewingConditions.getNc() * (((Math.cos(Math.toRadians(d23 < 20.14d ? d23 + 360.0d : d23) + 2.0d) + 3.8d) * 0.25d) * 3846.153846153846d)))) / (d21 + 0.305d), 0.9d) * Math.pow(1.64d - Math.pow(0.29d, viewingConditions.getN()), 0.73d);
        double dSqrt = Math.sqrt(d24) * dPow5;
        double flRoot2 = viewingConditions.getFlRoot() * dSqrt;
        double dSqrt2 = Math.sqrt((viewingConditions.getC() * dPow5) / (viewingConditions.getAw() + 4.0d)) * 50.0d;
        double d25 = (1.7000000000000002d * dPow4) / ((0.007d * dPow4) + 1.0d);
        double dLog1p = Math.log1p(flRoot2 * 0.0228d) * 43.859649122807014d;
        return new Cam16(d23, dSqrt, dPow4, flRoot, flRoot2, dSqrt2, d25, Math.cos(radians) * dLog1p, Math.sin(radians) * dLog1p);
    }

    public double distance(Cam16 cam16) {
        double jstar = getJstar() - cam16.getJstar();
        double astar = getAstar() - cam16.getAstar();
        double bstar = getBstar() - cam16.getBstar();
        return Math.pow(Math.sqrt((bstar * bstar) + (astar * astar) + (jstar * jstar)), 0.63d) * 1.41d;
    }

    public double getAstar() {
        return this.astar;
    }

    public double getBstar() {
        return this.bstar;
    }

    public double getChroma() {
        return this.chroma;
    }

    public double getHue() {
        return this.hue;
    }

    public double getJ() {
        return this.f10053j;
    }

    public double getJstar() {
        return this.jstar;
    }

    public double getM() {
        return this.f10054m;
    }

    public double getQ() {
        return this.f10055q;
    }

    public double getS() {
        return this.f10056s;
    }

    public int toInt() {
        return viewed(ViewingConditions.DEFAULT);
    }

    public int viewed(ViewingConditions viewingConditions) {
        double[] dArrXyzInViewingConditions = xyzInViewingConditions(viewingConditions, this.tempArray);
        return ColorUtils.argbFromXyz(dArrXyzInViewingConditions[0], dArrXyzInViewingConditions[1], dArrXyzInViewingConditions[2]);
    }

    public double[] xyzInViewingConditions(ViewingConditions viewingConditions, double[] dArr) {
        double dPow = Math.pow(((getChroma() == 0.0d || getJ() == 0.0d) ? 0.0d : getChroma() / Math.sqrt(getJ() / 100.0d)) / Math.pow(1.64d - Math.pow(0.29d, viewingConditions.getN()), 0.73d), 1.1111111111111112d);
        double radians = Math.toRadians(getHue());
        double dCos = (Math.cos(2.0d + radians) + 3.8d) * 0.25d;
        double dPow2 = Math.pow(getJ() / 100.0d, (1.0d / viewingConditions.getC()) / viewingConditions.getZ()) * viewingConditions.getAw();
        double ncb = viewingConditions.getNcb() * viewingConditions.getNc() * dCos * 3846.153846153846d;
        double nbb = dPow2 / viewingConditions.getNbb();
        double dSin = Math.sin(radians);
        double dCos2 = Math.cos(radians);
        double d8 = (((0.305d + nbb) * 23.0d) * dPow) / (((dPow * 108.0d) * dSin) + (((11.0d * dPow) * dCos2) + (ncb * 23.0d)));
        double d10 = dCos2 * d8;
        double d11 = d8 * dSin;
        double d12 = nbb * 460.0d;
        double d13 = ((288.0d * d11) + ((451.0d * d10) + d12)) / 1403.0d;
        double d14 = ((d12 - (891.0d * d10)) - (261.0d * d11)) / 1403.0d;
        double d15 = ((d12 - (d10 * 220.0d)) - (d11 * 6300.0d)) / 1403.0d;
        double dPow3 = Math.pow(Math.max(0.0d, (Math.abs(d13) * 27.13d) / (400.0d - Math.abs(d13))), 2.380952380952381d) * (100.0d / viewingConditions.getFl()) * Math.signum(d13);
        double dPow4 = Math.pow(Math.max(0.0d, (Math.abs(d14) * 27.13d) / (400.0d - Math.abs(d14))), 2.380952380952381d) * (100.0d / viewingConditions.getFl()) * Math.signum(d14);
        double dPow5 = Math.pow(Math.max(0.0d, (Math.abs(d15) * 27.13d) / (400.0d - Math.abs(d15))), 2.380952380952381d) * (100.0d / viewingConditions.getFl()) * Math.signum(d15);
        double d16 = dPow3 / viewingConditions.getRgbD()[0];
        double d17 = dPow4 / viewingConditions.getRgbD()[1];
        double d18 = dPow5 / viewingConditions.getRgbD()[2];
        double[][] dArr2 = CAM16RGB_TO_XYZ;
        double[] dArr3 = dArr2[0];
        double d19 = (dArr3[2] * d18) + (dArr3[1] * d17) + (dArr3[0] * d16);
        double[] dArr4 = dArr2[1];
        double d20 = (dArr4[2] * d18) + (dArr4[1] * d17) + (dArr4[0] * d16);
        double[] dArr5 = dArr2[2];
        double d21 = (d18 * dArr5[2]) + (d17 * dArr5[1]) + (d16 * dArr5[0]);
        if (dArr == null) {
            return new double[]{d19, d20, d21};
        }
        dArr[0] = d19;
        dArr[1] = d20;
        dArr[2] = d21;
        return dArr;
    }
}
