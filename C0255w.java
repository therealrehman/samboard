package androidx.fragment.app;

import android.os.Bundle;

/* JADX INFO: renamed from: androidx.fragment.app.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0255w extends B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Fragment f7743a;

    public C0255w(Fragment fragment) {
        this.f7743a = fragment;
    }

    @Override // androidx.fragment.app.B
    public final void a() {
        Fragment fragment = this.f7743a;
        fragment.mSavedStateRegistryController.a();
        androidx.lifecycle.K.e(fragment);
        Bundle bundle = fragment.mSavedFragmentState;
        fragment.mSavedStateRegistryController.b(bundle != null ? bundle.getBundle("registryState") : null);
    }
}
