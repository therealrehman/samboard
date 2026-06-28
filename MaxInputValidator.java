package com.google.android.material.timepicker;

import android.text.InputFilter;
import android.text.Spanned;

/* JADX INFO: loaded from: classes.dex */
class MaxInputValidator implements InputFilter {
    private int max;

    public MaxInputValidator(int i5) {
        this.max = i5;
    }

    @Override // android.text.InputFilter
    public CharSequence filter(CharSequence charSequence, int i5, int i7, Spanned spanned, int i9, int i10) {
        try {
            StringBuilder sb = new StringBuilder(spanned);
            sb.replace(i9, i10, charSequence.subSequence(i5, i7).toString());
            if (Integer.parseInt(sb.toString()) <= this.max) {
                return null;
            }
            return "";
        } catch (NumberFormatException unused) {
            return "";
        }
    }

    public int getMax() {
        return this.max;
    }

    public void setMax(int i5) {
        this.max = i5;
    }
}
