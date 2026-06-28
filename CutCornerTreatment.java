package com.google.android.material.shape;

/* JADX INFO: loaded from: classes.dex */
public class CutCornerTreatment extends CornerTreatment {
    float size;

    public CutCornerTreatment() {
        this.size = -1.0f;
    }

    @Override // com.google.android.material.shape.CornerTreatment
    public void getCornerPath(ShapePath shapePath, float f2, float f7, float f9) {
        shapePath.reset(0.0f, f9 * f7, 180.0f, 180.0f - f2);
        double d8 = f9;
        double d10 = f7;
        shapePath.lineTo((float) (Math.sin(Math.toRadians(f2)) * d8 * d10), (float) (Math.sin(Math.toRadians(90.0f - f2)) * d8 * d10));
    }

    @Deprecated
    public CutCornerTreatment(float f2) {
        this.size = f2;
    }
}
