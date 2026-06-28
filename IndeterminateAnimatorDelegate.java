package com.google.android.material.progressindicator;

import android.animation.Animator;
import androidx.vectordrawable.graphics.drawable.c;

/* JADX INFO: loaded from: classes.dex */
abstract class IndeterminateAnimatorDelegate<T extends Animator> {
    protected IndeterminateDrawable drawable;
    protected final int[] segmentColors;
    protected final float[] segmentPositions;

    public IndeterminateAnimatorDelegate(int i5) {
        this.segmentPositions = new float[i5 * 2];
        this.segmentColors = new int[i5];
    }

    public abstract void cancelAnimatorImmediately();

    public float getFractionInRange(int i5, int i7, int i9) {
        return (i5 - i7) / i9;
    }

    public abstract void invalidateSpecValues();

    public abstract void registerAnimatorsCompleteCallback(c cVar);

    public void registerDrawable(IndeterminateDrawable indeterminateDrawable) {
        this.drawable = indeterminateDrawable;
    }

    public abstract void requestCancelAnimatorAfterCurrentCycle();

    public abstract void startAnimator();

    public abstract void unregisterAnimatorsCompleteCallback();
}
