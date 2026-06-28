package androidx.appcompat.widget;

import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.appcompat.widget.SearchView;

/* JADX INFO: renamed from: androidx.appcompat.widget.h1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0155h1 extends TouchDelegate {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f6713a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Rect f6714b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Rect f6715c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Rect f6716d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f6717e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f6718f;

    public C0155h1(Rect rect, Rect rect2, SearchView.SearchAutoComplete searchAutoComplete) {
        super(rect, searchAutoComplete);
        int scaledTouchSlop = ViewConfiguration.get(searchAutoComplete.getContext()).getScaledTouchSlop();
        this.f6717e = scaledTouchSlop;
        Rect rect3 = new Rect();
        this.f6714b = rect3;
        Rect rect4 = new Rect();
        this.f6716d = rect4;
        Rect rect5 = new Rect();
        this.f6715c = rect5;
        rect3.set(rect);
        rect4.set(rect);
        int i5 = -scaledTouchSlop;
        rect4.inset(i5, i5);
        rect5.set(rect2);
        this.f6713a = searchAutoComplete;
    }

    @Override // android.view.TouchDelegate
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        boolean z9;
        boolean z10;
        int x9 = (int) motionEvent.getX();
        int y4 = (int) motionEvent.getY();
        int action = motionEvent.getAction();
        boolean z11 = true;
        if (action != 0) {
            if (action == 1 || action == 2) {
                z10 = this.f6718f;
                if (z10 && !this.f6716d.contains(x9, y4)) {
                    z11 = z10;
                    z9 = false;
                }
            } else {
                if (action == 3) {
                    z10 = this.f6718f;
                    this.f6718f = false;
                }
                z9 = true;
                z11 = false;
            }
            z11 = z10;
            z9 = true;
        } else if (this.f6714b.contains(x9, y4)) {
            this.f6718f = true;
            z9 = true;
        } else {
            z9 = true;
            z11 = false;
        }
        if (!z11) {
            return false;
        }
        Rect rect = this.f6715c;
        View view = this.f6713a;
        if (!z9 || rect.contains(x9, y4)) {
            motionEvent.setLocation(x9 - rect.left, y4 - rect.top);
        } else {
            motionEvent.setLocation(view.getWidth() / 2, view.getHeight() / 2);
        }
        return view.dispatchTouchEvent(motionEvent);
    }
}
