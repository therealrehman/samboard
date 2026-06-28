package com.google.android.material.appbar.model;

import U6.v;
import android.content.Context;
import com.google.android.material.R;
import com.google.android.material.appbar.model.AppBarModel;
import com.google.android.material.appbar.model.view.SuggestAppBarItemWhiteCaseView;
import com.samsung.android.keyscafe.honeytea.utils.ThemeParkConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.w;
import m7.InterfaceC0757d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0017\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003:\u0001\u0013B9\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0011\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Lcom/google/android/material/appbar/model/SuggestAppBarItemWhiteCaseModel;", "Lcom/google/android/material/appbar/model/view/SuggestAppBarItemWhiteCaseView;", "T", "Lcom/google/android/material/appbar/model/SuggestAppBarItemModel;", "Lm7/d;", "kclazz", "Landroid/content/Context;", "context", "", ThemeParkConstants.TITLE, "Lcom/google/android/material/appbar/model/AppBarModel$OnClickListener;", "closeClickListener", "Lcom/google/android/material/appbar/model/ButtonListModel;", "buttonListModel", "<init>", "(Lm7/d;Landroid/content/Context;Ljava/lang/String;Lcom/google/android/material/appbar/model/AppBarModel$OnClickListener;Lcom/google/android/material/appbar/model/ButtonListModel;)V", "moduleView", "init", "(Lcom/google/android/material/appbar/model/view/SuggestAppBarItemWhiteCaseView;)Lcom/google/android/material/appbar/model/view/SuggestAppBarItemWhiteCaseView;", "Builder", "material_release"}, k = 1, mv = {1, 8, 0})
public class SuggestAppBarItemWhiteCaseModel<T extends SuggestAppBarItemWhiteCaseView> extends SuggestAppBarItemModel<T> {

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fJ\u001b\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00120\u000f\"\n\b\u0001\u0010\u0012\u0018\u0001*\u00020\u0010H\u0082\bJ\"\u0010\u0013\u001a\u00020\u00002\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0007J\u0010\u0010\u0014\u001a\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u000bJ\u0010\u0010\u0015\u001a\u00020\u00002\b\u0010\f\u001a\u0004\u0018\u00010\rR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/google/android/material/appbar/model/SuggestAppBarItemWhiteCaseModel$Builder;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "buttonStyle", "Lcom/google/android/material/appbar/model/ButtonStyle;", "buttons", "", "Lcom/google/android/material/appbar/model/ButtonModel;", "closeClickListener", "Lcom/google/android/material/appbar/model/AppBarModel$OnClickListener;", ThemeParkConstants.TITLE, "", "build", "Lcom/google/android/material/appbar/model/SuggestAppBarItemWhiteCaseModel;", "Lcom/google/android/material/appbar/model/view/SuggestAppBarItemWhiteCaseView;", "create", "T", "setButtons", "setCloseClickListener", "setTitle", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Builder {
        private ButtonStyle buttonStyle;
        private List<? extends ButtonModel> buttons;
        private AppBarModel.OnClickListener closeClickListener;
        private final Context context;
        private String title;

        public Builder(Context context) {
            j.f(context, "context");
            this.context = context;
            this.buttons = v.f4893e;
        }

        private final <T extends SuggestAppBarItemWhiteCaseView> SuggestAppBarItemWhiteCaseModel<T> create() {
            throw new UnsupportedOperationException("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
        }

        public static /* synthetic */ Builder setButtons$default(Builder builder, List list, ButtonStyle buttonStyle, int i5, Object obj) {
            if ((i5 & 2) != 0) {
                buttonStyle = null;
            }
            return builder.setButtons(list, buttonStyle);
        }

        public final SuggestAppBarItemWhiteCaseModel<SuggestAppBarItemWhiteCaseView> build() {
            InterfaceC0757d interfaceC0757dB = w.f11853a.b(SuggestAppBarItemWhiteCaseView.class);
            Context context = this.context;
            String str = this.title;
            AppBarModel.OnClickListener onClickListener = this.closeClickListener;
            ButtonStyle buttonStyle = this.buttonStyle;
            if (buttonStyle == null) {
                buttonStyle = new ButtonStyle(R.style.Basic_CollapsingToolbar_Button_Light, R.style.Basic_CollapsingToolbar_Button);
            }
            return new SuggestAppBarItemWhiteCaseModel<>(interfaceC0757dB, context, str, onClickListener, new ButtonListModel(buttonStyle, this.buttons));
        }

        public final Builder setButtons(List<? extends ButtonModel> buttons) {
            j.f(buttons, "buttons");
            return setButtons$default(this, buttons, null, 2, null);
        }

        public final Builder setCloseClickListener(AppBarModel.OnClickListener closeClickListener) {
            this.closeClickListener = closeClickListener;
            return this;
        }

        public final Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public final Builder setButtons(List<? extends ButtonModel> buttons, ButtonStyle buttonStyle) {
            j.f(buttons, "buttons");
            this.buttons = buttons;
            if (buttonStyle != null) {
                this.buttonStyle = buttonStyle;
            }
            return this;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SuggestAppBarItemWhiteCaseModel(InterfaceC0757d kclazz, Context context, String str, AppBarModel.OnClickListener onClickListener, ButtonListModel buttonListModel) {
        super(kclazz, context, str, onClickListener, buttonListModel);
        j.f(kclazz, "kclazz");
        j.f(context, "context");
        j.f(buttonListModel, "buttonListModel");
    }

    @Override // com.google.android.material.appbar.model.SuggestAppBarItemModel, com.google.android.material.appbar.model.SuggestAppBarModel
    public T init(T moduleView) {
        j.f(moduleView, "moduleView");
        moduleView.setModel(this);
        moduleView.setTitle(getTitle());
        moduleView.setCloseClickListener(getCloseClickListener());
        moduleView.setButtonModules(getButtonListModel());
        Context context = moduleView.getContext();
        j.e(context, "context");
        moduleView.updateResource(context);
        return moduleView;
    }
}
