package com.google.android.material.snackbar;

import C5.b;
import android.content.ContentResolver;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.R;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.util.MaxFontScaleRatio;
import com.google.android.material.util.SeslTextViewHelperKt;
import h6.AbstractC0582a;

/* JADX INFO: loaded from: classes.dex */
public class Snackbar extends BaseTransientBottomBar<Snackbar> {
    public static final int SESL_SNACKBAR_TYPE_DEFAULT = -1;
    public static final int SESL_SNACKBAR_TYPE_SUGGESTION = 0;
    private static final int[] SNACKBAR_BUTTON_STYLE_ATTR;
    private static final int[] SNACKBAR_CONTENT_STYLE_ATTRS;
    private static boolean mIsCoordinatorLayoutParent;
    private final AccessibilityManager accessibilityManager;
    private BaseTransientBottomBar.BaseCallback<Snackbar> callback;
    private boolean hasAction;
    private int mType;

    public static class Callback extends BaseTransientBottomBar.BaseCallback<Snackbar> {
        public static final int DISMISS_EVENT_ACTION = 1;
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;
        public static final int DISMISS_EVENT_MANUAL = 3;
        public static final int DISMISS_EVENT_SWIPE = 0;
        public static final int DISMISS_EVENT_TIMEOUT = 2;

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback
        public void onDismissed(Snackbar snackbar, int i5) {
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.BaseCallback
        public void onShown(Snackbar snackbar) {
        }
    }

    public @interface SeslSnackBarType {
    }

    static {
        int i5 = R.attr.snackbarButtonStyle;
        SNACKBAR_BUTTON_STYLE_ATTR = new int[]{i5};
        SNACKBAR_CONTENT_STYLE_ATTRS = new int[]{i5, R.attr.snackbarTextViewStyle};
        mIsCoordinatorLayoutParent = false;
    }

    private Snackbar(Context context, ViewGroup viewGroup, View view, ContentViewCallback contentViewCallback) {
        super(context, viewGroup, view, contentViewCallback);
        this.mType = -1;
        this.accessibilityManager = (AccessibilityManager) viewGroup.getContext().getSystemService("accessibility");
    }

    private static ViewGroup findSuitableParent(View view) {
        ViewGroup viewGroup = null;
        while (!(view instanceof CoordinatorLayout)) {
            if (view instanceof FrameLayout) {
                if (view.getId() == 16908290) {
                    return (ViewGroup) view;
                }
                viewGroup = (ViewGroup) view;
            }
            if (view != null) {
                Object parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
            if (view == null) {
                return viewGroup;
            }
        }
        mIsCoordinatorLayoutParent = true;
        return (ViewGroup) view;
    }

    private Button getActionView() {
        return getContentLayout().getActionView();
    }

    private SnackbarContentLayout getContentLayout() {
        return (SnackbarContentLayout) this.view.getChildAt(0);
    }

    private TextView getMessageView() {
        return getContentLayout().getMessageView();
    }

    @Deprecated
    public static boolean hasSnackbarButtonStyleAttr(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(SNACKBAR_BUTTON_STYLE_ATTR);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, -1);
        typedArrayObtainStyledAttributes.recycle();
        return resourceId != -1;
    }

    private static boolean hasSnackbarContentStyleAttrs(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(SNACKBAR_CONTENT_STYLE_ATTRS);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, -1);
        int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(1, -1);
        typedArrayObtainStyledAttributes.recycle();
        return (resourceId == -1 || resourceId2 == -1) ? false : true;
    }

    private boolean isShowButtonBackgroundEnabled() {
        ContentResolver contentResolver = getContext().getContentResolver();
        return contentResolver != null && Settings.System.getInt(contentResolver, "show_button_background", 0) == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setAction$0(View.OnClickListener onClickListener, View view) {
        onClickListener.onClick(view);
        dispatchDismiss(1);
    }

    public static Snackbar make(View view, CharSequence charSequence, int i5) {
        return makeInternal(null, view, charSequence, i5, -1);
    }

    private static Snackbar makeInternal(Context context, View view, CharSequence charSequence, int i5, @SeslSnackBarType int i7) {
        mIsCoordinatorLayoutParent = false;
        ViewGroup viewGroupFindSuitableParent = findSuitableParent(view);
        if (viewGroupFindSuitableParent == null) {
            throw new IllegalArgumentException("No suitable parent found from the given view. Please provide a valid view.");
        }
        if (context == null) {
            context = viewGroupFindSuitableParent.getContext();
        }
        SnackbarContentLayout snackbarContentLayout = (SnackbarContentLayout) LayoutInflater.from(context).inflate(i7 == 0 ? R.layout.sesl_layout_snackbar_suggest_include : hasSnackbarContentStyleAttrs(context) ? R.layout.mtrl_layout_snackbar_include : R.layout.design_layout_snackbar_include, viewGroupFindSuitableParent, false);
        snackbarContentLayout.setIsCoordinatorLayoutParent(mIsCoordinatorLayoutParent);
        Snackbar snackbar = new Snackbar(context, viewGroupFindSuitableParent, snackbarContentLayout, snackbarContentLayout);
        snackbar.setType(i7);
        snackbar.setText(charSequence);
        snackbar.setDuration(i5);
        if (i7 == 0) {
            snackbar.view.setAnimationMode(2);
            snackbarContentLayout.seslSetType(0);
        }
        return snackbar;
    }

    private Snackbar setType(@SeslSnackBarType int i5) {
        this.mType = i5;
        return this;
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar
    public void dismiss() {
        super.dismiss();
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar
    public int getDuration() {
        int duration = super.getDuration();
        if (duration == -2) {
            return -2;
        }
        return this.accessibilityManager.getRecommendedTimeoutMillis(duration, (this.hasAction ? 4 : 0) | 3);
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar
    public boolean isShown() {
        return super.isShown();
    }

    public Snackbar setAction(int i5, View.OnClickListener onClickListener) {
        return setAction(getContext().getText(i5), onClickListener);
    }

    public Snackbar setActionTextColor(ColorStateList colorStateList) {
        getActionView().setTextColor(colorStateList);
        return this;
    }

    public Snackbar setBackgroundTint(int i5) {
        return setBackgroundTintList(ColorStateList.valueOf(i5));
    }

    public Snackbar setBackgroundTintList(ColorStateList colorStateList) {
        this.view.setBackgroundTintList(colorStateList);
        return this;
    }

    public Snackbar setBackgroundTintMode(PorterDuff.Mode mode) {
        this.view.setBackgroundTintMode(mode);
        return this;
    }

    @Deprecated
    public Snackbar setCallback(Callback callback) {
        BaseTransientBottomBar.BaseCallback<Snackbar> baseCallback = this.callback;
        if (baseCallback != null) {
            removeCallback(baseCallback);
        }
        if (callback != null) {
            addCallback(callback);
        }
        this.callback = callback;
        return this;
    }

    public Snackbar setMaxInlineActionWidth(int i5) {
        getContentLayout().setMaxInlineActionWidth(i5);
        return this;
    }

    public Snackbar setText(CharSequence charSequence) {
        getMessageView().setText(charSequence);
        SeslTextViewHelperKt.checkMaxFontScale(getMessageView(), this.mType == 0 ? R.dimen.sesl_design_snackbar_suggest_text_size : R.dimen.design_snackbar_text_size, MaxFontScaleRatio.LARGE);
        return this;
    }

    public Snackbar setTextColor(ColorStateList colorStateList) {
        getMessageView().setTextColor(colorStateList);
        return this;
    }

    public Snackbar setTextMaxLines(int i5) {
        getMessageView().setMaxLines(i5);
        return this;
    }

    @Override // com.google.android.material.snackbar.BaseTransientBottomBar
    public void show() {
        super.show();
    }

    public static final class SnackbarLayout extends BaseTransientBottomBar.SnackbarBaseLayout {
        public SnackbarLayout(Context context) {
            super(context);
            setBackgroundColor(android.R.color.transparent);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.widget.FrameLayout, android.view.View
        public void onMeasure(int i5, int i7) {
            super.onMeasure(i5, i7);
            int childCount = getChildCount();
            int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            for (int i9 = 0; i9 < childCount; i9++) {
                View childAt = getChildAt(i9);
                if (childAt.getLayoutParams().width == -1) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(childAt.getMeasuredHeight(), 1073741824));
                }
                if (childAt.getLayoutParams().height == -2) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) childAt.getLayoutParams();
                    if (childAt.getHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin > (getMeasuredHeight() - getPaddingBottom()) - getPaddingTop()) {
                        super.onMeasure(i5, i7);
                    }
                }
            }
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setBackground(Drawable drawable) {
            super.setBackground(drawable);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setBackgroundDrawable(Drawable drawable) {
            super.setBackgroundDrawable(drawable);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setBackgroundTintList(ColorStateList colorStateList) {
            super.setBackgroundTintList(colorStateList);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setBackgroundTintMode(PorterDuff.Mode mode) {
            super.setBackgroundTintMode(mode);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
            super.setLayoutParams(layoutParams);
        }

        @Override // com.google.android.material.snackbar.BaseTransientBottomBar.SnackbarBaseLayout, android.view.View
        public /* bridge */ /* synthetic */ void setOnClickListener(View.OnClickListener onClickListener) {
            super.setOnClickListener(onClickListener);
        }

        public SnackbarLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            setBackgroundColor(android.R.color.transparent);
        }
    }

    public static Snackbar make(Context context, View view, CharSequence charSequence, int i5) {
        return makeInternal(context, view, charSequence, i5, -1);
    }

    public Snackbar setAction(CharSequence charSequence, View.OnClickListener onClickListener) {
        Button actionView = getActionView();
        getContentLayout().setBackground(this.view.getResources().getDrawable(this.mType == 0 ? R.drawable.sesl_snackbar_suggest_action_frame_mtrl : R.drawable.sem_snackbar_action_frame_mtrl));
        if (TextUtils.isEmpty(charSequence) || onClickListener == null) {
            actionView.setVisibility(8);
            actionView.setOnClickListener(null);
            this.hasAction = false;
        } else {
            this.hasAction = true;
            if (this.mType != 0) {
                actionView.setVisibility(0);
            }
            actionView.setText(charSequence);
            actionView.setOnClickListener(new b(17, this, onClickListener));
            SeslTextViewHelperKt.checkMaxFontScale(getMessageView(), this.mType == 0 ? R.dimen.sesl_design_snackbar_suggest_action_text_size : R.dimen.sesl_design_snackbar_action_text_size, MaxFontScaleRatio.LARGE);
            AbstractC0582a.G0(actionView, isShowButtonBackgroundEnabled());
        }
        return this;
    }

    public Snackbar setActionTextColor(int i5) {
        getActionView().setTextColor(i5);
        return this;
    }

    public Snackbar setTextColor(int i5) {
        getMessageView().setTextColor(i5);
        return this;
    }

    public static Snackbar make(View view, CharSequence charSequence, int i5, @SeslSnackBarType int i7) {
        return makeInternal(null, view, charSequence, i5, i7);
    }

    public static Snackbar make(Context context, View view, CharSequence charSequence, int i5, @SeslSnackBarType int i7) {
        return makeInternal(context, view, charSequence, i5, i7);
    }

    public static Snackbar make(View view, int i5, int i7) {
        return make(view, view.getResources().getText(i5), i7);
    }

    public Snackbar setText(int i5) {
        return setText(getContext().getText(i5));
    }
}
