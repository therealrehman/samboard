package androidx.lifecycle;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Landroidx/lifecycle/SavedStateHandleAttacher;", "Landroidx/lifecycle/q;", "lifecycle-viewmodel-savedstate_release"}, k = 1, mv = {1, 8, 0})
public final class SavedStateHandleAttacher implements InterfaceC0275q {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final L f7803e;

    public SavedStateHandleAttacher(L l2) {
        this.f7803e = l2;
    }

    @Override // androidx.lifecycle.InterfaceC0275q
    public final void a(InterfaceC0276s interfaceC0276s, EnumC0270l enumC0270l) {
        if (enumC0270l == EnumC0270l.ON_CREATE) {
            interfaceC0276s.getLifecycle().b(this);
            this.f7803e.b();
        } else {
            throw new IllegalStateException(("Next event must be ON_CREATE, it was " + enumC0270l).toString());
        }
    }
}
