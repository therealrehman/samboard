package com.google.android.material.util;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0007\n\u0002\b\n\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/google/android/material/util/MaxFontScaleRatio;", "", "ratio", "", "(Ljava/lang/String;IF)V", "getRatio", "()F", "SMALL", "MEDIUM", "LARGE", "EXTRA_LARGE", "HUGE", "EXTRA_HUGE", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public enum MaxFontScaleRatio {
    SMALL(1.0f),
    MEDIUM(1.15f),
    LARGE(1.3f),
    EXTRA_LARGE(1.5f),
    HUGE(1.7f),
    EXTRA_HUGE(2.0f);

    private final float ratio;

    MaxFontScaleRatio(float f2) {
        this.ratio = f2;
    }

    public final float getRatio() {
        return this.ratio;
    }
}
