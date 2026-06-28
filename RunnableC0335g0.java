package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: androidx.recyclerview.widget.g0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC0335g0 implements Runnable {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f9142e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ RecyclerView f9143f;

    public /* synthetic */ RunnableC0335g0(RecyclerView recyclerView, int i5) {
        this.f9142e = i5;
        this.f9143f = recyclerView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z9;
        RecyclerView recyclerView;
        RecyclerView recyclerView2;
        RecyclerView recyclerView3;
        long j5;
        RecyclerView recyclerView4 = this.f9143f;
        switch (this.f9142e) {
            case 0:
                if (recyclerView4.mFirstLayoutComplete && !recyclerView4.isLayoutRequested()) {
                    if (!recyclerView4.mIsAttached) {
                        recyclerView4.requestLayout();
                    } else if (recyclerView4.mLayoutSuppressed) {
                        recyclerView4.mLayoutWasDefered = true;
                    } else {
                        recyclerView4.consumePendingUpdateOperations();
                    }
                    break;
                }
                break;
            case 1:
                AbstractC0358s0 abstractC0358s0 = recyclerView4.mItemAnimator;
                if (abstractC0358s0 != null) {
                    C0357s c0357s = (C0357s) abstractC0358s0;
                    ArrayList<V0> arrayList = c0357s.f9256e;
                    boolean z10 = !arrayList.isEmpty();
                    ArrayList arrayList2 = c0357s.f9258g;
                    boolean z11 = !arrayList2.isEmpty();
                    ArrayList arrayList3 = c0357s.h;
                    boolean z12 = !arrayList3.isEmpty();
                    ArrayList arrayList4 = c0357s.f9257f;
                    boolean z13 = !arrayList4.isEmpty();
                    if (z10 || z11 || z13 || z12) {
                        for (V0 v02 : arrayList) {
                            View view = v02.itemView;
                            ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
                            if (view.getTag() == null || !view.getTag().equals("preferencecategory")) {
                                recyclerView3 = recyclerView4;
                                j5 = 100;
                            } else {
                                recyclerView3 = recyclerView4;
                                j5 = 0;
                            }
                            c0357s.f9264n.add(v02);
                            viewPropertyAnimatorAnimate.setDuration(j5).alpha(0.0f).setListener(new C0346m(c0357s, v02, viewPropertyAnimatorAnimate, view)).start();
                            recyclerView4 = recyclerView3;
                        }
                        recyclerView2 = recyclerView4;
                        arrayList.clear();
                        if (z11) {
                            ArrayList arrayList5 = new ArrayList();
                            arrayList5.addAll(arrayList2);
                            c0357s.f9260j.add(arrayList5);
                            arrayList2.clear();
                            new RunnableC0344l(0, c0357s, arrayList5).run();
                        }
                        if (z12) {
                            ArrayList arrayList6 = new ArrayList();
                            arrayList6.addAll(arrayList3);
                            c0357s.f9261k.add(arrayList6);
                            arrayList3.clear();
                            new RunnableC0344l(1, c0357s, arrayList6).run();
                        }
                        if (z13) {
                            ArrayList arrayList7 = new ArrayList();
                            arrayList7.addAll(arrayList4);
                            c0357s.f9259i.add(arrayList7);
                            arrayList4.clear();
                            RunnableC0344l runnableC0344l = new RunnableC0344l(2, c0357s, arrayList7);
                            if (z10 || z11 || z12) {
                                View view2 = ((V0) arrayList7.get(0)).itemView;
                                if (view2.getTag() == null || !view2.getTag().equals("preferencecategory")) {
                                    WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
                                    view2.postOnAnimationDelayed(runnableC0344l, 100L);
                                } else {
                                    runnableC0344l.run();
                                }
                            } else {
                                runnableC0344l.run();
                            }
                        }
                    } else {
                        recyclerView2 = recyclerView4;
                    }
                    recyclerView = recyclerView2;
                    z9 = false;
                } else {
                    z9 = false;
                    recyclerView = recyclerView4;
                }
                recyclerView.mPostedAnimatorRunner = z9;
                break;
            case 2:
                recyclerView4.ensureTopGlow();
                recyclerView4.mTopGlow.onAbsorb(10000);
                recyclerView4.invalidate();
                break;
            case 3:
                View childAt = recyclerView4.getChildAt(0);
                if (childAt != null) {
                    childAt.requestFocus();
                }
                break;
            case 4:
                RecyclerView.access$300(recyclerView4);
                break;
            case 5:
                RecyclerView.access$400(recyclerView4);
                break;
            default:
                recyclerView4.setupGoToTop(0);
                break;
        }
    }
}
