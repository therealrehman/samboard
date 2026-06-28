package androidx.picker.eyeDropper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.samsung.android.keyscafe.R;

/* JADX INFO: loaded from: classes.dex */
public class SeslMagnifyingView extends View {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Bitmap f7864e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f7865f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f7866g;
    public int h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Paint f7867i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Paint f7868j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Paint f7869k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Paint f7870l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Paint f7871m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f7872n;
    public final int o;

    public SeslMagnifyingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        Paint paint = new Paint();
        this.f7867i = paint;
        Paint paint2 = new Paint();
        this.f7868j = paint2;
        Paint paint3 = new Paint();
        this.f7869k = paint3;
        Paint paint4 = new Paint();
        this.f7870l = paint4;
        Paint paint5 = new Paint();
        this.f7871m = paint5;
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sesl_eyedropper_color_border_stroke_width);
        this.f7872n = dimensionPixelSize;
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.sesl_eyedropper_inner_border_stroke_width);
        this.o = dimensionPixelSize2;
        int dimensionPixelSize3 = getResources().getDimensionPixelSize(R.dimen.sesl_eyedropper_dividers_stroke_width);
        int dimensionPixelSize4 = getResources().getDimensionPixelSize(R.dimen.sesl_eyedropper_center_square_stroke_width);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        Paint.Style style = Paint.Style.STROKE;
        paint2.setStyle(style);
        paint2.setAntiAlias(true);
        paint2.setStrokeWidth(dimensionPixelSize);
        paint3.setStyle(style);
        paint3.setColor(getResources().getColor(R.color.sesl_color_picker_cursor_stroke_color));
        paint3.setAntiAlias(true);
        paint3.setStrokeWidth(dimensionPixelSize2);
        paint4.setStyle(style);
        paint4.setColor(getResources().getColor(R.color.sesl_color_picker_swatch_cursor_color));
        paint4.setAntiAlias(true);
        paint4.setStrokeWidth(dimensionPixelSize3);
        paint5.setStyle(style);
        paint5.setColor(getResources().getColor(R.color.sesl_color_picker_cursor_stroke_color));
        paint5.setAntiAlias(true);
        paint5.setStrokeWidth(dimensionPixelSize4);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        Paint paint;
        super.onDraw(canvas);
        if (this.f7864e == null) {
            return;
        }
        float width = getWidth();
        float height = getHeight();
        float fMin = Math.min(width, height) / 2.0f;
        float f2 = this.f7865f;
        float f7 = (width / 3.0f) / 2.0f;
        float f9 = this.f7866g;
        float f10 = (height / 3.0f) / 2.0f;
        RectF rectF = new RectF(f2 - f7, f9 - f10, f2 + f7, f9 + f10);
        RectF rectF2 = new RectF(0.0f, 0.0f, width, height);
        Rect rect = new Rect();
        rectF.round(rect);
        Rect rect2 = new Rect();
        rectF2.round(rect2);
        canvas.save();
        Path path = new Path();
        float f11 = width / 2.0f;
        float f12 = height / 2.0f;
        path.addCircle(f11, f12, fMin, Path.Direction.CW);
        canvas.clipPath(path);
        canvas.drawBitmap(this.f7864e, rect, rect2, this.f7867i);
        float f13 = 15;
        float f14 = width / f13;
        float f15 = height / f13;
        int i5 = 0;
        while (true) {
            paint = this.f7870l;
            if (i5 >= 15) {
                break;
            }
            float f16 = i5 * f14;
            canvas.drawLine(f16, 0.0f, f16, height, paint);
            i5++;
        }
        for (int i7 = 0; i7 < 15; i7++) {
            float f17 = i7 * f15;
            canvas.drawLine(0.0f, f17, width, f17, paint);
        }
        float f18 = (width / 15.0f) / 2.0f;
        float f19 = (height / 15.0f) / 2.0f;
        int i9 = this.o;
        float f20 = i9;
        canvas.drawRoundRect(f11 - f18, f12 - f19, f11 + f18, f12 + f19, f20, f20, this.f7871m);
        float f21 = this.f7872n;
        canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, fMin - ((i9 / 2.0f) + f21), this.f7869k);
        Paint paint2 = this.f7868j;
        paint2.setColor(this.h);
        canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, fMin - (f21 / 2.0f), paint2);
        canvas.restore();
    }
}
