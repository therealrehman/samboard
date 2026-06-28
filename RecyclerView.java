package androidx.recyclerview.widget;

import android.R;
import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Trace;
import android.provider.Settings;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.EdgeEffect;
import android.widget.ImageView;
import android.widget.OverScroller;
import android.widget.SectionIndexer;
import androidx.appcompat.widget.AbstractC0152g1;
import androidx.core.view.C0223o;
import androidx.core.view.InterfaceC0222n;
import androidx.core.view.InterfaceC0224p;
import f.AbstractC0510a;
import f6.AbstractC0527a;
import h6.AbstractC0582a;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;
import k.C0634c;
import m7.AbstractC0752G;

/* JADX INFO: loaded from: classes.dex */
public class RecyclerView extends ViewGroup implements InterfaceC0222n {
    static final int DEFAULT_ORIENTATION = 1;
    static final boolean DISPATCH_TEMP_DETACH = false;
    private static final float FLING_DESTRETCH_FACTOR = 4.0f;
    private static final int FOCUS_MOVE_DOWN = 1;
    private static final int FOCUS_MOVE_FULL_DOWN = 3;
    private static final int FOCUS_MOVE_FULL_UP = 2;
    private static final int FOCUS_MOVE_UP = 0;
    static final long FOREVER_NS = Long.MAX_VALUE;
    private static final float FRAME_LATENCY_LIMIT = 16.66f;
    private static final int GO_TO_TOP_HIDE = 1500;
    private static final int GTT_STATE_NONE = 0;
    private static final int GTT_STATE_PRESSED = 2;
    private static final int GTT_STATE_SHOWN = 1;
    public static final int HORIZONTAL = 0;
    private static final int HOVERSCROLL_DELAY = 0;
    private static final int HOVERSCROLL_DOWN = 2;
    private static final int HOVERSCROLL_HEIGHT_BOTTOM_DP = 25;
    private static final int HOVERSCROLL_HEIGHT_TOP_DP = 25;
    private static final int HOVERSCROLL_UP = 1;
    private static final float INFLEXION = 0.35f;
    private static final int INVALID_POINTER = -1;
    public static final int INVALID_TYPE = -1;
    private static final int LASTITEM_ADD_REMOVE_DURATION = 330;
    private static final Class<?>[] LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE;
    private static final Interpolator LINEAR_INTERPOLATOR;
    static final int MAX_SCROLL_DURATION = 2000;
    private static final int MOTION_EVENT_ACTION_PEN_DOWN = 211;
    private static final int MOTION_EVENT_ACTION_PEN_MOVE = 213;
    private static final int MOTION_EVENT_ACTION_PEN_UP = 212;
    private static final int MSG_HOVERSCROLL_MOVE = 0;
    public static final long NO_ID = -1;
    public static final int NO_POSITION = -1;
    private static final float SCROLL_FRICTION = 0.015f;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private static final int STATISTICS_MAX_COUNT = 5;
    static final String TAG = "SeslRecyclerView";
    public static final int TOUCH_SLOP_DEFAULT = 0;
    public static final int TOUCH_SLOP_PAGING = 1;
    static final String TRACE_BIND_VIEW_TAG = "RV OnBindView";
    static final String TRACE_CREATE_VIEW_TAG = "RV CreateView";
    private static final String TRACE_HANDLE_ADAPTER_UPDATES_TAG = "RV PartialInvalidate";
    static final String TRACE_NESTED_PREFETCH_TAG = "RV Nested Prefetch";
    private static final String TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG = "RV FullInvalidate";
    private static final String TRACE_ON_LAYOUT_TAG = "RV OnLayout";
    static final String TRACE_PREFETCH_TAG = "RV Prefetch";
    static final String TRACE_SCROLL_TAG = "RV Scroll";
    public static final int UNDEFINED_DURATION = Integer.MIN_VALUE;
    static final boolean VERBOSE_TRACING = false;
    public static final int VERTICAL = 1;
    static boolean sDebugAssertionsEnabled = false;
    static final S0 sDefaultEdgeEffectFactory;
    static final Interpolator sQuinticInterpolator;
    static boolean sVerboseLoggingEnabled = false;
    private final int ON_ABSORB_VELOCITY;
    X0 mAccessibilityDelegate;
    private final AccessibilityManager mAccessibilityManager;
    AbstractC0341j0 mAdapter;
    C0324b mAdapterHelper;
    boolean mAdapterUpdateDuringMeasure;
    private Animator.AnimatorListener mAnimListener;
    private int mAnimatedBlackTop;
    private final Runnable mAutoHide;
    int mBlackTop;
    private EdgeEffect mBottomGlow;
    Rect mChildBound;
    private InterfaceC0347m0 mChildDrawingOrderCallback;
    C0342k mChildHelper;
    boolean mClipToPadding;
    private View mCloseChildByBottom;
    private View mCloseChildByTop;
    private int mCloseChildPositionByBottom;
    private int mCloseChildPositionByTop;
    private Context mContext;
    boolean mDataSetHasChangedAfterLayout;
    boolean mDispatchItemsChangedEvent;
    private int mDispatchScrollCounter;
    private int mDistanceFromCloseChildBottom;
    private int mDistanceFromCloseChildTop;
    private boolean mDrawHorizontalPadding;
    private boolean mDrawLastRoundedCorner;
    private boolean mDrawRect;
    private boolean mDrawReverse;
    private int mEatenAccessibilityChangeFlags;
    private boolean mEdgeEffectByDragging;
    private AbstractC0349n0 mEdgeEffectFactory;
    boolean mEnableFastScroller;
    private boolean mEnableGoToTop;
    private int mExtraPaddingInBottomHoverArea;
    private int mExtraPaddingInTopHoverArea;
    private b1 mFastScroller;
    private K0 mFastScrollerEventListener;
    boolean mFirstLayoutComplete;
    private float mFrameLatency;
    F mGapWorker;
    private final Runnable mGoToToFadeInRunnable;
    private final Runnable mGoToToFadeOutRunnable;
    private int mGoToTopBottomPadding;
    private final Runnable mGoToTopEdgeEffectRunnable;
    private int mGoToTopElevation;
    private ValueAnimator mGoToTopFadeInAnimator;
    private ValueAnimator mGoToTopFadeOutAnimator;
    private Drawable mGoToTopImage;
    private int mGoToTopImmersiveBottomPadding;
    private int mGoToTopLastState;
    private Rect mGoToTopRect;
    private int mGoToTopSize;
    private int mGoToTopState;
    private ImageView mGoToTopView;
    private boolean mGoToToping;
    boolean mHasFixedSize;
    private boolean mHasNestedScrollRange;
    private boolean mHoverAreaEnter;
    private int mHoverBottomAreaHeight;
    private Handler mHoverHandler;
    private long mHoverRecognitionCurrentTime;
    private long mHoverRecognitionDurationTime;
    private long mHoverRecognitionStartTime;
    int[] mHoverScrollArrows;
    private int mHoverScrollDirection;
    private boolean mHoverScrollEnable;
    private int mHoverScrollSpeed;
    private long mHoverScrollStartTime;
    private boolean mHoverScrollStateChanged;
    private int mHoverScrollStateForListener;
    private long mHoverScrollTimeInterval;
    private int mHoverTopAreaHeight;
    private boolean mIgnoreMotionEventTillDown;
    private C0353p0 mIndexTip;
    private boolean mIndexTipEnabled;
    private int mInitialTopOffsetOfScreen;
    private int mInitialTouchX;
    private int mInitialTouchY;
    private int mInterceptRequestLayoutDepth;
    private C0 mInterceptingOnItemTouchListener;
    private boolean mIsArrowKeyPressed;
    boolean mIsAttached;
    private boolean mIsCloseChildSetted;
    private boolean mIsCtrlKeyPressed;
    private boolean mIsCtrlMultiSelection;
    private boolean mIsEdgeEffectEnabled;
    private boolean mIsEnabledPaddingInHoverScroll;
    private boolean mIsFirstMultiSelectionMove;
    private boolean mIsFirstPenMoveEvent;
    private boolean mIsHoverOverscrolled;
    private boolean mIsLongPressMultiSelection;
    private boolean mIsNeedCheckLatency;
    private boolean mIsNeedPenSelectIconSet;
    private boolean mIsNeedPenSelection;
    private boolean mIsPenDragBlockEnabled;
    private boolean mIsPenHovered;
    private boolean mIsPenPressed;
    private boolean mIsPenSelectPointerSetted;
    private boolean mIsPenSelectionEnabled;
    private boolean mIsRecoilEnabled;
    private final boolean mIsRecoilSupported;
    private boolean mIsSendHoverScrollState;
    private boolean mIsSetOnlyAddAnim;
    private boolean mIsSetOnlyRemoveAnim;
    private boolean mIsSkipMoveEvent;
    AbstractC0358s0 mItemAnimator;
    private f.d mItemAnimatorHolder;
    private InterfaceC0355q0 mItemAnimatorListener;
    private Runnable mItemAnimatorRunner;
    private C0360t0 mItemBackgroundHolder;
    final ArrayList<AbstractC0362u0> mItemDecorations;
    boolean mItemsAddedOrRemoved;
    boolean mItemsChanged;
    private int mLastAutoMeasureNonExactMeasuredHeight;
    private int mLastAutoMeasureNonExactMeasuredWidth;
    private boolean mLastAutoMeasureSkippedDueToExact;
    private int mLastBlackTop;
    private ValueAnimator mLastItemAddRemoveAnim;
    private int mLastItemAnimTop;
    private int mLastTouchX;
    private int mLastTouchY;
    AbstractC0370y0 mLayout;
    private int mLayoutOrScrollCounter;
    boolean mLayoutSuppressed;
    boolean mLayoutWasDefered;
    private EdgeEffect mLeftGlow;
    Rect mListPadding;
    private L0 mLongPressMultiSelectionListener;
    private int mMaxFlingVelocity;
    private int mMinFlingVelocity;
    private final int[] mMinMaxLayoutPositions;
    private final int mMotionEventUpPendingFlag;
    private boolean mNeedsHoverScroll;
    private final int[] mNestedOffsets;
    private int mNestedScrollRange;
    private boolean mNewTextViewHoverState;
    private final I0 mObserver;
    private int mOldHoverScrollDirection;
    private boolean mOldTextViewHoverState;
    private List<A0> mOnChildAttachStateListeners;
    private B0 mOnFlingListener;
    private M0 mOnGoToTopClickListener;
    private final ArrayList<C0> mOnItemTouchListeners;
    private N0 mOnMultiSelectedListener;
    private int mPagingTouchSlop;
    private int mPenDistanceFromTrackedChildTop;
    private int mPenDragBlockBottom;
    private Drawable mPenDragBlockImage;
    private int mPenDragBlockLeft;
    private Rect mPenDragBlockRect;
    private int mPenDragBlockRight;
    private int mPenDragBlockTop;
    private int mPenDragEndX;
    private int mPenDragEndY;
    private long mPenDragScrollTimeInterval;
    private ArrayList<Integer> mPenDragSelectedItemArray;
    private int mPenDragSelectedViewPosition;
    private int mPenDragStartX;
    private int mPenDragStartY;
    private View mPenTrackedChild;
    private int mPenTrackedChildPosition;
    final List<V0> mPendingAccessibilityImportanceChange;
    J0 mPendingSavedState;
    private final float mPhysicalCoef;
    private float mPointerIconRotation;
    boolean mPostedAnimatorRunner;
    D mPrefetchRegistry;
    private boolean mPreserveFocusAfterLayout;
    private boolean mPreventFirstGlow;
    private int mRectColor;
    private Paint mRectPaint;
    final G0 mRecycler;
    H0 mRecyclerListener;
    final List<H0> mRecyclerListeners;
    private final int[] mRecyclerViewOffsets;
    private int mRemainNestedScrollRange;
    final int[] mReusableIntPair;
    private EdgeEffect mRightGlow;
    private View mRootViewCheckForDialog;
    private C0634c mRoundedCorner;
    private float mScaledHorizontalScrollFactor;
    private float mScaledVerticalScrollFactor;
    private D0 mScrollListener;
    private List<D0> mScrollListeners;
    private final int[] mScrollOffset;
    private int mScrollPointerId;
    private int mScrollState;
    private int mScrollbarBottomPadding;
    private int mScrollbarTopPadding;
    private C0223o mScrollingChildHelper;
    Drawable mSelector;
    Rect mSelectorRect;
    private int mSeslOverlayFeatureHeight;
    private int mShowFadeOutGTT;
    private boolean mSizeChange;
    final R0 mState;
    final Rect mTempRect;
    private final Rect mTempRect2;
    final RectF mTempRectF;
    private EdgeEffect mTopGlow;
    private int mTouchSlop;
    private int mTouchSlop2;
    final Runnable mUpdateChildViewsRunnable;
    private boolean mUsePagingTouchSlopForStylus;
    private VelocityTracker mVelocityTracker;
    final U0 mViewFlinger;
    private final n1 mViewInfoProcessCallback;
    final o1 mViewInfoStore;
    private final int[] mWindowOffsets;
    private static final int[] NESTED_SCROLLING_ATTRS = {R.attr.nestedScrollingEnabled};
    private static final float DECELERATION_RATE = (float) (Math.log(0.78d) / Math.log(0.9d));
    static final boolean FORCE_INVALIDATE_DISPLAY_LIST = false;
    static final boolean ALLOW_SIZE_IN_UNSPECIFIED_SPEC = true;
    static final boolean POST_UPDATES_ON_ANIMATION = true;
    static final boolean ALLOW_THREAD_GAP_WORK = true;
    private static final boolean FORCE_ABS_FOCUS_SEARCH_DIRECTION = false;
    private static final boolean IGNORE_DETACHED_FOCUSED_CHILD = false;
    private static float HOVERSCROLL_SPEED = 10.0f;

    static {
        Class cls = Integer.TYPE;
        LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE = new Class[]{Context.class, AttributeSet.class, cls, cls};
        LINEAR_INTERPOLATOR = new LinearInterpolator();
        sQuinticInterpolator = new E8.b(4);
        sDefaultEdgeEffectFactory = new S0();
    }

    public RecyclerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.samsung.android.keyscafe.R.attr.recyclerViewStyle);
    }

    public static void access$300(RecyclerView recyclerView) {
        if (recyclerView.mGoToTopFadeOutAnimator.isRunning()) {
            return;
        }
        if (recyclerView.mGoToTopFadeInAnimator.isRunning()) {
            recyclerView.mGoToTopFadeOutAnimator.cancel();
        }
        recyclerView.mGoToTopFadeOutAnimator.setFloatValues(recyclerView.mGoToTopView.getAlpha(), 0.0f);
        recyclerView.mGoToTopFadeOutAnimator.start();
    }

    public static void access$400(RecyclerView recyclerView) {
        if (recyclerView.mGoToTopFadeInAnimator.isRunning()) {
            return;
        }
        if (recyclerView.mGoToTopFadeOutAnimator.isRunning()) {
            recyclerView.mGoToTopFadeOutAnimator.cancel();
        }
        if (recyclerView.mGoToTopImage.getAlpha() < 255) {
            recyclerView.mGoToTopImage.setAlpha(255);
        }
        recyclerView.mGoToTopFadeInAnimator.setFloatValues(recyclerView.mGoToTopView.getAlpha(), 1.0f);
        recyclerView.mGoToTopFadeInAnimator.start();
    }

    public static boolean access$4000(RecyclerView recyclerView) {
        int childCount = recyclerView.getChildCount();
        if (childCount == 0) {
            return true;
        }
        return childCount == recyclerView.mAdapter.getItemCount() && recyclerView.getChildAt(0).getTop() >= recyclerView.mListPadding.top && recyclerView.getChildAt(childCount - 1).getBottom() <= recyclerView.getHeight() - recyclerView.mListPadding.bottom;
    }

    public static void clearNestedRecyclerViewIfNotNested(V0 v02) {
        WeakReference<RecyclerView> weakReference = v02.mNestedRecyclerView;
        if (weakReference != null) {
            RecyclerView recyclerView = weakReference.get();
            while (recyclerView != null) {
                if (recyclerView == v02.itemView) {
                    return;
                }
                Object parent = recyclerView.getParent();
                recyclerView = parent instanceof View ? (View) parent : null;
            }
            v02.mNestedRecyclerView = null;
        }
    }

    public static RecyclerView findNestedRecyclerView(View view) {
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        if (view instanceof RecyclerView) {
            return (RecyclerView) view;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            RecyclerView recyclerViewFindNestedRecyclerView = findNestedRecyclerView(viewGroup.getChildAt(i5));
            if (recyclerViewFindNestedRecyclerView != null) {
                return recyclerViewFindNestedRecyclerView;
            }
        }
        return null;
    }

    public static int g(int i5, EdgeEffect edgeEffect, EdgeEffect edgeEffect2, int i7) {
        if (i5 > 0 && edgeEffect != null && AbstractC0527a.q(edgeEffect) != 0.0f) {
            int iRound = Math.round(AbstractC0527a.R(edgeEffect, ((-i5) * FLING_DESTRETCH_FACTOR) / i7, 0.5f) * ((-i7) / FLING_DESTRETCH_FACTOR));
            if (iRound != i5) {
                edgeEffect.finish();
            }
            return i5 - iRound;
        }
        if (i5 >= 0 || edgeEffect2 == null || AbstractC0527a.q(edgeEffect2) == 0.0f) {
            return i5;
        }
        float f2 = i7;
        int iRound2 = Math.round(AbstractC0527a.R(edgeEffect2, (i5 * FLING_DESTRETCH_FACTOR) / f2, 0.5f) * (f2 / FLING_DESTRETCH_FACTOR));
        if (iRound2 != i5) {
            edgeEffect2.finish();
        }
        return i5 - iRound2;
    }

    public static V0 getChildViewHolderInt(View view) {
        if (view == null) {
            return null;
        }
        return ((C0372z0) view.getLayoutParams()).mViewHolder;
    }

    public static void getDecoratedBoundsWithMarginsInt(View view, Rect rect) {
        C0372z0 c0372z0 = (C0372z0) view.getLayoutParams();
        Rect rect2 = c0372z0.mDecorInsets;
        rect.set((view.getLeft() - rect2.left) - ((ViewGroup.MarginLayoutParams) c0372z0).leftMargin, (view.getTop() - rect2.top) - ((ViewGroup.MarginLayoutParams) c0372z0).topMargin, view.getRight() + rect2.right + ((ViewGroup.MarginLayoutParams) c0372z0).rightMargin, view.getBottom() + rect2.bottom + ((ViewGroup.MarginLayoutParams) c0372z0).bottomMargin);
    }

    private int getPendingAnimFlag() {
        AbstractC0358s0 itemAnimator = getItemAnimator();
        if (itemAnimator instanceof C0357s) {
            return ((C0357s) itemAnimator).f9265p;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getRecyclerViewScreenLocationY() {
        getLocationOnScreen(this.mRecyclerViewOffsets);
        return this.mRecyclerViewOffsets[1];
    }

    private C0223o getScrollingChildHelper() {
        if (this.mScrollingChildHelper == null) {
            this.mScrollingChildHelper = new C0223o(this);
        }
        return this.mScrollingChildHelper;
    }

    public static void setDebugAssertionsEnabled(boolean z9) {
        sDebugAssertionsEnabled = z9;
    }

    public static void setVerboseLoggingEnabled(boolean z9) {
        sVerboseLoggingEnabled = z9;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupGoToTop(int i5) {
        if (o() && this.mEnableGoToTop) {
            removeCallbacks(this.mAutoHide);
            if (i5 == 1 && !f()) {
                i5 = 0;
            }
            if (i5 == -1 && this.mSizeChange) {
                i5 = (f() || e()) ? this.mGoToTopLastState : 0;
            } else if (i5 == -1 && (f() || e())) {
                i5 = 1;
            }
            if (i5 != 0) {
                removeCallbacks(this.mGoToToFadeOutRunnable);
            }
            if (i5 != 1) {
                removeCallbacks(this.mGoToToFadeInRunnable);
            }
            if (this.mShowFadeOutGTT == 0 && i5 == 0 && this.mGoToTopLastState != 0) {
                post(this.mGoToToFadeOutRunnable);
            }
            if (i5 != 2) {
                this.mGoToTopView.setPressed(false);
            }
            this.mGoToTopState = i5;
            int width = (((getWidth() - getPaddingLeft()) - getPaddingRight()) / 2) + getPaddingLeft();
            if (i5 != 0) {
                if (i5 == 1 || i5 == 2) {
                    removeCallbacks(this.mGoToToFadeOutRunnable);
                    int height = getHeight();
                    Rect rect = this.mGoToTopRect;
                    int i7 = this.mGoToTopSize;
                    int i9 = this.mGoToTopBottomPadding;
                    int i10 = this.mGoToTopImmersiveBottomPadding;
                    rect.set(width - (i7 / 2), ((height - i7) - i9) - i10, (i7 / 2) + width, (height - i9) - i10);
                }
            } else if (this.mShowFadeOutGTT == 2) {
                this.mGoToTopRect.set(0, 0, 0, 0);
            }
            if (this.mShowFadeOutGTT == 2) {
                this.mShowFadeOutGTT = 0;
            }
            ImageView imageView = this.mGoToTopView;
            Rect rect2 = this.mGoToTopRect;
            imageView.layout(rect2.left, rect2.top, rect2.right, rect2.bottom);
            if (i5 == 1 && (this.mGoToTopLastState == 0 || this.mGoToTopView.getAlpha() == 0.0f || this.mSizeChange)) {
                post(this.mGoToToFadeInRunnable);
            }
            this.mSizeChange = false;
            this.mGoToTopLastState = this.mGoToTopState;
        }
    }

    public final void A(AbstractC0341j0 abstractC0341j0, boolean z9, boolean z10) {
        AbstractC0341j0 abstractC0341j02 = this.mAdapter;
        if (abstractC0341j02 != null) {
            abstractC0341j02.unregisterAdapterDataObserver(this.mObserver);
            this.mAdapter.onDetachedFromRecyclerView(this);
        }
        if (!z9 || z10) {
            removeAndRecycleViews();
        }
        C0324b c0324b = this.mAdapterHelper;
        c0324b.l(c0324b.f9054b);
        c0324b.l(c0324b.f9055c);
        int i5 = 0;
        c0324b.f9058f = 0;
        AbstractC0341j0 abstractC0341j03 = this.mAdapter;
        this.mAdapter = abstractC0341j0;
        if (abstractC0341j0 != null) {
            abstractC0341j0.registerAdapterDataObserver(this.mObserver);
            abstractC0341j0.onAttachedToRecyclerView(this);
        }
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 != null) {
            abstractC0370y0.onAdapterChanged(abstractC0341j03, this.mAdapter);
        }
        G0 g02 = this.mRecycler;
        AbstractC0341j0 abstractC0341j04 = this.mAdapter;
        g02.b();
        g02.g(abstractC0341j03, true);
        F0 f0D = g02.d();
        if (abstractC0341j03 != null) {
            f0D.f8889b--;
        }
        if (!z9 && f0D.f8889b == 0) {
            while (true) {
                SparseArray sparseArray = f0D.f8888a;
                if (i5 >= sparseArray.size()) {
                    break;
                }
                E0 e02 = (E0) sparseArray.valueAt(i5);
                if (e02 != null) {
                    ArrayList arrayList = e02.f8879a;
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        AbstractC0752G.m(((V0) it.next()).itemView);
                    }
                    arrayList.clear();
                } else {
                    Log.e(TAG, "clear() wasn't executed because RecycledViewPool.mScrap was invalid");
                }
                i5++;
            }
        }
        if (abstractC0341j04 != null) {
            f0D.f8889b++;
        } else {
            f0D.getClass();
        }
        g02.f();
        this.mState.f8983f = true;
    }

    public final boolean B(EdgeEffect edgeEffect, int i5, int i7) {
        if (i5 > 0) {
            return true;
        }
        float fQ = AbstractC0527a.q(edgeEffect) * i7;
        double dLog = Math.log((Math.abs(-i5) * INFLEXION) / (this.mPhysicalCoef * SCROLL_FRICTION));
        double d8 = DECELERATION_RATE;
        return ((float) (Math.exp((d8 / (d8 - 1.0d)) * dLog) * ((double) (this.mPhysicalCoef * SCROLL_FRICTION)))) < fQ;
    }

    public final void C(int i5, int i7, boolean z9) {
        int height;
        int i9;
        D0 d0;
        int iE = this.mChildHelper.e();
        if (this.mIsFirstMultiSelectionMove) {
            this.mPenDragStartX = i5;
            this.mPenDragStartY = i7;
            float f2 = i5;
            float f7 = i7;
            View viewFindChildViewUnder = findChildViewUnder(f2, f7);
            this.mPenTrackedChild = viewFindChildViewUnder;
            if (viewFindChildViewUnder == null) {
                View viewSeslFindNearChildViewUnder = seslFindNearChildViewUnder(f2, f7);
                this.mPenTrackedChild = viewSeslFindNearChildViewUnder;
                if (viewSeslFindNearChildViewUnder == null) {
                    Log.e(TAG, "updateLongPressMultiSelection, mPenTrackedChild is NULL");
                    this.mIsFirstMultiSelectionMove = false;
                    return;
                }
            }
            int childLayoutPosition = getChildLayoutPosition(this.mPenTrackedChild);
            this.mPenTrackedChildPosition = childLayoutPosition;
            this.mPenDragSelectedViewPosition = childLayoutPosition;
            this.mPenDistanceFromTrackedChildTop = this.mPenDragStartY - this.mPenTrackedChild.getTop();
            this.mIsFirstMultiSelectionMove = false;
        }
        if (this.mIsEnabledPaddingInHoverScroll) {
            i9 = this.mListPadding.top;
            height = getHeight() - this.mListPadding.bottom;
        } else {
            height = getHeight();
            i9 = 0;
        }
        this.mPenDragEndX = i5;
        this.mPenDragEndY = i7;
        if (i7 < 0) {
            this.mPenDragEndY = 0;
        } else if (i7 > height) {
            this.mPenDragEndY = height;
        }
        View viewFindChildViewUnder2 = findChildViewUnder(i5, this.mPenDragEndY);
        if (viewFindChildViewUnder2 == null && (viewFindChildViewUnder2 = seslFindNearChildViewUnder(this.mPenDragEndX, this.mPenDragEndY)) == null) {
            Log.e(TAG, "updateLongPressMultiSelection, touchedView is NULL");
            return;
        }
        int childLayoutPosition2 = getChildLayoutPosition(viewFindChildViewUnder2);
        if (childLayoutPosition2 == -1) {
            Log.e(TAG, "touchedPosition is NO_POSITION");
            return;
        }
        this.mPenDragSelectedViewPosition = childLayoutPosition2;
        int i10 = this.mPenTrackedChildPosition;
        if (i10 < childLayoutPosition2) {
            i10 = childLayoutPosition2;
            childLayoutPosition2 = i10;
        }
        int i11 = this.mPenDragStartX;
        int i12 = this.mPenDragEndX;
        this.mPenDragBlockLeft = i11 < i12 ? i11 : i12;
        int i13 = this.mPenDragStartY;
        int i14 = this.mPenDragEndY;
        this.mPenDragBlockTop = i13 < i14 ? i13 : i14;
        if (i12 > i11) {
            i11 = i12;
        }
        this.mPenDragBlockRight = i11;
        if (i14 > i13) {
            i13 = i14;
        }
        this.mPenDragBlockBottom = i13;
        for (int i15 = 0; i15 < iE; i15++) {
            View childAt = getChildAt(i15);
            if (childAt != null) {
                this.mPenDragSelectedViewPosition = getChildLayoutPosition(childAt);
                if (childAt.getVisibility() == 0) {
                    int i16 = this.mPenDragSelectedViewPosition;
                    if (childLayoutPosition2 > i16 || i16 > i10 || i16 == this.mPenTrackedChildPosition) {
                        if (i16 != -1 && this.mPenDragSelectedItemArray.contains(Integer.valueOf(i16))) {
                            this.mPenDragSelectedItemArray.remove(Integer.valueOf(this.mPenDragSelectedViewPosition));
                        }
                    } else if (i16 != -1 && !this.mPenDragSelectedItemArray.contains(Integer.valueOf(i16))) {
                        this.mPenDragSelectedItemArray.add(Integer.valueOf(this.mPenDragSelectedViewPosition));
                    }
                }
            }
        }
        int i17 = this.mLastTouchY - i7;
        if (z9 && Math.abs(i17) >= this.mTouchSlop) {
            if (i7 <= i9 + this.mHoverTopAreaHeight && i17 > 0) {
                if (!this.mHoverAreaEnter) {
                    this.mHoverAreaEnter = true;
                    this.mHoverScrollStartTime = System.currentTimeMillis();
                    D0 d02 = this.mScrollListener;
                    if (d02 != null) {
                        d02.onScrollStateChanged(this, 1);
                    }
                }
                if (!this.mHoverHandler.hasMessages(0)) {
                    this.mHoverRecognitionStartTime = System.currentTimeMillis();
                    this.mHoverScrollDirection = 2;
                    this.mHoverHandler.sendEmptyMessage(0);
                }
            } else if (i7 < (height - this.mHoverBottomAreaHeight) - this.mRemainNestedScrollRange || i17 >= 0) {
                if (this.mHoverAreaEnter && (d0 = this.mScrollListener) != null) {
                    d0.onScrollStateChanged(this, 0);
                }
                this.mHoverScrollStartTime = 0L;
                this.mHoverRecognitionStartTime = 0L;
                this.mHoverAreaEnter = false;
                if (this.mHoverHandler.hasMessages(0)) {
                    this.mHoverHandler.removeMessages(0);
                    if (this.mScrollState == 1) {
                        setScrollState(0);
                    }
                }
                this.mIsHoverOverscrolled = false;
            } else {
                if (!this.mHoverAreaEnter) {
                    this.mHoverAreaEnter = true;
                    this.mHoverScrollStartTime = System.currentTimeMillis();
                    D0 d03 = this.mScrollListener;
                    if (d03 != null) {
                        d03.onScrollStateChanged(this, 1);
                    }
                }
                if (!this.mHoverHandler.hasMessages(0)) {
                    this.mHoverRecognitionStartTime = System.currentTimeMillis();
                    this.mHoverScrollDirection = 1;
                    this.mHoverHandler.sendEmptyMessage(0);
                }
            }
        }
        invalidate();
    }

    public final void D() {
        int i5 = this.mScrollbarTopPadding;
        Class cls = Integer.TYPE;
        Method methodT = com.bumptech.glide.c.t(View.class, "semSetScrollBarTopPadding", cls);
        if (methodT != null) {
            com.bumptech.glide.c.C(this, methodT, Integer.valueOf(i5));
        }
        int i7 = this.mScrollbarBottomPadding;
        Method methodT2 = com.bumptech.glide.c.t(View.class, "semSetScrollBarBottomPadding", cls);
        if (methodT2 != null) {
            com.bumptech.glide.c.C(this, methodT2, Integer.valueOf(i7));
        }
    }

    public final void a(V0 v02) {
        View view = v02.itemView;
        boolean z9 = view.getParent() == this;
        this.mRecycler.n(getChildViewHolder(view));
        if (v02.isTmpDetached()) {
            this.mChildHelper.b(view, -1, view.getLayoutParams(), true);
            return;
        }
        if (!z9) {
            this.mChildHelper.a(view, -1, true);
            return;
        }
        C0342k c0342k = this.mChildHelper;
        int iIndexOfChild = c0342k.f9178a.f9119a.indexOfChild(view);
        if (iIndexOfChild >= 0) {
            c0342k.f9179b.h(iIndexOfChild);
            c0342k.i(view);
        } else {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view);
        }
    }

    public void absorbGlows(int i5, int i7) {
        if (i5 < 0) {
            ensureLeftGlow();
            if (this.mLeftGlow.isFinished()) {
                this.mLeftGlow.onAbsorb(-i5);
            }
        } else if (i5 > 0) {
            ensureRightGlow();
            if (this.mRightGlow.isFinished()) {
                this.mRightGlow.onAbsorb(i5);
            }
        }
        if (i7 < 0) {
            ensureTopGlow();
            if (this.mTopGlow.isFinished()) {
                this.mTopGlow.onAbsorb(-i7);
            }
        } else if (i7 > 0) {
            ensureBottomGlow();
            if (this.mBottomGlow.isFinished()) {
                this.mBottomGlow.onAbsorb(i7);
            }
        }
        if (i5 == 0 && i7 == 0) {
            return;
        }
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        postInvalidateOnAnimation();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void addFocusables(ArrayList<View> arrayList, int i5, int i7) {
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 == null || !abstractC0370y0.onAddFocusables(this, arrayList, i5, i7)) {
            super.addFocusables(arrayList, i5, i7);
        }
    }

    public void addItemDecoration(AbstractC0362u0 abstractC0362u0, int i5) {
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 != null) {
            abstractC0370y0.assertNotInLayoutOrScroll("Cannot add item decoration during a scroll  or layout");
        }
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(false);
        }
        if (i5 < 0) {
            this.mItemDecorations.add(abstractC0362u0);
        } else {
            this.mItemDecorations.add(i5, abstractC0362u0);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void addOnChildAttachStateChangeListener(A0 a02) {
        if (this.mOnChildAttachStateListeners == null) {
            this.mOnChildAttachStateListeners = new ArrayList();
        }
        this.mOnChildAttachStateListeners.add(a02);
    }

    public void addOnItemTouchListener(C0 c02) {
        this.mOnItemTouchListeners.add(c02);
    }

    public void addOnScrollListener(D0 d0) {
        if (this.mScrollListeners == null) {
            this.mScrollListeners = new ArrayList();
        }
        this.mScrollListeners.add(d0);
    }

    public void addRecyclerListener(H0 h02) {
        AbstractC0582a.m("'listener' arg cannot be null.", h02 != null);
        this.mRecyclerListeners.add(h02);
    }

    public void animateAppearance(V0 v02, C0356r0 c0356r0, C0356r0 c0356r02) {
        boolean zG;
        int i5;
        int i7;
        v02.setIsRecyclable(false);
        c1 c1Var = (c1) this.mItemAnimator;
        c1Var.getClass();
        if (c0356r0 == null || ((i5 = c0356r0.f9252a) == (i7 = c0356r02.f9252a) && c0356r0.f9253b == c0356r02.f9253b)) {
            C0357s c0357s = (C0357s) c1Var;
            c0357s.l(v02);
            v02.itemView.setAlpha(0.0f);
            c0357s.f9257f.add(v02);
            int i9 = c0357s.f9265p;
            if ((i9 & 8) == 0) {
                c0357s.f9265p = i9 | 8;
            }
            zG = true;
        } else {
            zG = c1Var.g(v02, i5, c0356r0.f9253b, i7, c0356r02.f9253b);
        }
        if (zG) {
            postAnimationRunner();
        }
    }

    public void animateDisappearance(V0 v02, C0356r0 c0356r0, C0356r0 c0356r02) {
        boolean zG;
        a(v02);
        v02.setIsRecyclable(false);
        c1 c1Var = (c1) this.mItemAnimator;
        c1Var.getClass();
        int i5 = c0356r0.f9252a;
        int i7 = c0356r0.f9253b;
        View view = v02.itemView;
        int left = c0356r02 == null ? view.getLeft() : c0356r02.f9252a;
        int top = c0356r02 == null ? view.getTop() : c0356r02.f9253b;
        if (v02.isRemoved() || (i5 == left && i7 == top)) {
            C0357s c0357s = (C0357s) c1Var;
            c0357s.l(v02);
            c0357s.f9256e.add(v02);
            if (v02.itemView.getBottom() > c0357s.f9266q) {
                c0357s.f9266q = v02.itemView.getBottom();
            }
            int i9 = c0357s.f9265p;
            if ((i9 & 1) == 0) {
                c0357s.f9265p = i9 | 1;
            }
            zG = true;
        } else {
            view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
            zG = c1Var.g(v02, i5, i7, left, top);
        }
        if (zG) {
            postAnimationRunner();
        }
    }

    public void assertInLayoutOrScroll(String str) {
        if (isComputingLayout()) {
            return;
        }
        if (str == null) {
            throw new IllegalStateException(A8.l.p(this, new StringBuilder("Cannot call this method unless RecyclerView is computing a layout or scrolling")));
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        throw new IllegalStateException(A8.l.p(this, sb));
    }

    public void assertNotInLayoutOrScroll(String str) {
        if (isComputingLayout()) {
            if (str != null) {
                throw new IllegalStateException(str);
            }
            throw new IllegalStateException(A8.l.p(this, new StringBuilder("Cannot call this method while RecyclerView is computing a layout or scrolling")));
        }
        if (this.mDispatchScrollCounter > 0) {
            Log.w(TAG, "Cannot call this method in a scroll callback. Scroll callbacks mightbe run during a measure & layout pass where you cannot change theRecyclerView data. Any method call that might change the structureof the RecyclerView or the adapter contents should be postponed tothe next frame.", new IllegalStateException(A8.l.p(this, new StringBuilder(""))));
        }
    }

    public final void b() {
        getLocationInWindow(this.mWindowOffsets);
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        int i5 = (abstractC0370y0 == null || !abstractC0370y0.canScrollHorizontally()) ? this.mWindowOffsets[1] : this.mWindowOffsets[0];
        int i7 = this.mNestedScrollRange;
        int i9 = this.mInitialTopOffsetOfScreen;
        int i10 = i7 - (i9 - i5);
        this.mRemainNestedScrollRange = i10;
        if (i9 - i5 < 0) {
            this.mNestedScrollRange = i10;
            this.mInitialTopOffsetOfScreen = i5;
        }
    }

    public final void c(int i5) {
        if (this.mHasNestedScrollRange) {
            if (f() && this.mRemainNestedScrollRange == 0) {
                return;
            }
            int i7 = this.mRemainNestedScrollRange - i5;
            this.mRemainNestedScrollRange = i7;
            if (i7 < 0) {
                this.mRemainNestedScrollRange = 0;
                return;
            }
            int i9 = this.mNestedScrollRange;
            if (i7 > i9) {
                this.mRemainNestedScrollRange = i9;
            }
        }
    }

    public boolean canReuseUpdatedViewHolder(V0 v02) {
        AbstractC0358s0 abstractC0358s0 = this.mItemAnimator;
        if (abstractC0358s0 != null) {
            List<Object> unmodifiedPayloads = v02.getUnmodifiedPayloads();
            C0357s c0357s = (C0357s) abstractC0358s0;
            c0357s.getClass();
            if (unmodifiedPayloads.isEmpty() && c0357s.f9120d && !v02.isInvalid()) {
                return false;
            }
        }
        return true;
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof C0372z0) && this.mLayout.checkLayoutParams((C0372z0) layoutParams);
    }

    public void clearOldPositions() {
        int iH = this.mChildHelper.h();
        for (int i5 = 0; i5 < iH; i5++) {
            V0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.g(i5));
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.clearOldPosition();
            }
        }
        G0 g02 = this.mRecycler;
        ArrayList arrayList = g02.f8893c;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((V0) arrayList.get(i7)).clearOldPosition();
        }
        ArrayList arrayList2 = g02.f8891a;
        int size2 = arrayList2.size();
        for (int i9 = 0; i9 < size2; i9++) {
            ((V0) arrayList2.get(i9)).clearOldPosition();
        }
        ArrayList arrayList3 = g02.f8892b;
        if (arrayList3 != null) {
            int size3 = arrayList3.size();
            for (int i10 = 0; i10 < size3; i10++) {
                ((V0) g02.f8892b.get(i10)).clearOldPosition();
            }
        }
    }

    public void clearOnChildAttachStateChangeListeners() {
        List<A0> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            list.clear();
        }
    }

    public void clearOnScrollListeners() {
        List<D0> list = this.mScrollListeners;
        if (list != null) {
            list.clear();
        }
    }

    @Override // android.view.View
    public int computeHorizontalScrollExtent() {
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 != null && abstractC0370y0.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollExtent(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollOffset() {
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 != null && abstractC0370y0.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollOffset(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeHorizontalScrollRange() {
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 != null && abstractC0370y0.canScrollHorizontally()) {
            return this.mLayout.computeHorizontalScrollRange(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollExtent() {
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 != null && abstractC0370y0.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollExtent(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollOffset() {
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 != null && abstractC0370y0.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollOffset(this.mState);
        }
        return 0;
    }

    @Override // android.view.View
    public int computeVerticalScrollRange() {
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 != null && abstractC0370y0.canScrollVertically()) {
            return this.mLayout.computeVerticalScrollRange(this.mState);
        }
        return 0;
    }

    public void considerReleasingGlowsOnScroll(int i5, int i7) {
        boolean zIsFinished;
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect == null || edgeEffect.isFinished() || i5 <= 0) {
            zIsFinished = false;
        } else {
            this.mLeftGlow.onRelease();
            zIsFinished = this.mLeftGlow.isFinished();
        }
        EdgeEffect edgeEffect2 = this.mRightGlow;
        if (edgeEffect2 != null && !edgeEffect2.isFinished() && i5 < 0) {
            this.mRightGlow.onRelease();
            zIsFinished |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished() && i7 > 0) {
            this.mTopGlow.onRelease();
            zIsFinished |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null && !edgeEffect4.isFinished() && i7 < 0) {
            this.mBottomGlow.onRelease();
            zIsFinished |= this.mBottomGlow.isFinished();
        }
        if (zIsFinished) {
            WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
            postInvalidateOnAnimation();
        }
    }

    public int consumeFlingInHorizontalStretch(int i5) {
        return g(i5, this.mLeftGlow, this.mRightGlow, getWidth());
    }

    public int consumeFlingInVerticalStretch(int i5) {
        return g(i5, this.mTopGlow, this.mBottomGlow, getHeight());
    }

    public void consumePendingUpdateOperations() {
        if (!this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout) {
            Trace.beginSection(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
            dispatchLayout();
            Trace.endSection();
            return;
        }
        if (this.mAdapterHelper.g()) {
            C0324b c0324b = this.mAdapterHelper;
            int i5 = c0324b.f9058f;
            if ((i5 & 4) == 0 || (i5 & 11) != 0) {
                if (c0324b.g()) {
                    Trace.beginSection(TRACE_ON_DATA_SET_CHANGE_LAYOUT_TAG);
                    dispatchLayout();
                    Trace.endSection();
                    return;
                }
                return;
            }
            Trace.beginSection(TRACE_HANDLE_ADAPTER_UPDATES_TAG);
            startInterceptRequestLayout();
            onEnterLayoutOrScroll();
            this.mAdapterHelper.j();
            if (!this.mLayoutWasDefered) {
                int iE = this.mChildHelper.e();
                int i7 = 0;
                while (true) {
                    if (i7 < iE) {
                        V0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.d(i7));
                        if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.isUpdated()) {
                            dispatchLayout();
                            break;
                        }
                        i7++;
                    } else {
                        this.mAdapterHelper.b();
                        break;
                    }
                }
            }
            stopInterceptRequestLayout(true);
            onExitLayoutOrScroll();
            Trace.endSection();
        }
    }

    public final void d(int i5) {
        if (this.mEnableGoToTop) {
            if (i5 == 0) {
                if (seslIsFastScrollerEnabled()) {
                    return;
                }
                removeCallbacks(this.mAutoHide);
                postDelayed(this.mAutoHide, 1500L);
                return;
            }
            if (i5 == 1) {
                removeCallbacks(this.mAutoHide);
                postDelayed(this.mAutoHide, 1500L);
            }
        }
    }

    public void defaultOnMeasure(int i5, int i7) {
        int paddingRight = getPaddingRight() + getPaddingLeft();
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        setMeasuredDimension(AbstractC0370y0.chooseSize(i5, paddingRight, getMinimumWidth()), AbstractC0370y0.chooseSize(i7, getPaddingBottom() + getPaddingTop(), getMinimumHeight()));
    }

    public void dispatchChildAttached(View view) {
        V0 childViewHolderInt = getChildViewHolderInt(view);
        onChildAttachedToWindow(view);
        AbstractC0341j0 abstractC0341j0 = this.mAdapter;
        if (abstractC0341j0 != null && childViewHolderInt != null) {
            abstractC0341j0.onViewAttachedToWindow(childViewHolderInt);
        }
        List<A0> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mOnChildAttachStateListeners.get(size).a(view);
            }
        }
    }

    public void dispatchChildDetached(View view) {
        V0 childViewHolderInt = getChildViewHolderInt(view);
        onChildDetachedFromWindow(view);
        AbstractC0341j0 abstractC0341j0 = this.mAdapter;
        if (abstractC0341j0 != null && childViewHolderInt != null) {
            abstractC0341j0.onViewDetachedFromWindow(childViewHolderInt);
        }
        List<A0> list = this.mOnChildAttachStateListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mOnChildAttachStateListeners.get(size).d(view);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        View childAt;
        super.dispatchDraw(canvas);
        int size = this.mItemDecorations.size();
        for (int i5 = 0; i5 < size; i5++) {
            this.mItemDecorations.get(i5).seslOnDispatchDraw(canvas, this, this.mState);
        }
        int width = getWidth();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        if (this.mDrawRect && ((this.mBlackTop != -1 || this.mLastBlackTop != -1) && !canScrollVertically(-1) && (!canScrollVertically(1) || isAnimating()))) {
            ValueAnimator valueAnimator = this.mLastItemAddRemoveAnim;
            if (valueAnimator == null || !valueAnimator.isRunning()) {
                this.mAnimatedBlackTop = this.mBlackTop;
            }
            if (isAnimating()) {
                int pendingAnimFlag = getPendingAnimFlag();
                if (pendingAnimFlag == 8) {
                    this.mIsSetOnlyAddAnim = true;
                } else if (pendingAnimFlag == 1) {
                    this.mIsSetOnlyRemoveAnim = true;
                }
                if (this.mDrawReverse) {
                    childAt = this.mBlackTop != -1 ? this.mChildHelper.d(0) : getChildAt(0);
                } else if (this.mBlackTop != -1) {
                    C0342k c0342k = this.mChildHelper;
                    childAt = c0342k.d(c0342k.e() - 1);
                } else {
                    childAt = getChildAt(getChildCount() - 1);
                }
                if (childAt != null) {
                    if (!this.mIsSetOnlyAddAnim && !this.mIsSetOnlyRemoveAnim) {
                        this.mAnimatedBlackTop = childAt.getHeight() + Math.round(childAt.getY());
                    } else if (this.mLastItemAddRemoveAnim == null) {
                        AbstractC0358s0 itemAnimator = getItemAnimator();
                        if ((itemAnimator instanceof C0357s) && this.mLastItemAnimTop == -1) {
                            this.mLastItemAnimTop = ((C0357s) itemAnimator).f9266q;
                        }
                        if (this.mIsSetOnlyAddAnim) {
                            this.mLastItemAddRemoveAnim = ValueAnimator.ofInt(this.mLastItemAnimTop, childAt.getHeight() + ((int) childAt.getY()));
                        } else if (this.mIsSetOnlyRemoveAnim) {
                            this.mLastItemAddRemoveAnim = ValueAnimator.ofInt(this.mLastItemAnimTop, childAt.getBottom());
                        } else {
                            Log.d(TAG, "Not set only add/remove anim");
                        }
                        this.mLastItemAddRemoveAnim.setDuration(330L);
                        this.mLastItemAddRemoveAnim.addListener(this.mAnimListener);
                        this.mLastItemAddRemoveAnim.addUpdateListener(new C0348n(this, 1));
                        this.mLastItemAddRemoveAnim.start();
                    }
                }
                invalidate();
            }
            int i7 = this.mBlackTop;
            if (i7 != -1 || this.mAnimatedBlackTop != i7 || this.mIsSetOnlyAddAnim) {
                canvas.drawRect(0.0f, this.mAnimatedBlackTop, width, getBottom(), this.mRectPaint);
                if (this.mDrawLastRoundedCorner) {
                    C0634c c0634c = this.mRoundedCorner;
                    c0634c.f11735k.set(paddingLeft, this.mAnimatedBlackTop, width - paddingRight, getBottom());
                    c0634c.e(canvas);
                }
            }
        }
        this.mLastItemAnimTop = this.mBlackTop;
        if (this.mDrawHorizontalPadding) {
            int height = getHeight();
            if (paddingLeft > 0) {
                canvas.drawRect(0.0f, 0.0f, paddingLeft, height, this.mRectPaint);
            }
            if (paddingRight > 0) {
                canvas.drawRect(width - paddingRight, 0.0f, width, height, this.mRectPaint);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x03c6  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x03fb  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean dispatchHoverEvent(android.view.MotionEvent r18) {
        /*
            Method dump skipped, instruction units count: 1040
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.dispatchHoverEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 19 || keyCode == 20) {
            if (keyEvent.getAction() == 0) {
                this.mIsArrowKeyPressed = true;
            }
        } else if (keyCode == 66 && this.mIsRecoilSupported && this.mIsRecoilEnabled) {
            if (keyEvent.getAction() == 0) {
                View focusedChild = getFocusedChild();
                if (focusedChild != null) {
                    this.mItemAnimatorHolder.a(focusedChild);
                }
            } else {
                this.mItemAnimatorHolder.b();
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:195:0x03db  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x040f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void dispatchLayout() {
        /*
            Method dump skipped, instruction units count: 1147
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.dispatchLayout():void");
    }

    @Override // android.view.View
    public boolean dispatchNestedFling(float f2, float f7, boolean z9) {
        return getScrollingChildHelper().a(f2, f7, z9);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreFling(float f2, float f7) {
        return getScrollingChildHelper().b(f2, f7);
    }

    @Override // android.view.View
    public boolean dispatchNestedPreScroll(int i5, int i7, int[] iArr, int[] iArr2) {
        return getScrollingChildHelper().c(i5, i7, iArr, iArr2, 0);
    }

    @Override // android.view.View
    public boolean dispatchNestedScroll(int i5, int i7, int i9, int i10, int[] iArr) {
        return getScrollingChildHelper().e(i5, i7, i9, i10, iArr, 0, null);
    }

    public void dispatchOnScrollStateChanged(int i5) {
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 != null) {
            abstractC0370y0.onScrollStateChanged(i5);
        }
        onScrollStateChanged(i5);
        D0 d0 = this.mScrollListener;
        if (d0 != null) {
            d0.onScrollStateChanged(this, i5);
        }
        List<D0> list = this.mScrollListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mScrollListeners.get(size).onScrollStateChanged(this, i5);
            }
        }
    }

    public void dispatchOnScrolled(int i5, int i7) {
        this.mDispatchScrollCounter++;
        int scrollX = getScrollX();
        int scrollY = getScrollY();
        onScrollChanged(scrollX, scrollY, scrollX - i5, scrollY - i7);
        onScrolled(i5, i7);
        b1 b1Var = this.mFastScroller;
        if (b1Var != null && this.mAdapter != null && (i5 != 0 || i7 != 0)) {
            b1Var.m(findFirstVisibleItemPosition(), getChildCount(), this.mAdapter.getItemCount());
        }
        if (this.mIndexTipEnabled && this.mIndexTip != null) {
            if (this.mScrollState != 0 && getHeight() > this.mSeslOverlayFeatureHeight) {
                C0353p0 c0353p0 = this.mIndexTip;
                int i9 = this.mScrollState;
                RecyclerView recyclerView = c0353p0.f9220J;
                if (i9 == 1 && recyclerView.mRemainNestedScrollRange != 0 && i7 >= 0) {
                    recyclerView.b();
                } else if (i7 != 0 && !c0353p0.f9240z && recyclerView.f() && !recyclerView.mGoToToping && !c0353p0.f9212A) {
                    c0353p0.c();
                    c0353p0.f9240z = true;
                }
            }
            this.mIndexTip.invalidate();
        }
        D0 d0 = this.mScrollListener;
        if (d0 != null) {
            d0.onScrolled(this, i5, i7);
        }
        List<D0> list = this.mScrollListeners;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                this.mScrollListeners.get(size).onScrolled(this, i5, i7);
            }
        }
        this.mDispatchScrollCounter--;
    }

    public void dispatchPendingImportantForAccessibilityChanges() {
        int i5;
        for (int size = this.mPendingAccessibilityImportanceChange.size() - 1; size >= 0; size--) {
            V0 v02 = this.mPendingAccessibilityImportanceChange.get(size);
            if (v02.itemView.getParent() == this && !v02.shouldIgnore() && (i5 = v02.mPendingAccessibilityState) != -1) {
                View view = v02.itemView;
                WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
                view.setImportantForAccessibility(i5);
                v02.mPendingAccessibilityState = -1;
            }
        }
        this.mPendingAccessibilityImportanceChange.clear();
    }

    @Override // android.view.View
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchRestoreInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchThawSelfOnly(sparseArray);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchSaveInstanceState(SparseArray<Parcelable> sparseArray) {
        dispatchFreezeSelfOnly(sparseArray);
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0168  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r17) {
        /*
            Method dump skipped, instruction units count: 486
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        boolean z9;
        ImageView imageView;
        super.draw(canvas);
        int size = this.mItemDecorations.size();
        for (int i5 = 0; i5 < size; i5++) {
            this.mItemDecorations.get(i5).onDrawOver(canvas, this, this.mState);
        }
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect == null || edgeEffect.isFinished()) {
            z9 = false;
        } else {
            int iSave = canvas.save();
            int paddingBottom = this.mClipToPadding ? getPaddingBottom() : 0;
            canvas.rotate(270.0f);
            canvas.translate((-getHeight()) + paddingBottom, 0.0f);
            EdgeEffect edgeEffect2 = this.mLeftGlow;
            z9 = edgeEffect2 != null && edgeEffect2.draw(canvas);
            canvas.restoreToCount(iSave);
        }
        EdgeEffect edgeEffect3 = this.mTopGlow;
        if (edgeEffect3 != null && !edgeEffect3.isFinished()) {
            int iSave2 = canvas.save();
            if (this.mClipToPadding) {
                canvas.translate(getPaddingLeft(), getPaddingTop());
            }
            EdgeEffect edgeEffect4 = this.mTopGlow;
            z9 |= edgeEffect4 != null && edgeEffect4.draw(canvas);
            canvas.restoreToCount(iSave2);
        }
        EdgeEffect edgeEffect5 = this.mRightGlow;
        if (edgeEffect5 != null && !edgeEffect5.isFinished()) {
            int iSave3 = canvas.save();
            int width = getWidth();
            int paddingTop = this.mClipToPadding ? getPaddingTop() : 0;
            canvas.rotate(90.0f);
            canvas.translate(paddingTop, -width);
            EdgeEffect edgeEffect6 = this.mRightGlow;
            z9 |= edgeEffect6 != null && edgeEffect6.draw(canvas);
            canvas.restoreToCount(iSave3);
        }
        EdgeEffect edgeEffect7 = this.mBottomGlow;
        if (edgeEffect7 != null && !edgeEffect7.isFinished()) {
            int iSave4 = canvas.save();
            canvas.rotate(180.0f);
            if (this.mClipToPadding) {
                canvas.translate(getPaddingRight() + (-getWidth()), getPaddingBottom() + (-getHeight()));
            } else {
                canvas.translate(-getWidth(), -getHeight());
            }
            EdgeEffect edgeEffect8 = this.mBottomGlow;
            z9 |= edgeEffect8 != null && edgeEffect8.draw(canvas);
            canvas.restoreToCount(iSave4);
        }
        if ((z9 || this.mItemAnimator == null || this.mItemDecorations.size() <= 0 || !this.mItemAnimator.f()) ? z9 : true) {
            WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
            postInvalidateOnAnimation();
        }
        if (this.mEnableGoToTop) {
            this.mGoToTopView.setTranslationY(getScrollY());
            if (this.mGoToTopState != 0 && !f()) {
                setupGoToTop(0);
            }
        }
        if (!o() && (imageView = this.mGoToTopView) != null && imageView.getAlpha() != 0.0f) {
            this.mGoToTopView.setAlpha(0.0f);
        }
        if (!this.mIsPenDragBlockEnabled || this.mIsLongPressMultiSelection || this.mLayout == null) {
            return;
        }
        if (this.mPenDragBlockLeft == 0 && this.mPenDragBlockTop == 0) {
            return;
        }
        int iFindFirstVisibleItemPosition = findFirstVisibleItemPosition();
        int iFindLastVisibleItemPosition = findLastVisibleItemPosition();
        int i7 = this.mPenTrackedChildPosition;
        if (i7 >= iFindFirstVisibleItemPosition && i7 <= iFindLastVisibleItemPosition) {
            View viewFindViewByPosition = this.mLayout.findViewByPosition(i7);
            this.mPenTrackedChild = viewFindViewByPosition;
            this.mPenDragStartY = (viewFindViewByPosition != null ? viewFindViewByPosition.getTop() : 0) + this.mPenDistanceFromTrackedChildTop;
        }
        int i9 = this.mPenDragStartY;
        int i10 = this.mPenDragEndY;
        int i11 = i9 < i10 ? i9 : i10;
        this.mPenDragBlockTop = i11;
        if (i10 > i9) {
            i9 = i10;
        }
        this.mPenDragBlockBottom = i9;
        this.mPenDragBlockRect.set(this.mPenDragBlockLeft, i11, this.mPenDragBlockRight, i9);
        this.mPenDragBlockImage.setBounds(this.mPenDragBlockRect);
        this.mPenDragBlockImage.draw(canvas);
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j5) {
        return super.drawChild(canvas, view, j5);
    }

    public final boolean e() {
        boolean zCanScrollHorizontally;
        boolean z9;
        int childCount = getChildCount();
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        boolean z10 = true;
        if (abstractC0370y0 != null) {
            zCanScrollHorizontally = abstractC0370y0.canScrollHorizontally();
            z9 = this.mLayout.getLayoutDirection() == 1;
        } else {
            zCanScrollHorizontally = false;
            z9 = false;
        }
        AbstractC0370y0 abstractC0370y02 = this.mLayout;
        boolean reverseLayout = abstractC0370y02 instanceof LinearLayoutManager ? ((LinearLayoutManager) abstractC0370y02).getReverseLayout() : false;
        if (this.mAdapter == null) {
            Log.e(TAG, "No adapter attached; skipping canScrollDown");
            return false;
        }
        boolean z11 = !reverseLayout ? j() + childCount >= this.mAdapter.getItemCount() : j() <= 0;
        if (z11 || childCount <= 0) {
            return z11;
        }
        getDecoratedBoundsWithMargins(getChildAt(reverseLayout ? 0 : childCount - 1), this.mChildBound);
        if (!zCanScrollHorizontally ? !(this.mChildBound.bottom > getBottom() - this.mListPadding.bottom || this.mChildBound.bottom > getHeight() - this.mListPadding.bottom) : !(!z9 ? this.mChildBound.right > getRight() - this.mListPadding.right || this.mChildBound.right > getWidth() - this.mListPadding.right : this.mChildBound.left < this.mListPadding.left)) {
            z10 = false;
        }
        return z10;
    }

    public void ensureBottomGlow() {
        if (this.mBottomGlow != null) {
            return;
        }
        ((S0) this.mEdgeEffectFactory).getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.mBottomGlow = edgeEffect;
        if (this.mClipToPadding) {
            edgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public void ensureLeftGlow() {
        if (this.mLeftGlow != null) {
            return;
        }
        ((S0) this.mEdgeEffectFactory).getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.mLeftGlow = edgeEffect;
        if (this.mClipToPadding) {
            edgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public void ensureRightGlow() {
        if (this.mRightGlow != null) {
            return;
        }
        ((S0) this.mEdgeEffectFactory).getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.mRightGlow = edgeEffect;
        if (this.mClipToPadding) {
            edgeEffect.setSize((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom(), (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight());
        } else {
            edgeEffect.setSize(getMeasuredHeight(), getMeasuredWidth());
        }
    }

    public void ensureTopGlow() {
        if (this.mTopGlow != null) {
            return;
        }
        ((S0) this.mEdgeEffectFactory).getClass();
        EdgeEffect edgeEffect = new EdgeEffect(getContext());
        this.mTopGlow = edgeEffect;
        if (this.mClipToPadding) {
            edgeEffect.setSize((getMeasuredWidth() - getPaddingLeft()) - getPaddingRight(), (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom());
        } else {
            edgeEffect.setSize(getMeasuredWidth(), getMeasuredHeight());
        }
    }

    public String exceptionLabel() {
        return " " + super.toString() + ", adapter:" + this.mAdapter + ", layout:" + this.mLayout + ", context:" + getContext();
    }

    public final boolean f() {
        boolean zCanScrollHorizontally;
        boolean z9;
        int childCount = getChildCount();
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        boolean z10 = true;
        if (abstractC0370y0 != null) {
            zCanScrollHorizontally = abstractC0370y0.canScrollHorizontally();
            z9 = this.mLayout.getLayoutDirection() == 1;
        } else {
            zCanScrollHorizontally = false;
            z9 = false;
        }
        AbstractC0370y0 abstractC0370y02 = this.mLayout;
        boolean reverseLayout = abstractC0370y02 instanceof LinearLayoutManager ? ((LinearLayoutManager) abstractC0370y02).getReverseLayout() : false;
        boolean z11 = !reverseLayout ? j() <= 0 : j() + childCount >= this.mAdapter.getItemCount();
        if (z11 || childCount <= 0) {
            return z11;
        }
        getDecoratedBoundsWithMargins(getChildAt(reverseLayout ? childCount - 1 : 0), this.mChildBound);
        if (!zCanScrollHorizontally ? this.mChildBound.top >= this.mListPadding.top : !z9 ? this.mChildBound.left >= this.mListPadding.left : this.mChildBound.right <= getRight() - this.mListPadding.right && this.mChildBound.right <= getWidth() - this.mListPadding.right) {
            z10 = false;
        }
        return z10;
    }

    public final void fillRemainingScrollValues(R0 r02) {
        if (getScrollState() != 2) {
            r02.getClass();
            return;
        }
        OverScroller overScroller = this.mViewFlinger.f9027g;
        overScroller.getFinalX();
        overScroller.getCurrX();
        r02.getClass();
        overScroller.getFinalY();
        overScroller.getCurrY();
    }

    public View findChildViewUnder(float f2, float f7) {
        for (int iE = this.mChildHelper.e() - 1; iE >= 0; iE--) {
            View viewD = this.mChildHelper.d(iE);
            float translationX = viewD.getTranslationX();
            float translationY = viewD.getTranslationY();
            if (f2 >= viewD.getLeft() + translationX && f2 <= viewD.getRight() + translationX && f7 >= viewD.getTop() + translationY && f7 <= viewD.getBottom() + translationY) {
                return viewD;
            }
        }
        return null;
    }

    public View findClickableChildUnder(MotionEvent motionEvent) {
        View viewFindChildViewUnder = findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (viewFindChildViewUnder == null || !viewFindChildViewUnder.isEnabled()) {
            return null;
        }
        View viewFindClickableChildUnder = findClickableChildUnder(viewFindChildViewUnder, motionEvent.getX(), motionEvent.getY());
        if (viewFindClickableChildUnder != null && viewFindClickableChildUnder != viewFindChildViewUnder) {
            if (viewFindClickableChildUnder.getHeight() * viewFindClickableChildUnder.getWidth() < ((double) (viewFindChildViewUnder.getHeight() * viewFindChildViewUnder.getWidth())) * 0.5d) {
                return null;
            }
        }
        return viewFindClickableChildUnder;
    }

    public View findClickableOfChild(View view) {
        if (view.isClickable()) {
            return view;
        }
        View viewFindClickableOfChild = null;
        if (view instanceof ViewGroup) {
            int i5 = 0;
            while (true) {
                ViewGroup viewGroup = (ViewGroup) view;
                if (i5 >= viewGroup.getChildCount() || (viewFindClickableOfChild = findClickableOfChild(viewGroup.getChildAt(i5))) != null) {
                    break;
                }
                i5++;
            }
        }
        return viewFindClickableOfChild;
    }

    public View findContainingItemView(View view) {
        ViewParent parent = view.getParent();
        while (parent != null && parent != this && (parent instanceof View)) {
            view = parent;
            parent = view.getParent();
        }
        if (parent == this) {
            return view;
        }
        return null;
    }

    public V0 findContainingViewHolder(View view) {
        View viewFindContainingItemView = findContainingItemView(view);
        if (viewFindContainingItemView == null) {
            return null;
        }
        return getChildViewHolder(viewFindContainingItemView);
    }

    public int findFirstVisibleItemPosition() {
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) abstractC0370y0).findFirstVisibleItemPosition();
        }
        if (abstractC0370y0 instanceof StaggeredGridLayoutManager) {
            return ((StaggeredGridLayoutManager) abstractC0370y0).k()[0];
        }
        return -1;
    }

    public int findLastVisibleItemPosition() {
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) abstractC0370y0).findLastVisibleItemPosition();
        }
        if (!(abstractC0370y0 instanceof StaggeredGridLayoutManager)) {
            return -1;
        }
        StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager) abstractC0370y0;
        int[] iArr = new int[staggeredGridLayoutManager.f9002e];
        for (int i5 = 0; i5 < staggeredGridLayoutManager.f9002e; i5++) {
            j1 j1Var = staggeredGridLayoutManager.f9003f[i5];
            boolean z9 = j1Var.f9177f.f9008l;
            ArrayList arrayList = j1Var.f9172a;
            iArr[i5] = z9 ? j1Var.e(0, arrayList.size(), true, false) : j1Var.e(arrayList.size() - 1, -1, true, false);
        }
        return iArr[0];
    }

    public V0 findViewHolderForAdapterPosition(int i5) {
        V0 v02 = null;
        if (this.mDataSetHasChangedAfterLayout) {
            return null;
        }
        int iH = this.mChildHelper.h();
        for (int i7 = 0; i7 < iH; i7++) {
            V0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.g(i7));
            if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && getAdapterPositionInRecyclerView(childViewHolderInt) == i5) {
                if (!this.mChildHelper.k(childViewHolderInt.itemView)) {
                    return childViewHolderInt;
                }
                v02 = childViewHolderInt;
            }
        }
        return v02;
    }

    public V0 findViewHolderForItemId(long j5) {
        AbstractC0341j0 abstractC0341j0 = this.mAdapter;
        V0 v02 = null;
        if (abstractC0341j0 != null && abstractC0341j0.hasStableIds()) {
            int iH = this.mChildHelper.h();
            for (int i5 = 0; i5 < iH; i5++) {
                V0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.g(i5));
                if (childViewHolderInt != null && !childViewHolderInt.isRemoved() && childViewHolderInt.getItemId() == j5) {
                    if (!this.mChildHelper.k(childViewHolderInt.itemView)) {
                        return childViewHolderInt;
                    }
                    v02 = childViewHolderInt;
                }
            }
        }
        return v02;
    }

    public V0 findViewHolderForLayoutPosition(int i5) {
        return findViewHolderForPosition(i5, false);
    }

    @Deprecated
    public V0 findViewHolderForPosition(int i5) {
        return findViewHolderForPosition(i5, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public boolean fling(int i5, int i7) {
        int iMax;
        int i9;
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 == null) {
            Log.e(TAG, "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return false;
        }
        if (this.mLayoutSuppressed) {
            return false;
        }
        int iCanScrollHorizontally = abstractC0370y0.canScrollHorizontally();
        boolean zCanScrollVertically = this.mLayout.canScrollVertically();
        if (iCanScrollHorizontally == 0 || Math.abs(i5) < this.mMinFlingVelocity) {
            i5 = 0;
        }
        if (!zCanScrollVertically || Math.abs(i7) < this.mMinFlingVelocity) {
            i7 = 0;
        }
        if (i5 == 0 && i7 == 0) {
            return false;
        }
        if (i5 == 0) {
            iMax = 0;
        } else {
            EdgeEffect edgeEffect = this.mLeftGlow;
            if (edgeEffect == null || AbstractC0527a.q(edgeEffect) == 0.0f) {
                EdgeEffect edgeEffect2 = this.mRightGlow;
                if (edgeEffect2 != null && AbstractC0527a.q(edgeEffect2) != 0.0f) {
                    if (B(this.mRightGlow, i5, getWidth())) {
                        this.mRightGlow.onAbsorb(i5);
                        i5 = 0;
                    }
                    iMax = i5;
                    i5 = 0;
                }
                iMax = 0;
            } else {
                int i10 = -i5;
                if (B(this.mLeftGlow, i10, getWidth())) {
                    this.mLeftGlow.onAbsorb(i10);
                    i5 = 0;
                }
                iMax = i5;
                i5 = 0;
            }
        }
        if (i7 == 0) {
            i9 = i7;
            i7 = 0;
        } else {
            EdgeEffect edgeEffect3 = this.mTopGlow;
            if (edgeEffect3 == null || AbstractC0527a.q(edgeEffect3) == 0.0f) {
                EdgeEffect edgeEffect4 = this.mBottomGlow;
                if (edgeEffect4 != null && AbstractC0527a.q(edgeEffect4) != 0.0f) {
                    if (B(this.mBottomGlow, i7, getHeight())) {
                        this.mBottomGlow.onAbsorb(i7);
                        i7 = 0;
                    }
                    i9 = 0;
                }
                i9 = i7;
                i7 = 0;
            } else {
                int i11 = -i7;
                if (B(this.mTopGlow, i11, getHeight())) {
                    this.mTopGlow.onAbsorb(i11);
                    i7 = 0;
                }
                i9 = 0;
            }
        }
        if (iMax != 0 || i7 != 0) {
            int i12 = this.mMaxFlingVelocity;
            iMax = Math.max(-i12, Math.min(iMax, i12));
            int i13 = this.mMaxFlingVelocity;
            i7 = Math.max(-i13, Math.min(i7, i13));
            this.mViewFlinger.a(iMax, i7);
        }
        if (i5 == 0 && i9 == 0) {
            return (iMax == 0 && i7 == 0) ? false : true;
        }
        float f2 = i5;
        float f7 = i9;
        if (!dispatchNestedPreFling(f2, f7)) {
            boolean z9 = iCanScrollHorizontally != 0 || zCanScrollVertically;
            dispatchNestedFling(f2, f7, z9);
            B0 b02 = this.mOnFlingListener;
            if (b02 != null && b02.onFling(i5, i9)) {
                return true;
            }
            if (z9) {
                if (zCanScrollVertically) {
                    iCanScrollHorizontally = (iCanScrollHorizontally == true ? 1 : 0) | 2;
                }
                startNestedScroll(iCanScrollHorizontally, 1);
                int i14 = this.mMaxFlingVelocity;
                int iMax2 = Math.max(-i14, Math.min(i5, i14));
                int i15 = this.mMaxFlingVelocity;
                this.mViewFlinger.a(iMax2, Math.max(-i15, Math.min(i9, i15)));
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:154:0x01c1  */
    @Override // android.view.ViewGroup, android.view.ViewParent
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View focusSearch(android.view.View r17, int r18) {
        /*
            Method dump skipped, instruction units count: 514
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.focusSearch(android.view.View, int):android.view.View");
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 != null) {
            return abstractC0370y0.generateDefaultLayoutParams();
        }
        throw new IllegalStateException(A8.l.p(this, new StringBuilder("RecyclerView has no LayoutManager")));
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 != null) {
            return abstractC0370y0.generateLayoutParams(getContext(), attributeSet);
        }
        throw new IllegalStateException(A8.l.p(this, new StringBuilder("RecyclerView has no LayoutManager")));
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "androidx.recyclerview.widget.RecyclerView";
    }

    public AbstractC0341j0 getAdapter() {
        return this.mAdapter;
    }

    public int getAdapterPositionInRecyclerView(V0 v02) {
        if (v02.hasAnyOfTheFlags(524) || !v02.isBound()) {
            return -1;
        }
        C0324b c0324b = this.mAdapterHelper;
        int i5 = v02.mPosition;
        ArrayList arrayList = c0324b.f9054b;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            C0322a c0322a = (C0322a) arrayList.get(i7);
            int i9 = c0322a.f9041a;
            if (i9 != 1) {
                if (i9 == 2) {
                    int i10 = c0322a.f9042b;
                    if (i10 <= i5) {
                        int i11 = c0322a.f9044d;
                        if (i10 + i11 > i5) {
                            return -1;
                        }
                        i5 -= i11;
                    } else {
                        continue;
                    }
                } else if (i9 == 8) {
                    int i12 = c0322a.f9042b;
                    if (i12 == i5) {
                        i5 = c0322a.f9044d;
                    } else {
                        if (i12 < i5) {
                            i5--;
                        }
                        if (c0322a.f9044d <= i5) {
                            i5++;
                        }
                    }
                }
            } else if (c0322a.f9042b <= i5) {
                i5 += c0322a.f9044d;
            }
        }
        return i5;
    }

    @Override // android.view.View
    public int getBaseline() {
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        return abstractC0370y0 != null ? abstractC0370y0.getBaseline() : super.getBaseline();
    }

    public long getChangedHolderKey(V0 v02) {
        return this.mAdapter.hasStableIds() ? v02.getItemId() : v02.mPosition;
    }

    public int getChildAdapterPosition(View view) {
        V0 childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            return childViewHolderInt.getAbsoluteAdapterPosition();
        }
        return -1;
    }

    @Override // android.view.ViewGroup
    public int getChildDrawingOrder(int i5, int i7) {
        return super.getChildDrawingOrder(i5, i7);
    }

    public long getChildItemId(View view) {
        V0 childViewHolderInt;
        AbstractC0341j0 abstractC0341j0 = this.mAdapter;
        if (abstractC0341j0 == null || !abstractC0341j0.hasStableIds() || (childViewHolderInt = getChildViewHolderInt(view)) == null) {
            return -1L;
        }
        return childViewHolderInt.getItemId();
    }

    public int getChildLayoutPosition(View view) {
        V0 childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            return childViewHolderInt.getLayoutPosition();
        }
        return -1;
    }

    @Deprecated
    public int getChildPosition(View view) {
        return getChildAdapterPosition(view);
    }

    public V0 getChildViewHolder(View view) {
        ViewParent parent = view.getParent();
        if (parent == null || parent == this) {
            return getChildViewHolderInt(view);
        }
        throw new IllegalArgumentException("View " + view + " is not a direct child of " + this);
    }

    @Override // android.view.ViewGroup
    public boolean getClipToPadding() {
        return this.mClipToPadding;
    }

    public X0 getCompatAccessibilityDelegate() {
        return this.mAccessibilityDelegate;
    }

    public void getDecoratedBoundsWithMargins(View view, Rect rect) {
        getDecoratedBoundsWithMarginsInt(view, rect);
    }

    public AbstractC0349n0 getEdgeEffectFactory() {
        return this.mEdgeEffectFactory;
    }

    public AbstractC0358s0 getItemAnimator() {
        return this.mItemAnimator;
    }

    public Rect getItemDecorInsetsForChild(View view) {
        C0372z0 c0372z0 = (C0372z0) view.getLayoutParams();
        if (!c0372z0.mInsetsDirty) {
            return c0372z0.mDecorInsets;
        }
        if (this.mState.f8984g && (c0372z0.isItemChanged() || c0372z0.isViewInvalid())) {
            return c0372z0.mDecorInsets;
        }
        Rect rect = c0372z0.mDecorInsets;
        rect.set(0, 0, 0, 0);
        int size = this.mItemDecorations.size();
        for (int i5 = 0; i5 < size; i5++) {
            this.mTempRect.set(0, 0, 0, 0);
            this.mItemDecorations.get(i5).getItemOffsets(this.mTempRect, view, this, this.mState);
            int i7 = rect.left;
            Rect rect2 = this.mTempRect;
            rect.left = i7 + rect2.left;
            rect.top += rect2.top;
            rect.right += rect2.right;
            rect.bottom += rect2.bottom;
        }
        c0372z0.mInsetsDirty = false;
        return rect;
    }

    public AbstractC0362u0 getItemDecorationAt(int i5) {
        int itemDecorationCount = getItemDecorationCount();
        if (i5 >= 0 && i5 < itemDecorationCount) {
            return this.mItemDecorations.get(i5);
        }
        throw new IndexOutOfBoundsException(i5 + " is an invalid index for size " + itemDecorationCount);
    }

    public int getItemDecorationCount() {
        return this.mItemDecorations.size();
    }

    public AbstractC0370y0 getLayoutManager() {
        return this.mLayout;
    }

    public final L0 getLongPressMultiSelectionListener() {
        return null;
    }

    public int getMaxFlingVelocity() {
        return this.mMaxFlingVelocity;
    }

    public int getMinFlingVelocity() {
        return this.mMinFlingVelocity;
    }

    public long getNanoTime() {
        if (ALLOW_THREAD_GAP_WORK) {
            return System.nanoTime();
        }
        return 0L;
    }

    public B0 getOnFlingListener() {
        return this.mOnFlingListener;
    }

    public boolean getPreserveFocusAfterLayout() {
        return this.mPreserveFocusAfterLayout;
    }

    public F0 getRecycledViewPool() {
        return this.mRecycler.d();
    }

    public int getScrollState() {
        return this.mScrollState;
    }

    public final void h() {
        m1 m1Var;
        this.mState.a(1);
        fillRemainingScrollValues(this.mState);
        this.mState.f8985i = false;
        startInterceptRequestLayout();
        o1 o1Var = this.mViewInfoStore;
        o1Var.f9205a.clear();
        o1Var.f9206b.a();
        onEnterLayoutOrScroll();
        v();
        View focusedChild = (this.mPreserveFocusAfterLayout && hasFocus() && this.mAdapter != null) ? getFocusedChild() : null;
        V0 v0FindContainingViewHolder = focusedChild == null ? null : findContainingViewHolder(focusedChild);
        if (v0FindContainingViewHolder == null) {
            R0 r02 = this.mState;
            r02.f8989m = -1L;
            r02.f8988l = -1;
            r02.f8990n = -1;
        } else {
            this.mState.f8989m = this.mAdapter.hasStableIds() ? v0FindContainingViewHolder.getItemId() : -1L;
            this.mState.f8988l = this.mDataSetHasChangedAfterLayout ? -1 : v0FindContainingViewHolder.isRemoved() ? v0FindContainingViewHolder.mOldPosition : v0FindContainingViewHolder.getAbsoluteAdapterPosition();
            R0 r03 = this.mState;
            View focusedChild2 = v0FindContainingViewHolder.itemView;
            int id = focusedChild2.getId();
            while (!focusedChild2.isFocused() && (focusedChild2 instanceof ViewGroup) && focusedChild2.hasFocus()) {
                focusedChild2 = ((ViewGroup) focusedChild2).getFocusedChild();
                if (focusedChild2.getId() != -1) {
                    id = focusedChild2.getId();
                }
            }
            r03.f8990n = id;
        }
        R0 r04 = this.mState;
        r04.h = r04.f8986j && this.mItemsChanged;
        this.mItemsChanged = false;
        this.mItemsAddedOrRemoved = false;
        r04.f8984g = r04.f8987k;
        r04.f8982e = this.mAdapter.getItemCount();
        l(this.mMinMaxLayoutPositions);
        if (this.mState.f8986j) {
            int iE = this.mChildHelper.e();
            for (int i5 = 0; i5 < iE; i5++) {
                V0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.d(i5));
                if (!childViewHolderInt.shouldIgnore() && (!childViewHolderInt.isInvalid() || this.mAdapter.hasStableIds())) {
                    AbstractC0358s0 abstractC0358s0 = this.mItemAnimator;
                    AbstractC0358s0.b(childViewHolderInt);
                    childViewHolderInt.getUnmodifiedPayloads();
                    abstractC0358s0.getClass();
                    C0356r0 c0356r0 = new C0356r0();
                    c0356r0.a(childViewHolderInt);
                    q.k kVar = this.mViewInfoStore.f9205a;
                    m1 m1VarA = (m1) kVar.getOrDefault(childViewHolderInt, null);
                    if (m1VarA == null) {
                        m1VarA = m1.a();
                        kVar.put(childViewHolderInt, m1VarA);
                    }
                    m1VarA.f9194b = c0356r0;
                    m1VarA.f9193a |= 4;
                    if (this.mState.h && childViewHolderInt.isUpdated() && !childViewHolderInt.isRemoved() && !childViewHolderInt.shouldIgnore() && !childViewHolderInt.isInvalid()) {
                        this.mViewInfoStore.f9206b.e(getChangedHolderKey(childViewHolderInt), childViewHolderInt);
                    }
                }
            }
        }
        if (this.mState.f8987k) {
            saveOldPositions();
            R0 r05 = this.mState;
            boolean z9 = r05.f8983f;
            r05.f8983f = false;
            this.mLayout.onLayoutChildren(this.mRecycler, r05);
            this.mState.f8983f = z9;
            for (int i7 = 0; i7 < this.mChildHelper.e(); i7++) {
                V0 childViewHolderInt2 = getChildViewHolderInt(this.mChildHelper.d(i7));
                if (!childViewHolderInt2.shouldIgnore() && ((m1Var = (m1) this.mViewInfoStore.f9205a.getOrDefault(childViewHolderInt2, null)) == null || (m1Var.f9193a & 4) == 0)) {
                    AbstractC0358s0.b(childViewHolderInt2);
                    boolean zHasAnyOfTheFlags = childViewHolderInt2.hasAnyOfTheFlags(8192);
                    AbstractC0358s0 abstractC0358s02 = this.mItemAnimator;
                    childViewHolderInt2.getUnmodifiedPayloads();
                    abstractC0358s02.getClass();
                    C0356r0 c0356r02 = new C0356r0();
                    c0356r02.a(childViewHolderInt2);
                    if (zHasAnyOfTheFlags) {
                        recordAnimationInfoIfBouncedHiddenView(childViewHolderInt2, c0356r02);
                    } else {
                        q.k kVar2 = this.mViewInfoStore.f9205a;
                        m1 m1VarA2 = (m1) kVar2.getOrDefault(childViewHolderInt2, null);
                        if (m1VarA2 == null) {
                            m1VarA2 = m1.a();
                            kVar2.put(childViewHolderInt2, m1VarA2);
                        }
                        m1VarA2.f9193a |= 2;
                        m1VarA2.f9194b = c0356r02;
                    }
                }
            }
            clearOldPositions();
        } else {
            clearOldPositions();
        }
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        this.mState.f8981d = 2;
    }

    public boolean hasFixedSize() {
        return this.mHasFixedSize;
    }

    @Override // android.view.View
    public boolean hasNestedScrollingParent() {
        return getScrollingChildHelper().g(0);
    }

    public boolean hasPendingAdapterUpdates() {
        return !this.mFirstLayoutComplete || this.mDataSetHasChangedAfterLayout || this.mAdapterHelper.g();
    }

    public final void i() {
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        this.mState.a(6);
        this.mAdapterHelper.c();
        this.mState.f8982e = this.mAdapter.getItemCount();
        this.mState.f8980c = 0;
        if (this.mPendingSavedState != null && this.mAdapter.canRestoreState()) {
            Parcelable parcelable = this.mPendingSavedState.f8909e;
            if (parcelable != null) {
                this.mLayout.onRestoreInstanceState(parcelable);
            }
            this.mPendingSavedState = null;
        }
        R0 r02 = this.mState;
        r02.f8984g = false;
        this.mLayout.onLayoutChildren(this.mRecycler, r02);
        R0 r03 = this.mState;
        r03.f8983f = false;
        r03.f8986j = r03.f8986j && this.mItemAnimator != null;
        r03.f8981d = 4;
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
    }

    public void initAdapterManager() {
        this.mAdapterHelper = new C0324b(new C0327c0(this));
    }

    public void initFastScroller(StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2) {
        if (stateListDrawable == null || drawable == null || stateListDrawable2 == null || drawable2 == null) {
            throw new IllegalArgumentException(A8.l.p(this, new StringBuilder("Trying to set fast scroller without both required drawables.")));
        }
        Resources resources = getContext().getResources();
        new C(this, stateListDrawable, drawable, stateListDrawable2, drawable2, resources.getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.fastscroll_default_thickness), resources.getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.fastscroll_minimum_range), resources.getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.fastscroll_margin));
    }

    public void invalidateGlows() {
        this.mBottomGlow = null;
        this.mTopGlow = null;
        this.mRightGlow = null;
        this.mLeftGlow = null;
    }

    public void invalidateItemDecorations() {
        if (this.mItemDecorations.size() == 0) {
            return;
        }
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 != null) {
            abstractC0370y0.assertNotInLayoutOrScroll("Cannot invalidate item decorations during a scroll or layout");
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public boolean isAccessibilityEnabled() {
        AccessibilityManager accessibilityManager = this.mAccessibilityManager;
        return accessibilityManager != null && accessibilityManager.isEnabled();
    }

    public boolean isAnimating() {
        AbstractC0358s0 abstractC0358s0 = this.mItemAnimator;
        return abstractC0358s0 != null && abstractC0358s0.f();
    }

    @Override // android.view.View
    public boolean isAttachedToWindow() {
        return this.mIsAttached;
    }

    public boolean isComputingLayout() {
        return this.mLayoutOrScrollCounter > 0;
    }

    @Deprecated
    public boolean isLayoutFrozen() {
        return isLayoutSuppressed();
    }

    @Override // android.view.ViewGroup
    public final boolean isLayoutSuppressed() {
        return this.mLayoutSuppressed;
    }

    @Override // android.view.View
    public boolean isNestedScrollingEnabled() {
        return getScrollingChildHelper().f7247d;
    }

    @Override // android.view.View
    public boolean isVerticalScrollBarEnabled() {
        b1 b1Var = this.mFastScroller;
        return b1Var != null ? !b1Var.j() && super.isVerticalScrollBarEnabled() : super.isVerticalScrollBarEnabled();
    }

    public final int j() {
        int iFindFirstVisibleItemPosition;
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 instanceof LinearLayoutManager) {
            iFindFirstVisibleItemPosition = ((LinearLayoutManager) abstractC0370y0).findFirstVisibleItemPosition();
        } else if (abstractC0370y0 instanceof StaggeredGridLayoutManager) {
            iFindFirstVisibleItemPosition = ((StaggeredGridLayoutManager) this.mLayout).k()[abstractC0370y0.getLayoutDirection() == 1 ? ((StaggeredGridLayoutManager) this.mLayout).f9002e - 1 : 0];
        } else {
            iFindFirstVisibleItemPosition = 0;
        }
        if (iFindFirstVisibleItemPosition == -1) {
            return 0;
        }
        return iFindFirstVisibleItemPosition;
    }

    public void jumpToPositionForSmoothScroller(int i5) {
        if (this.mLayout == null) {
            return;
        }
        setScrollState(2);
        this.mLayout.scrollToPosition(i5);
        awakenScrollBars();
    }

    public final boolean k(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        int size = this.mOnItemTouchListeners.size();
        for (int i5 = 0; i5 < size; i5++) {
            C0 c02 = this.mOnItemTouchListeners.get(i5);
            if (c02.c(motionEvent) && action != 3) {
                this.mInterceptingOnItemTouchListener = c02;
                return true;
            }
        }
        return false;
    }

    public final void l(int[] iArr) {
        int iE = this.mChildHelper.e();
        if (iE == 0) {
            iArr[0] = -1;
            iArr[1] = -1;
            return;
        }
        int i5 = Integer.MAX_VALUE;
        int i7 = Integer.MIN_VALUE;
        for (int i9 = 0; i9 < iE; i9++) {
            V0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.d(i9));
            if (!childViewHolderInt.shouldIgnore()) {
                int layoutPosition = childViewHolderInt.getLayoutPosition();
                if (layoutPosition < i5) {
                    i5 = layoutPosition;
                }
                if (layoutPosition > i7) {
                    i7 = layoutPosition;
                }
            }
        }
        iArr[0] = i5;
        iArr[1] = i7;
    }

    public final int m(boolean z9, boolean z10) {
        int i5 = z9 ? z10 ? 2 : 3 : z10 ? 4 : 1;
        float f2 = this.mPointerIconRotation;
        if (f2 == 0.0f) {
            return this.mHoverScrollArrows[AbstractC0152g1.g(i5)];
        }
        boolean z11 = f2 < 0.0f;
        int iG = ((AbstractC0152g1.g(i5) * (z11 ? -1 : 1)) + ((int) ((f2 + (z11 ? -45 : 45)) / 90.0f))) % 4;
        if (iG == 0) {
            return this.mHoverScrollArrows[iG];
        }
        int[] iArr = this.mHoverScrollArrows;
        if (z11) {
            iG += 4;
        }
        return iArr[iG];
    }

    public void markItemDecorInsetsDirty() {
        int iH = this.mChildHelper.h();
        for (int i5 = 0; i5 < iH; i5++) {
            ((C0372z0) this.mChildHelper.g(i5).getLayoutParams()).mInsetsDirty = true;
        }
        ArrayList arrayList = this.mRecycler.f8893c;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            C0372z0 c0372z0 = (C0372z0) ((V0) arrayList.get(i7)).itemView.getLayoutParams();
            if (c0372z0 != null) {
                c0372z0.mInsetsDirty = true;
            }
        }
    }

    public void markKnownViewsInvalid() {
        int iH = this.mChildHelper.h();
        for (int i5 = 0; i5 < iH; i5++) {
            V0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.g(i5));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.addFlags(6);
            }
        }
        markItemDecorInsetsDirty();
        G0 g02 = this.mRecycler;
        ArrayList arrayList = g02.f8893c;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            V0 v02 = (V0) arrayList.get(i7);
            if (v02 != null) {
                v02.addFlags(6);
                v02.addChangePayload(null);
            }
        }
        AbstractC0341j0 abstractC0341j0 = g02.h.mAdapter;
        if (abstractC0341j0 == null || !abstractC0341j0.hasStableIds()) {
            g02.h();
        }
    }

    public final void n(boolean z9, boolean z10) {
        int i5 = 2;
        Drawable drawable = this.mContext.getResources().getDrawable(z10 ? com.samsung.android.keyscafe.R.drawable.sesl_list_go_to_top_light : com.samsung.android.keyscafe.R.drawable.sesl_list_go_to_top_dark);
        this.mGoToTopImage = drawable;
        if (drawable != null) {
            if (this.mGoToTopView == null) {
                this.mGoToTopView = new ImageView(this.mContext);
            }
            this.mGoToTopView.setBackground(this.mContext.getResources().getDrawable(z10 ? com.samsung.android.keyscafe.R.drawable.sesl_go_to_top_background_light : com.samsung.android.keyscafe.R.drawable.sesl_go_to_top_background_dark, null));
            this.mGoToTopView.setElevation(this.mGoToTopElevation);
            this.mGoToTopView.setImageDrawable(this.mGoToTopImage);
            if (z9) {
                this.mGoToTopView.setAlpha(0.0f);
                if (!this.mEnableGoToTop) {
                    getOverlay().add(this.mGoToTopView);
                }
            } else if (this.mEnableGoToTop) {
                getOverlay().remove(this.mGoToTopView);
            }
            this.mEnableGoToTop = z9;
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.mGoToTopFadeInAnimator = valueAnimatorOfFloat;
            valueAnimatorOfFloat.setDuration(333L);
            this.mGoToTopFadeInAnimator.setInterpolator(AbstractC0510a.f10750a);
            this.mGoToTopFadeInAnimator.addUpdateListener(new C0348n(this, i5));
            ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(1.0f, 0.0f);
            this.mGoToTopFadeOutAnimator = valueAnimatorOfFloat2;
            valueAnimatorOfFloat2.setDuration(150L);
            this.mGoToTopFadeOutAnimator.setInterpolator(LINEAR_INTERPOLATOR);
            this.mGoToTopFadeOutAnimator.addUpdateListener(new C0348n(this, 3));
            this.mGoToTopFadeOutAnimator.addListener(new C0331e0(this, 0));
        }
    }

    public void nestedScrollBy(int i5, int i7) {
        s(i5, i7, null);
    }

    public final boolean o() {
        String string;
        AccessibilityManager accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        return (accessibilityManager == null || !accessibilityManager.isEnabled() || (string = Settings.Secure.getString(getContext().getContentResolver(), "enabled_accessibility_services")) == null || !(string.matches("(?i).*com.samsung.accessibility/com.samsung.android.app.talkback.TalkBackService.*") || string.matches("(?i).*com.samsung.android.accessibility.talkback/com.samsung.android.marvin.talkback.TalkBackService.*") || string.matches("(?i).*com.google.android.marvin.talkback.TalkBackService.*") || string.matches("(?i).*com.samsung.accessibility/com.samsung.accessibility.universalswitch.UniversalSwitchService.*"))) && getHeight() > this.mSeslOverlayFeatureHeight;
    }

    public void offsetChildrenHorizontal(int i5) {
        int iE = this.mChildHelper.e();
        for (int i7 = 0; i7 < iE; i7++) {
            this.mChildHelper.d(i7).offsetLeftAndRight(i5);
        }
    }

    public void offsetChildrenVertical(int i5) {
        int iE = this.mChildHelper.e();
        for (int i7 = 0; i7 < iE; i7++) {
            this.mChildHelper.d(i7).offsetTopAndBottom(i5);
        }
    }

    public void offsetPositionRecordsForInsert(int i5, int i7) {
        int iH = this.mChildHelper.h();
        for (int i9 = 0; i9 < iH; i9++) {
            V0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.g(i9));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && childViewHolderInt.mPosition >= i5) {
                if (sVerboseLoggingEnabled) {
                    Log.d(TAG, "offsetPositionRecordsForInsert attached child " + i9 + " holder " + childViewHolderInt + " now at position " + (childViewHolderInt.mPosition + i7));
                }
                childViewHolderInt.offsetPosition(i7, false);
                this.mState.f8983f = true;
            }
        }
        ArrayList arrayList = this.mRecycler.f8893c;
        int size = arrayList.size();
        for (int i10 = 0; i10 < size; i10++) {
            V0 v02 = (V0) arrayList.get(i10);
            if (v02 != null && v02.mPosition >= i5) {
                if (sVerboseLoggingEnabled) {
                    Log.d(TAG, "offsetPositionRecordsForInsert cached " + i10 + " holder " + v02 + " now at position " + (v02.mPosition + i7));
                }
                v02.offsetPosition(i7, true);
            }
        }
        requestLayout();
    }

    public void offsetPositionRecordsForMove(int i5, int i7) {
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int iH = this.mChildHelper.h();
        int i16 = -1;
        if (i5 < i7) {
            i10 = i5;
            i9 = i7;
            i11 = -1;
        } else {
            i9 = i5;
            i10 = i7;
            i11 = 1;
        }
        for (int i17 = 0; i17 < iH; i17++) {
            V0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.g(i17));
            if (childViewHolderInt != null && (i15 = childViewHolderInt.mPosition) >= i10 && i15 <= i9) {
                if (sVerboseLoggingEnabled) {
                    Log.d(TAG, "offsetPositionRecordsForMove attached child " + i17 + " holder " + childViewHolderInt);
                }
                if (childViewHolderInt.mPosition == i5) {
                    childViewHolderInt.offsetPosition(i7 - i5, false);
                } else {
                    childViewHolderInt.offsetPosition(i11, false);
                }
                this.mState.f8983f = true;
            }
        }
        G0 g02 = this.mRecycler;
        g02.getClass();
        if (i5 < i7) {
            i13 = i5;
            i12 = i7;
        } else {
            i12 = i5;
            i16 = 1;
            i13 = i7;
        }
        ArrayList arrayList = g02.f8893c;
        int size = arrayList.size();
        for (int i18 = 0; i18 < size; i18++) {
            V0 v02 = (V0) arrayList.get(i18);
            if (v02 != null && (i14 = v02.mPosition) >= i13 && i14 <= i12) {
                if (i14 == i5) {
                    v02.offsetPosition(i7 - i5, false);
                } else {
                    v02.offsetPosition(i16, false);
                }
                if (sVerboseLoggingEnabled) {
                    Log.d(TAG, "offsetPositionRecordsForMove cached child " + i18 + " holder " + v02);
                }
            }
        }
        requestLayout();
    }

    public void offsetPositionRecordsForRemove(int i5, int i7, boolean z9) {
        int i9 = i5 + i7;
        int iH = this.mChildHelper.h();
        for (int i10 = 0; i10 < iH; i10++) {
            V0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.g(i10));
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore()) {
                int i11 = childViewHolderInt.mPosition;
                if (i11 >= i9) {
                    if (sVerboseLoggingEnabled) {
                        Log.d(TAG, "offsetPositionRecordsForRemove attached child " + i10 + " holder " + childViewHolderInt + " now at position " + (childViewHolderInt.mPosition - i7));
                    }
                    childViewHolderInt.offsetPosition(-i7, z9);
                    this.mState.f8983f = true;
                } else if (i11 >= i5) {
                    if (sVerboseLoggingEnabled) {
                        Log.d(TAG, "offsetPositionRecordsForRemove attached child " + i10 + " holder " + childViewHolderInt + " now REMOVED");
                    }
                    childViewHolderInt.flagRemovedAndOffsetPosition(i5 - 1, -i7, z9);
                    this.mState.f8983f = true;
                }
            }
        }
        G0 g02 = this.mRecycler;
        ArrayList arrayList = g02.f8893c;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            V0 v02 = (V0) arrayList.get(size);
            if (v02 != null) {
                int i12 = v02.mPosition;
                if (i12 >= i9) {
                    if (sVerboseLoggingEnabled) {
                        Log.d(TAG, "offsetPositionRecordsForRemove cached " + size + " holder " + v02 + " now at position " + (v02.mPosition - i7));
                    }
                    v02.offsetPosition(-i7, z9);
                } else if (i12 >= i5) {
                    v02.addFlags(8);
                    g02.i(size);
                }
            }
        }
        requestLayout();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        b1 b1Var;
        super.onAttachedToWindow();
        this.mLayoutOrScrollCounter = 0;
        this.mIsAttached = true;
        this.mFirstLayoutComplete = this.mFirstLayoutComplete && !isLayoutRequested();
        this.mRecycler.f();
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 != null) {
            abstractC0370y0.dispatchAttachedToWindow(this);
        }
        this.mPostedAnimatorRunner = false;
        if (ALLOW_THREAD_GAP_WORK) {
            ThreadLocal threadLocal = F.f8883i;
            F f2 = (F) threadLocal.get();
            this.mGapWorker = f2;
            if (f2 == null) {
                F f7 = new F();
                f7.f8885e = new ArrayList();
                f7.h = new ArrayList();
                this.mGapWorker = f7;
                WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
                Display display = getDisplay();
                if (!isInEditMode() && display != null) {
                    float refreshRate = display.getRefreshRate();
                    f = refreshRate >= 30.0f ? refreshRate : 60.0f;
                    if (this.mIsNeedCheckLatency) {
                        this.mFrameLatency = 1000.0f / f;
                        this.mIsNeedCheckLatency = false;
                    }
                }
                F f9 = this.mGapWorker;
                f9.f8887g = (long) (1.0E9f / f);
                threadLocal.set(f9);
            }
            F f10 = this.mGapWorker;
            f10.getClass();
            boolean z9 = sDebugAssertionsEnabled;
            ArrayList arrayList = f10.f8885e;
            if (z9 && arrayList.contains(this)) {
                throw new IllegalStateException("RecyclerView already present in worker list!");
            }
            arrayList.add(this);
            AbstractC0370y0 abstractC0370y02 = this.mLayout;
            if (abstractC0370y02 == null || abstractC0370y02.getLayoutDirection() != 1 || (b1Var = this.mFastScroller) == null) {
                return;
            }
            b1Var.r(getVerticalScrollbarPosition());
        }
    }

    public void onChildAttachedToWindow(View view) {
    }

    public void onChildDetachedFromWindow(View view) {
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        C0353p0 c0353p0;
        F f2;
        super.onDetachedFromWindow();
        AbstractC0358s0 abstractC0358s0 = this.mItemAnimator;
        if (abstractC0358s0 != null) {
            abstractC0358s0.e();
        }
        stopScroll();
        this.mIsAttached = false;
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 != null) {
            abstractC0370y0.dispatchDetachedFromWindow(this, this.mRecycler);
        }
        this.mPendingAccessibilityImportanceChange.clear();
        removeCallbacks(this.mItemAnimatorRunner);
        this.mViewInfoStore.getClass();
        while (m1.f9192d.h() != null) {
        }
        G0 g02 = this.mRecycler;
        int i5 = 0;
        while (true) {
            ArrayList arrayList = g02.f8893c;
            if (i5 >= arrayList.size()) {
                break;
            }
            AbstractC0752G.m(((V0) arrayList.get(i5)).itemView);
            i5++;
        }
        g02.g(g02.h.mAdapter, false);
        int i7 = 0;
        while (i7 < getChildCount()) {
            int i9 = i7 + 1;
            View childAt = getChildAt(i7);
            if (childAt == null) {
                throw new IndexOutOfBoundsException();
            }
            Q.a aVar = (Q.a) childAt.getTag(com.samsung.android.keyscafe.R.id.pooling_container_listener_holder_tag);
            if (aVar == null) {
                aVar = new Q.a();
                childAt.setTag(com.samsung.android.keyscafe.R.id.pooling_container_listener_holder_tag, aVar);
            }
            ArrayList arrayList2 = aVar.f2894a;
            int iW = U6.o.W(arrayList2);
            if (-1 < iW) {
                A8.l.z(arrayList2.get(iW));
                throw null;
            }
            i7 = i9;
        }
        if (ALLOW_THREAD_GAP_WORK && (f2 = this.mGapWorker) != null) {
            boolean zRemove = f2.f8885e.remove(this);
            if (sDebugAssertionsEnabled && !zRemove) {
                throw new IllegalStateException("RecyclerView removal failed!");
            }
            this.mGapWorker = null;
        }
        if (this.mIndexTipEnabled && (c0353p0 = this.mIndexTip) != null) {
            c0353p0.f9240z = false;
            c0353p0.removeCallbacks(c0353p0.f9219I);
            c0353p0.setAlpha(0.0f);
            c0353p0.invalidate();
        }
        this.mIsNeedCheckLatency = true;
        if (this.mIsRecoilSupported) {
            ArrayList<f.e> arrayList3 = this.mItemAnimatorHolder.f10756a;
            for (f.e eVar : arrayList3) {
                boolean zB = eVar.b();
                ValueAnimator valueAnimator = eVar.f10762d;
                if (zB) {
                    valueAnimator.end();
                }
                valueAnimator.removeAllUpdateListeners();
            }
            arrayList3.clear();
        }
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int size = this.mItemDecorations.size();
        for (int i5 = 0; i5 < size; i5++) {
            this.mItemDecorations.get(i5).onDraw(canvas, this, this.mState);
        }
        if (this.mIsNeedCheckLatency) {
            WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
            Display display = getDisplay();
            if (display != null) {
                this.mFrameLatency = 1000.0f / display.getRefreshRate();
            } else {
                this.mFrameLatency = FRAME_LATENCY_LIMIT;
            }
            this.mIsNeedCheckLatency = false;
        }
        b1 b1Var = this.mFastScroller;
        if (b1Var != null) {
            b1Var.w();
        }
    }

    public void onEnterLayoutOrScroll() {
        this.mLayoutOrScrollCounter++;
    }

    public void onExitLayoutOrScroll() {
        onExitLayoutOrScroll(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0086  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onGenericMotionEvent(android.view.MotionEvent r12) {
        /*
            r11 = this;
            androidx.recyclerview.widget.y0 r0 = r11.mLayout
            r1 = 0
            if (r0 != 0) goto L6
            return r1
        L6:
            boolean r0 = r11.mLayoutSuppressed
            if (r0 == 0) goto Lb
            return r1
        Lb:
            int r0 = r12.getAction()
            r2 = 8
            if (r0 != r2) goto L91
            int r0 = r12.getSource()
            r2 = 2
            r0 = r0 & r2
            r3 = 0
            if (r0 == 0) goto L3e
            androidx.recyclerview.widget.y0 r0 = r11.mLayout
            boolean r0 = r0.canScrollVertically()
            if (r0 == 0) goto L2c
            r0 = 9
            float r0 = r12.getAxisValue(r0)
            float r0 = -r0
            goto L2d
        L2c:
            r0 = r3
        L2d:
            androidx.recyclerview.widget.y0 r4 = r11.mLayout
            boolean r4 = r4.canScrollHorizontally()
            if (r4 == 0) goto L3c
            r4 = 10
            float r4 = r12.getAxisValue(r4)
            goto L64
        L3c:
            r4 = r3
            goto L64
        L3e:
            int r0 = r12.getSource()
            r4 = 4194304(0x400000, float:5.877472E-39)
            r0 = r0 & r4
            if (r0 == 0) goto L62
            r0 = 26
            float r0 = r12.getAxisValue(r0)
            androidx.recyclerview.widget.y0 r4 = r11.mLayout
            boolean r4 = r4.canScrollVertically()
            if (r4 == 0) goto L57
            float r0 = -r0
            goto L3c
        L57:
            androidx.recyclerview.widget.y0 r4 = r11.mLayout
            boolean r4 = r4.canScrollHorizontally()
            if (r4 == 0) goto L62
            r4 = r0
            r0 = r3
            goto L64
        L62:
            r0 = r3
            r4 = r0
        L64:
            int r5 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r5 != 0) goto L6c
            int r3 = (r4 > r3 ? 1 : (r4 == r3 ? 0 : -1))
            if (r3 == 0) goto L91
        L6c:
            r3 = 1
            if (r5 == 0) goto L70
            goto L71
        L70:
            r2 = r3
        L71:
            r11.startNestedScroll(r2, r3)
            float r2 = r11.mScaledHorizontalScrollFactor
            float r2 = r2 * r4
            int r6 = (int) r2
            float r2 = r11.mScaledVerticalScrollFactor
            float r2 = r2 * r0
            int r7 = (int) r2
            r10 = 1
            r8 = 0
            r9 = 0
            r5 = r11
            boolean r2 = r5.dispatchNestedPreScroll(r6, r7, r8, r9, r10)
            if (r2 != 0) goto L91
            float r2 = r11.mScaledHorizontalScrollFactor
            float r4 = r4 * r2
            int r2 = (int) r4
            float r3 = r11.mScaledVerticalScrollFactor
            float r0 = r0 * r3
            int r0 = (int) r0
            r11.s(r2, r0, r12)
        L91:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0367  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x037f  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0386  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0090 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:98:0x01cf  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onInterceptTouchEvent(android.view.MotionEvent r18) {
        /*
            Method dump skipped, instruction units count: 1006
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i5, KeyEvent keyEvent) {
        if (i5 != 92) {
            if (i5 != 93) {
                if (i5 == 113 || i5 == 114) {
                    this.mIsCtrlKeyPressed = true;
                } else if (i5 != 122) {
                    if (i5 == 123 && keyEvent.hasNoModifiers()) {
                        u(3);
                    }
                } else if (keyEvent.hasNoModifiers()) {
                    u(2);
                }
            } else if (keyEvent.hasNoModifiers()) {
                u(1);
            }
        } else if (keyEvent.hasNoModifiers()) {
            u(0);
        }
        return super.onKeyDown(i5, keyEvent);
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i5, KeyEvent keyEvent) {
        if (i5 == 113 || i5 == 114) {
            this.mIsCtrlKeyPressed = false;
        }
        return super.onKeyUp(i5, keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        C0353p0 c0353p0;
        Trace.beginSection(TRACE_ON_LAYOUT_TAG);
        dispatchLayout();
        Trace.endSection();
        this.mFirstLayoutComplete = true;
        b1 b1Var = this.mFastScroller;
        if (b1Var != null && this.mAdapter != null) {
            int childCount = getChildCount();
            int itemCount = this.mAdapter.getItemCount();
            int i11 = b1Var.f9080Q;
            RecyclerView recyclerView = b1Var.f9096d;
            if (i11 == 0) {
                b1Var.f9080Q = recyclerView.getChildCount();
            }
            if (b1Var.f9079P != itemCount || b1Var.f9080Q != childCount) {
                b1Var.f9079P = itemCount;
                b1Var.f9080Q = childCount;
                if (itemCount - childCount > 0 && b1Var.f9069E != 2) {
                    b1Var.t(b1Var.f(recyclerView.findFirstVisibleItemPosition(), childCount, itemCount));
                }
                b1Var.x(childCount);
            }
        }
        if (z9) {
            this.mSizeChange = true;
            this.mSeslOverlayFeatureHeight = getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_recyclerview_overlay_feature_hidden_height);
            seslSetImmersiveScrollBottomPadding(0);
            setupGoToTop(-1);
            d(1);
            AbstractC0370y0 abstractC0370y0 = this.mLayout;
            if (abstractC0370y0 == null || abstractC0370y0.canScrollHorizontally()) {
                AbstractC0370y0 abstractC0370y02 = this.mLayout;
                if (abstractC0370y02 != null && abstractC0370y02.canScrollHorizontally()) {
                    getLocationInWindow(this.mWindowOffsets);
                    this.mRemainNestedScrollRange = 0;
                    this.mNestedScrollRange = 0;
                    this.mInitialTopOffsetOfScreen = this.mWindowOffsets[0];
                }
            } else {
                this.mHasNestedScrollRange = false;
                ViewParent parent = getParent();
                while (true) {
                    if (parent == null || !(parent instanceof ViewGroup)) {
                        break;
                    }
                    if (parent instanceof InterfaceC0224p) {
                        for (Class<?> superclass = parent.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
                            if (superclass.getSimpleName().equals("CoordinatorLayout")) {
                                ViewGroup viewGroup = (ViewGroup) parent;
                                viewGroup.getLocationInWindow(this.mWindowOffsets);
                                int height = viewGroup.getHeight() + this.mWindowOffsets[1];
                                getLocationInWindow(this.mWindowOffsets);
                                this.mInitialTopOffsetOfScreen = this.mWindowOffsets[1];
                                int height2 = getHeight() - (height - this.mInitialTopOffsetOfScreen);
                                this.mRemainNestedScrollRange = height2;
                                if (height2 < 0) {
                                    this.mRemainNestedScrollRange = 0;
                                }
                                this.mNestedScrollRange = this.mRemainNestedScrollRange;
                                this.mHasNestedScrollRange = true;
                            }
                        }
                    }
                    parent = parent.getParent();
                }
                if (!this.mHasNestedScrollRange) {
                    this.mInitialTopOffsetOfScreen = 0;
                    this.mRemainNestedScrollRange = 0;
                    this.mNestedScrollRange = 0;
                }
            }
            if (!this.mIndexTipEnabled || (c0353p0 = this.mIndexTip) == null) {
                return;
            }
            C0353p0.a(c0353p0, i9 - i5, i10 - i7, getPaddingLeft(), getPaddingRight());
        }
    }

    @Override // android.view.View
    public void onMeasure(int i5, int i7) {
        if (this.mLayout == null) {
            defaultOnMeasure(i5, i7);
            return;
        }
        this.mListPadding.set(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
        boolean z9 = false;
        if (this.mLayout.isAutoMeasureEnabled()) {
            int mode = View.MeasureSpec.getMode(i5);
            int mode2 = View.MeasureSpec.getMode(i7);
            this.mLayout.onMeasure(this.mRecycler, this.mState, i5, i7);
            if (mode == 1073741824 && mode2 == 1073741824) {
                z9 = true;
            }
            this.mLastAutoMeasureSkippedDueToExact = z9;
            if (z9 || this.mAdapter == null) {
                return;
            }
            if (this.mState.f8981d == 1) {
                h();
            }
            this.mLayout.setMeasureSpecs(i5, i7);
            this.mState.f8985i = true;
            i();
            this.mLayout.setMeasuredDimensionFromChildren(i5, i7);
            if (this.mLayout.shouldMeasureTwice()) {
                this.mLayout.setMeasureSpecs(View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
                this.mState.f8985i = true;
                i();
                this.mLayout.setMeasuredDimensionFromChildren(i5, i7);
            }
            this.mLastAutoMeasureNonExactMeasuredWidth = getMeasuredWidth();
            this.mLastAutoMeasureNonExactMeasuredHeight = getMeasuredHeight();
            return;
        }
        if (this.mHasFixedSize) {
            this.mLayout.onMeasure(this.mRecycler, this.mState, i5, i7);
            return;
        }
        if (this.mAdapterUpdateDuringMeasure) {
            startInterceptRequestLayout();
            onEnterLayoutOrScroll();
            v();
            onExitLayoutOrScroll();
            R0 r02 = this.mState;
            if (r02.f8987k) {
                r02.f8984g = true;
            } else {
                this.mAdapterHelper.c();
                this.mState.f8984g = false;
            }
            this.mAdapterUpdateDuringMeasure = false;
            stopInterceptRequestLayout(false);
        } else if (this.mState.f8987k) {
            setMeasuredDimension(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        AbstractC0341j0 abstractC0341j0 = this.mAdapter;
        if (abstractC0341j0 != null) {
            this.mState.f8982e = abstractC0341j0.getItemCount();
        } else {
            this.mState.f8982e = 0;
        }
        startInterceptRequestLayout();
        this.mLayout.onMeasure(this.mRecycler, this.mState, i5, i7);
        stopInterceptRequestLayout(false);
        this.mState.f8984g = false;
    }

    @Override // android.view.ViewGroup
    public boolean onRequestFocusInDescendants(int i5, Rect rect) {
        if (isComputingLayout()) {
            return false;
        }
        return super.onRequestFocusInDescendants(i5, rect);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof J0)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        J0 j0 = (J0) parcelable;
        this.mPendingSavedState = j0;
        super.onRestoreInstanceState(j0.getSuperState());
        requestLayout();
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i5) {
        super.onRtlPropertiesChanged(i5);
        b1 b1Var = this.mFastScroller;
        if (b1Var != null) {
            b1Var.r(getVerticalScrollbarPosition());
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        this.mIsNeedCheckLatency = true;
        J0 j0 = new J0(super.onSaveInstanceState());
        J0 j02 = this.mPendingSavedState;
        if (j02 != null) {
            j0.f8909e = j02.f8909e;
        } else {
            AbstractC0370y0 abstractC0370y0 = this.mLayout;
            if (abstractC0370y0 != null) {
                j0.f8909e = abstractC0370y0.onSaveInstanceState();
            } else {
                j0.f8909e = null;
            }
        }
        return j0;
    }

    public void onScrollStateChanged(int i5) {
    }

    public void onScrolled(int i5, int i7) {
    }

    @Override // android.view.View
    public void onSizeChanged(int i5, int i7, int i9, int i10) {
        super.onSizeChanged(i5, i7, i9, i10);
        if (i5 == i9 && i7 == i10) {
            return;
        }
        b1 b1Var = this.mFastScroller;
        if (b1Var != null) {
            boolean z9 = true;
            if (!b1Var.c(1) && !b1Var.c(-1)) {
                z9 = false;
            }
            b1Var.f9066B = z9;
            b1Var.d0 = -1;
            b1Var.f9098e0 = -1;
            b1Var.w();
        }
        invalidateGlows();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0129 A[PHI: r0
      0x0129: PHI (r0v48 int) = (r0v36 int), (r0v52 int) binds: [B:66:0x0112, B:70:0x0125] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:74:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0142  */
    /* JADX WARN: Type inference failed for: r10v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r10v2 */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r18) {
        /*
            Method dump skipped, instruction units count: 693
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final boolean p() {
        return o() && this.mEnableGoToTop;
    }

    public void postAnimationRunner() {
        if (this.mPostedAnimatorRunner || !this.mIsAttached) {
            return;
        }
        Runnable runnable = this.mItemAnimatorRunner;
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        postOnAnimation(runnable);
        this.mPostedAnimatorRunner = true;
    }

    public void processDataSetCompletelyChanged(boolean z9) {
        this.mDispatchItemsChangedEvent = z9 | this.mDispatchItemsChangedEvent;
        this.mDataSetHasChangedAfterLayout = true;
        markKnownViewsInvalid();
    }

    public final void q(int i5, int i7, int i9, int i10) {
        D0 d0;
        if (this.mIsNeedPenSelection) {
            if (this.mIsFirstPenMoveEvent) {
                this.mPenDragStartX = i5;
                this.mPenDragStartY = i7;
                this.mIsPenPressed = true;
                float f2 = i5;
                float f7 = i7;
                View viewFindChildViewUnder = findChildViewUnder(f2, f7);
                this.mPenTrackedChild = viewFindChildViewUnder;
                if (viewFindChildViewUnder == null) {
                    View viewSeslFindNearChildViewUnder = seslFindNearChildViewUnder(f2, f7);
                    this.mPenTrackedChild = viewSeslFindNearChildViewUnder;
                    if (viewSeslFindNearChildViewUnder == null) {
                        Log.e(TAG, "multiSelection, mPenTrackedChild is NULL");
                        this.mIsPenPressed = false;
                        this.mIsFirstPenMoveEvent = false;
                        return;
                    }
                }
                this.mPenTrackedChildPosition = getChildLayoutPosition(this.mPenTrackedChild);
                this.mPenDistanceFromTrackedChildTop = this.mPenDragStartY - this.mPenTrackedChild.getTop();
                this.mIsFirstPenMoveEvent = false;
            }
            if (this.mPenDragStartX == 0 && this.mPenDragStartY == 0) {
                this.mPenDragStartX = i5;
                this.mPenDragStartY = i7;
                this.mIsPenPressed = true;
            }
            this.mPenDragEndX = i5;
            this.mPenDragEndY = i7;
            if (i7 < 0) {
                this.mPenDragEndY = 0;
            } else if (i7 > i10) {
                this.mPenDragEndY = i10;
            }
            int i11 = this.mPenDragStartX;
            this.mPenDragBlockLeft = i11 < i5 ? i11 : i5;
            int i12 = this.mPenDragStartY;
            int i13 = this.mPenDragEndY;
            this.mPenDragBlockTop = i12 < i13 ? i12 : i13;
            if (i5 <= i11) {
                i5 = i11;
            }
            this.mPenDragBlockRight = i5;
            if (i13 > i12) {
                i12 = i13;
            }
            this.mPenDragBlockBottom = i12;
            if (i7 <= i9 + this.mHoverTopAreaHeight) {
                if (!this.mHoverAreaEnter) {
                    this.mHoverAreaEnter = true;
                    this.mHoverScrollStartTime = System.currentTimeMillis();
                    D0 d02 = this.mScrollListener;
                    if (d02 != null) {
                        d02.onScrollStateChanged(this, 1);
                    }
                }
                if (!this.mHoverHandler.hasMessages(0)) {
                    this.mHoverRecognitionStartTime = System.currentTimeMillis();
                    this.mHoverScrollDirection = 2;
                    this.mHoverHandler.sendEmptyMessage(0);
                }
            } else if (i7 >= (i10 - this.mHoverBottomAreaHeight) - this.mRemainNestedScrollRange) {
                if (!this.mHoverAreaEnter) {
                    this.mHoverAreaEnter = true;
                    this.mHoverScrollStartTime = System.currentTimeMillis();
                    D0 d03 = this.mScrollListener;
                    if (d03 != null) {
                        d03.onScrollStateChanged(this, 1);
                    }
                }
                if (!this.mHoverHandler.hasMessages(0)) {
                    this.mHoverRecognitionStartTime = System.currentTimeMillis();
                    this.mHoverScrollDirection = 1;
                    this.mHoverHandler.sendEmptyMessage(0);
                }
            } else {
                if (this.mHoverAreaEnter && (d0 = this.mScrollListener) != null) {
                    d0.onScrollStateChanged(this, 0);
                }
                this.mHoverScrollStartTime = 0L;
                this.mHoverRecognitionStartTime = 0L;
                this.mHoverAreaEnter = false;
                if (this.mHoverHandler.hasMessages(0)) {
                    this.mHoverHandler.removeMessages(0);
                    if (this.mScrollState == 1) {
                        setScrollState(0);
                    }
                }
                this.mIsHoverOverscrolled = false;
            }
            if (this.mIsPenDragBlockEnabled) {
                invalidate();
            }
        }
    }

    public final void r() {
        this.mIsPenPressed = false;
        this.mIsFirstPenMoveEvent = true;
        this.mPenDragSelectedViewPosition = -1;
        this.mPenDragSelectedItemArray.clear();
        this.mPenDragStartX = 0;
        this.mPenDragStartY = 0;
        this.mPenDragEndX = 0;
        this.mPenDragEndY = 0;
        this.mPenDragBlockLeft = 0;
        this.mPenDragBlockTop = 0;
        this.mPenDragBlockRight = 0;
        this.mPenDragBlockBottom = 0;
        this.mPenTrackedChild = null;
        this.mPenDistanceFromTrackedChildTop = 0;
        if (this.mIsPenDragBlockEnabled) {
            invalidate();
        }
        if (this.mHoverHandler.hasMessages(0)) {
            this.mHoverHandler.removeMessages(0);
        }
    }

    public void recordAnimationInfoIfBouncedHiddenView(V0 v02, C0356r0 c0356r0) {
        v02.setFlags(0, 8192);
        if (this.mState.h && v02.isUpdated() && !v02.isRemoved() && !v02.shouldIgnore()) {
            this.mViewInfoStore.f9206b.e(getChangedHolderKey(v02), v02);
        }
        q.k kVar = this.mViewInfoStore.f9205a;
        m1 m1VarA = (m1) kVar.getOrDefault(v02, null);
        if (m1VarA == null) {
            m1VarA = m1.a();
            kVar.put(v02, m1VarA);
        }
        m1VarA.f9194b = c0356r0;
        m1VarA.f9193a |= 4;
    }

    public void removeAndRecycleViews() {
        AbstractC0358s0 abstractC0358s0 = this.mItemAnimator;
        if (abstractC0358s0 != null) {
            abstractC0358s0.e();
        }
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 != null) {
            abstractC0370y0.removeAndRecycleAllViews(this.mRecycler);
        }
        AbstractC0370y0 abstractC0370y02 = this.mLayout;
        if (abstractC0370y02 != null) {
            abstractC0370y02.removeAndRecycleScrapInt(this.mRecycler);
        }
        this.mRecycler.b();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean removeAnimatingView(android.view.View r8) {
        /*
            r7 = this;
            r7.startInterceptRequestLayout()
            androidx.recyclerview.widget.k r0 = r7.mChildHelper
            H0.a r1 = r0.f9179b
            androidx.recyclerview.widget.c0 r2 = r0.f9178a
            int r3 = r0.f9181d
            r4 = 1
            r5 = 0
            if (r3 != r4) goto L1d
            android.view.View r0 = r0.f9182e
            if (r0 != r8) goto L15
        L13:
            r4 = r5
            goto L46
        L15:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "Cannot call removeViewIfHidden within removeView(At) for a different view"
            r7.<init>(r8)
            throw r7
        L1d:
            r6 = 2
            if (r3 == r6) goto L7e
            r0.f9181d = r6     // Catch: java.lang.Throwable -> L31
            androidx.recyclerview.widget.RecyclerView r3 = r2.f9119a     // Catch: java.lang.Throwable -> L31
            int r3 = r3.indexOfChild(r8)     // Catch: java.lang.Throwable -> L31
            r6 = -1
            if (r3 != r6) goto L33
            r0.m(r8)     // Catch: java.lang.Throwable -> L31
        L2e:
            r0.f9181d = r5
            goto L46
        L31:
            r7 = move-exception
            goto L7b
        L33:
            boolean r6 = r1.d(r3)     // Catch: java.lang.Throwable -> L31
            if (r6 == 0) goto L43
            r1.f(r3)     // Catch: java.lang.Throwable -> L31
            r0.m(r8)     // Catch: java.lang.Throwable -> L31
            r2.c(r3)     // Catch: java.lang.Throwable -> L31
            goto L2e
        L43:
            r0.f9181d = r5
            goto L13
        L46:
            if (r4 == 0) goto L75
            androidx.recyclerview.widget.V0 r0 = getChildViewHolderInt(r8)
            androidx.recyclerview.widget.G0 r1 = r7.mRecycler
            r1.n(r0)
            androidx.recyclerview.widget.G0 r1 = r7.mRecycler
            r1.k(r0)
            boolean r0 = androidx.recyclerview.widget.RecyclerView.sVerboseLoggingEnabled
            if (r0 == 0) goto L75
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "after removing animated view: "
            r0.<init>(r1)
            r0.append(r8)
            java.lang.String r8 = ", "
            r0.append(r8)
            r0.append(r7)
            java.lang.String r8 = r0.toString()
            java.lang.String r0 = "SeslRecyclerView"
            android.util.Log.d(r0, r8)
        L75:
            r8 = r4 ^ 1
            r7.stopInterceptRequestLayout(r8)
            return r4
        L7b:
            r0.f9181d = r5
            throw r7
        L7e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "Cannot call removeViewIfHidden within removeViewIfHidden"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.removeAnimatingView(android.view.View):boolean");
    }

    @Override // android.view.ViewGroup
    public void removeDetachedView(View view, boolean z9) {
        V0 childViewHolderInt = getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            if (childViewHolderInt.isTmpDetached()) {
                childViewHolderInt.clearTmpDetachFlag();
            } else if (!childViewHolderInt.shouldIgnore()) {
                StringBuilder sb = new StringBuilder("Called removeDetachedView with a view which is not flagged as tmp detached.");
                sb.append(childViewHolderInt);
                throw new IllegalArgumentException(A8.l.p(this, sb));
            }
        } else if (sDebugAssertionsEnabled) {
            StringBuilder sb2 = new StringBuilder("No ViewHolder found for child: ");
            sb2.append(view);
            throw new IllegalArgumentException(A8.l.p(this, sb2));
        }
        view.clearAnimation();
        dispatchChildDetached(view);
        super.removeDetachedView(view, z9);
    }

    public void removeItemDecoration(AbstractC0362u0 abstractC0362u0) {
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 != null) {
            abstractC0370y0.assertNotInLayoutOrScroll("Cannot remove item decoration during a scroll  or layout");
        }
        this.mItemDecorations.remove(abstractC0362u0);
        if (this.mItemDecorations.isEmpty()) {
            setWillNotDraw(getOverScrollMode() == 2);
        }
        markItemDecorInsetsDirty();
        requestLayout();
    }

    public void removeItemDecorationAt(int i5) {
        int itemDecorationCount = getItemDecorationCount();
        if (i5 >= 0 && i5 < itemDecorationCount) {
            removeItemDecoration(getItemDecorationAt(i5));
            return;
        }
        throw new IndexOutOfBoundsException(i5 + " is an invalid index for size " + itemDecorationCount);
    }

    public void removeOnChildAttachStateChangeListener(A0 a02) {
        List<A0> list = this.mOnChildAttachStateListeners;
        if (list == null) {
            return;
        }
        list.remove(a02);
    }

    public void removeOnItemTouchListener(C0 c02) {
        this.mOnItemTouchListeners.remove(c02);
        if (this.mInterceptingOnItemTouchListener == c02) {
            this.mInterceptingOnItemTouchListener = null;
        }
    }

    public void removeOnScrollListener(D0 d0) {
        List<D0> list = this.mScrollListeners;
        if (list != null) {
            list.remove(d0);
        }
    }

    public void removeRecyclerListener(H0 h02) {
        this.mRecyclerListeners.remove(h02);
    }

    public void repositionShadowingViews() {
        V0 v02;
        int iE = this.mChildHelper.e();
        for (int i5 = 0; i5 < iE; i5++) {
            View viewD = this.mChildHelper.d(i5);
            V0 childViewHolder = getChildViewHolder(viewD);
            if (childViewHolder != null && (v02 = childViewHolder.mShadowingHolder) != null) {
                View view = v02.itemView;
                int left = viewD.getLeft();
                int top = viewD.getTop();
                if (left != view.getLeft() || top != view.getTop()) {
                    view.layout(left, top, view.getWidth() + left, view.getHeight() + top);
                }
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestChildFocus(View view, View view2) {
        if (!this.mLayout.onRequestChildFocus(this, this.mState, view, view2) && view2 != null) {
            y(view, view2);
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z9) {
        return this.mLayout.requestChildRectangleOnScreen(this, view, rect, z9);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z9) {
        int size = this.mOnItemTouchListeners.size();
        for (int i5 = 0; i5 < size; i5++) {
            this.mOnItemTouchListeners.get(i5).e(z9);
        }
        super.requestDisallowInterceptTouchEvent(z9);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.mInterceptRequestLayoutDepth != 0 || this.mLayoutSuppressed) {
            this.mLayoutWasDefered = true;
        } else {
            super.requestLayout();
        }
    }

    public final void s(int i5, int i7, MotionEvent motionEvent) {
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 == null) {
            Log.e(TAG, "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.mLayoutSuppressed) {
            return;
        }
        int[] iArr = this.mReusableIntPair;
        iArr[0] = 0;
        iArr[1] = 0;
        boolean zCanScrollHorizontally = abstractC0370y0.canScrollHorizontally();
        boolean zCanScrollVertically = this.mLayout.canScrollVertically();
        int i9 = zCanScrollVertically ? (zCanScrollHorizontally ? 1 : 0) | 2 : zCanScrollHorizontally ? 1 : 0;
        float height = motionEvent == null ? getHeight() / 2.0f : motionEvent.getY();
        float width = motionEvent == null ? getWidth() / 2.0f : motionEvent.getX();
        int iW = i5 - w(height, i5);
        int iX = i7 - x(width, i7);
        startNestedScroll(i9, 1);
        if (dispatchNestedPreScroll(zCanScrollHorizontally ? iW : 0, zCanScrollVertically ? iX : 0, this.mReusableIntPair, this.mScrollOffset, 1)) {
            int[] iArr2 = this.mReusableIntPair;
            iW -= iArr2[0];
            iX -= iArr2[1];
        }
        scrollByInternal(zCanScrollHorizontally ? iW : 0, zCanScrollVertically ? iX : 0, motionEvent, 1);
        F f2 = this.mGapWorker;
        if (f2 != null && (iW != 0 || iX != 0)) {
            f2.a(this, iW, iX);
        }
        stopNestedScroll(1);
    }

    public void saveOldPositions() {
        int iH = this.mChildHelper.h();
        for (int i5 = 0; i5 < iH; i5++) {
            V0 childViewHolderInt = getChildViewHolderInt(this.mChildHelper.g(i5));
            if (sDebugAssertionsEnabled && childViewHolderInt.mPosition == -1 && !childViewHolderInt.isRemoved()) {
                throw new IllegalStateException(A8.l.p(this, new StringBuilder("view holder cannot have position -1 unless it is removed")));
            }
            if (!childViewHolderInt.shouldIgnore()) {
                childViewHolderInt.saveOldPosition();
            }
        }
    }

    @Override // android.view.View
    public void scrollBy(int i5, int i7) {
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 == null) {
            Log.e(TAG, "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.mLayoutSuppressed) {
            return;
        }
        boolean zCanScrollHorizontally = abstractC0370y0.canScrollHorizontally();
        boolean zCanScrollVertically = this.mLayout.canScrollVertically();
        if (zCanScrollHorizontally || zCanScrollVertically) {
            if (!zCanScrollHorizontally) {
                i5 = 0;
            }
            if (!zCanScrollVertically) {
                i7 = 0;
            }
            scrollByInternal(i5, i7, null, 0);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x011c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0124  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean scrollByInternal(int r19, int r20, android.view.MotionEvent r21, int r22) {
        /*
            Method dump skipped, instruction units count: 355
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.scrollByInternal(int, int, android.view.MotionEvent, int):boolean");
    }

    public void scrollStep(int i5, int i7, int[] iArr) {
        int iScrollVerticallyBy;
        startInterceptRequestLayout();
        onEnterLayoutOrScroll();
        Trace.beginSection(TRACE_SCROLL_TAG);
        fillRemainingScrollValues(this.mState);
        int iScrollHorizontallyBy = i5 != 0 ? this.mLayout.scrollHorizontallyBy(i5, this.mRecycler, this.mState) : 0;
        if (i7 != 0) {
            iScrollVerticallyBy = this.mLayout.scrollVerticallyBy(i7, this.mRecycler, this.mState);
            if (this.mGoToTopState == 0) {
                setupGoToTop(1);
                d(1);
            }
        } else {
            iScrollVerticallyBy = 0;
        }
        Trace.endSection();
        repositionShadowingViews();
        onExitLayoutOrScroll();
        stopInterceptRequestLayout(false);
        if (iArr != null) {
            iArr[0] = iScrollHorizontallyBy;
            iArr[1] = iScrollVerticallyBy;
        }
    }

    @Override // android.view.View
    public void scrollTo(int i5, int i7) {
        Log.w(TAG, "RecyclerView does not support scrolling to an absolute position. Use scrollToPosition instead");
    }

    public void scrollToPosition(int i5) {
        if (this.mLayoutSuppressed) {
            return;
        }
        stopScroll();
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 == null) {
            Log.e(TAG, "Cannot scroll to position a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        abstractC0370y0.scrollToPosition(i5);
        awakenScrollBars();
        b1 b1Var = this.mFastScroller;
        if (b1Var == null || this.mAdapter == null) {
            return;
        }
        b1Var.m(findFirstVisibleItemPosition(), getChildCount(), this.mAdapter.getItemCount());
    }

    @Override // android.view.View, android.view.accessibility.AccessibilityEventSource
    public void sendAccessibilityEventUnchecked(AccessibilityEvent accessibilityEvent) {
        if (shouldDeferAccessibilityEvent(accessibilityEvent)) {
            return;
        }
        super.sendAccessibilityEventUnchecked(accessibilityEvent);
    }

    public View seslFindNearChildViewUnder(float f2, float f7) {
        int i5 = (int) (f2 + 0.5f);
        int i7 = (int) (0.5f + f7);
        int iE = this.mChildHelper.e() - 1;
        int i9 = 0;
        int i10 = i7;
        int i11 = Integer.MAX_VALUE;
        for (int i12 = iE; i12 >= 0; i12--) {
            View childAt = getChildAt(i12);
            if (childAt != null) {
                int bottom = (childAt.getBottom() + childAt.getTop()) / 2;
                if (i9 != bottom) {
                    int iAbs = Math.abs(i7 - bottom);
                    if (iAbs < i11) {
                        i11 = iAbs;
                        i9 = bottom;
                        i10 = i9;
                    } else {
                        if (!(this.mLayout instanceof StaggeredGridLayoutManager)) {
                            break;
                        }
                        i9 = bottom;
                    }
                } else {
                    continue;
                }
            }
        }
        int i13 = -1;
        int i14 = Integer.MAX_VALUE;
        int i15 = Integer.MAX_VALUE;
        int i16 = -1;
        while (iE >= 0) {
            View childAt2 = getChildAt(iE);
            if (childAt2 != null) {
                int top = childAt2.getTop();
                int bottom2 = childAt2.getBottom();
                int left = childAt2.getLeft();
                int right = childAt2.getRight();
                if (i10 >= top && i10 <= bottom2) {
                    int iAbs2 = Math.abs(i5 - left);
                    int iAbs3 = Math.abs(i5 - right);
                    if (iAbs2 <= i14) {
                        i13 = iE;
                        i14 = iAbs2;
                    }
                    if (iAbs3 <= i15) {
                        i16 = iE;
                        i15 = iAbs3;
                    }
                }
                if (i10 > bottom2 || iE == 0) {
                    return i14 < i15 ? this.mChildHelper.d(i13) : this.mChildHelper.d(i16);
                }
            }
            iE--;
        }
        Log.e(TAG, "findNearChildViewUnder didn't find valid child view! " + f2 + ", " + f7);
        return null;
    }

    public int seslGetGoToTopBottomPadding() {
        return this.mGoToTopBottomPadding;
    }

    public int seslGetHoverBottomPadding() {
        return this.mHoverBottomAreaHeight;
    }

    public int seslGetHoverTopPadding() {
        return this.mHoverTopAreaHeight;
    }

    public final N0 seslGetOnMultiSelectedListener() {
        return null;
    }

    public void seslInitConfigurations(Context context) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        Resources resources = context.getResources();
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
        this.mTouchSlop2 = viewConfiguration.getScaledTouchSlop();
        this.mPagingTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        this.mScaledHorizontalScrollFactor = androidx.core.view.X.a(viewConfiguration);
        this.mScaledVerticalScrollFactor = androidx.core.view.X.b(viewConfiguration);
        this.mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        this.mMaxFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        this.mHoverTopAreaHeight = (int) (TypedValue.applyDimension(1, 25.0f, resources.getDisplayMetrics()) + 0.5f);
        this.mHoverBottomAreaHeight = (int) (TypedValue.applyDimension(1, 25.0f, resources.getDisplayMetrics()) + 0.5f);
        this.mGoToTopBottomPadding = resources.getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_go_to_top_scrollable_view_gap);
        this.mGoToTopImmersiveBottomPadding = 0;
        this.mGoToTopSize = resources.getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_go_to_top_scrollable_view_size);
        this.mGoToTopElevation = resources.getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_go_to_top_elevation);
    }

    public boolean seslIsFastScrollerEnabled() {
        b1 b1Var = this.mFastScroller;
        return b1Var != null && b1Var.j();
    }

    public boolean seslIsIndexTipEnabled() {
        return this.mIndexTipEnabled;
    }

    public boolean seslIsPagingTouchSlopForStylusEnabled() {
        return this.mUsePagingTouchSlopForStylus;
    }

    public void seslSetCtrlkeyPressed(boolean z9) {
        this.mIsCtrlKeyPressed = z9;
    }

    public void seslSetFastScrollerAdditionalPadding(int i5, int i7) {
        b1 b1Var = this.mFastScroller;
        if (b1Var != null) {
            b1Var.o = i5;
            b1Var.f9106n = i7;
            b1Var.d0 = -1;
            b1Var.f9098e0 = -1;
            b1Var.w();
        }
    }

    public void seslSetFastScrollerEnabled(boolean z9) {
        b1 b1Var = this.mFastScroller;
        if (b1Var != null) {
            z = z9 != b1Var.j();
            b1 b1Var2 = this.mFastScroller;
            if (b1Var2.f9073J != z9) {
                b1Var2.f9073J = z9;
                b1Var2.n();
            }
        } else if (z9) {
            b1 b1Var3 = new b1(this);
            this.mFastScroller = b1Var3;
            if (!b1Var3.f9073J) {
                b1Var3.f9073J = true;
                b1Var3.n();
            }
            this.mFastScroller.r(getVerticalScrollbarPosition());
            z = true;
        }
        b1 b1Var4 = this.mFastScroller;
        if (b1Var4 != null && z) {
            b1Var4.w();
        }
        if (this.mLayout instanceof StaggeredGridLayoutManager) {
            Log.w(TAG, "FastScroller cannot be used with StaggeredGridLayoutManager.");
        }
    }

    public void seslSetFastScrollerEventListener(K0 k02) {
    }

    public void seslSetFastScrollerThreshold(float f2) {
        b1 b1Var = this.mFastScroller;
        if (b1Var == null || f2 < 0.0f) {
            return;
        }
        b1Var.getClass();
        Log.d("SeslFastScroller", "FastScroller setThreshold called = " + f2);
        b1Var.f9084U = f2;
    }

    public void seslSetFillBottomColor(int i5) {
        this.mRectPaint.setColor(i5);
        this.mRoundedCorner.c(12, i5);
    }

    public void seslSetFillBottomEnabled(boolean z9) {
        if (this.mLayout instanceof LinearLayoutManager) {
            this.mDrawRect = z9;
            requestLayout();
        }
    }

    public void seslSetFillHorizontalPaddingEnabled(boolean z9) {
        int scrollBarStyle;
        this.mDrawHorizontalPadding = z9;
        int dimensionPixelOffset = z9 ? getResources().getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_system_scroller_vertical_padding) : 0;
        this.mScrollbarTopPadding = dimensionPixelOffset;
        this.mScrollbarBottomPadding = dimensionPixelOffset;
        D();
        b1 b1Var = this.mFastScroller;
        if (b1Var != null && b1Var.f9075L != (scrollBarStyle = getScrollBarStyle())) {
            b1Var.f9075L = scrollBarStyle;
            b1Var.d0 = -1;
            b1Var.f9098e0 = -1;
            b1Var.w();
        }
        requestLayout();
    }

    public void seslSetGoToTopBottomPadding(int i5) {
        this.mGoToTopBottomPadding = i5;
    }

    public void seslSetGoToTopEnabled(boolean z9) {
        n(z9, s6.c.O(this.mContext));
    }

    public void seslSetHoverBottomPadding(int i5) {
        this.mHoverBottomAreaHeight = i5;
    }

    public void seslSetHoverScrollEnabled(boolean z9) {
        this.mHoverScrollEnable = z9;
        this.mHoverScrollStateChanged = true;
    }

    public void seslSetHoverTopPadding(int i5) {
        this.mHoverTopAreaHeight = i5;
    }

    public void seslSetImmersiveScrollBottomPadding(int i5) {
        if (i5 >= 0) {
            if (this.mEnableGoToTop) {
                int height = ((getHeight() - this.mGoToTopSize) - this.mGoToTopBottomPadding) - i5;
                if (height < 0) {
                    this.mGoToTopImmersiveBottomPadding = 0;
                    Log.e(TAG, "The Immersive padding value (" + i5 + ") was too large to draw GoToTop.");
                    return;
                }
                this.mGoToTopImmersiveBottomPadding = i5;
                if (this.mGoToTopState != 0) {
                    int width = (((getWidth() - getPaddingLeft()) - getPaddingRight()) / 2) + getPaddingLeft();
                    Rect rect = this.mGoToTopRect;
                    int i7 = this.mGoToTopSize;
                    rect.set(width - (i7 / 2), height, (i7 / 2) + width, i7 + height);
                    ImageView imageView = this.mGoToTopView;
                    Rect rect2 = this.mGoToTopRect;
                    imageView.layout(rect2.left, rect2.top, rect2.right, rect2.bottom);
                }
            }
            b1 b1Var = this.mFastScroller;
            if (b1Var == null || this.mAdapter == null) {
                return;
            }
            b1Var.f9107p = i5;
            b1Var.y();
        }
    }

    public void seslSetIndexTipEnabled(boolean z9) {
        if (!(this.mAdapter instanceof SectionIndexer)) {
            throw new IllegalStateException("In order to use Index Tip, your Adapter has to implements SectionIndexer. or check if setAdapter is preceded.");
        }
        if (z9) {
            C0353p0 c0353p0 = this.mIndexTip;
            if (c0353p0 == null) {
                this.mIndexTip = new C0353p0(this, this.mContext);
            } else {
                c0353p0.b();
            }
            if (!this.mIndexTipEnabled) {
                getOverlay().add(this.mIndexTip);
            }
            C0353p0.a(this.mIndexTip, getRight(), getBottom(), getPaddingLeft(), getPaddingRight());
        } else if (this.mIndexTipEnabled) {
            getOverlay().remove(this.mIndexTip);
        }
        this.mIndexTipEnabled = z9;
    }

    public void seslSetLastRoundedCorner(boolean z9) {
        this.mDrawLastRoundedCorner = z9;
    }

    public void seslSetLongPressMultiSelectionListener(L0 l02) {
    }

    public void seslSetOnGoToTopClickListener(M0 m02) {
    }

    public void seslSetOnMultiSelectedListener(N0 n02) {
    }

    public void seslSetPagingTouchSlopForStylus(boolean z9) {
        this.mUsePagingTouchSlopForStylus = z9;
    }

    public void seslSetPenSelectionEnabled(boolean z9) {
        this.mIsPenSelectionEnabled = z9;
    }

    public void seslSetPointerIconRotation(float f2) {
        this.mPointerIconRotation = f2;
    }

    public void seslSetRecoilEnabled(boolean z9) {
        if (this.mIsRecoilEnabled != z9) {
            this.mIsRecoilEnabled = z9;
        }
    }

    public void seslSetScrollbarVerticalPadding(int i5, int i7) {
        this.mScrollbarTopPadding = i5;
        this.mScrollbarBottomPadding = i7;
        D();
    }

    public void seslSetSmoothScrollEnabled(boolean z9) {
        U0 u02 = this.mViewFlinger;
        if (u02 != null) {
            OverScroller overScroller = u02.f9027g;
            Method methodT = com.bumptech.glide.c.t(OverScroller.class, "semSetSmoothScrollEnabled", Boolean.TYPE);
            if (methodT != null) {
                com.bumptech.glide.c.C(overScroller, methodT, Boolean.valueOf(z9));
            }
        }
    }

    public void seslShowGoToTopEdge(float f2, float f7, int i5) {
        removeCallbacks(this.mGoToTopEdgeEffectRunnable);
        postDelayed(this.mGoToTopEdgeEffectRunnable, i5);
    }

    public void seslSnapScrollToPosition(int i5) {
        C0333f0 c0333f0 = new C0333f0(this, this.mContext, computeHorizontalScrollRange() * 0.2f);
        c0333f0.setTargetPosition(i5);
        if (getLayoutManager() != null) {
            getLayoutManager().startSmoothScroll(c0333f0);
        }
    }

    public void seslStartLongPressMultiSelection() {
        this.mIsLongPressMultiSelection = true;
    }

    public void seslUpdateIndexTipPosition() {
        C0353p0 c0353p0 = this.mIndexTip;
        if (c0353p0 != null) {
            if (c0353p0.f9216E != 1) {
                c0353p0.f9223g = false;
            } else {
                c0353p0.f9223g = true;
                c0353p0.invalidate();
            }
        }
    }

    public void setAccessibilityDelegateCompat(X0 x02) {
        this.mAccessibilityDelegate = x02;
        androidx.core.view.W.i(this, x02);
    }

    public void setAdapter(AbstractC0341j0 abstractC0341j0) {
        setLayoutFrozen(false);
        A(abstractC0341j0, false, true);
        processDataSetCompletelyChanged(false);
        requestLayout();
    }

    public void setChildDrawingOrderCallback(InterfaceC0347m0 interfaceC0347m0) {
        if (interfaceC0347m0 == this.mChildDrawingOrderCallback) {
            return;
        }
        setChildrenDrawingOrderEnabled(interfaceC0347m0 != null);
    }

    public boolean setChildImportantForAccessibilityInternal(V0 v02, int i5) {
        if (isComputingLayout()) {
            v02.mPendingAccessibilityState = i5;
            this.mPendingAccessibilityImportanceChange.add(v02);
            return false;
        }
        View view = v02.itemView;
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        view.setImportantForAccessibility(i5);
        return true;
    }

    @Override // android.view.ViewGroup
    public void setClipToPadding(boolean z9) {
        if (z9 != this.mClipToPadding) {
            invalidateGlows();
        }
        this.mClipToPadding = z9;
        super.setClipToPadding(z9);
        if (this.mFirstLayoutComplete) {
            requestLayout();
        }
    }

    public void setEdgeEffectEnabled(boolean z9) {
        if (this.mIsEdgeEffectEnabled != z9) {
            this.mIsEdgeEffectEnabled = z9;
        }
    }

    public void setEdgeEffectFactory(AbstractC0349n0 abstractC0349n0) {
        abstractC0349n0.getClass();
        this.mEdgeEffectFactory = abstractC0349n0;
        invalidateGlows();
    }

    public void setHasFixedSize(boolean z9) {
        this.mHasFixedSize = z9;
    }

    public void setItemAnimator(AbstractC0358s0 abstractC0358s0) {
        AbstractC0358s0 abstractC0358s02 = this.mItemAnimator;
        if (abstractC0358s02 != null) {
            abstractC0358s02.e();
            this.mItemAnimator.f9267a = null;
        }
        this.mItemAnimator = abstractC0358s0;
        if (abstractC0358s0 != null) {
            abstractC0358s0.f9267a = this.mItemAnimatorListener;
            abstractC0358s0.f9269c = this;
        }
    }

    public void setItemViewCacheSize(int i5) {
        G0 g02 = this.mRecycler;
        g02.f8895e = i5;
        g02.o();
    }

    @Deprecated
    public void setLayoutFrozen(boolean z9) {
        suppressLayout(z9);
    }

    public void setLayoutManager(AbstractC0370y0 abstractC0370y0) {
        C0327c0 c0327c0;
        if (abstractC0370y0 == this.mLayout) {
            return;
        }
        boolean z9 = abstractC0370y0 instanceof LinearLayoutManager;
        this.mDrawRect = this.mDrawRect && z9;
        this.mDrawLastRoundedCorner = this.mDrawLastRoundedCorner && z9;
        stopScroll();
        if (this.mLayout != null) {
            AbstractC0358s0 abstractC0358s0 = this.mItemAnimator;
            if (abstractC0358s0 != null) {
                abstractC0358s0.e();
            }
            this.mLayout.removeAndRecycleAllViews(this.mRecycler);
            this.mLayout.removeAndRecycleScrapInt(this.mRecycler);
            this.mRecycler.b();
            if (this.mIsAttached) {
                this.mLayout.dispatchDetachedFromWindow(this, this.mRecycler);
            }
            this.mLayout.setRecyclerView(null);
            this.mLayout = null;
        } else {
            this.mRecycler.b();
        }
        C0342k c0342k = this.mChildHelper;
        c0342k.f9179b.g();
        ArrayList arrayList = c0342k.f9180c;
        int size = arrayList.size() - 1;
        while (true) {
            c0327c0 = c0342k.f9178a;
            if (size < 0) {
                break;
            }
            View view = (View) arrayList.get(size);
            c0327c0.getClass();
            V0 childViewHolderInt = getChildViewHolderInt(view);
            if (childViewHolderInt != null) {
                childViewHolderInt.onLeftHiddenState(c0327c0.f9119a);
            }
            arrayList.remove(size);
            size--;
        }
        RecyclerView recyclerView = c0327c0.f9119a;
        int childCount = recyclerView.getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = recyclerView.getChildAt(i5);
            recyclerView.dispatchChildDetached(childAt);
            childAt.clearAnimation();
        }
        recyclerView.removeAllViews();
        this.mLayout = abstractC0370y0;
        if (abstractC0370y0 != null) {
            if (abstractC0370y0.mRecyclerView != null) {
                StringBuilder sb = new StringBuilder("LayoutManager ");
                sb.append(abstractC0370y0);
                sb.append(" is already attached to a RecyclerView:");
                throw new IllegalArgumentException(A8.l.p(abstractC0370y0.mRecyclerView, sb));
            }
            abstractC0370y0.setRecyclerView(this);
            if (this.mIsAttached) {
                this.mLayout.dispatchAttachedToWindow(this);
            }
        }
        this.mRecycler.o();
        requestLayout();
    }

    @Override // android.view.ViewGroup
    @Deprecated
    public void setLayoutTransition(LayoutTransition layoutTransition) {
        if (layoutTransition != null) {
            throw new IllegalArgumentException("Providing a LayoutTransition into RecyclerView is not supported. Please use setItemAnimator() instead for animating changes to the items in this RecyclerView");
        }
        super.setLayoutTransition(null);
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z9) {
        C0223o scrollingChildHelper = getScrollingChildHelper();
        if (scrollingChildHelper.f7247d) {
            WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
            androidx.core.view.M.z(scrollingChildHelper.f7246c);
        }
        scrollingChildHelper.f7247d = z9;
    }

    public void setOnFlingListener(B0 b02) {
        this.mOnFlingListener = b02;
    }

    @Deprecated
    public void setOnScrollListener(D0 d0) {
        this.mScrollListener = d0;
    }

    public void setPreserveFocusAfterLayout(boolean z9) {
        this.mPreserveFocusAfterLayout = z9;
    }

    public void setRecycledViewPool(F0 f02) {
        G0 g02 = this.mRecycler;
        RecyclerView recyclerView = g02.h;
        g02.g(recyclerView.mAdapter, false);
        if (g02.f8897g != null) {
            r1.f8889b--;
        }
        g02.f8897g = f02;
        if (f02 != null && recyclerView.getAdapter() != null) {
            g02.f8897g.f8889b++;
        }
        g02.f();
    }

    @Deprecated
    public void setRecyclerListener(H0 h02) {
        this.mRecyclerListener = h02;
    }

    @Override // android.view.View
    public void setScrollBarStyle(int i5) {
        super.setScrollBarStyle(i5);
        b1 b1Var = this.mFastScroller;
        if (b1Var == null || b1Var.f9075L == i5) {
            return;
        }
        b1Var.f9075L = i5;
        b1Var.d0 = -1;
        b1Var.f9098e0 = -1;
        b1Var.w();
    }

    public void setScrollState(int i5) {
        C0353p0 c0353p0;
        if (i5 == this.mScrollState) {
            return;
        }
        StringBuilder sbE = AbstractC0152g1.e(i5, "setting scroll state to ", " from ");
        sbE.append(this.mScrollState);
        Log.d(TAG, sbE.toString());
        if (sVerboseLoggingEnabled) {
            StringBuilder sbE2 = AbstractC0152g1.e(i5, "setting scroll state to ", " from ");
            sbE2.append(this.mScrollState);
            Log.d(TAG, sbE2.toString(), new Exception());
        }
        this.mScrollState = i5;
        if (i5 != 2) {
            U0 u02 = this.mViewFlinger;
            RecyclerView recyclerView = u02.f9030k;
            recyclerView.removeCallbacks(u02);
            u02.f9027g.abortAnimation();
            com.bumptech.glide.d.E(recyclerView, 0.0f);
            AbstractC0370y0 abstractC0370y0 = this.mLayout;
            if (abstractC0370y0 != null) {
                abstractC0370y0.stopSmoothScroller();
            }
        }
        dispatchOnScrollStateChanged(i5);
        if (i5 == 1) {
            this.mEdgeEffectByDragging = false;
        }
        if (i5 == 0 && this.mIndexTipEnabled && (c0353p0 = this.mIndexTip) != null) {
            c0353p0.b();
        }
    }

    public void setScrollingTouchSlop(int i5) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        Log.d(TAG, "setScrollingTouchSlop(): slopConstant[" + i5 + "]");
        seslSetPagingTouchSlopForStylus(false);
        if (i5 != 0) {
            if (i5 == 1) {
                this.mTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
                return;
            }
            Log.w(TAG, "setScrollingTouchSlop(): bad argument constant " + i5 + "; using default value");
        }
        this.mTouchSlop = viewConfiguration.getScaledTouchSlop();
    }

    public void setViewCacheExtension(T0 t02) {
        this.mRecycler.getClass();
    }

    public boolean shouldDeferAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (!isComputingLayout()) {
            return false;
        }
        int contentChangeTypes = accessibilityEvent != null ? accessibilityEvent.getContentChangeTypes() : 0;
        this.mEatenAccessibilityChangeFlags |= contentChangeTypes != 0 ? contentChangeTypes : 0;
        return true;
    }

    public void showGoToTop() {
        if (this.mEnableGoToTop && f() && this.mGoToTopState != 2) {
            setupGoToTop(1);
            d(1);
        }
    }

    public boolean showPointerIcon(MotionEvent motionEvent, int i5) {
        com.bumptech.glide.d.D(this, motionEvent.getToolType(0), i5 == 20001 ? null : PointerIcon.getSystemIcon(this.mContext, i5));
        return true;
    }

    public void smoothScrollBy(int i5, int i7) {
        smoothScrollBy(i5, i7, null);
    }

    public void smoothScrollToPosition(int i5) {
        if (this.mLayoutSuppressed) {
            return;
        }
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 == null) {
            Log.e(TAG, "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
        } else {
            abstractC0370y0.smoothScrollToPosition(this, this.mState, i5);
        }
    }

    public void smoothScrollToPositionJumpIfNeeded(int i5) {
        int i7;
        int iFindFirstVisibleItemPosition = findFirstVisibleItemPosition();
        boolean z9 = iFindFirstVisibleItemPosition > i5;
        int iFindLastVisibleItemPosition = z9 ? iFindFirstVisibleItemPosition : findLastVisibleItemPosition();
        int iAbs = Math.abs(((z9 ? 1 : -1) * i5) + (getChildCount() * 2));
        if (computeVerticalScrollOffset() != 0) {
            stopScroll();
        }
        if (Settings.System.getInt(getContext().getContentResolver(), "remove_animations", 0) == 1) {
            scrollToPosition(0);
            return;
        }
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 instanceof StaggeredGridLayoutManager) {
            ((StaggeredGridLayoutManager) abstractC0370y0).B(i5, false);
            return;
        }
        if (iFindFirstVisibleItemPosition > 0 && ((z9 && iAbs > 0 && iAbs < iFindLastVisibleItemPosition) || (!z9 && iAbs > 0 && iAbs > iFindLastVisibleItemPosition))) {
            if (abstractC0370y0 instanceof LinearLayoutManager) {
                if ((abstractC0370y0 instanceof GridLayoutManager) && iAbs < (i7 = ((GridLayoutManager) abstractC0370y0).f8899f)) {
                    iAbs = i7;
                }
                ((LinearLayoutManager) abstractC0370y0).scrollToPositionWithOffset(iAbs, 0);
            } else {
                scrollToPosition(iAbs);
            }
        }
        post(new RunnableC0329d0(this, i5));
    }

    public void startInterceptRequestLayout() {
        int i5 = this.mInterceptRequestLayoutDepth + 1;
        this.mInterceptRequestLayoutDepth = i5;
        if (i5 != 1 || this.mLayoutSuppressed) {
            return;
        }
        this.mLayoutWasDefered = false;
    }

    @Override // android.view.View
    public boolean startNestedScroll(int i5) {
        return getScrollingChildHelper().h(i5, 0);
    }

    public void stopInterceptRequestLayout(boolean z9) {
        if (this.mInterceptRequestLayoutDepth < 1) {
            if (sDebugAssertionsEnabled) {
                throw new IllegalStateException(A8.l.p(this, new StringBuilder("stopInterceptRequestLayout was called more times than startInterceptRequestLayout.")));
            }
            this.mInterceptRequestLayoutDepth = 1;
        }
        if (!z9 && !this.mLayoutSuppressed) {
            this.mLayoutWasDefered = false;
        }
        if (this.mInterceptRequestLayoutDepth == 1) {
            if (z9 && this.mLayoutWasDefered && !this.mLayoutSuppressed && this.mLayout != null && this.mAdapter != null) {
                dispatchLayout();
            }
            if (!this.mLayoutSuppressed) {
                this.mLayoutWasDefered = false;
            }
        }
        this.mInterceptRequestLayoutDepth--;
    }

    @Override // android.view.View
    public void stopNestedScroll() {
        getScrollingChildHelper().i(0);
    }

    public void stopScroll() {
        setScrollState(0);
        U0 u02 = this.mViewFlinger;
        RecyclerView recyclerView = u02.f9030k;
        recyclerView.removeCallbacks(u02);
        u02.f9027g.abortAnimation();
        com.bumptech.glide.d.E(recyclerView, 0.0f);
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 != null) {
            abstractC0370y0.stopSmoothScroller();
        }
    }

    @Override // android.view.ViewGroup
    public final void suppressLayout(boolean z9) {
        if (z9 != this.mLayoutSuppressed) {
            assertNotInLayoutOrScroll("Do not suppressLayout in layout or scroll");
            if (z9) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                onTouchEvent(MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0));
                this.mLayoutSuppressed = true;
                this.mIgnoreMotionEventTillDown = true;
                stopScroll();
                return;
            }
            this.mLayoutSuppressed = false;
            if (this.mLayoutWasDefered && this.mLayout != null && this.mAdapter != null) {
                requestLayout();
            }
            this.mLayoutWasDefered = false;
        }
    }

    public void swapAdapter(AbstractC0341j0 abstractC0341j0, boolean z9) {
        setLayoutFrozen(false);
        A(abstractC0341j0, true, z9);
        processDataSetCompletelyChanged(true);
        requestLayout();
    }

    public final void t(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.mScrollPointerId) {
            int i5 = actionIndex == 0 ? 1 : 0;
            this.mScrollPointerId = motionEvent.getPointerId(i5);
            int x9 = (int) (motionEvent.getX(i5) + 0.5f);
            this.mLastTouchX = x9;
            this.mInitialTouchX = x9;
            int y4 = (int) (motionEvent.getY(i5) + 0.5f);
            this.mLastTouchY = y4;
            this.mInitialTouchY = y4;
        }
    }

    public final void u(int i5) {
        int iFindFirstVisibleItemPosition;
        AbstractC0341j0 abstractC0341j0 = this.mAdapter;
        if (abstractC0341j0 == null) {
            Log.e(TAG, "No adapter attached; skipping pageScroll");
            return;
        }
        int itemCount = abstractC0341j0.getItemCount();
        if (itemCount <= 0) {
            return;
        }
        int i7 = 0;
        if (i5 == 0) {
            iFindFirstVisibleItemPosition = findFirstVisibleItemPosition() - getChildCount();
        } else if (i5 == 1) {
            iFindFirstVisibleItemPosition = findLastVisibleItemPosition() + getChildCount();
        } else if (i5 == 2) {
            iFindFirstVisibleItemPosition = 0;
        } else if (i5 != 3) {
            return;
        } else {
            iFindFirstVisibleItemPosition = itemCount - 1;
        }
        int i9 = itemCount - 1;
        if (iFindFirstVisibleItemPosition > i9) {
            i7 = i9;
        } else if (iFindFirstVisibleItemPosition >= 0) {
            i7 = iFindFirstVisibleItemPosition;
        }
        this.mLayout.mRecyclerView.scrollToPosition(i7);
        this.mLayout.mRecyclerView.post(new RunnableC0335g0(this, 3));
    }

    public final void v() {
        boolean z9;
        boolean z10 = false;
        if (this.mDataSetHasChangedAfterLayout) {
            C0324b c0324b = this.mAdapterHelper;
            c0324b.l(c0324b.f9054b);
            c0324b.l(c0324b.f9055c);
            c0324b.f9058f = 0;
            if (this.mDispatchItemsChangedEvent) {
                this.mLayout.onItemsChanged(this);
            }
        }
        if (this.mItemAnimator == null || !this.mLayout.supportsPredictiveItemAnimations()) {
            this.mAdapterHelper.c();
        } else {
            this.mAdapterHelper.j();
        }
        boolean z11 = this.mItemsAddedOrRemoved || this.mItemsChanged;
        this.mState.f8986j = this.mFirstLayoutComplete && this.mItemAnimator != null && ((z9 = this.mDataSetHasChangedAfterLayout) || z11 || this.mLayout.mRequestedSimpleAnimations) && (!z9 || this.mAdapter.hasStableIds());
        R0 r02 = this.mState;
        if (r02.f8986j && z11 && !this.mDataSetHasChangedAfterLayout && this.mItemAnimator != null && this.mLayout.supportsPredictiveItemAnimations()) {
            z10 = true;
        }
        r02.f8987k = z10;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return this.mGoToTopImage == drawable || super.verifyDrawable(drawable);
    }

    public void viewRangeUpdate(int i5, int i7, Object obj) {
        int i9;
        int i10;
        int iH = this.mChildHelper.h();
        int i11 = i7 + i5;
        for (int i12 = 0; i12 < iH; i12++) {
            View viewG = this.mChildHelper.g(i12);
            V0 childViewHolderInt = getChildViewHolderInt(viewG);
            if (childViewHolderInt != null && !childViewHolderInt.shouldIgnore() && (i10 = childViewHolderInt.mPosition) >= i5 && i10 < i11) {
                childViewHolderInt.addFlags(2);
                childViewHolderInt.addChangePayload(obj);
                ((C0372z0) viewG.getLayoutParams()).mInsetsDirty = true;
            }
        }
        G0 g02 = this.mRecycler;
        ArrayList arrayList = g02.f8893c;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            V0 v02 = (V0) arrayList.get(size);
            if (v02 != null && (i9 = v02.mPosition) >= i5 && i9 < i11) {
                v02.addFlags(2);
                g02.i(size);
            }
        }
    }

    public final int w(float f2, int i5) {
        float height = f2 / getHeight();
        float width = i5 / getWidth();
        EdgeEffect edgeEffect = this.mLeftGlow;
        float f7 = 0.0f;
        if (edgeEffect == null || AbstractC0527a.q(edgeEffect) == 0.0f) {
            EdgeEffect edgeEffect2 = this.mRightGlow;
            if (edgeEffect2 != null && AbstractC0527a.q(edgeEffect2) != 0.0f) {
                if (canScrollHorizontally(1)) {
                    this.mRightGlow.onRelease();
                } else {
                    float fR = AbstractC0527a.R(this.mRightGlow, width, height);
                    if (AbstractC0527a.q(this.mRightGlow) == 0.0f) {
                        this.mRightGlow.onRelease();
                    }
                    f7 = fR;
                }
                invalidate();
            }
        } else {
            if (canScrollHorizontally(-1)) {
                this.mLeftGlow.onRelease();
            } else {
                float f9 = -AbstractC0527a.R(this.mLeftGlow, -width, 1.0f - height);
                if (AbstractC0527a.q(this.mLeftGlow) == 0.0f) {
                    this.mLeftGlow.onRelease();
                }
                f7 = f9;
            }
            invalidate();
        }
        return Math.round(f7 * getWidth());
    }

    public final int x(float f2, int i5) {
        float width = f2 / getWidth();
        float height = i5 / getHeight();
        EdgeEffect edgeEffect = this.mTopGlow;
        float f7 = 0.0f;
        if (edgeEffect == null || AbstractC0527a.q(edgeEffect) == 0.0f) {
            EdgeEffect edgeEffect2 = this.mBottomGlow;
            if (edgeEffect2 != null && AbstractC0527a.q(edgeEffect2) != 0.0f) {
                if (canScrollVertically(1)) {
                    this.mBottomGlow.onRelease();
                } else {
                    float fR = AbstractC0527a.R(this.mBottomGlow, height, 1.0f - width);
                    if (AbstractC0527a.q(this.mBottomGlow) == 0.0f) {
                        this.mBottomGlow.onRelease();
                    }
                    f7 = fR;
                }
                invalidate();
            }
        } else {
            if (canScrollVertically(-1)) {
                this.mTopGlow.onRelease();
            } else {
                float f9 = -AbstractC0527a.R(this.mTopGlow, -height, width);
                if (AbstractC0527a.q(this.mTopGlow) == 0.0f) {
                    this.mTopGlow.onRelease();
                }
                f7 = f9;
            }
            invalidate();
        }
        return Math.round(f7 * getHeight());
    }

    public final void y(View view, View view2) {
        View view3 = view2 != null ? view2 : view;
        this.mTempRect.set(0, 0, view3.getWidth(), view3.getHeight());
        ViewGroup.LayoutParams layoutParams = view3.getLayoutParams();
        if (layoutParams instanceof C0372z0) {
            C0372z0 c0372z0 = (C0372z0) layoutParams;
            if (!c0372z0.mInsetsDirty) {
                Rect rect = c0372z0.mDecorInsets;
                Rect rect2 = this.mTempRect;
                rect2.left -= rect.left;
                rect2.right += rect.right;
                rect2.top -= rect.top;
                rect2.bottom += rect.bottom;
            }
        }
        if (view2 != null) {
            offsetDescendantRectToMyCoords(view2, this.mTempRect);
            offsetRectIntoDescendantCoords(view, this.mTempRect);
        }
        this.mLayout.requestChildRectangleOnScreen(this, view, this.mTempRect, !this.mFirstLayoutComplete, view2 == null);
    }

    public final void z() {
        VelocityTracker velocityTracker = this.mVelocityTracker;
        if (velocityTracker != null) {
            velocityTracker.clear();
        }
        boolean zIsFinished = false;
        stopNestedScroll(0);
        EdgeEffect edgeEffect = this.mLeftGlow;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
            zIsFinished = this.mLeftGlow.isFinished();
        }
        EdgeEffect edgeEffect2 = this.mTopGlow;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
            zIsFinished |= this.mTopGlow.isFinished();
        }
        EdgeEffect edgeEffect3 = this.mRightGlow;
        if (edgeEffect3 != null) {
            edgeEffect3.onRelease();
            zIsFinished |= this.mRightGlow.isFinished();
        }
        EdgeEffect edgeEffect4 = this.mBottomGlow;
        if (edgeEffect4 != null) {
            edgeEffect4.onRelease();
            zIsFinished |= this.mBottomGlow.isFinished();
        }
        if (zIsFinished) {
            WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
            postInvalidateOnAnimation();
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecyclerView(Context context, AttributeSet attributeSet, int i5) throws NoSuchMethodException {
        Object[] objArr;
        Constructor constructor;
        super(context, attributeSet, i5);
        int i7 = 6;
        int i9 = 5;
        int i10 = 4;
        int i11 = 2;
        int i12 = 1;
        this.mObserver = new I0(this);
        this.mRecycler = new G0(this);
        this.mViewInfoStore = new o1();
        this.mUpdateChildViewsRunnable = new RunnableC0335g0(this, 0);
        this.mTempRect = new Rect();
        this.mTempRect2 = new Rect();
        this.mTempRectF = new RectF();
        this.mRecyclerListeners = new ArrayList();
        this.mItemDecorations = new ArrayList<>();
        this.mOnItemTouchListeners = new ArrayList<>();
        this.mInterceptRequestLayoutDepth = 0;
        this.mDataSetHasChangedAfterLayout = false;
        this.mDispatchItemsChangedEvent = false;
        this.mLayoutOrScrollCounter = 0;
        this.mDispatchScrollCounter = 0;
        this.mEdgeEffectFactory = sDefaultEdgeEffectFactory;
        C0357s c0357s = new C0357s();
        c0357s.f9267a = null;
        c0357s.f9268b = new ArrayList();
        c0357s.f9269c = null;
        c0357s.f9120d = true;
        c0357s.f9256e = new ArrayList();
        c0357s.f9257f = new ArrayList();
        c0357s.f9258g = new ArrayList();
        c0357s.h = new ArrayList();
        c0357s.f9259i = new ArrayList();
        c0357s.f9260j = new ArrayList();
        c0357s.f9261k = new ArrayList();
        c0357s.f9262l = new ArrayList();
        c0357s.f9263m = new ArrayList();
        c0357s.f9264n = new ArrayList();
        c0357s.o = new ArrayList();
        c0357s.f9265p = 0;
        c0357s.f9266q = 0;
        this.mItemAnimator = c0357s;
        this.mScrollState = 0;
        this.mScrollPointerId = -1;
        this.mScaledHorizontalScrollFactor = Float.MIN_VALUE;
        this.mScaledVerticalScrollFactor = Float.MIN_VALUE;
        this.mPreserveFocusAfterLayout = true;
        this.mViewFlinger = new U0(this);
        this.mPrefetchRegistry = ALLOW_THREAD_GAP_WORK ? new D() : null;
        R0 r02 = new R0();
        r02.f8978a = -1;
        r02.f8979b = 0;
        r02.f8980c = 0;
        r02.f8981d = 1;
        r02.f8982e = 0;
        r02.f8983f = false;
        r02.f8984g = false;
        r02.h = false;
        r02.f8985i = false;
        r02.f8986j = false;
        r02.f8987k = false;
        this.mState = r02;
        this.mItemsAddedOrRemoved = false;
        this.mItemsChanged = false;
        this.mItemAnimatorListener = new C0327c0(this);
        this.mPostedAnimatorRunner = false;
        this.mMinMaxLayoutPositions = new int[2];
        this.mScrollOffset = new int[2];
        this.mNestedOffsets = new int[2];
        this.mWindowOffsets = new int[2];
        this.mEdgeEffectByDragging = false;
        this.ON_ABSORB_VELOCITY = 10000;
        this.mIndexTipEnabled = false;
        this.mRecyclerViewOffsets = new int[2];
        this.mMotionEventUpPendingFlag = 33554432;
        this.mIsSkipMoveEvent = false;
        this.mFrameLatency = FRAME_LATENCY_LIMIT;
        this.mIsNeedCheckLatency = true;
        this.mLastItemAddRemoveAnim = null;
        this.mIsSetOnlyAddAnim = false;
        this.mIsSetOnlyRemoveAnim = false;
        this.mLastItemAnimTop = -1;
        this.mPreventFirstGlow = false;
        this.mIsEdgeEffectEnabled = true;
        this.mAnimListener = new C0331e0(this, i12);
        this.mReusableIntPair = new int[2];
        this.mTouchSlop2 = 0;
        this.mPagingTouchSlop = 0;
        this.mUsePagingTouchSlopForStylus = false;
        this.mGoToToFadeOutRunnable = new RunnableC0335g0(this, i10);
        this.mGoToToFadeInRunnable = new RunnableC0335g0(this, i9);
        this.mAutoHide = new RunnableC0335g0(this, i7);
        this.mEnableGoToTop = false;
        this.mSizeChange = false;
        this.mGoToToping = false;
        this.mSeslOverlayFeatureHeight = 0;
        this.mGoToTopRect = new Rect();
        this.mGoToTopState = 0;
        this.mGoToTopLastState = 0;
        this.mShowFadeOutGTT = 0;
        this.mIsPenSelectionEnabled = true;
        this.mIsPenPressed = false;
        this.mIsFirstPenMoveEvent = true;
        this.mIsNeedPenSelection = false;
        this.mPenDragSelectedViewPosition = -1;
        this.mIsPenDragBlockEnabled = true;
        this.mPenDragStartX = 0;
        this.mPenDragStartY = 0;
        this.mPenDragEndX = 0;
        this.mPenDragEndY = 0;
        this.mPenDragBlockLeft = 0;
        this.mPenDragBlockTop = 0;
        this.mPenDragBlockRight = 0;
        this.mPenDragBlockBottom = 0;
        this.mPenTrackedChild = null;
        this.mPenTrackedChildPosition = -1;
        this.mPenDistanceFromTrackedChildTop = 0;
        this.mPenDragBlockRect = new Rect();
        this.mInitialTopOffsetOfScreen = 0;
        this.mRemainNestedScrollRange = 0;
        this.mNestedScrollRange = 0;
        this.mHasNestedScrollRange = false;
        this.mIsCtrlKeyPressed = false;
        this.mIsLongPressMultiSelection = false;
        this.mIsFirstMultiSelectionMove = true;
        this.mIsCtrlMultiSelection = false;
        this.mDrawHorizontalPadding = false;
        this.mDrawRect = false;
        this.mDrawLastRoundedCorner = true;
        this.mDrawReverse = false;
        this.mBlackTop = -1;
        this.mLastBlackTop = -1;
        this.mAnimatedBlackTop = -1;
        this.mRectPaint = new Paint();
        this.mScrollbarTopPadding = 0;
        this.mScrollbarBottomPadding = 0;
        this.mIsPenHovered = false;
        this.mRootViewCheckForDialog = null;
        this.mIsPenSelectPointerSetted = false;
        this.mIsNeedPenSelectIconSet = false;
        this.mOldTextViewHoverState = false;
        this.mNewTextViewHoverState = false;
        this.mHoverScrollSpeed = 0;
        this.mPointerIconRotation = 0.0f;
        int iE = android.support.v4.media.session.f.E();
        Method methodU = com.bumptech.glide.c.u("android.view.PointerIcon", "hidden_SEM_TYPE_STYLUS_SCROLL_RIGHT", new Class[0]);
        Object objC = methodU != null ? com.bumptech.glide.c.C(null, methodU, new Object[0]) : null;
        int iIntValue = objC instanceof Integer ? ((Integer) objC).intValue() : 13;
        int iD = android.support.v4.media.session.f.D();
        Method methodU2 = com.bumptech.glide.c.u("android.view.PointerIcon", "hidden_SEM_TYPE_STYLUS_SCROLL_LEFT", new Class[0]);
        Object objC2 = methodU2 != null ? com.bumptech.glide.c.C(null, methodU2, new Object[0]) : null;
        this.mHoverScrollArrows = new int[]{iE, iIntValue, iD, objC2 instanceof Integer ? ((Integer) objC2).intValue() : 17};
        this.mHoverRecognitionDurationTime = 0L;
        this.mHoverRecognitionCurrentTime = 0L;
        this.mHoverRecognitionStartTime = 0L;
        this.mHoverScrollTimeInterval = 300L;
        this.mPenDragScrollTimeInterval = 500L;
        this.mHoverScrollStartTime = 0L;
        this.mHoverScrollDirection = -1;
        this.mIsHoverOverscrolled = false;
        this.mIsSendHoverScrollState = false;
        this.mHoverScrollStateForListener = 0;
        this.mIsEnabledPaddingInHoverScroll = false;
        this.mHoverAreaEnter = false;
        this.mSelectorRect = new Rect();
        this.mHoverScrollEnable = true;
        this.mHoverScrollStateChanged = false;
        this.mNeedsHoverScroll = false;
        this.mHoverTopAreaHeight = 0;
        this.mHoverBottomAreaHeight = 0;
        this.mListPadding = new Rect();
        this.mChildBound = new Rect();
        this.mExtraPaddingInTopHoverArea = 0;
        this.mExtraPaddingInBottomHoverArea = 0;
        this.mIsCloseChildSetted = false;
        this.mOldHoverScrollDirection = -1;
        this.mCloseChildByTop = null;
        this.mCloseChildPositionByTop = -1;
        this.mDistanceFromCloseChildTop = 0;
        this.mCloseChildByBottom = null;
        this.mCloseChildPositionByBottom = -1;
        this.mDistanceFromCloseChildBottom = 0;
        C0360t0 c0360t0 = new C0360t0();
        c0360t0.f9273a = null;
        this.mItemBackgroundHolder = c0360t0;
        this.mHoverHandler = new HandlerC0337h0(this, Looper.getMainLooper());
        this.mPendingAccessibilityImportanceChange = new ArrayList();
        this.mItemAnimatorRunner = new RunnableC0335g0(this, i12);
        this.mLastAutoMeasureNonExactMeasuredWidth = 0;
        this.mLastAutoMeasureNonExactMeasuredHeight = 0;
        this.mViewInfoProcessCallback = new C0327c0(this);
        this.mGoToTopEdgeEffectRunnable = new RunnableC0335g0(this, i11);
        this.mIsRecoilEnabled = true;
        setScrollContainer(true);
        setFocusableInTouchMode(true);
        this.mContext = context;
        seslInitConfigurations(context);
        this.mIsRecoilSupported = true;
        this.mItemAnimatorHolder = new f.d(this.mContext);
        this.mPhysicalCoef = context.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
        setWillNotDraw(getOverScrollMode() == 2);
        this.mItemAnimator.f9267a = this.mItemAnimatorListener;
        initAdapterManager();
        this.mChildHelper = new C0342k(new C0327c0(this));
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        if (androidx.core.view.P.c(this) == 0) {
            androidx.core.view.P.m(this, 8);
        }
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        this.mAccessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        setAccessibilityDelegateCompat(new X0(this));
        int[] iArr = G0.a.f1023b;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, i5, 0);
        androidx.core.view.T.d(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, i5, 0);
        String string = typedArrayObtainStyledAttributes.getString(8);
        if (typedArrayObtainStyledAttributes.getInt(2, -1) == -1) {
            setDescendantFocusability(262144);
        }
        this.mClipToPadding = typedArrayObtainStyledAttributes.getBoolean(1, true);
        boolean z9 = typedArrayObtainStyledAttributes.getBoolean(3, false);
        this.mEnableFastScroller = z9;
        if (z9) {
            initFastScroller((StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(6), typedArrayObtainStyledAttributes.getDrawable(7), (StateListDrawable) typedArrayObtainStyledAttributes.getDrawable(4), typedArrayObtainStyledAttributes.getDrawable(5));
        }
        typedArrayObtainStyledAttributes.recycle();
        if (string != null) {
            String strTrim = string.trim();
            if (!strTrim.isEmpty()) {
                if (strTrim.charAt(0) == '.') {
                    strTrim = context.getPackageName() + strTrim;
                } else if (!strTrim.contains(".")) {
                    strTrim = RecyclerView.class.getPackage().getName() + '.' + strTrim;
                }
                try {
                    Class<? extends U> clsAsSubclass = Class.forName(strTrim, false, isInEditMode() ? getClass().getClassLoader() : context.getClassLoader()).asSubclass(AbstractC0370y0.class);
                    try {
                        constructor = clsAsSubclass.getConstructor(LAYOUT_MANAGER_CONSTRUCTOR_SIGNATURE);
                        objArr = new Object[]{context, attributeSet, Integer.valueOf(i5), 0};
                    } catch (NoSuchMethodException e3) {
                        try {
                            objArr = null;
                            constructor = clsAsSubclass.getConstructor(null);
                        } catch (NoSuchMethodException e10) {
                            e10.initCause(e3);
                            throw new IllegalStateException(attributeSet.getPositionDescription() + ": Error creating LayoutManager " + strTrim, e10);
                        }
                    }
                    constructor.setAccessible(true);
                    setLayoutManager((AbstractC0370y0) constructor.newInstance(objArr));
                } catch (ClassCastException e11) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Class is not a LayoutManager " + strTrim, e11);
                } catch (ClassNotFoundException e12) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Unable to find LayoutManager " + strTrim, e12);
                } catch (IllegalAccessException e13) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Cannot access non-public constructor " + strTrim, e13);
                } catch (InstantiationException e14) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + strTrim, e14);
                } catch (InvocationTargetException e15) {
                    throw new IllegalStateException(attributeSet.getPositionDescription() + ": Could not instantiate the LayoutManager: " + strTrim, e15);
                }
            }
        }
        int[] iArr2 = NESTED_SCROLLING_ATTRS;
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr2, i5, 0);
        androidx.core.view.T.d(this, context, iArr2, attributeSet, typedArrayObtainStyledAttributes2, i5, 0);
        boolean z10 = typedArrayObtainStyledAttributes2.getBoolean(0, true);
        typedArrayObtainStyledAttributes2.recycle();
        Resources resources = context.getResources();
        TypedValue typedValue = new TypedValue();
        this.mPenDragBlockImage = resources.getDrawable(com.samsung.android.keyscafe.R.drawable.sesl_pen_block_selection);
        context.getTheme().resolveAttribute(com.samsung.android.keyscafe.R.attr.roundedCornerColor, typedValue, true);
        int i13 = typedValue.resourceId;
        if (i13 > 0) {
            this.mRectColor = resources.getColor(i13);
        }
        this.mRectPaint.setColor(this.mRectColor);
        this.mRectPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mItemAnimator.f9269c = this;
        C0634c c0634c = new C0634c(getContext());
        this.mRoundedCorner = c0634c;
        c0634c.d(12);
        setNestedScrollingEnabled(z10);
        setTag(com.samsung.android.keyscafe.R.id.is_pooling_container_tag, Boolean.TRUE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.recyclerview.widget.V0 findViewHolderForPosition(int r6, boolean r7) {
        /*
            r5 = this;
            androidx.recyclerview.widget.k r0 = r5.mChildHelper
            int r0 = r0.h()
            r1 = 0
            r2 = 0
        L8:
            if (r2 >= r0) goto L3a
            androidx.recyclerview.widget.k r3 = r5.mChildHelper
            android.view.View r3 = r3.g(r2)
            androidx.recyclerview.widget.V0 r3 = getChildViewHolderInt(r3)
            if (r3 == 0) goto L37
            boolean r4 = r3.isRemoved()
            if (r4 != 0) goto L37
            if (r7 == 0) goto L23
            int r4 = r3.mPosition
            if (r4 == r6) goto L2a
            goto L37
        L23:
            int r4 = r3.getLayoutPosition()
            if (r4 == r6) goto L2a
            goto L37
        L2a:
            androidx.recyclerview.widget.k r1 = r5.mChildHelper
            android.view.View r4 = r3.itemView
            boolean r1 = r1.k(r4)
            if (r1 == 0) goto L36
            r1 = r3
            goto L37
        L36:
            return r3
        L37:
            int r2 = r2 + 1
            goto L8
        L3a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.findViewHolderForPosition(int, boolean):androidx.recyclerview.widget.V0");
    }

    public void onExitLayoutOrScroll(boolean z9) {
        int i5 = this.mLayoutOrScrollCounter - 1;
        this.mLayoutOrScrollCounter = i5;
        if (i5 < 1) {
            if (sDebugAssertionsEnabled && i5 < 0) {
                throw new IllegalStateException(A8.l.p(this, new StringBuilder("layout or scroll counter cannot go below zero.Some calls are not matching")));
            }
            this.mLayoutOrScrollCounter = 0;
            if (z9) {
                int i7 = this.mEatenAccessibilityChangeFlags;
                this.mEatenAccessibilityChangeFlags = 0;
                if (i7 != 0 && isAccessibilityEnabled()) {
                    AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
                    accessibilityEventObtain.setEventType(2048);
                    accessibilityEventObtain.setContentChangeTypes(i7);
                    sendAccessibilityEventUnchecked(accessibilityEventObtain);
                }
                dispatchPendingImportantForAccessibilityChanges();
            }
        }
    }

    public void seslSetGoToTopEnabled(boolean z9, boolean z10) {
        n(z9, z10);
    }

    public void smoothScrollBy(int i5, int i7, Interpolator interpolator) {
        smoothScrollBy(i5, i7, interpolator, Integer.MIN_VALUE);
    }

    public boolean dispatchNestedPreScroll(int i5, int i7, int[] iArr, int[] iArr2, int i9) {
        return getScrollingChildHelper().c(i5, i7, iArr, iArr2, i9);
    }

    public boolean dispatchNestedScroll(int i5, int i7, int i9, int i10, int[] iArr, int i11) {
        return getScrollingChildHelper().e(i5, i7, i9, i10, iArr, i11, null);
    }

    public boolean hasNestedScrollingParent(int i5) {
        return getScrollingChildHelper().g(i5);
    }

    public void smoothScrollBy(int i5, int i7, Interpolator interpolator, int i9) {
        smoothScrollBy(i5, i7, interpolator, i9, false);
    }

    public boolean startNestedScroll(int i5, int i7) {
        return getScrollingChildHelper().h(i5, i7);
    }

    @Override // androidx.core.view.InterfaceC0222n
    public void stopNestedScroll(int i5) {
        getScrollingChildHelper().i(i5);
    }

    public void smoothScrollBy(int i5, int i7, Interpolator interpolator, int i9, boolean z9) {
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 == null) {
            Log.e(TAG, "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        if (this.mLayoutSuppressed) {
            return;
        }
        if (!abstractC0370y0.canScrollHorizontally()) {
            i5 = 0;
        }
        if (!this.mLayout.canScrollVertically()) {
            i7 = 0;
        }
        if (i5 == 0 && i7 == 0) {
            return;
        }
        if (i9 != Integer.MIN_VALUE && i9 <= 0) {
            scrollBy(i5, i7);
            return;
        }
        if (z9) {
            int i10 = i5 != 0 ? 1 : 0;
            if (i7 != 0) {
                i10 |= 2;
            }
            startNestedScroll(i10, 1);
        }
        this.mViewFlinger.c(i5, i7, interpolator, i9);
        showGoToTop();
    }

    public final void dispatchNestedScroll(int i5, int i7, int i9, int i10, int[] iArr, int i11, int[] iArr2) {
        getScrollingChildHelper().e(i5, i7, i9, i10, iArr, i11, iArr2);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x007d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View findClickableChildUnder(android.view.View r9, float r10, float r11) {
        /*
            r8 = this;
            boolean r0 = r9.isClickable()
            r1 = 0
            if (r0 == 0) goto L7d
            android.graphics.Rect r0 = new android.graphics.Rect
            r0.<init>()
            android.graphics.Rect r2 = new android.graphics.Rect
            r2.<init>()
            r9.getGlobalVisibleRect(r0)
            r8.getGlobalVisibleRect(r2)
            androidx.core.view.o r3 = r8.mScrollingChildHelper
            if (r3 == 0) goto L6b
            android.view.ViewParent r3 = r3.f(r1)
            boolean r3 = r3 instanceof androidx.core.widget.NestedScrollView
            if (r3 == 0) goto L6b
            androidx.core.view.o r3 = r8.mScrollingChildHelper
            android.view.ViewParent r3 = r3.f(r1)
            androidx.core.widget.NestedScrollView r3 = (androidx.core.widget.NestedScrollView) r3
            int r4 = r3.getScrollY()
            r5 = r8
            r6 = r1
        L31:
            boolean r7 = r5 instanceof androidx.core.widget.NestedScrollView
            if (r7 != 0) goto L49
            int r7 = r5.getTop()
            int r6 = r6 + r7
            android.view.ViewParent r7 = r5.getParent()
            boolean r7 = r7 instanceof android.view.ViewGroup
            if (r7 == 0) goto L49
            android.view.ViewParent r5 = r5.getParent()
            android.view.ViewGroup r5 = (android.view.ViewGroup) r5
            goto L31
        L49:
            if (r4 <= r6) goto L6b
            int r3 = r3.getScrollY()
            r4 = r8
            r5 = r1
        L51:
            boolean r6 = r4 instanceof androidx.core.widget.NestedScrollView
            if (r6 != 0) goto L69
            int r6 = r4.getTop()
            int r5 = r5 + r6
            android.view.ViewParent r6 = r4.getParent()
            boolean r6 = r6 instanceof android.view.ViewGroup
            if (r6 == 0) goto L69
            android.view.ViewParent r4 = r4.getParent()
            android.view.ViewGroup r4 = (android.view.ViewGroup) r4
            goto L51
        L69:
            int r3 = r3 - r5
            goto L6c
        L6b:
            r3 = r1
        L6c:
            int r4 = (int) r10
            int r5 = r2.left
            int r4 = r4 + r5
            int r5 = (int) r11
            int r2 = r2.top
            int r5 = r5 + r2
            int r5 = r5 - r3
            boolean r0 = r0.contains(r4, r5)
            if (r0 == 0) goto L7d
            r0 = r9
            goto L7e
        L7d:
            r0 = 0
        L7e:
            boolean r2 = r9 instanceof android.view.ViewGroup
            if (r2 == 0) goto L99
            android.view.ViewGroup r9 = (android.view.ViewGroup) r9
        L84:
            int r2 = r9.getChildCount()
            if (r1 >= r2) goto L99
            android.view.View r2 = r9.getChildAt(r1)
            android.view.View r2 = r8.findClickableChildUnder(r2, r10, r11)
            if (r2 == 0) goto L96
            r0 = r2
            goto L99
        L96:
            int r1 = r1 + 1
            goto L84
        L99:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.recyclerview.widget.RecyclerView.findClickableChildUnder(android.view.View, float, float):android.view.View");
    }

    public void addItemDecoration(AbstractC0362u0 abstractC0362u0) {
        addItemDecoration(abstractC0362u0, -1);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        AbstractC0370y0 abstractC0370y0 = this.mLayout;
        if (abstractC0370y0 != null) {
            return abstractC0370y0.generateLayoutParams(layoutParams);
        }
        throw new IllegalStateException(A8.l.p(this, new StringBuilder("RecyclerView has no LayoutManager")));
    }

    public void seslSetIndexTipEnabled(boolean z9, int i5) {
        seslSetIndexTipEnabled(z9);
        this.mIndexTip.f9224i = i5;
    }
}
