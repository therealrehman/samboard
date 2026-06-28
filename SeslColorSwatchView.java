package androidx.picker3.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.W;
import com.samsung.android.keyscafe.R;
import d6.AbstractC0476d;
import java.lang.reflect.Array;

/* JADX INFO: loaded from: classes.dex */
class SeslColorSwatchView extends View {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final int[][] f8528A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public float[] f8529B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final StringBuilder[][] f8530C;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f8531e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public i f8532f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public GradientDrawable f8533g;
    public final Rect h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Rect f8534i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Resources f8535j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final float f8536k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final float f8537l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Point f8538m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f8539n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f8540p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f8541q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f8542r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Paint f8543s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Paint f8544t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final Paint f8545u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final RectF f8546v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final RectF f8547w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final n f8548x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f8549y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final int[][] f8550z;

    public SeslColorSwatchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 0);
        this.f8531e = 0;
        this.f8539n = -1;
        this.f8541q = false;
        this.f8542r = true;
        this.f8550z = new int[][]{new int[]{-1, -3355444, -5000269, -6710887, -8224126, -10066330, -11711155, -13421773, -15066598, -16777216}, new int[]{-22360, -38037, -49859, -60396, -65536, -393216, -2424832, -5767168, -10747904, -13434880}, new int[]{-11096, -19093, -25544, -30705, -32768, -361216, -2396672, -5745664, -10736128, -13428224}, new int[]{-88, -154, -200, -256, -328704, -329216, -2368768, -6053120, -10724352, -13421824}, new int[]{-5701720, -10027162, -13041864, -16056566, -16711936, -16713216, -16721152, -16735488, -16753664, -16764160}, new int[]{-5701685, -10027101, -13041784, -15728785, -16711834, -16714398, -16721064, -16735423, -16753627, -16764140}, new int[]{-5701633, -10027009, -12713985, -16056321, -16711681, -16714251, -16720933, -16735325, -16753572, -16764109}, new int[]{-5712641, -9718273, -13067009, -15430913, -16744193, -16744966, -16748837, -16755544, -16764575, -16770509}, new int[]{-5723905, -9737217, -13092609, -16119041, -16776961, -16776966, -16776997, -16777048, -16777119, -16777165}, new int[]{-3430145, -5870593, -7849729, -9498625, -10092289, -10223366, -11009829, -12386136, -14352292, -15466445}, new int[]{-22273, -39169, -50945, -61441, -65281, -392966, -2424613, -5767000, -10420127, -13434829}};
        this.f8528A = new int[][]{new int[]{100, 80, 70, 60, 51, 40, 30, 20, 10, 0}, new int[]{83, 71, 62, 54, 50, 49, 43, 33, 18, 10}, new int[]{83, 71, 61, 53, 50, 49, 43, 33, 18, 10}, new int[]{83, 70, 61, 50, 51, 49, 43, 32, 18, 10}, new int[]{83, 70, 61, 52, 50, 49, 43, 32, 18, 10}, new int[]{83, 70, 61, 53, 50, 48, 43, 32, 18, 10}, new int[]{83, 70, 62, 52, 50, 48, 43, 32, 18, 10}, new int[]{83, 71, 61, 54, 50, 49, 43, 33, 19, 10}, new int[]{83, 71, 61, 52, 50, 49, 43, 33, 19, 10}, new int[]{83, 71, 61, 53, 50, 49, 43, 33, 18, 10}, new int[]{83, 70, 61, 53, 50, 49, 43, 33, 19, 10}};
        this.f8530C = (StringBuilder[][]) Array.newInstance((Class<?>) StringBuilder.class, 11, 10);
        Resources resources = context.getResources();
        this.f8535j = resources;
        this.f8533g = (GradientDrawable) resources.getDrawable(R.drawable.sesl_color_swatch_view_cursor);
        this.h = new Rect();
        this.f8534i = new Rect();
        Paint paint = new Paint();
        this.f8545u = paint;
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        this.f8545u.setColor(resources.getColor(R.color.sesl_color_picker_shadow));
        this.f8545u.setMaskFilter(new BlurMaskFilter(10.0f, BlurMaskFilter.Blur.NORMAL));
        n nVar = new n(this, this);
        this.f8548x = nVar;
        W.i(this, nVar);
        setImportantForAccessibility(1);
        this.f8536k = resources.getDimension(R.dimen.sesl_color_picker_oneui_3_color_swatch_view_height) / 10.0f;
        this.f8537l = resources.getDimension(R.dimen.sesl_color_picker_oneui_3_color_swatch_view_width) / 11.0f;
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.sesl_swatch_rect_starting);
        this.o = dimensionPixelSize;
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.sesl_swatch_rect_top);
        this.f8540p = dimensionPixelSize2;
        this.f8546v = new RectF(dimensionPixelSize + 4.5f, dimensionPixelSize2 + 4.5f, resources.getDimensionPixelSize(R.dimen.sesl_color_picker_oneui_3_color_swatch_view_width) + dimensionPixelSize + 4.5f, resources.getDimensionPixelSize(R.dimen.sesl_color_picker_oneui_3_color_swatch_view_height) + dimensionPixelSize2 + 4.5f);
        this.f8547w = new RectF(0.0f, 0.0f, resources.getDimensionPixelSize(R.dimen.sesl_color_picker_oneui_3_color_swatch_view_width_background), resources.getDimensionPixelSize(R.dimen.sesl_color_picker_oneui_3_color_swatch_view_height_background));
        this.f8538m = new Point(-1, -1);
        this.f8531e = (int) (4 * Resources.getSystem().getDisplayMetrics().density);
        Paint paint2 = new Paint();
        this.f8543s = paint2;
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(resources.getColor(R.color.sesl_color_picker_stroke_color_swatchview));
        paint2.setStrokeWidth(0.25f);
        Paint paint3 = new Paint();
        this.f8544t = paint3;
        paint3.setStyle(style);
        paint3.setColor(resources.getColor(R.color.sesl_color_picker_transparent));
    }

    public final StringBuilder a(int i5) {
        Point pointB = b(i5);
        if (!this.f8541q) {
            return null;
        }
        int i7 = pointB.x;
        StringBuilder[] sbArr = this.f8530C[i7];
        int i9 = pointB.y;
        StringBuilder sb = sbArr[i9];
        if (sb != null) {
            return sb;
        }
        n nVar = this.f8548x;
        int i10 = (i9 * 11) + i7;
        int i11 = n.f8585f;
        return nVar.d(i10);
    }

    public final Point b(int i5) {
        int iArgb = Color.argb(255, (i5 >> 16) & 255, (i5 >> 8) & 255, i5 & 255);
        Point point = new Point(-1, -1);
        this.f8541q = false;
        for (int i7 = 0; i7 < 11; i7++) {
            for (int i9 = 0; i9 < 10; i9++) {
                if (this.f8550z[i7][i9] == iArgb) {
                    point.set(i7, i9);
                    this.f8541q = true;
                }
            }
        }
        this.f8542r = true;
        if (!this.f8541q && !this.f8538m.equals(-1, -1)) {
            this.f8542r = false;
            invalidate();
        }
        return point;
    }

    public final void c(Rect rect) {
        Point point = this.f8538m;
        int i5 = point.x;
        float f2 = this.f8537l;
        int i7 = this.o;
        int i9 = point.y;
        float f7 = this.f8536k;
        int i10 = this.f8540p;
        rect.set((int) (((((double) i5) - 0.05d) * ((double) f2)) + 4.5d + ((double) i7)), (int) (((((double) i9) - 0.05d) * ((double) f7)) + 4.5d + ((double) i10)), (int) (((((double) (i5 + 1)) + 0.05d) * ((double) f2)) + 4.5d + ((double) i7)), (int) (((((double) (i9 + 1)) + 0.05d) * ((double) f7)) + 4.5d + ((double) i10)));
    }

    public final void d(Rect rect) {
        Point point = this.f8538m;
        int i5 = point.x;
        float f2 = this.f8537l;
        int i7 = this.o;
        int i9 = point.y;
        float f7 = this.f8536k;
        int i10 = this.f8540p;
        rect.set((int) ((i5 * f2) + 4.5f + i7), (int) ((i9 * f7) + 4.5f + i10), (int) (((((double) (i5 + 1)) + 0.05d) * ((double) f2)) + 4.5d + ((double) i7)), (int) (((((double) (i9 + 1)) + 0.1d) * ((double) f7)) + 4.5d + ((double) i10)));
    }

    @Override // android.view.View
    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        return this.f8548x.dispatchHoverEvent(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int i5;
        int i7;
        int i9;
        Paint paint;
        char c5 = 2;
        int i10 = 8;
        Paint paint2 = new Paint();
        RectF rectF = this.f8547w;
        int i11 = this.f8531e;
        canvas.drawRoundRect(rectF, i11, i11, this.f8544t);
        int i12 = 0;
        while (i12 < 11) {
            int i13 = 0;
            while (i13 < 10) {
                paint2.setColor(this.f8550z[i12][i13]);
                float f2 = this.f8536k;
                int i14 = this.f8540p;
                float f7 = this.f8537l;
                int i15 = this.o;
                if (i12 == 0 && i13 == 0) {
                    float[] fArr = new float[i10];
                    fArr[0] = i11;
                    fArr[1] = i11;
                    fArr[c5] = 0.0f;
                    fArr[3] = 0.0f;
                    fArr[4] = 0.0f;
                    fArr[5] = 0.0f;
                    fArr[6] = 0.0f;
                    fArr[7] = 0.0f;
                    this.f8529B = fArr;
                    Path path = new Path();
                    path.addRoundRect((int) AbstractC0476d.h(f7, i12, i15, 4.5f), (int) AbstractC0476d.h(f2, i13, i14, 4.5f), (int) AbstractC0476d.h(f7, i12 + 1, i15, 4.5f), (int) AbstractC0476d.h(f2, i13 + 1, i14, 4.5f), this.f8529B, Path.Direction.CW);
                    canvas.drawPath(path, paint2);
                } else if (i12 == 0 && i13 == 9) {
                    this.f8529B = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, i11, i11};
                    Path path2 = new Path();
                    path2.addRoundRect((int) AbstractC0476d.h(f7, i12, i15, 4.5f), (int) AbstractC0476d.h(f2, i13, i14, 4.5f), (int) AbstractC0476d.h(f7, i12 + 1, i15, 4.5f), (int) AbstractC0476d.h(f2, i13 + 1, i14, 4.5f), this.f8529B, Path.Direction.CW);
                    canvas.drawPath(path2, paint2);
                } else {
                    int i16 = 10;
                    if (i12 == 10) {
                        if (i13 == 0) {
                            this.f8529B = new float[]{0.0f, 0.0f, i11, i11, 0.0f, 0.0f, 0.0f, 0.0f};
                            Path path3 = new Path();
                            path3.addRoundRect((int) AbstractC0476d.h(f7, i12, i15, 4.5f), (int) AbstractC0476d.h(f2, i13, i14, 4.5f), (int) AbstractC0476d.h(f7, i12 + 1, i15, 4.5f), (int) AbstractC0476d.h(f2, i13 + 1, i14, 4.5f), this.f8529B, Path.Direction.CW);
                            canvas.drawPath(path3, paint2);
                        } else {
                            i16 = 10;
                        }
                    }
                    if (i12 == i16 && i13 == 9) {
                        this.f8529B = new float[]{0.0f, 0.0f, 0.0f, 0.0f, i11, i11, 0.0f, 0.0f};
                        Path path4 = new Path();
                        path4.addRoundRect((int) AbstractC0476d.h(f7, i12, i15, 4.5f), (int) AbstractC0476d.h(f2, i13, i14, 4.5f), (int) AbstractC0476d.h(f7, i12 + 1, i15, 4.5f), (int) AbstractC0476d.h(f2, i13 + 1, i14, 4.5f), this.f8529B, Path.Direction.CW);
                        canvas.drawPath(path4, paint2);
                        i5 = i13;
                        i7 = i12;
                        i9 = i11;
                        paint = paint2;
                    } else {
                        i5 = i13;
                        i7 = i12;
                        i9 = i11;
                        paint = paint2;
                        canvas.drawRect((int) AbstractC0476d.h(f7, i12, i15, 4.5f), (int) AbstractC0476d.h(f2, i13, i14, 4.5f), (int) AbstractC0476d.h(f7, i12 + 1, i15, 4.5f), (int) AbstractC0476d.h(f2, i13 + 1, i14, 4.5f), paint2);
                    }
                    i13 = i5 + 1;
                    i11 = i9;
                    paint2 = paint;
                    i12 = i7;
                    c5 = 2;
                    i10 = 8;
                }
                i5 = i13;
                i7 = i12;
                i9 = i11;
                paint = paint2;
                i13 = i5 + 1;
                i11 = i9;
                paint2 = paint;
                i12 = i7;
                c5 = 2;
                i10 = 8;
            }
            i12++;
            c5 = 2;
            i10 = 8;
        }
        int i17 = i11;
        canvas.drawRoundRect(this.f8546v, i17, i17, this.f8543s);
        if (this.f8542r) {
            canvas.drawRect(this.f8534i, this.f8545u);
            int i18 = this.f8538m.y;
            Resources resources = this.f8535j;
            if (i18 == 8 || i18 == 9) {
                this.f8533g = (GradientDrawable) resources.getDrawable(R.drawable.sesl_color_swatch_view_cursor);
            } else {
                this.f8533g = (GradientDrawable) resources.getDrawable(R.drawable.sesl_color_swatch_view_cursor_gray);
            }
            this.f8533g.setColor(this.f8549y);
            this.f8533g.setBounds(this.h);
            this.f8533g.draw(canvas);
        }
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 2 && getParent() != null) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        float x9 = motionEvent.getX() - this.o;
        float y4 = motionEvent.getY() - this.f8540p;
        float f2 = this.f8537l;
        float f7 = 11.0f * f2;
        float f9 = this.f8536k;
        float f10 = 10.0f * f9;
        if (x9 >= f7) {
            x9 = f7 - 1.0f;
        } else if (x9 < 0.0f) {
            x9 = 0.0f;
        }
        if (y4 >= f10) {
            y4 = f10 - 1.0f;
        } else if (y4 < 0.0f) {
            y4 = 0.0f;
        }
        Point point = this.f8538m;
        Point point2 = new Point(point.x, point.y);
        point.set((int) (x9 / f2), (int) (y4 / f9));
        if ((!point2.equals(point)) || !this.f8542r) {
            int i5 = point.x;
            int[][] iArr = this.f8550z;
            int i7 = iArr[i5][point.y];
            this.f8549y = i7;
            this.f8549y = D.d.e(i7, 255);
            d(this.f8534i);
            c(this.h);
            Point point3 = this.f8538m;
            this.f8539n = (point3.y * 11) + point3.x;
            invalidate();
            i iVar = this.f8532f;
            if (iVar != null) {
                iVar.a(iArr[point.x][point.y]);
            }
        }
        return true;
    }
}
