package com.google.android.material.search;

import android.view.View;
import androidx.core.view.InterfaceC0226s;
import androidx.core.view.w0;
import com.google.android.material.internal.ViewUtils;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class i implements ViewUtils.OnApplyWindowInsetsListener, InterfaceC0226s {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ SearchView f10106e;

    public /* synthetic */ i(SearchView searchView) {
        this.f10106e = searchView;
    }

    @Override // androidx.core.view.InterfaceC0226s
    public w0 onApplyWindowInsets(View view, w0 w0Var) {
        return this.f10106e.lambda$setUpStatusBarSpacerInsetListener$5(view, w0Var);
    }

    @Override // com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener
    public w0 onApplyWindowInsets(View view, w0 w0Var, ViewUtils.RelativePadding relativePadding) {
        return this.f10106e.lambda$setUpToolbarInsetListener$4(view, w0Var, relativePadding);
    }
}
