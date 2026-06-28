package com.google.android.material.textfield;

import L.l;
import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.widget.C0154h0;
import androidx.appcompat.widget.S1;
import androidx.core.view.W;
import com.google.android.material.R;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"ViewConstructor"})
class StartCompoundLayout extends LinearLayout {
    private boolean hintExpanded;
    private CharSequence prefixText;
    private final TextView prefixTextView;
    private int startIconMinSize;
    private View.OnLongClickListener startIconOnLongClickListener;
    private ImageView.ScaleType startIconScaleType;
    private ColorStateList startIconTintList;
    private PorterDuff.Mode startIconTintMode;
    private final CheckableImageButton startIconView;
    private final TextInputLayout textInputLayout;

    public StartCompoundLayout(TextInputLayout textInputLayout, S1 s12) {
        super(textInputLayout.getContext());
        this.textInputLayout = textInputLayout;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388611));
        CheckableImageButton checkableImageButton = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(R.layout.design_text_input_start_icon, (ViewGroup) this, false);
        this.startIconView = checkableImageButton;
        IconHelper.setCompatRippleBackgroundIfNeeded(checkableImageButton);
        C0154h0 c0154h0 = new C0154h0(getContext(), null);
        this.prefixTextView = c0154h0;
        initStartIconView(s12);
        initPrefixTextView(s12);
        addView(checkableImageButton);
        addView(c0154h0);
    }

    private void initPrefixTextView(S1 s12) {
        this.prefixTextView.setVisibility(8);
        this.prefixTextView.setId(R.id.textinput_prefix_text);
        this.prefixTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        TextView textView = this.prefixTextView;
        WeakHashMap weakHashMap = W.f7199a;
        textView.setAccessibilityLiveRegion(1);
        setPrefixTextAppearance(s12.f6522b.getResourceId(R.styleable.TextInputLayout_prefixTextAppearance, 0));
        int i5 = R.styleable.TextInputLayout_prefixTextColor;
        TypedArray typedArray = s12.f6522b;
        if (typedArray.hasValue(i5)) {
            setPrefixTextColor(s12.a(i5));
        }
        setPrefixText(typedArray.getText(R.styleable.TextInputLayout_prefixText));
    }

    private void initStartIconView(S1 s12) {
        if (MaterialResources.isFontScaleAtLeast1_3(getContext())) {
            ((ViewGroup.MarginLayoutParams) this.startIconView.getLayoutParams()).setMarginEnd(0);
        }
        setStartIconOnClickListener(null);
        setStartIconOnLongClickListener(null);
        int i5 = R.styleable.TextInputLayout_startIconTint;
        if (s12.f6522b.hasValue(i5)) {
            this.startIconTintList = MaterialResources.getColorStateList(getContext(), s12, i5);
        }
        int i7 = R.styleable.TextInputLayout_startIconTintMode;
        TypedArray typedArray = s12.f6522b;
        if (typedArray.hasValue(i7)) {
            this.startIconTintMode = ViewUtils.parseTintMode(typedArray.getInt(i7, -1), null);
        }
        int i9 = R.styleable.TextInputLayout_startIconDrawable;
        if (typedArray.hasValue(i9)) {
            setStartIconDrawable(s12.b(i9));
            int i10 = R.styleable.TextInputLayout_startIconContentDescription;
            if (typedArray.hasValue(i10)) {
                setStartIconContentDescription(typedArray.getText(i10));
            }
            setStartIconCheckable(typedArray.getBoolean(R.styleable.TextInputLayout_startIconCheckable, true));
        }
        setStartIconMinSize(typedArray.getDimensionPixelSize(R.styleable.TextInputLayout_startIconMinSize, getResources().getDimensionPixelSize(R.dimen.mtrl_min_touch_target_size)));
        int i11 = R.styleable.TextInputLayout_startIconScaleType;
        if (typedArray.hasValue(i11)) {
            setStartIconScaleType(IconHelper.convertScaleType(typedArray.getInt(i11, -1)));
        }
    }

    private void updateVisibility() {
        int i5 = (this.prefixText == null || this.hintExpanded) ? 8 : 0;
        setVisibility((this.startIconView.getVisibility() == 0 || i5 == 0) ? 0 : 8);
        this.prefixTextView.setVisibility(i5);
        this.textInputLayout.updateDummyDrawables();
    }

    public CharSequence getPrefixText() {
        return this.prefixText;
    }

    public ColorStateList getPrefixTextColor() {
        return this.prefixTextView.getTextColors();
    }

    public int getPrefixTextStartOffset() {
        int marginEnd;
        if (isStartIconVisible()) {
            marginEnd = ((ViewGroup.MarginLayoutParams) this.startIconView.getLayoutParams()).getMarginEnd() + this.startIconView.getMeasuredWidth();
        } else {
            marginEnd = 0;
        }
        WeakHashMap weakHashMap = W.f7199a;
        return this.prefixTextView.getPaddingStart() + getPaddingStart() + marginEnd;
    }

    public TextView getPrefixTextView() {
        return this.prefixTextView;
    }

    public CharSequence getStartIconContentDescription() {
        return this.startIconView.getContentDescription();
    }

    public Drawable getStartIconDrawable() {
        return this.startIconView.getDrawable();
    }

    public int getStartIconMinSize() {
        return this.startIconMinSize;
    }

    public ImageView.ScaleType getStartIconScaleType() {
        return this.startIconScaleType;
    }

    public boolean isStartIconCheckable() {
        return this.startIconView.isCheckable();
    }

    public boolean isStartIconVisible() {
        return this.startIconView.getVisibility() == 0;
    }

    public void onHintStateChanged(boolean z9) {
        this.hintExpanded = z9;
        updateVisibility();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i5, int i7) {
        super.onMeasure(i5, i7);
        updatePrefixTextViewPadding();
    }

    public void refreshStartIconDrawableState() {
        IconHelper.refreshIconDrawableState(this.textInputLayout, this.startIconView, this.startIconTintList);
    }

    public void setPrefixText(CharSequence charSequence) {
        this.prefixText = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.prefixTextView.setText(charSequence);
        updateVisibility();
    }

    public void setPrefixTextAppearance(int i5) {
        this.prefixTextView.setTextAppearance(i5);
    }

    public void setPrefixTextColor(ColorStateList colorStateList) {
        this.prefixTextView.setTextColor(colorStateList);
    }

    public void setStartIconCheckable(boolean z9) {
        this.startIconView.setCheckable(z9);
    }

    public void setStartIconContentDescription(CharSequence charSequence) {
        if (getStartIconContentDescription() != charSequence) {
            this.startIconView.setContentDescription(charSequence);
        }
    }

    public void setStartIconDrawable(Drawable drawable) {
        this.startIconView.setImageDrawable(drawable);
        if (drawable != null) {
            IconHelper.applyIconTint(this.textInputLayout, this.startIconView, this.startIconTintList, this.startIconTintMode);
            setStartIconVisible(true);
            refreshStartIconDrawableState();
        } else {
            setStartIconVisible(false);
            setStartIconOnClickListener(null);
            setStartIconOnLongClickListener(null);
            setStartIconContentDescription(null);
        }
    }

    public void setStartIconMinSize(int i5) {
        if (i5 < 0) {
            throw new IllegalArgumentException("startIconSize cannot be less than 0");
        }
        if (i5 != this.startIconMinSize) {
            this.startIconMinSize = i5;
            IconHelper.setIconMinSize(this.startIconView, i5);
        }
    }

    public void setStartIconOnClickListener(View.OnClickListener onClickListener) {
        IconHelper.setIconOnClickListener(this.startIconView, onClickListener, this.startIconOnLongClickListener);
    }

    public void setStartIconOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.startIconOnLongClickListener = onLongClickListener;
        IconHelper.setIconOnLongClickListener(this.startIconView, onLongClickListener);
    }

    public void setStartIconScaleType(ImageView.ScaleType scaleType) {
        this.startIconScaleType = scaleType;
        IconHelper.setIconScaleType(this.startIconView, scaleType);
    }

    public void setStartIconTintList(ColorStateList colorStateList) {
        if (this.startIconTintList != colorStateList) {
            this.startIconTintList = colorStateList;
            IconHelper.applyIconTint(this.textInputLayout, this.startIconView, colorStateList, this.startIconTintMode);
        }
    }

    public void setStartIconTintMode(PorterDuff.Mode mode) {
        if (this.startIconTintMode != mode) {
            this.startIconTintMode = mode;
            IconHelper.applyIconTint(this.textInputLayout, this.startIconView, this.startIconTintList, mode);
        }
    }

    public void setStartIconVisible(boolean z9) {
        if (isStartIconVisible() != z9) {
            this.startIconView.setVisibility(z9 ? 0 : 8);
            updatePrefixTextViewPadding();
            updateVisibility();
        }
    }

    public void setupAccessibilityNodeInfo(l lVar) {
        if (this.prefixTextView.getVisibility() != 0) {
            lVar.f1793a.setTraversalAfter(this.startIconView);
        } else {
            lVar.f1793a.setLabelFor(this.prefixTextView);
            lVar.f1793a.setTraversalAfter(this.prefixTextView);
        }
    }

    public void updatePrefixTextViewPadding() {
        int paddingStart;
        EditText editText = this.textInputLayout.editText;
        if (editText == null) {
            return;
        }
        if (isStartIconVisible()) {
            paddingStart = 0;
        } else {
            WeakHashMap weakHashMap = W.f7199a;
            paddingStart = editText.getPaddingStart();
        }
        TextView textView = this.prefixTextView;
        int compoundPaddingTop = editText.getCompoundPaddingTop();
        int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.material_input_text_to_prefix_suffix_padding);
        int compoundPaddingBottom = editText.getCompoundPaddingBottom();
        WeakHashMap weakHashMap2 = W.f7199a;
        textView.setPaddingRelative(paddingStart, compoundPaddingTop, dimensionPixelSize, compoundPaddingBottom);
    }
}
