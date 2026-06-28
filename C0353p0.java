package androidx.recyclerview.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.PathInterpolator;
import android.widget.SectionIndexer;
import com.samsung.android.keyscafe.R;

/* JADX INFO: renamed from: androidx.recyclerview.widget.p0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0353p0 extends View {

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public static final /* synthetic */ int f9211K = 0;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f9212A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public Object[] f9213B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final SectionIndexer f9214C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public ValueAnimator f9215D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public int f9216E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final PathInterpolator f9217F;
    public final PathInterpolator G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final RunnableC0351o0 f9218H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public final RunnableC0351o0 f9219I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f9220J;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Paint f9221e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final TextPaint f9222f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f9223g;
    public int h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f9224i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f9225j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f9226k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f9227l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f9228m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f9229n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f9230p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f9231q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final float f9232r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f9233s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public String f9234t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public String f9235u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public String f9236v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public StaticLayout f9237w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public StaticLayout f9238x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public StaticLayout.Builder f9239y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f9240z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0353p0(RecyclerView recyclerView, Context context) {
        super(context);
        this.f9220J = recyclerView;
        this.f9223g = false;
        this.f9240z = false;
        this.f9212A = false;
        this.f9217F = new PathInterpolator(0.0f, 0.0f, 1.0f, 1.0f);
        this.G = new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);
        this.f9218H = new RunnableC0351o0(this, 0);
        this.f9219I = new RunnableC0351o0(this, 1);
        SectionIndexer sectionIndexer = (SectionIndexer) recyclerView.mAdapter;
        this.f9214C = sectionIndexer;
        if (sectionIndexer != null) {
            Object[] sections = sectionIndexer.getSections();
            this.f9213B = sections;
            if (sections == null) {
                throw new IllegalStateException("Section is null. This array, or its contents should be non-null");
            }
            b();
        }
        Resources resources = recyclerView.mContext.getResources();
        int color = s6.c.O(recyclerView.mContext) ? resources.getColor(R.color.sesl_scrollbar_index_tip_color) : resources.getColor(R.color.sesl_scrollbar_index_tip_color_dark);
        Paint paint = new Paint();
        this.f9221e = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f9221e.setAntiAlias(true);
        this.f9221e.setColor(Color.argb(Math.round(Color.alpha(color) * 0.9f), Color.red(color), Color.green(color), Color.blue(color)));
        TextPaint textPaint = new TextPaint();
        this.f9222f = textPaint;
        textPaint.setAntiAlias(true);
        if (Build.VERSION.SDK_INT >= 34) {
            this.f9222f.setTypeface(Typeface.create(Typeface.create("sec", 0), 400, false));
        } else {
            this.f9222f.setTypeface(Typeface.create(recyclerView.mContext.getString(R.string.sesl_font_family_regular), 0));
        }
        this.f9222f.setTextSize(resources.getDimensionPixelSize(R.dimen.sesl_index_tip_text_size));
        this.f9222f.setColor(resources.getColor(R.color.sesl_white));
        this.f9234t = "";
        TextPaint textPaint2 = this.f9222f;
        StaticLayout staticLayoutBuild = StaticLayout.Builder.obtain("", 0, 0, textPaint2, (int) textPaint2.measureText("")).build();
        this.f9237w = staticLayoutBuild;
        this.f9238x = staticLayoutBuild;
        this.f9236v = "";
        this.f9231q = 0.0f;
        this.f9230p = 0.0f;
        this.f9225j = resources.getDimensionPixelSize(R.dimen.sesl_index_tip_horizontal_padding);
        this.f9226k = resources.getDimensionPixelSize(R.dimen.sesl_index_tip_vertical_padding);
        this.f9229n = resources.getDimensionPixelSize(R.dimen.sesl_index_tip_min_width);
        this.f9228m = resources.getDimensionPixelSize(R.dimen.sesl_index_tip_max_width);
        this.f9224i = resources.getDimensionPixelSize(R.dimen.sesl_index_tip_margin_top);
        this.f9232r = resources.getDimension(R.dimen.sesl_index_tip_radius);
        int identifier = resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            this.f9233s = recyclerView.mContext.getResources().getDimensionPixelSize(identifier);
        } else {
            this.f9233s = 0;
        }
        setAlpha(0.0f);
    }

    public static void a(C0353p0 c0353p0, int i5, int i7, int i9, int i10) {
        c0353p0.layout(0, 0, i5, i7);
        int i11 = (i5 - i9) - i10;
        int i12 = c0353p0.f9225j * 2;
        if (i11 > i12) {
            int i13 = c0353p0.f9228m;
            if (i11 < i13) {
                i13 = i11 - i12;
            }
            c0353p0.o = i13;
        }
        c0353p0.h = Math.round(i11 / 2.0f) + i9;
        int i14 = c0353p0.f9220J.mContext.getResources().getConfiguration().orientation;
        c0353p0.f9216E = i14;
        if (i14 == 2) {
            c0353p0.f9223g = false;
        }
        c0353p0.b();
    }

    public final void b() {
        boolean z9 = this.f9240z;
        RunnableC0351o0 runnableC0351o0 = this.f9219I;
        if (z9) {
            removeCallbacks(runnableC0351o0);
            postDelayed(runnableC0351o0, 300L);
        } else {
            this.f9240z = false;
            removeCallbacks(runnableC0351o0);
            setAlpha(0.0f);
            invalidate();
        }
    }

    public final void c() {
        boolean z9 = this.f9240z;
        RecyclerView recyclerView = this.f9220J;
        ObjectAnimator objectAnimatorOfFloat = z9 ? ObjectAnimator.ofFloat(recyclerView.mIndexTip, "alpha", recyclerView.mIndexTip.getAlpha(), 0.0f) : ObjectAnimator.ofFloat(recyclerView.mIndexTip, "alpha", recyclerView.mIndexTip.getAlpha(), 1.0f);
        objectAnimatorOfFloat.setDuration(150L);
        objectAnimatorOfFloat.setInterpolator(this.f9217F);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimatorOfFloat);
        animatorSet.start();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        Object obj;
        int recyclerViewScreenLocationY;
        int i5;
        int i7 = this.f9226k;
        int i9 = this.f9225j;
        TextPaint textPaint = this.f9222f;
        super.onDraw(canvas);
        this.f9234t = "";
        RecyclerView recyclerView = this.f9220J;
        int iFindFirstVisibleItemPosition = recyclerView.findFirstVisibleItemPosition();
        if (iFindFirstVisibleItemPosition == -1) {
            Log.e("SeslRecyclerView", "First visible item was null.");
        } else {
            int sectionForPosition = this.f9214C.getSectionForPosition(iFindFirstVisibleItemPosition);
            if (sectionForPosition >= 0) {
                Object[] objArr = this.f9213B;
                if (sectionForPosition < objArr.length && (obj = objArr[sectionForPosition]) != null) {
                    String string = obj.toString();
                    this.f9234t = string;
                    StaticLayout.Builder builderObtain = StaticLayout.Builder.obtain(string, 0, string.length(), textPaint, (int) textPaint.measureText(this.f9234t));
                    this.f9239y = builderObtain;
                    this.f9237w = builderObtain.build();
                }
            }
        }
        if (!this.f9234t.equals("")) {
            this.f9212A = false;
        } else {
            if (this.f9236v.equals("")) {
                return;
            }
            if (!this.f9212A && this.f9240z) {
                c();
                this.f9240z = false;
                this.f9212A = true;
            }
            this.f9234t = this.f9236v;
        }
        float fMeasureText = (textPaint.measureText(this.f9234t) / 2.0f) + i9;
        float f2 = this.f9229n / 2.0f;
        if (fMeasureText < f2) {
            fMeasureText = f2;
        } else {
            int i10 = this.o;
            if (fMeasureText > i10 / 2.0f) {
                String str = this.f9234t;
                float lineWidth = StaticLayout.Builder.obtain(str, 0, str.length(), textPaint, ((i10 / 2) - i9) * 2).build().getLineWidth(0);
                String str2 = this.f9234t;
                StaticLayout.Builder builderObtain2 = StaticLayout.Builder.obtain(str2, 0, str2.length(), textPaint, (int) lineWidth);
                this.f9239y = builderObtain2;
                builderObtain2.setAlignment(Layout.Alignment.ALIGN_CENTER);
                StaticLayout staticLayoutBuild = this.f9239y.setMaxLines(2).setEllipsize(TextUtils.TruncateAt.END).build();
                this.f9237w = staticLayoutBuild;
                fMeasureText = (staticLayoutBuild.getLineWidth(0) / 2.0f) + i9;
            }
        }
        if (this.f9238x.getText().equals("")) {
            this.f9235u = this.f9234t;
            this.f9238x = this.f9237w;
        }
        float f7 = this.h;
        float f9 = f7 < fMeasureText ? f7 : fMeasureText;
        float f10 = this.f9231q;
        if (f10 > 0.0f && f10 != f9) {
            ValueAnimator valueAnimator = this.f9215D;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.f9230p, f9);
            this.f9215D = valueAnimatorOfFloat;
            valueAnimatorOfFloat.setDuration(200L);
            this.f9215D.setInterpolator(this.G);
            this.f9215D.addUpdateListener(new B(2, this));
            this.f9215D.start();
        }
        if (this.f9230p == 0.0f) {
            this.f9230p = f9;
        }
        StaticLayout staticLayout = this.f9237w;
        this.f9227l = (2 * i7) + (staticLayout.getLineBottom(staticLayout.getLineCount() - 1) - this.f9237w.getLineTop(0));
        int i11 = (!this.f9223g || (recyclerViewScreenLocationY = recyclerView.getRecyclerViewScreenLocationY()) >= (i5 = this.f9233s)) ? 0 : i5 - recyclerViewScreenLocationY;
        canvas.save();
        float f11 = this.h;
        float f12 = this.f9230p;
        float f13 = this.f9224i + this.f9227l + i11;
        float f14 = this.f9232r;
        canvas.drawRoundRect(f11 - f12, r5 + i11, f11 + f12, f13, f14, f14, this.f9221e);
        canvas.translate(this.h - (this.f9238x.getLineWidth(0) / 2.0f), this.f9238x.getLineTop(0) + this.f9224i + i7);
        this.f9238x.draw(canvas);
        canvas.restore();
        if (!this.f9234t.equals(this.f9235u)) {
            if (this.f9234t.length() > this.f9235u.length()) {
                this.f9235u = this.f9234t;
                RunnableC0351o0 runnableC0351o0 = this.f9218H;
                removeCallbacks(runnableC0351o0);
                postDelayed(runnableC0351o0, 90L);
            } else {
                this.f9235u = this.f9234t;
                this.f9238x = this.f9237w;
            }
        }
        if (this.f9234t.equals(this.f9236v)) {
            return;
        }
        this.f9236v = this.f9234t;
        this.f9231q = f9;
    }
}
