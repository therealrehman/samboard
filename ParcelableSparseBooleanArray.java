package com.google.android.material.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseBooleanArray;

/* JADX INFO: loaded from: classes.dex */
public class ParcelableSparseBooleanArray extends SparseBooleanArray implements Parcelable {
    public static final Parcelable.Creator<ParcelableSparseBooleanArray> CREATOR = new Parcelable.Creator<ParcelableSparseBooleanArray>() { // from class: com.google.android.material.internal.ParcelableSparseBooleanArray.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableSparseBooleanArray createFromParcel(Parcel parcel) {
            int i5 = parcel.readInt();
            ParcelableSparseBooleanArray parcelableSparseBooleanArray = new ParcelableSparseBooleanArray(i5);
            int[] iArr = new int[i5];
            boolean[] zArr = new boolean[i5];
            parcel.readIntArray(iArr);
            parcel.readBooleanArray(zArr);
            for (int i7 = 0; i7 < i5; i7++) {
                parcelableSparseBooleanArray.put(iArr[i7], zArr[i7]);
            }
            return parcelableSparseBooleanArray;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableSparseBooleanArray[] newArray(int i5) {
            return new ParcelableSparseBooleanArray[i5];
        }
    };

    public ParcelableSparseBooleanArray() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i5) {
        int[] iArr = new int[size()];
        boolean[] zArr = new boolean[size()];
        for (int i7 = 0; i7 < size(); i7++) {
            iArr[i7] = keyAt(i7);
            zArr[i7] = valueAt(i7);
        }
        parcel.writeInt(size());
        parcel.writeIntArray(iArr);
        parcel.writeBooleanArray(zArr);
    }

    public ParcelableSparseBooleanArray(int i5) {
        super(i5);
    }

    public ParcelableSparseBooleanArray(SparseBooleanArray sparseBooleanArray) {
        super(sparseBooleanArray.size());
        for (int i5 = 0; i5 < sparseBooleanArray.size(); i5++) {
            put(sparseBooleanArray.keyAt(i5), sparseBooleanArray.valueAt(i5));
        }
    }
}
