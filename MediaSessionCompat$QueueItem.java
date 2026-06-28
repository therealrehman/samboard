package android.support.v4.media.session;

import P.n;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompat;

/* JADX INFO: loaded from: classes.dex */
public final class MediaSessionCompat$QueueItem implements Parcelable {
    public static final Parcelable.Creator<MediaSessionCompat$QueueItem> CREATOR = new n(6);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final MediaDescriptionCompat f6047e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f6048f;

    public MediaSessionCompat$QueueItem(Parcel parcel) {
        this.f6047e = MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        this.f6048f = parcel.readLong();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return "MediaSession.QueueItem {Description=" + this.f6047e + ", Id=" + this.f6048f + " }";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        this.f6047e.writeToParcel(parcel, i5);
        parcel.writeLong(this.f6048f);
    }
}
