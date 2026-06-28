package androidx.lifecycle;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: androidx.lifecycle.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC0271m {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final EnumC0271m f7818e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final EnumC0271m f7819f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final EnumC0271m f7820g;
    public static final EnumC0271m h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final EnumC0271m f7821i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final /* synthetic */ EnumC0271m[] f7822j;

    static {
        EnumC0271m enumC0271m = new EnumC0271m("DESTROYED", 0);
        f7818e = enumC0271m;
        EnumC0271m enumC0271m2 = new EnumC0271m("INITIALIZED", 1);
        f7819f = enumC0271m2;
        EnumC0271m enumC0271m3 = new EnumC0271m("CREATED", 2);
        f7820g = enumC0271m3;
        EnumC0271m enumC0271m4 = new EnumC0271m("STARTED", 3);
        h = enumC0271m4;
        EnumC0271m enumC0271m5 = new EnumC0271m("RESUMED", 4);
        f7821i = enumC0271m5;
        f7822j = new EnumC0271m[]{enumC0271m, enumC0271m2, enumC0271m3, enumC0271m4, enumC0271m5};
    }

    public static EnumC0271m valueOf(String str) {
        return (EnumC0271m) Enum.valueOf(EnumC0271m.class, str);
    }

    public static EnumC0271m[] values() {
        return (EnumC0271m[]) f7822j.clone();
    }

    public final boolean a(EnumC0271m enumC0271m) {
        return compareTo(enumC0271m) >= 0;
    }
}
