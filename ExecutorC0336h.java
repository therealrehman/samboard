package androidx.recyclerview.widget;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* JADX INFO: renamed from: androidx.recyclerview.widget.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ExecutorC0336h implements Executor {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Handler f9145e = new Handler(Looper.getMainLooper());

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.f9145e.post(runnable);
    }
}
