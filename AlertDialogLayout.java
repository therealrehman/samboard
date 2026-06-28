package androidx.appcompat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.samsung.android.keyscafe.R;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class AlertDialogLayout extends C0 {
    public AlertDialogLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
    }

    public static int d(View view) {
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        int minimumHeight = view.getMinimumHeight();
        if (minimumHeight > 0) {
            return minimumHeight;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (viewGroup.getChildCount() == 1) {
                return d(viewGroup.getChildAt(0));
            }
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00a0  */
    @Override // androidx.appcompat.widget.C0, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onLayout(boolean r11, int r12, int r13, int r14, int r15) {
        /*
            r10 = this;
            r11 = 1
            int r0 = r10.getPaddingLeft()
            int r14 = r14 - r12
            int r12 = r10.getPaddingRight()
            int r12 = r14 - r12
            int r14 = r14 - r0
            int r1 = r10.getPaddingRight()
            int r14 = r14 - r1
            int r1 = r10.getMeasuredHeight()
            int r2 = r10.getChildCount()
            int r3 = r10.getGravity()
            r4 = r3 & 112(0x70, float:1.57E-43)
            r5 = 8388615(0x800007, float:1.1754953E-38)
            r3 = r3 & r5
            r5 = 16
            if (r4 == r5) goto L3a
            r5 = 80
            if (r4 == r5) goto L31
            int r13 = r10.getPaddingTop()
            goto L44
        L31:
            int r4 = r10.getPaddingTop()
            int r4 = r4 + r15
            int r4 = r4 - r13
            int r13 = r4 - r1
            goto L44
        L3a:
            int r4 = r10.getPaddingTop()
            int r15 = r15 - r13
            int r15 = r15 - r1
            int r15 = r15 / 2
            int r13 = r15 + r4
        L44:
            android.graphics.drawable.Drawable r15 = r10.getDividerDrawable()
            r1 = 0
            if (r15 != 0) goto L4d
            r15 = r1
            goto L51
        L4d:
            int r15 = r15.getIntrinsicHeight()
        L51:
            if (r1 >= r2) goto Lb1
            android.view.View r4 = r10.getChildAt(r1)
            if (r4 == 0) goto Laf
            int r5 = r4.getVisibility()
            r6 = 8
            if (r5 == r6) goto Laf
            int r5 = r4.getMeasuredWidth()
            int r6 = r4.getMeasuredHeight()
            android.view.ViewGroup$LayoutParams r7 = r4.getLayoutParams()
            androidx.appcompat.widget.B0 r7 = (androidx.appcompat.widget.B0) r7
            int r8 = r7.gravity
            if (r8 >= 0) goto L74
            r8 = r3
        L74:
            java.util.WeakHashMap r9 = androidx.core.view.W.f7199a
            int r9 = r10.getLayoutDirection()
            int r8 = android.view.Gravity.getAbsoluteGravity(r8, r9)
            r8 = r8 & 7
            if (r8 == r11) goto L8f
            r9 = 5
            if (r8 == r9) goto L89
            int r8 = r7.leftMargin
            int r8 = r8 + r0
            goto L9a
        L89:
            int r8 = r12 - r5
            int r9 = r7.rightMargin
        L8d:
            int r8 = r8 - r9
            goto L9a
        L8f:
            int r8 = r14 - r5
            int r8 = r8 / 2
            int r8 = r8 + r0
            int r9 = r7.leftMargin
            int r8 = r8 + r9
            int r9 = r7.rightMargin
            goto L8d
        L9a:
            boolean r9 = r10.hasDividerBeforeChildAt(r1)
            if (r9 == 0) goto La1
            int r13 = r13 + r15
        La1:
            int r9 = r7.topMargin
            int r13 = r13 + r9
            int r5 = r5 + r8
            int r9 = r13 + r6
            r4.layout(r8, r13, r5, r9)
            int r4 = r7.bottomMargin
            int r6 = r6 + r4
            int r6 = r6 + r13
            r13 = r6
        Laf:
            int r1 = r1 + r11
            goto L51
        Lb1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AlertDialogLayout.onLayout(boolean, int, int, int, int):void");
    }

    @Override // androidx.appcompat.widget.C0, android.view.View
    public final void onMeasure(int i5, int i7) {
        int iCombineMeasuredStates;
        int iD;
        int measuredHeight;
        int measuredHeight2;
        int childCount = getChildCount();
        View view = null;
        View view2 = null;
        View view3 = null;
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = getChildAt(i9);
            if (childAt.getVisibility() != 8) {
                int id = childAt.getId();
                if (id == R.id.topPanel) {
                    view = childAt;
                } else if (id == R.id.buttonPanel) {
                    view2 = childAt;
                } else {
                    if ((id != R.id.contentPanel && id != R.id.customPanel) || view3 != null) {
                        super.onMeasure(i5, i7);
                        return;
                    }
                    view3 = childAt;
                }
            }
        }
        int mode = View.MeasureSpec.getMode(i7);
        int size = View.MeasureSpec.getSize(i7);
        int mode2 = View.MeasureSpec.getMode(i5);
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        if (view != null) {
            view.measure(i5, 0);
            paddingBottom += view.getMeasuredHeight();
            iCombineMeasuredStates = View.combineMeasuredStates(0, view.getMeasuredState());
        } else {
            iCombineMeasuredStates = 0;
        }
        if (view2 != null) {
            view2.measure(i5, 0);
            iD = d(view2);
            measuredHeight = view2.getMeasuredHeight() - iD;
            paddingBottom += iD;
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view2.getMeasuredState());
        } else {
            iD = 0;
            measuredHeight = 0;
        }
        if (view3 != null) {
            view3.measure(i5, mode == 0 ? 0 : View.MeasureSpec.makeMeasureSpec(Math.max(0, size - paddingBottom), mode));
            measuredHeight2 = view3.getMeasuredHeight();
            paddingBottom += measuredHeight2;
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view3.getMeasuredState());
        } else {
            measuredHeight2 = 0;
        }
        int i10 = size - paddingBottom;
        if (view2 != null) {
            int i11 = paddingBottom - iD;
            int iMin = Math.min(i10, measuredHeight);
            if (iMin > 0) {
                i10 -= iMin;
                iD += iMin;
            }
            view2.measure(i5, View.MeasureSpec.makeMeasureSpec(iD, 1073741824));
            paddingBottom = i11 + view2.getMeasuredHeight();
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view2.getMeasuredState());
        }
        if (view3 != null && i10 > 0) {
            view3.measure(i5, View.MeasureSpec.makeMeasureSpec(measuredHeight2 + i10, mode));
            paddingBottom = (paddingBottom - measuredHeight2) + view3.getMeasuredHeight();
            iCombineMeasuredStates = View.combineMeasuredStates(iCombineMeasuredStates, view3.getMeasuredState());
        }
        int iMax = 0;
        for (int i12 = 0; i12 < childCount; i12++) {
            View childAt2 = getChildAt(i12);
            if (childAt2.getVisibility() != 8) {
                iMax = Math.max(iMax, childAt2.getMeasuredWidth());
            }
        }
        setMeasuredDimension(View.resolveSizeAndState(getPaddingRight() + getPaddingLeft() + iMax, i5, iCombineMeasuredStates), View.resolveSizeAndState(paddingBottom, i7, 0));
        if (mode2 != 1073741824) {
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
            for (int i13 = 0; i13 < childCount; i13++) {
                View childAt3 = getChildAt(i13);
                if (childAt3.getVisibility() != 8) {
                    B0 b02 = (B0) childAt3.getLayoutParams();
                    if (((LinearLayout.LayoutParams) b02).width == -1) {
                        int i14 = ((LinearLayout.LayoutParams) b02).height;
                        ((LinearLayout.LayoutParams) b02).height = childAt3.getMeasuredHeight();
                        measureChildWithMargins(childAt3, iMakeMeasureSpec, 0, i7, 0);
                        ((LinearLayout.LayoutParams) b02).height = i14;
                    }
                }
            }
        }
    }
}
