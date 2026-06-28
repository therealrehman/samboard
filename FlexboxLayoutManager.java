package com.google.android.flexbox;

import A8.l;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.AbstractC0341j0;
import androidx.recyclerview.widget.AbstractC0370y0;
import androidx.recyclerview.widget.C0368x0;
import androidx.recyclerview.widget.C0372z0;
import androidx.recyclerview.widget.G0;
import androidx.recyclerview.widget.P0;
import androidx.recyclerview.widget.R0;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.U;
import androidx.recyclerview.widget.Y;
import androidx.recyclerview.widget.Z;
import com.google.android.flexbox.FlexboxHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FlexboxLayoutManager extends AbstractC0370y0 implements FlexContainer, P0 {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean DEBUG = false;
    private static final String TAG = "FlexboxLayoutManager";
    private static final Rect TEMP_RECT = new Rect();
    private int mAlignItems;
    private AnchorInfo mAnchorInfo;
    private final Context mContext;
    private int mDirtyPosition;
    private int mFlexDirection;
    private List<FlexLine> mFlexLines;
    private FlexboxHelper.FlexLinesResult mFlexLinesResult;
    private int mFlexWrap;
    private final FlexboxHelper mFlexboxHelper;
    private boolean mFromBottomToTop;
    private boolean mIsRtl;
    private int mJustifyContent;
    private int mLastHeight;
    private int mLastWidth;
    private LayoutState mLayoutState;
    private int mMaxLine;
    private Z mOrientationHelper;
    private View mParent;
    private SavedState mPendingSavedState;
    private int mPendingScrollPosition;
    private int mPendingScrollPositionOffset;
    private boolean mRecycleChildrenOnDetach;
    private G0 mRecycler;
    private R0 mState;
    private Z mSubOrientationHelper;
    private SparseArray<View> mViewCache;

    public class AnchorInfo {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private boolean mAssignedFromSavedState;
        private int mCoordinate;
        private int mFlexLinePosition;
        private boolean mLayoutFromEnd;
        private int mPerpendicularCoordinate;
        private int mPosition;
        private boolean mValid;

        private AnchorInfo() {
            this.mPerpendicularCoordinate = 0;
        }

        public static /* synthetic */ int access$2412(AnchorInfo anchorInfo, int i5) {
            int i7 = anchorInfo.mPerpendicularCoordinate + i5;
            anchorInfo.mPerpendicularCoordinate = i7;
            return i7;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void assignCoordinateFromPadding() {
            if (FlexboxLayoutManager.this.isMainAxisDirectionHorizontal() || !FlexboxLayoutManager.this.mIsRtl) {
                this.mCoordinate = this.mLayoutFromEnd ? FlexboxLayoutManager.this.mOrientationHelper.g() : FlexboxLayoutManager.this.mOrientationHelper.k();
            } else {
                this.mCoordinate = this.mLayoutFromEnd ? FlexboxLayoutManager.this.mOrientationHelper.g() : FlexboxLayoutManager.this.getWidth() - FlexboxLayoutManager.this.mOrientationHelper.k();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void assignFromView(View view) {
            Z z9 = FlexboxLayoutManager.this.mFlexWrap == 0 ? FlexboxLayoutManager.this.mSubOrientationHelper : FlexboxLayoutManager.this.mOrientationHelper;
            if (FlexboxLayoutManager.this.isMainAxisDirectionHorizontal() || !FlexboxLayoutManager.this.mIsRtl) {
                if (this.mLayoutFromEnd) {
                    this.mCoordinate = z9.m() + z9.b(view);
                } else {
                    this.mCoordinate = z9.e(view);
                }
            } else if (this.mLayoutFromEnd) {
                this.mCoordinate = z9.m() + z9.e(view);
            } else {
                this.mCoordinate = z9.b(view);
            }
            this.mPosition = FlexboxLayoutManager.this.getPosition(view);
            this.mAssignedFromSavedState = false;
            int[] iArr = FlexboxLayoutManager.this.mFlexboxHelper.mIndexToFlexLine;
            int i5 = this.mPosition;
            if (i5 == -1) {
                i5 = 0;
            }
            int i7 = iArr[i5];
            this.mFlexLinePosition = i7 != -1 ? i7 : 0;
            if (FlexboxLayoutManager.this.mFlexLines.size() > this.mFlexLinePosition) {
                this.mPosition = ((FlexLine) FlexboxLayoutManager.this.mFlexLines.get(this.mFlexLinePosition)).mFirstIndex;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void reset() {
            this.mPosition = -1;
            this.mFlexLinePosition = -1;
            this.mCoordinate = Integer.MIN_VALUE;
            this.mValid = false;
            this.mAssignedFromSavedState = false;
            if (FlexboxLayoutManager.this.isMainAxisDirectionHorizontal()) {
                if (FlexboxLayoutManager.this.mFlexWrap == 0) {
                    this.mLayoutFromEnd = FlexboxLayoutManager.this.mFlexDirection == 1;
                    return;
                } else {
                    this.mLayoutFromEnd = FlexboxLayoutManager.this.mFlexWrap == 2;
                    return;
                }
            }
            if (FlexboxLayoutManager.this.mFlexWrap == 0) {
                this.mLayoutFromEnd = FlexboxLayoutManager.this.mFlexDirection == 3;
            } else {
                this.mLayoutFromEnd = FlexboxLayoutManager.this.mFlexWrap == 2;
            }
        }

        public String toString() {
            return "AnchorInfo{mPosition=" + this.mPosition + ", mFlexLinePosition=" + this.mFlexLinePosition + ", mCoordinate=" + this.mCoordinate + ", mPerpendicularCoordinate=" + this.mPerpendicularCoordinate + ", mLayoutFromEnd=" + this.mLayoutFromEnd + ", mValid=" + this.mValid + ", mAssignedFromSavedState=" + this.mAssignedFromSavedState + '}';
        }
    }

    public static class LayoutState {
        private static final int ITEM_DIRECTION_TAIL = 1;
        private static final int LAYOUT_END = 1;
        private static final int LAYOUT_START = -1;
        private static final int SCROLLING_OFFSET_NaN = Integer.MIN_VALUE;
        private int mAvailable;
        private int mFlexLinePosition;
        private boolean mInfinite;
        private int mItemDirection;
        private int mLastScrollDelta;
        private int mLayoutDirection;
        private int mOffset;
        private int mPosition;
        private int mScrollingOffset;
        private boolean mShouldRecycle;

        private LayoutState() {
            this.mItemDirection = 1;
            this.mLayoutDirection = 1;
        }

        public static /* synthetic */ int access$1012(LayoutState layoutState, int i5) {
            int i7 = layoutState.mOffset + i5;
            layoutState.mOffset = i7;
            return i7;
        }

        public static /* synthetic */ int access$1020(LayoutState layoutState, int i5) {
            int i7 = layoutState.mOffset - i5;
            layoutState.mOffset = i7;
            return i7;
        }

        public static /* synthetic */ int access$1220(LayoutState layoutState, int i5) {
            int i7 = layoutState.mAvailable - i5;
            layoutState.mAvailable = i7;
            return i7;
        }

        public static /* synthetic */ int access$1508(LayoutState layoutState) {
            int i5 = layoutState.mFlexLinePosition;
            layoutState.mFlexLinePosition = i5 + 1;
            return i5;
        }

        public static /* synthetic */ int access$1510(LayoutState layoutState) {
            int i5 = layoutState.mFlexLinePosition;
            layoutState.mFlexLinePosition = i5 - 1;
            return i5;
        }

        public static /* synthetic */ int access$1512(LayoutState layoutState, int i5) {
            int i7 = layoutState.mFlexLinePosition + i5;
            layoutState.mFlexLinePosition = i7;
            return i7;
        }

        public static /* synthetic */ int access$2012(LayoutState layoutState, int i5) {
            int i7 = layoutState.mScrollingOffset + i5;
            layoutState.mScrollingOffset = i7;
            return i7;
        }

        public static /* synthetic */ int access$2212(LayoutState layoutState, int i5) {
            int i7 = layoutState.mPosition + i5;
            layoutState.mPosition = i7;
            return i7;
        }

        public static /* synthetic */ int access$2220(LayoutState layoutState, int i5) {
            int i7 = layoutState.mPosition - i5;
            layoutState.mPosition = i7;
            return i7;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean hasMore(R0 r02, List<FlexLine> list) {
            int i5;
            int i7 = this.mPosition;
            return i7 >= 0 && i7 < r02.b() && (i5 = this.mFlexLinePosition) >= 0 && i5 < list.size();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("LayoutState{mAvailable=");
            sb.append(this.mAvailable);
            sb.append(", mFlexLinePosition=");
            sb.append(this.mFlexLinePosition);
            sb.append(", mPosition=");
            sb.append(this.mPosition);
            sb.append(", mOffset=");
            sb.append(this.mOffset);
            sb.append(", mScrollingOffset=");
            sb.append(this.mScrollingOffset);
            sb.append(", mLastScrollDelta=");
            sb.append(this.mLastScrollDelta);
            sb.append(", mItemDirection=");
            sb.append(this.mItemDirection);
            sb.append(", mLayoutDirection=");
            return l.u(sb, this.mLayoutDirection, '}');
        }
    }

    public FlexboxLayoutManager(Context context) {
        this(context, 0, 1);
    }

    private boolean canViewBeRecycledFromEnd(View view, int i5) {
        return (isMainAxisDirectionHorizontal() || !this.mIsRtl) ? this.mOrientationHelper.e(view) >= this.mOrientationHelper.f() - i5 : this.mOrientationHelper.b(view) <= i5;
    }

    private boolean canViewBeRecycledFromStart(View view, int i5) {
        return (isMainAxisDirectionHorizontal() || !this.mIsRtl) ? this.mOrientationHelper.b(view) <= i5 : this.mOrientationHelper.f() - this.mOrientationHelper.e(view) <= i5;
    }

    private void clearFlexLines() {
        this.mFlexLines.clear();
        this.mAnchorInfo.reset();
        this.mAnchorInfo.mPerpendicularCoordinate = 0;
    }

    private int computeScrollExtent(R0 r02) {
        if (getChildCount() == 0) {
            return 0;
        }
        int iB = r02.b();
        ensureOrientationHelper();
        View viewFindFirstReferenceChild = findFirstReferenceChild(iB);
        View viewFindLastReferenceChild = findLastReferenceChild(iB);
        if (r02.b() == 0 || viewFindFirstReferenceChild == null || viewFindLastReferenceChild == null) {
            return 0;
        }
        return Math.min(this.mOrientationHelper.l(), this.mOrientationHelper.b(viewFindLastReferenceChild) - this.mOrientationHelper.e(viewFindFirstReferenceChild));
    }

    private int computeScrollOffset(R0 r02) {
        if (getChildCount() == 0) {
            return 0;
        }
        int iB = r02.b();
        View viewFindFirstReferenceChild = findFirstReferenceChild(iB);
        View viewFindLastReferenceChild = findLastReferenceChild(iB);
        if (r02.b() != 0 && viewFindFirstReferenceChild != null && viewFindLastReferenceChild != null) {
            int position = getPosition(viewFindFirstReferenceChild);
            int position2 = getPosition(viewFindLastReferenceChild);
            int iAbs = Math.abs(this.mOrientationHelper.b(viewFindLastReferenceChild) - this.mOrientationHelper.e(viewFindFirstReferenceChild));
            int i5 = this.mFlexboxHelper.mIndexToFlexLine[position];
            if (i5 != 0 && i5 != -1) {
                return Math.round((i5 * (iAbs / ((r4[position2] - i5) + 1))) + (this.mOrientationHelper.k() - this.mOrientationHelper.e(viewFindFirstReferenceChild)));
            }
        }
        return 0;
    }

    private int computeScrollRange(R0 r02) {
        if (getChildCount() == 0) {
            return 0;
        }
        int iB = r02.b();
        View viewFindFirstReferenceChild = findFirstReferenceChild(iB);
        View viewFindLastReferenceChild = findLastReferenceChild(iB);
        if (r02.b() == 0 || viewFindFirstReferenceChild == null || viewFindLastReferenceChild == null) {
            return 0;
        }
        int iFindFirstVisibleItemPosition = findFirstVisibleItemPosition();
        return (int) ((Math.abs(this.mOrientationHelper.b(viewFindLastReferenceChild) - this.mOrientationHelper.e(viewFindFirstReferenceChild)) / ((findLastVisibleItemPosition() - iFindFirstVisibleItemPosition) + 1)) * r02.b());
    }

    private void ensureLayoutState() {
        if (this.mLayoutState == null) {
            this.mLayoutState = new LayoutState();
        }
    }

    private void ensureOrientationHelper() {
        if (this.mOrientationHelper != null) {
            return;
        }
        if (isMainAxisDirectionHorizontal()) {
            if (this.mFlexWrap == 0) {
                this.mOrientationHelper = new Y(this, 0);
                this.mSubOrientationHelper = new Y(this, 1);
                return;
            } else {
                this.mOrientationHelper = new Y(this, 1);
                this.mSubOrientationHelper = new Y(this, 0);
                return;
            }
        }
        if (this.mFlexWrap == 0) {
            this.mOrientationHelper = new Y(this, 1);
            this.mSubOrientationHelper = new Y(this, 0);
        } else {
            this.mOrientationHelper = new Y(this, 0);
            this.mSubOrientationHelper = new Y(this, 1);
        }
    }

    private int fill(G0 g02, R0 r02, LayoutState layoutState) {
        if (layoutState.mScrollingOffset != Integer.MIN_VALUE) {
            if (layoutState.mAvailable < 0) {
                LayoutState.access$2012(layoutState, layoutState.mAvailable);
            }
            recycleByLayoutState(g02, layoutState);
        }
        int i5 = layoutState.mAvailable;
        int crossSize = layoutState.mAvailable;
        boolean zIsMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int iLayoutFlexLine = 0;
        while (true) {
            if ((crossSize <= 0 && !this.mLayoutState.mInfinite) || !layoutState.hasMore(r02, this.mFlexLines)) {
                break;
            }
            FlexLine flexLine = this.mFlexLines.get(layoutState.mFlexLinePosition);
            layoutState.mPosition = flexLine.mFirstIndex;
            iLayoutFlexLine += layoutFlexLine(flexLine, layoutState);
            if (zIsMainAxisDirectionHorizontal || !this.mIsRtl) {
                LayoutState.access$1012(layoutState, flexLine.getCrossSize() * layoutState.mLayoutDirection);
            } else {
                LayoutState.access$1020(layoutState, flexLine.getCrossSize() * layoutState.mLayoutDirection);
            }
            crossSize -= flexLine.getCrossSize();
        }
        LayoutState.access$1220(layoutState, iLayoutFlexLine);
        if (layoutState.mScrollingOffset != Integer.MIN_VALUE) {
            LayoutState.access$2012(layoutState, iLayoutFlexLine);
            if (layoutState.mAvailable < 0) {
                LayoutState.access$2012(layoutState, layoutState.mAvailable);
            }
            recycleByLayoutState(g02, layoutState);
        }
        return i5 - layoutState.mAvailable;
    }

    private View findFirstReferenceChild(int i5) {
        View viewFindReferenceChild = findReferenceChild(0, getChildCount(), i5);
        if (viewFindReferenceChild == null) {
            return null;
        }
        int i7 = this.mFlexboxHelper.mIndexToFlexLine[getPosition(viewFindReferenceChild)];
        if (i7 == -1) {
            return null;
        }
        return findFirstReferenceViewInLine(viewFindReferenceChild, this.mFlexLines.get(i7));
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.view.View findFirstReferenceViewInLine(android.view.View r6, com.google.android.flexbox.FlexLine r7) {
        /*
            r5 = this;
            boolean r0 = r5.isMainAxisDirectionHorizontal()
            int r7 = r7.mItemCount
            r1 = 1
        L7:
            if (r1 >= r7) goto L3f
            android.view.View r2 = r5.getChildAt(r1)
            if (r2 == 0) goto L3c
            int r3 = r2.getVisibility()
            r4 = 8
            if (r3 != r4) goto L18
            goto L3c
        L18:
            boolean r3 = r5.mIsRtl
            if (r3 == 0) goto L2d
            if (r0 != 0) goto L2d
            androidx.recyclerview.widget.Z r3 = r5.mOrientationHelper
            int r3 = r3.b(r6)
            androidx.recyclerview.widget.Z r4 = r5.mOrientationHelper
            int r4 = r4.b(r2)
            if (r3 >= r4) goto L3c
            goto L3b
        L2d:
            androidx.recyclerview.widget.Z r3 = r5.mOrientationHelper
            int r3 = r3.e(r6)
            androidx.recyclerview.widget.Z r4 = r5.mOrientationHelper
            int r4 = r4.e(r2)
            if (r3 <= r4) goto L3c
        L3b:
            r6 = r2
        L3c:
            int r1 = r1 + 1
            goto L7
        L3f:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.findFirstReferenceViewInLine(android.view.View, com.google.android.flexbox.FlexLine):android.view.View");
    }

    private View findLastReferenceChild(int i5) {
        View viewFindReferenceChild = findReferenceChild(getChildCount() - 1, -1, i5);
        if (viewFindReferenceChild == null) {
            return null;
        }
        return findLastReferenceViewInLine(viewFindReferenceChild, this.mFlexLines.get(this.mFlexboxHelper.mIndexToFlexLine[getPosition(viewFindReferenceChild)]));
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private android.view.View findLastReferenceViewInLine(android.view.View r6, com.google.android.flexbox.FlexLine r7) {
        /*
            r5 = this;
            boolean r0 = r5.isMainAxisDirectionHorizontal()
            int r1 = r5.getChildCount()
            int r1 = r1 + (-2)
            int r2 = r5.getChildCount()
            int r7 = r7.mItemCount
            int r2 = r2 - r7
            int r2 = r2 + (-1)
        L13:
            if (r1 <= r2) goto L4b
            android.view.View r7 = r5.getChildAt(r1)
            if (r7 == 0) goto L48
            int r3 = r7.getVisibility()
            r4 = 8
            if (r3 != r4) goto L24
            goto L48
        L24:
            boolean r3 = r5.mIsRtl
            if (r3 == 0) goto L39
            if (r0 != 0) goto L39
            androidx.recyclerview.widget.Z r3 = r5.mOrientationHelper
            int r3 = r3.e(r6)
            androidx.recyclerview.widget.Z r4 = r5.mOrientationHelper
            int r4 = r4.e(r7)
            if (r3 <= r4) goto L48
            goto L47
        L39:
            androidx.recyclerview.widget.Z r3 = r5.mOrientationHelper
            int r3 = r3.b(r6)
            androidx.recyclerview.widget.Z r4 = r5.mOrientationHelper
            int r4 = r4.b(r7)
            if (r3 >= r4) goto L48
        L47:
            r6 = r7
        L48:
            int r1 = r1 + (-1)
            goto L13
        L4b:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.findLastReferenceViewInLine(android.view.View, com.google.android.flexbox.FlexLine):android.view.View");
    }

    private View findOneVisibleChild(int i5, int i7, boolean z9) {
        int i9 = i7 > i5 ? 1 : -1;
        while (i5 != i7) {
            View childAt = getChildAt(i5);
            if (isViewVisible(childAt, z9)) {
                return childAt;
            }
            i5 += i9;
        }
        return null;
    }

    private View findReferenceChild(int i5, int i7, int i9) {
        int position;
        ensureOrientationHelper();
        ensureLayoutState();
        int iK = this.mOrientationHelper.k();
        int iG = this.mOrientationHelper.g();
        int i10 = i7 > i5 ? 1 : -1;
        View view = null;
        View view2 = null;
        while (i5 != i7) {
            View childAt = getChildAt(i5);
            if (childAt != null && (position = getPosition(childAt)) >= 0 && position < i9) {
                if (((C0372z0) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else {
                    if (this.mOrientationHelper.e(childAt) >= iK && this.mOrientationHelper.b(childAt) <= iG) {
                        return childAt;
                    }
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i5 += i10;
        }
        return view != null ? view : view2;
    }

    private int fixLayoutEndGap(int i5, G0 g02, R0 r02, boolean z9) {
        int iHandleScrollingMainOrientation;
        int iG;
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            int iG2 = this.mOrientationHelper.g() - i5;
            if (iG2 <= 0) {
                return 0;
            }
            iHandleScrollingMainOrientation = -handleScrollingMainOrientation(-iG2, g02, r02);
        } else {
            int iK = i5 - this.mOrientationHelper.k();
            if (iK <= 0) {
                return 0;
            }
            iHandleScrollingMainOrientation = handleScrollingMainOrientation(iK, g02, r02);
        }
        int i7 = i5 + iHandleScrollingMainOrientation;
        if (!z9 || (iG = this.mOrientationHelper.g() - i7) <= 0) {
            return iHandleScrollingMainOrientation;
        }
        this.mOrientationHelper.p(iG);
        return iG + iHandleScrollingMainOrientation;
    }

    private int fixLayoutStartGap(int i5, G0 g02, R0 r02, boolean z9) {
        int iHandleScrollingMainOrientation;
        int iK;
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            int iK2 = i5 - this.mOrientationHelper.k();
            if (iK2 <= 0) {
                return 0;
            }
            iHandleScrollingMainOrientation = -handleScrollingMainOrientation(iK2, g02, r02);
        } else {
            int iG = this.mOrientationHelper.g() - i5;
            if (iG <= 0) {
                return 0;
            }
            iHandleScrollingMainOrientation = handleScrollingMainOrientation(-iG, g02, r02);
        }
        int i7 = i5 + iHandleScrollingMainOrientation;
        if (!z9 || (iK = i7 - this.mOrientationHelper.k()) <= 0) {
            return iHandleScrollingMainOrientation;
        }
        this.mOrientationHelper.p(-iK);
        return iHandleScrollingMainOrientation - iK;
    }

    private int getChildBottom(View view) {
        return getDecoratedBottom(view) + ((ViewGroup.MarginLayoutParams) ((C0372z0) view.getLayoutParams())).bottomMargin;
    }

    private View getChildClosestToStart() {
        return getChildAt(0);
    }

    private int getChildLeft(View view) {
        return getDecoratedLeft(view) - ((ViewGroup.MarginLayoutParams) ((C0372z0) view.getLayoutParams())).leftMargin;
    }

    private int getChildRight(View view) {
        return getDecoratedRight(view) + ((ViewGroup.MarginLayoutParams) ((C0372z0) view.getLayoutParams())).rightMargin;
    }

    private int getChildTop(View view) {
        return getDecoratedTop(view) - ((ViewGroup.MarginLayoutParams) ((C0372z0) view.getLayoutParams())).topMargin;
    }

    private int handleScrollingMainOrientation(int i5, G0 g02, R0 r02) {
        if (getChildCount() == 0 || i5 == 0) {
            return 0;
        }
        ensureOrientationHelper();
        int i7 = 1;
        this.mLayoutState.mShouldRecycle = true;
        boolean z9 = !isMainAxisDirectionHorizontal() && this.mIsRtl;
        if (!z9 ? i5 <= 0 : i5 >= 0) {
            i7 = -1;
        }
        int iAbs = Math.abs(i5);
        updateLayoutState(i7, iAbs);
        int iFill = this.mLayoutState.mScrollingOffset + fill(g02, r02, this.mLayoutState);
        if (iFill < 0) {
            return 0;
        }
        if (z9) {
            if (iAbs > iFill) {
                i5 = (-i7) * iFill;
            }
        } else if (iAbs > iFill) {
            i5 = i7 * iFill;
        }
        this.mOrientationHelper.p(-i5);
        this.mLayoutState.mLastScrollDelta = i5;
        return i5;
    }

    private int handleScrollingSubOrientation(int i5) {
        int i7;
        if (getChildCount() == 0 || i5 == 0) {
            return 0;
        }
        ensureOrientationHelper();
        boolean zIsMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        View view = this.mParent;
        int width = zIsMainAxisDirectionHorizontal ? view.getWidth() : view.getHeight();
        int width2 = zIsMainAxisDirectionHorizontal ? getWidth() : getHeight();
        if (getLayoutDirection() == 1) {
            int iAbs = Math.abs(i5);
            if (i5 < 0) {
                return -Math.min((width2 + this.mAnchorInfo.mPerpendicularCoordinate) - width, iAbs);
            }
            if (this.mAnchorInfo.mPerpendicularCoordinate + i5 > 0) {
                i7 = this.mAnchorInfo.mPerpendicularCoordinate;
                i5 = -i7;
            }
        } else {
            if (i5 > 0) {
                return Math.min((width2 - this.mAnchorInfo.mPerpendicularCoordinate) - width, i5);
            }
            if (this.mAnchorInfo.mPerpendicularCoordinate + i5 < 0) {
                i7 = this.mAnchorInfo.mPerpendicularCoordinate;
                i5 = -i7;
            }
        }
        return i5;
    }

    private static boolean isMeasurementUpToDate(int i5, int i7, int i9) {
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

    private boolean isViewVisible(View view, boolean z9) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int width = getWidth() - getPaddingRight();
        int height = getHeight() - getPaddingBottom();
        int childLeft = getChildLeft(view);
        int childTop = getChildTop(view);
        int childRight = getChildRight(view);
        int childBottom = getChildBottom(view);
        return z9 ? (paddingLeft <= childLeft && width >= childRight) && (paddingTop <= childTop && height >= childBottom) : (childLeft >= width || childRight >= paddingLeft) && (childTop >= height || childBottom >= paddingTop);
    }

    private int layoutFlexLine(FlexLine flexLine, LayoutState layoutState) {
        return isMainAxisDirectionHorizontal() ? layoutFlexLineMainAxisHorizontal(flexLine, layoutState) : layoutFlexLineMainAxisVertical(flexLine, layoutState);
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00cc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int layoutFlexLineMainAxisHorizontal(com.google.android.flexbox.FlexLine r22, com.google.android.flexbox.FlexboxLayoutManager.LayoutState r23) {
        /*
            Method dump skipped, instruction units count: 420
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.layoutFlexLineMainAxisHorizontal(com.google.android.flexbox.FlexLine, com.google.android.flexbox.FlexboxLayoutManager$LayoutState):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00d2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private int layoutFlexLineMainAxisVertical(com.google.android.flexbox.FlexLine r26, com.google.android.flexbox.FlexboxLayoutManager.LayoutState r27) {
        /*
            Method dump skipped, instruction units count: 532
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.layoutFlexLineMainAxisVertical(com.google.android.flexbox.FlexLine, com.google.android.flexbox.FlexboxLayoutManager$LayoutState):int");
    }

    private void recycleByLayoutState(G0 g02, LayoutState layoutState) {
        if (layoutState.mShouldRecycle) {
            if (layoutState.mLayoutDirection == -1) {
                recycleFlexLinesFromEnd(g02, layoutState);
            } else {
                recycleFlexLinesFromStart(g02, layoutState);
            }
        }
    }

    private void recycleChildren(G0 g02, int i5, int i7) {
        while (i7 >= i5) {
            removeAndRecycleViewAt(i7, g02);
            i7--;
        }
    }

    private void recycleFlexLinesFromEnd(G0 g02, LayoutState layoutState) {
        int childCount;
        int i5;
        View childAt;
        int i7;
        if (layoutState.mScrollingOffset < 0 || (childCount = getChildCount()) == 0 || (childAt = getChildAt(childCount - 1)) == null || (i7 = this.mFlexboxHelper.mIndexToFlexLine[getPosition(childAt)]) == -1) {
            return;
        }
        FlexLine flexLine = this.mFlexLines.get(i7);
        int i9 = i5;
        while (true) {
            if (i9 < 0) {
                break;
            }
            View childAt2 = getChildAt(i9);
            if (childAt2 != null) {
                if (!canViewBeRecycledFromEnd(childAt2, layoutState.mScrollingOffset)) {
                    break;
                }
                if (flexLine.mFirstIndex != getPosition(childAt2)) {
                    continue;
                } else if (i7 <= 0) {
                    childCount = i9;
                    break;
                } else {
                    i7 += layoutState.mLayoutDirection;
                    flexLine = this.mFlexLines.get(i7);
                    childCount = i9;
                }
            }
            i9--;
        }
        recycleChildren(g02, childCount, i5);
    }

    private void recycleFlexLinesFromStart(G0 g02, LayoutState layoutState) {
        int childCount;
        View childAt;
        if (layoutState.mScrollingOffset < 0 || (childCount = getChildCount()) == 0 || (childAt = getChildAt(0)) == null) {
            return;
        }
        int i5 = this.mFlexboxHelper.mIndexToFlexLine[getPosition(childAt)];
        int i7 = -1;
        if (i5 == -1) {
            return;
        }
        FlexLine flexLine = this.mFlexLines.get(i5);
        int i9 = 0;
        while (true) {
            if (i9 >= childCount) {
                break;
            }
            View childAt2 = getChildAt(i9);
            if (childAt2 != null) {
                if (!canViewBeRecycledFromStart(childAt2, layoutState.mScrollingOffset)) {
                    break;
                }
                if (flexLine.mLastIndex != getPosition(childAt2)) {
                    continue;
                } else if (i5 >= this.mFlexLines.size() - 1) {
                    i7 = i9;
                    break;
                } else {
                    i5 += layoutState.mLayoutDirection;
                    flexLine = this.mFlexLines.get(i5);
                    i7 = i9;
                }
            }
            i9++;
        }
        recycleChildren(g02, 0, i7);
    }

    private void resolveInfiniteAmount() {
        int heightMode = isMainAxisDirectionHorizontal() ? getHeightMode() : getWidthMode();
        this.mLayoutState.mInfinite = heightMode == 0 || heightMode == Integer.MIN_VALUE;
    }

    private void resolveLayoutDirection() {
        int layoutDirection = getLayoutDirection();
        int i5 = this.mFlexDirection;
        if (i5 == 0) {
            this.mIsRtl = layoutDirection == 1;
            this.mFromBottomToTop = this.mFlexWrap == 2;
            return;
        }
        if (i5 == 1) {
            this.mIsRtl = layoutDirection != 1;
            this.mFromBottomToTop = this.mFlexWrap == 2;
            return;
        }
        if (i5 == 2) {
            boolean z9 = layoutDirection == 1;
            this.mIsRtl = z9;
            if (this.mFlexWrap == 2) {
                this.mIsRtl = !z9;
            }
            this.mFromBottomToTop = false;
            return;
        }
        if (i5 != 3) {
            this.mIsRtl = false;
            this.mFromBottomToTop = false;
            return;
        }
        boolean z10 = layoutDirection == 1;
        this.mIsRtl = z10;
        if (this.mFlexWrap == 2) {
            this.mIsRtl = !z10;
        }
        this.mFromBottomToTop = true;
    }

    private boolean shouldMeasureChild(View view, int i5, int i7, C0372z0 c0372z0) {
        return (!view.isLayoutRequested() && isMeasurementCacheEnabled() && isMeasurementUpToDate(view.getWidth(), i5, ((ViewGroup.MarginLayoutParams) c0372z0).width) && isMeasurementUpToDate(view.getHeight(), i7, ((ViewGroup.MarginLayoutParams) c0372z0).height)) ? false : true;
    }

    private boolean updateAnchorFromChildren(R0 r02, AnchorInfo anchorInfo) {
        if (getChildCount() == 0) {
            return false;
        }
        View viewFindLastReferenceChild = anchorInfo.mLayoutFromEnd ? findLastReferenceChild(r02.b()) : findFirstReferenceChild(r02.b());
        if (viewFindLastReferenceChild == null) {
            return false;
        }
        anchorInfo.assignFromView(viewFindLastReferenceChild);
        if (r02.f8984g || !supportsPredictiveItemAnimations()) {
            return true;
        }
        if (this.mOrientationHelper.e(viewFindLastReferenceChild) < this.mOrientationHelper.g() && this.mOrientationHelper.b(viewFindLastReferenceChild) >= this.mOrientationHelper.k()) {
            return true;
        }
        anchorInfo.mCoordinate = anchorInfo.mLayoutFromEnd ? this.mOrientationHelper.g() : this.mOrientationHelper.k();
        return true;
    }

    private boolean updateAnchorFromPendingState(R0 r02, AnchorInfo anchorInfo, SavedState savedState) {
        int i5;
        View childAt;
        if (!r02.f8984g && (i5 = this.mPendingScrollPosition) != -1) {
            if (i5 >= 0 && i5 < r02.b()) {
                anchorInfo.mPosition = this.mPendingScrollPosition;
                anchorInfo.mFlexLinePosition = this.mFlexboxHelper.mIndexToFlexLine[anchorInfo.mPosition];
                SavedState savedState2 = this.mPendingSavedState;
                if (savedState2 != null && savedState2.hasValidAnchor(r02.b())) {
                    anchorInfo.mCoordinate = this.mOrientationHelper.k() + savedState.mAnchorOffset;
                    anchorInfo.mAssignedFromSavedState = true;
                    anchorInfo.mFlexLinePosition = -1;
                    return true;
                }
                if (this.mPendingScrollPositionOffset != Integer.MIN_VALUE) {
                    if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
                        anchorInfo.mCoordinate = this.mOrientationHelper.k() + this.mPendingScrollPositionOffset;
                    } else {
                        anchorInfo.mCoordinate = this.mPendingScrollPositionOffset - this.mOrientationHelper.h();
                    }
                    return true;
                }
                View viewFindViewByPosition = findViewByPosition(this.mPendingScrollPosition);
                if (viewFindViewByPosition == null) {
                    if (getChildCount() > 0 && (childAt = getChildAt(0)) != null) {
                        anchorInfo.mLayoutFromEnd = this.mPendingScrollPosition < getPosition(childAt);
                    }
                    anchorInfo.assignCoordinateFromPadding();
                } else {
                    if (this.mOrientationHelper.c(viewFindViewByPosition) > this.mOrientationHelper.l()) {
                        anchorInfo.assignCoordinateFromPadding();
                        return true;
                    }
                    if (this.mOrientationHelper.e(viewFindViewByPosition) - this.mOrientationHelper.k() < 0) {
                        anchorInfo.mCoordinate = this.mOrientationHelper.k();
                        anchorInfo.mLayoutFromEnd = false;
                        return true;
                    }
                    if (this.mOrientationHelper.g() - this.mOrientationHelper.b(viewFindViewByPosition) < 0) {
                        anchorInfo.mCoordinate = this.mOrientationHelper.g();
                        anchorInfo.mLayoutFromEnd = true;
                        return true;
                    }
                    anchorInfo.mCoordinate = anchorInfo.mLayoutFromEnd ? this.mOrientationHelper.m() + this.mOrientationHelper.b(viewFindViewByPosition) : this.mOrientationHelper.e(viewFindViewByPosition);
                }
                return true;
            }
            this.mPendingScrollPosition = -1;
            this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        }
        return false;
    }

    private void updateAnchorInfoForLayout(R0 r02, AnchorInfo anchorInfo) {
        if (updateAnchorFromPendingState(r02, anchorInfo, this.mPendingSavedState) || updateAnchorFromChildren(r02, anchorInfo)) {
            return;
        }
        anchorInfo.assignCoordinateFromPadding();
        anchorInfo.mPosition = 0;
        anchorInfo.mFlexLinePosition = 0;
    }

    private void updateDirtyPosition(int i5) {
        if (i5 >= findLastVisibleItemPosition()) {
            return;
        }
        int childCount = getChildCount();
        this.mFlexboxHelper.ensureMeasureSpecCache(childCount);
        this.mFlexboxHelper.ensureMeasuredSizeCache(childCount);
        this.mFlexboxHelper.ensureIndexToFlexLine(childCount);
        if (i5 >= this.mFlexboxHelper.mIndexToFlexLine.length) {
            return;
        }
        this.mDirtyPosition = i5;
        View childClosestToStart = getChildClosestToStart();
        if (childClosestToStart == null) {
            return;
        }
        this.mPendingScrollPosition = getPosition(childClosestToStart);
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            this.mPendingScrollPositionOffset = this.mOrientationHelper.e(childClosestToStart) - this.mOrientationHelper.k();
        } else {
            this.mPendingScrollPositionOffset = this.mOrientationHelper.h() + this.mOrientationHelper.b(childClosestToStart);
        }
    }

    private void updateFlexLines(int i5) {
        int i7;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
        int width = getWidth();
        int height = getHeight();
        boolean z9 = false;
        if (isMainAxisDirectionHorizontal()) {
            int i9 = this.mLastWidth;
            if (i9 != Integer.MIN_VALUE && i9 != width) {
                z9 = true;
            }
            i7 = this.mLayoutState.mInfinite ? this.mContext.getResources().getDisplayMetrics().heightPixels : this.mLayoutState.mAvailable;
        } else {
            int i10 = this.mLastHeight;
            if (i10 != Integer.MIN_VALUE && i10 != height) {
                z9 = true;
            }
            i7 = this.mLayoutState.mInfinite ? this.mContext.getResources().getDisplayMetrics().widthPixels : this.mLayoutState.mAvailable;
        }
        int i11 = i7;
        this.mLastWidth = width;
        this.mLastHeight = height;
        int i12 = this.mDirtyPosition;
        if (i12 == -1 && (this.mPendingScrollPosition != -1 || z9)) {
            if (this.mAnchorInfo.mLayoutFromEnd) {
                return;
            }
            this.mFlexLines.clear();
            this.mFlexLinesResult.reset();
            if (isMainAxisDirectionHorizontal()) {
                this.mFlexboxHelper.calculateHorizontalFlexLinesToIndex(this.mFlexLinesResult, iMakeMeasureSpec, iMakeMeasureSpec2, i11, this.mAnchorInfo.mPosition, this.mFlexLines);
            } else {
                this.mFlexboxHelper.calculateVerticalFlexLinesToIndex(this.mFlexLinesResult, iMakeMeasureSpec, iMakeMeasureSpec2, i11, this.mAnchorInfo.mPosition, this.mFlexLines);
            }
            this.mFlexLines = this.mFlexLinesResult.mFlexLines;
            this.mFlexboxHelper.determineMainSize(iMakeMeasureSpec, iMakeMeasureSpec2);
            this.mFlexboxHelper.stretchViews();
            AnchorInfo anchorInfo = this.mAnchorInfo;
            anchorInfo.mFlexLinePosition = this.mFlexboxHelper.mIndexToFlexLine[anchorInfo.mPosition];
            this.mLayoutState.mFlexLinePosition = this.mAnchorInfo.mFlexLinePosition;
            return;
        }
        int iMin = i12 != -1 ? Math.min(i12, this.mAnchorInfo.mPosition) : this.mAnchorInfo.mPosition;
        this.mFlexLinesResult.reset();
        if (isMainAxisDirectionHorizontal()) {
            if (this.mFlexLines.size() > 0) {
                this.mFlexboxHelper.clearFlexLines(this.mFlexLines, iMin);
                this.mFlexboxHelper.calculateFlexLines(this.mFlexLinesResult, iMakeMeasureSpec, iMakeMeasureSpec2, i11, iMin, this.mAnchorInfo.mPosition, this.mFlexLines);
            } else {
                this.mFlexboxHelper.ensureIndexToFlexLine(i5);
                this.mFlexboxHelper.calculateHorizontalFlexLines(this.mFlexLinesResult, iMakeMeasureSpec, iMakeMeasureSpec2, i11, 0, this.mFlexLines);
            }
        } else if (this.mFlexLines.size() > 0) {
            this.mFlexboxHelper.clearFlexLines(this.mFlexLines, iMin);
            this.mFlexboxHelper.calculateFlexLines(this.mFlexLinesResult, iMakeMeasureSpec2, iMakeMeasureSpec, i11, iMin, this.mAnchorInfo.mPosition, this.mFlexLines);
        } else {
            this.mFlexboxHelper.ensureIndexToFlexLine(i5);
            this.mFlexboxHelper.calculateVerticalFlexLines(this.mFlexLinesResult, iMakeMeasureSpec, iMakeMeasureSpec2, i11, 0, this.mFlexLines);
        }
        this.mFlexLines = this.mFlexLinesResult.mFlexLines;
        this.mFlexboxHelper.determineMainSize(iMakeMeasureSpec, iMakeMeasureSpec2, iMin);
        this.mFlexboxHelper.stretchViews(iMin);
    }

    private void updateLayoutState(int i5, int i7) {
        this.mLayoutState.mLayoutDirection = i5;
        boolean zIsMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getWidth(), getWidthMode());
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getHeight(), getHeightMode());
        boolean z9 = !zIsMainAxisDirectionHorizontal && this.mIsRtl;
        if (i5 == 1) {
            View childAt = getChildAt(getChildCount() - 1);
            if (childAt == null) {
                return;
            }
            this.mLayoutState.mOffset = this.mOrientationHelper.b(childAt);
            int position = getPosition(childAt);
            View viewFindLastReferenceViewInLine = findLastReferenceViewInLine(childAt, this.mFlexLines.get(this.mFlexboxHelper.mIndexToFlexLine[position]));
            this.mLayoutState.mItemDirection = 1;
            LayoutState layoutState = this.mLayoutState;
            layoutState.mPosition = position + layoutState.mItemDirection;
            if (this.mFlexboxHelper.mIndexToFlexLine.length <= this.mLayoutState.mPosition) {
                this.mLayoutState.mFlexLinePosition = -1;
            } else {
                LayoutState layoutState2 = this.mLayoutState;
                layoutState2.mFlexLinePosition = this.mFlexboxHelper.mIndexToFlexLine[layoutState2.mPosition];
            }
            if (z9) {
                this.mLayoutState.mOffset = this.mOrientationHelper.e(viewFindLastReferenceViewInLine);
                this.mLayoutState.mScrollingOffset = this.mOrientationHelper.k() + (-this.mOrientationHelper.e(viewFindLastReferenceViewInLine));
                LayoutState layoutState3 = this.mLayoutState;
                layoutState3.mScrollingOffset = Math.max(layoutState3.mScrollingOffset, 0);
            } else {
                this.mLayoutState.mOffset = this.mOrientationHelper.b(viewFindLastReferenceViewInLine);
                this.mLayoutState.mScrollingOffset = this.mOrientationHelper.b(viewFindLastReferenceViewInLine) - this.mOrientationHelper.g();
            }
            if ((this.mLayoutState.mFlexLinePosition == -1 || this.mLayoutState.mFlexLinePosition > this.mFlexLines.size() - 1) && this.mLayoutState.mPosition <= getFlexItemCount()) {
                int i9 = i7 - this.mLayoutState.mScrollingOffset;
                this.mFlexLinesResult.reset();
                if (i9 > 0) {
                    if (zIsMainAxisDirectionHorizontal) {
                        this.mFlexboxHelper.calculateHorizontalFlexLines(this.mFlexLinesResult, iMakeMeasureSpec, iMakeMeasureSpec2, i9, this.mLayoutState.mPosition, this.mFlexLines);
                    } else {
                        this.mFlexboxHelper.calculateVerticalFlexLines(this.mFlexLinesResult, iMakeMeasureSpec, iMakeMeasureSpec2, i9, this.mLayoutState.mPosition, this.mFlexLines);
                    }
                    this.mFlexboxHelper.determineMainSize(iMakeMeasureSpec, iMakeMeasureSpec2, this.mLayoutState.mPosition);
                    this.mFlexboxHelper.stretchViews(this.mLayoutState.mPosition);
                }
            }
        } else {
            View childAt2 = getChildAt(0);
            if (childAt2 == null) {
                return;
            }
            this.mLayoutState.mOffset = this.mOrientationHelper.e(childAt2);
            int position2 = getPosition(childAt2);
            View viewFindFirstReferenceViewInLine = findFirstReferenceViewInLine(childAt2, this.mFlexLines.get(this.mFlexboxHelper.mIndexToFlexLine[position2]));
            this.mLayoutState.mItemDirection = 1;
            int i10 = this.mFlexboxHelper.mIndexToFlexLine[position2];
            if (i10 == -1) {
                i10 = 0;
            }
            if (i10 > 0) {
                this.mLayoutState.mPosition = position2 - this.mFlexLines.get(i10 - 1).getItemCount();
            } else {
                this.mLayoutState.mPosition = -1;
            }
            this.mLayoutState.mFlexLinePosition = i10 > 0 ? i10 - 1 : 0;
            if (z9) {
                this.mLayoutState.mOffset = this.mOrientationHelper.b(viewFindFirstReferenceViewInLine);
                this.mLayoutState.mScrollingOffset = this.mOrientationHelper.b(viewFindFirstReferenceViewInLine) - this.mOrientationHelper.g();
                LayoutState layoutState4 = this.mLayoutState;
                layoutState4.mScrollingOffset = Math.max(layoutState4.mScrollingOffset, 0);
            } else {
                this.mLayoutState.mOffset = this.mOrientationHelper.e(viewFindFirstReferenceViewInLine);
                this.mLayoutState.mScrollingOffset = this.mOrientationHelper.k() + (-this.mOrientationHelper.e(viewFindFirstReferenceViewInLine));
            }
        }
        LayoutState layoutState5 = this.mLayoutState;
        layoutState5.mAvailable = i7 - layoutState5.mScrollingOffset;
    }

    private void updateLayoutStateToFillEnd(AnchorInfo anchorInfo, boolean z9, boolean z10) {
        if (z10) {
            resolveInfiniteAmount();
        } else {
            this.mLayoutState.mInfinite = false;
        }
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            this.mLayoutState.mAvailable = this.mOrientationHelper.g() - anchorInfo.mCoordinate;
        } else {
            this.mLayoutState.mAvailable = anchorInfo.mCoordinate - getPaddingRight();
        }
        this.mLayoutState.mPosition = anchorInfo.mPosition;
        this.mLayoutState.mItemDirection = 1;
        this.mLayoutState.mLayoutDirection = 1;
        this.mLayoutState.mOffset = anchorInfo.mCoordinate;
        this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
        this.mLayoutState.mFlexLinePosition = anchorInfo.mFlexLinePosition;
        if (!z9 || this.mFlexLines.size() <= 1 || anchorInfo.mFlexLinePosition < 0 || anchorInfo.mFlexLinePosition >= this.mFlexLines.size() - 1) {
            return;
        }
        FlexLine flexLine = this.mFlexLines.get(anchorInfo.mFlexLinePosition);
        LayoutState.access$1508(this.mLayoutState);
        LayoutState.access$2212(this.mLayoutState, flexLine.getItemCount());
    }

    private void updateLayoutStateToFillStart(AnchorInfo anchorInfo, boolean z9, boolean z10) {
        if (z10) {
            resolveInfiniteAmount();
        } else {
            this.mLayoutState.mInfinite = false;
        }
        if (isMainAxisDirectionHorizontal() || !this.mIsRtl) {
            this.mLayoutState.mAvailable = anchorInfo.mCoordinate - this.mOrientationHelper.k();
        } else {
            this.mLayoutState.mAvailable = (this.mParent.getWidth() - anchorInfo.mCoordinate) - this.mOrientationHelper.k();
        }
        this.mLayoutState.mPosition = anchorInfo.mPosition;
        this.mLayoutState.mItemDirection = 1;
        this.mLayoutState.mLayoutDirection = -1;
        this.mLayoutState.mOffset = anchorInfo.mCoordinate;
        this.mLayoutState.mScrollingOffset = Integer.MIN_VALUE;
        this.mLayoutState.mFlexLinePosition = anchorInfo.mFlexLinePosition;
        if (!z9 || anchorInfo.mFlexLinePosition <= 0 || this.mFlexLines.size() <= anchorInfo.mFlexLinePosition) {
            return;
        }
        FlexLine flexLine = this.mFlexLines.get(anchorInfo.mFlexLinePosition);
        LayoutState.access$1510(this.mLayoutState);
        LayoutState.access$2220(this.mLayoutState, flexLine.getItemCount());
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public boolean canScrollHorizontally() {
        if (this.mFlexWrap == 0) {
            return isMainAxisDirectionHorizontal();
        }
        if (isMainAxisDirectionHorizontal()) {
            int width = getWidth();
            View view = this.mParent;
            if (width <= (view != null ? view.getWidth() : 0)) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public boolean canScrollVertically() {
        if (this.mFlexWrap == 0) {
            return !isMainAxisDirectionHorizontal();
        }
        if (isMainAxisDirectionHorizontal()) {
            return true;
        }
        int height = getHeight();
        View view = this.mParent;
        return height > (view != null ? view.getHeight() : 0);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public boolean checkLayoutParams(C0372z0 c0372z0) {
        return c0372z0 instanceof LayoutParams;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public int computeHorizontalScrollExtent(R0 r02) {
        return computeScrollExtent(r02);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public int computeHorizontalScrollOffset(R0 r02) {
        return computeScrollOffset(r02);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public int computeHorizontalScrollRange(R0 r02) {
        return computeScrollRange(r02);
    }

    @Override // androidx.recyclerview.widget.P0
    public PointF computeScrollVectorForPosition(int i5) {
        View childAt;
        if (getChildCount() == 0 || (childAt = getChildAt(0)) == null) {
            return null;
        }
        int i7 = i5 < getPosition(childAt) ? -1 : 1;
        return isMainAxisDirectionHorizontal() ? new PointF(0.0f, i7) : new PointF(i7, 0.0f);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public int computeVerticalScrollExtent(R0 r02) {
        return computeScrollExtent(r02);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public int computeVerticalScrollOffset(R0 r02) {
        return computeScrollOffset(r02);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public int computeVerticalScrollRange(R0 r02) {
        return computeScrollRange(r02);
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View viewFindOneVisibleChild = findOneVisibleChild(0, getChildCount(), true);
        if (viewFindOneVisibleChild == null) {
            return -1;
        }
        return getPosition(viewFindOneVisibleChild);
    }

    public int findFirstVisibleItemPosition() {
        View viewFindOneVisibleChild = findOneVisibleChild(0, getChildCount(), false);
        if (viewFindOneVisibleChild == null) {
            return -1;
        }
        return getPosition(viewFindOneVisibleChild);
    }

    public int findLastCompletelyVisibleItemPosition() {
        View viewFindOneVisibleChild = findOneVisibleChild(getChildCount() - 1, -1, true);
        if (viewFindOneVisibleChild == null) {
            return -1;
        }
        return getPosition(viewFindOneVisibleChild);
    }

    public int findLastVisibleItemPosition() {
        View viewFindOneVisibleChild = findOneVisibleChild(getChildCount() - 1, -1, false);
        if (viewFindOneVisibleChild == null) {
            return -1;
        }
        return getPosition(viewFindOneVisibleChild);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public C0372z0 generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public C0372z0 generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getAlignContent() {
        return 5;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getAlignItems() {
        return this.mAlignItems;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getChildHeightMeasureSpec(int i5, int i7, int i9) {
        return AbstractC0370y0.getChildMeasureSpec(getHeight(), getHeightMode(), i7, i9, canScrollVertically());
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getChildWidthMeasureSpec(int i5, int i7, int i9) {
        return AbstractC0370y0.getChildMeasureSpec(getWidth(), getWidthMode(), i7, i9, canScrollHorizontally());
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getDecorationLengthCrossAxis(View view) {
        int leftDecorationWidth;
        int rightDecorationWidth;
        if (isMainAxisDirectionHorizontal()) {
            leftDecorationWidth = getTopDecorationHeight(view);
            rightDecorationWidth = getBottomDecorationHeight(view);
        } else {
            leftDecorationWidth = getLeftDecorationWidth(view);
            rightDecorationWidth = getRightDecorationWidth(view);
        }
        return rightDecorationWidth + leftDecorationWidth;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getDecorationLengthMainAxis(View view, int i5, int i7) {
        int topDecorationHeight;
        int bottomDecorationHeight;
        if (isMainAxisDirectionHorizontal()) {
            topDecorationHeight = getLeftDecorationWidth(view);
            bottomDecorationHeight = getRightDecorationWidth(view);
        } else {
            topDecorationHeight = getTopDecorationHeight(view);
            bottomDecorationHeight = getBottomDecorationHeight(view);
        }
        return bottomDecorationHeight + topDecorationHeight;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getFlexDirection() {
        return this.mFlexDirection;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public View getFlexItemAt(int i5) {
        View view = this.mViewCache.get(i5);
        return view != null ? view : this.mRecycler.m(Long.MAX_VALUE, i5).itemView;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getFlexItemCount() {
        return this.mState.b();
    }

    @Override // com.google.android.flexbox.FlexContainer
    public List<FlexLine> getFlexLines() {
        ArrayList arrayList = new ArrayList(this.mFlexLines.size());
        int size = this.mFlexLines.size();
        for (int i5 = 0; i5 < size; i5++) {
            FlexLine flexLine = this.mFlexLines.get(i5);
            if (flexLine.getItemCount() != 0) {
                arrayList.add(flexLine);
            }
        }
        return arrayList;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public List<FlexLine> getFlexLinesInternal() {
        return this.mFlexLines;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getFlexWrap() {
        return this.mFlexWrap;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getJustifyContent() {
        return this.mJustifyContent;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getLargestMainSize() {
        if (this.mFlexLines.size() == 0) {
            return 0;
        }
        int size = this.mFlexLines.size();
        int iMax = Integer.MIN_VALUE;
        for (int i5 = 0; i5 < size; i5++) {
            iMax = Math.max(iMax, this.mFlexLines.get(i5).mMainSize);
        }
        return iMax;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getMaxLine() {
        return this.mMaxLine;
    }

    public int getPositionToFlexLineIndex(int i5) {
        return this.mFlexboxHelper.mIndexToFlexLine[i5];
    }

    public boolean getRecycleChildrenOnDetach() {
        return this.mRecycleChildrenOnDetach;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public View getReorderedFlexItemAt(int i5) {
        return getFlexItemAt(i5);
    }

    @Override // com.google.android.flexbox.FlexContainer
    public int getSumOfCrossSize() {
        int size = this.mFlexLines.size();
        int i5 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            i5 += this.mFlexLines.get(i7).mCrossSize;
        }
        return i5;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public boolean isAutoMeasureEnabled() {
        return true;
    }

    public boolean isLayoutRtl() {
        return this.mIsRtl;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public boolean isMainAxisDirectionHorizontal() {
        int i5 = this.mFlexDirection;
        return i5 == 0 || i5 == 1;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void onAdapterChanged(AbstractC0341j0 abstractC0341j0, AbstractC0341j0 abstractC0341j02) {
        removeAllViews();
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.mParent = (View) recyclerView.getParent();
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void onDetachedFromWindow(RecyclerView recyclerView, G0 g02) {
        onDetachedFromWindow(recyclerView);
        if (this.mRecycleChildrenOnDetach) {
            removeAndRecycleAllViews(g02);
            g02.b();
        }
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void onItemsAdded(RecyclerView recyclerView, int i5, int i7) {
        super.onItemsAdded(recyclerView, i5, i7);
        updateDirtyPosition(i5);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void onItemsMoved(RecyclerView recyclerView, int i5, int i7, int i9) {
        super.onItemsMoved(recyclerView, i5, i7, i9);
        updateDirtyPosition(Math.min(i5, i7));
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void onItemsRemoved(RecyclerView recyclerView, int i5, int i7) {
        super.onItemsRemoved(recyclerView, i5, i7);
        updateDirtyPosition(i5);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void onItemsUpdated(RecyclerView recyclerView, int i5, int i7, Object obj) {
        super.onItemsUpdated(recyclerView, i5, i7, obj);
        updateDirtyPosition(i5);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void onLayoutChildren(G0 g02, R0 r02) {
        int i5;
        int i7;
        this.mRecycler = g02;
        this.mState = r02;
        int iB = r02.b();
        if (iB == 0 && r02.f8984g) {
            return;
        }
        resolveLayoutDirection();
        ensureOrientationHelper();
        ensureLayoutState();
        this.mFlexboxHelper.ensureMeasureSpecCache(iB);
        this.mFlexboxHelper.ensureMeasuredSizeCache(iB);
        this.mFlexboxHelper.ensureIndexToFlexLine(iB);
        this.mLayoutState.mShouldRecycle = false;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null && savedState.hasValidAnchor(iB)) {
            this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
        }
        if (!this.mAnchorInfo.mValid || this.mPendingScrollPosition != -1 || this.mPendingSavedState != null) {
            this.mAnchorInfo.reset();
            updateAnchorInfoForLayout(r02, this.mAnchorInfo);
            this.mAnchorInfo.mValid = true;
        }
        detachAndScrapAttachedViews(g02);
        if (this.mAnchorInfo.mLayoutFromEnd) {
            updateLayoutStateToFillStart(this.mAnchorInfo, false, true);
        } else {
            updateLayoutStateToFillEnd(this.mAnchorInfo, false, true);
        }
        updateFlexLines(iB);
        fill(g02, r02, this.mLayoutState);
        if (this.mAnchorInfo.mLayoutFromEnd) {
            i7 = this.mLayoutState.mOffset;
            updateLayoutStateToFillEnd(this.mAnchorInfo, true, false);
            fill(g02, r02, this.mLayoutState);
            i5 = this.mLayoutState.mOffset;
        } else {
            i5 = this.mLayoutState.mOffset;
            updateLayoutStateToFillStart(this.mAnchorInfo, true, false);
            fill(g02, r02, this.mLayoutState);
            i7 = this.mLayoutState.mOffset;
        }
        if (getChildCount() > 0) {
            if (this.mAnchorInfo.mLayoutFromEnd) {
                fixLayoutStartGap(i7 + fixLayoutEndGap(i5, g02, r02, true), g02, r02, false);
            } else {
                fixLayoutEndGap(i5 + fixLayoutStartGap(i7, g02, r02, true), g02, r02, false);
            }
        }
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void onLayoutCompleted(R0 r02) {
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mDirtyPosition = -1;
        this.mAnchorInfo.reset();
        this.mViewCache.clear();
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void onNewFlexItemAdded(View view, int i5, int i7, FlexLine flexLine) {
        calculateItemDecorationsForChild(view, TEMP_RECT);
        if (isMainAxisDirectionHorizontal()) {
            int rightDecorationWidth = getRightDecorationWidth(view) + getLeftDecorationWidth(view);
            flexLine.mMainSize += rightDecorationWidth;
            flexLine.mDividerLengthInMainSize += rightDecorationWidth;
            return;
        }
        int bottomDecorationHeight = getBottomDecorationHeight(view) + getTopDecorationHeight(view);
        flexLine.mMainSize += bottomDecorationHeight;
        flexLine.mDividerLengthInMainSize += bottomDecorationHeight;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void onNewFlexLineAdded(FlexLine flexLine) {
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.mPendingSavedState = (SavedState) parcelable;
            requestLayout();
        }
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public Parcelable onSaveInstanceState() {
        if (this.mPendingSavedState != null) {
            return new SavedState(this.mPendingSavedState);
        }
        SavedState savedState = new SavedState();
        if (getChildCount() > 0) {
            View childClosestToStart = getChildClosestToStart();
            savedState.mAnchorPosition = getPosition(childClosestToStart);
            savedState.mAnchorOffset = this.mOrientationHelper.e(childClosestToStart) - this.mOrientationHelper.k();
        } else {
            savedState.invalidateAnchor();
        }
        return savedState;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public int scrollHorizontallyBy(int i5, G0 g02, R0 r02) {
        if (!isMainAxisDirectionHorizontal() || this.mFlexWrap == 0) {
            int iHandleScrollingMainOrientation = handleScrollingMainOrientation(i5, g02, r02);
            this.mViewCache.clear();
            return iHandleScrollingMainOrientation;
        }
        int iHandleScrollingSubOrientation = handleScrollingSubOrientation(i5);
        AnchorInfo.access$2412(this.mAnchorInfo, iHandleScrollingSubOrientation);
        this.mSubOrientationHelper.p(-iHandleScrollingSubOrientation);
        return iHandleScrollingSubOrientation;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void scrollToPosition(int i5) {
        this.mPendingScrollPosition = i5;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.invalidateAnchor();
        }
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public int scrollVerticallyBy(int i5, G0 g02, R0 r02) {
        if (isMainAxisDirectionHorizontal() || (this.mFlexWrap == 0 && !isMainAxisDirectionHorizontal())) {
            int iHandleScrollingMainOrientation = handleScrollingMainOrientation(i5, g02, r02);
            this.mViewCache.clear();
            return iHandleScrollingMainOrientation;
        }
        int iHandleScrollingSubOrientation = handleScrollingSubOrientation(i5);
        AnchorInfo.access$2412(this.mAnchorInfo, iHandleScrollingSubOrientation);
        this.mSubOrientationHelper.p(-iHandleScrollingSubOrientation);
        return iHandleScrollingSubOrientation;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setAlignContent(int i5) {
        throw new UnsupportedOperationException("Setting the alignContent in the FlexboxLayoutManager is not supported. Use FlexboxLayout if you need to use this attribute.");
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setAlignItems(int i5) {
        int i7 = this.mAlignItems;
        if (i7 != i5) {
            if (i7 == 4 || i5 == 4) {
                removeAllViews();
                clearFlexLines();
            }
            this.mAlignItems = i5;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setFlexDirection(int i5) {
        if (this.mFlexDirection != i5) {
            removeAllViews();
            this.mFlexDirection = i5;
            this.mOrientationHelper = null;
            this.mSubOrientationHelper = null;
            clearFlexLines();
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setFlexLines(List<FlexLine> list) {
        this.mFlexLines = list;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setFlexWrap(int i5) {
        if (i5 == 2) {
            throw new UnsupportedOperationException("wrap_reverse is not supported in FlexboxLayoutManager");
        }
        int i7 = this.mFlexWrap;
        if (i7 != i5) {
            if (i7 == 0 || i5 == 0) {
                removeAllViews();
                clearFlexLines();
            }
            this.mFlexWrap = i5;
            this.mOrientationHelper = null;
            this.mSubOrientationHelper = null;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setJustifyContent(int i5) {
        if (this.mJustifyContent != i5) {
            this.mJustifyContent = i5;
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void setMaxLine(int i5) {
        if (this.mMaxLine != i5) {
            this.mMaxLine = i5;
            requestLayout();
        }
    }

    public void setRecycleChildrenOnDetach(boolean z9) {
        this.mRecycleChildrenOnDetach = z9;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void smoothScrollToPosition(RecyclerView recyclerView, R0 r02, int i5) {
        U u5 = new U(recyclerView.getContext());
        u5.setTargetPosition(i5);
        startSmoothScroll(u5);
    }

    @Override // com.google.android.flexbox.FlexContainer
    public void updateViewCache(int i5, View view) {
        this.mViewCache.put(i5, view);
    }

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.google.android.flexbox.FlexboxLayoutManager.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i5) {
                return new SavedState[i5];
            }
        };
        private int mAnchorOffset;
        private int mAnchorPosition;

        /* JADX INFO: Access modifiers changed from: private */
        public boolean hasValidAnchor(int i5) {
            int i7 = this.mAnchorPosition;
            return i7 >= 0 && i7 < i5;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void invalidateAnchor() {
            this.mAnchorPosition = -1;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("SavedState{mAnchorPosition=");
            sb.append(this.mAnchorPosition);
            sb.append(", mAnchorOffset=");
            return l.u(sb, this.mAnchorOffset, '}');
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i5) {
            parcel.writeInt(this.mAnchorPosition);
            parcel.writeInt(this.mAnchorOffset);
        }

        public SavedState() {
        }

        private SavedState(Parcel parcel) {
            this.mAnchorPosition = parcel.readInt();
            this.mAnchorOffset = parcel.readInt();
        }

        private SavedState(SavedState savedState) {
            this.mAnchorPosition = savedState.mAnchorPosition;
            this.mAnchorOffset = savedState.mAnchorOffset;
        }
    }

    public FlexboxLayoutManager(Context context, int i5) {
        this(context, i5, 1);
    }

    public FlexboxLayoutManager(Context context, int i5, int i7) {
        this.mMaxLine = -1;
        this.mFlexLines = new ArrayList();
        this.mFlexboxHelper = new FlexboxHelper(this);
        this.mAnchorInfo = new AnchorInfo();
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mLastWidth = Integer.MIN_VALUE;
        this.mLastHeight = Integer.MIN_VALUE;
        this.mViewCache = new SparseArray<>();
        this.mDirtyPosition = -1;
        this.mFlexLinesResult = new FlexboxHelper.FlexLinesResult();
        setFlexDirection(i5);
        setFlexWrap(i7);
        setAlignItems(4);
        this.mContext = context;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void onItemsUpdated(RecyclerView recyclerView, int i5, int i7) {
        super.onItemsUpdated(recyclerView, i5, i7);
        updateDirtyPosition(i5);
    }

    public static class LayoutParams extends C0372z0 implements FlexItem {
        public static final Parcelable.Creator<LayoutParams> CREATOR = new Parcelable.Creator<LayoutParams>() { // from class: com.google.android.flexbox.FlexboxLayoutManager.LayoutParams.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public LayoutParams createFromParcel(Parcel parcel) {
                return new LayoutParams(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public LayoutParams[] newArray(int i5) {
                return new LayoutParams[i5];
            }
        };
        private int mAlignSelf;
        private float mFlexBasisPercent;
        private float mFlexGrow;
        private float mFlexShrink;
        private int mMaxHeight;
        private int mMaxWidth;
        private int mMinHeight;
        private int mMinWidth;
        private boolean mWrapBefore;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMaxWidth = FlexItem.MAX_SIZE;
            this.mMaxHeight = FlexItem.MAX_SIZE;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getAlignSelf() {
            return this.mAlignSelf;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float getFlexBasisPercent() {
            return this.mFlexBasisPercent;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float getFlexGrow() {
            return this.mFlexGrow;
        }

        @Override // com.google.android.flexbox.FlexItem
        public float getFlexShrink() {
            return this.mFlexShrink;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getHeight() {
            return ((ViewGroup.MarginLayoutParams) this).height;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMarginBottom() {
            return ((ViewGroup.MarginLayoutParams) this).bottomMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMarginLeft() {
            return ((ViewGroup.MarginLayoutParams) this).leftMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMarginRight() {
            return ((ViewGroup.MarginLayoutParams) this).rightMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMarginTop() {
            return ((ViewGroup.MarginLayoutParams) this).topMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMaxHeight() {
            return this.mMaxHeight;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMaxWidth() {
            return this.mMaxWidth;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMinHeight() {
            return this.mMinHeight;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getMinWidth() {
            return this.mMinWidth;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getOrder() {
            return 1;
        }

        @Override // com.google.android.flexbox.FlexItem
        public int getWidth() {
            return ((ViewGroup.MarginLayoutParams) this).width;
        }

        @Override // com.google.android.flexbox.FlexItem
        public boolean isWrapBefore() {
            return this.mWrapBefore;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setAlignSelf(int i5) {
            this.mAlignSelf = i5;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setFlexBasisPercent(float f2) {
            this.mFlexBasisPercent = f2;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setFlexGrow(float f2) {
            this.mFlexGrow = f2;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setFlexShrink(float f2) {
            this.mFlexShrink = f2;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setHeight(int i5) {
            ((ViewGroup.MarginLayoutParams) this).height = i5;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setMaxHeight(int i5) {
            this.mMaxHeight = i5;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setMaxWidth(int i5) {
            this.mMaxWidth = i5;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setMinHeight(int i5) {
            this.mMinHeight = i5;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setMinWidth(int i5) {
            this.mMinWidth = i5;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setOrder(int i5) {
            throw new UnsupportedOperationException("Setting the order in the FlexboxLayoutManager is not supported. Use FlexboxLayout if you need to reorder using the attribute.");
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setWidth(int i5) {
            ((ViewGroup.MarginLayoutParams) this).width = i5;
        }

        @Override // com.google.android.flexbox.FlexItem
        public void setWrapBefore(boolean z9) {
            this.mWrapBefore = z9;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i5) {
            parcel.writeFloat(this.mFlexGrow);
            parcel.writeFloat(this.mFlexShrink);
            parcel.writeInt(this.mAlignSelf);
            parcel.writeFloat(this.mFlexBasisPercent);
            parcel.writeInt(this.mMinWidth);
            parcel.writeInt(this.mMinHeight);
            parcel.writeInt(this.mMaxWidth);
            parcel.writeInt(this.mMaxHeight);
            parcel.writeByte(this.mWrapBefore ? (byte) 1 : (byte) 0);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).bottomMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).leftMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).rightMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).topMargin);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).height);
            parcel.writeInt(((ViewGroup.MarginLayoutParams) this).width);
        }

        public LayoutParams(int i5, int i7) {
            super(i5, i7);
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMaxWidth = FlexItem.MAX_SIZE;
            this.mMaxHeight = FlexItem.MAX_SIZE;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMaxWidth = FlexItem.MAX_SIZE;
            this.mMaxHeight = FlexItem.MAX_SIZE;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMaxWidth = FlexItem.MAX_SIZE;
            this.mMaxHeight = FlexItem.MAX_SIZE;
        }

        public LayoutParams(C0372z0 c0372z0) {
            super(c0372z0);
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMaxWidth = FlexItem.MAX_SIZE;
            this.mMaxHeight = FlexItem.MAX_SIZE;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((C0372z0) layoutParams);
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMaxWidth = FlexItem.MAX_SIZE;
            this.mMaxHeight = FlexItem.MAX_SIZE;
            this.mFlexGrow = layoutParams.mFlexGrow;
            this.mFlexShrink = layoutParams.mFlexShrink;
            this.mAlignSelf = layoutParams.mAlignSelf;
            this.mFlexBasisPercent = layoutParams.mFlexBasisPercent;
            this.mMinWidth = layoutParams.mMinWidth;
            this.mMinHeight = layoutParams.mMinHeight;
            this.mMaxWidth = layoutParams.mMaxWidth;
            this.mMaxHeight = layoutParams.mMaxHeight;
            this.mWrapBefore = layoutParams.mWrapBefore;
        }

        public LayoutParams(Parcel parcel) {
            super(-2, -2);
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMaxWidth = FlexItem.MAX_SIZE;
            this.mMaxHeight = FlexItem.MAX_SIZE;
            this.mFlexGrow = parcel.readFloat();
            this.mFlexShrink = parcel.readFloat();
            this.mAlignSelf = parcel.readInt();
            this.mFlexBasisPercent = parcel.readFloat();
            this.mMinWidth = parcel.readInt();
            this.mMinHeight = parcel.readInt();
            this.mMaxWidth = parcel.readInt();
            this.mMaxHeight = parcel.readInt();
            this.mWrapBefore = parcel.readByte() != 0;
            ((ViewGroup.MarginLayoutParams) this).bottomMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).leftMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).rightMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).topMargin = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).height = parcel.readInt();
            ((ViewGroup.MarginLayoutParams) this).width = parcel.readInt();
        }
    }

    public FlexboxLayoutManager(Context context, AttributeSet attributeSet, int i5, int i7) {
        this.mMaxLine = -1;
        this.mFlexLines = new ArrayList();
        this.mFlexboxHelper = new FlexboxHelper(this);
        this.mAnchorInfo = new AnchorInfo();
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mLastWidth = Integer.MIN_VALUE;
        this.mLastHeight = Integer.MIN_VALUE;
        this.mViewCache = new SparseArray<>();
        this.mDirtyPosition = -1;
        this.mFlexLinesResult = new FlexboxHelper.FlexLinesResult();
        C0368x0 properties = AbstractC0370y0.getProperties(context, attributeSet, i5, i7);
        int i9 = properties.f9290a;
        if (i9 != 0) {
            if (i9 == 1) {
                if (properties.f9292c) {
                    setFlexDirection(3);
                } else {
                    setFlexDirection(2);
                }
            }
        } else if (properties.f9292c) {
            setFlexDirection(1);
        } else {
            setFlexDirection(0);
        }
        setFlexWrap(1);
        setAlignItems(4);
        this.mContext = context;
    }
}
