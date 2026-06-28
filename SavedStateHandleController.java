package androidx.lifecycle;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Landroidx/lifecycle/SavedStateHandleController;", "Landroidx/lifecycle/q;", "lifecycle-viewmodel-savedstate_release"}, k = 1, mv = {1, 8, 0})
public final class SavedStateHandleController implements InterfaceC0275q {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f7804e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final I f7805f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f7806g;

    public SavedStateHandleController(String str, I i5) {
        this.f7804e = str;
        this.f7805f = i5;
    }

    @Override // androidx.lifecycle.InterfaceC0275q
    public final void a(InterfaceC0276s interfaceC0276s, EnumC0270l enumC0270l) {
        if (enumC0270l == EnumC0270l.ON_DESTROY) {
            this.f7806g = false;
            interfaceC0276s.getLifecycle().b(this);
        }
    }

    public final void b(U0.e registry, AbstractC0272n lifecycle) {
        kotlin.jvm.internal.j.f(registry, "registry");
        kotlin.jvm.internal.j.f(lifecycle, "lifecycle");
        if (!(!this.f7806g)) {
            throw new IllegalStateException("Already attached to lifecycleOwner".toString());
        }
        this.f7806g = true;
        lifecycle.a(this);
        registry.d(this.f7804e, this.f7805f.f7769e);
    }
}
