package b2;

import android.net.Uri;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: renamed from: b2.C, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0409C implements p {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Set f9701b = Collections.unmodifiableSet(new HashSet(Arrays.asList("file", "android.resource", "content")));

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final InterfaceC0408B f9702a;

    public C0409C(InterfaceC0408B interfaceC0408B) {
        this.f9702a = interfaceC0408B;
    }

    @Override // b2.p
    public final o a(Object obj, int i5, int i7, V1.h hVar) {
        Uri uri = (Uri) obj;
        return new o(new q2.b(uri), this.f9702a.f(uri));
    }

    @Override // b2.p
    public final boolean b(Object obj) {
        return f9701b.contains(((Uri) obj).getScheme());
    }
}
