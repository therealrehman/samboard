package com.google.android.material.color.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public final class DynamicColor {
    public final Function<DynamicScheme, DynamicColor> background;
    public final ContrastCurve contrastCurve;
    private final HashMap<DynamicScheme, Hct> hctCache;
    public final boolean isBackground;
    public final String name;
    public final Function<DynamicScheme, Double> opacity;
    public final Function<DynamicScheme, TonalPalette> palette;
    public final Function<DynamicScheme, DynamicColor> secondBackground;
    public final Function<DynamicScheme, Double> tone;
    public final Function<DynamicScheme, ToneDeltaPair> toneDeltaPair;

    public DynamicColor(String str, Function<DynamicScheme, TonalPalette> function, Function<DynamicScheme, Double> function2, boolean z9, Function<DynamicScheme, DynamicColor> function3, Function<DynamicScheme, DynamicColor> function4, ContrastCurve contrastCurve, Function<DynamicScheme, ToneDeltaPair> function5) {
        this.hctCache = new HashMap<>();
        this.name = str;
        this.palette = function;
        this.tone = function2;
        this.isBackground = z9;
        this.background = function3;
        this.secondBackground = function4;
        this.contrastCurve = contrastCurve;
        this.toneDeltaPair = function5;
        this.opacity = null;
    }

    public static double enableLightForeground(double d8) {
        if (!tonePrefersLightForeground(d8) || toneAllowsLightForeground(d8)) {
            return d8;
        }
        return 49.0d;
    }

    public static double foregroundTone(double d8, double d10) {
        double dLighterUnsafe = Contrast.lighterUnsafe(d8, d10);
        double dDarkerUnsafe = Contrast.darkerUnsafe(d8, d10);
        double dRatioOfTones = Contrast.ratioOfTones(dLighterUnsafe, d8);
        double dRatioOfTones2 = Contrast.ratioOfTones(dDarkerUnsafe, d8);
        if (tonePrefersLightForeground(d8)) {
            return (dRatioOfTones >= d10 || dRatioOfTones >= dRatioOfTones2 || ((Math.abs(dRatioOfTones - dRatioOfTones2) > 0.1d ? 1 : (Math.abs(dRatioOfTones - dRatioOfTones2) == 0.1d ? 0 : -1)) < 0 && (dRatioOfTones > d10 ? 1 : (dRatioOfTones == d10 ? 0 : -1)) < 0 && (dRatioOfTones2 > d10 ? 1 : (dRatioOfTones2 == d10 ? 0 : -1)) < 0)) ? dLighterUnsafe : dDarkerUnsafe;
        }
        return (dRatioOfTones2 >= d10 || dRatioOfTones2 >= dRatioOfTones) ? dDarkerUnsafe : dLighterUnsafe;
    }

    public static DynamicColor fromArgb(String str, int i5) {
        return fromPalette(str, new a(0, TonalPalette.fromInt(i5)), new a(1, Hct.fromInt(i5)));
    }

    public static DynamicColor fromPalette(String str, Function<DynamicScheme, TonalPalette> function, Function<DynamicScheme, Double> function2) {
        return new DynamicColor(str, function, function2, false, null, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ TonalPalette lambda$fromArgb$0(TonalPalette tonalPalette, DynamicScheme dynamicScheme) {
        return tonalPalette;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Double lambda$fromArgb$1(Hct hct, DynamicScheme dynamicScheme) {
        return Double.valueOf(hct.getTone());
    }

    public static boolean toneAllowsLightForeground(double d8) {
        return Math.round(d8) <= 49;
    }

    public static boolean tonePrefersLightForeground(double d8) {
        return Math.round(d8) < 60;
    }

    public int getArgb(DynamicScheme dynamicScheme) {
        int i5 = getHct(dynamicScheme).toInt();
        Function<DynamicScheme, Double> function = this.opacity;
        if (function == null) {
            return i5;
        }
        return (MathUtils.clampInt(0, 255, (int) Math.round(function.apply(dynamicScheme).doubleValue() * 255.0d)) << 24) | (16777215 & i5);
    }

    public Hct getHct(DynamicScheme dynamicScheme) {
        Hct hct = this.hctCache.get(dynamicScheme);
        if (hct != null) {
            return hct;
        }
        Hct hct2 = this.palette.apply(dynamicScheme).getHct(getTone(dynamicScheme));
        if (this.hctCache.size() > 4) {
            this.hctCache.clear();
        }
        this.hctCache.put(dynamicScheme, hct2);
        return hct2;
    }

    public double getTone(DynamicScheme dynamicScheme) {
        double dMax;
        double dMin;
        boolean z9 = dynamicScheme.contrastLevel < 0.0d;
        Function<DynamicScheme, ToneDeltaPair> function = this.toneDeltaPair;
        if (function == null) {
            double dDoubleValue = this.tone.apply(dynamicScheme).doubleValue();
            Function<DynamicScheme, DynamicColor> function2 = this.background;
            if (function2 == null) {
                return dDoubleValue;
            }
            double tone = function2.apply(dynamicScheme).getTone(dynamicScheme);
            double contrast = this.contrastCurve.getContrast(dynamicScheme.contrastLevel);
            if (Contrast.ratioOfTones(tone, dDoubleValue) < contrast) {
                dDoubleValue = foregroundTone(tone, contrast);
            }
            if (z9) {
                dDoubleValue = foregroundTone(tone, contrast);
            }
            double d8 = (!this.isBackground || 50.0d > dDoubleValue || dDoubleValue >= 60.0d) ? dDoubleValue : Contrast.ratioOfTones(49.0d, tone) >= contrast ? 49.0d : 60.0d;
            if (this.secondBackground == null) {
                return d8;
            }
            double tone2 = this.background.apply(dynamicScheme).getTone(dynamicScheme);
            double tone3 = this.secondBackground.apply(dynamicScheme).getTone(dynamicScheme);
            double dMax2 = Math.max(tone2, tone3);
            double dMin2 = Math.min(tone2, tone3);
            if (Contrast.ratioOfTones(dMax2, d8) >= contrast && Contrast.ratioOfTones(dMin2, d8) >= contrast) {
                return d8;
            }
            double dLighter = Contrast.lighter(dMax2, contrast);
            double dDarker = Contrast.darker(dMin2, contrast);
            ArrayList arrayList = new ArrayList();
            if (dLighter != -1.0d) {
                arrayList.add(Double.valueOf(dLighter));
            }
            if (dDarker != -1.0d) {
                arrayList.add(Double.valueOf(dDarker));
            }
            if (tonePrefersLightForeground(tone2) || tonePrefersLightForeground(tone3)) {
                if (dLighter == -1.0d) {
                    return 100.0d;
                }
                return dLighter;
            }
            if (arrayList.size() == 1) {
                return ((Double) arrayList.get(0)).doubleValue();
            }
            if (dDarker == -1.0d) {
                return 0.0d;
            }
            return dDarker;
        }
        ToneDeltaPair toneDeltaPairApply = function.apply(dynamicScheme);
        DynamicColor roleA = toneDeltaPairApply.getRoleA();
        DynamicColor roleB = toneDeltaPairApply.getRoleB();
        double delta = toneDeltaPairApply.getDelta();
        TonePolarity polarity = toneDeltaPairApply.getPolarity();
        boolean stayTogether = toneDeltaPairApply.getStayTogether();
        double tone4 = this.background.apply(dynamicScheme).getTone(dynamicScheme);
        boolean z10 = polarity == TonePolarity.NEARER || (polarity == TonePolarity.LIGHTER && !dynamicScheme.isDark) || (polarity == TonePolarity.DARKER && dynamicScheme.isDark);
        DynamicColor dynamicColor = z10 ? roleA : roleB;
        DynamicColor dynamicColor2 = z10 ? roleB : roleA;
        boolean zEquals = this.name.equals(dynamicColor.name);
        double d10 = dynamicScheme.isDark ? 1.0d : -1.0d;
        double contrast2 = dynamicColor.contrastCurve.getContrast(dynamicScheme.contrastLevel);
        double contrast3 = dynamicColor2.contrastCurve.getContrast(dynamicScheme.contrastLevel);
        double dDoubleValue2 = dynamicColor.tone.apply(dynamicScheme).doubleValue();
        if (Contrast.ratioOfTones(tone4, dDoubleValue2) < contrast2) {
            dDoubleValue2 = foregroundTone(tone4, contrast2);
        }
        double dDoubleValue3 = dynamicColor2.tone.apply(dynamicScheme).doubleValue();
        if (Contrast.ratioOfTones(tone4, dDoubleValue3) < contrast3) {
            dDoubleValue3 = foregroundTone(tone4, contrast3);
        }
        if (z9) {
            dDoubleValue2 = foregroundTone(tone4, contrast2);
            dDoubleValue3 = foregroundTone(tone4, contrast3);
        }
        if ((dDoubleValue3 - dDoubleValue2) * d10 < delta) {
            double d11 = delta * d10;
            dDoubleValue3 = MathUtils.clampDouble(0.0d, 100.0d, dDoubleValue2 + d11);
            if ((dDoubleValue3 - dDoubleValue2) * d10 < delta) {
                dDoubleValue2 = MathUtils.clampDouble(0.0d, 100.0d, dDoubleValue3 - d11);
            }
        }
        if (50.0d > dDoubleValue2 || dDoubleValue2 >= 60.0d) {
            if (50.0d > dDoubleValue3 || dDoubleValue3 >= 60.0d) {
                dMax = dDoubleValue3;
            } else if (!stayTogether) {
                dMax = d10 > 0.0d ? 60.0d : 49.0d;
            } else if (d10 > 0.0d) {
                dMax = Math.max(dDoubleValue3, (delta * d10) + 60.0d);
                dDoubleValue2 = 60.0d;
            } else {
                dMin = Math.min(dDoubleValue3, (delta * d10) + 49.0d);
                dMax = dMin;
                dDoubleValue2 = 49.0d;
            }
        } else if (d10 > 0.0d) {
            dMax = Math.max(dDoubleValue3, (delta * d10) + 60.0d);
            dDoubleValue2 = 60.0d;
        } else {
            dMin = Math.min(dDoubleValue3, (delta * d10) + 49.0d);
            dMax = dMin;
            dDoubleValue2 = 49.0d;
        }
        return zEquals ? dDoubleValue2 : dMax;
    }

    public static DynamicColor fromPalette(String str, Function<DynamicScheme, TonalPalette> function, Function<DynamicScheme, Double> function2, boolean z9) {
        return new DynamicColor(str, function, function2, z9, null, null, null, null);
    }

    public DynamicColor(String str, Function<DynamicScheme, TonalPalette> function, Function<DynamicScheme, Double> function2, boolean z9, Function<DynamicScheme, DynamicColor> function3, Function<DynamicScheme, DynamicColor> function4, ContrastCurve contrastCurve, Function<DynamicScheme, ToneDeltaPair> function5, Function<DynamicScheme, Double> function6) {
        this.hctCache = new HashMap<>();
        this.name = str;
        this.palette = function;
        this.tone = function2;
        this.isBackground = z9;
        this.background = function3;
        this.secondBackground = function4;
        this.contrastCurve = contrastCurve;
        this.toneDeltaPair = function5;
        this.opacity = function6;
    }
}
