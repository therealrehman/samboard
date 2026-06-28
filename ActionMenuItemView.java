package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import androidx.appcompat.widget.C0154h0;
import androidx.appcompat.widget.InterfaceC0174o;
import androidx.appcompat.widget.d2;
import e.AbstractC0478a;

/* JADX INFO: loaded from: classes.dex */
public class ActionMenuItemView extends C0154h0 implements x, View.OnClickListener, InterfaceC0174o, View.OnLongClickListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public l f6170e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CharSequence f6171f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Drawable f6172g;
    public i h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public C0129b f6173i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public AbstractC0130c f6174j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f6175k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f6176l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f6177m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f6178n;
    public final int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f6179p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final float f6180q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Drawable f6181r;

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        this.f6179p = false;
        this.f6180q = 0.0f;
        Resources resources = context.getResources();
        this.f6175k = e();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, AbstractC0478a.f10558c, 0, 0);
        this.f6177m = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.o = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        setOnLongClickListener(this);
        this.f6178n = -1;
        setSaveEnabled(false);
        Resources.Theme theme = context.getTheme();
        int[] iArr = AbstractC0478a.f10564j;
        TypedArray typedArrayObtainStyledAttributes2 = theme.obtainStyledAttributes(null, iArr, 0, 0);
        int resourceId = typedArrayObtainStyledAttributes2.getResourceId(26, 0);
        typedArrayObtainStyledAttributes2.recycle();
        TypedArray typedArrayObtainStyledAttributes3 = context.obtainStyledAttributes(resourceId, AbstractC0478a.f10552C);
        TypedValue typedValuePeekValue = typedArrayObtainStyledAttributes3.peekValue(0);
        typedArrayObtainStyledAttributes3.recycle();
        if (typedValuePeekValue != null) {
            this.f6180q = TypedValue.complexToFloat(typedValuePeekValue.data);
        }
        seslSetButtonShapeEnabled(true);
        TypedArray typedArrayObtainStyledAttributes4 = context.getTheme().obtainStyledAttributes(null, iArr, 0, 0);
        int resourceId2 = typedArrayObtainStyledAttributes4.getResourceId(24, 0);
        typedArrayObtainStyledAttributes4.recycle();
        TypedArray typedArrayObtainStyledAttributes5 = context.getTheme().obtainStyledAttributes(resourceId2, new int[]{R.attr.background});
        this.f6181r = typedArrayObtainStyledAttributes5.getDrawable(0);
        typedArrayObtainStyledAttributes5.recycle();
    }

    @Override // androidx.appcompat.widget.InterfaceC0174o
    public final boolean a() {
        return d();
    }

    @Override // androidx.appcompat.widget.InterfaceC0174o
    public final boolean b() {
        return d() && this.f6170e.getIcon() == null;
    }

    public final boolean d() {
        return !TextUtils.isEmpty(getText());
    }

    @Override // android.view.View
    public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        onPopulateAccessibilityEvent(accessibilityEvent);
        return true;
    }

    public final boolean e() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i5 = configuration.screenWidthDp;
        return i5 >= 480 || (i5 >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    public final void f() {
        boolean z9 = (!TextUtils.isEmpty(this.f6171f)) & (this.f6172g == null || ((this.f6170e.f6263C & 4) == 4 && (this.f6175k || this.f6176l)));
        setText(z9 ? this.f6171f : null);
        if (z9) {
            setBackgroundResource(s6.c.O(getContext()) ? com.samsung.android.keyscafe.R.drawable.sesl_action_bar_item_text_background_light : com.samsung.android.keyscafe.R.drawable.sesl_action_bar_item_text_background_dark);
        } else {
            setBackground(this.f6181r);
        }
        CharSequence charSequence = this.f6170e.f6284u;
        if (TextUtils.isEmpty(charSequence)) {
            setContentDescription(z9 ? null : this.f6170e.f6273i);
        } else {
            setContentDescription(charSequence);
        }
        CharSequence charSequence2 = this.f6170e.f6285v;
        if (TextUtils.isEmpty(charSequence2)) {
            d2.a(this, z9 ? null : this.f6170e.f6273i);
        } else {
            d2.a(this, charSequence2);
        }
        float f2 = this.f6180q;
        if (f2 > 0.0f) {
            setTextSize(1, f2 * Math.min(getResources().getConfiguration().fontScale, 1.2f));
        }
        setText(z9 ? this.f6171f : null);
    }

    @Override // android.widget.TextView, android.view.View
    public CharSequence getAccessibilityClassName() {
        return Button.class.getName();
    }

    @Override // androidx.appcompat.view.menu.x
    public l getItemData() {
        return this.f6170e;
    }

    @Override // androidx.appcompat.view.menu.x
    public final void initialize(l lVar, int i5) {
        this.f6170e = lVar;
        setIcon(lVar.getIcon());
        setTitle(lVar.getTitleCondensed());
        setId(lVar.f6270e);
        setVisibility(lVar.isVisible() ? 0 : 8);
        setEnabled(lVar.isEnabled());
        if (lVar.hasSubMenu() && this.f6173i == null) {
            this.f6173i = new C0129b(this);
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        i iVar = this.h;
        if (iVar != null) {
            iVar.a(this.f6170e);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f6175k = e();
        f();
    }

    @Override // android.view.View
    public final void onHoverChanged(boolean z9) {
        super.onHoverChanged(z9);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    @Override // android.view.View.OnLongClickListener
    public final boolean onLongClick(View view) {
        return false;
    }

    @Override // androidx.appcompat.widget.C0154h0, android.widget.TextView, android.view.View
    public final void onMeasure(int i5, int i7) {
        int i9;
        boolean zD = d();
        if (zD && (i9 = this.f6178n) >= 0) {
            super.setPadding(i9, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i5, i7);
        int mode = View.MeasureSpec.getMode(i5);
        int size = View.MeasureSpec.getSize(i5);
        int measuredWidth = getMeasuredWidth();
        int i10 = this.f6177m;
        int iMin = mode == Integer.MIN_VALUE ? Math.min(size, i10) : i10;
        if (mode != 1073741824 && i10 > 0 && measuredWidth < iMin) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(iMin, 1073741824), i7);
        }
        if (zD || this.f6172g == null) {
            return;
        }
        int measuredWidth2 = getMeasuredWidth();
        int iWidth = this.f6172g.getBounds().width();
        if (this.f6179p) {
            return;
        }
        super.setPadding((measuredWidth2 - iWidth) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    @Override // android.view.View
    public final void onPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onPopulateAccessibilityEvent(accessibilityEvent);
        CharSequence contentDescription = getContentDescription();
        if (TextUtils.isEmpty(contentDescription)) {
            return;
        }
        accessibilityEvent.getText().add(contentDescription);
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        C0129b c0129b;
        if (this.f6170e.hasSubMenu() && (c0129b = this.f6173i) != null && c0129b.onTouch(this, motionEvent)) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.TextView, android.view.View
    public final boolean performLongClick() {
        if (this.f6172g == null) {
            return true;
        }
        return super.performLongClick();
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        super.setBackground(drawable);
    }

    public void setCheckable(boolean z9) {
    }

    public void setChecked(boolean z9) {
    }

    public void setExpandedFormat(boolean z9) {
        if (this.f6176l != z9) {
            this.f6176l = z9;
            l lVar = this.f6170e;
            if (lVar != null) {
                lVar.f6281r.onItemActionRequestChanged(lVar);
            }
        }
    }

    @Override // android.widget.TextView
    public final boolean setFrame(int i5, int i7, int i9, int i10) {
        boolean frame = super.setFrame(i5, i7, i9, i10);
        if (!this.f6179p) {
            return frame;
        }
        Drawable background = getBackground();
        if (this.f6172g != null && background != null) {
            int width = getWidth();
            int height = getHeight();
            int paddingLeft = (getPaddingLeft() - getPaddingRight()) / 2;
            E.a.f(background, paddingLeft, 0, width + paddingLeft, height);
        } else if (background != null) {
            E.a.f(background, 0, 0, getWidth(), getHeight());
        }
        return frame;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setIcon(android.graphics.drawable.Drawable r5) {
        /*
            r4 = this;
            r4.f6172g = r5
            if (r5 == 0) goto L25
            int r0 = r5.getIntrinsicWidth()
            int r1 = r5.getIntrinsicHeight()
            int r2 = r4.o
            if (r0 <= r2) goto L17
            float r3 = (float) r2
            float r0 = (float) r0
            float r3 = r3 / r0
            float r0 = (float) r1
            float r0 = r0 * r3
            int r1 = (int) r0
            r0 = r2
        L17:
            if (r1 <= r2) goto L20
            float r3 = (float) r2
            float r1 = (float) r1
            float r3 = r3 / r1
            float r0 = (float) r0
            float r0 = r0 * r3
            int r0 = (int) r0
            goto L21
        L20:
            r2 = r1
        L21:
            r1 = 0
            r5.setBounds(r1, r1, r0, r2)
        L25:
            r0 = 0
            r4.setCompoundDrawables(r5, r0, r0, r0)
            boolean r1 = r4.d()
            if (r1 == 0) goto L3c
            java.util.WeakHashMap r1 = androidx.core.view.W.f7199a
            int r1 = r4.getLayoutDirection()
            r2 = 1
            if (r1 != r2) goto L3c
            r4.setCompoundDrawables(r0, r0, r5, r0)
            goto L3f
        L3c:
            r4.setCompoundDrawables(r5, r0, r0, r0)
        L3f:
            r4.f()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.ActionMenuItemView.setIcon(android.graphics.drawable.Drawable):void");
    }

    public void setIsLastItem(boolean z9) {
    }

    public void setItemInvoker(i iVar) {
        this.h = iVar;
    }

    @Override // android.widget.TextView, android.view.View
    public final void setPadding(int i5, int i7, int i9, int i10) {
        this.f6178n = i5;
        super.setPadding(i5, i7, i9, i10);
    }

    @Override // android.widget.TextView, android.view.View
    public final void setPaddingRelative(int i5, int i7, int i9, int i10) {
        this.f6178n = i5;
        this.f6179p = true;
        super.setPaddingRelative(i5, i7, i9, i10);
    }

    public void setPopupCallback(AbstractC0130c abstractC0130c) {
        this.f6174j = abstractC0130c;
    }

    public void setTitle(CharSequence charSequence) {
        this.f6171f = charSequence;
        setContentDescription(charSequence);
        f();
    }
}
