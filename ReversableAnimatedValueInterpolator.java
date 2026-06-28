package com.google.android.material.internal;

import android.animation.TimeInterpolator;

/* JADX INFO: loaded from: classes.dex */
public class ReversableAnimatedValueInterpolator implements TimeInterpolator {
    private final TimeInterpolator sourceInterpolator;

    public ReversableAnimatedValueInterpolator(TimeInterpolator timeInterpolator) {
        this.sourceInterpolator = timeInterpolator;
    }

    public static TimeInterpolator of(boolean z9, TimeInterpolator timeInterpolator) {
        return z9 ? timeInterpolator : new ReversableAnimatedValueInterpolator(timeInterpolator);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        return 1.0f - this.sourceInterpolator.getInterpolation(f2);
    }
}
