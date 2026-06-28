package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import com.samsung.android.keyscafe.R;
import e.AbstractC0478a;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuView extends C0 implements androidx.appcompat.view.menu.i, androidx.appcompat.view.menu.y {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public androidx.appcompat.view.menu.j f6379e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Context f6380f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f6381g;
    public boolean h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public C0171n f6382i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public androidx.appcompat.view.menu.v f6383j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public androidx.appcompat.view.menu.h f6384k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f6385l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f6386m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f6387n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public r f6388p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f6389q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f6390r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f6391s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f6392t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f6393u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public int f6394v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final String f6395w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final boolean f6396x;

    public ActionMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        setBaselineAligned(false);
        float f2 = context.getResources().getDisplayMetrics().density;
        this.f6387n = (int) (56.0f * f2);
        this.o = (int) (f2 * 4.0f);
        this.f6380f = context;
        this.f6381g = 0;
        boolean z9 = s6.c.B() >= 130100;
        this.f6396x = z9;
        int[] iArr = AbstractC0478a.f10554E;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, R.attr.actionButtonStyle, 0);
        this.f6389q = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, 0);
        this.f6390r = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, 0);
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr, R.attr.actionOverflowButtonStyle, 0);
        this.f6391s = typedArrayObtainStyledAttributes2.getDimensionPixelSize(7, 0);
        this.f6392t = typedArrayObtainStyledAttributes2.getDimensionPixelSize(6, 0);
        this.f6394v = typedArrayObtainStyledAttributes2.getDimensionPixelSize(3, 0);
        typedArrayObtainStyledAttributes2.recycle();
        this.f6395w = context.getResources().getString(R.string.sesl_action_menu_overflow_badge_text_n);
        if (z9) {
            this.f6389q = getResources().getDimensionPixelSize(R.dimen.sesl_action_button_side_padding);
            this.f6390r = getResources().getDimensionPixelSize(R.dimen.sesl_action_button_side_padding);
            this.f6391s = getResources().getDimensionPixelSize(R.dimen.sesl_action_bar_overflow_side_padding);
            this.f6392t = getResources().getDimensionPixelSize(R.dimen.sesl_action_bar_overflow_padding_end);
        }
        this.f6393u = getResources().getDimensionPixelSize(R.dimen.sesl_action_bar_last_padding);
    }

    public static C0177p d() {
        C0177p c0177p = new C0177p(-2, -2);
        c0177p.f6828a = false;
        ((LinearLayout.LayoutParams) c0177p).gravity = 16;
        return c0177p;
    }

    public static C0177p e(ViewGroup.LayoutParams layoutParams) {
        C0177p c0177p;
        if (layoutParams == null) {
            return d();
        }
        if (layoutParams instanceof C0177p) {
            C0177p c0177p2 = (C0177p) layoutParams;
            c0177p = new C0177p(c0177p2);
            c0177p.f6828a = c0177p2.f6828a;
        } else {
            c0177p = new C0177p(layoutParams);
        }
        if (((LinearLayout.LayoutParams) c0177p).gravity <= 0) {
            ((LinearLayout.LayoutParams) c0177p).gravity = 16;
        }
        return c0177p;
    }

    @Override // androidx.appcompat.view.menu.i
    public final boolean a(androidx.appcompat.view.menu.l lVar) {
        androidx.appcompat.view.menu.j jVar = this.f6379e;
        if (jVar != null) {
            return jVar.performItemAction(lVar, 0);
        }
        return false;
    }

    @Override // androidx.appcompat.widget.C0, android.view.ViewGroup
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof C0177p;
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    public final boolean f(int i5) {
        boolean zA = false;
        if (i5 == 0) {
            return false;
        }
        KeyEvent.Callback childAt = getChildAt(i5 - 1);
        KeyEvent.Callback childAt2 = getChildAt(i5);
        if (i5 < getChildCount() && (childAt instanceof InterfaceC0174o)) {
            zA = ((InterfaceC0174o) childAt).a();
        }
        return (i5 <= 0 || !(childAt2 instanceof InterfaceC0174o)) ? zA : zA | ((InterfaceC0174o) childAt2).b();
    }

    @Override // androidx.appcompat.widget.C0, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return d();
    }

    @Override // androidx.appcompat.widget.C0, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return e(layoutParams);
    }

    public Menu getMenu() {
        if (this.f6379e == null) {
            Context context = getContext();
            androidx.appcompat.view.menu.j jVar = new androidx.appcompat.view.menu.j(context);
            this.f6379e = jVar;
            jVar.setCallback(new C0180q(this));
            C0171n c0171n = new C0171n(context);
            this.f6382i = c0171n;
            c0171n.h = true;
            c0171n.f6765i = true;
            androidx.appcompat.view.menu.v aVar = this.f6383j;
            if (aVar == null) {
                aVar = new W1.a(9);
            }
            c0171n.setCallback(aVar);
            this.f6379e.addMenuPresenter(this.f6382i, this.f6380f);
            this.f6382i.j(this);
        }
        return this.f6379e;
    }

    public String getOverflowBadgeText() {
        return this.f6395w;
    }

    public Drawable getOverflowIcon() {
        getMenu();
        C0171n c0171n = this.f6382i;
        if (c0171n.f6776u) {
            return null;
        }
        C0162k c0162k = c0171n.f6762e;
        if (c0162k != null) {
            return ((AppCompatImageView) c0162k.f6730g).getDrawable();
        }
        if (c0171n.f6764g) {
            return c0171n.f6763f;
        }
        return null;
    }

    public int getPopupTheme() {
        return this.f6381g;
    }

    public int getSumOfDigitsInBadges() {
        int i5;
        if (this.f6379e == null) {
            return 0;
        }
        int i7 = 0;
        for (int i9 = 0; i9 < this.f6379e.size(); i9++) {
            androidx.appcompat.view.menu.l lVar = (androidx.appcompat.view.menu.l) this.f6379e.getItem(i9);
            if (lVar.isVisible()) {
                String str = lVar.f6268I;
                if (str == null) {
                    i5 = 0;
                } else {
                    try {
                        i5 = Integer.parseInt(str);
                    } catch (NumberFormatException unused) {
                        i5 = 1;
                    }
                }
                i7 += i5;
            }
        }
        return i7;
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // androidx.appcompat.view.menu.y
    public final void initialize(androidx.appcompat.view.menu.j jVar) {
        this.f6379e = jVar;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        C0171n c0171n = this.f6382i;
        if (c0171n != null) {
            c0171n.i();
            this.f6382i.updateMenuView(false);
            if (this.f6382i.isOverflowMenuShowing()) {
                this.f6382i.hideOverflowMenu();
                this.f6382i.k();
            }
        }
        Context context = getContext();
        int[] iArr = AbstractC0478a.f10554E;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, iArr, R.attr.actionButtonStyle, 0);
        this.f6389q = typedArrayObtainStyledAttributes.getDimensionPixelSize(7, 0);
        this.f6390r = typedArrayObtainStyledAttributes.getDimensionPixelSize(6, 0);
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(null, iArr, R.attr.actionOverflowButtonStyle, 0);
        this.f6391s = typedArrayObtainStyledAttributes2.getDimensionPixelSize(7, 0);
        this.f6392t = typedArrayObtainStyledAttributes2.getDimensionPixelSize(6, 0);
        this.f6394v = typedArrayObtainStyledAttributes2.getDimensionPixelSize(3, 0);
        typedArrayObtainStyledAttributes2.recycle();
        if (this.f6396x) {
            this.f6389q = getResources().getDimensionPixelSize(R.dimen.sesl_action_button_side_padding);
            this.f6390r = getResources().getDimensionPixelSize(R.dimen.sesl_action_button_side_padding);
            this.f6391s = getResources().getDimensionPixelSize(R.dimen.sesl_action_bar_overflow_side_padding);
            this.f6392t = getResources().getDimensionPixelSize(R.dimen.sesl_action_bar_overflow_padding_end);
        }
        this.f6393u = getResources().getDimensionPixelSize(R.dimen.sesl_action_bar_last_padding);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        C0171n c0171n = this.f6382i;
        if (c0171n != null) {
            c0171n.hideOverflowMenu();
            C0150g c0150g = c0171n.f6771p;
            if (c0150g != null) {
                c0150g.dismiss();
            }
        }
    }

    @Override // androidx.appcompat.widget.C0, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        int width;
        int paddingLeft;
        if (!this.f6385l) {
            super.onLayout(z9, i5, i7, i9, i10);
            return;
        }
        int childCount = getChildCount();
        int i11 = (i10 - i7) / 2;
        int dividerWidth = getDividerWidth();
        int i12 = i9 - i5;
        int paddingRight = (i12 - getPaddingRight()) - getPaddingLeft();
        boolean zA = h2.a(this);
        int i13 = 0;
        int i14 = 0;
        for (int i15 = 0; i15 < childCount; i15++) {
            View childAt = getChildAt(i15);
            if (childAt.getVisibility() != 8) {
                C0177p c0177p = (C0177p) childAt.getLayoutParams();
                if (c0177p.f6828a) {
                    int measuredWidth = childAt.getMeasuredWidth();
                    if (f(i15)) {
                        measuredWidth += dividerWidth;
                    }
                    int measuredHeight = childAt.getMeasuredHeight();
                    if (zA) {
                        paddingLeft = getPaddingLeft() + ((LinearLayout.LayoutParams) c0177p).leftMargin;
                        width = paddingLeft + measuredWidth;
                    } else {
                        width = (getWidth() - getPaddingRight()) - ((LinearLayout.LayoutParams) c0177p).rightMargin;
                        paddingLeft = width - measuredWidth;
                    }
                    int i16 = i11 - (measuredHeight / 2);
                    childAt.layout(paddingLeft, i16, width, measuredHeight + i16);
                    paddingRight -= measuredWidth;
                    i13 = 1;
                } else {
                    paddingRight -= (childAt.getMeasuredWidth() + ((LinearLayout.LayoutParams) c0177p).leftMargin) + ((LinearLayout.LayoutParams) c0177p).rightMargin;
                    f(i15);
                    i14++;
                }
            }
        }
        if (childCount == 1 && i13 == 0) {
            View childAt2 = getChildAt(0);
            int measuredWidth2 = childAt2.getMeasuredWidth();
            int measuredHeight2 = childAt2.getMeasuredHeight();
            int i17 = (i12 / 2) - (measuredWidth2 / 2);
            int i18 = i11 - (measuredHeight2 / 2);
            childAt2.layout(i17, i18, measuredWidth2 + i17, measuredHeight2 + i18);
            return;
        }
        int i19 = i14 - (i13 ^ 1);
        int iMax = Math.max(0, i19 > 0 ? paddingRight / i19 : 0);
        if (zA) {
            int width2 = getWidth() - getPaddingRight();
            for (int i20 = 0; i20 < childCount; i20++) {
                View childAt3 = getChildAt(i20);
                C0177p c0177p2 = (C0177p) childAt3.getLayoutParams();
                if (childAt3.getVisibility() != 8 && !c0177p2.f6828a) {
                    int i21 = width2 - ((LinearLayout.LayoutParams) c0177p2).rightMargin;
                    int measuredWidth3 = childAt3.getMeasuredWidth();
                    int measuredHeight3 = childAt3.getMeasuredHeight();
                    int i22 = i11 - (measuredHeight3 / 2);
                    childAt3.layout(i21 - measuredWidth3, i22, i21, measuredHeight3 + i22);
                    width2 = i21 - ((measuredWidth3 + ((LinearLayout.LayoutParams) c0177p2).leftMargin) + iMax);
                }
            }
            return;
        }
        int paddingLeft2 = getPaddingLeft();
        for (int i23 = 0; i23 < childCount; i23++) {
            View childAt4 = getChildAt(i23);
            C0177p c0177p3 = (C0177p) childAt4.getLayoutParams();
            if (childAt4.getVisibility() != 8 && !c0177p3.f6828a) {
                int i24 = paddingLeft2 + ((LinearLayout.LayoutParams) c0177p3).leftMargin;
                int measuredWidth4 = childAt4.getMeasuredWidth();
                int measuredHeight4 = childAt4.getMeasuredHeight();
                int i25 = i11 - (measuredHeight4 / 2);
                childAt4.layout(i24, i25, i24 + measuredWidth4, measuredHeight4 + i25);
                paddingLeft2 = measuredWidth4 + ((LinearLayout.LayoutParams) c0177p3).rightMargin + iMax + i24;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r21v0 */
    /* JADX WARN: Type inference failed for: r21v1, types: [int] */
    /* JADX WARN: Type inference failed for: r21v2 */
    /* JADX WARN: Type inference failed for: r2v24 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v28, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r2v31, types: [int] */
    /* JADX WARN: Type inference failed for: r2v33 */
    /* JADX WARN: Type inference failed for: r2v34 */
    @Override // androidx.appcompat.widget.C0, android.view.View
    public final void onMeasure(int i5, int i7) {
        int i9;
        int i10;
        int i11;
        int i12;
        boolean z9;
        int i13;
        int i14;
        int i15;
        int i16;
        ?? r22;
        int i17;
        int i18;
        int i19;
        int i20;
        androidx.appcompat.view.menu.j jVar;
        boolean z10 = this.f6385l;
        boolean z11 = View.MeasureSpec.getMode(i5) == 1073741824;
        this.f6385l = z11;
        if (z10 != z11) {
            this.f6386m = 0;
        }
        int size = View.MeasureSpec.getSize(i5);
        if (this.f6385l && (jVar = this.f6379e) != null && size != this.f6386m) {
            this.f6386m = size;
            jVar.onItemsChanged(true);
        }
        int childCount = getChildCount();
        if (!this.f6385l || childCount <= 0) {
            int i21 = 0;
            while (i21 < childCount) {
                View childAt = getChildAt(i21);
                C0177p c0177p = (C0177p) childAt.getLayoutParams();
                ((LinearLayout.LayoutParams) c0177p).rightMargin = 0;
                ((LinearLayout.LayoutParams) c0177p).leftMargin = 0;
                if (childAt instanceof ActionMenuItemView) {
                    int i22 = this.f6389q;
                    int i23 = this.f6390r;
                    WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
                    childAt.setPaddingRelative(i22, 0, i23, 0);
                    int i24 = childCount - 1;
                    if (i21 == i24) {
                        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) childAt;
                        if (actionMenuItemView.d()) {
                            if (getLayoutDirection() == 0) {
                                ((LinearLayout.LayoutParams) c0177p).rightMargin = this.f6393u;
                                childAt.setLayoutParams(c0177p);
                            } else {
                                ((LinearLayout.LayoutParams) c0177p).leftMargin = this.f6393u;
                                childAt.setLayoutParams(c0177p);
                            }
                            i9 = 1;
                        } else if (this.f6396x) {
                            actionMenuItemView.setIsLastItem(true);
                            childAt.setLayoutParams(c0177p);
                            childAt.setPaddingRelative(this.f6389q, 0, this.f6392t, 0);
                            i9 = 1;
                        } else {
                            actionMenuItemView.setIsLastItem(true);
                            actionMenuItemView.setMinWidth(this.f6394v);
                            childAt.setLayoutParams(c0177p);
                            childAt.setPaddingRelative(this.f6391s, 0, this.f6392t, 0);
                        }
                        i21 += i9;
                    } else if (i21 < i24) {
                        ActionMenuItemView actionMenuItemView2 = (ActionMenuItemView) childAt;
                        if (!actionMenuItemView2.d()) {
                            actionMenuItemView2.setIsLastItem(false);
                        }
                    }
                } else if (c0177p.f6828a) {
                    if (childAt instanceof C0162k) {
                        ViewGroup viewGroup = (ViewGroup) childAt;
                        viewGroup.getChildAt(0).setPaddingRelative(this.f6391s, 0, this.f6392t, 0);
                        viewGroup.getChildAt(0).setMinimumWidth(this.f6394v);
                    } else {
                        int i25 = this.f6391s;
                        int i26 = this.f6392t;
                        WeakHashMap weakHashMap2 = androidx.core.view.W.f7199a;
                        childAt.setPaddingRelative(i25, 0, i26, 0);
                        childAt.setMinimumWidth(this.f6394v);
                    }
                }
                i9 = 1;
                i21 += i9;
            }
            super.onMeasure(i5, i7);
            return;
        }
        int mode = View.MeasureSpec.getMode(i7);
        int size2 = View.MeasureSpec.getSize(i5);
        int size3 = View.MeasureSpec.getSize(i7);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i7, paddingBottom, -2);
        int i27 = size2 - paddingRight;
        int i28 = this.f6387n;
        int i29 = i27 / i28;
        int i30 = i27 % i28;
        if (i29 == 0) {
            setMeasuredDimension(i27, 0);
            return;
        }
        int i31 = (i30 / i29) + i28;
        int childCount2 = getChildCount();
        int i32 = 0;
        int iMax = 0;
        int i33 = 0;
        int i34 = 0;
        int i35 = 0;
        int iMax2 = 0;
        long j5 = 0;
        while (true) {
            i10 = this.o;
            if (i33 >= childCount2) {
                break;
            }
            View childAt2 = getChildAt(i33);
            if (childAt2.getVisibility() == 8) {
                i17 = size3;
                i18 = paddingBottom;
                i20 = 1;
            } else {
                boolean z12 = childAt2 instanceof ActionMenuItemView;
                int i36 = i32 + 1;
                if (z12) {
                    childAt2.setPadding(i10, 0, i10, 0);
                }
                C0177p c0177p2 = (C0177p) childAt2.getLayoutParams();
                c0177p2.f6833f = false;
                c0177p2.f6830c = 0;
                c0177p2.f6829b = 0;
                c0177p2.f6831d = false;
                ((LinearLayout.LayoutParams) c0177p2).leftMargin = 0;
                ((LinearLayout.LayoutParams) c0177p2).rightMargin = 0;
                c0177p2.f6832e = z12 && ((ActionMenuItemView) childAt2).d();
                int i37 = c0177p2.f6828a ? 1 : i29;
                i17 = size3;
                C0177p c0177p3 = (C0177p) childAt2.getLayoutParams();
                i18 = paddingBottom;
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(childMeasureSpec) - paddingBottom, View.MeasureSpec.getMode(childMeasureSpec));
                ActionMenuItemView actionMenuItemView3 = z12 ? (ActionMenuItemView) childAt2 : null;
                boolean z13 = actionMenuItemView3 != null && actionMenuItemView3.d();
                if (i37 <= 0 || (z13 && i37 < 2)) {
                    i19 = 0;
                } else {
                    childAt2.measure(View.MeasureSpec.makeMeasureSpec(i37 * i31, Integer.MIN_VALUE), iMakeMeasureSpec);
                    int measuredWidth = childAt2.getMeasuredWidth();
                    i19 = measuredWidth / i31;
                    if (measuredWidth % i31 != 0) {
                        i19++;
                    }
                    if (z13 && i19 < 2) {
                        i19 = 2;
                    }
                }
                c0177p3.f6831d = !c0177p3.f6828a && z13;
                c0177p3.f6829b = i19;
                childAt2.measure(View.MeasureSpec.makeMeasureSpec(i19 * i31, 1073741824), iMakeMeasureSpec);
                iMax = Math.max(iMax, i19);
                if (c0177p2.f6831d) {
                    i20 = 1;
                    i35++;
                } else {
                    i20 = 1;
                }
                if (c0177p2.f6828a) {
                    i34 = i20;
                }
                i29 -= i19;
                iMax2 = Math.max(iMax2, childAt2.getMeasuredHeight());
                if (i19 == i20) {
                    j5 |= (long) (i20 << i33);
                }
                i32 = i36;
            }
            i33 += i20;
            paddingBottom = i18;
            size3 = i17;
        }
        int i38 = size3;
        int i39 = iMax2;
        boolean z14 = i34 != 0 && i32 == 2;
        boolean z15 = false;
        while (i35 > 0 && i29 > 0) {
            int i40 = Integer.MAX_VALUE;
            int i41 = 0;
            int i42 = 0;
            long j9 = 0;
            boolean z16 = z15;
            while (i41 < childCount2) {
                boolean z17 = z16;
                C0177p c0177p4 = (C0177p) getChildAt(i41).getLayoutParams();
                int i43 = i39;
                if (c0177p4.f6831d) {
                    int i44 = c0177p4.f6829b;
                    if (i44 < i40) {
                        j9 = 1 << i41;
                        i40 = i44;
                        i42 = 1;
                    } else if (i44 == i40) {
                        i42++;
                        j9 |= 1 << i41;
                    }
                }
                i41++;
                i39 = i43;
                z16 = z17 ? 1 : 0;
            }
            z9 = z16;
            i13 = i39;
            ?? r21 = 1;
            j5 |= j9;
            if (i42 > i29) {
                i11 = mode;
                i12 = i27;
                i14 = 1;
                break;
            }
            int i45 = i40 + 1;
            int i46 = 0;
            while (i46 < childCount2) {
                View childAt3 = getChildAt(i46);
                C0177p c0177p5 = (C0177p) childAt3.getLayoutParams();
                int i47 = mode;
                int i48 = i27;
                long j10 = r21 << i46;
                if ((j9 & j10) == 0) {
                    if (c0177p5.f6829b == i45) {
                        j5 |= j10;
                    }
                    r22 = 1;
                } else {
                    if (z14 && c0177p5.f6832e) {
                        r22 = 1;
                        r22 = 1;
                        if (i29 == 1) {
                            childAt3.setPadding(i10 + i31, 0, i10, 0);
                        }
                    } else {
                        r22 = 1;
                    }
                    c0177p5.f6829b += r22 == true ? 1 : 0;
                    c0177p5.f6833f = r22;
                    i29--;
                }
                i46 += r22;
                r21 = r22;
                mode = i47;
                i27 = i48;
            }
            z15 = r21 == true ? 1 : 0;
            i39 = i13;
            mode = mode;
        }
        i11 = mode;
        i12 = i27;
        z9 = z15 ? 1 : 0;
        i13 = i39;
        i14 = 1;
        int i49 = (i34 == 0 && i32 == i14) ? i14 : 0;
        if (i29 > 0 && j5 != 0 && (i29 < i32 - i14 || i49 != 0 || iMax > i14)) {
            float fBitCount = Long.bitCount(j5);
            if (i49 == 0) {
                if ((j5 & 1) != 0 && !((C0177p) getChildAt(0).getLayoutParams()).f6832e) {
                    fBitCount -= 0.5f;
                }
                int i50 = childCount2 - 1;
                if ((j5 & ((long) (1 << i50))) != 0 && !((C0177p) getChildAt(i50).getLayoutParams()).f6832e) {
                    fBitCount -= 0.5f;
                }
            }
            int i51 = fBitCount > 0.0f ? (int) ((i29 * i31) / fBitCount) : 0;
            int i52 = 0;
            z9 = z9;
            while (i52 < childCount2) {
                if ((j5 & ((long) (1 << i52))) == 0) {
                    i16 = 1;
                } else {
                    View childAt4 = getChildAt(i52);
                    C0177p c0177p6 = (C0177p) childAt4.getLayoutParams();
                    if (childAt4 instanceof ActionMenuItemView) {
                        c0177p6.f6830c = i51;
                        c0177p6.f6833f = true;
                        if (i52 == 0 && !c0177p6.f6832e) {
                            ((LinearLayout.LayoutParams) c0177p6).leftMargin = (-i51) / 2;
                        }
                        i16 = 1;
                        z9 = true;
                    } else if (c0177p6.f6828a) {
                        c0177p6.f6830c = i51;
                        i16 = 1;
                        c0177p6.f6833f = true;
                        ((LinearLayout.LayoutParams) c0177p6).rightMargin = (-i51) / 2;
                        z9 = true;
                    } else {
                        i16 = 1;
                        if (i52 != 0) {
                            ((LinearLayout.LayoutParams) c0177p6).leftMargin = i51 / 2;
                        }
                        if (i52 != childCount2 - 1) {
                            ((LinearLayout.LayoutParams) c0177p6).rightMargin = i51 / 2;
                        }
                    }
                }
                i52 += i16;
                z9 = z9;
            }
        }
        if (z9) {
            int i53 = 0;
            while (i53 < childCount2) {
                View childAt5 = getChildAt(i53);
                C0177p c0177p7 = (C0177p) childAt5.getLayoutParams();
                if (c0177p7.f6833f) {
                    childAt5.measure(View.MeasureSpec.makeMeasureSpec((c0177p7.f6829b * i31) + c0177p7.f6830c, 1073741824), childMeasureSpec);
                    i15 = 1;
                } else {
                    i15 = 1;
                }
                i53 += i15;
            }
        }
        setMeasuredDimension(i12, i11 != 1073741824 ? i13 : i38);
    }

    public void setExpandedActionViewsExclusive(boolean z9) {
        this.f6382i.f6769m = z9;
    }

    public void setOnMenuItemClickListener(r rVar) {
        this.f6388p = rVar;
    }

    public void setOverflowIcon(Drawable drawable) {
        getMenu();
        C0171n c0171n = this.f6382i;
        if (c0171n.f6776u) {
            return;
        }
        C0162k c0162k = c0171n.f6762e;
        if (c0162k != null) {
            ((AppCompatImageView) c0162k.f6730g).setImageDrawable(drawable);
        } else {
            c0171n.f6764g = true;
            c0171n.f6763f = drawable;
        }
    }

    public void setOverflowReserved(boolean z9) {
        this.h = z9;
    }

    public void setPopupTheme(int i5) {
        if (this.f6381g != i5) {
            this.f6381g = i5;
            if (i5 == 0) {
                this.f6380f = getContext();
            } else {
                this.f6380f = new ContextThemeWrapper(getContext(), i5);
            }
        }
    }

    public void setPresenter(C0171n c0171n) {
        this.f6382i = c0171n;
        c0171n.j(this);
    }

    @Override // androidx.appcompat.widget.C0, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ B0 generateDefaultLayoutParams() {
        return d();
    }

    @Override // androidx.appcompat.widget.C0, android.view.ViewGroup
    public final /* bridge */ /* synthetic */ B0 generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return e(layoutParams);
    }

    @Override // androidx.appcompat.widget.C0, android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C0177p(getContext(), attributeSet);
    }

    @Override // androidx.appcompat.widget.C0, android.view.ViewGroup
    public final B0 generateLayoutParams(AttributeSet attributeSet) {
        return new C0177p(getContext(), attributeSet);
    }
}
