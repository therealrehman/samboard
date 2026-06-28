package androidx.transition;

/* JADX INFO: renamed from: androidx.transition.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0390s extends m0 {
    /* JADX WARN: Removed duplicated region for block: B:18:0x0026  */
    @Override // androidx.transition.Z
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long a(android.view.ViewGroup r9, androidx.transition.U r10, androidx.transition.c0 r11, androidx.transition.c0 r12) {
        /*
            r8 = this;
            r0 = 0
            if (r11 != 0) goto L7
            if (r12 != 0) goto L7
            return r0
        L7:
            r8 = 1
            if (r12 == 0) goto L26
            r2 = 8
            if (r11 != 0) goto Lf
            goto L20
        Lf:
            java.util.HashMap r3 = r11.f9407a
            java.lang.String r4 = "android:visibilityPropagation:visibility"
            java.lang.Object r3 = r3.get(r4)
            java.lang.Integer r3 = (java.lang.Integer) r3
            if (r3 != 0) goto L1c
            goto L20
        L1c:
            int r2 = r3.intValue()
        L20:
            if (r2 != 0) goto L23
            goto L26
        L23:
            r11 = r12
            r12 = r8
            goto L27
        L26:
            r12 = -1
        L27:
            r2 = 0
            int r3 = androidx.transition.m0.b(r11, r2)
            int r11 = androidx.transition.m0.b(r11, r8)
            android.graphics.Rect r4 = r10.getEpicenter()
            if (r4 == 0) goto L3f
            int r8 = r4.centerX()
            int r2 = r4.centerY()
            goto L6c
        L3f:
            r4 = 2
            int[] r5 = new int[r4]
            r9.getLocationOnScreen(r5)
            r2 = r5[r2]
            int r6 = r9.getWidth()
            int r6 = r6 / r4
            int r6 = r6 + r2
            float r2 = (float) r6
            float r6 = r9.getTranslationX()
            float r6 = r6 + r2
            int r2 = java.lang.Math.round(r6)
            r8 = r5[r8]
            int r5 = r9.getHeight()
            int r5 = r5 / r4
            int r5 = r5 + r8
            float r8 = (float) r5
            float r4 = r9.getTranslationY()
            float r4 = r4 + r8
            int r8 = java.lang.Math.round(r4)
            r7 = r2
            r2 = r8
            r8 = r7
        L6c:
            float r3 = (float) r3
            float r11 = (float) r11
            float r8 = (float) r8
            float r2 = (float) r2
            float r8 = r8 - r3
            float r2 = r2 - r11
            float r8 = r8 * r8
            float r2 = r2 * r2
            float r2 = r2 + r8
            double r2 = (double) r2
            double r2 = java.lang.Math.sqrt(r2)
            float r8 = (float) r2
            int r11 = r9.getWidth()
            float r11 = (float) r11
            int r9 = r9.getHeight()
            float r9 = (float) r9
            r2 = 0
            float r11 = r11 - r2
            float r9 = r9 - r2
            float r11 = r11 * r11
            float r9 = r9 * r9
            float r9 = r9 + r11
            double r2 = (double) r9
            double r2 = java.lang.Math.sqrt(r2)
            float r9 = (float) r2
            float r8 = r8 / r9
            long r9 = r10.getDuration()
            int r11 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r11 >= 0) goto L9c
            r9 = 300(0x12c, double:1.48E-321)
        L9c:
            long r11 = (long) r12
            long r9 = r9 * r11
            float r9 = (float) r9
            r10 = 1077936128(0x40400000, float:3.0)
            float r9 = r9 / r10
            float r9 = r9 * r8
            int r8 = java.lang.Math.round(r9)
            long r8 = (long) r8
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.C0390s.a(android.view.ViewGroup, androidx.transition.U, androidx.transition.c0, androidx.transition.c0):long");
    }
}
