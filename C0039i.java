package F8;

import java.lang.ref.Reference;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: F8.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0039i {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final ThreadPoolExecutor f898g;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f899a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f900b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final A1.a f901c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final ArrayDeque f902d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final M3.g f903e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f904f;

    static {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        SynchronousQueue synchronousQueue = new SynchronousQueue();
        byte[] bArr = G8.c.f1064a;
        f898g = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, timeUnit, synchronousQueue, new G8.b("OkHttp ConnectionPool", true));
    }

    public C0039i() {
        TimeUnit timeUnit = TimeUnit.MINUTES;
        this.f901c = new A1.a(1, this);
        this.f902d = new ArrayDeque();
        this.f903e = new M3.g(5);
        this.f899a = 5;
        this.f900b = timeUnit.toNanos(5L);
    }

    public final int a(I8.c cVar, long j5) {
        ArrayList arrayList = cVar.f1481n;
        int i5 = 0;
        while (i5 < arrayList.size()) {
            Reference reference = (Reference) arrayList.get(i5);
            if (reference.get() != null) {
                i5++;
            } else {
                M8.i.f2183a.m("A connection to " + cVar.f1471c.f837a.f846a + " was leaked. Did you forget to close a response body?", ((I8.e) reference).f1484a);
                arrayList.remove(i5);
                cVar.f1478k = true;
                if (arrayList.isEmpty()) {
                    cVar.o = j5 - this.f900b;
                    return 0;
                }
            }
        }
        return arrayList.size();
    }
}
