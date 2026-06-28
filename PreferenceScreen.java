package androidx.preference;

import android.content.Context;
import android.util.AttributeSet;
import com.samsung.android.keyscafe.R;

/* JADX INFO: loaded from: classes.dex */
public final class PreferenceScreen extends PreferenceGroup {

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public final boolean f8766f0;

    public PreferenceScreen(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, C.b.a(context, R.attr.preferenceScreenStyle, android.R.attr.preferenceScreenStyle));
        this.f8766f0 = true;
    }

    @Override // androidx.preference.Preference
    public final void p() {
        F f2;
        if (this.f8716q != null || this.f8717r != null || J() == 0 || (f2 = this.f8707f.f8635j) == null) {
            return;
        }
        f2.onNavigateToScreen(this);
    }
}
