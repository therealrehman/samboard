package A8;

import g7.InterfaceC0562b;
import y8.C1259h;

/* JADX INFO: renamed from: A8.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0014b extends C0013a {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final InterfaceC0562b f125j;

    public C0014b(C1259h c1259h, InterfaceC0562b interfaceC0562b) {
        super(c1259h);
        this.f125j = interfaceC0562b;
    }

    @Override // A8.y
    public final InterfaceC0562b t(Object obj) {
        return new kotlinx.coroutines.internal.o(0, this.f125j, obj, ((C1259h) this.h).f15216i);
    }
}
