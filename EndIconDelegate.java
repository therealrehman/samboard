package com.google.android.material.textfield;

import L.l;
import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;
import com.google.android.material.internal.CheckableImageButton;

/* JADX INFO: loaded from: classes.dex */
abstract class EndIconDelegate {
    final Context context;
    final CheckableImageButton endIconView;
    final EndCompoundLayout endLayout;
    final TextInputLayout textInputLayout;

    public EndIconDelegate(EndCompoundLayout endCompoundLayout) {
        this.textInputLayout = endCompoundLayout.textInputLayout;
        this.endLayout = endCompoundLayout;
        this.context = endCompoundLayout.getContext();
        this.endIconView = endCompoundLayout.getEndIconView();
    }

    public void afterEditTextChanged(Editable editable) {
    }

    public void beforeEditTextChanged(CharSequence charSequence, int i5, int i7, int i9) {
    }

    public int getIconContentDescriptionResId() {
        return 0;
    }

    public int getIconDrawableResId() {
        return 0;
    }

    public View.OnFocusChangeListener getOnEditTextFocusChangeListener() {
        return null;
    }

    public View.OnClickListener getOnIconClickListener() {
        return null;
    }

    public View.OnFocusChangeListener getOnIconViewFocusChangeListener() {
        return null;
    }

    public L.b getTouchExplorationStateChangeListener() {
        return null;
    }

    public boolean isBoxBackgroundModeSupported(int i5) {
        return true;
    }

    public boolean isIconActivable() {
        return false;
    }

    public boolean isIconActivated() {
        return false;
    }

    public boolean isIconCheckable() {
        return false;
    }

    public boolean isIconChecked() {
        return false;
    }

    public void onEditTextAttached(EditText editText) {
    }

    public void onInitializeAccessibilityNodeInfo(View view, l lVar) {
    }

    public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
    }

    public void onSuffixVisibilityChanged(boolean z9) {
    }

    public final void refreshIconState() {
        this.endLayout.refreshIconState(false);
    }

    public void setUp() {
    }

    public boolean shouldTintIconOnError() {
        return false;
    }

    public void tearDown() {
    }
}
