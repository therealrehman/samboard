package androidx.recyclerview.widget;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.C0210b;

/* JADX INFO: loaded from: classes.dex */
public class X0 extends C0210b {
    private final W0 mItemDelegate;
    final RecyclerView mRecyclerView;

    public X0(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        C0210b itemDelegate = getItemDelegate();
        if (itemDelegate == null || !(itemDelegate instanceof W0)) {
            this.mItemDelegate = new W0(this);
        } else {
            this.mItemDelegate = (W0) itemDelegate;
        }
    }

    public C0210b getItemDelegate() {
        return this.mItemDelegate;
    }

    @Override // androidx.core.view.C0210b
    public void onInitializeAccessibilityEvent(@SuppressLint({"InvalidNullabilityOverride"}) View view, @SuppressLint({"InvalidNullabilityOverride"}) AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        if (!(view instanceof RecyclerView) || shouldIgnore()) {
            return;
        }
        RecyclerView recyclerView = (RecyclerView) view;
        if (recyclerView.getLayoutManager() != null) {
            recyclerView.getLayoutManager().onInitializeAccessibilityEvent(accessibilityEvent);
        }
    }

    @Override // androidx.core.view.C0210b
    public void onInitializeAccessibilityNodeInfo(View view, L.l lVar) {
        super.onInitializeAccessibilityNodeInfo(view, lVar);
        if (shouldIgnore() || this.mRecyclerView.getLayoutManager() == null) {
            return;
        }
        this.mRecyclerView.getLayoutManager().onInitializeAccessibilityNodeInfo(lVar);
    }

    @Override // androidx.core.view.C0210b
    public boolean performAccessibilityAction(@SuppressLint({"InvalidNullabilityOverride"}) View view, int i5, @SuppressLint({"InvalidNullabilityOverride"}) Bundle bundle) {
        if (super.performAccessibilityAction(view, i5, bundle)) {
            return true;
        }
        if (shouldIgnore() || this.mRecyclerView.getLayoutManager() == null) {
            return false;
        }
        return this.mRecyclerView.getLayoutManager().performAccessibilityAction(i5, bundle);
    }

    public boolean shouldIgnore() {
        return this.mRecyclerView.hasPendingAdapterUpdates();
    }
}
