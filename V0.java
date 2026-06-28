package androidx.recyclerview.widget;

import android.util.Log;
import android.view.View;
import d6.AbstractC0476d;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class V0 {
    static final int FLAG_ADAPTER_FULLUPDATE = 1024;
    static final int FLAG_ADAPTER_POSITION_UNKNOWN = 512;
    static final int FLAG_APPEARED_IN_PRE_LAYOUT = 4096;
    static final int FLAG_BOUNCED_FROM_HIDDEN_LIST = 8192;
    static final int FLAG_BOUND = 1;
    static final int FLAG_IGNORE = 128;
    static final int FLAG_INVALID = 4;
    static final int FLAG_MOVED = 2048;
    static final int FLAG_NOT_RECYCLABLE = 16;
    static final int FLAG_REMOVED = 8;
    static final int FLAG_RETURNED_FROM_SCRAP = 32;
    static final int FLAG_TMP_DETACHED = 256;
    static final int FLAG_UPDATE = 2;
    private static final List<Object> FULLUPDATE_PAYLOADS = Collections.emptyList();
    static final int PENDING_ACCESSIBILITY_STATE_NOT_SET = -1;
    public final View itemView;
    AbstractC0341j0 mBindingAdapter;
    int mFlags;
    WeakReference<RecyclerView> mNestedRecyclerView;
    RecyclerView mOwnerRecyclerView;
    int mPosition = -1;
    int mOldPosition = -1;
    long mItemId = -1;
    int mItemViewType = -1;
    int mPreLayoutPosition = -1;
    V0 mShadowedHolder = null;
    V0 mShadowingHolder = null;
    List<Object> mPayloads = null;
    List<Object> mUnmodifiedPayloads = null;
    private int mIsRecyclableCount = 0;
    G0 mScrapContainer = null;
    boolean mInChangeScrap = false;
    private int mWasImportantForAccessibilityBeforeHidden = 0;
    int mPendingAccessibilityState = -1;
    private boolean mIsViewHolderRecoilEffectEnabled = true;

    public V0(View view) {
        if (view == null) {
            throw new IllegalArgumentException("itemView may not be null");
        }
        this.itemView = view;
    }

    public void addChangePayload(Object obj) {
        if (obj == null) {
            addFlags(1024);
            return;
        }
        if ((1024 & this.mFlags) == 0) {
            if (this.mPayloads == null) {
                ArrayList arrayList = new ArrayList();
                this.mPayloads = arrayList;
                this.mUnmodifiedPayloads = Collections.unmodifiableList(arrayList);
            }
            this.mPayloads.add(obj);
        }
    }

    public void addFlags(int i5) {
        this.mFlags = i5 | this.mFlags;
    }

    public void clearOldPosition() {
        this.mOldPosition = -1;
        this.mPreLayoutPosition = -1;
    }

    public void clearPayload() {
        List<Object> list = this.mPayloads;
        if (list != null) {
            list.clear();
        }
        this.mFlags &= -1025;
    }

    public void clearReturnedFromScrapFlag() {
        this.mFlags &= -33;
    }

    public void clearTmpDetachFlag() {
        this.mFlags &= -257;
    }

    public boolean doesTransientStatePreventRecycling() {
        if ((this.mFlags & 16) == 0) {
            View view = this.itemView;
            WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
            if (view.hasTransientState()) {
                return true;
            }
        }
        return false;
    }

    public void flagRemovedAndOffsetPosition(int i5, int i7, boolean z9) {
        addFlags(8);
        offsetPosition(i7, z9);
        this.mPosition = i5;
    }

    public final int getAbsoluteAdapterPosition() {
        RecyclerView recyclerView = this.mOwnerRecyclerView;
        if (recyclerView == null) {
            return -1;
        }
        return recyclerView.getAdapterPositionInRecyclerView(this);
    }

    @Deprecated
    public final int getAdapterPosition() {
        return getBindingAdapterPosition();
    }

    public final AbstractC0341j0 getBindingAdapter() {
        return this.mBindingAdapter;
    }

    public final int getBindingAdapterPosition() {
        RecyclerView recyclerView;
        AbstractC0341j0 adapter;
        int adapterPositionInRecyclerView;
        if (this.mBindingAdapter == null || (recyclerView = this.mOwnerRecyclerView) == null || (adapter = recyclerView.getAdapter()) == null || (adapterPositionInRecyclerView = this.mOwnerRecyclerView.getAdapterPositionInRecyclerView(this)) == -1) {
            return -1;
        }
        return adapter.findRelativeAdapterPositionIn(this.mBindingAdapter, this, adapterPositionInRecyclerView);
    }

    public int getFlags() {
        return this.mFlags;
    }

    public final long getItemId() {
        return this.mItemId;
    }

    public final int getItemViewType() {
        return this.mItemViewType;
    }

    public final int getLayoutPosition() {
        int i5 = this.mPreLayoutPosition;
        return i5 == -1 ? this.mPosition : i5;
    }

    public final int getOldPosition() {
        return this.mOldPosition;
    }

    @Deprecated
    public final int getPosition() {
        int i5 = this.mPreLayoutPosition;
        return i5 == -1 ? this.mPosition : i5;
    }

    public List<Object> getUnmodifiedPayloads() {
        if ((this.mFlags & 1024) != 0) {
            return FULLUPDATE_PAYLOADS;
        }
        List<Object> list = this.mPayloads;
        return (list == null || list.size() == 0) ? FULLUPDATE_PAYLOADS : this.mUnmodifiedPayloads;
    }

    public boolean hasAnyOfTheFlags(int i5) {
        return (this.mFlags & i5) != 0;
    }

    public boolean isAdapterPositionUnknown() {
        return (this.mFlags & 512) != 0 || isInvalid();
    }

    public boolean isAttachedToTransitionOverlay() {
        return (this.itemView.getParent() == null || this.itemView.getParent() == this.mOwnerRecyclerView) ? false : true;
    }

    public boolean isBound() {
        return (this.mFlags & 1) != 0;
    }

    public boolean isInvalid() {
        return (this.mFlags & 4) != 0;
    }

    public final boolean isRecyclable() {
        if ((this.mFlags & 16) == 0) {
            View view = this.itemView;
            WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
            if (!view.hasTransientState()) {
                return true;
            }
        }
        return false;
    }

    public boolean isRemoved() {
        return (this.mFlags & 8) != 0;
    }

    public boolean isScrap() {
        return this.mScrapContainer != null;
    }

    public boolean isTmpDetached() {
        return (this.mFlags & 256) != 0;
    }

    public boolean isUpdated() {
        return (this.mFlags & 2) != 0;
    }

    public boolean needsUpdate() {
        return (this.mFlags & 2) != 0;
    }

    public void offsetPosition(int i5, boolean z9) {
        if (this.mOldPosition == -1) {
            this.mOldPosition = this.mPosition;
        }
        if (this.mPreLayoutPosition == -1) {
            this.mPreLayoutPosition = this.mPosition;
        }
        if (z9) {
            this.mPreLayoutPosition += i5;
        }
        this.mPosition += i5;
        if (this.itemView.getLayoutParams() != null) {
            ((C0372z0) this.itemView.getLayoutParams()).mInsetsDirty = true;
        }
    }

    public void onEnteredHiddenState(RecyclerView recyclerView) {
        int i5 = this.mPendingAccessibilityState;
        if (i5 != -1) {
            this.mWasImportantForAccessibilityBeforeHidden = i5;
        } else {
            View view = this.itemView;
            WeakHashMap weakHashMap = androidx.core.view.W.f7199a;
            this.mWasImportantForAccessibilityBeforeHidden = view.getImportantForAccessibility();
        }
        recyclerView.setChildImportantForAccessibilityInternal(this, 4);
    }

    public void onLeftHiddenState(RecyclerView recyclerView) {
        recyclerView.setChildImportantForAccessibilityInternal(this, this.mWasImportantForAccessibilityBeforeHidden);
        this.mWasImportantForAccessibilityBeforeHidden = 0;
    }

    public void resetInternal() {
        if (RecyclerView.sDebugAssertionsEnabled && isTmpDetached()) {
            throw new IllegalStateException("Attempting to reset temp-detached ViewHolder: " + this + ". ViewHolders should be fully detached before resetting.");
        }
        this.mFlags = 0;
        this.mPosition = -1;
        this.mOldPosition = -1;
        this.mItemId = -1L;
        this.mPreLayoutPosition = -1;
        this.mIsRecyclableCount = 0;
        this.mShadowedHolder = null;
        this.mShadowingHolder = null;
        clearPayload();
        this.mWasImportantForAccessibilityBeforeHidden = 0;
        this.mPendingAccessibilityState = -1;
        RecyclerView.clearNestedRecyclerViewIfNotNested(this);
    }

    public void saveOldPosition() {
        if (this.mOldPosition == -1) {
            this.mOldPosition = this.mPosition;
        }
    }

    public boolean seslIsViewHolderRecoilEffectEnabled() {
        return this.mIsViewHolderRecoilEffectEnabled;
    }

    public void seslSetViewHolderRecoilEffectEnabled(boolean z9) {
        if (this.mIsViewHolderRecoilEffectEnabled != z9) {
            this.mIsViewHolderRecoilEffectEnabled = z9;
        }
    }

    public void setFlags(int i5, int i7) {
        this.mFlags = (i5 & i7) | (this.mFlags & (~i7));
    }

    public final void setIsRecyclable(boolean z9) {
        int i5 = this.mIsRecyclableCount;
        int i7 = z9 ? i5 - 1 : i5 + 1;
        this.mIsRecyclableCount = i7;
        if (i7 < 0) {
            this.mIsRecyclableCount = 0;
            if (RecyclerView.sDebugAssertionsEnabled) {
                throw new RuntimeException("isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
            }
            Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
        } else if (!z9 && i7 == 1) {
            this.mFlags |= 16;
        } else if (z9 && i7 == 0) {
            this.mFlags &= -17;
        }
        if (RecyclerView.sVerboseLoggingEnabled) {
            Log.d("SeslRecyclerView", "setIsRecyclable val:" + z9 + ":" + this);
        }
    }

    public void setScrapContainer(G0 g02, boolean z9) {
        this.mScrapContainer = g02;
        this.mInChangeScrap = z9;
    }

    public boolean shouldBeKeptAsChild() {
        return (this.mFlags & 16) != 0;
    }

    public boolean shouldIgnore() {
        return (this.mFlags & 128) != 0;
    }

    public void stopIgnoring() {
        this.mFlags &= -129;
    }

    public String toString() {
        StringBuilder sbP = AbstractC0476d.p(getClass().isAnonymousClass() ? "ViewHolder" : getClass().getSimpleName(), "{");
        sbP.append(Integer.toHexString(hashCode()));
        sbP.append(" position=");
        sbP.append(this.mPosition);
        sbP.append(" id=");
        sbP.append(this.mItemId);
        sbP.append(", oldPos=");
        sbP.append(this.mOldPosition);
        sbP.append(", pLpos:");
        sbP.append(this.mPreLayoutPosition);
        StringBuilder sb = new StringBuilder(sbP.toString());
        if (isScrap()) {
            sb.append(" scrap ");
            sb.append(this.mInChangeScrap ? "[changeScrap]" : "[attachedScrap]");
        }
        if (isInvalid()) {
            sb.append(" invalid");
        }
        if (!isBound()) {
            sb.append(" unbound");
        }
        if (needsUpdate()) {
            sb.append(" update");
        }
        if (isRemoved()) {
            sb.append(" removed");
        }
        if (shouldIgnore()) {
            sb.append(" ignored");
        }
        if (isTmpDetached()) {
            sb.append(" tmpDetached");
        }
        if (!isRecyclable()) {
            sb.append(" not recyclable(" + this.mIsRecyclableCount + ")");
        }
        if (isAdapterPositionUnknown()) {
            sb.append(" undefined adapter position");
        }
        if (this.itemView.getParent() == null) {
            sb.append(" no parent");
        }
        sb.append("}");
        return sb.toString();
    }

    public void unScrap() {
        this.mScrapContainer.n(this);
    }

    public boolean wasReturnedFromScrap() {
        return (this.mFlags & 32) != 0;
    }
}
