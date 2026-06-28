package androidx.preference;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.samsung.android.keyscafe.R;

/* JADX INFO: loaded from: classes.dex */
public class SeslRingtonePreference extends Preference {
    public SeslRingtonePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.ringtonePreferenceStyle, 0);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, L.f8657k, R.attr.ringtonePreferenceStyle, 0);
        typedArrayObtainStyledAttributes.getInt(0, 1);
        typedArrayObtainStyledAttributes.getBoolean(1, true);
        typedArrayObtainStyledAttributes.getBoolean(2, true);
        this.f8716q = new Intent("android.intent.action.RINGTONE_PICKER");
        com.bumptech.glide.c.H();
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.preference.Preference
    public final void n(H h) {
        super.n(h);
    }

    @Override // androidx.preference.Preference
    public final Object r(TypedArray typedArray, int i5) {
        return typedArray.getString(i5);
    }

    @Override // androidx.preference.Preference
    public final void v(Object obj, boolean z9) {
        String str = (String) obj;
        if (z9 || TextUtils.isEmpty(str)) {
            return;
        }
        Uri uri = Uri.parse(str);
        x(uri != null ? uri.toString() : "");
    }
}
