package androidx.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.samsung.android.keyscafe.R;
import f6.AbstractC0527a;

/* JADX INFO: loaded from: classes.dex */
public class SeslSwitchPreferenceScreen extends SwitchPreferenceCompat {

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public final N f8776i0;

    public SeslSwitchPreferenceScreen(Context context, AttributeSet attributeSet, int i5, int i7) {
        super(context, attributeSet, i5, i7);
        this.f8776i0 = new N(this, 1);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, L.f8653f, i5, i7);
        String string = typedArrayObtainStyledAttributes.getString(13);
        if (string == null || string.equals("")) {
            Log.w("SwitchPreferenceScreen", "SwitchPreferenceScreen should getfragment property. Fragment property does not exsit in SwitchPreferenceScreen");
        }
        this.f8691I = R.layout.sesl_preference_switch_screen;
        this.f8692J = R.layout.sesl_switch_preference_screen_widget_divider;
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.preference.Preference
    public final void b() {
    }

    @Override // androidx.preference.SwitchPreferenceCompat, androidx.preference.Preference
    public final void o(K k5) {
        super.o(k5);
        k5.itemView.setOnKeyListener(this.f8776i0);
        TextView textView = (TextView) k5.a(android.R.id.title);
        View viewA = k5.a(android.R.id.switch_widget);
        View viewA2 = k5.a(R.id.switch_widget);
        if (textView == null || viewA == null || viewA2 == null) {
            return;
        }
        com.bumptech.glide.d.C(AbstractC0527a.r(), viewA);
        viewA.setContentDescription(textView.getText().toString());
        viewA2.setContentDescription(textView.getText().toString());
    }

    @Override // androidx.preference.TwoStatePreference, androidx.preference.Preference
    public final void p() {
    }

    public SeslSwitchPreferenceScreen(Context context, AttributeSet attributeSet, int i5) {
        this(context, attributeSet, i5, 0);
    }

    public SeslSwitchPreferenceScreen(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.switchPreferenceStyle);
    }

    public SeslSwitchPreferenceScreen(Context context) {
        this(context, null);
    }
}
