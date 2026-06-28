package androidx.appcompat.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.keyscafe.R;
import java.util.ArrayList;

/* JADX INFO: renamed from: androidx.appcompat.view.menu.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0131d implements w {
    private v mCallback;
    protected Context mContext;
    private int mId;
    protected LayoutInflater mInflater;
    protected j mMenu;
    protected y mMenuView;
    protected Context mSystemContext;
    protected LayoutInflater mSystemInflater;
    private int mMenuLayoutRes = R.layout.sesl_action_menu_layout;
    private int mItemLayoutRes = R.layout.sesl_action_menu_item_layout;

    public AbstractC0131d(Context context) {
        this.mSystemContext = context;
        this.mSystemInflater = LayoutInflater.from(context);
    }

    public void addItemView(View view, int i5) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
        ((ViewGroup) this.mMenuView).addView(view, i5);
    }

    public abstract void bindItemView(l lVar, x xVar);

    @Override // androidx.appcompat.view.menu.w
    public boolean collapseItemActionView(j jVar, l lVar) {
        return false;
    }

    public x createItemView(ViewGroup viewGroup) {
        return (x) this.mSystemInflater.inflate(this.mItemLayoutRes, viewGroup, false);
    }

    @Override // androidx.appcompat.view.menu.w
    public boolean expandItemActionView(j jVar, l lVar) {
        return false;
    }

    public boolean filterLeftoverView(ViewGroup viewGroup, int i5) {
        viewGroup.removeViewAt(i5);
        return true;
    }

    public v getCallback() {
        return this.mCallback;
    }

    @Override // androidx.appcompat.view.menu.w
    public int getId() {
        return this.mId;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public View getItemView(l lVar, View view, ViewGroup viewGroup) {
        x xVarCreateItemView = view instanceof x ? (x) view : createItemView(viewGroup);
        bindItemView(lVar, xVarCreateItemView);
        return (View) xVarCreateItemView;
    }

    public y getMenuView(ViewGroup viewGroup) {
        if (this.mMenuView == null) {
            y yVar = (y) this.mSystemInflater.inflate(this.mMenuLayoutRes, viewGroup, false);
            this.mMenuView = yVar;
            yVar.initialize(this.mMenu);
            updateMenuView(true);
        }
        return this.mMenuView;
    }

    @Override // androidx.appcompat.view.menu.w
    public void initForMenu(Context context, j jVar) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mMenu = jVar;
    }

    @Override // androidx.appcompat.view.menu.w
    public void onCloseMenu(j jVar, boolean z9) {
        v vVar = this.mCallback;
        if (vVar != null) {
            vVar.onCloseMenu(jVar, z9);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // androidx.appcompat.view.menu.w
    public boolean onSubMenuSelected(D d8) {
        v vVar = this.mCallback;
        j jVar = d8;
        if (vVar == null) {
            return false;
        }
        if (d8 == null) {
            jVar = this.mMenu;
        }
        return vVar.onOpenSubMenu(jVar);
    }

    @Override // androidx.appcompat.view.menu.w
    public void setCallback(v vVar) {
        this.mCallback = vVar;
    }

    public void setId(int i5) {
        this.mId = R.id.action_menu_presenter;
    }

    public boolean shouldIncludeItem(int i5, l lVar) {
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.appcompat.view.menu.w
    public void updateMenuView(boolean z9) {
        ViewGroup viewGroup = (ViewGroup) this.mMenuView;
        if (viewGroup == null) {
            return;
        }
        j jVar = this.mMenu;
        int i5 = 0;
        if (jVar != null) {
            jVar.flagActionItems();
            ArrayList<l> visibleItems = this.mMenu.getVisibleItems();
            int size = visibleItems.size();
            int i7 = 0;
            for (int i9 = 0; i9 < size; i9++) {
                l lVar = visibleItems.get(i9);
                if (shouldIncludeItem(i7, lVar)) {
                    View childAt = viewGroup.getChildAt(i7);
                    l itemData = childAt instanceof x ? ((x) childAt).getItemData() : null;
                    View itemView = getItemView(lVar, childAt, viewGroup);
                    if (lVar != itemData) {
                        itemView.setPressed(false);
                        itemView.jumpDrawablesToCurrentState();
                    }
                    if (itemView != childAt) {
                        addItemView(itemView, i7);
                    }
                    i7++;
                }
            }
            i5 = i7;
        }
        while (i5 < viewGroup.getChildCount()) {
            if (!filterLeftoverView(viewGroup, i5)) {
                i5++;
            }
        }
    }
}
