package androidx.appcompat.widget;

import F8.C0040j;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.widget.TextView;
import e.AbstractC0478a;
import h6.AbstractC0582a;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: androidx.appcompat.widget.f0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0148f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextView f6695a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public C0040j f6696b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public C0040j f6697c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public C0040j f6698d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public C0040j f6699e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public C0040j f6700f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public C0040j f6701g;
    public C0040j h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C0166l0 f6702i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f6703j = 0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f6704k = -1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Typeface f6705l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f6706m;

    public C0148f0(TextView textView) {
        this.f6695a = textView;
        this.f6702i = new C0166l0(textView);
    }

    public static C0040j c(Context context, C0206z c0206z, int i5) {
        synchronized (c0206z) {
            synchronized (c0206z.f6907a) {
            }
        }
        return null;
    }

    public final void a(Drawable drawable, C0040j c0040j) {
        if (drawable == null || c0040j == null) {
            return;
        }
        C0206z.d(drawable, c0040j, this.f6695a.getDrawableState());
    }

    public final void b() {
        C0040j c0040j = this.f6696b;
        TextView textView = this.f6695a;
        if (c0040j != null || this.f6697c != null || this.f6698d != null || this.f6699e != null) {
            Drawable[] compoundDrawables = textView.getCompoundDrawables();
            a(compoundDrawables[0], this.f6696b);
            a(compoundDrawables[1], this.f6697c);
            a(compoundDrawables[2], this.f6698d);
            a(compoundDrawables[3], this.f6699e);
        }
        if (this.f6700f == null && this.f6701g == null) {
            return;
        }
        Drawable[] drawableArrA = AbstractC0136b0.a(textView);
        a(drawableArrA[0], this.f6700f);
        a(drawableArrA[2], this.f6701g);
    }

    public final ColorStateList d() {
        C0040j c0040j = this.h;
        if (c0040j != null) {
            return (ColorStateList) c0040j.f907c;
        }
        return null;
    }

    public final PorterDuff.Mode e() {
        C0040j c0040j = this.h;
        if (c0040j != null) {
            return (PorterDuff.Mode) c0040j.f908d;
        }
        return null;
    }

    public final void f(AttributeSet attributeSet, int i5) {
        String string;
        String string2;
        boolean z9;
        boolean z10;
        boolean z11;
        int i7;
        Drawable drawableB;
        int i9;
        Paint.FontMetricsInt fontMetricsInt;
        int i10;
        ColorStateList colorStateList;
        int resourceId;
        int i11;
        int resourceId2;
        TextView textView = this.f6695a;
        Context context = textView.getContext();
        C0206z c0206zA = C0206z.a();
        int[] iArr = AbstractC0478a.h;
        S1 s1E = S1.e(context, attributeSet, iArr, i5, 0);
        Context context2 = textView.getContext();
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        androidx.core.view.T.d(textView, context2, iArr, attributeSet, s1E.f6522b, i5, 0);
        TypedArray typedArray = s1E.f6522b;
        int resourceId3 = typedArray.getResourceId(0, -1);
        if (typedArray.hasValue(3)) {
            c(context, c0206zA, typedArray.getResourceId(3, 0));
            this.f6696b = null;
        }
        if (typedArray.hasValue(1)) {
            c(context, c0206zA, typedArray.getResourceId(1, 0));
            this.f6697c = null;
        }
        if (typedArray.hasValue(4)) {
            c(context, c0206zA, typedArray.getResourceId(4, 0));
            this.f6698d = null;
        }
        if (typedArray.hasValue(2)) {
            c(context, c0206zA, typedArray.getResourceId(2, 0));
            this.f6699e = null;
        }
        if (typedArray.hasValue(5)) {
            c(context, c0206zA, typedArray.getResourceId(5, 0));
            this.f6700f = null;
        }
        if (typedArray.hasValue(6)) {
            c(context, c0206zA, typedArray.getResourceId(6, 0));
            this.f6701g = null;
        }
        s1E.f();
        boolean z12 = textView.getTransformationMethod() instanceof PasswordTransformationMethod;
        int[] iArr2 = AbstractC0478a.f10552C;
        if (resourceId3 != -1) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(resourceId3, iArr2);
            S1 s12 = new S1(context, typedArrayObtainStyledAttributes);
            if (z12 || !typedArrayObtainStyledAttributes.hasValue(14)) {
                z9 = false;
                z10 = false;
            } else {
                z10 = typedArrayObtainStyledAttributes.getBoolean(14, false);
                z9 = true;
            }
            j(context, s12);
            string2 = typedArrayObtainStyledAttributes.hasValue(15) ? typedArrayObtainStyledAttributes.getString(15) : null;
            string = typedArrayObtainStyledAttributes.hasValue(13) ? typedArrayObtainStyledAttributes.getString(13) : null;
            s12.f();
        } else {
            string = null;
            string2 = null;
            z9 = false;
            z10 = false;
        }
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr2, i5, 0);
        S1 s13 = new S1(context, typedArrayObtainStyledAttributes2);
        if (z12 || !typedArrayObtainStyledAttributes2.hasValue(14)) {
            z11 = z10;
        } else {
            z11 = typedArrayObtainStyledAttributes2.getBoolean(14, false);
            z9 = true;
        }
        if (typedArrayObtainStyledAttributes2.hasValue(15)) {
            string2 = typedArrayObtainStyledAttributes2.getString(15);
        }
        if (typedArrayObtainStyledAttributes2.hasValue(13)) {
            string = typedArrayObtainStyledAttributes2.getString(13);
        }
        if (typedArrayObtainStyledAttributes2.hasValue(0) && typedArrayObtainStyledAttributes2.getDimensionPixelSize(0, -1) == 0) {
            textView.setTextSize(0, 0.0f);
        }
        j(context, s13);
        s13.f();
        if (!z12 && z9) {
            textView.setAllCaps(z11);
        }
        Typeface typeface = this.f6705l;
        if (typeface != null) {
            if (this.f6704k == -1) {
                textView.setTypeface(typeface, this.f6703j);
            } else {
                textView.setTypeface(typeface);
            }
        }
        if (string != null) {
            AbstractC0142d0.d(textView, string);
        }
        if (string2 != null) {
            AbstractC0139c0.b(textView, AbstractC0139c0.a(string2));
        }
        int[] iArr3 = AbstractC0478a.f10563i;
        C0166l0 c0166l0 = this.f6702i;
        Context context3 = c0166l0.f6743i;
        TypedArray typedArrayObtainStyledAttributes3 = context3.obtainStyledAttributes(attributeSet, iArr3, i5, 0);
        TextView textView2 = c0166l0.h;
        androidx.core.view.T.d(textView2, textView2.getContext(), iArr3, attributeSet, typedArrayObtainStyledAttributes3, i5, 0);
        if (typedArrayObtainStyledAttributes3.hasValue(5)) {
            c0166l0.f6736a = typedArrayObtainStyledAttributes3.getInt(5, 0);
        }
        float dimension = typedArrayObtainStyledAttributes3.hasValue(4) ? typedArrayObtainStyledAttributes3.getDimension(4, -1.0f) : -1.0f;
        float dimension2 = typedArrayObtainStyledAttributes3.hasValue(2) ? typedArrayObtainStyledAttributes3.getDimension(2, -1.0f) : -1.0f;
        float dimension3 = typedArrayObtainStyledAttributes3.hasValue(1) ? typedArrayObtainStyledAttributes3.getDimension(1, -1.0f) : -1.0f;
        if (typedArrayObtainStyledAttributes3.hasValue(3) && (resourceId2 = typedArrayObtainStyledAttributes3.getResourceId(3, 0)) > 0) {
            TypedArray typedArrayObtainTypedArray = typedArrayObtainStyledAttributes3.getResources().obtainTypedArray(resourceId2);
            int length = typedArrayObtainTypedArray.length();
            int[] iArr4 = new int[length];
            if (length > 0) {
                for (int i12 = 0; i12 < length; i12++) {
                    iArr4[i12] = typedArrayObtainTypedArray.getDimensionPixelSize(i12, -1);
                }
                c0166l0.f6741f = C0166l0.a(iArr4);
                c0166l0.c();
            }
            typedArrayObtainTypedArray.recycle();
        }
        typedArrayObtainStyledAttributes3.recycle();
        if (!c0166l0.d()) {
            c0166l0.f6736a = 0;
        } else if (c0166l0.f6736a == 1) {
            if (!c0166l0.f6742g) {
                DisplayMetrics displayMetrics = context3.getResources().getDisplayMetrics();
                if (dimension2 == -1.0f) {
                    i11 = 2;
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                } else {
                    i11 = 2;
                }
                if (dimension3 == -1.0f) {
                    dimension3 = TypedValue.applyDimension(i11, 112.0f, displayMetrics);
                }
                if (dimension == -1.0f) {
                    dimension = 1.0f;
                }
                c0166l0.e(dimension2, dimension3, dimension);
            }
            c0166l0.b();
        }
        Method method = h2.f6719a;
        if (c0166l0.f6736a != 0) {
            int[] iArr5 = c0166l0.f6741f;
            if (iArr5.length > 0) {
                if (AbstractC0142d0.a(textView) != -1.0f) {
                    AbstractC0142d0.b(textView, Math.round(c0166l0.f6739d), Math.round(c0166l0.f6740e), Math.round(c0166l0.f6738c), 0);
                } else {
                    AbstractC0142d0.c(textView, iArr5, 0);
                }
            }
        }
        TypedArray typedArrayObtainStyledAttributes4 = context.obtainStyledAttributes(attributeSet, iArr3);
        int resourceId4 = typedArrayObtainStyledAttributes4.getResourceId(8, -1);
        if (resourceId4 != -1) {
            drawableB = c0206zA.b(context, resourceId4);
            i7 = 13;
        } else {
            i7 = 13;
            drawableB = null;
        }
        int resourceId5 = typedArrayObtainStyledAttributes4.getResourceId(i7, -1);
        Drawable drawableB2 = resourceId5 != -1 ? c0206zA.b(context, resourceId5) : null;
        int resourceId6 = typedArrayObtainStyledAttributes4.getResourceId(9, -1);
        Drawable drawableB3 = resourceId6 != -1 ? c0206zA.b(context, resourceId6) : null;
        int resourceId7 = typedArrayObtainStyledAttributes4.getResourceId(6, -1);
        Drawable drawableB4 = resourceId7 != -1 ? c0206zA.b(context, resourceId7) : null;
        int resourceId8 = typedArrayObtainStyledAttributes4.getResourceId(10, -1);
        Drawable drawableB5 = resourceId8 != -1 ? c0206zA.b(context, resourceId8) : null;
        int resourceId9 = typedArrayObtainStyledAttributes4.getResourceId(7, -1);
        Drawable drawableB6 = resourceId9 != -1 ? c0206zA.b(context, resourceId9) : null;
        if (drawableB5 != null || drawableB6 != null) {
            Drawable[] drawableArrA = AbstractC0136b0.a(textView);
            if (drawableB5 == null) {
                drawableB5 = drawableArrA[0];
            }
            if (drawableB2 == null) {
                drawableB2 = drawableArrA[1];
            }
            if (drawableB6 == null) {
                drawableB6 = drawableArrA[2];
            }
            if (drawableB4 == null) {
                drawableB4 = drawableArrA[3];
            }
            AbstractC0136b0.b(textView, drawableB5, drawableB2, drawableB6, drawableB4);
        } else if (drawableB != null || drawableB2 != null || drawableB3 != null || drawableB4 != null) {
            Drawable[] drawableArrA2 = AbstractC0136b0.a(textView);
            Drawable drawable = drawableArrA2[0];
            if (drawable == null && drawableArrA2[2] == null) {
                Drawable[] compoundDrawables = textView.getCompoundDrawables();
                if (drawableB == null) {
                    drawableB = compoundDrawables[0];
                }
                if (drawableB2 == null) {
                    drawableB2 = compoundDrawables[1];
                }
                if (drawableB3 == null) {
                    drawableB3 = compoundDrawables[2];
                }
                if (drawableB4 == null) {
                    drawableB4 = compoundDrawables[3];
                }
                textView.setCompoundDrawablesWithIntrinsicBounds(drawableB, drawableB2, drawableB3, drawableB4);
            } else {
                if (drawableB2 == null) {
                    drawableB2 = drawableArrA2[1];
                }
                Drawable drawable2 = drawableArrA2[2];
                if (drawableB4 == null) {
                    drawableB4 = drawableArrA2[3];
                }
                AbstractC0136b0.b(textView, drawable, drawableB2, drawable2, drawableB4);
            }
        }
        if (typedArrayObtainStyledAttributes4.hasValue(11)) {
            if (!typedArrayObtainStyledAttributes4.hasValue(11) || (resourceId = typedArrayObtainStyledAttributes4.getResourceId(11, 0)) == 0 || (colorStateList = C.s.a(context.getResources(), resourceId, context.getTheme())) == null) {
                colorStateList = typedArrayObtainStyledAttributes4.getColorStateList(11);
            }
            P.q.f(textView, colorStateList);
        }
        if (typedArrayObtainStyledAttributes4.hasValue(12)) {
            i9 = -1;
            fontMetricsInt = null;
            P.q.g(textView, AbstractC0183r0.b(typedArrayObtainStyledAttributes4.getInt(12, -1), null));
        } else {
            i9 = -1;
            fontMetricsInt = null;
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes4.getDimensionPixelSize(15, i9);
        int dimensionPixelSize2 = typedArrayObtainStyledAttributes4.getDimensionPixelSize(18, i9);
        int dimensionPixelSize3 = typedArrayObtainStyledAttributes4.getDimensionPixelSize(19, i9);
        typedArrayObtainStyledAttributes4.recycle();
        if (dimensionPixelSize != i9) {
            AbstractC0582a.n(dimensionPixelSize);
            P.s.d(textView, dimensionPixelSize);
        }
        if (dimensionPixelSize2 != i9) {
            AbstractC0582a.n(dimensionPixelSize2);
            Paint.FontMetricsInt fontMetricsInt2 = textView.getPaint().getFontMetricsInt();
            int i13 = textView.getIncludeFontPadding() ? fontMetricsInt2.bottom : fontMetricsInt2.descent;
            if (dimensionPixelSize2 > Math.abs(i13)) {
                textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), dimensionPixelSize2 - i13);
            }
            i10 = -1;
        } else {
            i10 = i9;
        }
        if (dimensionPixelSize3 != i10) {
            AbstractC0582a.n(dimensionPixelSize3);
            if (dimensionPixelSize3 != textView.getPaint().getFontMetricsInt(fontMetricsInt)) {
                textView.setLineSpacing(dimensionPixelSize3 - r0, 1.0f);
            }
        }
    }

    public final void g(Context context, int i5) {
        String string;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i5, AbstractC0478a.f10552C);
        S1 s12 = new S1(context, typedArrayObtainStyledAttributes);
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(14);
        TextView textView = this.f6695a;
        if (zHasValue) {
            textView.setAllCaps(typedArrayObtainStyledAttributes.getBoolean(14, false));
        }
        if (typedArrayObtainStyledAttributes.hasValue(0) && typedArrayObtainStyledAttributes.getDimensionPixelSize(0, -1) == 0) {
            textView.setTextSize(0, 0.0f);
        }
        j(context, s12);
        if (typedArrayObtainStyledAttributes.hasValue(13) && (string = typedArrayObtainStyledAttributes.getString(13)) != null) {
            AbstractC0142d0.d(textView, string);
        }
        s12.f();
        Typeface typeface = this.f6705l;
        if (typeface != null) {
            textView.setTypeface(typeface, this.f6703j);
        }
    }

    public final void h(ColorStateList colorStateList) {
        if (this.h == null) {
            this.h = new C0040j();
        }
        C0040j c0040j = this.h;
        c0040j.f907c = colorStateList;
        c0040j.f906b = colorStateList != null;
        this.f6696b = c0040j;
        this.f6697c = c0040j;
        this.f6698d = c0040j;
        this.f6699e = c0040j;
        this.f6700f = c0040j;
        this.f6701g = c0040j;
    }

    public final void i(PorterDuff.Mode mode) {
        if (this.h == null) {
            this.h = new C0040j();
        }
        C0040j c0040j = this.h;
        c0040j.f908d = mode;
        c0040j.f905a = mode != null;
        this.f6696b = c0040j;
        this.f6697c = c0040j;
        this.f6698d = c0040j;
        this.f6699e = c0040j;
        this.f6700f = c0040j;
        this.f6701g = c0040j;
    }

    public final void j(Context context, S1 s12) {
        String string;
        int i5 = this.f6703j;
        TypedArray typedArray = s12.f6522b;
        this.f6703j = typedArray.getInt(2, i5);
        int i7 = typedArray.getInt(11, -1);
        this.f6704k = i7;
        if (i7 != -1) {
            this.f6703j &= 2;
        }
        if (!typedArray.hasValue(10) && !typedArray.hasValue(12)) {
            if (typedArray.hasValue(1)) {
                this.f6706m = false;
                int i9 = typedArray.getInt(1, 1);
                if (i9 == 1) {
                    this.f6705l = Typeface.SANS_SERIF;
                    return;
                } else if (i9 == 2) {
                    this.f6705l = Typeface.SERIF;
                    return;
                } else {
                    if (i9 != 3) {
                        return;
                    }
                    this.f6705l = Typeface.MONOSPACE;
                    return;
                }
            }
            return;
        }
        this.f6705l = null;
        int i10 = typedArray.hasValue(12) ? 12 : 10;
        int i11 = this.f6704k;
        int i12 = this.f6703j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceD = s12.d(i10, this.f6703j, new C0133a0(this, i11, i12, new WeakReference(this.f6695a)));
                if (typefaceD != null) {
                    if (this.f6704k != -1) {
                        this.f6705l = AbstractC0145e0.a(Typeface.create(typefaceD, 0), this.f6704k, (this.f6703j & 2) != 0);
                    } else {
                        this.f6705l = typefaceD;
                    }
                }
                this.f6706m = this.f6705l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.f6705l != null || (string = typedArray.getString(i10)) == null) {
            return;
        }
        if (this.f6704k != -1) {
            this.f6705l = AbstractC0145e0.a(Typeface.create(string, 0), this.f6704k, (this.f6703j & 2) != 0);
        } else {
            this.f6705l = Typeface.create(string, this.f6703j);
        }
    }
}
