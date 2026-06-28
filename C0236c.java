package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/* JADX INFO: renamed from: androidx.fragment.app.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0236c implements Parcelable {
    public static final Parcelable.Creator<C0236c> CREATOR = new P.n(21);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f7617e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f7618f;

    public C0236c(Parcel parcel) {
        this.f7617e = parcel.createStringArrayList();
        this.f7618f = parcel.createTypedArrayList(C0235b.CREATOR);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        parcel.writeStringList(this.f7617e);
        parcel.writeTypedList(this.f7618f);
    }
}
