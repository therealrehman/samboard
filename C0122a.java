package a6;

import android.os.IBinder;
import android.os.Parcel;
import com.samsung.android.keyscafe.bubbletea.common.Constants;
import java.util.Map;

/* JADX INFO: renamed from: a6.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0122a implements c {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public IBinder f6011d;

    @Override // a6.c
    public final boolean a(String str) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.samsung.android.thememanager.IThemeManager");
            parcelObtain.writeString(str);
            this.f6011d.transact(78, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.readInt() != 0;
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.f6011d;
    }

    @Override // a6.c
    public final void b(String str) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.samsung.android.thememanager.IThemeManager");
            parcelObtain.writeString(str);
            this.f6011d.transact(81, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // a6.c
    public final boolean c(String str) {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.samsung.android.thememanager.IThemeManager");
            parcelObtain.writeString(str);
            parcelObtain.writeInt(0);
            this.f6011d.transact(77, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.readInt() != 0;
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // a6.c
    public final Map e() {
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.samsung.android.thememanager.IThemeManager");
            parcelObtain.writeString(Constants.HONEYBOARD_PACKAGE);
            this.f6011d.transact(79, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            return parcelObtain2.readHashMap(C0122a.class.getClassLoader());
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }
}
