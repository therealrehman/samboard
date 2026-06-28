package b2;

import android.content.res.Resources;
import android.net.Uri;
import e2.C0486c;
import j2.InterfaceC0614a;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class x implements q, InterfaceC0614a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Resources f9755e;

    public /* synthetic */ x(Resources resources) {
        this.f9755e = resources;
    }

    @Override // b2.q
    public p B(v vVar) {
        return new C0412b(this.f9755e, vVar.b(Uri.class, InputStream.class));
    }

    @Override // j2.InterfaceC0614a
    public X1.B f(X1.B b3, V1.h hVar) {
        if (b3 == null) {
            return null;
        }
        return new C0486c(this.f9755e, b3);
    }
}
