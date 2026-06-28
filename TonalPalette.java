package com.google.android.material.color.utilities;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class TonalPalette {
    Map<Integer, Integer> cache = new HashMap();
    double chroma;
    double hue;
    Hct keyColor;

    private TonalPalette(double d8, double d10, Hct hct) {
        this.hue = d8;
        this.chroma = d10;
        this.keyColor = hct;
    }

    private static Hct createKeyColor(double d8, double d10) {
        Hct hctFrom = Hct.from(d8, d10, 50.0d);
        Hct hct = hctFrom;
        double dAbs = Math.abs(hctFrom.getChroma() - d10);
        for (double d11 = 1.0d; d11 < 50.0d && Math.round(d10) != Math.round(hct.getChroma()); d11 += 1.0d) {
            Hct hctFrom2 = Hct.from(d8, d10, 50.0d + d11);
            double dAbs2 = Math.abs(hctFrom2.getChroma() - d10);
            if (dAbs2 < dAbs) {
                hct = hctFrom2;
                dAbs = dAbs2;
            }
            Hct hctFrom3 = Hct.from(d8, d10, 50.0d - d11);
            double dAbs3 = Math.abs(hctFrom3.getChroma() - d10);
            if (dAbs3 < dAbs) {
                hct = hctFrom3;
                dAbs = dAbs3;
            }
        }
        return hct;
    }

    public static TonalPalette fromHct(Hct hct) {
        return new TonalPalette(hct.getHue(), hct.getChroma(), hct);
    }

    public static TonalPalette fromHueAndChroma(double d8, double d10) {
        return new TonalPalette(d8, d10, createKeyColor(d8, d10));
    }

    public static TonalPalette fromInt(int i5) {
        return fromHct(Hct.fromInt(i5));
    }

    public double getChroma() {
        return this.chroma;
    }

    public Hct getHct(double d8) {
        return Hct.from(this.hue, this.chroma, d8);
    }

    public double getHue() {
        return this.hue;
    }

    public Hct getKeyColor() {
        return this.keyColor;
    }

    public int tone(int i5) {
        Integer numValueOf = this.cache.get(Integer.valueOf(i5));
        if (numValueOf == null) {
            numValueOf = Integer.valueOf(Hct.from(this.hue, this.chroma, i5).toInt());
            this.cache.put(Integer.valueOf(i5), numValueOf);
        }
        return numValueOf.intValue();
    }
}
