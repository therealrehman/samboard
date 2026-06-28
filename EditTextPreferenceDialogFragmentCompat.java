package androidx.preference;

import android.R;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/* JADX INFO: loaded from: classes.dex */
public class EditTextPreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public EditText f8624m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public CharSequence f8625n;
    public final t o = new t(1, this);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f8626p = -1;

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    public final void h(View view) {
        super.h(view);
        EditText editText = (EditText) view.findViewById(R.id.edit);
        this.f8624m = editText;
        if (editText == null) {
            throw new IllegalStateException("Dialog view must contain an EditText with id @android:id/edit");
        }
        editText.requestFocus();
        this.f8624m.setText(this.f8625n);
        EditText editText2 = this.f8624m;
        editText2.setSelection(editText2.getText().length());
        ((EditTextPreference) g()).getClass();
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat
    public final void i(boolean z9) {
        if (z9) {
            String string = this.f8624m.getText().toString();
            EditTextPreference editTextPreference = (EditTextPreference) g();
            editTextPreference.a(string);
            editTextPreference.G(string);
        }
    }

    public final void k() {
        long j5 = this.f8626p;
        if (j5 == -1 || j5 + 1000 <= SystemClock.currentThreadTimeMillis()) {
            return;
        }
        EditText editText = this.f8624m;
        if (editText == null || !editText.isFocused()) {
            this.f8626p = -1L;
            return;
        }
        if (((InputMethodManager) this.f8624m.getContext().getSystemService("input_method")).showSoftInput(this.f8624m, 0)) {
            this.f8626p = -1L;
            return;
        }
        EditText editText2 = this.f8624m;
        t tVar = this.o;
        editText2.removeCallbacks(tVar);
        this.f8624m.postDelayed(tVar, 50L);
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            this.f8625n = ((EditTextPreference) g()).d0;
        } else {
            this.f8625n = bundle.getCharSequence("EditTextPreferenceDialogFragment.text");
        }
    }

    @Override // androidx.preference.PreferenceDialogFragmentCompat, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putCharSequence("EditTextPreferenceDialogFragment.text", this.f8625n);
    }
}
