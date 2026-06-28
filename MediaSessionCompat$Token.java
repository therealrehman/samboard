package android.support.v4.media.session;

import P.n;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class MediaSessionCompat$Token implements Parcelable {
    public static final Parcelable.Creator<MediaSessionCompat$Token> CREATOR = new n(8);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f6050e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public c f6051f;

    public MediaSessionCompat$Token(Parcelable parcelable, c cVar) {
        this.f6050e = parcelable;
        this.f6051f = cVar;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaSessionCompat$Token)) {
            return false;
        }
        MediaSessionCompat$Token mediaSessionCompat$Token = (MediaSessionCompat$Token) obj;
        Object obj2 = this.f6050e;
        if (obj2 == null) {
            return mediaSessionCompat$Token.f6050e == null;
        }
        Object obj3 = mediaSessionCompat$Token.f6050e;
        if (obj3 == null) {
            return false;
        }
        return obj2.equals(obj3);
    }

    public final int hashCode() {
        Object obj = this.f6050e;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        parcel.writeParcelable((Parcelable) this.f6050e, i5);
    }
}
