package androidx.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.AbsSavedState;
import androidx.appcompat.widget.SeslSeekBar;
import com.samsung.android.keyscafe.R;

/* JADX INFO: loaded from: classes.dex */
public class SeekBarPreference extends Preference {

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public int f8767X;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public int f8768Y;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public int f8769Z;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public int f8770a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public boolean f8771b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public SeslSeekBar f8772c0;
    public final boolean d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public final boolean f8773e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public final M f8774f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public final N f8775g0;

    public SeekBarPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.seekBarPreferenceStyle, 0);
        this.f8774f0 = new M(this);
        this.f8775g0 = new N(this, 0);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, L.f8658l, R.attr.seekBarPreferenceStyle, 0);
        this.f8768Y = typedArrayObtainStyledAttributes.getInt(3, 0);
        int i5 = typedArrayObtainStyledAttributes.getInt(1, 100);
        int i7 = this.f8768Y;
        i5 = i5 < i7 ? i7 : i5;
        if (i5 != this.f8769Z) {
            this.f8769Z = i5;
            k();
        }
        int i9 = typedArrayObtainStyledAttributes.getInt(4, 0);
        if (i9 != this.f8770a0) {
            this.f8770a0 = Math.min(this.f8769Z - this.f8768Y, Math.abs(i9));
            k();
        }
        this.d0 = typedArrayObtainStyledAttributes.getBoolean(2, true);
        typedArrayObtainStyledAttributes.getBoolean(5, false);
        this.f8773e0 = typedArrayObtainStyledAttributes.getBoolean(6, false);
        typedArrayObtainStyledAttributes.recycle();
    }

    public static void G(SeekBarPreference seekBarPreference, SeslSeekBar seslSeekBar) {
        int progress = seslSeekBar.getProgress() + seekBarPreference.f8768Y;
        if (progress != seekBarPreference.f8767X) {
            seekBarPreference.a(Integer.valueOf(progress));
            seekBarPreference.H(progress, false);
        }
    }

    public final void H(int i5, boolean z9) {
        int i7 = this.f8768Y;
        if (i5 < i7) {
            i5 = i7;
        }
        int i9 = this.f8769Z;
        if (i5 > i9) {
            i5 = i9;
        }
        if (i5 != this.f8767X) {
            this.f8767X = i5;
            if (D()) {
                int i10 = ~i5;
                if (D()) {
                    i10 = this.f8707f.c().getInt(this.f8715p, i10);
                }
                if (i5 != i10) {
                    SharedPreferences.Editor editorB = this.f8707f.b();
                    editorB.putInt(this.f8715p, i5);
                    E(editorB);
                }
            }
            if (z9) {
                k();
            }
        }
    }

    @Override // androidx.preference.Preference
    public final void o(K k5) {
        super.o(k5);
        k5.itemView.setOnKeyListener(this.f8775g0);
        SeslSeekBar seslSeekBar = (SeslSeekBar) k5.a(R.id.seekbar);
        this.f8772c0 = seslSeekBar;
        if (seslSeekBar == null) {
            Log.e("SeekBarPreference", "SeekBar view is null in onBindViewHolder.");
            return;
        }
        seslSeekBar.setOnSeekBarChangeListener(this.f8774f0);
        this.f8772c0.setMax(this.f8769Z - this.f8768Y);
        int i5 = this.f8770a0;
        if (i5 != 0) {
            this.f8772c0.setKeyProgressIncrement(i5);
        } else {
            this.f8770a0 = this.f8772c0.getKeyProgressIncrement();
        }
        this.f8772c0.setProgress(this.f8767X - this.f8768Y);
        this.f8772c0.setEnabled(i());
    }

    @Override // androidx.preference.Preference
    public final Object r(TypedArray typedArray, int i5) {
        return Integer.valueOf(typedArray.getInt(i5, 0));
    }

    @Override // androidx.preference.Preference
    public final void s(Parcelable parcelable) {
        if (!parcelable.getClass().equals(O.class)) {
            super.s(parcelable);
            return;
        }
        O o = (O) parcelable;
        super.s(o.getSuperState());
        this.f8767X = o.f8680e;
        this.f8768Y = o.f8681f;
        this.f8769Z = o.f8682g;
        k();
    }

    @Override // androidx.preference.Preference
    public final Parcelable t() {
        this.f8697O = true;
        AbsSavedState absSavedState = AbsSavedState.EMPTY_STATE;
        if (this.f8721v) {
            return absSavedState;
        }
        O o = new O(absSavedState);
        o.f8680e = this.f8767X;
        o.f8681f = this.f8768Y;
        o.f8682g = this.f8769Z;
        return o;
    }

    @Override // androidx.preference.Preference
    public final void u(Object obj) {
        if (obj == null) {
            obj = 0;
        }
        int iIntValue = ((Integer) obj).intValue();
        if (D()) {
            iIntValue = this.f8707f.c().getInt(this.f8715p, iIntValue);
        }
        H(iIntValue, true);
    }
}
