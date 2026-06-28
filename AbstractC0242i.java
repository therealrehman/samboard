package androidx.fragment.app;

import android.view.View;
import java.util.LinkedHashSet;

/* JADX INFO: renamed from: androidx.fragment.app.i, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0242i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v0 f7655a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final H.f f7656b;

    public AbstractC0242i(v0 v0Var, H.f fVar) {
        this.f7655a = v0Var;
        this.f7656b = fVar;
    }

    public final void a() {
        v0 v0Var = this.f7655a;
        v0Var.getClass();
        H.f signal = this.f7656b;
        kotlin.jvm.internal.j.f(signal, "signal");
        LinkedHashSet linkedHashSet = v0Var.f7740e;
        if (linkedHashSet.remove(signal) && linkedHashSet.isEmpty()) {
            v0Var.b();
        }
    }

    public final boolean b() {
        v0 v0Var = this.f7655a;
        View view = v0Var.f7738c.mView;
        kotlin.jvm.internal.j.e(view, "operation.fragment.mView");
        int iC = s6.c.c(view);
        int i5 = v0Var.f7736a;
        return iC == i5 || !(iC == 2 || i5 == 2);
    }
}
