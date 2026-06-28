package com.google.android.material.internal;

import L.k;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.view.menu.D;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.view.menu.l;
import androidx.appcompat.view.menu.v;
import androidx.appcompat.view.menu.w;
import androidx.appcompat.view.menu.y;
import androidx.core.view.C0210b;
import androidx.core.view.W;
import androidx.core.view.w0;
import androidx.recyclerview.widget.AbstractC0341j0;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.V0;
import androidx.recyclerview.widget.X0;
import com.google.android.material.R;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class NavigationMenuPresenter implements w {
    public static final int NO_TEXT_APPEARANCE_SET = 0;
    private static final String STATE_ADAPTER = "android:menu:adapter";
    private static final String STATE_HEADER = "android:menu:header";
    private static final String STATE_HIERARCHY = "android:menu:list";
    NavigationMenuAdapter adapter;
    private v callback;
    int dividerInsetEnd;
    int dividerInsetStart;
    boolean hasCustomItemIconSize;
    LinearLayout headerLayout;
    ColorStateList iconTintList;
    private int id;
    Drawable itemBackground;
    RippleDrawable itemForeground;
    int itemHorizontalPadding;
    int itemIconPadding;
    int itemIconSize;
    private int itemMaxLines;
    int itemVerticalPadding;
    LayoutInflater layoutInflater;
    j menu;
    private NavigationMenuView menuView;
    int paddingSeparator;
    private int paddingTopDefault;
    ColorStateList subheaderColor;
    int subheaderInsetEnd;
    int subheaderInsetStart;
    ColorStateList textColor;
    int subheaderTextAppearance = 0;
    int textAppearance = 0;
    boolean textAppearanceActiveBoldEnabled = true;
    boolean isBehindStatusBar = true;
    private int overScrollMode = -1;
    final View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.google.android.material.internal.NavigationMenuPresenter.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            boolean z9 = true;
            NavigationMenuPresenter.this.setUpdateSuspended(true);
            l itemData = ((NavigationMenuItemView) view).getItemData();
            NavigationMenuPresenter navigationMenuPresenter = NavigationMenuPresenter.this;
            boolean zPerformItemAction = navigationMenuPresenter.menu.performItemAction(itemData, navigationMenuPresenter, 0);
            if (itemData != null && itemData.isCheckable() && zPerformItemAction) {
                NavigationMenuPresenter.this.adapter.setCheckedItem(itemData);
            } else {
                z9 = false;
            }
            NavigationMenuPresenter.this.setUpdateSuspended(false);
            if (z9) {
                NavigationMenuPresenter.this.updateMenuView(false);
            }
        }
    };

    public static class HeaderViewHolder extends ViewHolder {
        public HeaderViewHolder(View view) {
            super(view);
        }
    }

    public class NavigationMenuAdapter extends AbstractC0341j0 {
        private static final String STATE_ACTION_VIEWS = "android:menu:action_views";
        private static final String STATE_CHECKED_ITEM = "android:menu:checked";
        private static final int VIEW_TYPE_HEADER = 3;
        private static final int VIEW_TYPE_NORMAL = 0;
        private static final int VIEW_TYPE_SEPARATOR = 2;
        private static final int VIEW_TYPE_SUBHEADER = 1;
        private l checkedItem;
        private final ArrayList<NavigationMenuItem> items = new ArrayList<>();
        private boolean updateSuspended;

        public NavigationMenuAdapter() {
            prepareMenuItems();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int adjustItemPositionForA11yDelegate(int i5) {
            int i7 = i5;
            for (int i9 = 0; i9 < i5; i9++) {
                if (NavigationMenuPresenter.this.adapter.getItemViewType(i9) == 2 || NavigationMenuPresenter.this.adapter.getItemViewType(i9) == 3) {
                    i7--;
                }
            }
            return i7;
        }

        private void appendTransparentIconIfMissing(int i5, int i7) {
            while (i5 < i7) {
                ((NavigationMenuTextItem) this.items.get(i5)).needsEmptyIcon = true;
                i5++;
            }
        }

        private void prepareMenuItems() {
            if (this.updateSuspended) {
                return;
            }
            boolean z9 = true;
            this.updateSuspended = true;
            this.items.clear();
            this.items.add(new NavigationMenuHeaderItem());
            int size = NavigationMenuPresenter.this.menu.getVisibleItems().size();
            int i5 = -1;
            int i7 = 0;
            boolean z10 = false;
            int size2 = 0;
            while (i7 < size) {
                l lVar = NavigationMenuPresenter.this.menu.getVisibleItems().get(i7);
                if (lVar.isChecked()) {
                    setCheckedItem(lVar);
                }
                if (lVar.isCheckable()) {
                    lVar.i(false);
                }
                if (lVar.hasSubMenu()) {
                    D d8 = lVar.f6282s;
                    if (d8.hasVisibleItems()) {
                        if (i7 != 0) {
                            this.items.add(new NavigationMenuSeparatorItem(NavigationMenuPresenter.this.paddingSeparator, 0));
                        }
                        this.items.add(new NavigationMenuTextItem(lVar));
                        int size3 = this.items.size();
                        int size4 = d8.size();
                        int i9 = 0;
                        boolean z11 = false;
                        while (i9 < size4) {
                            l lVar2 = (l) d8.getItem(i9);
                            if (lVar2.isVisible()) {
                                if (!z11 && lVar2.getIcon() != null) {
                                    z11 = z9;
                                }
                                if (lVar2.isCheckable()) {
                                    lVar2.i(false);
                                }
                                if (lVar.isChecked()) {
                                    setCheckedItem(lVar);
                                }
                                this.items.add(new NavigationMenuTextItem(lVar2));
                            }
                            i9++;
                            z9 = true;
                        }
                        if (z11) {
                            appendTransparentIconIfMissing(size3, this.items.size());
                        }
                    }
                } else {
                    int i10 = lVar.f6271f;
                    if (i10 != i5) {
                        size2 = this.items.size();
                        z10 = lVar.getIcon() != null;
                        if (i7 != 0) {
                            size2++;
                            ArrayList<NavigationMenuItem> arrayList = this.items;
                            int i11 = NavigationMenuPresenter.this.paddingSeparator;
                            arrayList.add(new NavigationMenuSeparatorItem(i11, i11));
                        }
                    } else if (!z10 && lVar.getIcon() != null) {
                        appendTransparentIconIfMissing(size2, this.items.size());
                        z10 = true;
                    }
                    NavigationMenuTextItem navigationMenuTextItem = new NavigationMenuTextItem(lVar);
                    navigationMenuTextItem.needsEmptyIcon = z10;
                    this.items.add(navigationMenuTextItem);
                    i5 = i10;
                }
                i7++;
                z9 = true;
            }
            this.updateSuspended = false;
        }

        private void setAccessibilityDelegate(View view, final int i5, final boolean z9) {
            W.i(view, new C0210b() { // from class: com.google.android.material.internal.NavigationMenuPresenter.NavigationMenuAdapter.1
                @Override // androidx.core.view.C0210b
                public void onInitializeAccessibilityNodeInfo(View view2, L.l lVar) {
                    super.onInitializeAccessibilityNodeInfo(view2, lVar);
                    lVar.l(k.a(NavigationMenuAdapter.this.adjustItemPositionForA11yDelegate(i5), 1, 1, 1, z9, view2.isSelected()));
                }
            });
        }

        public Bundle createInstanceState() {
            Bundle bundle = new Bundle();
            l lVar = this.checkedItem;
            if (lVar != null) {
                bundle.putInt(STATE_CHECKED_ITEM, lVar.f6270e);
            }
            SparseArray<? extends Parcelable> sparseArray = new SparseArray<>();
            int size = this.items.size();
            for (int i5 = 0; i5 < size; i5++) {
                NavigationMenuItem navigationMenuItem = this.items.get(i5);
                if (navigationMenuItem instanceof NavigationMenuTextItem) {
                    l menuItem = ((NavigationMenuTextItem) navigationMenuItem).getMenuItem();
                    View actionView = menuItem != null ? menuItem.getActionView() : null;
                    if (actionView != null) {
                        ParcelableSparseArray parcelableSparseArray = new ParcelableSparseArray();
                        actionView.saveHierarchyState(parcelableSparseArray);
                        sparseArray.put(menuItem.f6270e, parcelableSparseArray);
                    }
                }
            }
            bundle.putSparseParcelableArray(STATE_ACTION_VIEWS, sparseArray);
            return bundle;
        }

        public l getCheckedItem() {
            return this.checkedItem;
        }

        @Override // androidx.recyclerview.widget.AbstractC0341j0
        public int getItemCount() {
            return this.items.size();
        }

        @Override // androidx.recyclerview.widget.AbstractC0341j0
        public long getItemId(int i5) {
            return i5;
        }

        @Override // androidx.recyclerview.widget.AbstractC0341j0
        public int getItemViewType(int i5) {
            NavigationMenuItem navigationMenuItem = this.items.get(i5);
            if (navigationMenuItem instanceof NavigationMenuSeparatorItem) {
                return 2;
            }
            if (navigationMenuItem instanceof NavigationMenuHeaderItem) {
                return 3;
            }
            if (navigationMenuItem instanceof NavigationMenuTextItem) {
                return ((NavigationMenuTextItem) navigationMenuItem).getMenuItem().hasSubMenu() ? 1 : 0;
            }
            throw new RuntimeException("Unknown item type.");
        }

        public int getRowCount() {
            int i5 = 0;
            for (int i7 = 0; i7 < NavigationMenuPresenter.this.adapter.getItemCount(); i7++) {
                int itemViewType = NavigationMenuPresenter.this.adapter.getItemViewType(i7);
                if (itemViewType == 0 || itemViewType == 1) {
                    i5++;
                }
            }
            return i5;
        }

        public void restoreInstanceState(Bundle bundle) {
            l menuItem;
            View actionView;
            ParcelableSparseArray parcelableSparseArray;
            l menuItem2;
            int i5 = bundle.getInt(STATE_CHECKED_ITEM, 0);
            if (i5 != 0) {
                this.updateSuspended = true;
                int size = this.items.size();
                int i7 = 0;
                while (true) {
                    if (i7 >= size) {
                        break;
                    }
                    NavigationMenuItem navigationMenuItem = this.items.get(i7);
                    if ((navigationMenuItem instanceof NavigationMenuTextItem) && (menuItem2 = ((NavigationMenuTextItem) navigationMenuItem).getMenuItem()) != null && menuItem2.f6270e == i5) {
                        setCheckedItem(menuItem2);
                        break;
                    }
                    i7++;
                }
                this.updateSuspended = false;
                prepareMenuItems();
            }
            SparseArray sparseParcelableArray = bundle.getSparseParcelableArray(STATE_ACTION_VIEWS);
            if (sparseParcelableArray != null) {
                int size2 = this.items.size();
                for (int i9 = 0; i9 < size2; i9++) {
                    NavigationMenuItem navigationMenuItem2 = this.items.get(i9);
                    if ((navigationMenuItem2 instanceof NavigationMenuTextItem) && (menuItem = ((NavigationMenuTextItem) navigationMenuItem2).getMenuItem()) != null && (actionView = menuItem.getActionView()) != null && (parcelableSparseArray = (ParcelableSparseArray) sparseParcelableArray.get(menuItem.f6270e)) != null) {
                        actionView.restoreHierarchyState(parcelableSparseArray);
                    }
                }
            }
        }

        public void setCheckedItem(l lVar) {
            if (this.checkedItem == lVar || !lVar.isCheckable()) {
                return;
            }
            l lVar2 = this.checkedItem;
            if (lVar2 != null) {
                lVar2.setChecked(false);
            }
            this.checkedItem = lVar;
            lVar.setChecked(true);
        }

        public void setUpdateSuspended(boolean z9) {
            this.updateSuspended = z9;
        }

        public void update() {
            prepareMenuItems();
            notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.AbstractC0341j0
        public void onBindViewHolder(ViewHolder viewHolder, int i5) {
            int itemViewType = getItemViewType(i5);
            if (itemViewType != 0) {
                if (itemViewType != 1) {
                    if (itemViewType != 2) {
                        return;
                    }
                    NavigationMenuSeparatorItem navigationMenuSeparatorItem = (NavigationMenuSeparatorItem) this.items.get(i5);
                    viewHolder.itemView.setPadding(NavigationMenuPresenter.this.dividerInsetStart, navigationMenuSeparatorItem.getPaddingTop(), NavigationMenuPresenter.this.dividerInsetEnd, navigationMenuSeparatorItem.getPaddingBottom());
                    return;
                }
                TextView textView = (TextView) viewHolder.itemView;
                textView.setText(((NavigationMenuTextItem) this.items.get(i5)).getMenuItem().f6273i);
                textView.setTextAppearance(NavigationMenuPresenter.this.subheaderTextAppearance);
                textView.setPadding(NavigationMenuPresenter.this.subheaderInsetStart, textView.getPaddingTop(), NavigationMenuPresenter.this.subheaderInsetEnd, textView.getPaddingBottom());
                ColorStateList colorStateList = NavigationMenuPresenter.this.subheaderColor;
                if (colorStateList != null) {
                    textView.setTextColor(colorStateList);
                }
                setAccessibilityDelegate(textView, i5, true);
                return;
            }
            NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) viewHolder.itemView;
            navigationMenuItemView.setIconTintList(NavigationMenuPresenter.this.iconTintList);
            navigationMenuItemView.setTextAppearance(NavigationMenuPresenter.this.textAppearance);
            ColorStateList colorStateList2 = NavigationMenuPresenter.this.textColor;
            if (colorStateList2 != null) {
                navigationMenuItemView.setTextColor(colorStateList2);
            }
            Drawable drawable = NavigationMenuPresenter.this.itemBackground;
            Drawable drawableNewDrawable = drawable != null ? drawable.getConstantState().newDrawable() : null;
            WeakHashMap weakHashMap = W.f7199a;
            navigationMenuItemView.setBackground(drawableNewDrawable);
            RippleDrawable rippleDrawable = NavigationMenuPresenter.this.itemForeground;
            if (rippleDrawable != null) {
                navigationMenuItemView.setForeground(rippleDrawable.getConstantState().newDrawable());
            }
            NavigationMenuTextItem navigationMenuTextItem = (NavigationMenuTextItem) this.items.get(i5);
            navigationMenuItemView.setNeedsEmptyIcon(navigationMenuTextItem.needsEmptyIcon);
            NavigationMenuPresenter navigationMenuPresenter = NavigationMenuPresenter.this;
            int i7 = navigationMenuPresenter.itemHorizontalPadding;
            int i9 = navigationMenuPresenter.itemVerticalPadding;
            navigationMenuItemView.setPadding(i7, i9, i7, i9);
            navigationMenuItemView.setIconPadding(NavigationMenuPresenter.this.itemIconPadding);
            NavigationMenuPresenter navigationMenuPresenter2 = NavigationMenuPresenter.this;
            if (navigationMenuPresenter2.hasCustomItemIconSize) {
                navigationMenuItemView.setIconSize(navigationMenuPresenter2.itemIconSize);
            }
            navigationMenuItemView.setMaxLines(NavigationMenuPresenter.this.itemMaxLines);
            navigationMenuItemView.initialize(navigationMenuTextItem.getMenuItem(), NavigationMenuPresenter.this.textAppearanceActiveBoldEnabled);
            setAccessibilityDelegate(navigationMenuItemView, i5, false);
        }

        @Override // androidx.recyclerview.widget.AbstractC0341j0
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i5) {
            if (i5 == 0) {
                NavigationMenuPresenter navigationMenuPresenter = NavigationMenuPresenter.this;
                return new NormalViewHolder(navigationMenuPresenter.layoutInflater, viewGroup, navigationMenuPresenter.onClickListener);
            }
            if (i5 == 1) {
                return new SubheaderViewHolder(NavigationMenuPresenter.this.layoutInflater, viewGroup);
            }
            if (i5 == 2) {
                return new SeparatorViewHolder(NavigationMenuPresenter.this.layoutInflater, viewGroup);
            }
            if (i5 != 3) {
                return null;
            }
            return new HeaderViewHolder(NavigationMenuPresenter.this.headerLayout);
        }

        @Override // androidx.recyclerview.widget.AbstractC0341j0
        public void onViewRecycled(ViewHolder viewHolder) {
            if (viewHolder instanceof NormalViewHolder) {
                ((NavigationMenuItemView) viewHolder.itemView).recycle();
            }
        }
    }

    public static class NavigationMenuHeaderItem implements NavigationMenuItem {
    }

    public interface NavigationMenuItem {
    }

    public static class NavigationMenuSeparatorItem implements NavigationMenuItem {
        private final int paddingBottom;
        private final int paddingTop;

        public NavigationMenuSeparatorItem(int i5, int i7) {
            this.paddingTop = i5;
            this.paddingBottom = i7;
        }

        public int getPaddingBottom() {
            return this.paddingBottom;
        }

        public int getPaddingTop() {
            return this.paddingTop;
        }
    }

    public static class NavigationMenuTextItem implements NavigationMenuItem {
        private final l menuItem;
        boolean needsEmptyIcon;

        public NavigationMenuTextItem(l lVar) {
            this.menuItem = lVar;
        }

        public l getMenuItem() {
            return this.menuItem;
        }
    }

    public class NavigationMenuViewAccessibilityDelegate extends X0 {
        public NavigationMenuViewAccessibilityDelegate(RecyclerView recyclerView) {
            super(recyclerView);
        }

        @Override // androidx.recyclerview.widget.X0, androidx.core.view.C0210b
        public void onInitializeAccessibilityNodeInfo(View view, L.l lVar) {
            super.onInitializeAccessibilityNodeInfo(view, lVar);
            AccessibilityNodeInfo.CollectionInfo collectionInfoObtain = AccessibilityNodeInfo.CollectionInfo.obtain(NavigationMenuPresenter.this.adapter.getRowCount(), 1, false);
            lVar.getClass();
            lVar.f1793a.setCollectionInfo(collectionInfoObtain);
        }
    }

    public static class NormalViewHolder extends ViewHolder {
        public NormalViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, View.OnClickListener onClickListener) {
            super(layoutInflater.inflate(R.layout.design_navigation_item, viewGroup, false));
            this.itemView.setOnClickListener(onClickListener);
        }
    }

    public static class SeparatorViewHolder extends ViewHolder {
        public SeparatorViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.design_navigation_item_separator, viewGroup, false));
        }
    }

    public static class SubheaderViewHolder extends ViewHolder {
        public SubheaderViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.design_navigation_item_subheader, viewGroup, false));
        }
    }

    public static abstract class ViewHolder extends V0 {
        public ViewHolder(View view) {
            super(view);
        }
    }

    private boolean hasHeader() {
        return getHeaderCount() > 0;
    }

    private void updateTopPadding() {
        int i5 = (hasHeader() || !this.isBehindStatusBar) ? 0 : this.paddingTopDefault;
        NavigationMenuView navigationMenuView = this.menuView;
        navigationMenuView.setPadding(0, i5, 0, navigationMenuView.getPaddingBottom());
    }

    public void addHeaderView(View view) {
        this.headerLayout.addView(view);
        NavigationMenuView navigationMenuView = this.menuView;
        navigationMenuView.setPadding(0, 0, 0, navigationMenuView.getPaddingBottom());
    }

    @Override // androidx.appcompat.view.menu.w
    public boolean collapseItemActionView(j jVar, l lVar) {
        return false;
    }

    public void dispatchApplyWindowInsets(w0 w0Var) {
        int iD = w0Var.d();
        if (this.paddingTopDefault != iD) {
            this.paddingTopDefault = iD;
            updateTopPadding();
        }
        NavigationMenuView navigationMenuView = this.menuView;
        navigationMenuView.setPadding(0, navigationMenuView.getPaddingTop(), 0, w0Var.a());
        W.b(this.headerLayout, w0Var);
    }

    @Override // androidx.appcompat.view.menu.w
    public boolean expandItemActionView(j jVar, l lVar) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.w
    public boolean flagActionItems() {
        return false;
    }

    public l getCheckedItem() {
        return this.adapter.getCheckedItem();
    }

    public int getDividerInsetEnd() {
        return this.dividerInsetEnd;
    }

    public int getDividerInsetStart() {
        return this.dividerInsetStart;
    }

    public int getHeaderCount() {
        return this.headerLayout.getChildCount();
    }

    public View getHeaderView(int i5) {
        return this.headerLayout.getChildAt(i5);
    }

    @Override // androidx.appcompat.view.menu.w
    public int getId() {
        return this.id;
    }

    public Drawable getItemBackground() {
        return this.itemBackground;
    }

    public int getItemHorizontalPadding() {
        return this.itemHorizontalPadding;
    }

    public int getItemIconPadding() {
        return this.itemIconPadding;
    }

    public int getItemMaxLines() {
        return this.itemMaxLines;
    }

    public ColorStateList getItemTextColor() {
        return this.textColor;
    }

    public ColorStateList getItemTintList() {
        return this.iconTintList;
    }

    public int getItemVerticalPadding() {
        return this.itemVerticalPadding;
    }

    public y getMenuView(ViewGroup viewGroup) {
        if (this.menuView == null) {
            NavigationMenuView navigationMenuView = (NavigationMenuView) this.layoutInflater.inflate(R.layout.design_navigation_menu, viewGroup, false);
            this.menuView = navigationMenuView;
            navigationMenuView.setAccessibilityDelegateCompat(new NavigationMenuViewAccessibilityDelegate(this.menuView));
            if (this.adapter == null) {
                this.adapter = new NavigationMenuAdapter();
            }
            int i5 = this.overScrollMode;
            if (i5 != -1) {
                this.menuView.setOverScrollMode(i5);
            }
            LinearLayout linearLayout = (LinearLayout) this.layoutInflater.inflate(R.layout.design_navigation_item_header, (ViewGroup) this.menuView, false);
            this.headerLayout = linearLayout;
            WeakHashMap weakHashMap = W.f7199a;
            linearLayout.setImportantForAccessibility(2);
            this.menuView.setAdapter(this.adapter);
        }
        return this.menuView;
    }

    public int getSubheaderInsetEnd() {
        return this.subheaderInsetEnd;
    }

    public int getSubheaderInsetStart() {
        return this.subheaderInsetStart;
    }

    public View inflateHeaderView(int i5) {
        View viewInflate = this.layoutInflater.inflate(i5, (ViewGroup) this.headerLayout, false);
        addHeaderView(viewInflate);
        return viewInflate;
    }

    @Override // androidx.appcompat.view.menu.w
    public void initForMenu(Context context, j jVar) {
        this.layoutInflater = LayoutInflater.from(context);
        this.menu = jVar;
        this.paddingSeparator = context.getResources().getDimensionPixelOffset(R.dimen.design_navigation_separator_vertical_padding);
    }

    public boolean isBehindStatusBar() {
        return this.isBehindStatusBar;
    }

    @Override // androidx.appcompat.view.menu.w
    public void onCloseMenu(j jVar, boolean z9) {
        v vVar = this.callback;
        if (vVar != null) {
            vVar.onCloseMenu(jVar, z9);
        }
    }

    @Override // androidx.appcompat.view.menu.w
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(STATE_HIERARCHY);
            if (sparseParcelableArray != null) {
                this.menuView.restoreHierarchyState(sparseParcelableArray);
            }
            Bundle bundle2 = bundle.getBundle(STATE_ADAPTER);
            if (bundle2 != null) {
                this.adapter.restoreInstanceState(bundle2);
            }
            SparseArray<Parcelable> sparseParcelableArray2 = bundle.getSparseParcelableArray(STATE_HEADER);
            if (sparseParcelableArray2 != null) {
                this.headerLayout.restoreHierarchyState(sparseParcelableArray2);
            }
        }
    }

    @Override // androidx.appcompat.view.menu.w
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        if (this.menuView != null) {
            SparseArray<Parcelable> sparseArray = new SparseArray<>();
            this.menuView.saveHierarchyState(sparseArray);
            bundle.putSparseParcelableArray(STATE_HIERARCHY, sparseArray);
        }
        NavigationMenuAdapter navigationMenuAdapter = this.adapter;
        if (navigationMenuAdapter != null) {
            bundle.putBundle(STATE_ADAPTER, navigationMenuAdapter.createInstanceState());
        }
        if (this.headerLayout != null) {
            SparseArray<Parcelable> sparseArray2 = new SparseArray<>();
            this.headerLayout.saveHierarchyState(sparseArray2);
            bundle.putSparseParcelableArray(STATE_HEADER, sparseArray2);
        }
        return bundle;
    }

    @Override // androidx.appcompat.view.menu.w
    public boolean onSubMenuSelected(D d8) {
        return false;
    }

    public void removeHeaderView(View view) {
        this.headerLayout.removeView(view);
        if (hasHeader()) {
            return;
        }
        NavigationMenuView navigationMenuView = this.menuView;
        navigationMenuView.setPadding(0, this.paddingTopDefault, 0, navigationMenuView.getPaddingBottom());
    }

    public void setBehindStatusBar(boolean z9) {
        if (this.isBehindStatusBar != z9) {
            this.isBehindStatusBar = z9;
            updateTopPadding();
        }
    }

    @Override // androidx.appcompat.view.menu.w
    public void setCallback(v vVar) {
        this.callback = vVar;
    }

    public void setCheckedItem(l lVar) {
        this.adapter.setCheckedItem(lVar);
    }

    public void setDividerInsetEnd(int i5) {
        this.dividerInsetEnd = i5;
        updateMenuView(false);
    }

    public void setDividerInsetStart(int i5) {
        this.dividerInsetStart = i5;
        updateMenuView(false);
    }

    public void setId(int i5) {
        this.id = i5;
    }

    public void setItemBackground(Drawable drawable) {
        this.itemBackground = drawable;
        updateMenuView(false);
    }

    public void setItemForeground(RippleDrawable rippleDrawable) {
        this.itemForeground = rippleDrawable;
        updateMenuView(false);
    }

    public void setItemHorizontalPadding(int i5) {
        this.itemHorizontalPadding = i5;
        updateMenuView(false);
    }

    public void setItemIconPadding(int i5) {
        this.itemIconPadding = i5;
        updateMenuView(false);
    }

    public void setItemIconSize(int i5) {
        if (this.itemIconSize != i5) {
            this.itemIconSize = i5;
            this.hasCustomItemIconSize = true;
            updateMenuView(false);
        }
    }

    public void setItemIconTintList(ColorStateList colorStateList) {
        this.iconTintList = colorStateList;
        updateMenuView(false);
    }

    public void setItemMaxLines(int i5) {
        this.itemMaxLines = i5;
        updateMenuView(false);
    }

    public void setItemTextAppearance(int i5) {
        this.textAppearance = i5;
        updateMenuView(false);
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z9) {
        this.textAppearanceActiveBoldEnabled = z9;
        updateMenuView(false);
    }

    public void setItemTextColor(ColorStateList colorStateList) {
        this.textColor = colorStateList;
        updateMenuView(false);
    }

    public void setItemVerticalPadding(int i5) {
        this.itemVerticalPadding = i5;
        updateMenuView(false);
    }

    public void setOverScrollMode(int i5) {
        this.overScrollMode = i5;
        NavigationMenuView navigationMenuView = this.menuView;
        if (navigationMenuView != null) {
            navigationMenuView.setOverScrollMode(i5);
        }
    }

    public void setSubheaderColor(ColorStateList colorStateList) {
        this.subheaderColor = colorStateList;
        updateMenuView(false);
    }

    public void setSubheaderInsetEnd(int i5) {
        this.subheaderInsetEnd = i5;
        updateMenuView(false);
    }

    public void setSubheaderInsetStart(int i5) {
        this.subheaderInsetStart = i5;
        updateMenuView(false);
    }

    public void setSubheaderTextAppearance(int i5) {
        this.subheaderTextAppearance = i5;
        updateMenuView(false);
    }

    public void setUpdateSuspended(boolean z9) {
        NavigationMenuAdapter navigationMenuAdapter = this.adapter;
        if (navigationMenuAdapter != null) {
            navigationMenuAdapter.setUpdateSuspended(z9);
        }
    }

    @Override // androidx.appcompat.view.menu.w
    public void updateMenuView(boolean z9) {
        NavigationMenuAdapter navigationMenuAdapter = this.adapter;
        if (navigationMenuAdapter != null) {
            navigationMenuAdapter.update();
        }
    }
}
