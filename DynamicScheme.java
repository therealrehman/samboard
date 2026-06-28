package com.google.android.material.color.utilities;

/* JADX INFO: loaded from: classes.dex */
public class DynamicScheme {
    public final double contrastLevel;
    public final TonalPalette errorPalette = TonalPalette.fromHueAndChroma(25.0d, 84.0d);
    public final boolean isDark;
    public final TonalPalette neutralPalette;
    public final TonalPalette neutralVariantPalette;
    public final TonalPalette primaryPalette;
    public final TonalPalette secondaryPalette;
    public final int sourceColorArgb;
    public final Hct sourceColorHct;
    public final TonalPalette tertiaryPalette;
    public final Variant variant;

    public DynamicScheme(Hct hct, Variant variant, boolean z9, double d8, TonalPalette tonalPalette, TonalPalette tonalPalette2, TonalPalette tonalPalette3, TonalPalette tonalPalette4, TonalPalette tonalPalette5) {
        this.sourceColorArgb = hct.toInt();
        this.sourceColorHct = hct;
        this.variant = variant;
        this.isDark = z9;
        this.contrastLevel = d8;
        this.primaryPalette = tonalPalette;
        this.secondaryPalette = tonalPalette2;
        this.tertiaryPalette = tonalPalette3;
        this.neutralPalette = tonalPalette4;
        this.neutralVariantPalette = tonalPalette5;
    }

    public static double getRotatedHue(Hct hct, double[] dArr, double[] dArr2) {
        double hue = hct.getHue();
        int i5 = 0;
        if (dArr2.length == 1) {
            return MathUtils.sanitizeDegreesDouble(hue + dArr2[0]);
        }
        int length = dArr.length;
        while (i5 <= length - 2) {
            double d8 = dArr[i5];
            int i7 = i5 + 1;
            double d10 = dArr[i7];
            if (d8 < hue && hue < d10) {
                return MathUtils.sanitizeDegreesDouble(hue + dArr2[i5]);
            }
            i5 = i7;
        }
        return hue;
    }
}
