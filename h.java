package com.bumptech.glide;

import F8.n;
import b2.p;
import b2.q;
import b2.r;
import b2.s;
import b2.u;
import b2.v;
import e2.C;
import h1.C0570c;
import j2.InterfaceC0614a;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import m2.C0738a;
import m2.C0739b;
import m2.C0740c;
import p5.C0828d;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final s f9946a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final n f9947b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final M3.h f9948c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C0570c f9949d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final com.bumptech.glide.load.data.i f9950e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final n f9951f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final l4.j f9952g;
    public final M3.e h = new M3.e(26);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C0739b f9953i = new C0739b();

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final C0828d f9954j;

    public h() {
        C0828d c0828d = new C0828d(new K.f(20), new C(23), new C(24), 20, false);
        this.f9954j = c0828d;
        this.f9946a = new s(c0828d);
        this.f9947b = new n(2);
        this.f9948c = new M3.h(29);
        this.f9949d = new C0570c(2, false);
        this.f9950e = new com.bumptech.glide.load.data.i();
        this.f9951f = new n(1);
        this.f9952g = new l4.j(1);
        List listAsList = Arrays.asList("Gif", "Bitmap", "BitmapDrawable");
        ArrayList arrayList = new ArrayList(listAsList.size());
        arrayList.add("legacy_prepend_all");
        Iterator it = listAsList.iterator();
        while (it.hasNext()) {
            arrayList.add((String) it.next());
        }
        arrayList.add("legacy_append");
        M3.h hVar = this.f9948c;
        synchronized (hVar) {
            try {
                ArrayList<String> arrayList2 = new ArrayList((ArrayList) hVar.f2058f);
                ((ArrayList) hVar.f2058f).clear();
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    ((ArrayList) hVar.f2058f).add((String) it2.next());
                }
                for (String str : arrayList2) {
                    if (!arrayList.contains(str)) {
                        ((ArrayList) hVar.f2058f).add(str);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void a(Class cls, V1.b bVar) {
        n nVar = this.f9947b;
        synchronized (nVar) {
            nVar.f931a.add(new C0738a(cls, bVar));
        }
    }

    public final void b(Class cls, V1.k kVar) {
        C0570c c0570c = this.f9949d;
        synchronized (c0570c) {
            c0570c.f11254a.add(new m2.d(cls, kVar));
        }
    }

    public final void c(Class cls, Class cls2, q qVar) {
        s sVar = this.f9946a;
        synchronized (sVar) {
            v vVar = sVar.f9736a;
            synchronized (vVar) {
                u uVar = new u(cls, cls2, qVar);
                ArrayList arrayList = vVar.f9749a;
                arrayList.add(arrayList.size(), uVar);
            }
            ((HashMap) sVar.f9737b.f286f).clear();
        }
    }

    public final void d(String str, Class cls, Class cls2, V1.j jVar) {
        M3.h hVar = this.f9948c;
        synchronized (hVar) {
            hVar.S0(str).add(new C0740c(cls, cls2, jVar));
        }
    }

    public final ArrayList e() {
        ArrayList arrayList;
        l4.j jVar = this.f9952g;
        synchronized (jVar) {
            arrayList = (ArrayList) jVar.f12026f;
        }
        if (arrayList.isEmpty()) {
            throw new g("Failed to find image header parser.");
        }
        return arrayList;
    }

    public final List f(Object obj) {
        List listUnmodifiableList;
        s sVar = this.f9946a;
        sVar.getClass();
        Class<?> cls = obj.getClass();
        synchronized (sVar) {
            r rVar = (r) ((HashMap) sVar.f9737b.f286f).get(cls);
            listUnmodifiableList = rVar == null ? null : rVar.f9735a;
            if (listUnmodifiableList == null) {
                listUnmodifiableList = Collections.unmodifiableList(sVar.f9736a.c(cls));
                if (((r) ((HashMap) sVar.f9737b.f286f).put(cls, new r(listUnmodifiableList))) != null) {
                    throw new IllegalStateException("Already cached loaders for model: " + cls);
                }
            }
        }
        if (listUnmodifiableList.isEmpty()) {
            throw new g("Failed to find any ModelLoaders registered for model class: " + obj.getClass());
        }
        int size = listUnmodifiableList.size();
        List listEmptyList = Collections.emptyList();
        boolean z9 = true;
        for (int i5 = 0; i5 < size; i5++) {
            p pVar = (p) listUnmodifiableList.get(i5);
            if (pVar.b(obj)) {
                if (z9) {
                    listEmptyList = new ArrayList(size - i5);
                    z9 = false;
                }
                listEmptyList.add(pVar);
            }
        }
        if (!listEmptyList.isEmpty()) {
            return listEmptyList;
        }
        throw new g("Found ModelLoaders for model class: " + listUnmodifiableList + ", but none that handle this specific model instance: " + obj);
    }

    public final void g(com.bumptech.glide.load.data.f fVar) {
        com.bumptech.glide.load.data.i iVar = this.f9950e;
        synchronized (iVar) {
            ((HashMap) iVar.f9993f).put(fVar.a(), fVar);
        }
    }

    public final void h(Class cls, Class cls2, InterfaceC0614a interfaceC0614a) {
        n nVar = this.f9951f;
        synchronized (nVar) {
            nVar.f931a.add(new j2.b(cls, cls2, interfaceC0614a));
        }
    }
}
