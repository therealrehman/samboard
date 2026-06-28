package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.ThemedSpinnerAdapter;
import androidx.appcompat.graphics.drawable.SeslRecoilDrawable;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatSpinner extends Spinner {
    public static final int[] o = {R.attr.spinnerMode};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C0188t f6399e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Context f6400f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final M f6401g;
    public SpinnerAdapter h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f6402i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Y f6403j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f6404k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f6405l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Rect f6406m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f6407n;

    /* JADX WARN: Removed duplicated region for block: B:26:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AppCompatSpinner(android.content.Context r13, android.util.AttributeSet r14) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 212
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatSpinner.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public final int a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i5 = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int iMax = Math.max(0, getSelectedItemPosition());
        int iMin = Math.min(spinnerAdapter.getCount(), iMax + 15);
        View view = null;
        int iMax2 = 0;
        for (int iMax3 = Math.max(0, iMax - (15 - (iMin - iMax))); iMax3 < iMin; iMax3++) {
            int itemViewType = spinnerAdapter.getItemViewType(iMax3);
            if (itemViewType != i5) {
                view = null;
                i5 = itemViewType;
            }
            view = spinnerAdapter.getView(iMax3, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            iMax2 = Math.max(iMax2, view.getMeasuredWidth());
        }
        if (drawable == null) {
            return iMax2;
        }
        Rect rect = this.f6406m;
        drawable.getPadding(rect);
        return iMax2 + rect.left + rect.right;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C0188t c0188t = this.f6399e;
        if (c0188t != null) {
            c0188t.a();
        }
    }

    @Override // android.widget.Spinner
    public int getDropDownHorizontalOffset() {
        Y y4 = this.f6403j;
        return y4 != null ? y4.b() : super.getDropDownHorizontalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownVerticalOffset() {
        Y y4 = this.f6403j;
        return y4 != null ? y4.k() : super.getDropDownVerticalOffset();
    }

    @Override // android.widget.Spinner
    public int getDropDownWidth() {
        return this.f6403j != null ? this.f6404k : super.getDropDownWidth();
    }

    public final Y getInternalPopup() {
        return this.f6403j;
    }

    @Override // android.widget.Spinner
    public Drawable getPopupBackground() {
        Y y4 = this.f6403j;
        return y4 != null ? y4.getBackground() : super.getPopupBackground();
    }

    @Override // android.widget.Spinner
    public Context getPopupContext() {
        return this.f6400f;
    }

    @Override // android.widget.Spinner
    public CharSequence getPrompt() {
        Y y4 = this.f6403j;
        return y4 != null ? y4.l() : super.getPrompt();
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0188t c0188t = this.f6399e;
        if (c0188t != null) {
            return c0188t.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0188t c0188t = this.f6399e;
        if (c0188t != null) {
            return c0188t.c();
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getBackground() instanceof SeslRecoilDrawable) {
            this.f6407n = true;
        }
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Y y4 = this.f6403j;
        if (y4 == null || !y4.a()) {
            return;
        }
        y4.dismiss();
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        View selectedView = getSelectedView();
        StringBuilder sb = new StringBuilder();
        if (selectedView instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) selectedView;
            for (int i5 = 0; i5 < viewGroup.getChildCount(); i5++) {
                View childAt = viewGroup.getChildAt(i5);
                if (childAt instanceof TextView) {
                    TextView textView = (TextView) childAt;
                    if (sb.length() == 0) {
                        sb = new StringBuilder(textView.getText());
                    } else {
                        sb.append(" ");
                        sb.append(textView.getText());
                    }
                }
            }
        } else if (selectedView instanceof TextView) {
            sb = new StringBuilder(((TextView) selectedView).getText());
        }
        accessibilityNodeInfo.setContentDescription(sb.toString());
        accessibilityNodeInfo.setClassName(Spinner.class.getName());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final void onMeasure(int i5, int i7) {
        int iA;
        super.onMeasure(i5, i7);
        if (this.f6403j == null || View.MeasureSpec.getMode(i5) != Integer.MIN_VALUE) {
            return;
        }
        getMeasuredWidth();
        if (getSelectedItemPosition() <= -1 || getSelectedItemPosition() >= getAdapter().getCount()) {
            iA = a(getAdapter(), getBackground());
        } else {
            SpinnerAdapter adapter = getAdapter();
            Drawable background = getBackground();
            iA = 0;
            if (adapter != null) {
                int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
                View view = adapter.getView(getSelectedItemPosition(), null, this);
                if (view.getLayoutParams() == null) {
                    view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
                }
                view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
                int measuredWidth = view.getMeasuredWidth();
                if (background != null) {
                    Rect rect = this.f6406m;
                    background.getPadding(rect);
                    iA = rect.left + rect.right + measuredWidth;
                } else {
                    iA = measuredWidth;
                }
            }
        }
        setMeasuredDimension(Math.min(iA, View.MeasureSpec.getSize(i5)), getMeasuredHeight());
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        X x9 = (X) parcelable;
        super.onRestoreInstanceState(x9.getSuperState());
        if (!x9.f6657e || (viewTreeObserver = getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(new N(0, this));
    }

    @Override // android.widget.Spinner, android.widget.AbsSpinner, android.view.View
    public final Parcelable onSaveInstanceState() {
        X x9 = new X(super.onSaveInstanceState());
        Y y4 = this.f6403j;
        x9.f6657e = y4 != null && y4.a();
        return x9;
    }

    @Override // android.widget.Spinner, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        M m6 = this.f6401g;
        if (m6 == null || !m6.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    @Override // android.widget.Spinner, android.view.View
    public final boolean performClick() {
        Y y4 = this.f6403j;
        if (y4 == null) {
            return super.performClick();
        }
        playSoundEffect(0);
        if (y4.a()) {
            return true;
        }
        this.f6403j.j(P.b(this), P.a(this));
        return true;
    }

    @Override // android.view.View
    public final void refreshDrawableState() {
        super.refreshDrawableState();
        if (!this.f6407n || getStateListAnimator() == null) {
            return;
        }
        getStateListAnimator().jumpToCurrentState();
        this.f6407n = false;
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0188t c0188t = this.f6399e;
        if (c0188t != null) {
            c0188t.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i5) {
        super.setBackgroundResource(i5);
        C0188t c0188t = this.f6399e;
        if (c0188t != null) {
            c0188t.f(i5);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownHorizontalOffset(int i5) {
        Y y4 = this.f6403j;
        if (y4 == null) {
            super.setDropDownHorizontalOffset(i5);
        } else {
            y4.h(i5);
            y4.i(i5);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownVerticalOffset(int i5) {
        Y y4 = this.f6403j;
        if (y4 != null) {
            y4.g(i5);
        } else {
            super.setDropDownVerticalOffset(i5);
        }
    }

    @Override // android.widget.Spinner
    public void setDropDownWidth(int i5) {
        if (this.f6403j != null) {
            this.f6404k = i5;
        } else {
            super.setDropDownWidth(i5);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundDrawable(Drawable drawable) {
        Y y4 = this.f6403j;
        if (y4 != null) {
            y4.setBackgroundDrawable(drawable);
        } else {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    @Override // android.widget.Spinner
    public void setPopupBackgroundResource(int i5) {
        setPopupBackgroundDrawable(android.support.v4.media.session.f.y(getPopupContext(), i5));
    }

    @Override // android.widget.Spinner
    public void setPrompt(CharSequence charSequence) {
        Y y4 = this.f6403j;
        if (y4 != null) {
            y4.f(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0188t c0188t = this.f6399e;
        if (c0188t != null) {
            c0188t.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0188t c0188t = this.f6399e;
        if (c0188t != null) {
            c0188t.i(mode);
        }
    }

    @Override // android.widget.AdapterView
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.f6402i) {
            this.h = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        Y y4 = this.f6403j;
        if (y4 != null) {
            Context context = this.f6400f;
            if (context == null) {
                context = getContext();
            }
            Resources.Theme theme = context.getTheme();
            T t8 = new T();
            t8.f6633e = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                t8.f6634f = (ListAdapter) spinnerAdapter;
            }
            if (theme != null && (spinnerAdapter instanceof ThemedSpinnerAdapter)) {
                Q.a((ThemedSpinnerAdapter) spinnerAdapter, theme);
            }
            y4.m(t8);
        }
    }
}
