package com.google.android.material.carousel;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class CarouselStrategy {
    public static int[] doubleCounts(int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i5 = 0; i5 < length; i5++) {
            iArr2[i5] = iArr[i5] * 2;
        }
        return iArr2;
    }

    public static float getChildMaskPercentage(float f2, float f7, float f9) {
        return 1.0f - ((f2 - f9) / (f7 - f9));
    }

    public boolean isContained() {
        return true;
    }

    public abstract KeylineState onFirstChildMeasuredWithMargins(Carousel carousel, View view);

    public boolean shouldRefreshKeylineState(Carousel carousel, int i5) {
        return false;
    }
}
