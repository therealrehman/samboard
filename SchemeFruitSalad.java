package com.google.android.material.color.utilities;

/* JADX INFO: loaded from: classes.dex */
public class SchemeFruitSalad extends DynamicScheme {
    public SchemeFruitSalad(Hct hct, boolean z9, double d8) {
        super(hct, Variant.FRUIT_SALAD, z9, d8, TonalPalette.fromHueAndChroma(MathUtils.sanitizeDegreesDouble(hct.getHue() - 50.0d), 48.0d), TonalPalette.fromHueAndChroma(MathUtils.sanitizeDegreesDouble(hct.getHue() - 50.0d), 36.0d), TonalPalette.fromHueAndChroma(hct.getHue(), 36.0d), TonalPalette.fromHueAndChroma(hct.getHue(), 10.0d), TonalPalette.fromHueAndChroma(hct.getHue(), 16.0d));
    }
}
