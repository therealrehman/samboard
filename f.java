package com.google.android.material.search;

import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class f implements View.OnClickListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f10099e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ SearchView f10100f;

    public /* synthetic */ f(SearchView searchView, int i5) {
        this.f10099e = i5;
        this.f10100f = searchView;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i5 = this.f10099e;
        SearchView searchView = this.f10100f;
        switch (i5) {
            case 0:
                searchView.lambda$setUpBackButton$1(view);
                break;
            case 1:
                searchView.lambda$setUpClearButton$2(view);
                break;
            default:
                searchView.lambda$setupWithSearchBar$7(view);
                break;
        }
    }
}
