package com.google.android.material.carousel;

import A8.l;
import C6.a;
import D.d;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.appcompat.widget.AbstractC0152g1;
import androidx.recyclerview.widget.AbstractC0362u0;
import androidx.recyclerview.widget.AbstractC0370y0;
import androidx.recyclerview.widget.C0372z0;
import androidx.recyclerview.widget.G0;
import androidx.recyclerview.widget.P0;
import androidx.recyclerview.widget.R0;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.U;
import com.bumptech.glide.c;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.carousel.KeylineState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class CarouselLayoutManager extends AbstractC0370y0 implements Carousel, P0 {
    public static final int ALIGNMENT_CENTER = 1;
    public static final int ALIGNMENT_START = 0;
    public static final int HORIZONTAL = 0;
    private static final String TAG = "CarouselLayoutManager";
    public static final int VERTICAL = 1;
    private int carouselAlignment;
    private CarouselStrategy carouselStrategy;
    private int currentEstimatedPosition;
    private int currentFillStartPosition;
    private KeylineState currentKeylineState;
    private final DebugItemDecoration debugItemDecoration;
    private boolean isDebuggingEnabled;
    private KeylineStateList keylineStateList;
    private Map<Integer, KeylineState> keylineStatePositionMap;
    private int lastItemCount;
    int maxScroll;
    int minScroll;
    private CarouselOrientationHelper orientationHelper;
    private final View.OnLayoutChangeListener recyclerViewSizeChangeListener;
    int scrollOffset;

    public static final class ChildCalculations {
        final float center;
        final View child;
        final float offsetCenter;
        final KeylineRange range;

        public ChildCalculations(View view, float f2, float f7, KeylineRange keylineRange) {
            this.child = view;
            this.center = f2;
            this.offsetCenter = f7;
            this.range = keylineRange;
        }
    }

    public static class DebugItemDecoration extends AbstractC0362u0 {
        private List<KeylineState.Keyline> keylines;
        private final Paint linePaint;

        public DebugItemDecoration() {
            Paint paint = new Paint();
            this.linePaint = paint;
            this.keylines = Collections.unmodifiableList(new ArrayList());
            paint.setStrokeWidth(5.0f);
            paint.setColor(-65281);
        }

        @Override // androidx.recyclerview.widget.AbstractC0362u0
        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, R0 r02) {
            super.onDrawOver(canvas, recyclerView, r02);
            this.linePaint.setStrokeWidth(recyclerView.getResources().getDimension(R.dimen.m3_carousel_debug_keyline_width));
            for (KeylineState.Keyline keyline : this.keylines) {
                this.linePaint.setColor(d.b(-65281, -16776961, keyline.mask));
                if (((CarouselLayoutManager) recyclerView.getLayoutManager()).isHorizontal()) {
                    canvas.drawLine(keyline.locOffset, ((CarouselLayoutManager) recyclerView.getLayoutManager()).getParentTop(), keyline.locOffset, ((CarouselLayoutManager) recyclerView.getLayoutManager()).getParentBottom(), this.linePaint);
                } else {
                    canvas.drawLine(((CarouselLayoutManager) recyclerView.getLayoutManager()).getParentLeft(), keyline.locOffset, ((CarouselLayoutManager) recyclerView.getLayoutManager()).getParentRight(), keyline.locOffset, this.linePaint);
                }
            }
        }

        public void setKeylines(List<KeylineState.Keyline> list) {
            this.keylines = Collections.unmodifiableList(list);
        }
    }

    public static class KeylineRange {
        final KeylineState.Keyline leftOrTop;
        final KeylineState.Keyline rightOrBottom;

        public KeylineRange(KeylineState.Keyline keyline, KeylineState.Keyline keyline2) {
            if (keyline.loc > keyline2.loc) {
                throw new IllegalArgumentException();
            }
            this.leftOrTop = keyline;
            this.rightOrBottom = keyline2;
        }
    }

    public static class LayoutDirection {
        private static final int INVALID_LAYOUT = Integer.MIN_VALUE;
        private static final int LAYOUT_END = 1;
        private static final int LAYOUT_START = -1;

        private LayoutDirection() {
        }
    }

    public CarouselLayoutManager() {
        this(new MultiBrowseCarouselStrategy());
    }

    private void addAndLayoutView(View view, int i5, ChildCalculations childCalculations) {
        float itemSize = this.currentKeylineState.getItemSize() / 2.0f;
        addView(view, i5);
        float f2 = childCalculations.offsetCenter;
        this.orientationHelper.layoutDecoratedWithMargins(view, (int) (f2 - itemSize), (int) (f2 + itemSize));
        updateChildMaskForLocation(view, childCalculations.center, childCalculations.range);
    }

    private float addEnd(float f2, float f7) {
        return isLayoutRtl() ? f2 - f7 : f2 + f7;
    }

    private float addStart(float f2, float f7) {
        return isLayoutRtl() ? f2 + f7 : f2 - f7;
    }

    private void addViewAtPosition(G0 g02, int i5, int i7) {
        if (i5 < 0 || i5 >= getItemCount()) {
            return;
        }
        ChildCalculations childCalculationsMakeChildCalculations = makeChildCalculations(g02, calculateChildStartForFill(i5), i5);
        addAndLayoutView(childCalculationsMakeChildCalculations.child, i7, childCalculationsMakeChildCalculations);
    }

    private void addViewsEnd(G0 g02, R0 r02, int i5) {
        float fCalculateChildStartForFill = calculateChildStartForFill(i5);
        while (i5 < r02.b()) {
            ChildCalculations childCalculationsMakeChildCalculations = makeChildCalculations(g02, fCalculateChildStartForFill, i5);
            if (isLocOffsetOutOfFillBoundsEnd(childCalculationsMakeChildCalculations.offsetCenter, childCalculationsMakeChildCalculations.range)) {
                return;
            }
            fCalculateChildStartForFill = addEnd(fCalculateChildStartForFill, this.currentKeylineState.getItemSize());
            if (!isLocOffsetOutOfFillBoundsStart(childCalculationsMakeChildCalculations.offsetCenter, childCalculationsMakeChildCalculations.range)) {
                addAndLayoutView(childCalculationsMakeChildCalculations.child, -1, childCalculationsMakeChildCalculations);
            }
            i5++;
        }
    }

    private void addViewsStart(G0 g02, int i5) {
        float fCalculateChildStartForFill = calculateChildStartForFill(i5);
        while (i5 >= 0) {
            ChildCalculations childCalculationsMakeChildCalculations = makeChildCalculations(g02, fCalculateChildStartForFill, i5);
            if (isLocOffsetOutOfFillBoundsStart(childCalculationsMakeChildCalculations.offsetCenter, childCalculationsMakeChildCalculations.range)) {
                return;
            }
            fCalculateChildStartForFill = addStart(fCalculateChildStartForFill, this.currentKeylineState.getItemSize());
            if (!isLocOffsetOutOfFillBoundsEnd(childCalculationsMakeChildCalculations.offsetCenter, childCalculationsMakeChildCalculations.range)) {
                addAndLayoutView(childCalculationsMakeChildCalculations.child, 0, childCalculationsMakeChildCalculations);
            }
            i5--;
        }
    }

    private float calculateChildOffsetCenterForLocation(View view, float f2, KeylineRange keylineRange) {
        KeylineState.Keyline keyline = keylineRange.leftOrTop;
        float f7 = keyline.locOffset;
        KeylineState.Keyline keyline2 = keylineRange.rightOrBottom;
        float fLerp = AnimationUtils.lerp(f7, keyline2.locOffset, keyline.loc, keyline2.loc, f2);
        if (keylineRange.rightOrBottom != this.currentKeylineState.getFirstKeyline() && keylineRange.leftOrTop != this.currentKeylineState.getLastKeyline()) {
            return fLerp;
        }
        float maskMargins = this.orientationHelper.getMaskMargins((C0372z0) view.getLayoutParams()) / this.currentKeylineState.getItemSize();
        KeylineState.Keyline keyline3 = keylineRange.rightOrBottom;
        return fLerp + (((1.0f - keyline3.mask) + maskMargins) * (f2 - keyline3.loc));
    }

    private float calculateChildStartForFill(int i5) {
        return addEnd(getParentStart() - this.scrollOffset, this.currentKeylineState.getItemSize() * i5);
    }

    private int calculateEndScroll(R0 r02, KeylineStateList keylineStateList) {
        boolean zIsLayoutRtl = isLayoutRtl();
        KeylineState startState = zIsLayoutRtl ? keylineStateList.getStartState() : keylineStateList.getEndState();
        KeylineState.Keyline firstFocalKeyline = zIsLayoutRtl ? startState.getFirstFocalKeyline() : startState.getLastFocalKeyline();
        int iB = (int) ((((((r02.b() - 1) * startState.getItemSize()) + getPaddingEnd()) * (zIsLayoutRtl ? -1.0f : 1.0f)) - (firstFocalKeyline.loc - getParentStart())) + (getParentEnd() - firstFocalKeyline.loc));
        return zIsLayoutRtl ? Math.min(0, iB) : Math.max(0, iB);
    }

    private static int calculateShouldScrollBy(int i5, int i7, int i9, int i10) {
        int i11 = i7 + i5;
        return i11 < i9 ? i9 - i7 : i11 > i10 ? i10 - i7 : i5;
    }

    private int calculateStartScroll(KeylineStateList keylineStateList) {
        boolean zIsLayoutRtl = isLayoutRtl();
        KeylineState endState = zIsLayoutRtl ? keylineStateList.getEndState() : keylineStateList.getStartState();
        return (int) (((getPaddingStart() * (zIsLayoutRtl ? 1 : -1)) + getParentStart()) - addStart((zIsLayoutRtl ? endState.getLastFocalKeyline() : endState.getFirstFocalKeyline()).loc, endState.getItemSize() / 2.0f));
    }

    private int convertFocusDirectionToLayoutDirection(int i5) {
        int orientation = getOrientation();
        if (i5 == 1) {
            return -1;
        }
        if (i5 == 2) {
            return 1;
        }
        if (i5 == 17) {
            if (orientation == 0) {
                return isLayoutRtl() ? 1 : -1;
            }
            return Integer.MIN_VALUE;
        }
        if (i5 == 33) {
            return orientation == 1 ? -1 : Integer.MIN_VALUE;
        }
        if (i5 == 66) {
            if (orientation == 0) {
                return isLayoutRtl() ? -1 : 1;
            }
            return Integer.MIN_VALUE;
        }
        if (i5 == 130) {
            return orientation == 1 ? 1 : Integer.MIN_VALUE;
        }
        AbstractC0152g1.f(i5, "Unknown focus request:", TAG);
        return Integer.MIN_VALUE;
    }

    private void fill(G0 g02, R0 r02) {
        removeAndRecycleOutOfBoundsViews(g02);
        if (getChildCount() == 0) {
            addViewsStart(g02, this.currentFillStartPosition - 1);
            addViewsEnd(g02, r02, this.currentFillStartPosition);
        } else {
            int position = getPosition(getChildAt(0));
            int position2 = getPosition(getChildAt(getChildCount() - 1));
            addViewsStart(g02, position - 1);
            addViewsEnd(g02, r02, position2 + 1);
        }
        validateChildOrderIfDebugging();
    }

    private View getChildClosestToEnd() {
        return getChildAt(isLayoutRtl() ? 0 : getChildCount() - 1);
    }

    private View getChildClosestToStart() {
        return getChildAt(isLayoutRtl() ? getChildCount() - 1 : 0);
    }

    private int getContainerSize() {
        return isHorizontal() ? getContainerWidth() : getContainerHeight();
    }

    private float getDecoratedCenterWithMargins(View view) {
        super.getDecoratedBoundsWithMargins(view, new Rect());
        return isHorizontal() ? r0.centerX() : r0.centerY();
    }

    private KeylineState getKeylineStateForPosition(int i5) {
        KeylineState keylineState;
        Map<Integer, KeylineState> map = this.keylineStatePositionMap;
        return (map == null || (keylineState = map.get(Integer.valueOf(c.d(i5, 0, Math.max(0, getItemCount() + (-1)))))) == null) ? this.keylineStateList.getDefaultState() : keylineState;
    }

    private float getMaskedItemSizeForLocOffset(float f2, KeylineRange keylineRange) {
        KeylineState.Keyline keyline = keylineRange.leftOrTop;
        float f7 = keyline.maskedItemSize;
        KeylineState.Keyline keyline2 = keylineRange.rightOrBottom;
        return AnimationUtils.lerp(f7, keyline2.maskedItemSize, keyline.locOffset, keyline2.locOffset, f2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getParentBottom() {
        return this.orientationHelper.getParentBottom();
    }

    private int getParentEnd() {
        return this.orientationHelper.getParentEnd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getParentLeft() {
        return this.orientationHelper.getParentLeft();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getParentRight() {
        return this.orientationHelper.getParentRight();
    }

    private int getParentStart() {
        return this.orientationHelper.getParentStart();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getParentTop() {
        return this.orientationHelper.getParentTop();
    }

    private int getScrollOffsetForPosition(int i5, KeylineState keylineState) {
        if (isLayoutRtl()) {
            return (int) (((getContainerSize() - keylineState.getLastFocalKeyline().loc) - (i5 * keylineState.getItemSize())) - (keylineState.getItemSize() / 2.0f));
        }
        return (int) ((keylineState.getItemSize() / 2.0f) + ((i5 * keylineState.getItemSize()) - keylineState.getFirstFocalKeyline().loc));
    }

    private int getSmallestScrollOffsetToFocalKeyline(int i5, KeylineState keylineState) {
        int i7 = Integer.MAX_VALUE;
        for (KeylineState.Keyline keyline : keylineState.getFocalKeylines()) {
            float itemSize = (keylineState.getItemSize() / 2.0f) + (i5 * keylineState.getItemSize());
            int containerSize = (isLayoutRtl() ? (int) ((getContainerSize() - keyline.loc) - itemSize) : (int) (itemSize - keyline.loc)) - this.scrollOffset;
            if (Math.abs(i7) > Math.abs(containerSize)) {
                i7 = containerSize;
            }
        }
        return i7;
    }

    private static KeylineRange getSurroundingKeylineRange(List<KeylineState.Keyline> list, float f2, boolean z9) {
        float f7 = Float.MAX_VALUE;
        int i5 = -1;
        int i7 = -1;
        int i9 = -1;
        int i10 = -1;
        float f9 = -3.4028235E38f;
        float f10 = Float.MAX_VALUE;
        float f11 = Float.MAX_VALUE;
        for (int i11 = 0; i11 < list.size(); i11++) {
            KeylineState.Keyline keyline = list.get(i11);
            float f12 = z9 ? keyline.locOffset : keyline.loc;
            float fAbs = Math.abs(f12 - f2);
            if (f12 <= f2 && fAbs <= f7) {
                i5 = i11;
                f7 = fAbs;
            }
            if (f12 > f2 && fAbs <= f10) {
                i9 = i11;
                f10 = fAbs;
            }
            if (f12 <= f11) {
                i7 = i11;
                f11 = f12;
            }
            if (f12 > f9) {
                i10 = i11;
                f9 = f12;
            }
        }
        if (i5 == -1) {
            i5 = i7;
        }
        if (i9 == -1) {
            i9 = i10;
        }
        return new KeylineRange(list.get(i5), list.get(i9));
    }

    private boolean isLocOffsetOutOfFillBoundsEnd(float f2, KeylineRange keylineRange) {
        float fAddStart = addStart(f2, getMaskedItemSizeForLocOffset(f2, keylineRange) / 2.0f);
        if (isLayoutRtl()) {
            if (fAddStart >= 0.0f) {
                return false;
            }
        } else if (fAddStart <= getContainerSize()) {
            return false;
        }
        return true;
    }

    private boolean isLocOffsetOutOfFillBoundsStart(float f2, KeylineRange keylineRange) {
        float fAddEnd = addEnd(f2, getMaskedItemSizeForLocOffset(f2, keylineRange) / 2.0f);
        if (isLayoutRtl()) {
            if (fAddEnd <= getContainerSize()) {
                return false;
            }
        } else if (fAddEnd >= 0.0f) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view, int i5, int i7, int i9, int i10, int i11, int i12, int i13, int i14) {
        if (i5 == i11 && i7 == i12 && i9 == i13 && i10 == i14) {
            return;
        }
        view.post(new a(9, this));
    }

    private void logChildrenIfDebugging() {
        if (this.isDebuggingEnabled && Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "internal representation of views on the screen");
            for (int i5 = 0; i5 < getChildCount(); i5++) {
                View childAt = getChildAt(i5);
                Log.d(TAG, "item position " + getPosition(childAt) + ", center:" + getDecoratedCenterWithMargins(childAt) + ", child index:" + i5);
            }
            Log.d(TAG, "==============");
        }
    }

    private ChildCalculations makeChildCalculations(G0 g02, float f2, int i5) {
        View view = g02.m(Long.MAX_VALUE, i5).itemView;
        measureChildWithMargins(view, 0, 0);
        float fAddEnd = addEnd(f2, this.currentKeylineState.getItemSize() / 2.0f);
        KeylineRange surroundingKeylineRange = getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), fAddEnd, false);
        return new ChildCalculations(view, fAddEnd, calculateChildOffsetCenterForLocation(view, fAddEnd, surroundingKeylineRange), surroundingKeylineRange);
    }

    private float offsetChild(View view, float f2, float f7, Rect rect) {
        float fAddEnd = addEnd(f2, f7);
        KeylineRange surroundingKeylineRange = getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), fAddEnd, false);
        float fCalculateChildOffsetCenterForLocation = calculateChildOffsetCenterForLocation(view, fAddEnd, surroundingKeylineRange);
        super.getDecoratedBoundsWithMargins(view, rect);
        updateChildMaskForLocation(view, fAddEnd, surroundingKeylineRange);
        this.orientationHelper.offsetChild(view, rect, f7, fCalculateChildOffsetCenterForLocation);
        return fCalculateChildOffsetCenterForLocation;
    }

    private void recalculateKeylineStateList(G0 g02) {
        View view = g02.m(Long.MAX_VALUE, 0).itemView;
        measureChildWithMargins(view, 0, 0);
        KeylineState keylineStateOnFirstChildMeasuredWithMargins = this.carouselStrategy.onFirstChildMeasuredWithMargins(this, view);
        if (isLayoutRtl()) {
            keylineStateOnFirstChildMeasuredWithMargins = KeylineState.reverse(keylineStateOnFirstChildMeasuredWithMargins, getContainerSize());
        }
        this.keylineStateList = KeylineStateList.from(this, keylineStateOnFirstChildMeasuredWithMargins);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshKeylineState() {
        this.keylineStateList = null;
        requestLayout();
    }

    private void removeAndRecycleOutOfBoundsViews(G0 g02) {
        while (getChildCount() > 0) {
            View childAt = getChildAt(0);
            float decoratedCenterWithMargins = getDecoratedCenterWithMargins(childAt);
            if (!isLocOffsetOutOfFillBoundsStart(decoratedCenterWithMargins, getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), decoratedCenterWithMargins, true))) {
                break;
            } else {
                removeAndRecycleView(childAt, g02);
            }
        }
        while (getChildCount() - 1 >= 0) {
            View childAt2 = getChildAt(getChildCount() - 1);
            float decoratedCenterWithMargins2 = getDecoratedCenterWithMargins(childAt2);
            if (!isLocOffsetOutOfFillBoundsEnd(decoratedCenterWithMargins2, getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), decoratedCenterWithMargins2, true))) {
                return;
            } else {
                removeAndRecycleView(childAt2, g02);
            }
        }
    }

    private void scrollBy(RecyclerView recyclerView, int i5) {
        if (isHorizontal()) {
            recyclerView.scrollBy(i5, 0);
        } else {
            recyclerView.scrollBy(0, i5);
        }
    }

    private void setCarouselAttributes(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Carousel);
            setCarouselAlignment(typedArrayObtainStyledAttributes.getInt(R.styleable.Carousel_carousel_alignment, 0));
            setOrientation(typedArrayObtainStyledAttributes.getInt(0, 0));
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void updateChildMaskForLocation(View view, float f2, KeylineRange keylineRange) {
        if (view instanceof Maskable) {
            KeylineState.Keyline keyline = keylineRange.leftOrTop;
            float f7 = keyline.mask;
            KeylineState.Keyline keyline2 = keylineRange.rightOrBottom;
            float fLerp = AnimationUtils.lerp(f7, keyline2.mask, keyline.loc, keyline2.loc, f2);
            float height = view.getHeight();
            float width = view.getWidth();
            RectF maskRect = this.orientationHelper.getMaskRect(height, width, AnimationUtils.lerp(0.0f, height / 2.0f, 0.0f, 1.0f, fLerp), AnimationUtils.lerp(0.0f, width / 2.0f, 0.0f, 1.0f, fLerp));
            float fCalculateChildOffsetCenterForLocation = calculateChildOffsetCenterForLocation(view, f2, keylineRange);
            RectF rectF = new RectF(fCalculateChildOffsetCenterForLocation - (maskRect.width() / 2.0f), fCalculateChildOffsetCenterForLocation - (maskRect.height() / 2.0f), (maskRect.width() / 2.0f) + fCalculateChildOffsetCenterForLocation, (maskRect.height() / 2.0f) + fCalculateChildOffsetCenterForLocation);
            RectF rectF2 = new RectF(getParentLeft(), getParentTop(), getParentRight(), getParentBottom());
            if (this.carouselStrategy.isContained()) {
                this.orientationHelper.containMaskWithinBounds(maskRect, rectF, rectF2);
            }
            this.orientationHelper.moveMaskOnEdgeOutsideBounds(maskRect, rectF, rectF2);
            ((Maskable) view).setMaskRectF(maskRect);
        }
    }

    private void updateCurrentKeylineStateForScrollOffset(KeylineStateList keylineStateList) {
        int i5 = this.maxScroll;
        int i7 = this.minScroll;
        if (i5 <= i7) {
            this.currentKeylineState = isLayoutRtl() ? keylineStateList.getEndState() : keylineStateList.getStartState();
        } else {
            this.currentKeylineState = keylineStateList.getShiftedState(this.scrollOffset, i7, i5);
        }
        this.debugItemDecoration.setKeylines(this.currentKeylineState.getKeylines());
    }

    private void updateItemCount() {
        int itemCount = getItemCount();
        int i5 = this.lastItemCount;
        if (itemCount == i5 || this.keylineStateList == null) {
            return;
        }
        if (this.carouselStrategy.shouldRefreshKeylineState(this, i5)) {
            refreshKeylineState();
        }
        this.lastItemCount = itemCount;
    }

    private void validateChildOrderIfDebugging() {
        if (!this.isDebuggingEnabled || getChildCount() < 1) {
            return;
        }
        int i5 = 0;
        while (i5 < getChildCount() - 1) {
            int position = getPosition(getChildAt(i5));
            int i7 = i5 + 1;
            int position2 = getPosition(getChildAt(i7));
            if (position > position2) {
                logChildrenIfDebugging();
                StringBuilder sbX = l.x("Detected invalid child order. Child at index [", i5, "] had adapter position [", position, "] and child at index [");
                sbX.append(i7);
                sbX.append("] had adapter position [");
                sbX.append(position2);
                sbX.append("].");
                throw new IllegalStateException(sbX.toString());
            }
            i5 = i7;
        }
    }

    public int calculateScrollDeltaToMakePositionVisible(int i5) {
        return (int) (this.scrollOffset - getScrollOffsetForPosition(i5, getKeylineStateForPosition(i5)));
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public boolean canScrollHorizontally() {
        return isHorizontal();
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public boolean canScrollVertically() {
        return !isHorizontal();
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public int computeHorizontalScrollExtent(R0 r02) {
        if (getChildCount() == 0 || this.keylineStateList == null || getItemCount() <= 1) {
            return 0;
        }
        return (int) (getWidth() * (this.keylineStateList.getDefaultState().getItemSize() / computeHorizontalScrollRange(r02)));
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public int computeHorizontalScrollOffset(R0 r02) {
        return this.scrollOffset;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public int computeHorizontalScrollRange(R0 r02) {
        return this.maxScroll - this.minScroll;
    }

    @Override // androidx.recyclerview.widget.P0
    public PointF computeScrollVectorForPosition(int i5) {
        if (this.keylineStateList == null) {
            return null;
        }
        int offsetToScrollToPosition = getOffsetToScrollToPosition(i5, getKeylineStateForPosition(i5));
        return isHorizontal() ? new PointF(offsetToScrollToPosition, 0.0f) : new PointF(0.0f, offsetToScrollToPosition);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public int computeVerticalScrollExtent(R0 r02) {
        if (getChildCount() == 0 || this.keylineStateList == null || getItemCount() <= 1) {
            return 0;
        }
        return (int) (getHeight() * (this.keylineStateList.getDefaultState().getItemSize() / computeVerticalScrollRange(r02)));
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public int computeVerticalScrollOffset(R0 r02) {
        return this.scrollOffset;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public int computeVerticalScrollRange(R0 r02) {
        return this.maxScroll - this.minScroll;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public C0372z0 generateDefaultLayoutParams() {
        return new C0372z0(-2, -2);
    }

    @Override // com.google.android.material.carousel.Carousel
    public int getCarouselAlignment() {
        return this.carouselAlignment;
    }

    @Override // com.google.android.material.carousel.Carousel
    public int getContainerHeight() {
        return getHeight();
    }

    @Override // com.google.android.material.carousel.Carousel
    public int getContainerWidth() {
        return getWidth();
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void getDecoratedBoundsWithMargins(View view, Rect rect) {
        super.getDecoratedBoundsWithMargins(view, rect);
        float fCenterY = rect.centerY();
        if (isHorizontal()) {
            fCenterY = rect.centerX();
        }
        float maskedItemSizeForLocOffset = getMaskedItemSizeForLocOffset(fCenterY, getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), fCenterY, true));
        float fWidth = isHorizontal() ? (rect.width() - maskedItemSizeForLocOffset) / 2.0f : 0.0f;
        float fHeight = isHorizontal() ? 0.0f : (rect.height() - maskedItemSizeForLocOffset) / 2.0f;
        rect.set((int) (rect.left + fWidth), (int) (rect.top + fHeight), (int) (rect.right - fWidth), (int) (rect.bottom - fHeight));
    }

    public int getOffsetToScrollToPosition(int i5, KeylineState keylineState) {
        return getScrollOffsetForPosition(i5, keylineState) - this.scrollOffset;
    }

    public int getOffsetToScrollToPositionForSnap(int i5, boolean z9) {
        int offsetToScrollToPosition = getOffsetToScrollToPosition(i5, this.keylineStateList.getShiftedState(this.scrollOffset, this.minScroll, this.maxScroll, true));
        int offsetToScrollToPosition2 = this.keylineStatePositionMap != null ? getOffsetToScrollToPosition(i5, getKeylineStateForPosition(i5)) : offsetToScrollToPosition;
        return (!z9 || Math.abs(offsetToScrollToPosition2) >= Math.abs(offsetToScrollToPosition)) ? offsetToScrollToPosition : offsetToScrollToPosition2;
    }

    public int getOrientation() {
        return this.orientationHelper.orientation;
    }

    @Override // com.google.android.material.carousel.Carousel
    public boolean isHorizontal() {
        return this.orientationHelper.orientation == 0;
    }

    public boolean isLayoutRtl() {
        return isHorizontal() && getLayoutDirection() == 1;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void measureChildWithMargins(View view, int i5, int i7) {
        if (!(view instanceof Maskable)) {
            throw new IllegalStateException("All children of a RecyclerView using CarouselLayoutManager must use MaskableFrameLayout as their root ViewGroup.");
        }
        C0372z0 c0372z0 = (C0372z0) view.getLayoutParams();
        Rect rect = new Rect();
        calculateItemDecorationsForChild(view, rect);
        int i9 = rect.left + rect.right + i5;
        int i10 = rect.top + rect.bottom + i7;
        KeylineStateList keylineStateList = this.keylineStateList;
        float itemSize = (keylineStateList == null || this.orientationHelper.orientation != 0) ? ((ViewGroup.MarginLayoutParams) c0372z0).width : keylineStateList.getDefaultState().getItemSize();
        KeylineStateList keylineStateList2 = this.keylineStateList;
        view.measure(AbstractC0370y0.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingRight() + getPaddingLeft() + ((ViewGroup.MarginLayoutParams) c0372z0).leftMargin + ((ViewGroup.MarginLayoutParams) c0372z0).rightMargin + i9, (int) itemSize, canScrollHorizontally()), AbstractC0370y0.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingBottom() + getPaddingTop() + ((ViewGroup.MarginLayoutParams) c0372z0).topMargin + ((ViewGroup.MarginLayoutParams) c0372z0).bottomMargin + i10, (int) ((keylineStateList2 == null || this.orientationHelper.orientation != 1) ? ((ViewGroup.MarginLayoutParams) c0372z0).height : keylineStateList2.getDefaultState().getItemSize()), canScrollVertically()));
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        refreshKeylineState();
        recyclerView.addOnLayoutChangeListener(this.recyclerViewSizeChangeListener);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void onDetachedFromWindow(RecyclerView recyclerView, G0 g02) {
        onDetachedFromWindow(recyclerView);
        recyclerView.removeOnLayoutChangeListener(this.recyclerViewSizeChangeListener);
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public View onFocusSearchFailed(View view, int i5, G0 g02, R0 r02) {
        int iConvertFocusDirectionToLayoutDirection;
        if (getChildCount() == 0 || (iConvertFocusDirectionToLayoutDirection = convertFocusDirectionToLayoutDirection(i5)) == Integer.MIN_VALUE) {
            return null;
        }
        if (iConvertFocusDirectionToLayoutDirection == -1) {
            if (getPosition(view) == 0) {
                return null;
            }
            addViewAtPosition(g02, getPosition(getChildAt(0)) - 1, 0);
            return getChildClosestToStart();
        }
        if (getPosition(view) == getItemCount() - 1) {
            return null;
        }
        addViewAtPosition(g02, getPosition(getChildAt(getChildCount() - 1)) + 1, -1);
        return getChildClosestToEnd();
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            accessibilityEvent.setFromIndex(getPosition(getChildAt(0)));
            accessibilityEvent.setToIndex(getPosition(getChildAt(getChildCount() - 1)));
        }
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void onItemsAdded(RecyclerView recyclerView, int i5, int i7) {
        super.onItemsAdded(recyclerView, i5, i7);
        updateItemCount();
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void onItemsRemoved(RecyclerView recyclerView, int i5, int i7) {
        super.onItemsRemoved(recyclerView, i5, i7);
        updateItemCount();
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void onLayoutChildren(G0 g02, R0 r02) {
        if (r02.b() <= 0 || getContainerSize() <= 0.0f) {
            removeAndRecycleAllViews(g02);
            this.currentFillStartPosition = 0;
            return;
        }
        boolean zIsLayoutRtl = isLayoutRtl();
        boolean z9 = this.keylineStateList == null;
        if (z9) {
            recalculateKeylineStateList(g02);
        }
        int iCalculateStartScroll = calculateStartScroll(this.keylineStateList);
        int iCalculateEndScroll = calculateEndScroll(r02, this.keylineStateList);
        this.minScroll = zIsLayoutRtl ? iCalculateEndScroll : iCalculateStartScroll;
        if (zIsLayoutRtl) {
            iCalculateEndScroll = iCalculateStartScroll;
        }
        this.maxScroll = iCalculateEndScroll;
        if (z9) {
            this.scrollOffset = iCalculateStartScroll;
            this.keylineStatePositionMap = this.keylineStateList.getKeylineStateForPositionMap(getItemCount(), this.minScroll, this.maxScroll, isLayoutRtl());
            int i5 = this.currentEstimatedPosition;
            if (i5 != -1) {
                this.scrollOffset = getScrollOffsetForPosition(i5, getKeylineStateForPosition(i5));
            }
        }
        int i7 = this.scrollOffset;
        this.scrollOffset = i7 + calculateShouldScrollBy(0, i7, this.minScroll, this.maxScroll);
        this.currentFillStartPosition = c.d(this.currentFillStartPosition, 0, r02.b());
        updateCurrentKeylineStateForScrollOffset(this.keylineStateList);
        detachAndScrapAttachedViews(g02);
        fill(g02, r02);
        this.lastItemCount = getItemCount();
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void onLayoutCompleted(R0 r02) {
        if (getChildCount() == 0) {
            this.currentFillStartPosition = 0;
        } else {
            this.currentFillStartPosition = getPosition(getChildAt(0));
        }
        validateChildOrderIfDebugging();
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public boolean requestChildRectangleOnScreen(RecyclerView recyclerView, View view, Rect rect, boolean z9, boolean z10) {
        int smallestScrollOffsetToFocalKeyline;
        if (this.keylineStateList == null || (smallestScrollOffsetToFocalKeyline = getSmallestScrollOffsetToFocalKeyline(getPosition(view), getKeylineStateForPosition(getPosition(view)))) == 0) {
            return false;
        }
        scrollBy(recyclerView, getSmallestScrollOffsetToFocalKeyline(getPosition(view), this.keylineStateList.getShiftedState(this.scrollOffset + calculateShouldScrollBy(smallestScrollOffsetToFocalKeyline, this.scrollOffset, this.minScroll, this.maxScroll), this.minScroll, this.maxScroll)));
        return true;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public int scrollHorizontallyBy(int i5, G0 g02, R0 r02) {
        if (canScrollHorizontally()) {
            return scrollBy(i5, g02, r02);
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void scrollToPosition(int i5) {
        this.currentEstimatedPosition = i5;
        if (this.keylineStateList == null) {
            return;
        }
        this.scrollOffset = getScrollOffsetForPosition(i5, getKeylineStateForPosition(i5));
        this.currentFillStartPosition = c.d(i5, 0, Math.max(0, getItemCount() - 1));
        updateCurrentKeylineStateForScrollOffset(this.keylineStateList);
        requestLayout();
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public int scrollVerticallyBy(int i5, G0 g02, R0 r02) {
        if (canScrollVertically()) {
            return scrollBy(i5, g02, r02);
        }
        return 0;
    }

    public void setCarouselAlignment(int i5) {
        this.carouselAlignment = i5;
        refreshKeylineState();
    }

    public void setCarouselStrategy(CarouselStrategy carouselStrategy) {
        this.carouselStrategy = carouselStrategy;
        refreshKeylineState();
    }

    public void setDebuggingEnabled(RecyclerView recyclerView, boolean z9) {
        this.isDebuggingEnabled = z9;
        recyclerView.removeItemDecoration(this.debugItemDecoration);
        if (z9) {
            recyclerView.addItemDecoration(this.debugItemDecoration);
        }
        recyclerView.invalidateItemDecorations();
    }

    public void setOrientation(int i5) {
        if (i5 != 0 && i5 != 1) {
            throw new IllegalArgumentException(l.o(i5, "invalid orientation:"));
        }
        assertNotInLayoutOrScroll(null);
        CarouselOrientationHelper carouselOrientationHelper = this.orientationHelper;
        if (carouselOrientationHelper == null || i5 != carouselOrientationHelper.orientation) {
            this.orientationHelper = CarouselOrientationHelper.createOrientationHelper(this, i5);
            refreshKeylineState();
        }
    }

    @Override // androidx.recyclerview.widget.AbstractC0370y0
    public void smoothScrollToPosition(RecyclerView recyclerView, R0 r02, int i5) {
        U u5 = new U(recyclerView.getContext()) { // from class: com.google.android.material.carousel.CarouselLayoutManager.1
            @Override // androidx.recyclerview.widget.U
            public int calculateDxToMakeVisible(View view, int i7) {
                if (CarouselLayoutManager.this.keylineStateList == null || !CarouselLayoutManager.this.isHorizontal()) {
                    return 0;
                }
                CarouselLayoutManager carouselLayoutManager = CarouselLayoutManager.this;
                return carouselLayoutManager.calculateScrollDeltaToMakePositionVisible(carouselLayoutManager.getPosition(view));
            }

            @Override // androidx.recyclerview.widget.U
            public int calculateDyToMakeVisible(View view, int i7) {
                if (CarouselLayoutManager.this.keylineStateList == null || CarouselLayoutManager.this.isHorizontal()) {
                    return 0;
                }
                CarouselLayoutManager carouselLayoutManager = CarouselLayoutManager.this;
                return carouselLayoutManager.calculateScrollDeltaToMakePositionVisible(carouselLayoutManager.getPosition(view));
            }

            @Override // androidx.recyclerview.widget.Q0
            public PointF computeScrollVectorForPosition(int i7) {
                return CarouselLayoutManager.this.computeScrollVectorForPosition(i7);
            }
        };
        u5.setTargetPosition(i5);
        startSmoothScroll(u5);
    }

    public CarouselLayoutManager(CarouselStrategy carouselStrategy) {
        this(carouselStrategy, 0);
    }

    public CarouselLayoutManager(CarouselStrategy carouselStrategy, int i5) {
        this.isDebuggingEnabled = false;
        this.debugItemDecoration = new DebugItemDecoration();
        this.currentFillStartPosition = 0;
        this.recyclerViewSizeChangeListener = new Y3.d(1, this);
        this.currentEstimatedPosition = -1;
        this.carouselAlignment = 0;
        setCarouselStrategy(carouselStrategy);
        setOrientation(i5);
    }

    private int scrollBy(int i5, G0 g02, R0 r02) {
        float f2;
        if (getChildCount() == 0 || i5 == 0) {
            return 0;
        }
        if (this.keylineStateList == null) {
            recalculateKeylineStateList(g02);
        }
        int iCalculateShouldScrollBy = calculateShouldScrollBy(i5, this.scrollOffset, this.minScroll, this.maxScroll);
        this.scrollOffset += iCalculateShouldScrollBy;
        updateCurrentKeylineStateForScrollOffset(this.keylineStateList);
        float itemSize = this.currentKeylineState.getItemSize() / 2.0f;
        float fCalculateChildStartForFill = calculateChildStartForFill(getPosition(getChildAt(0)));
        Rect rect = new Rect();
        if (isLayoutRtl()) {
            f2 = this.currentKeylineState.getLastFocalKeyline().locOffset;
        } else {
            f2 = this.currentKeylineState.getFirstFocalKeyline().locOffset;
        }
        float f7 = Float.MAX_VALUE;
        for (int i7 = 0; i7 < getChildCount(); i7++) {
            View childAt = getChildAt(i7);
            float fAbs = Math.abs(f2 - offsetChild(childAt, fCalculateChildStartForFill, itemSize, rect));
            if (childAt != null && fAbs < f7) {
                this.currentEstimatedPosition = getPosition(childAt);
                f7 = fAbs;
            }
            fCalculateChildStartForFill = addEnd(fCalculateChildStartForFill, this.currentKeylineState.getItemSize());
        }
        fill(g02, r02);
        return iCalculateShouldScrollBy;
    }

    @SuppressLint({"UnknownNullness"})
    public CarouselLayoutManager(Context context, AttributeSet attributeSet, int i5, int i7) {
        this.isDebuggingEnabled = false;
        this.debugItemDecoration = new DebugItemDecoration();
        this.currentFillStartPosition = 0;
        this.recyclerViewSizeChangeListener = new Y3.d(1, this);
        this.currentEstimatedPosition = -1;
        this.carouselAlignment = 0;
        setCarouselStrategy(new MultiBrowseCarouselStrategy());
        setCarouselAttributes(context, attributeSet);
    }
}
