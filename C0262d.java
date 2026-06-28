package androidx.lifecycle;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: androidx.lifecycle.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0262d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final C0262d f7813c = new C0262d();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap f7814a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f7815b = new HashMap();

    public static void b(HashMap map, C0261c c0261c, EnumC0270l enumC0270l, Class cls) {
        EnumC0270l enumC0270l2 = (EnumC0270l) map.get(c0261c);
        if (enumC0270l2 == null || enumC0270l == enumC0270l2) {
            if (enumC0270l2 == null) {
                map.put(c0261c, enumC0270l);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Method " + c0261c.f7812b.getName() + " in " + cls.getName() + " already declared with different @OnLifecycleEvent value: previous value " + enumC0270l2 + ", new value " + enumC0270l);
    }

    public final C0260b a(Class cls, Method[] methodArr) {
        int i5;
        Class superclass = cls.getSuperclass();
        HashMap map = new HashMap();
        HashMap map2 = this.f7814a;
        if (superclass != null) {
            C0260b c0260bA = (C0260b) map2.get(superclass);
            if (c0260bA == null) {
                c0260bA = a(superclass, null);
            }
            map.putAll(c0260bA.f7810b);
        }
        for (Class<?> cls2 : cls.getInterfaces()) {
            C0260b c0260bA2 = (C0260b) map2.get(cls2);
            if (c0260bA2 == null) {
                c0260bA2 = a(cls2, null);
            }
            for (Map.Entry entry : c0260bA2.f7810b.entrySet()) {
                b(map, (C0261c) entry.getKey(), (EnumC0270l) entry.getValue(), cls);
            }
        }
        if (methodArr == null) {
            try {
                methodArr = cls.getDeclaredMethods();
            } catch (NoClassDefFoundError e3) {
                throw new IllegalArgumentException("The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor.", e3);
            }
        }
        boolean z9 = false;
        for (Method method : methodArr) {
            B b3 = (B) method.getAnnotation(B.class);
            if (b3 != null) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length <= 0) {
                    i5 = 0;
                } else {
                    if (!InterfaceC0276s.class.isAssignableFrom(parameterTypes[0])) {
                        throw new IllegalArgumentException("invalid parameter type. Must be one and instanceof LifecycleOwner");
                    }
                    i5 = 1;
                }
                EnumC0270l enumC0270lValue = b3.value();
                if (parameterTypes.length > 1) {
                    if (!EnumC0270l.class.isAssignableFrom(parameterTypes[1])) {
                        throw new IllegalArgumentException("invalid parameter type. second arg must be an event");
                    }
                    if (enumC0270lValue != EnumC0270l.ON_ANY) {
                        throw new IllegalArgumentException("Second arg is supported only for ON_ANY value");
                    }
                    i5 = 2;
                }
                if (parameterTypes.length > 2) {
                    throw new IllegalArgumentException("cannot have more than 2 params");
                }
                b(map, new C0261c(method, i5), enumC0270lValue, cls);
                z9 = true;
            }
        }
        C0260b c0260b = new C0260b(map);
        map2.put(cls, c0260b);
        this.f7815b.put(cls, Boolean.valueOf(z9));
        return c0260b;
    }
}
