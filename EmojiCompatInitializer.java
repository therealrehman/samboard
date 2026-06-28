package androidx.emoji2.text;

import android.content.Context;
import android.os.Looper;
import androidx.lifecycle.AbstractC0272n;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.InterfaceC0276s;
import androidx.lifecycle.ProcessLifecycleInitializer;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EmojiCompatInitializer implements Z0.b {
    @Override // Z0.b
    public final List a() {
        return Collections.singletonList(ProcessLifecycleInitializer.class);
    }

    @Override // Z0.b
    public final /* bridge */ /* synthetic */ Object b(Context context) {
        c(context);
        return Boolean.TRUE;
    }

    public final void c(Context context) {
        Object objB;
        n nVar = new n(new V5.a(context, 1));
        nVar.f7476b = 1;
        if (i.f7447j == null) {
            synchronized (i.f7446i) {
                try {
                    if (i.f7447j == null) {
                        i.f7447j = new i(nVar);
                    }
                } finally {
                }
            }
        }
        Z0.a aVarC = Z0.a.c(context);
        aVarC.getClass();
        synchronized (Z0.a.f5858e) {
            try {
                objB = aVarC.f5859a.get(ProcessLifecycleInitializer.class);
                if (objB == null) {
                    objB = aVarC.b(ProcessLifecycleInitializer.class, new HashSet());
                }
            } finally {
            }
        }
        final AbstractC0272n lifecycle = ((InterfaceC0276s) objB).getLifecycle();
        lifecycle.a(new DefaultLifecycleObserver() { // from class: androidx.emoji2.text.EmojiCompatInitializer.1
            @Override // androidx.lifecycle.DefaultLifecycleObserver
            public final void onResume(InterfaceC0276s interfaceC0276s) {
                EmojiCompatInitializer.this.getClass();
                b.a(Looper.getMainLooper()).postDelayed(new J6.a(2), 500L);
                lifecycle.b(this);
            }
        });
    }
}
