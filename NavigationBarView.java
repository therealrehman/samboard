package com.google.android.material.navigation;

import E.a;
import R.c;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.view.menu.h;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.view.menu.y;
import androidx.appcompat.widget.S1;
import androidx.core.view.W;
import com.google.android.material.R;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.WeakHashMap;
import l.C0668j;

/* JADX INFO: loaded from: classes.dex */
public abstract class NavigationBarView extends FrameLayout {
    public static final int LABEL_VISIBILITY_AUTO = -1;
    public static final int LABEL_VISIBILITY_LABELED = 1;
    public static final int LABEL_VISIBILITY_SELECTED = 0;
    public static final int LABEL_VISIBILITY_UNLABELED = 2;
    private static final int MENU_PRESENTER_ID = 1;
    public static final int SESL_TYPE_ICON_LABEL = 1;
    public static final int SESL_TYPE_ICON_ONLY = 2;
    public static final int SESL_TYPE_LABEL_ONLY = 3;
    private OnItemClickListener clickListener;
    private int mMaxItemCount;
    h mSelectedCallback;
    private final NavigationBarMenu menu;
    private MenuInflater menuInflater;
    private final NavigationBarMenuView menuView;
    private final NavigationBarPresenter presenter;
    private OnItemReselectedListener reselectedListener;
    private OnItemSelectedListener selectedListener;

    @Retention(RetentionPolicy.SOURCE)
    public @interface LabelVisibility {
    }

    public interface OnItemClickListener {
        boolean onNavigationItemClick(MenuItem menuItem);
    }

    public interface OnItemReselectedListener {
        void onNavigationItemReselected(MenuItem menuItem);
    }

    public interface OnItemSelectedListener {
        boolean onNavigationItemSelected(MenuItem menuItem);
    }

    public static class SavedState extends c {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.navigation.NavigationBarView.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i5) {
                return new SavedState[i5];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, null);
            }
        };
        Bundle menuPresenterState;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private void readFromParcel(Parcel parcel, ClassLoader classLoader) {
            this.menuPresenterState = parcel.readBundle(classLoader);
        }

        @Override // R.c, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i5) {
            super.writeToParcel(parcel, i5);
            parcel.writeBundle(this.menuPresenterState);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            readFromParcel(parcel, classLoader == null ? getClass().getClassLoader() : classLoader);
        }
    }

    public NavigationBarView(Context context, AttributeSet attributeSet, int i5, int i7) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i5, i7), attributeSet, i5);
        this.mSelectedCallback = new h() { // from class: com.google.android.material.navigation.NavigationBarView.1
            @Override // androidx.appcompat.view.menu.h
            public boolean onMenuItemSelected(j jVar, MenuItem menuItem) {
                if (NavigationBarView.this.clickListener != null && NavigationBarView.this.clickListener.onNavigationItemClick(menuItem)) {
                    return true;
                }
                if (NavigationBarView.this.reselectedListener == null || menuItem.getItemId() != NavigationBarView.this.getSelectedItemId()) {
                    return (NavigationBarView.this.selectedListener == null || NavigationBarView.this.selectedListener.onNavigationItemSelected(menuItem)) ? false : true;
                }
                NavigationBarView.this.reselectedListener.onNavigationItemReselected(menuItem);
                return true;
            }

            @Override // androidx.appcompat.view.menu.h
            public void onMenuModeChange(j jVar) {
            }
        };
        Context context2 = getContext();
        int[] iArr = R.styleable.NavigationBarView;
        int i9 = R.styleable.NavigationBarView_itemTextAppearanceInactive;
        int i10 = R.styleable.NavigationBarView_itemTextAppearanceActive;
        int i11 = R.styleable.NavigationBarView_seslLabelTextAppearance;
        S1 s1ObtainTintedStyledAttributes = ThemeEnforcement.obtainTintedStyledAttributes(context2, attributeSet, iArr, i5, i7, i9, i10, i11);
        NavigationBarMenu navigationBarMenu = new NavigationBarMenu(context2, getClass(), getMaxItemCount());
        this.menu = navigationBarMenu;
        NavigationBarMenuView navigationBarMenuViewCreateNavigationBarMenuView = createNavigationBarMenuView(context2);
        this.menuView = navigationBarMenuViewCreateNavigationBarMenuView;
        NavigationBarPresenter navigationBarPresenter = new NavigationBarPresenter(context2);
        this.presenter = navigationBarPresenter;
        int maxItemCount = getMaxItemCount();
        this.mMaxItemCount = maxItemCount;
        setMaxItemCount(maxItemCount);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        navigationBarMenuViewCreateNavigationBarMenuView.setLayoutParams(layoutParams);
        int integer = s1ObtainTintedStyledAttributes.f6522b.getInteger(R.styleable.NavigationBarView_seslViewType, 3);
        seslSetViewType(integer);
        navigationBarPresenter.setMenuView(navigationBarMenuViewCreateNavigationBarMenuView);
        navigationBarPresenter.setId(1);
        navigationBarMenuViewCreateNavigationBarMenuView.setPresenter(navigationBarPresenter);
        navigationBarMenu.addMenuPresenter(navigationBarPresenter);
        navigationBarPresenter.initForMenu(getContext(), navigationBarMenu);
        int i12 = R.styleable.NavigationBarView_itemIconTint;
        TypedArray typedArray = s1ObtainTintedStyledAttributes.f6522b;
        if (typedArray.hasValue(i12)) {
            navigationBarMenuViewCreateNavigationBarMenuView.setIconTintList(s1ObtainTintedStyledAttributes.a(i12));
        } else {
            navigationBarMenuViewCreateNavigationBarMenuView.setIconTintList(navigationBarMenuViewCreateNavigationBarMenuView.createDefaultColorStateList(android.R.attr.textColorSecondary));
        }
        setItemIconSize(typedArray.getDimensionPixelSize(R.styleable.NavigationBarView_itemIconSize, getResources().getDimensionPixelSize(R.dimen.sesl_navigation_bar_icon_size)));
        if (typedArray.hasValue(i9)) {
            setItemTextAppearanceInactive(typedArray.getResourceId(i9, 0));
        }
        if (typedArray.hasValue(i11)) {
            seslSetLabelTextAppearance(typedArray.getResourceId(i11, 0));
        }
        if (typedArray.hasValue(i10)) {
            setItemTextAppearanceActive(typedArray.getResourceId(i10, 0));
        }
        setItemTextAppearanceActiveBoldEnabled(typedArray.getBoolean(R.styleable.NavigationBarView_itemTextAppearanceActiveBoldEnabled, true));
        int i13 = R.styleable.NavigationBarView_itemTextColor;
        if (typedArray.hasValue(i13)) {
            setItemTextColor(s1ObtainTintedStyledAttributes.a(i13));
        }
        Drawable background = getBackground();
        ColorStateList colorStateListOrNull = DrawableUtils.getColorStateListOrNull(background);
        if (background == null || colorStateListOrNull != null) {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(ShapeAppearanceModel.builder(context2, attributeSet, i5, i7).build());
            if (colorStateListOrNull != null) {
                materialShapeDrawable.setFillColor(colorStateListOrNull);
            }
            materialShapeDrawable.initializeElevationOverlay(context2);
            WeakHashMap weakHashMap = W.f7199a;
            setBackground(materialShapeDrawable);
        }
        if (background instanceof ColorDrawable) {
            navigationBarMenuViewCreateNavigationBarMenuView.setBackgroundColorDrawable((ColorDrawable) background);
        }
        int i14 = R.styleable.NavigationBarView_itemPaddingTop;
        if (typedArray.hasValue(i14)) {
            setItemPaddingTop(typedArray.getDimensionPixelSize(i14, 0));
        }
        int i15 = R.styleable.NavigationBarView_itemPaddingBottom;
        if (typedArray.hasValue(i15)) {
            setItemPaddingBottom(typedArray.getDimensionPixelSize(i15, 0));
        }
        int i16 = R.styleable.NavigationBarView_activeIndicatorLabelPadding;
        if (typedArray.hasValue(i16)) {
            setActiveIndicatorLabelPadding(typedArray.getDimensionPixelSize(i16, 0));
        }
        if (typedArray.hasValue(R.styleable.NavigationBarView_elevation)) {
            setElevation(typedArray.getDimensionPixelSize(r4, 0));
        }
        a.h(getBackground().mutate(), MaterialResources.getColorStateList(context2, s1ObtainTintedStyledAttributes, R.styleable.NavigationBarView_backgroundTint));
        setLabelVisibilityMode(typedArray.getInteger(R.styleable.NavigationBarView_labelVisibilityMode, -1));
        int resourceId = typedArray.getResourceId(R.styleable.NavigationBarView_itemBackground, 0);
        if (resourceId != 0) {
            navigationBarMenuViewCreateNavigationBarMenuView.setItemBackgroundRes(resourceId);
        } else {
            setItemRippleColor(MaterialResources.getColorStateList(context2, s1ObtainTintedStyledAttributes, R.styleable.NavigationBarView_itemRippleColor));
        }
        int resourceId2 = typedArray.getResourceId(R.styleable.NavigationBarView_itemStateListAnimator, 0);
        if (resourceId2 != 0) {
            navigationBarMenuViewCreateNavigationBarMenuView.setItemStateListAnimator(resourceId2);
        }
        int resourceId3 = typedArray.getResourceId(R.styleable.NavigationBarView_itemActiveIndicatorStyle, 0);
        if (resourceId3 != 0) {
            setItemActiveIndicatorEnabled(true);
            TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(resourceId3, R.styleable.NavigationBarActiveIndicator);
            setItemActiveIndicatorWidth(typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.NavigationBarActiveIndicator_android_width, 0));
            setItemActiveIndicatorHeight(typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.NavigationBarActiveIndicator_android_height, 0));
            setItemActiveIndicatorMarginHorizontal(typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.NavigationBarActiveIndicator_marginHorizontal, 0));
            setItemActiveIndicatorColor(MaterialResources.getColorStateList(context2, typedArrayObtainStyledAttributes, R.styleable.NavigationBarActiveIndicator_android_color));
            setItemActiveIndicatorShapeAppearance(ShapeAppearanceModel.builder(context2, typedArrayObtainStyledAttributes.getResourceId(R.styleable.NavigationBarActiveIndicator_shapeAppearance, 0), 0).build());
            typedArrayObtainStyledAttributes.recycle();
        }
        int i17 = R.styleable.NavigationBarView_menu;
        if (typedArray.hasValue(i17)) {
            inflateMenu(typedArray.getResourceId(i17, 0));
        }
        int i18 = R.styleable.NavigationBarView_seslExclusiveCheckable;
        if (typedArray.hasValue(i18)) {
            navigationBarMenuViewCreateNavigationBarMenuView.setExclusiveCheckable(typedArray.getBoolean(i18, true));
        }
        s1ObtainTintedStyledAttributes.f();
        addView(navigationBarMenuViewCreateNavigationBarMenuView);
        navigationBarMenu.setCallback(this.mSelectedCallback);
        navigationBarMenuViewCreateNavigationBarMenuView.setOverflowSelectedCallback(this.mSelectedCallback);
        int visibleItemCount = navigationBarMenuViewCreateNavigationBarMenuView.getVisibleItemCount();
        if (integer == 3 || visibleItemCount != this.mMaxItemCount) {
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sesl_navigation_bar_icon_mode_padding_horizontal);
            setPadding(dimensionPixelSize, getPaddingTop(), dimensionPixelSize, getPaddingBottom());
        } else {
            int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.sesl_navigation_bar_icon_mode_min_padding_horizontal);
            setPadding(dimensionPixelSize2, getPaddingTop(), dimensionPixelSize2, getPaddingBottom());
        }
    }

    private MenuInflater getMenuInflater() {
        if (this.menuInflater == null) {
            this.menuInflater = new C0668j(getContext());
        }
        return this.menuInflater;
    }

    public abstract NavigationBarMenuView createNavigationBarMenuView(Context context);

    public int getActiveIndicatorLabelPadding() {
        return this.menuView.getActiveIndicatorLabelPadding();
    }

    public BadgeDrawable getBadge(int i5) {
        return this.menuView.getBadge(i5);
    }

    public ColorStateList getItemActiveIndicatorColor() {
        return this.menuView.getItemActiveIndicatorColor();
    }

    public int getItemActiveIndicatorHeight() {
        return this.menuView.getItemActiveIndicatorHeight();
    }

    public int getItemActiveIndicatorMarginHorizontal() {
        return this.menuView.getItemActiveIndicatorMarginHorizontal();
    }

    public ShapeAppearanceModel getItemActiveIndicatorShapeAppearance() {
        return this.menuView.getItemActiveIndicatorShapeAppearance();
    }

    public int getItemActiveIndicatorWidth() {
        return this.menuView.getItemActiveIndicatorWidth();
    }

    public Drawable getItemBackground() {
        return this.menuView.getItemBackground();
    }

    @Deprecated
    public int getItemBackgroundResource() {
        return this.menuView.getItemBackgroundRes();
    }

    public int getItemIconSize() {
        return this.menuView.getItemIconSize();
    }

    public ColorStateList getItemIconTintList() {
        return this.menuView.getIconTintList();
    }

    public int getItemPaddingBottom() {
        return this.menuView.getItemPaddingBottom();
    }

    public int getItemPaddingTop() {
        return this.menuView.getItemPaddingTop();
    }

    public ColorStateList getItemRippleColor() {
        return this.menuView.getItemRippleColor();
    }

    public int getItemTextAppearanceActive() {
        return this.menuView.getItemTextAppearanceActive();
    }

    public int getItemTextAppearanceInactive() {
        return this.menuView.getItemTextAppearanceInactive();
    }

    public ColorStateList getItemTextColor() {
        return this.menuView.getItemTextColor();
    }

    public int getLabelVisibilityMode() {
        return this.menuView.getLabelVisibilityMode();
    }

    public abstract int getMaxItemCount();

    public Menu getMenu() {
        return this.menu;
    }

    public y getMenuView() {
        return this.menuView;
    }

    public BadgeDrawable getOrCreateBadge(int i5) {
        return this.menuView.getOrCreateBadge(i5);
    }

    public NavigationBarPresenter getPresenter() {
        return this.presenter;
    }

    public int getSelectedItemId() {
        return this.menuView.getSelectedItemId();
    }

    public void inflateMenu(int i5) {
        this.presenter.setUpdateSuspended(true);
        getMenuInflater().inflate(i5, this.menu);
        this.presenter.setUpdateSuspended(false);
        this.presenter.updateMenuView(true);
    }

    public boolean isItemActiveIndicatorEnabled() {
        return this.menuView.getItemActiveIndicatorEnabled();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.menu.restorePresenterStates(savedState.menuPresenterState);
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.menuPresenterState = bundle;
        this.menu.savePresenterStates(bundle);
        return savedState;
    }

    public void removeBadge(int i5) {
        this.menuView.removeBadge(i5);
    }

    public int seslGetLabelTextAppearance() {
        return this.menuView.seslGetLabelTextAppearance();
    }

    public j seslGetOverflowMenu() {
        return this.menuView.getOverflowMenu();
    }

    public boolean seslHasOverflowButton() {
        return this.menuView.hasOverflowButton();
    }

    public void seslHideOverflowMenu() {
        this.presenter.hideOverflowMenu();
    }

    public boolean seslIsOverflowShowing() {
        return this.presenter.isOverflowMenuShowing();
    }

    public void seslSetGroupDividerEnabled(boolean z9) {
        this.menuView.setGroupDividerEnabled(z9);
    }

    @Deprecated
    public void seslSetHasIcon(boolean z9) {
        this.menuView.setViewType(z9 ? 1 : 3);
    }

    public void seslSetLabelTextAppearance(int i5) {
        this.menuView.seslSetLabelTextAppearance(i5);
    }

    public void seslSetUpdateAnimation(boolean z9) {
        this.presenter.setAnimationEnable(z9);
    }

    public void seslSetViewType(int i5) {
        this.menuView.setViewType(i5);
    }

    public void seslShowOverflowMenu() {
        if (seslHasOverflowButton()) {
            this.menuView.showOverflowMenu();
        }
    }

    public void setActiveIndicatorLabelPadding(int i5) {
        this.menuView.setActiveIndicatorLabelPadding(i5);
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        MaterialShapeUtils.setElevation(this, f2);
    }

    public void setItemActiveIndicatorColor(ColorStateList colorStateList) {
        this.menuView.setItemActiveIndicatorColor(colorStateList);
    }

    public void setItemActiveIndicatorEnabled(boolean z9) {
        this.menuView.setItemActiveIndicatorEnabled(z9);
    }

    public void setItemActiveIndicatorHeight(int i5) {
        this.menuView.setItemActiveIndicatorHeight(i5);
    }

    public void setItemActiveIndicatorMarginHorizontal(int i5) {
        this.menuView.setItemActiveIndicatorMarginHorizontal(i5);
    }

    public void setItemActiveIndicatorShapeAppearance(ShapeAppearanceModel shapeAppearanceModel) {
        this.menuView.setItemActiveIndicatorShapeAppearance(shapeAppearanceModel);
    }

    public void setItemActiveIndicatorWidth(int i5) {
        this.menuView.setItemActiveIndicatorWidth(i5);
    }

    public void setItemBackground(Drawable drawable) {
        this.menuView.setItemBackground(drawable);
    }

    public void setItemBackgroundResource(int i5) {
        this.menuView.setItemBackgroundRes(i5);
    }

    public void setItemIconSize(int i5) {
        this.menuView.setItemIconSize(i5);
    }

    public void setItemIconSizeRes(int i5) {
        setItemIconSize(getResources().getDimensionPixelSize(i5));
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.menuView.setIconTintList(colorStateList);
    }

    public void setItemOnTouchListener(int i5, View.OnTouchListener onTouchListener) {
        this.menuView.setItemOnTouchListener(i5, onTouchListener);
    }

    public void setItemPaddingBottom(int i5) {
        this.menuView.setItemPaddingBottom(i5);
    }

    public void setItemPaddingTop(int i5) {
        this.menuView.setItemPaddingTop(i5);
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        this.menuView.setItemRippleColor(colorStateList);
    }

    public void setItemTextAppearanceActive(int i5) {
        this.menuView.setItemTextAppearanceActive(i5);
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z9) {
        this.menuView.setItemTextAppearanceActiveBoldEnabled(z9);
    }

    public void setItemTextAppearanceInactive(int i5) {
        this.menuView.setItemTextAppearanceInactive(i5);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.menuView.setItemTextColor(colorStateList);
    }

    public void setLabelVisibilityMode(int i5) {
        if (this.menuView.getLabelVisibilityMode() != i5) {
            this.menuView.setLabelVisibilityMode(i5);
            this.presenter.updateMenuView(false);
        }
    }

    public void setMaxItemCount(int i5) {
        this.menuView.setMaxItemCount(i5);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.clickListener = onItemClickListener;
    }

    public void setOnItemReselectedListener(OnItemReselectedListener onItemReselectedListener) {
        this.reselectedListener = onItemReselectedListener;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.selectedListener = onItemSelectedListener;
    }

    public void setSelectedItemId(int i5) {
        MenuItem menuItemFindItem = this.menu.findItem(i5);
        if (menuItemFindItem == null || this.menu.performItemAction(menuItemFindItem, this.presenter, 0)) {
            return;
        }
        menuItemFindItem.setChecked(true);
    }
}
