package androidx.activity;

import android.view.inputmethod.InputMethodManager;
import androidx.lifecycle.EnumC0270l;
import androidx.lifecycle.InterfaceC0275q;
import androidx.lifecycle.InterfaceC0276s;

/* JADX INFO: loaded from: classes.dex */
final class ImmLeaksCleaner implements InterfaceC0275q {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f6085e;

    @Override // androidx.lifecycle.InterfaceC0275q
    public final void a(InterfaceC0276s interfaceC0276s, EnumC0270l enumC0270l) {
        if (enumC0270l != EnumC0270l.ON_DESTROY) {
            return;
        }
        if (f6085e == 0) {
            try {
                f6085e = 2;
                InputMethodManager.class.getDeclaredField("mServedView").setAccessible(true);
                InputMethodManager.class.getDeclaredField("mNextServedView").setAccessible(true);
                InputMethodManager.class.getDeclaredField("mH").setAccessible(true);
                f6085e = 1;
            } catch (NoSuchFieldException unused) {
            }
        }
        if (f6085e == 1) {
            throw null;
        }
    }
}
