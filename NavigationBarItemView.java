package com.google.android.material.navigation;

import E.a;
import L.f;
import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.graphics.drawable.SeslRecoilDrawable;
import androidx.appcompat.view.menu.l;
import androidx.appcompat.view.menu.x;
import androidx.appcompat.widget.d2;
import androidx.core.view.AbstractC0228u;
import androidx.core.view.O;
import androidx.core.view.W;
import androidx.room.k;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;
import e.AbstractC0478a;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class NavigationBarItemView extends FrameLayout implements x {
    private static final ActiveIndicatorTransform ACTIVE_INDICATOR_LABELED_TRANSFORM;
    private static final ActiveIndicatorTransform ACTIVE_INDICATOR_UNLABELED_TRANSFORM;
    static final int BADGE_TYPE_DOT = 1;
    static final int BADGE_TYPE_N = 2;
    static final int BADGE_TYPE_OVERFLOW = 0;
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private static final int INVALID_ITEM_POSITION = -1;
    private static final float MAX_FONT_SCALE = 1.3f;
    public static final int SESL_MENU_ITEM_TYPE_CHECKBOX = 1;
    public static final int SESL_MENU_ITEM_TYPE_NONE = 0;
    private String TAG;
    private ValueAnimator activeIndicatorAnimator;
    private int activeIndicatorDesiredHeight;
    private int activeIndicatorDesiredWidth;
    private boolean activeIndicatorEnabled;
    private int activeIndicatorLabelPadding;
    private int activeIndicatorMarginHorizontal;
    private float activeIndicatorProgress;
    private boolean activeIndicatorResizeable;
    private ActiveIndicatorTransform activeIndicatorTransform;
    private final View activeIndicatorView;
    private int activeTextAppearance;
    private BadgeDrawable badgeDrawable;
    private final CheckBox checkBox;
    private int defaultMargin;
    private final ImageView icon;
    private final FrameLayout iconContainer;
    private ColorStateList iconTint;
    private boolean initialized;
    private boolean isNeedToSkipRefreshDrawable;
    private boolean isShifting;
    Drawable itemBackground;
    private l itemData;
    private int itemPaddingBottom;
    private int itemPaddingTop;
    private int itemPosition;
    private ColorStateList itemRippleColor;
    private final ViewGroup labelGroup;
    private int labelVisibilityMode;
    private final TextView largeLabel;
    private int mBadgeType;
    private boolean mIsBadgeNumberless;
    private SpannableStringBuilder mLabelImgSpan;
    private int mLargeLabelAppearance;
    private int mSmallLabelAppearance;
    private int mViewType;
    private Drawable originalIconDrawable;
    private float scaleDownFactor;
    private float scaleUpFactor;
    private float shiftAmount;
    private final TextView smallLabel;
    private Drawable wrappedIconDrawable;

    public static class ActiveIndicatorTransform {
        private static final float ALPHA_FRACTION = 0.2f;
        private static final float SCALE_X_HIDDEN = 0.4f;
        private static final float SCALE_X_SHOWN = 1.0f;

        private ActiveIndicatorTransform() {
        }

        public float calculateAlpha(float f2, float f7) {
            return AnimationUtils.lerp(0.0f, 1.0f, f7 == 0.0f ? 0.8f : 0.0f, f7 == 0.0f ? 1.0f : ALPHA_FRACTION, f2);
        }

        public float calculateScaleX(float f2, float f7) {
            return AnimationUtils.lerp(SCALE_X_HIDDEN, 1.0f, f2);
        }

        public float calculateScaleY(float f2, float f7) {
            return 1.0f;
        }

        public void updateForProgress(float f2, float f7, View view) {
            view.setScaleX(calculateScaleX(f2, f7));
            view.setScaleY(calculateScaleY(f2, f7));
            view.setAlpha(calculateAlpha(f2, f7));
        }
    }

    public static class ActiveIndicatorUnlabeledTransform extends ActiveIndicatorTransform {
        private ActiveIndicatorUnlabeledTransform() {
            super();
        }

        @Override // com.google.android.material.navigation.NavigationBarItemView.ActiveIndicatorTransform
        public float calculateScaleY(float f2, float f7) {
            return calculateScaleX(f2, f7);
        }
    }

    static {
        ACTIVE_INDICATOR_LABELED_TRANSFORM = new ActiveIndicatorTransform();
        ACTIVE_INDICATOR_UNLABELED_TRANSFORM = new ActiveIndicatorUnlabeledTransform();
    }

    public NavigationBarItemView(Context context) {
        this(context, null, 1);
    }

    private void calculateTextScaleFactors(float f2, float f7) {
        if (f7 == 0.0f || f2 == 0.0f) {
            Log.e(this.TAG, "LabelSize is invalid");
            this.scaleUpFactor = 1.0f;
            this.scaleDownFactor = 1.0f;
            this.shiftAmount = 0.0f;
            return;
        }
        this.shiftAmount = f2 - f7;
        float f9 = (f7 * 1.0f) / f2;
        this.scaleUpFactor = f9;
        this.scaleDownFactor = (f2 * 1.0f) / f7;
        if (f9 >= Float.MAX_VALUE || f9 <= -3.4028235E38f) {
            Log.e(this.TAG, "scaleUpFactor is invalid");
            this.scaleUpFactor = 1.0f;
            this.shiftAmount = 0.0f;
        }
        float f10 = this.scaleDownFactor;
        if (f10 >= Float.MAX_VALUE || f10 <= -3.4028235E38f) {
            Log.e(this.TAG, "scaleDownFactor is invalid");
            this.scaleDownFactor = 1.0f;
            this.shiftAmount = 0.0f;
        }
    }

    private static Drawable createItemBackgroundCompat(ColorStateList colorStateList) {
        return new RippleDrawable(RippleUtils.convertToRippleDrawableColor(colorStateList), null, null);
    }

    private FrameLayout getCustomParentForBadge(View view) {
        ImageView imageView = this.icon;
        if (view == imageView && BadgeUtils.USE_COMPAT_PARENT) {
            return (FrameLayout) imageView.getParent();
        }
        return null;
    }

    private View getIconOrContainer() {
        FrameLayout frameLayout = this.iconContainer;
        return frameLayout != null ? frameLayout : this.icon;
    }

    private int getItemVisiblePosition() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        int iIndexOfChild = viewGroup.indexOfChild(this);
        int i5 = 0;
        for (int i7 = 0; i7 < iIndexOfChild; i7++) {
            View childAt = viewGroup.getChildAt(i7);
            if ((childAt instanceof NavigationBarItemView) && childAt.getVisibility() == 0) {
                i5++;
            }
        }
        return i5;
    }

    private int getSuggestedIconHeight() {
        return getIconOrContainer().getMeasuredHeight() + ((FrameLayout.LayoutParams) getIconOrContainer().getLayoutParams()).topMargin;
    }

    private int getSuggestedIconWidth() {
        BadgeDrawable badgeDrawable = this.badgeDrawable;
        int minimumWidth = badgeDrawable == null ? 0 : badgeDrawable.getMinimumWidth() - this.badgeDrawable.getHorizontalOffset();
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getIconOrContainer().getLayoutParams();
        return Math.max(minimumWidth, layoutParams.rightMargin) + this.icon.getMeasuredWidth() + Math.max(minimumWidth, layoutParams.leftMargin);
    }

    private boolean hasBadge() {
        return this.badgeDrawable != null;
    }

    private boolean isActiveIndicatorResizeableAndUnlabeled() {
        return this.activeIndicatorResizeable && this.labelVisibilityMode == 2;
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

    private void maybeAnimateActiveIndicatorToProgress(final float f2) {
        if (this.activeIndicatorEnabled && this.initialized) {
            WeakHashMap weakHashMap = W.f7199a;
            if (isAttachedToWindow()) {
                ValueAnimator valueAnimator = this.activeIndicatorAnimator;
                if (valueAnimator != null) {
                    valueAnimator.cancel();
                    this.activeIndicatorAnimator = null;
                }
                ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.activeIndicatorProgress, f2);
                this.activeIndicatorAnimator = valueAnimatorOfFloat;
                valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.navigation.NavigationBarItemView.3
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                        NavigationBarItemView.this.setActiveIndicatorProgress(((Float) valueAnimator2.getAnimatedValue()).floatValue(), f2);
                    }
                });
                this.activeIndicatorAnimator.setInterpolator(MotionUtils.resolveThemeInterpolator(getContext(), com.google.android.material.R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
                this.activeIndicatorAnimator.setDuration(MotionUtils.resolveThemeDuration(getContext(), com.google.android.material.R.attr.motionDurationLong2, getResources().getInteger(com.google.android.material.R.integer.material_motion_duration_long_1)));
                this.activeIndicatorAnimator.start();
                return;
            }
        }
        setActiveIndicatorProgress(f2, f2);
    }

    private void refreshChecked() {
        l lVar = this.itemData;
        if (lVar != null) {
            setChecked(lVar.isChecked());
        }
    }

    private void refreshItemBackground() {
        Drawable drawableCreateItemBackgroundCompat = this.itemBackground;
        RippleDrawable rippleDrawable = null;
        boolean z9 = true;
        if (this.itemRippleColor != null) {
            Drawable activeIndicatorDrawable = getActiveIndicatorDrawable();
            if (this.activeIndicatorEnabled && getActiveIndicatorDrawable() != null && this.iconContainer != null && activeIndicatorDrawable != null) {
                rippleDrawable = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(this.itemRippleColor), null, activeIndicatorDrawable);
                z9 = false;
            } else if (drawableCreateItemBackgroundCompat == null) {
                drawableCreateItemBackgroundCompat = createItemBackgroundCompat(this.itemRippleColor);
            }
        }
        FrameLayout frameLayout = this.iconContainer;
        if (frameLayout != null) {
            frameLayout.setPadding(0, 0, 0, 0);
            this.iconContainer.setForeground(rippleDrawable);
        }
        WeakHashMap weakHashMap = W.f7199a;
        setBackground(drawableCreateItemBackgroundCompat);
        setDefaultFocusHighlightEnabled(z9);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setActiveIndicatorProgress(float f2, float f7) {
        View view = this.activeIndicatorView;
        if (view != null) {
            this.activeIndicatorTransform.updateForProgress(f2, f7, view);
        }
        this.activeIndicatorProgress = f2;
    }

    private void setLargeTextSize(int i5, TextView textView) {
        if (textView != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(i5, AbstractC0478a.f10552C);
            TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(0);
            typedArrayObtainStyledAttributes.recycle();
            textView.setTextSize(1, Math.min(getResources().getConfiguration().fontScale, MAX_FONT_SCALE) * TypedValue.complexToFloat(typedValuePeekValue.data));
        }
    }

    private static void setTextAppearanceWithoutFontScaling(TextView textView, int i5) {
        textView.setTextAppearance(i5);
        int unscaledTextSize = MaterialResources.getUnscaledTextSize(textView.getContext(), i5, 0);
        if (unscaledTextSize != 0) {
            textView.setTextSize(0, unscaledTextSize);
        }
    }

    private static void setViewScaleValues(View view, float f2, float f7, int i5) {
        view.setScaleX(f2);
        view.setScaleY(f7);
        view.setVisibility(i5);
    }

    private static void setViewTopMarginAndGravity(View view, int i5, int i7) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view.getLayoutParams();
        layoutParams.topMargin = i5;
        layoutParams.bottomMargin = i5;
        layoutParams.gravity = i7;
        view.setLayoutParams(layoutParams);
    }

    private void tryAttachBadgeToAnchor(View view) {
        if (hasBadge() && view != null) {
            setClipChildren(false);
            setClipToPadding(false);
            BadgeUtils.attachBadgeDrawable(this.badgeDrawable, view, getCustomParentForBadge(view));
        }
    }

    private void tryRemoveBadgeFromAnchor(View view) {
        if (hasBadge()) {
            if (view != null) {
                setClipChildren(true);
                setClipToPadding(true);
                BadgeUtils.detachBadgeDrawable(this.badgeDrawable, view);
            }
            this.badgeDrawable = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryUpdateBadgeBounds(View view) {
        if (hasBadge()) {
            BadgeUtils.setBadgeDrawableBounds(this.badgeDrawable, view, getCustomParentForBadge(view));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateActiveIndicatorLayoutParams(int i5) {
        if (this.activeIndicatorView == null || i5 <= 0) {
            return;
        }
        int iMin = Math.min(this.activeIndicatorDesiredWidth, i5 - (this.activeIndicatorMarginHorizontal * 2));
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.activeIndicatorView.getLayoutParams();
        layoutParams.height = isActiveIndicatorResizeableAndUnlabeled() ? iMin : this.activeIndicatorDesiredHeight;
        layoutParams.width = iMin;
        this.activeIndicatorView.setLayoutParams(layoutParams);
    }

    private void updateActiveIndicatorTransform() {
        if (isActiveIndicatorResizeableAndUnlabeled()) {
            this.activeIndicatorTransform = ACTIVE_INDICATOR_UNLABELED_TRANSFORM;
        } else {
            this.activeIndicatorTransform = ACTIVE_INDICATOR_LABELED_TRANSFORM;
        }
    }

    private static void updateViewPaddingBottom(View view, int i5) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), i5);
    }

    public void clear() {
        removeBadge();
        this.itemData = null;
        this.activeIndicatorProgress = 0.0f;
        this.initialized = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        FrameLayout frameLayout = this.iconContainer;
        if (frameLayout != null && this.activeIndicatorEnabled) {
            frameLayout.dispatchTouchEvent(motionEvent);
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public Drawable getActiveIndicatorDrawable() {
        View view = this.activeIndicatorView;
        if (view == null) {
            return null;
        }
        return view.getBackground();
    }

    public BadgeDrawable getBadge() {
        return this.badgeDrawable;
    }

    public int getBadgeType() {
        return this.mBadgeType;
    }

    public int getItemBackgroundResId() {
        return com.google.android.material.R.drawable.mtrl_navigation_bar_item_background;
    }

    @Override // androidx.appcompat.view.menu.x
    public l getItemData() {
        return this.itemData;
    }

    public int getItemDefaultMarginResId() {
        return com.google.android.material.R.dimen.mtrl_navigation_bar_item_default_margin;
    }

    public abstract int getItemLayoutResId();

    public int getItemPosition() {
        return this.itemPosition;
    }

    public TextView getLabel() {
        TextView textView = this.smallLabel;
        return textView != null ? textView : this.largeLabel;
    }

    public SpannableStringBuilder getLabelImageSpan() {
        return this.mLabelImgSpan;
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.labelGroup.getLayoutParams();
        return this.labelGroup.getMeasuredHeight() + getSuggestedIconHeight() + (this.labelGroup.getVisibility() == 0 ? this.activeIndicatorLabelPadding : 0) + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    @Override // android.view.View
    public int getSuggestedMinimumWidth() {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.labelGroup.getLayoutParams();
        return Math.max(getSuggestedIconWidth(), this.labelGroup.getMeasuredWidth() + layoutParams.leftMargin + layoutParams.rightMargin);
    }

    public int getViewType() {
        return this.mViewType;
    }

    @Override // androidx.appcompat.view.menu.x
    public void initialize(l lVar, int i5) {
        this.itemData = lVar;
        setCheckable(lVar.isCheckable());
        setChecked(lVar.isChecked());
        setEnabled(lVar.isEnabled());
        setIcon(lVar.getIcon());
        setTitle(lVar.f6273i);
        int i7 = lVar.f6270e;
        setId(i7);
        if (!TextUtils.isEmpty(lVar.f6284u)) {
            setContentDescription(lVar.f6284u);
        }
        d2.a(this, lVar.f6285v);
        String str = lVar.f6268I;
        setBadgeType((str == null || str.equals("") || str.isEmpty()) ? 1 : i7 == com.google.android.material.R.id.bottom_overflow ? 0 : 2);
        this.initialized = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getBackground() instanceof SeslRecoilDrawable) {
            this.isNeedToSkipRefreshDrawable = true;
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setLargeTextSize(this.mLargeLabelAppearance, this.largeLabel);
        setLargeTextSize(this.mSmallLabelAppearance, this.smallLabel);
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i5) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i5 + 1);
        l lVar = this.itemData;
        if (lVar != null && lVar.isCheckable() && this.itemData.isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, CHECKED_STATE_SET);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        String str;
        BadgeDrawable badgeDrawable;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (this.itemData != null && (badgeDrawable = this.badgeDrawable) != null && badgeDrawable.isVisible()) {
            l lVar = this.itemData;
            CharSequence charSequence = lVar.f6273i;
            if (!TextUtils.isEmpty(lVar.f6284u)) {
                charSequence = this.itemData.f6284u;
            }
            accessibilityNodeInfo.setContentDescription(((Object) charSequence) + ", " + ((Object) this.badgeDrawable.getContentDescription()));
        }
        TextView textView = (TextView) findViewById(com.google.android.material.R.id.notifications_badge);
        if (this.itemData != null && textView != null && textView.getVisibility() == 0 && textView.getWidth() > 0) {
            CharSequence charSequence2 = this.itemData.f6273i;
            String string = charSequence2.toString();
            if (TextUtils.isEmpty(this.itemData.f6284u)) {
                int i5 = this.mBadgeType;
                if (i5 == 0) {
                    string = ((Object) charSequence2) + " , " + getResources().getString(com.google.android.material.R.string.sesl_material_badge_description);
                } else if (i5 == 1) {
                    string = ((Object) charSequence2) + " , " + getResources().getString(com.google.android.material.R.string.mtrl_badge_numberless_content_description);
                } else if (i5 == 2) {
                    String string2 = textView.getText().toString();
                    if (isNumericValue(string2)) {
                        int i7 = Integer.parseInt(string2);
                        string = ((Object) charSequence2) + " , " + getResources().getQuantityString(com.google.android.material.R.plurals.mtrl_badge_content_description, i7, Integer.valueOf(i7));
                    } else {
                        if (this.mIsBadgeNumberless) {
                            str = ((Object) charSequence2) + " , " + getResources().getString(com.google.android.material.R.string.mtrl_exceed_max_badge_number_content_description, Integer.valueOf(k.MAX_BIND_PARAMETER_CNT));
                        } else {
                            str = ((Object) charSequence2) + " , " + getResources().getString(com.google.android.material.R.string.sesl_material_badge_description);
                        }
                        string = str;
                    }
                }
            } else {
                string = this.itemData.f6284u.toString();
            }
            accessibilityNodeInfo.setContentDescription(string);
        }
        accessibilityNodeInfo.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo) L.k.a(0, 1, getItemVisiblePosition(), 1, false, isSelected()).f1792a);
        if (isSelected()) {
            accessibilityNodeInfo.setClickable(false);
            accessibilityNodeInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) f.f1779e.f1787a);
        }
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    @Override // android.view.View
    public void onSizeChanged(final int i5, int i7, int i9, int i10) {
        super.onSizeChanged(i5, i7, i9, i10);
        post(new Runnable() { // from class: com.google.android.material.navigation.NavigationBarItemView.2
            @Override // java.lang.Runnable
            public void run() {
                NavigationBarItemView.this.updateActiveIndicatorLayoutParams(i5);
            }
        });
    }

    public boolean prefersCondensedTitle() {
        return false;
    }

    @Override // android.view.View
    public void refreshDrawableState() {
        super.refreshDrawableState();
        if (!this.isNeedToSkipRefreshDrawable || getStateListAnimator() == null) {
            return;
        }
        getStateListAnimator().jumpToCurrentState();
        this.isNeedToSkipRefreshDrawable = false;
    }

    public void removeBadge() {
        tryRemoveBadgeFromAnchor(this.icon);
    }

    public void seslSetLabelTextAppearance(int i5) {
        this.mLargeLabelAppearance = i5;
        this.mSmallLabelAppearance = i5;
        this.smallLabel.setTextAppearance(i5);
        calculateTextScaleFactors(this.smallLabel.getTextSize(), this.largeLabel.getTextSize());
        setLargeTextSize(this.mLargeLabelAppearance, this.largeLabel);
        setLargeTextSize(this.mSmallLabelAppearance, this.smallLabel);
    }

    public void setActiveIndicatorDrawable(Drawable drawable) {
        View view = this.activeIndicatorView;
        if (view == null) {
            return;
        }
        view.setBackgroundDrawable(drawable);
        refreshItemBackground();
    }

    public void setActiveIndicatorEnabled(boolean z9) {
        this.activeIndicatorEnabled = z9;
        refreshItemBackground();
        View view = this.activeIndicatorView;
        if (view != null) {
            view.setVisibility(z9 ? 0 : 8);
            requestLayout();
        }
    }

    public void setActiveIndicatorHeight(int i5) {
        this.activeIndicatorDesiredHeight = i5;
        updateActiveIndicatorLayoutParams(getWidth());
    }

    public void setActiveIndicatorLabelPadding(int i5) {
        if (this.activeIndicatorLabelPadding != i5) {
            this.activeIndicatorLabelPadding = i5;
            refreshChecked();
        }
    }

    public void setActiveIndicatorMarginHorizontal(int i5) {
        this.activeIndicatorMarginHorizontal = i5;
        updateActiveIndicatorLayoutParams(getWidth());
    }

    public void setActiveIndicatorResizeable(boolean z9) {
        this.activeIndicatorResizeable = z9;
    }

    public void setActiveIndicatorWidth(int i5) {
        this.activeIndicatorDesiredWidth = i5;
        updateActiveIndicatorLayoutParams(getWidth());
    }

    public void setBadge(BadgeDrawable badgeDrawable) {
        if (this.badgeDrawable == badgeDrawable) {
            return;
        }
        if (hasBadge() && this.icon != null) {
            Log.w("NavigationBar", "Multiple badges shouldn't be attached to one item.");
            tryRemoveBadgeFromAnchor(this.icon);
        }
        this.badgeDrawable = badgeDrawable;
        ImageView imageView = this.icon;
        if (imageView != null) {
            tryAttachBadgeToAnchor(imageView);
        }
    }

    public void setBadgeNumberless(boolean z9) {
        this.mIsBadgeNumberless = z9;
    }

    public void setBadgeType(int i5) {
        this.mBadgeType = i5;
    }

    public void setCheckable(boolean z9) {
        refreshDrawableState();
    }

    public void setChecked(boolean z9) {
        this.largeLabel.setPivotX(r0.getWidth() / 2);
        this.largeLabel.setPivotY(r0.getBaseline());
        this.smallLabel.setPivotX(r0.getWidth() / 2);
        this.smallLabel.setPivotY(r0.getBaseline());
        if (getViewType() != 3) {
            this.defaultMargin = getResources().getDimensionPixelSize(com.google.android.material.R.dimen.sesl_navigation_bar_icon_inset);
        }
        this.itemPaddingTop = this.defaultMargin;
        maybeAnimateActiveIndicatorToProgress(z9 ? 1.0f : 0.0f);
        int i5 = this.labelVisibilityMode;
        if (i5 != -1) {
            if (i5 == 0) {
                if (z9) {
                    setViewTopMarginAndGravity(getIconOrContainer(), this.itemPaddingTop, 49);
                    updateViewPaddingBottom(this.labelGroup, this.itemPaddingBottom);
                    this.largeLabel.setVisibility(0);
                    setViewScaleValues(this.largeLabel, 1.0f, 1.0f, 0);
                } else {
                    setViewTopMarginAndGravity(getIconOrContainer(), this.itemPaddingTop, 17);
                    updateViewPaddingBottom(this.labelGroup, 0);
                    this.largeLabel.setVisibility(4);
                    setViewScaleValues(this.largeLabel, 0.5f, 0.5f, 4);
                }
                this.smallLabel.setVisibility(4);
            } else if (i5 == 1) {
                updateViewPaddingBottom(this.labelGroup, this.itemPaddingBottom);
                if (z9) {
                    setViewTopMarginAndGravity(getIconOrContainer(), (int) (this.itemPaddingTop + this.shiftAmount), 49);
                    setViewScaleValues(this.largeLabel, 1.0f, 1.0f, 0);
                    TextView textView = this.smallLabel;
                    float f2 = this.scaleUpFactor;
                    setViewScaleValues(textView, f2, f2, 4);
                } else {
                    setViewTopMarginAndGravity(getIconOrContainer(), this.itemPaddingTop, 49);
                    TextView textView2 = this.largeLabel;
                    float f7 = this.scaleDownFactor;
                    setViewScaleValues(textView2, f7, f7, 4);
                    setViewScaleValues(this.smallLabel, 1.0f, 1.0f, 0);
                }
            } else if (i5 == 2) {
                setViewTopMarginAndGravity(getIconOrContainer(), this.itemPaddingTop, 17);
                this.largeLabel.setVisibility(8);
                this.smallLabel.setVisibility(8);
            }
        } else if (this.isShifting) {
            if (z9) {
                setViewTopMarginAndGravity(getIconOrContainer(), this.itemPaddingTop, 49);
                updateViewPaddingBottom(this.labelGroup, this.itemPaddingBottom);
                this.largeLabel.setVisibility(0);
                setViewScaleValues(this.largeLabel, 1.0f, 1.0f, 0);
            } else {
                setViewTopMarginAndGravity(getIconOrContainer(), this.itemPaddingTop, 17);
                updateViewPaddingBottom(this.labelGroup, 0);
                this.largeLabel.setVisibility(4);
                setViewScaleValues(this.largeLabel, 0.5f, 0.5f, 4);
            }
            this.smallLabel.setVisibility(4);
        } else {
            updateViewPaddingBottom(this.labelGroup, this.itemPaddingBottom);
            if (z9) {
                setViewTopMarginAndGravity(getIconOrContainer(), (int) (this.itemPaddingTop + this.shiftAmount), 49);
                setViewScaleValues(this.largeLabel, 1.0f, 1.0f, 4);
                TextView textView3 = this.smallLabel;
                float f9 = this.scaleUpFactor;
                setViewScaleValues(textView3, f9, f9, 0);
            } else {
                setViewTopMarginAndGravity(getIconOrContainer(), this.itemPaddingTop, 49);
                TextView textView4 = this.largeLabel;
                float f10 = this.scaleDownFactor;
                setViewScaleValues(textView4, f10, f10, 4);
                setViewScaleValues(this.smallLabel, 1.0f, 1.0f, 0);
            }
        }
        refreshDrawableState();
    }

    @Override // android.view.View
    public void setEnabled(boolean z9) {
        super.setEnabled(z9);
        this.smallLabel.setEnabled(z9);
        this.largeLabel.setEnabled(z9);
        this.icon.setEnabled(z9);
        if (z9) {
            O.d(this, AbstractC0228u.b(getContext(), 1002));
        } else {
            WeakHashMap weakHashMap = W.f7199a;
            O.d(this, null);
        }
    }

    public void setIcon(Drawable drawable) {
        if (drawable == this.originalIconDrawable) {
            return;
        }
        this.originalIconDrawable = drawable;
        if (drawable != null) {
            Drawable.ConstantState constantState = drawable.getConstantState();
            if (constantState != null) {
                drawable = constantState.newDrawable();
            }
            drawable = drawable.mutate();
            this.wrappedIconDrawable = drawable;
            ColorStateList colorStateList = this.iconTint;
            if (colorStateList != null) {
                a.h(drawable, colorStateList);
            }
        }
        this.icon.setImageDrawable(drawable);
    }

    public void setIconSize(int i5) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.icon.getLayoutParams();
        layoutParams.width = i5;
        layoutParams.height = i5;
        this.icon.setLayoutParams(layoutParams);
    }

    public void setIconTintList(ColorStateList colorStateList) {
        Drawable drawable;
        this.iconTint = colorStateList;
        l lVar = this.itemData;
        if ((lVar == null && this.wrappedIconDrawable == null) || lVar == null || (drawable = this.wrappedIconDrawable) == null) {
            return;
        }
        a.h(drawable, colorStateList);
        this.wrappedIconDrawable.invalidateSelf();
    }

    public void setItemBackground(int i5) {
        setItemBackground(i5 == 0 ? null : B.a.b(getContext(), i5));
    }

    public void setItemPaddingBottom(int i5) {
        if (this.itemPaddingBottom != i5) {
            this.itemPaddingBottom = i5;
            refreshChecked();
        }
    }

    public void setItemPaddingTop(int i5) {
        if (this.itemPaddingTop != i5) {
            this.itemPaddingTop = i5;
            refreshChecked();
        }
    }

    public void setItemPosition(int i5) {
        this.itemPosition = i5;
    }

    public void setItemRippleColor(ColorStateList colorStateList) {
        this.itemRippleColor = colorStateList;
        refreshItemBackground();
    }

    public void setLabelImageSpan(SpannableStringBuilder spannableStringBuilder) {
        this.mLabelImgSpan = spannableStringBuilder;
        this.smallLabel.setText(spannableStringBuilder);
        this.largeLabel.setText(spannableStringBuilder);
    }

    public void setLabelVisibilityMode(int i5) {
        if (this.labelVisibilityMode != i5) {
            this.labelVisibilityMode = i5;
            updateActiveIndicatorTransform();
            updateActiveIndicatorLayoutParams(getWidth());
            refreshChecked();
        }
    }

    public void setShifting(boolean z9) {
        if (this.isShifting != z9) {
            this.isShifting = z9;
            refreshChecked();
        }
    }

    public void setShortcut(boolean z9, char c5) {
    }

    public void setShowButtonShape(int i5, ColorStateList colorStateList) {
        Drawable drawable = getResources().getDrawable(com.google.android.material.R.drawable.sesl_bottom_nav_show_button_shapes_background);
        this.smallLabel.setTextColor(i5);
        this.largeLabel.setTextColor(i5);
        this.smallLabel.setBackground(drawable);
        this.largeLabel.setBackground(drawable);
        this.smallLabel.setBackgroundTintList(colorStateList);
        this.largeLabel.setBackgroundTintList(colorStateList);
    }

    public void setTextAppearanceActive(int i5) {
        this.activeTextAppearance = i5;
        setTextAppearanceWithoutFontScaling(this.largeLabel, i5);
        calculateTextScaleFactors(this.smallLabel.getTextSize(), this.largeLabel.getTextSize());
    }

    public void setTextAppearanceActiveBoldEnabled(boolean z9) {
        setTextAppearanceActive(this.activeTextAppearance);
        TextView textView = this.largeLabel;
        textView.setTypeface(textView.getTypeface(), z9 ? 1 : 0);
    }

    public void setTextAppearanceInactive(int i5) {
        setTextAppearanceWithoutFontScaling(this.smallLabel, i5);
        calculateTextScaleFactors(this.smallLabel.getTextSize(), this.largeLabel.getTextSize());
    }

    public void setTextColor(ColorStateList colorStateList) {
        if (colorStateList != null) {
            this.smallLabel.setTextColor(colorStateList);
            this.largeLabel.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        this.smallLabel.setText(charSequence);
        this.largeLabel.setText(charSequence);
        if (TextUtils.isEmpty(charSequence)) {
            this.smallLabel.setVisibility(8);
            this.largeLabel.setVisibility(8);
        }
        l lVar = this.itemData;
        if (lVar == null || TextUtils.isEmpty(lVar.f6284u)) {
            setContentDescription(charSequence);
        }
        l lVar2 = this.itemData;
        d2.a(this, lVar2 != null ? lVar2.f6285v : null);
    }

    public boolean showsIcon() {
        return true;
    }

    public void updateLabelGroupTopMargin(int i5) {
        if (this.labelGroup == null) {
            return;
        }
        this.defaultMargin = getResources().getDimensionPixelSize(com.google.android.material.R.dimen.sesl_bottom_navigation_icon_inset);
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.labelGroup.getLayoutParams();
        if (marginLayoutParams != null) {
            marginLayoutParams.topMargin = i5 + this.defaultMargin;
            this.labelGroup.setLayoutParams(marginLayoutParams);
        }
    }

    public NavigationBarItemView(Context context, int i5) {
        this(context, null, i5);
    }

    public NavigationBarItemView(Context context, AttributeSet attributeSet, int i5) {
        this(context, attributeSet, 0, i5);
    }

    public NavigationBarItemView(Context context, AttributeSet attributeSet, int i5, int i7) {
        super(context, attributeSet, i5);
        this.TAG = "NavigationBarItemView";
        this.initialized = false;
        this.itemPosition = -1;
        this.activeTextAppearance = 0;
        this.activeIndicatorTransform = ACTIVE_INDICATOR_LABELED_TRANSFORM;
        this.activeIndicatorProgress = 0.0f;
        this.activeIndicatorEnabled = false;
        this.activeIndicatorDesiredWidth = 0;
        this.activeIndicatorDesiredHeight = 0;
        this.activeIndicatorResizeable = false;
        this.activeIndicatorMarginHorizontal = 0;
        this.mBadgeType = 1;
        this.mViewType = i7;
        LayoutInflater.from(context).inflate(getItemLayoutResId(), (ViewGroup) this, true);
        this.iconContainer = (FrameLayout) findViewById(com.google.android.material.R.id.navigation_bar_item_icon_container);
        this.activeIndicatorView = findViewById(com.google.android.material.R.id.navigation_bar_item_active_indicator_view);
        ImageView imageView = (ImageView) findViewById(com.google.android.material.R.id.navigation_bar_item_icon_view);
        this.icon = imageView;
        ViewGroup viewGroup = (ViewGroup) findViewById(com.google.android.material.R.id.navigation_bar_item_labels_group);
        this.labelGroup = viewGroup;
        TextView textView = (TextView) findViewById(com.google.android.material.R.id.navigation_bar_item_small_label_view);
        this.smallLabel = textView;
        TextView textView2 = (TextView) findViewById(com.google.android.material.R.id.navigation_bar_item_large_label_view);
        this.largeLabel = textView2;
        this.checkBox = (CheckBox) findViewById(com.google.android.material.R.id.navigation_bar_item_checkbox_view);
        setBackgroundResource(getItemBackgroundResId());
        this.itemPaddingTop = getResources().getDimensionPixelSize(getItemDefaultMarginResId());
        this.itemPaddingBottom = viewGroup.getPaddingBottom();
        this.activeIndicatorLabelPadding = getResources().getDimensionPixelSize(com.google.android.material.R.dimen.m3_navigation_item_active_indicator_label_padding);
        WeakHashMap weakHashMap = W.f7199a;
        textView.setImportantForAccessibility(2);
        textView2.setImportantForAccessibility(2);
        setFocusable(true);
        calculateTextScaleFactors(textView.getTextSize(), textView2.getTextSize());
        if (imageView != null) {
            imageView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.google.android.material.navigation.NavigationBarItemView.1
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
                    if (NavigationBarItemView.this.icon.getVisibility() == 0) {
                        NavigationBarItemView navigationBarItemView = NavigationBarItemView.this;
                        navigationBarItemView.tryUpdateBadgeBounds(navigationBarItemView.icon);
                    }
                }
            });
        }
        W.i(this, null);
    }

    public void setItemBackground(Drawable drawable) {
        if (drawable != null && drawable.getConstantState() != null) {
            drawable = drawable.getConstantState().newDrawable().mutate();
        }
        this.itemBackground = drawable;
        refreshItemBackground();
    }
}
