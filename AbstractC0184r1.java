package androidx.appcompat.widget;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.TextView;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: androidx.appcompat.widget.r1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0184r1 extends TextView implements Checkable {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final int[] f6839r = {R.attr.state_checked};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6840e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f6841f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Drawable f6842g;
    public ColorStateList h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public PorterDuff.Mode f6843i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f6844j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f6845k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f6846l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f6847m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f6848n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f6849p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f6850q;

    private void setBasePadding(boolean z9) {
        if (z9) {
            this.f6846l = getPaddingLeft();
        } else {
            this.f6846l = getPaddingRight();
        }
    }

    public final void a() {
        Drawable drawable = this.f6842g;
        if (drawable != null) {
            if (this.f6844j || this.f6845k) {
                Drawable drawableMutate = drawable.mutate();
                this.f6842g = drawableMutate;
                if (this.f6844j) {
                    drawableMutate.setTintList(this.h);
                }
                if (this.f6845k) {
                    this.f6842g.setTintMode(this.f6843i);
                }
                if (this.f6842g.isStateful()) {
                    this.f6842g.setState(getDrawableState());
                }
            }
        }
    }

    public final boolean b() {
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        return (Gravity.getAbsoluteGravity(this.f6848n, getLayoutDirection()) & 7) == 3;
    }

    public final void c(Drawable drawable, int i5) {
        Drawable drawable2 = this.f6842g;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f6842g);
        }
        this.o = drawable != this.f6842g;
        if (drawable != null) {
            drawable.setCallback(this);
            drawable.setVisible(getVisibility() == 0, false);
            drawable.setState(f6839r);
            setMinHeight(drawable.getIntrinsicHeight());
            this.f6847m = drawable.getIntrinsicWidth();
            drawable.setState(getDrawableState());
        } else {
            this.f6847m = 0;
        }
        this.f6842g = drawable;
        this.f6841f = i5;
        a();
        Method methodT = com.bumptech.glide.c.t(View.class, "hidden_resolvePadding", new Class[0]);
        if (methodT != null) {
            com.bumptech.glide.c.C(this, methodT, new Object[0]);
        }
        setBasePadding(b());
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableHotspotChanged(float f2, float f7) {
        super.drawableHotspotChanged(f2, f7);
        Drawable drawable = this.f6842g;
        if (drawable != null) {
            E.a.e(drawable, f2, f7);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f6842g;
        if (drawable != null && drawable.isStateful() && drawable.setState(getDrawableState())) {
            invalidateDrawable(drawable);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public CharSequence getAccessibilityClassName() {
        return CheckedTextView.class.getName();
    }

    public Drawable getCheckMarkDrawable() {
        return this.f6842g;
    }

    public ColorStateList getCheckMarkTintList() {
        return this.h;
    }

    public PorterDuff.Mode getCheckMarkTintMode() {
        return this.f6843i;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    @Override // android.widget.TextView, android.view.View, android.graphics.drawable.Drawable.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void invalidateDrawable(android.graphics.drawable.Drawable r4) {
        /*
            r3 = this;
            super.invalidateDrawable(r4)
            boolean r0 = r3.verifyDrawable(r4)
            if (r0 == 0) goto L3a
            android.graphics.Rect r4 = r4.getBounds()
            boolean r0 = androidx.appcompat.widget.h2.a(r3)
            if (r0 == 0) goto L3a
            java.lang.Class<android.widget.TextView> r0 = android.widget.TextView.class
            java.lang.String r1 = "mSingleLine"
            java.lang.reflect.Field r0 = com.bumptech.glide.c.s(r0, r1)
            if (r0 == 0) goto L2c
            java.lang.Object r0 = com.bumptech.glide.c.m(r3, r0)
            boolean r1 = r0 instanceof java.lang.Boolean
            if (r1 == 0) goto L2c
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            goto L2d
        L2c:
            r0 = 0
        L2d:
            if (r0 == 0) goto L3a
            int r0 = r4.left
            int r1 = r4.top
            int r2 = r4.right
            int r4 = r4.bottom
            r3.invalidate(r0, r1, r2, r4)
        L3a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AbstractC0184r1.invalidateDrawable(android.graphics.drawable.Drawable):void");
    }

    @Override // android.widget.Checkable
    public final boolean isChecked() {
        return this.f6840e;
    }

    @Override // android.widget.TextView, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f6842g;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final int[] onCreateDrawableState(int i5) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i5 + 1);
        if (this.f6840e) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, f6839r);
        }
        return iArrOnCreateDrawableState;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onDraw(Canvas canvas) {
        int i5;
        int i7;
        super.onDraw(canvas);
        Drawable drawable = this.f6842g;
        if (drawable != null) {
            int gravity = getGravity() & 112;
            int intrinsicHeight = drawable.getIntrinsicHeight();
            int height = gravity != 16 ? gravity != 80 ? 0 : getHeight() - intrinsicHeight : (getHeight() - intrinsicHeight) / 2;
            boolean zB = b();
            int width = getWidth();
            int i9 = intrinsicHeight + height;
            if (zB) {
                i7 = this.f6846l;
                i5 = this.f6847m + i7;
            } else {
                i5 = width - this.f6846l;
                i7 = i5 - this.f6847m;
            }
            int scrollX = getScrollX();
            if (h2.a(this)) {
                drawable.setBounds(scrollX + i7, height, scrollX + i5, i9);
            } else {
                drawable.setBounds(i7, height, i5, i9);
            }
            drawable.draw(canvas);
            Drawable background = getBackground();
            if (background != null) {
                E.a.f(background, i7 + scrollX, height, scrollX + i5, i9);
            }
        }
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setChecked(this.f6840e);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setCheckable(true);
        accessibilityNodeInfo.setChecked(this.f6840e);
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        C0182q1 c0182q1 = (C0182q1) parcelable;
        super.onRestoreInstanceState(c0182q1.getSuperState());
        setChecked(c0182q1.f6837e);
        requestLayout();
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0076  */
    @Override // android.widget.TextView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onRtlPropertiesChanged(int r8) {
        /*
            r7 = this;
            super.onRtlPropertiesChanged(r8)
            r8 = 0
            java.lang.Class[] r0 = new java.lang.Class[r8]
            java.lang.String r1 = "resetPaddingToInitialValues"
            java.lang.Class<android.view.View> r2 = android.view.View.class
            java.lang.reflect.Method r0 = com.bumptech.glide.c.t(r2, r1, r0)
            if (r0 == 0) goto L15
            java.lang.Object[] r1 = new java.lang.Object[r8]
            com.bumptech.glide.c.C(r7, r0, r1)
        L15:
            android.graphics.drawable.Drawable r0 = r7.f6842g
            if (r0 == 0) goto L25
            int r0 = r7.f6847m
            int r1 = r7.f6846l
            int r0 = r0 + r1
            int r1 = r7.f6850q
            int r0 = r0 + r1
            int r1 = r7.f6849p
            int r0 = r0 + r1
            goto L27
        L25:
            int r0 = r7.f6846l
        L27:
            boolean r1 = r7.b()
            r3 = 1
            if (r1 == 0) goto L5d
            boolean r1 = r7.o
            java.lang.String r4 = "mPaddingLeft"
            java.lang.reflect.Field r5 = com.bumptech.glide.c.s(r2, r4)
            if (r5 == 0) goto L47
            java.lang.Object r5 = com.bumptech.glide.c.m(r7, r5)
            boolean r6 = r5 instanceof java.lang.Integer
            if (r6 == 0) goto L47
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            goto L48
        L47:
            r5 = r8
        L48:
            if (r5 == r0) goto L4b
            goto L4c
        L4b:
            r3 = r8
        L4c:
            r1 = r1 | r3
            r7.o = r1
            java.lang.reflect.Field r1 = com.bumptech.glide.c.s(r2, r4)
            if (r1 == 0) goto L8b
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            com.bumptech.glide.c.W(r7, r1, r0)
            goto L8b
        L5d:
            boolean r1 = r7.o
            java.lang.String r4 = "mPaddingRight"
            java.lang.reflect.Field r5 = com.bumptech.glide.c.s(r2, r4)
            if (r5 == 0) goto L76
            java.lang.Object r5 = com.bumptech.glide.c.m(r7, r5)
            boolean r6 = r5 instanceof java.lang.Integer
            if (r6 == 0) goto L76
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            goto L77
        L76:
            r5 = r8
        L77:
            if (r5 == r0) goto L7a
            goto L7b
        L7a:
            r3 = r8
        L7b:
            r1 = r1 | r3
            r7.o = r1
            java.lang.reflect.Field r1 = com.bumptech.glide.c.s(r2, r4)
            if (r1 == 0) goto L8b
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            com.bumptech.glide.c.W(r7, r1, r0)
        L8b:
            boolean r0 = r7.o
            if (r0 == 0) goto L94
            r7.requestLayout()
            r7.o = r8
        L94:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AbstractC0184r1.onRtlPropertiesChanged(int):void");
    }

    @Override // android.widget.TextView, android.view.View
    public final Parcelable onSaveInstanceState() {
        C0182q1 c0182q1 = new C0182q1(super.onSaveInstanceState());
        c0182q1.f6837e = this.f6840e;
        return c0182q1;
    }

    public void setCheckMarkDrawable(int i5) {
        if (i5 == 0 || i5 != this.f6841f) {
            c(i5 != 0 ? B.a.b(getContext(), i5) : null, i5);
        }
    }

    public void setCheckMarkTintList(ColorStateList colorStateList) {
        this.h = colorStateList;
        this.f6844j = true;
        a();
    }

    public void setCheckMarkTintMode(PorterDuff.Mode mode) {
        this.f6843i = mode;
        this.f6845k = true;
        a();
    }

    public void setChecked(boolean z9) {
        if (this.f6840e != z9) {
            this.f6840e = z9;
            refreshDrawableState();
            Method methodX = com.bumptech.glide.c.x(View.class, "hidden_notifyViewAccessibilityStateChangedIfNeeded", Integer.TYPE);
            if (methodX != null) {
                com.bumptech.glide.c.C(this, methodX, 0);
            }
        }
    }

    @Override // android.view.View
    public void setVisibility(int i5) {
        super.setVisibility(i5);
        Drawable drawable = this.f6842g;
        if (drawable != null) {
            drawable.setVisible(i5 == 0, false);
        }
    }

    @Override // android.widget.Checkable
    public final void toggle() {
        setChecked(!this.f6840e);
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        return drawable == this.f6842g || super.verifyDrawable(drawable);
    }

    public void setCheckMarkDrawable(Drawable drawable) {
        c(drawable, 0);
    }
}
