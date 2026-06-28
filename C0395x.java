package androidx.transition;

import android.graphics.Rect;

/* JADX INFO: renamed from: androidx.transition.x, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0395x extends S {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9505a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Rect f9506b;

    public /* synthetic */ C0395x(int i5, Rect rect) {
        this.f9505a = i5;
        this.f9506b = rect;
    }

    @Override // androidx.transition.S
    public final Rect a() {
        switch (this.f9505a) {
            case 0:
                return this.f9506b;
            default:
                Rect rect = this.f9506b;
                if (rect == null || rect.isEmpty()) {
                    return null;
                }
                return rect;
        }
    }
}
