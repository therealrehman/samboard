package android.support.v4.media.session;

import P.n;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class PlaybackStateCompat implements Parcelable {
    public static final Parcelable.Creator<PlaybackStateCompat> CREATOR = new n(10);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f6056e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final long f6057f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final long f6058g;
    public final float h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f6059i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final int f6060j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final CharSequence f6061k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final long f6062l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final ArrayList f6063m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f6064n;
    public final Bundle o;

    public static final class CustomAction implements Parcelable {
        public static final Parcelable.Creator<CustomAction> CREATOR = new g();

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final String f6065e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public final CharSequence f6066f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public final int f6067g;
        public final Bundle h;

        public CustomAction(Parcel parcel) {
            this.f6065e = parcel.readString();
            this.f6066f = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.f6067g = parcel.readInt();
            this.h = parcel.readBundle(f.class.getClassLoader());
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public final String toString() {
            return "Action:mName='" + ((Object) this.f6066f) + ", mIcon=" + this.f6067g + ", mExtras=" + this.h;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i5) {
            parcel.writeString(this.f6065e);
            TextUtils.writeToParcel(this.f6066f, parcel, i5);
            parcel.writeInt(this.f6067g);
            parcel.writeBundle(this.h);
        }
    }

    public PlaybackStateCompat(Parcel parcel) {
        this.f6056e = parcel.readInt();
        this.f6057f = parcel.readLong();
        this.h = parcel.readFloat();
        this.f6062l = parcel.readLong();
        this.f6058g = parcel.readLong();
        this.f6059i = parcel.readLong();
        this.f6061k = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.f6063m = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.f6064n = parcel.readLong();
        this.o = parcel.readBundle(f.class.getClassLoader());
        this.f6060j = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return "PlaybackState {state=" + this.f6056e + ", position=" + this.f6057f + ", buffered position=" + this.f6058g + ", speed=" + this.h + ", updated=" + this.f6062l + ", actions=" + this.f6059i + ", error code=" + this.f6060j + ", error message=" + this.f6061k + ", custom actions=" + this.f6063m + ", active item id=" + this.f6064n + "}";
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        parcel.writeInt(this.f6056e);
        parcel.writeLong(this.f6057f);
        parcel.writeFloat(this.h);
        parcel.writeLong(this.f6062l);
        parcel.writeLong(this.f6058g);
        parcel.writeLong(this.f6059i);
        TextUtils.writeToParcel(this.f6061k, parcel, i5);
        parcel.writeTypedList(this.f6063m);
        parcel.writeLong(this.f6064n);
        parcel.writeBundle(this.o);
        parcel.writeInt(this.f6060j);
    }
}
