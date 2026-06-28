package androidx.picker.controller.strategy;

import B0.h;
import C0.b;
import androidx.picker.loader.select.AllAppsSelectableItem;
import androidx.picker.loader.select.CategorySelectableItem;
import d.InterfaceC0463a;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import o0.c;
import y0.C1210g;
import z0.InterfaceC1280a;

/* JADX INFO: loaded from: classes.dex */
@InterfaceC0463a
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b'\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J?\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u001a\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\n\u0018\u00010\tj\n\u0012\u0004\u0012\u00020\n\u0018\u0001`\u000bH ¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u0013\u001a\u00020\u0010H\u0000¢\u0006\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/picker/controller/strategy/Strategy;", "", "Lo0/c;", "appPickerContext", "<init>", "(Lo0/c;)V", "", "Lz0/a;", "dataList", "Ljava/util/Comparator;", "LB0/h;", "Lkotlin/Comparator;", "comparator", "convert$picker_app_release", "(Ljava/util/List;Ljava/util/Comparator;)Ljava/util/List;", "convert", "LT6/p;", "clear$picker_app_release", "()V", "clear", "Lo0/c;", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public abstract class Strategy {
    private final c appPickerContext;

    public Strategy(c appPickerContext) {
        j.f(appPickerContext, "appPickerContext");
        this.appPickerContext = appPickerContext;
    }

    public final void clear$picker_app_release() {
        C1210g c1210g = ((b) this.appPickerContext.f12571f.getValue()).f336b;
        AllAppsSelectableItem allAppsSelectableItem = c1210g.f14967b;
        if (allAppsSelectableItem != null) {
            allAppsSelectableItem.dispose();
        }
        c1210g.f14967b = null;
        LinkedHashMap linkedHashMap = c1210g.f14968c;
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            ((CategorySelectableItem) ((Map.Entry) it.next()).getValue()).dispose();
        }
        linkedHashMap.clear();
    }

    public abstract List<h> convert$picker_app_release(List<? extends InterfaceC1280a> dataList, Comparator<h> comparator);
}
