package androidx.appcompat.widget;

import android.os.Parcel;
import android.os.Parcelable;
import d6.AbstractC0476d;

/* JADX INFO: renamed from: androidx.appcompat.widget.e1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0146e1 extends R.c {
    public static final Parcelable.Creator<C0146e1> CREATOR = new R.b(2);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6694e;

    public C0146e1(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.f6694e = ((Boolean) parcel.readValue(null)).booleanValue();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SearchView.SavedState{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" isIconified=");
        return AbstractC0476d.n(sb, this.f6694e, "}");
    }

    @Override // R.c, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        super.writeToParcel(parcel, i5);
        parcel.writeValue(Boolean.valueOf(this.f6694e));
    }
}
