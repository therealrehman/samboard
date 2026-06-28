package androidx.lifecycle;

/* JADX INFO: loaded from: classes.dex */
class LiveData$LifecycleBoundObserver extends x implements InterfaceC0275q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final InterfaceC0276s f7783i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ y f7784j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LiveData$LifecycleBoundObserver(y yVar, InterfaceC0276s interfaceC0276s, A a10) {
        super(yVar, a10);
        this.f7784j = yVar;
        this.f7783i = interfaceC0276s;
    }

    @Override // androidx.lifecycle.InterfaceC0275q
    public final void a(InterfaceC0276s interfaceC0276s, EnumC0270l enumC0270l) {
        InterfaceC0276s interfaceC0276s2 = this.f7783i;
        EnumC0271m enumC0271m = ((C0278u) interfaceC0276s2.getLifecycle()).f7828c;
        if (enumC0271m == EnumC0271m.f7818e) {
            this.f7784j.g(this.f7835e);
            return;
        }
        EnumC0271m enumC0271m2 = null;
        while (enumC0271m2 != enumC0271m) {
            b(e());
            enumC0271m2 = enumC0271m;
            enumC0271m = ((C0278u) interfaceC0276s2.getLifecycle()).f7828c;
        }
    }

    @Override // androidx.lifecycle.x
    public final void c() {
        this.f7783i.getLifecycle().b(this);
    }

    @Override // androidx.lifecycle.x
    public final boolean d(InterfaceC0276s interfaceC0276s) {
        return this.f7783i == interfaceC0276s;
    }

    @Override // androidx.lifecycle.x
    public final boolean e() {
        return ((C0278u) this.f7783i.getLifecycle()).f7828c.a(EnumC0271m.h);
    }
}
