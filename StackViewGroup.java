package com.google.android.material.appbar;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import com.samsung.android.keyscafe.R;
import java.util.Stack;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001:\u0001$B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00020\b2\b\u0010\r\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u000e\u0010\nR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00158\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u0018\u0010\u0017R\u001c\u0010\u001b\u001a\n \u001a*\u0004\u0018\u00010\u00190\u00198\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001e\u001a\n \u001a*\u0004\u0018\u00010\u001d0\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\n \u001a*\u0004\u0018\u00010\u001d0\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b \u0010\u001fR\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#¨\u0006%"}, d2 = {"Lcom/google/android/material/appbar/StackViewGroup;", "", "Landroid/widget/FrameLayout;", "rootView", "<init>", "(Landroid/widget/FrameLayout;)V", "Landroid/view/ViewGroup;", "child", "LT6/p;", "push", "(Landroid/view/ViewGroup;)V", "pop", "()Landroid/view/ViewGroup;", "view", "remove", "Landroid/widget/FrameLayout;", "getRootView", "()Landroid/widget/FrameLayout;", "Lcom/google/android/material/appbar/StackViewGroup$SceneStack;", "sceneStack", "Lcom/google/android/material/appbar/StackViewGroup$SceneStack;", "", "showDuration", "J", "hideDuration", "Landroid/view/animation/Interpolator;", "kotlin.jvm.PlatformType", "showHideInterpolator", "Landroid/view/animation/Interpolator;", "Landroid/animation/ObjectAnimator;", "showAnimator", "Landroid/animation/ObjectAnimator;", "hideAnimator", "Landroid/animation/AnimatorSet;", "showHideAnimator", "Landroid/animation/AnimatorSet;", "SceneStack", "material_release"}, k = 1, mv = {1, 8, 0})
public final class StackViewGroup {
    private final ObjectAnimator hideAnimator;
    private final long hideDuration;
    private final FrameLayout rootView;
    private final SceneStack<ViewGroup> sceneStack;
    private final ObjectAnimator showAnimator;
    private final long showDuration;
    private final AnimatorSet showHideAnimator;
    private final Interpolator showHideInterpolator;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005¢\u0006\u0002\u0010\u0004J\r\u0010\u0005\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0006J\u0015\u0010\u0007\u001a\u00028\u00002\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/google/android/material/appbar/StackViewGroup$SceneStack;", "T", "Landroid/view/View;", "Ljava/util/Stack;", "()V", "pop", "()Landroid/view/View;", "push", "item", "(Landroid/view/View;)Landroid/view/View;", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class SceneStack<T extends View> extends Stack<T> {
        public /* bridge */ boolean contains(View view) {
            return super.contains((Object) view);
        }

        public /* bridge */ int getSize() {
            return super.size();
        }

        public /* bridge */ int indexOf(View view) {
            return super.indexOf((Object) view);
        }

        public /* bridge */ int lastIndexOf(View view) {
            return super.lastIndexOf((Object) view);
        }

        @Override // java.util.Vector, java.util.AbstractList, java.util.List
        public final /* bridge */ T remove(int i5) {
            return (T) removeAt(i5);
        }

        public /* bridge */ View removeAt(int i5) {
            return remove(i5);
        }

        @Override // java.util.Vector, java.util.AbstractCollection, java.util.Collection, java.util.List
        public final /* bridge */ int size() {
            return getSize();
        }

        @Override // java.util.Vector, java.util.AbstractCollection, java.util.Collection, java.util.List
        public final /* bridge */ boolean contains(Object obj) {
            if (obj == null ? true : obj instanceof View) {
                return contains((View) obj);
            }
            return false;
        }

        @Override // java.util.Vector, java.util.AbstractList, java.util.List
        public final /* bridge */ int indexOf(Object obj) {
            if (obj == null ? true : obj instanceof View) {
                return indexOf((View) obj);
            }
            return -1;
        }

        @Override // java.util.Vector, java.util.AbstractList, java.util.List
        public final /* bridge */ int lastIndexOf(Object obj) {
            if (obj == null ? true : obj instanceof View) {
                return lastIndexOf((View) obj);
            }
            return -1;
        }

        @Override // java.util.Stack
        public synchronized T pop() {
            T result;
            try {
                result = (T) super.pop();
                if (size() > 0) {
                    peek().setVisibility(0);
                }
                j.e(result, "result");
            } catch (Throwable th) {
                throw th;
            }
            return result;
        }

        @Override // java.util.Stack
        public T push(T item) {
            j.f(item, "item");
            if (size() > 0) {
                T tPeek = peek();
                j.c(tPeek);
                tPeek.setVisibility(8);
            }
            Object objPush = super.push(item);
            j.e(objPush, "super.push(item)");
            return (T) objPush;
        }

        public /* bridge */ boolean remove(View view) {
            return super.remove((Object) view);
        }

        @Override // java.util.Vector, java.util.AbstractCollection, java.util.Collection, java.util.List
        public final /* bridge */ boolean remove(Object obj) {
            if (obj == null ? true : obj instanceof View) {
                return remove((View) obj);
            }
            return false;
        }
    }

    public StackViewGroup(FrameLayout rootView) {
        j.f(rootView, "rootView");
        this.rootView = rootView;
        this.sceneStack = new SceneStack<>();
        this.showDuration = 200L;
        this.hideDuration = 100L;
        Interpolator interpolatorLoadInterpolator = AnimationUtils.loadInterpolator(rootView.getContext(), R.interpolator.sesl_interpolator_0_0_1_1);
        this.showHideInterpolator = interpolatorLoadInterpolator;
        Property property = View.ALPHA;
        final ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat((Object) null, (Property<Object, Float>) property, 0.0f, 1.0f);
        objectAnimatorOfFloat.setInterpolator(interpolatorLoadInterpolator);
        objectAnimatorOfFloat.setDuration(200L);
        objectAnimatorOfFloat.setStartDelay(100L);
        objectAnimatorOfFloat.addListener(new Animator.AnimatorListener() { // from class: com.google.android.material.appbar.StackViewGroup$showAnimator$1$1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
                j.f(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                j.f(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animation) {
                j.f(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                j.f(animation, "animation");
                Object target = objectAnimatorOfFloat.getTarget();
                View view = target instanceof View ? (View) target : null;
                if (view == null) {
                    return;
                }
                view.setVisibility(0);
            }
        });
        this.showAnimator = objectAnimatorOfFloat;
        final ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat((Object) null, (Property<Object, Float>) property, 1.0f, 0.0f);
        objectAnimatorOfFloat2.setInterpolator(interpolatorLoadInterpolator);
        objectAnimatorOfFloat2.setDuration(100L);
        objectAnimatorOfFloat2.addListener(new Animator.AnimatorListener() { // from class: com.google.android.material.appbar.StackViewGroup$hideAnimator$1$1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
                j.f(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                j.f(animation, "animation");
                Object target = objectAnimatorOfFloat2.getTarget();
                View view = target instanceof View ? (View) target : null;
                if (view != null) {
                    this.getRootView().removeView(view);
                }
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
        this.hideAnimator = objectAnimatorOfFloat2;
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfFloat2, objectAnimatorOfFloat);
        this.showHideAnimator = animatorSet;
    }

    public final FrameLayout getRootView() {
        return this.rootView;
    }

    public final ViewGroup pop() {
        ViewGroup viewGroup = this.sceneStack.isEmpty() ^ true ? (ViewGroup) this.sceneStack.pop() : null;
        this.rootView.removeView(viewGroup);
        return viewGroup;
    }

    public final void push(ViewGroup child) {
        if (child != null) {
            this.sceneStack.push(child);
            this.rootView.addView(child);
        }
    }

    public final void remove(ViewGroup view) {
        this.sceneStack.remove((Object) view);
        if (view != null) {
            AnimatorSet animatorSet = this.showHideAnimator;
            if (animatorSet.isRunning()) {
                animatorSet.cancel();
            }
            this.hideAnimator.setTarget(view);
            ObjectAnimator objectAnimator = this.showAnimator;
            SceneStack<ViewGroup> sceneStack = this.sceneStack;
            objectAnimator.setTarget(sceneStack.size() > 0 ? sceneStack.peek() : null);
            this.showHideAnimator.start();
        }
    }
}
