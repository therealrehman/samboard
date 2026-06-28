package androidx.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.AbsSavedState;
import com.samsung.android.keyscafe.R;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class MultiSelectListPreference extends DialogPreference {
    public final CharSequence[] d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public final CharSequence[] f8670e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public final HashSet f8671f0;

    /* JADX WARN: Illegal instructions before constructor call */
    public MultiSelectListPreference(Context context, AttributeSet attributeSet) {
        int iA = C.b.a(context, R.attr.dialogPreferenceStyle, android.R.attr.dialogPreferenceStyle);
        super(context, attributeSet, iA);
        this.f8671f0 = new HashSet();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, L.f8652e, iA, 0);
        CharSequence[] textArray = typedArrayObtainStyledAttributes.getTextArray(2);
        this.d0 = textArray == null ? typedArrayObtainStyledAttributes.getTextArray(0) : textArray;
        CharSequence[] textArray2 = typedArrayObtainStyledAttributes.getTextArray(3);
        this.f8670e0 = textArray2 == null ? typedArrayObtainStyledAttributes.getTextArray(1) : textArray2;
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void G(Set set) {
        HashSet hashSet = this.f8671f0;
        hashSet.clear();
        hashSet.addAll(set);
        if (D()) {
            if (!set.equals(D() ? this.f8707f.c().getStringSet(this.f8715p, null) : null)) {
                SharedPreferences.Editor editorB = this.f8707f.b();
                editorB.putStringSet(this.f8715p, set);
                E(editorB);
            }
        }
        k();
    }

    @Override // androidx.preference.Preference
    public final Object r(TypedArray typedArray, int i5) {
        CharSequence[] textArray = typedArray.getTextArray(i5);
        HashSet hashSet = new HashSet();
        for (CharSequence charSequence : textArray) {
            hashSet.add(charSequence.toString());
        }
        return hashSet;
    }

    @Override // androidx.preference.Preference
    public final void s(Parcelable parcelable) {
        if (!parcelable.getClass().equals(C0313h.class)) {
            super.s(parcelable);
            return;
        }
        C0313h c0313h = (C0313h) parcelable;
        super.s(c0313h.getSuperState());
        G(c0313h.f8800e);
    }

    @Override // androidx.preference.Preference
    public final Parcelable t() {
        this.f8697O = true;
        AbsSavedState absSavedState = AbsSavedState.EMPTY_STATE;
        if (this.f8721v) {
            return absSavedState;
        }
        C0313h c0313h = new C0313h(absSavedState);
        c0313h.f8800e = this.f8671f0;
        return c0313h;
    }

    @Override // androidx.preference.Preference
    public final void u(Object obj) {
        Set<String> stringSet = (Set) obj;
        if (D()) {
            stringSet = this.f8707f.c().getStringSet(this.f8715p, stringSet);
        }
        G(stringSet);
    }
}
