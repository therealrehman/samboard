package com.google.android.material.chip;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.google.android.material.R;

/* JADX INFO: loaded from: classes.dex */
public class SeslExpansionButton extends ImageView {
    private boolean mAutoDisappear;
    private boolean mExpanded;
    private boolean mFloated;
    private final CountDownTimer mTimer;

    public SeslExpansionButton(Context context) {
        this(context, null);
    }

    public boolean isExpanded() {
        return this.mExpanded;
    }

    public boolean isFloated() {
        return this.mFloated;
    }

    @Override // android.widget.ImageView, android.view.View
    public int[] onCreateDrawableState(int i5) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i5 + 2);
        if (this.mExpanded) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, new int[]{R.attr.state_expansion_button_expanded});
        }
        if (this.mFloated) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, new int[]{R.attr.state_expansion_button_floated});
        }
        return iArrOnCreateDrawableState;
    }

    public void setAutomaticDisappear(boolean z9) {
        this.mAutoDisappear = z9;
        if (z9) {
            return;
        }
        this.mTimer.cancel();
    }

    public void setExpanded(boolean z9) {
        this.mExpanded = z9;
        refreshDrawableState();
    }

    public void setFloated(boolean z9) {
        this.mFloated = z9;
        refreshDrawableState();
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i5) {
        super.setVisibility(i5);
        if (i5 == 0) {
            startDisappearTimer();
        }
    }

    public void startDisappearTimer() {
        this.mTimer.cancel();
        this.mTimer.start();
    }

    public SeslExpansionButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, -1);
    }

    public SeslExpansionButton(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5);
        this.mAutoDisappear = false;
        setElevation(getResources().getDimension(R.dimen.expansion_button_elevation));
        long integer = context.getResources().getInteger(R.integer.expansion_button_duration);
        this.mTimer = new CountDownTimer(integer, integer) { // from class: com.google.android.material.chip.SeslExpansionButton.1
            @Override // android.os.CountDownTimer
            public void onFinish() {
                if (SeslExpansionButton.this.mAutoDisappear && SeslExpansionButton.this.getVisibility() == 0) {
                    SeslExpansionButton.this.setVisibility(4);
                }
            }

            @Override // android.os.CountDownTimer
            public void onTick(long j5) {
            }
        };
    }
}
