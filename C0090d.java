package V7;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: renamed from: V7.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0090d extends OutputStream {

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final byte[] f5097j = new byte[0];

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f5100g;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f5101i;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f5098e = 128;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f5099f = new ArrayList();
    public byte[] h = new byte[128];

    public final void b(int i5) {
        this.f5099f.add(new y(this.h));
        int length = this.f5100g + this.h.length;
        this.f5100g = length;
        this.h = new byte[Math.max(this.f5098e, Math.max(i5, length >>> 1))];
        this.f5101i = 0;
    }

    public final void e() {
        int i5 = this.f5101i;
        byte[] bArr = this.h;
        int length = bArr.length;
        ArrayList arrayList = this.f5099f;
        if (i5 >= length) {
            arrayList.add(new y(this.h));
            this.h = f5097j;
        } else if (i5 > 0) {
            byte[] bArr2 = new byte[i5];
            System.arraycopy(bArr, 0, bArr2, 0, Math.min(bArr.length, i5));
            arrayList.add(new y(bArr2));
        }
        this.f5100g += this.f5101i;
        this.f5101i = 0;
    }

    public final synchronized AbstractC0091e h() {
        ArrayList arrayList;
        e();
        arrayList = this.f5099f;
        if (!(arrayList instanceof Collection)) {
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add((AbstractC0091e) it.next());
            }
            arrayList = arrayList2;
        }
        return arrayList.isEmpty() ? AbstractC0091e.f5102e : AbstractC0091e.g(arrayList.iterator(), arrayList.size());
    }

    public final String toString() {
        int i5;
        String hexString = Integer.toHexString(System.identityHashCode(this));
        synchronized (this) {
            i5 = this.f5100g + this.f5101i;
        }
        return String.format("<ByteString.Output@%s size=%d>", hexString, Integer.valueOf(i5));
    }

    @Override // java.io.OutputStream
    public final synchronized void write(int i5) {
        try {
            if (this.f5101i == this.h.length) {
                b(1);
            }
            byte[] bArr = this.h;
            int i7 = this.f5101i;
            this.f5101i = i7 + 1;
            bArr[i7] = (byte) i5;
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // java.io.OutputStream
    public final synchronized void write(byte[] bArr, int i5, int i7) {
        try {
            byte[] bArr2 = this.h;
            int length = bArr2.length;
            int i9 = this.f5101i;
            if (i7 <= length - i9) {
                System.arraycopy(bArr, i5, bArr2, i9, i7);
                this.f5101i += i7;
            } else {
                int length2 = bArr2.length - i9;
                System.arraycopy(bArr, i5, bArr2, i9, length2);
                int i10 = i7 - length2;
                b(i10);
                System.arraycopy(bArr, i5 + length2, this.h, 0, i10);
                this.f5101i = i10;
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
