package androidx.preference;

import android.content.Context;
import android.util.AttributeSet;
import com.samsung.android.keyscafe.R;

/* JADX INFO: loaded from: classes.dex */
public class PreferenceCategory extends PreferenceGroup {
    public PreferenceCategory(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, C.b.a(context, R.attr.preferenceCategoryStyle, android.R.attr.preferenceCategoryStyle));
    }

    @Override // androidx.preference.Preference
    public final boolean C() {
        return !super.i();
    }

    @Override // androidx.preference.Preference
    public final boolean i() {
        return false;
    }

    @Override // androidx.preference.Preference
    public final void o(K k5) {
        super.o(k5);
        k5.itemView.setAccessibilityHeading(true);
    }
}
