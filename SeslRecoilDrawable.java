package androidx.appcompat.graphics.drawable;

import D.d;
import M5.j;
import android.R;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.BlendMode;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import androidx.recyclerview.widget.C0326c;
import androidx.recyclerview.widget.C0360t0;
import e.AbstractC0478a;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class SeslRecoilDrawable extends LayerDrawable {
    public static final LinearInterpolator o = new LinearInterpolator();

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final PathInterpolator f6160p = new PathInterpolator(0.17f, 0.17f, 0.67f, 1.0f);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6161e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f6162f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ValueAnimator f6163g;
    public long h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f6164i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f6165j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f6166k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public float f6167l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f6168m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public C0326c f6169n;

    public SeslRecoilDrawable() {
        super(new Drawable[0]);
        this.f6161e = false;
        this.f6162f = false;
        this.f6163g = ValueAnimator.ofFloat(0.0f);
        this.f6169n = null;
        b();
    }

    public final int a() {
        return d.e(this.f6165j, (int) (((Float) this.f6163g.getAnimatedValue()).floatValue() * Color.valueOf(this.f6165j).alpha() * 255.0f));
    }

    public final void b() {
        this.h = 100L;
        this.f6164i = 350L;
        this.f6163g.addUpdateListener(new j(19, this));
        setPaddingMode(1);
    }

    public final void c(float f2) {
        ValueAnimator valueAnimator = this.f6163g;
        if (valueAnimator.isRunning()) {
            valueAnimator.cancel();
        }
        valueAnimator.setFloatValues(((Float) valueAnimator.getAnimatedValue()).floatValue(), f2);
        valueAnimator.setInterpolator(o);
        valueAnimator.setDuration(this.h);
        valueAnimator.start();
    }

    public final void d(TypedArray typedArray) {
        Drawable drawable;
        for (int i5 = 0; i5 < typedArray.getIndexCount(); i5++) {
            int index = typedArray.getIndex(i5);
            if (index == 0) {
                this.f6165j = typedArray.getColor(index, 419430400);
            } else if (index == 2) {
                this.f6166k = typedArray.getDimensionPixelSize(index, -1);
            } else if (index == 1 && (drawable = typedArray.getDrawable(index)) != null) {
                setId(addLayer(drawable), R.id.mask);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0039 A[PHI: r4
      0x0039: PHI (r4v7 int) = (r4v2 int), (r4v5 int) binds: [B:8:0x0037, B:11:0x0049] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void draw(android.graphics.Canvas r6) {
        /*
            r5 = this;
            int r0 = r6.getSaveCount()
            int r1 = r5.getNumberOfLayers()
            if (r1 > 0) goto L61
            float r1 = r5.f6167l
            float r2 = r5.f6168m
            android.graphics.Rect r3 = new android.graphics.Rect
            r3.<init>()
            r5.getHotspotBounds(r3)
            int r4 = r3.height()
            if (r4 <= 0) goto L26
            int r1 = r3.centerX()
            float r1 = (float) r1
            int r2 = r3.centerY()
            float r2 = (float) r2
        L26:
            r6.translate(r1, r2)
            android.graphics.Paint r3 = new android.graphics.Paint
            r3.<init>()
            int r4 = r5.a()
            r3.setColor(r4)
            int r4 = r5.f6166k
            if (r4 <= 0) goto L3b
        L39:
            float r5 = (float) r4
            goto L57
        L3b:
            android.graphics.Rect r4 = new android.graphics.Rect
            r4.<init>()
            r5.getHotspotBounds(r4)
            int r4 = r4.height()
            int r4 = r4 / 2
            if (r4 <= 0) goto L4c
            goto L39
        L4c:
            android.graphics.Rect r5 = r5.getBounds()
            int r5 = r5.height()
            int r5 = r5 / 2
            float r5 = (float) r5
        L57:
            r4 = 0
            r6.drawCircle(r4, r4, r5, r3)
            float r5 = -r1
            float r1 = -r2
            r6.translate(r5, r1)
            goto L64
        L61:
            super.draw(r6)
        L64:
            r6.restoreToCount(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.graphics.drawable.SeslRecoilDrawable.draw(android.graphics.Canvas):void");
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        return null;
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public final boolean hasFocusStateSpecified() {
        return true;
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, AbstractC0478a.f10578y);
        try {
            try {
                d(typedArrayObtainAttributes);
            } catch (XmlPullParserException e3) {
                Log.e("SeslRecoilDrawable", "Failed to parse!!", e3);
            }
            super.inflate(resources, xmlPullParser, attributeSet, theme);
            Drawable drawableFindDrawableByLayerId = findDrawableByLayerId(R.id.mask);
            if (drawableFindDrawableByLayerId != null) {
                drawableFindDrawableByLayerId.setTint(0);
                drawableFindDrawableByLayerId.setTintBlendMode(BlendMode.SRC_IN);
            }
        } finally {
            typedArrayObtainAttributes.recycle();
        }
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public final boolean isProjected() {
        return getNumberOfLayers() <= 0;
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public final boolean isStateful() {
        return true;
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public final void jumpToCurrentState() {
        super.jumpToCurrentState();
        ValueAnimator valueAnimator = this.f6163g;
        if (valueAnimator.isRunning()) {
            valueAnimator.end();
        }
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public final boolean onStateChange(int[] iArr) {
        boolean z9 = false;
        boolean z10 = false;
        boolean z11 = false;
        for (int i5 : iArr) {
            if (i5 == 16842908) {
                z9 = true;
            } else if (i5 == 16842919) {
                z11 = true;
            } else if (i5 == 16843623) {
                z10 = true;
            }
        }
        boolean z12 = z9 || z10 || z11;
        if (z11) {
            this.f6162f = true;
            c(1.0f);
        } else if (z10) {
            c(0.6f);
        } else if (z9) {
            c(0.8f);
        } else if (this.f6161e && !z12) {
            ValueAnimator valueAnimator = this.f6163g;
            if (valueAnimator.isRunning()) {
                valueAnimator.cancel();
            }
            valueAnimator.setFloatValues(this.f6162f ? 1.0f : ((Float) valueAnimator.getAnimatedValue()).floatValue(), 0.0f);
            valueAnimator.setInterpolator(f6160p);
            valueAnimator.setDuration(this.f6164i);
            valueAnimator.start();
            C0326c c0326c = this.f6169n;
            if (c0326c != null) {
                C0360t0 c0360t0 = (C0360t0) c0326c.f9118e;
                SeslRecoilDrawable seslRecoilDrawable = c0360t0.f9273a;
                if (seslRecoilDrawable.f6169n != null) {
                    seslRecoilDrawable.f6169n = null;
                }
                c0360t0.f9273a = null;
            }
        }
        this.f6161e = z12;
        this.f6162f = z11;
        return super.onStateChange(iArr);
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public final void setHotspot(float f2, float f7) {
        super.setHotspot(f2, f7);
        this.f6167l = f2;
        this.f6168m = f7;
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public final void setTintBlendMode(BlendMode blendMode) {
        super.setTintBlendMode(blendMode);
        Drawable drawableFindDrawableByLayerId = findDrawableByLayerId(R.id.mask);
        if (drawableFindDrawableByLayerId != null) {
            drawableFindDrawableByLayerId.setTintBlendMode(BlendMode.SRC_IN);
        }
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public final void setTintList(ColorStateList colorStateList) {
        super.setTintList(colorStateList);
        Drawable drawableFindDrawableByLayerId = findDrawableByLayerId(R.id.mask);
        if (drawableFindDrawableByLayerId != null) {
            drawableFindDrawableByLayerId.setTint(a());
        }
    }

    public SeslRecoilDrawable(Drawable[] drawableArr) {
        super(drawableArr);
        this.f6161e = false;
        this.f6162f = false;
        this.f6163g = ValueAnimator.ofFloat(0.0f);
        this.f6169n = null;
        b();
    }

    public SeslRecoilDrawable(int i5, Drawable[] drawableArr, Drawable drawable) {
        this(drawableArr);
        b();
        this.f6165j = i5;
        if (drawable != null) {
            setId(addLayer(drawable), R.id.mask);
        }
    }
}
