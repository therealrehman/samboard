package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Rect;
import android.view.View;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: androidx.transition.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0378f extends AnimatorListenerAdapter {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f9423e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ View f9424f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Rect f9425g;
    public final /* synthetic */ int h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9426i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ int f9427j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ int f9428k;

    public C0378f(View view, Rect rect, int i5, int i7, int i9, int i10) {
        this.f9424f = view;
        this.f9425g = rect;
        this.h = i5;
        this.f9426i = i7;
        this.f9427j = i9;
        this.f9428k = i10;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.f9423e = true;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        if (this.f9423e) {
            return;
        }
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        View view = this.f9424f;
        view.setClipBounds(this.f9425g);
        C0376d c0376d = f0.f9429a;
        view.setLeftTopRightBottom(this.h, this.f9426i, this.f9427j, this.f9428k);
    }
}
