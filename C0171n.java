package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.view.menu.AbstractC0131d;
import androidx.appcompat.view.menu.ActionMenuItemView;
import com.samsung.android.keyscafe.R;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/* JADX INFO: renamed from: androidx.appcompat.widget.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0171n extends AbstractC0131d {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public C0162k f6762e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Drawable f6763f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f6764g;
    public boolean h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f6765i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f6766j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f6767k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f6768l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f6769m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final SparseBooleanArray f6770n;
    public C0150g o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public C0150g f6771p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public RunnableC0156i f6772q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public C0153h f6773r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final C0180q f6774s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public int f6775t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final boolean f6776u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final NumberFormat f6777v;

    public C0171n(Context context) {
        super(context);
        this.f6770n = new SparseBooleanArray();
        this.f6774s = new C0180q(this);
        this.f6777v = NumberFormat.getInstance(Locale.getDefault());
        this.f6776u = context.getResources().getBoolean(R.bool.sesl_action_bar_text_item_mode);
    }

    @Override // androidx.appcompat.view.menu.AbstractC0131d
    public final void bindItemView(androidx.appcompat.view.menu.l lVar, androidx.appcompat.view.menu.x xVar) {
        xVar.initialize(lVar, 0);
        ActionMenuItemView actionMenuItemView = (ActionMenuItemView) xVar;
        actionMenuItemView.setItemInvoker((ActionMenuView) this.mMenuView);
        if (this.f6773r == null) {
            this.f6773r = new C0153h(this);
        }
        actionMenuItemView.setPopupCallback(this.f6773r);
    }

    @Override // androidx.appcompat.view.menu.AbstractC0131d
    public final boolean filterLeftoverView(ViewGroup viewGroup, int i5) {
        if (viewGroup.getChildAt(i5) == this.f6762e) {
            return false;
        }
        return super.filterLeftoverView(viewGroup, i5);
    }

    @Override // androidx.appcompat.view.menu.w
    public final boolean flagActionItems() {
        int size;
        ArrayList<androidx.appcompat.view.menu.l> visibleItems;
        int i5;
        boolean z9;
        androidx.appcompat.view.menu.j jVar = this.mMenu;
        if (jVar != null) {
            visibleItems = jVar.getVisibleItems();
            size = visibleItems.size();
        } else {
            size = 0;
            visibleItems = null;
        }
        int i7 = this.f6768l;
        int i9 = this.f6767k;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        Object obj = this.mMenuView;
        if (obj == null) {
            Log.d("ActionMenuPresenter", "mMenuView is null, maybe Menu has not been initialized.");
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) obj;
        int i10 = 0;
        boolean z10 = false;
        int i11 = 0;
        int i12 = 0;
        while (true) {
            i5 = 2;
            z9 = true;
            if (i10 >= size) {
                break;
            }
            androidx.appcompat.view.menu.l lVar = visibleItems.get(i10);
            int i13 = lVar.f6263C;
            if ((i13 & 2) == 2) {
                i11++;
            } else if ((i13 & 1) == 1) {
                i12++;
            } else {
                z10 = true;
            }
            if (this.f6769m && lVar.G) {
                i7 = 0;
            }
            i10++;
        }
        if (this.h && (z10 || i12 + i11 > i7)) {
            i7--;
        }
        int i14 = i7 - i11;
        SparseBooleanArray sparseBooleanArray = this.f6770n;
        sparseBooleanArray.clear();
        int i15 = 0;
        int i16 = 0;
        while (i15 < size) {
            androidx.appcompat.view.menu.l lVar2 = visibleItems.get(i15);
            int i17 = lVar2.f6263C;
            boolean z11 = (i17 & 2) == i5 ? z9 : false;
            int i18 = lVar2.f6271f;
            if (z11) {
                View itemView = getItemView(lVar2, null, viewGroup);
                itemView.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                int measuredWidth = itemView.getMeasuredWidth();
                i9 -= measuredWidth;
                if (i16 == 0) {
                    i16 = measuredWidth;
                }
                if (i18 != 0) {
                    sparseBooleanArray.put(i18, z9);
                }
                lVar2.j(z9);
            } else if ((i17 & 1) == z9) {
                boolean z12 = sparseBooleanArray.get(i18);
                boolean z13 = ((i14 > 0 || z12) && i9 > 0) ? z9 : false;
                if (z13) {
                    View itemView2 = getItemView(lVar2, null, viewGroup);
                    itemView2.measure(iMakeMeasureSpec, iMakeMeasureSpec);
                    int measuredWidth2 = itemView2.getMeasuredWidth();
                    i9 -= measuredWidth2;
                    if (i16 == 0) {
                        i16 = measuredWidth2;
                    }
                    z13 &= i9 >= 0;
                }
                if (z13 && i18 != 0) {
                    sparseBooleanArray.put(i18, true);
                } else if (z12) {
                    sparseBooleanArray.put(i18, false);
                    for (int i19 = 0; i19 < i15; i19++) {
                        androidx.appcompat.view.menu.l lVar3 = visibleItems.get(i19);
                        if (lVar3.f6271f == i18) {
                            if (lVar3.g()) {
                                i14++;
                            }
                            lVar3.j(false);
                        }
                    }
                }
                if (z13) {
                    i14--;
                }
                lVar2.j(z13);
            } else {
                lVar2.j(false);
                i15++;
                i5 = 2;
                z9 = true;
            }
            i15++;
            i5 = 2;
            z9 = true;
        }
        return z9;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0131d
    public final View getItemView(androidx.appcompat.view.menu.l lVar, View view, ViewGroup viewGroup) {
        View actionView = lVar.getActionView();
        if (actionView == null || lVar.f()) {
            actionView = super.getItemView(lVar, view, viewGroup);
        }
        actionView.setVisibility(lVar.G ? 8 : 0);
        ViewGroup.LayoutParams layoutParams = actionView.getLayoutParams();
        ((ActionMenuView) viewGroup).getClass();
        if (!(layoutParams instanceof C0177p)) {
            actionView.setLayoutParams(ActionMenuView.e(layoutParams));
        }
        return actionView;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0131d
    public final androidx.appcompat.view.menu.y getMenuView(ViewGroup viewGroup) {
        androidx.appcompat.view.menu.y yVar = this.mMenuView;
        androidx.appcompat.view.menu.y menuView = super.getMenuView(viewGroup);
        if (yVar != menuView) {
            ((ActionMenuView) menuView).setPresenter(this);
        }
        return menuView;
    }

    public final boolean hideOverflowMenu() {
        Object obj;
        RunnableC0156i runnableC0156i = this.f6772q;
        if (runnableC0156i != null && (obj = this.mMenuView) != null) {
            ((View) obj).removeCallbacks(runnableC0156i);
            this.f6772q = null;
            return true;
        }
        C0150g c0150g = this.o;
        if (c0150g == null) {
            return false;
        }
        c0150g.dismiss();
        return true;
    }

    public final void i() {
        C0162k c0162k;
        Configuration configuration = this.mContext.getResources().getConfiguration();
        int i5 = configuration.screenWidthDp;
        int i7 = configuration.screenHeightDp;
        this.f6768l = (configuration.smallestScreenWidthDp > 600 || i5 > 600 || (i5 > 960 && i7 > 720) || (i5 > 720 && i7 > 960)) ? 5 : (i5 >= 500 || (i5 > 640 && i7 > 480) || (i5 > 480 && i7 > 640)) ? 4 : i5 >= 360 ? 3 : 2;
        int i9 = (int) (r0.getResources().getDisplayMetrics().widthPixels * 0.7f);
        this.f6766j = i9;
        if (!this.h || (c0162k = this.f6762e) == null) {
            this.f6767k = i9;
        } else {
            this.f6767k = i9 - c0162k.getMeasuredWidth();
        }
        androidx.appcompat.view.menu.j jVar = this.mMenu;
        if (jVar != null) {
            jVar.onItemsChanged(true);
        }
    }

    @Override // androidx.appcompat.view.menu.AbstractC0131d, androidx.appcompat.view.menu.w
    public final void initForMenu(Context context, androidx.appcompat.view.menu.j jVar) {
        super.initForMenu(context, jVar);
        Resources resources = context.getResources();
        if (!this.f6765i) {
            this.h = true;
        }
        this.f6766j = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.7f);
        Configuration configuration = context.getResources().getConfiguration();
        int i5 = configuration.screenWidthDp;
        int i7 = configuration.screenHeightDp;
        this.f6768l = (configuration.smallestScreenWidthDp > 600 || i5 > 600 || (i5 > 960 && i7 > 720) || (i5 > 720 && i7 > 960)) ? 5 : (i5 >= 500 || (i5 > 640 && i7 > 480) || (i5 > 480 && i7 > 640)) ? 4 : i5 >= 360 ? 3 : 2;
        int measuredWidth = this.f6766j;
        if (this.h) {
            if (this.f6762e == null) {
                C0162k c0162k = new C0162k(this, this.mSystemContext);
                this.f6762e = c0162k;
                c0162k.setId(R.id.sesl_action_bar_overflow_button);
                if (this.f6764g) {
                    if (this.f6776u) {
                        ((AppCompatImageView) this.f6762e.f6730g).setImageDrawable(this.f6763f);
                    }
                    this.f6763f = null;
                    this.f6764g = false;
                }
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                this.f6762e.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            }
            measuredWidth -= this.f6762e.getMeasuredWidth();
        } else {
            this.f6762e = null;
        }
        this.f6767k = measuredWidth;
        float f2 = resources.getDisplayMetrics().density;
    }

    public final boolean isOverflowMenuShowing() {
        C0150g c0150g = this.o;
        return c0150g != null && c0150g.isShowing();
    }

    public final void j(ActionMenuView actionMenuView) {
        this.mMenuView = actionMenuView;
        actionMenuView.f6379e = this.mMenu;
    }

    public final boolean k() {
        androidx.appcompat.view.menu.j jVar;
        if (!this.h || isOverflowMenuShowing() || (jVar = this.mMenu) == null || this.mMenuView == null || this.f6772q != null || jVar.getNonActionItems().isEmpty()) {
            return false;
        }
        RunnableC0156i runnableC0156i = new RunnableC0156i(this, new C0150g(this, this.mContext, this.mMenu, this.f6762e));
        this.f6772q = runnableC0156i;
        ((View) this.mMenuView).post(runnableC0156i);
        return true;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0131d, androidx.appcompat.view.menu.w
    public final void onCloseMenu(androidx.appcompat.view.menu.j jVar, boolean z9) {
        hideOverflowMenu();
        C0150g c0150g = this.f6771p;
        if (c0150g != null) {
            c0150g.dismiss();
        }
        super.onCloseMenu(jVar, z9);
    }

    @Override // androidx.appcompat.view.menu.w
    public final void onRestoreInstanceState(Parcelable parcelable) {
        int i5;
        androidx.appcompat.view.menu.j jVar;
        MenuItem menuItemFindItem;
        if ((parcelable instanceof C0168m) && (i5 = ((C0168m) parcelable).f6756e) > 0 && (jVar = this.mMenu) != null && (menuItemFindItem = jVar.findItem(i5)) != null) {
            onSubMenuSelected((androidx.appcompat.view.menu.D) menuItemFindItem.getSubMenu());
        }
    }

    @Override // androidx.appcompat.view.menu.w
    public final Parcelable onSaveInstanceState() {
        C0168m c0168m = new C0168m();
        c0168m.f6756e = this.f6775t;
        return c0168m;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.appcompat.view.menu.AbstractC0131d, androidx.appcompat.view.menu.w
    public final boolean onSubMenuSelected(androidx.appcompat.view.menu.D d8) {
        boolean z9 = false;
        if (d8 == null || !d8.hasVisibleItems()) {
            return false;
        }
        androidx.appcompat.view.menu.D d10 = d8;
        while (d10.getParentMenu() != this.mMenu) {
            d10 = (androidx.appcompat.view.menu.D) d10.getParentMenu();
        }
        MenuItem item = d10.getItem();
        ViewGroup viewGroup = (ViewGroup) this.mMenuView;
        View view = null;
        view = null;
        if (viewGroup != null) {
            int childCount = viewGroup.getChildCount();
            int i5 = 0;
            while (true) {
                if (i5 >= childCount) {
                    break;
                }
                View childAt = viewGroup.getChildAt(i5);
                if ((childAt instanceof androidx.appcompat.view.menu.x) && ((androidx.appcompat.view.menu.x) childAt).getItemData() == item) {
                    view = childAt;
                    break;
                }
                i5++;
            }
        }
        if (view == null) {
            return false;
        }
        this.f6775t = d8.getItem().getItemId();
        int size = d8.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size) {
                break;
            }
            MenuItem item2 = d8.getItem(i7);
            if (item2.isVisible() && item2.getIcon() != null) {
                z9 = true;
                break;
            }
            i7++;
        }
        C0150g c0150g = new C0150g(this, this.mContext, d8, view);
        this.f6771p = c0150g;
        c0150g.setForceShowIcon(z9);
        this.f6771p.show();
        super.onSubMenuSelected(d8);
        return true;
    }

    @Override // androidx.appcompat.view.menu.AbstractC0131d
    public final boolean shouldIncludeItem(int i5, androidx.appcompat.view.menu.l lVar) {
        return lVar.g();
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004c  */
    @Override // androidx.appcompat.view.menu.AbstractC0131d, androidx.appcompat.view.menu.w
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void updateMenuView(boolean r11) {
        /*
            Method dump skipped, instruction units count: 391
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.C0171n.updateMenuView(boolean):void");
    }
}
