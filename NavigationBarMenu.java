package com.google.android.material.navigation;

import android.content.Context;
import android.view.MenuItem;
import android.view.SubMenu;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.view.menu.l;

/* JADX INFO: loaded from: classes.dex */
public final class NavigationBarMenu extends j {
    private final int maxItemCount;
    private final Class<?> viewClass;

    public NavigationBarMenu(Context context, Class<?> cls, int i5) {
        super(context);
        this.viewClass = cls;
        this.maxItemCount = i5;
    }

    @Override // androidx.appcompat.view.menu.j
    public MenuItem addInternal(int i5, int i7, int i9, CharSequence charSequence) {
        stopDispatchingItemsChanged();
        MenuItem menuItemAddInternal = super.addInternal(i5, i7, i9, charSequence);
        if (menuItemAddInternal instanceof l) {
            ((l) menuItemAddInternal).i(true);
        }
        startDispatchingItemsChanged();
        return menuItemAddInternal;
    }

    @Override // androidx.appcompat.view.menu.j, android.view.Menu
    public SubMenu addSubMenu(int i5, int i7, int i9, CharSequence charSequence) {
        throw new UnsupportedOperationException(this.viewClass.getSimpleName().concat(" does not support submenus"));
    }

    public int getMaxItemCount() {
        return this.maxItemCount;
    }
}
