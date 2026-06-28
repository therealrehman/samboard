package com.google.android.material.internal;

import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.view.Window;
import androidx.core.view.g0;
import androidx.core.view.x0;
import com.google.android.material.color.MaterialColors;

/* JADX INFO: loaded from: classes.dex */
public class EdgeToEdgeUtils {
    private static final int EDGE_TO_EDGE_BAR_ALPHA = 128;

    private EdgeToEdgeUtils() {
    }

    public static void applyEdgeToEdge(Window window, boolean z9) {
        applyEdgeToEdge(window, z9, null, null);
    }

    @TargetApi(21)
    private static int getNavigationBarColor(Context context, boolean z9) {
        if (z9) {
            return 0;
        }
        return MaterialColors.getColor(context, R.attr.navigationBarColor, -16777216);
    }

    @TargetApi(21)
    private static int getStatusBarColor(Context context, boolean z9) {
        if (z9) {
            return 0;
        }
        return MaterialColors.getColor(context, R.attr.statusBarColor, -16777216);
    }

    private static boolean isUsingLightSystemBar(int i5, boolean z9) {
        return MaterialColors.isColorLight(i5) || (i5 == 0 && z9);
    }

    public static void setLightNavigationBar(Window window, boolean z9) {
        new x0(window, window.getDecorView()).a(z9);
    }

    public static void setLightStatusBar(Window window, boolean z9) {
        new x0(window, window.getDecorView()).b(z9);
    }

    public static void applyEdgeToEdge(Window window, boolean z9, Integer num, Integer num2) {
        boolean z10 = num == null || num.intValue() == 0;
        boolean z11 = num2 == null || num2.intValue() == 0;
        if (z10 || z11) {
            int color = MaterialColors.getColor(window.getContext(), R.attr.colorBackground, -16777216);
            if (z10) {
                num = Integer.valueOf(color);
            }
            if (z11) {
                num2 = Integer.valueOf(color);
            }
        }
        g0.a(window, !z9);
        int statusBarColor = getStatusBarColor(window.getContext(), z9);
        int navigationBarColor = getNavigationBarColor(window.getContext(), z9);
        window.setStatusBarColor(statusBarColor);
        window.setNavigationBarColor(navigationBarColor);
        setLightStatusBar(window, isUsingLightSystemBar(statusBarColor, MaterialColors.isColorLight(num.intValue())));
        setLightNavigationBar(window, isUsingLightSystemBar(navigationBarColor, MaterialColors.isColorLight(num2.intValue())));
    }
}
