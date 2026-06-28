package androidx.recyclerview.widget;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.C0210b;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class W0 extends C0210b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final X0 f9032a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final WeakHashMap f9033b = new WeakHashMap();

    public W0(X0 x02) {
        this.f9032a = x02;
    }

    @Override // androidx.core.view.C0210b
    public final boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        C0210b c0210b = (C0210b) this.f9033b.get(view);
        return c0210b != null ? c0210b.dispatchPopulateAccessibilityEvent(view, accessibilityEvent) : super.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    @Override // androidx.core.view.C0210b
    public final L.n getAccessibilityNodeProvider(View view) {
        C0210b c0210b = (C0210b) this.f9033b.get(view);
        return c0210b != null ? c0210b.getAccessibilityNodeProvider(view) : super.getAccessibilityNodeProvider(view);
    }

    @Override // androidx.core.view.C0210b
    public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        C0210b c0210b = (C0210b) this.f9033b.get(view);
        if (c0210b != null) {
            c0210b.onInitializeAccessibilityEvent(view, accessibilityEvent);
        } else {
            super.onInitializeAccessibilityEvent(view, accessibilityEvent);
        }
    }

    @Override // androidx.core.view.C0210b
    public final void onInitializeAccessibilityNodeInfo(View view, L.l lVar) {
        X0 x02 = this.f9032a;
        if (x02.shouldIgnore() || x02.mRecyclerView.getLayoutManager() == null) {
            super.onInitializeAccessibilityNodeInfo(view, lVar);
            return;
        }
        x02.mRecyclerView.getLayoutManager().onInitializeAccessibilityNodeInfoForItem(view, lVar);
        C0210b c0210b = (C0210b) this.f9033b.get(view);
        if (c0210b != null) {
            c0210b.onInitializeAccessibilityNodeInfo(view, lVar);
        } else {
            super.onInitializeAccessibilityNodeInfo(view, lVar);
        }
    }

    @Override // androidx.core.view.C0210b
    public final void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        C0210b c0210b = (C0210b) this.f9033b.get(view);
        if (c0210b != null) {
            c0210b.onPopulateAccessibilityEvent(view, accessibilityEvent);
        } else {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
        }
    }

    @Override // androidx.core.view.C0210b
    public final boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        C0210b c0210b = (C0210b) this.f9033b.get(viewGroup);
        return c0210b != null ? c0210b.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent) : super.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    @Override // androidx.core.view.C0210b
    public final boolean performAccessibilityAction(View view, int i5, Bundle bundle) {
        X0 x02 = this.f9032a;
        if (x02.shouldIgnore() || x02.mRecyclerView.getLayoutManager() == null) {
            return super.performAccessibilityAction(view, i5, bundle);
        }
        C0210b c0210b = (C0210b) this.f9033b.get(view);
        if (c0210b != null) {
            if (c0210b.performAccessibilityAction(view, i5, bundle)) {
                return true;
            }
        } else if (super.performAccessibilityAction(view, i5, bundle)) {
            return true;
        }
        return x02.mRecyclerView.getLayoutManager().performAccessibilityActionForItem(view, i5, bundle);
    }

    @Override // androidx.core.view.C0210b
    public final void sendAccessibilityEvent(View view, int i5) {
        C0210b c0210b = (C0210b) this.f9033b.get(view);
        if (c0210b != null) {
            c0210b.sendAccessibilityEvent(view, i5);
        } else {
            super.sendAccessibilityEvent(view, i5);
        }
    }

    @Override // androidx.core.view.C0210b
    public final void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        C0210b c0210b = (C0210b) this.f9033b.get(view);
        if (c0210b != null) {
            c0210b.sendAccessibilityEventUnchecked(view, accessibilityEvent);
        } else {
            super.sendAccessibilityEventUnchecked(view, accessibilityEvent);
        }
    }
}
