package F8;

import java.io.Serializable;

/* JADX INFO: renamed from: F8.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0040j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f905a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f906b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f907c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Serializable f908d;

    public C0040j(boolean z9) {
        this.f905a = z9;
    }

    public void a(C0038h... c0038hArr) {
        if (!this.f905a) {
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }
        String[] strArr = new String[c0038hArr.length];
        for (int i5 = 0; i5 < c0038hArr.length; i5++) {
            strArr[i5] = c0038hArr[i5].f897a;
        }
        b(strArr);
    }

    public void b(String... strArr) {
        if (!this.f905a) {
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }
        if (strArr.length == 0) {
            throw new IllegalArgumentException("At least one cipher suite is required");
        }
        this.f907c = (String[]) strArr.clone();
    }

    public void c(L... lArr) {
        if (!this.f905a) {
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }
        String[] strArr = new String[lArr.length];
        for (int i5 = 0; i5 < lArr.length; i5++) {
            strArr[i5] = lArr[i5].f845e;
        }
        d(strArr);
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.Serializable, java.lang.String[]] */
    public void d(String... strArr) {
        if (!this.f905a) {
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }
        if (strArr.length == 0) {
            throw new IllegalArgumentException("At least one TLS version is required");
        }
        this.f908d = (String[]) strArr.clone();
    }
}
