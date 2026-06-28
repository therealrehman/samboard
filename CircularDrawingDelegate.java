package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.google.android.material.color.MaterialColors;

/* JADX INFO: loaded from: classes.dex */
final class CircularDrawingDelegate extends DrawingDelegate<CircularProgressIndicatorSpec> {
    private float adjustedRadius;
    private int arcDirectionFactor;
    private float displayedCornerRadius;
    private float displayedTrackThickness;

    public CircularDrawingDelegate(CircularProgressIndicatorSpec circularProgressIndicatorSpec) {
        super(circularProgressIndicatorSpec);
        this.arcDirectionFactor = 1;
    }

    private void drawRoundedEnd(Canvas canvas, Paint paint, float f2, float f7, float f9) {
        canvas.save();
        canvas.rotate(f9);
        float f10 = this.adjustedRadius;
        float f11 = f2 / 2.0f;
        canvas.drawRoundRect(new RectF(f10 - f11, f7, f10 + f11, -f7), f7, f7, paint);
        canvas.restore();
    }

    private int getSize() {
        S s8 = this.spec;
        return (((CircularProgressIndicatorSpec) s8).indicatorInset * 2) + ((CircularProgressIndicatorSpec) s8).indicatorSize;
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public void adjustCanvas(Canvas canvas, Rect rect, float f2) {
        float fWidth = rect.width() / getPreferredWidth();
        float fHeight = rect.height() / getPreferredHeight();
        S s8 = this.spec;
        float f7 = (((CircularProgressIndicatorSpec) s8).indicatorSize / 2.0f) + ((CircularProgressIndicatorSpec) s8).indicatorInset;
        canvas.translate((f7 * fWidth) + rect.left, (f7 * fHeight) + rect.top);
        canvas.scale(fWidth, fHeight);
        canvas.rotate(-90.0f);
        float f9 = -f7;
        canvas.clipRect(f9, f9, f7, f7);
        this.arcDirectionFactor = ((CircularProgressIndicatorSpec) this.spec).indicatorDirection == 0 ? 1 : -1;
        this.displayedTrackThickness = ((CircularProgressIndicatorSpec) r8).trackThickness * f2;
        this.displayedCornerRadius = ((CircularProgressIndicatorSpec) r8).trackCornerRadius * f2;
        this.adjustedRadius = (((CircularProgressIndicatorSpec) r8).indicatorSize - ((CircularProgressIndicatorSpec) r8).trackThickness) / 2.0f;
        if ((this.drawable.isShowing() && ((CircularProgressIndicatorSpec) this.spec).showAnimationBehavior == 2) || (this.drawable.isHiding() && ((CircularProgressIndicatorSpec) this.spec).hideAnimationBehavior == 1)) {
            this.adjustedRadius = (((1.0f - f2) * ((CircularProgressIndicatorSpec) this.spec).trackThickness) / 2.0f) + this.adjustedRadius;
        } else if ((this.drawable.isShowing() && ((CircularProgressIndicatorSpec) this.spec).showAnimationBehavior == 1) || (this.drawable.isHiding() && ((CircularProgressIndicatorSpec) this.spec).hideAnimationBehavior == 2)) {
            this.adjustedRadius -= ((1.0f - f2) * ((CircularProgressIndicatorSpec) this.spec).trackThickness) / 2.0f;
        }
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public void fillIndicator(Canvas canvas, Paint paint, float f2, float f7, int i5) {
        if (f2 == f7) {
            return;
        }
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        paint.setColor(i5);
        paint.setStrokeWidth(this.displayedTrackThickness);
        int i7 = this.arcDirectionFactor;
        float f9 = f2 * 360.0f * i7;
        float f10 = (f7 >= f2 ? f7 - f2 : (1.0f + f7) - f2) * 360.0f * i7;
        float f11 = this.adjustedRadius;
        canvas.drawArc(new RectF(-f11, -f11, f11, f11), f9, f10, false, paint);
        if (this.displayedCornerRadius <= 0.0f || Math.abs(f10) >= 360.0f) {
            return;
        }
        paint.setStyle(Paint.Style.FILL);
        drawRoundedEnd(canvas, paint, this.displayedTrackThickness, this.displayedCornerRadius, f9);
        drawRoundedEnd(canvas, paint, this.displayedTrackThickness, this.displayedCornerRadius, f9 + f10);
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public void fillTrack(Canvas canvas, Paint paint) {
        int iCompositeARGBWithAlpha = MaterialColors.compositeARGBWithAlpha(((CircularProgressIndicatorSpec) this.spec).trackColor, this.drawable.getAlpha());
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setAntiAlias(true);
        paint.setColor(iCompositeARGBWithAlpha);
        paint.setStrokeWidth(this.displayedTrackThickness);
        float f2 = this.adjustedRadius;
        canvas.drawArc(new RectF(-f2, -f2, f2, f2), 0.0f, 360.0f, false, paint);
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public int getPreferredHeight() {
        return getSize();
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public int getPreferredWidth() {
        return getSize();
    }
}
