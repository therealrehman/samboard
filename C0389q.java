package androidx.transition;

import android.view.View;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: androidx.transition.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0389q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f9485a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f9486b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f9487c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f9488d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f9489e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f9490f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f9491g;
    public final float h;

    public C0389q(View view) {
        this.f9485a = view.getTranslationX();
        this.f9486b = view.getTranslationY();
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        this.f9487c = androidx.core.view.M.l(view);
        this.f9488d = view.getScaleX();
        this.f9489e = view.getScaleY();
        this.f9490f = view.getRotationX();
        this.f9491g = view.getRotationY();
        this.h = view.getRotation();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0389q)) {
            return false;
        }
        C0389q c0389q = (C0389q) obj;
        return c0389q.f9485a == this.f9485a && c0389q.f9486b == this.f9486b && c0389q.f9487c == this.f9487c && c0389q.f9488d == this.f9488d && c0389q.f9489e == this.f9489e && c0389q.f9490f == this.f9490f && c0389q.f9491g == this.f9491g && c0389q.h == this.h;
    }

    public final int hashCode() {
        float f2 = this.f9485a;
        int iFloatToIntBits = (f2 != 0.0f ? Float.floatToIntBits(f2) : 0) * 31;
        float f7 = this.f9486b;
        int iFloatToIntBits2 = (iFloatToIntBits + (f7 != 0.0f ? Float.floatToIntBits(f7) : 0)) * 31;
        float f9 = this.f9487c;
        int iFloatToIntBits3 = (iFloatToIntBits2 + (f9 != 0.0f ? Float.floatToIntBits(f9) : 0)) * 31;
        float f10 = this.f9488d;
        int iFloatToIntBits4 = (iFloatToIntBits3 + (f10 != 0.0f ? Float.floatToIntBits(f10) : 0)) * 31;
        float f11 = this.f9489e;
        int iFloatToIntBits5 = (iFloatToIntBits4 + (f11 != 0.0f ? Float.floatToIntBits(f11) : 0)) * 31;
        float f12 = this.f9490f;
        int iFloatToIntBits6 = (iFloatToIntBits5 + (f12 != 0.0f ? Float.floatToIntBits(f12) : 0)) * 31;
        float f13 = this.f9491g;
        int iFloatToIntBits7 = (iFloatToIntBits6 + (f13 != 0.0f ? Float.floatToIntBits(f13) : 0)) * 31;
        float f14 = this.h;
        return iFloatToIntBits7 + (f14 != 0.0f ? Float.floatToIntBits(f14) : 0);
    }
}
