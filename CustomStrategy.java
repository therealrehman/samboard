package androidx.picker.features.composable.custom;

import A8.l;
import B0.h;
import T6.e;
import d.InterfaceC0463a;
import f6.AbstractC0527a;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import q0.c;
import r0.a;

/* JADX INFO: loaded from: classes.dex */
@InterfaceC0463a
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H$¢\u0006\u0004\b\u0006\u0010\u0007J\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fR!\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0007R!\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u000e\u001a\u0004\b\u0012\u0010\u0007¨\u0006\u0014"}, d2 = {"Landroidx/picker/features/composable/custom/CustomStrategy;", "Lq0/c;", "<init>", "()V", "", "", "getCustomFrameList", "()Ljava/util/List;", "LB0/h;", "viewData", "Lq0/a;", "selectComposableType", "(LB0/h;)Lq0/a;", "customWidgetList$delegate", "LT6/e;", "getCustomWidgetList", "customWidgetList", "widgetFrameList$delegate", "getWidgetFrameList", "widgetFrameList", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public abstract class CustomStrategy extends c {

    /* JADX INFO: renamed from: customWidgetList$delegate, reason: from kotlin metadata */
    private final e customWidgetList = AbstractC0527a.N(new a(this, 0));

    /* JADX INFO: renamed from: widgetFrameList$delegate, reason: from kotlin metadata */
    private final e widgetFrameList = AbstractC0527a.N(new a(this, 1));

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Object> getCustomWidgetList() {
        return (List) this.customWidgetList.getValue();
    }

    public abstract List<Object> getCustomFrameList();

    @Override // androidx.picker.features.composable.ComposableStrategy
    public List<Object> getWidgetFrameList() {
        return (List) this.widgetFrameList.getValue();
    }

    @Override // q0.c, androidx.picker.features.composable.ComposableStrategy
    public q0.a selectComposableType(h viewData) {
        j.f(viewData, "viewData");
        if (!(viewData instanceof B0.c)) {
            return super.selectComposableType(viewData);
        }
        Iterator<T> it = getCustomWidgetList().iterator();
        if (!it.hasNext()) {
            return super.selectComposableType(viewData);
        }
        l.z(it.next());
        throw null;
    }
}
