package androidx.appcompat.widget;

import android.view.MenuItem;

/* JADX INFO: loaded from: classes.dex */
public final class U1 implements androidx.appcompat.view.menu.h, r {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Toolbar f6642e;

    public /* synthetic */ U1(Toolbar toolbar) {
        this.f6642e = toolbar;
    }

    @Override // androidx.appcompat.view.menu.h
    public boolean onMenuItemSelected(androidx.appcompat.view.menu.j jVar, MenuItem menuItem) {
        androidx.appcompat.view.menu.h hVar = this.f6642e.mMenuBuilderCallback;
        return hVar != null && hVar.onMenuItemSelected(jVar, menuItem);
    }

    @Override // androidx.appcompat.view.menu.h
    public void onMenuModeChange(androidx.appcompat.view.menu.j jVar) {
        Toolbar toolbar = this.f6642e;
        C0171n c0171n = toolbar.mMenuView.f6382i;
        if (c0171n == null || !c0171n.isOverflowMenuShowing()) {
            toolbar.mMenuHostHelper.c(jVar);
        }
        androidx.appcompat.view.menu.h hVar = toolbar.mMenuBuilderCallback;
        if (hVar != null) {
            hVar.onMenuModeChange(jVar);
        }
    }
}
