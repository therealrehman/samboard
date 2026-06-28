package androidx.appcompat.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import e.AbstractC0478a;

/* JADX INFO: loaded from: classes.dex */
public class SeslDropDownItemTextView extends AbstractC0184r1 {
    public SeslDropDownItemTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.textViewStyle, 0);
        this.h = null;
        this.f6843i = null;
        this.f6844j = false;
        this.f6845k = false;
        this.f6848n = 8388611;
        int[] iArr = AbstractC0478a.f10566l;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr, R.attr.textViewStyle, 0);
        try {
            saveAttributeDataForStyleable(context, iArr, attributeSet, typedArrayObtainStyledAttributes, R.attr.textViewStyle, 0);
            this.f6850q = context.getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_checked_spinner_padding_end);
            Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(1);
            if (drawable != null) {
                setCheckMarkDrawable(drawable);
            }
            if (typedArrayObtainStyledAttributes.hasValue(3)) {
                this.f6843i = AbstractC0183r0.b(typedArrayObtainStyledAttributes.getInt(3, -1), this.f6843i);
                this.f6845k = true;
            }
            if (typedArrayObtainStyledAttributes.hasValue(2)) {
                this.h = typedArrayObtainStyledAttributes.getColorStateList(2);
                this.f6844j = true;
            }
            this.f6848n = typedArrayObtainStyledAttributes.getInt(5, 8388611);
            setChecked(typedArrayObtainStyledAttributes.getBoolean(0, false));
            typedArrayObtainStyledAttributes.recycle();
            this.f6849p = context.getResources().getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_checked_text_padding);
            a();
            Resources resources = context.getResources();
            setMaxWidth(resources.getDisplayMetrics().widthPixels - (resources.getDimensionPixelSize(com.samsung.android.keyscafe.R.dimen.sesl_menu_popup_offset_horizontal) * 2));
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    @Override // androidx.appcompat.widget.AbstractC0184r1, android.widget.Checkable
    public void setChecked(boolean z9) {
        Context context;
        super.setChecked(z9);
        if (Build.VERSION.SDK_INT >= 34) {
            setTypeface(Typeface.create(Typeface.create("sec", 0), z9 ? 600 : 400, false));
        } else {
            setTypeface(Typeface.create("sec-roboto-light", z9 ? 1 : 0));
        }
        if (z9 && (context = getContext()) != null && getCurrentTextColor() == -65281) {
            Log.w("SeslDropDownItemTextView", "text color reload!");
            ColorStateList colorStateListA = C.s.a(context.getResources(), s6.c.O(context) ? com.samsung.android.keyscafe.R.color.sesl_spinner_dropdown_text_color_light : com.samsung.android.keyscafe.R.color.sesl_spinner_dropdown_text_color_dark, context.getTheme());
            if (colorStateListA != null) {
                setTextColor(colorStateListA);
            } else {
                Log.w("SeslDropDownItemTextView", "Didn't set SeslDropDownItemTextView text color!!");
            }
        }
    }
}
