package androidx.recyclerview.widget;

/* JADX INFO: renamed from: androidx.recyclerview.widget.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0322a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9041a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f9042b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f9043c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f9044d;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0322a)) {
            return false;
        }
        C0322a c0322a = (C0322a) obj;
        int i5 = this.f9041a;
        if (i5 != c0322a.f9041a) {
            return false;
        }
        if (i5 == 8 && Math.abs(this.f9044d - this.f9042b) == 1 && this.f9044d == c0322a.f9042b && this.f9042b == c0322a.f9044d) {
            return true;
        }
        if (this.f9044d != c0322a.f9044d || this.f9042b != c0322a.f9042b) {
            return false;
        }
        Object obj2 = this.f9043c;
        if (obj2 != null) {
            if (!obj2.equals(c0322a.f9043c)) {
                return false;
            }
        } else if (c0322a.f9043c != null) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return (((this.f9041a * 31) + this.f9042b) * 31) + this.f9044d;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("[");
        int i5 = this.f9041a;
        sb.append(i5 != 1 ? i5 != 2 ? i5 != 4 ? i5 != 8 ? "??" : "mv" : "up" : "rm" : "add");
        sb.append(",s:");
        sb.append(this.f9042b);
        sb.append("c:");
        sb.append(this.f9044d);
        sb.append(",p:");
        sb.append(this.f9043c);
        sb.append("]");
        return sb.toString();
    }
}
