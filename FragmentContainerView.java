package androidx.fragment.app;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import com.samsung.android.keyscafe.R;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u000bH\u0001¢\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\u0011\u001a\u00028\u0000\"\n\b\u0000\u0010\u0010*\u0004\u0018\u00010\u000f¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/fragment/app/FragmentContainerView;", "Landroid/widget/FrameLayout;", "Landroid/animation/LayoutTransition;", "transition", "LT6/p;", "setLayoutTransition", "(Landroid/animation/LayoutTransition;)V", "Landroid/view/View$OnApplyWindowInsetsListener;", "listener", "setOnApplyWindowInsetsListener", "(Landroid/view/View$OnApplyWindowInsetsListener;)V", "", "drawDisappearingViewsFirst", "setDrawDisappearingViewsLast", "(Z)V", "Landroidx/fragment/app/Fragment;", "F", "getFragment", "()Landroidx/fragment/app/Fragment;", "fragment_release"}, k = 1, mv = {1, 8, 0})
public final class FragmentContainerView extends FrameLayout {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final ArrayList f7513e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final ArrayList f7514f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public View.OnApplyWindowInsetsListener f7515g;
    public boolean h;

    public FragmentContainerView(Context context) {
        super(context);
        this.f7513e = new ArrayList();
        this.f7514f = new ArrayList();
        this.h = true;
    }

    public final void a(View view) {
        if (this.f7514f.contains(view)) {
            this.f7513e.add(view);
        }
    }

    @Override // android.view.ViewGroup
    public final void addView(View child, int i5, ViewGroup.LayoutParams layoutParams) {
        kotlin.jvm.internal.j.f(child, "child");
        Object tag = child.getTag(R.id.fragment_container_view_tag);
        if ((tag instanceof Fragment ? (Fragment) tag : null) != null) {
            super.addView(child, i5, layoutParams);
            return;
        }
        throw new IllegalStateException(("Views added to a FragmentContainerView must be associated with a Fragment. View " + child + " is not associated with a Fragment.").toString());
    }

    @Override // android.view.ViewGroup, android.view.View
    public final WindowInsets dispatchApplyWindowInsets(WindowInsets insets) {
        androidx.core.view.w0 w0VarF;
        kotlin.jvm.internal.j.f(insets, "insets");
        androidx.core.view.w0 w0VarF2 = androidx.core.view.w0.f(insets, null);
        View.OnApplyWindowInsetsListener onApplyWindowInsetsListener = this.f7515g;
        if (onApplyWindowInsetsListener != null) {
            kotlin.jvm.internal.j.c(onApplyWindowInsetsListener);
            WindowInsets windowInsetsOnApplyWindowInsets = onApplyWindowInsetsListener.onApplyWindowInsets(this, insets);
            kotlin.jvm.internal.j.e(windowInsetsOnApplyWindowInsets, "onApplyWindowInsetsListe…lyWindowInsets(v, insets)");
            w0VarF = androidx.core.view.w0.f(windowInsetsOnApplyWindowInsets, null);
        } else {
            w0VarF = androidx.core.view.W.f(this, w0VarF2);
        }
        kotlin.jvm.internal.j.e(w0VarF, "if (applyWindowInsetsLis…, insetsCompat)\n        }");
        if (!w0VarF.f7266a.j()) {
            int childCount = getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                androidx.core.view.W.b(getChildAt(i5), w0VarF);
            }
        }
        return insets;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        kotlin.jvm.internal.j.f(canvas, "canvas");
        if (this.h) {
            Iterator it = this.f7513e.iterator();
            while (it.hasNext()) {
                super.drawChild(canvas, (View) it.next(), getDrawingTime());
            }
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.view.ViewGroup
    public final boolean drawChild(Canvas canvas, View child, long j5) {
        kotlin.jvm.internal.j.f(canvas, "canvas");
        kotlin.jvm.internal.j.f(child, "child");
        if (this.h) {
            ArrayList arrayList = this.f7513e;
            if ((!arrayList.isEmpty()) && arrayList.contains(child)) {
                return false;
            }
        }
        return super.drawChild(canvas, child, j5);
    }

    @Override // android.view.ViewGroup
    public final void endViewTransition(View view) {
        kotlin.jvm.internal.j.f(view, "view");
        this.f7514f.remove(view);
        if (this.f7513e.remove(view)) {
            this.h = true;
        }
        super.endViewTransition(view);
    }

    public final <F extends Fragment> F getFragment() {
        FragmentActivity fragmentActivity;
        Fragment fragment;
        Y supportFragmentManager;
        View view = this;
        while (true) {
            fragmentActivity = null;
            if (view == null) {
                fragment = null;
                break;
            }
            Object tag = view.getTag(R.id.fragment_container_view_tag);
            fragment = tag instanceof Fragment ? (Fragment) tag : null;
            if (fragment != null) {
                break;
            }
            Object parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        if (fragment == null) {
            Context context = getContext();
            while (true) {
                if (!(context instanceof ContextWrapper)) {
                    break;
                }
                if (context instanceof FragmentActivity) {
                    fragmentActivity = (FragmentActivity) context;
                    break;
                }
                context = ((ContextWrapper) context).getBaseContext();
            }
            if (fragmentActivity == null) {
                throw new IllegalStateException("View " + this + " is not within a subclass of FragmentActivity.");
            }
            supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        } else {
            if (!fragment.isAdded()) {
                throw new IllegalStateException("The Fragment " + fragment + " that owns View " + this + " has already been destroyed. Nested fragments should always use the child FragmentManager.");
            }
            supportFragmentManager = fragment.getChildFragmentManager();
        }
        return (F) supportFragmentManager.A(getId());
    }

    @Override // android.view.View
    public final WindowInsets onApplyWindowInsets(WindowInsets insets) {
        kotlin.jvm.internal.j.f(insets, "insets");
        return insets;
    }

    @Override // android.view.ViewGroup
    public final void removeAllViewsInLayout() {
        int childCount = getChildCount();
        while (true) {
            childCount--;
            if (-1 >= childCount) {
                super.removeAllViewsInLayout();
                return;
            } else {
                View view = getChildAt(childCount);
                kotlin.jvm.internal.j.e(view, "view");
                a(view);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void removeView(View view) {
        kotlin.jvm.internal.j.f(view, "view");
        a(view);
        super.removeView(view);
    }

    @Override // android.view.ViewGroup
    public final void removeViewAt(int i5) {
        View view = getChildAt(i5);
        kotlin.jvm.internal.j.e(view, "view");
        a(view);
        super.removeViewAt(i5);
    }

    @Override // android.view.ViewGroup
    public final void removeViewInLayout(View view) {
        kotlin.jvm.internal.j.f(view, "view");
        a(view);
        super.removeViewInLayout(view);
    }

    @Override // android.view.ViewGroup
    public final void removeViews(int i5, int i7) {
        int i9 = i5 + i7;
        for (int i10 = i5; i10 < i9; i10++) {
            View view = getChildAt(i10);
            kotlin.jvm.internal.j.e(view, "view");
            a(view);
        }
        super.removeViews(i5, i7);
    }

    @Override // android.view.ViewGroup
    public final void removeViewsInLayout(int i5, int i7) {
        int i9 = i5 + i7;
        for (int i10 = i5; i10 < i9; i10++) {
            View view = getChildAt(i10);
            kotlin.jvm.internal.j.e(view, "view");
            a(view);
        }
        super.removeViewsInLayout(i5, i7);
    }

    public final void setDrawDisappearingViewsLast(boolean drawDisappearingViewsFirst) {
        this.h = drawDisappearingViewsFirst;
    }

    @Override // android.view.ViewGroup
    public void setLayoutTransition(LayoutTransition transition) {
        throw new UnsupportedOperationException("FragmentContainerView does not support Layout Transitions or animateLayoutChanges=\"true\".");
    }

    @Override // android.view.View
    public void setOnApplyWindowInsetsListener(View.OnApplyWindowInsetsListener listener) {
        kotlin.jvm.internal.j.f(listener, "listener");
        this.f7515g = listener;
    }

    @Override // android.view.ViewGroup
    public final void startViewTransition(View view) {
        kotlin.jvm.internal.j.f(view, "view");
        if (view.getParent() == this) {
            this.f7514f.add(view);
        }
        super.startViewTransition(view);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentContainerView(Context context, AttributeSet attributeSet) {
        String str;
        super(context, attributeSet, 0);
        kotlin.jvm.internal.j.f(context, "context");
        this.f7513e = new ArrayList();
        this.f7514f = new ArrayList();
        this.h = true;
        if (attributeSet != null) {
            String classAttribute = attributeSet.getClassAttribute();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, X.a.f5469b, 0, 0);
            if (classAttribute == null) {
                classAttribute = typedArrayObtainStyledAttributes.getString(0);
                str = "android:name";
            } else {
                str = "class";
            }
            typedArrayObtainStyledAttributes.recycle();
            if (classAttribute == null || isInEditMode()) {
                return;
            }
            throw new UnsupportedOperationException("FragmentContainerView must be within a FragmentActivity to use " + str + "=\"" + classAttribute + '\"');
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FragmentContainerView(Context context, AttributeSet attrs, Y fm) {
        View view;
        super(context, attrs);
        kotlin.jvm.internal.j.f(context, "context");
        kotlin.jvm.internal.j.f(attrs, "attrs");
        kotlin.jvm.internal.j.f(fm, "fm");
        this.f7513e = new ArrayList();
        this.f7514f = new ArrayList();
        this.h = true;
        String classAttribute = attrs.getClassAttribute();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, X.a.f5469b, 0, 0);
        classAttribute = classAttribute == null ? typedArrayObtainStyledAttributes.getString(0) : classAttribute;
        String string = typedArrayObtainStyledAttributes.getString(1);
        typedArrayObtainStyledAttributes.recycle();
        int id = getId();
        Fragment fragmentA = fm.A(id);
        if (classAttribute != null && fragmentA == null) {
            if (id == -1) {
                throw new IllegalStateException(A8.l.t("FragmentContainerView must have an android:id to add Fragment ", classAttribute, string != null ? " with tag ".concat(string) : ""));
            }
            S sD = fm.D();
            context.getClassLoader();
            Fragment fragmentA2 = sD.a(classAttribute);
            kotlin.jvm.internal.j.e(fragmentA2, "fm.fragmentFactory.insta…ontext.classLoader, name)");
            fragmentA2.mFragmentId = id;
            fragmentA2.mContainerId = id;
            fragmentA2.mTag = string;
            fragmentA2.mFragmentManager = fm;
            fragmentA2.mHost = fm.f7583u;
            fragmentA2.onInflate(context, attrs, (Bundle) null);
            C0234a c0234a = new C0234a(fm);
            c0234a.f7681p = true;
            fragmentA2.mContainer = this;
            c0234a.d(getId(), fragmentA2, string, 1);
            if (!c0234a.f7674g) {
                c0234a.h = false;
                c0234a.f7589q.y(c0234a, true);
            } else {
                throw new IllegalStateException("This transaction is already being added to the back stack");
            }
        }
        for (f0 f0Var : fm.f7567c.d()) {
            Fragment fragment = f0Var.f7642c;
            if (fragment.mContainerId == getId() && (view = fragment.mView) != null && view.getParent() == null) {
                fragment.mContainer = this;
                f0Var.b();
            }
        }
    }
}
