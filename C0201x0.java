package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.samsung.android.keyscafe.R;

/* JADX INFO: renamed from: androidx.appcompat.widget.x0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0201x0 extends ListView {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Rect f6880e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f6881f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f6882g;
    public int h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f6883i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f6884j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public C0192u0 f6885k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f6886l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f6887m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f6888n;
    public P.g o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public RunnableC0198w0 f6889p;

    public C0201x0(Context context, boolean z9) {
        super(context, null, R.attr.dropDownListViewStyle);
        this.f6880e = new Rect();
        this.f6881f = 0;
        this.f6882g = 0;
        this.h = 0;
        this.f6883i = 0;
        this.f6887m = z9;
        setCacheColorHint(0);
    }

    public final int a(int i5, int i7) {
        int listPaddingTop = getListPaddingTop();
        int listPaddingBottom = getListPaddingBottom();
        int dividerHeight = getDividerHeight();
        Drawable divider = getDivider();
        ListAdapter adapter = getAdapter();
        if (adapter == null) {
            return listPaddingTop + listPaddingBottom;
        }
        int measuredHeight = listPaddingTop + listPaddingBottom;
        if (dividerHeight <= 0 || divider == null) {
            dividerHeight = 0;
        }
        int count = adapter.getCount();
        int i9 = 0;
        View view = null;
        for (int i10 = 0; i10 < count; i10++) {
            int itemViewType = adapter.getItemViewType(i10);
            if (itemViewType != i9) {
                view = null;
                i9 = itemViewType;
            }
            view = adapter.getView(i10, view, this);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = generateDefaultLayoutParams();
                view.setLayoutParams(layoutParams);
            }
            int i11 = layoutParams.height;
            view.measure(i5, i11 > 0 ? View.MeasureSpec.makeMeasureSpec(i11, 1073741824) : View.MeasureSpec.makeMeasureSpec(0, 0));
            view.forceLayout();
            if (i10 > 0) {
                measuredHeight += dividerHeight;
            }
            measuredHeight += view.getMeasuredHeight();
            if (measuredHeight >= i7) {
                return i7;
            }
        }
        return measuredHeight;
    }

    /* JADX WARN: Removed duplicated region for block: B:82:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0015  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean b(android.view.MotionEvent r17, int r18) {
        /*
            Method dump skipped, instruction units count: 392
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.C0201x0.b(android.view.MotionEvent, int):boolean");
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        Drawable selector;
        Rect rect = this.f6880e;
        if (!rect.isEmpty() && (selector = getSelector()) != null) {
            selector.setBounds(rect);
            selector.draw(canvas);
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public final void drawableStateChanged() {
        if (this.f6889p != null) {
            return;
        }
        super.drawableStateChanged();
        C0192u0 c0192u0 = this.f6885k;
        if (c0192u0 != null) {
            c0192u0.f6859e = true;
        }
        Drawable selector = getSelector();
        if (selector != null && this.f6888n && isPressed()) {
            selector.setState(getDrawableState());
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean hasFocus() {
        return this.f6887m || super.hasFocus();
    }

    @Override // android.view.View
    public final boolean hasWindowFocus() {
        return this.f6887m || super.hasWindowFocus();
    }

    @Override // android.view.View
    public final boolean isFocused() {
        return this.f6887m || super.isFocused();
    }

    @Override // android.view.View
    public final boolean isInTouchMode() {
        return (this.f6887m && this.f6886l) || super.isInTouchMode();
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        this.f6889p = null;
        super.onDetachedFromWindow();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004f  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onHoverEvent(android.view.MotionEvent r5) {
        /*
            r4 = this;
            int r0 = r5.getActionMasked()
            r1 = 10
            if (r0 != r1) goto L17
            androidx.appcompat.widget.w0 r1 = r4.f6889p
            if (r1 != 0) goto L17
            androidx.appcompat.widget.w0 r1 = new androidx.appcompat.widget.w0
            r2 = 0
            r1.<init>(r2, r4)
            r4.f6889p = r1
            r4.post(r1)
        L17:
            boolean r1 = super.onHoverEvent(r5)
            r2 = 9
            r3 = -1
            if (r0 == r2) goto L28
            r2 = 7
            if (r0 != r2) goto L24
            goto L28
        L24:
            r4.setSelection(r3)
            goto L73
        L28:
            float r0 = r5.getX()
            int r0 = (int) r0
            float r5 = r5.getY()
            int r5 = (int) r5
            int r5 = r4.pointToPosition(r0, r5)
            java.lang.Class<android.widget.AdapterView> r0 = android.widget.AdapterView.class
            java.lang.String r2 = "mSelectedPosition"
            java.lang.reflect.Field r0 = com.bumptech.glide.c.s(r0, r2)
            if (r0 == 0) goto L4f
            java.lang.Object r0 = com.bumptech.glide.c.m(r4, r0)
            boolean r2 = r0 instanceof java.lang.Integer
            if (r2 == 0) goto L4f
            java.lang.Integer r0 = (java.lang.Integer) r0
            int r0 = r0.intValue()
            goto L50
        L4f:
            r0 = r3
        L50:
            if (r5 == r3) goto L73
            if (r5 == r0) goto L73
            int r0 = r4.getFirstVisiblePosition()
            int r5 = r5 - r0
            android.view.View r5 = r4.getChildAt(r5)
            boolean r5 = r5.isEnabled()
            if (r5 == 0) goto L70
            r4.requestFocus()
            boolean r5 = r4.isHovered()
            if (r5 != 0) goto L70
            r5 = 1
            r4.setHovered(r5)
        L70:
            r4.drawableStateChanged()
        L73:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.C0201x0.onHoverEvent(android.view.MotionEvent):boolean");
    }

    @Override // android.widget.AbsListView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f6884j = pointToPosition((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        RunnableC0198w0 runnableC0198w0 = this.f6889p;
        if (runnableC0198w0 != null) {
            C0201x0 c0201x0 = (C0201x0) runnableC0198w0.f6871f;
            c0201x0.f6889p = null;
            c0201x0.removeCallbacks(runnableC0198w0);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void setListSelectionHidden(boolean z9) {
        this.f6886l = z9;
    }

    @Override // android.widget.AbsListView
    public void setSelector(Drawable drawable) {
        C0192u0 c0192u0;
        if (drawable != null) {
            c0192u0 = new C0192u0(drawable);
            c0192u0.f6859e = true;
        } else {
            c0192u0 = null;
        }
        this.f6885k = c0192u0;
        super.setSelector(c0192u0);
        Rect rect = new Rect();
        if (drawable != null) {
            drawable.getPadding(rect);
        }
        this.f6881f = rect.left;
        this.f6882g = rect.top;
        this.h = rect.right;
        this.f6883i = rect.bottom;
    }
}
