package com.google.android.material.shape;

import android.graphics.RectF;

/* JADX INFO: loaded from: classes.dex */
public class CornerTreatment {
    @Deprecated
    public void getCornerPath(float f2, float f7, ShapePath shapePath) {
    }

    public void getCornerPath(ShapePath shapePath, float f2, float f7, float f9) {
        getCornerPath(f2, f7, shapePath);
    }

    public void getCornerPath(ShapePath shapePath, float f2, float f7, RectF rectF, CornerSize cornerSize) {
        getCornerPath(shapePath, f2, f7, cornerSize.getCornerSize(rectF));
    }
}
