package com.google.android.material.snackbar;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.B;
import androidx.core.view.C0233z;
import androidx.core.view.W;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.motion.MotionUtils;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SnackbarContentLayout extends LinearLayout implements ContentViewCallback {
    private Button actionView;
    private final TimeInterpolator contentInterpolator;
    private InputMethodManager mImm;
    private boolean mIsCoordinatorLayoutParent;
    private boolean mIsSuggestMultiLine;
    private SnackbarContentLayout mSnackBarContentLayout;
    private int mType;
    private int mWidthWtihAction;
    private WindowManager mWindowManager;
    private int maxInlineActionWidth;
    private int maxWidth;
    private TextView messageView;

    public SnackbarContentLayout(Context context) {
        this(context, null);
    }

    private int seslGetNavibarHeight() {
        try {
            int i5 = this.mWindowManager.getCurrentWindowMetrics().getWindowInsets().getInsets(WindowInsets.Type.navigationBars()).bottom;
            return i5 == 0 ? getResources().getDimensionPixelOffset(R.dimen.sesl_design_snackbar_layout_sip_padding_bottom) : i5;
        } catch (Exception unused) {
            return this.getResources().getDimensionPixelOffset(R.dimen.sesl_design_snackbar_layout_sip_padding_bottom);
        }
    }

    private void seslSetTouchDelegateForSnackBar() {
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.google.android.material.snackbar.SnackbarContentLayout.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    SnackbarContentLayout.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    if (SnackbarContentLayout.this.mSnackBarContentLayout == null || SnackbarContentLayout.this.actionView == null || SnackbarContentLayout.this.actionView.getVisibility() != 0) {
                        return;
                    }
                    SnackbarContentLayout.this.mSnackBarContentLayout.post(new Runnable() { // from class: com.google.android.material.snackbar.SnackbarContentLayout.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            B b3 = new B(SnackbarContentLayout.this.mSnackBarContentLayout);
                            int measuredHeight = SnackbarContentLayout.this.actionView.getMeasuredHeight() / 2;
                            b3.a(SnackbarContentLayout.this.actionView, C0233z.a(measuredHeight, measuredHeight, measuredHeight, measuredHeight));
                            SnackbarContentLayout.this.mSnackBarContentLayout.setTouchDelegate(b3);
                        }
                    });
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean seslUpdateLayoutMarginsForLandscape(int r7) {
        /*
            r6 = this;
            com.google.android.material.snackbar.SnackbarContentLayout r0 = r6.mSnackBarContentLayout
            android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
            android.view.ViewGroup$MarginLayoutParams r0 = (android.view.ViewGroup.MarginLayoutParams) r0
            android.view.inputmethod.InputMethodManager r1 = r6.mImm
            r2 = 0
            java.lang.Class[] r3 = new java.lang.Class[r2]
            java.lang.Class<android.view.inputmethod.InputMethodManager> r4 = android.view.inputmethod.InputMethodManager.class
            java.lang.String r5 = "semIsInputMethodShown"
            java.lang.reflect.Method r3 = com.bumptech.glide.c.x(r4, r5, r3)
            if (r3 == 0) goto L28
            java.lang.Object[] r4 = new java.lang.Object[r2]
            java.lang.Object r1 = com.bumptech.glide.c.C(r1, r3, r4)
            boolean r3 = r1 instanceof java.lang.Boolean
            if (r3 == 0) goto L28
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            goto L29
        L28:
            r1 = r2
        L29:
            if (r1 == 0) goto L32
            int r1 = r6.seslGetNavibarHeight()
            r0.bottomMargin = r1
            goto L3e
        L32:
            android.content.res.Resources r1 = r6.getResources()
            int r3 = com.google.android.material.R.dimen.sesl_design_snackbar_layout_padding_bottom
            int r1 = r1.getDimensionPixelOffset(r3)
            r0.bottomMargin = r1
        L3e:
            com.google.android.material.snackbar.SnackbarContentLayout r1 = r6.mSnackBarContentLayout
            android.view.ViewParent r1 = r1.getParent()
            boolean r3 = r6.mIsCoordinatorLayoutParent
            if (r3 == 0) goto L70
            boolean r3 = r1 instanceof android.view.ViewGroup
            if (r3 == 0) goto L70
            android.view.ViewGroup r1 = (android.view.ViewGroup) r1
            int r3 = r1.getMeasuredWidth()
            int r4 = r1.getPaddingLeft()
            int r1 = r1.getPaddingRight()
            int r5 = r6.mWidthWtihAction
            int r7 = java.lang.Math.min(r5, r7)
            int r3 = r3 - r7
            int r3 = r3 - r4
            int r3 = r3 - r1
            if (r3 <= 0) goto L6c
            int r3 = r3 / 2
            r0.rightMargin = r3
            r0.leftMargin = r3
            goto L70
        L6c:
            r0.rightMargin = r2
            r0.leftMargin = r2
        L70:
            com.google.android.material.snackbar.SnackbarContentLayout r6 = r6.mSnackBarContentLayout
            r6.setLayoutParams(r0)
            r6 = 1
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.snackbar.SnackbarContentLayout.seslUpdateLayoutMarginsForLandscape(int):boolean");
    }

    private boolean seslUpdateLayoutMarginsForPortrait(int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.mSnackBarContentLayout.getLayoutParams();
        ViewParent parent = this.mSnackBarContentLayout.getParent();
        if (!this.mIsCoordinatorLayoutParent || !(parent instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) parent;
        int measuredWidth = viewGroup.getMeasuredWidth();
        int paddingLeft = viewGroup.getPaddingLeft();
        int iMin = ((measuredWidth - Math.min(this.mWidthWtihAction, i5)) - paddingLeft) - viewGroup.getPaddingRight();
        if (iMin > 0) {
            int i7 = iMin / 2;
            marginLayoutParams.rightMargin = i7;
            marginLayoutParams.leftMargin = i7;
        } else {
            marginLayoutParams.rightMargin = 0;
            marginLayoutParams.leftMargin = 0;
        }
        this.mSnackBarContentLayout.setLayoutParams(marginLayoutParams);
        return true;
    }

    private static void updateTopBottomPadding(View view, int i5, int i7) {
        WeakHashMap weakHashMap = W.f7199a;
        if (view.isPaddingRelative()) {
            view.setPaddingRelative(view.getPaddingStart(), i5, view.getPaddingEnd(), i7);
        } else {
            view.setPadding(view.getPaddingLeft(), i5, view.getPaddingRight(), i7);
        }
    }

    private boolean updateViewsWithinLayout(int i5, int i7, int i9) {
        boolean z9;
        if (i5 != getOrientation()) {
            setOrientation(i5);
            z9 = true;
        } else {
            z9 = false;
        }
        if (this.messageView.getPaddingTop() == i7 && this.messageView.getPaddingBottom() == i9) {
            return z9;
        }
        updateTopBottomPadding(this.messageView, i7, i9);
        return true;
    }

    @Override // com.google.android.material.snackbar.ContentViewCallback
    public void animateContentIn(int i5, int i7) {
        this.messageView.setAlpha(0.0f);
        long j5 = i7;
        long j9 = i5;
        this.messageView.animate().alpha(1.0f).setDuration(j5).setInterpolator(this.contentInterpolator).setStartDelay(j9).start();
        if (this.actionView.getVisibility() == 0) {
            this.actionView.setAlpha(0.0f);
            this.actionView.animate().alpha(1.0f).setDuration(j5).setInterpolator(this.contentInterpolator).setStartDelay(j9).start();
        }
    }

    @Override // com.google.android.material.snackbar.ContentViewCallback
    public void animateContentOut(int i5, int i7) {
        this.messageView.setAlpha(1.0f);
        long j5 = i7;
        long j9 = i5;
        this.messageView.animate().alpha(0.0f).setDuration(j5).setInterpolator(this.contentInterpolator).setStartDelay(j9).start();
        if (this.actionView.getVisibility() == 0) {
            this.actionView.setAlpha(1.0f);
            this.actionView.animate().alpha(0.0f).setDuration(j5).setInterpolator(this.contentInterpolator).setStartDelay(j9).start();
        }
    }

    public Button getActionView() {
        return this.actionView;
    }

    public TextView getMessageView() {
        return this.messageView;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Resources resources = getContext().getResources();
        int fraction = (int) resources.getFraction(R.dimen.sesl_config_prefSnackWidth, resources.getDisplayMetrics().widthPixels, resources.getDisplayMetrics().widthPixels);
        this.mWidthWtihAction = fraction;
        this.maxWidth = fraction;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        this.messageView = (TextView) findViewById(R.id.snackbar_text);
        this.actionView = (Button) findViewById(R.id.snackbar_action);
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x0160  */
    @Override // android.widget.LinearLayout, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onMeasure(int r10, int r11) {
        /*
            Method dump skipped, instruction units count: 371
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.snackbar.SnackbarContentLayout.onMeasure(int, int):void");
    }

    public void seslSetType(int i5) {
        this.mType = i5;
    }

    public void setIsCoordinatorLayoutParent(boolean z9) {
        this.mIsCoordinatorLayoutParent = z9;
    }

    public void setMaxInlineActionWidth(int i5) {
        this.maxInlineActionWidth = i5;
    }

    public void updateActionTextColorAlphaIfNeeded(float f2) {
        if (f2 != 1.0f) {
            this.actionView.setTextColor(MaterialColors.layer(MaterialColors.getColor(this, R.attr.colorSurface), this.actionView.getCurrentTextColor(), f2));
        }
    }

    public SnackbarContentLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsCoordinatorLayoutParent = false;
        this.mIsSuggestMultiLine = false;
        this.contentInterpolator = MotionUtils.resolveThemeInterpolator(context, R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SnackbarLayout);
        this.maxWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.SnackbarLayout_android_maxWidth, -1);
        this.maxInlineActionWidth = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.SnackbarLayout_maxActionInlineWidth, -1);
        typedArrayObtainStyledAttributes.recycle();
        Resources resources = context.getResources();
        int fraction = (int) resources.getFraction(R.dimen.sesl_config_prefSnackWidth, resources.getDisplayMetrics().widthPixels, resources.getDisplayMetrics().widthPixels);
        this.mWidthWtihAction = fraction;
        this.maxWidth = fraction;
        this.mSnackBarContentLayout = (SnackbarContentLayout) findViewById(R.id.snackbar_content_layout);
        this.mImm = (InputMethodManager) context.getSystemService(InputMethodManager.class);
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        seslSetTouchDelegateForSnackBar();
    }
}
