package androidx.recyclerview.widget;

/* JADX INFO: loaded from: classes.dex */
public final class o1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q.k f9205a = new q.k();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q.e f9206b = new q.e();

    public final void a(V0 v02, C0356r0 c0356r0) {
        q.k kVar = this.f9205a;
        m1 m1VarA = (m1) kVar.getOrDefault(v02, null);
        if (m1VarA == null) {
            m1VarA = m1.a();
            kVar.put(v02, m1VarA);
        }
        m1VarA.f9195c = c0356r0;
        m1VarA.f9193a |= 8;
    }

    public final C0356r0 b(V0 v02, int i5) {
        m1 m1Var;
        C0356r0 c0356r0;
        q.k kVar = this.f9205a;
        int iE = kVar.e(v02);
        if (iE >= 0 && (m1Var = (m1) kVar.l(iE)) != null) {
            int i7 = m1Var.f9193a;
            if ((i7 & i5) != 0) {
                int i9 = i7 & (~i5);
                m1Var.f9193a = i9;
                if (i5 == 4) {
                    c0356r0 = m1Var.f9194b;
                } else {
                    if (i5 != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    c0356r0 = m1Var.f9195c;
                }
                if ((i9 & 12) == 0) {
                    kVar.j(iE);
                    m1Var.f9193a = 0;
                    m1Var.f9194b = null;
                    m1Var.f9195c = null;
                    m1.f9192d.c(m1Var);
                }
                return c0356r0;
            }
        }
        return null;
    }

    public final void c(V0 v02) {
        m1 m1Var = (m1) this.f9205a.getOrDefault(v02, null);
        if (m1Var == null) {
            return;
        }
        m1Var.f9193a &= -2;
    }

    public final void d(V0 v02) {
        q.e eVar = this.f9206b;
        int iF = eVar.f() - 1;
        while (true) {
            if (iF < 0) {
                break;
            }
            if (v02 == eVar.g(iF)) {
                Object[] objArr = eVar.f12908g;
                Object obj = objArr[iF];
                Object obj2 = q.e.f12905i;
                if (obj != obj2) {
                    objArr[iF] = obj2;
                    eVar.f12906e = true;
                }
            } else {
                iF--;
            }
        }
        m1 m1Var = (m1) this.f9205a.remove(v02);
        if (m1Var != null) {
            m1Var.f9193a = 0;
            m1Var.f9194b = null;
            m1Var.f9195c = null;
            m1.f9192d.c(m1Var);
        }
    }
}
