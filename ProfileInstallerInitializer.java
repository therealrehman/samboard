package androidx.profileinstaller;

import C.o;
import F0.i;
import Z0.b;
import android.content.Context;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ProfileInstallerInitializer implements b {
    @Override // Z0.b
    public final List a() {
        return Collections.emptyList();
    }

    @Override // Z0.b
    public final Object b(Context context) {
        i.a(new o(2, this, context.getApplicationContext()));
        return new B1.b(5, false);
    }
}
