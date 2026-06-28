package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.OverScroller;
import android.widget.Scroller;

/* JADX INFO: loaded from: classes.dex */
public abstract class e1 extends B0 {
    static final float MILLISECONDS_PER_INCH = 100.0f;
    private Scroller mGravityScroller;
    private OverScroller mOverScroller;
    RecyclerView mRecyclerView;
    private final D0 mScrollListener = new d1(this);

    public void attachToRecyclerView(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.mRecyclerView;
        if (recyclerView2 == recyclerView) {
            return;
        }
        if (recyclerView2 != null) {
            recyclerView2.removeOnScrollListener(this.mScrollListener);
            this.mRecyclerView.setOnFlingListener(null);
        }
        this.mRecyclerView = recyclerView;
        if (recyclerView != null) {
            if (recyclerView.getOnFlingListener() != null) {
                throw new IllegalStateException("An instance of OnFlingListener already set.");
            }
            this.mRecyclerView.addOnScrollListener(this.mScrollListener);
            this.mRecyclerView.setOnFlingListener(this);
            this.mGravityScroller = new Scroller(this.mRecyclerView.getContext(), new DecelerateInterpolator());
            this.mOverScroller = new OverScroller(this.mRecyclerView.getContext());
            snapToTargetExistingView();
        }
    }

    public abstract int[] calculateDistanceToFinalSnap(AbstractC0370y0 abstractC0370y0, View view);

    @SuppressLint({"UnknownNullness"})
    public int[] calculateScrollDistance(int i5, int i7) {
        this.mGravityScroller.fling(0, 0, i5, i7, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return new int[]{this.mGravityScroller.getFinalX(), this.mGravityScroller.getFinalY()};
    }

    public abstract Q0 createScroller(AbstractC0370y0 abstractC0370y0);

    @Deprecated
    public U createSnapScroller(AbstractC0370y0 abstractC0370y0) {
        if (abstractC0370y0 instanceof P0) {
            return new C0323a0(this.mRecyclerView.getContext(), 1, this);
        }
        return null;
    }

    public abstract View findSnapView(AbstractC0370y0 abstractC0370y0);

    public abstract int findTargetSnapPosition(AbstractC0370y0 abstractC0370y0, int i5, int i7);

    @Override // androidx.recyclerview.widget.B0
    public boolean onFling(int i5, int i7) {
        Q0 q0CreateScroller;
        int iFindTargetSnapPosition;
        AbstractC0370y0 layoutManager = this.mRecyclerView.getLayoutManager();
        if (layoutManager == null || this.mRecyclerView.getAdapter() == null) {
            return false;
        }
        int minFlingVelocity = this.mRecyclerView.getMinFlingVelocity();
        if ((Math.abs(i7) <= minFlingVelocity && Math.abs(i5) <= minFlingVelocity) || !(layoutManager instanceof P0) || (q0CreateScroller = createScroller(layoutManager)) == null || (iFindTargetSnapPosition = findTargetSnapPosition(layoutManager, i5, i7)) == -1) {
            return false;
        }
        q0CreateScroller.setTargetPosition(iFindTargetSnapPosition);
        layoutManager.startSmoothScroll(q0CreateScroller);
        return true;
    }

    public int[] seslCalculateScrollDistanceForLinear(int i5, int i7) {
        this.mOverScroller.computeScrollOffset();
        this.mOverScroller.fling(0, 0, i5, i7, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return new int[]{this.mOverScroller.getFinalX(), this.mOverScroller.getFinalY()};
    }

    public void snapToTargetExistingView() {
        AbstractC0370y0 layoutManager;
        View viewFindSnapView;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || (layoutManager = recyclerView.getLayoutManager()) == null || (viewFindSnapView = findSnapView(layoutManager)) == null) {
            return;
        }
        int[] iArrCalculateDistanceToFinalSnap = calculateDistanceToFinalSnap(layoutManager, viewFindSnapView);
        int i5 = iArrCalculateDistanceToFinalSnap[0];
        if (i5 == 0 && iArrCalculateDistanceToFinalSnap[1] == 0) {
            return;
        }
        this.mRecyclerView.smoothScrollBy(i5, iArrCalculateDistanceToFinalSnap[1]);
    }
}
