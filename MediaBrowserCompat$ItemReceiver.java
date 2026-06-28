package android.support.v4.media;

import a.C0113b;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.media.session.f;

/* JADX INFO: loaded from: classes.dex */
class MediaBrowserCompat$ItemReceiver extends C0113b {
    @Override // a.C0113b
    public final void c(int i5, Bundle bundle) {
        f.s(bundle);
        if (i5 != 0 || bundle == null || !bundle.containsKey("media_item")) {
            throw null;
        }
        Parcelable parcelable = bundle.getParcelable("media_item");
        if (parcelable != null && !(parcelable instanceof MediaBrowserCompat$MediaItem)) {
            throw null;
        }
        throw null;
    }
}
