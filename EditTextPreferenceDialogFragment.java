package androidx.preference;

import android.R;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public class EditTextPreferenceDialogFragment extends PreferenceDialogFragment {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public EditText f8622m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public CharSequence f8623n;

    @Deprecated
    public EditTextPreferenceDialogFragment() {
    }

    @Override // androidx.preference.PreferenceDialogFragment
    public final void b(View view) {
        super.b(view);
        EditText editText = (EditText) view.findViewById(R.id.edit);
        this.f8622m = editText;
        if (editText == null) {
            throw new IllegalStateException("Dialog view must contain an EditText with id @android:id/edit");
        }
        editText.requestFocus();
        this.f8622m.setText(this.f8623n);
        EditText editText2 = this.f8622m;
        editText2.setSelection(editText2.getText().length());
    }

    @Override // androidx.preference.PreferenceDialogFragment
    public final void c(boolean z9) {
        if (z9) {
            String string = this.f8622m.getText().toString();
            ((EditTextPreference) a()).a(string);
            ((EditTextPreference) a()).G(string);
        }
    }

    @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment, android.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            this.f8623n = ((EditTextPreference) a()).d0;
        } else {
            this.f8623n = bundle.getCharSequence("EditTextPreferenceDialogFragment.text");
        }
    }

    @Override // androidx.preference.PreferenceDialogFragment, android.app.DialogFragment, android.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putCharSequence("EditTextPreferenceDialogFragment.text", this.f8623n);
    }
}
