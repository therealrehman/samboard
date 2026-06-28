package com.google.android.material.transition;

import android.graphics.Path;
import android.graphics.PointF;
import androidx.transition.H;

/* JADX INFO: loaded from: classes.dex */
public final class MaterialArcMotion extends H {
    private static PointF getControlPoint(float f2, float f7, float f9, float f10) {
        return f7 > f10 ? new PointF(f9, f7) : new PointF(f2, f10);
    }

    @Override // androidx.transition.H
    public Path getPath(float f2, float f7, float f9, float f10) {
        Path path = new Path();
        path.moveTo(f2, f7);
        PointF controlPoint = getControlPoint(f2, f7, f9, f10);
        path.quadTo(controlPoint.x, controlPoint.y, f9, f10);
        return path;
    }
}
