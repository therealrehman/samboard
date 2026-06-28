package androidx.fragment.app;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: renamed from: androidx.fragment.app.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0253u extends androidx.activity.result.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ AtomicReference f7730a;

    public C0253u(AtomicReference atomicReference) {
        this.f7730a = atomicReference;
    }

    @Override // androidx.activity.result.c
    public final void a(Object obj) {
        androidx.activity.result.c cVar = (androidx.activity.result.c) this.f7730a.get();
        if (cVar == null) {
            throw new IllegalStateException("Operation cannot be started before fragment is in created state");
        }
        cVar.a(obj);
    }
}
