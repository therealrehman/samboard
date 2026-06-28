package c0;

import android.media.session.MediaSessionManager;
import android.os.IBinder;
import androidx.media.MediaBrowserServiceCompat;
import java.util.HashMap;

/* JADX INFO: renamed from: c0.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0432a implements IBinder.DeathRecipient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f9801a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final M3.g f9802b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashMap f9803c = new HashMap();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ MediaBrowserServiceCompat f9804d;

    public C0432a(MediaBrowserServiceCompat mediaBrowserServiceCompat, String str, int i5, int i7, M3.g gVar) {
        this.f9804d = mediaBrowserServiceCompat;
        this.f9801a = str;
        new MediaSessionManager.RemoteUserInfo(str, i5, i7);
        this.f9802b = gVar;
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
        this.f9804d.f7849g.post(new A1.a(12, this));
    }
}
