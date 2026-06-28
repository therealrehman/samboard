package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.coordinatorlayout.widget.d;

/* JADX INFO: loaded from: classes.dex */
class ViewOffsetBehavior<V extends View> extends d {
    private ViewOffsetHelper viewOffsetHelper;
    private int tempTopBottomOffset = 0;
    private int tempLeftRightOffset = 0;

    public ViewOffsetBehavior() {
    }

    public int getLeftAndRightOffset() {
        ViewOffsetHelper viewOffsetHelper = this.viewOffsetHelper;
        if (viewOffsetHelper != null) {
            return viewOffsetHelper.getLeftAndRightOffset();
        }
        return 0;
    }

    public int getTopAndBottomOffset() {
        ViewOffsetHelper viewOffsetHelper = this.viewOffsetHelper;
        if (viewOffsetHelper != null) {
            return viewOffsetHelper.getTopAndBottomOffset();
        }
        return 0;
    }

    public boolean isHorizontalOffsetEnabled() {
        ViewOffsetHelper viewOffsetHelper = this.viewOffsetHelper;
        return viewOffsetHelper != null && viewOffsetHelper.isHorizontalOffsetEnabled();
    }

    public boolean isVerticalOffsetEnabled() {
        ViewOffsetHelper viewOffsetHelper = this.viewOffsetHelper;
        return viewOffsetHelper != null && viewOffsetHelper.isVerticalOffsetEnabled();
    }

    public void layoutChild(CoordinatorLayout coordinatorLayout, V v4, int i5) {
        coordinatorLayout.onLayoutChild(v4, i5);
    }

    @Override // androidx.coordinatorlayout.widget.d
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v4, int i5) {
        layoutChild(coordinatorLayout, v4, i5);
        if (this.viewOffsetHelper == null) {
            this.viewOffsetHelper = new ViewOffsetHelper(v4);
        }
        this.viewOffsetHelper.onViewLayout();
        this.viewOffsetHelper.applyOffsets();
        int i7 = this.tempTopBottomOffset;
        if (i7 != 0) {
            this.viewOffsetHelper.setTopAndBottomOffset(i7);
            this.tempTopBottomOffset = 0;
        }
        int i9 = this.tempLeftRightOffset;
        if (i9 == 0) {
            return true;
        }
        this.viewOffsetHelper.setLeftAndRightOffset(i9);
        this.tempLeftRightOffset = 0;
        return true;
    }

    public void setHorizontalOffsetEnabled(boolean z9) {
        ViewOffsetHelper viewOffsetHelper = this.viewOffsetHelper;
        if (viewOffsetHelper != null) {
            viewOffsetHelper.setHorizontalOffsetEnabled(z9);
        }
    }

    public boolean setLeftAndRightOffset(int i5) {
        ViewOffsetHelper viewOffsetHelper = this.viewOffsetHelper;
        if (viewOffsetHelper != null) {
            return viewOffsetHelper.setLeftAndRightOffset(i5);
        }
        this.tempLeftRightOffset = i5;
        return false;
    }

    public boolean setTopAndBottomOffset(int i5) {
        ViewOffsetHelper viewOffsetHelper = this.viewOffsetHelper;
        if (viewOffsetHelper != null) {
            return viewOffsetHelper.setTopAndBottomOffset(i5);
        }
        this.tempTopBottomOffset = i5;
        return false;
    }

    public void setVerticalOffsetEnabled(boolean z9) {
        ViewOffsetHelper viewOffsetHelper = this.viewOffsetHelper;
        if (viewOffsetHelper != null) {
            viewOffsetHelper.setVerticalOffsetEnabled(z9);
        }
    }

    public ViewOffsetBehavior(Context context, AttributeSet attributeSet) {
    }
}
