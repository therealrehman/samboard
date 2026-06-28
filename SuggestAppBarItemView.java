package com.google.android.material.appbar.model.view;

import B.b;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.bumptech.glide.d;
import com.google.android.material.R;
import com.google.android.material.util.MaxFontScaleRatio;
import com.google.android.material.util.SeslTextViewHelperKt;
import f6.AbstractC0527a;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import s6.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0017\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u000e\u001a\u00020\rH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u000f\u0010\u0010\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0010\u0010\u000fJ\u000f\u0010\u0011\u001a\u00020\nH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u001d\u0010\u0017\u001a\u00020\n2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0015H\u0004¢\u0006\u0004\b\u0017\u0010\u0018R$\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001f¨\u0006 "}, d2 = {"Lcom/google/android/material/appbar/model/view/SuggestAppBarItemView;", "Lcom/google/android/material/appbar/model/view/SuggestAppBarView;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Landroid/widget/Button;", "button", "LT6/p;", "updateButton", "(Landroid/widget/Button;)V", "", "getSuggestButtonTextColor", "()I", "getButtonTextColor", "inflate", "()V", "updateResource", "(Landroid/content/Context;)V", "", "buttons", "updateButtons", "(Ljava/util/List;)V", "Landroid/view/ViewGroup;", "rootView", "Landroid/view/ViewGroup;", "getRootView", "()Landroid/view/ViewGroup;", "setRootView", "(Landroid/view/ViewGroup;)V", "material_release"}, k = 1, mv = {1, 8, 0})
public class SuggestAppBarItemView extends SuggestAppBarView {
    private ViewGroup rootView;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SuggestAppBarItemView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        j.f(context, "context");
    }

    private final int getButtonTextColor() {
        Context context = getContext();
        j.e(context, "context");
        int i5 = R.color.sesl_button_text_color;
        int i7 = R.color.sesl_button_text_color_dark;
        int i9 = R.color.sesl_button_text_color_dark_for_theme;
        if (c.L(context)) {
            if (!c.O(context)) {
                i5 = i7;
            }
        } else if (!c.O(context)) {
            i5 = i9;
        }
        return b.a(context, i5);
    }

    private final int getSuggestButtonTextColor() {
        Context context = getContext();
        j.e(context, "context");
        int i5 = R.color.sesl_suggest_button_text_color;
        int i7 = R.color.sesl_suggest_button_text_color_dark;
        int i9 = R.color.sesl_suggest_button_text_color_dark_for_theme;
        if (c.L(context)) {
            if (!c.O(context)) {
                i5 = i7;
            }
        } else if (!c.O(context)) {
            i5 = i9;
        }
        return b.a(context, i5);
    }

    private final void updateButton(Button button) {
        button.setTextColor(getButtonTextColor());
        SeslTextViewHelperKt.checkMaxFontScale(button, R.dimen.sesl_appbar_button_text_size, MaxFontScaleRatio.MEDIUM);
    }

    @Override // android.view.View
    public final ViewGroup getRootView() {
        return this.rootView;
    }

    @Override // com.google.android.material.appbar.model.view.SuggestAppBarView, com.google.android.material.appbar.model.view.AppBarView
    public void inflate() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.sesl_app_bar_suggest_in_viewpager, (ViewGroup) this, false);
        ImageButton imageButton = null;
        ViewGroup viewGroup = viewInflate instanceof ViewGroup ? (ViewGroup) viewInflate : null;
        if (viewGroup == null) {
            return;
        }
        ViewGroup viewGroup2 = (ViewGroup) viewGroup.findViewById(R.id.sesl_appbar_suggest_in_viewpager);
        viewGroup2.setBackgroundResource(R.drawable.sesl_viewpager_item_background);
        this.rootView = viewGroup2;
        setTitleView((TextView) viewGroup.findViewById(R.id.suggest_app_bar_title));
        ImageButton imageButton2 = (ImageButton) viewGroup.findViewById(R.id.suggest_app_bar_close);
        if (imageButton2 != null) {
            d.C(AbstractC0527a.r(), imageButton2);
            imageButton = imageButton2;
        }
        setClose(imageButton);
        setBottomLayout((ViewGroup) viewGroup.findViewById(R.id.suggest_app_bar_bottom_layout));
        Context context = getContext();
        j.e(context, "context");
        updateResource(context);
        addView(viewGroup);
    }

    public final void setRootView(ViewGroup viewGroup) {
        this.rootView = viewGroup;
    }

    public final void updateButtons(List<? extends Button> buttons) {
        j.f(buttons, "buttons");
        Iterator<T> it = buttons.iterator();
        while (it.hasNext()) {
            updateButton((Button) it.next());
        }
    }

    @Override // com.google.android.material.appbar.model.view.SuggestAppBarView, com.google.android.material.appbar.model.view.AppBarView
    public void updateResource(Context context) {
        j.f(context, "context");
        super.updateResource(context);
        boolean zO = c.O(context);
        ViewGroup viewGroup = this.rootView;
        if (viewGroup != null) {
            viewGroup.setBackgroundTintList(ColorStateList.valueOf(b.a(context, zO ? R.color.sesl_viewpager_item_background : R.color.sesl_viewpager_item_background_dark)));
        }
        TextView titleView = getTitleView();
        if (titleView != null) {
            SeslTextViewHelperKt.checkMaxFontScale(titleView, R.dimen.sesl_appbar_suggest_title_text_size, MaxFontScaleRatio.SMALL);
        }
        updateButtons(getButtons());
    }

    public /* synthetic */ SuggestAppBarItemView(Context context, AttributeSet attributeSet, int i5, e eVar) {
        this(context, (i5 & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SuggestAppBarItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j.f(context, "context");
        inflate();
    }
}
