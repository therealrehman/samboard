package androidx.fragment.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/* JADX INFO: renamed from: androidx.fragment.app.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0244k extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ C0246m f7682e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ View f7683f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ boolean f7684g;
    public final /* synthetic */ v0 h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ C0241h f7685i;

    public C0244k(C0246m c0246m, View view, boolean z9, v0 v0Var, C0241h c0241h) {
        this.f7682e = c0246m;
        this.f7683f = view;
        this.f7684g = z9;
        this.h = v0Var;
        this.f7685i = c0241h;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator anim) {
        kotlin.jvm.internal.j.f(anim, "anim");
        ViewGroup viewGroup = this.f7682e.f7694a;
        View viewToAnimate = this.f7683f;
        viewGroup.endViewTransition(viewToAnimate);
        boolean z9 = this.f7684g;
        v0 v0Var = this.h;
        if (z9) {
            int i5 = v0Var.f7736a;
            kotlin.jvm.internal.j.e(viewToAnimate, "viewToAnimate");
            A8.l.a(i5, viewToAnimate);
        }
        this.f7685i.a();
        if (Log.isLoggable("FragmentManager", 2)) {
            Log.v("FragmentManager", "Animator from operation " + v0Var + " has ended.");
        }
    }
}
