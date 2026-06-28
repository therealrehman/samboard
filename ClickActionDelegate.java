package com.google.android.material.timepicker;

import L.f;
import L.l;
import android.content.Context;
import android.view.View;
import androidx.core.view.C0210b;

/* JADX INFO: loaded from: classes.dex */
class ClickActionDelegate extends C0210b {
    private final f clickAction;

    public ClickActionDelegate(Context context, int i5) {
        this.clickAction = new f(16, context.getString(i5));
    }

    @Override // androidx.core.view.C0210b
    public void onInitializeAccessibilityNodeInfo(View view, l lVar) {
        super.onInitializeAccessibilityNodeInfo(view, lVar);
        lVar.b(this.clickAction);
    }
}
