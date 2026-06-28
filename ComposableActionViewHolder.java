package androidx.picker.features.composable.widget;

import B0.h;
import android.view.View;
import android.widget.ImageButton;
import androidx.picker.features.composable.ActionableComposableViewHolder;
import com.samsung.android.keyscafe.R;
import com.samsung.android.keyscafe.honeytea.model.HoneyTeaDB;
import d.InterfaceC0463a;
import g7.InterfaceC0562b;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

/* JADX INFO: loaded from: classes.dex */
@InterfaceC0463a
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0002H\u0010¢\u0006\u0004\b\f\u0010\u0005J\u0017\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000e\u0010\u0005R\u0014\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R(\u0010\u0016\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0002@BX\u0082\u000e¢\u0006\f\n\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Landroidx/picker/features/composable/widget/ComposableActionViewHolder;", "Landroidx/picker/features/composable/ActionableComposableViewHolder;", "Landroid/view/View;", "frameView", "<init>", "(Landroid/view/View;)V", "LB0/h;", "viewData", "LT6/p;", "bindData", "(LB0/h;)V", "itemView", "onBind$picker_app_release", "onBind", "onViewRecycled", "Landroid/widget/ImageButton;", "imageButton", "Landroid/widget/ImageButton;", "divider", "Landroid/view/View;", "", HoneyTeaDB.DB_COLUMN_NAME, "hasCustomClickListener", "Ljava/lang/Boolean;", "setHasCustomClickListener", "(Ljava/lang/Boolean;)V", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public final class ComposableActionViewHolder extends ActionableComposableViewHolder {
    private final View divider;
    private Boolean hasCustomClickListener;
    private final ImageButton imageButton;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposableActionViewHolder(View frameView) {
        super(frameView);
        j.f(frameView, "frameView");
        View viewFindViewById = frameView.findViewById(R.id.image_button);
        j.c(viewFindViewById);
        this.imageButton = (ImageButton) viewFindViewById;
        View viewFindViewById2 = frameView.findViewById(R.id.switch_divider_widget);
        j.c(viewFindViewById2);
        this.divider = viewFindViewById2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$1$lambda$0(InterfaceC0562b actionClick, h viewData, View view) {
        j.f(actionClick, "$actionClick");
        j.f(viewData, "$viewData");
        actionClick.invoke(viewData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean bindData$lambda$2(B0.c appInfoViewData) {
        j.f(appInfoViewData, "$appInfoViewData");
        return true;
    }

    private final void setHasCustomClickListener(Boolean bool) {
        this.hasCustomClickListener = bool;
        this.divider.setVisibility(j.a(bool, Boolean.TRUE) ? 0 : 8);
    }

    @Override // androidx.picker.features.composable.ActionableComposableViewHolder, androidx.picker.features.composable.ComposableViewHolder
    public void bindData(h viewData) {
        j.f(viewData, "viewData");
        B0.c cVar = (B0.c) viewData;
        this.imageButton.setImageDrawable(cVar.f160a.l());
        InterfaceC0562b interfaceC0562b = cVar.f164e;
        if (interfaceC0562b != null) {
            this.imageButton.setOnClickListener(new C5.b(11, interfaceC0562b, viewData));
        }
        setDoAction(new A6.a(2, cVar));
    }

    @Override // androidx.picker.features.composable.ActionableComposableViewHolder, androidx.picker.features.composable.ComposableViewHolder
    public void onBind$picker_app_release(View itemView) {
        j.f(itemView, "itemView");
        if (this.hasCustomClickListener == null) {
            setHasCustomClickListener(Boolean.valueOf(itemView.hasOnClickListeners()));
        }
        super.onBind$picker_app_release(itemView);
    }

    @Override // androidx.picker.features.composable.ActionableComposableViewHolder, androidx.picker.features.composable.ComposableViewHolder
    public void onViewRecycled(View itemView) {
        j.f(itemView, "itemView");
        super.onViewRecycled(itemView);
        this.imageButton.setOnClickListener(null);
    }
}
