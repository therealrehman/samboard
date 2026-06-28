package com.google.android.material.canvas;

import android.graphics.Canvas;
import android.graphics.RectF;

/* JADX INFO: loaded from: classes.dex */
public class CanvasCompat {

    public interface CanvasOperation {
        void run(Canvas canvas);
    }

    private CanvasCompat() {
    }

    public static int saveLayerAlpha(Canvas canvas, RectF rectF, int i5) {
        return canvas.saveLayerAlpha(rectF, i5);
    }

    public static int saveLayerAlpha(Canvas canvas, float f2, float f7, float f9, float f10, int i5) {
        return canvas.saveLayerAlpha(f2, f7, f9, f10, i5);
    }
}
