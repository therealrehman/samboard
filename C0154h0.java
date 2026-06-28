package androidx.appcompat.widget;

import android.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import androidx.appcompat.graphics.drawable.SeslRecoilDrawable;
import h6.AbstractC0582a;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: renamed from: androidx.appcompat.widget.h0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0154h0 extends TextView {
    private boolean isNeedToSkipRefreshDrawable;
    private final C0188t mBackgroundTintHelper;
    private D mEmojiTextViewHelper;
    private boolean mIsSetTypefaceProcessing;
    private Future<J.e> mPrecomputedTextFuture;
    private InterfaceC0151g0 mSuperCaller;
    private final Z mTextClassifierHelper;
    private final C0148f0 mTextHelper;

    public C0154h0(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.textViewStyle);
    }

    private D getEmojiTextViewHelper() {
        if (this.mEmojiTextViewHelper == null) {
            this.mEmojiTextViewHelper = new D(this);
        }
        return this.mEmojiTextViewHelper;
    }

    public final void c() {
        Future<J.e> future = this.mPrecomputedTextFuture;
        if (future == null) {
            return;
        }
        try {
            this.mPrecomputedTextFuture = null;
            A8.l.z(future.get());
            throw null;
        } catch (InterruptedException | ExecutionException unused) {
        }
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

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return getPaddingTop() - getPaint().getFontMetricsInt().top;
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return getPaddingBottom() + getPaint().getFontMetricsInt().bottom;
    }

    public InterfaceC0151g0 getSuperCaller() {
        if (this.mSuperCaller == null) {
            this.mSuperCaller = new I(this);
        }
        return this.mSuperCaller;
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

    @Override // android.widget.TextView
    public CharSequence getText() {
        c();
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        return super.getTextClassifier();
    }

    public J.d getTextMetricsParamsCompat() {
        return new J.d(P.s.c(this));
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

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        this.mTextHelper.getClass();
        com.bumptech.glide.c.J(inputConnectionOnCreateInputConnection, editorInfo, this);
        return inputConnectionOnCreateInputConnection;
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

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i5, int i7) {
        c();
        super.onMeasure(i5, i7);
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
    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        C0148f0 c0148f0 = this.mTextHelper;
        if (c0148f0 != null) {
            c0148f0.b();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        C0148f0 c0148f0 = this.mTextHelper;
        if (c0148f0 != null) {
            c0148f0.b();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        C0148f0 c0148f0 = this.mTextHelper;
        if (c0148f0 != null) {
            c0148f0.b();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        C0148f0 c0148f0 = this.mTextHelper;
        if (c0148f0 != null) {
            c0148f0.b();
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

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i5) {
        getSuperCaller().b(i5);
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i5) {
        getSuperCaller().a(i5);
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i5) {
        AbstractC0582a.n(i5);
        if (i5 != getPaint().getFontMetricsInt(null)) {
            setLineSpacing(i5 - r0, 1.0f);
        }
    }

    public void setPrecomputedText(J.e eVar) {
        throw null;
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
    public void setTextClassifier(TextClassifier textClassifier) {
        super.setTextClassifier(textClassifier);
    }

    public void setTextFuture(Future<J.e> future) {
        this.mPrecomputedTextFuture = future;
        if (future != null) {
            requestLayout();
        }
    }

    public void setTextMetricsParamsCompat(J.d dVar) {
        TextDirectionHeuristic textDirectionHeuristic;
        TextDirectionHeuristic textDirectionHeuristic2 = dVar.f1511b;
        TextDirectionHeuristic textDirectionHeuristic3 = TextDirectionHeuristics.FIRSTSTRONG_RTL;
        int i5 = 1;
        if (textDirectionHeuristic2 != textDirectionHeuristic3 && textDirectionHeuristic2 != (textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR)) {
            if (textDirectionHeuristic2 == TextDirectionHeuristics.ANYRTL_LTR) {
                i5 = 2;
            } else if (textDirectionHeuristic2 == TextDirectionHeuristics.LTR) {
                i5 = 3;
            } else if (textDirectionHeuristic2 == TextDirectionHeuristics.RTL) {
                i5 = 4;
            } else if (textDirectionHeuristic2 == TextDirectionHeuristics.LOCALE) {
                i5 = 5;
            } else if (textDirectionHeuristic2 == textDirectionHeuristic) {
                i5 = 6;
            } else if (textDirectionHeuristic2 == textDirectionHeuristic3) {
                i5 = 7;
            }
        }
        setTextDirection(i5);
        getPaint().set(dVar.f1510a);
        P.q.e(this, dVar.f1512c);
        P.q.h(this, dVar.f1513d);
    }

    @Override // android.widget.TextView
    public void setTextSize(int i5, float f2) {
        Method method = h2.f6719a;
        super.setTextSize(i5, f2);
    }

    @Override // android.widget.TextView
    public void setTypeface(Typeface typeface, int i5) {
        Typeface typefaceCreate;
        if (this.mIsSetTypefaceProcessing) {
            return;
        }
        if (typeface == null || i5 <= 0) {
            typefaceCreate = null;
        } else {
            Context context = getContext();
            B1.b bVar = D.j.f414a;
            if (context == null) {
                throw new IllegalArgumentException("Context cannot be null");
            }
            typefaceCreate = Typeface.create(typeface, i5);
        }
        this.mIsSetTypefaceProcessing = true;
        if (typefaceCreate != null) {
            typeface = typefaceCreate;
        }
        try {
            super.setTypeface(typeface, i5);
        } finally {
            this.mIsSetTypefaceProcessing = false;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0154h0(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5);
        Q1.a(context);
        this.mIsSetTypefaceProcessing = false;
        this.mSuperCaller = null;
        P1.a(this, getContext());
        C0188t c0188t = new C0188t(this);
        this.mBackgroundTintHelper = c0188t;
        c0188t.d(attributeSet, i5);
        C0148f0 c0148f0 = new C0148f0(this);
        this.mTextHelper = c0148f0;
        c0148f0.f(attributeSet, i5);
        c0148f0.b();
        this.mTextClassifierHelper = new Z();
        getEmojiTextViewHelper().c(attributeSet, i5);
    }

    public void seslSetButtonShapeEnabled(boolean z9, int i5) {
        AbstractC0582a.H0(this, z9, i5);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i5, int i7, int i9, int i10) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i5 != 0 ? android.support.v4.media.session.f.y(context, i5) : null, i7 != 0 ? android.support.v4.media.session.f.y(context, i7) : null, i9 != 0 ? android.support.v4.media.session.f.y(context, i9) : null, i10 != 0 ? android.support.v4.media.session.f.y(context, i10) : null);
        C0148f0 c0148f0 = this.mTextHelper;
        if (c0148f0 != null) {
            c0148f0.b();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i5, int i7, int i9, int i10) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i5 != 0 ? android.support.v4.media.session.f.y(context, i5) : null, i7 != 0 ? android.support.v4.media.session.f.y(context, i7) : null, i9 != 0 ? android.support.v4.media.session.f.y(context, i9) : null, i10 != 0 ? android.support.v4.media.session.f.y(context, i10) : null);
        C0148f0 c0148f0 = this.mTextHelper;
        if (c0148f0 != null) {
            c0148f0.b();
        }
    }
}
