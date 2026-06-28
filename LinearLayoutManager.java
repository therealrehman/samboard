package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PointF;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.PathInterpolator;
import androidx.appcompat.widget.AbstractC0152g1;

/* JADX INFO: loaded from: classes.dex */
public class LinearLayoutManager extends AbstractC0370y0 implements P0 {
    static final boolean DEBUG = false;
    public static final int HORIZONTAL = 0;
    public static final int INVALID_OFFSET = Integer.MIN_VALUE;
    private static final float MAX_SCROLL_FACTOR = 0.33333334f;
    private static final String TAG = "SeslLinearLayoutManager";
    public static final int VERTICAL = 1;
    final P mAnchorInfo;
    private int mInitialPrefetchItemCount;
    private boolean mLastStackFromEnd;
    private final Q mLayoutChunkResult;
    private S mLayoutState;
    int mOrientation;
    Z mOrientationHelper;
    private PathInterpolator mPathInterpolator;
    T mPendingSavedState;
    int mPendingScrollPosition;
    int mPendingScrollPositionOffset;
    private boolean mRecycleChildrenOnDetach;
    private int[] mReusableIntPair;
    boolean mReverseLayout;
    boolean mShouldReverseLayout;
    private boolean mSmoothScrollbarEnabled;
    boolean mStackFromEnd;

    public LinearLayoutManager() {
        this(1, false);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    @SuppressLint({"UnknownNullness"})
    public void assertNotInLayoutOrScroll(String str) {
        if (this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    public final void c() {
        Log.d(TAG, "internal representation of views on the screen");
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            Log.d(TAG, "item " + getPosition(childAt) + ", coord:" + this.mOrientationHelper.e(childAt));
        }
        Log.d(TAG, "==============");
    }

    public void calculateExtraLayoutSpace(R0 r02, int[] iArr) {
        int i5;
        int extraLayoutSpace = getExtraLayoutSpace(r02);
        if (this.mLayoutState.f8996f == -1) {
            i5 = 0;
        } else {
            i5 = extraLayoutSpace;
            extraLayoutSpace = 0;
        }
        iArr[0] = extraLayoutSpace;
        iArr[1] = i5;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public boolean canScrollHorizontally() {
        return this.mOrientation == 0;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public boolean canScrollVertically() {
        return this.mOrientation == 1;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    @SuppressLint({"UnknownNullness"})
    public void collectAdjacentPrefetchPositions(int i5, int i7, R0 r02, InterfaceC0366w0 interfaceC0366w0) {
        if (this.mOrientation != 0) {
            i5 = i7;
        }
        if (getChildCount() == 0 || i5 == 0) {
            return;
        }
        ensureLayoutState();
        f(i5 > 0 ? 1 : -1, Math.abs(i5), true, r02);
        collectPrefetchPositionsForLayoutState(r02, this.mLayoutState, interfaceC0366w0);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    @SuppressLint({"UnknownNullness"})
    public void collectInitialPrefetchPositions(int i5, InterfaceC0366w0 interfaceC0366w0) {
        boolean z9;
        int i7;
        T t8 = this.mPendingSavedState;
        if (t8 == null || (i7 = t8.f9022e) < 0) {
            e();
            z9 = this.mShouldReverseLayout;
            i7 = this.mPendingScrollPosition;
            if (i7 == -1) {
                i7 = z9 ? i5 - 1 : 0;
            }
        } else {
            z9 = t8.f9024g;
        }
        int i9 = z9 ? -1 : 1;
        for (int i10 = 0; i10 < this.mInitialPrefetchItemCount && i7 >= 0 && i7 < i5; i10++) {
            ((D) interfaceC0366w0).a(i7, 0);
            i7 += i9;
        }
    }

    public void collectPrefetchPositionsForLayoutState(R0 r02, S s8, InterfaceC0366w0 interfaceC0366w0) {
        int i5 = s8.f8994d;
        if (i5 < 0 || i5 >= r02.b()) {
            return;
        }
        ((D) interfaceC0366w0).a(i5, Math.max(0, s8.f8997g));
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    @SuppressLint({"UnknownNullness"})
    public int computeHorizontalScrollExtent(R0 r02) {
        return computeScrollExtent(r02);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    @SuppressLint({"UnknownNullness"})
    public int computeHorizontalScrollOffset(R0 r02) {
        return computeScrollOffset(r02);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    @SuppressLint({"UnknownNullness"})
    public int computeHorizontalScrollRange(R0 r02) {
        return computeScrollRange(r02);
    }

    public final int computeScrollExtent(R0 r02) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return AbstractC0328d.d(r02, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
    }

    public final int computeScrollOffset(R0 r02) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return AbstractC0328d.e(r02, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    public final int computeScrollRange(R0 r02) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return AbstractC0328d.f(r02, this.mOrientationHelper, findFirstVisibleChildClosestToStart(!this.mSmoothScrollbarEnabled, true), findFirstVisibleChildClosestToEnd(!this.mSmoothScrollbarEnabled, true), this, this.mSmoothScrollbarEnabled);
    }

    @Override // androidx.recyclerview.widget.P0
    @SuppressLint({"UnknownNullness"})
    public PointF computeScrollVectorForPosition(int i5) {
        if (getChildCount() == 0) {
            return null;
        }
        int i7 = (i5 < getPosition(getChildAt(0))) != this.mShouldReverseLayout ? -1 : 1;
        return this.mOrientation == 0 ? new PointF(i7, 0.0f) : new PointF(0.0f, i7);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    @SuppressLint({"UnknownNullness"})
    public int computeVerticalScrollExtent(R0 r02) {
        return computeScrollExtent(r02);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    @SuppressLint({"UnknownNullness"})
    public int computeVerticalScrollOffset(R0 r02) {
        return computeScrollOffset(r02);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    @SuppressLint({"UnknownNullness"})
    public int computeVerticalScrollRange(R0 r02) {
        return computeScrollRange(r02);
    }

    public int convertFocusDirectionToLayoutDirection(int i5) {
        return i5 != 1 ? i5 != 2 ? i5 != 17 ? i5 != 33 ? i5 != 66 ? (i5 == 130 && this.mOrientation == 1) ? 1 : Integer.MIN_VALUE : this.mOrientation == 0 ? 1 : Integer.MIN_VALUE : this.mOrientation == 1 ? -1 : Integer.MIN_VALUE : this.mOrientation == 0 ? -1 : Integer.MIN_VALUE : (this.mOrientation != 1 && isLayoutRTL()) ? -1 : 1 : (this.mOrientation != 1 && isLayoutRTL()) ? 1 : -1;
    }

    public S createLayoutState() {
        S s8 = new S();
        s8.f8991a = true;
        s8.h = 0;
        s8.f8998i = 0;
        s8.f9000k = null;
        return s8;
    }

    public final void d(G0 g02, S s8) {
        if (!s8.f8991a || s8.f9001l) {
            return;
        }
        int i5 = s8.f8997g;
        int i7 = s8.f8998i;
        if (s8.f8996f == -1) {
            int childCount = getChildCount();
            if (i5 < 0) {
                return;
            }
            int iF = (this.mOrientationHelper.f() - i5) + i7;
            if (this.mShouldReverseLayout) {
                for (int i9 = 0; i9 < childCount; i9++) {
                    View childAt = getChildAt(i9);
                    if (this.mOrientationHelper.e(childAt) < iF || this.mOrientationHelper.o(childAt) < iF) {
                        recycleChildren(g02, 0, i9);
                        return;
                    }
                }
                return;
            }
            int i10 = childCount - 1;
            for (int i11 = i10; i11 >= 0; i11--) {
                View childAt2 = getChildAt(i11);
                if (this.mOrientationHelper.e(childAt2) < iF || this.mOrientationHelper.o(childAt2) < iF) {
                    recycleChildren(g02, i10, i11);
                    return;
                }
            }
            return;
        }
        if (i5 < 0) {
            return;
        }
        int i12 = i5 - i7;
        int childCount2 = getChildCount();
        if (!this.mShouldReverseLayout) {
            for (int i13 = 0; i13 < childCount2; i13++) {
                View childAt3 = getChildAt(i13);
                if (this.mOrientationHelper.b(childAt3) > i12 || this.mOrientationHelper.n(childAt3) > i12) {
                    recycleChildren(g02, 0, i13);
                    return;
                }
            }
            return;
        }
        int i14 = childCount2 - 1;
        for (int i15 = i14; i15 >= 0; i15--) {
            View childAt4 = getChildAt(i15);
            if (this.mOrientationHelper.b(childAt4) > i12 || this.mOrientationHelper.n(childAt4) > i12) {
                recycleChildren(g02, i14, i15);
                return;
            }
        }
    }

    public final void e() {
        if (this.mOrientation == 1 || !isLayoutRTL()) {
            this.mShouldReverseLayout = this.mReverseLayout;
        } else {
            this.mShouldReverseLayout = !this.mReverseLayout;
        }
    }

    public void ensureLayoutState() {
        if (this.mLayoutState == null) {
            this.mLayoutState = createLayoutState();
        }
    }

    public final void f(int i5, int i7, boolean z9, R0 r02) {
        int iK;
        this.mLayoutState.f9001l = resolveIsInfinite();
        this.mLayoutState.f8996f = i5;
        int[] iArr = this.mReusableIntPair;
        iArr[0] = 0;
        iArr[1] = 0;
        calculateExtraLayoutSpace(r02, iArr);
        int iMax = Math.max(0, this.mReusableIntPair[0]);
        int iMax2 = Math.max(0, this.mReusableIntPair[1]);
        boolean z10 = i5 == 1;
        S s8 = this.mLayoutState;
        int i9 = z10 ? iMax2 : iMax;
        s8.h = i9;
        if (!z10) {
            iMax = iMax2;
        }
        s8.f8998i = iMax;
        if (z10) {
            s8.h = this.mOrientationHelper.h() + i9;
            View childClosestToEnd = getChildClosestToEnd();
            S s9 = this.mLayoutState;
            s9.f8995e = this.mShouldReverseLayout ? -1 : 1;
            int position = getPosition(childClosestToEnd);
            S s10 = this.mLayoutState;
            s9.f8994d = position + s10.f8995e;
            s10.f8992b = this.mOrientationHelper.b(childClosestToEnd);
            iK = this.mOrientationHelper.b(childClosestToEnd) - this.mOrientationHelper.g();
        } else {
            View childClosestToStart = getChildClosestToStart();
            S s11 = this.mLayoutState;
            s11.h = this.mOrientationHelper.k() + s11.h;
            S s12 = this.mLayoutState;
            s12.f8995e = this.mShouldReverseLayout ? 1 : -1;
            int position2 = getPosition(childClosestToStart);
            S s13 = this.mLayoutState;
            s12.f8994d = position2 + s13.f8995e;
            s13.f8992b = this.mOrientationHelper.e(childClosestToStart);
            iK = (-this.mOrientationHelper.e(childClosestToStart)) + this.mOrientationHelper.k();
        }
        S s14 = this.mLayoutState;
        s14.f8993c = i7;
        if (z9) {
            s14.f8993c = i7 - iK;
        }
        s14.f8997g = iK;
    }

    public int fill(G0 g02, S s8, R0 r02, boolean z9) {
        int i5;
        int i7 = s8.f8993c;
        int i9 = s8.f8997g;
        if (i9 != Integer.MIN_VALUE) {
            if (i7 < 0) {
                s8.f8997g = i9 + i7;
            }
            d(g02, s8);
        }
        int i10 = s8.f8993c + s8.h;
        Q q6 = this.mLayoutChunkResult;
        while (true) {
            if ((!s8.f9001l && i10 <= 0) || (i5 = s8.f8994d) < 0 || i5 >= r02.b()) {
                break;
            }
            q6.f8974a = 0;
            q6.f8975b = false;
            q6.f8976c = false;
            q6.f8977d = false;
            layoutChunk(g02, r02, s8, q6);
            if (!q6.f8975b) {
                int i11 = s8.f8992b;
                int i12 = q6.f8974a;
                s8.f8992b = (s8.f8996f * i12) + i11;
                if (!q6.f8976c || s8.f9000k != null || !r02.f8984g) {
                    s8.f8993c -= i12;
                    i10 -= i12;
                }
                int i13 = s8.f8997g;
                if (i13 != Integer.MIN_VALUE) {
                    int i14 = i13 + i12;
                    s8.f8997g = i14;
                    int i15 = s8.f8993c;
                    if (i15 < 0) {
                        s8.f8997g = i14 + i15;
                    }
                    d(g02, s8);
                }
                if (z9 && q6.f8977d) {
                    break;
                }
            } else {
                break;
            }
        }
        return i7 - s8.f8993c;
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View viewFindOneVisibleChild = findOneVisibleChild(0, getChildCount(), true, false);
        if (viewFindOneVisibleChild == null) {
            return -1;
        }
        return getPosition(viewFindOneVisibleChild);
    }

    public View findFirstVisibleChildClosestToEnd(boolean z9, boolean z10) {
        return this.mShouldReverseLayout ? findOneVisibleChild(0, getChildCount(), z9, z10) : findOneVisibleChild(getChildCount() - 1, -1, z9, z10);
    }

    public View findFirstVisibleChildClosestToStart(boolean z9, boolean z10) {
        return this.mShouldReverseLayout ? findOneVisibleChild(getChildCount() - 1, -1, z9, z10) : findOneVisibleChild(0, getChildCount(), z9, z10);
    }

    public int findFirstVisibleItemPosition() {
        View viewFindOneVisibleChild = findOneVisibleChild(0, getChildCount(), false, true);
        if (viewFindOneVisibleChild == null) {
            return -1;
        }
        return getPosition(viewFindOneVisibleChild);
    }

    public int findLastCompletelyVisibleItemPosition() {
        View viewFindOneVisibleChild = findOneVisibleChild(getChildCount() - 1, -1, true, false);
        if (viewFindOneVisibleChild == null) {
            return -1;
        }
        return getPosition(viewFindOneVisibleChild);
    }

    public int findLastVisibleItemPosition() {
        View viewFindOneVisibleChild = findOneVisibleChild(getChildCount() - 1, -1, false, true);
        if (viewFindOneVisibleChild == null) {
            return -1;
        }
        return getPosition(viewFindOneVisibleChild);
    }

    public View findOnePartiallyOrCompletelyInvisibleChild(int i5, int i7) {
        int i9;
        int i10;
        ensureLayoutState();
        if (i7 <= i5 && i7 >= i5) {
            return getChildAt(i5);
        }
        if (this.mOrientationHelper.e(getChildAt(i5)) < this.mOrientationHelper.k()) {
            i9 = 16644;
            i10 = 16388;
        } else {
            i9 = 4161;
            i10 = 4097;
        }
        return this.mOrientation == 0 ? this.mHorizontalBoundCheck.a(i5, i7, i9, i10) : this.mVerticalBoundCheck.a(i5, i7, i9, i10);
    }

    public View findOneVisibleChild(int i5, int i7, boolean z9, boolean z10) {
        ensureLayoutState();
        int i9 = z9 ? 24579 : 320;
        int i10 = z10 ? 320 : 0;
        return this.mOrientation == 0 ? this.mHorizontalBoundCheck.a(i5, i7, i9, i10) : this.mVerticalBoundCheck.a(i5, i7, i9, i10);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0078  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View findReferenceChild(androidx.recyclerview.widget.G0 r17, androidx.recyclerview.widget.R0 r18, boolean r19, boolean r20) {
        /*
            r16 = this;
            r0 = r16
            r16.ensureLayoutState()
            int r1 = r16.getChildCount()
            r2 = 0
            int r1 = java.lang.Math.max(r1, r2)
            r3 = 1
            if (r20 == 0) goto L16
            int r1 = r1 + (-1)
            r4 = -1
            r5 = r4
            goto L19
        L16:
            r4 = r1
            r1 = r2
            r5 = r3
        L19:
            int r6 = r18.b()
            androidx.recyclerview.widget.Z r7 = r0.mOrientationHelper
            int r7 = r7.k()
            androidx.recyclerview.widget.Z r8 = r0.mOrientationHelper
            int r8 = r8.g()
            r9 = 0
            r10 = r9
            r11 = r10
        L2c:
            if (r1 == r4) goto L7b
            android.view.View r12 = r0.getChildAt(r1)
            int r13 = r0.getPosition(r12)
            if (r13 < 0) goto L79
            if (r13 >= r6) goto L79
            androidx.recyclerview.widget.Z r13 = r0.mOrientationHelper
            int r13 = r13.e(r12)
            androidx.recyclerview.widget.Z r14 = r0.mOrientationHelper
            int r14 = r14.b(r12)
            android.view.ViewGroup$LayoutParams r15 = r12.getLayoutParams()
            androidx.recyclerview.widget.z0 r15 = (androidx.recyclerview.widget.C0372z0) r15
            boolean r15 = r15.isItemRemoved()
            if (r15 == 0) goto L56
            if (r11 != 0) goto L79
            r11 = r12
            goto L79
        L56:
            if (r14 > r7) goto L5c
            if (r13 >= r7) goto L5c
            r15 = r3
            goto L5d
        L5c:
            r15 = r2
        L5d:
            if (r13 < r8) goto L63
            if (r14 <= r8) goto L63
            r13 = r3
            goto L64
        L63:
            r13 = r2
        L64:
            if (r15 != 0) goto L6a
            if (r13 == 0) goto L69
            goto L6a
        L69:
            return r12
        L6a:
            if (r19 == 0) goto L72
            if (r13 == 0) goto L6f
            goto L74
        L6f:
            if (r9 != 0) goto L79
            goto L78
        L72:
            if (r15 == 0) goto L76
        L74:
            r10 = r12
            goto L79
        L76:
            if (r9 != 0) goto L79
        L78:
            r9 = r12
        L79:
            int r1 = r1 + r5
            goto L2c
        L7b:
            if (r9 == 0) goto L7e
            goto L83
        L7e:
            if (r10 == 0) goto L82
            r9 = r10
            goto L83
        L82:
            r9 = r11
        L83:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.findReferenceChild(androidx.recyclerview.widget.G0, androidx.recyclerview.widget.R0, boolean, boolean):android.view.View");
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    @SuppressLint({"UnknownNullness"})
    public View findViewByPosition(int i5) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return null;
        }
        int position = i5 - getPosition(getChildAt(0));
        if (position >= 0 && position < childCount) {
            View childAt = getChildAt(position);
            if (getPosition(childAt) == i5) {
                return childAt;
            }
        }
        return super.findViewByPosition(i5);
    }

    public final int fixLayoutEndGap(int i5, G0 g02, R0 r02, boolean z9) {
        int iG;
        int iG2 = this.mOrientationHelper.g() - i5;
        if (iG2 <= 0) {
            return 0;
        }
        int i7 = -scrollBy(-iG2, g02, r02);
        int i9 = i5 + i7;
        if (!z9 || (iG = this.mOrientationHelper.g() - i9) <= 0) {
            return i7;
        }
        this.mOrientationHelper.p(iG);
        return iG + i7;
    }

    public final int fixLayoutStartGap(int i5, G0 g02, R0 r02, boolean z9) {
        int iK;
        int iK2 = i5 - this.mOrientationHelper.k();
        if (iK2 <= 0) {
            return 0;
        }
        int i7 = -scrollBy(iK2, g02, r02);
        int i9 = i5 + i7;
        if (!z9 || (iK = i9 - this.mOrientationHelper.k()) <= 0) {
            return i7;
        }
        this.mOrientationHelper.p(-iK);
        return i7 - iK;
    }

    public final void g(int i5, int i7) {
        this.mLayoutState.f8993c = this.mOrientationHelper.g() - i7;
        S s8 = this.mLayoutState;
        s8.f8995e = this.mShouldReverseLayout ? -1 : 1;
        s8.f8994d = i5;
        s8.f8996f = 1;
        s8.f8992b = i7;
        s8.f8997g = Integer.MIN_VALUE;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    @SuppressLint({"UnknownNullness"})
    public C0372z0 generateDefaultLayoutParams() {
        return new C0372z0(-2, -2);
    }

    public final View getChildClosestToEnd() {
        return getChildAt(this.mShouldReverseLayout ? 0 : getChildCount() - 1);
    }

    public final View getChildClosestToStart() {
        return getChildAt(this.mShouldReverseLayout ? getChildCount() - 1 : 0);
    }

    @Deprecated
    public int getExtraLayoutSpace(R0 r02) {
        if (r02.f8978a != -1) {
            return this.mOrientationHelper.l();
        }
        return 0;
    }

    public int getInitialPrefetchItemCount() {
        return this.mInitialPrefetchItemCount;
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public boolean getRecycleChildrenOnDetach() {
        return this.mRecycleChildrenOnDetach;
    }

    public boolean getReverseLayout() {
        return this.mReverseLayout;
    }

    public boolean getStackFromEnd() {
        return this.mStackFromEnd;
    }

    public final void h(int i5, int i7) {
        this.mLayoutState.f8993c = i7 - this.mOrientationHelper.k();
        S s8 = this.mLayoutState;
        s8.f8994d = i5;
        s8.f8995e = this.mShouldReverseLayout ? 1 : -1;
        s8.f8996f = -1;
        s8.f8992b = i7;
        s8.f8997g = Integer.MIN_VALUE;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    public boolean isLayoutRTL() {
        return getLayoutDirection() == 1;
    }

    public boolean isSmoothScrollbarEnabled() {
        return this.mSmoothScrollbarEnabled;
    }

    public void layoutChunk(G0 g02, R0 r02, S s8, Q q6) {
        int i5;
        int i7;
        int i9;
        int paddingLeft;
        int iD;
        View viewB = s8.b(g02);
        if (viewB == null) {
            q6.f8975b = true;
            return;
        }
        C0372z0 c0372z0 = (C0372z0) viewB.getLayoutParams();
        if (s8.f9000k == null) {
            if (this.mShouldReverseLayout == (s8.f8996f == -1)) {
                addView(viewB);
            } else {
                addView(viewB, 0);
            }
        } else {
            if (this.mShouldReverseLayout == (s8.f8996f == -1)) {
                addDisappearingView(viewB);
            } else {
                addDisappearingView(viewB, 0);
            }
        }
        measureChildWithMargins(viewB, 0, 0);
        q6.f8974a = this.mOrientationHelper.c(viewB);
        if (this.mOrientation == 1) {
            if (isLayoutRTL()) {
                iD = getWidth() - getPaddingRight();
                paddingLeft = iD - this.mOrientationHelper.d(viewB);
            } else {
                paddingLeft = getPaddingLeft();
                iD = this.mOrientationHelper.d(viewB) + paddingLeft;
            }
            if (s8.f8996f == -1) {
                int i10 = s8.f8992b;
                i9 = i10;
                i7 = iD;
                i5 = i10 - q6.f8974a;
            } else {
                int i11 = s8.f8992b;
                i5 = i11;
                i7 = iD;
                i9 = q6.f8974a + i11;
            }
        } else {
            int paddingTop = getPaddingTop();
            int iD2 = this.mOrientationHelper.d(viewB) + paddingTop;
            if (s8.f8996f == -1) {
                int i12 = s8.f8992b;
                i7 = i12;
                i5 = paddingTop;
                i9 = iD2;
                paddingLeft = i12 - q6.f8974a;
            } else {
                int i13 = s8.f8992b;
                i5 = paddingTop;
                i7 = q6.f8974a + i13;
                i9 = iD2;
                paddingLeft = i13;
            }
        }
        layoutDecoratedWithMargins(viewB, paddingLeft, i5, i7, i9);
        if (c0372z0.isItemRemoved() || c0372z0.isItemChanged()) {
            q6.f8976c = true;
        }
        q6.f8977d = viewB.hasFocusable();
    }

    public void onAnchorReady(G0 g02, R0 r02, P p4, int i5) {
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    @SuppressLint({"UnknownNullness"})
    public void onDetachedFromWindow(RecyclerView recyclerView, G0 g02) {
        onDetachedFromWindow(recyclerView);
        if (this.mRecycleChildrenOnDetach) {
            removeAndRecycleAllViews(g02);
            g02.b();
        }
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    @SuppressLint({"UnknownNullness"})
    public View onFocusSearchFailed(View view, int i5, G0 g02, R0 r02) {
        int iConvertFocusDirectionToLayoutDirection;
        e();
        if (getChildCount() == 0 || (iConvertFocusDirectionToLayoutDirection = convertFocusDirectionToLayoutDirection(i5)) == Integer.MIN_VALUE) {
            return null;
        }
        ensureLayoutState();
        f(iConvertFocusDirectionToLayoutDirection, (int) (this.mOrientationHelper.l() * MAX_SCROLL_FACTOR), false, r02);
        S s8 = this.mLayoutState;
        s8.f8997g = Integer.MIN_VALUE;
        s8.f8991a = false;
        fill(g02, s8, r02, true);
        View viewFindOnePartiallyOrCompletelyInvisibleChild = iConvertFocusDirectionToLayoutDirection == -1 ? this.mShouldReverseLayout ? findOnePartiallyOrCompletelyInvisibleChild(getChildCount() - 1, -1) : findOnePartiallyOrCompletelyInvisibleChild(0, getChildCount()) : this.mShouldReverseLayout ? findOnePartiallyOrCompletelyInvisibleChild(0, getChildCount()) : findOnePartiallyOrCompletelyInvisibleChild(getChildCount() - 1, -1);
        View childClosestToStart = iConvertFocusDirectionToLayoutDirection == -1 ? getChildClosestToStart() : getChildClosestToEnd();
        if (!childClosestToStart.hasFocusable()) {
            return viewFindOnePartiallyOrCompletelyInvisibleChild;
        }
        if (viewFindOnePartiallyOrCompletelyInvisibleChild == null) {
            return null;
        }
        return childClosestToStart;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    @SuppressLint({"UnknownNullness"})
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            accessibilityEvent.setFromIndex(findFirstVisibleItemPosition());
            accessibilityEvent.setToIndex(findLastVisibleItemPosition());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01a0  */
    @Override // androidx.recyclerview.widget.AbstractC0370y0
    @android.annotation.SuppressLint({"UnknownNullness"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onLayoutChildren(androidx.recyclerview.widget.G0 r14, androidx.recyclerview.widget.R0 r15) {
        /*
            Method dump skipped, instruction units count: 1027
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.LinearLayoutManager.onLayoutChildren(androidx.recyclerview.widget.G0, androidx.recyclerview.widget.R0):void");
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    @SuppressLint({"UnknownNullness"})
    public void onLayoutCompleted(R0 r02) {
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mAnchorInfo.d();
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    @SuppressLint({"UnknownNullness"})
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof T) {
            T t8 = (T) parcelable;
            this.mPendingSavedState = t8;
            if (this.mPendingScrollPosition != -1) {
                t8.f9022e = -1;
            }
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    @SuppressLint({"UnknownNullness"})
    public Parcelable onSaveInstanceState() {
        T t8 = this.mPendingSavedState;
        if (t8 != null) {
            T t9 = new T();
            t9.f9022e = t8.f9022e;
            t9.f9023f = t8.f9023f;
            t9.f9024g = t8.f9024g;
            return t9;
        }
        T t10 = new T();
        if (getChildCount() > 0) {
            ensureLayoutState();
            boolean z9 = this.mLastStackFromEnd ^ this.mShouldReverseLayout;
            t10.f9024g = z9;
            if (z9) {
                View childClosestToEnd = getChildClosestToEnd();
                t10.f9023f = this.mOrientationHelper.g() - this.mOrientationHelper.b(childClosestToEnd);
                t10.f9022e = getPosition(childClosestToEnd);
            } else {
                View childClosestToStart = getChildClosestToStart();
                t10.f9022e = getPosition(childClosestToStart);
                t10.f9023f = this.mOrientationHelper.e(childClosestToStart) - this.mOrientationHelper.k();
            }
        } else {
            t10.f9022e = -1;
        }
        return t10;
    }

    public void prepareForDrop(View view, View view2, int i5, int i7) {
        assertNotInLayoutOrScroll("Cannot drop a view during a scroll or layout calculation");
        ensureLayoutState();
        e();
        int position = getPosition(view);
        int position2 = getPosition(view2);
        byte b3 = position < position2 ? (byte) 1 : (byte) -1;
        if (this.mShouldReverseLayout) {
            if (b3 == 1) {
                scrollToPositionWithOffset(position2, this.mOrientationHelper.g() - (this.mOrientationHelper.c(view) + this.mOrientationHelper.e(view2)));
                return;
            } else {
                scrollToPositionWithOffset(position2, this.mOrientationHelper.g() - this.mOrientationHelper.b(view2));
                return;
            }
        }
        if (b3 == -1) {
            scrollToPositionWithOffset(position2, this.mOrientationHelper.e(view2));
        } else {
            scrollToPositionWithOffset(position2, this.mOrientationHelper.b(view2) - this.mOrientationHelper.c(view));
        }
    }

    public final void recycleChildren(G0 g02, int i5, int i7) {
        if (i5 == i7) {
            return;
        }
        if (i7 <= i5) {
            while (i5 > i7) {
                removeAndRecycleViewAt(i5, g02);
                i5--;
            }
        } else {
            for (int i9 = i7 - 1; i9 >= i5; i9--) {
                removeAndRecycleViewAt(i9, g02);
            }
        }
    }

    public boolean resolveIsInfinite() {
        return this.mOrientationHelper.i() == 0 && this.mOrientationHelper.f() == 0;
    }

    public int scrollBy(int i5, G0 g02, R0 r02) {
        if (getChildCount() == 0 || i5 == 0) {
            return 0;
        }
        ensureLayoutState();
        this.mLayoutState.f8991a = true;
        int i7 = i5 > 0 ? 1 : -1;
        int iAbs = Math.abs(i5);
        f(i7, iAbs, true, r02);
        S s8 = this.mLayoutState;
        int iFill = fill(g02, s8, r02, false) + s8.f8997g;
        if (iFill < 0) {
            return 0;
        }
        if (iAbs > iFill) {
            i5 = i7 * iFill;
        }
        this.mOrientationHelper.p(-i5);
        this.mLayoutState.f8999j = i5;
        if (r02.f8981d != 2) {
            this.mRecyclerView.showGoToTop();
        }
        return i5;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    @SuppressLint({"UnknownNullness"})
    public int scrollHorizontallyBy(int i5, G0 g02, R0 r02) {
        if (this.mOrientation == 1) {
            return 0;
        }
        return scrollBy(i5, g02, r02);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void scrollToPosition(int i5) {
        this.mPendingScrollPosition = i5;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        T t8 = this.mPendingSavedState;
        if (t8 != null) {
            t8.f9022e = -1;
        }
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.showGoToTop();
        }
        requestLayout();
    }

    public void scrollToPositionWithOffset(int i5, int i7) {
        this.mPendingScrollPosition = i5;
        this.mPendingScrollPositionOffset = i7;
        T t8 = this.mPendingSavedState;
        if (t8 != null) {
            t8.f9022e = -1;
        }
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.showGoToTop();
        }
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    @SuppressLint({"UnknownNullness"})
    public int scrollVerticallyBy(int i5, G0 g02, R0 r02) {
        if (this.mOrientation == 0) {
            return 0;
        }
        return scrollBy(i5, g02, r02);
    }

    public void setInitialPrefetchItemCount(int i5) {
        this.mInitialPrefetchItemCount = i5;
    }

    public void setOrientation(int i5) {
        if (i5 != 0 && i5 != 1) {
            throw new IllegalArgumentException(A8.l.o(i5, "invalid orientation:"));
        }
        assertNotInLayoutOrScroll(null);
        if (i5 != this.mOrientation || this.mOrientationHelper == null) {
            Z zA = Z.a(this, i5);
            this.mOrientationHelper = zA;
            this.mAnchorInfo.f8969a = zA;
            this.mOrientation = i5;
            requestLayout();
        }
    }

    public void setRecycleChildrenOnDetach(boolean z9) {
        this.mRecycleChildrenOnDetach = z9;
    }

    public void setReverseLayout(boolean z9) {
        assertNotInLayoutOrScroll(null);
        if (z9 == this.mReverseLayout) {
            return;
        }
        this.mReverseLayout = z9;
        requestLayout();
    }

    public void setSmoothScrollbarEnabled(boolean z9) {
        this.mSmoothScrollbarEnabled = z9;
    }

    public void setStackFromEnd(boolean z9) {
        assertNotInLayoutOrScroll(null);
        if (this.mStackFromEnd == z9) {
            return;
        }
        this.mStackFromEnd = z9;
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public boolean shouldMeasureTwice() {
        return (getHeightMode() == 1073741824 || getWidthMode() == 1073741824 || !hasFlexibleChildInBothOrientations()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void smoothScrollToPosition(RecyclerView recyclerView, R0 r02, int i5) {
        U u5 = new U(recyclerView.getContext());
        recyclerView.showGoToTop();
        u5.setTargetPosition(i5);
        startSmoothScroll(u5);
        AbstractC0152g1.f(i5, "SS pos to : ", TAG);
    }

    public void smoothScrollToPositionJumpIfNeeded(RecyclerView recyclerView, R0 r02, int i5) {
        C0323a0 c0323a0 = new C0323a0(recyclerView.getContext(), 2, this);
        recyclerView.showGoToTop();
        c0323a0.setTargetPosition(i5);
        startSmoothScroll(c0323a0);
        Log.d(TAG, "smoothScroller2");
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && this.mLastStackFromEnd == this.mStackFromEnd;
    }

    public void validateChildOrder() {
        Log.d(TAG, "validating child count " + getChildCount());
        if (getChildCount() < 1) {
            return;
        }
        int position = getPosition(getChildAt(0));
        int iE = this.mOrientationHelper.e(getChildAt(0));
        if (this.mShouldReverseLayout) {
            for (int i5 = 1; i5 < getChildCount(); i5++) {
                View childAt = getChildAt(i5);
                int position2 = getPosition(childAt);
                int iE2 = this.mOrientationHelper.e(childAt);
                if (position2 < position) {
                    c();
                    StringBuilder sb = new StringBuilder("detected invalid position. loc invalid? ");
                    sb.append(iE2 < iE);
                    throw new RuntimeException(sb.toString());
                }
                if (iE2 > iE) {
                    c();
                    throw new RuntimeException("detected invalid location");
                }
            }
            return;
        }
        for (int i7 = 1; i7 < getChildCount(); i7++) {
            View childAt2 = getChildAt(i7);
            int position3 = getPosition(childAt2);
            int iE3 = this.mOrientationHelper.e(childAt2);
            if (position3 < position) {
                c();
                StringBuilder sb2 = new StringBuilder("detected invalid position. loc invalid? ");
                sb2.append(iE3 < iE);
                throw new RuntimeException(sb2.toString());
            }
            if (iE3 < iE) {
                c();
                throw new RuntimeException("detected invalid location");
            }
        }
    }

    public LinearLayoutManager(int i5, boolean z9) {
        this.mOrientation = 1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPathInterpolator = new PathInterpolator(0.22f, 0.5f, 0.0f, 1.0f);
        this.mPendingSavedState = null;
        this.mAnchorInfo = new P();
        this.mLayoutChunkResult = new Q();
        this.mInitialPrefetchItemCount = 2;
        this.mReusableIntPair = new int[2];
        setOrientation(i5);
        setReverseLayout(z9);
    }

    @SuppressLint({"UnknownNullness"})
    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i5, int i7) {
        this.mOrientation = 1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPathInterpolator = new PathInterpolator(0.22f, 0.5f, 0.0f, 1.0f);
        this.mPendingSavedState = null;
        this.mAnchorInfo = new P();
        this.mLayoutChunkResult = new Q();
        this.mInitialPrefetchItemCount = 2;
        this.mReusableIntPair = new int[2];
        C0368x0 properties = AbstractC0370y0.getProperties(context, attributeSet, i5, i7);
        setOrientation(properties.f9290a);
        setReverseLayout(properties.f9292c);
        setStackFromEnd(properties.f9293d);
    }
}
