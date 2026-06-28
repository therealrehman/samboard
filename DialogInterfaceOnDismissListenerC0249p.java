package androidx.fragment.app;

import android.content.DialogInterface;

/* JADX INFO: renamed from: androidx.fragment.app.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class DialogInterfaceOnDismissListenerC0249p implements DialogInterface.OnDismissListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ DialogFragment f7712e;

    public DialogInterfaceOnDismissListenerC0249p(DialogFragment dialogFragment) {
        this.f7712e = dialogFragment;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        DialogFragment dialogFragment = this.f7712e;
        if (dialogFragment.mDialog != null) {
            dialogFragment.onDismiss(dialogFragment.mDialog);
        }
    }
}
