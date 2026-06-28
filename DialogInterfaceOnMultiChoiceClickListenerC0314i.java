package androidx.preference;

import android.content.DialogInterface;

/* JADX INFO: renamed from: androidx.preference.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class DialogInterfaceOnMultiChoiceClickListenerC0314i implements DialogInterface.OnMultiChoiceClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f8801a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f8802b;

    public /* synthetic */ DialogInterfaceOnMultiChoiceClickListenerC0314i(int i5, Object obj) {
        this.f8801a = i5;
        this.f8802b = obj;
    }

    @Override // android.content.DialogInterface.OnMultiChoiceClickListener
    public final void onClick(DialogInterface dialogInterface, int i5, boolean z9) {
        switch (this.f8801a) {
            case 0:
                MultiSelectListPreferenceDialogFragment multiSelectListPreferenceDialogFragment = (MultiSelectListPreferenceDialogFragment) this.f8802b;
                if (!z9) {
                    multiSelectListPreferenceDialogFragment.f8673n |= multiSelectListPreferenceDialogFragment.f8672m.remove(multiSelectListPreferenceDialogFragment.f8674p[i5].toString());
                } else {
                    multiSelectListPreferenceDialogFragment.f8673n |= multiSelectListPreferenceDialogFragment.f8672m.add(multiSelectListPreferenceDialogFragment.f8674p[i5].toString());
                }
                break;
            default:
                MultiSelectListPreferenceDialogFragmentCompat multiSelectListPreferenceDialogFragmentCompat = (MultiSelectListPreferenceDialogFragmentCompat) this.f8802b;
                if (!z9) {
                    multiSelectListPreferenceDialogFragmentCompat.f8676n |= multiSelectListPreferenceDialogFragmentCompat.f8675m.remove(multiSelectListPreferenceDialogFragmentCompat.f8677p[i5].toString());
                } else {
                    multiSelectListPreferenceDialogFragmentCompat.f8676n |= multiSelectListPreferenceDialogFragmentCompat.f8675m.add(multiSelectListPreferenceDialogFragmentCompat.f8677p[i5].toString());
                }
                break;
        }
    }
}
