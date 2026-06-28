package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import androidx.appcompat.graphics.drawable.SeslRecoilDrawable;
import h6.AbstractC0582a;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: androidx.appcompat.widget.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0191u extends Button {
    private boolean isNeedToSkipRefreshDrawable;
    private D mAppCompatEmojiTextHelper;
    private final C0188t mBackgroundTintHelper;
    private final C0148f0 mTextHelper;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0191u(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5);
        Q1.a(context);
        P1.a(this, getContext());
        C0188t c0188t = new C0188t(this);
        this.mBackgroundTintHelper = c0188t;
        c0188t.d(attributeSet, i5);
        C0148f0 c0148f0 = new C0148f0(this);
        this.mTextHelper = c0148f0;
        c0148f0.f(attributeSet, i5);
        c0148f0.b();
        getEmojiTextViewHelper().c(attributeSet, i5);
    }

    private D getEmojiTextViewHelper() {
        if (this.mAppCompatEmojiTextHelper == null) {
            this.mAppCompatEmojiTextHelper = new D(this);
        }
        return this.mAppCompatEmojiTextHelper;
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        C0188t c0188t = this.mBackgroundTintHelper;
        if (c0188t != null) {
            c0188t.a();
        }
        C0148f0 c0148f0 = this.mTextHelper;
        if (c0148f0 != null) {
            c0148f0.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        Method method = h2.f6719a;
        return super.getAutoSizeMaxTextSize();
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        Method method = h2.f6719a;
        return super.getAutoSizeMinTextSize();
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        Method method = h2.f6719a;
        return super.getAutoSizeStepGranularity();
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        Method method = h2.f6719a;
        return super.getAutoSizeTextAvailableSizes();
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        Method method = h2.f6719a;
        return super.getAutoSizeTextType() == 1 ? 1 : 0;
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return super.getCustomSelectionActionModeCallback();
    }

    public ColorStateList getSupportBackgroundTintList() {
        C0188t c0188t = this.mBackgroundTintHelper;
        if (c0188t != null) {
            return c0188t.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C0188t c0188t = this.mBackgroundTintHelper;
        if (c0188t != null) {
            return c0188t.c();
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.mTextHelper.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.mTextHelper.e();
    }

    public boolean isEmojiCompatEnabled() {
        return getEmojiTextViewHelper().b();
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getBackground() instanceof SeslRecoilDrawable) {
            this.isNeedToSkipRefreshDrawable = true;
        }
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(Button.class.getName());
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(Button.class.getName());
    }

    @Override // android.widget.TextView, android.view.View
    public void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        super.onLayout(z9, i5, i7, i9, i10);
        C0148f0 c0148f0 = this.mTextHelper;
        if (c0148f0 != null) {
            c0148f0.getClass();
            Method method = h2.f6719a;
        }
    }

    @Override // android.widget.TextView
    public void onTextChanged(CharSequence charSequence, int i5, int i7, int i9) {
        super.onTextChanged(charSequence, i5, i7, i9);
        if (this.mTextHelper != null) {
            Method method = h2.f6719a;
        }
    }

    @Override // android.view.View
    public void refreshDrawableState() {
        super.refreshDrawableState();
        if (!this.isNeedToSkipRefreshDrawable || getStateListAnimator() == null) {
            return;
        }
        getStateListAnimator().jumpToCurrentState();
        this.isNeedToSkipRefreshDrawable = false;
    }

    public void seslSetButtonShapeEnabled(boolean z9) {
        AbstractC0582a.G0(this, z9);
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z9) {
        super.setAllCaps(z9);
        getEmojiTextViewHelper().d(z9);
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithConfiguration(int i5, int i7, int i9, int i10) {
        Method method = h2.f6719a;
        super.setAutoSizeTextTypeUniformWithConfiguration(i5, i7, i9, i10);
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i5) {
        Method method = h2.f6719a;
        super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i5);
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i5) {
        Method method = h2.f6719a;
        super.setAutoSizeTextTypeWithDefaults(i5);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C0188t c0188t = this.mBackgroundTintHelper;
        if (c0188t != null) {
            c0188t.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i5) {
        super.setBackgroundResource(i5);
        C0188t c0188t = this.mBackgroundTintHelper;
        if (c0188t != null) {
            c0188t.f(i5);
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(callback);
    }

    public void setEmojiCompatEnabled(boolean z9) {
        getEmojiTextViewHelper().e(z9);
    }

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(getEmojiTextViewHelper().a(inputFilterArr));
    }

    public void setSupportAllCaps(boolean z9) {
        C0148f0 c0148f0 = this.mTextHelper;
        if (c0148f0 != null) {
            c0148f0.f6695a.setAllCaps(z9);
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        C0188t c0188t = this.mBackgroundTintHelper;
        if (c0188t != null) {
            c0188t.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        C0188t c0188t = this.mBackgroundTintHelper;
        if (c0188t != null) {
            c0188t.i(mode);
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        this.mTextHelper.h(colorStateList);
        this.mTextHelper.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        this.mTextHelper.i(mode);
        this.mTextHelper.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i5) {
        super.setTextAppearance(context, i5);
        C0148f0 c0148f0 = this.mTextHelper;
        if (c0148f0 != null) {
            c0148f0.g(context, i5);
        }
    }

    @Override // android.widget.TextView
    public void setTextSize(int i5, float f2) {
        Method method = h2.f6719a;
        super.setTextSize(i5, f2);
    }

    public void seslSetButtonShapeEnabled(boolean z9, int i5) {
        AbstractC0582a.H0(this, z9, i5);
    }
}
