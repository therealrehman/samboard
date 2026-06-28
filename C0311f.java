package androidx.preference;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.AbsSavedState;

/* JADX INFO: renamed from: androidx.preference.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0311f extends C0317l {
    public static final Parcelable.Creator<C0311f> CREATOR = new P.n(28);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f8797e;

    public C0311f(Parcel parcel) {
        super(parcel);
        this.f8797e = parcel.readString();
    }

    @Override // android.view.AbsSavedState, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        super.writeToParcel(parcel, i5);
        parcel.writeString(this.f8797e);
    }

    public C0311f(AbsSavedState absSavedState) {
        super(absSavedState);
    }
}
