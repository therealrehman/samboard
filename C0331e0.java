package androidx.recyclerview.widget;

import android.animation.Animator;

/* JADX INFO: renamed from: androidx.recyclerview.widget.e0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0331e0 implements Animator.AnimatorListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f9130e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f9131f;

    public /* synthetic */ C0331e0(RecyclerView recyclerView, int i5) {
        this.f9130e = i5;
        this.f9131f = recyclerView;
    }

    private final void a(Animator animator) {
    }

    private final void b(Animator animator) {
    }

    private final void c(Animator animator) {
    }

    private final void d(Animator animator) {
    }

    private final void e(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        int i5 = this.f9130e;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.f9130e) {
            case 0:
                RecyclerView recyclerView = this.f9131f;
                try {
                    recyclerView.mShowFadeOutGTT = 2;
                    recyclerView.setupGoToTop(0);
                } catch (Exception unused) {
                    return;
                }
                break;
            default:
                RecyclerView recyclerView2 = this.f9131f;
                recyclerView2.mLastItemAddRemoveAnim = null;
                recyclerView2.mIsSetOnlyAddAnim = false;
                recyclerView2.mIsSetOnlyRemoveAnim = false;
                AbstractC0358s0 itemAnimator = recyclerView2.getItemAnimator();
                if (itemAnimator instanceof C0357s) {
                    ((C0357s) itemAnimator).f9265p = 0;
                }
                recyclerView2.invalidate();
                break;
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
        int i5 = this.f9130e;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        switch (this.f9130e) {
            case 0:
                try {
                    this.f9131f.mShowFadeOutGTT = 1;
                } catch (Exception unused) {
                    return;
                }
                break;
        }
    }
}
