package androidx.appcompat.widget;

import F8.C0040j;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import e.AbstractC0478a;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: androidx.appcompat.widget.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0188t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f6851a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0206z f6852b = C0206z.a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public C0040j f6853c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public C0040j f6854d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public C0040j f6855e;

    public C0188t(View view) {
        this.f6851a = view;
    }

    public final void a() {
        View view = this.f6851a;
        Drawable background = view.getBackground();
        if (background != null) {
            if (this.f6853c != null) {
                if (this.f6855e == null) {
                    this.f6855e = new C0040j();
                }
                C0040j c0040j = this.f6855e;
                c0040j.f907c = null;
                c0040j.f906b = false;
                c0040j.f908d = null;
                c0040j.f905a = false;
                WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
                ColorStateList colorStateListG = androidx.core.view.M.g(view);
                if (colorStateListG != null) {
                    c0040j.f906b = true;
                    c0040j.f907c = colorStateListG;
                }
                PorterDuff.Mode modeH = androidx.core.view.M.h(view);
                if (modeH != null) {
                    c0040j.f905a = true;
                    c0040j.f908d = modeH;
                }
                if (c0040j.f906b || c0040j.f905a) {
                    C0206z.d(background, c0040j, view.getDrawableState());
                    return;
                }
            }
            C0040j c0040j2 = this.f6854d;
            if (c0040j2 != null) {
                C0206z.d(background, c0040j2, view.getDrawableState());
                return;
            }
            C0040j c0040j3 = this.f6853c;
            if (c0040j3 != null) {
                C0206z.d(background, c0040j3, view.getDrawableState());
            }
        }
    }

    public final ColorStateList b() {
        C0040j c0040j = this.f6854d;
        if (c0040j != null) {
            return (ColorStateList) c0040j.f907c;
        }
        return null;
    }

    public final PorterDuff.Mode c() {
        C0040j c0040j = this.f6854d;
        if (c0040j != null) {
            return (PorterDuff.Mode) c0040j.f908d;
        }
        return null;
    }

    public final void d(AttributeSet attributeSet, int i5) {
        View view = this.f6851a;
        Context context = view.getContext();
        int[] iArr = AbstractC0478a.f10555F;
        S1 s1E = S1.e(context, attributeSet, iArr, i5, 0);
        TypedArray typedArray = s1E.f6522b;
        View view2 = this.f6851a;
        Context context2 = view2.getContext();
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        androidx.core.view.T.d(view2, context2, iArr, attributeSet, s1E.f6522b, i5, 0);
        try {
            if (typedArray.hasValue(0)) {
                typedArray.getResourceId(0, -1);
                C0206z c0206z = this.f6852b;
                view.getContext();
                synchronized (c0206z) {
                    synchronized (c0206z.f6907a) {
                    }
                }
            }
            if (typedArray.hasValue(1)) {
                androidx.core.view.M.q(view, s1E.a(1));
            }
            if (typedArray.hasValue(2)) {
                androidx.core.view.M.r(view, AbstractC0183r0.b(typedArray.getInt(2, -1), null));
            }
            s1E.f();
        } catch (Throwable th) {
            s1E.f();
            throw th;
        }
    }

    public final void e() {
        g(null);
        a();
    }

    public final void f(int i5) {
        C0206z c0206z = this.f6852b;
        if (c0206z != null) {
            this.f6851a.getContext();
            synchronized (c0206z) {
                synchronized (c0206z.f6907a) {
                }
            }
        }
        g(null);
        a();
    }

    public final void g(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.f6853c == null) {
                this.f6853c = new C0040j();
            }
            C0040j c0040j = this.f6853c;
            c0040j.f907c = colorStateList;
            c0040j.f906b = true;
        } else {
            this.f6853c = null;
        }
        a();
    }

    public final void h(ColorStateList colorStateList) {
        if (this.f6854d == null) {
            this.f6854d = new C0040j();
        }
        C0040j c0040j = this.f6854d;
        c0040j.f907c = colorStateList;
        c0040j.f906b = true;
        a();
    }

    public final void i(PorterDuff.Mode mode) {
        if (this.f6854d == null) {
            this.f6854d = new C0040j();
        }
        C0040j c0040j = this.f6854d;
        c0040j.f908d = mode;
        c0040j.f905a = true;
        a();
    }
}
