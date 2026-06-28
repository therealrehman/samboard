package androidx.fragment.app;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.samsung.android.keyscafe.R;
import d6.AbstractC0476d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: androidx.fragment.app.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0246m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewGroup f7694a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f7695b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f7696c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f7697d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f7698e;

    public C0246m(ViewGroup container) {
        kotlin.jvm.internal.j.f(container, "container");
        this.f7694a = container;
        this.f7695b = new ArrayList();
        this.f7696c = new ArrayList();
    }

    public static void a(View view, ArrayList arrayList) {
        if (!(view instanceof ViewGroup)) {
            if (arrayList.contains(view)) {
                return;
            }
            arrayList.add(view);
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        if (androidx.core.view.a0.b(viewGroup)) {
            if (arrayList.contains(view)) {
                return;
            }
            arrayList.add(view);
            return;
        }
        int childCount = viewGroup.getChildCount();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = viewGroup.getChildAt(i5);
            if (childAt.getVisibility() == 0) {
                a(childAt, arrayList);
            }
        }
    }

    public static void i(q.b bVar, View view) {
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        String strK = androidx.core.view.M.k(view);
        if (strK != null) {
            bVar.put(strK, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = viewGroup.getChildAt(i5);
                if (childAt.getVisibility() == 0) {
                    i(bVar, childAt);
                }
            }
        }
    }

    public static final C0246m l(ViewGroup container, Y fragmentManager) {
        kotlin.jvm.internal.j.f(container, "container");
        kotlin.jvm.internal.j.f(fragmentManager, "fragmentManager");
        kotlin.jvm.internal.j.e(fragmentManager.E(), "fragmentManager.specialEffectsControllerFactory");
        Object tag = container.getTag(R.id.special_effects_controller_view_tag);
        if (tag instanceof C0246m) {
            return (C0246m) tag;
        }
        C0246m c0246m = new C0246m(container);
        container.setTag(R.id.special_effects_controller_view_tag, c0246m);
        return c0246m;
    }

    public static void n(q.b bVar, Collection collection) {
        Set entries = bVar.entrySet();
        kotlin.jvm.internal.j.e(entries, "entries");
        B7.m mVar = new B7.m(17, collection);
        Iterator it = ((q.h) entries).iterator();
        while (it.hasNext()) {
            if (!((Boolean) mVar.invoke(it.next())).booleanValue()) {
                it.remove();
            }
        }
    }

    public final void b(int i5, int i7, f0 f0Var) {
        synchronized (this.f7695b) {
            H.f fVar = new H.f();
            Fragment fragment = f0Var.f7642c;
            kotlin.jvm.internal.j.e(fragment, "fragmentStateManager.fragment");
            v0 v0VarJ = j(fragment);
            if (v0VarJ != null) {
                v0VarJ.c(i5, i7);
                return;
            }
            v0 v0Var = new v0(i5, i7, f0Var, fVar);
            this.f7695b.add(v0Var);
            v0Var.f7739d.add(new u0(this, v0Var, 0));
            v0Var.f7739d.add(new u0(this, v0Var, 1));
        }
    }

    public final void c(int i5, f0 fragmentStateManager) {
        AbstractC0476d.s(i5, "finalState");
        kotlin.jvm.internal.j.f(fragmentStateManager, "fragmentStateManager");
        if (Log.isLoggable("FragmentManager", 2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing add operation for fragment " + fragmentStateManager.f7642c);
        }
        b(i5, 2, fragmentStateManager);
    }

    public final void d(f0 fragmentStateManager) {
        kotlin.jvm.internal.j.f(fragmentStateManager, "fragmentStateManager");
        if (Log.isLoggable("FragmentManager", 2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing hide operation for fragment " + fragmentStateManager.f7642c);
        }
        b(3, 1, fragmentStateManager);
    }

    public final void e(f0 fragmentStateManager) {
        kotlin.jvm.internal.j.f(fragmentStateManager, "fragmentStateManager");
        if (Log.isLoggable("FragmentManager", 2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing remove operation for fragment " + fragmentStateManager.f7642c);
        }
        b(1, 3, fragmentStateManager);
    }

    public final void f(f0 fragmentStateManager) {
        kotlin.jvm.internal.j.f(fragmentStateManager, "fragmentStateManager");
        if (Log.isLoggable("FragmentManager", 2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Enqueuing show operation for fragment " + fragmentStateManager.f7642c);
        }
        b(2, 1, fragmentStateManager);
    }

    /* JADX WARN: Removed duplicated region for block: B:152:0x0504  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x050f  */
    /* JADX WARN: Removed duplicated region for block: B:368:0x0531 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:370:0x051d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void g(java.util.ArrayList r41, final boolean r42) {
        /*
            Method dump skipped, instruction units count: 2313
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.C0246m.g(java.util.ArrayList, boolean):void");
    }

    public final void h() {
        if (this.f7698e) {
            return;
        }
        ViewGroup viewGroup = this.f7694a;
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        if (!viewGroup.isAttachedToWindow()) {
            k();
            this.f7697d = false;
            return;
        }
        synchronized (this.f7695b) {
            try {
                if (!this.f7695b.isEmpty()) {
                    ArrayList<v0> arrayListO0 = U6.n.O0(this.f7696c);
                    this.f7696c.clear();
                    for (v0 v0Var : arrayListO0) {
                        if (Log.isLoggable("FragmentManager", 2)) {
                            Log.v("FragmentManager", "SpecialEffectsController: Cancelling operation " + v0Var);
                        }
                        v0Var.a();
                        if (!v0Var.f7742g) {
                            this.f7696c.add(v0Var);
                        }
                    }
                    o();
                    ArrayList arrayListO02 = U6.n.O0(this.f7695b);
                    this.f7695b.clear();
                    this.f7696c.addAll(arrayListO02);
                    if (Log.isLoggable("FragmentManager", 2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Executing pending operations");
                    }
                    Iterator it = arrayListO02.iterator();
                    while (it.hasNext()) {
                        ((v0) it.next()).d();
                    }
                    g(arrayListO02, this.f7697d);
                    this.f7697d = false;
                    if (Log.isLoggable("FragmentManager", 2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: Finished executing pending operations");
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final v0 j(Fragment fragment) {
        Object next;
        Iterator it = this.f7695b.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            v0 v0Var = (v0) next;
            if (kotlin.jvm.internal.j.a(v0Var.f7738c, fragment) && !v0Var.f7741f) {
                break;
            }
        }
        return (v0) next;
    }

    public final void k() {
        if (Log.isLoggable("FragmentManager", 2)) {
            Log.v("FragmentManager", "SpecialEffectsController: Forcing all operations to complete");
        }
        ViewGroup viewGroup = this.f7694a;
        WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
        boolean zIsAttachedToWindow = viewGroup.isAttachedToWindow();
        synchronized (this.f7695b) {
            try {
                o();
                Iterator it = this.f7695b.iterator();
                while (it.hasNext()) {
                    ((v0) it.next()).d();
                }
                for (v0 v0Var : U6.n.O0(this.f7696c)) {
                    if (Log.isLoggable("FragmentManager", 2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: " + (zIsAttachedToWindow ? "" : "Container " + this.f7694a + " is not attached to window. ") + "Cancelling running operation " + v0Var);
                    }
                    v0Var.a();
                }
                for (v0 v0Var2 : U6.n.O0(this.f7695b)) {
                    if (Log.isLoggable("FragmentManager", 2)) {
                        Log.v("FragmentManager", "SpecialEffectsController: " + (zIsAttachedToWindow ? "" : "Container " + this.f7694a + " is not attached to window. ") + "Cancelling pending operation " + v0Var2);
                    }
                    v0Var2.a();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void m() {
        Object objPrevious;
        synchronized (this.f7695b) {
            try {
                o();
                ArrayList arrayList = this.f7695b;
                ListIterator listIterator = arrayList.listIterator(arrayList.size());
                while (true) {
                    if (!listIterator.hasPrevious()) {
                        objPrevious = null;
                        break;
                    }
                    objPrevious = listIterator.previous();
                    v0 v0Var = (v0) objPrevious;
                    View view = v0Var.f7738c.mView;
                    kotlin.jvm.internal.j.e(view, "operation.fragment.mView");
                    int iC = s6.c.c(view);
                    if (v0Var.f7736a == 2 && iC != 2) {
                        break;
                    }
                }
                v0 v0Var2 = (v0) objPrevious;
                Fragment fragment = v0Var2 != null ? v0Var2.f7738c : null;
                this.f7698e = fragment != null ? fragment.isPostponed() : false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void o() {
        for (v0 v0Var : this.f7695b) {
            int i5 = 2;
            if (v0Var.f7737b == 2) {
                View viewRequireView = v0Var.f7738c.requireView();
                kotlin.jvm.internal.j.e(viewRequireView, "fragment.requireView()");
                int visibility = viewRequireView.getVisibility();
                if (visibility != 0) {
                    i5 = 4;
                    if (visibility != 4) {
                        if (visibility != 8) {
                            throw new IllegalArgumentException(A8.l.o(visibility, "Unknown visibility "));
                        }
                        i5 = 3;
                    }
                }
                v0Var.c(i5, 1);
            }
        }
    }
}
