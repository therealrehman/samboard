package androidx.recyclerview.widget;

import android.os.Trace;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: renamed from: androidx.recyclerview.widget.j0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0341j0 {
    private final C0343k0 mObservable = new C0343k0();
    private boolean mHasStableIds = false;
    private EnumC0339i0 mStateRestorationPolicy = EnumC0339i0.f9157e;

    public final void bindViewHolder(V0 v02, int i5) {
        boolean z9 = v02.mBindingAdapter == null;
        if (z9) {
            v02.mPosition = i5;
            if (hasStableIds()) {
                v02.mItemId = getItemId(i5);
            }
            v02.setFlags(1, 519);
            Trace.beginSection("RV OnBindView");
        }
        v02.mBindingAdapter = this;
        if (RecyclerView.sDebugAssertionsEnabled) {
            if (v02.itemView.getParent() == null) {
                View view = v02.itemView;
                WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
                if (view.isAttachedToWindow() != v02.isTmpDetached()) {
                    throw new IllegalStateException("Temp-detached state out of sync with reality. holder.isTmpDetached(): " + v02.isTmpDetached() + ", attached to window: " + v02.itemView.isAttachedToWindow() + ", holder: " + v02);
                }
            }
            if (v02.itemView.getParent() == null) {
                View view2 = v02.itemView;
                WeakHashMap weakHashMap2 = androidx.core.view.W.f7199a;
                if (view2.isAttachedToWindow()) {
                    throw new IllegalStateException("Attempting to bind attached holder with no parent (AKA temp detached): " + v02);
                }
            }
        }
        onBindViewHolder(v02, i5, v02.getUnmodifiedPayloads());
        if (z9) {
            v02.clearPayload();
            ViewGroup.LayoutParams layoutParams = v02.itemView.getLayoutParams();
            if (layoutParams instanceof C0372z0) {
                ((C0372z0) layoutParams).mInsetsDirty = true;
            }
            Trace.endSection();
        }
    }

    public boolean canRestoreState() {
        int iOrdinal = this.mStateRestorationPolicy.ordinal();
        return iOrdinal != 1 ? iOrdinal != 2 : getItemCount() > 0;
    }

    public final V0 createViewHolder(ViewGroup viewGroup, int i5) {
        try {
            Trace.beginSection("RV CreateView");
            V0 v0OnCreateViewHolder = onCreateViewHolder(viewGroup, i5);
            if (v0OnCreateViewHolder.itemView.getParent() != null) {
                throw new IllegalStateException("ViewHolder views must not be attached when created. Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)");
            }
            v0OnCreateViewHolder.mItemViewType = i5;
            return v0OnCreateViewHolder;
        } finally {
            Trace.endSection();
        }
    }

    public int findRelativeAdapterPositionIn(AbstractC0341j0 abstractC0341j0, V0 v02, int i5) {
        if (abstractC0341j0 == this) {
            return i5;
        }
        return -1;
    }

    public abstract int getItemCount();

    public long getItemId(int i5) {
        return -1L;
    }

    public int getItemViewType(int i5) {
        return 0;
    }

    public final EnumC0339i0 getStateRestorationPolicy() {
        return this.mStateRestorationPolicy;
    }

    public final boolean hasObservers() {
        return this.mObservable.a();
    }

    public final boolean hasStableIds() {
        return this.mHasStableIds;
    }

    public final void notifyDataSetChanged() {
        this.mObservable.b();
    }

    public final void notifyItemChanged(int i5) {
        this.mObservable.d(i5, 1, null);
    }

    public final void notifyItemInserted(int i5) {
        this.mObservable.e(i5, 1);
    }

    public final void notifyItemMoved(int i5, int i7) {
        this.mObservable.c(i5, i7);
    }

    public final void notifyItemRangeChanged(int i5, int i7) {
        this.mObservable.d(i5, i7, null);
    }

    public final void notifyItemRangeInserted(int i5, int i7) {
        this.mObservable.e(i5, i7);
    }

    public final void notifyItemRangeRemoved(int i5, int i7) {
        this.mObservable.f(i5, i7);
    }

    public final void notifyItemRemoved(int i5) {
        this.mObservable.f(i5, 1);
    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    }

    public abstract void onBindViewHolder(V0 v02, int i5);

    public void onBindViewHolder(V0 v02, int i5, List<Object> list) {
        onBindViewHolder(v02, i5);
    }

    public abstract V0 onCreateViewHolder(ViewGroup viewGroup, int i5);

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
    }

    public boolean onFailedToRecycleView(V0 v02) {
        return false;
    }

    public void onViewAttachedToWindow(V0 v02) {
    }

    public void onViewDetachedFromWindow(V0 v02) {
    }

    public void onViewRecycled(V0 v02) {
    }

    public void registerAdapterDataObserver(AbstractC0345l0 abstractC0345l0) {
        this.mObservable.registerObserver(abstractC0345l0);
    }

    public int seslGetAccessibilityItemCount() {
        return getItemCount();
    }

    public int seslGetAccessibilityItemPosition(int i5) {
        return i5;
    }

    public boolean seslUseCustomAccessibilityPosition() {
        return false;
    }

    public void setHasStableIds(boolean z9) {
        if (hasObservers()) {
            throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
        }
        this.mHasStableIds = z9;
    }

    public void setStateRestorationPolicy(EnumC0339i0 enumC0339i0) {
        this.mStateRestorationPolicy = enumC0339i0;
        this.mObservable.g();
    }

    public void unregisterAdapterDataObserver(AbstractC0345l0 abstractC0345l0) {
        this.mObservable.unregisterObserver(abstractC0345l0);
    }

    public final void notifyItemChanged(int i5, Object obj) {
        this.mObservable.d(i5, 1, obj);
    }

    public final void notifyItemRangeChanged(int i5, int i7, Object obj) {
        this.mObservable.d(i5, i7, obj);
    }
}
