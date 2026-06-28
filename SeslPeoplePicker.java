package com.google.android.material.chip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.google.android.material.R;
import com.google.android.material.chip.SeslExpandableContainer;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class SeslPeoplePicker extends FrameLayout {
    private final SeslChipGroup mChipGroup;
    private final SeslExpandableContainer mContainer;
    private boolean mInitialized;
    private final boolean mIsRtl;
    private OnChipAddListener mOnChipAddListener;
    private OnChipRemovedListener mOnChipRemovedListener;
    private OnExpansionButtonClickedListener mOnExpansionButtonClickedListener;

    /* JADX INFO: renamed from: com.google.android.material.chip.SeslPeoplePicker$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        public AnonymousClass4() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$onAnimationEnd$0(SeslPeoplePicker seslPeoplePicker) {
            seslPeoplePicker.updateFloatWhenExpanded();
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            ViewGroup.LayoutParams layoutParams = SeslPeoplePicker.this.mContainer.getLayoutParams();
            layoutParams.height = -2;
            SeslPeoplePicker.this.mContainer.setLayoutParams(layoutParams);
            SeslPeoplePicker.this.mContainer.getExpansionButton().startDisappearTimer();
            SeslPeoplePicker.this.mContainer.post(new m(SeslPeoplePicker.this, 0));
            SeslPeoplePicker.this.mChipGroup.enableSeslLayoutTransitions(true);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            SeslPeoplePicker.this.mChipGroup.setAlpha(0.0f);
            SeslPeoplePicker.this.mChipGroup.setSingleLine(!SeslPeoplePicker.this.mContainer.isExpanded());
            SeslPeoplePicker.this.mChipGroup.enableSeslLayoutTransitions(false);
        }
    }

    public interface OnChipAddListener {
        void onChipAdded();
    }

    public interface OnChipRemovedListener {
        void onChipRemoved();
    }

    public interface OnExpansionButtonClickedListener {
        void onExpansionButtonCLicked();
    }

    public SeslPeoplePicker(Context context) {
        this(context, null);
    }

    public static /* synthetic */ void access$200(SeslPeoplePicker seslPeoplePicker) {
        seslPeoplePicker.updateFloatWhenExpanded();
    }

    private int getChipGroupZRowWidth() {
        int paddingStart = this.mChipGroup.getPaddingStart();
        int paddingEnd = this.mChipGroup.getPaddingEnd();
        int chipSpacingHorizontal = this.mChipGroup.getChipSpacingHorizontal();
        int width = this.mChipGroup.getChildAt(0).getWidth() + paddingStart + paddingEnd + chipSpacingHorizontal;
        int width2 = getWidth();
        for (int i5 = 1; i5 < this.mChipGroup.getChildCount(); i5++) {
            int width3 = ((Chip) this.mChipGroup.getChildAt(i5)).getWidth();
            if (width + width3 >= width2) {
                break;
            }
            width += width3 + chipSpacingHorizontal;
        }
        return (width - chipSpacingHorizontal) - paddingEnd;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setListeners$0() {
        startExpandingAnimation();
        OnExpansionButtonClickedListener onExpansionButtonClickedListener = this.mOnExpansionButtonClickedListener;
        if (onExpansionButtonClickedListener != null) {
            onExpansionButtonClickedListener.onExpansionButtonCLicked();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setListeners$1() {
        if (this.mChipGroup.getTotalWidth() > getWidth()) {
            int integer = getContext().getResources().getInteger(R.integer.sesl_chip_default_anim_duration);
            this.mContainer.enableFloatChange(false);
            getExpansionButton().setFloated(true);
            if (this.mIsRtl) {
                getExpansionButton().setVisibility(0);
                getExpansionButton().setFloated(true);
                this.mContainer.fullScrollToLeft(integer, 0);
            } else {
                this.mContainer.fullScrollToRight(integer, 0);
            }
            long j5 = integer;
            new CountDownTimer(j5, j5) { // from class: com.google.android.material.chip.SeslPeoplePicker.1
                @Override // android.os.CountDownTimer
                public void onFinish() {
                    SeslPeoplePicker.this.mContainer.enableFloatChange(true);
                    SeslPeoplePicker.this.getExpansionButton().setFloated(false);
                }

                @Override // android.os.CountDownTimer
                public void onTick(long j9) {
                }
            }.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setListeners$2(Context context) {
        if (!this.mInitialized) {
            this.mInitialized = true;
            SeslExpansionButton expansionButton = this.mContainer.getExpansionButton();
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) expansionButton.getLayoutParams();
            int dimension = (int) context.getResources().getDimension(R.dimen.expansion_button_margin_top);
            int dimension2 = (int) context.getResources().getDimension(R.dimen.expansion_button_margin_right);
            if (this.mIsRtl) {
                layoutParams.setMargins(dimension2, dimension, 0, 0);
            } else {
                layoutParams.setMargins(0, dimension, dimension2, 0);
            }
            expansionButton.setLayoutParams(layoutParams);
        }
        if (this.mChipGroup.getChildCount() == 1) {
            startShowingAnimation(context);
        }
        if (this.mContainer.isExpanded()) {
            this.mChipGroup.post(new m(this, 1));
        } else {
            post(new m(this, 2));
        }
        OnChipAddListener onChipAddListener = this.mOnChipAddListener;
        if (onChipAddListener != null) {
            onChipAddListener.onChipAdded();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setListeners$3(Context context) {
        if (this.mContainer.isExpanded()) {
            this.mContainer.post(new m(this, 1));
        }
        if (this.mChipGroup.getChildCount() == 0) {
            this.mContainer.tempHideExpansionButton();
            startHidingAnimation(context);
        }
        OnChipRemovedListener onChipRemovedListener = this.mOnChipRemovedListener;
        if (onChipRemovedListener != null) {
            onChipRemovedListener.onChipRemoved();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startExpandingAnimation$6(int i5, float f2, int i7, int i9, int i10, ValueAnimator valueAnimator) {
        ViewGroup.LayoutParams layoutParams = this.mContainer.getLayoutParams();
        layoutParams.height = i5 + ((int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * f2));
        this.mContainer.setLayoutParams(layoutParams);
        float fFloatValue = (((Float) valueAnimator.getAnimatedValue()).floatValue() * (i7 - i9)) / i10;
        if (fFloatValue > 0.0f) {
            this.mChipGroup.setAlpha(Math.min(fFloatValue, 1.0f));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startHidingAnimation$5(float f2, ValueAnimator valueAnimator) {
        ViewGroup.LayoutParams layoutParams = this.mContainer.getLayoutParams();
        layoutParams.height = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * f2);
        this.mContainer.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startShowingAnimation$4(float f2, ValueAnimator valueAnimator) {
        ViewGroup.LayoutParams layoutParams = this.mContainer.getLayoutParams();
        layoutParams.height = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * f2);
        this.mContainer.setLayoutParams(layoutParams);
    }

    private void setListeners(Context context) {
        this.mContainer.setOnExpansionButtonClickedListener(new SeslExpandableContainer.OnExpansionButtonClickedListener() { // from class: com.google.android.material.chip.i
            @Override // com.google.android.material.chip.SeslExpandableContainer.OnExpansionButtonClickedListener
            public final void onClick() {
                this.f10040a.lambda$setListeners$0();
            }
        });
        this.mChipGroup.setOnChipAddListener(new j(this, context));
        this.mChipGroup.setOnChipRemovedListener(new j(this, context));
    }

    private void startExpandingAnimation() {
        final int i5;
        int internalHeight = this.mChipGroup.getInternalHeight(getWidth());
        int paddingBottom = this.mChipGroup.getPaddingBottom() + this.mChipGroup.getPaddingTop() + this.mChipGroup.getChildAt(0).getHeight();
        if (this.mContainer.isExpanded()) {
            i5 = paddingBottom;
        } else {
            i5 = internalHeight;
            internalHeight = paddingBottom;
        }
        final float f2 = internalHeight - i5;
        Context context = getContext();
        final int integer = context.getResources().getInteger(R.integer.sesl_chip_default_anim_duration);
        final int integer2 = context.getResources().getInteger(R.integer.sesl_people_picker_alpha_duration);
        final int integer3 = context.getResources().getInteger(R.integer.sesl_people_picker_alpha_delay);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setDuration(integer);
        valueAnimatorOfFloat.setInterpolator(AnimationUtils.loadInterpolator(context, com.samsung.android.keyscafe.R.interpolator.sesl_chip_default_interpolator));
        valueAnimatorOfFloat.addListener(new AnonymousClass4());
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.chip.k
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f10043e.lambda$startExpandingAnimation$6(i5, f2, integer, integer3, integer2, valueAnimator);
            }
        });
        valueAnimatorOfFloat.start();
    }

    private void startHidingAnimation(Context context) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat.setDuration(context.getResources().getInteger(R.integer.sesl_chip_default_anim_duration));
        valueAnimatorOfFloat.setInterpolator(AnimationUtils.loadInterpolator(context, com.samsung.android.keyscafe.R.interpolator.sesl_chip_default_interpolator));
        valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.chip.SeslPeoplePicker.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                ViewGroup.LayoutParams layoutParams = SeslPeoplePicker.this.mContainer.getLayoutParams();
                layoutParams.height = -2;
                SeslPeoplePicker.this.mContainer.setLayoutParams(layoutParams);
            }
        });
        valueAnimatorOfFloat.addUpdateListener(new l(this, this.mContainer.getHeight(), 0));
        valueAnimatorOfFloat.start();
    }

    private void startShowingAnimation(Context context) {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setDuration(context.getResources().getInteger(R.integer.sesl_chip_default_anim_duration));
        valueAnimatorOfFloat.setInterpolator(AnimationUtils.loadInterpolator(context, com.samsung.android.keyscafe.R.interpolator.sesl_chip_default_interpolator));
        valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.chip.SeslPeoplePicker.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                ViewGroup.LayoutParams layoutParams = SeslPeoplePicker.this.mContainer.getLayoutParams();
                layoutParams.height = -2;
                SeslPeoplePicker.this.mContainer.setLayoutParams(layoutParams);
            }
        });
        valueAnimatorOfFloat.addUpdateListener(new l(this, context.getResources().getDimension(R.dimen.chip_height) + this.mChipGroup.getPaddingTop() + this.mChipGroup.getPaddingBottom(), 1));
        valueAnimatorOfFloat.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFloatWhenExpanded() {
        if (!this.mContainer.isExpanded() || this.mChipGroup.getChildCount() <= 0) {
            return;
        }
        SeslExpansionButton expansionButton = getExpansionButton();
        if (expansionButton.getVisibility() != 0) {
            expansionButton.setVisibility(0);
        }
        if (getChipGroupZRowWidth() >= getWidth() - getExpansionButton().getWidth()) {
            expansionButton.setFloated(true);
        } else {
            expansionButton.setFloated(false);
        }
    }

    public SeslChipGroup getChipGroup() {
        return this.mChipGroup;
    }

    public SeslExpandableContainer getChipGroupContainer() {
        return this.mContainer;
    }

    public SeslExpansionButton getExpansionButton() {
        return this.mContainer.getExpansionButton();
    }

    public void setExpansionBackgroundImage(Drawable drawable) {
        this.mContainer.setExpansionBackGroundImage(drawable);
    }

    public void setExpansionImageDrawable(Drawable drawable) {
        this.mContainer.setExpansionImageDrawable(drawable);
    }

    public void setOnChipAddListener(OnChipAddListener onChipAddListener) {
        this.mOnChipAddListener = onChipAddListener;
    }

    public void setOnChipRemovedListener(OnChipRemovedListener onChipRemovedListener) {
        this.mOnChipRemovedListener = onChipRemovedListener;
    }

    public void setOnExpansionButtonClickedListener(OnExpansionButtonClickedListener onExpansionButtonClickedListener) {
        this.mOnExpansionButtonClickedListener = onExpansionButtonClickedListener;
    }

    public SeslPeoplePicker(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public SeslPeoplePicker(Context context, AttributeSet attributeSet, int i5) {
        this(context, attributeSet, i5, -1);
    }

    public SeslPeoplePicker(Context context, AttributeSet attributeSet, int i5, int i7) {
        super(context, attributeSet, i5, i7);
        this.mIsRtl = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.sesl_people_picker_layout, (ViewGroup) this, true);
        SeslChipGroup seslChipGroup = (SeslChipGroup) viewInflate.findViewById(R.id.chip);
        this.mChipGroup = seslChipGroup;
        seslChipGroup.setSingleLine(true);
        SeslExpandableContainer seslExpandableContainer = (SeslExpandableContainer) viewInflate.findViewById(R.id.container);
        this.mContainer = seslExpandableContainer;
        seslExpandableContainer.enableFadingAnimation(true);
        setListeners(context);
    }
}
