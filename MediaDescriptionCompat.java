package android.support.v4.media;

import P.n;
import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new n(3);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f6023e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final CharSequence f6024f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final CharSequence f6025g;
    public final CharSequence h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Bitmap f6026i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Uri f6027j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final Bundle f6028k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Uri f6029l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f6030m;

    public MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.f6023e = str;
        this.f6024f = charSequence;
        this.f6025g = charSequence2;
        this.h = charSequence3;
        this.f6026i = bitmap;
        this.f6027j = uri;
        this.f6028k = bundle;
        this.f6029l = uri2;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return ((Object) this.f6024f) + ", " + ((Object) this.f6025g) + ", " + ((Object) this.h);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        Object objBuild = this.f6030m;
        if (objBuild == null) {
            MediaDescription.Builder builder = new MediaDescription.Builder();
            builder.setMediaId(this.f6023e);
            builder.setTitle(this.f6024f);
            builder.setSubtitle(this.f6025g);
            builder.setDescription(this.h);
            builder.setIconBitmap(this.f6026i);
            builder.setIconUri(this.f6027j);
            builder.setExtras(this.f6028k);
            builder.setMediaUri(this.f6029l);
            objBuild = builder.build();
            this.f6030m = objBuild;
        }
        ((MediaDescription) objBuild).writeToParcel(parcel, i5);
    }
}
