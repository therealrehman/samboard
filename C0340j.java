package androidx.recyclerview.widget;

/* JADX INFO: renamed from: androidx.recyclerview.widget.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0340j implements X {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final X f9168e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f9169f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f9170g = -1;
    public int h = -1;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f9171i = null;

    public C0340j(X x9) {
        this.f9168e = x9;
    }

    public final void a() {
        int i5 = this.f9169f;
        if (i5 == 0) {
            return;
        }
        X x9 = this.f9168e;
        if (i5 == 1) {
            x9.p(this.f9170g, this.h);
        } else if (i5 == 2) {
            x9.d(this.f9170g, this.h);
        } else if (i5 == 3) {
            x9.q(this.f9170g, this.h, this.f9171i);
        }
        this.f9171i = null;
        this.f9169f = 0;
    }

    @Override // androidx.recyclerview.widget.X
    public final void d(int i5, int i7) {
        int i9;
        if (this.f9169f == 2 && (i9 = this.f9170g) >= i5 && i9 <= i5 + i7) {
            this.h += i7;
            this.f9170g = i5;
        } else {
            a();
            this.f9170g = i5;
            this.h = i7;
            this.f9169f = 2;
        }
    }

    @Override // androidx.recyclerview.widget.X
    public final void e(int i5, int i7) {
        a();
        this.f9168e.e(i5, i7);
    }

    @Override // androidx.recyclerview.widget.X
    public final void p(int i5, int i7) {
        int i9;
        if (this.f9169f == 1 && i5 >= (i9 = this.f9170g)) {
            int i10 = this.h;
            if (i5 <= i9 + i10) {
                this.h = i10 + i7;
                this.f9170g = Math.min(i5, i9);
                return;
            }
        }
        a();
        this.f9170g = i5;
        this.h = i7;
        this.f9169f = 1;
    }

    @Override // androidx.recyclerview.widget.X
    public final void q(int i5, int i7, Object obj) {
        int i9;
        if (this.f9169f == 3) {
            int i10 = this.f9170g;
            int i11 = this.h;
            if (i5 <= i10 + i11 && (i9 = i5 + i7) >= i10 && this.f9171i == obj) {
                this.f9170g = Math.min(i5, i10);
                this.h = Math.max(i11 + i10, i9) - this.f9170g;
                return;
            }
        }
        a();
        this.f9170g = i5;
        this.h = i7;
        this.f9171i = obj;
        this.f9169f = 3;
    }
}
