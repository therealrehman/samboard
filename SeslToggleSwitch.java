package androidx.appcompat.widget;

import android.content.Context;
import android.util.AttributeSet;

/* JADX INFO: loaded from: classes.dex */
public class SeslToggleSwitch extends SwitchCompat {
    public SeslToggleSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.appcompat.widget.SwitchCompat, android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z9) {
        super.setChecked(z9);
    }

    public void setCheckedInternal(boolean z9) {
        super.setChecked(z9);
    }

    public void setOnBeforeCheckedChangeListener(J1 j12) {
    }
}
