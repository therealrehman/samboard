package androidx.preference;

import android.content.DialogInterface;
import android.os.Bundle;
import g.C0544l;

/* JADX INFO: loaded from: classes.dex */
public class ListPreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f8667m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public CharSequence[] f8668n;
    public CharSequence[] o;

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    public final void i(boolean z9) {
        int i5;
        if (!z9 || (i5 = this.f8667m) < 0) {
            return;
        }
        String string = this.o[i5].toString();
        ListPreference listPreference = (ListPreference) g();
        listPreference.a(string);
        listPreference.I(string);
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    public final void j(C0544l c0544l) {
        c0544l.setSingleChoiceItems(this.f8668n, this.f8667m, new DialogInterfaceOnClickListenerC0312g(1, this));
        c0544l.setPositiveButton((CharSequence) null, (DialogInterface.OnClickListener) null);
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle) {
        CharSequence[] charSequenceArr;
        super.onCreate(bundle);
        if (bundle != null) {
            this.f8667m = bundle.getInt("ListPreferenceDialogFragment.index", 0);
            this.f8668n = bundle.getCharSequenceArray("ListPreferenceDialogFragment.entries");
            this.o = bundle.getCharSequenceArray("ListPreferenceDialogFragment.entryValues");
            return;
        }
        ListPreference listPreference = (ListPreference) g();
        if (listPreference.d0 == null || (charSequenceArr = listPreference.f8661e0) == null) {
            throw new IllegalStateException("ListPreference requires an entries array and an entryValues array.");
        }
        this.f8667m = listPreference.G(listPreference.f8662f0);
        this.f8668n = listPreference.d0;
        this.o = charSequenceArr;
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("ListPreferenceDialogFragment.index", this.f8667m);
        bundle.putCharSequenceArray("ListPreferenceDialogFragment.entries", this.f8668n);
        bundle.putCharSequenceArray("ListPreferenceDialogFragment.entryValues", this.o);
    }
}
