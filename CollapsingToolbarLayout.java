package com.google.android.material.appbar;

import P.r;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ViewStubCompat;
import androidx.core.view.InterfaceC0226s;
import androidx.core.view.K;
import androidx.core.view.M;
import androidx.core.view.W;
import androidx.core.view.w0;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.model.AppBarModel;
import com.google.android.material.appbar.model.view.AppBarView;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import e.AbstractC0478a;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class CollapsingToolbarLayout extends FrameLayout {
    private static final int DEFAULT_SCRIM_ANIMATION_DURATION = 600;
    private static final int DEF_STYLE_RES = R.style.Widget_Design_CollapsingToolbar;
    private static final float EPSILON = 1.0E-5f;
    private static final float LAND_HEIGHT_PERCENT = 0.3f;
    private static final float MAX_FONT_SCALE = 1.0f;
    protected static final String TAG = "Sesl_CTL";
    public static final int TITLE_COLLAPSE_MODE_FADE = 1;
    public static final int TITLE_COLLAPSE_MODE_SCALE = 0;
    final CollapsingTextHelper collapsingTextHelper;
    private boolean collapsingTitleEnabled;
    private Drawable contentScrim;
    int currentOffset;
    private boolean drawCollapsingTitle;
    private View dummyView;
    final ElevationOverlayProvider elevationOverlayProvider;
    private int expandedMarginBottom;
    private int expandedMarginEnd;
    private int expandedMarginStart;
    private int expandedMarginTop;
    private int extraMultilineHeight;
    private boolean extraMultilineHeightEnabled;
    private boolean forceApplySystemWindowInsetTop;
    w0 lastInsets;
    private View mCustomSubTitleView;
    private float mDefaultHeight;
    private int mExtendSubTitleAppearance;
    private int mExtendTitleAppearance;
    private TextView mExtendedSubTitle;
    private TextView mExtendedTitle;
    private boolean mFadeToolbarTitle;
    private float mHeightProportion;
    private boolean mIsCollapsingToolbarTitleCustom;
    private boolean mIsCustomAccessibility;
    private StackViewGroup mStackViewGroup;
    private boolean mSubTitleEnabled;
    HashMap<AppBarModel, AppBarView> mSuggestViewHashMap;
    private boolean mTitleEnabled;
    private LinearLayout mTitleLayout;
    private LinearLayout mTitleLayoutParent;
    private ViewStubCompat mViewStubCompat;
    private AppBarLayout.OnOffsetChangedListener onOffsetChangedListener;
    private boolean refreshToolbar;
    private int scrimAlpha;
    private long scrimAnimationDuration;
    private final TimeInterpolator scrimAnimationFadeInInterpolator;
    private final TimeInterpolator scrimAnimationFadeOutInterpolator;
    private ValueAnimator scrimAnimator;
    private int scrimVisibleHeightTrigger;
    private boolean scrimsAreShown;
    Drawable statusBarScrim;
    private int titleCollapseMode;
    private final Rect tmpRect;
    private ViewGroup toolbar;
    private View toolbarDirectChild;
    private int toolbarId;
    private int topInsetApplied;

    public class OffsetUpdateListener implements AppBarLayout.OnOffsetChangedListener {
        public OffsetUpdateListener() {
            CollapsingToolbarLayout.this.updateDefaultHeight();
        }

        /* JADX WARN: Removed duplicated region for block: B:71:0x0156  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x0170  */
        /* JADX WARN: Removed duplicated region for block: B:86:? A[RETURN, SYNTHETIC] */
        @Override // com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener, com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onOffsetChanged(com.google.android.material.appbar.AppBarLayout r12, int r13) {
            /*
                Method dump skipped, instruction units count: 411
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.CollapsingToolbarLayout.OffsetUpdateListener.onOffsetChanged(com.google.android.material.appbar.AppBarLayout, int):void");
        }
    }

    public interface StaticLayoutBuilderConfigurer extends com.google.android.material.internal.StaticLayoutBuilderConfigurer {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TitleCollapseMode {
    }

    public CollapsingToolbarLayout(Context context) {
        this(context, null);
    }

    private void animateScrim(int i5) {
        ensureToolbar();
        ValueAnimator valueAnimator = this.scrimAnimator;
        if (valueAnimator == null) {
            ValueAnimator valueAnimator2 = new ValueAnimator();
            this.scrimAnimator = valueAnimator2;
            valueAnimator2.setInterpolator(i5 > this.scrimAlpha ? this.scrimAnimationFadeInInterpolator : this.scrimAnimationFadeOutInterpolator);
            this.scrimAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.appbar.CollapsingToolbarLayout.2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator3) {
                    CollapsingToolbarLayout.this.setScrimAlpha(((Integer) valueAnimator3.getAnimatedValue()).intValue());
                }
            });
        } else if (valueAnimator.isRunning()) {
            this.scrimAnimator.cancel();
        }
        this.scrimAnimator.setDuration(this.scrimAnimationDuration);
        this.scrimAnimator.setIntValues(this.scrimAlpha, i5);
        this.scrimAnimator.start();
    }

    private TextUtils.TruncateAt convertEllipsizeToTruncateAt(int i5) {
        return i5 != 0 ? i5 != 1 ? i5 != 3 ? TextUtils.TruncateAt.END : TextUtils.TruncateAt.MARQUEE : TextUtils.TruncateAt.MIDDLE : TextUtils.TruncateAt.START;
    }

    private void disableLiftOnScrollIfNeeded(AppBarLayout appBarLayout) {
        if (isTitleCollapseFadeMode()) {
            appBarLayout.setLiftOnScroll(false);
        }
    }

    private void ensureToolbar() {
        if (this.refreshToolbar) {
            ViewGroup viewGroup = null;
            this.toolbar = null;
            this.toolbarDirectChild = null;
            int i5 = this.toolbarId;
            if (i5 != -1) {
                ViewGroup viewGroup2 = (ViewGroup) findViewById(i5);
                this.toolbar = viewGroup2;
                if (viewGroup2 != null) {
                    this.toolbarDirectChild = findDirectChild(viewGroup2);
                }
            }
            if (this.toolbar == null) {
                int childCount = getChildCount();
                int i7 = 0;
                while (true) {
                    if (i7 >= childCount) {
                        break;
                    }
                    View childAt = getChildAt(i7);
                    if (isToolbar(childAt)) {
                        viewGroup = (ViewGroup) childAt;
                        break;
                    }
                    i7++;
                }
                this.toolbar = viewGroup;
                ViewStubCompat viewStubCompat = this.mViewStubCompat;
                if (viewStubCompat != null) {
                    viewStubCompat.bringToFront();
                    this.mViewStubCompat.invalidate();
                }
            }
            updateDummyView();
            this.refreshToolbar = false;
        }
    }

    private View findDirectChild(View view) {
        for (ViewParent parent = view.getParent(); parent != this && parent != null; parent = parent.getParent()) {
            if (parent instanceof View) {
                view = parent;
            }
        }
        return view;
    }

    private int getDefaultContentScrimColorForTitleCollapseFadeMode() {
        ColorStateList colorStateListOrNull = MaterialColors.getColorStateListOrNull(getContext(), R.attr.colorSurfaceContainer);
        if (colorStateListOrNull != null) {
            return colorStateListOrNull.getDefaultColor();
        }
        return this.elevationOverlayProvider.compositeOverlayWithThemeSurfaceColorIfNeeded(getResources().getDimension(R.dimen.design_appbar_elevation));
    }

    private static int getHeightWithMargins(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return view.getMeasuredHeight();
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        return view.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    private int getStatusbarHeight() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelOffset(identifier);
        }
        return 0;
    }

    private static CharSequence getToolbarTitle(View view) {
        if (view instanceof Toolbar) {
            return ((Toolbar) view).getTitle();
        }
        if (view instanceof android.widget.Toolbar) {
            return ((android.widget.Toolbar) view).getTitle();
        }
        return null;
    }

    public static ViewOffsetHelper getViewOffsetHelper(View view) {
        int i5 = R.id.view_offset_helper;
        ViewOffsetHelper viewOffsetHelper = (ViewOffsetHelper) view.getTag(i5);
        if (viewOffsetHelper != null) {
            return viewOffsetHelper;
        }
        ViewOffsetHelper viewOffsetHelper2 = new ViewOffsetHelper(view);
        view.setTag(i5, viewOffsetHelper2);
        return viewOffsetHelper2;
    }

    private TextView initSubTitle() {
        TextView textView = (TextView) findViewById(R.id.collapsing_appbar_extended_subtitle);
        textView.setTextAppearance(getContext(), this.mExtendSubTitleAppearance);
        return textView;
    }

    private TextView initTitle(Context context) {
        TextView textView = (TextView) findViewById(R.id.collapsing_appbar_extended_title);
        textView.setHyphenationFrequency(1);
        textView.setTextAppearance(context, this.mExtendTitleAppearance);
        textView.setVisibility(0);
        return textView;
    }

    private void initTitleLayout(Context context, CharSequence charSequence) {
        int statusbarHeight;
        StackViewGroup stackViewGroup = new StackViewGroup(new FrameLayout(context));
        this.mStackViewGroup = stackViewGroup;
        FrameLayout rootView = stackViewGroup.getRootView();
        addView(rootView);
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.sesl_app_bar, (ViewGroup) rootView, false);
        this.mStackViewGroup.push(viewGroup);
        LinearLayout linearLayout = (LinearLayout) viewGroup.findViewById(R.id.collapsing_appbar_title_layout_parent);
        this.mTitleLayoutParent = linearLayout;
        if (linearLayout != null && (statusbarHeight = getStatusbarHeight()) > 0) {
            setBottomPadding(this.mTitleLayoutParent, statusbarHeight);
        }
        this.mTitleLayout = (LinearLayout) findViewById(R.id.collapsing_appbar_title_layout);
        if (this.mTitleEnabled) {
            this.mExtendedTitle = initTitle(context);
        }
        if (this.mSubTitleEnabled) {
            seslSetSubtitle(charSequence);
        }
    }

    private boolean isTitleCollapseFadeMode() {
        return this.titleCollapseMode == 1;
    }

    private static boolean isToolbar(View view) {
        return (view instanceof Toolbar) || (view instanceof android.widget.Toolbar);
    }

    private boolean isToolbarChild(View view) {
        View view2 = this.toolbarDirectChild;
        if (view2 == null || view2 == this) {
            if (view != this.toolbar) {
                return false;
            }
        } else if (view != view2) {
            return false;
        }
        return true;
    }

    private void setBottomPadding(View view, int i5) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), i5);
    }

    private void updateCollapsedBounds(boolean z9) {
        int titleMarginStart;
        int titleMarginBottom;
        int titleMarginEnd;
        int titleMarginTop;
        View view = this.toolbarDirectChild;
        if (view == null) {
            view = this.toolbar;
        }
        int maxOffsetForPinChild = getMaxOffsetForPinChild(view);
        DescendantOffsetUtils.getDescendantRect(this, this.dummyView, this.tmpRect);
        ViewGroup viewGroup = this.toolbar;
        if (viewGroup instanceof Toolbar) {
            Toolbar toolbar = (Toolbar) viewGroup;
            titleMarginStart = toolbar.getTitleMarginStart();
            titleMarginEnd = toolbar.getTitleMarginEnd();
            titleMarginTop = toolbar.getTitleMarginTop();
            titleMarginBottom = toolbar.getTitleMarginBottom();
        } else if (viewGroup instanceof android.widget.Toolbar) {
            android.widget.Toolbar toolbar2 = (android.widget.Toolbar) viewGroup;
            titleMarginStart = toolbar2.getTitleMarginStart();
            titleMarginEnd = toolbar2.getTitleMarginEnd();
            titleMarginTop = toolbar2.getTitleMarginTop();
            titleMarginBottom = toolbar2.getTitleMarginBottom();
        } else {
            titleMarginStart = 0;
            titleMarginBottom = 0;
            titleMarginEnd = 0;
            titleMarginTop = 0;
        }
        CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
        Rect rect = this.tmpRect;
        int i5 = rect.left + (z9 ? titleMarginEnd : titleMarginStart);
        int i7 = rect.top + maxOffsetForPinChild + titleMarginTop;
        int i9 = rect.right;
        if (!z9) {
            titleMarginStart = titleMarginEnd;
        }
        collapsingTextHelper.setCollapsedBounds(i5, i7, i9 - titleMarginStart, (rect.bottom + maxOffsetForPinChild) - titleMarginBottom);
    }

    private void updateContentDescriptionFromTitle() {
        setContentDescription(getTitle());
    }

    private void updateContentScrimBounds(Drawable drawable, int i5, int i7) {
        updateContentScrimBounds(drawable, this.toolbar, i5, i7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDefaultHeight() {
        if (!(getParent() instanceof AppBarLayout)) {
            this.mDefaultHeight = getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_action_bar_height_with_padding);
            return;
        }
        AppBarLayout appBarLayout = (AppBarLayout) getParent();
        if (appBarLayout.useCollapsedHeight()) {
            this.mDefaultHeight = appBarLayout.seslGetCollapsedHeight();
        } else {
            this.mDefaultHeight = getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_action_bar_height_with_padding);
        }
    }

    private void updateDummyView() {
        View view;
        if (!this.collapsingTitleEnabled && (view = this.dummyView) != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(this.dummyView);
            }
        }
        if (!this.collapsingTitleEnabled || this.toolbar == null) {
            return;
        }
        if (this.dummyView == null) {
            this.dummyView = new View(getContext());
        }
        if (this.dummyView.getParent() == null) {
            this.toolbar.addView(this.dummyView, -1, -1);
        }
    }

    private void updateTextBounds(int i5, int i7, int i9, int i10, boolean z9) {
        View view;
        if (!this.collapsingTitleEnabled || (view = this.dummyView) == null) {
            return;
        }
        WeakHashMap weakHashMap = W.f7199a;
        boolean z10 = view.isAttachedToWindow() && this.dummyView.getVisibility() == 0;
        this.drawCollapsingTitle = z10;
        if (z10 || z9) {
            boolean z11 = getLayoutDirection() == 1;
            updateCollapsedBounds(z11);
            this.collapsingTextHelper.setExpandedBounds(z11 ? this.expandedMarginEnd : this.expandedMarginStart, this.tmpRect.top + this.expandedMarginTop, (i9 - i5) - (z11 ? this.expandedMarginStart : this.expandedMarginEnd), (i10 - i7) - this.expandedMarginBottom);
            this.collapsingTextHelper.recalculate(z9);
        }
    }

    private void updateTitleFromToolbarIfNeeded() {
        if (this.toolbar != null && this.collapsingTitleEnabled && TextUtils.isEmpty(this.collapsingTextHelper.getText())) {
            setTitle(getToolbarTitle(this.toolbar));
        }
    }

    private void updateTitleLayout() {
        Resources resources = getResources();
        this.mHeightProportion = SeslAppBarHelper.INSTANCE.getAppBarProPortion(getContext());
        if (this.mTitleEnabled) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(this.mExtendTitleAppearance, AbstractC0478a.f10552C);
            TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(0);
            if (typedValuePeekValue == null) {
                Log.i(TAG, "ExtendTitleAppearance value is null");
                typedArrayObtainStyledAttributes.recycle();
                return;
            }
            float fComplexToFloat = TypedValue.complexToFloat(typedValuePeekValue.data);
            float fMin = Math.min(resources.getConfiguration().fontScale, 1.0f);
            typedArrayObtainStyledAttributes.recycle();
            Log.i(TAG, "updateTitleLayout : context : " + getContext() + ", textSize : " + fComplexToFloat + ", fontScale : " + fMin + ", mSubTitleEnabled : " + this.mSubTitleEnabled);
            if (this.mSubTitleEnabled) {
                this.mExtendedTitle.setTextSize(0, resources.getDimensionPixelSize(R.dimen.sesl_appbar_extended_title_text_size_with_subtitle));
                TextView textView = this.mExtendedSubTitle;
                if (textView != null) {
                    textView.setTextSize(0, resources.getDimensionPixelSize(R.dimen.sesl_appbar_extended_subtitle_text_size));
                }
            } else {
                this.mExtendedTitle.setTextSize(1, fComplexToFloat * fMin);
            }
            if (Math.abs(this.mHeightProportion - 0.3f) >= EPSILON || !this.mSubTitleEnabled) {
                this.mExtendedTitle.setSingleLine(false);
                this.mExtendedTitle.setMaxLines(2);
            } else {
                this.mExtendedTitle.setSingleLine(true);
                this.mExtendedTitle.setMaxLines(1);
            }
            int maxLines = this.mExtendedTitle.getMaxLines();
            if (s6.c.B() >= 120000) {
                if (maxLines > 1) {
                    try {
                        int statusbarHeight = getStatusbarHeight();
                        if (this.mSubTitleEnabled && statusbarHeight > 0) {
                            statusbarHeight += getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_action_bar_top_padding);
                        }
                        setBottomPadding(this.mTitleLayoutParent, statusbarHeight);
                    } catch (Exception e3) {
                        Log.e(TAG, Log.getStackTraceString(e3));
                    }
                } else {
                    this.mExtendedTitle.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
                    r.h(this.mExtendedTitle, 0);
                    this.mExtendedTitle.setTextSize(0, resources.getDimensionPixelSize(R.dimen.sesl_appbar_extended_title_text_size_with_subtitle));
                }
            }
        }
        Iterator<AppBarView> it = this.mSuggestViewHashMap.values().iterator();
        while (it.hasNext()) {
            it.next().updateResource(getContext());
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        LayoutParams layoutParams2;
        super.addView(view, layoutParams);
        if (!this.mTitleEnabled || (layoutParams2 = (LayoutParams) view.getLayoutParams()) == null) {
            return;
        }
        boolean zSeslIsTitleCustom = layoutParams2.seslIsTitleCustom();
        this.mIsCollapsingToolbarTitleCustom = zSeslIsTitleCustom;
        if (zSeslIsTitleCustom) {
            TextView textView = this.mExtendedTitle;
            if (textView != null && textView.getParent() == this.mTitleLayout) {
                this.mExtendedTitle.setVisibility(8);
            }
            TextView textView2 = this.mExtendedSubTitle;
            if (textView2 != null && textView2.getParent() == this.mTitleLayout) {
                this.mExtendedSubTitle.setVisibility(8);
            }
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            this.mTitleLayout.addView(view, layoutParams);
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        Drawable drawable;
        super.draw(canvas);
        ensureToolbar();
        if (this.toolbar == null && (drawable = this.contentScrim) != null && this.scrimAlpha > 0) {
            drawable.mutate().setAlpha(this.scrimAlpha);
            this.contentScrim.draw(canvas);
        }
        if (this.collapsingTitleEnabled && this.drawCollapsingTitle) {
            if (this.toolbar == null || this.contentScrim == null || this.scrimAlpha <= 0 || !isTitleCollapseFadeMode() || this.collapsingTextHelper.getExpansionFraction() >= this.collapsingTextHelper.getFadeModeThresholdFraction()) {
                this.collapsingTextHelper.draw(canvas);
            } else {
                int iSave = canvas.save();
                canvas.clipRect(this.contentScrim.getBounds(), Region.Op.DIFFERENCE);
                this.collapsingTextHelper.draw(canvas);
                canvas.restoreToCount(iSave);
            }
        }
        if (this.statusBarScrim == null || this.scrimAlpha <= 0) {
            return;
        }
        w0 w0Var = this.lastInsets;
        int iD = w0Var != null ? w0Var.d() : 0;
        if (iD > 0) {
            this.statusBarScrim.setBounds(0, -this.currentOffset, getWidth(), iD - this.currentOffset);
            this.statusBarScrim.mutate().setAlpha(this.scrimAlpha);
            this.statusBarScrim.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j5) {
        boolean z9;
        if (this.contentScrim == null || this.scrimAlpha <= 0 || !isToolbarChild(view)) {
            z9 = false;
        } else {
            updateContentScrimBounds(this.contentScrim, view, getWidth(), getHeight());
            this.contentScrim.mutate().setAlpha(this.scrimAlpha);
            this.contentScrim.draw(canvas);
            z9 = true;
        }
        return super.drawChild(canvas, view, j5) || z9;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.statusBarScrim;
        boolean state = (drawable == null || !drawable.isStateful()) ? false : drawable.setState(drawableState);
        Drawable drawable2 = this.contentScrim;
        if (drawable2 != null && drawable2.isStateful()) {
            state |= drawable2.setState(drawableState);
        }
        CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
        if (collapsingTextHelper != null) {
            state |= collapsingTextHelper.setState(drawableState);
        }
        if (state) {
            invalidate();
        }
    }

    public int getCollapsedTitleGravity() {
        if (this.collapsingTitleEnabled) {
            return this.collapsingTextHelper.getCollapsedTextGravity();
        }
        return 0;
    }

    public float getCollapsedTitleTextSize() {
        return this.collapsingTextHelper.getCollapsedTextSize();
    }

    public Typeface getCollapsedTitleTypeface() {
        return this.collapsingTitleEnabled ? this.collapsingTextHelper.getCollapsedTypeface() : Typeface.DEFAULT;
    }

    public Drawable getContentScrim() {
        return this.contentScrim;
    }

    public int getExpandedTitleGravity() {
        if (this.mTitleEnabled) {
            return this.mExtendedTitle.getGravity();
        }
        if (this.collapsingTitleEnabled) {
            return this.collapsingTextHelper.getExpandedTextGravity();
        }
        return 0;
    }

    public int getExpandedTitleMarginBottom() {
        return this.expandedMarginBottom;
    }

    public int getExpandedTitleMarginEnd() {
        return this.expandedMarginEnd;
    }

    public int getExpandedTitleMarginStart() {
        return this.expandedMarginStart;
    }

    public int getExpandedTitleMarginTop() {
        return this.expandedMarginTop;
    }

    public float getExpandedTitleTextSize() {
        return this.collapsingTextHelper.getExpandedTextSize();
    }

    public Typeface getExpandedTitleTypeface() {
        return this.mTitleEnabled ? this.mExtendedTitle.getTypeface() : this.collapsingTitleEnabled ? this.collapsingTextHelper.getExpandedTypeface() : Typeface.DEFAULT;
    }

    public int getHyphenationFrequency() {
        return this.collapsingTextHelper.getHyphenationFrequency();
    }

    public int getLineCount() {
        return this.collapsingTextHelper.getLineCount();
    }

    public float getLineSpacingAdd() {
        return this.collapsingTextHelper.getLineSpacingAdd();
    }

    public float getLineSpacingMultiplier() {
        return this.collapsingTextHelper.getLineSpacingMultiplier();
    }

    public int getMaxLines() {
        return this.collapsingTextHelper.getMaxLines();
    }

    public final int getMaxOffsetForPinChild(View view) {
        return ((getHeight() - getViewOffsetHelper(view).getLayoutTop()) - view.getHeight()) - ((FrameLayout.LayoutParams) ((LayoutParams) view.getLayoutParams())).bottomMargin;
    }

    public int getScrimAlpha() {
        return this.scrimAlpha;
    }

    public long getScrimAnimationDuration() {
        return this.scrimAnimationDuration;
    }

    public int getScrimVisibleHeightTrigger() {
        int i5 = this.scrimVisibleHeightTrigger;
        if (i5 >= 0) {
            return i5 + this.topInsetApplied + this.extraMultilineHeight;
        }
        w0 w0Var = this.lastInsets;
        int iD = w0Var != null ? w0Var.d() : 0;
        WeakHashMap weakHashMap = W.f7199a;
        int minimumHeight = getMinimumHeight();
        return minimumHeight > 0 ? Math.min((minimumHeight * 2) + iD, getHeight()) : getHeight() / 3;
    }

    public Drawable getStatusBarScrim() {
        return this.statusBarScrim;
    }

    public CharSequence getSubTitle() {
        TextView textView;
        TextView textView2 = this.mExtendedSubTitle;
        if (textView2 == null || textView2.getVisibility() != 0 || (textView = this.mExtendedSubTitle) == null) {
            return null;
        }
        return textView.getText();
    }

    public CharSequence getTitle() {
        return this.collapsingTitleEnabled ? this.collapsingTextHelper.getText() : this.mExtendedTitle.getText();
    }

    public int getTitleCollapseMode() {
        return this.titleCollapseMode;
    }

    public TimeInterpolator getTitlePositionInterpolator() {
        return this.collapsingTextHelper.getPositionInterpolator();
    }

    public TextUtils.TruncateAt getTitleTextEllipsize() {
        return this.collapsingTextHelper.getTitleTextEllipsize();
    }

    public boolean isExtraMultilineHeightEnabled() {
        return this.extraMultilineHeightEnabled;
    }

    public boolean isForceApplySystemWindowInsetTop() {
        return this.forceApplySystemWindowInsetTop;
    }

    public boolean isRtlTextDirectionHeuristicsEnabled() {
        return this.collapsingTextHelper.isRtlTextDirectionHeuristicsEnabled();
    }

    public boolean isTitleEnabled() {
        return this.mTitleEnabled;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            AppBarLayout appBarLayout = (AppBarLayout) parent;
            disableLiftOnScrollIfNeeded(appBarLayout);
            WeakHashMap weakHashMap = W.f7199a;
            setFitsSystemWindows(appBarLayout.getFitsSystemWindows());
            if (this.onOffsetChangedListener == null) {
                this.onOffsetChangedListener = new OffsetUpdateListener();
            }
            appBarLayout.addOnOffsetChangedListener(this.onOffsetChangedListener);
            K.c(this);
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.collapsingTitleEnabled) {
            this.collapsingTextHelper.maybeUpdateFontWeightAdjustment(configuration);
        }
        this.mHeightProportion = SeslAppBarHelper.INSTANCE.getAppBarProPortion(getContext());
        updateDefaultHeight();
        updateTitleLayout();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        ViewParent parent = getParent();
        AppBarLayout.OnOffsetChangedListener onOffsetChangedListener = this.onOffsetChangedListener;
        if (onOffsetChangedListener != null && (parent instanceof AppBarLayout)) {
            ((AppBarLayout) parent).removeOnOffsetChangedListener(onOffsetChangedListener);
        }
        super.onDetachedFromWindow();
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        super.onLayout(z9, i5, i7, i9, i10);
        w0 w0Var = this.lastInsets;
        if (w0Var != null) {
            int iD = w0Var.d();
            int childCount = getChildCount();
            for (int i11 = 0; i11 < childCount; i11++) {
                View childAt = getChildAt(i11);
                WeakHashMap weakHashMap = W.f7199a;
                if (!childAt.getFitsSystemWindows() && childAt.getTop() < iD) {
                    childAt.offsetTopAndBottom(iD);
                }
            }
        }
        int childCount2 = getChildCount();
        for (int i12 = 0; i12 < childCount2; i12++) {
            getViewOffsetHelper(getChildAt(i12)).onViewLayout();
        }
        updateTextBounds(i5, i7, i9, i10, false);
        updateTitleFromToolbarIfNeeded();
        updateScrimVisibility();
        int childCount3 = getChildCount();
        for (int i13 = 0; i13 < childCount3; i13++) {
            getViewOffsetHelper(getChildAt(i13)).applyOffsets();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i5, int i7) {
        ensureToolbar();
        super.onMeasure(i5, i7);
        int mode = View.MeasureSpec.getMode(i7);
        w0 w0Var = this.lastInsets;
        int iD = w0Var != null ? w0Var.d() : 0;
        if ((mode == 0 || this.forceApplySystemWindowInsetTop) && iD > 0) {
            this.topInsetApplied = iD;
            super.onMeasure(i5, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + iD, 1073741824));
        }
        if (this.extraMultilineHeightEnabled && this.collapsingTitleEnabled && this.collapsingTextHelper.getMaxLines() > 1) {
            updateTitleFromToolbarIfNeeded();
            updateTextBounds(0, 0, getMeasuredWidth(), getMeasuredHeight(), true);
            int expandedLineCount = this.collapsingTextHelper.getExpandedLineCount();
            if (expandedLineCount > 1) {
                this.extraMultilineHeight = (expandedLineCount - 1) * Math.round(this.collapsingTextHelper.getExpandedTextFullHeight());
                super.onMeasure(i5, View.MeasureSpec.makeMeasureSpec(getMeasuredHeight() + this.extraMultilineHeight, 1073741824));
            }
        }
        ViewGroup viewGroup = this.toolbar;
        if (viewGroup != null) {
            View view = this.toolbarDirectChild;
            if (view == null || view == this) {
                setMinimumHeight(getHeightWithMargins(viewGroup));
            } else {
                setMinimumHeight(getHeightWithMargins(view));
            }
        }
    }

    @Override // android.view.View
    public void onSizeChanged(int i5, int i7, int i9, int i10) {
        super.onSizeChanged(i5, i7, i9, i10);
        Drawable drawable = this.contentScrim;
        if (drawable != null) {
            updateContentScrimBounds(drawable, i5, i7);
        }
    }

    public w0 onWindowInsetChanged(w0 w0Var) {
        WeakHashMap weakHashMap = W.f7199a;
        w0 w0Var2 = getFitsSystemWindows() ? w0Var : null;
        if (!Objects.equals(this.lastInsets, w0Var2)) {
            this.lastInsets = w0Var2;
            requestLayout();
        }
        return w0Var.f7266a.c();
    }

    public void seslEnableFadeToolbarTitle(boolean z9) {
        this.mFadeToolbarTitle = z9;
    }

    public View seslGetCustomSubtitle() {
        return this.mCustomSubTitleView;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x001c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int seslGetMinimumHeightWithoutMargin() {
        /*
            r2 = this;
            android.view.ViewGroup r0 = r2.toolbar
            if (r0 == 0) goto L1c
            android.view.View r1 = r2.toolbarDirectChild
            if (r1 == 0) goto Lc
            if (r1 != r2) goto Lb
            goto Lc
        Lb:
            r0 = r1
        Lc:
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
            boolean r1 = r0 instanceof android.view.ViewGroup.MarginLayoutParams
            if (r1 == 0) goto L1c
            android.view.ViewGroup$MarginLayoutParams r0 = (android.view.ViewGroup.MarginLayoutParams) r0
            int r1 = r0.topMargin
            int r0 = r0.bottomMargin
            int r1 = r1 + r0
            goto L1d
        L1c:
            r1 = 0
        L1d:
            java.util.WeakHashMap r0 = androidx.core.view.W.f7199a
            int r2 = r2.getMinimumHeight()
            int r2 = r2 - r1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.CollapsingToolbarLayout.seslGetMinimumHeightWithoutMargin():int");
    }

    public boolean seslIsCustomAccessibility() {
        return this.mIsCustomAccessibility;
    }

    public void seslSetCustomSubtitle(View view) {
        if (view != null) {
            this.mSubTitleEnabled = true;
            this.mCustomSubTitleView = view;
            if (this.mTitleEnabled) {
                this.mTitleLayout.addView(view);
            }
        } else {
            this.mSubTitleEnabled = false;
            View view2 = this.mCustomSubTitleView;
            if (view2 != null) {
                ((ViewGroup) view2.getParent()).removeView(this.mCustomSubTitleView);
                this.mCustomSubTitleView = null;
            }
        }
        updateTitleLayout();
        requestLayout();
    }

    public void seslSetCustomTitleView(View view, LayoutParams layoutParams) {
        boolean zSeslIsTitleCustom = layoutParams.seslIsTitleCustom();
        this.mIsCollapsingToolbarTitleCustom = zSeslIsTitleCustom;
        if (!zSeslIsTitleCustom) {
            super.addView(view, layoutParams);
            return;
        }
        TextView textView = this.mExtendedTitle;
        if (textView != null && textView.getParent() == this.mTitleLayout) {
            this.mExtendedTitle.setVisibility(8);
        }
        TextView textView2 = this.mExtendedSubTitle;
        if (textView2 != null && textView2.getParent() == this.mTitleLayout) {
            this.mExtendedSubTitle.setVisibility(8);
        }
        this.mTitleLayout.addView(view, layoutParams);
    }

    public void seslSetSubtitle(int i5) {
        seslSetSubtitle(getContext().getText(i5));
    }

    public void seslSetSuggestView(AppBarModel appBarModel) {
        Iterator<AppBarModel> it = this.mSuggestViewHashMap.keySet().iterator();
        while (it.hasNext()) {
            this.mStackViewGroup.remove(this.mSuggestViewHashMap.get(it.next()));
        }
        this.mSuggestViewHashMap.clear();
        if (appBarModel != null) {
            AppBarView appBarViewCreate = appBarModel.create();
            this.mSuggestViewHashMap.put(appBarModel, appBarViewCreate);
            this.mStackViewGroup.push(appBarViewCreate);
        }
        if (getParent() instanceof AppBarLayout) {
            ((AppBarLayout) getParent()).seslSetSuggestion(!this.mSuggestViewHashMap.isEmpty());
        }
    }

    public void seslSetUseCustomAccessibility(boolean z9) {
        this.mIsCustomAccessibility = z9;
    }

    public void setCollapsedTitleGravity(int i5) {
        if (this.collapsingTitleEnabled) {
            this.collapsingTextHelper.setCollapsedTextGravity(i5);
        }
    }

    public void setCollapsedTitleTextAppearance(int i5) {
        if (this.collapsingTitleEnabled) {
            this.collapsingTextHelper.setCollapsedTextAppearance(i5);
        }
    }

    public void setCollapsedTitleTextColor(int i5) {
        setCollapsedTitleTextColor(ColorStateList.valueOf(i5));
    }

    public void setCollapsedTitleTextSize(float f2) {
        this.collapsingTextHelper.setCollapsedTextSize(f2);
    }

    public void setCollapsedTitleTypeface(Typeface typeface) {
        if (this.collapsingTitleEnabled) {
            this.collapsingTextHelper.setCollapsedTypeface(typeface);
        }
    }

    public void setContentScrim(Drawable drawable) {
        Drawable drawable2 = this.contentScrim;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.contentScrim = drawableMutate;
            if (drawableMutate != null) {
                drawableMutate.setBounds(0, 0, getWidth(), getHeight());
                this.contentScrim.setCallback(this);
                this.contentScrim.setAlpha(this.scrimAlpha);
            }
            WeakHashMap weakHashMap = W.f7199a;
            postInvalidateOnAnimation();
        }
    }

    public void setContentScrimColor(int i5) {
        setContentScrim(new ColorDrawable(i5));
    }

    public void setContentScrimResource(int i5) {
        setContentScrim(B.a.b(getContext(), i5));
    }

    public void setExpandedTitleColor(int i5) {
        setExpandedTitleTextColor(ColorStateList.valueOf(i5));
    }

    public void setExpandedTitleGravity(int i5) {
        if (this.mTitleEnabled) {
            this.mExtendedTitle.setGravity(i5);
        } else if (this.collapsingTitleEnabled) {
            this.collapsingTextHelper.setExpandedTextGravity(i5);
        }
    }

    public void setExpandedTitleMargin(int i5, int i7, int i9, int i10) {
        this.expandedMarginStart = i5;
        this.expandedMarginTop = i7;
        this.expandedMarginEnd = i9;
        this.expandedMarginBottom = i10;
        requestLayout();
    }

    public void setExpandedTitleMarginBottom(int i5) {
        this.expandedMarginBottom = i5;
        requestLayout();
    }

    public void setExpandedTitleMarginEnd(int i5) {
        this.expandedMarginEnd = i5;
        requestLayout();
    }

    public void setExpandedTitleMarginStart(int i5) {
        this.expandedMarginStart = i5;
        requestLayout();
    }

    public void setExpandedTitleMarginTop(int i5) {
        this.expandedMarginTop = i5;
        requestLayout();
    }

    public void setExpandedTitleTextAppearance(int i5) {
        if (this.mTitleEnabled) {
            this.mExtendedTitle.setTextAppearance(getContext(), i5);
        } else if (this.collapsingTitleEnabled) {
            this.collapsingTextHelper.setExpandedTextAppearance(i5);
        }
    }

    public void setExpandedTitleTextColor(ColorStateList colorStateList) {
        if (this.mTitleEnabled) {
            this.mExtendedTitle.setTextColor(colorStateList);
        } else if (this.collapsingTitleEnabled) {
            this.collapsingTextHelper.setExpandedTextColor(colorStateList);
        }
    }

    public void setExpandedTitleTextSize(float f2) {
        this.collapsingTextHelper.setExpandedTextSize(f2);
    }

    public void setExpandedTitleTypeface(Typeface typeface) {
        if (this.mTitleEnabled) {
            this.mExtendedTitle.setTypeface(typeface);
        } else if (this.collapsingTitleEnabled) {
            this.collapsingTextHelper.setExpandedTypeface(typeface);
        }
    }

    public void setExtraMultilineHeightEnabled(boolean z9) {
        this.extraMultilineHeightEnabled = z9;
    }

    public void setForceApplySystemWindowInsetTop(boolean z9) {
        this.forceApplySystemWindowInsetTop = z9;
    }

    public void setHyphenationFrequency(int i5) {
        this.collapsingTextHelper.setHyphenationFrequency(i5);
    }

    public void setLineSpacingAdd(float f2) {
        this.collapsingTextHelper.setLineSpacingAdd(f2);
    }

    public void setLineSpacingMultiplier(float f2) {
        this.collapsingTextHelper.setLineSpacingMultiplier(f2);
    }

    public void setMaxLines(int i5) {
        this.collapsingTextHelper.setMaxLines(i5);
    }

    public void setRtlTextDirectionHeuristicsEnabled(boolean z9) {
        this.collapsingTextHelper.setRtlTextDirectionHeuristicsEnabled(z9);
    }

    public void setScrimAlpha(int i5) {
        ViewGroup viewGroup;
        if (i5 != this.scrimAlpha) {
            if (this.contentScrim != null && (viewGroup = this.toolbar) != null) {
                WeakHashMap weakHashMap = W.f7199a;
                viewGroup.postInvalidateOnAnimation();
            }
            this.scrimAlpha = i5;
            WeakHashMap weakHashMap2 = W.f7199a;
            postInvalidateOnAnimation();
        }
    }

    public void setScrimAnimationDuration(long j5) {
        this.scrimAnimationDuration = j5;
    }

    public void setScrimVisibleHeightTrigger(int i5) {
        if (this.scrimVisibleHeightTrigger != i5) {
            this.scrimVisibleHeightTrigger = i5;
            updateScrimVisibility();
        }
    }

    public void setScrimsShown(boolean z9) {
        WeakHashMap weakHashMap = W.f7199a;
        setScrimsShown(z9, isLaidOut() && !isInEditMode());
    }

    public void setStaticLayoutBuilderConfigurer(StaticLayoutBuilderConfigurer staticLayoutBuilderConfigurer) {
        this.collapsingTextHelper.setStaticLayoutBuilderConfigurer(staticLayoutBuilderConfigurer);
    }

    public void setStatusBarScrim(Drawable drawable) {
        Drawable drawable2 = this.statusBarScrim;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.statusBarScrim = drawableMutate;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.statusBarScrim.setState(getDrawableState());
                }
                Drawable drawable3 = this.statusBarScrim;
                WeakHashMap weakHashMap = W.f7199a;
                E.b.b(drawable3, getLayoutDirection());
                this.statusBarScrim.setVisible(getVisibility() == 0, false);
                this.statusBarScrim.setCallback(this);
                this.statusBarScrim.setAlpha(this.scrimAlpha);
            }
            WeakHashMap weakHashMap2 = W.f7199a;
            postInvalidateOnAnimation();
        }
    }

    public void setStatusBarScrimColor(int i5) {
        setStatusBarScrim(new ColorDrawable(i5));
    }

    public void setStatusBarScrimResource(int i5) {
        setStatusBarScrim(B.a.b(getContext(), i5));
    }

    public void setTitle(CharSequence charSequence) {
        if (this.collapsingTitleEnabled) {
            this.collapsingTextHelper.setText(charSequence);
            updateContentDescriptionFromTitle();
        } else {
            TextView textView = this.mExtendedTitle;
            if (textView != null) {
                textView.setText(charSequence);
            }
        }
        updateTitleLayout();
    }

    public void setTitleCollapseMode(int i5) {
        this.titleCollapseMode = i5;
        boolean zIsTitleCollapseFadeMode = isTitleCollapseFadeMode();
        this.collapsingTextHelper.setFadeModeEnabled(zIsTitleCollapseFadeMode);
        ViewParent parent = getParent();
        if (parent instanceof AppBarLayout) {
            disableLiftOnScrollIfNeeded((AppBarLayout) parent);
        }
        if (zIsTitleCollapseFadeMode && this.contentScrim == null) {
            setContentScrimColor(getDefaultContentScrimColorForTitleCollapseFadeMode());
        }
    }

    public void setTitleEllipsize(TextUtils.TruncateAt truncateAt) {
        this.collapsingTextHelper.setTitleTextEllipsize(truncateAt);
    }

    public void setTitleEnabled(boolean z9) {
        TextView textView;
        if (!z9) {
            this.mTitleEnabled = false;
            this.collapsingTitleEnabled = false;
        } else if (this.mExtendedTitle != null) {
            this.mTitleEnabled = true;
        } else {
            this.mTitleEnabled = false;
        }
        if (!z9 && !this.mTitleEnabled && (textView = this.mExtendedTitle) != null) {
            textView.setVisibility(4);
        }
        if (z9 && this.collapsingTitleEnabled) {
            updateDummyView();
            requestLayout();
        }
    }

    public void setTitlePositionInterpolator(TimeInterpolator timeInterpolator) {
        this.collapsingTextHelper.setPositionInterpolator(timeInterpolator);
    }

    @Override // android.view.View
    public void setVisibility(int i5) {
        super.setVisibility(i5);
        boolean z9 = i5 == 0;
        Drawable drawable = this.statusBarScrim;
        if (drawable != null && drawable.isVisible() != z9) {
            this.statusBarScrim.setVisible(z9, false);
        }
        Drawable drawable2 = this.contentScrim;
        if (drawable2 == null || drawable2.isVisible() == z9) {
            return;
        }
        this.contentScrim.setVisible(z9, false);
    }

    public final void updateScrimVisibility() {
        if (this.contentScrim == null && this.statusBarScrim == null) {
            return;
        }
        setScrimsShown(getHeight() + this.currentOffset < getScrimVisibleHeightTrigger());
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.contentScrim || drawable == this.statusBarScrim;
    }

    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.collapsingToolbarLayoutStyle);
    }

    private void updateContentScrimBounds(Drawable drawable, View view, int i5, int i7) {
        if (isTitleCollapseFadeMode() && view != null && this.collapsingTitleEnabled) {
            i7 = view.getBottom();
        }
        drawable.setBounds(0, 0, i5, i7);
    }

    public void seslSetSubtitle(CharSequence charSequence) {
        if (!this.mTitleEnabled || TextUtils.isEmpty(charSequence)) {
            this.mSubTitleEnabled = false;
            TextView textView = this.mExtendedSubTitle;
            if (textView != null) {
                textView.setVisibility(8);
            }
        } else {
            this.mSubTitleEnabled = true;
            if (this.mExtendedSubTitle == null) {
                this.mExtendedSubTitle = initSubTitle();
            }
            this.mExtendedSubTitle.setText(charSequence);
            this.mExtendedSubTitle.setVisibility(0);
            TextView textView2 = this.mExtendedTitle;
            if (textView2 != null) {
                textView2.setTextSize(0, getContext().getResources().getDimensionPixelSize(R.dimen.sesl_appbar_extended_title_text_size_with_subtitle));
            }
        }
        updateTitleLayout();
        requestLayout();
    }

    public void setCollapsedTitleTextColor(ColorStateList colorStateList) {
        if (this.collapsingTitleEnabled) {
            this.collapsingTextHelper.setCollapsedTextColor(colorStateList);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public CollapsingToolbarLayout(Context context, AttributeSet attributeSet, int i5) {
        int i7 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(context, attributeSet, i5, i7), attributeSet, i5);
        this.refreshToolbar = true;
        this.tmpRect = new Rect();
        this.scrimVisibleHeightTrigger = -1;
        this.topInsetApplied = 0;
        this.extraMultilineHeight = 0;
        this.mIsCustomAccessibility = false;
        this.mSuggestViewHashMap = new HashMap<>();
        this.mCustomSubTitleView = null;
        this.mFadeToolbarTitle = true;
        Context context2 = getContext();
        TypedArray typedArrayObtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R.styleable.CollapsingToolbarLayout, i5, i7, new int[0]);
        this.collapsingTitleEnabled = typedArrayObtainStyledAttributes.getBoolean(R.styleable.CollapsingToolbarLayout_titleEnabled, false);
        boolean z9 = typedArrayObtainStyledAttributes.getBoolean(R.styleable.CollapsingToolbarLayout_extendedTitleEnabled, true);
        this.mTitleEnabled = z9;
        boolean z10 = this.collapsingTitleEnabled;
        if (z10 == z9 && z10) {
            this.collapsingTitleEnabled = false;
        }
        CollapsingTextHelper collapsingTextHelper = new CollapsingTextHelper(this);
        this.collapsingTextHelper = collapsingTextHelper;
        if (this.collapsingTitleEnabled) {
            collapsingTextHelper.setTextSizeInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
            collapsingTextHelper.setRtlTextDirectionHeuristicsEnabled(false);
            collapsingTextHelper.setExpandedTextGravity(typedArrayObtainStyledAttributes.getInt(R.styleable.CollapsingToolbarLayout_expandedTitleGravity, 8388691));
            collapsingTextHelper.setCollapsedTextGravity(typedArrayObtainStyledAttributes.getInt(R.styleable.CollapsingToolbarLayout_collapsedTitleGravity, 8388627));
            int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_expandedTitleMargin, 0);
            this.expandedMarginBottom = dimensionPixelSize;
            this.expandedMarginEnd = dimensionPixelSize;
            this.expandedMarginTop = dimensionPixelSize;
            this.expandedMarginStart = dimensionPixelSize;
        }
        this.elevationOverlayProvider = new ElevationOverlayProvider(context2);
        this.mExtendTitleAppearance = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CollapsingToolbarLayout_extendedTitleTextAppearance, 0);
        this.mExtendSubTitleAppearance = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CollapsingToolbarLayout_extendedSubtitleTextAppearance, 0);
        int i9 = R.styleable.CollapsingToolbarLayout_expandedTitleTextAppearance;
        if (typedArrayObtainStyledAttributes.hasValue(i9)) {
            this.mExtendTitleAppearance = typedArrayObtainStyledAttributes.getResourceId(i9, 0);
        }
        CharSequence text = typedArrayObtainStyledAttributes.getText(R.styleable.CollapsingToolbarLayout_subtitle);
        this.mSubTitleEnabled = this.mTitleEnabled && !TextUtils.isEmpty(text);
        initTitleLayout(context2, text);
        updateDefaultHeight();
        updateTitleLayout();
        int i10 = R.styleable.CollapsingToolbarLayout_expandedTitleMarginStart;
        if (typedArrayObtainStyledAttributes.hasValue(i10)) {
            this.expandedMarginStart = typedArrayObtainStyledAttributes.getDimensionPixelSize(i10, 0);
        }
        int i11 = R.styleable.CollapsingToolbarLayout_expandedTitleMarginEnd;
        if (typedArrayObtainStyledAttributes.hasValue(i11)) {
            this.expandedMarginEnd = typedArrayObtainStyledAttributes.getDimensionPixelSize(i11, 0);
        }
        int i12 = R.styleable.CollapsingToolbarLayout_expandedTitleMarginTop;
        if (typedArrayObtainStyledAttributes.hasValue(i12)) {
            this.expandedMarginTop = typedArrayObtainStyledAttributes.getDimensionPixelSize(i12, 0);
        }
        int i13 = R.styleable.CollapsingToolbarLayout_expandedTitleMarginBottom;
        if (typedArrayObtainStyledAttributes.hasValue(i13)) {
            this.expandedMarginBottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(i13, 0);
        }
        setTitle(typedArrayObtainStyledAttributes.getText(R.styleable.CollapsingToolbarLayout_title));
        if (this.collapsingTitleEnabled) {
            collapsingTextHelper.setExpandedTextAppearance(R.style.TextAppearance_Design_CollapsingToolbar_Expanded);
            collapsingTextHelper.setCollapsedTextAppearance(2132017810);
            if (typedArrayObtainStyledAttributes.hasValue(i9)) {
                collapsingTextHelper.setExpandedTextAppearance(typedArrayObtainStyledAttributes.getResourceId(i9, 0));
            }
            int i14 = R.styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance;
            if (typedArrayObtainStyledAttributes.hasValue(i14)) {
                collapsingTextHelper.setCollapsedTextAppearance(typedArrayObtainStyledAttributes.getResourceId(i14, 0));
            }
            int i15 = R.styleable.CollapsingToolbarLayout_titleTextEllipsize;
            if (typedArrayObtainStyledAttributes.hasValue(i15)) {
                setTitleEllipsize(convertEllipsizeToTruncateAt(typedArrayObtainStyledAttributes.getInt(i15, -1)));
            }
            int i16 = R.styleable.CollapsingToolbarLayout_expandedTitleTextColor;
            if (typedArrayObtainStyledAttributes.hasValue(i16)) {
                collapsingTextHelper.setExpandedTextColor(MaterialResources.getColorStateList(context2, typedArrayObtainStyledAttributes, i16));
            }
            int i17 = R.styleable.CollapsingToolbarLayout_collapsedTitleTextColor;
            if (typedArrayObtainStyledAttributes.hasValue(i17)) {
                collapsingTextHelper.setCollapsedTextColor(MaterialResources.getColorStateList(context2, typedArrayObtainStyledAttributes, i17));
            }
        }
        this.scrimVisibleHeightTrigger = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.CollapsingToolbarLayout_scrimVisibleHeightTrigger, -1);
        int i18 = R.styleable.CollapsingToolbarLayout_maxLines;
        if (typedArrayObtainStyledAttributes.hasValue(i18)) {
            collapsingTextHelper.setMaxLines(typedArrayObtainStyledAttributes.getInt(i18, 1));
        }
        int i19 = R.styleable.CollapsingToolbarLayout_titlePositionInterpolator;
        if (typedArrayObtainStyledAttributes.hasValue(i19)) {
            collapsingTextHelper.setPositionInterpolator(android.view.animation.AnimationUtils.loadInterpolator(context2, typedArrayObtainStyledAttributes.getResourceId(i19, 0)));
        }
        this.scrimAnimationDuration = typedArrayObtainStyledAttributes.getInt(R.styleable.CollapsingToolbarLayout_scrimAnimationDuration, DEFAULT_SCRIM_ANIMATION_DURATION);
        int i20 = R.attr.motionEasingStandardInterpolator;
        this.scrimAnimationFadeInInterpolator = MotionUtils.resolveThemeInterpolator(context2, i20, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        this.scrimAnimationFadeOutInterpolator = MotionUtils.resolveThemeInterpolator(context2, i20, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        setContentScrim(typedArrayObtainStyledAttributes.getDrawable(R.styleable.CollapsingToolbarLayout_contentScrim));
        setStatusBarScrim(typedArrayObtainStyledAttributes.getDrawable(R.styleable.CollapsingToolbarLayout_statusBarScrim));
        this.toolbarId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.CollapsingToolbarLayout_toolbarId, -1);
        this.forceApplySystemWindowInsetTop = typedArrayObtainStyledAttributes.getBoolean(R.styleable.CollapsingToolbarLayout_forceApplySystemWindowInsetTop, false);
        this.extraMultilineHeightEnabled = typedArrayObtainStyledAttributes.getBoolean(R.styleable.CollapsingToolbarLayout_extraMultilineHeightEnabled, false);
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(AbstractC0478a.f10564j);
        if (!typedArrayObtainStyledAttributes2.getBoolean(147, false)) {
            LayoutInflater.from(context2).inflate(R.layout.sesl_material_action_mode_view_stub, (ViewGroup) this, true);
            this.mViewStubCompat = (ViewStubCompat) findViewById(R.id.action_mode_bar_stub);
        }
        typedArrayObtainStyledAttributes2.recycle();
        setWillNotDraw(false);
        InterfaceC0226s interfaceC0226s = new InterfaceC0226s() { // from class: com.google.android.material.appbar.CollapsingToolbarLayout.1
            @Override // androidx.core.view.InterfaceC0226s
            public w0 onApplyWindowInsets(View view, w0 w0Var) {
                return CollapsingToolbarLayout.this.onWindowInsetChanged(w0Var);
            }
        };
        WeakHashMap weakHashMap = W.f7199a;
        M.u(this, interfaceC0226s);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    public void setScrimsShown(boolean z9, boolean z10) {
        if (this.scrimsAreShown != z9) {
            if (z10) {
                animateScrim(z9 ? 255 : 0);
            } else {
                setScrimAlpha(z9 ? 255 : 0);
            }
            this.scrimsAreShown = z9;
        }
    }

    public static class LayoutParams extends FrameLayout.LayoutParams {
        public static final int COLLAPSE_MODE_OFF = 0;
        public static final int COLLAPSE_MODE_PARALLAX = 2;
        public static final int COLLAPSE_MODE_PIN = 1;
        private static final float DEFAULT_PARALLAX_MULTIPLIER = 0.5f;
        int collapseMode;
        private boolean isTitleCustom;
        float parallaxMult;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.collapseMode = 0;
            this.parallaxMult = 0.5f;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CollapsingToolbarLayout_Layout);
            this.collapseMode = typedArrayObtainStyledAttributes.getInt(R.styleable.CollapsingToolbarLayout_Layout_layout_collapseMode, 0);
            setParallaxMultiplier(typedArrayObtainStyledAttributes.getFloat(R.styleable.CollapsingToolbarLayout_Layout_layout_collapseParallaxMultiplier, 0.5f));
            this.isTitleCustom = typedArrayObtainStyledAttributes.getBoolean(R.styleable.CollapsingToolbarLayout_Layout_isCustomTitle, false);
            typedArrayObtainStyledAttributes.recycle();
        }

        public int getCollapseMode() {
            return this.collapseMode;
        }

        public float getParallaxMultiplier() {
            return this.parallaxMult;
        }

        public boolean seslIsTitleCustom() {
            return this.isTitleCustom;
        }

        public void seslSetIsTitleCustom(Boolean bool) {
            this.isTitleCustom = bool.booleanValue();
        }

        public void setCollapseMode(int i5) {
            this.collapseMode = i5;
        }

        public void setParallaxMultiplier(float f2) {
            this.parallaxMult = f2;
        }

        public LayoutParams(int i5, int i7) {
            super(i5, i7);
            this.collapseMode = 0;
            this.parallaxMult = 0.5f;
        }

        public LayoutParams(int i5, int i7, int i9) {
            super(i5, i7, i9);
            this.collapseMode = 0;
            this.parallaxMult = 0.5f;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.collapseMode = 0;
            this.parallaxMult = 0.5f;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.collapseMode = 0;
            this.parallaxMult = 0.5f;
        }

        public LayoutParams(FrameLayout.LayoutParams layoutParams) {
            super(layoutParams);
            this.collapseMode = 0;
            this.parallaxMult = 0.5f;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((FrameLayout.LayoutParams) layoutParams);
            this.collapseMode = 0;
            this.parallaxMult = 0.5f;
            this.collapseMode = layoutParams.collapseMode;
            this.parallaxMult = layoutParams.parallaxMult;
        }
    }
}
