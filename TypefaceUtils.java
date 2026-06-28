package com.google.android.material.resources;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Typeface;
import com.bumptech.glide.c;

/* JADX INFO: loaded from: classes.dex */
public class TypefaceUtils {
    private TypefaceUtils() {
    }

    public static Typeface maybeCopyWithFontWeightAdjustment(Context context, Typeface typeface) {
        return maybeCopyWithFontWeightAdjustment(context.getResources().getConfiguration(), typeface);
    }

    public static Typeface maybeCopyWithFontWeightAdjustment(Configuration configuration, Typeface typeface) {
        int i5 = configuration.fontWeightAdjustment;
        if (i5 == Integer.MAX_VALUE || i5 == 0 || typeface == null) {
            return null;
        }
        return Typeface.create(typeface, c.d(typeface.getWeight() + configuration.fontWeightAdjustment, 1, 1000), typeface.isItalic());
    }
}
