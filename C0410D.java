package b2;

import android.net.Uri;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: renamed from: b2.D, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0410D implements p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Set f9703b = Collections.unmodifiableSet(new HashSet(Arrays.asList("http", "https")));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final p f9704a;

    public C0410D(p pVar) {
        this.f9704a = pVar;
    }

    @Override // b2.p
    public final o a(Object obj, int i5, int i7, V1.h hVar) {
        return this.f9704a.a(new C0416f(((Uri) obj).toString()), i5, i7, hVar);
    }

    @Override // b2.p
    public final boolean b(Object obj) {
        return f9703b.contains(((Uri) obj).getScheme());
    }
}
