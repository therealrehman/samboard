package b2;

import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import java.net.URL;

/* JADX INFO: loaded from: classes.dex */
public final class y implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9756a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final p f9757b;

    public /* synthetic */ y(p pVar, int i5) {
        this.f9756a = i5;
        this.f9757b = pVar;
    }

    @Override // b2.p
    public final o a(Object obj, int i5, int i7, V1.h hVar) {
        Uri uriFromFile;
        switch (this.f9756a) {
            case 0:
                String str = (String) obj;
                if (TextUtils.isEmpty(str)) {
                    uriFromFile = null;
                } else if (str.charAt(0) == '/') {
                    uriFromFile = Uri.fromFile(new File(str));
                } else {
                    Uri uri = Uri.parse(str);
                    uriFromFile = uri.getScheme() == null ? Uri.fromFile(new File(str)) : uri;
                }
                if (uriFromFile == null) {
                    return null;
                }
                p pVar = this.f9757b;
                if (pVar.b(uriFromFile)) {
                    return pVar.a(uriFromFile, i5, i7, hVar);
                }
                return null;
            default:
                return this.f9757b.a(new C0416f((URL) obj), i5, i7, hVar);
        }
    }

    @Override // b2.p
    public final /* bridge */ /* synthetic */ boolean b(Object obj) {
        switch (this.f9756a) {
            case 0:
                break;
            default:
                break;
        }
        return true;
    }
}
