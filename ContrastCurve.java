package com.google.android.material.color.utilities;

/* JADX INFO: loaded from: classes.dex */
public final class ContrastCurve {
    private final double high;
    private final double low;
    private final double medium;
    private final double normal;

    public ContrastCurve(double d8, double d10, double d11, double d12) {
        this.low = d8;
        this.normal = d10;
        this.medium = d11;
        this.high = d12;
    }

    public double getContrast(double d8) {
        return d8 <= -1.0d ? this.low : d8 < 0.0d ? MathUtils.lerp(this.low, this.normal, (d8 - (-1.0d)) / 1.0d) : d8 < 0.5d ? MathUtils.lerp(this.normal, this.medium, (d8 - 0.0d) / 0.5d) : d8 < 1.0d ? MathUtils.lerp(this.medium, this.high, (d8 - 0.5d) / 0.5d) : this.high;
    }
}
