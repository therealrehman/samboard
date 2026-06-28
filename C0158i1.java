package androidx.appcompat.widget;

import android.R;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

/* JADX INFO: renamed from: androidx.appcompat.widget.i1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0158i1 implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6722e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ AbstractC0176o1 f6723f;

    public /* synthetic */ C0158i1(AbstractC0176o1 abstractC0176o1, int i5) {
        this.f6722e = i5;
        this.f6723f = abstractC0176o1;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.f6722e) {
            case 0:
                AbstractC0176o1.v(this.f6723f, ((Integer) valueAnimator.getAnimatedValue()).intValue());
                break;
            default:
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                AbstractC0176o1 abstractC0176o1 = this.f6723f;
                abstractC0176o1.P0 = iIntValue;
                int i5 = abstractC0176o1.P0;
                Drawable drawable = abstractC0176o1.f6582I;
                if (drawable != null) {
                    Drawable drawableFindDrawableByLayerId = drawable instanceof LayerDrawable ? ((LayerDrawable) drawable).findDrawableByLayerId(R.id.progress) : null;
                    if (drawableFindDrawableByLayerId != null) {
                        drawableFindDrawableByLayerId.setLevel(i5);
                    }
                }
                float f2 = i5 / 10000.0f;
                Drawable drawable2 = abstractC0176o1.f6809g0;
                if (drawable2 != null) {
                    abstractC0176o1.E(abstractC0176o1.getWidth(), drawable2, f2, Integer.MIN_VALUE);
                    abstractC0176o1.invalidate();
                }
                break;
        }
    }
}
