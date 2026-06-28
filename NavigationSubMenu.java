package com.google.android.material.internal;

import android.content.Context;
import androidx.appcompat.view.menu.D;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.view.menu.l;

/* JADX INFO: loaded from: classes.dex */
public class NavigationSubMenu extends D {
    public NavigationSubMenu(Context context, NavigationMenu navigationMenu, l lVar) {
        super(context, navigationMenu, lVar);
    }

    @Override // androidx.appcompat.view.menu.j
    public void onItemsChanged(boolean z9) {
        super.onItemsChanged(z9);
        ((j) getParentMenu()).onItemsChanged(z9);
    }
}
