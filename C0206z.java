package androidx.appcompat.widget;

import F8.C0040j;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.Log;

/* JADX INFO: renamed from: androidx.appcompat.widget.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0206z {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static C0206z f6906b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public P0 f6907a;

    static {
        PorterDuff.Mode mode = PorterDuff.Mode.SRC_IN;
    }

    public static synchronized C0206z a() {
        try {
            if (f6906b == null) {
                c();
            }
        } catch (Throwable th) {
            throw th;
        }
        return f6906b;
    }

    public static synchronized void c() {
        if (f6906b == null) {
            C0206z c0206z = new C0206z();
            f6906b = c0206z;
            c0206z.f6907a = P0.a();
            P0 p02 = f6906b.f6907a;
            synchronized (p02) {
                p02.getClass();
            }
        }
    }

    public static void d(Drawable drawable, C0040j c0040j, int[] iArr) {
        PorterDuff.Mode mode = P0.f6504d;
        int[] state = drawable.getState();
        Rect rect = AbstractC0183r0.f6838a;
        if (drawable.mutate() != drawable) {
            Log.d("ResourceManagerInternal", "Mutated drawable is not the same instance as the input.");
            return;
        }
        if ((drawable instanceof LayerDrawable) && drawable.isStateful()) {
            drawable.setState(new int[0]);
            drawable.setState(state);
        }
        boolean z9 = c0040j.f906b;
        if (!z9 && !c0040j.f905a) {
            drawable.clearColorFilter();
            return;
        }
        PorterDuffColorFilter porterDuffColorFilterE = null;
        ColorStateList colorStateList = z9 ? (ColorStateList) c0040j.f907c : null;
        PorterDuff.Mode mode2 = c0040j.f905a ? (PorterDuff.Mode) c0040j.f908d : P0.f6504d;
        if (colorStateList != null && mode2 != null) {
            porterDuffColorFilterE = P0.e(colorStateList.getColorForState(iArr, 0), mode2);
        }
        drawable.setColorFilter(porterDuffColorFilterE);
    }

    public final synchronized Drawable b(Context context, int i5) {
        return this.f6907a.c(context, i5);
    }
}
