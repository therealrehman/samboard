package com.google.android.material.carousel;

import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.AbstractC0370y0;
import androidx.recyclerview.widget.O0;
import androidx.recyclerview.widget.P0;
import androidx.recyclerview.widget.Q0;
import androidx.recyclerview.widget.R0;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.U;
import androidx.recyclerview.widget.e1;

/* JADX INFO: loaded from: classes.dex */
public class CarouselSnapHelper extends e1 {
    private static final float HORIZONTAL_SNAP_SPEED = 100.0f;
    private static final float VERTICAL_SNAP_SPEED = 50.0f;
    private final boolean disableFling;
    private RecyclerView recyclerView;

    public CarouselSnapHelper() {
        this(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int[] calculateDistanceToSnap(AbstractC0370y0 abstractC0370y0, View view, boolean z9) {
        if (!(abstractC0370y0 instanceof CarouselLayoutManager)) {
            return new int[]{0, 0};
        }
        int iDistanceToFirstFocalKeyline = distanceToFirstFocalKeyline(view, (CarouselLayoutManager) abstractC0370y0, z9);
        return abstractC0370y0.canScrollHorizontally() ? new int[]{iDistanceToFirstFocalKeyline, 0} : abstractC0370y0.canScrollVertically() ? new int[]{0, iDistanceToFirstFocalKeyline} : new int[]{0, 0};
    }

    private int distanceToFirstFocalKeyline(View view, CarouselLayoutManager carouselLayoutManager, boolean z9) {
        return carouselLayoutManager.getOffsetToScrollToPositionForSnap(carouselLayoutManager.getPosition(view), z9);
    }

    private View findViewNearestFirstKeyline(AbstractC0370y0 abstractC0370y0) {
        int childCount = abstractC0370y0.getChildCount();
        View view = null;
        if (childCount != 0 && (abstractC0370y0 instanceof CarouselLayoutManager)) {
            CarouselLayoutManager carouselLayoutManager = (CarouselLayoutManager) abstractC0370y0;
            int i5 = Integer.MAX_VALUE;
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = abstractC0370y0.getChildAt(i7);
                int iAbs = Math.abs(carouselLayoutManager.getOffsetToScrollToPositionForSnap(abstractC0370y0.getPosition(childAt), false));
                if (iAbs < i5) {
                    view = childAt;
                    i5 = iAbs;
                }
            }
        }
        return view;
    }

    private boolean isForwardFling(AbstractC0370y0 abstractC0370y0, int i5, int i7) {
        return abstractC0370y0.canScrollHorizontally() ? i5 > 0 : i7 > 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean isReverseLayout(AbstractC0370y0 abstractC0370y0) {
        PointF pointFComputeScrollVectorForPosition;
        int itemCount = abstractC0370y0.getItemCount();
        if (!(abstractC0370y0 instanceof P0) || (pointFComputeScrollVectorForPosition = ((P0) abstractC0370y0).computeScrollVectorForPosition(itemCount - 1)) == null) {
            return false;
        }
        return pointFComputeScrollVectorForPosition.x < 0.0f || pointFComputeScrollVectorForPosition.y < 0.0f;
    }

    @Override // androidx.recyclerview.widget.e1
    public void attachToRecyclerView(RecyclerView recyclerView) {
        super.attachToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override // androidx.recyclerview.widget.e1
    public int[] calculateDistanceToFinalSnap(AbstractC0370y0 abstractC0370y0, View view) {
        return calculateDistanceToSnap(abstractC0370y0, view, false);
    }

    @Override // androidx.recyclerview.widget.e1
    public Q0 createScroller(final AbstractC0370y0 abstractC0370y0) {
        if (abstractC0370y0 instanceof P0) {
            return new U(this.recyclerView.getContext()) { // from class: com.google.android.material.carousel.CarouselSnapHelper.1
                @Override // androidx.recyclerview.widget.U
                public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                    float f2;
                    float f7;
                    if (abstractC0370y0.canScrollVertically()) {
                        f2 = displayMetrics.densityDpi;
                        f7 = CarouselSnapHelper.VERTICAL_SNAP_SPEED;
                    } else {
                        f2 = displayMetrics.densityDpi;
                        f7 = CarouselSnapHelper.HORIZONTAL_SNAP_SPEED;
                    }
                    return f7 / f2;
                }

                @Override // androidx.recyclerview.widget.U, androidx.recyclerview.widget.Q0
                public void onTargetFound(View view, R0 r02, O0 o02) {
                    if (CarouselSnapHelper.this.recyclerView != null) {
                        CarouselSnapHelper carouselSnapHelper = CarouselSnapHelper.this;
                        int[] iArrCalculateDistanceToSnap = carouselSnapHelper.calculateDistanceToSnap(carouselSnapHelper.recyclerView.getLayoutManager(), view, true);
                        int i5 = iArrCalculateDistanceToSnap[0];
                        int i7 = iArrCalculateDistanceToSnap[1];
                        int iCalculateTimeForDeceleration = calculateTimeForDeceleration(Math.max(Math.abs(i5), Math.abs(i7)));
                        if (iCalculateTimeForDeceleration > 0) {
                            o02.b(i5, i7, iCalculateTimeForDeceleration, this.mDecelerateInterpolator);
                        }
                    }
                }
            };
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.e1
    public View findSnapView(AbstractC0370y0 abstractC0370y0) {
        return findViewNearestFirstKeyline(abstractC0370y0);
    }

    @Override // androidx.recyclerview.widget.e1
    public int findTargetSnapPosition(AbstractC0370y0 abstractC0370y0, int i5, int i7) {
        int itemCount;
        if (!this.disableFling || (itemCount = abstractC0370y0.getItemCount()) == 0) {
            return -1;
        }
        int childCount = abstractC0370y0.getChildCount();
        View view = null;
        int i9 = Integer.MAX_VALUE;
        int i10 = Integer.MIN_VALUE;
        View view2 = null;
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = abstractC0370y0.getChildAt(i11);
            if (childAt != null) {
                int iDistanceToFirstFocalKeyline = distanceToFirstFocalKeyline(childAt, (CarouselLayoutManager) abstractC0370y0, false);
                if (iDistanceToFirstFocalKeyline <= 0 && iDistanceToFirstFocalKeyline > i10) {
                    view2 = childAt;
                    i10 = iDistanceToFirstFocalKeyline;
                }
                if (iDistanceToFirstFocalKeyline >= 0 && iDistanceToFirstFocalKeyline < i9) {
                    view = childAt;
                    i9 = iDistanceToFirstFocalKeyline;
                }
            }
        }
        boolean zIsForwardFling = isForwardFling(abstractC0370y0, i5, i7);
        if (zIsForwardFling && view != null) {
            return abstractC0370y0.getPosition(view);
        }
        if (!zIsForwardFling && view2 != null) {
            return abstractC0370y0.getPosition(view2);
        }
        if (zIsForwardFling) {
            view = view2;
        }
        if (view == null) {
            return -1;
        }
        int position = abstractC0370y0.getPosition(view) + (isReverseLayout(abstractC0370y0) == zIsForwardFling ? -1 : 1);
        if (position < 0 || position >= itemCount) {
            return -1;
        }
        return position;
    }

    public CarouselSnapHelper(boolean z9) {
        this.disableFling = z9;
    }
}
