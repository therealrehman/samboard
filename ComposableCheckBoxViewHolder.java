package androidx.picker.features.composable.left;

import B0.h;
import C5.b;
import android.view.View;
import android.widget.CheckBox;
import androidx.picker.features.composable.ActionableComposableViewHolder;
import androidx.picker.features.composable.widget.c;
import androidx.picker.loader.select.SelectableItem;
import d.InterfaceC0463a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import l8.C0698T;
import p7.AbstractC0876m;
import y8.G;
import z0.e;

/* JADX INFO: loaded from: classes.dex */
@InterfaceC0463a
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0002H\u0010¢\u0006\u0004\b\f\u0010\u0005J\u0017\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000e\u0010\u0005R\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/picker/features/composable/left/ComposableCheckBoxViewHolder;", "Landroidx/picker/features/composable/ActionableComposableViewHolder;", "Landroid/view/View;", "frameView", "<init>", "(Landroid/view/View;)V", "LB0/h;", "viewData", "LT6/p;", "bindData", "(LB0/h;)V", "itemView", "onBind$picker_app_release", "onBind", "onViewRecycled", "Landroid/widget/CheckBox;", "checkBox", "Landroid/widget/CheckBox;", "Ly8/G;", "disposableHandle", "Ly8/G;", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public final class ComposableCheckBoxViewHolder extends ActionableComposableViewHolder {
    private final CheckBox checkBox;
    private G disposableHandle;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposableCheckBoxViewHolder(View frameView) {
        super(frameView);
        j.f(frameView, "frameView");
        this.checkBox = (CheckBox) frameView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$0(SelectableItem selectableItem, ComposableCheckBoxViewHolder this$0, View view) {
        j.f(selectableItem, "$selectableItem");
        j.f(this$0, "this$0");
        selectableItem.setValue(Boolean.valueOf(this$0.checkBox.isChecked()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean bindData$lambda$1(SelectableItem selectableItem, ComposableCheckBoxViewHolder this$0) {
        j.f(selectableItem, "$selectableItem");
        j.f(this$0, "this$0");
        selectableItem.setValue(Boolean.valueOf(!this$0.checkBox.isChecked()));
        return true;
    }

    @Override // androidx.picker.features.composable.ActionableComposableViewHolder, androidx.picker.features.composable.ComposableViewHolder
    public void bindData(h viewData) {
        SelectableItem selectableItemM;
        j.f(viewData, "viewData");
        e eVar = viewData instanceof e ? (e) viewData : null;
        if (eVar == null || (selectableItemM = eVar.m()) == null) {
            return;
        }
        G g9 = this.disposableHandle;
        if (g9 != null) {
            g9.dispose();
        }
        this.disposableHandle = selectableItemM.bind$picker_app_release(new C0698T(3, this));
        this.checkBox.setOnClickListener(new b(26, selectableItemM, this));
        setDoAction(new c(selectableItemM, this, 4));
    }

    @Override // androidx.picker.features.composable.ActionableComposableViewHolder, androidx.picker.features.composable.ComposableViewHolder
    public void onBind$picker_app_release(View itemView) {
        j.f(itemView, "itemView");
        AbstractC0876m.n(this.checkBox, itemView.hasOnClickListeners());
        super.onBind$picker_app_release(itemView);
    }

    @Override // androidx.picker.features.composable.ActionableComposableViewHolder, androidx.picker.features.composable.ComposableViewHolder
    public void onViewRecycled(View itemView) {
        j.f(itemView, "itemView");
        super.onViewRecycled(itemView);
        this.checkBox.setOnClickListener(null);
        G g9 = this.disposableHandle;
        if (g9 != null) {
            g9.dispose();
        }
    }
}
