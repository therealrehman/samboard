package F8;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLPeerUnverifiedException;

/* JADX INFO: renamed from: F8.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0036f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final C0036f f876c = new C0036f(new LinkedHashSet(new ArrayList()), null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Set f877a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final com.bumptech.glide.d f878b;

    public C0036f(Set set, com.bumptech.glide.d dVar) {
        this.f877a = set;
        this.f878b = dVar;
    }

    public static String b(X509Certificate x509Certificate) {
        if (!(x509Certificate instanceof X509Certificate)) {
            throw new IllegalArgumentException("Certificate pinning requires X509 certificates");
        }
        return "sha256/" + Q8.j.i(x509Certificate.getPublicKey().getEncoded()).d("SHA-256").a();
    }

    public final void a(String str, List list) throws SSLPeerUnverifiedException {
        List listEmptyList = Collections.emptyList();
        Iterator it = this.f877a.iterator();
        if (it.hasNext()) {
            A8.l.z(it.next());
            throw null;
        }
        if (listEmptyList.isEmpty()) {
            return;
        }
        com.bumptech.glide.d dVar = this.f878b;
        if (dVar != null) {
            list = dVar.g(str, list);
        }
        int size = list.size();
        for (int i5 = 0; i5 < size; i5++) {
            if (listEmptyList.size() > 0) {
                A8.l.z(listEmptyList.get(0));
                throw null;
            }
        }
        StringBuilder sb = new StringBuilder("Certificate pinning failure!\n  Peer certificate chain:");
        int size2 = list.size();
        for (int i7 = 0; i7 < size2; i7++) {
            X509Certificate x509Certificate = (X509Certificate) list.get(i7);
            sb.append("\n    ");
            sb.append(b(x509Certificate));
            sb.append(": ");
            sb.append(x509Certificate.getSubjectDN().getName());
        }
        sb.append("\n  Pinned certificates for ");
        sb.append(str);
        sb.append(":");
        int size3 = listEmptyList.size();
        for (int i9 = 0; i9 < size3; i9++) {
            A8.l.z(listEmptyList.get(i9));
            sb.append("\n    null");
        }
        throw new SSLPeerUnverifiedException(sb.toString());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C0036f) {
            C0036f c0036f = (C0036f) obj;
            if (G8.c.i(this.f878b, c0036f.f878b) && this.f877a.equals(c0036f.f877a)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        com.bumptech.glide.d dVar = this.f878b;
        return this.f877a.hashCode() + ((dVar != null ? dVar.hashCode() : 0) * 31);
    }
}
