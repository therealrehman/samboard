package com.google.android.material.internal;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class SeslDisplayUtils {
    private static final String TAG = "SeslDisplayUtils";

    public static int getEdgeArea(Context context) {
        return Settings.System.getInt(context.getContentResolver(), "active_edge_area", 1);
    }

    public static int getPinnedEdgeWidth(Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), "pinned_edge_width");
        } catch (Settings.SettingNotFoundException e3) {
            Log.w(TAG, "Failed get EdgeWidth " + e3.toString());
            return 0;
        }
    }

    public static boolean isPinEdgeEnabled(Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), "panel_mode", 0) == 1;
        } catch (Exception e3) {
            Log.w(TAG, "Failed get panel mode " + e3.toString());
            return false;
        }
    }
}
