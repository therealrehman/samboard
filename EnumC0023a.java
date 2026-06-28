package E7;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: E7.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC0023a {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final EnumC0023a f592f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final EnumC0023a f593g;
    public static final EnumC0023a h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final EnumC0023a f594i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final EnumC0023a f595j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final /* synthetic */ EnumC0023a[] f596k;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final String f597e;

    static {
        EnumC0023a enumC0023a = new EnumC0023a("METHOD_RETURN_TYPE", 0, "METHOD");
        f592f = enumC0023a;
        EnumC0023a enumC0023a2 = new EnumC0023a("VALUE_PARAMETER", 1, "PARAMETER");
        f593g = enumC0023a2;
        EnumC0023a enumC0023a3 = new EnumC0023a("FIELD", 2, "FIELD");
        h = enumC0023a3;
        EnumC0023a enumC0023a4 = new EnumC0023a("TYPE_USE", 3, "TYPE_USE");
        f594i = enumC0023a4;
        EnumC0023a enumC0023a5 = new EnumC0023a("TYPE_PARAMETER_BOUNDS", 4, "TYPE_USE");
        f595j = enumC0023a5;
        EnumC0023a[] enumC0023aArr = {enumC0023a, enumC0023a2, enumC0023a3, enumC0023a4, enumC0023a5, new EnumC0023a("TYPE_PARAMETER", 5, "TYPE_PARAMETER")};
        f596k = enumC0023aArr;
        android.support.v4.media.session.f.t(enumC0023aArr);
    }

    public EnumC0023a(String str, int i5, String str2) {
        this.f597e = str2;
    }

    public static EnumC0023a valueOf(String str) {
        return (EnumC0023a) Enum.valueOf(EnumC0023a.class, str);
    }

    public static EnumC0023a[] values() {
        return (EnumC0023a[]) f596k.clone();
    }
}
