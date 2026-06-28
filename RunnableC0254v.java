package androidx.fragment.app;

/* JADX INFO: renamed from: androidx.fragment.app.v, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0254v implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f7734e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Fragment f7735f;

    public /* synthetic */ RunnableC0254v(Fragment fragment, int i5) {
        this.f7734e = i5;
        this.f7735f = fragment;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f7734e) {
            case 0:
                this.f7735f.startPostponedEnterTransition();
                break;
            default:
                this.f7735f.callStartTransitionListener(false);
                break;
        }
    }
}
