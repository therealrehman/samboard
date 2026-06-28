package androidx.picker.widget;

import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/* JADX INFO: renamed from: androidx.picker.widget.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class ViewOnTouchListenerC0296p implements View.OnTouchListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f8436e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f8437f;

    public /* synthetic */ ViewOnTouchListenerC0296p(int i5, Object obj) {
        this.f8436e = i5;
        this.f8437f = obj;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        switch (this.f8436e) {
            case 0:
                if (motionEvent.getAction() != 1 && motionEvent.getAction() != 3) {
                    return false;
                }
                ((SeslDatePicker) this.f8437f).l();
                return false;
            default:
                if (!(view instanceof EditText) || motionEvent.getActionMasked() != 0) {
                    return false;
                }
                ((EditText) view).selectAll();
                ((P) this.f8437f).x();
                return true;
        }
    }
}
