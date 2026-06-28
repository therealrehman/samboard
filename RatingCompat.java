package android.support.v4.media;

import P.n;
import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class RatingCompat implements Parcelable {
    public static final Parcelable.Creator<RatingCompat> CREATOR = new n(5);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f6032e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f6033f;

    public RatingCompat(float f2, int i5) {
        this.f6032e = i5;
        this.f6033f = f2;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return this.f6032e;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Rating:style=");
        sb.append(this.f6032e);
        sb.append(" rating=");
        float f2 = this.f6033f;
        sb.append(f2 < 0.0f ? "unrated" : String.valueOf(f2));
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        parcel.writeInt(this.f6032e);
        parcel.writeFloat(this.f6033f);
    }
}
