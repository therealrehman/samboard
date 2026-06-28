package com.google.android.material.carousel;

import com.bumptech.glide.c;

/* JADX INFO: loaded from: classes.dex */
final class Arrangement {
    private static final float MEDIUM_ITEM_FLEX_PERCENTAGE = 0.1f;
    final float cost;
    final int largeCount;
    float largeSize;
    int mediumCount;
    float mediumSize;
    final int priority;
    int smallCount;
    float smallSize;

    public Arrangement(int i5, float f2, float f7, float f9, int i7, float f10, int i9, float f11, int i10, float f12) {
        this.priority = i5;
        this.smallSize = c.c(f2, f7, f9);
        this.smallCount = i7;
        this.mediumSize = f10;
        this.mediumCount = i9;
        this.largeSize = f11;
        this.largeCount = i10;
        fit(f12, f7, f9, f11);
        this.cost = cost(f11);
    }

    private float calculateLargeSize(float f2, int i5, float f7, int i7, int i9) {
        if (i5 <= 0) {
            f7 = 0.0f;
        }
        float f9 = i5;
        float f10 = i7 / 2.0f;
        return (f2 - ((f9 + f10) * f7)) / (i9 + f10);
    }

    private float cost(float f2) {
        if (isValid()) {
            return Math.abs(f2 - this.largeSize) * this.priority;
        }
        return Float.MAX_VALUE;
    }

    public static Arrangement findLowestCostArrangement(float f2, float f7, float f9, float f10, int[] iArr, float f11, int[] iArr2, float f12, int[] iArr3) {
        Arrangement arrangement = null;
        int i5 = 1;
        for (int i7 : iArr3) {
            int length = iArr2.length;
            int i9 = 0;
            while (i9 < length) {
                int i10 = iArr2[i9];
                int length2 = iArr.length;
                int i11 = 0;
                while (i11 < length2) {
                    int i12 = i11;
                    int i13 = length2;
                    int i14 = i9;
                    int i15 = length;
                    Arrangement arrangement2 = new Arrangement(i5, f7, f9, f10, iArr[i11], f11, i10, f12, i7, f2);
                    if (arrangement == null || arrangement2.cost < arrangement.cost) {
                        if (arrangement2.cost == 0.0f) {
                            return arrangement2;
                        }
                        arrangement = arrangement2;
                    }
                    i5++;
                    i11 = i12 + 1;
                    length2 = i13;
                    i9 = i14;
                    length = i15;
                }
                i9++;
            }
        }
        return arrangement;
    }

    private void fit(float f2, float f7, float f9, float f10) {
        float space = f2 - getSpace();
        int i5 = this.smallCount;
        if (i5 > 0 && space > 0.0f) {
            float f11 = this.smallSize;
            this.smallSize = Math.min(space / i5, f9 - f11) + f11;
        } else if (i5 > 0 && space < 0.0f) {
            float f12 = this.smallSize;
            this.smallSize = Math.max(space / i5, f7 - f12) + f12;
        }
        int i7 = this.smallCount;
        float f13 = i7 > 0 ? this.smallSize : 0.0f;
        this.smallSize = f13;
        float fCalculateLargeSize = calculateLargeSize(f2, i7, f13, this.mediumCount, this.largeCount);
        this.largeSize = fCalculateLargeSize;
        float f14 = (this.smallSize + fCalculateLargeSize) / 2.0f;
        this.mediumSize = f14;
        int i9 = this.mediumCount;
        if (i9 <= 0 || fCalculateLargeSize == f10) {
            return;
        }
        float f15 = (f10 - fCalculateLargeSize) * this.largeCount;
        float fMin = Math.min(Math.abs(f15), f14 * 0.1f * i9);
        if (f15 > 0.0f) {
            this.mediumSize -= fMin / this.mediumCount;
            this.largeSize = (fMin / this.largeCount) + this.largeSize;
        } else {
            this.mediumSize = (fMin / this.mediumCount) + this.mediumSize;
            this.largeSize -= fMin / this.largeCount;
        }
    }

    private float getSpace() {
        return (this.smallSize * this.smallCount) + (this.mediumSize * this.mediumCount) + (this.largeSize * this.largeCount);
    }

    private boolean isValid() {
        int i5 = this.largeCount;
        if (i5 <= 0 || this.smallCount <= 0 || this.mediumCount <= 0) {
            return i5 <= 0 || this.smallCount <= 0 || this.largeSize > this.smallSize;
        }
        float f2 = this.largeSize;
        float f7 = this.mediumSize;
        return f2 > f7 && f7 > this.smallSize;
    }

    public int getItemCount() {
        return this.smallCount + this.mediumCount + this.largeCount;
    }

    public String toString() {
        return "Arrangement [priority=" + this.priority + ", smallCount=" + this.smallCount + ", smallSize=" + this.smallSize + ", mediumCount=" + this.mediumCount + ", mediumSize=" + this.mediumSize + ", largeCount=" + this.largeCount + ", largeSize=" + this.largeSize + ", cost=" + this.cost + "]";
    }
}
