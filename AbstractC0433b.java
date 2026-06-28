package c0;

import android.content.Context;
import androidx.media.MediaBrowserServiceCompat;
import e2.C;
import h6.AbstractC0582a;
import java.util.ArrayList;
import java.util.Map;
import k6.C0651a;

/* JADX INFO: renamed from: c0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0433b implements g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f9805a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f9806b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f9807c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Object f9808d;

    public AbstractC0433b(Context context, b6.b bVar) {
        this.f9805a = context.getApplicationContext();
        this.f9806b = bVar;
        this.f9808d = C.o();
        this.f9807c = C0651a.d(context, bVar);
    }

    public static int a(Map map) {
        return "dl".equals((String) map.get("t")) ? 1 : 2;
    }

    public void b(Map map) {
        ((C0651a) this.f9807c).e(new h6.b((String) map.get("t"), Long.parseLong((String) map.get("ts")), AbstractC0582a.w0(d(map), 1), a(map)));
    }

    public abstract int c(Map map);

    public abstract Map d(Map map);

    public AbstractC0433b(MediaBrowserServiceCompat mediaBrowserServiceCompat) {
        this.f9808d = mediaBrowserServiceCompat;
        this.f9805a = new ArrayList();
    }
}
