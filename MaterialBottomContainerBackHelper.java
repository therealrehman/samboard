package com.google.android.material.motion;

import Z.b;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.Resources;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;

/* JADX INFO: loaded from: classes.dex */
public class MaterialBottomContainerBackHelper extends MaterialBackAnimationHelper<View> {
    private final float maxScaleXDistance;
    private final float maxScaleYDistance;

    public MaterialBottomContainerBackHelper(View view) {
        super(view);
        Resources resources = view.getResources();
        this.maxScaleXDistance = resources.getDimension(R.dimen.m3_back_progress_bottom_container_max_scale_x_distance);
        this.maxScaleYDistance = resources.getDimension(R.dimen.m3_back_progress_bottom_container_max_scale_y_distance);
    }

    private Animator createResetScaleAnimator() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(this.view, (Property<V, Float>) View.SCALE_X, 1.0f), ObjectAnimator.ofFloat(this.view, (Property<V, Float>) View.SCALE_Y, 1.0f));
        V v4 = this.view;
        if (v4 instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) v4;
            for (int i5 = 0; i5 < viewGroup.getChildCount(); i5++) {
                animatorSet.playTogether(ObjectAnimator.ofFloat(viewGroup.getChildAt(i5), (Property<View, Float>) View.SCALE_Y, 1.0f));
            }
        }
        animatorSet.setInterpolator(new b());
        return animatorSet;
    }

    public void cancelBackProgress() {
        if (super.onCancelBackProgress() == null) {
            return;
        }
        Animator animatorCreateResetScaleAnimator = createResetScaleAnimator();
        animatorCreateResetScaleAnimator.setDuration(this.cancelDuration);
        animatorCreateResetScaleAnimator.start();
    }

    public void finishBackProgressNotPersistent(androidx.activity.b bVar, Animator.AnimatorListener animatorListener) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.view, (Property<V, Float>) View.TRANSLATION_Y, this.view.getScaleY() * this.view.getHeight());
        objectAnimatorOfFloat.setInterpolator(new b());
        objectAnimatorOfFloat.setDuration(AnimationUtils.lerp(this.hideDurationMax, this.hideDurationMin, bVar.f6092c));
        objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.motion.MaterialBottomContainerBackHelper.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                MaterialBottomContainerBackHelper.this.view.setTranslationY(0.0f);
                MaterialBottomContainerBackHelper.this.updateBackProgress(0.0f);
            }
        });
        if (animatorListener != null) {
            objectAnimatorOfFloat.addListener(animatorListener);
        }
        objectAnimatorOfFloat.start();
    }

    public void finishBackProgressPersistent(androidx.activity.b bVar, Animator.AnimatorListener animatorListener) {
        Animator animatorCreateResetScaleAnimator = createResetScaleAnimator();
        animatorCreateResetScaleAnimator.setDuration(AnimationUtils.lerp(this.hideDurationMax, this.hideDurationMin, bVar.f6092c));
        if (animatorListener != null) {
            animatorCreateResetScaleAnimator.addListener(animatorListener);
        }
        animatorCreateResetScaleAnimator.start();
    }

    public void startBackProgress(androidx.activity.b bVar) {
        super.onStartBackProgress(bVar);
    }

    public void updateBackProgress(androidx.activity.b bVar) {
        if (super.onUpdateBackProgress(bVar) == null) {
            return;
        }
        updateBackProgress(bVar.f6092c);
    }

    public void updateBackProgress(float f2) {
        float fInterpolateProgress = interpolateProgress(f2);
        float width = this.view.getWidth();
        float height = this.view.getHeight();
        if (width <= 0.0f || height <= 0.0f) {
            return;
        }
        float f7 = this.maxScaleXDistance / width;
        float f9 = this.maxScaleYDistance / height;
        float fLerp = 1.0f - AnimationUtils.lerp(0.0f, f7, fInterpolateProgress);
        float fLerp2 = 1.0f - AnimationUtils.lerp(0.0f, f9, fInterpolateProgress);
        this.view.setScaleX(fLerp);
        this.view.setPivotY(height);
        this.view.setScaleY(fLerp2);
        V v4 = this.view;
        if (v4 instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) v4;
            for (int i5 = 0; i5 < viewGroup.getChildCount(); i5++) {
                View childAt = viewGroup.getChildAt(i5);
                childAt.setPivotY(-childAt.getTop());
                childAt.setScaleY(fLerp2 != 0.0f ? fLerp / fLerp2 : 1.0f);
            }
        }
    }
}
