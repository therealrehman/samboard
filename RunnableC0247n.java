package androidx.fragment.app;

import android.widget.ListView;

/* JADX INFO: renamed from: androidx.fragment.app.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0247n implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f7701e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f7702f;

    public /* synthetic */ RunnableC0247n(int i5, Object obj) {
        this.f7701e = i5;
        this.f7702f = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f7701e) {
            case 0:
                DialogFragment dialogFragment = (DialogFragment) this.f7702f;
                dialogFragment.mOnDismissListener.onDismiss(dialogFragment.mDialog);
                break;
            case 1:
                ((C0246m) this.f7702f).h();
                break;
            case 2:
                ((Y) this.f7702f).x(true);
                break;
            default:
                ListView listView = ((ListFragment) this.f7702f).f7530i;
                listView.focusableViewAvailable(listView);
                break;
        }
    }
}
