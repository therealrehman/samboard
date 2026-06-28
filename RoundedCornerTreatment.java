package com.google.android.material.shape;

/* JADX INFO: loaded from: classes.dex */
public class RoundedCornerTreatment extends CornerTreatment {
    float radius;

    public RoundedCornerTreatment() {
        this.radius = -1.0f;
    }

    @Override // com.google.android.material.shape.CornerTreatment
    public void getCornerPath(ShapePath shapePath, float f2, float f7, float f9) {
        shapePath.reset(0.0f, f9 * f7, 180.0f, 180.0f - f2);
        float f10 = f9 * 2.0f * f7;
        shapePath.addArc(0.0f, 0.0f, f10, f10, 180.0f, f2);
    }

    @Deprecated
    public RoundedCornerTreatment(float f2) {
        this.radius = f2;
    }
}
