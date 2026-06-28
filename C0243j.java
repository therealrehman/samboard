package androidx.fragment.app;

import android.transition.Transition;

/* JADX INFO: renamed from: androidx.fragment.app.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0243j extends AbstractC0242i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f7665c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f7666d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f7667e;

    public C0243j(v0 v0Var, H.f fVar, boolean z9, boolean z10) {
        super(v0Var, fVar);
        int i5 = v0Var.f7736a;
        Fragment fragment = v0Var.f7738c;
        this.f7665c = i5 == 2 ? z9 ? fragment.getReenterTransition() : fragment.getEnterTransition() : z9 ? fragment.getReturnTransition() : fragment.getExitTransition();
        this.f7666d = v0Var.f7736a == 2 ? z9 ? fragment.getAllowReturnTransitionOverlap() : fragment.getAllowEnterTransitionOverlap() : true;
        this.f7667e = z10 ? z9 ? fragment.getSharedElementReturnTransition() : fragment.getSharedElementEnterTransition() : null;
    }

    public final r0 c() {
        Object obj = this.f7665c;
        r0 r0VarD = d(obj);
        Object obj2 = this.f7667e;
        r0 r0VarD2 = d(obj2);
        if (r0VarD == null || r0VarD2 == null || r0VarD == r0VarD2) {
            return r0VarD == null ? r0VarD2 : r0VarD;
        }
        throw new IllegalArgumentException(("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + this.f7655a.f7738c + " returned Transition " + obj + " which uses a different Transition  type than its shared element transition " + obj2).toString());
    }

    public final r0 d(Object obj) {
        if (obj == null) {
            return null;
        }
        p0 p0Var = k0.f7686a;
        if (obj instanceof Transition) {
            return p0Var;
        }
        r0 r0Var = k0.f7687b;
        if (r0Var != null && r0Var.e(obj)) {
            return r0Var;
        }
        throw new IllegalArgumentException("Transition " + obj + " for fragment " + this.f7655a.f7738c + " is not a valid framework Transition or AndroidX Transition");
    }
}
