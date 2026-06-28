package androidx.recyclerview.widget;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: androidx.recyclerview.widget.i0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC0339i0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final EnumC0339i0 f9157e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final /* synthetic */ EnumC0339i0[] f9158f;

    static {
        EnumC0339i0 enumC0339i0 = new EnumC0339i0("ALLOW", 0);
        f9157e = enumC0339i0;
        f9158f = new EnumC0339i0[]{enumC0339i0, new EnumC0339i0("PREVENT_WHEN_EMPTY", 1), new EnumC0339i0("PREVENT", 2)};
    }

    public static EnumC0339i0 valueOf(String str) {
        return (EnumC0339i0) Enum.valueOf(EnumC0339i0.class, str);
    }

    public static EnumC0339i0[] values() {
        return (EnumC0339i0[]) f9158f.clone();
    }
}
