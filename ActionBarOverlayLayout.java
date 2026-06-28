package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.Window;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import androidx.core.view.InterfaceC0224p;
import androidx.core.view.InterfaceC0225q;
import com.samsung.android.keyscafe.R;
import java.util.WeakHashMap;
import l.C0669k;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"UnknownNullness"})
public class ActionBarOverlayLayout extends ViewGroup implements InterfaceC0175o0, InterfaceC0224p, InterfaceC0225q {

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public static final int[] f6353F = {R.attr.actionBarSize, android.R.attr.windowContentOverlay};

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public ViewPropertyAnimator f6354A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public final C0138c f6355B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final RunnableC0141d f6356C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final RunnableC0141d f6357D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final androidx.core.view.r f6358E;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f6359e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f6360f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ContentFrameLayout f6361g;
    public ActionBarContainer h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public InterfaceC0178p0 f6362i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Drawable f6363j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f6364k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f6365l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f6366m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f6367n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f6368p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f6369q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Rect f6370r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Rect f6371s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Rect f6372t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public androidx.core.view.w0 f6373u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public androidx.core.view.w0 f6374v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public androidx.core.view.w0 f6375w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public androidx.core.view.w0 f6376x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public InterfaceC0144e f6377y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public OverScroller f6378z;

    public ActionBarOverlayLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6360f = 0;
        this.f6370r = new Rect();
        this.f6371s = new Rect();
        this.f6372t = new Rect();
        new Rect();
        new Rect();
        new Rect();
        new Rect();
        androidx.core.view.w0 w0Var = androidx.core.view.w0.f7265b;
        this.f6373u = w0Var;
        this.f6374v = w0Var;
        this.f6375w = w0Var;
        this.f6376x = w0Var;
        this.f6355B = new C0138c(0, this);
        this.f6356C = new RunnableC0141d(this, 0);
        this.f6357D = new RunnableC0141d(this, 1);
        c(context);
        this.f6358E = new androidx.core.view.r();
    }

    public static boolean a(FrameLayout frameLayout, Rect rect, boolean z9) {
        boolean z10;
        C0147f c0147f = (C0147f) frameLayout.getLayoutParams();
        int i5 = ((ViewGroup.MarginLayoutParams) c0147f).leftMargin;
        int i7 = rect.left;
        if (i5 != i7) {
            ((ViewGroup.MarginLayoutParams) c0147f).leftMargin = i7;
            z10 = true;
        } else {
            z10 = false;
        }
        int i9 = ((ViewGroup.MarginLayoutParams) c0147f).topMargin;
        int i10 = rect.top;
        if (i9 != i10) {
            ((ViewGroup.MarginLayoutParams) c0147f).topMargin = i10;
            z10 = true;
        }
        int i11 = ((ViewGroup.MarginLayoutParams) c0147f).rightMargin;
        int i12 = rect.right;
        if (i11 != i12) {
            ((ViewGroup.MarginLayoutParams) c0147f).rightMargin = i12;
            z10 = true;
        }
        if (z9) {
            int i13 = ((ViewGroup.MarginLayoutParams) c0147f).bottomMargin;
            int i14 = rect.bottom;
            if (i13 != i14) {
                ((ViewGroup.MarginLayoutParams) c0147f).bottomMargin = i14;
                return true;
            }
        }
        return z10;
    }

    public final void b() {
        removeCallbacks(this.f6356C);
        removeCallbacks(this.f6357D);
        ViewPropertyAnimator viewPropertyAnimator = this.f6354A;
        if (viewPropertyAnimator != null) {
            viewPropertyAnimator.cancel();
        }
    }

    public final void c(Context context) {
        TypedArray typedArrayObtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(f6353F);
        this.f6359e = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
        this.f6363j = drawable;
        setWillNotDraw(drawable == null);
        typedArrayObtainStyledAttributes.recycle();
        this.f6364k = context.getApplicationInfo().targetSdkVersion < 19;
        this.f6378z = new OverScroller(context);
    }

    @Override // android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C0147f;
    }

    public final void d(int i5) {
        e();
        if (i5 == 2) {
            ((c2) this.f6362i).getClass();
            Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
        } else if (i5 == 5) {
            ((c2) this.f6362i).getClass();
            Log.i("ToolbarWidgetWrapper", "Progress display unsupported");
        } else {
            if (i5 != 109) {
                return;
            }
            setOverlayMode(true);
        }
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int translationY;
        super.draw(canvas);
        if (this.f6363j == null || this.f6364k) {
            return;
        }
        if (this.h.getVisibility() == 0) {
            translationY = (int) (this.h.getTranslationY() + this.h.getBottom() + 0.5f);
        } else {
            translationY = 0;
        }
        this.f6363j.setBounds(0, translationY, getWidth(), this.f6363j.getIntrinsicHeight() + translationY);
        this.f6363j.draw(canvas);
    }

    public final void e() {
        InterfaceC0178p0 wrapper;
        if (this.f6361g == null) {
            this.f6361g = (ContentFrameLayout) findViewById(R.id.action_bar_activity_content);
            this.h = (ActionBarContainer) findViewById(R.id.action_bar_container);
            KeyEvent.Callback callbackFindViewById = findViewById(R.id.action_bar);
            if (callbackFindViewById instanceof InterfaceC0178p0) {
                wrapper = (InterfaceC0178p0) callbackFindViewById;
            } else {
                if (!(callbackFindViewById instanceof Toolbar)) {
                    throw new IllegalStateException("Can't make a decor toolbar out of ".concat(callbackFindViewById.getClass().getSimpleName()));
                }
                wrapper = ((Toolbar) callbackFindViewById).getWrapper();
            }
            this.f6362i = wrapper;
        }
    }

    public final void f(androidx.appcompat.view.menu.j jVar, M3.g gVar) {
        e();
        c2 c2Var = (c2) this.f6362i;
        C0171n c0171n = c2Var.f6690m;
        Toolbar toolbar = c2Var.f6679a;
        if (c0171n == null) {
            C0171n c0171n2 = new C0171n(toolbar.getContext());
            c2Var.f6690m = c0171n2;
            c0171n2.setId(R.id.action_menu_presenter);
        }
        c2Var.f6690m.setCallback(gVar);
        toolbar.setMenu(jVar, c2Var.f6690m);
    }

    @Override // android.view.View
    public final boolean fitSystemWindows(Rect rect) {
        return super.fitSystemWindows(rect);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new C0147f(-1, -1);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C0147f(getContext(), attributeSet);
    }

    public int getActionBarHideOffset() {
        ActionBarContainer actionBarContainer = this.h;
        if (actionBarContainer != null) {
            return -((int) actionBarContainer.getTranslationY());
        }
        return 0;
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        androidx.core.view.r rVar = this.f6358E;
        return rVar.f7254b | rVar.f7253a;
    }

    public CharSequence getTitle() {
        e();
        return ((c2) this.f6362i).f6679a.getTitle();
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        e();
        androidx.core.view.w0 w0VarF = androidx.core.view.w0.f(windowInsets, this);
        boolean zA = a(this.h, new Rect(w0VarF.b(), w0VarF.d(), w0VarF.c(), w0VarF.a()), false);
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        Rect rect = this.f6370r;
        androidx.core.view.M.b(this, w0VarF, rect);
        int i5 = rect.left;
        int i7 = rect.top;
        int i9 = rect.right;
        int i10 = rect.bottom;
        androidx.core.view.u0 u0Var = w0VarF.f7266a;
        androidx.core.view.w0 w0VarI = u0Var.i(i5, i7, i9, i10);
        this.f6373u = w0VarI;
        boolean z9 = true;
        if (!this.f6374v.equals(w0VarI)) {
            this.f6374v = this.f6373u;
            zA = true;
        }
        Rect rect2 = this.f6371s;
        if (rect2.equals(rect)) {
            z9 = zA;
        } else {
            rect2.set(rect);
        }
        if (z9) {
            requestLayout();
        }
        return u0Var.a().f7266a.c().f7266a.b().e();
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        c(getContext());
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        androidx.core.view.K.c(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        int childCount = getChildCount();
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (childAt.getVisibility() != 8) {
                C0147f c0147f = (C0147f) childAt.getLayoutParams();
                int measuredWidth = childAt.getMeasuredWidth();
                int measuredHeight = childAt.getMeasuredHeight();
                int i12 = ((ViewGroup.MarginLayoutParams) c0147f).leftMargin + paddingLeft;
                int i13 = ((ViewGroup.MarginLayoutParams) c0147f).topMargin + paddingTop;
                childAt.layout(i12, i13, measuredWidth + i12, measuredHeight + i13);
            }
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i5, int i7) {
        int measuredHeight;
        e();
        measureChildWithMargins(this.h, i5, 0, i7, 0);
        C0147f c0147f = (C0147f) this.h.getLayoutParams();
        int iMax = Math.max(0, this.h.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) c0147f).leftMargin + ((ViewGroup.MarginLayoutParams) c0147f).rightMargin);
        int iMax2 = Math.max(0, this.h.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) c0147f).topMargin + ((ViewGroup.MarginLayoutParams) c0147f).bottomMargin);
        int iCombineMeasuredStates = View.combineMeasuredStates(0, this.h.getMeasuredState());
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        boolean z9 = (getWindowSystemUiVisibility() & 256) != 0;
        if (z9) {
            measuredHeight = this.f6359e;
            if (this.f6366m && this.h.getTabContainer() != null) {
                measuredHeight += this.f6359e;
            }
        } else {
            measuredHeight = this.h.getVisibility() != 8 ? this.h.getMeasuredHeight() : 0;
        }
        Rect rect = this.f6370r;
        Rect rect2 = this.f6372t;
        rect2.set(rect);
        androidx.core.view.w0 w0Var = this.f6373u;
        this.f6375w = w0Var;
        if (this.f6365l || z9) {
            D.f fVarB = D.f.b(w0Var.b(), this.f6375w.d() + measuredHeight, this.f6375w.c(), this.f6375w.a());
            androidx.core.view.n0 n0Var = new androidx.core.view.n0(this.f6375w);
            n0Var.e(fVarB);
            this.f6375w = n0Var.b();
        } else {
            rect2.top += measuredHeight;
            rect2.bottom = rect2.bottom;
            this.f6375w = w0Var.f7266a.i(0, measuredHeight, 0, 0);
        }
        a(this.f6361g, rect2, true);
        if (!this.f6376x.equals(this.f6375w)) {
            androidx.core.view.w0 w0Var2 = this.f6375w;
            this.f6376x = w0Var2;
            androidx.core.view.W.b(this.f6361g, w0Var2);
        }
        measureChildWithMargins(this.f6361g, i5, 0, i7, 0);
        C0147f c0147f2 = (C0147f) this.f6361g.getLayoutParams();
        int iMax3 = Math.max(iMax, this.f6361g.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) c0147f2).leftMargin + ((ViewGroup.MarginLayoutParams) c0147f2).rightMargin);
        int iMax4 = Math.max(iMax2, this.f6361g.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) c0147f2).topMargin + ((ViewGroup.MarginLayoutParams) c0147f2).bottomMargin);
        int iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.f6361g.getMeasuredState());
        setMeasuredDimension(View.resolveSizeAndState(Math.max(getPaddingRight() + getPaddingLeft() + iMax3, getSuggestedMinimumWidth()), i5, iCombineMeasuredStates2), View.resolveSizeAndState(Math.max(getPaddingBottom() + getPaddingTop() + iMax4, getSuggestedMinimumHeight()), i7, iCombineMeasuredStates2 << 16));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f2, float f7, boolean z9) {
        if (!this.f6367n || !z9) {
            return false;
        }
        this.f6378z.fling(0, 0, 0, (int) f7, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (this.f6378z.getFinalY() > this.h.getHeight()) {
            b();
            this.f6357D.run();
        } else {
            b();
            this.f6356C.run();
        }
        this.o = true;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f2, float f7) {
        return false;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i5, int i7, int[] iArr) {
    }

    @Override // androidx.core.view.InterfaceC0225q
    public final void onNestedScroll(View view, int i5, int i7, int i9, int i10, int i11, int[] iArr) {
        onNestedScroll(view, i5, i7, i9, i10, i11);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i5) {
        g.P p4;
        C0669k c0669k;
        this.f6358E.f7253a = i5;
        this.f6368p = getActionBarHideOffset();
        b();
        InterfaceC0144e interfaceC0144e = this.f6377y;
        if (interfaceC0144e == null || (c0669k = (p4 = (g.P) interfaceC0144e).f11054s) == null) {
            return;
        }
        c0669k.a();
        p4.f11054s = null;
    }

    @Override // androidx.core.view.InterfaceC0224p
    public final boolean onStartNestedScroll(View view, View view2, int i5, int i7) {
        return i7 == 0 && onStartNestedScroll(view, view2, i5);
    }

    @Override // androidx.core.view.InterfaceC0224p
    public final void onStopNestedScroll(View view, int i5) {
        if (i5 == 0) {
            onStopNestedScroll(view);
        }
    }

    @Override // android.view.View
    public final void onWindowSystemUiVisibilityChanged(int i5) {
        super.onWindowSystemUiVisibilityChanged(i5);
        e();
        int i7 = this.f6369q ^ i5;
        this.f6369q = i5;
        boolean z9 = (i5 & 4) == 0;
        boolean z10 = (i5 & 256) != 0;
        InterfaceC0144e interfaceC0144e = this.f6377y;
        if (interfaceC0144e != null) {
            ((g.P) interfaceC0144e).o = !z10;
            if (z9 || !z10) {
                g.P p4 = (g.P) interfaceC0144e;
                if (p4.f11051p) {
                    p4.f11051p = false;
                    p4.u(true);
                }
            } else {
                g.P p9 = (g.P) interfaceC0144e;
                if (!p9.f11051p) {
                    p9.f11051p = true;
                    p9.u(true);
                }
            }
        }
        if ((i7 & 256) == 0 || this.f6377y == null) {
            return;
        }
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        androidx.core.view.K.c(this);
    }

    @Override // android.view.View
    public final void onWindowVisibilityChanged(int i5) {
        super.onWindowVisibilityChanged(i5);
        this.f6360f = i5;
        InterfaceC0144e interfaceC0144e = this.f6377y;
        if (interfaceC0144e != null) {
            ((g.P) interfaceC0144e).f11050n = i5;
        }
    }

    public void setActionBarHideOffset(int i5) {
        b();
        this.h.setTranslationY(-Math.max(0, Math.min(i5, this.h.getHeight())));
    }

    public void setActionBarVisibilityCallback(InterfaceC0144e interfaceC0144e) {
        this.f6377y = interfaceC0144e;
        if (getWindowToken() != null) {
            ((g.P) this.f6377y).f11050n = this.f6360f;
            int i5 = this.f6369q;
            if (i5 != 0) {
                onWindowSystemUiVisibilityChanged(i5);
                WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
                androidx.core.view.K.c(this);
            }
        }
    }

    public void setHasNonEmbeddedTabs(boolean z9) {
        this.f6366m = z9;
    }

    public void setHideOnContentScrollEnabled(boolean z9) {
        if (z9 != this.f6367n) {
            this.f6367n = z9;
            if (z9) {
                return;
            }
            b();
            setActionBarHideOffset(0);
        }
    }

    public void setIcon(int i5) {
        e();
        c2 c2Var = (c2) this.f6362i;
        c2Var.f6682d = i5 != 0 ? android.support.v4.media.session.f.y(c2Var.f6679a.getContext(), i5) : null;
        c2Var.c();
    }

    public void setLogo(int i5) {
        e();
        c2 c2Var = (c2) this.f6362i;
        c2Var.f6683e = i5 != 0 ? android.support.v4.media.session.f.y(c2Var.f6679a.getContext(), i5) : null;
        c2Var.c();
    }

    public void setOverlayMode(boolean z9) {
        this.f6365l = z9;
        this.f6364k = z9 && getContext().getApplicationInfo().targetSdkVersion < 19;
    }

    public void setShowingForActionMode(boolean z9) {
    }

    public void setUiOptions(int i5) {
    }

    @Override // androidx.appcompat.widget.InterfaceC0175o0
    public void setWindowCallback(Window.Callback callback) {
        e();
        ((c2) this.f6362i).f6688k = callback;
    }

    @Override // androidx.appcompat.widget.InterfaceC0175o0
    public void setWindowTitle(CharSequence charSequence) {
        e();
        c2 c2Var = (c2) this.f6362i;
        if (c2Var.f6685g) {
            return;
        }
        c2Var.h = charSequence;
        if ((c2Var.f6680b & 8) != 0) {
            Toolbar toolbar = c2Var.f6679a;
            toolbar.setTitle(charSequence);
            if (c2Var.f6685g) {
                androidx.core.view.W.j(toolbar.getRootView(), charSequence);
            }
        }
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override // androidx.core.view.InterfaceC0224p
    public final void onNestedPreScroll(View view, int i5, int i7, int[] iArr, int i9) {
    }

    @Override // androidx.core.view.InterfaceC0224p
    public final void onNestedScroll(View view, int i5, int i7, int i9, int i10, int i11) {
        if (i11 == 0) {
            onNestedScroll(view, i5, i7, i9, i10);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i5) {
        if ((i5 & 2) == 0 || this.h.getVisibility() != 0) {
            return false;
        }
        return this.f6367n;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        if (!this.f6367n || this.o) {
            return;
        }
        if (this.f6368p <= this.h.getHeight()) {
            b();
            postDelayed(this.f6356C, 600L);
        } else {
            b();
            postDelayed(this.f6357D, 600L);
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new C0147f(layoutParams);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i5, int i7, int i9, int i10) {
        int i11 = this.f6368p + i7;
        this.f6368p = i11;
        setActionBarHideOffset(i11);
    }

    public void setIcon(Drawable drawable) {
        e();
        c2 c2Var = (c2) this.f6362i;
        c2Var.f6682d = drawable;
        c2Var.c();
    }

    @Override // androidx.core.view.InterfaceC0224p
    public final void onNestedScrollAccepted(View view, View view2, int i5, int i7) {
        if (i7 == 0) {
            onNestedScrollAccepted(view, view2, i5);
        }
    }
}
