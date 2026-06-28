package androidx.lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import kotlin.Metadata;
import p5.C0828d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Landroidx/lifecycle/LifecycleService;", "Landroid/app/Service;", "Landroidx/lifecycle/s;", "<init>", "()V", "lifecycle-service_release"}, k = 1, mv = {1, 8, 0})
public class LifecycleService extends Service implements InterfaceC0276s {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0828d f7782e = new C0828d(this);

    @Override // androidx.lifecycle.InterfaceC0276s
    public final AbstractC0272n getLifecycle() {
        return (C0278u) this.f7782e.f12718f;
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        kotlin.jvm.internal.j.f(intent, "intent");
        this.f7782e.s(EnumC0270l.ON_START);
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        this.f7782e.s(EnumC0270l.ON_CREATE);
        super.onCreate();
    }

    @Override // android.app.Service
    public final void onDestroy() {
        EnumC0270l enumC0270l = EnumC0270l.ON_STOP;
        C0828d c0828d = this.f7782e;
        c0828d.s(enumC0270l);
        c0828d.s(EnumC0270l.ON_DESTROY);
        super.onDestroy();
    }

    @Override // android.app.Service
    public final void onStart(Intent intent, int i5) {
        this.f7782e.s(EnumC0270l.ON_START);
        super.onStart(intent, i5);
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i5, int i7) {
        return super.onStartCommand(intent, i5, i7);
    }
}
