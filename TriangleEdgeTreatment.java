package com.google.android.material.shape;

/* JADX INFO: loaded from: classes.dex */
public class TriangleEdgeTreatment extends EdgeTreatment {
    private final boolean inside;
    private final float size;

    public TriangleEdgeTreatment(float f2, boolean z9) {
        this.size = f2;
        this.inside = z9;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f2, float f7, float f9, ShapePath shapePath) {
        if (!this.inside) {
            float f10 = this.size;
            shapePath.lineTo(f7 - (f10 * f9), 0.0f, f7, (-f10) * f9);
            shapePath.lineTo((this.size * f9) + f7, 0.0f, f2, 0.0f);
        } else {
            shapePath.lineTo(f7 - (this.size * f9), 0.0f);
            float f11 = this.size;
            shapePath.lineTo(f7, f11 * f9, (f11 * f9) + f7, 0.0f);
            shapePath.lineTo(f2, 0.0f);
        }
    }
}
