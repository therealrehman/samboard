package androidx.appcompat.widget;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final class Z1 extends R.c {
    public static final Parcelable.Creator<Z1> CREATOR = new R.b(3);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f6663e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f6664f;

    public Z1(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.f6663e = parcel.readInt();
        this.f6664f = parcel.readInt() != 0;
    }

    @Override // R.c, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        super.writeToParcel(parcel, i5);
        parcel.writeInt(this.f6663e);
        parcel.writeInt(this.f6664f ? 1 : 0);
    }
}
