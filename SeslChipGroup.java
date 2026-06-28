package com.google.android.material.chip;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ValueAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.core.view.W;
import com.google.android.material.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SeslChipGroup extends ChipGroup {
    private static final String TAG = "SeslChipGroup";
    private static final int sChipAlphaDelay = 100;
    private static final int sChipAppearAlphaDuration = 200;
    private static final int sChipDissChipAppearAlphaDuration = 200;
    private static int sChipInitialWidth;
    private OnChipAddListener mChipAddListener;
    private int mChipMaxWidth;
    private OnChipRemovedListener mChipRemoveListener;
    private boolean mDynamicChipTextTruncation;
    int mEmptyContainerHeight;
    private final LayoutTransition mLayoutTransition;
    private int mRowCount;

    public interface OnChipAddListener {
        void onAdd();
    }

    public interface OnChipRemovedListener {
        void onRemove();
    }

    public SeslChipGroup(Context context) {
        this(context, null);
    }

    private void addRemoveAnim() {
        if (isLineAddedOrRemoved()) {
            startSeslLayoutHeightAnim();
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = -2;
        this.mEmptyContainerHeight = 0;
        setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void animateChipAppear(ValueAnimator valueAnimator) {
        View targetView = ((SeslValueAnimator) valueAnimator).getTargetView();
        if (targetView == null) {
            return;
        }
        if (!(targetView instanceof SeslChip)) {
            targetView.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
            return;
        }
        SeslChip seslChip = (SeslChip) targetView;
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        seslChip.setRight(seslChip.getLeft() + sChipInitialWidth + ((int) ((seslChip.getChipDrawable().getIntrinsicWidth() - sChipInitialWidth) * fFloatValue)));
        seslChip.setBottom(seslChip.getChipDrawable().getIntrinsicHeight() + seslChip.getTop());
        seslChip.setInternalsAlpha((int) (com.bumptech.glide.c.c((((int) valueAnimator.getCurrentPlayTime()) - 100) / 200.0f, 0.0f, 1.0f) * 255.0f));
        seslChip.setBackgroundAlpha((int) (fFloatValue * 255.0f));
        seslChip.setTextEndPadding(0.0f);
        seslChip.setEllipsize(null);
        seslChip.setSeslFullText(true);
        seslChip.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void animateChipDisappear(ValueAnimator valueAnimator) {
        View targetView = ((SeslValueAnimator) valueAnimator).getTargetView();
        if (targetView == null) {
            return;
        }
        if (!(targetView instanceof SeslChip)) {
            targetView.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
            return;
        }
        SeslChip seslChip = (SeslChip) targetView;
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        seslChip.setRight(seslChip.getLeft() + ((int) (((seslChip.getChipDrawable().getIntrinsicWidth() - sChipInitialWidth) * fFloatValue) + sChipInitialWidth)));
        seslChip.setBottom(seslChip.getChipDrawable().getIntrinsicHeight() + seslChip.getTop());
        seslChip.setInternalsAlpha((int) (com.bumptech.glide.c.c(1.0f - (((int) valueAnimator.getCurrentPlayTime()) / 200.0f), 0.0f, 1.0f) * 255.0f));
        seslChip.setBackgroundAlpha((int) (fFloatValue * 255.0f));
        seslChip.setTextEndPadding(0.0f);
        seslChip.setEllipsize(null);
        seslChip.setSeslFullText(true);
        seslChip.invalidate();
    }

    private AnimatorListenerAdapter getAddRemAnimListener() {
        return new AnimatorListenerAdapter() { // from class: com.google.android.material.chip.SeslChipGroup.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                View targetView = ((SeslValueAnimator) animator).getTargetView();
                if (targetView == null) {
                    return;
                }
                if (!(targetView instanceof SeslChip)) {
                    targetView.setAlpha(1.0f);
                    return;
                }
                SeslChip seslChip = (SeslChip) targetView;
                seslChip.setInternalsAlpha(255);
                seslChip.setBackgroundAlpha(255);
                seslChip.setSeslFullText(false);
                seslChip.invalidate();
            }
        };
    }

    private LayoutTransition.TransitionListener getChipTransitionListener() {
        return new LayoutTransition.TransitionListener() { // from class: com.google.android.material.chip.SeslChipGroup.2
            @Override // android.animation.LayoutTransition.TransitionListener
            public void endTransition(LayoutTransition layoutTransition, ViewGroup viewGroup, View view, int i5) {
                if (view instanceof SeslChip) {
                    SeslChip seslChip = (SeslChip) view;
                    if (i5 == 2 || i5 == 3) {
                        seslChip.setSeslFullText(false);
                        seslChip.setEllipsize(TextUtils.TruncateAt.END);
                    }
                }
            }

            @Override // android.animation.LayoutTransition.TransitionListener
            public void startTransition(LayoutTransition layoutTransition, ViewGroup viewGroup, View view, int i5) {
                if (view instanceof SeslChip) {
                    SeslChip seslChip = (SeslChip) view;
                    if (i5 == 2 || i5 == 3) {
                        seslChip.setTextEndPadding(0.0f);
                        seslChip.setEllipsize(null);
                        ((ChipDrawable) seslChip.getChipDrawable()).setSeslFinalWidth(r0.getIntrinsicWidth());
                        seslChip.setSeslFullText(true);
                    }
                }
            }
        };
    }

    private boolean isLineAddedOrRemoved() {
        return getHeight() != getInternalHeight((float) getWidth()) && shouldAnimateHeight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$postRemoveView$0() {
        this.mChipRemoveListener.onRemove();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startSeslLayoutHeightAnim$1(int i5, int i7, ValueAnimator valueAnimator) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        int iFloatValue = i5 + ((int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * i7));
        layoutParams.height = iFloatValue;
        this.mEmptyContainerHeight = iFloatValue;
        setLayoutParams(layoutParams);
    }

    private void postAddView(Chip chip) {
        if (this.mDynamicChipTextTruncation) {
            int i5 = this.mChipMaxWidth;
            if (i5 > 0) {
                chip.setMaxWidth(i5);
            }
            chip.setEllipsize(TextUtils.TruncateAt.END);
        }
        OnChipAddListener onChipAddListener = this.mChipAddListener;
        if (onChipAddListener != null) {
            onChipAddListener.onAdd();
        }
    }

    private void postRemoveView() {
        if (this.mChipRemoveListener != null) {
            post(new m(this, 3));
        }
    }

    private void setChipLayoutTransition() {
        this.mLayoutTransition.enableTransitionType(2);
        this.mLayoutTransition.enableTransitionType(3);
        this.mLayoutTransition.enableTransitionType(4);
        this.mLayoutTransition.enableTransitionType(0);
        this.mLayoutTransition.enableTransitionType(1);
        this.mLayoutTransition.setStartDelay(2, 0L);
        this.mLayoutTransition.setStartDelay(3, 0L);
        this.mLayoutTransition.setStartDelay(4, 0L);
        this.mLayoutTransition.setStartDelay(0, 0L);
        this.mLayoutTransition.setStartDelay(1, 0L);
        int integer = getContext().getResources().getInteger(R.integer.sesl_chip_default_anim_duration);
        SeslValueAnimator seslValueAnimatorOfFloat = SeslValueAnimator.ofFloat(0.0f, 1.0f);
        long j5 = integer;
        seslValueAnimatorOfFloat.setDuration(j5);
        seslValueAnimatorOfFloat.setStartDelay(0L);
        seslValueAnimatorOfFloat.addUpdateListener(new b(0));
        seslValueAnimatorOfFloat.addListener(getAddRemAnimListener());
        this.mLayoutTransition.setAnimator(2, seslValueAnimatorOfFloat);
        SeslValueAnimator seslValueAnimatorOfFloat2 = SeslValueAnimator.ofFloat(1.0f, 0.0f);
        seslValueAnimatorOfFloat2.setDuration(j5);
        seslValueAnimatorOfFloat2.addUpdateListener(new b(1));
        seslValueAnimatorOfFloat2.addListener(getAddRemAnimListener());
        this.mLayoutTransition.setAnimator(3, seslValueAnimatorOfFloat2);
        Interpolator interpolatorLoadInterpolator = AnimationUtils.loadInterpolator(getContext(), com.samsung.android.keyscafe.R.interpolator.sesl_chip_default_interpolator);
        this.mLayoutTransition.setInterpolator(3, interpolatorLoadInterpolator);
        this.mLayoutTransition.setInterpolator(2, interpolatorLoadInterpolator);
        this.mLayoutTransition.setInterpolator(4, interpolatorLoadInterpolator);
        this.mLayoutTransition.setInterpolator(0, interpolatorLoadInterpolator);
        this.mLayoutTransition.setInterpolator(1, interpolatorLoadInterpolator);
        this.mLayoutTransition.addTransitionListener(getChipTransitionListener());
    }

    private void setStaticHeight() {
        this.mEmptyContainerHeight = getHeight();
    }

    private boolean shouldAnimateHeight() {
        return !isSingleLine() || (isSingleLine() && getChildCount() == 0);
    }

    private void startSeslLayoutHeightAnim() {
        final int height = getHeight();
        final int internalHeight = getInternalHeight(getWidth()) - height;
        if (Math.abs(internalHeight) < getContext().getResources().getDimension(R.dimen.chip_height)) {
            return;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setDuration(getContext().getResources().getInteger(R.integer.sesl_chip_default_anim_duration));
        valueAnimatorOfFloat.setInterpolator(AnimationUtils.loadInterpolator(getContext(), com.samsung.android.keyscafe.R.interpolator.sesl_chip_default_interpolator));
        valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.chip.SeslChipGroup.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                ViewGroup.LayoutParams layoutParams = SeslChipGroup.this.getLayoutParams();
                layoutParams.height = -2;
                SeslChipGroup seslChipGroup = SeslChipGroup.this;
                seslChipGroup.mEmptyContainerHeight = 0;
                seslChipGroup.setLayoutParams(layoutParams);
            }
        });
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.chip.c
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f10027e.lambda$startSeslLayoutHeightAnim$1(height, internalHeight, valueAnimator);
            }
        });
        valueAnimatorOfFloat.start();
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i5, ViewGroup.LayoutParams layoutParams) {
        enableSeslLayoutTransitions(getChildCount() > 0);
        super.addView(view instanceof SeslChip ? (SeslChip) view : view, i5, layoutParams);
        if (isLineAddedOrRemoved()) {
            enableSeslLayoutTransitions(false);
        }
        addRemoveAnim();
        if (view instanceof Chip) {
            postAddView((Chip) view);
        }
    }

    public void enableSeslLayoutTransitions(boolean z9) {
        if (z9) {
            setLayoutTransition(this.mLayoutTransition);
        } else {
            setLayoutTransition(null);
        }
    }

    public int getInternalHeight(float f2) {
        int i5;
        int childCount = getChildCount();
        if (childCount == 0) {
            return 0;
        }
        int paddingStart = getPaddingStart();
        int paddingEnd = getPaddingEnd();
        int chipSpacingHorizontal = getChipSpacingHorizontal();
        int width = getChildAt(0).getWidth() + paddingStart + paddingEnd + chipSpacingHorizontal;
        int i7 = 1;
        for (int i9 = 1; i9 < childCount; i9++) {
            int intrinsicWidth = ((Chip) getChildAt(i9)).getChipDrawable().getIntrinsicWidth();
            if (width + intrinsicWidth < f2) {
                i5 = intrinsicWidth + chipSpacingHorizontal + width;
            } else {
                i5 = intrinsicWidth + chipSpacingHorizontal + paddingStart + paddingEnd;
                i7++;
            }
            width = i5;
        }
        int chipSpacingVertical = getChipSpacingVertical();
        return (getPaddingTop() + (getPaddingBottom() + ((getChildAt(0).getHeight() + chipSpacingVertical) * i7))) - chipSpacingVertical;
    }

    @Override // com.google.android.material.internal.FlowLayout
    public int getRowCount() {
        return this.mRowCount;
    }

    public int getRowInternalIndex(View view) {
        int childCount = getChildCount();
        int[] iArr = new int[getRowCount()];
        for (int i5 = 0; i5 < childCount; i5++) {
            if (getChildAt(i5).equals(view)) {
                return iArr[getRowIndex(getChildAt(i5))];
            }
            int rowIndex = getRowIndex(getChildAt(i5));
            iArr[rowIndex] = iArr[rowIndex] + 1;
        }
        return 0;
    }

    public int getTotalWidth() {
        int paddingEnd = getPaddingEnd() + getPaddingStart();
        int childCount = getChildCount();
        if (childCount <= 0) {
            return paddingEnd;
        }
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            paddingEnd = (childAt instanceof SeslChip ? ((SeslChip) childAt).getChipDrawable().getIntrinsicWidth() : childAt.getWidth()) + paddingEnd;
        }
        if (childCount > 1) {
            return paddingEnd + ((childCount - 2) * getChipSpacingHorizontal());
        }
        return paddingEnd;
    }

    @Override // com.google.android.material.internal.FlowLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        int marginEnd;
        int marginStart;
        int i11 = 1;
        int i12 = 0;
        if (getChildCount() == 0) {
            this.mRowCount = 0;
            return;
        }
        this.mRowCount = 1;
        WeakHashMap weakHashMap = W.f7199a;
        boolean z10 = getLayoutDirection() == 1;
        int paddingRight = z10 ? getPaddingRight() : getPaddingLeft();
        int paddingLeft = z10 ? getPaddingLeft() : getPaddingRight();
        int paddingTop = getPaddingTop();
        int lineSpacing = getLineSpacing();
        int itemSpacing = getItemSpacing();
        int i13 = i9 - i5;
        int i14 = i13 - paddingLeft;
        if (!z10) {
            i13 = i14;
        }
        int i15 = 0;
        int measuredWidth = paddingRight;
        int i16 = paddingTop;
        while (i15 < getChildCount()) {
            View childAt = getChildAt(i15);
            if (childAt.getVisibility() == 8) {
                childAt.setTag(R.id.row_index_key, -1);
            } else {
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    marginStart = marginLayoutParams.getMarginStart();
                    marginEnd = marginLayoutParams.getMarginEnd();
                } else {
                    marginEnd = i12;
                    marginStart = marginEnd;
                }
                int measuredWidth2 = childAt.getMeasuredWidth() + measuredWidth + marginStart;
                if (!isSingleLine() && measuredWidth2 > i14) {
                    i16 = paddingTop + lineSpacing;
                    this.mRowCount += i11;
                    measuredWidth = paddingRight;
                }
                childAt.setTag(R.id.row_index_key, Integer.valueOf(this.mRowCount - i11));
                int i17 = measuredWidth + marginStart;
                int measuredWidth3 = childAt.getMeasuredWidth() + i17;
                int measuredHeight = childAt.getMeasuredHeight() + i16;
                if (z10) {
                    childAt.layout(i13 - measuredWidth3, i16, (i13 - measuredWidth) - marginStart, measuredHeight);
                } else {
                    childAt.layout(i17, i16, measuredWidth3, measuredHeight);
                }
                measuredWidth += childAt.getMeasuredWidth() + marginStart + marginEnd + itemSpacing;
                paddingTop = measuredHeight;
                i11 = 1;
            }
            i15 += i11;
            i12 = 0;
        }
    }

    @Override // com.google.android.material.internal.FlowLayout, android.view.View
    public void onMeasure(int i5, int i7) {
        super.onMeasure(i5, i7);
        if (getChildCount() <= 0) {
            setMeasuredDimension(getWidth(), this.mEmptyContainerHeight);
        }
    }

    @Override // android.view.ViewGroup
    public void removeAllViews() {
        setStaticHeight();
        super.removeAllViews();
        addRemoveAnim();
        postRemoveView();
    }

    @Override // android.view.ViewGroup
    public void removeAllViewsInLayout() {
        setStaticHeight();
        super.removeAllViewsInLayout();
        addRemoveAnim();
        postRemoveView();
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        enableSeslLayoutTransitions(getChildCount() > 1);
        setStaticHeight();
        super.removeView(view);
        addRemoveAnim();
        postRemoveView();
    }

    @Override // android.view.ViewGroup
    public void removeViewAt(int i5) {
        setStaticHeight();
        super.removeViewAt(i5);
        addRemoveAnim();
        postRemoveView();
    }

    @Override // android.view.ViewGroup
    public void removeViewInLayout(View view) {
        setStaticHeight();
        super.removeViewInLayout(view);
        addRemoveAnim();
        postRemoveView();
    }

    @Override // android.view.ViewGroup
    public void removeViews(int i5, int i7) {
        setStaticHeight();
        super.removeViews(i5, i7);
        addRemoveAnim();
        postRemoveView();
    }

    @Override // android.view.ViewGroup
    public void removeViewsInLayout(int i5, int i7) {
        setStaticHeight();
        super.removeViewsInLayout(i5, i7);
        addRemoveAnim();
        postRemoveView();
    }

    public void setDynamicTruncation(boolean z9) {
        this.mDynamicChipTextTruncation = z9;
        Log.i(TAG, "dynamic truncation state: " + z9);
    }

    public void setMaxChipWidth(int i5) {
        this.mChipMaxWidth = i5 - (getPaddingEnd() + getPaddingStart());
    }

    public void setOnChipAddListener(OnChipAddListener onChipAddListener) {
        this.mChipAddListener = onChipAddListener;
    }

    public void setOnChipRemovedListener(OnChipRemovedListener onChipRemovedListener) {
        this.mChipRemoveListener = onChipRemovedListener;
    }

    public SeslChipGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.chipGroupStyle);
    }

    public static class SeslValueAnimator extends ValueAnimator {
        private ArrayList<Animator.AnimatorListener> mSeslListeners;
        private ArrayList<ValueAnimator.AnimatorUpdateListener> mSeslUpdateListeners;
        private WeakReference<View> mTargetView;
        private float[] mValues;

        private SeslValueAnimator() {
        }

        public static SeslValueAnimator ofFloat(float... fArr) {
            SeslValueAnimator seslValueAnimator = new SeslValueAnimator();
            seslValueAnimator.setFloatValues(fArr);
            seslValueAnimator.mValues = fArr;
            seslValueAnimator.mSeslUpdateListeners = new ArrayList<>();
            seslValueAnimator.mSeslListeners = new ArrayList<>();
            return seslValueAnimator;
        }

        @Override // android.animation.Animator
        public void addListener(Animator.AnimatorListener animatorListener) {
            super.addListener(animatorListener);
            this.mSeslListeners.add(animatorListener);
        }

        @Override // android.animation.ValueAnimator
        public void addUpdateListener(ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
            super.addUpdateListener(animatorUpdateListener);
            this.mSeslUpdateListeners.add(animatorUpdateListener);
        }

        public View getTargetView() {
            return this.mTargetView.get();
        }

        @Override // android.animation.Animator
        public void setTarget(Object obj) {
            this.mTargetView = new WeakReference<>((View) obj);
            super.setTarget(obj);
        }

        @Override // android.animation.ValueAnimator, android.animation.Animator
        public SeslValueAnimator clone() {
            SeslValueAnimator seslValueAnimatorOfFloat = ofFloat(this.mValues);
            ArrayList<ValueAnimator.AnimatorUpdateListener> arrayList = this.mSeslUpdateListeners;
            if (arrayList != null) {
                Iterator<ValueAnimator.AnimatorUpdateListener> it = arrayList.iterator();
                while (it.hasNext()) {
                    seslValueAnimatorOfFloat.addUpdateListener(it.next());
                }
            }
            ArrayList<Animator.AnimatorListener> arrayList2 = this.mSeslListeners;
            if (arrayList2 != null) {
                Iterator<Animator.AnimatorListener> it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    seslValueAnimatorOfFloat.addListener(it2.next());
                }
            }
            return seslValueAnimatorOfFloat;
        }
    }

    public SeslChipGroup(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5);
        this.mDynamicChipTextTruncation = true;
        this.mLayoutTransition = new LayoutTransition();
        this.mEmptyContainerHeight = 0;
        sChipInitialWidth = (int) getResources().getDimension(R.dimen.chip_start_width);
        setLayoutDirection(TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()));
        setChipLayoutTransition();
        enableSeslLayoutTransitions(false);
    }

    public int getRowInternalIndex(int i5) {
        int childCount = getChildCount();
        int[] iArr = new int[getRowCount()];
        for (int i7 = 0; i7 < childCount; i7++) {
            if (i7 == i5) {
                return iArr[getRowIndex(getChildAt(i7))];
            }
            int rowIndex = getRowIndex(getChildAt(i7));
            iArr[rowIndex] = iArr[rowIndex] + 1;
        }
        return 0;
    }
}
