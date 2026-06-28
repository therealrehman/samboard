package V7;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: V7.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0095i {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f5117b = 0;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f5118a;

    static {
        new C0095i(0);
    }

    public C0095i() {
        this.f5118a = new HashMap();
    }

    public final void a(C0101o c0101o) {
        this.f5118a.put(new C0094h(c0101o.f5133d.f5127e, c0101o.f5130a), c0101o);
    }

    public C0095i(int i5) {
        this.f5118a = Collections.emptyMap();
    }
}
