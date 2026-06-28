package V7;

import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;

/* JADX INFO: renamed from: V7.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0087a extends FilterInputStream {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f5095e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5096f;

    public C0087a(r2.e eVar) {
        super(eVar);
        this.f5096f = Integer.MIN_VALUE;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int available() {
        switch (this.f5095e) {
            case 0:
                return Math.min(super.available(), this.f5096f);
            default:
                int i5 = this.f5096f;
                return i5 == Integer.MIN_VALUE ? super.available() : Math.min(i5, super.available());
        }
    }

    public long b(long j5) {
        int i5 = this.f5096f;
        if (i5 == 0) {
            return -1L;
        }
        return (i5 == Integer.MIN_VALUE || j5 <= ((long) i5)) ? j5 : i5;
    }

    public void e(long j5) {
        int i5 = this.f5096f;
        if (i5 == Integer.MIN_VALUE || j5 == -1) {
            return;
        }
        this.f5096f = (int) (((long) i5) - j5);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i5) {
        switch (this.f5095e) {
            case 1:
                synchronized (this) {
                    super.mark(i5);
                    this.f5096f = i5;
                }
                return;
            default:
                super.mark(i5);
                return;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read() throws IOException {
        switch (this.f5095e) {
            case 0:
                if (this.f5096f <= 0) {
                    return -1;
                }
                int i5 = super.read();
                if (i5 >= 0) {
                    this.f5096f--;
                }
                return i5;
            default:
                if (b(1L) == -1) {
                    return -1;
                }
                int i7 = super.read();
                e(1L);
                return i7;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        switch (this.f5095e) {
            case 1:
                synchronized (this) {
                    super.reset();
                    this.f5096f = Integer.MIN_VALUE;
                }
                return;
            default:
                super.reset();
                return;
        }
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final long skip(long j5) throws IOException {
        switch (this.f5095e) {
            case 0:
                long jSkip = super.skip(Math.min(j5, this.f5096f));
                if (jSkip >= 0) {
                    this.f5096f = (int) (((long) this.f5096f) - jSkip);
                }
                return jSkip;
            default:
                long jB = b(j5);
                if (jB == -1) {
                    return 0L;
                }
                long jSkip2 = super.skip(jB);
                e(jSkip2);
                return jSkip2;
        }
    }

    public C0087a(ByteArrayInputStream byteArrayInputStream, int i5) {
        super(byteArrayInputStream);
        this.f5096f = i5;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public final int read(byte[] bArr, int i5, int i7) throws IOException {
        switch (this.f5095e) {
            case 0:
                int i9 = this.f5096f;
                if (i9 <= 0) {
                    return -1;
                }
                int i10 = super.read(bArr, i5, Math.min(i7, i9));
                if (i10 >= 0) {
                    this.f5096f -= i10;
                }
                return i10;
            default:
                int iB = (int) b(i7);
                if (iB == -1) {
                    return -1;
                }
                int i11 = super.read(bArr, i5, iB);
                e(i11);
                return i11;
        }
    }
}
