package androidx.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.AbsSavedState;

/* JADX INFO: loaded from: classes.dex */
public abstract class TwoStatePreference extends Preference {

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public boolean f8787X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public CharSequence f8788Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public CharSequence f8789Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public boolean f8790a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public boolean f8791b0;

    public TwoStatePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0, 0);
    }

    @Override // androidx.preference.Preference
    public final boolean C() {
        return (this.f8791b0 ? this.f8787X : !this.f8787X) || super.C();
    }

    public final void G(boolean z9) {
        boolean z10 = this.f8787X != z9;
        if (z10 || !this.f8790a0) {
            this.f8787X = z9;
            this.f8790a0 = true;
            if (D()) {
                boolean z11 = !z9;
                if (D()) {
                    z11 = this.f8707f.c().getBoolean(this.f8715p, z11);
                }
                if (z9 != z11) {
                    SharedPreferences.Editor editorB = this.f8707f.b();
                    editorB.putBoolean(this.f8715p, z9);
                    E(editorB);
                }
            }
            if (z10) {
                l(C());
                k();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void H(android.view.View r4) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof android.widget.TextView
            if (r0 != 0) goto L5
            return
        L5:
            android.widget.TextView r4 = (android.widget.TextView) r4
            boolean r0 = r3.f8787X
            r1 = 0
            if (r0 == 0) goto L1b
            java.lang.CharSequence r0 = r3.f8788Y
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L1b
            java.lang.CharSequence r0 = r3.f8788Y
            r4.setText(r0)
        L19:
            r0 = r1
            goto L2e
        L1b:
            boolean r0 = r3.f8787X
            if (r0 != 0) goto L2d
            java.lang.CharSequence r0 = r3.f8789Z
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L2d
            java.lang.CharSequence r0 = r3.f8789Z
            r4.setText(r0)
            goto L19
        L2d:
            r0 = 1
        L2e:
            if (r0 == 0) goto L3e
            java.lang.CharSequence r3 = r3.h()
            boolean r2 = android.text.TextUtils.isEmpty(r3)
            if (r2 != 0) goto L3e
            r4.setText(r3)
            r0 = r1
        L3e:
            if (r0 != 0) goto L41
            goto L43
        L41:
            r1 = 8
        L43:
            int r3 = r4.getVisibility()
            if (r1 == r3) goto L4c
            r4.setVisibility(r1)
        L4c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.preference.TwoStatePreference.H(android.view.View):void");
    }

    @Override // androidx.preference.Preference
    public void p() {
        boolean z9 = !this.f8787X;
        a(Boolean.valueOf(z9));
        G(z9);
    }

    @Override // androidx.preference.Preference
    public final Object r(TypedArray typedArray, int i5) {
        return Boolean.valueOf(typedArray.getBoolean(i5, false));
    }

    @Override // androidx.preference.Preference
    public final void s(Parcelable parcelable) {
        if (!parcelable.getClass().equals(P.class)) {
            super.s(parcelable);
            return;
        }
        P p4 = (P) parcelable;
        super.s(p4.getSuperState());
        G(p4.f8683e);
    }

    @Override // androidx.preference.Preference
    public final Parcelable t() {
        this.f8697O = true;
        AbsSavedState absSavedState = AbsSavedState.EMPTY_STATE;
        if (this.f8721v) {
            return absSavedState;
        }
        P p4 = new P(absSavedState);
        p4.f8683e = this.f8787X;
        return p4;
    }

    @Override // androidx.preference.Preference
    public final void u(Object obj) {
        if (obj == null) {
            obj = Boolean.FALSE;
        }
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        if (D()) {
            zBooleanValue = this.f8707f.c().getBoolean(this.f8715p, zBooleanValue);
        }
        G(zBooleanValue);
    }
}
