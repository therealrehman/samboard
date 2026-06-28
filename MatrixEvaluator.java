package com.google.android.material.animation;

import android.animation.TypeEvaluator;
import android.graphics.Matrix;
import d6.AbstractC0476d;

/* JADX INFO: loaded from: classes.dex */
public class MatrixEvaluator implements TypeEvaluator<Matrix> {
    private final float[] tempStartValues = new float[9];
    private final float[] tempEndValues = new float[9];
    private final Matrix tempMatrix = new Matrix();

    @Override // android.animation.TypeEvaluator
    public Matrix evaluate(float f2, Matrix matrix, Matrix matrix2) {
        matrix.getValues(this.tempStartValues);
        matrix2.getValues(this.tempEndValues);
        for (int i5 = 0; i5 < 9; i5++) {
            float[] fArr = this.tempEndValues;
            float f7 = fArr[i5];
            float f9 = this.tempStartValues[i5];
            fArr[i5] = AbstractC0476d.u(f7, f9, f2, f9);
        }
        this.tempMatrix.setValues(this.tempEndValues);
        return this.tempMatrix;
    }
}
