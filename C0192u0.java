package androidx.appcompat.widget;

import android.graphics.Canvas;
import i.C0590a;

/* JADX INFO: renamed from: androidx.appcompat.widget.u0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0192u0 extends C0590a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6859e;

    @Override // i.C0590a, android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        if (this.f6859e) {
            super.draw(canvas);
        }
    }

    @Override // i.C0590a, android.graphics.drawable.Drawable
    public final void setHotspot(float f2, float f7) {
        if (this.f6859e) {
            super.setHotspot(f2, f7);
        }
    }

    @Override // i.C0590a, android.graphics.drawable.Drawable
    public final void setHotspotBounds(int i5, int i7, int i9, int i10) {
        if (this.f6859e) {
            super.setHotspotBounds(i5, i7, i9, i10);
        }
    }

    @Override // i.C0590a, android.graphics.drawable.Drawable
    public final boolean setState(int[] iArr) {
        if (this.f6859e) {
            return super.setState(iArr);
        }
        return false;
    }

    @Override // i.C0590a, android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z9, boolean z10) {
        if (this.f6859e) {
            return super.setVisible(z9, z10);
        }
        return false;
    }
}
