package androidx.preference;

import android.os.Bundle;
import g.C0544l;
import java.util.ArrayList;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class MultiSelectListPreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final HashSet f8675m = new HashSet();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f8676n;
    public CharSequence[] o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public CharSequence[] f8677p;

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    public final void i(boolean z9) {
        if (z9 && this.f8676n) {
            MultiSelectListPreference multiSelectListPreference = (MultiSelectListPreference) g();
            HashSet hashSet = this.f8675m;
            multiSelectListPreference.a(hashSet);
            multiSelectListPreference.G(hashSet);
        }
        this.f8676n = false;
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    public final void j(C0544l c0544l) {
        int length = this.f8677p.length;
        boolean[] zArr = new boolean[length];
        for (int i5 = 0; i5 < length; i5++) {
            zArr[i5] = this.f8675m.contains(this.f8677p[i5].toString());
        }
        c0544l.setMultiChoiceItems(this.o, zArr, new DialogInterfaceOnMultiChoiceClickListenerC0314i(1, this));
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle) {
        CharSequence[] charSequenceArr;
        super.onCreate(bundle);
        HashSet hashSet = this.f8675m;
        if (bundle != null) {
            hashSet.clear();
            hashSet.addAll(bundle.getStringArrayList("MultiSelectListPreferenceDialogFragmentCompat.values"));
            this.f8676n = bundle.getBoolean("MultiSelectListPreferenceDialogFragmentCompat.changed", false);
            this.o = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entries");
            this.f8677p = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entryValues");
            return;
        }
        MultiSelectListPreference multiSelectListPreference = (MultiSelectListPreference) g();
        if (multiSelectListPreference.d0 == null || (charSequenceArr = multiSelectListPreference.f8670e0) == null) {
            throw new IllegalStateException("MultiSelectListPreference requires an entries array and an entryValues array.");
        }
        hashSet.clear();
        hashSet.addAll(multiSelectListPreference.f8671f0);
        this.f8676n = false;
        this.o = multiSelectListPreference.d0;
        this.f8677p = charSequenceArr;
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putStringArrayList("MultiSelectListPreferenceDialogFragmentCompat.values", new ArrayList<>(this.f8675m));
        bundle.putBoolean("MultiSelectListPreferenceDialogFragmentCompat.changed", this.f8676n);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entries", this.o);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entryValues", this.f8677p);
    }
}
