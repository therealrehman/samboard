package androidx.recyclerview.widget;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: renamed from: androidx.recyclerview.widget.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0330e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f9128a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f9129b;

    public void a() {
        int[] iArr = (int[]) this.f9128a;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
        this.f9129b = null;
    }

    public void b(int i5) {
        int[] iArr = (int[]) this.f9128a;
        if (iArr == null) {
            int[] iArr2 = new int[Math.max(i5, 10) + 1];
            this.f9128a = iArr2;
            Arrays.fill(iArr2, -1);
        } else if (i5 >= iArr.length) {
            int length = iArr.length;
            while (length <= i5) {
                length *= 2;
            }
            int[] iArr3 = new int[length];
            this.f9128a = iArr3;
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            int[] iArr4 = (int[]) this.f9128a;
            Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0012  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int c(int r6) {
        /*
            r5 = this;
            java.lang.Object r0 = r5.f9128a
            int[] r0 = (int[]) r0
            r1 = -1
            if (r0 != 0) goto L8
            return r1
        L8:
            int r0 = r0.length
            if (r6 < r0) goto Lc
            return r1
        Lc:
            java.lang.Object r0 = r5.f9129b
            java.util.List r0 = (java.util.List) r0
            if (r0 != 0) goto L14
        L12:
            r0 = r1
            goto L6f
        L14:
            r2 = 0
            if (r0 != 0) goto L18
            goto L33
        L18:
            int r0 = r0.size()
            int r0 = r0 + (-1)
        L1e:
            if (r0 < 0) goto L33
            java.lang.Object r3 = r5.f9129b
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r3 = r3.get(r0)
            androidx.recyclerview.widget.h1 r3 = (androidx.recyclerview.widget.h1) r3
            int r4 = r3.f9147e
            if (r4 != r6) goto L30
            r2 = r3
            goto L33
        L30:
            int r0 = r0 + (-1)
            goto L1e
        L33:
            if (r2 == 0) goto L3c
            java.lang.Object r0 = r5.f9129b
            java.util.List r0 = (java.util.List) r0
            r0.remove(r2)
        L3c:
            java.lang.Object r0 = r5.f9129b
            java.util.List r0 = (java.util.List) r0
            int r0 = r0.size()
            r2 = 0
        L45:
            if (r2 >= r0) goto L59
            java.lang.Object r3 = r5.f9129b
            java.util.List r3 = (java.util.List) r3
            java.lang.Object r3 = r3.get(r2)
            androidx.recyclerview.widget.h1 r3 = (androidx.recyclerview.widget.h1) r3
            int r3 = r3.f9147e
            if (r3 < r6) goto L56
            goto L5a
        L56:
            int r2 = r2 + 1
            goto L45
        L59:
            r2 = r1
        L5a:
            if (r2 == r1) goto L12
            java.lang.Object r0 = r5.f9129b
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r0 = r0.get(r2)
            androidx.recyclerview.widget.h1 r0 = (androidx.recyclerview.widget.h1) r0
            java.lang.Object r3 = r5.f9129b
            java.util.List r3 = (java.util.List) r3
            r3.remove(r2)
            int r0 = r0.f9147e
        L6f:
            if (r0 != r1) goto L7f
            java.lang.Object r0 = r5.f9128a
            int[] r0 = (int[]) r0
            int r2 = r0.length
            java.util.Arrays.fill(r0, r6, r2, r1)
            java.lang.Object r5 = r5.f9128a
            int[] r5 = (int[]) r5
            int r5 = r5.length
            return r5
        L7f:
            int r0 = r0 + 1
            java.lang.Object r2 = r5.f9128a
            int[] r2 = (int[]) r2
            int r2 = r2.length
            int r0 = java.lang.Math.min(r0, r2)
            java.lang.Object r5 = r5.f9128a
            int[] r5 = (int[]) r5
            java.util.Arrays.fill(r5, r6, r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.C0330e.c(int):int");
    }

    public void d(int i5, int i7) {
        int[] iArr = (int[]) this.f9128a;
        if (iArr == null || i5 >= iArr.length) {
            return;
        }
        int i9 = i5 + i7;
        b(i9);
        int[] iArr2 = (int[]) this.f9128a;
        System.arraycopy(iArr2, i5, iArr2, i9, (iArr2.length - i5) - i7);
        Arrays.fill((int[]) this.f9128a, i5, i9, -1);
        List list = (List) this.f9129b;
        if (list == null) {
            return;
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            h1 h1Var = (h1) ((List) this.f9129b).get(size);
            int i10 = h1Var.f9147e;
            if (i10 >= i5) {
                h1Var.f9147e = i10 + i7;
            }
        }
    }

    public void e(int i5, int i7) {
        int[] iArr = (int[]) this.f9128a;
        if (iArr == null || i5 >= iArr.length) {
            return;
        }
        int i9 = i5 + i7;
        b(i9);
        int[] iArr2 = (int[]) this.f9128a;
        System.arraycopy(iArr2, i9, iArr2, i5, (iArr2.length - i5) - i7);
        int[] iArr3 = (int[]) this.f9128a;
        Arrays.fill(iArr3, iArr3.length - i7, iArr3.length, -1);
        List list = (List) this.f9129b;
        if (list == null) {
            return;
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            h1 h1Var = (h1) ((List) this.f9129b).get(size);
            int i10 = h1Var.f9147e;
            if (i10 >= i5) {
                if (i10 < i9) {
                    ((List) this.f9129b).remove(size);
                } else {
                    h1Var.f9147e = i10 - i7;
                }
            }
        }
    }
}
