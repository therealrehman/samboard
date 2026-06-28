package E7;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import m7.AbstractC0752G;

/* JADX INFO: renamed from: E7.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0024b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final U7.c f598a = new U7.c("javax.annotation.meta.TypeQualifierNickname");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final U7.c f599b = new U7.c("javax.annotation.meta.TypeQualifier");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final U7.c f600c = new U7.c("javax.annotation.meta.TypeQualifierDefault");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final U7.c f601d = new U7.c("kotlin.annotations.jvm.UnderMigration");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Map f602e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final LinkedHashMap f603f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Set f604g;

    static {
        EnumC0023a enumC0023a = EnumC0023a.h;
        EnumC0023a enumC0023a2 = EnumC0023a.f592f;
        EnumC0023a enumC0023a3 = EnumC0023a.f593g;
        List listX = U6.o.X(enumC0023a, enumC0023a2, enumC0023a3, EnumC0023a.f595j, EnumC0023a.f594i);
        U7.c cVar = z.f670c;
        M7.g gVar = M7.g.f2133g;
        Map mapE0 = U6.A.e0(new T6.h(cVar, new n(new M7.h(gVar), listX, false)), new T6.h(z.f673f, new n(new M7.h(gVar), listX, false)));
        f602e = mapE0;
        f603f = U6.A.f0(U6.A.e0(new T6.h(new U7.c("javax.annotation.ParametersAreNullableByDefault"), new n(new M7.h(M7.g.f2132f), AbstractC0752G.G(enumC0023a3))), new T6.h(new U7.c("javax.annotation.ParametersAreNonnullByDefault"), new n(new M7.h(gVar), AbstractC0752G.G(enumC0023a3)))), mapE0);
        f604g = U6.E.f0(z.h, z.f675i);
    }
}
