package androidx.preference;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.AbsSavedState;
import java.util.Collections;
import java.util.HashSet;

/* JADX INFO: renamed from: androidx.preference.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0313h extends C0317l {
    public static final Parcelable.Creator<C0313h> CREATOR = new P.n(29);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public HashSet f8800e;

    public C0313h(Parcel parcel) {
        super(parcel);
        int i5 = parcel.readInt();
        this.f8800e = new HashSet();
        String[] strArr = new String[i5];
        parcel.readStringArray(strArr);
        Collections.addAll(this.f8800e, strArr);
    }

    @Override // android.view.AbsSavedState, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        super.writeToParcel(parcel, i5);
        parcel.writeInt(this.f8800e.size());
        HashSet hashSet = this.f8800e;
        parcel.writeStringArray((String[]) hashSet.toArray(new String[hashSet.size()]));
    }

    public C0313h(AbsSavedState absSavedState) {
        super(absSavedState);
    }
}
