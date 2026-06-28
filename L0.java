package androidx.transition;

import android.animation.Animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class l0 extends U {
    public static final int MODE_IN = 1;
    public static final int MODE_OUT = 2;
    private static final String PROPNAME_SCREEN_LOCATION = "android:visibility:screenLocation";
    private int mMode;
    static final String PROPNAME_VISIBILITY = "android:visibility:visibility";
    private static final String PROPNAME_PARENT = "android:visibility:parent";
    private static final String[] sTransitionProperties = {PROPNAME_VISIBILITY, PROPNAME_PARENT};

    public l0() {
        this.mMode = 3;
    }

    public static void e(c0 c0Var) {
        int visibility = c0Var.f9408b.getVisibility();
        HashMap map = c0Var.f9407a;
        map.put(PROPNAME_VISIBILITY, Integer.valueOf(visibility));
        map.put(PROPNAME_PARENT, c0Var.f9408b.getParent());
        int[] iArr = new int[2];
        c0Var.f9408b.getLocationOnScreen(iArr);
        map.put(PROPNAME_SCREEN_LOCATION, iArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static androidx.transition.k0 f(androidx.transition.c0 r8, androidx.transition.c0 r9) {
        /*
            androidx.transition.k0 r0 = new androidx.transition.k0
            r0.<init>()
            r1 = 0
            r0.f9458a = r1
            r0.f9459b = r1
            r2 = 0
            r3 = -1
            java.lang.String r4 = "android:visibility:parent"
            java.lang.String r5 = "android:visibility:visibility"
            if (r8 == 0) goto L2f
            java.util.HashMap r6 = r8.f9407a
            boolean r7 = r6.containsKey(r5)
            if (r7 == 0) goto L2f
            java.lang.Object r7 = r6.get(r5)
            java.lang.Integer r7 = (java.lang.Integer) r7
            int r7 = r7.intValue()
            r0.f9460c = r7
            java.lang.Object r6 = r6.get(r4)
            android.view.ViewGroup r6 = (android.view.ViewGroup) r6
            r0.f9462e = r6
            goto L33
        L2f:
            r0.f9460c = r3
            r0.f9462e = r2
        L33:
            if (r9 == 0) goto L52
            java.util.HashMap r6 = r9.f9407a
            boolean r7 = r6.containsKey(r5)
            if (r7 == 0) goto L52
            java.lang.Object r2 = r6.get(r5)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            r0.f9461d = r2
            java.lang.Object r2 = r6.get(r4)
            android.view.ViewGroup r2 = (android.view.ViewGroup) r2
            r0.f9463f = r2
            goto L56
        L52:
            r0.f9461d = r3
            r0.f9463f = r2
        L56:
            r2 = 1
            if (r8 == 0) goto L8a
            if (r9 == 0) goto L8a
            int r8 = r0.f9460c
            int r9 = r0.f9461d
            if (r8 != r9) goto L68
            android.view.ViewGroup r3 = r0.f9462e
            android.view.ViewGroup r4 = r0.f9463f
            if (r3 != r4) goto L68
            return r0
        L68:
            if (r8 == r9) goto L78
            if (r8 != 0) goto L71
            r0.f9459b = r1
            r0.f9458a = r2
            goto L9f
        L71:
            if (r9 != 0) goto L9f
            r0.f9459b = r2
            r0.f9458a = r2
            goto L9f
        L78:
            android.view.ViewGroup r8 = r0.f9463f
            if (r8 != 0) goto L81
            r0.f9459b = r1
            r0.f9458a = r2
            goto L9f
        L81:
            android.view.ViewGroup r8 = r0.f9462e
            if (r8 != 0) goto L9f
            r0.f9459b = r2
            r0.f9458a = r2
            goto L9f
        L8a:
            if (r8 != 0) goto L95
            int r8 = r0.f9461d
            if (r8 != 0) goto L95
            r0.f9459b = r2
            r0.f9458a = r2
            goto L9f
        L95:
            if (r9 != 0) goto L9f
            int r8 = r0.f9460c
            if (r8 != 0) goto L9f
            r0.f9459b = r1
            r0.f9458a = r2
        L9f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.l0.f(androidx.transition.c0, androidx.transition.c0):androidx.transition.k0");
    }

    @Override // androidx.transition.U
    public void captureEndValues(c0 c0Var) {
        e(c0Var);
    }

    @Override // androidx.transition.U
    public void captureStartValues(c0 c0Var) {
        e(c0Var);
    }

    @Override // androidx.transition.U
    public Animator createAnimator(ViewGroup viewGroup, c0 c0Var, c0 c0Var2) {
        k0 k0VarF = f(c0Var, c0Var2);
        if (!k0VarF.f9458a) {
            return null;
        }
        if (k0VarF.f9462e == null && k0VarF.f9463f == null) {
            return null;
        }
        return k0VarF.f9459b ? onAppear(viewGroup, c0Var, k0VarF.f9460c, c0Var2, k0VarF.f9461d) : onDisappear(viewGroup, c0Var, k0VarF.f9460c, c0Var2, k0VarF.f9461d);
    }

    public int getMode() {
        return this.mMode;
    }

    @Override // androidx.transition.U
    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    @Override // androidx.transition.U
    public boolean isTransitionRequired(c0 c0Var, c0 c0Var2) {
        if (c0Var == null && c0Var2 == null) {
            return false;
        }
        if (c0Var != null && c0Var2 != null && c0Var2.f9407a.containsKey(PROPNAME_VISIBILITY) != c0Var.f9407a.containsKey(PROPNAME_VISIBILITY)) {
            return false;
        }
        k0 k0VarF = f(c0Var, c0Var2);
        if (k0VarF.f9458a) {
            return k0VarF.f9460c == 0 || k0VarF.f9461d == 0;
        }
        return false;
    }

    public boolean isVisible(c0 c0Var) {
        if (c0Var == null) {
            return false;
        }
        HashMap map = c0Var.f9407a;
        return ((Integer) map.get(PROPNAME_VISIBILITY)).intValue() == 0 && ((View) map.get(PROPNAME_PARENT)) != null;
    }

    public abstract Animator onAppear(ViewGroup viewGroup, View view, c0 c0Var, c0 c0Var2);

    public Animator onAppear(ViewGroup viewGroup, c0 c0Var, int i5, c0 c0Var2, int i7) {
        if ((this.mMode & 1) != 1 || c0Var2 == null) {
            return null;
        }
        if (c0Var == null) {
            View view = (View) c0Var2.f9408b.getParent();
            if (f(getMatchedTransitionValues(view, false), getTransitionValues(view, false)).f9458a) {
                return null;
            }
        }
        return onAppear(viewGroup, c0Var2.f9408b, c0Var, c0Var2);
    }

    public abstract Animator onDisappear(ViewGroup viewGroup, View view, c0 c0Var, c0 c0Var2);

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003e  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0190  */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.transition.U, androidx.transition.l0] */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r0v29 */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.view.ViewGroupOverlay] */
    /* JADX WARN: Type inference failed for: r10v10, types: [android.view.ViewGroupOverlay] */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v17 */
    /* JADX WARN: Type inference failed for: r10v18 */
    /* JADX WARN: Type inference failed for: r10v2, types: [android.view.View, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r12v4, types: [android.view.ViewGroup] */
    /* JADX WARN: Type inference failed for: r12v6, types: [android.view.ViewGroup] */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r2v8, types: [android.view.ViewGroupOverlay] */
    /* JADX WARN: Type inference failed for: r3v12, types: [android.view.ViewGroupOverlay] */
    /* JADX WARN: Type inference failed for: r5v2, types: [android.view.View] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.animation.Animator onDisappear(android.view.ViewGroup r19, androidx.transition.c0 r20, int r21, androidx.transition.c0 r22, int r23) {
        /*
            Method dump skipped, instruction units count: 539
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.transition.l0.onDisappear(android.view.ViewGroup, androidx.transition.c0, int, androidx.transition.c0, int):android.animation.Animator");
    }

    public void setMode(int i5) {
        if ((i5 & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.mMode = i5;
    }

    public l0(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMode = 3;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, N.f9374d);
        int iC = C.b.c(typedArrayObtainStyledAttributes, (XmlResourceParser) attributeSet, "transitionVisibilityMode", 0, 0);
        typedArrayObtainStyledAttributes.recycle();
        if (iC != 0) {
            setMode(iC);
        }
    }
}
