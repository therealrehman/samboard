package com.google.android.material.carousel;

import android.content.Context;
import com.google.android.material.R;
import com.google.android.material.carousel.KeylineState;

/* JADX INFO: loaded from: classes.dex */
final class CarouselStrategyHelper {
    private CarouselStrategyHelper() {
    }

    public static float addEnd(float f2, float f7, int i5) {
        return (Math.max(0, i5 - 1) * f7) + f2;
    }

    public static float addStart(float f2, float f7, int i5) {
        return i5 > 0 ? (f7 / 2.0f) + f2 : f2;
    }

    public static KeylineState createCenterAlignedKeylineState(Context context, float f2, float f7, Arrangement arrangement) {
        float f9;
        float f10;
        float fMin = Math.min(getExtraSmallSize(context) + f2, arrangement.largeSize);
        float f11 = fMin / 2.0f;
        float f12 = 0.0f - f11;
        float fAddStart = addStart(0.0f, arrangement.smallSize, arrangement.smallCount);
        float fUpdateCurPosition = updateCurPosition(0.0f, addEnd(fAddStart, arrangement.smallSize, (int) Math.floor(arrangement.smallCount / 2.0f)), arrangement.smallSize, arrangement.smallCount);
        float fAddStart2 = addStart(fUpdateCurPosition, arrangement.mediumSize, arrangement.mediumCount);
        float fUpdateCurPosition2 = updateCurPosition(fUpdateCurPosition, addEnd(fAddStart2, arrangement.mediumSize, (int) Math.floor(arrangement.mediumCount / 2.0f)), arrangement.mediumSize, arrangement.mediumCount);
        float fAddStart3 = addStart(fUpdateCurPosition2, arrangement.largeSize, arrangement.largeCount);
        float fUpdateCurPosition3 = updateCurPosition(fUpdateCurPosition2, addEnd(fAddStart3, arrangement.largeSize, arrangement.largeCount), arrangement.largeSize, arrangement.largeCount);
        float fAddStart4 = addStart(fUpdateCurPosition3, arrangement.mediumSize, arrangement.mediumCount);
        float fAddStart5 = addStart(updateCurPosition(fUpdateCurPosition3, addEnd(fAddStart4, arrangement.mediumSize, (int) Math.ceil(arrangement.mediumCount / 2.0f)), arrangement.mediumSize, arrangement.mediumCount), arrangement.smallSize, arrangement.smallCount);
        float f13 = f11 + f7;
        float childMaskPercentage = CarouselStrategy.getChildMaskPercentage(fMin, arrangement.largeSize, f2);
        float childMaskPercentage2 = CarouselStrategy.getChildMaskPercentage(arrangement.smallSize, arrangement.largeSize, f2);
        float childMaskPercentage3 = CarouselStrategy.getChildMaskPercentage(arrangement.mediumSize, arrangement.largeSize, f2);
        KeylineState.Builder builderAddAnchorKeyline = new KeylineState.Builder(arrangement.largeSize, f7).addAnchorKeyline(f12, childMaskPercentage, fMin);
        if (arrangement.smallCount > 0) {
            f9 = f13;
            builderAddAnchorKeyline.addKeylineRange(fAddStart, childMaskPercentage2, arrangement.smallSize, (int) Math.floor(r7 / 2.0f));
        } else {
            f9 = f13;
        }
        if (arrangement.mediumCount > 0) {
            builderAddAnchorKeyline.addKeylineRange(fAddStart2, childMaskPercentage3, arrangement.mediumSize, (int) Math.floor(r4 / 2.0f));
        }
        builderAddAnchorKeyline.addKeylineRange(fAddStart3, 0.0f, arrangement.largeSize, arrangement.largeCount, true);
        if (arrangement.mediumCount > 0) {
            f10 = 2.0f;
            builderAddAnchorKeyline.addKeylineRange(fAddStart4, childMaskPercentage3, arrangement.mediumSize, (int) Math.ceil(r4 / 2.0f));
        } else {
            f10 = 2.0f;
        }
        if (arrangement.smallCount > 0) {
            builderAddAnchorKeyline.addKeylineRange(fAddStart5, childMaskPercentage2, arrangement.smallSize, (int) Math.ceil(r0 / f10));
        }
        builderAddAnchorKeyline.addAnchorKeyline(f9, childMaskPercentage, fMin);
        return builderAddAnchorKeyline.build();
    }

    public static KeylineState createKeylineState(Context context, float f2, float f7, Arrangement arrangement, int i5) {
        return i5 == 1 ? createCenterAlignedKeylineState(context, f2, f7, arrangement) : createLeftAlignedKeylineState(context, f2, f7, arrangement);
    }

    public static KeylineState createLeftAlignedKeylineState(Context context, float f2, float f7, Arrangement arrangement) {
        float fMin = Math.min(getExtraSmallSize(context) + f2, arrangement.largeSize);
        float f9 = fMin / 2.0f;
        float f10 = 0.0f - f9;
        float fAddStart = addStart(0.0f, arrangement.largeSize, arrangement.largeCount);
        float fUpdateCurPosition = updateCurPosition(0.0f, addEnd(fAddStart, arrangement.largeSize, arrangement.largeCount), arrangement.largeSize, arrangement.largeCount);
        float fAddStart2 = addStart(fUpdateCurPosition, arrangement.mediumSize, arrangement.mediumCount);
        float fAddStart3 = addStart(updateCurPosition(fUpdateCurPosition, fAddStart2, arrangement.mediumSize, arrangement.mediumCount), arrangement.smallSize, arrangement.smallCount);
        float f11 = f9 + f7;
        float childMaskPercentage = CarouselStrategy.getChildMaskPercentage(fMin, arrangement.largeSize, f2);
        float childMaskPercentage2 = CarouselStrategy.getChildMaskPercentage(arrangement.smallSize, arrangement.largeSize, f2);
        float childMaskPercentage3 = CarouselStrategy.getChildMaskPercentage(arrangement.mediumSize, arrangement.largeSize, f2);
        KeylineState.Builder builderAddKeylineRange = new KeylineState.Builder(arrangement.largeSize, f7).addAnchorKeyline(f10, childMaskPercentage, fMin).addKeylineRange(fAddStart, 0.0f, arrangement.largeSize, arrangement.largeCount, true);
        if (arrangement.mediumCount > 0) {
            builderAddKeylineRange.addKeyline(fAddStart2, childMaskPercentage3, arrangement.mediumSize);
        }
        int i5 = arrangement.smallCount;
        if (i5 > 0) {
            builderAddKeylineRange.addKeylineRange(fAddStart3, childMaskPercentage2, arrangement.smallSize, i5);
        }
        builderAddKeylineRange.addAnchorKeyline(f11, childMaskPercentage, fMin);
        return builderAddKeylineRange.build();
    }

    public static float getExtraSmallSize(Context context) {
        return context.getResources().getDimension(R.dimen.m3_carousel_gone_size);
    }

    public static float getSmallSizeMax(Context context) {
        return context.getResources().getDimension(R.dimen.m3_carousel_small_item_size_max);
    }

    public static float getSmallSizeMin(Context context) {
        return context.getResources().getDimension(R.dimen.m3_carousel_small_item_size_min);
    }

    public static int maxValue(int[] iArr) {
        int i5 = Integer.MIN_VALUE;
        for (int i7 : iArr) {
            if (i7 > i5) {
                i5 = i7;
            }
        }
        return i5;
    }

    public static float updateCurPosition(float f2, float f7, float f9, int i5) {
        return i5 > 0 ? (f9 / 2.0f) + f7 : f2;
    }
}
