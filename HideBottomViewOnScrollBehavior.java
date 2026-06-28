package com.google.android.material.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.coordinatorlayout.widget.d;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.motion.MotionUtils;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* JADX INFO: loaded from: classes.dex */
public class HideBottomViewOnScrollBehavior<V extends View> extends d {
    private static final int DEFAULT_ENTER_ANIMATION_DURATION_MS = 225;
    private static final int DEFAULT_EXIT_ANIMATION_DURATION_MS = 175;
    public static final int STATE_SCROLLED_DOWN = 1;
    public static final int STATE_SCROLLED_UP = 2;
    private ViewPropertyAnimator currentAnimator;
    private int enterAnimDuration;
    private TimeInterpolator enterAnimInterpolator;
    private int exitAnimDuration;
    private TimeInterpolator exitAnimInterpolator;
    private static final int ENTER_ANIM_DURATION_ATTR = R.attr.motionDurationLong2;
    private static final int EXIT_ANIM_DURATION_ATTR = R.attr.motionDurationMedium4;
    private static final int ENTER_EXIT_ANIM_EASING_ATTR = R.attr.motionEasingEmphasizedInterpolator;
    private final LinkedHashSet<OnScrollStateChangedListener> onScrollStateChangedListeners = new LinkedHashSet<>();
    private int height = 0;

    @ScrollState
    private int currentState = 2;
    private int additionalHiddenOffsetY = 0;

    public interface OnScrollStateChangedListener {
        void onStateChanged(View view, @ScrollState int i5);
    }

    public @interface ScrollState {
    }

    public HideBottomViewOnScrollBehavior() {
    }

    private void animateChildTo(V v4, int i5, long j5, TimeInterpolator timeInterpolator) {
        this.currentAnimator = v4.animate().translationY(i5).setInterpolator(timeInterpolator).setDuration(j5).setListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.behavior.HideBottomViewOnScrollBehavior.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                HideBottomViewOnScrollBehavior.this.currentAnimator = null;
            }
        });
    }

    private void updateCurrentState(V v4, @ScrollState int i5) {
        this.currentState = i5;
        Iterator<OnScrollStateChangedListener> it = this.onScrollStateChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().onStateChanged(v4, this.currentState);
        }
    }

    public void addOnScrollStateChangedListener(OnScrollStateChangedListener onScrollStateChangedListener) {
        this.onScrollStateChangedListeners.add(onScrollStateChangedListener);
    }

    public void clearOnScrollStateChangedListeners() {
        this.onScrollStateChangedListeners.clear();
    }

    public boolean isScrolledDown() {
        return this.currentState == 1;
    }

    public boolean isScrolledUp() {
        return this.currentState == 2;
    }

    @Override // androidx.coordinatorlayout.widget.d
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v4, int i5) {
        this.height = v4.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) v4.getLayoutParams()).bottomMargin;
        this.enterAnimDuration = MotionUtils.resolveThemeDuration(v4.getContext(), ENTER_ANIM_DURATION_ATTR, DEFAULT_ENTER_ANIMATION_DURATION_MS);
        this.exitAnimDuration = MotionUtils.resolveThemeDuration(v4.getContext(), EXIT_ANIM_DURATION_ATTR, DEFAULT_EXIT_ANIMATION_DURATION_MS);
        Context context = v4.getContext();
        int i7 = ENTER_EXIT_ANIM_EASING_ATTR;
        this.enterAnimInterpolator = MotionUtils.resolveThemeInterpolator(context, i7, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        this.exitAnimInterpolator = MotionUtils.resolveThemeInterpolator(v4.getContext(), i7, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        return super.onLayoutChild(coordinatorLayout, v4, i5);
    }

    @Override // androidx.coordinatorlayout.widget.d
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, V v4, View view, int i5, int i7, int i9, int i10, int i11, int[] iArr) {
        if (i7 > 0) {
            slideDown(v4);
        } else if (i7 < 0) {
            slideUp(v4);
        }
    }

    @Override // androidx.coordinatorlayout.widget.d
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v4, View view, View view2, int i5, int i7) {
        return i5 == 2;
    }

    public void removeOnScrollStateChangedListener(OnScrollStateChangedListener onScrollStateChangedListener) {
        this.onScrollStateChangedListeners.remove(onScrollStateChangedListener);
    }

    public void setAdditionalHiddenOffsetY(V v4, int i5) {
        this.additionalHiddenOffsetY = i5;
        if (this.currentState == 1) {
            v4.setTranslationY(this.height + i5);
        }
    }

    public void slideDown(V v4) {
        slideDown(v4, true);
    }

    public void slideUp(V v4) {
        slideUp(v4, true);
    }

    public void slideDown(V v4, boolean z9) {
        if (isScrolledDown()) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.currentAnimator;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            v4.clearAnimation();
        }
        updateCurrentState(v4, 1);
        int i5 = this.height + this.additionalHiddenOffsetY;
        if (z9) {
            animateChildTo(v4, i5, this.exitAnimDuration, this.exitAnimInterpolator);
        } else {
            v4.setTranslationY(i5);
        }
    }

    public void slideUp(V v4, boolean z9) {
        if (isScrolledUp()) {
            return;
        }
        ViewPropertyAnimator viewPropertyAnimator = this.currentAnimator;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
            v4.clearAnimation();
        }
        updateCurrentState(v4, 2);
        if (z9) {
            animateChildTo(v4, 0, this.enterAnimDuration, this.enterAnimInterpolator);
        } else {
            v4.setTranslationY(0);
        }
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
    }
}
