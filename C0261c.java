package androidx.lifecycle;

import java.lang.reflect.Method;

/* JADX INFO: renamed from: androidx.lifecycle.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0261c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f7811a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Method f7812b;

    public C0261c(Method method, int i5) {
        this.f7811a = i5;
        this.f7812b = method;
        method.setAccessible(true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0261c)) {
            return false;
        }
        C0261c c0261c = (C0261c) obj;
        return this.f7811a == c0261c.f7811a && this.f7812b.getName().equals(c0261c.f7812b.getName());
    }

    public final int hashCode() {
        return this.f7812b.getName().hashCode() + (this.f7811a * 31);
    }
}
