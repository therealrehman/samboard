package com.google.android.material.color;

import B.b;
import D.d;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import com.google.android.material.color.utilities.Blend;
import com.google.android.material.color.utilities.Hct;
import com.google.android.material.resources.MaterialAttributes;
import com.samsung.android.keyscafe.R;
import p0.a;

/* JADX INFO: loaded from: classes.dex */
public class MaterialColors {
    public static final float ALPHA_DISABLED = 0.38f;
    public static final float ALPHA_DISABLED_LOW = 0.12f;
    public static final float ALPHA_FULL = 1.0f;
    public static final float ALPHA_LOW = 0.32f;
    public static final float ALPHA_MEDIUM = 0.54f;
    private static final int CHROMA_NEUTRAL = 6;
    private static final int TONE_ACCENT_CONTAINER_DARK = 30;
    private static final int TONE_ACCENT_CONTAINER_LIGHT = 90;
    private static final int TONE_ACCENT_DARK = 80;
    private static final int TONE_ACCENT_LIGHT = 40;
    private static final int TONE_ON_ACCENT_CONTAINER_DARK = 90;
    private static final int TONE_ON_ACCENT_CONTAINER_LIGHT = 10;
    private static final int TONE_ON_ACCENT_DARK = 20;
    private static final int TONE_ON_ACCENT_LIGHT = 100;
    private static final int TONE_SURFACE_CONTAINER_DARK = 12;
    private static final int TONE_SURFACE_CONTAINER_HIGH_DARK = 17;
    private static final int TONE_SURFACE_CONTAINER_HIGH_LIGHT = 92;
    private static final int TONE_SURFACE_CONTAINER_LIGHT = 94;

    private MaterialColors() {
    }

    public static int compositeARGBWithAlpha(int i5, int i7) {
        return d.e(i5, (Color.alpha(i5) * i7) / 255);
    }

    public static int getColor(View view, int i5) {
        return resolveColor(view.getContext(), MaterialAttributes.resolveTypedValueOrThrow(view, i5));
    }

    public static Integer getColorOrNull(Context context, int i5) {
        TypedValue typedValueResolve = MaterialAttributes.resolve(context, i5);
        if (typedValueResolve != null) {
            return Integer.valueOf(resolveColor(context, typedValueResolve));
        }
        return null;
    }

    private static int getColorRole(int i5, int i7) {
        Hct hctFromInt = Hct.fromInt(i5);
        hctFromInt.setTone(i7);
        return hctFromInt.toInt();
    }

    public static ColorRoles getColorRoles(Context context, int i5) {
        return getColorRoles(i5, isLightTheme(context));
    }

    public static ColorStateList getColorStateList(Context context, int i5, ColorStateList colorStateList) {
        TypedValue typedValueResolve = MaterialAttributes.resolve(context, i5);
        ColorStateList colorStateListResolveColorStateList = typedValueResolve != null ? resolveColorStateList(context, typedValueResolve) : null;
        return colorStateListResolveColorStateList == null ? colorStateList : colorStateListResolveColorStateList;
    }

    public static ColorStateList getColorStateListOrNull(Context context, int i5) {
        TypedValue typedValueResolve = MaterialAttributes.resolve(context, i5);
        if (typedValueResolve == null) {
            return null;
        }
        int i7 = typedValueResolve.resourceId;
        if (i7 != 0) {
            return a.f(context, i7);
        }
        int i9 = typedValueResolve.data;
        if (i9 != 0) {
            return ColorStateList.valueOf(i9);
        }
        return null;
    }

    public static int getSurfaceContainerFromSeed(Context context, int i5) {
        return getColorRole(i5, isLightTheme(context) ? 94 : 12, 6);
    }

    public static int getSurfaceContainerHighFromSeed(Context context, int i5) {
        return getColorRole(i5, isLightTheme(context) ? 92 : 17, 6);
    }

    public static int harmonize(int i5, int i7) {
        return Blend.harmonize(i5, i7);
    }

    public static int harmonizeWithPrimary(Context context, int i5) {
        return harmonize(i5, getColor(context, R.attr.colorPrimary, MaterialColors.class.getCanonicalName()));
    }

    public static boolean isColorLight(int i5) {
        boolean z9;
        if (i5 != 0) {
            ThreadLocal threadLocal = d.f405a;
            double[] dArr = (double[]) threadLocal.get();
            if (dArr == null) {
                dArr = new double[3];
                threadLocal.set(dArr);
            }
            int iRed = Color.red(i5);
            int iGreen = Color.green(i5);
            int iBlue = Color.blue(i5);
            if (dArr.length != 3) {
                throw new IllegalArgumentException("outXyz must have a length of 3.");
            }
            double d8 = ((double) iRed) / 255.0d;
            double dPow = d8 < 0.04045d ? d8 / 12.92d : Math.pow((d8 + 0.055d) / 1.055d, 2.4d);
            double d10 = ((double) iGreen) / 255.0d;
            double dPow2 = d10 < 0.04045d ? d10 / 12.92d : Math.pow((d10 + 0.055d) / 1.055d, 2.4d);
            double d11 = ((double) iBlue) / 255.0d;
            double dPow3 = d11 < 0.04045d ? d11 / 12.92d : Math.pow((d11 + 0.055d) / 1.055d, 2.4d);
            z9 = false;
            dArr[0] = ((0.1805d * dPow3) + (0.3576d * dPow2) + (0.4124d * dPow)) * 100.0d;
            double d12 = ((0.0722d * dPow3) + (0.7152d * dPow2) + (0.2126d * dPow)) * 100.0d;
            dArr[1] = d12;
            dArr[2] = ((dPow3 * 0.9505d) + (dPow2 * 0.1192d) + (dPow * 0.0193d)) * 100.0d;
            if (d12 / 100.0d > 0.5d) {
                return true;
            }
        } else {
            z9 = false;
        }
        return z9;
    }

    public static boolean isLightTheme(Context context) {
        return MaterialAttributes.resolveBoolean(context, R.attr.isLightTheme, true);
    }

    public static int layer(View view, int i5, int i7) {
        return layer(view, i5, i7, 1.0f);
    }

    private static int resolveColor(Context context, TypedValue typedValue) {
        int i5 = typedValue.resourceId;
        return i5 != 0 ? b.a(context, i5) : typedValue.data;
    }

    private static ColorStateList resolveColorStateList(Context context, TypedValue typedValue) {
        int i5 = typedValue.resourceId;
        return i5 != 0 ? a.f(context, i5) : ColorStateList.valueOf(typedValue.data);
    }

    public static ColorRoles getColorRoles(int i5, boolean z9) {
        return z9 ? new ColorRoles(getColorRole(i5, 40), getColorRole(i5, 100), getColorRole(i5, 90), getColorRole(i5, 10)) : new ColorRoles(getColorRole(i5, 80), getColorRole(i5, 20), getColorRole(i5, 30), getColorRole(i5, 90));
    }

    public static int layer(View view, int i5, int i7, float f2) {
        return layer(getColor(view, i5), getColor(view, i7), f2);
    }

    public static int getColor(Context context, int i5, String str) {
        return resolveColor(context, MaterialAttributes.resolveTypedValueOrThrow(context, i5, str));
    }

    private static int getColorRole(int i5, int i7, int i9) {
        Hct hctFromInt = Hct.fromInt(getColorRole(i5, i7));
        hctFromInt.setChroma(i9);
        return hctFromInt.toInt();
    }

    public static int layer(int i5, int i7, float f2) {
        return layer(i5, d.e(i7, Math.round(Color.alpha(i7) * f2)));
    }

    public static int getColor(View view, int i5, int i7) {
        return getColor(view.getContext(), i5, i7);
    }

    public static int getColor(Context context, int i5, int i7) {
        Integer colorOrNull = getColorOrNull(context, i5);
        return colorOrNull != null ? colorOrNull.intValue() : i7;
    }

    public static int layer(int i5, int i7) {
        return d.c(i7, i5);
    }
}
