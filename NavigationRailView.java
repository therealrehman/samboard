package com.google.android.material.navigationrail;

import D.f;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.widget.S1;
import androidx.core.view.W;
import androidx.core.view.w0;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.resources.MaterialResources;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class NavigationRailView extends NavigationBarView {
    private static final int DEFAULT_HEADER_GRAVITY = 49;
    static final int DEFAULT_MENU_GRAVITY = 49;
    static final int MAX_ITEM_COUNT = 7;
    static final int NO_ITEM_MINIMUM_HEIGHT = -1;
    private View headerView;
    private Boolean paddingBottomSystemWindowInsets;
    private Boolean paddingStartSystemWindowInsets;
    private Boolean paddingTopSystemWindowInsets;
    private final int topMargin;

    public NavigationRailView(Context context) {
        this(context, null);
    }

    private void applyWindowInsets() {
        ViewUtils.doOnApplyWindowInsets(this, new ViewUtils.OnApplyWindowInsetsListener() { // from class: com.google.android.material.navigationrail.NavigationRailView.1
            @Override // com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener
            public w0 onApplyWindowInsets(View view, w0 w0Var, ViewUtils.RelativePadding relativePadding) {
                f fVarF = w0Var.f7266a.f(7);
                NavigationRailView navigationRailView = NavigationRailView.this;
                if (navigationRailView.shouldApplyWindowInsetPadding(navigationRailView.paddingTopSystemWindowInsets)) {
                    relativePadding.top += fVarF.f408b;
                }
                NavigationRailView navigationRailView2 = NavigationRailView.this;
                if (navigationRailView2.shouldApplyWindowInsetPadding(navigationRailView2.paddingBottomSystemWindowInsets)) {
                    relativePadding.bottom += fVarF.f410d;
                }
                NavigationRailView navigationRailView3 = NavigationRailView.this;
                if (navigationRailView3.shouldApplyWindowInsetPadding(navigationRailView3.paddingStartSystemWindowInsets)) {
                    relativePadding.start += ViewUtils.isLayoutRtl(view) ? fVarF.f409c : fVarF.f407a;
                }
                relativePadding.applyToView(view);
                return w0Var;
            }
        });
    }

    private NavigationRailMenuView getNavigationRailMenuView() {
        return (NavigationRailMenuView) getMenuView();
    }

    private boolean isHeaderViewVisible() {
        View view = this.headerView;
        return (view == null || view.getVisibility() == 8) ? false : true;
    }

    private int makeMinWidthSpec(int i5) {
        int suggestedMinimumWidth = getSuggestedMinimumWidth();
        if (View.MeasureSpec.getMode(i5) == 1073741824 || suggestedMinimumWidth <= 0) {
            return i5;
        }
        return View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i5), getPaddingRight() + getPaddingLeft() + suggestedMinimumWidth), 1073741824);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldApplyWindowInsetPadding(Boolean bool) {
        if (bool != null) {
            return bool.booleanValue();
        }
        WeakHashMap weakHashMap = W.f7199a;
        return getFitsSystemWindows();
    }

    public void addHeaderView(int i5) {
        addHeaderView(LayoutInflater.from(getContext()).inflate(i5, (ViewGroup) this, false));
    }

    public View getHeaderView() {
        return this.headerView;
    }

    public int getItemMinimumHeight() {
        return ((NavigationRailMenuView) getMenuView()).getItemMinimumHeight();
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public int getMaxItemCount() {
        return 7;
    }

    public int getMenuGravity() {
        return getNavigationRailMenuView().getMenuGravity();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        super.onLayout(z9, i5, i7, i9, i10);
        NavigationRailMenuView navigationRailMenuView = getNavigationRailMenuView();
        int i11 = 0;
        if (isHeaderViewVisible()) {
            int bottom = this.headerView.getBottom() + this.topMargin;
            int top = navigationRailMenuView.getTop();
            if (top < bottom) {
                i11 = bottom - top;
            }
        } else if (navigationRailMenuView.isTopGravity()) {
            i11 = this.topMargin;
        }
        if (i11 > 0) {
            navigationRailMenuView.layout(navigationRailMenuView.getLeft(), navigationRailMenuView.getTop() + i11, navigationRailMenuView.getRight(), navigationRailMenuView.getBottom() + i11);
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i5, int i7) {
        int iMakeMinWidthSpec = makeMinWidthSpec(i5);
        super.onMeasure(iMakeMinWidthSpec, i7);
        if (isHeaderViewVisible()) {
            measureChild(getNavigationRailMenuView(), iMakeMinWidthSpec, View.MeasureSpec.makeMeasureSpec((getMeasuredHeight() - this.headerView.getMeasuredHeight()) - this.topMargin, Integer.MIN_VALUE));
        }
    }

    public void removeHeaderView() {
        View view = this.headerView;
        if (view != null) {
            removeView(view);
            this.headerView = null;
        }
    }

    public void setItemMinimumHeight(int i5) {
        ((NavigationRailMenuView) getMenuView()).setItemMinimumHeight(i5);
    }

    public void setMenuGravity(int i5) {
        getNavigationRailMenuView().setMenuGravity(i5);
    }

    public NavigationRailView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.navigationRailStyle);
    }

    public void addHeaderView(View view) {
        removeHeaderView();
        this.headerView = view;
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 49;
        layoutParams.topMargin = this.topMargin;
        addView(view, 0, layoutParams);
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public NavigationRailMenuView createNavigationBarMenuView(Context context) {
        return new NavigationRailMenuView(context);
    }

    public NavigationRailView(Context context, AttributeSet attributeSet, int i5) {
        this(context, attributeSet, i5, R.style.Widget_MaterialComponents_NavigationRailView);
    }

    public NavigationRailView(Context context, AttributeSet attributeSet, int i5, int i7) {
        super(context, attributeSet, i5, i7);
        this.paddingTopSystemWindowInsets = null;
        this.paddingBottomSystemWindowInsets = null;
        this.paddingStartSystemWindowInsets = null;
        this.topMargin = getResources().getDimensionPixelSize(R.dimen.mtrl_navigation_rail_margin);
        Context context2 = getContext();
        S1 s1ObtainTintedStyledAttributes = ThemeEnforcement.obtainTintedStyledAttributes(context2, attributeSet, R.styleable.NavigationRailView, i5, i7, new int[0]);
        int resourceId = s1ObtainTintedStyledAttributes.f6522b.getResourceId(R.styleable.NavigationRailView_headerLayout, 0);
        if (resourceId != 0) {
            addHeaderView(resourceId);
        }
        int i9 = R.styleable.NavigationRailView_menuGravity;
        TypedArray typedArray = s1ObtainTintedStyledAttributes.f6522b;
        setMenuGravity(typedArray.getInt(i9, 49));
        int i10 = R.styleable.NavigationRailView_itemMinHeight;
        if (typedArray.hasValue(i10)) {
            setItemMinimumHeight(typedArray.getDimensionPixelSize(i10, -1));
        }
        int i11 = R.styleable.NavigationRailView_paddingTopSystemWindowInsets;
        if (typedArray.hasValue(i11)) {
            this.paddingTopSystemWindowInsets = Boolean.valueOf(typedArray.getBoolean(i11, false));
        }
        int i12 = R.styleable.NavigationRailView_paddingBottomSystemWindowInsets;
        if (typedArray.hasValue(i12)) {
            this.paddingBottomSystemWindowInsets = Boolean.valueOf(typedArray.getBoolean(i12, false));
        }
        int i13 = R.styleable.NavigationRailView_paddingStartSystemWindowInsets;
        if (typedArray.hasValue(i13)) {
            this.paddingStartSystemWindowInsets = Boolean.valueOf(typedArray.getBoolean(i13, false));
        }
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.m3_navigation_rail_item_padding_top_with_large_font);
        int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen.m3_navigation_rail_item_padding_bottom_with_large_font);
        float fLerp = AnimationUtils.lerp(0.0f, 1.0f, 0.3f, 1.0f, MaterialResources.getFontScale(context2) - 1.0f);
        float fLerp2 = AnimationUtils.lerp(getItemPaddingTop(), dimensionPixelOffset, fLerp);
        float fLerp3 = AnimationUtils.lerp(getItemPaddingBottom(), dimensionPixelOffset2, fLerp);
        setItemPaddingTop(Math.round(fLerp2));
        setItemPaddingBottom(Math.round(fLerp3));
        s1ObtainTintedStyledAttributes.f();
        applyWindowInsets();
    }
}
