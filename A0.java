package androidx.transition;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import c4.HandlerC0438c;
import d6.AbstractC0476d;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class a0 extends U {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ArrayList f9399e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f9400f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f9401g;
    public boolean h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f9402i;

    public a0() {
        this.f9399e = new ArrayList();
        this.f9400f = true;
        this.h = false;
        this.f9402i = 0;
    }

    @Override // androidx.transition.U
    public final U addListener(T t8) {
        return (a0) super.addListener(t8);
    }

    @Override // androidx.transition.U
    public final U addTarget(View view) {
        for (int i5 = 0; i5 < this.f9399e.size(); i5++) {
            ((U) this.f9399e.get(i5)).addTarget(view);
        }
        return (a0) super.addTarget(view);
    }

    @Override // androidx.transition.U
    public final void cancel() {
        super.cancel();
        int size = this.f9399e.size();
        for (int i5 = 0; i5 < size; i5++) {
            ((U) this.f9399e.get(i5)).cancel();
        }
    }

    @Override // androidx.transition.U
    public final void captureEndValues(c0 c0Var) {
        if (isValidTarget(c0Var.f9408b)) {
            for (U u5 : this.f9399e) {
                if (u5.isValidTarget(c0Var.f9408b)) {
                    u5.captureEndValues(c0Var);
                    c0Var.f9409c.add(u5);
                }
            }
        }
    }

    @Override // androidx.transition.U
    public final void capturePropagationValues(c0 c0Var) {
        super.capturePropagationValues(c0Var);
        int size = this.f9399e.size();
        for (int i5 = 0; i5 < size; i5++) {
            ((U) this.f9399e.get(i5)).capturePropagationValues(c0Var);
        }
    }

    @Override // androidx.transition.U
    public final void captureStartValues(c0 c0Var) {
        if (isValidTarget(c0Var.f9408b)) {
            for (U u5 : this.f9399e) {
                if (u5.isValidTarget(c0Var.f9408b)) {
                    u5.captureStartValues(c0Var);
                    c0Var.f9409c.add(u5);
                }
            }
        }
    }

    @Override // androidx.transition.U
    public final void createAnimators(ViewGroup viewGroup, d0 d0Var, d0 d0Var2, ArrayList arrayList, ArrayList arrayList2) {
        long startDelay = getStartDelay();
        int size = this.f9399e.size();
        for (int i5 = 0; i5 < size; i5++) {
            U u5 = (U) this.f9399e.get(i5);
            if (startDelay > 0 && (this.f9400f || i5 == 0)) {
                long startDelay2 = u5.getStartDelay();
                if (startDelay2 > 0) {
                    u5.setStartDelay(startDelay2 + startDelay);
                } else {
                    u5.setStartDelay(startDelay);
                }
            }
            u5.createAnimators(viewGroup, d0Var, d0Var2, arrayList, arrayList2);
        }
    }

    public final void e(HandlerC0438c handlerC0438c) {
    }

    @Override // androidx.transition.U
    public final U excludeTarget(View view, boolean z9) {
        for (int i5 = 0; i5 < this.f9399e.size(); i5++) {
            ((U) this.f9399e.get(i5)).excludeTarget(view, z9);
        }
        return super.excludeTarget(view, z9);
    }

    public final void f(U u5) {
        this.f9399e.add(u5);
        u5.mParent = this;
        long j5 = this.mDuration;
        if (j5 >= 0) {
            u5.setDuration(j5);
        }
        if ((this.f9402i & 1) != 0) {
            u5.setInterpolator(getInterpolator());
        }
        if ((this.f9402i & 2) != 0) {
            u5.setPropagation(getPropagation());
        }
        if ((this.f9402i & 4) != 0) {
            u5.setPathMotion(getPathMotion());
        }
        if ((this.f9402i & 8) != 0) {
            u5.setEpicenterCallback(getEpicenterCallback());
        }
    }

    @Override // androidx.transition.U
    public final void forceToEnd(ViewGroup viewGroup) {
        super.forceToEnd(viewGroup);
        int size = this.f9399e.size();
        for (int i5 = 0; i5 < size; i5++) {
            ((U) this.f9399e.get(i5)).forceToEnd(viewGroup);
        }
    }

    public final void g(HandlerC0438c handlerC0438c) {
    }

    public final void h(U u5) {
        this.f9399e.remove(u5);
        u5.mParent = null;
    }

    public final void i(long j5) {
        ArrayList arrayList;
        super.setDuration(j5);
        if (this.mDuration < 0 || (arrayList = this.f9399e) == null) {
            return;
        }
        int size = arrayList.size();
        for (int i5 = 0; i5 < size; i5++) {
            ((U) this.f9399e.get(i5)).setDuration(j5);
        }
    }

    @Override // androidx.transition.U
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public final a0 setInterpolator(TimeInterpolator timeInterpolator) {
        this.f9402i |= 1;
        ArrayList arrayList = this.f9399e;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i5 = 0; i5 < size; i5++) {
                ((U) this.f9399e.get(i5)).setInterpolator(timeInterpolator);
            }
        }
        return (a0) super.setInterpolator(timeInterpolator);
    }

    public final void k(int i5) {
        if (i5 == 0) {
            this.f9400f = true;
        } else {
            if (i5 != 1) {
                throw new AndroidRuntimeException(A8.l.o(i5, "Invalid parameter for TransitionSet ordering: "));
            }
            this.f9400f = false;
        }
    }

    @Override // androidx.transition.U
    public final void pause(View view) {
        super.pause(view);
        int size = this.f9399e.size();
        for (int i5 = 0; i5 < size; i5++) {
            ((U) this.f9399e.get(i5)).pause(view);
        }
    }

    @Override // androidx.transition.U
    public final U removeListener(T t8) {
        return (a0) super.removeListener(t8);
    }

    @Override // androidx.transition.U
    public final U removeTarget(int i5) {
        for (int i7 = 0; i7 < this.f9399e.size(); i7++) {
            ((U) this.f9399e.get(i7)).removeTarget(i5);
        }
        return (a0) super.removeTarget(i5);
    }

    @Override // androidx.transition.U
    public final void resume(View view) {
        super.resume(view);
        int size = this.f9399e.size();
        for (int i5 = 0; i5 < size; i5++) {
            ((U) this.f9399e.get(i5)).resume(view);
        }
    }

    @Override // androidx.transition.U
    public final void runAnimators() {
        if (this.f9399e.isEmpty()) {
            start();
            end();
            return;
        }
        C0392u c0392u = new C0392u();
        c0392u.f9502f = this;
        Iterator it = this.f9399e.iterator();
        while (it.hasNext()) {
            ((U) it.next()).addListener(c0392u);
        }
        this.f9401g = this.f9399e.size();
        if (this.f9400f) {
            Iterator it2 = this.f9399e.iterator();
            while (it2.hasNext()) {
                ((U) it2.next()).runAnimators();
            }
            return;
        }
        for (int i5 = 1; i5 < this.f9399e.size(); i5++) {
            ((U) this.f9399e.get(i5 - 1)).addListener(new C0392u(1, (U) this.f9399e.get(i5)));
        }
        U u5 = (U) this.f9399e.get(0);
        if (u5 != null) {
            u5.runAnimators();
        }
    }

    @Override // androidx.transition.U
    public final void setCanRemoveViews(boolean z9) {
        super.setCanRemoveViews(z9);
        int size = this.f9399e.size();
        for (int i5 = 0; i5 < size; i5++) {
            ((U) this.f9399e.get(i5)).setCanRemoveViews(z9);
        }
    }

    @Override // androidx.transition.U
    public final /* bridge */ /* synthetic */ U setDuration(long j5) {
        i(j5);
        return this;
    }

    @Override // androidx.transition.U
    public final void setEpicenterCallback(S s8) {
        super.setEpicenterCallback(s8);
        this.f9402i |= 8;
        int size = this.f9399e.size();
        for (int i5 = 0; i5 < size; i5++) {
            ((U) this.f9399e.get(i5)).setEpicenterCallback(s8);
        }
    }

    @Override // androidx.transition.U
    public final void setPathMotion(H h) {
        super.setPathMotion(h);
        this.f9402i |= 4;
        if (this.f9399e != null) {
            for (int i5 = 0; i5 < this.f9399e.size(); i5++) {
                ((U) this.f9399e.get(i5)).setPathMotion(h);
            }
        }
    }

    @Override // androidx.transition.U
    public final void setPropagation(Z z9) {
        super.setPropagation(z9);
        this.f9402i |= 2;
        int size = this.f9399e.size();
        for (int i5 = 0; i5 < size; i5++) {
            ((U) this.f9399e.get(i5)).setPropagation(z9);
        }
    }

    @Override // androidx.transition.U
    public final U setStartDelay(long j5) {
        return (a0) super.setStartDelay(j5);
    }

    @Override // androidx.transition.U
    public final String toString(String str) {
        String string = super.toString(str);
        for (int i5 = 0; i5 < this.f9399e.size(); i5++) {
            StringBuilder sbP = AbstractC0476d.p(string, "\n");
            sbP.append(((U) this.f9399e.get(i5)).toString(str + "  "));
            string = sbP.toString();
        }
        return string;
    }

    @Override // androidx.transition.U
    /* JADX INFO: renamed from: clone */
    public final U mo7clone() {
        a0 a0Var = (a0) super.mo7clone();
        a0Var.f9399e = new ArrayList();
        int size = this.f9399e.size();
        for (int i5 = 0; i5 < size; i5++) {
            U uMo7clone = ((U) this.f9399e.get(i5)).mo7clone();
            a0Var.f9399e.add(uMo7clone);
            uMo7clone.mParent = a0Var;
        }
        return a0Var;
    }

    @Override // androidx.transition.U
    public final U addTarget(int i5) {
        for (int i7 = 0; i7 < this.f9399e.size(); i7++) {
            ((U) this.f9399e.get(i7)).addTarget(i5);
        }
        return (a0) super.addTarget(i5);
    }

    @Override // androidx.transition.U
    public final U excludeTarget(String str, boolean z9) {
        for (int i5 = 0; i5 < this.f9399e.size(); i5++) {
            ((U) this.f9399e.get(i5)).excludeTarget(str, z9);
        }
        return super.excludeTarget(str, z9);
    }

    @Override // androidx.transition.U
    public final U removeTarget(View view) {
        for (int i5 = 0; i5 < this.f9399e.size(); i5++) {
            ((U) this.f9399e.get(i5)).removeTarget(view);
        }
        return (a0) super.removeTarget(view);
    }

    public a0(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f9399e = new ArrayList();
        this.f9400f = true;
        this.h = false;
        this.f9402i = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, N.h);
        k(C.b.c(typedArrayObtainStyledAttributes, (XmlResourceParser) attributeSet, "transitionOrdering", 0, 0));
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.transition.U
    public final U addTarget(String str) {
        for (int i5 = 0; i5 < this.f9399e.size(); i5++) {
            ((U) this.f9399e.get(i5)).addTarget(str);
        }
        return (a0) super.addTarget(str);
    }

    @Override // androidx.transition.U
    public final U excludeTarget(int i5, boolean z9) {
        for (int i7 = 0; i7 < this.f9399e.size(); i7++) {
            ((U) this.f9399e.get(i7)).excludeTarget(i5, z9);
        }
        return super.excludeTarget(i5, z9);
    }

    @Override // androidx.transition.U
    public final U removeTarget(Class cls) {
        for (int i5 = 0; i5 < this.f9399e.size(); i5++) {
            ((U) this.f9399e.get(i5)).removeTarget((Class<?>) cls);
        }
        return (a0) super.removeTarget((Class<?>) cls);
    }

    @Override // androidx.transition.U
    public final U addTarget(Class cls) {
        for (int i5 = 0; i5 < this.f9399e.size(); i5++) {
            ((U) this.f9399e.get(i5)).addTarget((Class<?>) cls);
        }
        return (a0) super.addTarget((Class<?>) cls);
    }

    @Override // androidx.transition.U
    public final U excludeTarget(Class cls, boolean z9) {
        for (int i5 = 0; i5 < this.f9399e.size(); i5++) {
            ((U) this.f9399e.get(i5)).excludeTarget((Class<?>) cls, z9);
        }
        return super.excludeTarget((Class<?>) cls, z9);
    }

    @Override // androidx.transition.U
    public final U removeTarget(String str) {
        for (int i5 = 0; i5 < this.f9399e.size(); i5++) {
            ((U) this.f9399e.get(i5)).removeTarget(str);
        }
        return (a0) super.removeTarget(str);
    }
}
