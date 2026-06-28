package androidx.databinding;

import android.util.Log;
import android.view.View;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public class MergedDataBinderMapper extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashSet f7352a = new HashSet();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final CopyOnWriteArrayList f7353b = new CopyOnWriteArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final CopyOnWriteArrayList f7354c = new CopyOnWriteArrayList();

    @Override // androidx.databinding.d
    public final u b(int i5, View view) {
        Iterator it = this.f7353b.iterator();
        while (it.hasNext()) {
            u uVarB = ((d) it.next()).b(i5, view);
            if (uVarB != null) {
                return uVarB;
            }
        }
        if (e()) {
            return b(i5, view);
        }
        return null;
    }

    @Override // androidx.databinding.d
    public final u c(View[] viewArr, int i5) {
        Iterator it = this.f7353b.iterator();
        while (it.hasNext()) {
            u uVarC = ((d) it.next()).c(viewArr, i5);
            if (uVarC != null) {
                return uVarC;
            }
        }
        if (e()) {
            return c(viewArr, i5);
        }
        return null;
    }

    public final void d(d dVar) {
        if (this.f7352a.add(dVar.getClass())) {
            this.f7353b.add(dVar);
            Iterator it = dVar.a().iterator();
            while (it.hasNext()) {
                d((d) it.next());
            }
        }
    }

    public final boolean e() {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = this.f7354c;
        boolean z9 = false;
        for (String str : copyOnWriteArrayList) {
            try {
                Class<?> cls = Class.forName(str);
                if (d.class.isAssignableFrom(cls)) {
                    d((d) cls.newInstance());
                    copyOnWriteArrayList.remove(str);
                    z9 = true;
                }
            } catch (ClassNotFoundException unused) {
            } catch (IllegalAccessException e3) {
                Log.e("MergedDataBinderMapper", "unable to add feature mapper for " + str, e3);
            } catch (InstantiationException e10) {
                Log.e("MergedDataBinderMapper", "unable to add feature mapper for " + str, e10);
            }
        }
        return z9;
    }
}
