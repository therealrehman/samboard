package V7;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/* JADX INFO: renamed from: V7.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0092f {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5105c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final InputStream f5107e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5108f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5110i;
    public int h = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final byte[] f5103a = new byte[4096];

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5104b = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5106d = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f5109g = 0;

    public C0092f(InputStream inputStream) {
        this.f5107e = inputStream;
    }

    public final void a(int i5) {
        if (this.f5108f != i5) {
            throw new C0105t("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final int b() {
        int i5 = this.h;
        if (i5 == Integer.MAX_VALUE) {
            return -1;
        }
        return i5 - (this.f5109g + this.f5106d);
    }

    public final void c(int i5) {
        this.h = i5;
        o();
    }

    public final int d(int i5) {
        if (i5 < 0) {
            throw new C0105t("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        int i7 = this.f5109g + this.f5106d + i5;
        int i9 = this.h;
        if (i7 > i9) {
            throw C0105t.a();
        }
        this.h = i7;
        o();
        return i9;
    }

    public final y e() {
        int iK = k();
        int i5 = this.f5104b;
        int i7 = this.f5106d;
        if (iK > i5 - i7 || iK <= 0) {
            return iK == 0 ? AbstractC0091e.f5102e : new y(h(iK));
        }
        byte[] bArr = new byte[iK];
        System.arraycopy(this.f5103a, i7, bArr, 0, iK);
        y yVar = new y(bArr);
        this.f5106d += iK;
        return yVar;
    }

    public final int f() {
        return k();
    }

    public final AbstractC0088b g(AbstractC0089c abstractC0089c, C0095i c0095i) throws C0105t {
        int iK = k();
        if (this.f5110i >= 64) {
            throw new C0105t("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int iD = d(iK);
        this.f5110i++;
        AbstractC0088b abstractC0088b = (AbstractC0088b) abstractC0089c.a(this, c0095i);
        a(0);
        this.f5110i--;
        c(iD);
        return abstractC0088b;
    }

    public final byte[] h(int i5) throws C0105t {
        if (i5 <= 0) {
            if (i5 == 0) {
                return AbstractC0104s.f5135a;
            }
            throw new C0105t("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        int i7 = this.f5109g;
        int i9 = this.f5106d;
        int i10 = i7 + i9 + i5;
        int i11 = this.h;
        if (i10 > i11) {
            r((i11 - i7) - i9);
            throw C0105t.a();
        }
        byte[] bArr = this.f5103a;
        if (i5 < 4096) {
            byte[] bArr2 = new byte[i5];
            int i12 = this.f5104b - i9;
            System.arraycopy(bArr, i9, bArr2, 0, i12);
            int i13 = this.f5104b;
            this.f5106d = i13;
            int i14 = i5 - i12;
            if (i13 - i13 < i14) {
                p(i14);
            }
            System.arraycopy(bArr, 0, bArr2, i12, i14);
            this.f5106d = i14;
            return bArr2;
        }
        int i15 = this.f5104b;
        this.f5109g = i7 + i15;
        this.f5106d = 0;
        this.f5104b = 0;
        int length = i15 - i9;
        int i16 = i5 - length;
        ArrayList<byte[]> arrayList = new ArrayList();
        while (i16 > 0) {
            int iMin = Math.min(i16, 4096);
            byte[] bArr3 = new byte[iMin];
            int i17 = 0;
            while (i17 < iMin) {
                InputStream inputStream = this.f5107e;
                int i18 = inputStream == null ? -1 : inputStream.read(bArr3, i17, iMin - i17);
                if (i18 == -1) {
                    throw C0105t.a();
                }
                this.f5109g += i18;
                i17 += i18;
            }
            i16 -= iMin;
            arrayList.add(bArr3);
        }
        byte[] bArr4 = new byte[i5];
        System.arraycopy(bArr, i9, bArr4, 0, length);
        for (byte[] bArr5 : arrayList) {
            System.arraycopy(bArr5, 0, bArr4, length, bArr5.length);
            length += bArr5.length;
        }
        return bArr4;
    }

    public final int i() throws C0105t {
        int i5 = this.f5106d;
        if (this.f5104b - i5 < 4) {
            p(4);
            i5 = this.f5106d;
        }
        this.f5106d = i5 + 4;
        byte[] bArr = this.f5103a;
        return ((bArr[i5 + 3] & 255) << 24) | (bArr[i5] & 255) | ((bArr[i5 + 1] & 255) << 8) | ((bArr[i5 + 2] & 255) << 16);
    }

    public final long j() throws C0105t {
        int i5 = this.f5106d;
        if (this.f5104b - i5 < 8) {
            p(8);
            i5 = this.f5106d;
        }
        this.f5106d = i5 + 8;
        byte[] bArr = this.f5103a;
        return ((((long) bArr[i5 + 1]) & 255) << 8) | (((long) bArr[i5]) & 255) | ((((long) bArr[i5 + 2]) & 255) << 16) | ((((long) bArr[i5 + 3]) & 255) << 24) | ((((long) bArr[i5 + 4]) & 255) << 32) | ((((long) bArr[i5 + 5]) & 255) << 40) | ((((long) bArr[i5 + 6]) & 255) << 48) | ((((long) bArr[i5 + 7]) & 255) << 56);
    }

    public final int k() {
        int i5;
        int i7 = this.f5106d;
        int i9 = this.f5104b;
        if (i9 != i7) {
            int i10 = i7 + 1;
            byte[] bArr = this.f5103a;
            byte b3 = bArr[i7];
            if (b3 >= 0) {
                this.f5106d = i10;
                return b3;
            }
            if (i9 - i10 >= 9) {
                int i11 = i7 + 2;
                int i12 = (bArr[i10] << 7) ^ b3;
                long j5 = i12;
                if (j5 < 0) {
                    i5 = (int) ((-128) ^ j5);
                } else {
                    int i13 = i7 + 3;
                    int i14 = (bArr[i11] << 14) ^ i12;
                    long j9 = i14;
                    if (j9 >= 0) {
                        i5 = (int) (16256 ^ j9);
                    } else {
                        int i15 = i7 + 4;
                        int i16 = i14 ^ (bArr[i13] << 21);
                        long j10 = i16;
                        if (j10 < 0) {
                            i5 = (int) ((-2080896) ^ j10);
                        } else {
                            i13 = i7 + 5;
                            byte b10 = bArr[i15];
                            int i17 = (int) (((long) (i16 ^ (b10 << 28))) ^ 266354560);
                            if (b10 < 0) {
                                i15 = i7 + 6;
                                if (bArr[i13] < 0) {
                                    i13 = i7 + 7;
                                    if (bArr[i15] < 0) {
                                        i15 = i7 + 8;
                                        if (bArr[i13] < 0) {
                                            i13 = i7 + 9;
                                            if (bArr[i15] < 0) {
                                                int i18 = i7 + 10;
                                                if (bArr[i13] >= 0) {
                                                    i11 = i18;
                                                    i5 = i17;
                                                }
                                            }
                                        }
                                    }
                                }
                                i5 = i17;
                            }
                            i5 = i17;
                        }
                        i11 = i15;
                    }
                    i11 = i13;
                }
                this.f5106d = i11;
                return i5;
            }
        }
        return (int) m();
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b6, code lost:
    
        if (r3[r2] < 0) goto L39;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long l() {
        /*
            r12 = this;
            int r0 = r12.f5106d
            int r1 = r12.f5104b
            if (r1 != r0) goto L8
            goto Lb8
        L8:
            int r2 = r0 + 1
            byte[] r3 = r12.f5103a
            r4 = r3[r0]
            if (r4 < 0) goto L14
            r12.f5106d = r2
            long r0 = (long) r4
            return r0
        L14:
            int r1 = r1 - r2
            r5 = 9
            if (r1 >= r5) goto L1b
            goto Lb8
        L1b:
            int r1 = r0 + 2
            r2 = r3[r2]
            int r2 = r2 << 7
            r2 = r2 ^ r4
            long r4 = (long) r2
            r6 = 0
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 >= 0) goto L2e
            r2 = -128(0xffffffffffffff80, double:NaN)
        L2b:
            long r2 = r2 ^ r4
            goto Lc1
        L2e:
            int r2 = r0 + 3
            r1 = r3[r1]
            int r1 = r1 << 14
            long r8 = (long) r1
            long r4 = r4 ^ r8
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 < 0) goto L42
            r0 = 16256(0x3f80, double:8.0315E-320)
        L3c:
            long r0 = r0 ^ r4
            r10 = r0
            r1 = r2
            r2 = r10
            goto Lc1
        L42:
            int r1 = r0 + 4
            r2 = r3[r2]
            int r2 = r2 << 21
            long r8 = (long) r2
            long r4 = r4 ^ r8
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 >= 0) goto L52
            r2 = -2080896(0xffffffffffe03f80, double:NaN)
            goto L2b
        L52:
            int r2 = r0 + 5
            r1 = r3[r1]
            long r8 = (long) r1
            r1 = 28
            long r8 = r8 << r1
            long r4 = r4 ^ r8
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 < 0) goto L63
            r0 = 266354560(0xfe03f80, double:1.315966377E-315)
            goto L3c
        L63:
            int r1 = r0 + 6
            r2 = r3[r2]
            long r8 = (long) r2
            r2 = 35
            long r8 = r8 << r2
            long r4 = r4 ^ r8
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 >= 0) goto L76
            r2 = -34093383808(0xfffffff80fe03f80, double:NaN)
            goto L2b
        L76:
            int r2 = r0 + 7
            r1 = r3[r1]
            long r8 = (long) r1
            r1 = 42
            long r8 = r8 << r1
            long r4 = r4 ^ r8
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 < 0) goto L89
            r0 = 4363953127296(0x3f80fe03f80, double:2.1560793202584E-311)
            goto L3c
        L89:
            int r1 = r0 + 8
            r2 = r3[r2]
            long r8 = (long) r2
            r2 = 49
            long r8 = r8 << r2
            long r4 = r4 ^ r8
            int r2 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r2 >= 0) goto L9c
            r2 = -558586000294016(0xfffe03f80fe03f80, double:NaN)
            goto L2b
        L9c:
            int r2 = r0 + 9
            r1 = r3[r1]
            long r8 = (long) r1
            r1 = 56
            long r8 = r8 << r1
            long r4 = r4 ^ r8
            r8 = 71499008037633920(0xfe03f80fe03f80, double:6.838959413692434E-304)
            long r4 = r4 ^ r8
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 >= 0) goto Lbf
            int r1 = r0 + 10
            r0 = r3[r2]
            long r2 = (long) r0
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 >= 0) goto Lbd
        Lb8:
            long r0 = r12.m()
            return r0
        Lbd:
            r2 = r4
            goto Lc1
        Lbf:
            r1 = r2
            goto Lbd
        Lc1:
            r12.f5106d = r1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: V7.C0092f.l():long");
    }

    public final long m() throws C0105t {
        long j5 = 0;
        for (int i5 = 0; i5 < 64; i5 += 7) {
            if (this.f5106d == this.f5104b) {
                p(1);
            }
            int i7 = this.f5106d;
            this.f5106d = i7 + 1;
            byte b3 = this.f5103a[i7];
            j5 |= ((long) (b3 & 127)) << i5;
            if ((b3 & 128) == 0) {
                return j5;
            }
        }
        throw new C0105t("CodedInputStream encountered a malformed varint.");
    }

    public final int n() throws C0105t {
        if (this.f5106d == this.f5104b && !s(1)) {
            this.f5108f = 0;
            return 0;
        }
        int iK = k();
        this.f5108f = iK;
        if ((iK >>> 3) != 0) {
            return iK;
        }
        throw new C0105t("Protocol message contained an invalid tag (zero).");
    }

    public final void o() {
        int i5 = this.f5104b + this.f5105c;
        this.f5104b = i5;
        int i7 = this.f5109g + i5;
        int i9 = this.h;
        if (i7 <= i9) {
            this.f5105c = 0;
            return;
        }
        int i10 = i7 - i9;
        this.f5105c = i10;
        this.f5104b = i5 - i10;
    }

    public final void p(int i5) throws C0105t {
        if (!s(i5)) {
            throw C0105t.a();
        }
    }

    public final boolean q(int i5, C0093g c0093g) throws IOException {
        int iN;
        int i7 = i5 & 7;
        if (i7 == 0) {
            long jL = l();
            c0093g.v(i5);
            c0093g.w(jL);
            return true;
        }
        if (i7 == 1) {
            long j5 = j();
            c0093g.v(i5);
            c0093g.u(j5);
            return true;
        }
        if (i7 == 2) {
            y yVarE = e();
            c0093g.v(i5);
            c0093g.v(yVarE.size());
            c0093g.r(yVarE);
            return true;
        }
        if (i7 != 3) {
            if (i7 == 4) {
                return false;
            }
            if (i7 != 5) {
                throw new C0105t("Protocol message tag had invalid wire type.");
            }
            int i9 = i();
            c0093g.v(i5);
            c0093g.t(i9);
            return true;
        }
        c0093g.v(i5);
        do {
            iN = n();
            if (iN == 0) {
                break;
            }
        } while (q(iN, c0093g));
        int i10 = ((i5 >>> 3) << 3) | 4;
        a(i10);
        c0093g.v(i10);
        return true;
    }

    public final void r(int i5) throws C0105t {
        int i7 = this.f5104b;
        int i9 = this.f5106d;
        int i10 = i7 - i9;
        if (i5 <= i10 && i5 >= 0) {
            this.f5106d = i9 + i5;
            return;
        }
        if (i5 < 0) {
            throw new C0105t("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
        }
        int i11 = this.f5109g;
        int i12 = i11 + i9 + i5;
        int i13 = this.h;
        if (i12 > i13) {
            r((i13 - i11) - i9);
            throw C0105t.a();
        }
        this.f5106d = i7;
        p(1);
        while (true) {
            int i14 = i5 - i10;
            int i15 = this.f5104b;
            if (i14 <= i15) {
                this.f5106d = i14;
                return;
            } else {
                i10 += i15;
                this.f5106d = i15;
                p(1);
            }
        }
    }

    public final boolean s(int i5) throws IOException {
        InputStream inputStream;
        int i7 = this.f5106d;
        int i9 = i7 + i5;
        int i10 = this.f5104b;
        if (i9 <= i10) {
            StringBuilder sb = new StringBuilder(77);
            sb.append("refillBuffer() called when ");
            sb.append(i5);
            sb.append(" bytes were already available in buffer");
            throw new IllegalStateException(sb.toString());
        }
        if (this.f5109g + i7 + i5 <= this.h && (inputStream = this.f5107e) != null) {
            byte[] bArr = this.f5103a;
            if (i7 > 0) {
                if (i10 > i7) {
                    System.arraycopy(bArr, i7, bArr, 0, i10 - i7);
                }
                this.f5109g += i7;
                this.f5104b -= i7;
                this.f5106d = 0;
            }
            int i11 = this.f5104b;
            int i12 = inputStream.read(bArr, i11, bArr.length - i11);
            if (i12 == 0 || i12 < -1 || i12 > bArr.length) {
                StringBuilder sb2 = new StringBuilder(102);
                sb2.append("InputStream#read(byte[]) returned invalid result: ");
                sb2.append(i12);
                sb2.append("\nThe InputStream implementation is buggy.");
                throw new IllegalStateException(sb2.toString());
            }
            if (i12 > 0) {
                this.f5104b += i12;
                if ((this.f5109g + i5) - 67108864 > 0) {
                    throw new C0105t("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
                }
                o();
                if (this.f5104b >= i5) {
                    return true;
                }
                return s(i5);
            }
        }
        return false;
    }
}
