package androidx.picker.features.composable.widget;

import B0.h;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.CompoundButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.picker.features.composable.ActionableComposableViewHolder;
import androidx.picker.loader.select.SelectableItem;
import com.samsung.android.keyscafe.R;
import d.InterfaceC0463a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.m;
import kotlin.jvm.internal.w;
import m7.u;
import y8.G;

/* JADX INFO: loaded from: classes.dex */
@InterfaceC0463a
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0002H\u0010¢\u0006\u0004\b\f\u0010\u0005J\u0017\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000e\u0010\u0005R\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0018\u001a\u00020\u00178\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019¨\u0006\u001b²\u0006\u000e\u0010\u001a\u001a\u00020\u00178\n@\nX\u008a\u008e\u0002"}, d2 = {"Landroidx/picker/features/composable/widget/ComposableAllAppSwitchViewHolder;", "Landroidx/picker/features/composable/ActionableComposableViewHolder;", "Landroid/view/View;", "frameView", "<init>", "(Landroid/view/View;)V", "LB0/h;", "viewData", "LT6/p;", "bindData", "(LB0/h;)V", "itemView", "onBind$picker_app_release", "onBind", "onViewRecycled", "Landroidx/appcompat/widget/SwitchCompat;", "switch", "Landroidx/appcompat/widget/SwitchCompat;", "divider", "Landroid/view/View;", "Ly8/G;", "disposableHandle", "Ly8/G;", "", "fromUser", "Z", "selected", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public final class ComposableAllAppSwitchViewHolder extends ActionableComposableViewHolder {
    static final /* synthetic */ u[] $$delegatedProperties = {w.f11853a.d(new m(ComposableAllAppSwitchViewHolder.class, "selected"))};
    private G disposableHandle;
    private final View divider;
    private boolean fromUser;
    private final SwitchCompat switch;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposableAllAppSwitchViewHolder(View frameView) {
        super(frameView);
        j.f(frameView, "frameView");
        View viewFindViewById = frameView.findViewById(R.id.switch_widget);
        j.c(viewFindViewById);
        this.switch = (SwitchCompat) viewFindViewById;
        View viewFindViewById2 = frameView.findViewById(R.id.switch_divider_widget);
        j.c(viewFindViewById2);
        this.divider = viewFindViewById2;
    }

    private static final boolean bindData$lambda$0(SelectableItem selectableItem) {
        return selectableItem.getValue$picker_app_release(null, $$delegatedProperties[0]).booleanValue();
    }

    private static final void bindData$lambda$1(SelectableItem selectableItem, boolean z9) {
        selectableItem.setValue$picker_app_release(null, $$delegatedProperties[0], Boolean.valueOf(z9));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$2(ComposableAllAppSwitchViewHolder this$0, SelectableItem selected$delegate, View view) {
        j.f(this$0, "this$0");
        j.f(selected$delegate, "$selected$delegate");
        bindData$lambda$1(selected$delegate, this$0.switch.isChecked());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean bindData$lambda$3(ComposableAllAppSwitchViewHolder this$0, View view, MotionEvent motionEvent) {
        j.f(this$0, "this$0");
        this$0.fromUser = true;
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$4(ComposableAllAppSwitchViewHolder this$0, SelectableItem selected$delegate, CompoundButton compoundButton, boolean z9) {
        j.f(this$0, "this$0");
        j.f(selected$delegate, "$selected$delegate");
        if (this$0.fromUser) {
            bindData$lambda$1(selected$delegate, z9);
        }
        this$0.fromUser = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean bindData$lambda$5(ComposableAllAppSwitchViewHolder this$0, SelectableItem selected$delegate) {
        j.f(this$0, "this$0");
        j.f(selected$delegate, "$selected$delegate");
        bindData$lambda$1(selected$delegate, !this$0.switch.isChecked());
        return true;
    }

    @Override // androidx.picker.features.composable.ActionableComposableViewHolder, androidx.picker.features.composable.ComposableViewHolder
    public void bindData(h viewData) {
        j.f(viewData, "viewData");
        B0.a aVar = (B0.a) viewData;
        G g9 = this.disposableHandle;
        if (g9 != null) {
            g9.dispose();
        }
        B7.m mVar = new B7.m(19, this);
        final SelectableItem selectableItem = aVar.f158a;
        this.disposableHandle = selectableItem.bind$picker_app_release(mVar);
        this.switch.setChecked(bindData$lambda$0(selectableItem));
        this.switch.setOnClickListener(new C5.b(12, this, selectableItem));
        this.switch.setOnTouchListener(new a(0, this));
        this.switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: androidx.picker.features.composable.widget.b
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z9) {
                ComposableAllAppSwitchViewHolder.bindData$lambda$4(this.f7880e, selectableItem, compoundButton, z9);
            }
        });
        this.divider.setVisibility(8);
        setDoAction(new c(0, this, selectableItem));
    }

    @Override // androidx.picker.features.composable.ActionableComposableViewHolder, androidx.picker.features.composable.ComposableViewHolder
    public void onBind$picker_app_release(View itemView) {
        j.f(itemView, "itemView");
        super.onBind$picker_app_release(itemView);
        Object systemService = itemView.getContext().getSystemService("accessibility");
        AccessibilityManager accessibilityManager = systemService instanceof AccessibilityManager ? (AccessibilityManager) systemService : null;
        if (accessibilityManager == null || !accessibilityManager.isEnabled()) {
            return;
        }
        this.switch.setFocusable(false);
        this.switch.setClickable(false);
        itemView.setContentDescription(null);
    }

    @Override // androidx.picker.features.composable.ActionableComposableViewHolder, androidx.picker.features.composable.ComposableViewHolder
    public void onViewRecycled(View itemView) {
        j.f(itemView, "itemView");
        super.onViewRecycled(itemView);
        this.switch.setOnCheckedChangeListener(null);
        this.switch.setOnTouchListener(null);
        this.fromUser = false;
        G g9 = this.disposableHandle;
        if (g9 != null) {
            g9.dispose();
        }
    }
}
