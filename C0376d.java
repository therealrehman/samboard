package androidx.transition;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Property;
import android.view.View;
import android.widget.ImageView;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: androidx.transition.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0376d extends Property {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9410a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0376d(int i5, Class cls, String str) {
        super(cls, str);
        this.f9410a = i5;
    }

    @Override // android.util.Property
    public final Object get(Object obj) {
        switch (this.f9410a) {
            case 0:
                return null;
            case 1:
                return null;
            case 2:
                return null;
            case 3:
                return null;
            case 4:
                return null;
            case 5:
                return null;
            case 6:
                return null;
            case 7:
                return null;
            case 8:
                return Float.valueOf(((View) obj).getTransitionAlpha());
            default:
                WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
                return ((View) obj).getClipBounds();
        }
    }

    @Override // android.util.Property
    public final void set(Object obj, Object obj2) {
        switch (this.f9410a) {
            case 0:
                C0380h c0380h = (C0380h) obj;
                PointF pointF = (PointF) obj2;
                c0380h.getClass();
                c0380h.f9433a = Math.round(pointF.x);
                int iRound = Math.round(pointF.y);
                c0380h.f9434b = iRound;
                int i5 = c0380h.f9438f + 1;
                c0380h.f9438f = i5;
                if (i5 == c0380h.f9439g) {
                    int i7 = c0380h.f9433a;
                    int i9 = c0380h.f9435c;
                    int i10 = c0380h.f9436d;
                    C0376d c0376d = f0.f9429a;
                    c0380h.f9437e.setLeftTopRightBottom(i7, iRound, i9, i10);
                    c0380h.f9438f = 0;
                    c0380h.f9439g = 0;
                }
                break;
            case 1:
                C0380h c0380h2 = (C0380h) obj;
                PointF pointF2 = (PointF) obj2;
                c0380h2.getClass();
                c0380h2.f9435c = Math.round(pointF2.x);
                int iRound2 = Math.round(pointF2.y);
                c0380h2.f9436d = iRound2;
                int i11 = c0380h2.f9439g + 1;
                c0380h2.f9439g = i11;
                if (c0380h2.f9438f == i11) {
                    int i12 = c0380h2.f9433a;
                    int i13 = c0380h2.f9434b;
                    int i14 = c0380h2.f9435c;
                    C0376d c0376d2 = f0.f9429a;
                    c0380h2.f9437e.setLeftTopRightBottom(i12, i13, i14, iRound2);
                    c0380h2.f9438f = 0;
                    c0380h2.f9439g = 0;
                }
                break;
            case 2:
                View view = (View) obj;
                PointF pointF3 = (PointF) obj2;
                int left = view.getLeft();
                int top = view.getTop();
                int iRound3 = Math.round(pointF3.x);
                int iRound4 = Math.round(pointF3.y);
                C0376d c0376d3 = f0.f9429a;
                view.setLeftTopRightBottom(left, top, iRound3, iRound4);
                break;
            case 3:
                View view2 = (View) obj;
                PointF pointF4 = (PointF) obj2;
                int iRound5 = Math.round(pointF4.x);
                int iRound6 = Math.round(pointF4.y);
                int right = view2.getRight();
                int bottom = view2.getBottom();
                C0376d c0376d4 = f0.f9429a;
                view2.setLeftTopRightBottom(iRound5, iRound6, right, bottom);
                break;
            case 4:
                View view3 = (View) obj;
                PointF pointF5 = (PointF) obj2;
                int iRound7 = Math.round(pointF5.x);
                int iRound8 = Math.round(pointF5.y);
                int width = view3.getWidth() + iRound7;
                int height = view3.getHeight() + iRound8;
                C0376d c0376d5 = f0.f9429a;
                view3.setLeftTopRightBottom(iRound7, iRound8, width, height);
                break;
            case 5:
                ((ImageView) obj).animateTransform((Matrix) obj2);
                break;
            case 6:
                C0388p c0388p = (C0388p) obj;
                float[] fArr = (float[]) obj2;
                c0388p.getClass();
                System.arraycopy(fArr, 0, c0388p.f9482c, 0, fArr.length);
                c0388p.a();
                break;
            case 7:
                C0388p c0388p2 = (C0388p) obj;
                PointF pointF6 = (PointF) obj2;
                c0388p2.getClass();
                c0388p2.f9483d = pointF6.x;
                c0388p2.f9484e = pointF6.y;
                c0388p2.a();
                break;
            case 8:
                ((View) obj).setTransitionAlpha(((Float) obj2).floatValue());
                break;
            default:
                WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
                ((View) obj).setClipBounds((Rect) obj2);
                break;
        }
    }
}
