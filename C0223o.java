package androidx.core.view;

import android.util.Log;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: renamed from: androidx.core.view.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0223o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ViewParent f7244a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ViewParent f7245b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final View f7246c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f7247d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int[] f7248e;

    public C0223o(View view) {
        this.f7246c = view;
    }

    public final boolean a(float f2, float f7, boolean z9) {
        ViewParent viewParentF;
        if (!this.f7247d || (viewParentF = f(0)) == null) {
            return false;
        }
        try {
            return d0.a(viewParentF, this.f7246c, f2, f7, z9);
        } catch (AbstractMethodError e3) {
            Log.e("ViewParentCompat", "ViewParent " + viewParentF + " does not implement interface method onNestedFling", e3);
            return false;
        }
    }

    public final boolean b(float f2, float f7) {
        ViewParent viewParentF;
        if (!this.f7247d || (viewParentF = f(0)) == null) {
            return false;
        }
        try {
            return d0.b(viewParentF, this.f7246c, f2, f7);
        } catch (AbstractMethodError e3) {
            Log.e("ViewParentCompat", "ViewParent " + viewParentF + " does not implement interface method onNestedPreFling", e3);
            return false;
        }
    }

    public final boolean c(int i5, int i7, int[] iArr, int[] iArr2, int i9) {
        ViewParent viewParentF;
        int i10;
        int i11;
        int[] iArr3;
        if (!this.f7247d || (viewParentF = f(i9)) == null) {
            return false;
        }
        if (i5 == 0 && i7 == 0) {
            if (iArr2 == null) {
                return false;
            }
            iArr2[0] = 0;
            iArr2[1] = 0;
            return false;
        }
        View view = this.f7246c;
        if (iArr2 != null) {
            view.getLocationInWindow(iArr2);
            int i12 = iArr2[0];
            i11 = iArr2[1];
            i10 = i12;
        } else {
            i10 = 0;
            i11 = 0;
        }
        if (iArr == null) {
            if (this.f7248e == null) {
                this.f7248e = new int[2];
            }
            iArr3 = this.f7248e;
        } else {
            iArr3 = iArr;
        }
        iArr3[0] = 0;
        iArr3[1] = 0;
        boolean z9 = viewParentF instanceof InterfaceC0224p;
        View view2 = this.f7246c;
        if (z9) {
            ((InterfaceC0224p) viewParentF).onNestedPreScroll(view2, i5, i7, iArr3, i9);
        } else if (i9 == 0) {
            try {
                d0.c(viewParentF, view2, i5, i7, iArr3);
            } catch (AbstractMethodError e3) {
                Log.e("ViewParentCompat", "ViewParent " + viewParentF + " does not implement interface method onNestedPreScroll", e3);
            }
        }
        if (iArr2 != null) {
            view.getLocationInWindow(iArr2);
            iArr2[0] = iArr2[0] - i10;
            iArr2[1] = iArr2[1] - i11;
        }
        return (iArr3[0] == 0 && iArr3[1] == 0) ? false : true;
    }

    public final void d(int i5, int i7, int i9, int[] iArr) {
        e(0, i5, 0, i7, null, i9, iArr);
    }

    public final boolean e(int i5, int i7, int i9, int i10, int[] iArr, int i11, int[] iArr2) {
        ViewParent viewParentF;
        int i12;
        int i13;
        int[] iArr3;
        if (!this.f7247d || (viewParentF = f(i11)) == null) {
            return false;
        }
        if (i5 == 0 && i7 == 0 && i9 == 0 && i10 == 0) {
            if (iArr != null) {
                iArr[0] = 0;
                iArr[1] = 0;
            }
            return false;
        }
        View view = this.f7246c;
        if (iArr != null) {
            view.getLocationInWindow(iArr);
            i12 = iArr[0];
            i13 = iArr[1];
        } else {
            i12 = 0;
            i13 = 0;
        }
        if (iArr2 == null) {
            if (this.f7248e == null) {
                this.f7248e = new int[2];
            }
            int[] iArr4 = this.f7248e;
            iArr4[0] = 0;
            iArr4[1] = 0;
            iArr3 = iArr4;
        } else {
            iArr3 = iArr2;
        }
        boolean z9 = viewParentF instanceof InterfaceC0225q;
        View view2 = this.f7246c;
        if (z9) {
            ((InterfaceC0225q) viewParentF).onNestedScroll(view2, i5, i7, i9, i10, i11, iArr3);
        } else {
            iArr3[0] = iArr3[0] + i9;
            iArr3[1] = iArr3[1] + i10;
            if (viewParentF instanceof InterfaceC0224p) {
                ((InterfaceC0224p) viewParentF).onNestedScroll(view2, i5, i7, i9, i10, i11);
            } else if (i11 == 0) {
                try {
                    d0.d(viewParentF, view2, i5, i7, i9, i10);
                } catch (AbstractMethodError e3) {
                    Log.e("ViewParentCompat", "ViewParent " + viewParentF + " does not implement interface method onNestedScroll", e3);
                }
            }
        }
        if (iArr != null) {
            view.getLocationInWindow(iArr);
            iArr[0] = iArr[0] - i12;
            iArr[1] = iArr[1] - i13;
        }
        return true;
    }

    public final ViewParent f(int i5) {
        if (i5 == 0) {
            return this.f7244a;
        }
        if (i5 != 1) {
            return null;
        }
        return this.f7245b;
    }

    public final boolean g(int i5) {
        return f(i5) != null;
    }

    public final boolean h(int i5, int i7) {
        boolean zF;
        if (g(i7)) {
            return true;
        }
        if (this.f7247d) {
            View view = this.f7246c;
            View view2 = view;
            for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
                boolean z9 = parent instanceof InterfaceC0224p;
                if (z9) {
                    zF = ((InterfaceC0224p) parent).onStartNestedScroll(view2, view, i5, i7);
                } else if (i7 == 0) {
                    try {
                        zF = d0.f(parent, view2, view, i5);
                    } catch (AbstractMethodError e3) {
                        Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onStartNestedScroll", e3);
                        zF = false;
                    }
                } else {
                    zF = false;
                }
                if (zF) {
                    if (i7 == 0) {
                        this.f7244a = parent;
                    } else if (i7 == 1) {
                        this.f7245b = parent;
                    }
                    if (z9) {
                        ((InterfaceC0224p) parent).onNestedScrollAccepted(view2, view, i5, i7);
                    } else if (i7 == 0) {
                        try {
                            d0.e(parent, view2, view, i5);
                        } catch (AbstractMethodError e10) {
                            Log.e("ViewParentCompat", "ViewParent " + parent + " does not implement interface method onNestedScrollAccepted", e10);
                        }
                    }
                    return true;
                }
                if (parent instanceof View) {
                    view2 = parent;
                }
            }
        }
        return false;
    }

    public final void i(int i5) {
        ViewParent viewParentF = f(i5);
        if (viewParentF != null) {
            boolean z9 = viewParentF instanceof InterfaceC0224p;
            View view = this.f7246c;
            if (z9) {
                ((InterfaceC0224p) viewParentF).onStopNestedScroll(view, i5);
            } else if (i5 == 0) {
                try {
                    d0.g(viewParentF, view);
                } catch (AbstractMethodError e3) {
                    Log.e("ViewParentCompat", "ViewParent " + viewParentF + " does not implement interface method onStopNestedScroll", e3);
                }
            }
            if (i5 == 0) {
                this.f7244a = null;
            } else {
                if (i5 != 1) {
                    return;
                }
                this.f7245b = null;
            }
        }
    }
}
