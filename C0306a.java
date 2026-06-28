package androidx.preference;

import android.widget.CompoundButton;

/* JADX INFO: renamed from: androidx.preference.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0306a implements CompoundButton.OnCheckedChangeListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f8792e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ TwoStatePreference f8793f;

    public /* synthetic */ C0306a(TwoStatePreference twoStatePreference, int i5) {
        this.f8792e = i5;
        this.f8793f = twoStatePreference;
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z9) {
        switch (this.f8792e) {
            case 0:
                Boolean boolValueOf = Boolean.valueOf(z9);
                CheckBoxPreference checkBoxPreference = (CheckBoxPreference) this.f8793f;
                checkBoxPreference.a(boolValueOf);
                checkBoxPreference.G(z9);
                break;
            case 1:
                Boolean boolValueOf2 = Boolean.valueOf(z9);
                SwitchPreference switchPreference = (SwitchPreference) this.f8793f;
                switchPreference.a(boolValueOf2);
                switchPreference.G(z9);
                break;
            default:
                Boolean boolValueOf3 = Boolean.valueOf(z9);
                SwitchPreferenceCompat switchPreferenceCompat = (SwitchPreferenceCompat) this.f8793f;
                switchPreferenceCompat.a(boolValueOf3);
                switchPreferenceCompat.G(z9);
                break;
        }
    }
}
