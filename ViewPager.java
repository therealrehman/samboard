package androidx.viewpager.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Interpolator;
import android.widget.EdgeEffect;
import android.widget.Scroller;
import androidx.appcompat.widget.AbstractC0152g1;
import androidx.core.view.M;
import androidx.core.view.W;
import f6.AbstractC0527a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ViewPager extends ViewGroup {
    private static final String ACCESSIBILITY_CLASS_NAME = "androidx.viewpager.widget.ViewPager";
    private static final int CLOSE_ENOUGH = 2;
    private static final boolean DEBUG = false;
    private static final int DEFAULT_GUTTER_SIZE = 16;
    private static final int DEFAULT_OFFSCREEN_PAGES = 1;
    private static final float DEFAULT_TOUCH_SLOP_RATE = 0.5f;
    private static final int DRAW_ORDER_DEFAULT = 0;
    private static final int DRAW_ORDER_FORWARD = 1;
    private static final int DRAW_ORDER_REVERSE = 2;
    private static final int INVALID_POINTER = -1;
    private static final int MAX_SCROLL_X = 16777216;
    private static final int MAX_SETTLE_DURATION = 600;
    private static final int MIN_DISTANCE_FOR_FLING = 25;
    private static final int MIN_FLING_VELOCITY = 400;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final String TAG = "ViewPager";
    private int mActivePointerId;
    a mAdapter;
    private List<f> mAdapterChangeListeners;
    private int mBottomPageBounds;
    private boolean mCalledSuper;
    private int mCloseEnough;
    int mCurItem;
    private int mDecorChildCount;
    private int mDefaultGutterSize;
    private boolean mDragInGutterEnabled;
    private int mDrawingOrder;
    private ArrayList<View> mDrawingOrderedChildren;
    private final Runnable mEndScrollRunnable;
    private int mExpectedAdapterCount;
    private long mFakeDragBeginTime;
    private boolean mFakeDragging;
    private boolean mFirstLayout;
    private float mFirstOffset;
    private int mFlingDistance;
    private int mGutterSize;
    private boolean mInLayout;
    private float mInitialMotionX;
    private float mInitialMotionY;
    private g mInternalPageChangeListener;
    private boolean mIsBeingDragged;
    private boolean mIsChangedConfiguration;
    private boolean mIsMouseWheelEventSupport;
    private boolean mIsScrollStarted;
    private boolean mIsUnableToDrag;
    private final ArrayList<c> mItems;
    private float mLastMotionX;
    private float mLastMotionY;
    private float mLastOffset;
    public EdgeEffect mLeftEdge;
    private int mLeftIncr;
    private Drawable mMarginDrawable;
    private int mMaximumVelocity;
    private int mMinimumVelocity;
    private i mObserver;
    private int mOffscreenPageLimit;
    private g mOnPageChangeListener;
    private List<g> mOnPageChangeListeners;
    private int mPageMargin;
    private h mPageTransformer;
    private int mPageTransformerLayerType;
    private int mPagingTouchSlop;
    private boolean mPopulatePending;
    private Parcelable mRestoredAdapterState;
    private ClassLoader mRestoredClassLoader;
    private int mRestoredCurItem;
    public EdgeEffect mRightEdge;
    private int mScaledTouchSlop;
    private int mScrollState;
    private Scroller mScroller;
    private boolean mScrollingCacheEnabled;
    private boolean mSupportLayoutDirectionForDatePicker;
    private final c mTempItem;
    private final Rect mTempRect;
    private int mTopPageBounds;
    private int mTouchSlop;
    private float mTouchSlopRatio;
    private boolean mUsePagingTouchSlopForStylus;
    private VelocityTracker mVelocityTracker;
    static final int[] LAYOUT_ATTRS = {R.attr.layout_gravity};
    private static final Comparator<c> COMPARATOR = new G4.d(5);
    private static final Interpolator sInterpolator = new E8.b(5);
    private static final l sPositionComparator = new l();

    public ViewPager(Context context) {
        super(context);
        this.mItems = new ArrayList<>();
        this.mTempItem = new c();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -3.4028235E38f;
        this.mLastOffset = Float.MAX_VALUE;
        this.mOffscreenPageLimit = 1;
        this.mDragInGutterEnabled = true;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mEndScrollRunnable = new A1.a(11, this);
        this.mScrollState = 0;
        this.mIsMouseWheelEventSupport = false;
        this.mIsChangedConfiguration = false;
        this.mScaledTouchSlop = 0;
        this.mPagingTouchSlop = 0;
        this.mUsePagingTouchSlopForStylus = false;
        this.mTouchSlopRatio = 0.5f;
        this.mLeftIncr = -1;
        this.mSupportLayoutDirectionForDatePicker = false;
        initViewPager(context, null);
    }

    private int getClientWidth() {
        return (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private int getScrollStart() {
        return l() ? MAX_SCROLL_X - getScrollX() : getScrollX();
    }

    private void setScrollingCacheEnabled(boolean z9) {
        if (this.mScrollingCacheEnabled != z9) {
            this.mScrollingCacheEnabled = z9;
        }
    }

    public final void a(boolean z9) {
        boolean z10 = this.mScrollState == 2;
        if (z10) {
            setScrollingCacheEnabled(false);
            if (!this.mScroller.isFinished()) {
                this.mScroller.abortAnimation();
                int scrollX = getScrollX();
                int scrollY = getScrollY();
                int currX = this.mScroller.getCurrX();
                int currY = this.mScroller.getCurrY();
                if (scrollX != currX || scrollY != currY) {
                    scrollTo(currX, currY);
                    if (currX != scrollX) {
                        g(currX);
                    }
                }
            }
        }
        this.mPopulatePending = false;
        for (int i5 = 0; i5 < this.mItems.size(); i5++) {
            c cVar = this.mItems.get(i5);
            if (cVar.f9592c) {
                cVar.f9592c = false;
                z10 = true;
            }
        }
        if (z10) {
            if (!z9) {
                this.mEndScrollRunnable.run();
                return;
            }
            Runnable runnable = this.mEndScrollRunnable;
            WeakHashMap weakHashMap = W.f7199a;
            postOnAnimation(runnable);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i5, int i7) {
        c cVarInfoForChild;
        if (arrayList == null) {
            return;
        }
        int size = arrayList.size();
        int descendantFocusability = getDescendantFocusability();
        if (descendantFocusability != 393216) {
            for (int i9 = 0; i9 < getChildCount(); i9++) {
                View childAt = getChildAt(i9);
                if (childAt.getVisibility() == 0 && (cVarInfoForChild = infoForChild(childAt)) != null && cVarInfoForChild.f9591b == this.mCurItem) {
                    childAt.addFocusables(arrayList, i5, i7);
                }
            }
        }
        if ((descendantFocusability != 262144 || size == arrayList.size()) && isFocusable()) {
            if ((i7 & 1) == 1 && isInTouchMode() && !isFocusableInTouchMode()) {
                return;
            }
            arrayList.add(this);
        }
    }

    public c addNewItem(int i5, int i7) {
        c cVar = new c();
        cVar.f9591b = i5;
        cVar.f9590a = this.mAdapter.instantiateItem((ViewGroup) this, i5);
        cVar.f9593d = this.mAdapter.getPageWidth(i5);
        if (i7 < 0 || i7 >= this.mItems.size()) {
            this.mItems.add(cVar);
        } else {
            this.mItems.add(i7, cVar);
        }
        return cVar;
    }

    public void addOnAdapterChangeListener(f fVar) {
        if (this.mAdapterChangeListeners == null) {
            this.mAdapterChangeListeners = new ArrayList();
        }
        this.mAdapterChangeListeners.add(fVar);
    }

    public void addOnPageChangeListener(g gVar) {
        if (this.mOnPageChangeListeners == null) {
            this.mOnPageChangeListeners = new ArrayList();
        }
        this.mOnPageChangeListeners.add(gVar);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addTouchables(ArrayList<View> arrayList) {
        c cVarInfoForChild;
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() == 0 && (cVarInfoForChild = infoForChild(childAt)) != null && cVarInfoForChild.f9591b == this.mCurItem) {
                childAt.addTouchables(arrayList);
            }
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i5, ViewGroup.LayoutParams layoutParams) {
        if (!checkLayoutParams(layoutParams)) {
            layoutParams = generateLayoutParams(layoutParams);
        }
        d dVar = (d) layoutParams;
        if (dVar != null) {
            boolean z9 = dVar.f9595a | (view.getClass().getAnnotation(b.class) != null);
            dVar.f9595a = z9;
            if (!this.mInLayout) {
                super.addView(view, i5, layoutParams);
            } else {
                if (z9) {
                    throw new IllegalStateException("Cannot add pager decor view during layout");
                }
                dVar.f9598d = true;
                addViewInLayout(view, i5, layoutParams);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00bc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean arrowScroll(int r5) {
        /*
            Method dump skipped, instruction units count: 209
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.arrowScroll(int):boolean");
    }

    public final int b(int i5, float f2, int i7, int i9) {
        int i10;
        if (Math.abs(i9) <= this.mFlingDistance || Math.abs(i7) <= this.mMinimumVelocity || AbstractC0527a.q(this.mLeftEdge) != 0.0f || AbstractC0527a.q(this.mRightEdge) != 0.0f) {
            i10 = i5 - (this.mLeftIncr * ((int) (f2 + (i5 >= this.mCurItem ? 0.4f : 0.6f))));
        } else {
            i10 = i5 - (i7 > 0 ? 0 : this.mLeftIncr);
        }
        if (this.mItems.size() <= 0) {
            return i10;
        }
        return com.bumptech.glide.c.d(i10, this.mItems.get(0).f9591b, this.mItems.get(r2.size() - 1).f9591b);
    }

    public boolean beginFakeDrag() {
        if (this.mIsBeingDragged) {
            return false;
        }
        this.mFakeDragging = true;
        setScrollState(1);
        this.mLastMotionX = 0.0f;
        this.mInitialMotionX = 0.0f;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        } else {
            velocityTracker.clear();
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 0, 0.0f, 0.0f, 0);
        this.mVelocityTracker.addMovement(motionEventObtain);
        motionEventObtain.recycle();
        this.mFakeDragBeginTime = jUptimeMillis;
        return true;
    }

    public final void c(int i5) {
        g gVar;
        g gVar2 = this.mOnPageChangeListener;
        if (gVar2 != null) {
            gVar2.onPageSelected(i5);
        }
        List<g> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i7 = 0; i7 < size; i7++) {
                try {
                    gVar = this.mOnPageChangeListeners.get(i7);
                } catch (IndexOutOfBoundsException unused) {
                    StringBuilder sbE = AbstractC0152g1.e(i7, "IndexOutOfBoundsException: Index: ", ", Size: ");
                    sbE.append(this.mOnPageChangeListeners.size());
                    Log.e(TAG, sbE.toString());
                    gVar = null;
                }
                if (gVar != null) {
                    gVar.onPageSelected(i5);
                }
            }
        }
        g gVar3 = this.mInternalPageChangeListener;
        if (gVar3 != null) {
            gVar3.onPageSelected(i5);
        }
    }

    public boolean canScroll(View view, boolean z9, int i5, int i7, int i9) {
        int i10;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int scrollX = view.getScrollX();
            int scrollY = view.getScrollY();
            for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
                View childAt = viewGroup.getChildAt(childCount);
                int i11 = i7 + scrollX;
                if (i11 >= childAt.getLeft() && i11 < childAt.getRight() && (i10 = i9 + scrollY) >= childAt.getTop() && i10 < childAt.getBottom() && canScroll(childAt, true, i5, i11 - childAt.getLeft(), i10 - childAt.getTop())) {
                    return true;
                }
            }
        }
        return z9 && view.canScrollHorizontally(-i5);
    }

    @Override // android.view.View
    public boolean canScrollHorizontally(int i5) {
        if (this.mAdapter == null) {
            return false;
        }
        int clientWidth = getClientWidth();
        int scrollX = getScrollX();
        return i5 < 0 ? scrollX > ((int) (((float) clientWidth) * this.mFirstOffset)) : i5 > 0 && scrollX < ((int) (((float) clientWidth) * this.mLastOffset));
    }

    public void canSupportLayoutDirectionForDatePicker(boolean z9) {
        this.mSupportLayoutDirectionForDatePicker = z9;
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof d) && super.checkLayoutParams(layoutParams);
    }

    public void clearOnPageChangeListeners() {
        List<g> list = this.mOnPageChangeListeners;
        if (list != null) {
            list.clear();
        }
    }

    @Override // android.view.View
    public void computeScroll() {
        this.mIsScrollStarted = true;
        if (this.mScroller.isFinished() || !this.mScroller.computeScrollOffset()) {
            a(true);
            return;
        }
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        int currX = this.mScroller.getCurrX();
        int currY = this.mScroller.getCurrY();
        if (scrollX != currX || scrollY != currY) {
            scrollTo(currX, currY);
            if (!g(currX)) {
                this.mScroller.abortAnimation();
                scrollTo(0, currY);
            }
        }
        WeakHashMap weakHashMap = W.f7199a;
        postInvalidateOnAnimation();
    }

    public final Rect d(Rect rect, View view) {
        if (rect == null) {
            rect = new Rect();
        }
        if (view == null) {
            rect.set(0, 0, 0, 0);
            return rect;
        }
        rect.left = view.getLeft();
        rect.right = view.getRight();
        rect.top = view.getTop();
        rect.bottom = view.getBottom();
        ViewParent parent = view.getParent();
        while ((parent instanceof ViewGroup) && parent != this) {
            ViewGroup viewGroup = (ViewGroup) parent;
            rect.left = viewGroup.getLeft() + rect.left;
            rect.right = viewGroup.getRight() + rect.right;
            rect.top = viewGroup.getTop() + rect.top;
            rect.bottom = viewGroup.getBottom() + rect.bottom;
            parent = viewGroup.getParent();
        }
        return rect;
    }

    public void dataSetChanged() {
        int count = this.mAdapter.getCount();
        this.mExpectedAdapterCount = count;
        boolean z9 = this.mItems.size() < (this.mOffscreenPageLimit * 2) + 1 && this.mItems.size() < count;
        int iMax = this.mCurItem;
        int i5 = 0;
        boolean z10 = false;
        while (i5 < this.mItems.size()) {
            c cVar = this.mItems.get(i5);
            int itemPosition = this.mAdapter.getItemPosition(cVar.f9590a);
            if (itemPosition != -1) {
                if (itemPosition == -2) {
                    this.mItems.remove(i5);
                    i5--;
                    if (!z10) {
                        this.mAdapter.startUpdate((ViewGroup) this);
                        z10 = true;
                    }
                    this.mAdapter.destroyItem((ViewGroup) this, cVar.f9591b, cVar.f9590a);
                    int i7 = this.mCurItem;
                    if (i7 == cVar.f9591b) {
                        iMax = Math.max(0, Math.min(i7, count - 1));
                    }
                } else {
                    int i9 = cVar.f9591b;
                    if (i9 != itemPosition) {
                        if (i9 == this.mCurItem) {
                            iMax = itemPosition;
                        }
                        cVar.f9591b = itemPosition;
                    }
                }
                z9 = true;
            }
            i5++;
        }
        if (z10) {
            this.mAdapter.finishUpdate((ViewGroup) this);
        }
        Collections.sort(this.mItems, COMPARATOR);
        if (z9) {
            int childCount = getChildCount();
            for (int i10 = 0; i10 < childCount; i10++) {
                d dVar = (d) getChildAt(i10).getLayoutParams();
                if (!dVar.f9595a) {
                    dVar.f9597c = 0.0f;
                }
            }
            setCurrentItemInternal(iMax, false, true);
            requestLayout();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || executeKeyEvent(keyEvent);
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        c cVarInfoForChild;
        if (accessibilityEvent.getEventType() == 4096) {
            return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
        }
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() == 0 && (cVarInfoForChild = infoForChild(childAt)) != null && cVarInfoForChild.f9591b == this.mCurItem && childAt.dispatchPopulateAccessibilityEvent(accessibilityEvent)) {
                return true;
            }
        }
        return false;
    }

    public float distanceInfluenceForSnapDuration(float f2) {
        return (float) Math.sin((f2 - 0.5f) * 0.47123894f);
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        a aVar;
        super.draw(canvas);
        int overScrollMode = getOverScrollMode();
        boolean zDraw = false;
        if (overScrollMode == 0 || (overScrollMode == 1 && (aVar = this.mAdapter) != null && aVar.getCount() > 1)) {
            if (!this.mLeftEdge.isFinished()) {
                int iSave = canvas.save();
                int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
                int width = getWidth();
                canvas.rotate(270.0f);
                if (l()) {
                    canvas.translate(getPaddingTop() + (-height), ((-(this.mLastOffset + 1.0f)) * width) + 1.6777216E7f);
                } else {
                    canvas.translate(getPaddingTop() + (-height), this.mFirstOffset * width);
                }
                this.mLeftEdge.setSize(height, width);
                zDraw = this.mLeftEdge.draw(canvas);
                canvas.restoreToCount(iSave);
            }
            if (!this.mRightEdge.isFinished()) {
                int iSave2 = canvas.save();
                int width2 = getWidth();
                int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
                canvas.rotate(90.0f);
                if (l()) {
                    canvas.translate(-getPaddingTop(), (this.mFirstOffset * width2) - 1.6777216E7f);
                } else {
                    canvas.translate(-getPaddingTop(), (-(this.mLastOffset + 1.0f)) * width2);
                }
                this.mRightEdge.setSize(height2, width2);
                zDraw |= this.mRightEdge.draw(canvas);
                canvas.restoreToCount(iSave2);
            }
        } else {
            this.mLeftEdge.finish();
            this.mRightEdge.finish();
        }
        if (zDraw) {
            WeakHashMap weakHashMap = W.f7199a;
            postInvalidateOnAnimation();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.mMarginDrawable;
        if (drawable == null || !drawable.isStateful()) {
            return;
        }
        drawable.setState(getDrawableState());
    }

    public final c e() {
        int i5;
        int scrollStart = getScrollStart();
        int clientWidth = getClientWidth();
        float f2 = 0.0f;
        float f7 = clientWidth > 0 ? scrollStart / clientWidth : 0.0f;
        float f9 = clientWidth > 0 ? this.mPageMargin / clientWidth : 0.0f;
        int i7 = 0;
        boolean z9 = true;
        c cVar = null;
        int i9 = -1;
        float f10 = 0.0f;
        while (i7 < this.mItems.size()) {
            c cVar2 = this.mItems.get(i7);
            if (!z9 && cVar2.f9591b != (i5 = i9 + 1)) {
                cVar2 = this.mTempItem;
                cVar2.f9594e = f2 + f10 + f9;
                cVar2.f9591b = i5;
                cVar2.f9593d = this.mAdapter.getPageWidth(i5);
                i7--;
            }
            c cVar3 = cVar2;
            f2 = cVar3.f9594e;
            float f11 = cVar3.f9593d + f2 + f9;
            if (!z9 && f7 < f2) {
                return cVar;
            }
            if (f7 < f11 || i7 == this.mItems.size() - 1) {
                return cVar3;
            }
            int i10 = cVar3.f9591b;
            float f12 = cVar3.f9593d;
            i7++;
            z9 = false;
            i9 = i10;
            f10 = f12;
            cVar = cVar3;
        }
        return cVar;
    }

    public void endFakeDrag() {
        if (!this.mFakeDragging) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        if (this.mAdapter != null) {
            VelocityTracker velocityTracker = this.mVelocityTracker;
            velocityTracker.computeCurrentVelocity(1000, this.mMaximumVelocity);
            int xVelocity = (int) velocityTracker.getXVelocity(this.mActivePointerId);
            this.mPopulatePending = true;
            int clientWidth = getClientWidth();
            int scrollX = getScrollX();
            c cVarE = e();
            setCurrentItemInternal(b(cVarE.f9591b, ((scrollX / clientWidth) - cVarE.f9594e) / cVarE.f9593d, xVelocity, (int) (this.mLastMotionX - this.mInitialMotionX)), true, true, xVelocity);
        }
        this.mIsBeingDragged = false;
        this.mIsUnableToDrag = false;
        VelocityTracker velocityTracker2 = this.mVelocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.recycle();
            this.mVelocityTracker = null;
        }
        this.mFakeDragging = false;
    }

    public boolean executeKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == 21) {
                return keyEvent.hasModifiers(2) ? pageLeft() : arrowScroll(17);
            }
            if (keyCode == 22) {
                return keyEvent.hasModifiers(2) ? pageRight() : arrowScroll(66);
            }
            if (keyCode == 61) {
                if (keyEvent.hasNoModifiers()) {
                    return arrowScroll(2);
                }
                if (keyEvent.hasModifiers(1)) {
                    return arrowScroll(1);
                }
            }
        }
        return false;
    }

    public final void f(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mActivePointerId) {
            int i5 = actionIndex == 0 ? 1 : 0;
            this.mLastMotionX = motionEvent.getX(i5);
            this.mActivePointerId = motionEvent.getPointerId(i5);
            VelocityTracker velocityTracker = this.mVelocityTracker;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    public void fakeDragBy(float f2) {
        if (!this.mFakeDragging) {
            throw new IllegalStateException("No fake drag in progress. Call beginFakeDrag first.");
        }
        if (this.mAdapter == null) {
            return;
        }
        this.mLastMotionX += f2;
        float scrollX = getScrollX() - f2;
        float clientWidth = getClientWidth();
        float f7 = this.mFirstOffset * clientWidth;
        float f9 = this.mLastOffset * clientWidth;
        c cVar = this.mItems.get(0);
        c cVar2 = this.mItems.get(r4.size() - 1);
        if (cVar.f9591b != 0) {
            f7 = cVar.f9594e * clientWidth;
        }
        if (cVar2.f9591b != this.mAdapter.getCount() - 1) {
            f9 = cVar2.f9594e * clientWidth;
        }
        if (scrollX < f7) {
            scrollX = f7;
        } else if (scrollX > f9) {
            scrollX = f9;
        }
        int i5 = (int) scrollX;
        this.mLastMotionX = (scrollX - i5) + this.mLastMotionX;
        scrollTo(i5, getScrollY());
        g(i5);
        MotionEvent motionEventObtain = MotionEvent.obtain(this.mFakeDragBeginTime, SystemClock.uptimeMillis(), 2, this.mLastMotionX, 0.0f, 0);
        this.mVelocityTracker.addMovement(motionEventObtain);
        motionEventObtain.recycle();
    }

    public final boolean g(int i5) {
        if (this.mItems.size() == 0) {
            if (this.mFirstLayout) {
                return false;
            }
            this.mCalledSuper = false;
            onPageScrolled(0, 0.0f, 0);
            if (this.mCalledSuper) {
                return false;
            }
            throw new IllegalStateException("onPageScrolled did not call superclass implementation");
        }
        if (l()) {
            i5 = MAX_SCROLL_X - i5;
        }
        c cVarE = e();
        int clientWidth = getClientWidth();
        int i7 = this.mPageMargin;
        int i9 = clientWidth + i7;
        float f2 = clientWidth;
        int i10 = cVarE.f9591b;
        float f7 = ((i5 / f2) - cVarE.f9594e) / (cVarE.f9593d + (i7 / f2));
        this.mCalledSuper = false;
        onPageScrolled(i10, f7, (int) (i9 * f7));
        if (this.mCalledSuper) {
            return true;
        }
        throw new IllegalStateException("onPageScrolled did not call superclass implementation");
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        d dVar = new d(-1, -1);
        dVar.f9597c = 0.0f;
        return dVar;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return generateDefaultLayoutParams();
    }

    public a getAdapter() {
        return this.mAdapter;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i5, int i7) {
        if (this.mDrawingOrder == 2) {
            i7 = (i5 - 1) - i7;
        }
        return ((d) this.mDrawingOrderedChildren.get(i7).getLayoutParams()).f9600f;
    }

    public int getCurrentItem() {
        return this.mCurItem;
    }

    public int getOffscreenPageLimit() {
        return this.mOffscreenPageLimit;
    }

    public int getPageMargin() {
        return this.mPageMargin;
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x010f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean h(float r14, float r15) {
        /*
            Method dump skipped, instruction units count: 292
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.h(float, float):boolean");
    }

    public final void i(int i5, int i7, int i9, int i10) {
        if (i7 <= 0 || this.mItems.isEmpty()) {
            c cVarInfoForPosition = infoForPosition(this.mCurItem);
            int iMin = (int) ((cVarInfoForPosition != null ? Math.min(cVarInfoForPosition.f9594e, this.mLastOffset) : 0.0f) * ((i5 - getPaddingLeft()) - getPaddingRight()));
            if (iMin != getScrollX()) {
                a(false);
                scrollTo(iMin, getScrollY());
                return;
            }
            return;
        }
        if (!this.mScroller.isFinished()) {
            this.mScroller.setFinalX(getCurrentItem() * getClientWidth());
            return;
        }
        int paddingLeft = ((i5 - getPaddingLeft()) - getPaddingRight()) + i9;
        float scrollStart = getScrollStart() / (((i7 - getPaddingLeft()) - getPaddingRight()) + i10);
        scrollTo(l() ? (int) (1.6777216E7f - (scrollStart * paddingLeft)) : (int) (scrollStart * paddingLeft), getScrollY());
    }

    public c infoForAnyChild(View view) {
        while (true) {
            Object parent = view.getParent();
            if (parent == this) {
                return infoForChild(view);
            }
            if (!(parent instanceof View)) {
                return null;
            }
            view = (View) parent;
        }
    }

    public c infoForChild(View view) {
        for (int i5 = 0; i5 < this.mItems.size(); i5++) {
            c cVar = this.mItems.get(i5);
            if (this.mAdapter.isViewFromObject(view, cVar.f9590a)) {
                return cVar;
            }
        }
        return null;
    }

    public c infoForPosition(int i5) {
        for (int i7 = 0; i7 < this.mItems.size(); i7++) {
            c cVar = this.mItems.get(i7);
            if (cVar.f9591b == i5) {
                return cVar;
            }
        }
        return null;
    }

    public void initViewPager(Context context, AttributeSet attributeSet) {
        setWillNotDraw(false);
        setDescendantFocusability(262144);
        setFocusable(true);
        this.mScroller = new Scroller(context, sInterpolator);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        this.mScaledTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mPagingTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        this.mMinimumVelocity = (int) (400.0f * f2);
        this.mMaximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mLeftEdge = new EdgeEffect(context);
        this.mRightEdge = new EdgeEffect(context);
        this.mFlingDistance = (int) (25.0f * f2);
        this.mCloseEnough = (int) (2.0f * f2);
        this.mDefaultGutterSize = (int) (f2 * 16.0f);
        W.i(this, new e(this));
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        M.u(this, new M3.e(this));
    }

    public boolean isDragInGutterEnabled() {
        return this.mDragInGutterEnabled;
    }

    public boolean isFakeDragging() {
        return this.mFakeDragging;
    }

    public final boolean j() {
        this.mActivePointerId = -1;
        this.mIsBeingDragged = false;
        this.mIsUnableToDrag = false;
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.mVelocityTracker = null;
        }
        this.mLeftEdge.onRelease();
        this.mRightEdge.onRelease();
        return (this.mLeftEdge.isFinished() && this.mRightEdge.isFinished()) ? false : true;
    }

    public final void k(int i5, int i7, boolean z9, boolean z10) {
        int iC;
        c cVarInfoForPosition = infoForPosition(i5);
        if (cVarInfoForPosition != null) {
            float clientWidth = getClientWidth();
            iC = (int) (com.bumptech.glide.c.c(cVarInfoForPosition.f9594e, this.mFirstOffset, this.mLastOffset) * clientWidth);
            if (l()) {
                iC = (MAX_SCROLL_X - ((int) ((clientWidth * cVarInfoForPosition.f9593d) + 0.5f))) - iC;
            }
        } else {
            iC = 0;
        }
        if (z9) {
            smoothScrollTo(iC, 0, i7);
            if (z10) {
                c(i5);
                return;
            }
            return;
        }
        if (z10) {
            c(i5);
        }
        a(false);
        scrollTo(iC, 0);
        g(iC);
    }

    public final boolean l() {
        if (this.mSupportLayoutDirectionForDatePicker) {
            WeakHashMap weakHashMap = W.f7199a;
            if (getLayoutDirection() == 1) {
                return true;
            }
        }
        return false;
    }

    public final void m() {
        if (this.mDrawingOrder != 0) {
            ArrayList<View> arrayList = this.mDrawingOrderedChildren;
            if (arrayList == null) {
                this.mDrawingOrderedChildren = new ArrayList<>();
            } else {
                arrayList.clear();
            }
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                this.mDrawingOrderedChildren.add(getChildAt(i5));
            }
            Collections.sort(this.mDrawingOrderedChildren, sPositionComparator);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mFirstLayout = true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        removeCallbacks(this.mEndScrollRunnable);
        Scroller scroller = this.mScroller;
        if (scroller != null && !scroller.isFinished()) {
            this.mScroller.abortAnimation();
        }
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        int i5;
        float f2;
        float f7;
        super.onDraw(canvas);
        if (this.mPageMargin <= 0 || this.mMarginDrawable == null || this.mItems.size() <= 0 || this.mAdapter == null) {
            return;
        }
        int scrollX = getScrollX();
        float width = getWidth();
        float f9 = this.mPageMargin / width;
        int i7 = 0;
        c cVar = this.mItems.get(0);
        float f10 = cVar.f9594e;
        int size = this.mItems.size();
        int i9 = cVar.f9591b;
        int i10 = this.mItems.get(size - 1).f9591b;
        while (i9 < i10) {
            while (true) {
                i5 = cVar.f9591b;
                if (i9 <= i5 || i7 >= size) {
                    break;
                }
                i7++;
                cVar = this.mItems.get(i7);
            }
            if (i9 == i5) {
                f2 = l() ? 1.6777216E7f - cVar.f9594e : (cVar.f9594e + cVar.f9593d) * width;
                f10 = cVar.f9594e + cVar.f9593d + f9;
            } else {
                float pageWidth = this.mAdapter.getPageWidth(i9);
                f2 = l() ? 1.6777216E7f - f10 : (f10 + pageWidth) * width;
                f10 = pageWidth + f9 + f10;
            }
            if (this.mPageMargin + f2 > scrollX) {
                f7 = f9;
                this.mMarginDrawable.setBounds(Math.round(f2), this.mTopPageBounds, Math.round(this.mPageMargin + f2), this.mBottomPageBounds);
                this.mMarginDrawable.draw(canvas);
            } else {
                f7 = f9;
            }
            if (f2 > scrollX + r2) {
                return;
            }
            i9++;
            f9 = f7;
        }
    }

    @Override // android.view.View
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (this.mIsMouseWheelEventSupport && (motionEvent.getSource() & 2) != 0 && motionEvent.getAction() == 8) {
            float axisValue = motionEvent.getAxisValue(9);
            if (axisValue > 0.0f) {
                setCurrentItem(this.mCurItem - 1, true);
                return true;
            }
            if (axisValue < 0.0f) {
                setCurrentItem(this.mCurItem + 1, true);
                return true;
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int iFindPointerIndex;
        int action = motionEvent.getAction() & 255;
        if (action == 3 || action == 1) {
            j();
            return false;
        }
        if (action != 0) {
            if (this.mIsBeingDragged) {
                return true;
            }
            if (this.mIsUnableToDrag) {
                return false;
            }
        }
        if (action == 0) {
            float x9 = motionEvent.getX();
            this.mInitialMotionX = x9;
            this.mLastMotionX = x9;
            float y4 = motionEvent.getY();
            this.mInitialMotionY = y4;
            this.mLastMotionY = y4;
            this.mActivePointerId = motionEvent.getPointerId(0);
            this.mIsUnableToDrag = false;
            this.mIsScrollStarted = true;
            if (this.mUsePagingTouchSlopForStylus) {
                this.mTouchSlop = motionEvent.isFromSource(16386) ? this.mPagingTouchSlop : this.mScaledTouchSlop;
            }
            this.mScroller.computeScrollOffset();
            if (this.mScrollState == 2 && Math.abs(this.mScroller.getFinalX() - this.mScroller.getCurrX()) > this.mCloseEnough) {
                this.mScroller.abortAnimation();
                this.mPopulatePending = false;
                populate();
                this.mIsBeingDragged = true;
                ViewParent parent = getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                setScrollState(1);
            } else if (AbstractC0527a.q(this.mLeftEdge) == 0.0f && AbstractC0527a.q(this.mRightEdge) == 0.0f) {
                a(false);
                this.mIsBeingDragged = false;
            } else {
                this.mIsBeingDragged = true;
                setScrollState(1);
                if (AbstractC0527a.q(this.mLeftEdge) != 0.0f) {
                    AbstractC0527a.R(this.mLeftEdge, 0.0f, 1.0f - (this.mLastMotionY / getHeight()));
                }
                if (AbstractC0527a.q(this.mRightEdge) != 0.0f) {
                    AbstractC0527a.R(this.mRightEdge, 0.0f, this.mLastMotionY / getHeight());
                }
            }
        } else if (action == 2) {
            int i5 = this.mActivePointerId;
            if (i5 != -1 && (iFindPointerIndex = motionEvent.findPointerIndex(i5)) != -1) {
                float x10 = motionEvent.getX(iFindPointerIndex);
                float f2 = x10 - this.mLastMotionX;
                float fAbs = Math.abs(f2);
                float y9 = motionEvent.getY(iFindPointerIndex);
                float fAbs2 = Math.abs(y9 - this.mInitialMotionY);
                if (f2 != 0.0f) {
                    float f7 = this.mLastMotionX;
                    if ((this.mDragInGutterEnabled || ((f7 >= this.mGutterSize || f2 <= 0.0f) && (f7 <= getWidth() - this.mGutterSize || f2 >= 0.0f))) && canScroll(this, false, (int) f2, (int) x10, (int) y9)) {
                        this.mLastMotionX = x10;
                        this.mLastMotionY = y9;
                        this.mIsUnableToDrag = true;
                        return false;
                    }
                }
                float f9 = this.mTouchSlop;
                if (fAbs > f9 && fAbs * this.mTouchSlopRatio > fAbs2) {
                    this.mIsBeingDragged = true;
                    ViewParent parent2 = getParent();
                    if (parent2 != null) {
                        parent2.requestDisallowInterceptTouchEvent(true);
                    }
                    setScrollState(1);
                    this.mLastMotionX = f2 > 0.0f ? this.mInitialMotionX + this.mTouchSlop : this.mInitialMotionX - this.mTouchSlop;
                    this.mLastMotionY = y9;
                    setScrollingCacheEnabled(true);
                } else if (fAbs2 > f9) {
                    this.mIsUnableToDrag = true;
                }
                if (this.mIsBeingDragged && h(x10, y9)) {
                    WeakHashMap weakHashMap = W.f7199a;
                    postInvalidateOnAnimation();
                }
            }
        } else if (action == 6) {
            f(motionEvent);
        }
        if (this.mVelocityTracker == null) {
            this.mVelocityTracker = VelocityTracker.obtain();
        }
        this.mVelocityTracker.addMovement(motionEvent);
        return this.mIsBeingDragged;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0094  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instruction units count: 310
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    public void onMeasure(int i5, int i7) {
        d dVar;
        d dVar2;
        int i9;
        setMeasuredDimension(View.getDefaultSize(0, i5), View.getDefaultSize(0, i7));
        int measuredWidth = getMeasuredWidth();
        this.mGutterSize = Math.min(measuredWidth / 10, this.mDefaultGutterSize);
        int paddingLeft = (measuredWidth - getPaddingLeft()) - getPaddingRight();
        int measuredHeight = (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom();
        int childCount = getChildCount();
        int i10 = 0;
        while (true) {
            boolean z9 = true;
            int i11 = 1073741824;
            if (i10 >= childCount) {
                break;
            }
            View childAt = getChildAt(i10);
            if (childAt.getVisibility() != 8 && (dVar2 = (d) childAt.getLayoutParams()) != null && dVar2.f9595a) {
                int i12 = dVar2.f9596b;
                int i13 = i12 & 7;
                int i14 = i12 & 112;
                boolean z10 = i14 == 48 || i14 == 80;
                if (i13 != 3 && i13 != 5) {
                    z9 = false;
                }
                int i15 = Integer.MIN_VALUE;
                if (z10) {
                    i9 = Integer.MIN_VALUE;
                    i15 = 1073741824;
                } else {
                    i9 = z9 ? 1073741824 : Integer.MIN_VALUE;
                }
                int i16 = ((ViewGroup.LayoutParams) dVar2).width;
                if (i16 != -2) {
                    if (i16 == -1) {
                        i16 = paddingLeft;
                    }
                    i15 = 1073741824;
                } else {
                    i16 = paddingLeft;
                }
                int i17 = ((ViewGroup.LayoutParams) dVar2).height;
                if (i17 == -2) {
                    i17 = measuredHeight;
                    i11 = i9;
                } else if (i17 == -1) {
                    i17 = measuredHeight;
                }
                childAt.measure(View.MeasureSpec.makeMeasureSpec(i16, i15), View.MeasureSpec.makeMeasureSpec(i17, i11));
                if (z10) {
                    measuredHeight -= childAt.getMeasuredHeight();
                } else if (z9) {
                    paddingLeft -= childAt.getMeasuredWidth();
                }
            }
            i10++;
        }
        View.MeasureSpec.makeMeasureSpec(paddingLeft, 1073741824);
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824);
        this.mInLayout = true;
        populate();
        this.mInLayout = false;
        int childCount2 = getChildCount();
        for (int i18 = 0; i18 < childCount2; i18++) {
            View childAt2 = getChildAt(i18);
            if (childAt2.getVisibility() != 8 && (dVar = (d) childAt2.getLayoutParams()) != null && !dVar.f9595a) {
                childAt2.measure(View.MeasureSpec.makeMeasureSpec((int) (paddingLeft * dVar.f9597c), 1073741824), iMakeMeasureSpec);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onPageScrolled(int r13, float r14, int r15) {
        /*
            r12 = this;
            int r0 = r12.mDecorChildCount
            r1 = 0
            r2 = 1
            if (r0 <= 0) goto L6c
            int r0 = r12.getScrollX()
            int r3 = r12.getPaddingLeft()
            int r4 = r12.getPaddingRight()
            int r5 = r12.getWidth()
            int r6 = r12.getChildCount()
            r7 = r1
        L1b:
            if (r7 >= r6) goto L6c
            android.view.View r8 = r12.getChildAt(r7)
            android.view.ViewGroup$LayoutParams r9 = r8.getLayoutParams()
            androidx.viewpager.widget.d r9 = (androidx.viewpager.widget.d) r9
            boolean r10 = r9.f9595a
            if (r10 != 0) goto L2c
            goto L69
        L2c:
            int r9 = r9.f9596b
            r9 = r9 & 7
            if (r9 == r2) goto L50
            r10 = 3
            if (r9 == r10) goto L4a
            r10 = 5
            if (r9 == r10) goto L3a
            r9 = r3
            goto L5d
        L3a:
            int r9 = r5 - r4
            int r10 = r8.getMeasuredWidth()
            int r9 = r9 - r10
            int r10 = r8.getMeasuredWidth()
            int r4 = r4 + r10
        L46:
            r11 = r9
            r9 = r3
            r3 = r11
            goto L5d
        L4a:
            int r9 = r8.getWidth()
            int r9 = r9 + r3
            goto L5d
        L50:
            int r9 = r8.getMeasuredWidth()
            int r9 = r5 - r9
            int r9 = r9 / 2
            int r9 = java.lang.Math.max(r9, r3)
            goto L46
        L5d:
            int r3 = r3 + r0
            int r10 = r8.getLeft()
            int r3 = r3 - r10
            if (r3 == 0) goto L68
            r8.offsetLeftAndRight(r3)
        L68:
            r3 = r9
        L69:
            int r7 = r7 + 1
            goto L1b
        L6c:
            androidx.viewpager.widget.g r0 = r12.mOnPageChangeListener
            if (r0 == 0) goto L73
            r0.onPageScrolled(r13, r14, r15)
        L73:
            java.util.List<androidx.viewpager.widget.g> r0 = r12.mOnPageChangeListeners
            if (r0 == 0) goto La9
            int r0 = r0.size()
        L7b:
            if (r1 >= r0) goto La9
            java.util.List<androidx.viewpager.widget.g> r3 = r12.mOnPageChangeListeners     // Catch: java.lang.IndexOutOfBoundsException -> L86
            java.lang.Object r3 = r3.get(r1)     // Catch: java.lang.IndexOutOfBoundsException -> L86
            androidx.viewpager.widget.g r3 = (androidx.viewpager.widget.g) r3     // Catch: java.lang.IndexOutOfBoundsException -> L86
            goto La1
        L86:
            java.lang.String r3 = "IndexOutOfBoundsException: Index: "
            java.lang.String r4 = ", Size: "
            java.lang.StringBuilder r3 = androidx.appcompat.widget.AbstractC0152g1.e(r1, r3, r4)
            java.util.List<androidx.viewpager.widget.g> r4 = r12.mOnPageChangeListeners
            int r4 = r4.size()
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r4 = "ViewPager"
            android.util.Log.e(r4, r3)
            r3 = 0
        La1:
            if (r3 == 0) goto La6
            r3.onPageScrolled(r13, r14, r15)
        La6:
            int r1 = r1 + 1
            goto L7b
        La9:
            androidx.viewpager.widget.g r0 = r12.mInternalPageChangeListener
            if (r0 == 0) goto Lb0
            r0.onPageScrolled(r13, r14, r15)
        Lb0:
            r12.mCalledSuper = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onPageScrolled(int, float, int):void");
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i5, Rect rect) {
        int i7;
        int i9;
        int i10;
        c cVarInfoForChild;
        int childCount = getChildCount();
        if ((i5 & 2) != 0) {
            i9 = childCount;
            i7 = 0;
            i10 = 1;
        } else {
            i7 = childCount - 1;
            i9 = -1;
            i10 = -1;
        }
        while (i7 != i9) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() == 0 && (cVarInfoForChild = infoForChild(childAt)) != null && cVarInfoForChild.f9591b == this.mCurItem && childAt.requestFocus(i5, rect)) {
                return true;
            }
            i7 += i10;
        }
        return false;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof j)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        j jVar = (j) parcelable;
        super.onRestoreInstanceState(jVar.getSuperState());
        a aVar = this.mAdapter;
        ClassLoader classLoader = jVar.f9605g;
        if (aVar != null) {
            aVar.restoreState(jVar.f9604f, classLoader);
            setCurrentItemInternal(jVar.f9603e, false, true);
        } else {
            this.mRestoredCurItem = jVar.f9603e;
            this.mRestoredAdapterState = jVar.f9604f;
            this.mRestoredClassLoader = classLoader;
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i5) {
        super.onRtlPropertiesChanged(i5);
        if (this.mSupportLayoutDirectionForDatePicker) {
            this.mLeftIncr = i5 == 0 ? -1 : 1;
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        j jVar = new j(super.onSaveInstanceState());
        jVar.f9603e = this.mCurItem;
        a aVar = this.mAdapter;
        if (aVar != null) {
            jVar.f9604f = aVar.saveState();
        }
        return jVar;
    }

    @Override // android.view.View
    public void onSizeChanged(int i5, int i7, int i9, int i10) {
        super.onSizeChanged(i5, i7, i9, i10);
        if (i5 != i9) {
            int i11 = this.mPageMargin;
            i(i5, i9, i11, i11);
            if (this.mPageMargin > 0) {
                setCurrentItemInternal(this.mCurItem, false, true, 0);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x00ea  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r10) {
        /*
            Method dump skipped, instruction units count: 439
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public boolean pageLeft() {
        int i5 = this.mCurItem;
        if (i5 <= 0) {
            return false;
        }
        setCurrentItem(i5 + this.mLeftIncr, true);
        return true;
    }

    public boolean pageRight() {
        a aVar = this.mAdapter;
        if (aVar == null || this.mCurItem >= aVar.getCount() - 1) {
            return false;
        }
        setCurrentItem(this.mCurItem - this.mLeftIncr, true);
        return true;
    }

    public void populate() {
        populate(this.mCurItem);
    }

    public void removeOnAdapterChangeListener(f fVar) {
        List<f> list = this.mAdapterChangeListeners;
        if (list != null) {
            list.remove(fVar);
        }
    }

    public void removeOnPageChangeListener(g gVar) {
        List<g> list = this.mOnPageChangeListeners;
        if (list != null) {
            list.remove(gVar);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        if (this.mInLayout) {
            removeViewInLayout(view);
        } else {
            super.removeView(view);
        }
    }

    public float seslGetDefaultTouchSlopRatio() {
        return 0.5f;
    }

    public int seslGetTouchSlop() {
        return this.mScaledTouchSlop;
    }

    public float seslGetTouchSlopRatio() {
        return this.mTouchSlopRatio;
    }

    public boolean seslIsPagingTouchSlopForStylusEnabled() {
        return this.mUsePagingTouchSlopForStylus;
    }

    public void seslSetConfigurationChanged(boolean z9) {
        this.mIsChangedConfiguration = z9;
    }

    public void seslSetPagingTouchSlopForStylus(boolean z9) {
        this.mUsePagingTouchSlopForStylus = z9;
    }

    public void seslSetSupportedMouseWheelEvent(boolean z9) {
        this.mIsMouseWheelEventSupport = z9;
    }

    public void seslSetTouchSlop(int i5) {
        this.mScaledTouchSlop = i5;
    }

    public void seslSetTouchSlopRatio(float f2) {
        this.mTouchSlopRatio = f2;
    }

    public void setAdapter(a aVar) {
        a aVar2 = this.mAdapter;
        if (aVar2 != null) {
            aVar2.setViewPagerObserver(null);
            this.mAdapter.startUpdate((ViewGroup) this);
            for (int i5 = 0; i5 < this.mItems.size(); i5++) {
                c cVar = this.mItems.get(i5);
                this.mAdapter.destroyItem((ViewGroup) this, cVar.f9591b, cVar.f9590a);
            }
            this.mAdapter.finishUpdate((ViewGroup) this);
            this.mItems.clear();
            int i7 = 0;
            while (i7 < getChildCount()) {
                if (!((d) getChildAt(i7).getLayoutParams()).f9595a) {
                    removeViewAt(i7);
                    i7--;
                }
                i7++;
            }
            this.mCurItem = 0;
            scrollTo(0, 0);
        }
        a aVar3 = this.mAdapter;
        this.mAdapter = aVar;
        this.mExpectedAdapterCount = 0;
        if (aVar != null) {
            if (this.mObserver == null) {
                this.mObserver = new i(this);
            }
            this.mAdapter.setViewPagerObserver(this.mObserver);
            this.mPopulatePending = false;
            boolean z9 = this.mFirstLayout;
            this.mFirstLayout = true;
            this.mExpectedAdapterCount = this.mAdapter.getCount();
            if (this.mRestoredCurItem >= 0) {
                this.mAdapter.restoreState(this.mRestoredAdapterState, this.mRestoredClassLoader);
                setCurrentItemInternal(this.mRestoredCurItem, false, true);
                this.mRestoredCurItem = -1;
                this.mRestoredAdapterState = null;
                this.mRestoredClassLoader = null;
            } else if (z9) {
                requestLayout();
            } else {
                populate();
            }
        }
        List<f> list = this.mAdapterChangeListeners;
        if (list == null || list.isEmpty()) {
            return;
        }
        int size = this.mAdapterChangeListeners.size();
        for (int i9 = 0; i9 < size; i9++) {
            this.mAdapterChangeListeners.get(i9).onAdapterChanged(this, aVar3, aVar);
        }
    }

    public void setCurrentItem(int i5) {
        this.mPopulatePending = false;
        setCurrentItemInternal(i5, !this.mFirstLayout, false);
    }

    public void setCurrentItemInternal(int i5, boolean z9, boolean z10) {
        setCurrentItemInternal(i5, z9, z10, 0);
    }

    public void setDragInGutterEnabled(boolean z9) {
        this.mDragInGutterEnabled = z9;
    }

    public g setInternalPageChangeListener(g gVar) {
        g gVar2 = this.mInternalPageChangeListener;
        this.mInternalPageChangeListener = gVar;
        return gVar2;
    }

    public void setOffscreenPageLimit(int i5) {
        if (i5 < 1) {
            Log.w(TAG, "Requested offscreen page limit " + i5 + " too small; defaulting to 1");
            i5 = 1;
        }
        if (i5 != this.mOffscreenPageLimit) {
            this.mOffscreenPageLimit = i5;
            populate();
        }
    }

    @Deprecated
    public void setOnPageChangeListener(g gVar) {
        this.mOnPageChangeListener = gVar;
    }

    public void setPageMargin(int i5) {
        int i7 = this.mPageMargin;
        this.mPageMargin = i5;
        int width = getWidth();
        i(width, width, i5, i7);
        requestLayout();
    }

    public void setPageMarginDrawable(Drawable drawable) {
        this.mMarginDrawable = drawable;
        if (drawable != null) {
            refreshDrawableState();
        }
        setWillNotDraw(drawable == null);
        invalidate();
    }

    public void setPageTransformer(boolean z9, h hVar) {
        setPageTransformer(z9, hVar, 2);
    }

    public void setScrollState(int i5) {
        g gVar;
        if (this.mScrollState == i5) {
            return;
        }
        this.mScrollState = i5;
        g gVar2 = this.mOnPageChangeListener;
        if (gVar2 != null) {
            gVar2.onPageScrollStateChanged(i5);
        }
        List<g> list = this.mOnPageChangeListeners;
        if (list != null) {
            int size = list.size();
            for (int i7 = 0; i7 < size; i7++) {
                try {
                    gVar = this.mOnPageChangeListeners.get(i7);
                } catch (IndexOutOfBoundsException unused) {
                    StringBuilder sbE = AbstractC0152g1.e(i7, "IndexOutOfBoundsException: Index: ", ", Size: ");
                    sbE.append(this.mOnPageChangeListeners.size());
                    Log.e(TAG, sbE.toString());
                    gVar = null;
                }
                if (gVar != null) {
                    gVar.onPageScrollStateChanged(i5);
                }
            }
        }
        g gVar3 = this.mInternalPageChangeListener;
        if (gVar3 != null) {
            gVar3.onPageScrollStateChanged(i5);
        }
    }

    public void smoothScrollTo(int i5, int i7, int i9) {
        int scrollX;
        if (getChildCount() == 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        Scroller scroller = this.mScroller;
        if (scroller == null || scroller.isFinished()) {
            scrollX = getScrollX();
        } else {
            scrollX = this.mIsScrollStarted ? this.mScroller.getCurrX() : this.mScroller.getStartX();
            this.mScroller.abortAnimation();
            setScrollingCacheEnabled(false);
        }
        int i10 = scrollX;
        int scrollY = getScrollY();
        int i11 = i5 - i10;
        int i12 = i7 - scrollY;
        if (i11 == 0 && i12 == 0) {
            a(false);
            populate();
            setScrollState(0);
            return;
        }
        setScrollingCacheEnabled(true);
        setScrollState(2);
        int clientWidth = getClientWidth();
        int i13 = clientWidth / 2;
        float f2 = clientWidth;
        float f7 = i13;
        float fDistanceInfluenceForSnapDuration = (distanceInfluenceForSnapDuration(Math.min(1.0f, (Math.abs(i11) * 1.0f) / f2)) * f7) + f7;
        int iAbs = Math.abs(i9);
        int iMin = Math.min(iAbs > 0 ? Math.round(Math.abs(fDistanceInfluenceForSnapDuration / iAbs) * 1000.0f) * 4 : (int) (((Math.abs(i11) / ((this.mAdapter.getPageWidth(this.mCurItem) * f2) + this.mPageMargin)) + 1.0f) * 100.0f), MAX_SETTLE_DURATION);
        this.mIsScrollStarted = false;
        Scroller scroller2 = this.mScroller;
        if (scroller2 != null) {
            scroller2.startScroll(i10, scrollY, i11, i12, iMin);
        }
        WeakHashMap weakHashMap = W.f7199a;
        postInvalidateOnAnimation();
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mMarginDrawable;
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        d dVar = new d(context, attributeSet);
        dVar.f9597c = 0.0f;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, LAYOUT_ATTRS);
        dVar.f9596b = typedArrayObtainStyledAttributes.getInteger(0, 48);
        typedArrayObtainStyledAttributes.recycle();
        return dVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0072, code lost:
    
        r9 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00d6 A[PHI: r8 r11 r15
      0x00d6: PHI (r8v14 int) = (r8v13 int), (r8v4 int), (r8v17 int) binds: [B:68:0x00fa, B:65:0x00e4, B:57:0x00cb] A[DONT_GENERATE, DONT_INLINE]
      0x00d6: PHI (r11v27 int) = (r11v1 int), (r11v26 int), (r11v30 int) binds: [B:68:0x00fa, B:65:0x00e4, B:57:0x00cb] A[DONT_GENERATE, DONT_INLINE]
      0x00d6: PHI (r15v6 float) = (r15v4 float), (r15v5 float), (r15v3 float) binds: [B:68:0x00fa, B:65:0x00e4, B:57:0x00cb] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0163 A[PHI: r3 r12
      0x0163: PHI (r3v42 float) = (r3v40 float), (r3v41 float), (r3v39 float) binds: [B:102:0x0190, B:99:0x0176, B:92:0x0158] A[DONT_GENERATE, DONT_INLINE]
      0x0163: PHI (r12v10 int) = (r12v8 int), (r12v9 int), (r12v7 int) binds: [B:102:0x0190, B:99:0x0176, B:92:0x0158] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void populate(int r18) {
        /*
            Method dump skipped, instruction units count: 931
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.viewpager.widget.ViewPager.populate(int):void");
    }

    public void setCurrentItemInternal(int i5, boolean z9, boolean z10, int i7) {
        a aVar = this.mAdapter;
        if (aVar == null || aVar.getCount() <= 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (!z10 && this.mCurItem == i5 && this.mItems.size() != 0) {
            setScrollingCacheEnabled(false);
            return;
        }
        if (i5 < 0) {
            i5 = 0;
        } else if (i5 >= this.mAdapter.getCount()) {
            i5 = this.mAdapter.getCount() - 1;
        }
        int i9 = this.mOffscreenPageLimit;
        int i10 = this.mCurItem;
        if (i5 > i10 + i9 || i5 < i10 - i9) {
            for (int i11 = 0; i11 < this.mItems.size(); i11++) {
                this.mItems.get(i11).f9592c = true;
            }
        }
        boolean z11 = this.mCurItem != i5;
        if (!this.mFirstLayout) {
            populate(i5);
            k(i5, i7, z9, z11);
        } else {
            this.mCurItem = i5;
            if (z11) {
                c(i5);
            }
            requestLayout();
        }
    }

    public void setPageTransformer(boolean z9, h hVar, int i5) {
        boolean z10 = hVar != null;
        setChildrenDrawingOrderEnabled(z10);
        if (z10) {
            this.mDrawingOrder = z9 ? 2 : 1;
            this.mPageTransformerLayerType = i5;
        } else {
            this.mDrawingOrder = 0;
        }
        if (z10) {
            populate();
        }
    }

    public void setCurrentItem(int i5, boolean z9) {
        this.mPopulatePending = false;
        setCurrentItemInternal(i5, z9, false);
    }

    public void setPageMarginDrawable(int i5) {
        setPageMarginDrawable(B.a.b(getContext(), i5));
    }

    public ViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mItems = new ArrayList<>();
        this.mTempItem = new c();
        this.mTempRect = new Rect();
        this.mRestoredCurItem = -1;
        this.mRestoredAdapterState = null;
        this.mRestoredClassLoader = null;
        this.mFirstOffset = -3.4028235E38f;
        this.mLastOffset = Float.MAX_VALUE;
        this.mOffscreenPageLimit = 1;
        this.mDragInGutterEnabled = true;
        this.mActivePointerId = -1;
        this.mFirstLayout = true;
        this.mEndScrollRunnable = new A1.a(11, this);
        this.mScrollState = 0;
        this.mIsMouseWheelEventSupport = false;
        this.mIsChangedConfiguration = false;
        this.mScaledTouchSlop = 0;
        this.mPagingTouchSlop = 0;
        this.mUsePagingTouchSlopForStylus = false;
        this.mTouchSlopRatio = 0.5f;
        this.mLeftIncr = -1;
        this.mSupportLayoutDirectionForDatePicker = false;
        initViewPager(context, attributeSet);
    }
}
