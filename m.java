package com.google.android.material.search;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class m implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f10111e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f10112f;

    public /* synthetic */ m(int i5, Object obj) {
        this.f10111e = i5;
        this.f10112f = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i5 = this.f10111e;
        Object obj = this.f10112f;
        switch (i5) {
            case 0:
                ((SearchViewAnimationHelper) obj).lambda$startShowAnimationExpand$0();
                break;
            case 1:
                ((SearchViewAnimationHelper) obj).lambda$startShowAnimationTranslate$1();
                break;
            default:
                ((SearchBar) obj).lambda$startOnLoadAnimation$1();
                break;
        }
    }
}
