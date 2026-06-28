package com.google.android.material.appbar.model.view;

import B5.c;
import C.p;
import U6.o;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import androidx.appcompat.widget.C0190t1;
import androidx.appcompat.widget.C0193u1;
import androidx.appcompat.widget.C0196v1;
import androidx.recyclerview.widget.AbstractC0341j0;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.V0;
import androidx.viewpager2.widget.ViewPager2;
import com.samsung.android.keyscafe.R;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000Y\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0004*\u0001-\b'\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u000f\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\n¢\u0006\u0004\b\u0012\u0010\u0010J\u000f\u0010\u0013\u001a\u00020\fH\u0004¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\n¢\u0006\u0004\b\u0016\u0010\u0010J\u001d\u0010\u0019\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u0019\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH&¢\u0006\u0004\b\u0019\u0010\u0010R\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u001b8\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u001e\u0010\u001dR\u0016\u0010\u001f\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u001c\u0010#\u001a\n \"*\u0004\u0018\u00010!0!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u001c\u0010%\u001a\n \"*\u0004\u0018\u00010!0!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010$R\u0016\u0010'\u001a\u00020&8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020&8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b)\u0010(R\u0016\u0010+\u001a\u00020*8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b+\u0010,R\u0014\u0010.\u001a\u00020-8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010/¨\u00060"}, d2 = {"Lcom/google/android/material/appbar/model/view/BasicViewPagerAppBarView;", "Lcom/google/android/material/appbar/model/view/ViewPagerAppBarView;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Landroidx/viewpager2/widget/ViewPager2;", "viewPager", "", "index", "LT6/p;", "moveNextAndRemove", "(Landroidx/viewpager2/widget/ViewPager2;I)V", "internalRemoveItem", "(I)V", "count", "initIndicator", "addIndicator", "()V", "position", "removeIndicator", "", "animate", "removeItem", "(IZ)V", "", "deleteScaleDuration", "J", "deleteAlphaDuration", "isDeleteAnimatorRunning", "Z", "Landroid/animation/PropertyValuesHolder;", "kotlin.jvm.PlatformType", "deleteScaleX", "Landroid/animation/PropertyValuesHolder;", "deleteScaleY", "Landroid/animation/ValueAnimator;", "deleteScaleAnimator", "Landroid/animation/ValueAnimator;", "deleteAlphaAnimator", "Landroid/animation/AnimatorSet;", "deleteAnimator", "Landroid/animation/AnimatorSet;", "com/google/android/material/appbar/model/view/BasicViewPagerAppBarView$pageChangeCallback$1", "pageChangeCallback", "Lcom/google/android/material/appbar/model/view/BasicViewPagerAppBarView$pageChangeCallback$1;", "material_release"}, k = 1, mv = {1, 8, 0})
public abstract class BasicViewPagerAppBarView extends ViewPagerAppBarView {
    private final ValueAnimator deleteAlphaAnimator;
    private final long deleteAlphaDuration;
    private AnimatorSet deleteAnimator;
    private ValueAnimator deleteScaleAnimator;
    private final long deleteScaleDuration;
    private final PropertyValuesHolder deleteScaleX;
    private final PropertyValuesHolder deleteScaleY;
    private boolean isDeleteAnimatorRunning;
    private final BasicViewPagerAppBarView$pageChangeCallback$1 pageChangeCallback;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public BasicViewPagerAppBarView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        j.f(context, "context");
    }

    private final void internalRemoveItem(int index) {
        removeItem(index);
        removeIndicator(index);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveNextAndRemove(ViewPager2 viewPager, int index) {
        AbstractC0341j0 adapter = viewPager.getAdapter();
        if (adapter == null || index < 0 || index >= adapter.getItemCount()) {
            return;
        }
        if (index != viewPager.getCurrentItem()) {
            removeItem(index);
            return;
        }
        int itemCount = adapter.getItemCount();
        int i5 = index == itemCount + (-1) ? index - 1 : index < itemCount ? index + 1 : index;
        this.isDeleteAnimatorRunning = true;
        viewPager.c(i5, true);
        viewPager.postDelayed(new p(index, 1, this), 250L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void moveNextAndRemove$lambda$11$lambda$10(BasicViewPagerAppBarView this$0, int i5) {
        j.f(this$0, "this$0");
        this$0.isDeleteAnimatorRunning = false;
        this$0.removeItem(i5);
    }

    public final void addIndicator() {
        C0196v1 indicator = getIndicator();
        if (indicator != null) {
            Context context = indicator.getContext();
            j.e(context, "context");
            C0190t1 c0190t1 = new C0190t1(context);
            c0190t1.f6856e = indicator.f6865g;
            c0190t1.a(c0190t1.f6858g);
            c0190t1.f6857f = indicator.h;
            c0190t1.a(c0190t1.f6858g);
            c0190t1.setOnClickListener(new c(14, indicator));
            indicator.f6863e.add(c0190t1);
            c0190t1.setAccessibilityDelegate(new C0193u1(indicator, c0190t1));
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            int dimensionPixelSize = indicator.getContext().getResources().getDimensionPixelSize(R.dimen.sesl_viewpager_indicator_horizontal_padding);
            layoutParams.setMargins(dimensionPixelSize, 0, dimensionPixelSize, 0);
            indicator.addView(c0190t1, layoutParams);
            if (indicator.f6866i == -1) {
                indicator.setSelectedPosition(0);
            }
        }
    }

    public final void initIndicator(int count) {
        if (count > 1) {
            for (int i5 = 0; i5 < count; i5++) {
                addIndicator();
            }
        }
        ViewPager2 viewpager = getViewpager();
        if (viewpager != null) {
            ((ArrayList) viewpager.f9613g.f9844b).add(this.pageChangeCallback);
        }
    }

    public final void removeIndicator(int position) {
        AbstractC0341j0 adapter;
        C0196v1 indicator = getIndicator();
        if (indicator != null) {
            indicator.b(position);
            ViewPager2 viewpager = getViewpager();
            if (viewpager == null || (adapter = viewpager.getAdapter()) == null || adapter.getItemCount() != 1) {
                return;
            }
            indicator.b(position);
        }
    }

    public abstract void removeItem(int index);

    public final void removeItem(final int index, boolean animate) {
        AbstractC0341j0 adapter;
        V0 v0FindViewHolderForAdapterPosition;
        View view;
        if (!animate) {
            internalRemoveItem(index);
            return;
        }
        final ViewPager2 viewpager = getViewpager();
        if (viewpager == null || (adapter = viewpager.getAdapter()) == null || index < 0 || index >= adapter.getItemCount() || viewpager.getChildCount() < 0) {
            return;
        }
        View childAt = viewpager.getChildAt(0);
        if (childAt == null) {
            throw new IndexOutOfBoundsException("Index: 0, Size: " + viewpager.getChildCount());
        }
        RecyclerView recyclerView = childAt instanceof RecyclerView ? (RecyclerView) childAt : null;
        if (recyclerView == null || (v0FindViewHolderForAdapterPosition = recyclerView.findViewHolderForAdapterPosition(index)) == null || (view = v0FindViewHolderForAdapterPosition.itemView) == null) {
            internalRemoveItem(index);
            return;
        }
        if (this.deleteAnimator == null) {
            if (this.deleteScaleAnimator == null) {
                ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, this.deleteScaleX, this.deleteScaleY);
                objectAnimatorOfPropertyValuesHolder.setDuration(this.deleteScaleDuration);
                objectAnimatorOfPropertyValuesHolder.setInterpolator(AnimationUtils.loadInterpolator(getContext(), R.interpolator.sesl_interpolator_22_25_0_1));
                this.deleteScaleAnimator = objectAnimatorOfPropertyValuesHolder;
            }
            AnimatorSet animatorSet = new AnimatorSet();
            ValueAnimator valueAnimator = this.deleteScaleAnimator;
            if (valueAnimator == null) {
                j.n("deleteScaleAnimator");
                throw null;
            }
            animatorSet.playTogether(o.X(valueAnimator, this.deleteAlphaAnimator));
            this.deleteAnimator = animatorSet;
        }
        ValueAnimator valueAnimator2 = this.deleteAlphaAnimator;
        valueAnimator2.removeAllListeners();
        valueAnimator2.addListener(new Animator.AnimatorListener() { // from class: com.google.android.material.appbar.model.view.BasicViewPagerAppBarView$removeItem$1$1$3$1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
                j.f(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                j.f(animation, "animation");
                this.this$0.moveNextAndRemove(viewpager, index);
                this.this$0.removeIndicator(index);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animation) {
                j.f(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                j.f(animation, "animation");
            }
        });
        AnimatorSet animatorSet2 = this.deleteAnimator;
        if (animatorSet2 == null) {
            j.n("deleteAnimator");
            throw null;
        }
        animatorSet2.setTarget(view);
        animatorSet2.start();
    }

    public /* synthetic */ BasicViewPagerAppBarView(Context context, AttributeSet attributeSet, int i5, e eVar) {
        this(context, (i5 & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r6v2, types: [com.google.android.material.appbar.model.view.BasicViewPagerAppBarView$pageChangeCallback$1] */
    public BasicViewPagerAppBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j.f(context, "context");
        this.deleteScaleDuration = 350L;
        this.deleteAlphaDuration = 150L;
        this.deleteScaleX = PropertyValuesHolder.ofFloat((Property<?, Float>) View.SCALE_X, 1.0f, 0.9f);
        this.deleteScaleY = PropertyValuesHolder.ofFloat((Property<?, Float>) View.SCALE_Y, 1.0f, 0.9f);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat((Object) null, (Property<Object, Float>) View.ALPHA, 0.0f);
        objectAnimatorOfFloat.setDuration(150L);
        objectAnimatorOfFloat.setInterpolator(AnimationUtils.loadInterpolator(context, R.interpolator.sesl_interpolator_0_0_1_1));
        this.deleteAlphaAnimator = objectAnimatorOfFloat;
        this.pageChangeCallback = new c1.j() { // from class: com.google.android.material.appbar.model.view.BasicViewPagerAppBarView$pageChangeCallback$1
            @Override // c1.j
            public void onPageSelected(int position) {
                C0196v1 indicator;
                if (this.this$0.isDeleteAnimatorRunning || (indicator = this.this$0.getIndicator()) == null) {
                    return;
                }
                indicator.setSelectedPosition(position);
            }
        };
    }
}
