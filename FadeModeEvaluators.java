package com.google.android.material.transition;

import A8.l;
import d6.AbstractC0476d;

/* JADX INFO: loaded from: classes.dex */
class FadeModeEvaluators {
    private static final FadeModeEvaluator IN = new FadeModeEvaluator() { // from class: com.google.android.material.transition.FadeModeEvaluators.1
        @Override // com.google.android.material.transition.FadeModeEvaluator
        public FadeModeResult evaluate(float f2, float f7, float f9, float f10) {
            return FadeModeResult.endOnTop(255, TransitionUtils.lerp(0, 255, f7, f9, f2));
        }
    };
    private static final FadeModeEvaluator OUT = new FadeModeEvaluator() { // from class: com.google.android.material.transition.FadeModeEvaluators.2
        @Override // com.google.android.material.transition.FadeModeEvaluator
        public FadeModeResult evaluate(float f2, float f7, float f9, float f10) {
            return FadeModeResult.startOnTop(TransitionUtils.lerp(255, 0, f7, f9, f2), 255);
        }
    };
    private static final FadeModeEvaluator CROSS = new FadeModeEvaluator() { // from class: com.google.android.material.transition.FadeModeEvaluators.3
        @Override // com.google.android.material.transition.FadeModeEvaluator
        public FadeModeResult evaluate(float f2, float f7, float f9, float f10) {
            return FadeModeResult.startOnTop(TransitionUtils.lerp(255, 0, f7, f9, f2), TransitionUtils.lerp(0, 255, f7, f9, f2));
        }
    };
    private static final FadeModeEvaluator THROUGH = new FadeModeEvaluator() { // from class: com.google.android.material.transition.FadeModeEvaluators.4
        @Override // com.google.android.material.transition.FadeModeEvaluator
        public FadeModeResult evaluate(float f2, float f7, float f9, float f10) {
            float fU = AbstractC0476d.u(f9, f7, f10, f7);
            return FadeModeResult.startOnTop(TransitionUtils.lerp(255, 0, f7, fU, f2), TransitionUtils.lerp(0, 255, fU, f9, f2));
        }
    };

    private FadeModeEvaluators() {
    }

    public static FadeModeEvaluator get(int i5, boolean z9) {
        if (i5 == 0) {
            return z9 ? IN : OUT;
        }
        if (i5 == 1) {
            return z9 ? OUT : IN;
        }
        if (i5 == 2) {
            return CROSS;
        }
        if (i5 == 3) {
            return THROUGH;
        }
        throw new IllegalArgumentException(l.o(i5, "Invalid fade mode: "));
    }
}
