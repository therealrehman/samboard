package androidx.core.view;

import android.view.View;

/* JADX INFO: renamed from: androidx.core.view.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public interface InterfaceC0224p {
    void onNestedPreScroll(View view, int i5, int i7, int[] iArr, int i9);

    void onNestedScroll(View view, int i5, int i7, int i9, int i10, int i11);

    void onNestedScrollAccepted(View view, View view2, int i5, int i7);

    boolean onStartNestedScroll(View view, View view2, int i5, int i7);

    void onStopNestedScroll(View view, int i5);
}
