package com.google.android.material.appbar;

import D.f;
import L.l;
import L.o;
import L.w;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.ScrollView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.coordinatorlayout.widget.d;
import androidx.coordinatorlayout.widget.g;
import androidx.core.view.C0210b;
import androidx.core.view.InterfaceC0222n;
import androidx.core.view.InterfaceC0226s;
import androidx.core.view.M;
import androidx.core.view.T;
import androidx.core.view.W;
import androidx.core.view.w0;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import f.AbstractC0510a;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class AppBarLayout extends LinearLayout implements androidx.coordinatorlayout.widget.c, androidx.coordinatorlayout.widget.a {
    private static final float DEFAULT_HEIGHT_RATIO_TO_SCREEN = 0.39f;
    public static final int IMMERSIVE_DETACH_OPTION_SET_FIT_SYSTEM_WINDOW = 1;
    private static final int INVALID_SCROLL_RANGE = -1;
    static final int PENDING_ACTION_ANIMATE_ENABLED = 4;
    static final int PENDING_ACTION_COLLAPSED = 2;
    static final int PENDING_ACTION_COLLAPSED_IMM = 512;
    static final int PENDING_ACTION_EXPANDED = 1;
    static final int PENDING_ACTION_FORCE = 8;
    static final int PENDING_ACTION_NONE = 0;
    public static final int SESL_STATE_COLLAPSED = 0;
    public static final int SESL_STATE_EXPANDED = 1;
    public static final int SESL_STATE_HIDE = 2;
    public static final int SESL_STATE_IDLE = 3;
    private static final String TAG = "AppBarLayout";
    private final float appBarElevation;
    private Behavior behavior;
    private int currentOffset;
    private int downPreScrollRange;
    private int downScrollRange;
    private final boolean hasLiftOnScrollColor;
    private boolean haveChildWithInterpolator;
    private boolean isMouse;
    private w0 lastInsets;
    private boolean liftOnScroll;
    private ValueAnimator liftOnScrollColorAnimator;
    private final long liftOnScrollColorDuration;
    private final TimeInterpolator liftOnScrollColorInterpolator;
    private ValueAnimator.AnimatorUpdateListener liftOnScrollColorUpdateListener;
    private final List<LiftOnScrollListener> liftOnScrollListeners;
    private WeakReference<View> liftOnScrollTargetView;
    private int liftOnScrollTargetViewId;
    private boolean liftable;
    private boolean liftableOverride;
    private boolean lifted;
    private List<BaseOnOffsetChangedListener> listeners;
    private int mAdditionalScrollRange;
    private SeslAppbarState mAppbarState;
    private Drawable mBackground;
    private int mBottomPadding;
    private float mCollapsedHeight;
    private int mCurrentOrientation;
    private int mCurrentScreenHeight;
    private int mCustomHeight;
    private float mCustomHeightProportion;
    private boolean mHasSuggestion;
    private float mHeightProportion;
    private List<SeslBaseOnImmOffsetChangedListener> mImmOffsetListener;
    private int mImmersiveTopInset;
    private boolean mIsActivatedByUser;
    private boolean mIsActivatedImmersiveScroll;
    private boolean mIsCanScroll;
    private boolean mIsDetachedState;
    private boolean mIsReservedImmersiveDetachOption;
    private f mLastSysInsets;
    private f mLastTappableInsets;
    private boolean mReservedFitSystemWindow;
    private Resources mResources;
    private boolean mRestoreAnim;
    private boolean mSetCustomHeight;
    private boolean mSetCustomProportion;
    private boolean mUseCollapsedHeight;
    private boolean mUseCustomHeight;
    private boolean mUseCustomPadding;
    private int pendingAction;
    private Drawable statusBarForeground;
    private Integer statusBarForegroundOriginalColor;
    private int[] tmpStatesArray;
    private int totalScrollRange;
    private static final int DEF_STYLE_RES = R.style.Widget_Design_AppBarLayout;
    private static final int TAPPABLE_ELEMENT = 64;
    private static final int SYSTEM_BARS = 7;

    public static class BaseBehavior<T extends AppBarLayout> extends HeaderBehavior<T> {
        private static final int MAX_OFFSET_ANIMATION_DURATION = 450;
        private boolean coordinatorLayoutA11yScrollable;
        private WeakReference<View> lastNestedScrollingChildRef;
        private int lastStartedType;
        private float mDiffY_Touch;
        private boolean mDirectTouchAppbar;
        private boolean mIsFlingScrollDown;
        private boolean mIsFlingScrollUp;
        private boolean mIsScrollHold;
        private boolean mIsSetStaticDuration;
        private float mLastMotionY_Touch;
        private boolean mLifted;
        private boolean mToolisMouse;
        private int mTouchSlop;
        private float mVelocity;
        private ValueAnimator offsetAnimator;
        private int offsetDelta;
        private BaseDragCallback onDragCallback;
        private SavedState savedState;
        private float touchX;
        private float touchY;

        public static abstract class BaseDragCallback<T extends AppBarLayout> {
            public abstract boolean canDrag(T t8);
        }

        public BaseBehavior() {
            this.mIsFlingScrollDown = false;
            this.mIsFlingScrollUp = false;
            this.mDirectTouchAppbar = false;
            this.mTouchSlop = -1;
            this.mVelocity = 0.0f;
            this.mIsSetStaticDuration = false;
            this.mIsScrollHold = false;
        }

        private boolean addAccessibilityScrollActions(final CoordinatorLayout coordinatorLayout, final T t8, final View view) {
            boolean z9 = false;
            if (getTopBottomOffsetForScrollingSibling() != (-t8.getTotalScrollRange())) {
                addActionToExpand(coordinatorLayout, t8, L.f.f1780f, false);
                z9 = true;
            }
            if (getTopBottomOffsetForScrollingSibling() != 0) {
                if (!view.canScrollVertically(-1)) {
                    addActionToExpand(coordinatorLayout, t8, L.f.f1781g, true);
                    return true;
                }
                final int i5 = -t8.getDownNestedPreScrollRange();
                if (i5 != 0) {
                    W.h(coordinatorLayout, L.f.f1781g, null, new w() { // from class: com.google.android.material.appbar.AppBarLayout.BaseBehavior.3
                        /* JADX WARN: Multi-variable type inference failed */
                        /* JADX WARN: Type inference fix 'apply assigned field type' failed
                        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
                        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
                        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
                        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
                        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
                         */
                        @Override // L.w
                        public boolean perform(View view2, o oVar) {
                            BaseBehavior.this.onNestedPreScroll(coordinatorLayout, t8, view, 0, i5, new int[]{0, 0}, 1);
                            return true;
                        }
                    });
                    return true;
                }
            }
            return z9;
        }

        private void addActionToExpand(CoordinatorLayout coordinatorLayout, final T t8, L.f fVar, final boolean z9) {
            W.h(coordinatorLayout, fVar, null, new w() { // from class: com.google.android.material.appbar.AppBarLayout.BaseBehavior.4
                @Override // L.w
                public boolean perform(View view, o oVar) {
                    t8.setExpanded(z9);
                    return true;
                }
            });
        }

        private void animateOffsetTo(CoordinatorLayout coordinatorLayout, T t8, int i5, float f2) {
            int i7 = 250;
            int iAbs = (Math.abs(this.mVelocity) <= 0.0f || Math.abs(this.mVelocity) > 3000.0f) ? 250 : (int) (((double) (3000.0f - Math.abs(this.mVelocity))) * 0.4d);
            if (iAbs <= 250) {
                iAbs = 250;
            }
            if (this.mIsSetStaticDuration) {
                this.mIsSetStaticDuration = false;
            } else {
                i7 = iAbs;
            }
            if (Math.abs(this.mVelocity) < 2000.0f) {
                animateOffsetWithDuration(coordinatorLayout, t8, i5, i7);
            }
            this.mVelocity = 0.0f;
        }

        private void animateOffsetWithDuration(final CoordinatorLayout coordinatorLayout, final T t8, int i5, int i7) {
            int topBottomOffsetForScrollingSibling = getTopBottomOffsetForScrollingSibling();
            if (topBottomOffsetForScrollingSibling == i5) {
                ValueAnimator valueAnimator = this.offsetAnimator;
                if (valueAnimator == null || !valueAnimator.isRunning()) {
                    return;
                }
                this.offsetAnimator.cancel();
                return;
            }
            ValueAnimator valueAnimator2 = this.offsetAnimator;
            if (valueAnimator2 == null) {
                ValueAnimator valueAnimator3 = new ValueAnimator();
                this.offsetAnimator = valueAnimator3;
                valueAnimator3.setInterpolator(AbstractC0510a.f10753d);
                this.offsetAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.appbar.AppBarLayout.BaseBehavior.1
                    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                    public void onAnimationUpdate(ValueAnimator valueAnimator4) {
                        BaseBehavior.this.setHeaderTopBottomOffset(coordinatorLayout, t8, ((Integer) valueAnimator4.getAnimatedValue()).intValue());
                    }
                });
            } else {
                valueAnimator2.cancel();
            }
            this.offsetAnimator.setDuration(Math.min(i7, MAX_OFFSET_ANIMATION_DURATION));
            this.offsetAnimator.setIntValues(topBottomOffsetForScrollingSibling, i5);
            this.offsetAnimator.start();
        }

        private int calculateSnapOffset(int i5, int i7, int i9) {
            return i5 < (i7 + i9) / 2 ? i7 : i9;
        }

        private boolean canScrollChildren(CoordinatorLayout coordinatorLayout, T t8, View view) {
            return t8.hasScrollableChildren() && coordinatorLayout.getHeight() - view.getHeight() <= t8.getHeight();
        }

        private static boolean checkFlag(int i5, int i7) {
            return (i5 & i7) == i7;
        }

        private boolean childrenHaveScrollFlags(AppBarLayout appBarLayout) {
            int childCount = appBarLayout.getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                if (((LayoutParams) appBarLayout.getChildAt(i5).getLayoutParams()).scrollFlags != 0) {
                    return true;
                }
            }
            return false;
        }

        private View findFirstScrollingChild(CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = coordinatorLayout.getChildAt(i5);
                if ((childAt instanceof InterfaceC0222n) || (childAt instanceof AbsListView) || (childAt instanceof ScrollView)) {
                    return childAt;
                }
            }
            return null;
        }

        private static View getAppBarChildOnOffset(AppBarLayout appBarLayout, int i5) {
            int iAbs = Math.abs(i5);
            int childCount = appBarLayout.getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = appBarLayout.getChildAt(i7);
                if (iAbs >= childAt.getTop() && iAbs <= childAt.getBottom()) {
                    return childAt;
                }
            }
            return null;
        }

        private int getChildIndexOnOffset(T t8, int i5) {
            int paddingBottom = i5 + (t8.isLifted() ? t8.getPaddingBottom() : 0);
            int childCount = t8.getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = t8.getChildAt(i7);
                int top = childAt.getTop();
                int bottom = childAt.getBottom();
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (checkFlag(layoutParams.getScrollFlags(), 32)) {
                    top -= ((LinearLayout.LayoutParams) layoutParams).topMargin;
                    bottom += ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
                }
                if (t8.seslGetAdditionalScrollRange() != 0) {
                    bottom += t8.seslGetAdditionalScrollRange();
                }
                int i9 = -paddingBottom;
                if (top <= i9 && bottom >= i9) {
                    return i7;
                }
            }
            return -1;
        }

        private View getChildWithScrollingBehavior(CoordinatorLayout coordinatorLayout) {
            int childCount = coordinatorLayout.getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = coordinatorLayout.getChildAt(i5);
                if (((g) childAt.getLayoutParams()).f7154a instanceof ScrollingViewBehavior) {
                    return childAt;
                }
            }
            return null;
        }

        private int getImmPendingActionOffset(AppBarLayout appBarLayout) {
            Behavior behavior = (Behavior) ((g) appBarLayout.getLayoutParams()).f7154a;
            if (!appBarLayout.getCanScroll() || !(behavior instanceof SeslImmersiveScrollBehavior)) {
                return 0;
            }
            return appBarLayout.seslGetAdditionalScrollRange() + ((int) appBarLayout.seslGetCollapsedHeight());
        }

        private boolean isScrollHoldMode(T t8) {
            if (this.mToolisMouse) {
                return false;
            }
            int childIndexOnOffset = getChildIndexOnOffset(t8, getTopBottomOffsetForScrollingSibling());
            return childIndexOnOffset < 0 || (((LayoutParams) t8.getChildAt(childIndexOnOffset).getLayoutParams()).getScrollFlags() & 65536) != 65536;
        }

        private boolean shouldJumpElevationState(CoordinatorLayout coordinatorLayout, T t8) {
            List<View> dependents = coordinatorLayout.getDependents(t8);
            int size = dependents.size();
            for (int i5 = 0; i5 < size; i5++) {
                d dVar = ((g) dependents.get(i5).getLayoutParams()).f7154a;
                if (dVar instanceof ScrollingViewBehavior) {
                    return ((ScrollingViewBehavior) dVar).getOverlayTop() != 0;
                }
            }
            return false;
        }

        private void snapToChildIfNeeded(CoordinatorLayout coordinatorLayout, T t8) {
            int topBottomOffsetForScrollingSibling = getTopBottomOffsetForScrollingSibling() - (t8.getPaddingTop() + t8.getTopInset());
            int childIndexOnOffset = getChildIndexOnOffset(t8, topBottomOffsetForScrollingSibling);
            View childAt = coordinatorLayout.getChildAt(1);
            if (childIndexOnOffset >= 0) {
                View childAt2 = t8.getChildAt(childIndexOnOffset);
                LayoutParams layoutParams = (LayoutParams) childAt2.getLayoutParams();
                int scrollFlags = layoutParams.getScrollFlags();
                if ((scrollFlags & 4096) == 4096) {
                    seslHasNoSnapFlag(true);
                    return;
                }
                seslHasNoSnapFlag(false);
                int iSeslGetAdditionalScrollRange = t8.getCanScroll() ? t8.seslGetAdditionalScrollRange() : 0;
                if (t8.getBottom() < t8.seslGetCollapsedHeight()) {
                    if (t8.getCanScroll()) {
                        int iSeslGetCollapsedHeight = (((int) t8.seslGetCollapsedHeight()) - t8.getTotalScrollRange()) + iSeslGetAdditionalScrollRange;
                        int i5 = -t8.getTotalScrollRange();
                        int i7 = ((double) (t8.getBottom() + iSeslGetAdditionalScrollRange)) >= ((double) t8.seslGetCollapsedHeight()) * 0.48d ? iSeslGetCollapsedHeight : i5;
                        if (!this.mIsFlingScrollUp) {
                            i5 = i7;
                        }
                        if (!this.mIsFlingScrollDown) {
                            iSeslGetCollapsedHeight = i5;
                        }
                        animateOffsetTo(coordinatorLayout, t8, com.bumptech.glide.c.d(iSeslGetCollapsedHeight, -t8.getTotalScrollRange(), 0), 0.0f);
                        return;
                    }
                    return;
                }
                int topInset = -childAt2.getTop();
                int minimumHeight = -childAt2.getBottom();
                if (childIndexOnOffset == 0) {
                    WeakHashMap weakHashMap = W.f7199a;
                    if (t8.getFitsSystemWindows() && childAt2.getFitsSystemWindows()) {
                        topInset -= t8.getTopInset();
                    }
                }
                if (checkFlag(scrollFlags, 2)) {
                    if (t8.getCanScroll()) {
                        minimumHeight = (int) ((t8.seslGetCollapsedHeight() - t8.getPaddingBottom()) + minimumHeight);
                    } else {
                        WeakHashMap weakHashMap2 = W.f7199a;
                        minimumHeight += childAt2.getMinimumHeight();
                    }
                } else if (checkFlag(scrollFlags, 5)) {
                    WeakHashMap weakHashMap3 = W.f7199a;
                    int minimumHeight2 = childAt2.getMinimumHeight() + minimumHeight;
                    if (topBottomOffsetForScrollingSibling < minimumHeight2) {
                        topInset = minimumHeight2;
                    } else {
                        minimumHeight = minimumHeight2;
                    }
                }
                if (checkFlag(scrollFlags, 32)) {
                    topInset += ((LinearLayout.LayoutParams) layoutParams).topMargin;
                    minimumHeight -= ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
                }
                int i9 = (!this.mLifted ? ((double) topBottomOffsetForScrollingSibling) < ((double) (minimumHeight + topInset)) * 0.43d : ((double) topBottomOffsetForScrollingSibling) < ((double) (minimumHeight + topInset)) * 0.52d) ? topInset : minimumHeight;
                if (childAt == null) {
                    Log.w(AppBarLayout.TAG, "coordinatorLayout.getChildAt(1) is null");
                    topInset = i9;
                } else {
                    if (this.mIsFlingScrollUp) {
                        this.mIsFlingScrollUp = false;
                        this.mIsFlingScrollDown = false;
                    } else {
                        minimumHeight = i9;
                    }
                    if (!this.mIsFlingScrollDown || childAt.getTop() <= t8.seslGetCollapsedHeight()) {
                        topInset = minimumHeight;
                    } else {
                        this.mIsFlingScrollDown = false;
                    }
                }
                animateOffsetTo(coordinatorLayout, t8, com.bumptech.glide.c.d(topInset, -t8.getTotalScrollRange(), 0), 0.0f);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void stopNestedScrollIfNeeded(int i5, T t8, View view, int i7) {
            if (i7 == 1) {
                int topBottomOffsetForScrollingSibling = getTopBottomOffsetForScrollingSibling();
                if ((i5 >= 0 || topBottomOffsetForScrollingSibling != 0) && (i5 <= 0 || topBottomOffsetForScrollingSibling != (-t8.getDownNestedScrollRange()))) {
                    return;
                }
                WeakHashMap weakHashMap = W.f7199a;
                if (view instanceof InterfaceC0222n) {
                    ((InterfaceC0222n) view).stopNestedScroll(1);
                }
            }
        }

        private void updateAccessibilityActions(CoordinatorLayout coordinatorLayout, T t8) {
            View viewFindFirstScrollingChild;
            W.g(L.f.f1780f.a(), coordinatorLayout);
            W.e(0, coordinatorLayout);
            W.g(L.f.f1781g.a(), coordinatorLayout);
            W.e(0, coordinatorLayout);
            if (t8.getTotalScrollRange() == 0 || (viewFindFirstScrollingChild = findFirstScrollingChild(coordinatorLayout)) == null || !childrenHaveScrollFlags(t8)) {
                return;
            }
            if (T.a(coordinatorLayout) == null) {
                W.i(coordinatorLayout, new C0210b() { // from class: com.google.android.material.appbar.AppBarLayout.BaseBehavior.2
                    @Override // androidx.core.view.C0210b
                    public void onInitializeAccessibilityNodeInfo(View view, l lVar) {
                        super.onInitializeAccessibilityNodeInfo(view, lVar);
                        lVar.n(BaseBehavior.this.coordinatorLayoutA11yScrollable);
                        lVar.j(ScrollView.class.getName());
                    }
                });
            }
            this.coordinatorLayoutA11yScrollable = addAccessibilityScrollActions(coordinatorLayout, t8, viewFindFirstScrollingChild);
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public int getTopBottomOffsetForScrollingSibling() {
            return getTopAndBottomOffset() + this.offsetDelta;
        }

        public int interpolateOffset(T t8, int i5) {
            int iAbs = Math.abs(i5);
            int childCount = t8.getChildCount();
            int topInset = 0;
            int i7 = 0;
            while (true) {
                if (i7 >= childCount) {
                    break;
                }
                View childAt = t8.getChildAt(i7);
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                Interpolator scrollInterpolator = layoutParams.getScrollInterpolator();
                if (iAbs < childAt.getTop() || iAbs > childAt.getBottom()) {
                    i7++;
                } else if (scrollInterpolator != null) {
                    int scrollFlags = layoutParams.getScrollFlags();
                    if ((scrollFlags & 1) != 0) {
                        topInset = childAt.getHeight() + ((LinearLayout.LayoutParams) layoutParams).topMargin + ((LinearLayout.LayoutParams) layoutParams).bottomMargin;
                        if ((scrollFlags & 2) != 0) {
                            WeakHashMap weakHashMap = W.f7199a;
                            topInset -= childAt.getMinimumHeight();
                        }
                    }
                    WeakHashMap weakHashMap2 = W.f7199a;
                    if (childAt.getFitsSystemWindows()) {
                        topInset -= t8.getTopInset();
                    }
                    if (topInset > 0) {
                        float f2 = topInset;
                        return (childAt.getTop() + Math.round(scrollInterpolator.getInterpolation((iAbs - childAt.getTop()) / f2) * f2)) * Integer.signum(i5);
                    }
                }
            }
            return i5;
        }

        public boolean isOffsetAnimatorRunning() {
            ValueAnimator valueAnimator = this.offsetAnimator;
            return valueAnimator != null && valueAnimator.isRunning();
        }

        public void restoreScrollState(SavedState savedState, boolean z9) {
            if (this.savedState == null || z9) {
                this.savedState = savedState;
            }
        }

        public SavedState saveScrollState(Parcelable parcelable, T t8) {
            int topAndBottomOffset = getTopAndBottomOffset();
            int childCount = t8.getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = t8.getChildAt(i5);
                int bottom = childAt.getBottom() + topAndBottomOffset;
                if (childAt.getTop() + topAndBottomOffset <= 0 && bottom >= 0) {
                    if (parcelable == null) {
                        parcelable = R.c.EMPTY_STATE;
                    }
                    SavedState savedState = new SavedState(parcelable);
                    boolean z9 = topAndBottomOffset == 0;
                    savedState.fullyExpanded = z9;
                    savedState.fullyScrolled = !z9 && (-topAndBottomOffset) >= t8.getTotalScrollRange();
                    savedState.firstVisibleChildIndex = i5;
                    WeakHashMap weakHashMap = W.f7199a;
                    savedState.firstVisibleChildAtMinimumHeight = bottom == t8.getTopInset() + childAt.getMinimumHeight();
                    savedState.firstVisibleChildPercentageShown = bottom / childAt.getHeight();
                    return savedState;
                }
            }
            return null;
        }

        public void setDragCallback(BaseDragCallback baseDragCallback) {
            this.onDragCallback = baseDragCallback;
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0035  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void updateAppBarLayoutDrawableState(androidx.coordinatorlayout.widget.CoordinatorLayout r6, T r7, int r8, int r9, boolean r10) {
            /*
                r5 = this;
                r0 = 1
                android.view.View r1 = getAppBarChildOnOffset(r7, r8)
                r2 = 0
                if (r1 == 0) goto L4f
                android.view.ViewGroup$LayoutParams r3 = r1.getLayoutParams()
                com.google.android.material.appbar.AppBarLayout$LayoutParams r3 = (com.google.android.material.appbar.AppBarLayout.LayoutParams) r3
                int r3 = r3.getScrollFlags()
                r4 = r3 & 1
                if (r4 == 0) goto L4f
                java.util.WeakHashMap r4 = androidx.core.view.W.f7199a
                int r4 = r1.getMinimumHeight()
                if (r9 <= 0) goto L38
                r9 = r3 & 12
                if (r9 == 0) goto L38
                int r8 = -r8
                int r9 = r1.getBottom()
                int r9 = r9 - r4
                int r1 = r7.getTopInset()
                int r9 = r9 - r1
                int r1 = r7.getImmersiveTopInset()
                int r9 = r9 - r1
                if (r8 < r9) goto L35
                goto L36
            L35:
                r0 = r2
            L36:
                r2 = r0
                goto L4f
            L38:
                r9 = r3 & 2
                if (r9 == 0) goto L4f
                int r8 = -r8
                int r9 = r1.getBottom()
                int r9 = r9 - r4
                int r1 = r7.getTopInset()
                int r9 = r9 - r1
                int r1 = r7.getImmersiveTopInset()
                int r9 = r9 - r1
                if (r8 < r9) goto L35
                goto L36
            L4f:
                boolean r8 = r7.isLiftOnScroll()
                if (r8 == 0) goto L5d
                android.view.View r8 = r5.findFirstScrollingChild(r6)
                boolean r2 = r7.shouldLift(r8)
            L5d:
                boolean r8 = r7.setLiftedState(r2)
                if (r10 != 0) goto L6b
                if (r8 == 0) goto L92
                boolean r5 = r5.shouldJumpElevationState(r6, r7)
                if (r5 == 0) goto L92
            L6b:
                android.graphics.drawable.Drawable r5 = r7.getBackground()
                if (r5 == 0) goto L78
                android.graphics.drawable.Drawable r5 = r7.getBackground()
                r5.jumpToCurrentState()
            L78:
                android.graphics.drawable.Drawable r5 = r7.getForeground()
                if (r5 == 0) goto L85
                android.graphics.drawable.Drawable r5 = r7.getForeground()
                r5.jumpToCurrentState()
            L85:
                android.animation.StateListAnimator r5 = r7.getStateListAnimator()
                if (r5 == 0) goto L92
                android.animation.StateListAnimator r5 = r7.getStateListAnimator()
                r5.jumpToCurrentState()
            L92:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.updateAppBarLayoutDrawableState(androidx.coordinatorlayout.widget.CoordinatorLayout, com.google.android.material.appbar.AppBarLayout, int, int, boolean):void");
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public boolean canDragView(T t8) {
            BaseDragCallback baseDragCallback = this.onDragCallback;
            if (baseDragCallback != null) {
                return baseDragCallback.canDrag(t8);
            }
            WeakReference<View> weakReference = this.lastNestedScrollingChildRef;
            if (weakReference == null) {
                return true;
            }
            View view = weakReference.get();
            return (view == null || !view.isShown() || view.canScrollVertically(-1)) ? false : true;
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public int getMaxDragOffset(T t8) {
            return t8.getTopInset() + (-t8.getDownNestedScrollRange());
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public int getScrollRangeForDragFling(T t8) {
            return t8.getTotalScrollRange();
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public void onFlingFinished(CoordinatorLayout coordinatorLayout, T t8) {
            OverScroller overScroller = this.scroller;
            if (overScroller != null) {
                overScroller.forceFinished(true);
            }
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior, androidx.coordinatorlayout.widget.d
        public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, T t8, int i5) {
            int iRound;
            boolean zOnLayoutChild = super.onLayoutChild(coordinatorLayout, t8, i5);
            int pendingAction = t8.getPendingAction();
            SavedState savedState = this.savedState;
            if (savedState == null || (pendingAction & 8) != 0) {
                if (pendingAction != 0) {
                    boolean z9 = (pendingAction & 4) != 0;
                    if ((pendingAction & 2) != 0) {
                        float immPendingActionOffset = ((-t8.getTotalScrollRange()) + getImmPendingActionOffset(t8)) - t8.getImmersiveTopInset();
                        if (z9) {
                            animateOffsetTo(coordinatorLayout, t8, (int) immPendingActionOffset, 0.0f);
                        } else {
                            setHeaderTopBottomOffset(coordinatorLayout, t8, (int) immPendingActionOffset);
                        }
                    } else if ((pendingAction & 512) != 0) {
                        float immPendingActionOffset2 = (-t8.getTotalScrollRange()) + getImmPendingActionOffset(t8);
                        if (coordinatorLayout.getContext().getResources().getConfiguration().orientation == 1 && t8.getImmersiveTopInset() == 0 && t8.seslGetHeightProPortion() == 0.0f) {
                            immPendingActionOffset2 = 0.0f;
                        }
                        if (z9) {
                            animateOffsetTo(coordinatorLayout, t8, (int) immPendingActionOffset2, 0.0f);
                        } else {
                            setHeaderTopBottomOffset(coordinatorLayout, t8, (int) immPendingActionOffset2);
                        }
                    } else if ((pendingAction & 1) != 0) {
                        if (z9) {
                            animateOffsetTo(coordinatorLayout, t8, 0, 0.0f);
                        } else {
                            setHeaderTopBottomOffset(coordinatorLayout, t8, 0);
                        }
                    }
                }
            } else if (savedState.fullyScrolled) {
                setHeaderTopBottomOffset(coordinatorLayout, t8, -t8.getTotalScrollRange());
            } else if (savedState.fullyExpanded) {
                setHeaderTopBottomOffset(coordinatorLayout, t8, 0);
            } else {
                View childAt = t8.getChildAt(savedState.firstVisibleChildIndex);
                int i7 = -childAt.getBottom();
                if (this.savedState.firstVisibleChildAtMinimumHeight) {
                    WeakHashMap weakHashMap = W.f7199a;
                    iRound = t8.getTopInset() + childAt.getMinimumHeight() + i7;
                } else {
                    iRound = Math.round(childAt.getHeight() * this.savedState.firstVisibleChildPercentageShown) + i7;
                }
                setHeaderTopBottomOffset(coordinatorLayout, t8, iRound);
            }
            t8.resetPendingAction();
            this.savedState = null;
            setTopAndBottomOffset(com.bumptech.glide.c.d(getTopAndBottomOffset(), -t8.getTotalScrollRange(), 0));
            updateAppBarLayoutDrawableState(coordinatorLayout, t8, getTopAndBottomOffset(), 0, false);
            t8.onOffsetChanged(getTopAndBottomOffset());
            updateAccessibilityActions(coordinatorLayout, t8);
            return zOnLayoutChild;
        }

        @Override // androidx.coordinatorlayout.widget.d
        public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, T t8, int i5, int i7, int i9, int i10) {
            if (((ViewGroup.MarginLayoutParams) ((g) t8.getLayoutParams())).height != -2) {
                return super.onMeasureChild(coordinatorLayout, (View) t8, i5, i7, i9, i10);
            }
            coordinatorLayout.onMeasureChild(t8, i5, i7, View.MeasureSpec.makeMeasureSpec(0, 0), i10);
            return true;
        }

        @Override // androidx.coordinatorlayout.widget.d
        public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, T t8, View view, float f2, float f7) {
            this.mVelocity = f7;
            if (f7 < -300.0f) {
                this.mIsFlingScrollDown = true;
                this.mIsFlingScrollUp = false;
            } else {
                if (f7 <= 300.0f) {
                    this.mVelocity = 0.0f;
                    this.mIsFlingScrollDown = false;
                    this.mIsFlingScrollUp = false;
                    return true;
                }
                this.mIsFlingScrollDown = false;
                this.mIsFlingScrollUp = true;
            }
            return super.onNestedPreFling(coordinatorLayout, (View) t8, view, f2, f7);
        }

        @Override // androidx.coordinatorlayout.widget.d
        public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, T t8, View view, int i5, int i7, int[] iArr, int i9) {
            int i10;
            int i11;
            if (i7 != 0) {
                if (i7 < 0) {
                    int i12 = -t8.getTotalScrollRange();
                    int downNestedPreScrollRange = t8.getDownNestedPreScrollRange() + i12;
                    this.mIsFlingScrollDown = true;
                    this.mIsFlingScrollUp = false;
                    if (t8.getBottom() >= ((double) t8.getHeight()) * 0.52d) {
                        this.mIsSetStaticDuration = true;
                    }
                    if (i7 < -30) {
                        this.mIsFlingScrollDown = true;
                    } else {
                        this.mVelocity = 0.0f;
                        this.mIsFlingScrollDown = false;
                    }
                    i11 = i12;
                    i10 = downNestedPreScrollRange;
                } else {
                    int i13 = -t8.getUpNestedPreScrollRange();
                    this.mIsFlingScrollDown = false;
                    this.mIsFlingScrollUp = true;
                    if (t8.getBottom() <= ((double) t8.getHeight()) * 0.43d) {
                        this.mIsSetStaticDuration = true;
                    }
                    if (i7 > 30) {
                        this.mIsFlingScrollUp = true;
                    } else {
                        this.mVelocity = 0.0f;
                        this.mIsFlingScrollUp = false;
                    }
                    if (getTopAndBottomOffset() == i13) {
                        this.mIsScrollHold = true;
                    }
                    i10 = 0;
                    i11 = i13;
                }
                if (isFlingRunnable()) {
                    onFlingFinished(coordinatorLayout, (AppBarLayout) t8);
                }
                if (i11 != i10) {
                    iArr[1] = scroll(coordinatorLayout, t8, i7, i11, i10);
                }
            }
            if (t8.isLiftOnScroll()) {
                t8.setLiftedState(t8.shouldLift(view));
            }
            stopNestedScrollIfNeeded(i7, t8, view, i9);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.coordinatorlayout.widget.d
        public void onNestedScroll(CoordinatorLayout coordinatorLayout, T t8, View view, int i5, int i7, int i9, int i10, int i11, int[] iArr) {
            if (isScrollHoldMode(t8)) {
                if (i10 >= 0 || this.mIsScrollHold) {
                    WeakHashMap weakHashMap = W.f7199a;
                    if (view instanceof InterfaceC0222n) {
                        ((InterfaceC0222n) view).stopNestedScroll(1);
                    }
                } else {
                    iArr[1] = scroll(coordinatorLayout, t8, i10, -t8.getDownNestedScrollRange(), 0);
                    stopNestedScrollIfNeeded(i10, t8, view, i11);
                }
            } else if (i10 < 0) {
                iArr[1] = scroll(coordinatorLayout, t8, i10, -t8.getDownNestedScrollRange(), 0);
                stopNestedScrollIfNeeded(i10, t8, view, i11);
            }
            if (i10 == 0) {
                updateAccessibilityActions(coordinatorLayout, t8);
            }
        }

        @Override // androidx.coordinatorlayout.widget.d
        public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, T t8, Parcelable parcelable) {
            if (parcelable instanceof SavedState) {
                restoreScrollState((SavedState) parcelable, true);
                super.onRestoreInstanceState(coordinatorLayout, (View) t8, this.savedState.getSuperState());
            } else {
                super.onRestoreInstanceState(coordinatorLayout, (View) t8, parcelable);
                this.savedState = null;
            }
        }

        @Override // androidx.coordinatorlayout.widget.d
        public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, T t8) {
            Parcelable parcelableOnSaveInstanceState = super.onSaveInstanceState(coordinatorLayout, (View) t8);
            SavedState savedStateSaveScrollState = saveScrollState(parcelableOnSaveInstanceState, t8);
            return savedStateSaveScrollState == null ? parcelableOnSaveInstanceState : savedStateSaveScrollState;
        }

        @Override // androidx.coordinatorlayout.widget.d
        public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, T t8, View view, View view2, int i5, int i7) {
            ValueAnimator valueAnimator;
            boolean z9 = (i5 & 2) != 0 && (t8.isLiftOnScroll() || canScrollChildren(coordinatorLayout, t8, view));
            if (z9 && (valueAnimator = this.offsetAnimator) != null) {
                valueAnimator.cancel();
            }
            if (t8.getBottom() <= t8.seslGetCollapsedHeight()) {
                this.mLifted = true;
                t8.setLifted(true);
                this.mDiffY_Touch = 0.0f;
            } else {
                this.mLifted = false;
                t8.setLifted(false);
            }
            t8.updateInternalCollapsedHeight();
            this.lastNestedScrollingChildRef = null;
            this.lastStartedType = i7;
            this.mToolisMouse = t8.getIsMouse();
            return z9;
        }

        @Override // androidx.coordinatorlayout.widget.d
        public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, T t8, View view, int i5) {
            int i7;
            int i9 = this.mLastTouchEvent;
            if (i9 == 3 || i9 == 1 || (i7 = this.mLastInterceptTouchEvent) == 3 || i7 == 1) {
                snapToChildIfNeeded(coordinatorLayout, t8);
            }
            if (this.lastStartedType == 0 || i5 == 1) {
                if (t8.isLiftOnScroll()) {
                    t8.setLiftedState(t8.shouldLift(view));
                }
                if (this.mIsScrollHold) {
                    this.mIsScrollHold = false;
                }
            }
            this.lastNestedScrollingChildRef = new WeakReference<>(view);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x004b  */
        @Override // com.google.android.material.appbar.HeaderBehavior, androidx.coordinatorlayout.widget.d
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean onTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout r6, T r7, android.view.MotionEvent r8) {
            /*
                r5 = this;
                int r0 = r5.mTouchSlop
                if (r0 >= 0) goto L12
                android.content.Context r0 = r6.getContext()
                android.view.ViewConfiguration r0 = android.view.ViewConfiguration.get(r0)
                int r0 = r0.getScaledTouchSlop()
                r5.mTouchSlop = r0
            L12:
                int r0 = r8.getAction()
                boolean r1 = r7.getIsMouse()
                r5.mToolisMouse = r1
                r1 = 1
                r2 = 0
                if (r0 == 0) goto L80
                if (r0 == r1) goto L4b
                r3 = 2
                if (r0 == r3) goto L29
                r3 = 3
                if (r0 == r3) goto L4b
                goto L92
            L29:
                r5.mDirectTouchAppbar = r1
                float r0 = r8.getY()
                float r1 = r5.mLastMotionY_Touch
                float r3 = r0 - r1
                int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
                if (r2 == 0) goto L3b
                float r1 = r0 - r1
                r5.mDiffY_Touch = r1
            L3b:
                float r1 = r5.mDiffY_Touch
                float r1 = java.lang.Math.abs(r1)
                int r2 = r5.mTouchSlop
                float r2 = (float) r2
                int r1 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
                if (r1 <= 0) goto L92
                r5.mLastMotionY_Touch = r0
                goto L92
            L4b:
                float r0 = r5.mDiffY_Touch
                float r0 = java.lang.Math.abs(r0)
                r3 = 1101529088(0x41a80000, float:21.0)
                int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
                r3 = 0
                if (r0 <= 0) goto L6c
                float r0 = r5.mDiffY_Touch
                int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r4 >= 0) goto L63
                r5.mIsFlingScrollUp = r1
                r5.mIsFlingScrollDown = r3
                goto L76
            L63:
                int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                if (r0 <= 0) goto L76
                r5.mIsFlingScrollUp = r3
                r5.mIsFlingScrollDown = r1
                goto L76
            L6c:
                r5.touchX = r2
                r5.touchY = r2
                r5.mIsFlingScrollUp = r3
                r5.mIsFlingScrollDown = r3
                r5.mLastMotionY_Touch = r2
            L76:
                boolean r0 = r5.mDirectTouchAppbar
                if (r0 == 0) goto L92
                r5.mDirectTouchAppbar = r3
                r5.snapToChildIfNeeded(r6, r7)
                goto L92
            L80:
                r5.mDirectTouchAppbar = r1
                float r0 = r8.getX()
                r5.touchX = r0
                float r0 = r8.getY()
                r5.touchY = r0
                r5.mLastMotionY_Touch = r0
                r5.mDiffY_Touch = r2
            L92:
                boolean r5 = super.onTouchEvent(r6, r7, r8)
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.BaseBehavior.onTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout, com.google.android.material.appbar.AppBarLayout, android.view.MotionEvent):boolean");
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public int setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout, T t8, int i5, int i7, int i9) {
            int topBottomOffsetForScrollingSibling = getTopBottomOffsetForScrollingSibling();
            int i10 = 0;
            if (i7 == 0 || topBottomOffsetForScrollingSibling < i7 || topBottomOffsetForScrollingSibling > i9) {
                this.offsetDelta = 0;
            } else {
                int iD = com.bumptech.glide.c.d(i5, i7, i9);
                if (topBottomOffsetForScrollingSibling != iD) {
                    int iInterpolateOffset = t8.hasChildWithInterpolator() ? interpolateOffset(t8, iD) : iD;
                    boolean topAndBottomOffset = setTopAndBottomOffset(iInterpolateOffset);
                    int i11 = topBottomOffsetForScrollingSibling - iD;
                    this.offsetDelta = iD - iInterpolateOffset;
                    if (topAndBottomOffset) {
                        while (i10 < t8.getChildCount()) {
                            LayoutParams layoutParams = (LayoutParams) t8.getChildAt(i10).getLayoutParams();
                            ChildScrollEffect scrollEffect = layoutParams.getScrollEffect();
                            if (scrollEffect != null && (layoutParams.getScrollFlags() & 1) != 0) {
                                scrollEffect.onOffsetChanged(t8, t8.getChildAt(i10), getTopAndBottomOffset());
                            }
                            i10++;
                        }
                    }
                    if (!topAndBottomOffset && t8.hasChildWithInterpolator()) {
                        coordinatorLayout.dispatchDependentViewsChanged(t8);
                    }
                    t8.onOffsetChanged(getTopAndBottomOffset());
                    updateAppBarLayoutDrawableState(coordinatorLayout, t8, iD, iD < topBottomOffsetForScrollingSibling ? -1 : 1, false);
                    i10 = i11;
                }
            }
            updateAccessibilityActions(coordinatorLayout, t8);
            return i10;
        }

        public static class SavedState extends R.c {
            public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.appbar.AppBarLayout.BaseBehavior.SavedState.1
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
            boolean firstVisibleChildAtMinimumHeight;
            int firstVisibleChildIndex;
            float firstVisibleChildPercentageShown;
            boolean fullyExpanded;
            boolean fullyScrolled;

            public SavedState(Parcel parcel, ClassLoader classLoader) {
                super(parcel, classLoader);
                this.fullyScrolled = parcel.readByte() != 0;
                this.fullyExpanded = parcel.readByte() != 0;
                this.firstVisibleChildIndex = parcel.readInt();
                this.firstVisibleChildPercentageShown = parcel.readFloat();
                this.firstVisibleChildAtMinimumHeight = parcel.readByte() != 0;
            }

            @Override // R.c, android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i5) {
                super.writeToParcel(parcel, i5);
                parcel.writeByte(this.fullyScrolled ? (byte) 1 : (byte) 0);
                parcel.writeByte(this.fullyExpanded ? (byte) 1 : (byte) 0);
                parcel.writeInt(this.firstVisibleChildIndex);
                parcel.writeFloat(this.firstVisibleChildPercentageShown);
                parcel.writeByte(this.firstVisibleChildAtMinimumHeight ? (byte) 1 : (byte) 0);
            }

            public SavedState(Parcelable parcelable) {
                super(parcelable);
            }
        }

        public BaseBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.mIsFlingScrollDown = false;
            this.mIsFlingScrollUp = false;
            this.mDirectTouchAppbar = false;
            this.mTouchSlop = -1;
            this.mVelocity = 0.0f;
            this.mIsSetStaticDuration = false;
            this.mIsScrollHold = false;
        }
    }

    public interface BaseOnOffsetChangedListener<T extends AppBarLayout> {
        void onOffsetChanged(T t8, int i5);
    }

    public static class Behavior extends BaseBehavior<AppBarLayout> {

        public static abstract class DragCallback extends BaseBehavior.BaseDragCallback<AppBarLayout> {
        }

        public Behavior() {
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public /* bridge */ /* synthetic */ int getLastInterceptTouchEventEvent() {
            return super.getLastInterceptTouchEventEvent();
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public /* bridge */ /* synthetic */ int getLastTouchEventEvent() {
            return super.getLastTouchEventEvent();
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ int getLeftAndRightOffset() {
            return super.getLeftAndRightOffset();
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ int getTopAndBottomOffset() {
            return super.getTopAndBottomOffset();
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ boolean isHorizontalOffsetEnabled() {
            return super.isHorizontalOffsetEnabled();
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ boolean isVerticalOffsetEnabled() {
            return super.isVerticalOffsetEnabled();
        }

        @Override // com.google.android.material.appbar.HeaderBehavior, androidx.coordinatorlayout.widget.d
        public /* bridge */ /* synthetic */ boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            return super.onInterceptTouchEvent(coordinatorLayout, view, motionEvent);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i5) {
            return super.onLayoutChild(coordinatorLayout, appBarLayout, i5);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ boolean onMeasureChild(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i5, int i7, int i9, int i10) {
            return super.onMeasureChild(coordinatorLayout, appBarLayout, i5, i7, i9, i10);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, float f2, float f7) {
            return super.onNestedPreFling(coordinatorLayout, appBarLayout, view, f2, f7);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i5, int i7, int[] iArr, int i9) {
            super.onNestedPreScroll(coordinatorLayout, appBarLayout, view, i5, i7, iArr, i9);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i5, int i7, int i9, int i10, int i11, int[] iArr) {
            super.onNestedScroll(coordinatorLayout, appBarLayout, view, i5, i7, i9, i10, i11, iArr);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, Parcelable parcelable) {
            super.onRestoreInstanceState(coordinatorLayout, appBarLayout, parcelable);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
            return super.onSaveInstanceState(coordinatorLayout, appBarLayout);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i5, int i7) {
            return super.onStartNestedScroll(coordinatorLayout, appBarLayout, view, view2, i5, i7);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i5) {
            super.onStopNestedScroll(coordinatorLayout, appBarLayout, view, i5);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ boolean onTouchEvent(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, MotionEvent motionEvent) {
            return super.onTouchEvent(coordinatorLayout, appBarLayout, motionEvent);
        }

        @Override // com.google.android.material.appbar.HeaderBehavior
        public /* bridge */ /* synthetic */ void seslHasNoSnapFlag(boolean z9) {
            super.seslHasNoSnapFlag(z9);
        }

        @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior
        public /* bridge */ /* synthetic */ void setDragCallback(BaseBehavior.BaseDragCallback baseDragCallback) {
            super.setDragCallback(baseDragCallback);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ void setHorizontalOffsetEnabled(boolean z9) {
            super.setHorizontalOffsetEnabled(z9);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ boolean setLeftAndRightOffset(int i5) {
            return super.setLeftAndRightOffset(i5);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ boolean setTopAndBottomOffset(int i5) {
            return super.setTopAndBottomOffset(i5);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ void setVerticalOffsetEnabled(boolean z9) {
            super.setVerticalOffsetEnabled(z9);
        }

        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }
    }

    public static abstract class ChildScrollEffect {
        public abstract void onOffsetChanged(AppBarLayout appBarLayout, View view, float f2);
    }

    public static class CompressChildScrollEffect extends ChildScrollEffect {
        private static final float COMPRESS_DISTANCE_FACTOR = 0.3f;
        private final Rect relativeRect = new Rect();
        private final Rect ghostRect = new Rect();

        private static void updateRelativeRect(Rect rect, AppBarLayout appBarLayout, View view) {
            view.getDrawingRect(rect);
            appBarLayout.offsetDescendantRectToMyCoords(view, rect);
            rect.offset(0, -appBarLayout.getTopInset());
        }

        @Override // com.google.android.material.appbar.AppBarLayout.ChildScrollEffect
        public void onOffsetChanged(AppBarLayout appBarLayout, View view, float f2) {
            updateRelativeRect(this.relativeRect, appBarLayout, view);
            float fAbs = this.relativeRect.top - Math.abs(f2);
            if (fAbs > 0.0f) {
                WeakHashMap weakHashMap = W.f7199a;
                view.setClipBounds(null);
                view.setTranslationY(0.0f);
                return;
            }
            float fC = 1.0f - com.bumptech.glide.c.c(Math.abs(fAbs / this.relativeRect.height()), 0.0f, 1.0f);
            float fHeight = (-fAbs) - ((this.relativeRect.height() * 0.3f) * (1.0f - (fC * fC)));
            view.setTranslationY(fHeight);
            view.getDrawingRect(this.ghostRect);
            this.ghostRect.offset(0, (int) (-fHeight));
            Rect rect = this.ghostRect;
            WeakHashMap weakHashMap2 = W.f7199a;
            view.setClipBounds(rect);
        }
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        static final int COLLAPSIBLE_FLAGS = 10;
        private static final int FLAG_NO_SCROLL_HOLD = 65536;
        private static final int FLAG_NO_SNAP = 4096;
        static final int FLAG_QUICK_RETURN = 5;
        static final int FLAG_SNAP = 17;
        public static final int SCROLL_EFFECT_COMPRESS = 1;
        public static final int SCROLL_EFFECT_NONE = 0;
        public static final int SCROLL_FLAG_ENTER_ALWAYS = 4;
        public static final int SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED = 8;
        public static final int SCROLL_FLAG_EXIT_UNTIL_COLLAPSED = 2;
        public static final int SCROLL_FLAG_NO_SCROLL = 0;
        public static final int SCROLL_FLAG_SCROLL = 1;
        public static final int SCROLL_FLAG_SNAP = 16;
        public static final int SCROLL_FLAG_SNAP_MARGINS = 32;
        public static final int SESL_SCROLL_FLAG_NO_SCROLL_HOLD = 65536;
        public static final int SESL_SCROLL_FLAG_NO_SNAP = 4096;
        private ChildScrollEffect scrollEffect;
        int scrollFlags;
        Interpolator scrollInterpolator;

        @Retention(RetentionPolicy.SOURCE)
        public @interface ScrollEffect {
        }

        @Retention(RetentionPolicy.SOURCE)
        public @interface ScrollFlags {
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.scrollFlags = 1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AppBarLayout_Layout);
            this.scrollFlags = typedArrayObtainStyledAttributes.getInt(R.styleable.AppBarLayout_Layout_layout_scrollFlags, 0);
            setScrollEffect(typedArrayObtainStyledAttributes.getInt(R.styleable.AppBarLayout_Layout_layout_scrollEffect, 0));
            int i5 = R.styleable.AppBarLayout_Layout_layout_scrollInterpolator;
            if (typedArrayObtainStyledAttributes.hasValue(i5)) {
                this.scrollInterpolator = AnimationUtils.loadInterpolator(context, typedArrayObtainStyledAttributes.getResourceId(i5, 0));
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        private ChildScrollEffect createScrollEffectFromInt(int i5) {
            if (i5 != 1) {
                return null;
            }
            return new CompressChildScrollEffect();
        }

        public ChildScrollEffect getScrollEffect() {
            return this.scrollEffect;
        }

        public int getScrollFlags() {
            return this.scrollFlags;
        }

        public Interpolator getScrollInterpolator() {
            return this.scrollInterpolator;
        }

        public boolean isCollapsible() {
            int i5 = this.scrollFlags;
            return (i5 & 1) == 1 && (i5 & 10) != 0;
        }

        public void setScrollEffect(ChildScrollEffect childScrollEffect) {
            this.scrollEffect = childScrollEffect;
        }

        public void setScrollFlags(int i5) {
            this.scrollFlags = i5;
        }

        public void setScrollInterpolator(Interpolator interpolator) {
            this.scrollInterpolator = interpolator;
        }

        public void setScrollEffect(int i5) {
            this.scrollEffect = createScrollEffectFromInt(i5);
        }

        public LayoutParams(int i5, int i7) {
            super(i5, i7);
            this.scrollFlags = 1;
        }

        public LayoutParams(int i5, int i7, float f2) {
            super(i5, i7, f2);
            this.scrollFlags = 1;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.scrollFlags = 1;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.scrollFlags = 1;
        }

        public LayoutParams(LinearLayout.LayoutParams layoutParams) {
            super(layoutParams);
            this.scrollFlags = 1;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super((LinearLayout.LayoutParams) layoutParams);
            this.scrollFlags = 1;
            this.scrollFlags = layoutParams.scrollFlags;
            this.scrollEffect = layoutParams.scrollEffect;
            this.scrollInterpolator = layoutParams.scrollInterpolator;
        }
    }

    public interface LiftOnScrollListener {
        void onUpdate(float f2, int i5);
    }

    public interface OnOffsetChangedListener extends BaseOnOffsetChangedListener<AppBarLayout> {
        @Override // com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
        void onOffsetChanged(AppBarLayout appBarLayout, int i5);
    }

    public static class ScrollingViewBehavior extends HeaderScrollingViewBehavior {
        public ScrollingViewBehavior() {
        }

        private static int getAppBarLayoutOffset(AppBarLayout appBarLayout) {
            d dVar = ((g) appBarLayout.getLayoutParams()).f7154a;
            if (dVar instanceof BaseBehavior) {
                return ((BaseBehavior) dVar).getTopBottomOffsetForScrollingSibling();
            }
            return 0;
        }

        private void offsetChildAsNeeded(View view, View view2) {
            d dVar = ((g) view2.getLayoutParams()).f7154a;
            if (dVar instanceof BaseBehavior) {
                int bottom = ((((BaseBehavior) dVar).offsetDelta + (view2.getBottom() - view.getTop())) + getVerticalLayoutGap()) - getOverlapPixelsForOffset(view2);
                WeakHashMap weakHashMap = W.f7199a;
                view.offsetTopAndBottom(bottom);
            }
        }

        private void updateLiftedStateIfNeeded(View view, View view2) {
            if (view2 instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view2;
                if (appBarLayout.isLiftOnScroll()) {
                    appBarLayout.setLiftedState(appBarLayout.shouldLift(view));
                }
            }
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public /* bridge */ /* synthetic */ View findFirstDependency(List list) {
            return findFirstDependency((List<View>) list);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ int getLeftAndRightOffset() {
            return super.getLeftAndRightOffset();
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public float getOverlapRatioForOffset(View view) {
            int i5;
            if (view instanceof AppBarLayout) {
                AppBarLayout appBarLayout = (AppBarLayout) view;
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                int downNestedPreScrollRange = appBarLayout.getDownNestedPreScrollRange();
                int appBarLayoutOffset = getAppBarLayoutOffset(appBarLayout);
                if ((downNestedPreScrollRange == 0 || totalScrollRange + appBarLayoutOffset > downNestedPreScrollRange) && (i5 = totalScrollRange - downNestedPreScrollRange) != 0) {
                    return (appBarLayoutOffset / i5) + 1.0f;
                }
            }
            return 0.0f;
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public int getScrollRange(View view) {
            return view instanceof AppBarLayout ? ((AppBarLayout) view).getTotalScrollRange() : super.getScrollRange(view);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ int getTopAndBottomOffset() {
            return super.getTopAndBottomOffset();
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ boolean isHorizontalOffsetEnabled() {
            return super.isHorizontalOffsetEnabled();
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ boolean isVerticalOffsetEnabled() {
            return super.isVerticalOffsetEnabled();
        }

        @Override // androidx.coordinatorlayout.widget.d
        public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
            return view2 instanceof AppBarLayout;
        }

        @Override // androidx.coordinatorlayout.widget.d
        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, View view, View view2) {
            offsetChildAsNeeded(view, view2);
            updateLiftedStateIfNeeded(view, view2);
            return false;
        }

        @Override // androidx.coordinatorlayout.widget.d
        public void onDependentViewRemoved(CoordinatorLayout coordinatorLayout, View view, View view2) {
            if (view2 instanceof AppBarLayout) {
                W.g(L.f.f1780f.a(), coordinatorLayout);
                W.e(0, coordinatorLayout);
                W.g(L.f.f1781g.a(), coordinatorLayout);
                W.e(0, coordinatorLayout);
                W.i(coordinatorLayout, null);
            }
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior, androidx.coordinatorlayout.widget.d
        public /* bridge */ /* synthetic */ boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i5) {
            return super.onLayoutChild(coordinatorLayout, view, i5);
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior, androidx.coordinatorlayout.widget.d
        public /* bridge */ /* synthetic */ boolean onMeasureChild(CoordinatorLayout coordinatorLayout, View view, int i5, int i7, int i9, int i10) {
            return super.onMeasureChild(coordinatorLayout, view, i5, i7, i9, i10);
        }

        @Override // androidx.coordinatorlayout.widget.d
        public boolean onRequestChildRectangleOnScreen(CoordinatorLayout coordinatorLayout, View view, Rect rect, boolean z9) {
            AppBarLayout appBarLayoutFindFirstDependency = findFirstDependency(coordinatorLayout.getDependencies(view));
            if (appBarLayoutFindFirstDependency != null) {
                Rect rect2 = new Rect(rect);
                rect2.offset(view.getLeft(), view.getTop());
                Rect rect3 = this.tempRect1;
                rect3.set(0, 0, coordinatorLayout.getWidth(), coordinatorLayout.getHeight());
                if (!rect3.contains(rect2)) {
                    appBarLayoutFindFirstDependency.setExpanded(false, !z9);
                    return true;
                }
            }
            return false;
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ void setHorizontalOffsetEnabled(boolean z9) {
            super.setHorizontalOffsetEnabled(z9);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ boolean setLeftAndRightOffset(int i5) {
            return super.setLeftAndRightOffset(i5);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ boolean setTopAndBottomOffset(int i5) {
            return super.setTopAndBottomOffset(i5);
        }

        @Override // com.google.android.material.appbar.ViewOffsetBehavior
        public /* bridge */ /* synthetic */ void setVerticalOffsetEnabled(boolean z9) {
            super.setVerticalOffsetEnabled(z9);
        }

        public ScrollingViewBehavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ScrollingViewBehavior_Layout);
            setOverlayTop(typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ScrollingViewBehavior_Layout_behavior_overlapTop, 0));
            typedArrayObtainStyledAttributes.recycle();
        }

        @Override // com.google.android.material.appbar.HeaderScrollingViewBehavior
        public AppBarLayout findFirstDependency(List<View> list) {
            int size = list.size();
            for (int i5 = 0; i5 < size; i5++) {
                View view = list.get(i5);
                if (view instanceof AppBarLayout) {
                    return (AppBarLayout) view;
                }
            }
            return null;
        }
    }

    public static class SeslAppbarState {
        private int mCurrentState = 3;

        public int getState() {
            return this.mCurrentState;
        }

        public void onStateChanged(int i5) {
            this.mCurrentState = i5;
        }
    }

    public interface SeslBaseOnImmOffsetChangedListener<T extends AppBarLayout> {
        void onOffsetChanged(T t8, int i5);
    }

    public interface SeslOnImmOffsetChangedListener extends SeslBaseOnImmOffsetChangedListener<AppBarLayout> {
        @Override // com.google.android.material.appbar.AppBarLayout.SeslBaseOnImmOffsetChangedListener
        void onOffsetChanged(AppBarLayout appBarLayout, int i5);
    }

    public AppBarLayout(Context context) {
        this(context, null);
    }

    private int calculateInternalHeight() {
        int windowHeight = getWindowHeight();
        float heightPercent = windowHeight * getHeightPercent();
        if (heightPercent == 0.0f) {
            updateInternalCollapsedHeightOnce();
            heightPercent = seslGetCollapsedHeight();
        }
        StringBuilder sb = new StringBuilder("[calculateInternalHeight] orientation:" + this.mResources.getConfiguration().orientation + ", density:" + this.mResources.getConfiguration().densityDpi + ", windowHeight:" + windowHeight + ", heightDp:" + heightPercent);
        if (!this.mUseCustomHeight) {
            sb.append(", [3]mHeightProportion : ");
            sb.append(this.mHeightProportion);
        } else if (this.mSetCustomProportion) {
            sb.append(", [1]mCustomHeightProportion : ");
            sb.append(this.mCustomHeightProportion);
        } else if (this.mSetCustomHeight) {
            heightPercent = getImmersiveTopInset() + this.mCustomHeight;
            sb.append(", [2]CustomHeight : ");
            sb.append(this.mCustomHeight);
        }
        if (this.mIsActivatedImmersiveScroll) {
            Log.i(TAG, sb.toString());
        }
        return (int) heightPercent;
    }

    private void clearLiftOnScrollTargetView() {
        WeakReference<View> weakReference = this.liftOnScrollTargetView;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.liftOnScrollTargetView = null;
    }

    private Integer extractStatusBarForegroundColor() {
        Drawable drawable = this.statusBarForeground;
        if (drawable instanceof MaterialShapeDrawable) {
            return Integer.valueOf(((MaterialShapeDrawable) drawable).getResolvedTintColor());
        }
        ColorStateList colorStateListOrNull = DrawableUtils.getColorStateListOrNull(drawable);
        if (colorStateListOrNull != null) {
            return Integer.valueOf(colorStateListOrNull.getDefaultColor());
        }
        return null;
    }

    private View findLiftOnScrollTargetView(View view) {
        int i5;
        if (this.liftOnScrollTargetView == null && (i5 = this.liftOnScrollTargetViewId) != -1) {
            View viewFindViewById = view != null ? view.findViewById(i5) : null;
            if (viewFindViewById == null && (getParent() instanceof ViewGroup)) {
                viewFindViewById = ((ViewGroup) getParent()).findViewById(this.liftOnScrollTargetViewId);
            }
            if (viewFindViewById != null) {
                this.liftOnScrollTargetView = new WeakReference<>(viewFindViewById);
            }
        }
        WeakReference<View> weakReference = this.liftOnScrollTargetView;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    private float getDifferImmHeightRatio() {
        float windowHeight = getWindowHeight();
        float immersiveTopInset = getImmersiveTopInset();
        if (windowHeight == 0.0f) {
            windowHeight = 1.0f;
        }
        return immersiveTopInset / windowHeight;
    }

    private float getHeightPercent() {
        if (!this.mUseCustomHeight) {
            return this.mHeightProportion;
        }
        float f2 = this.mCustomHeightProportion;
        if (f2 != 0.0f) {
            return (getCanScroll() ? getDifferImmHeightRatio() : 0.0f) + f2;
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SeslImmersiveScrollBehavior getImmBehavior() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (!(layoutParams instanceof g)) {
            return null;
        }
        d dVar = ((g) layoutParams).f7154a;
        if (dVar instanceof SeslImmersiveScrollBehavior) {
            return (SeslImmersiveScrollBehavior) dVar;
        }
        return null;
    }

    private int getWindowHeight() {
        return this.mIsActivatedImmersiveScroll ? getContext().getResources().getDisplayMetrics().heightPixels : SeslAppBarHelper.INSTANCE.getScreenHeight(this);
    }

    private boolean hasCollapsibleChild() {
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            if (((LayoutParams) getChildAt(i5).getLayoutParams()).isCollapsible()) {
                return true;
            }
        }
        return false;
    }

    private void initializeLiftOnScrollWithColor(final MaterialShapeDrawable materialShapeDrawable, final ColorStateList colorStateList, final ColorStateList colorStateList2) {
        final Integer colorOrNull = MaterialColors.getColorOrNull(getContext(), R.attr.colorSurface);
        this.liftOnScrollColorUpdateListener = new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.appbar.a
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f10017e.lambda$initializeLiftOnScrollWithColor$0(colorStateList, colorStateList2, materialShapeDrawable, colorOrNull, valueAnimator);
            }
        };
        WeakHashMap weakHashMap = W.f7199a;
        setBackground(materialShapeDrawable);
    }

    private void initializeLiftOnScrollWithElevation(Context context, MaterialShapeDrawable materialShapeDrawable) {
        materialShapeDrawable.initializeElevationOverlay(context);
        this.liftOnScrollColorUpdateListener = new b(0, this, materialShapeDrawable);
        WeakHashMap weakHashMap = W.f7199a;
        setBackground(materialShapeDrawable);
    }

    private void invalidateScrollRanges() {
        Behavior behavior = this.behavior;
        BaseBehavior.SavedState savedStateSaveScrollState = (behavior == null || this.totalScrollRange == -1 || this.pendingAction != 0) ? null : behavior.saveScrollState(R.c.EMPTY_STATE, this);
        this.totalScrollRange = -1;
        this.downPreScrollRange = -1;
        this.downScrollRange = -1;
        if (savedStateSaveScrollState != null) {
            this.behavior.restoreScrollState(savedStateSaveScrollState, false);
        }
    }

    private boolean isDexEnabled() {
        if (getContext() == null) {
            return false;
        }
        return s6.c.M(getContext().getResources().getConfiguration());
    }

    private boolean isLiftOnScrollCompatibleBackground() {
        return getBackground() instanceof MaterialShapeDrawable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$initializeLiftOnScrollWithColor$0(ColorStateList colorStateList, ColorStateList colorStateList2, MaterialShapeDrawable materialShapeDrawable, Integer num, ValueAnimator valueAnimator) {
        Integer num2;
        int iLayer = MaterialColors.layer(colorStateList.getDefaultColor(), colorStateList2.getDefaultColor(), ((Float) valueAnimator.getAnimatedValue()).floatValue());
        materialShapeDrawable.setFillColor(ColorStateList.valueOf(iLayer));
        if (this.statusBarForeground != null && (num2 = this.statusBarForegroundOriginalColor) != null && num2.equals(num)) {
            E.a.g(this.statusBarForeground, iLayer);
        }
        if (this.liftOnScrollListeners.isEmpty()) {
            return;
        }
        for (LiftOnScrollListener liftOnScrollListener : this.liftOnScrollListeners) {
            if (materialShapeDrawable.getFillColor() != null) {
                liftOnScrollListener.onUpdate(0.0f, iLayer);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initializeLiftOnScrollWithElevation$1(MaterialShapeDrawable materialShapeDrawable, ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        materialShapeDrawable.setElevation(fFloatValue);
        Drawable drawable = this.statusBarForeground;
        if (drawable instanceof MaterialShapeDrawable) {
            ((MaterialShapeDrawable) drawable).setElevation(fFloatValue);
        }
        Iterator<LiftOnScrollListener> it = this.liftOnScrollListeners.iterator();
        while (it.hasNext()) {
            it.next().onUpdate(fFloatValue, materialShapeDrawable.getResolvedTintColor());
        }
    }

    private boolean setLiftableState(boolean z9) {
        if (this.liftable == z9) {
            return false;
        }
        this.liftable = z9;
        refreshDrawableState();
        return true;
    }

    private boolean shouldDrawStatusBarForeground() {
        return this.statusBarForeground != null && getTopInset() > 0;
    }

    private boolean shouldOffsetFirstChild() {
        if (getChildCount() <= 0) {
            return false;
        }
        View childAt = getChildAt(0);
        if (childAt.getVisibility() == 8) {
            return false;
        }
        WeakHashMap weakHashMap = W.f7199a;
        return !childAt.getFitsSystemWindows();
    }

    private void startLiftOnScrollColorAnimation(float f2, float f7) {
        ValueAnimator valueAnimator = this.liftOnScrollColorAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f2, f7);
        this.liftOnScrollColorAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(this.liftOnScrollColorDuration);
        this.liftOnScrollColorAnimator.setInterpolator(this.liftOnScrollColorInterpolator);
        ValueAnimator.AnimatorUpdateListener animatorUpdateListener = this.liftOnScrollColorUpdateListener;
        if (animatorUpdateListener != null) {
            this.liftOnScrollColorAnimator.addUpdateListener(animatorUpdateListener);
        }
        this.liftOnScrollColorAnimator.start();
    }

    private void updateInternalHeight() {
        updateInternalHeight(calculateInternalHeight());
    }

    private void updateWillNotDraw() {
        setWillNotDraw(!shouldDrawStatusBarForeground());
    }

    public void addLiftOnScrollListener(LiftOnScrollListener liftOnScrollListener) {
        this.liftOnScrollListeners.add(liftOnScrollListener);
    }

    public void addOnOffsetChangedListener(BaseOnOffsetChangedListener baseOnOffsetChangedListener) {
        if (this.listeners == null) {
            this.listeners = new ArrayList();
        }
        if (baseOnOffsetChangedListener == null || this.listeners.contains(baseOnOffsetChangedListener)) {
            return;
        }
        this.listeners.add(baseOnOffsetChangedListener);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void clearLiftOnScrollListener() {
        this.liftOnScrollListeners.clear();
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 8) {
            if (this.liftOnScrollTargetView != null) {
                if (motionEvent.getAxisValue(9) < 0.0f) {
                    setExpanded(false);
                } else if (motionEvent.getAxisValue(9) > 0.0f && !canScrollVertically(-1)) {
                    setExpanded(true);
                }
            } else if (motionEvent.getAxisValue(9) < 0.0f) {
                setExpanded(false);
            } else if (motionEvent.getAxisValue(9) > 0.0f) {
                setExpanded(true);
            }
        }
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (shouldDrawStatusBarForeground()) {
            int iSave = canvas.save();
            canvas.translate(0.0f, -this.currentOffset);
            this.statusBarForeground.draw(canvas);
            canvas.restoreToCount(iSave);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.statusBarForeground;
        if (drawable != null && drawable.isStateful() && drawable.setState(drawableState)) {
            invalidateDrawable(drawable);
        }
    }

    @Override // androidx.coordinatorlayout.widget.c
    public d getBehavior() {
        Behavior behavior = new Behavior();
        this.behavior = behavior;
        return behavior;
    }

    public boolean getCanScroll() {
        return this.mIsCanScroll;
    }

    public int getCurrentOrientation() {
        return this.mCurrentOrientation;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int getDownNestedPreScrollRange() {
        /*
            r11 = this;
            r0 = 5
            r1 = 8
            int r2 = r11.downPreScrollRange
            r3 = -1
            if (r2 == r3) goto L9
            return r2
        L9:
            int r2 = r11.getChildCount()
            int r2 = r2 + (-1)
            r4 = 0
            r5 = r4
        L11:
            if (r2 < 0) goto L77
            android.view.View r6 = r11.getChildAt(r2)
            int r7 = r6.getVisibility()
            if (r7 != r1) goto L1e
            goto L62
        L1e:
            android.view.ViewGroup$LayoutParams r7 = r6.getLayoutParams()
            com.google.android.material.appbar.AppBarLayout$LayoutParams r7 = (com.google.android.material.appbar.AppBarLayout.LayoutParams) r7
            int r8 = r6.getMeasuredHeight()
            int r9 = r7.scrollFlags
            r10 = r9 & 5
            if (r10 != r0) goto L64
            int r10 = r7.topMargin
            int r7 = r7.bottomMargin
            int r10 = r10 + r7
            r7 = r9 & 8
            if (r7 == 0) goto L3f
            java.util.WeakHashMap r7 = androidx.core.view.W.f7199a
            int r7 = r6.getMinimumHeight()
        L3d:
            int r7 = r7 + r10
            goto L4e
        L3f:
            r7 = r9 & 2
            if (r7 == 0) goto L4c
            java.util.WeakHashMap r7 = androidx.core.view.W.f7199a
            int r7 = r6.getMinimumHeight()
            int r7 = r8 - r7
            goto L3d
        L4c:
            int r7 = r10 + r8
        L4e:
            if (r2 != 0) goto L61
            java.util.WeakHashMap r9 = androidx.core.view.W.f7199a
            boolean r6 = r6.getFitsSystemWindows()
            if (r6 == 0) goto L61
            int r6 = r11.getTopInset()
            int r8 = r8 - r6
            int r7 = java.lang.Math.min(r7, r8)
        L61:
            int r5 = r5 + r7
        L62:
            int r2 = r2 + r3
            goto L11
        L64:
            boolean r0 = r11.getCanScroll()
            if (r0 == 0) goto L77
            float r0 = (float) r5
            float r1 = r11.seslGetCollapsedHeight()
            int r2 = r11.seslGetAdditionalScrollRange()
            float r2 = (float) r2
            float r1 = r1 + r2
            float r1 = r1 + r0
            int r5 = (int) r1
        L77:
            int r0 = java.lang.Math.max(r4, r5)
            r11.downPreScrollRange = r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.AppBarLayout.getDownNestedPreScrollRange():int");
    }

    public int getDownNestedScrollRange() {
        int minimumHeight;
        int i5 = this.downScrollRange;
        if (i5 != -1) {
            return i5;
        }
        int childCount = getChildCount();
        int i7 = 0;
        int i9 = 0;
        while (true) {
            if (i7 >= childCount) {
                break;
            }
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredHeight = ((LinearLayout.LayoutParams) layoutParams).topMargin + ((LinearLayout.LayoutParams) layoutParams).bottomMargin + childAt.getMeasuredHeight();
                int i10 = layoutParams.scrollFlags;
                if ((i10 & 1) == 0) {
                    break;
                }
                i9 += measuredHeight;
                if ((i10 & 2) != 0) {
                    if (this.mIsCanScroll && (childAt instanceof CollapsingToolbarLayout)) {
                        minimumHeight = ((CollapsingToolbarLayout) childAt).seslGetMinimumHeightWithoutMargin();
                    } else {
                        WeakHashMap weakHashMap = W.f7199a;
                        minimumHeight = childAt.getMinimumHeight();
                    }
                    i9 -= minimumHeight;
                }
            }
            i7++;
        }
        int iMax = Math.max(0, i9);
        this.downScrollRange = iMax;
        return iMax;
    }

    public final int getImmersiveTopInset() {
        if (this.mIsCanScroll) {
            return this.mImmersiveTopInset;
        }
        return 0;
    }

    public boolean getIsMouse() {
        return this.isMouse;
    }

    public int getLiftOnScrollTargetViewId() {
        return this.liftOnScrollTargetViewId;
    }

    public MaterialShapeDrawable getMaterialShapeBackground() {
        Drawable background = getBackground();
        if (background instanceof MaterialShapeDrawable) {
            return (MaterialShapeDrawable) background;
        }
        return null;
    }

    public final int getMinimumHeightForVisibleOverlappingContent() {
        int topInset = getTopInset();
        WeakHashMap weakHashMap = W.f7199a;
        int minimumHeight = getMinimumHeight();
        if (minimumHeight == 0) {
            int childCount = getChildCount();
            minimumHeight = childCount >= 1 ? getChildAt(childCount - 1).getMinimumHeight() : 0;
            if (minimumHeight == 0) {
                return getHeight() / 3;
            }
        }
        return (minimumHeight * 2) + topInset;
    }

    public int getPendingAction() {
        return this.pendingAction;
    }

    public Drawable getStatusBarForeground() {
        return this.statusBarForeground;
    }

    @Deprecated
    public float getTargetElevation() {
        return 0.0f;
    }

    public final int getTopInset() {
        w0 w0Var = this.lastInsets;
        if (w0Var != null) {
            return w0Var.d();
        }
        return 0;
    }

    public final int getTotalScrollRange() {
        int i5 = this.totalScrollRange;
        if (i5 != -1) {
            return i5;
        }
        int childCount = getChildCount();
        int i7 = 0;
        int minimumHeight = 0;
        while (true) {
            if (i7 >= childCount) {
                break;
            }
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                int measuredHeight = childAt.getMeasuredHeight();
                int i9 = layoutParams.scrollFlags;
                if ((i9 & 1) == 0) {
                    break;
                }
                int topInset = measuredHeight + ((LinearLayout.LayoutParams) layoutParams).topMargin + ((LinearLayout.LayoutParams) layoutParams).bottomMargin + minimumHeight;
                if (i7 == 0) {
                    WeakHashMap weakHashMap = W.f7199a;
                    if (childAt.getFitsSystemWindows()) {
                        topInset -= getTopInset();
                    }
                }
                minimumHeight = topInset;
                if ((i9 & 2) != 0) {
                    if (getCanScroll()) {
                        minimumHeight += seslGetAdditionalScrollRange() + getTopInset() + this.mBottomPadding;
                    } else {
                        WeakHashMap weakHashMap2 = W.f7199a;
                        minimumHeight -= childAt.getMinimumHeight();
                    }
                }
            }
            i7++;
        }
        int iMax = Math.max(0, minimumHeight);
        this.totalScrollRange = iMax;
        return iMax;
    }

    public int getUpNestedPreScrollRange() {
        return getTotalScrollRange();
    }

    public boolean hasChildWithInterpolator() {
        return this.haveChildWithInterpolator;
    }

    public boolean hasScrollableChildren() {
        return getTotalScrollRange() != 0;
    }

    public void internalActivateImmersiveScroll(boolean z9, boolean z10) {
        this.mIsActivatedImmersiveScroll = z9;
        this.mIsActivatedByUser = z10;
        SeslImmersiveScrollBehavior immBehavior = getImmBehavior();
        if (immBehavior != null) {
            if (!z9 || immBehavior.isAppBarHide()) {
                immBehavior.restoreTopAndBottom(this.mRestoreAnim);
            }
        }
    }

    public void internalProportion(float f2) {
        if (this.mUseCustomHeight || this.mHeightProportion == f2) {
            return;
        }
        this.mHeightProportion = f2;
        updateInternalHeight();
    }

    public boolean isDetachedState() {
        return this.mIsDetachedState;
    }

    public boolean isImmersiveActivatedByUser() {
        return this.mIsActivatedByUser;
    }

    public boolean isLiftOnScroll() {
        return this.liftOnScroll;
    }

    public boolean isLifted() {
        return this.lifted;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mIsDetachedState = false;
        MaterialShapeUtils.setParentAbsoluteElevation(this);
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Drawable drawable = this.mBackground;
        if (drawable != null) {
            setBackgroundDrawable(drawable == getBackground() ? this.mBackground : getBackground());
        } else if (getBackground() != null) {
            Drawable background = getBackground();
            this.mBackground = background;
            setBackgroundDrawable(background);
        } else {
            this.mBackground = null;
            setBackgroundColor(this.mResources.getColor(s6.c.O(getContext()) ? com.samsung.android.keyscafe.R.color.sesl_action_bar_background_color_light : com.samsung.android.keyscafe.R.color.sesl_action_bar_background_color_dark));
        }
        if (this.mCurrentScreenHeight != configuration.screenHeightDp || this.mCurrentOrientation != configuration.orientation) {
            boolean z9 = this.mUseCustomPadding;
            if (!z9 && !this.mUseCollapsedHeight) {
                Log.i(TAG, "Update bottom padding");
                int dimensionPixelSize = this.mResources.getDimensionPixelSize(R.dimen.sesl_extended_appbar_bottom_padding);
                this.mBottomPadding = dimensionPixelSize;
                setPadding(0, 0, 0, dimensionPixelSize);
                float dimensionPixelSize2 = this.mResources.getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_action_bar_height_with_padding) + this.mBottomPadding;
                this.mCollapsedHeight = dimensionPixelSize2;
                seslSetCollapsedHeight(dimensionPixelSize2, false);
            } else if (z9 && this.mBottomPadding == 0 && !this.mUseCollapsedHeight) {
                float dimensionPixelSize3 = this.mResources.getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_action_bar_height_with_padding);
                this.mCollapsedHeight = dimensionPixelSize3;
                seslSetCollapsedHeight(dimensionPixelSize3, false);
            }
        }
        if (!this.mSetCustomProportion) {
            this.mHeightProportion = SeslAppBarHelper.INSTANCE.getAppBarProPortion(getContext());
        }
        updateInternalHeight();
        Log.i(TAG, "onConfigurationChanged : mCollapsedHeight = " + this.mCollapsedHeight + ", mHeightProportion = " + this.mHeightProportion + ", mHasSuggestion = " + this.mHasSuggestion + ", mUseCollapsedHeight = " + this.mUseCollapsedHeight);
        if (this.lifted || (this.mCurrentOrientation == 1 && configuration.orientation == 2)) {
            setExpanded(false, false, true);
        } else {
            setExpanded(true, false, true);
        }
        this.mCurrentOrientation = configuration.orientation;
        this.mCurrentScreenHeight = configuration.screenHeightDp;
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i5) {
        if (this.tmpStatesArray == null) {
            this.tmpStatesArray = new int[4];
        }
        int[] iArr = this.tmpStatesArray;
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i5 + iArr.length);
        boolean z9 = this.liftable;
        int i7 = R.attr.state_liftable;
        if (!z9) {
            i7 = -i7;
        }
        iArr[0] = i7;
        iArr[1] = (z9 && this.lifted) ? R.attr.state_lifted : -R.attr.state_lifted;
        int i9 = R.attr.state_collapsible;
        if (!z9) {
            i9 = -i9;
        }
        iArr[2] = i9;
        iArr[3] = (z9 && this.lifted) ? R.attr.state_collapsed : -R.attr.state_collapsed;
        return View.mergeDrawableStates(iArrOnCreateDrawableState, iArr);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        this.mIsDetachedState = true;
        SeslImmersiveScrollBehavior immBehavior = getImmBehavior();
        if (immBehavior != null) {
            if (this.mIsReservedImmersiveDetachOption && this.mReservedFitSystemWindow) {
                immBehavior.setupDecorFitsSystemWindow();
            }
            immBehavior.notifyOnDetachedFromWindow();
        }
        super.onDetachedFromWindow();
        clearLiftOnScrollTargetView();
    }

    public void onImmOffsetChanged(int i5) {
        if (!willNotDraw()) {
            WeakHashMap weakHashMap = W.f7199a;
            postInvalidateOnAnimation();
        }
        List<SeslBaseOnImmOffsetChangedListener> list = this.mImmOffsetListener;
        if (list != null) {
            int size = list.size();
            for (int i7 = 0; i7 < size; i7++) {
                SeslBaseOnImmOffsetChangedListener seslBaseOnImmOffsetChangedListener = this.mImmOffsetListener.get(i7);
                if (seslBaseOnImmOffsetChangedListener != null) {
                    seslBaseOnImmOffsetChangedListener.onOffsetChanged(this, i5);
                }
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        boolean z10 = true;
        super.onLayout(z9, i5, i7, i9, i10);
        WeakHashMap weakHashMap = W.f7199a;
        if (getFitsSystemWindows() && shouldOffsetFirstChild()) {
            int topInset = getTopInset();
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                getChildAt(childCount).offsetTopAndBottom(topInset);
            }
        }
        invalidateScrollRanges();
        this.haveChildWithInterpolator = false;
        int childCount2 = getChildCount();
        int i11 = 0;
        while (true) {
            if (i11 >= childCount2) {
                break;
            }
            if (((LayoutParams) getChildAt(i11).getLayoutParams()).getScrollInterpolator() != null) {
                this.haveChildWithInterpolator = true;
                break;
            }
            i11++;
        }
        Drawable drawable = this.statusBarForeground;
        if (drawable != null) {
            drawable.setBounds(0, 0, getWidth(), getTopInset());
        }
        if (this.liftableOverride) {
            return;
        }
        if (!this.liftOnScroll && !hasCollapsibleChild()) {
            z10 = false;
        }
        setLiftableState(z10);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i5, int i7) {
        updateInternalHeight();
        super.onMeasure(i5, i7);
        int mode = View.MeasureSpec.getMode(i7);
        if (mode != 1073741824) {
            WeakHashMap weakHashMap = W.f7199a;
            if (getFitsSystemWindows() && shouldOffsetFirstChild()) {
                int measuredHeight = getMeasuredHeight();
                if (mode == Integer.MIN_VALUE) {
                    measuredHeight = com.bumptech.glide.c.d(getTopInset() + getMeasuredHeight(), 0, View.MeasureSpec.getSize(i7));
                } else if (mode == 0) {
                    measuredHeight += getTopInset();
                }
                setMeasuredDimension(getMeasuredWidth(), measuredHeight);
            }
        }
        invalidateScrollRanges();
    }

    public void onOffsetChanged(int i5) {
        this.currentOffset = i5;
        int totalScrollRange = getTotalScrollRange();
        int height = getHeight() - ((int) seslGetCollapsedHeight());
        if (Math.abs(i5) >= totalScrollRange) {
            if (getCanScroll()) {
                if (this.mAppbarState.getState() != 2) {
                    this.mAppbarState.onStateChanged(2);
                }
            } else if (this.mAppbarState.getState() != 0) {
                this.mAppbarState.onStateChanged(0);
            }
        } else if (Math.abs(i5) >= height) {
            if (this.mAppbarState.getState() != 0) {
                this.mAppbarState.onStateChanged(0);
            }
        } else if (Math.abs(i5) == 0) {
            if (this.mAppbarState.getState() != 1) {
                this.mAppbarState.onStateChanged(1);
            }
        } else if (this.mAppbarState.getState() != 3) {
            this.mAppbarState.onStateChanged(3);
        }
        if (!willNotDraw()) {
            WeakHashMap weakHashMap = W.f7199a;
            postInvalidateOnAnimation();
        }
        List<BaseOnOffsetChangedListener> list = this.listeners;
        if (list != null) {
            int size = list.size();
            for (int i7 = 0; i7 < size; i7++) {
                BaseOnOffsetChangedListener baseOnOffsetChangedListener = this.listeners.get(i7);
                if (baseOnOffsetChangedListener != null) {
                    baseOnOffsetChangedListener.onOffsetChanged(this, i5);
                }
            }
        }
    }

    public w0 onWindowInsetChanged(w0 w0Var) {
        WeakHashMap weakHashMap = W.f7199a;
        w0 w0Var2 = getFitsSystemWindows() ? w0Var : null;
        if (!Objects.equals(this.lastInsets, w0Var2)) {
            this.lastInsets = w0Var2;
            updateWillNotDraw();
            requestLayout();
        }
        return w0Var;
    }

    public boolean removeLiftOnScrollListener(LiftOnScrollListener liftOnScrollListener) {
        return this.liftOnScrollListeners.remove(liftOnScrollListener);
    }

    public void removeOnOffsetChangedListener(BaseOnOffsetChangedListener baseOnOffsetChangedListener) {
        List<BaseOnOffsetChangedListener> list = this.listeners;
        if (list == null || baseOnOffsetChangedListener == null) {
            return;
        }
        list.remove(baseOnOffsetChangedListener);
    }

    public void resetAppBarAndInsets() {
        seslResetAppBarAndInsets(true);
    }

    public void resetPendingAction() {
        this.pendingAction = 0;
    }

    public void seslActivateImmersiveScroll(boolean z9, boolean z10) {
        if (isDexEnabled()) {
            Log.i(TAG, "Dex Enabled Set false ImmersiveScroll");
            z9 = false;
        }
        this.mRestoreAnim = z10;
        internalActivateImmersiveScroll(z9, true);
        SeslImmersiveScrollBehavior immBehavior = getImmBehavior();
        if ((immBehavior != null ? immBehavior.dispatchImmersiveScrollEnabled() : true) || !z9) {
            setCanScroll(z9);
        }
    }

    public void seslAddOnImmOffsetChangedListener(SeslBaseOnImmOffsetChangedListener seslBaseOnImmOffsetChangedListener) {
        if (this.mImmOffsetListener == null) {
            this.mImmOffsetListener = new ArrayList();
        }
        if (seslBaseOnImmOffsetChangedListener == null || this.mImmOffsetListener.contains(seslBaseOnImmOffsetChangedListener)) {
            return;
        }
        this.mImmOffsetListener.add(seslBaseOnImmOffsetChangedListener);
    }

    public boolean seslCanImmersiveScroll() {
        SeslImmersiveScrollBehavior immBehavior = getImmBehavior();
        if (immBehavior != null) {
            return immBehavior.getCanImmersiveScrollState();
        }
        return false;
    }

    public void seslCancelWindowInsetsAnimationController() {
        SeslImmersiveScrollBehavior immBehavior = getImmBehavior();
        if (immBehavior != null) {
            Log.i(TAG, "seslCancelWindowInsetsAnimationController");
            immBehavior.cancelWindowInsetsAnimationController();
        }
    }

    public int seslGetAdditionalScrollRange() {
        return this.mAdditionalScrollRange;
    }

    public SeslAppbarState seslGetAppBarState() {
        return this.mAppbarState;
    }

    public float seslGetCollapsedHeight() {
        return this.mCollapsedHeight + getImmersiveTopInset();
    }

    public float seslGetHeightProPortion() {
        return this.mHeightProportion;
    }

    public boolean seslGetImmersiveScroll() {
        return seslIsActivatedImmsersiveScroll();
    }

    public boolean seslHaveImmersiveBehavior() {
        return getImmBehavior() != null;
    }

    public void seslImmersiveRelease() {
        SeslImmersiveScrollBehavior immBehavior = getImmBehavior();
        if (immBehavior != null) {
            immBehavior.release();
        }
    }

    public boolean seslIsActivatedImmsersiveScroll() {
        return this.mIsActivatedImmersiveScroll;
    }

    @Override // androidx.coordinatorlayout.widget.a
    public boolean seslIsCollapsed() {
        return this.lifted;
    }

    public void seslRemoveOnImmOffsetChangedListener(SeslBaseOnImmOffsetChangedListener seslBaseOnImmOffsetChangedListener) {
        List<SeslBaseOnImmOffsetChangedListener> list = this.mImmOffsetListener;
        if (list == null || seslBaseOnImmOffsetChangedListener == null) {
            return;
        }
        list.remove(seslBaseOnImmOffsetChangedListener);
    }

    public void seslReserveImmersiveDetachOption(int i5) {
        if (i5 == 0) {
            this.mIsReservedImmersiveDetachOption = false;
        } else {
            this.mIsReservedImmersiveDetachOption = true;
            this.mReservedFitSystemWindow = (i5 & 1) != 0;
        }
    }

    public void seslResetAppBarAndInsets(boolean z9) {
        SeslImmersiveScrollBehavior immBehavior = getImmBehavior();
        if (immBehavior != null) {
            Log.i(TAG, "seslResetAppBarAndInsets() force = " + z9);
            immBehavior.restoreTopAndBottom(true);
            immBehavior.forceRestoreWindowInset(z9);
        }
    }

    @Deprecated
    public void seslRestoreTopAndBottom(View view) {
        seslRestoreTopAndBottom();
    }

    public void seslSetAutoRestoreTopAndBottom(boolean z9) {
        SeslImmersiveScrollBehavior immBehavior = getImmBehavior();
        if (immBehavior != null) {
            immBehavior.setAutoRestoreTopAndBottom(z9);
        }
    }

    @Deprecated
    public void seslSetBottomView(View view, View view2) {
        seslSetBottomView(view2);
    }

    public void seslSetCollapsedHeight(float f2) {
        Log.i(TAG, "seslSetCollapsedHeight, height : " + f2);
        seslSetCollapsedHeight(f2, true);
    }

    public void seslSetCustomHeight(int i5) {
        g gVar;
        Log.i(TAG, "seslSetCustomHeight: height = " + i5);
        this.mCustomHeight = i5;
        this.mUseCustomHeight = true;
        this.mSetCustomHeight = true;
        this.mSetCustomProportion = false;
        try {
            gVar = (g) getLayoutParams();
        } catch (ClassCastException e3) {
            Log.e(TAG, Log.getStackTraceString(e3));
            gVar = null;
        }
        if (gVar != null) {
            ((ViewGroup.MarginLayoutParams) gVar).height = i5;
            setLayoutParams(gVar);
        }
    }

    public void seslSetCustomHeightProportion(boolean z9, float f2) {
        if (f2 > 1.0f) {
            Log.e(TAG, "Height proportion float range is 0..1");
            return;
        }
        Log.i(TAG, "seslSetCustomHeightProportion: useCustomHeight = " + z9 + ", heightProportion = " + f2);
        this.mUseCustomHeight = z9;
        this.mSetCustomProportion = z9;
        this.mSetCustomHeight = false;
        this.mCustomHeightProportion = f2;
        updateInternalHeight();
        requestLayout();
    }

    @Override // androidx.coordinatorlayout.widget.a
    public void seslSetExpanded(boolean z9) {
        setExpanded(z9);
    }

    public void seslSetImmersiveScroll(boolean z9, boolean z10) {
        seslActivateImmersiveScroll(z9, z10);
    }

    @Override // androidx.coordinatorlayout.widget.a
    public void seslSetIsMouse(boolean z9) {
        this.isMouse = z9;
    }

    public void seslSetNeedToCheckBottomViewMargin(boolean z9) {
        SeslImmersiveScrollBehavior immBehavior = getImmBehavior();
        if (immBehavior != null) {
            immBehavior.setNeedToCheckBottomViewMargin(z9);
        }
    }

    public void seslSetSuggestion(boolean z9) {
        this.mHasSuggestion = z9;
    }

    public void seslSetTCScrollRange(int i5) {
        this.mAdditionalScrollRange = i5;
    }

    public void seslSetWindowInsetsAnimationCallback(Object obj) {
        SeslImmersiveScrollBehavior immBehavior = getImmBehavior();
        if (immBehavior != null) {
            if (obj == null) {
                immBehavior.setWindowInsetsAnimationCallback(this, null);
            }
            if (obj instanceof WindowInsetsAnimation.Callback) {
                immBehavior.setWindowInsetsAnimationCallback(this, (WindowInsetsAnimation.Callback) obj);
            }
        }
    }

    public void setCanScroll(boolean z9) {
        if (this.mIsCanScroll != z9) {
            this.mIsCanScroll = z9;
            invalidateScrollRanges();
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setElevation(float f2) {
        super.setElevation(f2);
        MaterialShapeUtils.setElevation(this, f2);
    }

    public void setExpanded(boolean z9) {
        WeakHashMap weakHashMap = W.f7199a;
        setExpanded(z9, isLaidOut());
    }

    public void setImmersiveTopInset(int i5) {
        this.mImmersiveTopInset = i5;
    }

    public void setLiftOnScroll(boolean z9) {
        this.liftOnScroll = z9;
    }

    public void setLiftOnScrollTargetView(View view) {
        this.liftOnScrollTargetViewId = -1;
        if (view == null) {
            clearLiftOnScrollTargetView();
        } else {
            this.liftOnScrollTargetView = new WeakReference<>(view);
        }
    }

    public void setLiftOnScrollTargetViewId(int i5) {
        this.liftOnScrollTargetViewId = i5;
        clearLiftOnScrollTargetView();
    }

    public boolean setLiftable(boolean z9) {
        this.liftableOverride = true;
        return setLiftableState(z9);
    }

    public void setLiftableOverrideEnabled(boolean z9) {
        this.liftableOverride = z9;
    }

    public boolean setLifted(boolean z9) {
        return setLiftedState(z9, true);
    }

    public boolean setLiftedState(boolean z9) {
        return setLiftedState(z9, !this.liftableOverride);
    }

    @Override // android.widget.LinearLayout
    public void setOrientation(int i5) {
        if (i5 != 1) {
            throw new IllegalArgumentException("AppBarLayout is always vertical and does not support horizontal orientation");
        }
        super.setOrientation(i5);
    }

    public void setStatusBarForeground(Drawable drawable) {
        Drawable drawable2 = this.statusBarForeground;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            this.statusBarForeground = drawable != null ? drawable.mutate() : null;
            this.statusBarForegroundOriginalColor = extractStatusBarForegroundColor();
            Drawable drawable3 = this.statusBarForeground;
            if (drawable3 != null) {
                if (drawable3.isStateful()) {
                    this.statusBarForeground.setState(getDrawableState());
                }
                Drawable drawable4 = this.statusBarForeground;
                WeakHashMap weakHashMap = W.f7199a;
                E.b.b(drawable4, getLayoutDirection());
                this.statusBarForeground.setVisible(getVisibility() == 0, false);
                this.statusBarForeground.setCallback(this);
            }
            updateWillNotDraw();
            WeakHashMap weakHashMap2 = W.f7199a;
            postInvalidateOnAnimation();
        }
    }

    public void setStatusBarForegroundColor(int i5) {
        setStatusBarForeground(new ColorDrawable(i5));
    }

    public void setStatusBarForegroundResource(int i5) {
        setStatusBarForeground(android.support.v4.media.session.f.y(getContext(), i5));
    }

    @Deprecated
    public void setTargetElevation(float f2) {
        ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, f2);
    }

    @Override // android.view.View
    public void setVisibility(int i5) {
        super.setVisibility(i5);
        boolean z9 = i5 == 0;
        Drawable drawable = this.statusBarForeground;
        if (drawable != null) {
            drawable.setVisible(z9, false);
        }
    }

    public boolean shouldLift(View view) {
        View viewFindLiftOnScrollTargetView = findLiftOnScrollTargetView(view);
        if (viewFindLiftOnScrollTargetView != null) {
            view = viewFindLiftOnScrollTargetView;
        }
        return view != null && (view.canScrollVertically(-1) || view.getScrollY() > 0);
    }

    public void updateInternalCollapsedHeight() {
        if (useCollapsedHeight()) {
            return;
        }
        if (getImmBehavior() == null || !getCanScroll()) {
            float fSeslGetCollapsedHeight = seslGetCollapsedHeight();
            float height = getHeight() - getTotalScrollRange();
            if (height == fSeslGetCollapsedHeight || height <= 0.0f) {
                return;
            }
            Log.i(TAG, "Internal collapsedHeight/ oldCollapsedHeight :" + fSeslGetCollapsedHeight + " newCollapsedHeight :" + height);
            seslSetCollapsedHeight(height, false);
            updateInternalHeight();
        }
    }

    public void updateInternalCollapsedHeightOnce() {
        if (useCollapsedHeight()) {
            return;
        }
        if (getImmBehavior() == null || !getCanScroll()) {
            float fSeslGetCollapsedHeight = seslGetCollapsedHeight();
            Log.i(TAG, "update InternalCollapsedHeight from updateInternalHeight() : " + fSeslGetCollapsedHeight);
            seslSetCollapsedHeight(fSeslGetCollapsedHeight, false);
        }
    }

    public boolean useCollapsedHeight() {
        return this.mUseCollapsedHeight;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.statusBarForeground;
    }

    public AppBarLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.appBarLayoutStyle);
    }

    private void updateInternalHeight(int i5) {
        boolean z9 = this.mUseCustomHeight;
        if (!z9 || (z9 && (this.mSetCustomProportion || this.mSetCustomHeight))) {
            try {
                g gVar = (g) getLayoutParams();
                ((ViewGroup.MarginLayoutParams) gVar).height = i5;
                setLayoutParams(gVar);
            } catch (ClassCastException e3) {
                Log.e(TAG, Log.getStackTraceString(e3));
            }
        }
        if (this.mIsActivatedImmersiveScroll) {
            StringBuilder sb = new StringBuilder("[updateInternalHeight] mUseCustomHeight : " + this.mUseCustomHeight + ", mSetCustomProportion : " + this.mSetCustomProportion + ", mSetCustomHeight : " + this.mSetCustomHeight + ", mIsImmersiveScroll : " + this.mIsActivatedImmersiveScroll + ", mIsSetByUser : " + this.mIsActivatedByUser + ", mImmersiveTopInset : " + this.mImmersiveTopInset);
            WindowInsets rootWindowInsets = getRootView().getRootWindowInsets();
            if (rootWindowInsets != null) {
                sb.append("\n");
                sb.append(rootWindowInsets);
            }
            Log.i(TAG, sb.toString());
        }
    }

    public void seslRestoreTopAndBottom() {
        SeslImmersiveScrollBehavior immBehavior = getImmBehavior();
        if (immBehavior != null) {
            immBehavior.restoreTopAndBottom(true);
        }
    }

    public void seslSetBottomView(View view) {
        if (view == null) {
            Log.w(TAG, "bottomView is null");
        }
        SeslImmersiveScrollBehavior immBehavior = getImmBehavior();
        if (immBehavior != null) {
            immBehavior.setBottomView(view);
        }
    }

    public void seslSetImmersiveScroll(boolean z9) {
        seslActivateImmersiveScroll(z9);
    }

    public boolean setLiftedState(boolean z9, boolean z10) {
        if (!z10 || this.lifted == z9) {
            return false;
        }
        this.lifted = z9;
        refreshDrawableState();
        if (!isLiftOnScrollCompatibleBackground()) {
            return true;
        }
        if (this.hasLiftOnScrollColor) {
            startLiftOnScrollColorAnimation(z9 ? 0.0f : 1.0f, z9 ? 1.0f : 0.0f);
            return true;
        }
        if (!this.liftOnScroll) {
            return true;
        }
        startLiftOnScrollColorAnimation(z9 ? 0.0f : this.appBarElevation, z9 ? this.appBarElevation : 0.0f);
        return true;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public AppBarLayout(Context context, AttributeSet attributeSet, int i5) {
        int i7 = DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(context, attributeSet, i5, i7), attributeSet, i5);
        this.totalScrollRange = -1;
        this.downPreScrollRange = -1;
        this.downScrollRange = -1;
        this.pendingAction = 0;
        this.lifted = false;
        this.liftOnScrollListeners = new ArrayList();
        this.mCustomHeight = -1;
        this.mBottomPadding = 0;
        this.mUseCollapsedHeight = false;
        this.isMouse = false;
        this.mIsReservedImmersiveDetachOption = false;
        this.mReservedFitSystemWindow = false;
        this.mIsDetachedState = false;
        this.mHasSuggestion = false;
        this.mIsActivatedImmersiveScroll = false;
        this.mIsActivatedByUser = false;
        this.mIsCanScroll = false;
        this.mRestoreAnim = false;
        this.mAdditionalScrollRange = 0;
        this.mImmersiveTopInset = 0;
        this.mLastTappableInsets = null;
        this.mLastSysInsets = null;
        Context context2 = getContext();
        setOrientation(1);
        ViewUtilsLollipop.setStateListAnimatorFromAttrs(this, attributeSet, i5, i7);
        TypedArray typedArrayObtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R.styleable.AppBarLayout, i5, i7, new int[0]);
        this.mAppbarState = new SeslAppbarState();
        this.mResources = getResources();
        boolean zO = s6.c.O(context2);
        int i9 = R.styleable.AppBarLayout_android_background;
        if (typedArrayObtainStyledAttributes.hasValue(i9)) {
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(i9);
            this.mBackground = drawable;
            WeakHashMap weakHashMap = W.f7199a;
            setBackground(drawable);
        } else {
            this.mBackground = null;
            setBackgroundColor(this.mResources.getColor(zO ? com.samsung.android.keyscafe.R.color.sesl_action_bar_background_color_light : com.samsung.android.keyscafe.R.color.sesl_action_bar_background_color_dark));
        }
        ColorStateList colorStateList = MaterialResources.getColorStateList(context2, typedArrayObtainStyledAttributes, R.styleable.AppBarLayout_liftOnScrollColor);
        this.hasLiftOnScrollColor = colorStateList != null;
        ColorStateList colorStateListOrNull = DrawableUtils.getColorStateListOrNull(getBackground());
        if (colorStateListOrNull != null) {
            MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
            materialShapeDrawable.setFillColor(colorStateListOrNull);
            if (colorStateList != null) {
                initializeLiftOnScrollWithColor(materialShapeDrawable, colorStateListOrNull, colorStateList);
            } else {
                initializeLiftOnScrollWithElevation(context2, materialShapeDrawable);
            }
        }
        this.liftOnScrollColorDuration = MotionUtils.resolveThemeDuration(context2, R.attr.motionDurationMedium2, getResources().getInteger(R.integer.app_bar_elevation_anim_duration));
        this.liftOnScrollColorInterpolator = MotionUtils.resolveThemeInterpolator(context2, R.attr.motionEasingStandardInterpolator, com.google.android.material.animation.AnimationUtils.LINEAR_INTERPOLATOR);
        int i10 = R.styleable.AppBarLayout_expanded;
        if (typedArrayObtainStyledAttributes.hasValue(i10)) {
            setExpanded(typedArrayObtainStyledAttributes.getBoolean(i10, false), false, false);
        }
        int i11 = R.styleable.AppBarLayout_elevation;
        if (typedArrayObtainStyledAttributes.hasValue(i11)) {
            ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, typedArrayObtainStyledAttributes.getDimensionPixelSize(i11, 0));
        }
        int i12 = R.styleable.AppBarLayout_seslUseCustomHeight;
        if (typedArrayObtainStyledAttributes.hasValue(i12)) {
            this.mUseCustomHeight = typedArrayObtainStyledAttributes.getBoolean(i12, false);
        }
        int i13 = R.styleable.AppBarLayout_seslHeightProportion;
        if (typedArrayObtainStyledAttributes.hasValue(i13)) {
            this.mSetCustomProportion = true;
            this.mCustomHeightProportion = typedArrayObtainStyledAttributes.getFloat(i13, DEFAULT_HEIGHT_RATIO_TO_SCREEN);
        } else {
            this.mSetCustomProportion = false;
            this.mCustomHeightProportion = DEFAULT_HEIGHT_RATIO_TO_SCREEN;
        }
        this.mHeightProportion = SeslAppBarHelper.INSTANCE.getAppBarProPortion(getContext());
        int i14 = R.styleable.AppBarLayout_seslUseCustomPadding;
        if (typedArrayObtainStyledAttributes.hasValue(i14)) {
            this.mUseCustomPadding = typedArrayObtainStyledAttributes.getBoolean(i14, false);
        }
        if (this.mUseCustomPadding) {
            this.mBottomPadding = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.AppBarLayout_android_paddingBottom, 0);
        } else {
            this.mBottomPadding = this.mResources.getDimensionPixelOffset(R.dimen.sesl_extended_appbar_bottom_padding);
        }
        setPadding(0, 0, 0, this.mBottomPadding);
        float dimensionPixelSize = this.mResources.getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_action_bar_height_with_padding) + this.mBottomPadding;
        this.mCollapsedHeight = dimensionPixelSize;
        seslSetCollapsedHeight(dimensionPixelSize, false);
        if (typedArrayObtainStyledAttributes.hasValue(i11)) {
            ViewUtilsLollipop.setDefaultAppBarLayoutStateListAnimator(this, typedArrayObtainStyledAttributes.getDimensionPixelSize(i11, 0));
        }
        int i15 = R.styleable.AppBarLayout_android_keyboardNavigationCluster;
        if (typedArrayObtainStyledAttributes.hasValue(i15)) {
            setKeyboardNavigationCluster(typedArrayObtainStyledAttributes.getBoolean(i15, false));
        }
        int i16 = R.styleable.AppBarLayout_android_touchscreenBlocksFocus;
        if (typedArrayObtainStyledAttributes.hasValue(i16)) {
            setTouchscreenBlocksFocus(typedArrayObtainStyledAttributes.getBoolean(i16, false));
        }
        this.appBarElevation = getResources().getDimension(R.dimen.design_appbar_elevation);
        this.liftOnScroll = typedArrayObtainStyledAttributes.getBoolean(R.styleable.AppBarLayout_liftOnScroll, false);
        this.liftOnScrollTargetViewId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.AppBarLayout_liftOnScrollTargetViewId, -1);
        setStatusBarForeground(typedArrayObtainStyledAttributes.getDrawable(R.styleable.AppBarLayout_statusBarForeground));
        typedArrayObtainStyledAttributes.recycle();
        InterfaceC0226s interfaceC0226s = new InterfaceC0226s() { // from class: com.google.android.material.appbar.AppBarLayout.1
            @Override // androidx.core.view.InterfaceC0226s
            public w0 onApplyWindowInsets(View view, w0 w0Var) {
                f fVarF = w0Var.f7266a.f(AppBarLayout.SYSTEM_BARS);
                f fVarF2 = w0Var.f7266a.f(AppBarLayout.TAPPABLE_ELEMENT);
                if (!fVarF2.equals(AppBarLayout.this.mLastTappableInsets) || !fVarF.equals(AppBarLayout.this.mLastSysInsets)) {
                    Log.d(AppBarLayout.TAG, "[onApplyWindowInsets] sysInsets : " + fVarF + ", tappableInsets : " + fVarF2);
                    if (AppBarLayout.this.getImmBehavior() != null) {
                        AppBarLayout.this.getImmBehavior().notifyOnApplyWindowInsets();
                    }
                    AppBarLayout.this.mLastSysInsets = fVarF;
                    AppBarLayout.this.mLastTappableInsets = fVarF2;
                }
                return AppBarLayout.this.onWindowInsetChanged(w0Var);
            }
        };
        WeakHashMap weakHashMap2 = W.f7199a;
        M.u(this, interfaceC0226s);
        this.mCurrentOrientation = this.mResources.getConfiguration().orientation;
        this.mCurrentScreenHeight = this.mResources.getConfiguration().screenHeightDp;
        Log.i(TAG, "Init : mUseCustomHeight = " + this.mUseCustomHeight + ", mCustomHeightProportion = " + this.mCustomHeightProportion + ", mHeightProportion = " + this.mHeightProportion + ", mUseCustomPadding = " + this.mUseCustomPadding + ", mCurrentScreenHeight = " + this.mCurrentScreenHeight);
    }

    private void seslSetCollapsedHeight(float f2, boolean z9) {
        this.mUseCollapsedHeight = z9;
        this.mCollapsedHeight = f2;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -2);
    }

    public void removeOnOffsetChangedListener(OnOffsetChangedListener onOffsetChangedListener) {
        removeOnOffsetChangedListener((BaseOnOffsetChangedListener) onOffsetChangedListener);
    }

    public void seslRemoveOnImmOffsetChangedListener(OnOffsetChangedListener onOffsetChangedListener) {
        seslRemoveOnImmOffsetChangedListener((SeslBaseOnImmOffsetChangedListener) onOffsetChangedListener);
    }

    public void seslRestoreTopAndBottom(boolean z9) {
        SeslImmersiveScrollBehavior immBehavior = getImmBehavior();
        if (immBehavior != null) {
            immBehavior.restoreTopAndBottom(z9);
        }
    }

    public void setExpanded(boolean z9, boolean z10) {
        setExpanded(z9, z10, true);
    }

    private void setExpanded(boolean z9, boolean z10, boolean z11) {
        int i5;
        setLifted(!z9);
        if (z9) {
            i5 = 1;
        } else {
            i5 = seslGetImmersiveScroll() ? 512 : 2;
        }
        this.pendingAction = i5 | (z10 ? 4 : 0) | (z11 ? 8 : 0);
        requestLayout();
    }

    public void addOnOffsetChangedListener(OnOffsetChangedListener onOffsetChangedListener) {
        addOnOffsetChangedListener((BaseOnOffsetChangedListener) onOffsetChangedListener);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    public void seslAddOnImmOffsetChangedListener(SeslOnImmOffsetChangedListener seslOnImmOffsetChangedListener) {
        seslAddOnImmOffsetChangedListener((SeslBaseOnImmOffsetChangedListener) seslOnImmOffsetChangedListener);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup
    public LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof LinearLayout.LayoutParams) {
            return new LayoutParams((LinearLayout.LayoutParams) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    public void seslActivateImmersiveScroll(boolean z9) {
        seslActivateImmersiveScroll(z9, true);
    }
}
