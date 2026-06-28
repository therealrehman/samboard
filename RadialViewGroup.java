package com.google.android.material.timepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.l;
import androidx.constraintlayout.widget.p;
import androidx.core.view.W;
import com.google.android.material.R;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RelativeCornerSize;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
class RadialViewGroup extends ConstraintLayout {
    static final int LEVEL_1 = 1;
    static final int LEVEL_2 = 2;
    static final float LEVEL_RADIUS_RATIO = 0.66f;
    private static final String SKIP_TAG = "skip";
    private MaterialShapeDrawable background;
    private int radius;
    private final Runnable updateLayoutParametersRunnable;

    public RadialViewGroup(Context context) {
        this(context, null);
    }

    private void addConstraints(List<View> list, p pVar, int i5) {
        Iterator<View> it = list.iterator();
        float size = 0.0f;
        while (it.hasNext()) {
            int id = it.next().getId();
            int i7 = R.id.circle_center;
            l lVar = pVar.g(id).f7043d;
            lVar.f7108z = i7;
            lVar.f7047A = i5;
            lVar.f7048B = size;
            size += 360.0f / list.size();
        }
    }

    private Drawable createBackground() {
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        this.background = materialShapeDrawable;
        materialShapeDrawable.setCornerSize(new RelativeCornerSize(0.5f));
        this.background.setFillColor(ColorStateList.valueOf(-1));
        return this.background;
    }

    private static boolean shouldSkipView(View view) {
        return SKIP_TAG.equals(view.getTag());
    }

    private void updateLayoutParamsAsync() {
        Handler handler = getHandler();
        if (handler != null) {
            handler.removeCallbacks(this.updateLayoutParametersRunnable);
            handler.post(this.updateLayoutParametersRunnable);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i5, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i5, layoutParams);
        if (view.getId() == -1) {
            WeakHashMap weakHashMap = W.f7199a;
            view.setId(View.generateViewId());
        }
        updateLayoutParamsAsync();
    }

    public int getLeveledRadius(int i5) {
        int i7 = this.radius;
        return i5 == 2 ? Math.round(i7 * LEVEL_RADIUS_RATIO) : i7;
    }

    public int getRadius() {
        return this.radius;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        updateLayoutParams();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        updateLayoutParamsAsync();
    }

    @Override // android.view.View
    public void setBackgroundColor(int i5) {
        this.background.setFillColor(ColorStateList.valueOf(i5));
    }

    public void setRadius(int i5) {
        this.radius = i5;
        updateLayoutParams();
    }

    public void updateLayoutParams() {
        p pVar = new p();
        pVar.c(this);
        HashMap map = new HashMap();
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            View childAt = getChildAt(i5);
            if (childAt.getId() != R.id.circle_center && !shouldSkipView(childAt)) {
                int i7 = (Integer) childAt.getTag(R.id.material_clock_level);
                if (i7 == null) {
                    i7 = 1;
                }
                if (!map.containsKey(i7)) {
                    map.put(i7, new ArrayList());
                }
                ((List) map.get(i7)).add(childAt);
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            addConstraints((List) entry.getValue(), pVar, getLeveledRadius(((Integer) entry.getKey()).intValue()));
        }
        pVar.a(this);
    }

    public RadialViewGroup(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RadialViewGroup(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5);
        LayoutInflater.from(context).inflate(R.layout.material_radial_view_group, this);
        Drawable drawableCreateBackground = createBackground();
        WeakHashMap weakHashMap = W.f7199a;
        setBackground(drawableCreateBackground);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RadialViewGroup, i5, 0);
        this.radius = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.RadialViewGroup_materialCircleRadius, 0);
        this.updateLayoutParametersRunnable = new b(0, this);
        typedArrayObtainStyledAttributes.recycle();
    }
}
