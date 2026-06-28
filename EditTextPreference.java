package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import com.samsung.android.keyscafe.R;

/* JADX INFO: loaded from: classes.dex */
public class EditTextPreference extends DialogPreference {
    public String d0;

    /* JADX WARN: Illegal instructions before constructor call */
    public EditTextPreference(Context context, AttributeSet attributeSet) {
        int iA = C.b.a(context, R.attr.editTextPreferenceStyle, android.R.attr.editTextPreferenceStyle);
        super(context, attributeSet, iA);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, L.f8650c, iA, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(0, typedArrayObtainStyledAttributes.getBoolean(0, false))) {
            if (W1.a.f5291f == null) {
                W1.a.f5291f = new W1.a(20);
            }
            this.f8699Q = W1.a.f5291f;
            k();
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.preference.Preference
    public final boolean C() {
        return TextUtils.isEmpty(this.d0) || super.C();
    }

    public final void G(String str) {
        boolean zC = C();
        this.d0 = str;
        x(str);
        boolean zC2 = C();
        if (zC2 != zC) {
            l(zC2);
        }
        k();
    }

    @Override // androidx.preference.Preference
    public final Object r(TypedArray typedArray, int i5) {
        return typedArray.getString(i5);
    }

    @Override // androidx.preference.Preference
    public final void s(Parcelable parcelable) {
        if (!parcelable.getClass().equals(C0309d.class)) {
            super.s(parcelable);
            return;
        }
        C0309d c0309d = (C0309d) parcelable;
        super.s(c0309d.getSuperState());
        G(c0309d.f8795e);
    }

    @Override // androidx.preference.Preference
    public final Parcelable t() {
        this.f8697O = true;
        AbsSavedState absSavedState = AbsSavedState.EMPTY_STATE;
        if (this.f8721v) {
            return absSavedState;
        }
        C0309d c0309d = new C0309d(absSavedState);
        c0309d.f8795e = this.d0;
        return c0309d;
    }

    @Override // androidx.preference.Preference
    public final void u(Object obj) {
        G(g((String) obj));
    }
}
