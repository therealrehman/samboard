package androidx.core.view;

import android.graphics.Rect;
import android.view.TouchDelegate;
import android.view.View;

/* JADX INFO: renamed from: androidx.core.view.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0232y extends TouchDelegate {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f7270a;

    public C0232y(Rect rect, View view) {
        super(rect, view);
        this.f7270a = view;
    }
}
