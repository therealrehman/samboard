package androidx.media;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import androidx.picker.widget.HandlerC0295o;
import c0.c;
import c0.h;
import h6.AbstractC0582a;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import q.b;

/* JADX INFO: loaded from: classes.dex */
public abstract class MediaBrowserServiceCompat extends Service {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c f7847e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final b f7848f = new b();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final HandlerC0295o f7849g = new HandlerC0295o(this);

    static {
        Log.isLoggable("MBServiceCompat", 3);
    }

    public abstract AbstractC0582a a();

    public abstract void b();

    @Override // android.app.Service
    public final void dump(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Override // android.app.Service
    public final IBinder onBind(Intent intent) {
        return ((h) this.f7847e.f9806b).onBind(intent);
    }

    @Override // android.app.Service
    public final void onCreate() {
        super.onCreate();
        c cVar = new c(this);
        this.f7847e = cVar;
        cVar.e();
    }
}
