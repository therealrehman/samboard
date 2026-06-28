package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.PathInterpolator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: androidx.recyclerview.widget.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0344l implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f9183e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ Object f9184f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ Object f9185g;

    public /* synthetic */ RunnableC0344l(int i5, Object obj, Object obj2) {
        this.f9183e = i5;
        this.f9185g = obj;
        this.f9184f = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArrayList arrayList;
        long j5;
        RunnableC0344l runnableC0344l = this;
        switch (runnableC0344l.f9183e) {
            case 0:
                ArrayList arrayList2 = (ArrayList) runnableC0344l.f9184f;
                Iterator it = arrayList2.iterator();
                while (true) {
                    boolean zHasNext = it.hasNext();
                    C0357s c0357s = (C0357s) runnableC0344l.f9185g;
                    if (!zHasNext) {
                        arrayList2.clear();
                        c0357s.f9260j.remove(arrayList2);
                    } else {
                        r rVar = (r) it.next();
                        V0 v02 = rVar.f9247a;
                        c0357s.getClass();
                        View view = v02.itemView;
                        int i5 = rVar.f9250d - rVar.f9248b;
                        int i7 = rVar.f9251e - rVar.f9249c;
                        if (i5 != 0) {
                            view.animate().translationX(0.0f);
                        }
                        if (i7 != 0) {
                            view.animate().translationY(0.0f);
                        }
                        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
                        viewPropertyAnimatorAnimate.setInterpolator(C0357s.f9255s);
                        c0357s.f9263m.add(v02);
                        View view2 = c0357s.f9269c;
                        if (view2 instanceof RecyclerView) {
                            RecyclerView recyclerView = (RecyclerView) view2;
                            if (recyclerView.mBlackTop != -1 && v02.getLayoutPosition() == recyclerView.mChildHelper.e() - 1) {
                                viewPropertyAnimatorAnimate.setUpdateListener(new C0348n(recyclerView, 0));
                            }
                        }
                        viewPropertyAnimatorAnimate.setDuration(400L).setListener(new C0350o(c0357s, v02, i5, view, i7, viewPropertyAnimatorAnimate)).start();
                    }
                    break;
                }
                break;
            case 1:
                ArrayList arrayList3 = (ArrayList) runnableC0344l.f9184f;
                Iterator it2 = arrayList3.iterator();
                while (true) {
                    boolean zHasNext2 = it2.hasNext();
                    C0357s c0357s2 = (C0357s) runnableC0344l.f9185g;
                    if (!zHasNext2) {
                        arrayList3.clear();
                        c0357s2.f9261k.remove(arrayList3);
                    } else {
                        C0354q c0354q = (C0354q) it2.next();
                        c0357s2.getClass();
                        V0 v03 = c0354q.f9241a;
                        View view3 = v03 == null ? null : v03.itemView;
                        V0 v04 = c0354q.f9242b;
                        View view4 = v04 != null ? v04.itemView : null;
                        PathInterpolator pathInterpolator = C0357s.f9255s;
                        ArrayList arrayList4 = c0357s2.o;
                        if (view3 != null) {
                            ViewPropertyAnimator duration = view3.animate().setDuration(400L);
                            arrayList4.add(c0354q.f9241a);
                            duration.translationX(c0354q.f9245e - c0354q.f9243c);
                            duration.translationY(c0354q.f9246f - c0354q.f9244d);
                            arrayList = arrayList3;
                            j5 = 400;
                            duration.alpha(0.0f).setDuration(400L).setInterpolator(pathInterpolator).setListener(new C0352p(c0357s2, c0354q, duration, view3, 0)).start();
                        } else {
                            arrayList = arrayList3;
                            j5 = 400;
                        }
                        if (view4 != null) {
                            ViewPropertyAnimator viewPropertyAnimatorAnimate2 = view4.animate();
                            arrayList4.add(c0354q.f9242b);
                            viewPropertyAnimatorAnimate2.translationX(0.0f).translationY(0.0f).setDuration(j5).alpha(1.0f).setInterpolator(pathInterpolator).setListener(new C0352p(c0357s2, c0354q, viewPropertyAnimatorAnimate2, view4, 1)).start();
                        }
                        runnableC0344l = this;
                        arrayList3 = arrayList;
                    }
                    break;
                }
                break;
            case 2:
                ArrayList arrayList5 = (ArrayList) runnableC0344l.f9184f;
                Iterator it3 = arrayList5.iterator();
                while (true) {
                    boolean zHasNext3 = it3.hasNext();
                    C0357s c0357s3 = (C0357s) runnableC0344l.f9185g;
                    if (!zHasNext3) {
                        arrayList5.clear();
                        c0357s3.f9259i.remove(arrayList5);
                    } else {
                        V0 v05 = (V0) it3.next();
                        c0357s3.getClass();
                        View view5 = v05.itemView;
                        ViewPropertyAnimator viewPropertyAnimatorAnimate3 = view5.animate();
                        long j9 = (view5.getTag() == null || !view5.getTag().equals("preferencecategory")) ? 200L : 0L;
                        c0357s3.f9262l.add(v05);
                        viewPropertyAnimatorAnimate3.alpha(1.0f).setDuration(j9).setListener(new C0346m(c0357s3, v05, view5, viewPropertyAnimatorAnimate3)).start();
                    }
                    break;
                }
                break;
            default:
                androidx.fragment.app.q0 q0Var = (androidx.fragment.app.q0) runnableC0344l.f9185g;
                C0338i c0338i = (C0338i) q0Var.f7718j;
                if (c0338i.f9156g == q0Var.f7715f) {
                    List list = q0Var.h;
                    Runnable runnable = (Runnable) q0Var.f7717i;
                    List list2 = c0338i.f9155f;
                    c0338i.f9154e = list;
                    c0338i.f9155f = Collections.unmodifiableList(list);
                    ((C0361u) runnableC0344l.f9184f).a(c0338i.f9150a);
                    c0338i.a(list2, runnable);
                }
                break;
        }
    }
}
