package androidx.picker.features.composable.title;

import B0.h;
import B7.m;
import T6.e;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.p;
import androidx.picker.features.composable.ComposableViewHolder;
import androidx.picker.features.observable.ObservableProperty;
import androidx.picker.loader.select.SelectableItem;
import com.samsung.android.keyscafe.R;
import d.InterfaceC0463a;
import f6.AbstractC0527a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.q;
import kotlin.jvm.internal.w;
import m7.u;
import y8.G;

/* JADX INFO: loaded from: classes.dex */
@InterfaceC0463a
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\nJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0017\u0010\u0005R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001aR\u001b\u0010!\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001b\u0010$\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\u001e\u001a\u0004\b#\u0010 R\u001b\u0010'\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b%\u0010\u001e\u001a\u0004\b&\u0010 R\u0018\u0010)\u001a\u0004\u0018\u00010(8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010+\u001a\u00020\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010,¨\u0006/²\u0006\f\u0010.\u001a\u00020-8\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/picker/features/composable/title/ComposableTitleViewHolder;", "Landroidx/picker/features/composable/ComposableViewHolder;", "Landroid/view/View;", "frameView", "<init>", "(Landroid/view/View;)V", "", "showSubLabel", "", "getLayoutId", "(Z)I", "getLayoutHeight", "hasSubLabel", "LT6/p;", "adjustLayout", "(Z)V", "LB0/h;", "viewData", "getSubLabelShowState", "(LB0/h;)Z", "bindData", "(LB0/h;)V", "itemView", "onViewRecycled", "Landroid/widget/TextView;", "titleView", "Landroid/widget/TextView;", "summaryView", "extraTitleView", "highlightColor$delegate", "LT6/e;", "getHighlightColor", "()I", "highlightColor", "subLabelValueColor$delegate", "getSubLabelValueColor", "subLabelValueColor", "subLabelDescriptionColor$delegate", "getSubLabelDescriptionColor", "subLabelDescriptionColor", "Ly8/G;", "disposableHandle", "Ly8/G;", "currentLayoutId", "I", "", "highlightText", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public final class ComposableTitleViewHolder extends ComposableViewHolder {
    static final /* synthetic */ u[] $$delegatedProperties = {w.f11853a.f(new q(kotlin.jvm.internal.b.NO_RECEIVER, ComposableTitleViewHolder.class, "highlightText", "<v#0>", 0))};
    private int currentLayoutId;
    private G disposableHandle;
    private final TextView extraTitleView;

    /* JADX INFO: renamed from: highlightColor$delegate, reason: from kotlin metadata */
    private final e highlightColor;

    /* JADX INFO: renamed from: subLabelDescriptionColor$delegate, reason: from kotlin metadata */
    private final e subLabelDescriptionColor;

    /* JADX INFO: renamed from: subLabelValueColor$delegate, reason: from kotlin metadata */
    private final e subLabelValueColor;
    private final TextView summaryView;
    private final TextView titleView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposableTitleViewHolder(View frameView) {
        super(frameView);
        j.f(frameView, "frameView");
        View viewFindViewById = frameView.findViewById(R.id.title);
        j.e(viewFindViewById, "frameView.findViewById(R.id.title)");
        this.titleView = (TextView) viewFindViewById;
        View viewFindViewById2 = frameView.findViewById(R.id.summary);
        j.e(viewFindViewById2, "frameView.findViewById(R.id.summary)");
        this.summaryView = (TextView) viewFindViewById2;
        View viewFindViewById3 = frameView.findViewById(R.id.extra_label);
        j.e(viewFindViewById3, "frameView.findViewById(R.id.extra_label)");
        this.extraTitleView = (TextView) viewFindViewById3;
        this.highlightColor = AbstractC0527a.N(new b(frameView, 0));
        this.subLabelValueColor = AbstractC0527a.N(new b(frameView, 2));
        this.subLabelDescriptionColor = AbstractC0527a.N(new b(frameView, 1));
        this.currentLayoutId = R.layout.picker_app_composable_frame_title_single;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void adjustLayout(boolean hasSubLabel) {
        if (getFrameView() instanceof ConstraintLayout) {
            p pVar = new p();
            Context context = ((ConstraintLayout) getFrameView()).getContext();
            pVar.c((ConstraintLayout) LayoutInflater.from(context).inflate(this.currentLayoutId, (ViewGroup) null));
            pVar.a((ConstraintLayout) getFrameView());
            getFrameView().getLayoutParams().height = getLayoutHeight(hasSubLabel);
        }
    }

    private static final String bindData$lambda$1(ObservableProperty<String> observableProperty) {
        return observableProperty.getValue$picker_app_release(null, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindData$lambda$3(List disposableHandleList) {
        j.f(disposableHandleList, "$disposableHandleList");
        Iterator it = disposableHandleList.iterator();
        while (it.hasNext()) {
            ((G) it.next()).dispose();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getHighlightColor() {
        return ((Number) this.highlightColor.getValue()).intValue();
    }

    private final int getLayoutHeight(boolean showSubLabel) {
        return showSubLabel ? getFrameView().getResources().getDimensionPixelOffset(R.dimen.picker_app_list_sub_label_height) : getFrameView().getResources().getDimensionPixelOffset(R.dimen.picker_app_list_single_line_height);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getLayoutId(boolean showSubLabel) {
        return showSubLabel ? R.layout.picker_app_composable_frame_title_sublabel : R.layout.picker_app_composable_frame_title_single;
    }

    private final int getSubLabelDescriptionColor() {
        return ((Number) this.subLabelDescriptionColor.getValue()).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getSubLabelShowState(h viewData) {
        if (!(viewData instanceof B0.c)) {
            return false;
        }
        B0.c cVar = (B0.c) viewData;
        return (cVar.f160a.i() == 5 && cVar.f160a.a() && !cVar.f160a.p()) ? false : true;
    }

    private final int getSubLabelValueColor() {
        return ((Number) this.subLabelValueColor.getValue()).intValue();
    }

    @Override // androidx.picker.features.composable.ComposableViewHolder
    public void bindData(h viewData) {
        G gRegisterAfterChangeUpdateListener$picker_app_release;
        j.f(viewData, "viewData");
        G g9 = this.disposableHandle;
        if (g9 != null) {
            g9.dispose();
        }
        ArrayList arrayList = new ArrayList();
        boolean z9 = viewData instanceof B0.c;
        if (z9) {
            B0.c cVar = (B0.c) viewData;
            z0.c cVar2 = cVar.f160a;
            boolean z10 = !TextUtils.isEmpty(cVar2.k()) && getSubLabelShowState(viewData);
            int layoutId = getLayoutId(z10);
            if (this.currentLayoutId != layoutId) {
                this.currentLayoutId = layoutId;
                adjustLayout(z10);
            }
            this.titleView.setText(cVar2.h());
            this.summaryView.setText(cVar2.k());
            this.extraTitleView.setText(cVar2.c());
            this.summaryView.setTextColor(cVar2.a() ? getSubLabelValueColor() : getSubLabelDescriptionColor());
            SelectableItem selectableItem = cVar.f162c;
            if (selectableItem != null && (gRegisterAfterChangeUpdateListener$picker_app_release = selectableItem.registerAfterChangeUpdateListener$picker_app_release(new I7.p(8, viewData, this))) != null) {
                arrayList.add(gRegisterAfterChangeUpdateListener$picker_app_release);
            }
        } else if (viewData instanceof B0.e) {
            this.titleView.setText(((B0.e) viewData).f167a.f9c);
        } else if (viewData instanceof B0.a) {
            TextView textView = this.titleView;
            textView.setText(textView.getContext().getResources().getText(R.string.title_all_apps));
        }
        if (z9) {
            ObservableProperty observableProperty = ((B0.c) viewData).f166g;
            p0.a.o(this.titleView, bindData$lambda$1(observableProperty), getHighlightColor());
            arrayList.add(observableProperty.bind$picker_app_release(new m(18, this)));
        }
        this.disposableHandle = new a(0, arrayList);
    }

    @Override // androidx.picker.features.composable.ComposableViewHolder
    public void onViewRecycled(View itemView) {
        j.f(itemView, "itemView");
        super.onViewRecycled(itemView);
        G g9 = this.disposableHandle;
        if (g9 != null) {
            g9.dispose();
        }
    }
}
