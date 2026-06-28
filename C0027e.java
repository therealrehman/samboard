package E7;

import b8.AbstractC0430e;
import g7.InterfaceC0562b;
import h6.AbstractC0582a;
import s7.AbstractC0949h;
import v7.InterfaceC1116d;
import v7.InterfaceC1133u;
import y7.N;
import y7.V;

/* JADX INFO: renamed from: E7.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0027e extends kotlin.jvm.internal.k implements InterfaceC0562b {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C0027e f609f = new C0027e(1, 0);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C0027e f610g = new C0027e(1, 1);
    public static final C0027e h = new C0027e(1, 2);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final C0027e f611i = new C0027e(1, 3);

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final C0027e f612j = new C0027e(1, 4);

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final C0027e f613k = new C0027e(1, 5);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final C0027e f614l = new C0027e(1, 6);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f615e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0027e(int i5, int i7) {
        super(i5);
        this.f615e = i7;
    }

    @Override // g7.InterfaceC0562b
    public final Object invoke(Object obj) {
        InterfaceC1116d interfaceC1116dB;
        String strS;
        boolean z9 = false;
        switch (this.f615e) {
            case 0:
                InterfaceC1116d it = (InterfaceC1116d) obj;
                kotlin.jvm.internal.j.f(it, "it");
                int i5 = C0028f.f616m;
                return Boolean.valueOf(U6.n.i0(H.f587g, AbstractC0582a.s(it)));
            case 1:
                InterfaceC1116d it2 = (InterfaceC1116d) obj;
                kotlin.jvm.internal.j.f(it2, "it");
                if (it2 instanceof InterfaceC1133u) {
                    int i7 = C0028f.f616m;
                    if (U6.n.i0(H.f587g, AbstractC0582a.s(it2))) {
                        z9 = true;
                    }
                }
                return Boolean.valueOf(z9);
            case 2:
                InterfaceC1116d it3 = (InterfaceC1116d) obj;
                kotlin.jvm.internal.j.f(it3, "it");
                return Boolean.valueOf(s6.c.I(it3));
            case 3:
                return ((V) obj).getType();
            case 4:
                InterfaceC1116d it4 = (InterfaceC1116d) obj;
                kotlin.jvm.internal.j.f(it4, "it");
                return Boolean.valueOf(s6.c.I(AbstractC0430e.k(it4)));
            case 5:
                InterfaceC1116d it5 = (InterfaceC1116d) obj;
                kotlin.jvm.internal.j.f(it5, "it");
                int i9 = AbstractC0026d.f608m;
                N n4 = (N) it5;
                if (AbstractC0949h.z(n4) && AbstractC0430e.b(n4, new B7.m(2, n4)) != null) {
                    z9 = true;
                }
                return Boolean.valueOf(z9);
            default:
                InterfaceC1116d it6 = (InterfaceC1116d) obj;
                kotlin.jvm.internal.j.f(it6, "it");
                if (AbstractC0949h.z(it6)) {
                    int i10 = C0028f.f616m;
                    E e3 = null;
                    if (H.f586f.contains(it6.getName()) && (interfaceC1116dB = AbstractC0430e.b(it6, f610g)) != null && (strS = AbstractC0582a.s(interfaceC1116dB)) != null) {
                        e3 = H.f583c.contains(strS) ? E.f573e : ((G) U6.A.d0(H.f585e, strS)) == G.f576f ? E.f575g : E.f574f;
                    }
                    if (e3 != null) {
                        z9 = true;
                    }
                }
                return Boolean.valueOf(z9);
        }
    }
}
