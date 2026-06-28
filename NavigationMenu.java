package com.google.android.material.internal;

import android.content.Context;
import android.view.SubMenu;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.view.menu.l;

/* JADX INFO: loaded from: classes.dex */
public class NavigationMenu extends j {
    public NavigationMenu(Context context) {
        super(context);
    }

    @Override // androidx.appcompat.view.menu.j, android.view.Menu
    public SubMenu addSubMenu(int i5, int i7, int i9, CharSequence charSequence) {
        l lVar = (l) addInternal(i5, i7, i9, charSequence);
        NavigationSubMenu navigationSubMenu = new NavigationSubMenu(getContext(), this, lVar);
        lVar.f6282s = navigationSubMenu;
        navigationSubMenu.setHeaderTitle(lVar.f6273i);
        return navigationSubMenu;
    }
}
