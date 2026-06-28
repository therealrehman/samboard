package com.google.android.material.navigation;

import K.d;
import K.f;
import android.R;
import android.animation.AnimatorInflater;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.view.menu.h;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.view.menu.l;
import androidx.appcompat.view.menu.y;
import androidx.appcompat.widget.AbstractC0152g1;
import androidx.core.view.W;
import androidx.transition.C0374b;
import androidx.transition.Y;
import androidx.transition.a0;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.internal.TextScale;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.HashSet;
import java.util.WeakHashMap;
import p0.a;
import s6.c;

/* JADX INFO: loaded from: classes.dex */
public abstract class NavigationBarMenuView extends ViewGroup implements y {
    static final int BADGE_TYPE_DOT = 1;
    static final int BADGE_TYPE_N = 2;
    static final int BADGE_TYPE_OVERFLOW = 0;
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private static final int[] DISABLED_STATE_SET = {-16842910};
    private static final int ITEM_POOL_SIZE = 5;
    private static final int NO_PADDING = -1;
    private static final String TAG = "NavigationBarMenuView";
    private final SparseArray<BadgeDrawable> badgeDrawables;
    NavigationBarItemView[] buttons;
    private ColorStateList itemActiveIndicatorColor;
    private boolean itemActiveIndicatorEnabled;
    private int itemActiveIndicatorHeight;
    private int itemActiveIndicatorLabelPadding;
    private int itemActiveIndicatorMarginHorizontal;
    private boolean itemActiveIndicatorResizeable;
    private ShapeAppearanceModel itemActiveIndicatorShapeAppearance;
    private int itemActiveIndicatorWidth;
    private Drawable itemBackground;
    private int itemBackgroundRes;
    private int itemIconSize;
    private ColorStateList itemIconTint;
    private int itemPaddingBottom;
    private int itemPaddingTop;
    private final d itemPool;
    private ColorStateList itemRippleColor;
    private int itemStateListAnimatorId;
    private int itemTextAppearanceActive;
    private boolean itemTextAppearanceActiveBoldEnabled;
    private int itemTextAppearanceInactive;
    private final ColorStateList itemTextColorDefault;
    private ColorStateList itemTextColorFromUser;
    private int labelVisibilityMode;
    private ContentResolver mContentResolver;
    j mDummyMenu;
    private boolean mExclusiveCheckable;
    private boolean mHasGroupDivider;
    private boolean mHasOverflowMenu;
    private InternalBtnInfo mInvisibleBtns;
    private int mMaxItemCount;
    NavigationBarItemView mOverflowButton;
    private j mOverflowMenu;
    private ColorDrawable mSBBTextColorDrawable;
    private h mSelectedCallback;
    private int mSeslLabelTextAppearance;
    protected boolean mUseItemPool;
    private int mViewType;
    private int mViewVisibleItemCount;
    private InternalBtnInfo mVisibleBtns;
    private int mVisibleItemCount;
    private j menu;
    private final View.OnClickListener onClickListener;
    private final SparseArray<View.OnTouchListener> onTouchListeners;
    private NavigationBarPresenter presenter;
    private int selectedItemId;
    private int selectedItemPosition;
    private final a0 set;

    public static class InternalBtnInfo {
        int cnt = 0;
        int[] originPos;

        public InternalBtnInfo(int i5) {
            this.originPos = new int[i5];
        }
    }

    public NavigationBarMenuView(Context context) {
        super(context);
        this.itemPool = new f(5);
        this.onTouchListeners = new SparseArray<>(5);
        this.selectedItemId = 0;
        this.selectedItemPosition = 0;
        this.badgeDrawables = new SparseArray<>(5);
        this.itemPaddingTop = -1;
        this.itemPaddingBottom = -1;
        this.itemActiveIndicatorLabelPadding = -1;
        this.itemActiveIndicatorResizeable = false;
        this.itemStateListAnimatorId = 0;
        this.mViewType = 1;
        this.mVisibleBtns = null;
        this.mInvisibleBtns = null;
        this.mOverflowButton = null;
        this.mHasOverflowMenu = false;
        this.mOverflowMenu = null;
        this.mVisibleItemCount = 0;
        this.mViewVisibleItemCount = 0;
        this.mMaxItemCount = 0;
        this.mUseItemPool = true;
        this.mExclusiveCheckable = true;
        this.itemTextColorDefault = createDefaultColorStateList(R.attr.textColorSecondary);
        if (isInEditMode()) {
            this.set = null;
        } else {
            C0374b c0374b = new C0374b();
            this.set = c0374b;
            c0374b.k(0);
            c0374b.i(0L);
            c0374b.f(new TextScale());
        }
        this.onClickListener = new View.OnClickListener() { // from class: com.google.android.material.navigation.NavigationBarMenuView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                l itemData = ((NavigationBarItemView) view).getItemData();
                if (NavigationBarMenuView.this.menu.performItemAction(itemData, NavigationBarMenuView.this.presenter, 0)) {
                    return;
                }
                if (NavigationBarMenuView.this.mExclusiveCheckable) {
                    itemData.setChecked(true);
                } else {
                    itemData.setChecked(!itemData.isChecked());
                }
            }
        };
        this.mContentResolver = context.getContentResolver();
        WeakHashMap weakHashMap = W.f7199a;
        setImportantForAccessibility(1);
    }

    private void buildInternalMenu(boolean z9, int i5) {
        if (this.buttons == null) {
            return;
        }
        if (i5 < 0 || i5 > this.menu.size() || !(this.menu.getItem(i5) instanceof l)) {
            String str = TAG;
            StringBuilder sbE = AbstractC0152g1.e(i5, "position is out of index (pos=", "/size=");
            sbE.append(this.menu.size());
            sbE.append(") or not instance of MenuItemImpl");
            Log.e(str, sbE.toString());
            return;
        }
        l lVar = (l) this.menu.getItem(i5);
        NavigationBarItemView navigationBarItemViewBuildMenu = buildMenu(lVar, z9);
        this.buttons[this.mVisibleItemCount] = navigationBarItemViewBuildMenu;
        navigationBarItemViewBuildMenu.setVisibility(this.menu.getItem(i5).isVisible() ? 0 : 8);
        navigationBarItemViewBuildMenu.setOnClickListener(this.onClickListener);
        if (this.selectedItemId != 0 && this.menu.getItem(i5).getItemId() == this.selectedItemId) {
            this.selectedItemPosition = this.mVisibleItemCount;
        }
        String str2 = lVar.f6268I;
        int i7 = lVar.f6270e;
        if (str2 != null) {
            seslAddBadge(str2, i7);
        } else {
            seslRemoveBadge(i7);
        }
        setBadgeIfNeeded(navigationBarItemViewBuildMenu);
        if (navigationBarItemViewBuildMenu.getParent() instanceof ViewGroup) {
            ((ViewGroup) navigationBarItemViewBuildMenu.getParent()).removeView(navigationBarItemViewBuildMenu);
        }
        addView(navigationBarItemViewBuildMenu);
        this.mVisibleItemCount++;
        if (navigationBarItemViewBuildMenu.getVisibility() == 0) {
            this.mViewVisibleItemCount++;
        }
    }

    private NavigationBarItemView buildMenu(l lVar, boolean z9) {
        NavigationBarItemView newItem = getNewItem(lVar);
        newItem.setIconTintList(this.itemIconTint);
        newItem.setIconSize(this.itemIconSize);
        newItem.setTextColor(this.itemTextColorDefault);
        newItem.seslSetLabelTextAppearance(this.mSeslLabelTextAppearance);
        newItem.setTextAppearanceInactive(this.itemTextAppearanceInactive);
        newItem.setTextAppearanceActive(this.itemTextAppearanceActive);
        newItem.setTextColor(this.itemTextColorFromUser);
        Drawable drawable = this.itemBackground;
        if (drawable != null) {
            newItem.setItemBackground(drawable);
        } else {
            newItem.setItemBackground(this.itemBackgroundRes);
        }
        inflateStateListAnimator(newItem);
        newItem.setShifting(z9);
        newItem.setLabelVisibilityMode(this.labelVisibilityMode);
        newItem.initialize(lVar, 0);
        newItem.setItemPosition(this.mVisibleItemCount);
        return newItem;
    }

    private Drawable createItemActiveIndicatorDrawable() {
        if (this.itemActiveIndicatorShapeAppearance == null || this.itemActiveIndicatorColor == null) {
            return null;
        }
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this.itemActiveIndicatorShapeAppearance);
        materialShapeDrawable.setFillColor(this.itemActiveIndicatorColor);
        return materialShapeDrawable;
    }

    private NavigationBarItemView ensureOverflowButton(boolean z9) {
        this.mHasOverflowMenu = true;
        this.mDummyMenu = new j(getContext());
        new MenuInflater(getContext()).inflate(com.google.android.material.R.menu.nv_dummy_overflow_menu_icon, this.mDummyMenu);
        if (this.mDummyMenu.size() <= 0 || !(this.mDummyMenu.getItem(0) instanceof l)) {
            return null;
        }
        l lVar = (l) this.mDummyMenu.getItem(0);
        if (getViewType() == 1) {
            lVar.setTooltipText((CharSequence) null);
        } else {
            lVar.setTooltipText((CharSequence) getResources().getString(com.samsung.android.keyscafe.R.string.sesl_more_item_label));
        }
        NavigationBarItemView navigationBarItemViewBuildMenu = buildMenu(lVar, z9);
        inflateStateListAnimator(navigationBarItemViewBuildMenu);
        navigationBarItemViewBuildMenu.setBadgeType(0);
        navigationBarItemViewBuildMenu.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.navigation.NavigationBarMenuView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NavigationBarMenuView.this.mOverflowMenu.setCallback(NavigationBarMenuView.this.mSelectedCallback);
                NavigationBarMenuView.this.presenter.showOverflowMenu(NavigationBarMenuView.this.mOverflowMenu);
            }
        });
        navigationBarItemViewBuildMenu.setContentDescription(getResources().getString(com.samsung.android.keyscafe.R.string.sesl_action_menu_overflow_description));
        if (getViewType() == 3) {
            initOverflowSpan(navigationBarItemViewBuildMenu);
        }
        if (navigationBarItemViewBuildMenu.getParent() instanceof ViewGroup) {
            ((ViewGroup) navigationBarItemViewBuildMenu.getParent()).removeView(navigationBarItemViewBuildMenu);
        }
        addView(navigationBarItemViewBuildMenu);
        return navigationBarItemViewBuildMenu;
    }

    private NavigationBarItemView getNewItem(final l lVar) {
        NavigationBarItemView navigationBarItemView = (NavigationBarItemView) this.itemPool.h();
        if (navigationBarItemView != null) {
            return navigationBarItemView;
        }
        final int viewType = getViewType();
        return new NavigationBarItemView(getContext(), viewType) { // from class: com.google.android.material.navigation.NavigationBarMenuView.2
            @Override // com.google.android.material.navigation.NavigationBarItemView
            public int getItemLayoutResId() {
                if (lVar.f6269J == 1) {
                    return com.google.android.material.R.layout.sesl_bottom_navigation_item_checkbox;
                }
                int i5 = viewType;
                return (i5 == 1 || i5 == 2) ? com.google.android.material.R.layout.sesl_bottom_navigation_item : i5 != 3 ? com.google.android.material.R.layout.sesl_bottom_navigation_item : com.google.android.material.R.layout.sesl_bottom_navigation_item_text;
            }

            @Override // com.google.android.material.navigation.NavigationBarItemView, androidx.appcompat.view.menu.x
            public void initialize(l lVar2, int i5) {
                super.initialize(lVar2, i5);
                lVar2.i(NavigationBarMenuView.this.mExclusiveCheckable);
            }
        };
    }

    private void inflateStateListAnimator(NavigationBarItemView navigationBarItemView) {
        if (this.itemStateListAnimatorId != 0) {
            navigationBarItemView.setStateListAnimator(AnimatorInflater.loadStateListAnimator(getContext(), this.itemStateListAnimatorId));
        }
    }

    private void initOverflowSpan(NavigationBarItemView navigationBarItemView) {
        Drawable drawable = getContext().getDrawable(com.samsung.android.keyscafe.R.drawable.sesl_ic_menu_overflow_dark);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(" ");
        ImageSpan imageSpan = new ImageSpan(drawable);
        drawable.setState(new int[]{R.attr.state_enabled, -16842910});
        drawable.setTintList(this.itemTextColorFromUser);
        Resources resources = getResources();
        int i5 = com.google.android.material.R.dimen.sesl_bottom_navigation_icon_size;
        drawable.setBounds(0, 0, resources.getDimensionPixelSize(i5), getResources().getDimensionPixelSize(i5));
        spannableStringBuilder.setSpan(imageSpan, 0, 1, 18);
        navigationBarItemView.setLabelImageSpan(spannableStringBuilder);
    }

    private boolean isNumericValue(String str) {
        if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private boolean isShowButtonShapesEnabled() {
        return Settings.System.getInt(this.mContentResolver, "show_button_background", 0) == 1;
    }

    private boolean isValidId(int i5) {
        return i5 != -1;
    }

    private void removeUnusedBadges() {
        HashSet hashSet = new HashSet();
        for (int i5 = 0; i5 < this.menu.size(); i5++) {
            hashSet.add(Integer.valueOf(this.menu.getItem(i5).getItemId()));
        }
        for (int i7 = 0; i7 < this.badgeDrawables.size(); i7++) {
            int iKeyAt = this.badgeDrawables.keyAt(i7);
            if (!hashSet.contains(Integer.valueOf(iKeyAt))) {
                this.badgeDrawables.delete(iKeyAt);
            }
        }
    }

    private void seslCheckMaxFontScale(TextView textView, int i5) {
        float f2 = getResources().getConfiguration().fontScale;
        if (f2 > 1.2f) {
            textView.setTextSize(0, (i5 / f2) * 1.2f);
        }
    }

    private void setBadgeIfNeeded(NavigationBarItemView navigationBarItemView) {
        BadgeDrawable badgeDrawable;
        int id = navigationBarItemView.getId();
        if (isValidId(id) && (badgeDrawable = this.badgeDrawables.get(id)) != null) {
            navigationBarItemView.setBadge(badgeDrawable);
        }
    }

    private void setOverflowSpanColor(int i5, boolean z9) {
        SpannableStringBuilder labelImageSpan;
        NavigationBarItemView navigationBarItemView = this.mOverflowButton;
        if (navigationBarItemView == null || (labelImageSpan = navigationBarItemView.getLabelImageSpan()) == null) {
            return;
        }
        Drawable drawable = getContext().getDrawable(com.samsung.android.keyscafe.R.drawable.sesl_ic_menu_overflow_dark);
        ImageSpan[] imageSpanArr = (ImageSpan[]) labelImageSpan.getSpans(0, labelImageSpan.length(), ImageSpan.class);
        if (imageSpanArr != null) {
            for (ImageSpan imageSpan : imageSpanArr) {
                labelImageSpan.removeSpan(imageSpan);
            }
        }
        ImageSpan imageSpan2 = new ImageSpan(drawable);
        drawable.setState(new int[]{R.attr.state_enabled, -16842910});
        if (z9) {
            drawable.setTintList(this.itemTextColorFromUser);
        } else {
            drawable.setTint(i5);
        }
        Resources resources = getResources();
        int i7 = com.google.android.material.R.dimen.sesl_bottom_navigation_icon_size;
        drawable.setBounds(0, 0, resources.getDimensionPixelSize(i7), getResources().getDimensionPixelSize(i7));
        labelImageSpan.setSpan(imageSpan2, 0, 1, 18);
        this.mOverflowButton.setLabelImageSpan(labelImageSpan);
    }

    private void setShowButtonShape(NavigationBarItemView navigationBarItemView) {
        int color;
        l itemData;
        j jVar;
        if (navigationBarItemView == null) {
            return;
        }
        ColorStateList itemTextColor = getItemTextColor();
        if (isShowButtonShapesEnabled()) {
            ColorDrawable colorDrawable = this.mSBBTextColorDrawable;
            if (colorDrawable != null) {
                color = colorDrawable.getColor();
            } else {
                color = getResources().getColor(c.O(getContext()) ? com.google.android.material.R.color.sesl_bottom_navigation_background_light : com.google.android.material.R.color.sesl_bottom_navigation_background_dark, null);
            }
            navigationBarItemView.setShowButtonShape(color, itemTextColor);
            if (this.mOverflowButton == null || (itemData = navigationBarItemView.getItemData()) == null || (jVar = this.mDummyMenu) == null) {
                return;
            }
            if (itemData.f6270e == jVar.getItem(0).getItemId()) {
                setOverflowSpanColor(color, false);
            }
        }
    }

    private void updateBadge(NavigationBarItemView navigationBarItemView) {
        TextView textView;
        int measuredWidth;
        int measuredHeight;
        int measuredWidth2;
        if (navigationBarItemView == null || (textView = (TextView) navigationBarItemView.findViewById(com.google.android.material.R.id.notifications_badge)) == null) {
            return;
        }
        Resources resources = getResources();
        seslCheckMaxFontScale(textView, resources.getDimensionPixelSize(com.google.android.material.R.dimen.sesl_navigation_bar_num_badge_size));
        int badgeType = navigationBarItemView.getBadgeType();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(com.google.android.material.R.dimen.sesl_bottom_navigation_dot_badge_size);
        int dimensionPixelSize = this.mVisibleItemCount == this.mMaxItemCount ? resources.getDimensionPixelSize(com.google.android.material.R.dimen.sesl_bottom_navigation_icon_mode_min_padding_horizontal) : resources.getDimensionPixelSize(com.google.android.material.R.dimen.sesl_bottom_navigation_icon_mode_padding_horizontal);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(com.google.android.material.R.dimen.sesl_bottom_navigation_N_badge_top_margin);
        int dimensionPixelSize3 = resources.getDimensionPixelSize(com.google.android.material.R.dimen.sesl_bottom_navigation_N_badge_start_margin);
        TextView label = navigationBarItemView.getLabel();
        int width = label == null ? 1 : label.getWidth();
        int height = label == null ? 1 : label.getHeight();
        if (badgeType == 1 || badgeType == 0) {
            Drawable drawable = resources.getDrawable(com.google.android.material.R.drawable.sesl_dot_badge);
            WeakHashMap weakHashMap = W.f7199a;
            textView.setBackground(drawable);
            measuredWidth = dimensionPixelOffset;
            measuredHeight = measuredWidth;
        } else {
            Drawable drawable2 = resources.getDrawable(com.google.android.material.R.drawable.sesl_tab_n_badge);
            WeakHashMap weakHashMap2 = W.f7199a;
            textView.setBackground(drawable2);
            textView.measure(0, 0);
            measuredWidth = textView.getMeasuredWidth();
            measuredHeight = textView.getMeasuredHeight();
        }
        if (getViewType() != 3) {
            if (badgeType == 1) {
                measuredWidth2 = getItemIconSize() / 2;
            } else {
                measuredWidth2 = (textView.getMeasuredWidth() / 2) - dimensionPixelSize;
                dimensionPixelOffset /= 2;
            }
        } else if (badgeType == 1) {
            measuredWidth2 = (textView.getMeasuredWidth() + width) / 2;
            dimensionPixelOffset = (navigationBarItemView.getHeight() - height) / 2;
        } else if (badgeType == 0) {
            measuredWidth2 = ((width - textView.getMeasuredWidth()) - dimensionPixelSize3) / 2;
            dimensionPixelOffset = ((navigationBarItemView.getHeight() - height) / 2) - dimensionPixelSize2;
        } else {
            measuredWidth2 = (textView.getMeasuredWidth() + width) / 2;
            dimensionPixelOffset = ((navigationBarItemView.getHeight() - height) / 2) - dimensionPixelSize2;
            if ((textView.getMeasuredWidth() / 2) + (navigationBarItemView.getWidth() / 2) + measuredWidth2 > navigationBarItemView.getWidth()) {
                measuredWidth2 += navigationBarItemView.getWidth() - ((textView.getMeasuredWidth() / 2) + ((navigationBarItemView.getWidth() / 2) + measuredWidth2));
            }
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) textView.getLayoutParams();
        int i5 = layoutParams.width;
        int i7 = layoutParams.leftMargin;
        if (i5 == measuredWidth && i7 == measuredWidth2) {
            return;
        }
        layoutParams.width = measuredWidth;
        layoutParams.height = measuredHeight;
        layoutParams.topMargin = dimensionPixelOffset;
        layoutParams.setMarginStart(measuredWidth2);
        textView.setLayoutParams(layoutParams);
    }

    private void validateMenuItemId(int i5) {
        if (isValidId(i5)) {
            return;
        }
        throw new IllegalArgumentException(i5 + " is not a valid view id");
    }

    /* JADX WARN: Type inference failed for: r0v32 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9, types: [boolean, int] */
    @SuppressLint({"ClickableViewAccessibility"})
    public void buildMenuView() {
        int i5;
        removeAllViews();
        Y.a(this, this.set);
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        int i7 = 0;
        if (navigationBarItemViewArr != null && this.mUseItemPool) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView != null) {
                    seslRemoveBadge(navigationBarItemView.getId());
                    this.itemPool.c(navigationBarItemView);
                    navigationBarItemView.clear();
                }
            }
        }
        if (this.mOverflowButton != null) {
            seslRemoveBadge(com.google.android.material.R.id.bottom_overflow);
        }
        int size = this.menu.size();
        if (size == 0) {
            this.selectedItemId = 0;
            this.selectedItemPosition = 0;
            this.buttons = null;
            this.mVisibleItemCount = 0;
            this.mOverflowButton = null;
            this.mOverflowMenu = null;
            this.mVisibleBtns = null;
            this.mInvisibleBtns = null;
            return;
        }
        removeUnusedBadges();
        boolean zIsShifting = isShifting(this.labelVisibilityMode, this.menu.getVisibleItems().size());
        this.buttons = new NavigationBarItemView[this.menu.size()];
        this.mVisibleBtns = new InternalBtnInfo(size);
        this.mInvisibleBtns = new InternalBtnInfo(size);
        this.mOverflowMenu = new j(getContext());
        this.mVisibleBtns.cnt = 0;
        this.mInvisibleBtns.cnt = 0;
        int i9 = 0;
        int i10 = 0;
        for (int i11 = 0; i11 < size; i11++) {
            this.presenter.setUpdateSuspended(true);
            this.menu.getItem(i11).setCheckable(true);
            this.presenter.setUpdateSuspended(false);
            int i12 = ((l) this.menu.getItem(i11)).f6263C;
            if ((i12 & 2) == 2 || (i12 & 1) == 1) {
                InternalBtnInfo internalBtnInfo = this.mVisibleBtns;
                int[] iArr = internalBtnInfo.originPos;
                int i13 = internalBtnInfo.cnt;
                internalBtnInfo.cnt = i13 + 1;
                iArr[i13] = i11;
                if (this.menu.getItem(i11).isVisible()) {
                    i10++;
                }
            } else {
                InternalBtnInfo internalBtnInfo2 = this.mInvisibleBtns;
                int[] iArr2 = internalBtnInfo2.originPos;
                int i14 = internalBtnInfo2.cnt;
                internalBtnInfo2.cnt = i14 + 1;
                iArr2[i14] = i11;
                if (!this.menu.getItem(i11).isVisible()) {
                    i9++;
                }
            }
        }
        ?? r02 = this.mInvisibleBtns.cnt - i9 > 0 ? 1 : 0;
        this.mHasOverflowMenu = r02;
        int i15 = i10 + r02;
        int i16 = this.mMaxItemCount;
        if (i15 > i16) {
            int i17 = i15 - (i16 - 1);
            if (r02 != 0) {
                i17--;
            }
            for (int i18 = this.mVisibleBtns.cnt - 1; i18 >= 0; i18--) {
                if (this.menu.getItem(this.mVisibleBtns.originPos[i18]).isVisible()) {
                    InternalBtnInfo internalBtnInfo3 = this.mInvisibleBtns;
                    int[] iArr3 = internalBtnInfo3.originPos;
                    int i19 = internalBtnInfo3.cnt;
                    internalBtnInfo3.cnt = i19 + 1;
                    InternalBtnInfo internalBtnInfo4 = this.mVisibleBtns;
                    iArr3[i19] = internalBtnInfo4.originPos[i18];
                    internalBtnInfo4.cnt--;
                    i17--;
                    if (i17 == 0) {
                        break;
                    }
                } else {
                    InternalBtnInfo internalBtnInfo5 = this.mInvisibleBtns;
                    int[] iArr4 = internalBtnInfo5.originPos;
                    int i20 = internalBtnInfo5.cnt;
                    internalBtnInfo5.cnt = i20 + 1;
                    InternalBtnInfo internalBtnInfo6 = this.mVisibleBtns;
                    iArr4[i20] = internalBtnInfo6.originPos[i18];
                    internalBtnInfo6.cnt--;
                }
            }
        }
        this.mVisibleItemCount = 0;
        this.mViewVisibleItemCount = 0;
        int i21 = 0;
        while (true) {
            InternalBtnInfo internalBtnInfo7 = this.mVisibleBtns;
            if (i21 >= internalBtnInfo7.cnt) {
                break;
            }
            buildInternalMenu(zIsShifting, internalBtnInfo7.originPos[i21]);
            i21++;
        }
        if (this.mInvisibleBtns.cnt > 0) {
            int i22 = 0;
            int i23 = 0;
            while (true) {
                InternalBtnInfo internalBtnInfo8 = this.mInvisibleBtns;
                i5 = internalBtnInfo8.cnt;
                if (i22 >= i5) {
                    break;
                }
                l lVar = (l) this.menu.getItem(internalBtnInfo8.originPos[i22]);
                if (lVar != null) {
                    CharSequence charSequence = lVar.f6273i;
                    if (charSequence == null) {
                        charSequence = lVar.f6284u;
                    }
                    this.mOverflowMenu.add(lVar.f6271f, lVar.f6270e, lVar.f6272g, charSequence).setVisible(lVar.isVisible()).setEnabled(lVar.isEnabled());
                    this.mOverflowMenu.setGroupDividerEnabled(this.mHasGroupDivider);
                    String str = lVar.f6268I;
                    if (str == null || !str.equals(str)) {
                        lVar.f6268I = str;
                        lVar.f6281r.onItemsChanged(false);
                    }
                    if (!lVar.isVisible()) {
                        i23++;
                    }
                }
                i22++;
            }
            if (i5 - i23 > 0) {
                NavigationBarItemView navigationBarItemViewEnsureOverflowButton = ensureOverflowButton(zIsShifting);
                this.mOverflowButton = navigationBarItemViewEnsureOverflowButton;
                this.buttons[this.mVisibleBtns.cnt] = navigationBarItemViewEnsureOverflowButton;
                this.mVisibleItemCount++;
                this.mViewVisibleItemCount++;
                navigationBarItemViewEnsureOverflowButton.setVisibility(0);
            }
        }
        if (this.mViewVisibleItemCount > this.mMaxItemCount) {
            Log.i(TAG, "Maximum number of visible items supported by BottomNavigationView is " + this.mMaxItemCount + ". Current visible count is " + this.mViewVisibleItemCount);
            int i24 = this.mMaxItemCount;
            this.mVisibleItemCount = i24;
            this.mViewVisibleItemCount = i24;
        }
        while (true) {
            NavigationBarItemView[] navigationBarItemViewArr2 = this.buttons;
            if (i7 >= navigationBarItemViewArr2.length) {
                int iMin = Math.min(this.mMaxItemCount - 1, this.selectedItemPosition);
                this.selectedItemPosition = iMin;
                this.menu.getItem(iMin).setChecked(true);
                return;
            }
            setShowButtonShape(navigationBarItemViewArr2[i7]);
            i7++;
        }
    }

    public ColorStateList createDefaultColorStateList(int i5) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i5, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateListF = a.f(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(com.samsung.android.keyscafe.R.attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i7 = typedValue.data;
        int defaultColor = colorStateListF.getDefaultColor();
        int[] iArr = DISABLED_STATE_SET;
        return new ColorStateList(new int[][]{iArr, CHECKED_STATE_SET, ViewGroup.EMPTY_STATE_SET}, new int[]{colorStateListF.getColorForState(iArr, defaultColor), i7, defaultColor});
    }

    public abstract NavigationBarItemView createNavigationBarItemView(Context context);

    public NavigationBarItemView findItemView(int i5) {
        validateMenuItemId(i5);
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr == null) {
            return null;
        }
        for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
            if (navigationBarItemView == null) {
                return null;
            }
            if (navigationBarItemView.getId() == i5) {
                return navigationBarItemView;
            }
        }
        return null;
    }

    public int getActiveIndicatorLabelPadding() {
        return this.itemActiveIndicatorLabelPadding;
    }

    public ColorDrawable getBackgroundColorDrawable() {
        return this.mSBBTextColorDrawable;
    }

    public BadgeDrawable getBadge(int i5) {
        return this.badgeDrawables.get(i5);
    }

    public SparseArray<BadgeDrawable> getBadgeDrawables() {
        return this.badgeDrawables;
    }

    public ColorStateList getIconTintList() {
        return this.itemIconTint;
    }

    public ColorStateList getItemActiveIndicatorColor() {
        return this.itemActiveIndicatorColor;
    }

    public boolean getItemActiveIndicatorEnabled() {
        return this.itemActiveIndicatorEnabled;
    }

    public int getItemActiveIndicatorHeight() {
        return this.itemActiveIndicatorHeight;
    }

    public int getItemActiveIndicatorMarginHorizontal() {
        return this.itemActiveIndicatorMarginHorizontal;
    }

    public ShapeAppearanceModel getItemActiveIndicatorShapeAppearance() {
        return this.itemActiveIndicatorShapeAppearance;
    }

    public int getItemActiveIndicatorWidth() {
        return this.itemActiveIndicatorWidth;
    }

    public Drawable getItemBackground() {
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        return (navigationBarItemViewArr == null || navigationBarItemViewArr.length <= 0) ? this.itemBackground : navigationBarItemViewArr[0].getBackground();
    }

    @Deprecated
    public int getItemBackgroundRes() {
        return this.itemBackgroundRes;
    }

    public int getItemIconSize() {
        return this.itemIconSize;
    }

    public int getItemPaddingBottom() {
        return this.itemPaddingBottom;
    }

    public int getItemPaddingTop() {
        return this.itemPaddingTop;
    }

    public ColorStateList getItemRippleColor() {
        return this.itemRippleColor;
    }

    public int getItemTextAppearanceActive() {
        return this.itemTextAppearanceActive;
    }

    public int getItemTextAppearanceInactive() {
        return this.itemTextAppearanceInactive;
    }

    public ColorStateList getItemTextColor() {
        return this.itemTextColorFromUser;
    }

    public int getLabelVisibilityMode() {
        return this.labelVisibilityMode;
    }

    public j getMenu() {
        return this.menu;
    }

    public BadgeDrawable getOrCreateBadge(int i5) {
        validateMenuItemId(i5);
        BadgeDrawable badgeDrawableCreate = this.badgeDrawables.get(i5);
        if (badgeDrawableCreate == null) {
            badgeDrawableCreate = BadgeDrawable.create(getContext());
            this.badgeDrawables.put(i5, badgeDrawableCreate);
        }
        NavigationBarItemView navigationBarItemViewFindItemView = findItemView(i5);
        if (navigationBarItemViewFindItemView != null) {
            navigationBarItemViewFindItemView.setBadge(badgeDrawableCreate);
        }
        return badgeDrawableCreate;
    }

    public j getOverflowMenu() {
        return this.mOverflowMenu;
    }

    public int getSelectedItemId() {
        return this.selectedItemId;
    }

    public int getSelectedItemPosition() {
        return this.selectedItemPosition;
    }

    public int getViewType() {
        return this.mViewType;
    }

    public int getViewVisibleItemCount() {
        return this.mViewVisibleItemCount;
    }

    public int getVisibleItemCount() {
        return this.mVisibleItemCount;
    }

    public int getWindowAnimations() {
        return 0;
    }

    public boolean hasOverflowButton() {
        return this.mHasOverflowMenu;
    }

    public void hideOverflowMenu() {
        NavigationBarPresenter navigationBarPresenter;
        if (hasOverflowButton() && (navigationBarPresenter = this.presenter) != null && navigationBarPresenter.isOverflowMenuShowing()) {
            this.presenter.hideOverflowMenu();
        }
    }

    @Override // androidx.appcompat.view.menu.y
    public void initialize(j jVar) {
        this.menu = jVar;
    }

    public boolean isItemActiveIndicatorResizeable() {
        return this.itemActiveIndicatorResizeable;
    }

    public boolean isShifting(int i5, int i7) {
        return i5 == 0;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (getViewType() != 3) {
            setItemIconSize(getResources().getDimensionPixelSize(com.google.android.material.R.dimen.sesl_bottom_navigation_icon_size));
            NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
            if (navigationBarItemViewArr != null) {
                for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                    if (navigationBarItemView == null) {
                        break;
                    }
                    navigationBarItemView.updateLabelGroupTopMargin(getResources().getDimensionPixelSize(com.google.android.material.R.dimen.sesl_bottom_navigation_icon_size));
                }
            }
        }
        hideOverflowMenu();
    }

    public void removeBadge(int i5) {
        validateMenuItemId(i5);
        NavigationBarItemView navigationBarItemViewFindItemView = findItemView(i5);
        if (navigationBarItemViewFindItemView != null) {
            navigationBarItemViewFindItemView.removeBadge();
        }
        this.badgeDrawables.put(i5, null);
    }

    public void restoreBadgeDrawables(SparseArray<BadgeDrawable> sparseArray) {
        BadgeDrawable badgeDrawable;
        for (int i5 = 0; i5 < sparseArray.size(); i5++) {
            int iKeyAt = sparseArray.keyAt(i5);
            if (this.badgeDrawables.indexOfKey(iKeyAt) < 0) {
                this.badgeDrawables.append(iKeyAt, sparseArray.get(iKeyAt));
            }
        }
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView != null && (badgeDrawable = this.badgeDrawables.get(navigationBarItemView.getId())) != null) {
                    navigationBarItemView.setBadge(badgeDrawable);
                }
            }
        }
    }

    public void seslAddBadge(String str, int i5) {
        TextView textView;
        NavigationBarItemView navigationBarItemViewFindItemView = findItemView(i5);
        if (navigationBarItemViewFindItemView != null) {
            View viewFindViewById = navigationBarItemViewFindItemView.findViewById(com.google.android.material.R.id.notifications_badge_container);
            if (viewFindViewById != null) {
                textView = (TextView) viewFindViewById.findViewById(com.google.android.material.R.id.notifications_badge);
            } else {
                View viewInflate = LayoutInflater.from(getContext()).inflate(com.google.android.material.R.layout.sesl_navigation_bar_badge_layout, (ViewGroup) this, false);
                TextView textView2 = (TextView) viewInflate.findViewById(com.google.android.material.R.id.notifications_badge);
                navigationBarItemViewFindItemView.addView(viewInflate);
                textView = textView2;
            }
            if (!isNumericValue(str) || Integer.parseInt(str) <= 999) {
                navigationBarItemViewFindItemView.setBadgeNumberless(false);
            } else {
                navigationBarItemViewFindItemView.setBadgeNumberless(true);
                str = "999+";
            }
        } else {
            textView = null;
        }
        if (textView != null) {
            textView.setText(str);
        }
        updateBadge(navigationBarItemViewFindItemView);
    }

    public int seslGetLabelTextAppearance() {
        return this.mSeslLabelTextAppearance;
    }

    public void seslRemoveBadge(int i5) {
        View viewFindViewById;
        NavigationBarItemView navigationBarItemViewFindItemView = findItemView(i5);
        if (navigationBarItemViewFindItemView == null || (viewFindViewById = navigationBarItemViewFindItemView.findViewById(com.google.android.material.R.id.notifications_badge_container)) == null) {
            return;
        }
        navigationBarItemViewFindItemView.removeView(viewFindViewById);
    }

    public void seslSetLabelTextAppearance(int i5) {
        this.mSeslLabelTextAppearance = i5;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView == null) {
                    break;
                }
                navigationBarItemView.setTextAppearanceInactive(i5);
                ColorStateList colorStateList = this.itemTextColorFromUser;
                if (colorStateList != null) {
                    navigationBarItemView.setTextColor(colorStateList);
                }
            }
        }
        NavigationBarItemView navigationBarItemView2 = this.mOverflowButton;
        if (navigationBarItemView2 != null) {
            navigationBarItemView2.setTextAppearanceInactive(i5);
            ColorStateList colorStateList2 = this.itemTextColorFromUser;
            if (colorStateList2 != null) {
                this.mOverflowButton.setTextColor(colorStateList2);
            }
        }
    }

    public void setActiveIndicatorLabelPadding(int i5) {
        this.itemActiveIndicatorLabelPadding = i5;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorLabelPadding(i5);
            }
        }
    }

    public void setBackgroundColorDrawable(ColorDrawable colorDrawable) {
        this.mSBBTextColorDrawable = colorDrawable;
    }

    public void setExclusiveCheckable(boolean z9) {
        this.mExclusiveCheckable = z9;
    }

    public void setGroupDividerEnabled(boolean z9) {
        this.mHasGroupDivider = z9;
        j jVar = this.mOverflowMenu;
        if (jVar != null) {
            jVar.setGroupDividerEnabled(z9);
        } else {
            updateMenuView();
        }
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.itemIconTint = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView == null) {
                    break;
                }
                navigationBarItemView.setIconTintList(colorStateList);
            }
        }
        NavigationBarItemView navigationBarItemView2 = this.mOverflowButton;
        if (navigationBarItemView2 != null) {
            navigationBarItemView2.setIconTintList(colorStateList);
        }
    }

    public void setItemActiveIndicatorColor(ColorStateList colorStateList) {
        this.itemActiveIndicatorColor = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorDrawable(createItemActiveIndicatorDrawable());
            }
        }
    }

    public void setItemActiveIndicatorEnabled(boolean z9) {
        this.itemActiveIndicatorEnabled = z9;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorEnabled(z9);
            }
        }
    }

    public void setItemActiveIndicatorHeight(int i5) {
        this.itemActiveIndicatorHeight = i5;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorHeight(i5);
            }
        }
    }

    public void setItemActiveIndicatorMarginHorizontal(int i5) {
        this.itemActiveIndicatorMarginHorizontal = i5;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorMarginHorizontal(i5);
            }
        }
    }

    public void setItemActiveIndicatorResizeable(boolean z9) {
        this.itemActiveIndicatorResizeable = z9;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorResizeable(z9);
            }
        }
    }

    public void setItemActiveIndicatorShapeAppearance(ShapeAppearanceModel shapeAppearanceModel) {
        this.itemActiveIndicatorShapeAppearance = shapeAppearanceModel;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorDrawable(createItemActiveIndicatorDrawable());
            }
        }
    }

    public void setItemActiveIndicatorWidth(int i5) {
        this.itemActiveIndicatorWidth = i5;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setActiveIndicatorWidth(i5);
            }
        }
    }

    public void setItemBackground(Drawable drawable) {
        this.itemBackground = drawable;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView == null) {
                    break;
                }
                navigationBarItemView.setItemBackground(drawable);
            }
        }
        NavigationBarItemView navigationBarItemView2 = this.mOverflowButton;
        if (navigationBarItemView2 != null) {
            navigationBarItemView2.setItemBackground(drawable);
        }
    }

    public void setItemBackgroundRes(int i5) {
        this.itemBackgroundRes = i5;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView == null) {
                    break;
                }
                navigationBarItemView.setItemBackground(i5);
            }
        }
        NavigationBarItemView navigationBarItemView2 = this.mOverflowButton;
        if (navigationBarItemView2 != null) {
            navigationBarItemView2.setItemBackground(i5);
        }
    }

    public void setItemIconSize(int i5) {
        this.itemIconSize = i5;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView == null) {
                    break;
                }
                navigationBarItemView.setIconSize(i5);
            }
        }
        NavigationBarItemView navigationBarItemView2 = this.mOverflowButton;
        if (navigationBarItemView2 != null) {
            navigationBarItemView2.setIconSize(i5);
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public void setItemOnTouchListener(int i5, View.OnTouchListener onTouchListener) {
        if (onTouchListener == null) {
            this.onTouchListeners.remove(i5);
        } else {
            this.onTouchListeners.put(i5, onTouchListener);
        }
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView == null) {
                    return;
                }
                if (navigationBarItemView.getItemData().f6270e == i5) {
                    navigationBarItemView.setOnTouchListener(onTouchListener);
                }
            }
        }
    }

    public void setItemPaddingBottom(int i5) {
        this.itemPaddingBottom = i5;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemPaddingBottom(i5);
            }
        }
    }

    public void setItemPaddingTop(int i5) {
        this.itemPaddingTop = i5;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemPaddingTop(i5);
            }
        }
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        this.itemRippleColor = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setItemRippleColor(colorStateList);
            }
        }
    }

    public void setItemStateListAnimator(int i5) {
        this.itemStateListAnimatorId = i5;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView == null) {
                    break;
                }
                inflateStateListAnimator(navigationBarItemView);
            }
        }
        NavigationBarItemView navigationBarItemView2 = this.mOverflowButton;
        if (navigationBarItemView2 != null) {
            inflateStateListAnimator(navigationBarItemView2);
        }
    }

    public void setItemTextAppearanceActive(int i5) {
        this.itemTextAppearanceActive = i5;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView == null) {
                    break;
                }
                navigationBarItemView.setTextAppearanceActive(i5);
                ColorStateList colorStateList = this.itemTextColorFromUser;
                if (colorStateList != null) {
                    navigationBarItemView.setTextColor(colorStateList);
                }
            }
        }
        NavigationBarItemView navigationBarItemView2 = this.mOverflowButton;
        if (navigationBarItemView2 == null || this.itemTextColorFromUser == null) {
            return;
        }
        navigationBarItemView2.setTextAppearanceActive(i5);
        this.mOverflowButton.setTextColor(this.itemTextColorFromUser);
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z9) {
        this.itemTextAppearanceActiveBoldEnabled = z9;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                navigationBarItemView.setTextAppearanceActiveBoldEnabled(z9);
            }
        }
    }

    public void setItemTextAppearanceInactive(int i5) {
        this.itemTextAppearanceInactive = i5;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView == null) {
                    break;
                }
                navigationBarItemView.setTextAppearanceInactive(i5);
                ColorStateList colorStateList = this.itemTextColorFromUser;
                if (colorStateList != null) {
                    navigationBarItemView.setTextColor(colorStateList);
                }
            }
        }
        NavigationBarItemView navigationBarItemView2 = this.mOverflowButton;
        if (navigationBarItemView2 != null) {
            navigationBarItemView2.setTextAppearanceInactive(i5);
            ColorStateList colorStateList2 = this.itemTextColorFromUser;
            if (colorStateList2 != null) {
                this.mOverflowButton.setTextColor(colorStateList2);
            }
        }
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.itemTextColorFromUser = colorStateList;
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView == null) {
                    break;
                }
                navigationBarItemView.setTextColor(colorStateList);
            }
        }
        NavigationBarItemView navigationBarItemView2 = this.mOverflowButton;
        if (navigationBarItemView2 != null) {
            navigationBarItemView2.setTextColor(colorStateList);
            setOverflowSpanColor(0, true);
        }
    }

    public void setLabelVisibilityMode(int i5) {
        this.labelVisibilityMode = i5;
    }

    public void setMaxItemCount(int i5) {
        this.mMaxItemCount = i5;
    }

    public void setOverflowSelectedCallback(h hVar) {
        this.mSelectedCallback = hVar;
    }

    public void setPresenter(NavigationBarPresenter navigationBarPresenter) {
        this.presenter = navigationBarPresenter;
    }

    public void setViewType(int i5) {
        this.mViewType = i5;
    }

    public void showOverflowMenu() {
        NavigationBarPresenter navigationBarPresenter;
        if (!hasOverflowButton() || (navigationBarPresenter = this.presenter) == null) {
            return;
        }
        navigationBarPresenter.showOverflowMenu(this.mOverflowMenu);
    }

    public void tryRestoreSelectedItemId(int i5) {
        int size = this.menu.size();
        for (int i7 = 0; i7 < size; i7++) {
            MenuItem item = this.menu.getItem(i7);
            if (i5 == item.getItemId()) {
                this.selectedItemId = i5;
                this.selectedItemPosition = i7;
                item.setChecked(true);
                return;
            }
        }
    }

    public void updateBadgeIfNeeded() {
        NavigationBarItemView[] navigationBarItemViewArr = this.buttons;
        if (navigationBarItemViewArr != null) {
            for (NavigationBarItemView navigationBarItemView : navigationBarItemViewArr) {
                if (navigationBarItemView == null) {
                    return;
                }
                updateBadge(navigationBarItemView);
            }
        }
    }

    public void updateMenuView() {
        j jVar;
        a0 a0Var;
        j jVar2 = this.menu;
        if (jVar2 == null || this.buttons == null || this.mVisibleBtns == null || this.mInvisibleBtns == null) {
            return;
        }
        int size = jVar2.size();
        hideOverflowMenu();
        if (size != this.mVisibleBtns.cnt + this.mInvisibleBtns.cnt) {
            buildMenuView();
            return;
        }
        int i5 = this.selectedItemId;
        int i7 = 0;
        while (true) {
            InternalBtnInfo internalBtnInfo = this.mVisibleBtns;
            if (i7 >= internalBtnInfo.cnt) {
                break;
            }
            MenuItem item = this.menu.getItem(internalBtnInfo.originPos[i7]);
            if (item.isChecked()) {
                this.selectedItemId = item.getItemId();
                this.selectedItemPosition = i7;
            }
            if (item instanceof l) {
                seslRemoveBadge(item.getItemId());
                String str = ((l) item).f6268I;
                if (str != null) {
                    seslAddBadge(str, item.getItemId());
                }
            }
            i7++;
        }
        if (i5 != this.selectedItemId && (a0Var = this.set) != null) {
            Y.a(this, a0Var);
        }
        boolean zIsShifting = isShifting(this.labelVisibilityMode, this.menu.getVisibleItems().size());
        for (int i9 = 0; i9 < this.mVisibleBtns.cnt; i9++) {
            this.presenter.setUpdateSuspended(true);
            this.buttons[i9].setLabelVisibilityMode(this.labelVisibilityMode);
            this.buttons[i9].setShifting(zIsShifting);
            this.buttons[i9].initialize((l) this.menu.getItem(this.mVisibleBtns.originPos[i9]), 0);
            this.presenter.setUpdateSuspended(false);
        }
        int i10 = 0;
        boolean z9 = false;
        while (true) {
            InternalBtnInfo internalBtnInfo2 = this.mInvisibleBtns;
            if (i10 >= internalBtnInfo2.cnt) {
                break;
            }
            MenuItem item2 = this.menu.getItem(internalBtnInfo2.originPos[i10]);
            if ((item2 instanceof l) && (jVar = this.mOverflowMenu) != null) {
                l lVar = (l) item2;
                l lVar2 = (l) item2;
                MenuItem menuItemFindItem = jVar.findItem(lVar2.f6270e);
                if (menuItemFindItem instanceof l) {
                    ((l) menuItemFindItem).setTitle(lVar2.f6273i);
                    l lVar3 = (l) menuItemFindItem;
                    String str2 = lVar.f6268I;
                    String str3 = lVar3.f6268I;
                    if (str3 == null || !str3.equals(str2)) {
                        lVar3.f6268I = str2;
                        lVar3.f6281r.onItemsChanged(false);
                    }
                }
                z9 |= lVar.f6268I != null;
            }
            i10++;
        }
        if (z9) {
            seslAddBadge("", com.google.android.material.R.id.bottom_overflow);
        } else {
            seslRemoveBadge(com.google.android.material.R.id.bottom_overflow);
        }
    }

    private NavigationBarItemView getNewItem() {
        NavigationBarItemView navigationBarItemView = (NavigationBarItemView) this.itemPool.h();
        return navigationBarItemView == null ? createNavigationBarItemView(getContext()) : navigationBarItemView;
    }
}
