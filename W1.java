package androidx.appcompat.widget;

import android.content.Context;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import l.InterfaceC0661c;

/* JADX INFO: loaded from: classes.dex */
public final class W1 implements androidx.appcompat.view.menu.w {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public androidx.appcompat.view.menu.j f6654e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public androidx.appcompat.view.menu.l f6655f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Toolbar f6656g;

    public W1(Toolbar toolbar) {
        this.f6656g = toolbar;
    }

    @Override // androidx.appcompat.view.menu.w
    public final boolean collapseItemActionView(androidx.appcompat.view.menu.j jVar, androidx.appcompat.view.menu.l lVar) {
        Toolbar toolbar = this.f6656g;
        KeyEvent.Callback callback = toolbar.mExpandedActionView;
        if (callback instanceof InterfaceC0661c) {
            ((InterfaceC0661c) callback).c();
        }
        toolbar.removeView(toolbar.mExpandedActionView);
        toolbar.removeView(toolbar.mCollapseButtonView);
        toolbar.mExpandedActionView = null;
        toolbar.addChildrenForExpandedActionView();
        this.f6655f = null;
        toolbar.requestLayout();
        lVar.G = false;
        lVar.f6281r.onItemsChanged(false);
        toolbar.updateBackInvokedCallbackState();
        return true;
    }

    @Override // androidx.appcompat.view.menu.w
    public final boolean expandItemActionView(androidx.appcompat.view.menu.j jVar, androidx.appcompat.view.menu.l lVar) {
        ViewParent parent;
        Toolbar toolbar = this.f6656g;
        toolbar.ensureCollapseButtonView();
        ViewParent parent2 = toolbar.mCollapseButtonView.getParent();
        if (parent2 != toolbar) {
            if (parent2 instanceof ViewGroup) {
                ((ViewGroup) parent2).removeView(toolbar.mCollapseButtonView);
            }
            toolbar.addView(toolbar.mCollapseButtonView);
        }
        View actionView = lVar.getActionView();
        toolbar.mExpandedActionView = actionView;
        this.f6655f = lVar;
        if (actionView != null && (parent = actionView.getParent()) != toolbar) {
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(toolbar.mExpandedActionView);
            }
            X1 x1GenerateDefaultLayoutParams = toolbar.generateDefaultLayoutParams();
            x1GenerateDefaultLayoutParams.f6659a = (toolbar.mButtonGravity & 112) | 8388611;
            x1GenerateDefaultLayoutParams.f6660b = 2;
            toolbar.mExpandedActionView.setLayoutParams(x1GenerateDefaultLayoutParams);
            toolbar.addView(toolbar.mExpandedActionView);
        }
        toolbar.removeChildrenForExpandedActionView();
        toolbar.requestLayout();
        lVar.G = true;
        lVar.f6281r.onItemsChanged(false);
        KeyEvent.Callback callback = toolbar.mExpandedActionView;
        if (callback instanceof InterfaceC0661c) {
            ((InterfaceC0661c) callback).b();
        }
        toolbar.updateBackInvokedCallbackState();
        return true;
    }

    @Override // androidx.appcompat.view.menu.w
    public final boolean flagActionItems() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.w
    public final int getId() {
        return 0;
    }

    @Override // androidx.appcompat.view.menu.w
    public final void initForMenu(Context context, androidx.appcompat.view.menu.j jVar) {
        androidx.appcompat.view.menu.l lVar;
        androidx.appcompat.view.menu.j jVar2 = this.f6654e;
        if (jVar2 != null && (lVar = this.f6655f) != null) {
            jVar2.collapseItemActionView(lVar);
        }
        this.f6654e = jVar;
    }

    @Override // androidx.appcompat.view.menu.w
    public final void onCloseMenu(androidx.appcompat.view.menu.j jVar, boolean z9) {
    }

    @Override // androidx.appcompat.view.menu.w
    public final void onRestoreInstanceState(Parcelable parcelable) {
    }

    @Override // androidx.appcompat.view.menu.w
    public final Parcelable onSaveInstanceState() {
        return null;
    }

    @Override // androidx.appcompat.view.menu.w
    public final boolean onSubMenuSelected(androidx.appcompat.view.menu.D d8) {
        return false;
    }

    @Override // androidx.appcompat.view.menu.w
    public final void updateMenuView(boolean z9) {
        if (this.f6655f != null) {
            androidx.appcompat.view.menu.j jVar = this.f6654e;
            if (jVar != null) {
                int size = jVar.size();
                for (int i5 = 0; i5 < size; i5++) {
                    if (this.f6654e.getItem(i5) == this.f6655f) {
                        return;
                    }
                }
            }
            collapseItemActionView(this.f6654e, this.f6655f);
        }
    }
}
