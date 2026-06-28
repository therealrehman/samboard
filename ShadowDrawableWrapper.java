package com.google.android.material.shadow;

import B.b;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import com.google.android.material.R;
import i.C0590a;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class ShadowDrawableWrapper extends C0590a {
    static final double COS_45 = Math.cos(Math.toRadians(45.0d));
    static final float SHADOW_BOTTOM_SCALE = 1.0f;
    static final float SHADOW_HORIZ_SCALE = 0.5f;
    static final float SHADOW_MULTIPLIER = 1.5f;
    static final float SHADOW_TOP_SCALE = 0.25f;
    private boolean addPaddingForCorners;
    final RectF contentBounds;
    float cornerRadius;
    final Paint cornerShadowPaint;
    Path cornerShadowPath;
    private boolean dirty;
    final Paint edgeShadowPaint;
    float maxShadowSize;
    private boolean printedShadowClipWarning;
    float rawMaxShadowSize;
    float rawShadowSize;
    private float rotation;
    private final int shadowEndColor;
    private final int shadowMiddleColor;
    float shadowSize;
    private final int shadowStartColor;

    public ShadowDrawableWrapper(Context context, Drawable drawable, float f2, float f7, float f9) {
        super(drawable);
        this.dirty = true;
        this.addPaddingForCorners = true;
        this.printedShadowClipWarning = false;
        this.shadowStartColor = b.a(context, R.color.design_fab_shadow_start_color);
        this.shadowMiddleColor = b.a(context, R.color.design_fab_shadow_mid_color);
        this.shadowEndColor = b.a(context, R.color.design_fab_shadow_end_color);
        Paint paint = new Paint(5);
        this.cornerShadowPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.cornerRadius = Math.round(f2);
        this.contentBounds = new RectF();
        Paint paint2 = new Paint(paint);
        this.edgeShadowPaint = paint2;
        paint2.setAntiAlias(false);
        setShadowSize(f7, f9);
    }

    private void buildComponents(Rect rect) {
        float f2 = this.rawMaxShadowSize;
        float f7 = SHADOW_MULTIPLIER * f2;
        this.contentBounds.set(rect.left + f2, rect.top + f7, rect.right - f2, rect.bottom - f7);
        Drawable drawable = getDrawable();
        RectF rectF = this.contentBounds;
        drawable.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
        buildShadowCorners();
    }

    private void buildShadowCorners() {
        float f2 = this.cornerRadius;
        RectF rectF = new RectF(-f2, -f2, f2, f2);
        RectF rectF2 = new RectF(rectF);
        float f7 = this.shadowSize;
        rectF2.inset(-f7, -f7);
        Path path = this.cornerShadowPath;
        if (path == null) {
            this.cornerShadowPath = new Path();
        } else {
            path.reset();
        }
        this.cornerShadowPath.setFillType(Path.FillType.EVEN_ODD);
        this.cornerShadowPath.moveTo(-this.cornerRadius, 0.0f);
        this.cornerShadowPath.rLineTo(-this.shadowSize, 0.0f);
        this.cornerShadowPath.arcTo(rectF2, 180.0f, 90.0f, false);
        this.cornerShadowPath.arcTo(rectF, 270.0f, -90.0f, false);
        this.cornerShadowPath.close();
        float f9 = -rectF2.top;
        if (f9 > 0.0f) {
            float f10 = this.cornerRadius / f9;
            this.cornerShadowPaint.setShader(new RadialGradient(0.0f, 0.0f, f9, new int[]{0, this.shadowStartColor, this.shadowMiddleColor, this.shadowEndColor}, new float[]{0.0f, f10, ((1.0f - f10) / 2.0f) + f10, 1.0f}, Shader.TileMode.CLAMP));
        }
        this.edgeShadowPaint.setShader(new LinearGradient(0.0f, rectF.top, 0.0f, rectF2.top, new int[]{this.shadowStartColor, this.shadowMiddleColor, this.shadowEndColor}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP));
        this.edgeShadowPaint.setAntiAlias(false);
    }

    public static float calculateHorizontalPadding(float f2, float f7, boolean z9) {
        if (!z9) {
            return f2;
        }
        return (float) (((1.0d - COS_45) * ((double) f7)) + ((double) f2));
    }

    public static float calculateVerticalPadding(float f2, float f7, boolean z9) {
        if (!z9) {
            return f2 * SHADOW_MULTIPLIER;
        }
        return (float) (((1.0d - COS_45) * ((double) f7)) + ((double) (f2 * SHADOW_MULTIPLIER)));
    }

    private void drawShadow(Canvas canvas) {
        int i5;
        float f2;
        int i7;
        float f7;
        float f9;
        float f10;
        int iSave = canvas.save();
        canvas.rotate(this.rotation, this.contentBounds.centerX(), this.contentBounds.centerY());
        float f11 = this.cornerRadius;
        float f12 = (-f11) - this.shadowSize;
        float f13 = f11 * 2.0f;
        boolean z9 = this.contentBounds.width() - f13 > 0.0f;
        boolean z10 = this.contentBounds.height() - f13 > 0.0f;
        float f14 = this.rawShadowSize;
        float f15 = f11 / ((f14 - (0.5f * f14)) + f11);
        float f16 = f11 / ((f14 - (SHADOW_TOP_SCALE * f14)) + f11);
        float f17 = f11 / ((f14 - (f14 * 1.0f)) + f11);
        int iSave2 = canvas.save();
        RectF rectF = this.contentBounds;
        canvas.translate(rectF.left + f11, rectF.top + f11);
        canvas.scale(f15, f16);
        canvas.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if (z9) {
            canvas.scale(1.0f / f15, 1.0f);
            i5 = iSave2;
            f2 = f17;
            i7 = iSave;
            f7 = f16;
            canvas.drawRect(0.0f, f12, this.contentBounds.width() - f13, -this.cornerRadius, this.edgeShadowPaint);
        } else {
            i5 = iSave2;
            f2 = f17;
            i7 = iSave;
            f7 = f16;
        }
        canvas.restoreToCount(i5);
        int iSave3 = canvas.save();
        RectF rectF2 = this.contentBounds;
        canvas.translate(rectF2.right - f11, rectF2.bottom - f11);
        float f18 = f2;
        canvas.scale(f15, f18);
        canvas.rotate(180.0f);
        canvas.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if (z9) {
            canvas.scale(1.0f / f15, 1.0f);
            f9 = f7;
            f10 = f18;
            canvas.drawRect(0.0f, f12, this.contentBounds.width() - f13, (-this.cornerRadius) + this.shadowSize, this.edgeShadowPaint);
        } else {
            f9 = f7;
            f10 = f18;
        }
        canvas.restoreToCount(iSave3);
        int iSave4 = canvas.save();
        RectF rectF3 = this.contentBounds;
        canvas.translate(rectF3.left + f11, rectF3.bottom - f11);
        canvas.scale(f15, f10);
        canvas.rotate(270.0f);
        canvas.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if (z10) {
            canvas.scale(1.0f / f10, 1.0f);
            canvas.drawRect(0.0f, f12, this.contentBounds.height() - f13, -this.cornerRadius, this.edgeShadowPaint);
        }
        canvas.restoreToCount(iSave4);
        int iSave5 = canvas.save();
        RectF rectF4 = this.contentBounds;
        canvas.translate(rectF4.right - f11, rectF4.top + f11);
        float f19 = f9;
        canvas.scale(f15, f19);
        canvas.rotate(90.0f);
        canvas.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if (z10) {
            canvas.scale(1.0f / f19, 1.0f);
            canvas.drawRect(0.0f, f12, this.contentBounds.height() - f13, -this.cornerRadius, this.edgeShadowPaint);
        }
        canvas.restoreToCount(iSave5);
        canvas.restoreToCount(i7);
    }

    private static int toEven(float f2) {
        int iRound = Math.round(f2);
        return iRound % 2 == 1 ? iRound - 1 : iRound;
    }

    @Override // i.C0590a, android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.dirty) {
            buildComponents(getBounds());
            this.dirty = false;
        }
        drawShadow(canvas);
        super.draw(canvas);
    }

    public float getCornerRadius() {
        return this.cornerRadius;
    }

    public float getMaxShadowSize() {
        return this.rawMaxShadowSize;
    }

    public float getMinHeight() {
        float f2 = this.rawMaxShadowSize;
        return (this.rawMaxShadowSize * SHADOW_MULTIPLIER * 2.0f) + (Math.max(f2, ((f2 * SHADOW_MULTIPLIER) / 2.0f) + this.cornerRadius) * 2.0f);
    }

    public float getMinWidth() {
        float f2 = this.rawMaxShadowSize;
        return (this.rawMaxShadowSize * 2.0f) + (Math.max(f2, (f2 / 2.0f) + this.cornerRadius) * 2.0f);
    }

    @Override // i.C0590a, android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // i.C0590a, android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        int iCeil = (int) Math.ceil(calculateVerticalPadding(this.rawMaxShadowSize, this.cornerRadius, this.addPaddingForCorners));
        int iCeil2 = (int) Math.ceil(calculateHorizontalPadding(this.rawMaxShadowSize, this.cornerRadius, this.addPaddingForCorners));
        rect.set(iCeil2, iCeil, iCeil2, iCeil);
        return true;
    }

    public float getShadowSize() {
        return this.rawShadowSize;
    }

    @Override // i.C0590a, android.graphics.drawable.Drawable
    public void onBoundsChange(Rect rect) {
        this.dirty = true;
    }

    public void setAddPaddingForCorners(boolean z9) {
        this.addPaddingForCorners = z9;
        invalidateSelf();
    }

    @Override // i.C0590a, android.graphics.drawable.Drawable
    public void setAlpha(int i5) {
        super.setAlpha(i5);
        this.cornerShadowPaint.setAlpha(i5);
        this.edgeShadowPaint.setAlpha(i5);
    }

    public void setCornerRadius(float f2) {
        float fRound = Math.round(f2);
        if (this.cornerRadius == fRound) {
            return;
        }
        this.cornerRadius = fRound;
        this.dirty = true;
        invalidateSelf();
    }

    public void setMaxShadowSize(float f2) {
        setShadowSize(this.rawShadowSize, f2);
    }

    public final void setRotation(float f2) {
        if (this.rotation != f2) {
            this.rotation = f2;
            invalidateSelf();
        }
    }

    public void setShadowSize(float f2, float f7) {
        if (f2 < 0.0f || f7 < 0.0f) {
            throw new IllegalArgumentException("invalid shadow size");
        }
        float even = toEven(f2);
        float even2 = toEven(f7);
        if (even > even2) {
            if (!this.printedShadowClipWarning) {
                this.printedShadowClipWarning = true;
            }
            even = even2;
        }
        if (this.rawShadowSize == even && this.rawMaxShadowSize == even2) {
            return;
        }
        this.rawShadowSize = even;
        this.rawMaxShadowSize = even2;
        this.shadowSize = Math.round(even * SHADOW_MULTIPLIER);
        this.maxShadowSize = even2;
        this.dirty = true;
        invalidateSelf();
    }

    public void setShadowSize(float f2) {
        setShadowSize(f2, this.rawMaxShadowSize);
    }
}
