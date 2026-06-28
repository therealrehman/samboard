package android.support.v4.media;

import a.C0113b;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.media.session.f;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
class MediaBrowserCompat$SearchResultReceiver extends C0113b {
    @Override // a.C0113b
    public final void c(int i5, Bundle bundle) {
        Parcelable[] parcelableArray;
        f.s(bundle);
        if (i5 != 0 || bundle == null || !bundle.containsKey("search_results") || (parcelableArray = bundle.getParcelableArray("search_results")) == null) {
            throw null;
        }
        ArrayList arrayList = new ArrayList();
        for (Parcelable parcelable : parcelableArray) {
            arrayList.add((MediaBrowserCompat$MediaItem) parcelable);
        }
        throw null;
    }
}
