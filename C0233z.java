package androidx.core.view;

/* JADX INFO: renamed from: androidx.core.view.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0233z {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C0233z f7271e = new C0233z(0, 0, 0, 0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7272a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f7273b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f7274c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f7275d;

    public C0233z(int i5, int i7, int i9, int i10) {
        this.f7274c = i5;
        this.f7272a = i7;
        this.f7275d = i9;
        this.f7273b = i10;
    }

    public static C0233z a(int i5, int i7, int i9, int i10) {
        return (i5 == 0 && i7 == 0 && i9 == 0 && i10 == 0) ? f7271e : new C0233z(i5, i7, i9, i10);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C0233z.class != obj.getClass()) {
            return false;
        }
        C0233z c0233z = (C0233z) obj;
        return this.f7273b == c0233z.f7273b && this.f7274c == c0233z.f7274c && this.f7275d == c0233z.f7275d && this.f7272a == c0233z.f7272a;
    }

    public final int hashCode() {
        return (((((this.f7274c * 31) + this.f7272a) * 31) + this.f7275d) * 31) + this.f7273b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ExtraInsets{left=");
        sb.append(this.f7274c);
        sb.append(", top=");
        sb.append(this.f7272a);
        sb.append(", right=");
        sb.append(this.f7275d);
        sb.append(", bottom=");
        return A8.l.u(sb, this.f7273b, '}');
    }
}
