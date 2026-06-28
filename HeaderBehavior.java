package com.google.android.material.appbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.W;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
abstract class HeaderBehavior<V extends View> extends ViewOffsetBehavior<V> {
    private static final int INVALID_POINTER = -1;
    private int activePointerId;
    private Runnable flingRunnable;
    private boolean isBeingDragged;
    private int lastMotionY;
    private boolean mHasNoSnapFlag;
    int mLastInterceptTouchEvent;
    int mLastTouchEvent;
    OverScroller scroller;
    private int touchSlop;
    private VelocityTracker velocityTracker;

    public class FlingRunnable implements Runnable {
        private final V layout;
        private final CoordinatorLayout parent;

        public FlingRunnable(CoordinatorLayout coordinatorLayout, V v4) {
            this.parent = coordinatorLayout;
            this.layout = v4;
        }

        @Override // java.lang.Runnable
        public void run() {
            OverScroller overScroller;
            if (this.layout == null || (overScroller = HeaderBehavior.this.scroller) == null) {
                return;
            }
            if (!overScroller.computeScrollOffset()) {
                HeaderBehavior.this.onFlingFinished(this.parent, this.layout);
                return;
            }
            HeaderBehavior headerBehavior = HeaderBehavior.this;
            headerBehavior.setHeaderTopBottomOffset(this.parent, this.layout, headerBehavior.scroller.getCurrY());
            V v4 = this.layout;
            WeakHashMap weakHashMap = W.f7199a;
            v4.postOnAnimation(this);
        }
    }

    public HeaderBehavior() {
        this.activePointerId = -1;
        this.touchSlop = -1;
    }

    private void ensureVelocityTracker() {
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
    }

    public boolean canDragView(V v4) {
        return false;
    }

    public final boolean fling(CoordinatorLayout coordinatorLayout, V v4, int i5, int i7, float f2) {
        Runnable runnable = this.flingRunnable;
        if (runnable != null) {
            v4.removeCallbacks(runnable);
            this.flingRunnable = null;
        }
        if (this.scroller == null) {
            this.scroller = new OverScroller(v4.getContext());
        }
        this.scroller.fling(0, getTopAndBottomOffset(), 0, Math.round(f2), 0, 0, i5, i7);
        if (!this.scroller.computeScrollOffset()) {
            onFlingFinished(coordinatorLayout, v4);
            return false;
        }
        FlingRunnable flingRunnable = new FlingRunnable(coordinatorLayout, v4);
        this.flingRunnable = flingRunnable;
        WeakHashMap weakHashMap = W.f7199a;
        v4.postOnAnimation(flingRunnable);
        return true;
    }

    public int getLastInterceptTouchEventEvent() {
        return this.mLastInterceptTouchEvent;
    }

    public int getLastTouchEventEvent() {
        return this.mLastTouchEvent;
    }

    public int getMaxDragOffset(V v4) {
        return -v4.getHeight();
    }

    public int getScrollRangeForDragFling(V v4) {
        return v4.getHeight();
    }

    public int getTopBottomOffsetForScrollingSibling() {
        return getTopAndBottomOffset();
    }

    public boolean isFlingRunnable() {
        return this.flingRunnable != null;
    }

    public void onFlingFinished(CoordinatorLayout coordinatorLayout, V v4) {
        Runnable runnable = this.flingRunnable;
        if (runnable != null) {
            v4.removeCallbacks(runnable);
            this.flingRunnable = null;
        }
    }

    @Override // androidx.coordinatorlayout.widget.d
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v4, MotionEvent motionEvent) {
        int iFindPointerIndex;
        if (this.touchSlop < 0) {
            this.touchSlop = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        this.mLastInterceptTouchEvent = motionEvent.getAction();
        if (motionEvent.getActionMasked() == 2 && this.isBeingDragged) {
            int i5 = this.activePointerId;
            if (i5 == -1 || (iFindPointerIndex = motionEvent.findPointerIndex(i5)) == -1) {
                return false;
            }
            int y4 = (int) motionEvent.getY(iFindPointerIndex);
            if (Math.abs(y4 - this.lastMotionY) > this.touchSlop) {
                this.lastMotionY = y4;
                return true;
            }
        }
        if (motionEvent.getActionMasked() == 0) {
            this.activePointerId = -1;
            int x9 = (int) motionEvent.getX();
            int y9 = (int) motionEvent.getY();
            boolean z9 = canDragView(v4) && coordinatorLayout.isPointInChildBounds(v4, x9, y9);
            this.isBeingDragged = z9;
            if (z9) {
                this.lastMotionY = y9;
                this.activePointerId = motionEvent.getPointerId(0);
                ensureVelocityTracker();
                OverScroller overScroller = this.scroller;
                if (overScroller != null && !overScroller.isFinished()) {
                    this.scroller.abortAnimation();
                    return true;
                }
            }
        }
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x008b  */
    @Override // androidx.coordinatorlayout.widget.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout r11, V r12, android.view.MotionEvent r13) {
        /*
            r10 = this;
            int r0 = r13.getAction()
            r10.mLastTouchEvent = r0
            int r0 = r13.getActionMasked()
            r1 = -1
            r2 = 1
            r3 = 0
            if (r0 == r2) goto L53
            r4 = 2
            if (r0 == r4) goto L33
            r11 = 3
            if (r0 == r11) goto L79
            r11 = 6
            if (r0 == r11) goto L1a
            goto L87
        L1a:
            int r11 = r13.getActionIndex()
            if (r11 != 0) goto L21
            goto L22
        L21:
            r2 = r3
        L22:
            int r11 = r13.getPointerId(r2)
            r10.activePointerId = r11
            float r11 = r13.getY(r2)
            r12 = 1056964608(0x3f000000, float:0.5)
            float r11 = r11 + r12
            int r11 = (int) r11
            r10.lastMotionY = r11
            goto L87
        L33:
            int r0 = r10.activePointerId
            int r0 = r13.findPointerIndex(r0)
            if (r0 != r1) goto L3c
            return r3
        L3c:
            float r0 = r13.getY(r0)
            int r0 = (int) r0
            int r1 = r10.lastMotionY
            int r5 = r1 - r0
            r10.lastMotionY = r0
            int r6 = r10.getMaxDragOffset(r12)
            r7 = 0
            r2 = r10
            r3 = r11
            r4 = r12
            r2.scroll(r3, r4, r5, r6, r7)
            goto L87
        L53:
            boolean r0 = r10.mHasNoSnapFlag
            if (r0 == 0) goto L79
            android.view.VelocityTracker r0 = r10.velocityTracker
            if (r0 == 0) goto L79
            r0.addMovement(r13)
            android.view.VelocityTracker r0 = r10.velocityTracker
            r2 = 1000(0x3e8, float:1.401E-42)
            r0.computeCurrentVelocity(r2)
            android.view.VelocityTracker r0 = r10.velocityTracker
            int r2 = r10.activePointerId
            float r9 = r0.getYVelocity(r2)
            int r0 = r10.getScrollRangeForDragFling(r12)
            int r7 = -r0
            r8 = 0
            r4 = r10
            r5 = r11
            r6 = r12
            r4.fling(r5, r6, r7, r8, r9)
        L79:
            r10.isBeingDragged = r3
            r10.activePointerId = r1
            android.view.VelocityTracker r11 = r10.velocityTracker
            if (r11 == 0) goto L87
            r11.recycle()
            r11 = 0
            r10.velocityTracker = r11
        L87:
            android.view.VelocityTracker r11 = r10.velocityTracker
            if (r11 == 0) goto L8e
            r11.addMovement(r13)
        L8e:
            boolean r10 = r10.isBeingDragged
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.appbar.HeaderBehavior.onTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    public final int scroll(CoordinatorLayout coordinatorLayout, V v4, int i5, int i7, int i9) {
        return setHeaderTopBottomOffset(coordinatorLayout, v4, getTopBottomOffsetForScrollingSibling() - i5, i7, i9);
    }

    public void seslHasNoSnapFlag(boolean z9) {
        this.mHasNoSnapFlag = z9;
    }

    public int setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout, V v4, int i5) {
        return setHeaderTopBottomOffset(coordinatorLayout, v4, i5, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public int setHeaderTopBottomOffset(CoordinatorLayout coordinatorLayout, V v4, int i5, int i7, int i9) {
        int iD;
        int topAndBottomOffset = getTopAndBottomOffset();
        if (i7 == 0 || topAndBottomOffset < i7 || topAndBottomOffset > i9 || topAndBottomOffset == (iD = com.bumptech.glide.c.d(i5, i7, i9))) {
            return 0;
        }
        setTopAndBottomOffset(iD);
        return topAndBottomOffset - iD;
    }

    public HeaderBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.activePointerId = -1;
        this.touchSlop = -1;
    }
}
