package V7;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

/* JADX INFO: renamed from: V7.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0091e implements Iterable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final y f5102e = new y(new byte[0]);

    public static AbstractC0091e g(Iterator it, int i5) {
        if (i5 == 1) {
            return (AbstractC0091e) it.next();
        }
        int i7 = i5 >>> 1;
        return g(it, i7).h(g(it, i5 - i7));
    }

    public static C0090d n() {
        return new C0090d();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final V7.AbstractC0091e h(V7.AbstractC0091e r8) {
        /*
            Method dump skipped, instruction units count: 269
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: V7.AbstractC0091e.h(V7.e):V7.e");
    }

    public final void i(byte[] bArr, int i5, int i7, int i9) {
        if (i5 < 0) {
            StringBuilder sb = new StringBuilder(30);
            sb.append("Source offset < 0: ");
            sb.append(i5);
            throw new IndexOutOfBoundsException(sb.toString());
        }
        if (i7 < 0) {
            StringBuilder sb2 = new StringBuilder(30);
            sb2.append("Target offset < 0: ");
            sb2.append(i7);
            throw new IndexOutOfBoundsException(sb2.toString());
        }
        if (i9 < 0) {
            StringBuilder sb3 = new StringBuilder(23);
            sb3.append("Length < 0: ");
            sb3.append(i9);
            throw new IndexOutOfBoundsException(sb3.toString());
        }
        int i10 = i5 + i9;
        if (i10 > size()) {
            StringBuilder sb4 = new StringBuilder(34);
            sb4.append("Source end offset < 0: ");
            sb4.append(i10);
            throw new IndexOutOfBoundsException(sb4.toString());
        }
        int i11 = i7 + i9;
        if (i11 <= bArr.length) {
            if (i9 > 0) {
                j(bArr, i5, i7, i9);
            }
        } else {
            StringBuilder sb5 = new StringBuilder(34);
            sb5.append("Target end offset < 0: ");
            sb5.append(i11);
            throw new IndexOutOfBoundsException(sb5.toString());
        }
    }

    public abstract void j(byte[] bArr, int i5, int i7, int i9);

    public abstract int k();

    public abstract boolean l();

    public abstract boolean m();

    public abstract int o(int i5, int i7, int i9);

    public abstract int p(int i5, int i7, int i9);

    public abstract int q();

    public abstract String r();

    public final String s() {
        try {
            return r();
        } catch (UnsupportedEncodingException e3) {
            throw new RuntimeException("UTF-8 not supported?", e3);
        }
    }

    public abstract int size();

    public abstract void t(OutputStream outputStream, int i5, int i7);

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size()));
    }
}
