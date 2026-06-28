package com.google.android.material.math;

/* JADX INFO: loaded from: classes.dex */
public final class MathUtils {
    public static final float DEFAULT_EPSILON = 1.0E-4f;

    private MathUtils() {
    }

    public static float dist(float f2, float f7, float f9, float f10) {
        return (float) Math.hypot(f9 - f2, f10 - f7);
    }

    public static float distanceToFurthestCorner(float f2, float f7, float f9, float f10, float f11, float f12) {
        return max(dist(f2, f7, f9, f10), dist(f2, f7, f11, f10), dist(f2, f7, f11, f12), dist(f2, f7, f9, f12));
    }

    public static float floorMod(float f2, int i5) {
        float f7 = i5;
        int i7 = (int) (f2 / f7);
        if (Math.signum(f2) * f7 < 0.0f && i7 * i5 != f2) {
            i7--;
        }
        return f2 - (i7 * i5);
    }

    public static boolean geq(float f2, float f7, float f9) {
        return f2 + f9 >= f7;
    }

    public static float lerp(float f2, float f7, float f9) {
        return (f9 * f7) + ((1.0f - f9) * f2);
    }

    private static float max(float f2, float f7, float f9, float f10) {
        return (f2 <= f7 || f2 <= f9 || f2 <= f10) ? (f7 <= f9 || f7 <= f10) ? f9 > f10 ? f9 : f10 : f7 : f2;
    }

    public static int floorMod(int i5, int i7) {
        int i9 = i5 / i7;
        if ((i5 ^ i7) < 0 && i9 * i7 != i5) {
            i9--;
        }
        return i5 - (i9 * i7);
    }
}
