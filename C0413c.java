package b2;

import java.io.File;

/* JADX INFO: renamed from: b2.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0413c implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9708a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f9709b;

    public /* synthetic */ C0413c(int i5, Object obj) {
        this.f9708a = i5;
        this.f9709b = obj;
    }

    @Override // b2.p
    public final o a(Object obj, int i5, int i7, V1.h hVar) {
        switch (this.f9708a) {
            case 0:
                byte[] bArr = (byte[]) obj;
                return new o(new q2.b(bArr), new k(1, bArr, (z) this.f9709b));
            case 1:
                return new o(new q2.b(obj), new W1.c(1, obj.toString(), (z) this.f9709b));
            default:
                File file = (File) obj;
                return new o(new q2.b(file), new W1.c(2, file, (z) this.f9709b));
        }
    }

    @Override // b2.p
    public final boolean b(Object obj) {
        switch (this.f9708a) {
            case 0:
                return true;
            case 1:
                return obj.toString().startsWith("data:image");
            default:
                return true;
        }
    }
}
