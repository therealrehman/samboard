package L8;

import f6.AbstractC0527a;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: renamed from: L8.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0054c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Q8.s f1874b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f1873a = new ArrayList();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public C0053b[] f1877e = new C0053b[8];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1878f = 7;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1879g = 0;
    public int h = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int f1875c = 4096;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1876d = 4096;

    public C0054c(u uVar) {
        this.f1874b = AbstractC0527a.a(uVar);
    }

    public final int a(int i5) {
        int i7;
        int i9 = 0;
        if (i5 > 0) {
            int length = this.f1877e.length;
            while (true) {
                length--;
                i7 = this.f1878f;
                if (length < i7 || i5 <= 0) {
                    break;
                }
                int i10 = this.f1877e[length].f1872c;
                i5 -= i10;
                this.h -= i10;
                this.f1879g--;
                i9++;
            }
            C0053b[] c0053bArr = this.f1877e;
            System.arraycopy(c0053bArr, i7 + 1, c0053bArr, i7 + 1 + i9, this.f1879g);
            this.f1878f += i9;
        }
        return i9;
    }

    public final Q8.j b(int i5) throws IOException {
        if (i5 >= 0) {
            C0053b[] c0053bArr = e.f1887a;
            if (i5 <= c0053bArr.length - 1) {
                return c0053bArr[i5].f1870a;
            }
        }
        int length = this.f1878f + 1 + (i5 - e.f1887a.length);
        if (length >= 0) {
            C0053b[] c0053bArr2 = this.f1877e;
            if (length < c0053bArr2.length) {
                return c0053bArr2[length].f1870a;
            }
        }
        throw new IOException("Header index too large " + (i5 + 1));
    }

    public final void c(C0053b c0053b) {
        this.f1873a.add(c0053b);
        int i5 = this.f1876d;
        int i7 = c0053b.f1872c;
        if (i7 > i5) {
            Arrays.fill(this.f1877e, (Object) null);
            this.f1878f = this.f1877e.length - 1;
            this.f1879g = 0;
            this.h = 0;
            return;
        }
        a((this.h + i7) - i5);
        int i9 = this.f1879g + 1;
        C0053b[] c0053bArr = this.f1877e;
        if (i9 > c0053bArr.length) {
            C0053b[] c0053bArr2 = new C0053b[c0053bArr.length * 2];
            System.arraycopy(c0053bArr, 0, c0053bArr2, c0053bArr.length, c0053bArr.length);
            this.f1878f = this.f1877e.length - 1;
            this.f1877e = c0053bArr2;
        }
        int i10 = this.f1878f;
        this.f1878f = i10 - 1;
        this.f1877e[i10] = c0053b;
        this.f1879g++;
        this.h += i7;
    }

    public final Q8.j d() {
        int i5;
        Q8.s sVar = this.f1874b;
        byte b3 = sVar.readByte();
        int i7 = b3 & 255;
        boolean z9 = (b3 & 128) == 128;
        int iE = e(i7, 127);
        if (!z9) {
            return sVar.j(iE);
        }
        B b10 = B.f1861d;
        long j5 = iE;
        sVar.D(j5);
        byte[] bArrI = sVar.f3018e.I(j5);
        b10.getClass();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        A a10 = b10.f1862a;
        A a11 = a10;
        int i9 = 0;
        int i10 = 0;
        for (byte b11 : bArrI) {
            i9 = (i9 << 8) | (b11 & 255);
            i10 += 8;
            while (i10 >= 8) {
                a11 = ((A[]) a11.f1858d)[(i9 >>> (i10 - 8)) & 255];
                if (((A[]) a11.f1858d) == null) {
                    byteArrayOutputStream.write(a11.f1856b);
                    i10 -= a11.f1857c;
                    a11 = a10;
                } else {
                    i10 -= 8;
                }
            }
        }
        while (i10 > 0) {
            A a12 = ((A[]) a11.f1858d)[(i9 << (8 - i10)) & 255];
            if (((A[]) a12.f1858d) != null || (i5 = a12.f1857c) > i10) {
                break;
            }
            byteArrayOutputStream.write(a12.f1856b);
            i10 -= i5;
            a11 = a10;
        }
        return Q8.j.i(byteArrayOutputStream.toByteArray());
    }

    public final int e(int i5, int i7) {
        int i9 = i5 & i7;
        if (i9 < i7) {
            return i9;
        }
        int i10 = 0;
        while (true) {
            byte b3 = this.f1874b.readByte();
            int i11 = b3 & 255;
            if ((b3 & 128) == 0) {
                return i7 + (i11 << i10);
            }
            i7 += (b3 & 127) << i10;
            i10 += 7;
        }
    }
}
