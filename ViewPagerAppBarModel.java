package com.google.android.material.appbar.model;

import U6.v;
import android.content.Context;
import com.google.android.material.appbar.model.view.AppBarView;
import com.google.android.material.appbar.model.view.ViewPagerAppBarView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.w;
import m7.InterfaceC0757d;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003:\u0001\u0011B5\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0016\b\u0002\u0010\n\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\t0\u00030\b¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000e\u001a\u00028\u00002\u0006\u0010\r\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u000e\u0010\u000fR\"\u0010\n\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\t0\u00030\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010\u0010¨\u0006\u0012"}, d2 = {"Lcom/google/android/material/appbar/model/ViewPagerAppBarModel;", "Lcom/google/android/material/appbar/model/view/ViewPagerAppBarView;", "T", "Lcom/google/android/material/appbar/model/AppBarModel;", "Lm7/d;", "kclazz", "Landroid/content/Context;", "context", "", "Lcom/google/android/material/appbar/model/view/AppBarView;", "appBarModels", "<init>", "(Lm7/d;Landroid/content/Context;Ljava/util/List;)V", "moduleView", "init", "(Lcom/google/android/material/appbar/model/view/ViewPagerAppBarView;)Lcom/google/android/material/appbar/model/view/ViewPagerAppBarView;", "Ljava/util/List;", "Builder", "material_release"}, k = 1, mv = {1, 8, 0})
public class ViewPagerAppBarModel<T extends ViewPagerAppBarView> extends AppBarModel<T> {
    private final List<AppBarModel<? extends AppBarView>> appBarModels;

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ#\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\r0\n\"\n\b\u0001\u0010\r\u0018\u0001*\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0082\bJ\u001a\u0010\u000e\u001a\u00020\u00002\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0010R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/google/android/material/appbar/model/ViewPagerAppBarModel$Builder;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "appBarModels", "", "Lcom/google/android/material/appbar/model/AppBarModel;", "Lcom/google/android/material/appbar/model/view/AppBarView;", "build", "Lcom/google/android/material/appbar/model/ViewPagerAppBarModel;", "Lcom/google/android/material/appbar/model/view/ViewPagerAppBarView;", "create", "T", "setModels", "models", "", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Builder {
        private List<? extends AppBarModel<AppBarView>> appBarModels;
        private final Context context;

        public Builder(Context context) {
            j.f(context, "context");
            this.context = context;
            this.appBarModels = v.f4893e;
        }

        private final <T extends ViewPagerAppBarView> ViewPagerAppBarModel<T> create(Context context) {
            throw new UnsupportedOperationException("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
        }

        public final ViewPagerAppBarModel<ViewPagerAppBarView> build() {
            return new ViewPagerAppBarModel<>(w.f11853a.b(ViewPagerAppBarView.class), this.context, this.appBarModels);
        }

        public final Builder setModels(List<AppBarModel<AppBarView>> models) {
            j.f(models, "models");
            this.appBarModels = models;
            return this;
        }
    }

    public /* synthetic */ ViewPagerAppBarModel(InterfaceC0757d interfaceC0757d, Context context, List list, int i5, e eVar) {
        this(interfaceC0757d, context, (i5 & 4) != 0 ? v.f4893e : list);
    }

    @Override // com.google.android.material.appbar.model.AppBarModel
    public T init(T moduleView) {
        j.f(moduleView, "moduleView");
        return moduleView;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ViewPagerAppBarModel(InterfaceC0757d kclazz, Context context, List<? extends AppBarModel<? extends AppBarView>> appBarModels) {
        super(kclazz, context);
        j.f(kclazz, "kclazz");
        j.f(context, "context");
        j.f(appBarModels, "appBarModels");
        this.appBarModels = appBarModels;
    }
}
