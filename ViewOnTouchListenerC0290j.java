package androidx.picker.widget;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: renamed from: androidx.picker.widget.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ViewOnTouchListenerC0290j implements View.OnTouchListener {
    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        return motionEvent.getAction() == 0;
    }
}
