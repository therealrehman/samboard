package androidx.transition;

import java.util.ArrayList;

/* JADX INFO: renamed from: androidx.transition.z, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0397z extends W {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ Object f9509e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ ArrayList f9510f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f9511g = null;
    public final /* synthetic */ ArrayList h = null;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f9512i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ ArrayList f9513j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ B f9514k;

    public C0397z(B b3, Object obj, ArrayList arrayList, Object obj2, ArrayList arrayList2) {
        this.f9514k = b3;
        this.f9509e = obj;
        this.f9510f = arrayList;
        this.f9512i = obj2;
        this.f9513j = arrayList2;
    }

    @Override // androidx.transition.T
    public final void onTransitionEnd(U u5) {
        u5.removeListener(this);
    }

    @Override // androidx.transition.W, androidx.transition.T
    public final void onTransitionStart(U u5) {
        B b3 = this.f9514k;
        Object obj = this.f9509e;
        if (obj != null) {
            b3.t(obj, this.f9510f, null);
        }
        Object obj2 = this.f9511g;
        if (obj2 != null) {
            b3.t(obj2, this.h, null);
        }
        Object obj3 = this.f9512i;
        if (obj3 != null) {
            b3.t(obj3, this.f9513j, null);
        }
    }
}
