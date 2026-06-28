package com.google.android.material.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.f;
import android.util.TypedValue;
import androidx.appcompat.widget.S1;
import e.AbstractC0478a;
import p0.a;

/* JADX INFO: loaded from: classes.dex */
public class MaterialResources {
    private static final float FONT_SCALE_1_3 = 1.3f;
    private static final float FONT_SCALE_2_0 = 2.0f;

    private MaterialResources() {
    }

    public static ColorStateList getColorStateList(Context context, TypedArray typedArray, int i5) {
        int resourceId;
        ColorStateList colorStateListF;
        return (!typedArray.hasValue(i5) || (resourceId = typedArray.getResourceId(i5, 0)) == 0 || (colorStateListF = a.f(context, resourceId)) == null) ? typedArray.getColorStateList(i5) : colorStateListF;
    }

    private static int getComplexUnit(TypedValue typedValue) {
        return typedValue.getComplexUnit();
    }

    public static int getDimensionPixelSize(Context context, TypedArray typedArray, int i5, int i7) {
        TypedValue typedValue = new TypedValue();
        if (!typedArray.getValue(i5, typedValue) || typedValue.type != 2) {
            return typedArray.getDimensionPixelSize(i5, i7);
        }
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{typedValue.data});
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, i7);
        typedArrayObtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    public static Drawable getDrawable(Context context, TypedArray typedArray, int i5) {
        int resourceId;
        Drawable drawableY;
        return (!typedArray.hasValue(i5) || (resourceId = typedArray.getResourceId(i5, 0)) == 0 || (drawableY = f.y(context, resourceId)) == null) ? typedArray.getDrawable(i5) : drawableY;
    }

    public static float getFontScale(Context context) {
        return context.getResources().getConfiguration().fontScale;
    }

    public static int getIndexWithValue(TypedArray typedArray, int i5, int i7) {
        return typedArray.hasValue(i5) ? i5 : i7;
    }

    public static TextAppearance getTextAppearance(Context context, TypedArray typedArray, int i5) {
        int resourceId;
        if (!typedArray.hasValue(i5) || (resourceId = typedArray.getResourceId(i5, 0)) == 0) {
            return null;
        }
        return new TextAppearance(context, resourceId);
    }

    public static int getUnscaledTextSize(Context context, int i5, int i7) {
        if (i5 == 0) {
            return i7;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i5, AbstractC0478a.f10552C);
        TypedValue typedValue = new TypedValue();
        boolean value = typedArrayObtainStyledAttributes.getValue(0, typedValue);
        typedArrayObtainStyledAttributes.recycle();
        return !value ? i7 : getComplexUnit(typedValue) == 2 ? Math.round(TypedValue.complexToFloat(typedValue.data) * context.getResources().getDisplayMetrics().density) : TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
    }

    public static boolean isFontScaleAtLeast1_3(Context context) {
        return context.getResources().getConfiguration().fontScale >= FONT_SCALE_1_3;
    }

    public static boolean isFontScaleAtLeast2_0(Context context) {
        return context.getResources().getConfiguration().fontScale >= FONT_SCALE_2_0;
    }

    public static ColorStateList getColorStateList(Context context, S1 s12, int i5) {
        int resourceId;
        ColorStateList colorStateListF;
        return (!s12.f6522b.hasValue(i5) || (resourceId = s12.f6522b.getResourceId(i5, 0)) == 0 || (colorStateListF = a.f(context, resourceId)) == null) ? s12.a(i5) : colorStateListF;
    }
}
