package androidx.recyclerview.widget;

import java.util.ArrayList;

/* JADX INFO: renamed from: androidx.recyclerview.widget.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0324b {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C0327c0 f9056d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final K.e f9053a = new K.e(30);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f9054b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f9055c = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f9058f = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0326c f9057e = new C0326c(this);

    public C0324b(C0327c0 c0327c0) {
        this.f9056d = c0327c0;
    }

    public final boolean a(int i5) {
        ArrayList arrayList = this.f9055c;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            C0322a c0322a = (C0322a) arrayList.get(i7);
            int i9 = c0322a.f9041a;
            if (i9 == 8) {
                if (f(c0322a.f9044d, i7 + 1) == i5) {
                    return true;
                }
            } else if (i9 == 1) {
                int i10 = c0322a.f9042b;
                int i11 = c0322a.f9044d + i10;
                while (i10 < i11) {
                    if (f(i10, i7 + 1) == i5) {
                        return true;
                    }
                    i10++;
                }
            } else {
                continue;
            }
        }
        return false;
    }

    public final void b() {
        ArrayList arrayList = this.f9055c;
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            this.f9056d.a((C0322a) arrayList.get(i5));
        }
        l(arrayList);
        this.f9058f = 0;
    }

    public final void c() {
        b();
        ArrayList arrayList = this.f9054b;
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            C0322a c0322a = (C0322a) arrayList.get(i5);
            int i7 = c0322a.f9041a;
            C0327c0 c0327c0 = this.f9056d;
            if (i7 == 1) {
                c0327c0.a(c0322a);
                int i9 = c0322a.f9042b;
                int i10 = c0322a.f9044d;
                RecyclerView recyclerView = c0327c0.f9119a;
                recyclerView.offsetPositionRecordsForInsert(i9, i10);
                recyclerView.mItemsAddedOrRemoved = true;
            } else if (i7 == 2) {
                c0327c0.a(c0322a);
                int i11 = c0322a.f9042b;
                int i12 = c0322a.f9044d;
                RecyclerView recyclerView2 = c0327c0.f9119a;
                recyclerView2.offsetPositionRecordsForRemove(i11, i12, true);
                recyclerView2.mItemsAddedOrRemoved = true;
                recyclerView2.mState.f8980c += i12;
            } else if (i7 == 4) {
                c0327c0.a(c0322a);
                int i13 = c0322a.f9042b;
                int i14 = c0322a.f9044d;
                Object obj = c0322a.f9043c;
                RecyclerView recyclerView3 = c0327c0.f9119a;
                recyclerView3.viewRangeUpdate(i13, i14, obj);
                recyclerView3.mItemsChanged = true;
            } else if (i7 == 8) {
                c0327c0.a(c0322a);
                int i15 = c0322a.f9042b;
                int i16 = c0322a.f9044d;
                RecyclerView recyclerView4 = c0327c0.f9119a;
                recyclerView4.offsetPositionRecordsForMove(i15, i16);
                recyclerView4.mItemsAddedOrRemoved = true;
            }
        }
        l(arrayList);
        this.f9058f = 0;
    }

    public final void d(C0322a c0322a) {
        int i5;
        int i7 = c0322a.f9041a;
        if (i7 == 1 || i7 == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int iM = m(c0322a.f9042b, i7);
        int i9 = c0322a.f9042b;
        int i10 = c0322a.f9041a;
        if (i10 == 2) {
            i5 = 0;
        } else {
            if (i10 != 4) {
                throw new IllegalArgumentException("op should be remove or update." + c0322a);
            }
            i5 = 1;
        }
        int i11 = 1;
        for (int i12 = 1; i12 < c0322a.f9044d; i12++) {
            int iM2 = m((i5 * i12) + c0322a.f9042b, c0322a.f9041a);
            int i13 = c0322a.f9041a;
            if (i13 == 2 ? iM2 != iM : !(i13 == 4 && iM2 == iM + 1)) {
                C0322a c0322aH = h(c0322a.f9043c, i13, iM, i11);
                e(c0322aH, i9);
                c0322aH.f9043c = null;
                this.f9053a.c(c0322aH);
                if (c0322a.f9041a == 4) {
                    i9 += i11;
                }
                i11 = 1;
                iM = iM2;
            } else {
                i11++;
            }
        }
        Object obj = c0322a.f9043c;
        c0322a.f9043c = null;
        this.f9053a.c(c0322a);
        if (i11 > 0) {
            C0322a c0322aH2 = h(obj, c0322a.f9041a, iM, i11);
            e(c0322aH2, i9);
            c0322aH2.f9043c = null;
            this.f9053a.c(c0322aH2);
        }
    }

    public final void e(C0322a c0322a, int i5) {
        C0327c0 c0327c0 = this.f9056d;
        c0327c0.a(c0322a);
        int i7 = c0322a.f9041a;
        RecyclerView recyclerView = c0327c0.f9119a;
        if (i7 != 2) {
            if (i7 != 4) {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            recyclerView.viewRangeUpdate(i5, c0322a.f9044d, c0322a.f9043c);
            recyclerView.mItemsChanged = true;
            return;
        }
        int i9 = c0322a.f9044d;
        recyclerView.offsetPositionRecordsForRemove(i5, i9, true);
        recyclerView.mItemsAddedOrRemoved = true;
        recyclerView.mState.f8980c += i9;
    }

    public final int f(int i5, int i7) {
        ArrayList arrayList = this.f9055c;
        int size = arrayList.size();
        while (i7 < size) {
            C0322a c0322a = (C0322a) arrayList.get(i7);
            int i9 = c0322a.f9041a;
            if (i9 == 8) {
                int i10 = c0322a.f9042b;
                if (i10 == i5) {
                    i5 = c0322a.f9044d;
                } else {
                    if (i10 < i5) {
                        i5--;
                    }
                    if (c0322a.f9044d <= i5) {
                        i5++;
                    }
                }
            } else {
                int i11 = c0322a.f9042b;
                if (i11 > i5) {
                    continue;
                } else if (i9 == 2) {
                    int i12 = c0322a.f9044d;
                    if (i5 < i11 + i12) {
                        return -1;
                    }
                    i5 -= i12;
                } else if (i9 == 1) {
                    i5 += c0322a.f9044d;
                }
            }
            i7++;
        }
        return i5;
    }

    public final boolean g() {
        return this.f9054b.size() > 0;
    }

    public final C0322a h(Object obj, int i5, int i7, int i9) {
        C0322a c0322a = (C0322a) this.f9053a.h();
        if (c0322a != null) {
            c0322a.f9041a = i5;
            c0322a.f9042b = i7;
            c0322a.f9044d = i9;
            c0322a.f9043c = obj;
            return c0322a;
        }
        C0322a c0322a2 = new C0322a();
        c0322a2.f9041a = i5;
        c0322a2.f9042b = i7;
        c0322a2.f9044d = i9;
        c0322a2.f9043c = obj;
        return c0322a2;
    }

    public final void i(C0322a c0322a) {
        this.f9055c.add(c0322a);
        int i5 = c0322a.f9041a;
        C0327c0 c0327c0 = this.f9056d;
        if (i5 == 1) {
            int i7 = c0322a.f9042b;
            int i9 = c0322a.f9044d;
            RecyclerView recyclerView = c0327c0.f9119a;
            recyclerView.offsetPositionRecordsForInsert(i7, i9);
            recyclerView.mItemsAddedOrRemoved = true;
            return;
        }
        if (i5 == 2) {
            int i10 = c0322a.f9042b;
            int i11 = c0322a.f9044d;
            RecyclerView recyclerView2 = c0327c0.f9119a;
            recyclerView2.offsetPositionRecordsForRemove(i10, i11, false);
            recyclerView2.mItemsAddedOrRemoved = true;
            return;
        }
        if (i5 == 4) {
            int i12 = c0322a.f9042b;
            int i13 = c0322a.f9044d;
            Object obj = c0322a.f9043c;
            RecyclerView recyclerView3 = c0327c0.f9119a;
            recyclerView3.viewRangeUpdate(i12, i13, obj);
            recyclerView3.mItemsChanged = true;
            return;
        }
        if (i5 != 8) {
            throw new IllegalArgumentException("Unknown update op type for " + c0322a);
        }
        int i14 = c0322a.f9042b;
        int i15 = c0322a.f9044d;
        RecyclerView recyclerView4 = c0327c0.f9119a;
        recyclerView4.offsetPositionRecordsForMove(i14, i15);
        recyclerView4.mItemsAddedOrRemoved = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:184:0x00a4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:185:0x012b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0119 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0009 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x009f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void j() {
        /*
            Method dump skipped, instruction units count: 676
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.C0324b.j():void");
    }

    public final void k(C0322a c0322a) {
        c0322a.f9043c = null;
        this.f9053a.c(c0322a);
    }

    public final void l(ArrayList arrayList) {
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            k((C0322a) arrayList.get(i5));
        }
        arrayList.clear();
    }

    public final int m(int i5, int i7) {
        int i9;
        int i10;
        ArrayList arrayList = this.f9055c;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            C0322a c0322a = (C0322a) arrayList.get(size);
            int i11 = c0322a.f9041a;
            if (i11 == 8) {
                int i12 = c0322a.f9042b;
                int i13 = c0322a.f9044d;
                if (i12 < i13) {
                    i10 = i12;
                    i9 = i13;
                } else {
                    i9 = i12;
                    i10 = i13;
                }
                if (i5 < i10 || i5 > i9) {
                    if (i5 < i12) {
                        if (i7 == 1) {
                            c0322a.f9042b = i12 + 1;
                            c0322a.f9044d = i13 + 1;
                        } else if (i7 == 2) {
                            c0322a.f9042b = i12 - 1;
                            c0322a.f9044d = i13 - 1;
                        }
                    }
                } else if (i10 == i12) {
                    if (i7 == 1) {
                        c0322a.f9044d = i13 + 1;
                    } else if (i7 == 2) {
                        c0322a.f9044d = i13 - 1;
                    }
                    i5++;
                } else {
                    if (i7 == 1) {
                        c0322a.f9042b = i12 + 1;
                    } else if (i7 == 2) {
                        c0322a.f9042b = i12 - 1;
                    }
                    i5--;
                }
            } else {
                int i14 = c0322a.f9042b;
                if (i14 <= i5) {
                    if (i11 == 1) {
                        i5 -= c0322a.f9044d;
                    } else if (i11 == 2) {
                        i5 += c0322a.f9044d;
                    }
                } else if (i7 == 1) {
                    c0322a.f9042b = i14 + 1;
                } else if (i7 == 2) {
                    c0322a.f9042b = i14 - 1;
                }
            }
        }
        for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
            C0322a c0322a2 = (C0322a) arrayList.get(size2);
            if (c0322a2.f9041a == 8) {
                int i15 = c0322a2.f9044d;
                if (i15 == c0322a2.f9042b || i15 < 0) {
                    arrayList.remove(size2);
                    k(c0322a2);
                }
            } else if (c0322a2.f9044d <= 0) {
                arrayList.remove(size2);
                k(c0322a2);
            }
        }
        return i5;
    }
}
