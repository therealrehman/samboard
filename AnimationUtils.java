package com.google.android.material.animation;

import Z.a;
import Z.b;
import android.animation.TimeInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import d6.AbstractC0476d;

/* JADX INFO: loaded from: classes.dex */
public class AnimationUtils {
    public static final TimeInterpolator LINEAR_INTERPOLATOR = new LinearInterpolator();
    public static final TimeInterpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new b();
    public static final TimeInterpolator FAST_OUT_LINEAR_IN_INTERPOLATOR = new a(0);
    public static final TimeInterpolator LINEAR_OUT_SLOW_IN_INTERPOLATOR = new a(1);
    public static final TimeInterpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();

    public static float lerp(float f2, float f7, float f9) {
        return AbstractC0476d.u(f7, f2, f9, f2);
    }

    public static int lerp(int i5, int i7, float f2) {
        return Math.round(f2 * (i7 - i5)) + i5;
    }

    public static float lerp(float f2, float f7, float f9, float f10, float f11) {
        return f11 <= f9 ? f2 : f11 >= f10 ? f7 : lerp(f2, f7, (f11 - f9) / (f10 - f9));
    }
}
