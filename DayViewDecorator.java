package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public abstract class DayViewDecorator implements Parcelable {
    public ColorStateList getBackgroundColor(Context context, int i5, int i7, int i9, boolean z9, boolean z10) {
        return null;
    }

    public Drawable getCompoundDrawableBottom(Context context, int i5, int i7, int i9, boolean z9, boolean z10) {
        return null;
    }

    public Drawable getCompoundDrawableLeft(Context context, int i5, int i7, int i9, boolean z9, boolean z10) {
        return null;
    }

    public Drawable getCompoundDrawableRight(Context context, int i5, int i7, int i9, boolean z9, boolean z10) {
        return null;
    }

    public Drawable getCompoundDrawableTop(Context context, int i5, int i7, int i9, boolean z9, boolean z10) {
        return null;
    }

    public CharSequence getContentDescription(Context context, int i5, int i7, int i9, boolean z9, boolean z10, CharSequence charSequence) {
        return charSequence;
    }

    public ColorStateList getTextColor(Context context, int i5, int i7, int i9, boolean z9, boolean z10) {
        return null;
    }

    public void initialize(Context context) {
    }
}
