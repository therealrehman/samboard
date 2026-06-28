package com.google.android.material.timepicker;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.core.view.W;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.math.MathUtils;
import com.google.android.material.motion.MotionUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
class ClockHandView extends View {
    private static final int DEFAULT_ANIMATION_DURATION = 200;
    private boolean animatingOnTouchUp;
    private final int animationDuration;
    private final TimeInterpolator animationInterpolator;
    private final float centerDotRadius;
    private boolean changedDuringTouch;
    private int circleRadius;
    private int currentLevel;
    private double degRad;
    private float downX;
    private float downY;
    private boolean isInTapRegion;
    private boolean isMultiLevel;
    private final List<OnRotateListener> listeners;
    private OnActionUpListener onActionUpListener;
    private float originalDeg;
    private final Paint paint;
    private final ValueAnimator rotationAnimator;
    private final int scaledTouchSlop;
    private final RectF selectorBox;
    private final int selectorRadius;
    private final int selectorStrokeWidth;

    public interface OnActionUpListener {
        void onActionUp(float f2, boolean z9);
    }

    public interface OnRotateListener {
        void onRotate(float f2, boolean z9);
    }

    public ClockHandView(Context context) {
        this(context, null);
    }

    private void adjustLevel(float f2, float f7) {
        this.currentLevel = MathUtils.dist((float) (getWidth() / 2), (float) (getHeight() / 2), f2, f7) > ((float) getLeveledCircleRadius(2)) + ViewUtils.dpToPx(getContext(), 12) ? 1 : 2;
    }

    private void drawSelector(Canvas canvas) {
        int height = getHeight() / 2;
        int width = getWidth() / 2;
        float f2 = width;
        float leveledCircleRadius = getLeveledCircleRadius(this.currentLevel);
        float fCos = (((float) Math.cos(this.degRad)) * leveledCircleRadius) + f2;
        float f7 = height;
        float fSin = (leveledCircleRadius * ((float) Math.sin(this.degRad))) + f7;
        this.paint.setStrokeWidth(0.0f);
        canvas.drawCircle(fCos, fSin, this.selectorRadius, this.paint);
        double dSin = Math.sin(this.degRad);
        double dCos = Math.cos(this.degRad);
        this.paint.setStrokeWidth(this.selectorStrokeWidth);
        canvas.drawLine(f2, f7, width + ((int) (dCos * d)), height + ((int) (d * dSin)), this.paint);
        canvas.drawCircle(f2, f7, this.centerDotRadius, this.paint);
    }

    private int getDegreesFromXY(float f2, float f7) {
        int degrees = (int) Math.toDegrees(Math.atan2(f7 - (getHeight() / 2), f2 - (getWidth() / 2)));
        int i5 = degrees + 90;
        return i5 < 0 ? degrees + 450 : i5;
    }

    private int getLeveledCircleRadius(int i5) {
        int i7 = this.circleRadius;
        return i5 == 2 ? Math.round(i7 * 0.66f) : i7;
    }

    private Pair<Float, Float> getValuesForAnimation(float f2) {
        float handRotation = getHandRotation();
        if (Math.abs(handRotation - f2) > 180.0f) {
            if (handRotation > 180.0f && f2 < 180.0f) {
                f2 += 360.0f;
            }
            if (handRotation < 180.0f && f2 > 180.0f) {
                handRotation += 360.0f;
            }
        }
        return new Pair<>(Float.valueOf(handRotation), Float.valueOf(f2));
    }

    private boolean handleTouchInput(float f2, float f7, boolean z9, boolean z10, boolean z11) {
        float degreesFromXY = getDegreesFromXY(f2, f7);
        boolean z12 = false;
        boolean z13 = getHandRotation() != degreesFromXY;
        if (z10 && z13) {
            return true;
        }
        if (!z13 && !z9) {
            return false;
        }
        if (z11 && this.animatingOnTouchUp) {
            z12 = true;
        }
        setHandRotation(degreesFromXY, z12);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setHandRotation$0(ValueAnimator valueAnimator) {
        setHandRotationInternal(((Float) valueAnimator.getAnimatedValue()).floatValue(), true);
    }

    private void setHandRotationInternal(float f2, boolean z9) {
        float f7 = f2 % 360.0f;
        this.originalDeg = f7;
        this.degRad = Math.toRadians(f7 - 90.0f);
        int height = getHeight() / 2;
        int width = getWidth() / 2;
        float leveledCircleRadius = getLeveledCircleRadius(this.currentLevel);
        float fCos = (((float) Math.cos(this.degRad)) * leveledCircleRadius) + width;
        float fSin = (leveledCircleRadius * ((float) Math.sin(this.degRad))) + height;
        RectF rectF = this.selectorBox;
        int i5 = this.selectorRadius;
        rectF.set(fCos - i5, fSin - i5, fCos + i5, fSin + i5);
        Iterator<OnRotateListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onRotate(f7, z9);
        }
        invalidate();
    }

    public void addOnRotateListener(OnRotateListener onRotateListener) {
        this.listeners.add(onRotateListener);
    }

    public int getCurrentLevel() {
        return this.currentLevel;
    }

    public RectF getCurrentSelectorBox() {
        return this.selectorBox;
    }

    public float getHandRotation() {
        return this.originalDeg;
    }

    public int getSelectorRadius() {
        return this.selectorRadius;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSelector(canvas);
    }

    @Override // android.view.View
    public void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        super.onLayout(z9, i5, i7, i9, i10);
        if (this.rotationAnimator.isRunning()) {
            return;
        }
        setHandRotation(getHandRotation());
    }

    @Override // android.view.View
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z9;
        boolean z10;
        boolean z11;
        OnActionUpListener onActionUpListener;
        int actionMasked = motionEvent.getActionMasked();
        float x9 = motionEvent.getX();
        float y4 = motionEvent.getY();
        if (actionMasked == 0) {
            this.downX = x9;
            this.downY = y4;
            this.isInTapRegion = true;
            this.changedDuringTouch = false;
            z9 = false;
            z10 = false;
            z11 = true;
        } else if (actionMasked == 1 || actionMasked == 2) {
            int i5 = (int) (x9 - this.downX);
            int i7 = (int) (y4 - this.downY);
            this.isInTapRegion = (i7 * i7) + (i5 * i5) > this.scaledTouchSlop;
            boolean z12 = this.changedDuringTouch;
            z9 = actionMasked == 1;
            if (this.isMultiLevel) {
                adjustLevel(x9, y4);
            }
            z11 = false;
            z10 = z12;
        } else {
            z9 = false;
            z10 = false;
            z11 = false;
        }
        boolean zHandleTouchInput = handleTouchInput(x9, y4, z10, z11, z9) | this.changedDuringTouch;
        this.changedDuringTouch = zHandleTouchInput;
        if (zHandleTouchInput && z9 && (onActionUpListener = this.onActionUpListener) != null) {
            onActionUpListener.onActionUp(getDegreesFromXY(x9, y4), this.isInTapRegion);
        }
        return true;
    }

    public void setAnimateOnTouchUp(boolean z9) {
        this.animatingOnTouchUp = z9;
    }

    public void setCircleRadius(int i5) {
        this.circleRadius = i5;
        invalidate();
    }

    public void setCurrentLevel(int i5) {
        this.currentLevel = i5;
        invalidate();
    }

    public void setHandRotation(float f2) {
        setHandRotation(f2, false);
    }

    public void setMultiLevel(boolean z9) {
        if (this.isMultiLevel && !z9) {
            this.currentLevel = 1;
        }
        this.isMultiLevel = z9;
        invalidate();
    }

    public void setOnActionUpListener(OnActionUpListener onActionUpListener) {
        this.onActionUpListener = onActionUpListener;
    }

    public ClockHandView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialClockStyle);
    }

    public void setHandRotation(float f2, boolean z9) {
        ValueAnimator valueAnimator = this.rotationAnimator;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        if (!z9) {
            setHandRotationInternal(f2, false);
            return;
        }
        Pair<Float, Float> valuesForAnimation = getValuesForAnimation(f2);
        this.rotationAnimator.setFloatValues(((Float) valuesForAnimation.first).floatValue(), ((Float) valuesForAnimation.second).floatValue());
        this.rotationAnimator.setDuration(this.animationDuration);
        this.rotationAnimator.setInterpolator(this.animationInterpolator);
        this.rotationAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.timepicker.a
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator2) {
                this.f10137e.lambda$setHandRotation$0(valueAnimator2);
            }
        });
        this.rotationAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.timepicker.ClockHandView.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                animator.end();
            }
        });
        this.rotationAnimator.start();
    }

    public ClockHandView(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5);
        this.rotationAnimator = new ValueAnimator();
        this.listeners = new ArrayList();
        Paint paint = new Paint();
        this.paint = paint;
        this.selectorBox = new RectF();
        this.currentLevel = 1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ClockHandView, i5, R.style.Widget_MaterialComponents_TimePicker_Clock);
        this.animationDuration = MotionUtils.resolveThemeDuration(context, R.attr.motionDurationLong2, DEFAULT_ANIMATION_DURATION);
        this.animationInterpolator = MotionUtils.resolveThemeInterpolator(context, R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        this.circleRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ClockHandView_materialCircleRadius, 0);
        this.selectorRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ClockHandView_selectorSize, 0);
        this.selectorStrokeWidth = getResources().getDimensionPixelSize(R.dimen.material_clock_hand_stroke_width);
        this.centerDotRadius = r7.getDimensionPixelSize(R.dimen.material_clock_hand_center_dot_radius);
        int color = typedArrayObtainStyledAttributes.getColor(R.styleable.ClockHandView_clockHandColor, 0);
        paint.setAntiAlias(true);
        paint.setColor(color);
        setHandRotation(0.0f);
        this.scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        WeakHashMap weakHashMap = W.f7199a;
        setImportantForAccessibility(2);
        typedArrayObtainStyledAttributes.recycle();
    }
}
