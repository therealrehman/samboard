package A8;

/* JADX INFO: renamed from: A8.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0017e extends kotlinx.coroutines.internal.h {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ int f129d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ j f130e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0017e(kotlinx.coroutines.internal.j jVar, j jVar2, int i5) {
        super(jVar);
        this.f129d = i5;
        this.f130e = jVar2;
    }

    @Override // kotlinx.coroutines.internal.b
    public final kotlinx.coroutines.internal.u c(Object obj) {
        switch (this.f129d) {
            case 0:
                if (!((g) this.f130e).r()) {
                    break;
                }
                break;
            default:
                if (!this.f130e.l()) {
                    break;
                }
                break;
        }
        return kotlinx.coroutines.internal.a.f11857d;
    }
}
