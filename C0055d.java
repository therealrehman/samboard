package L8;

import java.io.EOFException;
import java.util.Arrays;

/* JADX INFO: renamed from: L8.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0055d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Q8.g f1880a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1882c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1881b = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public C0053b[] f1884e = new C0053b[8];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1885f = 7;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1886g = 0;
    public int h = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1883d = 4096;

    public C0055d(Q8.g gVar) {
        this.f1880a = gVar;
    }

    public final void a(int i5) {
        int i7;
        if (i5 > 0) {
            int length = this.f1884e.length - 1;
            int i9 = 0;
            while (true) {
                i7 = this.f1885f;
                if (length < i7 || i5 <= 0) {
                    break;
                }
                int i10 = this.f1884e[length].f1872c;
                i5 -= i10;
                this.h -= i10;
                this.f1886g--;
                i9++;
                length--;
            }
            C0053b[] c0053bArr = this.f1884e;
            int i11 = i7 + 1;
            System.arraycopy(c0053bArr, i11, c0053bArr, i11 + i9, this.f1886g);
            C0053b[] c0053bArr2 = this.f1884e;
            int i12 = this.f1885f + 1;
            Arrays.fill(c0053bArr2, i12, i12 + i9, (Object) null);
            this.f1885f += i9;
        }
    }

    public final void b(C0053b c0053b) {
        int i5 = this.f1883d;
        int i7 = c0053b.f1872c;
        if (i7 > i5) {
            Arrays.fill(this.f1884e, (Object) null);
            this.f1885f = this.f1884e.length - 1;
            this.f1886g = 0;
            this.h = 0;
            return;
        }
        a((this.h + i7) - i5);
        int i9 = this.f1886g + 1;
        C0053b[] c0053bArr = this.f1884e;
        if (i9 > c0053bArr.length) {
            C0053b[] c0053bArr2 = new C0053b[c0053bArr.length * 2];
            System.arraycopy(c0053bArr, 0, c0053bArr2, c0053bArr.length, c0053bArr.length);
            this.f1885f = this.f1884e.length - 1;
            this.f1884e = c0053bArr2;
        }
        int i10 = this.f1885f;
        this.f1885f = i10 - 1;
        this.f1884e[i10] = c0053b;
        this.f1886g++;
        this.h += i7;
    }

    public final void c(Q8.j jVar) throws EOFException {
        B.f1861d.getClass();
        long j5 = 0;
        long j9 = 0;
        for (int i5 = 0; i5 < jVar.e(); i5++) {
            j9 += (long) B.f1860c[jVar.h(i5) & 255];
        }
        int i7 = (int) ((j9 + 7) >> 3);
        int iE = jVar.e();
        Q8.g gVar = this.f1880a;
        if (i7 >= iE) {
            e(jVar.e(), 127, 0);
            gVar.Q(jVar);
            return;
        }
        Q8.g gVar2 = new Q8.g();
        B.f1861d.getClass();
        int i9 = 0;
        for (int i10 = 0; i10 < jVar.e(); i10++) {
            int iH = jVar.h(i10) & 255;
            int i11 = B.f1859b[iH];
            byte b3 = B.f1860c[iH];
            j5 = (j5 << b3) | ((long) i11);
            i9 += b3;
            while (i9 >= 8) {
                i9 -= 8;
                gVar2.S((int) (j5 >> i9));
            }
        }
        if (i9 > 0) {
            gVar2.S((int) ((j5 << (8 - i9)) | ((long) (255 >>> i9))));
        }
        Q8.j jVarJ = gVar2.j(gVar2.f2998f);
        e(jVarJ.e(), 127, 128);
        gVar.Q(jVarJ);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void d(java.util.ArrayList r14) {
        /*
            Method dump skipped, instruction units count: 248
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: L8.C0055d.d(java.util.ArrayList):void");
    }

    public final void e(int i5, int i7, int i9) {
        Q8.g gVar = this.f1880a;
        if (i5 < i7) {
            gVar.S(i5 | i9);
            return;
        }
        gVar.S(i9 | i7);
        int i10 = i5 - i7;
        while (i10 >= 128) {
            gVar.S(128 | (i10 & 127));
            i10 >>>= 7;
        }
        gVar.S(i10);
    }
}
