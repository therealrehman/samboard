package X1;

import java.io.File;
import java.util.List;

/* JADX INFO: renamed from: X1.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0109d implements g, com.bumptech.glide.load.data.d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f5501e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final h f5502f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final InterfaceC0111f f5503g;
    public int h = -1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public V1.e f5504i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public List f5505j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f5506k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile b2.o f5507l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public File f5508m;

    public C0109d(List list, h hVar, InterfaceC0111f interfaceC0111f) {
        this.f5501e = list;
        this.f5502f = hVar;
        this.f5503g = interfaceC0111f;
    }

    @Override // com.bumptech.glide.load.data.d
    public final void c(Exception exc) {
        this.f5503g.b(this.f5504i, exc, this.f5507l.f9734c, 3);
    }

    @Override // X1.g
    public final void cancel() {
        b2.o oVar = this.f5507l;
        if (oVar != null) {
            oVar.f9734c.cancel();
        }
    }

    @Override // com.bumptech.glide.load.data.d
    public final void d(Object obj) {
        this.f5503g.c(this.f5504i, obj, this.f5507l.f9734c, 3, this.f5504i);
    }

    @Override // X1.g
    public final boolean e() {
        while (true) {
            List list = this.f5505j;
            boolean z9 = false;
            if (list != null && this.f5506k < list.size()) {
                this.f5507l = null;
                while (!z9 && this.f5506k < this.f5505j.size()) {
                    List list2 = this.f5505j;
                    int i5 = this.f5506k;
                    this.f5506k = i5 + 1;
                    b2.p pVar = (b2.p) list2.get(i5);
                    File file = this.f5508m;
                    h hVar = this.f5502f;
                    this.f5507l = pVar.a(file, hVar.f5515e, hVar.f5516f, hVar.f5518i);
                    if (this.f5507l != null && this.f5502f.c(this.f5507l.f9734c.a()) != null) {
                        this.f5507l.f9734c.f(this.f5502f.o, this);
                        z9 = true;
                    }
                }
                return z9;
            }
            int i7 = this.h + 1;
            this.h = i7;
            if (i7 >= this.f5501e.size()) {
                return false;
            }
            V1.e eVar = (V1.e) this.f5501e.get(this.h);
            h hVar2 = this.f5502f;
            File fileB = hVar2.h.a().b(new C0110e(eVar, hVar2.f5523n));
            this.f5508m = fileB;
            if (fileB != null) {
                this.f5504i = eVar;
                this.f5505j = this.f5502f.f5513c.f9934b.f(fileB);
                this.f5506k = 0;
            }
        }
    }
}
