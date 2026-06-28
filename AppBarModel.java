package com.google.android.material.appbar.model;

import U6.n;
import android.content.Context;
import android.view.View;
import com.google.android.material.appbar.model.view.AppBarView;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import m7.InterfaceC0757d;
import m7.InterfaceC0760g;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003:\u0001\u0011B\u001d\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\r\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\r\u0010\u000eR\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u000fR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u0010¨\u0006\u0012"}, d2 = {"Lcom/google/android/material/appbar/model/AppBarModel;", "Lcom/google/android/material/appbar/model/view/AppBarView;", "T", "", "Lm7/d;", "kclazz", "Landroid/content/Context;", "context", "<init>", "(Lm7/d;Landroid/content/Context;)V", "create", "()Lcom/google/android/material/appbar/model/view/AppBarView;", "moduleView", "init", "(Lcom/google/android/material/appbar/model/view/AppBarView;)Lcom/google/android/material/appbar/model/view/AppBarView;", "Lm7/d;", "Landroid/content/Context;", "OnClickListener", "material_release"}, k = 1, mv = {1, 8, 0})
public class AppBarModel<T extends AppBarView> {
    private final Context context;
    private final InterfaceC0757d kclazz;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J'\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u000e\u0010\u0006\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u0004H&¢\u0006\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lcom/google/android/material/appbar/model/AppBarModel$OnClickListener;", "", "Landroid/view/View;", "view", "Lcom/google/android/material/appbar/model/AppBarModel;", "Lcom/google/android/material/appbar/model/view/AppBarView;", "module", "LT6/p;", "onClick", "(Landroid/view/View;Lcom/google/android/material/appbar/model/AppBarModel;)V", "material_release"}, k = 1, mv = {1, 8, 0})
    public interface OnClickListener {
        void onClick(View view, AppBarModel<? extends AppBarView> module);
    }

    public AppBarModel(InterfaceC0757d kclazz, Context context) {
        j.f(kclazz, "kclazz");
        j.f(context, "context");
        this.kclazz = kclazz;
        this.context = context;
    }

    public T create() {
        return (T) init((AppBarView) ((InterfaceC0760g) n.n0(this.kclazz.e())).call(this.context, null));
    }

    public T init(T moduleView) {
        j.f(moduleView, "moduleView");
        return moduleView;
    }
}
