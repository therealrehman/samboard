package com.google.android.material.progressindicator;

import android.content.Context;
import android.util.AttributeSet;
import com.google.android.material.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes.dex */
public class CircularProgressIndicator extends BaseProgressIndicator<CircularProgressIndicatorSpec> {
    public static final int DEF_STYLE_RES = R.style.Widget_MaterialComponents_CircularProgressIndicator;
    public static final int INDICATOR_DIRECTION_CLOCKWISE = 0;
    public static final int INDICATOR_DIRECTION_COUNTERCLOCKWISE = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface IndicatorDirection {
    }

    public CircularProgressIndicator(Context context) {
        this(context, null);
    }

    private void initializeDrawables() {
        setIndeterminateDrawable(IndeterminateDrawable.createCircularDrawable(getContext(), (CircularProgressIndicatorSpec) this.spec));
        setProgressDrawable(DeterminateDrawable.createCircularDrawable(getContext(), (CircularProgressIndicatorSpec) this.spec));
    }

    public int getIndicatorDirection() {
        return ((CircularProgressIndicatorSpec) this.spec).indicatorDirection;
    }

    public int getIndicatorInset() {
        return ((CircularProgressIndicatorSpec) this.spec).indicatorInset;
    }

    public int getIndicatorSize() {
        return ((CircularProgressIndicatorSpec) this.spec).indicatorSize;
    }

    public void setIndicatorDirection(int i5) {
        ((CircularProgressIndicatorSpec) this.spec).indicatorDirection = i5;
        invalidate();
    }

    public void setIndicatorInset(int i5) {
        S s8 = this.spec;
        if (((CircularProgressIndicatorSpec) s8).indicatorInset != i5) {
            ((CircularProgressIndicatorSpec) s8).indicatorInset = i5;
            invalidate();
        }
    }

    public void setIndicatorSize(int i5) {
        int iMax = Math.max(i5, getTrackThickness() * 2);
        S s8 = this.spec;
        if (((CircularProgressIndicatorSpec) s8).indicatorSize != iMax) {
            ((CircularProgressIndicatorSpec) s8).indicatorSize = iMax;
            ((CircularProgressIndicatorSpec) s8).validateSpec();
            invalidate();
        }
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public void setTrackThickness(int i5) {
        super.setTrackThickness(i5);
        ((CircularProgressIndicatorSpec) this.spec).validateSpec();
    }

    public CircularProgressIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.circularProgressIndicatorStyle);
    }

    @Override // com.google.android.material.progressindicator.BaseProgressIndicator
    public CircularProgressIndicatorSpec createSpec(Context context, AttributeSet attributeSet) {
        return new CircularProgressIndicatorSpec(context, attributeSet);
    }

    public CircularProgressIndicator(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5, DEF_STYLE_RES);
        initializeDrawables();
    }
}
