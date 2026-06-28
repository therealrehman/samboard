package F8;

import d6.AbstractC0476d;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.SSLSocket;

/* JADX INFO: renamed from: F8.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0041k {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C0041k f909e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C0041k f910f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f911a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f912b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String[] f913c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String[] f914d;

    static {
        C0038h c0038h = C0038h.f892q;
        C0038h c0038h2 = C0038h.f893r;
        C0038h c0038h3 = C0038h.f894s;
        C0038h c0038h4 = C0038h.f895t;
        C0038h c0038h5 = C0038h.f896u;
        C0038h c0038h6 = C0038h.f887k;
        C0038h c0038h7 = C0038h.f889m;
        C0038h c0038h8 = C0038h.f888l;
        C0038h c0038h9 = C0038h.f890n;
        C0038h c0038h10 = C0038h.f891p;
        C0038h c0038h11 = C0038h.o;
        C0038h[] c0038hArr = {c0038h, c0038h2, c0038h3, c0038h4, c0038h5, c0038h6, c0038h7, c0038h8, c0038h9, c0038h10, c0038h11};
        C0038h[] c0038hArr2 = {c0038h, c0038h2, c0038h3, c0038h4, c0038h5, c0038h6, c0038h7, c0038h8, c0038h9, c0038h10, c0038h11, C0038h.f885i, C0038h.f886j, C0038h.f884g, C0038h.h, C0038h.f882e, C0038h.f883f, C0038h.f881d};
        C0040j c0040j = new C0040j(true);
        c0040j.a(c0038hArr);
        L l2 = L.TLS_1_3;
        L l9 = L.TLS_1_2;
        c0040j.c(l2, l9);
        if (!c0040j.f905a) {
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }
        c0040j.f906b = true;
        new C0041k(c0040j);
        C0040j c0040j2 = new C0040j(true);
        c0040j2.a(c0038hArr2);
        L l10 = L.TLS_1_1;
        L l11 = L.TLS_1_0;
        c0040j2.c(l2, l9, l10, l11);
        if (!c0040j2.f905a) {
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }
        c0040j2.f906b = true;
        f909e = new C0041k(c0040j2);
        C0040j c0040j3 = new C0040j(true);
        c0040j3.a(c0038hArr2);
        c0040j3.c(l11);
        if (!c0040j3.f905a) {
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }
        c0040j3.f906b = true;
        new C0041k(c0040j3);
        f910f = new C0041k(new C0040j(false));
    }

    public C0041k(C0040j c0040j) {
        this.f911a = c0040j.f905a;
        this.f913c = (String[]) c0040j.f907c;
        this.f914d = (String[]) c0040j.f908d;
        this.f912b = c0040j.f906b;
    }

    public final boolean a(SSLSocket sSLSocket) {
        if (!this.f911a) {
            return false;
        }
        String[] strArr = this.f914d;
        if (strArr != null && !G8.c.o(G8.c.f1069f, strArr, sSLSocket.getEnabledProtocols())) {
            return false;
        }
        String[] strArr2 = this.f913c;
        return strArr2 == null || G8.c.o(C0038h.f879b, strArr2, sSLSocket.getEnabledCipherSuites());
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0041k)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        C0041k c0041k = (C0041k) obj;
        boolean z9 = c0041k.f911a;
        boolean z10 = this.f911a;
        if (z10 != z9) {
            return false;
        }
        return !z10 || (Arrays.equals(this.f913c, c0041k.f913c) && Arrays.equals(this.f914d, c0041k.f914d) && this.f912b == c0041k.f912b);
    }

    public final int hashCode() {
        if (this.f911a) {
            return ((((527 + Arrays.hashCode(this.f913c)) * 31) + Arrays.hashCode(this.f914d)) * 31) + (!this.f912b ? 1 : 0);
        }
        return 17;
    }

    public final String toString() {
        String string;
        List listUnmodifiableList;
        if (!this.f911a) {
            return "ConnectionSpec()";
        }
        String string2 = "[all enabled]";
        List listUnmodifiableList2 = null;
        String[] strArr = this.f913c;
        if (strArr != null) {
            if (strArr != null) {
                ArrayList arrayList = new ArrayList(strArr.length);
                for (String str : strArr) {
                    arrayList.add(C0038h.a(str));
                }
                listUnmodifiableList = Collections.unmodifiableList(arrayList);
            } else {
                listUnmodifiableList = null;
            }
            string = listUnmodifiableList.toString();
        } else {
            string = "[all enabled]";
        }
        String[] strArr2 = this.f914d;
        if (strArr2 != null) {
            if (strArr2 != null) {
                ArrayList arrayList2 = new ArrayList(strArr2.length);
                for (String str2 : strArr2) {
                    arrayList2.add(L.a(str2));
                }
                listUnmodifiableList2 = Collections.unmodifiableList(arrayList2);
            }
            string2 = listUnmodifiableList2.toString();
        }
        StringBuilder sb = new StringBuilder("ConnectionSpec(cipherSuites=");
        sb.append(string);
        sb.append(", tlsVersions=");
        sb.append(string2);
        sb.append(", supportsTlsExtensions=");
        return AbstractC0476d.n(sb, this.f912b, ")");
    }
}
