package com.google.android.material.tabs;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.f;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.material.R;

/* JADX INFO: loaded from: classes.dex */
public class TabItem extends View {
    public final int customLayout;
    public final Drawable icon;
    public CharSequence mSubText;
    public final CharSequence text;

    public TabItem(Context context) {
        this(context, null);
    }

    public TabItem(Context context, AttributeSet attributeSet) {
        int resourceId;
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TabItem);
        this.text = typedArrayObtainStyledAttributes.getText(R.styleable.TabItem_android_text);
        int i5 = R.styleable.TabItem_android_icon;
        this.icon = (!typedArrayObtainStyledAttributes.hasValue(i5) || (resourceId = typedArrayObtainStyledAttributes.getResourceId(i5, 0)) == 0) ? typedArrayObtainStyledAttributes.getDrawable(i5) : f.y(context, resourceId);
        this.customLayout = typedArrayObtainStyledAttributes.getResourceId(R.styleable.TabItem_android_layout, 0);
        typedArrayObtainStyledAttributes.recycle();
    }
}
