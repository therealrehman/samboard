package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.ToggleButton;

/* JADX INFO: renamed from: androidx.appcompat.widget.m0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0169m0 extends ToggleButton {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0188t f6757e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C0148f0 f6758f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public D f6759g;

    public C0169m0(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.buttonStyleToggle);
        P1.a(this, getContext());
        C0188t c0188t = new C0188t(this);
        this.f6757e = c0188t;
        c0188t.d(attributeSet, R.attr.buttonStyleToggle);
        C0148f0 c0148f0 = new C0148f0(this);
        this.f6758f = c0148f0;
        c0148f0.f(attributeSet, R.attr.buttonStyleToggle);
        getEmojiTextViewHelper().c(attributeSet, R.attr.buttonStyleToggle);
    }

    private D getEmojiTextViewHelper() {
        if (this.f6759g == null) {
            this.f6759g = new D(this);
        }
        return this.f6759g;
    }

    @Override // android.widget.ToggleButton, android.widget.CompoundButton, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C0188t c0188t = this.f6757e;
        if (c0188t != null) {
            c0188t.a();
        }
        C0148f0 c0148f0 = this.f6758f;
        if (c0148f0 != null) {
            c0148f0.b();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0188t c0188t = this.f6757e;
        if (c0188t != null) {
            return c0188t.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0188t c0188t = this.f6757e;
        if (c0188t != null) {
            return c0188t.c();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f6758f.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f6758f.e();
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z9) {
        super.setAllCaps(z9);
        getEmojiTextViewHelper().d(z9);
    }

    @Override // android.widget.ToggleButton, android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0188t c0188t = this.f6757e;
        if (c0188t != null) {
            c0188t.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i5) {
        super.setBackgroundResource(i5);
        C0188t c0188t = this.f6757e;
        if (c0188t != null) {
            c0188t.f(i5);
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        C0148f0 c0148f0 = this.f6758f;
        if (c0148f0 != null) {
            c0148f0.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        C0148f0 c0148f0 = this.f6758f;
        if (c0148f0 != null) {
            c0148f0.b();
        }
    }

    public void setEmojiCompatEnabled(boolean z9) {
        getEmojiTextViewHelper().e(z9);
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(getEmojiTextViewHelper().a(inputFilterArr));
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0188t c0188t = this.f6757e;
        if (c0188t != null) {
            c0188t.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0188t c0188t = this.f6757e;
        if (c0188t != null) {
            c0188t.i(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        C0148f0 c0148f0 = this.f6758f;
        c0148f0.h(colorStateList);
        c0148f0.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        C0148f0 c0148f0 = this.f6758f;
        c0148f0.i(mode);
        c0148f0.b();
    }
}
