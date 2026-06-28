package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.core.view.C0219k;
import androidx.core.view.C0233z;
import androidx.core.view.InterfaceC0215g;
import androidx.core.view.InterfaceC0221m;
import androidx.lifecycle.EnumC0271m;
import androidx.lifecycle.InterfaceC0276s;
import com.samsung.android.keyscafe.R;
import e.AbstractC0478a;
import f6.AbstractC0527a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.WeakHashMap;
import l.C0668j;

/* JADX INFO: loaded from: classes.dex */
public class Toolbar extends ViewGroup implements InterfaceC0215g {
    private static final float MAX_FONT_SCALE = 1.2f;
    private static final int SESL_TOP_INSET_TO_EXPAND = 100;
    private static final String TAG = "Toolbar";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final /* synthetic */ int f6638e = 0;
    private androidx.appcompat.view.menu.v mActionMenuPresenterCallback;
    private OnBackInvokedCallback mBackInvokedCallback;
    private boolean mBackInvokedCallbackEnabled;
    private OnBackInvokedDispatcher mBackInvokedDispatcher;
    private Drawable mBackground;
    int mButtonGravity;
    ImageButton mCollapseButtonView;
    private CharSequence mCollapseDescription;
    private Drawable mCollapseIcon;
    private boolean mCollapsible;
    private int mContentInsetEndWithActions;
    private int mContentInsetStartWithNavigation;
    private R0 mContentInsets;
    private boolean mEatingHover;
    private boolean mEatingTouch;
    View mExpandedActionView;
    private W1 mExpandedMenuPresenter;
    private int mGravity;
    private final ArrayList<View> mHiddenViews;
    private ImageView mLogoView;
    private int mMaxButtonHeight;
    androidx.appcompat.view.menu.h mMenuBuilderCallback;
    final C0219k mMenuHostHelper;
    ActionMenuView mMenuView;
    private final r mMenuViewItemClickListener;
    private Drawable mNavButtonIconDrawable;
    private ImageButton mNavButtonView;
    private CharSequence mNavTooltipText;
    private ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListenerForTD;
    Y1 mOnMenuItemClickListener;
    private C0171n mOuterActionMenuPresenter;
    private Context mPopupContext;
    private int mPopupTheme;
    private ArrayList<MenuItem> mProvidedMenuItems;
    private final Runnable mShowOverflowMenuRunnable;
    private CharSequence mSubtitleText;
    private int mSubtitleTextAppearance;
    private ColorStateList mSubtitleTextColor;
    private TextView mSubtitleTextView;
    private final int[] mTempMargins;
    private final ArrayList<View> mTempViews;
    private int mTitleMarginBottom;
    private int mTitleMarginEnd;
    private int mTitleMarginStart;
    private int mTitleMarginTop;
    private CharSequence mTitleText;
    private int mTitleTextAppearance;
    private ColorStateList mTitleTextColor;
    private TextView mTitleTextView;
    private int mUserTopPadding;
    private c2 mWrapper;

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.toolbarStyle);
    }

    public static /* synthetic */ void a(Toolbar toolbar, ViewGroup viewGroup) {
        boolean z9;
        View childAt;
        toolbar.getClass();
        androidx.core.view.B b3 = new androidx.core.view.B(viewGroup);
        if (toolbar.q(toolbar.mNavButtonView)) {
            b3.a(toolbar.mNavButtonView, C0233z.a(0, toolbar.mNavButtonView.getTop(), 0, viewGroup.getHeight() - toolbar.mNavButtonView.getBottom()));
            z9 = true;
        } else {
            z9 = false;
        }
        int childCount = viewGroup.getChildCount();
        int i5 = 0;
        while (true) {
            if (i5 >= childCount) {
                childAt = null;
                break;
            }
            childAt = viewGroup.getChildAt(i5);
            if (childAt instanceof ActionMenuView) {
                break;
            } else {
                i5++;
            }
        }
        if (childAt != null && childAt.getVisibility() == 0) {
            ViewGroup viewGroup2 = (ViewGroup) childAt;
            int childCount2 = viewGroup2.getChildCount();
            int i7 = 0;
            while (i7 < childCount2) {
                View childAt2 = viewGroup2.getChildAt(i7);
                if (childAt2.getVisibility() == 0) {
                    int measuredWidth = childAt2.getMeasuredWidth() / 2;
                    b3.a(childAt2, C0233z.a(i7 == 0 ? measuredWidth : 0, measuredWidth, 0, measuredWidth));
                    z9 = true;
                }
                i7++;
            }
        }
        if (z9) {
            viewGroup.setTouchDelegate(b3);
        }
    }

    private ArrayList<MenuItem> getCurrentMenuItems() {
        ArrayList<MenuItem> arrayList = new ArrayList<>();
        Menu menu = getMenu();
        for (int i5 = 0; i5 < menu.size(); i5++) {
            arrayList.add(menu.getItem(i5));
        }
        return arrayList;
    }

    private MenuInflater getMenuInflater() {
        return new C0668j(getContext());
    }

    public static int j(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.getMarginEnd() + marginLayoutParams.getMarginStart();
    }

    public static int k(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public void addChildrenForExpandedActionView() {
        for (int size = this.mHiddenViews.size() - 1; size >= 0; size--) {
            addView(this.mHiddenViews.get(size));
        }
        this.mHiddenViews.clear();
    }

    @Override // androidx.core.view.InterfaceC0215g
    public void addMenuProvider(InterfaceC0221m interfaceC0221m) {
        C0219k c0219k = this.mMenuHostHelper;
        c0219k.f7236b.add(interfaceC0221m);
        c0219k.f7235a.run();
    }

    public final void b(int i5, ArrayList arrayList) {
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        boolean z9 = getLayoutDirection() == 1;
        int childCount = getChildCount();
        int absoluteGravity = Gravity.getAbsoluteGravity(i5, getLayoutDirection());
        arrayList.clear();
        if (!z9) {
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = getChildAt(i7);
                X1 x12 = (X1) childAt.getLayoutParams();
                if (x12.f6660b == 0 && q(childAt) && h(x12.f6659a) == absoluteGravity) {
                    arrayList.add(childAt);
                }
            }
            return;
        }
        for (int i9 = childCount - 1; i9 >= 0; i9--) {
            View childAt2 = getChildAt(i9);
            X1 x13 = (X1) childAt2.getLayoutParams();
            if (x13.f6660b == 0 && q(childAt2) && h(x13.f6659a) == absoluteGravity) {
                arrayList.add(childAt2);
            }
        }
    }

    public final void c(View view, boolean z9) {
        if (view != null) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            X1 x1GenerateDefaultLayoutParams = layoutParams == null ? generateDefaultLayoutParams() : !checkLayoutParams(layoutParams) ? generateLayoutParams(layoutParams) : (X1) layoutParams;
            x1GenerateDefaultLayoutParams.f6660b = 1;
            if (z9 && this.mExpandedActionView != null) {
                view.setLayoutParams(x1GenerateDefaultLayoutParams);
                this.mHiddenViews.add(view);
            } else if (view.getParent() == null) {
                addView(view, x1GenerateDefaultLayoutParams);
            }
        }
    }

    public boolean canShowOverflowMenu() {
        ActionMenuView actionMenuView;
        return getVisibility() == 0 && (actionMenuView = this.mMenuView) != null && actionMenuView.h;
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof X1);
    }

    public void collapseActionView() {
        W1 w12 = this.mExpandedMenuPresenter;
        androidx.appcompat.view.menu.l lVar = w12 == null ? null : w12.f6655f;
        if (lVar != null) {
            lVar.collapseActionView();
        }
    }

    public final void d() {
        if (this.mContentInsets == null) {
            R0 r02 = new R0();
            r02.f6511a = 0;
            r02.f6512b = 0;
            r02.f6513c = Integer.MIN_VALUE;
            r02.f6514d = Integer.MIN_VALUE;
            r02.f6515e = 0;
            r02.f6516f = 0;
            r02.f6517g = false;
            r02.h = false;
            this.mContentInsets = r02;
        }
    }

    public void dismissPopupMenus() {
        C0171n c0171n;
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView == null || (c0171n = actionMenuView.f6382i) == null) {
            return;
        }
        c0171n.hideOverflowMenu();
        C0150g c0150g = c0171n.f6771p;
        if (c0150g != null) {
            c0150g.dismiss();
        }
    }

    @Override // android.view.View
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        motionEvent.getAction();
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    public final void e() {
        f();
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView.f6379e == null) {
            androidx.appcompat.view.menu.j jVar = (androidx.appcompat.view.menu.j) actionMenuView.getMenu();
            if (this.mExpandedMenuPresenter == null) {
                this.mExpandedMenuPresenter = new W1(this);
            }
            this.mMenuView.setExpandedActionViewsExclusive(true);
            jVar.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
            updateBackInvokedCallbackState();
        }
    }

    public void ensureCollapseButtonView() {
        if (this.mCollapseButtonView == null) {
            E e3 = new E(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            this.mCollapseButtonView = e3;
            e3.setImageDrawable(this.mCollapseIcon);
            this.mCollapseButtonView.setContentDescription(this.mCollapseDescription);
            X1 x1GenerateDefaultLayoutParams = generateDefaultLayoutParams();
            x1GenerateDefaultLayoutParams.f6659a = (this.mButtonGravity & 112) | 8388611;
            x1GenerateDefaultLayoutParams.f6660b = 2;
            this.mCollapseButtonView.setLayoutParams(x1GenerateDefaultLayoutParams);
            this.mCollapseButtonView.setOnClickListener(new ViewOnClickListenerC0135b(2, this));
            com.bumptech.glide.d.C(AbstractC0527a.r(), this.mCollapseButtonView);
            if (TextUtils.isEmpty(this.mCollapseDescription)) {
                return;
            }
            d2.a(this.mCollapseButtonView, this.mCollapseDescription);
        }
    }

    public final void f() {
        if (this.mMenuView == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext(), null);
            this.mMenuView = actionMenuView;
            actionMenuView.setPopupTheme(this.mPopupTheme);
            this.mMenuView.setOnMenuItemClickListener(this.mMenuViewItemClickListener);
            ActionMenuView actionMenuView2 = this.mMenuView;
            androidx.appcompat.view.menu.v vVar = this.mActionMenuPresenterCallback;
            U1 u12 = new U1(this);
            actionMenuView2.f6383j = vVar;
            actionMenuView2.f6384k = u12;
            X1 x1GenerateDefaultLayoutParams = generateDefaultLayoutParams();
            x1GenerateDefaultLayoutParams.f6659a = (this.mButtonGravity & 112) | 8388613;
            this.mMenuView.setLayoutParams(x1GenerateDefaultLayoutParams);
            c(this.mMenuView, false);
        }
    }

    public final void g() {
        if (this.mNavButtonView == null) {
            this.mNavButtonView = new E(getContext(), null, R.attr.toolbarNavigationButtonStyle);
            X1 x1GenerateDefaultLayoutParams = generateDefaultLayoutParams();
            x1GenerateDefaultLayoutParams.f6659a = (this.mButtonGravity & 112) | 8388611;
            this.mNavButtonView.setLayoutParams(x1GenerateDefaultLayoutParams);
            com.bumptech.glide.d.C(AbstractC0527a.r(), this.mNavButtonView);
            if (TextUtils.isEmpty(this.mNavTooltipText)) {
                return;
            }
            d2.a(this.mNavButtonView, this.mNavTooltipText);
        }
    }

    public CharSequence getCollapseContentDescription() {
        ImageButton imageButton = this.mCollapseButtonView;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getCollapseIcon() {
        ImageButton imageButton = this.mCollapseButtonView;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public int getContentInsetEnd() {
        R0 r02 = this.mContentInsets;
        if (r02 != null) {
            return r02.f6517g ? r02.f6511a : r02.f6512b;
        }
        return 0;
    }

    public int getContentInsetEndWithActions() {
        int i5 = this.mContentInsetEndWithActions;
        return i5 != Integer.MIN_VALUE ? i5 : getContentInsetEnd();
    }

    public int getContentInsetLeft() {
        R0 r02 = this.mContentInsets;
        if (r02 != null) {
            return r02.f6511a;
        }
        return 0;
    }

    public int getContentInsetRight() {
        R0 r02 = this.mContentInsets;
        if (r02 != null) {
            return r02.f6512b;
        }
        return 0;
    }

    public int getContentInsetStart() {
        R0 r02 = this.mContentInsets;
        if (r02 != null) {
            return r02.f6517g ? r02.f6512b : r02.f6511a;
        }
        return 0;
    }

    public int getContentInsetStartWithNavigation() {
        int i5 = this.mContentInsetStartWithNavigation;
        return i5 != Integer.MIN_VALUE ? i5 : getContentInsetStart();
    }

    public int getCurrentContentInsetEnd() {
        androidx.appcompat.view.menu.j jVar;
        ActionMenuView actionMenuView = this.mMenuView;
        return (actionMenuView == null || (jVar = actionMenuView.f6379e) == null || !jVar.hasVisibleItems()) ? getContentInsetEnd() : Math.max(getContentInsetEnd(), Math.max(this.mContentInsetEndWithActions, 0));
    }

    public int getCurrentContentInsetLeft() {
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        return getLayoutDirection() == 1 ? getCurrentContentInsetEnd() : getCurrentContentInsetStart();
    }

    public int getCurrentContentInsetRight() {
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        return getLayoutDirection() == 1 ? getCurrentContentInsetStart() : getCurrentContentInsetEnd();
    }

    public int getCurrentContentInsetStart() {
        return getNavigationIcon() != null ? Math.max(getContentInsetStart(), Math.max(this.mContentInsetStartWithNavigation, 0)) : getContentInsetStart();
    }

    public Drawable getLogo() {
        ImageView imageView = this.mLogoView;
        if (imageView != null) {
            return imageView.getDrawable();
        }
        return null;
    }

    public CharSequence getLogoDescription() {
        ImageView imageView = this.mLogoView;
        if (imageView != null) {
            return imageView.getContentDescription();
        }
        return null;
    }

    public Menu getMenu() {
        e();
        return this.mMenuView.getMenu();
    }

    public View getNavButtonView() {
        return this.mNavButtonView;
    }

    public CharSequence getNavigationContentDescription() {
        ImageButton imageButton = this.mNavButtonView;
        if (imageButton != null) {
            return imageButton.getContentDescription();
        }
        return null;
    }

    public Drawable getNavigationIcon() {
        ImageButton imageButton = this.mNavButtonView;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public C0171n getOuterActionMenuPresenter() {
        return this.mOuterActionMenuPresenter;
    }

    public Drawable getOverflowIcon() {
        e();
        return this.mMenuView.getOverflowIcon();
    }

    public Context getPopupContext() {
        return this.mPopupContext;
    }

    public int getPopupTheme() {
        return this.mPopupTheme;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitleText;
    }

    public final TextView getSubtitleTextView() {
        return this.mSubtitleTextView;
    }

    public CharSequence getTitle() {
        return this.mTitleText;
    }

    public int getTitleMarginBottom() {
        return this.mTitleMarginBottom;
    }

    public int getTitleMarginEnd() {
        return this.mTitleMarginEnd;
    }

    public int getTitleMarginStart() {
        return this.mTitleMarginStart;
    }

    public int getTitleMarginTop() {
        return this.mTitleMarginTop;
    }

    public final TextView getTitleTextView() {
        return this.mTitleTextView;
    }

    public InterfaceC0178p0 getWrapper() {
        if (this.mWrapper == null) {
            this.mWrapper = new c2(this, true);
        }
        return this.mWrapper;
    }

    public final int h(int i5) {
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        int layoutDirection = getLayoutDirection();
        int absoluteGravity = Gravity.getAbsoluteGravity(i5, layoutDirection) & 7;
        return (absoluteGravity == 1 || absoluteGravity == 3 || absoluteGravity == 5) ? absoluteGravity : layoutDirection == 1 ? 5 : 3;
    }

    public boolean hasExpandedActionView() {
        W1 w12 = this.mExpandedMenuPresenter;
        return (w12 == null || w12.f6655f == null) ? false : true;
    }

    public boolean hideOverflowMenu() {
        C0171n c0171n;
        ActionMenuView actionMenuView = this.mMenuView;
        return (actionMenuView == null || (c0171n = actionMenuView.f6382i) == null || !c0171n.hideOverflowMenu()) ? false : true;
    }

    public final int i(int i5, View view) {
        X1 x12 = (X1) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i7 = i5 > 0 ? (measuredHeight - i5) / 2 : 0;
        int i9 = x12.f6659a & 112;
        if (i9 != 16 && i9 != 48 && i9 != 80) {
            i9 = this.mGravity & 112;
        }
        if (i9 == 48) {
            return getPaddingTop();
        }
        if (i9 == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) x12).bottomMargin) - i7;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int iMax = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i10 = ((ViewGroup.MarginLayoutParams) x12).topMargin;
        if (iMax < i10) {
            iMax = i10;
        } else {
            int i11 = (((height - paddingBottom) - measuredHeight) - iMax) - paddingTop;
            int i12 = ((ViewGroup.MarginLayoutParams) x12).bottomMargin;
            if (i11 < i12) {
                iMax = Math.max(0, iMax - (i12 - i11));
            }
        }
        return paddingTop + iMax;
    }

    public void inflateMenu(int i5) {
        getMenuInflater().inflate(i5, getMenu());
    }

    public void invalidateMenu() {
        Iterator<MenuItem> it = this.mProvidedMenuItems.iterator();
        while (it.hasNext()) {
            getMenu().removeItem(it.next().getItemId());
        }
        Menu menu = getMenu();
        ArrayList<MenuItem> currentMenuItems = getCurrentMenuItems();
        C0219k c0219k = this.mMenuHostHelper;
        MenuInflater menuInflater = getMenuInflater();
        Iterator it2 = c0219k.f7236b.iterator();
        while (it2.hasNext()) {
            ((InterfaceC0221m) it2.next()).c(menu, menuInflater);
        }
        ArrayList<MenuItem> currentMenuItems2 = getCurrentMenuItems();
        currentMenuItems2.removeAll(currentMenuItems);
        this.mProvidedMenuItems = currentMenuItems2;
        this.mMenuHostHelper.c(menu);
    }

    public boolean isBackInvokedCallbackEnabled() {
        return this.mBackInvokedCallbackEnabled;
    }

    public boolean isOverflowMenuShowPending() {
        C0171n c0171n;
        ActionMenuView actionMenuView = this.mMenuView;
        return (actionMenuView == null || (c0171n = actionMenuView.f6382i) == null || (c0171n.f6772q == null && !c0171n.isOverflowMenuShowing())) ? false : true;
    }

    public boolean isOverflowMenuShowing() {
        C0171n c0171n;
        ActionMenuView actionMenuView = this.mMenuView;
        return (actionMenuView == null || (c0171n = actionMenuView.f6382i) == null || !c0171n.isOverflowMenuShowing()) ? false : true;
    }

    public boolean isTitleTruncated() {
        Layout layout;
        TextView textView = this.mTitleTextView;
        if (textView == null || (layout = textView.getLayout()) == null) {
            return false;
        }
        int lineCount = layout.getLineCount();
        for (int i5 = 0; i5 < lineCount; i5++) {
            if (layout.getEllipsisCount(i5) > 0) {
                return true;
            }
        }
        return false;
    }

    public final boolean l(View view) {
        return view.getParent() == this || this.mHiddenViews.contains(view);
    }

    public final int m(View view, int i5, int i7, int[] iArr) {
        X1 x12 = (X1) view.getLayoutParams();
        int i9 = ((ViewGroup.MarginLayoutParams) x12).leftMargin - iArr[0];
        int iMax = Math.max(0, i9) + i5;
        iArr[0] = Math.max(0, -i9);
        int i10 = i(i7, view);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax, i10, iMax + measuredWidth, view.getMeasuredHeight() + i10);
        return measuredWidth + ((ViewGroup.MarginLayoutParams) x12).rightMargin + iMax;
    }

    public final int n(View view, int i5, int i7, int[] iArr) {
        X1 x12 = (X1) view.getLayoutParams();
        int i9 = ((ViewGroup.MarginLayoutParams) x12).rightMargin - iArr[1];
        int iMax = i5 - Math.max(0, i9);
        iArr[1] = Math.max(0, -i9);
        int i10 = i(i7, view);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(iMax - measuredWidth, i10, iMax, view.getMeasuredHeight() + i10);
        return iMax - (measuredWidth + ((ViewGroup.MarginLayoutParams) x12).leftMargin);
    }

    public final int o(View view, int i5, int i7, int i9, int i10, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i11 = marginLayoutParams.leftMargin - iArr[0];
        int i12 = marginLayoutParams.rightMargin - iArr[1];
        int iMax = Math.max(0, i12) + Math.max(0, i11);
        iArr[0] = Math.max(0, -i11);
        iArr[1] = Math.max(0, -i12);
        view.measure(ViewGroup.getChildMeasureSpec(i5, getPaddingRight() + getPaddingLeft() + iMax + i7, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i9, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i10, marginLayoutParams.height));
        return view.getMeasuredWidth() + iMax;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        updateBackInvokedCallbackState();
        int dimensionPixelSize = this.mUserTopPadding;
        if (dimensionPixelSize == -1) {
            dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sesl_action_bar_top_padding);
        }
        setPadding(0, dimensionPixelSize, 0, 0);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(AbstractC0478a.f10564j);
        int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(16, 0);
        typedArrayObtainStyledAttributes.recycle();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = dimensionPixelSize2 + dimensionPixelSize;
        setLayoutParams(layoutParams);
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        C0171n c0171n;
        C0171n c0171n2;
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(AbstractC0478a.f10564j);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(16, 0);
        if (this.mNavButtonView != null) {
            typedArrayObtainStyledAttributes.recycle();
            typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, AbstractC0478a.f10554E, R.attr.actionOverflowButtonStyle, 0);
            this.mNavButtonView.setMinimumHeight(typedArrayObtainStyledAttributes.getDimensionPixelSize(4, 0));
        }
        typedArrayObtainStyledAttributes.recycle();
        int dimensionPixelSize2 = this.mUserTopPadding;
        if (dimensionPixelSize2 == -1) {
            dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.sesl_action_bar_top_padding);
        }
        setPadding(0, dimensionPixelSize2, 0, 0);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = dimensionPixelSize + dimensionPixelSize2;
        setLayoutParams(layoutParams);
        TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(null, AbstractC0478a.f10553D, android.R.attr.toolbarStyle, 0);
        int dimensionPixelSize3 = typedArrayObtainStyledAttributes2.getDimensionPixelSize(14, -1);
        if (dimensionPixelSize3 >= -1) {
            this.mMaxButtonHeight = dimensionPixelSize3;
        }
        int dimensionPixelSize4 = typedArrayObtainStyledAttributes2.getDimensionPixelSize(1, -1);
        if (dimensionPixelSize4 >= -1) {
            setMinimumHeight(dimensionPixelSize4);
        }
        typedArrayObtainStyledAttributes2.recycle();
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView == null || (c0171n = actionMenuView.f6382i) == null || !c0171n.isOverflowMenuShowing() || (c0171n2 = this.mMenuView.f6382i) == null) {
            return;
        }
        c0171n2.hideOverflowMenu();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.mShowOverflowMenuRunnable);
        updateBackInvokedCallbackState();
        if (this.mOnGlobalLayoutListenerForTD != null) {
            getViewTreeObserver().removeOnGlobalLayoutListener(this.mOnGlobalLayoutListenerForTD);
            this.mOnGlobalLayoutListenerForTD = null;
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.mEatingHover = false;
        }
        if (!this.mEatingHover) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.mEatingHover = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.mEatingHover = false;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x02b6 A[LOOP:0: B:109:0x02b4->B:110:0x02b6, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02d8 A[LOOP:1: B:112:0x02d6->B:113:0x02d8, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:116:0x02fe A[LOOP:2: B:115:0x02fc->B:116:0x02fe, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:120:0x033f  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0350 A[LOOP:3: B:124:0x034e->B:125:0x0350, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00e6  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x023b  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onLayout(boolean r19, int r20, int r21, int r22, int r23) {
        /*
            Method dump skipped, instruction units count: 869
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    public void onMeasure(int i5, int i7) {
        int iJ;
        int iMax;
        int iCombineMeasuredStates;
        int iJ2;
        int iK;
        int iCombineMeasuredStates2;
        int iMax2;
        int[] iArr = this.mTempMargins;
        boolean zA = h2.a(this);
        int i9 = !zA ? 1 : 0;
        int i10 = 0;
        if (q(this.mNavButtonView)) {
            p(this.mNavButtonView, i5, 0, i7, this.mMaxButtonHeight);
            iJ = j(this.mNavButtonView) + this.mNavButtonView.getMeasuredWidth();
            int iMax3 = Math.max(0, k(this.mNavButtonView) + this.mNavButtonView.getMeasuredHeight());
            int iCombineMeasuredStates3 = View.combineMeasuredStates(0, this.mNavButtonView.getMeasuredState());
            Drawable drawable = this.mNavButtonView.getDrawable();
            Drawable background = this.mNavButtonView.getBackground();
            if (drawable != null && background != null) {
                int paddingLeft = (this.mNavButtonView.getPaddingLeft() - this.mNavButtonView.getPaddingRight()) / 2;
                E.a.f(background, paddingLeft, 0, paddingLeft + iJ, iMax3);
            }
            iMax = iMax3;
            iCombineMeasuredStates = iCombineMeasuredStates3;
        } else {
            iJ = 0;
            iMax = 0;
            iCombineMeasuredStates = 0;
        }
        if (q(this.mCollapseButtonView)) {
            p(this.mCollapseButtonView, i5, 0, i7, this.mMaxButtonHeight);
            iJ = j(this.mCollapseButtonView) + this.mCollapseButtonView.getMeasuredWidth();
            iMax = Math.max(iMax, k(this.mCollapseButtonView) + this.mCollapseButtonView.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.mCollapseButtonView.getMeasuredState());
        }
        int currentContentInsetStart = getCurrentContentInsetStart();
        int iMax4 = Math.max(currentContentInsetStart, iJ);
        iArr[zA ? 1 : 0] = Math.max(0, currentContentInsetStart - iJ);
        if (q(this.mMenuView)) {
            p(this.mMenuView, i5, iMax4, i7, this.mMaxButtonHeight);
            iJ2 = j(this.mMenuView) + this.mMenuView.getMeasuredWidth();
            iMax = Math.max(iMax, k(this.mMenuView) + this.mMenuView.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.mMenuView.getMeasuredState());
        } else {
            iJ2 = 0;
        }
        int currentContentInsetEnd = getCurrentContentInsetEnd();
        int iMax5 = iMax4 + Math.max(currentContentInsetEnd, iJ2);
        iArr[i9] = Math.max(0, currentContentInsetEnd - iJ2);
        if (q(this.mExpandedActionView)) {
            iMax5 += o(this.mExpandedActionView, i5, iMax5, i7, 0, iArr);
            iMax = Math.max(iMax, k(this.mExpandedActionView) + this.mExpandedActionView.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.mExpandedActionView.getMeasuredState());
        }
        if (q(this.mLogoView)) {
            iMax5 += o(this.mLogoView, i5, iMax5, i7, 0, iArr);
            iMax = Math.max(iMax, k(this.mLogoView) + this.mLogoView.getMeasuredHeight());
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, this.mLogoView.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (((X1) childAt.getLayoutParams()).f6660b == 0 && q(childAt)) {
                iMax5 += o(childAt, i5, iMax5, i7, 0, iArr);
                iMax = Math.max(iMax, k(childAt) + childAt.getMeasuredHeight());
                iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, childAt.getMeasuredState());
            }
        }
        int i12 = this.mTitleMarginTop + this.mTitleMarginBottom;
        int i13 = this.mTitleMarginStart + this.mTitleMarginEnd;
        if (q(this.mTitleTextView)) {
            Context context = getContext();
            int i14 = this.mTitleTextAppearance;
            int[] iArr2 = AbstractC0478a.f10552C;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i14, iArr2);
            TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes.peekValue(0);
            float dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sesl_toolbar_title_text_size);
            if (!TextUtils.isEmpty(this.mSubtitleText)) {
                dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sesl_toolbar_title_text_size_with_subtitle);
            }
            if (typedValuePeekValue != null && TextUtils.isEmpty(this.mSubtitleText)) {
                dimensionPixelSize = TypedValue.complexToFloat(typedValuePeekValue.data);
            }
            typedArrayObtainStyledAttributes.recycle();
            TypedArray typedArrayObtainStyledAttributes2 = getContext().obtainStyledAttributes(this.mSubtitleTextAppearance, iArr2);
            TypedValue typedValuePeekValue2 = typedArrayObtainStyledAttributes2.peekValue(0);
            typedArrayObtainStyledAttributes2.recycle();
            float dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.sesl_toolbar_subtitle_text_size);
            if (typedValuePeekValue2 != null) {
                dimensionPixelSize2 = TypedValue.complexToFloat(typedValuePeekValue2.data);
            }
            float f2 = getContext().getResources().getConfiguration().fontScale;
            if (f2 > MAX_FONT_SCALE) {
                f2 = 1.2f;
            }
            if (dimensionPixelSize == -1.0f || !TextUtils.isEmpty(this.mSubtitleText)) {
                this.mTitleTextView.setTextSize(0, dimensionPixelSize * f2);
                this.mSubtitleTextView.setTextSize(1, dimensionPixelSize2 * f2);
            } else {
                this.mTitleTextView.setTextSize(1, dimensionPixelSize * f2);
            }
            o(this.mTitleTextView, i5, iMax5 + i13, i7, i12, iArr);
            int iJ3 = j(this.mTitleTextView) + this.mTitleTextView.getMeasuredWidth();
            iK = k(this.mTitleTextView) + this.mTitleTextView.getMeasuredHeight();
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates, this.mTitleTextView.getMeasuredState());
            iMax2 = iJ3;
        } else {
            iK = 0;
            iCombineMeasuredStates2 = iCombineMeasuredStates;
            iMax2 = 0;
        }
        if (q(this.mSubtitleTextView)) {
            iMax2 = Math.max(iMax2, o(this.mSubtitleTextView, i5, iMax5 + i13, i7, iK + i12, iArr));
            iK += k(this.mSubtitleTextView) + this.mSubtitleTextView.getMeasuredHeight();
            iCombineMeasuredStates2 = View.combineMeasuredStates(iCombineMeasuredStates2, this.mSubtitleTextView.getMeasuredState());
        }
        int iMax6 = Math.max(iMax, iK);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop() + iMax6;
        int iResolveSizeAndState = View.resolveSizeAndState(Math.max(paddingRight + iMax5 + iMax2, getSuggestedMinimumWidth()), i5, (-16777216) & iCombineMeasuredStates2);
        int iResolveSizeAndState2 = View.resolveSizeAndState(Math.max(paddingBottom, getSuggestedMinimumHeight()), i7, iCombineMeasuredStates2 << 16);
        if (!this.mCollapsible) {
            i10 = iResolveSizeAndState2;
            break;
        }
        int childCount2 = getChildCount();
        for (int i15 = 0; i15 < childCount2; i15++) {
            View childAt2 = getChildAt(i15);
            if (q(childAt2) && childAt2.getMeasuredWidth() > 0 && childAt2.getMeasuredHeight() > 0) {
                i10 = iResolveSizeAndState2;
                break;
            }
        }
        setMeasuredDimension(iResolveSizeAndState, i10);
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem menuItemFindItem;
        if (!(parcelable instanceof Z1)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        Z1 z12 = (Z1) parcelable;
        super.onRestoreInstanceState(z12.getSuperState());
        ActionMenuView actionMenuView = this.mMenuView;
        androidx.appcompat.view.menu.j jVar = actionMenuView != null ? actionMenuView.f6379e : null;
        int i5 = z12.f6663e;
        if (i5 != 0 && this.mExpandedMenuPresenter != null && jVar != null && (menuItemFindItem = jVar.findItem(i5)) != null) {
            menuItemFindItem.expandActionView();
        }
        if (z12.f6664f) {
            removeCallbacks(this.mShowOverflowMenuRunnable);
            post(this.mShowOverflowMenuRunnable);
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i5) {
        super.onRtlPropertiesChanged(i5);
        d();
        R0 r02 = this.mContentInsets;
        boolean z9 = i5 == 1;
        if (z9 == r02.f6517g) {
            return;
        }
        r02.f6517g = z9;
        if (!r02.h) {
            r02.f6511a = r02.f6515e;
            r02.f6512b = r02.f6516f;
            return;
        }
        if (z9) {
            int i7 = r02.f6514d;
            if (i7 == Integer.MIN_VALUE) {
                i7 = r02.f6515e;
            }
            r02.f6511a = i7;
            int i9 = r02.f6513c;
            if (i9 == Integer.MIN_VALUE) {
                i9 = r02.f6516f;
            }
            r02.f6512b = i9;
            return;
        }
        int i10 = r02.f6513c;
        if (i10 == Integer.MIN_VALUE) {
            i10 = r02.f6515e;
        }
        r02.f6511a = i10;
        int i11 = r02.f6514d;
        if (i11 == Integer.MIN_VALUE) {
            i11 = r02.f6516f;
        }
        r02.f6512b = i11;
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        androidx.appcompat.view.menu.l lVar;
        Z1 z12 = new Z1(super.onSaveInstanceState());
        W1 w12 = this.mExpandedMenuPresenter;
        if (w12 != null && (lVar = w12.f6655f) != null) {
            z12.f6663e = lVar.f6270e;
        }
        z12.f6664f = isOverflowMenuShowing();
        return z12;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.mEatingTouch = false;
        }
        if (!this.mEatingTouch) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.mEatingTouch = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.mEatingTouch = false;
        }
        return true;
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i5) {
        super.onWindowVisibilityChanged(i5);
        if (i5 != 0) {
            if (this.mOnGlobalLayoutListenerForTD != null) {
                getViewTreeObserver().removeOnGlobalLayoutListener(this.mOnGlobalLayoutListenerForTD);
                this.mOnGlobalLayoutListenerForTD = null;
                return;
            }
            return;
        }
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver == null || this.mOnGlobalLayoutListenerForTD != null) {
            return;
        }
        M5.b bVar = new M5.b(1, this);
        this.mOnGlobalLayoutListenerForTD = bVar;
        viewTreeObserver.addOnGlobalLayoutListener(bVar);
    }

    public final void p(View view, int i5, int i7, int i9, int i10) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i5, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i7, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i9, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i10 >= 0) {
            if (mode != 0) {
                i10 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i10);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i10, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    public final boolean q(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    public void removeChildrenForExpandedActionView() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            if (((X1) childAt.getLayoutParams()).f6660b != 2 && childAt != this.mMenuView) {
                removeViewAt(childCount);
                this.mHiddenViews.add(childAt);
            }
        }
    }

    @Override // androidx.core.view.InterfaceC0215g
    public void removeMenuProvider(InterfaceC0221m interfaceC0221m) {
        this.mMenuHostHelper.d(interfaceC0221m);
    }

    public View seslGetMenuPopupBackgroundView() {
        C0150g c0150g;
        C0171n c0171n = this.mMenuView.f6382i;
        if (c0171n == null || (c0150g = c0171n.o) == null) {
            return null;
        }
        return c0150g.seslGetBackgroundView();
    }

    public PopupWindow seslGetMenuPopupWindow() {
        C0150g c0150g;
        C0171n c0171n = this.mMenuView.f6382i;
        if (c0171n == null || (c0150g = c0171n.o) == null) {
            return null;
        }
        return c0150g.seslGetPopupWindow();
    }

    public ActionMenuView seslGetMenuView() {
        return this.mMenuView;
    }

    public int seslGetSubtitleTextColor() {
        TextView textView = this.mSubtitleTextView;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return 0;
    }

    public int seslGetTitleTextColor() {
        TextView textView = this.mTitleTextView;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return 0;
    }

    public int seslGetUserTopPadding() {
        return this.mUserTopPadding;
    }

    public void seslSetSubtitleAlpha(float f2) {
        TextView textView = this.mSubtitleTextView;
        if (textView != null) {
            textView.setAlpha(f2);
        }
    }

    public void seslSetTitleAlpha(float f2) {
        TextView textView = this.mTitleTextView;
        if (textView != null) {
            textView.setAlpha(f2);
        }
    }

    public void seslSetUserTopPadding(int i5) {
        this.mUserTopPadding = i5;
    }

    public void setBackInvokedCallbackEnabled(boolean z9) {
        if (this.mBackInvokedCallbackEnabled != z9) {
            this.mBackInvokedCallbackEnabled = z9;
            updateBackInvokedCallbackState();
        }
    }

    public void setCollapseContentDescription(int i5) {
        setCollapseContentDescription(i5 != 0 ? getContext().getText(i5) : null);
    }

    public void setCollapseIcon(int i5) {
        setCollapseIcon(android.support.v4.media.session.f.y(getContext(), i5));
    }

    public void setCollapsible(boolean z9) {
        this.mCollapsible = z9;
        requestLayout();
    }

    public void setContentInsetEndWithActions(int i5) {
        if (i5 < 0) {
            i5 = Integer.MIN_VALUE;
        }
        if (i5 != this.mContentInsetEndWithActions) {
            this.mContentInsetEndWithActions = i5;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetStartWithNavigation(int i5) {
        if (i5 < 0) {
            i5 = Integer.MIN_VALUE;
        }
        if (i5 != this.mContentInsetStartWithNavigation) {
            this.mContentInsetStartWithNavigation = i5;
            if (getNavigationIcon() != null) {
                requestLayout();
            }
        }
    }

    public void setContentInsetsAbsolute(int i5, int i7) {
        d();
        R0 r02 = this.mContentInsets;
        r02.h = false;
        if (i5 != Integer.MIN_VALUE) {
            r02.f6515e = i5;
            r02.f6511a = i5;
        }
        if (i7 != Integer.MIN_VALUE) {
            r02.f6516f = i7;
            r02.f6512b = i7;
        }
    }

    public void setContentInsetsRelative(int i5, int i7) {
        d();
        this.mContentInsets.a(i5, i7);
    }

    public void setLogo(int i5) {
        setLogo(android.support.v4.media.session.f.y(getContext(), i5));
    }

    public void setLogoDescription(int i5) {
        setLogoDescription(getContext().getText(i5));
    }

    public void setMenu(androidx.appcompat.view.menu.j jVar, C0171n c0171n) {
        if (jVar == null && this.mMenuView == null) {
            return;
        }
        f();
        androidx.appcompat.view.menu.j jVar2 = this.mMenuView.f6379e;
        if (jVar2 == jVar) {
            return;
        }
        if (jVar2 != null) {
            jVar2.removeMenuPresenter(this.mOuterActionMenuPresenter);
            jVar2.removeMenuPresenter(this.mExpandedMenuPresenter);
        }
        if (this.mExpandedMenuPresenter == null) {
            this.mExpandedMenuPresenter = new W1(this);
        }
        c0171n.f6769m = true;
        if (jVar != null) {
            jVar.addMenuPresenter(c0171n, this.mPopupContext);
            jVar.addMenuPresenter(this.mExpandedMenuPresenter, this.mPopupContext);
        } else {
            c0171n.initForMenu(this.mPopupContext, null);
            this.mExpandedMenuPresenter.initForMenu(this.mPopupContext, null);
            c0171n.updateMenuView(true);
            this.mExpandedMenuPresenter.updateMenuView(true);
        }
        this.mMenuView.setPopupTheme(this.mPopupTheme);
        this.mMenuView.setPresenter(c0171n);
        this.mOuterActionMenuPresenter = c0171n;
        updateBackInvokedCallbackState();
    }

    public void setMenuCallbacks(androidx.appcompat.view.menu.v vVar, androidx.appcompat.view.menu.h hVar) {
        this.mActionMenuPresenterCallback = vVar;
        this.mMenuBuilderCallback = hVar;
        ActionMenuView actionMenuView = this.mMenuView;
        if (actionMenuView != null) {
            actionMenuView.f6383j = vVar;
            actionMenuView.f6384k = hVar;
        }
    }

    public void setNavigationContentDescription(int i5) {
        setNavigationContentDescription(i5 != 0 ? getContext().getText(i5) : null);
    }

    public void setNavigationIcon(int i5) {
        setNavigationIcon(android.support.v4.media.session.f.y(getContext(), i5));
    }

    public void setNavigationOnClickListener(View.OnClickListener onClickListener) {
        g();
        this.mNavButtonView.setOnClickListener(onClickListener);
    }

    public void setOnMenuItemClickListener(Y1 y12) {
        this.mOnMenuItemClickListener = y12;
    }

    public void setOverflowIcon(Drawable drawable) {
        e();
        this.mMenuView.setOverflowIcon(drawable);
    }

    public void setPopupTheme(int i5) {
        if (this.mPopupTheme != i5) {
            this.mPopupTheme = i5;
            if (i5 == 0) {
                this.mPopupContext = getContext();
            } else {
                this.mPopupContext = new ContextThemeWrapper(getContext(), i5);
            }
        }
    }

    public void setSubtitle(int i5) {
        setSubtitle(getContext().getText(i5));
    }

    public void setSubtitleTextAppearance(Context context, int i5) {
        this.mSubtitleTextAppearance = i5;
        TextView textView = this.mSubtitleTextView;
        if (textView != null) {
            textView.setTextAppearance(context, i5);
        }
    }

    public void setSubtitleTextColor(int i5) {
        setSubtitleTextColor(ColorStateList.valueOf(i5));
    }

    public void setTitle(int i5) {
        setTitle(getContext().getText(i5));
    }

    public void setTitleAccessibilityEnabled(boolean z9) {
        if (z9) {
            TextView textView = this.mTitleTextView;
            if (textView != null) {
                textView.setImportantForAccessibility(1);
            }
            TextView textView2 = this.mSubtitleTextView;
            if (textView2 != null) {
                textView2.setImportantForAccessibility(1);
                return;
            }
            return;
        }
        TextView textView3 = this.mTitleTextView;
        if (textView3 != null) {
            textView3.setImportantForAccessibility(2);
        }
        TextView textView4 = this.mSubtitleTextView;
        if (textView4 != null) {
            textView4.setImportantForAccessibility(2);
        }
    }

    public void setTitleMargin(int i5, int i7, int i9, int i10) {
        this.mTitleMarginStart = i5;
        this.mTitleMarginTop = i7;
        this.mTitleMarginEnd = i9;
        this.mTitleMarginBottom = i10;
        requestLayout();
    }

    public void setTitleMarginBottom(int i5) {
        this.mTitleMarginBottom = i5;
        requestLayout();
    }

    public void setTitleMarginEnd(int i5) {
        this.mTitleMarginEnd = i5;
        requestLayout();
    }

    public void setTitleMarginStart(int i5) {
        this.mTitleMarginStart = i5;
        requestLayout();
    }

    public void setTitleMarginTop(int i5) {
        this.mTitleMarginTop = i5;
        requestLayout();
    }

    public void setTitleTextAppearance(Context context, int i5) {
        this.mTitleTextAppearance = i5;
        TextView textView = this.mTitleTextView;
        if (textView != null) {
            textView.setTextAppearance(context, i5);
        }
    }

    public void setTitleTextColor(int i5) {
        setTitleTextColor(ColorStateList.valueOf(i5));
    }

    public boolean showOverflowMenu() {
        C0171n c0171n;
        ActionMenuView actionMenuView = this.mMenuView;
        return (actionMenuView == null || (c0171n = actionMenuView.f6382i) == null || !c0171n.k()) ? false : true;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void updateBackInvokedCallbackState() {
        /*
            r4 = this;
            r0 = 0
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 33
            if (r1 < r2) goto L4d
            android.window.OnBackInvokedDispatcher r1 = androidx.appcompat.widget.V1.a(r4)
            boolean r2 = r4.hasExpandedActionView()
            if (r2 == 0) goto L21
            if (r1 == 0) goto L21
            java.util.WeakHashMap r2 = androidx.core.view.W.f7199a
            boolean r2 = r4.isAttachedToWindow()
            if (r2 == 0) goto L21
            boolean r2 = r4.mBackInvokedCallbackEnabled
            if (r2 == 0) goto L21
            r2 = 1
            goto L22
        L21:
            r2 = r0
        L22:
            if (r2 == 0) goto L3f
            android.window.OnBackInvokedDispatcher r3 = r4.mBackInvokedDispatcher
            if (r3 != 0) goto L3f
            android.window.OnBackInvokedCallback r2 = r4.mBackInvokedCallback
            if (r2 != 0) goto L37
            androidx.appcompat.widget.T1 r2 = new androidx.appcompat.widget.T1
            r2.<init>(r4, r0)
            android.window.OnBackInvokedCallback r0 = androidx.appcompat.widget.V1.b(r2)
            r4.mBackInvokedCallback = r0
        L37:
            android.window.OnBackInvokedCallback r0 = r4.mBackInvokedCallback
            androidx.appcompat.widget.V1.c(r1, r0)
            r4.mBackInvokedDispatcher = r1
            goto L4d
        L3f:
            if (r2 != 0) goto L4d
            android.window.OnBackInvokedDispatcher r0 = r4.mBackInvokedDispatcher
            if (r0 == 0) goto L4d
            android.window.OnBackInvokedCallback r1 = r4.mBackInvokedCallback
            androidx.appcompat.widget.V1.d(r0, r1)
            r0 = 0
            r4.mBackInvokedDispatcher = r0
        L4d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.updateBackInvokedCallbackState():void");
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5);
        this.mGravity = 8388627;
        this.mTempViews = new ArrayList<>();
        this.mHiddenViews = new ArrayList<>();
        this.mTempMargins = new int[2];
        this.mMenuHostHelper = new C0219k(new T1(this, 1));
        this.mProvidedMenuItems = new ArrayList<>();
        this.mMenuViewItemClickListener = new U1(this);
        this.mShowOverflowMenuRunnable = new RunnableC0198w0(3, this);
        this.mUserTopPadding = -1;
        Context context2 = getContext();
        int[] iArr = AbstractC0478a.f10553D;
        S1 s1E = S1.e(context2, attributeSet, iArr, i5, 0);
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        androidx.core.view.T.d(this, context, iArr, attributeSet, s1E.f6522b, i5, 0);
        TypedArray typedArray = s1E.f6522b;
        this.mTitleTextAppearance = typedArray.getResourceId(29, 0);
        this.mSubtitleTextAppearance = typedArray.getResourceId(20, 0);
        this.mGravity = typedArray.getInteger(0, this.mGravity);
        this.mButtonGravity = typedArray.getInteger(3, 48);
        this.mBackground = s1E.b(2);
        this.mNavTooltipText = typedArray.getText(31);
        setBackground(this.mBackground);
        int dimensionPixelOffset = typedArray.getDimensionPixelOffset(23, 0);
        this.mTitleMarginBottom = dimensionPixelOffset;
        this.mTitleMarginTop = dimensionPixelOffset;
        this.mTitleMarginEnd = dimensionPixelOffset;
        this.mTitleMarginStart = dimensionPixelOffset;
        int dimensionPixelOffset2 = typedArray.getDimensionPixelOffset(26, -1);
        if (dimensionPixelOffset2 >= 0) {
            this.mTitleMarginStart = dimensionPixelOffset2;
        }
        int dimensionPixelOffset3 = typedArray.getDimensionPixelOffset(25, -1);
        if (dimensionPixelOffset3 >= 0) {
            this.mTitleMarginEnd = dimensionPixelOffset3;
        }
        int dimensionPixelOffset4 = typedArray.getDimensionPixelOffset(27, -1);
        if (dimensionPixelOffset4 >= 0) {
            this.mTitleMarginTop = dimensionPixelOffset4;
        }
        int dimensionPixelOffset5 = typedArray.getDimensionPixelOffset(24, -1);
        if (dimensionPixelOffset5 >= 0) {
            this.mTitleMarginBottom = dimensionPixelOffset5;
        }
        this.mMaxButtonHeight = typedArray.getDimensionPixelSize(14, -1);
        int dimensionPixelOffset6 = typedArray.getDimensionPixelOffset(10, Integer.MIN_VALUE);
        int dimensionPixelOffset7 = typedArray.getDimensionPixelOffset(6, Integer.MIN_VALUE);
        int dimensionPixelSize = typedArray.getDimensionPixelSize(8, 0);
        int dimensionPixelSize2 = typedArray.getDimensionPixelSize(9, 0);
        d();
        R0 r02 = this.mContentInsets;
        r02.h = false;
        if (dimensionPixelSize != Integer.MIN_VALUE) {
            r02.f6515e = dimensionPixelSize;
            r02.f6511a = dimensionPixelSize;
        }
        if (dimensionPixelSize2 != Integer.MIN_VALUE) {
            r02.f6516f = dimensionPixelSize2;
            r02.f6512b = dimensionPixelSize2;
        }
        if (dimensionPixelOffset6 != Integer.MIN_VALUE || dimensionPixelOffset7 != Integer.MIN_VALUE) {
            r02.a(dimensionPixelOffset6, dimensionPixelOffset7);
        }
        this.mContentInsetStartWithNavigation = typedArray.getDimensionPixelOffset(11, Integer.MIN_VALUE);
        this.mContentInsetEndWithActions = typedArray.getDimensionPixelOffset(7, Integer.MIN_VALUE);
        this.mCollapseIcon = s1E.b(5);
        this.mCollapseDescription = typedArray.getText(4);
        CharSequence text = typedArray.getText(22);
        if (!TextUtils.isEmpty(text)) {
            setTitle(text);
        }
        CharSequence text2 = typedArray.getText(19);
        if (!TextUtils.isEmpty(text2)) {
            setSubtitle(text2);
        }
        this.mPopupContext = getContext();
        setPopupTheme(typedArray.getResourceId(18, 0));
        Drawable drawableB = s1E.b(17);
        if (drawableB != null) {
            setNavigationIcon(drawableB);
        }
        CharSequence text3 = typedArray.getText(16);
        if (!TextUtils.isEmpty(text3)) {
            setNavigationContentDescription(text3);
        }
        Drawable drawableB2 = s1E.b(12);
        if (drawableB2 != null) {
            setLogo(drawableB2);
        }
        CharSequence text4 = typedArray.getText(13);
        if (!TextUtils.isEmpty(text4)) {
            setLogoDescription(text4);
        }
        if (typedArray.hasValue(30)) {
            setTitleTextColor(s1E.a(30));
        }
        if (typedArray.hasValue(21)) {
            setSubtitleTextColor(s1E.a(21));
        }
        if (typedArray.hasValue(15)) {
            inflateMenu(typedArray.getResourceId(15, 0));
        }
        s1E.f();
    }

    @Override // android.view.ViewGroup
    public X1 generateDefaultLayoutParams() {
        X1 x12 = new X1(-2, -2);
        x12.f6660b = 0;
        x12.f6659a = 8388627;
        return x12;
    }

    public void setCollapseContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            ensureCollapseButtonView();
        }
        ImageButton imageButton = this.mCollapseButtonView;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
            d2.a(this.mCollapseButtonView, charSequence);
            this.mCollapseDescription = charSequence;
        }
    }

    public void setCollapseIcon(Drawable drawable) {
        if (drawable != null) {
            ensureCollapseButtonView();
            this.mCollapseButtonView.setImageDrawable(drawable);
        } else {
            ImageButton imageButton = this.mCollapseButtonView;
            if (imageButton != null) {
                imageButton.setImageDrawable(this.mCollapseIcon);
            }
        }
    }

    public void setLogo(Drawable drawable) {
        if (drawable != null) {
            if (this.mLogoView == null) {
                this.mLogoView = new AppCompatImageView(getContext(), null);
            }
            if (!l(this.mLogoView)) {
                c(this.mLogoView, true);
            }
        } else {
            ImageView imageView = this.mLogoView;
            if (imageView != null && l(imageView)) {
                removeView(this.mLogoView);
                this.mHiddenViews.remove(this.mLogoView);
            }
        }
        ImageView imageView2 = this.mLogoView;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void setLogoDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence) && this.mLogoView == null) {
            this.mLogoView = new AppCompatImageView(getContext(), null);
        }
        ImageView imageView = this.mLogoView;
        if (imageView != null) {
            imageView.setContentDescription(charSequence);
        }
    }

    public void setNavigationContentDescription(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            g();
        }
        ImageButton imageButton = this.mNavButtonView;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
            d2.a(this.mNavButtonView, charSequence);
        }
    }

    public void setNavigationIcon(Drawable drawable) {
        if (drawable != null) {
            g();
            if (!l(this.mNavButtonView)) {
                c(this.mNavButtonView, true);
            }
        } else {
            ImageButton imageButton = this.mNavButtonView;
            if (imageButton != null && l(imageButton)) {
                removeView(this.mNavButtonView);
                this.mHiddenViews.remove(this.mNavButtonView);
            }
        }
        ImageButton imageButton2 = this.mNavButtonView;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
            this.mNavButtonIconDrawable = drawable;
        }
    }

    public void setSubtitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.mSubtitleTextView;
            if (textView != null && l(textView)) {
                removeView(this.mSubtitleTextView);
                this.mHiddenViews.remove(this.mSubtitleTextView);
            }
        } else {
            if (this.mSubtitleTextView == null) {
                Context context = getContext();
                C0154h0 c0154h0 = new C0154h0(context, null);
                this.mSubtitleTextView = c0154h0;
                c0154h0.setSingleLine();
                this.mSubtitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                int i5 = this.mSubtitleTextAppearance;
                if (i5 != 0) {
                    this.mSubtitleTextView.setTextAppearance(context, i5);
                }
                ColorStateList colorStateList = this.mSubtitleTextColor;
                if (colorStateList != null) {
                    this.mSubtitleTextView.setTextColor(colorStateList);
                }
            }
            if (!l(this.mSubtitleTextView)) {
                c(this.mSubtitleTextView, true);
            }
        }
        TextView textView2 = this.mSubtitleTextView;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.mSubtitleText = charSequence;
    }

    public void setSubtitleTextColor(ColorStateList colorStateList) {
        this.mSubtitleTextColor = colorStateList;
        TextView textView = this.mSubtitleTextView;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            TextView textView = this.mTitleTextView;
            if (textView != null && l(textView)) {
                removeView(this.mTitleTextView);
                this.mHiddenViews.remove(this.mTitleTextView);
            }
        } else {
            if (this.mTitleTextView == null) {
                Context context = getContext();
                C0154h0 c0154h0 = new C0154h0(context, null);
                this.mTitleTextView = c0154h0;
                c0154h0.setSingleLine();
                this.mTitleTextView.setEllipsize(TextUtils.TruncateAt.END);
                int i5 = this.mTitleTextAppearance;
                if (i5 != 0) {
                    this.mTitleTextView.setTextAppearance(context, i5);
                }
                ColorStateList colorStateList = this.mTitleTextColor;
                if (colorStateList != null) {
                    this.mTitleTextView.setTextColor(colorStateList);
                }
            }
            if (!l(this.mTitleTextView)) {
                c(this.mTitleTextView, true);
            }
        }
        TextView textView2 = this.mTitleTextView;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.mTitleText = charSequence;
    }

    public void setTitleTextColor(ColorStateList colorStateList) {
        this.mTitleTextColor = colorStateList;
        TextView textView = this.mTitleTextView;
        if (textView != null) {
            textView.setTextColor(colorStateList);
        }
    }

    @Override // android.view.ViewGroup
    public X1 generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        X1 x12 = new X1(context, attributeSet);
        x12.f6659a = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0478a.f10557b);
        x12.f6659a = typedArrayObtainStyledAttributes.getInt(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        x12.f6660b = 0;
        return x12;
    }

    public void addMenuProvider(InterfaceC0221m interfaceC0221m, InterfaceC0276s interfaceC0276s) {
        this.mMenuHostHelper.a(interfaceC0221m, interfaceC0276s);
    }

    @SuppressLint({"LambdaLast"})
    public void addMenuProvider(InterfaceC0221m interfaceC0221m, InterfaceC0276s interfaceC0276s, EnumC0271m enumC0271m) {
        this.mMenuHostHelper.b(interfaceC0221m, interfaceC0276s, enumC0271m);
    }

    @Override // android.view.ViewGroup
    public X1 generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        boolean z9 = layoutParams instanceof X1;
        if (z9) {
            X1 x12 = (X1) layoutParams;
            X1 x13 = new X1(x12);
            x13.f6660b = 0;
            x13.f6660b = x12.f6660b;
            return x13;
        }
        if (z9) {
            X1 x14 = new X1((X1) layoutParams);
            x14.f6660b = 0;
            return x14;
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            X1 x15 = new X1(marginLayoutParams);
            x15.f6660b = 0;
            ((ViewGroup.MarginLayoutParams) x15).leftMargin = marginLayoutParams.leftMargin;
            ((ViewGroup.MarginLayoutParams) x15).topMargin = marginLayoutParams.topMargin;
            ((ViewGroup.MarginLayoutParams) x15).rightMargin = marginLayoutParams.rightMargin;
            ((ViewGroup.MarginLayoutParams) x15).bottomMargin = marginLayoutParams.bottomMargin;
            return x15;
        }
        X1 x16 = new X1(layoutParams);
        x16.f6660b = 0;
        return x16;
    }
}
