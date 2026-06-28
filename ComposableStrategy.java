package androidx.picker.features.composable;

import B0.h;
import d.InterfaceC0463a;
import java.util.List;
import kotlin.Metadata;
import q0.a;

/* JADX INFO: loaded from: classes.dex */
@InterfaceC0463a
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\n\ba\u0018\u00002\u00020\u0001J\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u00078&X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u00078&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u00078&X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\tR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u00078&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Landroidx/picker/features/composable/ComposableStrategy;", "", "LB0/h;", "viewData", "Lq0/a;", "selectComposableType", "(LB0/h;)Lq0/a;", "", "getLeftFrameList", "()Ljava/util/List;", "leftFrameList", "getIconFrameList", "iconFrameList", "getTitleFrameList", "titleFrameList", "getWidgetFrameList", "widgetFrameList", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public interface ComposableStrategy {
    List<Object> getIconFrameList();

    List<Object> getLeftFrameList();

    List<Object> getTitleFrameList();

    List<Object> getWidgetFrameList();

    a selectComposableType(h viewData);
}
