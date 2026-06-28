package com.google.android.material.timepicker;

import L.f;
import L.j;
import L.k;
import L.l;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.TextView;
import androidx.core.view.C0210b;
import androidx.core.view.W;
import com.google.android.material.R;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.timepicker.ClockHandView;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
class ClockFaceView extends RadialViewGroup implements ClockHandView.OnRotateListener {
    private static final float EPSILON = 0.001f;
    private static final int INITIAL_CAPACITY = 12;
    private static final String VALUE_PLACEHOLDER = "";
    private final int clockHandPadding;
    private final ClockHandView clockHandView;
    private final int clockSize;
    private float currentHandRotation;
    private final int[] gradientColors;
    private final float[] gradientPositions;
    private final int minimumHeight;
    private final int minimumWidth;
    private final RectF scratch;
    private final Rect scratchLineBounds;
    private final ColorStateList textColor;
    private final SparseArray<TextView> textViewPool;
    private final Rect textViewRect;
    private final C0210b valueAccessibilityDelegate;
    private String[] values;

    public ClockFaceView(Context context) {
        this(context, null);
    }

    private void findIntersectingTextView() {
        RectF currentSelectorBox = this.clockHandView.getCurrentSelectorBox();
        TextView selectedTextView = getSelectedTextView(currentSelectorBox);
        for (int i5 = 0; i5 < this.textViewPool.size(); i5++) {
            TextView textView = this.textViewPool.get(i5);
            if (textView != null) {
                textView.setSelected(textView == selectedTextView);
                textView.getPaint().setShader(getGradientForTextView(currentSelectorBox, textView));
                textView.invalidate();
            }
        }
    }

    private RadialGradient getGradientForTextView(RectF rectF, TextView textView) {
        textView.getHitRect(this.textViewRect);
        this.scratch.set(this.textViewRect);
        textView.getLineBounds(0, this.scratchLineBounds);
        RectF rectF2 = this.scratch;
        Rect rect = this.scratchLineBounds;
        rectF2.inset(rect.left, rect.top);
        if (RectF.intersects(rectF, this.scratch)) {
            return new RadialGradient(rectF.centerX() - this.scratch.left, rectF.centerY() - this.scratch.top, rectF.width() * 0.5f, this.gradientColors, this.gradientPositions, Shader.TileMode.CLAMP);
        }
        return null;
    }

    private TextView getSelectedTextView(RectF rectF) {
        float f2 = Float.MAX_VALUE;
        TextView textView = null;
        for (int i5 = 0; i5 < this.textViewPool.size(); i5++) {
            TextView textView2 = this.textViewPool.get(i5);
            if (textView2 != null) {
                textView2.getHitRect(this.textViewRect);
                this.scratch.set(this.textViewRect);
                this.scratch.union(rectF);
                float fHeight = this.scratch.height() * this.scratch.width();
                if (fHeight < f2) {
                    textView = textView2;
                    f2 = fHeight;
                }
            }
        }
        return textView;
    }

    private static float max3(float f2, float f7, float f9) {
        return Math.max(Math.max(f2, f7), f9);
    }

    private void updateTextViews(int i5) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        int size = this.textViewPool.size();
        boolean z9 = false;
        for (int i7 = 0; i7 < Math.max(this.values.length, size); i7++) {
            TextView textView = this.textViewPool.get(i7);
            if (i7 >= this.values.length) {
                removeView(textView);
                this.textViewPool.remove(i7);
            } else {
                if (textView == null) {
                    textView = (TextView) layoutInflaterFrom.inflate(R.layout.material_clockface_textview, (ViewGroup) this, false);
                    this.textViewPool.put(i7, textView);
                    addView(textView);
                }
                textView.setText(this.values[i7]);
                textView.setTag(R.id.material_value_index, Integer.valueOf(i7));
                int i9 = (i7 / 12) + 1;
                textView.setTag(R.id.material_clock_level, Integer.valueOf(i9));
                if (i9 > 1) {
                    z9 = true;
                }
                W.i(textView, this.valueAccessibilityDelegate);
                textView.setTextColor(this.textColor);
                if (i5 != 0) {
                    textView.setContentDescription(getResources().getString(i5, this.values[i7]));
                }
            }
        }
        this.clockHandView.setMultiLevel(z9);
    }

    public int getCurrentLevel() {
        return this.clockHandView.getCurrentLevel();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) j.a(1, this.values.length, 1, false).f1791a);
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        super.onLayout(z9, i5, i7, i9, i10);
        findIntersectingTextView();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.View
    public void onMeasure(int i5, int i7) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int iMax3 = (int) (this.clockSize / max3(this.minimumHeight / displayMetrics.heightPixels, this.minimumWidth / displayMetrics.widthPixels, 1.0f));
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMax3, 1073741824);
        setMeasuredDimension(iMax3, iMax3);
        super.onMeasure(iMakeMeasureSpec, iMakeMeasureSpec);
    }

    @Override // com.google.android.material.timepicker.ClockHandView.OnRotateListener
    public void onRotate(float f2, boolean z9) {
        if (Math.abs(this.currentHandRotation - f2) > EPSILON) {
            this.currentHandRotation = f2;
            findIntersectingTextView();
        }
    }

    public void setCurrentLevel(int i5) {
        this.clockHandView.setCurrentLevel(i5);
    }

    public void setHandRotation(float f2) {
        this.clockHandView.setHandRotation(f2);
        findIntersectingTextView();
    }

    @Override // com.google.android.material.timepicker.RadialViewGroup
    public void setRadius(int i5) {
        if (i5 != getRadius()) {
            super.setRadius(i5);
            this.clockHandView.setCircleRadius(getRadius());
        }
    }

    public void setValues(String[] strArr, int i5) {
        this.values = strArr;
        updateTextViews(i5);
    }

    @Override // com.google.android.material.timepicker.RadialViewGroup
    public void updateLayoutParams() {
        super.updateLayoutParams();
        for (int i5 = 0; i5 < this.textViewPool.size(); i5++) {
            this.textViewPool.get(i5).setVisibility(0);
        }
    }

    public ClockFaceView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialClockStyle);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public ClockFaceView(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5);
        this.textViewRect = new Rect();
        this.scratch = new RectF();
        this.scratchLineBounds = new Rect();
        this.textViewPool = new SparseArray<>();
        this.gradientPositions = new float[]{0.0f, 0.9f, 1.0f};
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ClockFaceView, i5, R.style.Widget_MaterialComponents_TimePicker_Clock);
        Resources resources = getResources();
        ColorStateList colorStateList = MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes, R.styleable.ClockFaceView_clockNumberTextColor);
        this.textColor = colorStateList;
        LayoutInflater.from(context).inflate(R.layout.material_clockface_view, (ViewGroup) this, true);
        ClockHandView clockHandView = (ClockHandView) findViewById(R.id.material_clock_hand);
        this.clockHandView = clockHandView;
        this.clockHandPadding = resources.getDimensionPixelSize(R.dimen.material_clock_hand_padding);
        int colorForState = colorStateList.getColorForState(new int[]{android.R.attr.state_selected}, colorStateList.getDefaultColor());
        this.gradientColors = new int[]{colorForState, colorForState, colorStateList.getDefaultColor()};
        clockHandView.addOnRotateListener(this);
        int defaultColor = p0.a.f(context, R.color.material_timepicker_clockface).getDefaultColor();
        ColorStateList colorStateList2 = MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes, R.styleable.ClockFaceView_clockFaceBackgroundColor);
        setBackgroundColor(colorStateList2 != null ? colorStateList2.getDefaultColor() : defaultColor);
        getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.google.android.material.timepicker.ClockFaceView.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                if (!ClockFaceView.this.isShown()) {
                    return true;
                }
                ClockFaceView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                ClockFaceView.this.setRadius(((ClockFaceView.this.getHeight() / 2) - ClockFaceView.this.clockHandView.getSelectorRadius()) - ClockFaceView.this.clockHandPadding);
                return true;
            }
        });
        setFocusable(true);
        typedArrayObtainStyledAttributes.recycle();
        this.valueAccessibilityDelegate = new C0210b() { // from class: com.google.android.material.timepicker.ClockFaceView.2
            @Override // androidx.core.view.C0210b
            public void onInitializeAccessibilityNodeInfo(View view, l lVar) {
                super.onInitializeAccessibilityNodeInfo(view, lVar);
                int iIntValue = ((Integer) view.getTag(R.id.material_value_index)).intValue();
                if (iIntValue > 0) {
                    lVar.f1793a.setTraversalAfter((View) ClockFaceView.this.textViewPool.get(iIntValue - 1));
                }
                lVar.l(k.a(0, 1, iIntValue, 1, false, view.isSelected()));
                lVar.k(true);
                lVar.b(f.f1779e);
            }

            @Override // androidx.core.view.C0210b
            public boolean performAccessibilityAction(View view, int i7, Bundle bundle) {
                if (i7 != 16) {
                    return super.performAccessibilityAction(view, i7, bundle);
                }
                long jUptimeMillis = SystemClock.uptimeMillis();
                view.getHitRect(ClockFaceView.this.textViewRect);
                float fCenterX = ClockFaceView.this.textViewRect.centerX();
                float fCenterY = ClockFaceView.this.textViewRect.centerY();
                ClockFaceView.this.clockHandView.onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 0, fCenterX, fCenterY, 0));
                ClockFaceView.this.clockHandView.onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 1, fCenterX, fCenterY, 0));
                return true;
            }
        };
        String[] strArr = new String[12];
        Arrays.fill(strArr, "");
        setValues(strArr, 0);
        this.minimumHeight = resources.getDimensionPixelSize(R.dimen.material_time_picker_minimum_screen_height);
        this.minimumWidth = resources.getDimensionPixelSize(R.dimen.material_time_picker_minimum_screen_width);
        this.clockSize = resources.getDimensionPixelSize(R.dimen.material_clock_size);
    }
}
