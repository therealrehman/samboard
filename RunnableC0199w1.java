package androidx.appcompat.widget;

/* JADX INFO: renamed from: androidx.appcompat.widget.w1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0199w1 implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6872e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ SeslProgressBar f6873f;

    public /* synthetic */ RunnableC0199w1(SeslProgressBar seslProgressBar, int i5) {
        this.f6872e = i5;
        this.f6873f = seslProgressBar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f6872e) {
            case 0:
                this.f6873f.sendAccessibilityEvent(4);
                return;
            default:
                synchronized (this.f6873f) {
                    try {
                        int size = this.f6873f.f6596W.size();
                        for (int i5 = 0; i5 < size; i5++) {
                            B1 b12 = (B1) this.f6873f.f6596W.get(i5);
                            this.f6873f.e(b12.f6409a, b12.f6410b, b12.f6411c, true, b12.f6412d);
                            B1.f6408e.c(b12);
                        }
                        this.f6873f.f6596W.clear();
                        this.f6873f.f6592S = false;
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                return;
        }
    }
}
