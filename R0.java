package androidx.recyclerview.widget;

/* JADX INFO: loaded from: classes.dex */
public final class R0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f8978a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f8979b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f8980c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f8981d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f8982e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f8983f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f8984g;
    public boolean h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f8985i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f8986j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f8987k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f8988l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f8989m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f8990n;

    public final void a(int i5) {
        if ((this.f8981d & i5) != 0) {
            return;
        }
        throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(i5) + " but it is " + Integer.toBinaryString(this.f8981d));
    }

    public final int b() {
        return this.f8984g ? this.f8979b - this.f8980c : this.f8982e;
    }

    public final String toString() {
        return "State{mTargetPosition=" + this.f8978a + ", mData=null, mItemCount=" + this.f8982e + ", mIsMeasuring=" + this.f8985i + ", mPreviousLayoutItemCount=" + this.f8979b + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.f8980c + ", mStructureChanged=" + this.f8983f + ", mInPreLayout=" + this.f8984g + ", mRunSimpleAnimations=" + this.f8986j + ", mRunPredictiveAnimations=" + this.f8987k + '}';
    }
}
