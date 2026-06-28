package com.google.android.material.slider;

import L.f;
import L.l;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.SeekBar;
import androidx.core.view.W;
import androidx.customview.widget.b;
import com.bumptech.glide.c;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewOverlayImpl;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.slider.BaseOnChangeListener;
import com.google.android.material.slider.BaseOnSliderTouchListener;
import com.google.android.material.slider.BaseSlider;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.google.android.material.tooltip.TooltipDrawable;
import d6.AbstractC0476d;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.WeakHashMap;
import p0.a;

/* JADX INFO: loaded from: classes.dex */
abstract class BaseSlider<S extends BaseSlider<S, L, T>, L extends BaseOnChangeListener<S>, T extends BaseOnSliderTouchListener<S>> extends View {
    private static final int DEFAULT_LABEL_ANIMATION_ENTER_DURATION = 83;
    private static final int DEFAULT_LABEL_ANIMATION_EXIT_DURATION = 117;
    private static final String EXCEPTION_ILLEGAL_DISCRETE_VALUE = "Value(%s) must be equal to valueFrom(%s) plus a multiple of stepSize(%s) when using stepSize(%s)";
    private static final String EXCEPTION_ILLEGAL_MIN_SEPARATION = "minSeparation(%s) must be greater or equal to 0";
    private static final String EXCEPTION_ILLEGAL_MIN_SEPARATION_STEP_SIZE = "minSeparation(%s) must be greater or equal and a multiple of stepSize(%s) when using stepSize(%s)";
    private static final String EXCEPTION_ILLEGAL_MIN_SEPARATION_STEP_SIZE_UNIT = "minSeparation(%s) cannot be set as a dimension when using stepSize(%s)";
    private static final String EXCEPTION_ILLEGAL_STEP_SIZE = "The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range";
    private static final String EXCEPTION_ILLEGAL_VALUE = "Slider value(%s) must be greater or equal to valueFrom(%s), and lower or equal to valueTo(%s)";
    private static final String EXCEPTION_ILLEGAL_VALUE_FROM = "valueFrom(%s) must be smaller than valueTo(%s)";
    private static final String EXCEPTION_ILLEGAL_VALUE_TO = "valueTo(%s) must be greater than valueFrom(%s)";
    private static final int HALO_ALPHA = 63;
    private static final int MIN_TOUCH_TARGET_DP = 48;
    private static final String TAG = "BaseSlider";
    private static final double THRESHOLD = 1.0E-4d;
    private static final int TIMEOUT_SEND_ACCESSIBILITY_EVENT = 200;
    static final int UNIT_PX = 0;
    static final int UNIT_VALUE = 1;
    private static final String WARNING_FLOATING_POINT_ERROR = "Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.";
    private BaseSlider<S, L, T>.AccessibilityEventSender accessibilityEventSender;
    private final AccessibilityHelper accessibilityHelper;
    private final AccessibilityManager accessibilityManager;
    private int activeThumbIdx;
    private final Paint activeTicksPaint;
    private final Paint activeTrackPaint;
    private final List<L> changeListeners;
    private Drawable customThumbDrawable;
    private List<Drawable> customThumbDrawablesForValues;
    private final MaterialShapeDrawable defaultThumbDrawable;
    private int defaultThumbRadius;
    private int defaultTickActiveRadius;
    private int defaultTickInactiveRadius;
    private int defaultTrackHeight;
    private boolean dirtyConfig;
    private int focusedThumbIdx;
    private boolean forceDrawCompatHalo;
    private LabelFormatter formatter;
    private ColorStateList haloColor;
    private final Paint haloPaint;
    private int haloRadius;
    private final Paint inactiveTicksPaint;
    private final Paint inactiveTrackPaint;
    private boolean isLongPress;
    private int labelBehavior;
    private int labelPadding;
    private int labelStyle;
    private final List<TooltipDrawable> labels;
    private boolean labelsAreAnimatedIn;
    private ValueAnimator labelsInAnimator;
    private ValueAnimator labelsOutAnimator;
    private MotionEvent lastEvent;
    private int minTouchTargetSize;
    private int minTrackSidePadding;
    private int minWidgetHeight;
    private final int scaledTouchSlop;
    private int separationUnit;
    private float stepSize;
    private boolean thumbIsPressed;
    private final Paint thumbPaint;
    private int thumbRadius;
    private int tickActiveRadius;
    private ColorStateList tickColorActive;
    private ColorStateList tickColorInactive;
    private int tickInactiveRadius;
    private boolean tickVisible;
    private float[] ticksCoordinates;
    private float touchDownX;
    private final List<T> touchListeners;
    private float touchPosition;
    private ColorStateList trackColorActive;
    private ColorStateList trackColorInactive;
    private int trackHeight;
    private int trackSidePadding;
    private int trackWidth;
    private float valueFrom;
    private float valueTo;
    private ArrayList<Float> values;
    private int widgetHeight;
    static final int DEF_STYLE_RES = R.style.Widget_MaterialComponents_Slider;
    private static final int LABEL_ANIMATION_ENTER_DURATION_ATTR = R.attr.motionDurationMedium4;
    private static final int LABEL_ANIMATION_EXIT_DURATION_ATTR = R.attr.motionDurationShort3;
    private static final int LABEL_ANIMATION_ENTER_EASING_ATTR = R.attr.motionEasingEmphasizedInterpolator;
    private static final int LABEL_ANIMATION_EXIT_EASING_ATTR = R.attr.motionEasingEmphasizedAccelerateInterpolator;

    public class AccessibilityEventSender implements Runnable {
        int virtualViewId;

        private AccessibilityEventSender() {
            this.virtualViewId = -1;
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseSlider.this.accessibilityHelper.sendEventForVirtualView(this.virtualViewId, 4);
        }

        public void setVirtualViewId(int i5) {
            this.virtualViewId = i5;
        }
    }

    public static class AccessibilityHelper extends b {
        private final BaseSlider<?, ?, ?> slider;
        final Rect virtualViewBounds;

        public AccessibilityHelper(BaseSlider<?, ?, ?> baseSlider) {
            super(baseSlider);
            this.virtualViewBounds = new Rect();
            this.slider = baseSlider;
        }

        private String startOrEndDescription(int i5) {
            return i5 == this.slider.getValues().size() + (-1) ? this.slider.getContext().getString(R.string.material_slider_range_end) : i5 == 0 ? this.slider.getContext().getString(R.string.material_slider_range_start) : "";
        }

        @Override // androidx.customview.widget.b
        public int getVirtualViewAt(float f2, float f7) {
            for (int i5 = 0; i5 < this.slider.getValues().size(); i5++) {
                this.slider.updateBoundsForVirtualViewId(i5, this.virtualViewBounds);
                if (this.virtualViewBounds.contains((int) f2, (int) f7)) {
                    return i5;
                }
            }
            return -1;
        }

        @Override // androidx.customview.widget.b
        public void getVisibleVirtualViews(List<Integer> list) {
            for (int i5 = 0; i5 < this.slider.getValues().size(); i5++) {
                list.add(Integer.valueOf(i5));
            }
        }

        @Override // androidx.customview.widget.b
        public boolean onPerformActionForVirtualView(int i5, int i7, Bundle bundle) {
            if (!this.slider.isEnabled()) {
                return false;
            }
            if (i7 != 4096 && i7 != 8192) {
                if (i7 == 16908349 && bundle != null && bundle.containsKey("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE")) {
                    if (this.slider.snapThumbToValue(i5, bundle.getFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE"))) {
                        this.slider.updateHaloHotspot();
                        this.slider.postInvalidate();
                        invalidateVirtualView(i5);
                        return true;
                    }
                }
                return false;
            }
            float fCalculateStepIncrement = this.slider.calculateStepIncrement(20);
            if (i7 == 8192) {
                fCalculateStepIncrement = -fCalculateStepIncrement;
            }
            if (this.slider.isRtl()) {
                fCalculateStepIncrement = -fCalculateStepIncrement;
            }
            if (!this.slider.snapThumbToValue(i5, c.c(this.slider.getValues().get(i5).floatValue() + fCalculateStepIncrement, this.slider.getValueFrom(), this.slider.getValueTo()))) {
                return false;
            }
            this.slider.updateHaloHotspot();
            this.slider.postInvalidate();
            invalidateVirtualView(i5);
            return true;
        }

        @Override // androidx.customview.widget.b
        public void onPopulateNodeForVirtualView(int i5, l lVar) {
            lVar.b(f.f1786m);
            List<Float> values = this.slider.getValues();
            float fFloatValue = values.get(i5).floatValue();
            float valueFrom = this.slider.getValueFrom();
            float valueTo = this.slider.getValueTo();
            if (this.slider.isEnabled()) {
                if (fFloatValue > valueFrom) {
                    lVar.a(8192);
                }
                if (fFloatValue < valueTo) {
                    lVar.a(4096);
                }
            }
            lVar.f1793a.setRangeInfo(AccessibilityNodeInfo.RangeInfo.obtain(1, valueFrom, valueTo, fFloatValue));
            lVar.j(SeekBar.class.getName());
            StringBuilder sb = new StringBuilder();
            if (this.slider.getContentDescription() != null) {
                sb.append(this.slider.getContentDescription());
                sb.append(",");
            }
            String value = this.slider.formatValue(fFloatValue);
            String string = this.slider.getContext().getString(R.string.material_slider_value);
            if (values.size() > 1) {
                string = startOrEndDescription(i5);
            }
            Locale locale = Locale.US;
            sb.append(string + ", " + value);
            lVar.m(sb.toString());
            this.slider.updateBoundsForVirtualViewId(i5, this.virtualViewBounds);
            lVar.h(this.virtualViewBounds);
        }
    }

    public static class SliderState extends View.BaseSavedState {
        public static final Parcelable.Creator<SliderState> CREATOR = new Parcelable.Creator<SliderState>() { // from class: com.google.android.material.slider.BaseSlider.SliderState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SliderState createFromParcel(Parcel parcel) {
                return new SliderState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SliderState[] newArray(int i5) {
                return new SliderState[i5];
            }
        };
        boolean hasFocus;
        float stepSize;
        float valueFrom;
        float valueTo;
        ArrayList<Float> values;

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i5) {
            super.writeToParcel(parcel, i5);
            parcel.writeFloat(this.valueFrom);
            parcel.writeFloat(this.valueTo);
            parcel.writeList(this.values);
            parcel.writeFloat(this.stepSize);
            parcel.writeBooleanArray(new boolean[]{this.hasFocus});
        }

        public SliderState(Parcelable parcelable) {
            super(parcelable);
        }

        private SliderState(Parcel parcel) {
            super(parcel);
            this.valueFrom = parcel.readFloat();
            this.valueTo = parcel.readFloat();
            ArrayList<Float> arrayList = new ArrayList<>();
            this.values = arrayList;
            parcel.readList(arrayList, Float.class.getClassLoader());
            this.stepSize = parcel.readFloat();
            this.hasFocus = parcel.createBooleanArray()[0];
        }
    }

    public BaseSlider(Context context) {
        this(context, null);
    }

    private void adjustCustomThumbDrawableBounds(Drawable drawable) {
        int i5 = this.thumbRadius * 2;
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth == -1 && intrinsicHeight == -1) {
            drawable.setBounds(0, 0, i5, i5);
        } else {
            float fMax = i5 / Math.max(intrinsicWidth, intrinsicHeight);
            drawable.setBounds(0, 0, (int) (intrinsicWidth * fMax), (int) (intrinsicHeight * fMax));
        }
    }

    private void attachLabelToContentView(TooltipDrawable tooltipDrawable) {
        tooltipDrawable.setRelativeToView(ViewUtils.getContentView(this));
    }

    private Float calculateIncrementForKey(int i5) {
        float fCalculateStepIncrement = this.isLongPress ? calculateStepIncrement(20) : calculateStepIncrement();
        if (i5 == 21) {
            if (!isRtl()) {
                fCalculateStepIncrement = -fCalculateStepIncrement;
            }
            return Float.valueOf(fCalculateStepIncrement);
        }
        if (i5 == 22) {
            if (isRtl()) {
                fCalculateStepIncrement = -fCalculateStepIncrement;
            }
            return Float.valueOf(fCalculateStepIncrement);
        }
        if (i5 == 69) {
            return Float.valueOf(-fCalculateStepIncrement);
        }
        if (i5 == 70 || i5 == 81) {
            return Float.valueOf(fCalculateStepIncrement);
        }
        return null;
    }

    private float calculateStepIncrement() {
        float f2 = this.stepSize;
        if (f2 == 0.0f) {
            return 1.0f;
        }
        return f2;
    }

    private int calculateTrackCenter() {
        return (this.widgetHeight / 2) + ((this.labelBehavior == 1 || shouldAlwaysShowLabel()) ? this.labels.get(0).getIntrinsicHeight() : 0);
    }

    private ValueAnimator createLabelAnimator(boolean z9) {
        int iResolveThemeDuration;
        TimeInterpolator timeInterpolatorResolveThemeInterpolator;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(getAnimatorCurrentValueOrDefault(z9 ? this.labelsOutAnimator : this.labelsInAnimator, z9 ? 0.0f : 1.0f), z9 ? 1.0f : 0.0f);
        if (z9) {
            iResolveThemeDuration = MotionUtils.resolveThemeDuration(getContext(), LABEL_ANIMATION_ENTER_DURATION_ATTR, 83);
            timeInterpolatorResolveThemeInterpolator = MotionUtils.resolveThemeInterpolator(getContext(), LABEL_ANIMATION_ENTER_EASING_ATTR, AnimationUtils.DECELERATE_INTERPOLATOR);
        } else {
            iResolveThemeDuration = MotionUtils.resolveThemeDuration(getContext(), LABEL_ANIMATION_EXIT_DURATION_ATTR, 117);
            timeInterpolatorResolveThemeInterpolator = MotionUtils.resolveThemeInterpolator(getContext(), LABEL_ANIMATION_EXIT_EASING_ATTR, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        }
        valueAnimatorOfFloat.setDuration(iResolveThemeDuration);
        valueAnimatorOfFloat.setInterpolator(timeInterpolatorResolveThemeInterpolator);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.slider.BaseSlider.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                Iterator it = BaseSlider.this.labels.iterator();
                while (it.hasNext()) {
                    ((TooltipDrawable) it.next()).setRevealFraction(fFloatValue);
                }
                BaseSlider baseSlider = BaseSlider.this;
                WeakHashMap weakHashMap = W.f7199a;
                baseSlider.postInvalidateOnAnimation();
            }
        });
        return valueAnimatorOfFloat;
    }

    private void createLabelPool() {
        if (this.labels.size() > this.values.size()) {
            List<TooltipDrawable> listSubList = this.labels.subList(this.values.size(), this.labels.size());
            for (TooltipDrawable tooltipDrawable : listSubList) {
                WeakHashMap weakHashMap = W.f7199a;
                if (isAttachedToWindow()) {
                    detachLabelFromContentView(tooltipDrawable);
                }
            }
            listSubList.clear();
        }
        while (true) {
            if (this.labels.size() >= this.values.size()) {
                break;
            }
            TooltipDrawable tooltipDrawableCreateFromAttributes = TooltipDrawable.createFromAttributes(getContext(), null, 0, this.labelStyle);
            this.labels.add(tooltipDrawableCreateFromAttributes);
            WeakHashMap weakHashMap2 = W.f7199a;
            if (isAttachedToWindow()) {
                attachLabelToContentView(tooltipDrawableCreateFromAttributes);
            }
        }
        int i5 = this.labels.size() != 1 ? 1 : 0;
        Iterator<TooltipDrawable> it = this.labels.iterator();
        while (it.hasNext()) {
            it.next().setStrokeWidth(i5);
        }
    }

    private void detachLabelFromContentView(TooltipDrawable tooltipDrawable) {
        ViewOverlayImpl contentViewOverlay = ViewUtils.getContentViewOverlay(this);
        if (contentViewOverlay != null) {
            contentViewOverlay.remove(tooltipDrawable);
            tooltipDrawable.detachView(ViewUtils.getContentView(this));
        }
    }

    private float dimenToValue(float f2) {
        if (f2 == 0.0f) {
            return 0.0f;
        }
        float f7 = (f2 - this.trackSidePadding) / this.trackWidth;
        float f9 = this.valueFrom;
        return AbstractC0476d.u(f9, this.valueTo, f7, f9);
    }

    private void dispatchOnChangedFromUser(int i5) {
        Iterator<L> it = this.changeListeners.iterator();
        while (it.hasNext()) {
            it.next().onValueChange(this, this.values.get(i5).floatValue(), true);
        }
        AccessibilityManager accessibilityManager = this.accessibilityManager;
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            return;
        }
        scheduleAccessibilityEventSender(i5);
    }

    private void dispatchOnChangedProgrammatically() {
        for (L l2 : this.changeListeners) {
            Iterator<Float> it = this.values.iterator();
            while (it.hasNext()) {
                l2.onValueChange(this, it.next().floatValue(), false);
            }
        }
    }

    private void drawActiveTrack(Canvas canvas, int i5, int i7) {
        float[] activeRange = getActiveRange();
        int i9 = this.trackSidePadding;
        float f2 = i5;
        float f7 = i7;
        canvas.drawLine((activeRange[0] * f2) + i9, f7, (activeRange[1] * f2) + i9, f7, this.activeTrackPaint);
    }

    private void drawInactiveTrack(Canvas canvas, int i5, int i7) {
        float[] activeRange = getActiveRange();
        float f2 = i5;
        float f7 = (activeRange[1] * f2) + this.trackSidePadding;
        if (f7 < r1 + i5) {
            float f9 = i7;
            canvas.drawLine(f7, f9, r1 + i5, f9, this.inactiveTrackPaint);
        }
        int i9 = this.trackSidePadding;
        float f10 = (activeRange[0] * f2) + i9;
        if (f10 > i9) {
            float f11 = i7;
            canvas.drawLine(i9, f11, f10, f11, this.inactiveTrackPaint);
        }
    }

    private void drawThumbDrawable(Canvas canvas, int i5, int i7, float f2, Drawable drawable) {
        canvas.save();
        canvas.translate((this.trackSidePadding + ((int) (normalizeValue(f2) * i5))) - (drawable.getBounds().width() / 2.0f), i7 - (drawable.getBounds().height() / 2.0f));
        drawable.draw(canvas);
        canvas.restore();
    }

    private void drawThumbs(Canvas canvas, int i5, int i7) {
        for (int i9 = 0; i9 < this.values.size(); i9++) {
            float fFloatValue = this.values.get(i9).floatValue();
            Drawable drawable = this.customThumbDrawable;
            if (drawable != null) {
                drawThumbDrawable(canvas, i5, i7, fFloatValue, drawable);
            } else if (i9 < this.customThumbDrawablesForValues.size()) {
                drawThumbDrawable(canvas, i5, i7, fFloatValue, this.customThumbDrawablesForValues.get(i9));
            } else {
                if (!isEnabled()) {
                    canvas.drawCircle((normalizeValue(fFloatValue) * i5) + this.trackSidePadding, i7, this.thumbRadius, this.thumbPaint);
                }
                drawThumbDrawable(canvas, i5, i7, fFloatValue, this.defaultThumbDrawable);
            }
        }
    }

    private void ensureLabelsAdded() {
        if (this.labelBehavior == 2) {
            return;
        }
        if (!this.labelsAreAnimatedIn) {
            this.labelsAreAnimatedIn = true;
            ValueAnimator valueAnimatorCreateLabelAnimator = createLabelAnimator(true);
            this.labelsInAnimator = valueAnimatorCreateLabelAnimator;
            this.labelsOutAnimator = null;
            valueAnimatorCreateLabelAnimator.start();
        }
        Iterator<TooltipDrawable> it = this.labels.iterator();
        for (int i5 = 0; i5 < this.values.size() && it.hasNext(); i5++) {
            if (i5 != this.focusedThumbIdx) {
                setValueForLabel(it.next(), this.values.get(i5).floatValue());
            }
        }
        if (!it.hasNext()) {
            throw new IllegalStateException(String.format("Not enough labels(%d) to display all the values(%d)", Integer.valueOf(this.labels.size()), Integer.valueOf(this.values.size())));
        }
        setValueForLabel(it.next(), this.values.get(this.focusedThumbIdx).floatValue());
    }

    private void ensureLabelsRemoved() {
        if (this.labelsAreAnimatedIn) {
            this.labelsAreAnimatedIn = false;
            ValueAnimator valueAnimatorCreateLabelAnimator = createLabelAnimator(false);
            this.labelsOutAnimator = valueAnimatorCreateLabelAnimator;
            this.labelsInAnimator = null;
            valueAnimatorCreateLabelAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.slider.BaseSlider.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    ViewOverlayImpl contentViewOverlay = ViewUtils.getContentViewOverlay(BaseSlider.this);
                    Iterator it = BaseSlider.this.labels.iterator();
                    while (it.hasNext()) {
                        contentViewOverlay.remove((TooltipDrawable) it.next());
                    }
                }
            });
            this.labelsOutAnimator.start();
        }
    }

    private void focusThumbOnFocusGained(int i5) {
        if (i5 == 1) {
            moveFocus(Integer.MAX_VALUE);
            return;
        }
        if (i5 == 2) {
            moveFocus(Integer.MIN_VALUE);
        } else if (i5 == 17) {
            moveFocusInAbsoluteDirection(Integer.MAX_VALUE);
        } else {
            if (i5 != 66) {
                return;
            }
            moveFocusInAbsoluteDirection(Integer.MIN_VALUE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String formatValue(float f2) {
        if (hasLabelFormatter()) {
            return this.formatter.getFormattedValue(f2);
        }
        return String.format(((float) ((int) f2)) == f2 ? "%.0f" : "%.2f", Float.valueOf(f2));
    }

    private float[] getActiveRange() {
        float fFloatValue = ((Float) Collections.max(getValues())).floatValue();
        float fFloatValue2 = ((Float) Collections.min(getValues())).floatValue();
        if (this.values.size() == 1) {
            fFloatValue2 = this.valueFrom;
        }
        float fNormalizeValue = normalizeValue(fFloatValue2);
        float fNormalizeValue2 = normalizeValue(fFloatValue);
        return isRtl() ? new float[]{fNormalizeValue2, fNormalizeValue} : new float[]{fNormalizeValue, fNormalizeValue2};
    }

    private static float getAnimatorCurrentValueOrDefault(ValueAnimator valueAnimator, float f2) {
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            return f2;
        }
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        valueAnimator.cancel();
        return fFloatValue;
    }

    private float getClampedValue(int i5, float f2) {
        float minSeparation = getMinSeparation();
        if (this.separationUnit == 0) {
            minSeparation = dimenToValue(minSeparation);
        }
        if (isRtl()) {
            minSeparation = -minSeparation;
        }
        int i7 = i5 + 1;
        int i9 = i5 - 1;
        return c.c(f2, i9 < 0 ? this.valueFrom : this.values.get(i9).floatValue() + minSeparation, i7 >= this.values.size() ? this.valueTo : this.values.get(i7).floatValue() - minSeparation);
    }

    private int getColorForState(ColorStateList colorStateList) {
        return colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
    }

    private float getValueOfTouchPosition() {
        double dSnapPosition = snapPosition(this.touchPosition);
        if (isRtl()) {
            dSnapPosition = 1.0d - dSnapPosition;
        }
        float f2 = this.valueTo;
        float f7 = this.valueFrom;
        return (float) ((dSnapPosition * ((double) (f2 - f7))) + ((double) f7));
    }

    private float getValueOfTouchPositionAbsolute() {
        float f2 = this.touchPosition;
        if (isRtl()) {
            f2 = 1.0f - f2;
        }
        float f7 = this.valueTo;
        float f9 = this.valueFrom;
        return AbstractC0476d.u(f7, f9, f2, f9);
    }

    private Drawable initializeCustomThumbDrawable(Drawable drawable) {
        Drawable drawableNewDrawable = drawable.mutate().getConstantState().newDrawable();
        adjustCustomThumbDrawableBounds(drawableNewDrawable);
        return drawableNewDrawable;
    }

    private void invalidateTrack() {
        this.inactiveTrackPaint.setStrokeWidth(this.trackHeight);
        this.activeTrackPaint.setStrokeWidth(this.trackHeight);
    }

    private boolean isInVerticalScrollingContainer() {
        for (ViewParent parent = getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
            ViewGroup viewGroup = (ViewGroup) parent;
            if ((viewGroup.canScrollVertically(1) || viewGroup.canScrollVertically(-1)) && viewGroup.shouldDelayChildPressedState()) {
                return true;
            }
        }
        return false;
    }

    private static boolean isMouseEvent(MotionEvent motionEvent) {
        return motionEvent.getToolType(0) == 3;
    }

    private boolean isMultipleOfStepSize(float f2) {
        double dDoubleValue = new BigDecimal(Float.toString(f2)).divide(new BigDecimal(Float.toString(this.stepSize)), MathContext.DECIMAL64).doubleValue();
        return Math.abs(((double) Math.round(dDoubleValue)) - dDoubleValue) < THRESHOLD;
    }

    private boolean isPotentialVerticalScroll(MotionEvent motionEvent) {
        return !isMouseEvent(motionEvent) && isInVerticalScrollingContainer();
    }

    private void loadResources(Resources resources) {
        this.minWidgetHeight = resources.getDimensionPixelSize(R.dimen.mtrl_slider_widget_height);
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.mtrl_slider_track_side_padding);
        this.minTrackSidePadding = dimensionPixelOffset;
        this.trackSidePadding = dimensionPixelOffset;
        this.defaultThumbRadius = resources.getDimensionPixelSize(R.dimen.mtrl_slider_thumb_radius);
        this.defaultTrackHeight = resources.getDimensionPixelSize(R.dimen.mtrl_slider_track_height);
        int i5 = R.dimen.mtrl_slider_tick_radius;
        this.defaultTickActiveRadius = resources.getDimensionPixelSize(i5);
        this.defaultTickInactiveRadius = resources.getDimensionPixelSize(i5);
        this.labelPadding = resources.getDimensionPixelSize(R.dimen.mtrl_slider_label_padding);
    }

    private void maybeCalculateTicksCoordinates() {
        if (this.stepSize <= 0.0f) {
            return;
        }
        validateConfigurationIfDirty();
        int iMin = Math.min((int) (((this.valueTo - this.valueFrom) / this.stepSize) + 1.0f), (this.trackWidth / (this.trackHeight * 2)) + 1);
        float[] fArr = this.ticksCoordinates;
        if (fArr == null || fArr.length != iMin * 2) {
            this.ticksCoordinates = new float[iMin * 2];
        }
        float f2 = this.trackWidth / (iMin - 1);
        for (int i5 = 0; i5 < iMin * 2; i5 += 2) {
            float[] fArr2 = this.ticksCoordinates;
            fArr2[i5] = ((i5 / 2.0f) * f2) + this.trackSidePadding;
            fArr2[i5 + 1] = calculateTrackCenter();
        }
    }

    private void maybeDrawCompatHalo(Canvas canvas, int i5, int i7) {
        if (shouldDrawCompatHalo()) {
            canvas.drawCircle((int) ((normalizeValue(this.values.get(this.focusedThumbIdx).floatValue()) * i5) + this.trackSidePadding), i7, this.haloRadius, this.haloPaint);
        }
    }

    private void maybeDrawTicks(Canvas canvas) {
        if (!this.tickVisible || this.stepSize <= 0.0f) {
            return;
        }
        float[] activeRange = getActiveRange();
        int iPivotIndex = pivotIndex(this.ticksCoordinates, activeRange[0]);
        int iPivotIndex2 = pivotIndex(this.ticksCoordinates, activeRange[1]);
        int i5 = iPivotIndex * 2;
        canvas.drawPoints(this.ticksCoordinates, 0, i5, this.inactiveTicksPaint);
        int i7 = iPivotIndex2 * 2;
        canvas.drawPoints(this.ticksCoordinates, i5, i7 - i5, this.activeTicksPaint);
        float[] fArr = this.ticksCoordinates;
        canvas.drawPoints(fArr, i7, fArr.length - i7, this.inactiveTicksPaint);
    }

    private boolean maybeIncreaseTrackSidePadding() {
        int iMax = Math.max(this.thumbRadius - this.defaultThumbRadius, 0);
        int iMax2 = Math.max((this.trackHeight - this.defaultTrackHeight) / 2, 0);
        int iMax3 = Math.max(this.tickActiveRadius - this.defaultTickActiveRadius, 0);
        int iMax4 = Math.max(this.tickInactiveRadius - this.defaultTickInactiveRadius, 0);
        int iMax5 = Math.max(Math.max(iMax, iMax2), Math.max(iMax3, iMax4)) + this.minTrackSidePadding;
        if (this.trackSidePadding == iMax5) {
            return false;
        }
        this.trackSidePadding = iMax5;
        WeakHashMap weakHashMap = W.f7199a;
        if (!isLaidOut()) {
            return true;
        }
        updateTrackWidth(getWidth());
        return true;
    }

    private boolean maybeIncreaseWidgetHeight() {
        int iMax = Math.max(this.minWidgetHeight, Math.max(this.trackHeight + getPaddingBottom() + getPaddingTop(), getPaddingBottom() + getPaddingTop() + (this.thumbRadius * 2)));
        if (iMax == this.widgetHeight) {
            return false;
        }
        this.widgetHeight = iMax;
        return true;
    }

    private boolean moveFocus(int i5) {
        int i7 = this.focusedThumbIdx;
        long j5 = ((long) i7) + ((long) i5);
        long size = this.values.size() - 1;
        if (j5 < 0) {
            j5 = 0;
        } else if (j5 > size) {
            j5 = size;
        }
        int i9 = (int) j5;
        this.focusedThumbIdx = i9;
        if (i9 == i7) {
            return false;
        }
        if (this.activeThumbIdx != -1) {
            this.activeThumbIdx = i9;
        }
        updateHaloHotspot();
        postInvalidate();
        return true;
    }

    private boolean moveFocusInAbsoluteDirection(int i5) {
        if (isRtl()) {
            i5 = i5 == Integer.MIN_VALUE ? Integer.MAX_VALUE : -i5;
        }
        return moveFocus(i5);
    }

    private float normalizeValue(float f2) {
        float f7 = this.valueFrom;
        float f9 = (f2 - f7) / (this.valueTo - f7);
        return isRtl() ? 1.0f - f9 : f9;
    }

    private Boolean onKeyDownNoActiveThumb(int i5, KeyEvent keyEvent) {
        if (i5 == 61) {
            return keyEvent.hasNoModifiers() ? Boolean.valueOf(moveFocus(1)) : keyEvent.isShiftPressed() ? Boolean.valueOf(moveFocus(-1)) : Boolean.FALSE;
        }
        if (i5 != 66) {
            if (i5 != 81) {
                if (i5 == 69) {
                    moveFocus(-1);
                    return Boolean.TRUE;
                }
                if (i5 != 70) {
                    switch (i5) {
                        case 21:
                            moveFocusInAbsoluteDirection(-1);
                            break;
                        case 22:
                            moveFocusInAbsoluteDirection(1);
                            break;
                    }
                    return Boolean.TRUE;
                }
            }
            moveFocus(1);
            return Boolean.TRUE;
        }
        this.activeThumbIdx = this.focusedThumbIdx;
        postInvalidate();
        return Boolean.TRUE;
    }

    private void onStartTrackingTouch() {
        Iterator<T> it = this.touchListeners.iterator();
        while (it.hasNext()) {
            it.next().onStartTrackingTouch(this);
        }
    }

    private void onStopTrackingTouch() {
        Iterator<T> it = this.touchListeners.iterator();
        while (it.hasNext()) {
            it.next().onStopTrackingTouch(this);
        }
    }

    private static int pivotIndex(float[] fArr, float f2) {
        return Math.round(f2 * ((fArr.length / 2) - 1));
    }

    private void processAttributes(Context context, AttributeSet attributeSet, int i5) {
        TypedArray typedArrayObtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, R.styleable.Slider, i5, DEF_STYLE_RES, new int[0]);
        this.labelStyle = typedArrayObtainStyledAttributes.getResourceId(R.styleable.Slider_labelStyle, R.style.Widget_MaterialComponents_Tooltip);
        this.valueFrom = typedArrayObtainStyledAttributes.getFloat(R.styleable.Slider_android_valueFrom, 0.0f);
        this.valueTo = typedArrayObtainStyledAttributes.getFloat(R.styleable.Slider_android_valueTo, 1.0f);
        setValues(Float.valueOf(this.valueFrom));
        this.stepSize = typedArrayObtainStyledAttributes.getFloat(R.styleable.Slider_android_stepSize, 0.0f);
        this.minTouchTargetSize = (int) Math.ceil(typedArrayObtainStyledAttributes.getDimension(R.styleable.Slider_minTouchTargetSize, (float) Math.ceil(ViewUtils.dpToPx(getContext(), 48))));
        int i7 = R.styleable.Slider_trackColor;
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(i7);
        int i9 = zHasValue ? i7 : R.styleable.Slider_trackColorInactive;
        if (!zHasValue) {
            i7 = R.styleable.Slider_trackColorActive;
        }
        ColorStateList colorStateList = MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes, i9);
        if (colorStateList == null) {
            colorStateList = a.f(context, R.color.material_slider_inactive_track_color);
        }
        setTrackInactiveTintList(colorStateList);
        ColorStateList colorStateList2 = MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes, i7);
        if (colorStateList2 == null) {
            colorStateList2 = a.f(context, R.color.material_slider_active_track_color);
        }
        setTrackActiveTintList(colorStateList2);
        this.defaultThumbDrawable.setFillColor(MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes, R.styleable.Slider_thumbColor));
        int i10 = R.styleable.Slider_thumbStrokeColor;
        if (typedArrayObtainStyledAttributes.hasValue(i10)) {
            setThumbStrokeColor(MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes, i10));
        }
        setThumbStrokeWidth(typedArrayObtainStyledAttributes.getDimension(R.styleable.Slider_thumbStrokeWidth, 0.0f));
        ColorStateList colorStateList3 = MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes, R.styleable.Slider_haloColor);
        if (colorStateList3 == null) {
            colorStateList3 = a.f(context, R.color.material_slider_halo_color);
        }
        setHaloTintList(colorStateList3);
        this.tickVisible = typedArrayObtainStyledAttributes.getBoolean(R.styleable.Slider_tickVisible, true);
        int i11 = R.styleable.Slider_tickColor;
        boolean zHasValue2 = typedArrayObtainStyledAttributes.hasValue(i11);
        int i12 = zHasValue2 ? i11 : R.styleable.Slider_tickColorInactive;
        if (!zHasValue2) {
            i11 = R.styleable.Slider_tickColorActive;
        }
        ColorStateList colorStateList4 = MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes, i12);
        if (colorStateList4 == null) {
            colorStateList4 = a.f(context, R.color.material_slider_inactive_tick_marks_color);
        }
        setTickInactiveTintList(colorStateList4);
        ColorStateList colorStateList5 = MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes, i11);
        if (colorStateList5 == null) {
            colorStateList5 = a.f(context, R.color.material_slider_active_tick_marks_color);
        }
        setTickActiveTintList(colorStateList5);
        setThumbRadius(typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_thumbRadius, 0));
        setHaloRadius(typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_haloRadius, 0));
        setThumbElevation(typedArrayObtainStyledAttributes.getDimension(R.styleable.Slider_thumbElevation, 0.0f));
        setTrackHeight(typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_trackHeight, 0));
        setTickActiveRadius(typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_tickRadiusActive, 0));
        setTickInactiveRadius(typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_tickRadiusInactive, 0));
        setLabelBehavior(typedArrayObtainStyledAttributes.getInt(R.styleable.Slider_labelBehavior, 0));
        if (!typedArrayObtainStyledAttributes.getBoolean(R.styleable.Slider_android_enabled, true)) {
            setEnabled(false);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    private void scheduleAccessibilityEventSender(int i5) {
        BaseSlider<S, L, T>.AccessibilityEventSender accessibilityEventSender = this.accessibilityEventSender;
        if (accessibilityEventSender == null) {
            this.accessibilityEventSender = new AccessibilityEventSender();
        } else {
            removeCallbacks(accessibilityEventSender);
        }
        this.accessibilityEventSender.setVirtualViewId(i5);
        postDelayed(this.accessibilityEventSender, 200L);
    }

    private void setValueForLabel(TooltipDrawable tooltipDrawable, float f2) {
        tooltipDrawable.setText(formatValue(f2));
        int iNormalizeValue = (this.trackSidePadding + ((int) (normalizeValue(f2) * this.trackWidth))) - (tooltipDrawable.getIntrinsicWidth() / 2);
        int iCalculateTrackCenter = calculateTrackCenter() - (this.labelPadding + this.thumbRadius);
        tooltipDrawable.setBounds(iNormalizeValue, iCalculateTrackCenter - tooltipDrawable.getIntrinsicHeight(), tooltipDrawable.getIntrinsicWidth() + iNormalizeValue, iCalculateTrackCenter);
        Rect rect = new Rect(tooltipDrawable.getBounds());
        DescendantOffsetUtils.offsetDescendantRect(ViewUtils.getContentView(this), this, rect);
        tooltipDrawable.setBounds(rect);
        ViewUtils.getContentViewOverlay(this).add(tooltipDrawable);
    }

    private void setValuesInternal(ArrayList<Float> arrayList) {
        if (arrayList.isEmpty()) {
            throw new IllegalArgumentException("At least one value must be set");
        }
        Collections.sort(arrayList);
        if (this.values.size() == arrayList.size() && this.values.equals(arrayList)) {
            return;
        }
        this.values = arrayList;
        this.dirtyConfig = true;
        this.focusedThumbIdx = 0;
        updateHaloHotspot();
        createLabelPool();
        dispatchOnChangedProgrammatically();
        postInvalidate();
    }

    private boolean shouldAlwaysShowLabel() {
        return this.labelBehavior == 3;
    }

    private boolean shouldDrawCompatHalo() {
        return this.forceDrawCompatHalo || !(getBackground() instanceof RippleDrawable);
    }

    private boolean snapActiveThumbToValue(float f2) {
        return snapThumbToValue(this.activeThumbIdx, f2);
    }

    private double snapPosition(float f2) {
        float f7 = this.stepSize;
        if (f7 <= 0.0f) {
            return f2;
        }
        int i5 = (int) ((this.valueTo - this.valueFrom) / f7);
        return ((double) Math.round(f2 * i5)) / ((double) i5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean snapThumbToValue(int i5, float f2) {
        this.focusedThumbIdx = i5;
        if (Math.abs(f2 - this.values.get(i5).floatValue()) < THRESHOLD) {
            return false;
        }
        this.values.set(i5, Float.valueOf(getClampedValue(i5, f2)));
        dispatchOnChangedFromUser(i5);
        return true;
    }

    private boolean snapTouchPosition() {
        return snapActiveThumbToValue(getValueOfTouchPosition());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateHaloHotspot() {
        if (shouldDrawCompatHalo() || getMeasuredWidth() <= 0) {
            return;
        }
        Drawable background = getBackground();
        if (background instanceof RippleDrawable) {
            int iNormalizeValue = (int) ((normalizeValue(this.values.get(this.focusedThumbIdx).floatValue()) * this.trackWidth) + this.trackSidePadding);
            int iCalculateTrackCenter = calculateTrackCenter();
            int i5 = this.haloRadius;
            E.a.f(background, iNormalizeValue - i5, iCalculateTrackCenter - i5, iNormalizeValue + i5, iCalculateTrackCenter + i5);
        }
    }

    private void updateTrackWidth(int i5) {
        this.trackWidth = Math.max(i5 - (this.trackSidePadding * 2), 0);
        maybeCalculateTicksCoordinates();
    }

    private void updateWidgetLayout() {
        boolean zMaybeIncreaseWidgetHeight = maybeIncreaseWidgetHeight();
        boolean zMaybeIncreaseTrackSidePadding = maybeIncreaseTrackSidePadding();
        if (zMaybeIncreaseWidgetHeight) {
            requestLayout();
        } else if (zMaybeIncreaseTrackSidePadding) {
            postInvalidate();
        }
    }

    private void validateConfigurationIfDirty() {
        if (this.dirtyConfig) {
            validateValueFrom();
            validateValueTo();
            validateStepSize();
            validateValues();
            validateMinSeparation();
            warnAboutFloatingPointError();
            this.dirtyConfig = false;
        }
    }

    private void validateMinSeparation() {
        float minSeparation = getMinSeparation();
        if (minSeparation < 0.0f) {
            throw new IllegalStateException("minSeparation(" + minSeparation + ") must be greater or equal to 0");
        }
        float f2 = this.stepSize;
        if (f2 <= 0.0f || minSeparation <= 0.0f) {
            return;
        }
        if (this.separationUnit != 1) {
            throw new IllegalStateException("minSeparation(" + minSeparation + ") cannot be set as a dimension when using stepSize(" + this.stepSize + ")");
        }
        if (minSeparation < f2 || !isMultipleOfStepSize(minSeparation)) {
            throw new IllegalStateException("minSeparation(" + minSeparation + ") must be greater or equal and a multiple of stepSize(" + this.stepSize + ") when using stepSize(" + this.stepSize + ")");
        }
    }

    private void validateStepSize() {
        if (this.stepSize <= 0.0f || valueLandsOnTick(this.valueTo)) {
            return;
        }
        throw new IllegalStateException("The stepSize(" + this.stepSize + ") must be 0, or a factor of the valueFrom(" + this.valueFrom + ")-valueTo(" + this.valueTo + ") range");
    }

    private void validateValueFrom() {
        if (this.valueFrom < this.valueTo) {
            return;
        }
        throw new IllegalStateException("valueFrom(" + this.valueFrom + ") must be smaller than valueTo(" + this.valueTo + ")");
    }

    private void validateValueTo() {
        if (this.valueTo > this.valueFrom) {
            return;
        }
        throw new IllegalStateException("valueTo(" + this.valueTo + ") must be greater than valueFrom(" + this.valueFrom + ")");
    }

    private void validateValues() {
        for (Float f2 : this.values) {
            if (f2.floatValue() < this.valueFrom || f2.floatValue() > this.valueTo) {
                throw new IllegalStateException("Slider value(" + f2 + ") must be greater or equal to valueFrom(" + this.valueFrom + "), and lower or equal to valueTo(" + this.valueTo + ")");
            }
            if (this.stepSize > 0.0f && !valueLandsOnTick(f2.floatValue())) {
                throw new IllegalStateException("Value(" + f2 + ") must be equal to valueFrom(" + this.valueFrom + ") plus a multiple of stepSize(" + this.stepSize + ") when using stepSize(" + this.stepSize + ")");
            }
        }
    }

    private boolean valueLandsOnTick(float f2) {
        return isMultipleOfStepSize(f2 - this.valueFrom);
    }

    private float valueToX(float f2) {
        return (normalizeValue(f2) * this.trackWidth) + this.trackSidePadding;
    }

    private void warnAboutFloatingPointError() {
        float f2 = this.stepSize;
        if (f2 == 0.0f) {
            return;
        }
        if (((int) f2) != f2) {
            Log.w(TAG, "Floating point value used for stepSize(" + f2 + "). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.");
        }
        float f7 = this.valueFrom;
        if (((int) f7) != f7) {
            Log.w(TAG, "Floating point value used for valueFrom(" + f7 + "). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.");
        }
        float f9 = this.valueTo;
        if (((int) f9) != f9) {
            Log.w(TAG, "Floating point value used for valueTo(" + f9 + "). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.");
        }
    }

    public void addOnChangeListener(L l2) {
        this.changeListeners.add(l2);
    }

    public void addOnSliderTouchListener(T t8) {
        this.touchListeners.add(t8);
    }

    public void clearOnChangeListeners() {
        this.changeListeners.clear();
    }

    public void clearOnSliderTouchListeners() {
        this.touchListeners.clear();
    }

    @Override // android.view.View
    public boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return this.accessibilityHelper.dispatchHoverEvent(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        this.inactiveTrackPaint.setColor(getColorForState(this.trackColorInactive));
        this.activeTrackPaint.setColor(getColorForState(this.trackColorActive));
        this.inactiveTicksPaint.setColor(getColorForState(this.tickColorInactive));
        this.activeTicksPaint.setColor(getColorForState(this.tickColorActive));
        for (TooltipDrawable tooltipDrawable : this.labels) {
            if (tooltipDrawable.isStateful()) {
                tooltipDrawable.setState(getDrawableState());
            }
        }
        if (this.defaultThumbDrawable.isStateful()) {
            this.defaultThumbDrawable.setState(getDrawableState());
        }
        this.haloPaint.setColor(getColorForState(this.haloColor));
        this.haloPaint.setAlpha(63);
    }

    public void forceDrawCompatHalo(boolean z9) {
        this.forceDrawCompatHalo = z9;
    }

    @Override // android.view.View
    public CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    public final int getAccessibilityFocusedVirtualViewId() {
        return this.accessibilityHelper.getAccessibilityFocusedVirtualViewId();
    }

    public int getActiveThumbIndex() {
        return this.activeThumbIdx;
    }

    public int getFocusedThumbIndex() {
        return this.focusedThumbIdx;
    }

    public int getHaloRadius() {
        return this.haloRadius;
    }

    public ColorStateList getHaloTintList() {
        return this.haloColor;
    }

    public int getLabelBehavior() {
        return this.labelBehavior;
    }

    public float getMinSeparation() {
        return 0.0f;
    }

    public float getStepSize() {
        return this.stepSize;
    }

    public float getThumbElevation() {
        return this.defaultThumbDrawable.getElevation();
    }

    public int getThumbRadius() {
        return this.thumbRadius;
    }

    public ColorStateList getThumbStrokeColor() {
        return this.defaultThumbDrawable.getStrokeColor();
    }

    public float getThumbStrokeWidth() {
        return this.defaultThumbDrawable.getStrokeWidth();
    }

    public ColorStateList getThumbTintList() {
        return this.defaultThumbDrawable.getFillColor();
    }

    public int getTickActiveRadius() {
        return this.tickActiveRadius;
    }

    public ColorStateList getTickActiveTintList() {
        return this.tickColorActive;
    }

    public int getTickInactiveRadius() {
        return this.tickInactiveRadius;
    }

    public ColorStateList getTickInactiveTintList() {
        return this.tickColorInactive;
    }

    public ColorStateList getTickTintList() {
        if (this.tickColorInactive.equals(this.tickColorActive)) {
            return this.tickColorActive;
        }
        throw new IllegalStateException("The inactive and active ticks are different colors. Use the getTickColorInactive() and getTickColorActive() methods instead.");
    }

    public ColorStateList getTrackActiveTintList() {
        return this.trackColorActive;
    }

    public int getTrackHeight() {
        return this.trackHeight;
    }

    public ColorStateList getTrackInactiveTintList() {
        return this.trackColorInactive;
    }

    public int getTrackSidePadding() {
        return this.trackSidePadding;
    }

    public ColorStateList getTrackTintList() {
        if (this.trackColorInactive.equals(this.trackColorActive)) {
            return this.trackColorActive;
        }
        throw new IllegalStateException("The inactive and active parts of the track are different colors. Use the getInactiveTrackColor() and getActiveTrackColor() methods instead.");
    }

    public int getTrackWidth() {
        return this.trackWidth;
    }

    public float getValueFrom() {
        return this.valueFrom;
    }

    public float getValueTo() {
        return this.valueTo;
    }

    public List<Float> getValues() {
        return new ArrayList(this.values);
    }

    public boolean hasLabelFormatter() {
        return this.formatter != null;
    }

    public final boolean isRtl() {
        WeakHashMap weakHashMap = W.f7199a;
        return getLayoutDirection() == 1;
    }

    public boolean isTickVisible() {
        return this.tickVisible;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Iterator<TooltipDrawable> it = this.labels.iterator();
        while (it.hasNext()) {
            attachLabelToContentView(it.next());
        }
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        BaseSlider<S, L, T>.AccessibilityEventSender accessibilityEventSender = this.accessibilityEventSender;
        if (accessibilityEventSender != null) {
            removeCallbacks(accessibilityEventSender);
        }
        this.labelsAreAnimatedIn = false;
        Iterator<TooltipDrawable> it = this.labels.iterator();
        while (it.hasNext()) {
            detachLabelFromContentView(it.next());
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        if (this.dirtyConfig) {
            validateConfigurationIfDirty();
            maybeCalculateTicksCoordinates();
        }
        super.onDraw(canvas);
        int iCalculateTrackCenter = calculateTrackCenter();
        drawInactiveTrack(canvas, this.trackWidth, iCalculateTrackCenter);
        if (((Float) Collections.max(getValues())).floatValue() > this.valueFrom) {
            drawActiveTrack(canvas, this.trackWidth, iCalculateTrackCenter);
        }
        maybeDrawTicks(canvas);
        if ((this.thumbIsPressed || isFocused()) && isEnabled()) {
            maybeDrawCompatHalo(canvas, this.trackWidth, iCalculateTrackCenter);
        }
        if ((this.activeThumbIdx != -1 || shouldAlwaysShowLabel()) && isEnabled()) {
            ensureLabelsAdded();
        } else {
            ensureLabelsRemoved();
        }
        drawThumbs(canvas, this.trackWidth, iCalculateTrackCenter);
    }

    @Override // android.view.View
    public void onFocusChanged(boolean z9, int i5, Rect rect) {
        super.onFocusChanged(z9, i5, rect);
        if (z9) {
            focusThumbOnFocusGained(i5);
            this.accessibilityHelper.requestKeyboardFocusForVirtualView(this.focusedThumbIdx);
        } else {
            this.activeThumbIdx = -1;
            this.accessibilityHelper.clearKeyboardFocusForVirtualView(this.focusedThumbIdx);
        }
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i5, KeyEvent keyEvent) {
        if (!isEnabled()) {
            return super.onKeyDown(i5, keyEvent);
        }
        if (this.values.size() == 1) {
            this.activeThumbIdx = 0;
        }
        if (this.activeThumbIdx == -1) {
            Boolean boolOnKeyDownNoActiveThumb = onKeyDownNoActiveThumb(i5, keyEvent);
            return boolOnKeyDownNoActiveThumb != null ? boolOnKeyDownNoActiveThumb.booleanValue() : super.onKeyDown(i5, keyEvent);
        }
        this.isLongPress |= keyEvent.isLongPress();
        Float fCalculateIncrementForKey = calculateIncrementForKey(i5);
        if (fCalculateIncrementForKey != null) {
            if (snapActiveThumbToValue(fCalculateIncrementForKey.floatValue() + this.values.get(this.activeThumbIdx).floatValue())) {
                updateHaloHotspot();
                postInvalidate();
            }
            return true;
        }
        if (i5 != 23) {
            if (i5 == 61) {
                if (keyEvent.hasNoModifiers()) {
                    return moveFocus(1);
                }
                if (keyEvent.isShiftPressed()) {
                    return moveFocus(-1);
                }
                return false;
            }
            if (i5 != 66) {
                return super.onKeyDown(i5, keyEvent);
            }
        }
        this.activeThumbIdx = -1;
        postInvalidate();
        return true;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i5, KeyEvent keyEvent) {
        this.isLongPress = false;
        return super.onKeyUp(i5, keyEvent);
    }

    @Override // android.view.View
    public void onMeasure(int i5, int i7) {
        super.onMeasure(i5, View.MeasureSpec.makeMeasureSpec(this.widgetHeight + ((this.labelBehavior == 1 || shouldAlwaysShowLabel()) ? this.labels.get(0).getIntrinsicHeight() : 0), 1073741824));
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SliderState sliderState = (SliderState) parcelable;
        super.onRestoreInstanceState(sliderState.getSuperState());
        this.valueFrom = sliderState.valueFrom;
        this.valueTo = sliderState.valueTo;
        setValuesInternal(sliderState.values);
        this.stepSize = sliderState.stepSize;
        if (sliderState.hasFocus) {
            requestFocus();
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SliderState sliderState = new SliderState(super.onSaveInstanceState());
        sliderState.valueFrom = this.valueFrom;
        sliderState.valueTo = this.valueTo;
        sliderState.values = new ArrayList<>(this.values);
        sliderState.stepSize = this.stepSize;
        sliderState.hasFocus = hasFocus();
        return sliderState;
    }

    @Override // android.view.View
    public void onSizeChanged(int i5, int i7, int i9, int i10) {
        updateTrackWidth(i5);
        updateHaloHotspot();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x006f  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r6) {
        /*
            Method dump skipped, instruction units count: 248
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public void onVisibilityChanged(View view, int i5) {
        ViewOverlayImpl contentViewOverlay;
        super.onVisibilityChanged(view, i5);
        if (i5 == 0 || (contentViewOverlay = ViewUtils.getContentViewOverlay(this)) == null) {
            return;
        }
        Iterator<TooltipDrawable> it = this.labels.iterator();
        while (it.hasNext()) {
            contentViewOverlay.remove(it.next());
        }
    }

    public boolean pickActiveThumb() {
        if (this.activeThumbIdx != -1) {
            return true;
        }
        float valueOfTouchPositionAbsolute = getValueOfTouchPositionAbsolute();
        float fValueToX = valueToX(valueOfTouchPositionAbsolute);
        this.activeThumbIdx = 0;
        float fAbs = Math.abs(this.values.get(0).floatValue() - valueOfTouchPositionAbsolute);
        for (int i5 = 1; i5 < this.values.size(); i5++) {
            float fAbs2 = Math.abs(this.values.get(i5).floatValue() - valueOfTouchPositionAbsolute);
            float fValueToX2 = valueToX(this.values.get(i5).floatValue());
            if (Float.compare(fAbs2, fAbs) > 1) {
                break;
            }
            boolean z9 = !isRtl() ? fValueToX2 - fValueToX >= 0.0f : fValueToX2 - fValueToX <= 0.0f;
            if (Float.compare(fAbs2, fAbs) < 0) {
                this.activeThumbIdx = i5;
            } else {
                if (Float.compare(fAbs2, fAbs) != 0) {
                    continue;
                } else {
                    if (Math.abs(fValueToX2 - fValueToX) < this.scaledTouchSlop) {
                        this.activeThumbIdx = -1;
                        return false;
                    }
                    if (z9) {
                        this.activeThumbIdx = i5;
                    }
                }
            }
            fAbs = fAbs2;
        }
        return this.activeThumbIdx != -1;
    }

    public void removeOnChangeListener(L l2) {
        this.changeListeners.remove(l2);
    }

    public void removeOnSliderTouchListener(T t8) {
        this.touchListeners.remove(t8);
    }

    public void setActiveThumbIndex(int i5) {
        this.activeThumbIdx = i5;
    }

    public void setCustomThumbDrawable(int i5) {
        setCustomThumbDrawable(getResources().getDrawable(i5));
    }

    public void setCustomThumbDrawablesForValues(int... iArr) {
        Drawable[] drawableArr = new Drawable[iArr.length];
        for (int i5 = 0; i5 < iArr.length; i5++) {
            drawableArr[i5] = getResources().getDrawable(iArr[i5]);
        }
        setCustomThumbDrawablesForValues(drawableArr);
    }

    @Override // android.view.View
    public void setEnabled(boolean z9) {
        super.setEnabled(z9);
        setLayerType(z9 ? 0 : 2, null);
    }

    public void setFocusedThumbIndex(int i5) {
        if (i5 < 0 || i5 >= this.values.size()) {
            throw new IllegalArgumentException("index out of range");
        }
        this.focusedThumbIdx = i5;
        this.accessibilityHelper.requestKeyboardFocusForVirtualView(i5);
        postInvalidate();
    }

    public void setHaloRadius(int i5) {
        if (i5 == this.haloRadius) {
            return;
        }
        this.haloRadius = i5;
        Drawable background = getBackground();
        if (shouldDrawCompatHalo() || !(background instanceof RippleDrawable)) {
            postInvalidate();
        } else {
            DrawableUtils.setRippleDrawableRadius((RippleDrawable) background, this.haloRadius);
        }
    }

    public void setHaloRadiusResource(int i5) {
        setHaloRadius(getResources().getDimensionPixelSize(i5));
    }

    public void setHaloTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.haloColor)) {
            return;
        }
        this.haloColor = colorStateList;
        Drawable background = getBackground();
        if (!shouldDrawCompatHalo() && (background instanceof RippleDrawable)) {
            ((RippleDrawable) background).setColor(colorStateList);
            return;
        }
        this.haloPaint.setColor(getColorForState(colorStateList));
        this.haloPaint.setAlpha(63);
        invalidate();
    }

    public void setLabelBehavior(int i5) {
        if (this.labelBehavior != i5) {
            this.labelBehavior = i5;
            requestLayout();
        }
    }

    public void setLabelFormatter(LabelFormatter labelFormatter) {
        this.formatter = labelFormatter;
    }

    public void setSeparationUnit(int i5) {
        this.separationUnit = i5;
        this.dirtyConfig = true;
        postInvalidate();
    }

    public void setStepSize(float f2) {
        if (f2 >= 0.0f) {
            if (this.stepSize != f2) {
                this.stepSize = f2;
                this.dirtyConfig = true;
                postInvalidate();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("The stepSize(" + f2 + ") must be 0, or a factor of the valueFrom(" + this.valueFrom + ")-valueTo(" + this.valueTo + ") range");
    }

    public void setThumbElevation(float f2) {
        this.defaultThumbDrawable.setElevation(f2);
    }

    public void setThumbElevationResource(int i5) {
        setThumbElevation(getResources().getDimension(i5));
    }

    public void setThumbRadius(int i5) {
        if (i5 == this.thumbRadius) {
            return;
        }
        this.thumbRadius = i5;
        this.defaultThumbDrawable.setShapeAppearanceModel(ShapeAppearanceModel.builder().setAllCorners(0, this.thumbRadius).build());
        MaterialShapeDrawable materialShapeDrawable = this.defaultThumbDrawable;
        int i7 = this.thumbRadius;
        materialShapeDrawable.setBounds(0, 0, i7 * 2, i7 * 2);
        Drawable drawable = this.customThumbDrawable;
        if (drawable != null) {
            adjustCustomThumbDrawableBounds(drawable);
        }
        Iterator<Drawable> it = this.customThumbDrawablesForValues.iterator();
        while (it.hasNext()) {
            adjustCustomThumbDrawableBounds(it.next());
        }
        updateWidgetLayout();
    }

    public void setThumbRadiusResource(int i5) {
        setThumbRadius(getResources().getDimensionPixelSize(i5));
    }

    public void setThumbStrokeColor(ColorStateList colorStateList) {
        this.defaultThumbDrawable.setStrokeColor(colorStateList);
        postInvalidate();
    }

    public void setThumbStrokeColorResource(int i5) {
        if (i5 != 0) {
            setThumbStrokeColor(a.f(getContext(), i5));
        }
    }

    public void setThumbStrokeWidth(float f2) {
        this.defaultThumbDrawable.setStrokeWidth(f2);
        postInvalidate();
    }

    public void setThumbStrokeWidthResource(int i5) {
        if (i5 != 0) {
            setThumbStrokeWidth(getResources().getDimension(i5));
        }
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.defaultThumbDrawable.getFillColor())) {
            return;
        }
        this.defaultThumbDrawable.setFillColor(colorStateList);
        invalidate();
    }

    public void setTickActiveRadius(int i5) {
        if (this.tickActiveRadius != i5) {
            this.tickActiveRadius = i5;
            this.activeTicksPaint.setStrokeWidth(i5 * 2);
            updateWidgetLayout();
        }
    }

    public void setTickActiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.tickColorActive)) {
            return;
        }
        this.tickColorActive = colorStateList;
        this.activeTicksPaint.setColor(getColorForState(colorStateList));
        invalidate();
    }

    public void setTickInactiveRadius(int i5) {
        if (this.tickInactiveRadius != i5) {
            this.tickInactiveRadius = i5;
            this.inactiveTicksPaint.setStrokeWidth(i5 * 2);
            updateWidgetLayout();
        }
    }

    public void setTickInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.tickColorInactive)) {
            return;
        }
        this.tickColorInactive = colorStateList;
        this.inactiveTicksPaint.setColor(getColorForState(colorStateList));
        invalidate();
    }

    public void setTickTintList(ColorStateList colorStateList) {
        setTickInactiveTintList(colorStateList);
        setTickActiveTintList(colorStateList);
    }

    public void setTickVisible(boolean z9) {
        if (this.tickVisible != z9) {
            this.tickVisible = z9;
            postInvalidate();
        }
    }

    public void setTrackActiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.trackColorActive)) {
            return;
        }
        this.trackColorActive = colorStateList;
        this.activeTrackPaint.setColor(getColorForState(colorStateList));
        invalidate();
    }

    public void setTrackHeight(int i5) {
        if (this.trackHeight != i5) {
            this.trackHeight = i5;
            invalidateTrack();
            updateWidgetLayout();
        }
    }

    public void setTrackInactiveTintList(ColorStateList colorStateList) {
        if (colorStateList.equals(this.trackColorInactive)) {
            return;
        }
        this.trackColorInactive = colorStateList;
        this.inactiveTrackPaint.setColor(getColorForState(colorStateList));
        invalidate();
    }

    public void setTrackTintList(ColorStateList colorStateList) {
        setTrackInactiveTintList(colorStateList);
        setTrackActiveTintList(colorStateList);
    }

    public void setValueFrom(float f2) {
        this.valueFrom = f2;
        this.dirtyConfig = true;
        postInvalidate();
    }

    public void setValueTo(float f2) {
        this.valueTo = f2;
        this.dirtyConfig = true;
        postInvalidate();
    }

    public void setValues(Float... fArr) {
        ArrayList<Float> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, fArr);
        setValuesInternal(arrayList);
    }

    public void updateBoundsForVirtualViewId(int i5, Rect rect) {
        int iNormalizeValue = this.trackSidePadding + ((int) (normalizeValue(getValues().get(i5).floatValue()) * this.trackWidth));
        int iCalculateTrackCenter = calculateTrackCenter();
        int i7 = this.thumbRadius;
        int i9 = this.minTouchTargetSize;
        if (i7 <= i9) {
            i7 = i9;
        }
        int i10 = i7 / 2;
        rect.set(iNormalizeValue - i10, iCalculateTrackCenter - i10, iNormalizeValue + i10, iCalculateTrackCenter + i10);
    }

    public BaseSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.sliderStyle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float calculateStepIncrement(int i5) {
        float fCalculateStepIncrement = calculateStepIncrement();
        return (this.valueTo - this.valueFrom) / fCalculateStepIncrement <= i5 ? fCalculateStepIncrement : Math.round(r1 / r2) * fCalculateStepIncrement;
    }

    public void setCustomThumbDrawable(Drawable drawable) {
        this.customThumbDrawable = initializeCustomThumbDrawable(drawable);
        this.customThumbDrawablesForValues.clear();
        postInvalidate();
    }

    public BaseSlider(Context context, AttributeSet attributeSet, int i5) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i5, DEF_STYLE_RES), attributeSet, i5);
        this.labels = new ArrayList();
        this.changeListeners = new ArrayList();
        this.touchListeners = new ArrayList();
        this.labelsAreAnimatedIn = false;
        this.thumbIsPressed = false;
        this.values = new ArrayList<>();
        this.activeThumbIdx = -1;
        this.focusedThumbIdx = -1;
        this.stepSize = 0.0f;
        this.tickVisible = true;
        this.isLongPress = false;
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        this.defaultThumbDrawable = materialShapeDrawable;
        this.customThumbDrawablesForValues = Collections.emptyList();
        this.separationUnit = 0;
        Context context2 = getContext();
        Paint paint = new Paint();
        this.inactiveTrackPaint = paint;
        Paint.Style style = Paint.Style.STROKE;
        paint.setStyle(style);
        Paint.Cap cap = Paint.Cap.ROUND;
        paint.setStrokeCap(cap);
        Paint paint2 = new Paint();
        this.activeTrackPaint = paint2;
        paint2.setStyle(style);
        paint2.setStrokeCap(cap);
        Paint paint3 = new Paint(1);
        this.thumbPaint = paint3;
        Paint.Style style2 = Paint.Style.FILL;
        paint3.setStyle(style2);
        paint3.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint paint4 = new Paint(1);
        this.haloPaint = paint4;
        paint4.setStyle(style2);
        Paint paint5 = new Paint();
        this.inactiveTicksPaint = paint5;
        paint5.setStyle(style);
        paint5.setStrokeCap(cap);
        Paint paint6 = new Paint();
        this.activeTicksPaint = paint6;
        paint6.setStyle(style);
        paint6.setStrokeCap(cap);
        loadResources(context2.getResources());
        processAttributes(context2, attributeSet, i5);
        setFocusable(true);
        setClickable(true);
        materialShapeDrawable.setShadowCompatibilityMode(2);
        this.scaledTouchSlop = ViewConfiguration.get(context2).getScaledTouchSlop();
        AccessibilityHelper accessibilityHelper = new AccessibilityHelper(this);
        this.accessibilityHelper = accessibilityHelper;
        W.i(this, accessibilityHelper);
        this.accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
    }

    public void setValues(List<Float> list) {
        setValuesInternal(new ArrayList<>(list));
    }

    public void setCustomThumbDrawablesForValues(Drawable... drawableArr) {
        this.customThumbDrawable = null;
        this.customThumbDrawablesForValues = new ArrayList();
        for (Drawable drawable : drawableArr) {
            this.customThumbDrawablesForValues.add(initializeCustomThumbDrawable(drawable));
        }
        postInvalidate();
    }
}
