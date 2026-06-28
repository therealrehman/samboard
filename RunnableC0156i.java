package androidx.appcompat.widget;

import android.view.View;
import androidx.appcompat.view.menu.AbstractC0131d;

/* JADX INFO: renamed from: androidx.appcompat.widget.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0156i implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0150g f6720e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ C0171n f6721f;

    public RunnableC0156i(C0171n c0171n, C0150g c0150g) {
        this.f6721f = c0171n;
        this.f6720e = c0150g;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C0171n c0171n = this.f6721f;
        if (((AbstractC0131d) c0171n).mMenu != null) {
            ((AbstractC0131d) c0171n).mMenu.changeMenuMode();
        }
        View view = (View) ((AbstractC0131d) c0171n).mMenuView;
        if (view != null && view.getWindowToken() != null) {
            C0150g c0150g = this.f6720e;
            if (c0150g.tryShow(0, 0)) {
                c0171n.o = c0150g;
            }
        }
        c0171n.f6772q = null;
    }
}
