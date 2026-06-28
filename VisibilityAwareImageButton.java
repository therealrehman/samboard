package com.google.android.material.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"AppCompatCustomView"})
public class VisibilityAwareImageButton extends ImageButton {
    private int userSetVisibility;

    public VisibilityAwareImageButton(Context context) {
        this(context, null);
    }

    public final int getUserSetVisibility() {
        return this.userSetVisibility;
    }

    public final void internalSetVisibility(int i5, boolean z9) {
        super.setVisibility(i5);
        if (z9) {
            this.userSetVisibility = i5;
        }
    }

    @Override // android.widget.ImageView, android.view.View
    public void setVisibility(int i5) {
        internalSetVisibility(i5, true);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5);
        this.userSetVisibility = getVisibility();
    }
}
