package X1;

import java.util.concurrent.ThreadFactory;

/* JADX INFO: renamed from: X1.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ThreadFactoryC0106a implements ThreadFactory {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f5497e;

    public /* synthetic */ ThreadFactoryC0106a(int i5) {
        this.f5497e = i5;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        switch (this.f5497e) {
            case 0:
                return new Thread(new A1.a(3, runnable), "glide-active-resources");
            case 1:
                Thread thread = new Thread(runnable);
                thread.setPriority(1);
                thread.setDaemon(true);
                return thread;
            default:
                return new Thread(runnable, "Apollo Dispatcher");
        }
    }
}
