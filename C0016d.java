package A8;

import y8.AbstractC1254c;

/* JADX INFO: renamed from: A8.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0016d extends AbstractC1254c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final y f127e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ g f128f;

    public C0016d(g gVar, y yVar) {
        this.f128f = gVar;
        this.f127e = yVar;
    }

    @Override // y8.AbstractC1257f
    public final void a(Throwable th) {
        if (this.f127e.q()) {
            this.f128f.getClass();
        }
    }

    @Override // g7.InterfaceC0562b
    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
        a((Throwable) obj);
        return T6.p.f3328a;
    }

    public final String toString() {
        return "RemoveReceiveOnCancel[" + this.f127e + ']';
    }
}
