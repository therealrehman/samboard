package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.flexbox.FlexItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class ConstraintLayout extends ViewGroup {
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_DRAW_CONSTRAINTS = false;
    public static final int DESIGN_INFO_ID = 0;
    private static final boolean MEASURE = false;
    private static final boolean OPTIMIZE_HEIGHT_CHANGE = false;
    private static final String TAG = "ConstraintLayout";
    private static final boolean USE_CONSTRAINTS_HELPER = true;
    public static final String VERSION = "ConstraintLayout-2.1.4";
    private static t sSharedValues;
    SparseArray<View> mChildrenByIds;
    private ArrayList<c> mConstraintHelpers;
    protected i mConstraintLayoutSpec;
    private p mConstraintSet;
    private int mConstraintSetId;
    private q mConstraintsChangedListener;
    private HashMap<String, Integer> mDesignIds;
    protected boolean mDirtyHierarchy;
    private int mLastMeasureHeight;
    int mLastMeasureHeightMode;
    int mLastMeasureHeightSize;
    private int mLastMeasureWidth;
    int mLastMeasureWidthMode;
    int mLastMeasureWidthSize;
    protected u.e mLayoutWidget;
    private int mMaxHeight;
    private int mMaxWidth;
    f mMeasurer;
    private s.d mMetrics;
    private int mMinHeight;
    private int mMinWidth;
    private int mOnMeasureHeightMeasureSpec;
    private int mOnMeasureWidthMeasureSpec;
    private int mOptimizationLevel;
    private SparseArray<u.d> mTempMapIdToWidget;

    public ConstraintLayout(Context context) {
        super(context);
        this.mChildrenByIds = new SparseArray<>();
        this.mConstraintHelpers = new ArrayList<>(4);
        this.mLayoutWidget = new u.e();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = 257;
        this.mConstraintSet = null;
        this.mConstraintLayoutSpec = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap<>();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        this.mTempMapIdToWidget = new SparseArray<>();
        this.mMeasurer = new f(this, this);
        this.mOnMeasureWidthMeasureSpec = 0;
        this.mOnMeasureHeightMeasureSpec = 0;
        a(null, 0);
    }

    private int getPaddingWidth() {
        int iMax = Math.max(0, getPaddingRight()) + Math.max(0, getPaddingLeft());
        int iMax2 = Math.max(0, getPaddingEnd()) + Math.max(0, getPaddingStart());
        return iMax2 > 0 ? iMax2 : iMax;
    }

    public static t getSharedValues() {
        if (sSharedValues == null) {
            t tVar = new t();
            new SparseIntArray();
            new HashMap();
            sSharedValues = tVar;
        }
        return sSharedValues;
    }

    public final void a(AttributeSet attributeSet, int i5) {
        u.e eVar = this.mLayoutWidget;
        eVar.f14225f0 = this;
        f fVar = this.mMeasurer;
        eVar.f14264u0 = fVar;
        eVar.f14262s0.f14536f = fVar;
        this.mChildrenByIds.put(getId(), this);
        this.mConstraintSet = null;
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, s.f7142b, i5, 0);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i7 = 0; i7 < indexCount; i7++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i7);
                if (index == 16) {
                    this.mMinWidth = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.mMinWidth);
                } else if (index == 17) {
                    this.mMinHeight = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.mMinHeight);
                } else if (index == 14) {
                    this.mMaxWidth = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxWidth);
                } else if (index == 15) {
                    this.mMaxHeight = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxHeight);
                } else if (index == 113) {
                    this.mOptimizationLevel = typedArrayObtainStyledAttributes.getInt(index, this.mOptimizationLevel);
                } else if (index == 56) {
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    if (resourceId != 0) {
                        try {
                            parseLayoutDescription(resourceId);
                        } catch (Resources.NotFoundException unused) {
                            this.mConstraintLayoutSpec = null;
                        }
                    }
                } else if (index == 34) {
                    int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, 0);
                    try {
                        p pVar = new p();
                        this.mConstraintSet = pVar;
                        pVar.h(getContext(), resourceId2);
                    } catch (Resources.NotFoundException unused2) {
                        this.mConstraintSet = null;
                    }
                    this.mConstraintSetId = resourceId2;
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
        u.e eVar2 = this.mLayoutWidget;
        eVar2.D0 = this.mOptimizationLevel;
        s.c.f13341p = eVar2.W(512);
    }

    /* JADX WARN: Removed duplicated region for block: B:149:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x02bc  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:149:0x02b7 -> B:150:0x02b8). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void applyConstraintsFromLayoutParams(boolean r19, android.view.View r20, u.d r21, androidx.constraintlayout.widget.e r22, android.util.SparseArray<u.d> r23) {
        /*
            Method dump skipped, instruction units count: 802
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.applyConstraintsFromLayoutParams(boolean, android.view.View, u.d, androidx.constraintlayout.widget.e, android.util.SparseArray):void");
    }

    public final void b(u.d dVar, e eVar, SparseArray sparseArray, int i5, int i7) {
        View view = this.mChildrenByIds.get(i5);
        u.d dVar2 = (u.d) sparseArray.get(i5);
        if (dVar2 == null || view == null || !(view.getLayoutParams() instanceof e)) {
            return;
        }
        eVar.f6974c0 = true;
        if (i7 == 6) {
            e eVar2 = (e) view.getLayoutParams();
            eVar2.f6974c0 = true;
            eVar2.f6996p0.f14194E = true;
        }
        dVar.i(6).b(dVar2.i(i7), eVar.f6947D, eVar.f6946C, true);
        dVar.f14194E = true;
        dVar.i(3).j();
        dVar.i(5).j();
    }

    @Override // android.view.ViewGroup
    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof e;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        Object tag;
        int size;
        ArrayList<c> arrayList = this.mConstraintHelpers;
        if (arrayList != null && (size = arrayList.size()) > 0) {
            for (int i5 = 0; i5 < size; i5++) {
                this.mConstraintHelpers.get(i5).getClass();
            }
        }
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            float width = getWidth();
            float height = getHeight();
            int childCount = getChildCount();
            for (int i7 = 0; i7 < childCount; i7++) {
                View childAt = getChildAt(i7);
                if (childAt.getVisibility() != 8 && (tag = childAt.getTag()) != null && (tag instanceof String)) {
                    String[] strArrSplit = ((String) tag).split(",");
                    if (strArrSplit.length == 4) {
                        int i9 = Integer.parseInt(strArrSplit[0]);
                        int i10 = Integer.parseInt(strArrSplit[1]);
                        int i11 = Integer.parseInt(strArrSplit[2]);
                        int i12 = (int) ((i9 / 1080.0f) * width);
                        int i13 = (int) ((i10 / 1920.0f) * height);
                        Paint paint = new Paint();
                        paint.setColor(-65536);
                        float f2 = i12;
                        float f7 = i13;
                        float f9 = i12 + ((int) ((i11 / 1080.0f) * width));
                        canvas.drawLine(f2, f7, f9, f7, paint);
                        float f10 = i13 + ((int) ((Integer.parseInt(strArrSplit[3]) / 1920.0f) * height));
                        canvas.drawLine(f9, f7, f9, f10, paint);
                        canvas.drawLine(f9, f10, f2, f10, paint);
                        canvas.drawLine(f2, f10, f2, f7, paint);
                        paint.setColor(-16711936);
                        canvas.drawLine(f2, f7, f9, f10, paint);
                        canvas.drawLine(f2, f10, f9, f7, paint);
                    }
                }
            }
        }
    }

    public void fillMetrics(s.d dVar) {
        this.mLayoutWidget.f14266w0.getClass();
    }

    @Override // android.view.View
    public void forceLayout() {
        this.mDirtyHierarchy = true;
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        super.forceLayout();
    }

    public Object getDesignInformation(int i5, Object obj) {
        if (i5 != 0 || !(obj instanceof String)) {
            return null;
        }
        String str = (String) obj;
        HashMap<String, Integer> map = this.mDesignIds;
        if (map == null || !map.containsKey(str)) {
            return null;
        }
        return this.mDesignIds.get(str);
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getOptimizationLevel() {
        return this.mLayoutWidget.D0;
    }

    public String getSceneString() {
        int id;
        StringBuilder sb = new StringBuilder();
        if (this.mLayoutWidget.f14231j == null) {
            int id2 = getId();
            if (id2 != -1) {
                this.mLayoutWidget.f14231j = getContext().getResources().getResourceEntryName(id2);
            } else {
                this.mLayoutWidget.f14231j = "parent";
            }
        }
        u.e eVar = this.mLayoutWidget;
        if (eVar.f14228h0 == null) {
            eVar.f14228h0 = eVar.f14231j;
            Log.v(TAG, " setDebugName " + this.mLayoutWidget.f14228h0);
        }
        for (u.d dVar : this.mLayoutWidget.f14260q0) {
            View view = (View) dVar.f14225f0;
            if (view != null) {
                if (dVar.f14231j == null && (id = view.getId()) != -1) {
                    dVar.f14231j = getContext().getResources().getResourceEntryName(id);
                }
                if (dVar.f14228h0 == null) {
                    dVar.f14228h0 = dVar.f14231j;
                    Log.v(TAG, " setDebugName " + dVar.f14228h0);
                }
            }
        }
        this.mLayoutWidget.n(sb);
        return sb.toString();
    }

    public View getViewById(int i5) {
        return this.mChildrenByIds.get(i5);
    }

    public final u.d getViewWidget(View view) {
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            return null;
        }
        if (view.getLayoutParams() instanceof e) {
            return ((e) view.getLayoutParams()).f6996p0;
        }
        view.setLayoutParams(generateLayoutParams(view.getLayoutParams()));
        if (view.getLayoutParams() instanceof e) {
            return ((e) view.getLayoutParams()).f6996p0;
        }
        return null;
    }

    public boolean isRtl() {
        return (getContext().getApplicationInfo().flags & 4194304) != 0 && 1 == getLayoutDirection();
    }

    public void loadLayoutDescription(int i5) {
        if (i5 == 0) {
            this.mConstraintLayoutSpec = null;
            return;
        }
        try {
            this.mConstraintLayoutSpec = new i(getContext(), this, i5);
        } catch (Resources.NotFoundException unused) {
            this.mConstraintLayoutSpec = null;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onLayout(boolean z9, int i5, int i7, int i9, int i10) {
        int childCount = getChildCount();
        boolean zIsInEditMode = isInEditMode();
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            e eVar = (e) childAt.getLayoutParams();
            u.d dVar = eVar.f6996p0;
            if (childAt.getVisibility() != 8 || eVar.d0 || eVar.f6977e0 || zIsInEditMode) {
                int iR = dVar.r();
                int iS = dVar.s();
                childAt.layout(iR, iS, dVar.q() + iR, dVar.k() + iS);
            }
        }
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            for (int i12 = 0; i12 < size; i12++) {
                this.mConstraintHelpers.get(i12).getClass();
            }
        }
    }

    @Override // android.view.View
    public void onMeasure(int i5, int i7) {
        boolean z9;
        u.d dVar;
        if (this.mOnMeasureWidthMeasureSpec == i5) {
            int i9 = this.mOnMeasureHeightMeasureSpec;
        }
        int i10 = 0;
        if (!this.mDirtyHierarchy) {
            int childCount = getChildCount();
            int i11 = 0;
            while (true) {
                if (i11 >= childCount) {
                    break;
                }
                if (getChildAt(i11).isLayoutRequested()) {
                    this.mDirtyHierarchy = true;
                    break;
                }
                i11++;
            }
        }
        this.mOnMeasureWidthMeasureSpec = i5;
        this.mOnMeasureHeightMeasureSpec = i7;
        this.mLayoutWidget.f14265v0 = isRtl();
        if (this.mDirtyHierarchy) {
            this.mDirtyHierarchy = false;
            int childCount2 = getChildCount();
            int i12 = 0;
            while (true) {
                if (i12 >= childCount2) {
                    z9 = false;
                    break;
                } else {
                    if (getChildAt(i12).isLayoutRequested()) {
                        z9 = true;
                        break;
                    }
                    i12++;
                }
            }
            if (z9) {
                boolean zIsInEditMode = isInEditMode();
                int childCount3 = getChildCount();
                for (int i13 = 0; i13 < childCount3; i13++) {
                    u.d viewWidget = getViewWidget(getChildAt(i13));
                    if (viewWidget != null) {
                        viewWidget.C();
                    }
                }
                Object obj = null;
                if (zIsInEditMode) {
                    for (int i14 = 0; i14 < childCount3; i14++) {
                        View childAt = getChildAt(i14);
                        try {
                            String resourceName = getResources().getResourceName(childAt.getId());
                            setDesignInformation(0, resourceName, Integer.valueOf(childAt.getId()));
                            int iIndexOf = resourceName.indexOf(47);
                            if (iIndexOf != -1) {
                                resourceName = resourceName.substring(iIndexOf + 1);
                            }
                            int id = childAt.getId();
                            if (id == 0) {
                                dVar = this.mLayoutWidget;
                            } else {
                                View viewFindViewById = this.mChildrenByIds.get(id);
                                if (viewFindViewById == null && (viewFindViewById = findViewById(id)) != null && viewFindViewById != this && viewFindViewById.getParent() == this) {
                                    onViewAdded(viewFindViewById);
                                }
                                dVar = viewFindViewById == this ? this.mLayoutWidget : viewFindViewById == null ? null : ((e) viewFindViewById.getLayoutParams()).f6996p0;
                            }
                            dVar.f14228h0 = resourceName;
                        } catch (Resources.NotFoundException unused) {
                        }
                    }
                }
                if (this.mConstraintSetId != -1) {
                    for (int i15 = 0; i15 < childCount3; i15++) {
                        getChildAt(i15).getId();
                    }
                }
                p pVar = this.mConstraintSet;
                if (pVar != null) {
                    pVar.b(this);
                }
                this.mLayoutWidget.f14260q0.clear();
                int size = this.mConstraintHelpers.size();
                if (size > 0) {
                    int i16 = 0;
                    while (i16 < size) {
                        c cVar = this.mConstraintHelpers.get(i16);
                        if (cVar.isInEditMode()) {
                            cVar.setIds(cVar.f6940i);
                        }
                        u.i iVar = cVar.h;
                        if (iVar != null) {
                            iVar.f14316r0 = i10;
                            Arrays.fill(iVar.f14315q0, obj);
                            for (int i17 = i10; i17 < cVar.f6938f; i17++) {
                                int i18 = cVar.f6937e[i17];
                                View viewById = getViewById(i18);
                                if (viewById == null) {
                                    Integer numValueOf = Integer.valueOf(i18);
                                    HashMap map = cVar.f6942k;
                                    String str = (String) map.get(numValueOf);
                                    int iF = cVar.f(this, str);
                                    if (iF != 0) {
                                        cVar.f6937e[i17] = iF;
                                        map.put(Integer.valueOf(iF), str);
                                        viewById = getViewById(iF);
                                    }
                                }
                                if (viewById != null) {
                                    u.i iVar2 = cVar.h;
                                    u.d viewWidget2 = getViewWidget(viewById);
                                    iVar2.getClass();
                                    if (viewWidget2 != iVar2 && viewWidget2 != null) {
                                        int i19 = iVar2.f14316r0 + 1;
                                        u.d[] dVarArr = iVar2.f14315q0;
                                        if (i19 > dVarArr.length) {
                                            iVar2.f14315q0 = (u.d[]) Arrays.copyOf(dVarArr, dVarArr.length * 2);
                                        }
                                        u.d[] dVarArr2 = iVar2.f14315q0;
                                        int i20 = iVar2.f14316r0;
                                        dVarArr2[i20] = viewWidget2;
                                        iVar2.f14316r0 = i20 + 1;
                                    }
                                }
                            }
                            cVar.h.S();
                        }
                        i16++;
                        obj = null;
                        i10 = 0;
                    }
                }
                for (int i21 = 0; i21 < childCount3; i21++) {
                    getChildAt(i21);
                }
                this.mTempMapIdToWidget.clear();
                this.mTempMapIdToWidget.put(0, this.mLayoutWidget);
                this.mTempMapIdToWidget.put(getId(), this.mLayoutWidget);
                for (int i22 = 0; i22 < childCount3; i22++) {
                    View childAt2 = getChildAt(i22);
                    this.mTempMapIdToWidget.put(childAt2.getId(), getViewWidget(childAt2));
                }
                for (int i23 = 0; i23 < childCount3; i23++) {
                    View childAt3 = getChildAt(i23);
                    u.d viewWidget3 = getViewWidget(childAt3);
                    if (viewWidget3 != null) {
                        e eVar = (e) childAt3.getLayoutParams();
                        u.e eVar2 = this.mLayoutWidget;
                        eVar2.f14260q0.add(viewWidget3);
                        u.d dVar2 = viewWidget3.f14208T;
                        if (dVar2 != null) {
                            ((u.e) dVar2).f14260q0.remove(viewWidget3);
                            viewWidget3.C();
                        }
                        viewWidget3.f14208T = eVar2;
                        applyConstraintsFromLayoutParams(zIsInEditMode, childAt3, viewWidget3, eVar, this.mTempMapIdToWidget);
                    }
                }
            }
            if (z9) {
                u.e eVar3 = this.mLayoutWidget;
                eVar3.f14261r0.w(eVar3);
            }
        }
        resolveSystem(this.mLayoutWidget, this.mOptimizationLevel, i5, i7);
        int iQ = this.mLayoutWidget.q();
        int iK = this.mLayoutWidget.k();
        u.e eVar4 = this.mLayoutWidget;
        resolveMeasuredDimension(i5, i7, iQ, iK, eVar4.f14256E0, eVar4.f14257F0);
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        super.onViewAdded(view);
        u.d viewWidget = getViewWidget(view);
        if ((view instanceof Guideline) && !(viewWidget instanceof u.h)) {
            e eVar = (e) view.getLayoutParams();
            u.h hVar = new u.h();
            eVar.f6996p0 = hVar;
            eVar.d0 = true;
            hVar.S(eVar.f6964V);
        }
        if (view instanceof c) {
            c cVar = (c) view;
            cVar.i();
            ((e) view.getLayoutParams()).f6977e0 = true;
            if (!this.mConstraintHelpers.contains(cVar)) {
                this.mConstraintHelpers.add(cVar);
            }
        }
        this.mChildrenByIds.put(view.getId(), view);
        this.mDirtyHierarchy = true;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        super.onViewRemoved(view);
        this.mChildrenByIds.remove(view.getId());
        u.d viewWidget = getViewWidget(view);
        this.mLayoutWidget.f14260q0.remove(viewWidget);
        viewWidget.C();
        this.mConstraintHelpers.remove(view);
        this.mDirtyHierarchy = true;
    }

    public void parseLayoutDescription(int i5) {
        this.mConstraintLayoutSpec = new i(getContext(), this, i5);
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        this.mDirtyHierarchy = true;
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        super.requestLayout();
    }

    public void resolveMeasuredDimension(int i5, int i7, int i9, int i10, boolean z9, boolean z10) {
        f fVar = this.mMeasurer;
        int i11 = fVar.f7011e;
        int iResolveSizeAndState = View.resolveSizeAndState(i9 + fVar.f7010d, i5, 0);
        int iResolveSizeAndState2 = View.resolveSizeAndState(i10 + i11, i7, 0);
        int i12 = iResolveSizeAndState & FlexItem.MAX_SIZE;
        int i13 = iResolveSizeAndState2 & FlexItem.MAX_SIZE;
        int iMin = Math.min(this.mMaxWidth, i12);
        int iMin2 = Math.min(this.mMaxHeight, i13);
        if (z9) {
            iMin |= 16777216;
        }
        if (z10) {
            iMin2 |= 16777216;
        }
        setMeasuredDimension(iMin, iMin2);
        this.mLastMeasureWidth = iMin;
        this.mLastMeasureHeight = iMin2;
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0270  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x0366  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x0379  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0454  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0459  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x045c  */
    /* JADX WARN: Removed duplicated region for block: B:343:0x0603  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00fd A[PHI: r7
      0x00fd: PHI (r7v3 boolean) = (r7v2 boolean), (r7v31 boolean) binds: [B:18:0x00a2, B:346:0x00fd] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x010f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void resolveSystem(u.e r24, int r25, int r26, int r27) {
        /*
            Method dump skipped, instruction units count: 1551
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.resolveSystem(u.e, int, int, int):void");
    }

    public void setConstraintSet(p pVar) {
        this.mConstraintSet = pVar;
    }

    public void setDesignInformation(int i5, Object obj, Object obj2) {
        if (i5 == 0 && (obj instanceof String) && (obj2 instanceof Integer)) {
            if (this.mDesignIds == null) {
                this.mDesignIds = new HashMap<>();
            }
            String strSubstring = (String) obj;
            int iIndexOf = strSubstring.indexOf("/");
            if (iIndexOf != -1) {
                strSubstring = strSubstring.substring(iIndexOf + 1);
            }
            Integer num = (Integer) obj2;
            num.intValue();
            this.mDesignIds.put(strSubstring, num);
        }
    }

    @Override // android.view.View
    public void setId(int i5) {
        this.mChildrenByIds.remove(getId());
        super.setId(i5);
        this.mChildrenByIds.put(getId(), this);
    }

    public void setMaxHeight(int i5) {
        if (i5 == this.mMaxHeight) {
            return;
        }
        this.mMaxHeight = i5;
        requestLayout();
    }

    public void setMaxWidth(int i5) {
        if (i5 == this.mMaxWidth) {
            return;
        }
        this.mMaxWidth = i5;
        requestLayout();
    }

    public void setMinHeight(int i5) {
        if (i5 == this.mMinHeight) {
            return;
        }
        this.mMinHeight = i5;
        requestLayout();
    }

    public void setMinWidth(int i5) {
        if (i5 == this.mMinWidth) {
            return;
        }
        this.mMinWidth = i5;
        requestLayout();
    }

    public void setOnConstraintsChanged(q qVar) {
        i iVar = this.mConstraintLayoutSpec;
        if (iVar != null) {
            iVar.getClass();
        }
    }

    public void setOptimizationLevel(int i5) {
        this.mOptimizationLevel = i5;
        u.e eVar = this.mLayoutWidget;
        eVar.D0 = i5;
        s.c.f13341p = eVar.W(512);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setSelfDimensionBehaviour(u.e r9, int r10, int r11, int r12, int r13) {
        /*
            r8 = this;
            androidx.constraintlayout.widget.f r0 = r8.mMeasurer
            int r1 = r0.f7011e
            int r0 = r0.f7010d
            int r2 = r8.getChildCount()
            r3 = 0
            r4 = 1
            r5 = 1073741824(0x40000000, float:2.0)
            r6 = -2147483648(0xffffffff80000000, float:-0.0)
            r7 = 2
            if (r10 == r6) goto L2e
            if (r10 == 0) goto L22
            if (r10 == r5) goto L1a
            r11 = r3
        L18:
            r10 = r4
            goto L37
        L1a:
            int r10 = r8.mMaxWidth
            int r10 = r10 - r0
            int r11 = java.lang.Math.min(r10, r11)
            goto L18
        L22:
            if (r2 != 0) goto L2c
            int r10 = r8.mMinWidth
            int r11 = java.lang.Math.max(r3, r10)
        L2a:
            r10 = r7
            goto L37
        L2c:
            r11 = r3
            goto L2a
        L2e:
            if (r2 != 0) goto L2a
            int r10 = r8.mMinWidth
            int r11 = java.lang.Math.max(r3, r10)
            goto L2a
        L37:
            if (r12 == r6) goto L53
            if (r12 == 0) goto L48
            if (r12 == r5) goto L40
            r13 = r3
        L3e:
            r7 = r4
            goto L5b
        L40:
            int r12 = r8.mMaxHeight
            int r12 = r12 - r1
            int r13 = java.lang.Math.min(r12, r13)
            goto L3e
        L48:
            if (r2 != 0) goto L51
            int r12 = r8.mMinHeight
            int r13 = java.lang.Math.max(r3, r12)
            goto L5b
        L51:
            r13 = r3
            goto L5b
        L53:
            if (r2 != 0) goto L5b
            int r12 = r8.mMinHeight
            int r13 = java.lang.Math.max(r3, r12)
        L5b:
            int r12 = r9.q()
            if (r11 != r12) goto L67
            int r12 = r9.k()
            if (r13 == r12) goto L6b
        L67:
            v.e r12 = r9.f14262s0
            r12.f14533c = r4
        L6b:
            r9.f14213Y = r3
            r9.f14214Z = r3
            int r12 = r8.mMaxWidth
            int r12 = r12 - r0
            int[] r2 = r9.f14192C
            r2[r3] = r12
            int r12 = r8.mMaxHeight
            int r12 = r12 - r1
            r2[r4] = r12
            r9.f14218b0 = r3
            r9.f14220c0 = r3
            r9.M(r10)
            r9.O(r11)
            r9.N(r7)
            r9.L(r13)
            int r10 = r8.mMinWidth
            int r10 = r10 - r0
            if (r10 >= 0) goto L93
            r9.f14218b0 = r3
            goto L95
        L93:
            r9.f14218b0 = r10
        L95:
            int r8 = r8.mMinHeight
            int r8 = r8 - r1
            if (r8 >= 0) goto L9d
            r9.f14220c0 = r3
            goto L9f
        L9d:
            r9.f14220c0 = r8
        L9f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.ConstraintLayout.setSelfDimensionBehaviour(u.e, int, int, int, int):void");
    }

    public void setState(int i5, int i7, int i9) {
        i iVar = this.mConstraintLayoutSpec;
        if (iVar != null) {
            float f2 = i7;
            float f7 = i9;
            int i10 = iVar.f7025b;
            SparseArray sparseArray = iVar.f7027d;
            int i11 = 0;
            ConstraintLayout constraintLayout = iVar.f7024a;
            if (i10 == i5) {
                g gVar = i5 == -1 ? (g) sparseArray.valueAt(0) : (g) sparseArray.get(i10);
                int i12 = iVar.f7026c;
                if (i12 == -1 || !((h) gVar.f7015b.get(i12)).a(f2, f7)) {
                    while (true) {
                        ArrayList arrayList = gVar.f7015b;
                        if (i11 >= arrayList.size()) {
                            i11 = -1;
                            break;
                        } else if (((h) arrayList.get(i11)).a(f2, f7)) {
                            break;
                        } else {
                            i11++;
                        }
                    }
                    if (iVar.f7026c == i11) {
                        return;
                    }
                    ArrayList arrayList2 = gVar.f7015b;
                    p pVar = i11 == -1 ? null : ((h) arrayList2.get(i11)).f7023f;
                    if (i11 != -1) {
                        int i13 = ((h) arrayList2.get(i11)).f7022e;
                    }
                    if (pVar == null) {
                        return;
                    }
                    iVar.f7026c = i11;
                    pVar.a(constraintLayout);
                    return;
                }
                return;
            }
            iVar.f7025b = i5;
            g gVar2 = (g) sparseArray.get(i5);
            while (true) {
                ArrayList arrayList3 = gVar2.f7015b;
                if (i11 >= arrayList3.size()) {
                    i11 = -1;
                    break;
                } else if (((h) arrayList3.get(i11)).a(f2, f7)) {
                    break;
                } else {
                    i11++;
                }
            }
            ArrayList arrayList4 = gVar2.f7015b;
            p pVar2 = i11 == -1 ? gVar2.f7017d : ((h) arrayList4.get(i11)).f7023f;
            if (i11 != -1) {
                int i14 = ((h) arrayList4.get(i11)).f7022e;
            }
            if (pVar2 != null) {
                iVar.f7026c = i11;
                pVar2.a(constraintLayout);
                return;
            }
            Log.v("ConstraintLayoutStates", "NO Constraint set found ! id=" + i5 + ", dim =" + f2 + ", " + f7);
        }
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override // android.view.ViewGroup
    public e generateDefaultLayoutParams() {
        return new e(-2, -2);
    }

    @Override // android.view.ViewGroup
    public e generateLayoutParams(AttributeSet attributeSet) {
        Context context = getContext();
        e eVar = new e(context, attributeSet);
        eVar.f6969a = -1;
        eVar.f6971b = -1;
        eVar.f6973c = -1.0f;
        eVar.f6975d = true;
        eVar.f6976e = -1;
        eVar.f6978f = -1;
        eVar.f6980g = -1;
        eVar.h = -1;
        eVar.f6983i = -1;
        eVar.f6985j = -1;
        eVar.f6986k = -1;
        eVar.f6988l = -1;
        eVar.f6990m = -1;
        eVar.f6992n = -1;
        eVar.o = -1;
        eVar.f6995p = -1;
        eVar.f6997q = 0;
        eVar.f6998r = 0.0f;
        eVar.f6999s = -1;
        eVar.f7000t = -1;
        eVar.f7001u = -1;
        eVar.f7002v = -1;
        eVar.f7003w = Integer.MIN_VALUE;
        eVar.f7004x = Integer.MIN_VALUE;
        eVar.f7005y = Integer.MIN_VALUE;
        eVar.f7006z = Integer.MIN_VALUE;
        eVar.f6944A = Integer.MIN_VALUE;
        eVar.f6945B = Integer.MIN_VALUE;
        eVar.f6946C = Integer.MIN_VALUE;
        eVar.f6947D = 0;
        eVar.f6948E = 0.5f;
        eVar.f6949F = 0.5f;
        eVar.G = null;
        eVar.f6950H = -1.0f;
        eVar.f6951I = -1.0f;
        eVar.f6952J = 0;
        eVar.f6953K = 0;
        eVar.f6954L = 0;
        eVar.f6955M = 0;
        eVar.f6956N = 0;
        eVar.f6957O = 0;
        eVar.f6958P = 0;
        eVar.f6959Q = 0;
        eVar.f6960R = 1.0f;
        eVar.f6961S = 1.0f;
        eVar.f6962T = -1;
        eVar.f6963U = -1;
        eVar.f6964V = -1;
        eVar.f6965W = false;
        eVar.f6966X = false;
        eVar.f6967Y = null;
        eVar.f6968Z = 0;
        eVar.f6970a0 = true;
        eVar.f6972b0 = true;
        eVar.f6974c0 = false;
        eVar.d0 = false;
        eVar.f6977e0 = false;
        eVar.f6979f0 = -1;
        eVar.f6981g0 = -1;
        eVar.f6982h0 = -1;
        eVar.f6984i0 = -1;
        eVar.j0 = Integer.MIN_VALUE;
        eVar.f6987k0 = Integer.MIN_VALUE;
        eVar.f6989l0 = 0.5f;
        eVar.f6996p0 = new u.d();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, s.f7142b);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i5 = 0; i5 < indexCount; i5++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i5);
            int i7 = d.f6943a.get(index);
            switch (i7) {
                case 1:
                    eVar.f6964V = typedArrayObtainStyledAttributes.getInt(index, eVar.f6964V);
                    break;
                case 2:
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, eVar.f6995p);
                    eVar.f6995p = resourceId;
                    if (resourceId == -1) {
                        eVar.f6995p = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 3:
                    eVar.f6997q = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, eVar.f6997q);
                    break;
                case 4:
                    float f2 = typedArrayObtainStyledAttributes.getFloat(index, eVar.f6998r) % 360.0f;
                    eVar.f6998r = f2;
                    if (f2 < 0.0f) {
                        eVar.f6998r = (360.0f - f2) % 360.0f;
                    }
                    break;
                case 5:
                    eVar.f6969a = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, eVar.f6969a);
                    break;
                case 6:
                    eVar.f6971b = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, eVar.f6971b);
                    break;
                case 7:
                    eVar.f6973c = typedArrayObtainStyledAttributes.getFloat(index, eVar.f6973c);
                    break;
                case 8:
                    int resourceId2 = typedArrayObtainStyledAttributes.getResourceId(index, eVar.f6976e);
                    eVar.f6976e = resourceId2;
                    if (resourceId2 == -1) {
                        eVar.f6976e = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 9:
                    int resourceId3 = typedArrayObtainStyledAttributes.getResourceId(index, eVar.f6978f);
                    eVar.f6978f = resourceId3;
                    if (resourceId3 == -1) {
                        eVar.f6978f = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 10:
                    int resourceId4 = typedArrayObtainStyledAttributes.getResourceId(index, eVar.f6980g);
                    eVar.f6980g = resourceId4;
                    if (resourceId4 == -1) {
                        eVar.f6980g = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 11:
                    int resourceId5 = typedArrayObtainStyledAttributes.getResourceId(index, eVar.h);
                    eVar.h = resourceId5;
                    if (resourceId5 == -1) {
                        eVar.h = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 12:
                    int resourceId6 = typedArrayObtainStyledAttributes.getResourceId(index, eVar.f6983i);
                    eVar.f6983i = resourceId6;
                    if (resourceId6 == -1) {
                        eVar.f6983i = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 13:
                    int resourceId7 = typedArrayObtainStyledAttributes.getResourceId(index, eVar.f6985j);
                    eVar.f6985j = resourceId7;
                    if (resourceId7 == -1) {
                        eVar.f6985j = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 14:
                    int resourceId8 = typedArrayObtainStyledAttributes.getResourceId(index, eVar.f6986k);
                    eVar.f6986k = resourceId8;
                    if (resourceId8 == -1) {
                        eVar.f6986k = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 15:
                    int resourceId9 = typedArrayObtainStyledAttributes.getResourceId(index, eVar.f6988l);
                    eVar.f6988l = resourceId9;
                    if (resourceId9 == -1) {
                        eVar.f6988l = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 16:
                    int resourceId10 = typedArrayObtainStyledAttributes.getResourceId(index, eVar.f6990m);
                    eVar.f6990m = resourceId10;
                    if (resourceId10 == -1) {
                        eVar.f6990m = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 17:
                    int resourceId11 = typedArrayObtainStyledAttributes.getResourceId(index, eVar.f6999s);
                    eVar.f6999s = resourceId11;
                    if (resourceId11 == -1) {
                        eVar.f6999s = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 18:
                    int resourceId12 = typedArrayObtainStyledAttributes.getResourceId(index, eVar.f7000t);
                    eVar.f7000t = resourceId12;
                    if (resourceId12 == -1) {
                        eVar.f7000t = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 19:
                    int resourceId13 = typedArrayObtainStyledAttributes.getResourceId(index, eVar.f7001u);
                    eVar.f7001u = resourceId13;
                    if (resourceId13 == -1) {
                        eVar.f7001u = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 20:
                    int resourceId14 = typedArrayObtainStyledAttributes.getResourceId(index, eVar.f7002v);
                    eVar.f7002v = resourceId14;
                    if (resourceId14 == -1) {
                        eVar.f7002v = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    break;
                case 21:
                    eVar.f7003w = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, eVar.f7003w);
                    break;
                case 22:
                    eVar.f7004x = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, eVar.f7004x);
                    break;
                case 23:
                    eVar.f7005y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, eVar.f7005y);
                    break;
                case 24:
                    eVar.f7006z = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, eVar.f7006z);
                    break;
                case 25:
                    eVar.f6944A = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, eVar.f6944A);
                    break;
                case 26:
                    eVar.f6945B = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, eVar.f6945B);
                    break;
                case 27:
                    eVar.f6965W = typedArrayObtainStyledAttributes.getBoolean(index, eVar.f6965W);
                    break;
                case 28:
                    eVar.f6966X = typedArrayObtainStyledAttributes.getBoolean(index, eVar.f6966X);
                    break;
                case 29:
                    eVar.f6948E = typedArrayObtainStyledAttributes.getFloat(index, eVar.f6948E);
                    break;
                case 30:
                    eVar.f6949F = typedArrayObtainStyledAttributes.getFloat(index, eVar.f6949F);
                    break;
                case 31:
                    int i9 = typedArrayObtainStyledAttributes.getInt(index, 0);
                    eVar.f6954L = i9;
                    if (i9 == 1) {
                        Log.e(TAG, "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                    }
                    break;
                case 32:
                    int i10 = typedArrayObtainStyledAttributes.getInt(index, 0);
                    eVar.f6955M = i10;
                    if (i10 == 1) {
                        Log.e(TAG, "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
                    }
                    break;
                case 33:
                    try {
                        eVar.f6956N = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, eVar.f6956N);
                    } catch (Exception unused) {
                        if (typedArrayObtainStyledAttributes.getInt(index, eVar.f6956N) == -2) {
                            eVar.f6956N = -2;
                        }
                    }
                    break;
                case 34:
                    try {
                        eVar.f6958P = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, eVar.f6958P);
                    } catch (Exception unused2) {
                        if (typedArrayObtainStyledAttributes.getInt(index, eVar.f6958P) == -2) {
                            eVar.f6958P = -2;
                        }
                    }
                    break;
                case 35:
                    eVar.f6960R = Math.max(0.0f, typedArrayObtainStyledAttributes.getFloat(index, eVar.f6960R));
                    eVar.f6954L = 2;
                    break;
                case 36:
                    try {
                        eVar.f6957O = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, eVar.f6957O);
                    } catch (Exception unused3) {
                        if (typedArrayObtainStyledAttributes.getInt(index, eVar.f6957O) == -2) {
                            eVar.f6957O = -2;
                        }
                    }
                    break;
                case 37:
                    try {
                        eVar.f6959Q = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, eVar.f6959Q);
                    } catch (Exception unused4) {
                        if (typedArrayObtainStyledAttributes.getInt(index, eVar.f6959Q) == -2) {
                            eVar.f6959Q = -2;
                        }
                    }
                    break;
                case 38:
                    eVar.f6961S = Math.max(0.0f, typedArrayObtainStyledAttributes.getFloat(index, eVar.f6961S));
                    eVar.f6955M = 2;
                    break;
                default:
                    switch (i7) {
                        case 44:
                            p.k(eVar, typedArrayObtainStyledAttributes.getString(index));
                            break;
                        case 45:
                            eVar.f6950H = typedArrayObtainStyledAttributes.getFloat(index, eVar.f6950H);
                            break;
                        case 46:
                            eVar.f6951I = typedArrayObtainStyledAttributes.getFloat(index, eVar.f6951I);
                            break;
                        case 47:
                            eVar.f6952J = typedArrayObtainStyledAttributes.getInt(index, 0);
                            break;
                        case 48:
                            eVar.f6953K = typedArrayObtainStyledAttributes.getInt(index, 0);
                            break;
                        case 49:
                            eVar.f6962T = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, eVar.f6962T);
                            break;
                        case 50:
                            eVar.f6963U = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, eVar.f6963U);
                            break;
                        case 51:
                            eVar.f6967Y = typedArrayObtainStyledAttributes.getString(index);
                            break;
                        case 52:
                            int resourceId15 = typedArrayObtainStyledAttributes.getResourceId(index, eVar.f6992n);
                            eVar.f6992n = resourceId15;
                            if (resourceId15 == -1) {
                                eVar.f6992n = typedArrayObtainStyledAttributes.getInt(index, -1);
                            }
                            break;
                        case 53:
                            int resourceId16 = typedArrayObtainStyledAttributes.getResourceId(index, eVar.o);
                            eVar.o = resourceId16;
                            if (resourceId16 == -1) {
                                eVar.o = typedArrayObtainStyledAttributes.getInt(index, -1);
                            }
                            break;
                        case 54:
                            eVar.f6947D = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, eVar.f6947D);
                            break;
                        case 55:
                            eVar.f6946C = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, eVar.f6946C);
                            break;
                        default:
                            switch (i7) {
                                case 64:
                                    p.j(eVar, typedArrayObtainStyledAttributes, index, 0);
                                    break;
                                case 65:
                                    p.j(eVar, typedArrayObtainStyledAttributes, index, 1);
                                    break;
                                case 66:
                                    eVar.f6968Z = typedArrayObtainStyledAttributes.getInt(index, eVar.f6968Z);
                                    break;
                                case 67:
                                    eVar.f6975d = typedArrayObtainStyledAttributes.getBoolean(index, eVar.f6975d);
                                    break;
                            }
                            break;
                    }
                    break;
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        eVar.a();
        return eVar;
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mChildrenByIds = new SparseArray<>();
        this.mConstraintHelpers = new ArrayList<>(4);
        this.mLayoutWidget = new u.e();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = 257;
        this.mConstraintSet = null;
        this.mConstraintLayoutSpec = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap<>();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        this.mTempMapIdToWidget = new SparseArray<>();
        this.mMeasurer = new f(this, this);
        this.mOnMeasureWidthMeasureSpec = 0;
        this.mOnMeasureHeightMeasureSpec = 0;
        a(attributeSet, 0);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i5) {
        super(context, attributeSet, i5);
        this.mChildrenByIds = new SparseArray<>();
        this.mConstraintHelpers = new ArrayList<>(4);
        this.mLayoutWidget = new u.e();
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = Integer.MAX_VALUE;
        this.mMaxHeight = Integer.MAX_VALUE;
        this.mDirtyHierarchy = true;
        this.mOptimizationLevel = 257;
        this.mConstraintSet = null;
        this.mConstraintLayoutSpec = null;
        this.mConstraintSetId = -1;
        this.mDesignIds = new HashMap<>();
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
        this.mTempMapIdToWidget = new SparseArray<>();
        this.mMeasurer = new f(this, this);
        this.mOnMeasureWidthMeasureSpec = 0;
        this.mOnMeasureHeightMeasureSpec = 0;
        a(attributeSet, i5);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        e eVar = new e(layoutParams);
        eVar.f6969a = -1;
        eVar.f6971b = -1;
        eVar.f6973c = -1.0f;
        eVar.f6975d = true;
        eVar.f6976e = -1;
        eVar.f6978f = -1;
        eVar.f6980g = -1;
        eVar.h = -1;
        eVar.f6983i = -1;
        eVar.f6985j = -1;
        eVar.f6986k = -1;
        eVar.f6988l = -1;
        eVar.f6990m = -1;
        eVar.f6992n = -1;
        eVar.o = -1;
        eVar.f6995p = -1;
        eVar.f6997q = 0;
        eVar.f6998r = 0.0f;
        eVar.f6999s = -1;
        eVar.f7000t = -1;
        eVar.f7001u = -1;
        eVar.f7002v = -1;
        eVar.f7003w = Integer.MIN_VALUE;
        eVar.f7004x = Integer.MIN_VALUE;
        eVar.f7005y = Integer.MIN_VALUE;
        eVar.f7006z = Integer.MIN_VALUE;
        eVar.f6944A = Integer.MIN_VALUE;
        eVar.f6945B = Integer.MIN_VALUE;
        eVar.f6946C = Integer.MIN_VALUE;
        eVar.f6947D = 0;
        eVar.f6948E = 0.5f;
        eVar.f6949F = 0.5f;
        eVar.G = null;
        eVar.f6950H = -1.0f;
        eVar.f6951I = -1.0f;
        eVar.f6952J = 0;
        eVar.f6953K = 0;
        eVar.f6954L = 0;
        eVar.f6955M = 0;
        eVar.f6956N = 0;
        eVar.f6957O = 0;
        eVar.f6958P = 0;
        eVar.f6959Q = 0;
        eVar.f6960R = 1.0f;
        eVar.f6961S = 1.0f;
        eVar.f6962T = -1;
        eVar.f6963U = -1;
        eVar.f6964V = -1;
        eVar.f6965W = false;
        eVar.f6966X = false;
        eVar.f6967Y = null;
        eVar.f6968Z = 0;
        eVar.f6970a0 = true;
        eVar.f6972b0 = true;
        eVar.f6974c0 = false;
        eVar.d0 = false;
        eVar.f6977e0 = false;
        eVar.f6979f0 = -1;
        eVar.f6981g0 = -1;
        eVar.f6982h0 = -1;
        eVar.f6984i0 = -1;
        eVar.j0 = Integer.MIN_VALUE;
        eVar.f6987k0 = Integer.MIN_VALUE;
        eVar.f6989l0 = 0.5f;
        eVar.f6996p0 = new u.d();
        return eVar;
    }
}
