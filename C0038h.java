package F8;

import java.util.LinkedHashMap;

/* JADX INFO: renamed from: F8.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0038h {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0037g f879b = new C0037g();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final LinkedHashMap f880c = new LinkedHashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final C0038h f881d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C0038h f882e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C0038h f883f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C0038h f884g;
    public static final C0038h h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final C0038h f885i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final C0038h f886j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final C0038h f887k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final C0038h f888l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final C0038h f889m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final C0038h f890n;
    public static final C0038h o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C0038h f891p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C0038h f892q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final C0038h f893r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final C0038h f894s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final C0038h f895t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public static final C0038h f896u;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f897a;

    static {
        b("SSL_RSA_WITH_NULL_MD5");
        b("SSL_RSA_WITH_NULL_SHA");
        b("SSL_RSA_EXPORT_WITH_RC4_40_MD5");
        b("SSL_RSA_WITH_RC4_128_MD5");
        b("SSL_RSA_WITH_RC4_128_SHA");
        b("SSL_RSA_EXPORT_WITH_DES40_CBC_SHA");
        b("SSL_RSA_WITH_DES_CBC_SHA");
        f881d = b("SSL_RSA_WITH_3DES_EDE_CBC_SHA");
        b("SSL_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA");
        b("SSL_DHE_DSS_WITH_DES_CBC_SHA");
        b("SSL_DHE_DSS_WITH_3DES_EDE_CBC_SHA");
        b("SSL_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA");
        b("SSL_DHE_RSA_WITH_DES_CBC_SHA");
        b("SSL_DHE_RSA_WITH_3DES_EDE_CBC_SHA");
        b("SSL_DH_anon_EXPORT_WITH_RC4_40_MD5");
        b("SSL_DH_anon_WITH_RC4_128_MD5");
        b("SSL_DH_anon_EXPORT_WITH_DES40_CBC_SHA");
        b("SSL_DH_anon_WITH_DES_CBC_SHA");
        b("SSL_DH_anon_WITH_3DES_EDE_CBC_SHA");
        b("TLS_KRB5_WITH_DES_CBC_SHA");
        b("TLS_KRB5_WITH_3DES_EDE_CBC_SHA");
        b("TLS_KRB5_WITH_RC4_128_SHA");
        b("TLS_KRB5_WITH_DES_CBC_MD5");
        b("TLS_KRB5_WITH_3DES_EDE_CBC_MD5");
        b("TLS_KRB5_WITH_RC4_128_MD5");
        b("TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA");
        b("TLS_KRB5_EXPORT_WITH_RC4_40_SHA");
        b("TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5");
        b("TLS_KRB5_EXPORT_WITH_RC4_40_MD5");
        f882e = b("TLS_RSA_WITH_AES_128_CBC_SHA");
        b("TLS_DHE_DSS_WITH_AES_128_CBC_SHA");
        b("TLS_DHE_RSA_WITH_AES_128_CBC_SHA");
        b("TLS_DH_anon_WITH_AES_128_CBC_SHA");
        f883f = b("TLS_RSA_WITH_AES_256_CBC_SHA");
        b("TLS_DHE_DSS_WITH_AES_256_CBC_SHA");
        b("TLS_DHE_RSA_WITH_AES_256_CBC_SHA");
        b("TLS_DH_anon_WITH_AES_256_CBC_SHA");
        b("TLS_RSA_WITH_NULL_SHA256");
        b("TLS_RSA_WITH_AES_128_CBC_SHA256");
        b("TLS_RSA_WITH_AES_256_CBC_SHA256");
        b("TLS_DHE_DSS_WITH_AES_128_CBC_SHA256");
        b("TLS_RSA_WITH_CAMELLIA_128_CBC_SHA");
        b("TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA");
        b("TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA");
        b("TLS_DHE_RSA_WITH_AES_128_CBC_SHA256");
        b("TLS_DHE_DSS_WITH_AES_256_CBC_SHA256");
        b("TLS_DHE_RSA_WITH_AES_256_CBC_SHA256");
        b("TLS_DH_anon_WITH_AES_128_CBC_SHA256");
        b("TLS_DH_anon_WITH_AES_256_CBC_SHA256");
        b("TLS_RSA_WITH_CAMELLIA_256_CBC_SHA");
        b("TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA");
        b("TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA");
        b("TLS_PSK_WITH_RC4_128_SHA");
        b("TLS_PSK_WITH_3DES_EDE_CBC_SHA");
        b("TLS_PSK_WITH_AES_128_CBC_SHA");
        b("TLS_PSK_WITH_AES_256_CBC_SHA");
        b("TLS_RSA_WITH_SEED_CBC_SHA");
        f884g = b("TLS_RSA_WITH_AES_128_GCM_SHA256");
        h = b("TLS_RSA_WITH_AES_256_GCM_SHA384");
        b("TLS_DHE_RSA_WITH_AES_128_GCM_SHA256");
        b("TLS_DHE_RSA_WITH_AES_256_GCM_SHA384");
        b("TLS_DHE_DSS_WITH_AES_128_GCM_SHA256");
        b("TLS_DHE_DSS_WITH_AES_256_GCM_SHA384");
        b("TLS_DH_anon_WITH_AES_128_GCM_SHA256");
        b("TLS_DH_anon_WITH_AES_256_GCM_SHA384");
        b("TLS_EMPTY_RENEGOTIATION_INFO_SCSV");
        b("TLS_FALLBACK_SCSV");
        b("TLS_ECDH_ECDSA_WITH_NULL_SHA");
        b("TLS_ECDH_ECDSA_WITH_RC4_128_SHA");
        b("TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA");
        b("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA");
        b("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA");
        b("TLS_ECDHE_ECDSA_WITH_NULL_SHA");
        b("TLS_ECDHE_ECDSA_WITH_RC4_128_SHA");
        b("TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA");
        b("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA");
        b("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA");
        b("TLS_ECDH_RSA_WITH_NULL_SHA");
        b("TLS_ECDH_RSA_WITH_RC4_128_SHA");
        b("TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA");
        b("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA");
        b("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA");
        b("TLS_ECDHE_RSA_WITH_NULL_SHA");
        b("TLS_ECDHE_RSA_WITH_RC4_128_SHA");
        b("TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA");
        f885i = b("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA");
        f886j = b("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA");
        b("TLS_ECDH_anon_WITH_NULL_SHA");
        b("TLS_ECDH_anon_WITH_RC4_128_SHA");
        b("TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA");
        b("TLS_ECDH_anon_WITH_AES_128_CBC_SHA");
        b("TLS_ECDH_anon_WITH_AES_256_CBC_SHA");
        b("TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256");
        b("TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384");
        b("TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256");
        b("TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384");
        b("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256");
        b("TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384");
        b("TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256");
        b("TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384");
        f887k = b("TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256");
        f888l = b("TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384");
        b("TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256");
        b("TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384");
        f889m = b("TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
        f890n = b("TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384");
        b("TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256");
        b("TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384");
        b("TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA");
        b("TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA");
        o = b("TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256");
        f891p = b("TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256");
        b("TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256");
        b("TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256");
        f892q = b("TLS_AES_128_GCM_SHA256");
        f893r = b("TLS_AES_256_GCM_SHA384");
        f894s = b("TLS_CHACHA20_POLY1305_SHA256");
        f895t = b("TLS_AES_128_CCM_SHA256");
        f896u = b("TLS_AES_256_CCM_8_SHA256");
    }

    public C0038h(String str) {
        str.getClass();
        this.f897a = str;
    }

    public static synchronized C0038h a(String str) {
        C0038h c0038h;
        String str2;
        try {
            LinkedHashMap linkedHashMap = f880c;
            c0038h = (C0038h) linkedHashMap.get(str);
            if (c0038h == null) {
                if (str.startsWith("TLS_")) {
                    str2 = "SSL_" + str.substring(4);
                } else if (str.startsWith("SSL_")) {
                    str2 = "TLS_" + str.substring(4);
                } else {
                    str2 = str;
                }
                c0038h = (C0038h) linkedHashMap.get(str2);
                if (c0038h == null) {
                    c0038h = new C0038h(str);
                }
                linkedHashMap.put(str, c0038h);
            }
        } catch (Throwable th) {
            throw th;
        }
        return c0038h;
    }

    public static C0038h b(String str) {
        C0038h c0038h = new C0038h(str);
        f880c.put(str, c0038h);
        return c0038h;
    }

    public final String toString() {
        return this.f897a;
    }
}
