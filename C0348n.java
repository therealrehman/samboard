package androidx.recyclerview.widget;

import android.animation.ValueAnimator;

/* JADX INFO: renamed from: androidx.recyclerview.widget.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0348n implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f9196e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f9197f;

    public /* synthetic */ C0348n(RecyclerView recyclerView, int i5) {
        this.f9196e = i5;
        this.f9197f = recyclerView;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.f9196e) {
            case 0:
                this.f9197f.invalidate();
                break;
            case 1:
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                RecyclerView recyclerView = this.f9197f;
                recyclerView.mAnimatedBlackTop = iIntValue;
                recyclerView.invalidate();
                break;
            case 2:
                try {
                    this.f9197f.mGoToTopView.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                } catch (Exception unused) {
                    return;
                }
                break;
            default:
                try {
                    this.f9197f.mGoToTopView.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                } catch (Exception unused2) {
                    return;
                }
                break;
        }
    }
}
