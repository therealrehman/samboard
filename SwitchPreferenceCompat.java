package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.view.W;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SwitchPreferenceCompat extends TwoStatePreference {

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public final C0306a f8782c0;
    public final CharSequence d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public final CharSequence f8783e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public int f8784f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public int f8785g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public final ViewOnClickListenerC0315j f8786h0;

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet, int i5, int i7) {
        super(context, attributeSet, i5, i7);
        this.f8782c0 = new C0306a(this, 2);
        this.f8785g0 = 0;
        this.f8786h0 = new ViewOnClickListenerC0315j(this, 2);
        this.f8784f0 = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, L.f8660n, i5, i7);
        String string = typedArrayObtainStyledAttributes.getString(7);
        this.f8788Y = string == null ? typedArrayObtainStyledAttributes.getString(0) : string;
        if (this.f8787X) {
            k();
        }
        String string2 = typedArrayObtainStyledAttributes.getString(6);
        this.f8789Z = string2 == null ? typedArrayObtainStyledAttributes.getString(1) : string2;
        if (!this.f8787X) {
            k();
        }
        String string3 = typedArrayObtainStyledAttributes.getString(9);
        this.d0 = string3 == null ? typedArrayObtainStyledAttributes.getString(3) : string3;
        k();
        String string4 = typedArrayObtainStyledAttributes.getString(8);
        this.f8783e0 = string4 == null ? typedArrayObtainStyledAttributes.getString(4) : string4;
        k();
        this.f8791b0 = typedArrayObtainStyledAttributes.getBoolean(5, typedArrayObtainStyledAttributes.getBoolean(2, false));
        typedArrayObtainStyledAttributes.recycle();
    }

    public static boolean I(boolean z9, View view, SwitchCompat switchCompat) {
        return z9 != switchCompat.isChecked() && view.hasWindowFocus() && com.bumptech.glide.d.t(null, view) && !view.isTemporarilyDetached();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void J(View view) {
        boolean z9 = view instanceof SwitchCompat;
        if (z9) {
            ((SwitchCompat) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.f8787X);
        }
        if (z9) {
            SwitchCompat switchCompat = (SwitchCompat) view;
            switchCompat.setTextOn(this.d0);
            switchCompat.setTextOff(this.f8783e0);
            switchCompat.setOnCheckedChangeListener(this.f8782c0);
            if (switchCompat.isClickable()) {
                switchCompat.setOnClickListener(this.f8786h0);
            }
            if (!j() || (this instanceof SeslSwitchPreferenceScreen)) {
                return;
            }
            WeakHashMap weakHashMap = W.f7199a;
            switchCompat.setBackground(null);
            switchCompat.setClickable(false);
        }
    }

    @Override // androidx.preference.Preference
    public void o(K k5) {
        super.o(k5);
        if (this.f8784f0 != 1) {
            J(k5.a(R.id.switch_widget));
        }
        H(k5.a(R.id.summary));
    }

    @Override // androidx.preference.Preference
    public final void w(View view) {
        super.w(view);
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.f8706e.getSystemService("accessibility");
        if (accessibilityManager == null || accessibilityManager.isEnabled()) {
            if (this.f8784f0 != 1) {
                J(view.findViewById(R.id.switch_widget));
            }
            if (j()) {
                return;
            }
            H(view.findViewById(R.id.summary));
        }
    }

    public SwitchPreferenceCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.samsung.android.keyscafe.R.attr.switchPreferenceCompatStyle, 0);
    }
}
