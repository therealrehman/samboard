package androidx.appcompat.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import d6.AbstractC0476d;

/* JADX INFO: renamed from: androidx.appcompat.widget.q1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0182q1 extends View.BaseSavedState {
    public static final Parcelable.Creator<C0182q1> CREATOR = new P.n(15);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6837e;

    public final String toString() {
        StringBuilder sb = new StringBuilder("SeslCheckedTextView.SavedState{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" checked=");
        return AbstractC0476d.n(sb, this.f6837e, "}");
    }

    @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i5) {
        super.writeToParcel(parcel, i5);
        parcel.writeValue(Boolean.valueOf(this.f6837e));
    }
}
