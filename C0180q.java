package androidx.appcompat.widget;

import android.view.MenuItem;
import androidx.appcompat.view.menu.AbstractC0131d;
import androidx.core.view.InterfaceC0221m;
import java.util.Iterator;

/* JADX INFO: renamed from: androidx.appcompat.widget.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0180q implements androidx.appcompat.view.menu.h, androidx.appcompat.view.menu.v {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f6836e;

    public /* synthetic */ C0180q(Object obj) {
        this.f6836e = obj;
    }

    @Override // androidx.appcompat.view.menu.v
    public void onCloseMenu(androidx.appcompat.view.menu.j jVar, boolean z9) {
        if (jVar instanceof androidx.appcompat.view.menu.D) {
            jVar.getRootMenu().close(false);
        }
        androidx.appcompat.view.menu.v callback = ((C0171n) this.f6836e).getCallback();
        if (callback != null) {
            callback.onCloseMenu(jVar, z9);
        }
    }

    @Override // androidx.appcompat.view.menu.h
    public boolean onMenuItemSelected(androidx.appcompat.view.menu.j jVar, MenuItem menuItem) {
        boolean z9;
        boolean zOnMenuItemSelected;
        r rVar = ((ActionMenuView) this.f6836e).f6388p;
        if (rVar == null) {
            return false;
        }
        Toolbar toolbar = ((U1) rVar).f6642e;
        Iterator it = toolbar.mMenuHostHelper.f7236b.iterator();
        while (true) {
            if (!it.hasNext()) {
                z9 = false;
                break;
            }
            if (((InterfaceC0221m) it.next()).a(menuItem)) {
                z9 = true;
                break;
            }
        }
        if (z9) {
            zOnMenuItemSelected = true;
        } else {
            Y1 y12 = toolbar.mOnMenuItemClickListener;
            zOnMenuItemSelected = y12 != null ? ((g.K) ((B8.e) y12).f286f).f11019b.onMenuItemSelected(0, menuItem) : false;
        }
        return zOnMenuItemSelected;
    }

    @Override // androidx.appcompat.view.menu.h
    public void onMenuModeChange(androidx.appcompat.view.menu.j jVar) {
        androidx.appcompat.view.menu.h hVar = ((ActionMenuView) this.f6836e).f6384k;
        if (hVar != null) {
            hVar.onMenuModeChange(jVar);
        }
    }

    @Override // androidx.appcompat.view.menu.v
    public boolean onOpenSubMenu(androidx.appcompat.view.menu.j jVar) {
        C0171n c0171n = (C0171n) this.f6836e;
        if (jVar == ((AbstractC0131d) c0171n).mMenu) {
            return false;
        }
        c0171n.f6775t = ((androidx.appcompat.view.menu.D) jVar).getItem().getItemId();
        androidx.appcompat.view.menu.v callback = c0171n.getCallback();
        if (callback != null) {
            return callback.onOpenSubMenu(jVar);
        }
        return false;
    }
}
