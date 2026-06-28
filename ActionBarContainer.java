package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.samsung.android.keyscafe.R;
import e.AbstractC0478a;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContainer extends FrameLayout {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6325e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public View f6326f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public View f6327g;
    public Drawable h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Drawable f6328i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public Drawable f6329j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f6330k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f6331l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f6332m;

    public ActionBarContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        C0132a c0132a = new C0132a(this);
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        setBackground(c0132a);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0478a.f10556a);
        boolean z9 = false;
        this.h = typedArrayObtainStyledAttributes.getDrawable(0);
        this.f6328i = typedArrayObtainStyledAttributes.getDrawable(2);
        this.f6332m = typedArrayObtainStyledAttributes.getDimensionPixelSize(13, -1);
        if (getId() == R.id.split_action_bar) {
            this.f6330k = true;
            this.f6329j = typedArrayObtainStyledAttributes.getDrawable(1);
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!this.f6330k ? !(this.h != null || this.f6328i != null) : this.f6329j == null) {
            z9 = true;
        }
        setWillNotDraw(z9);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.h;
        if (drawable != null && drawable.isStateful()) {
            this.h.setState(getDrawableState());
        }
        Drawable drawable2 = this.f6328i;
        if (drawable2 != null && drawable2.isStateful()) {
            this.f6328i.setState(getDrawableState());
        }
        Drawable drawable3 = this.f6329j;
        if (drawable3 == null || !drawable3.isStateful()) {
            return;
        }
        this.f6329j.setState(getDrawableState());
    }

    public View getTabContainer() {
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.h;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
        Drawable drawable2 = this.f6328i;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
        }
        Drawable drawable3 = this.f6329j;
        if (drawable3 != null) {
            drawable3.jumpToCurrentState();
        }
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.f6326f = findViewById(R.id.action_bar);
        this.f6327g = findViewById(R.id.action_context_bar);
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        super.onHoverEvent(motionEvent);
        return true;
    }

    @Override // android.view.ViewGroup
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return this.f6325e || super.onInterceptTouchEvent(motionEvent);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        super.onLayout(z9, i5, i7, i9, i10);
        boolean z10 = true;
        if (this.f6330k) {
            Drawable drawable = this.f6329j;
            if (drawable != null) {
                drawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
            } else {
                z10 = false;
            }
        } else {
            if (this.h == null) {
                z10 = false;
            } else if (this.f6326f.getVisibility() == 0) {
                this.h.setBounds(this.f6326f.getLeft(), this.f6326f.getTop(), this.f6326f.getRight(), getPaddingBottom() + this.f6326f.getBottom());
            } else {
                View view = this.f6327g;
                if (view == null || view.getVisibility() != 0) {
                    this.h.setBounds(0, 0, 0, 0);
                } else {
                    this.h.setBounds(this.f6327g.getLeft(), this.f6327g.getTop(), this.f6327g.getRight(), getPaddingBottom() + this.f6327g.getBottom());
                }
            }
            this.f6331l = false;
        }
        if (z10) {
            invalidate();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i5, int i7) {
        int i9;
        if (this.f6326f == null && View.MeasureSpec.getMode(i7) == Integer.MIN_VALUE && (i9 = this.f6332m) >= 0) {
            i7 = View.MeasureSpec.makeMeasureSpec(Math.min(i9, View.MeasureSpec.getSize(i7)), Integer.MIN_VALUE);
        }
        super.onMeasure(i5, i7);
        if (this.f6326f == null) {
            return;
        }
        View.MeasureSpec.getMode(i7);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrimaryBackground(Drawable drawable) {
        Drawable drawable2 = this.h;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.h);
        }
        this.h = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            View view = this.f6326f;
            if (view != null) {
                this.h.setBounds(view.getLeft(), this.f6326f.getTop(), this.f6326f.getRight(), this.f6326f.getBottom());
            }
        }
        boolean z9 = false;
        if (!this.f6330k ? !(this.h != null || this.f6328i != null) : this.f6329j == null) {
            z9 = true;
        }
        setWillNotDraw(z9);
        invalidate();
        invalidateOutline();
    }

    public void setSplitBackground(Drawable drawable) {
        Drawable drawable2;
        Drawable drawable3 = this.f6329j;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.f6329j);
        }
        this.f6329j = drawable;
        boolean z9 = this.f6330k;
        boolean z10 = false;
        if (drawable != null) {
            drawable.setCallback(this);
            if (z9 && (drawable2 = this.f6329j) != null) {
                drawable2.setBounds(0, 0, getMeasuredWidth(), getPaddingBottom() + getMeasuredHeight());
            }
        }
        if (!z9 ? !(this.h != null || this.f6328i != null) : this.f6329j == null) {
            z10 = true;
        }
        setWillNotDraw(z10);
        invalidate();
        invalidateOutline();
    }

    public void setStackedBackground(Drawable drawable) {
        Drawable drawable2 = this.f6328i;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.f6328i);
        }
        this.f6328i = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            if (this.f6331l && this.f6328i != null) {
                throw null;
            }
        }
        boolean z9 = false;
        if (!this.f6330k ? !(this.h != null || this.f6328i != null) : this.f6329j == null) {
            z9 = true;
        }
        setWillNotDraw(z9);
        invalidate();
        invalidateOutline();
    }

    public void setTabContainer(S0 s02) {
    }

    public void setTransitioning(boolean z9) {
        this.f6325e = z9;
        setDescendantFocusability(z9 ? 393216 : 262144);
    }

    @Override // android.view.View
    public void setVisibility(int i5) {
        super.setVisibility(i5);
        boolean z9 = i5 == 0;
        Drawable drawable = this.h;
        if (drawable != null) {
            drawable.setVisible(z9, false);
        }
        Drawable drawable2 = this.f6328i;
        if (drawable2 != null) {
            drawable2.setVisible(z9, false);
        }
        Drawable drawable3 = this.f6329j;
        if (drawable3 != null) {
            drawable3.setVisible(z9, false);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final ActionMode startActionModeForChild(View view, ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.View
    public final boolean verifyDrawable(Drawable drawable) {
        Drawable drawable2 = this.h;
        boolean z9 = this.f6330k;
        return (drawable == drawable2 && !z9) || (drawable == this.f6328i && this.f6331l) || ((drawable == this.f6329j && z9) || super.verifyDrawable(drawable));
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final ActionMode startActionModeForChild(View view, ActionMode.Callback callback, int i5) {
        if (i5 != 0) {
            return super.startActionModeForChild(view, callback, i5);
        }
        return null;
    }
}
