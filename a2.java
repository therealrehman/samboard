package androidx.appcompat.widget;

import android.view.View;
import android.view.Window;
import androidx.appcompat.view.menu.C0128a;

/* JADX INFO: loaded from: classes.dex */
public final class a2 implements View.OnClickListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0128a f6670e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ c2 f6671f;

    public a2(c2 c2Var) {
        this.f6671f = c2Var;
        this.f6670e = new C0128a(c2Var.f6679a.getContext(), c2Var.h);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        c2 c2Var = this.f6671f;
        Window.Callback callback = c2Var.f6688k;
        if (callback == null || !c2Var.f6689l) {
            return;
        }
        callback.onMenuItemSelected(0, this.f6670e);
    }
}
