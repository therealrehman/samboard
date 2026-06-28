package com.google.android.material.transformation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.Property;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.coordinatorlayout.widget.g;
import androidx.core.view.M;
import androidx.core.view.W;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.animation.ArgbEvaluatorCompat;
import com.google.android.material.animation.ChildrenAlphaProperty;
import com.google.android.material.animation.DrawableAlphaProperty;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.animation.MotionTiming;
import com.google.android.material.animation.Positioning;
import com.google.android.material.circularreveal.CircularRevealCompat;
import com.google.android.material.circularreveal.CircularRevealHelper;
import com.google.android.material.circularreveal.CircularRevealWidget;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.math.MathUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class FabTransformationBehavior extends ExpandableTransformationBehavior {
    private float dependencyOriginalTranslationX;
    private float dependencyOriginalTranslationY;
    private final int[] tmpArray;
    private final Rect tmpRect;
    private final RectF tmpRectF1;
    private final RectF tmpRectF2;

    public static class FabTransformationSpec {
        public Positioning positioning;
        public MotionSpec timings;
    }

    public FabTransformationBehavior() {
        this.tmpRect = new Rect();
        this.tmpRectF1 = new RectF();
        this.tmpRectF2 = new RectF();
        this.tmpArray = new int[2];
    }

    private ViewGroup calculateChildContentContainer(View view) {
        View viewFindViewById = view.findViewById(R.id.mtrl_child_content_container);
        return viewFindViewById != null ? toViewGroupOrNull(viewFindViewById) : ((view instanceof TransformationChildLayout) || (view instanceof TransformationChildCard)) ? toViewGroupOrNull(((ViewGroup) view).getChildAt(0)) : toViewGroupOrNull(view);
    }

    private void calculateChildVisibleBoundsAtEndOfExpansion(View view, FabTransformationSpec fabTransformationSpec, MotionTiming motionTiming, MotionTiming motionTiming2, float f2, float f7, float f9, float f10, RectF rectF) {
        float fCalculateValueOfAnimationAtEndOfExpansion = calculateValueOfAnimationAtEndOfExpansion(fabTransformationSpec, motionTiming, f2, f9);
        float fCalculateValueOfAnimationAtEndOfExpansion2 = calculateValueOfAnimationAtEndOfExpansion(fabTransformationSpec, motionTiming2, f7, f10);
        Rect rect = this.tmpRect;
        view.getWindowVisibleDisplayFrame(rect);
        RectF rectF2 = this.tmpRectF1;
        rectF2.set(rect);
        RectF rectF3 = this.tmpRectF2;
        calculateWindowBounds(view, rectF3);
        rectF3.offset(fCalculateValueOfAnimationAtEndOfExpansion, fCalculateValueOfAnimationAtEndOfExpansion2);
        rectF3.intersect(rectF2);
        rectF.set(rectF3);
    }

    private void calculateDependencyWindowBounds(View view, RectF rectF) {
        calculateWindowBounds(view, rectF);
        rectF.offset(this.dependencyOriginalTranslationX, this.dependencyOriginalTranslationY);
    }

    private Pair<MotionTiming, MotionTiming> calculateMotionTiming(float f2, float f7, boolean z9, FabTransformationSpec fabTransformationSpec) {
        MotionTiming timing;
        MotionTiming timing2;
        if (f2 == 0.0f || f7 == 0.0f) {
            timing = fabTransformationSpec.timings.getTiming("translationXLinear");
            timing2 = fabTransformationSpec.timings.getTiming("translationYLinear");
        } else if ((!z9 || f7 >= 0.0f) && (z9 || f7 <= 0.0f)) {
            timing = fabTransformationSpec.timings.getTiming("translationXCurveDownwards");
            timing2 = fabTransformationSpec.timings.getTiming("translationYCurveDownwards");
        } else {
            timing = fabTransformationSpec.timings.getTiming("translationXCurveUpwards");
            timing2 = fabTransformationSpec.timings.getTiming("translationYCurveUpwards");
        }
        return new Pair<>(timing, timing2);
    }

    private float calculateRevealCenterX(View view, View view2, Positioning positioning) {
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        calculateDependencyWindowBounds(view, rectF);
        calculateWindowBounds(view2, rectF2);
        rectF2.offset(-calculateTranslationX(view, view2, positioning), 0.0f);
        return rectF.centerX() - rectF2.left;
    }

    private float calculateRevealCenterY(View view, View view2, Positioning positioning) {
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        calculateDependencyWindowBounds(view, rectF);
        calculateWindowBounds(view2, rectF2);
        rectF2.offset(0.0f, -calculateTranslationY(view, view2, positioning));
        return rectF.centerY() - rectF2.top;
    }

    private float calculateTranslationX(View view, View view2, Positioning positioning) {
        float fCenterX;
        float fCenterX2;
        float f2;
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        calculateDependencyWindowBounds(view, rectF);
        calculateWindowBounds(view2, rectF2);
        int i5 = positioning.gravity & 7;
        if (i5 == 1) {
            fCenterX = rectF2.centerX();
            fCenterX2 = rectF.centerX();
        } else if (i5 == 3) {
            fCenterX = rectF2.left;
            fCenterX2 = rectF.left;
        } else {
            if (i5 != 5) {
                f2 = 0.0f;
                return f2 + positioning.xAdjustment;
            }
            fCenterX = rectF2.right;
            fCenterX2 = rectF.right;
        }
        f2 = fCenterX - fCenterX2;
        return f2 + positioning.xAdjustment;
    }

    private float calculateTranslationY(View view, View view2, Positioning positioning) {
        float fCenterY;
        float fCenterY2;
        float f2;
        RectF rectF = this.tmpRectF1;
        RectF rectF2 = this.tmpRectF2;
        calculateDependencyWindowBounds(view, rectF);
        calculateWindowBounds(view2, rectF2);
        int i5 = positioning.gravity & 112;
        if (i5 == 16) {
            fCenterY = rectF2.centerY();
            fCenterY2 = rectF.centerY();
        } else if (i5 == 48) {
            fCenterY = rectF2.top;
            fCenterY2 = rectF.top;
        } else {
            if (i5 != 80) {
                f2 = 0.0f;
                return f2 + positioning.yAdjustment;
            }
            fCenterY = rectF2.bottom;
            fCenterY2 = rectF.bottom;
        }
        f2 = fCenterY - fCenterY2;
        return f2 + positioning.yAdjustment;
    }

    private float calculateValueOfAnimationAtEndOfExpansion(FabTransformationSpec fabTransformationSpec, MotionTiming motionTiming, float f2, float f7) {
        long delay = motionTiming.getDelay();
        long duration = motionTiming.getDuration();
        MotionTiming timing = fabTransformationSpec.timings.getTiming("expansion");
        return AnimationUtils.lerp(f2, f7, motionTiming.getInterpolator().getInterpolation((((timing.getDuration() + timing.getDelay()) + 17) - delay) / duration));
    }

    private void calculateWindowBounds(View view, RectF rectF) {
        rectF.set(0.0f, 0.0f, view.getWidth(), view.getHeight());
        view.getLocationInWindow(this.tmpArray);
        rectF.offsetTo(r3[0], r3[1]);
        rectF.offset((int) (-view.getTranslationX()), (int) (-view.getTranslationY()));
    }

    private void createChildrenFadeAnimation(View view, View view2, boolean z9, boolean z10, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ViewGroup viewGroupCalculateChildContentContainer;
        ObjectAnimator objectAnimatorOfFloat;
        if (view2 instanceof ViewGroup) {
            if (((view2 instanceof CircularRevealWidget) && CircularRevealHelper.STRATEGY == 0) || (viewGroupCalculateChildContentContainer = calculateChildContentContainer(view2)) == null) {
                return;
            }
            if (z9) {
                if (!z10) {
                    ChildrenAlphaProperty.CHILDREN_ALPHA.set(viewGroupCalculateChildContentContainer, Float.valueOf(0.0f));
                }
                objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewGroupCalculateChildContentContainer, ChildrenAlphaProperty.CHILDREN_ALPHA, 1.0f);
            } else {
                objectAnimatorOfFloat = ObjectAnimator.ofFloat(viewGroupCalculateChildContentContainer, ChildrenAlphaProperty.CHILDREN_ALPHA, 0.0f);
            }
            fabTransformationSpec.timings.getTiming("contentFade").apply(objectAnimatorOfFloat);
            list.add(objectAnimatorOfFloat);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void createColorAnimation(View view, View view2, boolean z9, boolean z10, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfInt;
        if (view2 instanceof CircularRevealWidget) {
            CircularRevealWidget circularRevealWidget = (CircularRevealWidget) view2;
            int backgroundTint = getBackgroundTint(view);
            int i5 = 16777215 & backgroundTint;
            if (z9) {
                if (!z10) {
                    circularRevealWidget.setCircularRevealScrimColor(backgroundTint);
                }
                objectAnimatorOfInt = ObjectAnimator.ofInt(circularRevealWidget, CircularRevealWidget.CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR, i5);
            } else {
                objectAnimatorOfInt = ObjectAnimator.ofInt(circularRevealWidget, CircularRevealWidget.CircularRevealScrimColorProperty.CIRCULAR_REVEAL_SCRIM_COLOR, backgroundTint);
            }
            objectAnimatorOfInt.setEvaluator(ArgbEvaluatorCompat.getInstance());
            fabTransformationSpec.timings.getTiming("color").apply(objectAnimatorOfInt);
            list.add(objectAnimatorOfInt);
        }
    }

    private void createDependencyTranslationAnimation(View view, View view2, boolean z9, FabTransformationSpec fabTransformationSpec, List<Animator> list) {
        float fCalculateTranslationX = calculateTranslationX(view, view2, fabTransformationSpec.positioning);
        float fCalculateTranslationY = calculateTranslationY(view, view2, fabTransformationSpec.positioning);
        Pair<MotionTiming, MotionTiming> pairCalculateMotionTiming = calculateMotionTiming(fCalculateTranslationX, fCalculateTranslationY, z9, fabTransformationSpec);
        MotionTiming motionTiming = (MotionTiming) pairCalculateMotionTiming.first;
        MotionTiming motionTiming2 = (MotionTiming) pairCalculateMotionTiming.second;
        Property property = View.TRANSLATION_X;
        if (!z9) {
            fCalculateTranslationX = this.dependencyOriginalTranslationX;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) property, fCalculateTranslationX);
        Property property2 = View.TRANSLATION_Y;
        if (!z9) {
            fCalculateTranslationY = this.dependencyOriginalTranslationY;
        }
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, (Property<View, Float>) property2, fCalculateTranslationY);
        motionTiming.apply(objectAnimatorOfFloat);
        motionTiming2.apply(objectAnimatorOfFloat2);
        list.add(objectAnimatorOfFloat);
        list.add(objectAnimatorOfFloat2);
    }

    @TargetApi(21)
    private void createElevationAnimation(View view, View view2, boolean z9, boolean z10, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfFloat;
        WeakHashMap weakHashMap = W.f7199a;
        float fI = M.i(view2) - M.i(view);
        if (z9) {
            if (!z10) {
                view2.setTranslationZ(-fI);
            }
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Z, 0.0f);
        } else {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Z, -fI);
        }
        fabTransformationSpec.timings.getTiming("elevation").apply(objectAnimatorOfFloat);
        list.add(objectAnimatorOfFloat);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void createExpansionAnimation(View view, View view2, boolean z9, boolean z10, FabTransformationSpec fabTransformationSpec, float f2, float f7, List<Animator> list, List<Animator.AnimatorListener> list2) {
        Animator animatorCreateCircularReveal;
        if (view2 instanceof CircularRevealWidget) {
            final CircularRevealWidget circularRevealWidget = (CircularRevealWidget) view2;
            float fCalculateRevealCenterX = calculateRevealCenterX(view, view2, fabTransformationSpec.positioning);
            float fCalculateRevealCenterY = calculateRevealCenterY(view, view2, fabTransformationSpec.positioning);
            ((FloatingActionButton) view).getContentRect(this.tmpRect);
            float fWidth = this.tmpRect.width() / 2.0f;
            MotionTiming timing = fabTransformationSpec.timings.getTiming("expansion");
            if (z9) {
                if (!z10) {
                    circularRevealWidget.setRevealInfo(new CircularRevealWidget.RevealInfo(fCalculateRevealCenterX, fCalculateRevealCenterY, fWidth));
                }
                if (z10) {
                    fWidth = circularRevealWidget.getRevealInfo().radius;
                }
                animatorCreateCircularReveal = CircularRevealCompat.createCircularReveal(circularRevealWidget, fCalculateRevealCenterX, fCalculateRevealCenterY, MathUtils.distanceToFurthestCorner(fCalculateRevealCenterX, fCalculateRevealCenterY, 0.0f, 0.0f, f2, f7));
                animatorCreateCircularReveal.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.transformation.FabTransformationBehavior.4
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        CircularRevealWidget.RevealInfo revealInfo = circularRevealWidget.getRevealInfo();
                        revealInfo.radius = Float.MAX_VALUE;
                        circularRevealWidget.setRevealInfo(revealInfo);
                    }
                });
                createPreFillRadialExpansion(view2, timing.getDelay(), (int) fCalculateRevealCenterX, (int) fCalculateRevealCenterY, fWidth, list);
            } else {
                float f9 = circularRevealWidget.getRevealInfo().radius;
                Animator animatorCreateCircularReveal2 = CircularRevealCompat.createCircularReveal(circularRevealWidget, fCalculateRevealCenterX, fCalculateRevealCenterY, fWidth);
                int i5 = (int) fCalculateRevealCenterX;
                int i7 = (int) fCalculateRevealCenterY;
                createPreFillRadialExpansion(view2, timing.getDelay(), i5, i7, f9, list);
                createPostFillRadialExpansion(view2, timing.getDelay(), timing.getDuration(), fabTransformationSpec.timings.getTotalDuration(), i5, i7, fWidth, list);
                animatorCreateCircularReveal = animatorCreateCircularReveal2;
            }
            timing.apply(animatorCreateCircularReveal);
            list.add(animatorCreateCircularReveal);
            list2.add(CircularRevealCompat.createCircularRevealListener(circularRevealWidget));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void createIconFadeAnimation(View view, final View view2, boolean z9, boolean z10, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<Animator.AnimatorListener> list2) {
        ObjectAnimator objectAnimatorOfInt;
        if ((view2 instanceof CircularRevealWidget) && (view instanceof ImageView)) {
            final CircularRevealWidget circularRevealWidget = (CircularRevealWidget) view2;
            final Drawable drawable = ((ImageView) view).getDrawable();
            if (drawable == null) {
                return;
            }
            drawable.mutate();
            if (z9) {
                if (!z10) {
                    drawable.setAlpha(255);
                }
                objectAnimatorOfInt = ObjectAnimator.ofInt(drawable, DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT, 0);
            } else {
                objectAnimatorOfInt = ObjectAnimator.ofInt(drawable, DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT, 255);
            }
            objectAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.transformation.FabTransformationBehavior.2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    view2.invalidate();
                }
            });
            fabTransformationSpec.timings.getTiming("iconFade").apply(objectAnimatorOfInt);
            list.add(objectAnimatorOfInt);
            list2.add(new AnimatorListenerAdapter() { // from class: com.google.android.material.transformation.FabTransformationBehavior.3
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    circularRevealWidget.setCircularRevealOverlayDrawable(null);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    circularRevealWidget.setCircularRevealOverlayDrawable(drawable);
                }
            });
        }
    }

    private void createPostFillRadialExpansion(View view, long j5, long j9, long j10, int i5, int i7, float f2, List<Animator> list) {
        long j11 = j5 + j9;
        if (j11 < j10) {
            Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(view, i5, i7, f2, f2);
            animatorCreateCircularReveal.setStartDelay(j11);
            animatorCreateCircularReveal.setDuration(j10 - j11);
            list.add(animatorCreateCircularReveal);
        }
    }

    private void createPreFillRadialExpansion(View view, long j5, int i5, int i7, float f2, List<Animator> list) {
        if (j5 > 0) {
            Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal(view, i5, i7, f2, f2);
            animatorCreateCircularReveal.setStartDelay(0L);
            animatorCreateCircularReveal.setDuration(j5);
            list.add(animatorCreateCircularReveal);
        }
    }

    private void createTranslationAnimation(View view, View view2, boolean z9, boolean z10, FabTransformationSpec fabTransformationSpec, List<Animator> list, List<Animator.AnimatorListener> list2, RectF rectF) {
        ObjectAnimator objectAnimatorOfFloat;
        ObjectAnimator objectAnimatorOfFloat2;
        float fCalculateTranslationX = calculateTranslationX(view, view2, fabTransformationSpec.positioning);
        float fCalculateTranslationY = calculateTranslationY(view, view2, fabTransformationSpec.positioning);
        Pair<MotionTiming, MotionTiming> pairCalculateMotionTiming = calculateMotionTiming(fCalculateTranslationX, fCalculateTranslationY, z9, fabTransformationSpec);
        MotionTiming motionTiming = (MotionTiming) pairCalculateMotionTiming.first;
        MotionTiming motionTiming2 = (MotionTiming) pairCalculateMotionTiming.second;
        if (z9) {
            if (!z10) {
                view2.setTranslationX(-fCalculateTranslationX);
                view2.setTranslationY(-fCalculateTranslationY);
            }
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_X, 0.0f);
            objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Y, 0.0f);
            calculateChildVisibleBoundsAtEndOfExpansion(view2, fabTransformationSpec, motionTiming, motionTiming2, -fCalculateTranslationX, -fCalculateTranslationY, 0.0f, 0.0f, rectF);
        } else {
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_X, -fCalculateTranslationX);
            objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view2, (Property<View, Float>) View.TRANSLATION_Y, -fCalculateTranslationY);
        }
        motionTiming.apply(objectAnimatorOfFloat);
        motionTiming2.apply(objectAnimatorOfFloat2);
        list.add(objectAnimatorOfFloat);
        list.add(objectAnimatorOfFloat2);
    }

    private int getBackgroundTint(View view) {
        WeakHashMap weakHashMap = W.f7199a;
        ColorStateList colorStateListG = M.g(view);
        if (colorStateListG != null) {
            return colorStateListG.getColorForState(view.getDrawableState(), colorStateListG.getDefaultColor());
        }
        return 0;
    }

    private ViewGroup toViewGroupOrNull(View view) {
        if (view instanceof ViewGroup) {
            return (ViewGroup) view;
        }
        return null;
    }

    @Override // com.google.android.material.transformation.ExpandableBehavior, androidx.coordinatorlayout.widget.d
    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, View view, View view2) {
        if (view.getVisibility() == 8) {
            throw new IllegalStateException("This behavior cannot be attached to a GONE view. Set the view to INVISIBLE instead.");
        }
        if (!(view2 instanceof FloatingActionButton)) {
            return false;
        }
        int expandedComponentIdHint = ((FloatingActionButton) view2).getExpandedComponentIdHint();
        return expandedComponentIdHint == 0 || expandedComponentIdHint == view.getId();
    }

    @Override // androidx.coordinatorlayout.widget.d
    public void onAttachedToLayoutParams(g gVar) {
        if (gVar.h == 0) {
            gVar.h = 80;
        }
    }

    @Override // com.google.android.material.transformation.ExpandableTransformationBehavior
    public AnimatorSet onCreateExpandedStateChangeAnimation(final View view, final View view2, final boolean z9, boolean z10) {
        FabTransformationSpec fabTransformationSpecOnCreateMotionSpec = onCreateMotionSpec(view2.getContext(), z9);
        if (z9) {
            this.dependencyOriginalTranslationX = view.getTranslationX();
            this.dependencyOriginalTranslationY = view.getTranslationY();
        }
        List<Animator> arrayList = new ArrayList<>();
        ArrayList arrayList2 = new ArrayList();
        createElevationAnimation(view, view2, z9, z10, fabTransformationSpecOnCreateMotionSpec, arrayList, arrayList2);
        RectF rectF = this.tmpRectF1;
        createTranslationAnimation(view, view2, z9, z10, fabTransformationSpecOnCreateMotionSpec, arrayList, arrayList2, rectF);
        float fWidth = rectF.width();
        float fHeight = rectF.height();
        createDependencyTranslationAnimation(view, view2, z9, fabTransformationSpecOnCreateMotionSpec, arrayList);
        createIconFadeAnimation(view, view2, z9, z10, fabTransformationSpecOnCreateMotionSpec, arrayList, arrayList2);
        createExpansionAnimation(view, view2, z9, z10, fabTransformationSpecOnCreateMotionSpec, fWidth, fHeight, arrayList, arrayList2);
        createColorAnimation(view, view2, z9, z10, fabTransformationSpecOnCreateMotionSpec, arrayList, arrayList2);
        createChildrenFadeAnimation(view, view2, z9, z10, fabTransformationSpecOnCreateMotionSpec, arrayList, arrayList2);
        AnimatorSet animatorSet = new AnimatorSet();
        AnimatorSetCompat.playTogether(animatorSet, arrayList);
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.transformation.FabTransformationBehavior.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (z9) {
                    return;
                }
                view2.setVisibility(4);
                view.setAlpha(1.0f);
                view.setVisibility(0);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                if (z9) {
                    view2.setVisibility(0);
                    view.setAlpha(0.0f);
                    view.setVisibility(4);
                }
            }
        });
        int size = arrayList2.size();
        for (int i5 = 0; i5 < size; i5++) {
            animatorSet.addListener((Animator.AnimatorListener) arrayList2.get(i5));
        }
        return animatorSet;
    }

    public abstract FabTransformationSpec onCreateMotionSpec(Context context, boolean z9);

    public FabTransformationBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.tmpRect = new Rect();
        this.tmpRectF1 = new RectF();
        this.tmpRectF2 = new RectF();
        this.tmpArray = new int[2];
    }
}
