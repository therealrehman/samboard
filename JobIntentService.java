package androidx.core.app;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.util.HashMap;
import z.i;
import z.j;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class JobIntentService extends Service {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public j f7178e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public i f7179f;

    static {
        new HashMap();
    }

    public abstract void a();

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        j jVar = this.f7178e;
        if (jVar != null) {
            return jVar.getBinder();
        }
        return null;
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        this.f7178e = new j(this);
    }

    @Override // android.app.Service
    public final void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public final int onStartCommand(Intent intent, int i5, int i7) {
        return 2;
    }
}
