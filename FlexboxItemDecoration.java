package com.google.android.flexbox;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.AbstractC0362u0;
import androidx.recyclerview.widget.C0372z0;
import androidx.recyclerview.widget.R0;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FlexboxItemDecoration extends AbstractC0362u0 {
    public static final int BOTH = 3;
    public static final int HORIZONTAL = 1;
    private static final int[] LIST_DIVIDER_ATTRS = {android.R.attr.listDivider};
    public static final int VERTICAL = 2;
    private Drawable mDrawable;
    private int mOrientation;

    public FlexboxItemDecoration(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(LIST_DIVIDER_ATTRS);
        this.mDrawable = typedArrayObtainStyledAttributes.getDrawable(0);
        typedArrayObtainStyledAttributes.recycle();
        setOrientation(3);
    }

    private void drawHorizontalDecorations(Canvas canvas, RecyclerView recyclerView) {
        int top;
        int intrinsicHeight;
        int left;
        int right;
        int i5;
        int iMin;
        int left2;
        if (needsHorizontalDecoration()) {
            FlexboxLayoutManager flexboxLayoutManager = (FlexboxLayoutManager) recyclerView.getLayoutManager();
            int flexDirection = flexboxLayoutManager.getFlexDirection();
            int left3 = recyclerView.getLeft() - recyclerView.getPaddingLeft();
            int paddingRight = recyclerView.getPaddingRight() + recyclerView.getRight();
            int childCount = recyclerView.getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = recyclerView.getChildAt(i7);
                C0372z0 c0372z0 = (C0372z0) childAt.getLayoutParams();
                if (flexDirection == 3) {
                    intrinsicHeight = childAt.getBottom() + ((ViewGroup.MarginLayoutParams) c0372z0).bottomMargin;
                    top = this.mDrawable.getIntrinsicHeight() + intrinsicHeight;
                } else {
                    top = childAt.getTop() - ((ViewGroup.MarginLayoutParams) c0372z0).topMargin;
                    intrinsicHeight = top - this.mDrawable.getIntrinsicHeight();
                }
                if (!flexboxLayoutManager.isMainAxisDirectionHorizontal()) {
                    left = childAt.getLeft() - ((ViewGroup.MarginLayoutParams) c0372z0).leftMargin;
                    right = childAt.getRight();
                    i5 = ((ViewGroup.MarginLayoutParams) c0372z0).rightMargin;
                } else if (flexboxLayoutManager.isLayoutRtl()) {
                    iMin = Math.min(this.mDrawable.getIntrinsicWidth() + childAt.getRight() + ((ViewGroup.MarginLayoutParams) c0372z0).rightMargin, paddingRight);
                    left2 = childAt.getLeft() - ((ViewGroup.MarginLayoutParams) c0372z0).leftMargin;
                    this.mDrawable.setBounds(left2, intrinsicHeight, iMin, top);
                    this.mDrawable.draw(canvas);
                } else {
                    left = Math.max((childAt.getLeft() - ((ViewGroup.MarginLayoutParams) c0372z0).leftMargin) - this.mDrawable.getIntrinsicWidth(), left3);
                    right = childAt.getRight();
                    i5 = ((ViewGroup.MarginLayoutParams) c0372z0).rightMargin;
                }
                int i9 = left;
                iMin = right + i5;
                left2 = i9;
                this.mDrawable.setBounds(left2, intrinsicHeight, iMin, top);
                this.mDrawable.draw(canvas);
            }
        }
    }

    private void drawVerticalDecorations(Canvas canvas, RecyclerView recyclerView) {
        int left;
        int intrinsicWidth;
        int iMax;
        int bottom;
        int i5;
        int i7;
        if (needsVerticalDecoration()) {
            FlexboxLayoutManager flexboxLayoutManager = (FlexboxLayoutManager) recyclerView.getLayoutManager();
            int top = recyclerView.getTop() - recyclerView.getPaddingTop();
            int paddingBottom = recyclerView.getPaddingBottom() + recyclerView.getBottom();
            int childCount = recyclerView.getChildCount();
            int flexDirection = flexboxLayoutManager.getFlexDirection();
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt = recyclerView.getChildAt(i9);
                C0372z0 c0372z0 = (C0372z0) childAt.getLayoutParams();
                if (flexboxLayoutManager.isLayoutRtl()) {
                    intrinsicWidth = childAt.getRight() + ((ViewGroup.MarginLayoutParams) c0372z0).rightMargin;
                    left = this.mDrawable.getIntrinsicWidth() + intrinsicWidth;
                } else {
                    left = childAt.getLeft() - ((ViewGroup.MarginLayoutParams) c0372z0).leftMargin;
                    intrinsicWidth = left - this.mDrawable.getIntrinsicWidth();
                }
                if (flexboxLayoutManager.isMainAxisDirectionHorizontal()) {
                    iMax = childAt.getTop() - ((ViewGroup.MarginLayoutParams) c0372z0).topMargin;
                    bottom = childAt.getBottom();
                    i5 = ((ViewGroup.MarginLayoutParams) c0372z0).bottomMargin;
                } else if (flexDirection == 3) {
                    int iMin = Math.min(this.mDrawable.getIntrinsicHeight() + childAt.getBottom() + ((ViewGroup.MarginLayoutParams) c0372z0).bottomMargin, paddingBottom);
                    iMax = childAt.getTop() - ((ViewGroup.MarginLayoutParams) c0372z0).topMargin;
                    i7 = iMin;
                    this.mDrawable.setBounds(intrinsicWidth, iMax, left, i7);
                    this.mDrawable.draw(canvas);
                } else {
                    iMax = Math.max((childAt.getTop() - ((ViewGroup.MarginLayoutParams) c0372z0).topMargin) - this.mDrawable.getIntrinsicHeight(), top);
                    bottom = childAt.getBottom();
                    i5 = ((ViewGroup.MarginLayoutParams) c0372z0).bottomMargin;
                }
                i7 = bottom + i5;
                this.mDrawable.setBounds(intrinsicWidth, iMax, left, i7);
                this.mDrawable.draw(canvas);
            }
        }
    }

    private boolean isFirstItemInLine(int i5, List<FlexLine> list, FlexboxLayoutManager flexboxLayoutManager) {
        int positionToFlexLineIndex = flexboxLayoutManager.getPositionToFlexLineIndex(i5);
        if ((positionToFlexLineIndex == -1 || positionToFlexLineIndex >= flexboxLayoutManager.getFlexLinesInternal().size() || flexboxLayoutManager.getFlexLinesInternal().get(positionToFlexLineIndex).mFirstIndex != i5) && i5 != 0) {
            return list.size() != 0 && list.get(list.size() - 1).mLastIndex == i5 - 1;
        }
        return true;
    }

    private boolean needsHorizontalDecoration() {
        return (this.mOrientation & 1) > 0;
    }

    private boolean needsVerticalDecoration() {
        return (this.mOrientation & 2) > 0;
    }

    private void setOffsetAlongCrossAxis(Rect rect, int i5, FlexboxLayoutManager flexboxLayoutManager, List<FlexLine> list) {
        if (list.size() == 0 || flexboxLayoutManager.getPositionToFlexLineIndex(i5) == 0) {
            return;
        }
        if (flexboxLayoutManager.isMainAxisDirectionHorizontal()) {
            if (needsHorizontalDecoration()) {
                rect.top = this.mDrawable.getIntrinsicHeight();
                rect.bottom = 0;
                return;
            } else {
                rect.top = 0;
                rect.bottom = 0;
                return;
            }
        }
        if (needsVerticalDecoration()) {
            if (flexboxLayoutManager.isLayoutRtl()) {
                rect.right = this.mDrawable.getIntrinsicWidth();
                rect.left = 0;
            } else {
                rect.left = this.mDrawable.getIntrinsicWidth();
                rect.right = 0;
            }
        }
    }

    private void setOffsetAlongMainAxis(Rect rect, int i5, FlexboxLayoutManager flexboxLayoutManager, List<FlexLine> list, int i7) {
        if (isFirstItemInLine(i5, list, flexboxLayoutManager)) {
            return;
        }
        if (flexboxLayoutManager.isMainAxisDirectionHorizontal()) {
            if (!needsVerticalDecoration()) {
                rect.left = 0;
                rect.right = 0;
                return;
            } else if (flexboxLayoutManager.isLayoutRtl()) {
                rect.right = this.mDrawable.getIntrinsicWidth();
                rect.left = 0;
                return;
            } else {
                rect.left = this.mDrawable.getIntrinsicWidth();
                rect.right = 0;
                return;
            }
        }
        if (!needsHorizontalDecoration()) {
            rect.top = 0;
            rect.bottom = 0;
        } else if (i7 == 3) {
            rect.bottom = this.mDrawable.getIntrinsicHeight();
            rect.top = 0;
        } else {
            rect.top = this.mDrawable.getIntrinsicHeight();
            rect.bottom = 0;
        }
    }

    @Override // androidx.recyclerview.widget.AbstractC0362u0
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, R0 r02) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if (childAdapterPosition == 0) {
            return;
        }
        if (!needsHorizontalDecoration() && !needsVerticalDecoration()) {
            rect.set(0, 0, 0, 0);
            return;
        }
        FlexboxLayoutManager flexboxLayoutManager = (FlexboxLayoutManager) recyclerView.getLayoutManager();
        List<FlexLine> flexLines = flexboxLayoutManager.getFlexLines();
        setOffsetAlongMainAxis(rect, childAdapterPosition, flexboxLayoutManager, flexLines, flexboxLayoutManager.getFlexDirection());
        setOffsetAlongCrossAxis(rect, childAdapterPosition, flexboxLayoutManager, flexLines);
    }

    @Override // androidx.recyclerview.widget.AbstractC0362u0
    public void onDraw(Canvas canvas, RecyclerView recyclerView, R0 r02) {
        drawHorizontalDecorations(canvas, recyclerView);
        drawVerticalDecorations(canvas, recyclerView);
    }

    public void setDrawable(Drawable drawable) {
        if (drawable == null) {
            throw new IllegalArgumentException("Drawable cannot be null.");
        }
        this.mDrawable = drawable;
    }

    public void setOrientation(int i5) {
        this.mOrientation = i5;
    }
}
