package androidx.transition;

import android.view.View;
import d6.AbstractC0476d;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class c0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public View f9408b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap f9407a = new HashMap();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f9409c = new ArrayList();

    public c0(View view) {
        this.f9408b = view;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof c0)) {
            return false;
        }
        c0 c0Var = (c0) obj;
        return this.f9408b == c0Var.f9408b && this.f9407a.equals(c0Var.f9407a);
    }

    public final int hashCode() {
        return this.f9407a.hashCode() + (this.f9408b.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sbP = AbstractC0476d.p("TransitionValues@" + Integer.toHexString(hashCode()) + ":\n", "    view = ");
        sbP.append(this.f9408b);
        sbP.append("\n");
        String strK = AbstractC0476d.k(sbP.toString(), "    values:");
        HashMap map = this.f9407a;
        for (String str : map.keySet()) {
            strK = strK + "    " + str + ": " + map.get(str) + "\n";
        }
        return strK;
    }
}
