package com.google.android.material.carousel;

import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.C0372z0;

/* JADX INFO: loaded from: classes.dex */
public class FullScreenCarouselStrategy extends CarouselStrategy {
    @Override // com.google.android.material.carousel.CarouselStrategy
    public KeylineState onFirstChildMeasuredWithMargins(Carousel carousel, View view) {
        float containerHeight;
        int i5;
        int i7;
        C0372z0 c0372z0 = (C0372z0) view.getLayoutParams();
        if (carousel.isHorizontal()) {
            containerHeight = carousel.getContainerWidth();
            i5 = ((ViewGroup.MarginLayoutParams) c0372z0).leftMargin;
            i7 = ((ViewGroup.MarginLayoutParams) c0372z0).rightMargin;
        } else {
            containerHeight = carousel.getContainerHeight();
            i5 = ((ViewGroup.MarginLayoutParams) c0372z0).topMargin;
            i7 = ((ViewGroup.MarginLayoutParams) c0372z0).bottomMargin;
        }
        float f2 = i5 + i7;
        return CarouselStrategyHelper.createLeftAlignedKeylineState(view.getContext(), f2, containerHeight, new Arrangement(0, 0.0f, 0.0f, 0.0f, 0, 0.0f, 0, Math.min(containerHeight + f2, containerHeight), 1, containerHeight));
    }
}
