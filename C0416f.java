package b2;

import android.net.Uri;
import android.text.TextUtils;
import java.net.URL;
import java.security.MessageDigest;

/* JADX INFO: renamed from: b2.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0416f implements V1.e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC0417g f9712b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final URL f9713c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f9714d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f9715e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public URL f9716f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public volatile byte[] f9717g;
    public int h;

    public C0416f(URL url) {
        j jVar = InterfaceC0417g.f9718a;
        r2.f.c(url, "Argument must not be null");
        this.f9713c = url;
        this.f9714d = null;
        r2.f.c(jVar, "Argument must not be null");
        this.f9712b = jVar;
    }

    public final String a() {
        String str = this.f9714d;
        if (str != null) {
            return str;
        }
        URL url = this.f9713c;
        r2.f.c(url, "Argument must not be null");
        return url.toString();
    }

    public final URL b() {
        if (this.f9716f == null) {
            if (TextUtils.isEmpty(this.f9715e)) {
                String string = this.f9714d;
                if (TextUtils.isEmpty(string)) {
                    URL url = this.f9713c;
                    r2.f.c(url, "Argument must not be null");
                    string = url.toString();
                }
                this.f9715e = Uri.encode(string, "@#&=*+-_.,:!?()/~'%;$");
            }
            this.f9716f = new URL(this.f9715e);
        }
        return this.f9716f;
    }

    @Override // V1.e
    public final boolean equals(Object obj) {
        if (!(obj instanceof C0416f)) {
            return false;
        }
        C0416f c0416f = (C0416f) obj;
        return a().equals(c0416f.a()) && this.f9712b.equals(c0416f.f9712b);
    }

    @Override // V1.e
    public final int hashCode() {
        if (this.h == 0) {
            int iHashCode = a().hashCode();
            this.h = iHashCode;
            this.h = this.f9712b.hashCode() + (iHashCode * 31);
        }
        return this.h;
    }

    public final String toString() {
        return a();
    }

    @Override // V1.e
    public final void updateDiskCacheKey(MessageDigest messageDigest) {
        if (this.f9717g == null) {
            this.f9717g = a().getBytes(V1.e.f4977a);
        }
        messageDigest.update(this.f9717g);
    }

    public C0416f(String str) {
        j jVar = InterfaceC0417g.f9718a;
        this.f9713c = null;
        if (!TextUtils.isEmpty(str)) {
            this.f9714d = str;
            r2.f.c(jVar, "Argument must not be null");
            this.f9712b = jVar;
            return;
        }
        throw new IllegalArgumentException("Must not be null or empty");
    }
}
