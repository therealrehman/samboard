package com.google.android.material.bottomappbar;

import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.ShapePath;
import d6.AbstractC0476d;

/* JADX INFO: loaded from: classes.dex */
public class BottomAppBarTopEdgeTreatment extends EdgeTreatment implements Cloneable {
    private static final int ANGLE_LEFT = 180;
    private static final int ANGLE_UP = 270;
    private static final int ARC_HALF = 180;
    private static final int ARC_QUARTER = 90;
    private static final float ROUNDED_CORNER_FAB_OFFSET = 1.75f;
    private float cradleVerticalOffset;
    private float fabCornerSize = -1.0f;
    private float fabDiameter;
    private float fabMargin;
    private float horizontalOffset;
    private float roundedCornerRadius;

    public BottomAppBarTopEdgeTreatment(float f2, float f7, float f9) {
        this.fabMargin = f2;
        this.roundedCornerRadius = f7;
        setCradleVerticalOffset(f9);
        this.horizontalOffset = 0.0f;
    }

    public float getCradleVerticalOffset() {
        return this.cradleVerticalOffset;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f2, float f7, float f9, ShapePath shapePath) {
        float f10;
        float f11;
        float f12 = this.fabDiameter;
        if (f12 == 0.0f) {
            shapePath.lineTo(f2, 0.0f);
            return;
        }
        float f13 = ((this.fabMargin * 2.0f) + f12) / 2.0f;
        float f14 = f9 * this.roundedCornerRadius;
        float f15 = f7 + this.horizontalOffset;
        float fU = AbstractC0476d.u(1.0f, f9, f13, this.cradleVerticalOffset * f9);
        if (fU / f13 >= 1.0f) {
            shapePath.lineTo(f2, 0.0f);
            return;
        }
        float f16 = this.fabCornerSize;
        float f17 = f16 * f9;
        boolean z9 = f16 == -1.0f || Math.abs((f16 * 2.0f) - f12) < 0.1f;
        if (z9) {
            f10 = fU;
            f11 = 0.0f;
        } else {
            f11 = ROUNDED_CORNER_FAB_OFFSET;
            f10 = 0.0f;
        }
        float f18 = f13 + f14;
        float f19 = f10 + f14;
        float fSqrt = (float) Math.sqrt((f18 * f18) - (f19 * f19));
        float f20 = f15 - fSqrt;
        float f21 = f15 + fSqrt;
        float degrees = (float) Math.toDegrees(Math.atan(fSqrt / f19));
        float f22 = (90.0f - degrees) + f11;
        shapePath.lineTo(f20, 0.0f);
        float f23 = f14 * 2.0f;
        shapePath.addArc(f20 - f14, 0.0f, f20 + f14, f23, 270.0f, degrees);
        if (z9) {
            shapePath.addArc(f15 - f13, (-f13) - f10, f15 + f13, f13 - f10, 180.0f - f22, (f22 * 2.0f) - 180.0f);
        } else {
            float f24 = this.fabMargin;
            float f25 = f17 * 2.0f;
            float f26 = f15 - f13;
            shapePath.addArc(f26, -(f17 + f24), f26 + f24 + f25, f24 + f17, 180.0f - f22, ((f22 * 2.0f) - 180.0f) / 2.0f);
            float f27 = f15 + f13;
            float f28 = this.fabMargin;
            shapePath.lineTo(f27 - ((f28 / 2.0f) + f17), f28 + f17);
            float f29 = this.fabMargin;
            shapePath.addArc(f27 - (f25 + f29), -(f17 + f29), f27, f29 + f17, 90.0f, f22 - 90.0f);
        }
        shapePath.addArc(f21 - f14, 0.0f, f21 + f14, f23, 270.0f - degrees, degrees);
        shapePath.lineTo(f2, 0.0f);
    }

    public float getFabCornerRadius() {
        return this.fabCornerSize;
    }

    public float getFabCradleMargin() {
        return this.fabMargin;
    }

    public float getFabCradleRoundedCornerRadius() {
        return this.roundedCornerRadius;
    }

    public float getFabDiameter() {
        return this.fabDiameter;
    }

    public float getHorizontalOffset() {
        return this.horizontalOffset;
    }

    public void setCradleVerticalOffset(float f2) {
        if (f2 < 0.0f) {
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
        this.cradleVerticalOffset = f2;
    }

    public void setFabCornerSize(float f2) {
        this.fabCornerSize = f2;
    }

    public void setFabCradleMargin(float f2) {
        this.fabMargin = f2;
    }

    public void setFabCradleRoundedCornerRadius(float f2) {
        this.roundedCornerRadius = f2;
    }

    public void setFabDiameter(float f2) {
        this.fabDiameter = f2;
    }

    public void setHorizontalOffset(float f2) {
        this.horizontalOffset = f2;
    }
}
