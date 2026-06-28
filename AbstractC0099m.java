package V7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import k6.C0651a;

/* JADX INFO: renamed from: V7.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0099m extends AbstractC0102p {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0096j f5126e;

    public AbstractC0099m() {
        this.f5126e = new C0096j();
    }

    public final boolean i() {
        int i5 = 0;
        while (true) {
            E e3 = this.f5126e.f5120a;
            if (i5 >= e3.f5065f.size()) {
                Iterator it = e3.c().iterator();
                while (it.hasNext()) {
                    if (!C0096j.e((Map.Entry) it.next())) {
                        return false;
                    }
                }
                return true;
            }
            if (!C0096j.e((Map.Entry) e3.f5065f.get(i5))) {
                return false;
            }
            i5++;
        }
    }

    public final int j() {
        E e3;
        int i5 = 0;
        int iD = 0;
        while (true) {
            e3 = this.f5126e.f5120a;
            if (i5 >= e3.f5065f.size()) {
                break;
            }
            Map.Entry entry = (Map.Entry) e3.f5065f.get(i5);
            iD += C0096j.d((C0100n) entry.getKey(), entry.getValue());
            i5++;
        }
        for (Map.Entry entry2 : e3.c()) {
            iD += C0096j.d((C0100n) entry2.getKey(), entry2.getValue());
        }
        return iD;
    }

    public final Object k(C0101o c0101o) {
        p(c0101o);
        E e3 = this.f5126e.f5120a;
        C0100n c0100n = c0101o.f5133d;
        Object obj = e3.get(c0100n);
        if (obj == null) {
            return c0101o.f5131b;
        }
        if (!c0100n.f5129g) {
            return c0101o.a(obj);
        }
        if (c0100n.f5128f.f5084e != T.ENUM) {
            return obj;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = ((List) obj).iterator();
        while (it.hasNext()) {
            arrayList.add(c0101o.a(it.next()));
        }
        return arrayList;
    }

    public final boolean l(C0101o c0101o) {
        p(c0101o);
        C0096j c0096j = this.f5126e;
        c0096j.getClass();
        C0100n c0100n = c0101o.f5133d;
        if (c0100n.f5129g) {
            throw new IllegalArgumentException("hasField() can only be called on non-repeated fields.");
        }
        return c0096j.f5120a.get(c0100n) != null;
    }

    public final void m() {
        this.f5126e.f();
    }

    public final C0651a n() {
        return new C0651a(this);
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean o(V7.C0092f r10, V7.C0093g r11, V7.C0095i r12, int r13) throws V7.C0105t {
        /*
            Method dump skipped, instruction units count: 284
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: V7.AbstractC0099m.o(V7.f, V7.g, V7.i, int):boolean");
    }

    public final void p(C0101o c0101o) {
        if (c0101o.f5130a != a()) {
            throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
        }
    }

    public AbstractC0099m(AbstractC0098l abstractC0098l) {
        abstractC0098l.f5124f.f();
        abstractC0098l.f5125g = false;
        this.f5126e = abstractC0098l.f5124f;
    }
}
