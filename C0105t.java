package V7;

import java.io.IOException;

/* JADX INFO: renamed from: V7.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0105t extends IOException {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public AbstractC0088b f5136e;

    public C0105t(String str) {
        super(str);
        this.f5136e = null;
    }

    public static C0105t a() {
        return new C0105t("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
    }
}
