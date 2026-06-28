package V7;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/* JADX INFO: renamed from: V7.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0089c implements A {
    static {
        int i5 = C0095i.f5117b;
    }

    public final AbstractC0088b b(ByteArrayInputStream byteArrayInputStream, C0095i c0095i) throws C0105t {
        AbstractC0088b abstractC0088b;
        try {
            int i5 = byteArrayInputStream.read();
            if (i5 == -1) {
                abstractC0088b = null;
            } else {
                if ((i5 & 128) != 0) {
                    i5 &= 127;
                    int i7 = 7;
                    while (true) {
                        if (i7 >= 32) {
                            while (i7 < 64) {
                                int i9 = byteArrayInputStream.read();
                                if (i9 == -1) {
                                    throw C0105t.a();
                                }
                                if ((i9 & 128) != 0) {
                                    i7 += 7;
                                }
                            }
                            throw new C0105t("CodedInputStream encountered a malformed varint.");
                        }
                        int i10 = byteArrayInputStream.read();
                        if (i10 == -1) {
                            throw C0105t.a();
                        }
                        i5 |= (i10 & 127) << i7;
                        if ((i10 & 128) == 0) {
                            break;
                        }
                        i7 += 7;
                    }
                }
                C0092f c0092f = new C0092f(new C0087a(byteArrayInputStream, i5));
                abstractC0088b = (AbstractC0088b) a(c0092f, c0095i);
                try {
                    c0092f.a(0);
                } catch (C0105t e3) {
                    e3.f5136e = abstractC0088b;
                    throw e3;
                }
            }
            if (abstractC0088b == null || abstractC0088b.b()) {
                return abstractC0088b;
            }
            C0105t c0105t = new C0105t(new G6.a(3).getMessage());
            c0105t.f5136e = abstractC0088b;
            throw c0105t;
        } catch (IOException e10) {
            throw new C0105t(e10.getMessage());
        }
    }
}
