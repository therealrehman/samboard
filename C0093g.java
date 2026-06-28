package V7;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: renamed from: V7.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0093g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f5111a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5112b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5113c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final OutputStream f5114d;

    public C0093g(OutputStream outputStream, byte[] bArr) {
        this.f5114d = outputStream;
        this.f5111a = bArr;
        this.f5112b = bArr.length;
    }

    public static int a(int i5, int i7) {
        return c(i7) + h(i5);
    }

    public static int b(int i5, int i7) {
        return c(i7) + h(i5);
    }

    public static int c(int i5) {
        if (i5 >= 0) {
            return f(i5);
        }
        return 10;
    }

    public static int d(int i5, AbstractC0088b abstractC0088b) {
        return e(abstractC0088b) + h(i5);
    }

    public static int e(AbstractC0088b abstractC0088b) {
        int iC = abstractC0088b.c();
        return f(iC) + iC;
    }

    public static int f(int i5) {
        if ((i5 & (-128)) == 0) {
            return 1;
        }
        if ((i5 & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i5) == 0) {
            return 3;
        }
        return (i5 & (-268435456)) == 0 ? 4 : 5;
    }

    public static int g(long j5) {
        if (((-128) & j5) == 0) {
            return 1;
        }
        if (((-16384) & j5) == 0) {
            return 2;
        }
        if (((-2097152) & j5) == 0) {
            return 3;
        }
        if (((-268435456) & j5) == 0) {
            return 4;
        }
        if (((-34359738368L) & j5) == 0) {
            return 5;
        }
        if (((-4398046511104L) & j5) == 0) {
            return 6;
        }
        if (((-562949953421312L) & j5) == 0) {
            return 7;
        }
        if (((-72057594037927936L) & j5) == 0) {
            return 8;
        }
        return (j5 & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    public static int h(int i5) {
        return f(i5 << 3);
    }

    public static C0093g j(OutputStream outputStream, int i5) {
        return new C0093g(outputStream, new byte[i5]);
    }

    public final void i() throws IOException {
        if (this.f5114d != null) {
            k();
        }
    }

    public final void k() throws IOException {
        OutputStream outputStream = this.f5114d;
        if (outputStream == null) {
            throw new V1.c("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }
        outputStream.write(this.f5111a, 0, this.f5113c);
        this.f5113c = 0;
    }

    public final void l(int i5, int i7) throws IOException {
        x(i5, 0);
        n(i7);
    }

    public final void m(int i5, int i7) throws IOException {
        x(i5, 0);
        n(i7);
    }

    public final void n(int i5) throws IOException {
        if (i5 >= 0) {
            v(i5);
        } else {
            w(i5);
        }
    }

    public final void o(int i5, AbstractC0088b abstractC0088b) throws IOException {
        x(i5, 2);
        p(abstractC0088b);
    }

    public final void p(AbstractC0088b abstractC0088b) throws IOException {
        v(abstractC0088b.c());
        abstractC0088b.f(this);
    }

    public final void q(int i5) throws IOException {
        byte b3 = (byte) i5;
        if (this.f5113c == this.f5112b) {
            k();
        }
        int i7 = this.f5113c;
        this.f5113c = i7 + 1;
        this.f5111a[i7] = b3;
    }

    public final void r(AbstractC0091e abstractC0091e) throws IOException {
        int size = abstractC0091e.size();
        int i5 = this.f5113c;
        int i7 = this.f5112b;
        int i9 = i7 - i5;
        byte[] bArr = this.f5111a;
        if (i9 >= size) {
            abstractC0091e.i(bArr, 0, i5, size);
            this.f5113c += size;
            return;
        }
        abstractC0091e.i(bArr, 0, i5, i9);
        int i10 = size - i9;
        this.f5113c = i7;
        k();
        if (i10 <= i7) {
            abstractC0091e.i(bArr, i9, 0, i10);
            this.f5113c = i10;
            return;
        }
        if (i9 < 0) {
            StringBuilder sb = new StringBuilder(30);
            sb.append("Source offset < 0: ");
            sb.append(i9);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        if (i10 < 0) {
            StringBuilder sb2 = new StringBuilder(23);
            sb2.append("Length < 0: ");
            sb2.append(i10);
            throw new IndexOutOfBoundsException(sb2.toString());
        }
        int i11 = i9 + i10;
        if (i11 <= abstractC0091e.size()) {
            if (i10 > 0) {
                abstractC0091e.t(this.f5114d, i9, i10);
            }
        } else {
            StringBuilder sb3 = new StringBuilder(39);
            sb3.append("Source end offset exceeded: ");
            sb3.append(i11);
            throw new IndexOutOfBoundsException(sb3.toString());
        }
    }

    public final void s(byte[] bArr) throws IOException {
        int length = bArr.length;
        int i5 = this.f5113c;
        int i7 = this.f5112b;
        int i9 = i7 - i5;
        byte[] bArr2 = this.f5111a;
        if (i9 >= length) {
            System.arraycopy(bArr, 0, bArr2, i5, length);
            this.f5113c += length;
            return;
        }
        System.arraycopy(bArr, 0, bArr2, i5, i9);
        int i10 = length - i9;
        this.f5113c = i7;
        k();
        if (i10 > i7) {
            this.f5114d.write(bArr, i9, i10);
        } else {
            System.arraycopy(bArr, i9, bArr2, 0, i10);
            this.f5113c = i10;
        }
    }

    public final void t(int i5) throws IOException {
        q(i5 & 255);
        q((i5 >> 8) & 255);
        q((i5 >> 16) & 255);
        q((i5 >> 24) & 255);
    }

    public final void u(long j5) throws IOException {
        q(((int) j5) & 255);
        q(((int) (j5 >> 8)) & 255);
        q(((int) (j5 >> 16)) & 255);
        q(((int) (j5 >> 24)) & 255);
        q(((int) (j5 >> 32)) & 255);
        q(((int) (j5 >> 40)) & 255);
        q(((int) (j5 >> 48)) & 255);
        q(((int) (j5 >> 56)) & 255);
    }

    public final void v(int i5) throws IOException {
        while ((i5 & (-128)) != 0) {
            q((i5 & 127) | 128);
            i5 >>>= 7;
        }
        q(i5);
    }

    public final void w(long j5) throws IOException {
        while (((-128) & j5) != 0) {
            q((((int) j5) & 127) | 128);
            j5 >>>= 7;
        }
        q((int) j5);
    }

    public final void x(int i5, int i7) throws IOException {
        v((i5 << 3) | i7);
    }
}
