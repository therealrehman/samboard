package androidx.picker.features.composable.widget;

import B0.h;
import B7.m;
import android.view.View;
import androidx.appcompat.widget.SwitchCompat;
import androidx.picker.features.composable.ActionableComposableViewHolder;
import androidx.picker.loader.select.SelectableItem;
import com.samsung.android.keyscafe.R;
import com.samsung.android.keyscafe.honeytea.model.HoneyTeaDB;
import d.InterfaceC0463a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import p7.AbstractC0876m;
import y8.G;

/* JADX INFO: loaded from: classes.dex */
@InterfaceC0463a
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0002H\u0010¢\u0006\u0004\b\f\u0010\u0005J\u0017\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000e\u0010\u0005R\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R(\u0010\u0019\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0002@BX\u0082\u000e¢\u0006\f\n\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Landroidx/picker/features/composable/widget/ComposableSwitchViewHolder;", "Landroidx/picker/features/composable/ActionableComposableViewHolder;", "Landroid/view/View;", "frameView", "<init>", "(Landroid/view/View;)V", "LB0/h;", "viewData", "LT6/p;", "bindData", "(LB0/h;)V", "itemView", "onBind$picker_app_release", "onBind", "onViewRecycled", "Landroidx/appcompat/widget/SwitchCompat;", "switch", "Landroidx/appcompat/widget/SwitchCompat;", "divider", "Landroid/view/View;", "Ly8/G;", "disposableHandle", "Ly8/G;", "", HoneyTeaDB.DB_COLUMN_NAME, "hasCustomClickListener", "Ljava/lang/Boolean;", "setHasCustomClickListener", "(Ljava/lang/Boolean;)V", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public final class ComposableSwitchViewHolder extends ActionableComposableViewHolder {
    private G disposableHandle;
    private final View divider;
    private Boolean hasCustomClickListener;
    private final SwitchCompat switch;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposableSwitchViewHolder(View frameView) {
        super(frameView);
        j.f(frameView, "frameView");
        View viewFindViewById = frameView.findViewById(R.id.switch_widget);
        j.c(viewFindViewById);
        this.switch = (SwitchCompat) viewFindViewById;
        View viewFindViewById2 = frameView.findViewById(R.id.switch_divider_widget);
        j.c(viewFindViewById2);
        this.divider = viewFindViewById2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean bindData$lambda$0(SelectableItem selectableItem, ComposableSwitchViewHolder this$0) {
        j.f(selectableItem, "$selectableItem");
        j.f(this$0, "this$0");
        selectableItem.setValue(Boolean.valueOf(!this$0.switch.isChecked()));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$1(SelectableItem selectableItem, ComposableSwitchViewHolder this$0, View view) {
        j.f(selectableItem, "$selectableItem");
        j.f(this$0, "this$0");
        selectableItem.setValue(Boolean.valueOf(this$0.switch.isChecked()));
    }

    private final void setHasCustomClickListener(Boolean bool) {
        this.hasCustomClickListener = bool;
        this.divider.setVisibility(j.a(bool, Boolean.TRUE) ? 0 : 8);
    }

    @Override // androidx.picker.features.composable.ActionableComposableViewHolder, androidx.picker.features.composable.ComposableViewHolder
    public void bindData(h viewData) {
        SelectableItem selectableItem;
        j.f(viewData, "viewData");
        B0.c cVar = viewData instanceof B0.c ? (B0.c) viewData : null;
        if (cVar == null || (selectableItem = cVar.f162c) == null) {
            return;
        }
        G g9 = this.disposableHandle;
        if (g9 != null) {
            g9.dispose();
        }
        this.disposableHandle = selectableItem.bind$picker_app_release(new m(20, this));
        setDoAction(new c(selectableItem, this, 1));
        this.switch.setOnClickListener(new C5.b(14, selectableItem, this));
    }

    @Override // androidx.picker.features.composable.ActionableComposableViewHolder, androidx.picker.features.composable.ComposableViewHolder
    public void onBind$picker_app_release(View itemView) {
        j.f(itemView, "itemView");
        if (this.hasCustomClickListener == null) {
            setHasCustomClickListener(Boolean.valueOf(itemView.hasOnClickListeners()));
        }
        AbstractC0876m.n(this.switch, j.a(this.hasCustomClickListener, Boolean.TRUE));
        super.onBind$picker_app_release(itemView);
    }

    @Override // androidx.picker.features.composable.ActionableComposableViewHolder, androidx.picker.features.composable.ComposableViewHolder
    public void onViewRecycled(View itemView) {
        j.f(itemView, "itemView");
        super.onViewRecycled(itemView);
        this.switch.setOnClickListener(null);
        G g9 = this.disposableHandle;
        if (g9 != null) {
            g9.dispose();
        }
        setHasCustomClickListener(null);
    }
}
