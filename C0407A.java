package b2;

import java.io.File;

/* JADX INFO: renamed from: b2.A, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0407A implements p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C0407A f9699b = new C0407A(0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f9700a;

    public /* synthetic */ C0407A(int i5) {
        this.f9700a = i5;
    }

    @Override // b2.p
    public final o a(Object obj, int i5, int i7, V1.h hVar) {
        switch (this.f9700a) {
            case 0:
                return new o(new q2.b(obj), new C0414d(1, obj));
            case 1:
                File file = (File) obj;
                return new o(new q2.b(file), new C0414d(0, file));
            default:
                return null;
        }
    }

    @Override // b2.p
    public final boolean b(Object obj) {
        switch (this.f9700a) {
            case 0:
                return true;
            case 1:
                return true;
            default:
                return false;
        }
    }
}
