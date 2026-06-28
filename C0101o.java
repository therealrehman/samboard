package V7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: renamed from: V7.o, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0101o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AbstractC0088b f5130a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f5131b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final AbstractC0088b f5132c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C0100n f5133d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Method f5134e;

    public C0101o(AbstractC0099m abstractC0099m, Object obj, AbstractC0102p abstractC0102p, C0100n c0100n, Class cls) {
        if (abstractC0099m == null) {
            throw new IllegalArgumentException("Null containingTypeDefaultInstance");
        }
        if (c0100n.f5128f == S.f5081j && abstractC0102p == null) {
            throw new IllegalArgumentException("Null messageDefaultInstance");
        }
        this.f5130a = abstractC0099m;
        this.f5131b = obj;
        this.f5132c = abstractC0102p;
        this.f5133d = c0100n;
        if (!InterfaceC0103q.class.isAssignableFrom(cls)) {
            this.f5134e = null;
            return;
        }
        try {
            this.f5134e = cls.getMethod("valueOf", Integer.TYPE);
        } catch (NoSuchMethodException e3) {
            String name = cls.getName();
            StringBuilder sb = new StringBuilder(name.length() + 52);
            sb.append("Generated message class \"");
            sb.append(name);
            sb.append("\" missing method \"valueOf\".");
            throw new RuntimeException(sb.toString(), e3);
        }
    }

    public final Object a(Object obj) {
        if (this.f5133d.f5128f.f5084e != T.ENUM) {
            return obj;
        }
        try {
            return this.f5134e.invoke(null, (Integer) obj);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e3);
        } catch (InvocationTargetException e10) {
            Throwable cause = e10.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    public final Object b(Object obj) {
        return this.f5133d.f5128f.f5084e == T.ENUM ? Integer.valueOf(((InterfaceC0103q) obj).a()) : obj;
    }
}
