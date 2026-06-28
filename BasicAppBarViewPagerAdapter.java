package com.google.android.material.appbar.model.adapter;

import U6.n;
import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.AbstractC0341j0;
import androidx.recyclerview.widget.V0;
import com.google.android.material.appbar.model.AppBarModel;
import com.google.android.material.appbar.model.SuggestAppBarModel;
import com.google.android.material.appbar.model.adapter.BasicAppBarViewPagerAdapter.BasicViewHolder;
import com.google.android.material.appbar.model.view.AppBarView;
import com.google.android.material.appbar.model.view.SuggestAppBarItemView;
import com.samsung.android.honeyboard.forms.common.IntentConstants;
import h7.InterfaceC0583a;
import h7.InterfaceC0584b;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.z;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\b\u0006\b'\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\u000e\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u00028\u00000\u00032\n\u0012\u0006\u0012\u0004\u0018\u00018\u00010\u0005:\u0001,B\u000f\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\u000e\u001a\u00028\u00012\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\fH&¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u001f\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00028\u00012\u0006\u0010\u0010\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0015\u0010\u0016J\u000f\u0010\u0017\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0017\u0010\u0018J!\u0010\u001c\u001a\u00020\u00142\u0012\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001a0\u0019¢\u0006\u0004\b\u001c\u0010\u001dJ\u001d\u0010 \u001a\u00020\u00142\u000e\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001f0\u001e¢\u0006\u0004\b \u0010!J\u001d\u0010\"\u001a\u00020\f2\u000e\u0010\u001b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001f0\u001e¢\u0006\u0004\b\"\u0010#R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010$\u001a\u0004\b%\u0010&R&\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001a0'8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b(\u0010)\u001a\u0004\b*\u0010+¨\u0006-"}, d2 = {"Lcom/google/android/material/appbar/model/adapter/BasicAppBarViewPagerAdapter;", "Lcom/google/android/material/appbar/model/view/SuggestAppBarItemView;", "T", "Lcom/google/android/material/appbar/model/adapter/BasicAppBarViewPagerAdapter$BasicViewHolder;", "U", "Landroidx/recyclerview/widget/j0;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/view/ViewGroup;", "parent", "", IntentConstants.EXTRA_VIEW_TYPE, "onCreateViewHolder", "(Landroid/view/ViewGroup;I)Lcom/google/android/material/appbar/model/adapter/BasicAppBarViewPagerAdapter$BasicViewHolder;", "position", "getItemViewType", "(I)I", "holder", "LT6/p;", "onBindViewHolder", "(Lcom/google/android/material/appbar/model/adapter/BasicAppBarViewPagerAdapter$BasicViewHolder;I)V", "getItemCount", "()I", "", "Lcom/google/android/material/appbar/model/SuggestAppBarModel;", "dataModel", "setDataModel", "(Ljava/util/List;)V", "Lcom/google/android/material/appbar/model/AppBarModel;", "Lcom/google/android/material/appbar/model/view/AppBarView;", "removeDataModel", "(Lcom/google/android/material/appbar/model/AppBarModel;)V", "find", "(Lcom/google/android/material/appbar/model/AppBarModel;)I", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "", "data", "Ljava/util/List;", "getData", "()Ljava/util/List;", "BasicViewHolder", "material_release"}, k = 1, mv = {1, 8, 0})
public abstract class BasicAppBarViewPagerAdapter<T extends SuggestAppBarItemView, U extends BasicViewHolder<T>> extends AbstractC0341j0 {
    private final Context context;
    private final List<SuggestAppBarModel<T>> data;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000*\b\b\u0002\u0010\u0002*\u00020\u00012\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00028\u0002¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0004\u001a\u00028\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0007\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/google/android/material/appbar/model/adapter/BasicAppBarViewPagerAdapter$BasicViewHolder;", "Lcom/google/android/material/appbar/model/view/SuggestAppBarItemView;", "V", "Landroidx/recyclerview/widget/V0;", "appBarModuleView", "<init>", "(Lcom/google/android/material/appbar/model/view/SuggestAppBarItemView;)V", "Lcom/google/android/material/appbar/model/view/SuggestAppBarItemView;", "getAppBarModuleView", "()Lcom/google/android/material/appbar/model/view/SuggestAppBarItemView;", "material_release"}, k = 1, mv = {1, 8, 0})
    public static class BasicViewHolder<V extends SuggestAppBarItemView> extends V0 {
        private final V appBarModuleView;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BasicViewHolder(V appBarModuleView) {
            super(appBarModuleView);
            j.f(appBarModuleView, "appBarModuleView");
            this.appBarModuleView = appBarModuleView;
        }

        public final V getAppBarModuleView() {
            return this.appBarModuleView;
        }
    }

    public BasicAppBarViewPagerAdapter(Context context) {
        j.f(context, "context");
        this.context = context;
        this.data = new ArrayList();
    }

    public final int find(AppBarModel<? extends AppBarView> dataModel) {
        j.f(dataModel, "dataModel");
        return n.s0(getData(), dataModel);
    }

    public final Context getContext() {
        return this.context;
    }

    public List<SuggestAppBarModel<T>> getData() {
        return this.data;
    }

    @Override // androidx.recyclerview.widget.AbstractC0341j0
    public int getItemCount() {
        return getData().size();
    }

    @Override // androidx.recyclerview.widget.AbstractC0341j0
    public int getItemViewType(int position) {
        return 0;
    }

    @Override // androidx.recyclerview.widget.AbstractC0341j0
    public abstract U onCreateViewHolder(ViewGroup parent, int viewType);

    public final void removeDataModel(AppBarModel<? extends AppBarView> dataModel) {
        j.f(dataModel, "dataModel");
        int iS0 = n.s0(getData(), dataModel);
        List<SuggestAppBarModel<T>> data = getData();
        if ((data instanceof InterfaceC0583a) && !(data instanceof InterfaceC0584b)) {
            z.d(data, "kotlin.collections.MutableCollection");
            throw null;
        }
        data.remove(dataModel);
        notifyItemRemoved(iS0);
    }

    public final void setDataModel(List<? extends SuggestAppBarModel<T>> dataModel) {
        j.f(dataModel, "dataModel");
        getData().clear();
        getData().addAll(dataModel);
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.AbstractC0341j0
    public void onBindViewHolder(U holder, int position) {
        j.f(holder, "holder");
        getData().get(position).init(holder.getAppBarModuleView());
    }
}
