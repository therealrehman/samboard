package com.google.android.material.sidesheet;

import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* JADX INFO: loaded from: classes.dex */
abstract class SheetDelegate {
    public abstract int calculateInnerMargin(ViewGroup.MarginLayoutParams marginLayoutParams);

    public abstract float calculateSlideOffset(int i5);

    public abstract int getCoplanarSiblingAdjacentMargin(ViewGroup.MarginLayoutParams marginLayoutParams);

    public abstract int getExpandedOffset();

    public abstract int getHiddenOffset();

    public abstract int getMaxViewPositionHorizontal();

    public abstract int getMinViewPositionHorizontal();

    public abstract <V extends View> int getOuterEdge(V v4);

    public abstract int getParentInnerEdge(CoordinatorLayout coordinatorLayout);

    public abstract int getSheetEdge();

    public abstract boolean isExpandingOutwards(float f2);

    public abstract boolean isReleasedCloseToInnerEdge(View view);

    public abstract boolean isSwipeSignificant(float f2, float f7);

    public abstract boolean shouldHide(View view, float f2);

    public abstract void updateCoplanarSiblingAdjacentMargin(ViewGroup.MarginLayoutParams marginLayoutParams, int i5);

    public abstract void updateCoplanarSiblingLayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams, int i5, int i7);
}
