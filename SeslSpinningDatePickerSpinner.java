package androidx.picker.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.icu.text.DateFormatSymbols;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.Scroller;
import com.google.android.material.timepicker.TimeModel;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import m7.AbstractC0752G;

/* JADX INFO: loaded from: classes.dex */
class SeslSpinningDatePickerSpinner extends LinearLayout {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C0287g f8214f = new C0287g();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final d0 f8215e;

    public static class CustomEditText extends EditText {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f8216e;

        public CustomEditText(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        @Override // android.widget.TextView, android.view.View
        public final void onDraw(Canvas canvas) {
            canvas.translate(0.0f, this.f8216e);
            super.onDraw(canvas);
        }
    }

    public SeslSpinningDatePickerSpinner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 0);
        this.f8215e = new d0(this, context, attributeSet);
    }

    public static int[] a() {
        return LinearLayout.ENABLED_STATE_SET;
    }

    @Override // android.view.View
    public final void computeScroll() {
        d0 d0Var = this.f8215e;
        if (d0Var.f8304G0) {
            return;
        }
        Scroller scroller = d0Var.f8363w;
        if (scroller.isFinished()) {
            scroller = d0Var.f8367y;
            if (scroller.isFinished()) {
                return;
            }
        }
        scroller.computeScrollOffset();
        int currY = scroller.getCurrY();
        if (d0Var.f8369z == 0) {
            d0Var.f8369z = scroller.getStartY();
        }
        d0Var.o(currY - d0Var.f8369z);
        d0Var.f8369z = currY;
        if (!scroller.isFinished()) {
            ((SeslSpinningDatePickerSpinner) d0Var.f8272b).invalidate();
        } else if (scroller == d0Var.f8363w) {
            d0Var.l(0);
        }
    }

    @Override // android.view.View
    public final int computeVerticalScrollExtent() {
        return ((SeslSpinningDatePickerSpinner) this.f8215e.f8272b).getHeight();
    }

    @Override // android.view.View
    public final int computeVerticalScrollOffset() {
        return this.f8215e.f8361v;
    }

    @Override // android.view.View
    public final int computeVerticalScrollRange() {
        d0 d0Var = this.f8215e;
        d0Var.getClass();
        return (((int) TimeUnit.MILLISECONDS.toDays(d0Var.f8342l.getTimeInMillis() - d0Var.f8340k.getTimeInMillis())) + 1) * d0Var.f8357t;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        int i5;
        d0 d0Var = this.f8215e;
        if (!d0Var.f8306H0.isEnabled()) {
            return false;
        }
        int y4 = (int) motionEvent.getY();
        int i7 = y4 <= d0Var.f8312N ? 1 : d0Var.f8313O <= y4 ? 3 : 2;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 7 || actionMasked == 9) {
            int i9 = d0Var.f8314P;
            if (i9 != i7) {
                d0Var.f8314P = i7;
                M mE = d0Var.e();
                mE.j(i7, 128);
                mE.j(i9, 256);
            }
            if (i7 == Integer.MIN_VALUE) {
                return false;
            }
        } else {
            if (actionMasked != 10 || (i5 = d0Var.f8314P) == Integer.MIN_VALUE) {
                return false;
            }
            if (i5 != Integer.MIN_VALUE) {
                d0Var.f8314P = Integer.MIN_VALUE;
                M mE2 = d0Var.e();
                mE2.j(Integer.MIN_VALUE, 128);
                mE2.j(i5, 256);
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x00a5  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean dispatchKeyEvent(android.view.KeyEvent r12) {
        /*
            Method dump skipped, instruction units count: 268
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.picker.widget.SeslSpinningDatePickerSpinner.dispatchKeyEvent(android.view.KeyEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEventPreIme(KeyEvent keyEvent) {
        this.f8215e.getClass();
        return super.dispatchKeyEventPreIme(keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        d0 d0Var = this.f8215e;
        d0Var.getClass();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3) {
            d0Var.n();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        d0 d0Var = this.f8215e;
        d0Var.getClass();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 3) {
            d0Var.n();
        }
        return super.dispatchTrackballEvent(motionEvent);
    }

    @Override // android.view.View
    public final AccessibilityNodeProvider getAccessibilityNodeProvider() {
        return this.f8215e.e();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        d0 d0Var = this.f8215e;
        ((SeslSpinningDatePickerSpinner) d0Var.f8272b).getViewTreeObserver().addOnPreDrawListener(d0Var.f8326b0);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        Paint paint;
        super.onConfigurationChanged(configuration);
        d0 d0Var = this.f8215e;
        d0Var.getClass();
        boolean zI = d0.i();
        EditText editText = d0Var.f8329d;
        if (zI) {
            editText.setIncludeFontPadding(true);
            Typeface typeface = d0Var.f8352q0;
            d0Var.f8347n0 = typeface;
            d0Var.f8348o0 = Typeface.create(typeface, 0);
            d0Var.f8350p0 = Typeface.create(d0Var.f8347n0, 1);
            d0Var.p();
            return;
        }
        editText.setIncludeFontPadding(false);
        d0Var.p();
        if (d0Var.f8337i) {
            float f2 = 0.0f;
            float f7 = 0.0f;
            int i5 = 0;
            while (true) {
                paint = d0Var.f8353r;
                if (i5 > 9) {
                    break;
                }
                float fMeasureText = paint.measureText(String.format(Locale.getDefault(), TimeModel.NUMBER_FORMAT, Integer.valueOf(i5)));
                if (fMeasureText > f7) {
                    f7 = fMeasureText;
                }
                i5++;
            }
            float f9 = (int) (2 * f7);
            float f10 = 0.0f;
            for (String str : new DateFormatSymbols(Locale.getDefault()).getShortWeekdays()) {
                float fMeasureText2 = paint.measureText(str);
                if (fMeasureText2 > f10) {
                    f10 = fMeasureText2;
                }
            }
            for (String str2 : new DateFormatSymbols(Locale.getDefault()).getShortMonths()) {
                float fMeasureText3 = paint.measureText(str2);
                if (fMeasureText3 > f2) {
                    f2 = fMeasureText3;
                }
            }
            int paddingRight = editText.getPaddingRight() + editText.getPaddingLeft() + ((int) (f9 + f10 + f2 + (paint.measureText(" ") * 2.0f) + paint.measureText(",")));
            if (com.bumptech.glide.d.r(editText)) {
                paddingRight += ((int) Math.ceil(AbstractC0752G.z(paint) / 2.0f)) * 13;
            }
            if (d0Var.h != paddingRight) {
                int i7 = d0Var.f8334g;
                if (paddingRight > i7) {
                    d0Var.h = paddingRight;
                } else {
                    d0Var.h = i7;
                }
                ((SeslSpinningDatePickerSpinner) d0Var.f8272b).invalidate();
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        d0 d0Var = this.f8215e;
        d0Var.f8365x.abortAnimation();
        d0Var.f8303F0.b();
        d0Var.f8304G0 = false;
        d0Var.n();
        ((SeslSpinningDatePickerSpinner) d0Var.f8272b).getViewTreeObserver().removeOnPreDrawListener(d0Var.f8326b0);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0125  */
    @Override // android.widget.LinearLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onDraw(android.graphics.Canvas r19) {
        /*
            Method dump skipped, instruction units count: 330
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.picker.widget.SeslSpinningDatePickerSpinner.onDraw(android.graphics.Canvas):void");
    }

    @Override // android.view.View
    public final void onFocusChanged(boolean z9, int i5, Rect rect) {
        M mE;
        M mE2;
        d0 d0Var = this.f8215e;
        SeslSpinningDatePickerSpinner seslSpinningDatePickerSpinner = (SeslSpinningDatePickerSpinner) d0Var.f8272b;
        AccessibilityManager accessibilityManager = d0Var.f8306H0;
        if (z9) {
            InputMethodManager inputMethodManager = (InputMethodManager) d0Var.f8271a.getSystemService("input_method");
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(seslSpinningDatePickerSpinner.getWindowToken(), 0);
            }
            d0Var.f8318T = 1;
            if (d0Var.f8344m.equals(d0Var.f8340k)) {
                d0Var.f8318T = 2;
            }
            if (accessibilityManager.isEnabled() && (mE = d0Var.e()) != null) {
                mE.performAction(d0Var.f8318T, 64, null);
            }
        } else {
            if (accessibilityManager.isEnabled() && (mE2 = d0Var.e()) != null) {
                mE2.performAction(d0Var.f8318T, 128, null);
            }
            d0Var.f8318T = -1;
            d0Var.f8314P = Integer.MIN_VALUE;
        }
        seslSpinningDatePickerSpinner.invalidate();
        super.onFocusChanged(z9, i5, rect);
    }

    @Override // android.view.View
    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        d0 d0Var = this.f8215e;
        if (((SeslSpinningDatePickerSpinner) d0Var.f8272b).isEnabled() && !d0Var.f8333f0 && (motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8) {
            float axisValue = motionEvent.getAxisValue(9);
            if (axisValue != 0.0f) {
                d0Var.r(false);
                d0Var.a(axisValue < 0.0f);
                d0Var.r(true);
                return true;
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        d0 d0Var = this.f8215e;
        d0Var.getClass();
        accessibilityEvent.setClassName(SeslSpinningDatePickerSpinner.class.getName());
        accessibilityEvent.setScrollable(true);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long timeInMillis = d0Var.f8344m.getTimeInMillis();
        Calendar calendar = d0Var.f8340k;
        accessibilityEvent.setScrollY(((int) timeUnit.toDays(timeInMillis - calendar.getTimeInMillis())) * d0Var.f8357t);
        accessibilityEvent.setMaxScrollY(((int) timeUnit.toDays(d0Var.f8342l.getTimeInMillis() - calendar.getTimeInMillis())) * d0Var.f8357t);
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        d0 d0Var = this.f8215e;
        SeslSpinningDatePickerSpinner seslSpinningDatePickerSpinner = (SeslSpinningDatePickerSpinner) d0Var.f8272b;
        if (!seslSpinningDatePickerSpinner.isEnabled() || d0Var.f8333f0 || motionEvent.getActionMasked() != 0) {
            return false;
        }
        d0Var.n();
        float y4 = motionEvent.getY();
        d0Var.f8297C = y4;
        d0Var.f8300E = y4;
        d0Var.f8299D = motionEvent.getEventTime();
        d0Var.f8310L = false;
        d0Var.f8311M = false;
        d0Var.f8360u0 = false;
        float f2 = d0Var.f8297C;
        float f7 = d0Var.f8312N;
        c0 c0Var = d0Var.f8324Z;
        if (f2 < f7) {
            d0Var.r(false);
            if (d0Var.f8309K == 0) {
                c0Var.a();
                c0Var.f8290g = 1;
                c0Var.f8289f = 2;
                ((SeslSpinningDatePickerSpinner) ((d0) c0Var.h).f8272b).postDelayed(c0Var, ViewConfiguration.getTapTimeout());
            }
        } else if (f2 > d0Var.f8313O) {
            d0Var.r(false);
            if (d0Var.f8309K == 0) {
                c0Var.a();
                c0Var.f8290g = 1;
                c0Var.f8289f = 1;
                ((SeslSpinningDatePickerSpinner) ((d0) c0Var.h).f8272b).postDelayed(c0Var, ViewConfiguration.getTapTimeout());
            }
        }
        seslSpinningDatePickerSpinner.getParent().requestDisallowInterceptTouchEvent(true);
        boolean zIsFinished = d0Var.f8363w.isFinished();
        Scroller scroller = d0Var.f8367y;
        if (zIsFinished) {
            androidx.dynamicanimation.animation.h hVar = d0Var.f8303F0;
            if (hVar.f7419f) {
                OverScroller overScroller = d0Var.f8365x;
                overScroller.forceFinished(true);
                scroller.forceFinished(true);
                hVar.b();
                d0Var.f8304G0 = false;
                if (d0Var.f8309K == 2) {
                    overScroller.abortAnimation();
                    scroller.abortAnimation();
                }
                d0Var.l(0);
            } else if (scroller.isFinished()) {
                float f9 = d0Var.f8297C;
                if (f9 < d0Var.f8312N) {
                    d0Var.m(ViewConfiguration.getLongPressTimeout(), false);
                } else if (f9 > d0Var.f8313O) {
                    d0Var.m(ViewConfiguration.getLongPressTimeout(), true);
                } else {
                    d0Var.f8311M = true;
                }
            } else {
                d0Var.f8363w.forceFinished(true);
                scroller.forceFinished(true);
            }
        } else {
            d0Var.f8363w.forceFinished(true);
            scroller.forceFinished(true);
            if (d0Var.f8309K == 2) {
                d0Var.f8363w.abortAnimation();
                scroller.abortAnimation();
            }
            d0Var.l(0);
        }
        return true;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        d0 d0Var = this.f8215e;
        SeslSpinningDatePickerSpinner seslSpinningDatePickerSpinner = (SeslSpinningDatePickerSpinner) d0Var.f8272b;
        int measuredWidth = seslSpinningDatePickerSpinner.getMeasuredWidth();
        int measuredHeight = seslSpinningDatePickerSpinner.getMeasuredHeight();
        EditText editText = d0Var.f8329d;
        int measuredWidth2 = editText.getMeasuredWidth();
        int iMax = Math.max(editText.getMeasuredHeight(), (int) Math.floor(measuredHeight * d0Var.f8354r0));
        d0Var.f8356s0 = iMax;
        int i11 = (measuredWidth - measuredWidth2) / 2;
        int i12 = (measuredHeight - iMax) / 2;
        int i13 = iMax + i12;
        editText.layout(i11, i12, measuredWidth2 + i11, i13);
        if (z9) {
            if (d0Var.f8333f0) {
                if (!d0Var.k(d0Var.f8363w)) {
                    d0Var.k(d0Var.f8367y);
                }
                d0Var.s();
            }
            if (!d0Var.f8333f0) {
                d0Var.h();
            }
            int bottom = d0Var.f8339j + ((int) ((((seslSpinningDatePickerSpinner.getBottom() - seslSpinningDatePickerSpinner.getTop()) - (r5 * 3)) / 3.0f) + 0.5f));
            d0Var.f8357t = bottom;
            int height = d0Var.f8356s0;
            if (height > bottom) {
                height = seslSpinningDatePickerSpinner.getHeight() / 3;
            }
            d0Var.f8358t0 = height;
            int top = ((d0Var.f8356s0 / 2) + editText.getTop()) - d0Var.f8357t;
            d0Var.f8359u = top;
            d0Var.f8361v = top;
            Paint paint = d0Var.f8353r;
            ((CustomEditText) editText).f8216e = ((int) (((paint.descent() - paint.ascent()) / 2.0f) - paint.descent())) - (editText.getBaseline() - (d0Var.f8356s0 / 2));
            if (d0Var.f8335g0) {
                d0Var.f8370z0 = d0Var.f8366x0;
                seslSpinningDatePickerSpinner.post(new Y(0, d0Var));
                d0Var.f8335g0 = false;
            }
            if (d0Var.f8356s0 <= d0Var.f8357t) {
                d0Var.f8312N = i12;
                d0Var.f8313O = i13;
            } else {
                int i14 = d0Var.f8358t0;
                d0Var.f8312N = i14;
                d0Var.f8313O = i14 * 2;
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i5, int i7) {
        d0 d0Var = this.f8215e;
        int iJ = d0.j(i5, d0Var.h);
        int iJ2 = d0.j(i7, d0Var.f8332f);
        SeslSpinningDatePickerSpinner seslSpinningDatePickerSpinner = (SeslSpinningDatePickerSpinner) d0Var.f8272b;
        super.onMeasure(iJ, iJ2);
        int measuredWidth = seslSpinningDatePickerSpinner.getMeasuredWidth();
        int i9 = d0Var.f8334g;
        if (i9 != -1) {
            measuredWidth = View.resolveSizeAndState(Math.max(i9, measuredWidth), i5, 0);
        }
        int measuredHeight = seslSpinningDatePickerSpinner.getMeasuredHeight();
        int i10 = d0Var.f8330e;
        if (i10 != -1) {
            measuredHeight = View.resolveSizeAndState(Math.max(i10, measuredHeight), i7, 0);
        }
        seslSpinningDatePickerSpinner.setMeasuredDimension(measuredWidth, measuredHeight);
    }

    @Override // android.view.View
    public final void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        d0 d0Var = this.f8215e;
        d0Var.getClass();
        List<CharSequence> text = accessibilityEvent.getText();
        M mE = d0Var.e();
        int i5 = M.f7924g;
        text.add(mE.c());
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        d0 d0Var = this.f8215e;
        SeslSpinningDatePickerSpinner seslSpinningDatePickerSpinner = (SeslSpinningDatePickerSpinner) d0Var.f8272b;
        if (!seslSpinningDatePickerSpinner.isEnabled() || d0Var.f8333f0) {
            return false;
        }
        if (d0Var.f8302F == null) {
            d0Var.f8302F = VelocityTracker.obtain();
        }
        d0Var.f8302F.addMovement(motionEvent);
        int actionMasked = motionEvent.getActionMasked();
        int i5 = d0Var.G;
        if (actionMasked == 1) {
            if (d0Var.f8343l0) {
                d0Var.f8343l0 = false;
                d0Var.f8361v = d0Var.f8359u;
            }
            d0Var.f8319U = false;
            d0Var.f8320V = false;
            d0Var.f8321W = false;
            d0Var.f8315Q = 1;
            d0Var.o = 300L;
            b0 b0Var = d0Var.f8295B;
            if (b0Var != null) {
                seslSpinningDatePickerSpinner.removeCallbacks(b0Var);
            }
            c0 c0Var = d0Var.f8324Z;
            c0Var.a();
            VelocityTracker velocityTracker = d0Var.f8302F;
            velocityTracker.computeCurrentVelocity(1000, d0Var.f8307I);
            int yVelocity = (int) velocityTracker.getYVelocity();
            int y4 = (int) motionEvent.getY();
            int iAbs = (int) Math.abs(y4 - d0Var.f8297C);
            if (Math.abs(yVelocity) <= d0Var.f8305H) {
                long eventTime = motionEvent.getEventTime() - d0Var.f8299D;
                if (iAbs > i5 || eventTime >= ViewConfiguration.getLongPressTimeout()) {
                    if (d0Var.f8331e0) {
                        d0Var.f8331e0 = false;
                    }
                    d0Var.c(iAbs);
                    d0Var.r(true);
                } else if (d0Var.f8311M) {
                    d0Var.f8311M = false;
                    d0Var.s();
                } else {
                    int i7 = d0Var.f8313O;
                    d0 d0Var2 = (d0) c0Var.h;
                    if (y4 > i7) {
                        d0Var.a(true);
                        c0Var.a();
                        c0Var.f8290g = 2;
                        c0Var.f8289f = 1;
                        ((SeslSpinningDatePickerSpinner) d0Var2.f8272b).post(c0Var);
                    } else if (y4 < d0Var.f8312N) {
                        d0Var.a(false);
                        c0Var.a();
                        c0Var.f8290g = 2;
                        c0Var.f8289f = 2;
                        ((SeslSpinningDatePickerSpinner) d0Var2.f8272b).post(c0Var);
                    } else {
                        d0Var.c(iAbs);
                    }
                    d0Var.r(true);
                }
                d0Var.f8360u0 = false;
                d0Var.l(0);
            } else if (iAbs > i5 || !d0Var.f8311M) {
                Calendar calendar = d0Var.f8344m;
                if (yVelocity > 0 && calendar.equals(d0Var.f8340k)) {
                    d0Var.r(true);
                } else if (yVelocity >= 0 || !calendar.equals(d0Var.f8342l)) {
                    d0Var.f8369z = 0;
                    Math.abs(yVelocity);
                    d0Var.f8293A = d0Var.f8361v;
                    androidx.dynamicanimation.animation.h hVar = d0Var.f8303F0;
                    hVar.f7414a = yVelocity;
                    OverScroller overScroller = d0Var.f8365x;
                    overScroller.forceFinished(true);
                    overScroller.fling(0, d0Var.f8361v, 0, yVelocity, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    int iRound = Math.round((overScroller.getFinalY() + d0Var.f8361v) / d0Var.f8357t);
                    int i9 = d0Var.f8357t;
                    int i10 = d0Var.f8359u;
                    int i11 = (iRound * i9) + i10;
                    float fMax = yVelocity > 0 ? Math.max(i11, i9 + i10) : Math.min(i11, (-i9) + i10);
                    hVar.f7415b = d0Var.f8361v;
                    hVar.f7416c = true;
                    d0Var.f8304G0 = true;
                    hVar.e(fMax);
                    seslSpinningDatePickerSpinner.invalidate();
                } else {
                    d0Var.r(true);
                }
                d0Var.l(2);
            } else {
                d0Var.f8311M = false;
                d0Var.s();
                d0Var.l(0);
            }
            d0Var.f8302F.recycle();
            d0Var.f8302F = null;
        } else if (actionMasked != 2) {
            if (actionMasked == 3) {
                d0Var.c(0);
                d0Var.r(true);
                d0Var.l(0);
            }
        } else if (!d0Var.f8310L) {
            float y9 = motionEvent.getY();
            if (d0Var.f8309K == 1) {
                d0Var.o((int) (y9 - d0Var.f8300E));
                seslSpinningDatePickerSpinner.invalidate();
            } else if (((int) Math.abs(y9 - d0Var.f8297C)) > i5) {
                d0Var.n();
                d0Var.r(false);
                d0Var.l(1);
            }
            d0Var.f8300E = y9;
        }
        return true;
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z9) {
        super.onWindowFocusChanged(z9);
        d0 d0Var = this.f8215e;
        if (!d0Var.f8333f0) {
            if (!d0Var.f8363w.isFinished()) {
                d0Var.f8363w.forceFinished(true);
            }
            Scroller scroller = d0Var.f8367y;
            if (!scroller.isFinished()) {
                scroller.forceFinished(true);
            }
            OverScroller overScroller = d0Var.f8365x;
            if (!overScroller.isFinished()) {
                overScroller.forceFinished(true);
            }
            androidx.dynamicanimation.animation.h hVar = d0Var.f8303F0;
            if (hVar.f7419f) {
                hVar.b();
                d0Var.f8304G0 = false;
            }
            d0Var.c(0);
        }
        d0Var.f8345m0 = com.bumptech.glide.d.r(d0Var.f8329d);
        Paint paint = d0Var.f8353r;
        paint.setTextSize(d0Var.f8339j);
        paint.setTypeface(d0Var.f8347n0);
        d0Var.p();
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i5) {
        this.f8215e.getClass();
        super.onWindowVisibilityChanged(i5);
    }

    @Override // android.view.View
    public final boolean performClick() {
        if (super.performClick()) {
            return true;
        }
        this.f8215e.s();
        return true;
    }

    @Override // android.view.View
    public final boolean performLongClick() {
        if (!super.performLongClick()) {
            d0 d0Var = this.f8215e;
            d0Var.f8310L = true;
            d0Var.f8331e0 = true;
        }
        return true;
    }

    @Override // android.view.View
    public final void scrollBy(int i5, int i7) {
        this.f8215e.o(i7);
    }

    @Override // android.view.View
    public final void setEnabled(boolean z9) {
        super.setEnabled(z9);
        d0 d0Var = this.f8215e;
        if (z9) {
            d0Var.getClass();
        } else if (d0Var.f8309K != 0) {
            d0Var.s();
            d0Var.l(0);
        }
    }
}
