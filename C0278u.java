package androidx.lifecycle;

import android.os.Looper;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import m.C0731a;
import n.C0782a;

/* JADX INFO: renamed from: androidx.lifecycle.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0278u extends AbstractC0272n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f7826a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public C0782a f7827b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public EnumC0271m f7828c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WeakReference f7829d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7830e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f7831f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f7832g;
    public final ArrayList h;

    public C0278u(InterfaceC0276s provider) {
        kotlin.jvm.internal.j.f(provider, "provider");
        new AtomicReference();
        this.f7826a = true;
        this.f7827b = new C0782a();
        this.f7828c = EnumC0271m.f7819f;
        this.h = new ArrayList();
        this.f7829d = new WeakReference(provider);
    }

    @Override // androidx.lifecycle.AbstractC0272n
    public final void a(r observer) {
        InterfaceC0275q reflectiveGenericLifecycleObserver;
        InterfaceC0276s interfaceC0276s;
        ArrayList arrayList = this.h;
        kotlin.jvm.internal.j.f(observer, "observer");
        d("addObserver");
        EnumC0271m enumC0271m = this.f7828c;
        EnumC0271m enumC0271m2 = EnumC0271m.f7818e;
        if (enumC0271m != enumC0271m2) {
            enumC0271m2 = EnumC0271m.f7819f;
        }
        C0277t c0277t = new C0277t();
        HashMap map = AbstractC0279v.f7833a;
        boolean z9 = observer instanceof InterfaceC0275q;
        boolean z10 = observer instanceof DefaultLifecycleObserver;
        if (z9 && z10) {
            reflectiveGenericLifecycleObserver = new DefaultLifecycleObserverAdapter((DefaultLifecycleObserver) observer, (InterfaceC0275q) observer);
        } else if (z10) {
            reflectiveGenericLifecycleObserver = new DefaultLifecycleObserverAdapter((DefaultLifecycleObserver) observer, null);
        } else if (z9) {
            reflectiveGenericLifecycleObserver = (InterfaceC0275q) observer;
        } else {
            Class<?> cls = observer.getClass();
            if (AbstractC0279v.b(cls) == 2) {
                Object obj = AbstractC0279v.f7834b.get(cls);
                kotlin.jvm.internal.j.c(obj);
                List list = (List) obj;
                if (list.size() == 1) {
                    AbstractC0279v.a((Constructor) list.get(0), observer);
                    throw null;
                }
                int size = list.size();
                InterfaceC0265g[] interfaceC0265gArr = new InterfaceC0265g[size];
                if (size > 0) {
                    AbstractC0279v.a((Constructor) list.get(0), observer);
                    throw null;
                }
                reflectiveGenericLifecycleObserver = new CompositeGeneratedAdaptersObserver(interfaceC0265gArr);
            } else {
                reflectiveGenericLifecycleObserver = new ReflectiveGenericLifecycleObserver(observer);
            }
        }
        c0277t.f7825b = reflectiveGenericLifecycleObserver;
        c0277t.f7824a = enumC0271m2;
        if (((C0277t) this.f7827b.i(observer, c0277t)) == null && (interfaceC0276s = (InterfaceC0276s) this.f7829d.get()) != null) {
            boolean z11 = this.f7830e != 0 || this.f7831f;
            EnumC0271m enumC0271mC = c(observer);
            this.f7830e++;
            while (c0277t.f7824a.compareTo(enumC0271mC) < 0 && this.f7827b.f12320i.containsKey(observer)) {
                arrayList.add(c0277t.f7824a);
                C0268j c0268j = EnumC0270l.Companion;
                EnumC0271m enumC0271m3 = c0277t.f7824a;
                c0268j.getClass();
                EnumC0270l enumC0270lB = C0268j.b(enumC0271m3);
                if (enumC0270lB == null) {
                    throw new IllegalStateException("no event up from " + c0277t.f7824a);
                }
                c0277t.a(interfaceC0276s, enumC0270lB);
                arrayList.remove(arrayList.size() - 1);
                enumC0271mC = c(observer);
            }
            if (!z11) {
                h();
            }
            this.f7830e--;
        }
    }

    @Override // androidx.lifecycle.AbstractC0272n
    public final void b(r observer) {
        kotlin.jvm.internal.j.f(observer, "observer");
        d("removeObserver");
        this.f7827b.h(observer);
    }

    public final EnumC0271m c(r rVar) {
        C0277t c0277t;
        HashMap map = this.f7827b.f12320i;
        n.c cVar = map.containsKey(rVar) ? ((n.c) map.get(rVar)).h : null;
        EnumC0271m enumC0271m = (cVar == null || (c0277t = (C0277t) cVar.f12325f) == null) ? null : c0277t.f7824a;
        ArrayList arrayList = this.h;
        EnumC0271m enumC0271m2 = arrayList.isEmpty() ^ true ? (EnumC0271m) arrayList.get(arrayList.size() - 1) : null;
        EnumC0271m state1 = this.f7828c;
        kotlin.jvm.internal.j.f(state1, "state1");
        if (enumC0271m == null || enumC0271m.compareTo(state1) >= 0) {
            enumC0271m = state1;
        }
        return (enumC0271m2 == null || enumC0271m2.compareTo(enumC0271m) >= 0) ? enumC0271m : enumC0271m2;
    }

    public final void d(String str) {
        if (this.f7826a) {
            C0731a.c0().f12149c.getClass();
            if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
                throw new IllegalStateException(A8.l.t("Method ", str, " must be called on the main thread").toString());
            }
        }
    }

    public final void e(EnumC0270l event) {
        kotlin.jvm.internal.j.f(event, "event");
        d("handleLifecycleEvent");
        f(event.a());
    }

    public final void f(EnumC0271m enumC0271m) {
        EnumC0271m enumC0271m2 = this.f7828c;
        if (enumC0271m2 == enumC0271m) {
            return;
        }
        EnumC0271m enumC0271m3 = EnumC0271m.f7819f;
        EnumC0271m enumC0271m4 = EnumC0271m.f7818e;
        if (enumC0271m2 == enumC0271m3 && enumC0271m == enumC0271m4) {
            throw new IllegalStateException(("no event down from " + this.f7828c + " in component " + this.f7829d.get()).toString());
        }
        this.f7828c = enumC0271m;
        if (this.f7831f || this.f7830e != 0) {
            this.f7832g = true;
            return;
        }
        this.f7831f = true;
        h();
        this.f7831f = false;
        if (this.f7828c == enumC0271m4) {
            this.f7827b = new C0782a();
        }
    }

    public final void g() {
        EnumC0271m enumC0271m = EnumC0271m.f7820g;
        d("setCurrentState");
        f(enumC0271m);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0030, code lost:
    
        r7.f7832g = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0032, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void h() {
        /*
            Method dump skipped, instruction units count: 367
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.C0278u.h():void");
    }
}
