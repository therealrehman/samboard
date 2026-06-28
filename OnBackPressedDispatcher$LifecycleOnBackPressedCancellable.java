package androidx.activity;

import androidx.lifecycle.AbstractC0272n;
import androidx.lifecycle.EnumC0270l;
import androidx.lifecycle.InterfaceC0275q;
import androidx.lifecycle.InterfaceC0276s;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u00012\u00020\u0002¨\u0006\u0003"}, d2 = {"androidx/activity/OnBackPressedDispatcher$LifecycleOnBackPressedCancellable", "Landroidx/lifecycle/q;", "Landroidx/activity/c;", "activity_release"}, k = 1, mv = {1, 8, 0})
final class OnBackPressedDispatcher$LifecycleOnBackPressedCancellable implements InterfaceC0275q, c {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final AbstractC0272n f6086e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final o f6087f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public v f6088g;
    public final /* synthetic */ x h;

    public OnBackPressedDispatcher$LifecycleOnBackPressedCancellable(x xVar, AbstractC0272n abstractC0272n, o onBackPressedCallback) {
        kotlin.jvm.internal.j.f(onBackPressedCallback, "onBackPressedCallback");
        this.h = xVar;
        this.f6086e = abstractC0272n;
        this.f6087f = onBackPressedCallback;
        abstractC0272n.a(this);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // androidx.lifecycle.InterfaceC0275q
    public final void a(InterfaceC0276s interfaceC0276s, EnumC0270l enumC0270l) {
        if (enumC0270l != EnumC0270l.ON_START) {
            if (enumC0270l != EnumC0270l.ON_STOP) {
                if (enumC0270l == EnumC0270l.ON_DESTROY) {
                    cancel();
                    return;
                }
                return;
            } else {
                v vVar = this.f6088g;
                if (vVar != null) {
                    vVar.cancel();
                    return;
                }
                return;
            }
        }
        x xVar = this.h;
        xVar.getClass();
        o onBackPressedCallback = this.f6087f;
        kotlin.jvm.internal.j.f(onBackPressedCallback, "onBackPressedCallback");
        xVar.f6148b.addLast(onBackPressedCallback);
        v vVar2 = new v(xVar, onBackPressedCallback);
        onBackPressedCallback.addCancellable(vVar2);
        xVar.d();
        onBackPressedCallback.setEnabledChangedCallback$activity_release(new w(0, xVar, x.class, "updateEnabledCallbacks", "updateEnabledCallbacks()V", 0, 1));
        this.f6088g = vVar2;
    }

    @Override // androidx.activity.c
    public final void cancel() {
        this.f6086e.b(this);
        this.f6087f.removeCancellable(this);
        v vVar = this.f6088g;
        if (vVar != null) {
            vVar.cancel();
        }
        this.f6088g = null;
    }
}
