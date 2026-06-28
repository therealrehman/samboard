package androidx.coordinatorlayout.widget;

import A8.l;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.InterfaceC0224p;
import androidx.core.view.InterfaceC0225q;
import androidx.core.view.InterfaceC0226s;
import androidx.core.view.K;
import androidx.core.view.M;
import androidx.core.view.T;
import androidx.core.view.W;
import androidx.core.view.r;
import androidx.core.view.w0;
import com.samsung.android.keyscafe.R;
import io.flutter.plugin.platform.PlatformPlugin;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import x.AbstractC1174a;

/* JADX INFO: loaded from: classes.dex */
public class CoordinatorLayout extends ViewGroup implements InterfaceC0224p, InterfaceC0225q {
    static final Class<?>[] CONSTRUCTOR_PARAMS;
    static final int EVENT_NESTED_SCROLL = 1;
    static final int EVENT_PRE_DRAW = 0;
    static final int EVENT_VIEW_REMOVED = 2;
    static final String TAG = "CoordinatorLayout";
    static final Comparator<View> TOP_SORTED_CHILDREN_COMPARATOR;
    private static final int TYPE_ON_INTERCEPT = 0;
    private static final int TYPE_ON_TOUCH = 1;
    static final String WIDGET_PACKAGE_NAME;
    static final ThreadLocal<Map<String, Constructor<d>>> sConstructors;
    private static final K.d sRectPool;
    private InterfaceC0226s mApplyWindowInsetsListener;
    private final int[] mBehaviorConsumed;
    private View mBehaviorTouchView;
    private final j mChildDag;
    private final List<View> mDependencySortedChildren;
    private boolean mDisallowInterceptReset;
    private boolean mDrawStatusBarBackground;
    private boolean mEnableAutoCollapsingKeyEvent;
    private boolean mIsAttachedToWindow;
    private int[] mKeylines;
    private w0 mLastInsets;
    private View mLastNestedScrollingChild;
    private boolean mNeedsPreDrawListener;
    private final r mNestedScrollingParentHelper;
    private View mNestedScrollingTarget;
    private final int[] mNestedScrollingV2ConsumedCompat;
    ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;
    private h mOnPreDrawListener;
    private Paint mScrimPaint;
    private Drawable mStatusBarBackground;
    private final List<View> mTempList1;
    private boolean mToolIsMouse;

    static {
        Package r02 = CoordinatorLayout.class.getPackage();
        WIDGET_PACKAGE_NAME = r02 != null ? r02.getName() : null;
        TOP_SORTED_CHILDREN_COMPARATOR = new G4.d(2);
        CONSTRUCTOR_PARAMS = new Class[]{Context.class, AttributeSet.class};
        sConstructors = new ThreadLocal<>();
        sRectPool = new K.f(12);
    }

    public CoordinatorLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.coordinatorLayoutStyle);
        this.mDependencySortedChildren = new ArrayList();
        this.mChildDag = new j();
        this.mTempList1 = new ArrayList();
        this.mBehaviorConsumed = new int[2];
        this.mNestedScrollingV2ConsumedCompat = new int[2];
        this.mEnableAutoCollapsingKeyEvent = true;
        this.mNestedScrollingParentHelper = new r();
        int[] iArr = AbstractC1174a.f14852a;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, R.attr.coordinatorLayoutStyle, 0);
        WeakHashMap weakHashMap = W.f7199a;
        T.d(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, R.attr.coordinatorLayoutStyle, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        if (resourceId != 0) {
            Resources resources = context.getResources();
            this.mKeylines = resources.getIntArray(resourceId);
            float f2 = resources.getDisplayMetrics().density;
            int length = this.mKeylines.length;
            for (int i5 = 0; i5 < length; i5++) {
                this.mKeylines[i5] = (int) (r1[i5] * f2);
            }
        }
        this.mStatusBarBackground = typedArrayObtainStyledAttributes.getDrawable(1);
        typedArrayObtainStyledAttributes.recycle();
        k();
        super.setOnHierarchyChangeListener(new f(this));
        WeakHashMap weakHashMap2 = W.f7199a;
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
    }

    public static Rect a() {
        Rect rect = (Rect) sRectPool.h();
        return rect == null ? new Rect() : rect;
    }

    public static void c(int i5, Rect rect, Rect rect2, g gVar, int i7, int i9) {
        int i10 = gVar.f7156c;
        if (i10 == 0) {
            i10 = 17;
        }
        int absoluteGravity = Gravity.getAbsoluteGravity(i10, i5);
        int i11 = gVar.f7157d;
        if ((i11 & 7) == 0) {
            i11 |= 8388611;
        }
        if ((i11 & 112) == 0) {
            i11 |= 48;
        }
        int absoluteGravity2 = Gravity.getAbsoluteGravity(i11, i5);
        int i12 = absoluteGravity & 7;
        int i13 = absoluteGravity & 112;
        int i14 = absoluteGravity2 & 7;
        int i15 = absoluteGravity2 & 112;
        int iWidth = i14 != 1 ? i14 != 5 ? rect.left : rect.right : rect.left + (rect.width() / 2);
        int iHeight = i15 != 16 ? i15 != 80 ? rect.top : rect.bottom : rect.top + (rect.height() / 2);
        if (i12 == 1) {
            iWidth -= i7 / 2;
        } else if (i12 != 5) {
            iWidth -= i7;
        }
        if (i13 == 16) {
            iHeight -= i9 / 2;
        } else if (i13 != 80) {
            iHeight -= i9;
        }
        rect2.set(iWidth, iHeight, i7 + iWidth, i9 + iHeight);
    }

    public static void i(int i5, View view) {
        g gVar = (g) view.getLayoutParams();
        int i7 = gVar.f7161i;
        if (i7 != i5) {
            WeakHashMap weakHashMap = W.f7199a;
            view.offsetLeftAndRight(i5 - i7);
            gVar.f7161i = i5;
        }
    }

    public static void j(int i5, View view) {
        g gVar = (g) view.getLayoutParams();
        int i7 = gVar.f7162j;
        if (i7 != i5) {
            WeakHashMap weakHashMap = W.f7199a;
            view.offsetTopAndBottom(i5 - i7);
            gVar.f7162j = i5;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static d parseBehavior(Context context, AttributeSet attributeSet, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (str.startsWith(".")) {
            str = context.getPackageName() + str;
        } else if (str.indexOf(46) < 0) {
            String str2 = WIDGET_PACKAGE_NAME;
            if (!TextUtils.isEmpty(str2)) {
                str = str2 + '.' + str;
            }
        }
        try {
            ThreadLocal<Map<String, Constructor<d>>> threadLocal = sConstructors;
            Map<String, Constructor<d>> map = threadLocal.get();
            if (map == null) {
                map = new HashMap<>();
                threadLocal.set(map);
            }
            Constructor<d> constructor = map.get(str);
            if (constructor == null) {
                constructor = Class.forName(str, false, context.getClassLoader()).getConstructor(CONSTRUCTOR_PARAMS);
                constructor.setAccessible(true);
                map.put(str, constructor);
            }
            return constructor.newInstance(context, attributeSet);
        } catch (Exception e3) {
            throw new RuntimeException(l.r("Could not inflate Behavior subclass ", str), e3);
        }
    }

    public void addPreDrawListener() {
        if (this.mIsAttachedToWindow) {
            if (this.mOnPreDrawListener == null) {
                this.mOnPreDrawListener = new h(this);
            }
            getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mNeedsPreDrawListener = true;
    }

    public final void b(g gVar, Rect rect, int i5, int i7) {
        int width = getWidth();
        int height = getHeight();
        int iMax = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) gVar).leftMargin, Math.min(rect.left, ((width - getPaddingRight()) - i5) - ((ViewGroup.MarginLayoutParams) gVar).rightMargin));
        int iMax2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) gVar).topMargin, Math.min(rect.top, ((height - getPaddingBottom()) - i7) - ((ViewGroup.MarginLayoutParams) gVar).bottomMargin));
        rect.set(iMax, iMax2, i5 + iMax, i7 + iMax2);
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return (layoutParams instanceof g) && super.checkLayoutParams(layoutParams);
    }

    public final int d(int i5) {
        int[] iArr = this.mKeylines;
        if (iArr == null) {
            Log.e(TAG, "No keylines defined for " + this + " - attempted index lookup " + i5);
            return 0;
        }
        if (i5 >= 0 && i5 < iArr.length) {
            return iArr[i5];
        }
        Log.e(TAG, "Keyline index " + i5 + " out of range for " + this);
        return 0;
    }

    public void dispatchDependentViewsChanged(View view) {
        ArrayList arrayList = (ArrayList) this.mChildDag.f7173b.getOrDefault(view, null);
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        for (int i5 = 0; i5 < arrayList.size(); i5++) {
            View view2 = (View) arrayList.get(i5);
            d dVar = ((g) view2.getLayoutParams()).f7154a;
            if (dVar != null) {
                dVar.onDependentViewChanged(this, view2, view);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        int childCount = getChildCount() - 1;
        while (true) {
            if (childCount < 0) {
                break;
            }
            View childAt = getChildAt(childCount);
            d dVar = ((g) childAt.getLayoutParams()).f7154a;
            if (dVar != null) {
                dVar.dispatchGenericMotionEvent(motionEvent);
            }
            if (childAt instanceof a) {
                a aVar = (a) childAt;
                boolean z9 = motionEvent.getToolType(0) == 3;
                if (this.mToolIsMouse != z9) {
                    this.mToolIsMouse = z9;
                    aVar.seslSetIsMouse(z9);
                }
                if (motionEvent.getAction() == 8) {
                    if (this.mLastNestedScrollingChild != null) {
                        if (motionEvent.getAxisValue(9) < 0.0f) {
                            aVar.seslSetExpanded(false);
                        } else if (motionEvent.getAxisValue(9) > 0.0f && !this.mLastNestedScrollingChild.canScrollVertically(-1)) {
                            aVar.seslSetExpanded(true);
                        }
                    } else if (motionEvent.getAxisValue(9) < 0.0f) {
                        aVar.seslSetExpanded(false);
                    } else if (motionEvent.getAxisValue(9) > 0.0f) {
                        aVar.seslSetExpanded(true);
                    }
                }
            } else {
                childCount--;
            }
        }
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.mEnableAutoCollapsingKeyEvent && (keyEvent.getKeyCode() == 61 || keyEvent.getKeyCode() == 19 || keyEvent.getKeyCode() == 20 || keyEvent.getKeyCode() == 21 || keyEvent.getKeyCode() == 22)) {
            int childCount = getChildCount();
            int i5 = 0;
            while (true) {
                if (i5 >= childCount) {
                    break;
                }
                KeyEvent.Callback childAt = getChildAt(i5);
                if (childAt instanceof a) {
                    a aVar = (a) childAt;
                    if (!aVar.seslIsCollapsed()) {
                        aVar.seslSetExpanded(false);
                        break;
                    }
                }
                i5++;
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean doViewsOverlap(View view, View view2) {
        boolean z9 = false;
        if (view.getVisibility() != 0 || view2.getVisibility() != 0) {
            return false;
        }
        Rect rectA = a();
        getChildRect(view, view.getParent() != this, rectA);
        Rect rectA2 = a();
        getChildRect(view2, view2.getParent() != this, rectA2);
        try {
            if (rectA.left <= rectA2.right && rectA.top <= rectA2.bottom && rectA.right >= rectA2.left) {
                if (rectA.bottom >= rectA2.top) {
                    z9 = true;
                }
            }
            return z9;
        } finally {
            rectA.setEmpty();
            K.d dVar = sRectPool;
            dVar.c(rectA);
            rectA2.setEmpty();
            dVar.c(rectA2);
        }
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas canvas, View view, long j5) {
        g gVar = (g) view.getLayoutParams();
        d dVar = gVar.f7154a;
        if (dVar != null) {
            float scrimOpacity = dVar.getScrimOpacity(this, view);
            if (scrimOpacity > 0.0f) {
                if (this.mScrimPaint == null) {
                    this.mScrimPaint = new Paint();
                }
                this.mScrimPaint.setColor(gVar.f7154a.getScrimColor(this, view));
                Paint paint = this.mScrimPaint;
                int iRound = Math.round(scrimOpacity * 255.0f);
                if (iRound < 0) {
                    iRound = 0;
                } else if (iRound > 255) {
                    iRound = 255;
                }
                paint.setAlpha(iRound);
                int iSave = canvas.save();
                if (view.isOpaque()) {
                    canvas.clipRect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom(), Region.Op.DIFFERENCE);
                }
                canvas.drawRect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom(), this.mScrimPaint);
                canvas.restoreToCount(iSave);
            }
        }
        return super.drawChild(canvas, view, j5);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        Drawable drawable = this.mStatusBarBackground;
        if ((drawable == null || !drawable.isStateful()) ? false : drawable.setState(drawableState)) {
            invalidate();
        }
    }

    public final boolean e(d dVar, View view, MotionEvent motionEvent, int i5) {
        if (i5 == 0) {
            return dVar.onInterceptTouchEvent(this, view, motionEvent);
        }
        if (i5 == 1) {
            return dVar.onTouchEvent(this, view, motionEvent);
        }
        throw new IllegalArgumentException();
    }

    public void ensurePreDrawListener() {
        int childCount = getChildCount();
        boolean z9 = false;
        int i5 = 0;
        loop0: while (true) {
            if (i5 >= childCount) {
                break;
            }
            View childAt = getChildAt(i5);
            q.k kVar = this.mChildDag.f7173b;
            int i7 = kVar.f12929g;
            for (int i9 = 0; i9 < i7; i9++) {
                ArrayList arrayList = (ArrayList) kVar.l(i9);
                if (arrayList != null && arrayList.contains(childAt)) {
                    z9 = true;
                    break loop0;
                }
            }
            i5++;
        }
        if (z9 != this.mNeedsPreDrawListener) {
            if (z9) {
                addPreDrawListener();
            } else {
                removePreDrawListener();
            }
        }
    }

    public final boolean f(MotionEvent motionEvent, int i5) {
        boolean z9;
        boolean zBlocksInteractionBelow;
        int actionMasked = motionEvent.getActionMasked();
        List<View> list = this.mTempList1;
        list.clear();
        boolean zIsChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int childCount = getChildCount();
        for (int i7 = childCount - 1; i7 >= 0; i7--) {
            list.add(getChildAt(zIsChildrenDrawingOrderEnabled ? getChildDrawingOrder(childCount, i7) : i7));
        }
        Comparator<View> comparator = TOP_SORTED_CHILDREN_COMPARATOR;
        if (comparator != null) {
            Collections.sort(list, comparator);
        }
        int size = list.size();
        MotionEvent motionEventObtain = null;
        int i9 = 0;
        boolean zE = false;
        boolean z10 = false;
        while (i9 < size) {
            View view = list.get(i9);
            g gVar = (g) view.getLayoutParams();
            d dVar = gVar.f7154a;
            if (!(zE || z10) || actionMasked == 0) {
                if (!z10 && !zE && dVar != null) {
                    zE = e(dVar, view, motionEvent, i5);
                    if (zE) {
                        this.mBehaviorTouchView = view;
                        if (actionMasked != 3 && actionMasked != 1) {
                            for (int i10 = 0; i10 < i9; i10++) {
                                View view2 = list.get(i10);
                                d dVar2 = ((g) view2.getLayoutParams()).f7154a;
                                if (dVar2 != null) {
                                    if (motionEventObtain == null) {
                                        motionEventObtain = MotionEvent.obtain(motionEvent);
                                        motionEventObtain.setAction(3);
                                    }
                                    e(dVar2, view2, motionEventObtain, i5);
                                }
                            }
                        }
                    }
                }
                d dVar3 = gVar.f7154a;
                if (dVar3 == null) {
                    gVar.f7165m = false;
                }
                boolean z11 = gVar.f7165m;
                if (z11) {
                    zBlocksInteractionBelow = true;
                } else {
                    zBlocksInteractionBelow = (dVar3 != null ? dVar3.blocksInteractionBelow(this, view) : false) | z11;
                    gVar.f7165m = zBlocksInteractionBelow;
                }
                z9 = zBlocksInteractionBelow && !z11;
                if (zBlocksInteractionBelow && !z9) {
                    break;
                }
            } else {
                if (dVar != null) {
                    if (motionEventObtain == null) {
                        motionEventObtain = MotionEvent.obtain(motionEvent);
                        motionEventObtain.setAction(3);
                    }
                    e(dVar, view, motionEventObtain, i5);
                }
                z9 = z10;
            }
            i9++;
            z10 = z9;
        }
        list.clear();
        if (motionEventObtain != null) {
            motionEventObtain.recycle();
        }
        return zE;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0103  */
    /* JADX WARN: Type inference incomplete: some casts might be missing */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void g() {
        /*
            Method dump skipped, instruction units count: 418
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.g():void");
    }

    public void getChildRect(View view, boolean z9, Rect rect) {
        if (view.isLayoutRequested() || view.getVisibility() == 8) {
            rect.setEmpty();
        } else if (z9) {
            getDescendantRect(view, rect);
        } else {
            rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        }
    }

    public List<View> getDependencies(View view) {
        q.k kVar = this.mChildDag.f7173b;
        int i5 = kVar.f12929g;
        ArrayList arrayList = null;
        for (int i7 = 0; i7 < i5; i7++) {
            ArrayList arrayList2 = (ArrayList) kVar.l(i7);
            if (arrayList2 != null && arrayList2.contains(view)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(kVar.h(i7));
            }
        }
        return arrayList == null ? Collections.emptyList() : arrayList;
    }

    public final List<View> getDependencySortedChildren() {
        g();
        return Collections.unmodifiableList(this.mDependencySortedChildren);
    }

    public List<View> getDependents(View view) {
        ArrayList arrayList = (ArrayList) this.mChildDag.f7173b.getOrDefault(view, null);
        ArrayList arrayList2 = arrayList != null ? new ArrayList(arrayList) : null;
        return arrayList2 == null ? Collections.emptyList() : arrayList2;
    }

    public void getDescendantRect(View view, Rect rect) {
        ThreadLocal threadLocal = k.f7176a;
        rect.set(0, 0, view.getWidth(), view.getHeight());
        ThreadLocal threadLocal2 = k.f7176a;
        Matrix matrix = (Matrix) threadLocal2.get();
        if (matrix == null) {
            matrix = new Matrix();
            threadLocal2.set(matrix);
        } else {
            matrix.reset();
        }
        k.a(this, view, matrix);
        ThreadLocal threadLocal3 = k.f7177b;
        RectF rectF = (RectF) threadLocal3.get();
        if (rectF == null) {
            rectF = new RectF();
            threadLocal3.set(rectF);
        }
        rectF.set(rect);
        matrix.mapRect(rectF);
        rect.set((int) (rectF.left + 0.5f), (int) (rectF.top + 0.5f), (int) (rectF.right + 0.5f), (int) (rectF.bottom + 0.5f));
    }

    public void getDesiredAnchoredChildRect(View view, int i5, Rect rect, Rect rect2) {
        g gVar = (g) view.getLayoutParams();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        c(i5, rect, rect2, gVar, measuredWidth, measuredHeight);
        b(gVar, rect2, measuredWidth, measuredHeight);
    }

    public void getLastChildRect(View view, Rect rect) {
        rect.set(((g) view.getLayoutParams()).f7168q);
    }

    public final w0 getLastWindowInsets() {
        return this.mLastInsets;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        r rVar = this.mNestedScrollingParentHelper;
        return rVar.f7254b | rVar.f7253a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public g getResolvedLayoutParams(View view) {
        g gVar = (g) view.getLayoutParams();
        if (!gVar.f7155b) {
            if (view instanceof c) {
                d behavior = ((c) view).getBehavior();
                if (behavior == null) {
                    Log.e(TAG, "Attached behavior class is null");
                }
                gVar.b(behavior);
                gVar.f7155b = true;
            } else {
                e eVar = null;
                for (Class<?> superclass = view.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
                    eVar = (e) superclass.getAnnotation(e.class);
                    if (eVar != null) {
                        break;
                    }
                }
                if (eVar != null) {
                    try {
                        gVar.b((d) eVar.value().getDeclaredConstructor(null).newInstance(null));
                    } catch (Exception e3) {
                        Log.e(TAG, "Default behavior class " + eVar.value().getName() + " could not be instantiated. Did you forget a default constructor?", e3);
                    }
                }
                gVar.f7155b = true;
            }
        }
        return gVar;
    }

    public Drawable getStatusBarBackground() {
        return this.mStatusBarBackground;
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        return Math.max(super.getSuggestedMinimumHeight(), getPaddingBottom() + getPaddingTop());
    }

    @Override // android.view.View
    public int getSuggestedMinimumWidth() {
        return Math.max(super.getSuggestedMinimumWidth(), getPaddingRight() + getPaddingLeft());
    }

    public final void h() {
        View view = this.mBehaviorTouchView;
        if (view != null) {
            d dVar = ((g) view.getLayoutParams()).f7154a;
            if (dVar != null) {
                long jUptimeMillis = SystemClock.uptimeMillis();
                MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                dVar.onTouchEvent(this, this.mBehaviorTouchView, motionEventObtain);
                motionEventObtain.recycle();
            }
            this.mBehaviorTouchView = null;
        }
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            ((g) getChildAt(i5).getLayoutParams()).f7165m = false;
        }
        this.mDisallowInterceptReset = false;
    }

    public boolean isPointInChildBounds(View view, int i5, int i7) {
        Rect rectA = a();
        getDescendantRect(view, rectA);
        try {
            return rectA.contains(i5, i7);
        } finally {
            rectA.setEmpty();
            sRectPool.c(rectA);
        }
    }

    public final void k() {
        WeakHashMap weakHashMap = W.f7199a;
        if (!getFitsSystemWindows()) {
            M.u(this, null);
            return;
        }
        if (this.mApplyWindowInsetsListener == null) {
            this.mApplyWindowInsetsListener = new b(this);
        }
        M.u(this, this.mApplyWindowInsetsListener);
        setSystemUiVisibility(PlatformPlugin.DEFAULT_SYSTEM_UI);
    }

    public void offsetChildToAnchor(View view, int i5) {
        d dVar;
        g gVar = (g) view.getLayoutParams();
        if (gVar.f7163k != null) {
            Rect rectA = a();
            Rect rectA2 = a();
            Rect rectA3 = a();
            getDescendantRect(gVar.f7163k, rectA);
            getChildRect(view, false, rectA2);
            int measuredWidth = view.getMeasuredWidth();
            int measuredHeight = view.getMeasuredHeight();
            c(i5, rectA, rectA3, gVar, measuredWidth, measuredHeight);
            boolean z9 = (rectA3.left == rectA2.left && rectA3.top == rectA2.top) ? false : true;
            b(gVar, rectA3, measuredWidth, measuredHeight);
            int i7 = rectA3.left - rectA2.left;
            int i9 = rectA3.top - rectA2.top;
            if (i7 != 0) {
                WeakHashMap weakHashMap = W.f7199a;
                view.offsetLeftAndRight(i7);
            }
            if (i9 != 0) {
                WeakHashMap weakHashMap2 = W.f7199a;
                view.offsetTopAndBottom(i9);
            }
            if (z9 && (dVar = gVar.f7154a) != null) {
                dVar.onDependentViewChanged(this, view, gVar.f7163k);
            }
            rectA.setEmpty();
            K.d dVar2 = sRectPool;
            dVar2.c(rectA);
            rectA2.setEmpty();
            dVar2.c(rectA2);
            rectA3.setEmpty();
            dVar2.c(rectA3);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        h();
        if (this.mNeedsPreDrawListener) {
            if (this.mOnPreDrawListener == null) {
                this.mOnPreDrawListener = new h(this);
            }
            getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
        }
        if (this.mLastInsets == null) {
            WeakHashMap weakHashMap = W.f7199a;
            if (getFitsSystemWindows()) {
                K.c(this);
            }
        }
        this.mIsAttachedToWindow = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01e3  */
    /* JADX WARN: Type inference failed for: r18v0, types: [android.view.View, androidx.coordinatorlayout.widget.CoordinatorLayout] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference failed for: r8v28, types: [int] */
    /* JADX WARN: Type inference failed for: r8v30 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onChildViewsChanged(int r19) {
        /*
            Method dump skipped, instruction units count: 573
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onChildViewsChanged(int):void");
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        h();
        if (this.mNeedsPreDrawListener && this.mOnPreDrawListener != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        View view = this.mNestedScrollingTarget;
        if (view != null) {
            this.mLastNestedScrollingChild = view;
            onStopNestedScroll(view);
        }
        this.mIsAttachedToWindow = false;
    }

    @Override // android.view.View
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.mDrawStatusBarBackground || this.mStatusBarBackground == null) {
            return;
        }
        w0 w0Var = this.mLastInsets;
        int iD = w0Var != null ? w0Var.d() : 0;
        if (iD > 0) {
            this.mStatusBarBackground.setBounds(0, 0, getWidth(), iD);
            this.mStatusBarBackground.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
                KeyEvent.Callback childAt = getChildAt(childCount);
                if (childAt instanceof a) {
                    a aVar = (a) childAt;
                    boolean z9 = motionEvent.getToolType(0) == 3;
                    if (this.mToolIsMouse != z9) {
                        this.mToolIsMouse = z9;
                        aVar.seslSetIsMouse(z9);
                    }
                }
            }
            h();
        }
        boolean zF = f(motionEvent, 0);
        if (actionMasked == 1 || actionMasked == 3) {
            this.mBehaviorTouchView = null;
            h();
        }
        return zF;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        d dVar;
        WeakHashMap weakHashMap = W.f7199a;
        int layoutDirection = getLayoutDirection();
        int size = this.mDependencySortedChildren.size();
        for (int i11 = 0; i11 < size; i11++) {
            View view = this.mDependencySortedChildren.get(i11);
            if (view.getVisibility() != 8 && ((dVar = ((g) view.getLayoutParams()).f7154a) == null || !dVar.onLayoutChild(this, view, layoutDirection))) {
                onLayoutChild(view, layoutDirection);
            }
        }
    }

    public void onLayoutChild(View view, int i5) {
        Rect rectA;
        Rect rectA2;
        K.d dVar;
        g gVar = (g) view.getLayoutParams();
        View view2 = gVar.f7163k;
        if (view2 == null && gVar.f7159f != -1) {
            throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete.");
        }
        if (view2 != null) {
            rectA = a();
            rectA2 = a();
            try {
                getDescendantRect(view2, rectA);
                getDesiredAnchoredChildRect(view, i5, rectA, rectA2);
                view.layout(rectA2.left, rectA2.top, rectA2.right, rectA2.bottom);
                return;
            } finally {
                rectA.setEmpty();
                dVar = sRectPool;
                dVar.c(rectA);
                rectA2.setEmpty();
                dVar.c(rectA2);
            }
        }
        int i7 = gVar.f7158e;
        if (i7 < 0) {
            g gVar2 = (g) view.getLayoutParams();
            rectA = a();
            rectA.set(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) gVar2).leftMargin, getPaddingTop() + ((ViewGroup.MarginLayoutParams) gVar2).topMargin, (getWidth() - getPaddingRight()) - ((ViewGroup.MarginLayoutParams) gVar2).rightMargin, (getHeight() - getPaddingBottom()) - ((ViewGroup.MarginLayoutParams) gVar2).bottomMargin);
            if (this.mLastInsets != null) {
                WeakHashMap weakHashMap = W.f7199a;
                if (getFitsSystemWindows() && !view.getFitsSystemWindows()) {
                    rectA.left = this.mLastInsets.b() + rectA.left;
                    rectA.top = this.mLastInsets.d() + rectA.top;
                    rectA.right -= this.mLastInsets.c();
                    rectA.bottom -= this.mLastInsets.a();
                }
            }
            rectA2 = a();
            int i9 = gVar2.f7156c;
            if ((i9 & 7) == 0) {
                i9 |= 8388611;
            }
            if ((i9 & 112) == 0) {
                i9 |= 48;
            }
            Gravity.apply(i9, view.getMeasuredWidth(), view.getMeasuredHeight(), rectA, rectA2, i5);
            view.layout(rectA2.left, rectA2.top, rectA2.right, rectA2.bottom);
            return;
        }
        g gVar3 = (g) view.getLayoutParams();
        int i10 = gVar3.f7156c;
        if (i10 == 0) {
            i10 = 8388661;
        }
        int absoluteGravity = Gravity.getAbsoluteGravity(i10, i5);
        int i11 = absoluteGravity & 7;
        int i12 = absoluteGravity & 112;
        int width = getWidth();
        int height = getHeight();
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        if (i5 == 1) {
            i7 = width - i7;
        }
        int iD = d(i7) - measuredWidth;
        if (i11 == 1) {
            iD += measuredWidth / 2;
        } else if (i11 == 5) {
            iD += measuredWidth;
        }
        int i13 = i12 != 16 ? i12 != 80 ? 0 : measuredHeight : measuredHeight / 2;
        int iMax = Math.max(getPaddingLeft() + ((ViewGroup.MarginLayoutParams) gVar3).leftMargin, Math.min(iD, ((width - getPaddingRight()) - measuredWidth) - ((ViewGroup.MarginLayoutParams) gVar3).rightMargin));
        int iMax2 = Math.max(getPaddingTop() + ((ViewGroup.MarginLayoutParams) gVar3).topMargin, Math.min(i13, ((height - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) gVar3).bottomMargin));
        view.layout(iMax, iMax2, measuredWidth + iMax, measuredHeight + iMax2);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x011f  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r31, int r32) {
        /*
            Method dump skipped, instruction units count: 396
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.coordinatorlayout.widget.CoordinatorLayout.onMeasure(int, int):void");
    }

    public void onMeasureChild(View view, int i5, int i7, int i9, int i10) {
        measureChildWithMargins(view, i5, i7, i9, i10);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedFling(View view, float f2, float f7, boolean z9) {
        d dVar;
        int childCount = getChildCount();
        boolean zOnNestedFling = false;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                g gVar = (g) childAt.getLayoutParams();
                if (gVar.a(0) && (dVar = gVar.f7154a) != null) {
                    zOnNestedFling |= dVar.onNestedFling(this, childAt, view, f2, f7, z9);
                }
            }
        }
        if (zOnNestedFling) {
            onChildViewsChanged(1);
        }
        return zOnNestedFling;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onNestedPreFling(View view, float f2, float f7) {
        d dVar;
        int childCount = getChildCount();
        boolean zOnNestedPreFling = false;
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getVisibility() != 8) {
                g gVar = (g) childAt.getLayoutParams();
                if (gVar.a(0) && (dVar = gVar.f7154a) != null) {
                    zOnNestedPreFling |= dVar.onNestedPreFling(this, childAt, view, f2, f7);
                }
            }
        }
        return zOnNestedPreFling;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedPreScroll(View view, int i5, int i7, int[] iArr) {
        onNestedPreScroll(view, i5, i7, iArr, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScroll(View view, int i5, int i7, int i9, int i10) {
        onNestedScroll(view, i5, i7, i9, i10, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onNestedScrollAccepted(View view, View view2, int i5) {
        onNestedScrollAccepted(view, view2, i5, 0);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        Parcelable parcelable2;
        if (!(parcelable instanceof i)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        i iVar = (i) parcelable;
        super.onRestoreInstanceState(iVar.getSuperState());
        SparseArray sparseArray = iVar.f7171e;
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            int id = childAt.getId();
            d dVar = getResolvedLayoutParams(childAt).f7154a;
            if (id != -1 && dVar != null && (parcelable2 = (Parcelable) sparseArray.get(id)) != null) {
                dVar.onRestoreInstanceState(this, childAt, parcelable2);
            }
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        Parcelable parcelableOnSaveInstanceState;
        i iVar = new i(super.onSaveInstanceState());
        SparseArray sparseArray = new SparseArray();
        int childCount = getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            int id = childAt.getId();
            d dVar = ((g) childAt.getLayoutParams()).f7154a;
            if (id != -1 && dVar != null && (parcelableOnSaveInstanceState = dVar.onSaveInstanceState(this, childAt)) != null) {
                sparseArray.append(id, parcelableOnSaveInstanceState);
            }
        }
        iVar.f7171e = sparseArray;
        return iVar;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean onStartNestedScroll(View view, View view2, int i5) {
        return onStartNestedScroll(view, view2, i5, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void onStopNestedScroll(View view) {
        onStopNestedScroll(view, 0);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zF;
        int actionMasked = motionEvent.getActionMasked();
        View view = this.mBehaviorTouchView;
        boolean z9 = false;
        if (view != null) {
            d dVar = ((g) view.getLayoutParams()).f7154a;
            zF = dVar != null ? dVar.onTouchEvent(this, this.mBehaviorTouchView, motionEvent) : false;
        } else {
            zF = f(motionEvent, 1);
            if (actionMasked != 0 && zF) {
                z9 = true;
            }
        }
        if (this.mBehaviorTouchView == null || actionMasked == 3) {
            zF |= super.onTouchEvent(motionEvent);
        } else if (z9) {
            MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent);
            motionEventObtain.setAction(3);
            super.onTouchEvent(motionEventObtain);
            motionEventObtain.recycle();
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.mBehaviorTouchView = null;
            h();
        }
        return zF;
    }

    public void recordLastChildRect(View view, Rect rect) {
        ((g) view.getLayoutParams()).f7168q.set(rect);
    }

    public void removePreDrawListener() {
        if (this.mIsAttachedToWindow && this.mOnPreDrawListener != null) {
            getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener);
        }
        this.mNeedsPreDrawListener = false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z9) {
        d dVar = ((g) view.getLayoutParams()).f7154a;
        if (dVar == null || !dVar.onRequestChildRectangleOnScreen(this, view, rect, z9)) {
            return super.requestChildRectangleOnScreen(view, rect, z9);
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public void requestDisallowInterceptTouchEvent(boolean z9) {
        super.requestDisallowInterceptTouchEvent(z9);
        if (!z9 || this.mDisallowInterceptReset) {
            return;
        }
        if (this.mBehaviorTouchView == null) {
            int childCount = getChildCount();
            MotionEvent motionEventObtain = null;
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = getChildAt(i5);
                d dVar = ((g) childAt.getLayoutParams()).f7154a;
                if (dVar != null) {
                    if (motionEventObtain == null) {
                        long jUptimeMillis = SystemClock.uptimeMillis();
                        motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                    }
                    dVar.onInterceptTouchEvent(this, childAt, motionEventObtain);
                }
            }
            if (motionEventObtain != null) {
                motionEventObtain.recycle();
            }
        }
        h();
        this.mDisallowInterceptReset = true;
    }

    public void seslEnableAutoCollapsingKeyEvent(boolean z9) {
        this.mEnableAutoCollapsingKeyEvent = z9;
    }

    public void seslSetNestedScrollingChild(View view) {
        this.mLastNestedScrollingChild = view;
    }

    @Override // android.view.View
    public void setFitsSystemWindows(boolean z9) {
        super.setFitsSystemWindows(z9);
        k();
    }

    @Override // android.view.ViewGroup
    public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener) {
        this.mOnHierarchyChangeListener = onHierarchyChangeListener;
    }

    public void setStatusBarBackground(Drawable drawable) {
        Drawable drawable2 = this.mStatusBarBackground;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback(null);
            }
            Drawable drawableMutate = drawable != null ? drawable.mutate() : null;
            this.mStatusBarBackground = drawableMutate;
            if (drawableMutate != null) {
                if (drawableMutate.isStateful()) {
                    this.mStatusBarBackground.setState(getDrawableState());
                }
                Drawable drawable3 = this.mStatusBarBackground;
                WeakHashMap weakHashMap = W.f7199a;
                E.b.b(drawable3, getLayoutDirection());
                this.mStatusBarBackground.setVisible(getVisibility() == 0, false);
                this.mStatusBarBackground.setCallback(this);
            }
            WeakHashMap weakHashMap2 = W.f7199a;
            postInvalidateOnAnimation();
        }
    }

    public void setStatusBarBackgroundColor(int i5) {
        setStatusBarBackground(new ColorDrawable(i5));
    }

    public void setStatusBarBackgroundResource(int i5) {
        setStatusBarBackground(i5 != 0 ? B.a.b(getContext(), i5) : null);
    }

    @Override // android.view.View
    public void setVisibility(int i5) {
        super.setVisibility(i5);
        boolean z9 = i5 == 0;
        Drawable drawable = this.mStatusBarBackground;
        if (drawable == null || drawable.isVisible() == z9) {
            return;
        }
        this.mStatusBarBackground.setVisible(z9, false);
    }

    public final w0 setWindowInsets(w0 w0Var) {
        d dVar;
        if (!Objects.equals(this.mLastInsets, w0Var)) {
            this.mLastInsets = w0Var;
            boolean z9 = w0Var != null && w0Var.d() > 0;
            this.mDrawStatusBarBackground = z9;
            setWillNotDraw(!z9 && getBackground() == null);
            if (!w0Var.f7266a.j()) {
                int childCount = getChildCount();
                for (int i5 = 0; i5 < childCount; i5++) {
                    View childAt = getChildAt(i5);
                    WeakHashMap weakHashMap = W.f7199a;
                    if (childAt.getFitsSystemWindows() && (dVar = ((g) childAt.getLayoutParams()).f7154a) != null) {
                        w0Var = dVar.onApplyWindowInsets(this, childAt, w0Var);
                        if (w0Var.f7266a.j()) {
                            break;
                        }
                    }
                }
            }
            requestLayout();
        }
        return w0Var;
    }

    @Override // android.view.View
    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.mStatusBarBackground;
    }

    @Override // android.view.ViewGroup
    public g generateDefaultLayoutParams() {
        return new g();
    }

    @Override // androidx.core.view.InterfaceC0224p
    public void onNestedPreScroll(View view, int i5, int i7, int[] iArr, int i9) {
        d dVar;
        int childCount = getChildCount();
        boolean z9 = false;
        int iMax = 0;
        int iMax2 = 0;
        for (int i10 = 0; i10 < childCount; i10++) {
            View childAt = getChildAt(i10);
            if (childAt.getVisibility() != 8) {
                g gVar = (g) childAt.getLayoutParams();
                if (gVar.a(i9) && (dVar = gVar.f7154a) != null) {
                    int[] iArr2 = this.mBehaviorConsumed;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    dVar.onNestedPreScroll(this, childAt, view, i5, i7, iArr2, i9);
                    iMax = i5 > 0 ? Math.max(iMax, this.mBehaviorConsumed[0]) : Math.min(iMax, this.mBehaviorConsumed[0]);
                    iMax2 = i7 > 0 ? Math.max(iMax2, this.mBehaviorConsumed[1]) : Math.min(iMax2, this.mBehaviorConsumed[1]);
                    z9 = true;
                }
            }
        }
        iArr[0] = iMax;
        iArr[1] = iMax2;
        if (z9) {
            onChildViewsChanged(1);
        }
    }

    @Override // androidx.core.view.InterfaceC0224p
    public void onNestedScroll(View view, int i5, int i7, int i9, int i10, int i11) {
        onNestedScroll(view, i5, i7, i9, i10, 0, this.mNestedScrollingV2ConsumedCompat);
    }

    @Override // androidx.core.view.InterfaceC0224p
    public void onNestedScrollAccepted(View view, View view2, int i5, int i7) {
        d dVar;
        r rVar = this.mNestedScrollingParentHelper;
        if (i7 == 1) {
            rVar.f7254b = i5;
        } else {
            rVar.f7253a = i5;
        }
        this.mNestedScrollingTarget = view2;
        this.mLastNestedScrollingChild = view2;
        int childCount = getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            g gVar = (g) childAt.getLayoutParams();
            if (gVar.a(i7) && (dVar = gVar.f7154a) != null) {
                dVar.onNestedScrollAccepted(this, childAt, view, view2, i5, i7);
            }
        }
    }

    @Override // androidx.core.view.InterfaceC0224p
    public boolean onStartNestedScroll(View view, View view2, int i5, int i7) {
        int childCount = getChildCount();
        boolean z9 = false;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                g gVar = (g) childAt.getLayoutParams();
                d dVar = gVar.f7154a;
                if (dVar != null) {
                    boolean zOnStartNestedScroll = dVar.onStartNestedScroll(this, childAt, view, view2, i5, i7);
                    z9 |= zOnStartNestedScroll;
                    if (i7 == 0) {
                        gVar.f7166n = zOnStartNestedScroll;
                    } else if (i7 == 1) {
                        gVar.o = zOnStartNestedScroll;
                    }
                } else if (i7 == 0) {
                    gVar.f7166n = false;
                } else if (i7 == 1) {
                    gVar.o = false;
                }
            }
        }
        return z9;
    }

    @Override // androidx.core.view.InterfaceC0224p
    public void onStopNestedScroll(View view, int i5) {
        r rVar = this.mNestedScrollingParentHelper;
        if (i5 == 1) {
            rVar.f7254b = 0;
        } else {
            rVar.f7253a = 0;
        }
        this.mLastNestedScrollingChild = view;
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            g gVar = (g) childAt.getLayoutParams();
            if (gVar.a(i5)) {
                d dVar = gVar.f7154a;
                if (dVar != null) {
                    dVar.onStopNestedScroll(this, childAt, view, i5);
                }
                if (i5 == 0) {
                    gVar.f7166n = false;
                } else if (i5 == 1) {
                    gVar.o = false;
                }
                gVar.f7167p = false;
            }
        }
        this.mNestedScrollingTarget = null;
    }

    @Override // android.view.ViewGroup
    public g generateLayoutParams(AttributeSet attributeSet) {
        return new g(getContext(), attributeSet);
    }

    @Override // androidx.core.view.InterfaceC0225q
    public void onNestedScroll(View view, int i5, int i7, int i9, int i10, int i11, int[] iArr) {
        d dVar;
        int iMin;
        boolean z9;
        int iMin2;
        int childCount = getChildCount();
        boolean z10 = false;
        int i12 = 0;
        int i13 = 0;
        for (int i14 = 0; i14 < childCount; i14++) {
            View childAt = getChildAt(i14);
            if (childAt.getVisibility() != 8) {
                g gVar = (g) childAt.getLayoutParams();
                if (gVar.a(i11) && (dVar = gVar.f7154a) != null) {
                    int[] iArr2 = this.mBehaviorConsumed;
                    iArr2[0] = 0;
                    iArr2[1] = 0;
                    dVar.onNestedScroll(this, childAt, view, i5, i7, i9, i10, i11, iArr2);
                    if (i9 > 0) {
                        iMin = Math.max(i12, this.mBehaviorConsumed[0]);
                    } else {
                        iMin = Math.min(i12, this.mBehaviorConsumed[0]);
                    }
                    i12 = iMin;
                    if (i10 > 0) {
                        z9 = true;
                        iMin2 = Math.max(i13, this.mBehaviorConsumed[1]);
                    } else {
                        z9 = true;
                        iMin2 = Math.min(i13, this.mBehaviorConsumed[1]);
                    }
                    i13 = iMin2;
                    z10 = z9;
                }
            }
        }
        iArr[0] = iArr[0] + i12;
        iArr[1] = iArr[1] + i13;
        if (z10) {
            onChildViewsChanged(1);
        }
    }

    @Override // android.view.ViewGroup
    public g generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof g) {
            return new g((g) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new g((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new g(layoutParams);
    }
}
