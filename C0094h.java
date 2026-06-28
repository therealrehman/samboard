package V7;

/* JADX INFO: renamed from: V7.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0094h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f5115a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5116b;

    public C0094h(int i5, AbstractC0088b abstractC0088b) {
        this.f5115a = abstractC0088b;
        this.f5116b = i5;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0094h)) {
            return false;
        }
        C0094h c0094h = (C0094h) obj;
        return this.f5115a == c0094h.f5115a && this.f5116b == c0094h.f5116b;
    }

    public final int hashCode() {
        return (System.identityHashCode(this.f5115a) * 65535) + this.f5116b;
    }
}
