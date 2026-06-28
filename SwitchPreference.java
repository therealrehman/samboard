package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.view.W;
import com.samsung.android.keyscafe.R;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SwitchPreference extends TwoStatePreference {

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public final C0306a f8777c0;
    public final CharSequence d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public final CharSequence f8778e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public int f8779f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public int f8780g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public final ViewOnClickListenerC0315j f8781h0;

    /* JADX WARN: Illegal instructions before constructor call */
    public SwitchPreference(Context context, AttributeSet attributeSet) {
        int iA = C.b.a(context, R.attr.switchPreferenceStyle, android.R.attr.switchPreferenceStyle);
        super(context, attributeSet, iA, 0);
        this.f8777c0 = new C0306a(this, 1);
        this.f8780g0 = 0;
        this.f8781h0 = new ViewOnClickListenerC0315j(this, 1);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, L.f8659m, iA, 0);
        this.f8779f0 = 0;
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
        this.f8778e0 = string4 == null ? typedArrayObtainStyledAttributes.getString(4) : string4;
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
            switchCompat.setTextOff(this.f8778e0);
            switchCompat.setOnCheckedChangeListener(this.f8777c0);
            if (switchCompat.isClickable()) {
                switchCompat.setOnClickListener(this.f8781h0);
            }
            if (j()) {
                WeakHashMap weakHashMap = W.f7199a;
                switchCompat.setBackground(null);
                switchCompat.setClickable(false);
            }
        }
    }

    @Override // androidx.preference.Preference
    public final void o(K k5) {
        super.o(k5);
        if (this.f8779f0 != 1) {
            J(k5.a(android.R.id.switch_widget));
        }
        H(k5.a(android.R.id.summary));
    }

    @Override // androidx.preference.Preference
    public final void w(View view) {
        super.w(view);
        AccessibilityManager accessibilityManager = (AccessibilityManager) this.f8706e.getSystemService("accessibility");
        if (accessibilityManager == null || accessibilityManager.isEnabled()) {
            if (this.f8779f0 != 1) {
                J(view.findViewById(android.R.id.switch_widget));
            }
            if (j()) {
                return;
            }
            H(view.findViewById(android.R.id.summary));
        }
    }
}
