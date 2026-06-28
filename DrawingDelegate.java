package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;

/* JADX INFO: loaded from: classes.dex */
abstract class DrawingDelegate<S extends BaseProgressIndicatorSpec> {
    protected DrawableWithAnimatedVisibilityChange drawable;
    S spec;

    public DrawingDelegate(S s8) {
        this.spec = s8;
    }

    public abstract void adjustCanvas(Canvas canvas, Rect rect, float f2);

    public abstract void fillIndicator(Canvas canvas, Paint paint, float f2, float f7, int i5);

    public abstract void fillTrack(Canvas canvas, Paint paint);

    public abstract int getPreferredHeight();

    public abstract int getPreferredWidth();

    public void registerDrawable(DrawableWithAnimatedVisibilityChange drawableWithAnimatedVisibilityChange) {
        this.drawable = drawableWithAnimatedVisibilityChange;
    }

    public void validateSpecAndAdjustCanvas(Canvas canvas, Rect rect, float f2) {
        this.spec.validateSpec();
        adjustCanvas(canvas, rect, f2);
    }
}
