package E7;

import b8.AbstractC0430e;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import m7.AbstractC0752G;
import v7.InterfaceC1118f;
import w7.InterfaceC1158b;
import w7.InterfaceC1164h;

/* JADX INFO: renamed from: E7.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0025c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final LinkedHashMap f605c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final u f606a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ConcurrentHashMap f607b;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (EnumC0023a enumC0023a : EnumC0023a.values()) {
            String str = enumC0023a.f597e;
            if (linkedHashMap.get(str) == null) {
                linkedHashMap.put(str, enumC0023a);
            }
        }
        f605c = linkedHashMap;
    }

    public C0025c(u javaTypeEnhancementState) {
        kotlin.jvm.internal.j.f(javaTypeEnhancementState, "javaTypeEnhancementState");
        this.f606a = javaTypeEnhancementState;
        this.f607b = new ConcurrentHashMap();
    }

    public static ArrayList a(Object obj, boolean z9) {
        InterfaceC1158b interfaceC1158b = (InterfaceC1158b) obj;
        kotlin.jvm.internal.j.f(interfaceC1158b, "<this>");
        Map mapB = interfaceC1158b.b();
        ArrayList arrayList = new ArrayList();
        for (Map.Entry entry : mapB.entrySet()) {
            U6.t.e0(arrayList, (!z9 || kotlin.jvm.internal.j.a((U7.f) entry.getKey(), y.f654b)) ? j((Z7.g) entry.getValue()) : U6.v.f4893e);
        }
        return arrayList;
    }

    public static Object c(Object obj, U7.c cVar) {
        for (Object obj2 : e(obj)) {
            if (kotlin.jvm.internal.j.a(d(obj2), cVar)) {
                return obj2;
            }
        }
        return null;
    }

    public static U7.c d(Object obj) {
        InterfaceC1158b interfaceC1158b = (InterfaceC1158b) obj;
        kotlin.jvm.internal.j.f(interfaceC1158b, "<this>");
        return interfaceC1158b.a();
    }

    public static Iterable e(Object obj) {
        InterfaceC1164h annotations;
        InterfaceC1158b interfaceC1158b = (InterfaceC1158b) obj;
        kotlin.jvm.internal.j.f(interfaceC1158b, "<this>");
        InterfaceC1118f interfaceC1118fD = AbstractC0430e.d(interfaceC1158b);
        return (interfaceC1118fD == null || (annotations = interfaceC1118fD.getAnnotations()) == null) ? U6.v.f4893e : annotations;
    }

    public static boolean f(Object obj, U7.c cVar) {
        Iterable iterableE = e(obj);
        if ((iterableE instanceof Collection) && ((Collection) iterableE).isEmpty()) {
            return false;
        }
        Iterator it = iterableE.iterator();
        while (it.hasNext()) {
            if (kotlin.jvm.internal.j.a(d(it.next()), cVar)) {
                return true;
            }
        }
        return false;
    }

    public static List j(Z7.g gVar) {
        if (!(gVar instanceof Z7.b)) {
            return gVar instanceof Z7.i ? AbstractC0752G.G(((Z7.i) gVar).f5959c.c()) : U6.v.f4893e;
        }
        Iterable iterable = (Iterable) ((Z7.b) gVar).f5956a;
        ArrayList arrayList = new ArrayList();
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            U6.t.e0(arrayList, j((Z7.g) it.next()));
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0161  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final E7.v b(E7.v r18, java.lang.Iterable r19) {
        /*
            Method dump skipped, instruction units count: 496
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: E7.C0025c.b(E7.v, java.lang.Iterable):E7.v");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0085, code lost:
    
        if (r6.equals("ALWAYS") != false) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x009a, code lost:
    
        if (r6.equals("NEVER") == false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00a3, code lost:
    
        if (r6.equals("MAYBE") == false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a6, code lost:
    
        r6 = M7.g.f2132f;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final M7.h g(java.lang.Object r6, boolean r7) {
        /*
            Method dump skipped, instruction units count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: E7.C0025c.g(java.lang.Object, boolean):M7.h");
    }

    public final C h(Object obj) {
        String str;
        u uVar = this.f606a;
        C c5 = (C) uVar.f644a.f649c.get(d(obj));
        if (c5 != null) {
            return c5;
        }
        Object objC = c(obj, AbstractC0024b.f601d);
        if (objC == null || (str = (String) U6.n.p0(a(objC, false))) == null) {
            return null;
        }
        C c9 = uVar.f644a.f648b;
        if (c9 != null) {
            return c9;
        }
        int iHashCode = str.hashCode();
        if (iHashCode == -2137067054) {
            if (str.equals("IGNORE")) {
                return C.f564f;
            }
            return null;
        }
        if (iHashCode == -1838656823) {
            if (str.equals("STRICT")) {
                return C.h;
            }
            return null;
        }
        if (iHashCode == 2656902 && str.equals("WARN")) {
            return C.f565g;
        }
        return null;
    }

    public final Object i(Object annotation) {
        Object objI;
        kotlin.jvm.internal.j.f(annotation, "annotation");
        if (this.f606a.f644a.f650d) {
            return null;
        }
        if (U6.n.i0(AbstractC0024b.f604g, d(annotation)) || f(annotation, AbstractC0024b.f599b)) {
            return annotation;
        }
        if (!f(annotation, AbstractC0024b.f598a)) {
            return null;
        }
        ConcurrentHashMap concurrentHashMap = this.f607b;
        InterfaceC1118f interfaceC1118fD = AbstractC0430e.d((InterfaceC1158b) annotation);
        kotlin.jvm.internal.j.c(interfaceC1118fD);
        Object obj = concurrentHashMap.get(interfaceC1118fD);
        if (obj != null) {
            return obj;
        }
        Iterator it = e(annotation).iterator();
        while (true) {
            if (!it.hasNext()) {
                objI = null;
                break;
            }
            objI = i(it.next());
            if (objI != null) {
                break;
            }
        }
        if (objI == null) {
            return null;
        }
        Object objPutIfAbsent = concurrentHashMap.putIfAbsent(interfaceC1118fD, objI);
        return objPutIfAbsent == null ? objI : objPutIfAbsent;
    }
}
