package androidx.lifecycle;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\n\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"androidx/lifecycle/LegacySavedStateHandleController$tryToAddRecreator$1", "Landroidx/lifecycle/q;", "lifecycle-viewmodel-savedstate_release"}, k = 1, mv = {1, 8, 0})
public final class LegacySavedStateHandleController$tryToAddRecreator$1 implements InterfaceC0275q {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ AbstractC0272n f7780e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ U0.e f7781f;

    public LegacySavedStateHandleController$tryToAddRecreator$1(U0.e eVar, AbstractC0272n abstractC0272n) {
        this.f7780e = abstractC0272n;
        this.f7781f = eVar;
    }

    @Override // androidx.lifecycle.InterfaceC0275q
    public final void a(InterfaceC0276s interfaceC0276s, EnumC0270l enumC0270l) {
        if (enumC0270l == EnumC0270l.ON_START) {
            this.f7780e.b(this);
            this.f7781f.e();
        }
    }
}
