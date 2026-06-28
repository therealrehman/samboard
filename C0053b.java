package L8;

import java.util.Locale;

/* JADX INFO: renamed from: L8.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0053b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Q8.j f1865d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Q8.j f1866e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Q8.j f1867f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Q8.j f1868g;
    public static final Q8.j h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Q8.j f1869i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Q8.j f1870a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Q8.j f1871b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1872c;

    static {
        Q8.j jVar = Q8.j.h;
        f1865d = com.bumptech.glide.d.k(":");
        f1866e = com.bumptech.glide.d.k(":status");
        f1867f = com.bumptech.glide.d.k(":method");
        f1868g = com.bumptech.glide.d.k(":path");
        h = com.bumptech.glide.d.k(":scheme");
        f1869i = com.bumptech.glide.d.k(":authority");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C0053b(Q8.j jVar, String str) {
        this(jVar, com.bumptech.glide.d.k(str));
        Q8.j jVar2 = Q8.j.h;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0053b)) {
            return false;
        }
        C0053b c0053b = (C0053b) obj;
        return this.f1870a.equals(c0053b.f1870a) && this.f1871b.equals(c0053b.f1871b);
    }

    public final int hashCode() {
        return this.f1871b.hashCode() + ((this.f1870a.hashCode() + 527) * 31);
    }

    public final String toString() {
        String strM = this.f1870a.m();
        String strM2 = this.f1871b.m();
        byte[] bArr = G8.c.f1064a;
        Locale locale = Locale.US;
        return strM + ": " + strM2;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public C0053b(String str, String str2) {
        this(com.bumptech.glide.d.k(str), com.bumptech.glide.d.k(str2));
        Q8.j jVar = Q8.j.h;
    }

    public C0053b(Q8.j jVar, Q8.j jVar2) {
        this.f1870a = jVar;
        this.f1871b = jVar2;
        this.f1872c = jVar2.e() + jVar.e() + 32;
    }
}
