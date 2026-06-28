package com.google.android.material.transition;

import A8.l;
import android.animation.Animator;
import android.view.View;
import android.view.ViewGroup;
import androidx.transition.c0;
import com.google.android.material.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public final class MaterialSharedAxis extends MaterialVisibility<VisibilityAnimatorProvider> {
    private static final int DEFAULT_THEMED_DURATION_ATTR = R.attr.motionDurationLong1;
    private static final int DEFAULT_THEMED_EASING_ATTR = R.attr.motionEasingEmphasizedInterpolator;

    /* JADX INFO: renamed from: X, reason: collision with root package name */
    public static final int f10142X = 0;

    /* JADX INFO: renamed from: Y, reason: collision with root package name */
    public static final int f10143Y = 1;

    /* JADX INFO: renamed from: Z, reason: collision with root package name */
    public static final int f10144Z = 2;
    private final int axis;
    private final boolean forward;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Axis {
    }

    public MaterialSharedAxis(int i5, boolean z9) {
        super(createPrimaryAnimatorProvider(i5, z9), createSecondaryAnimatorProvider());
        this.axis = i5;
        this.forward = z9;
    }

    private static VisibilityAnimatorProvider createPrimaryAnimatorProvider(int i5, boolean z9) {
        if (i5 == 0) {
            return new SlideDistanceProvider(z9 ? 8388613 : 8388611);
        }
        if (i5 == 1) {
            return new SlideDistanceProvider(z9 ? 80 : 48);
        }
        if (i5 == 2) {
            return new ScaleProvider(z9);
        }
        throw new IllegalArgumentException(l.o(i5, "Invalid axis: "));
    }

    private static VisibilityAnimatorProvider createSecondaryAnimatorProvider() {
        return new FadeThroughProvider();
    }

    @Override // com.google.android.material.transition.MaterialVisibility
    public /* bridge */ /* synthetic */ void addAdditionalAnimatorProvider(VisibilityAnimatorProvider visibilityAnimatorProvider) {
        super.addAdditionalAnimatorProvider(visibilityAnimatorProvider);
    }

    @Override // com.google.android.material.transition.MaterialVisibility
    public /* bridge */ /* synthetic */ void clearAdditionalAnimatorProvider() {
        super.clearAdditionalAnimatorProvider();
    }

    public int getAxis() {
        return this.axis;
    }

    @Override // com.google.android.material.transition.MaterialVisibility
    public int getDurationThemeAttrResId(boolean z9) {
        return DEFAULT_THEMED_DURATION_ATTR;
    }

    @Override // com.google.android.material.transition.MaterialVisibility
    public int getEasingThemeAttrResId(boolean z9) {
        return DEFAULT_THEMED_EASING_ATTR;
    }

    @Override // com.google.android.material.transition.MaterialVisibility
    public /* bridge */ /* synthetic */ VisibilityAnimatorProvider getPrimaryAnimatorProvider() {
        return super.getPrimaryAnimatorProvider();
    }

    @Override // com.google.android.material.transition.MaterialVisibility
    public /* bridge */ /* synthetic */ VisibilityAnimatorProvider getSecondaryAnimatorProvider() {
        return super.getSecondaryAnimatorProvider();
    }

    public boolean isForward() {
        return this.forward;
    }

    @Override // com.google.android.material.transition.MaterialVisibility, androidx.transition.l0
    public /* bridge */ /* synthetic */ Animator onAppear(ViewGroup viewGroup, View view, c0 c0Var, c0 c0Var2) {
        return super.onAppear(viewGroup, view, c0Var, c0Var2);
    }

    @Override // com.google.android.material.transition.MaterialVisibility, androidx.transition.l0
    public /* bridge */ /* synthetic */ Animator onDisappear(ViewGroup viewGroup, View view, c0 c0Var, c0 c0Var2) {
        return super.onDisappear(viewGroup, view, c0Var, c0Var2);
    }

    @Override // com.google.android.material.transition.MaterialVisibility
    public /* bridge */ /* synthetic */ boolean removeAdditionalAnimatorProvider(VisibilityAnimatorProvider visibilityAnimatorProvider) {
        return super.removeAdditionalAnimatorProvider(visibilityAnimatorProvider);
    }

    @Override // com.google.android.material.transition.MaterialVisibility
    public /* bridge */ /* synthetic */ void setSecondaryAnimatorProvider(VisibilityAnimatorProvider visibilityAnimatorProvider) {
        super.setSecondaryAnimatorProvider(visibilityAnimatorProvider);
    }
}
