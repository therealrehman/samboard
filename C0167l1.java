package androidx.appcompat.widget;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.animation.PathInterpolator;
import f.AbstractC0510a;

/* JADX INFO: renamed from: androidx.appcompat.widget.l1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0167l1 extends Drawable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Paint f6744a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f6745b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ColorStateList f6746c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f6747d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ValueAnimator f6748e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ValueAnimator f6749f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f6750g;
    public final float h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float f6751i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f6752j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f6753k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final C0164k1 f6754l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ AbstractC0176o1 f6755m;

    public C0167l1(AbstractC0176o1 abstractC0176o1, float f2, float f7, ColorStateList colorStateList, boolean z9) {
        this.f6755m = abstractC0176o1;
        Paint paint = new Paint();
        this.f6744a = paint;
        this.f6747d = false;
        this.f6750g = 255;
        this.f6754l = new C0164k1(this, 0);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        this.f6746c = colorStateList;
        int defaultColor = colorStateList.getDefaultColor();
        this.f6752j = defaultColor;
        paint.setColor(defaultColor);
        paint.setStrokeWidth(f2);
        this.h = f2;
        this.f6751i = f7;
        this.f6745b = f2 / 2.0f;
        this.f6753k = z9;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f2, f7);
        this.f6748e = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(250L);
        ValueAnimator valueAnimator = this.f6748e;
        PathInterpolator pathInterpolator = AbstractC0510a.f10751b;
        valueAnimator.setInterpolator(pathInterpolator);
        this.f6748e.addUpdateListener(new C0161j1(this, 0));
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(f7, f2);
        this.f6749f = valueAnimatorOfFloat2;
        valueAnimatorOfFloat2.setDuration(250L);
        this.f6749f.setInterpolator(pathInterpolator);
        this.f6749f.addUpdateListener(new C0161j1(this, 1));
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Paint paint = this.f6744a;
        int alpha = paint.getAlpha();
        int i5 = this.f6750g;
        paint.setAlpha(((i5 + (i5 >>> 7)) * alpha) >>> 8);
        canvas.save();
        boolean z9 = this.f6753k;
        AbstractC0176o1 abstractC0176o1 = this.f6755m;
        if (z9) {
            float width = (abstractC0176o1.getWidth() - abstractC0176o1.getPaddingLeft()) - abstractC0176o1.getPaddingRight();
            float height = (abstractC0176o1.getHeight() - abstractC0176o1.getPaddingTop()) - abstractC0176o1.getPaddingBottom();
            float f2 = this.f6745b;
            float f7 = width / 2.0f;
            canvas.drawLine(f7, height - f2, f7, f2, paint);
        } else {
            float width2 = (abstractC0176o1.getWidth() - abstractC0176o1.getPaddingLeft()) - abstractC0176o1.getPaddingRight();
            float f9 = this.f6745b;
            canvas.drawLine(f9, abstractC0176o1.getHeight() / 2.0f, width2 - f9, abstractC0176o1.getHeight() / 2.0f, paint);
        }
        canvas.restore();
        paint.setAlpha(alpha);
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        return this.f6754l;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return (int) this.f6751i;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return (int) this.f6751i;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        Paint paint = this.f6744a;
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
        ValueAnimator valueAnimator = this.f6749f;
        ValueAnimator valueAnimator2 = this.f6748e;
        boolean zOnStateChange = super.onStateChange(iArr);
        int colorForState = this.f6746c.getColorForState(iArr, this.f6752j);
        if (this.f6752j != colorForState) {
            this.f6752j = colorForState;
            this.f6744a.setColor(colorForState);
            invalidateSelf();
        }
        boolean z9 = false;
        boolean z10 = false;
        for (int i5 : iArr) {
            if (i5 == 16842910) {
                z9 = true;
            } else if (i5 == 16842919) {
                z10 = true;
            }
        }
        boolean z11 = z9 && z10;
        if (this.f6747d != z11) {
            float f2 = this.f6751i;
            float f7 = this.h;
            if (z11) {
                if (!valueAnimator2.isRunning()) {
                    if (valueAnimator.isRunning()) {
                        valueAnimator.cancel();
                    }
                    valueAnimator2.setFloatValues(f7, f2);
                    valueAnimator2.start();
                }
            } else if (!valueAnimator.isRunning()) {
                if (valueAnimator2.isRunning()) {
                    valueAnimator2.cancel();
                }
                valueAnimator.setFloatValues(f2, f7);
                valueAnimator.start();
            }
            this.f6747d = z11;
        }
        return zOnStateChange;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i5) {
        this.f6750g = i5;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.f6744a.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        super.setTintList(colorStateList);
        if (colorStateList != null) {
            this.f6746c = colorStateList;
            int defaultColor = colorStateList.getDefaultColor();
            this.f6752j = defaultColor;
            this.f6744a.setColor(defaultColor);
            invalidateSelf();
        }
    }
}
