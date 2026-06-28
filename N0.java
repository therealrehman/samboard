package androidx.transition;

import android.view.View;
import android.view.WindowId;

/* JADX INFO: loaded from: classes.dex */
public final class n0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WindowId f9476a;

    public n0(View view) {
        this.f9476a = view.getWindowId();
    }

    public final boolean equals(Object obj) {
        return (obj instanceof n0) && ((n0) obj).f9476a.equals(this.f9476a);
    }

    public final int hashCode() {
        return this.f9476a.hashCode();
    }
}
