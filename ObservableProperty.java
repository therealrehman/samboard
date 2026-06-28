package androidx.picker.features.observable;

import com.samsung.android.keyscafe.honeytea.model.HoneyTeaDB;
import d.InterfaceC0463a;
import g7.InterfaceC0562b;
import g7.InterfaceC0563c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.w;
import m7.u;
import y8.G;

/* JADX INFO: loaded from: classes.dex */
@InterfaceC0463a
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010!\n\u0002\b\u0007\b\u0017\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B-\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u000f\u001a\u00020\u00062\u0006\u0010\n\u001a\u00028\u00002\u0006\u0010\u000b\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u0017\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00028\u0000H\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u0015\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00028\u0000¢\u0006\u0004\b\u0015\u0010\u0013J'\u0010\u001a\u001a\u00020\u00172\u0016\b\u0002\u0010\u0016\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0000¢\u0006\u0004\b\u0018\u0010\u0019J)\u0010\u001f\u001a\u00020\u00172\u0018\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u001bH\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ)\u0010!\u001a\u00020\u00172\u0018\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u001bH\u0000¢\u0006\u0004\b \u0010\u001eJ&\u0010'\u001a\u00028\u00002\b\u0010\"\u001a\u0004\u0018\u00010\u00022\n\u0010$\u001a\u0006\u0012\u0002\b\u00030#H\u0080\u0002¢\u0006\u0004\b%\u0010&J.\u0010\u0015\u001a\u00020\u00062\b\u0010\"\u001a\u0004\u0018\u00010\u00022\n\u0010$\u001a\u0006\u0012\u0002\b\u00030#2\u0006\u0010\u0011\u001a\u00028\u0000H\u0080\u0002¢\u0006\u0004\b(\u0010)R\"\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010*R+\u00101\u001a\u00028\u00002\u0006\u0010+\u001a\u00028\u00008D@DX\u0084\u008e\u0002¢\u0006\u0012\n\u0004\b,\u0010-\u001a\u0004\b.\u0010/\"\u0004\b0\u0010\u0013R,\u00103\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00060\u001b028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00104R,\u00105\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\f0\u001b028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00104R@\u00106\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0014\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00058\u0002@BX\u0082\u000e¢\u0006\f\n\u0004\b6\u0010*\"\u0004\b7\u00108¨\u00069"}, d2 = {"Landroidx/picker/features/observable/ObservableProperty;", "T", "", "Landroidx/picker/features/observable/b;", "mutableState", "Lkotlin/Function1;", "LT6/p;", "onUpdated", "<init>", "(Landroidx/picker/features/observable/b;Lg7/b;)V", "oldValue", "newValue", "", "beforeChange", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "afterChange", "(Ljava/lang/Object;Ljava/lang/Object;)V", HoneyTeaDB.DB_COLUMN_NAME, "setValueSilence$picker_app_release", "(Ljava/lang/Object;)V", "setValueSilence", "setValue", "callback", "Ly8/G;", "bind$picker_app_release", "(Lg7/b;)Ly8/G;", "bind", "Lkotlin/Function2;", "onValueUpdateListener", "registerBeforeChangeUpdateListener$picker_app_release", "(Lg7/c;)Ly8/G;", "registerBeforeChangeUpdateListener", "registerAfterChangeUpdateListener$picker_app_release", "registerAfterChangeUpdateListener", "thisRef", "Lm7/u;", "prop", "getValue$picker_app_release", "(Ljava/lang/Object;Lm7/u;)Ljava/lang/Object;", "getValue", "setValue$picker_app_release", "(Ljava/lang/Object;Lm7/u;Ljava/lang/Object;)V", "Lg7/b;", "<set-?>", "state$delegate", "Landroidx/picker/features/observable/b;", "getState", "()Ljava/lang/Object;", "setState", "state", "", "onAfterChangeListenerList", "Ljava/util/List;", "onBeforeChangeListenerList", "onBindCallback", "setOnBindCallback", "(Lg7/b;)V", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public class ObservableProperty<T> {
    static final /* synthetic */ u[] $$delegatedProperties = {w.f11853a.e(new n(ObservableProperty.class, "state", "getState()Ljava/lang/Object;"))};
    private final List<InterfaceC0563c> onAfterChangeListenerList;
    private final List<InterfaceC0563c> onBeforeChangeListenerList;
    private InterfaceC0562b onBindCallback;
    private final InterfaceC0562b onUpdated;

    /* JADX INFO: renamed from: state$delegate, reason: from kotlin metadata */
    private final b state;

    public ObservableProperty(b mutableState, InterfaceC0562b interfaceC0562b) {
        j.f(mutableState, "mutableState");
        this.onUpdated = interfaceC0562b;
        this.state = mutableState;
        this.onAfterChangeListenerList = new ArrayList();
        this.onBeforeChangeListenerList = new ArrayList();
    }

    private final void afterChange(T oldValue, T newValue) {
        Iterator<T> it = this.onAfterChangeListenerList.iterator();
        while (it.hasNext()) {
            ((InterfaceC0563c) it.next()).invoke(oldValue, newValue);
        }
    }

    private final boolean beforeChange(T oldValue, T newValue) {
        List<InterfaceC0563c> list = this.onBeforeChangeListenerList;
        if ((list instanceof Collection) && list.isEmpty()) {
            return true;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (!((Boolean) ((InterfaceC0563c) it.next()).invoke(oldValue, newValue)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void bind$lambda$0(ObservableProperty this$0, InterfaceC0562b interfaceC0562b) {
        j.f(this$0, "this$0");
        if (j.a(this$0.onBindCallback, interfaceC0562b)) {
            this$0.setOnBindCallback(null);
        }
    }

    public static /* synthetic */ G bind$picker_app_release$default(ObservableProperty observableProperty, InterfaceC0562b interfaceC0562b, int i5, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: bind");
        }
        if ((i5 & 1) != 0) {
            interfaceC0562b = null;
        }
        return observableProperty.bind$picker_app_release(interfaceC0562b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void registerAfterChangeUpdateListener$lambda$2(ObservableProperty this$0, InterfaceC0563c onValueUpdateListener) {
        j.f(this$0, "this$0");
        j.f(onValueUpdateListener, "$onValueUpdateListener");
        this$0.onAfterChangeListenerList.remove(onValueUpdateListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void registerBeforeChangeUpdateListener$lambda$1(ObservableProperty this$0, InterfaceC0563c onValueUpdateListener) {
        j.f(this$0, "this$0");
        j.f(onValueUpdateListener, "$onValueUpdateListener");
        this$0.onBeforeChangeListenerList.remove(onValueUpdateListener);
    }

    private final void setOnBindCallback(InterfaceC0562b interfaceC0562b) {
        this.onBindCallback = interfaceC0562b;
        if (interfaceC0562b != null) {
            interfaceC0562b.invoke(getState());
        }
    }

    public final G bind$picker_app_release(InterfaceC0562b callback) {
        setOnBindCallback(callback);
        return new c(0, this, callback);
    }

    public final T getState() {
        return (T) this.state.a($$delegatedProperties[0]);
    }

    public final T getValue$picker_app_release(Object thisRef, u prop) {
        j.f(prop, "prop");
        return getState();
    }

    public final G registerAfterChangeUpdateListener$picker_app_release(InterfaceC0563c onValueUpdateListener) {
        j.f(onValueUpdateListener, "onValueUpdateListener");
        this.onAfterChangeListenerList.add(onValueUpdateListener);
        return new d(this, onValueUpdateListener, 0);
    }

    public final G registerBeforeChangeUpdateListener$picker_app_release(InterfaceC0563c onValueUpdateListener) {
        j.f(onValueUpdateListener, "onValueUpdateListener");
        this.onBeforeChangeListenerList.add(onValueUpdateListener);
        return new d(this, onValueUpdateListener, 1);
    }

    public final void setState(T t8) {
        this.state.c(t8, $$delegatedProperties[0]);
    }

    public final void setValue(T value) {
        if (!j.a(getState(), value)) {
            if (!beforeChange(getState(), value)) {
                return;
            }
            T state = getState();
            setState(value);
            afterChange(state, value);
            InterfaceC0562b interfaceC0562b = this.onUpdated;
            if (interfaceC0562b != null) {
                interfaceC0562b.invoke(value);
            }
        }
        InterfaceC0562b interfaceC0562b2 = this.onBindCallback;
        if (interfaceC0562b2 != null) {
            interfaceC0562b2.invoke(value);
        }
    }

    public final void setValue$picker_app_release(Object thisRef, u prop, T value) {
        j.f(prop, "prop");
        setValue(value);
    }

    public final void setValueSilence$picker_app_release(T value) {
        setState(value);
        setValue(value);
    }

    public /* synthetic */ ObservableProperty(b bVar, InterfaceC0562b interfaceC0562b, int i5, kotlin.jvm.internal.e eVar) {
        this(bVar, (i5 & 2) != 0 ? null : interfaceC0562b);
    }
}
