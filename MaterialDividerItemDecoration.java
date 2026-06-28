package com.google.android.material.divider;

import B.b;
import E.a;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.recyclerview.widget.AbstractC0341j0;
import androidx.recyclerview.widget.AbstractC0362u0;
import androidx.recyclerview.widget.R0;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;

/* JADX INFO: loaded from: classes.dex */
public class MaterialDividerItemDecoration extends AbstractC0362u0 {
    private static final int DEF_STYLE_RES = R.style.Widget_MaterialComponents_MaterialDivider;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    private int color;
    private Drawable dividerDrawable;
    private int insetEnd;
    private int insetStart;
    private boolean lastItemDecorated;
    private int orientation;
    private final Rect tempRect;
    private int thickness;

    public MaterialDividerItemDecoration(Context context, AttributeSet attributeSet, int i5, int i7) {
        this.tempRect = new Rect();
        TypedArray typedArrayObtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, R.styleable.MaterialDivider, i5, DEF_STYLE_RES, new int[0]);
        this.color = MaterialResources.getColorStateList(context, typedArrayObtainStyledAttributes, R.styleable.MaterialDivider_dividerColor).getDefaultColor();
        this.thickness = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.MaterialDivider_dividerThickness, context.getResources().getDimensionPixelSize(R.dimen.material_divider_thickness));
        this.insetStart = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.MaterialDivider_dividerInsetStart, 0);
        this.insetEnd = typedArrayObtainStyledAttributes.getDimensionPixelOffset(R.styleable.MaterialDivider_dividerInsetEnd, 0);
        this.lastItemDecorated = typedArrayObtainStyledAttributes.getBoolean(R.styleable.MaterialDivider_lastItemDecorated, true);
        typedArrayObtainStyledAttributes.recycle();
        this.dividerDrawable = new ShapeDrawable();
        setDividerColor(this.color);
        setOrientation(i7);
    }

    private void drawForHorizontalOrientation(Canvas canvas, RecyclerView recyclerView) {
        int height;
        int paddingTop;
        int i5;
        int i7;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            paddingTop = recyclerView.getPaddingTop();
            height = recyclerView.getHeight() - recyclerView.getPaddingBottom();
            canvas.clipRect(recyclerView.getPaddingLeft(), paddingTop, recyclerView.getWidth() - recyclerView.getPaddingRight(), height);
        } else {
            height = recyclerView.getHeight();
            paddingTop = 0;
        }
        int i9 = paddingTop + this.insetStart;
        int i10 = height - this.insetEnd;
        boolean zIsLayoutRtl = ViewUtils.isLayoutRtl(recyclerView);
        int childCount = recyclerView.getChildCount();
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = recyclerView.getChildAt(i11);
            if (shouldDrawDivider(recyclerView, childAt)) {
                recyclerView.getLayoutManager().getDecoratedBoundsWithMargins(childAt, this.tempRect);
                int iRound = Math.round(childAt.getTranslationX());
                if (zIsLayoutRtl) {
                    i7 = this.tempRect.left + iRound;
                    i5 = this.thickness + i7;
                } else {
                    i5 = iRound + this.tempRect.right;
                    i7 = i5 - this.thickness;
                }
                this.dividerDrawable.setBounds(i7, i9, i5, i10);
                this.dividerDrawable.draw(canvas);
            }
        }
        canvas.restore();
    }

    private void drawForVerticalOrientation(Canvas canvas, RecyclerView recyclerView) {
        int width;
        int paddingLeft;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            paddingLeft = recyclerView.getPaddingLeft();
            width = recyclerView.getWidth() - recyclerView.getPaddingRight();
            canvas.clipRect(paddingLeft, recyclerView.getPaddingTop(), width, recyclerView.getHeight() - recyclerView.getPaddingBottom());
        } else {
            width = recyclerView.getWidth();
            paddingLeft = 0;
        }
        boolean zIsLayoutRtl = ViewUtils.isLayoutRtl(recyclerView);
        int i5 = paddingLeft + (zIsLayoutRtl ? this.insetEnd : this.insetStart);
        int i7 = width - (zIsLayoutRtl ? this.insetStart : this.insetEnd);
        int childCount = recyclerView.getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = recyclerView.getChildAt(i9);
            if (shouldDrawDivider(recyclerView, childAt)) {
                recyclerView.getLayoutManager().getDecoratedBoundsWithMargins(childAt, this.tempRect);
                int iRound = Math.round(childAt.getTranslationY()) + this.tempRect.bottom;
                this.dividerDrawable.setBounds(i5, iRound - this.thickness, i7, iRound);
                this.dividerDrawable.draw(canvas);
            }
        }
        canvas.restore();
    }

    public int getDividerColor() {
        return this.color;
    }

    public int getDividerInsetEnd() {
        return this.insetEnd;
    }

    public int getDividerInsetStart() {
        return this.insetStart;
    }

    public int getDividerThickness() {
        return this.thickness;
    }

    @Override // androidx.recyclerview.widget.AbstractC0362u0
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, R0 r02) {
        rect.set(0, 0, 0, 0);
        if (shouldDrawDivider(recyclerView, view)) {
            if (this.orientation == 1) {
                rect.bottom = this.thickness;
            } else if (ViewUtils.isLayoutRtl(recyclerView)) {
                rect.left = this.thickness;
            } else {
                rect.right = this.thickness;
            }
        }
    }

    public int getOrientation() {
        return this.orientation;
    }

    public boolean isLastItemDecorated() {
        return this.lastItemDecorated;
    }

    @Override // androidx.recyclerview.widget.AbstractC0362u0
    public void onDraw(Canvas canvas, RecyclerView recyclerView, R0 r02) {
        if (recyclerView.getLayoutManager() == null) {
            return;
        }
        if (this.orientation == 1) {
            drawForVerticalOrientation(canvas, recyclerView);
        } else {
            drawForHorizontalOrientation(canvas, recyclerView);
        }
    }

    public void setDividerColor(int i5) {
        this.color = i5;
        Drawable drawable = this.dividerDrawable;
        this.dividerDrawable = drawable;
        a.g(drawable, i5);
    }

    public void setDividerColorResource(Context context, int i5) {
        setDividerColor(b.a(context, i5));
    }

    public void setDividerInsetEnd(int i5) {
        this.insetEnd = i5;
    }

    public void setDividerInsetEndResource(Context context, int i5) {
        setDividerInsetEnd(context.getResources().getDimensionPixelOffset(i5));
    }

    public void setDividerInsetStart(int i5) {
        this.insetStart = i5;
    }

    public void setDividerInsetStartResource(Context context, int i5) {
        setDividerInsetStart(context.getResources().getDimensionPixelOffset(i5));
    }

    public void setDividerThickness(int i5) {
        this.thickness = i5;
    }

    public void setDividerThicknessResource(Context context, int i5) {
        setDividerThickness(context.getResources().getDimensionPixelSize(i5));
    }

    public void setLastItemDecorated(boolean z9) {
        this.lastItemDecorated = z9;
    }

    public void setOrientation(int i5) {
        if (i5 == 0 || i5 == 1) {
            this.orientation = i5;
            return;
        }
        throw new IllegalArgumentException("Invalid orientation: " + i5 + ". It should be either HORIZONTAL or VERTICAL");
    }

    public boolean shouldDrawDivider(int i5, AbstractC0341j0 abstractC0341j0) {
        return true;
    }

    private boolean shouldDrawDivider(RecyclerView recyclerView, View view) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        AbstractC0341j0 adapter = recyclerView.getAdapter();
        boolean z9 = adapter != null && childAdapterPosition == adapter.getItemCount() - 1;
        if (childAdapterPosition != -1) {
            return (!z9 || this.lastItemDecorated) && shouldDrawDivider(childAdapterPosition, adapter);
        }
        return false;
    }

    public MaterialDividerItemDecoration(Context context, int i5) {
        this(context, null, i5);
    }

    public MaterialDividerItemDecoration(Context context, AttributeSet attributeSet, int i5) {
        this(context, attributeSet, R.attr.materialDividerStyle, i5);
    }
}
