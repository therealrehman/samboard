package com.google.android.material.datepicker;

import L.l;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.core.view.C0210b;
import androidx.core.view.W;
import com.google.android.flexbox.FlexItem;
import com.google.android.material.R;
import com.google.android.material.internal.ViewUtils;
import java.util.Calendar;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
final class MaterialCalendarGridView extends GridView {
    private final Calendar dayCompute;
    private final boolean nestedScrollable;

    public MaterialCalendarGridView(Context context) {
        this(context, null);
    }

    private void gainFocus(int i5, Rect rect) {
        if (i5 == 33) {
            setSelection(getAdapter().lastPositionInMonth());
        } else if (i5 == 130) {
            setSelection(getAdapter().firstPositionInMonth());
        } else {
            super.onFocusChanged(true, i5, rect);
        }
    }

    private View getChildAtPosition(int i5) {
        return getChildAt(i5 - getFirstVisiblePosition());
    }

    private static int horizontalMidPoint(View view) {
        return (view.getWidth() / 2) + view.getLeft();
    }

    private static boolean skipMonth(Long l2, Long l9, Long l10, Long l11) {
        return l2 == null || l9 == null || l10 == null || l11 == null || l10.longValue() > l9.longValue() || l11.longValue() < l2.longValue();
    }

    @Override // android.widget.AbsListView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getAdapter().notifyDataSetChanged();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int iDayToPosition;
        int iHorizontalMidPoint;
        int iDayToPosition2;
        int iHorizontalMidPoint2;
        int width;
        int i5;
        MaterialCalendarGridView materialCalendarGridView = this;
        super.onDraw(canvas);
        MonthAdapter adapter = getAdapter();
        DateSelector<?> dateSelector = adapter.dateSelector;
        CalendarStyle calendarStyle = adapter.calendarStyle;
        int iMax = Math.max(adapter.firstPositionInMonth(), getFirstVisiblePosition());
        int iMin = Math.min(adapter.lastPositionInMonth(), getLastVisiblePosition());
        Long item = adapter.getItem(iMax);
        Long item2 = adapter.getItem(iMin);
        Iterator<K.c> it = dateSelector.getSelectedRanges().iterator();
        while (it.hasNext()) {
            K.c next = it.next();
            Object obj = next.f1639a;
            if (obj != null) {
                Object obj2 = next.f1640b;
                if (obj2 != null) {
                    Long l2 = (Long) obj;
                    long jLongValue = l2.longValue();
                    Long l9 = (Long) obj2;
                    long jLongValue2 = l9.longValue();
                    if (!skipMonth(item, item2, l2, l9)) {
                        boolean zIsLayoutRtl = ViewUtils.isLayoutRtl(this);
                        if (jLongValue < item.longValue()) {
                            iHorizontalMidPoint = adapter.isFirstInRow(iMax) ? 0 : !zIsLayoutRtl ? materialCalendarGridView.getChildAtPosition(iMax - 1).getRight() : materialCalendarGridView.getChildAtPosition(iMax - 1).getLeft();
                            iDayToPosition = iMax;
                        } else {
                            materialCalendarGridView.dayCompute.setTimeInMillis(jLongValue);
                            iDayToPosition = adapter.dayToPosition(materialCalendarGridView.dayCompute.get(5));
                            iHorizontalMidPoint = horizontalMidPoint(materialCalendarGridView.getChildAtPosition(iDayToPosition));
                        }
                        if (jLongValue2 > item2.longValue()) {
                            iHorizontalMidPoint2 = adapter.isLastInRow(iMin) ? getWidth() : !zIsLayoutRtl ? materialCalendarGridView.getChildAtPosition(iMin).getRight() : materialCalendarGridView.getChildAtPosition(iMin).getLeft();
                            iDayToPosition2 = iMin;
                        } else {
                            materialCalendarGridView.dayCompute.setTimeInMillis(jLongValue2);
                            iDayToPosition2 = adapter.dayToPosition(materialCalendarGridView.dayCompute.get(5));
                            iHorizontalMidPoint2 = horizontalMidPoint(materialCalendarGridView.getChildAtPosition(iDayToPosition2));
                        }
                        int itemId = (int) adapter.getItemId(iDayToPosition);
                        int i7 = iMax;
                        int i9 = iMin;
                        int itemId2 = (int) adapter.getItemId(iDayToPosition2);
                        while (itemId <= itemId2) {
                            int numColumns = getNumColumns() * itemId;
                            int numColumns2 = (getNumColumns() + numColumns) - 1;
                            View childAtPosition = materialCalendarGridView.getChildAtPosition(numColumns);
                            int top = childAtPosition.getTop() + calendarStyle.day.getTopInset();
                            MonthAdapter monthAdapter = adapter;
                            int bottom = childAtPosition.getBottom() - calendarStyle.day.getBottomInset();
                            if (zIsLayoutRtl) {
                                int i10 = iDayToPosition2 > numColumns2 ? 0 : iHorizontalMidPoint2;
                                width = numColumns > iDayToPosition ? getWidth() : iHorizontalMidPoint;
                                i5 = i10;
                            } else {
                                i5 = numColumns > iDayToPosition ? 0 : iHorizontalMidPoint;
                                width = iDayToPosition2 > numColumns2 ? getWidth() : iHorizontalMidPoint2;
                            }
                            canvas.drawRect(i5, top, width, bottom, calendarStyle.rangeFill);
                            itemId++;
                            materialCalendarGridView = this;
                            it = it;
                            adapter = monthAdapter;
                        }
                        materialCalendarGridView = this;
                        iMax = i7;
                        iMin = i9;
                    }
                }
            } else {
                materialCalendarGridView = this;
            }
        }
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public void onFocusChanged(boolean z9, int i5, Rect rect) {
        if (z9) {
            gainFocus(i5, rect);
        } else {
            super.onFocusChanged(false, i5, rect);
        }
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i5, KeyEvent keyEvent) {
        if (!super.onKeyDown(i5, keyEvent)) {
            return false;
        }
        if (getSelectedItemPosition() == -1 || getSelectedItemPosition() >= getAdapter().firstPositionInMonth()) {
            return true;
        }
        if (19 != i5) {
            return false;
        }
        setSelection(getAdapter().firstPositionInMonth());
        return true;
    }

    @Override // android.widget.GridView, android.widget.AbsListView, android.view.View
    public void onMeasure(int i5, int i7) {
        if (!this.nestedScrollable) {
            super.onMeasure(i5, i7);
            return;
        }
        super.onMeasure(i5, View.MeasureSpec.makeMeasureSpec(FlexItem.MAX_SIZE, Integer.MIN_VALUE));
        getLayoutParams().height = getMeasuredHeight();
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public void setSelection(int i5) {
        if (i5 < getAdapter().firstPositionInMonth()) {
            super.setSelection(getAdapter().firstPositionInMonth());
        } else {
            super.setSelection(i5);
        }
    }

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @Override // android.widget.AdapterView
    public final void setAdapter(ListAdapter listAdapter) {
        if (!(listAdapter instanceof MonthAdapter)) {
            throw new IllegalArgumentException(String.format("%1$s must have its Adapter set to a %2$s", MaterialCalendarGridView.class.getCanonicalName(), MonthAdapter.class.getCanonicalName()));
        }
        super.setAdapter(listAdapter);
    }

    public MaterialCalendarGridView(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5);
        this.dayCompute = UtcDates.getUtcCalendar();
        if (MaterialDatePicker.isFullscreen(getContext())) {
            setNextFocusLeftId(R.id.cancel_button);
            setNextFocusRightId(R.id.confirm_button);
        }
        this.nestedScrollable = MaterialDatePicker.isNestedScrollable(getContext());
        W.i(this, new C0210b() { // from class: com.google.android.material.datepicker.MaterialCalendarGridView.1
            @Override // androidx.core.view.C0210b
            public void onInitializeAccessibilityNodeInfo(View view, l lVar) {
                super.onInitializeAccessibilityNodeInfo(view, lVar);
                lVar.f1793a.setCollectionInfo(null);
            }
        });
    }

    @Override // android.widget.GridView, android.widget.AdapterView
    public MonthAdapter getAdapter() {
        return (MonthAdapter) super.getAdapter();
    }
}
