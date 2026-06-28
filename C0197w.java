package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.CheckedTextView;
import e.AbstractC0478a;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: androidx.appcompat.widget.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0197w extends CheckedTextView {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0200x f6867e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final C0188t f6868f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C0148f0 f6869g;
    public D h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0197w(Context context, AttributeSet attributeSet) {
        int resourceId;
        int resourceId2;
        super(context, attributeSet, R.attr.checkedTextViewStyle);
        Q1.a(context);
        P1.a(this, getContext());
        C0148f0 c0148f0 = new C0148f0(this);
        this.f6869g = c0148f0;
        c0148f0.f(attributeSet, R.attr.checkedTextViewStyle);
        c0148f0.b();
        C0188t c0188t = new C0188t(this);
        this.f6868f = c0188t;
        c0188t.d(attributeSet, R.attr.checkedTextViewStyle);
        this.f6867e = new C0200x(this);
        Context context2 = getContext();
        int[] iArr = AbstractC0478a.f10566l;
        S1 s1E = S1.e(context2, attributeSet, iArr, R.attr.checkedTextViewStyle, 0);
        TypedArray typedArray = s1E.f6522b;
        Context context3 = getContext();
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        androidx.core.view.T.d(this, context3, iArr, attributeSet, s1E.f6522b, R.attr.checkedTextViewStyle, 0);
        try {
            if (typedArray.hasValue(4) && (resourceId2 = typedArray.getResourceId(4, 0)) != 0) {
                try {
                    setCheckMarkDrawable(android.support.v4.media.session.f.y(getContext(), resourceId2));
                } catch (Resources.NotFoundException unused) {
                    if (typedArray.hasValue(1)) {
                        setCheckMarkDrawable(android.support.v4.media.session.f.y(getContext(), resourceId));
                    }
                }
            } else if (typedArray.hasValue(1) && (resourceId = typedArray.getResourceId(1, 0)) != 0) {
                setCheckMarkDrawable(android.support.v4.media.session.f.y(getContext(), resourceId));
            }
            if (typedArray.hasValue(6)) {
                setCheckMarkTintList(s1E.a(6));
            }
            if (typedArray.hasValue(7)) {
                setCheckMarkTintMode(AbstractC0183r0.b(typedArray.getInt(7, -1), null));
            }
            s1E.f();
            getEmojiTextViewHelper().c(attributeSet, R.attr.checkedTextViewStyle);
        } catch (Throwable th) {
            s1E.f();
            throw th;
        }
    }

    private D getEmojiTextViewHelper() {
        if (this.h == null) {
            this.h = new D(this);
        }
        return this.h;
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C0148f0 c0148f0 = this.f6869g;
        if (c0148f0 != null) {
            c0148f0.b();
        }
        C0188t c0188t = this.f6868f;
        if (c0188t != null) {
            c0188t.a();
        }
        C0200x c0200x = this.f6867e;
        if (c0200x != null) {
            c0200x.a();
        }
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return super.getCustomSelectionActionModeCallback();
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0188t c0188t = this.f6868f;
        if (c0188t != null) {
            return c0188t.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0188t c0188t = this.f6868f;
        if (c0188t != null) {
            return c0188t.c();
        }
        return null;
    }

    public ColorStateList getSupportCheckMarkTintList() {
        C0200x c0200x = this.f6867e;
        if (c0200x != null) {
            return c0200x.f6875b;
        }
        return null;
    }

    public PorterDuff.Mode getSupportCheckMarkTintMode() {
        C0200x c0200x = this.f6867e;
        if (c0200x != null) {
            return c0200x.f6876c;
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f6869g.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f6869g.e();
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        com.bumptech.glide.c.J(inputConnectionOnCreateInputConnection, editorInfo, this);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z9) {
        super.setAllCaps(z9);
        getEmojiTextViewHelper().d(z9);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0188t c0188t = this.f6868f;
        if (c0188t != null) {
            c0188t.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i5) {
        super.setBackgroundResource(i5);
        C0188t c0188t = this.f6868f;
        if (c0188t != null) {
            c0188t.f(i5);
        }
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(Drawable drawable) {
        super.setCheckMarkDrawable(drawable);
        C0200x c0200x = this.f6867e;
        if (c0200x != null) {
            if (c0200x.f6879f) {
                c0200x.f6879f = false;
            } else {
                c0200x.f6879f = true;
                c0200x.a();
            }
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        C0148f0 c0148f0 = this.f6869g;
        if (c0148f0 != null) {
            c0148f0.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        C0148f0 c0148f0 = this.f6869g;
        if (c0148f0 != null) {
            c0148f0.b();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(callback);
    }

    public void setEmojiCompatEnabled(boolean z9) {
        getEmojiTextViewHelper().e(z9);
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0188t c0188t = this.f6868f;
        if (c0188t != null) {
            c0188t.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0188t c0188t = this.f6868f;
        if (c0188t != null) {
            c0188t.i(mode);
        }
    }

    public void setSupportCheckMarkTintList(ColorStateList colorStateList) {
        C0200x c0200x = this.f6867e;
        if (c0200x != null) {
            c0200x.f6875b = colorStateList;
            c0200x.f6877d = true;
            c0200x.a();
        }
    }

    public void setSupportCheckMarkTintMode(PorterDuff.Mode mode) {
        C0200x c0200x = this.f6867e;
        if (c0200x != null) {
            c0200x.f6876c = mode;
            c0200x.f6878e = true;
            c0200x.a();
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        C0148f0 c0148f0 = this.f6869g;
        c0148f0.h(colorStateList);
        c0148f0.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        C0148f0 c0148f0 = this.f6869g;
        c0148f0.i(mode);
        c0148f0.b();
    }

    @Override // android.widget.TextView
    public final void setTextAppearance(Context context, int i5) {
        super.setTextAppearance(context, i5);
        C0148f0 c0148f0 = this.f6869g;
        if (c0148f0 != null) {
            c0148f0.g(context, i5);
        }
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i5) {
        setCheckMarkDrawable(android.support.v4.media.session.f.y(getContext(), i5));
    }
}
