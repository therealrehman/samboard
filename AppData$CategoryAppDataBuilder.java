package androidx.picker.model;

import A0.a;
import U6.v;
import android.graphics.drawable.Drawable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.j;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006B\u0011\b\u0012\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\b¨\u0006\t"}, d2 = {"androidx/picker/model/AppData$CategoryAppDataBuilder", "", "LA0/a;", "", "key", "<init>", "(Ljava/lang/String;)V", "appData", "(LA0/a;)V", "picker-app_release"}, k = 1, mv = {1, 8, 0})
public final class AppData$CategoryAppDataBuilder {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f7895a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Drawable f7896b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f7897c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public List f7898d;

    private AppData$CategoryAppDataBuilder(a aVar) {
        this(aVar.f7a.f15266f);
        this.f7896b = aVar.f8b;
        this.f7897c = aVar.f9c;
        List datas = aVar.f10d;
        j.f(datas, "datas");
        this.f7898d = datas;
    }

    public AppData$CategoryAppDataBuilder(String key) {
        j.f(key, "key");
        this.f7895a = key;
        this.f7898d = v.f4893e;
    }
}
