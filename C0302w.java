package androidx.picker.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/* JADX INFO: renamed from: androidx.picker.widget.w, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0302w extends View.BaseSavedState {
    public static final Parcelable.Creator<C0302w> CREATOR = new P.n(25);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f8449e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f8450f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f8451g;
    public final long h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f8452i;

    public C0302w(Parcelable parcelable, int i5, int i7, int i9, long j5, long j9) {
        super(parcelable);
        this.f8449e = i5;
        this.f8450f = i7;
        this.f8451g = i9;
        this.h = j5;
        this.f8452i = j9;
    }

    @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        super.writeToParcel(parcel, i5);
        parcel.writeInt(this.f8449e);
        parcel.writeInt(this.f8450f);
        parcel.writeInt(this.f8451g);
        parcel.writeLong(this.h);
        parcel.writeLong(this.f8452i);
    }

    public C0302w(Parcel parcel) {
        super(parcel);
        this.f8449e = parcel.readInt();
        this.f8450f = parcel.readInt();
        this.f8451g = parcel.readInt();
        this.h = parcel.readLong();
        this.f8452i = parcel.readLong();
    }
}
