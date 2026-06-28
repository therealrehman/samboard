package androidx.recyclerview.widget;

/* JADX INFO: renamed from: androidx.recyclerview.widget.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0332f extends AbstractC0328d {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ androidx.fragment.app.q0 f9132d;

    public C0332f(androidx.fragment.app.q0 q0Var) {
        this.f9132d = q0Var;
    }

    @Override // androidx.recyclerview.widget.AbstractC0328d
    public final boolean a(int i5, int i7) {
        androidx.fragment.app.q0 q0Var = this.f9132d;
        Object obj = q0Var.f7716g.get(i5);
        Object obj2 = q0Var.h.get(i7);
        if (obj != null && obj2 != null) {
            return ((AbstractC0363v) ((C0338i) q0Var.f7718j).f9151b.f9129b).areContentsTheSame(obj, obj2);
        }
        if (obj == null && obj2 == null) {
            return true;
        }
        throw new AssertionError();
    }

    @Override // androidx.recyclerview.widget.AbstractC0328d
    public final boolean b(int i5, int i7) {
        androidx.fragment.app.q0 q0Var = this.f9132d;
        Object obj = q0Var.f7716g.get(i5);
        Object obj2 = q0Var.h.get(i7);
        return (obj == null || obj2 == null) ? obj == null && obj2 == null : ((AbstractC0363v) ((C0338i) q0Var.f7718j).f9151b.f9129b).areItemsTheSame(obj, obj2);
    }

    @Override // androidx.recyclerview.widget.AbstractC0328d
    public final Object g(int i5, int i7) {
        androidx.fragment.app.q0 q0Var = this.f9132d;
        Object obj = q0Var.f7716g.get(i5);
        Object obj2 = q0Var.h.get(i7);
        if (obj == null || obj2 == null) {
            throw new AssertionError();
        }
        return ((AbstractC0363v) ((C0338i) q0Var.f7718j).f9151b.f9129b).getChangePayload(obj, obj2);
    }

    @Override // androidx.recyclerview.widget.AbstractC0328d
    public final int h() {
        return this.f9132d.h.size();
    }

    @Override // androidx.recyclerview.widget.AbstractC0328d
    public final int i() {
        return this.f9132d.f7716g.size();
    }
}
