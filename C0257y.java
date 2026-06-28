package androidx.fragment.app;

/* JADX INFO: renamed from: androidx.fragment.app.y, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0257y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7747a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ Object f7748b;

    public /* synthetic */ C0257y(int i5, Object obj) {
        this.f7747a = i5;
        this.f7748b = obj;
    }

    public final androidx.activity.result.g a() {
        switch (this.f7747a) {
            case 0:
                Fragment fragment = (Fragment) this.f7748b;
                Object obj = fragment.mHost;
                return obj instanceof androidx.activity.result.h ? ((androidx.activity.result.h) obj).getActivityResultRegistry() : fragment.requireActivity().getActivityResultRegistry();
            default:
                return (androidx.activity.result.g) this.f7748b;
        }
    }
}
