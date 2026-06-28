package F8;

import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: F8.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0034d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f864a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f865b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f866c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f867d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final boolean f868e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f869f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f870g;
    public final int h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f871i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f872j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f873k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final boolean f874l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f875m;

    static {
        C0033c c0033c = new C0033c();
        c0033c.f861a = true;
        new C0034d(c0033c);
        C0033c c0033c2 = new C0033c();
        c0033c2.f863c = true;
        long seconds = TimeUnit.SECONDS.toSeconds(Integer.MAX_VALUE);
        c0033c2.f862b = seconds <= 2147483647L ? (int) seconds : Integer.MAX_VALUE;
        new C0034d(c0033c2);
    }

    public C0034d(boolean z9, boolean z10, int i5, int i7, boolean z11, boolean z12, boolean z13, int i9, int i10, boolean z14, boolean z15, boolean z16, String str) {
        this.f864a = z9;
        this.f865b = z10;
        this.f866c = i5;
        this.f867d = i7;
        this.f868e = z11;
        this.f869f = z12;
        this.f870g = z13;
        this.h = i9;
        this.f871i = i10;
        this.f872j = z14;
        this.f873k = z15;
        this.f874l = z16;
        this.f875m = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static F8.C0034d a(F8.o r22) {
        /*
            Method dump skipped, instruction units count: 358
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: F8.C0034d.a(F8.o):F8.d");
    }

    public final String toString() {
        String string = this.f875m;
        if (string == null) {
            StringBuilder sb = new StringBuilder();
            if (this.f864a) {
                sb.append("no-cache, ");
            }
            if (this.f865b) {
                sb.append("no-store, ");
            }
            int i5 = this.f866c;
            if (i5 != -1) {
                sb.append("max-age=");
                sb.append(i5);
                sb.append(", ");
            }
            int i7 = this.f867d;
            if (i7 != -1) {
                sb.append("s-maxage=");
                sb.append(i7);
                sb.append(", ");
            }
            if (this.f868e) {
                sb.append("private, ");
            }
            if (this.f869f) {
                sb.append("public, ");
            }
            if (this.f870g) {
                sb.append("must-revalidate, ");
            }
            int i9 = this.h;
            if (i9 != -1) {
                sb.append("max-stale=");
                sb.append(i9);
                sb.append(", ");
            }
            int i10 = this.f871i;
            if (i10 != -1) {
                sb.append("min-fresh=");
                sb.append(i10);
                sb.append(", ");
            }
            if (this.f872j) {
                sb.append("only-if-cached, ");
            }
            if (this.f873k) {
                sb.append("no-transform, ");
            }
            if (this.f874l) {
                sb.append("immutable, ");
            }
            if (sb.length() == 0) {
                string = "";
            } else {
                sb.delete(sb.length() - 2, sb.length());
                string = sb.toString();
            }
            this.f875m = string;
        }
        return string;
    }

    public C0034d(C0033c c0033c) {
        this.f864a = c0033c.f861a;
        this.f865b = false;
        this.f866c = -1;
        this.f867d = -1;
        this.f868e = false;
        this.f869f = false;
        this.f870g = false;
        this.h = c0033c.f862b;
        this.f871i = -1;
        this.f872j = c0033c.f863c;
        this.f873k = false;
        this.f874l = false;
    }
}
