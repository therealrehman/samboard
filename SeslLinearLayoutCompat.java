package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import e.AbstractC0478a;
import java.util.WeakHashMap;
import k.C0633b;

/* JADX INFO: loaded from: classes.dex */
public class SeslLinearLayoutCompat extends C0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0180q f6568e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final f.d f6569f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final C0633b f6570g;

    public SeslLinearLayoutCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        int[] iArr = AbstractC0478a.f10577x;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, 0, 0);
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        androidx.core.view.T.d(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, 0, 0);
        int i5 = typedArrayObtainStyledAttributes.getInt(1, 0);
        typedArrayObtainStyledAttributes.recycle();
        C0633b c0633b = new C0633b(context);
        this.f6570g = c0633b;
        c0633b.d(i5);
        C0180q c0180q = new C0180q();
        c0180q.f6836e = null;
        this.f6568e = c0180q;
        this.f6569f = new f.d(context);
    }

    public final View d(View view, int i5, int i7) {
        View viewD = null;
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i9 = 0; i9 < viewGroup.getChildCount(); i9++) {
                View childAt = viewGroup.getChildAt(i9);
                if (e(childAt, i5, i7) && (viewD = d(childAt, i5, i7)) != null) {
                    break;
                }
            }
        }
        return (viewD == null && view.isClickable() && view.getVisibility() == 0 && view.isEnabled()) ? view : viewD;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        C0633b c0633b = this.f6570g;
        canvas.getClipBounds(c0633b.f11735k);
        c0633b.b(canvas);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 66) {
            int action = keyEvent.getAction();
            f.d dVar = this.f6569f;
            if (action == 0) {
                View focusedChild = getFocusedChild();
                if (focusedChild != null) {
                    dVar.a(focusedChild);
                }
            } else {
                dVar.b();
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x006e  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean dispatchTouchEvent(android.view.MotionEvent r13) {
        /*
            r12 = this;
            int r0 = r13.getAction()
            r1 = 0
            r2 = 0
            f.d r3 = r12.f6569f
            androidx.appcompat.widget.q r4 = r12.f6568e
            if (r0 == 0) goto L4c
            r5 = 1
            if (r0 == r5) goto L3a
            r5 = 3
            if (r0 == r5) goto L1c
            r5 = 211(0xd3, float:2.96E-43)
            if (r0 == r5) goto L4c
            r5 = 212(0xd4, float:2.97E-43)
            if (r0 == r5) goto L3a
            goto Lc2
        L1c:
            java.lang.Object r0 = r4.f6836e
            android.graphics.drawable.Drawable r0 = (android.graphics.drawable.Drawable) r0
            if (r0 == 0) goto L35
            boolean r5 = r0 instanceof androidx.appcompat.graphics.drawable.SeslRecoilDrawable
            if (r5 == 0) goto L2e
            androidx.appcompat.graphics.drawable.SeslRecoilDrawable r0 = (androidx.appcompat.graphics.drawable.SeslRecoilDrawable) r0
            int[] r2 = new int[r2]
            r0.setState(r2)
            goto L33
        L2e:
            int[] r2 = new int[r2]
            r0.setState(r2)
        L33:
            r4.f6836e = r1
        L35:
            r3.b()
            goto Lc2
        L3a:
            java.lang.Object r0 = r4.f6836e
            android.graphics.drawable.Drawable r0 = (android.graphics.drawable.Drawable) r0
            if (r0 == 0) goto L47
            int[] r2 = new int[r2]
            r0.setState(r2)
            r4.f6836e = r1
        L47:
            r3.b()
            goto Lc2
        L4c:
            r0 = r2
        L4d:
            int r5 = r12.getChildCount()
            if (r0 >= r5) goto L6b
            android.view.View r5 = r12.getChildAt(r0)
            float r6 = r13.getX()
            int r6 = (int) r6
            float r7 = r13.getY()
            int r7 = (int) r7
            boolean r6 = r12.e(r5, r6, r7)
            if (r6 == 0) goto L68
            goto L6c
        L68:
            int r0 = r0 + 1
            goto L4d
        L6b:
            r5 = r1
        L6c:
            if (r5 != 0) goto L70
        L6e:
            r0 = r1
            goto L9e
        L70:
            float r0 = r13.getX()
            int r0 = (int) r0
            float r6 = r13.getY()
            int r6 = (int) r6
            android.view.View r0 = r12.d(r5, r0, r6)
            if (r0 == 0) goto L9e
            if (r0 == r5) goto L9e
            int r6 = r5.getWidth()
            int r5 = r5.getHeight()
            int r5 = r5 * r6
            int r6 = r0.getWidth()
            int r7 = r0.getHeight()
            int r7 = r7 * r6
            double r6 = (double) r7
            double r8 = (double) r5
            r10 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            double r8 = r8 * r10
            int r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r5 >= 0) goto L9e
            goto L6e
        L9e:
            if (r0 == 0) goto Lc2
            java.lang.Object r5 = r4.f6836e
            android.graphics.drawable.Drawable r5 = (android.graphics.drawable.Drawable) r5
            if (r5 == 0) goto Lad
            int[] r2 = new int[r2]
            r5.setState(r2)
            r4.f6836e = r1
        Lad:
            android.graphics.drawable.Drawable r1 = r0.getBackground()
            r4.f6836e = r1
            if (r1 == 0) goto Lbf
            r2 = 16843623(0x1010367, float:2.3696E-38)
            int[] r2 = new int[]{r2}
            r1.setState(r2)
        Lbf:
            r3.a(r0)
        Lc2:
            boolean r12 = super.dispatchTouchEvent(r13)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.SeslLinearLayoutCompat.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    public final boolean e(View view, int i5, int i7) {
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        view.getGlobalVisibleRect(rect2);
        getGlobalVisibleRect(rect);
        return rect2.contains(rect.width() + ((i5 + rect.left) - getWidth()), rect.height() + ((i7 + rect.top) - getHeight()));
    }

    public C0633b getRoundedCorner() {
        return this.f6570g;
    }
}
