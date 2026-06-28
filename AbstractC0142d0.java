package androidx.appcompat.widget;

import android.widget.TextView;

/* JADX INFO: renamed from: androidx.appcompat.widget.d0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0142d0 {
    public static int a(TextView textView) {
        return textView.getAutoSizeStepGranularity();
    }

    public static void b(TextView textView, int i5, int i7, int i9, int i10) {
        textView.setAutoSizeTextTypeUniformWithConfiguration(i5, i7, i9, i10);
    }

    public static void c(TextView textView, int[] iArr, int i5) {
        textView.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i5);
    }

    public static boolean d(TextView textView, String str) {
        return textView.setFontVariationSettings(str);
    }
}
