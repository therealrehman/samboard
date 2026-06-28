package androidx.appcompat.widget;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class T1 implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6636e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Toolbar f6637f;

    public /* synthetic */ T1(Toolbar toolbar, int i5) {
        this.f6636e = i5;
        this.f6637f = toolbar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i5 = this.f6636e;
        Toolbar toolbar = this.f6637f;
        switch (i5) {
            case 0:
                toolbar.collapseActionView();
                break;
            default:
                toolbar.invalidateMenu();
                break;
        }
    }
}
