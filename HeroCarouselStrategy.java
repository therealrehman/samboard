package com.google.android.material.carousel;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.C0372z0;
import com.bumptech.glide.c;

/* JADX INFO: loaded from: classes.dex */
public class HeroCarouselStrategy extends CarouselStrategy {
    private int keylineCount = 0;
    private static final int[] SMALL_COUNTS = {1};
    private static final int[] MEDIUM_COUNTS = {0, 1};

    @Override // com.google.android.material.carousel.CarouselStrategy
    public KeylineState onFirstChildMeasuredWithMargins(Carousel carousel, View view) {
        int i5;
        int containerHeight = carousel.getContainerHeight();
        if (carousel.isHorizontal()) {
            containerHeight = carousel.getContainerWidth();
        }
        C0372z0 c0372z0 = (C0372z0) view.getLayoutParams();
        float f2 = ((ViewGroup.MarginLayoutParams) c0372z0).topMargin + ((ViewGroup.MarginLayoutParams) c0372z0).bottomMargin;
        float measuredWidth = view.getMeasuredWidth() * 2;
        if (carousel.isHorizontal()) {
            f2 = ((ViewGroup.MarginLayoutParams) c0372z0).leftMargin + ((ViewGroup.MarginLayoutParams) c0372z0).rightMargin;
            measuredWidth = view.getMeasuredHeight() * 2;
        }
        float smallSizeMin = CarouselStrategyHelper.getSmallSizeMin(view.getContext()) + f2;
        float smallSizeMax = CarouselStrategyHelper.getSmallSizeMax(view.getContext()) + f2;
        float f7 = containerHeight;
        float fMin = Math.min(measuredWidth + f2, f7);
        float fC = c.c((measuredWidth / 3.0f) + f2, CarouselStrategyHelper.getSmallSizeMin(view.getContext()) + f2, CarouselStrategyHelper.getSmallSizeMax(view.getContext()) + f2);
        float f9 = (fMin + fC) / 2.0f;
        int[] iArr = f7 < 2.0f * smallSizeMin ? new int[]{0} : SMALL_COUNTS;
        int iMax = (int) Math.max(1.0d, Math.floor((f7 - (CarouselStrategyHelper.maxValue(r4) * smallSizeMax)) / fMin));
        int iCeil = (((int) Math.ceil(f7 / fMin)) - iMax) + 1;
        int[] iArr2 = new int[iCeil];
        for (int i7 = 0; i7 < iCeil; i7++) {
            iArr2[i7] = iMax + i7;
        }
        int i9 = carousel.getCarouselAlignment() == 1 ? 1 : 0;
        Arrangement arrangementFindLowestCostArrangement = Arrangement.findLowestCostArrangement(f7, fC, smallSizeMin, smallSizeMax, i9 != 0 ? CarouselStrategy.doubleCounts(iArr) : iArr, f9, i9 != 0 ? CarouselStrategy.doubleCounts(MEDIUM_COUNTS) : MEDIUM_COUNTS, fMin, iArr2);
        this.keylineCount = arrangementFindLowestCostArrangement.getItemCount();
        if (arrangementFindLowestCostArrangement.getItemCount() > carousel.getItemCount()) {
            arrangementFindLowestCostArrangement = Arrangement.findLowestCostArrangement(f7, fC, smallSizeMin, smallSizeMax, iArr, f9, MEDIUM_COUNTS, fMin, iArr2);
            i5 = 0;
        } else {
            i5 = i9;
        }
        return CarouselStrategyHelper.createKeylineState(view.getContext(), f2, f7, arrangementFindLowestCostArrangement, i5);
    }

    @Override // com.google.android.material.carousel.CarouselStrategy
    public boolean shouldRefreshKeylineState(Carousel carousel, int i5) {
        if (carousel.getCarouselAlignment() == 1) {
            if (i5 < this.keylineCount && carousel.getItemCount() >= this.keylineCount) {
                return true;
            }
            if (i5 >= this.keylineCount && carousel.getItemCount() < this.keylineCount) {
                return true;
            }
        }
        return false;
    }
}
