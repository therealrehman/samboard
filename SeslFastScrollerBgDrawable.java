package androidx.recyclerview.sesl.drawable;

import D.d;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0007¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Landroidx/recyclerview/sesl/drawable/SeslFastScrollerBgDrawable;", "Landroid/graphics/drawable/Drawable;", "", "", "<init>", "()V", "recyclerview_release"}, k = 1, mv = {1, 8, 0})
public final class SeslFastScrollerBgDrawable extends Drawable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public float f8835a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Paint f8836b;

    public SeslFastScrollerBgDrawable() {
        Paint paint = new Paint(1);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAlpha(8);
        paint.setColor(d.e(-16777216, 255));
        this.f8836b = paint;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        j.f(canvas, "canvas");
        Paint paint = this.f8836b;
        paint.setStrokeWidth(this.f8835a);
        float f2 = 2;
        canvas.drawLine(canvas.getWidth() / f2, paint.getStrokeWidth() / f2, canvas.getWidth() / f2, canvas.getHeight() - (paint.getStrokeWidth() / f2), paint);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i5) {
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.f8836b.setColorFilter(colorFilter);
    }
}
