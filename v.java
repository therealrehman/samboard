package b2;

import java.util.ArrayList;
import java.util.HashSet;
import p5.C0828d;

/* JADX INFO: loaded from: classes.dex */
public final class v {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final z f9747e = new z(10);

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C0407A f9748f = new C0407A(2);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f9749a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final z f9750b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashSet f9751c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final K.d f9752d;

    public v(C0828d c0828d) {
        z zVar = f9747e;
        this.f9749a = new ArrayList();
        this.f9751c = new HashSet();
        this.f9752d = c0828d;
        this.f9750b = zVar;
    }

    public final p a(u uVar) {
        return uVar.f9746c.B(this);
    }

    public final synchronized p b(Class cls, Class cls2) {
        try {
            ArrayList arrayList = new ArrayList();
            boolean z9 = false;
            for (u uVar : this.f9749a) {
                if (this.f9751c.contains(uVar)) {
                    z9 = true;
                } else if (uVar.f9744a.isAssignableFrom(cls) && uVar.f9745b.isAssignableFrom(cls2)) {
                    this.f9751c.add(uVar);
                    arrayList.add(a(uVar));
                    this.f9751c.remove(uVar);
                }
            }
            if (arrayList.size() > 1) {
                z zVar = this.f9750b;
                K.d dVar = this.f9752d;
                zVar.getClass();
                return new C0412b(1, arrayList, dVar);
            }
            if (arrayList.size() == 1) {
                return (p) arrayList.get(0);
            }
            if (z9) {
                return f9748f;
            }
            throw new com.bumptech.glide.g("Failed to find any ModelLoaders for model: " + cls + " and data: " + cls2);
        } catch (Throwable th) {
            this.f9751c.clear();
            throw th;
        }
    }

    public final synchronized ArrayList c(Class cls) {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList();
            for (u uVar : this.f9749a) {
                if (!this.f9751c.contains(uVar) && uVar.f9744a.isAssignableFrom(cls)) {
                    this.f9751c.add(uVar);
                    arrayList.add(uVar.f9746c.B(this));
                    this.f9751c.remove(uVar);
                }
            }
        } catch (Throwable th) {
            this.f9751c.clear();
            throw th;
        }
        return arrayList;
    }

    public final synchronized ArrayList d(Class cls) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        for (u uVar : this.f9749a) {
            if (!arrayList.contains(uVar.f9745b) && uVar.f9744a.isAssignableFrom(cls)) {
                arrayList.add(uVar.f9745b);
            }
        }
        return arrayList;
    }
}
