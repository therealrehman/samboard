package androidx.core.view;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: androidx.core.view.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0209a extends View.AccessibilityDelegate {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0210b f7202a;

    public C0209a(C0210b c0210b) {
        this.f7202a = c0210b;
    }

    @Override // android.view.View.AccessibilityDelegate
    public final boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        return this.f7202a.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    @Override // android.view.View.AccessibilityDelegate
    public final AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
        L.n accessibilityNodeProvider = this.f7202a.getAccessibilityNodeProvider(view);
        if (accessibilityNodeProvider != null) {
            return (AccessibilityNodeProvider) accessibilityNodeProvider.f1797a;
        }
        return null;
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.f7202a.onInitializeAccessibilityEvent(view, accessibilityEvent);
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
        L.l lVar = new L.l(accessibilityNodeInfo);
        WeakHashMap weakHashMap = W.f7199a;
        accessibilityNodeInfo.setScreenReaderFocusable(Boolean.valueOf(S.d(view)).booleanValue());
        accessibilityNodeInfo.setHeading(Boolean.valueOf(S.c(view)).booleanValue());
        accessibilityNodeInfo.setPaneTitle(S.b(view));
        L.g.c(accessibilityNodeInfo, U.b(view));
        this.f7202a.onInitializeAccessibilityNodeInfo(view, lVar);
        accessibilityNodeInfo.getText();
        List<L.f> actionList = C0210b.getActionList(view);
        for (int i5 = 0; i5 < actionList.size(); i5++) {
            lVar.b(actionList.get(i5));
        }
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
        this.f7202a.onPopulateAccessibilityEvent(view, accessibilityEvent);
    }

    @Override // android.view.View.AccessibilityDelegate
    public final boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
        return this.f7202a.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
    }

    @Override // android.view.View.AccessibilityDelegate
    public final boolean performAccessibilityAction(View view, int i5, Bundle bundle) {
        return this.f7202a.performAccessibilityAction(view, i5, bundle);
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void sendAccessibilityEvent(View view, int i5) {
        this.f7202a.sendAccessibilityEvent(view, i5);
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
        this.f7202a.sendAccessibilityEventUnchecked(view, accessibilityEvent);
    }
}
