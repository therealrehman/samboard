package com.google.android.material.color.utilities;

/* JADX INFO: loaded from: classes.dex */
public final class Contrast {
    private static final double CONTRAST_RATIO_EPSILON = 0.04d;
    private static final double LUMINANCE_GAMUT_MAP_TOLERANCE = 0.4d;
    public static final double RATIO_30 = 3.0d;
    public static final double RATIO_45 = 4.5d;
    public static final double RATIO_70 = 7.0d;
    public static final double RATIO_MAX = 21.0d;
    public static final double RATIO_MIN = 1.0d;

    private Contrast() {
    }

    public static double darker(double d8, double d10) {
        if (d8 >= 0.0d && d8 <= 100.0d) {
            double dYFromLstar = ColorUtils.yFromLstar(d8);
            double d11 = ((dYFromLstar + 5.0d) / d10) - 5.0d;
            if (d11 >= 0.0d && d11 <= 100.0d) {
                double dRatioOfYs = ratioOfYs(dYFromLstar, d11);
                double dAbs = Math.abs(dRatioOfYs - d10);
                if (dRatioOfYs < d10 && dAbs > CONTRAST_RATIO_EPSILON) {
                    return -1.0d;
                }
                double dLstarFromY = ColorUtils.lstarFromY(d11) - LUMINANCE_GAMUT_MAP_TOLERANCE;
                if (dLstarFromY >= 0.0d && dLstarFromY <= 100.0d) {
                    return dLstarFromY;
                }
            }
        }
        return -1.0d;
    }

    public static double darkerUnsafe(double d8, double d10) {
        return Math.max(0.0d, darker(d8, d10));
    }

    public static double lighter(double d8, double d10) {
        if (d8 >= 0.0d && d8 <= 100.0d) {
            double dYFromLstar = ColorUtils.yFromLstar(d8);
            double d11 = ((dYFromLstar + 5.0d) * d10) - 5.0d;
            if (d11 >= 0.0d && d11 <= 100.0d) {
                double dRatioOfYs = ratioOfYs(d11, dYFromLstar);
                double dAbs = Math.abs(dRatioOfYs - d10);
                if (dRatioOfYs < d10 && dAbs > CONTRAST_RATIO_EPSILON) {
                    return -1.0d;
                }
                double dLstarFromY = ColorUtils.lstarFromY(d11) + LUMINANCE_GAMUT_MAP_TOLERANCE;
                if (dLstarFromY >= 0.0d && dLstarFromY <= 100.0d) {
                    return dLstarFromY;
                }
            }
        }
        return -1.0d;
    }

    public static double lighterUnsafe(double d8, double d10) {
        double dLighter = lighter(d8, d10);
        if (dLighter < 0.0d) {
            return 100.0d;
        }
        return dLighter;
    }

    public static double ratioOfTones(double d8, double d10) {
        return ratioOfYs(ColorUtils.yFromLstar(d8), ColorUtils.yFromLstar(d10));
    }

    public static double ratioOfYs(double d8, double d10) {
        double dMax = Math.max(d8, d10);
        if (dMax != d10) {
            d8 = d10;
        }
        return (dMax + 5.0d) / (d8 + 5.0d);
    }
}
