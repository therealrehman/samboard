package com.google.android.material.tabs;

import C.o;
import K.d;
import K.f;
import L.j;
import L.k;
import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.provider.Settings;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewDebug;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.d2;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.e;
import androidx.core.view.M;
import androidx.core.view.W;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.a;
import androidx.viewpager.widget.b;
import androidx.viewpager.widget.g;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import e.AbstractC0478a;
import f.AbstractC0510a;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;
import s6.c;

/* JADX INFO: loaded from: classes.dex */
@b
public class TabLayout extends HorizontalScrollView {
    private static final int ANIMATION_DURATION = 300;
    private static final int ANIM_HIDE_DURATION = 400;
    private static final float ANIM_RIPPLE_MINOR_SCALE = 0.95f;
    private static final int ANIM_SHOW_DURATION = 350;
    private static final int BADGE_N_TEXT_SIZE = 11;
    private static final int BADGE_TYPE_DOT = 2;
    private static final int BADGE_TYPE_N = 1;
    private static final int BADGE_TYPE_UNKNOWN = -1;
    static final int DEFAULT_GAP_TEXT_ICON = 8;
    private static final int DEFAULT_HEIGHT = 48;
    private static final int DEFAULT_HEIGHT_WITH_TEXT_ICON = 72;
    private static final int DEPTH_TYPE_CUSTOM = 3;
    private static final int DEPTH_TYPE_MAIN = 1;
    private static final int DEPTH_TYPE_SUB = 2;
    static final int FIXED_WRAP_GUTTER_MIN = 16;
    private static final int FONT_WEIGHT_REGULAR = 400;
    private static final int FONT_WEIGHT_SEMIBOLD = 600;
    public static final int GRAVITY_CENTER = 1;
    public static final int GRAVITY_FILL = 0;
    public static final int GRAVITY_START = 2;
    public static final int INDICATOR_ANIMATION_MODE_ELASTIC = 1;
    public static final int INDICATOR_ANIMATION_MODE_FADE = 2;
    public static final int INDICATOR_ANIMATION_MODE_LINEAR = 0;
    public static final int INDICATOR_GRAVITY_BOTTOM = 0;
    public static final int INDICATOR_GRAVITY_CENTER = 1;
    public static final int INDICATOR_GRAVITY_STRETCH = 3;
    public static final int INDICATOR_GRAVITY_TOP = 2;
    private static final int INVALID_WIDTH = -1;
    private static final String LOG_TAG = "TabLayout";
    public static final int MODE_AUTO = 2;
    public static final int MODE_FIXED = 1;
    public static final int MODE_SCROLLABLE = 0;
    private static final int SELECTED_INDICATOR_HEIGHT_DEFAULT = -1;
    private static final int SESL_DEFAULT_HEIGHT = 60;
    public static final int SESL_MODE_FIXED_AUTO = 11;
    public static final int SESL_MODE_WEIGHT_AUTO = 12;
    private static final int SESL_SUB_DEPTH_DEFAULT_HEIGHT = 56;
    public static final int TAB_LABEL_VISIBILITY_LABELED = 1;
    public static final int TAB_LABEL_VISIBILITY_UNLABELED = 0;
    private static final int TAB_MIN_WIDTH_MARGIN = 56;
    private AdapterChangeListener adapterChangeListener;
    private int contentInsetStart;
    private BaseOnTabSelectedListener currentVpSelectedListener;
    private final int defaultTabTextAppearance;
    int indicatorPosition;
    boolean inlineLabel;
    private ColorDrawable mBackgroundColorDrawable;
    private int mBadgeColor;
    private int mBadgeTextColor;
    private Typeface mBoldTypeface;
    private ContentResolver mContentResolver;
    private int mCurrentTouchSlop;
    private final int mDefaultTouchSlop;
    private int mDepthStyle;
    private int mFirstTabGravity;
    private int mIconTextGap;
    private boolean mIsChangedGravityByLocal;
    private boolean mIsOverScreen;
    private boolean mIsScaledTextSizeType;
    private int mMaxTouchSlop;
    private Typeface mNormalTypeface;
    private int mOverScreenMaxWidth;
    private int mRequestedTabWidth;
    private int mSubTabIndicator2ndHeight;
    private int mSubTabIndicatorHeight;
    private int mSubTabSelectedIndicatorColor;
    int mSubTabSubTextAppearance;
    ColorStateList mSubTabSubTextColors;
    int mSubTabTextSize;
    private int mTabMinSideSpace;
    private int mTabSelectedIndicatorColor;

    @ViewDebug.ExportedProperty(category = "tablayout")
    int mode;
    private TabLayoutOnPageChangeListener pageChangeListener;
    private a pagerAdapter;
    private DataSetObserver pagerAdapterObserver;
    private final int requestedTabMaxWidth;
    private final int requestedTabMinWidth;
    private ValueAnimator scrollAnimator;
    private final int scrollableTabMinWidth;
    private BaseOnTabSelectedListener selectedListener;
    private final ArrayList<BaseOnTabSelectedListener> selectedListeners;
    private Tab selectedTab;
    private int selectedTabTextAppearance;
    float selectedTabTextSize;
    private boolean setupViewPagerImplicitly;
    final SlidingTabIndicator slidingTabIndicator;
    final int tabBackgroundResId;
    int tabGravity;
    ColorStateList tabIconTint;
    PorterDuff.Mode tabIconTintMode;
    int tabIndicatorAnimationDuration;
    int tabIndicatorAnimationMode;
    boolean tabIndicatorFullWidth;
    int tabIndicatorGravity;
    int tabIndicatorHeight;
    private TabIndicatorInterpolator tabIndicatorInterpolator;
    private final TimeInterpolator tabIndicatorTimeInterpolator;
    int tabMaxWidth;
    int tabPaddingBottom;
    int tabPaddingEnd;
    int tabPaddingStart;
    int tabPaddingTop;
    ColorStateList tabRippleColorStateList;
    Drawable tabSelectedIndicator;
    private int tabSelectedIndicatorColor;
    private final int tabTextAppearance;
    ColorStateList tabTextColors;
    float tabTextMultiLineSize;
    float tabTextSize;
    private final d tabViewPool;
    private final ArrayList<Tab> tabs;
    boolean unboundedRipple;
    ViewPager viewPager;
    private int viewPagerScrollState;
    private static final int DEF_STYLE_RES = R.style.Widget_Design_TabLayout;
    private static final d tabPool = new f(16);

    public class AdapterChangeListener implements androidx.viewpager.widget.f {
        private boolean autoRefresh;

        public AdapterChangeListener() {
        }

        @Override // androidx.viewpager.widget.f
        public void onAdapterChanged(ViewPager viewPager, a aVar, a aVar2) {
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.viewPager == viewPager) {
                tabLayout.setPagerAdapter(aVar2, this.autoRefresh);
            }
        }

        public void setAutoRefresh(boolean z9) {
            this.autoRefresh = z9;
        }
    }

    @Deprecated
    public interface BaseOnTabSelectedListener<T extends Tab> {
        void onTabReselected(T t8);

        void onTabSelected(T t8);

        void onTabUnselected(T t8);
    }

    public @interface LabelVisibility {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    public interface OnTabSelectedListener extends BaseOnTabSelectedListener<Tab> {
    }

    public class PagerAdapterObserver extends DataSetObserver {
        public PagerAdapterObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            TabLayout.this.populateFromPagerAdapter();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            TabLayout.this.populateFromPagerAdapter();
        }
    }

    public class SlidingTabIndicator extends LinearLayout {
        ValueAnimator indicatorAnimator;
        private int layoutDirection;

        public SlidingTabIndicator(Context context) {
            super(context);
            this.layoutDirection = -1;
            setWillNotDraw(false);
        }

        private void jumpIndicatorToIndicatorPosition() {
        }

        private void jumpIndicatorToPosition(int i5) {
            if (TabLayout.this.viewPagerScrollState == 0 || (TabLayout.this.getTabSelectedIndicator().getBounds().left == -1 && TabLayout.this.getTabSelectedIndicator().getBounds().right == -1)) {
                View childAt = getChildAt(i5);
                TabIndicatorInterpolator tabIndicatorInterpolator = TabLayout.this.tabIndicatorInterpolator;
                TabLayout tabLayout = TabLayout.this;
                tabIndicatorInterpolator.setIndicatorBoundsForTab(tabLayout, childAt, tabLayout.tabSelectedIndicator);
                TabLayout.this.indicatorPosition = i5;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void jumpIndicatorToSelectedPosition() {
        }

        private void tweenIndicatorPosition(View view, View view2, float f2) {
            if (view == null || view.getWidth() <= 0) {
                Drawable drawable = TabLayout.this.tabSelectedIndicator;
                drawable.setBounds(-1, drawable.getBounds().top, -1, TabLayout.this.tabSelectedIndicator.getBounds().bottom);
            } else {
                TabIndicatorInterpolator tabIndicatorInterpolator = TabLayout.this.tabIndicatorInterpolator;
                TabLayout tabLayout = TabLayout.this;
                tabIndicatorInterpolator.updateIndicatorForOffset(tabLayout, view, view2, f2, tabLayout.tabSelectedIndicator);
            }
            WeakHashMap weakHashMap = W.f7199a;
            postInvalidateOnAnimation();
        }

        private void updateOrRecreateIndicatorAnimation(boolean z9, int i5, int i7) {
        }

        public void animateIndicatorToPosition(int i5, int i7) {
        }

        public boolean childrenNeedLayout() {
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                if (getChildAt(i5).getWidth() <= 0) {
                    return true;
                }
            }
            return false;
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            super.draw(canvas);
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
            super.onLayout(z9, i5, i7, i9, i10);
            ValueAnimator valueAnimator = this.indicatorAnimator;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                jumpIndicatorToIndicatorPosition();
            } else {
                updateOrRecreateIndicatorAnimation(false, TabLayout.this.getSelectedTabPosition(), -1);
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i5, int i7) {
            super.onMeasure(i5, i7);
            if (View.MeasureSpec.getMode(i5) != 1073741824) {
                return;
            }
            TabLayout tabLayout = TabLayout.this;
            int i9 = tabLayout.mode;
            int i10 = 0;
            boolean z9 = true;
            if (i9 == 11 || i9 == 12) {
                tabLayout.checkOverScreen();
                int size = TabLayout.this.mIsOverScreen ? TabLayout.this.mOverScreenMaxWidth : View.MeasureSpec.getSize(i5);
                int childCount = getChildCount();
                int[] iArr = new int[childCount];
                int i11 = 0;
                for (int i12 = 0; i12 < childCount; i12++) {
                    View childAt = getChildAt(i12);
                    if (childAt.getVisibility() == 0) {
                        childAt.measure(View.MeasureSpec.makeMeasureSpec(TabLayout.this.tabMaxWidth, 0), i7);
                        int measuredWidth = (TabLayout.this.mTabMinSideSpace * 2) + childAt.getMeasuredWidth();
                        iArr[i12] = measuredWidth;
                        i11 += measuredWidth;
                    }
                }
                int i13 = size / childCount;
                if (i11 > size) {
                    while (i10 < childCount) {
                        ((LinearLayout.LayoutParams) getChildAt(i10).getLayoutParams()).width = iArr[i10];
                        i10++;
                    }
                } else {
                    if (TabLayout.this.mode == 11) {
                        int i14 = 0;
                        while (true) {
                            if (i14 >= childCount) {
                                z9 = false;
                                break;
                            } else if (iArr[i14] > i13) {
                                break;
                            } else {
                                i14++;
                            }
                        }
                    }
                    if (z9) {
                        int i15 = (size - i11) / childCount;
                        while (i10 < childCount) {
                            ((LinearLayout.LayoutParams) getChildAt(i10).getLayoutParams()).width = iArr[i10] + i15;
                            i10++;
                        }
                    } else {
                        while (i10 < childCount) {
                            ((LinearLayout.LayoutParams) getChildAt(i10).getLayoutParams()).width = i13;
                            i10++;
                        }
                    }
                }
                if (i11 > size) {
                    size = i11;
                }
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), i7);
                return;
            }
            if (tabLayout.tabGravity == 1 || i9 == 2 || tabLayout.mFirstTabGravity == 1) {
                int childCount2 = getChildCount();
                TabLayout tabLayout2 = TabLayout.this;
                if (tabLayout2.tabGravity == 0 && tabLayout2.mFirstTabGravity == 1) {
                    for (int i16 = 0; i16 < childCount2; i16++) {
                        View childAt2 = getChildAt(i16);
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) childAt2.getLayoutParams();
                        layoutParams.width = -2;
                        layoutParams.weight = 0.0f;
                        childAt2.measure(View.MeasureSpec.makeMeasureSpec(0, 0), i7);
                    }
                }
                int iMax = 0;
                for (int i17 = 0; i17 < childCount2; i17++) {
                    View childAt3 = getChildAt(i17);
                    if (childAt3.getVisibility() == 0) {
                        iMax = Math.max(iMax, childAt3.getMeasuredWidth());
                    }
                }
                if (iMax <= 0) {
                    return;
                }
                if (iMax * childCount2 <= getMeasuredWidth() - (((int) ViewUtils.dpToPx(getContext(), 16)) * 2)) {
                    boolean z10 = false;
                    while (i10 < childCount2) {
                        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) getChildAt(i10).getLayoutParams();
                        if (layoutParams2.width != iMax || layoutParams2.weight != 0.0f) {
                            layoutParams2.width = iMax;
                            layoutParams2.weight = 0.0f;
                            z10 = true;
                        }
                        i10++;
                    }
                    TabLayout tabLayout3 = TabLayout.this;
                    if (tabLayout3.tabGravity == 0 && tabLayout3.mFirstTabGravity == 1) {
                        TabLayout.this.tabGravity = 1;
                    }
                    z9 = z10;
                } else {
                    TabLayout tabLayout4 = TabLayout.this;
                    tabLayout4.tabGravity = 0;
                    tabLayout4.updateTabViews(false);
                }
                if (z9) {
                    super.onMeasure(i5, i7);
                }
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onRtlPropertiesChanged(int i5) {
            super.onRtlPropertiesChanged(i5);
        }

        public void setIndicatorPositionFromTabPosition(int i5, float f2) {
            TabLayout.this.indicatorPosition = Math.round(i5 + f2);
            ValueAnimator valueAnimator = this.indicatorAnimator;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.indicatorAnimator.cancel();
            }
            tweenIndicatorPosition(getChildAt(i5), getChildAt(i5 + 1), f2);
        }

        public void setSelectedIndicatorHeight(int i5) {
            Rect bounds = TabLayout.this.tabSelectedIndicator.getBounds();
            TabLayout.this.tabSelectedIndicator.setBounds(bounds.left, 0, bounds.right, i5);
            requestLayout();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TabGravity {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TabIndicatorAnimationMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TabIndicatorGravity {
    }

    public static class TabLayoutOnPageChangeListener implements g {
        private int previousScrollState;
        private int scrollState;
        private final WeakReference<TabLayout> tabLayoutRef;

        public TabLayoutOnPageChangeListener(TabLayout tabLayout) {
            this.tabLayoutRef = new WeakReference<>(tabLayout);
        }

        @Override // androidx.viewpager.widget.g
        public void onPageScrollStateChanged(int i5) {
            this.previousScrollState = this.scrollState;
            this.scrollState = i5;
            TabLayout tabLayout = this.tabLayoutRef.get();
            if (tabLayout != null) {
                tabLayout.updateViewPagerScrollState(this.scrollState);
            }
        }

        @Override // androidx.viewpager.widget.g
        public void onPageScrolled(int i5, float f2, int i7) {
            TabLayout tabLayout = this.tabLayoutRef.get();
            if (tabLayout != null) {
                int i9 = this.scrollState;
                tabLayout.setScrollPosition(i5, f2, i9 != 2 || this.previousScrollState == 1, (i9 == 2 && this.previousScrollState == 0) ? false : true, false);
            }
        }

        @Override // androidx.viewpager.widget.g
        public void onPageSelected(int i5) {
            TabLayout tabLayout = this.tabLayoutRef.get();
            if (tabLayout == null || tabLayout.getSelectedTabPosition() == i5 || i5 >= tabLayout.getTabCount()) {
                return;
            }
            int i7 = this.scrollState;
            tabLayout.selectTab(tabLayout.getTabAt(i5), i7 == 0 || (i7 == 2 && this.previousScrollState == 0));
        }

        public void reset() {
            this.scrollState = 0;
            this.previousScrollState = 0;
        }
    }

    public final class TabView extends LinearLayout {
        private View badgeAnchorView;
        private BadgeDrawable badgeDrawable;
        private Drawable baseBackgroundDrawable;
        private ImageView customIconView;
        private TextView customTextView;
        private View customView;
        private int defaultMaxLines;
        private ImageView iconView;
        private CharSequence mCustomRoleDescription;
        private TextView mDotBadgeView;
        private int mIconSize;
        private SeslAbsIndicatorView mIndicatorView;
        private boolean mIsCallPerformClick;
        private View mMainTabTouchBackground;
        private TextView mNBadgeView;
        private TextView mSubTextView;
        private ConstraintLayout mTabParentView;
        View.OnKeyListener mTabViewKeyListener;
        private Tab tab;
        private TextView textView;

        public TabView(Context context) {
            super(context);
            this.defaultMaxLines = 2;
            this.mCustomRoleDescription = null;
            this.mTabViewKeyListener = new View.OnKeyListener() { // from class: com.google.android.material.tabs.TabLayout.TabView.1
                @Override // android.view.View.OnKeyListener
                public boolean onKey(View view, int i5, KeyEvent keyEvent) {
                    return false;
                }
            };
            updateBackgroundDrawable(context);
            setGravity(17);
            setOrientation(!TabLayout.this.inlineLabel ? 1 : 0);
            setClickable(true);
            setOnKeyListener(this.mTabViewKeyListener);
            if (TabLayout.this.mDepthStyle == 1) {
                int i5 = TabLayout.this.tabPaddingTop;
                int i7 = TabLayout.this.tabPaddingBottom;
                WeakHashMap weakHashMap = W.f7199a;
                setPaddingRelative(0, i5, 0, i7);
            }
            this.mIconSize = getResources().getDimensionPixelOffset(R.dimen.sesl_tab_icon_size);
        }

        private void addOnLayoutChangeListener(final View view) {
            if (view == null) {
                return;
            }
            view.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.google.android.material.tabs.TabLayout.TabView.2
                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view2, int i5, int i7, int i9, int i10, int i11, int i12, int i13, int i14) {
                    if (view.getVisibility() == 0) {
                        TabView.this.tryUpdateBadgeDrawableBounds(view);
                    }
                }
            });
        }

        private float approximateLineWidth(Layout layout, int i5, float f2) {
            return (f2 / layout.getPaint().getTextSize()) * layout.getLineWidth(i5);
        }

        private void clipViewToPaddingForBadge(boolean z9) {
            setClipChildren(z9);
            setClipToPadding(z9);
            ViewGroup viewGroup = (ViewGroup) getParent();
            if (viewGroup != null) {
                viewGroup.setClipChildren(z9);
                viewGroup.setClipToPadding(z9);
            }
        }

        private FrameLayout createPreApi18BadgeAnchorRoot() {
            FrameLayout frameLayout = new FrameLayout(getContext());
            frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            return frameLayout;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public BadgeDrawable getBadge() {
            return this.badgeDrawable;
        }

        private FrameLayout getCustomParentForBadge(View view) {
            if ((view == this.iconView || view == this.textView) && BadgeUtils.USE_COMPAT_PARENT) {
                return (FrameLayout) view.getParent();
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public BadgeDrawable getOrCreateBadge() {
            if (this.badgeDrawable == null) {
                this.badgeDrawable = BadgeDrawable.create(getContext());
            }
            tryUpdateBadgeAnchor();
            BadgeDrawable badgeDrawable = this.badgeDrawable;
            if (badgeDrawable != null) {
                return badgeDrawable;
            }
            throw new IllegalStateException("Unable to create badge");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean hasBadgeDrawable() {
            return this.badgeDrawable != null;
        }

        private void inflateAndAddDefaultIconView() {
            ViewGroup viewGroup;
            if (BadgeUtils.USE_COMPAT_PARENT) {
                FrameLayout frameLayoutCreatePreApi18BadgeAnchorRoot = createPreApi18BadgeAnchorRoot();
                addView(frameLayoutCreatePreApi18BadgeAnchorRoot, 0);
                viewGroup = frameLayoutCreatePreApi18BadgeAnchorRoot;
            } else {
                viewGroup = this;
            }
            ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.sesl_layout_tab_icon, viewGroup, false);
            this.iconView = imageView;
            viewGroup.addView(imageView, 0);
        }

        private void inflateAndAddDefaultSubTextView() {
            ViewGroup viewGroup;
            if (BadgeUtils.USE_COMPAT_PARENT) {
                FrameLayout frameLayoutCreatePreApi18BadgeAnchorRoot = createPreApi18BadgeAnchorRoot();
                addView(frameLayoutCreatePreApi18BadgeAnchorRoot);
                viewGroup = frameLayoutCreatePreApi18BadgeAnchorRoot;
            } else {
                viewGroup = this;
            }
            TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.sesl_layout_tab_sub_text, viewGroup, false);
            this.textView = textView;
            viewGroup.addView(textView);
        }

        private void inflateAndAddDefaultTextView() {
            ViewGroup viewGroup;
            if (BadgeUtils.USE_COMPAT_PARENT) {
                FrameLayout frameLayoutCreatePreApi18BadgeAnchorRoot = createPreApi18BadgeAnchorRoot();
                addView(frameLayoutCreatePreApi18BadgeAnchorRoot);
                viewGroup = frameLayoutCreatePreApi18BadgeAnchorRoot;
            } else {
                viewGroup = this;
            }
            TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.sesl_layout_tab_text, viewGroup, false);
            this.textView = textView;
            viewGroup.addView(textView);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeBadge() {
            if (this.badgeAnchorView != null) {
                tryRemoveBadgeFromAnchor();
            }
            this.badgeDrawable = null;
        }

        private void seslUpdateTextAndIcon(TextView textView, TextView textView2, ImageView imageView) {
            updateTextAndIcon(textView, imageView, true);
            if (textView2 != null) {
                Tab tab = this.tab;
                CharSequence charSequenceSeslGetSubText = tab != null ? tab.seslGetSubText() : null;
                e eVar = (e) textView.getLayoutParams();
                boolean z9 = !TextUtils.isEmpty(charSequenceSeslGetSubText);
                eVar.f6983i = z9 ? -1 : 0;
                eVar.f6988l = z9 ? -1 : 0;
                eVar.f6986k = z9 ? R.id.center_anchor : -1;
                textView2.setText(z9 ? charSequenceSeslGetSubText : null);
                if (!z9) {
                    textView2.setVisibility(8);
                    return;
                }
                if (this.tab.labelVisibilityMode == 1) {
                    textView2.setVisibility(0);
                } else {
                    textView2.setVisibility(8);
                }
                setVisibility(0);
            }
        }

        private void setBackgroundBounds() {
            Drawable drawable = this.baseBackgroundDrawable;
            if (drawable != null) {
                View view = this.mMainTabTouchBackground;
                if (view == null) {
                    drawable.setBounds(getLeft(), getTop(), getRight(), getBottom());
                    return;
                }
                drawable.setBounds(getPaddingStart() + view.getLeft(), getPaddingTop() + this.mMainTabTouchBackground.getTop(), getPaddingStart() + this.mMainTabTouchBackground.getRight(), getPaddingTop() + this.mMainTabTouchBackground.getBottom());
            }
        }

        private void showMainTabTouchBackground(int i5) {
            if (this.mMainTabTouchBackground != null && TabLayout.this.mDepthStyle == 1 && TabLayout.this.tabBackgroundResId == 0) {
                this.mMainTabTouchBackground.setAlpha(1.0f);
                AnimationSet animationSet = new AnimationSet(true);
                animationSet.setFillAfter(true);
                if (i5 == 0) {
                    AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
                    alphaAnimation.setDuration(100L);
                    alphaAnimation.setFillAfter(true);
                    animationSet.addAnimation(alphaAnimation);
                    ScaleAnimation scaleAnimation = new ScaleAnimation(TabLayout.ANIM_RIPPLE_MINOR_SCALE, 1.0f, TabLayout.ANIM_RIPPLE_MINOR_SCALE, 1.0f, 1, 0.5f, 1, 0.5f);
                    scaleAnimation.setDuration(350L);
                    scaleAnimation.setInterpolator(AbstractC0510a.f10751b);
                    scaleAnimation.setFillAfter(true);
                    animationSet.addAnimation(scaleAnimation);
                    this.mMainTabTouchBackground.startAnimation(animationSet);
                    return;
                }
                if ((i5 == 1 || i5 == 3) && this.mMainTabTouchBackground.getAnimation() != null) {
                    if (!this.mMainTabTouchBackground.getAnimation().hasEnded()) {
                        this.mMainTabTouchBackground.getAnimation().setAnimationListener(new Animation.AnimationListener() { // from class: com.google.android.material.tabs.TabLayout.TabView.3
                            @Override // android.view.animation.Animation.AnimationListener
                            public void onAnimationEnd(Animation animation) {
                                AnimationSet animationSet2 = new AnimationSet(true);
                                animationSet2.setFillAfter(true);
                                AlphaAnimation alphaAnimation2 = new AlphaAnimation(1.0f, 0.0f);
                                alphaAnimation2.setDuration(400L);
                                alphaAnimation2.setFillAfter(true);
                                animationSet2.addAnimation(alphaAnimation2);
                                TabView.this.mMainTabTouchBackground.startAnimation(alphaAnimation2);
                            }

                            @Override // android.view.animation.Animation.AnimationListener
                            public void onAnimationRepeat(Animation animation) {
                            }

                            @Override // android.view.animation.Animation.AnimationListener
                            public void onAnimationStart(Animation animation) {
                            }
                        });
                        return;
                    }
                    AlphaAnimation alphaAnimation2 = new AlphaAnimation(1.0f, 0.0f);
                    alphaAnimation2.setDuration(400L);
                    alphaAnimation2.setFillAfter(true);
                    animationSet.addAnimation(alphaAnimation2);
                    this.mMainTabTouchBackground.startAnimation(animationSet);
                }
            }
        }

        private boolean startTabTouchAnimation(MotionEvent motionEvent, KeyEvent keyEvent) {
            SeslAbsIndicatorView seslAbsIndicatorView;
            TextView textView;
            SeslAbsIndicatorView seslAbsIndicatorView2;
            if (motionEvent == null || this.tab.getCustomView() != null || this.textView == null || keyEvent != null) {
                return false;
            }
            int action = motionEvent.getAction() & 255;
            if (action == 0) {
                this.mIsCallPerformClick = false;
                if (this.tab.position != TabLayout.this.getSelectedTabPosition() && (textView = this.textView) != null) {
                    textView.setTypeface(TabLayout.this.mBoldTypeface);
                    TabLayout tabLayout = TabLayout.this;
                    tabLayout.startTextColorChangeAnimation(this.textView, tabLayout.getSelectedTabTextColor());
                    SeslAbsIndicatorView seslAbsIndicatorView3 = this.mIndicatorView;
                    if (seslAbsIndicatorView3 != null) {
                        seslAbsIndicatorView3.setPressed();
                    }
                    TabLayout tabLayout2 = TabLayout.this;
                    Tab tabAt = tabLayout2.getTabAt(tabLayout2.getSelectedTabPosition());
                    if (tabAt != null) {
                        TextView textView2 = tabAt.view.textView;
                        if (textView2 != null) {
                            textView2.setTypeface(TabLayout.this.mNormalTypeface);
                            TabLayout tabLayout3 = TabLayout.this;
                            tabLayout3.startTextColorChangeAnimation(tabAt.view.textView, tabLayout3.tabTextColors.getDefaultColor());
                        }
                        SeslAbsIndicatorView seslAbsIndicatorView4 = tabAt.view.mIndicatorView;
                        if (seslAbsIndicatorView4 != null) {
                            seslAbsIndicatorView4.setHide();
                        }
                    }
                } else if (this.tab.position == TabLayout.this.getSelectedTabPosition() && (seslAbsIndicatorView = this.mIndicatorView) != null) {
                    seslAbsIndicatorView.setPressed();
                }
            } else if (action == 1) {
                SeslAbsIndicatorView seslAbsIndicatorView5 = this.mIndicatorView;
                if (seslAbsIndicatorView5 != null) {
                    seslAbsIndicatorView5.setReleased();
                    this.mIndicatorView.onTouchEvent(motionEvent);
                }
                performClick();
                this.mIsCallPerformClick = true;
            } else if (action == 3) {
                this.textView.setTypeface(TabLayout.this.mNormalTypeface);
                TabLayout tabLayout4 = TabLayout.this;
                tabLayout4.startTextColorChangeAnimation(this.textView, tabLayout4.tabTextColors.getDefaultColor());
                SeslAbsIndicatorView seslAbsIndicatorView6 = this.mIndicatorView;
                if (seslAbsIndicatorView6 != null && !seslAbsIndicatorView6.isSelected()) {
                    this.mIndicatorView.setHide();
                }
                TabLayout tabLayout5 = TabLayout.this;
                Tab tabAt2 = tabLayout5.getTabAt(tabLayout5.getSelectedTabPosition());
                if (tabAt2 != null) {
                    TextView textView3 = tabAt2.view.textView;
                    if (textView3 != null) {
                        textView3.setTypeface(TabLayout.this.mBoldTypeface);
                        TabLayout tabLayout6 = TabLayout.this;
                        tabLayout6.startTextColorChangeAnimation(tabAt2.view.textView, tabLayout6.getSelectedTabTextColor());
                    }
                    SeslAbsIndicatorView seslAbsIndicatorView7 = tabAt2.view.mIndicatorView;
                    if (seslAbsIndicatorView7 != null) {
                        seslAbsIndicatorView7.setShow();
                    }
                }
                if (TabLayout.this.mDepthStyle != 1 && (seslAbsIndicatorView2 = this.mIndicatorView) != null && seslAbsIndicatorView2.isSelected()) {
                    this.mIndicatorView.setReleased();
                }
            }
            return super.onTouchEvent(motionEvent);
        }

        private void tryAttachBadgeToAnchor(View view) {
            if (hasBadgeDrawable() && view != null) {
                clipViewToPaddingForBadge(false);
                BadgeUtils.attachBadgeDrawable(this.badgeDrawable, view, getCustomParentForBadge(view));
                this.badgeAnchorView = view;
            }
        }

        private void tryRemoveBadgeFromAnchor() {
            if (hasBadgeDrawable()) {
                clipViewToPaddingForBadge(true);
                View view = this.badgeAnchorView;
                if (view != null) {
                    BadgeUtils.detachBadgeDrawable(this.badgeDrawable, view);
                    this.badgeAnchorView = null;
                }
            }
        }

        private void tryUpdateBadgeAnchor() {
            Tab tab;
            Tab tab2;
            if (hasBadgeDrawable()) {
                if (this.customView != null) {
                    tryRemoveBadgeFromAnchor();
                    return;
                }
                if (this.iconView != null && (tab2 = this.tab) != null && tab2.getIcon() != null) {
                    View view = this.badgeAnchorView;
                    ImageView imageView = this.iconView;
                    if (view == imageView) {
                        tryUpdateBadgeDrawableBounds(imageView);
                        return;
                    } else {
                        tryRemoveBadgeFromAnchor();
                        tryAttachBadgeToAnchor(this.iconView);
                        return;
                    }
                }
                if (this.textView == null || (tab = this.tab) == null || tab.getTabLabelVisibility() != 1) {
                    tryRemoveBadgeFromAnchor();
                    return;
                }
                View view2 = this.badgeAnchorView;
                TextView textView = this.textView;
                if (view2 == textView) {
                    tryUpdateBadgeDrawableBounds(textView);
                } else {
                    tryRemoveBadgeFromAnchor();
                    tryAttachBadgeToAnchor(this.textView);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void tryUpdateBadgeDrawableBounds(View view) {
            if (hasBadgeDrawable() && view == this.badgeAnchorView) {
                BadgeUtils.setBadgeDrawableBounds(this.badgeDrawable, view, getCustomParentForBadge(view));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateBackgroundDrawable(Context context) {
            TabLayout tabLayout = TabLayout.this;
            if (tabLayout.tabBackgroundResId == 0 || tabLayout.mDepthStyle == 2) {
                this.baseBackgroundDrawable = null;
                return;
            }
            Drawable drawableY = android.support.v4.media.session.f.y(context, TabLayout.this.tabBackgroundResId);
            this.baseBackgroundDrawable = drawableY;
            if (drawableY != null && drawableY.isStateful()) {
                this.baseBackgroundDrawable.setState(getDrawableState());
            }
            Drawable drawable = this.baseBackgroundDrawable;
            WeakHashMap weakHashMap = W.f7199a;
            setBackground(drawable);
        }

        private void updateTextAndIcon(TextView textView, ImageView imageView, boolean z9) {
            Tab tab = this.tab;
            Drawable drawableMutate = (tab == null || tab.getIcon() == null) ? null : this.tab.getIcon().mutate();
            if (drawableMutate != null) {
                TabLayout tabLayout = TabLayout.this;
                ColorStateList colorStateList = tabLayout.tabIconTint;
                if (colorStateList == null) {
                    E.a.h(drawableMutate, tabLayout.tabTextColors);
                } else {
                    E.a.h(drawableMutate, colorStateList);
                }
                PorterDuff.Mode mode = TabLayout.this.tabIconTintMode;
                if (mode != null) {
                    E.a.i(drawableMutate, mode);
                }
            }
            Tab tab2 = this.tab;
            CharSequence text = tab2 != null ? tab2.getText() : null;
            boolean z10 = false;
            if (imageView != null) {
                if (drawableMutate != null) {
                    imageView.setImageDrawable(drawableMutate);
                    imageView.setVisibility(0);
                    setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                    imageView.setImageDrawable(null);
                }
            }
            boolean z11 = !TextUtils.isEmpty(text);
            if (textView != null) {
                boolean z12 = z11 && this.tab.labelVisibilityMode == 1;
                if (!z11) {
                    text = null;
                }
                textView.setText(text);
                textView.setVisibility(z12 ? 0 : 8);
                if (z11) {
                    setVisibility(0);
                }
                z10 = z12;
            }
            if (z9 && imageView != null) {
                if (z10 && imageView.getVisibility() == 0) {
                    if (TabLayout.this.mIconTextGap != -1) {
                        int unused = TabLayout.this.mIconTextGap;
                    } else {
                        ViewUtils.dpToPx(getContext(), 8);
                    }
                }
            }
            Tab tab3 = this.tab;
            d2.a(this, z11 ? null : tab3 != null ? tab3.contentDesc : null);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void drawableStateChanged() {
            super.drawableStateChanged();
        }

        public int getContentHeight() {
            View[] viewArr = {this.textView, this.iconView, this.customView};
            int iMax = 0;
            int iMin = 0;
            boolean z9 = false;
            for (int i5 = 0; i5 < 3; i5++) {
                View view = viewArr[i5];
                if (view != null && view.getVisibility() == 0) {
                    iMin = z9 ? Math.min(iMin, view.getTop()) : view.getTop();
                    iMax = z9 ? Math.max(iMax, view.getBottom()) : view.getBottom();
                    z9 = true;
                }
            }
            return iMax - iMin;
        }

        public int getContentWidth() {
            View[] viewArr = {this.textView, this.iconView, this.customView};
            int iMax = 0;
            int iMin = 0;
            boolean z9 = false;
            for (int i5 = 0; i5 < 3; i5++) {
                View view = viewArr[i5];
                if (view != null && view.getVisibility() == 0) {
                    iMin = z9 ? Math.min(iMin, view.getLeft()) : view.getLeft();
                    iMax = z9 ? Math.max(iMax, view.getRight()) : view.getRight();
                    z9 = true;
                }
            }
            return iMax - iMin;
        }

        public Tab getTab() {
            return this.tab;
        }

        @Override // android.view.View
        public void onConfigurationChanged(Configuration configuration) {
            super.onConfigurationChanged(configuration);
            this.mIconSize = getResources().getDimensionPixelOffset(R.dimen.sesl_tab_icon_size);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onDraw(Canvas canvas) {
            setBackgroundBounds();
            super.onDraw(canvas);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            BadgeDrawable badgeDrawable = this.badgeDrawable;
            if (badgeDrawable != null && badgeDrawable.isVisible()) {
                accessibilityNodeInfo.setContentDescription(((Object) getContentDescription()) + ", " + ((Object) this.badgeDrawable.getContentDescription()));
            }
            accessibilityNodeInfo.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo) k.a(0, 1, this.tab.getPosition(), 1, false, isSelected()).f1792a);
            if (isSelected()) {
                accessibilityNodeInfo.setClickable(false);
                accessibilityNodeInfo.removeAction((AccessibilityNodeInfo.AccessibilityAction) L.f.f1779e.f1787a);
            }
            CharSequence string = this.mCustomRoleDescription;
            if (string == null) {
                string = getResources().getString(R.string.item_view_role_description);
            }
            accessibilityNodeInfo.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", string);
            TextView textView = this.mDotBadgeView;
            if (textView != null && textView.getVisibility() == 0 && this.mDotBadgeView.getContentDescription() != null) {
                accessibilityNodeInfo.setContentDescription(((Object) getContentDescription()) + ", " + ((Object) this.mDotBadgeView.getContentDescription()));
                return;
            }
            TextView textView2 = this.mNBadgeView;
            if (textView2 == null || textView2.getVisibility() != 0 || this.mNBadgeView.getContentDescription() == null) {
                return;
            }
            accessibilityNodeInfo.setContentDescription(((Object) getContentDescription()) + ", " + ((Object) this.mNBadgeView.getContentDescription()));
        }

        @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
            TextView textView;
            super.onLayout(z9, i5, i7, i9, i10);
            View view = this.mMainTabTouchBackground;
            if (view != null) {
                view.setLeft(0);
                View view2 = this.mMainTabTouchBackground;
                ConstraintLayout constraintLayout = this.mTabParentView;
                view2.setRight(constraintLayout != null ? constraintLayout.getWidth() : i9 - i5);
                if (this.mMainTabTouchBackground.getAnimation() != null && this.mMainTabTouchBackground.getAnimation().hasEnded()) {
                    this.mMainTabTouchBackground.setAlpha(0.0f);
                }
            }
            if (this.iconView == null || this.tab.icon == null || (textView = this.textView) == null || this.mIndicatorView == null || this.mTabParentView == null) {
                return;
            }
            int measuredWidth = textView.getMeasuredWidth() + this.mIconSize;
            if (TabLayout.this.mIconTextGap != -1) {
                measuredWidth += TabLayout.this.mIconTextGap;
            }
            int iAbs = Math.abs((getWidth() - measuredWidth) / 2);
            if (!ViewUtils.isLayoutRtl(this)) {
                if (this.iconView.getLeft() == this.mTabParentView.getLeft()) {
                    this.textView.offsetLeftAndRight(iAbs);
                    this.iconView.offsetLeftAndRight(iAbs);
                    this.mIndicatorView.offsetLeftAndRight(iAbs);
                    return;
                }
                return;
            }
            int i11 = -iAbs;
            if (this.iconView.getRight() == this.mTabParentView.getRight()) {
                this.textView.offsetLeftAndRight(i11);
                this.iconView.offsetLeftAndRight(i11);
                this.mIndicatorView.offsetLeftAndRight(i11);
            }
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int i5, int i7) {
            TextView textView;
            Layout layout;
            TextView textView2;
            int size = View.MeasureSpec.getSize(i5);
            int mode = View.MeasureSpec.getMode(i5);
            int tabMaxWidth = TabLayout.this.getTabMaxWidth();
            TabLayout tabLayout = TabLayout.this;
            int i9 = tabLayout.mode;
            if (i9 == 11 || i9 == 12) {
                if (mode == 0) {
                    i5 = View.MeasureSpec.makeMeasureSpec(tabLayout.tabMaxWidth, 0);
                } else if (mode == 1073741824) {
                    i5 = View.MeasureSpec.makeMeasureSpec(size, 1073741824);
                }
            } else if (tabLayout.mRequestedTabWidth != -1) {
                i5 = View.MeasureSpec.makeMeasureSpec(TabLayout.this.mRequestedTabWidth, 1073741824);
            } else if (tabMaxWidth > 0 && (mode == 0 || size > tabMaxWidth)) {
                i5 = View.MeasureSpec.makeMeasureSpec(TabLayout.this.tabMaxWidth, Integer.MIN_VALUE);
            }
            super.onMeasure(i5, i7);
            TextView textView3 = this.textView;
            if (textView3 != null && this.customView == null) {
                TabLayout tabLayout2 = TabLayout.this;
                float f2 = tabLayout2.tabTextSize;
                tabLayout2.checkMaxFontScale(textView3, (int) f2);
                if (TabLayout.this.mDepthStyle == 2 && (textView2 = this.mSubTextView) != null) {
                    TabLayout tabLayout3 = TabLayout.this;
                    tabLayout3.checkMaxFontScale(textView2, tabLayout3.mSubTabTextSize);
                }
                int i10 = this.defaultMaxLines;
                ImageView imageView = this.iconView;
                if (imageView == null || imageView.getVisibility() != 0) {
                    TextView textView4 = this.textView;
                    if (textView4 != null && textView4.getLineCount() > 1) {
                        f2 = TabLayout.this.tabTextMultiLineSize;
                    }
                } else {
                    f2 = TabLayout.this.mSubTabTextSize;
                    i10 = 1;
                }
                float textSize = this.textView.getTextSize();
                int lineCount = this.textView.getLineCount();
                int maxLines = this.textView.getMaxLines();
                if ((f2 != textSize || (maxLines >= 0 && i10 != maxLines)) && (TabLayout.this.mode != 1 || f2 <= textSize || lineCount != 1 || ((layout = this.textView.getLayout()) != null && approximateLineWidth(layout, 0, f2) <= (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight()))) {
                    this.textView.setTextSize(0, f2);
                    TabLayout.this.checkMaxFontScale(this.textView, (int) f2);
                    if (TabLayout.this.mDepthStyle == 2 && (textView = this.mSubTextView) != null) {
                        TabLayout tabLayout4 = TabLayout.this;
                        tabLayout4.checkMaxFontScale(textView, tabLayout4.mSubTabTextSize);
                    }
                    this.textView.setMaxLines(i10);
                    super.onMeasure(i5, i7);
                }
            }
            if (this.customTextView != null || this.mTabParentView == null || this.textView == null || this.tab == null) {
                return;
            }
            TabLayout tabLayout5 = TabLayout.this;
            if (tabLayout5.mode == 0 && tabLayout5.mDepthStyle == 2) {
                if (tabMaxWidth > 0) {
                    this.textView.measure(tabMaxWidth, 0);
                } else {
                    this.textView.measure(0, 0);
                }
                int measuredWidth = this.textView.getMeasuredWidth();
                ViewGroup.LayoutParams layoutParams = this.mTabParentView.getLayoutParams();
                layoutParams.width = (getContext().getResources().getDimensionPixelSize(R.dimen.sesl_tablayout_subtab_side_space) * 2) + measuredWidth;
                this.mTabParentView.setLayoutParams(layoutParams);
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(layoutParams.width, Integer.MIN_VALUE), i7);
            }
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            return (!isEnabled() || TabLayout.this.isScrollingEnabled()) ? super.onTouchEvent(motionEvent) : this.tab.getCustomView() != null ? super.onTouchEvent(motionEvent) : startTabTouchAnimation(motionEvent, null);
        }

        @Override // android.view.View
        public boolean performClick() {
            if (this.mIsCallPerformClick) {
                this.mIsCallPerformClick = false;
                return true;
            }
            boolean zPerformClick = super.performClick();
            if (this.tab == null) {
                return zPerformClick;
            }
            if (!zPerformClick) {
                playSoundEffect(0);
            }
            this.tab.select();
            return true;
        }

        public void reset() {
            setTab(null);
            setSelected(false);
        }

        public void seslSetRoleDescription(CharSequence charSequence) {
            this.mCustomRoleDescription = charSequence;
        }

        @Override // android.view.View
        public void setEnabled(boolean z9) {
            super.setEnabled(z9);
            View view = this.mMainTabTouchBackground;
            if (view != null) {
                view.setVisibility(z9 ? 0 : 8);
            }
        }

        @Override // android.view.View
        public void setSelected(boolean z9) {
            if (isEnabled()) {
                isSelected();
                super.setSelected(z9);
                TextView textView = this.textView;
                if (textView != null) {
                    textView.setSelected(z9);
                }
                ImageView imageView = this.iconView;
                if (imageView != null) {
                    imageView.setSelected(z9);
                }
                View view = this.customView;
                if (view != null) {
                    view.setSelected(z9);
                }
                SeslAbsIndicatorView seslAbsIndicatorView = this.mIndicatorView;
                if (seslAbsIndicatorView != null) {
                    seslAbsIndicatorView.setSelected(z9);
                    if (!TextUtils.isEmpty(this.tab != null ? r0.seslGetSubText() : null)) {
                        SeslAbsIndicatorView seslAbsIndicatorView2 = this.mIndicatorView;
                        Drawable drawableB = B.a.b(getContext(), c.O(getContext()) ? R.drawable.sesl_tablayout_subtab_subtext_indicator_background_light : R.drawable.sesl_tablayout_subtab_subtext_indicator_background_dark);
                        WeakHashMap weakHashMap = W.f7199a;
                        seslAbsIndicatorView2.setBackground(drawableB);
                    }
                }
                TextView textView2 = this.mSubTextView;
                if (textView2 != null) {
                    textView2.setSelected(z9);
                }
            }
        }

        public void setShowButtonShape(int i5, ColorStateList colorStateList) {
            Drawable drawable = getResources().getDrawable(R.drawable.sesl_bottom_nav_show_button_shapes_background);
            TextView textView = this.textView;
            if (textView != null) {
                textView.setTextColor(i5);
                this.textView.setBackground(drawable);
                this.textView.setBackgroundTintList(colorStateList);
            }
            TextView textView2 = this.mSubTextView;
            if (textView2 != null) {
                textView2.setTextColor(i5);
                this.mSubTextView.setBackground(drawable);
                this.mSubTextView.setBackgroundTintList(colorStateList);
            }
        }

        public void setTab(Tab tab) {
            if (tab != this.tab) {
                this.tab = tab;
                update();
            }
        }

        public final void update() {
            updateTab();
            Tab tab = this.tab;
            setSelected(tab != null && tab.isSelected());
        }

        public final void updateOrientation() {
            setOrientation(!TabLayout.this.inlineLabel ? 1 : 0);
            TextView textView = this.customTextView;
            if (textView == null && this.customIconView == null) {
                updateTextAndIcon(this.textView, this.iconView, true);
            } else {
                updateTextAndIcon(textView, this.customIconView, false);
            }
        }

        public final void updateTab() {
            int i5;
            ConstraintLayout constraintLayout;
            ViewParent parent;
            Tab tab = this.tab;
            View customView = tab != null ? tab.getCustomView() : null;
            if (customView != null) {
                ViewParent parent2 = customView.getParent();
                if (parent2 != this) {
                    if (parent2 != null) {
                        ((ViewGroup) parent2).removeView(customView);
                    }
                    View view = this.customView;
                    if (view != null && (parent = view.getParent()) != null) {
                        ((ViewGroup) parent).removeView(this.customView);
                    }
                    addView(customView);
                }
                this.customView = customView;
                TextView textView = this.textView;
                if (textView != null) {
                    textView.setVisibility(8);
                }
                ImageView imageView = this.iconView;
                if (imageView != null) {
                    imageView.setVisibility(8);
                    this.iconView.setImageDrawable(null);
                }
                TextView textView2 = this.mSubTextView;
                if (textView2 != null) {
                    textView2.setVisibility(8);
                }
                TextView textView3 = (TextView) customView.findViewById(android.R.id.text1);
                this.customTextView = textView3;
                if (textView3 != null) {
                    this.defaultMaxLines = textView3.getMaxLines();
                }
                this.customIconView = (ImageView) customView.findViewById(android.R.id.icon);
            } else {
                View view2 = this.customView;
                if (view2 != null) {
                    removeView(view2);
                    this.customView = null;
                }
                this.customTextView = null;
                this.customIconView = null;
            }
            boolean z9 = false;
            if (this.customView != null || this.tab == null) {
                TextView textView4 = this.customTextView;
                if (textView4 != null || this.customIconView != null) {
                    updateTextAndIcon(textView4, this.customIconView, false);
                }
            } else {
                if (this.mTabParentView == null) {
                    if (TabLayout.this.mDepthStyle == 2) {
                        this.mTabParentView = (ConstraintLayout) LayoutInflater.from(getContext()).inflate(R.layout.sesl_tabs_sub_tab_layout, (ViewGroup) this, false);
                    } else {
                        ConstraintLayout constraintLayout2 = (ConstraintLayout) LayoutInflater.from(getContext()).inflate(R.layout.sesl_tabs_main_tab_layout, (ViewGroup) this, false);
                        this.mTabParentView = constraintLayout2;
                        View viewFindViewById = constraintLayout2.findViewById(R.id.main_tab_touch_background);
                        this.mMainTabTouchBackground = viewFindViewById;
                        if (viewFindViewById != null && this.tab.icon == null) {
                            View view3 = this.mMainTabTouchBackground;
                            Drawable drawableB = B.a.b(getContext(), c.O(getContext()) ? R.drawable.sesl_tablayout_maintab_touch_background_light : R.drawable.sesl_tablayout_maintab_touch_background_dark);
                            WeakHashMap weakHashMap = W.f7199a;
                            view3.setBackground(drawableB);
                            this.mMainTabTouchBackground.setAlpha(0.0f);
                        }
                    }
                }
                if (this.mIndicatorView == null) {
                    this.mIndicatorView = (SeslAbsIndicatorView) this.mTabParentView.findViewById(R.id.indicator);
                }
                if (TabLayout.this.mDepthStyle != 2) {
                    SeslAbsIndicatorView seslAbsIndicatorView = this.mIndicatorView;
                    if (seslAbsIndicatorView != null) {
                        seslAbsIndicatorView.setSelectedIndicatorColor(TabLayout.this.mTabSelectedIndicatorColor);
                    }
                } else if (this.mIndicatorView != null && TabLayout.this.mSubTabSelectedIndicatorColor != -1) {
                    this.mIndicatorView.setSelectedIndicatorColor(TabLayout.this.mSubTabSelectedIndicatorColor);
                }
                if (this.textView == null) {
                    this.textView = (TextView) this.mTabParentView.findViewById(R.id.title);
                }
                this.defaultMaxLines = this.textView.getMaxLines();
                this.textView.setTextAppearance(TabLayout.this.defaultTabTextAppearance);
                if (!isSelected() || TabLayout.this.selectedTabTextAppearance == -1) {
                    this.textView.setTextAppearance(TabLayout.this.tabTextAppearance);
                } else {
                    this.textView.setTextAppearance(TabLayout.this.selectedTabTextAppearance);
                }
                if (isSelected()) {
                    this.textView.setTypeface(TabLayout.this.mBoldTypeface);
                } else {
                    this.textView.setTypeface(TabLayout.this.mNormalTypeface);
                }
                TabLayout tabLayout = TabLayout.this;
                tabLayout.checkMaxFontScale(this.textView, (int) tabLayout.tabTextSize);
                this.textView.setTextColor(TabLayout.this.tabTextColors);
                if (TabLayout.this.mDepthStyle == 2) {
                    if (this.mSubTextView == null) {
                        this.mSubTextView = (TextView) this.mTabParentView.findViewById(R.id.sub_title);
                    }
                    TextView textView5 = this.mSubTextView;
                    if (textView5 != null) {
                        textView5.setTextAppearance(TabLayout.this.mSubTabSubTextAppearance);
                        this.mSubTextView.setTextColor(TabLayout.this.mSubTabSubTextColors);
                    }
                    TextView textView6 = this.mSubTextView;
                    if (textView6 != null) {
                        TabLayout tabLayout2 = TabLayout.this;
                        tabLayout2.checkMaxFontScale(textView6, tabLayout2.mSubTabTextSize);
                    }
                }
                if (this.iconView == null && (constraintLayout = this.mTabParentView) != null) {
                    this.iconView = (ImageView) constraintLayout.findViewById(R.id.icon);
                }
                seslUpdateTextAndIcon(this.textView, this.mSubTextView, this.iconView);
                if (TabLayout.this.mDepthStyle == 2) {
                    i = TabLayout.this.mode == 0 ? -2 : -1;
                    i5 = TextUtils.isEmpty(tab != null ? tab.seslGetSubText() : null) ^ true ? TabLayout.this.mSubTabIndicator2ndHeight : TabLayout.this.mSubTabIndicatorHeight;
                    ConstraintLayout constraintLayout3 = this.mTabParentView;
                    if (constraintLayout3 != null && constraintLayout3.getHeight() != i5) {
                        z9 = true;
                    }
                } else if (this.tab.icon != null) {
                    i5 = -1;
                    i = -2;
                } else {
                    i5 = -1;
                }
                ConstraintLayout constraintLayout4 = this.mTabParentView;
                if (constraintLayout4 != null && constraintLayout4.getParent() == null) {
                    addView(this.mTabParentView, i, i5);
                } else if (z9) {
                    removeView(this.mTabParentView);
                    addView(this.mTabParentView, i, i5);
                }
                tryUpdateBadgeAnchor();
                addOnLayoutChangeListener(this.iconView);
                addOnLayoutChangeListener(this.textView);
            }
            if (tab == null || TextUtils.isEmpty(tab.contentDesc)) {
                return;
            }
            setContentDescription(tab.contentDesc);
        }
    }

    public static class ViewPagerOnTabSelectedListener implements OnTabSelectedListener {
        private final ViewPager viewPager;

        public ViewPagerOnTabSelectedListener(ViewPager viewPager) {
            this.viewPager = viewPager;
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(Tab tab) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(Tab tab) {
            this.viewPager.setCurrentItem(tab.getPosition());
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(Tab tab) {
        }
    }

    public TabLayout(Context context) {
        this(context, null);
    }

    private void addTabFromItemView(TabItem tabItem) {
        Tab tabNewTab = newTab();
        CharSequence charSequence = tabItem.text;
        if (charSequence != null) {
            tabNewTab.setText(charSequence);
        }
        Drawable drawable = tabItem.icon;
        if (drawable != null) {
            tabNewTab.setIcon(drawable);
        }
        int i5 = tabItem.customLayout;
        if (i5 != 0) {
            tabNewTab.setCustomView(i5);
        }
        if (!TextUtils.isEmpty(tabItem.getContentDescription())) {
            tabNewTab.setContentDescription(tabItem.getContentDescription());
        }
        CharSequence charSequence2 = tabItem.mSubText;
        if (charSequence2 != null) {
            tabNewTab.seslSetSubText(charSequence2);
        }
        addTab(tabNewTab);
    }

    private void addTabView(Tab tab) {
        TabView tabView = tab.view;
        tabView.setSelected(false);
        tabView.setActivated(false);
        this.slidingTabIndicator.addView(tabView, tab.getPosition(), createLayoutParamsForTabs());
        tabView.post(new o(7, this, tabView));
    }

    private void addViewInternal(View view) {
        if (!(view instanceof TabItem)) {
            throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
        }
        addTabFromItemView((TabItem) view);
    }

    private void animateToTab(int i5) {
        if (i5 == -1) {
            return;
        }
        if (getWindowToken() != null) {
            WeakHashMap weakHashMap = W.f7199a;
            if (isLaidOut() && !this.slidingTabIndicator.childrenNeedLayout()) {
                int scrollX = getScrollX();
                int iCalculateScrollXForTab = calculateScrollXForTab(i5, 0.0f);
                if (scrollX != iCalculateScrollXForTab) {
                    ensureScrollAnimator();
                    this.scrollAnimator.setIntValues(scrollX, iCalculateScrollXForTab);
                    this.scrollAnimator.start();
                }
                this.slidingTabIndicator.animateIndicatorToPosition(i5, this.tabIndicatorAnimationDuration);
                return;
            }
        }
        setScrollPosition(i5, 0.0f, true);
    }

    private void applyGravityForModeScrollable(int i5) {
        if (i5 == 0) {
            Log.w(LOG_TAG, "MODE_SCROLLABLE + GRAVITY_FILL is not supported, GRAVITY_START will be used instead");
        } else if (i5 == 1) {
            this.slidingTabIndicator.setGravity(1);
            return;
        } else if (i5 != 2) {
            return;
        }
        this.slidingTabIndicator.setGravity(8388611);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void applyModeAndGravity() {
        /*
            r3 = this;
            com.google.android.material.tabs.TabLayout$SlidingTabIndicator r0 = r3.slidingTabIndicator
            java.util.WeakHashMap r1 = androidx.core.view.W.f7199a
            r1 = 0
            r0.setPaddingRelative(r1, r1, r1, r1)
            int r0 = r3.mode
            r1 = 1
            if (r0 == 0) goto L2c
            r2 = 2
            if (r0 == r1) goto L1b
            if (r0 == r2) goto L1b
            r2 = 11
            if (r0 == r2) goto L2c
            r2 = 12
            if (r0 == r2) goto L2c
            goto L31
        L1b:
            int r0 = r3.tabGravity
            if (r0 != r2) goto L26
            java.lang.String r0 = "TabLayout"
            java.lang.String r2 = "GRAVITY_START is not supported with the current tab mode, GRAVITY_CENTER will be used instead"
            android.util.Log.w(r0, r2)
        L26:
            com.google.android.material.tabs.TabLayout$SlidingTabIndicator r0 = r3.slidingTabIndicator
            r0.setGravity(r1)
            goto L31
        L2c:
            int r0 = r3.tabGravity
            r3.applyGravityForModeScrollable(r0)
        L31:
            r3.updateTabViews(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.applyModeAndGravity():void");
    }

    private int calculateScrollXForTab(int i5, float f2) {
        View childAt;
        int i7 = this.mode;
        if ((i7 != 0 && i7 != 2 && i7 != 11 && i7 != 12) || (childAt = this.slidingTabIndicator.getChildAt(i5)) == null) {
            return 0;
        }
        int i9 = i5 + 1;
        View childAt2 = i9 < this.slidingTabIndicator.getChildCount() ? this.slidingTabIndicator.getChildAt(i9) : null;
        int width = childAt.getWidth();
        int width2 = childAt2 != null ? childAt2.getWidth() : 0;
        int left = ((width / 2) + childAt.getLeft()) - (getWidth() / 2);
        int i10 = (int) ((width + width2) * 0.5f * f2);
        WeakHashMap weakHashMap = W.f7199a;
        return getLayoutDirection() == 0 ? left + i10 : left - i10;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkMaxFontScale(TextView textView, int i5) {
        float f2 = getResources().getConfiguration().fontScale;
        if (textView == null || !this.mIsScaledTextSizeType || f2 <= 1.3f) {
            return;
        }
        textView.setTextSize(0, (i5 / f2) * 1.3f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkOverScreen() {
        int measuredWidth = getMeasuredWidth();
        if (measuredWidth <= ((int) ((getContext().getResources().getDisplayMetrics().densityDpi / 160.0f) * getResources().getInteger(R.integer.sesl_tablayout_over_screen_width_dp)))) {
            this.mIsOverScreen = false;
        } else {
            this.mIsOverScreen = true;
            this.mOverScreenMaxWidth = (int) (getResources().getFloat(R.dimen.sesl_tablayout_over_screen_max_width_rate) * measuredWidth);
        }
    }

    private void configureTab(Tab tab, int i5) {
        tab.setPosition(i5);
        this.tabs.add(i5, tab);
        int size = this.tabs.size();
        int i7 = -1;
        for (int i9 = i5 + 1; i9 < size; i9++) {
            if (this.tabs.get(i9).getPosition() == this.indicatorPosition) {
                i7 = i9;
            }
            this.tabs.get(i9).setPosition(i9);
        }
        this.indicatorPosition = i7;
    }

    private void createAddBadge(int i5, TabView tabView) {
        int i7;
        int paddingRight;
        int i9;
        if (tabView == null || tabView.mTabParentView == null) {
            return;
        }
        TextView textView = new TextView(getContext());
        Resources resources = getResources();
        if (i5 != 2) {
            if (tabView.mNBadgeView != null) {
                return;
            }
            textView.setVisibility(8);
            textView.setMinWidth(resources.getDimensionPixelSize(R.dimen.sesl_tab_badge_number_min_width));
            int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.sesl_tab_badge_number_text_size);
            textView.setTextSize(0, dimensionPixelSize);
            seslCheckMaxFontScale(textView, dimensionPixelSize);
            textView.setGravity(17);
            textView.setTextColor(resources.getColor(R.color.sesl_badge_text_color));
            Drawable drawable = resources.getDrawable(R.drawable.sesl_tab_n_badge);
            WeakHashMap weakHashMap = W.f7199a;
            textView.setBackground(drawable);
            textView.setId(R.id.sesl_badge_n);
            textView.setMaxLines(1);
            e eVar = new e(-2, resources.getDimensionPixelSize(R.dimen.sesl_tab_badge_number_height));
            if (tabView.iconView == null || tabView.iconView.getVisibility() != 0) {
                int i10 = R.id.title;
                eVar.f6983i = i10;
                eVar.f6999s = i10;
            } else {
                int i11 = R.id.icon;
                eVar.f6983i = i11;
                eVar.f6999s = i11;
            }
            eVar.setMargins(getContext().getResources().getDimensionPixelSize(R.dimen.sesl_tablayout_subtab_n_badge_xoffset), resources.getDimensionPixelSize(R.dimen.sesl_tab_badge_offset_y), 0, 0);
            tabView.mTabParentView.addView(textView, eVar);
            tabView.mNBadgeView = textView;
            return;
        }
        if (tabView.mDotBadgeView != null) {
            return;
        }
        textView.setVisibility(8);
        Drawable drawable2 = resources.getDrawable(R.drawable.sesl_dot_badge);
        WeakHashMap weakHashMap2 = W.f7199a;
        textView.setBackground(drawable2);
        textView.setId(R.id.sesl_badge_dot);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.sesl_tab_badge_dot_size);
        int dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen.sesl_tablayout_subtab_dot_badge_offset_x);
        e eVar2 = new e(dimensionPixelSize2, dimensionPixelSize2);
        if (tabView.iconView == null || tabView.iconView.getVisibility() != 0) {
            int i12 = R.id.title;
            eVar2.f6983i = i12;
            eVar2.f6999s = i12;
            if (tabView.textView != null) {
                paddingRight = tabView.textView.getPaddingRight();
                i7 = dimensionPixelSize3;
                i9 = 0;
            } else {
                i7 = dimensionPixelSize3;
                paddingRight = 0;
                i9 = 0;
            }
        } else {
            int i13 = R.id.icon;
            eVar2.f6983i = i13;
            eVar2.f6999s = i13;
            int i14 = R.dimen.sesl_tablayout_subtab_dot_badge_with_icon_offset;
            int dimensionPixelSize4 = resources.getDimensionPixelSize(i14);
            int dimensionPixelSize5 = resources.getDimensionPixelSize(i14);
            i7 = dimensionPixelSize4;
            i9 = dimensionPixelSize5;
            paddingRight = 0;
        }
        eVar2.setMargins(i7 - paddingRight, i9, 0, 0);
        tabView.mTabParentView.addView(textView, eVar2);
        tabView.mDotBadgeView = textView;
    }

    private static ColorStateList createColorStateList(int i5, int i7) {
        return new ColorStateList(new int[][]{HorizontalScrollView.SELECTED_STATE_SET, HorizontalScrollView.EMPTY_STATE_SET}, new int[]{i7, i5});
    }

    private LinearLayout.LayoutParams createLayoutParamsForTabs() {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
        updateTabViewLayoutParams(layoutParams);
        return layoutParams;
    }

    private TabView createTabView(Tab tab) {
        d dVar = this.tabViewPool;
        TabView tabView = dVar != null ? (TabView) dVar.h() : null;
        if (tabView == null) {
            tabView = new TabView(getContext());
        }
        if (tabView.mMainTabTouchBackground != null) {
            tabView.mMainTabTouchBackground.setAlpha(0.0f);
        }
        if (tabView.mTabParentView != null) {
            tabView.mTabParentView.removeView(tabView.mDotBadgeView);
            tabView.mTabParentView.removeView(tabView.mNBadgeView);
            tabView.mDotBadgeView = null;
            tabView.mNBadgeView = null;
        }
        tabView.setTab(tab);
        tabView.setFocusable(true);
        tabView.setMinimumWidth(getTabMinWidth());
        if (TextUtils.isEmpty(tab.contentDesc)) {
            tabView.setContentDescription(tab.text);
        } else {
            tabView.setContentDescription(tab.contentDesc);
        }
        return tabView;
    }

    private void dispatchTabReselected(Tab tab) {
        for (int size = this.selectedListeners.size() - 1; size >= 0; size--) {
            this.selectedListeners.get(size).onTabReselected(tab);
        }
    }

    private void dispatchTabSelected(Tab tab) {
        for (int size = this.selectedListeners.size() - 1; size >= 0; size--) {
            this.selectedListeners.get(size).onTabSelected(tab);
        }
    }

    private void dispatchTabUnselected(Tab tab) {
        for (int size = this.selectedListeners.size() - 1; size >= 0; size--) {
            this.selectedListeners.get(size).onTabUnselected(tab);
        }
    }

    private void ensureScrollAnimator() {
        if (this.scrollAnimator == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.scrollAnimator = valueAnimator;
            valueAnimator.setInterpolator(this.tabIndicatorTimeInterpolator);
            this.scrollAnimator.setDuration(this.tabIndicatorAnimationDuration);
            this.scrollAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.tabs.TabLayout.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                    TabLayout.this.scrollTo(((Integer) valueAnimator2.getAnimatedValue()).intValue(), 0);
                }
            });
        }
    }

    private int getDefaultHeight() {
        return this.mDepthStyle == 2 ? 56 : 60;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getSelectedTabTextColor() {
        ColorStateList colorStateList = this.tabTextColors;
        if (colorStateList != null) {
            return colorStateList.getColorForState(new int[]{android.R.attr.state_selected, android.R.attr.state_enabled}, colorStateList.getDefaultColor());
        }
        return -1;
    }

    private int getTabMinWidth() {
        int i5 = this.requestedTabMinWidth;
        if (i5 != -1) {
            return i5;
        }
        return 0;
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.slidingTabIndicator.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isScrollingEnabled() {
        return getTabMode() == 0 || getTabMode() == 2;
    }

    private boolean isShowButtonShapesEnabled() {
        return this.mDepthStyle == 1 && Settings.System.getInt(this.mContentResolver, "show_button_background", 0) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$addTabView$0(TabView tabView) {
        tabView.setStateListAnimator(AnimatorInflater.loadStateListAnimator(getContext(), com.samsung.android.keyscafe.R.animator.sesl_recoil_button_selector));
        tabView.getStateListAnimator().jumpToCurrentState();
    }

    private void removeTabViewAt(int i5) {
        TabView tabView = (TabView) this.slidingTabIndicator.getChildAt(i5);
        this.slidingTabIndicator.removeViewAt(i5);
        if (tabView != null) {
            tabView.reset();
            this.tabViewPool.c(tabView);
        }
        requestLayout();
    }

    private void seslCheckMaxFontScale(TextView textView, int i5) {
        float f2 = getResources().getConfiguration().fontScale;
        if (f2 > 1.2f) {
            textView.setTextSize(0, (i5 / f2) * 1.2f);
        }
    }

    private int seslGetSelectedTabSubTextColor() {
        ColorStateList colorStateList = this.mSubTabSubTextColors;
        if (colorStateList != null) {
            return colorStateList.getColorForState(new int[]{android.R.attr.state_selected, android.R.attr.state_enabled}, colorStateList.getDefaultColor());
        }
        return -1;
    }

    private void setSelectedTabView(int i5, boolean z9) {
        SeslAbsIndicatorView seslAbsIndicatorView;
        int childCount = this.slidingTabIndicator.getChildCount();
        if (i5 < childCount) {
            int i7 = 0;
            while (i7 < childCount) {
                View childAt = this.slidingTabIndicator.getChildAt(i7);
                if ((i7 != i5 || childAt.isSelected()) && (i7 == i5 || !childAt.isSelected())) {
                    childAt.setSelected(i7 == i5);
                    childAt.setActivated(i7 == i5);
                } else {
                    childAt.setSelected(i7 == i5);
                    childAt.setActivated(i7 == i5);
                    if (childAt instanceof TabView) {
                        ((TabView) childAt).updateTab();
                    }
                }
                i7++;
            }
            for (int i9 = 0; i9 < getTabCount(); i9++) {
                Tab tab = this.tabs.get(i9);
                if (tab != null && (seslAbsIndicatorView = tab.view.mIndicatorView) != null) {
                    if (i9 != i5) {
                        seslAbsIndicatorView.setHide();
                    } else if (!z9) {
                        seslAbsIndicatorView.setReleased();
                    } else if (seslAbsIndicatorView.getAlpha() != 1.0f) {
                        seslAbsIndicatorView.setShow();
                    }
                }
            }
        }
    }

    private void setShowButtonShape(TabView tabView) {
        int color;
        ColorStateList tabTextColors = getTabTextColors();
        if (isShowButtonShapesEnabled()) {
            ColorDrawable colorDrawable = this.mBackgroundColorDrawable;
            if (colorDrawable != null) {
                color = colorDrawable.getColor();
            } else {
                color = getResources().getColor(c.O(getContext()) ? R.color.sesl_bottom_navigation_background_light : R.color.sesl_bottom_navigation_background_dark, null);
            }
            tabView.setShowButtonShape(color, tabTextColors);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startTextColorChangeAnimation(TextView textView, int i5) {
        if (textView != null) {
            textView.setTextColor(i5);
        }
    }

    private void updateAllTabs() {
        int size = this.tabs.size();
        for (int i5 = 0; i5 < size; i5++) {
            this.tabs.get(i5).updateView();
        }
    }

    private void updateBadgePosition(TabView tabView) {
        int dimensionPixelSize;
        TextView textView;
        byte b3;
        int dimensionPixelSize2;
        View view = tabView.textView;
        ImageView imageView = tabView.iconView;
        if (tabView.getWidth() > 0) {
            if (tabView.mNBadgeView != null && tabView.mNBadgeView.getVisibility() == 0) {
                textView = tabView.mNBadgeView;
                dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.sesl_tablayout_subtab_n_badge_xoffset);
                b3 = 1;
            } else if (tabView.mDotBadgeView == null || tabView.mDotBadgeView.getVisibility() != 0) {
                dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.sesl_tablayout_subtab_n_badge_xoffset);
                textView = null;
                b3 = -1;
            } else {
                textView = tabView.mDotBadgeView;
                dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.sesl_tablayout_subtab_dot_badge_offset_x);
                b3 = 2;
            }
            if (textView == null || textView.getVisibility() != 0) {
                return;
            }
            int paddingRight = 0;
            textView.measure(0, 0);
            int measuredWidth = b3 == 1 ? textView.getMeasuredWidth() : getResources().getDimensionPixelSize(R.dimen.sesl_tab_badge_dot_size);
            if (imageView != null && imageView.getVisibility() == 0) {
                Resources resources = getResources();
                int i5 = R.dimen.sesl_tablayout_subtab_dot_badge_with_icon_offset;
                int dimensionPixelSize3 = resources.getDimensionPixelSize(i5);
                dimensionPixelSize2 = getResources().getDimensionPixelSize(i5);
                dimensionPixelSize = dimensionPixelSize3;
                view = imageView;
            } else if (view != null) {
                paddingRight = view.getPaddingRight();
                dimensionPixelSize2 = 0;
            } else {
                dimensionPixelSize2 = 0;
            }
            if (view == null) {
                return;
            }
            int width = tabView.getWidth();
            int i7 = dimensionPixelSize - paddingRight;
            if (view.getRight() + dimensionPixelSize + measuredWidth > width) {
                i7 = -((view.getRight() + measuredWidth) - width);
            }
            e eVar = (e) textView.getLayoutParams();
            int i9 = ((ViewGroup.MarginLayoutParams) eVar).width;
            if (eVar.getMarginStart() == i7 && i9 == measuredWidth && ((ViewGroup.MarginLayoutParams) eVar).topMargin == dimensionPixelSize2) {
                return;
            }
            eVar.setMargins(i7, dimensionPixelSize2, eVar.getMarginEnd(), ((ViewGroup.MarginLayoutParams) eVar).bottomMargin);
            ((ViewGroup.MarginLayoutParams) eVar).width = measuredWidth;
            textView.setLayoutParams(eVar);
        }
    }

    private void updateTabViewLayoutParams(LinearLayout.LayoutParams layoutParams) {
        int i5 = this.mode;
        if (i5 == 1 && this.tabGravity == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
        } else if (i5 == 11 || i5 == 12) {
            layoutParams.width = -2;
            layoutParams.weight = 0.0f;
        } else {
            layoutParams.width = -2;
            layoutParams.weight = 0.0f;
        }
    }

    public void addOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        addOnTabSelectedListener((BaseOnTabSelectedListener) onTabSelectedListener);
    }

    public void addTab(Tab tab) {
        addTab(tab, this.tabs.isEmpty());
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view) {
        addViewInternal(view);
    }

    public void clearOnTabSelectedListeners() {
        this.selectedListeners.clear();
    }

    public Tab createTabFromPool() {
        Tab tab = (Tab) tabPool.h();
        return tab == null ? new Tab() : tab;
    }

    public int getSelectedTabPosition() {
        Tab tab = this.selectedTab;
        if (tab != null) {
            return tab.getPosition();
        }
        return -1;
    }

    public Tab getTabAt(int i5) {
        if (i5 < 0 || i5 >= getTabCount()) {
            return null;
        }
        return this.tabs.get(i5);
    }

    public int getTabCount() {
        return this.tabs.size();
    }

    public int getTabGravity() {
        return this.tabGravity;
    }

    public ColorStateList getTabIconTint() {
        return this.tabIconTint;
    }

    public int getTabIndicatorAnimationMode() {
        return this.tabIndicatorAnimationMode;
    }

    public int getTabIndicatorGravity() {
        return this.tabIndicatorGravity;
    }

    public int getTabMaxWidth() {
        return this.tabMaxWidth;
    }

    public int getTabMode() {
        return this.mode;
    }

    public ColorStateList getTabRippleColor() {
        return this.tabRippleColorStateList;
    }

    public Drawable getTabSelectedIndicator() {
        return this.tabSelectedIndicator;
    }

    public ColorStateList getTabTextColors() {
        return this.tabTextColors;
    }

    public boolean hasUnboundedRipple() {
        return this.unboundedRipple;
    }

    public boolean isInlineLabel() {
        return this.inlineLabel;
    }

    public boolean isTabIndicatorFullWidth() {
        return this.tabIndicatorFullWidth;
    }

    public Tab newTab() {
        Tab tabCreateTabFromPool = createTabFromPool();
        tabCreateTabFromPool.parent = this;
        tabCreateTabFromPool.view = createTabView(tabCreateTabFromPool);
        if (tabCreateTabFromPool.id != -1) {
            tabCreateTabFromPool.view.setId(tabCreateTabFromPool.id);
        }
        return tabCreateTabFromPool;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        TabView tabView;
        super.onAttachedToWindow();
        for (int i5 = 0; i5 < getTabCount(); i5++) {
            Tab tabAt = getTabAt(i5);
            if (tabAt != null && (tabView = tabAt.view) != null) {
                if (tabView.mMainTabTouchBackground != null) {
                    tabAt.view.mMainTabTouchBackground.setAlpha(0.0f);
                }
                if (tabAt.view.mIndicatorView != null) {
                    if (getSelectedTabPosition() == i5) {
                        tabAt.view.mIndicatorView.setShow();
                    } else {
                        tabAt.view.mIndicatorView.setHide();
                    }
                }
            }
        }
        MaterialShapeUtils.setParentAbsoluteElevation(this);
        if (this.viewPager == null) {
            ViewParent parent = getParent();
            if (parent instanceof ViewPager) {
                setupWithViewPager((ViewPager) parent, true, true);
            }
        }
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        TabView tabView;
        super.onConfigurationChanged(configuration);
        for (int i5 = 0; i5 < getTabCount(); i5++) {
            Tab tabAt = getTabAt(i5);
            if (tabAt != null && (tabView = tabAt.view) != null && tabView.mMainTabTouchBackground != null) {
                tabAt.view.mMainTabTouchBackground.setAlpha(0.0f);
            }
        }
        updateTabViews();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.setupViewPagerImplicitly) {
            setupWithViewPager(null);
            this.setupViewPagerImplicitly = false;
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) j.a(1, getTabCount(), 1, false).f1791a);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return isScrollingEnabled() && super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        super.onLayout(z9, i5, i7, i9, i10);
        updateTabViews();
        if (z9) {
            this.mMaxTouchSlop = Math.max(this.mMaxTouchSlop, i9 - i5);
        }
        int i11 = (this.mode == 1 || !(canScrollHorizontally(1) || canScrollHorizontally(-1))) ? this.mMaxTouchSlop : this.mDefaultTouchSlop;
        if (this.mCurrentTouchSlop != i11) {
            Method methodT = com.bumptech.glide.c.t(HorizontalScrollView.class, "hidden_setTouchSlop", Integer.TYPE);
            if (methodT != null) {
                com.bumptech.glide.c.C(this, methodT, Integer.valueOf(i11));
            }
            this.mCurrentTouchSlop = i11;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0093  */
    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r7, int r8) {
        /*
            Method dump skipped, instruction units count: 220
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.tabs.TabLayout.onMeasure(int, int):void");
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 8 || isScrollingEnabled()) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.view.View
    public void onVisibilityChanged(View view, int i5) {
        TabView tabView;
        super.onVisibilityChanged(view, i5);
        for (int i7 = 0; i7 < getTabCount(); i7++) {
            Tab tabAt = getTabAt(i7);
            if (tabAt != null && (tabView = tabAt.view) != null && tabView.mMainTabTouchBackground != null) {
                tabAt.view.mMainTabTouchBackground.setAlpha(0.0f);
            }
        }
    }

    public void populateFromPagerAdapter() {
        int currentItem;
        removeAllTabs();
        a aVar = this.pagerAdapter;
        if (aVar != null) {
            int count = aVar.getCount();
            for (int i5 = 0; i5 < count; i5++) {
                addTab(newTab().setText(this.pagerAdapter.getPageTitle(i5)), false);
            }
            ViewPager viewPager = this.viewPager;
            if (viewPager == null || count <= 0 || (currentItem = viewPager.getCurrentItem()) == getSelectedTabPosition() || currentItem >= getTabCount()) {
                return;
            }
            selectTab(getTabAt(currentItem), true, true);
        }
    }

    public boolean releaseFromTabPool(Tab tab) {
        return tabPool.c(tab);
    }

    public void removeAllTabs() {
        for (int childCount = this.slidingTabIndicator.getChildCount() - 1; childCount >= 0; childCount--) {
            removeTabViewAt(childCount);
        }
        Iterator<Tab> it = this.tabs.iterator();
        while (it.hasNext()) {
            Tab next = it.next();
            it.remove();
            next.reset();
            releaseFromTabPool(next);
        }
        this.selectedTab = null;
    }

    public void removeOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        removeOnTabSelectedListener((BaseOnTabSelectedListener) onTabSelectedListener);
    }

    public void removeTab(Tab tab) {
        if (tab.parent != this) {
            throw new IllegalArgumentException("Tab does not belong to this TabLayout.");
        }
        removeTabAt(tab.getPosition());
    }

    public void removeTabAt(int i5) {
        Tab tab = this.selectedTab;
        int position = tab != null ? tab.getPosition() : 0;
        removeTabViewAt(i5);
        Tab tabRemove = this.tabs.remove(i5);
        if (tabRemove != null) {
            tabRemove.reset();
            releaseFromTabPool(tabRemove);
        }
        int size = this.tabs.size();
        int i7 = -1;
        for (int i9 = i5; i9 < size; i9++) {
            if (this.tabs.get(i9).getPosition() == this.indicatorPosition) {
                i7 = i9;
            }
            this.tabs.get(i9).setPosition(i9);
        }
        this.indicatorPosition = i7;
        if (position == i5) {
            selectTab(this.tabs.isEmpty() ? null : this.tabs.get(Math.max(0, i5 - 1)));
        }
    }

    public void selectTab(Tab tab) {
        selectTab(tab, true);
    }

    public ColorStateList seslGetTabSubTextColors() {
        return this.mSubTabSubTextColors;
    }

    public void seslSetBadgeColor(int i5) {
        this.mBadgeColor = i5;
    }

    public void seslSetBadgeTextColor(int i5) {
        this.mBadgeTextColor = i5;
    }

    public void seslSetIconTextGap(int i5) {
        this.mIconTextGap = i5;
        updateAllTabs();
    }

    public void seslSetSubTabIndicatorHeight(int i5) {
        this.mSubTabIndicatorHeight = i5;
    }

    public void seslSetSubTabSelectedIndicatorColor(int i5) {
        this.mSubTabSelectedIndicatorColor = i5;
        setSelectedTabIndicatorColor(i5);
    }

    public void seslSetSubTabStyle() {
        if (this.mDepthStyle == 1) {
            this.mDepthStyle = 2;
            this.tabTextColors = getResources().getColorStateList(c.O(getContext()) ? R.color.sesl_tablayout_subtab_text_color_light : R.color.sesl_tablayout_subtab_text_color_dark);
            if (this.tabs.size() > 0) {
                int selectedTabPosition = getSelectedTabPosition();
                ArrayList arrayList = new ArrayList(this.tabs.size());
                for (int i5 = 0; i5 < this.tabs.size(); i5++) {
                    Tab tabNewTab = newTab();
                    tabNewTab.text = this.tabs.get(i5).text;
                    tabNewTab.icon = this.tabs.get(i5).icon;
                    tabNewTab.customView = this.tabs.get(i5).customView;
                    tabNewTab.subText = this.tabs.get(i5).subText;
                    if (i5 == selectedTabPosition) {
                        tabNewTab.select();
                    }
                    tabNewTab.view.update();
                    arrayList.add(tabNewTab);
                }
                removeAllTabs();
                int i7 = 0;
                while (i7 < arrayList.size()) {
                    addTab((Tab) arrayList.get(i7), i7 == selectedTabPosition);
                    if (this.tabs.get(i7) != null) {
                        this.tabs.get(i7).view.update();
                    }
                    i7++;
                }
                arrayList.clear();
            }
        }
    }

    public void seslSetTabSubTextColors(ColorStateList colorStateList) {
        if (this.mSubTabSubTextColors != colorStateList) {
            this.mSubTabSubTextColors = colorStateList;
            updateAllTabs();
        }
    }

    @Deprecated
    public void seslSetTabTextColor(ColorStateList colorStateList, boolean z9) {
        if (this.tabTextColors != colorStateList) {
            this.tabTextColors = colorStateList;
            if (z9) {
                updateAllTabs();
                return;
            }
            if (this.tabs != null) {
                for (int i5 = 0; i5 < this.tabs.size(); i5++) {
                    TabView tabView = this.tabs.get(i5).view;
                    if (tabView != null && tabView.textView != null) {
                        tabView.textView.setTextColor(this.tabTextColors);
                    }
                }
            }
        }
    }

    public void seslSetTabWidth(int i5) {
        this.mRequestedTabWidth = i5;
    }

    public void seslShowBadge(int i5, boolean z9, String str) {
        seslShowBadge(i5, z9, str, null);
    }

    public void seslShowDotBadge(int i5, boolean z9) {
        seslShowDotBadge(i5, z9, null);
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        MaterialShapeUtils.setElevation(this, f2);
    }

    public void setInlineLabel(boolean z9) {
        if (this.inlineLabel != z9) {
            this.inlineLabel = z9;
            for (int i5 = 0; i5 < this.slidingTabIndicator.getChildCount(); i5++) {
                View childAt = this.slidingTabIndicator.getChildAt(i5);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).updateOrientation();
                }
            }
            applyModeAndGravity();
        }
    }

    public void setInlineLabelResource(int i5) {
        setInlineLabel(getResources().getBoolean(i5));
    }

    @Deprecated
    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        setOnTabSelectedListener((BaseOnTabSelectedListener) onTabSelectedListener);
    }

    public void setPagerAdapter(a aVar, boolean z9) {
        DataSetObserver dataSetObserver;
        a aVar2 = this.pagerAdapter;
        if (aVar2 != null && (dataSetObserver = this.pagerAdapterObserver) != null) {
            aVar2.unregisterDataSetObserver(dataSetObserver);
        }
        this.pagerAdapter = aVar;
        if (z9 && aVar != null) {
            if (this.pagerAdapterObserver == null) {
                this.pagerAdapterObserver = new PagerAdapterObserver();
            }
            aVar.registerDataSetObserver(this.pagerAdapterObserver);
        }
        populateFromPagerAdapter();
    }

    public void setScrollAnimatorListener(Animator.AnimatorListener animatorListener) {
        ensureScrollAnimator();
        this.scrollAnimator.addListener(animatorListener);
    }

    public void setScrollPosition(int i5, float f2, boolean z9) {
        setScrollPosition(i5, f2, z9, true);
    }

    public void setSelectedTabIndicator(Drawable drawable) {
        if (drawable == null) {
            drawable = new GradientDrawable();
        }
        Drawable drawableMutate = drawable.mutate();
        this.tabSelectedIndicator = drawableMutate;
        DrawableUtils.setTint(drawableMutate, this.tabSelectedIndicatorColor);
        int intrinsicHeight = this.tabIndicatorHeight;
        if (intrinsicHeight == -1) {
            intrinsicHeight = this.tabSelectedIndicator.getIntrinsicHeight();
        }
        this.slidingTabIndicator.setSelectedIndicatorHeight(intrinsicHeight);
    }

    public void setSelectedTabIndicatorColor(int i5) {
        int i7;
        updateTabViews(false);
        this.mTabSelectedIndicatorColor = i5;
        Iterator<Tab> it = this.tabs.iterator();
        while (it.hasNext()) {
            SeslAbsIndicatorView seslAbsIndicatorView = it.next().view.mIndicatorView;
            if (seslAbsIndicatorView != null) {
                if (this.mDepthStyle != 2 || (i7 = this.mSubTabSelectedIndicatorColor) == -1) {
                    seslAbsIndicatorView.setSelectedIndicatorColor(i5);
                } else {
                    seslAbsIndicatorView.setSelectedIndicatorColor(i7);
                }
                seslAbsIndicatorView.invalidate();
            }
        }
    }

    public void setSelectedTabIndicatorGravity(int i5) {
        if (this.tabIndicatorGravity != i5) {
            this.tabIndicatorGravity = i5;
            SlidingTabIndicator slidingTabIndicator = this.slidingTabIndicator;
            WeakHashMap weakHashMap = W.f7199a;
            slidingTabIndicator.postInvalidateOnAnimation();
        }
    }

    @Deprecated
    public void setSelectedTabIndicatorHeight(int i5) {
        this.tabIndicatorHeight = i5;
        this.slidingTabIndicator.setSelectedIndicatorHeight(i5);
    }

    public void setTabGravity(int i5) {
        if (this.tabGravity != i5) {
            this.tabGravity = i5;
            applyModeAndGravity();
        }
    }

    public void setTabIconTint(ColorStateList colorStateList) {
        if (this.tabIconTint != colorStateList) {
            this.tabIconTint = colorStateList;
            updateAllTabs();
        }
    }

    public void setTabIconTintResource(int i5) {
        setTabIconTint(p0.a.f(getContext(), i5));
    }

    public void setTabIndicatorAnimationMode(int i5) {
        this.tabIndicatorAnimationMode = i5;
        if (i5 == 0) {
            this.tabIndicatorInterpolator = new TabIndicatorInterpolator();
            return;
        }
        if (i5 == 1) {
            this.tabIndicatorInterpolator = new ElasticTabIndicatorInterpolator();
        } else {
            if (i5 == 2) {
                this.tabIndicatorInterpolator = new FadeTabIndicatorInterpolator();
                return;
            }
            throw new IllegalArgumentException(i5 + " is not a valid TabIndicatorAnimationMode");
        }
    }

    public void setTabIndicatorFullWidth(boolean z9) {
        this.tabIndicatorFullWidth = z9;
        this.slidingTabIndicator.jumpIndicatorToSelectedPosition();
        SlidingTabIndicator slidingTabIndicator = this.slidingTabIndicator;
        WeakHashMap weakHashMap = W.f7199a;
        slidingTabIndicator.postInvalidateOnAnimation();
    }

    public void setTabMode(int i5) {
        if (i5 != this.mode) {
            this.mode = i5;
            applyModeAndGravity();
            updateTabViews();
        }
    }

    public void setTabRippleColor(ColorStateList colorStateList) {
        if (this.tabRippleColorStateList != colorStateList) {
            this.tabRippleColorStateList = colorStateList;
            for (int i5 = 0; i5 < this.slidingTabIndicator.getChildCount(); i5++) {
                View childAt = this.slidingTabIndicator.getChildAt(i5);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).updateBackgroundDrawable(getContext());
                }
            }
        }
    }

    public void setTabRippleColorResource(int i5) {
        setTabRippleColor(p0.a.f(getContext(), i5));
    }

    public void setTabTextColors(ColorStateList colorStateList) {
        if (this.tabTextColors != colorStateList) {
            this.tabTextColors = colorStateList;
            updateAllTabs();
        }
    }

    @Deprecated
    public void setTabsFromPagerAdapter(a aVar) {
        setPagerAdapter(aVar, false);
    }

    public void setUnboundedRipple(boolean z9) {
        if (this.unboundedRipple != z9) {
            this.unboundedRipple = z9;
            for (int i5 = 0; i5 < this.slidingTabIndicator.getChildCount(); i5++) {
                View childAt = this.slidingTabIndicator.getChildAt(i5);
                if (childAt instanceof TabView) {
                    ((TabView) childAt).updateBackgroundDrawable(getContext());
                }
            }
        }
    }

    public void setUnboundedRippleResource(int i5) {
        setUnboundedRipple(getResources().getBoolean(i5));
    }

    public void setupWithViewPager(ViewPager viewPager) {
        setupWithViewPager(viewPager, true);
    }

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    public void updateTabViews(boolean z9) {
        for (int i5 = 0; i5 < this.slidingTabIndicator.getChildCount(); i5++) {
            View childAt = this.slidingTabIndicator.getChildAt(i5);
            childAt.setMinimumWidth(getTabMinWidth());
            updateTabViewLayoutParams((LinearLayout.LayoutParams) childAt.getLayoutParams());
            if (z9) {
                childAt.requestLayout();
            }
        }
        updateTabViews();
    }

    public void updateViewPagerScrollState(int i5) {
        this.viewPagerScrollState = i5;
    }

    public TabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.tabStyle);
    }

    @Deprecated
    public void addOnTabSelectedListener(BaseOnTabSelectedListener baseOnTabSelectedListener) {
        if (this.selectedListeners.contains(baseOnTabSelectedListener)) {
            return;
        }
        this.selectedListeners.add(baseOnTabSelectedListener);
    }

    public void addTab(Tab tab, int i5) {
        addTab(tab, i5, this.tabs.isEmpty());
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i5) {
        addViewInternal(view);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    @Deprecated
    public void removeOnTabSelectedListener(BaseOnTabSelectedListener baseOnTabSelectedListener) {
        this.selectedListeners.remove(baseOnTabSelectedListener);
    }

    public void selectTab(Tab tab, boolean z9) {
        selectTab(tab, z9, true);
    }

    public void seslShowBadge(int i5, boolean z9, String str, String str2) {
        if (this.mDepthStyle == 2 || this.tabs.get(i5) == null || this.tabs.get(i5).view == null) {
            return;
        }
        TabView tabView = this.tabs.get(i5).view;
        if (tabView.mNBadgeView == null) {
            createAddBadge(1, tabView);
        }
        if (tabView.mNBadgeView != null) {
            TextView textView = tabView.mNBadgeView;
            textView.setText(str);
            if (!z9) {
                textView.setVisibility(8);
                return;
            }
            textView.setVisibility(0);
            if (this.mBadgeColor != -1) {
                E.a.g(textView.getBackground(), this.mBadgeColor);
            }
            int i7 = this.mBadgeTextColor;
            if (i7 != -1) {
                textView.setTextColor(i7);
            }
            if (str2 != null) {
                textView.setContentDescription(str2);
            }
            updateTabViews();
            textView.requestLayout();
        }
    }

    public void seslShowDotBadge(int i5, boolean z9, String str) {
        if (this.tabs.get(i5) == null || this.tabs.get(i5).view == null) {
            return;
        }
        TabView tabView = this.tabs.get(i5).view;
        if (tabView.mDotBadgeView == null) {
            createAddBadge(2, tabView);
        }
        if (tabView.mDotBadgeView != null) {
            TextView textView = tabView.mDotBadgeView;
            if (!z9) {
                textView.setVisibility(8);
                return;
            }
            textView.setVisibility(0);
            if (this.mBadgeColor != -1) {
                E.a.g(textView.getBackground(), this.mBadgeColor);
            }
            if (str != null) {
                textView.setContentDescription(str);
            }
            updateTabViews();
        }
    }

    @Deprecated
    public void setOnTabSelectedListener(BaseOnTabSelectedListener baseOnTabSelectedListener) {
        BaseOnTabSelectedListener baseOnTabSelectedListener2 = this.selectedListener;
        if (baseOnTabSelectedListener2 != null) {
            removeOnTabSelectedListener(baseOnTabSelectedListener2);
        }
        this.selectedListener = baseOnTabSelectedListener;
        if (baseOnTabSelectedListener != null) {
            addOnTabSelectedListener(baseOnTabSelectedListener);
        }
    }

    public void setScrollPosition(int i5, float f2, boolean z9, boolean z10) {
        setScrollPosition(i5, f2, z9, z10, true);
    }

    public void setupWithViewPager(ViewPager viewPager, boolean z9) {
        setupWithViewPager(viewPager, z9, false);
    }

    public static class Tab {
        public static final int INVALID_POSITION = -1;
        private CharSequence contentDesc;
        private View customView;
        private Drawable icon;
        public TabLayout parent;
        private CharSequence subText;
        private Object tag;
        private CharSequence text;
        public TabView view;
        private int position = -1;

        @LabelVisibility
        private int labelVisibilityMode = 1;
        private int id = -1;

        public BadgeDrawable getBadge() {
            return this.view.getBadge();
        }

        public CharSequence getContentDescription() {
            TabView tabView = this.view;
            if (tabView == null) {
                return null;
            }
            return tabView.getContentDescription();
        }

        public View getCustomView() {
            return this.customView;
        }

        public Drawable getIcon() {
            return this.icon;
        }

        public int getId() {
            return this.id;
        }

        public BadgeDrawable getOrCreateBadge() {
            return this.view.getOrCreateBadge();
        }

        public int getPosition() {
            return this.position;
        }

        @LabelVisibility
        public int getTabLabelVisibility() {
            return this.labelVisibilityMode;
        }

        public Object getTag() {
            return this.tag;
        }

        public CharSequence getText() {
            return this.text;
        }

        public boolean isSelected() {
            TabLayout tabLayout = this.parent;
            if (tabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            int selectedTabPosition = tabLayout.getSelectedTabPosition();
            return selectedTabPosition != -1 && selectedTabPosition == this.position;
        }

        public void removeBadge() {
            this.view.removeBadge();
        }

        public void reset() {
            this.parent = null;
            this.view = null;
            this.tag = null;
            this.icon = null;
            this.id = -1;
            this.text = null;
            this.contentDesc = null;
            this.position = -1;
            this.customView = null;
            this.subText = null;
        }

        public void select() {
            TabLayout tabLayout = this.parent;
            if (tabLayout == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            tabLayout.selectTab(this);
        }

        public CharSequence seslGetSubText() {
            return this.subText;
        }

        public TextView seslGetSubTextView() {
            TabView tabView;
            if (this.customView != null || (tabView = this.view) == null) {
                return null;
            }
            return tabView.mSubTextView;
        }

        public TextView seslGetTextView() {
            TabView tabView;
            if (this.customView != null || (tabView = this.view) == null) {
                return null;
            }
            return tabView.textView;
        }

        public Tab seslSetSubText(CharSequence charSequence) {
            this.subText = charSequence;
            updateView();
            return this;
        }

        public Tab setContentDescription(int i5) {
            TabLayout tabLayout = this.parent;
            if (tabLayout != null) {
                return setContentDescription(tabLayout.getResources().getText(i5));
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        public Tab setCustomView(View view) {
            if (this.view.textView != null) {
                this.view.removeAllViews();
            }
            this.customView = view;
            updateView();
            return this;
        }

        public Tab setIcon(Drawable drawable) {
            this.icon = drawable;
            TabLayout tabLayout = this.parent;
            if (tabLayout.tabGravity == 1 || tabLayout.mode == 2) {
                tabLayout.updateTabViews(true);
            }
            updateView();
            if (BadgeUtils.USE_COMPAT_PARENT && this.view.hasBadgeDrawable() && this.view.badgeDrawable.isVisible()) {
                this.view.invalidate();
            }
            return this;
        }

        public Tab setId(int i5) {
            this.id = i5;
            TabView tabView = this.view;
            if (tabView != null) {
                tabView.setId(i5);
            }
            return this;
        }

        public void setPosition(int i5) {
            this.position = i5;
        }

        public Tab setTabLabelVisibility(@LabelVisibility int i5) {
            this.labelVisibilityMode = i5;
            TabLayout tabLayout = this.parent;
            if (tabLayout.tabGravity == 1 || tabLayout.mode == 2) {
                tabLayout.updateTabViews(true);
            }
            updateView();
            if (BadgeUtils.USE_COMPAT_PARENT && this.view.hasBadgeDrawable() && this.view.badgeDrawable.isVisible()) {
                this.view.invalidate();
            }
            return this;
        }

        public Tab setTag(Object obj) {
            this.tag = obj;
            return this;
        }

        public Tab setText(CharSequence charSequence) {
            if (TextUtils.isEmpty(this.contentDesc) && !TextUtils.isEmpty(charSequence)) {
                this.view.setContentDescription(charSequence);
            }
            this.text = charSequence;
            updateView();
            return this;
        }

        public void updateView() {
            TabView tabView = this.view;
            if (tabView != null) {
                tabView.update();
            }
        }

        public Tab setContentDescription(CharSequence charSequence) {
            this.contentDesc = charSequence;
            updateView();
            return this;
        }

        public Tab setCustomView(int i5) {
            return setCustomView(LayoutInflater.from(this.view.getContext()).inflate(i5, (ViewGroup) this.view, false));
        }

        public Tab setText(int i5) {
            TabLayout tabLayout = this.parent;
            if (tabLayout != null) {
                return setText(tabLayout.getResources().getText(i5));
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        public Tab setIcon(int i5) {
            TabLayout tabLayout = this.parent;
            if (tabLayout != null) {
                return setIcon(android.support.v4.media.session.f.y(tabLayout.getContext(), i5));
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }
    }

    public TabLayout(Context context, AttributeSet attributeSet, int i5) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i5, DEF_STYLE_RES), attributeSet, i5);
        this.indicatorPosition = -1;
        this.tabs = new ArrayList<>();
        this.selectedTabTextAppearance = -1;
        this.tabSelectedIndicatorColor = 0;
        this.tabMaxWidth = Integer.MAX_VALUE;
        this.tabIndicatorHeight = -1;
        this.selectedListeners = new ArrayList<>();
        this.tabViewPool = new K.e(12);
        this.mIsScaledTextSizeType = false;
        this.mIconTextGap = -1;
        this.mRequestedTabWidth = -1;
        this.mIsOverScreen = false;
        this.mOverScreenMaxWidth = -1;
        this.mBadgeColor = -1;
        this.mBadgeTextColor = -1;
        this.mSubTabSelectedIndicatorColor = -1;
        this.mSubTabIndicatorHeight = 1;
        this.mSubTabIndicator2ndHeight = 1;
        Context context2 = getContext();
        setHorizontalScrollBarEnabled(false);
        SlidingTabIndicator slidingTabIndicator = new SlidingTabIndicator(context2);
        this.slidingTabIndicator = slidingTabIndicator;
        super.addView(slidingTabIndicator, 0, new FrameLayout.LayoutParams(-2, -1));
        TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R.styleable.TabLayout, i5, c.O(context2) ? R.style.Widget_Design_TabLayout_Light : R.style.Widget_Design_TabLayout);
        ColorStateList colorStateListOrNull = DrawableUtils.getColorStateListOrNull(getBackground());
        if (colorStateListOrNull != null) {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
            materialShapeDrawable.setFillColor(colorStateListOrNull);
            materialShapeDrawable.initializeElevationOverlay(context2);
            WeakHashMap weakHashMap = W.f7199a;
            materialShapeDrawable.setElevation(M.i(this));
            setBackground(materialShapeDrawable);
        }
        setSelectedTabIndicator(MaterialResources.getDrawable(context2, typedArrayObtainStyledAttributes, R.styleable.TabLayout_tabIndicator));
        int i7 = R.styleable.TabLayout_tabIndicatorColor;
        setSelectedTabIndicatorColor(typedArrayObtainStyledAttributes.getColor(i7, 0));
        slidingTabIndicator.setSelectedIndicatorHeight(typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabIndicatorHeight, -1));
        this.mTabSelectedIndicatorColor = typedArrayObtainStyledAttributes.getColor(i7, 0);
        setSelectedTabIndicatorGravity(typedArrayObtainStyledAttributes.getInt(R.styleable.TabLayout_tabIndicatorGravity, 0));
        setTabIndicatorAnimationMode(typedArrayObtainStyledAttributes.getInt(R.styleable.TabLayout_tabIndicatorAnimationMode, 0));
        setTabIndicatorFullWidth(typedArrayObtainStyledAttributes.getBoolean(R.styleable.TabLayout_tabIndicatorFullWidth, true));
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabPadding, 0);
        this.tabPaddingBottom = dimensionPixelSize;
        this.tabPaddingEnd = dimensionPixelSize;
        this.tabPaddingTop = dimensionPixelSize;
        this.tabPaddingStart = dimensionPixelSize;
        this.tabPaddingStart = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingStart, dimensionPixelSize);
        this.tabPaddingTop = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingTop, this.tabPaddingTop);
        this.tabPaddingEnd = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingEnd, this.tabPaddingEnd);
        this.tabPaddingBottom = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabPaddingBottom, this.tabPaddingBottom);
        if (ThemeEnforcement.isMaterial3Theme(context2)) {
            this.defaultTabTextAppearance = R.attr.textAppearanceTitleSmall;
        } else {
            this.defaultTabTextAppearance = R.attr.textAppearanceButton;
        }
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.TabLayout_tabTextAppearance, R.style.TextAppearance_Design_Tab);
        this.tabTextAppearance = resourceId;
        int[] iArr = AbstractC0478a.f10552C;
        TypedArray typedArrayObtainStyledAttributes2 = context2.obtainStyledAttributes(resourceId, iArr);
        this.tabTextSize = typedArrayObtainStyledAttributes2.getDimensionPixelSize(0, 0);
        this.mIsScaledTextSizeType = typedArrayObtainStyledAttributes2.getText(0).toString().contains("sp");
        this.tabTextColors = MaterialResources.getColorStateList(context2, typedArrayObtainStyledAttributes2, 3);
        Resources resources = getResources();
        this.mMaxTouchSlop = resources.getDisplayMetrics().widthPixels;
        int scaledTouchSlop = ViewConfiguration.get(context2).getScaledTouchSlop();
        this.mDefaultTouchSlop = scaledTouchSlop;
        this.mCurrentTouchSlop = scaledTouchSlop;
        if (Build.VERSION.SDK_INT >= 34) {
            Typeface typefaceCreate = Typeface.create("sec", 0);
            this.mBoldTypeface = Typeface.create(typefaceCreate, FONT_WEIGHT_SEMIBOLD, false);
            this.mNormalTypeface = Typeface.create(typefaceCreate, 400, false);
        } else {
            String string = resources.getString(com.samsung.android.keyscafe.R.string.sesl_font_family_regular);
            this.mBoldTypeface = Typeface.create(string, 1);
            this.mNormalTypeface = Typeface.create(string, 0);
        }
        this.mSubTabIndicatorHeight = resources.getDimensionPixelSize(R.dimen.sesl_tablayout_subtab_indicator_height);
        this.mSubTabIndicator2ndHeight = resources.getDimensionPixelSize(R.dimen.sesl_tablayout_subtab_indicator_2nd_height);
        this.mTabMinSideSpace = resources.getDimensionPixelSize(R.dimen.sesl_tab_min_side_space);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(R.styleable.TabLayout_seslTabSubTextAppearance, R.style.TextAppearance_Design_Tab_SubText);
        this.mSubTabSubTextAppearance = resourceId2;
        TypedArray typedArrayObtainStyledAttributes3 = context2.obtainStyledAttributes(resourceId2, iArr);
        try {
            this.mSubTabSubTextColors = MaterialResources.getColorStateList(context2, typedArrayObtainStyledAttributes3, 3);
            this.mSubTabTextSize = typedArrayObtainStyledAttributes3.getDimensionPixelSize(0, 0);
            typedArrayObtainStyledAttributes2.recycle();
            typedArrayObtainStyledAttributes3.recycle();
            int i9 = R.styleable.TabLayout_seslTabSubTextColor;
            if (typedArrayObtainStyledAttributes.hasValue(i9)) {
                this.mSubTabSubTextColors = MaterialResources.getColorStateList(context2, typedArrayObtainStyledAttributes, i9);
            }
            int i10 = R.styleable.TabLayout_seslTabSelectedSubTextColor;
            if (typedArrayObtainStyledAttributes.hasValue(i10)) {
                this.mSubTabSubTextColors = createColorStateList(this.mSubTabSubTextColors.getDefaultColor(), typedArrayObtainStyledAttributes.getColor(i10, 0));
            }
            this.mDepthStyle = typedArrayObtainStyledAttributes.getInt(R.styleable.TabLayout_seslTabStyle, 1);
            int i11 = R.styleable.TabLayout_tabSelectedTextAppearance;
            if (typedArrayObtainStyledAttributes.hasValue(i11)) {
                this.selectedTabTextAppearance = typedArrayObtainStyledAttributes.getResourceId(i11, resourceId);
            }
            int i12 = this.selectedTabTextAppearance;
            if (i12 != -1) {
                typedArrayObtainStyledAttributes3 = context2.obtainStyledAttributes(i12, iArr);
                try {
                    this.selectedTabTextSize = typedArrayObtainStyledAttributes3.getDimensionPixelSize(0, (int) this.tabTextSize);
                    ColorStateList colorStateList = MaterialResources.getColorStateList(context2, typedArrayObtainStyledAttributes3, 3);
                    if (colorStateList != null) {
                        this.tabTextColors = createColorStateList(this.tabTextColors.getDefaultColor(), colorStateList.getColorForState(new int[]{android.R.attr.state_selected}, colorStateList.getDefaultColor()));
                    }
                } finally {
                    typedArrayObtainStyledAttributes3.recycle();
                }
            }
            int i13 = R.styleable.TabLayout_tabTextColor;
            if (typedArrayObtainStyledAttributes.hasValue(i13)) {
                this.tabTextColors = MaterialResources.getColorStateList(context2, typedArrayObtainStyledAttributes, i13);
            }
            int i14 = R.styleable.TabLayout_tabSelectedTextColor;
            if (typedArrayObtainStyledAttributes.hasValue(i14)) {
                this.tabTextColors = createColorStateList(this.tabTextColors.getDefaultColor(), typedArrayObtainStyledAttributes.getColor(i14, 0));
            }
            this.tabIconTint = MaterialResources.getColorStateList(context2, typedArrayObtainStyledAttributes, R.styleable.TabLayout_tabIconTint);
            this.tabIconTintMode = ViewUtils.parseTintMode(typedArrayObtainStyledAttributes.getInt(R.styleable.TabLayout_tabIconTintMode, -1), null);
            this.tabRippleColorStateList = MaterialResources.getColorStateList(context2, typedArrayObtainStyledAttributes, R.styleable.TabLayout_tabRippleColor);
            this.tabIndicatorAnimationDuration = typedArrayObtainStyledAttributes.getInt(R.styleable.TabLayout_tabIndicatorAnimationDuration, 300);
            this.tabIndicatorTimeInterpolator = MotionUtils.resolveThemeInterpolator(context2, R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            this.requestedTabMinWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabMinWidth, -1);
            this.requestedTabMaxWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabMaxWidth, -1);
            this.tabBackgroundResId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.TabLayout_tabBackground, 0);
            this.contentInsetStart = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.TabLayout_tabContentStart, 0);
            this.mode = typedArrayObtainStyledAttributes.getInt(R.styleable.TabLayout_tabMode, 1);
            int i15 = typedArrayObtainStyledAttributes.getInt(R.styleable.TabLayout_tabGravity, 0);
            this.tabGravity = i15;
            this.mFirstTabGravity = i15;
            this.inlineLabel = typedArrayObtainStyledAttributes.getBoolean(R.styleable.TabLayout_tabInlineLabel, false);
            this.unboundedRipple = typedArrayObtainStyledAttributes.getBoolean(R.styleable.TabLayout_tabUnboundedRipple, false);
            typedArrayObtainStyledAttributes.recycle();
            this.tabTextMultiLineSize = resources.getDimensionPixelSize(R.dimen.sesl_tab_text_size_2line);
            this.scrollableTabMinWidth = resources.getDimensionPixelSize(R.dimen.sesl_tab_scrollable_min_width);
            applyModeAndGravity();
            Drawable background = getBackground();
            this.mContentResolver = context2.getContentResolver();
            if (background instanceof ColorDrawable) {
                this.mBackgroundColorDrawable = (ColorDrawable) background;
            }
            if (this.mDepthStyle == 2) {
                this.tabTextColors = getResources().getColorStateList(c.O(getContext()) ? R.color.sesl_tablayout_subtab_text_color_light : R.color.sesl_tablayout_subtab_text_color_dark);
            }
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes2.recycle();
            throw th;
        }
    }

    private void selectTab(Tab tab, boolean z9, boolean z10) {
        ViewPager viewPager;
        if (tab != null && !tab.view.isEnabled() && (viewPager = this.viewPager) != null) {
            viewPager.setCurrentItem(getSelectedTabPosition());
            return;
        }
        Tab tab2 = this.selectedTab;
        if (tab2 == tab) {
            if (tab2 != null) {
                dispatchTabReselected(tab);
                animateToTab(tab.getPosition());
                return;
            }
            return;
        }
        int position = tab != null ? tab.getPosition() : -1;
        if (z9) {
            if ((tab2 == null || tab2.getPosition() == -1) && position != -1) {
                setScrollPosition(position, 0.0f, true);
            } else {
                animateToTab(position);
            }
            if (position != -1) {
                setSelectedTabView(position, z10);
            }
        }
        this.selectedTab = tab;
        if (tab2 != null && tab2.parent != null) {
            dispatchTabUnselected(tab2);
        }
        if (tab != null) {
            dispatchTabSelected(tab);
        }
    }

    private void setupWithViewPager(ViewPager viewPager, boolean z9, boolean z10) {
        ViewPager viewPager2 = this.viewPager;
        if (viewPager2 != null) {
            TabLayoutOnPageChangeListener tabLayoutOnPageChangeListener = this.pageChangeListener;
            if (tabLayoutOnPageChangeListener != null) {
                viewPager2.removeOnPageChangeListener(tabLayoutOnPageChangeListener);
            }
            AdapterChangeListener adapterChangeListener = this.adapterChangeListener;
            if (adapterChangeListener != null) {
                this.viewPager.removeOnAdapterChangeListener(adapterChangeListener);
            }
        }
        BaseOnTabSelectedListener baseOnTabSelectedListener = this.currentVpSelectedListener;
        if (baseOnTabSelectedListener != null) {
            removeOnTabSelectedListener(baseOnTabSelectedListener);
            this.currentVpSelectedListener = null;
        }
        if (viewPager != null) {
            this.viewPager = viewPager;
            if (this.pageChangeListener == null) {
                this.pageChangeListener = new TabLayoutOnPageChangeListener(this);
            }
            this.pageChangeListener.reset();
            viewPager.addOnPageChangeListener(this.pageChangeListener);
            ViewPagerOnTabSelectedListener viewPagerOnTabSelectedListener = new ViewPagerOnTabSelectedListener(viewPager);
            this.currentVpSelectedListener = viewPagerOnTabSelectedListener;
            addOnTabSelectedListener((BaseOnTabSelectedListener) viewPagerOnTabSelectedListener);
            a adapter = viewPager.getAdapter();
            if (adapter != null) {
                setPagerAdapter(adapter, z9);
            }
            if (this.adapterChangeListener == null) {
                this.adapterChangeListener = new AdapterChangeListener();
            }
            this.adapterChangeListener.setAutoRefresh(z9);
            viewPager.addOnAdapterChangeListener(this.adapterChangeListener);
            setScrollPosition(viewPager.getCurrentItem(), 0.0f, true);
        } else {
            this.viewPager = null;
            setPagerAdapter(null, false);
        }
        this.setupViewPagerImplicitly = z10;
    }

    public void addTab(Tab tab, boolean z9) {
        addTab(tab, this.tabs.size(), z9);
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup, android.view.ViewManager
    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        addViewInternal(view);
    }

    public void setScrollPosition(int i5, float f2, boolean z9, boolean z10, boolean z11) {
        int iRound = Math.round(i5 + f2);
        if (iRound < 0 || iRound >= this.slidingTabIndicator.getChildCount()) {
            return;
        }
        if (z10) {
            this.slidingTabIndicator.setIndicatorPositionFromTabPosition(i5, f2);
        }
        ValueAnimator valueAnimator = this.scrollAnimator;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            this.scrollAnimator.cancel();
        }
        int iCalculateScrollXForTab = calculateScrollXForTab(i5, f2);
        int scrollX = getScrollX();
        boolean z12 = (i5 < getSelectedTabPosition() && iCalculateScrollXForTab >= scrollX) || (i5 > getSelectedTabPosition() && iCalculateScrollXForTab <= scrollX) || i5 == getSelectedTabPosition();
        WeakHashMap weakHashMap = W.f7199a;
        if (getLayoutDirection() == 1) {
            z12 = (i5 < getSelectedTabPosition() && iCalculateScrollXForTab <= scrollX) || (i5 > getSelectedTabPosition() && iCalculateScrollXForTab >= scrollX) || i5 == getSelectedTabPosition();
        }
        if (z12 || this.viewPagerScrollState == 1 || z11) {
            if (i5 < 0) {
                iCalculateScrollXForTab = 0;
            }
            scrollTo(iCalculateScrollXForTab, 0);
        }
        if (z9) {
            setSelectedTabView(iRound, true);
        }
    }

    public void addTab(Tab tab, int i5, boolean z9) {
        if (tab.parent == this) {
            configureTab(tab, i5);
            addTabView(tab);
            if (z9) {
                tab.select();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
    }

    @Override // android.widget.HorizontalScrollView, android.view.ViewGroup
    public void addView(View view, int i5, ViewGroup.LayoutParams layoutParams) {
        addViewInternal(view);
    }

    public void seslSetTabSubTextColors(int i5, int i7) {
        seslSetTabSubTextColors(createColorStateList(i5, i7));
    }

    public void setTabTextColors(int i5, int i7) {
        setTabTextColors(createColorStateList(i5, i7));
    }

    private void updateTabViews() {
        if (this.tabs.isEmpty()) {
            return;
        }
        for (int i5 = 0; i5 < this.tabs.size(); i5++) {
            TabView tabView = this.tabs.get(i5).view;
            updateBadgePosition(tabView);
            setShowButtonShape(tabView);
        }
    }

    public void setSelectedTabIndicator(int i5) {
        if (i5 != 0) {
            setSelectedTabIndicator(android.support.v4.media.session.f.y(getContext(), i5));
        } else {
            setSelectedTabIndicator((Drawable) null);
        }
    }
}
