package F8;

import java.text.DateFormat;
import java.util.Date;
import java.util.regex.Pattern;
import v7.AbstractC1115c;

/* JADX INFO: renamed from: F8.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0042l {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Pattern f915j = Pattern.compile("(\\d{2,4})[^\\d]*");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final Pattern f916k = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final Pattern f917l = Pattern.compile("(\\d{1,2})[^\\d]*");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Pattern f918m = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f919a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f920b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f921c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final String f922d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f923e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f924f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f925g;
    public final boolean h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f926i;

    public C0042l(String str, String str2, long j5, String str3, String str4, boolean z9, boolean z10, boolean z11, boolean z12) {
        this.f919a = str;
        this.f920b = str2;
        this.f921c = j5;
        this.f922d = str3;
        this.f923e = str4;
        this.f924f = z9;
        this.f925g = z10;
        this.f926i = z11;
        this.h = z12;
    }

    public static int a(int i5, int i7, String str, boolean z9) {
        while (i5 < i7) {
            char cCharAt = str.charAt(i5);
            if (((cCharAt < ' ' && cCharAt != '\t') || cCharAt >= 127 || (cCharAt >= '0' && cCharAt <= '9') || ((cCharAt >= 'a' && cCharAt <= 'z') || ((cCharAt >= 'A' && cCharAt <= 'Z') || cCharAt == ':'))) == (!z9)) {
                return i5;
            }
            i5++;
        }
        return i7;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x007f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long b(int r14, java.lang.String r15) {
        /*
            Method dump skipped, instruction units count: 285
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: F8.C0042l.b(int, java.lang.String):long");
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0042l)) {
            return false;
        }
        C0042l c0042l = (C0042l) obj;
        return c0042l.f919a.equals(this.f919a) && c0042l.f920b.equals(this.f920b) && c0042l.f922d.equals(this.f922d) && c0042l.f923e.equals(this.f923e) && c0042l.f921c == this.f921c && c0042l.f924f == this.f924f && c0042l.f925g == this.f925g && c0042l.h == this.h && c0042l.f926i == this.f926i;
    }

    public final int hashCode() {
        int iC = AbstractC1115c.c(AbstractC1115c.c(AbstractC1115c.c(AbstractC1115c.c(527, 31, this.f919a), 31, this.f920b), 31, this.f922d), 31, this.f923e);
        long j5 = this.f921c;
        return ((((((((iC + ((int) (j5 ^ (j5 >>> 32)))) * 31) + (!this.f924f ? 1 : 0)) * 31) + (!this.f925g ? 1 : 0)) * 31) + (!this.h ? 1 : 0)) * 31) + (!this.f926i ? 1 : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f919a);
        sb.append('=');
        sb.append(this.f920b);
        if (this.h) {
            long j5 = this.f921c;
            if (j5 == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=");
                sb.append(((DateFormat) J8.d.f1620a.get()).format(new Date(j5)));
            }
        }
        if (!this.f926i) {
            sb.append("; domain=");
            sb.append(this.f922d);
        }
        sb.append("; path=");
        sb.append(this.f923e);
        if (this.f924f) {
            sb.append("; secure");
        }
        if (this.f925g) {
            sb.append("; httponly");
        }
        return sb.toString();
    }
}
