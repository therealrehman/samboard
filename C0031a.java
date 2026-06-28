package F8;

import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import v7.AbstractC1115c;

/* JADX INFO: renamed from: F8.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0031a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q f846a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0032b f847b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final SocketFactory f848c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C0032b f849d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final List f850e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final List f851f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ProxySelector f852g;
    public final Proxy h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final SSLSocketFactory f853i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final HostnameVerifier f854j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final C0036f f855k;

    public C0031a(String str, int i5, C0032b c0032b, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, C0036f c0036f, C0032b c0032b2, Proxy proxy, List list, List list2, ProxySelector proxySelector) {
        p pVar = new p();
        String str2 = sSLSocketFactory != null ? "https" : "http";
        if (str2.equalsIgnoreCase("http")) {
            pVar.f933a = "http";
        } else {
            if (!str2.equalsIgnoreCase("https")) {
                throw new IllegalArgumentException("unexpected scheme: ".concat(str2));
            }
            pVar.f933a = "https";
        }
        if (str == null) {
            throw new NullPointerException("host == null");
        }
        String strB = G8.c.b(q.h(0, str.length(), str, false));
        if (strB == null) {
            throw new IllegalArgumentException("unexpected host: ".concat(str));
        }
        pVar.f936d = strB;
        if (i5 <= 0 || i5 > 65535) {
            throw new IllegalArgumentException(A8.l.o(i5, "unexpected port: "));
        }
        pVar.f937e = i5;
        this.f846a = pVar.b();
        if (c0032b == null) {
            throw new NullPointerException("dns == null");
        }
        this.f847b = c0032b;
        if (socketFactory == null) {
            throw new NullPointerException("socketFactory == null");
        }
        this.f848c = socketFactory;
        if (c0032b2 == null) {
            throw new NullPointerException("proxyAuthenticator == null");
        }
        this.f849d = c0032b2;
        if (list == null) {
            throw new NullPointerException("protocols == null");
        }
        this.f850e = G8.c.k(list);
        if (list2 == null) {
            throw new NullPointerException("connectionSpecs == null");
        }
        this.f851f = G8.c.k(list2);
        if (proxySelector == null) {
            throw new NullPointerException("proxySelector == null");
        }
        this.f852g = proxySelector;
        this.h = proxy;
        this.f853i = sSLSocketFactory;
        this.f854j = hostnameVerifier;
        this.f855k = c0036f;
    }

    public final boolean a(C0031a c0031a) {
        return this.f847b.equals(c0031a.f847b) && this.f849d.equals(c0031a.f849d) && this.f850e.equals(c0031a.f850e) && this.f851f.equals(c0031a.f851f) && this.f852g.equals(c0031a.f852g) && G8.c.i(this.h, c0031a.h) && G8.c.i(this.f853i, c0031a.f853i) && G8.c.i(this.f854j, c0031a.f854j) && G8.c.i(this.f855k, c0031a.f855k) && this.f846a.f945e == c0031a.f846a.f945e;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C0031a) {
            C0031a c0031a = (C0031a) obj;
            if (this.f846a.equals(c0031a.f846a) && a(c0031a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (this.f852g.hashCode() + ((this.f851f.hashCode() + ((this.f850e.hashCode() + ((this.f849d.hashCode() + ((this.f847b.hashCode() + AbstractC1115c.c(527, 31, this.f846a.h)) * 31)) * 31)) * 31)) * 31)) * 31;
        Proxy proxy = this.h;
        int iHashCode2 = (iHashCode + (proxy != null ? proxy.hashCode() : 0)) * 31;
        SSLSocketFactory sSLSocketFactory = this.f853i;
        int iHashCode3 = (iHashCode2 + (sSLSocketFactory != null ? sSLSocketFactory.hashCode() : 0)) * 31;
        HostnameVerifier hostnameVerifier = this.f854j;
        int iHashCode4 = (iHashCode3 + (hostnameVerifier != null ? hostnameVerifier.hashCode() : 0)) * 31;
        C0036f c0036f = this.f855k;
        return iHashCode4 + (c0036f != null ? c0036f.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Address{");
        q qVar = this.f846a;
        sb.append(qVar.f944d);
        sb.append(":");
        sb.append(qVar.f945e);
        Proxy proxy = this.h;
        if (proxy != null) {
            sb.append(", proxy=");
            sb.append(proxy);
        } else {
            sb.append(", proxySelector=");
            sb.append(this.f852g);
        }
        sb.append("}");
        return sb.toString();
    }
}
