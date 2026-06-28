package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import com.google.android.material.color.MaterialColors;

/* JADX INFO: loaded from: classes.dex */
final class LinearDrawingDelegate extends DrawingDelegate<LinearProgressIndicatorSpec> {
    private float displayedCornerRadius;
    private Path displayedTrackPath;
    private float displayedTrackThickness;
    private float trackLength;

    public LinearDrawingDelegate(LinearProgressIndicatorSpec linearProgressIndicatorSpec) {
        super(linearProgressIndicatorSpec);
        this.trackLength = 300.0f;
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public void adjustCanvas(Canvas canvas, Rect rect, float f2) {
        this.trackLength = rect.width();
        float f7 = ((LinearProgressIndicatorSpec) this.spec).trackThickness;
        canvas.translate((rect.width() / 2.0f) + rect.left, Math.max(0.0f, (rect.height() - ((LinearProgressIndicatorSpec) this.spec).trackThickness) / 2.0f) + (rect.height() / 2.0f) + rect.top);
        if (((LinearProgressIndicatorSpec) this.spec).drawHorizontallyInverse) {
            canvas.scale(-1.0f, 1.0f);
        }
        if ((this.drawable.isShowing() && ((LinearProgressIndicatorSpec) this.spec).showAnimationBehavior == 1) || (this.drawable.isHiding() && ((LinearProgressIndicatorSpec) this.spec).hideAnimationBehavior == 2)) {
            canvas.scale(1.0f, -1.0f);
        }
        if (this.drawable.isShowing() || this.drawable.isHiding()) {
            canvas.translate(0.0f, ((f2 - 1.0f) * ((LinearProgressIndicatorSpec) this.spec).trackThickness) / 2.0f);
        }
        float f9 = this.trackLength;
        canvas.clipRect((-f9) / 2.0f, (-f7) / 2.0f, f9 / 2.0f, f7 / 2.0f);
        S s8 = this.spec;
        this.displayedTrackThickness = ((LinearProgressIndicatorSpec) s8).trackThickness * f2;
        this.displayedCornerRadius = ((LinearProgressIndicatorSpec) s8).trackCornerRadius * f2;
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public void fillIndicator(Canvas canvas, Paint paint, float f2, float f7, int i5) {
        if (f2 == f7) {
            return;
        }
        float f9 = this.trackLength;
        float f10 = (-f9) / 2.0f;
        float f11 = ((f2 * f9) + f10) - (this.displayedCornerRadius * 2.0f);
        float f12 = (f7 * f9) + f10;
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(i5);
        canvas.save();
        canvas.clipPath(this.displayedTrackPath);
        float f13 = this.displayedTrackThickness;
        RectF rectF = new RectF(f11, (-f13) / 2.0f, f12, f13 / 2.0f);
        float f14 = this.displayedCornerRadius;
        canvas.drawRoundRect(rectF, f14, f14, paint);
        canvas.restore();
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public void fillTrack(Canvas canvas, Paint paint) {
        int iCompositeARGBWithAlpha = MaterialColors.compositeARGBWithAlpha(((LinearProgressIndicatorSpec) this.spec).trackColor, this.drawable.getAlpha());
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(iCompositeARGBWithAlpha);
        Path path = new Path();
        this.displayedTrackPath = path;
        float f2 = this.trackLength;
        float f7 = this.displayedTrackThickness;
        RectF rectF = new RectF((-f2) / 2.0f, (-f7) / 2.0f, f2 / 2.0f, f7 / 2.0f);
        float f9 = this.displayedCornerRadius;
        path.addRoundRect(rectF, f9, f9, Path.Direction.CCW);
        canvas.drawPath(this.displayedTrackPath, paint);
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public int getPreferredHeight() {
        return ((LinearProgressIndicatorSpec) this.spec).trackThickness;
    }

    @Override // com.google.android.material.progressindicator.DrawingDelegate
    public int getPreferredWidth() {
        return -1;
    }
}
