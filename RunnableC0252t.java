package androidx.fragment.app;

import java.util.ArrayList;

/* JADX INFO: renamed from: androidx.fragment.app.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC0252t implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f7727e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f7728f;

    public /* synthetic */ RunnableC0252t(int i5, Object obj) {
        this.f7727e = i5;
        this.f7728f = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f7727e) {
            case 0:
                Fragment fragment = (Fragment) this.f7728f;
                s0 s0Var = fragment.mViewLifecycleOwner;
                s0Var.f7726i.b(fragment.mSavedViewRegistryState);
                fragment.mSavedViewRegistryState = null;
                break;
            default:
                ArrayList transitioningViews = (ArrayList) this.f7728f;
                kotlin.jvm.internal.j.f(transitioningViews, "$transitioningViews");
                k0.a(4, transitioningViews);
                break;
        }
    }
}
