package androidx.appcompat.widget;

import android.R;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewDebug;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.RemoteViews;
import e.AbstractC0478a;
import java.lang.reflect.Field;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.WeakHashMap;
import l.C0663e;

/* JADX INFO: loaded from: classes.dex */
@RemoteViews.RemoteView
public class SeslProgressBar extends View {

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public static final DecelerateInterpolator f6574e0 = new DecelerateInterpolator();

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final int f6575A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public boolean f6576B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public boolean f6577C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public Transformation f6578D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public AlphaAnimation f6579E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public boolean f6580F;
    public Drawable G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public Drawable f6581H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public Drawable f6582I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public A1 f6583J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public int f6584K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final boolean f6585L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public Interpolator f6586M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public RunnableC0199w1 f6587N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public final long f6588O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public boolean f6589P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public boolean f6590Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public boolean f6591R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public boolean f6592S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public float f6593T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public final boolean f6594U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public boolean f6595V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public final ArrayList f6596W;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public RunnableC0199w1 f6597a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public NumberFormat f6598b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public Locale f6599c0;
    public final Y5.a d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f6600e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f6601f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f6602g;
    public int h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f6603i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Drawable f6604j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Drawable f6605k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Drawable f6606l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Drawable f6607m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Drawable f6608n;
    public C0208z1 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f6609p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f6610q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f6611r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f6612s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f6613t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f6614u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f6615v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f6616w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public int f6617x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f6618y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final int f6619z;

    public SeslProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.progressBarStyle);
    }

    public static boolean i(Drawable drawable) {
        if (!(drawable instanceof LayerDrawable)) {
            return !(drawable instanceof StateListDrawable) && (drawable instanceof BitmapDrawable);
        }
        LayerDrawable layerDrawable = (LayerDrawable) drawable;
        int numberOfLayers = layerDrawable.getNumberOfLayers();
        for (int i5 = 0; i5 < numberOfLayers; i5++) {
            if (i(layerDrawable.getDrawable(i5))) {
                return true;
            }
        }
        return false;
    }

    public final void a() {
        A1 a1;
        Drawable drawable = this.G;
        if (drawable == null || (a1 = this.f6583J) == null) {
            return;
        }
        if (a1.f6313c || a1.f6314d) {
            Drawable drawableMutate = drawable.mutate();
            this.G = drawableMutate;
            if (a1.f6313c) {
                E.a.h(drawableMutate, a1.f6311a);
            }
            if (a1.f6314d) {
                E.a.i(this.G, a1.f6312b);
            }
            if (this.G.isStateful()) {
                this.G.setState(getDrawableState());
            }
        }
    }

    public final void b() {
        Drawable drawableG;
        A1 a1 = this.f6583J;
        if ((a1.f6317g || a1.h) && (drawableG = g(R.id.progress, true)) != null) {
            A1 a12 = this.f6583J;
            if (a12.f6317g) {
                E.a.h(drawableG, a12.f6315e);
            }
            A1 a13 = this.f6583J;
            if (a13.h) {
                E.a.i(drawableG, a13.f6316f);
            }
            if (drawableG.isStateful()) {
                drawableG.setState(getDrawableState());
            }
        }
    }

    public final void c() {
        Drawable drawableG;
        A1 a1 = this.f6583J;
        if ((a1.f6320k || a1.f6321l) && (drawableG = g(R.id.background, false)) != null) {
            A1 a12 = this.f6583J;
            if (a12.f6320k) {
                E.a.h(drawableG, a12.f6318i);
            }
            A1 a13 = this.f6583J;
            if (a13.f6321l) {
                E.a.i(drawableG, a13.f6319j);
            }
            if (drawableG.isStateful()) {
                drawableG.setState(getDrawableState());
            }
        }
    }

    public final void d() {
        Drawable drawableG;
        A1 a1 = this.f6583J;
        if ((a1.o || a1.f6324p) && (drawableG = g(R.id.secondaryProgress, false)) != null) {
            A1 a12 = this.f6583J;
            if (a12.o) {
                E.a.h(drawableG, a12.f6322m);
            }
            A1 a13 = this.f6583J;
            if (a13.f6324p) {
                E.a.i(drawableG, a13.f6323n);
            }
            if (drawableG.isStateful()) {
                drawableG.setState(getDrawableState());
            }
        }
    }

    @Override // android.view.View
    public void drawableHotspotChanged(float f2, float f7) {
        super.drawableHotspotChanged(f2, f7);
        Drawable drawable = this.f6581H;
        if (drawable != null) {
            E.a.e(drawable, f2, f7);
        }
        Drawable drawable2 = this.G;
        if (drawable2 != null) {
            E.a.e(drawable2, f2, f7);
        }
    }

    @Override // android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        u();
    }

    public final synchronized void e(int i5, int i7, boolean z9, boolean z10, boolean z11) {
        try {
            int i9 = this.f6617x;
            int i10 = this.f6615v;
            int i11 = i9 - i10;
            float f2 = i11 > 0 ? (i7 - i10) / i11 : 0.0f;
            float f7 = i11 > 0 ? (this.f6593T - i10) / i11 : 0.0f;
            boolean z12 = i5 == 16908301;
            Drawable drawable = this.f6582I;
            if (drawable != null) {
                int i12 = (int) (10000.0f * f2);
                if (drawable instanceof LayerDrawable) {
                    Drawable drawableFindDrawableByLayerId = ((LayerDrawable) drawable).findDrawableByLayerId(i5);
                    if (drawableFindDrawableByLayerId != null && canResolveLayoutDirection()) {
                        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
                        E.b.b(drawableFindDrawableByLayerId, getLayoutDirection());
                    }
                    if (drawableFindDrawableByLayerId != null) {
                        drawable = drawableFindDrawableByLayerId;
                    }
                    drawable.setLevel(i12);
                } else if (drawable instanceof StateListDrawable) {
                } else {
                    drawable.setLevel(i12);
                }
            } else {
                invalidate();
            }
            if (z12 && z11) {
                ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, this.d0, f7, f2);
                objectAnimatorOfFloat.setAutoCancel(true);
                objectAnimatorOfFloat.setDuration(80L);
                objectAnimatorOfFloat.setInterpolator(f6574e0);
                objectAnimatorOfFloat.start();
            } else {
                o(f2, i5);
            }
            if (z12 && z10) {
                j(f2, z9, i7);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void f(Canvas canvas) {
        Drawable drawable = this.f6582I;
        if (drawable != 0) {
            int iSave = canvas.save();
            if (this.f6600e != 3 && this.f6594U && h2.a(this)) {
                canvas.translate(getWidth() - getPaddingRight(), getPaddingTop());
                canvas.scale(-1.0f, 1.0f);
            } else {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            long drawingTime = getDrawingTime();
            if (this.f6580F) {
                this.f6579E.getTransformation(drawingTime, this.f6578D);
                float alpha = this.f6578D.getAlpha();
                try {
                    this.f6590Q = true;
                    drawable.setLevel((int) (alpha * 10000.0f));
                    this.f6590Q = false;
                    WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
                    postInvalidateOnAnimation();
                } catch (Throwable th) {
                    this.f6590Q = false;
                    throw th;
                }
            }
            drawable.draw(canvas);
            canvas.restoreToCount(iSave);
            if (this.f6589P && (drawable instanceof Animatable)) {
                ((Animatable) drawable).start();
                this.f6589P = false;
            }
        }
    }

    public final Drawable g(int i5, boolean z9) {
        Drawable drawable = this.f6581H;
        if (drawable != null) {
            this.f6581H = drawable.mutate();
            drawableFindDrawableByLayerId = drawable instanceof LayerDrawable ? ((LayerDrawable) drawable).findDrawableByLayerId(i5) : null;
            if (z9 && drawableFindDrawableByLayerId == null) {
                return drawable;
            }
        }
        return drawableFindDrawableByLayerId;
    }

    @Override // android.view.View
    public CharSequence getAccessibilityClassName() {
        return ProgressBar.class.getName();
    }

    public Drawable getCurrentDrawable() {
        return this.f6582I;
    }

    public Drawable getIndeterminateDrawable() {
        return this.G;
    }

    public ColorStateList getIndeterminateTintList() {
        A1 a1 = this.f6583J;
        if (a1 != null) {
            return a1.f6311a;
        }
        return null;
    }

    public PorterDuff.Mode getIndeterminateTintMode() {
        A1 a1 = this.f6583J;
        if (a1 != null) {
            return a1.f6312b;
        }
        return null;
    }

    public Interpolator getInterpolator() {
        return this.f6586M;
    }

    @ViewDebug.ExportedProperty(category = "progress")
    public synchronized int getMax() {
        return this.f6617x;
    }

    public int getMaxHeight() {
        return this.f6612s;
    }

    public int getMaxWidth() {
        return this.f6610q;
    }

    @ViewDebug.ExportedProperty(category = "progress")
    public synchronized int getMin() {
        return this.f6615v;
    }

    public int getMinHeight() {
        return this.f6611r;
    }

    public int getMinWidth() {
        return this.f6609p;
    }

    public boolean getMirrorForRtl() {
        return this.f6594U;
    }

    @Override // android.view.View
    public int getPaddingLeft() {
        Field fieldS = com.bumptech.glide.c.s(View.class, "mPaddingLeft");
        if (fieldS != null) {
            Object objM = com.bumptech.glide.c.m(this, fieldS);
            if (objM instanceof Integer) {
                return ((Integer) objM).intValue();
            }
        }
        return 0;
    }

    @Override // android.view.View
    public int getPaddingRight() {
        Field fieldS = com.bumptech.glide.c.s(View.class, "mPaddingRight");
        if (fieldS != null) {
            Object objM = com.bumptech.glide.c.m(this, fieldS);
            if (objM instanceof Integer) {
                return ((Integer) objM).intValue();
            }
        }
        return 0;
    }

    @ViewDebug.ExportedProperty(category = "progress")
    public synchronized int getProgress() {
        return this.f6576B ? 0 : this.f6613t;
    }

    public ColorStateList getProgressBackgroundTintList() {
        A1 a1 = this.f6583J;
        if (a1 != null) {
            return a1.f6318i;
        }
        return null;
    }

    public PorterDuff.Mode getProgressBackgroundTintMode() {
        A1 a1 = this.f6583J;
        if (a1 != null) {
            return a1.f6319j;
        }
        return null;
    }

    public Drawable getProgressDrawable() {
        return this.f6581H;
    }

    public ColorStateList getProgressTintList() {
        A1 a1 = this.f6583J;
        if (a1 != null) {
            return a1.f6315e;
        }
        return null;
    }

    public PorterDuff.Mode getProgressTintMode() {
        A1 a1 = this.f6583J;
        if (a1 != null) {
            return a1.f6316f;
        }
        return null;
    }

    @ViewDebug.ExportedProperty(category = "progress")
    public synchronized int getSecondaryProgress() {
        return this.f6576B ? 0 : this.f6614u;
    }

    public ColorStateList getSecondaryProgressTintList() {
        A1 a1 = this.f6583J;
        if (a1 != null) {
            return a1.f6322m;
        }
        return null;
    }

    public PorterDuff.Mode getSecondaryProgressTintMode() {
        A1 a1 = this.f6583J;
        if (a1 != null) {
            return a1.f6323n;
        }
        return null;
    }

    public final void h(int i5) {
        if (getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_progress_bar_size_small) == i5) {
            this.f6602g = getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_progress_circle_size_small_width);
            this.h = getResources().getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_progress_circle_size_small_padding);
            return;
        }
        if (getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_progress_bar_size_small_title) == i5) {
            this.f6602g = getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_progress_circle_size_small_title_width);
            this.h = getResources().getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_progress_circle_size_small_title_padding);
        } else if (getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_progress_bar_size_large) == i5) {
            this.f6602g = getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_progress_circle_size_large_width);
            this.h = getResources().getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_progress_circle_size_large_padding);
        } else if (getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_progress_bar_size_xlarge) == i5) {
            this.f6602g = getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_progress_circle_size_xlarge_width);
            this.h = getResources().getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_progress_circle_size_xlarge_padding);
        } else {
            this.f6602g = (getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_progress_circle_size_small_width) * i5) / getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_progress_bar_size_small);
            this.h = (getResources().getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_progress_circle_size_small_padding) * i5) / getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_progress_bar_size_small);
        }
    }

    @Override // android.view.View, android.graphics.drawable.Drawable.Callback
    public final void invalidateDrawable(Drawable drawable) {
        if (this.f6590Q) {
            return;
        }
        if (!verifyDrawable(drawable)) {
            super.invalidateDrawable(drawable);
            return;
        }
        Rect bounds = drawable.getBounds();
        int paddingLeft = getPaddingLeft() + getScrollX();
        int paddingTop = getPaddingTop() + getScrollY();
        invalidate(bounds.left + paddingLeft, bounds.top + paddingTop, bounds.right + paddingLeft, bounds.bottom + paddingTop);
    }

    public void j(float f2, boolean z9, int i5) {
        if (((AccessibilityManager) getContext().getSystemService("accessibility")).isEnabled()) {
            RunnableC0199w1 runnableC0199w1 = this.f6597a0;
            if (runnableC0199w1 == null) {
                this.f6597a0 = new RunnableC0199w1(this, 0);
            } else {
                removeCallbacks(runnableC0199w1);
            }
            postDelayed(this.f6597a0, 200L);
        }
        int i7 = this.f6614u;
        if (i7 <= this.f6613t || z9) {
            return;
        }
        l(R.id.secondaryProgress, i7, false, false);
    }

    @Override // android.view.View
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f6581H;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.G;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
    }

    public void k(float f2, int i5) {
    }

    public final synchronized void l(int i5, int i7, boolean z9, boolean z10) {
        try {
            if (this.f6588O == Thread.currentThread().getId()) {
                e(i5, i7, z9, true, z10);
            } else {
                if (this.f6587N == null) {
                    this.f6587N = new RunnableC0199w1(this, 1);
                }
                B1 b12 = (B1) B1.f6408e.h();
                if (b12 == null) {
                    b12 = new B1();
                }
                b12.f6409a = i5;
                b12.f6410b = i7;
                b12.f6411c = z9;
                b12.f6412d = z10;
                this.f6596W.add(b12);
                if (this.f6591R && !this.f6592S) {
                    post(this.f6587N);
                    this.f6592S = true;
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void m(int i5) {
        if (getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_progress_bar_indeterminate_xsmall) >= i5) {
            setIndeterminateDrawable(this.f6604j);
            return;
        }
        if (getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_progress_bar_indeterminate_small) >= i5) {
            setIndeterminateDrawable(this.f6605k);
            return;
        }
        if (getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_progress_bar_indeterminate_medium) >= i5) {
            setIndeterminateDrawable(this.f6606l);
        } else if (getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_progress_bar_indeterminate_large) >= i5) {
            setIndeterminateDrawable(this.f6607m);
        } else {
            setIndeterminateDrawable(this.f6608n);
        }
    }

    public synchronized boolean n(int i5, boolean z9, boolean z10) {
        Drawable drawableFindDrawableByLayerId;
        try {
            if (this.f6576B) {
                return false;
            }
            int iD = com.bumptech.glide.c.d(i5, this.f6615v, this.f6617x);
            int i7 = this.f6613t;
            if (iD == i7) {
                return false;
            }
            this.f6593T = i7;
            this.f6613t = iD;
            if (this.f6600e == 7 && (getProgressDrawable() instanceof LayerDrawable) && (drawableFindDrawableByLayerId = ((LayerDrawable) getProgressDrawable()).findDrawableByLayerId(R.id.progress)) != null && (drawableFindDrawableByLayerId instanceof C0205y1)) {
                C0205y1 c0205y1 = (C0205y1) drawableFindDrawableByLayerId;
                if (z10) {
                    ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(c0205y1, c0205y1.f6904i, iD);
                    objectAnimatorOfInt.setAutoCancel(true);
                    objectAnimatorOfInt.setDuration(80L);
                    objectAnimatorOfInt.setInterpolator(f6574e0);
                    objectAnimatorOfInt.start();
                } else {
                    c0205y1.f6901e = iD;
                    c0205y1.f6905j.invalidate();
                }
            }
            l(R.id.progress, this.f6613t, z9, z10);
            return true;
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void o(float f2, int i5) {
        this.f6593T = f2;
        Drawable drawableFindDrawableByLayerId = this.f6582I;
        if ((drawableFindDrawableByLayerId instanceof LayerDrawable) && (drawableFindDrawableByLayerId = ((LayerDrawable) drawableFindDrawableByLayerId).findDrawableByLayerId(i5)) == null) {
            drawableFindDrawableByLayerId = this.f6582I;
        }
        if (drawableFindDrawableByLayerId != null) {
            drawableFindDrawableByLayerId.setLevel((int) (10000.0f * f2));
        } else {
            invalidate();
        }
        k(f2, i5);
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f6576B) {
            p();
        }
        synchronized (this) {
            try {
                int size = this.f6596W.size();
                for (int i5 = 0; i5 < size; i5++) {
                    B1 b12 = (B1) this.f6596W.get(i5);
                    e(b12.f6409a, b12.f6410b, b12.f6411c, true, b12.f6412d);
                    B1.f6408e.c(b12);
                }
                this.f6596W.clear();
            } catch (Throwable th) {
                throw th;
            }
        }
        this.f6591R = true;
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        if (this.f6576B) {
            q();
        } else {
            this.o = null;
        }
        RunnableC0199w1 runnableC0199w1 = this.f6587N;
        if (runnableC0199w1 != null) {
            removeCallbacks(runnableC0199w1);
            this.f6592S = false;
        }
        RunnableC0199w1 runnableC0199w12 = this.f6597a0;
        if (runnableC0199w12 != null) {
            removeCallbacks(runnableC0199w12);
        }
        super.onDetachedFromWindow();
        this.f6591R = false;
    }

    @Override // android.view.View
    public synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        f(canvas);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setItemCount(this.f6617x - this.f6615v);
        accessibilityEvent.setCurrentItemIndex(this.f6613t);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        boolean z9;
        boolean z10;
        String string;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        synchronized (this) {
            z9 = this.f6576B;
        }
        if (!z9) {
            accessibilityNodeInfo.setRangeInfo(AccessibilityNodeInfo.RangeInfo.obtain(0, getMin(), getMax(), getProgress()));
        }
        if (getStateDescription() == null) {
            synchronized (this) {
                z10 = this.f6576B;
            }
            if (z10) {
                Context context = getContext();
                int identifier = context.getResources().getIdentifier("in_progress", "string", "android");
                if (identifier > 0) {
                    try {
                        string = context.getResources().getString(identifier);
                    } catch (Resources.NotFoundException unused) {
                        string = "";
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        string = "";
                    }
                } else {
                    string = "";
                }
                accessibilityNodeInfo.setStateDescription(string);
                return;
            }
            int progress = getProgress();
            Locale locale = getResources().getConfiguration().locale;
            if (!locale.equals(this.f6599c0) || this.f6598b0 == null) {
                this.f6599c0 = locale;
                this.f6598b0 = NumberFormat.getPercentInstance(locale);
            }
            accessibilityNodeInfo.setStateDescription(this.f6598b0.format(getMax() - getMin() > 0.0f ? com.bumptech.glide.c.c((progress - r5) / r2, 0.0f, 1.0f) : 0.0f));
        }
    }

    @Override // android.view.View
    public synchronized void onMeasure(int i5, int i7) {
        int iMax;
        int iMax2;
        try {
            Drawable drawable = this.f6582I;
            if (drawable != null) {
                iMax2 = Math.max(this.f6609p, Math.min(this.f6610q, drawable.getIntrinsicWidth()));
                iMax = Math.max(this.f6611r, Math.min(this.f6612s, drawable.getIntrinsicHeight()));
            } else {
                iMax = 0;
                iMax2 = 0;
            }
            u();
            int paddingLeft = getPaddingLeft() + getPaddingRight() + iMax2;
            int paddingTop = getPaddingTop() + getPaddingBottom() + iMax;
            int iResolveSizeAndState = View.resolveSizeAndState(paddingLeft, i5, 0);
            int iResolveSizeAndState2 = View.resolveSizeAndState(paddingTop, i7, 0);
            h((iResolveSizeAndState - getPaddingLeft()) - getPaddingRight());
            if (this.f6603i && this.f6576B) {
                m((iResolveSizeAndState - getPaddingLeft()) - getPaddingRight());
            }
            setMeasuredDimension(iResolveSizeAndState, iResolveSizeAndState2);
        } catch (Throwable th) {
            throw th;
        }
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        C1 c12 = (C1) parcelable;
        super.onRestoreInstanceState(c12.getSuperState());
        setProgress(c12.f6418e);
        setSecondaryProgress(c12.f6419f);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        C1 c12 = new C1(super.onSaveInstanceState());
        c12.f6418e = this.f6613t;
        c12.f6419f = this.f6614u;
        return c12;
    }

    @Override // android.view.View
    public void onSizeChanged(int i5, int i7, int i9, int i10) {
        t(i5, i7);
    }

    @Override // android.view.View
    public final void onVisibilityAggregated(boolean z9) {
        super.onVisibilityAggregated(z9);
        if (z9 != this.f6595V) {
            this.f6595V = z9;
            if (this.f6576B) {
                if (z9) {
                    p();
                } else {
                    q();
                }
            }
            Drawable drawable = this.f6582I;
            if (drawable != null) {
                drawable.setVisible(z9, false);
            }
        }
    }

    public final void p() {
        if (getVisibility() == 0) {
            Drawable drawable = this.G;
            if (drawable instanceof Animatable) {
                this.f6589P = true;
                this.f6580F = false;
                if (drawable instanceof AnimatedVectorDrawable) {
                    ((AnimatedVectorDrawable) drawable).registerAnimationCallback(this.o);
                }
            } else {
                this.f6580F = true;
                if (this.f6586M == null) {
                    this.f6586M = new LinearInterpolator();
                }
                Transformation transformation = this.f6578D;
                if (transformation == null) {
                    this.f6578D = new Transformation();
                } else {
                    transformation.clear();
                }
                AlphaAnimation alphaAnimation = this.f6579E;
                if (alphaAnimation == null) {
                    this.f6579E = new AlphaAnimation(0.0f, 1.0f);
                } else {
                    alphaAnimation.reset();
                }
                this.f6579E.setRepeatMode(this.f6619z);
                this.f6579E.setRepeatCount(-1);
                this.f6579E.setDuration(this.f6575A);
                this.f6579E.setInterpolator(this.f6586M);
                this.f6579E.setStartTime(-1L);
            }
            postInvalidate();
        }
    }

    @Override // android.view.View
    public final void postInvalidate() {
        if (this.f6585L) {
            return;
        }
        super.postInvalidate();
    }

    public final void q() {
        this.f6580F = false;
        Object obj = this.G;
        if (obj instanceof Animatable) {
            ((Animatable) obj).stop();
            Drawable drawable = this.G;
            if (drawable instanceof AnimatedVectorDrawable) {
                ((AnimatedVectorDrawable) drawable).unregisterAnimationCallback(this.o);
            }
            this.f6589P = false;
        }
        postInvalidate();
    }

    public final void r(Drawable drawable) {
        Drawable drawable2 = this.f6582I;
        this.f6582I = drawable;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setVisible(false, false);
            }
            Drawable drawable3 = this.f6582I;
            if (drawable3 != null) {
                drawable3.setVisible(getWindowVisibility() == 0 && isShown(), false);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [android.graphics.drawable.Drawable] */
    /* JADX WARN: Type inference failed for: r8v1, types: [android.graphics.drawable.Drawable] */
    /* JADX WARN: Type inference failed for: r8v4, types: [android.graphics.drawable.BitmapDrawable, android.graphics.drawable.Drawable] */
    public final Drawable s(Drawable drawable, boolean z9) {
        if (!(drawable instanceof LayerDrawable)) {
            if (drawable instanceof StateListDrawable) {
                return new StateListDrawable();
            }
            if (drawable instanceof BitmapDrawable) {
                drawable = (BitmapDrawable) drawable.getConstantState().newDrawable(getResources());
                drawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
                if (this.f6584K <= 0) {
                    this.f6584K = drawable.getIntrinsicWidth();
                }
                if (z9) {
                    return new ClipDrawable(drawable, 3, 1);
                }
            }
            return drawable;
        }
        LayerDrawable layerDrawable = (LayerDrawable) drawable;
        int numberOfLayers = layerDrawable.getNumberOfLayers();
        Drawable[] drawableArr = new Drawable[numberOfLayers];
        for (int i5 = 0; i5 < numberOfLayers; i5++) {
            int id = layerDrawable.getId(i5);
            drawableArr[i5] = s(layerDrawable.getDrawable(i5), id == 16908301 || id == 16908303);
        }
        LayerDrawable layerDrawable2 = new LayerDrawable(drawableArr);
        for (int i7 = 0; i7 < numberOfLayers; i7++) {
            layerDrawable2.setId(i7, layerDrawable.getId(i7));
            layerDrawable2.setLayerGravity(i7, layerDrawable.getLayerGravity(i7));
            layerDrawable2.setLayerWidth(i7, layerDrawable.getLayerWidth(i7));
            layerDrawable2.setLayerHeight(i7, layerDrawable.getLayerHeight(i7));
            layerDrawable2.setLayerInsetLeft(i7, layerDrawable.getLayerInsetLeft(i7));
            layerDrawable2.setLayerInsetRight(i7, layerDrawable.getLayerInsetRight(i7));
            layerDrawable2.setLayerInsetTop(i7, layerDrawable.getLayerInsetTop(i7));
            layerDrawable2.setLayerInsetBottom(i7, layerDrawable.getLayerInsetBottom(i7));
            layerDrawable2.setLayerInsetStart(i7, layerDrawable.getLayerInsetStart(i7));
            layerDrawable2.setLayerInsetEnd(i7, layerDrawable.getLayerInsetEnd(i7));
        }
        return layerDrawable2;
    }

    public synchronized void setIndeterminate(boolean z9) {
        try {
            if (!this.f6577C || !this.f6576B) {
                if (z9 != this.f6576B) {
                    this.f6576B = z9;
                    if (z9) {
                        r(this.G);
                        p();
                    } else {
                        r(this.f6581H);
                        q();
                    }
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public void setIndeterminateDrawable(Drawable drawable) {
        Drawable drawable2 = this.G;
        if (drawable2 != drawable) {
            boolean z9 = this.f6603i;
            if (drawable2 != null) {
                if (z9) {
                    q();
                }
                this.G.setCallback(null);
                unscheduleDrawable(this.G);
            }
            this.G = drawable;
            if (drawable != null) {
                drawable.setCallback(this);
                WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
                E.b.b(drawable, getLayoutDirection());
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
                a();
            }
            if (this.f6576B) {
                if (z9) {
                    p();
                }
                r(drawable);
                postInvalidate();
            }
        }
    }

    public void setIndeterminateDrawableTiled(Drawable drawable) {
        if (drawable != null && (drawable instanceof AnimationDrawable)) {
            AnimationDrawable animationDrawable = (AnimationDrawable) drawable;
            int numberOfFrames = animationDrawable.getNumberOfFrames();
            AnimationDrawable animationDrawable2 = new AnimationDrawable();
            animationDrawable2.setOneShot(animationDrawable.isOneShot());
            for (int i5 = 0; i5 < numberOfFrames; i5++) {
                Drawable drawableS = s(animationDrawable.getFrame(i5), true);
                drawableS.setLevel(10000);
                animationDrawable2.addFrame(drawableS, animationDrawable.getDuration(i5));
            }
            animationDrawable2.setLevel(10000);
            drawable = animationDrawable2;
        }
        setIndeterminateDrawable(drawable);
    }

    public void setIndeterminateTintList(ColorStateList colorStateList) {
        if (this.f6583J == null) {
            this.f6583J = new A1();
        }
        A1 a1 = this.f6583J;
        a1.f6311a = colorStateList;
        a1.f6313c = true;
        a();
    }

    public void setIndeterminateTintMode(PorterDuff.Mode mode) {
        if (this.f6583J == null) {
            this.f6583J = new A1();
        }
        A1 a1 = this.f6583J;
        a1.f6312b = mode;
        a1.f6314d = true;
        a();
    }

    public void setInterpolator(Interpolator interpolator) {
        this.f6586M = interpolator;
    }

    public synchronized void setMax(int i5) {
        int i7;
        try {
            boolean z9 = this.f6616w;
            if (z9 && i5 < (i7 = this.f6615v)) {
                i5 = i7;
            }
            this.f6618y = true;
            if (!z9 || i5 == this.f6617x) {
                this.f6617x = i5;
            } else {
                this.f6617x = i5;
                postInvalidate();
                if (this.f6613t > i5) {
                    this.f6613t = i5;
                }
                l(R.id.progress, this.f6613t, false, false);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public void setMaxHeight(int i5) {
        this.f6612s = i5;
        requestLayout();
    }

    public void setMaxWidth(int i5) {
        this.f6610q = i5;
        requestLayout();
    }

    public synchronized void setMin(int i5) {
        int i7;
        try {
            boolean z9 = this.f6618y;
            if (z9 && i5 > (i7 = this.f6617x)) {
                i5 = i7;
            }
            this.f6616w = true;
            if (!z9 || i5 == this.f6615v) {
                this.f6615v = i5;
            } else {
                this.f6615v = i5;
                postInvalidate();
                if (this.f6613t < i5) {
                    this.f6613t = i5;
                }
                l(R.id.progress, this.f6613t, false, false);
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public void setMinHeight(int i5) {
        this.f6611r = i5;
        requestLayout();
    }

    public void setMinWidth(int i5) {
        this.f6609p = i5;
        requestLayout();
    }

    public void setMode(int i5) {
        Drawable drawableB;
        this.f6600e = i5;
        if (i5 == 3) {
            drawableB = B.a.b(getContext(), com.samsung.android.keyscafe.R.drawable.sesl_scrubber_progress_vertical);
        } else if (i5 != 4) {
            if (i5 == 7) {
                this.f6577C = false;
                setIndeterminate(false);
                LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{new C0205y1(this, true, new ColorStateList(new int[][]{new int[0]}, new int[]{getResources().getColor(com.samsung.android.keyscafe.R.color.sesl_progress_control_color_background)})), new C0205y1(this, false, new ColorStateList(new int[][]{new int[0]}, new int[]{getResources().getColor(com.samsung.android.keyscafe.R.color.sesl_progress_control_color_activated_light)}))});
                layerDrawable.setPaddingMode(1);
                layerDrawable.setId(0, R.id.background);
                layerDrawable.setId(1, R.id.progress);
                setProgressDrawable(layerDrawable);
            }
            drawableB = null;
        } else {
            drawableB = B.a.b(getContext(), com.samsung.android.keyscafe.R.drawable.sesl_split_seekbar_background_progress);
        }
        if (drawableB != null) {
            setProgressDrawableTiled(drawableB);
        }
    }

    public synchronized void setProgress(int i5) {
        n(i5, false, false);
    }

    public void setProgressBackgroundTintList(ColorStateList colorStateList) {
        if (this.f6583J == null) {
            this.f6583J = new A1();
        }
        A1 a1 = this.f6583J;
        a1.f6318i = colorStateList;
        a1.f6320k = true;
        if (this.f6581H != null) {
            c();
        }
    }

    public void setProgressBackgroundTintMode(PorterDuff.Mode mode) {
        if (this.f6583J == null) {
            this.f6583J = new A1();
        }
        A1 a1 = this.f6583J;
        a1.f6319j = mode;
        a1.f6321l = true;
        if (this.f6581H != null) {
            c();
        }
    }

    public void setProgressDrawable(Drawable drawable) {
        Drawable drawable2 = this.f6581H;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
                unscheduleDrawable(this.f6581H);
            }
            this.f6581H = drawable;
            if (drawable != null) {
                drawable.setCallback(this);
                WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
                E.b.b(drawable, getLayoutDirection());
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
                if (this.f6600e == 3) {
                    int minimumWidth = drawable.getMinimumWidth();
                    if (this.f6610q < minimumWidth) {
                        this.f6610q = minimumWidth;
                        requestLayout();
                    }
                } else {
                    int minimumHeight = drawable.getMinimumHeight();
                    if (this.f6612s < minimumHeight) {
                        this.f6612s = minimumHeight;
                        requestLayout();
                    }
                }
                if (this.f6581H != null && this.f6583J != null) {
                    b();
                    c();
                    d();
                }
            }
            if (!this.f6576B) {
                r(drawable);
                postInvalidate();
            }
            t(getWidth(), getHeight());
            u();
            e(R.id.progress, this.f6613t, false, false, false);
            e(R.id.secondaryProgress, this.f6614u, false, false, false);
            if (getImportantForAccessibility() == 0) {
                setImportantForAccessibility(1);
            }
        }
    }

    public void setProgressDrawableTiled(Drawable drawable) {
        if (drawable != null) {
            drawable = s(drawable, false);
        }
        setProgressDrawable(drawable);
    }

    public void setProgressTintList(ColorStateList colorStateList) {
        if (this.f6583J == null) {
            this.f6583J = new A1();
        }
        A1 a1 = this.f6583J;
        a1.f6315e = colorStateList;
        a1.f6317g = true;
        if (this.f6581H != null) {
            b();
        }
    }

    public void setProgressTintMode(PorterDuff.Mode mode) {
        if (this.f6583J == null) {
            this.f6583J = new A1();
        }
        A1 a1 = this.f6583J;
        a1.f6316f = mode;
        a1.h = true;
        if (this.f6581H != null) {
            b();
        }
    }

    public synchronized void setSecondaryProgress(int i5) {
        if (this.f6576B) {
            return;
        }
        int i7 = this.f6615v;
        if (i5 < i7) {
            i5 = i7;
        }
        int i9 = this.f6617x;
        if (i5 > i9) {
            i5 = i9;
        }
        if (i5 != this.f6614u) {
            this.f6614u = i5;
            l(R.id.secondaryProgress, i5, false, false);
        }
    }

    public void setSecondaryProgressTintList(ColorStateList colorStateList) {
        if (this.f6583J == null) {
            this.f6583J = new A1();
        }
        A1 a1 = this.f6583J;
        a1.f6322m = colorStateList;
        a1.o = true;
        if (this.f6581H != null) {
            d();
        }
    }

    public void setSecondaryProgressTintMode(PorterDuff.Mode mode) {
        if (this.f6583J == null) {
            this.f6583J = new A1();
        }
        A1 a1 = this.f6583J;
        a1.f6323n = mode;
        a1.f6324p = true;
        if (this.f6581H != null) {
            d();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0072  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void t(int r11, int r12) {
        /*
            r10 = this;
            int r0 = r10.getPaddingRight()
            int r1 = r10.getPaddingLeft()
            int r1 = r1 + r0
            int r11 = r11 - r1
            int r0 = r10.getPaddingTop()
            int r1 = r10.getPaddingBottom()
            int r1 = r1 + r0
            int r12 = r12 - r1
            android.graphics.drawable.Drawable r0 = r10.G
            r1 = 0
            if (r0 == 0) goto L78
            boolean r2 = r10.f6577C
            if (r2 == 0) goto L60
            boolean r2 = r0 instanceof android.graphics.drawable.AnimationDrawable
            if (r2 != 0) goto L60
            int r0 = r0.getIntrinsicWidth()
            android.graphics.drawable.Drawable r2 = r10.G
            int r2 = r2.getIntrinsicHeight()
            float r0 = (float) r0
            float r2 = (float) r2
            float r0 = r0 / r2
            float r2 = (float) r11
            float r3 = (float) r12
            float r4 = r2 / r3
            float r5 = r0 - r4
            float r5 = java.lang.Math.abs(r5)
            double r5 = (double) r5
            r7 = 4502148214488346440(0x3e7ad7f29abcaf48, double:1.0E-7)
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 >= 0) goto L60
            int r4 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r4 <= 0) goto L51
            float r3 = r3 * r0
            int r0 = (int) r3
            int r2 = r11 - r0
            int r2 = r2 / 2
            int r0 = r0 + r2
            r3 = r2
            r2 = r0
            r0 = r1
            goto L63
        L51:
            r3 = 1065353216(0x3f800000, float:1.0)
            float r3 = r3 / r0
            float r3 = r3 * r2
            int r0 = (int) r3
            int r12 = r12 - r0
            int r12 = r12 / 2
            int r0 = r0 + r12
            r2 = r11
            r3 = r1
            r9 = r0
            r0 = r12
            r12 = r9
            goto L63
        L60:
            r2 = r11
            r0 = r1
            r3 = r0
        L63:
            boolean r4 = r10.f6594U
            if (r4 == 0) goto L72
            boolean r4 = androidx.appcompat.widget.h2.a(r10)
            if (r4 == 0) goto L72
            int r2 = r11 - r2
            int r11 = r11 - r3
            r3 = r2
            goto L73
        L72:
            r11 = r2
        L73:
            android.graphics.drawable.Drawable r2 = r10.G
            r2.setBounds(r3, r0, r11, r12)
        L78:
            android.graphics.drawable.Drawable r10 = r10.f6581H
            if (r10 == 0) goto L7f
            r10.setBounds(r1, r1, r11, r12)
        L7f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SeslProgressBar.t(int, int):void");
    }

    public final void u() {
        int[] drawableState = getDrawableState();
        Drawable drawable = this.f6581H;
        boolean state = (drawable == null || !drawable.isStateful()) ? false : drawable.setState(drawableState);
        Drawable drawable2 = this.G;
        if (drawable2 != null && drawable2.isStateful()) {
            state |= drawable2.setState(drawableState);
        }
        if (state) {
            invalidate();
        }
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return drawable == this.f6581H || drawable == this.G || super.verifyDrawable(drawable);
    }

    public SeslProgressBar(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5, 0);
        this.f6600e = 0;
        this.f6603i = false;
        this.f6584K = 0;
        this.f6594U = false;
        this.f6596W = new ArrayList();
        this.d0 = new Y5.a("visual_progress", 1);
        this.f6588O = Thread.currentThread().getId();
        this.f6615v = 0;
        this.f6617x = 100;
        this.f6613t = 0;
        this.f6614u = 0;
        this.f6576B = false;
        this.f6577C = false;
        this.f6575A = 4000;
        this.f6619z = 1;
        this.f6609p = 24;
        this.f6610q = 48;
        this.f6611r = 24;
        this.f6612s = 48;
        int[] iArr = AbstractC0478a.f10574u;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i5, 0);
        try {
            saveAttributeDataForStyleable(context, iArr, attributeSet, typedArrayObtainStyledAttributes, i5, 0);
            this.f6585L = true;
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(8);
            if (drawable != null) {
                if (i(drawable)) {
                    setProgressDrawableTiled(drawable);
                } else {
                    setProgressDrawable(drawable);
                }
            }
            this.f6575A = typedArrayObtainStyledAttributes.getInt(9, this.f6575A);
            this.f6609p = typedArrayObtainStyledAttributes.getDimensionPixelSize(11, this.f6609p);
            this.f6610q = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, this.f6610q);
            this.f6611r = typedArrayObtainStyledAttributes.getDimensionPixelSize(12, this.f6611r);
            this.f6612s = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, this.f6612s);
            this.f6619z = typedArrayObtainStyledAttributes.getInt(10, this.f6619z);
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(13, R.anim.linear_interpolator);
            if (resourceId > 0) {
                setInterpolator(AnimationUtils.loadInterpolator(context, resourceId));
            }
            setMin(typedArrayObtainStyledAttributes.getInt(26, this.f6615v));
            setMax(typedArrayObtainStyledAttributes.getInt(2, this.f6617x));
            setProgress(typedArrayObtainStyledAttributes.getInt(3, this.f6613t));
            setSecondaryProgress(typedArrayObtainStyledAttributes.getInt(4, this.f6614u));
            Drawable drawable2 = typedArrayObtainStyledAttributes.getDrawable(7);
            if (drawable2 != null) {
                if (i(drawable2)) {
                    setIndeterminateDrawableTiled(drawable2);
                } else {
                    setIndeterminateDrawable(drawable2);
                }
            }
            boolean z9 = typedArrayObtainStyledAttributes.getBoolean(6, this.f6577C);
            this.f6577C = z9;
            this.f6585L = false;
            setIndeterminate(z9 || typedArrayObtainStyledAttributes.getBoolean(5, this.f6576B));
            this.f6594U = typedArrayObtainStyledAttributes.getBoolean(15, false);
            if (typedArrayObtainStyledAttributes.hasValue(17)) {
                if (this.f6583J == null) {
                    this.f6583J = new A1();
                }
                this.f6583J.f6316f = AbstractC0183r0.b(typedArrayObtainStyledAttributes.getInt(17, -1), null);
                this.f6583J.h = true;
            }
            if (typedArrayObtainStyledAttributes.hasValue(16)) {
                if (this.f6583J == null) {
                    this.f6583J = new A1();
                }
                this.f6583J.f6315e = typedArrayObtainStyledAttributes.getColorStateList(16);
                this.f6583J.f6317g = true;
            }
            if (typedArrayObtainStyledAttributes.hasValue(19)) {
                if (this.f6583J == null) {
                    this.f6583J = new A1();
                }
                this.f6583J.f6319j = AbstractC0183r0.b(typedArrayObtainStyledAttributes.getInt(19, -1), null);
                this.f6583J.f6321l = true;
            }
            if (typedArrayObtainStyledAttributes.hasValue(18)) {
                if (this.f6583J == null) {
                    this.f6583J = new A1();
                }
                this.f6583J.f6318i = typedArrayObtainStyledAttributes.getColorStateList(18);
                this.f6583J.f6320k = true;
            }
            if (typedArrayObtainStyledAttributes.hasValue(21)) {
                if (this.f6583J == null) {
                    this.f6583J = new A1();
                }
                this.f6583J.f6323n = AbstractC0183r0.b(typedArrayObtainStyledAttributes.getInt(21, -1), null);
                this.f6583J.f6324p = true;
            }
            if (typedArrayObtainStyledAttributes.hasValue(20)) {
                if (this.f6583J == null) {
                    this.f6583J = new A1();
                }
                this.f6583J.f6322m = typedArrayObtainStyledAttributes.getColorStateList(20);
                this.f6583J.o = true;
            }
            if (typedArrayObtainStyledAttributes.hasValue(23)) {
                if (this.f6583J == null) {
                    this.f6583J = new A1();
                }
                this.f6583J.f6312b = AbstractC0183r0.b(typedArrayObtainStyledAttributes.getInt(23, -1), null);
                this.f6583J.f6314d = true;
            }
            if (typedArrayObtainStyledAttributes.hasValue(22)) {
                if (this.f6583J == null) {
                    this.f6583J = new A1();
                }
                this.f6583J.f6311a = typedArrayObtainStyledAttributes.getColorStateList(22);
                this.f6583J.f6313c = true;
            }
            this.f6603i = typedArrayObtainStyledAttributes.getBoolean(27, false);
            C0663e c0663e = new C0663e(context, 2132017403);
            this.f6604j = getResources().getDrawable(com.samsung.android.keyscafe.R.drawable.sesl_progress_bar_indeterminate_xsmall_transition, c0663e.getTheme());
            this.f6605k = getResources().getDrawable(com.samsung.android.keyscafe.R.drawable.sesl_progress_bar_indeterminate_small_transition, c0663e.getTheme());
            this.f6606l = getResources().getDrawable(com.samsung.android.keyscafe.R.drawable.sesl_progress_bar_indeterminate_medium_transition, c0663e.getTheme());
            this.f6607m = getResources().getDrawable(com.samsung.android.keyscafe.R.drawable.sesl_progress_bar_indeterminate_large_transition, c0663e.getTheme());
            this.f6608n = getResources().getDrawable(com.samsung.android.keyscafe.R.drawable.sesl_progress_bar_indeterminate_xlarge_transition, c0663e.getTheme());
            typedArrayObtainStyledAttributes.recycle();
            if (this.f6581H != null && this.f6583J != null) {
                b();
                c();
                d();
            }
            a();
            WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
            if (getImportantForAccessibility() == 0) {
                setImportantForAccessibility(1);
            }
            this.f6601f = context.getResources().getDisplayMetrics().density;
            this.o = new C0208z1(this);
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }
}
