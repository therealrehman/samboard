package androidx.transition;

import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.keyscafe.R;

/* JADX INFO: loaded from: classes.dex */
public final class i0 extends W {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ ViewGroup f9447e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ View f9448f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ View f9449g;
    public final /* synthetic */ l0 h;

    public i0(l0 l0Var, ViewGroup viewGroup, View view, View view2) {
        this.h = l0Var;
        this.f9447e = viewGroup;
        this.f9448f = view;
        this.f9449g = view2;
    }

    @Override // androidx.transition.T
    public final void onTransitionEnd(U u5) {
        this.f9449g.setTag(R.id.save_overlay_view, null);
        this.f9447e.getOverlay().remove(this.f9448f);
        u5.removeListener(this);
    }

    @Override // androidx.transition.W, androidx.transition.T
    public final void onTransitionPause(U u5) {
        this.f9447e.getOverlay().remove(this.f9448f);
    }

    @Override // androidx.transition.W, androidx.transition.T
    public final void onTransitionResume(U u5) {
        View view = this.f9448f;
        if (view.getParent() == null) {
            this.f9447e.getOverlay().add(view);
        } else {
            this.h.cancel();
        }
    }
}
