package androidx.appcompat.widget;

import android.animation.ValueAnimator;

/* JADX INFO: renamed from: androidx.appcompat.widget.j1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0161j1 implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6726e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ C0167l1 f6727f;

    public /* synthetic */ C0161j1(C0167l1 c0167l1, int i5) {
        this.f6726e = i5;
        this.f6727f = c0167l1;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.f6726e) {
            case 0:
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                C0167l1 c0167l1 = this.f6727f;
                c0167l1.f6744a.setStrokeWidth(fFloatValue);
                c0167l1.f6745b = fFloatValue / 2.0f;
                c0167l1.invalidateSelf();
                break;
            default:
                float fFloatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                C0167l1 c0167l12 = this.f6727f;
                c0167l12.f6744a.setStrokeWidth(fFloatValue2);
                c0167l12.f6745b = fFloatValue2 / 2.0f;
                c0167l12.invalidateSelf();
                break;
        }
    }
}
