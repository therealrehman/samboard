package androidx.core.widget;

import B8.e;
import P.h;
import P.i;
import P.j;
import P.k;
import P.l;
import P.m;
import P.o;
import android.R;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.v4.media.session.f;
import android.util.AttributeSet;
import android.util.Log;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.EdgeEffect;
import android.widget.FrameLayout;
import android.widget.OverScroller;
import androidx.core.view.C0212d;
import androidx.core.view.C0223o;
import androidx.core.view.InterfaceC0222n;
import androidx.core.view.InterfaceC0224p;
import androidx.core.view.InterfaceC0225q;
import androidx.core.view.M;
import androidx.core.view.W;
import androidx.core.view.r;
import com.bumptech.glide.d;
import f6.AbstractC0527a;
import h6.AbstractC0582a;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class NestedScrollView extends FrameLayout implements InterfaceC0225q, InterfaceC0222n {

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public static final float f7276h0 = (float) (Math.log(0.78d) / Math.log(0.9d));

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public static final j f7277i0 = new j();
    public static final int[] j0 = {R.attr.fillViewport};

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public o f7278A;

    /* JADX INFO: renamed from: B, reason: collision with root package name */
    public final r f7279B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final C0223o f7280C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public float f7281D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public boolean f7282E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public int f7283F;
    public boolean G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final Rect f7284H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public int f7285I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f7286J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final boolean f7287K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public boolean f7288L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public boolean f7289M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public l f7290N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public int f7291O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public int f7292P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public int f7293Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public long f7294R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public long f7295S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public final long f7296T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public long f7297U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public boolean f7298V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public final Context f7299W;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public int f7300a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public boolean f7301b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public final int[] f7302c0;
    public int d0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f7303e;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public int f7304e0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f7305f;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public final C0212d f7306f0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Rect f7307g;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public final h f7308g0;
    public final OverScroller h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final EdgeEffect f7309i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final EdgeEffect f7310j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f7311k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f7312l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f7313m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public View f7314n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public VelocityTracker f7315p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f7316q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f7317r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final int f7318s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int f7319t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f7320u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f7321v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final int[] f7322w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int[] f7323x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public int f7324y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public int f7325z;

    static {
        new LinearInterpolator();
    }

    public NestedScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, com.samsung.android.keyscafe.R.attr.nestedScrollViewStyle);
        this.f7307g = new Rect();
        this.f7312l = true;
        this.f7313m = false;
        this.f7314n = null;
        this.o = false;
        this.f7317r = true;
        this.f7321v = -1;
        this.f7322w = new int[2];
        this.f7323x = new int[2];
        this.f7282E = false;
        this.f7283F = 0;
        this.G = false;
        this.f7284H = new Rect();
        this.f7285I = 0;
        new PathInterpolator(0.33f, 0.0f, 0.3f, 1.0f);
        Paint paint = new Paint();
        new h(this, 1);
        new h(this, 2);
        new h(this, 3);
        this.f7286J = false;
        this.f7287K = true;
        this.f7288L = false;
        this.f7289M = false;
        this.f7291O = 0;
        this.f7292P = 0;
        this.f7293Q = -1;
        this.f7294R = 0L;
        this.f7295S = 0L;
        this.f7296T = 300L;
        this.f7297U = 0L;
        this.f7298V = false;
        this.f7300a0 = 0;
        this.f7301b0 = false;
        this.f7302c0 = new int[2];
        this.d0 = 0;
        this.f7304e0 = 0;
        this.f7306f0 = new C0212d(getContext(), new e(9, this));
        View.OnLayoutChangeListener iVar = new i(this);
        h hVar = new h(this, 0);
        this.f7308g0 = hVar;
        this.f7299W = context;
        this.f7309i = AbstractC0527a.I() ? P.e.a(context, attributeSet) : new EdgeEffect(context);
        this.f7310j = AbstractC0527a.I() ? P.e.a(context, attributeSet) : new EdgeEffect(context);
        this.f7303e = context.getResources().getDisplayMetrics().density * 160.0f * 386.0878f * 0.84f;
        this.h = new OverScroller(getContext());
        setFocusable(true);
        setDescendantFocusability(262144);
        setWillNotDraw(false);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        this.f7318s = viewConfiguration.getScaledTouchSlop();
        this.f7319t = viewConfiguration.getScaledMinimumFlingVelocity();
        this.f7320u = viewConfiguration.getScaledMaximumFlingVelocity();
        post(hVar);
        addOnLayoutChangeListener(iVar);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, j0, com.samsung.android.keyscafe.R.attr.nestedScrollViewStyle, 0);
        setFillViewport(typedArrayObtainStyledAttributes.getBoolean(0, false));
        typedArrayObtainStyledAttributes.recycle();
        this.f7279B = new r();
        this.f7280C = new C0223o(this);
        setNestedScrollingEnabled(true);
        W.i(this, f7277i0);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    public static boolean i(View view, View view2) {
        if (view == view2) {
            return true;
        }
        Object parent = view.getParent();
        return (parent instanceof ViewGroup) && i((View) parent, view2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupGoToTop(int i5) {
        String string;
        AccessibilityManager accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        if (accessibilityManager == null || !accessibilityManager.isEnabled() || (string = Settings.Secure.getString(getContext().getContentResolver(), "enabled_accessibility_services")) == null || string.matches("(?i).*com.samsung.accessibility/com.samsung.android.app.talkback.TalkBackService.*") || string.matches("(?i).*com.samsung.android.accessibility.talkback/com.samsung.android.marvin.talkback.TalkBackService.*") || string.matches("(?i).*com.google.android.marvin.talkback.TalkBackService.*")) {
            return;
        }
        string.matches("(?i).*com.samsung.accessibility/com.samsung.accessibility.universalswitch.UniversalSwitchService.*");
    }

    @Override // android.view.ViewGroup
    public final void addView(View view) {
        if (getChildCount() > 0) {
            throw new IllegalStateException("ScrollView can host only one direct child");
        }
        super.addView(view);
    }

    public final boolean b(int i5) {
        View viewFindFocus = findFocus();
        if (viewFindFocus == this) {
            viewFindFocus = null;
        }
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, i5);
        int maxScrollAmount = getMaxScrollAmount();
        if (viewFindNextFocus == null || !j(viewFindNextFocus, maxScrollAmount, getHeight())) {
            if (i5 == 33 && getScrollY() < maxScrollAmount) {
                maxScrollAmount = getScrollY();
            } else if (i5 == 130 && getChildCount() > 0) {
                View childAt = getChildAt(0);
                maxScrollAmount = Math.min((childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin) - ((getHeight() + getScrollY()) - getPaddingBottom()), maxScrollAmount);
            }
            if (maxScrollAmount == 0) {
                return false;
            }
            if (i5 != 130) {
                maxScrollAmount = -maxScrollAmount;
            }
            e(maxScrollAmount);
        } else {
            Rect rect = this.f7307g;
            viewFindNextFocus.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(viewFindNextFocus, rect);
            int iD = d(rect);
            this.f7325z = getScrollY();
            e(iD);
            viewFindNextFocus.requestFocus(i5);
        }
        if (viewFindFocus != null && viewFindFocus.isFocused() && (!j(viewFindFocus, 0, getHeight()))) {
            int descendantFocusability = getDescendantFocusability();
            setDescendantFocusability(131072);
            requestFocus();
            setDescendantFocusability(descendantFocusability);
        }
        return true;
    }

    public final boolean c() {
        int overScrollMode = getOverScrollMode();
        if (overScrollMode != 0) {
            return overScrollMode == 1 && getScrollRange() > 0;
        }
        return true;
    }

    @Override // android.view.View
    public final int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    @Override // android.view.View
    public final int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @Override // android.view.View
    public final int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00fb  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void computeScroll() {
        /*
            Method dump skipped, instruction units count: 268
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.computeScroll():void");
    }

    @Override // android.view.View
    public final int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @Override // android.view.View
    public final int computeVerticalScrollOffset() {
        return Math.max(0, super.computeVerticalScrollOffset());
    }

    @Override // android.view.View
    public final int computeVerticalScrollRange() {
        int childCount = getChildCount();
        int height = (getHeight() - getPaddingBottom()) - getPaddingTop();
        if (childCount == 0) {
            return height;
        }
        View childAt = getChildAt(0);
        int bottom = childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
        int scrollY = getScrollY();
        int iMax = Math.max(0, bottom - height);
        return scrollY < 0 ? bottom - scrollY : scrollY > iMax ? bottom + (scrollY - iMax) : bottom;
    }

    public final int d(Rect rect) {
        if (getChildCount() == 0) {
            return 0;
        }
        int height = getHeight();
        int scrollY = getScrollY();
        int i5 = scrollY + height;
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        if (rect.top > 0) {
            scrollY += verticalFadingEdgeLength;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int i7 = rect.bottom < (childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin ? i5 - verticalFadingEdgeLength : i5;
        int i9 = rect.bottom;
        if (i9 > i7 && rect.top > scrollY) {
            return Math.min(rect.height() > height ? rect.top - scrollY : rect.bottom - i7, (childAt.getBottom() + layoutParams.bottomMargin) - i5);
        }
        if (rect.top >= scrollY || i9 >= i7) {
            return 0;
        }
        return Math.max(rect.height() > height ? 0 - (i7 - rect.bottom) : 0 - (scrollY - rect.top), -getScrollY());
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        Context context = this.f7299W;
        if (action == 9) {
            if (this.f7301b0) {
                int[] iArr = this.f7302c0;
                getLocationInWindow(iArr);
                int i5 = this.f7304e0;
                int i7 = this.f7300a0;
                int i9 = iArr[1];
                int i10 = i7 - i9;
                int i11 = i5 - i10;
                this.d0 = i11;
                if (i10 < 0) {
                    this.f7304e0 = i11;
                    this.f7300a0 = i9;
                }
            }
            int toolType = motionEvent.getToolType(0);
            this.f7289M = true;
            if (!this.f7286J || !this.f7287K) {
                this.f7289M = false;
            }
            if (this.f7289M && toolType == 2) {
                if (Settings.System.getInt(context.getContentResolver(), AbstractC0582a.I(), 0) != 1) {
                    this.f7289M = false;
                }
            }
            if (this.f7289M && toolType == 3) {
                this.f7289M = false;
            }
        }
        if (!this.f7289M) {
            return super.dispatchHoverEvent(motionEvent);
        }
        int x9 = (int) motionEvent.getX();
        int y4 = (int) motionEvent.getY();
        int childCount = getChildCount();
        int scrollRange = getScrollRange();
        if (this.f7290N == null) {
            this.f7290N = new l(this);
        }
        if (this.f7291O <= 0 || this.f7292P <= 0) {
            this.f7291O = (int) (TypedValue.applyDimension(1, 25.0f, context.getResources().getDisplayMetrics()) + 0.5f);
            this.f7292P = (int) (TypedValue.applyDimension(1, 25.0f, context.getResources().getDisplayMetrics()) + 0.5f);
        }
        int height = childCount != 0 ? getHeight() : 0;
        boolean z9 = motionEvent.getToolType(0) == 2;
        if ((y4 > this.f7291O && y4 < (height - this.f7292P) - this.d0) || x9 <= 0 || x9 > getRight() || scrollRange == 0 || ((y4 >= 0 && y4 <= this.f7291O && getScrollY() <= 0 && this.f7298V) || ((y4 >= height - this.f7292P && y4 <= height && getScrollY() >= scrollRange && this.f7298V) || ((z9 && motionEvent.getButtonState() == 32) || !z9 || ((KeyguardManager) context.getSystemService("keyguard")).inKeyguardRestrictedInputMode())))) {
            if (this.f7290N.hasMessages(1)) {
                this.f7290N.removeMessages(1);
                p(motionEvent, f.B());
            }
            if ((y4 > this.f7291O && y4 < height - this.f7292P) || x9 <= 0 || x9 > getRight()) {
                this.f7298V = false;
            }
            if (this.f7288L || this.f7297U != 0) {
                p(motionEvent, f.B());
            }
            this.f7295S = 0L;
            this.f7297U = 0L;
            this.f7288L = false;
            return super.dispatchHoverEvent(motionEvent);
        }
        if (!this.f7288L) {
            this.f7297U = System.currentTimeMillis();
        }
        if (action != 7) {
            if (action == 9) {
                this.f7288L = true;
                if (y4 < 0 || y4 > this.f7291O) {
                    if (y4 >= (height - this.f7292P) - this.d0 && y4 <= height && !this.f7290N.hasMessages(1)) {
                        this.f7295S = System.currentTimeMillis();
                        p(motionEvent, f.D());
                        this.f7293Q = 1;
                        this.f7290N.sendEmptyMessage(1);
                    }
                } else if (!this.f7290N.hasMessages(1)) {
                    this.f7295S = System.currentTimeMillis();
                    p(motionEvent, f.E());
                    this.f7293Q = 2;
                    this.f7290N.sendEmptyMessage(1);
                }
            } else if (action == 10) {
                if (this.f7290N.hasMessages(1)) {
                    this.f7290N.removeMessages(1);
                }
                p(motionEvent, f.B());
                this.f7295S = 0L;
                this.f7297U = 0L;
                this.f7298V = false;
                this.f7288L = false;
                this.h.forceFinished(true);
                return super.dispatchHoverEvent(motionEvent);
            }
        } else {
            if (!this.f7288L) {
                this.f7288L = true;
                motionEvent.setAction(10);
                return super.dispatchHoverEvent(motionEvent);
            }
            if (y4 < 0 || y4 > this.f7291O) {
                if (y4 >= (height - this.f7292P) - this.d0 && y4 <= height && !this.f7290N.hasMessages(1)) {
                    this.f7295S = System.currentTimeMillis();
                    if (!this.f7298V || this.f7293Q == 2) {
                        p(motionEvent, f.D());
                    }
                    this.f7293Q = 1;
                    this.f7290N.sendEmptyMessage(1);
                }
            } else if (!this.f7290N.hasMessages(1)) {
                this.f7295S = System.currentTimeMillis();
                if (!this.f7298V || this.f7293Q == 1) {
                    p(motionEvent, f.E());
                }
                this.f7293Q = 2;
                this.f7290N.sendEmptyMessage(1);
            }
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent) || f(keyEvent);
    }

    @Override // android.view.View
    public final boolean dispatchNestedFling(float f2, float f7, boolean z9) {
        return this.f7280C.a(f2, f7, z9);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreFling(float f2, float f7) {
        d.E(this, 1.0f);
        return this.f7280C.b(f2, f7);
    }

    @Override // android.view.View
    public final boolean dispatchNestedPreScroll(int i5, int i7, int[] iArr, int[] iArr2) {
        return this.f7280C.c(i5, i7, iArr, iArr2, 0);
    }

    @Override // android.view.View
    public final boolean dispatchNestedScroll(int i5, int i7, int i9, int i10, int[] iArr) {
        return this.f7280C.e(i5, i7, i9, i10, iArr, 0, null);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        MotionEvent motionEvent2;
        int height;
        int i5;
        int x9 = (int) motionEvent.getX();
        int y4 = (int) motionEvent.getY();
        int childCount = getChildCount();
        int scrollRange = getScrollRange();
        if (this.f7290N == null) {
            this.f7290N = new l(this);
        }
        if (this.f7291O <= 0 || this.f7292P <= 0) {
            Context context = this.f7299W;
            this.f7291O = (int) (TypedValue.applyDimension(1, 25.0f, context.getResources().getDisplayMetrics()) + 0.5f);
            this.f7292P = (int) (TypedValue.applyDimension(1, 25.0f, context.getResources().getDisplayMetrics()) + 0.5f);
        }
        if (childCount != 0) {
            height = getHeight();
            motionEvent2 = motionEvent;
        } else {
            motionEvent2 = motionEvent;
            height = 0;
        }
        boolean z9 = motionEvent2.getToolType(0) == 2;
        int action = motionEvent.getAction();
        Rect rect = this.f7284H;
        if (action == 0) {
            this.f7282E = false;
            if (this.G && this.f7285I != 2 && rect.contains(x9, y4)) {
                setupGoToTop(2);
                throw null;
            }
        } else if (action != 1) {
            if (action != 2) {
                if (action == 3 && this.G && this.f7285I != 0) {
                    int[] iArr = StateSet.NOTHING;
                    throw null;
                }
            } else if (this.G && this.f7285I == 2) {
                if (rect.contains(x9, y4)) {
                    return true;
                }
                this.f7285I = 1;
                int[] iArr2 = StateSet.NOTHING;
                throw null;
            }
        } else if (this.G && this.f7285I == 2) {
            if (canScrollVertically(-1)) {
                post(new h(this, 4));
            }
            this.f7285I = 1;
            int[] iArr3 = StateSet.NOTHING;
            throw null;
        }
        if ((y4 > this.f7291O && y4 < height - this.f7292P) || scrollRange == 0 || !z9 || motionEvent.getButtonState() != 32) {
            if (this.f7290N.hasMessages(1)) {
                this.f7290N.removeMessages(1);
            }
            this.f7295S = 0L;
            this.f7297U = 0L;
            this.f7288L = false;
            this.f7298V = false;
            return super.dispatchTouchEvent(motionEvent);
        }
        if (!this.f7288L) {
            this.f7297U = System.currentTimeMillis();
        }
        switch (action) {
            case 211:
                if (this.G && this.f7285I != 2 && rect.contains(x9, y4)) {
                    setupGoToTop(2);
                    throw null;
                }
                break;
            case 212:
                if (!this.G || this.f7285I != 2) {
                    if (this.f7290N.hasMessages(1)) {
                        this.f7290N.removeMessages(1);
                    }
                    this.f7295S = 0L;
                    this.f7297U = 0L;
                    this.f7298V = false;
                    this.f7288L = false;
                    return super.dispatchTouchEvent(motionEvent);
                }
                Log.d("NestedScrollView", "pen up false GOTOTOP");
                if (canScrollVertically(-1)) {
                    i5 = 0;
                    q(0 - getScrollX(), 0 - getScrollY(), 250);
                    this.f7309i.onAbsorb(10000);
                    invalidate();
                } else {
                    i5 = 0;
                }
                setupGoToTop(i5);
                int[] iArr4 = StateSet.NOTHING;
                throw null;
            case 213:
                if (this.G && this.f7285I == 2 && !rect.contains(x9, y4)) {
                    this.f7285I = 1;
                    int[] iArr5 = StateSet.NOTHING;
                    throw null;
                }
                break;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        int paddingLeft;
        super.draw(canvas);
        int scrollY = getScrollY();
        EdgeEffect edgeEffect = this.f7309i;
        int paddingLeft2 = 0;
        if (!edgeEffect.isFinished()) {
            int iSave = canvas.save();
            int width = getWidth();
            int height = getHeight();
            int iMin = Math.min(0, scrollY);
            if (k.a(this)) {
                width -= getPaddingRight() + getPaddingLeft();
                paddingLeft = getPaddingLeft();
            } else {
                paddingLeft = 0;
            }
            if (k.a(this)) {
                height -= getPaddingBottom() + getPaddingTop();
                iMin += getPaddingTop();
            }
            canvas.translate(paddingLeft, iMin);
            edgeEffect.setSize(width, height);
            if (edgeEffect.draw(canvas)) {
                postInvalidateOnAnimation();
            }
            canvas.restoreToCount(iSave);
        }
        EdgeEffect edgeEffect2 = this.f7310j;
        if (edgeEffect2.isFinished()) {
            return;
        }
        int iSave2 = canvas.save();
        int width2 = getWidth();
        int height2 = getHeight();
        int iMax = Math.max(getScrollRange(), scrollY) + height2;
        if (k.a(this)) {
            width2 -= getPaddingRight() + getPaddingLeft();
            paddingLeft2 = getPaddingLeft();
        }
        if (k.a(this)) {
            height2 -= getPaddingBottom() + getPaddingTop();
            iMax -= getPaddingBottom();
        }
        canvas.translate(paddingLeft2 - width2, iMax);
        canvas.rotate(180.0f, width2, 0.0f);
        edgeEffect2.setSize(width2, height2);
        if (edgeEffect2.draw(canvas)) {
            postInvalidateOnAnimation();
        }
        canvas.restoreToCount(iSave2);
    }

    public final void e(int i5) {
        if (i5 != 0) {
            if (this.f7317r) {
                q(0, i5, 250);
            } else {
                scrollBy(0, i5);
            }
        }
    }

    public final boolean f(KeyEvent keyEvent) {
        Rect rect = this.f7307g;
        rect.setEmpty();
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            if (childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin > (getHeight() - getPaddingTop()) - getPaddingBottom()) {
                if (keyEvent.getAction() != 0) {
                    return false;
                }
                int keyCode = keyEvent.getKeyCode();
                if (keyCode == 19) {
                    return !keyEvent.isAltPressed() ? b(33) : h(33);
                }
                if (keyCode == 20) {
                    return !keyEvent.isAltPressed() ? b(130) : h(130);
                }
                if (keyCode != 62) {
                    return false;
                }
                int i5 = keyEvent.isShiftPressed() ? 33 : 130;
                boolean z9 = i5 == 130;
                int height = getHeight();
                if (z9) {
                    rect.top = getScrollY() + height;
                    int childCount = getChildCount();
                    if (childCount > 0) {
                        View childAt2 = getChildAt(childCount - 1);
                        int paddingBottom = getPaddingBottom() + childAt2.getBottom() + ((FrameLayout.LayoutParams) childAt2.getLayoutParams()).bottomMargin;
                        if (rect.top + height > paddingBottom) {
                            rect.top = paddingBottom - height;
                        }
                    }
                } else {
                    int scrollY = getScrollY() - height;
                    rect.top = scrollY;
                    if (scrollY < 0) {
                        rect.top = 0;
                    }
                }
                int i7 = rect.top;
                int i9 = height + i7;
                rect.bottom = i9;
                n(i5, i7, i9);
                return false;
            }
        }
        if (!isFocused() || keyEvent.getKeyCode() == 4) {
            return false;
        }
        View viewFindFocus = findFocus();
        if (viewFindFocus == this) {
            viewFindFocus = null;
        }
        View viewFindNextFocus = FocusFinder.getInstance().findNextFocus(this, viewFindFocus, 130);
        return (viewFindNextFocus == null || viewFindNextFocus == this || !viewFindNextFocus.requestFocus(130)) ? false : true;
    }

    public final void g(int i5) {
        if (getChildCount() > 0) {
            this.h.fling(getScrollX(), getScrollY(), 0, i5, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);
            d.E(this, Math.abs(this.h.getCurrVelocity()));
            r(2, 1);
            this.f7325z = getScrollY();
            postInvalidateOnAnimation();
        }
    }

    @Override // android.view.View
    public float getBottomFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int bottom = ((childAt.getBottom() + layoutParams.bottomMargin) - getScrollY()) - (getHeight() - getPaddingBottom());
        if (bottom < verticalFadingEdgeLength) {
            return bottom / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    public int getMaxScrollAmount() {
        return (int) (getHeight() * 0.5f);
    }

    @Override // android.view.ViewGroup
    public int getNestedScrollAxes() {
        r rVar = this.f7279B;
        return rVar.f7254b | rVar.f7253a;
    }

    public int getScrollRange() {
        if (getChildCount() <= 0) {
            return 0;
        }
        View childAt = getChildAt(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
        return Math.max(0, ((childAt.getHeight() + layoutParams.topMargin) + layoutParams.bottomMargin) - ((getHeight() - getPaddingTop()) - getPaddingBottom()));
    }

    @Override // android.view.View
    public float getTopFadingEdgeStrength() {
        if (getChildCount() == 0) {
            return 0.0f;
        }
        int verticalFadingEdgeLength = getVerticalFadingEdgeLength();
        int scrollY = getScrollY();
        if (scrollY < verticalFadingEdgeLength) {
            return scrollY / verticalFadingEdgeLength;
        }
        return 1.0f;
    }

    public float getVerticalScrollFactorCompat() {
        if (this.f7281D == 0.0f) {
            TypedValue typedValue = new TypedValue();
            Context context = getContext();
            if (!context.getTheme().resolveAttribute(R.attr.listPreferredItemHeight, typedValue, true)) {
                throw new IllegalStateException("Expected theme to define listPreferredItemHeight.");
            }
            this.f7281D = typedValue.getDimension(context.getResources().getDisplayMetrics());
        }
        return this.f7281D;
    }

    public final boolean h(int i5) {
        int childCount;
        boolean z9 = i5 == 130;
        int height = getHeight();
        Rect rect = this.f7307g;
        rect.top = 0;
        rect.bottom = height;
        if (z9 && (childCount = getChildCount()) > 0) {
            View childAt = getChildAt(childCount - 1);
            int paddingBottom = getPaddingBottom() + childAt.getBottom() + ((FrameLayout.LayoutParams) childAt.getLayoutParams()).bottomMargin;
            rect.bottom = paddingBottom;
            rect.top = paddingBottom - height;
        }
        return n(i5, rect.top, rect.bottom);
    }

    @Override // android.view.View
    public final boolean hasNestedScrollingParent() {
        return this.f7280C.g(0);
    }

    @Override // android.view.View
    public final boolean isNestedScrollingEnabled() {
        return this.f7280C.f7247d;
    }

    public final boolean j(View view, int i5, int i7) {
        Rect rect = this.f7307g;
        view.getDrawingRect(rect);
        offsetDescendantRectToMyCoords(view, rect);
        return rect.bottom + i5 >= getScrollY() && rect.top - i5 <= getScrollY() + i7;
    }

    public final void k(int i5, int i7, int[] iArr) {
        if (!this.f7282E || this.h.isFinished()) {
            int scrollY = getScrollY();
            scrollBy(0, i5);
            this.f7325z = getScrollY();
            if (this.h.springBack(getScrollX(), getScrollY(), 0, 0, 0, getScrollRange())) {
                postInvalidateOnAnimation();
            }
            int scrollY2 = getScrollY() - scrollY;
            if (iArr != null) {
                iArr[1] = iArr[1] + scrollY2;
            }
            this.f7280C.d(scrollY2, i5 - scrollY2, i7, iArr);
        }
    }

    public final void l(MotionEvent motionEvent) {
        int actionIndex = motionEvent.getActionIndex();
        if (motionEvent.getPointerId(actionIndex) == this.f7321v) {
            int i5 = actionIndex == 0 ? 1 : 0;
            this.f7311k = (int) motionEvent.getY(i5);
            this.f7321v = motionEvent.getPointerId(i5);
            VelocityTracker velocityTracker = this.f7315p;
            if (velocityTracker != null) {
                velocityTracker.clear();
            }
        }
    }

    public final boolean m(int i5, int i7, int i9, int i10) {
        boolean z9;
        boolean z10;
        getOverScrollMode();
        super.computeHorizontalScrollRange();
        super.computeHorizontalScrollExtent();
        computeVerticalScrollRange();
        super.computeVerticalScrollExtent();
        int i11 = i9 + i5;
        if (i7 <= 0 && i7 >= 0) {
            z9 = false;
        } else {
            i7 = 0;
            z9 = true;
        }
        if (i11 > i10) {
            z10 = true;
        } else if (i11 < 0) {
            i10 = 0;
            z10 = true;
        } else {
            i10 = i11;
            z10 = false;
        }
        if (z10 && !this.f7280C.g(1)) {
            this.h.springBack(i7, i10, 0, 0, 0, getScrollRange());
        }
        super.scrollTo(i7, i10);
        return z9 || z10;
    }

    @Override // android.view.ViewGroup
    public final void measureChild(View view, int i5, int i7) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i5, getPaddingRight() + getPaddingLeft(), layoutParams.width), View.MeasureSpec.makeMeasureSpec(0, 0));
    }

    @Override // android.view.ViewGroup
    public final void measureChildWithMargins(View view, int i5, int i7, int i9, int i10) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        view.measure(ViewGroup.getChildMeasureSpec(i5, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i7, marginLayoutParams.width), View.MeasureSpec.makeMeasureSpec(marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, 0));
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0068  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean n(int r18, int r19, int r20) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            int r4 = r17.getHeight()
            int r5 = r17.getScrollY()
            int r4 = r4 + r5
            r6 = 33
            if (r1 != r6) goto L17
            r6 = 1
            goto L18
        L17:
            r6 = 0
        L18:
            r9 = 2
            java.util.ArrayList r9 = r0.getFocusables(r9)
            int r10 = r9.size()
            r11 = 0
            r12 = 0
            r13 = 0
        L24:
            if (r12 >= r10) goto L6c
            java.lang.Object r14 = r9.get(r12)
            android.view.View r14 = (android.view.View) r14
            int r15 = r14.getTop()
            int r7 = r14.getBottom()
            if (r2 >= r7) goto L69
            if (r15 >= r3) goto L69
            if (r2 >= r15) goto L3f
            if (r7 >= r3) goto L3f
            r16 = 1
            goto L41
        L3f:
            r16 = 0
        L41:
            if (r11 != 0) goto L47
            r11 = r14
            r13 = r16
            goto L69
        L47:
            if (r6 == 0) goto L4f
            int r8 = r11.getTop()
            if (r15 < r8) goto L57
        L4f:
            if (r6 != 0) goto L59
            int r8 = r11.getBottom()
            if (r7 <= r8) goto L59
        L57:
            r7 = 1
            goto L5a
        L59:
            r7 = 0
        L5a:
            if (r13 == 0) goto L61
            if (r16 == 0) goto L69
            if (r7 == 0) goto L69
            goto L68
        L61:
            if (r16 == 0) goto L66
            r11 = r14
            r13 = 1
            goto L69
        L66:
            if (r7 == 0) goto L69
        L68:
            r11 = r14
        L69:
            int r12 = r12 + 1
            goto L24
        L6c:
            if (r11 != 0) goto L6f
            r11 = r0
        L6f:
            if (r2 < r5) goto L75
            if (r3 > r4) goto L75
            r7 = 0
            goto L7f
        L75:
            if (r6 == 0) goto L79
            int r2 = r2 - r5
            goto L7b
        L79:
            int r2 = r3 - r4
        L7b:
            r0.e(r2)
            r7 = 1
        L7f:
            android.view.View r0 = r17.findFocus()
            if (r11 == r0) goto L88
            r11.requestFocus(r1)
        L88:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.n(int, int, int):boolean");
    }

    public final boolean o(EdgeEffect edgeEffect, int i5) {
        if (i5 > 0) {
            return true;
        }
        float fQ = AbstractC0527a.q(edgeEffect) * getHeight();
        float fAbs = Math.abs(-i5) * 0.35f;
        float f2 = this.f7303e * 0.015f;
        double dLog = Math.log(fAbs / f2);
        double d8 = f7276h0;
        return ((float) (Math.exp((d8 / (d8 - 1.0d)) * dLog) * ((double) f2))) < fQ;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f7313m = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:121:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0111  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onGenericMotionEvent(android.view.MotionEvent r31) {
        /*
            Method dump skipped, instruction units count: 974
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.onGenericMotionEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0124  */
    @Override // android.view.ViewGroup
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onInterceptTouchEvent(android.view.MotionEvent r13) {
        /*
            Method dump skipped, instruction units count: 322
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        int measuredHeight;
        super.onLayout(z9, i5, i7, i9, i10);
        this.f7312l = false;
        View view = this.f7314n;
        if (view != null && i(view, this)) {
            View view2 = this.f7314n;
            Rect rect = this.f7307g;
            view2.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(view2, rect);
            int iD = d(rect);
            if (iD != 0) {
                scrollBy(0, iD);
            }
        }
        this.f7314n = null;
        if (z9) {
            getResources().getDimensionPixelOffset(com.samsung.android.keyscafe.R.dimen.sesl_nestedscrollview_overlay_feature_hidden_height);
        }
        if (!this.f7313m) {
            if (this.f7278A != null) {
                scrollTo(getScrollX(), this.f7278A.f2401e);
                this.f7278A = null;
            }
            if (getChildCount() > 0) {
                View childAt = getChildAt(0);
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                measuredHeight = childAt.getMeasuredHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            } else {
                measuredHeight = 0;
            }
            int paddingTop = ((i10 - i7) - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            int i11 = (paddingTop >= measuredHeight || scrollY < 0) ? 0 : paddingTop + scrollY > measuredHeight ? measuredHeight - paddingTop : scrollY;
            if (i11 != scrollY) {
                scrollTo(getScrollX(), i11);
            }
        }
        scrollTo(getScrollX(), getScrollY());
        this.f7313m = true;
        if (!z9 || super.computeHorizontalScrollRange() > super.computeHorizontalScrollExtent()) {
            return;
        }
        this.f7301b0 = false;
        ViewParent parent = getParent();
        while (true) {
            if (parent == null || !(parent instanceof ViewGroup)) {
                break;
            }
            if (parent instanceof InterfaceC0224p) {
                for (Class<?> superclass = parent.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
                    if (superclass.getSimpleName().equals("CoordinatorLayout")) {
                        ViewGroup viewGroup = (ViewGroup) parent;
                        int[] iArr = this.f7302c0;
                        viewGroup.getLocationInWindow(iArr);
                        int height = viewGroup.getHeight() + iArr[1];
                        getLocationInWindow(iArr);
                        this.f7300a0 = iArr[1];
                        int height2 = getHeight() - (height - this.f7300a0);
                        this.d0 = height2;
                        if (height2 < 0) {
                            this.d0 = 0;
                        }
                        this.f7304e0 = this.d0;
                        this.f7301b0 = true;
                    }
                }
            }
            parent = parent.getParent();
        }
        if (this.f7301b0) {
            return;
        }
        this.f7300a0 = 0;
        this.d0 = 0;
        this.f7304e0 = 0;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i5, int i7) {
        super.onMeasure(i5, i7);
        if (this.f7316q && View.MeasureSpec.getMode(i7) != 0 && getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int measuredHeight = childAt.getMeasuredHeight();
            int measuredHeight2 = (((getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) - layoutParams.topMargin) - layoutParams.bottomMargin;
            if (measuredHeight < measuredHeight2) {
                childAt.measure(ViewGroup.getChildMeasureSpec(i5, getPaddingRight() + getPaddingLeft() + layoutParams.leftMargin + layoutParams.rightMargin, layoutParams.width), View.MeasureSpec.makeMeasureSpec(measuredHeight2, 1073741824));
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedFling(View view, float f2, float f7, boolean z9) {
        if (z9) {
            return false;
        }
        dispatchNestedFling(0.0f, f7, true);
        g((int) f7);
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onNestedPreFling(View view, float f2, float f7) {
        return dispatchNestedPreFling(f2, f7);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedPreScroll(View view, int i5, int i7, int[] iArr) {
        this.f7280C.c(i5, i7, iArr, null, 0);
    }

    @Override // androidx.core.view.InterfaceC0225q
    public final void onNestedScroll(View view, int i5, int i7, int i9, int i10, int i11, int[] iArr) {
        k(i10, i11, iArr);
    }

    @Override // androidx.core.view.InterfaceC0224p
    public final void onNestedScrollAccepted(View view, View view2, int i5, int i7) {
        r rVar = this.f7279B;
        if (i7 == 1) {
            rVar.f7254b = i5;
        } else {
            rVar.f7253a = i5;
        }
        r(2, i7);
    }

    @Override // android.view.View
    public final void onOverScrolled(int i5, int i7, boolean z9, boolean z10) {
        super.scrollTo(i5, i7);
    }

    @Override // android.view.ViewGroup
    public final boolean onRequestFocusInDescendants(int i5, Rect rect) {
        if (i5 == 2) {
            i5 = 130;
        } else if (i5 == 1) {
            i5 = 33;
        }
        View viewFindNextFocus = rect == null ? FocusFinder.getInstance().findNextFocus(this, null, i5) : FocusFinder.getInstance().findNextFocusFromRect(this, rect, i5);
        if (viewFindNextFocus == null || (!j(viewFindNextFocus, 0, getHeight()))) {
            return false;
        }
        return viewFindNextFocus.requestFocus(i5, rect);
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof o)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        o oVar = (o) parcelable;
        super.onRestoreInstanceState(oVar.getSuperState());
        this.f7278A = oVar;
        requestLayout();
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        o oVar = new o(super.onSaveInstanceState());
        oVar.f2401e = getScrollY();
        return oVar;
    }

    @Override // android.view.View
    public final void onScrollChanged(int i5, int i7, int i9, int i10) {
        super.onScrollChanged(i5, i7, i9, i10);
        c();
    }

    @Override // android.view.View
    public final void onSizeChanged(int i5, int i7, int i9, int i10) {
        super.onSizeChanged(i5, i7, i9, i10);
        View viewFindFocus = findFocus();
        if (viewFindFocus == null || this == viewFindFocus || !j(viewFindFocus, 0, i10)) {
            return;
        }
        Rect rect = this.f7307g;
        viewFindFocus.getDrawingRect(rect);
        offsetDescendantRectToMyCoords(viewFindFocus, rect);
        e(d(rect));
    }

    @Override // androidx.core.view.InterfaceC0224p
    public final boolean onStartNestedScroll(View view, View view2, int i5, int i7) {
        return (i5 & 2) != 0;
    }

    @Override // androidx.core.view.InterfaceC0224p
    public final void onStopNestedScroll(View view, int i5) {
        r rVar = this.f7279B;
        if (i5 == 1) {
            rVar.f7254b = 0;
        } else {
            rVar.f7253a = 0;
        }
        stopNestedScroll(i5);
    }

    /* JADX WARN: Removed duplicated region for block: B:117:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0122  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x014a  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0220  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onTouchEvent(android.view.MotionEvent r25) {
        /*
            Method dump skipped, instruction units count: 771
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.widget.NestedScrollView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public final void p(MotionEvent motionEvent, int i5) {
        motionEvent.getDevice();
        d.D(this, motionEvent.getToolType(0), i5 == 20001 ? null : PointerIcon.getSystemIcon(this.f7299W, i5));
    }

    public final void q(int i5, int i7, int i9) {
        if (getChildCount() == 0) {
            return;
        }
        if (AnimationUtils.currentAnimationTimeMillis() - this.f7305f > 250) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int height = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            int height2 = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int scrollY = getScrollY();
            this.h.startScroll(getScrollX(), scrollY, 0, Math.max(0, Math.min(i7 + scrollY, Math.max(0, height - height2))) - scrollY, i9);
            stopNestedScroll(1);
            this.f7325z = getScrollY();
            postInvalidateOnAnimation();
        } else {
            if (!this.h.isFinished()) {
                this.h.abortAnimation();
                stopNestedScroll(1);
            }
            scrollBy(i5, i7);
        }
        this.f7305f = AnimationUtils.currentAnimationTimeMillis();
    }

    public final boolean r(int i5, int i7) {
        return this.f7280C.h(i5, i7);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestChildFocus(View view, View view2) {
        if (this.f7312l) {
            this.f7314n = view2;
        } else {
            Rect rect = this.f7307g;
            view2.getDrawingRect(rect);
            offsetDescendantRectToMyCoords(view2, rect);
            int iD = d(rect);
            if (iD != 0) {
                scrollBy(0, iD);
            }
        }
        super.requestChildFocus(view, view2);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean requestChildRectangleOnScreen(View view, Rect rect, boolean z9) {
        rect.offset(view.getLeft() - view.getScrollX(), view.getTop() - view.getScrollY());
        int iD = d(rect);
        boolean z10 = iD != 0;
        if (z10) {
            if (z9) {
                scrollBy(0, iD);
            } else {
                q(0, iD, 250);
            }
        }
        return z10;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void requestDisallowInterceptTouchEvent(boolean z9) {
        VelocityTracker velocityTracker;
        if (z9 && (velocityTracker = this.f7315p) != null) {
            velocityTracker.recycle();
            this.f7315p = null;
        }
        super.requestDisallowInterceptTouchEvent(z9);
    }

    @Override // android.view.View, android.view.ViewParent
    public final void requestLayout() {
        this.f7312l = true;
        super.requestLayout();
    }

    public final boolean s(MotionEvent motionEvent) {
        boolean z9;
        EdgeEffect edgeEffect = this.f7309i;
        if (AbstractC0527a.q(edgeEffect) != 0.0f) {
            AbstractC0527a.R(edgeEffect, 0.0f, motionEvent.getX() / getWidth());
            z9 = true;
        } else {
            z9 = false;
        }
        EdgeEffect edgeEffect2 = this.f7310j;
        if (AbstractC0527a.q(edgeEffect2) == 0.0f) {
            return z9;
        }
        AbstractC0527a.R(edgeEffect2, 0.0f, 1.0f - (motionEvent.getX() / getWidth()));
        return true;
    }

    @Override // android.view.View
    public final void scrollTo(int i5, int i7) {
        if (getChildCount() > 0) {
            View childAt = getChildAt(0);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
            int width = (getWidth() - getPaddingLeft()) - getPaddingRight();
            int width2 = childAt.getWidth() + layoutParams.leftMargin + layoutParams.rightMargin;
            int height = (getHeight() - getPaddingTop()) - getPaddingBottom();
            int height2 = childAt.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
            if (width >= width2 || i5 < 0) {
                i5 = 0;
            } else if (width + i5 > width2) {
                i5 = width2 - width;
            }
            if (height >= height2 || i7 < 0) {
                i7 = 0;
            } else if (height + i7 > height2) {
                i7 = height2 - height;
            }
            if (i5 == getScrollX() && i7 == getScrollY()) {
                return;
            }
            super.scrollTo(i5, i7);
        }
    }

    public void setFillViewport(boolean z9) {
        if (z9 != this.f7316q) {
            this.f7316q = z9;
            requestLayout();
        }
    }

    @Override // android.view.View
    public void setNestedScrollingEnabled(boolean z9) {
        C0223o c0223o = this.f7280C;
        if (c0223o.f7247d) {
            WeakHashMap weakHashMap = W.f7199a;
            M.z(c0223o.f7246c);
        }
        c0223o.f7247d = z9;
    }

    public void setOnScrollChangeListener(m mVar) {
    }

    public void setSmoothScrollingEnabled(boolean z9) {
        this.f7317r = z9;
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return true;
    }

    @Override // android.view.View
    public final boolean startNestedScroll(int i5) {
        return this.f7280C.h(i5, 0);
    }

    @Override // androidx.core.view.InterfaceC0222n
    public final void stopNestedScroll(int i5) {
        this.f7280C.i(i5);
    }

    @Override // androidx.core.view.InterfaceC0224p
    public final void onNestedPreScroll(View view, int i5, int i7, int[] iArr, int i9) {
        this.f7280C.c(i5, i7, iArr, null, i9);
    }

    @Override // androidx.core.view.InterfaceC0224p
    public final void onNestedScroll(View view, int i5, int i7, int i9, int i10, int i11) {
        k(i10, i11, null);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final boolean onStartNestedScroll(View view, View view2, int i5) {
        return onStartNestedScroll(view, view2, i5, 0);
    }

    @Override // android.view.View
    public final void stopNestedScroll() {
        stopNestedScroll(0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScroll(View view, int i5, int i7, int i9, int i10) {
        k(i10, 0, null);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i5) {
        if (getChildCount() <= 0) {
            super.addView(view, i5);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onNestedScrollAccepted(View view, View view2, int i5) {
        onNestedScrollAccepted(view, view2, i5, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void onStopNestedScroll(View view) {
        onStopNestedScroll(view, 0);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i5, ViewGroup.LayoutParams layoutParams) {
        if (getChildCount() <= 0) {
            super.addView(view, i5, layoutParams);
            return;
        }
        throw new IllegalStateException("ScrollView can host only one direct child");
    }
}
