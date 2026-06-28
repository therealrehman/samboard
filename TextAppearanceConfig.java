package com.google.android.material.resources;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class TextAppearanceConfig {
    private static boolean shouldLoadFontSynchronously;

    public static void setShouldLoadFontSynchronously(boolean z9) {
        shouldLoadFontSynchronously = z9;
    }

    public static boolean shouldLoadFontSynchronously() {
        return shouldLoadFontSynchronously;
    }
}
