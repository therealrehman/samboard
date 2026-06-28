package androidx.core.view;

import android.view.Menu;
import androidx.lifecycle.AbstractC0272n;
import androidx.lifecycle.C0268j;
import androidx.lifecycle.EnumC0270l;
import androidx.lifecycle.EnumC0271m;
import androidx.lifecycle.InterfaceC0275q;
import androidx.lifecycle.InterfaceC0276s;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: renamed from: androidx.core.view.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0219k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Runnable f7235a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CopyOnWriteArrayList f7236b = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashMap f7237c = new HashMap();

    public C0219k(Runnable runnable) {
        this.f7235a = runnable;
    }

    public final void a(final InterfaceC0221m interfaceC0221m, InterfaceC0276s interfaceC0276s) {
        this.f7236b.add(interfaceC0221m);
        this.f7235a.run();
        AbstractC0272n lifecycle = interfaceC0276s.getLifecycle();
        HashMap map = this.f7237c;
        C0218j c0218j = (C0218j) map.remove(interfaceC0221m);
        if (c0218j != null) {
            c0218j.f7229a.b(c0218j.f7230b);
            c0218j.f7230b = null;
        }
        map.put(interfaceC0221m, new C0218j(lifecycle, new InterfaceC0275q() { // from class: androidx.core.view.i
            @Override // androidx.lifecycle.InterfaceC0275q
            public final void a(InterfaceC0276s interfaceC0276s2, EnumC0270l enumC0270l) {
                EnumC0270l enumC0270l2 = EnumC0270l.ON_DESTROY;
                C0219k c0219k = this.f7227e;
                if (enumC0270l == enumC0270l2) {
                    c0219k.d(interfaceC0221m);
                } else {
                    c0219k.getClass();
                }
            }
        }));
    }

    public final void b(final InterfaceC0221m interfaceC0221m, InterfaceC0276s interfaceC0276s, final EnumC0271m enumC0271m) {
        AbstractC0272n lifecycle = interfaceC0276s.getLifecycle();
        HashMap map = this.f7237c;
        C0218j c0218j = (C0218j) map.remove(interfaceC0221m);
        if (c0218j != null) {
            c0218j.f7229a.b(c0218j.f7230b);
            c0218j.f7230b = null;
        }
        map.put(interfaceC0221m, new C0218j(lifecycle, new InterfaceC0275q() { // from class: androidx.core.view.h
            @Override // androidx.lifecycle.InterfaceC0275q
            public final void a(InterfaceC0276s interfaceC0276s2, EnumC0270l enumC0270l) {
                C0219k c0219k = this.f7222e;
                c0219k.getClass();
                EnumC0270l.Companion.getClass();
                EnumC0271m enumC0271m2 = enumC0271m;
                EnumC0270l enumC0270lC = C0268j.c(enumC0271m2);
                Runnable runnable = c0219k.f7235a;
                CopyOnWriteArrayList copyOnWriteArrayList = c0219k.f7236b;
                InterfaceC0221m interfaceC0221m2 = interfaceC0221m;
                if (enumC0270l == enumC0270lC) {
                    copyOnWriteArrayList.add(interfaceC0221m2);
                    runnable.run();
                } else if (enumC0270l == EnumC0270l.ON_DESTROY) {
                    c0219k.d(interfaceC0221m2);
                } else if (enumC0270l == C0268j.a(enumC0271m2)) {
                    copyOnWriteArrayList.remove(interfaceC0221m2);
                    runnable.run();
                }
            }
        }));
    }

    public final void c(Menu menu) {
        Iterator it = this.f7236b.iterator();
        while (it.hasNext()) {
            ((InterfaceC0221m) it.next()).d(menu);
        }
    }

    public final void d(InterfaceC0221m interfaceC0221m) {
        this.f7236b.remove(interfaceC0221m);
        C0218j c0218j = (C0218j) this.f7237c.remove(interfaceC0221m);
        if (c0218j != null) {
            c0218j.f7229a.b(c0218j.f7230b);
            c0218j.f7230b = null;
        }
        this.f7235a.run();
    }
}
