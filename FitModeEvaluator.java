package com.google.android.material.transition;

import android.graphics.RectF;

/* JADX INFO: loaded from: classes.dex */
interface FitModeEvaluator {
    void applyMask(RectF rectF, float f2, FitModeResult fitModeResult);

    FitModeResult evaluate(float f2, float f7, float f9, float f10, float f11, float f12, float f13);

    boolean shouldMaskStartBounds(FitModeResult fitModeResult);
}
