package androidx.picker.loader.select;

import androidx.picker.features.observable.ObservableProperty;
import androidx.picker.features.observable.b;
import d.InterfaceC0463a;
import g7.InterfaceC0562b;
import kotlin.Metadata;
import kotlin.jvm.internal.e;
import kotlin.jvm.internal.j;
import y0.h;
import y8.G;

/* JADX INFO: loaded from: classes.dex */
@InterfaceC0463a
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0017\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B-\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0003\u0012\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tJ#\u0010\u000e\u001a\u00020\u000b2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0005H\u0000¢\u0006\u0004\b\f\u0010\rJ#\u0010\u0010\u001a\u00020\u000b2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005H\u0000¢\u0006\u0004\b\u000f\u0010\rR\u0011\u0010\u0011\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Landroidx/picker/loader/select/SelectableItem;", "Landroidx/picker/features/observable/ObservableProperty;", "", "Landroidx/picker/features/observable/b;", "mutableState", "Lkotlin/Function1;", "LT6/p;", "onUpdated", "<init>", "(Landroidx/picker/features/observable/b;Lg7/b;)V", "onValueUpdateListener", "Ly8/G;", "registerBeforeChangeUpdateListener$picker_app_release", "(Lg7/b;)Ly8/G;", "registerBeforeChangeUpdateListener", "registerAfterChangeUpdateListener$picker_app_release", "registerAfterChangeUpdateListener", "isSelected", "()Z", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public class SelectableItem extends ObservableProperty<Boolean> {
    public /* synthetic */ SelectableItem(b bVar, InterfaceC0562b interfaceC0562b, int i5, e eVar) {
        this(bVar, (i5 & 2) != 0 ? null : interfaceC0562b);
    }

    public final boolean isSelected() {
        return getState().booleanValue();
    }

    public final G registerAfterChangeUpdateListener$picker_app_release(InterfaceC0562b onValueUpdateListener) {
        j.f(onValueUpdateListener, "onValueUpdateListener");
        return registerAfterChangeUpdateListener$picker_app_release(new h(0, onValueUpdateListener));
    }

    public final G registerBeforeChangeUpdateListener$picker_app_release(InterfaceC0562b onValueUpdateListener) {
        j.f(onValueUpdateListener, "onValueUpdateListener");
        return registerBeforeChangeUpdateListener$picker_app_release(new h(1, onValueUpdateListener));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SelectableItem(b mutableState, InterfaceC0562b interfaceC0562b) {
        super(mutableState, interfaceC0562b);
        j.f(mutableState, "mutableState");
    }
}
