package com.google.android.material.motion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.util.Property;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.b;
import androidx.core.view.W;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class MaterialSideContainerBackHelper extends MaterialBackAnimationHelper<View> {
    private final float maxScaleXDistanceGrow;
    private final float maxScaleXDistanceShrink;
    private final float maxScaleYDistance;

    public MaterialSideContainerBackHelper(View view) {
        super(view);
        Resources resources = view.getResources();
        this.maxScaleXDistanceShrink = resources.getDimension(R.dimen.m3_back_progress_side_container_max_scale_x_distance_shrink);
        this.maxScaleXDistanceGrow = resources.getDimension(R.dimen.m3_back_progress_side_container_max_scale_x_distance_grow);
        this.maxScaleYDistance = resources.getDimension(R.dimen.m3_back_progress_side_container_max_scale_y_distance);
    }

    private boolean checkAbsoluteGravity(int i5, int i7) {
        V v4 = this.view;
        WeakHashMap weakHashMap = W.f7199a;
        return (Gravity.getAbsoluteGravity(i5, v4.getLayoutDirection()) & i7) == i7;
    }

    private int getEdgeMargin(boolean z9) {
        ViewGroup.LayoutParams layoutParams = this.view.getLayoutParams();
        if (!(layoutParams instanceof ViewGroup.MarginLayoutParams)) {
            return 0;
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        return z9 ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
    }

    public void cancelBackProgress() {
        if (super.onCancelBackProgress() == null) {
            return;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ObjectAnimator.ofFloat(this.view, (Property<V, Float>) View.SCALE_X, 1.0f), ObjectAnimator.ofFloat(this.view, (Property<V, Float>) View.SCALE_Y, 1.0f));
        V v4 = this.view;
        if (v4 instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) v4;
            for (int i5 = 0; i5 < viewGroup.getChildCount(); i5++) {
                animatorSet.playTogether(ObjectAnimator.ofFloat(viewGroup.getChildAt(i5), (Property<View, Float>) View.SCALE_Y, 1.0f));
            }
        }
        animatorSet.setDuration(this.cancelDuration);
        animatorSet.start();
    }

    public void finishBackProgress(b bVar, final int i5, Animator.AnimatorListener animatorListener, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        final boolean z9 = bVar.f6093d == 0;
        boolean zCheckAbsoluteGravity = checkAbsoluteGravity(i5, 3);
        float scaleX = (this.view.getScaleX() * this.view.getWidth()) + getEdgeMargin(zCheckAbsoluteGravity);
        V v4 = this.view;
        Property property = View.TRANSLATION_X;
        if (zCheckAbsoluteGravity) {
            scaleX = -scaleX;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(v4, (Property<V, Float>) property, scaleX);
        if (animatorUpdateListener != null) {
            objectAnimatorOfFloat.addUpdateListener(animatorUpdateListener);
        }
        objectAnimatorOfFloat.setInterpolator(new Z.b());
        objectAnimatorOfFloat.setDuration(AnimationUtils.lerp(this.hideDurationMax, this.hideDurationMin, bVar.f6092c));
        objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.motion.MaterialSideContainerBackHelper.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                MaterialSideContainerBackHelper.this.view.setTranslationX(0.0f);
                MaterialSideContainerBackHelper.this.updateBackProgress(0.0f, z9, i5);
            }
        });
        if (animatorListener != null) {
            objectAnimatorOfFloat.addListener(animatorListener);
        }
        objectAnimatorOfFloat.start();
    }

    public void startBackProgress(b bVar) {
        super.onStartBackProgress(bVar);
    }

    public void updateBackProgress(b bVar, int i5) {
        if (super.onUpdateBackProgress(bVar) == null) {
            return;
        }
        updateBackProgress(bVar.f6092c, bVar.f6093d == 0, i5);
    }

    public void updateBackProgress(float f2, boolean z9, int i5) {
        float fInterpolateProgress = interpolateProgress(f2);
        boolean zCheckAbsoluteGravity = checkAbsoluteGravity(i5, 3);
        boolean z10 = z9 == zCheckAbsoluteGravity;
        int width = this.view.getWidth();
        int height = this.view.getHeight();
        float f7 = width;
        if (f7 > 0.0f) {
            float f9 = height;
            if (f9 <= 0.0f) {
                return;
            }
            float f10 = this.maxScaleXDistanceShrink / f7;
            float f11 = this.maxScaleXDistanceGrow / f7;
            float f12 = this.maxScaleYDistance / f9;
            V v4 = this.view;
            if (zCheckAbsoluteGravity) {
                f7 = 0.0f;
            }
            v4.setPivotX(f7);
            if (!z10) {
                f11 = -f10;
            }
            float fLerp = AnimationUtils.lerp(0.0f, f11, fInterpolateProgress);
            float f13 = fLerp + 1.0f;
            this.view.setScaleX(f13);
            float fLerp2 = 1.0f - AnimationUtils.lerp(0.0f, f12, fInterpolateProgress);
            this.view.setScaleY(fLerp2);
            V v5 = this.view;
            if (v5 instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) v5;
                for (int i7 = 0; i7 < viewGroup.getChildCount(); i7++) {
                    View childAt = viewGroup.getChildAt(i7);
                    childAt.setPivotX(zCheckAbsoluteGravity ? childAt.getWidth() + (width - childAt.getRight()) : -childAt.getLeft());
                    childAt.setPivotY(-childAt.getTop());
                    float f14 = z10 ? 1.0f - fLerp : 1.0f;
                    float f15 = fLerp2 != 0.0f ? (f13 / fLerp2) * f14 : 1.0f;
                    childAt.setScaleX(f14);
                    childAt.setScaleY(f15);
                }
            }
        }
    }
}
