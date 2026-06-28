package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: androidx.recyclerview.widget.y0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0370y0 {
    boolean mAutoMeasure;
    C0342k mChildHelper;
    private int mHeight;
    private int mHeightMode;
    l1 mHorizontalBoundCheck;
    private final k1 mHorizontalBoundCheckCallback;
    boolean mIsAttachedToWindow;
    private boolean mItemPrefetchEnabled;
    private boolean mMeasurementCacheEnabled;
    int mPrefetchMaxCountObserved;
    boolean mPrefetchMaxObservedInInitialPrefetch;
    RecyclerView mRecyclerView;
    boolean mRequestedSimpleAnimations;
    Q0 mSmoothScroller;
    l1 mVerticalBoundCheck;
    private final k1 mVerticalBoundCheckCallback;
    private int mWidth;
    private int mWidthMode;

    public AbstractC0370y0() {
        C0364v0 c0364v0 = new C0364v0(this, 0);
        this.mHorizontalBoundCheckCallback = c0364v0;
        C0364v0 c0364v02 = new C0364v0(this, 1);
        this.mVerticalBoundCheckCallback = c0364v02;
        this.mHorizontalBoundCheck = new l1(c0364v0);
        this.mVerticalBoundCheck = new l1(c0364v02);
        this.mRequestedSimpleAnimations = false;
        this.mIsAttachedToWindow = false;
        this.mAutoMeasure = false;
        this.mMeasurementCacheEnabled = true;
        this.mItemPrefetchEnabled = true;
    }

    public static int chooseSize(int i5, int i7, int i9) {
        int mode = View.MeasureSpec.getMode(i5);
        int size = View.MeasureSpec.getSize(i5);
        return mode != Integer.MIN_VALUE ? mode != 1073741824 ? Math.max(i7, i9) : size : Math.min(size, Math.max(i7, i9));
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x000c A[PHI: r3
      0x000c: PHI (r3v5 int) = (r3v0 int), (r3v2 int), (r3v0 int) binds: [B:7:0x0010, B:11:0x0016, B:4:0x000a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:6:0x000e  */
    @java.lang.Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getChildMeasureSpec(int r1, int r2, int r3, boolean r4) {
        /*
            int r1 = r1 - r2
            r2 = 0
            int r1 = java.lang.Math.max(r2, r1)
            r0 = 1073741824(0x40000000, float:2.0)
            if (r4 == 0) goto L10
            if (r3 < 0) goto Le
        Lc:
            r2 = r0
            goto L1e
        Le:
            r3 = r2
            goto L1e
        L10:
            if (r3 < 0) goto L13
            goto Lc
        L13:
            r4 = -1
            if (r3 != r4) goto L18
            r3 = r1
            goto Lc
        L18:
            r4 = -2
            if (r3 != r4) goto Le
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1
        L1e:
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r3, r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.AbstractC0370y0.getChildMeasureSpec(int, int, int, boolean):int");
    }

    public static C0368x0 getProperties(Context context, AttributeSet attributeSet, int i5, int i7) {
        C0368x0 c0368x0 = new C0368x0();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, G0.a.f1023b, i5, i7);
        c0368x0.f9290a = typedArrayObtainStyledAttributes.getInt(0, 1);
        c0368x0.f9291b = typedArrayObtainStyledAttributes.getInt(10, 1);
        c0368x0.f9292c = typedArrayObtainStyledAttributes.getBoolean(9, false);
        c0368x0.f9293d = typedArrayObtainStyledAttributes.getBoolean(11, false);
        typedArrayObtainStyledAttributes.recycle();
        return c0368x0;
    }

    public static boolean isMeasurementUpToDate(int i5, int i7, int i9) {
        int mode = View.MeasureSpec.getMode(i7);
        int size = View.MeasureSpec.getSize(i7);
        if (i9 > 0 && i5 != i9) {
            return false;
        }
        if (mode == Integer.MIN_VALUE) {
            return size >= i5;
        }
        if (mode != 0) {
            return mode == 1073741824 && size == i5;
        }
        return true;
    }

    public final void a(View view, int i5, boolean z9) {
        V0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (z9 || childViewHolderInt.isRemoved()) {
            q.k kVar = this.mRecyclerView.mViewInfoStore.f9205a;
            m1 m1VarA = (m1) kVar.getOrDefault(childViewHolderInt, null);
            if (m1VarA == null) {
                m1VarA = m1.a();
                kVar.put(childViewHolderInt, m1VarA);
            }
            m1VarA.f9193a |= 1;
        } else {
            this.mRecyclerView.mViewInfoStore.c(childViewHolderInt);
        }
        C0372z0 c0372z0 = (C0372z0) view.getLayoutParams();
        if (childViewHolderInt.wasReturnedFromScrap() || childViewHolderInt.isScrap()) {
            if (childViewHolderInt.isScrap()) {
                childViewHolderInt.unScrap();
            } else {
                childViewHolderInt.clearReturnedFromScrapFlag();
            }
            this.mChildHelper.b(view, i5, view.getLayoutParams(), false);
        } else if (view.getParent() == this.mRecyclerView) {
            int iJ = this.mChildHelper.j(view);
            if (i5 == -1) {
                i5 = this.mChildHelper.e();
            }
            if (iJ == -1) {
                StringBuilder sb = new StringBuilder("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:");
                sb.append(this.mRecyclerView.indexOfChild(view));
                throw new IllegalStateException(A8.l.p(this.mRecyclerView, sb));
            }
            if (iJ != i5) {
                this.mRecyclerView.mLayout.moveView(iJ, i5);
            }
        } else {
            this.mChildHelper.a(view, i5, false);
            c0372z0.mInsetsDirty = true;
            Q0 q02 = this.mSmoothScroller;
            if (q02 != null && q02.isRunning()) {
                this.mSmoothScroller.onChildAttachedToWindow(view);
            }
        }
        if (c0372z0.mPendingInvalidate) {
            if (RecyclerView.sVerboseLoggingEnabled) {
                Log.d("SeslRecyclerView", "consuming pending invalidate on child " + c0372z0.mViewHolder);
            }
            childViewHolderInt.itemView.invalidate();
            c0372z0.mPendingInvalidate = false;
        }
    }

    @SuppressLint({"UnknownNullness"})
    public void addDisappearingView(View view) {
        addDisappearingView(view, -1);
    }

    @SuppressLint({"UnknownNullness"})
    public void addView(View view) {
        addView(view, -1);
    }

    public void assertInLayoutOrScroll(String str) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.assertInLayoutOrScroll(str);
        }
    }

    @SuppressLint({"UnknownNullness"})
    public void assertNotInLayoutOrScroll(String str) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.assertNotInLayoutOrScroll(str);
        }
    }

    public void attachView(View view, int i5, C0372z0 c0372z0) {
        V0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt.isRemoved()) {
            q.k kVar = this.mRecyclerView.mViewInfoStore.f9205a;
            m1 m1VarA = (m1) kVar.getOrDefault(childViewHolderInt, null);
            if (m1VarA == null) {
                m1VarA = m1.a();
                kVar.put(childViewHolderInt, m1VarA);
            }
            m1VarA.f9193a |= 1;
        } else {
            this.mRecyclerView.mViewInfoStore.c(childViewHolderInt);
        }
        this.mChildHelper.b(view, i5, c0372z0, childViewHolderInt.isRemoved());
    }

    public final void b(G0 g02, int i5, View view) {
        V0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt.shouldIgnore()) {
            if (RecyclerView.sVerboseLoggingEnabled) {
                Log.d("SeslRecyclerView", "ignoring view " + childViewHolderInt);
                return;
            }
            return;
        }
        if (childViewHolderInt.isInvalid() && !childViewHolderInt.isRemoved() && !this.mRecyclerView.mAdapter.hasStableIds()) {
            removeViewAt(i5);
            g02.k(childViewHolderInt);
        } else {
            detachViewAt(i5);
            g02.l(view);
            this.mRecyclerView.mViewInfoStore.c(childViewHolderInt);
        }
    }

    public void calculateItemDecorationsForChild(View view, Rect rect) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null) {
            rect.set(0, 0, 0, 0);
        } else {
            rect.set(recyclerView.getItemDecorInsetsForChild(view));
        }
    }

    public abstract boolean canScrollHorizontally();

    public abstract boolean canScrollVertically();

    public boolean checkLayoutParams(C0372z0 c0372z0) {
        return c0372z0 != null;
    }

    @SuppressLint({"UnknownNullness"})
    public void collectAdjacentPrefetchPositions(int i5, int i7, R0 r02, InterfaceC0366w0 interfaceC0366w0) {
    }

    @SuppressLint({"UnknownNullness"})
    public void collectInitialPrefetchPositions(int i5, InterfaceC0366w0 interfaceC0366w0) {
    }

    public abstract int computeHorizontalScrollExtent(R0 r02);

    public abstract int computeHorizontalScrollOffset(R0 r02);

    public abstract int computeHorizontalScrollRange(R0 r02);

    public abstract int computeVerticalScrollExtent(R0 r02);

    public abstract int computeVerticalScrollOffset(R0 r02);

    public abstract int computeVerticalScrollRange(R0 r02);

    public void detachAndScrapAttachedViews(G0 g02) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            b(g02, childCount, getChildAt(childCount));
        }
    }

    public void detachAndScrapView(View view, G0 g02) {
        b(g02, this.mChildHelper.j(view), view);
    }

    public void detachAndScrapViewAt(int i5, G0 g02) {
        b(g02, i5, getChildAt(i5));
    }

    public void detachView(View view) {
        int iJ = this.mChildHelper.j(view);
        if (iJ >= 0) {
            this.mChildHelper.c(iJ);
        }
    }

    public void detachViewAt(int i5) {
        getChildAt(i5);
        this.mChildHelper.c(i5);
    }

    public void dispatchAttachedToWindow(RecyclerView recyclerView) {
        this.mIsAttachedToWindow = true;
        onAttachedToWindow(recyclerView);
    }

    public void dispatchDetachedFromWindow(RecyclerView recyclerView, G0 g02) {
        this.mIsAttachedToWindow = false;
        onDetachedFromWindow(recyclerView, g02);
    }

    @SuppressLint({"UnknownNullness"})
    public void endAnimation(View view) {
        AbstractC0358s0 abstractC0358s0 = this.mRecyclerView.mItemAnimator;
        if (abstractC0358s0 != null) {
            abstractC0358s0.d(RecyclerView.getChildViewHolderInt(view));
        }
    }

    public View findContainingItemView(View view) {
        View viewFindContainingItemView;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || (viewFindContainingItemView = recyclerView.findContainingItemView(view)) == null || this.mChildHelper.k(viewFindContainingItemView)) {
            return null;
        }
        return viewFindContainingItemView;
    }

    public View findViewByPosition(int i5) {
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            V0 childViewHolderInt = RecyclerView.getChildViewHolderInt(childAt);
            if (childViewHolderInt != null && childViewHolderInt.getLayoutPosition() == i5 && !childViewHolderInt.shouldIgnore() && (this.mRecyclerView.mState.f8984g || !childViewHolderInt.isRemoved())) {
                return childAt;
            }
        }
        return null;
    }

    public abstract C0372z0 generateDefaultLayoutParams();

    @SuppressLint({"UnknownNullness"})
    public C0372z0 generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C0372z0 ? new C0372z0((C0372z0) layoutParams) : layoutParams instanceof ViewGroup.MarginLayoutParams ? new C0372z0((ViewGroup.MarginLayoutParams) layoutParams) : new C0372z0(layoutParams);
    }

    public int getBaseline() {
        return -1;
    }

    public int getBottomDecorationHeight(View view) {
        return ((C0372z0) view.getLayoutParams()).mDecorInsets.bottom;
    }

    public View getChildAt(int i5) {
        C0342k c0342k = this.mChildHelper;
        if (c0342k != null) {
            return c0342k.d(i5);
        }
        return null;
    }

    public int getChildCount() {
        C0342k c0342k = this.mChildHelper;
        if (c0342k != null) {
            return c0342k.e();
        }
        return 0;
    }

    public boolean getClipToPadding() {
        RecyclerView recyclerView = this.mRecyclerView;
        return recyclerView != null && recyclerView.mClipToPadding;
    }

    public int getColumnCountForAccessibility(G0 g02, R0 r02) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || recyclerView.mAdapter == null || !canScrollHorizontally()) {
            return 1;
        }
        return this.mRecyclerView.mAdapter.getItemCount();
    }

    public int getDecoratedBottom(View view) {
        return getBottomDecorationHeight(view) + view.getBottom();
    }

    public void getDecoratedBoundsWithMargins(View view, Rect rect) {
        RecyclerView.getDecoratedBoundsWithMarginsInt(view, rect);
    }

    public int getDecoratedLeft(View view) {
        return view.getLeft() - getLeftDecorationWidth(view);
    }

    public int getDecoratedMeasuredHeight(View view) {
        Rect rect = ((C0372z0) view.getLayoutParams()).mDecorInsets;
        return view.getMeasuredHeight() + rect.top + rect.bottom;
    }

    public int getDecoratedMeasuredWidth(View view) {
        Rect rect = ((C0372z0) view.getLayoutParams()).mDecorInsets;
        return view.getMeasuredWidth() + rect.left + rect.right;
    }

    public int getDecoratedRight(View view) {
        return getRightDecorationWidth(view) + view.getRight();
    }

    public int getDecoratedTop(View view) {
        return view.getTop() - getTopDecorationHeight(view);
    }

    public View getFocusedChild() {
        View focusedChild;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || (focusedChild = recyclerView.getFocusedChild()) == null || this.mChildHelper.k(focusedChild)) {
            return null;
        }
        return focusedChild;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public int getHeightMode() {
        return this.mHeightMode;
    }

    public int getItemCount() {
        RecyclerView recyclerView = this.mRecyclerView;
        AbstractC0341j0 adapter = recyclerView != null ? recyclerView.getAdapter() : null;
        if (adapter != null) {
            return adapter.getItemCount();
        }
        return 0;
    }

    public int getItemViewType(View view) {
        return RecyclerView.getChildViewHolderInt(view).getItemViewType();
    }

    public int getLayoutDirection() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null) {
            Log.e("SeslRecyclerView", "RecyclerView is null.");
            return 0;
        }
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        return recyclerView.getLayoutDirection();
    }

    public int getLeftDecorationWidth(View view) {
        return ((C0372z0) view.getLayoutParams()).mDecorInsets.left;
    }

    public int getMinimumHeight() {
        RecyclerView recyclerView = this.mRecyclerView;
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        return recyclerView.getMinimumHeight();
    }

    public int getMinimumWidth() {
        RecyclerView recyclerView = this.mRecyclerView;
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        return recyclerView.getMinimumWidth();
    }

    public int getPaddingBottom() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            return recyclerView.getPaddingBottom();
        }
        return 0;
    }

    public int getPaddingEnd() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null) {
            return 0;
        }
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        return recyclerView.getPaddingEnd();
    }

    public int getPaddingLeft() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            return recyclerView.getPaddingLeft();
        }
        return 0;
    }

    public int getPaddingRight() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            return recyclerView.getPaddingRight();
        }
        return 0;
    }

    public int getPaddingStart() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null) {
            return 0;
        }
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        return recyclerView.getPaddingStart();
    }

    public int getPaddingTop() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            return recyclerView.getPaddingTop();
        }
        return 0;
    }

    public int getPosition(View view) {
        if (view != null) {
            return ((C0372z0) view.getLayoutParams()).getViewLayoutPosition();
        }
        Log.e("SeslRecyclerView", "View is null.");
        return -1;
    }

    public int getRightDecorationWidth(View view) {
        return ((C0372z0) view.getLayoutParams()).mDecorInsets.right;
    }

    public int getRowCountForAccessibility(G0 g02, R0 r02) {
        AbstractC0341j0 abstractC0341j0;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || (abstractC0341j0 = recyclerView.mAdapter) == null) {
            return 1;
        }
        if (abstractC0341j0.seslUseCustomAccessibilityPosition()) {
            if (canScrollVertically()) {
                return this.mRecyclerView.mAdapter.seslGetAccessibilityItemCount();
            }
            return 1;
        }
        if (canScrollVertically()) {
            return this.mRecyclerView.mAdapter.getItemCount();
        }
        return 1;
    }

    public int getSelectionModeForAccessibility(G0 g02, R0 r02) {
        return 0;
    }

    public int getTopDecorationHeight(View view) {
        return ((C0372z0) view.getLayoutParams()).mDecorInsets.top;
    }

    public void getTransformedBoundingBox(View view, boolean z9, Rect rect) {
        Matrix matrix;
        if (z9) {
            Rect rect2 = ((C0372z0) view.getLayoutParams()).mDecorInsets;
            rect.set(-rect2.left, -rect2.top, view.getWidth() + rect2.right, view.getHeight() + rect2.bottom);
        } else {
            rect.set(0, 0, view.getWidth(), view.getHeight());
        }
        if (this.mRecyclerView != null && (matrix = view.getMatrix()) != null && !matrix.isIdentity()) {
            RectF rectF = this.mRecyclerView.mTempRectF;
            rectF.set(rect);
            matrix.mapRect(rectF);
            rect.set((int) Math.floor(rectF.left), (int) Math.floor(rectF.top), (int) Math.ceil(rectF.right), (int) Math.ceil(rectF.bottom));
        }
        rect.offset(view.getLeft(), view.getTop());
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getWidthMode() {
        return this.mWidthMode;
    }

    public boolean hasFlexibleChildInBothOrientations() {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            ViewGroup.LayoutParams layoutParams = getChildAt(i5).getLayoutParams();
            if (layoutParams.width < 0 && layoutParams.height < 0) {
                return true;
            }
        }
        return false;
    }

    public boolean hasFocus() {
        RecyclerView recyclerView = this.mRecyclerView;
        return recyclerView != null && recyclerView.hasFocus();
    }

    public void ignoreView(View view) {
        ViewParent parent = view.getParent();
        RecyclerView recyclerView = this.mRecyclerView;
        if (parent != recyclerView || recyclerView.indexOfChild(view) == -1) {
            throw new IllegalArgumentException(A8.l.p(this.mRecyclerView, new StringBuilder("View should be fully attached to be ignored")));
        }
        V0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        childViewHolderInt.addFlags(128);
        this.mRecyclerView.mViewInfoStore.d(childViewHolderInt);
    }

    public boolean isAttachedToWindow() {
        return this.mIsAttachedToWindow;
    }

    public boolean isAutoMeasureEnabled() {
        return this.mAutoMeasure;
    }

    public boolean isFocused() {
        RecyclerView recyclerView = this.mRecyclerView;
        return recyclerView != null && recyclerView.isFocused();
    }

    public final boolean isItemPrefetchEnabled() {
        return this.mItemPrefetchEnabled;
    }

    public boolean isLayoutHierarchical(G0 g02, R0 r02) {
        return false;
    }

    public boolean isMeasurementCacheEnabled() {
        return this.mMeasurementCacheEnabled;
    }

    public boolean isSmoothScrolling() {
        Q0 q02 = this.mSmoothScroller;
        return q02 != null && q02.isRunning();
    }

    public boolean isViewPartiallyVisible(View view, boolean z9, boolean z10) {
        boolean z11 = this.mHorizontalBoundCheck.b(view) && this.mVerticalBoundCheck.b(view);
        return z9 ? z11 : !z11;
    }

    public void layoutDecorated(View view, int i5, int i7, int i9, int i10) {
        Rect rect = ((C0372z0) view.getLayoutParams()).mDecorInsets;
        view.layout(i5 + rect.left, i7 + rect.top, i9 - rect.right, i10 - rect.bottom);
    }

    public void layoutDecoratedWithMargins(View view, int i5, int i7, int i9, int i10) {
        C0372z0 c0372z0 = (C0372z0) view.getLayoutParams();
        Rect rect = c0372z0.mDecorInsets;
        view.layout(i5 + rect.left + ((ViewGroup.MarginLayoutParams) c0372z0).leftMargin, i7 + rect.top + ((ViewGroup.MarginLayoutParams) c0372z0).topMargin, (i9 - rect.right) - ((ViewGroup.MarginLayoutParams) c0372z0).rightMargin, (i10 - rect.bottom) - ((ViewGroup.MarginLayoutParams) c0372z0).bottomMargin);
    }

    public void measureChild(View view, int i5, int i7) {
        C0372z0 c0372z0 = (C0372z0) view.getLayoutParams();
        Rect itemDecorInsetsForChild = this.mRecyclerView.getItemDecorInsetsForChild(view);
        int i9 = itemDecorInsetsForChild.left + itemDecorInsetsForChild.right + i5;
        int i10 = itemDecorInsetsForChild.top + itemDecorInsetsForChild.bottom + i7;
        int childMeasureSpec = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingRight() + getPaddingLeft() + i9, ((ViewGroup.MarginLayoutParams) c0372z0).width, canScrollHorizontally());
        int childMeasureSpec2 = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingBottom() + getPaddingTop() + i10, ((ViewGroup.MarginLayoutParams) c0372z0).height, canScrollVertically());
        if (shouldMeasureChild(view, childMeasureSpec, childMeasureSpec2, c0372z0)) {
            view.measure(childMeasureSpec, childMeasureSpec2);
        }
    }

    public void measureChildWithMargins(View view, int i5, int i7) {
        C0372z0 c0372z0 = (C0372z0) view.getLayoutParams();
        Rect itemDecorInsetsForChild = this.mRecyclerView.getItemDecorInsetsForChild(view);
        int i9 = itemDecorInsetsForChild.left + itemDecorInsetsForChild.right + i5;
        int i10 = itemDecorInsetsForChild.top + itemDecorInsetsForChild.bottom + i7;
        int childMeasureSpec = getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingRight() + getPaddingLeft() + ((ViewGroup.MarginLayoutParams) c0372z0).leftMargin + ((ViewGroup.MarginLayoutParams) c0372z0).rightMargin + i9, ((ViewGroup.MarginLayoutParams) c0372z0).width, canScrollHorizontally());
        int childMeasureSpec2 = getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingBottom() + getPaddingTop() + ((ViewGroup.MarginLayoutParams) c0372z0).topMargin + ((ViewGroup.MarginLayoutParams) c0372z0).bottomMargin + i10, ((ViewGroup.MarginLayoutParams) c0372z0).height, canScrollVertically());
        if (shouldMeasureChild(view, childMeasureSpec, childMeasureSpec2, c0372z0)) {
            view.measure(childMeasureSpec, childMeasureSpec2);
        }
    }

    public void moveView(int i5, int i7) {
        View childAt = getChildAt(i5);
        if (childAt != null) {
            detachViewAt(i5);
            attachView(childAt, i7);
        } else {
            throw new IllegalArgumentException("Cannot move a child from non-existing index:" + i5 + this.mRecyclerView.toString());
        }
    }

    public void offsetChildrenHorizontal(int i5) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.offsetChildrenHorizontal(i5);
        }
    }

    public void offsetChildrenVertical(int i5) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.offsetChildrenVertical(i5);
        }
    }

    public void onAdapterChanged(AbstractC0341j0 abstractC0341j0, AbstractC0341j0 abstractC0341j02) {
    }

    public boolean onAddFocusables(RecyclerView recyclerView, ArrayList<View> arrayList, int i5, int i7) {
        return false;
    }

    public void onAttachedToWindow(RecyclerView recyclerView) {
    }

    @Deprecated
    public void onDetachedFromWindow(RecyclerView recyclerView) {
    }

    public abstract void onDetachedFromWindow(RecyclerView recyclerView, G0 g02);

    public View onFocusSearchFailed(View view, int i5, G0 g02, R0 r02) {
        return null;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        RecyclerView recyclerView = this.mRecyclerView;
        onInitializeAccessibilityEvent(recyclerView.mRecycler, recyclerView.mState, accessibilityEvent);
    }

    public void onInitializeAccessibilityNodeInfo(L.l lVar) {
        RecyclerView recyclerView = this.mRecyclerView;
        onInitializeAccessibilityNodeInfo(recyclerView.mRecycler, recyclerView.mState, lVar);
    }

    public void onInitializeAccessibilityNodeInfoForItem(View view, L.l lVar) {
        V0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt == null || childViewHolderInt.isRemoved() || this.mChildHelper.k(childViewHolderInt.itemView)) {
            return;
        }
        RecyclerView recyclerView = this.mRecyclerView;
        onInitializeAccessibilityNodeInfoForItem(recyclerView.mRecycler, recyclerView.mState, view, lVar);
    }

    public View onInterceptFocusSearch(View view, int i5) {
        return null;
    }

    public void onItemsAdded(RecyclerView recyclerView, int i5, int i7) {
    }

    public void onItemsChanged(RecyclerView recyclerView) {
    }

    public void onItemsMoved(RecyclerView recyclerView, int i5, int i7, int i9) {
    }

    public void onItemsRemoved(RecyclerView recyclerView, int i5, int i7) {
    }

    public void onItemsUpdated(RecyclerView recyclerView, int i5, int i7) {
    }

    public abstract void onLayoutChildren(G0 g02, R0 r02);

    public abstract void onLayoutCompleted(R0 r02);

    public void onMeasure(G0 g02, R0 r02, int i5, int i7) {
        this.mRecyclerView.defaultOnMeasure(i5, i7);
    }

    @Deprecated
    public boolean onRequestChildFocus(RecyclerView recyclerView, View view, View view2) {
        return isSmoothScrolling() || recyclerView.isComputingLayout();
    }

    @SuppressLint({"UnknownNullness"})
    public void onRestoreInstanceState(Parcelable parcelable) {
    }

    public Parcelable onSaveInstanceState() {
        return null;
    }

    public void onScrollStateChanged(int i5) {
    }

    public void onSmoothScrollerStopped(Q0 q02) {
        if (this.mSmoothScroller == q02) {
            this.mSmoothScroller = null;
        }
    }

    public boolean performAccessibilityAction(int i5, Bundle bundle) {
        RecyclerView recyclerView = this.mRecyclerView;
        return performAccessibilityAction(recyclerView.mRecycler, recyclerView.mState, i5, bundle);
    }

    public boolean performAccessibilityActionForItem(G0 g02, R0 r02, View view, int i5, Bundle bundle) {
        return false;
    }

    public void postOnAnimation(Runnable runnable) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
            recyclerView.postOnAnimation(runnable);
        }
    }

    public void removeAllViews() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            this.mChildHelper.l(childCount);
        }
    }

    public void removeAndRecycleAllViews(G0 g02) {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            if (!RecyclerView.getChildViewHolderInt(getChildAt(childCount)).shouldIgnore()) {
                removeAndRecycleViewAt(childCount, g02);
            }
        }
    }

    public void removeAndRecycleScrapInt(G0 g02) {
        ArrayList arrayList;
        int size = g02.f8891a.size();
        int i5 = size - 1;
        while (true) {
            arrayList = g02.f8891a;
            if (i5 < 0) {
                break;
            }
            View view = ((V0) arrayList.get(i5)).itemView;
            V0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.setIsRecyclable(false);
                if (childViewHolderInt.isTmpDetached()) {
                    this.mRecyclerView.removeDetachedView(view, false);
                }
                AbstractC0358s0 abstractC0358s0 = this.mRecyclerView.mItemAnimator;
                if (abstractC0358s0 != null) {
                    abstractC0358s0.d(childViewHolderInt);
                }
                childViewHolderInt.setIsRecyclable(true);
                V0 childViewHolderInt2 = RecyclerView.getChildViewHolderInt(view);
                childViewHolderInt2.mScrapContainer = null;
                childViewHolderInt2.mInChangeScrap = false;
                childViewHolderInt2.clearReturnedFromScrapFlag();
                g02.k(childViewHolderInt2);
            }
            i5--;
        }
        arrayList.clear();
        ArrayList arrayList2 = g02.f8892b;
        if (arrayList2 != null) {
            arrayList2.clear();
        }
        if (size > 0) {
            this.mRecyclerView.invalidate();
        }
    }

    public void removeAndRecycleView(View view, G0 g02) {
        removeView(view);
        g02.j(view);
    }

    public void removeAndRecycleViewAt(int i5, G0 g02) {
        View childAt = getChildAt(i5);
        removeViewAt(i5);
        g02.j(childAt);
    }

    public boolean removeCallbacks(Runnable runnable) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            return recyclerView.removeCallbacks(runnable);
        }
        return false;
    }

    public void removeDetachedView(View view) {
        this.mRecyclerView.removeDetachedView(view, false);
    }

    @SuppressLint({"UnknownNullness"})
    public void removeView(View view) {
        C0342k c0342k = this.mChildHelper;
        C0327c0 c0327c0 = c0342k.f9178a;
        int i5 = c0342k.f9181d;
        if (i5 == 1) {
            throw new IllegalStateException("Cannot call removeView(At) within removeView(At)");
        }
        if (i5 == 2) {
            throw new IllegalStateException("Cannot call removeView(At) within removeViewIfHidden");
        }
        try {
            c0342k.f9181d = 1;
            c0342k.f9182e = view;
            int iIndexOfChild = c0327c0.f9119a.indexOfChild(view);
            if (iIndexOfChild >= 0) {
                if (c0342k.f9179b.f(iIndexOfChild)) {
                    c0342k.m(view);
                }
                c0327c0.c(iIndexOfChild);
            }
            c0342k.f9181d = 0;
            c0342k.f9182e = null;
        } catch (Throwable th) {
            c0342k.f9181d = 0;
            c0342k.f9182e = null;
            throw th;
        }
    }

    public void removeViewAt(int i5) {
        if (getChildAt(i5) != null) {
            this.mChildHelper.l(i5);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00b6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean requestChildRectangleOnScreen(androidx.recyclerview.widget.RecyclerView r9, android.view.View r10, android.graphics.Rect r11, boolean r12, boolean r13) {
        /*
            r8 = this;
            int r0 = r8.getPaddingLeft()
            int r1 = r8.getPaddingTop()
            int r2 = r8.getWidth()
            int r3 = r8.getPaddingRight()
            int r2 = r2 - r3
            int r3 = r8.getHeight()
            int r4 = r8.getPaddingBottom()
            int r3 = r3 - r4
            int r4 = r10.getLeft()
            int r5 = r11.left
            int r4 = r4 + r5
            int r5 = r10.getScrollX()
            int r4 = r4 - r5
            int r5 = r10.getTop()
            int r6 = r11.top
            int r5 = r5 + r6
            int r10 = r10.getScrollY()
            int r5 = r5 - r10
            int r10 = r11.width()
            int r10 = r10 + r4
            int r11 = r11.height()
            int r11 = r11 + r5
            int r4 = r4 - r0
            r0 = 0
            int r6 = java.lang.Math.min(r0, r4)
            int r5 = r5 - r1
            int r1 = java.lang.Math.min(r0, r5)
            int r10 = r10 - r2
            int r2 = java.lang.Math.max(r0, r10)
            int r11 = r11 - r3
            int r11 = java.lang.Math.max(r0, r11)
            int r3 = r8.getLayoutDirection()
            r7 = 1
            if (r3 != r7) goto L60
            if (r2 == 0) goto L5b
            goto L68
        L5b:
            int r2 = java.lang.Math.max(r6, r10)
            goto L68
        L60:
            if (r6 == 0) goto L63
            goto L67
        L63:
            int r6 = java.lang.Math.min(r4, r2)
        L67:
            r2 = r6
        L68:
            if (r1 == 0) goto L6b
            goto L6f
        L6b:
            int r1 = java.lang.Math.min(r5, r11)
        L6f:
            int[] r10 = new int[]{r2, r1}
            r11 = r10[r0]
            r10 = r10[r7]
            if (r13 == 0) goto Lb6
            android.view.View r13 = r9.getFocusedChild()
            if (r13 != 0) goto L80
            goto Lbb
        L80:
            int r1 = r8.getPaddingLeft()
            int r2 = r8.getPaddingTop()
            int r3 = r8.getWidth()
            int r4 = r8.getPaddingRight()
            int r3 = r3 - r4
            int r4 = r8.getHeight()
            int r5 = r8.getPaddingBottom()
            int r4 = r4 - r5
            androidx.recyclerview.widget.RecyclerView r5 = r8.mRecyclerView
            android.graphics.Rect r5 = r5.mTempRect
            r8.getDecoratedBoundsWithMargins(r13, r5)
            int r8 = r5.left
            int r8 = r8 - r11
            if (r8 >= r3) goto Lbb
            int r8 = r5.right
            int r8 = r8 - r11
            if (r8 <= r1) goto Lbb
            int r8 = r5.top
            int r8 = r8 - r10
            if (r8 >= r4) goto Lbb
            int r8 = r5.bottom
            int r8 = r8 - r10
            if (r8 > r2) goto Lb6
            goto Lbb
        Lb6:
            if (r11 != 0) goto Lbc
            if (r10 == 0) goto Lbb
            goto Lbc
        Lbb:
            return r0
        Lbc:
            if (r12 == 0) goto Lc2
            r9.scrollBy(r11, r10)
            goto Lc5
        Lc2:
            r9.smoothScrollBy(r11, r10)
        Lc5:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.AbstractC0370y0.requestChildRectangleOnScreen(androidx.recyclerview.widget.RecyclerView, android.view.View, android.graphics.Rect, boolean, boolean):boolean");
    }

    public void requestLayout() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.requestLayout();
        }
    }

    public void requestSimpleAnimationsInNextLayout() {
        this.mRequestedSimpleAnimations = true;
    }

    public abstract int scrollHorizontallyBy(int i5, G0 g02, R0 r02);

    public abstract void scrollToPosition(int i5);

    public abstract int scrollVerticallyBy(int i5, G0 g02, R0 r02);

    @Deprecated
    public void setAutoMeasureEnabled(boolean z9) {
        this.mAutoMeasure = z9;
    }

    public void setExactMeasureSpecsFrom(RecyclerView recyclerView) {
        setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), 1073741824));
    }

    public final void setItemPrefetchEnabled(boolean z9) {
        if (z9 != this.mItemPrefetchEnabled) {
            this.mItemPrefetchEnabled = z9;
            this.mPrefetchMaxCountObserved = 0;
            RecyclerView recyclerView = this.mRecyclerView;
            if (recyclerView != null) {
                recyclerView.mRecycler.o();
            }
        }
    }

    public void setMeasureSpecs(int i5, int i7) {
        this.mWidth = View.MeasureSpec.getSize(i5);
        int mode = View.MeasureSpec.getMode(i5);
        this.mWidthMode = mode;
        if (mode == 0 && !RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
            this.mWidth = 0;
        }
        this.mHeight = View.MeasureSpec.getSize(i7);
        int mode2 = View.MeasureSpec.getMode(i7);
        this.mHeightMode = mode2;
        if (mode2 != 0 || RecyclerView.ALLOW_SIZE_IN_UNSPECIFIED_SPEC) {
            return;
        }
        this.mHeight = 0;
    }

    public void setMeasuredDimension(Rect rect, int i5, int i7) {
        setMeasuredDimension(chooseSize(i5, getPaddingRight() + getPaddingLeft() + rect.width(), getMinimumWidth()), chooseSize(i7, getPaddingBottom() + getPaddingTop() + rect.height(), getMinimumHeight()));
    }

    public void setMeasuredDimensionFromChildren(int i5, int i7) {
        int childCount = getChildCount();
        if (childCount == 0) {
            this.mRecyclerView.defaultOnMeasure(i5, i7);
            return;
        }
        int i9 = Integer.MIN_VALUE;
        int i10 = Integer.MAX_VALUE;
        int i11 = Integer.MIN_VALUE;
        int i12 = Integer.MAX_VALUE;
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt = getChildAt(i13);
            Rect rect = this.mRecyclerView.mTempRect;
            getDecoratedBoundsWithMargins(childAt, rect);
            int i14 = rect.left;
            if (i14 < i12) {
                i12 = i14;
            }
            int i15 = rect.right;
            if (i15 > i9) {
                i9 = i15;
            }
            int i16 = rect.top;
            if (i16 < i10) {
                i10 = i16;
            }
            int i17 = rect.bottom;
            if (i17 > i11) {
                i11 = i17;
            }
        }
        this.mRecyclerView.mTempRect.set(i12, i10, i9, i11);
        setMeasuredDimension(this.mRecyclerView.mTempRect, i5, i7);
    }

    public void setMeasurementCacheEnabled(boolean z9) {
        this.mMeasurementCacheEnabled = z9;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        if (recyclerView == null) {
            this.mRecyclerView = null;
            this.mChildHelper = null;
            this.mWidth = 0;
            this.mHeight = 0;
        } else {
            this.mRecyclerView = recyclerView;
            this.mChildHelper = recyclerView.mChildHelper;
            this.mWidth = recyclerView.getWidth();
            this.mHeight = recyclerView.getHeight();
        }
        this.mWidthMode = 1073741824;
        this.mHeightMode = 1073741824;
    }

    public boolean shouldMeasureChild(View view, int i5, int i7, C0372z0 c0372z0) {
        return (!view.isLayoutRequested() && this.mMeasurementCacheEnabled && isMeasurementUpToDate(view.getWidth(), i5, ((ViewGroup.MarginLayoutParams) c0372z0).width) && isMeasurementUpToDate(view.getHeight(), i7, ((ViewGroup.MarginLayoutParams) c0372z0).height)) ? false : true;
    }

    public boolean shouldMeasureTwice() {
        return false;
    }

    public boolean shouldReMeasureChild(View view, int i5, int i7, C0372z0 c0372z0) {
        return (this.mMeasurementCacheEnabled && isMeasurementUpToDate(view.getMeasuredWidth(), i5, ((ViewGroup.MarginLayoutParams) c0372z0).width) && isMeasurementUpToDate(view.getMeasuredHeight(), i7, ((ViewGroup.MarginLayoutParams) c0372z0).height)) ? false : true;
    }

    public abstract void smoothScrollToPosition(RecyclerView recyclerView, R0 r02, int i5);

    @SuppressLint({"UnknownNullness"})
    public void startSmoothScroll(Q0 q02) {
        Q0 q03 = this.mSmoothScroller;
        if (q03 != null && q02 != q03 && q03.isRunning()) {
            this.mSmoothScroller.stop();
        }
        this.mSmoothScroller = q02;
        q02.start(this.mRecyclerView, this);
    }

    public void stopIgnoringView(View view) {
        V0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        childViewHolderInt.stopIgnoring();
        childViewHolderInt.resetInternal();
        childViewHolderInt.addFlags(4);
    }

    public void stopSmoothScroller() {
        Q0 q02 = this.mSmoothScroller;
        if (q02 != null) {
            q02.stop();
        }
    }

    public boolean supportsPredictiveItemAnimations() {
        return false;
    }

    @SuppressLint({"UnknownNullness"})
    public void addDisappearingView(View view, int i5) {
        a(view, i5, true);
    }

    @SuppressLint({"UnknownNullness"})
    public void addView(View view, int i5) {
        a(view, i5, false);
    }

    public void onInitializeAccessibilityEvent(G0 g02, R0 r02, AccessibilityEvent accessibilityEvent) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || accessibilityEvent == null) {
            return;
        }
        boolean z9 = true;
        if (!recyclerView.canScrollVertically(1) && !this.mRecyclerView.canScrollVertically(-1) && !this.mRecyclerView.canScrollHorizontally(-1) && !this.mRecyclerView.canScrollHorizontally(1)) {
            z9 = false;
        }
        accessibilityEvent.setScrollable(z9);
        AbstractC0341j0 abstractC0341j0 = this.mRecyclerView.mAdapter;
        if (abstractC0341j0 != null) {
            if (abstractC0341j0.seslUseCustomAccessibilityPosition()) {
                accessibilityEvent.setItemCount(this.mRecyclerView.mAdapter.seslGetAccessibilityItemCount());
            } else {
                accessibilityEvent.setItemCount(this.mRecyclerView.mAdapter.getItemCount());
            }
        }
    }

    public void onInitializeAccessibilityNodeInfo(G0 g02, R0 r02, L.l lVar) {
        if (this.mRecyclerView.canScrollVertically(-1) || this.mRecyclerView.canScrollHorizontally(-1)) {
            lVar.a(8192);
            lVar.n(true);
        }
        if (this.mRecyclerView.canScrollVertically(1) || this.mRecyclerView.canScrollHorizontally(1)) {
            lVar.a(4096);
            lVar.n(true);
        }
        L.j jVarA = L.j.a(getRowCountForAccessibility(g02, r02), getColumnCountForAccessibility(g02, r02), getSelectionModeForAccessibility(g02, r02), isLayoutHierarchical(g02, r02));
        lVar.getClass();
        lVar.f1793a.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) jVarA.f1791a);
    }

    public void onItemsUpdated(RecyclerView recyclerView, int i5, int i7, Object obj) {
        onItemsUpdated(recyclerView, i5, i7);
    }

    public boolean onRequestChildFocus(RecyclerView recyclerView, R0 r02, View view, View view2) {
        return onRequestChildFocus(recyclerView, view, view2);
    }

    public boolean performAccessibilityAction(G0 g02, R0 r02, int i5, Bundle bundle) {
        int paddingTop;
        int paddingLeft;
        int i7;
        int i9;
        if (this.mRecyclerView == null) {
            return false;
        }
        int height = getHeight();
        int width = getWidth();
        Rect rect = new Rect();
        if (this.mRecyclerView.getMatrix().isIdentity() && this.mRecyclerView.getGlobalVisibleRect(rect)) {
            height = rect.height();
            width = rect.width();
        }
        if (i5 == 4096) {
            paddingTop = this.mRecyclerView.canScrollVertically(1) ? (height - getPaddingTop()) - getPaddingBottom() : 0;
            if (this.mRecyclerView.canScrollHorizontally(1)) {
                paddingLeft = (width - getPaddingLeft()) - getPaddingRight();
                i7 = paddingTop;
                i9 = paddingLeft;
            }
            i7 = paddingTop;
            i9 = 0;
        } else if (i5 != 8192) {
            i9 = 0;
            i7 = 0;
        } else {
            paddingTop = this.mRecyclerView.canScrollVertically(-1) ? -((height - getPaddingTop()) - getPaddingBottom()) : 0;
            if (this.mRecyclerView.canScrollHorizontally(-1)) {
                paddingLeft = -((width - getPaddingLeft()) - getPaddingRight());
                i7 = paddingTop;
                i9 = paddingLeft;
            }
            i7 = paddingTop;
            i9 = 0;
        }
        if (i7 == 0 && i9 == 0) {
            return false;
        }
        this.mRecyclerView.smoothScrollBy(i9, i7, null, Integer.MIN_VALUE, true);
        return true;
    }

    public boolean performAccessibilityActionForItem(View view, int i5, Bundle bundle) {
        RecyclerView recyclerView = this.mRecyclerView;
        return performAccessibilityActionForItem(recyclerView.mRecycler, recyclerView.mState, view, i5, bundle);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x001a  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getChildMeasureSpec(int r4, int r5, int r6, int r7, boolean r8) {
        /*
            int r4 = r4 - r6
            r6 = 0
            int r4 = java.lang.Math.max(r6, r4)
            r0 = -2
            r1 = -1
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = 1073741824(0x40000000, float:2.0)
            if (r8 == 0) goto L1d
            if (r7 < 0) goto L12
        L10:
            r5 = r3
            goto L30
        L12:
            if (r7 != r1) goto L1a
            if (r5 == r2) goto L22
            if (r5 == 0) goto L1a
            if (r5 == r3) goto L22
        L1a:
            r5 = r6
            r7 = r5
            goto L30
        L1d:
            if (r7 < 0) goto L20
            goto L10
        L20:
            if (r7 != r1) goto L24
        L22:
            r7 = r4
            goto L30
        L24:
            if (r7 != r0) goto L1a
            if (r5 == r2) goto L2e
            if (r5 != r3) goto L2b
            goto L2e
        L2b:
            r7 = r4
            r5 = r6
            goto L30
        L2e:
            r7 = r4
            r5 = r2
        L30:
            int r4 = android.view.View.MeasureSpec.makeMeasureSpec(r7, r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.AbstractC0370y0.getChildMeasureSpec(int, int, int, int, boolean):int");
    }

    public void onInitializeAccessibilityNodeInfoForItem(G0 g02, R0 r02, View view, L.l lVar) {
        int position = canScrollVertically() ? getPosition(view) : 0;
        int position2 = canScrollHorizontally() ? getPosition(view) : 0;
        if (this.mRecyclerView.mAdapter.seslUseCustomAccessibilityPosition()) {
            position = this.mRecyclerView.mAdapter.seslGetAccessibilityItemPosition(position);
            position2 = this.mRecyclerView.mAdapter.seslGetAccessibilityItemPosition(position2);
        }
        lVar.l(L.k.a(position, 1, position2, 1, false, false));
    }

    @SuppressLint({"UnknownNullness"})
    public C0372z0 generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new C0372z0(context, attributeSet);
    }

    public void setMeasuredDimension(int i5, int i7) {
        this.mRecyclerView.setMeasuredDimension(i5, i7);
    }

    public void attachView(View view, int i5) {
        attachView(view, i5, (C0372z0) view.getLayoutParams());
    }

    public void attachView(View view) {
        attachView(view, -1);
    }

    public boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z9) {
        return requestChildRectangleOnScreen(recyclerView, view, rect, z9, false);
    }
}
