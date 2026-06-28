package com.google.android.material.tabs;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/* JADX INFO: loaded from: classes.dex */
public class SeslTabDotLineIndicator extends SeslAbsIndicatorView {
    private static final float CIRCLE_INTERVAL = 2.5f;
    private static final float DIAMETER_SIZE = 2.5f;
    private static final int SCALE_DIFF = 5;
    private final int mDiameter;
    private final int mInterval;
    private Paint mPaint;
    private float mScaleFrom;
    private final float mScaleFromDiff;
    private int mWidth;

    public SeslTabDotLineIndicator(Context context) {
        this(context, null);
    }

    private void updateDotLineScaleFrom() {
        if (this.mWidth != getWidth() || this.mWidth == 0) {
            int width = getWidth();
            this.mWidth = width;
            if (width <= 0) {
                this.mScaleFrom = 0.9f;
            } else {
                this.mScaleFrom = (width - this.mScaleFromDiff) / width;
            }
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        updateDotLineScaleFrom();
        if ((isPressed() || isSelected()) && (getBackground() instanceof ColorDrawable)) {
            int width = (getWidth() - getPaddingStart()) - getPaddingEnd();
            float height = getHeight() / 2.0f;
            int i5 = this.mDiameter;
            float f2 = i5 / 2.0f;
            canvas.drawRoundRect(0.0f, height - f2, width, height + f2, i5, i5, this.mPaint);
        }
    }

    @Override // com.google.android.material.tabs.SeslAbsIndicatorView
    public void onHide() {
        setAlpha(0.0f);
    }

    @Override // com.google.android.material.tabs.SeslAbsIndicatorView
    public void onSetSelectedIndicatorColor(int i5) {
        this.mPaint.setColor(i5);
    }

    @Override // com.google.android.material.tabs.SeslAbsIndicatorView
    public void onShow() {
        startReleaseEffect();
    }

    @Override // com.google.android.material.tabs.SeslAbsIndicatorView
    public /* bridge */ /* synthetic */ void setHide() {
        super.setHide();
    }

    @Override // com.google.android.material.tabs.SeslAbsIndicatorView
    public /* bridge */ /* synthetic */ void setPressed() {
        super.setPressed();
    }

    @Override // com.google.android.material.tabs.SeslAbsIndicatorView
    public /* bridge */ /* synthetic */ void setReleased() {
        super.setReleased();
    }

    @Override // com.google.android.material.tabs.SeslAbsIndicatorView
    public /* bridge */ /* synthetic */ void setSelectedIndicatorColor(int i5) {
        super.setSelectedIndicatorColor(i5);
    }

    @Override // com.google.android.material.tabs.SeslAbsIndicatorView
    public /* bridge */ /* synthetic */ void setShow() {
        super.setShow();
    }

    @Override // com.google.android.material.tabs.SeslAbsIndicatorView
    public void startPressEffect() {
        setAlpha(1.0f);
        invalidate();
    }

    @Override // com.google.android.material.tabs.SeslAbsIndicatorView
    public void startReleaseEffect() {
        setAlpha(1.0f);
    }

    public SeslTabDotLineIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SeslTabDotLineIndicator(Context context, AttributeSet attributeSet, int i5) {
        this(context, attributeSet, i5, 0);
    }

    public SeslTabDotLineIndicator(Context context, AttributeSet attributeSet, int i5, int i7) {
        super(context, attributeSet, i5, i7);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.mDiameter = (int) TypedValue.applyDimension(1, 2.5f, displayMetrics);
        this.mInterval = (int) TypedValue.applyDimension(1, 2.5f, displayMetrics);
        this.mScaleFromDiff = TypedValue.applyDimension(1, 5.0f, displayMetrics);
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setFlags(1);
    }
}
