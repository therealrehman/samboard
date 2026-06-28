package androidx.lifecycle;

/* JADX INFO: renamed from: androidx.lifecycle.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0277t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public EnumC0271m f7824a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public InterfaceC0275q f7825b;

    public final void a(InterfaceC0276s interfaceC0276s, EnumC0270l enumC0270l) {
        EnumC0271m enumC0271mA = enumC0270l.a();
        EnumC0271m state1 = this.f7824a;
        kotlin.jvm.internal.j.f(state1, "state1");
        if (enumC0271mA.compareTo(state1) < 0) {
            state1 = enumC0271mA;
        }
        this.f7824a = state1;
        this.f7825b.a(interfaceC0276s, enumC0270l);
        this.f7824a = enumC0271mA;
    }
}
