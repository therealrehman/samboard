package androidx.picker.model;

import A0.b;
import U6.v;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;
import z0.C1281b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0012\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\b¨\u0006\t"}, d2 = {"androidx/picker/model/AppData$GroupAppDataBuilder", "", "LA0/b;", "", "key", "<init>", "(Ljava/lang/String;)V", "appData", "(LA0/b;)V", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public final class AppData$GroupAppDataBuilder {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7899a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f7900b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f7901c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public List f7902d;

    private AppData$GroupAppDataBuilder(b bVar) {
        this(bVar.f11a.f15266f);
        this.f7900b = bVar.f12b;
        List datas = bVar.f14d;
        j.f(datas, "datas");
        this.f7902d = datas;
    }

    public final b a() {
        String activityName = (6 & 2) != 0 ? "" : null;
        String packageName = this.f7899a;
        j.f(packageName, "packageName");
        j.f(activityName, "activityName");
        C1281b c1281b = new C1281b(0, packageName, activityName);
        String str = this.f7900b;
        if (str != null) {
            packageName = str;
        }
        String str2 = this.f7901c;
        return new b(c1281b, packageName, str2 != null ? str2 : "", this.f7902d);
    }

    public AppData$GroupAppDataBuilder(String key) {
        j.f(key, "key");
        this.f7899a = key;
        this.f7902d = v.f4893e;
    }
}
