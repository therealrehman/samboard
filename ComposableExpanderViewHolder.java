package androidx.picker.features.composable.widget;

import B0.e;
import B0.h;
import android.view.View;
import android.widget.ImageView;
import androidx.picker.features.composable.ActionableComposableViewHolder;
import com.samsung.android.keyscafe.R;
import d.InterfaceC0463a;
import f0.AbstractC0513c;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

/* JADX INFO: loaded from: classes.dex */
@InterfaceC0463a
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0014\u0010\u0005R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0019\u001a\u00020\u00188\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a¨\u0006\u001b"}, d2 = {"Landroidx/picker/features/composable/widget/ComposableExpanderViewHolder;", "Landroidx/picker/features/composable/ActionableComposableViewHolder;", "Landroid/view/View;", "frameView", "<init>", "(Landroid/view/View;)V", "Lf0/c;", "adapter", "", "collapsed", "LT6/p;", "checkCollapsed", "(Lf0/c;Z)V", "LB0/h;", "viewData", "bindData", "(LB0/h;)V", "bindAdapter", "(Lf0/c;)V", "itemView", "onViewRecycled", "Landroid/widget/ImageView;", "toggle", "Landroid/widget/ImageView;", "LB0/e;", "refferalItem", "LB0/e;", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public final class ComposableExpanderViewHolder extends ActionableComposableViewHolder {
    private e refferalItem;
    private final ImageView toggle;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposableExpanderViewHolder(View frameView) {
        super(frameView);
        j.f(frameView, "frameView");
        View viewFindViewById = frameView.findViewById(R.id.image_button);
        j.c(viewFindViewById);
        this.toggle = (ImageView) viewFindViewById;
        j.c(frameView.findViewById(R.id.switch_divider_widget));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindAdapter$lambda$2$lambda$1(ComposableExpanderViewHolder this$0, AbstractC0513c adapter, View view) {
        j.f(this$0, "this$0");
        j.f(adapter, "$adapter");
        e eVar = this$0.refferalItem;
        if (eVar == null) {
            j.n("refferalItem");
            throw null;
        }
        view.setSelected(eVar.f169c.isEmpty());
        this$0.checkCollapsed(adapter, view.isSelected());
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0047, code lost:
    
        r8.notifyItemRangeRemoved(r5, r9);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void checkCollapsed(f0.AbstractC0513c r8, boolean r9) {
        /*
            r7 = this;
            java.util.ArrayList r0 = r8.f10768f
            java.lang.String r1 = "null cannot be cast to non-null type java.util.ArrayList<@[EnhancedNullability] androidx.picker.model.viewdata.ViewData>"
            kotlin.jvm.internal.j.d(r0, r1)
            B0.e r1 = r7.refferalItem
            r2 = 0
            java.lang.String r3 = "refferalItem"
            if (r1 == 0) goto L85
            int r1 = r0.indexOf(r1)
            if (r9 == 0) goto L4b
            r9 = 0
        L15:
            int r4 = r0.size()
            int r5 = r1 + 1
            if (r4 <= r5) goto L47
            java.lang.Object r4 = r0.get(r5)
            java.lang.String r6 = "data[pos + 1]"
            kotlin.jvm.internal.j.e(r4, r6)
            B0.h r4 = (B0.h) r4
            boolean r4 = checkCollapsed$isCanBeCollapsed(r4)
            if (r4 == 0) goto L47
            B0.e r4 = r7.refferalItem
            if (r4 == 0) goto L43
            java.lang.Object r5 = r0.remove(r5)
            java.lang.String r6 = "data.removeAt(pos + 1)"
            kotlin.jvm.internal.j.e(r5, r6)
            java.util.List r4 = r4.f169c
            r4.add(r5)
            int r9 = r9 + 1
            goto L15
        L43:
            kotlin.jvm.internal.j.n(r3)
            throw r2
        L47:
            r8.notifyItemRangeRemoved(r5, r9)
            goto L79
        L4b:
            int r9 = r1 + 1
            B0.e r4 = r7.refferalItem
            if (r4 == 0) goto L81
            java.util.List r4 = r4.f169c
            java.util.Iterator r4 = r4.iterator()
            r5 = r9
        L58:
            boolean r6 = r4.hasNext()
            if (r6 == 0) goto L6a
            java.lang.Object r6 = r4.next()
            B0.h r6 = (B0.h) r6
            r0.add(r5, r6)
            int r5 = r5 + 1
            goto L58
        L6a:
            int r5 = r5 - r1
            int r5 = r5 + (-1)
            r8.notifyItemRangeInserted(r9, r5)
            B0.e r7 = r7.refferalItem
            if (r7 == 0) goto L7d
            java.util.List r7 = r7.f169c
            r7.clear()
        L79:
            r8.notifyItemChanged(r1)
            return
        L7d:
            kotlin.jvm.internal.j.n(r3)
            throw r2
        L81:
            kotlin.jvm.internal.j.n(r3)
            throw r2
        L85:
            kotlin.jvm.internal.j.n(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.picker.features.composable.widget.ComposableExpanderViewHolder.checkCollapsed(f0.c, boolean):void");
    }

    private static final boolean checkCollapsed$isCanBeCollapsed(h hVar) {
        return hVar instanceof B0.c;
    }

    @Override // androidx.picker.features.composable.ComposableViewHolder
    public void bindAdapter(AbstractC0513c adapter) {
        j.f(adapter, "adapter");
        this.toggle.setOnClickListener(new C5.b(13, this, adapter));
    }

    @Override // androidx.picker.features.composable.ActionableComposableViewHolder, androidx.picker.features.composable.ComposableViewHolder
    public void bindData(h viewData) {
        j.f(viewData, "viewData");
        if (viewData instanceof e) {
            e eVar = (e) viewData;
            this.refferalItem = eVar;
            this.toggle.setSelected(eVar.f169c.isEmpty());
        }
    }

    @Override // androidx.picker.features.composable.ActionableComposableViewHolder, androidx.picker.features.composable.ComposableViewHolder
    public void onViewRecycled(View itemView) {
        j.f(itemView, "itemView");
        super.onViewRecycled(itemView);
        ImageView imageView = this.toggle;
        imageView.setSelected(false);
        imageView.setOnTouchListener(null);
        imageView.setOnClickListener(null);
    }
}
