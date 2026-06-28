package androidx.recyclerview.widget;

import android.view.animation.Interpolator;
import android.widget.OverScroller;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class U0 implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f9025e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f9026f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public OverScroller f9027g;
    public Interpolator h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f9028i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f9029j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f9030k;

    public U0(RecyclerView recyclerView) {
        this.f9030k = recyclerView;
        Interpolator interpolator = RecyclerView.sQuinticInterpolator;
        this.h = interpolator;
        this.f9028i = false;
        this.f9029j = false;
        this.f9027g = new OverScroller(recyclerView.getContext(), interpolator);
    }

    public final void a(int i5, int i7) {
        RecyclerView recyclerView = this.f9030k;
        recyclerView.setScrollState(2);
        this.f9026f = 0;
        this.f9025e = 0;
        Interpolator interpolator = this.h;
        Interpolator interpolator2 = RecyclerView.sQuinticInterpolator;
        if (interpolator != interpolator2) {
            this.h = interpolator2;
            this.f9027g = new OverScroller(recyclerView.getContext(), interpolator2);
        }
        OverScroller overScroller = this.f9027g;
        boolean z9 = recyclerView.mIsSkipMoveEvent;
        float f2 = recyclerView.mFrameLatency;
        Class cls = Integer.TYPE;
        Method methodT = com.bumptech.glide.c.t(OverScroller.class, "hidden_fling", cls, cls, Boolean.TYPE, Float.TYPE);
        if (methodT != null) {
            com.bumptech.glide.c.C(overScroller, methodT, Integer.valueOf(i5), Integer.valueOf(i7), Boolean.valueOf(z9), Float.valueOf(f2));
        } else {
            overScroller.fling(0, 0, i5, i7, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        b();
    }

    public final void b() {
        if (this.f9028i) {
            this.f9029j = true;
            return;
        }
        RecyclerView recyclerView = this.f9030k;
        recyclerView.removeCallbacks(this);
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        recyclerView.postOnAnimation(this);
    }

    public final void c(int i5, int i7, Interpolator interpolator, int i9) {
        int iMin;
        int iRound;
        RecyclerView recyclerView = this.f9030k;
        if (i9 == Integer.MIN_VALUE) {
            int iAbs = Math.abs(i5);
            int iAbs2 = Math.abs(i7);
            boolean z9 = iAbs > iAbs2;
            int iSqrt = (int) Math.sqrt(0);
            int iSqrt2 = (int) Math.sqrt((i7 * i7) + (i5 * i5));
            int width = z9 ? recyclerView.getWidth() : recyclerView.getHeight();
            int i10 = width / 2;
            float f2 = width;
            float f7 = i10;
            float fSin = (((float) Math.sin((Math.min(1.0f, (iSqrt2 * 1.0f) / f2) - 0.5f) * 0.47123894f)) * f7) + f7;
            if (iSqrt > 0) {
                iRound = Math.round(Math.abs(fSin / iSqrt) * 1000.0f) * 4;
            } else {
                if (!z9) {
                    iAbs = iAbs2;
                }
                iRound = (int) (((iAbs / f2) + 1.0f) * 300.0f);
            }
            iMin = Math.min(iRound, 2000);
        } else {
            iMin = i9;
        }
        Interpolator interpolator2 = interpolator == null ? RecyclerView.sQuinticInterpolator : interpolator;
        recyclerView.startNestedScroll(i5 != 0 ? 2 : 1, 1);
        if (!this.f9030k.dispatchNestedPreScroll(i5, i7, null, null, 1)) {
            if (this.h != interpolator2) {
                this.h = interpolator2;
                this.f9027g = new OverScroller(recyclerView.getContext(), interpolator2);
            }
            this.f9026f = 0;
            this.f9025e = 0;
            recyclerView.setScrollState(2);
            this.f9027g.startScroll(0, 0, i5, i7, iMin);
            b();
        }
        recyclerView.c(i7);
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i5;
        int i7;
        int i9;
        int i10;
        RecyclerView recyclerView = this.f9030k;
        if (recyclerView.mLayout == null) {
            recyclerView.removeCallbacks(this);
            this.f9027g.abortAnimation();
            com.bumptech.glide.d.E(recyclerView, 0.0f);
            return;
        }
        this.f9029j = false;
        this.f9028i = true;
        recyclerView.consumePendingUpdateOperations();
        OverScroller overScroller = this.f9027g;
        if (overScroller.computeScrollOffset()) {
            int currX = overScroller.getCurrX();
            int currY = overScroller.getCurrY();
            int i11 = currX - this.f9025e;
            int i12 = currY - this.f9026f;
            this.f9025e = currX;
            this.f9026f = currY;
            int iConsumeFlingInHorizontalStretch = recyclerView.consumeFlingInHorizontalStretch(i11);
            int iConsumeFlingInVerticalStretch = recyclerView.consumeFlingInVerticalStretch(i12);
            int[] iArr = recyclerView.mReusableIntPair;
            iArr[0] = 0;
            iArr[1] = 0;
            if (recyclerView.dispatchNestedPreScroll(iConsumeFlingInHorizontalStretch, iConsumeFlingInVerticalStretch, iArr, null, 1)) {
                int[] iArr2 = recyclerView.mReusableIntPair;
                iConsumeFlingInHorizontalStretch -= iArr2[0];
                int i13 = iArr2[1];
                iConsumeFlingInVerticalStretch -= i13;
                recyclerView.c(i13);
            } else {
                recyclerView.c(iConsumeFlingInVerticalStretch);
            }
            if (recyclerView.getOverScrollMode() != 2) {
                recyclerView.considerReleasingGlowsOnScroll(iConsumeFlingInHorizontalStretch, iConsumeFlingInVerticalStretch);
            }
            if (recyclerView.mAdapter != null) {
                int[] iArr3 = recyclerView.mReusableIntPair;
                iArr3[0] = 0;
                iArr3[1] = 0;
                recyclerView.scrollStep(iConsumeFlingInHorizontalStretch, iConsumeFlingInVerticalStretch, iArr3);
                int[] iArr4 = recyclerView.mReusableIntPair;
                int i14 = iArr4[0];
                int i15 = iArr4[1];
                int i16 = iConsumeFlingInHorizontalStretch - i14;
                int i17 = iConsumeFlingInVerticalStretch - i15;
                Q0 q02 = recyclerView.mLayout.mSmoothScroller;
                if (q02 != null && !q02.isPendingInitialRun() && q02.isRunning()) {
                    int iB = recyclerView.mState.b();
                    if (iB == 0) {
                        q02.stop();
                    } else if (q02.getTargetPosition() >= iB) {
                        q02.setTargetPosition(iB - 1);
                        q02.onAnimation(i14, i15);
                    } else {
                        q02.onAnimation(i14, i15);
                    }
                }
                i9 = i15;
                i10 = i14;
                i5 = i16;
                i7 = i17;
            } else {
                i5 = iConsumeFlingInHorizontalStretch;
                i7 = iConsumeFlingInVerticalStretch;
                i9 = 0;
                i10 = 0;
            }
            if (!recyclerView.mItemDecorations.isEmpty()) {
                recyclerView.invalidate();
            }
            int[] iArr5 = recyclerView.mReusableIntPair;
            iArr5[0] = 0;
            iArr5[1] = 0;
            if (recyclerView.getScrollingChildHelper().e(i10, i9, i5, i7, null, 1, iArr5)) {
                recyclerView.mScrollOffset[0] = 0;
                recyclerView.mScrollOffset[1] = 0;
            }
            if (recyclerView.mScrollOffset[0] < 0 || recyclerView.mScrollOffset[1] < 0) {
                recyclerView.mScrollOffset[0] = 0;
                recyclerView.mScrollOffset[1] = 0;
            }
            int[] iArr6 = recyclerView.mReusableIntPair;
            int i18 = i5 - iArr6[0];
            int i19 = i7 - iArr6[1];
            if (i10 != 0 || i9 != 0) {
                recyclerView.dispatchOnScrolled(i10, i9);
            }
            if (!recyclerView.awakenScrollBars()) {
                recyclerView.invalidate();
            }
            boolean z9 = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i18 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i19 != 0));
            Q0 q03 = recyclerView.mLayout.mSmoothScroller;
            if ((q03 == null || !q03.isPendingInitialRun()) && z9) {
                if (recyclerView.getOverScrollMode() != 2 && !recyclerView.mEdgeEffectByDragging) {
                    int currVelocity = (int) overScroller.getCurrVelocity();
                    int i20 = i18 < 0 ? -currVelocity : i18 > 0 ? currVelocity : 0;
                    if (i19 < 0) {
                        currVelocity = -currVelocity;
                    } else if (i19 <= 0) {
                        currVelocity = 0;
                    }
                    recyclerView.absorbGlows(i20, currVelocity);
                }
                if (RecyclerView.ALLOW_THREAD_GAP_WORK) {
                    D d8 = recyclerView.mPrefetchRegistry;
                    int[] iArr7 = d8.f8872c;
                    if (iArr7 != null) {
                        Arrays.fill(iArr7, -1);
                    }
                    d8.f8873d = 0;
                }
            } else {
                b();
                F f2 = recyclerView.mGapWorker;
                if (f2 != null) {
                    f2.a(recyclerView, i10, i9);
                }
            }
            com.bumptech.glide.d.E(recyclerView, Math.abs(overScroller.getCurrVelocity()));
        }
        Q0 q04 = recyclerView.mLayout.mSmoothScroller;
        if (q04 != null && q04.isPendingInitialRun()) {
            q04.onAnimation(0, 0);
        }
        this.f9028i = false;
        if (!this.f9029j) {
            recyclerView.setScrollState(0);
            recyclerView.stopNestedScroll(1);
        } else {
            recyclerView.removeCallbacks(this);
            WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
            recyclerView.postOnAnimation(this);
        }
    }
}
