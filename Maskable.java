package com.google.android.material.carousel;

import android.graphics.RectF;

/* JADX INFO: loaded from: classes.dex */
interface Maskable {
    RectF getMaskRectF();

    @Deprecated
    float getMaskXPercentage();

    void setMaskRectF(RectF rectF);

    @Deprecated
    void setMaskXPercentage(float f2);

    void setOnMaskChangedListener(OnMaskChangedListener onMaskChangedListener);
}
