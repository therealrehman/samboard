package E7;

import b8.AbstractC0430e;
import v7.InterfaceC1133u;
import y7.AbstractC1243p;

/* JADX INFO: renamed from: E7.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0028f extends H {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final /* synthetic */ int f616m = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public static final InterfaceC1133u a(InterfaceC1133u functionDescriptor) {
        kotlin.jvm.internal.j.f(functionDescriptor, "functionDescriptor");
        U7.f name = ((AbstractC1243p) functionDescriptor).getName();
        kotlin.jvm.internal.j.e(name, "getName(...)");
        if (b(name)) {
            return (InterfaceC1133u) AbstractC0430e.b(functionDescriptor, C0027e.f609f);
        }
        return null;
    }

    public static boolean b(U7.f fVar) {
        kotlin.jvm.internal.j.f(fVar, "<this>");
        return H.f586f.contains(fVar);
    }
}
