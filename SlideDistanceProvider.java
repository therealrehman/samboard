package com.google.android.material.transition;

import A8.l;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.W;
import com.google.android.material.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class SlideDistanceProvider implements VisibilityAnimatorProvider {
    private static final int DEFAULT_DISTANCE = -1;
    private int slideDistance = -1;
    private int slideEdge;

    @Retention(RetentionPolicy.SOURCE)
    public @interface GravityFlag {
    }

    public SlideDistanceProvider(int i5) {
        this.slideEdge = i5;
    }

    private static Animator createTranslationAppearAnimator(View view, View view2, int i5, int i7) {
        float translationX = view2.getTranslationX();
        float translationY = view2.getTranslationY();
        if (i5 == 3) {
            return createTranslationXAnimator(view2, i7 + translationX, translationX, translationX);
        }
        if (i5 == 5) {
            return createTranslationXAnimator(view2, translationX - i7, translationX, translationX);
        }
        if (i5 == 48) {
            return createTranslationYAnimator(view2, translationY - i7, translationY, translationY);
        }
        if (i5 == 80) {
            return createTranslationYAnimator(view2, i7 + translationY, translationY, translationY);
        }
        if (i5 == 8388611) {
            return createTranslationXAnimator(view2, isRtl(view) ? i7 + translationX : translationX - i7, translationX, translationX);
        }
        if (i5 == 8388613) {
            return createTranslationXAnimator(view2, isRtl(view) ? translationX - i7 : i7 + translationX, translationX, translationX);
        }
        throw new IllegalArgumentException(l.o(i5, "Invalid slide direction: "));
    }

    private static Animator createTranslationDisappearAnimator(View view, View view2, int i5, int i7) {
        float translationX = view2.getTranslationX();
        float translationY = view2.getTranslationY();
        if (i5 == 3) {
            return createTranslationXAnimator(view2, translationX, translationX - i7, translationX);
        }
        if (i5 == 5) {
            return createTranslationXAnimator(view2, translationX, i7 + translationX, translationX);
        }
        if (i5 == 48) {
            return createTranslationYAnimator(view2, translationY, i7 + translationY, translationY);
        }
        if (i5 == 80) {
            return createTranslationYAnimator(view2, translationY, translationY - i7, translationY);
        }
        if (i5 == 8388611) {
            return createTranslationXAnimator(view2, translationX, isRtl(view) ? translationX - i7 : i7 + translationX, translationX);
        }
        if (i5 == 8388613) {
            return createTranslationXAnimator(view2, translationX, isRtl(view) ? i7 + translationX : translationX - i7, translationX);
        }
        throw new IllegalArgumentException(l.o(i5, "Invalid slide direction: "));
    }

    private static Animator createTranslationXAnimator(final View view, float f2, float f7, final float f9) {
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat((Property<?, Float>) View.TRANSLATION_X, f2, f7));
        objectAnimatorOfPropertyValuesHolder.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.transition.SlideDistanceProvider.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                view.setTranslationX(f9);
            }
        });
        return objectAnimatorOfPropertyValuesHolder;
    }

    private static Animator createTranslationYAnimator(final View view, float f2, float f7, final float f9) {
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat((Property<?, Float>) View.TRANSLATION_Y, f2, f7));
        objectAnimatorOfPropertyValuesHolder.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.transition.SlideDistanceProvider.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                view.setTranslationY(f9);
            }
        });
        return objectAnimatorOfPropertyValuesHolder;
    }

    private int getSlideDistanceOrDefault(Context context) {
        int i5 = this.slideDistance;
        return i5 != -1 ? i5 : context.getResources().getDimensionPixelSize(R.dimen.mtrl_transition_shared_axis_slide_distance);
    }

    private static boolean isRtl(View view) {
        WeakHashMap weakHashMap = W.f7199a;
        return view.getLayoutDirection() == 1;
    }

    @Override // com.google.android.material.transition.VisibilityAnimatorProvider
    public Animator createAppear(ViewGroup viewGroup, View view) {
        return createTranslationAppearAnimator(viewGroup, view, this.slideEdge, getSlideDistanceOrDefault(view.getContext()));
    }

    @Override // com.google.android.material.transition.VisibilityAnimatorProvider
    public Animator createDisappear(ViewGroup viewGroup, View view) {
        return createTranslationDisappearAnimator(viewGroup, view, this.slideEdge, getSlideDistanceOrDefault(view.getContext()));
    }

    public int getSlideDistance() {
        return this.slideDistance;
    }

    public int getSlideEdge() {
        return this.slideEdge;
    }

    public void setSlideDistance(int i5) {
        if (i5 < 0) {
            throw new IllegalArgumentException("Slide distance must be positive. If attempting to reverse the direction of the slide, use setSlideEdge(int) instead.");
        }
        this.slideDistance = i5;
    }

    public void setSlideEdge(int i5) {
        this.slideEdge = i5;
    }
}
