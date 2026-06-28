package androidx.core.view;

import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: renamed from: androidx.core.view.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ViewTreeObserverOnPreDrawListenerC0227t implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final View f7255e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ViewTreeObserver f7256f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Runnable f7257g;

    public ViewTreeObserverOnPreDrawListenerC0227t(View view, Runnable runnable) {
        this.f7255e = view;
        this.f7256f = view.getViewTreeObserver();
        this.f7257g = runnable;
    }

    public static void a(View view, Runnable runnable) {
        if (view == null) {
            throw new NullPointerException("view == null");
        }
        if (runnable == null) {
            throw new NullPointerException("runnable == null");
        }
        ViewTreeObserverOnPreDrawListenerC0227t viewTreeObserverOnPreDrawListenerC0227t = new ViewTreeObserverOnPreDrawListenerC0227t(view, runnable);
        view.getViewTreeObserver().addOnPreDrawListener(viewTreeObserverOnPreDrawListenerC0227t);
        view.addOnAttachStateChangeListener(viewTreeObserverOnPreDrawListenerC0227t);
    }

    @Override // android.view.ViewTreeObserver.OnPreDrawListener
    public final boolean onPreDraw() {
        boolean zIsAlive = this.f7256f.isAlive();
        View view = this.f7255e;
        if (zIsAlive) {
            this.f7256f.removeOnPreDrawListener(this);
        } else {
            view.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        view.removeOnAttachStateChangeListener(this);
        this.f7257g.run();
        return true;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        this.f7256f = view.getViewTreeObserver();
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        boolean zIsAlive = this.f7256f.isAlive();
        View view2 = this.f7255e;
        if (zIsAlive) {
            this.f7256f.removeOnPreDrawListener(this);
        } else {
            view2.getViewTreeObserver().removeOnPreDrawListener(this);
        }
        view2.removeOnAttachStateChangeListener(this);
    }
}
