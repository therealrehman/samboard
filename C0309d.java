package androidx.preference;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.AbsSavedState;

/* JADX INFO: renamed from: androidx.preference.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0309d extends C0317l {
    public static final Parcelable.Creator<C0309d> CREATOR = new P.n(27);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f8795e;

    public C0309d(Parcel parcel) {
        super(parcel);
        this.f8795e = parcel.readString();
    }

    @Override // android.view.AbsSavedState, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        super.writeToParcel(parcel, i5);
        parcel.writeString(this.f8795e);
    }

    public C0309d(AbsSavedState absSavedState) {
        super(absSavedState);
    }
}
