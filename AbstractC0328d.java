package androidx.recyclerview.widget;

import android.view.View;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutorService;

/* JADX INFO: renamed from: androidx.recyclerview.widget.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0328d {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static ExecutorService f9122b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f9121a = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final G4.d f9123c = new G4.d(3);

    public static C0361u c(AbstractC0328d abstractC0328d) {
        ArrayList arrayList;
        ArrayList arrayList2;
        C0367x c0367x;
        C0369y c0369y;
        ArrayList arrayList3;
        ArrayList arrayList4;
        int i5;
        C0367x c0367x2;
        C0367x c0367x3;
        int i7;
        int i9;
        C0369y c0369y2;
        C0369y c0369y3;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16 = abstractC0328d.i();
        int iH = abstractC0328d.h();
        ArrayList arrayList5 = new ArrayList();
        ArrayList arrayList6 = new ArrayList();
        C0367x c0367x4 = new C0367x();
        int i17 = 0;
        c0367x4.f9286a = 0;
        c0367x4.f9287b = i16;
        c0367x4.f9288c = 0;
        c0367x4.f9289d = iH;
        arrayList6.add(c0367x4);
        int i18 = i16 + iH;
        int i19 = 1;
        int i20 = (((i18 + 1) / 2) * 2) + 1;
        int[] iArr = new int[i20];
        int i21 = i20 / 2;
        int[] iArr2 = new int[i20];
        ArrayList arrayList7 = new ArrayList();
        while (!arrayList6.isEmpty()) {
            C0367x c0367x5 = (C0367x) arrayList6.remove(arrayList6.size() - i19);
            if (c0367x5.b() < i19 || c0367x5.a() < i19) {
                arrayList = arrayList6;
                arrayList2 = arrayList7;
                c0367x = c0367x5;
                c0369y = null;
            } else {
                int iA = ((c0367x5.a() + c0367x5.b()) + i19) / 2;
                int i22 = i19 + i21;
                iArr[i22] = c0367x5.f9286a;
                iArr2[i22] = c0367x5.f9287b;
                int i23 = i17;
                while (i23 < iA) {
                    int i24 = Math.abs(c0367x5.b() - c0367x5.a()) % 2 == i19 ? i19 : i17;
                    int iB = c0367x5.b() - c0367x5.a();
                    int i25 = -i23;
                    int i26 = i25;
                    while (true) {
                        if (i26 > i23) {
                            arrayList = arrayList6;
                            i7 = i17;
                            arrayList2 = arrayList7;
                            i9 = iA;
                            c0369y2 = null;
                            break;
                        }
                        if (i26 == i25 || (i26 != i23 && iArr[i26 + 1 + i21] > iArr[(i26 - 1) + i21])) {
                            i13 = iArr[i26 + 1 + i21];
                            i14 = i13;
                        } else {
                            i13 = iArr[(i26 - 1) + i21];
                            i14 = i13 + 1;
                        }
                        i9 = iA;
                        arrayList = arrayList6;
                        int i27 = ((i14 - c0367x5.f9286a) + c0367x5.f9288c) - i26;
                        int i28 = (i23 == 0 || i14 != i13) ? i27 : i27 - 1;
                        arrayList2 = arrayList7;
                        while (i14 < c0367x5.f9287b && i27 < c0367x5.f9289d && abstractC0328d.b(i14, i27)) {
                            i14++;
                            i27++;
                        }
                        iArr[i26 + i21] = i14;
                        if (i24 != 0) {
                            int i29 = iB - i26;
                            i15 = i24;
                            if (i29 >= i25 + 1 && i29 <= i23 - 1 && iArr2[i29 + i21] <= i14) {
                                c0369y2 = new C0369y();
                                c0369y2.f9294a = i13;
                                c0369y2.f9295b = i28;
                                c0369y2.f9296c = i14;
                                c0369y2.f9297d = i27;
                                i7 = 0;
                                c0369y2.f9298e = false;
                                break;
                            }
                        } else {
                            i15 = i24;
                        }
                        i26 += 2;
                        i17 = 0;
                        iA = i9;
                        arrayList6 = arrayList;
                        arrayList7 = arrayList2;
                        i24 = i15;
                    }
                    if (c0369y2 != null) {
                        c0369y = c0369y2;
                        c0367x = c0367x5;
                        break;
                    }
                    int i30 = (c0367x5.b() - c0367x5.a()) % 2 == 0 ? 1 : i7;
                    int iB2 = c0367x5.b() - c0367x5.a();
                    int i31 = i25;
                    while (true) {
                        if (i31 > i23) {
                            c0367x = c0367x5;
                            c0369y3 = null;
                            break;
                        }
                        if (i31 == i25 || (i31 != i23 && iArr2[i31 + 1 + i21] < iArr2[(i31 - 1) + i21])) {
                            i10 = iArr2[i31 + 1 + i21];
                            i11 = i10;
                        } else {
                            i10 = iArr2[(i31 - 1) + i21];
                            i11 = i10 - 1;
                        }
                        int i32 = c0367x5.f9289d - ((c0367x5.f9287b - i11) - i31);
                        int i33 = (i23 == 0 || i11 != i10) ? i32 : i32 + 1;
                        while (i11 > c0367x5.f9286a && i32 > c0367x5.f9288c) {
                            c0367x = c0367x5;
                            if (!abstractC0328d.b(i11 - 1, i32 - 1)) {
                                break;
                            }
                            i11--;
                            i32--;
                            c0367x5 = c0367x;
                        }
                        c0367x = c0367x5;
                        iArr2[i31 + i21] = i11;
                        if (i30 != 0 && (i12 = iB2 - i31) >= i25 && i12 <= i23 && iArr[i12 + i21] >= i11) {
                            c0369y3 = new C0369y();
                            c0369y3.f9294a = i11;
                            c0369y3.f9295b = i32;
                            c0369y3.f9296c = i10;
                            c0369y3.f9297d = i33;
                            c0369y3.f9298e = true;
                            break;
                        }
                        i31 += 2;
                        c0367x5 = c0367x;
                    }
                    if (c0369y3 != null) {
                        c0369y = c0369y3;
                        break;
                    }
                    i23++;
                    iA = i9;
                    arrayList6 = arrayList;
                    arrayList7 = arrayList2;
                    c0367x5 = c0367x;
                    i19 = 1;
                    i17 = 0;
                }
                arrayList = arrayList6;
                arrayList2 = arrayList7;
                c0367x = c0367x5;
                c0369y = null;
            }
            if (c0369y != null) {
                if (c0369y.a() > 0) {
                    int i34 = c0369y.f9297d;
                    int i35 = c0369y.f9295b;
                    int i36 = i34 - i35;
                    int i37 = c0369y.f9296c;
                    int i38 = c0369y.f9294a;
                    int i39 = i37 - i38;
                    arrayList5.add(i36 != i39 ? c0369y.f9298e ? new C0359t(i38, i35, c0369y.a()) : i36 > i39 ? new C0359t(i38, i35 + 1, c0369y.a()) : new C0359t(i38 + 1, i35, c0369y.a()) : new C0359t(i38, i35, i39));
                }
                if (arrayList2.isEmpty()) {
                    c0367x2 = new C0367x();
                    arrayList4 = arrayList2;
                    c0367x3 = c0367x;
                    i5 = 1;
                } else {
                    i5 = 1;
                    arrayList4 = arrayList2;
                    c0367x2 = (C0367x) arrayList4.remove(arrayList2.size() - 1);
                    c0367x3 = c0367x;
                }
                c0367x2.f9286a = c0367x3.f9286a;
                c0367x2.f9288c = c0367x3.f9288c;
                c0367x2.f9287b = c0369y.f9294a;
                c0367x2.f9289d = c0369y.f9295b;
                arrayList3 = arrayList;
                arrayList3.add(c0367x2);
                c0367x3.f9287b = c0367x3.f9287b;
                c0367x3.f9289d = c0367x3.f9289d;
                c0367x3.f9286a = c0369y.f9296c;
                c0367x3.f9288c = c0369y.f9297d;
                arrayList3.add(c0367x3);
            } else {
                arrayList3 = arrayList;
                arrayList4 = arrayList2;
                i5 = 1;
                arrayList4.add(c0367x);
            }
            i19 = i5;
            arrayList6 = arrayList3;
            arrayList7 = arrayList4;
            i17 = 0;
        }
        Collections.sort(arrayList5, f9123c);
        return new C0361u(abstractC0328d, arrayList5, iArr, iArr2);
    }

    public static int d(R0 r02, Z z9, View view, View view2, AbstractC0370y0 abstractC0370y0, boolean z10) {
        if (abstractC0370y0.getChildCount() == 0 || r02.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z10) {
            return Math.abs(abstractC0370y0.getPosition(view) - abstractC0370y0.getPosition(view2)) + 1;
        }
        return Math.min(z9.l(), z9.b(view2) - z9.e(view));
    }

    public static int e(R0 r02, Z z9, View view, View view2, AbstractC0370y0 abstractC0370y0, boolean z10, boolean z11) {
        if (abstractC0370y0.getChildCount() == 0 || r02.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        int iMax = z11 ? Math.max(0, (r02.b() - Math.max(abstractC0370y0.getPosition(view), abstractC0370y0.getPosition(view2))) - 1) : Math.max(0, Math.min(abstractC0370y0.getPosition(view), abstractC0370y0.getPosition(view2)));
        if (z10) {
            return Math.round((iMax * (Math.abs(z9.b(view2) - z9.e(view)) / (Math.abs(abstractC0370y0.getPosition(view) - abstractC0370y0.getPosition(view2)) + 1))) + (z9.k() - z9.e(view)));
        }
        return iMax;
    }

    public static int f(R0 r02, Z z9, View view, View view2, AbstractC0370y0 abstractC0370y0, boolean z10) {
        if (abstractC0370y0.getChildCount() == 0 || r02.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z10) {
            return r02.b();
        }
        return (int) (((z9.b(view2) - z9.e(view)) / (Math.abs(abstractC0370y0.getPosition(view) - abstractC0370y0.getPosition(view2)) + 1)) * r02.b());
    }

    public abstract boolean a(int i5, int i7);

    public abstract boolean b(int i5, int i7);

    public abstract Object g(int i5, int i7);

    public abstract int h();

    public abstract int i();
}
