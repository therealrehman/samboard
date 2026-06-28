package androidx.appcompat.widget;

import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;

/* JADX INFO: renamed from: androidx.appcompat.widget.z0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0207z0 implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f6908e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ A0 f6909f;

    public /* synthetic */ RunnableC0207z0(A0 a02, int i5) {
        this.f6908e = i5;
        this.f6909f = a02;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f6908e) {
            case 0:
                ViewParent parent = this.f6909f.h.getParent();
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
                break;
            default:
                A0 a02 = this.f6909f;
                a02.a();
                View view = a02.h;
                if (view.isEnabled() && !view.isLongClickable() && a02.c()) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    long jUptimeMillis = SystemClock.uptimeMillis();
                    MotionEvent motionEventObtain = MotionEvent.obtain(jUptimeMillis, jUptimeMillis, 3, 0.0f, 0.0f, 0);
                    view.onTouchEvent(motionEventObtain);
                    motionEventObtain.recycle();
                    a02.f6308k = true;
                    break;
                }
                break;
        }
    }
}
