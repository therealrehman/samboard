package com.google.android.material.bottomsheet;

import A8.l;
import L.o;
import L.w;
import R.c;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.RoundedCorner;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.activity.b;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.coordinatorlayout.widget.d;
import androidx.core.view.C0209a;
import androidx.core.view.C0210b;
import androidx.core.view.M;
import androidx.core.view.T;
import androidx.core.view.W;
import androidx.core.view.j0;
import androidx.customview.widget.f;
import androidx.customview.widget.g;
import com.google.android.material.R;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MaterialBackHandler;
import com.google.android.material.motion.MaterialBottomContainerBackHelper;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import d6.AbstractC0476d;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class BottomSheetBehavior<V extends View> extends d implements MaterialBackHandler {
    private static final int CORNER_ANIMATION_DURATION = 500;
    static final int DEFAULT_SIGNIFICANT_VEL_THRESHOLD = 500;
    private static final int DEF_STYLE_RES = R.style.Widget_Design_BottomSheet_Modal;
    private static final float HIDE_FRICTION = 0.1f;
    private static final float HIDE_THRESHOLD = 0.5f;
    private static final int INVALID_POSITION = -1;
    private static final int NO_MAX_SIZE = -1;
    public static final int PEEK_HEIGHT_AUTO = -1;
    public static final int SAVE_ALL = -1;
    public static final int SAVE_FIT_TO_CONTENTS = 2;
    public static final int SAVE_HIDEABLE = 4;
    public static final int SAVE_NONE = 0;
    public static final int SAVE_PEEK_HEIGHT = 1;
    public static final int SAVE_SKIP_COLLAPSED = 8;
    public static final int STATE_COLLAPSED = 4;
    public static final int STATE_DRAGGING = 1;
    public static final int STATE_EXPANDED = 3;
    public static final int STATE_HALF_EXPANDED = 6;
    public static final int STATE_HIDDEN = 5;
    public static final int STATE_SETTLING = 2;
    private static final String TAG = "BottomSheetBehavior";
    static final int VIEW_INDEX_ACCESSIBILITY_DELEGATE_VIEW = 1;
    private static final int VIEW_INDEX_BOTTOM_SHEET = 0;
    WeakReference<View> accessibilityDelegateViewRef;
    int activePointerId;
    private ColorStateList backgroundTint;
    MaterialBottomContainerBackHelper bottomContainerBackHelper;
    private final ArrayList<BottomSheetCallback> callbacks;
    private int childHeight;
    int collapsedOffset;
    private final f dragCallback;
    private boolean draggable;
    float elevation;
    final SparseIntArray expandHalfwayActionIds;
    private boolean expandedCornersRemoved;
    int expandedOffset;
    private boolean fitToContents;
    int fitToContentsOffset;
    private int gestureInsetBottom;
    private boolean gestureInsetBottomIgnored;
    int halfExpandedOffset;
    float halfExpandedRatio;
    private float hideFriction;
    boolean hideable;
    private boolean ignoreEvents;
    private Map<View, Integer> importantForAccessibilityMap;
    private int initialY;
    private int insetBottom;
    private int insetTop;
    private ValueAnimator interpolatorAnimator;
    private int lastNestedScrollDy;
    int lastStableState;
    private boolean marginLeftSystemWindowInsets;
    private boolean marginRightSystemWindowInsets;
    private boolean marginTopSystemWindowInsets;
    private MaterialShapeDrawable materialShapeDrawable;
    private int maxHeight;
    private int maxWidth;
    private float maximumVelocity;
    private boolean nestedScrolled;
    WeakReference<View> nestedScrollingChildRef;
    private boolean paddingBottomSystemWindowInsets;
    private boolean paddingLeftSystemWindowInsets;
    private boolean paddingRightSystemWindowInsets;
    private boolean paddingTopSystemWindowInsets;
    int parentHeight;
    int parentWidth;
    private int peekHeight;
    private boolean peekHeightAuto;
    private int peekHeightGestureInsetBuffer;
    private int peekHeightMin;
    private int saveFlags;
    private ShapeAppearanceModel shapeAppearanceModelDefault;
    private boolean shouldRemoveExpandedCorners;
    private int significantVelocityThreshold;
    private boolean skipCollapsed;
    int state;
    private final BottomSheetBehavior<V>.StateSettlingTracker stateSettlingTracker;
    boolean touchingScrollingChild;
    private boolean updateImportantForAccessibilityOnSiblings;
    private VelocityTracker velocityTracker;
    g viewDragHelper;
    WeakReference<V> viewRef;

    public static abstract class BottomSheetCallback {
        public void onLayout(View view) {
        }

        public abstract void onSlide(View view, float f2);

        public abstract void onStateChanged(View view, int i5);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SaveFlags {
    }

    public static class SavedState extends c {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.SavedState.1
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i5) {
                return new SavedState[i5];
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.ClassLoaderCreator
            public SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }
        };
        boolean fitToContents;
        boolean hideable;
        int peekHeight;
        boolean skipCollapsed;
        final int state;

        public SavedState(Parcel parcel) {
            this(parcel, (ClassLoader) null);
        }

        @Override // R.c, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i5) {
            super.writeToParcel(parcel, i5);
            parcel.writeInt(this.state);
            parcel.writeInt(this.peekHeight);
            parcel.writeInt(this.fitToContents ? 1 : 0);
            parcel.writeInt(this.hideable ? 1 : 0);
            parcel.writeInt(this.skipCollapsed ? 1 : 0);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.state = parcel.readInt();
            this.peekHeight = parcel.readInt();
            this.fitToContents = parcel.readInt() == 1;
            this.hideable = parcel.readInt() == 1;
            this.skipCollapsed = parcel.readInt() == 1;
        }

        public SavedState(Parcelable parcelable, BottomSheetBehavior<?> bottomSheetBehavior) {
            super(parcelable);
            this.state = bottomSheetBehavior.state;
            this.peekHeight = ((BottomSheetBehavior) bottomSheetBehavior).peekHeight;
            this.fitToContents = ((BottomSheetBehavior) bottomSheetBehavior).fitToContents;
            this.hideable = bottomSheetBehavior.hideable;
            this.skipCollapsed = ((BottomSheetBehavior) bottomSheetBehavior).skipCollapsed;
        }

        @Deprecated
        public SavedState(Parcelable parcelable, int i5) {
            super(parcelable);
            this.state = i5;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface StableState {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

    public class StateSettlingTracker {
        private final Runnable continueSettlingRunnable;
        private boolean isContinueSettlingRunnablePosted;
        private int targetState;

        private StateSettlingTracker() {
            this.continueSettlingRunnable = new Runnable() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.StateSettlingTracker.1
                @Override // java.lang.Runnable
                public void run() {
                    StateSettlingTracker.this.isContinueSettlingRunnablePosted = false;
                    g gVar = BottomSheetBehavior.this.viewDragHelper;
                    if (gVar != null && gVar.h()) {
                        StateSettlingTracker stateSettlingTracker = StateSettlingTracker.this;
                        stateSettlingTracker.continueSettlingToState(stateSettlingTracker.targetState);
                        return;
                    }
                    StateSettlingTracker stateSettlingTracker2 = StateSettlingTracker.this;
                    BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                    if (bottomSheetBehavior.state == 2) {
                        bottomSheetBehavior.setStateInternal(stateSettlingTracker2.targetState);
                    }
                }
            };
        }

        public void continueSettlingToState(int i5) {
            WeakReference<V> weakReference = BottomSheetBehavior.this.viewRef;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            this.targetState = i5;
            if (this.isContinueSettlingRunnablePosted) {
                return;
            }
            V v4 = BottomSheetBehavior.this.viewRef.get();
            Runnable runnable = this.continueSettlingRunnable;
            WeakHashMap weakHashMap = W.f7199a;
            v4.postOnAnimation(runnable);
            this.isContinueSettlingRunnablePosted = true;
        }
    }

    public BottomSheetBehavior() {
        this.saveFlags = 0;
        this.fitToContents = true;
        this.updateImportantForAccessibilityOnSiblings = false;
        this.maxWidth = -1;
        this.maxHeight = -1;
        this.stateSettlingTracker = new StateSettlingTracker();
        this.halfExpandedRatio = 0.5f;
        this.elevation = -1.0f;
        this.draggable = true;
        this.state = 4;
        this.lastStableState = 4;
        this.hideFriction = 0.1f;
        this.callbacks = new ArrayList<>();
        this.initialY = -1;
        this.expandHalfwayActionIds = new SparseIntArray();
        this.dragCallback = new f() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.5
            private long viewCapturedMillis;

            private boolean releasedLow(View view) {
                int top = view.getTop();
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                return top > (bottomSheetBehavior.getExpandedOffset() + bottomSheetBehavior.parentHeight) / 2;
            }

            @Override // androidx.customview.widget.f
            public int clampViewPositionHorizontal(View view, int i5, int i7) {
                return view.getLeft();
            }

            @Override // androidx.customview.widget.f
            public int clampViewPositionVertical(View view, int i5, int i7) {
                return com.bumptech.glide.c.d(i5, BottomSheetBehavior.this.getExpandedOffset(), getViewVerticalDragRange(view));
            }

            @Override // androidx.customview.widget.f
            public int getViewVerticalDragRange(View view) {
                return BottomSheetBehavior.this.canBeHiddenByDragging() ? BottomSheetBehavior.this.parentHeight : BottomSheetBehavior.this.collapsedOffset;
            }

            @Override // androidx.customview.widget.f
            public void onViewDragStateChanged(int i5) {
                if (i5 == 1 && BottomSheetBehavior.this.draggable) {
                    BottomSheetBehavior.this.setStateInternal(1);
                }
            }

            @Override // androidx.customview.widget.f
            public void onViewPositionChanged(View view, int i5, int i7, int i9, int i10) {
                BottomSheetBehavior.this.dispatchOnSlide(i7);
            }

            /* JADX WARN: Removed duplicated region for block: B:39:0x00ad  */
            /* JADX WARN: Removed duplicated region for block: B:6:0x0010  */
            @Override // androidx.customview.widget.f
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onViewReleased(android.view.View r8, float r9, float r10) {
                /*
                    Method dump skipped, instruction units count: 308
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.AnonymousClass5.onViewReleased(android.view.View, float, float):void");
            }

            @Override // androidx.customview.widget.f
            public boolean tryCaptureView(View view, int i5) {
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                int i7 = bottomSheetBehavior.state;
                if (i7 == 1 || bottomSheetBehavior.touchingScrollingChild) {
                    return false;
                }
                if (i7 == 3 && bottomSheetBehavior.activePointerId == i5) {
                    WeakReference<View> weakReference = bottomSheetBehavior.nestedScrollingChildRef;
                    View view2 = weakReference != null ? weakReference.get() : null;
                    if (view2 != null && view2.canScrollVertically(-1)) {
                        return false;
                    }
                }
                this.viewCapturedMillis = System.currentTimeMillis();
                WeakReference<V> weakReference2 = BottomSheetBehavior.this.viewRef;
                return weakReference2 != null && weakReference2.get() == view;
            }
        };
    }

    private int addAccessibilityActionForState(View view, int i5, int i7) {
        int iA;
        String string = view.getResources().getString(i5);
        w wVarCreateAccessibilityViewCommandForState = createAccessibilityViewCommandForState(i7);
        ArrayList arrayListD = W.d(view);
        int i9 = 0;
        while (true) {
            if (i9 >= arrayListD.size()) {
                int i10 = -1;
                for (int i11 = 0; i11 < 32 && i10 == -1; i11++) {
                    int i12 = W.f7200b[i11];
                    boolean z9 = true;
                    for (int i13 = 0; i13 < arrayListD.size(); i13++) {
                        z9 &= ((L.f) arrayListD.get(i13)).a() != i12;
                    }
                    if (z9) {
                        i10 = i12;
                    }
                }
                iA = i10;
            } else {
                if (TextUtils.equals(string, ((AccessibilityNodeInfo.AccessibilityAction) ((L.f) arrayListD.get(i9)).f1787a).getLabel())) {
                    iA = ((L.f) arrayListD.get(i9)).a();
                    break;
                }
                i9++;
            }
        }
        if (iA != -1) {
            L.f fVar = new L.f(null, iA, string, wVarCreateAccessibilityViewCommandForState, null);
            View.AccessibilityDelegate accessibilityDelegateA = T.a(view);
            C0210b c0210b = accessibilityDelegateA == null ? null : accessibilityDelegateA instanceof C0209a ? ((C0209a) accessibilityDelegateA).f7202a : new C0210b(accessibilityDelegateA);
            if (c0210b == null) {
                c0210b = new C0210b();
            }
            W.i(view, c0210b);
            W.g(fVar.a(), view);
            W.d(view).add(fVar);
            W.e(0, view);
        }
        return iA;
    }

    private void calculateCollapsedOffset() {
        int iCalculatePeekHeight = calculatePeekHeight();
        if (this.fitToContents) {
            this.collapsedOffset = Math.max(this.parentHeight - iCalculatePeekHeight, this.fitToContentsOffset);
        } else {
            this.collapsedOffset = this.parentHeight - iCalculatePeekHeight;
        }
    }

    private float calculateCornerInterpolation(float f2, RoundedCorner roundedCorner) {
        if (roundedCorner != null) {
            float radius = roundedCorner.getRadius();
            if (radius > 0.0f && f2 > 0.0f) {
                return radius / f2;
            }
        }
        return 0.0f;
    }

    private void calculateHalfExpandedOffset() {
        this.halfExpandedOffset = (int) ((1.0f - this.halfExpandedRatio) * this.parentHeight);
    }

    private float calculateInterpolationWithCornersRemoved() {
        WeakReference<V> weakReference;
        WindowInsets rootWindowInsets;
        if (this.materialShapeDrawable == null || (weakReference = this.viewRef) == null || weakReference.get() == null) {
            return 0.0f;
        }
        V v4 = this.viewRef.get();
        if (!isAtTopOfScreen() || (rootWindowInsets = v4.getRootWindowInsets()) == null) {
            return 0.0f;
        }
        return Math.max(calculateCornerInterpolation(this.materialShapeDrawable.getTopLeftCornerResolvedSize(), rootWindowInsets.getRoundedCorner(0)), calculateCornerInterpolation(this.materialShapeDrawable.getTopRightCornerResolvedSize(), rootWindowInsets.getRoundedCorner(1)));
    }

    private int calculatePeekHeight() {
        int iMin;
        int i5;
        int i7;
        if (this.peekHeightAuto) {
            iMin = Math.min(Math.max(this.peekHeightMin, this.parentHeight - ((this.parentWidth * 9) / 16)), this.childHeight);
            i5 = this.insetBottom;
        } else {
            if (!this.gestureInsetBottomIgnored && !this.paddingBottomSystemWindowInsets && (i7 = this.gestureInsetBottom) > 0) {
                return Math.max(this.peekHeight, i7 + this.peekHeightGestureInsetBuffer);
            }
            iMin = this.peekHeight;
            i5 = this.insetBottom;
        }
        return iMin + i5;
    }

    private float calculateSlideOffsetWithTop(int i5) {
        float f2;
        float expandedOffset;
        int i7 = this.collapsedOffset;
        if (i5 > i7 || i7 == getExpandedOffset()) {
            int i9 = this.collapsedOffset;
            f2 = i9 - i5;
            expandedOffset = this.parentHeight - i9;
        } else {
            int i10 = this.collapsedOffset;
            f2 = i10 - i5;
            expandedOffset = i10 - getExpandedOffset();
        }
        return f2 / expandedOffset;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean canBeHiddenByDragging() {
        return isHideable() && isHideableWhenDragging();
    }

    private void clearAccessibilityAction(View view, int i5) {
        if (view == null) {
            return;
        }
        W.g(524288, view);
        W.e(0, view);
        W.g(262144, view);
        W.e(0, view);
        W.g(1048576, view);
        W.e(0, view);
        int i7 = this.expandHalfwayActionIds.get(i5, -1);
        if (i7 != -1) {
            W.g(i7, view);
            W.e(0, view);
            this.expandHalfwayActionIds.delete(i5);
        }
    }

    private w createAccessibilityViewCommandForState(final int i5) {
        return new w() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.6
            @Override // L.w
            public boolean perform(View view, o oVar) {
                BottomSheetBehavior.this.setState(i5);
                return true;
            }
        };
    }

    private void createMaterialShapeDrawableIfNeeded(Context context) {
        if (this.shapeAppearanceModelDefault == null) {
            return;
        }
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this.shapeAppearanceModelDefault);
        this.materialShapeDrawable = materialShapeDrawable;
        materialShapeDrawable.initializeElevationOverlay(context);
        ColorStateList colorStateList = this.backgroundTint;
        if (colorStateList != null) {
            this.materialShapeDrawable.setFillColor(colorStateList);
            return;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.colorBackground, typedValue, true);
        this.materialShapeDrawable.setTint(typedValue.data);
    }

    private void createShapeValueAnimator() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(calculateInterpolationWithCornersRemoved(), 1.0f);
        this.interpolatorAnimator = valueAnimatorOfFloat;
        valueAnimatorOfFloat.setDuration(500L);
        this.interpolatorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                if (BottomSheetBehavior.this.materialShapeDrawable != null) {
                    BottomSheetBehavior.this.materialShapeDrawable.setInterpolation(fFloatValue);
                }
            }
        });
    }

    public static <V extends View> BottomSheetBehavior<V> from(V v4) {
        ViewGroup.LayoutParams layoutParams = v4.getLayoutParams();
        if (!(layoutParams instanceof androidx.coordinatorlayout.widget.g)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        d dVar = ((androidx.coordinatorlayout.widget.g) layoutParams).f7154a;
        if (dVar instanceof BottomSheetBehavior) {
            return (BottomSheetBehavior) dVar;
        }
        throw new IllegalArgumentException("The view is not associated with BottomSheetBehavior");
    }

    private int getChildMeasureSpec(int i5, int i7, int i9, int i10) {
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i5, i7, i10);
        if (i9 == -1) {
            return childMeasureSpec;
        }
        int mode = View.MeasureSpec.getMode(childMeasureSpec);
        int size = View.MeasureSpec.getSize(childMeasureSpec);
        if (mode == 1073741824) {
            return View.MeasureSpec.makeMeasureSpec(Math.min(size, i9), 1073741824);
        }
        if (size != 0) {
            i9 = Math.min(size, i9);
        }
        return View.MeasureSpec.makeMeasureSpec(i9, Integer.MIN_VALUE);
    }

    private int getTopOffsetForState(int i5) {
        if (i5 == 3) {
            return getExpandedOffset();
        }
        if (i5 == 4) {
            return this.collapsedOffset;
        }
        if (i5 == 5) {
            return this.parentHeight;
        }
        if (i5 == 6) {
            return this.halfExpandedOffset;
        }
        throw new IllegalArgumentException(l.o(i5, "Invalid state to get top offset: "));
    }

    private float getYVelocity() {
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker == null) {
            return 0.0f;
        }
        velocityTracker.computeCurrentVelocity(1000, this.maximumVelocity);
        return this.velocityTracker.getYVelocity(this.activePointerId);
    }

    private boolean isAtTopOfScreen() {
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference == null || weakReference.get() == null) {
            return false;
        }
        int[] iArr = new int[2];
        this.viewRef.get().getLocationOnScreen(iArr);
        return iArr[1] == 0;
    }

    private boolean isExpandedAndShouldRemoveCorners() {
        return this.state == 3 && (this.shouldRemoveExpandedCorners || isAtTopOfScreen());
    }

    private boolean isLayouting(V v4) {
        ViewParent parent = v4.getParent();
        if (parent != null && parent.isLayoutRequested()) {
            WeakHashMap weakHashMap = W.f7199a;
            if (v4.isAttachedToWindow()) {
                return true;
            }
        }
        return false;
    }

    private void replaceAccessibilityActionForState(View view, L.f fVar, int i5) {
        W.h(view, fVar, null, createAccessibilityViewCommandForState(i5));
    }

    private void reset() {
        this.activePointerId = -1;
        this.initialY = -1;
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.velocityTracker = null;
        }
    }

    private void restoreOptionalState(SavedState savedState) {
        int i5 = this.saveFlags;
        if (i5 == 0) {
            return;
        }
        if (i5 == -1 || (i5 & 1) == 1) {
            this.peekHeight = savedState.peekHeight;
        }
        if (i5 == -1 || (i5 & 2) == 2) {
            this.fitToContents = savedState.fitToContents;
        }
        if (i5 == -1 || (i5 & 4) == 4) {
            this.hideable = savedState.hideable;
        }
        if (i5 == -1 || (i5 & 8) == 8) {
            this.skipCollapsed = savedState.skipCollapsed;
        }
    }

    private void runAfterLayout(V v4, Runnable runnable) {
        if (isLayouting(v4)) {
            v4.post(runnable);
        } else {
            runnable.run();
        }
    }

    private void setWindowInsetsListener(View view) {
        final boolean z9 = (isGestureInsetBottomIgnored() || this.peekHeightAuto) ? false : true;
        if (this.paddingBottomSystemWindowInsets || this.paddingLeftSystemWindowInsets || this.paddingRightSystemWindowInsets || this.marginLeftSystemWindowInsets || this.marginRightSystemWindowInsets || this.marginTopSystemWindowInsets || z9) {
            ViewUtils.doOnApplyWindowInsets(view, new ViewUtils.OnApplyWindowInsetsListener() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.4
                /* JADX WARN: Removed duplicated region for block: B:33:0x009e  */
                @Override // com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public androidx.core.view.w0 onApplyWindowInsets(android.view.View r11, androidx.core.view.w0 r12, com.google.android.material.internal.ViewUtils.RelativePadding r13) {
                    /*
                        r10 = this;
                        androidx.core.view.u0 r0 = r12.f7266a
                        r1 = 7
                        D.f r0 = r0.f(r1)
                        androidx.core.view.u0 r1 = r12.f7266a
                        r2 = 32
                        D.f r1 = r1.f(r2)
                        com.google.android.material.bottomsheet.BottomSheetBehavior r2 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        int r3 = r0.f408b
                        com.google.android.material.bottomsheet.BottomSheetBehavior.access$302(r2, r3)
                        boolean r2 = com.google.android.material.internal.ViewUtils.isLayoutRtl(r11)
                        int r3 = r11.getPaddingBottom()
                        int r4 = r11.getPaddingLeft()
                        int r5 = r11.getPaddingRight()
                        com.google.android.material.bottomsheet.BottomSheetBehavior r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        boolean r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.access$400(r6)
                        if (r6 == 0) goto L40
                        com.google.android.material.bottomsheet.BottomSheetBehavior r3 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        int r6 = r12.a()
                        com.google.android.material.bottomsheet.BottomSheetBehavior.access$502(r3, r6)
                        int r3 = r13.bottom
                        com.google.android.material.bottomsheet.BottomSheetBehavior r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        int r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.access$500(r6)
                        int r3 = r3 + r6
                    L40:
                        com.google.android.material.bottomsheet.BottomSheetBehavior r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        boolean r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.access$600(r6)
                        int r7 = r0.f407a
                        if (r6 == 0) goto L52
                        if (r2 == 0) goto L4f
                        int r4 = r13.end
                        goto L51
                    L4f:
                        int r4 = r13.start
                    L51:
                        int r4 = r4 + r7
                    L52:
                        com.google.android.material.bottomsheet.BottomSheetBehavior r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        boolean r6 = com.google.android.material.bottomsheet.BottomSheetBehavior.access$700(r6)
                        int r8 = r0.f409c
                        if (r6 == 0) goto L65
                        if (r2 == 0) goto L61
                        int r13 = r13.start
                        goto L63
                    L61:
                        int r13 = r13.end
                    L63:
                        int r5 = r13 + r8
                    L65:
                        android.view.ViewGroup$LayoutParams r13 = r11.getLayoutParams()
                        android.view.ViewGroup$MarginLayoutParams r13 = (android.view.ViewGroup.MarginLayoutParams) r13
                        com.google.android.material.bottomsheet.BottomSheetBehavior r2 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        boolean r2 = com.google.android.material.bottomsheet.BottomSheetBehavior.access$800(r2)
                        r6 = 0
                        r9 = 1
                        if (r2 == 0) goto L7d
                        int r2 = r13.leftMargin
                        if (r2 == r7) goto L7d
                        r13.leftMargin = r7
                        r2 = r9
                        goto L7e
                    L7d:
                        r2 = r6
                    L7e:
                        com.google.android.material.bottomsheet.BottomSheetBehavior r7 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        boolean r7 = com.google.android.material.bottomsheet.BottomSheetBehavior.access$900(r7)
                        if (r7 == 0) goto L8d
                        int r7 = r13.rightMargin
                        if (r7 == r8) goto L8d
                        r13.rightMargin = r8
                        r2 = r9
                    L8d:
                        com.google.android.material.bottomsheet.BottomSheetBehavior r7 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        boolean r7 = com.google.android.material.bottomsheet.BottomSheetBehavior.access$1000(r7)
                        if (r7 == 0) goto L9e
                        int r7 = r13.topMargin
                        int r0 = r0.f408b
                        if (r7 == r0) goto L9e
                        r13.topMargin = r0
                        goto L9f
                    L9e:
                        r9 = r2
                    L9f:
                        if (r9 == 0) goto La4
                        r11.setLayoutParams(r13)
                    La4:
                        int r13 = r11.getPaddingTop()
                        r11.setPadding(r4, r13, r5, r3)
                        boolean r11 = r2
                        if (r11 == 0) goto Lb6
                        com.google.android.material.bottomsheet.BottomSheetBehavior r11 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        int r13 = r1.f410d
                        com.google.android.material.bottomsheet.BottomSheetBehavior.access$1102(r11, r13)
                    Lb6:
                        com.google.android.material.bottomsheet.BottomSheetBehavior r11 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        boolean r11 = com.google.android.material.bottomsheet.BottomSheetBehavior.access$400(r11)
                        if (r11 != 0) goto Lc2
                        boolean r11 = r2
                        if (r11 == 0) goto Lc7
                    Lc2:
                        com.google.android.material.bottomsheet.BottomSheetBehavior r10 = com.google.android.material.bottomsheet.BottomSheetBehavior.this
                        com.google.android.material.bottomsheet.BottomSheetBehavior.access$1200(r10, r6)
                    Lc7:
                        return r12
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.AnonymousClass4.onApplyWindowInsets(android.view.View, androidx.core.view.w0, com.google.android.material.internal.ViewUtils$RelativePadding):androidx.core.view.w0");
                }
            });
        }
    }

    private boolean shouldHandleDraggingWithHelper() {
        return this.viewDragHelper != null && (this.draggable || this.state == 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSettling(View view, int i5, boolean z9) {
        int topOffsetForState = getTopOffsetForState(i5);
        g gVar = this.viewDragHelper;
        if (gVar == null || (!z9 ? gVar.u(view, view.getLeft(), topOffsetForState) : gVar.s(view.getLeft(), topOffsetForState))) {
            setStateInternal(i5);
            return;
        }
        setStateInternal(2);
        updateDrawableForTargetState(i5, true);
        this.stateSettlingTracker.continueSettlingToState(i5);
    }

    private void updateAccessibilityActions() {
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference != null) {
            updateAccessibilityActions(weakReference.get(), 0);
        }
        WeakReference<View> weakReference2 = this.accessibilityDelegateViewRef;
        if (weakReference2 != null) {
            updateAccessibilityActions(weakReference2.get(), 1);
        }
    }

    private void updateDrawableForTargetState(int i5, boolean z9) {
        boolean zIsExpandedAndShouldRemoveCorners;
        ValueAnimator valueAnimator;
        if (i5 == 2 || this.expandedCornersRemoved == (zIsExpandedAndShouldRemoveCorners = isExpandedAndShouldRemoveCorners()) || this.materialShapeDrawable == null) {
            return;
        }
        this.expandedCornersRemoved = zIsExpandedAndShouldRemoveCorners;
        if (!z9 || (valueAnimator = this.interpolatorAnimator) == null) {
            ValueAnimator valueAnimator2 = this.interpolatorAnimator;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.interpolatorAnimator.cancel();
            }
            this.materialShapeDrawable.setInterpolation(this.expandedCornersRemoved ? calculateInterpolationWithCornersRemoved() : 1.0f);
            return;
        }
        if (valueAnimator.isRunning()) {
            this.interpolatorAnimator.reverse();
        } else {
            this.interpolatorAnimator.setFloatValues(this.materialShapeDrawable.getInterpolation(), zIsExpandedAndShouldRemoveCorners ? calculateInterpolationWithCornersRemoved() : 1.0f);
            this.interpolatorAnimator.start();
        }
    }

    private void updateImportantForAccessibility(boolean z9) {
        Map<View, Integer> map;
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference == null) {
            return;
        }
        ViewParent parent = weakReference.get().getParent();
        if (parent instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) parent;
            int childCount = coordinatorLayout.getChildCount();
            if (z9) {
                if (this.importantForAccessibilityMap != null) {
                    return;
                } else {
                    this.importantForAccessibilityMap = new HashMap(childCount);
                }
            }
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = coordinatorLayout.getChildAt(i5);
                if (childAt != this.viewRef.get()) {
                    if (z9) {
                        this.importantForAccessibilityMap.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                        if (this.updateImportantForAccessibilityOnSiblings) {
                            WeakHashMap weakHashMap = W.f7199a;
                            childAt.setImportantForAccessibility(4);
                        }
                    } else if (this.updateImportantForAccessibilityOnSiblings && (map = this.importantForAccessibilityMap) != null && map.containsKey(childAt)) {
                        int iIntValue = this.importantForAccessibilityMap.get(childAt).intValue();
                        WeakHashMap weakHashMap2 = W.f7199a;
                        childAt.setImportantForAccessibility(iIntValue);
                    }
                }
            }
            if (!z9) {
                this.importantForAccessibilityMap = null;
            } else if (this.updateImportantForAccessibilityOnSiblings) {
                this.viewRef.get().sendAccessibilityEvent(8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePeekHeight(boolean z9) {
        V v4;
        if (this.viewRef != null) {
            calculateCollapsedOffset();
            if (this.state != 4 || (v4 = this.viewRef.get()) == null) {
                return;
            }
            if (z9) {
                setState(4);
            } else {
                v4.requestLayout();
            }
        }
    }

    public void addBottomSheetCallback(BottomSheetCallback bottomSheetCallback) {
        if (this.callbacks.contains(bottomSheetCallback)) {
            return;
        }
        this.callbacks.add(bottomSheetCallback);
    }

    public float calculateSlideOffset() {
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference == null || weakReference.get() == null) {
            return -1.0f;
        }
        return calculateSlideOffsetWithTop(this.viewRef.get().getTop());
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void cancelBackProgress() {
        MaterialBottomContainerBackHelper materialBottomContainerBackHelper = this.bottomContainerBackHelper;
        if (materialBottomContainerBackHelper == null) {
            return;
        }
        materialBottomContainerBackHelper.cancelBackProgress();
    }

    public void disableShapeAnimations() {
        this.interpolatorAnimator = null;
    }

    public void dispatchOnSlide(int i5) {
        V v4 = this.viewRef.get();
        if (v4 == null || this.callbacks.isEmpty()) {
            return;
        }
        float fCalculateSlideOffsetWithTop = calculateSlideOffsetWithTop(i5);
        for (int i7 = 0; i7 < this.callbacks.size(); i7++) {
            this.callbacks.get(i7).onSlide(v4, fCalculateSlideOffsetWithTop);
        }
    }

    public View findScrollingChild(View view) {
        if (view.getVisibility() != 0) {
            return null;
        }
        WeakHashMap weakHashMap = W.f7199a;
        if (M.p(view)) {
            return view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                View viewFindScrollingChild = findScrollingChild(viewGroup.getChildAt(i5));
                if (viewFindScrollingChild != null) {
                    return viewFindScrollingChild;
                }
            }
        }
        return null;
    }

    public MaterialBottomContainerBackHelper getBackHelper() {
        return this.bottomContainerBackHelper;
    }

    public int getExpandedOffset() {
        if (this.fitToContents) {
            return this.fitToContentsOffset;
        }
        return Math.max(this.expandedOffset, this.paddingTopSystemWindowInsets ? 0 : this.insetTop);
    }

    public float getHalfExpandedRatio() {
        return this.halfExpandedRatio;
    }

    public float getHideFriction() {
        return this.hideFriction;
    }

    public int getLastStableState() {
        return this.lastStableState;
    }

    public MaterialShapeDrawable getMaterialShapeDrawable() {
        return this.materialShapeDrawable;
    }

    public int getMaxHeight() {
        return this.maxHeight;
    }

    public int getMaxWidth() {
        return this.maxWidth;
    }

    public int getPeekHeight() {
        if (this.peekHeightAuto) {
            return -1;
        }
        return this.peekHeight;
    }

    public int getPeekHeightMin() {
        return this.peekHeightMin;
    }

    public int getSaveFlags() {
        return this.saveFlags;
    }

    public int getSignificantVelocityThreshold() {
        return this.significantVelocityThreshold;
    }

    public boolean getSkipCollapsed() {
        return this.skipCollapsed;
    }

    public int getState() {
        return this.state;
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void handleBackInvoked() {
        MaterialBottomContainerBackHelper materialBottomContainerBackHelper = this.bottomContainerBackHelper;
        if (materialBottomContainerBackHelper == null) {
            return;
        }
        b bVarOnHandleBackInvoked = materialBottomContainerBackHelper.onHandleBackInvoked();
        if (bVarOnHandleBackInvoked == null || Build.VERSION.SDK_INT < 34) {
            setState(this.hideable ? 5 : 4);
        } else if (this.hideable) {
            this.bottomContainerBackHelper.finishBackProgressNotPersistent(bVarOnHandleBackInvoked, new AnimatorListenerAdapter() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    BottomSheetBehavior.this.setStateInternal(5);
                    WeakReference<V> weakReference = BottomSheetBehavior.this.viewRef;
                    if (weakReference == null || weakReference.get() == null) {
                        return;
                    }
                    BottomSheetBehavior.this.viewRef.get().requestLayout();
                }
            });
        } else {
            this.bottomContainerBackHelper.finishBackProgressPersistent(bVarOnHandleBackInvoked, null);
            setState(4);
        }
    }

    public boolean isDraggable() {
        return this.draggable;
    }

    public boolean isFitToContents() {
        return this.fitToContents;
    }

    public boolean isGestureInsetBottomIgnored() {
        return this.gestureInsetBottomIgnored;
    }

    public boolean isHideable() {
        return this.hideable;
    }

    public boolean isHideableWhenDragging() {
        return true;
    }

    public boolean isNestedScrollingCheckEnabled() {
        return true;
    }

    public boolean isShouldRemoveExpandedCorners() {
        return this.shouldRemoveExpandedCorners;
    }

    @Override // androidx.coordinatorlayout.widget.d
    public void onAttachedToLayoutParams(androidx.coordinatorlayout.widget.g gVar) {
        super.onAttachedToLayoutParams(gVar);
        this.viewRef = null;
        this.viewDragHelper = null;
        this.bottomContainerBackHelper = null;
    }

    @Override // androidx.coordinatorlayout.widget.d
    public void onDetachedFromLayoutParams() {
        super.onDetachedFromLayoutParams();
        this.viewRef = null;
        this.viewDragHelper = null;
        this.bottomContainerBackHelper = null;
    }

    @Override // androidx.coordinatorlayout.widget.d
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v4, MotionEvent motionEvent) {
        int i5;
        g gVar;
        if (!v4.isShown() || !this.draggable) {
            this.ignoreEvents = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            reset();
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (actionMasked == 0) {
            int x9 = (int) motionEvent.getX();
            this.initialY = (int) motionEvent.getY();
            if (this.state != 2) {
                WeakReference<View> weakReference = this.nestedScrollingChildRef;
                View view = weakReference != null ? weakReference.get() : null;
                if (view != null && coordinatorLayout.isPointInChildBounds(view, x9, this.initialY)) {
                    this.activePointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
                    this.touchingScrollingChild = true;
                }
            }
            this.ignoreEvents = this.activePointerId == -1 && !coordinatorLayout.isPointInChildBounds(v4, x9, this.initialY);
        } else if (actionMasked == 1 || actionMasked == 3) {
            this.touchingScrollingChild = false;
            this.activePointerId = -1;
            if (this.ignoreEvents) {
                this.ignoreEvents = false;
                return false;
            }
        }
        if (!this.ignoreEvents && (gVar = this.viewDragHelper) != null && gVar.t(motionEvent)) {
            return true;
        }
        WeakReference<View> weakReference2 = this.nestedScrollingChildRef;
        View view2 = weakReference2 != null ? weakReference2.get() : null;
        return (actionMasked != 2 || view2 == null || this.ignoreEvents || this.state == 1 || coordinatorLayout.isPointInChildBounds(view2, (int) motionEvent.getX(), (int) motionEvent.getY()) || this.viewDragHelper == null || (i5 = this.initialY) == -1 || Math.abs(((float) i5) - motionEvent.getY()) <= ((float) this.viewDragHelper.f7332b)) ? false : true;
    }

    @Override // androidx.coordinatorlayout.widget.d
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v4, int i5) {
        WeakHashMap weakHashMap = W.f7199a;
        if (coordinatorLayout.getFitsSystemWindows() && !v4.getFitsSystemWindows()) {
            v4.setFitsSystemWindows(true);
        }
        if (this.viewRef == null) {
            this.peekHeightMin = coordinatorLayout.getResources().getDimensionPixelSize(R.dimen.design_bottom_sheet_peek_height_min);
            setWindowInsetsListener(v4);
            v4.setWindowInsetsAnimationCallback(new j0(new InsetsAnimationCallback(v4)));
            this.viewRef = new WeakReference<>(v4);
            this.bottomContainerBackHelper = new MaterialBottomContainerBackHelper(v4);
            MaterialShapeDrawable materialShapeDrawable = this.materialShapeDrawable;
            if (materialShapeDrawable != null) {
                v4.setBackground(materialShapeDrawable);
                MaterialShapeDrawable materialShapeDrawable2 = this.materialShapeDrawable;
                float fI = this.elevation;
                if (fI == -1.0f) {
                    fI = M.i(v4);
                }
                materialShapeDrawable2.setElevation(fI);
            } else {
                ColorStateList colorStateList = this.backgroundTint;
                if (colorStateList != null) {
                    M.q(v4, colorStateList);
                }
            }
            updateAccessibilityActions();
            if (v4.getImportantForAccessibility() == 0) {
                v4.setImportantForAccessibility(1);
            }
        }
        if (this.viewDragHelper == null) {
            this.viewDragHelper = new g(coordinatorLayout.getContext(), coordinatorLayout, this.dragCallback);
        }
        int top = v4.getTop();
        coordinatorLayout.onLayoutChild(v4, i5);
        this.parentWidth = coordinatorLayout.getWidth();
        this.parentHeight = coordinatorLayout.getHeight();
        int height = v4.getHeight();
        this.childHeight = height;
        int iMin = this.parentHeight;
        int i7 = iMin - height;
        int i9 = this.insetTop;
        if (i7 < i9) {
            if (this.paddingTopSystemWindowInsets) {
                int i10 = this.maxHeight;
                if (i10 != -1) {
                    iMin = Math.min(iMin, i10);
                }
                this.childHeight = iMin;
            } else {
                int iMin2 = iMin - i9;
                int i11 = this.maxHeight;
                if (i11 != -1) {
                    iMin2 = Math.min(iMin2, i11);
                }
                this.childHeight = iMin2;
            }
        }
        this.fitToContentsOffset = Math.max(0, this.parentHeight - this.childHeight);
        calculateHalfExpandedOffset();
        calculateCollapsedOffset();
        int i12 = this.state;
        if (i12 == 3) {
            v4.offsetTopAndBottom(getExpandedOffset());
        } else if (i12 == 6) {
            v4.offsetTopAndBottom(this.halfExpandedOffset);
        } else if (this.hideable && i12 == 5) {
            v4.offsetTopAndBottom(this.parentHeight);
        } else if (i12 == 4) {
            v4.offsetTopAndBottom(this.collapsedOffset);
        } else if (i12 == 1 || i12 == 2) {
            v4.offsetTopAndBottom(top - v4.getTop());
        }
        updateDrawableForTargetState(this.state, false);
        this.nestedScrollingChildRef = new WeakReference<>(findScrollingChild(v4));
        for (int i13 = 0; i13 < this.callbacks.size(); i13++) {
            this.callbacks.get(i13).onLayout(v4);
        }
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.d
    public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, V v4, int i5, int i7, int i9, int i10) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) v4.getLayoutParams();
        v4.measure(getChildMeasureSpec(i5, coordinatorLayout.getPaddingRight() + coordinatorLayout.getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i7, this.maxWidth, marginLayoutParams.width), getChildMeasureSpec(i9, coordinatorLayout.getPaddingBottom() + coordinatorLayout.getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i10, this.maxHeight, marginLayoutParams.height));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.d
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, V v4, View view, float f2, float f7) {
        WeakReference<View> weakReference;
        if (isNestedScrollingCheckEnabled() && (weakReference = this.nestedScrollingChildRef) != null && view == weakReference.get()) {
            return this.state != 3 || super.onNestedPreFling(coordinatorLayout, v4, view, f2, f7);
        }
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.d
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, V v4, View view, int i5, int i7, int[] iArr, int i9) {
        if (i9 == 1) {
            return;
        }
        WeakReference<View> weakReference = this.nestedScrollingChildRef;
        View view2 = weakReference != null ? weakReference.get() : null;
        if (!isNestedScrollingCheckEnabled() || view == view2) {
            int top = v4.getTop();
            int i10 = top - i7;
            if (i7 > 0) {
                if (i10 < getExpandedOffset()) {
                    int expandedOffset = top - getExpandedOffset();
                    iArr[1] = expandedOffset;
                    int i11 = -expandedOffset;
                    WeakHashMap weakHashMap = W.f7199a;
                    v4.offsetTopAndBottom(i11);
                    setStateInternal(3);
                } else {
                    if (!this.draggable) {
                        return;
                    }
                    iArr[1] = i7;
                    WeakHashMap weakHashMap2 = W.f7199a;
                    v4.offsetTopAndBottom(-i7);
                    setStateInternal(1);
                }
            } else if (i7 < 0 && !view.canScrollVertically(-1)) {
                if (i10 > this.collapsedOffset && !canBeHiddenByDragging()) {
                    int i12 = top - this.collapsedOffset;
                    iArr[1] = i12;
                    int i13 = -i12;
                    WeakHashMap weakHashMap3 = W.f7199a;
                    v4.offsetTopAndBottom(i13);
                    setStateInternal(4);
                } else {
                    if (!this.draggable) {
                        return;
                    }
                    iArr[1] = i7;
                    WeakHashMap weakHashMap4 = W.f7199a;
                    v4.offsetTopAndBottom(-i7);
                    setStateInternal(1);
                }
            }
            dispatchOnSlide(v4.getTop());
            this.lastNestedScrollDy = i7;
            this.nestedScrolled = true;
        }
    }

    @Override // androidx.coordinatorlayout.widget.d
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, V v4, View view, int i5, int i7, int i9, int i10, int i11, int[] iArr) {
    }

    @Override // androidx.coordinatorlayout.widget.d
    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, V v4, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(coordinatorLayout, v4, savedState.getSuperState());
        restoreOptionalState(savedState);
        int i5 = savedState.state;
        if (i5 == 1 || i5 == 2) {
            this.state = 4;
            this.lastStableState = 4;
        } else {
            this.state = i5;
            this.lastStableState = i5;
        }
    }

    @Override // androidx.coordinatorlayout.widget.d
    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, V v4) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout, v4), (BottomSheetBehavior<?>) this);
    }

    @Override // androidx.coordinatorlayout.widget.d
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, V v4, View view, View view2, int i5, int i7) {
        this.lastNestedScrollDy = 0;
        this.nestedScrolled = false;
        return (i5 & 2) != 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00a9  */
    @Override // androidx.coordinatorlayout.widget.d
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onStopNestedScroll(androidx.coordinatorlayout.widget.CoordinatorLayout r3, V r4, android.view.View r5, int r6) {
        /*
            r2 = this;
            int r3 = r4.getTop()
            int r6 = r2.getExpandedOffset()
            r0 = 3
            if (r3 != r6) goto Lf
            r2.setStateInternal(r0)
            return
        Lf:
            boolean r3 = r2.isNestedScrollingCheckEnabled()
            if (r3 == 0) goto L24
            java.lang.ref.WeakReference<android.view.View> r3 = r2.nestedScrollingChildRef
            if (r3 == 0) goto L23
            java.lang.Object r3 = r3.get()
            if (r5 != r3) goto L23
            boolean r3 = r2.nestedScrolled
            if (r3 != 0) goto L24
        L23:
            return
        L24:
            int r3 = r2.lastNestedScrollDy
            r5 = 6
            if (r3 <= 0) goto L39
            boolean r3 = r2.fitToContents
            if (r3 == 0) goto L2f
            goto Laa
        L2f:
            int r3 = r4.getTop()
            int r6 = r2.halfExpandedOffset
            if (r3 <= r6) goto Laa
            goto La9
        L39:
            boolean r3 = r2.hideable
            if (r3 == 0) goto L49
            float r3 = r2.getYVelocity()
            boolean r3 = r2.shouldHide(r4, r3)
            if (r3 == 0) goto L49
            r0 = 5
            goto Laa
        L49:
            int r3 = r2.lastNestedScrollDy
            r6 = 4
            if (r3 != 0) goto L8e
            int r3 = r4.getTop()
            boolean r1 = r2.fitToContents
            if (r1 == 0) goto L68
            int r5 = r2.fitToContentsOffset
            int r5 = r3 - r5
            int r5 = java.lang.Math.abs(r5)
            int r1 = r2.collapsedOffset
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r5 >= r3) goto L92
            goto Laa
        L68:
            int r1 = r2.halfExpandedOffset
            if (r3 >= r1) goto L7e
            int r1 = r2.collapsedOffset
            int r1 = r3 - r1
            int r1 = java.lang.Math.abs(r1)
            if (r3 >= r1) goto L77
            goto Laa
        L77:
            boolean r3 = r2.shouldSkipHalfExpandedStateWhenDragging()
            if (r3 == 0) goto La9
            goto L92
        L7e:
            int r0 = r3 - r1
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.collapsedOffset
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L92
            goto La9
        L8e:
            boolean r3 = r2.fitToContents
            if (r3 == 0) goto L94
        L92:
            r0 = r6
            goto Laa
        L94:
            int r3 = r4.getTop()
            int r0 = r2.halfExpandedOffset
            int r0 = r3 - r0
            int r0 = java.lang.Math.abs(r0)
            int r1 = r2.collapsedOffset
            int r3 = r3 - r1
            int r3 = java.lang.Math.abs(r3)
            if (r0 >= r3) goto L92
        La9:
            r0 = r5
        Laa:
            r3 = 0
            r2.startSettling(r4, r0, r3)
            r2.nestedScrolled = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.onStopNestedScroll(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.View, int):void");
    }

    @Override // androidx.coordinatorlayout.widget.d
    public boolean onTouchEvent(CoordinatorLayout coordinatorLayout, V v4, MotionEvent motionEvent) {
        if (!v4.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.state == 1 && actionMasked == 0) {
            return true;
        }
        if (shouldHandleDraggingWithHelper()) {
            this.viewDragHelper.m(motionEvent);
        }
        if (actionMasked == 0) {
            reset();
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (shouldHandleDraggingWithHelper() && actionMasked == 2 && !this.ignoreEvents) {
            float fAbs = Math.abs(this.initialY - motionEvent.getY());
            g gVar = this.viewDragHelper;
            if (fAbs > gVar.f7332b) {
                gVar.c(motionEvent.getPointerId(motionEvent.getActionIndex()), v4);
            }
        }
        return !this.ignoreEvents;
    }

    public void removeBottomSheetCallback(BottomSheetCallback bottomSheetCallback) {
        this.callbacks.remove(bottomSheetCallback);
    }

    public void setAccessibilityDelegateView(View view) {
        WeakReference<View> weakReference;
        if (view != null || (weakReference = this.accessibilityDelegateViewRef) == null) {
            this.accessibilityDelegateViewRef = new WeakReference<>(view);
            updateAccessibilityActions(view, 1);
        } else {
            clearAccessibilityAction(weakReference.get(), 1);
            this.accessibilityDelegateViewRef = null;
        }
    }

    @Deprecated
    public void setBottomSheetCallback(BottomSheetCallback bottomSheetCallback) {
        Log.w(TAG, "BottomSheetBehavior now supports multiple callbacks. `setBottomSheetCallback()` removes all existing callbacks, including ones set internally by library authors, which may result in unintended behavior. This may change in the future. Please use `addBottomSheetCallback()` and `removeBottomSheetCallback()` instead to set your own callbacks.");
        this.callbacks.clear();
        if (bottomSheetCallback != null) {
            this.callbacks.add(bottomSheetCallback);
        }
    }

    public void setDraggable(boolean z9) {
        this.draggable = z9;
    }

    public void setExpandedOffset(int i5) {
        if (i5 < 0) {
            throw new IllegalArgumentException("offset must be greater than or equal to 0");
        }
        this.expandedOffset = i5;
        updateDrawableForTargetState(this.state, true);
    }

    public void setFitToContents(boolean z9) {
        if (this.fitToContents == z9) {
            return;
        }
        this.fitToContents = z9;
        if (this.viewRef != null) {
            calculateCollapsedOffset();
        }
        setStateInternal((this.fitToContents && this.state == 6) ? 3 : this.state);
        updateDrawableForTargetState(this.state, true);
        updateAccessibilityActions();
    }

    public void setGestureInsetBottomIgnored(boolean z9) {
        this.gestureInsetBottomIgnored = z9;
    }

    public void setHalfExpandedRatio(float f2) {
        if (f2 <= 0.0f || f2 >= 1.0f) {
            throw new IllegalArgumentException("ratio must be a float value between 0 and 1");
        }
        this.halfExpandedRatio = f2;
        if (this.viewRef != null) {
            calculateHalfExpandedOffset();
        }
    }

    public void setHideFriction(float f2) {
        this.hideFriction = f2;
    }

    public void setHideable(boolean z9) {
        if (this.hideable != z9) {
            this.hideable = z9;
            if (!z9 && this.state == 5) {
                setState(4);
            }
            updateAccessibilityActions();
        }
    }

    public void setHideableInternal(boolean z9) {
        this.hideable = z9;
    }

    public void setMaxHeight(int i5) {
        this.maxHeight = i5;
    }

    public void setMaxWidth(int i5) {
        this.maxWidth = i5;
    }

    public void setPeekHeight(int i5) {
        setPeekHeight(i5, false);
    }

    public void setSaveFlags(int i5) {
        this.saveFlags = i5;
    }

    public void setShouldRemoveExpandedCorners(boolean z9) {
        if (this.shouldRemoveExpandedCorners != z9) {
            this.shouldRemoveExpandedCorners = z9;
            updateDrawableForTargetState(getState(), true);
        }
    }

    public void setSignificantVelocityThreshold(int i5) {
        this.significantVelocityThreshold = i5;
    }

    public void setSkipCollapsed(boolean z9) {
        this.skipCollapsed = z9;
    }

    public void setState(int i5) {
        if (i5 == 1 || i5 == 2) {
            throw new IllegalArgumentException(AbstractC0476d.m(new StringBuilder("STATE_"), i5 == 1 ? "DRAGGING" : "SETTLING", " should not be set externally."));
        }
        if (!this.hideable && i5 == 5) {
            Log.w(TAG, "Cannot set state: " + i5);
            return;
        }
        final int i7 = (i5 == 6 && this.fitToContents && getTopOffsetForState(i5) <= this.fitToContentsOffset) ? 3 : i5;
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference == null || weakReference.get() == null) {
            setStateInternal(i5);
        } else {
            final V v4 = this.viewRef.get();
            runAfterLayout(v4, new Runnable() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.1
                @Override // java.lang.Runnable
                public void run() {
                    BottomSheetBehavior.this.startSettling(v4, i7, false);
                }
            });
        }
    }

    public void setStateInternal(int i5) {
        V v4;
        if (this.state == i5) {
            return;
        }
        this.state = i5;
        if (i5 == 4 || i5 == 3 || i5 == 6 || (this.hideable && i5 == 5)) {
            this.lastStableState = i5;
        }
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference == null || (v4 = weakReference.get()) == null) {
            return;
        }
        if (i5 == 3) {
            updateImportantForAccessibility(true);
        } else if (i5 == 6 || i5 == 5 || i5 == 4) {
            updateImportantForAccessibility(false);
        }
        updateDrawableForTargetState(i5, true);
        for (int i7 = 0; i7 < this.callbacks.size(); i7++) {
            this.callbacks.get(i7).onStateChanged(v4, i5);
        }
        updateAccessibilityActions();
    }

    public void setUpdateImportantForAccessibilityOnSiblings(boolean z9) {
        this.updateImportantForAccessibilityOnSiblings = z9;
    }

    public boolean shouldExpandOnUpwardDrag(long j5, float f2) {
        return false;
    }

    public boolean shouldHide(View view, float f2) {
        if (this.skipCollapsed) {
            return true;
        }
        if (!isHideableWhenDragging() || view.getTop() < this.collapsedOffset) {
            return false;
        }
        return Math.abs(((f2 * this.hideFriction) + ((float) view.getTop())) - ((float) this.collapsedOffset)) / ((float) calculatePeekHeight()) > 0.5f;
    }

    public boolean shouldSkipHalfExpandedStateWhenDragging() {
        return false;
    }

    public boolean shouldSkipSmoothAnimation() {
        return true;
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void startBackProgress(b bVar) {
        MaterialBottomContainerBackHelper materialBottomContainerBackHelper = this.bottomContainerBackHelper;
        if (materialBottomContainerBackHelper == null) {
            return;
        }
        materialBottomContainerBackHelper.startBackProgress(bVar);
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void updateBackProgress(b bVar) {
        MaterialBottomContainerBackHelper materialBottomContainerBackHelper = this.bottomContainerBackHelper;
        if (materialBottomContainerBackHelper == null) {
            return;
        }
        materialBottomContainerBackHelper.updateBackProgress(bVar);
    }

    public final void setPeekHeight(int i5, boolean z9) {
        if (i5 == -1) {
            if (this.peekHeightAuto) {
                return;
            } else {
                this.peekHeightAuto = true;
            }
        } else {
            if (!this.peekHeightAuto && this.peekHeight == i5) {
                return;
            }
            this.peekHeightAuto = false;
            this.peekHeight = Math.max(0, i5);
        }
        updatePeekHeight(z9);
    }

    private void updateAccessibilityActions(View view, int i5) {
        if (view == null) {
            return;
        }
        clearAccessibilityAction(view, i5);
        if (!this.fitToContents && this.state != 6) {
            this.expandHalfwayActionIds.put(i5, addAccessibilityActionForState(view, R.string.bottomsheet_action_expand_halfway, 6));
        }
        if (this.hideable && isHideableWhenDragging() && this.state != 5) {
            replaceAccessibilityActionForState(view, L.f.f1783j, 5);
        }
        int i7 = this.state;
        if (i7 == 3) {
            replaceAccessibilityActionForState(view, L.f.f1782i, this.fitToContents ? 4 : 6);
            return;
        }
        if (i7 == 4) {
            replaceAccessibilityActionForState(view, L.f.h, this.fitToContents ? 3 : 6);
        } else {
            if (i7 != 6) {
                return;
            }
            replaceAccessibilityActionForState(view, L.f.f1782i, 4);
            replaceAccessibilityActionForState(view, L.f.h, 3);
        }
    }

    public BottomSheetBehavior(Context context, AttributeSet attributeSet) {
        int i5;
        this.saveFlags = 0;
        this.fitToContents = true;
        this.updateImportantForAccessibilityOnSiblings = false;
        this.maxWidth = -1;
        this.maxHeight = -1;
        this.stateSettlingTracker = new StateSettlingTracker();
        this.halfExpandedRatio = 0.5f;
        this.elevation = -1.0f;
        this.draggable = true;
        this.state = 4;
        this.lastStableState = 4;
        this.hideFriction = 0.1f;
        this.callbacks = new ArrayList<>();
        this.initialY = -1;
        this.expandHalfwayActionIds = new SparseIntArray();
        this.dragCallback = new f() { // from class: com.google.android.material.bottomsheet.BottomSheetBehavior.5
            private long viewCapturedMillis;

            private boolean releasedLow(View view) {
                int top = view.getTop();
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                return top > (bottomSheetBehavior.getExpandedOffset() + bottomSheetBehavior.parentHeight) / 2;
            }

            @Override // androidx.customview.widget.f
            public int clampViewPositionHorizontal(View view, int i52, int i7) {
                return view.getLeft();
            }

            @Override // androidx.customview.widget.f
            public int clampViewPositionVertical(View view, int i52, int i7) {
                return com.bumptech.glide.c.d(i52, BottomSheetBehavior.this.getExpandedOffset(), getViewVerticalDragRange(view));
            }

            @Override // androidx.customview.widget.f
            public int getViewVerticalDragRange(View view) {
                return BottomSheetBehavior.this.canBeHiddenByDragging() ? BottomSheetBehavior.this.parentHeight : BottomSheetBehavior.this.collapsedOffset;
            }

            @Override // androidx.customview.widget.f
            public void onViewDragStateChanged(int i52) {
                if (i52 == 1 && BottomSheetBehavior.this.draggable) {
                    BottomSheetBehavior.this.setStateInternal(1);
                }
            }

            @Override // androidx.customview.widget.f
            public void onViewPositionChanged(View view, int i52, int i7, int i9, int i10) {
                BottomSheetBehavior.this.dispatchOnSlide(i7);
            }

            /* JADX WARN: Removed duplicated region for block: B:39:0x00ad  */
            /* JADX WARN: Removed duplicated region for block: B:6:0x0010  */
            @Override // androidx.customview.widget.f
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onViewReleased(android.view.View r8, float r9, float r10) {
                /*
                    Method dump skipped, instruction units count: 308
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.bottomsheet.BottomSheetBehavior.AnonymousClass5.onViewReleased(android.view.View, float, float):void");
            }

            @Override // androidx.customview.widget.f
            public boolean tryCaptureView(View view, int i52) {
                BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.this;
                int i7 = bottomSheetBehavior.state;
                if (i7 == 1 || bottomSheetBehavior.touchingScrollingChild) {
                    return false;
                }
                if (i7 == 3 && bottomSheetBehavior.activePointerId == i52) {
                    WeakReference<View> weakReference = bottomSheetBehavior.nestedScrollingChildRef;
                    View view2 = weakReference != null ? weakReference.get() : null;
                    if (view2 != null && view2.canScrollVertically(-1)) {
                        return false;
                    }
                }
                this.viewCapturedMillis = System.currentTimeMillis();
                WeakReference<V> weakReference2 = BottomSheetBehavior.this.viewRef;
                return weakReference2 != null && weakReference2.get() == view;
            }
        };
        this.peekHeightGestureInsetBuffer = context.getResources().getDimensionPixelSize(R.dimen.mtrl_min_touch_target_size);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BottomSheetBehavior_Layout);
        int i7 = R.styleable.BottomSheetBehavior_Layout_backgroundTint;
        if (typedArrayObtainStyledAttributes.hasValue(i7)) {
            this.backgroundTint = MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes, i7);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.BottomSheetBehavior_Layout_shapeAppearance)) {
            this.shapeAppearanceModelDefault = ShapeAppearanceModel.builder(context, attributeSet, R.attr.bottomSheetStyle, DEF_STYLE_RES).build();
        }
        createMaterialShapeDrawableIfNeeded(context);
        createShapeValueAnimator();
        this.elevation = typedArrayObtainStyledAttributes.getDimension(R.styleable.BottomSheetBehavior_Layout_android_elevation, -1.0f);
        int i9 = R.styleable.BottomSheetBehavior_Layout_android_maxWidth;
        if (typedArrayObtainStyledAttributes.hasValue(i9)) {
            setMaxWidth(typedArrayObtainStyledAttributes.getDimensionPixelSize(i9, -1));
        }
        int i10 = R.styleable.BottomSheetBehavior_Layout_android_maxHeight;
        if (typedArrayObtainStyledAttributes.hasValue(i10)) {
            setMaxHeight(typedArrayObtainStyledAttributes.getDimensionPixelSize(i10, -1));
        }
        int i11 = R.styleable.BottomSheetBehavior_Layout_behavior_peekHeight;
        TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(i11);
        if (typedValuePeekValue != null && (i5 = typedValuePeekValue.data) == -1) {
            setPeekHeight(i5);
        } else {
            setPeekHeight(typedArrayObtainStyledAttributes.getDimensionPixelSize(i11, -1));
        }
        setHideable(typedArrayObtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_hideable, false));
        setGestureInsetBottomIgnored(typedArrayObtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_gestureInsetBottomIgnored, false));
        setFitToContents(typedArrayObtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_fitToContents, true));
        setSkipCollapsed(typedArrayObtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_skipCollapsed, false));
        setDraggable(typedArrayObtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_behavior_draggable, true));
        setSaveFlags(typedArrayObtainStyledAttributes.getInt(R.styleable.BottomSheetBehavior_Layout_behavior_saveFlags, 0));
        setHalfExpandedRatio(typedArrayObtainStyledAttributes.getFloat(R.styleable.BottomSheetBehavior_Layout_behavior_halfExpandedRatio, 0.5f));
        int i12 = R.styleable.BottomSheetBehavior_Layout_behavior_expandedOffset;
        TypedValue typedValuePeekValue2 = typedArrayObtainStyledAttributes.peekValue(i12);
        if (typedValuePeekValue2 != null && typedValuePeekValue2.type == 16) {
            setExpandedOffset(typedValuePeekValue2.data);
        } else {
            setExpandedOffset(typedArrayObtainStyledAttributes.getDimensionPixelOffset(i12, 0));
        }
        setSignificantVelocityThreshold(typedArrayObtainStyledAttributes.getInt(R.styleable.BottomSheetBehavior_Layout_behavior_significantVelocityThreshold, 500));
        this.paddingBottomSystemWindowInsets = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingBottomSystemWindowInsets, false);
        this.paddingLeftSystemWindowInsets = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingLeftSystemWindowInsets, false);
        this.paddingRightSystemWindowInsets = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingRightSystemWindowInsets, false);
        this.paddingTopSystemWindowInsets = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_paddingTopSystemWindowInsets, true);
        this.marginLeftSystemWindowInsets = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_marginLeftSystemWindowInsets, false);
        this.marginRightSystemWindowInsets = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_marginRightSystemWindowInsets, false);
        this.marginTopSystemWindowInsets = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_marginTopSystemWindowInsets, false);
        this.shouldRemoveExpandedCorners = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BottomSheetBehavior_Layout_shouldRemoveExpandedCorners, true);
        typedArrayObtainStyledAttributes.recycle();
        this.maximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }
}
