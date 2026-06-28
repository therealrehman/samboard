package com.google.android.material.appbar.model.view;

import B.a;
import C5.b;
import U6.o;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.d2;
import com.bumptech.glide.d;
import com.google.android.material.R;
import com.google.android.material.appbar.model.AppBarModel;
import com.google.android.material.appbar.model.ButtonListModel;
import com.google.android.material.appbar.model.ButtonModel;
import com.google.android.material.appbar.model.ButtonStyle;
import com.google.android.material.appbar.model.SuggestAppBarModel;
import com.samsung.android.keyscafe.honeytea.utils.ThemeParkConstants;
import f6.AbstractC0527a;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import s6.c;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0005\b\u0017\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\u000fH\u0016¢\u0006\u0004\b\u0017\u0010\u0011J\u0017\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u001d\u0010\u001c\u001a\u00020\u000f2\u000e\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010 \u001a\u00020\u000f2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e¢\u0006\u0004\b \u0010!J\u0017\u0010$\u001a\u00020\u000f2\b\u0010#\u001a\u0004\u0018\u00010\"¢\u0006\u0004\b$\u0010%J\u0015\u0010(\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020&¢\u0006\u0004\b(\u0010)R\u001e\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u001a8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u001b\u0010*R$\u0010,\u001a\u0004\u0018\u00010+8\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R$\u00103\u001a\u0004\u0018\u0001028\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b3\u00104\u001a\u0004\b5\u00106\"\u0004\b7\u00108R$\u0010:\u001a\u0004\u0018\u0001098\u0004@\u0004X\u0084\u000e¢\u0006\u0012\n\u0004\b:\u0010;\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001d\u0010A\u001a\b\u0012\u0004\u0012\u00020\f0@8\u0006¢\u0006\f\n\u0004\bA\u0010B\u001a\u0004\bC\u0010D¨\u0006E"}, d2 = {"Lcom/google/android/material/appbar/model/view/SuggestAppBarView;", "Lcom/google/android/material/appbar/model/view/AppBarView;", "Landroid/content/Context;", "context", "Landroid/util/AttributeSet;", "attrs", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Lcom/google/android/material/appbar/model/ButtonModel;", "buttonModel", "", "style", "Landroid/widget/Button;", "generateButton", "(Lcom/google/android/material/appbar/model/ButtonModel;I)Landroid/widget/Button;", "LT6/p;", "addMargin", "()V", "getAppBarSuggestTitleColor", "(Landroid/content/Context;)I", "Landroid/graphics/drawable/Drawable;", "getCloseDrawable", "(Landroid/content/Context;)Landroid/graphics/drawable/Drawable;", "inflate", "updateResource", "(Landroid/content/Context;)V", "Lcom/google/android/material/appbar/model/SuggestAppBarModel;", "model", "setModel", "(Lcom/google/android/material/appbar/model/SuggestAppBarModel;)V", "", ThemeParkConstants.TITLE, "setTitle", "(Ljava/lang/String;)V", "Lcom/google/android/material/appbar/model/AppBarModel$OnClickListener;", "closeClickListener", "setCloseClickListener", "(Lcom/google/android/material/appbar/model/AppBarModel$OnClickListener;)V", "Lcom/google/android/material/appbar/model/ButtonListModel;", "buttonModels", "setButtonModules", "(Lcom/google/android/material/appbar/model/ButtonListModel;)V", "Lcom/google/android/material/appbar/model/SuggestAppBarModel;", "Landroid/widget/TextView;", "titleView", "Landroid/widget/TextView;", "getTitleView", "()Landroid/widget/TextView;", "setTitleView", "(Landroid/widget/TextView;)V", "Landroid/widget/ImageButton;", "close", "Landroid/widget/ImageButton;", "getClose", "()Landroid/widget/ImageButton;", "setClose", "(Landroid/widget/ImageButton;)V", "Landroid/view/ViewGroup;", "bottomLayout", "Landroid/view/ViewGroup;", "getBottomLayout", "()Landroid/view/ViewGroup;", "setBottomLayout", "(Landroid/view/ViewGroup;)V", "", "buttons", "Ljava/util/List;", "getButtons", "()Ljava/util/List;", "material_release"}, k = 1, mv = {1, 8, 0})
public class SuggestAppBarView extends AppBarView {
    private ViewGroup bottomLayout;
    private final List<Button> buttons;
    private ImageButton close;
    private SuggestAppBarModel<? extends SuggestAppBarView> model;
    private TextView titleView;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SuggestAppBarView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        j.f(context, "context");
    }

    private final void addMargin() {
        View view = new View(getContext());
        view.setLayoutParams(new ViewGroup.LayoutParams(view.getResources().getDimensionPixelOffset(R.dimen.sesl_appbar_button_side_margin), -1));
        ViewGroup viewGroup = this.bottomLayout;
        if (viewGroup != null) {
            viewGroup.addView(view);
        }
    }

    private final Button generateButton(ButtonModel buttonModel, int style) {
        Button button = new Button(getContext(), null, 0, style);
        button.setText(buttonModel.getText());
        String contentDescription = buttonModel.getContentDescription();
        if (contentDescription != null) {
            button.setContentDescription(contentDescription);
        }
        button.setOnClickListener(new b(15, buttonModel, this));
        return button;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void generateButton$lambda$9$lambda$8(ButtonModel buttonModel, SuggestAppBarView this$0, View it) {
        j.f(buttonModel, "$buttonModel");
        j.f(this$0, "this$0");
        AppBarModel.OnClickListener clickListener = buttonModel.getClickListener();
        if (clickListener != null) {
            j.e(it, "it");
            SuggestAppBarModel<? extends SuggestAppBarView> suggestAppBarModel = this$0.model;
            if (suggestAppBarModel != null) {
                clickListener.onClick(it, suggestAppBarModel);
            } else {
                j.n("model");
                throw null;
            }
        }
    }

    private final int getAppBarSuggestTitleColor(Context context) {
        int i5 = R.color.sesl_appbar_suggest_title;
        int i7 = R.color.sesl_appbar_suggest_title_dark;
        int i9 = R.color.sesl_appbar_suggest_title_dark_for_theme;
        j.f(context, "context");
        if (c.L(context)) {
            if (!c.O(context)) {
                i5 = i7;
            }
        } else if (!c.O(context)) {
            i5 = i9;
        }
        return B.b.a(context, i5);
    }

    private final Drawable getCloseDrawable(Context context) {
        int i5 = R.drawable.sesl_close_button_recoil_background;
        int i7 = R.drawable.sesl_close_button_recoil_background_dark;
        int i9 = R.drawable.sesl_close_button_recoil_background_for_theme;
        int i10 = R.drawable.sesl_close_button_recoil_background_dark_for_theme;
        j.f(context, "context");
        if (!c.L(context)) {
            if (!c.O(context)) {
                i9 = i10;
            }
            i5 = i9;
        } else if (!c.O(context)) {
            i5 = i7;
        }
        return a.b(context, i5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setCloseClickListener$lambda$4$lambda$3(AppBarModel.OnClickListener onClickListener, SuggestAppBarView this$0, View it) {
        j.f(this$0, "this$0");
        if (onClickListener != null) {
            j.e(it, "it");
            SuggestAppBarModel<? extends SuggestAppBarView> suggestAppBarModel = this$0.model;
            if (suggestAppBarModel != null) {
                onClickListener.onClick(it, suggestAppBarModel);
            } else {
                j.n("model");
                throw null;
            }
        }
    }

    public final ViewGroup getBottomLayout() {
        return this.bottomLayout;
    }

    public final List<Button> getButtons() {
        return this.buttons;
    }

    public final ImageButton getClose() {
        return this.close;
    }

    public final TextView getTitleView() {
        return this.titleView;
    }

    @Override // com.google.android.material.appbar.model.view.AppBarView
    public void inflate() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.sesl_app_bar_suggest, (ViewGroup) this, false);
        ImageButton imageButton = null;
        ViewGroup viewGroup = viewInflate instanceof ViewGroup ? (ViewGroup) viewInflate : null;
        if (viewGroup == null) {
            return;
        }
        this.titleView = (TextView) viewGroup.findViewById(R.id.suggest_app_bar_title);
        ImageButton imageButton2 = (ImageButton) viewGroup.findViewById(R.id.suggest_app_bar_close);
        if (imageButton2 != null) {
            d.C(AbstractC0527a.r(), imageButton2);
            imageButton = imageButton2;
        }
        this.close = imageButton;
        this.bottomLayout = (ViewGroup) viewGroup.findViewById(R.id.suggest_app_bar_bottom_layout);
        Context context = getContext();
        j.e(context, "context");
        updateResource(context);
        addView(viewGroup);
    }

    public final void setBottomLayout(ViewGroup viewGroup) {
        this.bottomLayout = viewGroup;
    }

    public final void setButtonModules(ButtonListModel buttonModels) {
        j.f(buttonModels, "buttonModels");
        ViewGroup viewGroup = this.bottomLayout;
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        this.buttons.clear();
        List<ButtonModel> buttonModels2 = buttonModels.getButtonModels();
        ButtonStyle buttonStyle = buttonModels.getButtonStyle();
        int i5 = 0;
        for (Object obj : buttonModels2) {
            int i7 = i5 + 1;
            if (i5 < 0) {
                o.b0();
                throw null;
            }
            Button buttonGenerateButton = generateButton((ButtonModel) obj, c.O(getContext()) ? buttonStyle.getDefStyleRes() : buttonStyle.getDefStyleResDark());
            buttonGenerateButton.setMaxWidth(buttonGenerateButton.getResources().getDimensionPixelSize(buttonModels2.size() > 1 ? R.dimen.sesl_appbar_button_max_width : R.dimen.sesl_appbar_button_max_width_multi));
            if (i5 != 0) {
                addMargin();
            }
            this.buttons.add(buttonGenerateButton);
            ViewGroup viewGroup2 = this.bottomLayout;
            if (viewGroup2 != null) {
                viewGroup2.addView(buttonGenerateButton);
            }
            i5 = i7;
        }
    }

    public final void setClose(ImageButton imageButton) {
        this.close = imageButton;
    }

    public final void setCloseClickListener(AppBarModel.OnClickListener closeClickListener) {
        ImageButton imageButton = this.close;
        if (imageButton != null) {
            imageButton.setVisibility(closeClickListener == null ? 8 : 0);
            imageButton.setOnClickListener(new b(16, closeClickListener, this));
        }
    }

    public final void setModel(SuggestAppBarModel<? extends SuggestAppBarView> model) {
        j.f(model, "model");
        this.model = model;
    }

    public final void setTitle(String title) {
        TextView textView = this.titleView;
        if (textView != null) {
            textView.setText(title);
            textView.setVisibility(TextUtils.isEmpty(title) ? 8 : 0);
        }
    }

    public final void setTitleView(TextView textView) {
        this.titleView = textView;
    }

    @Override // com.google.android.material.appbar.model.view.AppBarView
    public void updateResource(Context context) {
        j.f(context, "context");
        c.O(context);
        TextView textView = this.titleView;
        if (textView != null) {
            textView.setTextColor(getAppBarSuggestTitleColor(context));
        }
        ImageButton imageButton = this.close;
        if (imageButton != null) {
            String string = imageButton.getResources().getString(com.samsung.android.keyscafe.R.string.sesl_appbar_suggest_dismiss);
            j.e(string, "resources.getString(andr…l_appbar_suggest_dismiss)");
            d2.a(imageButton, string);
            imageButton.setContentDescription(string);
            imageButton.setBackground(getCloseDrawable(context));
        }
    }

    public /* synthetic */ SuggestAppBarView(Context context, AttributeSet attributeSet, int i5, e eVar) {
        this(context, (i5 & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SuggestAppBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        j.f(context, "context");
        this.buttons = new ArrayList();
        inflate();
    }
}
