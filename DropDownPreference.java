package androidx.preference;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.SpinnerAdapter;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.C0179p1;
import com.samsung.android.keyscafe.R;

/* JADX INFO: loaded from: classes.dex */
public class DropDownPreference extends ListPreference {

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public final C0179p1 f8620i0;
    public AppCompatSpinner j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public final C0308c f8621k0;

    public DropDownPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.dropdownPreferenceStyle);
        this.f8621k0 = new C0308c(this);
        C0179p1 c0179p1 = new C0179p1(context, R.layout.support_simple_spinner_dropdown_item);
        this.f8620i0 = c0179p1;
        c0179p1.clear();
        CharSequence[] charSequenceArr = this.d0;
        if (charSequenceArr != null) {
            for (CharSequence charSequence : charSequenceArr) {
                c0179p1.add(charSequence.toString());
            }
        }
    }

    @Override // androidx.preference.Preference
    public final void k() {
        super.k();
        C0179p1 c0179p1 = this.f8620i0;
        if (c0179p1 != null) {
            c0179p1.notifyDataSetChanged();
        }
    }

    @Override // androidx.preference.Preference
    public final void o(K k5) {
        int length;
        CharSequence[] charSequenceArr;
        AppCompatSpinner appCompatSpinner = (AppCompatSpinner) k5.itemView.findViewById(R.id.spinner);
        this.j0 = appCompatSpinner;
        appCompatSpinner.setSoundEffectsEnabled(false);
        SpinnerAdapter adapter = this.j0.getAdapter();
        C0179p1 c0179p1 = this.f8620i0;
        if (!c0179p1.equals(adapter)) {
            this.j0.setAdapter((SpinnerAdapter) c0179p1);
        }
        this.j0.setOnItemSelectedListener(this.f8621k0);
        AppCompatSpinner appCompatSpinner2 = this.j0;
        String str = this.f8662f0;
        if (str == null || (charSequenceArr = this.f8661e0) == null) {
            length = -1;
        } else {
            length = charSequenceArr.length - 1;
            while (length >= 0) {
                if (TextUtils.equals(charSequenceArr[length].toString(), str)) {
                    break;
                } else {
                    length--;
                }
            }
            length = -1;
        }
        appCompatSpinner2.setSelection(length);
        super.o(k5);
    }

    @Override // androidx.preference.DialogPreference, androidx.preference.Preference
    public final void p() {
        this.j0.performClick();
    }
}
