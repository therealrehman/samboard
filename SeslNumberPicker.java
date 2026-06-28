package androidx.picker.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.provider.Settings;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.OverScroller;
import android.widget.Scroller;
import android.widget.TextView;
import com.samsung.android.keyscafe.R;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SeslNumberPicker extends LinearLayout {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final H f8208f = new H();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final P f8209e;

    public static class CustomEditText extends EditText {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public String f8210e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f8211f;

        public CustomEditText(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            Method methodX = com.bumptech.glide.c.x(TextView.class, "setLocalePreferredLineHeightForMinimumUsed", Boolean.TYPE);
            if (methodX != null) {
                com.bumptech.glide.c.C(this, methodX, Boolean.FALSE);
            }
            this.f8210e = "";
        }

        @Override // android.widget.TextView, android.view.View
        public final void onDraw(Canvas canvas) {
            canvas.translate(0.0f, this.f8211f);
            super.onDraw(canvas);
        }

        @Override // android.widget.TextView
        public final void onEditorAction(int i5) {
            super.onEditorAction(i5);
            if (i5 == 6) {
                clearFocus();
            }
        }

        @Override // android.view.View
        public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            Object objC;
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            AccessibilityManager accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
            Method methodU = com.bumptech.glide.c.u("android.view.accessibility.AccessibilityManager", "semIsScreenReaderEnabled", new Class[0]);
            if ((methodU == null || (objC = com.bumptech.glide.c.C(accessibilityManager, methodU, new Object[0])) == null) ? true : ((Boolean) objC).booleanValue()) {
                accessibilityNodeInfo.setText(getText());
                accessibilityNodeInfo.setTooltipText(this.f8210e);
                return;
            }
            CharSequence text = getText();
            if (!this.f8210e.equals("")) {
                if (TextUtils.isEmpty(text)) {
                    text = ", " + this.f8210e;
                } else {
                    text = text.toString() + ", " + this.f8210e;
                }
            }
            accessibilityNodeInfo.setText(text);
        }

        @Override // android.view.View
        public final void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            int size = accessibilityEvent.getText().size();
            super.onPopulateAccessibilityEvent(accessibilityEvent);
            int size2 = accessibilityEvent.getText().size();
            if (size2 > size) {
                accessibilityEvent.getText().remove(size2 - 1);
            }
            Editable text = getText();
            if (!TextUtils.isEmpty(text)) {
                accessibilityEvent.getText().add(text);
            }
            accessibilityEvent.setContentDescription(this.f8210e);
        }
    }

    public SeslNumberPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 0);
        this.f8209e = new P(this, context, attributeSet);
    }

    public static D getTwoDigitFormatter() {
        return f8208f;
    }

    public final void a() {
        EditText editText = this.f8209e.f7980e;
        editText.setImeOptions(33554432);
        editText.setPrivateImeOptions("inputType=YearDateTime_edittext");
        editText.setText("");
    }

    @Override // android.view.View
    public final void computeScroll() {
        P p4 = this.f8209e;
        if (p4.f7960T0) {
            return;
        }
        Scroller scroller = p4.f7940D;
        if (scroller.isFinished()) {
            scroller = p4.f7943F;
            if (scroller.isFinished()) {
                return;
            }
        }
        scroller.computeScrollOffset();
        int currY = scroller.getCurrY();
        if (p4.G == 0) {
            p4.G = scroller.getStartY();
        }
        p4.t(currY - p4.G);
        p4.G = currY;
        if (!scroller.isFinished()) {
            ((SeslNumberPicker) p4.f8272b).invalidate();
            return;
        }
        if (scroller == p4.f7940D) {
            if (!p4.e(0)) {
                p4.B();
            }
            p4.p(0);
        } else if (p4.f7959T != 1) {
            p4.B();
        }
    }

    @Override // android.view.View
    public final int computeVerticalScrollExtent() {
        return ((SeslNumberPicker) this.f8209e.f8272b).getHeight();
    }

    @Override // android.view.View
    public final int computeVerticalScrollOffset() {
        return this.f8209e.f7938C;
    }

    @Override // android.view.View
    public final int computeVerticalScrollRange() {
        P p4 = this.f8209e;
        return ((p4.f7996n - p4.f7994m) + 1) * p4.f7934A;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        int i5;
        P p4 = this.f8209e;
        if (p4.m()) {
            return super.dispatchHoverEvent(motionEvent);
        }
        if (!p4.f7970Y0.isEnabled()) {
            return false;
        }
        int y4 = (int) motionEvent.getY();
        int i7 = 2;
        if (!p4.f7986h0) {
            if (y4 <= p4.f7967X) {
                i7 = 1;
            } else if (p4.f7969Y <= y4) {
                i7 = 3;
            }
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 7 || actionMasked == 9) {
            int i9 = p4.f7971Z;
            if (i9 != i7) {
                p4.f7971Z = i7;
                M mG = p4.g();
                mG.j(i7, 128);
                mG.j(i9, 256);
            }
            if (i7 == Integer.MIN_VALUE) {
                return false;
            }
        } else {
            if (actionMasked != 10 || (i5 = p4.f7971Z) == Integer.MIN_VALUE) {
                return false;
            }
            if (i5 != Integer.MIN_VALUE) {
                p4.f7971Z = Integer.MIN_VALUE;
                M mG2 = p4.g();
                mG2.j(Integer.MIN_VALUE, 128);
                mG2.j(i5, 256);
            }
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        P p4 = this.f8209e;
        p4.getClass();
        int action = keyEvent.getAction();
        int keyCode = keyEvent.getKeyCode();
        if (keyCode != 66 && keyCode != 160) {
            SeslNumberPicker seslNumberPicker = (SeslNumberPicker) p4.f8272b;
            switch (keyCode) {
                case 19:
                case 20:
                    if (!p4.f7986h0) {
                        if (action == 0) {
                            if (keyCode == 20) {
                                int i5 = p4.f7977c0;
                                if (i5 == 1) {
                                    p4.f7977c0 = 2;
                                    seslNumberPicker.invalidate();
                                } else if (i5 == 2 && (p4.f7956Q || p4.o != p4.f7996n)) {
                                    p4.f7977c0 = 3;
                                    seslNumberPicker.invalidate();
                                }
                                return true;
                            }
                            if (keyCode == 19) {
                                int i7 = p4.f7977c0;
                                if (i7 != 2) {
                                    if (i7 == 3) {
                                        p4.f7977c0 = 2;
                                        seslNumberPicker.invalidate();
                                        return true;
                                    }
                                } else if (p4.f7956Q || p4.o != p4.f7994m) {
                                    p4.f7977c0 = 1;
                                    seslNumberPicker.invalidate();
                                    return true;
                                }
                            }
                        } else if (action == 1 && p4.f7970Y0.isEnabled()) {
                            M mG = p4.g();
                            if (mG != null) {
                                mG.performAction(p4.f7977c0, 64, null);
                            }
                            return true;
                        }
                    }
                    break;
                case 21:
                case 22:
                    if (action == 0) {
                        if (keyCode == 21) {
                            View viewFocusSearch = seslNumberPicker.focusSearch(17);
                            if (viewFocusSearch != null) {
                                viewFocusSearch.requestFocus(17);
                            }
                        } else if (keyCode == 22) {
                            View viewFocusSearch2 = seslNumberPicker.focusSearch(66);
                            if (viewFocusSearch2 != null) {
                                viewFocusSearch2.requestFocus(66);
                            }
                        }
                        return true;
                    }
                    break;
            }
            return super.dispatchKeyEvent(keyEvent);
        }
        if (!p4.f7986h0 && action == 1) {
            if (p4.f7977c0 == 2) {
                if (p4.f7985g0) {
                    EditText editText = p4.f7980e;
                    editText.setVisibility(0);
                    editText.requestFocus();
                    p4.x();
                    p4.s();
                    return true;
                }
            } else if (p4.f7940D.isFinished()) {
                int i9 = p4.f7977c0;
                if (i9 == 1) {
                    p4.y(false);
                    p4.c(false);
                    if (!p4.f7956Q && p4.o == p4.f7994m + 1) {
                        p4.f7977c0 = 2;
                    }
                    p4.y(true);
                } else if (i9 == 3) {
                    p4.y(false);
                    p4.c(true);
                    if (!p4.f7956Q && p4.o == p4.f7996n - 1) {
                        p4.f7977c0 = 2;
                    }
                    p4.y(true);
                }
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEventPreIme(KeyEvent keyEvent) {
        P p4 = this.f8209e;
        if (p4.f7985g0) {
            EditText editText = p4.f7980e;
            boolean zHasFocus = editText.hasFocus();
            SeslNumberPicker seslNumberPicker = (SeslNumberPicker) p4.f8272b;
            if ((zHasFocus || (!p4.f7985g0 && seslNumberPicker.hasFocus())) && keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 0) {
                p4.f8003r = true;
                InputMethodManager inputMethodManager = (InputMethodManager) p4.f8271a.getSystemService("input_method");
                if (inputMethodManager != null && inputMethodManager.isActive(editText)) {
                    inputMethodManager.hideSoftInputFromWindow(seslNumberPicker.getWindowToken(), 0);
                    editText.setVisibility(4);
                }
                p4.u(false);
                return true;
            }
            p4.f8003r = false;
        }
        return super.dispatchKeyEventPreIme(keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        P p4 = this.f8209e;
        p4.getClass();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3) {
            p4.s();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        P p4 = this.f8209e;
        p4.getClass();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3) {
            p4.s();
        }
        return super.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.View
    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        P p4 = this.f8209e;
        return p4.m() ? super.getAccessibilityNodeProvider() : p4.g();
    }

    public String[] getDisplayedValues() {
        return this.f8209e.f7992l;
    }

    public EditText getEditText() {
        return this.f8209e.f7980e;
    }

    public int[] getEnableStateSet() {
        return LinearLayout.ENABLED_STATE_SET;
    }

    public int getMaxValue() {
        return this.f8209e.f7996n;
    }

    public int getMinValue() {
        return this.f8209e.f7994m;
    }

    public int getPaintFlags() {
        return this.f8209e.f8017y.getFlags();
    }

    public int getValue() {
        return this.f8209e.o;
    }

    public boolean getWrapSelectorWheel() {
        return this.f8209e.f7956Q;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        P p4 = this.f8209e;
        ((SeslNumberPicker) p4.f8272b).getViewTreeObserver().addOnPreDrawListener(p4.j0);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        P p4 = this.f8209e;
        boolean z9 = p4.f8020z0;
        boolean z10 = Settings.Global.getInt(p4.f8271a.getContentResolver(), "bold_text", 0) != 0;
        p4.f8020z0 = z10;
        if (z9 != z10) {
            p4.f8017y.setFakeBoldText(z10);
        }
        if (p4.f8014w0) {
            return;
        }
        boolean zL = P.l();
        EditText editText = p4.f7980e;
        if (!zL) {
            editText.setIncludeFontPadding(false);
            p4.v();
            p4.A();
        } else {
            editText.setIncludeFontPadding(true);
            Typeface typeface = p4.D0;
            p4.f7935A0 = typeface;
            p4.f7937B0 = Typeface.create(typeface, 0);
            p4.f7939C0 = Typeface.create(p4.f7935A0, 1);
            p4.v();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        P p4 = this.f8209e;
        p4.f7941E.abortAnimation();
        p4.S0.b();
        p4.f7960T0 = false;
        p4.s();
        ((SeslNumberPicker) p4.f8272b).getViewTreeObserver().removeOnPreDrawListener(p4.j0);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x014c  */
    @Override // android.widget.LinearLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onDraw(android.graphics.Canvas r18) {
        /*
            Method dump skipped, instruction units count: 365
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.picker.widget.SeslNumberPicker.onDraw(android.graphics.Canvas):void");
    }

    @Override // android.view.View
    public final void onFocusChanged(boolean z9, int i5, Rect rect) {
        M mG;
        M mG2;
        P p4 = this.f8209e;
        AccessibilityManager accessibilityManager = p4.f7970Y0;
        if (z9) {
            if (p4.f7986h0) {
                p4.f7977c0 = -1;
                EditText editText = p4.f7980e;
                if (editText.getVisibility() == 0) {
                    editText.requestFocus();
                }
            } else {
                p4.f7977c0 = 1;
                if (!p4.f7956Q && p4.o == p4.f7994m) {
                    p4.f7977c0 = 2;
                }
            }
            if (accessibilityManager.isEnabled() && (mG = p4.g()) != null) {
                if (p4.f7986h0) {
                    p4.f7977c0 = 2;
                }
                mG.performAction(p4.f7977c0, 64, null);
            }
        } else {
            if (accessibilityManager.isEnabled() && (mG2 = p4.g()) != null) {
                if (p4.f7986h0) {
                    p4.f7977c0 = 2;
                }
                mG2.performAction(p4.f7977c0, 128, null);
            }
            p4.f7977c0 = -1;
            p4.f7971Z = Integer.MIN_VALUE;
        }
        ((SeslNumberPicker) p4.f8272b).invalidate();
        super.onFocusChanged(z9, i5, rect);
    }

    @Override // android.view.View
    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        P p4 = this.f8209e;
        if (((SeslNumberPicker) p4.f8272b).isEnabled() && !p4.f7986h0 && !p4.f7997n0 && (motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8) {
            float axisValue = motionEvent.getAxisValue(9);
            if (axisValue != 0.0f) {
                p4.y(false);
                p4.c(axisValue < 0.0f);
                p4.y(true);
                return true;
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        P p4 = this.f8209e;
        p4.getClass();
        accessibilityEvent.setClassName(NumberPicker.class.getName());
        accessibilityEvent.setScrollable(true);
        accessibilityEvent.setScrollY((p4.f7994m + p4.o) * p4.f7934A);
        accessibilityEvent.setMaxScrollY((p4.f7996n - p4.f7994m) * p4.f7934A);
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        P p4 = this.f8209e;
        SeslNumberPicker seslNumberPicker = (SeslNumberPicker) p4.f8272b;
        if (!seslNumberPicker.isEnabled() || p4.f7986h0 || p4.f7997n0 || motionEvent.getActionMasked() != 0) {
            return false;
        }
        p4.s();
        p4.f7980e.setVisibility(4);
        float y4 = motionEvent.getY();
        p4.f7950K = y4;
        p4.f7951L = y4;
        motionEvent.getEventTime();
        p4.f7961U = false;
        p4.f7963V = false;
        p4.f7965W = false;
        p4.f7947H0 = false;
        float f2 = p4.f7950K;
        float f7 = p4.f7967X;
        c0 c0Var = p4.f7981e0;
        if (f2 < f7) {
            p4.y(false);
            if (p4.f7959T == 0) {
                c0Var.a();
                c0Var.f8290g = 1;
                c0Var.f8289f = 2;
                ((SeslNumberPicker) ((P) c0Var.h).f8272b).postDelayed(c0Var, ViewConfiguration.getTapTimeout());
            }
        } else if (f2 > p4.f7969Y) {
            p4.y(false);
            if (p4.f7959T == 0) {
                c0Var.a();
                c0Var.f8290g = 1;
                c0Var.f8289f = 1;
                ((SeslNumberPicker) ((P) c0Var.h).f8272b).postDelayed(c0Var, ViewConfiguration.getTapTimeout());
            }
        }
        seslNumberPicker.getParent().requestDisallowInterceptTouchEvent(true);
        boolean zIsFinished = p4.f7940D.isFinished();
        Scroller scroller = p4.f7943F;
        if (zIsFinished) {
            androidx.dynamicanimation.animation.h hVar = p4.S0;
            if (hVar.f7419f) {
                OverScroller overScroller = p4.f7941E;
                overScroller.forceFinished(true);
                scroller.forceFinished(true);
                hVar.b();
                p4.f7960T0 = false;
                if (p4.f7959T == 2) {
                    overScroller.abortAnimation();
                    scroller.abortAnimation();
                }
                p4.p(0);
            } else if (scroller.isFinished()) {
                float f9 = p4.f7950K;
                if (f9 < p4.f7967X) {
                    if (p4.f7999p != 1) {
                        p4.r();
                    }
                } else if (f9 <= p4.f7969Y) {
                    p4.f7965W = true;
                    if (p4.f7999p != 1) {
                        p4.r();
                    } else {
                        J j5 = p4.f7949J;
                        if (j5 == null) {
                            p4.f7949J = new J(p4, 1);
                        } else {
                            seslNumberPicker.removeCallbacks(j5);
                        }
                        seslNumberPicker.postDelayed(p4.f7949J, ViewConfiguration.getLongPressTimeout());
                    }
                } else if (p4.f7999p != 1) {
                    p4.r();
                }
            } else {
                p4.f7940D.forceFinished(true);
                scroller.forceFinished(true);
            }
        } else {
            p4.f7940D.forceFinished(true);
            scroller.forceFinished(true);
            if (p4.f7959T == 2) {
                p4.f7940D.abortAnimation();
                scroller.abortAnimation();
            }
            p4.p(0);
        }
        return true;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        P p4 = this.f8209e;
        SeslNumberPicker seslNumberPicker = (SeslNumberPicker) p4.f8272b;
        int measuredWidth = seslNumberPicker.getMeasuredWidth();
        int measuredHeight = seslNumberPicker.getMeasuredHeight();
        EditText editText = p4.f7980e;
        int measuredWidth2 = editText.getMeasuredWidth();
        int iMax = Math.max(editText.getMeasuredHeight(), (int) Math.floor(measuredHeight * p4.f7942E0));
        p4.f7944F0 = iMax;
        int i11 = (measuredWidth - measuredWidth2) / 2;
        int i12 = (measuredHeight - iMax) / 2;
        int i13 = iMax + i12;
        editText.layout(i11, i12, measuredWidth2 + i11, i13);
        if (z9) {
            if (p4.f7997n0) {
                if (!p4.o(p4.f7940D)) {
                    p4.o(p4.f7943F);
                }
                p4.z();
            } else {
                p4.k();
            }
            int bottom = p4.f7990k + ((int) ((((seslNumberPicker.getBottom() - seslNumberPicker.getTop()) - (p4.f7990k * 3)) / 3) + 0.5f));
            p4.f7934A = bottom;
            int height = p4.f7944F0;
            if (height > bottom || p4.f7983f0) {
                height = seslNumberPicker.getHeight() / 3;
            }
            p4.f7945G0 = height;
            int top = ((p4.f7944F0 / 2) + editText.getTop()) - p4.f7934A;
            p4.f7936B = top;
            p4.f7938C = top;
            Paint paint = p4.f8017y;
            ((CustomEditText) editText).f8211f = ((int) (((paint.descent() - paint.ascent()) / 2.0f) - paint.descent())) - (editText.getBaseline() - (p4.f7944F0 / 2));
            if (p4.f7998o0) {
                if (!p4.f7986h0 && (p4.f7983f0 || p4.f7956Q || p4.o - p4.f7994m != 0)) {
                    ValueAnimator valueAnimator = p4.P0;
                    if (valueAnimator.isStarted()) {
                        valueAnimator.cancel();
                    }
                    ValueAnimator valueAnimator2 = p4.O0;
                    if (valueAnimator2.isStarted()) {
                        valueAnimator2.cancel();
                    }
                    ValueAnimator valueAnimator3 = p4.Q0;
                    if (valueAnimator3.isStarted()) {
                        valueAnimator3.cancel();
                    }
                    ValueAnimator valueAnimator4 = p4.R0;
                    if (valueAnimator4.isStarted()) {
                        valueAnimator4.cancel();
                    }
                    seslNumberPicker.post(new Y(2, p4));
                }
                p4.f7998o0 = false;
            }
            if (p4.f7944F0 <= p4.f7934A) {
                p4.f7967X = i12;
                p4.f7969Y = i13;
            } else {
                int i14 = p4.f7945G0;
                p4.f7967X = i14;
                p4.f7969Y = i14 * 2;
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i5, int i7) {
        P p4 = this.f8209e;
        int iN = P.n(i5, p4.f7987i);
        int iN2 = P.n(i7, p4.f7984g);
        SeslNumberPicker seslNumberPicker = (SeslNumberPicker) p4.f8272b;
        super.onMeasure(iN, iN2);
        int measuredWidth = seslNumberPicker.getMeasuredWidth();
        int i9 = p4.h;
        if (i9 != -1) {
            measuredWidth = View.resolveSizeAndState(Math.max(i9, measuredWidth), i5, 0);
        }
        int measuredHeight = seslNumberPicker.getMeasuredHeight();
        int i10 = p4.f7982f;
        if (i10 != -1) {
            measuredHeight = View.resolveSizeAndState(Math.max(i10, measuredHeight), i7, 0);
        }
        seslNumberPicker.setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override // android.view.View
    public final void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        P p4 = this.f8209e;
        p4.getClass();
        List<CharSequence> text = accessibilityEvent.getText();
        M mG = p4.g();
        int i5 = M.f7924g;
        text.add(mG.d(true));
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        P p4 = this.f8209e;
        SeslNumberPicker seslNumberPicker = (SeslNumberPicker) p4.f8272b;
        if (!seslNumberPicker.isEnabled() || p4.f7986h0 || p4.f7997n0) {
            return false;
        }
        if (p4.f7952M == null) {
            p4.f7952M = VelocityTracker.obtain();
        }
        p4.f7952M.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int i5 = p4.f7953N;
        if (actionMasked == 1) {
            J j5 = p4.f7949J;
            if (j5 != null) {
                seslNumberPicker.removeCallbacks(j5);
            }
            J j9 = p4.f7948I;
            if (j9 != null) {
                seslNumberPicker.removeCallbacks(j9);
            }
            if (!p4.f7963V) {
                c0 c0Var = p4.f7981e0;
                c0Var.a();
                VelocityTracker velocityTracker = p4.f7952M;
                velocityTracker.computeCurrentVelocity(1000, p4.f7955P);
                int yVelocity = (int) velocityTracker.getYVelocity();
                int y4 = (int) motionEvent.getY();
                int iAbs = (int) Math.abs(y4 - p4.f7950K);
                if (!p4.f7985g0 && p4.f7961U) {
                    p4.e(0);
                    p4.y(true);
                    p4.p(0);
                } else if (Math.abs(yVelocity) <= p4.f7954O || Math.abs(yVelocity) <= p4.f7964V0) {
                    if (iAbs > i5) {
                        if (p4.f7995m0) {
                            p4.x();
                            p4.f7995m0 = false;
                        }
                        p4.e(iAbs);
                        p4.y(true);
                    } else if (p4.f7965W) {
                        p4.f7965W = false;
                        if (p4.f7985g0) {
                            p4.x();
                        }
                    } else {
                        int i7 = p4.f7969Y;
                        P p9 = (P) c0Var.h;
                        if (y4 > i7) {
                            p4.c(true);
                            c0Var.a();
                            c0Var.f8290g = 2;
                            c0Var.f8289f = 1;
                            ((SeslNumberPicker) p9.f8272b).post(c0Var);
                        } else if (y4 < p4.f7967X) {
                            p4.c(false);
                            c0Var.a();
                            c0Var.f8290g = 2;
                            c0Var.f8289f = 2;
                            ((SeslNumberPicker) p9.f8272b).post(c0Var);
                        } else {
                            p4.e(iAbs);
                        }
                        p4.y(true);
                    }
                    p4.f7947H0 = false;
                    p4.p(0);
                } else if (iAbs > i5 || !p4.f7965W) {
                    boolean z9 = p4.f7956Q;
                    if (!z9 && yVelocity > 0 && p4.o == p4.f7994m) {
                        p4.y(true);
                    } else if (z9 || yVelocity >= 0 || p4.o != p4.f7996n) {
                        p4.G = 0;
                        Math.abs(yVelocity);
                        p4.f7946H = p4.f7938C;
                        androidx.dynamicanimation.animation.h hVar = p4.S0;
                        hVar.f7414a = yVelocity;
                        OverScroller overScroller = p4.f7941E;
                        overScroller.forceFinished(true);
                        overScroller.fling(0, p4.f7938C, 0, yVelocity, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
                        int iRound = Math.round((overScroller.getFinalY() + p4.f7938C) / p4.f7934A);
                        int i9 = p4.f7934A;
                        int i10 = p4.f7936B;
                        int i11 = (iRound * i9) + i10;
                        float fMax = yVelocity > 0 ? Math.max(i11, i9 + i10) : Math.min(i11, (-i9) + i10);
                        hVar.f7415b = p4.f7938C;
                        hVar.f7416c = true;
                        p4.f7960T0 = true;
                        hVar.e(fMax);
                        seslNumberPicker.invalidate();
                    } else {
                        p4.y(true);
                    }
                    p4.p(2);
                } else {
                    p4.f7965W = false;
                    if (p4.f7985g0) {
                        p4.x();
                    }
                    p4.p(0);
                }
                p4.f7952M.recycle();
                p4.f7952M = null;
            }
        } else if (actionMasked != 2) {
            if (actionMasked == 3) {
                p4.e(0);
                p4.y(true);
                p4.p(0);
            }
        } else if (!p4.f7961U) {
            float y9 = motionEvent.getY();
            if (p4.f7959T == 1) {
                p4.t((int) (y9 - p4.f7951L));
                seslNumberPicker.invalidate();
            } else if (((int) Math.abs(y9 - p4.f7950K)) > i5) {
                p4.s();
                p4.y(false);
                p4.p(1);
            }
            p4.f7951L = y9;
        }
        return true;
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z9) {
        InputMethodManager inputMethodManager;
        super.onWindowFocusChanged(z9);
        P p4 = this.f8209e;
        SeslNumberPicker seslNumberPicker = (SeslNumberPicker) p4.f8272b;
        EditText editText = p4.f7980e;
        if (z9 && p4.f7986h0 && editText.isFocused()) {
            seslNumberPicker.postDelayed(new J(p4, 0), 20L);
        } else if (z9 && p4.f7986h0 && !editText.isFocused() && (inputMethodManager = (InputMethodManager) p4.f8271a.getSystemService("input_method")) != null && inputMethodManager.isActive(editText)) {
            inputMethodManager.hideSoftInputFromWindow(seslNumberPicker.getWindowToken(), 0);
        }
        if (!p4.f7997n0) {
            if (!p4.f7940D.isFinished()) {
                p4.f7940D.forceFinished(true);
            }
            Scroller scroller = p4.f7943F;
            if (!scroller.isFinished()) {
                scroller.forceFinished(true);
            }
            OverScroller overScroller = p4.f7941E;
            if (!overScroller.isFinished()) {
                overScroller.forceFinished(true);
            }
            androidx.dynamicanimation.animation.h hVar = p4.S0;
            if (hVar.f7419f) {
                hVar.b();
                p4.f7960T0 = false;
            }
            p4.e(0);
        }
        p4.f8018y0 = com.bumptech.glide.d.r(editText);
        Paint paint = p4.f8017y;
        paint.setTextSize(p4.f7990k);
        paint.setTypeface(p4.f7935A0);
        p4.v();
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i5) {
        this.f8209e.getClass();
        super.onWindowVisibilityChanged(i5);
    }

    @Override // android.view.View
    public final boolean performClick() {
        P p4 = this.f8209e;
        if (p4.m()) {
            return super.performClick();
        }
        if (super.performClick() || !p4.f7985g0) {
            return true;
        }
        p4.x();
        return true;
    }

    @Override // android.view.View
    public final boolean performLongClick() {
        if (!super.performLongClick()) {
            P p4 = this.f8209e;
            p4.f7961U = true;
            if (p4.f7985g0) {
                p4.f7995m0 = true;
            }
        }
        return true;
    }

    @Override // android.view.View
    public final void scrollBy(int i5, int i7) {
        this.f8209e.t(i7);
    }

    public void setCustomIntervalValue(int i5) {
        this.f8209e.f7999p = i5;
    }

    public void setCustomNumberPickerIdleColor(int i5) {
        P p4 = this.f8209e;
        p4.f7980e.setTextColor(i5);
        p4.j(p4.f8271a);
        p4.f8017y.setColor(p4.f8004r0);
        p4.Q0.setIntValues(p4.f8006s0, p4.f8008t0);
        p4.R0.setIntValues(p4.f8008t0, p4.f8006s0);
        ((SeslNumberPicker) p4.f8272b).invalidate();
    }

    public void setCustomNumberPickerScrollColor(int i5) {
        P p4 = this.f8209e;
        p4.f8016x0 = true;
        p4.f8010u0 = i5;
        p4.j(p4.f8271a);
        p4.Q0.setIntValues(p4.f8006s0, p4.f8008t0);
        p4.R0.setIntValues(p4.f8008t0, p4.f8006s0);
        ((SeslNumberPicker) p4.f8272b).invalidate();
    }

    public void setCustomTalkbackFormatter(C c5) {
        this.f8209e.f8011v = c5;
    }

    public void setDateUnit(int i5) {
        P p4 = this.f8209e;
        if (i5 == -1) {
            p4.f7976c = "";
        }
        Context context = p4.f8271a;
        switch (i5) {
            case 997:
                p4.f7976c = context.getResources().getString(R.string.sesl_date_picker_day);
                break;
            case 998:
                p4.f7976c = context.getResources().getString(R.string.sesl_date_picker_month);
                break;
            case androidx.room.k.MAX_BIND_PARAMETER_CNT /* 999 */:
                p4.f7976c = context.getResources().getString(R.string.sesl_date_picker_year);
                break;
        }
    }

    public void setDisplayedValues(String[] strArr) {
        P p4 = this.f8209e;
        if (p4.f7992l == strArr) {
            return;
        }
        p4.f7992l = strArr;
        EditText editText = p4.f7980e;
        if (strArr != null) {
            editText.setRawInputType(524289);
        } else {
            editText.setRawInputType(2);
        }
        p4.B();
        p4.k();
        p4.A();
    }

    public void setEditTextMode(boolean z9) {
        this.f8209e.u(z9);
    }

    public void setEditTextModeEnabled(boolean z9) {
        P p4 = this.f8209e;
        if (p4.f7985g0 == z9 || z9) {
            return;
        }
        if (p4.f7986h0) {
            p4.u(false);
        }
        p4.f7980e.setAccessibilityDelegate(null);
        p4.f7985g0 = z9;
    }

    @Override // android.view.View
    public void setEnabled(boolean z9) {
        super.setEnabled(z9);
        P p4 = this.f8209e;
        p4.f7980e.setEnabled(z9);
        if (z9 || p4.f7959T == 0) {
            return;
        }
        p4.z();
        p4.p(0);
    }

    public void setErrorToastMessage(String str) {
        P p4 = this.f8209e;
        p4.getClass();
        if (TextUtils.isEmpty(str)) {
            return;
        }
        p4.f7966W0 = str;
    }

    public void setFormatter(D d8) {
        P p4 = this.f8209e;
        if (d8 == p4.f8009u) {
            return;
        }
        p4.f8009u = d8;
        p4.k();
        p4.B();
    }

    public void setMaxInputLength(int i5) {
        EditText editText = this.f8209e.f7980e;
        editText.setFilters(new InputFilter[]{editText.getFilters()[0], new InputFilter.LengthFilter(i5)});
    }

    public void setMaxValue(int i5) {
        P p4 = this.f8209e;
        if (p4.f7996n == i5) {
            return;
        }
        if (i5 < 0) {
            throw new IllegalArgumentException("maxValue must be >= 0");
        }
        boolean z9 = p4.f7956Q;
        int i7 = p4.f7999p;
        if (i7 == 1 || ((z9 ? 1 : 0) + i5) % i7 == 0) {
            p4.f7996n = i5;
            if (i5 < p4.o) {
                p4.o = i5;
            }
            p4.C();
            p4.k();
            p4.B();
            p4.A();
            ((SeslNumberPicker) p4.f8272b).invalidate();
        }
    }

    public void setMinValue(int i5) {
        P p4 = this.f8209e;
        if (p4.f7994m == i5) {
            return;
        }
        if (i5 < 0) {
            throw new IllegalArgumentException("minValue must be >= 0");
        }
        int i7 = p4.f7999p;
        if (i7 == 1 || i5 % i7 == 0) {
            p4.f7994m = i5;
            if (i5 > p4.o) {
                p4.o = i5;
            }
            p4.C();
            p4.k();
            p4.B();
            p4.A();
            ((SeslNumberPicker) p4.f8272b).invalidate();
        }
    }

    public void setOnEditTextModeChangedListener(E e3) {
        this.f8209e.f8007t = e3;
    }

    public void setOnLongPressUpdateInterval(long j5) {
    }

    public void setOnScrollListener(F f2) {
        this.f8209e.getClass();
    }

    public void setOnValueChangedListener(G g9) {
        this.f8209e.f8005s = g9;
    }

    public void setPaintFlags(int i5) {
        P p4 = this.f8209e;
        Paint paint = p4.f8017y;
        if (paint.getFlags() != i5) {
            paint.setFlags(i5);
            p4.f7980e.setPaintFlags(i5);
            p4.A();
        }
    }

    public void setPickerContentDescription(String str) {
        P p4 = this.f8209e;
        p4.f7979d = str;
        ((CustomEditText) p4.f7980e).f8210e = str;
    }

    public void setSkipValuesOnLongPressEnabled(boolean z9) {
    }

    public void setSubTextSize(float f2) {
        this.f8209e.getClass();
    }

    public void setSubTextTypeface(Typeface typeface) {
        P p4 = this.f8209e;
        p4.f8014w0 = true;
        p4.f7937B0 = typeface;
        p4.f8017y.setTypeface(p4.f7935A0);
        p4.f7939C0 = Typeface.create(p4.f7935A0, 1);
        p4.v();
        p4.A();
    }

    public void setTextSize(float f2) {
        P p4 = this.f8209e;
        int iApplyDimension = (int) TypedValue.applyDimension(1, f2, p4.f8271a.getResources().getDisplayMetrics());
        p4.f7990k = iApplyDimension;
        p4.f8017y.setTextSize(iApplyDimension);
        p4.f7980e.setTextSize(0, p4.f7990k);
        p4.A();
    }

    public void setTextTypeface(Typeface typeface) {
        P p4 = this.f8209e;
        p4.f8014w0 = true;
        p4.f7935A0 = typeface;
        p4.f7937B0 = Typeface.create(typeface, 0);
        p4.f8017y.setTypeface(p4.f7935A0);
        p4.f7939C0 = Typeface.create(p4.f7935A0, 1);
        p4.v();
        p4.A();
    }

    public void setValue(int i5) {
        P p4 = this.f8209e;
        if (!p4.f7940D.isFinished() || p4.S0.f7419f) {
            p4.z();
        }
        p4.w(i5, false);
    }

    public void setWrapSelectorWheel(boolean z9) {
        P p4 = this.f8209e;
        p4.f7957R = z9;
        p4.C();
    }
}
