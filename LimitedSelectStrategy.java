package androidx.picker.controller.strategy;

import B0.h;
import B5.d;
import I7.p;
import T6.e;
import U6.B;
import U6.n;
import U6.o;
import U6.t;
import androidx.picker.loader.select.SelectableItem;
import d.InterfaceC0463a;
import f6.AbstractC0527a;
import g7.InterfaceC0562b;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import l0.a;
import l0.c;
import l8.C0698T;
import l8.C0709e;
import m0.b;
import z0.InterfaceC1280a;

/* JADX INFO: loaded from: classes.dex */
@InterfaceC0463a
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0017\u0018\u0000 &2\u00020\u0001:\u0001'B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0007\u001a\u00020\u0006H\u0014¢\u0006\u0004\b\u0007\u0010\bJ?\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\r0\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u001a\u0010\u000f\u001a\u0016\u0012\u0004\u0012\u00020\r\u0018\u00010\fj\n\u0012\u0004\u0012\u00020\r\u0018\u0001`\u000eH\u0010¢\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R<\u0010\u001e\u001a*\u0012\u001c\u0012\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\t0\u0019\u0012\u0004\u0012\u00020\u001c0\u0019j\u0002`\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u001b\u0010%\u001a\u00020 8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$¨\u0006("}, d2 = {"Landroidx/picker/controller/strategy/LimitedSelectStrategy;", "Landroidx/picker/controller/strategy/Strategy;", "Lo0/c;", "appPickerContext", "<init>", "(Lo0/c;)V", "", "getItemLimitedSize", "()I", "", "Lz0/a;", "dataList", "Ljava/util/Comparator;", "LB0/h;", "Lkotlin/Comparator;", "comparator", "convert$picker_app_release", "(Ljava/util/List;Ljava/util/Comparator;)Ljava/util/List;", "convert", "LC0/b;", "viewDataRepository", "LC0/b;", "Lm0/b;", "convertAppInfoDataTask", "Lm0/b;", "Lkotlin/Function1;", "Lz0/c;", "LB0/c;", "Lm0/d;", "Landroidx/picker/controller/strategy/task/ParseAppDataTaskProvider;", "parseAppDataTask", "Lg7/b;", "Lm0/c;", "limitedSelectableTask$delegate", "LT6/e;", "getLimitedSelectableTask", "()Lm0/c;", "limitedSelectableTask", "Companion", "l0/c", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public class LimitedSelectStrategy extends Strategy {
    private static final c Companion = new c();
    private static final int DEFAULT_LIMIT = 5;
    private final b convertAppInfoDataTask;

    /* JADX INFO: renamed from: limitedSelectableTask$delegate, reason: from kotlin metadata */
    private final e limitedSelectableTask;
    private final InterfaceC0562b parseAppDataTask;
    private final C0.b viewDataRepository;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LimitedSelectStrategy(o0.c appPickerContext) {
        super(appPickerContext);
        j.f(appPickerContext, "appPickerContext");
        C0.b bVar = (C0.b) appPickerContext.f12571f.getValue();
        this.viewDataRepository = bVar;
        this.convertAppInfoDataTask = new b(new a(1, bVar, C0.b.class, "createAppInfoViewData", "createAppInfoViewData(Landroidx/picker/model/AppInfoData;)Landroidx/picker/model/viewdata/AppInfoViewData;", 0, 5));
        int i5 = 15;
        this.parseAppDataTask = new p(i5, new a(1, bVar, C0.b.class, "createGroupTitleViewData", "createGroupTitleViewData(Landroidx/picker/model/appdata/GroupAppData;)Landroidx/picker/model/viewdata/GroupTitleViewData;", 0, 6), new l0.b(2, bVar, C0.b.class, "createCategoryViewData", "createCategoryViewData(Landroidx/picker/model/appdata/CategoryAppData;Ljava/util/List;)Landroidx/picker/model/viewdata/CategoryViewData;", 0, 2));
        this.limitedSelectableTask = AbstractC0527a.N(new d(24, this));
    }

    private final m0.c getLimitedSelectableTask() {
        return (m0.c) this.limitedSelectableTask.getValue();
    }

    @Override // androidx.picker.controller.strategy.Strategy
    public List<h> convert$picker_app_release(List<? extends InterfaceC1280a> dataList, Comparator<h> comparator) {
        int i5 = 1;
        j.f(dataList, "dataList");
        ArrayList arrayListB = ((m0.d) this.parseAppDataTask.invoke(new p(12, this, comparator))).b(dataList);
        m0.c limitedSelectableTask = getLimitedSelectableTask();
        limitedSelectableTask.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : arrayListB) {
            if (obj instanceof B0.c) {
                arrayList.add(obj);
            }
        }
        ArrayList<B0.c> arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            if (((B0.c) obj2).f162c != null) {
                arrayList2.add(obj2);
            }
        }
        ArrayList<T6.h> arrayList3 = new ArrayList(U6.p.c0(arrayList2, 10));
        for (B0.c cVar : arrayList2) {
            SelectableItem selectableItem = cVar.f162c;
            j.c(selectableItem);
            arrayList3.add(new T6.h(cVar, selectableItem));
        }
        if (!arrayList3.isEmpty()) {
            ArrayList arrayList4 = new ArrayList();
            for (Object obj3 : arrayList3) {
                T6.h hVar = (T6.h) obj3;
                B0.c cVar2 = (B0.c) hVar.f3315e;
                SelectableItem selectableItem2 = (SelectableItem) hVar.f3316f;
                if (!cVar2.f160a.d() && selectableItem2.isSelected()) {
                    arrayList4.add(obj3);
                }
            }
            ArrayList arrayList5 = new ArrayList(U6.p.c0(arrayList4, 10));
            Iterator it = arrayList4.iterator();
            while (it.hasNext()) {
                arrayList5.add(((B0.c) ((T6.h) it.next()).f3315e).f160a.o());
            }
            HashSet hashSet = new HashSet(B.a0(U6.p.c0(arrayList5, 12)));
            n.L0(arrayList5, hashSet);
            limitedSelectableTask.f12157b = hashSet;
            androidx.picker.features.composable.title.a aVar = limitedSelectableTask.f12158c;
            if (aVar != null) {
                aVar.dispose();
            }
            ArrayList arrayList6 = new ArrayList();
            for (T6.h hVar2 : arrayList3) {
                B0.c cVar3 = (B0.c) hVar2.f3315e;
                SelectableItem selectableItem3 = (SelectableItem) hVar2.f3316f;
                t.e0(arrayList6, o.X(selectableItem3.registerBeforeChangeUpdateListener$picker_app_release(new C0698T(i5, limitedSelectableTask)), selectableItem3.registerAfterChangeUpdateListener$picker_app_release(new C0709e(limitedSelectableTask, cVar3, selectableItem3, arrayList3))));
            }
            limitedSelectableTask.f12158c = new androidx.picker.features.composable.title.a(2, arrayList6);
        }
        return arrayListB;
    }

    public int getItemLimitedSize() {
        return 5;
    }
}
