package com.google.android.material.internal;

import C.j;
import C.s;
import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.view.menu.l;
import androidx.appcompat.view.menu.x;
import androidx.appcompat.widget.B0;
import androidx.appcompat.widget.d2;
import androidx.core.view.C0210b;
import androidx.core.view.W;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class NavigationMenuItemView extends ForegroundLinearLayout implements x {
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private final C0210b accessibilityDelegate;
    private FrameLayout actionArea;
    boolean checkable;
    private Drawable emptyDrawable;
    private boolean hasIconTintList;
    private int iconSize;
    private ColorStateList iconTintList;
    boolean isBold;
    private l itemData;
    private boolean needsEmptyIcon;
    private final CheckedTextView textView;

    public NavigationMenuItemView(Context context) {
        this(context, null);
    }

    private void adjustAppearance() {
        if (shouldExpandActionArea()) {
            this.textView.setVisibility(8);
            FrameLayout frameLayout = this.actionArea;
            if (frameLayout != null) {
                B0 b02 = (B0) frameLayout.getLayoutParams();
                ((LinearLayout.LayoutParams) b02).width = -1;
                this.actionArea.setLayoutParams(b02);
                return;
            }
            return;
        }
        this.textView.setVisibility(0);
        FrameLayout frameLayout2 = this.actionArea;
        if (frameLayout2 != null) {
            B0 b03 = (B0) frameLayout2.getLayoutParams();
            ((LinearLayout.LayoutParams) b03).width = -2;
            this.actionArea.setLayoutParams(b03);
        }
    }

    private StateListDrawable createDefaultBackground() {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(com.samsung.android.keyscafe.R.attr.colorControlHighlight, typedValue, true)) {
            return null;
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(CHECKED_STATE_SET, new ColorDrawable(typedValue.data));
        stateListDrawable.addState(ViewGroup.EMPTY_STATE_SET, new ColorDrawable(0));
        return stateListDrawable;
    }

    private void setActionView(View view) {
        if (view != null) {
            if (this.actionArea == null) {
                this.actionArea = (FrameLayout) ((ViewStub) findViewById(com.google.android.material.R.id.design_menu_item_action_area_stub)).inflate();
            }
            this.actionArea.removeAllViews();
            this.actionArea.addView(view);
        }
    }

    private boolean shouldExpandActionArea() {
        l lVar = this.itemData;
        return lVar.f6273i == null && lVar.getIcon() == null && this.itemData.getActionView() != null;
    }

    @Override // androidx.appcompat.view.menu.x
    public l getItemData() {
        return this.itemData;
    }

    @Override // androidx.appcompat.view.menu.x
    public void initialize(l lVar, int i5) {
        this.itemData = lVar;
        int i7 = lVar.f6270e;
        if (i7 > 0) {
            setId(i7);
        }
        setVisibility(lVar.isVisible() ? 0 : 8);
        if (getBackground() == null) {
            StateListDrawable stateListDrawableCreateDefaultBackground = createDefaultBackground();
            WeakHashMap weakHashMap = W.f7199a;
            setBackground(stateListDrawableCreateDefaultBackground);
        }
        setCheckable(lVar.isCheckable());
        setChecked(lVar.isChecked());
        setEnabled(lVar.isEnabled());
        setTitle(lVar.f6273i);
        setIcon(lVar.getIcon());
        setActionView(lVar.getActionView());
        setContentDescription(lVar.f6284u);
        d2.a(this, lVar.f6285v);
        adjustAppearance();
    }

    @Override // android.view.ViewGroup, android.view.View
    public int[] onCreateDrawableState(int i5) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i5 + 1);
        l lVar = this.itemData;
        if (lVar != null && lVar.isCheckable() && this.itemData.isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, CHECKED_STATE_SET);
        }
        return iArrOnCreateDrawableState;
    }

    public boolean prefersCondensedTitle() {
        return false;
    }

    public void recycle() {
        FrameLayout frameLayout = this.actionArea;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
        }
        this.textView.setCompoundDrawables(null, null, null, null);
    }

    public void setCheckable(boolean z9) {
        refreshDrawableState();
        if (this.checkable != z9) {
            this.checkable = z9;
            this.accessibilityDelegate.sendAccessibilityEvent(this.textView, 2048);
        }
    }

    public void setChecked(boolean z9) {
        refreshDrawableState();
        this.textView.setChecked(z9);
        CheckedTextView checkedTextView = this.textView;
        checkedTextView.setTypeface(checkedTextView.getTypeface(), (z9 && this.isBold) ? 1 : 0);
    }

    public void setHorizontalPadding(int i5) {
        setPadding(i5, getPaddingTop(), i5, getPaddingBottom());
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            if (this.hasIconTintList) {
                Drawable.ConstantState constantState = drawable.getConstantState();
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                drawable = drawable.mutate();
                E.a.h(drawable, this.iconTintList);
            }
            int i5 = this.iconSize;
            drawable.setBounds(0, 0, i5, i5);
        } else if (this.needsEmptyIcon) {
            if (this.emptyDrawable == null) {
                Resources resources = getResources();
                int i7 = com.google.android.material.R.drawable.navigation_empty_icon;
                Resources.Theme theme = getContext().getTheme();
                ThreadLocal threadLocal = s.f322a;
                Drawable drawableA = j.a(resources, i7, theme);
                this.emptyDrawable = drawableA;
                if (drawableA != null) {
                    int i9 = this.iconSize;
                    drawableA.setBounds(0, 0, i9, i9);
                }
            }
            drawable = this.emptyDrawable;
        }
        this.textView.setCompoundDrawablesRelative(drawable, null, null, null);
    }

    public void setIconPadding(int i5) {
        this.textView.setCompoundDrawablePadding(i5);
    }

    public void setIconSize(int i5) {
        this.iconSize = i5;
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.iconTintList = colorStateList;
        this.hasIconTintList = colorStateList != null;
        l lVar = this.itemData;
        if (lVar != null) {
            setIcon(lVar.getIcon());
        }
    }

    public void setMaxLines(int i5) {
        this.textView.setMaxLines(i5);
    }

    public void setNeedsEmptyIcon(boolean z9) {
        this.needsEmptyIcon = z9;
    }

    public void setShortcut(boolean z9, char c5) {
    }

    public void setTextAppearance(int i5) {
        this.textView.setTextAppearance(i5);
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.textView.setTextColor(colorStateList);
    }

    public void setTitle(CharSequence charSequence) {
        this.textView.setText(charSequence);
    }

    public boolean showsIcon() {
        return true;
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5);
        this.isBold = true;
        C0210b c0210b = new C0210b() { // from class: com.google.android.material.internal.NavigationMenuItemView.1
            @Override // androidx.core.view.C0210b
            public void onInitializeAccessibilityNodeInfo(View view, L.l lVar) {
                super.onInitializeAccessibilityNodeInfo(view, lVar);
                lVar.i(NavigationMenuItemView.this.checkable);
            }
        };
        this.accessibilityDelegate = c0210b;
        setOrientation(0);
        LayoutInflater.from(context).inflate(com.google.android.material.R.layout.design_navigation_menu_item, (ViewGroup) this, true);
        setIconSize(context.getResources().getDimensionPixelSize(com.google.android.material.R.dimen.design_navigation_icon_size));
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(com.google.android.material.R.id.design_menu_item_text);
        this.textView = checkedTextView;
        checkedTextView.setDuplicateParentStateEnabled(true);
        W.i(checkedTextView, c0210b);
    }

    public void initialize(l lVar, boolean z9) {
        this.isBold = z9;
        initialize(lVar, 0);
    }
}
