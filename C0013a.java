package A8;

import h6.AbstractC0582a;
import y8.AbstractC1274x;
import y8.C1259h;
import y8.InterfaceC1258g;

/* JADX INFO: renamed from: A8.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0013a extends y {
    public final InterfaceC1258g h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f124i = 1;

    public C0013a(C1259h c1259h) {
        this.h = c1259h;
    }

    @Override // A8.A
    public final kotlinx.coroutines.internal.u a(Object obj) {
        if (((C1259h) this.h).w(this.f124i == 1 ? new q(obj) : obj, null, t(obj)) == null) {
            return null;
        }
        return AbstractC1274x.f15245a;
    }

    @Override // A8.A
    public final void c(Object obj) {
        C1259h c1259h = (C1259h) this.h;
        c1259h.k(c1259h.f15183g);
    }

    @Override // kotlinx.coroutines.internal.j
    public final String toString() {
        StringBuilder sb = new StringBuilder("ReceiveElement@");
        sb.append(AbstractC1274x.c(this));
        sb.append("[receiveMode=");
        return l.u(sb, this.f124i, ']');
    }

    @Override // A8.y
    public final void u(r rVar) {
        int i5 = this.f124i;
        InterfaceC1258g interfaceC1258g = this.h;
        if (i5 == 1) {
            ((C1259h) interfaceC1258g).resumeWith(new q(new o(rVar.h)));
        } else {
            ((C1259h) interfaceC1258g).resumeWith(AbstractC0582a.y(rVar.y()));
        }
    }
}
