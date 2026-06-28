package V7;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: V7.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0098l extends AbstractC0097k implements z {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public C0096j f5124f = C0096j.f5119d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f5125g;

    public final void f(AbstractC0099m abstractC0099m) {
        E e3;
        if (!this.f5125g) {
            this.f5124f = this.f5124f.clone();
            this.f5125g = true;
        }
        C0096j c0096j = this.f5124f;
        C0096j c0096j2 = abstractC0099m.f5126e;
        c0096j.getClass();
        int i5 = 0;
        while (true) {
            int size = c0096j2.f5120a.f5065f.size();
            e3 = c0096j2.f5120a;
            if (i5 >= size) {
                break;
            }
            c0096j.g((Map.Entry) e3.f5065f.get(i5));
            i5++;
        }
        Iterator it = e3.c().iterator();
        while (it.hasNext()) {
            c0096j.g((Map.Entry) it.next());
        }
    }
}
