package androidx.fragment.app;

import android.content.DialogInterface;

/* JADX INFO: renamed from: androidx.fragment.app.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class DialogInterfaceOnCancelListenerC0248o implements DialogInterface.OnCancelListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ DialogFragment f7710e;

    public DialogInterfaceOnCancelListenerC0248o(DialogFragment dialogFragment) {
        this.f7710e = dialogFragment;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        DialogFragment dialogFragment = this.f7710e;
        if (dialogFragment.mDialog != null) {
            dialogFragment.onCancel(dialogFragment.mDialog);
        }
    }
}
