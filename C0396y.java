package androidx.transition;

import android.view.View;
import java.util.ArrayList;

/* JADX INFO: renamed from: androidx.transition.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0396y implements T {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ View f9507e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ ArrayList f9508f;

    public C0396y(View view, ArrayList arrayList) {
        this.f9507e = view;
        this.f9508f = arrayList;
    }

    @Override // androidx.transition.T
    public final void onTransitionCancel(U u5) {
    }

    @Override // androidx.transition.T
    public final void onTransitionEnd(U u5) {
        u5.removeListener(this);
        this.f9507e.setVisibility(8);
        ArrayList arrayList = this.f9508f;
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            ((View) arrayList.get(i5)).setVisibility(0);
        }
    }

    @Override // androidx.transition.T
    public final void onTransitionPause(U u5) {
    }

    @Override // androidx.transition.T
    public final void onTransitionResume(U u5) {
    }

    @Override // androidx.transition.T
    public final void onTransitionStart(U u5) {
        u5.removeListener(this);
        u5.addListener(this);
    }
}
