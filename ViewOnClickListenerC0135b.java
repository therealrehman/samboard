package androidx.appcompat.widget;

import android.view.View;
import l.AbstractC0660b;

/* JADX INFO: renamed from: androidx.appcompat.widget.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ViewOnClickListenerC0135b implements View.OnClickListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6672e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f6673f;

    public /* synthetic */ ViewOnClickListenerC0135b(int i5, Object obj) {
        this.f6672e = i5;
        this.f6673f = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.f6672e) {
            case 0:
                ((AbstractC0660b) this.f6673f).a();
                break;
            case 1:
                SeslSwitchBar seslSwitchBar = (SeslSwitchBar) this.f6673f;
                SeslToggleSwitch seslToggleSwitch = seslSwitchBar.f6623f;
                if (seslToggleSwitch != null && seslToggleSwitch.isEnabled()) {
                    seslSwitchBar.f6623f.setChecked(!r0.isChecked());
                    break;
                }
                break;
            default:
                ((Toolbar) this.f6673f).collapseActionView();
                break;
        }
    }
}
