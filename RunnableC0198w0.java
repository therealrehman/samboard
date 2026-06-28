package androidx.appcompat.widget;

import android.graphics.drawable.AnimatedVectorDrawable;

/* JADX INFO: renamed from: androidx.appcompat.widget.w0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0198w0 implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6870e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f6871f;

    public /* synthetic */ RunnableC0198w0(int i5, Object obj) {
        this.f6870e = i5;
        this.f6871f = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f6870e) {
            case 0:
                C0201x0 c0201x0 = (C0201x0) this.f6871f;
                c0201x0.f6889p = null;
                c0201x0.drawableStateChanged();
                break;
            case 1:
                ((SearchView) this.f6871f).n();
                break;
            case 2:
                SeslProgressBar seslProgressBar = (SeslProgressBar) ((C0208z1) this.f6871f).f6910a.get();
                if (seslProgressBar != null) {
                    ((AnimatedVectorDrawable) seslProgressBar.G).start();
                    break;
                }
                break;
            default:
                ((Toolbar) this.f6871f).showOverflowMenu();
                break;
        }
    }
}
