package com.google.android.material.appbar.model.view;

import B.b;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import androidx.appcompat.widget.C0196v1;
import androidx.appcompat.widget.InterfaceC0187s1;
import androidx.viewpager2.widget.ViewPager2;
import c1.h;
import com.google.android.material.R;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import s6.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0017\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u000e\u0010\rJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0012\u0010\u0013R$\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR$\u0010\u001c\u001a\u0004\u0018\u00010\u001b8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R$\u0010#\u001a\u0004\u0018\u00010\"8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(¨\u0006)"}, d2 = {"Lcom/google/android/material/appbar/model/view/ViewPagerAppBarView;", "Lcom/google/android/material/appbar/model/view/AppBarView;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Landroid/content/res/ColorStateList;", "getViewPagerBackgroundColorStateList", "(Landroid/content/Context;)Landroid/content/res/ColorStateList;", "", "getViewPagerIndicatorOffColor", "(Landroid/content/Context;)I", "getViewPagerIndicatorOnColor", "LT6/p;", "inflate", "()V", "updateResource", "(Landroid/content/Context;)V", "Landroidx/viewpager2/widget/ViewPager2;", "viewpager", "Landroidx/viewpager2/widget/ViewPager2;", "getViewpager", "()Landroidx/viewpager2/widget/ViewPager2;", "setViewpager", "(Landroidx/viewpager2/widget/ViewPager2;)V", "Landroid/view/ViewGroup;", "bottomLayout", "Landroid/view/ViewGroup;", "getBottomLayout", "()Landroid/view/ViewGroup;", "setBottomLayout", "(Landroid/view/ViewGroup;)V", "Landroidx/appcompat/widget/v1;", "indicator", "Landroidx/appcompat/widget/v1;", "getIndicator", "()Landroidx/appcompat/widget/v1;", "setIndicator", "(Landroidx/appcompat/widget/v1;)V", "material_release"}, k = 1, mv = {1, 8, 0})
public class ViewPagerAppBarView extends AppBarView {
    private ViewGroup bottomLayout;
    private C0196v1 indicator;
    private ViewPager2 viewpager;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ViewPagerAppBarView(Context context) {
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

    private final int getViewPagerIndicatorOffColor(Context context) {
        j.f(context, "context");
        return b.a(context, c.L(context) ? c.O(context) ? com.samsung.android.keyscafe.R.color.sesl_appbar_viewpager_indicator_off : com.samsung.android.keyscafe.R.color.sesl_appbar_viewpager_indicator_off_dark : c.O(context) ? com.samsung.android.keyscafe.R.color.sesl_appbar_viewpager_indicator_off_for_theme : com.samsung.android.keyscafe.R.color.sesl_appbar_viewpager_indicator_off_dark_for_theme);
    }

    private final int getViewPagerIndicatorOnColor(Context context) {
        int i5;
        j.f(context, "context");
        if (c.L(context)) {
            c.O(context);
            i5 = com.samsung.android.keyscafe.R.color.sesl_appbar_viewpager_indicator_on;
        } else {
            c.O(context);
            i5 = com.samsung.android.keyscafe.R.color.sesl_appbar_viewpager_indicator_on_for_theme;
        }
        return b.a(context, i5);
    }

    public final ViewGroup getBottomLayout() {
        return this.bottomLayout;
    }

    public final C0196v1 getIndicator() {
        return this.indicator;
    }

    public final ViewPager2 getViewpager() {
        return this.viewpager;
    }

    @Override // com.google.android.material.appbar.model.view.AppBarView
    public void inflate() {
        Drawable drawableMutate;
        Drawable drawableMutate2;
        int i5;
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.sesl_app_bar_viewpager, (ViewGroup) this, false);
        ViewGroup viewGroup = viewInflate instanceof ViewGroup ? (ViewGroup) viewInflate : null;
        if (viewGroup == null) {
            return;
        }
        this.viewpager = (ViewPager2) viewGroup.findViewById(R.id.app_bar_viewpager);
        this.bottomLayout = (ViewGroup) viewGroup.findViewById(R.id.bottom_layout);
        Context context = getContext();
        j.e(context, "context");
        C0196v1 c0196v1 = new C0196v1(context, null);
        c0196v1.f6863e = new ArrayList();
        Drawable drawable = context.getDrawable(com.samsung.android.keyscafe.R.drawable.sesl_viewpager_indicator_on_off);
        if (drawable == null || (drawableMutate = drawable.mutate()) == null) {
            drawableMutate = null;
        } else {
            drawableMutate.setTint(b.a(context, c.L(context) ? c.O(context) ? com.samsung.android.keyscafe.R.color.sesl_appbar_viewpager_indicator_off : com.samsung.android.keyscafe.R.color.sesl_appbar_viewpager_indicator_off_dark : c.O(context) ? com.samsung.android.keyscafe.R.color.sesl_appbar_viewpager_indicator_off_for_theme : com.samsung.android.keyscafe.R.color.sesl_appbar_viewpager_indicator_off_dark_for_theme));
        }
        c0196v1.f6865g = drawableMutate;
        Drawable drawable2 = context.getDrawable(com.samsung.android.keyscafe.R.drawable.sesl_viewpager_indicator_on_off);
        if (drawable2 == null || (drawableMutate2 = drawable2.mutate()) == null) {
            drawableMutate2 = null;
        } else {
            if (c.L(context)) {
                c.O(context);
                i5 = com.samsung.android.keyscafe.R.color.sesl_appbar_viewpager_indicator_on;
            } else {
                c.O(context);
                i5 = com.samsung.android.keyscafe.R.color.sesl_appbar_viewpager_indicator_on_for_theme;
            }
            drawableMutate2.setTint(b.a(context, i5));
        }
        c0196v1.h = drawableMutate2;
        c0196v1.f6866i = -1;
        c0196v1.setOnItemClickListener(new InterfaceC0187s1() { // from class: com.google.android.material.appbar.model.view.ViewPagerAppBarView$inflate$1$1
            @Override // androidx.appcompat.widget.InterfaceC0187s1
            public void onItemClick(View view, int position) {
                ViewPager2 viewpager = this.this$0.getViewpager();
                if (viewpager != null) {
                    viewpager.c(position, true);
                }
            }
        });
        this.indicator = c0196v1;
        ViewPager2 viewPager2 = this.viewpager;
        if (viewPager2 != null) {
            viewPager2.f9608B = true;
            viewPager2.f9619n.setEdgeEffectEnabled(false);
            ValueAnimator duration = ValueAnimator.ofFloat(1.0f, 0.95f).setDuration(400L);
            viewPager2.f9629y = duration;
            PathInterpolator pathInterpolator = ViewPager2.f9606E;
            duration.setInterpolator(pathInterpolator);
            viewPager2.f9629y.addUpdateListener(new h(viewPager2, 0));
            ValueAnimator duration2 = ValueAnimator.ofFloat(0.95f, 1.0f).setDuration(400L);
            viewPager2.f9630z = duration2;
            duration2.setInterpolator(pathInterpolator);
            viewPager2.f9630z.addUpdateListener(new h(viewPager2, 1));
            if (viewPager2.f9619n.getClipChildren()) {
                viewPager2.f9619n.setClipChildren(false);
            }
            Drawable drawable3 = viewPager2.getContext().getDrawable(R.drawable.sesl_viewpager_background);
            viewPager2.setBackground(drawable3 != null ? drawable3.mutate() : null);
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        ViewGroup viewGroup2 = this.bottomLayout;
        if (viewGroup2 != null) {
            viewGroup2.addView(this.indicator, layoutParams);
        }
        Context context2 = getContext();
        j.e(context2, "context");
        updateResource(context2);
        addView(viewGroup);
    }

    public final void setBottomLayout(ViewGroup viewGroup) {
        this.bottomLayout = viewGroup;
    }

    public final void setIndicator(C0196v1 c0196v1) {
        this.indicator = c0196v1;
    }

    public final void setViewpager(ViewPager2 viewPager2) {
        this.viewpager = viewPager2;
    }

    @Override // com.google.android.material.appbar.model.view.AppBarView
    public void updateResource(Context context) {
        Drawable drawableMutate;
        Drawable drawableMutate2;
        j.f(context, "context");
        ViewPager2 viewPager2 = this.viewpager;
        if (viewPager2 != null) {
            viewPager2.setBackgroundTintList(getViewPagerBackgroundColorStateList(context));
        }
        C0196v1 c0196v1 = this.indicator;
        if (c0196v1 != null) {
            Drawable drawable = context.getDrawable(com.samsung.android.keyscafe.R.drawable.sesl_viewpager_indicator_on_off);
            Drawable drawable2 = null;
            if (drawable == null || (drawableMutate = drawable.mutate()) == null) {
                drawableMutate = null;
            } else {
                drawableMutate.setTint(getViewPagerIndicatorOffColor(context));
            }
            c0196v1.setDefaultCircle(drawableMutate);
            Drawable drawable3 = context.getDrawable(com.samsung.android.keyscafe.R.drawable.sesl_viewpager_indicator_on_off);
            if (drawable3 != null && (drawableMutate2 = drawable3.mutate()) != null) {
                drawableMutate2.setTint(getViewPagerIndicatorOnColor(context));
                drawable2 = drawableMutate2;
            }
            c0196v1.setSelectCircle(drawable2);
        }
    }

    public /* synthetic */ ViewPagerAppBarView(Context context, AttributeSet attributeSet, int i5, e eVar) {
        this(context, (i5 & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewPagerAppBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j.f(context, "context");
        inflate();
    }
}
