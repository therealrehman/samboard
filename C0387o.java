package androidx.transition;

import android.view.View;
import com.samsung.android.keyscafe.R;
import java.util.ArrayList;

/* JADX INFO: renamed from: androidx.transition.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0387o extends W {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f9477e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f9478f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f9479g;

    public /* synthetic */ C0387o() {
    }

    @Override // androidx.transition.T
    public final void onTransitionEnd(U u5) {
        switch (this.f9477e) {
            case 0:
                u5.removeListener(this);
                View view = (View) this.f9478f;
                int i5 = E.f9351k;
                E e3 = (E) view.getTag(R.id.ghost_view);
                if (e3 != null) {
                    int i7 = e3.h - 1;
                    e3.h = i7;
                    if (i7 <= 0) {
                        ((D) e3.getParent()).removeView(e3);
                    }
                }
                view.setTag(R.id.transition_transform, null);
                view.setTag(R.id.parent_matrix, null);
                break;
            default:
                ((ArrayList) ((q.b) this.f9478f).getOrDefault(((X) this.f9479g).f9391f, null)).remove(u5);
                u5.removeListener(this);
                break;
        }
    }

    @Override // androidx.transition.W, androidx.transition.T
    public void onTransitionPause(U u5) {
        switch (this.f9477e) {
            case 0:
                ((C) this.f9479g).setVisibility(4);
                break;
        }
    }

    @Override // androidx.transition.W, androidx.transition.T
    public void onTransitionResume(U u5) {
        switch (this.f9477e) {
            case 0:
                ((C) this.f9479g).setVisibility(0);
                break;
        }
    }

    public C0387o(X x9, q.b bVar) {
        this.f9479g = x9;
        this.f9478f = bVar;
    }
}
