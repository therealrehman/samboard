package b2;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: b2.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0412b implements p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9705a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f9706b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Object f9707c;

    public /* synthetic */ C0412b(int i5, Object obj, Object obj2) {
        this.f9705a = i5;
        this.f9706b = obj;
        this.f9707c = obj2;
    }

    @Override // b2.p
    public final o a(Object obj, int i5, int i7, V1.h hVar) {
        o oVarA;
        Uri uri;
        switch (this.f9705a) {
            case 0:
                Uri uri2 = (Uri) obj;
                return new o(new q2.b(uri2), ((InterfaceC0411a) this.f9707c).l((AssetManager) this.f9706b, uri2.toString().substring(22)));
            case 1:
                List list = (List) this.f9706b;
                int size = list.size();
                ArrayList arrayList = new ArrayList(size);
                V1.e eVar = null;
                for (int i9 = 0; i9 < size; i9++) {
                    p pVar = (p) list.get(i9);
                    if (pVar.b(obj) && (oVarA = pVar.a(obj, i5, i7, hVar)) != null) {
                        arrayList.add(oVarA.f9734c);
                        eVar = oVarA.f9732a;
                    }
                }
                if (arrayList.isEmpty() || eVar == null) {
                    return null;
                }
                return new o(eVar, new t(arrayList, (K.d) this.f9707c));
            default:
                Integer num = (Integer) obj;
                Resources resources = (Resources) this.f9707c;
                try {
                    uri = Uri.parse("android.resource://" + resources.getResourcePackageName(num.intValue()) + '/' + resources.getResourceTypeName(num.intValue()) + '/' + resources.getResourceEntryName(num.intValue()));
                    break;
                } catch (Resources.NotFoundException e3) {
                    if (Log.isLoggable("ResourceLoader", 5)) {
                        Log.w("ResourceLoader", "Received invalid resource id: " + num, e3);
                    }
                    uri = null;
                }
                if (uri == null) {
                    return null;
                }
                return ((p) this.f9706b).a(uri, i5, i7, hVar);
        }
    }

    @Override // b2.p
    public final boolean b(Object obj) {
        switch (this.f9705a) {
            case 0:
                Uri uri = (Uri) obj;
                return "file".equals(uri.getScheme()) && !uri.getPathSegments().isEmpty() && "android_asset".equals(uri.getPathSegments().get(0));
            case 1:
                Iterator it = ((List) this.f9706b).iterator();
                while (it.hasNext()) {
                    if (((p) it.next()).b(obj)) {
                        return true;
                    }
                }
                return false;
            default:
                return true;
        }
    }

    public String toString() {
        switch (this.f9705a) {
            case 1:
                return "MultiModelLoader{modelLoaders=" + Arrays.toString(((List) this.f9706b).toArray()) + '}';
            default:
                return super.toString();
        }
    }

    public C0412b(Resources resources, p pVar) {
        this.f9705a = 2;
        this.f9707c = resources;
        this.f9706b = pVar;
    }
}
