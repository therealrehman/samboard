package androidx.recyclerview.widget;

import android.graphics.PointF;
import android.view.View;

/* JADX INFO: renamed from: androidx.recyclerview.widget.b0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0325b0 extends e1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Y f9059a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Y f9060b;

    public static int a(View view, Z z9) {
        return ((z9.c(view) / 2) + z9.e(view)) - ((z9.l() / 2) + z9.k());
    }

    public static View b(AbstractC0370y0 abstractC0370y0, Z z9) {
        int childCount = abstractC0370y0.getChildCount();
        View view = null;
        if (childCount == 0) {
            return null;
        }
        int iL = (z9.l() / 2) + z9.k();
        int i5 = Integer.MAX_VALUE;
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = abstractC0370y0.getChildAt(i7);
            int iAbs = Math.abs(((z9.c(childAt) / 2) + z9.e(childAt)) - iL);
            if (iAbs < i5) {
                view = childAt;
                i5 = iAbs;
            }
        }
        return view;
    }

    public final Z c(AbstractC0370y0 abstractC0370y0) {
        Y y4 = this.f9060b;
        if (y4 == null || y4.f9036a != abstractC0370y0) {
            this.f9060b = new Y(abstractC0370y0, 0);
        }
        return this.f9060b;
    }

    @Override // androidx.recyclerview.widget.e1
    public final int[] calculateDistanceToFinalSnap(AbstractC0370y0 abstractC0370y0, View view) {
        int[] iArr = new int[2];
        if (abstractC0370y0.canScrollHorizontally()) {
            iArr[0] = a(view, c(abstractC0370y0));
        } else {
            iArr[0] = 0;
        }
        if (abstractC0370y0.canScrollVertically()) {
            iArr[1] = a(view, d(abstractC0370y0));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    @Override // androidx.recyclerview.widget.e1
    public final Q0 createScroller(AbstractC0370y0 abstractC0370y0) {
        if (abstractC0370y0 instanceof P0) {
            return new C0323a0(this.mRecyclerView.getContext(), 0, this);
        }
        return null;
    }

    public final Z d(AbstractC0370y0 abstractC0370y0) {
        Y y4 = this.f9059a;
        if (y4 == null || y4.f9036a != abstractC0370y0) {
            this.f9059a = new Y(abstractC0370y0, 1);
        }
        return this.f9059a;
    }

    @Override // androidx.recyclerview.widget.e1
    public View findSnapView(AbstractC0370y0 abstractC0370y0) {
        if (abstractC0370y0.canScrollVertically()) {
            return b(abstractC0370y0, d(abstractC0370y0));
        }
        if (abstractC0370y0.canScrollHorizontally()) {
            return b(abstractC0370y0, c(abstractC0370y0));
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.e1
    public final int findTargetSnapPosition(AbstractC0370y0 abstractC0370y0, int i5, int i7) {
        PointF pointFComputeScrollVectorForPosition;
        int itemCount = abstractC0370y0.getItemCount();
        if (itemCount == 0) {
            return -1;
        }
        View view = null;
        Z zD = abstractC0370y0.canScrollVertically() ? d(abstractC0370y0) : abstractC0370y0.canScrollHorizontally() ? c(abstractC0370y0) : null;
        if (zD == null) {
            return -1;
        }
        int childCount = abstractC0370y0.getChildCount();
        boolean z9 = false;
        int i9 = Integer.MAX_VALUE;
        int i10 = Integer.MIN_VALUE;
        View view2 = null;
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = abstractC0370y0.getChildAt(i11);
            if (childAt != null) {
                int iA = a(childAt, zD);
                if (iA <= 0 && iA > i10) {
                    view2 = childAt;
                    i10 = iA;
                }
                if (iA >= 0 && iA < i9) {
                    view = childAt;
                    i9 = iA;
                }
            }
        }
        boolean z10 = !abstractC0370y0.canScrollHorizontally() ? i7 <= 0 : i5 <= 0;
        if (z10 && view != null) {
            return abstractC0370y0.getPosition(view);
        }
        if (!z10 && view2 != null) {
            return abstractC0370y0.getPosition(view2);
        }
        if (z10) {
            view = view2;
        }
        if (view == null) {
            return -1;
        }
        int position = abstractC0370y0.getPosition(view);
        int itemCount2 = abstractC0370y0.getItemCount();
        if ((abstractC0370y0 instanceof P0) && (pointFComputeScrollVectorForPosition = ((P0) abstractC0370y0).computeScrollVectorForPosition(itemCount2 - 1)) != null && (pointFComputeScrollVectorForPosition.x < 0.0f || pointFComputeScrollVectorForPosition.y < 0.0f)) {
            z9 = true;
        }
        int i12 = position + (z9 == z10 ? -1 : 1);
        if (i12 < 0 || i12 >= itemCount) {
            return -1;
        }
        return i12;
    }
}
