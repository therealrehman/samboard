package android.support.v4.media.session;

import P.n;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class ParcelableVolumeInfo implements Parcelable {
    public static final Parcelable.Creator<ParcelableVolumeInfo> CREATOR = new n(9);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f6052e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f6053f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f6054g;
    public int h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f6055i;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        parcel.writeInt(this.f6052e);
        parcel.writeInt(this.f6054g);
        parcel.writeInt(this.h);
        parcel.writeInt(this.f6055i);
        parcel.writeInt(this.f6053f);
    }
}
