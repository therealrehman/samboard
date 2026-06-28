package com.google.android.material.carousel;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.C0372z0;
import com.google.android.material.carousel.KeylineState;

/* JADX INFO: loaded from: classes.dex */
public final class UncontainedCarouselStrategy extends CarouselStrategy {
    private static final float MEDIUM_LARGE_ITEM_PERCENTAGE_THRESHOLD = 0.85f;

    private float calculateMediumChildSize(float f2, float f7, float f9) {
        float fMax = Math.max(1.5f * f9, f2);
        float f10 = MEDIUM_LARGE_ITEM_PERCENTAGE_THRESHOLD * f7;
        if (fMax > f10) {
            fMax = Math.max(f10, f9 * 1.2f);
        }
        return Math.min(f7, fMax);
    }

    private KeylineState createCenterAlignedKeylineState(float f2, float f7, float f9, int i5, float f10, float f11, float f12) {
        float fMin = Math.min(f11, f9);
        float childMaskPercentage = CarouselStrategy.getChildMaskPercentage(fMin, f9, f7);
        float childMaskPercentage2 = CarouselStrategy.getChildMaskPercentage(f10, f9, f7);
        float f13 = f10 / 2.0f;
        float f14 = (f12 + 0.0f) - f13;
        float f15 = f14 + f13;
        float f16 = fMin / 2.0f;
        float f17 = (i5 * f9) + f15;
        KeylineState.Builder builderAddKeylineRange = new KeylineState.Builder(f9, f2).addAnchorKeyline((f14 - f13) - f16, childMaskPercentage, fMin).addKeyline(f14, childMaskPercentage2, f10, false).addKeylineRange((f9 / 2.0f) + f15, 0.0f, f9, i5, true);
        builderAddKeylineRange.addKeyline(f13 + f17, childMaskPercentage2, f10, false);
        builderAddKeylineRange.addAnchorKeyline(f17 + f10 + f16, childMaskPercentage, fMin);
        return builderAddKeylineRange.build();
    }

    private KeylineState createLeftAlignedKeylineState(Context context, float f2, float f7, float f9, int i5, float f10, int i7, float f11) {
        float fMin = Math.min(f11, f9);
        float fMax = Math.max(fMin, 0.5f * f10);
        float childMaskPercentage = CarouselStrategy.getChildMaskPercentage(fMax, f9, f2);
        float childMaskPercentage2 = CarouselStrategy.getChildMaskPercentage(fMin, f9, f2);
        float childMaskPercentage3 = CarouselStrategy.getChildMaskPercentage(f10, f9, f2);
        float f12 = (i5 * f9) + 0.0f;
        KeylineState.Builder builderAddKeylineRange = new KeylineState.Builder(f9, f7).addAnchorKeyline(0.0f - (fMax / 2.0f), childMaskPercentage, fMax).addKeylineRange(f9 / 2.0f, 0.0f, f9, i5, true);
        if (i7 > 0) {
            float f13 = (f10 / 2.0f) + f12;
            f12 += f10;
            builderAddKeylineRange.addKeyline(f13, childMaskPercentage3, f10, false);
        }
        builderAddKeylineRange.addAnchorKeyline((CarouselStrategyHelper.getExtraSmallSize(context) / 2.0f) + f12, childMaskPercentage2, fMin);
        return builderAddKeylineRange.build();
    }

    @Override // com.google.android.material.carousel.CarouselStrategy
    public boolean isContained() {
        return false;
    }

    @Override // com.google.android.material.carousel.CarouselStrategy
    public KeylineState onFirstChildMeasuredWithMargins(Carousel carousel, View view) {
        float f2;
        float containerWidth = carousel.isHorizontal() ? carousel.getContainerWidth() : carousel.getContainerHeight();
        C0372z0 c0372z0 = (C0372z0) view.getLayoutParams();
        float f7 = ((ViewGroup.MarginLayoutParams) c0372z0).topMargin + ((ViewGroup.MarginLayoutParams) c0372z0).bottomMargin;
        float measuredHeight = view.getMeasuredHeight();
        if (carousel.isHorizontal()) {
            float f9 = ((ViewGroup.MarginLayoutParams) c0372z0).leftMargin + ((ViewGroup.MarginLayoutParams) c0372z0).rightMargin;
            measuredHeight = view.getMeasuredWidth();
            f2 = f9;
        } else {
            f2 = f7;
        }
        float f10 = measuredHeight + f2;
        float extraSmallSize = CarouselStrategyHelper.getExtraSmallSize(view.getContext()) + f2;
        float extraSmallSize2 = CarouselStrategyHelper.getExtraSmallSize(view.getContext()) + f2;
        int iMax = Math.max(1, (int) Math.floor(containerWidth / f10));
        float f11 = containerWidth - (iMax * f10);
        if (carousel.getCarouselAlignment() == 1) {
            float f12 = f11 / 2.0f;
            return createCenterAlignedKeylineState(containerWidth, f2, f10, iMax, Math.max(Math.min(3.0f * f12, f10), CarouselStrategyHelper.getSmallSizeMin(view.getContext()) + f2), extraSmallSize2, f12);
        }
        return createLeftAlignedKeylineState(view.getContext(), f2, containerWidth, f10, iMax, calculateMediumChildSize(extraSmallSize, f10, f11), f11 > 0.0f ? 1 : 0, extraSmallSize2);
    }
}
