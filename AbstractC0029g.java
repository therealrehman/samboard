package E7;

import com.samsung.android.honeyboard.forms.model.FieldName;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import u7.C1058d;

/* JADX INFO: renamed from: E7.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0029g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Map f617a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final LinkedHashMap f618b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Set f619c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Set f620d;

    static {
        U7.e eVar = s7.l.f13486j;
        U7.c cVarG = eVar.b(U7.f.e("name")).g();
        kotlin.jvm.internal.j.e(cVarG, "toSafe(...)");
        T6.h hVar = new T6.h(cVarG, s7.m.f13505d);
        U7.c cVarG2 = eVar.b(U7.f.e("ordinal")).g();
        kotlin.jvm.internal.j.e(cVarG2, "toSafe(...)");
        T6.h hVar2 = new T6.h(cVarG2, U7.f.e("ordinal"));
        T6.h hVar3 = new T6.h(s7.l.f13451B.c(U7.f.e(FieldName.SIZE)), U7.f.e(FieldName.SIZE));
        U7.c cVar = s7.l.f13455F;
        T6.h hVar4 = new T6.h(cVar.c(U7.f.e(FieldName.SIZE)), U7.f.e(FieldName.SIZE));
        U7.c cVarG3 = s7.l.f13482e.b(U7.f.e("length")).g();
        kotlin.jvm.internal.j.e(cVarG3, "toSafe(...)");
        Map mapE0 = U6.A.e0(hVar, hVar2, hVar3, hVar4, new T6.h(cVarG3, U7.f.e("length")), new T6.h(cVar.c(U7.f.e("keys")), U7.f.e("keySet")), new T6.h(cVar.c(U7.f.e("values")), U7.f.e("values")), new T6.h(cVar.c(U7.f.e("entries")), U7.f.e("entrySet")));
        f617a = mapE0;
        Set<Map.Entry> setEntrySet = mapE0.entrySet();
        ArrayList<T6.h> arrayList = new ArrayList(U6.p.c0(setEntrySet, 10));
        for (Map.Entry entry : setEntrySet) {
            arrayList.add(new T6.h(((U7.c) entry.getKey()).f(), entry.getValue()));
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (T6.h hVar5 : arrayList) {
            U7.f fVar = (U7.f) hVar5.f3316f;
            Object arrayList2 = linkedHashMap.get(fVar);
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
                linkedHashMap.put(fVar, arrayList2);
            }
            ((List) arrayList2).add((U7.f) hVar5.f3315e);
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(U6.B.a0(linkedHashMap.size()));
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            Object key = entry2.getKey();
            Iterable iterable = (Iterable) entry2.getValue();
            kotlin.jvm.internal.j.f(iterable, "<this>");
            linkedHashMap2.put(key, U6.n.N0(U6.n.Q0(iterable)));
        }
        f618b = linkedHashMap2;
        Map map = f617a;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Map.Entry entry3 : map.entrySet()) {
            String str = C1058d.f14446a;
            U7.e eVarI = ((U7.c) entry3.getKey()).e().i();
            kotlin.jvm.internal.j.e(eVarI, "toUnsafe(...)");
            U7.b bVarF = C1058d.f(eVarI);
            kotlin.jvm.internal.j.c(bVarF);
            linkedHashSet.add(bVarF.b().c((U7.f) entry3.getValue()));
        }
        Set setKeySet = f617a.keySet();
        f619c = setKeySet;
        ArrayList arrayList3 = new ArrayList(U6.p.c0(setKeySet, 10));
        Iterator it = setKeySet.iterator();
        while (it.hasNext()) {
            arrayList3.add(((U7.c) it.next()).f());
        }
        f620d = U6.n.R0(arrayList3);
    }
}
