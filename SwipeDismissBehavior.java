package com.google.android.material.behavior;

import L.o;
import L.w;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.coordinatorlayout.widget.d;
import androidx.core.view.W;
import androidx.customview.widget.f;
import androidx.customview.widget.g;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SwipeDismissBehavior<V extends View> extends d {
    private static final float DEFAULT_ALPHA_END_DISTANCE = 0.5f;
    private static final float DEFAULT_ALPHA_START_DISTANCE = 0.0f;
    private static final float DEFAULT_DRAG_DISMISS_THRESHOLD = 0.5f;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_SETTLING = 2;
    public static final int SWIPE_DIRECTION_ANY = 2;
    public static final int SWIPE_DIRECTION_END_TO_START = 1;
    public static final int SWIPE_DIRECTION_START_TO_END = 0;
    private boolean interceptingEvents;
    OnDismissListener listener;
    private boolean requestingDisallowInterceptTouchEvent;
    private boolean sensitivitySet;
    g viewDragHelper;
    private float sensitivity = 0.0f;
    int swipeDirection = 2;
    float dragDismissThreshold = 0.5f;
    float alphaStartSwipeDistance = 0.0f;
    float alphaEndSwipeDistance = 0.5f;
    private final f dragCallback = new f() { // from class: com.google.android.material.behavior.SwipeDismissBehavior.1
        private static final int INVALID_POINTER_ID = -1;
        private int activePointerId = -1;
        private int originalCapturedViewLeft;

        private boolean shouldDismiss(View view, float f2) {
            if (f2 == 0.0f) {
                return Math.abs(view.getLeft() - this.originalCapturedViewLeft) >= Math.round(((float) view.getWidth()) * SwipeDismissBehavior.this.dragDismissThreshold);
            }
            WeakHashMap weakHashMap = W.f7199a;
            boolean z9 = view.getLayoutDirection() == 1;
            int i5 = SwipeDismissBehavior.this.swipeDirection;
            if (i5 == 2) {
                return true;
            }
            if (i5 == 0) {
                if (z9) {
                    if (f2 >= 0.0f) {
                        return false;
                    }
                } else if (f2 <= 0.0f) {
                    return false;
                }
                return true;
            }
            if (i5 != 1) {
                return false;
            }
            if (z9) {
                if (f2 <= 0.0f) {
                    return false;
                }
            } else if (f2 >= 0.0f) {
                return false;
            }
            return true;
        }

        @Override // androidx.customview.widget.f
        public int clampViewPositionHorizontal(View view, int i5, int i7) {
            int width;
            int width2;
            int width3;
            WeakHashMap weakHashMap = W.f7199a;
            boolean z9 = view.getLayoutDirection() == 1;
            int i9 = SwipeDismissBehavior.this.swipeDirection;
            if (i9 == 0) {
                if (z9) {
                    width = this.originalCapturedViewLeft - view.getWidth();
                    width2 = this.originalCapturedViewLeft;
                } else {
                    width = this.originalCapturedViewLeft;
                    width3 = view.getWidth();
                    width2 = width3 + width;
                }
            } else if (i9 != 1) {
                width = this.originalCapturedViewLeft - view.getWidth();
                width2 = this.originalCapturedViewLeft + view.getWidth();
            } else if (z9) {
                width = this.originalCapturedViewLeft;
                width3 = view.getWidth();
                width2 = width3 + width;
            } else {
                width = this.originalCapturedViewLeft - view.getWidth();
                width2 = this.originalCapturedViewLeft;
            }
            return SwipeDismissBehavior.clamp(width, i5, width2);
        }

        @Override // androidx.customview.widget.f
        public int clampViewPositionVertical(View view, int i5, int i7) {
            return view.getTop();
        }

        @Override // androidx.customview.widget.f
        public int getViewHorizontalDragRange(View view) {
            return view.getWidth();
        }

        @Override // androidx.customview.widget.f
        public void onViewCaptured(View view, int i5) {
            this.activePointerId = i5;
            this.originalCapturedViewLeft = view.getLeft();
            ViewParent parent = view.getParent();
            if (parent != null) {
                SwipeDismissBehavior.this.requestingDisallowInterceptTouchEvent = true;
                parent.requestDisallowInterceptTouchEvent(true);
                SwipeDismissBehavior.this.requestingDisallowInterceptTouchEvent = false;
            }
        }

        @Override // androidx.customview.widget.f
        public void onViewDragStateChanged(int i5) {
            OnDismissListener onDismissListener = SwipeDismissBehavior.this.listener;
            if (onDismissListener != null) {
                onDismissListener.onDragStateChanged(i5);
            }
        }

        @Override // androidx.customview.widget.f
        public void onViewPositionChanged(View view, int i5, int i7, int i9, int i10) {
            float width = view.getWidth() * SwipeDismissBehavior.this.alphaStartSwipeDistance;
            float width2 = view.getWidth() * SwipeDismissBehavior.this.alphaEndSwipeDistance;
            float fAbs = Math.abs(i5 - this.originalCapturedViewLeft);
            if (fAbs <= width) {
                view.setAlpha(1.0f);
            } else if (fAbs >= width2) {
                view.setAlpha(0.0f);
            } else {
                view.setAlpha(SwipeDismissBehavior.clamp(0.0f, 1.0f - SwipeDismissBehavior.fraction(width, width2, fAbs), 1.0f));
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x001d  */
        @Override // androidx.customview.widget.f
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void onViewReleased(android.view.View r3, float r4, float r5) {
            /*
                r2 = this;
                r5 = -1
                r2.activePointerId = r5
                int r5 = r3.getWidth()
                boolean r0 = r2.shouldDismiss(r3, r4)
                if (r0 == 0) goto L23
                r0 = 0
                int r4 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
                if (r4 < 0) goto L1d
                int r4 = r3.getLeft()
                int r0 = r2.originalCapturedViewLeft
                if (r4 >= r0) goto L1b
                goto L1d
            L1b:
                int r0 = r0 + r5
                goto L21
            L1d:
                int r4 = r2.originalCapturedViewLeft
                int r0 = r4 - r5
            L21:
                r4 = 1
                goto L26
            L23:
                int r0 = r2.originalCapturedViewLeft
                r4 = 0
            L26:
                com.google.android.material.behavior.SwipeDismissBehavior r5 = com.google.android.material.behavior.SwipeDismissBehavior.this
                androidx.customview.widget.g r5 = r5.viewDragHelper
                int r1 = r3.getTop()
                boolean r5 = r5.s(r0, r1)
                if (r5 == 0) goto L41
                com.google.android.material.behavior.SwipeDismissBehavior$SettleRunnable r5 = new com.google.android.material.behavior.SwipeDismissBehavior$SettleRunnable
                com.google.android.material.behavior.SwipeDismissBehavior r2 = com.google.android.material.behavior.SwipeDismissBehavior.this
                r5.<init>(r3, r4)
                java.util.WeakHashMap r2 = androidx.core.view.W.f7199a
                r3.postOnAnimation(r5)
                goto L4c
            L41:
                if (r4 == 0) goto L4c
                com.google.android.material.behavior.SwipeDismissBehavior r2 = com.google.android.material.behavior.SwipeDismissBehavior.this
                com.google.android.material.behavior.SwipeDismissBehavior$OnDismissListener r2 = r2.listener
                if (r2 == 0) goto L4c
                r2.onDismiss(r3)
            L4c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.behavior.SwipeDismissBehavior.AnonymousClass1.onViewReleased(android.view.View, float, float):void");
        }

        @Override // androidx.customview.widget.f
        public boolean tryCaptureView(View view, int i5) {
            int i7 = this.activePointerId;
            return (i7 == -1 || i7 == i5) && SwipeDismissBehavior.this.canSwipeDismissView(view);
        }
    };

    public interface OnDismissListener {
        void onDismiss(View view);

        void onDragStateChanged(int i5);
    }

    public class SettleRunnable implements Runnable {
        private final boolean dismiss;
        private final View view;

        public SettleRunnable(View view, boolean z9) {
            this.view = view;
            this.dismiss = z9;
        }

        @Override // java.lang.Runnable
        public void run() {
            OnDismissListener onDismissListener;
            g gVar = SwipeDismissBehavior.this.viewDragHelper;
            if (gVar != null && gVar.h()) {
                View view = this.view;
                WeakHashMap weakHashMap = W.f7199a;
                view.postOnAnimation(this);
            } else {
                if (!this.dismiss || (onDismissListener = SwipeDismissBehavior.this.listener) == null) {
                    return;
                }
                onDismissListener.onDismiss(this.view);
            }
        }
    }

    public static float clamp(float f2, float f7, float f9) {
        return Math.min(Math.max(f2, f7), f9);
    }

    private void ensureViewDragHelper(ViewGroup viewGroup) {
        g gVar;
        if (this.viewDragHelper == null) {
            if (this.sensitivitySet) {
                float f2 = this.sensitivity;
                gVar = new g(viewGroup.getContext(), viewGroup, this.dragCallback);
                gVar.f7332b = (int) ((1.0f / f2) * gVar.f7332b);
            } else {
                gVar = new g(viewGroup.getContext(), viewGroup, this.dragCallback);
            }
            this.viewDragHelper = gVar;
        }
    }

    public static float fraction(float f2, float f7, float f9) {
        return (f9 - f2) / (f7 - f2);
    }

    private void updateAccessibilityActions(View view) {
        W.g(1048576, view);
        W.e(0, view);
        if (canSwipeDismissView(view)) {
            W.h(view, L.f.f1783j, null, new w() { // from class: com.google.android.material.behavior.SwipeDismissBehavior.2
                @Override // L.w
                public boolean perform(View view2, o oVar) {
                    if (!SwipeDismissBehavior.this.canSwipeDismissView(view2)) {
                        return false;
                    }
                    WeakHashMap weakHashMap = W.f7199a;
                    boolean z9 = view2.getLayoutDirection() == 1;
                    int i5 = SwipeDismissBehavior.this.swipeDirection;
                    view2.offsetLeftAndRight((!(i5 == 0 && z9) && (i5 != 1 || z9)) ? view2.getWidth() : -view2.getWidth());
                    view2.setAlpha(0.0f);
                    OnDismissListener onDismissListener = SwipeDismissBehavior.this.listener;
                    if (onDismissListener != null) {
                        onDismissListener.onDismiss(view2);
                    }
                    return true;
                }
            });
        }
    }

    public boolean canSwipeDismissView(View view) {
        return true;
    }

    public int getDragState() {
        g gVar = this.viewDragHelper;
        if (gVar != null) {
            return gVar.f7331a;
        }
        return 0;
    }

    public OnDismissListener getListener() {
        return this.listener;
    }

    @Override // androidx.coordinatorlayout.widget.d
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v4, MotionEvent motionEvent) {
        boolean zIsPointInChildBounds = this.interceptingEvents;
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            zIsPointInChildBounds = coordinatorLayout.isPointInChildBounds(v4, (int) motionEvent.getX(), (int) motionEvent.getY());
            this.interceptingEvents = zIsPointInChildBounds;
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.interceptingEvents = false;
        }
        if (!zIsPointInChildBounds) {
            return false;
        }
        ensureViewDragHelper(coordinatorLayout);
        return !this.requestingDisallowInterceptTouchEvent && this.viewDragHelper.t(motionEvent);
    }

    @Override // androidx.coordinatorlayout.widget.d
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v4, int i5) {
        boolean zOnLayoutChild = super.onLayoutChild(coordinatorLayout, v4, i5);
        WeakHashMap weakHashMap = W.f7199a;
        if (v4.getImportantForAccessibility() == 0) {
            v4.setImportantForAccessibility(1);
            updateAccessibilityActions(v4);
        }
        return zOnLayoutChild;
    }

    @Override // androidx.coordinatorlayout.widget.d
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v4, MotionEvent motionEvent) {
        if (this.viewDragHelper == null) {
            return false;
        }
        if (this.requestingDisallowInterceptTouchEvent && motionEvent.getActionMasked() == 3) {
            return true;
        }
        this.viewDragHelper.m(motionEvent);
        return true;
    }

    public void setDragDismissDistance(float f2) {
        this.dragDismissThreshold = clamp(0.0f, f2, 1.0f);
    }

    public void setEndAlphaSwipeDistance(float f2) {
        this.alphaEndSwipeDistance = clamp(0.0f, f2, 1.0f);
    }

    public void setListener(OnDismissListener onDismissListener) {
        this.listener = onDismissListener;
    }

    public void setSensitivity(float f2) {
        this.sensitivity = f2;
        this.sensitivitySet = true;
    }

    public void setStartAlphaSwipeDistance(float f2) {
        this.alphaStartSwipeDistance = clamp(0.0f, f2, 1.0f);
    }

    public void setSwipeDirection(int i5) {
        this.swipeDirection = i5;
    }

    public static int clamp(int i5, int i7, int i9) {
        return Math.min(Math.max(i5, i7), i9);
    }
}
