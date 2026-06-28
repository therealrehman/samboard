package com.google.android.material.shape;

/* JADX INFO: loaded from: classes.dex */
public final class OffsetEdgeTreatment extends EdgeTreatment {
    private final float offset;
    private final EdgeTreatment other;

    public OffsetEdgeTreatment(EdgeTreatment edgeTreatment, float f2) {
        this.other = edgeTreatment;
        this.offset = f2;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public boolean forceIntersection() {
        return this.other.forceIntersection();
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f2, float f7, float f9, ShapePath shapePath) {
        this.other.getEdgePath(f2, f7 - this.offset, f9, shapePath);
    }
}
