package com.google.android.material.internal;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.view.menu.j;
import androidx.appcompat.view.menu.y;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class NavigationMenuView extends RecyclerView implements y {
    public NavigationMenuView(Context context) {
        this(context, null);
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // androidx.appcompat.view.menu.y
    public void initialize(j jVar) {
    }

    public NavigationMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NavigationMenuView(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5);
        setLayoutManager(new LinearLayoutManager(1, false));
    }
}
