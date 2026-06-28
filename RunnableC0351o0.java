package androidx.recyclerview.widget;

/* JADX INFO: renamed from: androidx.recyclerview.widget.o0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0351o0 implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f9203e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ C0353p0 f9204f;

    public /* synthetic */ RunnableC0351o0(C0353p0 c0353p0, int i5) {
        this.f9203e = i5;
        this.f9204f = c0353p0;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f9203e) {
            case 0:
                C0353p0 c0353p0 = this.f9204f;
                if (c0353p0.f9220J.mIndexTip != null) {
                    c0353p0.f9238x = c0353p0.f9237w;
                    c0353p0.invalidate();
                }
                break;
            default:
                C0353p0 c0353p02 = this.f9204f;
                if (c0353p02.f9220J.mIndexTip != null && c0353p02.f9240z) {
                    c0353p02.c();
                    c0353p02.f9240z = false;
                    break;
                }
                break;
        }
    }
}
