package androidx.preference;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.recyclerview.widget.T;
import androidx.recyclerview.widget.h1;
import androidx.recyclerview.widget.i1;
import z0.C1281b;

/* JADX INFO: renamed from: androidx.preference.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0316k implements Parcelable.Creator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8807a;

    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        switch (this.f8807a) {
            case 0:
                return new C0317l(parcel);
            case 1:
                return new y(parcel);
            case 2:
                return new O(parcel);
            case 3:
                return new P(parcel);
            case 4:
                T t8 = new T();
                t8.f9022e = parcel.readInt();
                t8.f9023f = parcel.readInt();
                t8.f9024g = parcel.readInt() == 1;
                return t8;
            case 5:
                h1 h1Var = new h1();
                h1Var.f9147e = parcel.readInt();
                h1Var.f9148f = parcel.readInt();
                h1Var.h = parcel.readInt() == 1;
                int i5 = parcel.readInt();
                if (i5 > 0) {
                    int[] iArr = new int[i5];
                    h1Var.f9149g = iArr;
                    parcel.readIntArray(iArr);
                }
                return h1Var;
            case 6:
                i1 i1Var = new i1();
                i1Var.f9159e = parcel.readInt();
                i1Var.f9160f = parcel.readInt();
                int i7 = parcel.readInt();
                i1Var.f9161g = i7;
                if (i7 > 0) {
                    int[] iArr2 = new int[i7];
                    i1Var.h = iArr2;
                    parcel.readIntArray(iArr2);
                }
                int i9 = parcel.readInt();
                i1Var.f9162i = i9;
                if (i9 > 0) {
                    int[] iArr3 = new int[i9];
                    i1Var.f9163j = iArr3;
                    parcel.readIntArray(iArr3);
                }
                i1Var.f9165l = parcel.readInt() == 1;
                i1Var.f9166m = parcel.readInt() == 1;
                i1Var.f9167n = parcel.readInt() == 1;
                i1Var.f9164k = parcel.readArrayList(h1.class.getClassLoader());
                return i1Var;
            case 7:
                f1.g gVar = new f1.g(parcel);
                gVar.f10792e = parcel.readString();
                gVar.f10794g = parcel.readFloat();
                gVar.h = parcel.readInt() == 1;
                gVar.f10795i = parcel.readString();
                gVar.f10796j = parcel.readInt();
                gVar.f10797k = parcel.readInt();
                return gVar;
            default:
                kotlin.jvm.internal.j.f(parcel, "parcel");
                return new C1281b(parcel.readInt(), parcel.readString(), parcel.readString());
        }
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i5) {
        switch (this.f8807a) {
            case 0:
                return new C0317l[i5];
            case 1:
                return new y[i5];
            case 2:
                return new O[i5];
            case 3:
                return new P[i5];
            case 4:
                return new T[i5];
            case 5:
                return new h1[i5];
            case 6:
                return new i1[i5];
            case 7:
                return new f1.g[i5];
            default:
                return new C1281b[i5];
        }
    }
}
