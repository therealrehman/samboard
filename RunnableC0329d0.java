package androidx.recyclerview.widget;

import android.util.Log;

/* JADX INFO: renamed from: androidx.recyclerview.widget.d0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0329d0 implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f9124e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f9125f;

    public RunnableC0329d0(RecyclerView recyclerView, int i5) {
        this.f9125f = recyclerView;
        this.f9124e = i5;
    }

    @Override // java.lang.Runnable
    public final void run() {
        RecyclerView recyclerView = this.f9125f;
        if (recyclerView.mLayoutSuppressed) {
            return;
        }
        AbstractC0370y0 abstractC0370y0 = recyclerView.mLayout;
        if (abstractC0370y0 == null) {
            Log.e("SeslRecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
            return;
        }
        boolean z9 = abstractC0370y0 instanceof LinearLayoutManager;
        int i5 = this.f9124e;
        if (z9) {
            ((LinearLayoutManager) abstractC0370y0).smoothScrollToPositionJumpIfNeeded(recyclerView, recyclerView.mState, i5);
        } else {
            abstractC0370y0.smoothScrollToPosition(recyclerView, recyclerView.mState, i5);
        }
    }
}
