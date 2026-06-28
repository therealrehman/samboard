package androidx.lifecycle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: androidx.lifecycle.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0260b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap f7809a = new HashMap();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Map f7810b;

    public C0260b(HashMap map) {
        this.f7810b = map;
        for (Map.Entry entry : map.entrySet()) {
            EnumC0270l enumC0270l = (EnumC0270l) entry.getValue();
            List arrayList = (List) this.f7809a.get(enumC0270l);
            if (arrayList == null) {
                arrayList = new ArrayList();
                this.f7809a.put(enumC0270l, arrayList);
            }
            arrayList.add((C0261c) entry.getKey());
        }
    }

    public static void a(List list, InterfaceC0276s interfaceC0276s, EnumC0270l enumC0270l, Object obj) {
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                C0261c c0261c = (C0261c) list.get(size);
                c0261c.getClass();
                try {
                    int i5 = c0261c.f7811a;
                    Method method = c0261c.f7812b;
                    if (i5 == 0) {
                        method.invoke(obj, null);
                    } else if (i5 == 1) {
                        method.invoke(obj, interfaceC0276s);
                    } else if (i5 == 2) {
                        method.invoke(obj, interfaceC0276s, enumC0270l);
                    }
                } catch (IllegalAccessException e3) {
                    throw new RuntimeException(e3);
                } catch (InvocationTargetException e10) {
                    throw new RuntimeException("Failed to call observer method", e10.getCause());
                }
            }
        }
    }
}
