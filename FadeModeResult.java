package com.google.android.material.transition.platform;

/* JADX INFO: loaded from: classes.dex */
class FadeModeResult {
    final int endAlpha;
    final boolean endOnTop;
    final int startAlpha;

    private FadeModeResult(int i5, int i7, boolean z9) {
        this.startAlpha = i5;
        this.endAlpha = i7;
        this.endOnTop = z9;
    }

    public static FadeModeResult endOnTop(int i5, int i7) {
        return new FadeModeResult(i5, i7, true);
    }

    public static FadeModeResult startOnTop(int i5, int i7) {
        return new FadeModeResult(i5, i7, false);
    }
}
