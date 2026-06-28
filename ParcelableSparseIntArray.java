package com.google.android.material.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseIntArray;

/* JADX INFO: loaded from: classes.dex */
public class ParcelableSparseIntArray extends SparseIntArray implements Parcelable {
    public static final Parcelable.Creator<ParcelableSparseIntArray> CREATOR = new Parcelable.Creator<ParcelableSparseIntArray>() { // from class: com.google.android.material.internal.ParcelableSparseIntArray.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableSparseIntArray createFromParcel(Parcel parcel) {
            int i5 = parcel.readInt();
            ParcelableSparseIntArray parcelableSparseIntArray = new ParcelableSparseIntArray(i5);
            int[] iArr = new int[i5];
            int[] iArr2 = new int[i5];
            parcel.readIntArray(iArr);
            parcel.readIntArray(iArr2);
            for (int i7 = 0; i7 < i5; i7++) {
                parcelableSparseIntArray.put(iArr[i7], iArr2[i7]);
            }
            return parcelableSparseIntArray;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableSparseIntArray[] newArray(int i5) {
            return new ParcelableSparseIntArray[i5];
        }
    };

    public ParcelableSparseIntArray() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i5) {
        int[] iArr = new int[size()];
        int[] iArr2 = new int[size()];
        for (int i7 = 0; i7 < size(); i7++) {
            iArr[i7] = keyAt(i7);
            iArr2[i7] = valueAt(i7);
        }
        parcel.writeInt(size());
        parcel.writeIntArray(iArr);
        parcel.writeIntArray(iArr2);
    }

    public ParcelableSparseIntArray(int i5) {
        super(i5);
    }

    public ParcelableSparseIntArray(SparseIntArray sparseIntArray) {
        for (int i5 = 0; i5 < sparseIntArray.size(); i5++) {
            put(sparseIntArray.keyAt(i5), sparseIntArray.valueAt(i5));
        }
    }
}
