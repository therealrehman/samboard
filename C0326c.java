package androidx.recyclerview.widget;

/* JADX INFO: renamed from: androidx.recyclerview.widget.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0326c implements X {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f9118e;

    public /* synthetic */ C0326c(Object obj) {
        this.f9118e = obj;
    }

    @Override // androidx.recyclerview.widget.X
    public void d(int i5, int i7) {
        ((AbstractC0341j0) this.f9118e).notifyItemRangeRemoved(i5, i7);
    }

    @Override // androidx.recyclerview.widget.X
    public void e(int i5, int i7) {
        ((AbstractC0341j0) this.f9118e).notifyItemMoved(i5, i7);
    }

    @Override // androidx.recyclerview.widget.X
    public void p(int i5, int i7) {
        ((AbstractC0341j0) this.f9118e).notifyItemRangeInserted(i5, i7);
    }

    @Override // androidx.recyclerview.widget.X
    public void q(int i5, int i7, Object obj) {
        ((AbstractC0341j0) this.f9118e).notifyItemRangeChanged(i5, i7, obj);
    }
}
