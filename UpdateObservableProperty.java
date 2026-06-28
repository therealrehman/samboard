package androidx.picker.features.observable;

import d.InterfaceC0463a;
import g7.InterfaceC0562b;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

/* JADX INFO: loaded from: classes.dex */
@InterfaceC0463a
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0011\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\b\u0012\u0004\u0012\u00028\u00010\u0003B3\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006¢\u0006\u0004\b\t\u0010\nJ\u0015\u0010\f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00028\u0000¢\u0006\u0004\b\f\u0010\rR \u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/picker/features/observable/UpdateObservableProperty;", "T", "R", "Landroidx/picker/features/observable/ObservableProperty;", "Landroidx/picker/features/observable/f;", "mutableState", "Lkotlin/Function1;", "LT6/p;", "onUpdated", "<init>", "(Landroidx/picker/features/observable/f;Lg7/b;)V", "newBase", "update", "(Ljava/lang/Object;)V", "Landroidx/picker/features/observable/f;", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public class UpdateObservableProperty<T, R> extends ObservableProperty<R> {
    private final f mutableState;

    public /* synthetic */ UpdateObservableProperty(f fVar, InterfaceC0562b interfaceC0562b, int i5, kotlin.jvm.internal.e eVar) {
        this(fVar, (i5 & 2) != 0 ? null : interfaceC0562b);
    }

    public final void update(T newBase) {
        this.mutableState.f7894e = newBase;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdateObservableProperty(f mutableState, InterfaceC0562b interfaceC0562b) {
        super(mutableState, interfaceC0562b);
        j.f(mutableState, "mutableState");
        this.mutableState = mutableState;
    }
}
