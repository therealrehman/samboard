package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.samsung.android.keyscafe.R;
import e.AbstractC0478a;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ButtonBarLayout extends LinearLayout {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6413e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f6414f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f6415g;
    public final int h;

    public ButtonBarLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6415g = -1;
        int[] iArr = AbstractC0478a.f10565k;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        androidx.core.view.T.d(this, context, iArr, attributeSet, typedArrayObtainStyledAttributes, 0, 0);
        this.f6413e = typedArrayObtainStyledAttributes.getBoolean(0, true);
        typedArrayObtainStyledAttributes.recycle();
        if (getOrientation() == 1) {
            setStacked(this.f6413e);
        }
        this.h = (int) getResources().getDimension(R.dimen.sesl_dialog_button_bar_margin_bottom);
    }

    private void setDividerInvisible(int i5) {
        int childCount = getChildCount();
        while (i5 < childCount) {
            if (!(getChildAt(i5) instanceof Button)) {
                getChildAt(i5).setVisibility(8);
            }
            i5++;
        }
    }

    private void setDividerVisible(int i5) {
        int i7;
        int childCount = getChildCount();
        while (i5 < childCount) {
            if (!(getChildAt(i5) instanceof Button) && (i7 = i5 + 1) < childCount && (getChildAt(i7) instanceof Button) && getChildAt(i7).getVisibility() == 0) {
                getChildAt(i5).setVisibility(0);
            }
            i5++;
        }
    }

    private void setStacked(boolean z9) {
        if (this.f6414f != z9) {
            if (!z9 || this.f6413e) {
                this.f6414f = z9;
                setOrientation(z9 ? 1 : 0);
                setGravity(z9 ? 8388613 : 80);
            }
        }
    }

    public final int a(int i5) {
        int childCount = getChildCount();
        while (i5 < childCount) {
            if (getChildAt(i5).getVisibility() == 0 && (getChildAt(i5) instanceof Button)) {
                return i5;
            }
            i5++;
        }
        return -1;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i5, int i7) {
        int iMakeMeasureSpec;
        boolean z9;
        int size = View.MeasureSpec.getSize(i5);
        int measuredHeight = 0;
        if (this.f6413e) {
            if (size > this.f6415g && this.f6414f) {
                setStacked(false);
                setDividerVisible(a(0));
            }
            this.f6415g = size;
        }
        if (this.f6414f || View.MeasureSpec.getMode(i5) != 1073741824) {
            iMakeMeasureSpec = i5;
            z9 = false;
        } else {
            iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
            z9 = true;
        }
        super.onMeasure(iMakeMeasureSpec, i7);
        if (this.f6413e && !this.f6414f) {
            boolean z10 = (getMeasuredWidthAndState() & (-16777216)) == 16777216;
            if (z10) {
                setStacked(true);
                setDividerInvisible(0);
                setGravity(17);
                z9 = true;
            }
            if (z10) {
                int childCount = getChildCount();
                for (int i9 = 0; i9 < childCount; i9++) {
                    View childAt = getChildAt(i9);
                    if (childAt instanceof Button) {
                        ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                            layoutParams.width = -1;
                            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                            if (i9 < childCount - 1) {
                                marginLayoutParams.setMargins(0, 0, 0, this.h);
                            }
                            childAt.setLayoutParams(marginLayoutParams);
                        }
                    }
                }
            } else {
                int childCount2 = getChildCount();
                for (int i10 = 0; i10 < childCount2; i10++) {
                    View childAt2 = getChildAt(i10);
                    if (childAt2 instanceof Button) {
                        ViewGroup.LayoutParams layoutParams2 = childAt2.getLayoutParams();
                        if (layoutParams2 instanceof ViewGroup.MarginLayoutParams) {
                            layoutParams2.width = -2;
                            ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) layoutParams2;
                            if (i10 < childCount2 - 1) {
                                marginLayoutParams2.setMargins(0, 0, 0, 0);
                            }
                            childAt2.setLayoutParams(marginLayoutParams2);
                        }
                    }
                }
            }
        }
        if (z9) {
            super.onMeasure(i5, i7);
        }
        int iA = a(0);
        if (iA >= 0) {
            View childAt3 = getChildAt(iA);
            LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) childAt3.getLayoutParams();
            measuredHeight = childAt3.getMeasuredHeight() + getPaddingTop() + layoutParams3.topMargin + layoutParams3.bottomMargin;
            if (this.f6414f) {
                int iA2 = a(iA + 1);
                if (iA2 >= 0) {
                    measuredHeight = getChildAt(iA2).getPaddingTop() + ((int) (getResources().getDisplayMetrics().density * 16.0f)) + measuredHeight;
                }
            } else {
                measuredHeight += getPaddingBottom();
            }
        }
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        if (getMinimumHeight() != measuredHeight) {
            setMinimumHeight(measuredHeight);
            if (i7 == 0 || z9) {
                super.onMeasure(i5, i7);
            }
        }
    }

    public void setAllowStacking(boolean z9) {
        if (this.f6413e != z9) {
            this.f6413e = z9;
            if (!z9 && this.f6414f) {
                setStacked(false);
            }
            requestLayout();
        }
    }
}
