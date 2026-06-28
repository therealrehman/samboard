package androidx.recyclerview.widget;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;

/* JADX INFO: renamed from: androidx.recyclerview.widget.a0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0323a0 extends U {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9045a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f9046b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0323a0(Context context, int i5, Object obj) {
        super(context);
        this.f9045a = i5;
        this.f9046b = obj;
    }

    @Override // androidx.recyclerview.widget.U
    public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
        switch (this.f9045a) {
            case 0:
                return 100.0f / displayMetrics.densityDpi;
            case 1:
                return 100.0f / displayMetrics.densityDpi;
            default:
                return super.calculateSpeedPerPixel(displayMetrics);
        }
    }

    @Override // androidx.recyclerview.widget.U
    public int calculateTimeForScrolling(int i5) {
        switch (this.f9045a) {
            case 0:
                return Math.min(100, super.calculateTimeForScrolling(i5));
            default:
                return super.calculateTimeForScrolling(i5);
        }
    }

    @Override // androidx.recyclerview.widget.U, androidx.recyclerview.widget.Q0
    public final void onTargetFound(View view, R0 r02, O0 o02) {
        switch (this.f9045a) {
            case 0:
                C0325b0 c0325b0 = (C0325b0) this.f9046b;
                int[] iArrCalculateDistanceToFinalSnap = c0325b0.calculateDistanceToFinalSnap(c0325b0.mRecyclerView.getLayoutManager(), view);
                int i5 = iArrCalculateDistanceToFinalSnap[0];
                int i7 = iArrCalculateDistanceToFinalSnap[1];
                int iCalculateTimeForDeceleration = calculateTimeForDeceleration(Math.max(Math.abs(i5), Math.abs(i7)));
                if (iCalculateTimeForDeceleration > 0) {
                    o02.b(i5, i7, iCalculateTimeForDeceleration, this.mDecelerateInterpolator);
                }
                break;
            case 1:
                e1 e1Var = (e1) this.f9046b;
                RecyclerView recyclerView = e1Var.mRecyclerView;
                if (recyclerView != null) {
                    int[] iArrCalculateDistanceToFinalSnap2 = e1Var.calculateDistanceToFinalSnap(recyclerView.getLayoutManager(), view);
                    int i9 = iArrCalculateDistanceToFinalSnap2[0];
                    int i10 = iArrCalculateDistanceToFinalSnap2[1];
                    int iCalculateTimeForDeceleration2 = calculateTimeForDeceleration(Math.max(Math.abs(i9), Math.abs(i10)));
                    if (iCalculateTimeForDeceleration2 > 0) {
                        o02.b(i9, i10, iCalculateTimeForDeceleration2, this.mDecelerateInterpolator);
                    }
                    break;
                }
                break;
            default:
                int iCalculateDxToMakeVisible = calculateDxToMakeVisible(view, getHorizontalSnapPreference());
                int iCalculateDyToMakeVisible = calculateDyToMakeVisible(view, getVerticalSnapPreference());
                int iSqrt = (int) Math.sqrt((iCalculateDyToMakeVisible * iCalculateDyToMakeVisible) + (iCalculateDxToMakeVisible * iCalculateDxToMakeVisible));
                if (calculateTimeForDeceleration(iSqrt) > 0) {
                    int i11 = (int) (((((double) iSqrt) * 2.0E-4d) + 0.44999998807907104d) * 1000.0d);
                    if (i11 > 800) {
                        i11 = 800;
                    }
                    o02.b(-iCalculateDxToMakeVisible, -iCalculateDyToMakeVisible, i11, ((LinearLayoutManager) this.f9046b).mPathInterpolator);
                }
                break;
        }
    }
}
