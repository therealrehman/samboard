package androidx.recyclerview.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;

/* JADX INFO: renamed from: androidx.recyclerview.widget.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0350o extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ V0 f9198e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f9199f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ View f9200g;
    public final /* synthetic */ int h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ ViewPropertyAnimator f9201i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ C0357s f9202j;

    public C0350o(C0357s c0357s, V0 v02, int i5, View view, int i7, ViewPropertyAnimator viewPropertyAnimator) {
        this.f9202j = c0357s;
        this.f9198e = v02;
        this.f9199f = i5;
        this.f9200g = view;
        this.h = i7;
        this.f9201i = viewPropertyAnimator;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        int i5 = this.f9199f;
        View view = this.f9200g;
        if (i5 != 0) {
            view.setTranslationX(0.0f);
        }
        if (this.h != 0) {
            view.setTranslationY(0.0f);
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.f9201i.setListener(null);
        C0357s c0357s = this.f9202j;
        V0 v02 = this.f9198e;
        c0357s.c(v02);
        c0357s.f9263m.remove(v02);
        c0357s.i();
        int i5 = c0357s.f9265p;
        if ((i5 & 2) != 0) {
            c0357s.f9265p = i5 & (-3);
        }
        int i7 = c0357s.f9265p;
        if ((i7 & 8) != 0) {
            c0357s.f9265p = i7 | 16;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.f9202j.getClass();
    }
}
