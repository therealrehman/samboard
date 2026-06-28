package androidx.viewpager2.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.PathInterpolator;
import androidx.core.view.W;
import androidx.recyclerview.widget.AbstractC0341j0;
import androidx.recyclerview.widget.AbstractC0358s0;
import androidx.recyclerview.widget.Y;
import androidx.recyclerview.widget.Z;
import b1.AbstractC0406a;
import c1.b;
import c1.c;
import c1.d;
import c1.e;
import c1.f;
import c1.g;
import c1.i;
import c1.j;
import c1.k;
import c1.l;
import c1.m;
import c1.n;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ViewPager2 extends ViewGroup {

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public static final PathInterpolator f9606E = new PathInterpolator(0.22f, 0.25f, 0.0f, 1.0f);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public float f9607A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public boolean f9608B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public int f9609C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public Z f9610D;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Rect f9611e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Rect f9612f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final f f9613g;
    public int h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f9614i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final e f9615j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final i f9616k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f9617l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Parcelable f9618m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final m f9619n;
    public final l o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final d f9620p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final f f9621q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final B8.e f9622r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final b f9623s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public AbstractC0358s0 f9624t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f9625u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public boolean f9626v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f9627w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final t5.f f9628x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public ValueAnimator f9629y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public ValueAnimator f9630z;

    public ViewPager2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f9611e = new Rect();
        this.f9612f = new Rect();
        f fVar = new f();
        this.f9613g = fVar;
        int i5 = 0;
        this.f9614i = false;
        this.f9615j = new e(i5, this);
        this.f9617l = -1;
        this.f9624t = null;
        this.f9625u = false;
        int i7 = 1;
        this.f9626v = true;
        this.f9627w = -1;
        this.f9607A = 1.0f;
        this.f9608B = false;
        this.f9609C = 0;
        this.f9628x = new t5.f(this);
        m mVar = new m(this, context);
        this.f9619n = mVar;
        WeakHashMap weakHashMap = W.f7199a;
        mVar.setId(View.generateViewId());
        this.f9619n.setDescendantFocusability(131072);
        i iVar = new i(this);
        this.f9616k = iVar;
        this.f9619n.setLayoutManager(iVar);
        this.f9619n.setScrollingTouchSlop(1);
        int[] iArr = AbstractC0406a.f9698a;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        saveAttributeDataForStyleable(context, iArr, attributeSet, typedArrayObtainStyledAttributes, 0, 0);
        try {
            setOrientation(typedArrayObtainStyledAttributes.getInt(0, 0));
            typedArrayObtainStyledAttributes.recycle();
            this.f9619n.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
            this.f9619n.addOnChildAttachStateChangeListener(new g());
            d dVar = new d(this);
            this.f9620p = dVar;
            this.f9622r = new B8.e(24, dVar);
            l lVar = new l(this);
            this.o = lVar;
            lVar.attachToRecyclerView(this.f9619n);
            this.f9619n.addOnScrollListener(this.f9620p);
            f fVar2 = new f();
            this.f9621q = fVar2;
            this.f9620p.f9829a = fVar2;
            f fVar3 = new f(this, i5);
            f fVar4 = new f(this, i7);
            ((ArrayList) fVar2.f9844b).add(fVar3);
            ((ArrayList) this.f9621q.f9844b).add(fVar4);
            this.f9628x.n(this.f9619n);
            ((ArrayList) this.f9621q.f9844b).add(fVar);
            b bVar = new b();
            this.f9623s = bVar;
            ((ArrayList) this.f9621q.f9844b).add(bVar);
            m mVar2 = this.f9619n;
            attachViewToParent(mVar2, 0, mVar2.getLayoutParams());
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    public static void a(ViewPager2 viewPager2) {
        View viewFindSnapView;
        l lVar = viewPager2.o;
        if (lVar == null || (viewFindSnapView = lVar.findSnapView(viewPager2.f9616k)) == null) {
            return;
        }
        int iIndexOfChild = viewPager2.f9619n.indexOfChild(viewFindSnapView);
        i iVar = viewPager2.f9616k;
        Z z9 = viewPager2.f9610D;
        if (z9 == null || z9.f9036a != iVar) {
            viewPager2.f9610D = new Y(iVar, 0);
        }
        Z z10 = viewPager2.f9610D;
        viewPager2.f9610D = z10;
        int iE = z10.e(viewFindSnapView);
        View childAt = viewPager2.f9619n.getChildAt(iE < 0 ? iIndexOfChild + 1 : iIndexOfChild - 1);
        int i5 = iE < 0 ? iE * (-1) : iE;
        float width = ((((viewFindSnapView.getWidth() - i5) / viewFindSnapView.getWidth()) * 0.1f) + 0.9f) * viewPager2.f9607A;
        float f2 = i5;
        float width2 = (((f2 / viewFindSnapView.getWidth()) * 0.1f) + 0.9f) * viewPager2.f9607A;
        float f7 = iE > 0 ? -4 : 4;
        float width3 = ((viewFindSnapView.getWidth() - i5) / viewFindSnapView.getWidth()) * f7;
        viewFindSnapView.setScaleX(width);
        viewFindSnapView.setScaleY(width);
        viewFindSnapView.setRotationY((f2 / viewFindSnapView.getWidth()) * f7);
        if (childAt != null) {
            childAt.setScaleX(width2);
            childAt.setScaleY(width2);
            childAt.setRotationY(-width3);
        }
    }

    public final void b() {
        AbstractC0341j0 adapter;
        if (this.f9617l == -1 || (adapter = getAdapter()) == null) {
            return;
        }
        if (this.f9618m != null) {
            this.f9618m = null;
        }
        int iMax = Math.max(0, Math.min(this.f9617l, adapter.getItemCount() - 1));
        this.h = iMax;
        this.f9617l = -1;
        this.f9619n.scrollToPosition(iMax);
        this.f9628x.u();
    }

    public final void c(int i5, boolean z9) {
        if (((d) this.f9622r.f286f).f9840m) {
            throw new IllegalStateException("Cannot change current item when ViewPager2 is fake dragging");
        }
        d(i5, z9);
    }

    @Override // android.view.View
    public final boolean canScrollHorizontally(int i5) {
        return this.f9619n.canScrollHorizontally(i5);
    }

    @Override // android.view.View
    public final boolean canScrollVertically(int i5) {
        return this.f9619n.canScrollVertically(i5);
    }

    public final void d(int i5, boolean z9) {
        j jVar;
        AbstractC0341j0 adapter = getAdapter();
        if (adapter == null) {
            if (this.f9617l != -1) {
                this.f9617l = Math.max(i5, 0);
                return;
            }
            return;
        }
        if (adapter.getItemCount() <= 0) {
            return;
        }
        int iMin = Math.min(Math.max(i5, 0), adapter.getItemCount() - 1);
        int i7 = this.h;
        if (iMin == i7 && this.f9620p.f9834f == 0) {
            return;
        }
        if (iMin == i7 && z9) {
            return;
        }
        double d8 = i7;
        this.h = iMin;
        this.f9628x.u();
        d dVar = this.f9620p;
        if (dVar.f9834f != 0) {
            dVar.c();
            c cVar = dVar.f9835g;
            d8 = ((double) cVar.f9826a) + ((double) cVar.f9827b);
        }
        d dVar2 = this.f9620p;
        dVar2.getClass();
        dVar2.f9833e = z9 ? 2 : 3;
        dVar2.f9840m = false;
        boolean z10 = dVar2.f9836i != iMin;
        dVar2.f9836i = iMin;
        dVar2.a(2);
        if (z10 && (jVar = dVar2.f9829a) != null) {
            jVar.onPageSelected(iMin);
        }
        if (!z9) {
            this.f9619n.scrollToPosition(iMin);
            return;
        }
        double d10 = iMin;
        if (Math.abs(d10 - d8) <= 3.0d) {
            this.f9619n.smoothScrollToPosition(iMin);
            return;
        }
        this.f9619n.scrollToPosition(d10 > d8 ? iMin - 3 : iMin + 3);
        m mVar = this.f9619n;
        mVar.post(new I.b(iMin, mVar));
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        Parcelable parcelable = (Parcelable) sparseArray.get(getId());
        if (parcelable instanceof n) {
            int i5 = ((n) parcelable).f9850e;
            sparseArray.put(this.f9619n.getId(), (Parcelable) sparseArray.get(i5));
            sparseArray.remove(i5);
        }
        super.dispatchRestoreInstanceState(sparseArray);
        b();
    }

    public final void e() {
        l lVar = this.o;
        if (lVar == null) {
            throw new IllegalStateException("Design assumption violated.");
        }
        View viewFindSnapView = lVar.findSnapView(this.f9616k);
        if (viewFindSnapView == null) {
            return;
        }
        int position = this.f9616k.getPosition(viewFindSnapView);
        if (position != this.h && getScrollState() == 0) {
            this.f9621q.onPageSelected(position);
        }
        this.f9614i = false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        this.f9628x.getClass();
        this.f9628x.getClass();
        return "androidx.viewpager.widget.ViewPager";
    }

    public AbstractC0341j0 getAdapter() {
        return this.f9619n.getAdapter();
    }

    public int getCurrentItem() {
        return this.h;
    }

    public int getItemDecorationCount() {
        return this.f9619n.getItemDecorationCount();
    }

    public int getOffscreenPageLimit() {
        return this.f9627w;
    }

    public int getOrientation() {
        return this.f9616k.getOrientation();
    }

    public int getPageSize() {
        int height;
        int paddingBottom;
        m mVar = this.f9619n;
        if (getOrientation() == 0) {
            height = mVar.getWidth() - mVar.getPaddingLeft();
            paddingBottom = mVar.getPaddingRight();
        } else {
            height = mVar.getHeight() - mVar.getPaddingTop();
            paddingBottom = mVar.getPaddingBottom();
        }
        return height - paddingBottom;
    }

    public int getScrollState() {
        return this.f9620p.f9834f;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        int itemCount;
        int itemCount2;
        int itemCount3;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        ViewPager2 viewPager2 = (ViewPager2) this.f9628x.f14136i;
        if (viewPager2.getAdapter() == null) {
            itemCount = 0;
            itemCount2 = 0;
        } else if (viewPager2.getOrientation() == 1) {
            itemCount = viewPager2.getAdapter().getItemCount();
            itemCount2 = 0;
        } else {
            itemCount2 = viewPager2.getAdapter().getItemCount();
            itemCount = 0;
        }
        accessibilityNodeInfo.setCollectionInfo((AccessibilityNodeInfo.CollectionInfo) L.j.a(itemCount, itemCount2, 0, false).f1791a);
        AbstractC0341j0 adapter = viewPager2.getAdapter();
        if (adapter == null || (itemCount3 = adapter.getItemCount()) == 0 || !viewPager2.f9626v) {
            return;
        }
        if (viewPager2.h > 0) {
            accessibilityNodeInfo.addAction(8192);
        }
        if (viewPager2.h < itemCount3 - 1) {
            accessibilityNodeInfo.addAction(4096);
        }
        accessibilityNodeInfo.setScrollable(true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        int measuredWidth = this.f9619n.getMeasuredWidth();
        int measuredHeight = this.f9619n.getMeasuredHeight();
        int paddingLeft = getPaddingLeft();
        Rect rect = this.f9611e;
        rect.left = paddingLeft;
        rect.right = (i9 - i5) - getPaddingRight();
        rect.top = getPaddingTop();
        rect.bottom = (i10 - i7) - getPaddingBottom();
        Rect rect2 = this.f9612f;
        Gravity.apply(8388659, measuredWidth, measuredHeight, rect, rect2);
        this.f9619n.layout(rect2.left, rect2.top, rect2.right, rect2.bottom);
        if (this.f9614i) {
            e();
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i5, int i7) {
        measureChild(this.f9619n, i5, i7);
        int measuredWidth = this.f9619n.getMeasuredWidth();
        int measuredHeight = this.f9619n.getMeasuredHeight();
        int measuredState = this.f9619n.getMeasuredState();
        int paddingRight = getPaddingRight() + getPaddingLeft() + measuredWidth;
        int paddingBottom = getPaddingBottom() + getPaddingTop() + measuredHeight;
        setMeasuredDimension(View.resolveSizeAndState(Math.max(paddingRight, getSuggestedMinimumWidth()), i5, measuredState), View.resolveSizeAndState(Math.max(paddingBottom, getSuggestedMinimumHeight()), i7, measuredState << 16));
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof n)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        n nVar = (n) parcelable;
        super.onRestoreInstanceState(nVar.getSuperState());
        this.f9617l = nVar.f9851f;
        this.f9618m = nVar.f9852g;
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        n nVar = new n(super.onSaveInstanceState());
        nVar.f9850e = this.f9619n.getId();
        int i5 = this.f9617l;
        if (i5 == -1) {
            i5 = this.h;
        }
        nVar.f9851f = i5;
        Parcelable parcelable = this.f9618m;
        if (parcelable != null) {
            nVar.f9852g = parcelable;
        } else {
            this.f9619n.getAdapter();
        }
        return nVar;
    }

    @Override // android.view.ViewGroup
    public final void onViewAdded(View view) {
        throw new IllegalStateException("ViewPager2 does not support direct child views");
    }

    @Override // android.view.View
    public final boolean performAccessibilityAction(int i5, Bundle bundle) {
        this.f9628x.getClass();
        if (i5 != 8192 && i5 != 4096) {
            return super.performAccessibilityAction(i5, bundle);
        }
        t5.f fVar = this.f9628x;
        fVar.getClass();
        if (i5 != 8192 && i5 != 4096) {
            throw new IllegalStateException();
        }
        ViewPager2 viewPager2 = (ViewPager2) fVar.f14136i;
        int currentItem = i5 == 8192 ? viewPager2.getCurrentItem() - 1 : viewPager2.getCurrentItem() + 1;
        if (viewPager2.f9626v) {
            viewPager2.d(currentItem, true);
        }
        return true;
    }

    public void setAdapter(AbstractC0341j0 abstractC0341j0) {
        AbstractC0341j0 adapter = this.f9619n.getAdapter();
        t5.f fVar = this.f9628x;
        if (adapter != null) {
            adapter.unregisterAdapterDataObserver((e) fVar.h);
        } else {
            fVar.getClass();
        }
        e eVar = this.f9615j;
        if (adapter != null) {
            adapter.unregisterAdapterDataObserver(eVar);
        }
        this.f9619n.setAdapter(abstractC0341j0);
        this.h = 0;
        b();
        t5.f fVar2 = this.f9628x;
        fVar2.u();
        if (abstractC0341j0 != null) {
            abstractC0341j0.registerAdapterDataObserver((e) fVar2.h);
        }
        if (abstractC0341j0 != null) {
            abstractC0341j0.registerAdapterDataObserver(eVar);
        }
    }

    public void setCurrentItem(int i5) {
        c(i5, true);
    }

    @Override // android.view.View
    public void setLayoutDirection(int i5) {
        super.setLayoutDirection(i5);
        this.f9628x.u();
    }

    public void setOffscreenPageLimit(int i5) {
        if (i5 < 1 && i5 != -1) {
            throw new IllegalArgumentException("Offscreen page limit must be OFFSCREEN_PAGE_LIMIT_DEFAULT or a number > 0");
        }
        this.f9627w = i5;
        this.f9619n.requestLayout();
    }

    public void setOrientation(int i5) {
        this.f9616k.setOrientation(i5);
        this.f9628x.u();
    }

    public void setPageTransformer(k kVar) {
        if (kVar != null) {
            if (!this.f9625u) {
                this.f9624t = this.f9619n.getItemAnimator();
                this.f9625u = true;
            }
            this.f9619n.setItemAnimator(null);
        } else if (this.f9625u) {
            this.f9619n.setItemAnimator(this.f9624t);
            this.f9624t = null;
            this.f9625u = false;
        }
        this.f9623s.getClass();
        if (kVar == null) {
            return;
        }
        this.f9623s.getClass();
        this.f9623s.getClass();
    }

    public void setUserInputEnabled(boolean z9) {
        this.f9626v = z9;
        this.f9628x.u();
    }
}
