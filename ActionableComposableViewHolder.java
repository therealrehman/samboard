package androidx.picker.features.composable;

import B0.h;
import B5.c;
import K.g;
import android.view.View;
import d.InterfaceC0463a;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

/* JADX INFO: loaded from: classes.dex */
@InterfaceC0463a
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b!\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0002H\u0011¢\u0006\u0004\b\f\u0010\u0005J\u0017\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0002H\u0017¢\u0006\u0004\b\u000e\u0010\u0005R*\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroidx/picker/features/composable/ActionableComposableViewHolder;", "Landroidx/picker/features/composable/ComposableViewHolder;", "Landroid/view/View;", "frameView", "<init>", "(Landroid/view/View;)V", "LB0/h;", "viewData", "LT6/p;", "bindData", "(LB0/h;)V", "itemView", "onBind$picker_app_release", "onBind", "onViewRecycled", "LK/g;", "", "doAction", "LK/g;", "getDoAction", "()LK/g;", "setDoAction", "(LK/g;)V", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public abstract class ActionableComposableViewHolder extends ComposableViewHolder {
    private g doAction;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActionableComposableViewHolder(View frameView) {
        super(frameView);
        j.f(frameView, "frameView");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBind$lambda$0(ActionableComposableViewHolder this$0, View view) {
        j.f(this$0, "this$0");
        g doAction = this$0.getDoAction();
        if (doAction != null) {
        }
    }

    @Override // androidx.picker.features.composable.ComposableViewHolder
    public abstract void bindData(h viewData);

    public g getDoAction() {
        return this.doAction;
    }

    @Override // androidx.picker.features.composable.ComposableViewHolder
    public void onBind$picker_app_release(View itemView) {
        j.f(itemView, "itemView");
        super.onBind$picker_app_release(itemView);
        if (getDoAction() == null || itemView.hasOnClickListeners()) {
            return;
        }
        itemView.setOnClickListener(new c(25, this));
    }

    @Override // androidx.picker.features.composable.ComposableViewHolder
    public void onViewRecycled(View itemView) {
        j.f(itemView, "itemView");
        super.onViewRecycled(itemView);
        if (getDoAction() != null) {
            itemView.setOnClickListener(null);
        }
        setDoAction(null);
    }

    public void setDoAction(g gVar) {
        this.doAction = gVar;
    }
}
