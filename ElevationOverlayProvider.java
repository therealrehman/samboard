package com.google.android.material.elevation;

import D.d;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialAttributes;

/* JADX INFO: loaded from: classes.dex */
public class ElevationOverlayProvider {
    private static final float FORMULA_MULTIPLIER = 4.5f;
    private static final float FORMULA_OFFSET = 2.0f;
    private static final int OVERLAY_ACCENT_COLOR_ALPHA = (int) Math.round(5.1000000000000005d);
    private final int colorSurface;
    private final float displayDensity;
    private final int elevationOverlayAccentColor;
    private final int elevationOverlayColor;
    private final boolean elevationOverlayEnabled;

    public ElevationOverlayProvider(Context context) {
        this(MaterialAttributes.resolveBoolean(context, R.attr.elevationOverlayEnabled, false), MaterialColors.getColor(context, R.attr.elevationOverlayColor, 0), MaterialColors.getColor(context, R.attr.elevationOverlayAccentColor, 0), MaterialColors.getColor(context, R.attr.colorSurface, 0), context.getResources().getDisplayMetrics().density);
    }

    private boolean isThemeSurfaceColor(int i5) {
        return d.e(i5, 255) == this.colorSurface;
    }

    public int calculateOverlayAlpha(float f2) {
        return Math.round(calculateOverlayAlphaFraction(f2) * 255.0f);
    }

    public float calculateOverlayAlphaFraction(float f2) {
        if (this.displayDensity <= 0.0f || f2 <= 0.0f) {
            return 0.0f;
        }
        return Math.min(((((float) Math.log1p(f2 / r2)) * FORMULA_MULTIPLIER) + FORMULA_OFFSET) / 100.0f, 1.0f);
    }

    public int compositeOverlay(int i5, float f2, View view) {
        return compositeOverlay(i5, getParentAbsoluteElevation(view) + f2);
    }

    public int compositeOverlayIfNeeded(int i5, float f2, View view) {
        return compositeOverlayIfNeeded(i5, getParentAbsoluteElevation(view) + f2);
    }

    public int compositeOverlayWithThemeSurfaceColorIfNeeded(float f2, View view) {
        return compositeOverlayWithThemeSurfaceColorIfNeeded(getParentAbsoluteElevation(view) + f2);
    }

    public float getParentAbsoluteElevation(View view) {
        return ViewUtils.getParentAbsoluteElevation(view);
    }

    public int getThemeElevationOverlayColor() {
        return this.elevationOverlayColor;
    }

    public int getThemeSurfaceColor() {
        return this.colorSurface;
    }

    public boolean isThemeElevationOverlayEnabled() {
        return this.elevationOverlayEnabled;
    }

    public int compositeOverlay(int i5, float f2) {
        int i7;
        float fCalculateOverlayAlphaFraction = calculateOverlayAlphaFraction(f2);
        int iAlpha = Color.alpha(i5);
        int iLayer = MaterialColors.layer(d.e(i5, 255), this.elevationOverlayColor, fCalculateOverlayAlphaFraction);
        if (fCalculateOverlayAlphaFraction > 0.0f && (i7 = this.elevationOverlayAccentColor) != 0) {
            iLayer = MaterialColors.layer(iLayer, d.e(i7, OVERLAY_ACCENT_COLOR_ALPHA));
        }
        return d.e(iLayer, iAlpha);
    }

    public int compositeOverlayIfNeeded(int i5, float f2) {
        return (this.elevationOverlayEnabled && isThemeSurfaceColor(i5)) ? compositeOverlay(i5, f2) : i5;
    }

    public int compositeOverlayWithThemeSurfaceColorIfNeeded(float f2) {
        return compositeOverlayIfNeeded(this.colorSurface, f2);
    }

    public ElevationOverlayProvider(boolean z9, int i5, int i7, int i9, float f2) {
        this.elevationOverlayEnabled = z9;
        this.elevationOverlayColor = i5;
        this.elevationOverlayAccentColor = i7;
        this.colorSurface = i9;
        this.displayDensity = f2;
    }
}
