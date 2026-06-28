package V7;

import java.io.Serializable;
import java.util.Collections;

/* JADX INFO: renamed from: V7.p, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0102p extends AbstractC0088b implements Serializable {
    public static C0101o g(AbstractC0099m abstractC0099m, AbstractC0102p abstractC0102p, int i5, P p4, Class cls) {
        return new C0101o(abstractC0099m, Collections.emptyList(), abstractC0102p, new C0100n(i5, p4, true), cls);
    }

    public static C0101o h(AbstractC0099m abstractC0099m, Serializable serializable, AbstractC0102p abstractC0102p, int i5, S s8, Class cls) {
        return new C0101o(abstractC0099m, serializable, abstractC0102p, new C0100n(i5, s8, false), cls);
    }
}
