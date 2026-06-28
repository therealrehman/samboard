package com.google.android.flexbox;

import A8.l;
import P.c;
import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
class FlexboxHelper {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int INITIAL_CAPACITY = 10;
    private static final long MEASURE_SPEC_WIDTH_MASK = 4294967295L;
    private boolean[] mChildrenFrozen;
    private final FlexContainer mFlexContainer;
    int[] mIndexToFlexLine;
    long[] mMeasureSpecCache;
    private long[] mMeasuredSizeCache;

    public static class FlexLinesResult {
        int mChildState;
        List<FlexLine> mFlexLines;

        public void reset() {
            this.mFlexLines = null;
            this.mChildState = 0;
        }
    }

    public static class Order implements Comparable<Order> {
        int index;
        int order;

        private Order() {
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("Order{order=");
            sb.append(this.order);
            sb.append(", index=");
            return l.u(sb, this.index, '}');
        }

        @Override // java.lang.Comparable
        public int compareTo(Order order) {
            int i5 = this.order;
            int i7 = order.order;
            return i5 != i7 ? i5 - i7 : this.index - order.index;
        }
    }

    public FlexboxHelper(FlexContainer flexContainer) {
        this.mFlexContainer = flexContainer;
    }

    private void addFlexLine(List<FlexLine> list, FlexLine flexLine, int i5, int i7) {
        flexLine.mSumCrossSizeBefore = i7;
        this.mFlexContainer.onNewFlexLineAdded(flexLine);
        flexLine.mLastIndex = i5;
        list.add(flexLine);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void checkSizeConstraints(android.view.View r7, int r8) {
        /*
            r6 = this;
            android.view.ViewGroup$LayoutParams r0 = r7.getLayoutParams()
            com.google.android.flexbox.FlexItem r0 = (com.google.android.flexbox.FlexItem) r0
            int r1 = r7.getMeasuredWidth()
            int r2 = r7.getMeasuredHeight()
            int r3 = r0.getMinWidth()
            r4 = 1
            if (r1 >= r3) goto L1b
            int r1 = r0.getMinWidth()
        L19:
            r3 = r4
            goto L27
        L1b:
            int r3 = r0.getMaxWidth()
            if (r1 <= r3) goto L26
            int r1 = r0.getMaxWidth()
            goto L19
        L26:
            r3 = 0
        L27:
            int r5 = r0.getMinHeight()
            if (r2 >= r5) goto L32
            int r2 = r0.getMinHeight()
            goto L3e
        L32:
            int r5 = r0.getMaxHeight()
            if (r2 <= r5) goto L3d
            int r2 = r0.getMaxHeight()
            goto L3e
        L3d:
            r4 = r3
        L3e:
            if (r4 == 0) goto L55
            r0 = 1073741824(0x40000000, float:2.0)
            int r1 = android.view.View.MeasureSpec.makeMeasureSpec(r1, r0)
            int r0 = android.view.View.MeasureSpec.makeMeasureSpec(r2, r0)
            r7.measure(r1, r0)
            r6.updateMeasureCache(r8, r1, r0, r7)
            com.google.android.flexbox.FlexContainer r6 = r6.mFlexContainer
            r6.updateViewCache(r8, r7)
        L55:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxHelper.checkSizeConstraints(android.view.View, int):void");
    }

    private List<FlexLine> constructFlexLinesForAlignContentCenter(List<FlexLine> list, int i5, int i7) {
        int i9 = (i5 - i7) / 2;
        ArrayList arrayList = new ArrayList();
        FlexLine flexLine = new FlexLine();
        flexLine.mCrossSize = i9;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            if (i10 == 0) {
                arrayList.add(flexLine);
            }
            arrayList.add(list.get(i10));
            if (i10 == list.size() - 1) {
                arrayList.add(flexLine);
            }
        }
        return arrayList;
    }

    private List<Order> createOrders(int i5) {
        ArrayList arrayList = new ArrayList(i5);
        for (int i7 = 0; i7 < i5; i7++) {
            FlexItem flexItem = (FlexItem) this.mFlexContainer.getFlexItemAt(i7).getLayoutParams();
            Order order = new Order();
            order.order = flexItem.getOrder();
            order.index = i7;
            arrayList.add(order);
        }
        return arrayList;
    }

    private void ensureChildrenFrozen(int i5) {
        boolean[] zArr = this.mChildrenFrozen;
        if (zArr == null) {
            this.mChildrenFrozen = new boolean[Math.max(i5, 10)];
        } else if (zArr.length < i5) {
            this.mChildrenFrozen = new boolean[Math.max(zArr.length * 2, i5)];
        } else {
            Arrays.fill(zArr, false);
        }
    }

    private void evaluateMinimumSizeForCompoundButton(CompoundButton compoundButton) {
        FlexItem flexItem = (FlexItem) compoundButton.getLayoutParams();
        int minWidth = flexItem.getMinWidth();
        int minHeight = flexItem.getMinHeight();
        Drawable drawableA = c.a(compoundButton);
        int minimumWidth = drawableA == null ? 0 : drawableA.getMinimumWidth();
        int minimumHeight = drawableA != null ? drawableA.getMinimumHeight() : 0;
        if (minWidth == -1) {
            minWidth = minimumWidth;
        }
        flexItem.setMinWidth(minWidth);
        if (minHeight == -1) {
            minHeight = minimumHeight;
        }
        flexItem.setMinHeight(minHeight);
    }

    private void expandFlexItems(int i5, int i7, FlexLine flexLine, int i9, int i10, boolean z9) {
        int i11;
        int i12;
        int iMax;
        double d8;
        double d10;
        float f2 = flexLine.mTotalFlexGrow;
        float f7 = 0.0f;
        if (f2 <= 0.0f || i9 < (i11 = flexLine.mMainSize)) {
            return;
        }
        float f9 = (i9 - i11) / f2;
        flexLine.mMainSize = i10 + flexLine.mDividerLengthInMainSize;
        if (!z9) {
            flexLine.mCrossSize = Integer.MIN_VALUE;
        }
        int i13 = 0;
        boolean z10 = false;
        int i14 = 0;
        float f10 = 0.0f;
        while (i13 < flexLine.mItemCount) {
            int i15 = flexLine.mFirstIndex + i13;
            View reorderedFlexItemAt = this.mFlexContainer.getReorderedFlexItemAt(i15);
            if (reorderedFlexItemAt == null || reorderedFlexItemAt.getVisibility() == 8) {
                i12 = i11;
            } else {
                FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                int flexDirection = this.mFlexContainer.getFlexDirection();
                if (flexDirection == 0 || flexDirection == 1) {
                    i12 = i11;
                    int measuredWidth = reorderedFlexItemAt.getMeasuredWidth();
                    long[] jArr = this.mMeasuredSizeCache;
                    if (jArr != null) {
                        measuredWidth = extractLowerInt(jArr[i15]);
                    }
                    int measuredHeight = reorderedFlexItemAt.getMeasuredHeight();
                    long[] jArr2 = this.mMeasuredSizeCache;
                    if (jArr2 != null) {
                        measuredHeight = extractHigherInt(jArr2[i15]);
                    }
                    if (!this.mChildrenFrozen[i15] && flexItem.getFlexGrow() > 0.0f) {
                        float flexGrow = (flexItem.getFlexGrow() * f9) + measuredWidth;
                        if (i13 == flexLine.mItemCount - 1) {
                            flexGrow += f10;
                            f10 = 0.0f;
                        }
                        int iRound = Math.round(flexGrow);
                        if (iRound > flexItem.getMaxWidth()) {
                            iRound = flexItem.getMaxWidth();
                            this.mChildrenFrozen[i15] = true;
                            flexLine.mTotalFlexGrow -= flexItem.getFlexGrow();
                            z10 = true;
                        } else {
                            float f11 = (flexGrow - iRound) + f10;
                            double d11 = f11;
                            if (d11 > 1.0d) {
                                iRound++;
                                d8 = d11 - 1.0d;
                            } else if (d11 < -1.0d) {
                                iRound--;
                                d8 = d11 + 1.0d;
                            } else {
                                f10 = f11;
                            }
                            f10 = (float) d8;
                        }
                        int childHeightMeasureSpecInternal = getChildHeightMeasureSpecInternal(i7, flexItem, flexLine.mSumCrossSizeBefore);
                        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iRound, 1073741824);
                        reorderedFlexItemAt.measure(iMakeMeasureSpec, childHeightMeasureSpecInternal);
                        int measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                        int measuredHeight2 = reorderedFlexItemAt.getMeasuredHeight();
                        updateMeasureCache(i15, iMakeMeasureSpec, childHeightMeasureSpecInternal, reorderedFlexItemAt);
                        this.mFlexContainer.updateViewCache(i15, reorderedFlexItemAt);
                        measuredWidth = measuredWidth2;
                        measuredHeight = measuredHeight2;
                    }
                    int iMax2 = Math.max(i14, measuredHeight + flexItem.getMarginTop() + flexItem.getMarginBottom() + this.mFlexContainer.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                    flexLine.mMainSize = measuredWidth + flexItem.getMarginLeft() + flexItem.getMarginRight() + flexLine.mMainSize;
                    iMax = iMax2;
                } else {
                    int measuredHeight3 = reorderedFlexItemAt.getMeasuredHeight();
                    long[] jArr3 = this.mMeasuredSizeCache;
                    if (jArr3 != null) {
                        measuredHeight3 = extractHigherInt(jArr3[i15]);
                    }
                    int measuredWidth3 = reorderedFlexItemAt.getMeasuredWidth();
                    long[] jArr4 = this.mMeasuredSizeCache;
                    if (jArr4 != null) {
                        measuredWidth3 = extractLowerInt(jArr4[i15]);
                    }
                    if (this.mChildrenFrozen[i15] || flexItem.getFlexGrow() <= f7) {
                        i12 = i11;
                    } else {
                        float flexGrow2 = (flexItem.getFlexGrow() * f9) + measuredHeight3;
                        if (i13 == flexLine.mItemCount - 1) {
                            flexGrow2 += f10;
                            f10 = f7;
                        }
                        int iRound2 = Math.round(flexGrow2);
                        if (iRound2 > flexItem.getMaxHeight()) {
                            iRound2 = flexItem.getMaxHeight();
                            this.mChildrenFrozen[i15] = true;
                            flexLine.mTotalFlexGrow -= flexItem.getFlexGrow();
                            i12 = i11;
                            z10 = true;
                        } else {
                            float f12 = (flexGrow2 - iRound2) + f10;
                            i12 = i11;
                            double d12 = f12;
                            if (d12 > 1.0d) {
                                iRound2++;
                                d10 = d12 - 1.0d;
                            } else if (d12 < -1.0d) {
                                iRound2--;
                                d10 = d12 + 1.0d;
                            } else {
                                f10 = f12;
                            }
                            f10 = (float) d10;
                        }
                        int childWidthMeasureSpecInternal = getChildWidthMeasureSpecInternal(i5, flexItem, flexLine.mSumCrossSizeBefore);
                        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iRound2, 1073741824);
                        reorderedFlexItemAt.measure(childWidthMeasureSpecInternal, iMakeMeasureSpec2);
                        measuredWidth3 = reorderedFlexItemAt.getMeasuredWidth();
                        int measuredHeight4 = reorderedFlexItemAt.getMeasuredHeight();
                        updateMeasureCache(i15, childWidthMeasureSpecInternal, iMakeMeasureSpec2, reorderedFlexItemAt);
                        this.mFlexContainer.updateViewCache(i15, reorderedFlexItemAt);
                        measuredHeight3 = measuredHeight4;
                    }
                    iMax = Math.max(i14, measuredWidth3 + flexItem.getMarginLeft() + flexItem.getMarginRight() + this.mFlexContainer.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                    flexLine.mMainSize = measuredHeight3 + flexItem.getMarginTop() + flexItem.getMarginBottom() + flexLine.mMainSize;
                }
                flexLine.mCrossSize = Math.max(flexLine.mCrossSize, iMax);
                i14 = iMax;
            }
            i13++;
            i11 = i12;
            f7 = 0.0f;
        }
        int i16 = i11;
        if (!z10 || i16 == flexLine.mMainSize) {
            return;
        }
        expandFlexItems(i5, i7, flexLine, i9, i10, true);
    }

    private int getChildHeightMeasureSpecInternal(int i5, FlexItem flexItem, int i7) {
        FlexContainer flexContainer = this.mFlexContainer;
        int childHeightMeasureSpec = flexContainer.getChildHeightMeasureSpec(i5, flexContainer.getPaddingTop() + this.mFlexContainer.getPaddingBottom() + flexItem.getMarginTop() + flexItem.getMarginBottom() + i7, flexItem.getHeight());
        int size = View.MeasureSpec.getSize(childHeightMeasureSpec);
        return size > flexItem.getMaxHeight() ? View.MeasureSpec.makeMeasureSpec(flexItem.getMaxHeight(), View.MeasureSpec.getMode(childHeightMeasureSpec)) : size < flexItem.getMinHeight() ? View.MeasureSpec.makeMeasureSpec(flexItem.getMinHeight(), View.MeasureSpec.getMode(childHeightMeasureSpec)) : childHeightMeasureSpec;
    }

    private int getChildWidthMeasureSpecInternal(int i5, FlexItem flexItem, int i7) {
        FlexContainer flexContainer = this.mFlexContainer;
        int childWidthMeasureSpec = flexContainer.getChildWidthMeasureSpec(i5, flexContainer.getPaddingLeft() + this.mFlexContainer.getPaddingRight() + flexItem.getMarginLeft() + flexItem.getMarginRight() + i7, flexItem.getWidth());
        int size = View.MeasureSpec.getSize(childWidthMeasureSpec);
        return size > flexItem.getMaxWidth() ? View.MeasureSpec.makeMeasureSpec(flexItem.getMaxWidth(), View.MeasureSpec.getMode(childWidthMeasureSpec)) : size < flexItem.getMinWidth() ? View.MeasureSpec.makeMeasureSpec(flexItem.getMinWidth(), View.MeasureSpec.getMode(childWidthMeasureSpec)) : childWidthMeasureSpec;
    }

    private int getFlexItemMarginEndCross(FlexItem flexItem, boolean z9) {
        return z9 ? flexItem.getMarginBottom() : flexItem.getMarginRight();
    }

    private int getFlexItemMarginEndMain(FlexItem flexItem, boolean z9) {
        return z9 ? flexItem.getMarginRight() : flexItem.getMarginBottom();
    }

    private int getFlexItemMarginStartCross(FlexItem flexItem, boolean z9) {
        return z9 ? flexItem.getMarginTop() : flexItem.getMarginLeft();
    }

    private int getFlexItemMarginStartMain(FlexItem flexItem, boolean z9) {
        return z9 ? flexItem.getMarginLeft() : flexItem.getMarginTop();
    }

    private int getFlexItemSizeCross(FlexItem flexItem, boolean z9) {
        return z9 ? flexItem.getHeight() : flexItem.getWidth();
    }

    private int getFlexItemSizeMain(FlexItem flexItem, boolean z9) {
        return z9 ? flexItem.getWidth() : flexItem.getHeight();
    }

    private int getPaddingEndCross(boolean z9) {
        return z9 ? this.mFlexContainer.getPaddingBottom() : this.mFlexContainer.getPaddingEnd();
    }

    private int getPaddingEndMain(boolean z9) {
        return z9 ? this.mFlexContainer.getPaddingEnd() : this.mFlexContainer.getPaddingBottom();
    }

    private int getPaddingStartCross(boolean z9) {
        return z9 ? this.mFlexContainer.getPaddingTop() : this.mFlexContainer.getPaddingStart();
    }

    private int getPaddingStartMain(boolean z9) {
        return z9 ? this.mFlexContainer.getPaddingStart() : this.mFlexContainer.getPaddingTop();
    }

    private int getViewMeasuredSizeCross(View view, boolean z9) {
        return z9 ? view.getMeasuredHeight() : view.getMeasuredWidth();
    }

    private int getViewMeasuredSizeMain(View view, boolean z9) {
        return z9 ? view.getMeasuredWidth() : view.getMeasuredHeight();
    }

    private boolean isLastFlexItem(int i5, int i7, FlexLine flexLine) {
        return i5 == i7 - 1 && flexLine.getItemCountNotGone() != 0;
    }

    private boolean isWrapRequired(View view, int i5, int i7, int i9, int i10, FlexItem flexItem, int i11, int i12, int i13) {
        if (this.mFlexContainer.getFlexWrap() == 0) {
            return false;
        }
        if (flexItem.isWrapBefore()) {
            return true;
        }
        if (i5 == 0) {
            return false;
        }
        int maxLine = this.mFlexContainer.getMaxLine();
        if (maxLine != -1 && maxLine <= i13 + 1) {
            return false;
        }
        int decorationLengthMainAxis = this.mFlexContainer.getDecorationLengthMainAxis(view, i11, i12);
        if (decorationLengthMainAxis > 0) {
            i10 += decorationLengthMainAxis;
        }
        return i7 < i9 + i10;
    }

    private void shrinkFlexItems(int i5, int i7, FlexLine flexLine, int i9, int i10, boolean z9) {
        int iMax;
        int i11 = flexLine.mMainSize;
        float f2 = flexLine.mTotalFlexShrink;
        float f7 = 0.0f;
        if (f2 <= 0.0f || i9 > i11) {
            return;
        }
        float f9 = (i11 - i9) / f2;
        flexLine.mMainSize = i10 + flexLine.mDividerLengthInMainSize;
        if (!z9) {
            flexLine.mCrossSize = Integer.MIN_VALUE;
        }
        int i12 = 0;
        boolean z10 = false;
        int i13 = 0;
        float f10 = 0.0f;
        while (i12 < flexLine.mItemCount) {
            int i14 = flexLine.mFirstIndex + i12;
            View reorderedFlexItemAt = this.mFlexContainer.getReorderedFlexItemAt(i14);
            if (reorderedFlexItemAt != null && reorderedFlexItemAt.getVisibility() != 8) {
                FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                int flexDirection = this.mFlexContainer.getFlexDirection();
                if (flexDirection == 0 || flexDirection == 1) {
                    int measuredWidth = reorderedFlexItemAt.getMeasuredWidth();
                    long[] jArr = this.mMeasuredSizeCache;
                    if (jArr != null) {
                        measuredWidth = extractLowerInt(jArr[i14]);
                    }
                    int measuredHeight = reorderedFlexItemAt.getMeasuredHeight();
                    long[] jArr2 = this.mMeasuredSizeCache;
                    if (jArr2 != null) {
                        measuredHeight = extractHigherInt(jArr2[i14]);
                    }
                    if (!this.mChildrenFrozen[i14] && flexItem.getFlexShrink() > 0.0f) {
                        float flexShrink = measuredWidth - (flexItem.getFlexShrink() * f9);
                        if (i12 == flexLine.mItemCount - 1) {
                            flexShrink += f10;
                            f10 = 0.0f;
                        }
                        int iRound = Math.round(flexShrink);
                        if (iRound < flexItem.getMinWidth()) {
                            iRound = flexItem.getMinWidth();
                            this.mChildrenFrozen[i14] = true;
                            flexLine.mTotalFlexShrink -= flexItem.getFlexShrink();
                            z10 = true;
                        } else {
                            float f11 = (flexShrink - iRound) + f10;
                            double d8 = f11;
                            if (d8 > 1.0d) {
                                iRound++;
                                f11 -= 1.0f;
                            } else if (d8 < -1.0d) {
                                iRound--;
                                f11 += 1.0f;
                            }
                            f10 = f11;
                        }
                        int childHeightMeasureSpecInternal = getChildHeightMeasureSpecInternal(i7, flexItem, flexLine.mSumCrossSizeBefore);
                        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iRound, 1073741824);
                        reorderedFlexItemAt.measure(iMakeMeasureSpec, childHeightMeasureSpecInternal);
                        int measuredWidth2 = reorderedFlexItemAt.getMeasuredWidth();
                        int measuredHeight2 = reorderedFlexItemAt.getMeasuredHeight();
                        updateMeasureCache(i14, iMakeMeasureSpec, childHeightMeasureSpecInternal, reorderedFlexItemAt);
                        this.mFlexContainer.updateViewCache(i14, reorderedFlexItemAt);
                        measuredWidth = measuredWidth2;
                        measuredHeight = measuredHeight2;
                    }
                    int iMax2 = Math.max(i13, measuredHeight + flexItem.getMarginTop() + flexItem.getMarginBottom() + this.mFlexContainer.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                    flexLine.mMainSize = measuredWidth + flexItem.getMarginLeft() + flexItem.getMarginRight() + flexLine.mMainSize;
                    iMax = iMax2;
                } else {
                    int measuredHeight3 = reorderedFlexItemAt.getMeasuredHeight();
                    long[] jArr3 = this.mMeasuredSizeCache;
                    if (jArr3 != null) {
                        measuredHeight3 = extractHigherInt(jArr3[i14]);
                    }
                    int measuredWidth3 = reorderedFlexItemAt.getMeasuredWidth();
                    long[] jArr4 = this.mMeasuredSizeCache;
                    if (jArr4 != null) {
                        measuredWidth3 = extractLowerInt(jArr4[i14]);
                    }
                    if (!this.mChildrenFrozen[i14] && flexItem.getFlexShrink() > f7) {
                        float flexShrink2 = measuredHeight3 - (flexItem.getFlexShrink() * f9);
                        if (i12 == flexLine.mItemCount - 1) {
                            flexShrink2 += f10;
                            f10 = f7;
                        }
                        int iRound2 = Math.round(flexShrink2);
                        if (iRound2 < flexItem.getMinHeight()) {
                            iRound2 = flexItem.getMinHeight();
                            this.mChildrenFrozen[i14] = true;
                            flexLine.mTotalFlexShrink -= flexItem.getFlexShrink();
                            z10 = true;
                        } else {
                            float f12 = (flexShrink2 - iRound2) + f10;
                            double d10 = f12;
                            if (d10 > 1.0d) {
                                iRound2++;
                                f12 -= 1.0f;
                            } else if (d10 < -1.0d) {
                                iRound2--;
                                f12 += 1.0f;
                            }
                            f10 = f12;
                        }
                        int childWidthMeasureSpecInternal = getChildWidthMeasureSpecInternal(i5, flexItem, flexLine.mSumCrossSizeBefore);
                        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iRound2, 1073741824);
                        reorderedFlexItemAt.measure(childWidthMeasureSpecInternal, iMakeMeasureSpec2);
                        measuredWidth3 = reorderedFlexItemAt.getMeasuredWidth();
                        int measuredHeight4 = reorderedFlexItemAt.getMeasuredHeight();
                        updateMeasureCache(i14, childWidthMeasureSpecInternal, iMakeMeasureSpec2, reorderedFlexItemAt);
                        this.mFlexContainer.updateViewCache(i14, reorderedFlexItemAt);
                        measuredHeight3 = measuredHeight4;
                    }
                    iMax = Math.max(i13, measuredWidth3 + flexItem.getMarginLeft() + flexItem.getMarginRight() + this.mFlexContainer.getDecorationLengthCrossAxis(reorderedFlexItemAt));
                    flexLine.mMainSize = measuredHeight3 + flexItem.getMarginTop() + flexItem.getMarginBottom() + flexLine.mMainSize;
                }
                flexLine.mCrossSize = Math.max(flexLine.mCrossSize, iMax);
                i13 = iMax;
            }
            i12++;
            f7 = 0.0f;
        }
        if (!z10 || i11 == flexLine.mMainSize) {
            return;
        }
        shrinkFlexItems(i5, i7, flexLine, i9, i10, true);
    }

    private int[] sortOrdersIntoReorderedIndices(int i5, List<Order> list, SparseIntArray sparseIntArray) {
        Collections.sort(list);
        sparseIntArray.clear();
        int[] iArr = new int[i5];
        int i7 = 0;
        for (Order order : list) {
            int i9 = order.index;
            iArr[i7] = i9;
            sparseIntArray.append(i9, order.order);
            i7++;
        }
        return iArr;
    }

    private void stretchViewHorizontally(View view, int i5, int i7) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int iMin = Math.min(Math.max(((i5 - flexItem.getMarginLeft()) - flexItem.getMarginRight()) - this.mFlexContainer.getDecorationLengthCrossAxis(view), flexItem.getMinWidth()), flexItem.getMaxWidth());
        long[] jArr = this.mMeasuredSizeCache;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(jArr != null ? extractHigherInt(jArr[i7]) : view.getMeasuredHeight(), 1073741824);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iMin, 1073741824);
        view.measure(iMakeMeasureSpec2, iMakeMeasureSpec);
        updateMeasureCache(i7, iMakeMeasureSpec2, iMakeMeasureSpec, view);
        this.mFlexContainer.updateViewCache(i7, view);
    }

    private void stretchViewVertically(View view, int i5, int i7) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int iMin = Math.min(Math.max(((i5 - flexItem.getMarginTop()) - flexItem.getMarginBottom()) - this.mFlexContainer.getDecorationLengthCrossAxis(view), flexItem.getMinHeight()), flexItem.getMaxHeight());
        long[] jArr = this.mMeasuredSizeCache;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(jArr != null ? extractLowerInt(jArr[i7]) : view.getMeasuredWidth(), 1073741824);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(iMin, 1073741824);
        view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
        updateMeasureCache(i7, iMakeMeasureSpec, iMakeMeasureSpec2, view);
        this.mFlexContainer.updateViewCache(i7, view);
    }

    private void updateMeasureCache(int i5, int i7, int i9, View view) {
        long[] jArr = this.mMeasureSpecCache;
        if (jArr != null) {
            jArr[i5] = makeCombinedLong(i7, i9);
        }
        long[] jArr2 = this.mMeasuredSizeCache;
        if (jArr2 != null) {
            jArr2[i5] = makeCombinedLong(view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void calculateFlexLines(FlexLinesResult flexLinesResult, int i5, int i7, int i9, int i10, int i11, List<FlexLine> list) {
        int i12;
        FlexLinesResult flexLinesResult2;
        int i13;
        int i14;
        int i15;
        List<FlexLine> list2;
        int i16;
        View view;
        int i17;
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        int i25 = i5;
        int i26 = i7;
        int i27 = i11;
        boolean zIsMainAxisDirectionHorizontal = this.mFlexContainer.isMainAxisDirectionHorizontal();
        int mode = View.MeasureSpec.getMode(i5);
        int size = View.MeasureSpec.getSize(i5);
        List<FlexLine> arrayList = list == null ? new ArrayList() : list;
        flexLinesResult.mFlexLines = arrayList;
        int i28 = i27 == -1 ? 1 : 0;
        int paddingStartMain = getPaddingStartMain(zIsMainAxisDirectionHorizontal);
        int paddingEndMain = getPaddingEndMain(zIsMainAxisDirectionHorizontal);
        int paddingStartCross = getPaddingStartCross(zIsMainAxisDirectionHorizontal);
        int paddingEndCross = getPaddingEndCross(zIsMainAxisDirectionHorizontal);
        FlexLine flexLine = new FlexLine();
        int i29 = i10;
        flexLine.mFirstIndex = i29;
        int i30 = paddingEndMain + paddingStartMain;
        flexLine.mMainSize = i30;
        int flexItemCount = this.mFlexContainer.getFlexItemCount();
        int i31 = i28;
        int i32 = Integer.MIN_VALUE;
        int i33 = 0;
        int iCombineMeasuredStates = 0;
        int i34 = 0;
        while (true) {
            if (i29 >= flexItemCount) {
                i12 = iCombineMeasuredStates;
                flexLinesResult2 = flexLinesResult;
                break;
            }
            View reorderedFlexItemAt = this.mFlexContainer.getReorderedFlexItemAt(i29);
            if (reorderedFlexItemAt != null) {
                if (reorderedFlexItemAt.getVisibility() != 8) {
                    if (reorderedFlexItemAt instanceof CompoundButton) {
                        evaluateMinimumSizeForCompoundButton((CompoundButton) reorderedFlexItemAt);
                    }
                    FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                    int i35 = flexItemCount;
                    if (flexItem.getAlignSelf() == 4) {
                        flexLine.mIndicesAlignSelfStretch.add(Integer.valueOf(i29));
                    }
                    int flexItemSizeMain = getFlexItemSizeMain(flexItem, zIsMainAxisDirectionHorizontal);
                    if (flexItem.getFlexBasisPercent() != -1.0f && mode == 1073741824) {
                        flexItemSizeMain = Math.round(size * flexItem.getFlexBasisPercent());
                    }
                    if (zIsMainAxisDirectionHorizontal) {
                        int childWidthMeasureSpec = this.mFlexContainer.getChildWidthMeasureSpec(i25, i30 + getFlexItemMarginStartMain(flexItem, true) + getFlexItemMarginEndMain(flexItem, true), flexItemSizeMain);
                        i13 = size;
                        i14 = mode;
                        int childHeightMeasureSpec = this.mFlexContainer.getChildHeightMeasureSpec(i26, paddingStartCross + paddingEndCross + getFlexItemMarginStartCross(flexItem, true) + getFlexItemMarginEndCross(flexItem, true) + i33, getFlexItemSizeCross(flexItem, true));
                        reorderedFlexItemAt.measure(childWidthMeasureSpec, childHeightMeasureSpec);
                        updateMeasureCache(i29, childWidthMeasureSpec, childHeightMeasureSpec, reorderedFlexItemAt);
                        i15 = childWidthMeasureSpec;
                    } else {
                        i13 = size;
                        i14 = mode;
                        int childWidthMeasureSpec2 = this.mFlexContainer.getChildWidthMeasureSpec(i26, paddingStartCross + paddingEndCross + getFlexItemMarginStartCross(flexItem, false) + getFlexItemMarginEndCross(flexItem, false) + i33, getFlexItemSizeCross(flexItem, false));
                        int childHeightMeasureSpec2 = this.mFlexContainer.getChildHeightMeasureSpec(i25, getFlexItemMarginStartMain(flexItem, false) + i30 + getFlexItemMarginEndMain(flexItem, false), flexItemSizeMain);
                        reorderedFlexItemAt.measure(childWidthMeasureSpec2, childHeightMeasureSpec2);
                        updateMeasureCache(i29, childWidthMeasureSpec2, childHeightMeasureSpec2, reorderedFlexItemAt);
                        i15 = childHeightMeasureSpec2;
                    }
                    this.mFlexContainer.updateViewCache(i29, reorderedFlexItemAt);
                    checkSizeConstraints(reorderedFlexItemAt, i29);
                    iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, reorderedFlexItemAt.getMeasuredState());
                    int i36 = i33;
                    int i37 = i30;
                    FlexLine flexLine2 = flexLine;
                    int i38 = i29;
                    list2 = arrayList;
                    int i39 = i15;
                    if (isWrapRequired(reorderedFlexItemAt, i14, i13, flexLine.mMainSize, getFlexItemMarginEndMain(flexItem, zIsMainAxisDirectionHorizontal) + getViewMeasuredSizeMain(reorderedFlexItemAt, zIsMainAxisDirectionHorizontal) + getFlexItemMarginStartMain(flexItem, zIsMainAxisDirectionHorizontal), flexItem, i38, i34, arrayList.size())) {
                        i29 = i38;
                        if (flexLine2.getItemCountNotGone() > 0) {
                            addFlexLine(list2, flexLine2, i29 > 0 ? i29 - 1 : 0, i36);
                            i33 = flexLine2.mCrossSize + i36;
                        } else {
                            i33 = i36;
                        }
                        if (!zIsMainAxisDirectionHorizontal) {
                            i16 = i7;
                            view = reorderedFlexItemAt;
                            i17 = -1;
                            if (flexItem.getWidth() == -1) {
                                FlexContainer flexContainer = this.mFlexContainer;
                                view.measure(flexContainer.getChildWidthMeasureSpec(i16, flexContainer.getPaddingLeft() + this.mFlexContainer.getPaddingRight() + flexItem.getMarginLeft() + flexItem.getMarginRight() + i33, flexItem.getWidth()), i39);
                                checkSizeConstraints(view, i29);
                            }
                        } else if (flexItem.getHeight() == -1) {
                            FlexContainer flexContainer2 = this.mFlexContainer;
                            i16 = i7;
                            i17 = -1;
                            view = reorderedFlexItemAt;
                            view.measure(i39, flexContainer2.getChildHeightMeasureSpec(i16, flexContainer2.getPaddingTop() + this.mFlexContainer.getPaddingBottom() + flexItem.getMarginTop() + flexItem.getMarginBottom() + i33, flexItem.getHeight()));
                            checkSizeConstraints(view, i29);
                        } else {
                            i16 = i7;
                            view = reorderedFlexItemAt;
                            i17 = -1;
                        }
                        flexLine = new FlexLine();
                        i19 = 1;
                        flexLine.mItemCount = 1;
                        i18 = i37;
                        flexLine.mMainSize = i18;
                        flexLine.mFirstIndex = i29;
                        i21 = Integer.MIN_VALUE;
                        i20 = 0;
                    } else {
                        i16 = i7;
                        i29 = i38;
                        view = reorderedFlexItemAt;
                        i17 = -1;
                        flexLine = flexLine2;
                        i18 = i37;
                        i19 = 1;
                        flexLine.mItemCount++;
                        i20 = i34 + 1;
                        i33 = i36;
                        i21 = i32;
                    }
                    flexLine.mAnyItemsHaveFlexGrow = (flexLine.mAnyItemsHaveFlexGrow ? 1 : 0) | (flexItem.getFlexGrow() != 0.0f ? i19 : 0);
                    flexLine.mAnyItemsHaveFlexShrink = (flexLine.mAnyItemsHaveFlexShrink ? 1 : 0) | (flexItem.getFlexShrink() != 0.0f ? i19 : 0);
                    int[] iArr = this.mIndexToFlexLine;
                    if (iArr != null) {
                        iArr[i29] = list2.size();
                    }
                    flexLine.mMainSize = getViewMeasuredSizeMain(view, zIsMainAxisDirectionHorizontal) + getFlexItemMarginStartMain(flexItem, zIsMainAxisDirectionHorizontal) + getFlexItemMarginEndMain(flexItem, zIsMainAxisDirectionHorizontal) + flexLine.mMainSize;
                    flexLine.mTotalFlexGrow += flexItem.getFlexGrow();
                    flexLine.mTotalFlexShrink += flexItem.getFlexShrink();
                    this.mFlexContainer.onNewFlexItemAdded(view, i29, i20, flexLine);
                    int iMax = Math.max(i21, getViewMeasuredSizeCross(view, zIsMainAxisDirectionHorizontal) + getFlexItemMarginStartCross(flexItem, zIsMainAxisDirectionHorizontal) + getFlexItemMarginEndCross(flexItem, zIsMainAxisDirectionHorizontal) + this.mFlexContainer.getDecorationLengthCrossAxis(view));
                    flexLine.mCrossSize = Math.max(flexLine.mCrossSize, iMax);
                    if (zIsMainAxisDirectionHorizontal) {
                        if (this.mFlexContainer.getFlexWrap() != 2) {
                            flexLine.mMaxBaseline = Math.max(flexLine.mMaxBaseline, view.getBaseline() + flexItem.getMarginTop());
                        } else {
                            flexLine.mMaxBaseline = Math.max(flexLine.mMaxBaseline, (view.getMeasuredHeight() - view.getBaseline()) + flexItem.getMarginBottom());
                        }
                    }
                    i22 = i35;
                    if (isLastFlexItem(i29, i22, flexLine)) {
                        addFlexLine(list2, flexLine, i29, i33);
                        i33 += flexLine.mCrossSize;
                    }
                    i23 = i11;
                    if (i23 == i17 || list2.size() <= 0 || list2.get(list2.size() - i19).mLastIndex < i23 || i29 < i23 || i31 != 0) {
                        i24 = i9;
                    } else {
                        i33 = -flexLine.getCrossSize();
                        i24 = i9;
                        i31 = i19;
                    }
                    if (i33 > i24 && i31 != 0) {
                        flexLinesResult2 = flexLinesResult;
                        i12 = iCombineMeasuredStates;
                        break;
                    }
                    i34 = i20;
                    i32 = iMax;
                    i29++;
                    i25 = i5;
                    flexItemCount = i22;
                    i26 = i16;
                    i30 = i18;
                    arrayList = list2;
                    size = i13;
                    mode = i14;
                    i27 = i23;
                } else {
                    flexLine.mGoneItemCount++;
                    flexLine.mItemCount++;
                    if (isLastFlexItem(i29, flexItemCount, flexLine)) {
                        addFlexLine(arrayList, flexLine, i29, i33);
                    }
                }
            } else if (isLastFlexItem(i29, flexItemCount, flexLine)) {
                addFlexLine(arrayList, flexLine, i29, i33);
            }
            i13 = size;
            i14 = mode;
            i16 = i26;
            i23 = i27;
            i18 = i30;
            list2 = arrayList;
            i22 = flexItemCount;
            i29++;
            i25 = i5;
            flexItemCount = i22;
            i26 = i16;
            i30 = i18;
            arrayList = list2;
            size = i13;
            mode = i14;
            i27 = i23;
        }
        flexLinesResult2.mChildState = i12;
    }

    public void calculateHorizontalFlexLines(FlexLinesResult flexLinesResult, int i5, int i7) {
        calculateFlexLines(flexLinesResult, i5, i7, Integer.MAX_VALUE, 0, -1, null);
    }

    public void calculateHorizontalFlexLinesToIndex(FlexLinesResult flexLinesResult, int i5, int i7, int i9, int i10, List<FlexLine> list) {
        calculateFlexLines(flexLinesResult, i5, i7, i9, 0, i10, list);
    }

    public void calculateVerticalFlexLines(FlexLinesResult flexLinesResult, int i5, int i7) {
        calculateFlexLines(flexLinesResult, i7, i5, Integer.MAX_VALUE, 0, -1, null);
    }

    public void calculateVerticalFlexLinesToIndex(FlexLinesResult flexLinesResult, int i5, int i7, int i9, int i10, List<FlexLine> list) {
        calculateFlexLines(flexLinesResult, i7, i5, i9, 0, i10, list);
    }

    public void clearFlexLines(List<FlexLine> list, int i5) {
        int i7 = this.mIndexToFlexLine[i5];
        if (i7 == -1) {
            i7 = 0;
        }
        if (list.size() > i7) {
            list.subList(i7, list.size()).clear();
        }
        int[] iArr = this.mIndexToFlexLine;
        int length = iArr.length - 1;
        if (i5 > length) {
            Arrays.fill(iArr, -1);
        } else {
            Arrays.fill(iArr, i5, length, -1);
        }
        long[] jArr = this.mMeasureSpecCache;
        int length2 = jArr.length - 1;
        if (i5 > length2) {
            Arrays.fill(jArr, 0L);
        } else {
            Arrays.fill(jArr, i5, length2, 0L);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int[] createReorderedIndices(View view, int i5, ViewGroup.LayoutParams layoutParams, SparseIntArray sparseIntArray) {
        int flexItemCount = this.mFlexContainer.getFlexItemCount();
        List<Order> listCreateOrders = createOrders(flexItemCount);
        Order order = new Order();
        if (view == null || !(layoutParams instanceof FlexItem)) {
            order.order = 1;
        } else {
            order.order = ((FlexItem) layoutParams).getOrder();
        }
        if (i5 == -1 || i5 == flexItemCount || i5 >= this.mFlexContainer.getFlexItemCount()) {
            order.index = flexItemCount;
        } else {
            order.index = i5;
            while (i5 < flexItemCount) {
                listCreateOrders.get(i5).index++;
                i5++;
            }
        }
        listCreateOrders.add(order);
        return sortOrdersIntoReorderedIndices(flexItemCount + 1, listCreateOrders, sparseIntArray);
    }

    public void determineCrossSize(int i5, int i7, int i9) {
        int mode;
        int size;
        int flexDirection = this.mFlexContainer.getFlexDirection();
        if (flexDirection == 0 || flexDirection == 1) {
            int mode2 = View.MeasureSpec.getMode(i7);
            int size2 = View.MeasureSpec.getSize(i7);
            mode = mode2;
            size = size2;
        } else {
            if (flexDirection != 2 && flexDirection != 3) {
                throw new IllegalArgumentException(l.o(flexDirection, "Invalid flex direction: "));
            }
            mode = View.MeasureSpec.getMode(i5);
            size = View.MeasureSpec.getSize(i5);
        }
        List<FlexLine> flexLinesInternal = this.mFlexContainer.getFlexLinesInternal();
        if (mode == 1073741824) {
            int sumOfCrossSize = this.mFlexContainer.getSumOfCrossSize() + i9;
            int i10 = 0;
            if (flexLinesInternal.size() == 1) {
                flexLinesInternal.get(0).mCrossSize = size - i9;
                return;
            }
            if (flexLinesInternal.size() >= 2) {
                int alignContent = this.mFlexContainer.getAlignContent();
                if (alignContent == 1) {
                    FlexLine flexLine = new FlexLine();
                    flexLine.mCrossSize = size - sumOfCrossSize;
                    flexLinesInternal.add(0, flexLine);
                    return;
                }
                if (alignContent == 2) {
                    this.mFlexContainer.setFlexLines(constructFlexLinesForAlignContentCenter(flexLinesInternal, size, sumOfCrossSize));
                    return;
                }
                if (alignContent == 3) {
                    if (sumOfCrossSize >= size) {
                        return;
                    }
                    float size3 = (size - sumOfCrossSize) / (flexLinesInternal.size() - 1);
                    ArrayList arrayList = new ArrayList();
                    int size4 = flexLinesInternal.size();
                    float f2 = 0.0f;
                    while (i10 < size4) {
                        arrayList.add(flexLinesInternal.get(i10));
                        if (i10 != flexLinesInternal.size() - 1) {
                            FlexLine flexLine2 = new FlexLine();
                            if (i10 == flexLinesInternal.size() - 2) {
                                flexLine2.mCrossSize = Math.round(f2 + size3);
                                f2 = 0.0f;
                            } else {
                                flexLine2.mCrossSize = Math.round(size3);
                            }
                            int i11 = flexLine2.mCrossSize;
                            float f7 = (size3 - i11) + f2;
                            if (f7 > 1.0f) {
                                flexLine2.mCrossSize = i11 + 1;
                                f7 -= 1.0f;
                            } else if (f7 < -1.0f) {
                                flexLine2.mCrossSize = i11 - 1;
                                f7 += 1.0f;
                            }
                            f2 = f7;
                            arrayList.add(flexLine2);
                        }
                        i10++;
                    }
                    this.mFlexContainer.setFlexLines(arrayList);
                    return;
                }
                if (alignContent == 4) {
                    if (sumOfCrossSize >= size) {
                        this.mFlexContainer.setFlexLines(constructFlexLinesForAlignContentCenter(flexLinesInternal, size, sumOfCrossSize));
                        return;
                    }
                    int size5 = (size - sumOfCrossSize) / (flexLinesInternal.size() * 2);
                    ArrayList arrayList2 = new ArrayList();
                    FlexLine flexLine3 = new FlexLine();
                    flexLine3.mCrossSize = size5;
                    for (FlexLine flexLine4 : flexLinesInternal) {
                        arrayList2.add(flexLine3);
                        arrayList2.add(flexLine4);
                        arrayList2.add(flexLine3);
                    }
                    this.mFlexContainer.setFlexLines(arrayList2);
                    return;
                }
                if (alignContent == 5 && sumOfCrossSize < size) {
                    float size6 = (size - sumOfCrossSize) / flexLinesInternal.size();
                    int size7 = flexLinesInternal.size();
                    float f9 = 0.0f;
                    while (i10 < size7) {
                        FlexLine flexLine5 = flexLinesInternal.get(i10);
                        float f10 = flexLine5.mCrossSize + size6;
                        if (i10 == flexLinesInternal.size() - 1) {
                            f10 += f9;
                            f9 = 0.0f;
                        }
                        int iRound = Math.round(f10);
                        float f11 = (f10 - iRound) + f9;
                        if (f11 > 1.0f) {
                            iRound++;
                            f11 -= 1.0f;
                        } else if (f11 < -1.0f) {
                            iRound--;
                            f11 += 1.0f;
                        }
                        f9 = f11;
                        flexLine5.mCrossSize = iRound;
                        i10++;
                    }
                }
            }
        }
    }

    public void determineMainSize(int i5, int i7) {
        determineMainSize(i5, i7, 0);
    }

    public void ensureIndexToFlexLine(int i5) {
        int[] iArr = this.mIndexToFlexLine;
        if (iArr == null) {
            this.mIndexToFlexLine = new int[Math.max(i5, 10)];
        } else if (iArr.length < i5) {
            this.mIndexToFlexLine = Arrays.copyOf(this.mIndexToFlexLine, Math.max(iArr.length * 2, i5));
        }
    }

    public void ensureMeasureSpecCache(int i5) {
        long[] jArr = this.mMeasureSpecCache;
        if (jArr == null) {
            this.mMeasureSpecCache = new long[Math.max(i5, 10)];
        } else if (jArr.length < i5) {
            this.mMeasureSpecCache = Arrays.copyOf(this.mMeasureSpecCache, Math.max(jArr.length * 2, i5));
        }
    }

    public void ensureMeasuredSizeCache(int i5) {
        long[] jArr = this.mMeasuredSizeCache;
        if (jArr == null) {
            this.mMeasuredSizeCache = new long[Math.max(i5, 10)];
        } else if (jArr.length < i5) {
            this.mMeasuredSizeCache = Arrays.copyOf(this.mMeasuredSizeCache, Math.max(jArr.length * 2, i5));
        }
    }

    public int extractHigherInt(long j5) {
        return (int) (j5 >> 32);
    }

    public int extractLowerInt(long j5) {
        return (int) j5;
    }

    public boolean isOrderChangedFromLastMeasurement(SparseIntArray sparseIntArray) {
        int flexItemCount = this.mFlexContainer.getFlexItemCount();
        if (sparseIntArray.size() != flexItemCount) {
            return true;
        }
        for (int i5 = 0; i5 < flexItemCount; i5++) {
            View flexItemAt = this.mFlexContainer.getFlexItemAt(i5);
            if (flexItemAt != null && ((FlexItem) flexItemAt.getLayoutParams()).getOrder() != sparseIntArray.get(i5)) {
                return true;
            }
        }
        return false;
    }

    public void layoutSingleChildHorizontal(View view, FlexLine flexLine, int i5, int i7, int i9, int i10) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int alignItems = this.mFlexContainer.getAlignItems();
        if (flexItem.getAlignSelf() != -1) {
            alignItems = flexItem.getAlignSelf();
        }
        int i11 = flexLine.mCrossSize;
        if (alignItems != 0) {
            if (alignItems == 1) {
                if (this.mFlexContainer.getFlexWrap() != 2) {
                    int i12 = i7 + i11;
                    view.layout(i5, (i12 - view.getMeasuredHeight()) - flexItem.getMarginBottom(), i9, i12 - flexItem.getMarginBottom());
                    return;
                } else {
                    view.layout(i5, view.getMeasuredHeight() + (i7 - i11) + flexItem.getMarginTop(), i9, view.getMeasuredHeight() + (i10 - i11) + flexItem.getMarginTop());
                    return;
                }
            }
            if (alignItems == 2) {
                int measuredHeight = (((i11 - view.getMeasuredHeight()) + flexItem.getMarginTop()) - flexItem.getMarginBottom()) / 2;
                if (this.mFlexContainer.getFlexWrap() != 2) {
                    int i13 = i7 + measuredHeight;
                    view.layout(i5, i13, i9, view.getMeasuredHeight() + i13);
                    return;
                } else {
                    int i14 = i7 - measuredHeight;
                    view.layout(i5, i14, i9, view.getMeasuredHeight() + i14);
                    return;
                }
            }
            if (alignItems == 3) {
                if (this.mFlexContainer.getFlexWrap() != 2) {
                    int iMax = Math.max(flexLine.mMaxBaseline - view.getBaseline(), flexItem.getMarginTop());
                    view.layout(i5, i7 + iMax, i9, i10 + iMax);
                    return;
                } else {
                    int iMax2 = Math.max(view.getBaseline() + (flexLine.mMaxBaseline - view.getMeasuredHeight()), flexItem.getMarginBottom());
                    view.layout(i5, i7 - iMax2, i9, i10 - iMax2);
                    return;
                }
            }
            if (alignItems != 4) {
                return;
            }
        }
        if (this.mFlexContainer.getFlexWrap() != 2) {
            view.layout(i5, i7 + flexItem.getMarginTop(), i9, i10 + flexItem.getMarginTop());
        } else {
            view.layout(i5, i7 - flexItem.getMarginBottom(), i9, i10 - flexItem.getMarginBottom());
        }
    }

    public void layoutSingleChildVertical(View view, FlexLine flexLine, boolean z9, int i5, int i7, int i9, int i10) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        int alignItems = this.mFlexContainer.getAlignItems();
        if (flexItem.getAlignSelf() != -1) {
            alignItems = flexItem.getAlignSelf();
        }
        int i11 = flexLine.mCrossSize;
        if (alignItems != 0) {
            if (alignItems == 1) {
                if (!z9) {
                    view.layout(((i5 + i11) - view.getMeasuredWidth()) - flexItem.getMarginRight(), i7, ((i9 + i11) - view.getMeasuredWidth()) - flexItem.getMarginRight(), i10);
                    return;
                }
                view.layout(view.getMeasuredWidth() + (i5 - i11) + flexItem.getMarginLeft(), i7, view.getMeasuredWidth() + (i9 - i11) + flexItem.getMarginLeft(), i10);
                return;
            }
            if (alignItems == 2) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
                int marginStart = ((marginLayoutParams.getMarginStart() + (i11 - view.getMeasuredWidth())) - marginLayoutParams.getMarginEnd()) / 2;
                if (z9) {
                    view.layout(i5 - marginStart, i7, i9 - marginStart, i10);
                    return;
                } else {
                    view.layout(i5 + marginStart, i7, i9 + marginStart, i10);
                    return;
                }
            }
            if (alignItems != 3 && alignItems != 4) {
                return;
            }
        }
        if (z9) {
            view.layout(i5 - flexItem.getMarginRight(), i7, i9 - flexItem.getMarginRight(), i10);
        } else {
            view.layout(i5 + flexItem.getMarginLeft(), i7, i9 + flexItem.getMarginLeft(), i10);
        }
    }

    public long makeCombinedLong(int i5, int i7) {
        return (((long) i5) & 4294967295L) | (((long) i7) << 32);
    }

    public void stretchViews() {
        stretchViews(0);
    }

    public void calculateHorizontalFlexLines(FlexLinesResult flexLinesResult, int i5, int i7, int i9, int i10, List<FlexLine> list) {
        calculateFlexLines(flexLinesResult, i5, i7, i9, i10, -1, list);
    }

    public void calculateVerticalFlexLines(FlexLinesResult flexLinesResult, int i5, int i7, int i9, int i10, List<FlexLine> list) {
        calculateFlexLines(flexLinesResult, i7, i5, i9, i10, -1, list);
    }

    public void determineMainSize(int i5, int i7, int i9) {
        int size;
        int paddingLeft;
        int paddingRight;
        ensureChildrenFrozen(this.mFlexContainer.getFlexItemCount());
        if (i9 >= this.mFlexContainer.getFlexItemCount()) {
            return;
        }
        int flexDirection = this.mFlexContainer.getFlexDirection();
        int flexDirection2 = this.mFlexContainer.getFlexDirection();
        if (flexDirection2 == 0 || flexDirection2 == 1) {
            int mode = View.MeasureSpec.getMode(i5);
            size = View.MeasureSpec.getSize(i5);
            int largestMainSize = this.mFlexContainer.getLargestMainSize();
            if (mode != 1073741824) {
                size = Math.min(largestMainSize, size);
            }
            paddingLeft = this.mFlexContainer.getPaddingLeft();
            paddingRight = this.mFlexContainer.getPaddingRight();
        } else {
            if (flexDirection2 != 2 && flexDirection2 != 3) {
                throw new IllegalArgumentException(l.o(flexDirection, "Invalid flex direction: "));
            }
            int mode2 = View.MeasureSpec.getMode(i7);
            size = View.MeasureSpec.getSize(i7);
            if (mode2 != 1073741824) {
                size = this.mFlexContainer.getLargestMainSize();
            }
            paddingLeft = this.mFlexContainer.getPaddingTop();
            paddingRight = this.mFlexContainer.getPaddingBottom();
        }
        int i10 = paddingLeft + paddingRight;
        int[] iArr = this.mIndexToFlexLine;
        List<FlexLine> flexLinesInternal = this.mFlexContainer.getFlexLinesInternal();
        int size2 = flexLinesInternal.size();
        for (int i11 = iArr != null ? iArr[i9] : 0; i11 < size2; i11++) {
            FlexLine flexLine = flexLinesInternal.get(i11);
            int i12 = flexLine.mMainSize;
            if (i12 < size && flexLine.mAnyItemsHaveFlexGrow) {
                expandFlexItems(i5, i7, flexLine, size, i10, false);
            } else if (i12 > size && flexLine.mAnyItemsHaveFlexShrink) {
                shrinkFlexItems(i5, i7, flexLine, size, i10, false);
            }
        }
    }

    public void stretchViews(int i5) {
        View reorderedFlexItemAt;
        if (i5 >= this.mFlexContainer.getFlexItemCount()) {
            return;
        }
        int flexDirection = this.mFlexContainer.getFlexDirection();
        if (this.mFlexContainer.getAlignItems() != 4) {
            for (FlexLine flexLine : this.mFlexContainer.getFlexLinesInternal()) {
                for (Integer num : flexLine.mIndicesAlignSelfStretch) {
                    View reorderedFlexItemAt2 = this.mFlexContainer.getReorderedFlexItemAt(num.intValue());
                    if (flexDirection == 0 || flexDirection == 1) {
                        stretchViewVertically(reorderedFlexItemAt2, flexLine.mCrossSize, num.intValue());
                    } else {
                        if (flexDirection != 2 && flexDirection != 3) {
                            throw new IllegalArgumentException(l.o(flexDirection, "Invalid flex direction: "));
                        }
                        stretchViewHorizontally(reorderedFlexItemAt2, flexLine.mCrossSize, num.intValue());
                    }
                }
            }
            return;
        }
        int[] iArr = this.mIndexToFlexLine;
        List<FlexLine> flexLinesInternal = this.mFlexContainer.getFlexLinesInternal();
        int size = flexLinesInternal.size();
        for (int i7 = iArr != null ? iArr[i5] : 0; i7 < size; i7++) {
            FlexLine flexLine2 = flexLinesInternal.get(i7);
            int i9 = flexLine2.mItemCount;
            for (int i10 = 0; i10 < i9; i10++) {
                int i11 = flexLine2.mFirstIndex + i10;
                if (i10 < this.mFlexContainer.getFlexItemCount() && (reorderedFlexItemAt = this.mFlexContainer.getReorderedFlexItemAt(i11)) != null && reorderedFlexItemAt.getVisibility() != 8) {
                    FlexItem flexItem = (FlexItem) reorderedFlexItemAt.getLayoutParams();
                    if (flexItem.getAlignSelf() == -1 || flexItem.getAlignSelf() == 4) {
                        if (flexDirection == 0 || flexDirection == 1) {
                            stretchViewVertically(reorderedFlexItemAt, flexLine2.mCrossSize, i11);
                        } else {
                            if (flexDirection != 2 && flexDirection != 3) {
                                throw new IllegalArgumentException(l.o(flexDirection, "Invalid flex direction: "));
                            }
                            stretchViewHorizontally(reorderedFlexItemAt, flexLine2.mCrossSize, i11);
                        }
                    }
                }
            }
        }
    }

    public int[] createReorderedIndices(SparseIntArray sparseIntArray) {
        int flexItemCount = this.mFlexContainer.getFlexItemCount();
        return sortOrdersIntoReorderedIndices(flexItemCount, createOrders(flexItemCount), sparseIntArray);
    }
}
