package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.widget.S1;

/* JADX INFO: loaded from: classes.dex */
public final class ExpandedMenuView extends ListView implements i, y, AdapterView.OnItemClickListener {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final int[] f6207f = {R.attr.background, R.attr.divider};

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public j f6208e;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        S1 s1E = S1.e(context, attributeSet, f6207f, R.attr.listViewStyle, 0);
        TypedArray typedArray = s1E.f6522b;
        if (typedArray.hasValue(0)) {
            setBackgroundDrawable(s1E.b(0));
        }
        if (typedArray.hasValue(1)) {
            setDivider(s1E.b(1));
        }
        s1E.f();
    }

    @Override // androidx.appcompat.view.menu.i
    public final boolean a(l lVar) {
        return this.f6208e.performItemAction(lVar, 0);
    }

    public int getWindowAnimations() {
        return 0;
    }

    @Override // androidx.appcompat.view.menu.y
    public final void initialize(j jVar) {
        this.f6208e = jVar;
    }

    @Override // android.widget.ListView, android.widget.AbsListView, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i5, long j5) {
        a((l) getAdapter().getItem(i5));
    }
}
