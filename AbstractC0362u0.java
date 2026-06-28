package androidx.recyclerview.widget;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

/* JADX INFO: renamed from: androidx.recyclerview.widget.u0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0362u0 {
    @Deprecated
    public void getItemOffsets(Rect rect, int i5, RecyclerView recyclerView) {
        rect.set(0, 0, 0, 0);
    }

    @Deprecated
    public void onDraw(Canvas canvas, RecyclerView recyclerView) {
    }

    @Deprecated
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView) {
    }

    public void seslOnDispatchDraw(Canvas canvas, RecyclerView recyclerView, R0 r02) {
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, R0 r02) {
        getItemOffsets(rect, ((C0372z0) view.getLayoutParams()).getViewLayoutPosition(), recyclerView);
    }

    public void onDraw(Canvas canvas, RecyclerView recyclerView, R0 r02) {
        onDraw(canvas, recyclerView);
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, R0 r02) {
        onDrawOver(canvas, recyclerView);
    }
}
