package androidx.preference;

import android.app.AlertDialog;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class MultiSelectListPreferenceDialogFragment extends PreferenceDialogFragment {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final HashSet f8672m = new HashSet();

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f8673n;
    public CharSequence[] o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public CharSequence[] f8674p;

    @Deprecated
    public MultiSelectListPreferenceDialogFragment() {
    }

    @Override // androidx.preference.PreferenceDialogFragment
    public final void c(boolean z9) {
        MultiSelectListPreference multiSelectListPreference = (MultiSelectListPreference) a();
        if (z9 && this.f8673n) {
            HashSet hashSet = this.f8672m;
            multiSelectListPreference.a(hashSet);
            multiSelectListPreference.G(hashSet);
        }
        this.f8673n = false;
    }

    @Override // androidx.preference.PreferenceDialogFragment
    public final void d(AlertDialog.Builder builder) {
        int length = this.f8674p.length;
        boolean[] zArr = new boolean[length];
        for (int i5 = 0; i5 < length; i5++) {
            zArr[i5] = this.f8672m.contains(this.f8674p[i5].toString());
        }
        builder.setMultiChoiceItems(this.o, zArr, new DialogInterfaceOnMultiChoiceClickListenerC0314i(0, this));
    }

    @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment, android.app.Fragment
    public final void onCreate(Bundle bundle) {
        CharSequence[] charSequenceArr;
        super.onCreate(bundle);
        HashSet hashSet = this.f8672m;
        if (bundle != null) {
            hashSet.clear();
            hashSet.addAll(bundle.getStringArrayList("MultiSelectListPreferenceDialogFragment.values"));
            this.f8673n = bundle.getBoolean("MultiSelectListPreferenceDialogFragment.changed", false);
            this.o = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragment.entries");
            this.f8674p = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragment.entryValues");
            return;
        }
        MultiSelectListPreference multiSelectListPreference = (MultiSelectListPreference) a();
        if (multiSelectListPreference.d0 == null || (charSequenceArr = multiSelectListPreference.f8670e0) == null) {
            throw new IllegalStateException("MultiSelectListPreference requires an entries array and an entryValues array.");
        }
        hashSet.clear();
        hashSet.addAll(multiSelectListPreference.f8671f0);
        this.f8673n = false;
        this.o = multiSelectListPreference.d0;
        this.f8674p = charSequenceArr;
    }

    @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment, android.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putStringArrayList("MultiSelectListPreferenceDialogFragment.values", new ArrayList<>(this.f8672m));
        bundle.putBoolean("MultiSelectListPreferenceDialogFragment.changed", this.f8673n);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragment.entries", this.o);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragment.entryValues", this.f8674p);
    }
}
