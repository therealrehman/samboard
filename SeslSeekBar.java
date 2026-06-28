package androidx.appcompat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.SeekBar;

/* JADX INFO: loaded from: classes.dex */
public class SeslSeekBar extends AbstractC0176o1 {

    /* JADX INFO: renamed from: f1, reason: collision with root package name */
    public int f6620f1;

    /* JADX INFO: renamed from: g1, reason: collision with root package name */
    public D1 f6621g1;

    public SeslSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // androidx.appcompat.widget.AbstractC0176o1
    public final void D() {
        super.D();
        D1 d12 = this.f6621g1;
        if (d12 != null) {
            d12.onStopTrackingTouch(this);
        }
    }

    @Override // androidx.appcompat.widget.AbstractC0176o1, androidx.appcompat.widget.SeslProgressBar, android.view.View
    public CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    @Override // androidx.appcompat.widget.AbstractC0176o1, androidx.appcompat.widget.SeslProgressBar
    public final void j(float f2, boolean z9, int i5) {
        super.j(f2, z9, i5);
        if (!this.f6805c1) {
            D1 d12 = this.f6621g1;
            if (d12 != null) {
                d12.onProgressChanged(this, i5, z9);
                return;
            }
            return;
        }
        int iRound = Math.round(i5 / 1000.0f);
        if (this.f6620f1 != iRound) {
            this.f6620f1 = iRound;
            D1 d13 = this.f6621g1;
            if (d13 != null) {
                d13.onProgressChanged(this, iRound, z9);
            }
        }
    }

    @Override // androidx.appcompat.widget.AbstractC0176o1, androidx.appcompat.widget.SeslProgressBar, android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        boolean z9;
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        synchronized (this) {
            z9 = this.f6576B;
        }
        if (z9 || !isEnabled()) {
            return;
        }
        accessibilityNodeInfo.addAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS);
    }

    public void setOnSeekBarChangeListener(D1 d12) {
        this.f6621g1 = d12;
    }

    public void setOnSeekBarHoverListener(E1 e12) {
    }
}
