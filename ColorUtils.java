package com.google.android.material.color.utilities;

/* JADX INFO: loaded from: classes.dex */
public class ColorUtils {
    static final double[][] SRGB_TO_XYZ = {new double[]{0.41233895d, 0.35762064d, 0.18051042d}, new double[]{0.2126d, 0.7152d, 0.0722d}, new double[]{0.01932141d, 0.11916382d, 0.95034478d}};
    static final double[][] XYZ_TO_SRGB = {new double[]{3.2413774792388685d, -1.5376652402851851d, -0.49885366846268053d}, new double[]{-0.9691452513005321d, 1.8758853451067872d, 0.04156585616912061d}, new double[]{0.05562093689691305d, -0.20395524564742123d, 1.0571799111220335d}};
    static final double[] WHITE_POINT_D65 = {95.047d, 100.0d, 108.883d};

    private ColorUtils() {
    }

    public static int alphaFromArgb(int i5) {
        return (i5 >> 24) & 255;
    }

    public static int argbFromLab(double d8, double d10, double d11) {
        double[] dArr = WHITE_POINT_D65;
        double d12 = (d8 + 16.0d) / 116.0d;
        double d13 = d12 - (d11 / 200.0d);
        return argbFromXyz(labInvf((d10 / 500.0d) + d12) * dArr[0], labInvf(d12) * dArr[1], labInvf(d13) * dArr[2]);
    }

    public static int argbFromLinrgb(double[] dArr) {
        return argbFromRgb(delinearized(dArr[0]), delinearized(dArr[1]), delinearized(dArr[2]));
    }

    public static int argbFromLstar(double d8) {
        int iDelinearized = delinearized(yFromLstar(d8));
        return argbFromRgb(iDelinearized, iDelinearized, iDelinearized);
    }

    public static int argbFromRgb(int i5, int i7, int i9) {
        return ((i5 & 255) << 16) | (-16777216) | ((i7 & 255) << 8) | (i9 & 255);
    }

    public static int argbFromXyz(double d8, double d10, double d11) {
        double[][] dArr = XYZ_TO_SRGB;
        double[] dArr2 = dArr[0];
        double d12 = (dArr2[2] * d11) + (dArr2[1] * d10) + (dArr2[0] * d8);
        double[] dArr3 = dArr[1];
        double d13 = (dArr3[2] * d11) + (dArr3[1] * d10) + (dArr3[0] * d8);
        double[] dArr4 = dArr[2];
        return argbFromRgb(delinearized(d12), delinearized(d13), delinearized((dArr4[2] * d11) + (dArr4[1] * d10) + (dArr4[0] * d8)));
    }

    public static int blueFromArgb(int i5) {
        return i5 & 255;
    }

    public static int delinearized(double d8) {
        double d10 = d8 / 100.0d;
        return MathUtils.clampInt(0, 255, (int) Math.round((d10 <= 0.0031308d ? d10 * 12.92d : (Math.pow(d10, 0.4166666666666667d) * 1.055d) - 0.055d) * 255.0d));
    }

    public static int greenFromArgb(int i5) {
        return (i5 >> 8) & 255;
    }

    public static boolean isOpaque(int i5) {
        return alphaFromArgb(i5) >= 255;
    }

    public static double labF(double d8) {
        return d8 > 0.008856451679035631d ? Math.pow(d8, 0.3333333333333333d) : ((d8 * 903.2962962962963d) + 16.0d) / 116.0d;
    }

    public static double[] labFromArgb(int i5) {
        double dLinearized = linearized(redFromArgb(i5));
        double dLinearized2 = linearized(greenFromArgb(i5));
        double dLinearized3 = linearized(blueFromArgb(i5));
        double[][] dArr = SRGB_TO_XYZ;
        double[] dArr2 = dArr[0];
        double d8 = (dArr2[2] * dLinearized3) + (dArr2[1] * dLinearized2) + (dArr2[0] * dLinearized);
        double[] dArr3 = dArr[1];
        double d10 = (dArr3[2] * dLinearized3) + (dArr3[1] * dLinearized2) + (dArr3[0] * dLinearized);
        double[] dArr4 = dArr[2];
        double d11 = (dArr4[2] * dLinearized3) + (dArr4[1] * dLinearized2) + (dArr4[0] * dLinearized);
        double[] dArr5 = WHITE_POINT_D65;
        double d12 = d8 / dArr5[0];
        double d13 = d10 / dArr5[1];
        double d14 = d11 / dArr5[2];
        double dLabF = labF(d12);
        double dLabF2 = labF(d13);
        return new double[]{(116.0d * dLabF2) - 16.0d, (dLabF - dLabF2) * 500.0d, (dLabF2 - labF(d14)) * 200.0d};
    }

    public static double labInvf(double d8) {
        double d10 = d8 * d8 * d8;
        return d10 > 0.008856451679035631d ? d10 : ((d8 * 116.0d) - 16.0d) / 903.2962962962963d;
    }

    public static double linearized(int i5) {
        double d8 = ((double) i5) / 255.0d;
        return (d8 <= 0.040449936d ? d8 / 12.92d : Math.pow((d8 + 0.055d) / 1.055d, 2.4d)) * 100.0d;
    }

    public static double lstarFromArgb(int i5) {
        return (labF(xyzFromArgb(i5)[1] / 100.0d) * 116.0d) - 16.0d;
    }

    public static double lstarFromY(double d8) {
        return (labF(d8 / 100.0d) * 116.0d) - 16.0d;
    }

    public static int redFromArgb(int i5) {
        return (i5 >> 16) & 255;
    }

    public static double[] whitePointD65() {
        return WHITE_POINT_D65;
    }

    public static double[] xyzFromArgb(int i5) {
        return MathUtils.matrixMultiply(new double[]{linearized(redFromArgb(i5)), linearized(greenFromArgb(i5)), linearized(blueFromArgb(i5))}, SRGB_TO_XYZ);
    }

    public static double yFromLstar(double d8) {
        return labInvf((d8 + 16.0d) / 116.0d) * 100.0d;
    }
}
