package com.google.android.material.color.utilities;

/* JADX INFO: loaded from: classes.dex */
public class Blend {
    private Blend() {
    }

    public static int cam16Ucs(int i5, int i7, double d8) {
        Cam16 cam16FromInt = Cam16.fromInt(i5);
        Cam16 cam16FromInt2 = Cam16.fromInt(i7);
        double jstar = cam16FromInt.getJstar();
        double astar = cam16FromInt.getAstar();
        double bstar = cam16FromInt.getBstar();
        return Cam16.fromUcs(((cam16FromInt2.getJstar() - jstar) * d8) + jstar, ((cam16FromInt2.getAstar() - astar) * d8) + astar, ((cam16FromInt2.getBstar() - bstar) * d8) + bstar).toInt();
    }

    public static int harmonize(int i5, int i7) {
        Hct hctFromInt = Hct.fromInt(i5);
        Hct hctFromInt2 = Hct.fromInt(i7);
        double dMin = Math.min(MathUtils.differenceDegrees(hctFromInt.getHue(), hctFromInt2.getHue()) * 0.5d, 15.0d);
        return Hct.from(MathUtils.sanitizeDegreesDouble((MathUtils.rotationDirection(hctFromInt.getHue(), hctFromInt2.getHue()) * dMin) + hctFromInt.getHue()), hctFromInt.getChroma(), hctFromInt.getTone()).toInt();
    }

    public static int hctHue(int i5, int i7, double d8) {
        return Hct.from(Cam16.fromInt(cam16Ucs(i5, i7, d8)).getHue(), Cam16.fromInt(i5).getChroma(), ColorUtils.lstarFromArgb(i5)).toInt();
    }
}
