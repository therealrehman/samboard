package com.google.android.material.chip;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.material.R;
import com.google.android.material.resources.TextAppearance;

/* JADX INFO: loaded from: classes.dex */
public class SeslChip extends Chip {
    private int mChipMinWidth;

    public SeslChip(Context context) {
        this(context, null);
    }

    private void setChipIconAlpha(int i5) {
        Drawable chipIcon = getChipIcon();
        if (chipIcon == null) {
            return;
        }
        chipIcon.setAlpha(i5);
    }

    private void setCloseIconAlpha(int i5) {
        Drawable closeIcon = getCloseIcon();
        if (closeIcon == null) {
            return;
        }
        closeIcon.setAlpha(i5);
    }

    private void setTextAlpha(int i5) {
        ColorStateList textColor;
        ChipDrawable chipDrawable = (ChipDrawable) getChipDrawable();
        TextAppearance textAppearance = chipDrawable.getTextAppearance();
        if (textAppearance == null || (textColor = textAppearance.getTextColor()) == null) {
            return;
        }
        chipDrawable.setTextColor(textColor.withAlpha(i5));
    }

    private void updateLayoutParamsWidth() {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = getChipDrawable().getIntrinsicWidth() + ((int) getChipEndPadding());
            setLayoutParams(layoutParams);
        }
    }

    @Override // com.google.android.material.chip.Chip
    public Drawable getBackgroundDrawable() {
        return getChipDrawable();
    }

    public int getChipMinWidth() {
        return this.mChipMinWidth;
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        return ((ChipDrawable) getChipDrawable()).getText();
    }

    public void refreshChip() {
        requestLayout();
    }

    public void setBackgroundAlpha(int i5) {
        getChipDrawable().setAlpha(i5);
    }

    @Override // com.google.android.material.chip.Chip
    public void setChipIconVisible(boolean z9) {
        super.setChipIconVisible(z9);
        updateLayoutParamsWidth();
    }

    public void setChipMinWidth(int i5) {
        this.mChipMinWidth = i5;
    }

    @Override // com.google.android.material.chip.Chip
    public void setCloseIconVisible(boolean z9) {
        super.setCloseIconVisible(z9);
        updateLayoutParamsWidth();
    }

    public void setInternalsAlpha(int i5) {
        setTextAlpha(i5);
        setCloseIconAlpha(i5);
        setChipIconAlpha(i5);
    }

    @Override // android.widget.TextView, android.view.View
    public void setSelected(boolean z9) {
        super.setSelected(z9);
        updateLayoutParamsWidth();
    }

    public void setSeslFullText(boolean z9) {
        ((ChipDrawable) getChipDrawable()).setSeslFullText(z9);
    }

    @Override // com.google.android.material.chip.Chip, android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        ChipDrawable chipDrawable = (ChipDrawable) getChipDrawable();
        if (chipDrawable == null) {
            super.setText(charSequence, bufferType);
        } else {
            chipDrawable.setText(charSequence);
            updateLayoutParamsWidth();
        }
    }

    public SeslChip(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.chipStyle);
    }

    public SeslChip(Context context, AttributeSet attributeSet, int i5) {
        super(new ContextThemeWrapper(context, R.style.SeslPeoplePickerStyle), attributeSet, i5);
        ChipDrawable chipDrawable = (ChipDrawable) getChipDrawable();
        if (chipDrawable != null) {
            chipDrawable.setShouldDrawText(true);
            chipDrawable.setCloseIconTint(null);
            chipDrawable.setChipIconTint(null);
        }
    }

    @Override // com.google.android.material.chip.Chip
    public void setChipIconVisible(int i5) {
        setChipIconVisible(getContext().getResources().getBoolean(i5));
    }

    @Override // com.google.android.material.chip.Chip
    public void setCloseIconVisible(int i5) {
        setCloseIconVisible(getContext().getResources().getBoolean(i5));
    }
}
