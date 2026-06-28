package com.google.android.material.appbar;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.WindowInsetsAnimationControlListener;
import android.view.WindowInsetsAnimationController;
import android.view.WindowInsetsController;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.PathInterpolator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.internal.SeslContextUtils;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class SeslImmersiveScrollBehavior extends AppBarLayout.Behavior {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MSG_APPEAR_ANIMATION = 100;
    private static final String TAG = "SeslImmersiveScrollBehavior";
    private boolean isRoundedCornerHide;
    private WindowInsetsAnimationController mAnimationController;
    private final Handler mAnimationHandler;
    private AppBarLayout mAppBarLayout;
    private View mBottomArea;
    boolean mCalledHideShowOnLayoutChild;
    private boolean mCanImmersiveScroll;
    private CancellationSignal mCancellationSignal;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private View mContentView;
    private Context mContext;
    private CoordinatorLayout mCoordinatorLayout;
    private float mCurOffset;
    private WindowInsetsAnimation.Callback mCustomWindowInsetsAnimation;
    private View mDecorView;
    private WindowInsets mDecorViewInset;
    private boolean mIsMultiWindow;
    private boolean mIsSetAutoRestore;
    private View mNavigationBarBg;
    private int mNavigationBarHeight;
    private boolean mNeedInit;
    private boolean mNeedRestoreAnim;
    private boolean mNeedToCheckBottomViewMargin;
    private ValueAnimator mOffsetAnimator;
    private final AppBarLayout.OnOffsetChangedListener mOffsetChangedListener;
    private WindowInsetsController.OnControllableInsetsChangedListener mOnInsetsChangedListener;
    private int mPrevOffset;
    private int mPrevOrientation;
    private boolean mShownAtDown;
    private View mStatusBarBg;
    private int mStatusBarHeight;
    private View mTargetView;
    private boolean mToolIsMouse;
    private final WindowInsetsAnimation.Callback mWindowAnimationCallback;
    private final WindowInsetsAnimationControlListener mWindowInsetsAnimationControlListener;
    private WindowInsetsController mWindowInsetsController;
    private boolean useCustomAnimationCallback;

    public SeslImmersiveScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mNeedToCheckBottomViewMargin = false;
        this.mCurOffset = 0.0f;
        this.mCanImmersiveScroll = true;
        this.mWindowInsetsController = null;
        this.mOnInsetsChangedListener = null;
        this.useCustomAnimationCallback = false;
        this.mCustomWindowInsetsAnimation = null;
        this.mNeedRestoreAnim = true;
        this.mIsSetAutoRestore = true;
        this.isRoundedCornerHide = false;
        this.mCalledHideShowOnLayoutChild = false;
        this.mNeedInit = false;
        this.mAnimationHandler = new Handler(Looper.getMainLooper()) { // from class: com.google.android.material.appbar.SeslImmersiveScrollBehavior.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 100) {
                    SeslImmersiveScrollBehavior.this.startRestoreAnimation();
                }
            }
        };
        this.mOffsetChangedListener = new AppBarLayout.OnOffsetChangedListener() { // from class: com.google.android.material.appbar.SeslImmersiveScrollBehavior.2
            /* JADX WARN: Removed duplicated region for block: B:92:0x0289  */
            @Override // com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener, com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onOffsetChanged(com.google.android.material.appbar.AppBarLayout r18, int r19) {
                /*
                    Method dump skipped, instruction units count: 1001
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.SeslImmersiveScrollBehavior.AnonymousClass2.onOffsetChanged(com.google.android.material.appbar.AppBarLayout, int):void");
            }
        };
        this.mWindowInsetsAnimationControlListener = new WindowInsetsAnimationControlListener() { // from class: com.google.android.material.appbar.SeslImmersiveScrollBehavior.5
            @Override // android.view.WindowInsetsAnimationControlListener
            public void onCancelled(WindowInsetsAnimationController windowInsetsAnimationController) {
                SeslImmersiveScrollBehavior.this.cancelWindowInsetsAnimationController();
            }

            @Override // android.view.WindowInsetsAnimationControlListener
            public void onFinished(WindowInsetsAnimationController windowInsetsAnimationController) {
                SeslImmersiveScrollBehavior.this.resetWindowInsetsAnimationController();
            }

            @Override // android.view.WindowInsetsAnimationControlListener
            public void onReady(WindowInsetsAnimationController windowInsetsAnimationController, int i5) {
                if (SeslImmersiveScrollBehavior.this.mDecorView != null) {
                    SeslImmersiveScrollBehavior.this.mCancellationSignal = null;
                    SeslImmersiveScrollBehavior.this.mAnimationController = windowInsetsAnimationController;
                    SeslImmersiveScrollBehavior.this.setInsetsAndAlphaToDefault();
                }
            }
        };
        this.mWindowAnimationCallback = new WindowInsetsAnimation.Callback(1) { // from class: com.google.android.material.appbar.SeslImmersiveScrollBehavior.6
            @Override // android.view.WindowInsetsAnimation.Callback
            public void onEnd(WindowInsetsAnimation windowInsetsAnimation) {
                super.onEnd(windowInsetsAnimation);
                if (SeslImmersiveScrollBehavior.this.mContentView == null || SeslImmersiveScrollBehavior.this.mAppBarLayout.isDetachedState()) {
                    return;
                }
                SeslImmersiveScrollBehavior seslImmersiveScrollBehavior = SeslImmersiveScrollBehavior.this;
                seslImmersiveScrollBehavior.mDecorViewInset = seslImmersiveScrollBehavior.mContentView.getRootWindowInsets();
                if (SeslImmersiveScrollBehavior.this.mDecorViewInset != null) {
                    SeslImmersiveScrollBehavior.this.mContentView.dispatchApplyWindowInsets(SeslImmersiveScrollBehavior.this.mDecorViewInset);
                }
            }

            @Override // android.view.WindowInsetsAnimation.Callback
            public WindowInsets onProgress(WindowInsets windowInsets, List<WindowInsetsAnimation> list) {
                return windowInsets;
            }
        };
        this.mContext = context;
        updateSystemBarsHeight();
        updateAppBarHeightProportion();
    }

    private void animateRestoreTopAndBottom(final CoordinatorLayout coordinatorLayout, final AppBarLayout appBarLayout, int i5) {
        this.mPrevOffset = i5;
        PathInterpolator pathInterpolator = new PathInterpolator(0.17f, 0.17f, 0.2f, 1.0f);
        float fSeslGetCollapsedHeight = (-this.mAppBarLayout.getHeight()) + this.mAppBarLayout.seslGetCollapsedHeight();
        final int[] iArr = {0};
        ValueAnimator valueAnimator = this.mOffsetAnimator;
        if (valueAnimator == null) {
            ValueAnimator valueAnimator2 = new ValueAnimator();
            this.mOffsetAnimator = valueAnimator2;
            valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.appbar.SeslImmersiveScrollBehavior.7
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator3) {
                    if (SeslImmersiveScrollBehavior.this.mTargetView == null) {
                        Log.e(SeslImmersiveScrollBehavior.TAG, "mTargetView is null");
                        return;
                    }
                    int iIntValue = ((Integer) valueAnimator3.getAnimatedValue()).intValue();
                    iArr[0] = SeslImmersiveScrollBehavior.this.mPrevOffset - iIntValue;
                    SeslImmersiveScrollBehavior.this.mTargetView.scrollBy(0, -iArr[0]);
                    SeslImmersiveScrollBehavior.this.setHeaderTopBottomOffset(coordinatorLayout, appBarLayout, iIntValue);
                    SeslImmersiveScrollBehavior.this.mPrevOffset = iIntValue;
                }
            });
        } else {
            valueAnimator.cancel();
        }
        this.mOffsetAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.appbar.SeslImmersiveScrollBehavior.8
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (SeslImmersiveScrollBehavior.this.mNavigationBarBg != null) {
                    SeslImmersiveScrollBehavior.this.mNavigationBarBg.setTranslationY(0.0f);
                }
                if (SeslImmersiveScrollBehavior.this.mAnimationController != null) {
                    SeslImmersiveScrollBehavior.this.mAnimationController.finish(true);
                }
            }
        });
        this.mOffsetAnimator.setDuration(150L);
        this.mOffsetAnimator.setInterpolator(pathInterpolator);
        this.mOffsetAnimator.setStartDelay(0L);
        this.mOffsetAnimator.setIntValues(this.mNeedRestoreAnim ? -this.mAppBarLayout.getHeight() : (int) fSeslGetCollapsedHeight, (int) fSeslGetCollapsedHeight);
        this.mOffsetAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean canImmersiveScroll() {
        AppBarLayout appBarLayout;
        if (this.mAppBarLayout != null && !isDexEnabled() && !this.useCustomAnimationCallback) {
            if (this.mAppBarLayout.getIsMouse()) {
                prepareImmersiveScroll(false, false);
                return false;
            }
            if (isAccessibilityEnabled()) {
                Log.i(TAG, "Disable ImmersiveScroll due to accessibility enabled");
                updateOrientationState();
                prepareImmersiveScroll(false, true);
                return false;
            }
            if (this.mAppBarLayout.seslIsActivatedImmsersiveScroll()) {
                prepareImmersiveScroll(true, false);
                boolean zUpdateOrientationState = getCurrentNavbarCanMoveState() ? updateOrientationState() : true;
                Context context = this.mContext;
                if (context != null) {
                    Activity activity = SeslContextUtils.getActivity(context);
                    if (activity == null && (appBarLayout = this.mAppBarLayout) != null) {
                        this.mContext = appBarLayout.getContext();
                        activity = SeslContextUtils.getActivity(this.mAppBarLayout.getContext());
                    }
                    if (activity != null) {
                        boolean zIsInMultiWindowMode = activity.isInMultiWindowMode();
                        if (this.mIsMultiWindow != zIsInMultiWindowMode) {
                            forceRestoreWindowInset(true);
                            cancelWindowInsetsAnimationController();
                        }
                        this.mIsMultiWindow = zIsInMultiWindowMode;
                        if (zIsInMultiWindowMode) {
                            return false;
                        }
                    }
                }
                return zUpdateOrientationState;
            }
            AppBarLayout appBarLayout2 = this.mAppBarLayout;
            if (appBarLayout2 != null && appBarLayout2.isImmersiveActivatedByUser()) {
                cancelWindowInsetsAnimationController();
            }
            prepareImmersiveScroll(false, false);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void findSystemBarsBackground() {
        View view = this.mDecorView;
        if (view == null || this.mContext == null) {
            return;
        }
        this.mDecorViewInset = view.getRootWindowInsets();
        this.mDecorView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.google.android.material.appbar.SeslImmersiveScrollBehavior.4
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                SeslImmersiveScrollBehavior.this.mDecorView.getViewTreeObserver().removeOnPreDrawListener(this);
                SeslImmersiveScrollBehavior seslImmersiveScrollBehavior = SeslImmersiveScrollBehavior.this;
                seslImmersiveScrollBehavior.mStatusBarBg = seslImmersiveScrollBehavior.mDecorView.findViewById(R.id.statusBarBackground);
                SeslImmersiveScrollBehavior seslImmersiveScrollBehavior2 = SeslImmersiveScrollBehavior.this;
                seslImmersiveScrollBehavior2.mNavigationBarBg = seslImmersiveScrollBehavior2.mDecorView.findViewById(R.id.navigationBarBackground);
                return false;
            }
        });
        updateSystemBarsHeight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishWindowInsetsAnimationController() {
        AppBarLayout appBarLayout = this.mAppBarLayout;
        if (appBarLayout == null) {
            return;
        }
        WindowInsetsAnimationController windowInsetsAnimationController = this.mAnimationController;
        if (this.mContentView == null) {
            View rootView = appBarLayout.getRootView();
            this.mDecorView = rootView;
            this.mContentView = rootView.findViewById(R.id.content);
        }
        if (windowInsetsAnimationController == null) {
            CancellationSignal cancellationSignal = this.mCancellationSignal;
            if (cancellationSignal != null) {
                cancellationSignal.cancel();
                return;
            }
            return;
        }
        int i5 = windowInsetsAnimationController.getCurrentInsets().bottom;
        int i7 = windowInsetsAnimationController.getShownStateInsets().bottom;
        int i9 = windowInsetsAnimationController.getHiddenStateInsets().bottom;
        if (i5 == i7) {
            windowInsetsAnimationController.finish(true);
        } else if (i5 == i9) {
            windowInsetsAnimationController.finish(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void forceHideRoundedCorner(int i5) {
        WindowInsetsAnimationController windowInsetsAnimationController = this.mAnimationController;
        if (windowInsetsAnimationController == null || this.mDecorView == null) {
            return;
        }
        boolean z9 = i5 != windowInsetsAnimationController.getShownStateInsets().bottom;
        if (z9 != this.isRoundedCornerHide) {
            this.isRoundedCornerHide = z9;
            View view = this.mDecorView;
            Method methodT = com.bumptech.glide.c.t(view.getClass(), "hidden_semSetForceHideRoundedCorner", Boolean.TYPE);
            if (methodT != null) {
                com.bumptech.glide.c.C(view, methodT, Boolean.valueOf(z9));
            }
        }
    }

    private boolean getCurrentNavbarCanMoveState() {
        try {
            return this.mContext.getApplicationContext().getResources().getBoolean(Resources.getSystem().getIdentifier("config_navBarCanMove", "bool", "android"));
        } catch (Exception e3) {
            Log.e(TAG, "ERROR, e : " + e3.getMessage());
            return true;
        }
    }

    private boolean isAccessibilityEnabled() {
        Context context = this.mContext;
        if (context == null) {
            return false;
        }
        return ((AccessibilityManager) context.getSystemService("accessibility")).isTouchExplorationEnabled();
    }

    private boolean isDexEnabled() {
        Context context = this.mContext;
        if (context == null) {
            return false;
        }
        return s6.c.M(context.getResources().getConfiguration());
    }

    public static boolean isGestureNavigateEnabled(Context context) {
        int integer = context.getResources().getInteger(Resources.getSystem().getIdentifier("config_navBarInteractionMode", "integer", "android"));
        return integer == 2 || integer == 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isHideCameraCutout(WindowInsets windowInsets) {
        return windowInsets.getDisplayCutout() == null && windowInsets.getInsets(WindowInsets.Type.systemBars()).top == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isLandscape() {
        AppBarLayout appBarLayout = this.mAppBarLayout;
        return appBarLayout != null && appBarLayout.getCurrentOrientation() == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isNavigationBarBottomPosition() {
        if (this.mDecorViewInset == null) {
            if (this.mDecorView == null) {
                this.mDecorView = this.mAppBarLayout.getRootView();
            }
            this.mDecorViewInset = this.mDecorView.getRootWindowInsets();
        }
        WindowInsets windowInsets = this.mDecorViewInset;
        return windowInsets == null || windowInsets.getInsets(WindowInsets.Type.navigationBars()).bottom != 0;
    }

    private boolean isTouchInGestureNavigationArea(float f2, WindowInsets windowInsets) {
        if (this.mDecorView == null) {
            return false;
        }
        int i5 = windowInsets.getInsets(WindowInsets.Type.mandatorySystemGestures()).bottom;
        Rect rect = new Rect();
        this.mDecorView.getWindowVisibleDisplayFrame(rect);
        return f2 > ((float) (rect.bottom - i5));
    }

    private void prepareImmersiveScroll(boolean z9, boolean z10) {
        if (this.mCanImmersiveScroll != z9) {
            this.mCanImmersiveScroll = z9;
            forceRestoreWindowInset(z10);
            setupDecorsFitSystemWindowState(z9);
            if (z9 != this.mAppBarLayout.getCanScroll()) {
                this.mAppBarLayout.setCanScroll(z9);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetWindowInsetsAnimationController() {
        this.mAnimationController = null;
        this.mCancellationSignal = null;
        this.mShownAtDown = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setInsetsAndAlphaToDefault() {
        /*
            r6 = this;
            android.content.Context r0 = r6.mContext
            boolean r0 = com.google.android.material.internal.SeslDisplayUtils.isPinEdgeEnabled(r0)
            r1 = 0
            if (r0 == 0) goto L31
            android.view.WindowInsets r0 = r6.mDecorViewInset
            int r2 = android.view.WindowInsets.Type.navigationBars()
            android.graphics.Insets r0 = r0.getInsets(r2)
            android.content.Context r2 = r6.mContext
            int r2 = com.google.android.material.internal.SeslDisplayUtils.getPinnedEdgeWidth(r2)
            android.content.Context r3 = r6.mContext
            int r3 = com.google.android.material.internal.SeslDisplayUtils.getEdgeArea(r3)
            int r4 = r0.left
            if (r2 != r4) goto L29
            if (r3 != 0) goto L29
            r5 = r2
            r2 = r1
            r1 = r5
            goto L32
        L29:
            int r0 = r0.right
            if (r2 != r0) goto L31
            r0 = 1
            if (r3 != r0) goto L31
            goto L32
        L31:
            r2 = r1
        L32:
            int r0 = r6.mStatusBarHeight
            float r0 = (float) r0
            int r3 = r6.mNavigationBarHeight
            float r3 = (float) r3
            android.view.WindowInsetsAnimationController r6 = r6.mAnimationController
            int r0 = (int) r0
            int r3 = (int) r3
            android.graphics.Insets r0 = android.graphics.Insets.of(r1, r0, r2, r3)
            r1 = 1065353216(0x3f800000, float:1.0)
            r6.setInsetsAndAlpha(r0, r1, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.SeslImmersiveScrollBehavior.setInsetsAndAlphaToDefault():void");
    }

    private void setWindowInsetsController() {
        View view = this.mDecorView;
        if (view != null && this.mAnimationController == null && this.mWindowInsetsController == null) {
            this.mWindowInsetsController = view.getWindowInsetsController();
        }
    }

    private void setupDecorsFitSystemWindowState(boolean z9) {
        AppBarLayout appBarLayout;
        int i5;
        AppBarLayout appBarLayout2;
        if (this.mDecorView == null || (appBarLayout = this.mAppBarLayout) == null || this.useCustomAnimationCallback) {
            return;
        }
        if (this.mContext == null) {
            Context context = appBarLayout.getContext();
            this.mContext = context;
            if (context == null) {
                return;
            }
        }
        Activity activity = SeslContextUtils.getActivity(this.mContext);
        if (activity == null && (appBarLayout2 = this.mAppBarLayout) != null) {
            this.mContext = appBarLayout2.getContext();
            activity = SeslContextUtils.getActivity(this.mAppBarLayout.getContext());
        }
        if (activity != null) {
            Window window = activity.getWindow();
            if (z9) {
                WindowInsets windowInsets = this.mDecorViewInset;
                if (windowInsets == null || !isHideCameraCutout(windowInsets)) {
                    this.mAppBarLayout.setImmersiveTopInset(this.mStatusBarHeight);
                } else {
                    this.mAppBarLayout.setImmersiveTopInset(0);
                }
                window.setDecorFitsSystemWindows(false);
                window.getDecorView().setFitsSystemWindows(false);
                WindowInsets windowInsets2 = this.mDecorViewInset;
                if (windowInsets2 == null || (i5 = windowInsets2.getInsets(WindowInsets.Type.statusBars()).top) == 0 || i5 == this.mStatusBarHeight) {
                    return;
                }
                this.mStatusBarHeight = i5;
                this.mAppBarLayout.setImmersiveTopInset(i5);
                return;
            }
            this.mAppBarLayout.setImmersiveTopInset(0);
            window.setDecorFitsSystemWindows(true);
            window.getDecorView().setFitsSystemWindows(true);
            if (isNavigationBarBottomPosition() || !isLandscape()) {
                return;
            }
            if (this.mWindowInsetsController == null) {
                setWindowInsetsController();
            }
            WindowInsets rootWindowInsets = this.mDecorView.getRootWindowInsets();
            this.mDecorViewInset = rootWindowInsets;
            if (this.mWindowInsetsController == null || rootWindowInsets == null || rootWindowInsets.getInsets(WindowInsets.Type.statusBars()).top == 0) {
                return;
            }
            try {
                this.mWindowInsetsController.hide(WindowInsets.Type.statusBars());
            } catch (IllegalStateException unused) {
                Log.w(TAG, "setupDecorsFitSystemWindowState: mWindowInsetsController.hide failed!");
            }
        }
    }

    private void startAnimationControlRequest() {
        setWindowInsetsController();
        if (this.mCancellationSignal == null) {
            this.mCancellationSignal = new CancellationSignal();
        }
        int iSystemBars = WindowInsets.Type.systemBars();
        if (!isHideCameraCutout(this.mDecorViewInset)) {
            try {
                this.mWindowInsetsController.hide(iSystemBars);
            } catch (IllegalStateException unused) {
                Log.w(TAG, "startAnimationControlRequest: mWindowInsetsController.hide failed!");
            }
        }
        this.mWindowInsetsController.setSystemBarsBehavior(2);
        this.mWindowInsetsController.controlWindowInsetsAnimation(iSystemBars, -1L, null, this.mCancellationSignal, this.mWindowInsetsAnimationControlListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startRestoreAnimation() {
        if (isAppBarHide()) {
            animateRestoreTopAndBottom(this.mCoordinatorLayout, this.mAppBarLayout, -this.mAppBarLayout.getUpNestedPreScrollRange());
        }
    }

    private void updateAppBarHeightProportion() {
        AppBarLayout appBarLayout = this.mAppBarLayout;
        if (appBarLayout == null) {
            return;
        }
        if (this.mContext == null) {
            Context context = appBarLayout.getContext();
            this.mContext = context;
            if (context == null) {
                return;
            }
        }
        Resources resources = this.mContext.getResources();
        float appBarProPortion = SeslAppBarHelper.INSTANCE.getAppBarProPortion(this.mContext);
        float f2 = 0.0f;
        if (appBarProPortion != 0.0f) {
            f2 = (this.mStatusBarHeight / resources.getDisplayMetrics().heightPixels) + appBarProPortion;
        }
        if (this.mCanImmersiveScroll) {
            this.mAppBarLayout.internalProportion(f2);
        } else {
            this.mAppBarLayout.internalProportion(appBarProPortion);
        }
    }

    private boolean updateOrientationState() {
        AppBarLayout appBarLayout = this.mAppBarLayout;
        if (appBarLayout == null) {
            return false;
        }
        int currentOrientation = appBarLayout.getCurrentOrientation();
        if (this.mPrevOrientation != currentOrientation) {
            this.mPrevOrientation = currentOrientation;
            forceRestoreWindowInset(true);
            this.mCalledHideShowOnLayoutChild = false;
        }
        if (currentOrientation == 1) {
            return true;
        }
        if (currentOrientation == 2) {
            return false;
        }
        Log.e(TAG, "ERROR, e : AppbarLayout Configuration is wrong");
        return false;
    }

    private void updateSystemBarsHeight() {
        Context context = this.mContext;
        if (context == null) {
            return;
        }
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            this.mStatusBarHeight = resources.getDimensionPixelSize(identifier);
        }
        int identifier2 = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier2 > 0) {
            this.mNavigationBarHeight = resources.getDimensionPixelSize(identifier2);
        }
        View view = this.mDecorView;
        if (view != null) {
            WindowInsets rootWindowInsets = view.getRootWindowInsets();
            this.mDecorViewInset = rootWindowInsets;
            if (rootWindowInsets != null) {
                this.mNavigationBarHeight = rootWindowInsets.getInsets(WindowInsets.Type.navigationBars()).bottom;
            }
        }
    }

    public void cancelWindowInsetsAnimationController() {
        View view = this.mDecorView;
        if (view != null) {
            WindowInsets rootWindowInsets = view.getRootWindowInsets();
            this.mDecorViewInset = rootWindowInsets;
            if (rootWindowInsets != null) {
                this.mShownAtDown = rootWindowInsets.isVisible(WindowInsets.Type.statusBars()) || this.mDecorViewInset.isVisible(WindowInsets.Type.navigationBars());
            }
        }
        WindowInsetsAnimationController windowInsetsAnimationController = this.mAnimationController;
        if (windowInsetsAnimationController != null) {
            windowInsetsAnimationController.finish(this.mShownAtDown);
        }
        CancellationSignal cancellationSignal = this.mCancellationSignal;
        if (cancellationSignal != null) {
            cancellationSignal.cancel();
        }
        resetWindowInsetsAnimationController();
    }

    @Override // androidx.coordinatorlayout.widget.d
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        boolean z9 = motionEvent.getToolType(0) == 3;
        if (this.mToolIsMouse != z9) {
            this.mToolIsMouse = z9;
            AppBarLayout appBarLayout = this.mAppBarLayout;
            if (appBarLayout != null) {
                appBarLayout.seslSetIsMouse(z9);
                dispatchImmersiveScrollEnabled();
            }
        }
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    public boolean dispatchImmersiveScrollEnabled() {
        AppBarLayout appBarLayout = this.mAppBarLayout;
        if (appBarLayout == null || appBarLayout.isDetachedState()) {
            return false;
        }
        boolean zCanImmersiveScroll = canImmersiveScroll();
        setupDecorsFitSystemWindowState(zCanImmersiveScroll);
        updateAppBarHeightProportion();
        updateSystemBarsHeight();
        return zCanImmersiveScroll;
    }

    public void forceRestoreWindowInset(boolean z9) {
        if (this.mWindowInsetsController != null) {
            WindowInsets rootWindowInsets = this.mDecorView.getRootWindowInsets();
            this.mDecorViewInset = rootWindowInsets;
            if (rootWindowInsets != null) {
                boolean zIsVisible = rootWindowInsets.isVisible(WindowInsets.Type.statusBars());
                boolean zIsVisible2 = this.mDecorViewInset.isVisible(WindowInsets.Type.navigationBars());
                if (!zIsVisible || !zIsVisible2 || isAppBarHide() || z9) {
                    try {
                        this.mWindowInsetsController.show(WindowInsets.Type.systemBars());
                    } catch (IllegalStateException unused) {
                        Log.w(TAG, "forceRestoreWindowInset: mWindowInsetsController.show failed!");
                    }
                }
            }
        }
    }

    public boolean getCanImmersiveScrollState() {
        return this.mCanImmersiveScroll;
    }

    public void initImmViews(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout) {
        Log.d(TAG, "initImmViews mNeedInit=" + this.mNeedInit);
        int i5 = 0;
        this.mNeedInit = false;
        this.mCanImmersiveScroll = false;
        this.mAppBarLayout = appBarLayout;
        this.mCoordinatorLayout = coordinatorLayout;
        appBarLayout.addOnOffsetChangedListener(this.mOffsetChangedListener);
        if (!this.mAppBarLayout.isImmersiveActivatedByUser() && !isDexEnabled()) {
            this.mAppBarLayout.internalActivateImmersiveScroll(true, false);
        }
        View rootView = this.mAppBarLayout.getRootView();
        this.mDecorView = rootView;
        View viewFindViewById = rootView.findViewById(R.id.content);
        this.mContentView = viewFindViewById;
        if (this.useCustomAnimationCallback) {
            viewFindViewById.setWindowInsetsAnimationCallback(this.mCustomWindowInsetsAnimation);
        } else {
            viewFindViewById.setWindowInsetsAnimationCallback(this.mWindowAnimationCallback);
        }
        findSystemBarsBackground();
        dispatchImmersiveScrollEnabled();
        while (true) {
            if (i5 >= appBarLayout.getChildCount()) {
                break;
            }
            View childAt = appBarLayout.getChildAt(i5);
            if (this.mCollapsingToolbarLayout != null) {
                break;
            }
            if (childAt instanceof CollapsingToolbarLayout) {
                this.mCollapsingToolbarLayout = (CollapsingToolbarLayout) childAt;
                break;
            }
            i5++;
        }
        View viewFindViewById2 = coordinatorLayout.findViewById(com.google.android.material.R.id.bottom_bar_overlay);
        if (this.mBottomArea == null || viewFindViewById2 != null) {
            this.mBottomArea = viewFindViewById2;
        }
    }

    public boolean isAppBarHide() {
        if (this.mAppBarLayout != null) {
            if (this.mAppBarLayout.getPaddingBottom() + r0.getBottom() < this.mAppBarLayout.seslGetCollapsedHeight()) {
                return true;
            }
        }
        return false;
    }

    public void notifyOnApplyWindowInsets() {
        if (this.mAppBarLayout != null) {
            cancelWindowInsetsAnimationController();
            updateSystemBarsHeight();
            this.mAppBarLayout.onOffsetChanged(getTopAndBottomOffset());
        }
    }

    public void notifyOnDetachedFromWindow() {
        Log.i(TAG, "DetachedFromWindow");
        WindowInsetsController.OnControllableInsetsChangedListener onControllableInsetsChangedListener = this.mOnInsetsChangedListener;
        if (onControllableInsetsChangedListener != null) {
            this.mWindowInsetsController.removeOnControllableInsetsChangedListener(onControllableInsetsChangedListener);
            this.mOnInsetsChangedListener = null;
        }
        resetWindowInsetsAnimationController();
    }

    public void release() {
        String str = TAG;
        Log.d(str, "release");
        if (this.mAnimationHandler.hasMessages(100)) {
            Log.d(str, "release removeMessages");
            this.mAnimationHandler.removeMessages(100);
        }
        View view = this.mContentView;
        if (view != null) {
            view.setWindowInsetsAnimationCallback(null);
        }
        this.mTargetView = null;
        this.mNeedInit = true;
    }

    public void restoreTopAndBottom(boolean z9) {
        AppBarLayout appBarLayout;
        Log.i(TAG, " Restore top and bottom areas [Animate] " + z9);
        this.mNeedRestoreAnim = z9;
        if (this.mAppBarLayout != null && isAppBarHide()) {
            if (this.mAnimationHandler.hasMessages(100)) {
                this.mAnimationHandler.removeMessages(100);
            }
            this.mAnimationHandler.sendEmptyMessageDelayed(100, 100L);
        }
        if (this.mBottomArea == null || this.mNavigationBarBg == null || this.mAnimationHandler.hasMessages(100) || (appBarLayout = this.mAppBarLayout) == null || appBarLayout.seslIsActivatedImmsersiveScroll()) {
            return;
        }
        this.mBottomArea.setTranslationY(0.0f);
    }

    public void setAutoRestoreTopAndBottom(boolean z9) {
        this.mIsSetAutoRestore = z9;
    }

    public void setBottomView(View view) {
        this.mBottomArea = view;
    }

    public void setNeedToCheckBottomViewMargin(boolean z9) {
        this.mNeedToCheckBottomViewMargin = z9;
    }

    public void setWindowInsetsAnimationCallback(AppBarLayout appBarLayout, WindowInsetsAnimation.Callback callback) {
        if (this.mContentView == null) {
            View rootView = appBarLayout.getRootView();
            this.mDecorView = rootView;
            this.mContentView = rootView.findViewById(R.id.content);
        }
        if (callback == null) {
            this.useCustomAnimationCallback = false;
        } else {
            this.mCustomWindowInsetsAnimation = callback;
            this.useCustomAnimationCallback = true;
        }
        if (!this.useCustomAnimationCallback) {
            this.mContentView.setPadding(0, 0, 0, 0);
            this.mContentView.setWindowInsetsAnimationCallback(this.mWindowAnimationCallback);
            return;
        }
        this.mContentView.setWindowInsetsAnimationCallback(this.mCustomWindowInsetsAnimation);
        prepareImmersiveScroll(false, false);
        View view = this.mBottomArea;
        if (view != null) {
            view.setTranslationY(0.0f);
        }
    }

    public void setupDecorFitsSystemWindow() {
        Log.i(TAG, "fits system window Immersive detached");
        Activity activity = SeslContextUtils.getActivity(this.mContext);
        if (activity != null && this.mAppBarLayout != null) {
            activity.getWindow().setDecorFitsSystemWindows(true);
            View view = this.mBottomArea;
            if (view != null) {
                view.setTranslationY(0.0f);
            }
        }
        View view2 = this.mStatusBarBg;
        if (view2 == null || view2.getTranslationY() == 0.0f) {
            return;
        }
        this.mStatusBarBg.setTranslationY(0.0f);
    }

    @Override // com.google.android.material.appbar.ViewOffsetBehavior
    public void layoutChild(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i5) {
        super.layoutChild(coordinatorLayout, appBarLayout, i5);
        if (this.mWindowInsetsController != null && this.mOnInsetsChangedListener == null) {
            WindowInsetsController.OnControllableInsetsChangedListener onControllableInsetsChangedListener = new WindowInsetsController.OnControllableInsetsChangedListener() { // from class: com.google.android.material.appbar.SeslImmersiveScrollBehavior.3
                @Override // android.view.WindowInsetsController.OnControllableInsetsChangedListener
                public void onControllableInsetsChanged(WindowInsetsController windowInsetsController, int i7) {
                    if (SeslImmersiveScrollBehavior.this.isLandscape() && !SeslImmersiveScrollBehavior.this.isNavigationBarBottomPosition() && !SeslImmersiveScrollBehavior.this.mCalledHideShowOnLayoutChild) {
                        windowInsetsController.hide(WindowInsets.Type.navigationBars());
                        windowInsetsController.show(WindowInsets.Type.navigationBars());
                        windowInsetsController.setSystemBarsBehavior(2);
                        SeslImmersiveScrollBehavior.this.mCalledHideShowOnLayoutChild = true;
                    }
                    if (SeslImmersiveScrollBehavior.this.mIsSetAutoRestore && i7 == 8) {
                        SeslImmersiveScrollBehavior seslImmersiveScrollBehavior = SeslImmersiveScrollBehavior.this;
                        seslImmersiveScrollBehavior.mDecorViewInset = seslImmersiveScrollBehavior.mDecorView.getRootWindowInsets();
                        if (SeslImmersiveScrollBehavior.this.mDecorViewInset != null && SeslImmersiveScrollBehavior.this.mDecorViewInset.isVisible(WindowInsets.Type.statusBars()) && SeslImmersiveScrollBehavior.this.isAppBarHide()) {
                            SeslImmersiveScrollBehavior.this.restoreTopAndBottom(true);
                        }
                    }
                }
            };
            this.mOnInsetsChangedListener = onControllableInsetsChangedListener;
            this.mWindowInsetsController.addOnControllableInsetsChangedListener(onControllableInsetsChangedListener);
        }
        AppBarLayout appBarLayout2 = this.mAppBarLayout;
        if (appBarLayout2 == null || appBarLayout != appBarLayout2 || this.mNeedInit) {
            initImmViews(coordinatorLayout, appBarLayout);
        }
    }

    @Override // com.google.android.material.appbar.AppBarLayout.Behavior, com.google.android.material.appbar.HeaderBehavior, androidx.coordinatorlayout.widget.d
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, MotionEvent motionEvent) {
        int toolType = motionEvent.getToolType(0);
        if (toolType == 0) {
            return super.onInterceptTouchEvent(coordinatorLayout, (View) appBarLayout, motionEvent);
        }
        boolean z9 = toolType == 3;
        if (this.mToolIsMouse != z9) {
            this.mToolIsMouse = z9;
            appBarLayout.seslSetIsMouse(z9);
        }
        return super.onInterceptTouchEvent(coordinatorLayout, (View) appBarLayout, motionEvent);
    }

    @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior, androidx.coordinatorlayout.widget.d
    public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, int i5, int i7, int i9, int i10) {
        dispatchImmersiveScrollEnabled();
        return super.onMeasureChild(coordinatorLayout, appBarLayout, i5, i7, i9, i10);
    }

    @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior, androidx.coordinatorlayout.widget.d
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i5, int i7, int[] iArr, int i9) {
        this.mTargetView = view;
        if (this.mCancellationSignal == null) {
            super.onNestedPreScroll(coordinatorLayout, appBarLayout, view, i5, i7, iArr, i9);
        } else {
            iArr[0] = i5;
            iArr[1] = i7;
        }
    }

    @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior, androidx.coordinatorlayout.widget.d
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i5, int i7, int i9, int i10, int i11, int[] iArr) {
        this.mTargetView = view;
        super.onNestedScroll(coordinatorLayout, appBarLayout, view, i5, i7, i9, i10, i11, iArr);
    }

    @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior, androidx.coordinatorlayout.widget.d
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, View view2, int i5, int i7) {
        this.mTargetView = view2;
        if (dispatchImmersiveScrollEnabled() && this.mAnimationController == null) {
            startAnimationControlRequest();
        }
        return super.onStartNestedScroll(coordinatorLayout, appBarLayout, view, view2, i5, i7);
    }

    @Override // com.google.android.material.appbar.AppBarLayout.BaseBehavior, androidx.coordinatorlayout.widget.d
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i5) {
        this.mTargetView = view;
        super.onStopNestedScroll(coordinatorLayout, appBarLayout, view, i5);
    }
}
