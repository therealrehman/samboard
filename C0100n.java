package V7;

/* JADX INFO: renamed from: V7.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0100n implements Comparable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f5127e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final S f5128f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f5129g;
    public final boolean h = false;

    public C0100n(int i5, S s8, boolean z9) {
        this.f5127e = i5;
        this.f5128f = s8;
        this.f5129g = z9;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return this.f5127e - ((C0100n) obj).f5127e;
    }
}
