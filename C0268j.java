package androidx.lifecycle;

/* JADX INFO: renamed from: androidx.lifecycle.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0268j {
    public static EnumC0270l a(EnumC0271m state) {
        kotlin.jvm.internal.j.f(state, "state");
        int iOrdinal = state.ordinal();
        if (iOrdinal == 2) {
            return EnumC0270l.ON_DESTROY;
        }
        if (iOrdinal == 3) {
            return EnumC0270l.ON_STOP;
        }
        if (iOrdinal != 4) {
            return null;
        }
        return EnumC0270l.ON_PAUSE;
    }

    public static EnumC0270l b(EnumC0271m state) {
        kotlin.jvm.internal.j.f(state, "state");
        int iOrdinal = state.ordinal();
        if (iOrdinal == 1) {
            return EnumC0270l.ON_CREATE;
        }
        if (iOrdinal == 2) {
            return EnumC0270l.ON_START;
        }
        if (iOrdinal != 3) {
            return null;
        }
        return EnumC0270l.ON_RESUME;
    }

    public static EnumC0270l c(EnumC0271m state) {
        kotlin.jvm.internal.j.f(state, "state");
        int iOrdinal = state.ordinal();
        if (iOrdinal == 2) {
            return EnumC0270l.ON_CREATE;
        }
        if (iOrdinal == 3) {
            return EnumC0270l.ON_START;
        }
        if (iOrdinal != 4) {
            return null;
        }
        return EnumC0270l.ON_RESUME;
    }
}
