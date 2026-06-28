package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.view.W;
import com.google.android.material.R;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationMenuView extends NavigationBarMenuView {
    private int activeItemMaxWidth;
    private final int activeItemMinWidth;
    private final int inactiveItemMaxWidth;
    private final int inactiveItemMinWidth;
    private int itemHeight;
    private boolean itemHorizontalTranslationEnabled;
    private boolean mHasIcon;
    private float mWidthPercent;
    private final List<Integer> tempChildWidths;

    public BottomNavigationMenuView(Context context) {
        super(context);
        this.tempChildWidths = new ArrayList();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        setLayoutParams(layoutParams);
        Resources resources = getResources();
        TypedValue typedValue = new TypedValue();
        resources.getValue(R.dimen.sesl_bottom_navigation_width_proportion, typedValue, true);
        this.mWidthPercent = typedValue.getFloat();
        this.inactiveItemMaxWidth = resources.getDimensionPixelSize(R.dimen.sesl_bottom_navigation_item_max_width);
        this.inactiveItemMinWidth = resources.getDimensionPixelSize(R.dimen.sesl_bottom_navigation_item_min_width);
        this.activeItemMaxWidth = (int) (getResources().getDisplayMetrics().widthPixels * this.mWidthPercent);
        this.activeItemMinWidth = resources.getDimensionPixelSize(R.dimen.sesl_bottom_navigation_active_item_min_width);
        this.itemHeight = resources.getDimensionPixelSize(R.dimen.sesl_bottom_navigation_icon_mode_height);
        this.mUseItemPool = false;
    }

    @Override // com.google.android.material.navigation.NavigationBarMenuView
    public NavigationBarItemView createNavigationBarItemView(Context context) {
        return new BottomNavigationItemView(context);
    }

    public boolean isItemHorizontalTranslationEnabled() {
        return this.itemHorizontalTranslationEnabled;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        int childCount = getChildCount();
        int i11 = i9 - i5;
        int i12 = i10 - i7;
        int dimensionPixelSize = this.mHasIcon ? getViewVisibleItemCount() == 5 ? getResources().getDimensionPixelSize(R.dimen.sesl_bottom_navigation_icon_mode_min_padding_horizontal) : getResources().getDimensionPixelSize(R.dimen.sesl_bottom_navigation_icon_mode_padding_horizontal) : 0;
        int measuredWidth = 0;
        for (int i13 = 0; i13 < childCount; i13++) {
            View childAt = getChildAt(i13);
            if (childAt.getVisibility() != 8) {
                WeakHashMap weakHashMap = W.f7199a;
                if (getLayoutDirection() == 1) {
                    int i14 = i11 - measuredWidth;
                    childAt.layout((i14 - childAt.getMeasuredWidth()) + dimensionPixelSize, 0, i14 - dimensionPixelSize, i12);
                } else {
                    childAt.layout(measuredWidth + dimensionPixelSize, 0, (childAt.getMeasuredWidth() + measuredWidth) - dimensionPixelSize, i12);
                }
                measuredWidth += childAt.getMeasuredWidth();
            }
        }
        updateBadgeIfNeeded();
    }

    @Override // android.view.View
    public void onMeasure(int i5, int i7) {
        int i9;
        int i10;
        if (View.MeasureSpec.getSize(i5) / getResources().getDisplayMetrics().density < 590.0f) {
            this.mWidthPercent = 1.0f;
        } else {
            this.mWidthPercent = 0.75f;
        }
        this.activeItemMaxWidth = (int) (getResources().getDisplayMetrics().widthPixels * this.mWidthPercent);
        int size = (int) (View.MeasureSpec.getSize(i5) * this.mWidthPercent);
        getMenu();
        getVisibleItemCount();
        int i11 = 0;
        for (int i12 = 0; i12 < getChildCount(); i12++) {
            if (getChildAt(i12).getVisibility() == 0) {
                i11++;
            }
        }
        int childCount = getChildCount();
        this.tempChildWidths.clear();
        this.mHasIcon = getViewType() != 3;
        this.itemHeight = getResources().getDimensionPixelSize(this.mHasIcon ? R.dimen.sesl_bottom_navigation_icon_mode_height : R.dimen.sesl_bottom_navigation_text_mode_height);
        int size2 = View.MeasureSpec.getSize(i7);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size2, 1073741824);
        if (isShifting(getLabelVisibilityMode(), childCount) && isItemHorizontalTranslationEnabled()) {
            View childAt = getChildAt(getSelectedItemPosition());
            int iMax = this.activeItemMinWidth;
            if (childAt.getVisibility() != 8) {
                childAt.measure(View.MeasureSpec.makeMeasureSpec(this.activeItemMaxWidth, Integer.MIN_VALUE), iMakeMeasureSpec);
                iMax = Math.max(iMax, childAt.getMeasuredWidth());
            }
            int i13 = childCount - (childAt.getVisibility() != 8 ? 1 : 0);
            int iMin = Math.min(size - (this.inactiveItemMinWidth * i13), Math.min(iMax, this.activeItemMaxWidth));
            int i14 = size - iMin;
            int iMin2 = Math.min(i14 / (i13 != 0 ? i13 : 1), this.inactiveItemMaxWidth);
            int i15 = i14 - (i13 * iMin2);
            int i16 = 0;
            while (i16 < childCount) {
                if (getChildAt(i16).getVisibility() != 8) {
                    i10 = i16 == getSelectedItemPosition() ? iMin : iMin2;
                    if (i15 > 0) {
                        i10++;
                        i15--;
                    }
                } else {
                    i10 = 0;
                }
                this.tempChildWidths.add(Integer.valueOf(i10));
                i16++;
            }
        } else {
            int iMin3 = size / (i11 != 0 ? i11 : 1);
            if (i11 != 2) {
                iMin3 = Math.min(iMin3, this.activeItemMaxWidth);
            }
            int i17 = size - (i11 * iMin3);
            for (int i18 = 0; i18 < childCount; i18++) {
                if (getChildAt(i18).getVisibility() == 8) {
                    i9 = 0;
                } else if (i17 > 0) {
                    i9 = iMin3 + 1;
                    i17--;
                } else {
                    i9 = iMin3;
                }
                this.tempChildWidths.add(Integer.valueOf(i9));
            }
        }
        int measuredWidth = 0;
        for (int i19 = 0; i19 < childCount; i19++) {
            View childAt2 = getChildAt(i19);
            if (childAt2 != null && childAt2.getVisibility() != 8) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec(this.tempChildWidths.get(i19).intValue(), 1073741824), iMakeMeasureSpec);
                childAt2.getLayoutParams().width = childAt2.getMeasuredWidth();
                measuredWidth = childAt2.getMeasuredWidth() + measuredWidth;
            }
        }
        setMeasuredDimension(measuredWidth, size2);
    }

    public void setItemHorizontalTranslationEnabled(boolean z9) {
        this.itemHorizontalTranslationEnabled = z9;
    }
}
