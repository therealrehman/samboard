package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.C0154h0;
import androidx.core.view.W;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.animation.AnimatorSetCompat;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
final class IndicatorViewController {
    private static final int CAPTION_STATE_ERROR = 1;
    private static final int CAPTION_STATE_HELPER_TEXT = 2;
    private static final int CAPTION_STATE_NONE = 0;
    static final int COUNTER_INDEX = 2;
    private static final int DEFAULT_CAPTION_FADE_ANIMATION_DURATION = 167;
    private static final int DEFAULT_CAPTION_TRANSLATION_Y_ANIMATION_DURATION = 217;
    static final int ERROR_INDEX = 0;
    static final int HELPER_INDEX = 1;
    private Animator captionAnimator;
    private FrameLayout captionArea;
    private int captionDisplayed;
    private final int captionFadeInAnimationDuration;
    private final TimeInterpolator captionFadeInAnimationInterpolator;
    private final int captionFadeOutAnimationDuration;
    private final TimeInterpolator captionFadeOutAnimationInterpolator;
    private int captionToShow;
    private final int captionTranslationYAnimationDuration;
    private final TimeInterpolator captionTranslationYAnimationInterpolator;
    private final float captionTranslationYPx;
    private final Context context;
    private boolean errorEnabled;
    private CharSequence errorText;
    private int errorTextAppearance;
    private TextView errorView;
    private int errorViewAccessibilityLiveRegion;
    private CharSequence errorViewContentDescription;
    private ColorStateList errorViewTextColor;
    private CharSequence helperText;
    private boolean helperTextEnabled;
    private int helperTextTextAppearance;
    private TextView helperTextView;
    private ColorStateList helperTextViewTextColor;
    private LinearLayout indicatorArea;
    private int indicatorsAdded;
    private final TextInputLayout textInputView;
    private Typeface typeface;

    public IndicatorViewController(TextInputLayout textInputLayout) {
        Context context = textInputLayout.getContext();
        this.context = context;
        this.textInputView = textInputLayout;
        this.captionTranslationYPx = context.getResources().getDimensionPixelSize(R.dimen.design_textinput_caption_translate_y);
        int i5 = R.attr.motionDurationShort4;
        this.captionTranslationYAnimationDuration = MotionUtils.resolveThemeDuration(context, i5, DEFAULT_CAPTION_TRANSLATION_Y_ANIMATION_DURATION);
        this.captionFadeInAnimationDuration = MotionUtils.resolveThemeDuration(context, R.attr.motionDurationMedium4, DEFAULT_CAPTION_FADE_ANIMATION_DURATION);
        this.captionFadeOutAnimationDuration = MotionUtils.resolveThemeDuration(context, i5, DEFAULT_CAPTION_FADE_ANIMATION_DURATION);
        int i7 = R.attr.motionEasingEmphasizedDecelerateInterpolator;
        this.captionTranslationYAnimationInterpolator = MotionUtils.resolveThemeInterpolator(context, i7, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        TimeInterpolator timeInterpolator = AnimationUtils.LINEAR_INTERPOLATOR;
        this.captionFadeInAnimationInterpolator = MotionUtils.resolveThemeInterpolator(context, i7, timeInterpolator);
        this.captionFadeOutAnimationInterpolator = MotionUtils.resolveThemeInterpolator(context, R.attr.motionEasingLinearInterpolator, timeInterpolator);
    }

    private boolean canAdjustIndicatorPadding() {
        return (this.indicatorArea == null || this.textInputView.getEditText() == null) ? false : true;
    }

    private void createCaptionAnimators(List<Animator> list, boolean z9, TextView textView, int i5, int i7, int i9) {
        if (textView == null || !z9) {
            return;
        }
        if (i5 == i9 || i5 == i7) {
            ObjectAnimator objectAnimatorCreateCaptionOpacityAnimator = createCaptionOpacityAnimator(textView, i9 == i5);
            if (i5 == i9 && i7 != 0) {
                objectAnimatorCreateCaptionOpacityAnimator.setStartDelay(this.captionFadeOutAnimationDuration);
            }
            list.add(objectAnimatorCreateCaptionOpacityAnimator);
            if (i9 != i5 || i7 == 0) {
                return;
            }
            ObjectAnimator objectAnimatorCreateCaptionTranslationYAnimator = createCaptionTranslationYAnimator(textView);
            objectAnimatorCreateCaptionTranslationYAnimator.setStartDelay(this.captionFadeOutAnimationDuration);
            list.add(objectAnimatorCreateCaptionTranslationYAnimator);
        }
    }

    private ObjectAnimator createCaptionOpacityAnimator(TextView textView, boolean z9) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.ALPHA, z9 ? 1.0f : 0.0f);
        objectAnimatorOfFloat.setDuration(z9 ? this.captionFadeInAnimationDuration : this.captionFadeOutAnimationDuration);
        objectAnimatorOfFloat.setInterpolator(z9 ? this.captionFadeInAnimationInterpolator : this.captionFadeOutAnimationInterpolator);
        return objectAnimatorOfFloat;
    }

    private ObjectAnimator createCaptionTranslationYAnimator(TextView textView) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.TRANSLATION_Y, -this.captionTranslationYPx, 0.0f);
        objectAnimatorOfFloat.setDuration(this.captionTranslationYAnimationDuration);
        objectAnimatorOfFloat.setInterpolator(this.captionTranslationYAnimationInterpolator);
        return objectAnimatorOfFloat;
    }

    private TextView getCaptionViewFromDisplayState(int i5) {
        if (i5 == 1) {
            return this.errorView;
        }
        if (i5 != 2) {
            return null;
        }
        return this.helperTextView;
    }

    private int getIndicatorPadding(boolean z9, int i5, int i7) {
        return z9 ? this.context.getResources().getDimensionPixelSize(i5) : i7;
    }

    private boolean isCaptionStateError(int i5) {
        return (i5 != 1 || this.errorView == null || TextUtils.isEmpty(this.errorText)) ? false : true;
    }

    private boolean isCaptionStateHelperText(int i5) {
        return (i5 != 2 || this.helperTextView == null || TextUtils.isEmpty(this.helperText)) ? false : true;
    }

    private void setCaptionViewVisibilities(int i5, int i7) {
        TextView captionViewFromDisplayState;
        TextView captionViewFromDisplayState2;
        if (i5 == i7) {
            return;
        }
        if (i7 != 0 && (captionViewFromDisplayState2 = getCaptionViewFromDisplayState(i7)) != null) {
            captionViewFromDisplayState2.setVisibility(0);
            captionViewFromDisplayState2.setAlpha(1.0f);
        }
        if (i5 != 0 && (captionViewFromDisplayState = getCaptionViewFromDisplayState(i5)) != null) {
            captionViewFromDisplayState.setVisibility(4);
            if (i5 == 1) {
                captionViewFromDisplayState.setText((CharSequence) null);
            }
        }
        this.captionDisplayed = i7;
    }

    private void setTextViewTypeface(TextView textView, Typeface typeface) {
        if (textView != null) {
            textView.setTypeface(typeface);
        }
    }

    private void setViewGroupGoneIfEmpty(ViewGroup viewGroup, int i5) {
        if (i5 == 0) {
            viewGroup.setVisibility(8);
        }
    }

    private boolean shouldAnimateCaptionView(TextView textView, CharSequence charSequence) {
        TextInputLayout textInputLayout = this.textInputView;
        WeakHashMap weakHashMap = W.f7199a;
        return textInputLayout.isLaidOut() && this.textInputView.isEnabled() && !(this.captionToShow == this.captionDisplayed && textView != null && TextUtils.equals(textView.getText(), charSequence));
    }

    private void updateCaptionViewsVisibility(final int i5, final int i7, boolean z9) {
        if (i5 == i7) {
            return;
        }
        if (z9) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.captionAnimator = animatorSet;
            ArrayList arrayList = new ArrayList();
            createCaptionAnimators(arrayList, this.helperTextEnabled, this.helperTextView, 2, i5, i7);
            createCaptionAnimators(arrayList, this.errorEnabled, this.errorView, 1, i5, i7);
            AnimatorSetCompat.playTogether(animatorSet, arrayList);
            final TextView captionViewFromDisplayState = getCaptionViewFromDisplayState(i5);
            final TextView captionViewFromDisplayState2 = getCaptionViewFromDisplayState(i7);
            animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.textfield.IndicatorViewController.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    IndicatorViewController.this.captionDisplayed = i7;
                    IndicatorViewController.this.captionAnimator = null;
                    TextView textView = captionViewFromDisplayState;
                    if (textView != null) {
                        textView.setVisibility(4);
                        if (i5 == 1 && IndicatorViewController.this.errorView != null) {
                            IndicatorViewController.this.errorView.setText((CharSequence) null);
                        }
                    }
                    TextView textView2 = captionViewFromDisplayState2;
                    if (textView2 != null) {
                        textView2.setTranslationY(0.0f);
                        captionViewFromDisplayState2.setAlpha(1.0f);
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationStart(Animator animator) {
                    TextView textView = captionViewFromDisplayState2;
                    if (textView != null) {
                        textView.setVisibility(0);
                        captionViewFromDisplayState2.setAlpha(0.0f);
                    }
                }
            });
            animatorSet.start();
        } else {
            setCaptionViewVisibilities(i5, i7);
        }
        this.textInputView.updateEditTextBackground();
        this.textInputView.updateLabelState(z9);
        this.textInputView.updateTextInputBoxState();
    }

    public void addIndicator(TextView textView, int i5) {
        if (this.indicatorArea == null && this.captionArea == null) {
            LinearLayout linearLayout = new LinearLayout(this.context);
            this.indicatorArea = linearLayout;
            linearLayout.setOrientation(0);
            this.textInputView.addView(this.indicatorArea, -1, -2);
            this.captionArea = new FrameLayout(this.context);
            this.indicatorArea.addView(this.captionArea, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (this.textInputView.getEditText() != null) {
                adjustIndicatorPadding();
            }
        }
        if (isCaptionView(i5)) {
            this.captionArea.setVisibility(0);
            this.captionArea.addView(textView);
        } else {
            this.indicatorArea.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        }
        this.indicatorArea.setVisibility(0);
        this.indicatorsAdded++;
    }

    public void adjustIndicatorPadding() {
        if (canAdjustIndicatorPadding()) {
            EditText editText = this.textInputView.getEditText();
            boolean zIsFontScaleAtLeast1_3 = MaterialResources.isFontScaleAtLeast1_3(this.context);
            LinearLayout linearLayout = this.indicatorArea;
            int i5 = R.dimen.material_helper_text_font_1_3_padding_horizontal;
            WeakHashMap weakHashMap = W.f7199a;
            linearLayout.setPaddingRelative(getIndicatorPadding(zIsFontScaleAtLeast1_3, i5, editText.getPaddingStart()), getIndicatorPadding(zIsFontScaleAtLeast1_3, R.dimen.material_helper_text_font_1_3_padding_top, this.context.getResources().getDimensionPixelSize(R.dimen.material_helper_text_default_padding_top)), getIndicatorPadding(zIsFontScaleAtLeast1_3, i5, editText.getPaddingEnd()), 0);
        }
    }

    public void cancelCaptionAnimator() {
        Animator animator = this.captionAnimator;
        if (animator != null) {
            animator.cancel();
        }
    }

    public boolean errorIsDisplayed() {
        return isCaptionStateError(this.captionDisplayed);
    }

    public boolean errorShouldBeShown() {
        return isCaptionStateError(this.captionToShow);
    }

    public int getErrorAccessibilityLiveRegion() {
        return this.errorViewAccessibilityLiveRegion;
    }

    public CharSequence getErrorContentDescription() {
        return this.errorViewContentDescription;
    }

    public CharSequence getErrorText() {
        return this.errorText;
    }

    public int getErrorViewCurrentTextColor() {
        TextView textView = this.errorView;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    public ColorStateList getErrorViewTextColors() {
        TextView textView = this.errorView;
        if (textView != null) {
            return textView.getTextColors();
        }
        return null;
    }

    public CharSequence getHelperText() {
        return this.helperText;
    }

    public View getHelperTextView() {
        return this.helperTextView;
    }

    public ColorStateList getHelperTextViewColors() {
        TextView textView = this.helperTextView;
        if (textView != null) {
            return textView.getTextColors();
        }
        return null;
    }

    public int getHelperTextViewCurrentTextColor() {
        TextView textView = this.helperTextView;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    public boolean helperTextIsDisplayed() {
        return isCaptionStateHelperText(this.captionDisplayed);
    }

    public boolean helperTextShouldBeShown() {
        return isCaptionStateHelperText(this.captionToShow);
    }

    public void hideError() {
        this.errorText = null;
        cancelCaptionAnimator();
        if (this.captionDisplayed == 1) {
            if (!this.helperTextEnabled || TextUtils.isEmpty(this.helperText)) {
                this.captionToShow = 0;
            } else {
                this.captionToShow = 2;
            }
        }
        updateCaptionViewsVisibility(this.captionDisplayed, this.captionToShow, shouldAnimateCaptionView(this.errorView, ""));
    }

    public void hideHelperText() {
        cancelCaptionAnimator();
        int i5 = this.captionDisplayed;
        if (i5 == 2) {
            this.captionToShow = 0;
        }
        updateCaptionViewsVisibility(i5, this.captionToShow, shouldAnimateCaptionView(this.helperTextView, ""));
    }

    public boolean isCaptionView(int i5) {
        return i5 == 0 || i5 == 1;
    }

    public boolean isErrorEnabled() {
        return this.errorEnabled;
    }

    public boolean isHelperTextEnabled() {
        return this.helperTextEnabled;
    }

    public void removeIndicator(TextView textView, int i5) {
        FrameLayout frameLayout;
        if (this.indicatorArea == null) {
            return;
        }
        if (!isCaptionView(i5) || (frameLayout = this.captionArea) == null) {
            this.indicatorArea.removeView(textView);
        } else {
            frameLayout.removeView(textView);
        }
        int i7 = this.indicatorsAdded - 1;
        this.indicatorsAdded = i7;
        setViewGroupGoneIfEmpty(this.indicatorArea, i7);
    }

    public void setErrorAccessibilityLiveRegion(int i5) {
        this.errorViewAccessibilityLiveRegion = i5;
        TextView textView = this.errorView;
        if (textView != null) {
            WeakHashMap weakHashMap = W.f7199a;
            textView.setAccessibilityLiveRegion(i5);
        }
    }

    public void setErrorContentDescription(CharSequence charSequence) {
        this.errorViewContentDescription = charSequence;
        TextView textView = this.errorView;
        if (textView != null) {
            textView.setContentDescription(charSequence);
        }
    }

    public void setErrorEnabled(boolean z9) {
        if (this.errorEnabled == z9) {
            return;
        }
        cancelCaptionAnimator();
        if (z9) {
            C0154h0 c0154h0 = new C0154h0(this.context, null);
            this.errorView = c0154h0;
            c0154h0.setId(R.id.textinput_error);
            this.errorView.setTextAlignment(5);
            Typeface typeface = this.typeface;
            if (typeface != null) {
                this.errorView.setTypeface(typeface);
            }
            setErrorTextAppearance(this.errorTextAppearance);
            setErrorViewTextColor(this.errorViewTextColor);
            setErrorContentDescription(this.errorViewContentDescription);
            setErrorAccessibilityLiveRegion(this.errorViewAccessibilityLiveRegion);
            this.errorView.setVisibility(4);
            addIndicator(this.errorView, 0);
        } else {
            hideError();
            removeIndicator(this.errorView, 0);
            this.errorView = null;
            this.textInputView.updateEditTextBackground();
            this.textInputView.updateTextInputBoxState();
        }
        this.errorEnabled = z9;
    }

    public void setErrorTextAppearance(int i5) {
        this.errorTextAppearance = i5;
        TextView textView = this.errorView;
        if (textView != null) {
            this.textInputView.setTextAppearanceCompatWithErrorFallback(textView, i5);
        }
    }

    public void setErrorViewTextColor(ColorStateList colorStateList) {
        this.errorViewTextColor = colorStateList;
        TextView textView = this.errorView;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    public void setHelperTextAppearance(int i5) {
        this.helperTextTextAppearance = i5;
        TextView textView = this.helperTextView;
        if (textView != null) {
            textView.setTextAppearance(i5);
        }
    }

    public void setHelperTextEnabled(boolean z9) {
        if (this.helperTextEnabled == z9) {
            return;
        }
        cancelCaptionAnimator();
        if (z9) {
            C0154h0 c0154h0 = new C0154h0(this.context, null);
            this.helperTextView = c0154h0;
            c0154h0.setId(R.id.textinput_helper_text);
            this.helperTextView.setTextAlignment(5);
            Typeface typeface = this.typeface;
            if (typeface != null) {
                this.helperTextView.setTypeface(typeface);
            }
            this.helperTextView.setVisibility(4);
            TextView textView = this.helperTextView;
            WeakHashMap weakHashMap = W.f7199a;
            textView.setAccessibilityLiveRegion(1);
            setHelperTextAppearance(this.helperTextTextAppearance);
            setHelperTextViewTextColor(this.helperTextViewTextColor);
            addIndicator(this.helperTextView, 1);
            this.helperTextView.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.google.android.material.textfield.IndicatorViewController.2
                @Override // android.view.View.AccessibilityDelegate
                public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                    super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                    EditText editText = IndicatorViewController.this.textInputView.getEditText();
                    if (editText != null) {
                        accessibilityNodeInfo.setLabeledBy(editText);
                    }
                }
            });
        } else {
            hideHelperText();
            removeIndicator(this.helperTextView, 1);
            this.helperTextView = null;
            this.textInputView.updateEditTextBackground();
            this.textInputView.updateTextInputBoxState();
        }
        this.helperTextEnabled = z9;
    }

    public void setHelperTextViewTextColor(ColorStateList colorStateList) {
        this.helperTextViewTextColor = colorStateList;
        TextView textView = this.helperTextView;
        if (textView == null || colorStateList == null) {
            return;
        }
        textView.setTextColor(colorStateList);
    }

    public void setTypefaces(Typeface typeface) {
        if (typeface != this.typeface) {
            this.typeface = typeface;
            setTextViewTypeface(this.errorView, typeface);
            setTextViewTypeface(this.helperTextView, typeface);
        }
    }

    public void showError(CharSequence charSequence) {
        cancelCaptionAnimator();
        this.errorText = charSequence;
        this.errorView.setText(charSequence);
        int i5 = this.captionDisplayed;
        if (i5 != 1) {
            this.captionToShow = 1;
        }
        updateCaptionViewsVisibility(i5, this.captionToShow, shouldAnimateCaptionView(this.errorView, charSequence));
    }

    public void showHelper(CharSequence charSequence) {
        cancelCaptionAnimator();
        this.helperText = charSequence;
        this.helperTextView.setText(charSequence);
        int i5 = this.captionDisplayed;
        if (i5 != 2) {
            this.captionToShow = 2;
        }
        updateCaptionViewsVisibility(i5, this.captionToShow, shouldAnimateCaptionView(this.helperTextView, charSequence));
    }
}
