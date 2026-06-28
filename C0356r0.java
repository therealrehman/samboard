package androidx.recyclerview.widget;

import android.view.View;

/* JADX INFO: renamed from: androidx.recyclerview.widget.r0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0356r0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f9252a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f9253b;

    public final void a(V0 v02) {
        View view = v02.itemView;
        this.f9252a = view.getLeft();
        this.f9253b = view.getTop();
        view.getRight();
        view.getBottom();
    }
}
