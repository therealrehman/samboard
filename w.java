package b2;

import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.net.Uri;

/* JADX INFO: loaded from: classes.dex */
public final class w implements q {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f9753e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Resources f9754f;

    public /* synthetic */ w(Resources resources, int i5) {
        this.f9753e = i5;
        this.f9754f = resources;
    }

    @Override // b2.q
    public final p B(v vVar) {
        switch (this.f9753e) {
            case 0:
                return new C0412b(this.f9754f, vVar.b(Uri.class, AssetFileDescriptor.class));
            default:
                return new C0412b(this.f9754f, C0407A.f9699b);
        }
    }
}
