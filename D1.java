package androidx.recyclerview.widget;

/* JADX INFO: loaded from: classes.dex */
public final class d1 extends D0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f9126a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ e1 f9127b;

    public d1(e1 e1Var) {
        this.f9127b = e1Var;
    }

    @Override // androidx.recyclerview.widget.D0
    public final void onScrollStateChanged(RecyclerView recyclerView, int i5) {
        if (i5 == 0 && this.f9126a) {
            this.f9126a = false;
            this.f9127b.snapToTargetExistingView();
        }
    }

    @Override // androidx.recyclerview.widget.D0
    public final void onScrolled(RecyclerView recyclerView, int i5, int i7) {
        if (i5 == 0 && i7 == 0) {
            return;
        }
        this.f9126a = true;
    }
}
