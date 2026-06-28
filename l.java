package com.google.android.material.chip;

import android.animation.ValueAnimator;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class l implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f10048e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ SeslPeoplePicker f10049f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ float f10050g;

    public /* synthetic */ l(SeslPeoplePicker seslPeoplePicker, float f2, int i5) {
        this.f10048e = i5;
        this.f10049f = seslPeoplePicker;
        this.f10050g = f2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.f10048e) {
            case 0:
                this.f10049f.lambda$startHidingAnimation$5(this.f10050g, valueAnimator);
                break;
            default:
                this.f10049f.lambda$startShowingAnimation$4(this.f10050g, valueAnimator);
                break;
        }
    }
}
