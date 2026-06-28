package com.google.android.material.stateful;

import R.c;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import q.k;

/* JADX INFO: loaded from: classes.dex */
public class ExtendableSavedState extends c {
    public static final Parcelable.Creator<ExtendableSavedState> CREATOR = new Parcelable.ClassLoaderCreator<ExtendableSavedState>() { // from class: com.google.android.material.stateful.ExtendableSavedState.1
        @Override // android.os.Parcelable.Creator
        public ExtendableSavedState[] newArray(int i5) {
            return new ExtendableSavedState[i5];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        public ExtendableSavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
            return new ExtendableSavedState(parcel, classLoader);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Parcelable.Creator
        public ExtendableSavedState createFromParcel(Parcel parcel) {
            return new ExtendableSavedState(parcel, null);
        }
    };
    public final k extendableStates;

    public String toString() {
        return "ExtendableSavedState{" + Integer.toHexString(System.identityHashCode(this)) + " states=" + this.extendableStates + "}";
    }

    @Override // R.c, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i5) {
        super.writeToParcel(parcel, i5);
        int i7 = this.extendableStates.f12929g;
        parcel.writeInt(i7);
        String[] strArr = new String[i7];
        Bundle[] bundleArr = new Bundle[i7];
        for (int i9 = 0; i9 < i7; i9++) {
            strArr[i9] = (String) this.extendableStates.h(i9);
            bundleArr[i9] = (Bundle) this.extendableStates.l(i9);
        }
        parcel.writeStringArray(strArr);
        parcel.writeTypedArray(bundleArr, 0);
    }

    public ExtendableSavedState(Parcelable parcelable) {
        super(parcelable);
        this.extendableStates = new k();
    }

    private ExtendableSavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        int i5 = parcel.readInt();
        String[] strArr = new String[i5];
        parcel.readStringArray(strArr);
        Bundle[] bundleArr = new Bundle[i5];
        parcel.readTypedArray(bundleArr, Bundle.CREATOR);
        this.extendableStates = new k(i5);
        for (int i7 = 0; i7 < i5; i7++) {
            this.extendableStates.put(strArr[i7], bundleArr[i7]);
        }
    }
}
