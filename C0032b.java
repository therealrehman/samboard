package F8;

import java.lang.ref.Reference;
import java.net.Socket;

/* JADX INFO: renamed from: F8.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0032b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C0032b f856a = new C0032b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0032b f857b = new C0032b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final C0032b f858c = new C0032b();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final C0032b f859d = new C0032b();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static C0032b f860e;

    public static Socket a(C0039i c0039i, C0031a c0031a, I8.f fVar) {
        for (I8.c cVar : c0039i.f902d) {
            if (cVar.g(c0031a, null) && cVar.h != null && cVar != fVar.a()) {
                if (fVar.f1497n != null || fVar.f1493j.f1481n.size() != 1) {
                    throw new IllegalStateException();
                }
                Reference reference = (Reference) fVar.f1493j.f1481n.get(0);
                Socket socketB = fVar.b(true, false, false);
                fVar.f1493j = cVar;
                cVar.f1481n.add(reference);
                return socketB;
            }
        }
        return null;
    }

    public static void b(C0039i c0039i, C0031a c0031a, I8.f fVar, K k5) {
        for (I8.c cVar : c0039i.f902d) {
            if (cVar.g(c0031a, k5)) {
                if (fVar.f1493j != null) {
                    throw new IllegalStateException();
                }
                fVar.f1493j = cVar;
                fVar.f1494k = true;
                cVar.f1481n.add(new I8.e(fVar, fVar.f1491g));
                return;
            }
        }
    }
}
