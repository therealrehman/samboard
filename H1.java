package androidx.recyclerview.widget;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.preference.C0316k;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class h1 implements Parcelable {
    public static final Parcelable.Creator<h1> CREATOR = new C0316k(5);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f9147e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f9148f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int[] f9149g;
    public boolean h;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return "FullSpanItem{mPosition=" + this.f9147e + ", mGapDir=" + this.f9148f + ", mHasUnwantedGapAfter=" + this.h + ", mGapPerSpan=" + Arrays.toString(this.f9149g) + '}';
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        parcel.writeInt(this.f9147e);
        parcel.writeInt(this.f9148f);
        parcel.writeInt(this.h ? 1 : 0);
        int[] iArr = this.f9149g;
        if (iArr == null || iArr.length <= 0) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(iArr.length);
            parcel.writeIntArray(this.f9149g);
        }
    }
}
