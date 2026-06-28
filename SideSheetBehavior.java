package com.google.android.material.sidesheet;

import A8.l;
import C.p;
import L.o;
import L.w;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.M;
import androidx.core.view.W;
import androidx.customview.widget.f;
import androidx.customview.widget.g;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.motion.MaterialSideContainerBackHelper;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import d6.AbstractC0476d;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SideSheetBehavior<V extends View> extends androidx.coordinatorlayout.widget.d implements Sheet<SideSheetCallback> {
    private static final int DEFAULT_ACCESSIBILITY_PANE_TITLE = R.string.side_sheet_accessibility_pane_title;
    private static final int DEF_STYLE_RES = R.style.Widget_Material3_SideSheet;
    private static final float HIDE_FRICTION = 0.1f;
    private static final float HIDE_THRESHOLD = 0.5f;
    private static final int NO_MAX_SIZE = -1;
    static final int SIGNIFICANT_VEL_THRESHOLD = 500;
    private ColorStateList backgroundTint;
    private int childWidth;
    private WeakReference<View> coplanarSiblingViewRef;
    private float elevation;
    private boolean ignoreEvents;
    private int initialX;
    private int innerMargin;
    private MaterialShapeDrawable materialShapeDrawable;
    private float maximumVelocity;
    private int parentInnerEdge;
    private int parentWidth;
    private ShapeAppearanceModel shapeAppearanceModel;
    private SheetDelegate sheetDelegate;
    private MaterialSideContainerBackHelper sideContainerBackHelper;
    private VelocityTracker velocityTracker;
    private g viewDragHelper;
    private WeakReference<V> viewRef;
    private final SideSheetBehavior<V>.StateSettlingTracker stateSettlingTracker = new StateSettlingTracker();
    private boolean draggable = true;
    private int state = 5;
    private int lastStableState = 5;
    private float hideFriction = 0.1f;
    private int coplanarSiblingViewId = -1;
    private final Set<SideSheetCallback> callbacks = new LinkedHashSet();
    private final f dragCallback = new f() { // from class: com.google.android.material.sidesheet.SideSheetBehavior.1
        @Override // androidx.customview.widget.f
        public int clampViewPositionHorizontal(View view, int i5, int i7) {
            return com.bumptech.glide.c.d(i5, SideSheetBehavior.this.sheetDelegate.getMinViewPositionHorizontal(), SideSheetBehavior.this.sheetDelegate.getMaxViewPositionHorizontal());
        }

        @Override // androidx.customview.widget.f
        public int clampViewPositionVertical(View view, int i5, int i7) {
            return view.getTop();
        }

        @Override // androidx.customview.widget.f
        public int getViewHorizontalDragRange(View view) {
            return SideSheetBehavior.this.getInnerMargin() + SideSheetBehavior.this.childWidth;
        }

        @Override // androidx.customview.widget.f
        public void onViewDragStateChanged(int i5) {
            if (i5 == 1 && SideSheetBehavior.this.draggable) {
                SideSheetBehavior.this.setStateInternal(1);
            }
        }

        @Override // androidx.customview.widget.f
        public void onViewPositionChanged(View view, int i5, int i7, int i9, int i10) {
            ViewGroup.MarginLayoutParams marginLayoutParams;
            View coplanarSiblingView = SideSheetBehavior.this.getCoplanarSiblingView();
            if (coplanarSiblingView != null && (marginLayoutParams = (ViewGroup.MarginLayoutParams) coplanarSiblingView.getLayoutParams()) != null) {
                SideSheetBehavior.this.sheetDelegate.updateCoplanarSiblingLayoutParams(marginLayoutParams, view.getLeft(), view.getRight());
                coplanarSiblingView.setLayoutParams(marginLayoutParams);
            }
            SideSheetBehavior.this.dispatchOnSlide(view, i5);
        }

        @Override // androidx.customview.widget.f
        public void onViewReleased(View view, float f2, float f7) {
            int iCalculateTargetStateOnViewReleased = SideSheetBehavior.this.calculateTargetStateOnViewReleased(view, f2, f7);
            SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
            sideSheetBehavior.startSettling(view, iCalculateTargetStateOnViewReleased, sideSheetBehavior.shouldSkipSmoothAnimation());
        }

        @Override // androidx.customview.widget.f
        public boolean tryCaptureView(View view, int i5) {
            return (SideSheetBehavior.this.state == 1 || SideSheetBehavior.this.viewRef == null || SideSheetBehavior.this.viewRef.get() != view) ? false : true;
        }
    };

    public static class SavedState extends R.c {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() { // from class: com.google.android.material.sidesheet.SideSheetBehavior.SavedState.1
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
        final int state;

        public SavedState(Parcel parcel) {
            this(parcel, (ClassLoader) null);
        }

        @Override // R.c, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i5) {
            super.writeToParcel(parcel, i5);
            parcel.writeInt(this.state);
        }

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.state = parcel.readInt();
        }

        public SavedState(Parcelable parcelable, SideSheetBehavior<?> sideSheetBehavior) {
            super(parcelable);
            this.state = ((SideSheetBehavior) sideSheetBehavior).state;
        }
    }

    public class StateSettlingTracker {
        private final Runnable continueSettlingRunnable = new Runnable() { // from class: com.google.android.material.sidesheet.d
            @Override // java.lang.Runnable
            public final void run() {
                this.f10121e.lambda$new$0();
            }
        };
        private boolean isContinueSettlingRunnablePosted;
        private int targetState;

        public StateSettlingTracker() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$new$0() {
            this.isContinueSettlingRunnablePosted = false;
            if (SideSheetBehavior.this.viewDragHelper != null && SideSheetBehavior.this.viewDragHelper.h()) {
                continueSettlingToState(this.targetState);
            } else if (SideSheetBehavior.this.state == 2) {
                SideSheetBehavior.this.setStateInternal(this.targetState);
            }
        }

        public void continueSettlingToState(int i5) {
            if (SideSheetBehavior.this.viewRef == null || SideSheetBehavior.this.viewRef.get() == null) {
                return;
            }
            this.targetState = i5;
            if (this.isContinueSettlingRunnablePosted) {
                return;
            }
            View view = (View) SideSheetBehavior.this.viewRef.get();
            Runnable runnable = this.continueSettlingRunnable;
            WeakHashMap weakHashMap = W.f7199a;
            view.postOnAnimation(runnable);
            this.isContinueSettlingRunnablePosted = true;
        }
    }

    public SideSheetBehavior() {
    }

    private int calculateCurrentOffset(int i5, V v4) {
        int i7 = this.state;
        if (i7 == 1 || i7 == 2) {
            return i5 - this.sheetDelegate.getOuterEdge(v4);
        }
        if (i7 == 3) {
            return 0;
        }
        if (i7 == 5) {
            return this.sheetDelegate.getHiddenOffset();
        }
        throw new IllegalStateException("Unexpected value: " + this.state);
    }

    private float calculateDragDistance(float f2, float f7) {
        return Math.abs(f2 - f7);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int calculateTargetStateOnViewReleased(View view, float f2, float f7) {
        if (isExpandingOutwards(f2)) {
            return 3;
        }
        if (shouldHide(view, f2)) {
            if (!this.sheetDelegate.isSwipeSignificant(f2, f7) && !this.sheetDelegate.isReleasedCloseToInnerEdge(view)) {
                return 3;
            }
        } else if (f2 == 0.0f || !SheetUtils.isSwipeMostlyHorizontal(f2, f7)) {
            int left = view.getLeft();
            if (Math.abs(left - getExpandedOffset()) < Math.abs(left - this.sheetDelegate.getHiddenOffset())) {
                return 3;
            }
        }
        return 5;
    }

    private void clearCoplanarSiblingView() {
        WeakReference<View> weakReference = this.coplanarSiblingViewRef;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.coplanarSiblingViewRef = null;
    }

    private w createAccessibilityViewCommandForState(final int i5) {
        return new w() { // from class: com.google.android.material.sidesheet.b
            @Override // L.w
            public final boolean perform(View view, o oVar) {
                return this.f10116e.lambda$createAccessibilityViewCommandForState$2(i5, view, null);
            }
        };
    }

    private void createMaterialShapeDrawableIfNeeded(Context context) {
        if (this.shapeAppearanceModel == null) {
            return;
        }
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(this.shapeAppearanceModel);
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

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchOnSlide(View view, int i5) {
        if (this.callbacks.isEmpty()) {
            return;
        }
        float fCalculateSlideOffset = this.sheetDelegate.calculateSlideOffset(i5);
        Iterator<SideSheetCallback> it = this.callbacks.iterator();
        while (it.hasNext()) {
            it.next().onSlide(view, fCalculateSlideOffset);
        }
    }

    private void ensureAccessibilityPaneTitleIsSet(View view) {
        if (W.c(view) == null) {
            W.j(view, view.getResources().getString(DEFAULT_ACCESSIBILITY_PANE_TITLE));
        }
    }

    public static <V extends View> SideSheetBehavior<V> from(V v4) {
        ViewGroup.LayoutParams layoutParams = v4.getLayoutParams();
        if (!(layoutParams instanceof androidx.coordinatorlayout.widget.g)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        androidx.coordinatorlayout.widget.d dVar = ((androidx.coordinatorlayout.widget.g) layoutParams).f7154a;
        if (dVar instanceof SideSheetBehavior) {
            return (SideSheetBehavior) dVar;
        }
        throw new IllegalArgumentException("The view is not associated with SideSheetBehavior");
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

    private ValueAnimator.AnimatorUpdateListener getCoplanarFinishAnimatorUpdateListener() {
        final ViewGroup.MarginLayoutParams marginLayoutParams;
        final View coplanarSiblingView = getCoplanarSiblingView();
        if (coplanarSiblingView == null || (marginLayoutParams = (ViewGroup.MarginLayoutParams) coplanarSiblingView.getLayoutParams()) == null) {
            return null;
        }
        final int coplanarSiblingAdjacentMargin = this.sheetDelegate.getCoplanarSiblingAdjacentMargin(marginLayoutParams);
        return new ValueAnimator.AnimatorUpdateListener() { // from class: com.google.android.material.sidesheet.c
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f10118e.lambda$getCoplanarFinishAnimatorUpdateListener$1(marginLayoutParams, coplanarSiblingAdjacentMargin, coplanarSiblingView, valueAnimator);
            }
        };
    }

    private int getGravityFromSheetEdge() {
        SheetDelegate sheetDelegate = this.sheetDelegate;
        return (sheetDelegate == null || sheetDelegate.getSheetEdge() == 0) ? 5 : 3;
    }

    private androidx.coordinatorlayout.widget.g getViewLayoutParams() {
        V v4;
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference == null || (v4 = weakReference.get()) == null || !(v4.getLayoutParams() instanceof androidx.coordinatorlayout.widget.g)) {
            return null;
        }
        return (androidx.coordinatorlayout.widget.g) v4.getLayoutParams();
    }

    private boolean hasLeftMargin() {
        androidx.coordinatorlayout.widget.g viewLayoutParams = getViewLayoutParams();
        return viewLayoutParams != null && ((ViewGroup.MarginLayoutParams) viewLayoutParams).leftMargin > 0;
    }

    private boolean hasRightMargin() {
        androidx.coordinatorlayout.widget.g viewLayoutParams = getViewLayoutParams();
        return viewLayoutParams != null && ((ViewGroup.MarginLayoutParams) viewLayoutParams).rightMargin > 0;
    }

    private boolean isDraggedFarEnough(MotionEvent motionEvent) {
        return shouldHandleDraggingWithHelper() && calculateDragDistance((float) this.initialX, motionEvent.getX()) > ((float) this.viewDragHelper.f7332b);
    }

    private boolean isExpandingOutwards(float f2) {
        return this.sheetDelegate.isExpandingOutwards(f2);
    }

    private boolean isLayingOut(V v4) {
        ViewParent parent = v4.getParent();
        if (parent != null && parent.isLayoutRequested()) {
            WeakHashMap weakHashMap = W.f7199a;
            if (v4.isAttachedToWindow()) {
                return true;
            }
        }
        return false;
    }

    private boolean isSettling(View view, int i5, boolean z9) {
        int outerEdgeOffsetForState = getOuterEdgeOffsetForState(i5);
        g viewDragHelper = getViewDragHelper();
        return viewDragHelper != null && (!z9 ? !viewDragHelper.u(view, outerEdgeOffsetForState, view.getTop()) : !viewDragHelper.s(outerEdgeOffsetForState, view.getTop()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$createAccessibilityViewCommandForState$2(int i5, View view, o oVar) {
        setState(i5);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getCoplanarFinishAnimatorUpdateListener$1(ViewGroup.MarginLayoutParams marginLayoutParams, int i5, View view, ValueAnimator valueAnimator) {
        this.sheetDelegate.updateCoplanarSiblingAdjacentMargin(marginLayoutParams, AnimationUtils.lerp(i5, 0, valueAnimator.getAnimatedFraction()));
        view.requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setState$0(int i5) {
        V v4 = this.viewRef.get();
        if (v4 != null) {
            startSettling(v4, i5, false);
        }
    }

    private void maybeAssignCoplanarSiblingViewBasedId(CoordinatorLayout coordinatorLayout) {
        int i5;
        View viewFindViewById;
        if (this.coplanarSiblingViewRef != null || (i5 = this.coplanarSiblingViewId) == -1 || (viewFindViewById = coordinatorLayout.findViewById(i5)) == null) {
            return;
        }
        this.coplanarSiblingViewRef = new WeakReference<>(viewFindViewById);
    }

    private void replaceAccessibilityActionForState(V v4, L.f fVar, int i5) {
        W.h(v4, fVar, null, createAccessibilityViewCommandForState(i5));
    }

    private void resetVelocity() {
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.velocityTracker = null;
        }
    }

    private void runAfterLayout(V v4, Runnable runnable) {
        if (isLayingOut(v4)) {
            v4.post(runnable);
        } else {
            runnable.run();
        }
    }

    private void setSheetEdge(V v4, int i5) {
        setSheetEdge(Gravity.getAbsoluteGravity(((androidx.coordinatorlayout.widget.g) v4.getLayoutParams()).f7156c, i5) == 3 ? 1 : 0);
    }

    private boolean shouldHandleDraggingWithHelper() {
        return this.viewDragHelper != null && (this.draggable || this.state == 1);
    }

    private boolean shouldInterceptTouchEvent(V v4) {
        return (v4.isShown() || W.c(v4) != null) && this.draggable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startSettling(View view, int i5, boolean z9) {
        if (!isSettling(view, i5, z9)) {
            setStateInternal(i5);
        } else {
            setStateInternal(2);
            this.stateSettlingTracker.continueSettlingToState(i5);
        }
    }

    private void updateAccessibilityActions() {
        V v4;
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference == null || (v4 = weakReference.get()) == null) {
            return;
        }
        W.g(262144, v4);
        W.e(0, v4);
        W.g(1048576, v4);
        W.e(0, v4);
        if (this.state != 5) {
            replaceAccessibilityActionForState(v4, L.f.f1783j, 5);
        }
        if (this.state != 3) {
            replaceAccessibilityActionForState(v4, L.f.h, 3);
        }
    }

    private void updateCoplanarSiblingBackProgress() {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        V v4 = this.viewRef.get();
        View coplanarSiblingView = getCoplanarSiblingView();
        if (coplanarSiblingView == null || (marginLayoutParams = (ViewGroup.MarginLayoutParams) coplanarSiblingView.getLayoutParams()) == null) {
            return;
        }
        this.sheetDelegate.updateCoplanarSiblingAdjacentMargin(marginLayoutParams, (int) ((v4.getScaleX() * this.childWidth) + this.innerMargin));
        coplanarSiblingView.requestLayout();
    }

    private void updateMaterialShapeDrawable(ShapeAppearanceModel shapeAppearanceModel) {
        MaterialShapeDrawable materialShapeDrawable = this.materialShapeDrawable;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel);
        }
    }

    private void updateSheetVisibility(View view) {
        int i5 = this.state == 5 ? 4 : 0;
        if (view.getVisibility() != i5) {
            view.setVisibility(i5);
        }
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void cancelBackProgress() {
        MaterialSideContainerBackHelper materialSideContainerBackHelper = this.sideContainerBackHelper;
        if (materialSideContainerBackHelper == null) {
            return;
        }
        materialSideContainerBackHelper.cancelBackProgress();
    }

    public void expand() {
        setState(3);
    }

    public MaterialSideContainerBackHelper getBackHelper() {
        return this.sideContainerBackHelper;
    }

    public int getChildWidth() {
        return this.childWidth;
    }

    public View getCoplanarSiblingView() {
        WeakReference<View> weakReference = this.coplanarSiblingViewRef;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public int getExpandedOffset() {
        return this.sheetDelegate.getExpandedOffset();
    }

    public float getHideFriction() {
        return this.hideFriction;
    }

    public float getHideThreshold() {
        return 0.5f;
    }

    public int getInnerMargin() {
        return this.innerMargin;
    }

    public int getLastStableState() {
        return this.lastStableState;
    }

    public int getOuterEdgeOffsetForState(int i5) {
        if (i5 == 3) {
            return getExpandedOffset();
        }
        if (i5 == 5) {
            return this.sheetDelegate.getHiddenOffset();
        }
        throw new IllegalArgumentException(l.o(i5, "Invalid state to get outer edge offset: "));
    }

    public int getParentInnerEdge() {
        return this.parentInnerEdge;
    }

    public int getParentWidth() {
        return this.parentWidth;
    }

    public int getSignificantVelocityThreshold() {
        return SIGNIFICANT_VEL_THRESHOLD;
    }

    @Override // com.google.android.material.sidesheet.Sheet
    public int getState() {
        return this.state;
    }

    public g getViewDragHelper() {
        return this.viewDragHelper;
    }

    public float getXVelocity() {
        VelocityTracker velocityTracker = this.velocityTracker;
        if (velocityTracker == null) {
            return 0.0f;
        }
        velocityTracker.computeCurrentVelocity(1000, this.maximumVelocity);
        return this.velocityTracker.getXVelocity();
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void handleBackInvoked() {
        MaterialSideContainerBackHelper materialSideContainerBackHelper = this.sideContainerBackHelper;
        if (materialSideContainerBackHelper == null) {
            return;
        }
        androidx.activity.b bVarOnHandleBackInvoked = materialSideContainerBackHelper.onHandleBackInvoked();
        if (bVarOnHandleBackInvoked == null || Build.VERSION.SDK_INT < 34) {
            setState(5);
        } else {
            this.sideContainerBackHelper.finishBackProgress(bVarOnHandleBackInvoked, getGravityFromSheetEdge(), new AnimatorListenerAdapter() { // from class: com.google.android.material.sidesheet.SideSheetBehavior.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    SideSheetBehavior.this.setStateInternal(5);
                    if (SideSheetBehavior.this.viewRef == null || SideSheetBehavior.this.viewRef.get() == null) {
                        return;
                    }
                    ((View) SideSheetBehavior.this.viewRef.get()).requestLayout();
                }
            }, getCoplanarFinishAnimatorUpdateListener());
        }
    }

    public void hide() {
        setState(5);
    }

    public boolean isDraggable() {
        return this.draggable;
    }

    @Override // androidx.coordinatorlayout.widget.d
    public void onAttachedToLayoutParams(androidx.coordinatorlayout.widget.g gVar) {
        super.onAttachedToLayoutParams(gVar);
        this.viewRef = null;
        this.viewDragHelper = null;
        this.sideContainerBackHelper = null;
    }

    @Override // androidx.coordinatorlayout.widget.d
    public void onDetachedFromLayoutParams() {
        super.onDetachedFromLayoutParams();
        this.viewRef = null;
        this.viewDragHelper = null;
        this.sideContainerBackHelper = null;
    }

    @Override // androidx.coordinatorlayout.widget.d
    public boolean onInterceptTouchEvent(CoordinatorLayout coordinatorLayout, V v4, MotionEvent motionEvent) {
        g gVar;
        if (!shouldInterceptTouchEvent(v4)) {
            this.ignoreEvents = true;
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            resetVelocity();
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (actionMasked == 0) {
            this.initialX = (int) motionEvent.getX();
        } else if ((actionMasked == 1 || actionMasked == 3) && this.ignoreEvents) {
            this.ignoreEvents = false;
            return false;
        }
        return (this.ignoreEvents || (gVar = this.viewDragHelper) == null || !gVar.t(motionEvent)) ? false : true;
    }

    @Override // androidx.coordinatorlayout.widget.d
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, V v4, int i5) {
        WeakHashMap weakHashMap = W.f7199a;
        if (coordinatorLayout.getFitsSystemWindows() && !v4.getFitsSystemWindows()) {
            v4.setFitsSystemWindows(true);
        }
        if (this.viewRef == null) {
            this.viewRef = new WeakReference<>(v4);
            this.sideContainerBackHelper = new MaterialSideContainerBackHelper(v4);
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
            updateSheetVisibility(v4);
            updateAccessibilityActions();
            if (v4.getImportantForAccessibility() == 0) {
                v4.setImportantForAccessibility(1);
            }
            ensureAccessibilityPaneTitleIsSet(v4);
        }
        setSheetEdge(v4, i5);
        if (this.viewDragHelper == null) {
            this.viewDragHelper = new g(coordinatorLayout.getContext(), coordinatorLayout, this.dragCallback);
        }
        int outerEdge = this.sheetDelegate.getOuterEdge(v4);
        coordinatorLayout.onLayoutChild(v4, i5);
        this.parentWidth = coordinatorLayout.getWidth();
        this.parentInnerEdge = this.sheetDelegate.getParentInnerEdge(coordinatorLayout);
        this.childWidth = v4.getWidth();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) v4.getLayoutParams();
        this.innerMargin = marginLayoutParams != null ? this.sheetDelegate.calculateInnerMargin(marginLayoutParams) : 0;
        v4.offsetLeftAndRight(calculateCurrentOffset(outerEdge, v4));
        maybeAssignCoplanarSiblingViewBasedId(coordinatorLayout);
        for (SideSheetCallback sideSheetCallback : this.callbacks) {
            if (sideSheetCallback instanceof SideSheetCallback) {
                sideSheetCallback.onLayout(v4);
            }
        }
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.d
    public boolean onMeasureChild(CoordinatorLayout coordinatorLayout, V v4, int i5, int i7, int i9, int i10) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) v4.getLayoutParams();
        v4.measure(getChildMeasureSpec(i5, coordinatorLayout.getPaddingRight() + coordinatorLayout.getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i7, -1, marginLayoutParams.width), getChildMeasureSpec(i9, coordinatorLayout.getPaddingBottom() + coordinatorLayout.getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i10, -1, marginLayoutParams.height));
        return true;
    }

    @Override // androidx.coordinatorlayout.widget.d
    public void onRestoreInstanceState(CoordinatorLayout coordinatorLayout, V v4, Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        if (savedState.getSuperState() != null) {
            super.onRestoreInstanceState(coordinatorLayout, v4, savedState.getSuperState());
        }
        int i5 = savedState.state;
        if (i5 == 1 || i5 == 2) {
            i5 = 5;
        }
        this.state = i5;
        this.lastStableState = i5;
    }

    @Override // androidx.coordinatorlayout.widget.d
    public Parcelable onSaveInstanceState(CoordinatorLayout coordinatorLayout, V v4) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout, v4), (SideSheetBehavior<?>) this);
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
            resetVelocity();
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (shouldHandleDraggingWithHelper() && actionMasked == 2 && !this.ignoreEvents && isDraggedFarEnough(motionEvent)) {
            this.viewDragHelper.c(motionEvent.getPointerId(motionEvent.getActionIndex()), v4);
        }
        return !this.ignoreEvents;
    }

    public void setCoplanarSiblingView(View view) {
        this.coplanarSiblingViewId = -1;
        if (view == null) {
            clearCoplanarSiblingView();
            return;
        }
        this.coplanarSiblingViewRef = new WeakReference<>(view);
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference != null) {
            V v4 = weakReference.get();
            WeakHashMap weakHashMap = W.f7199a;
            if (v4.isLaidOut()) {
                v4.requestLayout();
            }
        }
    }

    public void setCoplanarSiblingViewId(int i5) {
        this.coplanarSiblingViewId = i5;
        clearCoplanarSiblingView();
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference != null) {
            V v4 = weakReference.get();
            if (i5 != -1) {
                WeakHashMap weakHashMap = W.f7199a;
                if (v4.isLaidOut()) {
                    v4.requestLayout();
                }
            }
        }
    }

    public void setDraggable(boolean z9) {
        this.draggable = z9;
    }

    public void setHideFriction(float f2) {
        this.hideFriction = f2;
    }

    @Override // com.google.android.material.sidesheet.Sheet
    public void setState(int i5) {
        if (i5 == 1 || i5 == 2) {
            throw new IllegalArgumentException(AbstractC0476d.m(new StringBuilder("STATE_"), i5 == 1 ? "DRAGGING" : "SETTLING", " should not be set externally."));
        }
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference == null || weakReference.get() == null) {
            setStateInternal(i5);
        } else {
            runAfterLayout(this.viewRef.get(), new p(i5, 2, this));
        }
    }

    public void setStateInternal(int i5) {
        V v4;
        if (this.state == i5) {
            return;
        }
        this.state = i5;
        if (i5 == 3 || i5 == 5) {
            this.lastStableState = i5;
        }
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference == null || (v4 = weakReference.get()) == null) {
            return;
        }
        updateSheetVisibility(v4);
        Iterator<SideSheetCallback> it = this.callbacks.iterator();
        while (it.hasNext()) {
            it.next().onStateChanged(v4, i5);
        }
        updateAccessibilityActions();
    }

    public boolean shouldHide(View view, float f2) {
        return this.sheetDelegate.shouldHide(view, f2);
    }

    public boolean shouldSkipSmoothAnimation() {
        return true;
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void startBackProgress(androidx.activity.b bVar) {
        MaterialSideContainerBackHelper materialSideContainerBackHelper = this.sideContainerBackHelper;
        if (materialSideContainerBackHelper == null) {
            return;
        }
        materialSideContainerBackHelper.startBackProgress(bVar);
    }

    @Override // com.google.android.material.motion.MaterialBackHandler
    public void updateBackProgress(androidx.activity.b bVar) {
        MaterialSideContainerBackHelper materialSideContainerBackHelper = this.sideContainerBackHelper;
        if (materialSideContainerBackHelper == null) {
            return;
        }
        materialSideContainerBackHelper.updateBackProgress(bVar, getGravityFromSheetEdge());
        updateCoplanarSiblingBackProgress();
    }

    @Override // com.google.android.material.sidesheet.Sheet
    public void addCallback(SideSheetCallback sideSheetCallback) {
        this.callbacks.add(sideSheetCallback);
    }

    @Override // com.google.android.material.sidesheet.Sheet
    public void removeCallback(SideSheetCallback sideSheetCallback) {
        this.callbacks.remove(sideSheetCallback);
    }

    private void setSheetEdge(int i5) {
        SheetDelegate sheetDelegate = this.sheetDelegate;
        if (sheetDelegate == null || sheetDelegate.getSheetEdge() != i5) {
            if (i5 == 0) {
                this.sheetDelegate = new RightSheetDelegate(this);
                if (this.shapeAppearanceModel == null || hasRightMargin()) {
                    return;
                }
                ShapeAppearanceModel.Builder builder = this.shapeAppearanceModel.toBuilder();
                builder.setTopRightCornerSize(0.0f).setBottomRightCornerSize(0.0f);
                updateMaterialShapeDrawable(builder.build());
                return;
            }
            if (i5 == 1) {
                this.sheetDelegate = new LeftSheetDelegate(this);
                if (this.shapeAppearanceModel == null || hasLeftMargin()) {
                    return;
                }
                ShapeAppearanceModel.Builder builder2 = this.shapeAppearanceModel.toBuilder();
                builder2.setTopLeftCornerSize(0.0f).setBottomLeftCornerSize(0.0f);
                updateMaterialShapeDrawable(builder2.build());
                return;
            }
            throw new IllegalArgumentException("Invalid sheet edge position value: " + i5 + ". Must be 0 or 1.");
        }
    }

    public SideSheetBehavior(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SideSheetBehavior_Layout);
        int i5 = R.styleable.SideSheetBehavior_Layout_backgroundTint;
        if (typedArrayObtainStyledAttributes.hasValue(i5)) {
            this.backgroundTint = MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes, i5);
        }
        if (typedArrayObtainStyledAttributes.hasValue(R.styleable.SideSheetBehavior_Layout_shapeAppearance)) {
            this.shapeAppearanceModel = ShapeAppearanceModel.builder(context, attributeSet, 0, DEF_STYLE_RES).build();
        }
        int i7 = R.styleable.SideSheetBehavior_Layout_coplanarSiblingViewId;
        if (typedArrayObtainStyledAttributes.hasValue(i7)) {
            setCoplanarSiblingViewId(typedArrayObtainStyledAttributes.getResourceId(i7, -1));
        }
        createMaterialShapeDrawableIfNeeded(context);
        this.elevation = typedArrayObtainStyledAttributes.getDimension(R.styleable.SideSheetBehavior_Layout_android_elevation, -1.0f);
        setDraggable(typedArrayObtainStyledAttributes.getBoolean(R.styleable.SideSheetBehavior_Layout_behavior_draggable, true));
        typedArrayObtainStyledAttributes.recycle();
        this.maximumVelocity = ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }
}
