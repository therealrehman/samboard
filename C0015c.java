package A8;

import g7.InterfaceC0562b;
import y8.AbstractC1274x;
import y8.C1259h;
import y8.C1266o;
import y8.InterfaceC1258g;

/* JADX INFO: renamed from: A8.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0015c extends y {
    public final M3.e h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final InterfaceC1258g f126i;

    public C0015c(M3.e eVar, C1259h c1259h) {
        this.h = eVar;
        this.f126i = c1259h;
    }

    @Override // A8.A
    public final kotlinx.coroutines.internal.u a(Object obj) {
        if (((C1259h) this.f126i).w(Boolean.TRUE, null, t(obj)) == null) {
            return null;
        }
        return AbstractC1274x.f15245a;
    }

    @Override // A8.A
    public final void c(Object obj) {
        this.h.f2049g = obj;
        C1259h c1259h = (C1259h) this.f126i;
        c1259h.k(c1259h.f15183g);
    }

    @Override // A8.y
    public final InterfaceC0562b t(Object obj) {
        InterfaceC0562b interfaceC0562b = ((g) this.h.f2048f).f141e;
        if (interfaceC0562b != null) {
            return new kotlinx.coroutines.internal.o(0, interfaceC0562b, obj, ((C1259h) this.f126i).f15216i);
        }
        return null;
    }

    @Override // kotlinx.coroutines.internal.j
    public final String toString() {
        return "ReceiveHasNext@" + AbstractC1274x.c(this);
    }

    @Override // A8.y
    public final void u(r rVar) {
        kotlinx.coroutines.internal.u uVarW;
        Throwable th = rVar.h;
        InterfaceC1258g interfaceC1258g = this.f126i;
        if (th == null) {
            uVarW = ((C1259h) interfaceC1258g).w(Boolean.FALSE, null, null);
        } else {
            Throwable thY = rVar.y();
            C1259h c1259h = (C1259h) interfaceC1258g;
            c1259h.getClass();
            uVarW = c1259h.w(new C1266o(thY, false), null, null);
        }
        if (uVarW != null) {
            this.h.f2049g = rVar;
            C1259h c1259h2 = (C1259h) interfaceC1258g;
            c1259h2.k(c1259h2.f15183g);
        }
    }
}
