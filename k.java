package com.bumptech.glide;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import com.samsung.android.keyscafe.R;
import java.util.ArrayList;
import n2.C0793h;
import n2.InterfaceC0788c;
import o2.ViewTreeObserverOnPreDrawListenerC0804c;
import p2.InterfaceC0823c;

/* JADX INFO: loaded from: classes.dex */
public final class k implements o2.f {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final o2.d f9968e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final View f9969f;

    public k(ImageView imageView) {
        r2.f.c(imageView, "Argument must not be null");
        this.f9969f = imageView;
        this.f9968e = new o2.d(imageView);
    }

    @Override // o2.f
    public final InterfaceC0788c getRequest() {
        Object tag = this.f9969f.getTag(R.id.glide_custom_view_target_tag);
        if (tag == null) {
            return null;
        }
        if (tag instanceof InterfaceC0788c) {
            return (InterfaceC0788c) tag;
        }
        throw new IllegalArgumentException("You must not pass non-R.id ids to setTag(id)");
    }

    @Override // o2.f
    public final void getSize(o2.e eVar) throws Throwable {
        o2.d dVar = this.f9968e;
        View view = dVar.f12581a;
        int paddingRight = view.getPaddingRight() + view.getPaddingLeft();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int iA = dVar.a(view.getWidth(), layoutParams != null ? layoutParams.width : 0, paddingRight);
        View view2 = dVar.f12581a;
        int paddingBottom = view2.getPaddingBottom() + view2.getPaddingTop();
        ViewGroup.LayoutParams layoutParams2 = view2.getLayoutParams();
        int iA2 = dVar.a(view2.getHeight(), layoutParams2 != null ? layoutParams2.height : 0, paddingBottom);
        if ((iA > 0 || iA == Integer.MIN_VALUE) && (iA2 > 0 || iA2 == Integer.MIN_VALUE)) {
            ((C0793h) eVar).n(iA, iA2);
            return;
        }
        ArrayList arrayList = dVar.f12582b;
        if (!arrayList.contains(eVar)) {
            arrayList.add(eVar);
        }
        if (dVar.f12583c == null) {
            ViewTreeObserver viewTreeObserver = view2.getViewTreeObserver();
            ViewTreeObserverOnPreDrawListenerC0804c viewTreeObserverOnPreDrawListenerC0804c = new ViewTreeObserverOnPreDrawListenerC0804c(dVar);
            dVar.f12583c = viewTreeObserverOnPreDrawListenerC0804c;
            viewTreeObserver.addOnPreDrawListener(viewTreeObserverOnPreDrawListenerC0804c);
        }
    }

    @Override // k2.InterfaceC0641e
    public final void onDestroy() {
    }

    @Override // o2.f
    public final void onLoadCleared(Drawable drawable) {
        o2.d dVar = this.f9968e;
        ViewTreeObserver viewTreeObserver = dVar.f12581a.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnPreDrawListener(dVar.f12583c);
        }
        dVar.f12583c = null;
        dVar.f12582b.clear();
    }

    @Override // o2.f
    public final void onLoadFailed(Drawable drawable) {
    }

    @Override // o2.f
    public final void onLoadStarted(Drawable drawable) {
    }

    @Override // o2.f
    public final void onResourceReady(Object obj, InterfaceC0823c interfaceC0823c) {
    }

    @Override // k2.InterfaceC0641e
    public final void onStart() {
    }

    @Override // k2.InterfaceC0641e
    public final void onStop() {
    }

    @Override // o2.f
    public final void removeCallback(o2.e eVar) {
        this.f9968e.f12582b.remove(eVar);
    }

    @Override // o2.f
    public final void setRequest(InterfaceC0788c interfaceC0788c) {
        this.f9969f.setTag(R.id.glide_custom_view_target_tag, interfaceC0788c);
    }

    public final String toString() {
        return "Target for: " + this.f9969f;
    }
}
