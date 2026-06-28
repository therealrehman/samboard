package androidx.picker.features.composable;

import B0.h;
import android.view.View;
import d.InterfaceC0463a;
import f0.AbstractC0513c;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

/* JADX INFO: loaded from: classes.dex */
@InterfaceC0463a
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\b'\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H&¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0002H\u0011¢\u0006\u0004\b\f\u0010\u0005J\u0017\u0010\u0010\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0012\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0002H\u0017¢\u0006\u0004\b\u0012\u0010\u0005R\u001a\u0010\u0003\u001a\u00020\u00028\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Landroidx/picker/features/composable/ComposableViewHolder;", "", "Landroid/view/View;", "frameView", "<init>", "(Landroid/view/View;)V", "LB0/h;", "viewData", "LT6/p;", "bindData", "(LB0/h;)V", "itemView", "onBind$picker_app_release", "onBind", "Lf0/c;", "adapter", "bindAdapter", "(Lf0/c;)V", "onViewRecycled", "Landroid/view/View;", "getFrameView", "()Landroid/view/View;", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public abstract class ComposableViewHolder {
    private final View frameView;

    public ComposableViewHolder(View frameView) {
        j.f(frameView, "frameView");
        this.frameView = frameView;
    }

    public void bindAdapter(AbstractC0513c adapter) {
        j.f(adapter, "adapter");
    }

    public abstract void bindData(h viewData);

    public final View getFrameView() {
        return this.frameView;
    }

    public void onBind$picker_app_release(View itemView) {
        j.f(itemView, "itemView");
    }

    public void onViewRecycled(View itemView) {
        j.f(itemView, "itemView");
    }
}
