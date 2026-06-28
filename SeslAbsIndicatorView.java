package com.google.android.material.tabs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
abstract class SeslAbsIndicatorView extends View {
    public SeslAbsIndicatorView(Context context) {
        super(context);
    }

    public abstract void onHide();

    public abstract void onSetSelectedIndicatorColor(int i5);

    public abstract void onShow();

    public void setHide() {
        onHide();
    }

    public void setPressed() {
        startPressEffect();
    }

    public void setReleased() {
        startReleaseEffect();
    }

    public void setSelectedIndicatorColor(int i5) {
        onSetSelectedIndicatorColor(i5);
    }

    public void setShow() {
        onShow();
    }

    public abstract void startPressEffect();

    public abstract void startReleaseEffect();

    public SeslAbsIndicatorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SeslAbsIndicatorView(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5);
    }

    public SeslAbsIndicatorView(Context context, AttributeSet attributeSet, int i5, int i7) {
        super(context, attributeSet, i5, i7);
    }
}
