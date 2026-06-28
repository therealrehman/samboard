package androidx.picker.loader.select;

import U6.n;
import U6.p;
import androidx.picker.features.observable.a;
import d.InterfaceC0463a;
import g7.InterfaceC0562b;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import y0.C1204a;
import y0.C1205b;
import y0.C1206c;
import y8.G;

/* JADX INFO: loaded from: classes.dex */
@InterfaceC0463a
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010!\n\u0002\b\u0004\b\u0011\u0018\u00002\u00020\u00012\u00020\u0002B+\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\r\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\r\u0010\fJ\u000f\u0010\u000e\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u000e\u0010\fJ\u001b\u0010\u0010\u001a\u00020\u00072\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u0013R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Landroidx/picker/loader/select/AllAppsSelectableItem;", "Landroidx/picker/loader/select/SelectableItem;", "Ly8/G;", "", "selectableItemList", "Lkotlin/Function1;", "", "LT6/p;", "onUpdated", "<init>", "(Ljava/util/List;Lg7/b;)V", "bindSelectableItemList", "()V", "updateAllAppsStatus", "dispose", "dataList", "reset", "(Ljava/util/List;)V", "", "Ljava/util/List;", "disposableHandle", "Ly8/G;", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public class AllAppsSelectableItem extends SelectableItem implements G {
    private G disposableHandle;
    private final List<SelectableItem> selectableItemList;

    public /* synthetic */ AllAppsSelectableItem(List list, InterfaceC0562b interfaceC0562b, int i5, e eVar) {
        this(list, (i5 & 2) != 0 ? C1205b.f14957f : interfaceC0562b);
    }

    private final void bindSelectableItemList() {
        G g9 = this.disposableHandle;
        if (g9 != null) {
            g9.dispose();
        }
        G gRegisterAfterChangeUpdateListener$picker_app_release = registerAfterChangeUpdateListener$picker_app_release(new C1206c(this, 0));
        List<SelectableItem> list = this.selectableItemList;
        ArrayList arrayList = new ArrayList(p.c0(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((SelectableItem) it.next()).registerAfterChangeUpdateListener$picker_app_release(new C1206c(this, 1)));
        }
        this.disposableHandle = new C1204a(gRegisterAfterChangeUpdateListener$picker_app_release, arrayList, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bindSelectableItemList$lambda$4(G disposable1, List disposableHandleList) {
        j.f(disposable1, "$disposable1");
        j.f(disposableHandleList, "$disposableHandleList");
        disposable1.dispose();
        Iterator it = disposableHandleList.iterator();
        while (it.hasNext()) {
            ((G) it.next()).dispose();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateAllAppsStatus() {
        if (this.selectableItemList.isEmpty()) {
            return;
        }
        List<SelectableItem> list = this.selectableItemList;
        boolean z9 = true;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (!((SelectableItem) it.next()).isSelected()) {
                    z9 = false;
                    break;
                }
            }
        }
        setValueSilence$picker_app_release(Boolean.valueOf(z9));
    }

    @Override // y8.G
    public void dispose() {
        G g9 = this.disposableHandle;
        if (g9 != null) {
            g9.dispose();
        }
    }

    public final void reset(List<? extends SelectableItem> dataList) {
        j.f(dataList, "dataList");
        List<SelectableItem> list = this.selectableItemList;
        list.clear();
        list.addAll(dataList);
        bindSelectableItemList();
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public AllAppsSelectableItem(List<? extends SelectableItem> selectableItemList, InterfaceC0562b onUpdated) {
        j.f(selectableItemList, "selectableItemList");
        j.f(onUpdated, "onUpdated");
        boolean z9 = true;
        if (!selectableItemList.isEmpty()) {
            Iterator<T> it = selectableItemList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (!((SelectableItem) it.next()).isSelected()) {
                    z9 = false;
                    break;
                }
            }
        }
        super(new a(z9), onUpdated);
        this.selectableItemList = n.O0(selectableItemList);
        bindSelectableItemList();
    }
}
