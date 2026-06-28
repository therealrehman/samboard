package androidx.recyclerview.widget;

import android.util.Log;
import android.view.View;

/* JADX INFO: renamed from: androidx.recyclerview.widget.c0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0327c0 implements n1, InterfaceC0355q0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f9119a;

    public /* synthetic */ C0327c0(RecyclerView recyclerView) {
        this.f9119a = recyclerView;
    }

    public void a(C0322a c0322a) {
        int i5 = c0322a.f9041a;
        RecyclerView recyclerView = this.f9119a;
        if (i5 == 1) {
            recyclerView.mLayout.onItemsAdded(recyclerView, c0322a.f9042b, c0322a.f9044d);
            return;
        }
        if (i5 == 2) {
            recyclerView.mLayout.onItemsRemoved(recyclerView, c0322a.f9042b, c0322a.f9044d);
        } else if (i5 == 4) {
            recyclerView.mLayout.onItemsUpdated(recyclerView, c0322a.f9042b, c0322a.f9044d, c0322a.f9043c);
        } else {
            if (i5 != 8) {
                return;
            }
            recyclerView.mLayout.onItemsMoved(recyclerView, c0322a.f9042b, c0322a.f9044d, 1);
        }
    }

    public V0 b(int i5) {
        RecyclerView recyclerView = this.f9119a;
        V0 v0FindViewHolderForPosition = recyclerView.findViewHolderForPosition(i5, true);
        if (v0FindViewHolderForPosition == null) {
            return null;
        }
        if (!recyclerView.mChildHelper.k(v0FindViewHolderForPosition.itemView)) {
            return v0FindViewHolderForPosition;
        }
        if (RecyclerView.sVerboseLoggingEnabled) {
            Log.d("SeslRecyclerView", "assuming view holder cannot be find because it is hidden");
        }
        return null;
    }

    public void c(int i5) {
        RecyclerView recyclerView = this.f9119a;
        View childAt = recyclerView.getChildAt(i5);
        if (childAt != null) {
            recyclerView.dispatchChildDetached(childAt);
            childAt.clearAnimation();
        }
        recyclerView.removeViewAt(i5);
    }
}
