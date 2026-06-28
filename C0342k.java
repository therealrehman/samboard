package androidx.recyclerview.widget;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* JADX INFO: renamed from: androidx.recyclerview.widget.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0342k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0327c0 f9178a;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public View f9182e;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f9181d = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final H0.a f9179b = new H0.a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final ArrayList f9180c = new ArrayList();

    public C0342k(C0327c0 c0327c0) {
        this.f9178a = c0327c0;
    }

    public final void a(View view, int i5, boolean z9) {
        C0327c0 c0327c0 = this.f9178a;
        int childCount = i5 < 0 ? c0327c0.f9119a.getChildCount() : f(i5);
        this.f9179b.e(childCount, z9);
        if (z9) {
            i(view);
        }
        RecyclerView recyclerView = c0327c0.f9119a;
        recyclerView.addView(view, childCount);
        recyclerView.dispatchChildAttached(view);
    }

    public final void b(View view, int i5, ViewGroup.LayoutParams layoutParams, boolean z9) {
        C0327c0 c0327c0 = this.f9178a;
        int childCount = i5 < 0 ? c0327c0.f9119a.getChildCount() : f(i5);
        this.f9179b.e(childCount, z9);
        if (z9) {
            i(view);
        }
        c0327c0.getClass();
        V0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        RecyclerView recyclerView = c0327c0.f9119a;
        if (childViewHolderInt != null) {
            if (!childViewHolderInt.isTmpDetached() && !childViewHolderInt.shouldIgnore()) {
                StringBuilder sb = new StringBuilder("Called attach on a child which is not detached: ");
                sb.append(childViewHolderInt);
                throw new IllegalArgumentException(A8.l.p(recyclerView, sb));
            }
            if (RecyclerView.sVerboseLoggingEnabled) {
                Log.d("SeslRecyclerView", "reAttach " + childViewHolderInt);
            }
            childViewHolderInt.clearTmpDetachFlag();
        } else if (RecyclerView.sDebugAssertionsEnabled) {
            StringBuilder sb2 = new StringBuilder("No ViewHolder found for child: ");
            sb2.append(view);
            sb2.append(", index: ");
            sb2.append(childCount);
            throw new IllegalArgumentException(A8.l.p(recyclerView, sb2));
        }
        recyclerView.attachViewToParent(view, childCount, layoutParams);
    }

    public final void c(int i5) {
        int iF = f(i5);
        this.f9179b.f(iF);
        RecyclerView recyclerView = this.f9178a.f9119a;
        View childAt = recyclerView.getChildAt(iF);
        if (childAt != null) {
            V0 childViewHolderInt = RecyclerView.getChildViewHolderInt(childAt);
            if (childViewHolderInt != null) {
                if (childViewHolderInt.isTmpDetached() && !childViewHolderInt.shouldIgnore()) {
                    StringBuilder sb = new StringBuilder("called detach on an already detached child ");
                    sb.append(childViewHolderInt);
                    throw new IllegalArgumentException(A8.l.p(recyclerView, sb));
                }
                if (RecyclerView.sVerboseLoggingEnabled) {
                    Log.d("SeslRecyclerView", "tmpDetach " + childViewHolderInt);
                }
                childViewHolderInt.addFlags(256);
            }
        } else if (RecyclerView.sDebugAssertionsEnabled) {
            StringBuilder sb2 = new StringBuilder("No view at offset ");
            sb2.append(iF);
            throw new IllegalArgumentException(A8.l.p(recyclerView, sb2));
        }
        recyclerView.detachViewFromParent(iF);
    }

    public final View d(int i5) {
        return this.f9178a.f9119a.getChildAt(f(i5));
    }

    public final int e() {
        return this.f9178a.f9119a.getChildCount() - this.f9180c.size();
    }

    public final int f(int i5) {
        if (i5 < 0) {
            return -1;
        }
        int childCount = this.f9178a.f9119a.getChildCount();
        int i7 = i5;
        while (i7 < childCount) {
            H0.a aVar = this.f9179b;
            int iB = i5 - (i7 - aVar.b(i7));
            if (iB == 0) {
                while (aVar.d(i7)) {
                    i7++;
                }
                return i7;
            }
            i7 += iB;
        }
        return -1;
    }

    public final View g(int i5) {
        return this.f9178a.f9119a.getChildAt(i5);
    }

    public final int h() {
        return this.f9178a.f9119a.getChildCount();
    }

    public final void i(View view) {
        this.f9180c.add(view);
        C0327c0 c0327c0 = this.f9178a;
        c0327c0.getClass();
        V0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            childViewHolderInt.onEnteredHiddenState(c0327c0.f9119a);
        }
    }

    public final int j(View view) {
        int iIndexOfChild = this.f9178a.f9119a.indexOfChild(view);
        if (iIndexOfChild == -1) {
            return -1;
        }
        H0.a aVar = this.f9179b;
        if (aVar.d(iIndexOfChild)) {
            return -1;
        }
        return iIndexOfChild - aVar.b(iIndexOfChild);
    }

    public final boolean k(View view) {
        return this.f9180c.contains(view);
    }

    public final void l(int i5) {
        C0327c0 c0327c0 = this.f9178a;
        int i7 = this.f9181d;
        if (i7 == 1) {
            throw new IllegalStateException("Cannot call removeView(At) within removeView(At)");
        }
        if (i7 == 2) {
            throw new IllegalStateException("Cannot call removeView(At) within removeViewIfHidden");
        }
        try {
            int iF = f(i5);
            View childAt = c0327c0.f9119a.getChildAt(iF);
            if (childAt == null) {
                this.f9181d = 0;
                this.f9182e = null;
                return;
            }
            this.f9181d = 1;
            this.f9182e = childAt;
            if (this.f9179b.f(iF)) {
                m(childAt);
            }
            c0327c0.c(iF);
            this.f9181d = 0;
            this.f9182e = null;
        } catch (Throwable th) {
            this.f9181d = 0;
            this.f9182e = null;
            throw th;
        }
    }

    public final void m(View view) {
        if (this.f9180c.remove(view)) {
            C0327c0 c0327c0 = this.f9178a;
            c0327c0.getClass();
            V0 childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
            if (childViewHolderInt != null) {
                childViewHolderInt.onLeftHiddenState(c0327c0.f9119a);
            }
        }
    }

    public final String toString() {
        return this.f9179b.toString() + ", hidden list:" + this.f9180c.size();
    }
}
