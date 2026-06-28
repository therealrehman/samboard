package androidx.appcompat.widget;

import android.widget.AbsListView;

/* JADX INFO: renamed from: androidx.appcompat.widget.t0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0189t0 {
    public static boolean a(AbsListView absListView) {
        return absListView.isSelectedChildViewEnabled();
    }

    public static void b(AbsListView absListView, boolean z9) {
        absListView.setSelectedChildViewEnabled(z9);
    }
}
