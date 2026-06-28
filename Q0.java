package androidx.recyclerview.widget;

import android.graphics.PointF;
import android.util.Log;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class Q0 {
    private AbstractC0370y0 mLayoutManager;
    private boolean mPendingInitialRun;
    private RecyclerView mRecyclerView;
    private final O0 mRecyclingAction;
    private boolean mRunning;
    private boolean mStarted;
    private int mTargetPosition = -1;
    private View mTargetView;

    public Q0() {
        O0 o02 = new O0();
        o02.f8965d = -1;
        o02.f8967f = false;
        o02.f8968g = 0;
        o02.f8962a = 0;
        o02.f8963b = 0;
        o02.f8964c = Integer.MIN_VALUE;
        o02.f8966e = null;
        this.mRecyclingAction = o02;
    }

    public PointF computeScrollVectorForPosition(int i5) {
        Object layoutManager = getLayoutManager();
        if (layoutManager instanceof P0) {
            return ((P0) layoutManager).computeScrollVectorForPosition(i5);
        }
        Log.w("SeslRecyclerView", "You should override computeScrollVectorForPosition when the LayoutManager does not implement " + P0.class.getCanonicalName());
        return null;
    }

    public View findViewByPosition(int i5) {
        return this.mRecyclerView.mLayout.findViewByPosition(i5);
    }

    public int getChildCount() {
        return this.mRecyclerView.mLayout.getChildCount();
    }

    public int getChildPosition(View view) {
        return this.mRecyclerView.getChildLayoutPosition(view);
    }

    public AbstractC0370y0 getLayoutManager() {
        return this.mLayoutManager;
    }

    public int getTargetPosition() {
        return this.mTargetPosition;
    }

    @Deprecated
    public void instantScrollToPosition(int i5) {
        this.mRecyclerView.scrollToPosition(i5);
    }

    public boolean isPendingInitialRun() {
        return this.mPendingInitialRun;
    }

    public boolean isRunning() {
        return this.mRunning;
    }

    public void normalize(PointF pointF) {
        float f2 = pointF.x;
        float f7 = pointF.y;
        float fSqrt = (float) Math.sqrt((f7 * f7) + (f2 * f2));
        pointF.x /= fSqrt;
        pointF.y /= fSqrt;
    }

    public void onAnimation(int i5, int i7) {
        PointF pointFComputeScrollVectorForPosition;
        RecyclerView recyclerView = this.mRecyclerView;
        if (this.mTargetPosition == -1 || recyclerView == null) {
            stop();
        }
        if (this.mPendingInitialRun && this.mTargetView == null && this.mLayoutManager != null && (pointFComputeScrollVectorForPosition = computeScrollVectorForPosition(this.mTargetPosition)) != null) {
            float f2 = pointFComputeScrollVectorForPosition.x;
            if (f2 != 0.0f || pointFComputeScrollVectorForPosition.y != 0.0f) {
                recyclerView.scrollStep((int) Math.signum(f2), (int) Math.signum(pointFComputeScrollVectorForPosition.y), null);
            }
        }
        this.mPendingInitialRun = false;
        View view = this.mTargetView;
        if (view != null) {
            if (getChildPosition(view) == this.mTargetPosition) {
                onTargetFound(this.mTargetView, recyclerView.mState, this.mRecyclingAction);
                this.mRecyclingAction.a(recyclerView);
                stop();
            } else {
                Log.e("SeslRecyclerView", "Passed over target position while smooth scrolling.");
                this.mTargetView = null;
            }
        }
        if (this.mRunning) {
            onSeekTargetStep(i5, i7, recyclerView.mState, this.mRecyclingAction);
            O0 o02 = this.mRecyclingAction;
            boolean z9 = o02.f8965d >= 0;
            o02.a(recyclerView);
            if (z9 && this.mRunning) {
                this.mPendingInitialRun = true;
                recyclerView.mViewFlinger.b();
            }
        }
    }

    public void onChildAttachedToWindow(View view) {
        if (getChildPosition(view) == getTargetPosition()) {
            this.mTargetView = view;
            if (RecyclerView.sVerboseLoggingEnabled) {
                Log.d("SeslRecyclerView", "smooth scroll target view has been attached");
            }
        }
    }

    public abstract void onSeekTargetStep(int i5, int i7, R0 r02, O0 o02);

    public abstract void onStart();

    public abstract void onStop();

    public abstract void onTargetFound(View view, R0 r02, O0 o02);

    public void setTargetPosition(int i5) {
        this.mTargetPosition = i5;
    }

    public void start(RecyclerView recyclerView, AbstractC0370y0 abstractC0370y0) {
        U0 u02 = recyclerView.mViewFlinger;
        RecyclerView recyclerView2 = u02.f9030k;
        recyclerView2.removeCallbacks(u02);
        u02.f9027g.abortAnimation();
        com.bumptech.glide.d.E(recyclerView2, 0.0f);
        if (this.mStarted) {
            Log.w("SeslRecyclerView", "An instance of " + getClass().getSimpleName() + " was started more than once. Each instance of" + getClass().getSimpleName() + " is intended to only be used once. You should create a new instance for each use.");
        }
        this.mRecyclerView = recyclerView;
        this.mLayoutManager = abstractC0370y0;
        int i5 = this.mTargetPosition;
        if (i5 == -1) {
            throw new IllegalArgumentException("Invalid target position");
        }
        recyclerView.mState.f8978a = i5;
        this.mRunning = true;
        this.mPendingInitialRun = true;
        this.mTargetView = findViewByPosition(getTargetPosition());
        onStart();
        this.mRecyclerView.mViewFlinger.b();
        this.mStarted = true;
    }

    public final void stop() {
        if (this.mRunning) {
            this.mRunning = false;
            onStop();
            this.mRecyclerView.mState.f8978a = -1;
            this.mTargetView = null;
            this.mTargetPosition = -1;
            this.mPendingInitialRun = false;
            this.mLayoutManager.onSmoothScrollerStopped(this);
            this.mLayoutManager = null;
            this.mRecyclerView = null;
        }
    }
}
