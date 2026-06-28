package com.google.android.material.util;

import android.widget.TextView;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a%\u0010\u0006\u001a\u00020\u0005*\u00020\u00002\b\b\u0001\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\u0000¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroid/widget/TextView;", "", "baseSizeDp", "Lcom/google/android/material/util/MaxFontScaleRatio;", "maxFontScale", "LT6/p;", "checkMaxFontScale", "(Landroid/widget/TextView;ILcom/google/android/material/util/MaxFontScaleRatio;)V", "material_release"}, k = 2, mv = {1, 8, 0})
public final class SeslTextViewHelperKt {
    public static final void checkMaxFontScale(TextView textView, int i5, MaxFontScaleRatio maxFontScale) {
        j.f(textView, "<this>");
        j.f(maxFontScale, "maxFontScale");
        float f2 = textView.getResources().getConfiguration().fontScale;
        float ratio = maxFontScale.getRatio();
        if (f2 > ratio) {
            f2 = ratio;
        }
        textView.setTextSize(0, textView.getResources().getDimensionPixelSize(i5) * f2);
    }
}
