package com.google.android.material.bottomnavigation;

import B.b;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.appcompat.view.menu.y;
import androidx.appcompat.widget.S1;
import androidx.core.view.B;
import androidx.core.view.C0233z;
import androidx.core.view.W;
import androidx.core.view.w0;
import com.google.android.material.R;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.navigation.NavigationBarMenuView;
import com.google.android.material.navigation.NavigationBarView;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class BottomNavigationView extends NavigationBarView {
    static final int MAX_ITEM_COUNT = 5;
    private ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListenerForTD;

    @Deprecated
    public interface OnNavigationItemReselectedListener extends NavigationBarView.OnItemReselectedListener {
    }

    @Deprecated
    public interface OnNavigationItemSelectedListener extends NavigationBarView.OnItemSelectedListener {
    }

    public BottomNavigationView(Context context) {
        this(context, null);
    }

    private void addCompatibilityTopDivider(Context context) {
        View view = new View(context);
        view.setBackgroundColor(b.a(context, R.color.sesl_bottom_navigation_shadow_color));
        view.setLayoutParams(new FrameLayout.LayoutParams(-1, getResources().getDimensionPixelSize(R.dimen.sesl_bottom_navigation_shadow_height)));
        addView(view);
    }

    private void applyWindowInsets() {
        ViewUtils.doOnApplyWindowInsets(this, new ViewUtils.OnApplyWindowInsetsListener() { // from class: com.google.android.material.bottomnavigation.BottomNavigationView.1
            @Override // com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener
            public w0 onApplyWindowInsets(View view, w0 w0Var, ViewUtils.RelativePadding relativePadding) {
                relativePadding.bottom = w0Var.a() + relativePadding.bottom;
                WeakHashMap weakHashMap = W.f7199a;
                boolean z9 = view.getLayoutDirection() == 1;
                int iB = w0Var.b();
                int iC = w0Var.c();
                relativePadding.start += z9 ? iC : iB;
                int i5 = relativePadding.end;
                if (!z9) {
                    iB = iC;
                }
                relativePadding.end = i5 + iB;
                relativePadding.applyToView(view);
                return w0Var;
            }
        });
    }

    private int makeMinHeightSpec(int i5) {
        return View.MeasureSpec.makeMeasureSpec(getPaddingBottom() + getPaddingTop() + getSuggestedMinimumHeight(), 1073741824);
    }

    private void seslRemoveListenerForTouchDelegate() {
        if (this.mOnGlobalLayoutListenerForTD != null) {
            getViewTreeObserver().removeOnGlobalLayoutListener(this.mOnGlobalLayoutListenerForTD);
            this.mOnGlobalLayoutListenerForTD = null;
        }
    }

    private void seslSetTouchDelegateForBottomBar() {
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver == null || this.mOnGlobalLayoutListenerForTD != null) {
            return;
        }
        ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.google.android.material.bottomnavigation.BottomNavigationView.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                final BottomNavigationView bottomNavigationView = BottomNavigationView.this;
                if (bottomNavigationView != null) {
                    bottomNavigationView.post(new Runnable() { // from class: com.google.android.material.bottomnavigation.BottomNavigationView.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            View childAt;
                            B b3 = new B(bottomNavigationView);
                            int childCount = bottomNavigationView.getChildCount();
                            boolean z9 = false;
                            int i5 = 0;
                            while (true) {
                                if (i5 >= childCount) {
                                    childAt = null;
                                    break;
                                }
                                childAt = bottomNavigationView.getChildAt(i5);
                                if (childAt instanceof BottomNavigationMenuView) {
                                    break;
                                } else {
                                    i5++;
                                }
                            }
                            if (childAt != null && childAt.getVisibility() == 0) {
                                ViewGroup viewGroup = (ViewGroup) childAt;
                                int childCount2 = viewGroup.getChildCount();
                                int i7 = 0;
                                boolean z10 = false;
                                while (i7 < childCount2) {
                                    View childAt2 = viewGroup.getChildAt(i7);
                                    if (childAt2.getVisibility() == 0) {
                                        int measuredHeight = childAt2.getMeasuredHeight() / 2;
                                        b3.a(childAt2, C0233z.a(i7 == 0 ? measuredHeight : 0, measuredHeight, i7 == childCount2 + (-1) ? measuredHeight : 0, measuredHeight));
                                        z10 = true;
                                    }
                                    i7++;
                                }
                                z9 = z10;
                            }
                            if (z9) {
                                bottomNavigationView.setTouchDelegate(b3);
                            }
                        }
                    });
                }
            }
        };
        this.mOnGlobalLayoutListenerForTD = onGlobalLayoutListener;
        viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener);
    }

    private boolean shouldDrawCompatibilityTopDivider() {
        return false;
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public NavigationBarMenuView createNavigationBarMenuView(Context context) {
        return new BottomNavigationMenuView(context);
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public int getMaxItemCount() {
        return 5;
    }

    public boolean isItemHorizontalTranslationEnabled() {
        return ((BottomNavigationMenuView) getMenuView()).isItemHorizontalTranslationEnabled();
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i5, int i7) {
        super.onMeasure(i5, makeMinHeightSpec(i7));
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i5) {
        super.onWindowVisibilityChanged(i5);
        if (i5 == 0) {
            seslSetTouchDelegateForBottomBar();
        } else {
            seslRemoveListenerForTouchDelegate();
        }
    }

    @Override // com.google.android.material.navigation.NavigationBarView
    public void seslSetGroupDividerEnabled(boolean z9) {
        super.seslSetGroupDividerEnabled(z9);
    }

    public void setItemHorizontalTranslationEnabled(boolean z9) {
        BottomNavigationMenuView bottomNavigationMenuView = (BottomNavigationMenuView) getMenuView();
        if (bottomNavigationMenuView.isItemHorizontalTranslationEnabled() != z9) {
            bottomNavigationMenuView.setItemHorizontalTranslationEnabled(z9);
            getPresenter().updateMenuView(false);
        }
    }

    @Deprecated
    public void setOnNavigationItemReselectedListener(OnNavigationItemReselectedListener onNavigationItemReselectedListener) {
        setOnItemReselectedListener(onNavigationItemReselectedListener);
    }

    @Deprecated
    public void setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener onNavigationItemSelectedListener) {
        setOnItemSelectedListener(onNavigationItemSelectedListener);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.bottomNavigationStyle);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet, int i5) {
        this(context, attributeSet, i5, R.style.Widget_Design_BottomNavigationView);
    }

    public BottomNavigationView(Context context, AttributeSet attributeSet, int i5, int i7) {
        int dimensionPixelSize;
        super(context, attributeSet, i5, i7);
        Context context2 = getContext();
        S1 s1ObtainTintedStyledAttributes = ThemeEnforcement.obtainTintedStyledAttributes(context2, attributeSet, R.styleable.BottomNavigationView, i5, i7, new int[0]);
        setItemHorizontalTranslationEnabled(s1ObtainTintedStyledAttributes.f6522b.getBoolean(R.styleable.BottomNavigationView_itemHorizontalTranslationEnabled, true));
        if (s1ObtainTintedStyledAttributes.f6522b.getBoolean(R.styleable.BottomNavigationView_compatShadowEnabled, true) && shouldDrawCompatibilityTopDivider()) {
            addCompatibilityTopDivider(context2);
        }
        s1ObtainTintedStyledAttributes.f();
        y menuView = getMenuView();
        if (menuView instanceof NavigationBarMenuView) {
            NavigationBarMenuView navigationBarMenuView = (NavigationBarMenuView) menuView;
            if (navigationBarMenuView.getViewType() != 3) {
                dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sesl_bottom_navigation_icon_mode_height);
            } else {
                dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.sesl_bottom_navigation_text_mode_height);
                int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.sesl_navigation_bar_text_mode_padding_horizontal);
                setPadding(dimensionPixelSize2, getPaddingTop(), dimensionPixelSize2, getPaddingBottom());
            }
            navigationBarMenuView.setMinimumHeight(dimensionPixelSize);
            setMinimumHeight(dimensionPixelSize);
        }
    }
}
