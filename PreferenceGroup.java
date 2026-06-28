package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.AbsSavedState;
import java.util.ArrayList;
import java.util.Collections;

/* JADX INFO: loaded from: classes.dex */
public abstract class PreferenceGroup extends Preference {

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public final q.k f8758X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public final Handler f8759Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public final ArrayList f8760Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public boolean f8761a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public int f8762b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public boolean f8763c0;
    public int d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public final t f8764e0;

    public PreferenceGroup(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5, 0);
        this.f8758X = new q.k();
        this.f8759Y = new Handler(Looper.getMainLooper());
        this.f8761a0 = true;
        this.f8762b0 = 0;
        this.f8763c0 = false;
        this.d0 = Integer.MAX_VALUE;
        this.f8764e0 = new t(3, this);
        this.f8760Z = new ArrayList();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, L.f8655i, i5, 0);
        this.f8761a0 = typedArrayObtainStyledAttributes.getBoolean(2, typedArrayObtainStyledAttributes.getBoolean(2, true));
        if (typedArrayObtainStyledAttributes.hasValue(1)) {
            L(typedArrayObtainStyledAttributes.getInt(1, typedArrayObtainStyledAttributes.getInt(1, Integer.MAX_VALUE)));
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void G(Preference preference) {
        long jLongValue;
        if (this.f8760Z.contains(preference)) {
            return;
        }
        if (preference.f8715p != null) {
            PreferenceGroup preferenceGroup = this;
            while (true) {
                PreferenceGroup preferenceGroup2 = preferenceGroup.f8696N;
                if (preferenceGroup2 == null) {
                    break;
                } else {
                    preferenceGroup = preferenceGroup2;
                }
            }
            String str = preference.f8715p;
            if (preferenceGroup.H(str) != null) {
                Log.e("PreferenceGroup", "Found duplicated key: \"" + str + "\". This can cause unintended behaviour, please use unique keys for every preference.");
            }
        }
        int i5 = preference.f8711k;
        if (i5 == Integer.MAX_VALUE) {
            if (this.f8761a0) {
                int i7 = this.f8762b0;
                this.f8762b0 = i7 + 1;
                if (i7 != i5) {
                    preference.f8711k = i7;
                    A a10 = preference.f8694L;
                    if (a10 != null) {
                        Handler handler = a10.f8599j;
                        t tVar = a10.f8600k;
                        handler.removeCallbacks(tVar);
                        handler.post(tVar);
                    }
                }
            }
            if (preference instanceof PreferenceGroup) {
                ((PreferenceGroup) preference).f8761a0 = this.f8761a0;
            }
        }
        int iBinarySearch = Collections.binarySearch(this.f8760Z, preference);
        if (iBinarySearch < 0) {
            iBinarySearch = (iBinarySearch * (-1)) - 1;
        }
        boolean zC = C();
        if (preference.f8725z == zC) {
            preference.f8725z = !zC;
            preference.l(preference.C());
            preference.k();
        }
        synchronized (this) {
            this.f8760Z.add(iBinarySearch, preference);
        }
        H h = this.f8707f;
        String str2 = preference.f8715p;
        if (str2 == null || !this.f8758X.containsKey(str2)) {
            synchronized (h) {
                jLongValue = h.f8628b;
                h.f8628b = 1 + jLongValue;
            }
        } else {
            jLongValue = ((Long) this.f8758X.getOrDefault(str2, null)).longValue();
            this.f8758X.remove(str2);
        }
        preference.f8708g = jLongValue;
        preference.h = true;
        try {
            preference.n(h);
            preference.h = false;
            if (preference.f8696N != null) {
                throw new IllegalStateException("This preference already has a parent. You must remove the existing parent before assigning a new one.");
            }
            preference.f8696N = this;
            if (this.f8763c0) {
                preference.m();
            }
            A a11 = this.f8694L;
            if (a11 != null) {
                Handler handler2 = a11.f8599j;
                t tVar2 = a11.f8600k;
                handler2.removeCallbacks(tVar2);
                handler2.post(tVar2);
            }
        } catch (Throwable th) {
            preference.h = false;
            throw th;
        }
    }

    public final Preference H(CharSequence charSequence) {
        Preference preferenceH;
        if (charSequence == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        if (TextUtils.equals(this.f8715p, charSequence)) {
            return this;
        }
        int iJ = J();
        for (int i5 = 0; i5 < iJ; i5++) {
            Preference preferenceI = I(i5);
            if (TextUtils.equals(preferenceI.f8715p, charSequence)) {
                return preferenceI;
            }
            if ((preferenceI instanceof PreferenceGroup) && (preferenceH = ((PreferenceGroup) preferenceI).H(charSequence)) != null) {
                return preferenceH;
            }
        }
        return null;
    }

    public final Preference I(int i5) {
        return (Preference) this.f8760Z.get(i5);
    }

    public final int J() {
        return this.f8760Z.size();
    }

    public final void K(Preference preference) {
        synchronized (this) {
            try {
                preference.F();
                if (preference.f8696N == this) {
                    preference.f8696N = null;
                }
                if (this.f8760Z.remove(preference)) {
                    String str = preference.f8715p;
                    if (str != null) {
                        this.f8758X.put(str, Long.valueOf(preference.f()));
                        this.f8759Y.removeCallbacks(this.f8764e0);
                        this.f8759Y.post(this.f8764e0);
                    }
                    if (this.f8763c0) {
                        preference.q();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        A a10 = this.f8694L;
        if (a10 != null) {
            Handler handler = a10.f8599j;
            t tVar = a10.f8600k;
            handler.removeCallbacks(tVar);
            handler.post(tVar);
        }
    }

    public final void L(int i5) {
        if (i5 != Integer.MAX_VALUE && !(!TextUtils.isEmpty(this.f8715p))) {
            Log.e("PreferenceGroup", getClass().getSimpleName().concat(" should have a key defined if it contains an expandable preference"));
        }
        this.d0 = i5;
    }

    @Override // androidx.preference.Preference
    public final void c(Bundle bundle) {
        super.c(bundle);
        int size = this.f8760Z.size();
        for (int i5 = 0; i5 < size; i5++) {
            I(i5).c(bundle);
        }
    }

    @Override // androidx.preference.Preference
    public final void d(Bundle bundle) {
        super.d(bundle);
        int size = this.f8760Z.size();
        for (int i5 = 0; i5 < size; i5++) {
            I(i5).d(bundle);
        }
    }

    @Override // androidx.preference.Preference
    public final void l(boolean z9) {
        super.l(z9);
        int size = this.f8760Z.size();
        for (int i5 = 0; i5 < size; i5++) {
            Preference preferenceI = I(i5);
            if (preferenceI.f8725z == z9) {
                preferenceI.f8725z = !z9;
                preferenceI.l(preferenceI.C());
                preferenceI.k();
            }
        }
    }

    @Override // androidx.preference.Preference
    public final void m() {
        super.m();
        this.f8763c0 = true;
        int iJ = J();
        for (int i5 = 0; i5 < iJ; i5++) {
            I(i5).m();
        }
    }

    @Override // androidx.preference.Preference
    public final void q() {
        F();
        this.f8763c0 = false;
        int iJ = J();
        for (int i5 = 0; i5 < iJ; i5++) {
            I(i5).q();
        }
    }

    @Override // androidx.preference.Preference
    public final void s(Parcelable parcelable) {
        if (!parcelable.getClass().equals(y.class)) {
            super.s(parcelable);
            return;
        }
        y yVar = (y) parcelable;
        this.d0 = yVar.f8830e;
        super.s(yVar.getSuperState());
    }

    @Override // androidx.preference.Preference
    public final Parcelable t() {
        this.f8697O = true;
        return new y(AbsSavedState.EMPTY_STATE, this.d0);
    }

    public PreferenceGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }
}
