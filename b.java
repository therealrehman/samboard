package com.google.android.material.appbar;

import android.animation.ValueAnimator;
import android.graphics.Matrix;
import android.graphics.Rect;
import com.google.android.material.internal.ExpandCollapseAnimationHelper;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.samsung.android.keyscafe.honeytea.setting.view.CheckBoxOutlineView;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class b implements ValueAnimator.AnimatorUpdateListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f10021e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f10022f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f10023g;

    public /* synthetic */ b(int i5, Object obj, Object obj2) {
        this.f10021e = i5;
        this.f10022f = obj;
        this.f10023g = obj2;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.f10021e) {
            case 0:
                ((AppBarLayout) this.f10022f).lambda$initializeLiftOnScrollWithElevation$1((MaterialShapeDrawable) this.f10023g, valueAnimator);
                break;
            case 1:
                ((ExpandCollapseAnimationHelper) this.f10022f).lambda$getExpandCollapseAnimator$0((Rect) this.f10023g, valueAnimator);
                break;
            default:
                CheckBoxOutlineView.onAttachedToWindow$lambda$3$lambda$2((Matrix) this.f10022f, (CheckBoxOutlineView) this.f10023g, valueAnimator);
                break;
        }
    }
}
