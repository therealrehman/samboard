package com.google.android.material.animation;

import android.animation.TypeEvaluator;
import d6.AbstractC0476d;

/* JADX INFO: loaded from: classes.dex */
public class ArgbEvaluatorCompat implements TypeEvaluator<Integer> {
    private static final ArgbEvaluatorCompat instance = new ArgbEvaluatorCompat();

    public static ArgbEvaluatorCompat getInstance() {
        return instance;
    }

    @Override // android.animation.TypeEvaluator
    public Integer evaluate(float f2, Integer num, Integer num2) {
        int iIntValue = num.intValue();
        float f7 = ((iIntValue >> 24) & 255) / 255.0f;
        int iIntValue2 = num2.intValue();
        float f9 = ((iIntValue2 >> 24) & 255) / 255.0f;
        float fPow = (float) Math.pow(((iIntValue >> 16) & 255) / 255.0f, 2.2d);
        float fPow2 = (float) Math.pow(((iIntValue >> 8) & 255) / 255.0f, 2.2d);
        float fPow3 = (float) Math.pow((iIntValue & 255) / 255.0f, 2.2d);
        float fPow4 = (float) Math.pow(((iIntValue2 >> 16) & 255) / 255.0f, 2.2d);
        float fPow5 = (float) Math.pow(((iIntValue2 >> 8) & 255) / 255.0f, 2.2d);
        float fPow6 = (float) Math.pow((iIntValue2 & 255) / 255.0f, 2.2d);
        float fU = AbstractC0476d.u(f9, f7, f2, f7);
        float fU2 = AbstractC0476d.u(fPow4, fPow, f2, fPow);
        float fU3 = AbstractC0476d.u(fPow5, fPow2, f2, fPow2);
        float fU4 = AbstractC0476d.u(fPow6, fPow3, f2, fPow3);
        float fPow7 = ((float) Math.pow(fU2, 0.45454545454545453d)) * 255.0f;
        float fPow8 = ((float) Math.pow(fU3, 0.45454545454545453d)) * 255.0f;
        return Integer.valueOf(Math.round(((float) Math.pow(fU4, 0.45454545454545453d)) * 255.0f) | (Math.round(fPow7) << 16) | (Math.round(fU * 255.0f) << 24) | (Math.round(fPow8) << 8));
    }
}
