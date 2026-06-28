package com.google.android.material.transition;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.c0;
import androidx.transition.l0;

/* JADX INFO: loaded from: classes.dex */
public final class Hold extends l0 {
    @Override // androidx.transition.l0
    public Animator onAppear(ViewGroup viewGroup, View view, c0 c0Var, c0 c0Var2) {
        return ValueAnimator.ofFloat(0.0f);
    }

    @Override // androidx.transition.l0
    public Animator onDisappear(ViewGroup viewGroup, View view, c0 c0Var, c0 c0Var2) {
        return ValueAnimator.ofFloat(0.0f);
    }
}
