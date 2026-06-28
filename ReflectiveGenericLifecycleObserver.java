package androidx.lifecycle;

import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
class ReflectiveGenericLifecycleObserver implements InterfaceC0275q {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f7797e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C0260b f7798f;

    public ReflectiveGenericLifecycleObserver(Object obj) {
        this.f7797e = obj;
        C0262d c0262d = C0262d.f7813c;
        Class<?> cls = obj.getClass();
        C0260b c0260b = (C0260b) c0262d.f7814a.get(cls);
        this.f7798f = c0260b == null ? c0262d.a(cls, null) : c0260b;
    }

    @Override // androidx.lifecycle.InterfaceC0275q
    public final void a(InterfaceC0276s interfaceC0276s, EnumC0270l enumC0270l) {
        HashMap map = this.f7798f.f7809a;
        List list = (List) map.get(enumC0270l);
        Object obj = this.f7797e;
        C0260b.a(list, interfaceC0276s, enumC0270l, obj);
        C0260b.a((List) map.get(EnumC0270l.ON_ANY), interfaceC0276s, enumC0270l, obj);
    }
}
