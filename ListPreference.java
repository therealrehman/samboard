package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.AbsSavedState;
import com.samsung.android.keyscafe.R;

/* JADX INFO: loaded from: classes.dex */
public class ListPreference extends DialogPreference {
    public final CharSequence[] d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public final CharSequence[] f8661e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public String f8662f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public final String f8663g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public boolean f8664h0;

    public ListPreference(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, L.f8651d, i5, 0);
        CharSequence[] textArray = typedArrayObtainStyledAttributes.getTextArray(2);
        this.d0 = textArray == null ? typedArrayObtainStyledAttributes.getTextArray(0) : textArray;
        CharSequence[] textArray2 = typedArrayObtainStyledAttributes.getTextArray(3);
        this.f8661e0 = textArray2 == null ? typedArrayObtainStyledAttributes.getTextArray(1) : textArray2;
        if (typedArrayObtainStyledAttributes.getBoolean(4, typedArrayObtainStyledAttributes.getBoolean(4, false))) {
            if (W1.a.f5292g == null) {
                W1.a.f5292g = new W1.a(21);
            }
            this.f8699Q = W1.a.f5292g;
            k();
        }
        typedArrayObtainStyledAttributes.recycle();
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, L.f8653f, i5, 0);
        String string = typedArrayObtainStyledAttributes2.getString(34);
        this.f8663g0 = string == null ? typedArrayObtainStyledAttributes2.getString(7) : string;
        typedArrayObtainStyledAttributes2.recycle();
    }

    public final int G(String str) {
        CharSequence[] charSequenceArr;
        if (str == null || (charSequenceArr = this.f8661e0) == null) {
            return -1;
        }
        for (int length = charSequenceArr.length - 1; length >= 0; length--) {
            if (TextUtils.equals(charSequenceArr[length].toString(), str)) {
                return length;
            }
        }
        return -1;
    }

    public final CharSequence H() {
        CharSequence[] charSequenceArr;
        int iG = G(this.f8662f0);
        if (iG < 0 || (charSequenceArr = this.d0) == null) {
            return null;
        }
        return charSequenceArr[iG];
    }

    public final void I(String str) {
        boolean z9 = !TextUtils.equals(this.f8662f0, str);
        if (z9 || !this.f8664h0) {
            this.f8662f0 = str;
            this.f8664h0 = true;
            x(str);
            if (z9) {
                k();
            }
        }
    }

    @Override // androidx.preference.Preference
    public final CharSequence h() {
        W1.a aVar = this.f8699Q;
        if (aVar != null) {
            return aVar.n(this);
        }
        CharSequence charSequenceH = H();
        CharSequence charSequenceH2 = super.h();
        String str = this.f8663g0;
        if (str == null) {
            return charSequenceH2;
        }
        if (charSequenceH == null) {
            charSequenceH = "";
        }
        String str2 = String.format(str, charSequenceH);
        if (TextUtils.equals(str2, charSequenceH2)) {
            return charSequenceH2;
        }
        Log.w("ListPreference", "Setting a summary with a String formatting marker is no longer supported. You should use a SummaryProvider instead.");
        return str2;
    }

    @Override // androidx.preference.Preference
    public final Object r(TypedArray typedArray, int i5) {
        return typedArray.getString(i5);
    }

    @Override // androidx.preference.Preference
    public final void s(Parcelable parcelable) {
        if (!parcelable.getClass().equals(C0311f.class)) {
            super.s(parcelable);
            return;
        }
        C0311f c0311f = (C0311f) parcelable;
        super.s(c0311f.getSuperState());
        I(c0311f.f8797e);
    }

    @Override // androidx.preference.Preference
    public final Parcelable t() {
        this.f8697O = true;
        AbsSavedState absSavedState = AbsSavedState.EMPTY_STATE;
        if (this.f8721v) {
            return absSavedState;
        }
        C0311f c0311f = new C0311f(absSavedState);
        c0311f.f8797e = this.f8662f0;
        return c0311f;
    }

    @Override // androidx.preference.Preference
    public final void u(Object obj) {
        I(g((String) obj));
    }

    public ListPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C.b.a(context, R.attr.dialogPreferenceStyle, android.R.attr.dialogPreferenceStyle));
    }
}
