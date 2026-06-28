package androidx.appcompat.widget;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.LinearInterpolator;
import android.widget.AbsSeekBar;
import com.samsung.android.keyscafe.R;
import e.AbstractC0478a;
import f.AbstractC0510a;
import h6.AbstractC0582a;
import i.C0590a;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: androidx.appcompat.widget.o1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0176o1 extends SeslProgressBar {

    /* JADX INFO: renamed from: A0, reason: collision with root package name */
    public final Rect f6790A0;

    /* JADX INFO: renamed from: B0, reason: collision with root package name */
    public int f6791B0;

    /* JADX INFO: renamed from: C0, reason: collision with root package name */
    public Drawable f6792C0;
    public Drawable D0;

    /* JADX INFO: renamed from: E0, reason: collision with root package name */
    public float f6793E0;

    /* JADX INFO: renamed from: F0, reason: collision with root package name */
    public int f6794F0;

    /* JADX INFO: renamed from: G0, reason: collision with root package name */
    public Drawable f6795G0;

    /* JADX INFO: renamed from: H0, reason: collision with root package name */
    public ColorStateList f6796H0;
    public final ColorStateList I0;
    public final ColorStateList J0;
    public ColorStateList K0;
    public ColorStateList L0;
    public ColorStateList M0;
    public boolean N0;
    public AnimatorSet O0;
    public int P0;
    public boolean Q0;
    public final boolean R0;
    public final boolean S0;

    /* JADX INFO: renamed from: T0, reason: collision with root package name */
    public boolean f6797T0;

    /* JADX INFO: renamed from: U0, reason: collision with root package name */
    public int f6798U0;

    /* JADX INFO: renamed from: V0, reason: collision with root package name */
    public boolean f6799V0;

    /* JADX INFO: renamed from: W0, reason: collision with root package name */
    public final int f6800W0;

    /* JADX INFO: renamed from: X0, reason: collision with root package name */
    public final int f6801X0;

    /* JADX INFO: renamed from: Y0, reason: collision with root package name */
    public final int f6802Y0;

    /* JADX INFO: renamed from: Z0, reason: collision with root package name */
    public final int f6803Z0;
    public final int a1;

    /* JADX INFO: renamed from: b1, reason: collision with root package name */
    public final int f6804b1;

    /* JADX INFO: renamed from: c1, reason: collision with root package name */
    public boolean f6805c1;

    /* JADX INFO: renamed from: d1, reason: collision with root package name */
    public ValueAnimator f6806d1;

    /* JADX INFO: renamed from: e1, reason: collision with root package name */
    public float f6807e1;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public final Rect f6808f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public Drawable f6809g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public ColorStateList f6810h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public PorterDuff.Mode f6811i0;
    public boolean j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public boolean f6812k0;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public Drawable f6813l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public ColorStateList f6814m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public PorterDuff.Mode f6815n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public boolean f6816o0;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public boolean f6817p0;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public int f6818q0;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public boolean f6819r0;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public final boolean f6820s0;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public int f6821t0;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public final float f6822u0;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public final int f6823v0;

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public float f6824w0;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public boolean f6825x0;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public List f6826y0;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public final ArrayList f6827z0;

    public AbstractC0176o1(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.seekBarStyle);
        this.f6808f0 = new Rect();
        this.f6810h0 = null;
        this.f6811i0 = null;
        this.j0 = false;
        this.f6812k0 = false;
        this.f6814m0 = null;
        this.f6815n0 = null;
        this.f6816o0 = false;
        this.f6817p0 = false;
        this.f6820s0 = true;
        this.f6821t0 = 1;
        this.f6826y0 = Collections.emptyList();
        this.f6827z0 = new ArrayList();
        this.f6790A0 = new Rect();
        this.f6794F0 = -1;
        this.N0 = false;
        this.Q0 = false;
        this.f6797T0 = false;
        this.f6798U0 = 0;
        this.f6799V0 = false;
        this.f6805c1 = false;
        this.f6807e1 = 0.0f;
        int[] iArr = AbstractC0478a.f10562g;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, R.attr.seekBarStyle, 0);
        try {
            saveAttributeDataForStyleable(context, iArr, attributeSet, typedArrayObtainStyledAttributes, R.attr.seekBarStyle, 0);
            Resources resources = context.getResources();
            setThumb(typedArrayObtainStyledAttributes.getDrawable(0));
            if (typedArrayObtainStyledAttributes.hasValue(4)) {
                this.f6811i0 = AbstractC0183r0.b(typedArrayObtainStyledAttributes.getInt(4, -1), this.f6811i0);
                this.f6812k0 = true;
            }
            if (typedArrayObtainStyledAttributes.hasValue(3)) {
                this.f6810h0 = typedArrayObtainStyledAttributes.getColorStateList(3);
                this.j0 = true;
            }
            setTickMark(typedArrayObtainStyledAttributes.getDrawable(10));
            if (typedArrayObtainStyledAttributes.hasValue(12)) {
                this.f6815n0 = AbstractC0183r0.b(typedArrayObtainStyledAttributes.getInt(12, -1), this.f6815n0);
                this.f6817p0 = true;
            }
            if (typedArrayObtainStyledAttributes.hasValue(11)) {
                this.f6814m0 = typedArrayObtainStyledAttributes.getColorStateList(11);
                this.f6816o0 = true;
            }
            this.f6819r0 = typedArrayObtainStyledAttributes.getBoolean(2, false);
            this.S0 = typedArrayObtainStyledAttributes.getBoolean(5, true);
            this.f6800W0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(9, Math.round(resources.getDimension(R.dimen.sesl_seekbar_track_height)));
            this.f6801X0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, Math.round(resources.getDimension(R.dimen.sesl_seekbar_track_height_expand)));
            this.f6802Y0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(9, Math.round(resources.getDimension(R.dimen.sesl_seekbar_mode_expand_track_height)));
            this.f6803Z0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, Math.round(resources.getDimension(R.dimen.sesl_seekbar_mode_expand_track_height_expand)));
            this.a1 = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, Math.round(resources.getDimension(R.dimen.sesl_seekbar_thumb_radius)));
            this.f6804b1 = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, Math.round(resources.getDimension(R.dimen.sesl_seekbar_mode_expand_thumb_radius)));
            setThumbOffset(typedArrayObtainStyledAttributes.getDimensionPixelOffset(1, getThumbOffset()));
            if (typedArrayObtainStyledAttributes.hasValue(6)) {
                this.f6600e = typedArrayObtainStyledAttributes.getInt(6, 0);
            }
            if (typedArrayObtainStyledAttributes.getBoolean(13, true)) {
                typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0478a.f10564j, 0, 0);
                try {
                    this.f6822u0 = typedArrayObtainStyledAttributes.getFloat(0, 0.5f);
                    typedArrayObtainStyledAttributes.recycle();
                } finally {
                    typedArrayObtainStyledAttributes.recycle();
                }
            } else {
                this.f6822u0 = 1.0f;
            }
            w();
            x();
            this.f6823v0 = ViewConfiguration.get(context).getScaledTouchSlop();
            boolean zO = s6.c.O(context);
            this.R0 = zO;
            this.J0 = y(resources.getColor(zO ? R.color.sesl_seekbar_control_color_default : R.color.sesl_seekbar_control_color_default_dark));
            this.I0 = y(resources.getColor(R.color.sesl_seekbar_control_color_secondary));
            this.f6796H0 = y(resources.getColor(R.color.sesl_seekbar_control_color_activated));
            this.L0 = y(resources.getColor(zO ? R.color.sesl_seekbar_overlap_color_default_light : R.color.sesl_seekbar_overlap_color_default_dark));
            this.M0 = y(resources.getColor(zO ? R.color.sesl_seekbar_overlap_color_activated_light : R.color.sesl_seekbar_overlap_color_activated_dark));
            ColorStateList thumbTintList = getThumbTintList();
            this.K0 = thumbTintList;
            if (thumbTintList == null) {
                this.K0 = new ColorStateList(new int[][]{new int[]{android.R.attr.state_enabled}, new int[]{-16842910}}, new int[]{resources.getColor(R.color.sesl_thumb_control_color_activated), resources.getColor(zO ? R.color.sesl_seekbar_disable_color_activated_light : R.color.sesl_seekbar_disable_color_activated_dark)});
            }
            if (resources.getBoolean(R.bool.sesl_seekbar_sliding_animation)) {
                A();
            }
            int i5 = this.f6600e;
            if (i5 != 0) {
                setMode(i5);
            } else {
                B();
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public static boolean C(int i5) {
        Method methodU = com.bumptech.glide.c.u("com.samsung.android.widget.SemHoverPopupWindow", "hidden_TYPE_USER_CUSTOM", new Class[0]);
        Object objC = methodU != null ? com.bumptech.glide.c.C(null, methodU, new Object[0]) : null;
        return i5 == (objC instanceof Integer ? ((Integer) objC).intValue() : 3);
    }

    private int getHoverPopupType() {
        Method methodX = com.bumptech.glide.c.x(View.class, "semGetHoverPopupType", new Class[0]);
        if (methodX == null) {
            return 0;
        }
        Object objC = com.bumptech.glide.c.C(this, methodX, new Object[0]);
        if (objC instanceof Integer) {
            return ((Integer) objC).intValue();
        }
        return 0;
    }

    private float getScale() {
        int max = getMax() - getMin();
        if (max > 0) {
            return (getProgress() - r0) / max;
        }
        return 0.0f;
    }

    private void setHoverPopupGravity(int i5) {
        Object objB = com.bumptech.glide.d.B(this);
        Method methodU = com.bumptech.glide.c.u("com.samsung.android.widget.SemHoverPopupWindow", "hidden_setGravity", Integer.TYPE);
        if (methodU != null) {
            com.bumptech.glide.c.C(objB, methodU, Integer.valueOf(i5));
        }
    }

    private void setProgressOverlapTintList(ColorStateList colorStateList) {
        super.setProgressTintList(colorStateList);
    }

    private void setThumbOverlapTintList(ColorStateList colorStateList) {
        this.f6810h0 = colorStateList;
        this.j0 = true;
        w();
    }

    public static void v(AbstractC0176o1 abstractC0176o1, int i5) {
        super.setProgress(i5);
    }

    public static ColorStateList y(int i5) {
        return new ColorStateList(new int[][]{new int[0]}, new int[]{i5});
    }

    public final void A() {
        this.O0 = new AnimatorSet();
        ArrayList arrayList = new ArrayList();
        int i5 = 400;
        for (int i7 = 0; i7 < 8; i7++) {
            boolean z9 = i7 % 2 == 0;
            ValueAnimator valueAnimatorOfInt = z9 ? ValueAnimator.ofInt(0, i5) : ValueAnimator.ofInt(i5, 0);
            valueAnimatorOfInt.setDuration(62);
            valueAnimatorOfInt.setInterpolator(new LinearInterpolator());
            valueAnimatorOfInt.addUpdateListener(new C0158i1(this, 1));
            arrayList.add(valueAnimatorOfInt);
            if (z9) {
                i5 = (int) (((double) i5) * 0.6d);
            }
        }
        this.O0.playSequentially(arrayList);
    }

    public final void B() {
        int i5 = this.f6800W0;
        int i7 = this.f6801X0;
        C0167l1 c0167l1 = new C0167l1(this, i5, i7, this.J0, false);
        C0167l1 c0167l12 = new C0167l1(this, i5, i7, this.I0, false);
        C0167l1 c0167l13 = new C0167l1(this, i5, i7, this.f6796H0, false);
        Drawable c0590a = new C0590a(new C0173n1(this, this.a1, this.K0, false));
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{c0167l1, new ClipDrawable(c0167l12, 19, 1), new ClipDrawable(c0167l13, 19, 1)});
        layerDrawable.setPaddingMode(1);
        layerDrawable.setId(0, android.R.id.background);
        layerDrawable.setId(1, android.R.id.secondaryProgress);
        layerDrawable.setId(2, android.R.id.progress);
        setProgressDrawable(layerDrawable);
        setThumb(c0590a);
        setBackgroundResource(R.drawable.sesl_seekbar_background_borderless_expand);
        if (getMaxHeight() > i7) {
            setMaxHeight(i7);
        }
    }

    public void D() {
        this.f6825x0 = false;
        if (!this.f6805c1 || !isPressed()) {
            if (this.f6805c1) {
                setProgress(Math.round(super.getProgress() / 1000.0f));
                return;
            }
            return;
        }
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(super.getProgress(), (int) (Math.round(super.getProgress() / 1000.0f) * 1000.0f));
        this.f6806d1 = valueAnimatorOfInt;
        valueAnimatorOfInt.setDuration(300L);
        this.f6806d1.setInterpolator(AbstractC0510a.f10752c);
        this.f6806d1.start();
        this.f6806d1.addUpdateListener(new C0158i1((SeslSeekBar) this, 0));
    }

    public final void E(int i5, Drawable drawable, float f2, int i7) {
        int i9;
        int i10 = this.f6600e;
        if (i10 == 3 || i10 == 6) {
            F(getHeight(), drawable, f2, i7);
            return;
        }
        int paddingLeft = ((i5 - getPaddingLeft()) - getPaddingRight()) - ((int) (this.f6807e1 * 2.0f));
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int i11 = (this.f6818q0 * 2) + (paddingLeft - intrinsicWidth);
        int i12 = (int) ((f2 * i11) + 0.5f);
        if (i7 == Integer.MIN_VALUE) {
            Rect bounds = drawable.getBounds();
            i7 = bounds.top;
            i9 = bounds.bottom;
        } else {
            i9 = i7 + intrinsicHeight;
        }
        int i13 = (int) this.f6807e1;
        if (h2.a(this) && this.f6594U) {
            i12 = i11 - i12;
        }
        int i14 = i13 + i12;
        int i15 = i14 + intrinsicWidth;
        Drawable background = getBackground();
        if (background != null) {
            int paddingLeft2 = getPaddingLeft() - this.f6818q0;
            int paddingTop = getPaddingTop();
            E.a.f(background, i14 + paddingLeft2, i7 + paddingTop, paddingLeft2 + i15, paddingTop + i9);
        }
        drawable.setBounds(i14, i7, i15, i9);
        L();
        this.f6791B0 = (getPaddingLeft() + i14) - (getPaddingLeft() - (intrinsicWidth / 2));
        M();
    }

    public final void F(int i5, Drawable drawable, float f2, int i7) {
        int i9;
        int paddingTop = (i5 - getPaddingTop()) - getPaddingBottom();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        int i10 = (this.f6818q0 * 2) + (paddingTop - intrinsicHeight);
        int i11 = (int) ((f2 * i10) + 0.5f);
        if (i7 == Integer.MIN_VALUE) {
            Rect bounds = drawable.getBounds();
            i7 = bounds.left;
            i9 = bounds.right;
        } else {
            i9 = i7 + intrinsicWidth;
        }
        int i12 = i10 - i11;
        int i13 = intrinsicHeight + i12;
        Drawable background = getBackground();
        if (background != null) {
            int paddingLeft = getPaddingLeft();
            int paddingTop2 = getPaddingTop() - this.f6818q0;
            E.a.f(background, i7 + paddingLeft, i12 + paddingTop2, paddingLeft + i9, paddingTop2 + i13);
        }
        drawable.setBounds(i7, i12, i9, i13);
        this.f6791B0 = getPaddingLeft() + (intrinsicWidth / 2) + i12;
    }

    public final void G(MotionEvent motionEvent) {
        setPressed(true);
        Drawable drawable = this.f6809g0;
        if (drawable != null) {
            invalidate(drawable.getBounds());
        }
        SeslSeekBar seslSeekBar = (SeslSeekBar) this;
        seslSeekBar.f6825x0 = true;
        ValueAnimator valueAnimator = seslSeekBar.f6806d1;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
        D1 d12 = seslSeekBar.f6621g1;
        if (d12 != null) {
            d12.onStartTrackingTouch(seslSeekBar);
        }
        J(motionEvent);
        if (getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
    }

    public final boolean H() {
        Method methodT = com.bumptech.glide.c.t(View.class, "isHoveringUIEnabled", new Class[0]);
        if (methodT == null) {
            return false;
        }
        Object objC = com.bumptech.glide.c.C(this, methodT, new Object[0]);
        if (objC instanceof Boolean) {
            return ((Boolean) objC).booleanValue();
        }
        return false;
    }

    public final void I(int i5) {
        int width = getWidth();
        getPaddingLeft();
        getPaddingRight();
        if (i5 >= getPaddingLeft() && i5 <= width - getPaddingRight()) {
            getPaddingLeft();
        }
        getMax();
    }

    public final void J(MotionEvent motionEvent) {
        float f2;
        int min;
        float paddingLeft;
        float f7;
        float f9;
        int min2;
        int i5 = this.f6600e;
        if (i5 == 3 || i5 == 6) {
            int height = getHeight();
            int paddingTop = (height - getPaddingTop()) - getPaddingBottom();
            int iRound = Math.round(motionEvent.getX());
            int iRound2 = height - Math.round(motionEvent.getY());
            float paddingBottom = iRound2 < getPaddingBottom() ? 0.0f : iRound2 > height - getPaddingTop() ? 1.0f : (iRound2 - getPaddingBottom()) / paddingTop;
            if (this.f6805c1) {
                float max = super.getMax() - super.getMin();
                float f10 = 1.0f / max;
                if (paddingBottom > 0.0f && paddingBottom < 1.0f) {
                    float f11 = paddingBottom % f10;
                    if (f11 > f10 / 2.0f) {
                        paddingBottom += f10 - f11;
                    }
                }
                f2 = paddingBottom * max;
                min = super.getMin();
            } else {
                float max2 = getMax() - getMin();
                float f12 = 1.0f / max2;
                if (paddingBottom > 0.0f && paddingBottom < 1.0f) {
                    float f13 = paddingBottom % f12;
                    if (f13 > f12 / 2.0f) {
                        paddingBottom += f12 - f13;
                    }
                }
                f2 = paddingBottom * max2;
                min = getMin();
            }
            float f14 = f2 + min + 0.0f;
            float f15 = iRound;
            float f16 = iRound2;
            Drawable background = getBackground();
            if (background != null) {
                E.a.e(background, f15, f16);
            }
            n(Math.round(f14), true, false);
            return;
        }
        int iRound3 = Math.round(motionEvent.getX());
        int iRound4 = Math.round(motionEvent.getY());
        int width = getWidth();
        int paddingLeft2 = (width - getPaddingLeft()) - getPaddingRight();
        if (h2.a(this) && this.f6594U) {
            if (iRound3 <= width - getPaddingRight()) {
                if (iRound3 >= getPaddingLeft()) {
                    paddingLeft = getPaddingLeft() + (paddingLeft2 - iRound3);
                    f7 = paddingLeft / paddingLeft2;
                }
                f7 = 1.0f;
            }
            f7 = 0.0f;
        } else {
            if (iRound3 >= getPaddingLeft()) {
                if (iRound3 <= width - getPaddingRight()) {
                    paddingLeft = iRound3 - getPaddingLeft();
                    f7 = paddingLeft / paddingLeft2;
                }
                f7 = 1.0f;
            }
            f7 = 0.0f;
        }
        if (this.f6805c1) {
            float max3 = super.getMax() - super.getMin();
            float f17 = 1.0f / max3;
            if (f7 > 0.0f && f7 < 1.0f) {
                float f18 = f7 % f17;
                if (f18 > f17 / 2.0f) {
                    f7 += f17 - f18;
                }
            }
            f9 = f7 * max3;
            min2 = super.getMin();
        } else {
            float max4 = getMax() - getMin();
            float f19 = 1.0f / max4;
            if (f7 > 0.0f && f7 < 1.0f) {
                float f20 = f7 % f19;
                if (f20 > f19 / 2.0f) {
                    f7 += f19 - f20;
                }
            }
            f9 = f7 * max4;
            min2 = getMin();
        }
        float f21 = f9 + min2 + 0.0f;
        float f22 = iRound3;
        float f23 = iRound4;
        Drawable background2 = getBackground();
        if (background2 != null) {
            E.a.e(background2, f22, f23);
        }
        n(Math.round(f21), true, false);
    }

    public final void K() {
        Drawable drawable;
        if (this.f6794F0 == -1 || (drawable = this.f6795G0) == null) {
            return;
        }
        E.a.h(drawable, this.L0);
        if (!this.Q0) {
            if ((!this.f6805c1 || super.getProgress() <= this.f6794F0 * 1000.0f) && getProgress() <= this.f6794F0) {
                setProgressTintList(this.f6796H0);
                setThumbTintList(this.K0);
            } else {
                setProgressOverlapTintList(this.M0);
                setThumbOverlapTintList(this.M0);
            }
        }
        if (getCurrentDrawable() == null || this.f6794F0 == -1 || this.f6795G0 == null) {
            return;
        }
        this.f6795G0.setBounds(getCurrentDrawable().getBounds());
    }

    public final void L() {
        Drawable drawable = this.f6809g0;
        if (drawable == null) {
            super.setSystemGestureExclusionRects(this.f6826y0);
            return;
        }
        ArrayList arrayList = this.f6827z0;
        arrayList.clear();
        Rect rect = this.f6790A0;
        drawable.copyBounds(rect);
        arrayList.add(rect);
        arrayList.addAll(this.f6826y0);
        super.setSystemGestureExclusionRects(arrayList);
    }

    public final void M() {
        if (this.f6600e != 4) {
            return;
        }
        Drawable drawable = this.f6792C0;
        Rect bounds = getCurrentDrawable().getBounds();
        if (drawable != null) {
            if (this.f6594U && h2.a(this)) {
                drawable.setBounds(this.f6791B0, bounds.top, getWidth() - getPaddingRight(), bounds.bottom);
            } else {
                drawable.setBounds(getPaddingLeft(), bounds.top, this.f6791B0, bounds.bottom);
            }
        }
        int width = getWidth();
        int height = getHeight();
        Drawable drawable2 = this.D0;
        if (drawable2 != null) {
            float f2 = width / 2.0f;
            float f7 = this.f6601f;
            float f9 = height / 2.0f;
            drawable2.setBounds((int) (f2 - ((f7 * 4.0f) / 2.0f)), (int) (f9 - ((f7 * 22.0f) / 2.0f)), (int) (((4.0f * f7) / 2.0f) + f2), (int) (((f7 * 22.0f) / 2.0f) + f9));
        }
    }

    public final void N(int i5, int i7) {
        int i9;
        int i10;
        int i11;
        int i12;
        int i13 = this.f6600e;
        if (i13 == 3 || i13 == 6) {
            int paddingLeft = (i5 - getPaddingLeft()) - getPaddingRight();
            Drawable currentDrawable = getCurrentDrawable();
            Drawable drawable = this.f6809g0;
            int iMin = Math.min(this.f6610q, paddingLeft);
            int intrinsicWidth = drawable == null ? 0 : drawable.getIntrinsicWidth();
            if (intrinsicWidth > iMin) {
                i10 = (paddingLeft - intrinsicWidth) / 2;
                i9 = ((intrinsicWidth - iMin) / 2) + i10;
            } else {
                int i14 = (paddingLeft - iMin) / 2;
                int i15 = ((iMin - intrinsicWidth) / 2) + i14;
                i9 = i14;
                i10 = i15;
            }
            if (currentDrawable != null) {
                currentDrawable.setBounds(i9, 0, paddingLeft - i9, (i7 - getPaddingBottom()) - getPaddingTop());
            }
            if (drawable != null) {
                F(i7, drawable, getScale(), i10);
                return;
            }
            return;
        }
        int paddingTop = (i7 - getPaddingTop()) - getPaddingBottom();
        Drawable currentDrawable2 = getCurrentDrawable();
        Drawable drawable2 = this.f6809g0;
        int iMin2 = Math.min(this.f6612s, paddingTop);
        int intrinsicHeight = drawable2 == null ? 0 : drawable2.getIntrinsicHeight();
        if (intrinsicHeight > iMin2) {
            i12 = (paddingTop - intrinsicHeight) / 2;
            i11 = ((intrinsicHeight - iMin2) / 2) + i12;
        } else {
            int i16 = (paddingTop - iMin2) / 2;
            int i17 = ((iMin2 - intrinsicHeight) / 2) + i16;
            i11 = i16;
            i12 = i17;
        }
        if (currentDrawable2 != null) {
            currentDrawable2.setBounds(0, i11, (i5 - getPaddingRight()) - getPaddingLeft(), iMin2 + i11);
        }
        if (drawable2 != null) {
            E(i5, drawable2, getScale(), i12);
        }
        M();
    }

    public final void O(int i5) {
        if (this.f6600e == 1) {
            if (i5 == getMax()) {
                setProgressOverlapTintList(this.M0);
                setThumbOverlapTintList(this.M0);
            } else {
                setProgressTintList(this.f6796H0);
                setThumbTintList(this.K0);
            }
        }
    }

    @Override // androidx.appcompat.widget.SeslProgressBar, android.view.View
    public final void drawableHotspotChanged(float f2, float f7) {
        super.drawableHotspotChanged(f2, f7);
        Drawable drawable = this.f6809g0;
        if (drawable != null) {
            E.a.e(drawable, f2, f7);
        }
    }

    @Override // androidx.appcompat.widget.SeslProgressBar, android.view.View
    public final void drawableStateChanged() {
        Drawable drawable;
        super.drawableStateChanged();
        Drawable progressDrawable = getProgressDrawable();
        if (progressDrawable != null) {
            float f2 = this.f6822u0;
            if (f2 < 1.0f) {
                int i5 = isEnabled() ? 255 : (int) (f2 * 255.0f);
                progressDrawable.setAlpha(i5);
                Drawable drawable2 = this.f6795G0;
                if (drawable2 != null) {
                    drawable2.setAlpha(i5);
                }
            }
        }
        if (this.f6809g0 != null && this.j0) {
            if (isEnabled()) {
                E.a.h(this.f6809g0, this.K0);
                K();
            } else {
                E.a.h(this.f6809g0, null);
            }
        }
        if (this.f6797T0 && progressDrawable != null && progressDrawable.isStateful() && (drawable = this.f6795G0) != null) {
            drawable.setState(getDrawableState());
        }
        Drawable drawable3 = this.f6809g0;
        if (drawable3 != null && drawable3.isStateful() && drawable3.setState(getDrawableState())) {
            invalidateDrawable(drawable3);
        }
        Drawable drawable4 = this.f6813l0;
        if (drawable4 != null && drawable4.isStateful() && drawable4.setState(getDrawableState())) {
            invalidateDrawable(drawable4);
        }
    }

    @Override // androidx.appcompat.widget.SeslProgressBar
    public final void f(Canvas canvas) {
        int iMax;
        int max;
        Drawable drawable = this.f6809g0;
        Rect rect = this.f6808f0;
        if (drawable == null || !this.f6819r0) {
            super.f(canvas);
            z(canvas);
        } else {
            Rect rectA = AbstractC0183r0.a(drawable);
            drawable.copyBounds(rect);
            rect.offset(getPaddingLeft() - this.f6818q0, getPaddingTop());
            rect.left += rectA.left;
            rect.right -= rectA.right;
            int iSave = canvas.save();
            canvas.clipRect(rect, Region.Op.DIFFERENCE);
            super.f(canvas);
            z(canvas);
            canvas.restoreToCount(iSave);
        }
        if (this.f6794F0 == -1 || this.f6795G0 == null) {
            return;
        }
        canvas.save();
        if (this.f6594U && h2.a(this)) {
            canvas.translate(getWidth() - getPaddingRight(), getPaddingTop());
            canvas.scale(-1.0f, 1.0f);
        } else {
            canvas.translate(getPaddingLeft(), getPaddingTop());
        }
        Rect bounds = this.f6795G0.getBounds();
        this.f6795G0.copyBounds(rect);
        if (this.f6805c1) {
            iMax = Math.max(super.getProgress(), (int) (this.f6794F0 * 1000.0f));
            max = super.getMax();
        } else {
            iMax = Math.max(getProgress(), this.f6794F0);
            max = getMax();
        }
        int min = getMin();
        float f2 = (iMax - min) / (max - min);
        int i5 = this.f6600e;
        if (i5 == 3 || i5 == 6) {
            rect.bottom = (int) (bounds.bottom - (bounds.height() * f2));
        } else {
            rect.left = (int) ((bounds.width() * f2) + bounds.left);
        }
        canvas.clipRect(rect);
        if (this.J0.getDefaultColor() != this.L0.getDefaultColor()) {
            this.f6795G0.draw(canvas);
        }
        canvas.restore();
    }

    @Override // androidx.appcompat.widget.SeslProgressBar, android.view.View
    public CharSequence getAccessibilityClassName() {
        Log.d("SeslAbsSeekBar", "Stack:", new Throwable("stack dump"));
        return AbsSeekBar.class.getName();
    }

    public int getKeyProgressIncrement() {
        return this.f6821t0;
    }

    @Override // androidx.appcompat.widget.SeslProgressBar
    public synchronized int getMax() {
        try {
        } catch (Throwable th) {
            throw th;
        }
        return this.f6805c1 ? Math.round(super.getMax() / 1000.0f) : super.getMax();
    }

    @Override // androidx.appcompat.widget.SeslProgressBar
    public synchronized int getMin() {
        try {
        } catch (Throwable th) {
            throw th;
        }
        return this.f6805c1 ? Math.round(super.getMin() / 1000.0f) : super.getMin();
    }

    @Override // androidx.appcompat.widget.SeslProgressBar
    public synchronized int getProgress() {
        try {
        } catch (Throwable th) {
            throw th;
        }
        return this.f6805c1 ? Math.round(super.getProgress() / 1000.0f) : super.getProgress();
    }

    public boolean getSplitTrack() {
        return this.f6819r0;
    }

    public Drawable getThumb() {
        return this.f6809g0;
    }

    public Rect getThumbBounds() {
        Drawable drawable = this.f6809g0;
        if (drawable != null) {
            return drawable.getBounds();
        }
        return null;
    }

    public int getThumbHeight() {
        return this.f6809g0.getIntrinsicHeight();
    }

    public int getThumbOffset() {
        return this.f6818q0;
    }

    public ColorStateList getThumbTintList() {
        return this.f6810h0;
    }

    public PorterDuff.Mode getThumbTintMode() {
        return this.f6811i0;
    }

    public Drawable getTickMark() {
        return this.f6813l0;
    }

    public ColorStateList getTickMarkTintList() {
        return this.f6814m0;
    }

    public PorterDuff.Mode getTickMarkTintMode() {
        return this.f6815n0;
    }

    @Override // androidx.appcompat.widget.SeslProgressBar
    public void j(float f2, boolean z9, int i5) {
        int i7 = (int) (10000.0f * f2);
        AnimatorSet animatorSet = this.O0;
        if (animatorSet != null && animatorSet.isRunning()) {
            this.O0.cancel();
        }
        this.P0 = i7;
        super.j(f2, z9, i5);
        Drawable drawable = this.f6809g0;
        if (drawable != null) {
            E(getWidth(), drawable, f2, Integer.MIN_VALUE);
            invalidate();
        }
        if (z9 && this.f6600e == 8) {
            performHapticFeedback(s6.c.X(41));
            return;
        }
        if (z9 && this.S0) {
            int i9 = this.f6600e;
            if (i9 == 5 || i9 == 0 || i9 == 6 || i9 == 3) {
                if (i5 == getMin() || i5 == getMax()) {
                    performHapticFeedback(s6.c.X(41));
                }
            }
        }
    }

    @Override // androidx.appcompat.widget.SeslProgressBar, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f6809g0;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f6813l0;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
    }

    @Override // androidx.appcompat.widget.SeslProgressBar
    public final void k(float f2, int i5) {
        Drawable drawable;
        if (i5 != 16908301 || (drawable = this.f6809g0) == null) {
            return;
        }
        E(getWidth(), drawable, f2, Integer.MIN_VALUE);
        invalidate();
    }

    @Override // androidx.appcompat.widget.SeslProgressBar
    public final boolean n(int i5, boolean z9, boolean z10) {
        boolean zN = super.n(i5, z9, z10);
        O(i5);
        K();
        return zN;
    }

    @Override // androidx.appcompat.widget.SeslProgressBar, android.view.View
    public final synchronized void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
            if (H()) {
                int hoverPopupType = getHoverPopupType();
                if (C(hoverPopupType) && this.f6798U0 != hoverPopupType) {
                    this.f6798U0 = hoverPopupType;
                    setHoverPopupGravity(12849);
                    int measuredHeight = getMeasuredHeight() / 2;
                    Object objB = com.bumptech.glide.d.B(this);
                    Class cls = Integer.TYPE;
                    Method methodU = com.bumptech.glide.c.u("com.samsung.android.widget.SemHoverPopupWindow", "hidden_setOffset", cls, cls);
                    if (methodU != null) {
                        com.bumptech.glide.c.C(objB, methodU, 0, Integer.valueOf(measuredHeight));
                    }
                    Object objB2 = com.bumptech.glide.d.B(this);
                    Method methodU2 = com.bumptech.glide.c.u("com.samsung.android.widget.SemHoverPopupWindow", "hidden_setHoverDetectTime", cls);
                    if (methodU2 != null) {
                        com.bumptech.glide.c.C(objB2, methodU2, 200);
                    }
                }
            }
            if (this.f6600e == 4) {
                this.f6792C0.draw(canvas);
                this.D0.draw(canvas);
            }
            if (this.f6809g0 != null) {
                int iSave = canvas.save();
                int i5 = this.f6600e;
                if (i5 == 3 || i5 == 6) {
                    canvas.translate(getPaddingLeft(), getPaddingTop() - this.f6818q0);
                } else {
                    canvas.translate(getPaddingLeft() - this.f6818q0, getPaddingTop());
                }
                this.f6809g0.draw(canvas);
                canvas.restoreToCount(iSave);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        if (H()) {
            int action = motionEvent.getAction();
            int x9 = (int) motionEvent.getX();
            motionEvent.getY();
            if (action == 7) {
                I(x9);
                if (C(getHoverPopupType())) {
                    int rawX = (int) motionEvent.getRawX();
                    int rawY = (int) motionEvent.getRawY();
                    Object objB = com.bumptech.glide.d.B(this);
                    Class cls = Integer.TYPE;
                    Method methodY = com.bumptech.glide.c.y("com.samsung.android.widget.SemHoverPopupWindow", "setHoveringPoint", cls, cls);
                    if (methodY != null) {
                        com.bumptech.glide.c.C(objB, methodY, Integer.valueOf(rawX), Integer.valueOf(rawY));
                    }
                    Object objB2 = com.bumptech.glide.d.B(this);
                    Method methodU = com.bumptech.glide.c.u("com.samsung.android.widget.SemHoverPopupWindow", "hidden_update", new Class[0]);
                    if (methodU != null) {
                        com.bumptech.glide.c.C(objB2, methodU, new Object[0]);
                    }
                }
            } else if (action == 9) {
                I(x9);
            }
        }
        return super.onHoverEvent(motionEvent);
    }

    @Override // androidx.appcompat.widget.SeslProgressBar, android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (isEnabled()) {
            int progress = getProgress();
            if (progress > getMin()) {
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_BACKWARD);
            }
            if (progress < getMax()) {
                accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_FORWARD);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007f A[RETURN] */
    @Override // android.view.View, android.view.KeyEvent.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onKeyDown(int r9, android.view.KeyEvent r10) {
        /*
            r8 = this;
            boolean r0 = r8.isEnabled()
            if (r0 == 0) goto L80
            int r0 = r8.f6821t0
            int r1 = r8.f6600e
            r2 = 3
            r3 = 1148846080(0x447a0000, float:1000.0)
            r4 = 81
            r5 = 70
            r6 = 69
            r7 = 1
            if (r1 == r2) goto L4d
            r2 = 6
            if (r1 != r2) goto L1a
            goto L4d
        L1a:
            r1 = 21
            if (r9 == r1) goto L29
            r1 = 22
            if (r9 == r1) goto L2a
            if (r9 == r6) goto L29
            if (r9 == r5) goto L2a
            if (r9 == r4) goto L2a
            goto L80
        L29:
            int r0 = -r0
        L2a:
            boolean r1 = androidx.appcompat.widget.h2.a(r8)
            if (r1 == 0) goto L31
            int r0 = -r0
        L31:
            boolean r1 = r8.f6805c1
            if (r1 == 0) goto L41
            int r1 = r8.getProgress()
            int r1 = r1 + r0
            float r0 = (float) r1
            float r0 = r0 * r3
            int r0 = java.lang.Math.round(r0)
            goto L46
        L41:
            int r1 = r8.getProgress()
            int r0 = r0 + r1
        L46:
            boolean r0 = r8.n(r0, r7, r7)
            if (r0 == 0) goto L80
            return r7
        L4d:
            r1 = 19
            if (r9 == r1) goto L5d
            r1 = 20
            if (r9 == r1) goto L5c
            if (r9 == r6) goto L5c
            if (r9 == r5) goto L5d
            if (r9 == r4) goto L5d
            goto L80
        L5c:
            int r0 = -r0
        L5d:
            boolean r1 = androidx.appcompat.widget.h2.a(r8)
            if (r1 == 0) goto L64
            int r0 = -r0
        L64:
            boolean r1 = r8.f6805c1
            if (r1 == 0) goto L74
            int r1 = r8.getProgress()
            int r1 = r1 + r0
            float r0 = (float) r1
            float r0 = r0 * r3
            int r0 = java.lang.Math.round(r0)
            goto L79
        L74:
            int r1 = r8.getProgress()
            int r0 = r0 + r1
        L79:
            boolean r0 = r8.n(r0, r7, r7)
            if (r0 == 0) goto L80
            return r7
        L80:
            boolean r8 = super.onKeyDown(r9, r10)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AbstractC0176o1.onKeyDown(int, android.view.KeyEvent):boolean");
    }

    @Override // androidx.appcompat.widget.SeslProgressBar, android.view.View
    public final synchronized void onMeasure(int i5, int i7) {
        int iMax;
        int iMax2;
        try {
            Drawable currentDrawable = getCurrentDrawable();
            if (currentDrawable != null) {
                int i9 = this.f6600e;
                if (i9 == 3 || i9 == 6) {
                    Drawable drawable = this.f6809g0;
                    int intrinsicHeight = drawable == null ? 0 : drawable.getIntrinsicHeight();
                    int iMax3 = Math.max(this.f6609p, Math.min(this.f6610q, currentDrawable.getIntrinsicHeight()));
                    iMax = Math.max(this.f6611r, Math.min(this.f6612s, currentDrawable.getIntrinsicWidth()));
                    iMax2 = Math.max(intrinsicHeight, iMax3);
                } else {
                    Drawable drawable2 = this.f6809g0;
                    int intrinsicHeight2 = drawable2 == null ? 0 : drawable2.getIntrinsicHeight();
                    iMax2 = Math.max(this.f6609p, Math.min(this.f6610q, currentDrawable.getIntrinsicWidth()));
                    iMax = Math.max(intrinsicHeight2, Math.max(this.f6611r, Math.min(this.f6612s, currentDrawable.getIntrinsicHeight())));
                }
            } else {
                iMax = 0;
                iMax2 = 0;
            }
            setMeasuredDimension(View.resolveSizeAndState(getPaddingLeft() + getPaddingRight() + iMax2, i5, 0), View.resolveSizeAndState(getPaddingTop() + getPaddingBottom() + iMax, i7, 0));
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i5) {
        super.onRtlPropertiesChanged(i5);
        Drawable drawable = this.f6809g0;
        if (drawable != null) {
            E(getWidth(), drawable, getScale(), Integer.MIN_VALUE);
            invalidate();
        }
    }

    @Override // androidx.appcompat.widget.SeslProgressBar, android.view.View
    public final void onSizeChanged(int i5, int i7, int i9, int i10) {
        t(i5, i7);
        N(i5, i7);
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x00cd  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            Method dump skipped, instruction units count: 219
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AbstractC0176o1.onTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public final boolean performAccessibilityAction(int i5, Bundle bundle) {
        boolean z9;
        boolean z10;
        if (super.performAccessibilityAction(i5, bundle)) {
            return true;
        }
        if (!isEnabled()) {
            return false;
        }
        if (i5 == 4096 || i5 == 8192) {
            synchronized (this) {
                z9 = this.f6576B;
            }
            if (!z9 && isEnabled()) {
                int iMax = Math.max(1, Math.round((getMax() - getMin()) / 20.0f));
                if (i5 == 8192) {
                    iMax = -iMax;
                }
                if (n(this.f6805c1 ? Math.round((getProgress() + iMax) * 1000.0f) : getProgress() + iMax, true, true)) {
                    return true;
                }
            }
            return false;
        }
        if (i5 != 16908349) {
            return false;
        }
        synchronized (this) {
            z10 = this.f6576B;
        }
        if (z10 || !isEnabled() || bundle == null || !bundle.containsKey("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE")) {
            return false;
        }
        float f2 = bundle.getFloat("android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE");
        return n(this.f6805c1 ? Math.round(f2 * 1000.0f) : (int) f2, true, true);
    }

    public void setKeyProgressIncrement(int i5) {
        if (i5 < 0) {
            i5 = -i5;
        }
        this.f6821t0 = i5;
    }

    @Override // androidx.appcompat.widget.SeslProgressBar
    public synchronized void setMax(int i5) {
        try {
            if (this.f6805c1) {
                i5 = Math.round(i5 * 1000.0f);
            }
            super.setMax(i5);
            int max = getMax() - getMin();
            int i7 = this.f6821t0;
            if (i7 == 0 || max / i7 > 20) {
                setKeyProgressIncrement(Math.max(1, Math.round(max / 20.0f)));
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // androidx.appcompat.widget.SeslProgressBar
    public synchronized void setMin(int i5) {
        try {
            if (this.f6805c1) {
                i5 = Math.round(i5 * 1000.0f);
            }
            super.setMin(i5);
            int max = getMax() - getMin();
            int i7 = this.f6821t0;
            if (i7 == 0 || max / i7 > 20) {
                setKeyProgressIncrement(Math.max(1, Math.round(max / 20.0f)));
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // androidx.appcompat.widget.SeslProgressBar
    public void setMode(int i5) {
        if (this.f6600e == i5 && this.f6799V0) {
            Log.w("SeslAbsSeekBar", "Seekbar mode is already set. Do not call this method redundant");
            return;
        }
        super.setMode(i5);
        this.f6807e1 = 0.0f;
        if (i5 == 0) {
            setProgressTintList(this.f6796H0);
            setThumbTintList(this.K0);
        } else if (i5 == 1) {
            O(getProgress());
        } else if (i5 == 3) {
            setThumb(getContext().getResources().getDrawable(this.R0 ? R.drawable.sesl_scrubber_control_anim_light : R.drawable.sesl_scrubber_control_anim_dark));
            setBackgroundResource(R.drawable.sesl_seek_bar_background_borderless);
        } else if (i5 != 4) {
            ColorStateList colorStateList = this.I0;
            ColorStateList colorStateList2 = this.J0;
            if (i5 == 5) {
                float f2 = this.f6802Y0;
                int i7 = this.f6803Z0;
                float f7 = i7;
                C0167l1 c0167l1 = new C0167l1(this, f2, f7, colorStateList2, false);
                C0167l1 c0167l12 = new C0167l1(this, f2, f7, colorStateList, false);
                C0167l1 c0167l13 = new C0167l1(this, f2, f7, this.f6796H0, false);
                Drawable c0590a = new C0590a(new C0173n1(this, this.f6804b1, this.K0, false));
                LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{c0167l1, new ClipDrawable(c0167l12, 19, 1), new ClipDrawable(c0167l13, 19, 1)});
                layerDrawable.setPaddingMode(1);
                layerDrawable.setId(0, android.R.id.background);
                layerDrawable.setId(1, android.R.id.secondaryProgress);
                layerDrawable.setId(2, android.R.id.progress);
                setProgressDrawable(layerDrawable);
                setThumb(c0590a);
                setBackgroundResource(R.drawable.sesl_seekbar_background_borderless_expand);
                if (getMaxHeight() > i7) {
                    setMaxHeight(i7);
                }
                this.f6807e1 = getContext().getResources().getDimension(R.dimen.sesl_seekbar_level_progress_padding_start_end);
            } else if (i5 == 6) {
                float f9 = this.f6800W0;
                int i9 = this.f6801X0;
                float f10 = i9;
                C0167l1 c0167l14 = new C0167l1(this, f9, f10, colorStateList2, true);
                C0167l1 c0167l15 = new C0167l1(this, f9, f10, colorStateList, true);
                C0167l1 c0167l16 = new C0167l1(this, f9, f10, this.f6796H0, true);
                Drawable c0590a2 = new C0590a(new C0173n1(this, this.a1, this.K0, true));
                LayerDrawable layerDrawable2 = new LayerDrawable(new Drawable[]{c0167l14, new ClipDrawable(c0167l15, 81, 2), new ClipDrawable(c0167l16, 81, 2)});
                layerDrawable2.setPaddingMode(1);
                layerDrawable2.setId(0, android.R.id.background);
                layerDrawable2.setId(1, android.R.id.secondaryProgress);
                layerDrawable2.setId(2, android.R.id.progress);
                setProgressDrawable(layerDrawable2);
                setThumb(c0590a2);
                setBackgroundResource(R.drawable.sesl_seekbar_background_borderless_expand);
                if (getMaxWidth() > i9) {
                    setMaxWidth(i9);
                }
            } else if (i5 == 8) {
                this.f6807e1 = getContext().getResources().getDimension(R.dimen.sesl_seekbar_level_progress_padding_start_end);
                setProgressDrawable(getContext().getResources().getDrawable(R.drawable.sesl_level_seekbar_progress));
                setTickMark(getContext().getResources().getDrawable(R.drawable.sesl_level_seekbar_tick_mark));
                setThumb(getContext().getResources().getDrawable(R.drawable.sesl_level_seekbar_thumb));
                setBackgroundResource(R.drawable.sesl_seek_bar_background_borderless);
            }
        } else {
            this.f6792C0 = getContext().getResources().getDrawable(R.drawable.sesl_split_seekbar_primary_progress);
            this.D0 = getContext().getResources().getDrawable(R.drawable.sesl_split_seekbar_vertical_bar);
            M();
        }
        invalidate();
        this.f6799V0 = true;
    }

    @Deprecated
    public void setOverlapBackgroundForDualColor(int i5) {
        ColorStateList colorStateListY = y(i5);
        if (!colorStateListY.equals(this.L0)) {
            this.L0 = colorStateListY;
        }
        this.M0 = this.L0;
        this.Q0 = true;
    }

    public void setOverlapPointForDualColor(int i5) {
        if (i5 >= getMax()) {
            return;
        }
        this.f6797T0 = true;
        this.f6794F0 = i5;
        if (i5 == -1) {
            setProgressTintList(this.f6796H0);
            setThumbTintList(this.K0);
        } else {
            if (this.f6795G0 == null) {
                int i7 = this.f6600e;
                if (i7 == 5) {
                    this.f6795G0 = new C0167l1(this, this.f6802Y0, this.f6803Z0, this.L0, false);
                } else {
                    int i9 = this.f6801X0;
                    int i10 = this.f6800W0;
                    if (i7 == 6) {
                        this.f6795G0 = new C0167l1(this, i10, i9, this.L0, true);
                    } else if (i7 == 0) {
                        this.f6795G0 = new C0167l1(this, i10, i9, this.L0, false);
                    } else if (getProgressDrawable() != null && getProgressDrawable().getConstantState() != null) {
                        this.f6795G0 = getProgressDrawable().getConstantState().newDrawable().mutate();
                    }
                }
            }
            K();
        }
        invalidate();
    }

    @Override // androidx.appcompat.widget.SeslProgressBar
    public synchronized void setProgress(int i5) {
        try {
            if (this.f6805c1) {
                i5 = Math.round(i5 * 1000.0f);
            }
            super.setProgress(i5);
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // androidx.appcompat.widget.SeslProgressBar
    public void setProgressDrawable(Drawable drawable) {
        super.setProgressDrawable(drawable);
    }

    @Override // androidx.appcompat.widget.SeslProgressBar
    public void setProgressTintList(ColorStateList colorStateList) {
        super.setProgressTintList(colorStateList);
        this.f6796H0 = colorStateList;
    }

    public void setSeamless(boolean z9) {
        if (this.f6805c1 != z9) {
            this.f6805c1 = z9;
            if (z9) {
                super.setMax(Math.round(super.getMax() * 1000.0f));
                super.setMin(Math.round(super.getMin() * 1000.0f));
                super.setProgress(Math.round(super.getProgress() * 1000.0f));
                super.setSecondaryProgress(Math.round(super.getSecondaryProgress() * 1000.0f));
                return;
            }
            super.setProgress(Math.round(super.getProgress() / 1000.0f));
            super.setSecondaryProgress(Math.round(super.getSecondaryProgress() / 1000.0f));
            super.setMax(Math.round(super.getMax() / 1000.0f));
            super.setMin(Math.round(super.getMin() / 1000.0f));
        }
    }

    @Override // androidx.appcompat.widget.SeslProgressBar
    public synchronized void setSecondaryProgress(int i5) {
        try {
            if (this.f6805c1) {
                i5 = Math.round(i5 * 1000.0f);
            }
            super.setSecondaryProgress(i5);
        } catch (Throwable th) {
            throw th;
        }
    }

    public void setSplitTrack(boolean z9) {
        this.f6819r0 = z9;
        invalidate();
    }

    @Override // android.view.View
    public void setSystemGestureExclusionRects(List<Rect> list) {
        AbstractC0582a.o(list, "rects must not be null");
        this.f6826y0 = list;
        L();
    }

    public void setThumb(Drawable drawable) {
        boolean z9;
        Drawable drawable2 = this.f6809g0;
        if (drawable2 == null || drawable == drawable2) {
            z9 = false;
        } else {
            drawable2.setCallback(null);
            z9 = true;
        }
        if (drawable != null) {
            drawable.setCallback(this);
            if (canResolveLayoutDirection()) {
                WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
                E.b.b(drawable, getLayoutDirection());
            }
            int i5 = this.f6600e;
            if (i5 == 3 || i5 == 6) {
                this.f6818q0 = drawable.getIntrinsicHeight() / 2;
            } else {
                this.f6818q0 = drawable.getIntrinsicWidth() / 2;
            }
            if (z9 && (drawable.getIntrinsicWidth() != this.f6809g0.getIntrinsicWidth() || drawable.getIntrinsicHeight() != this.f6809g0.getIntrinsicHeight())) {
                requestLayout();
            }
        }
        this.f6809g0 = drawable;
        w();
        invalidate();
        if (z9) {
            N(getWidth(), getHeight());
            if (drawable == null || !drawable.isStateful()) {
                return;
            }
            drawable.setState(getDrawableState());
        }
    }

    public void setThumbOffset(int i5) {
        this.f6818q0 = i5;
        invalidate();
    }

    public void setThumbTintColor(int i5) {
        ColorStateList colorStateListY = y(i5);
        if (colorStateListY.equals(this.K0)) {
            return;
        }
        this.K0 = colorStateListY;
    }

    public void setThumbTintList(ColorStateList colorStateList) {
        this.f6810h0 = colorStateList;
        this.j0 = true;
        w();
        this.K0 = colorStateList;
    }

    public void setThumbTintMode(PorterDuff.Mode mode) {
        this.f6811i0 = mode;
        this.f6812k0 = true;
        w();
    }

    public void setTickMark(Drawable drawable) {
        Drawable drawable2 = this.f6813l0;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.f6813l0 = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
            E.b.b(drawable, getLayoutDirection());
            if (drawable.isStateful()) {
                drawable.setState(getDrawableState());
            }
            x();
        }
        invalidate();
    }

    public void setTickMarkTintList(ColorStateList colorStateList) {
        this.f6814m0 = colorStateList;
        this.f6816o0 = true;
        x();
    }

    public void setTickMarkTintMode(PorterDuff.Mode mode) {
        this.f6815n0 = mode;
        this.f6817p0 = true;
        x();
    }

    @Override // androidx.appcompat.widget.SeslProgressBar
    public final void t(int i5, int i7) {
        super.t(i5, i7);
        N(i5, i7);
        if (getCurrentDrawable() == null || this.f6794F0 == -1 || this.f6795G0 == null) {
            return;
        }
        this.f6795G0.setBounds(getCurrentDrawable().getBounds());
    }

    @Override // androidx.appcompat.widget.SeslProgressBar, android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return drawable == this.f6809g0 || drawable == this.f6813l0 || super.verifyDrawable(drawable);
    }

    public final void w() {
        Drawable drawable = this.f6809g0;
        if (drawable != null) {
            if (this.j0 || this.f6812k0) {
                Drawable drawableMutate = drawable.mutate();
                this.f6809g0 = drawableMutate;
                if (this.j0) {
                    E.a.h(drawableMutate, this.f6810h0);
                }
                if (this.f6812k0) {
                    E.a.i(this.f6809g0, this.f6811i0);
                }
                if (this.f6809g0.isStateful()) {
                    this.f6809g0.setState(getDrawableState());
                }
            }
        }
    }

    public final void x() {
        Drawable drawable = this.f6813l0;
        if (drawable != null) {
            if (this.f6816o0 || this.f6817p0) {
                Drawable drawableMutate = drawable.mutate();
                this.f6813l0 = drawableMutate;
                if (this.f6816o0) {
                    E.a.h(drawableMutate, this.f6814m0);
                }
                if (this.f6817p0) {
                    E.a.i(this.f6813l0, this.f6815n0);
                }
                if (this.f6813l0.isStateful()) {
                    this.f6813l0.setState(getDrawableState());
                }
            }
        }
    }

    public final void z(Canvas canvas) {
        if (this.f6813l0 != null) {
            int max = getMax() - getMin();
            if (max > 1) {
                int intrinsicWidth = this.f6813l0.getIntrinsicWidth();
                int intrinsicHeight = this.f6813l0.getIntrinsicHeight();
                int i5 = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                int i7 = intrinsicHeight >= 0 ? intrinsicHeight / 2 : 1;
                this.f6813l0.setBounds(-i5, -i7, i5, i7);
                float width = (((getWidth() - getPaddingLeft()) - getPaddingRight()) - (this.f6807e1 * 2.0f)) / max;
                int iSave = canvas.save();
                canvas.translate(this.f6807e1 + getPaddingLeft(), getHeight() / 2.0f);
                for (int i9 = 0; i9 <= max; i9++) {
                    this.f6813l0.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(iSave);
            }
        }
    }
}
