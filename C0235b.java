package androidx.fragment.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;

/* JADX INFO: renamed from: androidx.fragment.app.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0235b implements Parcelable {
    public static final Parcelable.Creator<C0235b> CREATOR = new P.n(20);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int[] f7599e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f7600f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int[] f7601g;
    public final int[] h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f7602i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final String f7603j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final int f7604k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f7605l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final CharSequence f7606m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f7607n;
    public final CharSequence o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final ArrayList f7608p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final ArrayList f7609q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final boolean f7610r;

    public C0235b(C0234a c0234a) {
        int size = c0234a.f7668a.size();
        this.f7599e = new int[size * 6];
        if (!c0234a.f7674g) {
            throw new IllegalStateException("Not on back stack");
        }
        this.f7600f = new ArrayList(size);
        this.f7601g = new int[size];
        this.h = new int[size];
        int i5 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            i0 i0Var = (i0) c0234a.f7668a.get(i7);
            int i9 = i5 + 1;
            this.f7599e[i5] = i0Var.f7657a;
            ArrayList arrayList = this.f7600f;
            Fragment fragment = i0Var.f7658b;
            arrayList.add(fragment != null ? fragment.mWho : null);
            int[] iArr = this.f7599e;
            iArr[i9] = i0Var.f7659c ? 1 : 0;
            iArr[i5 + 2] = i0Var.f7660d;
            iArr[i5 + 3] = i0Var.f7661e;
            int i10 = i5 + 5;
            iArr[i5 + 4] = i0Var.f7662f;
            i5 += 6;
            iArr[i10] = i0Var.f7663g;
            this.f7601g[i7] = i0Var.h.ordinal();
            this.h[i7] = i0Var.f7664i.ordinal();
        }
        this.f7602i = c0234a.f7673f;
        this.f7603j = c0234a.f7675i;
        this.f7604k = c0234a.f7591s;
        this.f7605l = c0234a.f7676j;
        this.f7606m = c0234a.f7677k;
        this.f7607n = c0234a.f7678l;
        this.o = c0234a.f7679m;
        this.f7608p = c0234a.f7680n;
        this.f7609q = c0234a.o;
        this.f7610r = c0234a.f7681p;
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        parcel.writeIntArray(this.f7599e);
        parcel.writeStringList(this.f7600f);
        parcel.writeIntArray(this.f7601g);
        parcel.writeIntArray(this.h);
        parcel.writeInt(this.f7602i);
        parcel.writeString(this.f7603j);
        parcel.writeInt(this.f7604k);
        parcel.writeInt(this.f7605l);
        TextUtils.writeToParcel(this.f7606m, parcel, 0);
        parcel.writeInt(this.f7607n);
        TextUtils.writeToParcel(this.o, parcel, 0);
        parcel.writeStringList(this.f7608p);
        parcel.writeStringList(this.f7609q);
        parcel.writeInt(this.f7610r ? 1 : 0);
    }

    public C0235b(Parcel parcel) {
        this.f7599e = parcel.createIntArray();
        this.f7600f = parcel.createStringArrayList();
        this.f7601g = parcel.createIntArray();
        this.h = parcel.createIntArray();
        this.f7602i = parcel.readInt();
        this.f7603j = parcel.readString();
        this.f7604k = parcel.readInt();
        this.f7605l = parcel.readInt();
        Parcelable.Creator creator = TextUtils.CHAR_SEQUENCE_CREATOR;
        this.f7606m = (CharSequence) creator.createFromParcel(parcel);
        this.f7607n = parcel.readInt();
        this.o = (CharSequence) creator.createFromParcel(parcel);
        this.f7608p = parcel.createStringArrayList();
        this.f7609q = parcel.createStringArrayList();
        this.f7610r = parcel.readInt() != 0;
    }
}
