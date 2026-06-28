package com.google.android.material.carousel;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.C0372z0;
import com.bumptech.glide.c;

/* JADX INFO: loaded from: classes.dex */
public final class MultiBrowseCarouselStrategy extends CarouselStrategy {
    private int keylineCount = 0;
    private static final int[] SMALL_COUNTS = {1};
    private static final int[] MEDIUM_COUNTS = {1, 0};

    public boolean ensureArrangementFitsItemCount(Arrangement arrangement, int i5) {
        int itemCount = arrangement.getItemCount() - i5;
        boolean z9 = itemCount > 0 && (arrangement.smallCount > 0 || arrangement.mediumCount > 1);
        while (itemCount > 0) {
            int i7 = arrangement.smallCount;
            if (i7 > 0) {
                arrangement.smallCount = i7 - 1;
            } else {
                int i9 = arrangement.mediumCount;
                if (i9 > 1) {
                    arrangement.mediumCount = i9 - 1;
                }
            }
            itemCount--;
        }
        return z9;
    }

    @Override // com.google.android.material.carousel.CarouselStrategy
    public KeylineState onFirstChildMeasuredWithMargins(Carousel carousel, View view) {
        float containerHeight = carousel.getContainerHeight();
        if (carousel.isHorizontal()) {
            containerHeight = carousel.getContainerWidth();
        }
        C0372z0 c0372z0 = (C0372z0) view.getLayoutParams();
        float f2 = ((ViewGroup.MarginLayoutParams) c0372z0).topMargin + ((ViewGroup.MarginLayoutParams) c0372z0).bottomMargin;
        float measuredHeight = view.getMeasuredHeight();
        if (carousel.isHorizontal()) {
            f2 = ((ViewGroup.MarginLayoutParams) c0372z0).leftMargin + ((ViewGroup.MarginLayoutParams) c0372z0).rightMargin;
            measuredHeight = view.getMeasuredWidth();
        }
        float f7 = f2;
        float smallSizeMin = CarouselStrategyHelper.getSmallSizeMin(view.getContext()) + f7;
        float smallSizeMax = CarouselStrategyHelper.getSmallSizeMax(view.getContext()) + f7;
        float fMin = Math.min(measuredHeight + f7, containerHeight);
        float fC = c.c((measuredHeight / 3.0f) + f7, CarouselStrategyHelper.getSmallSizeMin(view.getContext()) + f7, CarouselStrategyHelper.getSmallSizeMax(view.getContext()) + f7);
        float f9 = (fMin + fC) / 2.0f;
        int[] iArrDoubleCounts = SMALL_COUNTS;
        if (containerHeight < 2.0f * smallSizeMin) {
            iArrDoubleCounts = new int[]{0};
        }
        int[] iArrDoubleCounts2 = MEDIUM_COUNTS;
        if (carousel.getCarouselAlignment() == 1) {
            iArrDoubleCounts = CarouselStrategy.doubleCounts(iArrDoubleCounts);
            iArrDoubleCounts2 = CarouselStrategy.doubleCounts(iArrDoubleCounts2);
        }
        int[] iArr = iArrDoubleCounts;
        int[] iArr2 = iArrDoubleCounts2;
        int iMax = (int) Math.max(1.0d, Math.floor(((containerHeight - (CarouselStrategyHelper.maxValue(iArr2) * f9)) - (CarouselStrategyHelper.maxValue(iArr) * smallSizeMax)) / fMin));
        int iCeil = (int) Math.ceil(containerHeight / fMin);
        int i5 = (iCeil - iMax) + 1;
        int[] iArr3 = new int[i5];
        for (int i7 = 0; i7 < i5; i7++) {
            iArr3[i7] = iCeil - i7;
        }
        Arrangement arrangementFindLowestCostArrangement = Arrangement.findLowestCostArrangement(containerHeight, fC, smallSizeMin, smallSizeMax, iArr, f9, iArr2, fMin, iArr3);
        this.keylineCount = arrangementFindLowestCostArrangement.getItemCount();
        if (ensureArrangementFitsItemCount(arrangementFindLowestCostArrangement, carousel.getItemCount())) {
            arrangementFindLowestCostArrangement = Arrangement.findLowestCostArrangement(containerHeight, fC, smallSizeMin, smallSizeMax, new int[]{arrangementFindLowestCostArrangement.smallCount}, f9, new int[]{arrangementFindLowestCostArrangement.mediumCount}, fMin, new int[]{arrangementFindLowestCostArrangement.largeCount});
        }
        return CarouselStrategyHelper.createKeylineState(view.getContext(), f7, containerHeight, arrangementFindLowestCostArrangement, carousel.getCarouselAlignment());
    }

    @Override // com.google.android.material.carousel.CarouselStrategy
    public boolean shouldRefreshKeylineState(Carousel carousel, int i5) {
        return (i5 < this.keylineCount && carousel.getItemCount() >= this.keylineCount) || (i5 >= this.keylineCount && carousel.getItemCount() < this.keylineCount);
    }
}
