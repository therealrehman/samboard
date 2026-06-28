package androidx.picker.controller.strategy;

import B0.h;
import I7.p;
import U6.n;
import d.InterfaceC0463a;
import g7.InterfaceC0562b;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import m0.a;
import m0.b;
import m0.d;
import o0.c;
import z0.InterfaceC1280a;

/* JADX INFO: loaded from: classes.dex */
@InterfaceC0463a
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J?\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u001a\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\n\u0018\u00010\tj\n\u0012\u0004\u0012\u00020\n\u0018\u0001`\u000bH\u0010¢\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R<\u0010\u001b\u001a*\u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00060\u0016\u0012\u0004\u0012\u00020\u00190\u0016j\u0002`\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001e\u001a\u00020\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001f¨\u0006 "}, d2 = {"Landroidx/picker/controller/strategy/AllSelectStrategy;", "Landroidx/picker/controller/strategy/Strategy;", "Lo0/c;", "appPickerContext", "<init>", "(Lo0/c;)V", "", "Lz0/a;", "dataList", "Ljava/util/Comparator;", "LB0/h;", "Lkotlin/Comparator;", "comparator", "convert$picker_app_release", "(Ljava/util/List;Ljava/util/Comparator;)Ljava/util/List;", "convert", "LC0/b;", "viewDataRepository", "LC0/b;", "Lm0/b;", "convertAppInfoDataTask", "Lm0/b;", "Lkotlin/Function1;", "Lz0/c;", "LB0/c;", "Lm0/d;", "Landroidx/picker/controller/strategy/task/ParseAppDataTaskProvider;", "parseAppDataTask", "Lg7/b;", "Lm0/a;", "addAllAppsTask", "Lm0/a;", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public final class AllSelectStrategy extends Strategy {
    private final a addAllAppsTask;
    private final b convertAppInfoDataTask;
    private final InterfaceC0562b parseAppDataTask;
    private final C0.b viewDataRepository;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AllSelectStrategy(c appPickerContext) {
        super(appPickerContext);
        j.f(appPickerContext, "appPickerContext");
        C0.b bVar = (C0.b) appPickerContext.f12571f.getValue();
        this.viewDataRepository = bVar;
        this.convertAppInfoDataTask = new b(new l0.a(1, bVar, C0.b.class, "createAppInfoViewData", "createAppInfoViewData(Landroidx/picker/model/AppInfoData;)Landroidx/picker/model/viewdata/AppInfoViewData;", 0, 1));
        this.parseAppDataTask = new p(15, new l0.a(1, bVar, C0.b.class, "createGroupTitleViewData", "createGroupTitleViewData(Landroidx/picker/model/appdata/GroupAppData;)Landroidx/picker/model/viewdata/GroupTitleViewData;", 0, 2), new l0.b(2, bVar, C0.b.class, "createCategoryViewData", "createCategoryViewData(Landroidx/picker/model/appdata/CategoryAppData;Ljava/util/List;)Landroidx/picker/model/viewdata/CategoryViewData;", 0, 0));
        this.addAllAppsTask = new a(new l0.a(1, bVar, C0.b.class, "createAllAppsViewData", "createAllAppsViewData(Ljava/util/List;)Landroidx/picker/model/viewdata/AllAppsViewData;", 0, 0));
    }

    @Override // androidx.picker.controller.strategy.Strategy
    public List<h> convert$picker_app_release(List<? extends InterfaceC1280a> dataList, Comparator<h> comparator) {
        j.f(dataList, "dataList");
        ArrayList arrayListB = ((d) this.parseAppDataTask.invoke(new p(10, this, comparator))).b(dataList);
        a aVar = this.addAllAppsTask;
        aVar.getClass();
        ArrayList arrayListO0 = n.O0(arrayListB);
        ArrayList arrayList = new ArrayList();
        for (Object obj : arrayListO0) {
            if (obj instanceof B0.c) {
                arrayList.add(obj);
            }
        }
        arrayListO0.add(0, (B0.a) aVar.f12153a.invoke(arrayList));
        return arrayListO0;
    }
}
