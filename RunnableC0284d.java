package androidx.picker.widget;

import androidx.picker.loader.select.AllAppsSelectableItem;
import androidx.picker.loader.select.SelectableItem;
import java.util.ArrayList;
import java.util.List;
import y0.C1210g;

/* JADX INFO: renamed from: androidx.picker.widget.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC0284d implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f8291e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ AbstractC0288h f8292f;

    public /* synthetic */ RunnableC0284d(AbstractC0288h abstractC0288h, int i5) {
        this.f8291e = i5;
        this.f8292f = abstractC0288h;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SelectableItem selectableItem;
        switch (this.f8291e) {
            case 0:
                this.f8292f.f8378e.notifyDataSetChanged();
                break;
            default:
                AbstractC0288h abstractC0288h = this.f8292f;
                abstractC0288h.getClass();
                ArrayList arrayList = new ArrayList();
                for (B0.h hVar : (List) abstractC0288h.h.f797g) {
                    if ((hVar instanceof B0.c) && (selectableItem = ((B0.c) hVar).f162c) != null) {
                        arrayList.add(selectableItem);
                    }
                }
                C1210g c1210g = abstractC0288h.f8381i;
                c1210g.getClass();
                AllAppsSelectableItem allAppsSelectableItem = c1210g.f14967b;
                if (allAppsSelectableItem != null) {
                    allAppsSelectableItem.reset(arrayList);
                }
                break;
        }
    }
}
