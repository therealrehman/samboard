package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import d6.AbstractC0476d;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class GridLayoutManager extends LinearLayoutManager {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f8898e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f8899f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int[] f8900g;
    public View[] h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final SparseIntArray f8901i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final SparseIntArray f8902j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public I f8903k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Rect f8904l;

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i5, int i7) {
        super(context, attributeSet, i5, i7);
        this.f8898e = false;
        this.f8899f = -1;
        this.f8901i = new SparseIntArray();
        this.f8902j = new SparseIntArray();
        this.f8903k = new G();
        this.f8904l = new Rect();
        p(AbstractC0370y0.getProperties(context, attributeSet, i5, i7).f9291b);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final boolean checkLayoutParams(C0372z0 c0372z0) {
        return c0372z0 instanceof H;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void collectPrefetchPositionsForLayoutState(R0 r02, S s8, InterfaceC0366w0 interfaceC0366w0) {
        int i5;
        int spanSize = this.f8899f;
        for (int i7 = 0; i7 < this.f8899f && (i5 = s8.f8994d) >= 0 && i5 < r02.b() && spanSize > 0; i7++) {
            int i9 = s8.f8994d;
            ((D) interfaceC0366w0).a(i9, Math.max(0, s8.f8997g));
            spanSize -= this.f8903k.getSpanSize(i9);
            s8.f8994d += s8.f8995e;
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.AbstractC0370y0
    public final int computeHorizontalScrollOffset(R0 r02) {
        return super.computeHorizontalScrollOffset(r02);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.AbstractC0370y0
    public final int computeHorizontalScrollRange(R0 r02) {
        return super.computeHorizontalScrollRange(r02);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.AbstractC0370y0
    public final int computeVerticalScrollOffset(R0 r02) {
        return super.computeVerticalScrollOffset(r02);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.AbstractC0370y0
    public final int computeVerticalScrollRange(R0 r02) {
        return super.computeVerticalScrollRange(r02);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final View findReferenceChild(G0 g02, R0 r02, boolean z9, boolean z10) {
        int i5;
        int childCount;
        int childCount2 = getChildCount();
        int i7 = 1;
        if (z10) {
            childCount = getChildCount() - 1;
            i5 = -1;
            i7 = -1;
        } else {
            i5 = childCount2;
            childCount = 0;
        }
        int iB = r02.b();
        ensureLayoutState();
        int iK = this.mOrientationHelper.k();
        int iG = this.mOrientationHelper.g();
        View view = null;
        View view2 = null;
        while (childCount != i5) {
            View childAt = getChildAt(childCount);
            int position = getPosition(childAt);
            if (position >= 0 && position < iB && m(position, g02, r02) == 0) {
                if (((C0372z0) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else {
                    if (this.mOrientationHelper.e(childAt) < iG && this.mOrientationHelper.b(childAt) > iK) {
                        return childAt;
                    }
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            childCount += i7;
        }
        return view != null ? view : view2;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.AbstractC0370y0
    public final C0372z0 generateDefaultLayoutParams() {
        return this.mOrientation == 0 ? new H(-2, -1) : new H(-1, -2);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final C0372z0 generateLayoutParams(Context context, AttributeSet attributeSet) {
        H h = new H(context, attributeSet);
        h.f8905e = -1;
        h.f8906f = 0;
        return h;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final int getColumnCountForAccessibility(G0 g02, R0 r02) {
        if (this.mOrientation == 1) {
            return r02.b() < 1 ? this.f8899f : (l(r02.b() - 1, g02, r02) + 1 != 1 || r02.b() >= this.f8899f) ? this.f8899f : r02.b();
        }
        if (r02.b() < 1) {
            return 0;
        }
        return l(r02.b() - 1, g02, r02) + 1;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final int getRowCountForAccessibility(G0 g02, R0 r02) {
        if (this.mOrientation == 0) {
            return this.f8899f;
        }
        if (r02.b() < 1) {
            return 0;
        }
        return l(r02.b() - 1, g02, r02) + 1;
    }

    public final void i(int i5) {
        int i7;
        int[] iArr = this.f8900g;
        int i9 = this.f8899f;
        if (iArr == null || iArr.length != i9 + 1 || iArr[iArr.length - 1] != i5) {
            iArr = new int[i9 + 1];
        }
        int i10 = 0;
        iArr[0] = 0;
        int i11 = i5 / i9;
        int i12 = i5 % i9;
        int i13 = 0;
        for (int i14 = 1; i14 <= i9; i14++) {
            i10 += i12;
            if (i10 <= 0 || i9 - i10 >= i12) {
                i7 = i11;
            } else {
                i7 = i11 + 1;
                i10 -= i9;
            }
            i13 += i7;
            iArr[i14] = i13;
        }
        this.f8900g = iArr;
    }

    public final void j() {
        View[] viewArr = this.h;
        if (viewArr == null || viewArr.length != this.f8899f) {
            this.h = new View[this.f8899f];
        }
    }

    public final int k(int i5, int i7) {
        if (this.mOrientation != 1 || !isLayoutRTL()) {
            int[] iArr = this.f8900g;
            return iArr[i7 + i5] - iArr[i5];
        }
        int[] iArr2 = this.f8900g;
        int i9 = this.f8899f;
        return iArr2[i9 - i5] - iArr2[(i9 - i5) - i7];
    }

    public final int l(int i5, G0 g02, R0 r02) {
        if (!r02.f8984g) {
            return this.f8903k.getCachedSpanGroupIndex(i5, this.f8899f);
        }
        int iC = g02.c(i5);
        if (iC != -1) {
            return this.f8903k.getCachedSpanGroupIndex(iC, this.f8899f);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + i5);
        return 0;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void layoutChunk(G0 g02, R0 r02, S s8, Q q6) {
        int i5;
        int i7;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int iD;
        int iD2;
        int i14;
        int iD3;
        int childMeasureSpec;
        int childMeasureSpec2;
        boolean z9;
        int i15;
        View viewB;
        int iJ = this.mOrientationHelper.j();
        boolean z10 = iJ != 1073741824;
        int i16 = getChildCount() > 0 ? this.f8900g[this.f8899f] : 0;
        if (z10) {
            q();
        }
        boolean z11 = s8.f8995e == 1;
        int iM = this.f8899f;
        if (!z11) {
            iM = m(s8.f8994d, g02, r02) + n(s8.f8994d, g02, r02);
        }
        int i17 = 0;
        while (i17 < this.f8899f && (i15 = s8.f8994d) >= 0 && i15 < r02.b() && iM > 0) {
            int i18 = s8.f8994d;
            int iN = n(i18, g02, r02);
            if (iN > this.f8899f) {
                throw new IllegalArgumentException(AbstractC0476d.l(A8.l.x("Item at position ", i18, " requires ", iN, " spans but GridLayoutManager has only "), this.f8899f, " spans."));
            }
            iM -= iN;
            if (iM < 0 || (viewB = s8.b(g02)) == null) {
                break;
            }
            this.h[i17] = viewB;
            i17++;
        }
        if (i17 == 0) {
            q6.f8975b = true;
            return;
        }
        if (z11) {
            i9 = 1;
            i7 = i17;
            i5 = 0;
        } else {
            i5 = i17 - 1;
            i7 = -1;
            i9 = -1;
        }
        int i19 = 0;
        while (i5 != i7) {
            View view = this.h[i5];
            H h = (H) view.getLayoutParams();
            int iN2 = n(getPosition(view), g02, r02);
            h.f8906f = iN2;
            h.f8905e = i19;
            i19 += iN2;
            i5 += i9;
        }
        float f2 = 0.0f;
        int i20 = 0;
        for (int i21 = 0; i21 < i17; i21++) {
            View view2 = this.h[i21];
            if (s8.f9000k != null) {
                z9 = false;
                if (z11) {
                    addDisappearingView(view2);
                } else {
                    addDisappearingView(view2, 0);
                }
            } else if (z11) {
                addView(view2);
                z9 = false;
            } else {
                z9 = false;
                addView(view2, 0);
            }
            calculateItemDecorationsForChild(view2, this.f8904l);
            o(view2, iJ, z9);
            int iC = this.mOrientationHelper.c(view2);
            if (iC > i20) {
                i20 = iC;
            }
            float fD = (this.mOrientationHelper.d(view2) * 1.0f) / ((H) view2.getLayoutParams()).f8906f;
            if (fD > f2) {
                f2 = fD;
            }
        }
        if (z10) {
            i(Math.max(Math.round(f2 * this.f8899f), i16));
            i20 = 0;
            for (int i22 = 0; i22 < i17; i22++) {
                View view3 = this.h[i22];
                o(view3, 1073741824, true);
                int iC2 = this.mOrientationHelper.c(view3);
                if (iC2 > i20) {
                    i20 = iC2;
                }
            }
        }
        for (int i23 = 0; i23 < i17; i23++) {
            View view4 = this.h[i23];
            if (this.mOrientationHelper.c(view4) != i20) {
                H h2 = (H) view4.getLayoutParams();
                Rect rect = h2.mDecorInsets;
                int i24 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) h2).topMargin + ((ViewGroup.MarginLayoutParams) h2).bottomMargin;
                int i25 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) h2).leftMargin + ((ViewGroup.MarginLayoutParams) h2).rightMargin;
                int iK = k(h2.f8905e, h2.f8906f);
                if (this.mOrientation == 1) {
                    childMeasureSpec2 = AbstractC0370y0.getChildMeasureSpec(iK, 1073741824, i25, ((ViewGroup.MarginLayoutParams) h2).width, false);
                    childMeasureSpec = View.MeasureSpec.makeMeasureSpec(i20 - i24, 1073741824);
                } else {
                    int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i20 - i25, 1073741824);
                    childMeasureSpec = AbstractC0370y0.getChildMeasureSpec(iK, 1073741824, i24, ((ViewGroup.MarginLayoutParams) h2).height, false);
                    childMeasureSpec2 = iMakeMeasureSpec;
                }
                if (shouldReMeasureChild(view4, childMeasureSpec2, childMeasureSpec, (C0372z0) view4.getLayoutParams())) {
                    view4.measure(childMeasureSpec2, childMeasureSpec);
                }
            }
        }
        int i26 = 0;
        q6.f8974a = i20;
        if (this.mOrientation != 1) {
            if (s8.f8996f == -1) {
                int i27 = s8.f8992b;
                i11 = i27 - i20;
                i10 = i27;
            } else {
                int i28 = s8.f8992b;
                i10 = i28 + i20;
                i11 = i28;
            }
            i12 = 0;
            i13 = 0;
        } else if (s8.f8996f == -1) {
            i13 = s8.f8992b;
            i12 = i13 - i20;
            i11 = 0;
            i10 = 0;
        } else {
            int i29 = s8.f8992b;
            i12 = i29;
            i10 = 0;
            i13 = i29 + i20;
            i11 = 0;
        }
        while (i26 < i17) {
            View view5 = this.h[i26];
            H h9 = (H) view5.getLayoutParams();
            if (this.mOrientation != 1) {
                int paddingTop = getPaddingTop() + this.f8900g[h9.f8905e];
                iD = i11;
                iD2 = i10;
                i14 = paddingTop;
                iD3 = this.mOrientationHelper.d(view5) + paddingTop;
            } else if (isLayoutRTL()) {
                int paddingLeft = getPaddingLeft() + this.f8900g[this.f8899f - h9.f8905e];
                iD2 = paddingLeft;
                i14 = i12;
                iD3 = i13;
                iD = paddingLeft - this.mOrientationHelper.d(view5);
            } else {
                int paddingLeft2 = getPaddingLeft() + this.f8900g[h9.f8905e];
                iD2 = this.mOrientationHelper.d(view5) + paddingLeft2;
                i14 = i12;
                iD3 = i13;
                iD = paddingLeft2;
            }
            layoutDecoratedWithMargins(view5, iD, i14, iD2, iD3);
            if (h9.isItemRemoved() || h9.isItemChanged()) {
                q6.f8976c = true;
            }
            q6.f8977d |= view5.hasFocusable();
            i26++;
            i11 = iD;
            i10 = iD2;
            i12 = i14;
            i13 = iD3;
        }
        Arrays.fill(this.h, (Object) null);
    }

    public final int m(int i5, G0 g02, R0 r02) {
        if (!r02.f8984g) {
            return this.f8903k.getCachedSpanIndex(i5, this.f8899f);
        }
        int i7 = this.f8902j.get(i5, -1);
        if (i7 != -1) {
            return i7;
        }
        int iC = g02.c(i5);
        if (iC != -1) {
            return this.f8903k.getCachedSpanIndex(iC, this.f8899f);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i5);
        return 0;
    }

    public final int n(int i5, G0 g02, R0 r02) {
        if (!r02.f8984g) {
            return this.f8903k.getSpanSize(i5);
        }
        int i7 = this.f8901i.get(i5, -1);
        if (i7 != -1) {
            return i7;
        }
        int iC = g02.c(i5);
        if (iC != -1) {
            return this.f8903k.getSpanSize(iC);
        }
        Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + i5);
        return 1;
    }

    public final void o(View view, int i5, boolean z9) {
        int childMeasureSpec;
        int childMeasureSpec2;
        H h = (H) view.getLayoutParams();
        Rect rect = h.mDecorInsets;
        int i7 = rect.top + rect.bottom + ((ViewGroup.MarginLayoutParams) h).topMargin + ((ViewGroup.MarginLayoutParams) h).bottomMargin;
        int i9 = rect.left + rect.right + ((ViewGroup.MarginLayoutParams) h).leftMargin + ((ViewGroup.MarginLayoutParams) h).rightMargin;
        int iK = k(h.f8905e, h.f8906f);
        if (this.mOrientation == 1) {
            childMeasureSpec2 = AbstractC0370y0.getChildMeasureSpec(iK, i5, i9, ((ViewGroup.MarginLayoutParams) h).width, false);
            childMeasureSpec = AbstractC0370y0.getChildMeasureSpec(this.mOrientationHelper.l(), getHeightMode(), i7, ((ViewGroup.MarginLayoutParams) h).height, true);
        } else {
            int childMeasureSpec3 = AbstractC0370y0.getChildMeasureSpec(iK, i5, i7, ((ViewGroup.MarginLayoutParams) h).height, false);
            int childMeasureSpec4 = AbstractC0370y0.getChildMeasureSpec(this.mOrientationHelper.l(), getWidthMode(), i9, ((ViewGroup.MarginLayoutParams) h).width, true);
            childMeasureSpec = childMeasureSpec3;
            childMeasureSpec2 = childMeasureSpec4;
        }
        C0372z0 c0372z0 = (C0372z0) view.getLayoutParams();
        if (z9 ? shouldReMeasureChild(view, childMeasureSpec2, childMeasureSpec, c0372z0) : shouldMeasureChild(view, childMeasureSpec2, childMeasureSpec, c0372z0)) {
            view.measure(childMeasureSpec2, childMeasureSpec);
        }
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void onAnchorReady(G0 g02, R0 r02, P p4, int i5) {
        super.onAnchorReady(g02, r02, p4, i5);
        q();
        if (r02.b() > 0 && !r02.f8984g) {
            boolean z9 = i5 == 1;
            int iM = m(p4.f8970b, g02, r02);
            if (z9) {
                while (iM > 0) {
                    int i7 = p4.f8970b;
                    if (i7 <= 0) {
                        break;
                    }
                    int i9 = i7 - 1;
                    p4.f8970b = i9;
                    iM = m(i9, g02, r02);
                }
            } else {
                int iB = r02.b() - 1;
                int i10 = p4.f8970b;
                while (i10 < iB) {
                    int i11 = i10 + 1;
                    int iM2 = m(i11, g02, r02);
                    if (iM2 <= iM) {
                        break;
                    }
                    i10 = i11;
                    iM = iM2;
                }
                p4.f8970b = i10;
            }
        }
        j();
    }

    /* JADX WARN: Code restructure failed: missing block: B:56:0x00d1, code lost:
    
        if (r13 == (r2 > r15)) goto L47;
     */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x010f  */
    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.AbstractC0370y0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.View onFocusSearchFailed(android.view.View r24, int r25, androidx.recyclerview.widget.G0 r26, androidx.recyclerview.widget.R0 r27) {
        /*
            Method dump skipped, instruction units count: 317
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.GridLayoutManager.onFocusSearchFailed(android.view.View, int, androidx.recyclerview.widget.G0, androidx.recyclerview.widget.R0):android.view.View");
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void onInitializeAccessibilityNodeInfo(G0 g02, R0 r02, L.l lVar) {
        super.onInitializeAccessibilityNodeInfo(g02, r02, lVar);
        lVar.j(GridView.class.getName());
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void onInitializeAccessibilityNodeInfoForItem(G0 g02, R0 r02, View view, L.l lVar) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof H)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, lVar);
            return;
        }
        H h = (H) layoutParams;
        int iL = l(h.getViewLayoutPosition(), g02, r02);
        if (this.mOrientation == 0) {
            lVar.l(L.k.a(h.f8905e, h.f8906f, iL, 1, false, false));
        } else {
            lVar.l(L.k.a(iL, 1, h.f8905e, h.f8906f, false, false));
        }
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void onItemsAdded(RecyclerView recyclerView, int i5, int i7) {
        this.f8903k.invalidateSpanIndexCache();
        this.f8903k.invalidateSpanGroupIndexCache();
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void onItemsChanged(RecyclerView recyclerView) {
        this.f8903k.invalidateSpanIndexCache();
        this.f8903k.invalidateSpanGroupIndexCache();
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void onItemsMoved(RecyclerView recyclerView, int i5, int i7, int i9) {
        this.f8903k.invalidateSpanIndexCache();
        this.f8903k.invalidateSpanGroupIndexCache();
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void onItemsRemoved(RecyclerView recyclerView, int i5, int i7) {
        this.f8903k.invalidateSpanIndexCache();
        this.f8903k.invalidateSpanGroupIndexCache();
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void onItemsUpdated(RecyclerView recyclerView, int i5, int i7, Object obj) {
        this.f8903k.invalidateSpanIndexCache();
        this.f8903k.invalidateSpanGroupIndexCache();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.AbstractC0370y0
    public void onLayoutChildren(G0 g02, R0 r02) {
        boolean z9 = r02.f8984g;
        SparseIntArray sparseIntArray = this.f8902j;
        SparseIntArray sparseIntArray2 = this.f8901i;
        if (z9) {
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                H h = (H) getChildAt(i5).getLayoutParams();
                int viewLayoutPosition = h.getViewLayoutPosition();
                sparseIntArray2.put(viewLayoutPosition, h.f8906f);
                sparseIntArray.put(viewLayoutPosition, h.f8905e);
            }
        }
        super.onLayoutChildren(g02, r02);
        sparseIntArray2.clear();
        sparseIntArray.clear();
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.AbstractC0370y0
    public final void onLayoutCompleted(R0 r02) {
        super.onLayoutCompleted(r02);
        this.f8898e = false;
    }

    public final void p(int i5) {
        if (i5 == this.f8899f) {
            return;
        }
        this.f8898e = true;
        if (i5 < 1) {
            throw new IllegalArgumentException(A8.l.o(i5, "Span count should be at least 1. Provided "));
        }
        this.f8899f = i5;
        this.f8903k.invalidateSpanIndexCache();
        requestLayout();
    }

    public final void q() {
        int height;
        int paddingTop;
        if (getOrientation() == 1) {
            height = getWidth() - getPaddingRight();
            paddingTop = getPaddingLeft();
        } else {
            height = getHeight() - getPaddingBottom();
            paddingTop = getPaddingTop();
        }
        i(height - paddingTop);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.AbstractC0370y0
    public final int scrollHorizontallyBy(int i5, G0 g02, R0 r02) {
        q();
        j();
        return super.scrollHorizontallyBy(i5, g02, r02);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.AbstractC0370y0
    public final int scrollVerticallyBy(int i5, G0 g02, R0 r02) {
        q();
        j();
        return super.scrollVerticallyBy(i5, g02, r02);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final void setMeasuredDimension(Rect rect, int i5, int i7) {
        int iChooseSize;
        int iChooseSize2;
        if (this.f8900g == null) {
            super.setMeasuredDimension(rect, i5, i7);
        }
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        if (this.mOrientation == 1) {
            iChooseSize2 = AbstractC0370y0.chooseSize(i7, rect.height() + paddingBottom, getMinimumHeight());
            int[] iArr = this.f8900g;
            iChooseSize = AbstractC0370y0.chooseSize(i5, iArr[iArr.length - 1] + paddingRight, getMinimumWidth());
        } else {
            iChooseSize = AbstractC0370y0.chooseSize(i5, rect.width() + paddingRight, getMinimumWidth());
            int[] iArr2 = this.f8900g;
            iChooseSize2 = AbstractC0370y0.chooseSize(i7, iArr2[iArr2.length - 1] + paddingBottom, getMinimumHeight());
        }
        setMeasuredDimension(iChooseSize, iChooseSize2);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager
    public final void setStackFromEnd(boolean z9) {
        if (z9) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.setStackFromEnd(false);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.AbstractC0370y0
    public final boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && !this.f8898e;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public final C0372z0 generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            H h = new H((ViewGroup.MarginLayoutParams) layoutParams);
            h.f8905e = -1;
            h.f8906f = 0;
            return h;
        }
        H h2 = new H(layoutParams);
        h2.f8905e = -1;
        h2.f8906f = 0;
        return h2;
    }

    public GridLayoutManager(int i5) {
        this.f8898e = false;
        this.f8899f = -1;
        this.f8901i = new SparseIntArray();
        this.f8902j = new SparseIntArray();
        this.f8903k = new G();
        this.f8904l = new Rect();
        p(i5);
    }

    public GridLayoutManager(int i5, int i7) {
        super(1, false);
        this.f8898e = false;
        this.f8899f = -1;
        this.f8901i = new SparseIntArray();
        this.f8902j = new SparseIntArray();
        this.f8903k = new G();
        this.f8904l = new Rect();
        p(i5);
    }
}
