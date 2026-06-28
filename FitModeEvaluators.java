package com.google.android.material.transition;

import A8.l;
import android.graphics.RectF;

/* JADX INFO: loaded from: classes.dex */
class FitModeEvaluators {
    private static final FitModeEvaluator WIDTH = new FitModeEvaluator() { // from class: com.google.android.material.transition.FitModeEvaluators.1
        @Override // com.google.android.material.transition.FitModeEvaluator
        public void applyMask(RectF rectF, float f2, FitModeResult fitModeResult) {
            rectF.bottom -= Math.abs(fitModeResult.currentEndHeight - fitModeResult.currentStartHeight) * f2;
        }

        @Override // com.google.android.material.transition.FitModeEvaluator
        public FitModeResult evaluate(float f2, float f7, float f9, float f10, float f11, float f12, float f13) {
            float fLerp = TransitionUtils.lerp(f10, f12, f7, f9, f2, true);
            float f14 = fLerp / f10;
            float f15 = fLerp / f12;
            return new FitModeResult(f14, f15, fLerp, f11 * f14, fLerp, f13 * f15);
        }

        @Override // com.google.android.material.transition.FitModeEvaluator
        public boolean shouldMaskStartBounds(FitModeResult fitModeResult) {
            return fitModeResult.currentStartHeight > fitModeResult.currentEndHeight;
        }
    };
    private static final FitModeEvaluator HEIGHT = new FitModeEvaluator() { // from class: com.google.android.material.transition.FitModeEvaluators.2
        @Override // com.google.android.material.transition.FitModeEvaluator
        public void applyMask(RectF rectF, float f2, FitModeResult fitModeResult) {
            float fAbs = (Math.abs(fitModeResult.currentEndWidth - fitModeResult.currentStartWidth) / 2.0f) * f2;
            rectF.left += fAbs;
            rectF.right -= fAbs;
        }

        @Override // com.google.android.material.transition.FitModeEvaluator
        public FitModeResult evaluate(float f2, float f7, float f9, float f10, float f11, float f12, float f13) {
            float fLerp = TransitionUtils.lerp(f11, f13, f7, f9, f2, true);
            float f14 = fLerp / f11;
            float f15 = fLerp / f13;
            return new FitModeResult(f14, f15, f10 * f14, fLerp, f12 * f15, fLerp);
        }

        @Override // com.google.android.material.transition.FitModeEvaluator
        public boolean shouldMaskStartBounds(FitModeResult fitModeResult) {
            return fitModeResult.currentStartWidth > fitModeResult.currentEndWidth;
        }
    };

    private FitModeEvaluators() {
    }

    public static FitModeEvaluator get(int i5, boolean z9, RectF rectF, RectF rectF2) {
        if (i5 == 0) {
            return shouldAutoFitToWidth(z9, rectF, rectF2) ? WIDTH : HEIGHT;
        }
        if (i5 == 1) {
            return WIDTH;
        }
        if (i5 == 2) {
            return HEIGHT;
        }
        throw new IllegalArgumentException(l.o(i5, "Invalid fit mode: "));
    }

    private static boolean shouldAutoFitToWidth(boolean z9, RectF rectF, RectF rectF2) {
        float fWidth = rectF.width();
        float fHeight = rectF.height();
        float fWidth2 = rectF2.width();
        float fHeight2 = rectF2.height();
        float f2 = (fHeight2 * fWidth) / fWidth2;
        float f7 = (fWidth2 * fHeight) / fWidth;
        if (z9) {
            if (f2 < fHeight) {
                return false;
            }
        } else if (f7 < fHeight2) {
            return false;
        }
        return true;
    }
}
