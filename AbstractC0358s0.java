package androidx.recyclerview.widget;

import android.view.View;
import java.util.ArrayList;

/* JADX INFO: renamed from: androidx.recyclerview.widget.s0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0358s0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public InterfaceC0355q0 f9267a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ArrayList f9268b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public View f9269c;

    public static void b(V0 v02) {
        int i5 = v02.mFlags;
        if (!v02.isInvalid() && (i5 & 4) == 0) {
            v02.getOldPosition();
            v02.getAbsoluteAdapterPosition();
        }
    }

    public abstract boolean a(V0 v02, V0 v03, C0356r0 c0356r0, C0356r0 c0356r02);

    public final void c(V0 v02) {
        InterfaceC0355q0 interfaceC0355q0 = this.f9267a;
        if (interfaceC0355q0 != null) {
            C0327c0 c0327c0 = (C0327c0) interfaceC0355q0;
            c0327c0.getClass();
            v02.setIsRecyclable(true);
            if (v02.mShadowedHolder != null && v02.mShadowingHolder == null) {
                v02.mShadowedHolder = null;
            }
            v02.mShadowingHolder = null;
            RecyclerView recyclerView = c0327c0.f9119a;
            for (AbstractC0362u0 abstractC0362u0 : recyclerView.mItemDecorations) {
                if (abstractC0362u0 instanceof N) {
                    ((N) abstractC0362u0).j(v02, false);
                }
            }
            if (v02.shouldBeKeptAsChild() || recyclerView.removeAnimatingView(v02.itemView) || !v02.isTmpDetached()) {
                return;
            }
            recyclerView.removeDetachedView(v02.itemView, false);
        }
    }

    public abstract void d(V0 v02);

    public abstract void e();

    public abstract boolean f();
}
