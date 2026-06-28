package androidx.room;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/* JADX INFO: loaded from: classes.dex */
public class MultiInstanceInvalidationService extends Service {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f9301e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final q.l f9302f = new q.l();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final h f9303g = new h(this);
    public final android.support.v4.media.session.d h = new android.support.v4.media.session.d(this);

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return this.h;
    }
}
