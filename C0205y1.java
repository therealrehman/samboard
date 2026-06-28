package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* JADX INFO: renamed from: androidx.appcompat.widget.y1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0205y1 extends Drawable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Paint f6897a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ColorStateList f6898b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f6899c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f6900d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f6901e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final RectF f6902f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f6903g;
    public final C0164k1 h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final C0202x1 f6904i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ SeslProgressBar f6905j;

    public C0205y1(SeslProgressBar seslProgressBar, boolean z9, ColorStateList colorStateList) {
        this.f6905j = seslProgressBar;
        Paint paint = new Paint();
        this.f6897a = paint;
        this.f6899c = 255;
        this.f6902f = new RectF();
        this.h = new C0164k1(this, 1);
        this.f6904i = new C0202x1(this);
        this.f6900d = z9;
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        this.f6898b = colorStateList;
        int defaultColor = colorStateList.getDefaultColor();
        this.f6903g = defaultColor;
        paint.setColor(defaultColor);
        this.f6901e = 0;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Paint paint = this.f6897a;
        SeslProgressBar seslProgressBar = this.f6905j;
        paint.setStrokeWidth(seslProgressBar.f6602g);
        int alpha = paint.getAlpha();
        int i5 = this.f6899c;
        paint.setAlpha(((i5 + (i5 >>> 7)) * alpha) >>> 8);
        paint.setAntiAlias(true);
        RectF rectF = this.f6902f;
        int i7 = seslProgressBar.f6602g;
        int i9 = seslProgressBar.h;
        rectF.set((i7 / 2.0f) + i9, (i7 / 2.0f) + i9, (seslProgressBar.getWidth() - (seslProgressBar.f6602g / 2.0f)) - seslProgressBar.h, (seslProgressBar.getWidth() - (seslProgressBar.f6602g / 2.0f)) - seslProgressBar.h);
        int i10 = seslProgressBar.f6617x - seslProgressBar.f6615v;
        float f2 = i10 > 0 ? (this.f6901e - r0) / i10 : 0.0f;
        canvas.save();
        if (this.f6900d) {
            canvas.drawArc(rectF, 270.0f, 360.0f, false, paint);
        } else {
            canvas.drawArc(rectF, 270.0f, f2 * 360.0f, false, paint);
        }
        canvas.restore();
        paint.setAlpha(alpha);
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        return this.h;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        Paint paint = this.f6897a;
        if (paint.getXfermode() != null) {
            return -3;
        }
        int alpha = paint.getAlpha();
        if (alpha == 0) {
            return -2;
        }
        return alpha == 255 ? -1 : -3;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        boolean zOnStateChange = super.onStateChange(iArr);
        int colorForState = this.f6898b.getColorForState(iArr, this.f6903g);
        if (this.f6903g != colorForState) {
            this.f6903g = colorForState;
            this.f6897a.setColor(colorForState);
            invalidateSelf();
        }
        return zOnStateChange;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i5) {
        this.f6899c = i5;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.f6897a.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        super.setTintList(colorStateList);
        if (colorStateList != null) {
            this.f6898b = colorStateList;
            int defaultColor = colorStateList.getDefaultColor();
            this.f6903g = defaultColor;
            this.f6897a.setColor(defaultColor);
            invalidateSelf();
        }
    }
}
