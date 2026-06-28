package androidx.constraintlayout.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public class Guideline extends View {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f6926e;

    public Guideline(Context context) {
        super(context);
        this.f6926e = true;
        super.setVisibility(8);
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
    }

    @Override // android.view.View
    public final void onMeasure(int i5, int i7) {
        setMeasuredDimension(0, 0);
    }

    public void setFilterRedundantCalls(boolean z9) {
        this.f6926e = z9;
    }

    public void setGuidelineBegin(int i5) {
        e eVar = (e) getLayoutParams();
        if (this.f6926e && eVar.f6969a == i5) {
            return;
        }
        eVar.f6969a = i5;
        setLayoutParams(eVar);
    }

    public void setGuidelineEnd(int i5) {
        e eVar = (e) getLayoutParams();
        if (this.f6926e && eVar.f6971b == i5) {
            return;
        }
        eVar.f6971b = i5;
        setLayoutParams(eVar);
    }

    public void setGuidelinePercent(float f2) {
        e eVar = (e) getLayoutParams();
        if (this.f6926e && eVar.f6973c == f2) {
            return;
        }
        eVar.f6973c = f2;
        setLayoutParams(eVar);
    }

    @Override // android.view.View
    public void setVisibility(int i5) {
    }

    public Guideline(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6926e = true;
        super.setVisibility(8);
    }
}
