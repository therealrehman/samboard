package X1;

import java.security.MessageDigest;

/* JADX INFO: renamed from: X1.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0110e implements V1.e {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final V1.e f5509b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final V1.e f5510c;

    public C0110e(V1.e eVar, V1.e eVar2) {
        this.f5509b = eVar;
        this.f5510c = eVar2;
    }

    @Override // V1.e
    public final boolean equals(Object obj) {
        if (!(obj instanceof C0110e)) {
            return false;
        }
        C0110e c0110e = (C0110e) obj;
        return this.f5509b.equals(c0110e.f5509b) && this.f5510c.equals(c0110e.f5510c);
    }

    @Override // V1.e
    public final int hashCode() {
        return this.f5510c.hashCode() + (this.f5509b.hashCode() * 31);
    }

    public final String toString() {
        return "DataCacheKey{sourceKey=" + this.f5509b + ", signature=" + this.f5510c + '}';
    }

    @Override // V1.e
    public final void updateDiskCacheKey(MessageDigest messageDigest) {
        this.f5509b.updateDiskCacheKey(messageDigest);
        this.f5510c.updateDiskCacheKey(messageDigest);
    }
}
