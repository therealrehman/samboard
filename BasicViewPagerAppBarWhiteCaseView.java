package com.google.android.material.appbar.model.view;

import B.b;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.C0196v1;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.R;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import s6.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u000e\u0010\rJ\u0017\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/google/android/material/appbar/model/view/BasicViewPagerAppBarWhiteCaseView;", "Lcom/google/android/material/appbar/model/view/BasicViewPagerAppBarView;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Landroid/content/res/ColorStateList;", "getViewPagerBackgroundColorStateList", "(Landroid/content/Context;)Landroid/content/res/ColorStateList;", "", "getViewPagerIndicatorOffWithWhiteCaseColor", "(Landroid/content/Context;)I", "getViewPagerIndicatorOnWithWhiteCaseColor", "LT6/p;", "updateResource", "(Landroid/content/Context;)V", "material_release"}, k = 1, mv = {1, 8, 0})
public abstract class BasicViewPagerAppBarWhiteCaseView extends BasicViewPagerAppBarView {
    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public BasicViewPagerAppBarWhiteCaseView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        j.f(context, "context");
    }

    private final ColorStateList getViewPagerBackgroundColorStateList(Context context) {
        int i5 = R.color.sesl_viewpager_background;
        int i7 = R.color.sesl_viewpager_background_dark;
        int i9 = R.color.sesl_viewpager_background_for_theme;
        j.f(context, "context");
        if (c.L(context)) {
            if (!c.O(context)) {
                i5 = i7;
            }
            i9 = i5;
        } else {
            c.O(context);
        }
        ColorStateList colorStateListValueOf = ColorStateList.valueOf(b.a(context, i9));
        j.e(colorStateListValueOf, "valueOf(\n            Ses…)\n            )\n        )");
        return colorStateListValueOf;
    }

    private final int getViewPagerIndicatorOffWithWhiteCaseColor(Context context) {
        int i5 = R.color.sesl_appbar_viewpager_indicator_off_with_white_case;
        int i7 = R.color.sesl_appbar_viewpager_indicator_off_with_white_case_for_theme;
        j.f(context, "context");
        if (!c.L(context)) {
            if (!c.O(context)) {
                i7 = com.samsung.android.keyscafe.R.color.sesl_appbar_viewpager_indicator_off_dark_for_theme;
            }
            i5 = i7;
        } else if (!c.O(context)) {
            i5 = com.samsung.android.keyscafe.R.color.sesl_appbar_viewpager_indicator_off_dark;
        }
        return b.a(context, i5);
    }

    private final int getViewPagerIndicatorOnWithWhiteCaseColor(Context context) {
        int i5 = R.color.sesl_appbar_viewpager_indicator_on_with_white_case;
        int i7 = R.color.sesl_appbar_viewpager_indicator_on_with_white_case_for_theme;
        j.f(context, "context");
        if (c.L(context)) {
            c.O(context);
        } else {
            c.O(context);
            i5 = i7;
        }
        return b.a(context, i5);
    }

    @Override // com.google.android.material.appbar.model.view.ViewPagerAppBarView, com.google.android.material.appbar.model.view.AppBarView
    public void updateResource(Context context) {
        Drawable drawableMutate;
        Drawable drawableMutate2;
        j.f(context, "context");
        ViewPager2 viewpager = getViewpager();
        if (viewpager != null) {
            viewpager.setBackgroundTintList(getViewPagerBackgroundColorStateList(context));
        }
        C0196v1 indicator = getIndicator();
        if (indicator != null) {
            Drawable drawable = context.getDrawable(com.samsung.android.keyscafe.R.drawable.sesl_viewpager_indicator_on_off);
            Drawable drawable2 = null;
            if (drawable == null || (drawableMutate = drawable.mutate()) == null) {
                drawableMutate = null;
            } else {
                drawableMutate.setTint(getViewPagerIndicatorOffWithWhiteCaseColor(context));
            }
            indicator.setDefaultCircle(drawableMutate);
            Drawable drawable3 = context.getDrawable(com.samsung.android.keyscafe.R.drawable.sesl_viewpager_indicator_on_off);
            if (drawable3 != null && (drawableMutate2 = drawable3.mutate()) != null) {
                drawableMutate2.setTint(getViewPagerIndicatorOnWithWhiteCaseColor(context));
                drawable2 = drawableMutate2;
            }
            indicator.setSelectCircle(drawable2);
        }
    }

    public /* synthetic */ BasicViewPagerAppBarWhiteCaseView(Context context, AttributeSet attributeSet, int i5, e eVar) {
        this(context, (i5 & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BasicViewPagerAppBarWhiteCaseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j.f(context, "context");
    }
}
