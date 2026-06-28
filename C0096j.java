package V7;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: V7.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0096j {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final C0096j f5119d = new C0096j(0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f5121b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f5122c = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final E f5120a = new E(16);

    public C0096j() {
    }

    public static int c(S s8, Object obj) {
        switch (s8.ordinal()) {
            case 0:
                ((Double) obj).getClass();
                return 8;
            case 1:
                ((Float) obj).getClass();
                return 4;
            case 2:
                return C0093g.g(((Long) obj).longValue());
            case 3:
                return C0093g.g(((Long) obj).longValue());
            case 4:
                return C0093g.c(((Integer) obj).intValue());
            case 5:
                ((Long) obj).getClass();
                return 8;
            case 6:
                ((Integer) obj).getClass();
                return 4;
            case 7:
                ((Boolean) obj).getClass();
                return 1;
            case 8:
                try {
                    byte[] bytes = ((String) obj).getBytes("UTF-8");
                    return C0093g.f(bytes.length) + bytes.length;
                } catch (UnsupportedEncodingException e3) {
                    throw new RuntimeException("UTF-8 not supported.", e3);
                }
            case 9:
                return ((AbstractC0088b) obj).c();
            case 10:
                return C0093g.e((AbstractC0088b) obj);
            case 11:
                if (obj instanceof AbstractC0091e) {
                    AbstractC0091e abstractC0091e = (AbstractC0091e) obj;
                    return abstractC0091e.size() + C0093g.f(abstractC0091e.size());
                }
                byte[] bArr = (byte[]) obj;
                return C0093g.f(bArr.length) + bArr.length;
            case 12:
                return C0093g.f(((Integer) obj).intValue());
            case 13:
                return obj instanceof InterfaceC0103q ? C0093g.c(((InterfaceC0103q) obj).a()) : C0093g.c(((Integer) obj).intValue());
            case 14:
                ((Integer) obj).getClass();
                return 4;
            case 15:
                ((Long) obj).getClass();
                return 8;
            case 16:
                int iIntValue = ((Integer) obj).intValue();
                return C0093g.f((iIntValue >> 31) ^ (iIntValue << 1));
            case 17:
                long jLongValue = ((Long) obj).longValue();
                return C0093g.g((jLongValue >> 63) ^ (jLongValue << 1));
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int d(C0100n c0100n, Object obj) {
        S s8 = c0100n.f5128f;
        boolean z9 = c0100n.f5129g;
        int i5 = c0100n.f5127e;
        if (!z9) {
            int iH = C0093g.h(i5);
            if (s8 == S.f5080i) {
                iH *= 2;
            }
            return c(s8, obj) + iH;
        }
        int iC = 0;
        if (c0100n.h) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                iC += c(s8, it.next());
            }
            return C0093g.f(iC) + C0093g.h(i5) + iC;
        }
        for (Object obj2 : (List) obj) {
            int iH2 = C0093g.h(i5);
            if (s8 == S.f5080i) {
                iH2 *= 2;
            }
            iC += c(s8, obj2) + iH2;
        }
        return iC;
    }

    public static boolean e(Map.Entry entry) {
        C0100n c0100n = (C0100n) entry.getKey();
        if (c0100n.f5128f.f5084e != T.MESSAGE) {
            return true;
        }
        if (!c0100n.f5129g) {
            Object value = entry.getValue();
            if (value instanceof AbstractC0088b) {
                return ((AbstractC0088b) value).b();
            }
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        Iterator it = ((List) entry.getValue()).iterator();
        while (it.hasNext()) {
            if (!((AbstractC0088b) it.next()).b()) {
                return false;
            }
        }
        return true;
    }

    public static Object h(C0092f c0092f, S s8) {
        switch (s8.ordinal()) {
            case 0:
                return Double.valueOf(Double.longBitsToDouble(c0092f.j()));
            case 1:
                return Float.valueOf(Float.intBitsToFloat(c0092f.i()));
            case 2:
                return Long.valueOf(c0092f.l());
            case 3:
                return Long.valueOf(c0092f.l());
            case 4:
                return Integer.valueOf(c0092f.k());
            case 5:
                return Long.valueOf(c0092f.j());
            case 6:
                return Integer.valueOf(c0092f.i());
            case 7:
                return Boolean.valueOf(c0092f.l() != 0);
            case 8:
                int iK = c0092f.k();
                int i5 = c0092f.f5104b;
                int i7 = c0092f.f5106d;
                if (iK > i5 - i7 || iK <= 0) {
                    return iK == 0 ? "" : new String(c0092f.h(iK), "UTF-8");
                }
                String str = new String(c0092f.f5103a, i7, iK, "UTF-8");
                c0092f.f5106d += iK;
                return str;
            case 9:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle nested groups.");
            case 10:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle embedded messages.");
            case 11:
                return c0092f.e();
            case 12:
                return Integer.valueOf(c0092f.k());
            case 13:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle enums.");
            case 14:
                return Integer.valueOf(c0092f.i());
            case 15:
                return Long.valueOf(c0092f.j());
            case 16:
                int iK2 = c0092f.k();
                return Integer.valueOf((-(iK2 & 1)) ^ (iK2 >>> 1));
            case 17:
                long jL = c0092f.l();
                return Long.valueOf((-(jL & 1)) ^ (jL >>> 1));
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void j(V7.S r2, java.lang.Object r3) {
        /*
            r3.getClass()
            V7.T r2 = r2.f5084e
            int r2 = r2.ordinal()
            r0 = 1
            r1 = 0
            switch(r2) {
                case 0: goto L36;
                case 1: goto L33;
                case 2: goto L30;
                case 3: goto L2d;
                case 4: goto L2a;
                case 5: goto L27;
                case 6: goto L1e;
                case 7: goto L12;
                case 8: goto Lf;
                default: goto Le;
            }
        Le:
            goto L38
        Lf:
            boolean r1 = r3 instanceof V7.AbstractC0088b
            goto L38
        L12:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L1c
            boolean r2 = r3 instanceof V7.InterfaceC0103q
            if (r2 == 0) goto L1b
            goto L1c
        L1b:
            r0 = r1
        L1c:
            r1 = r0
            goto L38
        L1e:
            boolean r2 = r3 instanceof V7.AbstractC0091e
            if (r2 != 0) goto L1c
            boolean r2 = r3 instanceof byte[]
            if (r2 == 0) goto L1b
            goto L1c
        L27:
            boolean r1 = r3 instanceof java.lang.String
            goto L38
        L2a:
            boolean r1 = r3 instanceof java.lang.Boolean
            goto L38
        L2d:
            boolean r1 = r3 instanceof java.lang.Double
            goto L38
        L30:
            boolean r1 = r3 instanceof java.lang.Float
            goto L38
        L33:
            boolean r1 = r3 instanceof java.lang.Long
            goto L38
        L36:
            boolean r1 = r3 instanceof java.lang.Integer
        L38:
            if (r1 == 0) goto L3b
            return
        L3b:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Wrong object type used with protocol message reflection."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: V7.C0096j.j(V7.S, java.lang.Object):void");
    }

    public static void k(C0093g c0093g, S s8, int i5, Object obj) {
        if (s8 != S.f5080i) {
            c0093g.x(i5, s8.f5085f);
            l(c0093g, s8, obj);
        } else {
            c0093g.x(i5, 3);
            ((AbstractC0088b) obj).f(c0093g);
            c0093g.x(i5, 4);
        }
    }

    public static void l(C0093g c0093g, S s8, Object obj) {
        switch (s8.ordinal()) {
            case 0:
                double dDoubleValue = ((Double) obj).doubleValue();
                c0093g.getClass();
                c0093g.u(Double.doubleToRawLongBits(dDoubleValue));
                break;
            case 1:
                float fFloatValue = ((Float) obj).floatValue();
                c0093g.getClass();
                c0093g.t(Float.floatToRawIntBits(fFloatValue));
                break;
            case 2:
                c0093g.w(((Long) obj).longValue());
                break;
            case 3:
                c0093g.w(((Long) obj).longValue());
                break;
            case 4:
                c0093g.n(((Integer) obj).intValue());
                break;
            case 5:
                c0093g.u(((Long) obj).longValue());
                break;
            case 6:
                c0093g.t(((Integer) obj).intValue());
                break;
            case 7:
                c0093g.q(((Boolean) obj).booleanValue() ? 1 : 0);
                break;
            case 8:
                c0093g.getClass();
                byte[] bytes = ((String) obj).getBytes("UTF-8");
                c0093g.v(bytes.length);
                c0093g.s(bytes);
                break;
            case 9:
                c0093g.getClass();
                ((AbstractC0088b) obj).f(c0093g);
                break;
            case 10:
                c0093g.p((AbstractC0088b) obj);
                break;
            case 11:
                if (!(obj instanceof AbstractC0091e)) {
                    byte[] bArr = (byte[]) obj;
                    c0093g.getClass();
                    c0093g.v(bArr.length);
                    c0093g.s(bArr);
                } else {
                    AbstractC0091e abstractC0091e = (AbstractC0091e) obj;
                    c0093g.getClass();
                    c0093g.v(abstractC0091e.size());
                    c0093g.r(abstractC0091e);
                }
                break;
            case 12:
                c0093g.v(((Integer) obj).intValue());
                break;
            case 13:
                if (!(obj instanceof InterfaceC0103q)) {
                    c0093g.n(((Integer) obj).intValue());
                } else {
                    c0093g.n(((InterfaceC0103q) obj).a());
                }
                break;
            case 14:
                c0093g.t(((Integer) obj).intValue());
                break;
            case 15:
                c0093g.u(((Long) obj).longValue());
                break;
            case 16:
                int iIntValue = ((Integer) obj).intValue();
                c0093g.v((iIntValue >> 31) ^ (iIntValue << 1));
                break;
            case 17:
                long jLongValue = ((Long) obj).longValue();
                c0093g.w((jLongValue >> 63) ^ (jLongValue << 1));
                break;
        }
    }

    public final void a(C0100n c0100n, Object obj) {
        List arrayList;
        if (!c0100n.f5129g) {
            throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
        }
        j(c0100n.f5128f, obj);
        E e3 = this.f5120a;
        Object obj2 = e3.get(c0100n);
        if (obj2 == null) {
            arrayList = new ArrayList();
            e3.put(c0100n, arrayList);
        } else {
            arrayList = (List) obj2;
        }
        arrayList.add(obj);
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final C0096j clone() {
        E e3;
        C0096j c0096j = new C0096j();
        int i5 = 0;
        while (true) {
            e3 = this.f5120a;
            if (i5 >= e3.f5065f.size()) {
                break;
            }
            Map.Entry entry = (Map.Entry) e3.f5065f.get(i5);
            c0096j.i((C0100n) entry.getKey(), entry.getValue());
            i5++;
        }
        for (Map.Entry entry2 : e3.c()) {
            c0096j.i((C0100n) entry2.getKey(), entry2.getValue());
        }
        c0096j.f5122c = this.f5122c;
        return c0096j;
    }

    public final void f() {
        if (this.f5121b) {
            return;
        }
        E e3 = this.f5120a;
        if (!e3.h) {
            for (int i5 = 0; i5 < e3.f5065f.size(); i5++) {
                Map.Entry entry = (Map.Entry) e3.f5065f.get(i5);
                if (((C0100n) entry.getKey()).f5129g) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
            for (Map.Entry entry2 : e3.c()) {
                if (((C0100n) entry2.getKey()).f5129g) {
                    entry2.setValue(Collections.unmodifiableList((List) entry2.getValue()));
                }
            }
        }
        if (!e3.h) {
            e3.f5066g = e3.f5066g.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(e3.f5066g);
            e3.h = true;
        }
        this.f5121b = true;
    }

    public final void g(Map.Entry entry) {
        C0100n c0100n = (C0100n) entry.getKey();
        Object value = entry.getValue();
        boolean z9 = c0100n.f5129g;
        E e3 = this.f5120a;
        if (z9) {
            Object arrayList = e3.get(c0100n);
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            for (Object obj : (List) value) {
                List list = (List) arrayList;
                if (obj instanceof byte[]) {
                    byte[] bArr = (byte[]) obj;
                    byte[] bArr2 = new byte[bArr.length];
                    System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                    obj = bArr2;
                }
                list.add(obj);
            }
            e3.put(c0100n, arrayList);
            return;
        }
        if (c0100n.f5128f.f5084e != T.MESSAGE) {
            if (value instanceof byte[]) {
                byte[] bArr3 = (byte[]) value;
                byte[] bArr4 = new byte[bArr3.length];
                System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
                value = bArr4;
            }
            e3.put(c0100n, value);
            return;
        }
        Object obj2 = e3.get(c0100n);
        if (obj2 != null) {
            e3.put(c0100n, ((AbstractC0088b) obj2).e().e((AbstractC0102p) ((AbstractC0088b) value)).c());
            return;
        }
        if (value instanceof byte[]) {
            byte[] bArr5 = (byte[]) value;
            byte[] bArr6 = new byte[bArr5.length];
            System.arraycopy(bArr5, 0, bArr6, 0, bArr5.length);
            value = bArr6;
        }
        e3.put(c0100n, value);
    }

    public final void i(C0100n c0100n, Object obj) {
        boolean z9 = c0100n.f5129g;
        S s8 = c0100n.f5128f;
        if (!z9) {
            j(s8, obj);
        } else {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                j(s8, it.next());
            }
            obj = arrayList;
        }
        this.f5120a.put(c0100n, obj);
    }

    public C0096j(int i5) {
        f();
    }
}
