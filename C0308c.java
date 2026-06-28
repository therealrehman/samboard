package androidx.preference;

import android.view.View;
import android.widget.AdapterView;

/* JADX INFO: renamed from: androidx.preference.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0308c implements AdapterView.OnItemSelectedListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ DropDownPreference f8794e;

    public C0308c(DropDownPreference dropDownPreference) {
        this.f8794e = dropDownPreference;
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onItemSelected(AdapterView adapterView, View view, int i5, long j5) {
        if (i5 >= 0) {
            DropDownPreference dropDownPreference = this.f8794e;
            String string = dropDownPreference.f8661e0[i5].toString();
            if (string.equals(dropDownPreference.f8662f0)) {
                return;
            }
            dropDownPreference.a(string);
            dropDownPreference.I(string);
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public final void onNothingSelected(AdapterView adapterView) {
    }
}
