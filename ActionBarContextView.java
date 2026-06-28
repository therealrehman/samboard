package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.samsung.android.keyscafe.R;
import e.AbstractC0478a;
import java.util.WeakHashMap;
import l.AbstractC0660b;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContextView extends ViewGroup {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final I3.h f6333e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Context f6334f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ActionMenuView f6335g;
    public C0171n h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f6336i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public androidx.core.view.e0 f6337j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f6338k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f6339l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public CharSequence f6340m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public CharSequence f6341n;
    public View o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public View f6342p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public View f6343q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public LinearLayout f6344r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public TextView f6345s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public TextView f6346t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f6347u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f6348v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f6349w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final int f6350x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f6351y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f6352z;

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        int resourceId;
        super(context, attributeSet, R.attr.actionModeStyle);
        this.f6333e = new I3.h(this);
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true) || typedValue.resourceId == 0) {
            this.f6334f = context;
        } else {
            this.f6334f = new ContextThemeWrapper(context, typedValue.resourceId);
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0478a.f10559d, R.attr.actionModeStyle, 0);
        Drawable drawable = (!typedArrayObtainStyledAttributes.hasValue(0) || (resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0)) == 0) ? typedArrayObtainStyledAttributes.getDrawable(0) : android.support.v4.media.session.f.y(context, resourceId);
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        setBackground(drawable);
        this.f6347u = typedArrayObtainStyledAttributes.getResourceId(5, 0);
        this.f6348v = typedArrayObtainStyledAttributes.getResourceId(4, 0);
        this.f6336i = typedArrayObtainStyledAttributes.getLayoutDimension(3, 0);
        this.f6350x = typedArrayObtainStyledAttributes.getResourceId(2, R.layout.sesl_action_mode_close_item);
        typedArrayObtainStyledAttributes.recycle();
    }

    public static int f(View view, int i5, int i7) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE), i7);
        return Math.max(0, i5 - view.getMeasuredWidth());
    }

    public static int h(View view, boolean z9, int i5, int i7, int i9) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i10 = ((i9 - measuredHeight) / 2) + i7;
        if (z9) {
            view.layout(i5 - measuredWidth, i10, i5, measuredHeight + i10);
        } else {
            view.layout(i5, i10, i5 + measuredWidth, measuredHeight + i10);
        }
        return z9 ? -measuredWidth : measuredWidth;
    }

    public final void c(AbstractC0660b abstractC0660b) {
        this.f6352z = true;
        View view = this.o;
        if (view == null) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(this.f6350x, (ViewGroup) this, false);
            this.o = viewInflate;
            addView(viewInflate);
        } else if (view.getParent() == null) {
            addView(this.o);
        }
        View viewFindViewById = this.o.findViewById(R.id.action_mode_close_button);
        this.f6342p = viewFindViewById;
        viewFindViewById.setOnClickListener(new ViewOnClickListenerC0135b(0, abstractC0660b));
        androidx.appcompat.view.menu.j jVarC = abstractC0660b.c();
        C0171n c0171n = this.h;
        if (c0171n != null) {
            c0171n.hideOverflowMenu();
            C0150g c0150g = c0171n.f6771p;
            if (c0150g != null) {
                c0150g.dismiss();
            }
        }
        C0171n c0171n2 = new C0171n(getContext());
        this.h = c0171n2;
        c0171n2.h = true;
        c0171n2.f6765i = true;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        jVarC.addMenuPresenter(this.h, this.f6334f);
        ActionMenuView actionMenuView = (ActionMenuView) this.h.getMenuView(this);
        this.f6335g = actionMenuView;
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        actionMenuView.setBackground(null);
        addView(this.f6335g, layoutParams);
    }

    public final void d() {
        if (this.f6344r == null) {
            LayoutInflater.from(getContext()).inflate(R.layout.sesl_action_bar_title_item, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.f6344r = linearLayout;
            this.f6345s = (TextView) linearLayout.findViewById(R.id.action_bar_title);
            this.f6346t = (TextView) this.f6344r.findViewById(R.id.action_bar_subtitle);
            int i5 = this.f6347u;
            if (i5 != 0) {
                this.f6345s.setTextAppearance(getContext(), i5);
            }
            int i7 = this.f6348v;
            if (i7 != 0) {
                this.f6346t.setTextAppearance(getContext(), i7);
            }
        }
        this.f6345s.setText(this.f6340m);
        this.f6346t.setText(this.f6341n);
        boolean z9 = !TextUtils.isEmpty(this.f6340m);
        boolean z10 = !TextUtils.isEmpty(this.f6341n);
        this.f6346t.setVisibility(z10 ? 0 : 8);
        this.f6344r.setVisibility((z9 || z10) ? 0 : 8);
        if (this.f6344r.getParent() == null) {
            addView(this.f6344r);
        }
    }

    public final void e() {
        removeAllViews();
        this.f6343q = null;
        this.f6335g = null;
        this.h = null;
        View view = this.f6342p;
        if (view != null) {
            view.setOnClickListener(null);
        }
    }

    public final void g(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, AbstractC0478a.f10556a, R.attr.actionBarStyle, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(13, 0));
        typedArrayObtainStyledAttributes.recycle();
        C0171n c0171n = this.h;
        if (c0171n != null) {
            c0171n.i();
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    public int getAnimatedVisibility() {
        return this.f6337j != null ? this.f6333e.f1331f : getVisibility();
    }

    public int getContentHeight() {
        return this.f6336i;
    }

    public boolean getIsActionModeAccessibilityOn() {
        return this.f6351y;
    }

    public CharSequence getSubtitle() {
        return this.f6341n;
    }

    public CharSequence getTitle() {
        return this.f6340m;
    }

    @Override // android.view.View
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public final void setVisibility(int i5) {
        if (i5 != getVisibility()) {
            androidx.core.view.e0 e0Var = this.f6337j;
            if (e0Var != null) {
                e0Var.b();
            }
            super.setVisibility(i5);
        }
    }

    public final androidx.core.view.e0 j(long j5, int i5) {
        androidx.core.view.e0 e0Var = this.f6337j;
        if (e0Var != null) {
            e0Var.b();
        }
        I3.h hVar = this.f6333e;
        if (i5 != 0) {
            androidx.core.view.e0 e0VarA = androidx.core.view.W.a(this);
            e0VarA.a(0.0f);
            e0VarA.c(j5);
            ((ActionBarContextView) hVar.h).f6337j = e0VarA;
            hVar.f1331f = i5;
            e0VarA.d(hVar);
            return e0VarA;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        androidx.core.view.e0 e0VarA2 = androidx.core.view.W.a(this);
        e0VarA2.a(1.0f);
        e0VarA2.c(j5);
        ((ActionBarContextView) hVar.h).f6337j = e0VarA2;
        hVar.f1331f = i5;
        e0VarA2.d(hVar);
        return e0VarA2;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sesl_action_bar_top_padding);
        setPadding(0, dimensionPixelSize, 0, 0);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, AbstractC0478a.f10559d, android.R.attr.actionModeStyle, 0);
        int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, -1);
        typedArrayObtainStyledAttributes.recycle();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = dimensionPixelSize2 + dimensionPixelSize;
        setLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        g(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, AbstractC0478a.f10559d, android.R.attr.actionModeStyle, 0);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(3, -1);
        if (dimensionPixelSize >= 0) {
            setContentHeight(dimensionPixelSize);
        }
        setPadding(0, getResources().getDimensionPixelSize(R.dimen.sesl_action_bar_top_padding), 0, 0);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        C0171n c0171n = this.h;
        if (c0171n != null) {
            c0171n.hideOverflowMenu();
            C0150g c0150g = this.h.f6771p;
            if (c0150g != null) {
                c0150g.dismiss();
            }
        }
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.f6339l = false;
        }
        if (!this.f6339l) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.f6339l = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.f6339l = false;
        }
        return true;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() != 32) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            return;
        }
        Log.d("ActionBarContextView", "onInitializeAccessibilityEvent Check ActionMode :" + this.f6352z);
        if (this.f6352z) {
            this.f6351y = true;
            this.f6352z = false;
        } else {
            this.f6351y = false;
        }
        Log.d("ActionBarContextView", "onInitializeAccessibilityEvent mIsActionModeAccessibilityOn :" + this.f6351y);
        accessibilityEvent.setSource(this);
        accessibilityEvent.setClassName(getClass().getName());
        accessibilityEvent.setPackageName(getContext().getPackageName());
        accessibilityEvent.setContentDescription(this.f6340m);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        boolean zA = h2.a(this);
        int paddingRight = zA ? (i9 - i5) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i10 - i7) - getPaddingTop()) - getPaddingBottom();
        View view = this.o;
        if (view != null && view.getVisibility() != 8) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.o.getLayoutParams();
            int i11 = zA ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i12 = zA ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int i13 = zA ? paddingRight - i11 : paddingRight + i11;
            int iH = h(this.o, zA, i13, paddingTop, paddingTop2) + i13;
            paddingRight = zA ? iH - i12 : iH + i12;
        }
        LinearLayout linearLayout = this.f6344r;
        if (linearLayout != null && this.f6343q == null && linearLayout.getVisibility() != 8) {
            paddingRight += h(this.f6344r, zA, paddingRight, paddingTop, paddingTop2);
        }
        View view2 = this.f6343q;
        if (view2 != null) {
            h(view2, zA, paddingRight, paddingTop, paddingTop2);
        }
        int paddingLeft = zA ? getPaddingLeft() : (i9 - i5) - getPaddingRight();
        ActionMenuView actionMenuView = this.f6335g;
        if (actionMenuView != null) {
            h(actionMenuView, !zA, paddingLeft, paddingTop, paddingTop2);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i5, int i7) {
        if (View.MeasureSpec.getMode(i5) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with android:layout_width=\"match_parent\" (or fill_parent)"));
        }
        if (View.MeasureSpec.getMode(i7) == 0) {
            throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with android:layout_height=\"wrap_content\""));
        }
        int size = View.MeasureSpec.getSize(i5);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sesl_action_bar_top_padding);
        int i9 = this.f6336i;
        int size2 = i9 > 0 ? i9 + dimensionPixelSize : View.MeasureSpec.getSize(i7);
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int iMin = size2 - paddingBottom;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMin, Integer.MIN_VALUE);
        View view = this.o;
        if (view != null && view.getVisibility() == 0) {
            int iF = f(this.o, paddingLeft, iMakeMeasureSpec);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.o.getLayoutParams();
            paddingLeft = iF - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
        }
        ActionMenuView actionMenuView = this.f6335g;
        if (actionMenuView != null && actionMenuView.getParent() == this) {
            paddingLeft = f(this.f6335g, paddingLeft, iMakeMeasureSpec);
        }
        if (this.f6344r != null && this.f6343q == null) {
            Context context = getContext();
            if (this.f6345s != null) {
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(this.f6347u, AbstractC0478a.f10552C);
                TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(0);
                typedArrayObtainStyledAttributes.recycle();
                float fComplexToFloat = TypedValue.complexToFloat(typedValuePeekValue.data);
                if (TextUtils.isEmpty(this.f6341n)) {
                    this.f6345s.setTextSize(1, fComplexToFloat * Math.min(context.getResources().getConfiguration().fontScale, 1.2f));
                } else {
                    this.f6345s.setTextSize(1, fComplexToFloat);
                }
            }
            View view2 = this.o;
            if (view2 == null || view2.getVisibility() == 8) {
                int dimension = (int) context.getResources().getDimension(R.dimen.sesl_toolbar_content_inset);
                WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
                boolean z9 = getLayoutDirection() == 0;
                TextView textView = this.f6345s;
                if (textView != null && textView.getVisibility() == 0) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f6345s.getLayoutParams();
                    if (z9) {
                        layoutParams.leftMargin = dimension;
                    } else {
                        layoutParams.rightMargin = dimension;
                    }
                    this.f6345s.setLayoutParams(layoutParams);
                }
                TextView textView2 = this.f6346t;
                if (textView2 != null && textView2.getVisibility() == 0) {
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f6346t.getLayoutParams();
                    if (z9) {
                        layoutParams2.leftMargin = dimension;
                    } else {
                        layoutParams2.rightMargin = dimension;
                    }
                    this.f6346t.setLayoutParams(layoutParams2);
                }
            }
            if (this.f6349w) {
                this.f6344r.measure(View.MeasureSpec.makeMeasureSpec(0, 0), iMakeMeasureSpec);
                int measuredWidth = this.f6344r.getMeasuredWidth();
                boolean z10 = measuredWidth <= paddingLeft;
                if (z10) {
                    paddingLeft -= measuredWidth;
                }
                this.f6344r.setVisibility(z10 ? 0 : 8);
            } else {
                paddingLeft = f(this.f6344r, paddingLeft, iMakeMeasureSpec);
            }
        }
        View view3 = this.f6343q;
        if (view3 != null) {
            ViewGroup.LayoutParams layoutParams3 = view3.getLayoutParams();
            int i10 = layoutParams3.width;
            int i11 = i10 != -2 ? 1073741824 : Integer.MIN_VALUE;
            if (i10 >= 0) {
                paddingLeft = Math.min(i10, paddingLeft);
            }
            int i12 = layoutParams3.height;
            int i13 = i12 == -2 ? Integer.MIN_VALUE : 1073741824;
            if (i12 >= 0) {
                iMin = Math.min(i12, iMin);
            }
            this.f6343q.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i11), View.MeasureSpec.makeMeasureSpec(iMin, i13));
        }
        if (this.f6336i > 0) {
            setMeasuredDimension(size, size2);
            return;
        }
        int childCount = getChildCount();
        int i14 = 0;
        for (int i15 = 0; i15 < childCount; i15++) {
            int measuredHeight = getChildAt(i15).getMeasuredHeight() + paddingBottom;
            if (measuredHeight > i14) {
                i14 = measuredHeight;
            }
        }
        setMeasuredDimension(size, i14);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f6338k = false;
        }
        if (!this.f6338k) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.f6338k = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.f6338k = false;
        }
        return true;
    }

    public void setContentHeight(int i5) {
        this.f6336i = i5;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.f6343q;
        if (view2 != null) {
            removeView(view2);
        }
        this.f6343q = view;
        if (view != null && (linearLayout = this.f6344r) != null) {
            removeView(linearLayout);
            this.f6344r = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.f6341n = charSequence;
        d();
    }

    public void setTitle(CharSequence charSequence) {
        this.f6340m = charSequence;
        d();
        androidx.core.view.W.j(this, charSequence);
    }

    public void setTitleOptional(boolean z9) {
        if (z9 != this.f6349w) {
            requestLayout();
        }
        this.f6349w = z9;
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }
}
