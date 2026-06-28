package androidx.preference;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;

/* JADX INFO: loaded from: classes.dex */
public class CheckBoxPreference extends TwoStatePreference {

    /* JADX INFO: renamed from: c0, reason: collision with root package name */
    public final C0306a f8607c0;

    public CheckBoxPreference(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5, 0);
        this.f8607c0 = new C0306a(this, 0);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, L.f8648a, i5, 0);
        String string = typedArrayObtainStyledAttributes.getString(5);
        this.f8788Y = string == null ? typedArrayObtainStyledAttributes.getString(0) : string;
        if (this.f8787X) {
            k();
        }
        String string2 = typedArrayObtainStyledAttributes.getString(4);
        this.f8789Z = string2 == null ? typedArrayObtainStyledAttributes.getString(1) : string2;
        if (!this.f8787X) {
            k();
        }
        this.f8791b0 = typedArrayObtainStyledAttributes.getBoolean(3, typedArrayObtainStyledAttributes.getBoolean(2, false));
        typedArrayObtainStyledAttributes.recycle();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void I(View view) {
        boolean z9 = view instanceof CompoundButton;
        if (z9) {
            ((CompoundButton) view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable) {
            ((Checkable) view).setChecked(this.f8787X);
        }
        if (z9) {
            ((CompoundButton) view).setOnCheckedChangeListener(this.f8607c0);
        }
    }

    @Override // androidx.preference.Preference
    public void o(K k5) {
        super.o(k5);
        I(k5.a(R.id.checkbox));
        H(k5.a(R.id.summary));
    }

    @Override // androidx.preference.Preference
    public final void w(View view) {
        super.w(view);
        if (((AccessibilityManager) this.f8706e.getSystemService("accessibility")).isEnabled()) {
            I(view.findViewById(R.id.checkbox));
            if (j()) {
                return;
            }
            H(view.findViewById(R.id.summary));
        }
    }

    public CheckBoxPreference(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C.b.a(context, com.samsung.android.keyscafe.R.attr.checkBoxPreferenceStyle, R.attr.checkBoxPreferenceStyle));
    }
}
