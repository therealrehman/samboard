package androidx.fragment.app;

import c.AbstractC0431a;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: renamed from: androidx.fragment.app.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0258z extends B {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ C0257y f7749a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ AtomicReference f7750b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ AbstractC0431a f7751c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ androidx.activity.result.b f7752d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Fragment f7753e;

    public C0258z(Fragment fragment, C0257y c0257y, AtomicReference atomicReference, AbstractC0431a abstractC0431a, androidx.activity.result.b bVar) {
        this.f7753e = fragment;
        this.f7749a = c0257y;
        this.f7750b = atomicReference;
        this.f7751c = abstractC0431a;
        this.f7752d = bVar;
    }

    @Override // androidx.fragment.app.B
    public final void a() {
        Fragment fragment = this.f7753e;
        this.f7750b.set(this.f7749a.a().c(fragment.generateActivityResultKey(), fragment, this.f7751c, this.f7752d));
    }
}
