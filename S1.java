package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;

/* JADX INFO: loaded from: classes.dex */
public final class S1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f6521a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final TypedArray f6522b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public TypedValue f6523c;

    public S1(Context context, TypedArray typedArray) {
        this.f6521a = context;
        this.f6522b = typedArray;
    }

    public static S1 e(Context context, AttributeSet attributeSet, int[] iArr, int i5, int i7) {
        return new S1(context, context.obtainStyledAttributes(attributeSet, iArr, i5, i7));
    }

    public final ColorStateList a(int i5) {
        int resourceId;
        ColorStateList colorStateListF;
        TypedArray typedArray = this.f6522b;
        return (!typedArray.hasValue(i5) || (resourceId = typedArray.getResourceId(i5, 0)) == 0 || (colorStateListF = p0.a.f(this.f6521a, resourceId)) == null) ? typedArray.getColorStateList(i5) : colorStateListF;
    }

    public final Drawable b(int i5) {
        int resourceId;
        TypedArray typedArray = this.f6522b;
        return (!typedArray.hasValue(i5) || (resourceId = typedArray.getResourceId(i5, 0)) == 0) ? typedArray.getDrawable(i5) : android.support.v4.media.session.f.y(this.f6521a, resourceId);
    }

    public final Drawable c(int i5) {
        int resourceId;
        Drawable drawableD;
        if (!this.f6522b.hasValue(i5) || (resourceId = this.f6522b.getResourceId(i5, 0)) == 0) {
            return null;
        }
        C0206z c0206zA = C0206z.a();
        Context context = this.f6521a;
        synchronized (c0206zA) {
            drawableD = c0206zA.f6907a.d(context, resourceId, true);
        }
        return drawableD;
    }

    public final Typeface d(int i5, int i7, C0133a0 c0133a0) {
        int resourceId = this.f6522b.getResourceId(i5, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.f6523c == null) {
            this.f6523c = new TypedValue();
        }
        TypedValue typedValue = this.f6523c;
        ThreadLocal threadLocal = C.s.f322a;
        Context context = this.f6521a;
        if (context.isRestricted()) {
            return null;
        }
        return C.s.c(context, resourceId, typedValue, i7, c0133a0, true, false);
    }

    public final void f() {
        this.f6522b.recycle();
    }
}
