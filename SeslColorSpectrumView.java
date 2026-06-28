package androidx.picker3.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.W;
import com.samsung.android.keyscafe.R;

/* JADX INFO: loaded from: classes.dex */
class SeslColorSpectrumView extends View {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public int f8506A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public final m f8507B;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Resources f8508e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Paint f8509f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Paint f8510g;
    public Paint h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Paint f8511i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Paint f8512j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Drawable f8513k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int[] f8514l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f8515m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f8516n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Rect f8517p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final Rect f8518q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public i f8519r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f8520s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int f8521t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f8522u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public float f8523v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f8524w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int f8525x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final int f8526y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f8527z;

    public SeslColorSpectrumView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f8514l = new int[]{-65281, -16776961, -16711681, -16711936, -256, -65536};
        this.f8520s = 0;
        float dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sesl_spectrum_stroke_width);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.sesl_spectrum_rect_starting);
        this.f8521t = dimensionPixelSize2;
        int dimensionPixelSize3 = getResources().getDimensionPixelSize(R.dimen.sesl_spectrum_rect_top);
        this.f8522u = dimensionPixelSize3;
        this.f8524w = false;
        this.f8527z = -1;
        Resources resources = context.getResources();
        this.f8508e = resources;
        m mVar = new m(this, this);
        this.f8507B = mVar;
        W.i(this, mVar);
        setImportantForAccessibility(1);
        int dimensionPixelSize4 = resources.getDimensionPixelSize(R.dimen.sesl_color_picker_oneui_3_color_spectrum_view_width);
        int dimensionPixelSize5 = resources.getDimensionPixelSize(R.dimen.sesl_color_picker_oneui_3_color_spectrum_view_height);
        this.f8525x = (int) (resources.getDimension(R.dimen.sesl_color_picker_oneui_3_color_spectrum_view_height) / 25.0f);
        this.f8526y = (int) (resources.getDimension(R.dimen.sesl_color_picker_oneui_3_color_swatch_view_width) / 30.0f);
        this.f8517p = new Rect(dimensionPixelSize2, dimensionPixelSize3, dimensionPixelSize4, dimensionPixelSize5);
        this.f8518q = new Rect(0, 0, resources.getDimensionPixelSize(R.dimen.sesl_color_picker_oneui_3_color_spectrum_view_width_background), resources.getDimensionPixelSize(R.dimen.sesl_color_picker_oneui_3_color_spectrum_view_height_background));
        this.o = resources.getDimensionPixelSize(R.dimen.sesl_color_picker_spectrum_cursor_paint_size);
        resources.getDimensionPixelSize(R.dimen.sesl_color_picker_spectrum_cursor_paint_size);
        resources.getDimensionPixelSize(R.dimen.sesl_color_picker_spectrum_cursor_out_stroke_size);
        this.f8520s = (int) (4 * Resources.getSystem().getDisplayMetrics().density);
        this.f8509f = new Paint();
        this.f8510g = new Paint();
        this.f8512j = new Paint();
        this.f8510g.setStyle(Paint.Style.STROKE);
        this.f8510g.setColor(resources.getColor(R.color.sesl_color_picker_stroke_color_spectrumview));
        this.f8510g.setStrokeWidth(dimensionPixelSize);
        this.f8513k = resources.getDrawable(R.drawable.sesl_color_picker_gradient_wheel_cursor);
        this.f8512j.setStyle(Paint.Style.FILL);
        this.f8512j.setColor(resources.getColor(R.color.sesl_color_picker_transparent));
    }

    public final void a(int i5) {
        float[] fArr = new float[3];
        Color.colorToHSV(i5, fArr);
        Rect rect = this.f8517p;
        if (rect != null) {
            String strSubstring = String.format("%08x", Integer.valueOf(i5)).substring(2);
            String string = getResources().getString(R.string.sesl_color_white_ffffff);
            if (this.f8524w && strSubstring.equals(string)) {
                this.f8516n = 0.0f;
                this.f8515m = 0.0f;
            } else if (strSubstring.equals(string)) {
                this.f8516n = 0.0f;
                this.f8515m = this.f8523v;
            } else {
                this.f8515m = ((rect.width() * fArr[0]) / 300.0f) + rect.left;
                this.f8516n = (rect.height() * fArr[1]) + rect.top;
                float f2 = this.f8515m;
                int iWidth = rect.width();
                int i7 = this.f8521t;
                if (f2 > iWidth + i7) {
                    this.f8515m = rect.width() + i7;
                }
                float f7 = this.f8516n;
                int iHeight = rect.height();
                int i9 = this.f8522u;
                if (f7 > iHeight + i9) {
                    this.f8516n = rect.height() + i9;
                }
            }
            Log.d("SeslColorSpectrumView", "updateCursorPosition() HSV[" + fArr[0] + ", " + fArr[1] + ", " + fArr[1] + "] mCursorPosX=" + this.f8515m + " mCursorPosY=" + this.f8516n);
        }
        invalidate();
    }

    public final void b(int i5) {
        Log.i("SeslColorSpectrumView", "updateCursorColor color " + i5);
        if (!String.format("%08x", Integer.valueOf(i5)).substring(2).equals(getResources().getString(R.string.sesl_color_black_000000))) {
            this.f8509f.setColor(D.d.e(i5, 255));
        } else {
            this.f8509f.setColor(Color.parseColor("#" + getResources().getString(R.string.sesl_color_white_ffffff)));
        }
    }

    @Override // android.view.View
    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return this.f8507B.dispatchHoverEvent(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = this.f8518q;
        float f2 = rect.left;
        float f7 = rect.top;
        float f9 = rect.right;
        float f10 = rect.bottom;
        int i5 = this.f8520s;
        canvas.drawRoundRect(f2, f7, f9, f10, i5, i5, this.f8512j);
        Rect rect2 = this.f8517p;
        float f11 = rect2.right;
        int i7 = rect2.top;
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        LinearGradient linearGradient = new LinearGradient(f11, i7, rect2.left, i7, this.f8514l, (float[]) null, tileMode);
        Paint paint = new Paint(1);
        this.f8511i = paint;
        paint.setShader(linearGradient);
        this.f8511i.setStyle(Paint.Style.FILL);
        int i9 = rect2.left;
        LinearGradient linearGradient2 = new LinearGradient(i9, rect2.top, i9, rect2.bottom, -1, 0, tileMode);
        Paint paint2 = new Paint(1);
        this.h = paint2;
        paint2.setShader(linearGradient2);
        canvas.drawRoundRect(rect2.left, rect2.top, rect2.right, rect2.bottom, i5, i5, this.f8511i);
        canvas.drawRoundRect(rect2.left, rect2.top, rect2.right, rect2.bottom, i5, i5, this.h);
        canvas.drawRoundRect(rect2.left, rect2.top, rect2.right, rect2.bottom, i5, i5, this.f8510g);
        float f12 = this.f8515m;
        int i10 = rect2.left;
        if (f12 < i10) {
            this.f8515m = i10;
        }
        float f13 = this.f8516n;
        int i11 = rect2.top;
        if (f13 < i11) {
            this.f8516n = i11;
        }
        float f14 = this.f8515m;
        int i12 = rect2.right;
        int i13 = this.f8521t;
        if (f14 > i12 + i13) {
            this.f8515m = i12 + i13;
        }
        float f15 = this.f8516n;
        int i14 = rect2.bottom;
        int i15 = this.f8522u;
        if (f15 > i14 + i15) {
            this.f8516n = i14 + i15;
        }
        float f16 = this.f8515m;
        float f17 = this.f8516n;
        int i16 = this.o;
        canvas.drawCircle(f16, f17, i16 / 2.0f, this.f8509f);
        Drawable drawable = this.f8513k;
        float f18 = this.f8515m;
        float f19 = this.f8516n;
        drawable.setBounds(((int) f18) - (i16 / 2), ((int) f19) - (i16 / 2), (i16 / 2) + ((int) f18), (i16 / 2) + ((int) f19));
        this.f8513k.draw(canvas);
        setDrawingCacheEnabled(true);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            playSoundEffect(0);
        } else if (action == 2 && getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        float x9 = motionEvent.getX();
        float y4 = motionEvent.getY();
        this.f8523v = x9;
        Rect rect = this.f8517p;
        int iWidth = rect.width();
        int i5 = this.f8521t;
        if (x9 > iWidth + i5) {
            this.f8523v = rect.width() + i5;
        }
        int iHeight = rect.height();
        int i7 = this.f8522u;
        if (y4 > iHeight + i7) {
            rect.height();
        }
        if (x9 > rect.width() + i5) {
            x9 = rect.width() + i5;
        }
        if (y4 > rect.height() + i7) {
            y4 = rect.height() + i7;
        }
        if (x9 < 0.0f) {
            x9 = 0.0f;
        }
        if (y4 < 0.0f) {
            y4 = 0.0f;
        }
        this.f8515m = x9;
        this.f8516n = y4;
        float fWidth = ((x9 - rect.left) / rect.width()) * 300.0f;
        float fHeight = (this.f8516n - rect.top) / rect.height();
        float f2 = fWidth >= 0.0f ? fWidth : 0.0f;
        i iVar = this.f8519r;
        if (iVar != null) {
            iVar.b(f2, fHeight);
        } else {
            Log.d("SeslColorSpectrumView", "Listener is not set.");
        }
        this.f8527z = (((int) (this.f8516n / this.f8525x)) * 30) + ((int) (this.f8515m / this.f8526y));
        invalidate();
        return true;
    }
}
