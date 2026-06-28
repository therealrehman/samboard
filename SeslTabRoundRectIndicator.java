package com.google.android.material.tabs;

import B.a;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.view.animation.ScaleAnimation;
import androidx.core.view.W;
import com.google.android.material.R;
import java.util.WeakHashMap;
import s6.c;

/* JADX INFO: loaded from: classes.dex */
public class SeslTabRoundRectIndicator extends SeslAbsIndicatorView {
    private static final int DURATION_ALPHA = 100;
    private static final int DURATION_PRESS = 250;
    private static final int DURATION_RELEASE = 350;
    private static final float SCALE_MINOR = 0.95f;
    private final Interpolator LINEAR_INTERPOLATOR;
    private final PathInterpolator SCALE_INTERPOLATOR;
    private AnimationSet mPressAnimationSet;

    public SeslTabRoundRectIndicator(Context context) {
        this(context, null);
    }

    @Override // com.google.android.material.tabs.SeslAbsIndicatorView
    public void onHide() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 0.0f);
        alphaAnimation.setDuration(0L);
        alphaAnimation.setFillAfter(true);
        startAnimation(alphaAnimation);
        setAlpha(0.0f);
    }

    @Override // com.google.android.material.tabs.SeslAbsIndicatorView
    public void onSetSelectedIndicatorColor(int i5) {
        if (getBackground() instanceof NinePatchDrawable) {
            return;
        }
        getBackground().setTint(i5);
        if (isSelected()) {
            return;
        }
        setHide();
    }

    @Override // com.google.android.material.tabs.SeslAbsIndicatorView
    public void onShow() {
        setAlpha(1.0f);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 1.0f);
        alphaAnimation.setDuration(0L);
        alphaAnimation.setFillAfter(true);
        startAnimation(alphaAnimation);
    }

    @Override // android.view.View
    public void onVisibilityChanged(View view, int i5) {
        super.onVisibilityChanged(view, i5);
        if (i5 == 0 || isSelected()) {
            return;
        }
        onHide();
    }

    @Override // com.google.android.material.tabs.SeslAbsIndicatorView
    public /* bridge */ /* synthetic */ void setHide() {
        super.setHide();
    }

    @Override // com.google.android.material.tabs.SeslAbsIndicatorView
    public /* bridge */ /* synthetic */ void setPressed() {
        super.setPressed();
    }

    @Override // com.google.android.material.tabs.SeslAbsIndicatorView
    public /* bridge */ /* synthetic */ void setReleased() {
        super.setReleased();
    }

    @Override // com.google.android.material.tabs.SeslAbsIndicatorView
    public /* bridge */ /* synthetic */ void setSelectedIndicatorColor(int i5) {
        super.setSelectedIndicatorColor(i5);
    }

    @Override // com.google.android.material.tabs.SeslAbsIndicatorView
    public /* bridge */ /* synthetic */ void setShow() {
        super.setShow();
    }

    @Override // com.google.android.material.tabs.SeslAbsIndicatorView
    public void startPressEffect() {
        setAlpha(1.0f);
        AnimationSet animationSet = new AnimationSet(false);
        this.mPressAnimationSet = animationSet;
        animationSet.setFillAfter(true);
        this.mPressAnimationSet.setAnimationListener(new Animation.AnimationListener() { // from class: com.google.android.material.tabs.SeslTabRoundRectIndicator.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                SeslTabRoundRectIndicator.this.mPressAnimationSet = null;
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, SCALE_MINOR, 1.0f, SCALE_MINOR, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(250L);
        scaleAnimation.setInterpolator(this.SCALE_INTERPOLATOR);
        scaleAnimation.setFillAfter(true);
        this.mPressAnimationSet.addAnimation(scaleAnimation);
        if (!isSelected()) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(100L);
            alphaAnimation.setFillAfter(true);
            alphaAnimation.setInterpolator(this.LINEAR_INTERPOLATOR);
            this.mPressAnimationSet.addAnimation(alphaAnimation);
        }
        startAnimation(this.mPressAnimationSet);
    }

    @Override // com.google.android.material.tabs.SeslAbsIndicatorView
    public void startReleaseEffect() {
        setAlpha(1.0f);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.setFillAfter(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(SCALE_MINOR, 1.0f, SCALE_MINOR, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(350L);
        scaleAnimation.setInterpolator(this.SCALE_INTERPOLATOR);
        scaleAnimation.setFillAfter(true);
        animationSet.addAnimation(scaleAnimation);
        startAnimation(animationSet);
    }

    public SeslTabRoundRectIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SeslTabRoundRectIndicator(Context context, AttributeSet attributeSet, int i5) {
        this(context, attributeSet, i5, 0);
    }

    public SeslTabRoundRectIndicator(Context context, AttributeSet attributeSet, int i5, int i7) {
        int i9;
        super(context, attributeSet, i5, i7);
        this.LINEAR_INTERPOLATOR = new LinearInterpolator();
        this.SCALE_INTERPOLATOR = new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);
        if (c.O(context)) {
            i9 = R.drawable.sesl_tablayout_subtab_indicator_background_light;
        } else {
            i9 = R.drawable.sesl_tablayout_subtab_indicator_background_dark;
        }
        Drawable drawableB = a.b(context, i9);
        WeakHashMap weakHashMap = W.f7199a;
        setBackground(drawableB);
    }
}
