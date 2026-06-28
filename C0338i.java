package androidx.recyclerview.widget;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

/* JADX INFO: renamed from: androidx.recyclerview.widget.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0338i {
    public static final ExecutorC0336h h = new ExecutorC0336h();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final X f9150a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0330e f9151b;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public List f9154e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f9156g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final CopyOnWriteArrayList f9153d = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public List f9155f = Collections.emptyList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ExecutorC0336h f9152c = h;

    public C0338i(C0326c c0326c, C0330e c0330e) {
        this.f9150a = c0326c;
        this.f9151b = c0330e;
    }

    public final void a(List list, Runnable runnable) {
        for (InterfaceC0334g interfaceC0334g : this.f9153d) {
            ((V) interfaceC0334g).f9031a.onCurrentListChanged(list, this.f9155f);
        }
        if (runnable != null) {
            runnable.run();
        }
    }

    public final void b(List list, Runnable runnable) {
        int i5 = this.f9156g + 1;
        this.f9156g = i5;
        List list2 = this.f9154e;
        if (list == list2) {
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        List list3 = this.f9155f;
        X x9 = this.f9150a;
        if (list == null) {
            int size = list2.size();
            this.f9154e = null;
            this.f9155f = Collections.emptyList();
            x9.d(0, size);
            a(list3, runnable);
            return;
        }
        if (list2 != null) {
            ((Executor) this.f9151b.f9128a).execute(new androidx.fragment.app.q0(this, list2, list, i5, runnable));
            return;
        }
        this.f9154e = list;
        this.f9155f = Collections.unmodifiableList(list);
        x9.p(0, list.size());
        a(list3, runnable);
    }
}
