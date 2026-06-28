package androidx.appcompat.widget;

/* JADX INFO: renamed from: androidx.appcompat.widget.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0141d implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6692e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ ActionBarOverlayLayout f6693f;

    public /* synthetic */ RunnableC0141d(ActionBarOverlayLayout actionBarOverlayLayout, int i5) {
        this.f6692e = i5;
        this.f6693f = actionBarOverlayLayout;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f6692e) {
            case 0:
                ActionBarOverlayLayout actionBarOverlayLayout = this.f6693f;
                actionBarOverlayLayout.b();
                actionBarOverlayLayout.f6354A = actionBarOverlayLayout.h.animate().translationY(0.0f).setListener(actionBarOverlayLayout.f6355B);
                break;
            default:
                ActionBarOverlayLayout actionBarOverlayLayout2 = this.f6693f;
                actionBarOverlayLayout2.b();
                actionBarOverlayLayout2.f6354A = actionBarOverlayLayout2.h.animate().translationY(-actionBarOverlayLayout2.h.getHeight()).setListener(actionBarOverlayLayout2.f6355B);
                break;
        }
    }
}
