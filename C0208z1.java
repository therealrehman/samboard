package androidx.appcompat.widget;

import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import java.lang.ref.WeakReference;

/* JADX INFO: renamed from: androidx.appcompat.widget.z1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0208z1 extends Animatable2.AnimationCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WeakReference f6910a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Handler f6911b = new Handler(Looper.getMainLooper());

    public C0208z1(SeslProgressBar seslProgressBar) {
        this.f6910a = new WeakReference(seslProgressBar);
    }

    @Override // android.graphics.drawable.Animatable2.AnimationCallback
    public final void onAnimationEnd(Drawable drawable) {
        this.f6911b.post(new RunnableC0198w0(2, this));
    }
}
