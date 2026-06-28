package com.google.android.material.chip;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.google.android.material.R;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class SeslExpandableContainer extends FrameLayout {
    private static final String TAG = "SeslExpandableContainer";
    private boolean mChipGroupInitialized;
    private boolean mExpanded;
    private final SeslExpansionButton mExpansionButton;
    private final int mExpansionButtonContainerId;
    private boolean mFadeAnimation;
    private boolean mFloatChangeAllowed;
    private final boolean mIsRtl;
    private OnExpansionButtonClickedListener mOnExpansionButtonClickedListener;
    private boolean mPaddingAllowed;
    private final View mPaddingView;
    private final HorizontalScrollView mScrollView;
    private int mScrollViewPos;
    private final LinearLayout mScrollingChipsContainer;

    public interface OnExpansionButtonClickedListener {
        void onClick();
    }

    public SeslExpandableContainer(Context context) {
        this(context, null);
    }

    private void addExpansionButtonContainer(Context context) {
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        relativeLayout.setId(this.mExpansionButtonContainerId);
        if (this.mIsRtl) {
            relativeLayout.setGravity(3);
        } else {
            relativeLayout.setGravity(5);
        }
        addView(relativeLayout);
        relativeLayout.addView(this.mExpansionButton);
    }

    private void fadeAnimation() {
        (this.mExpanded ? getChildAt(1) : this.mScrollingChipsContainer).animate().setDuration(100L).alpha(0.0f).setListener(new Animator.AnimatorListener() { // from class: com.google.android.material.chip.SeslExpandableContainer.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                if (SeslExpandableContainer.this.mExpanded) {
                    SeslExpandableContainer.this.getChildAt(1).setAlpha(1.0f);
                } else {
                    SeslExpandableContainer.this.mScrollingChipsContainer.setAlpha(1.0f);
                }
                SeslExpandableContainer.this.postFadeAnimation();
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
            }
        });
    }

    private static void getAddedChildrens(ViewGroup viewGroup, View[] viewArr, boolean z9) {
        for (int i5 = 0; i5 < viewGroup.getChildCount(); i5++) {
            viewArr[i5] = viewGroup.getChildAt(i5);
        }
        if (z9) {
            Collections.reverse(Arrays.asList(viewArr));
        }
    }

    private void initExpansionButtonLayout(Context context) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, context.getResources().getDimensionPixelSize(R.dimen.expansion_button_margin_top), 0, 0);
        this.mExpansionButton.setLayoutParams(layoutParams);
        this.mExpansionButton.setBackground(context.getDrawable(R.drawable.sesl_expansion_button_background));
        this.mExpansionButton.setImageDrawable(context.getDrawable(R.drawable.sesl_expansion_button_foreground));
        this.mExpansionButton.setAutomaticDisappear(true);
        this.mExpansionButton.setExpanded(false);
        this.mExpansionButton.setFloated(true);
        this.mExpansionButton.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$fullScrollToRight$1(int i5, int i7) {
        scrollTo(getScrollContentsWidth(), i5, i7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$scrollTo$0(int i5, int i7, int i9) {
        if (i5 > 0) {
            ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(this.mScrollView, "scrollX", i7);
            ObjectAnimator objectAnimatorOfInt2 = ObjectAnimator.ofInt(this.mScrollView, "scrollY", 0);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setInterpolator(AnimationUtils.loadInterpolator(getContext(), com.samsung.android.keyscafe.R.interpolator.sesl_chip_default_interpolator));
            animatorSet.setDuration(i5);
            animatorSet.setStartDelay(i9);
            animatorSet.playTogether(objectAnimatorOfInt, objectAnimatorOfInt2);
            animatorSet.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setExpanded$2() {
        this.mExpansionButton.setExpanded(this.mExpanded);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setExpansionButton$3() {
        this.mExpansionButton.setExpanded(this.mExpanded);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setExpansionButton$4(View view) {
        if (this.mFadeAnimation) {
            fadeAnimation();
            return;
        }
        this.mExpanded = !this.mExpanded;
        refreshLayout();
        post(new d(this, 0));
        OnExpansionButtonClickedListener onExpansionButtonClickedListener = this.mOnExpansionButtonClickedListener;
        if (onExpansionButtonClickedListener != null) {
            onExpansionButtonClickedListener.onClick();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setOnScrollChangeListener$5(View view, int i5, int i7, int i9, int i10) {
        updateScrollExpansionButton();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postFadeAnimation() {
        this.mExpanded = !this.mExpanded;
        refreshLayout();
        this.mExpansionButton.setExpanded(this.mExpanded);
        OnExpansionButtonClickedListener onExpansionButtonClickedListener = this.mOnExpansionButtonClickedListener;
        if (onExpansionButtonClickedListener != null) {
            onExpansionButtonClickedListener.onClick();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshLayout() {
        setLayoutTransition(null);
        int i5 = 1;
        if (this.mExpanded) {
            if (this.mScrollingChipsContainer.getChildCount() > 0) {
                this.mExpansionButton.setAutomaticDisappear(false);
                this.mScrollViewPos = this.mScrollView.getScrollX();
                int childCount = this.mScrollingChipsContainer.getChildCount();
                View[] viewArr = new View[childCount];
                getAddedChildrens(this.mScrollingChipsContainer, viewArr, this.mIsRtl);
                int height = 0;
                for (int i7 = 0; i7 < childCount; i7++) {
                    View view = viewArr[i7];
                    if (!this.mPaddingAllowed || view.getId() != this.mPaddingView.getId()) {
                        this.mScrollingChipsContainer.removeView(view);
                        addView(view, i5);
                        height += view.getHeight();
                        i5++;
                    }
                }
                this.mScrollView.setVisibility(8);
                if (this.mExpansionButton.getVisibility() == 0 || height <= 0) {
                    return;
                }
                this.mExpansionButton.setVisibility(0);
                return;
            }
            return;
        }
        if (getChildCount() > 2) {
            this.mExpansionButton.setAutomaticDisappear(true);
            this.mScrollView.setVisibility(0);
            int childCount2 = getChildCount();
            View[] viewArr2 = new View[childCount2];
            getAddedChildrens(this, viewArr2, this.mIsRtl);
            int i9 = 0;
            for (int i10 = 0; i10 < childCount2; i10++) {
                View view2 = viewArr2[i10];
                if (!this.mChipGroupInitialized && (view2 instanceof SeslChipGroup)) {
                    ((SeslChipGroup) view2).setMaxChipWidth(getWidth());
                    this.mChipGroupInitialized = true;
                }
                int id = view2.getId();
                if (id != this.mScrollView.getId() && id != this.mExpansionButtonContainerId && id != this.mPaddingView.getId()) {
                    removeView(view2);
                    this.mScrollingChipsContainer.addView(view2, i9);
                    i9++;
                }
            }
            this.mScrollView.scrollTo(this.mScrollViewPos, 0);
            updateScrollExpansionButton();
        }
    }

    private void setExpansionButton() {
        this.mExpansionButton.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.chip.h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f10039e.lambda$setExpansionButton$4(view);
            }
        });
    }

    private void setOnScrollChangeListener() {
        this.mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() { // from class: com.google.android.material.chip.f
            @Override // android.view.View.OnScrollChangeListener
            public final void onScrollChange(View view, int i5, int i7, int i9, int i10) {
                this.f10035a.lambda$setOnScrollChangeListener$5(view, i5, i7, i9, i10);
            }
        });
    }

    private boolean shouldFloat() {
        return (this.mIsRtl && this.mScrollView.getScrollX() > getPaddingView().getWidth() / 2) || (!this.mIsRtl && this.mScrollView.getScrollX() < getScrollContentsWidth() - getWidth());
    }

    private void updateScrollExpansionButton() {
        int scrollContentsWidth = getScrollContentsWidth();
        int width = getWidth() + 10;
        int width2 = this.mPaddingView.getWidth();
        if (this.mPaddingAllowed) {
            if ((this.mPaddingView.getVisibility() == 0 && scrollContentsWidth - width2 > width) || (this.mPaddingView.getVisibility() == 8 && scrollContentsWidth > width)) {
                if (this.mExpansionButton.getVisibility() != 0) {
                    this.mExpansionButton.setVisibility(0);
                }
                setExpansionButton();
            } else if (this.mExpansionButton.getVisibility() == 0) {
                this.mExpansionButton.setVisibility(4);
            }
        } else if (scrollContentsWidth > width) {
            if (this.mExpansionButton.getVisibility() != 0) {
                this.mExpansionButton.setVisibility(0);
            }
            setExpansionButton();
        } else if (this.mExpansionButton.getVisibility() == 0) {
            this.mExpansionButton.setVisibility(4);
        }
        updateScrollExpansionButtonFloat();
    }

    private void updateScrollExpansionButtonFloat() {
        if (this.mFloatChangeAllowed && this.mScrollView.getVisibility() == 0) {
            if (!this.mPaddingAllowed || shouldFloat()) {
                this.mExpansionButton.setFloated(true);
            } else {
                this.mExpansionButton.setFloated(false);
            }
        }
    }

    public void enableFadingAnimation(boolean z9) {
        this.mFadeAnimation = z9;
    }

    public void enableFloatChange(boolean z9) {
        this.mFloatChangeAllowed = z9;
    }

    public void enablePadding(boolean z9) {
        this.mPaddingAllowed = z9;
        post(new d(this, 1));
        Log.i(TAG, "padding view updated visibility: " + z9);
    }

    public void fullScrollToLeft(int i5, int i7) {
        if (this.mExpanded) {
            Log.w(TAG, "cannot scroll if container is expanded");
        } else {
            scrollTo(0, i5, i7);
        }
    }

    public void fullScrollToRight(int i5, int i7) {
        if (this.mExpanded) {
            Log.w(TAG, "cannot scroll if container is expanded");
        } else {
            post(new g(this, i5, i7, 0));
        }
    }

    public SeslExpansionButton getExpansionButton() {
        return this.mExpansionButton;
    }

    public View getPaddingView() {
        return this.mPaddingView;
    }

    public int getScrollContentsWidth() {
        if (this.mExpanded) {
            return 0;
        }
        int totalWidth = 0;
        for (int i5 = 0; i5 < this.mScrollingChipsContainer.getChildCount(); i5++) {
            View childAt = this.mScrollingChipsContainer.getChildAt(i5);
            if (childAt.getVisibility() == 0) {
                totalWidth = (childAt instanceof SeslChipGroup ? ((SeslChipGroup) childAt).getTotalWidth() : childAt.getWidth()) + totalWidth;
            }
        }
        return totalWidth;
    }

    public boolean isExpanded() {
        return this.mExpanded;
    }

    public boolean isPaddingEnabled() {
        return this.mPaddingAllowed;
    }

    public boolean isPaddingViewVisible() {
        return this.mPaddingView.getVisibility() == 0;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        super.onLayout(z9, i5, i7, i9, i10);
        refreshLayout();
    }

    public void scrollTo(final int i5, final int i7, final int i9) {
        if (this.mExpanded) {
            Log.w(TAG, "cannot scroll if container is expanded");
        } else {
            this.mScrollView.post(new Runnable() { // from class: com.google.android.material.chip.e
                @Override // java.lang.Runnable
                public final void run() {
                    this.f10032e.lambda$scrollTo$0(i7, i5, i9);
                }
            });
        }
    }

    public void setExpanded(boolean z9) {
        this.mExpanded = z9;
        refreshLayout();
        post(new d(this, 2));
        Log.i(TAG, "expansion state: " + z9);
    }

    public void setExpansionBackGroundImage(Drawable drawable) {
        this.mExpansionButton.setBackground(drawable);
        Log.i(TAG, "expansion button background changed");
    }

    public void setExpansionImageDrawable(Drawable drawable) {
        this.mExpansionButton.setImageDrawable(drawable);
        Log.i(TAG, "expansion button image changed");
    }

    public void setOnExpansionButtonClickedListener(OnExpansionButtonClickedListener onExpansionButtonClickedListener) {
        this.mOnExpansionButtonClickedListener = onExpansionButtonClickedListener;
    }

    public void tempHideExpansionButton() {
        this.mExpansionButton.setVisibility(8);
    }

    public void updateExpansionButton() {
        if (this.mExpanded) {
            return;
        }
        updateScrollExpansionButton();
    }

    public SeslExpandableContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public SeslExpandableContainer(Context context, AttributeSet attributeSet, int i5) {
        this(context, attributeSet, i5, -1);
    }

    public SeslExpandableContainer(Context context, AttributeSet attributeSet, int i5, int i7) {
        super(context, attributeSet, i5, i7);
        this.mExpanded = false;
        this.mPaddingAllowed = true;
        this.mScrollViewPos = 0;
        this.mFloatChangeAllowed = true;
        this.mIsRtl = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.sesl_expandable_container, (ViewGroup) null);
        this.mScrollView = (HorizontalScrollView) viewInflate.findViewById(R.id.sesl_scroll_view);
        setOnScrollChangeListener();
        this.mScrollingChipsContainer = (LinearLayout) viewInflate.findViewById(R.id.sesl_scrolling_chips_container);
        this.mPaddingView = viewInflate.findViewById(R.id.sesl_padding_view);
        addView(viewInflate);
        this.mExpansionButtonContainerId = View.generateViewId();
        this.mExpansionButton = new SeslExpansionButton(context);
        initExpansionButtonLayout(context);
        addExpansionButtonContainer(context);
    }
}
