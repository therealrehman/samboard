package androidx.recyclerview.widget;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: androidx.recyclerview.widget.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0361u {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List f9274a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int[] f9275b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final int[] f9276c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final AbstractC0328d f9277d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f9278e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f9279f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f9280g;

    public C0361u(AbstractC0328d abstractC0328d, ArrayList arrayList, int[] iArr, int[] iArr2) {
        int[] iArr3;
        int[] iArr4;
        AbstractC0328d abstractC0328d2;
        int i5;
        C0359t c0359t;
        int i7;
        this.f9274a = arrayList;
        this.f9275b = iArr;
        this.f9276c = iArr2;
        Arrays.fill(iArr, 0);
        Arrays.fill(iArr2, 0);
        this.f9277d = abstractC0328d;
        int i9 = abstractC0328d.i();
        this.f9278e = i9;
        int iH = abstractC0328d.h();
        this.f9279f = iH;
        this.f9280g = true;
        C0359t c0359t2 = arrayList.isEmpty() ? null : (C0359t) arrayList.get(0);
        if (c0359t2 == null || c0359t2.f9270a != 0 || c0359t2.f9271b != 0) {
            arrayList.add(0, new C0359t(0, 0, 0));
        }
        arrayList.add(new C0359t(i9, iH, 0));
        Iterator it = arrayList.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            iArr3 = this.f9276c;
            iArr4 = this.f9275b;
            abstractC0328d2 = this.f9277d;
            if (!zHasNext) {
                break;
            }
            C0359t c0359t3 = (C0359t) it.next();
            for (int i10 = 0; i10 < c0359t3.f9272c; i10++) {
                int i11 = c0359t3.f9270a + i10;
                int i12 = c0359t3.f9271b + i10;
                int i13 = abstractC0328d2.a(i11, i12) ? 1 : 2;
                iArr4[i11] = (i12 << 4) | i13;
                iArr3[i12] = (i11 << 4) | i13;
            }
        }
        if (this.f9280g) {
            Iterator it2 = arrayList.iterator();
            int i14 = 0;
            while (it2.hasNext()) {
                C0359t c0359t4 = (C0359t) it2.next();
                while (true) {
                    i5 = c0359t4.f9270a;
                    if (i14 < i5) {
                        if (iArr4[i14] == 0) {
                            int size = arrayList.size();
                            int i15 = 0;
                            int i16 = 0;
                            while (true) {
                                if (i15 < size) {
                                    c0359t = (C0359t) arrayList.get(i15);
                                    while (true) {
                                        i7 = c0359t.f9271b;
                                        if (i16 < i7) {
                                            if (iArr3[i16] == 0 && abstractC0328d2.b(i14, i16)) {
                                                int i17 = abstractC0328d2.a(i14, i16) ? 8 : 4;
                                                iArr4[i14] = (i16 << 4) | i17;
                                                iArr3[i16] = i17 | (i14 << 4);
                                            } else {
                                                i16++;
                                            }
                                        }
                                    }
                                }
                                i16 = c0359t.f9272c + i7;
                                i15++;
                            }
                        }
                        i14++;
                    }
                }
                i14 = c0359t4.f9272c + i5;
            }
        }
    }

    public static C0365w b(ArrayDeque arrayDeque, int i5, boolean z9) {
        C0365w c0365w;
        Iterator it = arrayDeque.iterator();
        while (true) {
            if (!it.hasNext()) {
                c0365w = null;
                break;
            }
            c0365w = (C0365w) it.next();
            if (c0365w.f9283a == i5 && c0365w.f9285c == z9) {
                it.remove();
                break;
            }
        }
        while (it.hasNext()) {
            C0365w c0365w2 = (C0365w) it.next();
            if (z9) {
                c0365w2.f9284b--;
            } else {
                c0365w2.f9284b++;
            }
        }
        return c0365w;
    }

    public final void a(X x9) {
        int[] iArr;
        AbstractC0328d abstractC0328d;
        int i5;
        int i7;
        List list;
        int i9;
        C0361u c0361u = this;
        C0340j c0340j = x9 instanceof C0340j ? (C0340j) x9 : new C0340j(x9);
        ArrayDeque arrayDeque = new ArrayDeque();
        List list2 = c0361u.f9274a;
        int size = list2.size() - 1;
        int i10 = c0361u.f9278e;
        int i11 = c0361u.f9279f;
        int i12 = i10;
        while (size >= 0) {
            C0359t c0359t = (C0359t) list2.get(size);
            int i13 = c0359t.f9270a;
            int i14 = c0359t.f9272c;
            int i15 = i13 + i14;
            int i16 = c0359t.f9271b;
            int i17 = i16 + i14;
            while (true) {
                iArr = c0361u.f9275b;
                abstractC0328d = c0361u.f9277d;
                i5 = 0;
                if (i12 <= i15) {
                    break;
                }
                i12--;
                int i18 = iArr[i12];
                if ((i18 & 12) != 0) {
                    list = list2;
                    int i19 = i18 >> 4;
                    C0365w c0365wB = b(arrayDeque, i19, false);
                    if (c0365wB != null) {
                        i9 = i11;
                        int i20 = (i10 - c0365wB.f9284b) - 1;
                        c0340j.e(i12, i20);
                        if ((i18 & 4) != 0) {
                            c0340j.q(i20, 1, abstractC0328d.g(i12, i19));
                        }
                    } else {
                        i9 = i11;
                        arrayDeque.add(new C0365w(i12, (i10 - i12) - 1, true));
                    }
                } else {
                    list = list2;
                    i9 = i11;
                    c0340j.d(i12, 1);
                    i10--;
                }
                list2 = list;
                i11 = i9;
            }
            List list3 = list2;
            while (i11 > i17) {
                i11--;
                int i21 = c0361u.f9276c[i11];
                if ((i21 & 12) != 0) {
                    int i22 = i21 >> 4;
                    C0365w c0365wB2 = b(arrayDeque, i22, true);
                    if (c0365wB2 == null) {
                        arrayDeque.add(new C0365w(i11, i10 - i12, false));
                        i7 = 0;
                    } else {
                        i7 = 0;
                        c0340j.e((i10 - c0365wB2.f9284b) - 1, i12);
                        if ((i21 & 4) != 0) {
                            c0340j.q(i12, 1, abstractC0328d.g(i22, i11));
                        }
                    }
                } else {
                    i7 = i5;
                    c0340j.p(i12, 1);
                    i10++;
                }
                c0361u = this;
                i5 = i7;
            }
            i12 = c0359t.f9270a;
            int i23 = i12;
            int i24 = i16;
            while (i5 < i14) {
                if ((iArr[i23] & 15) == 2) {
                    c0340j.q(i23, 1, abstractC0328d.g(i23, i24));
                }
                i23++;
                i24++;
                i5++;
            }
            size--;
            c0361u = this;
            i11 = i16;
            list2 = list3;
        }
        c0340j.a();
    }
}
