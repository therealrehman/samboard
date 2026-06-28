package androidx.appcompat.widget;

import android.animation.ValueAnimator;

/* JADX INFO: renamed from: androidx.appcompat.widget.m1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0170m1 implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6760e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ C0173n1 f6761f;

    public /* synthetic */ C0170m1(C0173n1 c0173n1, int i5) {
        this.f6760e = i5;
        this.f6761f = c0173n1;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.f6760e) {
            case 0:
                int iFloatValue = (int) ((Float) valueAnimator.getAnimatedValue()).floatValue();
                C0173n1 c0173n1 = this.f6761f;
                c0173n1.f6782e = iFloatValue;
                c0173n1.invalidateSelf();
                break;
            default:
                int iFloatValue2 = (int) ((Float) valueAnimator.getAnimatedValue()).floatValue();
                C0173n1 c0173n12 = this.f6761f;
                c0173n12.f6782e = iFloatValue2;
                c0173n12.invalidateSelf();
                break;
        }
    }
}
