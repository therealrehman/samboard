package androidx.appcompat.widget;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

/* JADX INFO: renamed from: androidx.appcompat.widget.k1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0164k1 extends Drawable.ConstantState {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f6733a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f6734b;

    public /* synthetic */ C0164k1(int i5, Object obj) {
        this.f6733a = i5;
        this.f6734b = obj;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public boolean canApplyTheme() {
        switch (this.f6733a) {
            case 2:
                return ((Drawable.ConstantState) this.f6734b).canApplyTheme();
            default:
                return super.canApplyTheme();
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final int getChangingConfigurations() {
        switch (this.f6733a) {
            case 0:
                return 0;
            case 1:
                return 0;
            case 2:
                return ((Drawable.ConstantState) this.f6734b).getChangingConfigurations();
            default:
                return 0;
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public final Drawable newDrawable() {
        switch (this.f6733a) {
            case 0:
                return (C0167l1) this.f6734b;
            case 1:
                return (C0205y1) this.f6734b;
            case 2:
                androidx.vectordrawable.graphics.drawable.f fVar = new androidx.vectordrawable.graphics.drawable.f(null);
                Drawable drawableNewDrawable = ((Drawable.ConstantState) this.f6734b).newDrawable();
                fVar.f9531e = drawableNewDrawable;
                drawableNewDrawable.setCallback(fVar.f9530j);
                return fVar;
            default:
                return new i2.b(this);
        }
    }

    public /* synthetic */ C0164k1(Drawable drawable, int i5) {
        this.f6733a = i5;
        this.f6734b = drawable;
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable(Resources resources) {
        switch (this.f6733a) {
            case 2:
                androidx.vectordrawable.graphics.drawable.f fVar = new androidx.vectordrawable.graphics.drawable.f(null);
                Drawable drawableNewDrawable = ((Drawable.ConstantState) this.f6734b).newDrawable(resources);
                fVar.f9531e = drawableNewDrawable;
                drawableNewDrawable.setCallback(fVar.f9530j);
                return fVar;
            case 3:
                return new i2.b(this);
            default:
                return super.newDrawable(resources);
        }
    }

    @Override // android.graphics.drawable.Drawable.ConstantState
    public Drawable newDrawable(Resources resources, Resources.Theme theme) {
        switch (this.f6733a) {
            case 2:
                androidx.vectordrawable.graphics.drawable.f fVar = new androidx.vectordrawable.graphics.drawable.f(null);
                Drawable drawableNewDrawable = ((Drawable.ConstantState) this.f6734b).newDrawable(resources, theme);
                fVar.f9531e = drawableNewDrawable;
                drawableNewDrawable.setCallback(fVar.f9530j);
                return fVar;
            default:
                return super.newDrawable(resources, theme);
        }
    }
}
