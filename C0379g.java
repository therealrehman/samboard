package androidx.transition;

import android.view.ViewGroup;

/* JADX INFO: renamed from: androidx.transition.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0379g extends W {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f9431e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ ViewGroup f9432f;

    public C0379g(ViewGroup viewGroup) {
        this.f9432f = viewGroup;
    }

    @Override // androidx.transition.W, androidx.transition.T
    public final void onTransitionCancel(U u5) {
        this.f9432f.suppressLayout(false);
        this.f9431e = true;
    }

    @Override // androidx.transition.T
    public final void onTransitionEnd(U u5) {
        if (!this.f9431e) {
            this.f9432f.suppressLayout(false);
        }
        u5.removeListener(this);
    }

    @Override // androidx.transition.W, androidx.transition.T
    public final void onTransitionPause(U u5) {
        this.f9432f.suppressLayout(false);
    }

    @Override // androidx.transition.W, androidx.transition.T
    public final void onTransitionResume(U u5) {
        this.f9432f.suppressLayout(true);
    }
}
