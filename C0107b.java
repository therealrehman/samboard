package X1;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: X1.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0107b extends WeakReference {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final V1.e f5498a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f5499b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public B f5500c;

    public C0107b(V1.e eVar, v vVar, ReferenceQueue referenceQueue, boolean z9) {
        B b3;
        super(vVar, referenceQueue);
        r2.f.c(eVar, "Argument must not be null");
        this.f5498a = eVar;
        if (vVar.f5617e && z9) {
            b3 = vVar.f5619g;
            r2.f.c(b3, "Argument must not be null");
        } else {
            b3 = null;
        }
        this.f5500c = b3;
        this.f5499b = vVar.f5617e;
    }
}
