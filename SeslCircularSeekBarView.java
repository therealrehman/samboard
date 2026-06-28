package androidx.picker.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.PathInterpolator;
import com.samsung.android.keyscafe.R;
import e0.AbstractC0479a;
import java.util.Objects;
import m7.AbstractC0752G;

/* JADX INFO: loaded from: classes.dex */
class SeslCircularSeekBarView extends View {

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public static final int f8028r0 = Paint.Cap.ROUND.ordinal();

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public static final int f8029s0 = Color.argb(255, 133, 135, 254);

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public static final int f8030t0 = Color.argb(255, 133, 135, 254);

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public static final int f8031u0 = Color.argb(255, 133, 135, 254);

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public static final int f8032v0 = Color.argb(255, 255, 167, 0);

    /* JADX INFO: renamed from: w0, reason: collision with root package name */
    public static final int f8033w0 = Color.argb(255, 255, 167, 0);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final RectF f8034A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public final RectF f8035B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final RectF f8036C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final RectF f8037D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final int f8038E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final int f8039F;
    public final int G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final int f8040H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final int f8041I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final int f8042J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final int f8043K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final int f8044L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public final int f8045M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public Paint f8046N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public Paint f8047O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public float f8048P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public float f8049Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public final Path f8050R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public final Path f8051S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public final Path f8052T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public final Path f8053U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public final Path f8054V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public float f8055W;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public float f8056a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public final boolean f8057b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public boolean f8058c0;
    public boolean d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f8059e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public boolean f8060e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Paint f8061f;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public boolean f8062f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Paint f8063g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public int f8064g0;
    public Paint h;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public float f8065h0;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Paint f8066i;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public float f8067i0;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Paint f8068j;
    public float j0;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Paint f8069k;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public float f8070k0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Paint f8071l;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public final Drawable f8072l0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Paint f8073m;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public final Drawable f8074m0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Paint f8075n;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public final W1.a f8076n0;
    public Paint.Cap o;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public final B2.g f8077o0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final float f8078p;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public final M3.h f8079p0;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final float f8080q;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public final boolean f8081q0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final float f8082r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public float f8083s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public float f8084t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f8085u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final float f8086v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public float f8087w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final float f8088x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final float f8089y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final float f8090z;

    public SeslCircularSeekBarView(Context context, AttributeSet attributeSet) {
        Drawable drawable;
        super(context, attributeSet);
        this.f8059e = getResources().getDisplayMetrics().density;
        this.f8034A = new RectF();
        this.f8035B = new RectF();
        this.f8036C = new RectF();
        this.f8037D = new RectF();
        this.f8058c0 = true;
        this.d0 = true;
        this.f8060e0 = false;
        this.f8081q0 = false;
        new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, AbstractC0479a.f10584e, 0, 0);
        if (typedArrayObtainStyledAttributes != null) {
            this.f8085u = typedArrayObtainStyledAttributes.getDimension(23, 65.0f);
            this.f8086v = typedArrayObtainStyledAttributes.getDimension(13, 50.0f);
            typedArrayObtainStyledAttributes.getDimension(22, 15.0f);
            this.f8078p = typedArrayObtainStyledAttributes.getDimension(6, 15.0f);
            this.f8080q = getResources().getDimension(R.dimen.sesl_sleep_goal_wheel_width);
            this.f8082r = getResources().getDimension(R.dimen.sesl_dot_line_stroke_width);
            this.o = Paint.Cap.values()[typedArrayObtainStyledAttributes.getInt(0, f8028r0)];
            this.G = typedArrayObtainStyledAttributes.getColor(17, f8030t0);
            this.f8039F = typedArrayObtainStyledAttributes.getColor(10, f8029s0);
            this.f8041I = typedArrayObtainStyledAttributes.getColor(11, f8031u0);
            this.f8038E = typedArrayObtainStyledAttributes.getColor(25, f8032v0);
            this.f8040H = typedArrayObtainStyledAttributes.getColor(26, f8033w0);
            this.f8042J = typedArrayObtainStyledAttributes.getColor(1, -3355444);
            this.f8043K = typedArrayObtainStyledAttributes.getColor(2, 0);
            this.f8044L = typedArrayObtainStyledAttributes.getColor(4, -3355444);
            this.f8045M = typedArrayObtainStyledAttributes.getColor(3, -7829368);
            this.f8055W = typedArrayObtainStyledAttributes.getInt(16, 100);
            this.f8056a0 = typedArrayObtainStyledAttributes.getInt(24, 40);
            this.f8057b0 = typedArrayObtainStyledAttributes.getBoolean(15, true);
            typedArrayObtainStyledAttributes.getBoolean(18, true);
            this.f8058c0 = typedArrayObtainStyledAttributes.getBoolean(14, true);
            this.f8062f0 = typedArrayObtainStyledAttributes.getBoolean(12, false);
            this.j0 = 7.5f;
            this.f8070k0 = 225.0f;
            this.f8089y = ((typedArrayObtainStyledAttributes.getFloat(27, 270.0f) % 360.0f) + 360.0f) % 360.0f;
            float f2 = ((typedArrayObtainStyledAttributes.getFloat(9, 270.0f) % 360.0f) + 360.0f) % 360.0f;
            this.f8090z = f2;
            if (this.f8089y % 360.0f == f2 % 360.0f) {
                this.f8090z = f2 - 0.1f;
            }
            float f7 = ((typedArrayObtainStyledAttributes.getFloat(20, 0.0f) % 360.0f) + 360.0f) % 360.0f;
            this.f8087w = f7;
            if (f7 == 0.0f) {
                this.f8087w = 0.1f;
            }
            float f9 = ((typedArrayObtainStyledAttributes.getFloat(20, 0.0f) % 360.0f) + 360.0f) % 360.0f;
            this.f8088x = f9;
            if (f9 == 0.0f) {
                this.f8088x = 0.1f;
            }
            W1.a aVar = new W1.a(19);
            new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);
            this.f8076n0 = aVar;
            typedArrayObtainStyledAttributes.recycle();
        }
        Drawable.ConstantState constantState = getResources().getDrawable(R.drawable.sesl_bedtime, null).mutate().getConstantState();
        Objects.requireNonNull(constantState);
        this.f8074m0 = constantState.newDrawable().mutate();
        Drawable.ConstantState constantState2 = getResources().getDrawable(R.drawable.sesl_wakeup, null).mutate().getConstantState();
        Objects.requireNonNull(constantState2);
        this.f8072l0 = constantState2.newDrawable().mutate();
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(getContext().getResources().getColor(R.color.sesl_picker_thumb_icon_color), PorterDuff.Mode.SRC_ATOP);
        if (this.f8074m0 != null && (drawable = this.f8072l0) != null) {
            drawable.setColorFilter(porterDuffColorFilter);
            this.f8074m0.setColorFilter(porterDuffColorFilter);
        }
        c();
        this.f8050R = new Path();
        this.f8051S = new Path();
        this.f8052T = new Path();
        this.f8053U = new Path();
        this.f8054V = new Path();
        new Path();
        this.f8077o0 = new B2.g();
        this.f8079p0 = new M3.h(20);
    }

    public final void a(Canvas canvas) {
        RectF rectF;
        canvas.drawPath(this.f8054V, this.f8073m);
        if (this.f8081q0) {
            canvas.drawPath(this.f8054V, this.f8075n);
        }
        Drawable drawable = this.f8074m0;
        if (drawable == null || (rectF = this.f8035B) == null) {
            return;
        }
        drawable.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
        this.f8074m0.draw(canvas);
    }

    public final void b(Canvas canvas) {
        RectF rectF;
        canvas.drawPath(this.f8053U, this.f8069k);
        if (this.f8081q0) {
            canvas.drawPath(this.f8053U, this.f8071l);
        }
        Drawable drawable = this.f8072l0;
        if (drawable == null || (rectF = this.f8036C) == null) {
            return;
        }
        drawable.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
        this.f8072l0.draw(canvas);
    }

    public final void c() {
        Paint paint = new Paint();
        this.f8061f = paint;
        paint.setAntiAlias(true);
        this.f8061f.setDither(true);
        this.f8061f.setColor(this.f8042J);
        this.f8061f.setStrokeWidth(this.f8078p);
        Paint paint2 = this.f8061f;
        Paint.Style style = Paint.Style.STROKE;
        paint2.setStyle(style);
        Paint paint3 = this.f8061f;
        Paint.Join join = Paint.Join.ROUND;
        paint3.setStrokeJoin(join);
        this.f8061f.setStrokeCap(this.o);
        Paint paint4 = new Paint();
        this.f8063g = paint4;
        paint4.setAntiAlias(true);
        this.f8063g.setDither(true);
        this.f8063g.setColor(this.f8043K);
        this.f8063g.setStyle(Paint.Style.FILL);
        Paint paint5 = new Paint();
        this.h = paint5;
        paint5.setAntiAlias(true);
        this.h.setDither(true);
        this.h.setStrokeWidth(this.f8078p);
        this.h.setStyle(style);
        this.h.setStrokeJoin(join);
        this.h.setStrokeCap(this.o);
        Paint paint6 = new Paint();
        this.f8066i = paint6;
        paint6.setAntiAlias(true);
        this.f8066i.setDither(true);
        this.f8066i.setStrokeWidth(this.f8080q);
        this.f8066i.setStyle(style);
        this.f8066i.setStrokeJoin(join);
        this.f8066i.setStrokeCap(Paint.Cap.ROUND);
        this.f8066i.setColor(getResources().getColor(R.color.sesl_sleep_goal_wheel_color));
        Paint paint7 = new Paint();
        this.f8069k = paint7;
        paint7.setAntiAlias(true);
        this.f8069k.setDither(true);
        this.f8069k.setColor(this.f8038E);
        this.f8069k.setStrokeWidth(this.f8085u);
        this.f8069k.setStyle(style);
        this.f8069k.setStrokeJoin(join);
        this.f8069k.setStrokeCap(this.o);
        Paint paint8 = new Paint();
        this.f8071l = paint8;
        paint8.set(this.f8069k);
        this.f8071l.setColor(this.f8040H);
        this.f8071l.setStrokeWidth(this.f8085u);
        Paint paint9 = new Paint();
        this.f8073m = paint9;
        paint9.set(this.f8069k);
        this.f8073m.setColor(this.f8039F);
        Paint paint10 = new Paint();
        this.f8075n = paint10;
        paint10.set(this.f8071l);
        this.f8075n.setColor(this.f8041I);
        this.f8075n.setStrokeWidth(this.f8085u);
        Paint paint11 = new Paint(1);
        this.f8046N = paint11;
        float f2 = this.f8059e * 1.0f;
        paint11.setStrokeWidth(f2);
        this.f8046N.setColor(this.f8044L);
        this.f8046N.setStyle(style);
        Paint paint12 = new Paint(1);
        this.f8047O = paint12;
        paint12.setStrokeWidth(f2);
        this.f8047O.setColor(this.f8045M);
        this.f8047O.setStyle(style);
        Path path = new Path();
        float f7 = this.f8082r / 2.0f;
        path.addCircle(f7, 0.0f, f7, Path.Direction.CW);
        Paint paint13 = new Paint();
        this.f8068j = paint13;
        paint13.setStyle(style);
        this.f8068j.setStrokeWidth(this.f8082r);
        this.f8068j.setColor(getResources().getColor(R.color.sesl_dotted_line_color));
        this.f8068j.setPathEffect(new PathDashPathEffect(path, getResources().getDimension(R.dimen.sesl_dot_line_gap_width) + this.f8082r, 0.0f, PathDashPathEffect.Style.ROTATE));
    }

    public final void d() {
        float f2 = (360.0f - (this.f8089y - this.f8090z)) % 360.0f;
        this.f8048P = f2;
        if (f2 <= 0.0f) {
            this.f8048P = 360.0f;
        }
        float f7 = this.j0 - this.f8070k0;
        this.f8049Q = f7;
        if (f7 < 0.0f) {
            f7 += 360.0f;
        }
        this.f8049Q = f7;
        RectF rectF = this.f8034A;
        float f9 = this.f8065h0;
        float f10 = this.f8067i0;
        rectF.set(-f9, -f10, f9, f10);
        RectF rectF2 = this.f8037D;
        rectF2.left = rectF.centerX() - (this.f8083s - 5.0f);
        rectF2.top = rectF.centerY() - (this.f8083s - 5.0f);
        rectF2.right = (this.f8083s - 5.0f) + rectF.centerY();
        rectF2.bottom = (this.f8083s - 5.0f) + rectF.centerY();
        this.f8050R.reset();
        this.f8050R.addArc(rectF, this.f8089y, this.f8048P);
        float f11 = this.f8070k0;
        float f12 = this.f8087w;
        float f13 = f11 - (f12 / 2.0f);
        float f14 = this.f8049Q + f12;
        if (f14 >= 360.0f) {
            f14 = 359.9f;
        }
        this.f8051S.reset();
        this.f8051S.addArc(rectF, f13, f14);
        this.f8052T.reset();
        if (this.f8056a0 > 6.5d) {
            this.f8052T.addArc(rectF, f13, f14);
        }
        float f15 = this.j0 - (this.f8087w / 2.0f);
        this.f8053U.reset();
        this.f8053U.addArc(rectF, f15, this.f8087w);
        float f16 = this.f8070k0 - (this.f8088x / 2.0f);
        this.f8054V.reset();
        this.f8054V.addArc(rectF, f16, this.f8088x);
        double d8 = ((double) (this.f8070k0 / 180.0f)) * 3.141592653589793d;
        RectF rectF3 = this.f8035B;
        rectF3.left = ((float) ((Math.cos(d8) * ((double) this.f8084t)) + ((double) rectF.centerX()))) - (this.f8086v / 2.0f);
        float fSin = (float) ((Math.sin(d8) * ((double) this.f8084t)) + ((double) rectF.centerY()));
        float f17 = this.f8086v;
        float f18 = fSin - (f17 / 2.0f);
        rectF3.top = f18;
        rectF3.right = rectF3.left + f17;
        rectF3.bottom = f18 + f17;
        double d10 = ((double) (this.j0 / 180.0f)) * 3.141592653589793d;
        RectF rectF4 = this.f8036C;
        rectF4.left = ((float) ((Math.cos(d10) * ((double) this.f8084t)) + ((double) rectF.centerX()))) - (this.f8086v / 2.0f);
        float fSin2 = (float) ((Math.sin(d10) * ((double) this.f8084t)) + ((double) rectF.centerY()));
        float f19 = this.f8086v;
        float f20 = fSin2 - (f19 / 2.0f);
        rectF4.top = f20;
        rectF4.right = rectF4.left + f19;
        rectF4.bottom = f20 + f19;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        requestLayout();
        invalidate();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        RectF rectF;
        super.onDraw(canvas);
        canvas.translate(getWidth() / 2.0f, getHeight() / 2.0f);
        canvas.drawPath(this.f8050R, this.f8063g);
        canvas.drawPath(this.f8050R, this.f8061f);
        double d8 = 0.0d;
        while (true) {
            rectF = this.f8034A;
            if (d8 > 360.0d) {
                break;
            }
            double d10 = ((((double) this.f8089y) + d8) / 180.0d) * 3.141592653589793d;
            double dCenterX = rectF.centerX();
            float f2 = this.f8083s;
            float f7 = this.f8059e * 2.5f;
            float fCos = (float) (dCenterX + (Math.cos(d10) * ((double) (f2 - f7))));
            float fSin = (float) ((Math.sin(d10) * ((double) (this.f8083s - f7))) + ((double) rectF.centerY()));
            float fCos2 = (float) ((Math.cos(d10) * ((double) (this.f8083s + f7))) + ((double) rectF.centerX()));
            float fSin2 = (float) ((Math.sin(d10) * ((double) (f7 + this.f8083s))) + ((double) rectF.centerY()));
            double d11 = d8 % 90.0d;
            if (d11 != 0.0d && d11 != 2.5d && d11 != 3.0d && d11 != 87.0d && d11 != 87.5d && d8 != 175.0d && d8 != 185.0d) {
                if (d8 % 15.0d == 0.0d) {
                    canvas.drawLine(fCos, fSin, fCos2, fSin2, this.f8047O);
                } else {
                    canvas.drawLine(fCos, fSin, fCos2, fSin2, this.f8046N);
                }
            }
            d8 += 2.5d;
        }
        M3.h hVar = this.f8079p0;
        int[] iArr = (int[]) hVar.f2058f;
        int i5 = this.f8039F;
        iArr[0] = i5;
        iArr[1] = i5;
        iArr[2] = this.G;
        int i7 = this.f8038E;
        iArr[3] = i7;
        iArr[4] = i7;
        float[] fArr = (float[]) hVar.f2059g;
        fArr[0] = 0.0f;
        float f9 = this.f8056a0 / this.f8055W;
        fArr[1] = 0.1f * f9;
        fArr[2] = 0.5f * f9;
        fArr[3] = 0.9f * f9;
        fArr[4] = f9;
        float fCenterX = rectF.centerX();
        float fCenterY = rectF.centerY();
        M3.h hVar2 = this.f8079p0;
        SweepGradient sweepGradient = new SweepGradient(fCenterX, fCenterY, (int[]) hVar2.f2058f, (float[]) hVar2.f2059g);
        Matrix matrix = new Matrix();
        matrix.setRotate(this.f8070k0, rectF.centerX(), rectF.centerY());
        sweepGradient.setLocalMatrix(matrix);
        this.h.setShader(sweepGradient);
        canvas.drawPath(this.f8051S, this.h);
        canvas.drawPath(this.f8052T, this.f8068j);
        if (this.f8064g0 == 0) {
            b(canvas);
            a(canvas);
        } else {
            a(canvas);
            b(canvas);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i5, int i7) {
        int defaultSize = View.getDefaultSize(getSuggestedMinimumHeight(), i7);
        int defaultSize2 = View.getDefaultSize(getSuggestedMinimumWidth(), i5);
        if (defaultSize == 0) {
            defaultSize = defaultSize2;
        }
        if (defaultSize2 == 0) {
            defaultSize2 = defaultSize;
        }
        if (this.f8057b0) {
            int iMin = Math.min(defaultSize2, defaultSize);
            setMeasuredDimension(iMin, iMin);
        } else {
            setMeasuredDimension(defaultSize2, defaultSize);
        }
        this.f8085u = getResources().getDimension(R.dimen.sesl_sleep_time_pointer_size);
        float dimension = (this.f8085u / 2.0f) + getResources().getDimension(R.dimen.sesl_sleep_time_icon_touch_width);
        float f2 = getResources().getConfiguration().screenWidthDp * getResources().getDisplayMetrics().density;
        float f7 = getResources().getConfiguration().screenHeightDp;
        float dimension2 = getResources().getDimension(R.dimen.sesl_sleep_visual_edit_outer_circle_size);
        if (f7 < 420.0f) {
            dimension2 = (int) getResources().getDimension(R.dimen.sesl_sleep_visual_edit_outer_circle_min_size);
        }
        float f9 = (f2 / 2.0f) - dimension;
        this.f8065h0 = f9;
        float f10 = (dimension2 / 2.0f) - dimension;
        this.f8067i0 = f10;
        if (this.f8057b0) {
            float fMin = Math.min(f10, f9);
            this.f8067i0 = fMin;
            this.f8065h0 = fMin;
        }
        TypedValue typedValue = new TypedValue();
        getResources().getValue(R.dimen.sesl_time_picker_inner_circle_container_ratio, typedValue, true);
        float f11 = this.f8067i0;
        this.f8084t = f11;
        this.f8083s = typedValue.getFloat() * f11;
        d();
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        Bundle bundle = (Bundle) parcelable;
        super.onRestoreInstanceState(bundle.getParcelable("PARENT"));
        this.f8055W = bundle.getFloat("MAX");
        this.f8056a0 = bundle.getFloat("PROGRESS");
        this.f8049Q = bundle.getFloat("mProgressDegrees");
        this.j0 = bundle.getFloat("mSecondPointerPosition");
        this.f8070k0 = bundle.getFloat("mFirstPointerPosition");
        this.f8087w = bundle.getFloat("mSecondPointerAngle");
        this.f8058c0 = bundle.getBoolean("mLockEnabled");
        this.d0 = bundle.getBoolean("mLockAtStart");
        this.f8060e0 = bundle.getBoolean("mLockAtEnd");
        this.o = Paint.Cap.values()[bundle.getInt("mCircleStyle")];
        this.f8064g0 = bundle.getInt("mLastPointerTouched");
        this.f8062f0 = bundle.getBoolean("mHideProgressWhenEmpty");
        c();
        d();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        Parcelable parcelableOnSaveInstanceState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putParcelable("PARENT", parcelableOnSaveInstanceState);
        bundle.putFloat("MAX", this.f8055W);
        bundle.putFloat("PROGRESS", this.f8056a0);
        bundle.putFloat("mProgressDegrees", this.f8049Q);
        bundle.putFloat("mSecondPointerPosition", this.j0);
        bundle.putFloat("mFirstPointerPosition", this.f8070k0);
        bundle.putFloat("mSecondPointerAngle", this.f8087w);
        bundle.putBoolean("mLockEnabled", this.f8058c0);
        bundle.putBoolean("mLockAtStart", this.d0);
        bundle.putBoolean("mLockAtEnd", this.f8060e0);
        bundle.putInt("mCircleStyle", this.o.ordinal());
        bundle.putInt("mLastPointerTouched", this.f8064g0);
        bundle.putBoolean("mHideProgressWhenEmpty", this.f8062f0);
        return bundle;
    }

    @Override // android.view.View
    public final void onSizeChanged(int i5, int i7, int i9, int i10) {
        super.onSizeChanged(i5, i7, i9, i10);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled()) {
            return false;
        }
        this.f8076n0.getClass();
        this.f8077o0.f198a = motionEvent.getX() - (getWidth() / 2.0f);
        this.f8077o0.f199b = motionEvent.getY() - (getHeight() / 2.0f);
        B2.g gVar = this.f8077o0;
        RectF rectF = this.f8034A;
        float fCenterX = rectF.centerX();
        B2.g gVar2 = this.f8077o0;
        gVar.f200c = fCenterX - gVar2.f198a;
        float fCenterY = rectF.centerY();
        B2.g gVar3 = this.f8077o0;
        gVar2.f201d = fCenterY - gVar3.f199b;
        gVar3.f202e = (float) Math.sqrt(Math.pow(this.f8077o0.f201d, 2.0d) + Math.pow(gVar3.f200c, 2.0d));
        B2.g gVar4 = this.f8077o0;
        float f2 = this.f8059e * 48.0f;
        gVar4.getClass();
        float f7 = this.f8078p;
        gVar4.f203f = f7 < f2 ? f2 / 2.0f : f7 / 2.0f;
        float fMax = Math.max(this.f8067i0, this.f8065h0);
        B2.g gVar5 = this.f8077o0;
        gVar4.f204g = fMax + gVar5.f203f;
        float fMin = Math.min(this.f8067i0, this.f8065h0);
        B2.g gVar6 = this.f8077o0;
        gVar5.h = fMin - gVar6.f203f;
        gVar6.f205i = (float) (((Math.atan2(gVar6.f199b, gVar6.f198a) / 3.141592653589793d) * 180.0d) % 360.0d);
        B2.g gVar7 = this.f8077o0;
        float f9 = gVar7.f205i;
        if (f9 < 0.0f) {
            f9 += 360.0f;
        }
        gVar7.f205i = f9;
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action != 1) {
                if (action != 2) {
                    if (action != 3) {
                        return true;
                    }
                    Log.d("CircularSeekBar", "MotionEvent.ACTION_CANCEL");
                    return false;
                }
                float f10 = this.f8077o0.f204g;
            }
            return false;
        }
        B2.g gVar8 = this.f8077o0;
        float f11 = gVar8.f205i;
        float f12 = gVar8.f202e;
        float f13 = gVar8.h;
        float f14 = gVar8.f204g;
        float fMax2 = Math.max((float) (((double) (this.f8085u * 180.0f)) / (((double) Math.max(this.f8067i0, this.f8065h0)) * 3.141592653589793d)), this.f8087w / 2.0f);
        float f15 = f11 - this.j0;
        if (f15 < 0.0f) {
            f15 += 360.0f;
        }
        float f16 = 360.0f - f15;
        float f17 = this.f8070k0;
        float f18 = f11 - f17;
        if (f18 < 0.0f) {
            f18 += 360.0f;
        }
        float f19 = 360.0f - f18;
        boolean z9 = f12 >= f13 && f12 <= f14;
        boolean z10 = f15 <= fMax2 || f16 <= fMax2;
        boolean z11 = f18 <= fMax2 || f19 <= fMax2;
        float fQ = AbstractC0752G.q(f17);
        float fQ2 = AbstractC0752G.q(this.j0);
        float fQ3 = AbstractC0752G.q(f11);
        return (z9 && z10 && z11) || (z9 && z10) || ((z9 && z11) || (z9 && ((fQ > fQ2 ? 1 : (fQ == fQ2 ? 0 : -1)) >= 0 ? !((fQ > fQ2 ? 1 : (fQ == fQ2 ? 0 : -1)) <= 0 || (((fQ3 > fQ ? 1 : (fQ3 == fQ ? 0 : -1)) <= 0 || (fQ3 > 1440.0f ? 1 : (fQ3 == 1440.0f ? 0 : -1)) > 0) && ((fQ3 > fQ2 ? 1 : (fQ3 == fQ2 ? 0 : -1)) >= 0 || (fQ3 > 0.0f ? 1 : (fQ3 == 0.0f ? 0 : -1)) <= 0))) : !((fQ3 > fQ ? 1 : (fQ3 == fQ ? 0 : -1)) <= 0 || (fQ3 > fQ2 ? 1 : (fQ3 == fQ2 ? 0 : -1)) >= 0))));
    }
}
