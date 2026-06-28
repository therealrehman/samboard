package com.google.android.material.shape;

/* JADX INFO: loaded from: classes.dex */
public class EdgeTreatment {
    public boolean forceIntersection() {
        return false;
    }

    @Deprecated
    public void getEdgePath(float f2, float f7, ShapePath shapePath) {
        getEdgePath(f2, f2 / 2.0f, f7, shapePath);
    }

    public void getEdgePath(float f2, float f7, float f9, ShapePath shapePath) {
        shapePath.lineTo(f2, 0.0f);
    }
}
