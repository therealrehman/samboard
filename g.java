package com.google.android.material.search;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class g implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f10101e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ SearchView f10102f;

    public /* synthetic */ g(SearchView searchView, int i5) {
        this.f10101e = i5;
        this.f10102f = searchView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i5 = this.f10101e;
        SearchView searchView = this.f10102f;
        switch (i5) {
            case 0:
                searchView.lambda$clearFocusAndHideKeyboard$9();
                break;
            case 1:
                searchView.lambda$requestFocusAndShowKeyboard$8();
                break;
            case 2:
                searchView.show();
                break;
            default:
                searchView.requestFocusAndShowKeyboardIfNeeded();
                break;
        }
    }
}
