package androidx.transition;

/* JADX INFO: loaded from: classes.dex */
public abstract class m0 extends Z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String[] f9468a = {"android:visibilityPropagation:visibility", "android:visibilityPropagation:center"};

    public static int b(c0 c0Var, int i5) {
        int[] iArr;
        if (c0Var == null || (iArr = (int[]) c0Var.f9407a.get("android:visibilityPropagation:center")) == null) {
            return -1;
        }
        return iArr[i5];
    }
}
