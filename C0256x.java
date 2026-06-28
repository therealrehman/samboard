package androidx.fragment.app;

import android.view.View;

/* JADX INFO: renamed from: androidx.fragment.app.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0256x extends J {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Fragment f7745e;

    public C0256x(Fragment fragment) {
        this.f7745e = fragment;
    }

    @Override // androidx.fragment.app.J
    public final View b(int i5) {
        Fragment fragment = this.f7745e;
        View view = fragment.mView;
        if (view != null) {
            return view.findViewById(i5);
        }
        throw new IllegalStateException(A8.l.q("Fragment ", fragment, " does not have a view"));
    }

    @Override // androidx.fragment.app.J
    public final boolean c() {
        return this.f7745e.mView != null;
    }
}
