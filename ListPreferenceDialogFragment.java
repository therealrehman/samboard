package androidx.preference;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class ListPreferenceDialogFragment extends PreferenceDialogFragment {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f8665m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public CharSequence[] f8666n;
    public CharSequence[] o;

    @Deprecated
    public ListPreferenceDialogFragment() {
    }

    @Override // androidx.preference.PreferenceDialogFragment
    public final void c(boolean z9) {
        int i5;
        ListPreference listPreference = (ListPreference) a();
        if (!z9 || (i5 = this.f8665m) < 0) {
            return;
        }
        String string = this.o[i5].toString();
        listPreference.a(string);
        listPreference.I(string);
    }

    @Override // androidx.preference.PreferenceDialogFragment
    public final void d(AlertDialog.Builder builder) {
        builder.setSingleChoiceItems(this.f8666n, this.f8665m, new DialogInterfaceOnClickListenerC0312g(0, this));
        builder.setPositiveButton((CharSequence) null, (DialogInterface.OnClickListener) null);
    }

    @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment, android.app.Fragment
    public final void onCreate(Bundle bundle) {
        CharSequence[] charSequenceArr;
        super.onCreate(bundle);
        if (bundle != null) {
            this.f8665m = bundle.getInt("ListPreferenceDialogFragment.index", 0);
            this.f8666n = bundle.getCharSequenceArray("ListPreferenceDialogFragment.entries");
            this.o = bundle.getCharSequenceArray("ListPreferenceDialogFragment.entryValues");
            return;
        }
        ListPreference listPreference = (ListPreference) a();
        if (listPreference.d0 == null || (charSequenceArr = listPreference.f8661e0) == null) {
            throw new IllegalStateException("ListPreference requires an entries array and an entryValues array.");
        }
        this.f8665m = listPreference.G(listPreference.f8662f0);
        this.f8666n = listPreference.d0;
        this.o = charSequenceArr;
    }

    @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment, android.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("ListPreferenceDialogFragment.index", this.f8665m);
        bundle.putCharSequenceArray("ListPreferenceDialogFragment.entries", this.f8666n);
        bundle.putCharSequenceArray("ListPreferenceDialogFragment.entryValues", this.o);
    }
}
