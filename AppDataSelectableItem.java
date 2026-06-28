package androidx.picker.loader.select;

import d.InterfaceC0463a;
import g7.InterfaceC0562b;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import y0.C1207d;
import z0.c;

/* JADX INFO: loaded from: classes.dex */
@InterfaceC0463a
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0011\u0018\u00002\u00020\u0001:\u0001\u0002B)\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004¢\u0006\u0004\b\b\u0010\tB%\b\u0016\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0004\b\b\u0010\fJ\u0015\u0010\r\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/picker/loader/select/AppDataSelectableItem;", "Landroidx/picker/loader/select/SelectableItem;", "Ly0/d;", "mutableState", "Lkotlin/Function1;", "", "LT6/p;", "onUpdated", "<init>", "(Ly0/d;Lg7/b;)V", "Lz0/c;", "appInfoData", "(Lz0/c;Lg7/b;)V", "updateBase", "(Lz0/c;)V", "Ly0/d;", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public class AppDataSelectableItem extends SelectableItem {
    private final C1207d mutableState;

    public /* synthetic */ AppDataSelectableItem(C1207d c1207d, InterfaceC0562b interfaceC0562b, int i5, e eVar) {
        this(c1207d, (i5 & 2) != 0 ? null : interfaceC0562b);
    }

    public final void updateBase(c appInfoData) {
        j.f(appInfoData, "appInfoData");
        this.mutableState.f7894e = appInfoData;
    }

    private AppDataSelectableItem(C1207d c1207d, InterfaceC0562b interfaceC0562b) {
        super(c1207d, interfaceC0562b);
        this.mutableState = c1207d;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AppDataSelectableItem(c appInfoData, InterfaceC0562b onUpdated) {
        this(new C1207d(appInfoData), onUpdated);
        j.f(appInfoData, "appInfoData");
        j.f(onUpdated, "onUpdated");
    }
}
