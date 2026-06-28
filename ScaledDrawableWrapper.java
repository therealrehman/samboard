package com.google.android.material.drawable;

import android.graphics.drawable.Drawable;
import i.C0590a;

/* JADX INFO: loaded from: classes.dex */
public class ScaledDrawableWrapper extends C0590a {
    private final int height;
    private final int width;

    public ScaledDrawableWrapper(Drawable drawable, int i5, int i7) {
        super(drawable);
        this.width = i5;
        this.height = i7;
    }

    @Override // i.C0590a, android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.height;
    }

    @Override // i.C0590a, android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.width;
    }
}
