package com.google.android.material.transition.platform;

/* JADX INFO: loaded from: classes.dex */
class FitModeResult {
    final float currentEndHeight;
    final float currentEndWidth;
    final float currentStartHeight;
    final float currentStartWidth;
    final float endScale;
    final float startScale;

    public FitModeResult(float f2, float f7, float f9, float f10, float f11, float f12) {
        this.startScale = f2;
        this.endScale = f7;
        this.currentStartWidth = f9;
        this.currentStartHeight = f10;
        this.currentEndWidth = f11;
        this.currentEndHeight = f12;
    }
}
