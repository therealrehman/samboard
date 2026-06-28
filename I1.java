package androidx.recyclerview.widget;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.preference.C0316k;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class i1 implements Parcelable {
    public static final Parcelable.Creator<i1> CREATOR = new C0316k(6);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f9159e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f9160f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f9161g;
    public int[] h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f9162i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int[] f9163j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public List f9164k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f9165l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f9166m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f9167n;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        parcel.writeInt(this.f9159e);
        parcel.writeInt(this.f9160f);
        parcel.writeInt(this.f9161g);
        if (this.f9161g > 0) {
            parcel.writeIntArray(this.h);
        }
        parcel.writeInt(this.f9162i);
        if (this.f9162i > 0) {
            parcel.writeIntArray(this.f9163j);
        }
        parcel.writeInt(this.f9165l ? 1 : 0);
        parcel.writeInt(this.f9166m ? 1 : 0);
        parcel.writeInt(this.f9167n ? 1 : 0);
        parcel.writeList(this.f9164k);
    }
}
