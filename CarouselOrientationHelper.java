package com.google.android.material.carousel;

import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.C0372z0;

/* JADX INFO: loaded from: classes.dex */
abstract class CarouselOrientationHelper {
    final int orientation;

    private static CarouselOrientationHelper createHorizontalHelper(final CarouselLayoutManager carouselLayoutManager) {
        return new CarouselOrientationHelper(0) { // from class: com.google.android.material.carousel.CarouselOrientationHelper.2
            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public void containMaskWithinBounds(RectF rectF, RectF rectF2, RectF rectF3) {
                float f2 = rectF2.left;
                float f7 = rectF3.left;
                if (f2 < f7 && rectF2.right > f7) {
                    float f9 = f7 - f2;
                    rectF.left += f9;
                    rectF2.left += f9;
                }
                float f10 = rectF2.right;
                float f11 = rectF3.right;
                if (f10 <= f11 || rectF2.left >= f11) {
                    return;
                }
                float f12 = f10 - f11;
                rectF.right = Math.max(rectF.right - f12, rectF.left);
                rectF2.right = Math.max(rectF2.right - f12, rectF2.left);
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public float getMaskMargins(C0372z0 c0372z0) {
                return ((ViewGroup.MarginLayoutParams) c0372z0).rightMargin + ((ViewGroup.MarginLayoutParams) c0372z0).leftMargin;
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public RectF getMaskRect(float f2, float f7, float f9, float f10) {
                return new RectF(f10, 0.0f, f7 - f10, f2);
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public int getParentBottom() {
                return carouselLayoutManager.getHeight() - carouselLayoutManager.getPaddingBottom();
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public int getParentEnd() {
                return carouselLayoutManager.isLayoutRtl() ? getParentLeft() : getParentRight();
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public int getParentLeft() {
                return 0;
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public int getParentRight() {
                return carouselLayoutManager.getWidth();
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public int getParentStart() {
                return carouselLayoutManager.isLayoutRtl() ? getParentRight() : getParentLeft();
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public int getParentTop() {
                return carouselLayoutManager.getPaddingTop();
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public void layoutDecoratedWithMargins(View view, int i5, int i7) {
                carouselLayoutManager.layoutDecoratedWithMargins(view, i5, getParentTop(), i7, getParentBottom());
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public void moveMaskOnEdgeOutsideBounds(RectF rectF, RectF rectF2, RectF rectF3) {
                if (rectF2.right <= rectF3.left) {
                    float fFloor = ((float) Math.floor(rectF.right)) - 1.0f;
                    rectF.right = fFloor;
                    rectF.left = Math.min(rectF.left, fFloor);
                }
                if (rectF2.left >= rectF3.right) {
                    float fCeil = ((float) Math.ceil(rectF.left)) + 1.0f;
                    rectF.left = fCeil;
                    rectF.right = Math.max(fCeil, rectF.right);
                }
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public void offsetChild(View view, Rect rect, float f2, float f7) {
                view.offsetLeftAndRight((int) (f7 - (rect.left + f2)));
            }
        };
    }

    public static CarouselOrientationHelper createOrientationHelper(CarouselLayoutManager carouselLayoutManager, int i5) {
        if (i5 == 0) {
            return createHorizontalHelper(carouselLayoutManager);
        }
        if (i5 == 1) {
            return createVerticalHelper(carouselLayoutManager);
        }
        throw new IllegalArgumentException("invalid orientation");
    }

    private static CarouselOrientationHelper createVerticalHelper(final CarouselLayoutManager carouselLayoutManager) {
        return new CarouselOrientationHelper(1) { // from class: com.google.android.material.carousel.CarouselOrientationHelper.1
            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public void containMaskWithinBounds(RectF rectF, RectF rectF2, RectF rectF3) {
                float f2 = rectF2.top;
                float f7 = rectF3.top;
                if (f2 < f7 && rectF2.bottom > f7) {
                    float f9 = f7 - f2;
                    rectF.top += f9;
                    rectF3.top += f9;
                }
                float f10 = rectF2.bottom;
                float f11 = rectF3.bottom;
                if (f10 <= f11 || rectF2.top >= f11) {
                    return;
                }
                float f12 = f10 - f11;
                rectF.bottom = Math.max(rectF.bottom - f12, rectF.top);
                rectF2.bottom = Math.max(rectF2.bottom - f12, rectF2.top);
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public float getMaskMargins(C0372z0 c0372z0) {
                return ((ViewGroup.MarginLayoutParams) c0372z0).topMargin + ((ViewGroup.MarginLayoutParams) c0372z0).bottomMargin;
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public RectF getMaskRect(float f2, float f7, float f9, float f10) {
                return new RectF(0.0f, f9, f7, f2 - f9);
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public int getParentBottom() {
                return carouselLayoutManager.getHeight();
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public int getParentEnd() {
                return getParentBottom();
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public int getParentLeft() {
                return carouselLayoutManager.getPaddingLeft();
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public int getParentRight() {
                return carouselLayoutManager.getWidth() - carouselLayoutManager.getPaddingRight();
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public int getParentStart() {
                return getParentTop();
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public int getParentTop() {
                return 0;
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public void layoutDecoratedWithMargins(View view, int i5, int i7) {
                carouselLayoutManager.layoutDecoratedWithMargins(view, getParentLeft(), i5, getParentRight(), i7);
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public void moveMaskOnEdgeOutsideBounds(RectF rectF, RectF rectF2, RectF rectF3) {
                if (rectF2.bottom <= rectF3.top) {
                    float fFloor = ((float) Math.floor(rectF.bottom)) - 1.0f;
                    rectF.bottom = fFloor;
                    rectF.top = Math.min(rectF.top, fFloor);
                }
                if (rectF2.top >= rectF3.bottom) {
                    float fCeil = ((float) Math.ceil(rectF.top)) + 1.0f;
                    rectF.top = fCeil;
                    rectF.bottom = Math.max(fCeil, rectF.bottom);
                }
            }

            @Override // com.google.android.material.carousel.CarouselOrientationHelper
            public void offsetChild(View view, Rect rect, float f2, float f7) {
                view.offsetTopAndBottom((int) (f7 - (rect.top + f2)));
            }
        };
    }

    public abstract void containMaskWithinBounds(RectF rectF, RectF rectF2, RectF rectF3);

    public abstract float getMaskMargins(C0372z0 c0372z0);

    public abstract RectF getMaskRect(float f2, float f7, float f9, float f10);

    public abstract int getParentBottom();

    public abstract int getParentEnd();

    public abstract int getParentLeft();

    public abstract int getParentRight();

    public abstract int getParentStart();

    public abstract int getParentTop();

    public abstract void layoutDecoratedWithMargins(View view, int i5, int i7);

    public abstract void moveMaskOnEdgeOutsideBounds(RectF rectF, RectF rectF2, RectF rectF3);

    public abstract void offsetChild(View view, Rect rect, float f2, float f7);

    private CarouselOrientationHelper(int i5) {
        this.orientation = i5;
    }
}
