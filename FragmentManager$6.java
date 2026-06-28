package androidx.fragment.app;

import androidx.lifecycle.EnumC0270l;
import androidx.lifecycle.InterfaceC0275q;
import androidx.lifecycle.InterfaceC0276s;

/* JADX INFO: loaded from: classes.dex */
class FragmentManager$6 implements InterfaceC0275q {
    @Override // androidx.lifecycle.InterfaceC0275q
    public final void a(InterfaceC0276s interfaceC0276s, EnumC0270l enumC0270l) {
        if (enumC0270l == EnumC0270l.ON_START || enumC0270l == EnumC0270l.ON_DESTROY) {
            throw null;
        }
    }
}
