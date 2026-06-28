package androidx.appcompat.app;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import g.E;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatDialogFragment extends DialogFragment {
    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        return new E(getContext(), getTheme());
    }

    @Override // androidx.fragment.app.DialogFragment
    public void setupDialog(Dialog dialog, int i5) {
        if (!(dialog instanceof E)) {
            super.setupDialog(dialog, i5);
            return;
        }
        E e3 = (E) dialog;
        if (i5 != 1 && i5 != 2) {
            if (i5 != 3) {
                return;
            } else {
                dialog.getWindow().addFlags(24);
            }
        }
        e3.supportRequestWindowFeature(1);
    }
}
