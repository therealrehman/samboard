package androidx.savedstate;

import A8.l;
import U0.c;
import U0.e;
import U0.g;
import android.os.Bundle;
import androidx.lifecycle.EnumC0270l;
import androidx.lifecycle.InterfaceC0275q;
import androidx.lifecycle.InterfaceC0276s;
import androidx.lifecycle.K;
import androidx.lifecycle.Q;
import androidx.lifecycle.V;
import androidx.lifecycle.W;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Landroidx/savedstate/Recreator;", "Landroidx/lifecycle/q;", "U0/a", "savedstate_release"}, k = 1, mv = {1, 8, 0})
public final class Recreator implements InterfaceC0275q {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final g f9346e;

    public Recreator(g owner) {
        j.f(owner, "owner");
        this.f9346e = owner;
    }

    @Override // androidx.lifecycle.InterfaceC0275q
    public final void a(InterfaceC0276s interfaceC0276s, EnumC0270l enumC0270l) {
        if (enumC0270l != EnumC0270l.ON_CREATE) {
            throw new AssertionError("Next event must be ON_CREATE");
        }
        interfaceC0276s.getLifecycle().b(this);
        g gVar = this.f9346e;
        Bundle bundleA = gVar.getSavedStateRegistry().a("androidx.savedstate.Restarter");
        if (bundleA == null) {
            return;
        }
        ArrayList<String> stringArrayList = bundleA.getStringArrayList("classes_to_restore");
        if (stringArrayList == null) {
            throw new IllegalStateException("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
        }
        for (String str : stringArrayList) {
            try {
                Class<? extends U> clsAsSubclass = Class.forName(str, false, Recreator.class.getClassLoader()).asSubclass(c.class);
                j.e(clsAsSubclass, "{\n                Class.…class.java)\n            }");
                try {
                    Constructor declaredConstructor = clsAsSubclass.getDeclaredConstructor(null);
                    declaredConstructor.setAccessible(true);
                    try {
                        Object objNewInstance = declaredConstructor.newInstance(null);
                        j.e(objNewInstance, "{\n                constr…wInstance()\n            }");
                        if (!(gVar instanceof W)) {
                            throw new IllegalStateException("Internal error: OnRecreation should be registered only on components that implement ViewModelStoreOwner".toString());
                        }
                        V viewModelStore = ((W) gVar).getViewModelStore();
                        e savedStateRegistry = gVar.getSavedStateRegistry();
                        viewModelStore.getClass();
                        LinkedHashMap linkedHashMap = viewModelStore.f7808a;
                        for (String key : new HashSet(linkedHashMap.keySet())) {
                            j.f(key, "key");
                            Q q6 = (Q) linkedHashMap.get(key);
                            j.c(q6);
                            K.a(q6, savedStateRegistry, gVar.getLifecycle());
                        }
                        if (!new HashSet(linkedHashMap.keySet()).isEmpty()) {
                            savedStateRegistry.e();
                        }
                    } catch (Exception e3) {
                        throw new RuntimeException(l.r("Failed to instantiate ", str), e3);
                    }
                } catch (NoSuchMethodException e10) {
                    throw new IllegalStateException("Class " + clsAsSubclass.getSimpleName() + " must have default constructor in order to be automatically recreated", e10);
                }
            } catch (ClassNotFoundException e11) {
                throw new RuntimeException(l.t("Class ", str, " wasn't found"), e11);
            }
        }
    }
}
