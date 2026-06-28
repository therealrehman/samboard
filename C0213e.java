package androidx.core.view;

import android.view.DisplayCutout;
import java.util.Objects;

/* JADX INFO: renamed from: androidx.core.view.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0213e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final DisplayCutout f7220a;

    public C0213e(DisplayCutout displayCutout) {
        this.f7220a = displayCutout;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C0213e.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.f7220a, ((C0213e) obj).f7220a);
    }

    public final int hashCode() {
        DisplayCutout displayCutout = this.f7220a;
        if (displayCutout == null) {
            return 0;
        }
        return displayCutout.hashCode();
    }

    public final String toString() {
        return "DisplayCutoutCompat{" + this.f7220a + "}";
    }
}
