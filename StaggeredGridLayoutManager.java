package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class StaggeredGridLayoutManager extends AbstractC0370y0 implements P0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f9002e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final j1[] f9003f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Z f9004g;
    public final Z h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f9005i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f9006j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final O f9007k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f9008l;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final BitSet f9010n;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C0330e f9012q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final int f9013r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f9014s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f9015t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public i1 f9016u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final Rect f9017v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final f1 f9018w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final boolean f9019x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int[] f9020y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final RunnableC0371z f9021z;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f9009m = false;
    public int o = -1;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f9011p = Integer.MIN_VALUE;

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i5, int i7) {
        this.f9002e = -1;
        this.f9008l = false;
        C0330e c0330e = new C0330e();
        this.f9012q = c0330e;
        this.f9013r = 2;
        this.f9017v = new Rect();
        this.f9018w = new f1(this);
        this.f9019x = true;
        this.f9021z = new RunnableC0371z(2, this);
        C0368x0 properties = AbstractC0370y0.getProperties(context, attributeSet, i5, i7);
        int i9 = properties.f9290a;
        if (i9 != 0 && i9 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        assertNotInLayoutOrScroll(null);
        if (i9 != this.f9005i) {
            this.f9005i = i9;
            Z z9 = this.f9004g;
            this.f9004g = this.h;
            this.h = z9;
            requestLayout();
        }
        int i10 = properties.f9291b;
        assertNotInLayoutOrScroll(null);
        if (i10 != this.f9002e) {
            c0330e.a();
            requestLayout();
            this.f9002e = i10;
            this.f9010n = new BitSet(this.f9002e);
            this.f9003f = new j1[this.f9002e];
            for (int i11 = 0; i11 < this.f9002e; i11++) {
                this.f9003f[i11] = new j1(this, i11);
            }
            requestLayout();
        }
        boolean z10 = properties.f9292c;
        assertNotInLayoutOrScroll(null);
        i1 i1Var = this.f9016u;
        if (i1Var != null && i1Var.f9165l != z10) {
            i1Var.f9165l = z10;
        }
        this.f9008l = z10;
        requestLayout();
        O o = new O();
        o.f8954a = true;
        o.f8959f = 0;
        o.f8960g = 0;
        this.f9007k = o;
        this.f9004g = Z.a(this, this.f9005i);
        this.h = Z.a(this, 1 - this.f9005i);
    }

    public static int F(int i5, int i7, int i9) {
        if (i7 == 0 && i9 == 0) {
            return i5;
        }
        int mode = View.MeasureSpec.getMode(i5);
        return (mode == Integer.MIN_VALUE || mode == 1073741824) ? View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i5) - i7) - i9), mode) : i5;
    }

    public final void A() {
        if (this.f9005i == 1 || !isLayoutRTL()) {
            this.f9009m = this.f9008l;
        } else {
            this.f9009m = !this.f9008l;
        }
    }

    public final void B(int i5, boolean z9) {
        i1 i1Var = this.f9016u;
        if (i1Var != null) {
            i1Var.h = null;
            i1Var.f9161g = 0;
            i1Var.f9159e = -1;
            i1Var.f9160f = -1;
        }
        this.o = i5;
        this.f9011p = 0;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.showGoToTop();
        }
        if (z9) {
            this.f9012q.a();
        }
        requestLayout();
    }

    public final void C(int i5) {
        O o = this.f9007k;
        o.f8958e = i5;
        o.f8957d = this.f9009m != (i5 == -1) ? -1 : 1;
    }

    public final void D(int i5, R0 r02) {
        int iL;
        int iL2;
        int i7;
        O o = this.f9007k;
        boolean z9 = false;
        o.f8955b = 0;
        o.f8956c = i5;
        if (!isSmoothScrolling() || (i7 = r02.f8978a) == -1) {
            iL = 0;
            iL2 = 0;
        } else {
            if (this.f9009m == (i7 < i5)) {
                iL = this.f9004g.l();
                iL2 = 0;
            } else {
                iL2 = this.f9004g.l();
                iL = 0;
            }
        }
        if (getClipToPadding()) {
            o.f8959f = this.f9004g.k() - iL2;
            o.f8960g = this.f9004g.g() + iL;
        } else {
            o.f8960g = this.f9004g.f() + iL;
            o.f8959f = -iL2;
        }
        o.h = false;
        o.f8954a = true;
        if (this.f9004g.i() == 0 && this.f9004g.f() == 0) {
            z9 = true;
        }
        o.f8961i = z9;
    }

    public final void E(j1 j1Var, int i5, int i7) {
        int i9 = j1Var.f9175d;
        int i10 = j1Var.f9176e;
        if (i5 != -1) {
            int i11 = j1Var.f9174c;
            if (i11 == Integer.MIN_VALUE) {
                j1Var.a();
                i11 = j1Var.f9174c;
            }
            if (i11 - i9 >= i7) {
                this.f9010n.set(i10, false);
                return;
            }
            return;
        }
        int i12 = j1Var.f9173b;
        if (i12 == Integer.MIN_VALUE) {
            View view = (View) j1Var.f9172a.get(0);
            g1 g1Var = (g1) view.getLayoutParams();
            j1Var.f9173b = j1Var.f9177f.f9004g.e(view);
            g1Var.getClass();
            i12 = j1Var.f9173b;
        }
        if (i12 + i9 <= i7) {
            this.f9010n.set(i10, false);
        }
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void assertNotInLayoutOrScroll(String str) {
        if (this.f9016u == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    public final int c(int i5) {
        if (getChildCount() == 0) {
            return this.f9009m ? 1 : -1;
        }
        return (i5 < n()) != this.f9009m ? -1 : 1;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final boolean canScrollHorizontally() {
        return this.f9005i == 0;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final boolean canScrollVertically() {
        return this.f9005i == 1;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final boolean checkLayoutParams(C0372z0 c0372z0) {
        return c0372z0 instanceof g1;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void collectAdjacentPrefetchPositions(int i5, int i7, R0 r02, InterfaceC0366w0 interfaceC0366w0) {
        O o;
        int iF;
        int iH;
        if (this.f9005i != 0) {
            i5 = i7;
        }
        if (getChildCount() == 0 || i5 == 0) {
            return;
        }
        w(i5, r02);
        int[] iArr = this.f9020y;
        if (iArr == null || iArr.length < this.f9002e) {
            this.f9020y = new int[this.f9002e];
        }
        int i9 = 0;
        int i10 = 0;
        while (true) {
            int i11 = this.f9002e;
            o = this.f9007k;
            if (i9 >= i11) {
                break;
            }
            if (o.f8957d == -1) {
                iF = o.f8959f;
                iH = this.f9003f[i9].h(iF);
            } else {
                iF = this.f9003f[i9].f(o.f8960g);
                iH = o.f8960g;
            }
            int i12 = iF - iH;
            if (i12 >= 0) {
                this.f9020y[i10] = i12;
                i10++;
            }
            i9++;
        }
        Arrays.sort(this.f9020y, 0, i10);
        for (int i13 = 0; i13 < i10; i13++) {
            int i14 = o.f8956c;
            if (i14 < 0 || i14 >= r02.b()) {
                return;
            }
            ((D) interfaceC0366w0).a(o.f8956c, this.f9020y[i13]);
            o.f8956c += o.f8957d;
        }
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final int computeHorizontalScrollExtent(R0 r02) {
        return e(r02);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final int computeHorizontalScrollOffset(R0 r02) {
        return f(r02);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final int computeHorizontalScrollRange(R0 r02) {
        return g(r02);
    }

    @Override // androidx.recyclerview.widget.P0
    public final PointF computeScrollVectorForPosition(int i5) {
        int iC = c(i5);
        PointF pointF = new PointF();
        if (iC == 0) {
            return null;
        }
        if (this.f9005i == 0) {
            pointF.x = iC;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = iC;
        }
        return pointF;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final int computeVerticalScrollExtent(R0 r02) {
        return e(r02);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final int computeVerticalScrollOffset(R0 r02) {
        return f(r02);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final int computeVerticalScrollRange(R0 r02) {
        return g(r02);
    }

    public final boolean d() {
        int iN;
        if (getChildCount() != 0 && this.f9013r != 0 && isAttachedToWindow()) {
            if (this.f9009m) {
                iN = o();
                n();
            } else {
                iN = n();
                o();
            }
            C0330e c0330e = this.f9012q;
            if (iN == 0 && s() != null) {
                c0330e.a();
                requestSimpleAnimationsInNextLayout();
                requestLayout();
                return true;
            }
        }
        return false;
    }

    public final int e(R0 r02) {
        if (getChildCount() == 0) {
            return 0;
        }
        Z z9 = this.f9004g;
        boolean z10 = this.f9019x;
        return AbstractC0328d.d(r02, z9, j(!z10), i(!z10), this, this.f9019x);
    }

    public final int f(R0 r02) {
        if (getChildCount() == 0) {
            return 0;
        }
        Z z9 = this.f9004g;
        boolean z10 = this.f9019x;
        return AbstractC0328d.e(r02, z9, j(!z10), i(!z10), this, this.f9019x, this.f9009m);
    }

    public final int g(R0 r02) {
        if (getChildCount() == 0) {
            return 0;
        }
        Z z9 = this.f9004g;
        boolean z10 = this.f9019x;
        return AbstractC0328d.f(r02, z9, j(!z10), i(!z10), this, this.f9019x);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final C0372z0 generateDefaultLayoutParams() {
        return this.f9005i == 0 ? new g1(-2, -1) : new g1(-1, -2);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final C0372z0 generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new g1(context, attributeSet);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final int getColumnCountForAccessibility(G0 g02, R0 r02) {
        return this.f9005i == 1 ? this.f9002e : super.getColumnCountForAccessibility(g02, r02);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final int getRowCountForAccessibility(G0 g02, R0 r02) {
        return this.f9005i == 0 ? this.f9002e : super.getRowCountForAccessibility(g02, r02);
    }

    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r1v53 */
    public final int h(G0 g02, O o, R0 r02) {
        j1 j1Var;
        ?? r12;
        int iC;
        int iC2;
        int iK;
        int iC3;
        View view;
        int i5;
        int i7;
        int i9;
        G0 g03 = g02;
        int i10 = 0;
        int i11 = 1;
        this.f9010n.set(0, this.f9002e, true);
        O o9 = this.f9007k;
        int i12 = o9.f8961i ? o.f8958e == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE : o.f8958e == 1 ? o.f8960g + o.f8955b : o.f8959f - o.f8955b;
        int i13 = o.f8958e;
        for (int i14 = 0; i14 < this.f9002e; i14++) {
            if (!this.f9003f[i14].f9172a.isEmpty()) {
                E(this.f9003f[i14], i13, i12);
            }
        }
        int iG = this.f9009m ? this.f9004g.g() : this.f9004g.k();
        boolean z9 = false;
        while (true) {
            int i15 = o.f8956c;
            int i16 = -1;
            if (((i15 < 0 || i15 >= r02.b()) ? i10 : i11) == 0 || (!o9.f8961i && this.f9010n.isEmpty())) {
                break;
            }
            View view2 = g03.m(Long.MAX_VALUE, o.f8956c).itemView;
            o.f8956c += o.f8957d;
            g1 g1Var = (g1) view2.getLayoutParams();
            int viewLayoutPosition = g1Var.getViewLayoutPosition();
            C0330e c0330e = this.f9012q;
            int[] iArr = (int[]) c0330e.f9128a;
            int i17 = (iArr == null || viewLayoutPosition >= iArr.length) ? -1 : iArr[viewLayoutPosition];
            if (i17 == -1) {
                if (v(o.f8958e)) {
                    i7 = this.f9002e - i11;
                    i9 = -1;
                } else {
                    i16 = this.f9002e;
                    i7 = i10;
                    i9 = i11;
                }
                j1 j1Var2 = null;
                if (o.f8958e == i11) {
                    int iK2 = this.f9004g.k();
                    int i18 = Integer.MAX_VALUE;
                    while (i7 != i16) {
                        j1 j1Var3 = this.f9003f[i7];
                        int iF = j1Var3.f(iK2);
                        if (iF < i18) {
                            i18 = iF;
                            j1Var2 = j1Var3;
                        }
                        i7 += i9;
                    }
                } else {
                    int iG2 = this.f9004g.g();
                    int i19 = Integer.MIN_VALUE;
                    while (i7 != i16) {
                        j1 j1Var4 = this.f9003f[i7];
                        int iH = j1Var4.h(iG2);
                        if (iH > i19) {
                            j1Var2 = j1Var4;
                            i19 = iH;
                        }
                        i7 += i9;
                    }
                }
                j1Var = j1Var2;
                c0330e.b(viewLayoutPosition);
                ((int[]) c0330e.f9128a)[viewLayoutPosition] = j1Var.f9176e;
            } else {
                j1Var = this.f9003f[i17];
            }
            j1 j1Var5 = j1Var;
            g1Var.f9144e = j1Var5;
            if (o.f8958e == 1) {
                addView(view2);
                r12 = 0;
            } else {
                r12 = 0;
                addView(view2, 0);
            }
            if (this.f9005i == 1) {
                t(view2, AbstractC0370y0.getChildMeasureSpec(this.f9006j, getWidthMode(), r12, ((ViewGroup.MarginLayoutParams) g1Var).width, r12), AbstractC0370y0.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingBottom() + getPaddingTop(), ((ViewGroup.MarginLayoutParams) g1Var).height, true));
            } else {
                t(view2, AbstractC0370y0.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingRight() + getPaddingLeft(), ((ViewGroup.MarginLayoutParams) g1Var).width, true), AbstractC0370y0.getChildMeasureSpec(this.f9006j, getHeightMode(), 0, ((ViewGroup.MarginLayoutParams) g1Var).height, false));
            }
            if (o.f8958e == 1) {
                int iF2 = j1Var5.f(iG);
                iC2 = iF2;
                iC = this.f9004g.c(view2) + iF2;
            } else {
                int iH2 = j1Var5.h(iG);
                iC = iH2;
                iC2 = iH2 - this.f9004g.c(view2);
            }
            if (o.f8958e == 1) {
                j1 j1Var6 = g1Var.f9144e;
                j1Var6.getClass();
                g1 g1Var2 = (g1) view2.getLayoutParams();
                g1Var2.f9144e = j1Var6;
                ArrayList arrayList = j1Var6.f9172a;
                arrayList.add(view2);
                j1Var6.f9174c = Integer.MIN_VALUE;
                if (arrayList.size() == 1) {
                    j1Var6.f9173b = Integer.MIN_VALUE;
                }
                if (g1Var2.isItemRemoved() || g1Var2.isItemChanged()) {
                    j1Var6.f9175d = j1Var6.f9177f.f9004g.c(view2) + j1Var6.f9175d;
                }
            } else {
                j1 j1Var7 = g1Var.f9144e;
                j1Var7.getClass();
                g1 g1Var3 = (g1) view2.getLayoutParams();
                g1Var3.f9144e = j1Var7;
                ArrayList arrayList2 = j1Var7.f9172a;
                arrayList2.add(0, view2);
                j1Var7.f9173b = Integer.MIN_VALUE;
                if (arrayList2.size() == 1) {
                    j1Var7.f9174c = Integer.MIN_VALUE;
                }
                if (g1Var3.isItemRemoved() || g1Var3.isItemChanged()) {
                    j1Var7.f9175d = j1Var7.f9177f.f9004g.c(view2) + j1Var7.f9175d;
                }
            }
            if (isLayoutRTL() && this.f9005i == 1) {
                iC3 = this.h.g() - (((this.f9002e - 1) - j1Var5.f9176e) * this.f9006j);
                iK = iC3 - this.h.c(view2);
            } else {
                iK = this.h.k() + (j1Var5.f9176e * this.f9006j);
                iC3 = this.h.c(view2) + iK;
            }
            int i20 = iC3;
            int i21 = iK;
            if (this.f9005i == 1) {
                view = view2;
                layoutDecoratedWithMargins(view2, i21, iC2, i20, iC);
            } else {
                view = view2;
                layoutDecoratedWithMargins(view, iC2, i21, iC, i20);
            }
            E(j1Var5, o9.f8958e, i12);
            x(g02, o9);
            if (o9.h && view.hasFocusable()) {
                i5 = 0;
                this.f9010n.set(j1Var5.f9176e, false);
            } else {
                i5 = 0;
            }
            g03 = g02;
            i10 = i5;
            z9 = true;
            i11 = 1;
        }
        G0 g04 = g03;
        int i22 = i10;
        if (!z9) {
            x(g04, o9);
        }
        int iK3 = o9.f8958e == -1 ? this.f9004g.k() - q(this.f9004g.k()) : p(this.f9004g.g()) - this.f9004g.g();
        return iK3 > 0 ? Math.min(o.f8955b, iK3) : i22;
    }

    public final View i(boolean z9) {
        int iK = this.f9004g.k();
        int iG = this.f9004g.g();
        View view = null;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            int iE = this.f9004g.e(childAt);
            int iB = this.f9004g.b(childAt);
            if (iB > iK && iE < iG) {
                if (iB <= iG || !z9) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final boolean isAutoMeasureEnabled() {
        return this.f9013r != 0;
    }

    public final boolean isLayoutRTL() {
        return getLayoutDirection() == 1;
    }

    public final View j(boolean z9) {
        int iK = this.f9004g.k();
        int iG = this.f9004g.g();
        int childCount = getChildCount();
        View view = null;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            int iE = this.f9004g.e(childAt);
            if (this.f9004g.b(childAt) > iK && iE < iG) {
                if (iE >= iK || !z9) {
                    return childAt;
                }
                if (view == null) {
                    view = childAt;
                }
            }
        }
        return view;
    }

    public final int[] k() {
        int[] iArr = new int[this.f9002e];
        for (int i5 = 0; i5 < this.f9002e; i5++) {
            j1 j1Var = this.f9003f[i5];
            boolean z9 = j1Var.f9177f.f9008l;
            ArrayList arrayList = j1Var.f9172a;
            iArr[i5] = z9 ? j1Var.e(arrayList.size() - 1, -1, true, false) : j1Var.e(0, arrayList.size(), true, false);
        }
        return iArr;
    }

    public final void l(G0 g02, R0 r02, boolean z9) {
        int iG;
        int iP = p(Integer.MIN_VALUE);
        if (iP != Integer.MIN_VALUE && (iG = this.f9004g.g() - iP) > 0) {
            int i5 = iG - (-scrollBy(-iG, g02, r02));
            if (!z9 || i5 <= 0) {
                return;
            }
            this.f9004g.p(i5);
        }
    }

    public final void m(G0 g02, R0 r02, boolean z9) {
        int iK;
        int iQ = q(Integer.MAX_VALUE);
        if (iQ != Integer.MAX_VALUE && (iK = iQ - this.f9004g.k()) > 0) {
            int iScrollBy = iK - scrollBy(iK, g02, r02);
            if (!z9 || iScrollBy <= 0) {
                return;
            }
            this.f9004g.p(-iScrollBy);
        }
    }

    public final int n() {
        if (getChildCount() == 0) {
            return 0;
        }
        return getPosition(getChildAt(0));
    }

    public final int o() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return 0;
        }
        return getPosition(getChildAt(childCount - 1));
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void offsetChildrenHorizontal(int i5) {
        super.offsetChildrenHorizontal(i5);
        for (int i7 = 0; i7 < this.f9002e; i7++) {
            j1 j1Var = this.f9003f[i7];
            int i9 = j1Var.f9173b;
            if (i9 != Integer.MIN_VALUE) {
                j1Var.f9173b = i9 + i5;
            }
            int i10 = j1Var.f9174c;
            if (i10 != Integer.MIN_VALUE) {
                j1Var.f9174c = i10 + i5;
            }
        }
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void offsetChildrenVertical(int i5) {
        super.offsetChildrenVertical(i5);
        for (int i7 = 0; i7 < this.f9002e; i7++) {
            j1 j1Var = this.f9003f[i7];
            int i9 = j1Var.f9173b;
            if (i9 != Integer.MIN_VALUE) {
                j1Var.f9173b = i9 + i5;
            }
            int i10 = j1Var.f9174c;
            if (i10 != Integer.MIN_VALUE) {
                j1Var.f9174c = i10 + i5;
            }
        }
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void onAdapterChanged(AbstractC0341j0 abstractC0341j0, AbstractC0341j0 abstractC0341j02) {
        this.f9012q.a();
        for (int i5 = 0; i5 < this.f9002e; i5++) {
            this.f9003f[i5].b();
        }
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void onDetachedFromWindow(RecyclerView recyclerView, G0 g02) {
        onDetachedFromWindow(recyclerView);
        removeCallbacks(this.f9021z);
        for (int i5 = 0; i5 < this.f9002e; i5++) {
            this.f9003f[i5].b();
        }
        recyclerView.requestLayout();
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x004d  */
    @Override // androidx.recyclerview.widget.AbstractC0370y0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View onFocusSearchFailed(android.view.View r9, int r10, androidx.recyclerview.widget.G0 r11, androidx.recyclerview.widget.R0 r12) {
        /*
            Method dump skipped, instruction units count: 328
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.onFocusSearchFailed(android.view.View, int, androidx.recyclerview.widget.G0, androidx.recyclerview.widget.R0):android.view.View");
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            View viewJ = j(false);
            View viewI = i(false);
            if (viewJ == null || viewI == null) {
                return;
            }
            int position = getPosition(viewJ);
            int position2 = getPosition(viewI);
            if (position < position2) {
                accessibilityEvent.setFromIndex(position);
                accessibilityEvent.setToIndex(position2);
            } else {
                accessibilityEvent.setFromIndex(position2);
                accessibilityEvent.setToIndex(position);
            }
        }
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void onInitializeAccessibilityNodeInfoForItem(G0 g02, R0 r02, View view, L.l lVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof g1)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, lVar);
            return;
        }
        g1 g1Var = (g1) layoutParams;
        if (this.f9005i == 0) {
            j1 j1Var = g1Var.f9144e;
            lVar.l(L.k.a(j1Var != null ? j1Var.f9176e : -1, 1, -1, -1, false, false));
        } else {
            j1 j1Var2 = g1Var.f9144e;
            lVar.l(L.k.a(-1, -1, j1Var2 != null ? j1Var2.f9176e : -1, 1, false, false));
        }
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void onItemsAdded(RecyclerView recyclerView, int i5, int i7) {
        r(i5, i7, 1);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void onItemsChanged(RecyclerView recyclerView) {
        this.f9012q.a();
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void onItemsMoved(RecyclerView recyclerView, int i5, int i7, int i9) {
        r(i5, i7, 8);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void onItemsRemoved(RecyclerView recyclerView, int i5, int i7) {
        r(i5, i7, 2);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void onItemsUpdated(RecyclerView recyclerView, int i5, int i7, Object obj) {
        r(i5, i7, 4);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void onLayoutChildren(G0 g02, R0 r02) {
        u(g02, r02, true);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void onLayoutCompleted(R0 r02) {
        this.o = -1;
        this.f9011p = Integer.MIN_VALUE;
        this.f9016u = null;
        this.f9018w.a();
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof i1) {
            i1 i1Var = (i1) parcelable;
            this.f9016u = i1Var;
            if (this.o != -1) {
                i1Var.h = null;
                i1Var.f9161g = 0;
                i1Var.f9159e = -1;
                i1Var.f9160f = -1;
                i1Var.h = null;
                i1Var.f9161g = 0;
                i1Var.f9162i = 0;
                i1Var.f9163j = null;
                i1Var.f9164k = null;
            }
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final Parcelable onSaveInstanceState() {
        int iH;
        int iK;
        int[] iArr;
        i1 i1Var = this.f9016u;
        if (i1Var != null) {
            i1 i1Var2 = new i1();
            i1Var2.f9161g = i1Var.f9161g;
            i1Var2.f9159e = i1Var.f9159e;
            i1Var2.f9160f = i1Var.f9160f;
            i1Var2.h = i1Var.h;
            i1Var2.f9162i = i1Var.f9162i;
            i1Var2.f9163j = i1Var.f9163j;
            i1Var2.f9165l = i1Var.f9165l;
            i1Var2.f9166m = i1Var.f9166m;
            i1Var2.f9167n = i1Var.f9167n;
            i1Var2.f9164k = i1Var.f9164k;
            return i1Var2;
        }
        i1 i1Var3 = new i1();
        i1Var3.f9165l = this.f9008l;
        i1Var3.f9166m = this.f9014s;
        i1Var3.f9167n = this.f9015t;
        C0330e c0330e = this.f9012q;
        if (c0330e == null || (iArr = (int[]) c0330e.f9128a) == null) {
            i1Var3.f9162i = 0;
        } else {
            i1Var3.f9163j = iArr;
            i1Var3.f9162i = iArr.length;
            i1Var3.f9164k = (List) c0330e.f9129b;
        }
        if (getChildCount() > 0) {
            i1Var3.f9159e = this.f9014s ? o() : n();
            View viewI = this.f9009m ? i(true) : j(true);
            i1Var3.f9160f = viewI != null ? getPosition(viewI) : -1;
            int i5 = this.f9002e;
            i1Var3.f9161g = i5;
            i1Var3.h = new int[i5];
            for (int i7 = 0; i7 < this.f9002e; i7++) {
                if (this.f9014s) {
                    iH = this.f9003f[i7].f(Integer.MIN_VALUE);
                    if (iH != Integer.MIN_VALUE) {
                        iK = this.f9004g.g();
                        iH -= iK;
                    }
                } else {
                    iH = this.f9003f[i7].h(Integer.MIN_VALUE);
                    if (iH != Integer.MIN_VALUE) {
                        iK = this.f9004g.k();
                        iH -= iK;
                    }
                }
                i1Var3.h[i7] = iH;
            }
        } else {
            i1Var3.f9159e = -1;
            i1Var3.f9160f = -1;
            i1Var3.f9161g = 0;
        }
        return i1Var3;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void onScrollStateChanged(int i5) {
        if (i5 == 0) {
            d();
        }
    }

    public final int p(int i5) {
        int iF = this.f9003f[0].f(i5);
        for (int i7 = 1; i7 < this.f9002e; i7++) {
            int iF2 = this.f9003f[i7].f(i5);
            if (iF2 > iF) {
                iF = iF2;
            }
        }
        return iF;
    }

    public final int q(int i5) {
        int iH = this.f9003f[0].h(i5);
        for (int i7 = 1; i7 < this.f9002e; i7++) {
            int iH2 = this.f9003f[i7].h(i5);
            if (iH2 < iH) {
                iH = iH2;
            }
        }
        return iH;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x003c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x003d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void r(int r8, int r9, int r10) {
        /*
            r7 = this;
            boolean r0 = r7.f9009m
            if (r0 == 0) goto L9
            int r0 = r7.o()
            goto Ld
        L9:
            int r0 = r7.n()
        Ld:
            r1 = 8
            if (r10 != r1) goto L1b
            if (r8 >= r9) goto L17
            int r2 = r9 + 1
        L15:
            r3 = r8
            goto L1e
        L17:
            int r2 = r8 + 1
            r3 = r9
            goto L1e
        L1b:
            int r2 = r8 + r9
            goto L15
        L1e:
            androidx.recyclerview.widget.e r4 = r7.f9012q
            r4.c(r3)
            r5 = 1
            if (r10 == r5) goto L37
            r6 = 2
            if (r10 == r6) goto L33
            if (r10 == r1) goto L2c
            goto L3a
        L2c:
            r4.e(r8, r5)
            r4.d(r9, r5)
            goto L3a
        L33:
            r4.e(r8, r9)
            goto L3a
        L37:
            r4.d(r8, r9)
        L3a:
            if (r2 > r0) goto L3d
            return
        L3d:
            boolean r8 = r7.f9009m
            if (r8 == 0) goto L46
            int r8 = r7.n()
            goto L4a
        L46:
            int r8 = r7.o()
        L4a:
            if (r3 > r8) goto L4f
            r7.requestLayout()
        L4f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.r(int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00ff A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x002c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View s() {
        /*
            Method dump skipped, instruction units count: 258
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.s():android.view.View");
    }

    public final int scrollBy(int i5, G0 g02, R0 r02) {
        if (getChildCount() == 0 || i5 == 0) {
            return 0;
        }
        w(i5, r02);
        O o = this.f9007k;
        int iH = h(g02, o, r02);
        if (o.f8955b >= iH) {
            i5 = i5 < 0 ? -iH : iH;
        }
        this.f9004g.p(-i5);
        this.f9014s = this.f9009m;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.showGoToTop();
        }
        o.f8955b = 0;
        x(g02, o);
        return i5;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final int scrollHorizontallyBy(int i5, G0 g02, R0 r02) {
        return scrollBy(i5, g02, r02);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void scrollToPosition(int i5) {
        i1 i1Var = this.f9016u;
        if (i1Var != null && i1Var.f9159e != i5) {
            i1Var.h = null;
            i1Var.f9161g = 0;
            i1Var.f9159e = -1;
            i1Var.f9160f = -1;
        }
        this.o = i5;
        this.f9011p = Integer.MIN_VALUE;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.showGoToTop();
        }
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final int scrollVerticallyBy(int i5, G0 g02, R0 r02) {
        return scrollBy(i5, g02, r02);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void setMeasuredDimension(Rect rect, int i5, int i7) {
        int iChooseSize;
        int iChooseSize2;
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        if (this.f9005i == 1) {
            iChooseSize2 = AbstractC0370y0.chooseSize(i7, rect.height() + paddingBottom, getMinimumHeight());
            iChooseSize = AbstractC0370y0.chooseSize(i5, (this.f9006j * this.f9002e) + paddingRight, getMinimumWidth());
        } else {
            iChooseSize = AbstractC0370y0.chooseSize(i5, rect.width() + paddingRight, getMinimumWidth());
            iChooseSize2 = AbstractC0370y0.chooseSize(i7, (this.f9006j * this.f9002e) + paddingBottom, getMinimumHeight());
        }
        setMeasuredDimension(iChooseSize, iChooseSize2);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void smoothScrollToPosition(RecyclerView recyclerView, R0 r02, int i5) {
        if (recyclerView != null) {
            U u5 = new U(recyclerView.getContext());
            recyclerView.showGoToTop();
            u5.setTargetPosition(i5);
            startSmoothScroll(u5);
        }
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final boolean supportsPredictiveItemAnimations() {
        return this.f9016u == null;
    }

    public final void t(View view, int i5, int i7) {
        Rect rect = this.f9017v;
        calculateItemDecorationsForChild(view, rect);
        g1 g1Var = (g1) view.getLayoutParams();
        int iF = F(i5, ((ViewGroup.MarginLayoutParams) g1Var).leftMargin + rect.left, ((ViewGroup.MarginLayoutParams) g1Var).rightMargin + rect.right);
        int iF2 = F(i7, ((ViewGroup.MarginLayoutParams) g1Var).topMargin + rect.top, ((ViewGroup.MarginLayoutParams) g1Var).bottomMargin + rect.bottom);
        if (shouldMeasureChild(view, iF, iF2, g1Var)) {
            view.measure(iF, iF2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x01cf  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0436  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void u(androidx.recyclerview.widget.G0 r17, androidx.recyclerview.widget.R0 r18, boolean r19) {
        /*
            Method dump skipped, instruction units count: 1105
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.StaggeredGridLayoutManager.u(androidx.recyclerview.widget.G0, androidx.recyclerview.widget.R0, boolean):void");
    }

    public final boolean v(int i5) {
        if (this.f9005i == 0) {
            return (i5 == -1) != this.f9009m;
        }
        return ((i5 == -1) == this.f9009m) == isLayoutRTL();
    }

    public final void w(int i5, R0 r02) {
        int iN;
        int i7;
        if (i5 > 0) {
            iN = o();
            i7 = 1;
        } else {
            iN = n();
            i7 = -1;
        }
        O o = this.f9007k;
        o.f8954a = true;
        D(iN, r02);
        C(i7);
        o.f8956c = iN + o.f8957d;
        o.f8955b = Math.abs(i5);
    }

    public final void x(G0 g02, O o) {
        if (!o.f8954a || o.f8961i) {
            return;
        }
        if (o.f8955b == 0) {
            if (o.f8958e == -1) {
                y(g02, o.f8960g);
                return;
            } else {
                z(g02, o.f8959f);
                return;
            }
        }
        int i5 = 1;
        if (o.f8958e == -1) {
            int i7 = o.f8959f;
            int iH = this.f9003f[0].h(i7);
            while (i5 < this.f9002e) {
                int iH2 = this.f9003f[i5].h(i7);
                if (iH2 > iH) {
                    iH = iH2;
                }
                i5++;
            }
            int i9 = i7 - iH;
            y(g02, i9 < 0 ? o.f8960g : o.f8960g - Math.min(i9, o.f8955b));
            return;
        }
        int i10 = o.f8960g;
        int iF = this.f9003f[0].f(i10);
        while (i5 < this.f9002e) {
            int iF2 = this.f9003f[i5].f(i10);
            if (iF2 < iF) {
                iF = iF2;
            }
            i5++;
        }
        int i11 = iF - o.f8960g;
        z(g02, i11 < 0 ? o.f8959f : Math.min(i11, o.f8955b) + o.f8959f);
    }

    public final void y(G0 g02, int i5) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (this.f9004g.e(childAt) < i5 || this.f9004g.o(childAt) < i5) {
                return;
            }
            g1 g1Var = (g1) childAt.getLayoutParams();
            g1Var.getClass();
            if (g1Var.f9144e.f9172a.size() == 1) {
                return;
            }
            j1 j1Var = g1Var.f9144e;
            ArrayList arrayList = j1Var.f9172a;
            int size = arrayList.size();
            View view = (View) arrayList.remove(size - 1);
            g1 g1Var2 = (g1) view.getLayoutParams();
            g1Var2.f9144e = null;
            if (g1Var2.isItemRemoved() || g1Var2.isItemChanged()) {
                j1Var.f9175d -= j1Var.f9177f.f9004g.c(view);
            }
            if (size == 1) {
                j1Var.f9173b = Integer.MIN_VALUE;
            }
            j1Var.f9174c = Integer.MIN_VALUE;
            removeAndRecycleView(childAt, g02);
        }
    }

    public final void z(G0 g02, int i5) {
        while (getChildCount() > 0) {
            View childAt = getChildAt(0);
            if (this.f9004g.b(childAt) > i5 || this.f9004g.n(childAt) > i5) {
                return;
            }
            g1 g1Var = (g1) childAt.getLayoutParams();
            g1Var.getClass();
            if (g1Var.f9144e.f9172a.size() == 1) {
                return;
            }
            j1 j1Var = g1Var.f9144e;
            ArrayList arrayList = j1Var.f9172a;
            View view = (View) arrayList.remove(0);
            g1 g1Var2 = (g1) view.getLayoutParams();
            g1Var2.f9144e = null;
            if (arrayList.size() == 0) {
                j1Var.f9174c = Integer.MIN_VALUE;
            }
            if (g1Var2.isItemRemoved() || g1Var2.isItemChanged()) {
                j1Var.f9175d -= j1Var.f9177f.f9004g.c(view);
            }
            j1Var.f9173b = Integer.MIN_VALUE;
            removeAndRecycleView(childAt, g02);
        }
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final C0372z0 generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new g1((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new g1(layoutParams);
    }
}
