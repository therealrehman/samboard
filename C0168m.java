package androidx.appcompat.widget;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: renamed from: androidx.appcompat.widget.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0168m implements Parcelable {
    public static final Parcelable.Creator<C0168m> CREATOR = new P.n(13);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f6756e;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        parcel.writeInt(this.f6756e);
    }
}
