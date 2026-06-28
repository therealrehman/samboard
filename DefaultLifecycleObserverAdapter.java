package androidx.lifecycle;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Landroidx/lifecycle/DefaultLifecycleObserverAdapter;", "Landroidx/lifecycle/q;", "lifecycle-common"}, k = 1, mv = {1, 8, 0})
public final class DefaultLifecycleObserverAdapter implements InterfaceC0275q {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final DefaultLifecycleObserver f7755e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final InterfaceC0275q f7756f;

    public DefaultLifecycleObserverAdapter(DefaultLifecycleObserver defaultLifecycleObserver, InterfaceC0275q interfaceC0275q) {
        kotlin.jvm.internal.j.f(defaultLifecycleObserver, "defaultLifecycleObserver");
        this.f7755e = defaultLifecycleObserver;
        this.f7756f = interfaceC0275q;
    }

    @Override // androidx.lifecycle.InterfaceC0275q
    public final void a(InterfaceC0276s interfaceC0276s, EnumC0270l enumC0270l) {
        int i5 = AbstractC0263e.f7816a[enumC0270l.ordinal()];
        DefaultLifecycleObserver defaultLifecycleObserver = this.f7755e;
        switch (i5) {
            case 1:
                defaultLifecycleObserver.onCreate(interfaceC0276s);
                break;
            case 2:
                defaultLifecycleObserver.onStart(interfaceC0276s);
                break;
            case 3:
                defaultLifecycleObserver.onResume(interfaceC0276s);
                break;
            case 4:
                defaultLifecycleObserver.onPause(interfaceC0276s);
                break;
            case 5:
                defaultLifecycleObserver.onStop(interfaceC0276s);
                break;
            case 6:
                defaultLifecycleObserver.onDestroy(interfaceC0276s);
                break;
            case 7:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        InterfaceC0275q interfaceC0275q = this.f7756f;
        if (interfaceC0275q != null) {
            interfaceC0275q.a(interfaceC0276s, enumC0270l);
        }
    }
}
