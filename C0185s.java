package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.AutoCompleteTextView;

/* JADX INFO: renamed from: androidx.appcompat.widget.s, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0185s extends AutoCompleteTextView {
    private final C mAppCompatEmojiEditTextHelper;
    private final C0188t mBackgroundTintHelper;
    private final C0148f0 mTextHelper;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0185s(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5);
        Q1.a(context);
        P1.a(this, getContext());
        C0188t c0188t = new C0188t(this);
        this.mBackgroundTintHelper = c0188t;
        c0188t.d(attributeSet, i5);
        C0148f0 c0148f0 = new C0148f0(this);
        this.mTextHelper = c0148f0;
        c0148f0.f(attributeSet, i5);
        c0148f0.b();
        C c5 = new C(this);
        this.mAppCompatEmojiEditTextHelper = c5;
        c5.b(attributeSet, i5);
        initEmojiKeyListener(c5);
    }

    @Override // android.widget.TextView, android.view.View
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

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return super.getCustomSelectionActionModeCallback();
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

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.mTextHelper.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.mTextHelper.e();
    }

    public void initEmojiKeyListener(C c5) {
        KeyListener keyListener = getKeyListener();
        c5.getClass();
        if (!(keyListener instanceof NumberKeyListener)) {
            boolean zIsFocusable = super.isFocusable();
            boolean zIsClickable = super.isClickable();
            boolean zIsLongClickable = super.isLongClickable();
            int inputType = super.getInputType();
            KeyListener keyListenerA = c5.a(keyListener);
            if (keyListenerA == keyListener) {
                return;
            }
            super.setKeyListener(keyListenerA);
            super.setRawInputType(inputType);
            super.setFocusable(zIsFocusable);
            super.setClickable(zIsClickable);
            super.setLongClickable(zIsLongClickable);
        }
    }

    public boolean isEmojiCompatEnabled() {
        return ((V.i) ((M3.h) this.mAppCompatEmojiEditTextHelper.f6417b.f3147f).f2059g).h;
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        com.bumptech.glide.c.J(inputConnectionOnCreateInputConnection, editorInfo, this);
        return this.mAppCompatEmojiEditTextHelper.c(inputConnectionOnCreateInputConnection, editorInfo);
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

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(callback);
    }

    @Override // android.widget.AutoCompleteTextView
    public void setDropDownBackgroundResource(int i5) {
        setDropDownBackgroundDrawable(android.support.v4.media.session.f.y(getContext(), i5));
    }

    public void setEmojiCompatEnabled(boolean z9) {
        this.mAppCompatEmojiEditTextHelper.d(z9);
    }

    @Override // android.widget.TextView
    public void setKeyListener(KeyListener keyListener) {
        super.setKeyListener(this.mAppCompatEmojiEditTextHelper.a(keyListener));
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

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.mTextHelper.h(colorStateList);
        this.mTextHelper.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.mTextHelper.i(mode);
        this.mTextHelper.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i5) {
        super.setTextAppearance(context, i5);
        C0148f0 c0148f0 = this.mTextHelper;
        if (c0148f0 != null) {
            c0148f0.g(context, i5);
        }
    }
}
