package com.google.android.material.snackbar;

import L.l;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.coordinatorlayout.widget.g;
import androidx.core.view.C0210b;
import androidx.core.view.InterfaceC0226s;
import androidx.core.view.K;
import androidx.core.view.M;
import androidx.core.view.W;
import androidx.core.view.w0;
import androidx.dynamicanimation.animation.d;
import androidx.dynamicanimation.animation.e;
import androidx.dynamicanimation.animation.f;
import androidx.dynamicanimation.animation.h;
import androidx.dynamicanimation.animation.i;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.behavior.SwipeDismissBehavior;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.internal.WindowUtils;
import com.google.android.material.math.MathUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.SnackbarManager;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.samsung.android.honeyboard.forms.model.FieldName;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseTransientBottomBar<B extends BaseTransientBottomBar<B>> {
    static final int ANIMATION_MODE_FADE = 1;
    static final int ANIMATION_MODE_SLIDE = 0;
    static final int ANIMATION_MODE_SUGGESTIVE = 2;
    private static final float ANIMATION_SCALE_FROM_VALUE = 0.2f;
    static final int DEFAULT_ANIMATION_FADE_DURATION = 180;
    private static final int DEFAULT_ANIMATION_FADE_IN_DURATION = 180;
    private static final int DEFAULT_ANIMATION_FADE_OUT_DURATION = 180;
    static final int DEFAULT_SLIDE_ANIMATION_DURATION = 250;
    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_LONG = 0;
    public static final int LENGTH_SHORT = -1;
    static final int MSG_DISMISS = 1;
    static final int MSG_SHOW = 0;
    static int TRANSITION_HEIGHT;
    private final AccessibilityManager accessibilityManager;
    private Anchor anchor;
    private boolean anchorViewLayoutListenerEnabled;
    private final int animationFadeInDuration;
    private final TimeInterpolator animationFadeInterpolator;
    private final int animationFadeOutDuration;
    private final TimeInterpolator animationScaleInterpolator;
    private final int animationSlideDuration;
    private final TimeInterpolator animationSlideInterpolator;
    private int appliedBottomMarginGestureInset;
    private Behavior behavior;
    private final Runnable bottomMarginGestureInsetRunnable;
    private List<BaseCallback<B>> callbacks;
    private final com.google.android.material.snackbar.ContentViewCallback contentViewCallback;
    private final Context context;
    private int duration;
    private int extraBottomMarginAnchorView;
    private int extraBottomMarginGestureInset;
    private int extraBottomMarginWindowInset;
    private int extraLeftMarginWindowInset;
    private int extraRightMarginWindowInset;
    private boolean gestureInsetBottomIgnored;
    SnackbarManager.Callback managerCallback;
    private boolean pendingShowingView;
    private final ViewGroup targetParent;
    protected final SnackbarBaseLayout view;
    private static final TimeInterpolator DEFAULT_ANIMATION_SLIDE_INTERPOLATOR = AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
    private static final TimeInterpolator DEFAULT_ANIMATION_FADE_INTERPOLATOR = AnimationUtils.LINEAR_INTERPOLATOR;
    private static final TimeInterpolator DEFAULT_ANIMATION_SCALE_INTERPOLATOR = AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR;
    private static final boolean USE_OFFSET_API = false;
    private static final int[] SNACKBAR_STYLE_ATTR = {R.attr.snackbarStyle};
    private static final String TAG = "BaseTransientBottomBar";
    static final Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.1
        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i5 = message.what;
            if (i5 == 0) {
                ((BaseTransientBottomBar) message.obj).showView();
                return true;
            }
            if (i5 != 1) {
                return false;
            }
            ((BaseTransientBottomBar) message.obj).hideView(message.arg1);
            return true;
        }
    });

    public static class Anchor implements View.OnAttachStateChangeListener, ViewTreeObserver.OnGlobalLayoutListener {
        private final WeakReference<View> anchorView;
        private final WeakReference<BaseTransientBottomBar> transientBottomBar;

        private Anchor(BaseTransientBottomBar baseTransientBottomBar, View view) {
            this.transientBottomBar = new WeakReference<>(baseTransientBottomBar);
            this.anchorView = new WeakReference<>(view);
        }

        public static Anchor anchor(BaseTransientBottomBar baseTransientBottomBar, View view) {
            Anchor anchor = new Anchor(baseTransientBottomBar, view);
            WeakHashMap weakHashMap = W.f7199a;
            if (view.isAttachedToWindow()) {
                ViewUtils.addOnGlobalLayoutListener(view, anchor);
            }
            view.addOnAttachStateChangeListener(anchor);
            return anchor;
        }

        private boolean unanchorIfNoTransientBottomBar() {
            if (this.transientBottomBar.get() != null) {
                return false;
            }
            unanchor();
            return true;
        }

        public View getAnchorView() {
            return this.anchorView.get();
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (unanchorIfNoTransientBottomBar() || !this.transientBottomBar.get().anchorViewLayoutListenerEnabled) {
                return;
            }
            this.transientBottomBar.get().recalculateAndUpdateMargins();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
            if (unanchorIfNoTransientBottomBar()) {
                return;
            }
            ViewUtils.addOnGlobalLayoutListener(view, this);
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            if (unanchorIfNoTransientBottomBar()) {
                return;
            }
            ViewUtils.removeOnGlobalLayoutListener(view, this);
        }

        public void unanchor() {
            if (this.anchorView.get() != null) {
                this.anchorView.get().removeOnAttachStateChangeListener(this);
                ViewUtils.removeOnGlobalLayoutListener(this.anchorView.get(), this);
            }
            this.anchorView.clear();
            this.transientBottomBar.clear();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface AnimationMode {
    }

    public static abstract class BaseCallback<B> {
        public static final int DISMISS_EVENT_ACTION = 1;
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;
        public static final int DISMISS_EVENT_MANUAL = 3;
        public static final int DISMISS_EVENT_SWIPE = 0;
        public static final int DISMISS_EVENT_TIMEOUT = 2;

        @Retention(RetentionPolicy.SOURCE)
        public @interface DismissEvent {
        }

        public void onDismissed(B b3, int i5) {
        }

        public void onShown(B b3) {
        }
    }

    public static class Behavior extends SwipeDismissBehavior<View> {
        private final BehaviorDelegate delegate = new BehaviorDelegate(this);

        /* JADX INFO: Access modifiers changed from: private */
        public void setBaseTransientBottomBar(BaseTransientBottomBar<?> baseTransientBottomBar) {
            this.delegate.setBaseTransientBottomBar(baseTransientBottomBar);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior
        public boolean canSwipeDismissView(View view) {
            return this.delegate.canSwipeDismissView(view);
        }

        @Override // com.google.android.material.behavior.SwipeDismissBehavior, androidx.coordinatorlayout.widget.d
        public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            this.delegate.onInterceptTouchEvent(coordinatorLayout, view, motionEvent);
            return super.onInterceptTouchEvent(coordinatorLayout, view, motionEvent);
        }
    }

    public static class BehaviorDelegate {
        private SnackbarManager.Callback managerCallback;

        public BehaviorDelegate(SwipeDismissBehavior<?> swipeDismissBehavior) {
            swipeDismissBehavior.setStartAlphaSwipeDistance(0.1f);
            swipeDismissBehavior.setEndAlphaSwipeDistance(0.6f);
            swipeDismissBehavior.setSwipeDirection(0);
        }

        public boolean canSwipeDismissView(View view) {
            return view instanceof SnackbarBaseLayout;
        }

        public void onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
            int actionMasked = motionEvent.getActionMasked();
            if (actionMasked == 0) {
                if (coordinatorLayout.isPointInChildBounds(view, (int) motionEvent.getX(), (int) motionEvent.getY())) {
                    SnackbarManager.getInstance().pauseTimeout(this.managerCallback);
                }
            } else if (actionMasked == 1 || actionMasked == 3) {
                SnackbarManager.getInstance().restoreTimeoutIfPaused(this.managerCallback);
            }
        }

        public void setBaseTransientBottomBar(BaseTransientBottomBar<?> baseTransientBottomBar) {
            this.managerCallback = baseTransientBottomBar.managerCallback;
        }
    }

    @Deprecated
    public interface ContentViewCallback extends com.google.android.material.snackbar.ContentViewCallback {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    public static class SnackbarBaseLayout extends FrameLayout {
        private static final View.OnTouchListener consumeAllTouchListener = new View.OnTouchListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout.1
            @Override // android.view.View.OnTouchListener
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (view instanceof ViewGroup) {
                    return SnackbarBaseLayout.findChildUnder((ViewGroup) view, motionEvent.getX(), motionEvent.getY()) != null;
                }
                return true;
            }
        };
        private final float actionTextColorAlpha;
        private boolean addingToTargetParent;
        private int animationMode;
        private final float backgroundOverlayColorAlpha;
        private ColorStateList backgroundTint;
        private PorterDuff.Mode backgroundTintMode;
        private BaseTransientBottomBar<?> baseTransientBottomBar;
        private final int maxInlineActionWidth;
        private final int maxWidth;
        private Rect originalMargins;
        ShapeAppearanceModel shapeAppearanceModel;

        public SnackbarBaseLayout(Context context) {
            this(context, null);
        }

        private Drawable createThemedBackground() {
            int iLayer = MaterialColors.layer(this, R.attr.colorSurface, R.attr.colorOnSurface, getBackgroundOverlayColorAlpha());
            ShapeAppearanceModel shapeAppearanceModel = this.shapeAppearanceModel;
            Drawable drawableCreateMaterialShapeDrawableBackground = shapeAppearanceModel != null ? BaseTransientBottomBar.createMaterialShapeDrawableBackground(iLayer, shapeAppearanceModel) : BaseTransientBottomBar.createGradientDrawableBackground(iLayer, getResources());
            ColorStateList colorStateList = this.backgroundTint;
            if (colorStateList != null) {
                E.a.h(drawableCreateMaterialShapeDrawableBackground, colorStateList);
            }
            return drawableCreateMaterialShapeDrawableBackground;
        }

        public static View findChildUnder(ViewGroup viewGroup, float f2, float f7) {
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                if (isChildUnder(childAt, f2, f7)) {
                    return childAt;
                }
            }
            return null;
        }

        public static boolean isChildUnder(View view, float f2, float f7) {
            float x9 = view.getX();
            float y4 = view.getY();
            return f2 >= x9 && f7 >= y4 && f2 < ((float) view.getWidth()) + x9 && f7 < ((float) view.getHeight()) + y4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setBaseTransientBottomBar(BaseTransientBottomBar<?> baseTransientBottomBar) {
            this.baseTransientBottomBar = baseTransientBottomBar;
        }

        private void updateOriginalMargins(ViewGroup.MarginLayoutParams marginLayoutParams) {
            this.originalMargins = new Rect(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
        }

        public void addToTargetParent(ViewGroup viewGroup) {
            this.addingToTargetParent = true;
            viewGroup.addView(this);
            this.addingToTargetParent = false;
        }

        public float getActionTextColorAlpha() {
            return this.actionTextColorAlpha;
        }

        public int getAnimationMode() {
            return this.animationMode;
        }

        public float getBackgroundOverlayColorAlpha() {
            return this.backgroundOverlayColorAlpha;
        }

        public int getMaxInlineActionWidth() {
            return this.maxInlineActionWidth;
        }

        public int getMaxWidth() {
            return this.maxWidth;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            BaseTransientBottomBar<?> baseTransientBottomBar = this.baseTransientBottomBar;
            if (baseTransientBottomBar != null) {
                baseTransientBottomBar.onAttachedToWindow();
            }
            WeakHashMap weakHashMap = W.f7199a;
            K.c(this);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            BaseTransientBottomBar<?> baseTransientBottomBar = this.baseTransientBottomBar;
            if (baseTransientBottomBar != null) {
                baseTransientBottomBar.onDetachedFromWindow();
            }
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
            super.onLayout(z9, i5, i7, i9, i10);
            BaseTransientBottomBar<?> baseTransientBottomBar = this.baseTransientBottomBar;
            if (baseTransientBottomBar != null) {
                baseTransientBottomBar.onLayoutChange();
            }
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int i5, int i7) {
            super.onMeasure(i5, i7);
            super.onMeasure(i5, i7);
        }

        public void setAnimationMode(int i5) {
            this.animationMode = i5;
        }

        @Override // android.view.View
        public void setBackground(Drawable drawable) {
            setBackgroundDrawable(drawable);
        }

        @Override // android.view.View
        public void setBackgroundDrawable(Drawable drawable) {
            if (drawable != null && this.backgroundTint != null) {
                drawable = drawable.mutate();
                E.a.h(drawable, this.backgroundTint);
                E.a.i(drawable, this.backgroundTintMode);
            }
            super.setBackgroundDrawable(drawable);
        }

        @Override // android.view.View
        public void setBackgroundTintList(ColorStateList colorStateList) {
            this.backgroundTint = colorStateList;
            if (getBackground() != null) {
                Drawable drawableMutate = getBackground().mutate();
                E.a.h(drawableMutate, colorStateList);
                E.a.i(drawableMutate, this.backgroundTintMode);
                if (drawableMutate != getBackground()) {
                    super.setBackgroundDrawable(drawableMutate);
                }
            }
        }

        @Override // android.view.View
        public void setBackgroundTintMode(PorterDuff.Mode mode) {
            this.backgroundTintMode = mode;
            if (getBackground() != null) {
                Drawable drawableMutate = getBackground().mutate();
                E.a.i(drawableMutate, mode);
                if (drawableMutate != getBackground()) {
                    super.setBackgroundDrawable(drawableMutate);
                }
            }
        }

        @Override // android.view.View
        public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
            super.setLayoutParams(layoutParams);
            if (this.addingToTargetParent || !(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                return;
            }
            updateOriginalMargins((ViewGroup.MarginLayoutParams) layoutParams);
            BaseTransientBottomBar<?> baseTransientBottomBar = this.baseTransientBottomBar;
            if (baseTransientBottomBar != null) {
                baseTransientBottomBar.updateMargins();
            }
        }

        @Override // android.view.View
        public void setOnClickListener(View.OnClickListener onClickListener) {
            setOnTouchListener(onClickListener != null ? null : consumeAllTouchListener);
            super.setOnClickListener(onClickListener);
        }

        public SnackbarBaseLayout(Context context, AttributeSet attributeSet) {
            super(MaterialThemeOverlay.wrap(context, attributeSet, 0, 0), attributeSet);
            Context context2 = getContext();
            TypedArray typedArrayObtainStyledAttributes = context2.obtainStyledAttributes(attributeSet, R.styleable.SnackbarLayout);
            int i5 = R.styleable.SnackbarLayout_elevation;
            if (typedArrayObtainStyledAttributes.hasValue(i5)) {
                float dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(i5, 0);
                WeakHashMap weakHashMap = W.f7199a;
                M.s(this, dimensionPixelSize);
            }
            this.animationMode = typedArrayObtainStyledAttributes.getInt(R.styleable.SnackbarLayout_animationMode, 1);
            if (typedArrayObtainStyledAttributes.hasValue(R.styleable.SnackbarLayout_shapeAppearance) || typedArrayObtainStyledAttributes.hasValue(R.styleable.SnackbarLayout_shapeAppearanceOverlay)) {
                this.shapeAppearanceModel = ShapeAppearanceModel.builder(context2, attributeSet, 0, 0).build();
            }
            this.backgroundOverlayColorAlpha = typedArrayObtainStyledAttributes.getFloat(R.styleable.SnackbarLayout_backgroundOverlayColorAlpha, 1.0f);
            setBackgroundTintList(MaterialResources.getColorStateList(context2, typedArrayObtainStyledAttributes, R.styleable.SnackbarLayout_backgroundTint));
            setBackgroundTintMode(ViewUtils.parseTintMode(typedArrayObtainStyledAttributes.getInt(R.styleable.SnackbarLayout_backgroundTintMode, -1), PorterDuff.Mode.SRC_IN));
            this.actionTextColorAlpha = typedArrayObtainStyledAttributes.getFloat(R.styleable.SnackbarLayout_actionTextColorAlpha, 1.0f);
            this.maxWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.SnackbarLayout_android_maxWidth, -1);
            this.maxInlineActionWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.SnackbarLayout_maxActionInlineWidth, -1);
            BaseTransientBottomBar.TRANSITION_HEIGHT = typedArrayObtainStyledAttributes.getResources().getDimensionPixelSize(R.dimen.sesl_design_snackbar_suggest_transition_height);
            typedArrayObtainStyledAttributes.recycle();
            setOnTouchListener(consumeAllTouchListener);
            setFocusable(true);
            if (getBackground() == null) {
                Drawable drawableCreateThemedBackground = createThemedBackground();
                WeakHashMap weakHashMap2 = W.f7199a;
                setBackground(drawableCreateThemedBackground);
            }
        }
    }

    public BaseTransientBottomBar(ViewGroup viewGroup, View view, com.google.android.material.snackbar.ContentViewCallback contentViewCallback) {
        this(viewGroup.getContext(), viewGroup, view, contentViewCallback);
    }

    private void animateViewOut(int i5) {
        if (this.view.getAnimationMode() == 1 || this.view.getAnimationMode() == 2) {
            startFadeOutAnimation(i5);
        } else {
            startSlideOutAnimation(i5);
        }
    }

    private int calculateBottomMarginForAnchorView() {
        if (getAnchorView() == null) {
            return 0;
        }
        int[] iArr = new int[2];
        getAnchorView().getLocationOnScreen(iArr);
        int i5 = iArr[1];
        int[] iArr2 = new int[2];
        this.targetParent.getLocationOnScreen(iArr2);
        return (this.targetParent.getHeight() + iArr2[1]) - i5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static GradientDrawable createGradientDrawableBackground(int i5, Resources resources) {
        float dimension = resources.getDimension(R.dimen.mtrl_snackbar_background_corner_radius);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setCornerRadius(dimension);
        gradientDrawable.setColor(i5);
        return gradientDrawable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static MaterialShapeDrawable createMaterialShapeDrawableBackground(int i5, ShapeAppearanceModel shapeAppearanceModel) {
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(shapeAppearanceModel);
        materialShapeDrawable.setFillColor(ColorStateList.valueOf(i5));
        return materialShapeDrawable;
    }

    private ValueAnimator getAlphaAnimator(float... fArr) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fArr);
        valueAnimatorOfFloat.setInterpolator(this.animationFadeInterpolator);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.11
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                BaseTransientBottomBar.this.view.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        return valueAnimatorOfFloat;
    }

    private ValueAnimator getScaleAnimator(float... fArr) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(fArr);
        valueAnimatorOfFloat.setInterpolator(this.animationScaleInterpolator);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.12
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                BaseTransientBottomBar.this.view.setScaleX(fFloatValue);
                BaseTransientBottomBar.this.view.setScaleY(fFloatValue);
            }
        });
        return valueAnimatorOfFloat;
    }

    private int getTranslationYBottom() {
        int height = this.view.getHeight();
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        return layoutParams instanceof ViewGroup.MarginLayoutParams ? height + ((ViewGroup.MarginLayoutParams) layoutParams).bottomMargin : height;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getViewAbsoluteBottom() {
        int[] iArr = new int[2];
        this.view.getLocationInWindow(iArr);
        return this.view.getHeight() + iArr[1];
    }

    private boolean isSwipeDismissable() {
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        return (layoutParams instanceof g) && (((g) layoutParams).f7154a instanceof SwipeDismissBehavior);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void lambda$seslStartSuggestAnimation$0(final SnackbarContentLayout snackbarContentLayout, final TextView textView, final Button button) {
        final int measuredWidth = snackbarContentLayout.getMeasuredWidth();
        final int measuredHeight = snackbarContentLayout.getMeasuredHeight();
        updateContentBackground(snackbarContentLayout, 100, 100);
        snackbarContentLayout.setAlpha(1.0f);
        this.view.setAlpha(1.0f);
        this.view.setTranslationY(r1.getHeight());
        h hVar = new h(this.view, e.f7408m);
        hVar.b();
        i iVar = new i();
        iVar.b(350.0f);
        iVar.a(1.0f);
        hVar.f7425t = iVar;
        hVar.e(0.0f - TRANSITION_HEIGHT);
        hVar.f7414a = 0.1f;
        hVar.g();
        final int i5 = 100;
        final int i7 = 100;
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.17
            @Override // java.lang.Runnable
            public void run() {
                textView.setAlpha(0.0f);
                ViewPropertyAnimator duration = textView.animate().alpha(1.0f).setDuration(150L);
                Context context = BaseTransientBottomBar.this.getContext();
                int i9 = R.interpolator.sesl_snackbar_suggestion_interpolator;
                duration.setInterpolator(android.view.animation.AnimationUtils.loadInterpolator(context, i9)).setStartDelay(150L).start();
                button.setAlpha(0.0f);
                button.animate().alpha(1.0f).setDuration(150L).setInterpolator(android.view.animation.AnimationUtils.loadInterpolator(BaseTransientBottomBar.this.getContext(), i9)).setStartDelay(150L).start();
                h hVar2 = new h(snackbarContentLayout, new f(FieldName.SIZE) { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.17.1
                    private float _value = 0.0f;

                    @Override // androidx.dynamicanimation.animation.f
                    public float getValue(SnackbarContentLayout snackbarContentLayout2) {
                        return this._value;
                    }

                    @Override // androidx.dynamicanimation.animation.f
                    public void setValue(SnackbarContentLayout snackbarContentLayout2, float f2) {
                        float fMin = Math.min(4.0f * f2, 1.0f);
                        AnonymousClass17 anonymousClass17 = AnonymousClass17.this;
                        int iLerp = (int) MathUtils.lerp(i5, measuredWidth, fMin);
                        AnonymousClass17 anonymousClass172 = AnonymousClass17.this;
                        int iLerp2 = (int) MathUtils.lerp(i7, measuredHeight, fMin);
                        AnonymousClass17 anonymousClass173 = AnonymousClass17.this;
                        BaseTransientBottomBar.this.updateContentBackground(snackbarContentLayout, iLerp, iLerp2);
                        this._value = f2;
                    }
                });
                hVar2.f7415b = 0.0f;
                hVar2.f7416c = true;
                i iVar2 = new i();
                iVar2.b(50.0f);
                iVar2.a(0.72f);
                hVar2.f7425t = iVar2;
                hVar2.e(1.0f);
                hVar2.g();
                h hVar3 = new h(BaseTransientBottomBar.this.view, e.f7408m);
                hVar3.b();
                i iVar3 = new i();
                iVar3.b(300.0f);
                iVar3.a(0.72f);
                hVar3.f7425t = iVar3;
                hVar3.e(0.0f);
                hVar3.f7414a = 0.1f;
                hVar3.g();
                hVar2.a(new d() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.17.2
                    @Override // androidx.dynamicanimation.animation.d
                    public void onAnimationEnd(e eVar, boolean z9, float f2, float f7) {
                        BaseTransientBottomBar.this.onViewShown();
                    }
                });
            }
        }, 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recalculateAndUpdateMargins() {
        this.extraBottomMarginAnchorView = calculateBottomMarginForAnchorView();
        updateMargins();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void seslStartSuggestAnimation() {
        SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) this.view.findViewById(R.id.snackbar_content_layout);
        TextView textView = (TextView) snackbarContentLayout.findViewById(R.id.snackbar_text);
        Button button = (Button) snackbarContentLayout.findViewById(R.id.snackbar_action);
        snackbarContentLayout.setAlpha(0.0f);
        textView.setAlpha(0.0f);
        button.setAlpha(0.0f);
        textView.setVisibility(0);
        button.setVisibility(0);
        snackbarContentLayout.post(new a(this, snackbarContentLayout, textView, button, 0));
    }

    private void setUpBehavior(g gVar) {
        SwipeDismissBehavior<? extends View> newBehavior = this.behavior;
        if (newBehavior == null) {
            newBehavior = getNewBehavior();
        }
        if (newBehavior instanceof Behavior) {
            ((Behavior) newBehavior).setBaseTransientBottomBar(this);
        }
        newBehavior.setListener(new SwipeDismissBehavior.OnDismissListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.7
            @Override // com.google.android.material.behavior.SwipeDismissBehavior.OnDismissListener
            public void onDismiss(View view) {
                if (view.getParent() != null) {
                    view.setVisibility(8);
                }
                BaseTransientBottomBar.this.dispatchDismiss(0);
            }

            @Override // com.google.android.material.behavior.SwipeDismissBehavior.OnDismissListener
            public void onDragStateChanged(int i5) {
                if (i5 == 0) {
                    SnackbarManager.getInstance().restoreTimeoutIfPaused(BaseTransientBottomBar.this.managerCallback);
                } else if (i5 == 1 || i5 == 2) {
                    SnackbarManager.getInstance().pauseTimeout(BaseTransientBottomBar.this.managerCallback);
                }
            }
        });
        gVar.b(newBehavior);
        if (getAnchorView() == null) {
            gVar.f7160g = 80;
        }
    }

    private boolean shouldUpdateGestureInset() {
        return this.extraBottomMarginGestureInset > 0 && !this.gestureInsetBottomIgnored && isSwipeDismissable();
    }

    private void showViewImpl() {
        if (shouldAnimate()) {
            animateViewIn();
            return;
        }
        if (this.view.getParent() != null) {
            this.view.setVisibility(0);
        }
        onViewShown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startFadeInAnimation() {
        ValueAnimator alphaAnimator = getAlphaAnimator(0.0f, 1.0f);
        getScaleAnimator(ANIMATION_SCALE_FROM_VALUE, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(alphaAnimator);
        animatorSet.setInterpolator(android.view.animation.AnimationUtils.loadInterpolator(this.context, android.R.interpolator.decelerate_quad));
        animatorSet.setDuration(this.animationFadeInDuration);
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.9
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                BaseTransientBottomBar.this.onViewShown();
            }
        });
        animatorSet.start();
    }

    private void startFadeOutAnimation(final int i5) {
        ValueAnimator alphaAnimator = getAlphaAnimator(1.0f, 0.0f);
        alphaAnimator.setInterpolator(android.view.animation.AnimationUtils.loadInterpolator(this.context, android.R.interpolator.accelerate_quad));
        alphaAnimator.setDuration(this.animationFadeOutDuration);
        alphaAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.10
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                BaseTransientBottomBar.this.onViewHidden(i5);
            }
        });
        alphaAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSlideInAnimation() {
        int translationYBottom = getTranslationYBottom();
        if (USE_OFFSET_API) {
            SnackbarBaseLayout snackbarBaseLayout = this.view;
            WeakHashMap weakHashMap = W.f7199a;
            snackbarBaseLayout.offsetTopAndBottom(translationYBottom);
        } else {
            this.view.setTranslationY(translationYBottom);
        }
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(translationYBottom, 0);
        valueAnimator.setInterpolator(this.animationSlideInterpolator);
        valueAnimator.setDuration(this.animationSlideDuration);
        valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.13
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                BaseTransientBottomBar.this.onViewShown();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                BaseTransientBottomBar.this.contentViewCallback.animateContentIn(BaseTransientBottomBar.this.animationSlideDuration - BaseTransientBottomBar.this.animationFadeInDuration, BaseTransientBottomBar.this.animationFadeInDuration);
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(translationYBottom) { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.14
            private int previousAnimatedIntValue;
            final /* synthetic */ int val$translationYBottom;

            {
                this.val$translationYBottom = translationYBottom;
                this.previousAnimatedIntValue = translationYBottom;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                int iIntValue = ((Integer) valueAnimator2.getAnimatedValue()).intValue();
                if (BaseTransientBottomBar.USE_OFFSET_API) {
                    SnackbarBaseLayout snackbarBaseLayout2 = BaseTransientBottomBar.this.view;
                    int i5 = iIntValue - this.previousAnimatedIntValue;
                    WeakHashMap weakHashMap2 = W.f7199a;
                    snackbarBaseLayout2.offsetTopAndBottom(i5);
                } else {
                    BaseTransientBottomBar.this.view.setTranslationY(iIntValue);
                }
                this.previousAnimatedIntValue = iIntValue;
            }
        });
        valueAnimator.start();
    }

    private void startSlideOutAnimation(final int i5) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setIntValues(0, getTranslationYBottom());
        valueAnimator.setInterpolator(this.animationSlideInterpolator);
        valueAnimator.setDuration(this.animationSlideDuration);
        valueAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.15
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                BaseTransientBottomBar.this.onViewHidden(i5);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                BaseTransientBottomBar.this.contentViewCallback.animateContentOut(0, BaseTransientBottomBar.this.animationFadeOutDuration);
            }
        });
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.16
            private int previousAnimatedIntValue = 0;

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator2) {
                int iIntValue = ((Integer) valueAnimator2.getAnimatedValue()).intValue();
                if (BaseTransientBottomBar.USE_OFFSET_API) {
                    SnackbarBaseLayout snackbarBaseLayout = BaseTransientBottomBar.this.view;
                    int i7 = iIntValue - this.previousAnimatedIntValue;
                    WeakHashMap weakHashMap = W.f7199a;
                    snackbarBaseLayout.offsetTopAndBottom(i7);
                } else {
                    BaseTransientBottomBar.this.view.setTranslationY(iIntValue);
                }
                this.previousAnimatedIntValue = iIntValue;
            }
        });
        valueAnimator.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateContentBackground(View view, int i5, int i7) {
        GradientDrawable gradientDrawable = (GradientDrawable) view.getBackground();
        float fMin = Math.min(i5, i7) / 2.0f;
        float dimensionPixelSize = this.context.getResources().getDimensionPixelSize(R.dimen.sesl_design_snackbar_suggest_background_radius);
        if (fMin > dimensionPixelSize) {
            fMin = dimensionPixelSize;
        }
        gradientDrawable.setCornerRadius(fMin);
        Rect bounds = gradientDrawable.getBounds();
        int iCenterX = bounds.centerX();
        int iCenterY = bounds.centerY();
        int i9 = i5 / 2;
        int i10 = i7 / 2;
        gradientDrawable.setBounds(iCenterX - i9, iCenterY - i10, iCenterX + i9, iCenterY + i10);
        gradientDrawable.invalidateSelf();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateMargins() {
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            Log.w(TAG, "Unable to update margins because layout params are not MarginLayoutParams");
            return;
        }
        if (this.view.originalMargins == null) {
            Log.w(TAG, "Unable to update margins because original view margins are not set");
            return;
        }
        if (this.view.getParent() == null) {
            return;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        int i5 = this.view.originalMargins.bottom + (getAnchorView() != null ? this.extraBottomMarginAnchorView : this.extraBottomMarginWindowInset);
        int i7 = this.view.originalMargins.left + this.extraLeftMarginWindowInset;
        int i9 = this.view.originalMargins.right + this.extraRightMarginWindowInset;
        int i10 = this.view.originalMargins.top;
        boolean z9 = (marginLayoutParams.bottomMargin == i5 && marginLayoutParams.leftMargin == i7 && marginLayoutParams.rightMargin == i9 && marginLayoutParams.topMargin == i10) ? false : true;
        if (z9) {
            marginLayoutParams.bottomMargin = i5;
            marginLayoutParams.leftMargin = i7;
            marginLayoutParams.rightMargin = i9;
            marginLayoutParams.topMargin = i10;
            this.view.requestLayout();
        }
        this.view.requestLayout();
        if ((z9 || this.appliedBottomMarginGestureInset != this.extraBottomMarginGestureInset) && shouldUpdateGestureInset()) {
            this.view.removeCallbacks(this.bottomMarginGestureInsetRunnable);
            this.view.post(this.bottomMarginGestureInsetRunnable);
        }
    }

    public B addCallback(BaseCallback<B> baseCallback) {
        if (baseCallback == null) {
            return this;
        }
        if (this.callbacks == null) {
            this.callbacks = new ArrayList();
        }
        this.callbacks.add(baseCallback);
        return this;
    }

    public void animateViewIn() {
        this.view.post(new Runnable() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.8
            @Override // java.lang.Runnable
            public void run() {
                SnackbarBaseLayout snackbarBaseLayout = BaseTransientBottomBar.this.view;
                if (snackbarBaseLayout == null) {
                    return;
                }
                if (snackbarBaseLayout.getParent() != null) {
                    BaseTransientBottomBar.this.view.setVisibility(0);
                }
                if (BaseTransientBottomBar.this.view.getAnimationMode() == 1) {
                    BaseTransientBottomBar.this.startFadeInAnimation();
                } else if (BaseTransientBottomBar.this.view.getAnimationMode() == 2) {
                    BaseTransientBottomBar.this.seslStartSuggestAnimation();
                } else {
                    BaseTransientBottomBar.this.startSlideInAnimation();
                }
            }
        });
    }

    public void dismiss() {
        dispatchDismiss(3);
    }

    public void dispatchDismiss(int i5) {
        SnackbarManager.getInstance().dismiss(this.managerCallback, i5);
    }

    public View getAnchorView() {
        Anchor anchor = this.anchor;
        if (anchor == null) {
            return null;
        }
        return anchor.getAnchorView();
    }

    public int getAnimationMode() {
        return this.view.getAnimationMode();
    }

    public Behavior getBehavior() {
        return this.behavior;
    }

    public Context getContext() {
        return this.context;
    }

    public int getDuration() {
        return this.duration;
    }

    public SwipeDismissBehavior<? extends View> getNewBehavior() {
        return new Behavior();
    }

    public int getSnackbarBaseLayoutResId() {
        return hasSnackbarStyleAttr() ? R.layout.mtrl_layout_snackbar : R.layout.design_layout_snackbar;
    }

    public View getView() {
        return this.view;
    }

    public boolean hasSnackbarStyleAttr() {
        TypedArray typedArrayObtainStyledAttributes = this.context.obtainStyledAttributes(SNACKBAR_STYLE_ATTR);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, -1);
        typedArrayObtainStyledAttributes.recycle();
        return resourceId != -1;
    }

    public final void hideView(int i5) {
        if (shouldAnimate() && this.view.getVisibility() == 0) {
            animateViewOut(i5);
        } else {
            onViewHidden(i5);
        }
    }

    public boolean isAnchorViewLayoutListenerEnabled() {
        return this.anchorViewLayoutListenerEnabled;
    }

    public boolean isGestureInsetBottomIgnored() {
        return this.gestureInsetBottomIgnored;
    }

    public boolean isShown() {
        return SnackbarManager.getInstance().isCurrent(this.managerCallback);
    }

    public boolean isShownOrQueued() {
        return SnackbarManager.getInstance().isCurrentOrNext(this.managerCallback);
    }

    public void onAttachedToWindow() {
        WindowInsets rootWindowInsets = this.view.getRootWindowInsets();
        if (rootWindowInsets != null) {
            this.extraBottomMarginGestureInset = rootWindowInsets.getMandatorySystemGestureInsets().bottom;
            updateMargins();
        }
    }

    public void onDetachedFromWindow() {
        if (isShownOrQueued()) {
            handler.post(new Runnable() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.6
                @Override // java.lang.Runnable
                public void run() {
                    BaseTransientBottomBar.this.onViewHidden(3);
                }
            });
        }
    }

    public void onLayoutChange() {
        if (this.pendingShowingView) {
            showViewImpl();
            this.pendingShowingView = false;
        }
    }

    public void onViewHidden(int i5) {
        SnackbarManager.getInstance().onDismissed(this.managerCallback);
        List<BaseCallback<B>> list = this.callbacks;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.callbacks.get(size).onDismissed(this, i5);
            }
        }
        ViewParent parent = this.view.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(this.view);
        }
    }

    public void onViewShown() {
        SnackbarManager.getInstance().onShown(this.managerCallback);
        List<BaseCallback<B>> list = this.callbacks;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.callbacks.get(size).onShown(this);
            }
        }
    }

    public B removeCallback(BaseCallback<B> baseCallback) {
        List<BaseCallback<B>> list;
        if (baseCallback == null || (list = this.callbacks) == null) {
            return this;
        }
        list.remove(baseCallback);
        return this;
    }

    public B setAnchorView(View view) {
        Anchor anchor = this.anchor;
        if (anchor != null) {
            anchor.unanchor();
        }
        this.anchor = view == null ? null : Anchor.anchor(this, view);
        return this;
    }

    public void setAnchorViewLayoutListenerEnabled(boolean z9) {
        this.anchorViewLayoutListenerEnabled = z9;
    }

    public B setAnimationMode(int i5) {
        this.view.setAnimationMode(i5);
        return this;
    }

    public B setBehavior(Behavior behavior) {
        this.behavior = behavior;
        return this;
    }

    public B setDuration(int i5) {
        this.duration = i5;
        return this;
    }

    public B setGestureInsetBottomIgnored(boolean z9) {
        this.gestureInsetBottomIgnored = z9;
        return this;
    }

    public boolean shouldAnimate() {
        AccessibilityManager accessibilityManager = this.accessibilityManager;
        if (accessibilityManager == null) {
            return true;
        }
        List<AccessibilityServiceInfo> enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(1);
        return enabledAccessibilityServiceList != null && enabledAccessibilityServiceList.isEmpty();
    }

    public void show() {
        SnackbarManager.getInstance().show(getDuration(), this.managerCallback);
    }

    public final void showView() {
        if (this.view.getParent() == null) {
            ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
            if (layoutParams instanceof g) {
                setUpBehavior((g) layoutParams);
            }
            this.view.addToTargetParent(this.targetParent);
            recalculateAndUpdateMargins();
            this.view.setVisibility(4);
        }
        SnackbarBaseLayout snackbarBaseLayout = this.view;
        WeakHashMap weakHashMap = W.f7199a;
        if (snackbarBaseLayout.isLaidOut()) {
            showViewImpl();
        } else {
            this.pendingShowingView = true;
        }
    }

    public BaseTransientBottomBar(Context context, ViewGroup viewGroup, View view, com.google.android.material.snackbar.ContentViewCallback contentViewCallback) {
        this.anchorViewLayoutListenerEnabled = false;
        this.bottomMarginGestureInsetRunnable = new Runnable() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.2
            @Override // java.lang.Runnable
            public void run() {
                BaseTransientBottomBar baseTransientBottomBar = BaseTransientBottomBar.this;
                if (baseTransientBottomBar.view == null || baseTransientBottomBar.context == null) {
                    return;
                }
                int iHeight = (WindowUtils.getCurrentWindowBounds(BaseTransientBottomBar.this.context).height() - BaseTransientBottomBar.this.getViewAbsoluteBottom()) + ((int) BaseTransientBottomBar.this.view.getTranslationY());
                if (iHeight >= BaseTransientBottomBar.this.extraBottomMarginGestureInset) {
                    BaseTransientBottomBar baseTransientBottomBar2 = BaseTransientBottomBar.this;
                    baseTransientBottomBar2.appliedBottomMarginGestureInset = baseTransientBottomBar2.extraBottomMarginGestureInset;
                    return;
                }
                ViewGroup.LayoutParams layoutParams = BaseTransientBottomBar.this.view.getLayoutParams();
                if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                    Log.w(BaseTransientBottomBar.TAG, "Unable to apply gesture inset because layout params are not MarginLayoutParams");
                    return;
                }
                BaseTransientBottomBar baseTransientBottomBar3 = BaseTransientBottomBar.this;
                baseTransientBottomBar3.appliedBottomMarginGestureInset = baseTransientBottomBar3.extraBottomMarginGestureInset;
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                marginLayoutParams.bottomMargin = (BaseTransientBottomBar.this.extraBottomMarginGestureInset - iHeight) + marginLayoutParams.bottomMargin;
                BaseTransientBottomBar.this.view.requestLayout();
            }
        };
        this.managerCallback = new SnackbarManager.Callback() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.5
            @Override // com.google.android.material.snackbar.SnackbarManager.Callback
            public void dismiss(int i5) {
                Handler handler2 = BaseTransientBottomBar.handler;
                handler2.sendMessage(handler2.obtainMessage(1, i5, 0, BaseTransientBottomBar.this));
            }

            @Override // com.google.android.material.snackbar.SnackbarManager.Callback
            public void show() {
                Handler handler2 = BaseTransientBottomBar.handler;
                handler2.sendMessage(handler2.obtainMessage(0, BaseTransientBottomBar.this));
            }
        };
        if (viewGroup == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null parent");
        }
        if (view == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null content");
        }
        if (contentViewCallback == null) {
            throw new IllegalArgumentException("Transient bottom bar must have non-null callback");
        }
        this.targetParent = viewGroup;
        this.contentViewCallback = contentViewCallback;
        this.context = context;
        ThemeEnforcement.checkAppCompatTheme(context);
        SnackbarBaseLayout snackbarBaseLayout = (SnackbarBaseLayout) LayoutInflater.from(context).inflate(getSnackbarBaseLayoutResId(), viewGroup, false);
        this.view = snackbarBaseLayout;
        snackbarBaseLayout.setBaseTransientBottomBar(this);
        if (view instanceof SnackbarContentLayout) {
            ((SnackbarContentLayout) view).updateActionTextColorAlphaIfNeeded(snackbarBaseLayout.getActionTextColorAlpha());
        }
        snackbarBaseLayout.addView(view);
        WeakHashMap weakHashMap = W.f7199a;
        snackbarBaseLayout.setAccessibilityLiveRegion(1);
        snackbarBaseLayout.setImportantForAccessibility(1);
        snackbarBaseLayout.setFitsSystemWindows(true);
        M.u(snackbarBaseLayout, new InterfaceC0226s() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.3
            @Override // androidx.core.view.InterfaceC0226s
            public w0 onApplyWindowInsets(View view2, w0 w0Var) {
                BaseTransientBottomBar.this.extraBottomMarginWindowInset = w0Var.a();
                BaseTransientBottomBar.this.extraLeftMarginWindowInset = w0Var.b();
                BaseTransientBottomBar.this.extraRightMarginWindowInset = w0Var.c();
                BaseTransientBottomBar.this.updateMargins();
                return w0Var;
            }
        });
        W.i(snackbarBaseLayout, new C0210b() { // from class: com.google.android.material.snackbar.BaseTransientBottomBar.4
            @Override // androidx.core.view.C0210b
            public void onInitializeAccessibilityNodeInfo(View view2, l lVar) {
                super.onInitializeAccessibilityNodeInfo(view2, lVar);
                lVar.a(1048576);
                lVar.f1793a.setDismissable(true);
            }

            @Override // androidx.core.view.C0210b
            public boolean performAccessibilityAction(View view2, int i5, Bundle bundle) {
                if (i5 != 1048576) {
                    return super.performAccessibilityAction(view2, i5, bundle);
                }
                BaseTransientBottomBar.this.dismiss();
                return true;
            }
        });
        this.accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        int i5 = R.attr.motionDurationLong2;
        this.animationSlideDuration = MotionUtils.resolveThemeDuration(context, i5, DEFAULT_SLIDE_ANIMATION_DURATION);
        this.animationFadeInDuration = MotionUtils.resolveThemeDuration(context, i5, 180);
        this.animationFadeOutDuration = MotionUtils.resolveThemeDuration(context, R.attr.motionDurationMedium1, 180);
        int i7 = R.attr.motionEasingEmphasizedInterpolator;
        this.animationFadeInterpolator = MotionUtils.resolveThemeInterpolator(context, i7, DEFAULT_ANIMATION_FADE_INTERPOLATOR);
        this.animationScaleInterpolator = MotionUtils.resolveThemeInterpolator(context, i7, DEFAULT_ANIMATION_SCALE_INTERPOLATOR);
        this.animationSlideInterpolator = MotionUtils.resolveThemeInterpolator(context, i7, DEFAULT_ANIMATION_SLIDE_INTERPOLATOR);
    }

    public B setAnchorView(int i5) {
        View viewFindViewById = this.targetParent.findViewById(i5);
        if (viewFindViewById != null) {
            return (B) setAnchorView(viewFindViewById);
        }
        throw new IllegalArgumentException(A8.l.o(i5, "Unable to find anchor view with id: "));
    }
}
