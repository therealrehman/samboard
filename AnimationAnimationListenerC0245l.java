package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.animation.Animation;

/* JADX INFO: renamed from: androidx.fragment.app.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class AnimationAnimationListenerC0245l implements Animation.AnimationListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ v0 f7688a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ C0246m f7689b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ View f7690c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ C0241h f7691d;

    public AnimationAnimationListenerC0245l(View view, C0241h c0241h, C0246m c0246m, v0 v0Var) {
        this.f7688a = v0Var;
        this.f7689b = c0246m;
        this.f7690c = view;
        this.f7691d = c0241h;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationEnd(Animation animation) {
        kotlin.jvm.internal.j.f(animation, "animation");
        C0246m c0246m = this.f7689b;
        c0246m.f7694a.post(new RunnableC0237d(c0246m, this.f7690c, this.f7691d));
        if (Log.isLoggable("FragmentManager", 2)) {
            Log.v("FragmentManager", "Animation from operation " + this.f7688a + " has ended.");
        }
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationRepeat(Animation animation) {
        kotlin.jvm.internal.j.f(animation, "animation");
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationStart(Animation animation) {
        kotlin.jvm.internal.j.f(animation, "animation");
        if (Log.isLoggable("FragmentManager", 2)) {
            Log.v("FragmentManager", "Animation from operation " + this.f7688a + " has reached onAnimationStart.");
        }
    }
}
