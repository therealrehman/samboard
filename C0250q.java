package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import androidx.lifecycle.InterfaceC0276s;

/* JADX INFO: renamed from: androidx.fragment.app.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0250q implements androidx.lifecycle.A {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ DialogFragment f7713e;

    public C0250q(DialogFragment dialogFragment) {
        this.f7713e = dialogFragment;
    }

    @Override // androidx.lifecycle.A
    public final void onChanged(Object obj) {
        if (((InterfaceC0276s) obj) != null) {
            DialogFragment dialogFragment = this.f7713e;
            if (dialogFragment.mShowsDialog) {
                View viewRequireView = dialogFragment.requireView();
                if (viewRequireView.getParent() != null) {
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
                if (dialogFragment.mDialog != null) {
                    if (Log.isLoggable("FragmentManager", 3)) {
                        Log.d("SeslDialogFragment", "DialogFragment " + this + " setting the content view on " + dialogFragment.mDialog);
                    }
                    dialogFragment.mDialog.setContentView(viewRequireView);
                }
            }
        }
    }
}
