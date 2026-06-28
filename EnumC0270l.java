package androidx.lifecycle;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: androidx.lifecycle.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC0270l {
    private static final /* synthetic */ EnumC0270l[] $VALUES;
    public static final C0268j Companion;
    public static final EnumC0270l ON_ANY;
    public static final EnumC0270l ON_CREATE;
    public static final EnumC0270l ON_DESTROY;
    public static final EnumC0270l ON_PAUSE;
    public static final EnumC0270l ON_RESUME;
    public static final EnumC0270l ON_START;
    public static final EnumC0270l ON_STOP;

    static {
        EnumC0270l enumC0270l = new EnumC0270l("ON_CREATE", 0);
        ON_CREATE = enumC0270l;
        EnumC0270l enumC0270l2 = new EnumC0270l("ON_START", 1);
        ON_START = enumC0270l2;
        EnumC0270l enumC0270l3 = new EnumC0270l("ON_RESUME", 2);
        ON_RESUME = enumC0270l3;
        EnumC0270l enumC0270l4 = new EnumC0270l("ON_PAUSE", 3);
        ON_PAUSE = enumC0270l4;
        EnumC0270l enumC0270l5 = new EnumC0270l("ON_STOP", 4);
        ON_STOP = enumC0270l5;
        EnumC0270l enumC0270l6 = new EnumC0270l("ON_DESTROY", 5);
        ON_DESTROY = enumC0270l6;
        EnumC0270l enumC0270l7 = new EnumC0270l("ON_ANY", 6);
        ON_ANY = enumC0270l7;
        $VALUES = new EnumC0270l[]{enumC0270l, enumC0270l2, enumC0270l3, enumC0270l4, enumC0270l5, enumC0270l6, enumC0270l7};
        Companion = new C0268j();
    }

    public static EnumC0270l valueOf(String str) {
        return (EnumC0270l) Enum.valueOf(EnumC0270l.class, str);
    }

    public static EnumC0270l[] values() {
        return (EnumC0270l[]) $VALUES.clone();
    }

    public final EnumC0271m a() {
        switch (AbstractC0269k.f7817a[ordinal()]) {
            case 1:
            case 2:
                return EnumC0271m.f7820g;
            case 3:
            case 4:
                return EnumC0271m.h;
            case 5:
                return EnumC0271m.f7821i;
            case 6:
                return EnumC0271m.f7818e;
            default:
                throw new IllegalArgumentException(this + " has no target state");
        }
    }
}
