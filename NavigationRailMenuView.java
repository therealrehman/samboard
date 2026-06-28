package com.google.android.material.navigationrail;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;

/* JADX INFO: loaded from: classes.dex */
public class NavigationRailMenuView extends NavigationBarMenuView {
    private int itemMinimumHeight;
    private final FrameLayout.LayoutParams layoutParams;

    public NavigationRailMenuView(Context context) {
        super(context);
        this.itemMinimumHeight = -1;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        this.layoutParams = layoutParams;
        layoutParams.gravity = 49;
        setLayoutParams(layoutParams);
        setItemActiveIndicatorResizeable(true);
    }

    private int makeSharedHeightSpec(int i5, int i7, int i9) {
        int iMax = i7 / Math.max(1, i9);
        int size = this.itemMinimumHeight;
        if (size == -1) {
            size = View.MeasureSpec.getSize(i5);
        }
        return View.MeasureSpec.makeMeasureSpec(Math.min(size, iMax), 0);
    }

    private int measureChildHeight(View view, int i5, int i7) {
        if (view.getVisibility() == 8) {
            return 0;
        }
        view.measure(i5, i7);
        return view.getMeasuredHeight();
    }

    private int measureSharedChildHeights(int i5, int i7, int i9, View view) {
        int iMakeSharedHeightSpec = view == null ? makeSharedHeightSpec(i5, i7, i9) : View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), 0);
        int childCount = getChildCount();
        int iMeasureChildHeight = 0;
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            if (childAt != view) {
                iMeasureChildHeight += measureChildHeight(childAt, i5, iMakeSharedHeightSpec);
            }
        }
        return iMeasureChildHeight;
    }

    private int measureShiftingChildHeights(int i5, int i7, int i9) {
        int iMeasureChildHeight;
        View childAt = getChildAt(getSelectedItemPosition());
        if (childAt != null) {
            iMeasureChildHeight = measureChildHeight(childAt, i5, makeSharedHeightSpec(i5, i7, i9));
            i7 -= iMeasureChildHeight;
            i9--;
        } else {
            iMeasureChildHeight = 0;
        }
        return iMeasureChildHeight + measureSharedChildHeights(i5, i7, i9, childAt);
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuView
    public NavigationBarItemView createNavigationBarItemView(Context context) {
        return new NavigationRailItemView(context);
    }

    public int getItemMinimumHeight() {
        return this.itemMinimumHeight;
    }

    public int getMenuGravity() {
        return this.layoutParams.gravity;
    }

    public boolean isTopGravity() {
        return (this.layoutParams.gravity & 112) == 48;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        int childCount = getChildCount();
        int i11 = i9 - i5;
        int i12 = 0;
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt = getChildAt(i13);
            if (childAt.getVisibility() != 8) {
                int measuredHeight = childAt.getMeasuredHeight() + i12;
                childAt.layout(0, i12, i11, measuredHeight);
                i12 = measuredHeight;
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i5, int i7) {
        int size = View.MeasureSpec.getSize(i7);
        int size2 = getMenu().getVisibleItems().size();
        setMeasuredDimension(View.MeasureSpec.getSize(i5), View.resolveSizeAndState((size2 <= 1 || !isShifting(getLabelVisibilityMode(), size2)) ? measureSharedChildHeights(i5, size, size2, null) : measureShiftingChildHeights(i5, size, size2), i7, 0));
    }

    public void setItemMinimumHeight(int i5) {
        if (this.itemMinimumHeight != i5) {
            this.itemMinimumHeight = i5;
            requestLayout();
        }
    }

    public void setMenuGravity(int i5) {
        FrameLayout.LayoutParams layoutParams = this.layoutParams;
        if (layoutParams == null) {
            return;
        }
        if (layoutParams.gravity != i5) {
            layoutParams.gravity = i5;
        }
        setLayoutParams(layoutParams);
    }
}
