package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.CheckBox;

/* JADX INFO: renamed from: androidx.appcompat.widget.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0194v extends CheckBox implements P.u {
    private D mAppCompatEmojiTextHelper;
    private final C0188t mBackgroundTintHelper;
    private final C0203y mCompoundButtonHelper;
    private final C0148f0 mTextHelper;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0194v(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5);
        Q1.a(context);
        P1.a(this, getContext());
        C0203y c0203y = new C0203y(this);
        this.mCompoundButtonHelper = c0203y;
        c0203y.b(attributeSet, i5);
        C0188t c0188t = new C0188t(this);
        this.mBackgroundTintHelper = c0188t;
        c0188t.d(attributeSet, i5);
        C0148f0 c0148f0 = new C0148f0(this);
        this.mTextHelper = c0148f0;
        c0148f0.f(attributeSet, i5);
        getEmojiTextViewHelper().c(attributeSet, i5);
    }

    private D getEmojiTextViewHelper() {
        if (this.mAppCompatEmojiTextHelper == null) {
            this.mAppCompatEmojiTextHelper = new D(this);
        }
        return this.mAppCompatEmojiTextHelper;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0188t c0188t = this.mBackgroundTintHelper;
        if (c0188t != null) {
            c0188t.a();
        }
        C0148f0 c0148f0 = this.mTextHelper;
        if (c0148f0 != null) {
            c0148f0.b();
        }
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        C0203y c0203y = this.mCompoundButtonHelper;
        if (c0203y != null) {
            c0203y.getClass();
        }
        return compoundPaddingLeft;
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0188t c0188t = this.mBackgroundTintHelper;
        if (c0188t != null) {
            return c0188t.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0188t c0188t = this.mBackgroundTintHelper;
        if (c0188t != null) {
            return c0188t.c();
        }
        return null;
    }

    @Override // P.u
    public ColorStateList getSupportButtonTintList() {
        C0203y c0203y = this.mCompoundButtonHelper;
        if (c0203y != null) {
            return c0203y.f6892b;
        }
        return null;
    }

    public PorterDuff.Mode getSupportButtonTintMode() {
        C0203y c0203y = this.mCompoundButtonHelper;
        if (c0203y != null) {
            return c0203y.f6893c;
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.mTextHelper.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.mTextHelper.e();
    }

    public boolean isEmojiCompatEnabled() {
        return getEmojiTextViewHelper().b();
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z9) {
        super.setAllCaps(z9);
        getEmojiTextViewHelper().d(z9);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0188t c0188t = this.mBackgroundTintHelper;
        if (c0188t != null) {
            c0188t.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i5) {
        super.setBackgroundResource(i5);
        C0188t c0188t = this.mBackgroundTintHelper;
        if (c0188t != null) {
            c0188t.f(i5);
        }
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        C0203y c0203y = this.mCompoundButtonHelper;
        if (c0203y != null) {
            if (c0203y.f6896f) {
                c0203y.f6896f = false;
            } else {
                c0203y.f6896f = true;
                c0203y.a();
            }
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        C0148f0 c0148f0 = this.mTextHelper;
        if (c0148f0 != null) {
            c0148f0.b();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        C0148f0 c0148f0 = this.mTextHelper;
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
        C0188t c0188t = this.mBackgroundTintHelper;
        if (c0188t != null) {
            c0188t.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0188t c0188t = this.mBackgroundTintHelper;
        if (c0188t != null) {
            c0188t.i(mode);
        }
    }

    @Override // P.u
    public void setSupportButtonTintList(ColorStateList colorStateList) {
        C0203y c0203y = this.mCompoundButtonHelper;
        if (c0203y != null) {
            c0203y.f6892b = colorStateList;
            c0203y.f6894d = true;
            c0203y.a();
        }
    }

    @Override // P.u
    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        C0203y c0203y = this.mCompoundButtonHelper;
        if (c0203y != null) {
            c0203y.f6893c = mode;
            c0203y.f6895e = true;
            c0203y.a();
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.mTextHelper.h(colorStateList);
        this.mTextHelper.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.mTextHelper.i(mode);
        this.mTextHelper.b();
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(int i5) {
        setButtonDrawable(android.support.v4.media.session.f.y(getContext(), i5));
    }
}
