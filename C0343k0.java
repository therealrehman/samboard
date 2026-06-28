package androidx.recyclerview.widget;

import android.database.Observable;

/* JADX INFO: renamed from: androidx.recyclerview.widget.k0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0343k0 extends Observable {
    public final boolean a() {
        return !((Observable) this).mObservers.isEmpty();
    }

    public final void b() {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            ((AbstractC0345l0) ((Observable) this).mObservers.get(size)).onChanged();
        }
    }

    public final void c(int i5, int i7) {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            ((AbstractC0345l0) ((Observable) this).mObservers.get(size)).onItemRangeMoved(i5, i7, 1);
        }
    }

    public final void d(int i5, int i7, Object obj) {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            ((AbstractC0345l0) ((Observable) this).mObservers.get(size)).onItemRangeChanged(i5, i7, obj);
        }
    }

    public final void e(int i5, int i7) {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            ((AbstractC0345l0) ((Observable) this).mObservers.get(size)).onItemRangeInserted(i5, i7);
        }
    }

    public final void f(int i5, int i7) {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            ((AbstractC0345l0) ((Observable) this).mObservers.get(size)).onItemRangeRemoved(i5, i7);
        }
    }

    public final void g() {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            ((AbstractC0345l0) ((Observable) this).mObservers.get(size)).onStateRestorationPolicyChanged();
        }
    }
}
