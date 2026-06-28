package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import e.AbstractC0478a;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class ViewStubCompat extends View {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f6646e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f6647f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public WeakReference f6648g;
    public LayoutInflater h;

    public ViewStubCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f6646e = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0478a.G, 0, 0);
        this.f6647f = typedArrayObtainStyledAttributes.getResourceId(2, -1);
        this.f6646e = typedArrayObtainStyledAttributes.getResourceId(1, 0);
        setId(typedArrayObtainStyledAttributes.getResourceId(0, -1));
        typedArrayObtainStyledAttributes.recycle();
        setVisibility(8);
        setWillNotDraw(true);
    }

    public final View a() {
        ViewParent parent = getParent();
        if (!(parent instanceof ViewGroup)) {
            throw new IllegalStateException("ViewStub must have a non-null ViewGroup viewParent");
        }
        if (this.f6646e == 0) {
            throw new IllegalArgumentException("ViewStub must have a valid layoutResource");
        }
        ViewGroup viewGroup = (ViewGroup) parent;
        LayoutInflater layoutInflaterFrom = this.h;
        if (layoutInflaterFrom == null) {
            layoutInflaterFrom = LayoutInflater.from(getContext());
        }
        View viewInflate = layoutInflaterFrom.inflate(this.f6646e, viewGroup, false);
        int i5 = this.f6647f;
        if (i5 != -1) {
            viewInflate.setId(i5);
        }
        int iIndexOfChild = viewGroup.indexOfChild(this);
        viewGroup.removeViewInLayout(this);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            viewGroup.addView(viewInflate, iIndexOfChild, layoutParams);
        } else {
            viewGroup.addView(viewInflate, iIndexOfChild);
        }
        this.f6648g = new WeakReference(viewInflate);
        return viewInflate;
    }

    @Override // android.view.View
    public final void dispatchDraw(Canvas canvas) {
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
    }

    public int getInflatedId() {
        return this.f6647f;
    }

    public LayoutInflater getLayoutInflater() {
        return this.h;
    }

    public int getLayoutResource() {
        return this.f6646e;
    }

    @Override // android.view.View
    public final void onMeasure(int i5, int i7) {
        setMeasuredDimension(0, 0);
    }

    public void setInflatedId(int i5) {
        this.f6647f = i5;
    }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.h = layoutInflater;
    }

    public void setLayoutResource(int i5) {
        this.f6646e = i5;
    }

    public void setOnInflateListener(g2 g2Var) {
    }

    @Override // android.view.View
    public void setVisibility(int i5) {
        WeakReference weakReference = this.f6648g;
        if (weakReference != null) {
            View view = (View) weakReference.get();
            if (view == null) {
                throw new IllegalStateException("setVisibility called on un-referenced view");
            }
            view.setVisibility(i5);
            return;
        }
        super.setVisibility(i5);
        if (i5 == 0 || i5 == 4) {
            a();
        }
    }
}
