package androidx.preference;

import android.content.DialogInterface;

/* JADX INFO: renamed from: androidx.preference.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class DialogInterfaceOnClickListenerC0312g implements DialogInterface.OnClickListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f8798e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f8799f;

    public /* synthetic */ DialogInterfaceOnClickListenerC0312g(int i5, Object obj) {
        this.f8798e = i5;
        this.f8799f = obj;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i5) {
        switch (this.f8798e) {
            case 0:
                ListPreferenceDialogFragment listPreferenceDialogFragment = (ListPreferenceDialogFragment) this.f8799f;
                listPreferenceDialogFragment.f8665m = i5;
                listPreferenceDialogFragment.f8732l = -1;
                dialogInterface.dismiss();
                break;
            default:
                ListPreferenceDialogFragmentCompat listPreferenceDialogFragmentCompat = (ListPreferenceDialogFragmentCompat) this.f8799f;
                listPreferenceDialogFragmentCompat.f8667m = i5;
                listPreferenceDialogFragmentCompat.f8739l = -1;
                dialogInterface.dismiss();
                break;
        }
    }
}
