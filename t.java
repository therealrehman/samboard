package b2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class t implements com.bumptech.glide.load.data.e, com.bumptech.glide.load.data.d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f9738e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final K.d f9739f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f9740g;
    public com.bumptech.glide.f h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public com.bumptech.glide.load.data.d f9741i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public List f9742j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f9743k;

    public t(ArrayList arrayList, K.d dVar) {
        this.f9739f = dVar;
        if (arrayList.isEmpty()) {
            throw new IllegalArgumentException("Must not be empty.");
        }
        this.f9738e = arrayList;
        this.f9740g = 0;
    }

    @Override // com.bumptech.glide.load.data.e
    public final Class a() {
        return ((com.bumptech.glide.load.data.e) this.f9738e.get(0)).a();
    }

    @Override // com.bumptech.glide.load.data.e
    public final void b() {
        List list = this.f9742j;
        if (list != null) {
            this.f9739f.c(list);
        }
        this.f9742j = null;
        Iterator it = this.f9738e.iterator();
        while (it.hasNext()) {
            ((com.bumptech.glide.load.data.e) it.next()).b();
        }
    }

    @Override // com.bumptech.glide.load.data.d
    public final void c(Exception exc) {
        List list = this.f9742j;
        r2.f.c(list, "Argument must not be null");
        list.add(exc);
        g();
    }

    @Override // com.bumptech.glide.load.data.e
    public final void cancel() {
        this.f9743k = true;
        Iterator it = this.f9738e.iterator();
        while (it.hasNext()) {
            ((com.bumptech.glide.load.data.e) it.next()).cancel();
        }
    }

    @Override // com.bumptech.glide.load.data.d
    public final void d(Object obj) {
        if (obj != null) {
            this.f9741i.d(obj);
        } else {
            g();
        }
    }

    @Override // com.bumptech.glide.load.data.e
    public final int e() {
        return ((com.bumptech.glide.load.data.e) this.f9738e.get(0)).e();
    }

    @Override // com.bumptech.glide.load.data.e
    public final void f(com.bumptech.glide.f fVar, com.bumptech.glide.load.data.d dVar) {
        this.h = fVar;
        this.f9741i = dVar;
        this.f9742j = (List) this.f9739f.h();
        ((com.bumptech.glide.load.data.e) this.f9738e.get(this.f9740g)).f(fVar, this);
        if (this.f9743k) {
            cancel();
        }
    }

    public final void g() {
        if (this.f9743k) {
            return;
        }
        if (this.f9740g < this.f9738e.size() - 1) {
            this.f9740g++;
            f(this.h, this.f9741i);
        } else {
            r2.f.b(this.f9742j);
            this.f9741i.c(new X1.x("Fetch failed", new ArrayList(this.f9742j)));
        }
    }
}
