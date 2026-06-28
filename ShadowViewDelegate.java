package com.google.android.material.shadow;

import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes.dex */
public interface ShadowViewDelegate {
    float getRadius();

    boolean isCompatPaddingEnabled();

    void setBackgroundDrawable(Drawable drawable);

    void setShadowPadding(int i5, int i7, int i9, int i10);
}
