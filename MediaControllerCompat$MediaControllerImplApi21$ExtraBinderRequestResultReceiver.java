package android.support.v4.media.session;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.ResultReceiver;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
class MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver extends ResultReceiver {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public WeakReference f6046e;

    @Override // android.os.ResultReceiver
    public final void onReceiveResult(int i5, Bundle bundle) {
        c cVar;
        e eVar = (e) this.f6046e.get();
        if (eVar == null || bundle == null) {
            return;
        }
        synchronized (eVar.f6073b) {
            MediaSessionCompat$Token mediaSessionCompat$Token = eVar.f6076e;
            IBinder binder = bundle.getBinder("android.support.v4.media.session.EXTRA_BINDER");
            int i7 = b.f6069d;
            if (binder == null) {
                cVar = null;
            } else {
                IInterface iInterfaceQueryLocalInterface = binder.queryLocalInterface("android.support.v4.media.session.IMediaSession");
                if (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof c)) {
                    a aVar = new a();
                    aVar.f6068d = binder;
                    cVar = aVar;
                } else {
                    cVar = (c) iInterfaceQueryLocalInterface;
                }
            }
            mediaSessionCompat$Token.f6051f = cVar;
            MediaSessionCompat$Token mediaSessionCompat$Token2 = eVar.f6076e;
            bundle.getBundle("android.support.v4.media.session.SESSION_TOKEN2_BUNDLE");
            mediaSessionCompat$Token2.getClass();
            eVar.a();
        }
    }
}
