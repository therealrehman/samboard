package androidx.preference;

import android.view.View;

/* JADX INFO: renamed from: androidx.preference.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ViewOnClickListenerC0315j implements View.OnClickListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f8805e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Preference f8806f;

    public /* synthetic */ ViewOnClickListenerC0315j(Preference preference, int i5) {
        this.f8805e = i5;
        this.f8806f = preference;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.f8805e) {
            case 0:
                this.f8806f.w(view);
                break;
            case 1:
                ((SwitchPreference) this.f8806f).b();
                break;
            default:
                ((SwitchPreferenceCompat) this.f8806f).b();
                break;
        }
    }
}
