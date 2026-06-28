package androidx.appcompat.widget;

import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;
import com.samsung.android.keyscafe.R;
import f.AbstractC0510a;

/* JADX INFO: renamed from: androidx.appcompat.widget.n1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0173n1 extends Drawable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Paint f6778a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Paint f6779b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ColorStateList f6780c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final int f6781d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f6782e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ValueAnimator f6783f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ValueAnimator f6784g;
    public boolean h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f6785i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final boolean f6786j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f6787k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f6788l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ AbstractC0176o1 f6789m;

    public C0173n1(AbstractC0176o1 abstractC0176o1, int i5, ColorStateList colorStateList, boolean z9) {
        this.f6789m = abstractC0176o1;
        Paint paint = new Paint(1);
        this.f6778a = paint;
        Paint paint2 = new Paint(1);
        this.f6779b = paint2;
        this.h = false;
        this.f6785i = 255;
        this.f6786j = false;
        this.f6788l = abstractC0176o1.getContext().getResources().getDimensionPixelSize(R.dimen.sesl_seekbar_thumb_stroke);
        this.f6782e = i5;
        this.f6781d = i5;
        this.f6780c = colorStateList;
        this.f6787k = colorStateList.getDefaultColor();
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        paint2.setStyle(style);
        paint.setColor(this.f6787k);
        paint2.setColor(abstractC0176o1.getContext().getResources().getColor(R.color.sesl_thumb_control_fill_color_activated));
        this.f6786j = z9;
        float f2 = i5;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(f2, 0.0f);
        this.f6783f = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(100L);
        this.f6783f.setInterpolator(new LinearInterpolator());
        this.f6783f.addUpdateListener(new C0170m1(this, 0));
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(0.0f, f2);
        this.f6784g = valueAnimatorOfFloat2;
        valueAnimatorOfFloat2.setDuration(300L);
        this.f6784g.setInterpolator(AbstractC0510a.f10752c);
        this.f6784g.addUpdateListener(new C0170m1(this, 1));
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Paint paint = this.f6778a;
        int alpha = paint.getAlpha();
        int i5 = this.f6785i;
        paint.setAlpha(((i5 + (i5 >>> 7)) * alpha) >>> 8);
        Paint paint2 = this.f6779b;
        int i7 = this.f6785i;
        paint2.setAlpha(((i7 + (i7 >>> 7)) * alpha) >>> 8);
        canvas.save();
        boolean z9 = this.f6786j;
        int i9 = this.f6788l;
        AbstractC0176o1 abstractC0176o1 = this.f6789m;
        if (z9) {
            float width = ((abstractC0176o1.getWidth() - abstractC0176o1.getPaddingLeft()) - abstractC0176o1.getPaddingRight()) / 2.0f;
            canvas.drawCircle(width, abstractC0176o1.f6791B0 - abstractC0176o1.getPaddingLeft(), this.f6782e, paint);
            canvas.drawCircle(width, abstractC0176o1.f6791B0 - abstractC0176o1.getPaddingLeft(), this.f6782e - i9, paint2);
        } else {
            canvas.drawCircle(abstractC0176o1.f6791B0, abstractC0176o1.getHeight() / 2.0f, this.f6782e, paint);
            canvas.drawCircle(abstractC0176o1.f6791B0, abstractC0176o1.getHeight() / 2.0f, this.f6782e - i9, paint2);
        }
        canvas.restore();
        paint.setAlpha(alpha);
        paint2.setAlpha(alpha);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicHeight() {
        return this.f6781d * 2;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getIntrinsicWidth() {
        return this.f6781d * 2;
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        Paint paint = this.f6778a;
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
        int colorForState = this.f6780c.getColorForState(iArr, this.f6787k);
        if (this.f6787k != colorForState) {
            this.f6787k = colorForState;
            this.f6778a.setColor(colorForState);
            invalidateSelf();
        }
        boolean z9 = false;
        boolean z10 = false;
        boolean z11 = false;
        for (int i5 : iArr) {
            if (i5 == 16842910) {
                z10 = true;
            } else if (i5 == 16842919) {
                z11 = true;
            }
        }
        if (z10 && z11) {
            z9 = true;
        }
        if (this.h != z9) {
            if (z9) {
                if (!this.f6783f.isRunning()) {
                    if (this.f6784g.isRunning()) {
                        this.f6784g.cancel();
                    }
                    this.f6783f.start();
                }
            } else if (!this.f6784g.isRunning()) {
                if (this.f6783f.isRunning()) {
                    this.f6783f.cancel();
                }
                this.f6784g.start();
            }
            this.h = z9;
        }
        return zOnStateChange;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i5) {
        this.f6785i = i5;
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.f6778a.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        super.setTintList(colorStateList);
        if (colorStateList != null) {
            this.f6780c = colorStateList;
            int colorForState = colorStateList.getColorForState(this.f6789m.getDrawableState(), this.f6787k);
            this.f6787k = colorForState;
            this.f6778a.setColor(colorForState);
            invalidateSelf();
        }
    }
}
