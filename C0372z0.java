package androidx.recyclerview.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* JADX INFO: renamed from: androidx.recyclerview.widget.z0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C0372z0 extends ViewGroup.MarginLayoutParams {
    final Rect mDecorInsets;
    boolean mInsetsDirty;
    boolean mPendingInvalidate;
    V0 mViewHolder;

    public C0372z0(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDecorInsets = new Rect();
        this.mInsetsDirty = true;
        this.mPendingInvalidate = false;
    }

    public int getAbsoluteAdapterPosition() {
        return this.mViewHolder.getAbsoluteAdapterPosition();
    }

    public int getBindingAdapterPosition() {
        return this.mViewHolder.getBindingAdapterPosition();
    }

    @Deprecated
    public int getViewAdapterPosition() {
        return this.mViewHolder.getBindingAdapterPosition();
    }

    public int getViewLayoutPosition() {
        return this.mViewHolder.getLayoutPosition();
    }

    @Deprecated
    public int getViewPosition() {
        return this.mViewHolder.getPosition();
    }

    public boolean isItemChanged() {
        return this.mViewHolder.isUpdated();
    }

    public boolean isItemRemoved() {
        return this.mViewHolder.isRemoved();
    }

    public boolean isViewInvalid() {
        return this.mViewHolder.isInvalid();
    }

    public boolean viewNeedsUpdate() {
        return this.mViewHolder.needsUpdate();
    }

    public C0372z0(int i5, int i7) {
        super(i5, i7);
        this.mDecorInsets = new Rect();
        this.mInsetsDirty = true;
        this.mPendingInvalidate = false;
    }

    public C0372z0(ViewGroup.MarginLayoutParams marginLayoutParams) {
        super(marginLayoutParams);
        this.mDecorInsets = new Rect();
        this.mInsetsDirty = true;
        this.mPendingInvalidate = false;
    }

    public C0372z0(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.mDecorInsets = new Rect();
        this.mInsetsDirty = true;
        this.mPendingInvalidate = false;
    }

    public C0372z0(C0372z0 c0372z0) {
        super((ViewGroup.LayoutParams) c0372z0);
        this.mDecorInsets = new Rect();
        this.mInsetsDirty = true;
        this.mPendingInvalidate = false;
    }
}
